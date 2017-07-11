package surveyor.regression.source;

import org.junit.Test;
import common.source.Log;
import surveyor.scommon.entities.FeatureInfoEntity;
import surveyor.scommon.source.BaseMapViewPage.FeatureInfo;

import static org.junit.Assert.*;
import java.io.IOException;

/**
 * This class contains survey view Analytics tests.
 * @author spulikkal
 *
 */
public class SurveyViewPageTest_Analytics extends BaseSurveyViewPageTest {

	public SurveyViewPageTest_Analytics() throws IOException {
		super();
	}

	/**
	 *	Test Case ID: TC2357
	 *	Test Case Description: Survey View - No Field Notes option for Analytics Surveys
	 *	SCRIPT:
	 *	- Log into the UI as Picarro Admin
	 *	- Navigate to the Driving Surveys page
	 *	- Select a survey that has visible indications
	 *	- Click on one or more of the indications
	 *	- Click on Display
	 *	RESULT:
	 *	- Upon clicking an indication, a popup should appear with details about that leak. The popup should not have a button for adding Field Notes
	 *	- The Display menu does not have an option for Field Notes
	 */
	@Test
	public void TC2357_SurveyView_NoFieldNotesOptionForAnalyticsSurveys() throws Exception {
		Log.info("\nRunning TC2357_SurveyView_NoFieldNotesOptionForAnalyticsSurveys ...");

		final String expectedAmplitude = "0.18 ppm";
		final String expectedClassificationConfidence = "95.00 %";
		final String expectedDisposition = "Natural Gas";
		final String expectedEthaneRatio = "6.78  1.00 %";
		final String expectedMethaneConcentration = "2.15 ppm";

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */

		getSurveyViewPageAction().open(TEST_SURVEY_ANALYTICS1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().getSurveyViewPage().waitForAJAXCallsToComplete();
		getTestEnvironmentAction().idleForSeconds("5", NOTSET);   // hold execution to wait for indication bubbles to settle.

		getSurveyViewPageAction().clickOnFirst3300IndicationShownOnMap("NaturalGas", NOTSET);
		getSurveyViewPageAction().waitForFeatureInfoPopupToOpen(EMPTY, NOTSET);

		FeatureInfo.Field[] expectedFields = { FeatureInfo.Field.Disposition,
				FeatureInfo.Field.ClassificationConfidence, FeatureInfo.Field.MethaneConcentration,
				FeatureInfo.Field.EthaneRatio, FeatureInfo.Field.Amplitude};
		FeatureInfoEntity fiEntity = getSurveyViewPageAction().getSurveyViewPage().getFeatureInfoDialogTextAsObject(expectedFields);

		assertTrue(getSurveyViewPageAction().getSurveyViewPage().verifyFeatureInfoPopupAmplitudeEquals(fiEntity.getAmplitude(), expectedAmplitude));
		assertTrue(getSurveyViewPageAction().getSurveyViewPage().verifyFeatureInfoPopupClassificationConfidenceEquals(fiEntity.getClassificationConfidence(), expectedClassificationConfidence));
		assertTrue(getSurveyViewPageAction().getSurveyViewPage().verifyFeatureInfoPopupDispositionEquals(fiEntity.getDisposition(), expectedDisposition));
		assertTrue(getSurveyViewPageAction().getSurveyViewPage().verifyFeatureInfoPopupEthaneRatioEquals(fiEntity.getEthaneRatio(), expectedEthaneRatio));
		assertTrue(getSurveyViewPageAction().getSurveyViewPage().verifyFeatureInfoPopupMethaneConcEquals(fiEntity.getMethaneConcentration(), expectedMethaneConcentration));

		assertFalse(getSurveyViewPageAction().verifyFeatureInfoPopupAddFieldNotesButtonIsVisible(EMPTY, NOTSET));

		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyDisplaySwitchNotesButtonIsNotVisible(EMPTY, NOTSET));
	}

	/**
	 *	Test Case ID: TC2366
	 *	Test Case Description: Survey View - Analytics Survey mode has no Capture or Ref Gas features
	 *	Analytics Survey mode will not have the option of running Ref Gas Measurements or Captures
	 *	SCRIPT:
	 *	- Log into the UI
	 *	- Select an Analytics Survey and click on the View Survey button
	 *	- Click on the Display button
	 *	RESULT:
	 *	- The Display menu will not have an option for Analysis Results
	 */
	@Test
	public void TC2366_SurveyView_AnalyticsSurveyModeHasNoCaptureOrRefGasFeatures() throws Exception {
		Log.info("\nRunning TC2366_SurveyView_AnalyticsSurveyModeHasNoCaptureOrRefGasFeatures ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */

		getSurveyViewPageAction().open(TEST_SURVEY_ANALYTICS1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().getSurveyViewPage().waitForAJAXCallsToComplete();

		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyDisplaySwitchIsotopicAnalysisButtonIsNotVisible(EMPTY, NOTSET));
	}

	/**
	 *	Test Case ID: TC2367
	 *	Test Case Description: Survey View - "Analytics Survey Active" is displayed
	 *	Survey View shows indication that Analytics Survey is being run
	 *	SCRIPT:
	 *	- Log into UI
	 *	- Navigate to Driving Surveys page
	 *	- Select an Analytics Survey and click on View Survey button
	 *	RESULT:
	 *	- In Survey View, "Analytics Survey Active" appears in bold green font at top left of map and "Mode: Analytics" appears in the Survey Information block at top
	**/
	@Test
	public void TC2367_SurveyView_AnalyticsSurveyActiveIsDisplayed() throws Exception {
		Log.info("\nRunning TC2367_SurveyView_AnalyticsSurveyActiveIsDisplayed ...");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */

		getSurveyViewPageAction().open(TEST_SURVEY_ANALYTICS1_ID, NOTSET);
		getSurveyViewPageAction().verifyPageLoaded(EMPTY, NOTSET);
		getSurveyViewPageAction().getSurveyViewPage().waitForAJAXCallsToComplete();

		getSurveyViewPageAction().clickOnDisplayButton(EMPTY, NOTSET);
		assertTrue(getSurveyViewPageAction().verifyDisplaySwitchIsotopicAnalysisButtonIsNotVisible(EMPTY, NOTSET));

		assertTrue(getSurveyViewPageAction().verifyCorrectSurveyActiveMessageIsShownOnMap(EMPTY, NOTSET));
		assertTrue(getSurveyViewPageAction().verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_ANALYTICS1_TYPE, NOTSET));
	}
}