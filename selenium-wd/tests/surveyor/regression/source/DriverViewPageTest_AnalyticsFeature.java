package surveyor.regression.source;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.CUSUSERROLEDR;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.REGBASEUSERNAME;
import static surveyor.scommon.source.SurveyorConstants.SQACUS;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import common.source.BrowserCommands;
import common.source.DateUtility;
import common.source.ExceptionUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.WebElementExtender;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.BaseMapViewPage.DisplaySwitchType;
import surveyor.scommon.source.BaseMapViewPage.GisSwitchType;
import surveyor.scommon.source.BaseMapViewPage.MapSwitchType;
import surveyor.scommon.source.BaseReportsPageActionTest.ReportTestRunMode;
import surveyor.scommon.source.DriverViewPage.CloudCover;
import surveyor.scommon.source.DriverViewPage.SolarRadiation;
import surveyor.scommon.source.DriverViewPage.SurveyTime;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.DriverViewPage.Wind;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyorTestRunner;

/*
 * **** NOTES ****:
 *  1. Action based tests that work on MapView (Survey, Observer, Driver) can derive from BaseMapViewTest.
 *  2. If any of the tests do NOT use TestEnvironment actions for starting Analyzer and simulator then
 *  they should follow this convention to start simulator:
 *    Mark the test as TC*_SimulatorTest_* and it will be detected as Simulator based test and will trigger
 *    installation of Simulator pre-requisites before running the test.
 *
 */
@RunWith(SurveyorTestRunner.class)
public class DriverViewPageTest_AnalyticsFeature extends BaseMapViewTest {

	private DriverViewPageActions driverViewPageAction;
	private static DriverViewPage driverViewPage;
	private static ManageCustomersPage manageCustomersPage;
	private static ManageCustomerPageActions manageCustomerPageActions;
	private static ManageUsersPage manageUsersPage = null;
	private static LoginPage loginPage;
	private static Map<String, String> testAccount;

	public DriverViewPageTest_AnalyticsFeature() throws IOException {
		super();
	}

	@BeforeClass
	public static void beforeTestClass() {
		initializeTestObjects();
	}

	@Before

	public void beforeTestMethod() throws Exception {

		try {
			initializeTestObjects();
			initializePageObjects();
			driverViewPageAction = new DriverViewPageActions(getDriver(), getBaseURL(), getTestSetup());
			TestSetup.restartAnalyzer();
		} catch (IOException e) {
			Log.info(ExceptionUtility.getStackTraceString(e));
		}

		if(testAccount == null){
			testAccount = createTestAccount("LicFeature");
		}else{
			getLoginPage().open();
			getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
			manageCustomerPageActions.open(EMPTY, NOTSET);
			manageCustomerPageActions.getManageCustomersPage().editAndSelectLicensedFeatures(testAccount.get("customerName"), LicensedFeatures.values());
		}
	}

	private void initializePageObjects() {
		PageObjectFactory pageObjectFactory = new PageObjectFactory();

		loginPage = pageObjectFactory.getLoginPage();
		PageFactory.initElements(getDriver(), loginPage);

		driverViewPage = pageObjectFactory.getDriverViewPage();
		PageFactory.initElements(getDriver(), driverViewPage);

		// Additional page objects.
		manageCustomersPage = pageObjectFactory.getManageCustomersPage();
		PageFactory.initElements(getDriver(),  manageCustomersPage);

		manageUsersPage = pageObjectFactory.getManageUsersPage();
		PageFactory.initElements(getDriver(),  manageUsersPage);
	}


	/**
	 * Initializes the page action objects.
	 * @throws Exception
	 */
	protected static void initializePageActions() throws Exception {
		manageCustomerPageActions = new ManageCustomerPageActions(getDriver(), getBaseURL(), getTestSetup());
	}


	/* * Test Case ID: TC2332_DriverViewAnalyticsSurveyModeForLicensedCustomers
	 * Script:
	 * - Log into the UI as Picarro Admin
	 * -Navigate to the Manage Customers page
	 * -Select a customer that does not have Analytics license enabled and click on the Edit button
	 * -Click on the Analytics checkbox 
	 * -Click OK
	 * -Log out of the UI
	 * -Log in to driver view
	 * -Click on Start Survey button
	 * -Start the survey
	 * Results:
	 * - User will see a list of customers
	 * - User will see configuration page for that customer with list of licensable features
	 * - Analytics checkbox is now checked
	 * - User is taken back to list of customers
	 * - Customer user navigates to driver view page
	 * - "Analytics" survey mode button is present
	 * - User is able to conduct analytics survey
	 */
	@Test /*Analytics survey needs to be enabled for the analyzer in this test*/
	public void TC2332_DriverViewAnalyticsSurveyModeForLicensedCustomers() throws Exception{
		Log.info("\nTestcase - TC2332_DriverViewAnalyticsSurveyModeForLicensedCustomers\n");

		
		String userName = testAccount.get("userName");
		String userPassword = testAccount.get("userPassword");
	//	String customerName = testAccount.get("customerName");

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);

		testEnvironmentAction.get().startAnalyzer(EMPTY, 30); 	// start analyzer. SimAuto-Analyzer1
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
		testEnvironmentAction.get().startReplay(EMPTY, 3); 	// start replay db3 file.

		// start survey and verify menu items
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		//	assertTrue(driverViewPageAction.getDriverViewPage().getStartEQSurveyButton().isDisplayed());
		assertTrue(driverViewPageAction.getDriverViewPage().getStartSurveyButton().isDisplayed());
		assertTrue(driverViewPageAction.getDriverViewPage().getSystemShutdownButton().isDisplayed());

		// click Start Survey button.
		driverViewPageAction.getDriverViewPage().clickStartSurveyButton();
		driverViewPageAction.getDriverViewPage().waitForStartSurveyModalDialogToShow();

		// verify survey options.
		assertTrue(driverViewPageAction.getDriverViewPage().getManualButton().isDisplayed());
		assertTrue(driverViewPageAction.getDriverViewPage().getOperatorButton().isDisplayed());
		assertTrue(driverViewPageAction.getDriverViewPage().getStandardButton().isDisplayed());
		assertTrue(driverViewPageAction.getDriverViewPage().getRapidResponseButton().isDisplayed());
		assertTrue(driverViewPageAction.getDriverViewPage().getAssessmentButton().isDisplayed());
		assertTrue(driverViewPageAction.getDriverViewPage().getAnalyticsButton().isDisplayed());
		
		// Stop current simulator and start another with a different Analyzer.
		testEnvironmentAction.get().stopAnalyzer(EMPTY, NOTSET);
	}

}