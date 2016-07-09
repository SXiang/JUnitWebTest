package surveyor.performance.source;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.DriverFactory;
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
public class BaseReportJobPerformanceTest extends BasePerformanceTest {
	protected static final String EMPTY = "";
	protected static final Integer NOTSET = -1;
	protected static final Integer REPORT_GENERATION_TIMEOUT_IN_SECONDS = 3600;  // Max timeout= 1hr for report gen.

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
	public void beforeTestMethod() throws Exception {
		initializeProperties();
	}
	
	/**
	 * Initializes the page action objects.
	 */
	protected static void initializePageActions() {
		homePageAction = new HomePageActions(driver, baseURL, testSetup);
		loginPageAction = new LoginPageActions(driver, baseURL, testSetup);
		complianceReportsPageAction = new ComplianceReportsPageActions(driver, baseURL, testSetup);
		testEnvironmentAction = new TestEnvironmentActions();
	}

	public HomePageActions getHomePageAction() {
		return homePageAction;
	}

	public LoginPageActions getLoginPageAction() {
		return loginPageAction;
	}
	
	public ComplianceReportsPageActions getComplianceReportsPageAction() {
		return complianceReportsPageAction;
	}

	public TestEnvironmentActions getTestEnvironmentAction() {
		return testEnvironmentAction;
	}

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

	protected void checkAndGenerateReportJobBaselineCsv() throws IOException {
		if (TestContext.INSTANCE.getTestSetup().isCollectReportJobPerfMetric()) {
			generateReportJobBaselineRunExecutionCsv(complianceReportsPageAction.workingDataRow.tCID);
		}
	}
	
	protected void executePerformanceTest(Integer userDataRowID, Integer reportDataRowID, Integer executionTimesForBaselines,
			String category) throws Exception, IOException {
		// Run for specified number of times depending on whether we are generating baselines or not.
		Integer testExecutionTimes = getTestExecutionTimes(executionTimesForBaselines, ReportJobTestCategory.valueOf(category));
		for (int i=0; i<testExecutionTimes; i++) {
			initializePageActions();
			
			getLoginPageAction().open(EMPTY, NOTSET);
			getLoginPageAction().login(EMPTY, userDataRowID);   
			getComplianceReportsPageAction().open(EMPTY, NOTSET);
			getComplianceReportsPageAction().createNewReport(EMPTY, reportDataRowID);
			getComplianceReportsPageAction().setReportGenerationTimeout(String.valueOf(REPORT_GENERATION_TIMEOUT_IN_SECONDS), reportDataRowID);
			getComplianceReportsPageAction().waitForReportGenerationToComplete(EMPTY, reportDataRowID);
			getComplianceReportsPageAction().verifyReportJobBaselines(EMPTY, reportDataRowID);
		}

		// Generate the CSV if collecting baselines
		checkAndGenerateReportJobBaselineCsv();
	}
}