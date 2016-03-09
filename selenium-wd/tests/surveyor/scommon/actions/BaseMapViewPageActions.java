package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.OLMapUtility;
import common.source.BrowserCommands;
import common.source.TestContext;
import common.source.OLMapUtility.IconColor;
import common.source.TestSetup;
import surveyor.scommon.source.BaseMapViewPage.DisplaySwitchType;
import surveyor.scommon.source.BaseMapViewPage.GisSwitchType;
import surveyor.scommon.source.BaseMapViewPage.MapSwitchType;
import surveyor.scommon.actions.data.DriverViewDataReader.DriverViewDataRow;
import surveyor.scommon.source.BaseMapViewPage;

public class BaseMapViewPageActions extends BasePageActions {

	private static final String FN_VERIFY_FIELD_NOTES_IS_SHOWN_ON_MAP = "verifyFieldNotesIsShownOnMap";

	private static final String FN_VERIFY_GIS_SWITCH_IS_OFF = "verifyGisSwitchIsOff";
	private static final String FN_VERIFY_DISPLAY_SWITCH_IS_OFF = "verifyDisplaySwitchIsOff";
	private static final String FN_VERIFY_MAP_SWITCH_OFF = "verifyMapSwitchOff";
	private static final String FN_VERIFY_MAP_SWITCH_ON = "verifyMapSwitchOn";
	private static final String FN_VERIFY_GIS_SWITCH_IS_ON = "verifyGisSwitchIsOn";
	private static final String FN_VERIFY_DISPLAY_SWITCH_IS_ON = "verifyDisplaySwitchIsOn";
	private static final String FN_VERIFY_CROSS_HAIR_ICON_IS_SHOWN_ON_MAP = "verifyCrossHairIconIsShownOnMap";
	private static final String FN_VERIFY_SURVEY_INFO_TAG_LABEL_EQUALS = "verifySurveyInfoTagLabelEquals";
	
	private static final String CLS_BASEMAP_VIEW_PAGE_ACTIONS = "BaseMapViewPageActions::";
	
	protected BaseMapViewPage pageObject = null;

	public BaseMapViewPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL);
	}

	public void initializePageObject(WebDriver driver, BaseMapViewPage pageObj) {
		setPageObject(pageObj);
		PageFactory.initElements(driver, getPageObject());
	}

	public boolean clickOnCurtainArrowDownButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnCurtainArrowDownButton", data, dataRowID);
		getPageObject().clickCurtainArrowDownButton();		
		return true;
	}

	public boolean clickOnCurtainArrowLeftButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnCurtainArrowLeftButton", data, dataRowID);
		getPageObject().clickCurtainArrowLeftButton();
		return true;
	}

	public boolean clickOnCurtainArrowRightButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnCurtainArrowRightButton", data, dataRowID);
		getPageObject().clickCurtainArrowRightButton();
		return true;
	}

	public boolean clickOnCurtainArrowUpButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnCurtainArrowUpButton", data, dataRowID);
		getPageObject().clickCurtainArrowUpButton();
		return true;
	}

	public boolean clickOnCurtainReturnButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnCurtainReturnButton", data, dataRowID);
		getPageObject().clickCurtainReturnButton();
		return true;
	}

	public boolean clickOnCurtainZoomInButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnCurtainZoomInButton", data, dataRowID);
		getPageObject().clickCurtainZoomInButton();
		return true;
	}

	public boolean clickOnCurtainZoomOutButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnCurtainZoomOutButton", data, dataRowID);
		getPageObject().clickCurtainZoomOutButton();
		return true;
	}

	public boolean clickOnDisplayButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnDisplayButton", data, dataRowID);
		getPageObject().clickDisplayButton();
		return true;
	}

	public boolean clickOnFirstIndicationShownOnMap(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnFirstIndicationShownOnMap", data, dataRowID);
		String mapCanvasXPath = "//*[@id='map']/div/canvas";
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.clickFirstIndicationOnMap(mapCanvasXPath);
	}

	public boolean clickOnGisButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnGisButton", data, dataRowID);
		getPageObject().clickGisButton();
		return true;
	}

	public boolean clickOnMapButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnMapButton", data, dataRowID);
		getPageObject().clickMapButton();
		TestContext.INSTANCE.getTestSetup().slowdownInSeconds(TestContext.INSTANCE.getTestSetup().getSlowdownInSeconds());
		return true;
	}

	public boolean clickOnPicarroLogoButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnPicarroLogoButton", data, dataRowID);
		this.getPageObject().clickPicarroLogoButton();
		return true;
	}

	/**
	 * Executes clickOnZoomInButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnZoomInButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnZoomInButton", data, dataRowID);
		getPageObject().clickZoomInButton();
		return true;
	}
 
	/**
	 * Executes clickOnZoomOutButton action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnZoomOutButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnZoomOutButton", data, dataRowID);
		getPageObject().clickZoomOutButton();
		return true;
	}

	public boolean hideCurtainView(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".hideCurtainView", data, dataRowID);
		getPageObject().hideCurtainMenu();
		return true;
	}

	public boolean showCurtainView(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".showCurtainView", data, dataRowID);
		getPageObject().clickCurtainButton();
		return true;
	}

	public boolean open(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".open", data, dataRowID);
		getPageObject().open();
		return true;
	}

	public boolean turnOnMapView(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnMapView", data, dataRowID);
		getPageObject().toggleMapSwitch(MapSwitchType.Map, true);
		return true;
	}
	public boolean turnOnSatelliteView(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnSatelliteView", data, dataRowID);
		getPageObject().toggleMapSwitch(MapSwitchType.Satellite, true);
		return true;
	}
	
	public boolean verifyMapSwitchOn(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifyMapSwitchOn", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASEMAP_VIEW_PAGE_ACTIONS + FN_VERIFY_MAP_SWITCH_ON, ARG_DATA, data);
		MapSwitchType switchType = MapSwitchType.Map;
		if (data.equalsIgnoreCase("Map")) {
			switchType = MapSwitchType.Map;
		} else if (data.equalsIgnoreCase("Satellite")) {
			switchType = MapSwitchType.Satellite;
		}
		return getPageObject().isMapSwitchOn(switchType);
	}
	
	public boolean verifyMapSwitchOff(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifyMapSwitchOff", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASEMAP_VIEW_PAGE_ACTIONS + FN_VERIFY_MAP_SWITCH_OFF, ARG_DATA, data);
		return !verifyMapSwitchOn(data, dataRowID);
	}

	/* GIS Switch - Enable/Disable methods */

	/**
	 * Executes turnOnAllAssets action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean turnOnAllAssets(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnAllAssets", data, dataRowID);
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
		logAction(getRuntimeType() + ".turnOnAllBoundaries", data, dataRowID);
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
		logAction(getRuntimeType() + ".turnOnAllAssetsAndBoundaries", data, dataRowID);
		turnOnAllAssets(data, dataRowID);
		turnOnAllBoundaries(data, dataRowID);
		return true;
	}

	public boolean turnOnBoundariesDistrict(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnBoundariesDistrict", data, dataRowID);
		getPageObject().toggleGisSwitch(GisSwitchType.BoundariesDistrict, true);
		return true;
	}
	public boolean turnOnBoundariesDistrictPlat(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnBoundariesDistrictPlat", data, dataRowID);
		getPageObject().toggleGisSwitch(GisSwitchType.BoundariesDistrictPlat, true);
		return true;
	}
	public boolean turnOnMaterialTypeCopper(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnMaterialTypeCopper", data, dataRowID);
		getPageObject().toggleGisSwitch(GisSwitchType.MaterialTypeCopper, true);
		return true;
	}
	public boolean turnOnMaterialTypeCastIron(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnMaterialTypeCastIron", data, dataRowID);
		getPageObject().toggleGisSwitch(GisSwitchType.MaterialTypeCastIron, true);
		return true;
	}
	public boolean turnOnMaterialTypeOtherPlastic(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnMaterialTypeOtherPlastic", data, dataRowID);
		getPageObject().toggleGisSwitch(GisSwitchType.MaterialTypeOtherPlastic, true);
		return true;
	}
	public boolean turnOnMaterialTypePEPlastic(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnMaterialTypePEPlastic", data, dataRowID);
		getPageObject().toggleGisSwitch(GisSwitchType.MaterialTypePEPlastic, true);
		return true;
	}
	public boolean turnOnMaterialTypeProtectedSteel(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnMaterialTypeProtectedSteel", data, dataRowID);
		getPageObject().toggleGisSwitch(GisSwitchType.MaterialTypeProtectedSteel, true);
		return true;
	}
	public boolean turnOnMaterialTypeUnprotectedSteel(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnMaterialTypeUnprotectedSteel", data, dataRowID);
		getPageObject().toggleGisSwitch(GisSwitchType.MaterialTypeUnprotectedSteel, true);
		return true;
	}
	public boolean turnOnUseAllBoundaries(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnUseAllBoundaries", data, dataRowID);
		getPageObject().toggleGisSwitch(GisSwitchType.UseAllBoundaries, true);
		return true;
	}
	public boolean turnOnUseAllPipes(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnUseAllPipes", data, dataRowID);
		getPageObject().toggleGisSwitch(GisSwitchType.UseAllPipes, true);
		return true;
	}
	public boolean turnOffBoundariesDistrict(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffBoundariesDistrict", data, dataRowID);
		getPageObject().toggleGisSwitch(GisSwitchType.BoundariesDistrict, false);
		return true;
	}
	public boolean turnOffBoundariesDistrictPlat(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffBoundariesDistrictPlat", data, dataRowID);
		getPageObject().toggleGisSwitch(GisSwitchType.BoundariesDistrictPlat, false);
		return true;
	}
	public boolean turnOffMaterialTypeCopper(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffMaterialTypeCopper", data, dataRowID);
		getPageObject().toggleGisSwitch(GisSwitchType.MaterialTypeCopper, false);
		return true;
	}
	public boolean turnOffMaterialTypeCastIron(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffMaterialTypeCastIron", data, dataRowID);
		getPageObject().toggleGisSwitch(GisSwitchType.MaterialTypeCastIron, false);
		return true;
	}
	public boolean turnOffMaterialTypeOtherPlastic(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffMaterialTypeOtherPlastic", data, dataRowID);
		getPageObject().toggleGisSwitch(GisSwitchType.MaterialTypeOtherPlastic, false);
		return true;
	}
	public boolean turnOffMaterialTypePEPlastic(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffMaterialTypePEPlastic", data, dataRowID);
		getPageObject().toggleGisSwitch(GisSwitchType.MaterialTypePEPlastic, false);
		return true;
	}
	public boolean turnOffMaterialTypeProtectedSteel(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffMaterialTypeProtectedSteel", data, dataRowID);
		getPageObject().toggleGisSwitch(GisSwitchType.MaterialTypeProtectedSteel, false);
		return true;
	}
	public boolean turnOffMaterialTypeUnprotectedSteel(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffMaterialTypeUnprotectedSteel", data, dataRowID);
		getPageObject().toggleGisSwitch(GisSwitchType.MaterialTypeUnprotectedSteel, false);
		return true;
	}
	public boolean turnOffUseAllBoundaries(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffUseAllBoundaries", data, dataRowID);
		getPageObject().toggleGisSwitch(GisSwitchType.UseAllBoundaries, false);
		return true;
	}
	public boolean turnOffUseAllPipes(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffUseAllPipes", data, dataRowID);
		getPageObject().toggleGisSwitch(GisSwitchType.UseAllPipes, false);
		return true;
	}
	
	/**
	 * Executes turnOffAllAssets action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean turnOffAllAssets(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffAllAssets", data, dataRowID);
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
		logAction(getRuntimeType() + ".turnOffAllBoundaries", data, dataRowID);
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
		logAction(getRuntimeType() + ".turnOffAllAssetsAndBoundaries", data, dataRowID);
		turnOffAllAssets(data, dataRowID);
		turnOffAllBoundaries(data, dataRowID);
		return true;
	}

	/* Display Switch (Enable/Disable) methods */

	public boolean turnOnAllDisplayOptions(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnAllDisplayOptions", data, dataRowID);
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
		logAction(getRuntimeType() + ".turnOnEightHourHistory", data, dataRowID);
		getPageObject().toggleDisplaySwitch(DisplaySwitchType.EightHourHistory, true);
		return true;
	}
	public boolean turnOnConcentrationChart(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnConcentrationChart", data, dataRowID);
		getPageObject().toggleDisplaySwitch(DisplaySwitchType.ConcentrationChart, true);
		return true;
	}
	public boolean turnOnFOVs(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnFOVs", data, dataRowID);
		getPageObject().toggleDisplaySwitch(DisplaySwitchType.FOVs, true);
		return true;
	}
	public boolean turnOnIndications(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnIndications", data, dataRowID);
		getPageObject().toggleDisplaySwitch(DisplaySwitchType.Indications, true);
		return true;
	}
	public boolean turnOnIsotopicAnalysis(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnIsotopicAnalysis", data, dataRowID);
		getPageObject().toggleDisplaySwitch(DisplaySwitchType.IsotopicAnalysis, true);
		return true;
	}
	public boolean turnOnLisas(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnLisas", data, dataRowID);
		getPageObject().toggleDisplaySwitch(DisplaySwitchType.Lisas, true);
		return true;
	}
	public boolean turnOnNotes(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnNotes", data, dataRowID);
		getPageObject().toggleDisplaySwitch(DisplaySwitchType.Notes, true);
		return true;
	}
	public boolean turnOnWindRose(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnWindRose", data, dataRowID);
		getPageObject().toggleDisplaySwitch(DisplaySwitchType.WindRose, true);
		return true;
	}
	public boolean turnOffAllDisplayOptions(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffAllDisplayOptions", data, dataRowID);
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
		logAction(getRuntimeType() + ".turnOffEightHourHistory", data, dataRowID);
		getPageObject().toggleDisplaySwitch(DisplaySwitchType.EightHourHistory, false);
		return true;
	}
	public boolean turnOffConcentrationChart(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffConcentrationChart", data, dataRowID);
		getPageObject().toggleDisplaySwitch(DisplaySwitchType.ConcentrationChart, false);
		return true;
	}
	public boolean turnOffFOVs(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffFOVs", data, dataRowID);
		getPageObject().toggleDisplaySwitch(DisplaySwitchType.FOVs, false);
		return true;
	}
	public boolean turnOffIndications(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffIndications", data, dataRowID);
		getPageObject().toggleDisplaySwitch(DisplaySwitchType.Indications, false);
		return true;
	}
	public boolean turnOffIsotopicAnalysis(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffIsotopicAnalysis", data, dataRowID);
		getPageObject().toggleDisplaySwitch(DisplaySwitchType.IsotopicAnalysis, false);
		return true;
	}
	public boolean turnOffLisas(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffLisas", data, dataRowID);
		getPageObject().toggleDisplaySwitch(DisplaySwitchType.Lisas, false);
		return true;
	}
	public boolean turnOffNotes(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffNotes", data, dataRowID);
		getPageObject().toggleDisplaySwitch(DisplaySwitchType.Notes, false);
		return true;
	}
	public boolean turnOffWindRose(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffWindRose", data, dataRowID);
		getPageObject().toggleDisplaySwitch(DisplaySwitchType.WindRose, false);
		return true;
	}
	
	/* Button state verification methods */
	
	public boolean verifyDisplaySwitchIsOn(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifyDisplaySwitchIsOn", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASEMAP_VIEW_PAGE_ACTIONS + FN_VERIFY_DISPLAY_SWITCH_IS_ON, ARG_DATA, data);
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
		return getPageObject().isDisplaySwitchOn(switchType);
	}

	public boolean verifyDisplaySwitchIsOff(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifyDisplaySwitchIsOff", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASEMAP_VIEW_PAGE_ACTIONS + FN_VERIFY_DISPLAY_SWITCH_IS_OFF, ARG_DATA, data);
		return !verifyDisplaySwitchIsOn(data, dataRowID);
	}

	public boolean verifyGisSwitchIsOn(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifyGisSwitchIsOn", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASEMAP_VIEW_PAGE_ACTIONS + FN_VERIFY_GIS_SWITCH_IS_ON, ARG_DATA, data);
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
		return getPageObject().isGisSwitchOn(switchType);
	}

	public boolean verifyGisSwitchIsOff(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifyGisSwitchIsOff", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASEMAP_VIEW_PAGE_ACTIONS + FN_VERIFY_GIS_SWITCH_IS_OFF, ARG_DATA, data);
		return !verifyGisSwitchIsOn(data, dataRowID);
	}

	/* Verify OLMap elements */
	public boolean verifyLISAIsShownOnMap(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyLISAIsShownOnMap", data, dataRowID);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isLISAShownOnMap();
	}
	public boolean verifyIndicationsIsShownOnMap(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyIndicationsIsShownOnMap", data, dataRowID);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isIndicationsShownOnMap();
	}
	public boolean verifyConcentrationChartIsShownOnMap(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyConcentrationChartIsShownOnMap", data, dataRowID);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isConcentrationChartDataShowingOnMap();
	}
	public boolean verifyBreadcrumbIsShownOnMap(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyBreadcrumbIsShownOnMap", data, dataRowID);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isBreadcrumbShownOnMap();
	}
	public boolean verifyFOVIsShownOnMap(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyFOVIsShownOnMap", data, dataRowID);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isFOVShownOnMap();
	}
	public boolean verifyAssetIsShownOnMap(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyAssetIsShownOnMap", data, dataRowID);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isAssetShownOnMap();
	}
	public boolean verifyBoundariesIsShownOnMap(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyBoundariesIsShownOnMap", data, dataRowID);
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
		logAction(getRuntimeType() + ".verifyCrossHairIconIsShownOnMap", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASEMAP_VIEW_PAGE_ACTIONS + FN_VERIFY_CROSS_HAIR_ICON_IS_SHOWN_ON_MAP, ARG_DATA, data);
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
		logAction(getRuntimeType() + ".verifyLISAIsNotShownOnMap", data, dataRowID);
		return !verifyLISAIsShownOnMap(data, dataRowID);
	}
	public boolean verifyIndicationsIsNotShownOnMap(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyIndicationsIsNotShownOnMap", data, dataRowID);
		return !verifyIndicationsIsShownOnMap(data, dataRowID);
	}
	public boolean verifyConcentrationChartIsNotShownOnMap(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyConcentrationChartIsNotShownOnMap", data, dataRowID);
		return !verifyConcentrationChartIsShownOnMap(data, dataRowID);
	}
	public boolean verifyBreadcrumbIsNotShownOnMap(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyBreadcrumbIsNotShownOnMap", data, dataRowID);
		return !verifyBreadcrumbIsShownOnMap(data, dataRowID);
	}
	public boolean verifyFOVIsNotShownOnMap(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyFOVIsNotShownOnMap", data, dataRowID);
		return !verifyFOVIsShownOnMap(data, dataRowID);
	}
	public boolean verifyAssetIsNotShownOnMap(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyAssetIsNotShownOnMap", data, dataRowID);
		return !verifyAssetIsShownOnMap(data, dataRowID);
	}
	public boolean verifyBoundariesIsNotShownOnMap(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyBoundariesIsNotShownOnMap", data, dataRowID);
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
		logAction(getRuntimeType() + ".verifyCrossHairIconIsNotShownOnMap", data, dataRowID);
		return !verifyCrossHairIconIsShownOnMap(data, dataRowID);
	}

	/* TO BE IMPLEMENTED METHODS */
	public boolean verifyCarIconIsNotInCenter(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyCarIconIsNotInCenter", data, dataRowID);
		return true;
	}

	/**
	 * Executes verifyMapViewIsShown action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyMapViewIsShown(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyMapViewIsShown", data, dataRowID);
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
		logAction(getRuntimeType() + ".verifySatelliteViewIsShown", data, dataRowID);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isSatelliteViewShown();
	}
	
	/**
	 * Executes verifyDisplaySwitch8HourHistoryButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitch8HourHistoryButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitch8HourHistoryButtonIsVisible", data, dataRowID);
		return this.pageObject.isDisplaySwitch8HourHistoryButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchWindroseButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchWindroseButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchWindroseButtonIsVisible", data, dataRowID);
		return this.pageObject.isDisplaySwitchWindroseButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchConcentrationChartButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchConcentrationChartButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchConcentrationChartButtonIsVisible", data, dataRowID);
		return this.pageObject.isDisplaySwitchConcentrationChartButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchNotesButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchNotesButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchNotesButtonIsVisible", data, dataRowID);
		return this.pageObject.isDisplaySwitchNotesButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchIsotopicAnalysisButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchIsotopicAnalysisButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchIsotopicAnalysisButtonIsVisible", data, dataRowID);
		return this.pageObject.isDisplaySwitchIsotopicAnalysisButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchIndicationsButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchIndicationsButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchIndicationsButtonIsVisible", data, dataRowID);
		return this.pageObject.isDisplaySwitchIndicationsButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchLisasButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchLisasButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchLisasButtonIsVisible", data, dataRowID);
		return this.pageObject.isDisplaySwitchLisasButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchFovsButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchFovsButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchFovsButtonIsVisible", data, dataRowID);
		return this.pageObject.isDisplaySwitchFovsButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypeCastIronButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeCastIronButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypeCastIronButtonIsVisible", data, dataRowID);
		return this.pageObject.isGisMaterialTypeCastIronButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypeCopperButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeCopperButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypeCopperButtonIsVisible", data, dataRowID);
		return this.pageObject.isGisMaterialTypeCopperButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypeOtherPlasticButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeOtherPlasticButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypeOtherPlasticButtonIsVisible", data, dataRowID);
		return this.pageObject.isGisMaterialTypeOtherPlasticButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypePEPlasticButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypePEPlasticButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypePEPlasticButtonIsVisible", data, dataRowID);
		return this.pageObject.isGisMaterialTypePEPlasticButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypeProtectedSteelButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeProtectedSteelButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypeProtectedSteelButtonIsVisible", data, dataRowID);
		return this.pageObject.isGisMaterialTypeProtectedSteelButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypeUnProtectedSteelButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeUnProtectedSteelButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypeUnProtectedSteelButtonIsVisible", data, dataRowID);
		return this.pageObject.isGisMaterialTypeUnProtectedSteelButtonVisible();
	}
 
	/**
	 * Executes verifyGisUseAllPipesButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisUseAllPipesButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisUseAllPipesButtonIsVisible", data, dataRowID);
		return this.pageObject.isGisUseAllPipesButtonVisible();
	}
 
	/**
	 * Executes verifyGisBoundaryBigBoundaryButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisBoundaryBigBoundaryButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisBoundaryBigBoundaryButtonIsVisible", data, dataRowID);
		return this.pageObject.isGisBoundaryBigBoundaryButtonVisible();
	}
 
	/**
	 * Executes verifyGisBoundarySmallBoundaryButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisBoundarySmallBoundaryButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisBoundarySmallBoundaryButtonIsVisible", data, dataRowID);
		return this.pageObject.isGisBoundarySmallBoundaryButtonVisible();
	}
 
	/**
	 * Executes verifyGisUseAllBoundariesButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisUseAllBoundariesButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisUseAllBoundariesButtonIsVisible", data, dataRowID);
		return this.pageObject.isGisUseAllBoundariesButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitch8HourHistoryButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitch8HourHistoryButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitch8HourHistoryButtonIsNotVisible", data, dataRowID);
		return !this.pageObject.isDisplaySwitch8HourHistoryButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchWindroseButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchWindroseButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchWindroseButtonIsNotVisible", data, dataRowID);
		return !this.pageObject.isDisplaySwitchWindroseButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchConcentrationChartButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchConcentrationChartButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchConcentrationChartButtonIsNotVisible", data, dataRowID);
		return !this.pageObject.isDisplaySwitchConcentrationChartButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchNotesButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchNotesButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchNotesButtonIsNotVisible", data, dataRowID);
		return !this.pageObject.isDisplaySwitchNotesButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchIsotopicAnalysisButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchIsotopicAnalysisButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchIsotopicAnalysisButtonIsNotVisible", data, dataRowID);
		return !this.pageObject.isDisplaySwitchIsotopicAnalysisButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchIndicationsButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchIndicationsButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchIndicationsButtonIsNotVisible", data, dataRowID);
		return !this.pageObject.isDisplaySwitchIndicationsButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchLisasButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchLisasButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchLisasButtonIsNotVisible", data, dataRowID);
		return !this.pageObject.isDisplaySwitchLisasButtonVisible();
	}
 
	/**
	 * Executes verifyDisplaySwitchFovsButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchFovsButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchFovsButtonIsNotVisible", data, dataRowID);
		return !this.pageObject.isDisplaySwitchFovsButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypeCastIronButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeCastIronButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypeCastIronButtonIsNotVisible", data, dataRowID);
		return !this.pageObject.isGisMaterialTypeCastIronButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypeCopperButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeCopperButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypeCopperButtonIsNotVisible", data, dataRowID);
		return !this.pageObject.isGisMaterialTypeCopperButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypeOtherPlasticButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeOtherPlasticButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypeOtherPlasticButtonIsNotVisible", data, dataRowID);
		return !this.pageObject.isGisMaterialTypeOtherPlasticButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypePEPlasticButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypePEPlasticButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypePEPlasticButtonIsNotVisible", data, dataRowID);
		return !this.pageObject.isGisMaterialTypePEPlasticButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypeProtectedSteelButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeProtectedSteelButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypeProtectedSteelButtonIsNotVisible", data, dataRowID);
		return !this.pageObject.isGisMaterialTypeProtectedSteelButtonVisible();
	}
 
	/**
	 * Executes verifyGisMaterialTypeUnProtectedSteelButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeUnProtectedSteelButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypeUnProtectedSteelButtonIsNotVisible", data, dataRowID);
		return !this.pageObject.isGisMaterialTypeUnProtectedSteelButtonVisible();
	}
 
	/**
	 * Executes verifyGisUseAllPipesButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisUseAllPipesButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisUseAllPipesButtonIsNotVisible", data, dataRowID);
		return !this.pageObject.isGisUseAllPipesButtonVisible();
	}
 
	/**
	 * Executes verifyGisBoundaryBigBoundaryButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisBoundaryBigBoundaryButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisBoundaryBigBoundaryButtonIsNotVisible", data, dataRowID);
		return !this.pageObject.isGisBoundaryBigBoundaryButtonVisible();
	}
 
	/**
	 * Executes verifyGisBoundarySmallBoundaryButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisBoundarySmallBoundaryButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisBoundarySmallBoundaryButtonIsNotVisible", data, dataRowID);
		return !this.pageObject.isGisBoundarySmallBoundaryButtonVisible();
	}
 
	public boolean verifyFieldNotesIsShownOnMap(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifyFieldNotesIsShownOnMap", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASEMAP_VIEW_PAGE_ACTIONS + FN_VERIFY_FIELD_NOTES_IS_SHOWN_ON_MAP, ARG_DATA, data);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isFieldNoteShown(data);
	}

	public boolean verifyFieldNotesIsNotShownOnMap(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifyFieldNotesIsNotShownOnMap", data, dataRowID);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return !mapUtility.isFieldNoteShown(data);
	}

	/**
	 * Executes verifyGisUseAllBoundariesButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisUseAllBoundariesButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisUseAllBoundariesButtonIsNotVisible", data, dataRowID);
		return !this.pageObject.isGisUseAllBoundariesButtonVisible();
	}
 
	public BaseMapViewPage getPageObject() {
		return pageObject;
	}

	public void setPageObject(BaseMapViewPage BaseMapViewPage) {
		this.pageObject = BaseMapViewPage;
	}

	protected String getRuntimeType() {
		if (this.getClass() == DriverViewPageActions.class) { 
			return "DriverViewPageActions";
		} else if (this.getClass() == ObserverViewPageActions.class) { 
			return "ObserverViewPageActions";
		} else if (this.getClass() == SurveyViewPageActions.class) { 
			return "SurveyViewPageActions";
		}
		return "";
	}
	
	protected boolean verifySurveyInfoTagLabelEquals(String data, Integer dataRowID, DriverViewDataRow workingDataRow, String actualTagValue) throws Exception {
		String expectedTagValue = null;
		if (!ActionArguments.isEmpty(data)) {
			expectedTagValue = "Tag: " + data;
		} else if (dataRowID > 0) {
			expectedTagValue = "Tag: " + workingDataRow.surveyTag;
		} else {
			throw new Exception(String.format("Either data or dataRowID must be passed for %s action.", FN_VERIFY_SURVEY_INFO_TAG_LABEL_EQUALS));
		}
		
		log(String.format("Looking for Text-[%s], Found Survey Tag Label Text-[%s]", expectedTagValue, actualTagValue));
		return actualTagValue.equals(expectedTagValue);
	}
}