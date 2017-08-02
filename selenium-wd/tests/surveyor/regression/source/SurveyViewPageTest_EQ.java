package surveyor.regression.source;

import org.junit.Ignore;
import org.junit.Test;
import common.source.Log;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.entities.FeatureInfoEntity;
import surveyor.scommon.source.BaseMapViewPage.FeatureInfo;

import static org.junit.Assert.*;
import java.io.IOException;

/**
 * This class contains survey view EQ tests.
 * @author steven xiang
 *
 */
public class SurveyViewPageTest_EQ extends BaseSurveyViewPageTest {

	public SurveyViewPageTest_EQ() throws IOException {
		super();
	}

	/**
	 *	Test Case ID: TC1070_SurveyViewInSatelliteView_PicarroSupport
	 *	Test Case Description:  Survey View as Picarro support - View EQ Survey in satellite view when GIS and display options are ON
	 *	SCRIPT:
	 *	-  On Home Page, login as Picarro support role and click on Driving Surveys -> View Survey (EQ Survey)
	 *	- Click on Map and turn Satellite View ON
	 *	- Click on Display button
	 *		All options ON
	 *	- Click on GIS
	 *		All options ON
	 *	RESULT:
	 *	-  Survey Information is displayed in satellite view - Tag, EQ Mode (bold green text), Stability Class, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- The "EQ Mode Active" message in bold green font should not obscure any part of the survey details nor the map
	 *	- User  should see Breadcrumb, FOV, Indications and LISA
	 *	- All pipes and boundaries data are displayed
	 */
	@Test /* US4080 - Dev not completed - Enable verifications of Lisa/Indication when it is done */
	public void TC1070_SurveyViewInSatelliteView_PicarroSupport() throws Exception {
		Log.info("\nRunning TC1070_SurveyViewInSatelliteView_PicarroSupport ...");
		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_PICARRO_SUPPORT);   /* Picarro Support */
		getSurveyViewPageAction().open(TEST_SURVEY_EQ_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnFOVs(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnEightHourHistory(EMPTY, NOTSET);

		assertTrue(getSurveyViewPageAction().verifyDisplaySwitchConcentrationChartButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyDisplaySwitchIndicationsButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyDisplaySwitchIsotopicAnalysisButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyDisplaySwitchLisasButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyDisplaySwitchNotesButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyDisplaySwitchWindroseButtonIsNotVisible(EMPTY, NOTSET));
		
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnSatelliteView(EMPTY, NOTSET);

		assertTrue(getSurveyViewPageAction().verifySurveyModeWarningCorrect(Resources.getResource(ResourceKeys.Dialog_EQModeActive), NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_EQ_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_EQ_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_EQ_USERNAME, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER_4, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR_4, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_F, NOTSET));
		
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
//		assertTrue(getSurveyViewPageAction().verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
//		assertTrue(getSurveyViewPageAction().verifyLISAIsShownOnMap(EMPTY, NOTSET));
		
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		getSurveyViewPageAction().getSurveyViewPage().setZoomLevelForAssets();
		assertTrue(getSurveyViewPageAction().verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}

	/**
	 *	Test Case ID: TC1077_SurveyViewInMapView_CustomerDriver
	 *	Test Case Description:   	Survey View Customer Driver role- View EQ Survey in map view when GIS and display options are ON
	 *	SCRIPT:
	 *	- On Home Page by login as Customer Driver and  click on Driving Surveys -> View Survey (EQ Survey)
	 *	- Click on Map and turn Map View ON
	 *	- Click on Display button
	 *		All options ON
	 *	- Click on GIS
	 *		All options ON
	 *	RESULT:
	 *	- Survey Information is displayed in map view - Tag, EQ Mode (bold green text), Stability Class, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- The "EQ Mode Active" message in bold green font should be visible at top left of the map
	 *	- Display should show only FOV option.
	 *	- User  should see Breadcrumb, FOV.
	 *	- User should not see LISA or Indications on the survey view.
	 *	- All pipes and boundaries data are displayed
	 */
	@Ignore /* DE3189 seed data missing */
	public void TC1077_SurveyViewInMapView_CustomerDriver() throws Exception {
		Log.info("\nRunning TC1077_SurveyViewInMapView_CustomerDriver ...");
		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, USER_ROW_ID_SQACUS_DRIVER);   /* SQACUS Driver */
		getSurveyViewPageAction().open(TEST_SURVEY_EQ_ID_SQACUS, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnZoomOutButton(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnFOVs(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnEightHourHistory(EMPTY, NOTSET);
		
		assertTrue(getSurveyViewPageAction().verifyDisplaySwitchConcentrationChartButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyDisplaySwitchIndicationsButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyDisplaySwitchIsotopicAnalysisButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyDisplaySwitchLisasButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyDisplaySwitchNotesButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyDisplaySwitchWindroseButtonIsNotVisible(EMPTY, NOTSET));
		
		getSurveyViewPageAction().clickOnGisButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
		getSurveyViewPageAction().clickOnMapButton(EMPTY, NOTSET);
		getSurveyViewPageAction().turnOnMapView(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifySurveyModeWarningCorrect(Resources.getResource(ResourceKeys.Dialog_EQModeActive), NOTSET));
        assertTrue(getSurveyViewPageAction().verifySurveyInfoTagLabelEquals(TEST_SURVEY_EQ_TAG, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_EQ_TYPE, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_EQ_USERNAME_SQACUS, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER_6, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR_6, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_F, NOTSET));
		
		assertTrue(getSurveyViewPageAction().verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyFOVIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		
		assertTrue(getSurveyViewPageAction().verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
		getSurveyViewPageAction().getSurveyViewPage().setZoomLevelForAssets();
		assertTrue(getSurveyViewPageAction().verifyAssetIsShownOnMap(EMPTY, NOTSET));
	}
}