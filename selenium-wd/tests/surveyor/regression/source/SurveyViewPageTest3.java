package surveyor.regression.source;

import org.junit.Test;

import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.SurveyViewPage;
import static org.junit.Assert.*;
import common.source.Log;
import static surveyor.scommon.source.SurveyorConstants.ISOTOPIC_CAPTURE_CANCELLED;
import static surveyor.scommon.source.SurveyorConstants.ISOTOPIC_CAPTURE_NOT_NATURAL_GAS;

import java.io.IOException;

public class SurveyViewPageTest3 extends BaseSurveyViewPageTest {

	public SurveyViewPageTest3() throws IOException {
		super();
	}

	/* * Test Case ID: TC428_SurveyView_ViewIndicationsRapidResponseSurveyWhenNoGISLoaded
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- Indications - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 * Results: -
	 *	- - Breadcrumb and Indications are displayed on map
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC428_SurveyView_ViewIndicationsRapidResponseSurveyWhenNoGISLoaded() throws Exception {
		Log.info("\nRunning TC428_SurveyView_ViewIndicationsRapidResponseSurveyWhenNoGISLoaded ...");

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
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC429_SurveyView_ViewAllDataManualSurveyWhenGISDataLoadedAllDisplayOptionsAreON
	 * Script: -
	 *	- -On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All ON
	 *	- - Map View: Map
	 *	- - GIS: All ON
	 * Results: -
	 *	- - Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map
	 *	- - All pipes and boundaries data are displayed
	 */
	@Test
	public void TC429_SurveyView_ViewAllDataManualSurveyWhenGISDataLoadedAllDisplayOptionsAreON() throws Exception {
		Log.info("\nRunning TC429_SurveyView_ViewAllDataManualSurveyWhenGISDataLoadedAllDisplayOptionsAreON ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllDisplayOptions(EMPTY, NOTSET);
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
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

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
		getSurveyViewPageAction().turnOffAllAssets(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllBoundaries(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyFieldNotesIsShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
	}

	/**
	 * Test Case ID: TC430_SurveyView_ViewManualSurveyWhenGISDisplayOptionsAreOFF
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All OFF
	 *	- - Map View: Map
	 *	- - GIS: All OFF
	 * Results: -
	 *	- - User should see only Breadcrumb in map view
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC430_SurveyView_ViewManualSurveyWhenGISDisplayOptionsAreOFF() throws Exception {
		Log.info("\nRunning TC430_SurveyView_ViewManualSurveyWhenGISDisplayOptionsAreOFF ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC431_SurveyView_ViewAllDataSatelliteViewManualSurveyWhenGISDataLoadedAllDisplayOptionsAreON
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All ON
	 *	- - Map View: Satellite
	 *	- - GIS: All ON
	 * Results: -
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map in satellite view
	 *	- - All pipes and boundaries data are displayed
	 */
	@Test
	public void TC431_SurveyView_ViewAllDataSatelliteViewManualSurveyWhenGISDataLoadedAllDisplayOptionsAreON() throws Exception {
		Log.info("\nRunning TC431_SurveyView_ViewAllDataSatelliteViewManualSurveyWhenGISDataLoadedAllDisplayOptionsAreON ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllDisplayOptions(EMPTY, NOTSET);
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
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

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
		getSurveyViewPageAction().turnOffAllAssets(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllBoundaries(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyFieldNotesIsShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));


	}

	/**
	 * Test Case ID: TC432_SurveyView_ViewIndicationsDataManualSurveyWhenGISDataLoaded
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: Indication ON
	 *	- All other options OFF
	 *	- - Map View: Map
	 *	- - GIS: All ON
	 * Results: -
	 * --Indications and breadcrumb is displayed on map along with assets and boundaries data
	 *	- - All other survey data is not displayed
	 */
	@Test
	public void TC432_SurveyView_ViewIndicationsDataManualSurveyWhenGISDataLoaded() throws Exception {
		Log.info("\nRunning TC432_SurveyView_ViewIndicationsDataManualSurveyWhenGISDataLoaded ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnIndications(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));

		getSurveyViewPageAction().getSurveyViewPage().setZoomLevel(19);
		assertTrue(getSurveyViewPageAction().verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC433_SurveyView_ViewFOVDataManualSurveyWhenGISDataLoaded
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: FOV ON
	 *	- All other options OFF
	 *	- - Map View: Map
	 *	- - GIS: All ON
	 * Results: -
	 * --FOV and breadcrumb is displayed on map along with assets and boundaries data
	 *	- - All other survey data is not displayed
	 */
	@Test
	public void TC433_SurveyView_ViewFOVDataManualSurveyWhenGISDataLoaded() throws Exception {
		Log.info("\nRunning TC433_SurveyView_ViewFOVDataManualSurveyWhenGISDataLoaded ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnFOVs(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));

		getSurveyViewPageAction().getSurveyViewPage().setZoomLevel(19);
		assertTrue(getSurveyViewPageAction().verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC434_SurveyView_ViewFOVIndicationsLisasDataManualSurveyWhenAssetsAreLoaded
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All ON
	 *	- - Map View: Map
	 *	- - GIS: Assets ON
	 *	- Boundaries OFF
	 * Results: -
	 *	- - Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map
	 *	- - All pipes data is displayed. Boundaries are not present
	 */
	@Test
	public void TC434_SurveyView_ViewFOVIndicationsLisasDataManualSurveyWhenAssetsAreLoaded() throws Exception {
		Log.info("\nRunning TC434_SurveyView_ViewFOVIndicationsLisasDataManualSurveyWhenAssetsAreLoaded ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllAssets(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllBoundaries(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
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
		getSurveyViewPageAction().turnOffAllAssets(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllBoundaries(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyFieldNotesIsShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));


	}

	/**
	 * Test Case ID: TC435_SurveyView_ViewFOVIndicationsLisasDataManualSurveyWhenBoundariesLoadedIntoGIS
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All ON
	 *	- - Map View: Map
	 *	- - GIS: Assets OFF
	 *	- Boundaries ON
	 * Results: -
	 *	- - Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map
	 *	- - Only boundaries are displayed. Assets are not displayed
	 */
	@Test
	public void TC435_SurveyView_ViewFOVIndicationsLisasDataManualSurveyWhenBoundariesLoadedIntoGIS() throws Exception {
		Log.info("\nRunning TC435_SurveyView_ViewFOVIndicationsLisasDataManualSurveyWhenBoundariesLoadedIntoGIS ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssets(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

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
		assertTrue(getSurveyViewPageAction().verifyFieldNotesIsShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));


	}

	/**
	 * Test Case ID: TC436_SurveyView_ViewIndicationsLisasDataManualSurveyWhenGISDataLoaded
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: Indications ON
	 *	- LISA ON
	 *	- All other options OFF
	 *	- - Map View: Map
	 *	- - GIS: All ON
	 * Results: -
	 *	- -  Indications, Lisa and breadcrumb is displayed on map along with assets and boundaries data
	 *  - - All other survey data is not displayed
	 */
	@Test
	public void TC436_SurveyView_ViewIndicationsLisasDataManualSurveyWhenGISDataLoaded() throws Exception {
		Log.info("\nRunning TC436_SurveyView_ViewIndicationsLisasDataManualSurveyWhenGISDataLoaded ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnIndications(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnLisas(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));

		getSurveyViewPageAction().getSurveyViewPage().setZoomLevel(19);
		assertTrue(getSurveyViewPageAction().verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC437_SurveyView_ViewIndicationsManualSurveyWhenNoGISLoaded
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- Indications - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 * Results: -
	 *	- - Breadcrumb and Indications are displayed on map
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC437_SurveyView_ViewIndicationsManualSurveyWhenNoGISLoaded() throws Exception {
		Log.info("\nRunning TC437_SurveyView_ViewIndicationsManualSurveyWhenNoGISLoaded ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnIndications(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC931_PositionConcentrationChartWindRose8_HourHistoryOptionsAreNotPresentSurveyView
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click on Display button
	 * Results: -
	 *  - - Position button not present in survey view
	 *	- - Concentration Chart, Wind rose options are not present in survey view
	 */
	@Test
	public void TC931_PositionConcentrationChartWindRose8_HourHistoryOptionsAreNotPresentSurveyView() throws Exception {
		Log.info("\nRunning TC931_PositionConcentrationChartWindRose8_HourHistoryOptionsAreNotPresentSurveyView ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyDisplaySwitchConcentrationChartButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyDisplaySwitchWindroseButtonIsNotVisible(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC932_SurveyView_CheckZoomControlsSurveyViewWhenSurveyDataLoaded_SatelliteView
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click on Map and turn Satellite View ON
	 *	- - Click on Display
	 *	- All options ON
	 *	- - Zoom in by 2-3 levels (clicking on + icon present at top left corner near Tag value)
	 *	- - Zoom out by 2-3 levels (clicking on - icon present at top left corner near Tag value)
	 * Results: -
	 * --Survey data is displayed in satellite view at every zoom level
	 */
	@Test
	public void TC932_SurveyView_CheckZoomControlsSurveyViewWhenSurveyDataLoaded_SatelliteView() throws Exception {
		Log.info("\nRunning TC932_SurveyView_CheckZoomControlsSurveyViewWhenSurveyDataLoaded_SatelliteView ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomInButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomInButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getTestEnvironmentAction().idleForSeconds(String.valueOf(10), NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC933_SurveyView_CheckZoomControlsSurveyViewWhenSurveyDataLoaded_MapView
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click on Map and turn Map View ON
	 *	- - Click on Display
	 *	- All options ON
	 *	- - Zoom in by 2-3 levels (clicking on + icon present at top left corner near Tag value)
	 *	- - Zoom out by 2-3 levels (clicking on - icon present at top left corner near Tag value)
	 * Results: -
	 * --Survey data is displayed in map view at every zoom level
	 */
	@Test
	public void TC933_SurveyView_CheckZoomControlsSurveyViewWhenSurveyDataLoaded_MapView() throws Exception {
		Log.info("\nRunning TC933_SurveyView_CheckZoomControlsSurveyViewWhenSurveyDataLoaded_MapView ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomInButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomInButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC934_SurveyView_CheckZoomLevelUsingMouseControlsSurveyViewWhenSurveyDataLoaded_Map
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click on Map and turn Map View ON
	 *	- - Click on Display
	 *	- All options ON
	 *	- - Zoom in using mouse controls
	 *	- - Zoom out using mouse controls
	 * Results: -
	 * -- Survey data is displayed in map view at every zoom level
	 */
	@Test
	public void TC934_SurveyView_CheckZoomLevelUsingMouseControlsSurveyViewWhenSurveyDataLoaded_Map() throws Exception {
		Log.info("\nRunning TC934_SurveyView_CheckZoomLevelUsingMouseControlsSurveyViewWhenSurveyDataLoaded_Map ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomInButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC935_SurveyView_CheckZoomLevelUsingMouseControlsSurveyViewWhenSurveyDataLoaded_Satellite
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click on Map and turn Satellite View ON
	 *	- - Click on Display
	 *	- All options ON
	 *	- - Zoom in using mouse controls
	 *	- - Zoom out using mouse controls
	 * Results: -
	 * --Survey data is displayed in satellite view at every zoom level
	 */
	@Test
	public void TC935_SurveyView_CheckZoomLevelUsingMouseControlsSurveyViewWhenSurveyDataLoaded_Satellite() throws Exception {
		Log.info("\nRunning TC935_SurveyView_CheckZoomLevelUsingMouseControlsSurveyViewWhenSurveyDataLoaded_Satellite ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomInButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
	}
	/**
	 * Test Case ID: TC936_SurveyView_CurtainViewDisplayedUserCanNavigateIt
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click on Curtain button
	 *	- - Click on Return button
	 * Results: -
	 *  - - Capture results are not displayed
     *	- - User is returned back to survey view
	 */
	@Test
	public void TC936_SurveyView_CurtainViewDisplayedUserCanNavigateIt() throws Exception {
		Log.info("\nRunning TC936_SurveyView_CurtainViewDisplayedUserCanNavigateIt ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().showCurtainView(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnCurtainReturnButton(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsNotPresentOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_NATURAL_GAS, NOTSET));
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC937_SurveyView_UserAllowedMoveUpDownLeftRightZoomInZoomOutCurtainView
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click on Curtain button
	 *	- - Click Up
	 *	- - Click Right
	 *	- - Click Zoom In
	 *	- - Click Zoom Out
	 *	- - Click Return
	 * Results: -
	 * -- Spikes are displayed in Curtain View
	 * -- User will be rotated as instructed
     * -- User will be zoomed in or out as instructed
     * -- User will be returned to Survey View page
	 */
	@Test
	public void TC937_SurveyView_UserAllowedMoveUpDownLeftRightZoomInZoomOutCurtainView() throws Exception {
		Log.info("\nRunning TC937_SurveyView_UserAllowedMoveUpDownLeftRightZoomInZoomOutCurtainView ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().showCurtainView(EMPTY, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnCurtainArrowUpButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnCurtainArrowRightButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnCurtainZoomInButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnCurtainZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnCurtainReturnButton(EMPTY, NOTSET);
		getSurveyViewPageAction().verifySurveyViewPageIsOpened(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC938_SurveyView_NavigateBackForthBetweenSurveyViewHomePage
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 * Results: -
	 *	- - Click on Picarro Icon present at bottom
	 *	- - User is navigated to home page
	 */
	@Test
	public void TC938_SurveyView_NavigateBackForthBetweenSurveyViewHomePage() throws Exception {
		Log.info("\nRunning TC938_SurveyView_NavigateBackForthBetweenSurveyViewHomePage ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET));
		getSurveyViewPageAction().clickOnPicarroLogoButton(EMPTY, NOTSET);
		assertTrue(getHomePageAction().verifyPageLoaded(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC939_SurveyView_CheckSurveyDataPresentAfterUserRefreshesBrowser_Satellite
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click on Map and turn Satellite View ON
	 *	- - Refresh the browser after couple of mins
	 * Results: -
	 *	- - Survey details - Tag, Mode, Driver Name, Stability Class, Start Time, End Time, Surveyor Name should be present in satellite view
	 *  - - Start and End Time should not reset after the refresh.
	 */
	@Test
	public void TC939_SurveyView_CheckSurveyDataPresentAfterUserRefreshesBrowser_Satellite() throws Exception {
		Log.info("\nRunning TC939_SurveyView_CheckSurveyDataPresentAfterUserRefreshesBrowser_Satellite ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		getSurveyViewPageAction().refreshPage(EMPTY, NOTSET);
		String startTimeTextBeforeRefresh = ((SurveyViewPage)getSurveyViewPageAction().getPageObject()).getStartTimeLabelText();
		String endTimeTextBeforeRefresh = ((SurveyViewPage)getSurveyViewPageAction().getPageObject()).getEndTimeLabelText();
		getTestEnvironmentAction().idleForSeconds(String.valueOf(60), NOTSET);
		getSurveyViewPageAction().refreshPage(EMPTY, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		String startTimeTextAfterRefresh = ((SurveyViewPage)getSurveyViewPageAction().getPageObject()).getStartTimeLabelText();
		String endTimeTextAfterRefresh = ((SurveyViewPage)getSurveyViewPageAction().getPageObject()).getEndTimeLabelText();
		assertTrue(startTimeTextAfterRefresh.equals(startTimeTextBeforeRefresh));
		assertTrue(endTimeTextAfterRefresh.equals(endTimeTextBeforeRefresh));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_STANDARD1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_STANDARD1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_STANDARD1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
	}

	/**
	 * Test Case ID: TC940_SurveyView_CheckSurveyDataPresentAfterUserRefreshesBrowser_Map
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click on Map and turn Map View ON
	 *	- - Refresh the browser after couple of mins
	 *	- - Click on Map and turn Map View ON
	 * Results: -
	 *	- - Survey details - Tag, Mode, Driver Name, Stability Class, Start Time, End Time, Surveyor Name should be present in map view
	 *  - - Start and End Time should not reset after the refresh.
	 */
	@Test
	public void TC940_SurveyView_CheckSurveyDataPresentAfterUserRefreshesBrowser_Map() throws Exception {
		Log.info("\nRunning TC940_SurveyView_CheckSurveyDataPresentAfterUserRefreshesBrowser_Map ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		getSurveyViewPageAction().refreshPage(EMPTY, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		String startTimeTextBeforeRefresh = ((SurveyViewPage)getSurveyViewPageAction().getPageObject()).getStartTimeLabelText();
		String endTimeTextBeforeRefresh = ((SurveyViewPage)getSurveyViewPageAction().getPageObject()).getEndTimeLabelText();
		getTestEnvironmentAction().idleForSeconds(String.valueOf(60), NOTSET);
		getSurveyViewPageAction().refreshPage(EMPTY, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		String startTimeTextAfterRefresh = ((SurveyViewPage)getSurveyViewPageAction().getPageObject()).getStartTimeLabelText();
		String endTimeTextAfterRefresh = ((SurveyViewPage)getSurveyViewPageAction().getPageObject()).getEndTimeLabelText();
		assertTrue(startTimeTextAfterRefresh.equals(startTimeTextBeforeRefresh));
		assertTrue(endTimeTextAfterRefresh.equals(endTimeTextBeforeRefresh));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_STANDARD1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_STANDARD1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_STANDARD1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
	}

	/**
	 * Test Case ID: TC941_SurveyView_ViewOperatorSurveySatelliteViewWhenAllGISDisplayOptionsAreTurnedOFF
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey (Operator survey)
	 *	- - Display: All OFF
	 *	- - Map View: Satellite
	 *	- - GIS: All OFF
	 * Results: -
	 * 	- - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Stability Class, Surveyor and analyzer info, Start Time, End Time
	 *  - - User should see only Breadcrumb on map in satellite view
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC941_SurveyView_ViewOperatorSurveySatelliteViewWhenAllGISDisplayOptionsAreTurnedOFF() throws Exception {
		Log.info("\nRunning TC941_SurveyView_ViewOperatorSurveySatelliteViewWhenAllGISDisplayOptionsAreTurnedOFF ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_OPERATOR1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_OPERATOR1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_OPERATOR1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC942_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenBoundariesDataLoadedSatelliteView
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey (Operator survey)
	 *	- - Display: All ON
	 *	- - Map View: Satellite
	 *	- - GIS: Assets OFF
	 *	- Boundaries ON
	 * Results: -
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map in satellite view
	 *	- - Only boundaries are displayed. Assets are not displayed
	 */
	@Test
	public void TC942_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenBoundariesDataLoadedSatelliteView() throws Exception {
		Log.info("\nRunning TC942_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenBoundariesDataLoadedSatelliteView ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);    /*Picarro Admin*/
		getSurveyViewPageAction().open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllAssets (EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllBoundaries(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_OPERATOR1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_OPERATOR1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_OPERATOR1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFieldNotesIsShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC943_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenAssetsAreLoadedSatelliteView
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey (Operator survey)
	 *	- - Display: All ON
	 *	- - Map View: Satellite
	 *	- - GIS: Assets ON
	 *	- Boundaries OFF
	 * Results: -
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map in satellite view
	 *	- - All pipes data is displayed. Boundaries are not present
	 */
	@Test
	public void TC943_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenAssetsAreLoadedSatelliteView() throws Exception {
		Log.info("\nRunning TC943_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenAssetsAreLoadedSatelliteView ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllAssets(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomInButton(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_OPERATOR1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_OPERATOR1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_OPERATOR1_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFieldNotesIsShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));

		getSurveyViewPageAction().getSurveyViewPage().setZoomLevel(19);
		assertTrue(getSurveyViewPageAction().verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC944_SurveyView_ViewIndicationsDataSatelliteViewOperatorSurveyWhenBoundariesPipeLoaded
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: Indication ON
	 *	- All other options OFF
	 *	- - Map View: Satellite
	 *	- - GIS: All ON
	 * Results: -
	 *  - - Indications and breadcrumb is displayed on map in satellite view along with assets and boundaries data for operator survey
	 *	- - All other survey data is not displayed
	 */
	@Test
	public void TC944_SurveyView_ViewIndicationsDataSatelliteViewOperatorSurveyWhenBoundariesPipeLoaded() throws Exception {
		Log.info("\nRunning TC944_SurveyView_ViewIndicationsDataSatelliteViewOperatorSurveyWhenBoundariesPipeLoaded ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnIndications(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));

		getSurveyViewPageAction().getSurveyViewPage().setZoomLevel(19);
		assertTrue(getSurveyViewPageAction().verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC945_SurveyView_ViewFOVDataSatelliteViewOperatorSurveyWhenGISDataLoaded
	 * Script: -
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: FOV ON
	 *	- All other options OFF
	 *	- - Map View: Satellite
	 *	- - GIS: All ON
	 * Results: -
	 *  - - FOV and breadcrumb is displayed on map in satellite view along with assets and boundaries data for operator survey
	 *	- - All other survey data is not displayed
	 */
	@Test
	public void TC945_SurveyView_ViewFOVDataSatelliteViewOperatorSurveyWhenGISDataLoaded() throws Exception {
		Log.info("\nRunning TC945_SurveyView_ViewFOVDataSatelliteViewOperatorSurveyWhenGISDataLoaded ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
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
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));

		getSurveyViewPageAction().getSurveyViewPage().setZoomLevel(19);
		assertTrue(getSurveyViewPageAction().verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}
}