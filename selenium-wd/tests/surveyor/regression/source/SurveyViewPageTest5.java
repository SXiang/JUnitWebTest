package surveyor.regression.source;

import org.junit.Ignore;
import org.junit.Test;
import common.source.Log;
import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.ISOTOPIC_CAPTURE_NATURAL_GAS;

public class SurveyViewPageTest5 extends SurveyViewPageTest4 {

	public SurveyViewPageTest5() {
		super();
	}

	/**
	 * Test Case ID: TC946_SurveyView_ViewOperatorSurveySurveyMapViewWhenLISAFieldNotesAreONGISOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- LISA - ON
	 *	- Field Notes - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- - Survey Information is displayed in map view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- - Breadcrumb, Lisa and field notes are present on map
	 *	- - Indications and FOV are not displayed on map
	 */
	@Ignore
	public void TC946_SurveyView_ViewOperatorSurveySurveyMapViewWhenLISAFieldNotesAreONGISOFF() throws Exception {
		Log.info("\nRunning TC946_SurveyView_ViewOperatorSurveySurveyMapViewWhenLISAFieldNotesAreONGISOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
		surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
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
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC947_SurveyView_ViewOperatorSurveySurveySatelliteViewWhenLISAFieldNotesAreONGISOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- LISA - ON
	 *	- Field Notes - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- - Breadcrumb, Lisa and field notes are present on map in satellite view
	 *	- - Indications and FOV are not displayed on map in satellite view
	 */
	@Ignore
	public void TC947_SurveyView_ViewOperatorSurveySurveySatelliteViewWhenLISAFieldNotesAreONGISOFF() throws Exception {
		Log.info("\nRunning TC947_SurveyView_ViewOperatorSurveySurveySatelliteViewWhenLISAFieldNotesAreONGISOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
		surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_STANDARD1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_STANDARD1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_STANDARD1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC948_SurveyView_ViewOperatorSurveySurveyMapViewWhenFovIndicationONGISON
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- FOV - ON
	 *	- Indications - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options ON
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- Survey Information is displayed in map view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- -Breadcrumb,Indications and FOV are displayed on map along with GIS data
	 *	- - Lisa and field notes (if any) are not present on map
	 */
	@Ignore
	public void TC948_SurveyView_ViewOperatorSurveySurveyMapViewWhenFovIndicationONGISON() throws Exception {
		Log.info("\nRunning TC948_SurveyView_ViewOperatorSurveySurveyMapViewWhenFovIndicationONGISON ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
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
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
	}
 
	/**
	 * Test Case ID: TC949_SurveyView_ViewOperatorSurveySurveySatelliteViewWhenFovIndicationONGISON
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
	@Ignore
	public void TC949_SurveyView_ViewOperatorSurveySurveySatelliteViewWhenFovIndicationONGISON() throws Exception {
		Log.info("\nRunning TC949_SurveyView_ViewOperatorSurveySurveySatelliteViewWhenFovIndicationONGISON ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_STANDARD1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_STANDARD1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_STANDARD1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
	}

	/**
	 * Test Case ID: TC953_SurveyView_ViewOperatorSurveySurveySatelliteViewWhenIndicationsONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- Indications - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *	- - Breadcrumb and Indications are displayed on map in satellite view
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Ignore
	public void TC953_SurveyView_ViewOperatorSurveySurveySatelliteViewWhenIndicationsONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC953_SurveyView_ViewOperatorSurveySurveySatelliteViewWhenIndicationsONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC954_SurveyView_ViewOperatorSurveySurveyMapViewWhenLISAONAssetsBoundariesOFF
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
	@Ignore
	public void TC954_SurveyView_ViewOperatorSurveySurveyMapViewWhenLISAONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC954_SurveyView_ViewOperatorSurveySurveyMapViewWhenLISAONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
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
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC955_SurveyView_ViewOperatorSurveySurveySatelliteViewWhenLISAONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- Lisa - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *	- - Breadcrumb and Lisa are displayed on map in satellite view
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Ignore
	public void TC955_SurveyView_ViewOperatorSurveySurveySatelliteViewWhenLISAONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC955_SurveyView_ViewOperatorSurveySurveySatelliteViewWhenLISAONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
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
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC956_SurveyView_ViewOperatorSurveySurveyMapViewWhenFOVONAssetsBoundariesOFF
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
	@Ignore
	public void TC956_SurveyView_ViewOperatorSurveySurveyMapViewWhenFOVONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC956_SurveyView_ViewOperatorSurveySurveyMapViewWhenFOVONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC957_SurveyView_ViewOperatorSurveySurveySatelliteViewWhenFOVONAssetsBoundariesOFF
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
	@Ignore
	public void TC957_SurveyView_ViewOperatorSurveySurveySatelliteViewWhenFOVONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC957_SurveyView_ViewOperatorSurveySurveySatelliteViewWhenFOVONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC958_SurveyView_ViewOperatorSurveySurveyMapViewWhenFieldNotesONAssetsBoundariesOFF
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
	@Ignore
	public void TC958_SurveyView_ViewOperatorSurveySurveyMapViewWhenFieldNotesONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC958_SurveyView_ViewOperatorSurveySurveyMapViewWhenFieldNotesONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC959_SurveyView_ViewOperatorSurveySurveySatellliteViewWhenFieldNotesONAssetsBoundariesOFF
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
	@Ignore
	public void TC959_SurveyView_ViewOperatorSurveySurveySatellliteViewWhenFieldNotesONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC959_SurveyView_ViewOperatorSurveySurveySatellliteViewWhenFieldNotesONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC960_SurveyView_ViewOperatorSurveySurveyMapViewWhenIsotopicAnalysisONAssetsBoundariesOFF
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
	@Ignore
	public void TC960_SurveyView_ViewOperatorSurveySurveyMapViewWhenIsotopicAnalysisONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC960_SurveyView_ViewOperatorSurveySurveyMapViewWhenIsotopicAnalysisONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIsotopicAnalysis(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_NATURAL_GAS, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC961_SurveyView_ViewOperatorSurveySurveySatelliteViewWhenIsotopicAnalysisONAssetsBoundariesOFF
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
	@Ignore
	public void TC961_SurveyView_ViewOperatorSurveySurveySatelliteViewWhenIsotopicAnalysisONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC961_SurveyView_ViewOperatorSurveySurveySatelliteViewWhenIsotopicAnalysisONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIsotopicAnalysis(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(ISOTOPIC_CAPTURE_NATURAL_GAS, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC970_SurveyView_ViewStandardSurveySatelliteViewWhenAllGISDisplayOptionsAreTurnedOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All OFF
	 *	- - Map View: Satellite
	 *	- - GIS: All OFF
	 * Results: - 
	 *	- - User should see only Breadcrumb in satellite view
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Ignore
	public void TC970_SurveyView_ViewStandardSurveySatelliteViewWhenAllGISDisplayOptionsAreTurnedOFF() throws Exception {
		Log.info("\nRunning TC970_SurveyView_ViewStandardSurveySatelliteViewWhenAllGISDisplayOptionsAreTurnedOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
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
	 * Test Case ID: TC971_SurveyView_ViewFOVIndicationsLisasSurveyDataStandardSurveyWhenBoundariesDataLoadedSatelliteView
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All ON
	 *	- - Map View: Satellite
	 *	- - GIS: Assets OFF
	 *	- Boundaries ON
	 * Results: - 
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map in satellite view
	 *	- - Only boundaries are displayed. Assets are not displayed
	 */
	@Ignore
	public void TC971_SurveyView_ViewFOVIndicationsLisasSurveyDataStandardSurveyWhenBoundariesDataLoadedSatelliteView() throws Exception {
		Log.info("\nRunning TC971_SurveyView_ViewFOVIndicationsLisasSurveyDataStandardSurveyWhenBoundariesDataLoadedSatelliteView ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssets(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllBoundaries(EMPTY, NOTSET);
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
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC972_SurveyView_ViewFOVIndicationsLisasSurveyDataStandardSurveyWhenAssetsAreLoadedSatelliteView
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All ON
	 *	- - Map View: Stallite
	 *	- - GIS: Assets ON
	 *	- Boundaries OFF
	 * Results: - 
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map in satellite view
	 *	- - All pipes data is displayed. Boundaries are not present
	 */
	@Ignore
	public void TC972_SurveyView_ViewFOVIndicationsLisasSurveyDataStandardSurveyWhenAssetsAreLoadedSatelliteView() throws Exception {
		Log.info("\nRunning TC972_SurveyView_ViewFOVIndicationsLisasSurveyDataStandardSurveyWhenAssetsAreLoadedSatelliteView ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);
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
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC973_SurveyView_ViewIndicationsDataSatelliteViewStandardSurveyWhenBoundariesPipeLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: Indication ON
	 *	- All other options OFF
	 *	- - Map View: Satellite
	 *	- - GIS: All ON
	 * Results: - 
	 *	- - All other survey data is not displayed
	 */
	@Ignore
	public void TC973_SurveyView_ViewIndicationsDataSatelliteViewStandardSurveyWhenBoundariesPipeLoaded() throws Exception {
		Log.info("\nRunning TC973_SurveyView_ViewIndicationsDataSatelliteViewStandardSurveyWhenBoundariesPipeLoaded ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
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
	 * Test Case ID: TC974_SurveyView_ViewFOVDataSatelliteViewStandardSurveyWhenBoundariesAssetsLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: FOV ON
	 *	- All other options OFF
	 *	- - Map View: Satellite
	 *	- - GIS: All ON
	 * Results: - 
	 *	- - All other survey data is not displayed
	 */
	@Ignore
	public void TC974_SurveyView_ViewFOVDataSatelliteViewStandardSurveyWhenBoundariesAssetsLoaded() throws Exception {
		Log.info("\nRunning TC974_SurveyView_ViewFOVDataSatelliteViewStandardSurveyWhenBoundariesAssetsLoaded ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
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
 
	/**
	 * Test Case ID: TC975_SurveyView_ViewStandardSurveyMapViewWhenLISAFieldNotesAreONGISOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- LISA - ON
	 *	- Field Notes - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- - Survey Information is displayed in map view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- - Breadcrumb, Lisa and field notes are present on map
	 *	- - Indications and FOV are not displayed on map
	 */
	@Ignore
	public void TC975_SurveyView_ViewStandardSurveyMapViewWhenLISAFieldNotesAreONGISOFF() throws Exception {
		Log.info("\nRunning TC975_SurveyView_ViewStandardSurveyMapViewWhenLISAFieldNotesAreONGISOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
		surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
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
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC976_SurveyView_ViewStandardSurveySatelliteViewWhenLISAFieldNotesAreONGISOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- LISA - ON
	 *	- Field Notes - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- - Breadcrumb, Lisa and field notes are present on map in satellite view
	 *	- - Indications and FOV are not displayed on map in satellite view
	 */
	@Ignore
	public void TC976_SurveyView_ViewStandardSurveySatelliteViewWhenLISAFieldNotesAreONGISOFF() throws Exception {
		Log.info("\nRunning TC976_SurveyView_ViewStandardSurveySatelliteViewWhenLISAFieldNotesAreONGISOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
		surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_STANDARD1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_STANDARD1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_STANDARD1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC977_SurveyView_ViewStandardSurveyMapViewWhenFovIndicationONGISON
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- FOV - ON
	 *	- Indications - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options ON
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- Survey Information is displayed in map view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- -Breadcrumb,Indications and FOV are displayed on map along with GIS data
	 *	- - Lisa and field notes (if any) are not present on map
	 */
	@Ignore
	public void TC977_SurveyView_ViewStandardSurveyMapViewWhenFovIndicationONGISON() throws Exception {
		Log.info("\nRunning TC977_SurveyView_ViewStandardSurveyMapViewWhenFovIndicationONGISON ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
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
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
	}
 
	/**
	 * Test Case ID: TC978_SurveyView_ViewStandardSurveySatelliteViewWhenFovIndicationONGISON
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
	@Ignore
	public void TC978_SurveyView_ViewStandardSurveySatelliteViewWhenFovIndicationONGISON() throws Exception {
		Log.info("\nRunning TC978_SurveyView_ViewStandardSurveySatelliteViewWhenFovIndicationONGISON ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_STANDARD1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_STANDARD1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_STANDARD1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(SAMPLE_FIELD_NOTES1, NOTSET));
	}
 
	/**
	 * Test Case ID: TC979_SurveyView_ViewStandardSurveyMapViewWhenAllDisplayOptionsAreONProtectedSteelCastIronAssetsBoundariesON
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All options - ON
	 *	- - Click GIS
	 *	- Assets: Protected steel, Cast iron - ON
	 *	- Boundaries: ON
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- - Breadcrumb, Indications, LISA, FOV and survey information is displayed on map
	 *	- - Boundaries displayed on map
	 */
	@Ignore
	public void TC979_SurveyView_ViewStandardSurveyMapViewWhenAllDisplayOptionsAreONProtectedSteelCastIronAssetsBoundariesON() throws Exception {
		Log.info("\nRunning TC979_SurveyView_ViewStandardSurveyMapViewWhenAllDisplayOptionsAreONProtectedSteelCastIronAssetsBoundariesON ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypeProtectedSteel(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypeCastIron(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_RAPID_RESP_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_RAPID_RESP_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_RAPID_RESP_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC980_SurveyView_ViewStandardSurveySatelliteViewWhenAllDisplayOptionsAreONUn_ProtectedSteelCopperAssetsONBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All options - ON
	 *	- - Click GIS
	 *	- Assets: Un-Protected Steel, Copper - ON
	 *	- Boundaries: OFF
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *	- - Breadcrumb, Indications, LISA, FOV and survey information is displayed on map in satellite view
	 *	- - Boundaries not displayed on map in satellite view
	 */
	@Ignore
	public void TC980_SurveyView_ViewStandardSurveySatelliteViewWhenAllDisplayOptionsAreONUn_ProtectedSteelCopperAssetsONBoundariesOFF() throws Exception {
		Log.info("\nRunning TC980_SurveyView_ViewStandardSurveySatelliteViewWhenAllDisplayOptionsAreONUn_ProtectedSteelCopperAssetsONBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypeUnprotectedSteel(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypeCastIron(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_RAPID_RESP_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_RAPID_RESP_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_RAPID_RESP_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC981_SurveyView_ViewStandardSurveyMapViewWhenIndicationsONAssetsBoundariesOFF
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
	@Ignore
	public void TC981_SurveyView_ViewStandardSurveyMapViewWhenIndicationsONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC981_SurveyView_ViewStandardSurveyMapViewWhenIndicationsONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
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
}
