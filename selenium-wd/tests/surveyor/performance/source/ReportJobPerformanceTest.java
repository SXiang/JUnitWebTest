package surveyor.performance.source;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.ComplianceReportsPage;

public class ReportJobPerformanceTest extends BasePerformanceTest {
	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;
	
	private static HomePageActions homePageAction;
	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static TestEnvironmentActions testEnvironmentAction;

	private static ComplianceReportsPage complianceReportsPage;
	
	@BeforeClass
	public static void beforeTestClass() throws Exception {
		initializePageActions();
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
	 * Test Case ID: TC1841_PerfTest_LightLoad_TestCase3
	 * Script: -  	
	 *	- - Login as <USER>
	 *	- - Create New Compliance Report
	 *  - - Wait for Report to be generated
	 *  - - Query DB to find processing time for each report job
	 * Results: - 
	 *	- - Verify report job processing time values confirm to the baseline values.
	 */
	@Test
	public void TC1841_PerfTest_LightLoad_TestCase3() throws Exception {
		Log.info("\nRunning TC1841_PerfTest_LightLoad_TestCase3 ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* [TODO: Use Correct User] */
		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.createNewReport(EMPTY, 10);
		complianceReportsPageAction.waitForReportGenerationToComplete(EMPTY, 10);
		complianceReportsPageAction.verifyReportJobBaselines(EMPTY, 10);
	}
	
	/**
	 * Test Case ID: TC1842_PerfTest_MediumLoad_TestCase6
	 * Script: -  	
	 *	- - Login as <USER>
	 *	- - Create New Compliance Report
	 *  - - Wait for Report to be generated
	 *  - - Query DB to find processing time for each report job
	 * Results: - 
	 *	- - Verify report job processing time values confirm to the baseline values.
	 */
	@Test
	public void TC1842_PerfTest_MediumLoad_TestCase6() throws Exception {
		Log.info("\nRunning TC1842_PerfTest_MediumLoad_TestCase6 ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* [TODO: Use Correct User] */
		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.createNewReport(EMPTY, 11);
		complianceReportsPageAction.waitForReportGenerationToComplete(EMPTY, 11);
		complianceReportsPageAction.verifyReportJobBaselines(EMPTY, 11);
	}
	
	/**
	 * Test Case ID: TC1843_PerfTest_HighLoadNormalPlat_TestCase8
	 * Script: -  	
	 *	- - Login as <USER>
	 *	- - Create New Compliance Report
	 *  - - Wait for Report to be generated
	 *  - - Query DB to find processing time for each report job
	 * Results: - 
	 *	- - Verify report job processing time values confirm to the baseline values.
	 */
	@Test
	public void TC1843_PerfTest_HighLoadNormalPlat_TestCase8() throws Exception {
		Log.info("\nRunning TC1843_PerfTest_HighLoadNormalPlat_TestCase8 ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* [TODO: Use Correct User] */
		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.createNewReport(EMPTY, 12);
		complianceReportsPageAction.waitForReportGenerationToComplete(EMPTY, 12);
		complianceReportsPageAction.verifyReportJobBaselines(EMPTY, 12);
	}
	
	/**
	 * Test Case ID: TC1844_PerfTest_UltraHighLoad_TestCase10
	 * Script: -  	
	 *	- - Login as <USER>
	 *	- - Create New Compliance Report
	 *  - - Wait for Report to be generated
	 *  - - Query DB to find processing time for each report job
	 * Results: - 
	 *	- - Verify report job processing time values confirm to the baseline values.
	 */
	@Test
	public void TC1844_PerfTest_UltraHighLoad_TestCase10() throws Exception {
		Log.info("\nRunning TC1844_PerfTest_UltraHighLoad_TestCase10 ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* [TODO: Use Correct User] */
		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.createNewReport(EMPTY, 13);
		complianceReportsPageAction.waitForReportGenerationToComplete(EMPTY, 13);
		complianceReportsPageAction.verifyReportJobBaselines(EMPTY, 13);
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
}