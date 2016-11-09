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
		
		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMaterialTypeOtherPlastic(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMaterialTypeProtectedSteel(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMaterialTypeCastIron(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_OPERATOR1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_OPERATOR1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_OPERATOR1_USERNAME, NOTSET));
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
		
		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllDisplayOptions(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMaterialTypePEPlastic(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMaterialTypeUnprotectedSteel(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMaterialTypeCopper(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOffAllBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_OPERATOR1_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_OPERATOR1_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_OPERATOR1_USERNAME, NOTSET));
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
		
		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_OPERATOR1_ID, NOTSET);
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
}