package surveyor.regression.source;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.CUSUSERROLEDR;
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
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.BaseMapViewPage.DisplaySwitchType;
import surveyor.scommon.source.BaseMapViewPage.GisSwitchType;
import surveyor.scommon.source.BaseMapViewPage.MapSwitchType;
import surveyor.scommon.source.DriverViewPage.CloudCover;
import surveyor.scommon.source.DriverViewPage.SolarRadiation;
import surveyor.scommon.source.DriverViewPage.SurveyTime;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.DriverViewPage.Wind;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;
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
public class DriverViewPageTest2 extends BaseMapViewTest {

	private DriverViewPageActions driverViewPageAction;
	private static DriverViewPage driverViewPage;
	private static ManageCustomersPage manageCustomersPage = null;
	private static ManageUsersPage manageUsersPage = null;
	private static LoginPage loginPage;

	public DriverViewPageTest2() throws IOException {
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

		// Additional page objects.
		manageCustomersPage = pageObjectFactory.getManageCustomersPage();
		PageFactory.initElements(getDriver(),  manageCustomersPage);

		manageUsersPage = pageObjectFactory.getManageUsersPage();
		PageFactory.initElements(getDriver(),  manageUsersPage);
	}

	/* * Test Case ID: TC2124_PicarroAdminCanSelectAnyModeOnCustomerAnalyzerWithLicense
	 * Script:
	 * - Log into the tablet as Picarro Admin
	 * - Click on the Mode button
	 * - Click on the Start Survey button
	 * Results:
	 * - User will see buttons for "Start Driving Survey", "Start EQ Survey" and "System Shutdown"
	 * - Survey conditions popup will appear. Standard, Operator, Rapid Response, Assessment and Manual mode buttons are available
	 */
	@Test /*EQ survey needs to be enabled for the analyzer in this test*/
	public void TC2124_SimulatorTest_PicarroAdminCanSelectAnyModeOnCustomerAnalyzerWithLicense() throws Exception{
		Log.info("\nTestcase - TC2124_SimulatorTest_PicarroAdminCanSelectAnyModeOnCustomerAnalyzerWithLicense\n");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

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

		// Stop current simulator and start another with a different Analyzer.
		testEnvironmentAction.get().stopAnalyzer(EMPTY, NOTSET);
	}

	/* * Test Case ID: TC1227_SimulatorTest_StartDrivingSurvey_CurtainView
	 * Script:
	 *	- 1. Login to driver view
	 *	- 2. Click on Mode -> Start Driving Survey
	 *	- 3. Provide survey tag, select  Survey Time: Day Solar Radiation: Overcast Wind: Calm Survey Type: Standard
	 *	- 4. Click on Start Survey button
	 *	- 5. Click on Curtain view button - (Position ON by default)
	 *	- 6. Click Up - Click Down - Click Left - Click Right
	 *	- 7. Click Zoom In - Click Zoom Out
	 *	- 8. Click on Return
	 *	- 9. Turn OFF Position
	 *	- 10. Click on Curtain view button - (Position OFF now) -
	 *	- 11. Click Up - Click Down - Click Left - Click Right
	 *	- 12. Click Zoom In - Click Zoom Out
	 * Results:
	 *	- 1. Red color cursor will move along with car position and blue spikes are displayed
	 *	- 2. View will be tilted/rotated as instructed
	 *	- 3. User will be zoomed in or out as instructed
	 *	- 4. User will be returned to Driver View page
	 *	- 5. User will be navigated to Curtain View again
	 *	- 6. Map will move up, down, left, right as instructed instead of tilting/rotating
	 *	- 7. User will be zoomed in or out as instructed
	 */
	@Test /*Lack of verifications - view tilted/rotated, move as instructed, zoomed as instructed*/
	public void TC1227_SimulatorTest_StartDrivingSurvey_CurtainView() throws Exception {
		Log.info("\nRunning TC1227_StartDrivingSurvey_CurtainView");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		testEnvironmentAction.get().startAnalyzer(EMPTY, 3); 	// start analyzer. SimAuto-Analyzer1
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
		testEnvironmentAction.get().startReplay(EMPTY, 3); 	// start replay db3 file.

		// start survey and verify menu items
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.showCurtainView(EMPTY, NOTSET);
//		assertTrue(driverViewPageAction.verifySpikesAreDisplayed(EMPTY, NOTSET));
//		assertTrue(driverViewPageAction.verifyRedCursorIsMovingWithCarPosition(EMPTY, NOTSET));
		driverViewPageAction.clickOnCurtainArrowUpButton(EMPTY, NOTSET);
		driverViewPageAction.clickOnCurtainArrowDownButton(EMPTY, NOTSET);
		driverViewPageAction.clickOnCurtainArrowLeftButton(EMPTY, NOTSET);
		driverViewPageAction.clickOnCurtainArrowRightButton(EMPTY, NOTSET);
		driverViewPageAction.clickOnCurtainZoomOutButton(EMPTY, NOTSET);
		driverViewPageAction.clickOnCurtainZoomInButton(EMPTY, NOTSET);

		driverViewPageAction.clickOnCurtainReturnButton(EMPTY, NOTSET);
		driverViewPageAction.turnOffPosition(EMPTY, NOTSET);

		driverViewPageAction.showCurtainView(EMPTY, NOTSET);
//		assertTrue(driverViewPageAction.verifySpikesAreDisplayed(EMPTY, NOTSET));
//		assertTrue(driverViewPageAction.verifyRedCursorIsMovingWithCarPosition(EMPTY, NOTSET));
		driverViewPageAction.clickOnCurtainArrowUpButton(EMPTY, NOTSET);
		driverViewPageAction.clickOnCurtainArrowDownButton(EMPTY, NOTSET);
		driverViewPageAction.clickOnCurtainArrowLeftButton(EMPTY, NOTSET);
		driverViewPageAction.clickOnCurtainArrowRightButton(EMPTY, NOTSET);
		driverViewPageAction.clickOnCurtainZoomOutButton(EMPTY, NOTSET);
		driverViewPageAction.clickOnCurtainZoomInButton(EMPTY, NOTSET);

		// Stop current simulator and start another with a different Analyzer.
		testEnvironmentAction.get().stopAnalyzer(EMPTY, NOTSET);
	}

	/* * Test Case ID: TC2098_SimulatorTest_DriverCanSelectOperatorRROrmanulModesWithLicense
	 * Script:
	 * - Log into the UI as Picarro Admin and navigate to the Manage Customers page
	 * - Select a customer and click the "Edit" button
	 * - Check the Rapid Response checkbox and click OK
	 * - Log into the tablet as a customer driver role user
	 * - Click on the mode button
	 * - Click on the Start Survey button
	 * - Fill out the details and select Rapid Response as the survey type
	 * - Repeat with Operator mode
	 * - Repeat with Manual mode, but user must be either Picarro or customer admin user
	 * Results:
	 * - User will see a list of Customers
	 * - User will see a list of features enabled/disabled for customer
	 * - Driver user will be taken to Driver View
	 * - Mode button will bring up menu with two or three selections, including "Start Survey"
	 * - Popup will appear where user can add tag, select environmental conditions and survey mode. Rapid Response will be among the selections
	 * - Survey will start
	 */
	@Test
	public void TC2098_SimulatorTest_DriverCanSelectOperatorRROrmanulModesWithLicense() throws Exception {
		Log.info("\nRunning TC2098_SimulatorTest_DriverCanSelectOperatorRROrmanulModesWithLicense");

		loginPageAction.get().open(EMPTY, NOTSET);
		loginPageAction.get().login(EMPTY, USER_ROW_ID_PICARRO_DRIVER);   /* Picarro Driver */

		startDrivingSurvey(driverViewPageAction, ANALYZER1_REPLAY_ROW_ID, 43, ONE_SECOND); /* Rapid Response */
		assertTrue(driverViewPageAction.verifyDriverViewPageIsOpened(EMPTY, NOTSET));

		startDrivingSurvey(driverViewPageAction, ANALYZER1_REPLAY_ROW_ID, 44, ONE_SECOND); /* Operator */
		assertTrue(driverViewPageAction.verifyDriverViewPageIsOpened(EMPTY, NOTSET));

		LoginPageActions.clearStoredObjects();
		loginPageAction.get().open(EMPTY, NOTSET);
		loginPageAction.get().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */

		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
		startDrivingSurvey(driverViewPageAction, ANALYZER1_REPLAY_ROW_ID, 45, ONE_SECOND); /* Manual */
		assertTrue(driverViewPageAction.verifyDriverViewPageIsOpened(EMPTY, NOTSET));

		// Stop current simulator
		testEnvironmentAction.get().stopAnalyzer(EMPTY, NOTSET);
	}

	/* * Test Case ID: TC2099_SimulatorTest_DriverCanNotSelectOperatorRROrmanulModesWithoutLicense
	 * Script:
	 * - Log into the UI as Picarro Admin and navigate to the Manage Customers page
	 * - Confirm that customer does not have Rapid Response, Operator, or Manual modes enabled
	 * - Log into the tablet as a customer admin user
	 * - Click on the mode button
	 * - Click on the Start Survey button
	 * Results:
	 * - User will see a list of Customers
	 * - User will see Rapid Response, Operator and Manual survey modes unchecked
	 * - Mode button will bring up two or three buttons, including "Start Survey"
	 * - Popup will appear where user can select environmental conditions and survey modes. Rapid Response, Operator and Manual will not be among the selections. (Standard will always be available, and depending on the customer's tier, Assessment may also be available)
	 */
	@Test
	public void TC2099_SimulatorTest_DriverCanNotSelectOperatorRROrmanulModesWithoutLicense() throws Exception {
		Log.info("\nRunning TC2099_SimulatorTest_DriverCanNotSelectOperatorRROrmanulModesWithoutLicense");

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

		// verify these buttons are NOT showing.
		assertFalse(WebElementExtender.isElementPresentAndDisplayed(driverViewPageAction.getDriverViewPage().getRapidResponseButton()));
		assertFalse(WebElementExtender.isElementPresentAndDisplayed(driverViewPageAction.getDriverViewPage().getOperatorButton()));
		assertFalse(WebElementExtender.isElementPresentAndDisplayed(driverViewPageAction.getDriverViewPage().getManualButton()));

		// Stop current simulator
		testEnvironmentAction.get().stopAnalyzer(EMPTY, NOTSET);
	}

	/* * Test Case ID: TC2132_CurtainViewNotAvailableWithoutLicense
	 * Script:
	 * - Log into Driver View
	 * - Click Mode -> Start Survey
	 * - Fill out survey details and click "Start Survey"
	 * - Check bottom panel for Curtain View button
	 * Results:
	 * - Curtain View button should not be present
	 */
	@Test
	public void TC2132_SimulatorTest_CurtainViewNotAvailableWithoutLicense() throws Exception {
		Log.info("\nRunning TC2132_SimulatorTest_CurtainViewNotAvailableWithoutLicense");

		loginPageAction.get().open(EMPTY, NOTSET);
		loginPageAction.get().login(EMPTY, USER_ROW_ID_CUSTOMER_ADMIN_NOLIC);   /* Utility Admin */
		testEnvironmentAction.get().startAnalyzer(EMPTY, 39);

		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
		testEnvironmentAction.get().startReplay(EMPTY, 3); 	// start replay db3 file.
		assertFalse(driverViewPageAction.getDriverViewPage().isCurtainButtonPresent());
	}
}