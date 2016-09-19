package surveyor.regression.source;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;
import java.util.concurrent.atomic.AtomicInteger;

import common.source.Log;
import common.source.WebDriverFactory;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;

import com.googlecode.junittoolbox.ParallelParameterized;
import org.junit.Test;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;

@RunWith(ParallelParameterized.class)
public class ComplianceReportsPageLoadTest extends BaseReportsPageActionTest {

	private static AtomicInteger testIndex = new AtomicInteger();
	private ThreadLocal<HomePageActions> homePageAction = new ThreadLocal<HomePageActions>();
	private ThreadLocal<LoginPageActions> loginPageAction = new ThreadLocal<LoginPageActions>();
	private ThreadLocal<ManageCustomerPageActions> manageCustomerPageAction = new ThreadLocal<ManageCustomerPageActions>();
	private ThreadLocal<ManageUsersPageActions> manageUsersPageAction = new ThreadLocal<ManageUsersPageActions>();
	private ThreadLocal<ManageLocationPageActions> manageLocationPageAction = new ThreadLocal<ManageLocationPageActions>();
	private ThreadLocal<ComplianceReportsPageActions> complianceReportsPageAction = new ThreadLocal<ComplianceReportsPageActions>();
	private ThreadLocal<TestEnvironmentActions> testEnvironmentAction = new ThreadLocal<TestEnvironmentActions>();
	private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

	@SuppressWarnings("unused")
	private String testCaseID;
	private Integer userDataRowID;
	private Integer reportDataRowID1;
	@SuppressWarnings("unused")
	private Integer reportDataRowID2;

	@Parameters(name = "{index}: TC243[TestCaseID={0},userDataRowID={1},reportDataRowID1={2},reportDataRowID2={3}]")
    public static Iterable<Object[]> tc243_data() {
        return asList(
    		new Object[]{ "TC243-1" /*TestCaseID*/, 4 /*userDataRowID*/,  112 /*reportDataRowID1*/, -1/*reportDataRowID2*/},
    		new Object[]{ "TC243-2" /*TestCaseID*/, 4 /*userDataRowID*/,  113 /*reportDataRowID1*/, -1/*reportDataRowID2*/},
    		new Object[]{ "TC243-3" /*TestCaseID*/, 4 /*userDataRowID*/,  114 /*reportDataRowID1*/, -1/*reportDataRowID2*/},
    		new Object[]{ "TC243-4" /*TestCaseID*/, 4 /*userDataRowID*/,  115 /*reportDataRowID1*/, -1/*reportDataRowID2*/},
    		new Object[]{ "TC243-5" /*TestCaseID*/, 4 /*userDataRowID*/,  116 /*reportDataRowID1*/, -1/*reportDataRowID2*/}
        );
    }

    public ComplianceReportsPageLoadTest(String testCaseId, Integer userDataRowId,  Integer reportDataRowId1, Integer reportDataRowId2) {
    	this.testCaseID = testCaseId;
    	this.userDataRowID = userDataRowId;
    	this.reportDataRowID1 = reportDataRowId1;
    	this.reportDataRowID2 = reportDataRowId2;
    }

	@BeforeClass
	public static void beforeClass() throws Exception{
		initializeTestObjects(false /*initializeDriver*/);
	}

	@Before
	public void beforeTest() throws Exception{
		initializeTestObjects(false /*initializeDriver*/);

		// Create new web driver for each test.
		webDriver.set(WebDriverFactory.getDriver(testIndex.incrementAndGet()));

		initializePageActions();

		// Select run mode here.
		setPropertiesForTestRunMode();
	}

	private void setPropertiesForTestRunMode() throws Exception {
		setTestRunMode(ReportTestRunMode.FullTestRun);

		if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			complianceReportsPageAction.get().fillWorkingDataForReports(getUnitTestReportRowID());
		}
	}

	/**
	 * Initializes the page action objects.
	 * @throws Exception
	 */
	protected void initializePageActions() throws Exception {
		homePageAction.set(new HomePageActions(webDriver.get(), getBaseURL(), getTestSetup()));
		manageCustomerPageAction.set(new ManageCustomerPageActions(webDriver.get(), getBaseURL(), getTestSetup()));
		manageUsersPageAction.set(new ManageUsersPageActions(webDriver.get(), getBaseURL(), getTestSetup()));
		manageLocationPageAction.set(new ManageLocationPageActions(webDriver.get(), getBaseURL(), getTestSetup()));
		loginPageAction.set(new LoginPageActions(webDriver.get(), getBaseURL(), getTestSetup()));
		testEnvironmentAction.set(new TestEnvironmentActions());
		complianceReportsPageAction.set(new ComplianceReportsPageActions(webDriver.get(), getBaseURL(), getTestSetup()));
		setReportsPage((ComplianceReportsPage)complianceReportsPageAction.get().getPageObject());
	}

	/**
	 * Test Case ID: TC243_Generate4_5_ComplianceReportsWithMultipleViewsSurveysInParallel
	 * Test Description: Generate multiple compliance reports (4-5) at the same time
	 * Validation:
	 *	- Generate 4 or 5 compliance reports simultaneously with multiple views and multiple surveys
	 * Expected Result:
	 *	- Reports generated successfully
	 */
	@Test
	public void TC243_Generate4_5_ComplianceReportsWithMultipleViewsSurveysInParallel() throws Exception {
		Log.info("\nRunning TC243_Generate4_5_ComplianceReportsWithMultipleViewsSurveysInParallel ...");

		loginPageAction.get().open(EMPTY, NOTSET);
		loginPageAction.get().login(EMPTY, getUserRowID(userDataRowID));

		complianceReportsPageAction.get().open(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.get().createNewReport(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.get().waitForReportGenerationToComplete(EMPTY, getReportRowID(reportDataRowID1)));
		quitWebDriver();
		setCleanUpPerformed(true);
	}

	private void quitWebDriver() {
		this.webDriver.get().quit();
	}
}