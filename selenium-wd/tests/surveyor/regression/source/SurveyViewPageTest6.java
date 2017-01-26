package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.ISOTOPIC_CAPTURE_CANCELLED;
import static surveyor.scommon.source.SurveyorConstants.ISOTOPIC_CAPTURE_NOT_NATURAL_GAS;

import java.io.IOException;

import org.junit.Test;

import common.source.Log;

/**
 * This class contains survey view tests that need to be execution as single thread of execution.
 * @author spulikkal
 *
 */
public class SurveyViewPageTest6 extends BaseSurveyViewPageTest {

	public SurveyViewPageTest6() throws IOException {
		super();
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnFOVs(EMPTY, NOTSET);

		getSurveyViewPage().setZoomLevelForAssets();
		getSurveyViewPageAction().clickOnZoomInButton(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));

		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
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

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */
		getSurveyViewPageAction().open(TEST_SURVEY_ASSESSMENT1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnFOVs(EMPTY, NOTSET);

		getSurveyViewPage().setZoomLevelForAssets();
		getSurveyViewPageAction().clickOnZoomInButton(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));

		getSurveyViewPage().setZoomLevelForAssets();
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
	}
}