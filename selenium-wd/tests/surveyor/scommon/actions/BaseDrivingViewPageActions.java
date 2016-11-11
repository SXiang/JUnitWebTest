package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;
import common.source.DateUtility;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.scommon.source.BaseDrivingViewPage;

public class BaseDrivingViewPageActions extends BaseMapViewPageActions {
	
	private static final String FN_VERIFY_SURVEY_INFO_ANALYZER_LABEL_EQUALS = "verifySurveyInfoAnalyzerLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_SURVEYOR_LABEL_EQUALS = "verifySurveyInfoSurveyorLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_DRIVER_LABEL_EQUALS = "verifySurveyInfoDriverLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_STABILITY_CLASS_LABEL_EQUALS = "verifySurveyInfoStabilityClassLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_ZOOM_LEVEL_LABEL_EQUALS = "verifySurveyInfoZoomLevelLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_SURVEY_STATUS_LABEL_EQUALS = "verifySurveyInfoSurveyStatusLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_TIME_LABEL_STARTS_WITH = "verifySurveyInfoTimeLabelStartsWith";
	private static final String FN_VERIFY_SURVEY_INFO_TIME_LABEL_EQUALS = "verifySurveyInfoTimeLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_TIME_REMAINING_LABEL_EQUALS = "verifySurveyInfoTimeRemainingLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_TIME_ELAPSED_LABEL_EQUALS = "verifySurveyInfoTimeElapsedLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_TIME_ELAPSED_LABEL_STARTS_WITH = "verifySurveyInfoTimeElapsedLabelStartsWith";
	private static final String FN_VERIFY_SURVEY_INFO_TIME_REMAINING_LABEL_STARTS_WITH = "verifySurveyInfoTimeRemainingLabelStartsWith";
	private static final String FN_VERIFY_SURVEY_INFO_MODE_LABEL_EQUALS = "verifySurveyInfoModeLabelEquals";

	private static final String CLS_BASE_DRIVING_VIEW_PAGE_ACTIONS = "BaseDrivingViewPageActions::";
	
	public BaseDrivingViewPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup);
	}

	public boolean clickOnPositionButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnPositionButton", data, dataRowID);
		getBaseDrivingViewPage().clickPositionButton();
		return true;
	}

	public boolean clickOnStatusButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnStatusButton", data, dataRowID);
		getBaseDrivingViewPage().clickStatusButton();
		return true;
	}
	
	public boolean clickOnHeaderInfoBox(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnHeaderInfoBox", data, dataRowID);
		getBaseDrivingViewPage().clickHeaderInfoBox();
		TestContext.INSTANCE.getTestSetup().slowdownInSeconds(TestContext.INSTANCE.getTestSetup().getSlowdownInSeconds());
		return true;
	}

	/* Position Button */
	public boolean turnOnPosition(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnPosition", data, dataRowID);
		getBaseDrivingViewPage().togglePositionButton(true);
		return true;
	}

	public boolean turnOffPosition(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffPosition", data, dataRowID);
		getBaseDrivingViewPage().togglePositionButton(false);
		return true;
	}

	public boolean verifyAnemometerButtonIsGreen(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyAnemometerButtonIsGreen", data, dataRowID);
		return getBaseDrivingViewPage().isAnemometerButtonGreen();
	}

	public boolean verifyAnemometerButtonIsRed(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyAnemometerButtonIsRed", data, dataRowID);
		return getBaseDrivingViewPage().isAnemometerButtonRed();
	}
	
	public boolean verifyWindRoseIsShownOnMap(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyWindRoseIsShownOnMap", data, dataRowID);
		return this.getBaseDrivingViewPage().isWindRoseShown();
	}
	public boolean verifyWindRoseIsNotShownOnMap(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyWindRoseIsNotShownOnMap", data, dataRowID);
		return !this.getBaseDrivingViewPage().isWindRoseShown();
	}

	public boolean waitForConnectionToComplete(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".waitForPageLoad", data, dataRowID);
		getBaseDrivingViewPage().waitForPageLoad();
		getBaseDrivingViewPage().waitForConnectionComplete();
		return true;
	}
	
	public boolean verifyPageLoaded(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyPageLoaded", data, dataRowID);
		this.getBaseDrivingViewPage().waitForPageLoad();
		this.getBaseDrivingViewPage().waitForConnectionComplete();
		return true;
	}

	public boolean verifyPositionButtonIsGreen(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyPositionButtonIsGreen", data, dataRowID);
		return getBaseDrivingViewPage().isPositionButtonGreen();
	}

	public boolean verifyPositionButtonIsNotSelected(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyPositionButtonIsNotSelected", data, dataRowID);
		return !getBaseDrivingViewPage().isPositionButtonSelected();
	}

	public boolean verifyPositionButtonIsSelected(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyPositionButtonIsSelected", data, dataRowID);
		return getBaseDrivingViewPage().isPositionButtonSelected();
	}

	public boolean verifyPressureButtonIsGreen(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyPressureButtonIsGreen", data, dataRowID);
		return getBaseDrivingViewPage().isPressureButtonGreen();
	}

	public boolean verifyPressureButtonIsRed(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyPressureButtonIsRed", data, dataRowID);
		return getBaseDrivingViewPage().isPressureButtonRed();
	}

	public boolean verifyStatusButtonIsGreen(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyStatusButtonIsGreen", data, dataRowID);
		return getBaseDrivingViewPage().isStatusButtonGreen();
	}

	public boolean verifyStatusButtonIsGreenWithPlus(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyStatusButtonIsGreenWithPlus", data, dataRowID);
		return getBaseDrivingViewPage().isStatusButtonGreenWithPlus();
	}

	public boolean verifyStatusButtonIsRed(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyStatusButtonIsRed", data, dataRowID);
		return getBaseDrivingViewPage().isStatusButtonRed();
	}

	public boolean verifyStatusButtonIsExpanded(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyStatusButtonIsExpanded", data, dataRowID);
		return getBaseDrivingViewPage().isStatusButtonOpen();
	}

	public boolean verifyStatusButtonIsCollapsed(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyStatusButtonIsCollapsed", data, dataRowID);
		return !verifyStatusButtonIsExpanded(data, dataRowID);
	}

	public boolean verifyWBTempButtonIsGreen(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyWBTempButtonIsGreen", data, dataRowID);
		return getBaseDrivingViewPage().isWBTempButtonGreen();
	}

	public boolean verifyWBTempButtonIsRed(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyWBTempButtonIsRed", data, dataRowID);
		return getBaseDrivingViewPage().isWBTempButtonRed();
	}

	public boolean verifyFlowButtonIsGreen(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyFlowButtonIsGreen", data, dataRowID);
		return getBaseDrivingViewPage().isFlowButtonGreen();
	}

	public boolean verifyFlowButtonIsRed(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyFlowButtonIsRed", data, dataRowID);
		return getBaseDrivingViewPage().isFlowButtonRed();
	}

	public boolean verifyGPSButtonIsGreen(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGPSButtonIsGreen", data, dataRowID);
		return getBaseDrivingViewPage().isGPSButtonGreen();
	}

	public boolean verifyGPSButtonIsRed(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGPSButtonIsRed", data, dataRowID);
		return getBaseDrivingViewPage().isGPSButtonRed();
	}

	public boolean verifyHBTempButtonIsGreen(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyHBTempButtonIsGreen", data, dataRowID);
		return getBaseDrivingViewPage().isHBTempButtonGreen();
	}

	public boolean verifyHBTempButtonIsRed(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyHBTempButtonIsRed", data, dataRowID);
		return getBaseDrivingViewPage().isHBTempButtonRed();
	}

	/* SurveyInfo - Labels */
	public boolean verifySurveyInfoModeLabelEquals(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoModeLabelEquals", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASE_DRIVING_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_MODE_LABEL_EQUALS, ARG_DATA, data);
		log(String.format("Looking for Text-[%s], Found Survey Mode Label Text-[%s]", data, getBaseDrivingViewPage().getSurveyModeLabelText()));
		return getBaseDrivingViewPage().getSurveyModeLabelText().equals(data);
	}
	public boolean verifySurveyInfoSurveyStatusLabelEquals(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoSurveyStatusLabelEquals", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASE_DRIVING_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_SURVEY_STATUS_LABEL_EQUALS, ARG_DATA, data);
		log(String.format("Looking for Text-[%s], Found Survey Status Label Text-[%s]", data, getBaseDrivingViewPage().getSurveyStatusLabelText()));
		return getBaseDrivingViewPage().getSurveyStatusLabelText().equals(data);
	}
	public boolean verifySurveyInfoTimeLabelStartsWith(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoTimeLabelStartsWith", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASE_DRIVING_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_TIME_LABEL_STARTS_WITH, ARG_DATA, data);
		log(String.format("Looking for Text-[%s], Found Time Label Text-[%s]", data, getBaseDrivingViewPage().getTimeLabelText()));
		return getBaseDrivingViewPage().getTimeLabelText().startsWith(data);
	}
	public boolean verifySurveyInfoTimeLabelEquals(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoTimeLabelEquals", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASE_DRIVING_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_TIME_LABEL_EQUALS, ARG_DATA, data);
		log(String.format("Looking for Text-[%s], Found Time Label Text-[%s]", data, getBaseDrivingViewPage().getTimeElapsedLabelText()));
		return getBaseDrivingViewPage().getTimeElapsedLabelText().equals(data);
	}
	public boolean verifySurveyInfoTimeElapsedLabelEquals(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoTimeElapsedLabelEquals", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASE_DRIVING_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_TIME_ELAPSED_LABEL_EQUALS, ARG_DATA, data);
		log(String.format("Looking for Text-[%s], Found Time Elapsed Label Text-[%s]", data, getBaseDrivingViewPage().getTimeElapsedLabelText()));
		return getBaseDrivingViewPage().getTimeElapsedLabelText().equals(data);
	}
	public boolean verifySurveyInfoTimeRemainingLabelEquals(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoTimeRemainingLabelEquals", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASE_DRIVING_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_TIME_REMAINING_LABEL_EQUALS, ARG_DATA, data);
		log(String.format("Looking for Text-[%s], Found Time Remaining Label Text-[%s]", data, getBaseDrivingViewPage().getTimeRemainingLabelText()));
		return getBaseDrivingViewPage().getTimeRemainingLabelText().equals(data);
	}
	public boolean verifySurveyInfoZoomLevelLabelEquals(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoZoomLevelLabelEquals", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASE_DRIVING_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_ZOOM_LEVEL_LABEL_EQUALS, ARG_DATA, data);
		log(String.format("Looking for Text-[%s], Found Zoom Level Label Text-[%s]", data, getBaseDrivingViewPage().getZoomLevelLabelText()));
		return getBaseDrivingViewPage().getZoomLevelLabelText().equals(data);
	}
	public boolean verifySurveyInfoStabilityClassLabelEquals(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoStabilityClassLabelEquals", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASE_DRIVING_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_STABILITY_CLASS_LABEL_EQUALS, ARG_DATA, data);
		log(String.format("Looking for Text-[%s], Found Stability Class Label Text-[%s]", data, getBaseDrivingViewPage().getStabilityClassLabelText()));
		return getBaseDrivingViewPage().getStabilityClassLabelText().equals(data);
	}
	public boolean verifySurveyInfoDriverLabelEquals(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoDriverLabelEquals", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASE_DRIVING_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_DRIVER_LABEL_EQUALS, ARG_DATA, data);
		log(String.format("Looking for Text-[%s], Found Driver Label Text-[%s]", data, getBaseDrivingViewPage().getDriverLabelText()));
		return getBaseDrivingViewPage().getDriverLabelText().equals(data);
	}
	public boolean verifySurveyInfoSurveyorLabelEquals(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoSurveyorLabelEquals", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASE_DRIVING_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_SURVEYOR_LABEL_EQUALS, ARG_DATA, data);
		log(String.format("Looking for Text-[%s], Found Surveyor Label Text-[%s]", data, getBaseDrivingViewPage().getSurveyorLabelText()));
		return getBaseDrivingViewPage().getSurveyorLabelText().equals(data);
	}
	public boolean verifySurveyInfoAnalyzerLabelEquals(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoAnalyzerLabelEquals", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASE_DRIVING_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_ANALYZER_LABEL_EQUALS, ARG_DATA, data);
		log(String.format("Looking for Text-[%s], Found Analyzer Label Text-[%s]", data, getBaseDrivingViewPage().getAnalyzerLabelText()));
		return getBaseDrivingViewPage().getAnalyzerLabelText().equals(data);
	}
	public boolean verifySurveyInfoTimeElapsedLabelStartsWith(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoTimeElapsedLabelStartsWith", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASE_DRIVING_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_TIME_ELAPSED_LABEL_STARTS_WITH, ARG_DATA, data);
		log(String.format("Looking for Text-[%s], Found Time Elapsed Label Text-[%s]", data, getBaseDrivingViewPage().getTimeElapsedLabelText()));
		return getBaseDrivingViewPage().getTimeElapsedLabelText().startsWith(data);
	}
	public boolean verifySurveyInfoTimeRemainingLabelStartsWith(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoTimeRemainingLabelStartsWith", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASE_DRIVING_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_TIME_REMAINING_LABEL_STARTS_WITH, ARG_DATA, data);
		log(String.format("Looking for Text-[%s], Found Time Remaining Label Text-[%s]", data, getBaseDrivingViewPage().getTimeRemainingLabelText()));
		return getBaseDrivingViewPage().getTimeRemainingLabelText().startsWith(data);
	}
	
	/**
	 * Executes verifySurveyInfoTimeElapsedIsTickingForward action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws InterruptedException 
	 */
	public boolean verifySurveyInfoTimeElapsedIsTickingForward(String data, Integer dataRowID) throws InterruptedException {
		logAction(getRuntimeType() + ".verifySurveyInfoTimeElapsedIsTickingForward", data, dataRowID);
		return DateUtility.isTimeTickingForward(getBaseDrivingViewPage().getTimeElapsedLabel());
	}
 
	/**
	 * Executes verifySurveyInfoTimeLabelIsTickingForward action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws InterruptedException 
	 */
	public boolean verifySurveyInfoTimeLabelIsTickingForward(String data, Integer dataRowID) throws InterruptedException {
		logAction(getRuntimeType() + ".verifySurveyInfoTimeLabelIsTickingForward", data, dataRowID);
		return DateUtility.isTimeTickingForward(getBaseDrivingViewPage().getTimeLabel());
	}
 
	/**
	 * Executes verifySurveyInfoTimeRemainingLabelIsTickingBackward action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws InterruptedException 
	 */
	public boolean verifySurveyInfoTimeRemainingLabelIsTickingBackward(String data, Integer dataRowID) throws InterruptedException {
		logAction(getRuntimeType() + ".verifySurveyInfoTimeRemainingLabelIsTickingBackward", data, dataRowID);
		return DateUtility.isTimeTickingBackward(getBaseDrivingViewPage().getTimeRemainingLabel());
	}

	/**
	 * Executes verifyRedCursorIsMovingWithCarPosition action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws InterruptedException 
	 */
	/*TBD*/
	public boolean verifyRedCursorIsMovingWithCarPosition(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyRedCursorIsMovingWithCarPosition", data, dataRowID);
		//return getBaseDrivingViewPage().isRedCursorIsMovingWithCarPosition();
		return false;
	}
	
	/**
	 * Executes verifySpikesAreDisplayed action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws InterruptedException 
	 */
	/*TBD*/
	public boolean verifySpikesAreDisplayed(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifySpikesAreDisplayed", data, dataRowID);
		//return getBaseDrivingViewPage().areSpikesDisplayed();
		return false;
	}
	
	public BaseDrivingViewPage getBaseDrivingViewPage() {
		return (BaseDrivingViewPage)this.getPageObject();
	}
}