
package surveyor.regression.source;

import org.junit.Ignore;
import static org.junit.Assert.*;

import common.source.Log;

public class SurveyViewPageTest2 extends SurveyViewPageTest {

	public SurveyViewPageTest2() {
		super();
	}

	/**
	 * Test Case ID: TC393_UserNotAllowedStartStopSurveySurveyView
	 * Script: -  	
	 * Results: - 
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 */
	@Ignore
	public void TC393_UserNotAllowedStartStopSurveySurveyView() throws Exception {
		Log.info("\nRunning TC393_UserNotAllowedStartStopSurveySurveyView ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
	}
 
	/**
	 * Test Case ID: TC394_UserShouldNotAbleViewStatusInformationSurveyView
	 * Script: -  	
	 * Results: - 
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 */
	@Ignore
	public void TC394_UserShouldNotAbleViewStatusInformationSurveyView() throws Exception {
		Log.info("\nRunning TC394_UserShouldNotAbleViewStatusInformationSurveyView ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
	}
 
	/**
	 * Test Case ID: TC395_SurveyView_ViewOperatorSurveyMapViewWhenAllGISDisplayOptionsAreON
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey (Operator survey)
	 *	- - Display: All ON
	 *	- - Map View: Map
	 *	- - GIS: All ON
	 *	- - Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any)
	 * Results: - 
	 *	- - All pipes and boundaries data are displayed
	 */
	@Ignore
	public void TC395_SurveyView_ViewOperatorSurveyMapViewWhenAllGISDisplayOptionsAreON() throws Exception {
		Log.info("\nRunning TC395_SurveyView_ViewOperatorSurveyMapViewWhenAllGISDisplayOptionsAreON ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
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
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC396_SurveyView_ViewOperatorSurveyWhenAllGISDisplayOptionsAreTurnedOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey (Operator survey)
	 *	- - Display: All OFF
	 *	- - Map View: Map
	 *	- - GIS: All OFF
	 *	- - User should see only Breadcrumb in map view
	 * Results: - 
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Ignore
	public void TC396_SurveyView_ViewOperatorSurveyWhenAllGISDisplayOptionsAreTurnedOFF() throws Exception {
		Log.info("\nRunning TC396_SurveyView_ViewOperatorSurveyWhenAllGISDisplayOptionsAreTurnedOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
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
	 * Test Case ID: TC397_SurveyView_ViewOperatorSurveySatelliteViewWhenAllGISDisplayOptionsAreON
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey (Operator survey)
	 *	- - Display: All ON
	 *	- - Map View: Satellite
	 *	- - GIS: All ON
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map in satellite view
	 * Results: - 
	 *	- - All pipes and boundaries data are displayed
	 */
	@Ignore
	public void TC397_SurveyView_ViewOperatorSurveySatelliteViewWhenAllGISDisplayOptionsAreON() throws Exception {
		Log.info("\nRunning TC397_SurveyView_ViewOperatorSurveySatelliteViewWhenAllGISDisplayOptionsAreON ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
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
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC398_SurveyView_ViewStandardSurveyWhenAllGISDataLoadedAllDisplayOptionsAreON
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All ON
	 *	- - Map View: Map
	 *	- - GIS: All ON
	 *	- - Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map
	 * Results: - 
	 *	- - All pipes and boundaries data are displayed
	 */
	@Ignore
	public void TC398_SurveyView_ViewStandardSurveyWhenAllGISDataLoadedAllDisplayOptionsAreON() throws Exception {
		Log.info("\nRunning TC398_SurveyView_ViewStandardSurveyWhenAllGISDataLoadedAllDisplayOptionsAreON ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
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
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC399_SurveyView_ViewStandardSurveyWhenAllGISDisplayOptionsAreTurnedOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All OFF
	 *	- - Map View: Map
	 *	- - GIS: All OFF
	 *	- - User should see only Breadcrumb in map view
	 * Results: - 
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Ignore
	public void TC399_SurveyView_ViewStandardSurveyWhenAllGISDisplayOptionsAreTurnedOFF() throws Exception {
		Log.info("\nRunning TC399_SurveyView_ViewStandardSurveyWhenAllGISDisplayOptionsAreTurnedOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
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
	 * Test Case ID: TC400_SurveyView_ViewStandardSurveySatelliteViewWhenAllGISDataLoadedAllDisplayOptionsAreON
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All ON
	 *	- - Map View: Satellite
	 *	- - GIS: All ON
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on mapin satellite view
	 * Results: - 
	 *	- - All pipes and boundaries data are displayed
	 */
	@Ignore
	public void TC400_SurveyView_ViewStandardSurveySatelliteViewWhenAllGISDataLoadedAllDisplayOptionsAreON() throws Exception {
		Log.info("\nRunning TC400_SurveyView_ViewStandardSurveySatelliteViewWhenAllGISDataLoadedAllDisplayOptionsAreON ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
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
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC401_SurveyView_VerifyRestrictionsMaximumZoomLevelAtWhichAssetsAreDisplayed
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey 
	 *	- - Zoom in to Level 17 and above
	 *	- - Zoom out to Level 16 and below
	 * Results: - 
	 *	- - Assets are not displayed touser in survey view
	 */
	@Ignore
	public void TC401_SurveyView_VerifyRestrictionsMaximumZoomLevelAtWhichAssetsAreDisplayed() throws Exception {
		Log.info("\nRunning TC401_SurveyView_VerifyRestrictionsMaximumZoomLevelAtWhichAssetsAreDisplayed ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.clickOnZoomInButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomInButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC402_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenBoundariesDataLoadedMapView
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey (Operator survey)
	 *	- - Display: All ON
	 *	- - Map View: Map
	 *	- - GIS: Assets OFF
	 *	- Boundaries ON
	 *	- - Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map
	 * Results: - 
	 *	- - Only boundaries are displayed. Assets are not displayed
	 */
	@Ignore
	public void TC402_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenBoundariesDataLoadedMapView() throws Exception {
		Log.info("\nRunning TC402_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenBoundariesDataLoadedMapView ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
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
	 * Test Case ID: TC403_SurveyView_ViewFOVIndicationsLisasSurveyDataStandardSurveyWhenBoundariesDataLoadedMapView
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
	@Ignore
	public void TC403_SurveyView_ViewFOVIndicationsLisasSurveyDataStandardSurveyWhenBoundariesDataLoadedMapView() throws Exception {
		Log.info("\nRunning TC403_SurveyView_ViewFOVIndicationsLisasSurveyDataStandardSurveyWhenBoundariesDataLoadedMapView ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
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
	 * Test Case ID: TC404_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenAssetsAreLoadedMapView
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey (Operator survey)
	 *	- - Display: All ON
	 *	- - Map View: Map
	 *	- - GIS: Assets ON
	 *	- Boundaries OFF
	 *	- - Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map
	 * Results: - 
	 *	- - All pipes data is displayed. Boundaries are not present
	 */
	@Ignore
	public void TC404_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenAssetsAreLoadedMapView() throws Exception {
		Log.info("\nRunning TC404_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenAssetsAreLoadedMapView ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
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
	 * Test Case ID: TC405_SurveyView_ViewFOVIndicationsLisasSurveyDataStandardSurveyWhenAssetsAreLoadedMapView
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
	@Ignore
	public void TC405_SurveyView_ViewFOVIndicationsLisasSurveyDataStandardSurveyWhenAssetsAreLoadedMapView() throws Exception {
		Log.info("\nRunning TC405_SurveyView_ViewFOVIndicationsLisasSurveyDataStandardSurveyWhenAssetsAreLoadedMapView ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
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
	 * Test Case ID: TC406_SurveyView_ChangeBaseMapLayer
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey 
	 *	- - Click on Map and select Map View: Map 
	 * Results: - 
	 *	- -Click on Map and selectMap View: Satellite
	 */
	@Ignore
	public void TC406_SurveyView_ChangeBaseMapLayer() throws Exception {
		Log.info("\nRunning TC406_SurveyView_ChangeBaseMapLayer ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
	}
 
	/**
	 * Test Case ID: TC407_SurveyView_ViewIndicationsDataMapViewStandardSurveyWhenBoundariesPipeLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: Indication ON
	 *	- All other options OFF
	 *	- - Map View: Map
	 *	- - GIS: All ON
	 * Results: - 
	 *	- - All other survey data is not displayed
	 */
	@Ignore
	public void TC407_SurveyView_ViewIndicationsDataMapViewStandardSurveyWhenBoundariesPipeLoaded() throws Exception {
		Log.info("\nRunning TC407_SurveyView_ViewIndicationsDataMapViewStandardSurveyWhenBoundariesPipeLoaded ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
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
	 * Test Case ID: TC408_SurveyView_ViewFOVDataMapViewStandardSurveyWhenBoundariesAssetsLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: FOV ON
	 *	- All other options OFF
	 *	- - Map View: Map
	 *	- - GIS: All ON
	 * Results: - 
	 *	- - All other survey data is not displayed
	 */
	@Ignore
	public void TC408_SurveyView_ViewFOVDataMapViewStandardSurveyWhenBoundariesAssetsLoaded() throws Exception {
		Log.info("\nRunning TC408_SurveyView_ViewFOVDataMapViewStandardSurveyWhenBoundariesAssetsLoaded ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
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
	 * Test Case ID: TC409_SurveyView_ViewIndicationsDataMapViewOperatorSurveyWhenBoundariesPipeLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: Indication ON
	 *	- All other options OFF
	 *	- - Map View: Map
	 *	- - GIS: All ON
	 * Results: - 
	 *	- - All other survey data is not displayed
	 */
	@Ignore
	public void TC409_SurveyView_ViewIndicationsDataMapViewOperatorSurveyWhenBoundariesPipeLoaded() throws Exception {
		Log.info("\nRunning TC409_SurveyView_ViewIndicationsDataMapViewOperatorSurveyWhenBoundariesPipeLoaded ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
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
	 * Test Case ID: TC410_SurveyView_ViewFOVDataMapViewOperatorSurveyWhenGISDataLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: FOV ON
	 *	- All other options OFF
	 *	- - Map View: Map
	 *	- - GIS: All ON
	 * Results: - 
	 *	- - All other survey data is not displayed
	 */
	@Ignore
	public void TC410_SurveyView_ViewFOVDataMapViewOperatorSurveyWhenGISDataLoaded() throws Exception {
		Log.info("\nRunning TC410_SurveyView_ViewFOVDataMapViewOperatorSurveyWhenGISDataLoaded ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
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
	 * Test Case ID: TC420_SurveyView_ViewAllDataRapidResponseSurveyWhenGISDataLoadedAllDisplayOptionsAreON
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All ON
	 *	- - Map View: Map
	 *	- - GIS: All ON
	 *	- - Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map
	 * Results: - 
	 *	- - All pipes and boundaries data are displayed
	 */
	@Ignore
	public void TC420_SurveyView_ViewAllDataRapidResponseSurveyWhenGISDataLoadedAllDisplayOptionsAreON() throws Exception {
		Log.info("\nRunning TC420_SurveyView_ViewAllDataRapidResponseSurveyWhenGISDataLoadedAllDisplayOptionsAreON ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
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
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC421_SurveyView_ViewRapidRepsonseSurveyWhenAllGISDisplayOptionsAreOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All OFF
	 *	- - Map View: Map
	 *	- - GIS: All OFF
	 *	- - User should see only Breadcrumb in map view
	 * Results: - 
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Ignore
	public void TC421_SurveyView_ViewRapidRepsonseSurveyWhenAllGISDisplayOptionsAreOFF() throws Exception {
		Log.info("\nRunning TC421_SurveyView_ViewRapidRepsonseSurveyWhenAllGISDisplayOptionsAreOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
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
	 * Test Case ID: TC422_SurveyView_ViewRapidResponseSurveySatelliteViewWhenGISDataLoadedAllDisplayOptionsAreON
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All ON
	 *	- - Map View: Satellite
	 *	- - GIS: All ON
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on mapin satellite view
	 * Results: - 
	 *	- - All pipes and boundaries data are displayed
	 */
	@Ignore
	public void TC422_SurveyView_ViewRapidResponseSurveySatelliteViewWhenGISDataLoadedAllDisplayOptionsAreON() throws Exception {
		Log.info("\nRunning TC422_SurveyView_ViewRapidResponseSurveySatelliteViewWhenGISDataLoadedAllDisplayOptionsAreON ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
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
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC423_SurveyView_ViewIndicationsDataRapidResponseSurveyWhenGISDataLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: Indication ON
	 *	- All other options OFF
	 *	- - Map View: Map
	 *	- - GIS: All ON
	 * Results: - 
	 *	- - All other survey data is not displayed
	 */
	@Ignore
	public void TC423_SurveyView_ViewIndicationsDataRapidResponseSurveyWhenGISDataLoaded() throws Exception {
		Log.info("\nRunning TC423_SurveyView_ViewIndicationsDataRapidResponseSurveyWhenGISDataLoaded ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
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
	 * Test Case ID: TC424_SurveyView_ViewFOVDataRapidResponseSurveyWhenGISDataLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: FOV ON
	 *	- All other options OFF
	 *	- - Map View: Map
	 *	- - GIS: All ON
	 * Results: - 
	 *	- - All other survey data is not displayed
	 */
	@Ignore
	public void TC424_SurveyView_ViewFOVDataRapidResponseSurveyWhenGISDataLoaded() throws Exception {
		Log.info("\nRunning TC424_SurveyView_ViewFOVDataRapidResponseSurveyWhenGISDataLoaded ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
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
	 * Test Case ID: TC425_SurveyView_ViewFOVIndicationsLisasSurveyDataRapidResponseSurveyWhenAssetsAreLoaded
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
	@Ignore
	public void TC425_SurveyView_ViewFOVIndicationsLisasSurveyDataRapidResponseSurveyWhenAssetsAreLoaded() throws Exception {
		Log.info("\nRunning TC425_SurveyView_ViewFOVIndicationsLisasSurveyDataRapidResponseSurveyWhenAssetsAreLoaded ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
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
	 * Test Case ID: TC426_SurveyView_ViewFOVIndicationsLisasDataRapidResponseSurveyWhenBoundariesAreLoaded
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
	@Ignore
	public void TC426_SurveyView_ViewFOVIndicationsLisasDataRapidResponseSurveyWhenBoundariesAreLoaded() throws Exception {
		Log.info("\nRunning TC426_SurveyView_ViewFOVIndicationsLisasDataRapidResponseSurveyWhenBoundariesAreLoaded ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
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
	 * Test Case ID: TC427_SurveyView_ViewIndicationsLisasRapidResponseSurveyWhenGISDataLoaded
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
	@Ignore
	public void TC427_SurveyView_ViewIndicationsLisasRapidResponseSurveyWhenGISDataLoaded() throws Exception {
		Log.info("\nRunning TC427_SurveyView_ViewIndicationsLisasRapidResponseSurveyWhenGISDataLoaded ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
		surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
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

}
