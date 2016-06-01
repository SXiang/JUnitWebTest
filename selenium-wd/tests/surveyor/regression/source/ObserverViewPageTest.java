package surveyor.regression.source;

import static org.junit.Assert.*;
import common.source.Log;
import common.source.TestSetup;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.SurveyorTestRunner;

import surveyor.scommon.actions.ObserverViewPageActions;
import surveyor.scommon.source.ObserverViewPage;

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
public class ObserverViewPageTest extends BaseMapViewTest {

	// Change this: When test defaulted to Analyzer 1.
	private static String SURVEY_INFO_SURVEYOR_ANALYZER_FOR_TEST = SURVEY_INFO_SURVEYOR3_ANALYZER3;

	private static DriverViewPageActions driverViewPageAction;
	private static ObserverViewPageActions observerViewPageAction;

	private static DriverViewPage driverViewPage;
	private static ObserverViewPage observerViewPage;

	@BeforeClass
	public static void beforeTestClass() throws Exception {
		disposeProcesses();

		// Initialize needed at Class level for automation reports.
		initializePageActions();
	}

	@Before
	public void beforeTestMethod() {
		try {
			// Initialize 2nd instance before 1st, since we want the TestContext to be set to the 1st instance.
			initializePageActions2();
			initializePageActions();

			observerViewPageAction = new ObserverViewPageActions(driver, baseURL, testSetup);
			driverViewPageAction = new DriverViewPageActions(driver2, baseURL2, testSetup2);

			// Initialize page objects.
			observerViewPage = new ObserverViewPage(driver, testSetup, baseURL);
			PageFactory.initElements(driver, observerViewPage);

			driverViewPage = new DriverViewPage(driver2, testSetup2, baseURL2);
			PageFactory.initElements(driver2, driverViewPage);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void afterTestMethod() {
		try {
			afterTest();
			afterTest2();

			disposeProcesses();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void startDrivingSurvey(Integer analyzerRowId, Integer surveyRowId, Integer idleTimeInSeconds) throws Exception {
		startDrivingSurvey(driverViewPageAction, analyzerRowId, surveyRowId, idleTimeInSeconds);
	}

	private void startReplay(Integer analyzerRowId) throws Exception {
		startReplay(driverViewPageAction, analyzerRowId);
	}

	private void stopSurveyAndAnalyzer() {
		stopSurveyAndAnalyzer(driverViewPageAction);
	}

	private void stopSurvey() {
		stopSurvey(driverViewPageAction);
	}

	private void loginAsDriver(int userRowID) throws Exception {
		loginPageAction2.open(EMPTY, NOTSET);
		loginPageAction2.login(EMPTY, userRowID); /* Picarro Admin */
	}

	private void loginAsObserver(int userRowID) throws Exception {
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, userRowID); /* Picarro Admin */
	}

	/**
	 * Test Case ID: TC344_ObserverViewShouldNotModeButton Script: - Pre-requisite: - At least one of the surveyors should be active/online - On Home Page, click on Picarro Surveyors -& Online
	 * Results: - - Mode button not present. Start Driving Survey, Stop Driving Survey and shutdown analyzer buttons are not present
	 */
	@Test
	public void TC344_ObserverViewShouldNotModeButton() throws Exception {
		Log.info("\nRunning TC344_ObserverViewShouldNotModeButton ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		// ## Start a Standard Driving Survey. Run for few seconds
		startDrivingSurvey(ANALYZER3_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		// Goto home page and click on first Online Surveyor link.
		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);

		// ## Verifications
		observerViewPageAction.verifyModeButtonIsNotVisible(EMPTY, NOTSET);

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC345_ObserverShouldAbleSeeStatusUpdateWhenVehicleWarmingUp Script: - - - On Home Page, click on Picarro Surveyors -& Online -& Status - - Click on Display button - - Click on Map
	 * button - - Click on GIS button - - Click on Status button Results: - - Analyzer Warming Up message and analyzer info is displayed - Position button is by default selected and car icon is
	 * displayed - All the Display options like Indications, LISA, FoV, Concentration Chart, WindRose, Field Notes and Isotopic Analysis are ON - Satellite View is by default ON and Map view is OFF -
	 * All Asset types and boundaries level are OFF (if customer has assets associated to them) or no assets and boundaries options are displayed to user - Status should display same info as in driver
	 * view. Status is red and on expanding flow, temp gauges, etc are also red
	 */
	@Ignore
	public void TC345_ObserverShouldAbleSeeStatusUpdateWhenVehicleWarmingUp() throws Exception {
		Log.info("\nRunning TC345_ObserverShouldAbleSeeStatusUpdateWhenVehicleWarmingUp ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		// Start Analyzer & simulate Instrument Warming
		startReplay(ANALYZER1_INSTRUMENT_WARMING_ROW_ID);
		// TODO: Implement check to verify Analyzer warming message is shown.
		// assertTrue(observerViewPageAction.verifyAnalyzerWarmingMessageIsShown(EMPTY, NOTSET));

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);

		assertTrue(observerViewPageAction.verifyDisplaySwitchIndicationsButtonIsVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyDisplaySwitchLisasButtonIsVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyDisplaySwitchFovsButtonIsVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyDisplaySwitchConcentrationChartButtonIsVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyDisplaySwitchWindroseButtonIsVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyDisplaySwitchNotesButtonIsVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyDisplaySwitchIsotopicAnalysisButtonIsVisible(EMPTY, NOTSET));

		observerViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		assertTrue(observerViewPageAction.verifySatelliteViewIsShown(EMPTY, NOTSET));

		observerViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		assertTrue(observerViewPageAction.verifyGisBoundaryBigBoundaryButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyGisBoundarySmallBoundaryButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyGisMaterialTypeCastIronButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyGisMaterialTypeCopperButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyGisMaterialTypeOtherPlasticButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyGisMaterialTypePEPlasticButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyGisMaterialTypeProtectedSteelButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyGisMaterialTypeUnProtectedSteelButtonIsNotVisible(EMPTY, NOTSET));

		observerViewPageAction.clickOnStatusButton(EMPTY, NOTSET);
		assertTrue(observerViewPageAction.verifyStatusButtonIsExpanded(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyStatusButtonIsRed(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyFlowButtonIsRed(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyHBTempButtonIsRed(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyWBTempButtonIsRed(EMPTY, NOTSET));

		assertTrue(observerViewPageAction.verifyPositionButtonIsSelected(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyCrossHairIconIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));

		// TODO: Post Browser TAB implemented do this CHECK.
		// Status should display same info as in driver view

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC346_ViewOperatorSurveyObserverViewWhenEverythingLoaded Script: - - - On Home Page, click on Picarro Surveyors -& Online - Display: All ON - Position auto-center: ON - Map View:
	 * Map - GIS: All ON Results: - - User can see car position, wind rose, concentration chart, breadcrumb, FOV, Indications, LISA, Isotopic Analysis results (if any) and Field Notes (if any) - All
	 * pipes data and boundaries are displayed
	 */
	@Ignore
	public void TC346_ViewOperatorSurveyObserverViewWhenEverythingLoaded() throws Exception {
		Log.info("\nRunning TC346_ViewOperatorSurveyObserverViewWhenEverythingLoaded ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_OPERATOR_ROW_ID, SURVEY_OPERATOR1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageAction.turnOnPosition(EMPTY, NOTSET);
		observerViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnMapView(EMPTY, NOTSET);
		observerViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(observerViewPageAction.verifyCrossHairIconIsShownOnMap(COLOR_RED, NOTSET));
		assertTrue(observerViewPageAction.verifyWindRoseIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyConcentrationChartIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));

		// CHECK: Lisa not appearing on the map.
		// assertTrue(observerViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		observerViewPageAction.clickOnZoomInButton(EMPTY, NOTSET);
		assertTrue(observerViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(observerViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC347_ViewOperatorSurveySatelliteViewWhenGISDataLoaded Script: - - - On Home Page, click on Picarro Surveyors -& Online Display: All on, Position auto-center: On, Map View:
	 * Satellite, GIS: All on Results: - - User can see car position, wind rose, concentration chart, breadcrumb, FOV, Indications, LISA, Isotopic Analysis results (if any) and Field Notes (if any) in
	 * satellite View - All pipes data and boundaries are displayed
	 */
	@Ignore
	public void TC347_ViewOperatorSurveySatelliteViewWhenGISDataLoaded() throws Exception {
		Log.info("\nRunning TC347_ViewOperatorSurveySatelliteViewWhenGISDataLoaded ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_OPERATOR_ROW_ID, SURVEY_OPERATOR1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageAction.turnOnPosition(EMPTY, NOTSET);
		observerViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		observerViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		assertTrue(observerViewPageAction.verifyCrossHairIconIsShownOnMap(COLOR_RED, NOTSET));
		assertTrue(observerViewPageAction.verifyWindRoseIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyConcentrationChartIsShownOnMap(EMPTY, NOTSET));

		// TODO: Check: FOV is NOT shown in the survey. Check if time dependent.
		// assertTrue(observerViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(observerViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC348_ViewOperatorSurveyWhenAllDisplayGISOptionsAreTurnedOFF Script: - - - On Home Page, click on Picarro Surveyors -& Online - Display: All OFF, Position auto-center: OFF, Map
	 * View: Map, GIS: All OFF Status: No alarms Results: - - Survey Information is displayed in map view to observer Tag, Mode, Time, Survey Active, Driver Info, Elapsed time, Remaining Time, Zoom
	 * level, Surveyor and analyzer info - Car position, breadcrumb is displayed to the user - Map is not centered on car's position - User is not able to see FOV, Indications, LISA, assets,
	 * boundaries, Field Notes (if any), Capture results (if any), Concentration Chart and Windrose
	 */
	@Ignore
	public void TC348_ViewOperatorSurveyWhenAllDisplayGISOptionsAreTurnedOFF() throws Exception {
		Log.info("\nRunning TC348_ViewOperatorSurveyWhenAllDisplayGISOptionsAreTurnedOFF ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_OPERATOR_ROW_ID, SURVEY_OPERATOR1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);

		observerViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnMapView(EMPTY, NOTSET);
		observerViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		observerViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);

		observerViewPageAction.clickOnHeaderInfoBox(EMPTY, NOTSET);
		assertTrue(observerViewPageAction.verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.surveyTag, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType, NOTSET));

		// TODO: CHECK: Driver label is showing EMPTY
		// assertTrue(observerViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeLabelStartsWith(SURVEY_INFO_TIME_PREFIX + String.valueOf(getHourOfDay()), NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeElapsedLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeRemainingLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoZoomLevelLabelEquals(SURVEY_INFO_ZOOM_LEVEL_19, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeElapsedIsTickingForward(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeRemainingLabelIsTickingBackward(EMPTY, NOTSET));

		assertTrue(observerViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_SURVEY_STATUS_ACTIVE, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR_ANALYZER_FOR_TEST, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));

		assertTrue(observerViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyCrossHairIconIsShownOnMap(COLOR_RED, NOTSET));
		assertTrue(observerViewPageAction.verifyCarIconIsNotInCenter(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(observerViewPageAction.verifyWindRoseIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyConcentrationChartIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC349_ViewStandardSurveyWhenAllBoundariesPipeTypesAreLoaded Script: - - - On Home Page, click on Picarro Surveyors -& Online - Display: All on, Position auto-center: On, Map View:
	 * Map, GIS: All pipe types and boundaries on Results: - - User can see car position, wind rose, concentration chart, breadcrumb, FOV, Indications, LISA, Isotopic Analysis results (if any) and
	 * Field Notes (if any) - All pipes data and boundaries are displayed
	 */
	@Ignore
	public void TC349_ViewStandardSurveyWhenAllBoundariesPipeTypesAreLoaded() throws Exception {
		Log.info("\nRunning TC349_ViewStandardSurveyWhenAllBoundariesPipeTypesAreLoaded ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.turnOnPosition(EMPTY, NOTSET);
		observerViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);

		observerViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnMapView(EMPTY, NOTSET);
		observerViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		assertTrue(observerViewPageAction.verifyCrossHairIconIsShownOnMap(COLOR_RED, NOTSET));
		assertTrue(observerViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyWindRoseIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyConcentrationChartIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC350_ViewStandardSurveySatelliteViewWhenAllMapBoundariesPipeTypeAreLoaded Script: - - - On Home Page, click on Picarro Surveyors -& Online - Display: All on, Position
	 * auto-center: On, Map View: Satellite, GIS: All ON - User can see car position, wind rose, concentration chart, breadcrumb, FOV, Indications, LISA, Isotopic Analysis results (if any) and Field
	 * Notes (if any) in satellite view Results: - - All pipes data and boundaries are displayed
	 */
	@Ignore
	public void TC350_ViewStandardSurveySatelliteViewWhenAllMapBoundariesPipeTypeAreLoaded() throws Exception {
		Log.info("\nRunning TC350_ViewStandardSurveySatelliteViewWhenAllMapBoundariesPipeTypeAreLoaded ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.turnOnPosition(EMPTY, NOTSET);
		observerViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);

		observerViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		observerViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		assertTrue(observerViewPageAction.verifyCrossHairIconIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyWindRoseIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyConcentrationChartIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC351_ViewStandardSurveyWhenAllDisplayOptionsGISAreTurnedOFF Script: - - - On Home Page, click on Picarro Surveyors -& Online - Display: All OFF, Position auto-center: OFF, Map
	 * View: Map, GIS: All OFF Status: No alarms - Survey Information is displayed in map view to observer Tag, Mode, Time, Survey Active, Driver Info, Elapsed time, Remaining Time, Zoom level,
	 * Surveyor and analyzer info - Car position, breadcrumb is displayed to the user - Map is not centered on car's position Results: - - User is not able to see FOV, Indications, LISA, assets,
	 * boundaries, Field Notes (if any), Isotopic Analysis results (if any), Concentration Chart and Windrose
	 */
	@Ignore
	public void TC351_ViewStandardSurveyWhenAllDisplayOptionsGISAreTurnedOFF() throws Exception {
		Log.info("\nRunning TC351_ViewStandardSurveyWhenAllDisplayOptionsGISAreTurnedOFF ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.turnOffPosition(EMPTY, NOTSET);
		observerViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);

		observerViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnMapView(EMPTY, NOTSET);
		observerViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		observerViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);

		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);

		assertTrue(observerViewPageAction.verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.surveyTag, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeLabelStartsWith(SURVEY_INFO_TIME_PREFIX + String.valueOf(getHourOfDay()), NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeElapsedLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeRemainingLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoZoomLevelLabelEquals(SURVEY_INFO_ZOOM_LEVEL_19, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeElapsedIsTickingForward(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeRemainingLabelIsTickingBackward(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_SURVEY_STATUS_ACTIVE, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR3_ANALYZER3, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));

		assertTrue(observerViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyCrossHairIconIsShownOnMap(COLOR_RED, NOTSET));
		assertTrue(observerViewPageAction.verifyCarIconIsNotInCenter(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(observerViewPageAction.verifyWindRoseIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyConcentrationChartIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC352_ChangesDoneByObserverObserveViewAreNotReflectedDriverView Script: - - - On Home Page, click on Picarro Surveyors -& Online - - Turn OFF few display, Turn ON all assets and
	 * boundaries Results: - - Driver View should not be affected.
	 */
	@Test
	public void TC352_ChangesDoneByObserverObserveViewAreNotReflectedDriverView() throws Exception {
		Log.info("\nRunning TC352_ChangesDoneByObserverObserveViewAreNotReflectedDriverView ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.turnOffAllAssets(EMPTY, NOTSET);
		observerViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);

		driverViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET);
		driverViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET);

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC356_ViewOperatorSurveyFOVIndicationsLisasWhenBoundariesLoaded Script: - - - On Home Page, click on Picarro Surveyors -& Online - Display: Wind rose ON - Concentration chart ON -
	 * Isotopic Analysis OFF - Field Notes OFF - Indications ON - LISAs ON - FOV ON, - Position auto-center: On, - Map View: Map - GIS: Boundaries All ON Results: - - Survey in-progress and Survey
	 * Information is displayed in map view - Tag, Mode, Time, Survey Active, Driver Info, Elapsed time, Remaining Time, Zoom level, Surveyor and analyzer info - Car position, wind rose, concentration
	 * chart, breadcrumb, Indications, Lisa, FoV is displayed on map along with boundaries data - Field Notes and Isotopic Analysis results (if any) are not displayed on observer view - Assets are not
	 * displayed
	 */
	@Ignore
	public void TC356_ViewOperatorSurveyFOVIndicationsLisasWhenBoundariesLoaded() throws Exception {
		Log.info("\nRunning TC356_ViewOperatorSurveyFOVIndicationsLisasWhenBoundariesLoaded ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_OPERATOR_ROW_ID, SURVEY_OPERATOR1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.turnOnWindRose(EMPTY, NOTSET);
		observerViewPageAction.turnOnConcentrationChart(EMPTY, NOTSET);
		observerViewPageAction.turnOffIsotopicAnalysis(EMPTY, NOTSET);
		observerViewPageAction.turnOffNotes(EMPTY, NOTSET);
		observerViewPageAction.turnOnIndications(EMPTY, NOTSET);
		observerViewPageAction.turnOnLisas(EMPTY, NOTSET);
		observerViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		observerViewPageAction.turnOnPosition(EMPTY, NOTSET);
		observerViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnMapView(EMPTY, NOTSET);
		observerViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnAllBoundaries(EMPTY, NOTSET);

		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);

		assertTrue(observerViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(observerViewPageAction.verifyCarIconIsNotInCenter(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyConcentrationChartIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyWindRoseIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));

		assertTrue(observerViewPageAction.verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.surveyTag, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeLabelStartsWith(SURVEY_INFO_TIME_PREFIX + String.valueOf(getHourOfDay()), NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeElapsedLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeRemainingLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoZoomLevelLabelEquals(SURVEY_INFO_ZOOM_LEVEL_19, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeElapsedIsTickingForward(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeRemainingLabelIsTickingBackward(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_SURVEY_STATUS_ACTIVE, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR3_ANALYZER3, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));

		assertTrue(observerViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC357_ViewFOVIndicationsLisasDataStandardSurveyWhenBoundariesLoaded Script: - - - On Home Page, click on Picarro Surveyors -& Online - Display: Wind rose ON - Concentration chart
	 * ON - Isotopic Analysis OFF - Field Notes OFF - Indications ON - LISAs ON - FOV ON, - Position auto-center: On, - Map View: Map - GIS: Boundaries All ON Results: - - Survey in-progress and
	 * Survey Information is displayed in map view - Tag, Mode, Time, Survey Active, Driver Info, Elapsed time, Remaining Time, Zoom level, Surveyor and analyzer info - Car position, wind rose,
	 * concentration chart, breadcrumb, Indications, Lisa, FoV is displayed on map along with boundaries data - Field Notes and Isotopic Analysis results (if any) are not displayed on observer view -
	 * Assets data is not displayed on map
	 */
	@Ignore
	public void TC357_ViewFOVIndicationsLisasDataStandardSurveyWhenBoundariesLoaded() throws Exception {
		Log.info("\nRunning TC357_ViewFOVIndicationsLisasDataStandardSurveyWhenBoundariesLoaded ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.turnOnWindRose(EMPTY, NOTSET);
		observerViewPageAction.turnOnConcentrationChart(EMPTY, NOTSET);
		observerViewPageAction.turnOffIsotopicAnalysis(EMPTY, NOTSET);
		observerViewPageAction.turnOffNotes(EMPTY, NOTSET);
		observerViewPageAction.turnOnIndications(EMPTY, NOTSET);
		observerViewPageAction.turnOnLisas(EMPTY, NOTSET);
		observerViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		observerViewPageAction.turnOnPosition(EMPTY, NOTSET);
		observerViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnMapView(EMPTY, NOTSET);
		observerViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnAllBoundaries(EMPTY, NOTSET);

		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);

		assertTrue(observerViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyCarIconIsNotInCenter(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyConcentrationChartIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyWindRoseIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));

		assertTrue(observerViewPageAction.verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.surveyTag, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeLabelStartsWith(SURVEY_INFO_TIME_PREFIX + String.valueOf(getHourOfDay()), NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeElapsedLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeRemainingLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoZoomLevelLabelEquals(SURVEY_INFO_ZOOM_LEVEL_19, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeElapsedIsTickingForward(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeRemainingLabelIsTickingBackward(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_SURVEY_STATUS_ACTIVE, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR3_ANALYZER3, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));

		assertTrue(observerViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(observerViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC1037_ObserverView_OnceUserStartsNewSurveyOldSurveyShouldNotDisplayedObserverView Script: - - - On Home Page, click on Picarro Surveyors -& Online - Ask driver user to stop the
	 * survey - - Now ask driver user to start the survey again with different survey tag value (eg. tag - Survey 2) Results: - - Observer user should only see recent survey data (eg. Survey 2) - User
	 * should not see first survey (eg. Survey 1)
	 */
	@Ignore
	// TODO: Correct implementation.
	public void TC1037_ObserverView_OnceUserStartsNewSurveyOldSurveyShouldNotDisplayedObserverView() throws Exception {
		Log.info("\nRunning TC1037_ObserverView_OnceUserStartsNewSurveyOldSurveyShouldNotDisplayedObserverView ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		// Start 1st survey.
		startDrivingSurvey(ANALYZER3_REPLAY_ROW_ID, SURVEY_OPERATOR1_ROW_ID, ONE_SECOND * 15);
		driverViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		driverViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		testEnvironmentAction.idleForSeconds(String.valueOf(10), NOTSET);
		driverViewPageAction.clickOnFirstIndicationShownOnMap(EMPTY, NOTSET);
		driverViewPageAction.enterFieldNotes(SAMPLE_FIELD_NOTES1, NOTSET);
		stopSurvey();

		// Start 2st survey with a different tag.
		startDrivingSurvey(ANALYZER3_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		// Verify 1st survey field note is NOT shown.
		// Verify 2nd survey indication is shown.
		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET);
		observerViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET);
		observerViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET);
		observerViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET);

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC1262_ObserverView_ReturningFromCurtainViewShouldRestoreUserSelectedMapViewCombination Script: - - Log in to application and navigate to observer view - - Select Map view - -
	 * Click on Curtain View - - Click on return Results: - - Map view should be displayed
	 */
	@Test
	public void TC1262_ObserverView_ReturningFromCurtainViewShouldRestoreUserSelectedMapViewCombination() throws Exception {
		Log.info("\nRunning TC1262_ObserverView_ReturningFromCurtainViewShouldRestoreUserSelectedMapViewCombination ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnMapView(EMPTY, NOTSET);
		observerViewPageAction.showCurtainView(EMPTY, NOTSET);
		observerViewPageAction.clickOnCurtainReturnButton(EMPTY, NOTSET);
		assertTrue(observerViewPageAction.verifyMapViewIsShown(EMPTY, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC1273_ObserverViewTimeShouldChangePerTimezoneSelected Script: - - Log in to SQA - - Navigate to observer view - Suppose user system time is in PDT. Change it to CDT Results: - -
	 * Time and timezone text (PDT/CDT) should be changed
	 */
	@Ignore
	// TODO: Time zone setting (make action driven)
	public void TC1273_ObserverViewTimeShouldChangePerTimezoneSelected() throws Exception {
		Log.info("\nRunning TC1273_ObserverViewTimeShouldChangePerTimezoneSelected ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		observerViewPageAction.open(EMPTY, NOTSET);

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC1302_ObserverConnectingDialogShouldPopUpWhenConnectingCarNotServer Script: - - With the analyzer warmed up, log into the tablet - Click on the Mode -> Start Survey button - Fill
	 * out the necessary details on the survey conditions pop-up window and click Start Survey - On a laptop that is not connected to the car's wifi, go to Observer view for this Surveyor vehicle -
	 * Unplug the ethernet cable from the router - Plug the ethernet cable back into the router - Repeat twice more Results: - - Approximately five seconds after the ethernet cable is unplugged, the
	 * Driver View screen should darken and show the message, "Connecting" - Approximately one minute after the cable is unplugged, the Observer View screen should darken and show the message,
	 * "Connecting" - Several seconds after the ethernet cable is plugged back in, the screens on both Driver View and Observer View should return to normal brightness and the Connecting message
	 * should disappear. - Click on the Mode -& Start Survey button
	 */
	@Ignore
	// TODO: Time dependent Connecting... test case.
	public void TC1302_ObserverConnectingDialogShouldPopUpWhenConnectingCarNotServer() throws Exception {
		Log.info("\nRunning TC1302_ObserverConnectingDialogShouldPopUpWhenConnectingCarNotServer ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		observerViewPageAction.open(EMPTY, NOTSET);

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC1375_ObserverView_CurtainView_ChangeInvocationOfOLCesium Script: - - 1) Log into Observer View - 2) While the survey is running, click the Curtain button - 3) Click on the
	 * Return button and refresh the page - 4) Let the survey continue for a few more minutes - 5) Click on the Curtain button Results: - 1) When the Curtain button is clicked, "Connecting" screen
	 * will appear, followed by car icon. Background map/satellite will gradually appear, along with blue wall (curtain) along the vehicle's route from the beginning of the survey 2) The second time
	 * the Curtain button is clicked, the blue wall will appear along the route from the beginning of the survey, not just the route from the point where the page was refreshed
	 */
	@Ignore
	// TODO: Curtain view.
	public void TC1375_ObserverView_CurtainView_ChangeInvocationOfOLCesium() throws Exception {
		Log.info("\nRunning TC1375_ObserverView_CurtainView_ChangeInvocationOfOLCesium ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.showCurtainView(EMPTY, NOTSET);
		observerViewPageAction.clickOnCurtainReturnButton(EMPTY, NOTSET);
		observerViewPageAction.refreshPage(EMPTY, NOTSET);
		observerViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		testEnvironmentAction.idleForSeconds(String.valueOf(60), NOTSET);
		observerViewPageAction.showCurtainView(EMPTY, NOTSET);

		// TODO: Verifications for curtain view currently not present.
	}

	/**
	 * Test Case ID: TC1411_ObserverView_AssessmentSurvey_CurtainViewDisplayedUserAbleFollowVehicle Script: - - - On Home Page, click on Picarro Surveyors -& Online -& Curtain - - Click on Return. - -
	 * Turn Position OFF and click on Curtain - - Click on Return Results: - - Red color cursor will move along with car position and blue spikes are displayed - Cursor will stop moving but blue
	 * spikes will be displayed - User will be returned to Observer View page
	 */
	@Test
	public void TC1411_ObserverView_AssessmentSurvey_CurtainViewDisplayedUserAbleFollowVehicle() throws Exception {
		Log.info("\nRunning TC1411_ObserverView_AssessmentSurvey_CurtainViewDisplayedUserAbleFollowVehicle ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ASSESSMENT_ROW_ID, SURVEY_ASSESSMENT1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.showCurtainView(EMPTY, NOTSET);
		observerViewPageAction.clickOnCurtainReturnButton(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		assertTrue(observerViewPageAction.verifyObserverViewPageIsOpened(EMPTY, NOTSET));
		observerViewPageAction.turnOffPosition(EMPTY, NOTSET);
		observerViewPageAction.showCurtainView(EMPTY, NOTSET);
		observerViewPageAction.clickOnCurtainReturnButton(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		assertTrue(observerViewPageAction.verifyObserverViewPageIsOpened(EMPTY, NOTSET));

		// TODO: Verify breadcrumb.
		// assertTrue(observerViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		// TODO: Car icon check & breadcrumb color detection is TODO
		// assertTrue(observerViewPageAction.verifyCrossHairIconIsShownOnMap(COLOR_RED, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC1412_ObserverView_AssessmentModeSurvey_UserAllowedMoveUpDownLeftRightZoomInZoomOutCurtainView Script: - - - On Home Page, click on Picarro Surveyors -& Online -& Curtain - -
	 * Click Up - - Click Right - - Click Zoom In - - Click Zoom Out Results: - - - Click Return
	 */
	@Ignore
	// TODO: Need a method to verify that the map image changes on Direction and Zoom clicks.
	public void TC1412_ObserverView_AssessmentModeSurvey_UserAllowedMoveUpDownLeftRightZoomInZoomOutCurtainView() throws Exception {
		Log.info("\nRunning TC1412_ObserverView_AssessmentModeSurvey_UserAllowedMoveUpDownLeftRightZoomInZoomOutCurtainView ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ASSESSMENT_ROW_ID, SURVEY_ASSESSMENT1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.clickOnCurtainArrowUpButton(EMPTY, NOTSET);
		// TODO:
		// observerViewPageAction.verifyMapImageHasChanged(EMPTY, NOTSET);
		observerViewPageAction.clickOnCurtainArrowRightButton(EMPTY, NOTSET);
		// TODO:
		// observerViewPageAction.verifyMapImageHasChanged(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomInButton(EMPTY, NOTSET);
		// TODO:
		// observerViewPageAction.verifyMapImageHasChanged(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		// TODO:
		// observerViewPageAction.verifyMapImageHasChanged(EMPTY, NOTSET);
		observerViewPageAction.clickOnCurtainReturnButton(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		assertTrue(observerViewPageAction.verifyObserverViewPageIsOpened(EMPTY, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC1413_ObserverView_AssessmentModeSurvey_ReturningFromCurtainViewShouldRestoreUserSelectedMapViewCombination Script: - - Log in to application and navigate to observer view - -
	 * Select Map view - - Click on Curtain View - - Click on return Results: - - Map view should be displayed
	 */
	@Ignore
	public void TC1413_ObserverView_AssessmentModeSurvey_ReturningFromCurtainViewShouldRestoreUserSelectedMapViewCombination() throws Exception {
		Log.info("\nRunning TC1413_ObserverView_AssessmentModeSurvey_ReturningFromCurtainViewShouldRestoreUserSelectedMapViewCombination ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ASSESSMENT_ROW_ID, SURVEY_ASSESSMENT1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnMapView(EMPTY, NOTSET);
		observerViewPageAction.showCurtainView(EMPTY, NOTSET);
		observerViewPageAction.clickOnCurtainReturnButton(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		assertTrue(observerViewPageAction.verifyMapViewIsShown(EMPTY, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC1453_ObserverView_CheckAssessmentSurveyDataPresentAfterObserverRefreshesBrowser_Satellite Script: - - - On Home Page, click on Picarro Surveyors -& Online - - Click on Map and
	 * turn Satellite View ON - - On the Display menu, turn Concentration Chart and LISAs OFF. Leave other options ON - - On the GIS menu, turn all Material Types ON, leave Boundaries OFF - - Refresh
	 * the browser after couple of mins Results: - - After refreshing, survey data should be present - Survey details Tag, Mode, Current Time, Survey Active, Driver Name, Stability Class, Elapsed
	 * Time, Remaining Time, Surveyor Name and Zoom Level should be present - None of the values should be missing. Elapsed and Remaining Time should not reset after the refresh. All menu settings
	 * should persist
	 */
	@Ignore
	public void TC1453_ObserverView_CheckAssessmentSurveyDataPresentAfterObserverRefreshesBrowser_Satellite() throws Exception {
		Log.info("\nRunning TC1453_ObserverView_CheckAssessmentSurveyDataPresentAfterObserverRefreshesBrowser_Satellite ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ASSESSMENT_ROW_ID, SURVEY_ASSESSMENT1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		observerViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);

		observerViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageAction.turnOffConcentrationChart(EMPTY, NOTSET);
		observerViewPageAction.turnOffLisas(EMPTY, NOTSET);
		observerViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnAllAssets(EMPTY, NOTSET);
		observerViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);
		testEnvironmentAction.idleForSeconds(String.valueOf(60), NOTSET);
		observerViewPageAction.refreshPage(EMPTY, NOTSET);
		observerViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		assertTrue(observerViewPageAction.verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.surveyTag, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeLabelStartsWith(SURVEY_INFO_TIME_PREFIX + String.valueOf(getHourOfDay()), NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeElapsedLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeRemainingLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoZoomLevelLabelEquals(SURVEY_INFO_ZOOM_LEVEL_19, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeElapsedIsTickingForward(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeRemainingLabelIsTickingBackward(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER1, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR1, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC1454_ObserverView_CheckAssessmentSurveyDataPresentAfterObserverRefreshesBrowser_Map Script: - - - On Home Page, click on Picarro Surveyors -& Online - - Click on Map and turn
	 * Map View ON - - On the Display menu, leave Concentration Chart Turn other options OFF - - On the GIS menu, leave all Material Types OFF, turn Boundaries ON - - Refresh the browser after couple
	 * of mins Results: - - After refreshing, survey data should be present - Survey details - Tag, Mode, Current Time, Survey Active, Driver Name, Stability Class, Elapsed Time, Remaining Time,
	 * Surveyor Name and Zoom Level should be present - None of the values should be missing. Elapsed and Remaining Time should not reset after the refresh. All menu settings should persist
	 */
	@Ignore
	public void TC1454_ObserverView_CheckAssessmentSurveyDataPresentAfterObserverRefreshesBrowser_Map() throws Exception {
		Log.info("\nRunning TC1454_ObserverView_CheckAssessmentSurveyDataPresentAfterObserverRefreshesBrowser_Map ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ASSESSMENT_ROW_ID, SURVEY_ASSESSMENT1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnMapView(EMPTY, NOTSET);
		observerViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);

		observerViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageAction.turnOnConcentrationChart(EMPTY, NOTSET);
		observerViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		observerViewPageAction.turnOffAllAssets(EMPTY, NOTSET);
		observerViewPageAction.turnOnAllBoundaries(EMPTY, NOTSET);
		testEnvironmentAction.idleForSeconds(String.valueOf(10), NOTSET);
		observerViewPageAction.refreshPage(EMPTY, NOTSET);
		observerViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);

		assertTrue(observerViewPageAction.verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.surveyTag, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeLabelStartsWith(SURVEY_INFO_TIME_PREFIX + String.valueOf(getHourOfDay()), NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeElapsedLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeRemainingLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoZoomLevelLabelEquals(SURVEY_INFO_ZOOM_LEVEL_19, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeElapsedIsTickingForward(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeRemainingLabelIsTickingBackward(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER1, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR1, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC1457_ViewAssessmentSurveyObserverViewWhenEverythingLoaded Script: - - - On Home Page, click on Picarro Surveyors -& Online - Display: All ON - Position auto-center: ON - Map
	 * View: Map - GIS: All ON Results: - - User can see car position, wind rose, concentration chart, breadcrumb, FOV - All pipes data and boundaries are displayed
	 */
	@Ignore
	public void TC1457_ViewAssessmentSurveyObserverViewWhenEverythingLoaded() throws Exception {
		Log.info("\nRunning TC1457_ViewAssessmentSurveyObserverViewWhenEverythingLoaded ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ASSESSMENT_ROW_ID, SURVEY_ASSESSMENT1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);

		observerViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageAction.turnOnPosition(EMPTY, NOTSET);
		observerViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnMapView(EMPTY, NOTSET);
		observerViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(observerViewPageAction.verifyCrossHairIconIsShownOnMap(COLOR_RED, NOTSET));
		assertTrue(observerViewPageAction.verifyWindRoseIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyConcentrationChartIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC1458_ViewAssessmentSurveySatelliteViewWhenGISDataLoaded Script: - - - On Home Page, click on Picarro Surveyors -& Online - Display: All on, Position auto-center: On, - Map View:
	 * Satellite, - GIS: All on Results: - - User can see car position, wind rose, concentration chart, breadcrumb, FOV in satellite View - All pipes data and boundaries are displayed
	 */
	@Ignore
	public void TC1458_ViewAssessmentSurveySatelliteViewWhenGISDataLoaded() throws Exception {
		Log.info("\nRunning TC1458_ViewAssessmentSurveySatelliteViewWhenGISDataLoaded ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ASSESSMENT_ROW_ID, SURVEY_ASSESSMENT1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageAction.turnOnPosition(EMPTY, NOTSET);
		observerViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		observerViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(observerViewPageAction.verifyCrossHairIconIsShownOnMap(COLOR_RED, NOTSET));
		assertTrue(observerViewPageAction.verifyWindRoseIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyConcentrationChartIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC1459_ViewAssessmentModeSurveyWhenAllDisplayGISOptionsAreTurnedOFF Script: - - - On Home Page, click on Picarro Surveyors -& Online - Display: All OFF, - Position auto-center:
	 * OFF, - Map View: Map, - GIS: All OFF - Status: No alarms Results: - - Survey Information is displayed in map view to observer Tag, Mode, Time, Survey Active, Driver Info, Elapsed time,
	 * Remaining Time, Zoom level, Surveyor and analyzer info - Car position, breadcrumb is displayed to the user - Map is not centered on car's position - User is not able to see FOV, Indications,
	 * LISA, assets, boundaries, Field Notes (if any), Capture results (if any), Concentration Chart and Windrose
	 */
	@Ignore
	public void TC1459_ViewAssessmentModeSurveyWhenAllDisplayGISOptionsAreTurnedOFF() throws Exception {
		Log.info("\nRunning TC1459_ViewAssessmentModeSurveyWhenAllDisplayGISOptionsAreTurnedOFF ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ASSESSMENT_ROW_ID, SURVEY_ASSESSMENT1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.turnOffPosition(EMPTY, NOTSET);
		observerViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		observerViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnMapView(EMPTY, NOTSET);
		observerViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		observerViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(observerViewPageAction.verifyStatusButtonIsGreen(EMPTY, NOTSET));

		assertTrue(observerViewPageAction.verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.surveyTag, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeLabelStartsWith(SURVEY_INFO_TIME_PREFIX + String.valueOf(getHourOfDay()), NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeElapsedLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeRemainingLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoZoomLevelLabelEquals(SURVEY_INFO_ZOOM_LEVEL_19, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeElapsedIsTickingForward(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeRemainingLabelIsTickingBackward(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER1, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR1, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));

		assertTrue(observerViewPageAction.verifyCrossHairIconIsShownOnMap(COLOR_RED, NOTSET));
		assertTrue(observerViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));

		assertTrue(observerViewPageAction.verifyCarIconIsNotInCenter(EMPTY, NOTSET));

		assertTrue(observerViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(observerViewPageAction.verifyWindRoseIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyConcentrationChartIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC1460_ViewAssessmentModeSurveyObserverMapViewWhenFovIndicationWindRoseConcentrationChartON Script: - - - On Home Page, click on Picarro Surveyors -& Online - - Click Display -
	 * Windrose - ON - Concentration Chart - OFF - FoV - ON - - Click GIS - All options ON - - Click on Map and turn Map view ON Results: - - Wind rose, Car position, Breadcrumb and FoV are displayed
	 * on map along with assets and boundaries data - Survey Information is displayed in map view - Tag, Mode, Time, Survey Active, Driver Info, Elapsed time, Remaining Time, Zoom level, Surveyor and
	 * analyzer info
	 */
	@Ignore
	// TODO: Check if the pre-req is correct. Title=Assessment, Pre-req mentions Operator
	public void TC1460_ViewAssessmentModeSurveyObserverMapViewWhenFovIndicationWindRoseConcentrationChartON() throws Exception {
		Log.info("\nRunning TC1460_ViewAssessmentModeSurveyObserverMapViewWhenFovIndicationWindRoseConcentrationChartON ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ASSESSMENT_ROW_ID, SURVEY_ASSESSMENT1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		observerViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageAction.turnOnWindRose(EMPTY, NOTSET);
		observerViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		observerViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		observerViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnMapView(EMPTY, NOTSET);

		assertTrue(observerViewPageAction.verifyWindRoseIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyCrossHairIconIsShownOnMap(COLOR_RED, NOTSET));
		assertTrue(observerViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));

		assertTrue(observerViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

		assertTrue(observerViewPageAction.verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.surveyTag, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeLabelStartsWith(SURVEY_INFO_TIME_PREFIX + String.valueOf(getHourOfDay()), NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeElapsedLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeRemainingLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoZoomLevelLabelEquals(SURVEY_INFO_ZOOM_LEVEL_19, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeElapsedIsTickingForward(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeRemainingLabelIsTickingBackward(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER1, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR1, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC1461_ViewAssessmentModeSurveyObserverMapViewWhenAllDisplayOptionsAreONOtherPlasticProtectedSteelCastIronAssetsONBoundariesOFF Script: - - - On Home Page, click on Picarro
	 * Surveyors -& Online - - Click Display - All options (FOV,Windrose,Concentration chart) - ON - - Click GIS - Assets: Other Plastic, Protected steel, Cast iron - ON - Boundaries: OFF - - Click on
	 * Map and turn Map view ON Results: - - Breadcrumb, car position, Concentration Chart, Wind rose, FOV and survey information is displayed on map - Other Plastic, Protected steel and Cast iron
	 * assets are displayed on map and other assets are not displayed - Boundaries not displayed on map
	 */
	@Ignore
	public void TC1461_ViewAssessmentModeSurveyObserverMapViewWhenAllDisplayOptionsAreONOtherPlasticProtectedSteelCastIronAssetsONBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1461_ViewAssessmentModeSurveyObserverMapViewWhenAllDisplayOptionsAreONOtherPlasticProtectedSteelCastIronAssetsONBoundariesOFF ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ASSESSMENT_ROW_ID, SURVEY_ASSESSMENT1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		observerViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);
		observerViewPageAction.turnOnMaterialTypeOtherPlastic(EMPTY, NOTSET);
		observerViewPageAction.turnOnMaterialTypeProtectedSteel(EMPTY, NOTSET);
		observerViewPageAction.turnOnMaterialTypeCastIron(EMPTY, NOTSET);
		observerViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnMapView(EMPTY, NOTSET);

		assertTrue(observerViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyConcentrationChartIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyWindRoseIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));

		// TODO: Specific asset check (Currently no mechanism to detect from client-side). Might need some server-side product changes.
		assertTrue(observerViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));

		assertTrue(observerViewPageAction.verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.surveyTag, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeLabelStartsWith(SURVEY_INFO_TIME_PREFIX + String.valueOf(getHourOfDay()), NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeElapsedLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeRemainingLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoZoomLevelLabelEquals(SURVEY_INFO_ZOOM_LEVEL_19, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeElapsedIsTickingForward(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoTimeRemainingLabelIsTickingBackward(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER1, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR1, NOTSET));
		assertTrue(observerViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));

		assertTrue(observerViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC1462_ViewAssessmentModeSurveyObserverMapViewWhenFOVONAssetsBoundariesOFFSatelliteView Script: - - - On Home Page, click on Picarro Surveyors -& Online - - Click Display - FOV-
	 * ON - All other options(Windrose, concentration Chart) - OFF - - Click GIS - All options OFF - - Click on Map and turn Satelite view ON - Car position, Breadcrumb and FOV are displayed on map
	 * Results: - - All other survey data, assets and boundaries are not displayed
	 */
	@Ignore
	// TODO: Check if the pre-req is correct. Title=Assessment, Pre-req mentions Operator
	public void TC1462_ViewAssessmentModeSurveyObserverMapViewWhenFOVONAssetsBoundariesOFFSatelliteView() throws Exception {
		Log.info("\nRunning TC1462_ViewAssessmentModeSurveyObserverMapViewWhenFOVONAssetsBoundariesOFFSatelliteView ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ASSESSMENT_ROW_ID, SURVEY_ASSESSMENT1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		observerViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		observerViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		observerViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		observerViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		observerViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);

		assertTrue(observerViewPageAction.verifyCrossHairIconIsShownOnMap(COLOR_RED, NOTSET));
		assertTrue(observerViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));

		assertTrue(observerViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));

		assertTrue(observerViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC142_UserCanViewOnlineSurveys Script: - - User is able to view the online surveyors by clicking on Online link present on Surveyors page Results: - - User is navigated to
	 * Observer view
	 */
	@Test
	public void TC142_UserCanViewOnlineSurveys() throws Exception {
		Log.info("\nRunning TC142_UserCanViewOnlineSurveys ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		homePageAction.clickOnFirstOnlineSurveyorLink(EMPTY, NOTSET);
		observerViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		assertTrue(observerViewPageAction.verifyObserverViewPageIsOpened(EMPTY, NOTSET));
	}
}
