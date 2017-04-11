package surveyor.performance.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import common.source.DateUtility;
import common.source.Log;
import common.source.TestContext;
import surveyor.dataprovider.PerformanceReportJobDataProvider.ReportJobTestCategory;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.entities.ReportJobPerfDBStat;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.SurveyorConstants.Environment;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class BaseReportJobPerformanceTest extends BasePerformanceTest {
	protected static final String EMPTY = "";
	protected static final Integer NOTSET = -1;

	public BaseReportJobPerformanceTest() {
	}

	/**
	 * This is a unit test to test compareReportJobPerfBaseline() method.
	 * The input data to the test has to be an existing report in the environment you run the unit test against.
	 * Before enabling this unit test make sure that the input test data is correct for the environment you are running against.
	 * @throws Exception
	 */
	@Ignore
	public void UnitTest_compareReportJobPerfBaseline() throws Exception {
		setComplianceReportsPage(new ComplianceReportsPage(getDriver(), getBaseURL(), getTestSetup()));
		PageFactory.initElements(getDriver(), getComplianceReportsPage());

		String testCaseID = "PerfTest3";
		String reportTitle = "9a231d51baa34934986b";
		getComplianceReportsPage().compareReportJobPerfBaseline(testCaseID, reportTitle);
	}

	protected Integer getTestExecutionTimes(Integer executionTimesForBaselines, ReportJobTestCategory category) {
		Log.method("getTestExecutionTimes", executionTimesForBaselines, category);

		Integer executionTimes = executionTimesForBaselines;
		// If not collecting baseline metrics run ONLY once.
		if (!TestContext.INSTANCE.getTestSetup().isCollectReportJobPerfMetric()) {
			executionTimes = 1;
		} else {
			Integer executionTimesFromProperties = 0;
			switch (category) {
			case High:
				executionTimesFromProperties = TestContext.INSTANCE.getTestSetup().getExecutionTimesForHighLoadReportJobPerfBaseline();
				break;
			case Light:
				executionTimesFromProperties = TestContext.INSTANCE.getTestSetup().getExecutionTimesForLightLoadReportJobPerfBaseline();
				break;
			case Medium:
				executionTimesFromProperties = TestContext.INSTANCE.getTestSetup().getExecutionTimesForMediumLoadReportJobPerfBaseline();
				break;
			case UltraHigh:
				executionTimesFromProperties = TestContext.INSTANCE.getTestSetup().getExecutionTimesForUltraHighLoadReportJobPerfBaseline();
				break;
			case LargeArea:
			case LargePipes:
				executionTimesFromProperties = TestContext.INSTANCE.getTestSetup().getExecutionTimesForLargeAreaPipesReportJobPerfBaseline();
				break;
			default:
				break;
			}
			// If value is set in test properties, give higher precedence to it.
			if (executionTimesFromProperties > 0) {
				executionTimes = executionTimesFromProperties;
			}
		}
		return executionTimes;
	}

	protected void checkAndGenerateReportJobBaselineCsv() throws IOException {
		Log.method("checkAndGenerateReportJobBaselineCsv");
		if (TestContext.INSTANCE.getTestSetup().isCollectReportJobPerfMetric()) {
			generateReportJobBaselineRunExecutionCsv(getComplianceReportsPageAction().workingDataRow.get().tCID);
		}
	}

	protected void executePerformanceTest(Integer userDataRowID, Integer reportDataRowID, Integer executionTimesForBaselines,
			String category) throws Exception, IOException {
		Log.method("executePerformanceTest", userDataRowID, reportDataRowID, executionTimesForBaselines, category);

		LocalDateTime startDate = LocalDateTime.now();

		boolean result = true;

		// Run for specified number of times depending on whether we are generating baselines or not.
		Integer testExecutionTimes = getTestExecutionTimes(executionTimesForBaselines, ReportJobTestCategory.valueOf(category));
		for (int i=0; i<testExecutionTimes; i++) {
			Log.info(String.format("Executing test iteration - %d", i));
			initializePageActions();

			createAndAssertOnReportGenerationComplete(userDataRowID, reportDataRowID);
			getComplianceReportsPageAction().getComplianceReportsPage().setReportEndEpochTime(DateUtility.getCurrentUnixEpochTime());
			result = result && getComplianceReportsPageAction().verifyReportJobBaselines(EMPTY, reportDataRowID);
		}

		// Generate the CSV if collecting baselines
		checkAndGenerateReportJobBaselineCsv();

		// Post execution results to automation DB.
		LocalDateTime endDate = LocalDateTime.now();
		postRunResultsToAutomationDB(reportDataRowID, startDate, endDate);

		if (!result) {
			Log.error("Performance baseline comparison failed! Checks error message above for failing baseline comparison.");
		}

		assertTrue(result);
	}

	protected void createAndAssertOnReportGenerationComplete(Integer userDataRowID, Integer reportDataRowID) throws Exception {
		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, userDataRowID);
		getComplianceReportsPageAction().open(EMPTY, NOTSET);
		getComplianceReportsPageAction().createNewReport(EMPTY, reportDataRowID);
		getComplianceReportsPageAction().setReportGenerationTimeout(String.valueOf(REPORT_GENERATION_TIMEOUT_1HR_IN_SECONDS), reportDataRowID);
		assertTrue(getComplianceReportsPageAction().waitForReportGenerationToCompleteWithErrorChecks(EMPTY, reportDataRowID));
	}

	protected void postRunResultsToAutomationDB(Integer reportDataRowID, LocalDateTime startDate, LocalDateTime endDate) throws Exception {
		Log.method("postRunResultsToAutomationDB", reportDataRowID, startDate, endDate);
		if (TestContext.INSTANCE.getTestSetup().isAutomationReportingApiEnabled()) {
			List<ReportJobPerfDBStat> postDBStatList = getComplianceReportsPageAction().getComplianceReportsPage().getPostDBStatList();
			if (postDBStatList!=null && postDBStatList.size() > 0) {
				Log.info(String.format("Found '%d' report jobs to post to DB.", postDBStatList.size()));
				String reportTitle = getComplianceReportsPageAction().getReportsDataRow(reportDataRowID).title;
				String testCaseID = getComplianceReportsPageAction().getReportsDataRow(reportDataRowID).tCID;
				int i = 0;
				for (ReportJobPerfDBStat reportJobPerfDBStat : postDBStatList) {
					Log.info(String.format("Posting results for report job number=%d to DB.", (i+1)));
					String reportJobTypeId = reportJobPerfDBStat.getReportJobTypeId();
					String reportJobTypeName = reportJobPerfDBStat.getReportJobTypeName();
					LocalDateTime reportJobStartTime = reportJobPerfDBStat.getReportJobStartTime();
					LocalDateTime reportJobEndTime = reportJobPerfDBStat.getReportJobEndTime();
					LocalDateTime testExecutionStartDate = startDate;
					LocalDateTime testExecutionEndDate = endDate;
					LocalDateTime reportStartTime = DateUtility.fromUnixTime(getComplianceReportsPageAction().getComplianceReportsPage().getReportStartEpochTime());
					LocalDateTime reportEndTime = DateUtility.fromUnixTime(getComplianceReportsPageAction().getComplianceReportsPage().getReportEndEpochTime());
					String buildNumber = getComplianceReportsPageAction().getComplianceReportsPage().getWebAppVersion();
					Environment environment = reportJobPerfDBStat.getEnvironment();
					TestContext.INSTANCE.getTestSetup().postReportJobPerfStat(reportTitle, reportJobTypeId, reportJobTypeName, reportJobStartTime,
							reportJobEndTime, testExecutionStartDate, testExecutionEndDate,
							reportStartTime, reportEndTime, buildNumber, testCaseID, environment);
					i++;
				}
			}
		}
	}

	/** Method to be overridden by derived test class **/
	protected void initializePageActions() {

	}

	protected HomePageActions getHomePageAction() {
		return null;
	}

	protected LoginPageActions getLoginPageAction() {
		return null;
	}

	protected TestEnvironmentActions getTestEnvironmentAction() {
		return null;
	}

	protected ComplianceReportsPageActions getComplianceReportsPageAction() {
		return null;
	}

	protected ComplianceReportsPage getComplianceReportsPage() {
		return null;
	}

	protected void setComplianceReportsPage(ComplianceReportsPage cmpReportsPage) {
	}
}