package surveyor.regression.source;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import common.source.ExceptionUtility;
import common.source.Log;
import common.source.TestSetup;
import common.source.WebElementExtender;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.ManageAnalyzerPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.ManageRefGasBottlesPageActions;
import surveyor.scommon.actions.ManageSurveyorPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.actions.SurveyViewPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.entities.CustomerSurveyInfoEntity;
import surveyor.scommon.generators.TestDataGenerator;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
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
	private static LoginPage loginPage;
	private static HomePage homePage;
	private static MeasurementSessionsPage measurementSessionsPage;
	private static ManageCustomerPageActions manageCustomerPageAction;
	private static ManageUsersPageActions manageUsersPageAction;
	private static ManageLocationPageActions manageLocationPageAction;
	private static ManageAnalyzerPageActions manageAnalyzerPageAction;
	private static ManageSurveyorPageActions manageSurveyorPageAction;
	private static ManageRefGasBottlesPageActions manageRefGasBottlesPageAction;

	public AnalyticsLicenseFeature_DriverViewTests() throws IOException {
		super();
	}

	@BeforeClass
	public static void beforeTestClass() {
		initializeTestObjects();
		manageCustomerPageAction = ActionBuilder.createManageCustomerPageAction();
		manageUsersPageAction = ActionBuilder.createManageUsersPageAction();
		manageLocationPageAction = ActionBuilder.createManageLocationPageAction();
		manageAnalyzerPageAction = ActionBuilder.createManageAnalyzerPageAction();
		manageSurveyorPageAction = ActionBuilder.createManageSurveyorPageAction();
		manageRefGasBottlesPageAction = ActionBuilder.createManageRefGasBottlePageAction();

	}

	@Before
	public void beforeTestMethod() {
		try {
			initializeTestObjects();
			initializePageObjects();
			initializePageActions();
			TestSetup.restartAnalyzer();
		} catch (UnknownHostException e) {
			Log.info(ExceptionUtility.getStackTraceString(e));
		} catch (IOException e) {
			Log.info(ExceptionUtility.getStackTraceString(e));
		}
	}

	private void initializePageActions() {
		driverViewPageAction = new DriverViewPageActions(getDriver(), getBaseURL(), getTestSetup());
		new SurveyViewPageActions(getDriver(), getBaseURL(), getTestSetup());
	}

	private void initializePageObjects() {
		PageObjectFactory pageObjectFactory = new PageObjectFactory();

		loginPage = pageObjectFactory.getLoginPage();
		PageFactory.initElements(getDriver(), loginPage);

		homePage = pageObjectFactory.getHomePage();
		PageFactory.initElements(getDriver(), homePage);

		measurementSessionsPage = pageObjectFactory.getMeasurementSessionsPage();
		PageFactory.initElements(getDriver(), measurementSessionsPage);
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
	@Test
	public void TC2332_DriverViewAnalyticsSurveyModeForLicensedCustomers() throws Exception{
		Log.info("\nTestcase - TC2332_DriverViewAnalyticsSurveyModeForLicensedCustomers\n");

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		// Create new customer.
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.createNewCustomer(EMPTY, 14 /*customerRowID*/);

		// Create new location.
		manageLocationPageAction.open(EMPTY, NOTSET);
		manageLocationPageAction.createNewLocation(EMPTY, 17 /*locationRowID*/);

		// Create new user.
		manageUsersPageAction.open(EMPTY, NOTSET);
		manageUsersPageAction.createNewCustomerUser(EMPTY, 26 /*userRowID*/);

		// Create new surveyor.
		manageSurveyorPageAction.open(EMPTY, NOTSET);
		manageSurveyorPageAction.createNewSurveyor(EMPTY, 25 /*surveyorRowID*/);

		// Create new analyzer.
		manageAnalyzerPageAction.open(EMPTY, NOTSET);
		manageAnalyzerPageAction.createNewAnalyzer(EMPTY, 23 /*analyzerRowID*/);

		// Create new ref gas bottle.
		manageRefGasBottlesPageAction.open(EMPTY, NOTSET);
		manageRefGasBottlesPageAction.createNewRefGasBottle(EMPTY, 7 /*refGasBottleRowID*/);

		String customerName = ManageCustomerPageActions.workingDataRow.get().name;
		String newUsername = ManageUsersPageActions.workingDataRow.get().username;
		String newUserPass = ManageUsersPageActions.workingDataRow.get().password;

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		//Unselect Analytics License
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.ANALYTICS);
		homePage.logout();

		//Select Analytics License
		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndSelectLicensedFeatures(customerName, LicensedFeatures.ANALYTICS);
		homePage.logout();

		TestEnvironmentActions.generateSurveyForUser(newUsername, newUserPass,
				61, 64, 60, (driverPageAction) -> {
					// Include verifications to perform once the Survey has started and before Stop survey is called.
					assertTrue(driverPageAction.verifyCorrectAnalyticsSurveyActiveMessageIsShownOnMap(EMPTY, NOTSET));
					return true;
				});
	}

	/* * Test Case ID: TC2335_CreateNewCustomerWithAnalyticsSurveyLicense
	 * Script:
	 * - Log into the UI as Picarro Admin
	 * - Navigate to the Manage Customers page and click on the Add New Customer button
	 * - Fill out the Name and EULA fields, and check the Account Enabled and Analytics buttons and click OK
	 * - Navigate to the Manage Locations page and click on the Add New Locations button
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
	@Test 
	public void TC2335_CreateNewCustomerWithAnalyticsSurveyLicense() throws Exception{
		Log.info("\nTestcase - TC2335_CreateNewCustomerWithAnalyticsSurveyLicense\n");

		final int DB3_ANALYZER_ROW_ID = 61;	 	/* TestEnvironment datasheet rowID (specifies Analyzer, Replay DB3) */
		final int SURVEY_ROW_ID = 64;	 		/* Survey information  */
		final int SURVEY_RUNTIME_IN_SECONDS = 60; /* Number of seconds to run the survey for. */
		final int newCustomerRowID = 14;
		final int newLocationRowID = 17;
		final int newCustomerUserRowID = 26;
		final int newSurveyorRowID = 25;
		final int newAnalyzerRowID = 23;
		final int newRefGasBottleRowID = 7;

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		CustomerSurveyInfoEntity custSrvInfo = new CustomerSurveyInfoEntity(newCustomerRowID, newLocationRowID, newCustomerUserRowID, newAnalyzerRowID,
				newSurveyorRowID, newRefGasBottleRowID, DB3_ANALYZER_ROW_ID, SURVEY_RUNTIME_IN_SECONDS, SURVEY_ROW_ID);
		new TestDataGenerator().generateNewCustomerAndSurvey(custSrvInfo, (driverPageAction) -> {
			assertTrue(driverPageAction.verifyCorrectAnalyticsSurveyActiveMessageIsShownOnMap(EMPTY, NOTSET));
			return true;
		});
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
	@Test 
	public void TC2334_DriverViewAnalyticsSurveyModeLicenseRevokedFromCustomer() throws Exception{
		Log.info("\nTestcase - TC2334_DriverViewAnalyticsSurveyModeLicenseRevokedFromCustomer\n");

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		// Create new customer.
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.createNewCustomer(EMPTY, 14 /*customerRowID*/);

		// Create new location.
		manageLocationPageAction.open(EMPTY, NOTSET);
		manageLocationPageAction.createNewLocation(EMPTY, 17 /*locationRowID*/);

		// Create new user.
		manageUsersPageAction.open(EMPTY, NOTSET);
		manageUsersPageAction.createNewCustomerUser(EMPTY, 26 /*userRowID*/);

		// Create new surveyor.
		manageSurveyorPageAction.open(EMPTY, NOTSET);
		manageSurveyorPageAction.createNewSurveyor(EMPTY, 25 /*surveyorRowID*/);

		// Create new analyzer.
		manageAnalyzerPageAction.open(EMPTY, NOTSET);
		manageAnalyzerPageAction.createNewAnalyzer(EMPTY, 23 /*analyzerRowID*/);

		// Create new ref gas bottle.
		manageRefGasBottlesPageAction.open(EMPTY, NOTSET);
		manageRefGasBottlesPageAction.createNewRefGasBottle(EMPTY, 7 /*refGasBottleRowID*/);

		String customerName = ManageCustomerPageActions.workingDataRow.get().name;
		String newUsername = ManageUsersPageActions.workingDataRow.get().username;
		String newUserPass = ManageUsersPageActions.workingDataRow.get().password;

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		//Unselect Analytics License
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.ANALYTICS);
		homePage.logout();

		loginPage.open();
		loginPage.loginNormalAs(newUsername, newUserPass);

		getTestEnvironmentAction().startAnalyzer(EMPTY, 61); 	// start analyzer. RFADS2004-PICARRO
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
		getTestEnvironmentAction().startReplay(EMPTY, 61); 	// start replay db3 file.

		// start survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.getDriverViewPage().getSystemShutdownButton().isDisplayed());

		// click Start Survey button.
		driverViewPageAction.getDriverViewPage().clickStartSurveyButton();
		driverViewPageAction.getDriverViewPage().waitForStartSurveyModalDialogToShow();

		// verify Analytics button is NOT showing.
		assertFalse(WebElementExtender.isElementPresentAndDisplayed(driverViewPageAction.getDriverViewPage().getAnalyticsButton()));

		// Stop current simulator
		testEnvironmentAction.get().stopAnalyzer(EMPTY, NOTSET);
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
	@Test 
	public void TC2333_DriverViewAnalyticsSurveyModeNotAvailableForUnlicensedCustomers() throws Exception{
		Log.info("\nTestcase - TC2333_DriverView-AnalyticsSurveyModeNotAvailableForUnlicensedCustomers\n");

		loginPageAction.get().open(EMPTY, NOTSET);
		loginPageAction.get().login(EMPTY, USER_ROW_ID_CUSTOMER_ADMIN_NOLIC);   /* Utility Admin */
		testEnvironmentAction.get().startAnalyzer(EMPTY, 39);

		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
		testEnvironmentAction.get().startReplay(EMPTY, 3); 	// start simulator and replay db3 file.

		// start survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.getDriverViewPage().getSystemShutdownButton().isDisplayed());

		// click Start Survey button.
		driverViewPageAction.getDriverViewPage().clickStartSurveyButton();
		driverViewPageAction.getDriverViewPage().waitForStartSurveyModalDialogToShow();

		// verify Analytics button is NOT showing.
		assertFalse(WebElementExtender.isElementPresentAndDisplayed(driverViewPageAction.getDriverViewPage().getAnalyticsButton()));

		// Stop current simulator
		testEnvironmentAction.get().stopAnalyzer(EMPTY, NOTSET);
	}
}