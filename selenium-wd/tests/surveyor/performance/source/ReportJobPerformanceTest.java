package surveyor.performance.source;

import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.Log;
import common.source.TestContext;
import surveyor.dataprovider.PerformanceReportJobDataProvider;
import surveyor.dataprovider.PerformanceReportJobDataProvider.ReportJobTestCategory;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class ReportJobPerformanceTest extends BasePerformanceTest {
	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;
	private static final Integer REPORT_GENERATION_TIMEOUT_IN_SECONDS = 3600;  // Max timeout= 1hr for report gen.
	
	private static HomePageActions homePageAction;
	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static TestEnvironmentActions testEnvironmentAction;
	
	private static ComplianceReportsPage complianceReportsPage;
	
	@BeforeClass
	public static void beforeTestClass() throws Exception {
		initializePageActions();
	}

	@Before
	public void beforeTestMethod() {
		initializeProperties();
	}
		
	/**
	 * Initializes the page action objects.
	 */
	protected static void initializePageActions() {
		loginPageAction = new LoginPageActions(driver, baseURL, testSetup);
		homePageAction = new HomePageActions(driver, baseURL, testSetup);
		complianceReportsPageAction = new ComplianceReportsPageActions(driver, baseURL, testSetup);
		testEnvironmentAction = new TestEnvironmentActions();
	}

	public ReportJobPerformanceTest() {
	}

	/**
	 * Test Case ID: ReportJob_PerformanceTest
	 * Script: -  	
	 *	- - Login as specified user
	 *	- - Create New Compliance Report with specified report data
	 *  - - Wait for Report to be generated
	 *  - - Query DB to find processing time for each report job
	 * Results: - 
	 *	- - Verify report job processing time values confirm to the baseline values.
	 */
	@Test
	@UseDataProvider(value = PerformanceReportJobDataProvider.REPORT_JOB_PERFORMANCE_PROVIDER, location = PerformanceReportJobDataProvider.class)
	public void ReportJob_PerformanceTest(String rallyTestCaseID, Integer userDataRowID, Integer reportDataRowID,
			Integer executionTimesForBaselines, String category) throws Exception {
		Log.info(String.format("\nRunning [%s] ReportJob_PerformanceTest ...", rallyTestCaseID));
		
		// Run for specified number of times depending on whether we are generating baselines or not.
		Integer testExecutionTimes = getTestExecutionTimes(executionTimesForBaselines, ReportJobTestCategory.valueOf(category));
		for (int i=0; i<testExecutionTimes; i++) {
			initializePageActions();
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, userDataRowID);   
			complianceReportsPageAction.open(EMPTY, NOTSET);
			complianceReportsPageAction.createNewReport(EMPTY, reportDataRowID);
			complianceReportsPageAction.setReportGenerationTimeout(String.valueOf(REPORT_GENERATION_TIMEOUT_IN_SECONDS), reportDataRowID);
			complianceReportsPageAction.waitForReportGenerationToComplete(EMPTY, reportDataRowID);
			complianceReportsPageAction.verifyReportJobBaselines(EMPTY, reportDataRowID);
		}

		// Generate the CSV if collecting baselines
		checkAndGenerateReportJobBaselineCsv();
	}

	/**
	 * This is a unit test to test compareReportJobPerfBaseline() method.
	 * The input data to the test has to be an existing report in the environment you run the unit test against.
	 * Before enabling this unit test make sure that the input test data is correct for the environment you are running against.
	 * @throws Exception
	 */
	@Ignore
	public void UnitTest_compareReportJobPerfBaseline() throws Exception {
		complianceReportsPage = new ComplianceReportsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, complianceReportsPage);
		
		String testCaseID = "PerfTest3";
		String reportTitle = "9a231d51baa34934986b";
		complianceReportsPage.compareReportJobPerfBaseline(testCaseID, reportTitle);
	}

	protected Integer getTestExecutionTimes(Integer executionTimesForBaselines, ReportJobTestCategory category) {
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

	private void checkAndGenerateReportJobBaselineCsv() throws IOException {
		if (TestContext.INSTANCE.getTestSetup().isCollectReportJobPerfMetric()) {
			generateReportJobBaselineRunExecutionCsv(complianceReportsPageAction.workingDataRow.tCID);
		}
	}
}