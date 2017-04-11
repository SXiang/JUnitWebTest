package surveyor.regression.source;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import common.source.ExceptionUtility;
import common.source.Log;
import common.source.TestSetup;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.MeasurementSessionsPage;
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
public class AnalyticsLicenseFeature_DriverViewTests extends BaseMapViewTest {

	private DriverViewPageActions driverViewPageAction;
	private static DriverViewPage driverViewPage;
	private static ManageCustomersPage manageCustomersPage = null;
	private static ManageUsersPage manageUsersPage = null;
	private static LoginPage loginPage;
	private static Map<String, String> testAccount, testSurvey;
	private static MeasurementSessionsPage measurementSessionsPage;
	private static ManageCustomerPageActions manageCustomerPageAction;
	
	public AnalyticsLicenseFeature_DriverViewTests() throws IOException {
		super();
	}

	@BeforeClass
	public static void beforeTestClass() {
		initializeTestObjects();
	}

	@Before
	public void beforeTestMethod() {
		try {
			initializeTestObjects();
			initializePageObjects();
			driverViewPageAction = new DriverViewPageActions(getDriver(), getBaseURL(), getTestSetup());
			TestSetup.restartAnalyzer();
		} catch (UnknownHostException e) {
			Log.info(ExceptionUtility.getStackTraceString(e));
		} catch (IOException e) {
			Log.info(ExceptionUtility.getStackTraceString(e));
		}
	}

	private void initializePageObjects() {
		PageObjectFactory pageObjectFactory = new PageObjectFactory();

		loginPage = pageObjectFactory.getLoginPage();
		PageFactory.initElements(getDriver(), loginPage);

		driverViewPage = pageObjectFactory.getDriverViewPage();
		PageFactory.initElements(getDriver(), driverViewPage);

		manageCustomersPage = pageObjectFactory.getManageCustomersPage();
		PageFactory.initElements(getDriver(),  manageCustomersPage);

		manageUsersPage = pageObjectFactory.getManageUsersPage();
		PageFactory.initElements(getDriver(),  manageUsersPage);
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
	@Test /*waiting for survey configuration for analytics survey mode --steven x*/
	public void TC2332_DriverViewAnalyticsSurveyModeForLicensedCustomers() throws Exception{
		Log.info("\nTestcase - TC2332_DriverViewAnalyticsSurveyModeForLicensedCustomers\n");

		String userName = testAccount.get("userName");
		String userPassword = testAccount.get("userPassword");
		String customerName = testAccount.get("customerName");

		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		/* Unselect Anaytics*/
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.ANALYTICS);

		getHomePage().logout();

		/* Select Analytics*/
		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndSelectLicensedFeatures(customerName, LicensedFeatures.ANALYTICS);

		getHomePage().logout();

		loginPage.open();
		loginPage.loginNormalAs(userName, userPassword);

		testSurvey = addTestSurvey(testAccount.get("analyzerName"), testAccount.get("analyzerSharedKey")
				,testAccount.get("userName"), testAccount.get("userPassword"), 20 /*surveyRuntimeInSeconds*/, SurveyType.Analytics);

		getHomePage().clickOnDrivingSurveyLink();
		measurementSessionsPage.waitForTableDataToLoad();
		assertTrue(measurementSessionsPage.getTableRows().size() > 0);
		measurementSessionsPage.performSearch(testSurvey.get("surveyTag"));
		measurementSessionsPage.waitForTableDataToLoad();
		assertTrue(measurementSessionsPage.getTableRows().size() > 0);
	}

	/* * Test Case ID: TC2335_CreateNewCustomerWithAnalyticsSurveyLicense
	 * Script:
	 * - Log into the UI as Picarro Admin
	 * - Navigate to the Manage Customers page and click on the Add New Customer button
	 * - Fill out the Name and EULA fields, and check the Account Enabled and Analytics buttons and click OK
	 * - Navigate to the Manage Locations page and click on the Add New Locations button
	 * - Fill out the necessary fields and click OK
	 * - Navigate to the Manage Users page and click on the Add New Customer User button
	 * - Select the newly-created customer from the dropdown, select Supervisor or Utility Admin role and fill out the other necessary information and click OK
	 * - Add surveyor
	 * - Add analyzer
	 * - Add reference gas bottle
	 * - Log out of the UI
	 * - Log in to driver view
	 * - Click on Start survey button
	 * Results:
	 * - User if taken to the Dashboard page
	 * - User is navigated to the New Customer page
	 * - User is navigated back to the list of customers and newly created customer is present
	 * - User is navigated to the New Location page
	 * - User is navigated back to the list of locations and newly created location is present
	 * - User is navigated to the New User page
	 * - User is navigated back to the list of users and the newly created user is present
	 * - Customer user is navigated to driver view
	 * - "Analytics" survey mode button is present
	 * - User is able to conduct analytics survey
	 */
	@Test /*waiting for survey configuration for analytics survey mode --steven x*/
	public void TC2335_CreateNewCustomerWithAnalyticsSurveyLicense() throws Exception{
		Log.info("\nTestcase - TC2335_CreateNewCustomerWithAnalyticsSurveyLicense\n");

		String userName = testAccount.get("userName");
		String userPassword = testAccount.get("userPassword");

		loginPage.open();
		loginPage.loginNormalAs(userName, userPassword);

		testSurvey = addTestSurvey(testAccount.get("analyzerName"), testAccount.get("analyzerSharedKey")
				,testAccount.get("userName"), testAccount.get("userPassword"), 20 /*surveyRuntimeInSeconds*/, SurveyType.Analytics);

		getHomePage().clickOnDrivingSurveyLink();
		measurementSessionsPage.waitForTableDataToLoad();
		assertTrue(measurementSessionsPage.getTableRows().size() > 0);
		measurementSessionsPage.performSearch(testSurvey.get("surveyTag"));
		measurementSessionsPage.waitForTableDataToLoad();
		assertTrue(measurementSessionsPage.getTableRows().size() > 0);
	}

	
	/* * Test Case ID: TC2334_DriverViewAnalyticsSurveyModeLicenseRevokedFromCustomer
	 * Script:
	 * - Log into the UI as Picarro Admin
	 * - Navigate to the Manage Customers page
	 * - Select a customer that has an Analytics license and click the Edit button
	 * - Uncheck the Analytics checkbox and click OK
	 * - Log out of the UI
	 * - Log in to driver view
	 * - Click on Start survey button
	 * Results:
	 * - User is taken to Dashboard
	 * - User is shown a list of customers
	 * - User is shown the configuration for that customer and a list of licensable features
	 * - User is taken back to list of customers
	 * - Customer user navigated to driver view
	 * - "Analytics" survey mode button is not present
	 */
	@Test /*waiting for survey configuration for analytics survey mode --steven x*/
	public void TC2334_DriverViewAnalyticsSurveyModeLicenseRevokedFromCustomer() throws Exception{
		Log.info("\nTestcase - TC2334_DriverViewAnalyticsSurveyModeLicenseRevokedFromCustomer\n");

		String userName = testAccount.get("userName");
		String userPassword = testAccount.get("userPassword");
		String customerName = testAccount.get("customerName");

		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		/* Unselect Anaytics*/
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.ANALYTICS);

		getHomePage().logout();

		loginPage.open();
		loginPage.loginNormalAs(userName, userPassword);

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
		assertFalse(driverViewPageAction.getDriverViewPage().getAnalyticsButton().isDisplayed());
	}

	/* * Test Case ID: TC2333_DriverView-AnalyticsSurveyModeNotAvailableForUnlicensedCustomers
	 * Script:
	 * - Customer don't have Analytics license feature
	 * - Log in to driver view
	 * - Click on Start Survey button
	 * Results:
	 * - Customer user navigated to driver view
	 * - "Analytics" survey mode button is not present
	 */
	@Test /*waiting for survey configuration for analytics survey mode --steven x*/
	public void TC2333_DriverViewAnalyticsSurveyModeNotAvailableForUnlicensedCustomers() throws Exception{
		Log.info("\nTestcase - TC2333_DriverView-AnalyticsSurveyModeNotAvailableForUnlicensedCustomers\n");

		String userName = testAccount.get("userName");
		String userPassword = testAccount.get("userPassword");
		String customerName = testAccount.get("customerName");

		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		/* Unselect Anaytics*/
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.ANALYTICS);

		getHomePage().logout();

		loginPage.open();
		loginPage.loginNormalAs(userName, userPassword);

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
		assertFalse(driverViewPageAction.getDriverViewPage().getAnalyticsButton().isDisplayed());
	}

}