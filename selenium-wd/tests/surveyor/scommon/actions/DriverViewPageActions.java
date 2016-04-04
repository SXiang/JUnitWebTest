package surveyor.scommon.actions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import common.source.OLMapUtility;
import common.source.BrowserCommands;
import common.source.Log;
import common.source.TestContext;
import common.source.OLMapUtility.IconColor;
import common.source.RegexUtility;
import common.source.TestSetup;
import surveyor.scommon.actions.data.DriverViewDataReader;
import surveyor.scommon.actions.data.DriverViewDataReader.DriverViewDataRow;
import surveyor.scommon.source.BaseMapViewPage.DisplaySwitchType;
import surveyor.scommon.source.BaseMapViewPage.GisSwitchType;
import surveyor.scommon.source.BaseMapViewPage.MapSwitchType;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.DriverViewPage.CloudCover;
import surveyor.scommon.source.DriverViewPage.SolarRadiation;
import surveyor.scommon.source.DriverViewPage.SurveyTime;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.DriverViewPage.Wind;

public class DriverViewPageActions extends BaseDrivingViewPageActions {
	
	private static final String FN_ENTER_FIELD_NOTES = "enterFieldNotes";
	private static final String FN_VERIFY_GIS_SWITCH_IS_OFF = "verifyGisSwitchIsOff";
	private static final String FN_VERIFY_DISPLAY_SWITCH_IS_OFF = "verifyDisplaySwitchIsOff";
	private static final String FN_VERIFY_MAP_SWITCH_OFF = "verifyMapSwitchOff";
	private static final String FN_VERIFY_MAP_SWITCH_ON = "verifyMapSwitchOn";
	private static final String FN_VERIFY_GIS_SWITCH_IS_ON = "verifyGisSwitchIsOn";
	private static final String FN_VERIFY_DISPLAY_SWITCH_IS_ON = "verifyDisplaySwitchIsOn";
	private static final String FN_VERIFY_CROSS_HAIR_ICON_IS_SHOWN_ON_MAP = "verifyCrossHairIconIsShownOnMap";
	private static final String FN_START_DRIVING_SURVEY = "startDrivingSurvey";
	private static final String CLS_DRIVER_VIEW_PAGE_ACTIONS = "DriverViewPageActions::";
	
	private DriverViewDataReader dataReader = null;

	public static DriverViewDataRow workingDataRow = null;    // Stores the workingDataRow from startSurvey action

	public DriverViewPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup);
		initializePageObject(driver, new DriverViewPage(driver, testSetup, strBaseURL));
		setDataReader(new DriverViewDataReader(this.excelUtility));
	}

	public boolean clickOnCurtainArrowDownButton(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.clickOnCurtainArrowDownButton", data, dataRowID);
		getDriverViewPage().clickCurtainArrowDownButton();		
		return true;
	}

	public boolean clickOnCurtainArrowLeftButton(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.clickOnCurtainArrowLeftButton", data, dataRowID);
		getDriverViewPage().clickCurtainArrowLeftButton();
		return true;
	}

	public boolean clickOnCurtainArrowRightButton(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.clickOnCurtainArrowRightButton", data, dataRowID);
		getDriverViewPage().clickCurtainArrowRightButton();
		return true;
	}

	public boolean clickOnCurtainArrowUpButton(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.clickOnCurtainArrowUpButton", data, dataRowID);
		getDriverViewPage().clickCurtainArrowUpButton();
		return true;
	}

	public boolean clickOnCurtainReturnButton(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.clickOnCurtainReturnButton", data, dataRowID);
		getDriverViewPage().clickCurtainReturnButton();
		return true;
	}

	public boolean clickOnCurtainZoomInButton(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.clickOnCurtainZoomInButton", data, dataRowID);
		getDriverViewPage().clickCurtainZoomInButton();
		return true;
	}

	public boolean clickOnCurtainZoomOutButton(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.clickOnCurtainZoomOutButton", data, dataRowID);
		getDriverViewPage().clickCurtainZoomOutButton();
		return true;
	}

	public boolean clickOnDisplayButton(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.clickOnDisplayButton", data, dataRowID);
		getDriverViewPage().clickDisplayButton();
		return true;
	}

	public boolean clickOnFirstIndicationShownOnMap(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.clickOnFirstIndicationShownOnMap", data, dataRowID);
		String mapCanvasXPath = "//*[@id='map']/div/canvas";
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.clickFirstIndicationOnMap(mapCanvasXPath);
	}

	public boolean clickOnGisButton(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.clickOnGisButton", data, dataRowID);
		getDriverViewPage().clickGisButton();
		return true;
	}

	public boolean clickOnMapButton(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.clickOnMapButton", data, dataRowID);
		getDriverViewPage().clickMapButton();
		TestContext.INSTANCE.getTestSetup().slowdownInSeconds(TestContext.INSTANCE.getTestSetup().getSlowdownInSeconds());
		return true;
	}

	public boolean clickOnModeButton(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.clickOnModeButton", data, dataRowID);
		getDriverViewPage().clickModeButton();
		TestContext.INSTANCE.getTestSetup().slowdownInSeconds(TestContext.INSTANCE.getTestSetup().getSlowdownInSeconds());
		return true;
	}

	public boolean clickOnPicarroLogoButton(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.clickOnPicarroLogoButton", data, dataRowID);
		this.getDriverViewPage().clickPicarroLogoButton();
		return true;
	}

	public boolean clickOnPositionButton(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.clickOnPositionButton", data, dataRowID);
		getDriverViewPage().clickPositionButton();
		return true;
	}

	public boolean clickOnShutdownButton(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.clickOnShutdownButton", data, dataRowID);
		getDriverViewPage().clickShutdownButton();
		TestContext.INSTANCE.getTestSetup().slowdownInSeconds(TestContext.INSTANCE.getTestSetup().getSlowdownInSeconds());
		return true;
	}

	public boolean clickOnShutdownConfirmButton(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.clickOnShutdownConfirmButton", data, dataRowID);
		getDriverViewPage().clickShutdownConfirmButton();
		return true;
	}

	public boolean clickOnShutdownCancelButton(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.clickOnShutdownCancelButton", data, dataRowID);
		getDriverViewPage().clickShutdownCancelButton();
		return true;
	}

	public boolean clickOnStatusButton(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.clickOnStatusButton", data, dataRowID);
		getDriverViewPage().clickStatusButton();
		return true;
	}

	/**
	 * Executes clickOnZoomInButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnZoomInButton(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.clickOnZoomInButton", data, dataRowID);
		getDriverViewPage().clickZoomInButton();
		return true;
	}
 
	/**
	 * Executes clickOnZoomOutButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnZoomOutButton(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.clickOnZoomOutButton", data, dataRowID);
		getDriverViewPage().clickZoomOutButton();
		return true;
	}

	public boolean hideCurtainView(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.hideCurtainView", data, dataRowID);
		getDriverViewPage().hideCurtainMenu();
		return true;
	}

	public boolean showCurtainView(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.showCurtainView", data, dataRowID);
		getDriverViewPage().clickCurtainButton();
		return true;
	}

	public boolean open(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.open", data, dataRowID);
		getDriverViewPage().open();
		return true;
	}

	public boolean waitForConnectionToComplete(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.waitForPageLoad", data, dataRowID);
		getDriverViewPage().waitForPageLoad();
		getDriverViewPage().waitForConnectionComplete();
		return true;
	}
	
	/**
	 * Verifies the survey tag in Start Survey modal dialog.
	 *
	 * @return 
	 * @throws Exception 
	 */
	public boolean verifySurveyTagInStartSurveyDialogEquals(String data, Integer dataRowID) throws Exception {
		logAction("DriverViewPageActions.verifySurveyTagInStartSurveyDialogEquals", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_DRIVER_VIEW_PAGE_ACTIONS + "verifySurveyTagInStartSurveyDialogEquals", ARG_DATA, data);
		String surveyTag = getDriverViewPage().getSurveyTagFromStartSurveyDialog();
		log(String.format("Looking for Text-[%s], Found Survey Tag Text-[%s]", data, surveyTag));
		return surveyTag.equals(data);
	}

	/**
	 * Opens the Start Survey modal dialog.
	 *
	 * @return 
	 */
	public boolean openStartSurveyModalDialog(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.openStartSurveyModalDialog", data, dataRowID);
		getDriverViewPage().openStartSurveyModalDialog();
		return true;
	}

	/**
	 * 
	 * @param commaSeperatedValues - colon seperated values in this format:
	 * 		[Survey Tag]:[Survey Time]:[Solar Radiation]:[Wind]:[CloudCover]:[Survey Type]
	 * @param dataRowID (Required) - RowID in Survey test data sheet from where data for starting survey will be read.
	 * @return
	 * @throws Exception 
	 */
	public boolean startDrivingSurvey(String commaSeperatedValues, Integer dataRowID) throws Exception {
		logAction("DriverViewPageActions.startDrivingSurvey", commaSeperatedValues, dataRowID);
		String surveyTag = null;
		SurveyTime time = SurveyTime.Day;
		SolarRadiation radiation = SolarRadiation.Moderate;
		Wind wind = Wind.Calm;
		CloudCover cloudCover = CloudCover.LessThan50;
		SurveyType type = SurveyType.Standard;
		Float minAmplitude = -1.0F;
		if (!ActionArguments.isEmpty(commaSeperatedValues)){
			List<String> listValues = RegexUtility.split(commaSeperatedValues, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
			surveyTag = ActionArguments.evaluateArgForFunction(listValues.get(0));
			time = getSurveyTime(listValues.get(1));
			radiation = getSolarRadiation(listValues.get(2));
			wind = getWind(listValues.get(3));
			cloudCover = getCloudCover(listValues.get(4));
			type = getSurveyType(listValues.get(5));
			if (listValues.get(6) != "") {
				minAmplitude = Float.valueOf(listValues.get(6));
			}
			
		} else {
			ActionArguments.verifyGreaterThanZero(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_START_DRIVING_SURVEY, ARG_DATA_ROW_ID, dataRowID);
			DriverViewDataRow dataRow = getDataReader().getDataRow(dataRowID);
			surveyTag = ActionArguments.evaluateArgForFunction(dataRow.surveyTag);
			time = getSurveyTime(dataRow.surveyTime);
			radiation = getSolarRadiation(dataRow.solarRadiation);
			wind = getWind(dataRow.wind);
			cloudCover = getCloudCover(dataRow.wind);
			type = getSurveyType(dataRow.surveyType);
			if (!ActionArguments.isEmpty(dataRow.minAmplitude)) {
				minAmplitude = Float.valueOf(dataRow.minAmplitude);
			}
			
			// store the working datarow.
			workingDataRow = dataRow;
			workingDataRow.surveyTag = surveyTag;	// update the tag to value evaluated by function.
		}
		try {
			getDriverViewPage().startDrivingSurvey(surveyTag, time, radiation, wind, cloudCover, type, minAmplitude);
		} catch (Exception e) {
			Log.error(e.toString());
			return false;
		}
		return true;
	}

	private SurveyType getSurveyType(String surveyType) {
		SurveyType type = SurveyType.Manual;
		if (surveyType.equalsIgnoreCase("Manual")) {
			type = SurveyType.Manual;
		} else if (surveyType.equalsIgnoreCase("Operator")) {
			type = SurveyType.Operator;
		} else if (surveyType.equalsIgnoreCase("RapidResponse")) {
			type = SurveyType.RapidResponse;
		} else if (surveyType.equalsIgnoreCase("Assessment")) {
			type = SurveyType.Assessment;
		} else {
			type = SurveyType.Standard;
		}
		return type;
	}

	private Wind getWind(String windValue) {
		Wind wind = Wind.Calm;
		if (windValue.equalsIgnoreCase("Calm")) {
			wind = Wind.Calm;
		} else if (windValue.equalsIgnoreCase("Light")) {
			wind = Wind.Light;
		} else {
			wind = Wind.Strong;
		}
		return wind;
	}

	private CloudCover getCloudCover(String cloudCoverValue) {
		CloudCover cloudCover = CloudCover.LessThan50;
		if (cloudCoverValue.equalsIgnoreCase("LessThan50")) {
			cloudCover = CloudCover.LessThan50;
		} else if (cloudCoverValue.equalsIgnoreCase("MoreThan50")) {
			cloudCover = CloudCover.MoreThan50;
		}
		return cloudCover;
	}

	private SolarRadiation getSolarRadiation(String solarRadiation) {
		SolarRadiation radiation = SolarRadiation.Moderate;
		if (solarRadiation.equalsIgnoreCase("Moderate")) {
			radiation = SolarRadiation.Moderate;
		} else if (solarRadiation.equalsIgnoreCase("Overcast")) {
			radiation = SolarRadiation.Overcast;
		} else {
			radiation = SolarRadiation.Strong;
		}
		return radiation;
	}

	private SurveyTime getSurveyTime(String surveyTime) {
		SurveyTime time = SurveyTime.Day;
		if (surveyTime.equalsIgnoreCase("Day")) {			
			time = SurveyTime.Day;
		} else {
			time = SurveyTime.Night;
		}
		return time;
	}
	
	public boolean stopDrivingSurvey(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.stopDrivingSurvey", data, dataRowID);
		getDriverViewPage().stopDrivingSurvey();
		TestContext.INSTANCE.getTestSetup().slowdownInSeconds(TestContext.INSTANCE.getTestSetup().getSlowdownInSeconds());
		getDriverViewPage().waitForUIUnBlock();

		return true;
	}
	
	/**
	 * Executes refreshPage action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean refreshPage(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.refreshPage", data, dataRowID);
		BrowserCommands.refresh();
		DriverViewPage driverViewPage = new DriverViewPage(TestContext.INSTANCE.getDriver(), 
				TestContext.INSTANCE.getTestSetup(),
				TestContext.INSTANCE.getBaseUrl());
		initializePageObject(TestContext.INSTANCE.getDriver(), driverViewPage);
		return true;
	}
	
	/* GIS Switch - Enable/Disable methods */
	
	public boolean turnOnMapView(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnMapView", data, dataRowID);
		getDriverViewPage().toggleMapSwitch(MapSwitchType.Map, true);
		return true;
	}
	public boolean turnOnSatelliteView(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnSatelliteView", data, dataRowID);
		getDriverViewPage().toggleMapSwitch(MapSwitchType.Satellite, true);
		return true;
	}
	
	public boolean verifyMapSwitchOn(String data, Integer dataRowID) throws Exception {
		logAction("DriverViewPageActions.verifyMapSwitchOn", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_VERIFY_MAP_SWITCH_ON, ARG_DATA, data);
		MapSwitchType switchType = MapSwitchType.Map;
		if (data.equalsIgnoreCase("Map")) {
			switchType = MapSwitchType.Map;
		} else if (data.equalsIgnoreCase("Satellite")) {
			switchType = MapSwitchType.Satellite;
		}
		return getDriverViewPage().isMapSwitchOn(switchType);
	}
	
	public boolean verifyMapSwitchOff(String data, Integer dataRowID) throws Exception {
		logAction("DriverViewPageActions.verifyMapSwitchOff", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_VERIFY_MAP_SWITCH_OFF, ARG_DATA, data);
		return !verifyMapSwitchOn(data, dataRowID);
	}
	
	/**
	 * Executes turnOnAllAssets action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean turnOnAllAssets(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnAllAssets", data, dataRowID);
		turnOnMaterialTypeCopper(data, dataRowID);
		turnOnMaterialTypeUnprotectedSteel(data, dataRowID);
		turnOnMaterialTypeProtectedSteel(data, dataRowID);
		turnOnMaterialTypeCastIron(data, dataRowID);
		turnOnMaterialTypeOtherPlastic(data, dataRowID);
		turnOnMaterialTypePEPlastic(data, dataRowID);
		turnOnUseAllPipes(data, dataRowID);
		return true;
	}
 
	/**
	 * Executes turnOnAllBoundaries action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean turnOnAllBoundaries(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnAllBoundaries", data, dataRowID);
		turnOnBoundariesDistrict(data, dataRowID);
		turnOnBoundariesDistrictPlat(data, dataRowID);
		return true;
	}
 
	/**
	 * Executes turnOnAllAssetsAndBoundaries action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean turnOnAllAssetsAndBoundaries(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnAllAssetsAndBoundaries", data, dataRowID);
		turnOnAllAssets(data, dataRowID);
		turnOnAllBoundaries(data, dataRowID);
		return true;
	}

	public boolean turnOnBoundariesDistrict(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnBoundariesDistrict", data, dataRowID);
		getDriverViewPage().toggleGisSwitch(GisSwitchType.BoundariesDistrict, true);
		return true;
	}
	public boolean turnOnBoundariesDistrictPlat(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnBoundariesDistrictPlat", data, dataRowID);
		getDriverViewPage().toggleGisSwitch(GisSwitchType.BoundariesDistrictPlat, true);
		return true;
	}
	public boolean turnOnMaterialTypeCopper(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnMaterialTypeCopper", data, dataRowID);
		getDriverViewPage().toggleGisSwitch(GisSwitchType.MaterialTypeCopper, true);
		return true;
	}
	public boolean turnOnMaterialTypeCastIron(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnMaterialTypeCastIron", data, dataRowID);
		getDriverViewPage().toggleGisSwitch(GisSwitchType.MaterialTypeCastIron, true);
		return true;
	}
	public boolean turnOnMaterialTypeOtherPlastic(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnMaterialTypeOtherPlastic", data, dataRowID);
		getDriverViewPage().toggleGisSwitch(GisSwitchType.MaterialTypeOtherPlastic, true);
		return true;
	}
	public boolean turnOnMaterialTypePEPlastic(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnMaterialTypePEPlastic", data, dataRowID);
		getDriverViewPage().toggleGisSwitch(GisSwitchType.MaterialTypePEPlastic, true);
		return true;
	}
	public boolean turnOnMaterialTypeProtectedSteel(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnMaterialTypeProtectedSteel", data, dataRowID);
		getDriverViewPage().toggleGisSwitch(GisSwitchType.MaterialTypeProtectedSteel, true);
		return true;
	}
	public boolean turnOnMaterialTypeUnprotectedSteel(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnMaterialTypeUnprotectedSteel", data, dataRowID);
		getDriverViewPage().toggleGisSwitch(GisSwitchType.MaterialTypeUnprotectedSteel, true);
		return true;
	}
	public boolean turnOnUseAllBoundaries(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnUseAllBoundaries", data, dataRowID);
		getDriverViewPage().toggleGisSwitch(GisSwitchType.UseAllBoundaries, true);
		return true;
	}
	public boolean turnOnUseAllPipes(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnUseAllPipes", data, dataRowID);
		getDriverViewPage().toggleGisSwitch(GisSwitchType.UseAllPipes, true);
		return true;
	}
	public boolean turnOffBoundariesDistrict(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOffBoundariesDistrict", data, dataRowID);
		getDriverViewPage().toggleGisSwitch(GisSwitchType.BoundariesDistrict, false);
		return true;
	}
	public boolean turnOffBoundariesDistrictPlat(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOffBoundariesDistrictPlat", data, dataRowID);
		getDriverViewPage().toggleGisSwitch(GisSwitchType.BoundariesDistrictPlat, false);
		return true;
	}
	public boolean turnOffMaterialTypeCopper(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOffMaterialTypeCopper", data, dataRowID);
		getDriverViewPage().toggleGisSwitch(GisSwitchType.MaterialTypeCopper, false);
		return true;
	}
	public boolean turnOffMaterialTypeCastIron(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOffMaterialTypeCastIron", data, dataRowID);
		getDriverViewPage().toggleGisSwitch(GisSwitchType.MaterialTypeCastIron, false);
		return true;
	}
	public boolean turnOffMaterialTypeOtherPlastic(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOffMaterialTypeOtherPlastic", data, dataRowID);
		getDriverViewPage().toggleGisSwitch(GisSwitchType.MaterialTypeOtherPlastic, false);
		return true;
	}
	public boolean turnOffMaterialTypePEPlastic(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOffMaterialTypePEPlastic", data, dataRowID);
		getDriverViewPage().toggleGisSwitch(GisSwitchType.MaterialTypePEPlastic, false);
		return true;
	}
	public boolean turnOffMaterialTypeProtectedSteel(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOffMaterialTypeProtectedSteel", data, dataRowID);
		getDriverViewPage().toggleGisSwitch(GisSwitchType.MaterialTypeProtectedSteel, false);
		return true;
	}
	public boolean turnOffMaterialTypeUnprotectedSteel(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOffMaterialTypeUnprotectedSteel", data, dataRowID);
		getDriverViewPage().toggleGisSwitch(GisSwitchType.MaterialTypeUnprotectedSteel, false);
		return true;
	}
	public boolean turnOffUseAllBoundaries(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOffUseAllBoundaries", data, dataRowID);
		getDriverViewPage().toggleGisSwitch(GisSwitchType.UseAllBoundaries, false);
		return true;
	}
	public boolean turnOffUseAllPipes(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOffUseAllPipes", data, dataRowID);
		getDriverViewPage().toggleGisSwitch(GisSwitchType.UseAllPipes, false);
		return true;
	}
	
	/**
	 * Executes turnOffAllAssets action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean turnOffAllAssets(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOffAllAssets", data, dataRowID);
		turnOffMaterialTypeCopper(data, dataRowID);
		turnOffMaterialTypeUnprotectedSteel(data, dataRowID);
		turnOffMaterialTypeProtectedSteel(data, dataRowID);
		turnOffMaterialTypeCastIron(data, dataRowID);
		turnOffMaterialTypeOtherPlastic(data, dataRowID);
		turnOffMaterialTypePEPlastic(data, dataRowID);
		turnOffUseAllPipes(data, dataRowID);
		return true;
	}
 
	/**
	 * Executes turnOffAllBoundaries action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean turnOffAllBoundaries(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOffAllBoundaries", data, dataRowID);
		turnOffBoundariesDistrict(data, dataRowID);
		turnOffBoundariesDistrictPlat(data, dataRowID);
		return true;
	}
 
	/**
	 * Executes turnOffAllAssetsAndBoundaries action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean turnOffAllAssetsAndBoundaries(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOffAllAssetsAndBoundaries", data, dataRowID);
		turnOffAllAssets(data, dataRowID);
		turnOffAllBoundaries(data, dataRowID);
		return true;
	}

	/* Display Switch (Enable/Disable) methods */

	public boolean turnOnAllDisplayOptions(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnAllDisplayOptions", data, dataRowID);
		turnOnEightHourHistory(data, dataRowID);
		turnOnConcentrationChart(data, dataRowID);
		turnOnFOVs(data, dataRowID);
		turnOnIndications(data, dataRowID);
		turnOnIsotopicAnalysis(data, dataRowID);
		turnOnLisas(data, dataRowID);
		turnOnNotes(data, dataRowID);
		turnOnWindRose(data, dataRowID);
		return true;
	}
	public boolean turnOnEightHourHistory(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnEightHourHistory", data, dataRowID);
		getDriverViewPage().toggleDisplaySwitch(DisplaySwitchType.EightHourHistory, true);
		return true;
	}
	public boolean turnOnConcentrationChart(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnConcentrationChart", data, dataRowID);
		getDriverViewPage().toggleDisplaySwitch(DisplaySwitchType.ConcentrationChart, true);
		return true;
	}
	public boolean turnOnFOVs(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnFOVs", data, dataRowID);
		getDriverViewPage().toggleDisplaySwitch(DisplaySwitchType.FOVs, true);
		return true;
	}
	public boolean turnOnIndications(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnIndications", data, dataRowID);
		getDriverViewPage().toggleDisplaySwitch(DisplaySwitchType.Indications, true);
		return true;
	}
	public boolean turnOnIsotopicAnalysis(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnIsotopicAnalysis", data, dataRowID);
		getDriverViewPage().toggleDisplaySwitch(DisplaySwitchType.IsotopicAnalysis, true);
		return true;
	}
	public boolean turnOnLisas(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnLisas", data, dataRowID);
		getDriverViewPage().toggleDisplaySwitch(DisplaySwitchType.Lisas, true);
		return true;
	}
	public boolean turnOnNotes(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnNotes", data, dataRowID);
		getDriverViewPage().toggleDisplaySwitch(DisplaySwitchType.Notes, true);
		return true;
	}
	public boolean turnOnWindRose(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnWindRose", data, dataRowID);
		getDriverViewPage().toggleDisplaySwitch(DisplaySwitchType.WindRose, true);
		return true;
	}
	public boolean turnOffAllDisplayOptions(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOffAllDisplayOptions", data, dataRowID);
		turnOffEightHourHistory(data, dataRowID);
		turnOffConcentrationChart(data, dataRowID);
		turnOffFOVs(data, dataRowID);
		turnOffIndications(data, dataRowID);
		turnOffIsotopicAnalysis(data, dataRowID);
		turnOffLisas(data, dataRowID);
		turnOffNotes(data, dataRowID);
		turnOffWindRose(data, dataRowID);
		return true;
	}
	public boolean turnOffEightHourHistory(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOffEightHourHistory", data, dataRowID);
		getDriverViewPage().toggleDisplaySwitch(DisplaySwitchType.EightHourHistory, false);
		return true;
	}
	public boolean turnOffConcentrationChart(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOffConcentrationChart", data, dataRowID);
		getDriverViewPage().toggleDisplaySwitch(DisplaySwitchType.ConcentrationChart, false);
		return true;
	}
	public boolean turnOffFOVs(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOffFOVs", data, dataRowID);
		getDriverViewPage().toggleDisplaySwitch(DisplaySwitchType.FOVs, false);
		return true;
	}
	public boolean turnOffIndications(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOffIndications", data, dataRowID);
		getDriverViewPage().toggleDisplaySwitch(DisplaySwitchType.Indications, false);
		return true;
	}
	public boolean turnOffIsotopicAnalysis(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOffIsotopicAnalysis", data, dataRowID);
		getDriverViewPage().toggleDisplaySwitch(DisplaySwitchType.IsotopicAnalysis, false);
		return true;
	}
	public boolean turnOffLisas(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOffLisas", data, dataRowID);
		getDriverViewPage().toggleDisplaySwitch(DisplaySwitchType.Lisas, false);
		return true;
	}
	public boolean turnOffNotes(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOffNotes", data, dataRowID);
		getDriverViewPage().toggleDisplaySwitch(DisplaySwitchType.Notes, false);
		return true;
	}
	public boolean turnOffWindRose(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOffWindRose", data, dataRowID);
		getDriverViewPage().toggleDisplaySwitch(DisplaySwitchType.WindRose, false);
		return true;
	}
	
	/* Button state verification methods */
	
	public boolean verifyDisplaySwitchIsOn(String data, Integer dataRowID) throws Exception {
		logAction("DriverViewPageActions.verifyDisplaySwitchIsOn", data, dataRowID);
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
		return getDriverViewPage().isDisplaySwitchOn(switchType);
	}

	public boolean verifyDisplaySwitchIsOff(String data, Integer dataRowID) throws Exception {
		logAction("DriverViewPageActions.verifyDisplaySwitchIsOff", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_VERIFY_DISPLAY_SWITCH_IS_OFF, ARG_DATA, data);
		return !verifyDisplaySwitchIsOn(data, dataRowID);
	}

	public boolean verifyGisSwitchIsOn(String data, Integer dataRowID) throws Exception {
		logAction("DriverViewPageActions.verifyGisSwitchIsOn", data, dataRowID);
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
		return getDriverViewPage().isGisSwitchOn(switchType);
	}

	public boolean verifyStartSurveyButtonFromSurveyDialogIsEnabled(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStartSurveyButtonFromSurveyDialogIsEnabled", data, dataRowID);
		String expectedClassAttr = "trigger_button active";
		String foundClassAttr = getDriverViewPage().getStartSurveyButtonFromStartSurveyDialog().getAttribute("class");
		log(String.format("Looking for class attribute-[%s], Found class attribute-[%s]", expectedClassAttr, foundClassAttr));
		return foundClassAttr.equalsIgnoreCase(expectedClassAttr);
	}

	public boolean verifyStartSurveyButtonFromSurveyDialogIsDisabled(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStartSurveyButtonFromSurveyDialogIsDisabled", data, dataRowID);
		String expectedClassAttr = "trigger_button";
		String foundClassAttr = getDriverViewPage().getStartSurveyButtonFromStartSurveyDialog().getAttribute("class");
		log(String.format("Looking for class attribute-[%s], Found class attribute-[%s]", expectedClassAttr, foundClassAttr));
		return foundClassAttr.equalsIgnoreCase(expectedClassAttr);
	}
	
	public boolean verifyGisSwitchIsOff(String data, Integer dataRowID) throws Exception {
		logAction("DriverViewPageActions.verifyGisSwitchIsOff", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_VERIFY_GIS_SWITCH_IS_OFF, ARG_DATA, data);
		return !verifyGisSwitchIsOn(data, dataRowID);
	}

	public boolean verifyPageLoaded(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyPageLoaded", data, dataRowID);
		this.getDriverViewPage().waitForPageLoad();
		this.getDriverViewPage().waitForConnectionComplete();
		return true;
	}

	public boolean verifyPositionButtonIsGreen(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyPositionButtonIsGreen", data, dataRowID);
		return getDriverViewPage().isPositionButtonGreen();
	}

	public boolean verifyPositionButtonIsNotSelected(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyPositionButtonIsNotSelected", data, dataRowID);
		return !getDriverViewPage().isPositionButtonSelected();
	}

	public boolean verifyPositionButtonIsSelected(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyPositionButtonIsSelected", data, dataRowID);
		return getDriverViewPage().isPositionButtonSelected();
	}

	public boolean verifyPressureButtonIsGreen(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyPressureButtonIsGreen", data, dataRowID);
		return getDriverViewPage().isPressureButtonGreen();
	}

	public boolean verifyPressureButtonIsRed(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyPressureButtonIsRed", data, dataRowID);
		return getDriverViewPage().isPressureButtonRed();
	}

	public boolean verifyStatusButtonIsGreen(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStatusButtonIsGreen", data, dataRowID);
		return getDriverViewPage().isStatusButtonGreen();
	}

	public boolean verifyStatusButtonIsGreenWithPlus(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStatusButtonIsGreenWithPlus", data, dataRowID);
		return getDriverViewPage().isStatusButtonGreenWithPlus();
	}

	public boolean verifyStatusButtonIsRed(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStatusButtonIsRed", data, dataRowID);
		return getDriverViewPage().isStatusButtonRed();
	}

	public boolean verifyStatusButtonIsExpanded(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStatusButtonIsExpanded", data, dataRowID);
		return getDriverViewPage().isStatusButtonOpen();
	}

	public boolean verifyStatusButtonIsCollapsed(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStatusButtonIsCollapsed", data, dataRowID);
		return !verifyStatusButtonIsExpanded(data, dataRowID);
	}

	public boolean verifyRefBottleMeasButtonIsDisabled(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyRefBottleMeasButtonIsDisabled", data, dataRowID);
		return !getDriverViewPage().isRefBottleMeasButtonEnabled();
	}

	public boolean verifyRefBottleMeasButtonIsEnabled(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyRefBottleMeasButtonIsEnabled", data, dataRowID);
		return getDriverViewPage().isRefBottleMeasButtonEnabled();
	}

	public boolean verifyStartEQSurveyButtonIsDisabled(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStartEQSurveyButtonIsDisabled", data, dataRowID);
		return !getDriverViewPage().isStartEQSurveyButtonEnabled();
	}

	public boolean verifyStartEQSurveyButtonIsEnabled(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStartEQSurveyButtonIsEnabled", data, dataRowID);
		return getDriverViewPage().isStartEQSurveyButtonEnabled();
	}

	public boolean verifyStartIsotopicCaptureButtonIsDisabled(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStartIsotopicCaptureButtonIsDisabled", data, dataRowID);
		return !getDriverViewPage().isStartIsotopicCaptureButtonEnabled();
	}

	public boolean verifyStartIsotopicCaptureButtonIsEnabled(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStartIsotopicCaptureButtonIsEnabled", data, dataRowID);
		return getDriverViewPage().isStartIsotopicCaptureButtonEnabled();
	}

	public boolean verifyStartSurveyButtonIsDisabled(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStartSurveyButtonIsDisabled", data, dataRowID);
		return !getDriverViewPage().isStartSurveyButtonEnabled();
	}

	public boolean verifyStartSurveyButtonIsEnabled(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStartSurveyButtonIsEnabled", data, dataRowID);
		return getDriverViewPage().isStartSurveyButtonEnabled();
	}

	public boolean verifyStopDrivingSurveyButtonIsDisabled(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStopDrivingSurveyButtonIsDisabled", data, dataRowID);
		return !getDriverViewPage().isStopDrivingSurveyButtonEnabled();
	}

	public boolean verifyStopDrivingSurveyButtonIsEnabled(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStopDrivingSurveyButtonIsEnabled", data, dataRowID);
		return getDriverViewPage().isStopDrivingSurveyButtonEnabled();
	}

	public boolean verifySystemShutdownButtonIsDisabled(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifySystemShutdownButtonIsDisabled", data, dataRowID);
		return !getDriverViewPage().isSystemShutdownButtonEnabled();
	}

	public boolean verifySystemShutdownButtonIsEnabled(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifySystemShutdownButtonIsEnabled", data, dataRowID);
		return getDriverViewPage().isSystemShutdownButtonEnabled();
	}
	
	public boolean verifySystemShutdownButtonIsDisplayed(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifySystemShutdownButtonIsDisplayed", data, dataRowID);
		return getDriverViewPage().getSystemShutdownButton().isDisplayed();
	}

	public boolean verifySystemShutdownButtonIsNotDisplayed(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifySystemShutdownButtonIsNotDisplayed", data, dataRowID);
		return !verifySystemShutdownButtonIsDisplayed(data, dataRowID);
	}

	public boolean verifyWBTempButtonIsGreen(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyWBTempButtonIsGreen", data, dataRowID);
		return getDriverViewPage().isWBTempButtonGreen();
	}

	public boolean verifyWBTempButtonIsRed(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyWBTempButtonIsRed", data, dataRowID);
		return getDriverViewPage().isWBTempButtonRed();
	}

	/* Verify EQ methods */
	/**
	 * Executes verifyEQModeDialogMessageEquals action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyEQModeDialogMessageEquals(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyEQModeDialogMessageEquals", data, dataRowID);
		return getDriverViewPage().verifyEQModeDialogMessageEquals(data);
	}
 
	/**
	 * Executes verifyEQModeDialogIsShown action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyEQModeDialogIsShown(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyEQModeDialogIsShown", data, dataRowID);
		return getDriverViewPage().isEQModeDialogShown();
	}
 
	/**
	 * Executes verifyEQModeDialogIsNotShown action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyEQModeDialogIsNotShown(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyEQModeDialogIsNotShown", data, dataRowID);
		return getDriverViewPage().isEQModeDialogHidden();
	}
	
	/* Verify OLMap elements */
	public boolean verifyLISAIsShownOnMap(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyLISAIsShownOnMap", data, dataRowID);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isLISAShownOnMap();
	}
	public boolean verifyIndicationsIsShownOnMap(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyIndicationsIsShownOnMap", data, dataRowID);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isIndicationsShownOnMap();
	}
	public boolean verifyConcentrationChartIsShownOnMap(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyConcentrationChartIsShownOnMap", data, dataRowID);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isConcentrationChartDataShowingOnMap();
	}
	public boolean verifyBreadcrumbIsShownOnMap(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyBreadcrumbIsShownOnMap", data, dataRowID);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isBreadcrumbShownOnMap();
	}
	public boolean verifyFOVIsShownOnMap(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyFOVIsShownOnMap", data, dataRowID);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isFOVShownOnMap();
	}
	public boolean verifyAssetIsShownOnMap(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyAssetIsShownOnMap", data, dataRowID);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isAssetShownOnMap();
	}
	public boolean verifyBoundariesIsShownOnMap(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyBoundariesIsShownOnMap", data, dataRowID);
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
		logAction("DriverViewPageActions.verifyCrossHairIconIsShownOnMap", data, dataRowID);
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
		logAction("DriverViewPageActions.verifyLISAIsNotShownOnMap", data, dataRowID);
		return !verifyLISAIsShownOnMap(data, dataRowID);
	}
	public boolean verifyIndicationsIsNotShownOnMap(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyIndicationsIsNotShownOnMap", data, dataRowID);
		return !verifyIndicationsIsShownOnMap(data, dataRowID);
	}
	public boolean verifyConcentrationChartIsNotShownOnMap(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyConcentrationChartIsNotShownOnMap", data, dataRowID);
		return !verifyConcentrationChartIsShownOnMap(data, dataRowID);
	}
	public boolean verifyBreadcrumbIsNotShownOnMap(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyBreadcrumbIsNotShownOnMap", data, dataRowID);
		return !verifyBreadcrumbIsShownOnMap(data, dataRowID);
	}
	public boolean verifyFOVIsNotShownOnMap(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyFOVIsNotShownOnMap", data, dataRowID);
		return !verifyFOVIsShownOnMap(data, dataRowID);
	}
	public boolean verifyAssetIsNotShownOnMap(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyAssetIsNotShownOnMap", data, dataRowID);
		return !verifyAssetIsShownOnMap(data, dataRowID);
	}
	public boolean verifyBoundariesIsNotShownOnMap(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyBoundariesIsNotShownOnMap", data, dataRowID);
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
		logAction("DriverViewPageActions.verifyCrossHairIconIsNotShownOnMap", data, dataRowID);
		return !verifyCrossHairIconIsShownOnMap(data, dataRowID);
	}

	/* SurveyInfo - Label method dependent on workingDataRow. All other methods in Base Class. */
	public boolean verifySurveyInfoTagLabelEquals(String data, Integer dataRowID) throws Exception {
		logAction("DriverViewPageActions.verifySurveyInfoTagLabelEquals", data, dataRowID);
		String actualTagValue = getDriverViewPage().getTagLabelText();
		return verifySurveyInfoTagLabelEquals(data, dataRowID, workingDataRow, actualTagValue);
	}

	/* TO BE IMPLEMENTED METHODS */
	public boolean verifyCarIconIsNotInCenter(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyCarIconIsNotInCenter", data, dataRowID);
		return true;
	}

	/**
	 * Executes enterFieldNotes action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception 
	 */
	public boolean enterFieldNotes(String data, Integer dataRowID) throws Exception {
		logAction("DriverViewPageActions.enterFieldNotes", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_DRIVER_VIEW_PAGE_ACTIONS + FN_ENTER_FIELD_NOTES, ARG_DATA, data);
		this.getDriverViewPage().setFieldNotesTextField(data);
		this.getDriverViewPage().clickFieldNotesSaveButton();
		return true;
	}
 
	/**
	 * Executes verifyMapViewIsShown action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyMapViewIsShown(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyMapViewIsShown", data, dataRowID);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isMapViewShown();
	}
 
	/**
	 * Executes verifySatelliteViewIsShown action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySatelliteViewIsShown(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifySatelliteViewIsShown", data, dataRowID);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isSatelliteViewShown();
	}
	
	/**
	 * Executes verifyDriverViewPageIsOpened action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDriverViewPageIsOpened(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyDriverViewPageIsOpened", data, dataRowID);
		return getDriverViewPage().checkIfAtDriverViewPage();
	}

	/**
	 * Executes verifyDisplaySwitch8HourHistoryButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitch8HourHistoryButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyDisplaySwitch8HourHistoryButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isDisplaySwitch8HourHistoryButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchWindroseButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchWindroseButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyDisplaySwitchWindroseButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isDisplaySwitchWindroseButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchConcentrationChartButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchConcentrationChartButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyDisplaySwitchConcentrationChartButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isDisplaySwitchConcentrationChartButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchNotesButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchNotesButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyDisplaySwitchNotesButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isDisplaySwitchNotesButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchIsotopicAnalysisButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchIsotopicAnalysisButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyDisplaySwitchIsotopicAnalysisButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isDisplaySwitchIsotopicAnalysisButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchIndicationsButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchIndicationsButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyDisplaySwitchIndicationsButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isDisplaySwitchIndicationsButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchLisasButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchLisasButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyDisplaySwitchLisasButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isDisplaySwitchLisasButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchFovsButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchFovsButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyDisplaySwitchFovsButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isDisplaySwitchFovsButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypeCastIronButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeCastIronButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyGisMaterialTypeCastIronButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isGisMaterialTypeCastIronButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypeCopperButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeCopperButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyGisMaterialTypeCopperButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isGisMaterialTypeCopperButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypeOtherPlasticButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeOtherPlasticButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyGisMaterialTypeOtherPlasticButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isGisMaterialTypeOtherPlasticButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypePEPlasticButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypePEPlasticButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyGisMaterialTypePEPlasticButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isGisMaterialTypePEPlasticButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypeProtectedSteelButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeProtectedSteelButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyGisMaterialTypeProtectedSteelButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isGisMaterialTypeProtectedSteelButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypeUnProtectedSteelButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeUnProtectedSteelButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyGisMaterialTypeUnProtectedSteelButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isGisMaterialTypeUnProtectedSteelButtonVisible();
	}
 
	/**
	 * Executes verifyGisUseAllPipesButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisUseAllPipesButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyGisUseAllPipesButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isGisUseAllPipesButtonVisible();
	}
 
	/**
	 * Executes verifyGisBoundaryBigBoundaryButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisBoundaryBigBoundaryButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyGisBoundaryBigBoundaryButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isGisBoundaryBigBoundaryButtonVisible();
	}
 
	/**
	 * Executes verifyGisBoundarySmallBoundaryButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisBoundarySmallBoundaryButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyGisBoundarySmallBoundaryButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isGisBoundarySmallBoundaryButtonVisible();
	}
 
	/**
	 * Executes verifyGisUseAllBoundariesButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisUseAllBoundariesButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyGisUseAllBoundariesButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isGisUseAllBoundariesButtonVisible();
	}
 
	/**
	 * Executes verifyStartSurveyButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyStartSurveyButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStartSurveyButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isStartSurveyButtonVisible();
	}
 
	/**
	 * Executes verifyStartEQSurveyButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyStartEQSurveyButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStartEQSurveyButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isStartEQSurveyButtonVisible();
	}
 
	/**
	 * Executes verifySystemShutdownButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySystemShutdownButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifySystemShutdownButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isSystemShutdownButtonVisible();
	}
 
	/**
	 * Executes verifyStopDrivingSurveyButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyStopDrivingSurveyButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStopDrivingSurveyButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isStopDrivingSurveyButtonVisible();
	}
 
	/**
	 * Executes verifyStartIsotopicCaptureButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyStartIsotopicCaptureButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStartIsotopicCaptureButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isStartIsotopicCaptureButtonVisible();
	}
 
	/**
	 * Executes verifyRefBottleMeasButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyRefBottleMeasButtonIsVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyRefBottleMeasButtonIsVisible", data, dataRowID);
		return getDriverViewPage().isRefBottleMeasButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitch8HourHistoryButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitch8HourHistoryButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyDisplaySwitch8HourHistoryButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isDisplaySwitch8HourHistoryButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchWindroseButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchWindroseButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyDisplaySwitchWindroseButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isDisplaySwitchWindroseButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchConcentrationChartButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchConcentrationChartButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyDisplaySwitchConcentrationChartButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isDisplaySwitchConcentrationChartButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchNotesButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchNotesButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyDisplaySwitchNotesButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isDisplaySwitchNotesButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchIsotopicAnalysisButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchIsotopicAnalysisButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyDisplaySwitchIsotopicAnalysisButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isDisplaySwitchIsotopicAnalysisButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchIndicationsButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchIndicationsButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyDisplaySwitchIndicationsButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isDisplaySwitchIndicationsButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchLisasButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchLisasButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyDisplaySwitchLisasButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isDisplaySwitchLisasButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchFovsButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchFovsButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyDisplaySwitchFovsButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isDisplaySwitchFovsButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypeCastIronButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeCastIronButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyGisMaterialTypeCastIronButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isGisMaterialTypeCastIronButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypeCopperButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeCopperButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyGisMaterialTypeCopperButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isGisMaterialTypeCopperButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypeOtherPlasticButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeOtherPlasticButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyGisMaterialTypeOtherPlasticButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isGisMaterialTypeOtherPlasticButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypePEPlasticButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypePEPlasticButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyGisMaterialTypePEPlasticButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isGisMaterialTypePEPlasticButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypeProtectedSteelButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeProtectedSteelButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyGisMaterialTypeProtectedSteelButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isGisMaterialTypeProtectedSteelButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypeUnProtectedSteelButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeUnProtectedSteelButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyGisMaterialTypeUnProtectedSteelButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isGisMaterialTypeUnProtectedSteelButtonVisible();
	}
 
	/**
	 * Executes verifyGisUseAllPipesButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisUseAllPipesButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyGisUseAllPipesButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isGisUseAllPipesButtonVisible();
	}
 
	/**
	 * Executes verifyGisBoundaryBigBoundaryButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisBoundaryBigBoundaryButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyGisBoundaryBigBoundaryButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isGisBoundaryBigBoundaryButtonVisible();
	}
 
	/**
	 * Executes verifyGisBoundarySmallBoundaryButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisBoundarySmallBoundaryButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyGisBoundarySmallBoundaryButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isGisBoundarySmallBoundaryButtonVisible();
	}
 
	/**
	 * Executes verifyGisUseAllBoundariesButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisUseAllBoundariesButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyGisUseAllBoundariesButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isGisUseAllBoundariesButtonVisible();
	}
 
	/**
	 * Executes verifyStartSurveyButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyStartSurveyButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStartSurveyButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isStartSurveyButtonVisible();
	}
 
	/**
	 * Executes verifyStartEQSurveyButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyStartEQSurveyButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStartEQSurveyButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isStartEQSurveyButtonVisible();
	}
 
	/**
	 * Executes verifySystemShutdownButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySystemShutdownButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifySystemShutdownButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isSystemShutdownButtonVisible();
	}
 
	/**
	 * Executes verifyStopDrivingSurveyButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyStopDrivingSurveyButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStopDrivingSurveyButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isStopDrivingSurveyButtonVisible();
	}
 
	/**
	 * Executes verifyStartIsotopicCaptureButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyStartIsotopicCaptureButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyStartIsotopicCaptureButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isStartIsotopicCaptureButtonVisible();
	}
 
	/**
	 * Executes verifyRefBottleMeasButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyRefBottleMeasButtonIsNotVisible(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.verifyRefBottleMeasButtonIsNotVisible", data, dataRowID);
		return !getDriverViewPage().isRefBottleMeasButtonVisible();
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
		else if (actionName.equals("clickOnFirstIndicationShownOnMap")) { return this.clickOnFirstIndicationShownOnMap(data, dataRowID); }
		else if (actionName.equals("clickOnGisButton")) { return this.clickOnGisButton(data, dataRowID); }
		else if (actionName.equals("clickOnHeaderInfoBox")) { return this.clickOnHeaderInfoBox(data, dataRowID); }
		else if (actionName.equals("clickOnMapButton")) { return this.clickOnMapButton(data, dataRowID); }
		else if (actionName.equals("clickOnModeButton")) { return this.clickOnModeButton(data, dataRowID); }
		else if (actionName.equals("clickOnPicarroLogoButton")) { return this.clickOnPicarroLogoButton(data, dataRowID); }
		else if (actionName.equals("clickOnPositionButton")) { return this.clickOnPositionButton(data, dataRowID); }
		else if (actionName.equals("clickOnStatusButton")) { return this.clickOnStatusButton(data, dataRowID); }
		else if (actionName.equals("clickOnZoomInButton")) { return this.clickOnZoomInButton(data, dataRowID); }
		else if (actionName.equals("clickOnZoomOutButton")) { return this.clickOnZoomOutButton(data, dataRowID); }
		else if (actionName.equals("enterFieldNotes")) { return this.enterFieldNotes(data, dataRowID); }
		else if (actionName.equals("hideCurtainView")) { return this.hideCurtainView(data, dataRowID); }
		else if (actionName.equals("insertTextById")) { return this.insertTextById(data, dataRowID); }
		else if (actionName.equals("insertTextByXPath")) { return this.insertTextByXPath(data, dataRowID); }
		else if (actionName.equals("open")) { return this.open(data, dataRowID); }
		else if (actionName.equals("refreshPage")) { return this.refreshPage(data, dataRowID); }
		else if (actionName.equals("selectDropDownByID")) { return this.selectDropDownByID(data, dataRowID); }
		else if (actionName.equals("selectDropDownByXPath")) { return this.selectDropDownByXPath(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByID")) { return this.selectRadioButtonByID(data, dataRowID); }
		else if (actionName.equals("selectRadioButtonByXPath")) { return this.selectRadioButtonByXPath(data, dataRowID); }
		else if (actionName.equals("showCurtainView")) { return this.showCurtainView(data, dataRowID); }
		else if (actionName.equals("startDrivingSurvey  ")) { return this.startDrivingSurvey  (data, dataRowID); }
		else if (actionName.equals("stopDrivingSurvey")) { return this.stopDrivingSurvey(data, dataRowID); }
		else if (actionName.equals("turnOffAllAssets")) { return this.turnOffAllAssets(data, dataRowID); }
		else if (actionName.equals("turnOffAllBoundaries")) { return this.turnOffAllBoundaries(data, dataRowID); }
		else if (actionName.equals("turnOffAllAssetsAndBoundaries")) { return this.turnOffAllAssetsAndBoundaries(data, dataRowID); }
		else if (actionName.equals("turnOffBoundariesDistrict")) { return this.turnOffBoundariesDistrict(data, dataRowID); }
		else if (actionName.equals("turnOffBoundariesDistrictPlat")) { return this.turnOffBoundariesDistrictPlat(data, dataRowID); }
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
		else if (actionName.equals("turnOffPosition")) { return this.turnOffPosition(data, dataRowID); }
		else if (actionName.equals("turnOffUseAllBoundaries")) { return this.turnOffUseAllBoundaries(data, dataRowID); }
		else if (actionName.equals("turnOffUseAllPipes")) { return this.turnOffUseAllPipes(data, dataRowID); }
		else if (actionName.equals("turnOffWindRose")) { return this.turnOffWindRose(data, dataRowID); }
		else if (actionName.equals("turnOnAllAssets")) { return this.turnOnAllAssets(data, dataRowID); }
		else if (actionName.equals("turnOnAllBoundaries")) { return this.turnOnAllBoundaries(data, dataRowID); }
		else if (actionName.equals("turnOnAllAssetsAndBoundaries")) { return this.turnOnAllAssetsAndBoundaries(data, dataRowID); }
		else if (actionName.equals("turnOnBoundariesDistrict")) { return this.turnOnBoundariesDistrict(data, dataRowID); }
		else if (actionName.equals("turnOnBoundariesDistrictPlat")) { return this.turnOnBoundariesDistrictPlat(data, dataRowID); }
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
		else if (actionName.equals("turnOnPosition")) { return this.turnOnPosition(data, dataRowID); }
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
		else if (actionName.equals("verifyDriverViewPageIsOpened")) { return this.verifyDriverViewPageIsOpened(data, dataRowID); }
		else if (actionName.equals("verifyEQModeDialogMessageEquals")) { return this.verifyEQModeDialogMessageEquals(data, dataRowID); }
		else if (actionName.equals("verifyEQModeDialogIsShown")) { return this.verifyEQModeDialogIsShown(data, dataRowID); }
		else if (actionName.equals("verifyEQModeDialogIsNotShown")) { return this.verifyEQModeDialogIsNotShown(data, dataRowID); }
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
		else if (actionName.equals("verifyMapSwitchOn")) { return this.verifyMapSwitchOn(data, dataRowID); }
		else if (actionName.equals("verifyMapSwitchOff")) { return this.verifyMapSwitchOff(data, dataRowID); }
		else if (actionName.equals("verifyMapViewIsShown")) { return this.verifyMapViewIsShown(data, dataRowID); }
		else if (actionName.equals("verifyPageLoaded")) { return this.verifyPageLoaded(data, dataRowID); }
		else if (actionName.equals("verifyPositionButtonIsGreen")) { return this.verifyPositionButtonIsGreen(data, dataRowID); }
		else if (actionName.equals("verifyPositionButtonIsNotSelected")) { return this.verifyPositionButtonIsNotSelected(data, dataRowID); }
		else if (actionName.equals("verifyPositionButtonIsSelected")) { return this.verifyPositionButtonIsSelected(data, dataRowID); }
		else if (actionName.equals("verifyPressureButtonIsGreen")) { return this.verifyPressureButtonIsGreen(data, dataRowID); }
		else if (actionName.equals("verifyPressureButtonIsRed")) { return this.verifyPressureButtonIsRed(data, dataRowID); }
		else if (actionName.equals("verifyRefBottleMeasButtonIsDisabled")) { return this.verifyRefBottleMeasButtonIsDisabled(data, dataRowID); }
		else if (actionName.equals("verifyRefBottleMeasButtonIsEnabled")) { return this.verifyRefBottleMeasButtonIsEnabled(data, dataRowID); }
		else if (actionName.equals("verifySatelliteViewIsShown")) { return this.verifySatelliteViewIsShown(data, dataRowID); }
		else if (actionName.equals("verifyStartEQSurveyButtonIsDisabled")) { return this.verifyStartEQSurveyButtonIsDisabled(data, dataRowID); }
		else if (actionName.equals("verifyStartEQSurveyButtonIsEnabled")) { return this.verifyStartEQSurveyButtonIsEnabled(data, dataRowID); }
		else if (actionName.equals("verifyStartIsotopicCaptureButtonIsDisabled")) { return this.verifyStartIsotopicCaptureButtonIsDisabled(data, dataRowID); }
		else if (actionName.equals("verifyStartIsotopicCaptureButtonIsEnabled")) { return this.verifyStartIsotopicCaptureButtonIsEnabled(data, dataRowID); }
		else if (actionName.equals("verifyStartSurveyButtonIsDisabled")) { return this.verifyStartSurveyButtonIsDisabled(data, dataRowID); }
		else if (actionName.equals("verifyStartSurveyButtonIsEnabled")) { return this.verifyStartSurveyButtonIsEnabled(data, dataRowID); }
		else if (actionName.equals("verifyStatusButtonIsGreen")) { return this.verifyStatusButtonIsGreen(data, dataRowID); }
		else if (actionName.equals("verifyStatusButtonIsGreenWithPlus")) { return this.verifyStatusButtonIsGreenWithPlus(data, dataRowID); }
		else if (actionName.equals("verifyStatusButtonIsRed")) { return this.verifyStatusButtonIsRed(data, dataRowID); }
		else if (actionName.equals("verifyStatusButtonIsExpanded")) { return this.verifyStatusButtonIsExpanded(data, dataRowID); }
		else if (actionName.equals("verifyStatusButtonIsCollapsed")) { return this.verifyStatusButtonIsCollapsed(data, dataRowID); }
		else if (actionName.equals("verifyStopDrivingSurveyButtonIsDisabled")) { return this.verifyStopDrivingSurveyButtonIsDisabled(data, dataRowID); }
		else if (actionName.equals("verifyStopDrivingSurveyButtonIsEnabled")) { return this.verifyStopDrivingSurveyButtonIsEnabled(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoAnalyzerLabelEquals")) { return this.verifySurveyInfoAnalyzerLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoDriverLabelEquals")) { return this.verifySurveyInfoDriverLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoModeLabelEquals")) { return this.verifySurveyInfoModeLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoStabilityClassLabelEquals")) { return this.verifySurveyInfoStabilityClassLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoSurveyorLabelEquals")) { return this.verifySurveyInfoSurveyorLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoSurveyStatusLabelEquals")) { return this.verifySurveyInfoSurveyStatusLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoTagLabelEquals")) { return this.verifySurveyInfoTagLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoTimeLabelEquals")) { return this.verifySurveyInfoTimeLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoTimeLabelStartsWith")) { return this.verifySurveyInfoTimeLabelStartsWith(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoTimeElapsedLabelEquals")) { return this.verifySurveyInfoTimeElapsedLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoTimeElapsedLabelStartsWith")) { return this.verifySurveyInfoTimeElapsedLabelStartsWith(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoTimeRemainingLabelEquals")) { return this.verifySurveyInfoTimeRemainingLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoTimeRemainingLabelStartsWith")) { return this.verifySurveyInfoTimeRemainingLabelStartsWith(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoTimeElapsedIsTickingForward")) { return this.verifySurveyInfoTimeElapsedIsTickingForward(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoTimeLabelIsTickingForward")) { return this.verifySurveyInfoTimeLabelIsTickingForward(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoTimeRemainingLabelIsTickingBackward")) { return this.verifySurveyInfoTimeRemainingLabelIsTickingBackward(data, dataRowID); }
		else if (actionName.equals("verifySurveyInfoZoomLevelLabelEquals")) { return this.verifySurveyInfoZoomLevelLabelEquals(data, dataRowID); }
		else if (actionName.equals("verifySystemShutdownButtonIsDisabled")) { return this.verifySystemShutdownButtonIsDisabled(data, dataRowID); }
		else if (actionName.equals("verifySystemShutdownButtonIsEnabled")) { return this.verifySystemShutdownButtonIsEnabled(data, dataRowID); }
		else if (actionName.equals("verifySystemShutdownButtonIsDisplayed")) { return this.verifySystemShutdownButtonIsDisplayed(data, dataRowID); }
		else if (actionName.equals("verifySystemShutdownButtonIsNotDisplayed")) { return this.verifySystemShutdownButtonIsNotDisplayed(data, dataRowID); }
		else if (actionName.equals("verifyWBTempButtonIsGreen")) { return this.verifyWBTempButtonIsGreen(data, dataRowID); }
		else if (actionName.equals("verifyWBTempButtonIsRed")) { return this.verifyWBTempButtonIsRed(data, dataRowID); }
		else if (actionName.equals("verifyWindRoseIsNotShownOnMap")) { return this.verifyWindRoseIsNotShownOnMap(data, dataRowID); }
		else if (actionName.equals("verifyWindRoseIsShownOnMap")) { return this.verifyWindRoseIsShownOnMap(data, dataRowID); }
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
		else if (actionName.equals("verifyStartSurveyButtonIsVisible")) { return this.verifyStartSurveyButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyStartEQSurveyButtonIsVisible")) { return this.verifyStartEQSurveyButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifySystemShutdownButtonIsVisible")) { return this.verifySystemShutdownButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyStopDrivingSurveyButtonIsVisible")) { return this.verifyStopDrivingSurveyButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyStartIsotopicCaptureButtonIsVisible")) { return this.verifyStartIsotopicCaptureButtonIsVisible(data, dataRowID); }
		else if (actionName.equals("verifyRefBottleMeasButtonIsVisible")) { return this.verifyRefBottleMeasButtonIsVisible(data, dataRowID); }
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
		else if (actionName.equals("verifyMapShownForZoomLevelIsCorrect")) { return this.verifyMapShownForZoomLevelIsCorrect(data, dataRowID); }
		else if (actionName.equals("verifyStartSurveyButtonIsNotVisible")) { return this.verifyStartSurveyButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyStartEQSurveyButtonIsNotVisible")) { return this.verifyStartEQSurveyButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifySystemShutdownButtonIsNotVisible")) { return this.verifySystemShutdownButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyStopDrivingSurveyButtonIsNotVisible")) { return this.verifyStopDrivingSurveyButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyStartIsotopicCaptureButtonIsNotVisible")) { return this.verifyStartIsotopicCaptureButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyRefBottleMeasButtonIsNotVisible")) { return this.verifyRefBottleMeasButtonIsNotVisible(data, dataRowID); }
		else if (actionName.equals("verifyIsotopicCaptureResultIsPresentOnMap")) { return this.verifyIsotopicCaptureResultIsPresentOnMap(data, dataRowID); }
		else if (actionName.equals("verifyIsotopicCaptureResultIsNotPresentOnMap")) { return this.verifyIsotopicCaptureResultIsNotPresentOnMap(data, dataRowID); }
		else if (actionName.equals("verifyRefGasCaptureResultIsPresentOnMap")) { return this.verifyRefGasCaptureResultIsPresentOnMap(data, dataRowID); }
		else if (actionName.equals("verifyRefGasCaptureResultIsNotPresentOnMap")) { return this.verifyRefGasCaptureResultIsNotPresentOnMap(data, dataRowID); }
		else if (actionName.equals("waitForConnectionToComplete")) { return this.waitForConnectionToComplete(data, dataRowID); }		
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

	public DriverViewPage getDriverViewPage() {
		return (DriverViewPage)this.pageObject;
	}
}