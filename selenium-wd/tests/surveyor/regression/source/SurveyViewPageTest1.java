package surveyor.regression.source;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import common.source.Log;

public class SurveyViewPageTest1 extends BaseSurveyViewPageTest {

	public SurveyViewPageTest1() throws IOException {
		super();
	}

	/**
	 * Test Case ID: TC950_SurveyView_ViewOperatorSurveySurveyMapViewWhenAllDisplayOptionsAreONOtherPlasticProtectedSteelCastIronAssetsONBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All options - ON
	 *	- - Click GIS
	 *	- Assets: Other Plastic, Protected steel, Cast iron - ON
	 *	- Boundaries: OFF
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- - Breadcrumb, Indications, LISA, FOV and survey information is displayed on map
	 *  - Other Plastic, Protected steel and Cast iron assets are displayed on map and other assets are not displayed
	 *	- - Boundaries not displayed on map
	 */
	@Test
	public void TC950_SurveyView_ViewOperatorSurveySurveyMapViewWhenAllDisplayOptionsAreONOtherPlasticProtectedSteelCastIronAssetsONBoundariesOFF() throws Exception {
		Log.info("\nRunning TC950_SurveyView_ViewOperatorSurveySurveyMapViewWhenAllDisplayOptionsAreONOtherPlasticProtectedSteelCastIronAssetsONBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypeOtherPlastic(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypeProtectedSteel(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypeCastIron(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_OPERATOR1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_OPERATOR1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_OPERATOR1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		
		// TODO: Assets don't have unique IDs in UI. Specific assets checks cannot be currently done from UI.
		// Specific Assets check is tracked as an API test.
	}
 
	/**
	 * Test Case ID: TC951_SurveyView_ViewOperatorSurveySurveySatelliteViewWhenAllDisplayOptionsAreONPEPlasticUn_ProtectedSteelCopperAssetsONBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All options - ON
	 *	- - Click GIS
	 *	- Assets: PE Plastic, Un-Protected Steel, Copper - ON
	 *	- Boundaries: OFF
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *	- - Breadcrumb, Indications, LISA, FOV and survey information is displayed on map in satellite view
	 *	- - PE Plastic, Un-Protected Steel and Copper assets are displayed on map in satellite view and other assets are not displayed
	 *	- - Boundaries not displayed on map in satellite view
	 */
	@Test
	public void TC951_SurveyView_ViewOperatorSurveySurveySatelliteViewWhenAllDisplayOptionsAreONPEPlasticUn_ProtectedSteelCopperAssetsONBoundariesOFF() throws Exception {
		Log.info("\nRunning TC951_SurveyView_ViewOperatorSurveySurveySatelliteViewWhenAllDisplayOptionsAreONPEPlasticUn_ProtectedSteelCopperAssetsONBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypePEPlastic(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypeUnprotectedSteel(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMaterialTypeCopper(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_OPERATOR1_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_OPERATOR1_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_OPERATOR1_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));

		// TODO: Assets don't have unique IDs in UI. Specific assets checks cannot be currently done from UI.
		// Specific Assets check is tracked as an API test.
	}
 
	/**
	 * Test Case ID: TC952_SurveyView_ViewOperatorSurveySurveyMapViewWhenIndicationsONAssetsBoundariesOFF
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
	public void TC952_SurveyView_ViewOperatorSurveySurveyMapViewWhenIndicationsONAssetsBoundariesOFF() throws Exception {
		Log.info("\nRunning TC952_SurveyView_ViewOperatorSurveySurveyMapViewWhenIndicationsONAssetsBoundariesOFF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
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
}
