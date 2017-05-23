package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;

import common.source.BrowserCommands;
import common.source.DateUtility;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.scommon.actions.data.DriverViewDataReader;
import surveyor.scommon.source.SurveyViewPage;

public class SurveyViewPageActions extends BaseMapViewPageActions {
	private static final String FN_VERIFY_SURVEY_INFO_ANALYZER_LABEL_EQUALS = "verifySurveyInfoAnalyzerLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_SURVEYOR_LABEL_EQUALS = "verifySurveyInfoSurveyorLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_DRIVER_LABEL_EQUALS = "verifySurveyInfoDriverLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_STABILITY_CLASS_LABEL_EQUALS = "verifySurveyInfoStabilityClassLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_START_TIME_LABEL_EQUALS = "verifySurveyInfoStartTimeLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_START_TIME_LABEL_STARTS_WITH = "verifySurveyInfoStartTimeLabelStartsWith";
	private static final String FN_VERIFY_SURVEY_INFO_END_TIME_LABEL_EQUALS = "verifySurveyInfoEndTimeLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_END_TIME_LABEL_STARTS_WITH = "verifySurveyInfoEndTimeLabelStartsWith";
	private static final String FN_VERIFY_SURVEY_INFO_MODE_LABEL_EQUALS = "verifySurveyInfoModeLabelEquals";
	private static final String SURVEY_INFO_START_TIME_PREFIX = "Start Time: ";
	private static final String SURVEY_INFO_END_TIME_PREFIX = "End Time: ";
	private static final String CLS_SURVEY_VIEW_PAGE_ACTIONS = "SurveyViewPageActions::";

	// Use the Driver view data reader as the input could be read from DriverViewTestData.
	private DriverViewDataReader dataReader = null;

	public SurveyViewPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup);
		initializePageObject(driver, new SurveyViewPage(driver, strBaseURL, testSetup));
		setDataReader(new DriverViewDataReader(this.excelUtility));
	}

	/**
	 * Executes open action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean open(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".open", data, dataRowID);
		getSurveyViewPage().setSurveyId(data);
		getSurveyViewPage().open();
		return true;
	}

	/**
	 * Executes refreshPage action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean refreshPage(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".refreshPage", data, dataRowID);
		BrowserCommands.refresh();
		SurveyViewPage surveyViewPage = new SurveyViewPage(TestContext.INSTANCE.getDriver(),
				TestContext.INSTANCE.getBaseUrl(),
				TestContext.INSTANCE.getTestSetup());
		initializePageObject(TestContext.INSTANCE.getDriver(), surveyViewPage);
		return true;
	}

	@Override
	public boolean turnOffAllDisplayOptions(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffAllDisplayOptions", data, dataRowID);
		turnOffFOVs(data, dataRowID);
		turnOffIndications(data, dataRowID);
		turnOffIsotopicAnalysis(data, dataRowID);
		turnOffLisas(data, dataRowID);
		turnOffNotes(data, dataRowID);
		return true;
	}

	@Override
	public boolean turnOnAllDisplayOptions(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnAllDisplayOptions", data, dataRowID);
		turnOnFOVs(data, dataRowID);
		turnOnIndications(data, dataRowID);
		turnOnIsotopicAnalysis(data, dataRowID);
		turnOnLisas(data, dataRowID);
		turnOnNotes(data, dataRowID);
		return true;
	}

	/**
	 * Executes verifySurveyViewPageIsOpened action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyPageLoaded(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyPageLoaded", data, dataRowID);
		this.getSurveyViewPage().waitForPageLoad();
		this.getSurveyViewPage().waitForUIUnBlock();
		return true;
	}

	/**
	 * Executes verifySurveyViewPageIsOpened action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySurveyViewPageIsOpened(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifySurveyViewPageIsOpened", data, dataRowID);
		return getSurveyViewPage().checkIfAtSurveyViewPage();
	}

	/* SurveyInfo - Labels */
	public boolean verifySurveyInfoTagLabelEquals(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoTagLabelEquals", data, dataRowID);
		String actualTagValue = getSurveyViewPage().getTagLabelText();
		return verifySurveyInfoTagLabelEquals(data, dataRowID, DriverViewPageActions.workingDataRow.get(), actualTagValue);
	}
	public boolean verifySurveyInfoModeLabelEquals(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoModeLabelEquals", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_SURVEY_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_MODE_LABEL_EQUALS, ARG_DATA, data);
		log(String.format("Looking for Text-[%s], Found Survey Mode Label Text-[%s]", data, getSurveyViewPage().getSurveyModeLabelText()));
		return getSurveyViewPage().getSurveyModeLabelText().equals(data);
	}
	public boolean verifySurveyInfoStartTimeLabelEquals(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoStartTimeLabelEquals", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_SURVEY_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_START_TIME_LABEL_EQUALS, ARG_DATA, data);
		log(String.format("Looking for Text-[%s], Found Start Time Label Text-[%s]", data, getSurveyViewPage().getStartTimeLabelText()));
		return getSurveyViewPage().getStartTimeLabelText().equals(data);
	}
	public boolean verifySurveyInfoEndTimeLabelEquals(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoEndTimeLabelEquals", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_SURVEY_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_END_TIME_LABEL_EQUALS, ARG_DATA, data);
		log(String.format("Looking for Text-[%s], Found End Time Label Text-[%s]", data, getSurveyViewPage().getEndTimeLabelText()));
		return getSurveyViewPage().getEndTimeLabelText().equals(data);
	}
	public boolean verifySurveyInfoStabilityClassLabelEquals(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoStabilityClassLabelEquals", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_SURVEY_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_STABILITY_CLASS_LABEL_EQUALS, ARG_DATA, data);
		log(String.format("Looking for Text-[%s], Found Stability Class Label Text-[%s]", data, getSurveyViewPage().getStabilityClassLabelText()));
		return getSurveyViewPage().getStabilityClassLabelText().equals(data);
	}
	public boolean verifySurveyInfoDriverLabelEquals(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoDriverLabelEquals", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_SURVEY_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_DRIVER_LABEL_EQUALS, ARG_DATA, data);
		log(String.format("Looking for Text-[%s], Found Driver Label Text-[%s]", data, getSurveyViewPage().getDriverLabelText()));
		return getSurveyViewPage().getDriverLabelText().equals(data);
	}
	public boolean verifySurveyInfoSurveyorLabelEquals(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoSurveyorLabelEquals", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_SURVEY_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_SURVEYOR_LABEL_EQUALS, ARG_DATA, data);
		log(String.format("Looking for Text-[%s], Found Surveyor Label Text-[%s]", data, getSurveyViewPage().getSurveyorLabelText()));
		return getSurveyViewPage().getSurveyorLabelText().equals(data);
	}
	public boolean verifySurveyInfoAnalyzerLabelEquals(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoAnalyzerLabelEquals", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_SURVEY_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_ANALYZER_LABEL_EQUALS, ARG_DATA, data);
		log(String.format("Looking for Text-[%s], Found Analyzer Label Text-[%s]", data, getSurveyViewPage().getAnalyzerLabelText()));
		return getSurveyViewPage().getAnalyzerLabelText().equals(data);
	}
	public boolean verifySurveyInfoStartTimeLabelStartsWith(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoStartTimeLabelStartsWith", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_SURVEY_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_START_TIME_LABEL_STARTS_WITH, ARG_DATA, data);
		log(String.format("Looking for Text-[%s], Found Start Time Label Text-[%s]", data, getSurveyViewPage().getStartTimeLabelText()));
		return getSurveyViewPage().getStartTimeLabelText().startsWith(data);
	}
	public boolean verifySurveyInfoEndTimeLabelStartsWith(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifySurveyInfoEndTimeLabelStartsWith", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_SURVEY_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_END_TIME_LABEL_STARTS_WITH, ARG_DATA, data);
		log(String.format("Looking for Text-[%s], Found End Time Label Text-[%s]", data, getSurveyViewPage().getEndTimeLabelText()));
		return getSurveyViewPage().getEndTimeLabelText().startsWith(data);
	}

	/**
	 * Executes verifySurveyInfoStartTimeLabelHasCorrectTimeFormat action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(String data, Integer dataRowID) {
		logAction("SurveyViewPageActions.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat", data, dataRowID);
		String startTimeText = getSurveyViewPage().getStartTimeLabelText().replace(SURVEY_INFO_START_TIME_PREFIX, "");
		return DateUtility.isValidLongDateTimeFormat(startTimeText);
	}

	/**
	 * Executes verifySurveyInfoEndTimeLabelHasCorrectTimeFormat action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(String data, Integer dataRowID) {
		logAction("SurveyViewPageActions.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat", data, dataRowID);
		String endTimeText = getSurveyViewPage().getEndTimeLabelText().replace(SURVEY_INFO_END_TIME_PREFIX, "");
		return DateUtility.isValidLongDateTimeFormat(endTimeText);
	}

	/* Invoke action using specified ActionName */
	@Override
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		if (actionName.equals("clickById")) { return this.clickById(data, dataRowID); }
		else if (actionName.equals("clickByIdAndWait")) { return this.clickByIdAndWait(data, dataRowID); }
		else if (actionName.equals("clickByXPath")) { return this.clickByXPath(data, dataRowID); }
		else if (actionName.equals("clickByXPathAndWait")) { return this.clickByXPathAndWait(data, dataRowID); }
		else if (actionName.equals("clickOnCurtainArrowDownButton")) { return this.clickOnCurtainArrowDownButton(data, dataRowID); }
		else if (actionName.equals("clickOnCurtainArrowLeftButton")) { return this.clickOnCurtainArrowLeftButton(data, dataRowID); }
		else if (actionName.equals("clickOnCurtainArrowRightButton")) { return this.clickOnCurtainArrowRightButton(data, dataRowID); }
		else if (actionName.equals("clickOnCurtainArrowUpButton")) { return this.clickOnCurtainArrowUpButton(data, dataRowID); }
		else if (actionName.equals("clickOnCurtainReturnButton")) { return this.clickOnCurtainReturnButton(data, dataRowID); }
		else if (actionName.equals("clickOnCurtainZoomInButton")) { return this.clickOnCurtainZoomInButton(data, dataRowID); }
		else if (actionName.equals("clickOnCurtainZoomOutButton")) { return this.clickOnCurtainZoomOutButton(data, dataRowID); }
		else if (actionName.equals("clickOnDisplayButton")) { return this.clickOnDisplayButton(data, dataRowID); }
		else if (actionName.equals("clickOnGisButton")) { return this.clickOnGisButton(data, dataRowID); }
		else if (actionName.equals("clickOnMapButton")) { return this.clickOnMapButton(data, dataRowID); }
		else if (actionName.equals("clickOnPicarroLogoButton")) { return this.clickOnPicarroLogoButton(data, dataRowID); }
		else if (actionName.equals("clickOnZoomInButton")) { return this.clickOnZoomInButton(data, dataRowID); }
		else if (actionName.equals("clickOnZoomOutButton")) { return this.clickOnZoomOutButton(data, dataRowID); }
		else if (actionName.equals("insertTextById")) { return this.insertTextById(data, dataRowID); }
		else if (actionName.equals("insertTextByXPath")) { return this.insertTextByXPath(data, dataRowID); }
		else if (actionName.equals("open")) { return this.open(data, dataRowID); }
		else if (actionName.equals("refreshPage")) { return this.refreshPage(data, dataRowID); }
		else if (actionName.equals("selectDropDownByID")) { return this.selectDropDownByID(data, dataRowID); }
		else if (actionName.equals("selectDropDownByXPath")) { return this.selectDropDownByXPath(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByID")) { return this.selectRadioButtonByID(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByXPath")) { return this.selectRadioButtonByXPath(data, dataRowID); }
		else if (actionName.equals("showCurtainView")) { return this.showCurtainView(data, dataRowID); }
		else if (actionName.equals("sortRecordsBy")) { return this.sortRecordsBy(data, dataRowID); }
		else if (actionName.equals("turnOffAllAssets")) { return this.turnOffAllAssets(data, dataRowID); }
		else if (actionName.equals("turnOffAllBoundaries")) { return this.turnOffAllBoundaries(data, dataRowID); }
		else if (actionName.equals("turnOffAllAssetsAndBoundaries")) { return this.turnOffAllAssetsAndBoundaries(data, dataRowID); }
		else if (actionName.equals("turnOffBoundariesDistrict")) { return this.turnOffBigBoundary(data, dataRowID); }
		else if (actionName.equals("turnOffBoundariesDistrictPlat")) { return this.turnOffSmallBoundary(data, dataRowID); }
		else if (actionName.equals("turnOffConcentrationChart")) { return this.turnOffConcentrationChart(data, dataRowID); }
		else if (actionName.equals("turnOffAllDisplayOptions")) { return this.turnOffAllDisplayOptions(data, dataRowID); }
		else if (actionName.equals("turnOffEightHourHistory")) { return this.turnOffEightHourHistory(data, dataRowID); }
		else if (actionName.equals("turnOffFOVs")) { return this.turnOffFOVs(data, dataRowID); }
		else if (actionName.equals("turnOffIndications")) { return this.turnOffIndications(data, dataRowID); }
		else if (actionName.equals("turnOffIsotopicAnalysis")) { return this.turnOffIsotopicAnalysis(data, dataRowID); }
		else if (actionName.equals("turnOffLisas")) { return this.turnOffLisas(data, dataRowID); }
		else if (actionName.equals("turnOffMaterialTypeCastIron")) { return this.turnOffMaterialTypeCastIron(data, dataRowID); }
		else if (actionName.equals("turnOffMaterialTypeCopper ")) { return this.turnOffMaterialTypeCopper (data, dataRowID); }
		else if (actionName.equals("turnOffMaterialTypeOtherPlastic")) { return this.turnOffMaterialTypeOtherPlastic(data, dataRowID); }
		else if (actionName.equals("turnOffMaterialTypePEPlastic")) { return this.turnOffMaterialTypePEPlastic(data, dataRowID); }
		else if (actionName.equals("turnOffMaterialTypeProtectedSteel")) { return this.turnOffMaterialTypeProtectedSteel(data, dataRowID); }
		else if (actionName.equals("turnOffMaterialTypeUnprotectedSteel")) { return this.turnOffMaterialTypeUnprotectedSteel(data, dataRowID); }
		else if (actionName.equals("turnOffNotes")) { return this.turnOffNotes(data, dataRowID); }
		else if (actionName.equals("turnOffUseAllBoundaries")) { return this.turnOffUseAllBoundaries(data, dataRowID); }
		else if (actionName.equals("turnOffUseAllPipes")) { return this.turnOffUseAllPipes(data, dataRowID); }
		else if (actionName.equals("turnOffWindRose")) { return this.turnOffWindRose(data, dataRowID); }
		else if (actionName.equals("turnOnAllAssets")) { return this.turnOnAllAssets(data, dataRowID); }
		else if (actionName.equals("turnOnAllBoundaries")) { return this.turnOnAllBoundaries(data, dataRowID); }
		else if (actionName.equals("turnOnAllAssetsAndBoundaries")) { return this.turnOnAllAssetsAndBoundaries(data, dataRowID); }
		else if (actionName.equals("turnOnBoundariesDistrict")) { return this.turnOnBigBoundary(data, dataRowID); }
		else if (actionName.equals("turnOnBoundariesDistrictPlat")) { return this.turnOnSmallBoundary(data, dataRowID); }
		else if (actionName.equals("turnOnConcentrationChart")) { return this.turnOnConcentrationChart(data, dataRowID); }
		else if (actionName.equals("turnOnAllDisplayOptions")) { return this.turnOnAllDisplayOptions(data, dataRowID); }
		else if (actionName.equals("turnOnEightHourHistory")) { return this.turnOnEightHourHistory(data, dataRowID); }
		else if (actionName.equals("turnOnFOVs")) { return this.turnOnFOVs(data, dataRowID); }
		else if (actionName.equals("turnOnIndications")) { return this.turnOnIndications(data, dataRowID); }
		else if (actionName.equals("turnOnIsotopicAnalysis")) { return this.turnOnIsotopicAnalysis(data, dataRowID); }
		else if (actionName.equals("turnOnLisas")) { return this.turnOnLisas(data, dataRowID); }
		else if (actionName.equals("turnOnMapView")) { return this.turnOnMapView(data, dataRowID); }
		else if (actionName.equals("turnOnMaterialTypeCastIron")) { return this.turnOnMaterialTypeCastIron(data, dataRowID); }
		else if (actionName.equals("turnOnMaterialTypeCopper ")) { return this.turnOnMaterialTypeCopper (data, dataRowID); }
		else if (actionName.equals("turnOnMaterialTypeOtherPlastic")) { return this.turnOnMaterialTypeOtherPlastic(data, dataRowID); }
		else if (actionName.equals("turnOnMaterialTypePEPlastic")) { return this.turnOnMaterialTypePEPlastic(data, dataRowID); }
		else if (actionName.equals("turnOnMaterialTypeProtectedSteel")) { return this.turnOnMaterialTypeProtectedSteel(data, dataRowID); }
		else if (actionName.equals("turnOnMaterialTypeUnprotectedSteel")) { return this.turnOnMaterialTypeUnprotectedSteel(data, dataRowID); }
		else if (actionName.equals("turnOnNotes")) { return this.turnOnNotes(data, dataRowID); }
		else if (actionName.equals("turnOnSatelliteView")) { return this.turnOnSatelliteView(data, dataRowID); }
		else if (actionName.equals("turnOnUseAllBoundaries")) { return this.turnOnUseAllBoundaries(data, dataRowID); }
		else if (actionName.equals("turnOnUseAllPipes")) { return this.turnOnUseAllPipes(data, dataRowID); }
		else if (actionName.equals("turnOnWindRose")) { return this.turnOnWindRose(data, dataRowID); }
		else if (actionName.equals("verifyAnalyticsModeDialogIsShown")) { return this.verifyAnalyticsModeDialogIsShown(data, dataRowID); }
		else if (actionName.equals("verifyAnalyticsModeDialogIsNotShown")) { return this.verifyAnalyticsModeDialogIsNotShown(data, dataRowID); }
		else if (actionName.equals("verifyCorrectAnalyticsSurveyActiveMessageIsShownOnMap")) { return this.verifyCorrectAnalyticsSurveyActiveMessageIsShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyAssetIsNotShownOnMap")) { return this.verifyAssetIsNotShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyAssetIsShownOnMap")) { return this.verifyAssetIsShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyBoundariesIsNotShownOnMap")) { return this.verifyBoundariesIsNotShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyBoundariesIsShownOnMap")) { return this.verifyBoundariesIsShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyBreadcrumbIsNotShownOnMap")) { return this.verifyBreadcrumbIsNotShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyBreadcrumbIsShownOnMap")) { return this.verifyBreadcrumbIsShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyCarIconIsNotInCenter")) { return this.verifyCarIconIsNotInCenter(data, dataRowID); }
		else if (actionName.equals("verifyConcentrationChartIsNotShownOnMap")) { return this.verifyConcentrationChartIsNotShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyConcentrationChartIsShownOnMap")) { return this.verifyConcentrationChartIsShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyCrossHairIconIsNotShownOnMap")) { return this.verifyCrossHairIconIsNotShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyCrossHairIconIsShownOnMap")) { return this.verifyCrossHairIconIsShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyDisplaySwitchIsOff")) { return this.verifyDisplaySwitchIsOff(data, dataRowID); }
		else if (actionName.equals("verifyDisplaySwitchIsOn")) { return this.verifyDisplaySwitchIsOn(data, dataRowID); }
		else if (actionName.equals("verifyFeatureInfoPopupAddFieldNotesButtonIsVisible")) { return this.verifyFeatureInfoPopupAddFieldNotesButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyFeatureInfoPopupAddFieldNotesButtonIsNotVisible")) { return this.verifyFeatureInfoPopupAddFieldNotesButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyFieldNotesIsNotShownOnMap")) { return this.verifyFieldNotesIsNotShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyFieldNotesIsShownOnMap")) { return this.verifyFieldNotesIsShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyFOVIsNotShownOnMap")) { return this.verifyFOVIsNotShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyFOVIsShownOnMap")) { return this.verifyFOVIsShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyGisSwitchIsOff")) { return this.verifyGisSwitchIsOff(data, dataRowID); }
		else if (actionName.equals("verifyGisSwitchIsOn")) { return this.verifyGisSwitchIsOn(data, dataRowID); }
		else if (actionName.equals("verifyIndicationsIsNotShownOnMap")) { return this.verifyIndicationsIsNotShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyIndicationsIsShownOnMap")) { return this.verifyIndicationsIsShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyLISAIsNotShownOnMap")) { return this.verifyLISAIsNotShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyLISAIsShownOnMap")) { return this.verifyLISAIsShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyMapSwitchOn")) { return this.verifyMapSwitchOn(data, dataRowID); }
		else if (actionName.equals("verifyMapSwitchOff")) { return this.verifyMapSwitchOff(data, dataRowID); }
		else if (actionName.equals("verifyMapViewIsShown")) { return this.verifyMapViewIsShown(data, dataRowID); }
		else if (actionName.equals("verifySatelliteViewIsShown")) { return this.verifySatelliteViewIsShown(data, dataRowID); }
		else if (actionName.equals("verifyDisplaySwitch8HourHistoryButtonIsVisible")) { return this.verifyDisplaySwitch8HourHistoryButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyDisplaySwitchWindroseButtonIsVisible")) { return this.verifyDisplaySwitchWindroseButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyDisplaySwitchConcentrationChartButtonIsVisible")) { return this.verifyDisplaySwitchConcentrationChartButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyDisplaySwitchNotesButtonIsVisible")) { return this.verifyDisplaySwitchNotesButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyDisplaySwitchIsotopicAnalysisButtonIsVisible")) { return this.verifyDisplaySwitchIsotopicAnalysisButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyDisplaySwitchIndicationsButtonIsVisible")) { return this.verifyDisplaySwitchIndicationsButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyDisplaySwitchLisasButtonIsVisible")) { return this.verifyDisplaySwitchLisasButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyDisplaySwitchFovsButtonIsVisible")) { return this.verifyDisplaySwitchFovsButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyGisMaterialTypeCastIronButtonIsVisible")) { return this.verifyGisMaterialTypeCastIronButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyGisMaterialTypeCopperButtonIsVisible")) { return this.verifyGisMaterialTypeCopperButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyGisMaterialTypeOtherPlasticButtonIsVisible")) { return this.verifyGisMaterialTypeOtherPlasticButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyGisMaterialTypePEPlasticButtonIsVisible")) { return this.verifyGisMaterialTypePEPlasticButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyGisMaterialTypeProtectedSteelButtonIsVisible")) { return this.verifyGisMaterialTypeProtectedSteelButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyGisMaterialTypeUnProtectedSteelButtonIsVisible")) { return this.verifyGisMaterialTypeUnProtectedSteelButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyGisUseAllPipesButtonIsVisible")) { return this.verifyGisUseAllPipesButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyGisBoundaryBigBoundaryButtonIsVisible")) { return this.verifyGisBoundaryBigBoundaryButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyGisBoundarySmallBoundaryButtonIsVisible")) { return this.verifyGisBoundarySmallBoundaryButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyGisUseAllBoundariesButtonIsVisible")) { return this.verifyGisUseAllBoundariesButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyDisplaySwitch8HourHistoryButtonIsNotVisible")) { return this.verifyDisplaySwitch8HourHistoryButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyDisplaySwitchWindroseButtonIsNotVisible")) { return this.verifyDisplaySwitchWindroseButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyDisplaySwitchConcentrationChartButtonIsNotVisible")) { return this.verifyDisplaySwitchConcentrationChartButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyDisplaySwitchNotesButtonIsNotVisible")) { return this.verifyDisplaySwitchNotesButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyDisplaySwitchIsotopicAnalysisButtonIsNotVisible")) { return this.verifyDisplaySwitchIsotopicAnalysisButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyDisplaySwitchIndicationsButtonIsNotVisible")) { return this.verifyDisplaySwitchIndicationsButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyDisplaySwitchLisasButtonIsNotVisible")) { return this.verifyDisplaySwitchLisasButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyDisplaySwitchFovsButtonIsNotVisible")) { return this.verifyDisplaySwitchFovsButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyGisMaterialTypeCastIronButtonIsNotVisible")) { return this.verifyGisMaterialTypeCastIronButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyGisMaterialTypeCopperButtonIsNotVisible")) { return this.verifyGisMaterialTypeCopperButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyGisMaterialTypeOtherPlasticButtonIsNotVisible")) { return this.verifyGisMaterialTypeOtherPlasticButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyGisMaterialTypePEPlasticButtonIsNotVisible")) { return this.verifyGisMaterialTypePEPlasticButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyGisMaterialTypeProtectedSteelButtonIsNotVisible")) { return this.verifyGisMaterialTypeProtectedSteelButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyGisMaterialTypeUnProtectedSteelButtonIsNotVisible")) { return this.verifyGisMaterialTypeUnProtectedSteelButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyGisUseAllPipesButtonIsNotVisible")) { return this.verifyGisUseAllPipesButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyGisBoundaryBigBoundaryButtonIsNotVisible")) { return this.verifyGisBoundaryBigBoundaryButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyGisBoundarySmallBoundaryButtonIsNotVisible")) { return this.verifyGisBoundarySmallBoundaryButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyGisUseAllBoundariesButtonIsNotVisible")) { return this.verifyGisUseAllBoundariesButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyPageLoaded")) { return this.verifyPageLoaded(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoTagLabelEquals")) { return this.verifySurveyInfoTagLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoModeLabelEquals")) { return this.verifySurveyInfoModeLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoStartTimeLabelEquals")) { return this.verifySurveyInfoStartTimeLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoEndTimeLabelEquals")) { return this.verifySurveyInfoEndTimeLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoStabilityClassLabelEquals")) { return this.verifySurveyInfoStabilityClassLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoDriverLabelEquals")) { return this.verifySurveyInfoDriverLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoSurveyorLabelEquals")) { return this.verifySurveyInfoSurveyorLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoAnalyzerLabelEquals")) { return this.verifySurveyInfoAnalyzerLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoStartTimeLabelStartsWith")) { return this.verifySurveyInfoStartTimeLabelStartsWith(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoEndTimeLabelStartsWith")) { return this.verifySurveyInfoEndTimeLabelStartsWith(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoStartTimeLabelHasCorrectTimeFormat")) { return this.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoEndTimeLabelHasCorrectTimeFormat")) { return this.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(data, dataRowID); }
		else if (actionName.equals("verifySurveyViewPageIsOpened")) { return this.verifySurveyViewPageIsOpened(data, dataRowID); }
		else if (actionName.equals("verifyIsotopicCaptureResultIsPresentOnMap")) { return this.verifyIsotopicCaptureResultIsPresentOnMap(data, dataRowID); }
		else if (actionName.equals("verifyIsotopicCaptureResultIsNotPresentOnMap")) { return this.verifyIsotopicCaptureResultIsNotPresentOnMap(data, dataRowID); }
		else if (actionName.equals("verifyMapShownForZoomLevelIsCorrect")) { return this.verifyMapShownForZoomLevelIsCorrect(data, dataRowID); }
		else if (actionName.equals("verifyRefGasCaptureResultIsPresentOnMap")) { return this.verifyRefGasCaptureResultIsPresentOnMap(data, dataRowID); }
		else if (actionName.equals("verifyRefGasCaptureResultIsNotPresentOnMap")) { return this.verifyRefGasCaptureResultIsNotPresentOnMap(data, dataRowID); }
		else if (actionName.equals("waitForIndicationsToBeShownOnMap")) { return this.waitForIndicationsToBeShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifySurveyAmplitudes")) { return this.verifySurveyAmplitudes(data, dataRowID); }
		return false;
	}

	public DriverViewDataReader getDataReader() {
		if (dataReader == null) {
			setDataReader(new DriverViewDataReader(this.excelUtility));
		}
		return dataReader;
	}

	public void setDataReader(DriverViewDataReader dataReader) {
		this.dataReader = dataReader;
	}

	public SurveyViewPage getSurveyViewPage() {
		return (SurveyViewPage)this.getPageObject();
	}
}
