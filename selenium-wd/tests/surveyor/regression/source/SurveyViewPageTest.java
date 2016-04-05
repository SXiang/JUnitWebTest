package surveyor.regression.source;

import static org.junit.Assert.*;
import common.source.Log;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.source.SurveyorTestRunner;

import surveyor.scommon.actions.SurveyViewPageActions;
import surveyor.scommon.source.SurveyViewPage;

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
public class SurveyViewPageTest extends BaseMapViewTest {

	protected static final String SAMPLE_SURVEY_FIELD_NOTES1 = "Ref Gas Canceled";
	protected static final String SURVEY_INFO_SURVEYOR = "Surveyor: Software Car";
	protected static final String SURVEY_INFO_ANALYZER = "Analyzer: FDDS2038";
	protected static final String SURVEY_INFO_ASSESSMENT_SURVEYOR = "Surveyor: Picarro Production #10";
	protected static final String SURVEY_INFO_ASSESSMENT_ANALYZER = "Analyzer: FEDS2055";
	protected static final String SURVEY_INFO_MODE_PREFIX = "Mode: ";
	protected static final String SURVEY_INFO_DRIVER_PREFIX = "Driver: ";
	protected static final String SURVEY_INFO_STABILITY_CLASS_B = "Stability Class: B";
	protected static final String TEST_SURVEY_STANDARD1_ID = "c8782024-cc65-b53a-5317-39d4b4f4731f";
	protected static final String TEST_SURVEY_STANDARD1_TAG = "stnd-pic";
	protected static final String TEST_SURVEY_STANDARD1_TYPE = "Standard";
	protected static final String TEST_SURVEY_STANDARD1_USERNAME = "Administrator";
	protected static final String TEST_SURVEY_ASSESSMENT1_ID = "028f8fb7-8ba3-a44b-f778-39d626cd322b";
	protected static final String TEST_SURVEY_ASSESSMENT1_TAG = "assessment";
	protected static final String TEST_SURVEY_ASSESSMENT1_TYPE = "Assessment";
	protected static final String TEST_SURVEY_ASSESSMENT1_USERNAME = "driver1@picarro.com";
	protected static final String TEST_SURVEY_RAPID_RESP_ID = "04B2FEDA-EE18-A49E-3208-39D4B50F48FE";
	protected static final String TEST_SURVEY_RAPID_RESP_TAG = "rr-pic";
	protected static final String TEST_SURVEY_RAPID_RESP_TYPE = "Rapid Response";
	protected static final String TEST_SURVEY_RAPID_RESP_USERNAME = "Administrator";
	protected static final String TEST_SURVEY_MANUAL1_ID = "4D7BF7AC-6CC2-9B18-1894-39D4B546927B";
	protected static final String TEST_SURVEY_MANUAL1_TAG = "man-pic";
	protected static final String TEST_SURVEY_MANUAL1_TYPE = "Manual";
	protected static final String TEST_SURVEY_MANUAL1_USERNAME = "Administrator";
	protected static final String TEST_SURVEY_OPERATOR1_ID = "1556ac85-a125-0347-2a02-39d4b529c6bd";
	protected static final String TEST_SURVEY_OPERATOR1_TAG = "op-pic";
	protected static final String TEST_SURVEY_OPERATOR1_TYPE = "Operator";
	protected static final String TEST_SURVEY_OPERATOR1_USERNAME = "Administrator";
	
	// Ethane specific constants.
	protected static final String ETHANE_SAMPLE_FIELD_NOTES = "3.6 big leak location";
	protected static final String ETHANE_SURVEY_INFO_SURVEYOR = "Surveyor: Green-Escape";
	protected static final String ETHANE_SURVEY_INFO_ANALYZER = "Analyzer: FEDS2059";
	protected static final String TEST_ETHANE_SURVEY_STANDARD_ID = "183fcb51-e968-bb81-0c98-39d666857ae7";
	protected static final String TEST_ETHANE_SURVEY_STANDARD_TAG = "ethane night sf b1";
	protected static final String TEST_ETHANE_SURVEY_STANDARD_TYPE = "Standard";
	protected static final String TEST_ETHANE_SURVEY_STANDARD_USERNAME = "picscdr@picarro.com";

	// Use this Manual survey for verifying Field notes are present.
	protected static final String TEST_SURVEY_MANUAL2_ID = "2278D26F-8D69-B070-56FD-39D4B552F8F2";
	protected static final String EMPTY = "";
	protected static final Integer NOTSET = -1;
	
	protected static DriverViewPageActions driverViewPageAction;
	protected static SurveyViewPageActions surveyViewPageAction;
	protected static SurveyViewPage surveyViewPage;
	
	@BeforeClass
	public static void beforeTestClass() throws Exception {
		disposeProcesses();
		
		// Initialization needed at class level for automation reports.
		initializePageActions();
	}

	@Before
	public void beforeTestMethod() {
		try {
			initializePageActions();
			
			driverViewPageAction = new DriverViewPageActions(driver, baseURL, testSetup);
			surveyViewPageAction = new SurveyViewPageActions(driver, baseURL,testSetup);

			// Initialize page objects.
			surveyViewPage = new SurveyViewPage(driver, testSetup, baseURL);
			PageFactory.initElements(driver, surveyViewPage);

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@After
    public void afterTestMethod() {
		try {
			afterTest();
			
			disposeProcesses();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test Case ID: TC1000_SurveyView_ViewIndicationsRapidResponseSurveySatelliteViewWhenNoGISLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All other options - OFF
	 *	- Indications - ON
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *	- - Breadcrumb and Indications are displayed on map in satellite view
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1000_SurveyView_ViewIndicationsRapidResponseSurveySatelliteViewWhenNoGISLoaded() throws Exception {
		Log.info("\nRunning TC1000_SurveyView_ViewIndicationsRapidResponseSurveySatelliteViewWhenNoGISLoaded ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1001_SurveyView_ViewRapidResponseSurveyMapViewWhenLISAFieldNotesAreOnGISOff
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All other options - OFF
	 *	- LISA - ON
	 *	- Field Notes - ON
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- - Survey Information is displayed in map view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- - Breadcrumb, Lisa and field notes are present on map
	 *	- - Indications and FOV are not displayed on map
	 */
	@Test
	public void TC1001_SurveyView_ViewRapidResponseSurveyMapViewWhenLISAFieldNotesAreOnGISOff() throws Exception {
		Log.info("\nRunning TC1001_SurveyView_ViewRapidResponseSurveyMapViewWhenLISAFieldNotesAreOnGISOff ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
		surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_RAPID_RESP_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_RAPID_RESP_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_RAPID_RESP_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1002_SurveyView_ViewRapidResponseSurveySatelliteViewWhenLISAFieldNotesAreOnGISOff
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All other options - OFF
	 *	- LISA - ON
	 *	- Field Notes - ON
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- - Breadcrumb, Lisa and field notes are present on map in satellite view
	 *	- - Indications and FOV are not displayed on map in satellite view
	 */
	@Test
	public void TC1002_SurveyView_ViewRapidResponseSurveySatelliteViewWhenLISAFieldNotesAreOnGISOff() throws Exception {
		Log.info("\nRunning TC1002_SurveyView_ViewRapidResponseSurveySatelliteViewWhenLISAFieldNotesAreOnGISOff ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
		surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_RAPID_RESP_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_RAPID_RESP_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_RAPID_RESP_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1003_SurveyView_ViewRapidResponseSurveyMapViewWhenFovIndicationOnGISOn
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All other options - OFF
	 *	- FOV - ON
	 *	- Indications - ON
	 *	- - Click GIS
	 *	- All options ON
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- Survey Information is displayed in map view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- -Breadcrumb,Indications and FOV are displayed on map along with GIS data
	 *	- - Lisa and field notes (if any) are not present on map
	 */
	@Test
	public void TC1003_SurveyView_ViewRapidResponseSurveyMapViewWhenFovIndicationOnGISOn() throws Exception {
		Log.info("\nRunning TC1003_SurveyView_ViewRapidResponseSurveyMapViewWhenFovIndicationOnGISOn ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_RAPID_RESP_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_RAPID_RESP_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_RAPID_RESP_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1004_SurveyView_ViewRapidResponseSurveySatelliteViewWhenFovIndicationOnGISOn
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All other options - OFF
	 *	- FOV - ON
	 *	- Indications - ON
	 *	- - Click GIS
	 *	- All options ON
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *	- Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- -Breadcrumb,Indications and FOV are displayed on map in satellite view along with GIS data
	 *	- - Lisa and field notes (if any) are not present on map
	 */
	@Test
	public void TC1004_SurveyView_ViewRapidResponseSurveySatelliteViewWhenFovIndicationOnGISOn() throws Exception {
		Log.info("\nRunning TC1004_SurveyView_ViewRapidResponseSurveySatelliteViewWhenFovIndicationOnGISOn ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_RAPID_RESP_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_RAPID_RESP_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_RAPID_RESP_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1005_SurveyView_ViewRapidResponseSurveyMapViewWhenAllDisplayOptionsAreOn
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All options - ON
	 *	- - Click GIS
	 *	- Boundaries: OFF
	 *	- Assets: Protected steel, UnProtected Steel - ON
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- - Breadcrumb, Indications, LISA, FOV and survey information is displayed on map
	 *  - Protected steel and UnProtected Steel assets are displayed on map and other assets are not displayed
	 *	- - Boundaries not displayed on map
	 */
	@Test
	public void TC1005_SurveyView_ViewRapidResponseSurveyMapViewWhenAllDisplayOptionsAreOn() throws Exception {
		Log.info("\nRunning TC1005_SurveyView_ViewRapidResponseSurveyMapViewWhenAllDisplayOptionsAreOn ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssets(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypeProtectedSteel(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypeUnprotectedSteel(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		// TODO: Specific asset check (Currently no mechanism to detect from client-side). Might need some server-side product changes.
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1006_SurveyView_ViewRapidResponseSurveySatelliteViewWhenAllDisplayOptionsAreONOtherPlasticCopperAssetsONBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All options - ON
	 *	- - Click GIS
	 *	- Boundaries: OFF
	 *	- Assets: Other Plastic, Copper - ON
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *	- - Breadcrumb, Indications, LISA, FOV and survey information is displayed on map in satellite view
	 *  - Other Plastic and Copper assets are displayed on map in satellite view and other assets are not displayed
	 *	- - Boundaries not displayed on map in satellite view
	 */
	@Test
	public void TC1006_SurveyView_ViewRapidResponseSurveySatelliteViewWhenAllDisplayOptionsAreONOtherPlasticCopperAssetsONBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1006_SurveyView_ViewRapidResponseSurveySatelliteViewWhenAllDisplayOptionsAreONOtherPlasticCopperAssetsONBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssets(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypeOtherPlastic(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypeCopper(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		// TODO: Specific asset check (Currently no mechanism to detect from client-side). Might need some server-side product changes.
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1007_SurveyView_ViewRapidResponseSurveyMapViewWhenLISAONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All other options - OFF
	 *	- Lisa - ON
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- - Breadcrumb and Lisa are displayed on map
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1007_SurveyView_ViewRapidResponseSurveyMapViewWhenLISAONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1007_SurveyView_ViewRapidResponseSurveyMapViewWhenLISAONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1008_SurveyView_ViewRapidResponseSurveySatelliteViewWhenLISAONAssetsBoundariesOFF
	 * Script: -  	
	 *	- -On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All other options - OFF
	 *	- Lisa - ON
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *	- - Breadcrumb and Lisa are displayed on map in satellite view
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1008_SurveyView_ViewRapidResponseSurveySatelliteViewWhenLISAONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1008_SurveyView_ViewRapidResponseSurveySatelliteViewWhenLISAONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1009_SurveyView_ViewRapidResponseSurveyMapViewWhenFOVONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All other options - OFF
	 *	- FOV - ON
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- - Breadcrumb and FOV are displayed on map
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1009_SurveyView_ViewRapidResponseSurveyMapViewWhenFOVONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1009_SurveyView_ViewRapViewRapidResponseidResponseSurveyMapViewWhenFOVONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1010_SurveyView_ViewRapidResponseSurveySatelliteViewWhenFOVONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All other options - OFF
	 *	- FOV - ON
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *	- - Breadcrumb and FOV are displayed on map in satellite view
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1010_SurveyView_ViewRapidResponseSurveySatelliteViewWhenFOVONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1010_SurveyView_ViewRapidResponseSurveySatelliteViewWhenFOVONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1011_SurveyView_ViewRapidResponseSurveyMapViewWhenFieldNotesONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All other options - OFF
	 *	- Field Notes - ON
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- - Breadcrumb and Field Notes are displayed on map
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1011_SurveyView_ViewRapidResponseSurveyMapViewWhenFieldNotesONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1011_SurveyView_ViewRapidResponseSurveyMapViewWhenFieldNotesONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1012_SurveyView_ViewRapidResponseSurveySatellliteViewWhenFieldNotesONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All other options - OFF
	 *	- Field Notes - ON
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *	- - Breadcrumb and Field Notes are displayed on map in satellite view
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1012_SurveyView_ViewRapidResponseSurveySatellliteViewWhenFieldNotesONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1012_SurveyView_ViewRapidResponseSurveySatellliteViewWhenFieldNotesONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1013_SurveyView_ViewRapidResponseSurveyMapViewWhenIsotopicAnalysisONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All other options - OFF
	 *	- Isotopic Analysis - ON
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *  - Breadcrumb and capture results are displayed on map
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1013_SurveyView_ViewRapidResponseSurveyMapViewWhenIsotopicAnalysisONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1013_SurveyView_ViewRapidResponseSurveyMapViewWhenIsotopicAnalysisONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIsotopicAnalysis(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_NotNaturalGas), NOTSET));
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_IsotopicCanceled), NOTSET));
		assertTrue(surveyViewPageAction.verifyRefGasCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_Passed), NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1014_SurveyView_ViewRapidResponseSurveySatelliteViewWhenIsotopicAnalysisONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All other options - OFF
	 *	- Isotopic Analysis - ON
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *  - Breadcrumb and capture results are displayed on map in satellite view
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1014_SurveyView_ViewRapidResponseSurveySatelliteViewWhenIsotopicAnalysisONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1014_SurveyView_ViewRapidResponseSurveySatelliteViewWhenIsotopicAnalysisONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIsotopicAnalysis(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_NotNaturalGas), NOTSET));
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_IsotopicCanceled), NOTSET));
		assertTrue(surveyViewPageAction.verifyRefGasCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_Passed), NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1016_SurveyView_ViewManualSurveySatelliteViewWhenGISDisplayOptionsAreOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All OFF
	 *	- - GIS: All OFF
	 *	- - Map View: Satellite
	 * Results: - 
	 *  - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Stability Class, Surveyor and analyzer info, 
	 *    Start Time, End Time
	 *	- - User should see only Breadcrumb in satellite view
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1016_SurveyView_ViewManualSurveySatelliteViewWhenGISDisplayOptionsAreOFF() throws Exception {
		Log.info("\nRunning TC1016_SurveyView_ViewManualSurveySatelliteViewWhenGISDisplayOptionsAreOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1017_SurveyView_ViewIndicationsDataManualSurveySatelliteViewWhenGISDataLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: Indication ON
	 *	- All other options OFF
	 *	- - Map View: Satellite
	 *	- - GIS: All ON
	 * Results: - 
	 *  - Indications and breadcrumb is displayed on map in satellite view along with assets and boundaries data
	 *	- - All other survey data is not displayed
	 */
	@Test
	public void TC1017_SurveyView_ViewIndicationsDataManualSurveySatelliteViewWhenGISDataLoaded() throws Exception {
		Log.info("\nRunning TC1017_SurveyView_ViewIndicationsDataManualSurveySatelliteViewWhenGISDataLoaded ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1018_SurveyView_ViewFOVDataManualSurveySatellliteViewWhenGISDataLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: FOV ON
	 *	- All other options OFF
	 *	- - Map View: Satellite
	 *	- - GIS: All ON
	 * Results: - 
	 * 	- FOV and breadcrumb is displayed on map in satellite view along with assets and boundaries data
	 *	- - All other survey data is not displayed
	 */
	@Test
	public void TC1018_SurveyView_ViewFOVDataManualSurveySatellliteViewWhenGISDataLoaded() throws Exception {
		Log.info("\nRunning TC1018_SurveyView_ViewFOVDataManualSurveySatellliteViewWhenGISDataLoaded ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1019_SurveyView_ViewFOVIndicationsLisasDataManualSurveySatelliteViewWhenAssetsAreLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All ON
	 *	- - Map View: Satellite
	 *	- - GIS: Assets ON
	 *	- Boundaries OFF
	 * Results: - 
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,
	 *      Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map in satellite view
	 *  - All pipes data is displayed. Boundaries are not present
	 */
	@Test
	public void TC1019_SurveyView_ViewFOVIndicationsLisasDataManualSurveySatelliteViewWhenAssetsAreLoaded() throws Exception {
		Log.info("\nRunning TC1019_SurveyView_ViewFOVIndicationsLisasDataManualSurveySatelliteViewWhenAssetsAreLoaded ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssets(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_NotNaturalGas), NOTSET));
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_IsotopicCanceled), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));

		// Use the second manual survey for Field notes verification.
		surveyViewPageAction.open(TEST_SURVEY_MANUAL2_ID, NOTSET);
		surveyViewPageAction.refreshPage(EMPTY, NOTSET);	// refresh the page to re-initialize the page object.
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssets(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));

	}
 
	/**
	 * Test Case ID: TC1020_SurveyView_ViewFOVIndicationsLisasDataManualSurveySatelliteViewWhenBoundariesLoadedIntoGIS
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All ON
	 *	- - Map View: Satellite
	 *	- - GIS: Assets OFF
	 *	- Boundaries ON
	 * Results: - 
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,
	 *      Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map in satellite view
	 *	- - Only boundaries are displayed. Assets are not displayed
	 */
	@Test
	public void TC1020_SurveyView_ViewFOVIndicationsLisasDataManualSurveySatelliteViewWhenBoundariesLoadedIntoGIS() throws Exception {
		Log.info("\nRunning TC1020_SurveyView_ViewFOVIndicationsLisasDataManualSurveySatelliteViewWhenBoundariesLoadedIntoGIS ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssets(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllBoundaries(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_NotNaturalGas), NOTSET));
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_IsotopicCanceled), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));		
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));

		// Use the second manual survey for Field notes verification.
		surveyViewPageAction.open(TEST_SURVEY_MANUAL2_ID, NOTSET);
		surveyViewPageAction.refreshPage(EMPTY, NOTSET);	// refresh the page to re-initialize the page object.
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssets(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllBoundaries(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1021_SurveyView_ViewIndicationsLisasDataManualSurveySatelliteViewWhenGISDataLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: Indications ON
	 *	- LISA ON
	 *	- All other options OFF
	 *	- - Map View: Satellite
	 *	- - GIS: All ON
	 * Results: - 
	 *  - Indications, Lisa and breadcrumb is displayed on map in satellite view along with assets and boundaries data
	 *	- - All other survey data is not displayed
	 */
	@Test
	public void TC1021_SurveyView_ViewIndicationsLisasDataManualSurveySatelliteViewWhenGISDataLoaded() throws Exception {
		Log.info("\nRunning TC1021_SurveyView_ViewIndicationsLisasDataManualSurveySatelliteViewWhenGISDataLoaded ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1022_SurveyView_ViewIndicationsManualSurveySatelliteViewWhenNoGISLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All other options - OFF
	 *	- Indications - ON
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *	- - Breadcrumb and Indications are displayed on map in satellite view
	 *	- - All other survey data, assets and boundaries are not displayed
	 *  - Indications displayed in survey view should be same as that present in driver/observer view
	 */
	@Test
	public void TC1022_SurveyView_ViewIndicationsManualSurveySatelliteViewWhenNoGISLoaded() throws Exception {
		Log.info("\nRunning TC1022_SurveyView_ViewIndicationsManualSurveySatelliteViewWhenNoGISLoaded ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		
		// TODO: Indications displayed in survey view are same as that present in driver/observer view needs to be verified.
	}
 
	/**
	 * Test Case ID: TC1023_SurveyView_ViewManualSurveyMapViewWhenLISAFieldNotesAreOnGISOff
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All other options - OFF
	 *	- LISA - ON
	 *	- Field Notes - ON
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- - Survey Information is displayed in map view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- - Breadcrumb, Lisa and field notes are present on map
	 *	- - Indications and FOV are not displayed on map
	 */
	@Test
	public void TC1023_SurveyView_ViewManualSurveyMapViewWhenLISAFieldNotesAreOnGISOff() throws Exception {
		Log.info("\nRunning TC1023_SurveyView_ViewManualSurveyMapViewWhenLISAFieldNotesAreOnGISOff ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
		surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));

		// Use the second manual survey for Field notes verification.
		surveyViewPageAction.open(TEST_SURVEY_MANUAL2_ID, NOTSET);
		surveyViewPageAction.refreshPage(EMPTY, NOTSET);	// refresh the page to re-initialize the page object.
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
		surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1024_SurveyView_ViewManualSurveySatelliteViewWhenLISAFieldNotesAreOnGISOff
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All other options - OFF
	 *	- LISA - ON
	 *	- Field Notes - ON
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- - Breadcrumb, Lisa and field notes are present on map in satellite view
	 *	- - Indications and FOV are not displayed on map in satellite view
	 */
	@Test
	public void TC1024_SurveyView_ViewManualSurveySatelliteViewWhenLISAFieldNotesAreOnGISOff() throws Exception {
		Log.info("\nRunning TC1024_SurveyView_ViewManualSurveySatelliteViewWhenLISAFieldNotesAreOnGISOff ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
		surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));

		// Use the second manual survey for Field notes verification.
		surveyViewPageAction.open(TEST_SURVEY_MANUAL2_ID, NOTSET);
		surveyViewPageAction.refreshPage(EMPTY, NOTSET);	// refresh the page to re-initialize the page object.
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
		surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1025_SurveyView_ViewManualSurveyMapViewWhenFovIndicationOnGISOn
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All other options - OFF
	 *	- FOV - ON
	 *	- Indications - ON
	 *	- - Click GIS
	 *	- All options ON
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- Survey Information is displayed in map view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- -Breadcrumb,Indications and FOV are displayed on map along with GIS data
	 *	- - Lisa and field notes (if any) are not present on map
	 */
	@Test
	public void TC1025_SurveyView_ViewManualSurveyMapViewWhenFovIndicationOnGISOn() throws Exception {
		Log.info("\nRunning TC1025_SurveyView_ViewManualSurveyMapViewWhenFovIndicationOnGISOn ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
	}
	
	/**
	 * Test Case ID: TC1026_SurveyView_ViewManualSurveySatelliteViewWhenFovIndicationONGISON
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- FOV - ON
	 *	- Indications - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options ON
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *	- Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- -Breadcrumb,Indications and FOV are displayed on map in satellite view along with GIS data
	 *	- - Lisa and field notes (if any) are not present on map
	 */
	@Test
	public void TC1026_SurveyView_ViewManualSurveySatelliteViewWhenFovIndicationONGISON() throws Exception {
		Log.info("\nRunning TC1026_SurveyView_ViewManualSurveySatelliteViewWhenFovIndicationONGISON ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1027_SurveyView_ViewManualSurveyMapViewWhenAllDisplayOptionsAreONCastIronCopperAssetsONBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All options - ON
	 *	- - Click GIS
	 *	- Assets: Cast Iron, Copper - ON
	 *	- Boundaries: OFF
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- - Breadcrumb, Indications, LISA, FOV and survey information is displayed on map
	 *  - Cast Iron, Copper assets are displayed on map and other assets are not displayed
	 *	- - Boundaries not displayed on map
	 */
	@Test
	public void TC1027_SurveyView_ViewManualSurveyMapViewWhenAllDisplayOptionsAreONCastIronCopperAssetsONBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1027_SurveyView_ViewManualSurveyMapViewWhenAllDisplayOptionsAreONCastIronCopperAssetsONBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypeCopper(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypeCastIron(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		
		// Assets don't have unique IDs in UI. Specific assets checks cannot be currently done from UI.
		// Specific Assets check is tracked as an API test.
	}
 
	/**
	 * Test Case ID: TC1028_SurveyView_ViewManualSurveySatelliteViewWhenAllDisplayOptionsAreONProtectedSteelPEPlasticAssetsONBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All options - ON
	 *	- - Click GIS
	 *	- Assets: PE Plastic, Protected Steel - ON
	 *	- Boundaries: OFF
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *	- - Breadcrumb, Indications, LISA, FOV and survey information is displayed on map in satellite view
	 *  - - PE Plastic and Protected Steel assets are displayed on map in satellite view and other assets are not displayed
	 *	- - Boundaries not displayed on map in satellite view
	 */
	@Test
	public void TC1028_SurveyView_ViewManualSurveySatelliteViewProtectedSteelPEPlasticAssetsONBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1028_SurveyView_ViewManualSurveySatelliteViewProtectedSteelPEPlasticAssetsONBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypePEPlastic(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypeProtectedSteel(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));

		// Assets don't have unique IDs in UI. Specific assets checks cannot be currently done from UI.
		// Specific Assets check is tracked as an API test.
	}
 
	/**
	 * Test Case ID: TC1029_SurveyView_ViewManualSurveyMapViewWhenLISAONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- Lisa - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- - Breadcrumb and Lisa are displayed on map
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1029_SurveyView_ViewManualSurveyMapViewWhenLISAONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1029_SurveyView_ViewManualSurveyMapViewWhenLISAONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1030_SurveyView_ViewManualSurveySatelliteViewWhenLISAONAssetsBoundariesOFF
	 * Script: -  	
	 *	- -On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- Lisa - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 *	- - Breadcrumb and Lisa are displayed on map in satellite view
	 * Results: - 
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1030_SurveyView_ViewManualSurveySatelliteViewWhenLISAONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1030_SurveyView_ViewManualSurveySatelliteViewWhenLISAONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1031_SurveyView_ViewManualSurveyMapViewWhenFOVONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- FOV - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- - Breadcrumb and FOV are displayed on map
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1031_SurveyView_ViewManualSurveyMapViewWhenFOVONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1031_SurveyView_ViewManualSurveyMapViewWhenFOVONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1032_SurveyView_ViewManualSurveySatelliteViewWhenFOVONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- FOV - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *	- - Breadcrumb and FOV are displayed on map in satellite view
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1032_SurveyView_ViewManualSurveySatelliteViewWhenFOVONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1032_SurveyView_ViewManualSurveySatelliteViewWhenFOVONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1033_SurveyView_ViewManualSurveyMapViewWhenFieldNotesONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- Field Notes - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- - Breadcrumb and Field Notes are displayed on map
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	// TODO: Test coding complete. Need a manual survey with field notes that has been pushed to automation DB.
	public void TC1033_SurveyView_ViewManualSurveyMapViewWhenFieldNotesONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1033_SurveyView_ViewManualSurveyMapViewWhenFieldNotesONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		// TODO: Need a manual survey with Field notes for verification.
		//assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1034_SurveyView_ViewManualSurveySatellliteViewWhenFieldNotesONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- Field Notes - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *	- - Breadcrumb and Field Notes are displayed on map in satellite view
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	// TODO: Test coding complete. Need a manual survey with field notes that has been pushed to automation DB.
	public void TC1034_SurveyView_ViewManualSurveySatelliteViewWhenFieldNotesONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1034_SurveyView_ViewManualSurveySatelliteViewWhenFieldNotesONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		// TODO: Need a manual survey with Field notes for verification.
		//assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1035_SurveyView_ViewManualSurveyMapViewWhenIsotopicAnalysisONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- Isotopic Analysis - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- - Breadcrumb and capture results are displayed on map
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1035_SurveyView_ViewManualSurveyMapViewWhenIsotopicAnalysisONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1035_SurveyView_ViewManualSurveyMapViewWhenIsotopicAnalysisONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIsotopicAnalysis(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CANCELLED, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1036_SurveyView_ViewManualSurveySatelliteViewWhenIsotopicAnalysisONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- Isotopic Analysis - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *	- - Breadcrumb and capture results are displayed on map in satellite view
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1036_SurveyView_ViewManualSurveySatelliteViewWhenIsotopicAnalysisONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1036_SurveyView_ViewManualSurveySatelliteViewWhenIsotopicAnalysisONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIsotopicAnalysis(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CANCELLED, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1263_SurveyView_ReturningFromCurtainViewShouldRestoreUserSelectedMapViewCombination
	 * Script: -  	
	 *	- - Select Map view
	 *	- - Click on Curtain View
	 *	- - Click on return
	 * Results: - 
	 *	- - Map view should be displayed
	 */
	@Test
	public void TC1263_SurveyView_ReturningFromCurtainViewShouldRestoreUserSelectedMapViewCombination() throws Exception {
		Log.info("\nRunning TC1263_SurveyView_ReturningFromCurtainViewShouldRestoreUserSelectedMapViewCombination ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.showCurtainView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnCurtainReturnButton(EMPTY, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyMapViewIsShown(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1376_SurveyView_CurtainView_ChangeInvocationOfOLCesium
	 * Script: -  	
	 *	- 1) From the Driving Surveys page, click on a survey
	 *	- 2) Click the Curtain button
	 * Results: - 
	 *  - 1) When the Curtain button is clicked, "Connecting" screen will appear. Background map/satellite will gradually appear, along with blue wall (curtain)
	 */
	@Ignore
	// TODO: Curtain view needs more page object methods.
	public void TC1376_SurveyView_CurtainView_ChangeInvocationOfOLCesium() throws Exception {
		Log.info("\nRunning TC1376_SurveyView_CurtainView_ChangeInvocationOfOLCesium ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.showCurtainView(EMPTY, NOTSET);
	}
 
	/**
	 * Test Case ID: TC1410_HistoricalSurveyViewAssessmentSurveyMode_RedTrace
	 * Script: -  	
	 * Results: - 
	 *	- - On Home Page, click on Driving surveys link and choose a survey with assessment mode and red trace
	 */
	@Ignore
	// TODO: Check survey with Red trace. 
	public void TC1410_HistoricalSurveyViewAssessmentSurveyMode_RedTrace() throws Exception {
		Log.info("\nRunning TC1410_HistoricalSurveyViewAssessmentSurveyMode_RedTrace ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
	}
 
	/**
	 * Test Case ID: TC1502_SurveyViewAssessmentMode_CurtainViewDisplayedUserCanNavigateIt
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey for assessment mode
	 *	- - Click on Curtain button
	 *	- - Click on Return button
	 *	- - Capture results are not displayed
	 * Results: - 
	 *  - Spikes are displayed in Curtain View and user not allowed to turn pipes ON or OFF
	 *  - Capture results are not displayed 
	 *  - Follow button is not present
	 *	- - User is returned back to survey view
	 */
	@Ignore
	public void TC1502_SurveyViewAssessmentMode_CurtainViewDisplayedUserCanNavigateIt() throws Exception {
		Log.info("\nRunning TC1502_SurveyViewAssessmentMode_CurtainViewDisplayedUserCanNavigateIt ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
		surveyViewPageAction.showCurtainView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnCurtainReturnButton(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsNotPresentOnMap(ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
	}
 
	/**
	 * Test Case ID: TC1512_SurveyViewAssessmentMode_CheckSurveyDataPresentAfterUserRefreshesBrowser_Satellite
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey in assessment mode
	 *	- - Click on Map and turn Satellite View ON
	 *	- - Refresh the browser after couple of mins
	 * Results: - 
	 *  - After refreshing, survey data should be present in satellite view
	 *	- - Survey details - Tag, Mode, Driver Name, Stability Class, Start Time, End Time, Surveyor Name should be present in satellite view
	 *  - None of the values should be missing. Start and End Time should not reset after the refresh.
	 */
	@Test
	public void TC1512_SurveyViewAssessmentMode_CheckSurveyDataPresentAfterUserRefreshesBrowser_Satellite() throws Exception {
		Log.info("\nRunning TC1512_SurveyViewAssessmentMode_CheckSurveyDataPresentAfterUserRefreshesBrowser_Satellite ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		String startTimeTextBeforeRefresh = ((SurveyViewPage)surveyViewPageAction.getPageObject()).getStartTimeLabelText();
		String endTimeTextBeforeRefresh = ((SurveyViewPage)surveyViewPageAction.getPageObject()).getEndTimeLabelText();
		testEnvironmentAction.idleForSeconds(String.valueOf(60), NOTSET);
		surveyViewPageAction.refreshPage(EMPTY, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		String startTimeTextAfterRefresh = ((SurveyViewPage)surveyViewPageAction.getPageObject()).getStartTimeLabelText();
		String endTimeTextAfterRefresh = ((SurveyViewPage)surveyViewPageAction.getPageObject()).getEndTimeLabelText();
		assertTrue(startTimeTextAfterRefresh.equals(startTimeTextBeforeRefresh));
		assertTrue(endTimeTextAfterRefresh.equals(endTimeTextBeforeRefresh));
		assertTrue(surveyViewPageAction.verifySatelliteViewIsShown(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_ASSESSMENT1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_ASSESSMENT1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_ASSESSMENT1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ASSESSMENT_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_ASSESSMENT_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1513_SurveyViewAssessmentMode_CheckSurveyDataPresentAfterUserRefreshesBrowser_Map
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey in assessment mode
	 *	- - Click on Map and turn Map View ON
	 *	- - Refresh the browser after couple of mins
	 *	- - Click on Map and turn Map View ON
	 * Results: - 
	 *  - After refreshing, survey data should be present in map view
	 *	- - Survey details - Tag, Mode, Driver Name, Stability Class, Start Time, End Time, Surveyor Name should be present in map view
	 *  - None of the values should be missing. Start and End Time should not reset after the refresh.
	 */
	@Test
	public void TC1513_SurveyViewAssessmentMode_CheckSurveyDataPresentAfterUserRefreshesBrowser_Map() throws Exception {
		Log.info("\nRunning TC1513_SurveyViewAssessmentMode_CheckSurveyDataPresentAfterUserRefreshesBrowser_Map ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		String startTimeTextBeforeRefresh = ((SurveyViewPage)surveyViewPageAction.getPageObject()).getStartTimeLabelText();
		String endTimeTextBeforeRefresh = ((SurveyViewPage)surveyViewPageAction.getPageObject()).getEndTimeLabelText();
		testEnvironmentAction.idleForSeconds(String.valueOf(60), NOTSET);
		surveyViewPageAction.refreshPage(EMPTY, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		String startTimeTextAfterRefresh = ((SurveyViewPage)surveyViewPageAction.getPageObject()).getStartTimeLabelText();
		String endTimeTextAfterRefresh = ((SurveyViewPage)surveyViewPageAction.getPageObject()).getEndTimeLabelText();
		assertTrue(startTimeTextAfterRefresh.equals(startTimeTextBeforeRefresh));
		assertTrue(endTimeTextAfterRefresh.equals(endTimeTextBeforeRefresh));
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_ASSESSMENT1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_ASSESSMENT1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_ASSESSMENT1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ASSESSMENT_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_ASSESSMENT_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1514_SurveyViewAssessmentMode_CheckZoomLevelUsingMouseControlsSurveyViewWhenSurveyDataLoaded_Map
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey in assessment mode
	 *	- - Click on Map and turn Map View ON
	 *	- - Click on Display
	 *	- All options ON
	 *	- - Zoom in using mouse controls
	 *	- - Zoom out using mouse controls
	 * Results: - 
	 *  - Survey data is displayed in map view at every zoom level
	 */
	@Test
	public void TC1514_SurveyViewAssessmentMode_CheckZoomLevelUsingMouseControlsSurveyViewWhenSurveyDataLoaded_Map() throws Exception {
		Log.info("\nRunning TC1514_SurveyViewAssessmentMode_CheckZoomLevelUsingMouseControlsSurveyViewWhenSurveyDataLoaded_Map ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);

		surveyViewPageAction.clickOnZoomInButton(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1515_SurveyViewAssessmentMode_CheckZoomLevelUsingMouseControlsSurveyViewWhenSurveyDataLoaded_Satellite
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey in assessment mode
	 *	- - Click on Map and turn Satellite View ON
	 *	- - Click on Display
	 *	- All options ON
	 *	- - Zoom in using mouse controls
	 *	- - Zoom out using mouse controls
	 * Results: - 
	 *  - Survey data is displayed in satellite view at every zoom level
	 */
	@Test
	public void TC1515_SurveyViewAssessmentMode_CheckZoomLevelUsingMouseControlsSurveyViewWhenSurveyDataLoaded_Satellite() throws Exception {
		Log.info("\nRunning TC1515_SurveyViewAssessmentMode_CheckZoomLevelUsingMouseControlsSurveyViewWhenSurveyDataLoaded_Satellite ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);

		surveyViewPageAction.clickOnZoomInButton(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1516_SurveyView_ViewAssessmentSurveySurveyMapViewWhenFovGISON
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey in assessment mode
	 *	- - Click Display
	 *	- FOV - ON
	 *	- - Click GIS
	 *	- All options ON
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- Survey Information is displayed in map view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- -Breadcrumb,FOV are displayed on map along with GIS data
	 */
	@Test
	public void TC1516_SurveyView_ViewAssessmentSurveyMapViewWhenFovGISON() throws Exception {
		Log.info("\nRunning TC1516_SurveyView_ViewAssessmentSurveyMapViewWhenFovGISON ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_ASSESSMENT1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_ASSESSMENT1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_ASSESSMENT1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ASSESSMENT_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_ASSESSMENT_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1517_SurveyView_ViewAssessmentSurveySatelliteViewWhenFovGISON
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- FOV - ON
	 *	- - Click GIS
	 *	- All options ON
	 *	- - Click on Map and turn Satellite view ON
	 *	- Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 * Results: - 
	 *	- -Breadcrumb and FOV are displayed on map in satellite view along with GIS data
	 */
	@Test
	public void TC1517_SurveyView_ViewAssessmentSurveySurveySatelliteViewWhenFovGISON() throws Exception {
		Log.info("\nRunning TC1517_SurveyView_ViewAssessmentSurveySurveySatelliteViewWhenFovGISON ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_ASSESSMENT1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_ASSESSMENT1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_ASSESSMENT1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ASSESSMENT_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_ASSESSMENT_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1518_SurveyView_ViewAssessmentSurveyMapViewWhenAllDisplayOptionsAreONOtherPlasticProtectedSteelCastIronAssetsONBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All options - ON
	 *	- - Click GIS
	 *	- Assets: Other Plastic, Protected steel, Cast iron - ON
	 *	- Boundaries: OFF
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- - Breadcrumb, FOV and survey information is displayed on map
	 *  - Other Plastic, Protected steel and Cast iron assets are displayed on map and other assets are not displayed
	 *	- - Boundaries not displayed on map
	 */
	@Test
	public void TC1518_SurveyView_ViewAssessmentSurveyMapViewWhenAllDisplayOptionsAreONOtherPlasticProtectedSteelCastIronAssetsONBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1518_SurveyView_ViewAssessmentSurveyMapViewWhenAllDisplayOptionsAreONOtherPlasticProtectedSteelCastIronAssetsONBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypeOtherPlastic(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypeProtectedSteel(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypeCastIron(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_ASSESSMENT1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_ASSESSMENT1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_ASSESSMENT1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ASSESSMENT_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_ASSESSMENT_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		
		// Assets don't have unique IDs in UI. Specific assets checks cannot be currently done from UI.
		// Specific Assets check is tracked as an API test.
	}
 
	/**
	 * Test Case ID: TC1519_SurveyView_ViewAssessmentSurveySatelliteViewWhenAllDisplayOptionsAreONPEPlasticUn_ProtectedSteelCopperAssetsONBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey in assessment mode
	 *	- - Click Display
	 *	- All options - ON
	 *	- - Click GIS
	 *	- Assets: PE Plastic, Un-Protected Steel, Copper - ON
	 *	- Boundaries: OFF
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *	- - Breadcrumb, FOV and survey information is displayed on map in satellite view
	 *  - PE Plastic, Un-Protected Steel and Copper assets are displayed on map in satellite view and other assets are not displayed
	 *	- - Boundaries not displayed on map in satellite view
	 */
	@Test
	public void TC1519_SurveyView_ViewAssessmentSurveySatelliteViewWhenAllDisplayOptionsAreONPEPlasticUn_ProtectedSteelCopperAssetsONBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1519_SurveyView_ViewAssessmentSurveySatelliteViewWhenAllDisplayOptionsAreONPEPlasticUn_ProtectedSteelCopperAssetsONBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypePEPlastic(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypeUnprotectedSteel(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypeCopper(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_ASSESSMENT1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_ASSESSMENT1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_ASSESSMENT1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ASSESSMENT_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_ASSESSMENT_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));

		// Assets don't have unique IDs in UI. Specific assets checks cannot be currently done from UI.
		// Specific Assets check is tracked as an API test.
	}
 
	/**
	 * Test Case ID: TC1520_SurveyView_ViewAssessmentSurveyMapViewWhenfovOFFAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey in assessment mode
	 *	- - Click Display
	 *	- FOV - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- - Breadcrumb and top survey information is displayed on map
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1520_SurveyView_ViewAssessmentSurveyMapViewWhenfovOFFAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1520_SurveyView_ViewAssessmentSurveyMapViewWhenfovOFFAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffFOVs(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_ASSESSMENT1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_ASSESSMENT1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_ASSESSMENT1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ASSESSMENT_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_ASSESSMENT_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC1521_SurveyView_ViewAssessmentSurveyMapViewWhenFOVOFFAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey in assessment mode
	 *	- - Click Display
	 *	- FOV- OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- - Breadcrumb and survey related data on top is displayed on map
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1521_SurveyView_ViewAssessmentSurveyMapViewWhenFOVOFFAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1521_SurveyView_ViewAssessmentSurveyMapViewWhenFOVOFFAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffFOVs(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_ASSESSMENT1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_ASSESSMENT1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_ASSESSMENT1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ASSESSMENT_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_ASSESSMENT_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
	}
 	
	/**
	 * Test Case ID: TC1694_SurveyView_VerifyNotesAddedForPeaksInSurvey
	 * Script: -  	
	 *	- - Verify a historical survey from Driving Survey page
	 * Results: - 
	 *	- Not Natural Gas- Green
	 *	- Natural Gas - Red
	 *	- Possible Natural Gas- Yellow
	 *	- Vehicle Exhaust - Purple
	 */
	@Ignore
	public void TC1694_SurveyView_VerifyNotesAddedForPeaksInSurvey () throws Exception {
		Log.info("\nRunning TC1694_SurveyView_VerifyNotesAddedForPeaksInSurvey  ...");


		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_ETHANE_SURVEY_STANDARD_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		// wait for elements to paint on the map.
		testEnvironmentAction.idleForSeconds(String.valueOf(10), NOTSET);
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(ETHANE_SAMPLE_FIELD_NOTES, NOTSET));	}

	/**
	 * Test Case ID: TC1692_SurveyView_VerifyPeakbubble
	 * Script: -  	
	 *	- - Verify a historical survey from Driving Survey page
	 * Results: - 
	 *	- Not Natural Gas- Green
	 *	- Natural Gas - Red
	 *	- Possible Natural Gas- Yellow
	 *	- Vehicle Exhaust - Purple
	 */
	@Ignore
	public void TC1692_SurveyView_VerifyPeakbubble () throws Exception {
		Log.info("\nRunning TC1692_SurveyView_VerifyPeakbubble  ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_ETHANE_SURVEY_STANDARD_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		// wait for elements to paint on the map.
		testEnvironmentAction.idleForSeconds(String.valueOf(10), NOTSET);
		surveyViewPageAction.clickOnFirst3300IndicationShownOnMap("NaturalGas", NOTSET);
		// TODO: Click at Pixel currently not working as Expected.
		//surveyViewPageAction.waitForPeakInfoPopupToOpen(EMPTY, NOTSET);
	}
}