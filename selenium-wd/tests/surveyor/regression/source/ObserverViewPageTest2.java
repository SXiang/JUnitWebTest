package surveyor.regression.source;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import common.source.Log;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.SurveyorTestRunner;

import surveyor.scommon.actions.ObserverViewPageActions;
import surveyor.scommon.source.ObserverViewPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;

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
public class ObserverViewPageTest2 extends BaseMapViewTest {

	// Change this: When test defaulted to Analyzer 1.
	private DriverViewPageActions driverViewPageAction;
	private ArrayList<ObserverViewPageActions> observerViewPageActionList = new ArrayList<ObserverViewPageActions>();

	private DriverViewPage driverViewPage;
	private ArrayList<ObserverViewPage> observerViewPageList = new ArrayList<ObserverViewPage>();

	private static ManageCustomersPage manageCustomersPage;
	private static LoginPage loginPage;
	private static Map<String, String> testAccount;

	public ObserverViewPageTest2() throws IOException {
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
			initializePageActionsList();
			initializeBasePageActions();
			initializeObserverViewPageActionList();

			driverViewPageAction = new DriverViewPageActions(getDriver(), getBaseURL(), getTestSetup());

			if(testAccount == null){
				testAccount = createTestAccount("ObserverViewTest2");
			}else{
				loginPage.open();
				loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
				manageCustomersPage.open();
				manageCustomersPage.editAndSelectLicensedFeatures(testAccount.get("customerName"), LicensedFeatures.values());
			}
		} catch (Exception e) {
			e.printStackTrace();
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
	}

	private void initializeObserverViewPageActionList(){
		for(int i = observerViewPageActionList.size(); i < driverList.size(); i++){
			observerViewPageActionList.add(new ObserverViewPageActions(driverList.get(i), baseURLList.get(i), testSetupList.get(i)));
			// Initialize page objects.
			observerViewPageList.add(new ObserverViewPage(driverList.get(i), baseURLList.get(i), testSetupList.get(i)));
			PageFactory.initElements(getDriver(), observerViewPageList.get(i));
		}
	}
	private void startDrivingSurvey(Integer analyzerRowId, Integer surveyRowId, Integer idleTimeInSeconds) throws Exception {
		startDrivingSurvey(driverViewPageAction, analyzerRowId, surveyRowId, idleTimeInSeconds);
	}
	private void startDrivingSurvey(String analyzerSerialNumber, String analyzerSharedkey, Integer analyzerRowId, Integer surveyRowId, Integer idleTimeInSeconds) throws Exception {
		startDrivingSurvey(driverViewPageAction,analyzerSerialNumber, analyzerSharedkey, analyzerRowId, surveyRowId, idleTimeInSeconds);
	}

	private void stopSurveyAndAnalyzer() {
		stopSurveyAndAnalyzer(driverViewPageAction);
	}

	private void loginAsObserver(int userRowID) throws Exception {
		loginAsObserver(userRowID, 0);
	}
	private void loginAsObserver(int userRowID, int index) throws Exception {
		loginPageActionList.get(index).open(EMPTY, NOTSET);
		LoginPageActions.workingDataRow.set(null);
		loginPageActionList.get(index).login(EMPTY, userRowID); /* Picarro Admin */
	}

	private void loginAsDriver(int userRowID) throws Exception {
		loginPageAction.get().open(EMPTY, NOTSET);
		LoginPageActions.workingDataRow.set(null);
		loginPageAction.get().login(EMPTY, userRowID); /* Picarro Admin */
	}
	private void loginAsObserver(String usernameColonPassword, int index) throws Exception {
		loginPageActionList.get(index).open(EMPTY, NOTSET);
		LoginPageActions.workingDataRow.set(null);
		loginPageActionList.get(index).login(usernameColonPassword,NOTSET) ;
	}

	private void loginAsDriver(String usernameColonPassword) throws Exception {
		loginPageAction.get().open(EMPTY, NOTSET);
		LoginPageActions.workingDataRow.set(null);
		loginPageAction.get().login(usernameColonPassword,NOTSET) ;
	}

	/**
	 * Test Case ID: TC299_SimulatorTest_VerifyObserverViewInfo
	 * Test Description:
	 * - Log into UI as customer user (Utility Admin, Support, Driver)
	 * - On Home Page, click on Surveyors
	 * - Click on Online link
	 * Result:
	 * - User is navigated to Dashboard
	 * - User is taken to list of Surveyors for that customer
	 * - User is navigated to Observer View
	 * - Lag time between Driver and Observer view to display survey data should not be higher than 8 sec
	 * - Survey information till the current time should be displayed
	 */
	@Test /*Lack of verification of 'Lag time between Driver and Observer view to display survey data should not be higher than 8 sec'*/
	public void TC299_VerifyObserverViewInfo() throws Exception{
		Log.info("\nTestcase - TC299_SimulatorTest_VerifyObserverViewInfo\n");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_DRIVER);

		homePageActionList.get(0).clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyObserverViewPageIsOpened(EMPTY, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC353_CurtainViewIsDisplayedAndUserIsAbleToFollowTheVehicle
	 * Test Description:Observer View - Curtain View is displayed and user is able to Follow the vehicle
	 * - Script:
	 * -  On Home Page, click on Picarro Surveyors -> Online -> Curtain
	 * - Click on Return.
	 * - Turn Position OFF and click on Curtain
	 * - Click on Return
	 * Result:
	 * - Red color cursor will move along with car position and blue spikes are displayed
	 * - Cursor will stop moving but blue spikes will be displayed
	 * - User will be returned to Observer View page
	 */
	@Test /*TBD: verifySpikesAreDisplayed, verifyRedCursorIsMovingWithCarPosition*/
	public void TC353_CurtainViewIsDisplayedAndUserIsAbleToFollowTheVehicle() throws Exception{
		Log.info("\nTestcase - TC353_CurtainViewIsDisplayedAndUserIsAbleToFollowTheVehicle\n");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_DRIVER);

		homePageActionList.get(0).clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyObserverViewPageIsOpened(EMPTY, NOTSET));
		observerViewPageActionList.get(0).showCurtainView(EMPTY, NOTSET);
//		assertTrue(observerViewPageActionList.get(0).verifySpikesAreDisplayed(EMPTY, NOTSET));
//		assertTrue(observerViewPageActionList.get(0).verifyRedCursorIsMovingWithCarPosition(EMPTY, NOTSET));
		observerViewPageActionList.get(0).clickOnCurtainReturnButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnPositionButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffPosition(EMPTY, NOTSET);
		observerViewPageActionList.get(0).showCurtainView(EMPTY, NOTSET);
//		assertTrue(observerViewPageActionList.get(0).verifySpikesAreDisplayed(EMPTY, NOTSET));
//		assertFalse(observerViewPageActionList.get(0).verifyRedCursorIsMovingWithCarPosition(EMPTY, NOTSET));
		observerViewPageActionList.get(0).clickOnCurtainReturnButton(EMPTY, NOTSET);
		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC2133_CurtainViewIsNotAvailableWithoutLicense
	 * Test Description:Observer View - Curtain View is displayed and user is able to Follow the vehicle
	 * Script:
	 * - Log into UI
	 * - Click Online link for one of the customer's surveyors
	 * - Check bottom panel for Curtain View button
	 * Result:
	 * - User is navigated to Observer View for selected surveyor
	 * - Curtain View button should not be present
	 */
	@Test
	public void TC2133_CurtainViewIsNotAvailableWithoutLicense() throws Exception{
		Log.info("\nTestcase - TC2133_CurtainViewIsNotAvailableWithoutLicense\n");

		String analyzerName = testAccount.get("analyzerName");
		String analyzerSharedKey = testAccount.get("analyzerSharedKey");
		String userName = testAccount.get("userName");
		String userPassword = testAccount.get("userPassword");
		String customerName = testAccount.get("customerName");

		loginAsDriver(USER_ROW_ID_AUTOMATION_ADMIN);
		manageCustomersPage.open();
		manageCustomersPage.editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.CURTAINVIEW);

		loginAsDriver(userName+":"+userPassword);
		loginAsObserver(userName+":"+userPassword, 0);

		startDrivingSurvey(analyzerName, analyzerSharedKey, ANALYZER1_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND);
		homePageActionList.get(0).open(getLiveObservePath(), NOTSET);
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyObserverViewPageIsOpened(EMPTY, NOTSET));
		assertFalse(observerViewPageActionList.get(0).getObserverViewPage().isCurtainButtonPresent());

		stopSurveyAndAnalyzer();
	}

	/* * Test Case ID: TC2079_UserNotAbleToNavigateToObserverViewOnFleetMapScreenWithoutLicense
	 * Script:
	 * - Log in as utility admin or supervisor
	 * - On home page, click on Fleet map
	 * Results:
	 * - Fleet map link is present
	 * - Fleet Map page showing locations of customer's surveyor vehicles.
	 * - Online status is present for surveyor but it's not clickable (i.e. Suveyor name link is not click able). User can't navigate to observer view
	 */
	@Test
	public void TC2079_UserNotAbleToNavigateToObserverViewOnFleetMapScreenWithoutLicense() throws Exception {
		Log.info("\nRunning TC2079_UserNotAbleToNavigateToObserverViewOnFleetMapScreenWithoutLicense");

		String analyzerName = testAccount.get("analyzerName");
		String analyzerSharedKey = testAccount.get("analyzerSharedKey");
		String userName = testAccount.get("userName");
		String userPassword = testAccount.get("userPassword");
		String customerName = testAccount.get("customerName");

		loginAsDriver(USER_ROW_ID_AUTOMATION_ADMIN);
		manageCustomersPage.open();
		manageCustomersPage.editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.FLEETMAPVIEW);

		loginAsDriver(userName+":"+userPassword);
		loginAsObserver(userName+":"+userPassword, 0);

		startDrivingSurvey(analyzerName, analyzerSharedKey, ANALYZER1_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND);
		homePageActionList.get(0).open(getLiveObservePath(), NOTSET);
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyObserverViewPageIsOpened(EMPTY, NOTSET));
		assertFalse(observerViewPageActionList.get(0).getObserverViewPage().verifyFleetMapLinkIsClickable());

		stopSurveyAndAnalyzer();
	}
	/* * Test Case ID: TC2080_ObserverLinknotPresentWithoutLicense
	 * Script:
	 * - Log in as utility admin or supervisor
	 * - On home page, Surveyor section present on dashboard
	 * - Click on Online link present next to online Surveyor in Active Surveyors section
	 * - Log in as Picarro admin
	 * - Navigate to Manage Customer page and click on Edit button of above customer (Eg, CNP)
	 * - Disable "Observer View" privilege
	 * - Log in as utility admin or supervisor
	 * - On home page, Surveyor section present on dashboard
	 * Results:
	 * - Online link will be available for all online surveyors only. Offline surveyor will not have any clickable link
	 * - User is navigated to Observer view
	 * - Online status is present but its not clickable
	 */
	@Test
	public void TC2080_ObserverLinknotPresentWithoutLicense() throws Exception {
		Log.info("\nRunning TC2080_ObserverLinknotPresentWithoutLicense");

		String analyzerName = testAccount.get("analyzerName");
		String analyzerSharedKey = testAccount.get("analyzerSharedKey");
		String userName = testAccount.get("userName");
		String userPassword = testAccount.get("userPassword");
		String customerName = testAccount.get("customerName");

		loginAsDriver(USER_ROW_ID_AUTOMATION_ADMIN);
		manageCustomersPage.open();
		manageCustomersPage.editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.OBSERVERVIEW);

		loginAsDriver(userName+":"+userPassword);
		loginAsObserver(userName+":"+userPassword, 0);

		startDrivingSurvey(analyzerName, analyzerSharedKey, ANALYZER1_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND);
		homePageActionList.get(0).getHomePage().open();
		assertFalse(homePageActionList.get(0).getHomePage().verifyLinkFirstOnlineSurveyorClickable());

		stopSurveyAndAnalyzer();
	}
}
