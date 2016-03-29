package surveyor.regression.source;

import org.junit.Ignore;
import org.junit.Test;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import static org.junit.Assert.*;
import common.source.Log;

public class SurveyViewPageTest3 extends SurveyViewPageTest2 {

	public SurveyViewPageTest3() {
		super();
	}

	/**
	 * Test Case ID: TC429_SurveyView_ViewAllDataManualSurveyWhenGISDataLoadedAllDisplayOptionsAreON
	 * Script: -  	
	 *	- -On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All ON
	 *	- - Map View: Map
	 *	- - GIS: All ON
	 *	- - Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map
	 * Results: - 
	 *	- - All pipes and boundaries data are displayed
	 */
	@Test
	public void TC429_SurveyView_ViewAllDataManualSurveyWhenGISDataLoadedAllDisplayOptionsAreON() throws Exception {
		Log.info("\nRunning TC429_SurveyView_ViewAllDataManualSurveyWhenGISDataLoadedAllDisplayOptionsAreON ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

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
	 * Test Case ID: TC430_SurveyView_ViewManualSurveyWhenGISDisplayOptionsAreOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All OFF
	 *	- - Map View: Map
	 *	- - GIS: All OFF
	 *	- - User should see only Breadcrumb in map view
	 * Results: - 
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC430_SurveyView_ViewManualSurveyWhenGISDisplayOptionsAreOFF() throws Exception {
		Log.info("\nRunning TC430_SurveyView_ViewManualSurveyWhenGISDisplayOptionsAreOFF ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC431_SurveyView_ViewAllDataSatelliteViewManualSurveyWhenGISDataLoadedAllDisplayOptionsAreON
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All ON
	 *	- - Map View: Satellite
	 *	- - GIS: All ON
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map in satellite view
	 * Results: - 
	 *	- - All pipes and boundaries data are displayed
	 */
	@Test
	public void TC431_SurveyView_ViewAllDataSatelliteViewManualSurveyWhenGISDataLoadedAllDisplayOptionsAreON() throws Exception {
		Log.info("\nRunning TC431_SurveyView_ViewAllDataSatelliteViewManualSurveyWhenGISDataLoadedAllDisplayOptionsAreON ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

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
	 * Test Case ID: TC432_SurveyView_ViewIndicationsDataManualSurveyWhenGISDataLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: Indication ON
	 *	- All other options OFF
	 *	- - Map View: Map
	 *	- - GIS: All ON
	 * Results: - 
	 *	- - All other survey data is not displayed
	 */
	@Test
	public void TC432_SurveyView_ViewIndicationsDataManualSurveyWhenGISDataLoaded() throws Exception {
		Log.info("\nRunning TC432_SurveyView_ViewIndicationsDataManualSurveyWhenGISDataLoaded ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
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
	 *	- - All other survey data is not displayed
	 */
	@Test
	public void TC433_SurveyView_ViewFOVDataManualSurveyWhenGISDataLoaded() throws Exception {
		Log.info("\nRunning TC433_SurveyView_ViewFOVDataManualSurveyWhenGISDataLoaded ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
	}

	/**
	 * Test Case ID: TC434_SurveyView_ViewFOVIndicationsLisasDataManualSurveyWhenAssetsAreLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All ON
	 *	- - Map View: Map
	 *	- - GIS: Assets ON
	 *	- Boundaries OFF
	 *	- - Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map
	 * Results: - 
	 *	- - All pipes data is displayed. Boundaries are not present
	 */
	@Test
	public void TC434_SurveyView_ViewFOVIndicationsLisasDataManualSurveyWhenAssetsAreLoaded() throws Exception {
		Log.info("\nRunning TC434_SurveyView_ViewFOVIndicationsLisasDataManualSurveyWhenAssetsAreLoaded ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssets(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
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
		surveyViewPageAction.turnOffAllAssets(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllBoundaries(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));


	}

	/**
	 * Test Case ID: TC435_SurveyView_ViewFOVIndicationsLisasDataManualSurveyWhenBoundariesLoadedIntoGIS
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All ON
	 *	- - Map View: Map
	 *	- - GIS: Assets OFF
	 *	- Boundaries ON
	 *	- - Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map
	 * Results: - 
	 *	- - Only boundaries are displayed. Assets are not displayed
	 */
	@Test
	public void TC435_SurveyView_ViewFOVIndicationsLisasDataManualSurveyWhenBoundariesLoadedIntoGIS() throws Exception {
		Log.info("\nRunning TC435_SurveyView_ViewFOVIndicationsLisasDataManualSurveyWhenBoundariesLoadedIntoGIS ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssets(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_MANUAL1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_MANUAL1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_MANUAL1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));

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
	 * Test Case ID: TC436_SurveyView_ViewIndicationsLisasDataManualSurveyWhenGISDataLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: Indications ON
	 *	- LISA ON
	 *	- All other options OFF
	 *	- - Map View: Map
	 *	- - GIS: All ON
	 * Results: - 
	 *	- - All other survey data is not displayed
	 */
	@Test
	public void TC436_SurveyView_ViewIndicationsLisasDataManualSurveyWhenGISDataLoaded() throws Exception {
		Log.info("\nRunning TC436_SurveyView_ViewIndicationsLisasDataManualSurveyWhenGISDataLoaded ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
		surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
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
	 *	- - Breadcrumb and Indications are displayed on map
	 * Results: - 
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC437_SurveyView_ViewIndicationsManualSurveyWhenNoGISLoaded() throws Exception {
		Log.info("\nRunning TC437_SurveyView_ViewIndicationsManualSurveyWhenNoGISLoaded ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_MANUAL1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC931_PositionConcentrationChartWindRose8_HourHistoryOptionsAreNotPresentSurveyView
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click on Display button
	 * Results: - 
	 *	- - Concentration Chart, Wind rose, 8-hour history options are not present in survey view
	 */
	@Test
	public void TC931_PositionConcentrationChartWindRose8_HourHistoryOptionsAreNotPresentSurveyView() throws Exception {
		Log.info("\nRunning TC931_PositionConcentrationChartWindRose8_HourHistoryOptionsAreNotPresentSurveyView ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyDisplaySwitch8HourHistoryButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyDisplaySwitchConcentrationChartButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyDisplaySwitchWindroseButtonIsNotVisible(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC932_SurveyView_CheckZoomControlsSurveyViewWhenSurveyDataLoaded_SatelliteView
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click on Map and turn Satellite View ON
	 *	- - Click on Display
	 *	- All options ON
	 *	- - Zoom in by 2-3 levels (clicking on + icon present at top left corner near Tag value)
	 * Results: - 
	 *	- - Zoom out by 2-3 levels (clicking on - icon present at top left corner near Tag value)
	 */
	@Test
	public void TC932_SurveyView_CheckZoomControlsSurveyViewWhenSurveyDataLoaded_SatelliteView() throws Exception {
		Log.info("\nRunning TC932_SurveyView_CheckZoomControlsSurveyViewWhenSurveyDataLoaded_SatelliteView ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomInButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomInButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC933_SurveyView_CheckZoomControlsSurveyViewWhenSurveyDataLoaded_MapView
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click on Map and turn Map View ON
	 *	- - Click on Display
	 *	- All options ON
	 *	- - Zoom in by 2-3 levels (clicking on + icon present at top left corner near Tag value)
	 * Results: - 
	 *	- - Zoom out by 2-3 levels (clicking on - icon present at top left corner near Tag value)
	 */
	@Test
	public void TC933_SurveyView_CheckZoomControlsSurveyViewWhenSurveyDataLoaded_MapView() throws Exception {
		Log.info("\nRunning TC933_SurveyView_CheckZoomControlsSurveyViewWhenSurveyDataLoaded_MapView ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomInButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomInButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC934_SurveyView_CheckZoomLevelUsingMouseControlsSurveyViewWhenSurveyDataLoaded_Map
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click on Map and turn Map View ON
	 *	- - Click on Display
	 *	- All options ON
	 *	- - Zoom in using mouse controls
	 * Results: - 
	 *	- - Zoom out using mouse controls
	 */
	@Test
	public void TC934_SurveyView_CheckZoomLevelUsingMouseControlsSurveyViewWhenSurveyDataLoaded_Map() throws Exception {
		Log.info("\nRunning TC934_SurveyView_CheckZoomLevelUsingMouseControlsSurveyViewWhenSurveyDataLoaded_Map ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomInButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC935_SurveyView_CheckZoomLevelUsingMouseControlsSurveyViewWhenSurveyDataLoaded_Satellite
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click on Map and turn Satellite View ON
	 *	- - Click on Display
	 *	- All options ON
	 *	- - Zoom in using mouse controls
	 * Results: - 
	 *	- - Zoom out using mouse controls
	 */
	@Test
	public void TC935_SurveyView_CheckZoomLevelUsingMouseControlsSurveyViewWhenSurveyDataLoaded_Satellite() throws Exception {
		Log.info("\nRunning TC935_SurveyView_CheckZoomLevelUsingMouseControlsSurveyViewWhenSurveyDataLoaded_Satellite ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomInButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC936_SurveyView_CurtainViewDisplayedUserCanNavigateIt
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click on Curtain button
	 *	- - Click on Return button
	 *	- - Capture results are not displayed
	 * Results: - 
	 *	- - User is returned back to survey view
	 */
	@Test
	public void TC936_SurveyView_CurtainViewDisplayedUserCanNavigateIt() throws Exception {
		Log.info("\nRunning TC936_SurveyView_CurtainViewDisplayedUserCanNavigateIt ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.showCurtainView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnCurtainReturnButton(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsNotPresentOnMap(ISOTOPIC_CAPTURE_NATURAL_GAS, NOTSET));
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
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
	 * Results: - 
	 *	- - Click Return
	 */
	@Test
	public void TC937_SurveyView_UserAllowedMoveUpDownLeftRightZoomInZoomOutCurtainView() throws Exception {
		Log.info("\nRunning TC937_SurveyView_UserAllowedMoveUpDownLeftRightZoomInZoomOutCurtainView ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.showCurtainView(EMPTY, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnCurtainArrowUpButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnCurtainArrowRightButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnCurtainZoomInButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnCurtainZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnCurtainReturnButton(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC938_SurveyView_NavigateBackForthBetweenSurveyViewHomePage
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click on Picarro Icon present at bottom
	 * Results: - 
	 *	- - User is navigated to Survey view page
	 */
	@Test
	public void TC938_SurveyView_NavigateBackForthBetweenSurveyViewHomePage() throws Exception {
		Log.info("\nRunning TC938_SurveyView_NavigateBackForthBetweenSurveyViewHomePage ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnPicarroLogoButton(EMPTY, NOTSET);
		surveyViewPageAction.verifySurveyViewPageIsOpened(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC939_SurveyView_CheckSurveyDataPresentAfterUserRefreshesBrowser_Satellite
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click on Map and turn Satellite View ON
	 *	- - Refresh the browser after couple of mins
	 * Results: - 
	 *	- - Survey details - Tag, Mode, Driver Name, Stability Class, Start Time, End Time, Surveyor Name should be present in satellite view
	 */
	@Test
	public void TC939_SurveyView_CheckSurveyDataPresentAfterUserRefreshesBrowser_Satellite() throws Exception {
		Log.info("\nRunning TC939_SurveyView_CheckSurveyDataPresentAfterUserRefreshesBrowser_Satellite ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.refreshPage(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_STANDARD1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_STANDARD1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_STANDARD1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
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
	 */
	@Test
	public void TC940_SurveyView_CheckSurveyDataPresentAfterUserRefreshesBrowser_Map() throws Exception {
		Log.info("\nRunning TC940_SurveyView_CheckSurveyDataPresentAfterUserRefreshesBrowser_Map ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.refreshPage(EMPTY, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_STANDARD1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_STANDARD1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_STANDARD1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
	}

	/**
	 * Test Case ID: TC941_SurveyView_ViewOperatorSurveySatelliteViewWhenAllGISDisplayOptionsAreTurnedOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey (Operator survey)
	 *	- - Display: All OFF
	 *	- - Map View: Satellite
	 *	- - GIS: All OFF
	 *	- - User should see only Breadcrumb on map in satellite view
	 * Results: - 
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC941_SurveyView_ViewOperatorSurveySatelliteViewWhenAllGISDisplayOptionsAreTurnedOFF() throws Exception {
		Log.info("\nRunning TC941_SurveyView_ViewOperatorSurveySatelliteViewWhenAllGISDisplayOptionsAreTurnedOFF ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC942_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenBoundariesDataLoadedSatelliteView
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey (Operator survey)
	 *	- - Display: All ON
	 *	- - Map View: Satellite
	 *	- - GIS: Assets OFF
	 *	- Boundaries ON
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map in satellite view
	 * Results: - 
	 *	- - Only boundaries are displayed. Assets are not displayed
	 */
	@Test
	public void TC942_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenBoundariesDataLoadedSatelliteView() throws Exception {
		Log.info("\nRunning TC942_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenBoundariesDataLoadedSatelliteView ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);    /*Picarro Admin*/ 
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssets (EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllBoundaries(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_STANDARD1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_STANDARD1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_STANDARD1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_NotNaturalGas), NOTSET));
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_IsotopicCanceled), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));

	}

	/**
	 * Test Case ID: TC943_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenAssetsAreLoadedSatelliteView
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey (Operator survey)
	 *	- - Display: All ON
	 *	- - Map View: Satellite
	 *	- - GIS: Assets ON
	 *	- Boundaries OFF
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map in satellite view
	 * Results: - 
	 *	- - All pipes data is displayed. Boundaries are not present
	 */
	@Test
	public void TC943_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenAssetsAreLoadedSatelliteView() throws Exception {
		Log.info("\nRunning TC943_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenAssetsAreLoadedSatelliteView ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssets(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomInButton(EMPTY, NOTSET);
		
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_STANDARD1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_STANDARD1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_STANDARD1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
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
	 *	- - All other survey data is not displayed
	 */
	@Test
	public void TC944_SurveyView_ViewIndicationsDataSatelliteViewOperatorSurveyWhenBoundariesPipeLoaded() throws Exception {
		Log.info("\nRunning TC944_SurveyView_ViewIndicationsDataSatelliteViewOperatorSurveyWhenBoundariesPipeLoaded ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
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
	 *	- - All other survey data is not displayed
	 */
	@Test
	public void TC945_SurveyView_ViewFOVDataSatelliteViewOperatorSurveyWhenGISDataLoaded() throws Exception {
		Log.info("\nRunning TC945_SurveyView_ViewFOVDataSatelliteViewOperatorSurveyWhenGISDataLoaded ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
	}
}
