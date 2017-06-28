package surveyor.regression.source;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import common.source.FunctionUtil;
import common.source.Log;
import common.source.OLMapUtility.IconColor;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.actions.ObserverViewPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.ObserverViewPage;
import surveyor.scommon.source.PageObjectFactory;

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
public class ObserverViewPageTest_EQ extends BaseMapViewTest {

	private DriverViewPageActions driverViewPageAction;
	private ArrayList<ObserverViewPageActions> observerViewPageActionList = new ArrayList<ObserverViewPageActions>();
	private TestEnvironmentActions testEnvironmentAction = ActionBuilder.createTestEnvironmentAction();
	private ArrayList<ObserverViewPage> observerViewPageList = new ArrayList<ObserverViewPage>();
	private DriverViewPage driverViewPage;
	public ObserverViewPageTest_EQ() throws IOException {
		super();
	}

	@BeforeClass
	public static void beforeTestClass() {
		initializeTestObjects();
	}

	@AfterClass
	public static void afterClass() {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initializePageObjects() {
		PageObjectFactory pageObjectFactory = new PageObjectFactory();

		driverViewPage = pageObjectFactory.getDriverViewPage();
		PageFactory.initElements(getDriver(), driverViewPage);

		LoginPage loginPage = pageObjectFactory.getLoginPage();
		setLoginPage(loginPage);
		PageFactory.initElements(getDriver(), loginPage);
		
		HomePage homePage = pageObjectFactory.getHomePage();
		setHomePage(homePage);
		PageFactory.initElements(getDriver(), homePage);
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
	
	/**
	 * Test Case ID: TC1060_ObserveActiveAndInactiveEQSurvey
	 * Test Description: -  	Observe Active and Inactive EQ Survey - Map View
	 * Script:
	 *	-On Home Page, click on Picarro Surveyors -> Online
	 *	- Click on Map and turn Map View ON
	 *	- Click on Display button
	 *	- Ask driver to stop the EQ survey
	 *		(user still driving the car)
	 * Results:
	 *	- - Survey Information is displayed in map view - Tag, Mode, Time, EQ Mode Active (bold green text), 
	 *		Driver Info, Elapsed time, 
	 *		Remaining Time, Zoom level, Surveyor and analyzer info
	 *	- The "EQ Mode Active" message in bold green font should not obscure any part of the survey details nor the map
	 *	- Car icon is displayed in red color. Breadcrumb will  be displayed in blue color
	 *	- "Isotopic Analysis" and "Field Notes" toggle buttons should not be present
	 *	- Wind Rose, Concentration Chart, Indications, Lisa and FOV toggle buttons should be present
	 *	- Curtain View button should not be present
	 *	- Car icon is displayed in grey color. Breadcrumb will be displayed in black color
	 *	- Survey Inactive message will be displayed
	 */
	@Test
	public void TC1060_ObserveActiveAndInactiveEQSurvey() throws Exception{
		Log.info("\nTestcase - TC1060_ObserveActiveAndInactiveEQSurvey\n");
		final int userDataRowID = 16;
		final int analyzerDb3DataRowID = 78;
		final int surveyDataRowID = 65;
		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, userDataRowID);   /* Picarro Driver */

		
		Log.info("Starting Analyzer...");
		testEnvironmentAction.startAnalyzer(EMPTY, analyzerDb3DataRowID); 	// start analyzer.
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);

		Log.info("Starting Replay...");
		getTestEnvironmentAction().startReplay(EMPTY, analyzerDb3DataRowID);
		// start survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.startEQDrivingSurvey(EMPTY, surveyDataRowID);

		loginAsObserver(USER_ROW_ID_PICARRO_DRIVER);

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyObserverViewPageIsOpened(EMPTY, NOTSET));

		observerViewPageActionList.get(0).getObserverViewPage().waitForAJAXCallsToComplete();
		observerViewPageActionList.get(0).clickOnMapButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnMapView(EMPTY, NOTSET);
		
		observerViewPageActionList.get(0).clickOnDisplayButton(EMPTY, NOTSET);
	
		// Check availability of toggle buttons
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchIndicationsButtonIsVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchLisasButtonIsVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchFovsButtonIsVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchConcentrationChartButtonIsVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchWindroseButtonIsVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchNotesButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchIsotopicAnalysisButtonIsNotVisible(EMPTY, NOTSET));

		// Check Survey information displayed
		observerViewPageActionList.get(0).clickOnHeaderInfoBox(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.get().surveyTag, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.get().surveyType, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeLabelStartsWith(SURVEY_INFO_TIME_PREFIX + String.valueOf(getHourOfDay()), NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeElapsedLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeRemainingLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoZoomLevelLabelEquals(String.format(SURVEY_INFO_ZOOM_LEVEL_X, 19), NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeElapsedIsTickingForward(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeRemainingLabelIsTickingBackward(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_SURVEY_STATUS_ACTIVE, NOTSET));
		
		assertTrue(observerViewPageActionList.get(0).verifyCrossHairIconIsShownOnMap("Red", NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBreadcrumbIsShownOnMap("Blue", NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyCorrectSurveyActiveMessageIsShownOnMap("EQ", NOTSET));

		// Check Inactive information displayed
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		FunctionUtil.warnOnError(() -> driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET));
		getTestEnvironmentAction().idleForSeconds("5", NOTSET);
		
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_SURVEY_STATUS_INACTIVE, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyCrossHairIconIsShownOnMap("Gray", NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBreadcrumbIsShownOnMap("Black", NOTSET));
		
		// stop simulator and PSA.
		Log.info("Stopping Analyzer...");
		getTestEnvironmentAction().stopReplay(EMPTY, NOTSET);
		getTestEnvironmentAction().stopAnalyzer(EMPTY, NOTSET);
	}
	
	/**
	 * Test Case ID: TC1067_ObserverViewEQSurveyAsCustomerDriver
	 * Test Description: - Observer View - EQ Survey as customer driver - Satellite - 
	 * 						FoV, Wind Rose and Concentration Chart ON - Position and  GIS OFF
	 * Script:
	 *	-On Home Page, click on Picarro Surveyors -> Online
	 *	- Click on Map and turn Satellite View ON
	 *	- Click Display
	 *		Wind Rose - ON
	 *		Concentration Chart - ON
	 *		FoV - ON
	 *	- Click GIS - All options OFF
	 *	- Click on Position button (to disable it)
	 * Results:
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Time, EQ Mode Active (bold green text), Driver Info, Elapsed time, Remaining Time, Zoom level, Surveyor and analyzer info
	 *	- Display should show FOV, windrose and concentration chart options only.
	 *	- Non-Picarro user should not see indication and Lisa information
	 *	- Wind rose, concentration chart, Car position, Indication, FoV  and Breadcrumb is present on map in satellite view
	 *	- Map is not centered on car's position
	 *	- LISA,Indication Assets, Boundaries data will not be displayed on map in satellite view
	 */
	@Test
	public void TC1067_ObserverViewEQSurveyAsCustomerDriver() throws Exception{
		Log.info("\nTestcase - TC1067_ObserverViewEQSurveyAsCustomerDriver\n");
		
		final int userDataRowID = 3;
		final int analyzerDb3DataRowID = 77;
		final int surveyDataRowID = 65;
		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, userDataRowID);   /* Picarro Driver */

		
		Log.info("Starting Analyzer...");
		testEnvironmentAction.startAnalyzer(EMPTY, analyzerDb3DataRowID); 	// start analyzer.
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);

		Log.info("Starting Replay...");
		getTestEnvironmentAction().startReplay(EMPTY, analyzerDb3DataRowID);
		// start survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.startEQDrivingSurvey(EMPTY, surveyDataRowID);

		loginAsObserver(userDataRowID);

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyObserverViewPageIsOpened(EMPTY, NOTSET));

		observerViewPageActionList.get(0).getObserverViewPage().waitForAJAXCallsToComplete();
		observerViewPageActionList.get(0).clickOnMapButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnSatelliteView(EMPTY, NOTSET);
		
		observerViewPageActionList.get(0).clickOnDisplayButton(EMPTY, NOTSET);
	
		// Check availability of toggle buttons
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchIndicationsButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchLisasButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchFovsButtonIsVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchConcentrationChartButtonIsVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchWindroseButtonIsVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchNotesButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchIsotopicAnalysisButtonIsNotVisible(EMPTY, NOTSET));
		
		// Check Survey information displayed
		observerViewPageActionList.get(0).clickOnHeaderInfoBox(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.get().surveyTag, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.get().surveyType, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeLabelStartsWith(SURVEY_INFO_TIME_PREFIX + String.valueOf(getHourOfDay()), NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeElapsedLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeRemainingLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoZoomLevelLabelEquals(String.format(SURVEY_INFO_ZOOM_LEVEL_X, 19), NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeElapsedIsTickingForward(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeRemainingLabelIsTickingBackward(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_SURVEY_STATUS_ACTIVE, NOTSET));
		
		assertTrue(observerViewPageActionList.get(0).verifyCrossHairIconIsShownOnMap("Red", NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBreadcrumbIsShownOnMap("Blue", NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyCorrectSurveyActiveMessageIsShownOnMap("EQ", NOTSET));

		observerViewPageActionList.get(0).clickOnGisButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffUseAllPipes(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffUseAllBoundaries(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnPositionButton(EMPTY, NOTSET);
		
		assertTrue(observerViewPageActionList.get(0).verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyLISAIsNotShownOnMap(EMPTY, NOTSET));

		// stop simulator and PSA.
		Log.info("Stopping Analyzer...");
		getTestEnvironmentAction().stopReplay(EMPTY, NOTSET);
		getTestEnvironmentAction().stopAnalyzer(EMPTY, NOTSET);
	}
}