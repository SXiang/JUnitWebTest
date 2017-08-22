package surveyor.regression.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

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
import surveyor.scommon.source.SurveyorTestRunner;

import surveyor.scommon.actions.ObserverViewPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
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

	private DriverViewPageActions driverViewPageAction;
	private ArrayList<ObserverViewPageActions> observerViewPageActionList = new ArrayList<ObserverViewPageActions>();
	private DriverViewPage driverViewPage;
	private ArrayList<ObserverViewPage> observerViewPageList = new ArrayList<ObserverViewPage>();

	public ObserverViewPageTest() throws IOException {
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
			initializePageActionsList();
			initializeBasePageActions();
			initializeObserverViewPageActionList();

			driverViewPageAction = new DriverViewPageActions(getDriver(), getBaseURL(), getTestSetup());
			driverViewPage = new DriverViewPage(getDriver(), getBaseURL(), getTestSetup());
			PageFactory.initElements(getDriver(), driverViewPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	private void startReplay(Integer analyzerRowId) throws Exception {
		startReplay(driverViewPageAction, analyzerRowId);
	}

	private void stopSurveyAndAnalyzer() {
		stopSurveyAndAnalyzer(driverViewPageAction);
	}

	private void stopSurvey() {
		stopSurvey(driverViewPageAction);
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
		getLoginPageAction().open(EMPTY, NOTSET);
		LoginPageActions.workingDataRow.set(null);
		getLoginPageAction().login(EMPTY, userRowID); /* Picarro Admin */
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
		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);

		// ## Verifications
		observerViewPageActionList.get(0).verifyModeButtonIsNotVisible(EMPTY, NOTSET);

		stopSurveyAndAnalyzer();
	}

	/** MandatoryRegression
	 * Test Case ID: TC345_ObserverShouldAbleSeeStatusUpdateWhenVehicleWarmingUp 
	 * Script: 
	 * 	- - On Home Page, click on Picarro Surveyors -& Online -& Status 
	 * 	- - Click on Display button 
	 * 	- - Click on Map button 
	 * 	- - Click on GIS button 
	 * 	- - Click on Status button 
	 * Results: 
	 *  - Analyzer Warming Up message and analyzer info is displayed 
	 * 	- Position button is by default selected and car icon is	displayed 
	 * 	- All the Display options like Indications, LISA, FoV, Concentration Chart, WindRose, Field Notes and Isotopic Analysis are ON 
	 * 	- Satellite View is by default ON and Map view is OFF 
	 * 	- All Asset types and boundaries level are OFF (if customer has assets associated to them) 
	 * 		or no assets and boundaries options are displayed to user 
	 * 	- Status should display same info as in driver view. Status is red and on expanding flow, temp gauges, etc are also red
	 */
	@Test
	public void TC345_ObserverShouldAbleSeeStatusUpdateWhenVehicleWarmingUp() throws Exception {
		Log.info("\nRunning TC345_ObserverShouldAbleSeeStatusUpdateWhenVehicleWarmingUp ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		// Start Analyzer & simulate Instrument Warming
		startReplay(ANALYZER1_INSTRUMENT_WARMING_ROW_ID);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		String analyzer =  getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_SURVEY_STATUS_WARMINGUP, NOTSET));
		
		observerViewPageActionList.get(0).clickOnDisplayButton(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchIndicationsButtonIsVisible("ON", NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchLisasButtonIsVisible("ON", NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchFovsButtonIsVisible("ON", NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchConcentrationChartButtonIsVisible("ON", NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchWindroseButtonIsVisible("ON", NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchNotesButtonIsVisible("ON", NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyDisplaySwitchIsotopicAnalysisButtonIsVisible("ON", NOTSET));

		observerViewPageActionList.get(0).clickOnMapButton(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifySatelliteViewIsShown("ON", NOTSET));

		observerViewPageActionList.get(0).clickOnGisButton(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyGisBoundaryBigBoundaryButtonIsVisible("OFF", NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyGisBoundarySmallBoundaryButtonIsVisible("OFF", NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyGisMaterialTypeCastIronButtonIsVisible("OFF", NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyGisMaterialTypeCopperButtonIsVisible("OFF", NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyGisMaterialTypeOtherPlasticButtonIsVisible("OFF", NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyGisMaterialTypePEPlasticButtonIsVisible("OFF", NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyGisMaterialTypeProtectedSteelButtonIsVisible("OFF", NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyGisMaterialTypeUnProtectedSteelButtonIsVisible("OFF", NOTSET));

		assertTrue(driverViewPageAction.verifyStatusButtonIsRed(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifyPressureButtonIsRed(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifyHBTempButtonIsRed(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifyWBTempButtonIsRed(EMPTY, NOTSET));
		
		assertTrue(observerViewPageActionList.get(0).verifyStatusButtonIsRed(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyPressureButtonIsRed(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyHBTempButtonIsRed(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyWBTempButtonIsRed(EMPTY, NOTSET));

		assertTrue(observerViewPageActionList.get(0).verifyPositionButtonIsSelected(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyCrossHairIconIsShownOnMap("Gray", NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));

		 getTestEnvironmentAction().stopAnalyzer(EMPTY, NOTSET);
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

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnDisplayButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnPosition(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnMapButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnMapView(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnGisButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyCrossHairIconIsShownOnMap(COLOR_RED, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyWindRoseIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyConcentrationChartIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyFOVIsShownOnMap(EMPTY, NOTSET));

		// CHECK: Lisa not appearing on the map.
		// assertTrue(observerViewPageActionList.get(0).verifyLISAIsShownOnMap(EMPTY, NOTSET));
		observerViewPageActionList.get(0).clickOnZoomInButton(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

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

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnDisplayButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnPosition(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnGisButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnMapButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnSatelliteView(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnZoomOutButton(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyCrossHairIconIsShownOnMap(COLOR_RED, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyWindRoseIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyConcentrationChartIsShownOnMap(EMPTY, NOTSET));

		// TODO: Check: FOV is NOT shown in the survey. Check if time dependent.
		// assertTrue(observerViewPageActionList.get(0).verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

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

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnDisplayButton(EMPTY, NOTSET);

		observerViewPageActionList.get(0).turnOffAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnMapButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnMapView(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnGisButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);

		observerViewPageActionList.get(0).clickOnHeaderInfoBox(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.get().surveyTag, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.get().surveyType, NOTSET));

		// TODO: CHECK: Driver label is showing EMPTY
		// assertTrue(observerViewPageActionList.get(0).verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeLabelStartsWith(SURVEY_INFO_TIME_PREFIX + String.valueOf(getHourOfDay()), NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeElapsedLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeRemainingLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoZoomLevelLabelEquals(String.format(SURVEY_INFO_ZOOM_LEVEL_X, 19), NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeElapsedIsTickingForward(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeRemainingLabelIsTickingBackward(EMPTY, NOTSET));

		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_SURVEY_STATUS_ACTIVE, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR_ANALYZER_FOR_TEST, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));

		assertTrue(observerViewPageActionList.get(0).verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyCrossHairIconIsShownOnMap(COLOR_RED, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyCarIconIsNotInCenter(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyWindRoseIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyConcentrationChartIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));

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

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnPosition(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnDisplayButton(EMPTY, NOTSET);

		observerViewPageActionList.get(0).turnOnAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnMapButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnMapView(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnGisButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnZoomOutButton(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyCrossHairIconIsShownOnMap(COLOR_RED, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyWindRoseIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyConcentrationChartIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/** MandatoryRegression
	 * Test Case ID: TC350_ViewStandardSurveySatelliteViewWhenAllMapBoundariesPipeTypeAreLoaded Script: - - - On Home Page, click on Picarro Surveyors -& Online - Display: All on, Position
	 * auto-center: On, Map View: Satellite, GIS: All ON - User can see car position, wind rose, concentration chart, breadcrumb, FOV, Indications, LISA, Isotopic Analysis results (if any) and Field
	 * Notes (if any) in satellite view Results: - - All pipes data and boundaries are displayed
	 */
	@Test
	public void TC350_ViewStandardSurveySatelliteViewWhenAllMapBoundariesPipeTypeAreLoaded() throws Exception {
		Log.info("\nRunning TC350_ViewStandardSurveySatelliteViewWhenAllMapBoundariesPipeTypeAreLoaded ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnPosition(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnDisplayButton(EMPTY, NOTSET);

		observerViewPageActionList.get(0).turnOnAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnMapButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnSatelliteView(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnGisButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnGisButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).getBaseMapViewPageObject().setZoomLevelForAssets();

		assertTrue(observerViewPageActionList.get(0).verifyCrossHairIconIsShownOnMap("Red", NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyWindRoseIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyConcentrationChartIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/** MandatoryRegression
	 * Test Case ID: TC351_ViewStandardSurveyWhenAllDisplayOptionsGISAreTurnedOFF Script: - - - On Home Page, click on Picarro Surveyors -& Online - Display: All OFF, Position auto-center: OFF, Map
	 * View: Map, GIS: All OFF Status: No alarms - Survey Information is displayed in map view to observer Tag, Mode, Time, Survey Active, Driver Info, Elapsed time, Remaining Time, Zoom level,
	 * Surveyor and analyzer info - Car position, breadcrumb is displayed to the user - Map is not centered on car's position Results: - - User is not able to see FOV, Indications, LISA, assets,
	 * boundaries, Field Notes (if any), Isotopic Analysis results (if any), Concentration Chart and Windrose
	 */
	@Test
	public void TC351_ViewStandardSurveyWhenAllDisplayOptionsGISAreTurnedOFF() throws Exception {
		Log.info("\nRunning TC351_ViewStandardSurveyWhenAllDisplayOptionsGISAreTurnedOFF ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);
        String driver = LoginPageActions.workingDataRow.get().username;
        int zoomLevel = 17;
		startDrivingSurvey(ANALYZER3_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();

		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffPosition(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnDisplayButton(EMPTY, NOTSET);

		observerViewPageActionList.get(0).turnOffAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnMapButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnMapView(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnGisButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnGisButton(EMPTY, NOTSET);

		observerViewPageActionList.get(0).getBaseMapViewPageObject().setZoomLevel(zoomLevel+1);
		observerViewPageActionList.get(0).clickOnZoomOutButton(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.get().surveyTag, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.get().surveyType, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + driver, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeLabelStartsWith(SURVEY_INFO_TIME_PREFIX + String.valueOf(getHourOfDay()), NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeElapsedLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeRemainingLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoZoomLevelLabelEquals(String.format(SURVEY_INFO_ZOOM_LEVEL_X, zoomLevel), NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeElapsedIsTickingForward(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeRemainingLabelIsTickingBackward(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_SURVEY_STATUS_ACTIVE, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR3_ANALYZER3, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_C, NOTSET));

		assertTrue(observerViewPageActionList.get(0).verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyCrossHairIconIsShownOnMap(COLOR_RED, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyCarIconIsNotInCenter(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyWindRoseIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyConcentrationChartIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));

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

		String analyzer =  getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffAllAssets(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffAllBoundaries(EMPTY, NOTSET);

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

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnWindRose(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnConcentrationChart(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffIsotopicAnalysis(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffNotes(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnIndications(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnLisas(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnFOVs(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnPosition(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnMapButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnMapView(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnGisButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnAllBoundaries(EMPTY, NOTSET);

		observerViewPageActionList.get(0).clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnZoomOutButton(EMPTY, NOTSET);

		assertTrue(observerViewPageActionList.get(0).verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyCarIconIsNotInCenter(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyConcentrationChartIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyWindRoseIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyFOVIsShownOnMap(EMPTY, NOTSET));

		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.get().surveyTag, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.get().surveyType, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.get().username, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeLabelStartsWith(SURVEY_INFO_TIME_PREFIX + String.valueOf(getHourOfDay()), NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeElapsedLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeRemainingLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoZoomLevelLabelEquals(String.format(SURVEY_INFO_ZOOM_LEVEL_X, 19), NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeElapsedIsTickingForward(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeRemainingLabelIsTickingBackward(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_SURVEY_STATUS_ACTIVE, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR3_ANALYZER3, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));

		assertTrue(observerViewPageActionList.get(0).verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

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

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).turnOnWindRose(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnConcentrationChart(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffIsotopicAnalysis(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffNotes(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnIndications(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnLisas(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnFOVs(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnPosition(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnMapButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnMapView(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnGisButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnAllBoundaries(EMPTY, NOTSET);

		observerViewPageActionList.get(0).clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnZoomOutButton(EMPTY, NOTSET);

		assertTrue(observerViewPageActionList.get(0).verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyCarIconIsNotInCenter(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyConcentrationChartIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyWindRoseIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyFOVIsShownOnMap(EMPTY, NOTSET));

		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.get().surveyTag, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.get().surveyType, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.get().username, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeLabelStartsWith(SURVEY_INFO_TIME_PREFIX + String.valueOf(getHourOfDay()), NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeElapsedLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeRemainingLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoZoomLevelLabelEquals(String.format(SURVEY_INFO_ZOOM_LEVEL_X, 19), NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeElapsedIsTickingForward(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeRemainingLabelIsTickingBackward(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_SURVEY_STATUS_ACTIVE, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR3_ANALYZER3, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));

		assertTrue(observerViewPageActionList.get(0).verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

		stopSurveyAndAnalyzer();
	}

	/** MandatoryRegression
	 * Test Case ID: TC1037_ObserverView_OnceUserStartsNewSurveyOldSurveyShouldNotDisplayedObserverView Script: - - - On Home Page, click on Picarro Surveyors -& Online - Ask driver user to stop the
	 * survey - - Now ask driver user to start the survey again with different survey tag value (eg. tag - Survey 2) Results: - - Observer user should only see recent survey data (eg. Survey 2) - User
	 * should not see first survey (eg. Survey 1)
	 */
	@Test
	public void TC1037_ObserverView_OnceUserStartsNewSurveyOldSurveyShouldNotDisplayedObserverView() throws Exception {
		Log.info("\nRunning TC1037_ObserverView_OnceUserStartsNewSurveyOldSurveyShouldNotDisplayedObserverView ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		// Start 1st survey.
		startDrivingSurvey(ANALYZER3_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND * 3);
		driverViewPageAction.getBaseDrivingViewPage().setZoomLevel(15);
		 getTestEnvironmentAction().idleForSeconds(String.valueOf(15), NOTSET);
		 getTestEnvironmentAction().stopReplay(EMPTY, NOTSET);
		driverViewPageAction.clickOnFirstIndicationShownOnMap(EMPTY, NOTSET);
		driverViewPageAction.clickOnFeatureInfoAddUpdateNote(EMPTY, NOTSET);
		driverViewPageAction.enterFieldNotes(SAMPLE_FIELD_NOTES1, NOTSET);
		stopSurvey();

		// Start 2st survey with a different tag.
		TestEnvironmentActions.workingDataRow.set(null);
		startDrivingSurvey(ANALYZER3_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND * 3);
		 getTestEnvironmentAction().idleForSeconds(String.valueOf(15), NOTSET);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);
		// Verify 1st survey field note is NOT shown.
		// Verify 2nd survey indication is shown.
		String analyzer =  getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).getBaseDrivingViewPage().setZoomLevel(15);
		observerViewPageActionList.get(0).verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET);
		observerViewPageActionList.get(0).verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET);
		observerViewPageActionList.get(0).verifyIndicationsIsShownOnMap(EMPTY, NOTSET);
		observerViewPageActionList.get(0).verifyLISAIsShownOnMap(EMPTY, NOTSET);

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

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnMapButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnMapView(EMPTY, NOTSET);
		observerViewPageActionList.get(0).showCurtainView(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnCurtainReturnButton(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyMapViewIsShown(EMPTY, NOTSET));

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

		observerViewPageActionList.get(0).open(EMPTY, NOTSET);

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC1302_ObserverConnectingDialogShouldPopUpWhenConnectingCarNotServer Script: - - With the analyzer warmed up, log into the tablet - Click on the Mode -> Start Survey button - Fill
	 * out the necessary details on the survey conditions pop-up window and click Start Survey - On a laptop that is not connected to the car's wifi, go to Observer view for this Surveyor vehicle -
	 * Unplug the ethernet cable from the router - Plug the ethernet cable back into the router - Repeat twice more Results: - - Approximately five seconds after the ethernet cable is unplugged, the
	 * Driver View screen should darken and show the message, Connecting - Approximately one minute after the cable is unplugged, the Observer View screen should darken and show the message,
	 * Connecting - Several seconds after the ethernet cable is plugged back in, the screens on both Driver View and Observer View should return to normal brightness and the Connecting message
	 * should disappear. - Click on the Mode -& Start Survey button
	 */
	@Ignore
	// TODO: Time dependent Connecting... test case.
	public void TC1302_ObserverConnectingDialogShouldPopUpWhenConnectingCarNotServer() throws Exception {
		Log.info("\nRunning TC1302_ObserverConnectingDialogShouldPopUpWhenConnectingCarNotServer ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);

		startDrivingSurvey(ANALYZER3_REPLAY_ROW_ID, SURVEY_STANDARD1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		observerViewPageActionList.get(0).open(EMPTY, NOTSET);

		stopSurveyAndAnalyzer();
	}

	/**
	 * Test Case ID: TC1375_ObserverView_CurtainView_ChangeInvocationOfOLCesium Script: - - 1) Log into Observer View - 2) While the survey is running, click the Curtain button - 3) Click on the
	 * Return button and refresh the page - 4) Let the survey continue for a few more minutes - 5) Click on the Curtain button Results: - 1) When the Curtain button is clicked, Connecting screen
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

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).showCurtainView(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnCurtainReturnButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).refreshPage(EMPTY, NOTSET);
		observerViewPageActionList.get(0).verifyPageLoaded(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnZoomOutButton(EMPTY, NOTSET);
		getTestEnvironmentAction().idleForSeconds(String.valueOf(60), NOTSET);
		observerViewPageActionList.get(0).showCurtainView(EMPTY, NOTSET);

		// TODO: Verifications for curtain view currently not present.
	}

	/**
	 * Test Case ID: TC1411_ObserverView_AssessmentSurvey_CurtainViewDisplayedUserAbleFollowVehicle Script:
	 * - - - On Home Page, click on Picarro Surveyors -& Online -& Curtain
	 * - - Click on Return.
	 * - - Turn Position OFF and click on Curtain
	 * - - Click on Return Results:
	 * 		- - Red color cursor will move along with car position and blue spikes are displayed
	 * 		- Cursor will stop moving but blue
	 * spikes will be displayed - User will be returned to Observer View page
	 */
	@Test /* Need a assessmentSurvey which running longer time - to enable the the second verification */
	public void TC1411_ObserverView_AssessmentSurvey_CurtainViewDisplayedUserAbleFollowVehicle() throws Exception {
		Log.info("\nRunning TC1411_ObserverView_AssessmentSurvey_CurtainViewDisplayedUserAbleFollowVehicle ...");

		loginAsDriver(USER_ROW_ID_PICARRO_DRIVER);
		startDrivingSurvey(ANALYZER3_REPLAY_ASSESSMENT_ROW_ID, SURVEY_ASSESSMENT1_ROW_ID, ONE_SECOND);

		loginAsObserver(USER_ROW_ID_PICARRO_ADMIN);

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).showCurtainView(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnCurtainReturnButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyObserverViewPageIsOpened(EMPTY, NOTSET));

		observerViewPageActionList.get(0).turnOffPosition(EMPTY, NOTSET);
		observerViewPageActionList.get(0).showCurtainView(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnCurtainReturnButton(EMPTY, NOTSET);
		// * Enable these steps when a long run assessment survey is available for this test
		//observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		//assertTrue(observerViewPageActionList.get(0).verifyObserverViewPageIsOpened(EMPTY, NOTSET));

		// TODO: Verify breadcrumb.
		// assertTrue(observerViewPageActionList.get(0).verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		// TODO: Car icon check & breadcrumb color detection is TODO
		// assertTrue(observerViewPageActionList.get(0).verifyCrossHairIconIsShownOnMap(COLOR_RED, NOTSET));

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

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnCurtainArrowUpButton(EMPTY, NOTSET);
		// TODO:
		// observerViewPageActionList.get(0).verifyMapImageHasChanged(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnCurtainArrowRightButton(EMPTY, NOTSET);
		// TODO:
		// observerViewPageActionList.get(0).verifyMapImageHasChanged(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnZoomInButton(EMPTY, NOTSET);
		// TODO:
		// observerViewPageActionList.get(0).verifyMapImageHasChanged(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnZoomOutButton(EMPTY, NOTSET);
		// TODO:
		// observerViewPageActionList.get(0).verifyMapImageHasChanged(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnCurtainReturnButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyObserverViewPageIsOpened(EMPTY, NOTSET));

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

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnMapButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnMapView(EMPTY, NOTSET);
		observerViewPageActionList.get(0).showCurtainView(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnCurtainReturnButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyMapViewIsShown(EMPTY, NOTSET));

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

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnMapButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnSatelliteView(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnDisplayButton(EMPTY, NOTSET);

		observerViewPageActionList.get(0).turnOnAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffConcentrationChart(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffLisas(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnGisButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnAllAssets(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffAllBoundaries(EMPTY, NOTSET);
		getTestEnvironmentAction().idleForSeconds(String.valueOf(60), NOTSET);
		observerViewPageActionList.get(0).refreshPage(EMPTY, NOTSET);
		observerViewPageActionList.get(0).verifyPageLoaded(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.get().surveyTag, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.get().surveyType, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.get().username, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeLabelStartsWith(SURVEY_INFO_TIME_PREFIX + String.valueOf(getHourOfDay()), NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeElapsedLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeRemainingLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoZoomLevelLabelEquals(String.format(SURVEY_INFO_ZOOM_LEVEL_X, 19), NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeElapsedIsTickingForward(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeRemainingLabelIsTickingBackward(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER1, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR1, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));

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

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnMapButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnMapView(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnDisplayButton(EMPTY, NOTSET);

		observerViewPageActionList.get(0).turnOffAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnConcentrationChart(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnGisButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffAllAssets(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnAllBoundaries(EMPTY, NOTSET);
		getTestEnvironmentAction().idleForSeconds(String.valueOf(10), NOTSET);
		observerViewPageActionList.get(0).refreshPage(EMPTY, NOTSET);
		observerViewPageActionList.get(0).verifyPageLoaded(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnZoomOutButton(EMPTY, NOTSET);

		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.get().surveyTag, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.get().surveyType, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.get().username, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeLabelStartsWith(SURVEY_INFO_TIME_PREFIX + String.valueOf(getHourOfDay()), NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeElapsedLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeRemainingLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoZoomLevelLabelEquals(String.format(SURVEY_INFO_ZOOM_LEVEL_X, 19), NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeElapsedIsTickingForward(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeRemainingLabelIsTickingBackward(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER1, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR1, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));

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

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnDisplayButton(EMPTY, NOTSET);

		observerViewPageActionList.get(0).turnOnAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnPosition(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnMapButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnMapView(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnGisButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyCrossHairIconIsShownOnMap(COLOR_RED, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyWindRoseIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyConcentrationChartIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

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

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnZoomOutButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnDisplayButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnPosition(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnMapButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnSatelliteView(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnGisButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyCrossHairIconIsShownOnMap(COLOR_RED, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyWindRoseIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyConcentrationChartIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

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

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffPosition(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnDisplayButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnMapButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnMapView(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnGisButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyStatusButtonIsGreen(EMPTY, NOTSET));

		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.get().surveyTag, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.get().surveyType, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.get().username, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeLabelStartsWith(SURVEY_INFO_TIME_PREFIX + String.valueOf(getHourOfDay()), NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeElapsedLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeRemainingLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoZoomLevelLabelEquals(String.format(SURVEY_INFO_ZOOM_LEVEL_X, 19), NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeElapsedIsTickingForward(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeRemainingLabelIsTickingBackward(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER1, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR1, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));

		assertTrue(observerViewPageActionList.get(0).verifyCrossHairIconIsShownOnMap(COLOR_RED, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));

		assertTrue(observerViewPageActionList.get(0).verifyCarIconIsNotInCenter(EMPTY, NOTSET));

		assertTrue(observerViewPageActionList.get(0).verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyWindRoseIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyConcentrationChartIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));

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

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnDisplayButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnWindRose(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnFOVs(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnGisButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnMapButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnMapView(EMPTY, NOTSET);

		assertTrue(observerViewPageActionList.get(0).verifyWindRoseIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyCrossHairIconIsShownOnMap(COLOR_RED, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyFOVIsShownOnMap(EMPTY, NOTSET));

		assertTrue(observerViewPageActionList.get(0).verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.get().surveyTag, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.get().surveyType, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.get().username, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeLabelStartsWith(SURVEY_INFO_TIME_PREFIX + String.valueOf(getHourOfDay()), NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeElapsedLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeRemainingLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoZoomLevelLabelEquals(String.format(SURVEY_INFO_ZOOM_LEVEL_X, 19), NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeElapsedIsTickingForward(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeRemainingLabelIsTickingBackward(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER1, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR1, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));

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

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnDisplayButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnGisButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffAllBoundaries(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnMaterialTypeOtherPlastic(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnMaterialTypeProtectedSteel(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnMaterialTypeCastIron(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnMapButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnMapView(EMPTY, NOTSET);

		assertTrue(observerViewPageActionList.get(0).verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyConcentrationChartIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyWindRoseIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyFOVIsShownOnMap(EMPTY, NOTSET));

		// TODO: Specific asset check (Currently no mechanism to detect from client-side). Might need some server-side product changes.
		assertTrue(observerViewPageActionList.get(0).verifyAssetIsShownOnMap(EMPTY, NOTSET));

		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.get().surveyTag, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.get().surveyType, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.get().username, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeLabelStartsWith(SURVEY_INFO_TIME_PREFIX + String.valueOf(getHourOfDay()), NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeElapsedLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeRemainingLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoZoomLevelLabelEquals(String.format(SURVEY_INFO_ZOOM_LEVEL_X, 19), NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeElapsedIsTickingForward(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoTimeRemainingLabelIsTickingBackward(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER1, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR1, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));

		assertTrue(observerViewPageActionList.get(0).verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));

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

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnDisplayButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffAllDisplayOptions(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnFOVs(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnGisButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		observerViewPageActionList.get(0).clickOnMapButton(EMPTY, NOTSET);
		observerViewPageActionList.get(0).turnOnSatelliteView(EMPTY, NOTSET);

		assertTrue(observerViewPageActionList.get(0).verifyCrossHairIconIsShownOnMap(COLOR_RED, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyFOVIsShownOnMap(EMPTY, NOTSET));

		assertTrue(observerViewPageActionList.get(0).verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));

		assertTrue(observerViewPageActionList.get(0).verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(observerViewPageActionList.get(0).verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));

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

		String analyzer = getTestEnvironmentAction().getWorkingAnalyzerSerialNumber();
		homePageActionList.get(0).clickOnFirstMatchingOnlineSurveyorLink(analyzer, NOTSET);
		observerViewPageActionList.get(0).getObserverViewPage().waitForPageLoad();
		observerViewPageActionList.get(0).waitForConnectionToComplete(EMPTY, NOTSET);
		assertTrue(observerViewPageActionList.get(0).verifyObserverViewPageIsOpened(EMPTY, NOTSET));
		stopSurveyAndAnalyzer();
	}

}
