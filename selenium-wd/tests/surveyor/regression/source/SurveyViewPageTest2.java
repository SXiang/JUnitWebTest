
package surveyor.regression.source;

import org.junit.Test;
import static org.junit.Assert.*;

import common.source.Log;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

public class SurveyViewPageTest2 extends SurveyViewPageTest1 {

	public SurveyViewPageTest2() {
		super();
	}

	/**
	 * Test Case ID: TC393_UserNotAllowedStartStopSurveySurveyView
	 * Test Description: User not allowed to start and stop survey in survey view
	 * Script: 
	 *  - On Home Page, click on Driving Surveys -& View Survey 	
	 * Results: 
	 *	- User should be able to see entire survey at once
     *  - Mode button is not present
	 */
	@Test
	public void TC393_UserNotAllowedStartStopSurveySurveyView() throws Exception {
		Log.info("\nRunning TC393_UserNotAllowedStartStopSurveySurveyView  - "+
				"Test Description:  User not allowed to start and stop survey in survey view\n");
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyModeButtonIsNotVisible(EMPTY, NOTSET));		
	}
	/**
	 * Test Case ID: TC394_UserShouldNotAbleViewStatusInformationSurveyView
	 * Test Description: User should not be able to view status information, in survey view
	 * Script:
	 *  -  	On Home Page, click on Driving Surveys -& View Survey
	 * Results: 
	 *  -   Status button not present
	 */
	@Test
	public void TC394_UserShouldNotAbleViewStatusInformationSurveyView() throws Exception {
		Log.info("\nRunning TC394_UserShouldNotAbleViewStatusInformationSurveyView - "+
	              "Test Description: User should not be able to view status information in survey view");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);		
		assertTrue(surveyViewPageAction.verifyStatusButtonIsNotVisible(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC395_SurveyView_ViewOperatorSurveyMapViewWhenAllGISDisplayOptionsAreON
	 * Test Description: Survey View - View Operator Survey in map view when all GIS and display options are ON
	 * Script:
	 *	- On Home Page, click on Driving Surveys -& View Survey (Operator survey)
	 *	- Display: All ON
	 *	- Map View: Map
	 *	- GIS: All ON
	 * Results:
	 *	- Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any)
	 *	- All pipes and boundaries data are displayed
	 */
	@Test
	public void TC395_SurveyView_ViewOperatorSurveyMapViewWhenAllGISDisplayOptionsAreON() throws Exception {
		Log.info("\nRunning TC395_SurveyView_ViewOperatorSurveyMapViewWhenAllGISDisplayOptionsAreON - "+ 
	             "Test Description: Survey View - View Operator Survey in map view when all GIS and display options are ON\n");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_OPERATOR1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_OPERATOR1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_OPERATOR1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_IsotopicCanceled), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		
		surveyViewPage.setZoomLevelForAssets();
        assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC396_SurveyView_ViewOperatorSurveyWhenAllGISDisplayOptionsAreTurnedOFF
	 * Description: Survey View - View Operator Survey when all GIS and display options are turned OFF
	 * Script:  	
	 *	- On Home Page, click on Driving Surveys -& View Survey (Operator survey)
	 *	- Display: All OFF
	 *	- Map View: Map
	 *	- GIS: All OFF
	 * Results:
	 *	- Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class, 
	 *    Surveyor and analyzer info, Start Time, End Time
     *  - User  should see only Breadcrumb in map view
     *  - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC396_SurveyView_ViewOperatorSurveyWhenAllGISDisplayOptionsAreTurnedOFF() throws Exception {
		Log.info("\nRunning TC396_SurveyView_ViewOperatorSurveyWhenAllGISDisplayOptionsAreTurnedOFF - "+ 
	             "Test Description: Survey View - View Operator Survey when all GIS and display options are turned OFF\n");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_OPERATOR1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_OPERATOR1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_OPERATOR1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsNotPresentOnMap(Resources.getResource(ResourceKeys.Survey_IsotopicCanceled), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		
		surveyViewPage.setZoomLevelForAssets();
        assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC397_SurveyView_ViewOperatorSurveySatelliteViewWhenAllGISDisplayOptionsAreON
	 * Test Description: Survey View - View Operator Survey in satellite view when all GIS and display options are ON
	 * Script:  	
	 *	- On Home Page, click on Driving Surveys -& View Survey (Operator survey)
	 *	- Display: All ON
	 *	- Map View: Satellite
	 *	- GIS: All ON	
	 * Results:
	 *  - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map in satellite view
	 *	- All pipes and boundaries data are displayed
	 */
	@Test
	public void TC397_SurveyView_ViewOperatorSurveySatelliteViewWhenAllGISDisplayOptionsAreON() throws Exception {
		Log.info("\nRunning TC397_SurveyView_ViewOperatorSurveySatelliteViewWhenAllGISDisplayOptionsAreON - "+ 
	             "Test Description: Survey View - View Operator Survey in satellite view when all GIS and display options are ON\n");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_IsotopicCanceled), NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_OPERATOR1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_OPERATOR1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_OPERATOR1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		
		surveyViewPage.setZoomLevelForAssets();
        assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC398_SurveyView_ViewStandardSurveyWhenAllGISDataLoadedAllDisplayOptionsAreON
	 * Test Description: Survey View - View Standard Survey when all GIS data is loaded and all display options are ON
	 * Script:  	
	 *	- On Home Page, click on Driving Surveys -& View Survey
	 *	- Display: All ON
	 *	- Map View: Map
	 *	- GIS: All ON
	 * Results:
	 *	- All pipes and boundaries data are displayed
	 *	- Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map
	 */
	@Test
	public void TC398_SurveyView_ViewStandardSurveyWhenAllGISDataLoadedAllDisplayOptionsAreON() throws Exception {
		Log.info("\nRunning TC398_SurveyView_ViewStandardSurveyWhenAllGISDataLoadedAllDisplayOptionsAreON - "+ 
		          "Test Description: Survey View - View Standard Survey when all GIS data is loaded and all display options are ON\n");
		            
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_STANDARD1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_STANDARD1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_STANDARD1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));

		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_NotNaturalGas), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		
		surveyViewPage.setZoomLevelForAssets();
        assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC399_SurveyView_ViewStandardSurveyWhenAllGISDisplayOptionsAreTurnedOFF
	 * Test Description: Survey View - View Standard Survey when all GIS and display options are turned OFF
	 * Script:  	
	 *	- On Home Page, click on Driving Surveys -& View Survey
	 *	- Display: All OFF
	 *	- Map View: Map
	 *	- GIS: All OFF
	 * Results:
	 *  - Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class, Surveyor and analyzer info, Start Time, End Time
	 *	- User should see only Breadcrumb in map view
	 *	- All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC399_SurveyView_ViewStandardSurveyWhenAllGISDisplayOptionsAreTurnedOFF() throws Exception {
		Log.info("\nRunning TC399_SurveyView_ViewStandardSurveyWhenAllGISDisplayOptionsAreTurnedOFF - "+ 
		          "Test Description: Survey View - View Standard Survey when all GIS and display options are turned OFF\n");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_STANDARD1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_STANDARD1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_STANDARD1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsNotPresentOnMap(Resources.getResource(ResourceKeys.Survey_NotNaturalGas), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));		
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		
		surveyViewPage.setZoomLevelForAssets();
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC400_SurveyView_ViewStandardSurveySatelliteViewWhenAllGISDataLoadedAllDisplayOptionsAreON
	 * Test Description: Survey View - View Standard Survey in satellite view when all GIS data is loaded and all display options are ON
	 * Script:  	
	 *	- On Home Page, click on Driving Surveys -& View Survey
	 *	- Display: All ON
	 *	- Map View: Satellite
	 *	- GIS: All ON
	 * Results:
	 * 	- Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on mapin satellite view
	 *	- All pipes and boundaries data are displayed
	 */
	@Test
	public void TC400_SurveyView_ViewStandardSurveySatelliteViewWhenAllGISDataLoadedAllDisplayOptionsAreON() throws Exception {
		Log.info("\nRunning TC400_SurveyView_ViewStandardSurveySatelliteViewWhenAllGISDataLoadedAllDisplayOptionsAreON - "+ 
		          "Test Description: Survey View - View Standard Survey in satellite view when all GIS data is loaded and all display options are ON\n");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_STANDARD1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_STANDARD1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_STANDARD1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_NotNaturalGas), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));	
		
		surveyViewPage.setZoomLevelForAssets();
        assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC401_SurveyView_VerifyRestrictionsMaximumZoomLevelAtWhichAssetsAreDisplayed
	 * Test Description: Survey View - Verify restrictions on the maximum zoom level at which assets are displayed
	 * Script:  	
	 *	- On Home Page, click on Driving Surveys -& View Survey 
	 *	- Zoom in to Level 17 and above
	 *	- Zoom out to Level 16 and below
	 * Results:
	 *  - Assets are displayed to user in survey view
	 *	- Assets are not displayed to user in survey view
	 */
	@Test
	public void TC401_SurveyView_VerifyRestrictionsMaximumZoomLevelAtWhichAssetsAreDisplayed() throws Exception {
		Log.info("\nRunning TC401_SurveyView_VerifyRestrictionsMaximumZoomLevelAtWhichAssetsAreDisplayed - "+ 
		          "Test Description: Survey View - Verify restrictions on the maximum zoom level at which assets are displayed\n");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);		
		
		surveyViewPage.setZoomLevel(19);
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));		
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));		
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
		
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC402_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenBoundariesDataLoadedMapView
	 * Test Description: Survey View - View FOV, Indications, LISAs survey data for Operator survey when boundaries data is loaded in map view
	 * Script:  	
	 *	- On Home Page, click on Driving Surveys -& View Survey (Operator survey)
	 *	- Display: All ON
	 *	- Map View: Map
	 *	- GIS: Assets OFF
	 *	- Boundaries ON
	 * Results:
	 * 	- Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map
	 *	- Only boundaries are displayed. Assets are not displayed
	 */
	@Test
	public void TC402_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenBoundariesDataLoadedMapView() throws Exception {
		Log.info("\nRunning TC402_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenBoundariesDataLoadedMapView - "+ 
		          "Test Description: View FOV, Indications, LISAs survey data for Operator survey when boundaries data is loaded in map view\n");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);		
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssets(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_OPERATOR1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_OPERATOR1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_OPERATOR1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_IsotopicCanceled), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		
		surveyViewPage.setZoomLevelForAssets();
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));

	}
 
	/**
	 * Test Case ID: TC403_SurveyView_ViewFOVIndicationsLisasSurveyDataStandardSurveyWhenBoundariesDataLoadedMapView
	 * Test Description: Survey View - View FOV, Indications, LISAs survey data for Standard survey when boundaries data is loaded in map view
	 * Script:  	
	 *	- On Home Page, click on Driving Surveys -& View Survey
	 *	- Display: All ON
	 *	- Map View: Map
	 *	- GIS: Assets OFF
	 *	- Boundaries ON
	 * Results:
	 * 	- Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map
	 *	- Only boundaries are displayed. Assets are not displayed
	 */
	@Test
	public void TC403_SurveyView_ViewFOVIndicationsLisasSurveyDataStandardSurveyWhenBoundariesDataLoadedMapView() throws Exception {
		Log.info("\nRunning TC403_SurveyView_ViewFOVIndicationsLisasSurveyDataStandardSurveyWhenBoundariesDataLoadedMapView - "+ 
		          "Test Description: View FOV, Indications, LISAs survey data for Standard survey when boundaries data is loaded in map view\n");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssets(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_STANDARD1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_STANDARD1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_STANDARD1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
	
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_NotNaturalGas), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		
		surveyViewPage.setZoomLevelForAssets();
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));

	}
 
	/**
	 * Test Case ID: TC404_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenAssetsAreLoadedMapView
	 * Test Description: Survey View - View FOV, Indications, LISAs survey data for operator survey when assets are loaded in map view
	 * Script:  	
	 *	- On Home Page, click on Driving Surveys -& View Survey (Operator survey)
	 *	- Display: All ON
	 *	- Map View: Map
	 *	- GIS: Assets ON
	 *	- Boundaries OFF
	 * Results:
	 *	- Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map
	 *	- All pipes data is displayed. Boundaries are not present
	 */
	@Test
	public void TC404_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenAssetsAreLoadedMapView() throws Exception {
		Log.info("\nRunning TC404_SurveyView_ViewFOVIndicationsLisasSurveyDataOperatorSurveyWhenAssetsAreLoadedMapView - "+ 
		          "Test Description: View FOV, Indications, LISAs survey data for operator survey when assets are loaded in map view\n");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssets(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_OPERATOR1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_OPERATOR1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_OPERATOR1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));

		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_IsotopicCanceled), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_CANCELLED, NOTSET));		
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		
		surveyViewPage.setZoomLevelForAssets();
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC405_SurveyView_ViewFOVIndicationsLisasSurveyDataStandardSurveyWhenAssetsAreLoadedMapView
	 * Test Description:  Survey View - View FOV, Indications, LISAs survey data for standard survey when assets are loaded in map view
	 * Script:  	
	 *	- On Home Page, click on Driving Surveys -& View Survey
	 *	- Display: All ON
	 *	- Map View: Map
	 *	- GIS: Assets ON
	 *	- Boundaries OFF
	 * Results:
	 * 	- Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map
	 *	- All pipes data is displayed. Boundaries are not present
	 */
	@Test
	public void TC405_SurveyView_ViewFOVIndicationsLisasSurveyDataStandardSurveyWhenAssetsAreLoadedMapView() throws Exception {
		Log.info("\nRunning TC405_SurveyView_ViewFOVIndicationsLisasSurveyDataStandardSurveyWhenAssetsAreLoadedMapView - "+ 
		          "Test Description: View FOV, Indications, LISAs survey data for standard survey when assets are loaded in map view\n");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssets(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);		
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_STANDARD1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_STANDARD1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_STANDARD1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));

		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_NotNaturalGas), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));		
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		
		surveyViewPage.setZoomLevelForAssets();
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC406_SurveyView_ChangeBaseMapLayer
	 * Test Description: Survey View - Change Base Map layer
	 * Script:  	
	 *	- On Home Page, click on Driving Surveys -& View Survey 
	 *	- Click on Map and select Map View: Map 
	 *  - Click on Map and selectMap View: Satellite
	 * Results:
	 *  - Base Map will vary depending on value selected
	 */
	@Test
	public void TC406_SurveyView_ChangeBaseMapLayer() throws Exception {
		Log.info("\nRunning TC406_SurveyView_ChangeBaseMapLayer - "+ 
		          "Test Description: Survey View - Change Base Map layer\n");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyMapViewIsShown(EMPTY, NOTSET));
		
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySatelliteViewIsShown(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC407_SurveyView_ViewIndicationsDataMapViewStandardSurveyWhenBoundariesPipeLoaded
	 * Script:  View Indications data in map view for standard survey when boundaries and pipe loaded	
	 *	- On Home Page, click on Driving Surveys -& View Survey
	 *	- Display: Indication ON
	 *	- All other options OFF
	 *	- Map View: Map
	 *	- GIS: All ON
	 * Results:
	 *  - Indications and breadcrumb is displayed on map along with assets and boundaries data
	 *	- All other survey data is not displayed
	 */
	@Test
	public void TC407_SurveyView_ViewIndicationsDataMapViewStandardSurveyWhenBoundariesPipeLoaded() throws Exception {
		Log.info("\nRunning TC407_SurveyView_ViewIndicationsDataMapViewStandardSurveyWhenBoundariesPipeLoaded - "+ 
		          "Test Description: View Indications data in map view for standard survey when boundaries and pipe loaded\n");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
				
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);		
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsNotPresentOnMap(Resources.getResource(ResourceKeys.Survey_NotNaturalGas), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));		
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));
		
		surveyViewPage.setZoomLevelForAssets();
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC408_SurveyView_ViewFOVDataMapViewStandardSurveyWhenBoundariesAssetsLoaded
	 * Test Description: View FOV data in map view for standard survey when boundaries and assets is loaded
	 * Script:  	
	 *	- On Home Page, click on Driving Surveys -& View Survey
	 *	- Display: FOV ON
	 *	- All other options OFF
	 *	- Map View: Map
	 *	- GIS: All ON
	 * Results:
	 *  - FOV and breadcrumb is displayed on map along with assets and boundaries data
	 *	- All other survey data is not displayed
	 */
	@Test
	public void TC408_SurveyView_ViewFOVDataMapViewStandardSurveyWhenBoundariesAssetsLoaded() throws Exception {
		Log.info("\nRunning TC408_SurveyView_ViewFOVDataMapViewStandardSurveyWhenBoundariesAssetsLoaded - "+ 
		          "Test Description: View FOV data in map view for standard survey when boundaries and assets is loaded\n");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_STANDARD1_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsNotPresentOnMap(Resources.getResource(ResourceKeys.Survey_NotNaturalGas), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));		
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));		
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_NOT_NATURAL_GAS, NOTSET));
		
		surveyViewPage.setZoomLevelForAssets();
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC409_SurveyView_ViewIndicationsDataMapViewOperatorSurveyWhenBoundariesPipeLoaded
	 * Test Description: View Indications data in map view for operator survey when boundaries and pipe loaded
	 * Script:  	
	 *	- On Home Page, click on Driving Surveys -& View Survey
	 *	- Display: Indication ON
	 *	- All other options OFF
	 *	- Map View: Map
	 *	- GIS: All ON
	 * Results:
	 *	- All other survey data is not displayed
	 */
	@Test
	public void TC409_SurveyView_ViewIndicationsDataMapViewOperatorSurveyWhenBoundariesPipeLoaded() throws Exception {
		Log.info("\nRunning TC409_SurveyView_ViewIndicationsDataMapViewOperatorSurveyWhenBoundariesPipeLoaded - "+ 
		          "Test Description: View Indications data in map view for operator survey when boundaries and pipe loaded\n");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);		
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsNotPresentOnMap(Resources.getResource(ResourceKeys.Survey_IsotopicCanceled), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));		
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		
		surveyViewPage.setZoomLevelForAssets();
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC410_SurveyView_ViewFOVDataMapViewOperatorSurveyWhenGISDataLoaded
	 * Test Description: View FOV data in map view for operator survey when boundaries and assets is loaded
	 * Script:  	
	 *	- On Home Page, click on Driving Surveys -& View Survey
	 *	- Display: FOV ON
	 *	- All other options OFF
	 *	- Map View: Map
	 *	- GIS: All ON
	 * Results:
	 *  - FOV and breadcrumb is displayed on map along with assets and boundaries data
	 *	- All other survey data is not displayed
	 */
	@Test
	public void TC410_SurveyView_ViewFOVDataMapViewOperatorSurveyWhenGISDataLoaded() throws Exception {
		Log.info("\nRunning TC410_SurveyView_ViewFOVDataMapViewOperatorSurveyWhenGISDataLoaded - "+ 
		          "Test Description: View FOV data in map view for operator survey when boundaries and assets is loaded\n");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);

		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsNotPresentOnMap(Resources.getResource(ResourceKeys.Survey_IsotopicCanceled), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));		
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));		
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		
		surveyViewPage.setZoomLevelForAssets();
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC420_SurveyView_ViewAllDataRapidResponseSurveyWhenGISDataLoadedAllDisplayOptionsAreON
	 * Test Description: View all data for Rapid Response survey when GIS data is loaded and all display options are ON
	 * Script:  	
	 *	- On Home Page, click on Driving Surveys -& View Survey
	 *	- Display: All ON
	 *	- Map View: Map
	 *	- GIS: All ON
	 * Results:
	 *	- Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map
	 *	- All pipes and boundaries data are displayed
	 */
	@Test
	public void TC420_SurveyView_ViewAllDataRapidResponseSurveyWhenGISDataLoadedAllDisplayOptionsAreON() throws Exception {
		Log.info("\nRunning TC420_SurveyView_ViewAllDataRapidResponseSurveyWhenGISDataLoadedAllDisplayOptionsAreON - "+ 
		          "Test Description: View all data for Rapid Response survey when GIS data is loaded and all display options are ON\n");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);		
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_RAPID_RESP_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_RAPID_RESP_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_RAPID_RESP_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));

        assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_IsotopicCanceled), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		
		surveyViewPage.setZoomLevelForAssets();
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC421_SurveyView_ViewRapidRepsonseSurveyWhenAllGISDisplayOptionsAreOFF
	 * Test Description:  View Rapid Repsonse Survey when all GIS and display options are OFF
	 * Script:  	
	 *	- On Home Page, click on Driving Surveys -& View Survey
	 *	- Display: All OFF
	 *	- Map View: Map
	 *	- GIS: All OFF
	 * Results:
	 *  - Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class, Surveyor and analyzer info, Start Time, End Time
     *  - User  should see only Breadcrumb in map view
	 *	- All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC421_SurveyView_ViewRapidRepsonseSurveyWhenAllGISDisplayOptionsAreOFF() throws Exception {
		Log.info("\nRunning TC421_SurveyView_ViewRapidRepsonseSurveyWhenAllGISDisplayOptionsAreOFF - "+ 
		          "Test Description:  View Rapid Repsonse Survey when all GIS and display options are OFF\n");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);		
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_RAPID_RESP_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_RAPID_RESP_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_RAPID_RESP_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsNotPresentOnMap(Resources.getResource(ResourceKeys.Survey_IsotopicCanceled), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		
		surveyViewPage.setZoomLevelForAssets();
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC422_SurveyView_ViewRapidResponseSurveySatelliteViewWhenGISDataLoadedAllDisplayOptionsAreON
	 * Test Description: View Rapid Response survey in satellite view when GIS data is loaded and all display options are ON
	 * Script:  	
	 *	- On Home Page, click on Driving Surveys -& View Survey
	 *	- Display: All ON
	 *	- Map View: Satellite
	 *	- GIS: All ON
	 * Results:
	 *	- Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on mapin satellite view
	 *	- All pipes and boundaries data are displayed
	 */
	@Test
	public void TC422_SurveyView_ViewRapidResponseSurveySatelliteViewWhenGISDataLoadedAllDisplayOptionsAreON() throws Exception {
		Log.info("\nRunning TC422_SurveyView_ViewRapidResponseSurveySatelliteViewWhenGISDataLoadedAllDisplayOptionsAreON - "+ 
		          "Test Description: View Rapid Response survey in satellite view when GIS data is loaded and all display options are ON\n");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);		
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_RAPID_RESP_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_RAPID_RESP_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_RAPID_RESP_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_IsotopicCanceled), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		
		surveyViewPage.setZoomLevelForAssets();
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC423_SurveyView_ViewIndicationsDataRapidResponseSurveyWhenGISDataLoaded
	 * Test Description:  View Indications data for Rapid Response survey when GIS data is loaded
	 * Script:  	
	 *	- On Home Page, click on Driving Surveys -& View Survey
	 *	- Display: Indication ON
	 *	- All other options OFF
	 *	- Map View: Map
	 *	- GIS: All ON
	 * Results:
	 *  - Indications and breadcrumb is displayed on map along with assets and boundaries data
	 *	- All other survey data is not displayed
	 */
	@Test
	public void TC423_SurveyView_ViewIndicationsDataRapidResponseSurveyWhenGISDataLoaded() throws Exception {
		Log.info("\nRunning TC423_SurveyView_ViewIndicationsDataRapidResponseSurveyWhenGISDataLoaded - "+ 
		          "Test Description:  View Indications data for Rapid Response survey when GIS data is loaded\n");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);		
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		
        assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsNotPresentOnMap(Resources.getResource(ResourceKeys.Survey_IsotopicCanceled), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));		
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		
		surveyViewPage.setZoomLevelForAssets();
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC424_SurveyView_ViewFOVDataRapidResponseSurveyWhenGISDataLoaded
	 * Test Description: View FOV data for Rapid Response survey when GIS data is loaded
	 * Script:  	
	 *	- On Home Page, click on Driving Surveys -& View Survey
	 *	- Display: FOV ON
	 *	- All other options OFF
	 *	- Map View: Map
	 *	- GIS: All ON
	 * Results:
	 *  - FOV and breadcrumb is displayed on map along with assets and boundaries data
	 *	- All other survey data is not displayed
	 */
	@Test
	public void TC424_SurveyView_ViewFOVDataRapidResponseSurveyWhenGISDataLoaded() throws Exception {
		Log.info("\nRunning TC424_SurveyView_ViewFOVDataRapidResponseSurveyWhenGISDataLoaded - "+ 
		          "Test Description: View FOV data for Rapid Response survey when GIS data is loaded\n");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);		
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsNotPresentOnMap(Resources.getResource(ResourceKeys.Survey_IsotopicCanceled), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));		
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));		
		assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		
		surveyViewPage.setZoomLevelForAssets();
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC425_SurveyView_ViewFOVIndicationsLisasSurveyDataRapidResponseSurveyWhenAssetsAreLoaded
	 * Test Description:  View FOV, Indications, LISAs, survey data for Rapid Response survey when assets are loaded
	 * Script:  	
	 *	- On Home Page, click on Driving Surveys -& View Survey
	 *	- Display: All ON
	 *	- Map View: Map
	 *	- GIS: Assets ON
	 *	- Boundaries OFF
	 * Results:
	 *	- Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map
	 *	- All pipes data is displayed. Boundaries are not present
	 */
	@Test
	public void TC425_SurveyView_ViewFOVIndicationsLisasSurveyDataRapidResponseSurveyWhenAssetsAreLoaded() throws Exception {
		Log.info("\nRunning TC425_SurveyView_ViewFOVIndicationsLisasSurveyDataRapidResponseSurveyWhenAssetsAreLoaded - "+ 
		          "Test Description:  View FOV, Indications, LISAs, survey data for Rapid Response survey when assets are loaded\n");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);		
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssets(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_RAPID_RESP_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_RAPID_RESP_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_RAPID_RESP_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_IsotopicCanceled), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		
		surveyViewPage.setZoomLevelForAssets();
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC426_SurveyView_ViewFOVIndicationsLisasDataRapidResponseSurveyWhenBoundariesAreLoaded
	 * Test Description: View FOV, Indications and LISAs data for Rapid Response survey when boundaries are loaded
	 * Script:  	
	 *	- On Home Page, click on Driving Surveys -& View Survey
	 *	- Display: All ON
	 *	- Map View: Map
	 *	- GIS: Assets OFF
	 *	- Boundaries ON

	 * Results:
	 *	- Survey Information is displayed in map view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map
	 *	- Only boundaries are displayed. Assets are not displayed
	 */
	@Test
	public void TC426_SurveyView_ViewFOVIndicationsLisasDataRapidResponseSurveyWhenBoundariesAreLoaded() throws Exception {
		Log.info("\nRunning TC426_SurveyView_ViewFOVIndicationsLisasDataRapidResponseSurveyWhenBoundariesAreLoaded - "+ 
		          "Test Description: View FOV, Indications and LISAs data for Rapid Response survey when boundaries are loaded\n");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);		
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssets(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_RAPID_RESP_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_RAPID_RESP_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_RAPID_RESP_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsPresentOnMap(Resources.getResource(ResourceKeys.Survey_IsotopicCanceled), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		
		surveyViewPage.setZoomLevelForAssets();
		assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
	}
 
	/**
	 * Test Case ID: TC427_SurveyView_ViewIndicationsLisasRapidResponseSurveyWhenGISDataLoaded
	 * Test Description: View Indications and LISAs for Rapid response survey when GIS data is loaded
	 * Script:  	
	 *	- On Home Page, click on Driving Surveys -& View Survey
	 *	- Display: Indications ON
	 *	- LISA ON
	 *	- All other options OFF
	 *	- Map View: Map
	 *	- GIS: All ON
	 * Results:
	 *  - Indications, Lisa and breadcrumb is displayed on map along with assets and boundaries data
	 *	- All other survey data is not displayed
	 */
	@Test
	public void TC427_SurveyView_ViewIndicationsLisasRapidResponseSurveyWhenGISDataLoaded() throws Exception {
		Log.info("\nRunning TC427_SurveyView_ViewIndicationsLisasRapidResponseSurveyWhenGISDataLoaded - "+ 
		          "Test Description: View Indications and LISAs for Rapid response survey when GIS data is loaded\n");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);	
		surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		
		assertTrue(surveyViewPageAction.verifyIsotopicCaptureResultIsNotPresentOnMap(Resources.getResource(ResourceKeys.Survey_IsotopicCanceled), NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));		
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(FIELD_NOTE_ISOTOPIC_CAPTURE_CANCELLED, NOTSET));
		
		surveyViewPage.setZoomLevelForAssets();
		assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}

}

