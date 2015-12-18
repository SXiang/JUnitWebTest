package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.Constants;
import common.source.ExcelUtility;
import common.source.OLMapUtility;
import common.source.TestContext;
import common.source.OLMapUtility.IconColor;
import common.source.TestSetup;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.DriverViewPage.DisplaySwitchType;
import surveyor.scommon.source.DriverViewPage.GisSwitchType;
import surveyor.scommon.source.DriverViewPage.MapSwitchType;
import surveyor.scommon.source.DriverViewPage.SolarRadiation;
import surveyor.scommon.source.DriverViewPage.SurveyTime;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.DriverViewPage.Wind;

public class DriverViewPageActions extends BasePageActions {
	
	private static final String FN_START_SIMULATOR_SCRIPT = "startSimulatorScript";
	private static final String FN_VERIFY_GIS_SWITCH_IS_ON = "verifyGisSwitchIsOn";
	private static final String FN_VERIFY_DISPLAY_SWITCH_IS_ON = "verifyDisplaySwitchIsOn";
	private static final String FN_VERIFY_SURVEY_INFO_TIME_REMAINING_LABEL_HAS_TEXT = "verifySurveyInfoTimeRemainingLabelHasText";
	private static final String FN_VERIFY_SURVEY_INFO_TIME_ELAPSED_LABEL_HAS_TEXT = "verifySurveyInfoTimeElapsedLabelHasText";
	private static final String FN_VERIFY_SURVEY_INFO_ANALYZER_LABEL_EQUALS = "verifySurveyInfoAnalyzerLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_SURVEYOR_LABEL_EQUALS = "verifySurveyInfoSurveyorLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_DRIVER_LABEL_EQUALS = "verifySurveyInfoDriverLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_STABILITY_CLASS_LABEL_EQUALS = "verifySurveyInfoStabilityClassLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_ZOOM_LEVEL_LABEL_EQUALS = "verifySurveyInfoZoomLevelLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_TIME_REMAINING_LABEL_EQUALS = "verifySurveyInfoTimeRemainingLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_TIME_ELAPSED_LABEL_EQUALS = "verifySurveyInfoTimeElapsedLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_TAG_LABEL_EQUALS = "verifySurveyInfoTagLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_SURVEY_STATUS_LABEL_EQUALS = "verifySurveyInfoSurveyStatusLabelEquals";
	private static final String FN_VERIFY_SURVEY_INFO_MODE_LABEL_EQUALS = "verifySurveyInfoModeLabelEquals";
	private static final String FN_VERIFY_CROSS_HAIR_ICON_IS_SHOWN_ON_MAP = "verifyCrossHairIconIsShownOnMap";
	private static final String FN_START_DRIVING_SURVEY = "startDrivingSurvey";
	private static final String CLS_DRIVER_VIEW_PAGE_ACTIONS = "DriverViewPageActions::";
	private static final String ARG_DATA_ROW_ID = "dataRowID";
	private static final String ARG_DATA = "data";
	private static final String TESTDATA_SHEET_NAME = "Driver View Test Data";
	
	public static final int Excel_TestData_DriverView_Col_RowID = 0;	
	public static final int Excel_TestData_DriverView_Col_SurveyTag = 1;
	public static final int Excel_TestData_DriverView_Col_SurveyTime = 2;
	public static final int Excel_TestData_DriverView_Col_SolarRadiation = 3;
	public static final int Excel_TestData_DriverView_Col_Wind = 4;
	public static final int Excel_TestData_DriverView_Col_SurveyType = 5;
	public static final int Excel_TestData_DriverView_Col_ReplayScriptDB3File = 6;
	public static final int Excel_TestData_DriverView_Col_ReplayScriptDefnFile = 7;

	public class DriverViewDataRow {
		public String rowID;
		public String surveyTag;
		public String surveyTime;
		public String solarRadiation;
		public String wind;
		public String surveyType;
		public String replayScriptDB3;
		public String replayScriptDefn;
		
		public DriverViewDataRow(String rowID, String surveyTag, String surveyTime,
				String solarRadiation, String wind, String surveyType,
				String replayScriptDB3, String replayScriptDefn) throws Exception {
			this.rowID = rowID;
			this.surveyTag = surveyTag;
			this.surveyTime = surveyTime;
			this.solarRadiation = solarRadiation;
			this.wind = wind;
			this.surveyType = surveyType;
			this.replayScriptDB3 = replayScriptDB3;
			this.replayScriptDefn = replayScriptDefn;
		}
	}	

	private DriverViewPage driverViewPage = null;

	public DriverViewPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL);
		driverViewPage = new DriverViewPage(driver, testSetup, strBaseURL);
		PageFactory.initElements(driver, driverViewPage);
	}

	private DriverViewDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getCellData(dataRowID, Excel_TestData_DriverView_Col_RowID, TESTDATA_SHEET_NAME);
		String surveyTag = excelUtility.getCellData(dataRowID, Excel_TestData_DriverView_Col_SurveyTag, TESTDATA_SHEET_NAME);
		// SurveyTag could be an ActionFunction. Check and get value.
		surveyTag = ActionArguments.evaluateArgForFunction(surveyTag);
		String surveyTime = excelUtility.getCellData(dataRowID, Excel_TestData_DriverView_Col_SurveyTime, TESTDATA_SHEET_NAME);
		String solarRadiation = excelUtility.getCellData(dataRowID, Excel_TestData_DriverView_Col_SolarRadiation, TESTDATA_SHEET_NAME);
		String wind = excelUtility.getCellData(dataRowID, Excel_TestData_DriverView_Col_Wind, TESTDATA_SHEET_NAME);
		String surveyType = excelUtility.getCellData(dataRowID, Excel_TestData_DriverView_Col_SurveyType, TESTDATA_SHEET_NAME);
		String replayScriptDB3 = excelUtility.getCellData(dataRowID, Excel_TestData_DriverView_Col_ReplayScriptDB3File, TESTDATA_SHEET_NAME);
		String replayScriptDefn = excelUtility.getCellData(dataRowID, Excel_TestData_DriverView_Col_ReplayScriptDefnFile, TESTDATA_SHEET_NAME);
		
		System.out.println(String.format("Found data row: rowID=[%s], surveyTag=[%s], surveyTime=[%s], solarRadiation=[%s], "
				+ "wind=[%s], surveyType=[%s], replayScriptDB3=[%s], replayScriptDefn=[%s]", 
				rowID, surveyTag, surveyTime, solarRadiation, wind, surveyType, replayScriptDB3, replayScriptDefn));
		
		return new DriverViewDataRow(rowID, surveyTag, surveyTime, solarRadiation, wind, surveyType, replayScriptDB3, replayScriptDefn);
	}

	public boolean clickOnCurtainArrowDownButton(String data, Integer dataRowID) {
		driverViewPage.clickCurtainArrowDownButton();		
		return true;
	}

	public boolean clickOnCurtainArrowLeftButton(String data, Integer dataRowID) {
		driverViewPage.clickCurtainArrowLeftButton();
		return true;
	}

	public boolean clickOnCurtainArrowRightButton(String data, Integer dataRowID) {
		driverViewPage.clickCurtainArrowRightButton();
		return true;
	}

	public boolean clickOnCurtainArrowUpButton(String data, Integer dataRowID) {
		driverViewPage.clickCurtainArrowUpButton();
		return true;
	}

	public boolean clickOnCurtainReturnButton(String data, Integer dataRowID) {
		driverViewPage.clickCurtainReturnButton();
		return true;
	}

	public boolean clickOnCurtainZoomInButton(String data, Integer dataRowID) {
		driverViewPage.clickCurtainZoomInButton();
		return true;
	}

	public boolean clickOnCurtainZoomOutButton(String data, Integer dataRowID) {
		driverViewPage.clickCurtainZoomOutButton();
		return true;
	}

	public boolean clickOnDisplayButton(String data, Integer dataRowID) {
		driverViewPage.clickDisplayButton();
		return true;
	}

	public boolean clickOnGisButton(String data, Integer dataRowID) {
		driverViewPage.clickGisButton();
		return true;
	}

	public boolean clickOnMapButton(String data, Integer dataRowID) {
		driverViewPage.clickMapButton();
		return true;
	}

	public boolean clickOnModeButton(String data, Integer dataRowID) {
		driverViewPage.clickModeButton();
		TestContext.INSTANCE.getTestSetup().slowdownInSeconds(TestContext.INSTANCE.getTestSetup().getSlowdownInSeconds());
		return true;
	}

	public boolean clickOnPositionButton(String data, Integer dataRowID) {
		driverViewPage.clickPositionButton();
		return true;
	}

	public boolean clickOnStatusButton(String data, Integer dataRowID) {
		driverViewPage.clickStatusButton();
		return true;
	}

	public boolean hideCurtainView(String data, Integer dataRowID) {
		driverViewPage.hideCurtainMenu();
		return true;
	}

	public boolean showCurtainView(String data, Integer dataRowID) {
		driverViewPage.clickCurtainButton();
		return true;
	}

	public boolean startSimulatorScript(String data, Integer dataRowID) throws Exception {
		ActionArguments.verifyGreaterThanZero(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_START_SIMULATOR_SCRIPT, ARG_DATA_ROW_ID, dataRowID);
		try {
			DriverViewDataRow dataRow = getDataRow(dataRowID);
			if (!ActionArguments.isEmpty(dataRow.replayScriptDB3)) {
				TestSetup.replayDB3Script(dataRow.replayScriptDefn, dataRow.replayScriptDB3);
			} else {
				TestSetup.replayDB3Script(dataRow.replayScriptDefn);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean open(String data, Integer dataRowID) {
		driverViewPage.open();
		driverViewPage.waitForPageLoad();
		driverViewPage.waitForConnectionComplete();
		return true;
	}

	/**
	 * 
	 * @param data 
	 * @param dataRowID (Required) - RowID in Survey test data sheet from where data for starting survey will be read.
	 * @return
	 * @throws Exception 
	 */
	public boolean startDrivingSurvey(String data, Integer dataRowID) throws Exception {
		ActionArguments.verifyGreaterThanZero(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_START_DRIVING_SURVEY, ARG_DATA_ROW_ID, dataRowID);
		try {
			DriverViewDataRow dataRow = getDataRow(dataRowID);
			SurveyTime time = getSurveyTime(dataRow);
			SolarRadiation radiation = getSolarRadiation(dataRow);
			Wind wind = getWind(dataRow);
			SurveyType type = getSurveyType(dataRow);
			
			driverViewPage.clickModeButton();
			driverViewPage.startDrivingSurvey(dataRow.surveyTag, time, radiation, wind, type);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private SurveyType getSurveyType(DriverViewDataRow dataRow) {
		SurveyType type = SurveyType.Manual;
		if (dataRow.surveyType.equalsIgnoreCase("Manual")) {
			type = SurveyType.Manual;
		} else if (dataRow.surveyType.equalsIgnoreCase("Operator")) {
			type = SurveyType.Operator;
		} else if (dataRow.surveyType.equalsIgnoreCase("RapidResponse")) {
			type = SurveyType.RapidResponse;
		} else {
			type = SurveyType.Standard;
		}
		return type;
	}

	private Wind getWind(DriverViewDataRow dataRow) {
		Wind wind = Wind.Calm;
		if (dataRow.wind.equalsIgnoreCase("Calm")) {
			wind = Wind.Calm;
		} else if (dataRow.wind.equalsIgnoreCase("Light")) {
			wind = Wind.Light;
		} else {
			wind = Wind.Strong;
		}
		return wind;
	}

	private SolarRadiation getSolarRadiation(DriverViewDataRow dataRow) {
		SolarRadiation radiation = SolarRadiation.Moderate;
		if (dataRow.solarRadiation.equalsIgnoreCase("Moderate")) {
			radiation = SolarRadiation.Moderate;
		} else if (dataRow.solarRadiation.equalsIgnoreCase("Overcast")) {
			radiation = SolarRadiation.Overcast;
		} else {
			radiation = SolarRadiation.Strong;
		}
		return radiation;
	}

	private SurveyTime getSurveyTime(DriverViewDataRow dataRow) {
		SurveyTime time = SurveyTime.Day;
		if (dataRow.surveyTime.equalsIgnoreCase("Day")) {			
			time = SurveyTime.Day;
		} else {
			time = SurveyTime.Night;
		}
		return time;
	}
	
	public boolean stopDrivingSurvey(String data, Integer dataRowID) {
		driverViewPage.clickModeButton();
		driverViewPage.stopDrivingSurvey();
		return true;
	}
	
	/* GIS Switch - Enable/Disable methods */
	
	public boolean turnOnMapView(String data, Integer dataRowID) {
		driverViewPage.toggleMapSwitch(MapSwitchType.Map, true);
		return true;
	}
	public boolean turnOnSatelliteView(String data, Integer dataRowID) {
		driverViewPage.toggleMapSwitch(MapSwitchType.Satellite, true);
		return true;
	}
	public boolean turnOnBoundariesDistrict(String data, Integer dataRowID) {
		driverViewPage.toggleGisSwitch(GisSwitchType.BoundariesDistrict, true);
		return true;
	}
	public boolean turnOnBoundariesDistrictPlat(String data, Integer dataRowID) {
		driverViewPage.toggleGisSwitch(GisSwitchType.BoundariesDistrictPlat, true);
		return true;
	}
	public boolean turnOnMaterialTypeCopper(String data, Integer dataRowID) {
		driverViewPage.toggleGisSwitch(GisSwitchType.MaterialTypeCopper, true);
		return true;
	}
	public boolean turnOnMaterialTypeCastIron(String data, Integer dataRowID) {
		driverViewPage.toggleGisSwitch(GisSwitchType.MaterialTypeCastIron, true);
		return true;
	}
	public boolean turnOnMaterialTypeOtherPlastic(String data, Integer dataRowID) {
		driverViewPage.toggleGisSwitch(GisSwitchType.MaterialTypeOtherPlastic, true);
		return true;
	}
	public boolean turnOnMaterialTypePEPlastic(String data, Integer dataRowID) {
		driverViewPage.toggleGisSwitch(GisSwitchType.MaterialTypePEPlastic, true);
		return true;
	}
	public boolean turnOnMaterialTypeProtectedSteel(String data, Integer dataRowID) {
		driverViewPage.toggleGisSwitch(GisSwitchType.MaterialTypeProtectedSteel, true);
		return true;
	}
	public boolean turnOnMaterialTypeUnprotectedSteel(String data, Integer dataRowID) {
		driverViewPage.toggleGisSwitch(GisSwitchType.MaterialTypeUnprotectedSteel, true);
		return true;
	}
	public boolean turnOnUseAllBoundaries(String data, Integer dataRowID) {
		driverViewPage.toggleGisSwitch(GisSwitchType.UseAllBoundaries, true);
		return true;
	}
	public boolean turnOnUseAllPipes(String data, Integer dataRowID) {
		driverViewPage.toggleGisSwitch(GisSwitchType.UseAllPipes, true);
		return true;
	}
	public boolean turnOffBoundariesDistrict(String data, Integer dataRowID) {
		driverViewPage.toggleGisSwitch(GisSwitchType.BoundariesDistrict, false);
		return true;
	}
	public boolean turnOffBoundariesDistrictPlat(String data, Integer dataRowID) {
		driverViewPage.toggleGisSwitch(GisSwitchType.BoundariesDistrictPlat, false);
		return true;
	}
	public boolean turnOffMaterialTypeCopper(String data, Integer dataRowID) {
		driverViewPage.toggleGisSwitch(GisSwitchType.MaterialTypeCopper, false);
		return true;
	}
	public boolean turnOffMaterialTypeCastIron(String data, Integer dataRowID) {
		driverViewPage.toggleGisSwitch(GisSwitchType.MaterialTypeCastIron, false);
		return true;
	}
	public boolean turnOffMaterialTypeOtherPlastic(String data, Integer dataRowID) {
		driverViewPage.toggleGisSwitch(GisSwitchType.MaterialTypeOtherPlastic, false);
		return true;
	}
	public boolean turnOffMaterialTypePEPlastic(String data, Integer dataRowID) {
		driverViewPage.toggleGisSwitch(GisSwitchType.MaterialTypePEPlastic, false);
		return true;
	}
	public boolean turnOffMaterialTypeProtectedSteel(String data, Integer dataRowID) {
		driverViewPage.toggleGisSwitch(GisSwitchType.MaterialTypeProtectedSteel, false);
		return true;
	}
	public boolean turnOffMaterialTypeUnprotectedSteel(String data, Integer dataRowID) {
		driverViewPage.toggleGisSwitch(GisSwitchType.MaterialTypeUnprotectedSteel, false);
		return true;
	}
	public boolean turnOffUseAllBoundaries(String data, Integer dataRowID) {
		driverViewPage.toggleGisSwitch(GisSwitchType.UseAllBoundaries, false);
		return true;
	}
	public boolean turnOffUseAllPipes(String data, Integer dataRowID) {
		driverViewPage.toggleGisSwitch(GisSwitchType.UseAllPipes, false);
		return true;
	}

	/* Display Switch (Enable/Disable) methods */
	
	public boolean turnOnEightHourHistory(String data, Integer dataRowID) {
		driverViewPage.toggleDisplaySwitch(DisplaySwitchType.EightHourHistory, true);
		return true;
	}
	public boolean turnOnConcentrationChart(String data, Integer dataRowID) {
		driverViewPage.toggleDisplaySwitch(DisplaySwitchType.ConcentrationChart, true);
		return true;
	}
	public boolean turnOnFOVs(String data, Integer dataRowID) {
		driverViewPage.toggleDisplaySwitch(DisplaySwitchType.FOVs, true);
		return true;
	}
	public boolean turnOnIndications(String data, Integer dataRowID) {
		driverViewPage.toggleDisplaySwitch(DisplaySwitchType.Indications, true);
		return true;
	}
	public boolean turnOnIsotopicAnalysis(String data, Integer dataRowID) {
		driverViewPage.toggleDisplaySwitch(DisplaySwitchType.IsotopicAnalysis, true);
		return true;
	}
	public boolean turnOnLisas(String data, Integer dataRowID) {
		driverViewPage.toggleDisplaySwitch(DisplaySwitchType.Lisas, true);
		return true;
	}
	public boolean turnOnNotes(String data, Integer dataRowID) {
		driverViewPage.toggleDisplaySwitch(DisplaySwitchType.Notes, true);
		return true;
	}
	public boolean turnOnWindRose(String data, Integer dataRowID) {
		driverViewPage.toggleDisplaySwitch(DisplaySwitchType.WindRose, true);
		return true;
	}
	public boolean turnOffEightHourHistory(String data, Integer dataRowID) {
		driverViewPage.toggleDisplaySwitch(DisplaySwitchType.EightHourHistory, false);
		return true;
	}
	public boolean turnOffConcentrationChart(String data, Integer dataRowID) {
		driverViewPage.toggleDisplaySwitch(DisplaySwitchType.ConcentrationChart, false);
		return true;
	}
	public boolean turnOffFOVs(String data, Integer dataRowID) {
		driverViewPage.toggleDisplaySwitch(DisplaySwitchType.FOVs, false);
		return true;
	}
	public boolean turnOffIndications(String data, Integer dataRowID) {
		driverViewPage.toggleDisplaySwitch(DisplaySwitchType.Indications, false);
		return true;
	}
	public boolean turnOffIsotopicAnalysis(String data, Integer dataRowID) {
		driverViewPage.toggleDisplaySwitch(DisplaySwitchType.IsotopicAnalysis, false);
		return true;
	}
	public boolean turnOffLisas(String data, Integer dataRowID) {
		driverViewPage.toggleDisplaySwitch(DisplaySwitchType.Lisas, false);
		return true;
	}
	public boolean turnOffNotes(String data, Integer dataRowID) {
		driverViewPage.toggleDisplaySwitch(DisplaySwitchType.Notes, false);
		return true;
	}
	public boolean turnOffWindRose(String data, Integer dataRowID) {
		driverViewPage.toggleDisplaySwitch(DisplaySwitchType.WindRose, false);
		return true;
	}

	/* Button state verification methods */
	
	public boolean verifyAnemometerButtonIsGreen(String data, Integer dataRowID) {
		return driverViewPage.isAnemometerButtonGreen();
	}

	public boolean verifyAnemometerButtonIsRed(String data, Integer dataRowID) {
		return driverViewPage.isAnemometerButtonRed();
	}

	public boolean verifyDisplaySwitchIsOn(String data, Integer dataRowID) throws Exception {
		ActionArguments.verifyNotNullOrEmpty(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_VERIFY_DISPLAY_SWITCH_IS_ON, ARG_DATA, data);
		DisplaySwitchType switchType = DisplaySwitchType.ConcentrationChart;
		if (data.equalsIgnoreCase("ConcentrationChart")) {
			switchType = DisplaySwitchType.ConcentrationChart;
		} else if (data.equalsIgnoreCase("EightHourHistory")) {
			switchType = DisplaySwitchType.EightHourHistory;
		} else if (data.equalsIgnoreCase("FOVs")) {
			switchType = DisplaySwitchType.FOVs;
		} else if (data.equalsIgnoreCase("Indications")) {
			switchType = DisplaySwitchType.Indications;
		} else if (data.equalsIgnoreCase("IsotopicAnalysis")) {
			switchType = DisplaySwitchType.IsotopicAnalysis;
		} else if (data.equalsIgnoreCase("Lisas")) {
			switchType = DisplaySwitchType.Lisas;
		} else if (data.equalsIgnoreCase("Notes")) {
			switchType = DisplaySwitchType.Notes;
		} else if (data.equalsIgnoreCase("WindRose")) {
			switchType = DisplaySwitchType.WindRose;
		}
		return driverViewPage.isDisplaySwitchOn(switchType);
	}

	public boolean verifyDisplaySwitchIsOff(String data, Integer dataRowID) throws Exception {
		return !verifyDisplaySwitchIsOn(data, dataRowID);
	}

	public boolean verifyFlowButtonIsGreen(String data, Integer dataRowID) {
		return driverViewPage.isFlowButtonGreen();
	}

	public boolean verifyFlowButtonIsRed(String data, Integer dataRowID) {
		return driverViewPage.isFlowButtonRed();
	}

	public boolean verifyGisSwitchIsOn(String data, Integer dataRowID) throws Exception {
		ActionArguments.verifyNotNullOrEmpty(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_VERIFY_GIS_SWITCH_IS_ON, ARG_DATA, data);
		GisSwitchType switchType = GisSwitchType.UseAllPipes;
		if (data.equalsIgnoreCase("BoundariesDistrict")) {
			switchType = GisSwitchType.MaterialTypePEPlastic;
		} else if (data.equalsIgnoreCase("BoundariesDistrictPlat")) {
			switchType = GisSwitchType.BoundariesDistrictPlat;
		} else if (data.equalsIgnoreCase("MaterialTypeCastIron")) {
			switchType = GisSwitchType.MaterialTypeCastIron;
		} else if (data.equalsIgnoreCase("MaterialTypeCopper")) {
			switchType = GisSwitchType.MaterialTypeCopper;
		} else if (data.equalsIgnoreCase("MaterialTypeOtherPlastic")) {
			switchType = GisSwitchType.MaterialTypeOtherPlastic;
		} else if (data.equalsIgnoreCase("MaterialTypePEPlastic")) {
			switchType = GisSwitchType.MaterialTypePEPlastic;
		} else if (data.equalsIgnoreCase("MaterialTypeProtectedSteel")) {
			switchType = GisSwitchType.MaterialTypeProtectedSteel;
		} else if (data.equalsIgnoreCase("MaterialTypeUnprotectedSteel")) {
			switchType = GisSwitchType.MaterialTypeUnprotectedSteel;
		} else if (data.equalsIgnoreCase("UseAllBoundaries")) {
			switchType = GisSwitchType.UseAllBoundaries;
		} else if (data.equalsIgnoreCase("UseAllPipes")) {
			switchType = GisSwitchType.UseAllPipes;
		}
		return driverViewPage.isGisSwitchOn(switchType);
	}

	public boolean verifyGisSwitchIsOff(String data, Integer dataRowID) throws Exception {
		return !verifyGisSwitchIsOn(data, dataRowID);
	}

	public boolean verifyGPSButtonIsGreen(String data, Integer dataRowID) {
		return driverViewPage.isGPSButtonGreen();
	}

	public boolean verifyGPSButtonIsRed(String data, Integer dataRowID) {
		return driverViewPage.isGPSButtonRed();
	}

	public boolean verifyHBTempButtonIsGreen(String data, Integer dataRowID) {
		return driverViewPage.isHBTempButtonGreen();
	}

	public boolean verifyHBTempButtonIsRed(String data, Integer dataRowID) {
		return driverViewPage.isHBTempButtonRed();
	}

	public boolean verifyPositionButtonIsGreen(String data, Integer dataRowID) {
		return driverViewPage.isPositionButtonGreen();
	}

	public boolean verifyPositionButtonIsNotSelected(String data, Integer dataRowID) {
		return !driverViewPage.isPositionButtonSelected();
	}

	public boolean verifyPositionButtonIsSelected(String data, Integer dataRowID) {
		return driverViewPage.isPositionButtonSelected();
	}

	public boolean verifyPressureButtonIsGreen(String data, Integer dataRowID) {
		return driverViewPage.isPressureButtonGreen();
	}

	public boolean verifyPressureButtonIsRed(String data, Integer dataRowID) {
		return driverViewPage.isPressureButtonRed();
	}

	public boolean verifyRefBottleMeasButtonIsDisabled(String data, Integer dataRowID) {
		return !driverViewPage.isRefBottleMeasButtonEnabled();
	}

	public boolean verifyRefBottleMeasButtonIsEnabled(String data, Integer dataRowID) {
		return driverViewPage.isRefBottleMeasButtonEnabled();
	}

	public boolean verifyStartEQSurveyButtonIsDisabled(String data, Integer dataRowID) {
		return !driverViewPage.isStartEQSurveyButtonEnabled();
	}

	public boolean verifyStartEQSurveyButtonIsEnabled(String data, Integer dataRowID) {
		return driverViewPage.isStartEQSurveyButtonEnabled();
	}

	public boolean verifyStartIsotopicCaptureButtonIsDisabled(String data, Integer dataRowID) {
		return !driverViewPage.isStartIsotopicCaptureButtonEnabled();
	}

	public boolean verifyStartIsotopicCaptureButtonIsEnabled(String data, Integer dataRowID) {
		return driverViewPage.isStartIsotopicCaptureButtonEnabled();
	}

	public boolean verifyStartSurveyButtonIsDisabled(String data, Integer dataRowID) {
		return !driverViewPage.isStartSurveyButtonEnabled();
	}

	public boolean verifyStartSurveyButtonIsEnabled(String data, Integer dataRowID) {
		return driverViewPage.isStartSurveyButtonEnabled();
	}

	public boolean verifyStopDrivingSurveyButtonIsDisabled(String data, Integer dataRowID) {
		return !driverViewPage.isStopDrivingSurveyButtonEnabled();
	}

	public boolean verifyStopDrivingSurveyButtonIsEnabled(String data, Integer dataRowID) {
		return driverViewPage.isStopDrivingSurveyButtonEnabled();
	}

	public boolean verifySystemShutdownButtonIsDisabled(String data, Integer dataRowID) {
		return !driverViewPage.isSystemShutdownButtonEnabled();
	}

	public boolean verifySystemShutdownButtonIsEnabled(String data, Integer dataRowID) {
		return driverViewPage.isSystemShutdownButtonEnabled();
	}

	public boolean verifyWBTempButtonIsGreen(String data, Integer dataRowID) {
		return driverViewPage.isWBTempButtonGreen();
	}

	public boolean verifyWBTempButtonIsRed(String data, Integer dataRowID) {
		return driverViewPage.isWBTempButtonRed();
	}

	/* Verify OLMap elements */
	public boolean verifyLISAIsShownOnMap(String data, Integer dataRowID) {
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isLISAShownOnMap();
	}
	public boolean verifyIndicationsIsShownOnMap(String data, Integer dataRowID) {
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isIndicationsShownOnMap();
	}
	public boolean verifyConcentrationChartIsShownOnMap(String data, Integer dataRowID) {
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isConcentrationChartDataShowingOnMap();
	}
	public boolean verifyBreadcrumbIsShownOnMap(String data, Integer dataRowID) {
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isBreadcrumbShownOnMap();
	}
	public boolean verifyFOVIsShownOnMap(String data, Integer dataRowID) {
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isFOVShownOnMap();
	}
	public boolean verifyAssetIsShownOnMap(String data, Integer dataRowID) {
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isAssetShownOnMap();
	}
	public boolean verifyBoundariesIsShownOnMap(String data, Integer dataRowID) {
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isBoundariesShownOnMap();
	}
	
	/**
	 * 
	 * @param data (Required) - Specifies the color for the cross hair icon which should be shown on the map.
	 * @param dataRowID
	 * @return
	 * @throws Exception 
	 */
	public boolean verifyCrossHairIconIsShownOnMap(String data, Integer dataRowID) throws Exception {
		ActionArguments.verifyNotNullOrEmpty(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_VERIFY_CROSS_HAIR_ICON_IS_SHOWN_ON_MAP, ARG_DATA, data);
		IconColor color = IconColor.Gray;		
		if (data.equalsIgnoreCase("Gray")) {
			color = IconColor.Gray;
		} else if (data.equalsIgnoreCase("White")) {
			color = IconColor.White;
		} else if (data.equalsIgnoreCase("Red")) {
			color = IconColor.Red;
		}
		
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isCrossHairIconShownOnMap(color);
	}
	public boolean verifyLISAIsNotShownOnMap(String data, Integer dataRowID) {
		return !verifyLISAIsShownOnMap(data, dataRowID);
	}
	public boolean verifyIndicationsIsNotShownOnMap(String data, Integer dataRowID) {
		return !verifyIndicationsIsShownOnMap(data, dataRowID);
	}
	public boolean verifyConcentrationChartIsNotShownOnMap(String data, Integer dataRowID) {
		return !verifyConcentrationChartIsShownOnMap(data, dataRowID);
	}
	public boolean verifyBreadcrumbIsNotShownOnMap(String data, Integer dataRowID) {
		return !verifyBreadcrumbIsShownOnMap(data, dataRowID);
	}
	public boolean verifyFOVIsNotShownOnMap(String data, Integer dataRowID) {
		return !verifyFOVIsShownOnMap(data, dataRowID);
	}
	public boolean verifyAssetIsNotShownOnMap(String data, Integer dataRowID) {
		return !verifyAssetIsShownOnMap(data, dataRowID);
	}
	public boolean verifyBoundariesIsNotShownOnMap(String data, Integer dataRowID) {
		return !verifyBoundariesIsShownOnMap(data, dataRowID);
	}
	/**
	 * 
	 * @param data (Required) - Specifies the color for the cross hair icon which should be shown on the map.
	 * @param dataRowID
	 * @return
	 * @throws Exception 
	 */
	public boolean verifyCrossHairIconIsNotShownOnMap(String data, Integer dataRowID) throws Exception {
		return !verifyCrossHairIconIsShownOnMap(data, dataRowID);
	}

	/* SurveyInfo - Labels */
	public boolean verifySurveyInfoModeLabelEquals(String data, Integer dataRowID) throws Exception {
		ActionArguments.verifyNotNullOrEmpty(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_MODE_LABEL_EQUALS, ARG_DATA, data);
		return driverViewPage.getSurveyModeLabelText().equals(data);
	}
	public boolean verifySurveyInfoSurveyStatusLabelEquals(String data, Integer dataRowID) throws Exception {
		ActionArguments.verifyNotNullOrEmpty(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_SURVEY_STATUS_LABEL_EQUALS, ARG_DATA, data);
		return driverViewPage.getSurveyStatusLabelText().equals(data);
	}
	public boolean verifySurveyInfoTagLabelEquals(String data, Integer dataRowID) throws Exception {
		ActionArguments.verifyNotNullOrEmpty(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_TAG_LABEL_EQUALS, ARG_DATA, data);
		return driverViewPage.getTagLabelText().equals(data);
	}
	public boolean verifySurveyInfoTimeElapsedLabelEquals(String data, Integer dataRowID) throws Exception {
		ActionArguments.verifyNotNullOrEmpty(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_TIME_ELAPSED_LABEL_EQUALS, ARG_DATA, data);
		return driverViewPage.getTimeElapsedLabelText().equals(data);
	}
	public boolean verifySurveyInfoTimeRemainingLabelEquals(String data, Integer dataRowID) throws Exception {
		ActionArguments.verifyNotNullOrEmpty(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_TIME_REMAINING_LABEL_EQUALS, ARG_DATA, data);
		return driverViewPage.getTimeRemainingLabelText().equals(data);
	}
	public boolean verifySurveyInfoZoomLevelLabelEquals(String data, Integer dataRowID) throws Exception {
		ActionArguments.verifyNotNullOrEmpty(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_ZOOM_LEVEL_LABEL_EQUALS, ARG_DATA, data);
		return driverViewPage.getZoomLevelLabelText().equals(data);
	}
	public boolean verifySurveyInfoStabilityClassLabelEquals(String data, Integer dataRowID) throws Exception {
		ActionArguments.verifyNotNullOrEmpty(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_STABILITY_CLASS_LABEL_EQUALS, ARG_DATA, data);
		return driverViewPage.getStabilityClassLabelText().equals(data);
	}
	public boolean verifySurveyInfoDriverLabelEquals(String data, Integer dataRowID) throws Exception {
		ActionArguments.verifyNotNullOrEmpty(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_DRIVER_LABEL_EQUALS, ARG_DATA, data);
		return driverViewPage.getDriverLabelText().equals(data);
	}
	public boolean verifySurveyInfoSurveyorLabelEquals(String data, Integer dataRowID) throws Exception {
		ActionArguments.verifyNotNullOrEmpty(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_SURVEYOR_LABEL_EQUALS, ARG_DATA, data);
		return driverViewPage.getSurveyorLabelText().equals(data);
	}
	public boolean verifySurveyInfoAnalyzerLabelEquals(String data, Integer dataRowID) throws Exception {
		ActionArguments.verifyNotNullOrEmpty(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_ANALYZER_LABEL_EQUALS, ARG_DATA, data);
		return driverViewPage.getAnalyzerLabelText().equals(data);
	}
	public boolean verifySurveyInfoTimeElapsedLabelHasText(String data, Integer dataRowID) throws Exception {
		ActionArguments.verifyNotNullOrEmpty(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_TIME_ELAPSED_LABEL_HAS_TEXT, ARG_DATA, data);
		return !driverViewPage.getTimeElapsedLabelText().isEmpty();
	}
	public boolean verifySurveyInfoTimeRemainingLabelHasText(String data, Integer dataRowID) throws Exception {
		ActionArguments.verifyNotNullOrEmpty(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_VERIFY_SURVEY_INFO_TIME_REMAINING_LABEL_HAS_TEXT, ARG_DATA, data);
		return !driverViewPage.getTimeRemainingLabelText().isEmpty();
	}
	
	/* TO BE IMPLEMENTED METHODS */
	public boolean verifyFieldNotesIsShownOnMap(String data, Integer dataRowID) {
		return true;
	}
	public boolean verifyWindRoseIsShownOnMap(String data, Integer dataRowID) {
		return true;
	}
	public boolean verifyFieldNotesIsNotShownOnMap(String data, Integer dataRowID) {
		return true;
	}
	public boolean verifyWindRoseIsNotShownOnMap(String data, Integer dataRowID) {
		return true;
	}
	public boolean verifyCarIconIsNotInCenter(String data, Integer dataRowID) {
		return true;
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
		else if (actionName.equals("clickOnModeButton")) { return this.clickOnModeButton(data, dataRowID); }
		else if (actionName.equals("clickOnPositionButton")) { return this.clickOnPositionButton(data, dataRowID); }
		else if (actionName.equals("clickOnStatusButton")) { return this.clickOnStatusButton(data, dataRowID); }
		else if (actionName.equals("hideCurtainView")) { return this.hideCurtainView(data, dataRowID); }
		else if (actionName.equals("insertTextById")) { return this.insertTextById(data, dataRowID); }
		else if (actionName.equals("insertTextByXPath")) { return this.insertTextByXPath(data, dataRowID); }
		else if (actionName.equals("open")) { return this.open(data, dataRowID); }
		else if (actionName.equals("selectDropDownByID")) { return this.selectDropDownByID(data, dataRowID); }
		else if (actionName.equals("selectDropDownByXPath")) { return this.selectDropDownByXPath(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByID")) { return this.selectRadioButtonByID(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByXPath")) { return this.selectRadioButtonByXPath(data, dataRowID); }
		else if (actionName.equals("showCurtainView")) { return this.showCurtainView(data, dataRowID); }
		else if (actionName.equals("startDrivingSurvey  ")) { return this.startDrivingSurvey  (data, dataRowID); }
		else if (actionName.equals("startSimulatorScript")) { return this.startSimulatorScript(data, dataRowID); }
		else if (actionName.equals("stopDrivingSurvey")) { return this.stopDrivingSurvey(data, dataRowID); }
		else if (actionName.equals("turnOffBoundariesDistrict")) { return this.turnOffBoundariesDistrict(data, dataRowID); }
		else if (actionName.equals("turnOffBoundariesDistrictPlat")) { return this.turnOffBoundariesDistrictPlat(data, dataRowID); }
		else if (actionName.equals("turnOffConcentrationChart")) { return this.turnOffConcentrationChart(data, dataRowID); }
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
		else if (actionName.equals("turnOnBoundariesDistrict")) { return this.turnOnBoundariesDistrict(data, dataRowID); }
		else if (actionName.equals("turnOnBoundariesDistrictPlat")) { return this.turnOnBoundariesDistrictPlat(data, dataRowID); }
		else if (actionName.equals("turnOnConcentrationChart")) { return this.turnOnConcentrationChart(data, dataRowID); }
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
		else if (actionName.equals("verifyAnemometerButtonIsGreen")) { return this.verifyAnemometerButtonIsGreen(data, dataRowID); }
		else if (actionName.equals("verifyAnemometerButtonIsRed")) { return this.verifyAnemometerButtonIsRed(data, dataRowID); }
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
		else if (actionName.equals("verifyFieldNotesIsNotShownOnMap")) { return this.verifyFieldNotesIsNotShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyFieldNotesIsShownOnMap")) { return this.verifyFieldNotesIsShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyFlowButtonIsGreen")) { return this.verifyFlowButtonIsGreen(data, dataRowID); }
		else if (actionName.equals("verifyFlowButtonIsRed")) { return this.verifyFlowButtonIsRed(data, dataRowID); }
		else if (actionName.equals("verifyFOVIsNotShownOnMap")) { return this.verifyFOVIsNotShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyFOVIsShownOnMap")) { return this.verifyFOVIsShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyGisSwitchIsOff")) { return this.verifyGisSwitchIsOff(data, dataRowID); }
		else if (actionName.equals("verifyGisSwitchIsOn")) { return this.verifyGisSwitchIsOn(data, dataRowID); }
		else if (actionName.equals("verifyGPSButtonIsGreen")) { return this.verifyGPSButtonIsGreen(data, dataRowID); }
		else if (actionName.equals("verifyGPSButtonIsRed")) { return this.verifyGPSButtonIsRed(data, dataRowID); }
		else if (actionName.equals("verifyHBTempButtonIsGreen")) { return this.verifyHBTempButtonIsGreen(data, dataRowID); }
		else if (actionName.equals("verifyHBTempButtonIsRed")) { return this.verifyHBTempButtonIsRed(data, dataRowID); }
		else if (actionName.equals("verifyIndicationsIsNotShownOnMap")) { return this.verifyIndicationsIsNotShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyIndicationsIsShownOnMap")) { return this.verifyIndicationsIsShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyLISAIsNotShownOnMap")) { return this.verifyLISAIsNotShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyLISAIsShownOnMap")) { return this.verifyLISAIsShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyPositionButtonIsGreen")) { return this.verifyPositionButtonIsGreen(data, dataRowID); }
		else if (actionName.equals("verifyPositionButtonIsNotSelected")) { return this.verifyPositionButtonIsNotSelected(data, dataRowID); }
		else if (actionName.equals("verifyPositionButtonIsSelected")) { return this.verifyPositionButtonIsSelected(data, dataRowID); }
		else if (actionName.equals("verifyPressureButtonIsGreen")) { return this.verifyPressureButtonIsGreen(data, dataRowID); }
		else if (actionName.equals("verifyPressureButtonIsRed")) { return this.verifyPressureButtonIsRed(data, dataRowID); }
		else if (actionName.equals("verifyRefBottleMeasButtonIsDisabled")) { return this.verifyRefBottleMeasButtonIsDisabled(data, dataRowID); }
		else if (actionName.equals("verifyRefBottleMeasButtonIsEnabled")) { return this.verifyRefBottleMeasButtonIsEnabled(data, dataRowID); }
		else if (actionName.equals("verifyStartEQSurveyButtonIsDisabled")) { return this.verifyStartEQSurveyButtonIsDisabled(data, dataRowID); }
		else if (actionName.equals("verifyStartEQSurveyButtonIsEnabled")) { return this.verifyStartEQSurveyButtonIsEnabled(data, dataRowID); }
		else if (actionName.equals("verifyStartIsotopicCaptureButtonIsDisabled")) { return this.verifyStartIsotopicCaptureButtonIsDisabled(data, dataRowID); }
		else if (actionName.equals("verifyStartIsotopicCaptureButtonIsEnabled")) { return this.verifyStartIsotopicCaptureButtonIsEnabled(data, dataRowID); }
		else if (actionName.equals("verifyStartSurveyButtonIsDisabled")) { return this.verifyStartSurveyButtonIsDisabled(data, dataRowID); }
		else if (actionName.equals("verifyStartSurveyButtonIsEnabled")) { return this.verifyStartSurveyButtonIsEnabled(data, dataRowID); }
		else if (actionName.equals("verifyStopDrivingSurveyButtonIsDisabled")) { return this.verifyStopDrivingSurveyButtonIsDisabled(data, dataRowID); }
		else if (actionName.equals("verifyStopDrivingSurveyButtonIsEnabled")) { return this.verifyStopDrivingSurveyButtonIsEnabled(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoAnalyzerLabelEquals")) { return this.verifySurveyInfoAnalyzerLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoDriverLabelEquals")) { return this.verifySurveyInfoDriverLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoModeLabelEquals")) { return this.verifySurveyInfoModeLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoStabilityClassLabelEquals")) { return this.verifySurveyInfoStabilityClassLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoSurveyorLabelEquals")) { return this.verifySurveyInfoSurveyorLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoSurveyStatusLabelEquals")) { return this.verifySurveyInfoSurveyStatusLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoTagLabelEquals")) { return this.verifySurveyInfoTagLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoTimeElapsedLabelEquals")) { return this.verifySurveyInfoTimeElapsedLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoTimeElapsedLabelHasText")) { return this.verifySurveyInfoTimeElapsedLabelHasText(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoTimeRemainingLabelEquals")) { return this.verifySurveyInfoTimeRemainingLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoTimeRemainingLabelHasText")) { return this.verifySurveyInfoTimeRemainingLabelHasText(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoZoomLevelLabelEquals")) { return this.verifySurveyInfoZoomLevelLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySystemShutdownButtonIsDisabled")) { return this.verifySystemShutdownButtonIsDisabled(data, dataRowID); }
		else if (actionName.equals("verifySystemShutdownButtonIsEnabled")) { return this.verifySystemShutdownButtonIsEnabled(data, dataRowID); }
		else if (actionName.equals("verifyWBTempButtonIsGreen")) { return this.verifyWBTempButtonIsGreen(data, dataRowID); }
		else if (actionName.equals("verifyWBTempButtonIsRed")) { return this.verifyWBTempButtonIsRed(data, dataRowID); }
		else if (actionName.equals("verifyWindRoseIsNotShownOnMap")) { return this.verifyWindRoseIsNotShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyWindRoseIsShownOnMap")) { return this.verifyWindRoseIsShownOnMap(data, dataRowID); }
		return false;
	}
}