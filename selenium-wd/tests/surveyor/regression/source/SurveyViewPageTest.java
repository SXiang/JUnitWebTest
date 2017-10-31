package surveyor.regression.source;

import static org.junit.Assert.*;
import common.source.Log;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.Test;
import surveyor.scommon.source.SurveyorTestRunner;

import surveyor.scommon.source.SurveyViewPage;
import static surveyor.scommon.source.SurveyorConstants.ISOTOPIC_CAPTURE_CANCELLED;
import static surveyor.scommon.source.SurveyorConstants.ISOTOPIC_CAPTURE_NOT_NATURAL_GAS;
import static surveyor.scommon.source.SurveyorConstants.REFGAS_CAPTURE_FAIL;
import java.io.IOException;
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
public class SurveyViewPageTest extends BaseSurveyViewPageTest {

	public SurveyViewPageTest() throws IOException {
		super();
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnIndications(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnLisas(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_RAPID_RESP_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_RAPID_RESP_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_RAPID_RESP_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnLisas(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_RAPID_RESP_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_RAPID_RESP_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_RAPID_RESP_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnFOVs(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnIndications(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_RAPID_RESP_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_RAPID_RESP_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_RAPID_RESP_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		getSurveyViewPageAction().getSurveyViewPage().setZoomLevel(19);
		assertTrue(getSurveyViewPageAction().verifyAssetIsShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnFOVs(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnIndications(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_RAPID_RESP_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_RAPID_RESP_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_RAPID_RESP_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		getSurveyViewPageAction().getSurveyViewPage().setZoomLevel(19);
		assertTrue(getSurveyViewPageAction().verifyAssetIsShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssets(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMaterialTypeProtectedSteel(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMaterialTypeUnprotectedSteel(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		// TODO: Specific asset check (Currently no mechanism to detect from client-side). Might need some server-side product changes.
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssets(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMaterialTypeOtherPlastic(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMaterialTypeCopper(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		// TODO: Specific asset check (Currently no mechanism to detect from client-side). Might need some server-side product changes.
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnLisas(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnLisas(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnFOVs(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnFOVs(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnIsotopicAnalysis(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyRefGasCaptureResultIsPresentOnMap(REFGAS_CAPTURE_FAIL, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnIsotopicAnalysis(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyRefGasCaptureResultIsPresentOnMap(REFGAS_CAPTURE_FAIL, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnIndications(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));

		getSurveyViewPageAction().getSurveyViewPage().setZoomLevel(19);
		assertTrue(getSurveyViewPageAction().verifyAssetIsShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnFOVs(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));

		getSurveyViewPageAction().getSurveyViewPage().setZoomLevel(19);
		assertTrue(getSurveyViewPageAction().verifyAssetIsShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllAssets(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllBoundaries(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));

		getSurveyViewPageAction().getSurveyViewPage().setZoomLevel(19);
		assertTrue(getSurveyViewPageAction().verifyAssetIsShownOnMap(EMPTY, NOTSET));

		// Use the second manual survey for Field notes verification.
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL2_ID, NOTSET);
		getSurveyViewPageAction().refreshPage(EMPTY, NOTSET);	// refresh the page to re-initialize the page object.
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllAssets(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllBoundaries(EMPTY, NOTSET);
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssets(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllBoundaries(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));

		// Use the second manual survey for Field notes verification.
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL2_ID, NOTSET);
		getSurveyViewPageAction().refreshPage(EMPTY, NOTSET);	// refresh the page to re-initialize the page object.
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssets(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllBoundaries(EMPTY, NOTSET);
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnLisas(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnIndications(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));

		getSurveyViewPageAction().getSurveyViewPage().setZoomLevel(19);
		assertTrue(getSurveyViewPageAction().verifyAssetIsShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnIndications(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));

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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnLisas(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));

		// Use the second manual survey for Field notes verification.
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL2_ID, NOTSET);
		getSurveyViewPageAction().refreshPage(EMPTY, NOTSET);	// refresh the page to re-initialize the page object.
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnLisas(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnLisas(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));

		// Use the second manual survey for Field notes verification.
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL2_ID, NOTSET);
		getSurveyViewPageAction().refreshPage(EMPTY, NOTSET);	// refresh the page to re-initialize the page object.
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnLisas(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnFOVs(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnIndications(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		getSurveyViewPageAction().getSurveyViewPage().setZoomLevel(19);
		assertTrue(getSurveyViewPageAction().verifyAssetIsShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnFOVs(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnIndications(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));

		getSurveyViewPageAction().getSurveyViewPage().setZoomLevel(19);
		assertTrue(getSurveyViewPageAction().verifyAssetIsShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMaterialTypeCopper(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMaterialTypeCastIron(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));

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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMaterialTypePEPlastic(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMaterialTypeProtectedSteel(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));

		// TODO: Assets don't have unique IDs in UI. Specific assets checks cannot be currently done from UI.
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnLisas(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnLisas(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnFOVs(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL2_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnFOVs(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
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
	public void TC1033_SurveyView_ViewManualSurveyMapViewWhenFieldNotesONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1033_SurveyView_ViewManualSurveyMapViewWhenFieldNotesONAssetsBoundariesOFF ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL2_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
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
	public void TC1034_SurveyView_ViewManualSurveySatelliteViewWhenFieldNotesONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC1034_SurveyView_ViewManualSurveySatelliteViewWhenFieldNotesONAssetsBoundariesOFF ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL2_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnIsotopicAnalysis(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnIsotopicAnalysis(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		getSurveyViewPageAction().showCurtainView(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnCurtainReturnButton(EMPTY, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyMapViewIsShown(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		getSurveyViewPageAction().showCurtainView(EMPTY, NOTSET);
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
		getSurveyViewPageAction().showCurtainView(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnCurtainReturnButton(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsNotPresentOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		String startTimeTextBeforeRefresh = ((SurveyViewPage)getSurveyViewPageAction().getPageObject()).getStartTimeLabelText();
		String endTimeTextBeforeRefresh = ((SurveyViewPage)getSurveyViewPageAction().getPageObject()).getEndTimeLabelText();
		getTestEnvironmentAction().idleForSeconds(String.valueOf(60), NOTSET);
		getSurveyViewPageAction().refreshPage(EMPTY, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		String startTimeTextAfterRefresh = ((SurveyViewPage)getSurveyViewPageAction().getPageObject()).getStartTimeLabelText();
		String endTimeTextAfterRefresh = ((SurveyViewPage)getSurveyViewPageAction().getPageObject()).getEndTimeLabelText();
		assertTrue(startTimeTextAfterRefresh.equals(startTimeTextBeforeRefresh));
		assertTrue(endTimeTextAfterRefresh.equals(endTimeTextBeforeRefresh));
		assertTrue(getSurveyViewPageAction().verifySatelliteViewIsShown(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_ASSESSMENT1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_ASSESSMENT1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_ASSESSMENT1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ASSESSMENT_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_ASSESSMENT_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		String startTimeTextBeforeRefresh = ((SurveyViewPage)getSurveyViewPageAction().getPageObject()).getStartTimeLabelText();
		String endTimeTextBeforeRefresh = ((SurveyViewPage)getSurveyViewPageAction().getPageObject()).getEndTimeLabelText();
		getTestEnvironmentAction().idleForSeconds(String.valueOf(60), NOTSET);
		getSurveyViewPageAction().refreshPage(EMPTY, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		String startTimeTextAfterRefresh = ((SurveyViewPage)getSurveyViewPageAction().getPageObject()).getStartTimeLabelText();
		String endTimeTextAfterRefresh = ((SurveyViewPage)getSurveyViewPageAction().getPageObject()).getEndTimeLabelText();
		assertTrue(startTimeTextAfterRefresh.equals(startTimeTextBeforeRefresh));
		assertTrue(endTimeTextAfterRefresh.equals(endTimeTextBeforeRefresh));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_ASSESSMENT1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_ASSESSMENT1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_ASSESSMENT1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ASSESSMENT_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_ASSESSMENT_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnFOVs(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_ASSESSMENT1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_ASSESSMENT1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_ASSESSMENT1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ASSESSMENT_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_ASSESSMENT_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnFOVs(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_ASSESSMENT1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_ASSESSMENT1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_ASSESSMENT1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ASSESSMENT_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_ASSESSMENT_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnFOVs(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMaterialTypeOtherPlastic(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMaterialTypeProtectedSteel(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMaterialTypeCastIron(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_ASSESSMENT1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_ASSESSMENT1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_ASSESSMENT1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ASSESSMENT_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_ASSESSMENT_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));

		// TODO: Assets don't have unique IDs in UI. Specific assets checks cannot be currently done from UI.
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnFOVs(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMaterialTypePEPlastic(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMaterialTypeUnprotectedSteel(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMaterialTypeCopper(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_ASSESSMENT1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_ASSESSMENT1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_ASSESSMENT1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ASSESSMENT_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_ASSESSMENT_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));

		// TODO: Assets don't have unique IDs in UI. Specific assets checks cannot be currently done from UI.
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffFOVs(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_ASSESSMENT1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_ASSESSMENT1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_ASSESSMENT1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ASSESSMENT_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_ASSESSMENT_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffFOVs(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_ASSESSMENT1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_ASSESSMENT1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_ASSESSMENT1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ASSESSMENT_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_ASSESSMENT_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_ETHANE_SURVEY_STANDARD_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		// wait for elements to paint on the map.
		getTestEnvironmentAction().idleForSeconds(String.valueOf(10), NOTSET);
	}

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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_ETHANE_SURVEY_STANDARD_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		// wait for elements to paint on the map.
		getTestEnvironmentAction().idleForSeconds(String.valueOf(10), NOTSET);
		getSurveyViewPageAction().clickOnFirst3300IndicationShownOnMap("NaturalGas", NOTSET);
		// TODO: Click at Pixel currently not working as Expected.
		//surveyViewPageAction.waitForFeatureInfoPopupToOpen(EMPTY, NOTSET);
	}
}