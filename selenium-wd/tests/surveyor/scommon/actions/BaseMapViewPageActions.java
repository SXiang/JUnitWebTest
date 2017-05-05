package surveyor.scommon.actions;

import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.Log;
import common.source.OLMapEntities.Indication;
import common.source.OLMapUtility;
import common.source.TestContext;
import common.source.OLMapUtility.IconColor;
import common.source.TestSetup;
import common.source.WebElementExtender;
import surveyor.scommon.source.BaseMapViewPage.DisplaySwitchType;
import surveyor.scommon.source.BaseMapViewPage.GisSwitchType;
import surveyor.scommon.source.BaseMapViewPage.MapSwitchType;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.actions.data.DriverViewDataReader.DriverViewDataRow;
import surveyor.scommon.source.BaseMapViewPage;

public class BaseMapViewPageActions extends BasePageActions {
	private static final int DEFAULT_ZOOM_LEVEL = 19;

	private static final Float ANALYTICS_SURVEY_SPAN_CSS_MAXLEFT_PERC_VALUE = 1.01f;
	private static final Float ANALYTICS_SURVEY_SPAN_CSS_MAXTOP_PERC_VALUE = 10.01f;
	private static final String ANALYTICS_SURVEY_SPAN_CSS_COLOR_VALUE = "rgb(0, 128, 0)";

	private static final String FN_VERIFY_MAP_ZOOM_LEVEL_IS_CORRECT = "verifyMapShownForZoomLevelIsCorrect";
	private static final String FN_VERIFY_FIELD_NOTES_IS_SHOWN_ON_MAP = "verifyFieldNotesIsShownOnMap";
	private static final String FN_VERIFY_FIELD_NOTES_IS_NOT_SHOWN_ON_MAP = "verifyFieldNotesIsNotShownOnMap";
	private static final String FN_VERIFY_ISOTOPIC_CAPTURE_RESULT_IS_PRESENT_ON_MAP = "verifyIsotopicCaptureResultIsPresentOnMap";
	private static final String FN_VERIFY_ISOTOPIC_CAPTURE_RESULT_IS_NOT_PRESENT_ON_MAP = "verifyIsotopicCaptureResultIsNotPresentOnMap";
	private static final String FN_VERIFY_REF_GAS_CAPTURE_RESULT_IS_PRESENT_ON_MAP = "verifyRefGasCaptureResultIsPresentOnMap";
	private static final String FN_VERIFY_REF_GAS_CAPTURE_RESULT_IS_NOT_PRESENT_ON_MAP = "verifyRefGasCaptureResultIsNotPresentOnMap";
	private static final String FN_VERIFY_GIS_SWITCH_IS_OFF = "verifyGisSwitchIsOff";
	private static final String FN_VERIFY_DISPLAY_SWITCH_IS_OFF = "verifyDisplaySwitchIsOff";
	private static final String FN_VERIFY_MAP_SWITCH_OFF = "verifyMapSwitchOff";
	private static final String FN_VERIFY_MAP_SWITCH_ON = "verifyMapSwitchOn";
	private static final String FN_VERIFY_GIS_SWITCH_IS_ON = "verifyGisSwitchIsOn";
	private static final String FN_VERIFY_DISPLAY_SWITCH_IS_ON = "verifyDisplaySwitchIsOn";
	private static final String FN_VERIFY_CROSS_HAIR_ICON_IS_SHOWN_ON_MAP = "verifyCrossHairIconIsShownOnMap";
	private static final String FN_VERIFY_SURVEY_INFO_TAG_LABEL_EQUALS = "verifySurveyInfoTagLabelEquals";

	private static final String CLS_BASEMAP_VIEW_PAGE_ACTIONS = "BaseMapViewPageActions::";
	private static final int TIMEOUT_LOAD_LAYER = 10;

	public BaseMapViewPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL);
	}

	public boolean clickOnCurtainArrowDownButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnCurtainArrowDownButton", data, dataRowID);
		getBaseMapViewPageObject().clickCurtainArrowDownButton();
		return true;
	}

	public boolean clickOnCurtainArrowLeftButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnCurtainArrowLeftButton", data, dataRowID);
		getBaseMapViewPageObject().clickCurtainArrowLeftButton();
		return true;
	}

	public boolean clickOnCurtainArrowRightButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnCurtainArrowRightButton", data, dataRowID);
		getBaseMapViewPageObject().clickCurtainArrowRightButton();
		return true;
	}

	public boolean clickOnCurtainArrowUpButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnCurtainArrowUpButton", data, dataRowID);
		getBaseMapViewPageObject().clickCurtainArrowUpButton();
		return true;
	}

	public boolean clickOnCurtainReturnButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnCurtainReturnButton", data, dataRowID);
		getBaseMapViewPageObject().clickCurtainReturnButton();
		return true;
	}

	public boolean clickOnCurtainZoomInButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnCurtainZoomInButton", data, dataRowID);
		getBaseMapViewPageObject().clickCurtainZoomInButton();
		return true;
	}

	public boolean clickOnCurtainZoomOutButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnCurtainZoomOutButton", data, dataRowID);
		getBaseMapViewPageObject().clickCurtainZoomOutButton();
		return true;
	}

	public boolean clickOnDisplayButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnDisplayButton", data, dataRowID);
		getBaseMapViewPageObject().clickDisplayButton();
		TestContext.INSTANCE.stayIdle(3);
		return true;
	}

	public boolean clickOnGisButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnGisButton", data, dataRowID);
		getBaseMapViewPageObject().clickGisButton();
		TestContext.INSTANCE.stayIdle(3);
		return true;
	}

	public boolean clickOnMapButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnMapButton", data, dataRowID);
		getBaseMapViewPageObject().clickMapButton();
		TestContext.INSTANCE.stayIdle(3);
		return true;
	}

	public boolean clickOnPicarroLogoButton(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnPicarroLogoButton", data, dataRowID);
		this.getBaseMapViewPageObject().clickPicarroLogoButton();
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
		getBaseMapViewPageObject().clickZoomInButton();
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
		getBaseMapViewPageObject().clickZoomOutButton();
		return true;
	}

	/**
	 * Executes clickOnFirst3300IndicationShownOnMap action.
	 * @param data - specifies type of the indication (For eg.'NaturalGas','NotNaturalGas','PossibleNaturalGas','VehicleExhaust').
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean clickOnFirst3300IndicationShownOnMap(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".clickOnFirstIndicationShownOnMap", data, dataRowID);
		String mapCanvasXPath = "//*[@id='map']/div/canvas";
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		boolean retVal = mapUtility.clickFirst3300IndicationOnMap(mapCanvasXPath, data);
		return retVal;
	}

	public boolean waitForFeatureInfoPopupToOpen(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".waitForFeatureInfoPopupToOpen", data, dataRowID);
		getBaseMapViewPageObject().waitForFeatureInfoPopupToOpen();
		return true;
	}

	public boolean waitForFeatureInfoPopupToClose(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".waitForFeatureInfoPopupToClose", data, dataRowID);
		getBaseMapViewPageObject().waitForFeatureInfoPopupToClose();
		return true;
	}

	public boolean showCurtainView(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".showCurtainView", data, dataRowID);
		getBaseMapViewPageObject().clickCurtainButton();
		return true;
	}

	public boolean open(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".open", data, dataRowID);
		getBaseMapViewPageObject().open();
		return true;
	}

	public boolean turnOnMapView(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnMapView", data, dataRowID);
		getBaseMapViewPageObject().toggleMapSwitch(MapSwitchType.Map, true);
		return true;
	}
	public boolean turnOnSatelliteView(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnSatelliteView", data, dataRowID);
		getBaseMapViewPageObject().toggleMapSwitch(MapSwitchType.Satellite, true);
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
		return getBaseMapViewPageObject().isMapSwitchOn(switchType);
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
	 * @throws Exception
	 * @throws IOException
	 */
	public boolean turnOnAllAssets(String data, Integer dataRowID) throws IOException, Exception {
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
	 * @throws Exception
	 * @throws IOException
	 */
	public boolean turnOnAllBoundaries(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOnAllBoundaries", data, dataRowID);
		turnOnBigBoundary(data, dataRowID);
		turnOnSmallBoundary(data, dataRowID);
		return true;
	}

	/**
	 * Executes turnOnAllAssetsAndBoundaries action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 * @throws IOException
	 */
	public boolean turnOnAllAssetsAndBoundaries(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOnAllAssetsAndBoundaries", data, dataRowID);
		turnOnAllAssets(data, dataRowID);
		turnOnAllBoundaries(data, dataRowID);
		return true;
	}

	public boolean turnOnBigBoundary(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOnBoundariesDistrict", data, dataRowID);
		getBaseMapViewPageObject().toggleGisSwitch(GisSwitchType.BigBoundary, true);
		return true;
	}
	public boolean turnOnSmallBoundary(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOnBoundariesDistrictPlat", data, dataRowID);
		getBaseMapViewPageObject().toggleGisSwitch(GisSwitchType.SmallBoundary, true);
		return true;
	}
	public boolean turnOnMaterialTypeCopper(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOnMaterialTypeCopper", data, dataRowID);
		getBaseMapViewPageObject().toggleGisSwitch(GisSwitchType.MaterialTypeCopper, true);
		return true;
	}
	public boolean turnOnMaterialTypeCastIron(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOnMaterialTypeCastIron", data, dataRowID);
		getBaseMapViewPageObject().toggleGisSwitch(GisSwitchType.MaterialTypeCastIron, true);
		return true;
	}
	public boolean turnOnMaterialTypeOtherPlastic(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOnMaterialTypeOtherPlastic", data, dataRowID);
		getBaseMapViewPageObject().toggleGisSwitch(GisSwitchType.MaterialTypeOtherPlastic, true);
		return true;
	}
	public boolean turnOnMaterialTypePEPlastic(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOnMaterialTypePEPlastic", data, dataRowID);
		getBaseMapViewPageObject().toggleGisSwitch(GisSwitchType.MaterialTypePEPlastic, true);
		return true;
	}
	public boolean turnOnMaterialTypeProtectedSteel(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOnMaterialTypeProtectedSteel", data, dataRowID);
		getBaseMapViewPageObject().toggleGisSwitch(GisSwitchType.MaterialTypeProtectedSteel, true);
		return true;
	}
	public boolean turnOnMaterialTypeUnprotectedSteel(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOnMaterialTypeUnprotectedSteel", data, dataRowID);
		getBaseMapViewPageObject().toggleGisSwitch(GisSwitchType.MaterialTypeUnprotectedSteel, true);
		return true;
	}
	public boolean turnOnUseAllBoundaries(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOnUseAllBoundaries", data, dataRowID);
		getBaseMapViewPageObject().toggleGisSwitch(GisSwitchType.UseAllBoundaries, true);
		return true;
	}
	public boolean turnOnUseAllPipes(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOnUseAllPipes", data, dataRowID);
		getBaseMapViewPageObject().toggleGisSwitch(GisSwitchType.UseAllPipes, true);
		return true;
	}
	public boolean turnOffBigBoundary(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOffBoundariesDistrict", data, dataRowID);
		getBaseMapViewPageObject().toggleGisSwitch(GisSwitchType.BigBoundary, false);
		return true;
	}
	public boolean turnOffSmallBoundary(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOffBoundariesDistrictPlat", data, dataRowID);
		getBaseMapViewPageObject().toggleGisSwitch(GisSwitchType.SmallBoundary, false);
		return true;
	}
	public boolean turnOffMaterialTypeCopper(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOffMaterialTypeCopper", data, dataRowID);
		getBaseMapViewPageObject().toggleGisSwitch(GisSwitchType.MaterialTypeCopper, false);
		return true;
	}
	public boolean turnOffMaterialTypeCastIron(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOffMaterialTypeCastIron", data, dataRowID);
		getBaseMapViewPageObject().toggleGisSwitch(GisSwitchType.MaterialTypeCastIron, false);
		return true;
	}
	public boolean turnOffMaterialTypeOtherPlastic(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOffMaterialTypeOtherPlastic", data, dataRowID);
		getBaseMapViewPageObject().toggleGisSwitch(GisSwitchType.MaterialTypeOtherPlastic, false);
		return true;
	}
	public boolean turnOffMaterialTypePEPlastic(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOffMaterialTypePEPlastic", data, dataRowID);
		getBaseMapViewPageObject().toggleGisSwitch(GisSwitchType.MaterialTypePEPlastic, false);
		return true;
	}
	public boolean turnOffMaterialTypeProtectedSteel(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOffMaterialTypeProtectedSteel", data, dataRowID);
		getBaseMapViewPageObject().toggleGisSwitch(GisSwitchType.MaterialTypeProtectedSteel, false);
		return true;
	}
	public boolean turnOffMaterialTypeUnprotectedSteel(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOffMaterialTypeUnprotectedSteel", data, dataRowID);
		getBaseMapViewPageObject().toggleGisSwitch(GisSwitchType.MaterialTypeUnprotectedSteel, false);
		return true;
	}
	public boolean turnOffUseAllBoundaries(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOffUseAllBoundaries", data, dataRowID);
		getBaseMapViewPageObject().toggleGisSwitch(GisSwitchType.UseAllBoundaries, false);
		return true;
	}
	public boolean turnOffUseAllPipes(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOffUseAllPipes", data, dataRowID);
		getBaseMapViewPageObject().toggleGisSwitch(GisSwitchType.UseAllPipes, false);
		return true;
	}

	/**
	 * Executes turnOffAllAssets action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 * @throws IOException
	 */
	public boolean turnOffAllAssets(String data, Integer dataRowID) throws IOException, Exception {
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
	 * @throws Exception
	 * @throws IOException
	 */
	public boolean turnOffAllBoundaries(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOffAllBoundaries", data, dataRowID);
		turnOffBigBoundary(data, dataRowID);
		turnOffSmallBoundary(data, dataRowID);
		return true;
	}

	/**
	 * Executes turnOffAllAssetsAndBoundaries action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 * @throws Exception
	 * @throws IOException
	 */
	public boolean turnOffAllAssetsAndBoundaries(String data, Integer dataRowID) throws IOException, Exception {
		logAction(getRuntimeType() + ".turnOffAllAssetsAndBoundaries", data, dataRowID);
		turnOffAllAssets(data, dataRowID);
		turnOffAllBoundaries(data, dataRowID);
		return true;
	}

	/* Display Switch (Enable/Disable) methods */

	public boolean turnOnAllDisplayOptions(String data, Integer dataRowID) {
		String runtimeType = getRuntimeType();
		logAction(getRuntimeType() + ".turnOnAllDisplayOptions", data, dataRowID);
		if (!runtimeType.equals("ObserverViewPageActions") && !runtimeType.equals("SurveyViewPageActions")) {
			turnOnEightHourHistory(data, dataRowID);
		}
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
		getBaseMapViewPageObject().toggleDisplaySwitch(DisplaySwitchType.EightHourHistory, true);
		return true;
	}
	public boolean turnOnConcentrationChart(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnConcentrationChart", data, dataRowID);
		getBaseMapViewPageObject().toggleDisplaySwitch(DisplaySwitchType.ConcentrationChart, true);
		return true;
	}
	public boolean turnOnFOVs(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnFOVs", data, dataRowID);
		getBaseMapViewPageObject().toggleDisplaySwitch(DisplaySwitchType.FOVs, true);
		return true;
	}
	public boolean turnOnIndications(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnIndications", data, dataRowID);
		getBaseMapViewPageObject().toggleDisplaySwitch(DisplaySwitchType.Indications, true);
		return true;
	}
	public boolean turnOnPossibleNaturalGas(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnPossibleNaturalGas", data, dataRowID);
		getBaseMapViewPageObject().toggleDisplaySwitch(DisplaySwitchType.PossibleNaturalGas, true);
		return true;
	}
	public boolean turnOnNotNaturalGas(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnNotNaturalGas", data, dataRowID);
		getBaseMapViewPageObject().toggleDisplaySwitch(DisplaySwitchType.NotNaturalGas, true);
		return true;
	}
	public boolean turnOnVehicleExhaust(String data, Integer dataRowID) {
		logAction("DriverViewPageActions.turnOnVehicleExhaust", data, dataRowID);
		getBaseMapViewPageObject().toggleDisplaySwitch(DisplaySwitchType.VehicleExhaust, true);
		return true;
	}
	public boolean turnOnIsotopicAnalysis(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnIsotopicAnalysis", data, dataRowID);
		getBaseMapViewPageObject().toggleDisplaySwitch(DisplaySwitchType.IsotopicAnalysis, true);
		return true;
	}
	public boolean turnOnLisas(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnLisas", data, dataRowID);
		getBaseMapViewPageObject().toggleDisplaySwitch(DisplaySwitchType.Lisas, true);
		return true;
	}
	public boolean turnOnNotes(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnNotes", data, dataRowID);
		getBaseMapViewPageObject().toggleDisplaySwitch(DisplaySwitchType.Notes, true);
		return true;
	}
	public boolean turnOnWindRose(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOnWindRose", data, dataRowID);
		getBaseMapViewPageObject().toggleDisplaySwitch(DisplaySwitchType.WindRose, true);
		return true;
	}
	public boolean turnOffAllDisplayOptions(String data, Integer dataRowID) {
		String runtimeType = getRuntimeType();
		logAction(runtimeType + ".turnOffAllDisplayOptions", data, dataRowID);
		if (!runtimeType.equals("ObserverViewPageActions") && !runtimeType.equals("SurveyViewPageActions")) {
			turnOffEightHourHistory(data, dataRowID);
		}
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
		try {
			// Intentional delay included in this action.
			// Observed Behavior: Not including a delay before this action intermittently causes some elements to not appear on the map.
			new TestEnvironmentActions().idleForSeconds("2", NOTSET);
		} catch (Exception e) {
			e.printStackTrace();
		}
		getBaseMapViewPageObject().toggleDisplaySwitch(DisplaySwitchType.EightHourHistory, false);
		return true;
	}
	public boolean turnOffConcentrationChart(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffConcentrationChart", data, dataRowID);
		getBaseMapViewPageObject().toggleDisplaySwitch(DisplaySwitchType.ConcentrationChart, false);
		return true;
	}
	public boolean turnOffFOVs(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffFOVs", data, dataRowID);
		getBaseMapViewPageObject().toggleDisplaySwitch(DisplaySwitchType.FOVs, false);
		return true;
	}
	public boolean turnOffIndications(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffIndications", data, dataRowID);
		getBaseMapViewPageObject().toggleDisplaySwitch(DisplaySwitchType.Indications, false);
		return true;
	}
	public boolean turnOffPossibleNaturalGas(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffPossibleNaturalGas", data, dataRowID);
		getBaseMapViewPageObject().toggleDisplaySwitch(DisplaySwitchType.PossibleNaturalGas, false);
		return true;
	}
	public boolean turnOffNotNaturalGas(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffNotNaturalGas", data, dataRowID);
		getBaseMapViewPageObject().toggleDisplaySwitch(DisplaySwitchType.NotNaturalGas, false);
		return true;
	}
	public boolean turnOffVehicleExhaust(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffVehicleExhaust", data, dataRowID);
		getBaseMapViewPageObject().toggleDisplaySwitch(DisplaySwitchType.VehicleExhaust, false);
		return true;
	}
	public boolean turnOffIsotopicAnalysis(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffIsotopicAnalysis", data, dataRowID);
		getBaseMapViewPageObject().toggleDisplaySwitch(DisplaySwitchType.IsotopicAnalysis, false);
		return true;
	}
	public boolean turnOffLisas(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffLisas", data, dataRowID);
		getBaseMapViewPageObject().toggleDisplaySwitch(DisplaySwitchType.Lisas, false);
		return true;
	}
	public boolean turnOffNotes(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffNotes", data, dataRowID);
		getBaseMapViewPageObject().toggleDisplaySwitch(DisplaySwitchType.Notes, false);
		return true;
	}
	public boolean turnOffWindRose(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".turnOffWindRose", data, dataRowID);
		getBaseMapViewPageObject().toggleDisplaySwitch(DisplaySwitchType.WindRose, false);
		return true;
	}

	/* Verify Analytics methods */
	/**
	 * Executes verifyAnalyticsModeDialogIsShown action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyAnalyticsModeDialogIsShown(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyAnalyticsModeDialogIsShown", data, dataRowID);
		return getBaseMapViewPageObject().isAnalyticsModeDialogShown();
	}

	/**
	 * Executes verifyAnalyticsModeDialogIsNotShown action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyAnalyticsModeDialogIsNotShown(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyAnalyticsModeDialogIsNotShown", data, dataRowID);
		return getBaseMapViewPageObject().isAnalyticsModeDialogHidden();
	}

	/**
	 * Verifies Analytics Survey active dialog is shown on the map with correct message and CSS formatting.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyCorrectAnalyticsSurveyActiveMessageIsShownOnMap(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyCorrectAnalyticsSurveyActiveMessageIsShownOnMap", data, dataRowID);
		getBaseMapViewPageObject().waitForAnalyticsDialogToBeDisplayed();
		boolean dialogShown = getBaseMapViewPageObject().isAnalyticsModeDialogShown();
		WebElement analyticsModeDialog = getBaseMapViewPageObject().getAnalyticsModeDialog();
		WebElement mapElement = getBaseMapViewPageObject().getMapElement();
		String spanText = analyticsModeDialog.getText();
		String expectedSpanText = Resources.getResource(ResourceKeys.Dialog_AnalyticsModeActive);

		String leftCssValue = WebElementExtender.getCssPropertyValue(getDriver(), analyticsModeDialog, "left").replace("px", "");
		String topCssValue = WebElementExtender.getCssPropertyValue(getDriver(), analyticsModeDialog, "top").replace("px", "");
		String mapCssWidthValue = WebElementExtender.getCssPropertyValue(getDriver(), mapElement, "width").replace("px", "");
		String mapCssHeightValue = WebElementExtender.getCssPropertyValue(getDriver(), mapElement, "height").replace("px", "");
		String colorCssValue = WebElementExtender.getCssPropertyValue(getDriver(), analyticsModeDialog, "color");

		Log.info(String.format("mapCssWidthValue=[%s], mapCssHeightValue=[%s], leftCssValue=[%s], topCssValue=[%s]",
				mapCssWidthValue, mapCssHeightValue, leftCssValue, topCssValue));

		Float fMapWidth = Float.parseFloat(mapCssWidthValue);
		Float fMapHeight = Float.parseFloat(mapCssHeightValue);
		Float leftPerc = 0.0f;
		Float topPerc = 0.0f;

		if (leftCssValue.contains("%")) {
			leftPerc = Float.parseFloat(leftCssValue.replace("%", "").trim());
		} else {
			Float fLeft = Float.parseFloat(leftCssValue);
			leftPerc = (fLeft * 100.0f) / fMapWidth;
		}

		if (topCssValue.contains("%")) {
			topPerc = Float.parseFloat(topCssValue.replace("%", "").trim());
		} else {
			Float fTop = Float.parseFloat(topCssValue);
			topPerc = (fTop * 100.0f) / fMapHeight;
		}

		Log.info(String.format("Analytics Survey Active dialog shown = [%b]", dialogShown));
		Log.info(String.format("Survey Active Text Check = [%b]; Actual Span Text = [%s]; Expected Span Text = [%s]", spanText.equals(expectedSpanText), spanText, expectedSpanText));
		Log.info(String.format("CSS Left Formatting Check = [%b]; Left Percent=%f", (leftPerc < ANALYTICS_SURVEY_SPAN_CSS_MAXLEFT_PERC_VALUE), leftPerc));
		Log.info(String.format("CSS Top Formatting Check = [%b]; Top Percent=%f", (topPerc < ANALYTICS_SURVEY_SPAN_CSS_MAXTOP_PERC_VALUE), topPerc));
		Log.info(String.format("CSS Color Check = [%b]; Color=%s", colorCssValue.equals(ANALYTICS_SURVEY_SPAN_CSS_COLOR_VALUE), colorCssValue));
		return dialogShown && spanText.equals(expectedSpanText) && (leftPerc < ANALYTICS_SURVEY_SPAN_CSS_MAXLEFT_PERC_VALUE) &&
				(topPerc < ANALYTICS_SURVEY_SPAN_CSS_MAXTOP_PERC_VALUE) && colorCssValue.equals(ANALYTICS_SURVEY_SPAN_CSS_COLOR_VALUE);
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
		return getBaseMapViewPageObject().isDisplaySwitchOn(switchType);
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
		if (data.equalsIgnoreCase("BigBoundary")) {
			switchType = GisSwitchType.BigBoundary;
		} else if (data.equalsIgnoreCase("SmallBoundary")) {
			switchType = GisSwitchType.SmallBoundary;
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
		return getBaseMapViewPageObject().isGisSwitchOn(switchType);
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
		boolean isAssetShown = false;
		try {
			isAssetShown =	(new WebDriverWait(getDriver(),TIMEOUT_LOAD_LAYER)).until(new ExpectedCondition<Boolean>(){
			public Boolean apply(WebDriver d){
				return mapUtility.isAssetShownOnMap();
			}
		 });
		}catch(Exception e){
			isAssetShown = false;
		}
		return isAssetShown;
	}
	public boolean verifyBoundariesIsShownOnMap(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyBoundariesIsShownOnMap", data, dataRowID);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isBoundariesShownOnMap();
	}

	public boolean verifySurveyAmplitudes(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifySurveyAmplitudes", data, dataRowID);
		float minAmp = Float.parseFloat(data);
		Set<Indication> indications = getIndicationsShownOnPage();
		System.out.println("IndicationsFound: '"+indications.size()+"'");
		for(Indication indi:indications){
			float amp = Float.parseFloat(indi.amplitude);
			System.out.println("Amplitude on indication = '"+amp+"'");
			if(amp < minAmp){
				Log.warn("Amplitiude '"+amp+"' on map is less then expected: > '"+minAmp+"'");
				return false;
			}
		}
		return true;
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

	public boolean verifyAssetIsNotShownOnMap(String data, Integer dataRowID){
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
		return getBaseMapViewPageObject().isDisplaySwitch8HourHistoryButtonVisible();
	}

	/**
	 * Executes verifyDisplaySwitchWindroseButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchWindroseButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchWindroseButtonIsVisible", data, dataRowID);
		return getBaseMapViewPageObject().isDisplaySwitchWindroseButtonVisible();
	}

	/**
	 * Executes verifyDisplaySwitchConcentrationChartButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchConcentrationChartButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchConcentrationChartButtonIsVisible", data, dataRowID);
		return getBaseMapViewPageObject().isDisplaySwitchConcentrationChartButtonVisible();
	}

	/**
	 * Executes verifyDisplaySwitchNotesButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchNotesButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchNotesButtonIsVisible", data, dataRowID);
		return getBaseMapViewPageObject().isDisplaySwitchNotesButtonVisible();
	}

	/**
	 * Executes verifyDisplaySwitchIsotopicAnalysisButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchIsotopicAnalysisButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchIsotopicAnalysisButtonIsVisible", data, dataRowID);
		return getBaseMapViewPageObject().isDisplaySwitchIsotopicAnalysisButtonVisible();
	}

	/**
	 * Executes verifyDisplaySwitchIndicationsButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchIndicationsButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchIndicationsButtonIsVisible", data, dataRowID);
		return getBaseMapViewPageObject().isDisplaySwitchIndicationsButtonVisible();
	}

	/**
	 * Executes verifyDisplaySwitchLisasButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchLisasButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchLisasButtonIsVisible", data, dataRowID);
		return getBaseMapViewPageObject().isDisplaySwitchLisasButtonVisible();
	}

	/**
	 * Executes verifyDisplaySwitchFovsButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchFovsButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchFovsButtonIsVisible", data, dataRowID);
		return getBaseMapViewPageObject().isDisplaySwitchFovsButtonVisible();
	}

	/**
	 * Executes verifyGisMaterialTypeCastIronButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeCastIronButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypeCastIronButtonIsVisible", data, dataRowID);
		return getBaseMapViewPageObject().isGisMaterialTypeCastIronButtonVisible();
	}

	/**
	 * Executes verifyGisMaterialTypeCopperButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeCopperButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypeCopperButtonIsVisible", data, dataRowID);
		return getBaseMapViewPageObject().isGisMaterialTypeCopperButtonVisible();
	}

	/**
	 * Executes verifyGisMaterialTypeOtherPlasticButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeOtherPlasticButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypeOtherPlasticButtonIsVisible", data, dataRowID);
		return getBaseMapViewPageObject().isGisMaterialTypeOtherPlasticButtonVisible();
	}

	/**
	 * Executes verifyGisMaterialTypePEPlasticButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypePEPlasticButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypePEPlasticButtonIsVisible", data, dataRowID);
		return getBaseMapViewPageObject().isGisMaterialTypePEPlasticButtonVisible();
	}

	/**
	 * Executes verifyGisMaterialTypeProtectedSteelButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeProtectedSteelButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypeProtectedSteelButtonIsVisible", data, dataRowID);
		return getBaseMapViewPageObject().isGisMaterialTypeProtectedSteelButtonVisible();
	}

	/**
	 * Executes verifyGisMaterialTypeUnProtectedSteelButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeUnProtectedSteelButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypeUnProtectedSteelButtonIsVisible", data, dataRowID);
		return getBaseMapViewPageObject().isGisMaterialTypeUnProtectedSteelButtonVisible();
	}

	/**
	 * Executes verifyGisUseAllPipesButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisUseAllPipesButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisUseAllPipesButtonIsVisible", data, dataRowID);
		return getBaseMapViewPageObject().isGisUseAllPipesButtonVisible();
	}

	/**
	 * Executes verifyGisBoundaryBigBoundaryButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisBoundaryBigBoundaryButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisBoundaryBigBoundaryButtonIsVisible", data, dataRowID);
		return getBaseMapViewPageObject().isGisBoundaryBigBoundaryButtonVisible();
	}

	/**
	 * Executes verifyGisBoundarySmallBoundaryButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisBoundarySmallBoundaryButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisBoundarySmallBoundaryButtonIsVisible", data, dataRowID);
		return getBaseMapViewPageObject().isGisBoundarySmallBoundaryButtonVisible();
	}

	/**
	 * Executes verifyGisUseAllBoundariesButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisUseAllBoundariesButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisUseAllBoundariesButtonIsVisible", data, dataRowID);
		return getBaseMapViewPageObject().isGisUseAllBoundariesButtonVisible();
	}

	/**
	 * Executes verifyDisplaySwitch8HourHistoryButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitch8HourHistoryButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitch8HourHistoryButtonIsNotVisible", data, dataRowID);
		return !getBaseMapViewPageObject().isDisplaySwitch8HourHistoryButtonVisible();
	}

	/**
	 * Executes verifyDisplaySwitchWindroseButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchWindroseButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchWindroseButtonIsNotVisible", data, dataRowID);
		return !getBaseMapViewPageObject().isDisplaySwitchWindroseButtonVisible();
	}

	/**
	 * Executes verifyDisplaySwitchConcentrationChartButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchConcentrationChartButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchConcentrationChartButtonIsNotVisible", data, dataRowID);
		return !getBaseMapViewPageObject().isDisplaySwitchConcentrationChartButtonVisible();
	}

	/**
	 * Executes verifyDisplaySwitchNotesButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchNotesButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchNotesButtonIsNotVisible", data, dataRowID);
		return !getBaseMapViewPageObject().isDisplaySwitchNotesButtonVisible();
	}

	/**
	 * Executes verifyDisplaySwitchIsotopicAnalysisButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchIsotopicAnalysisButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchIsotopicAnalysisButtonIsNotVisible", data, dataRowID);
		return !getBaseMapViewPageObject().isDisplaySwitchIsotopicAnalysisButtonVisible();
	}

	/**
	 * Executes verifyDisplaySwitchIndicationsButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchIndicationsButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchIndicationsButtonIsNotVisible", data, dataRowID);
		return !getBaseMapViewPageObject().isDisplaySwitchIndicationsButtonVisible();
	}

	/**
	 * Executes verifyDisplaySwitchLisasButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchLisasButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchLisasButtonIsNotVisible", data, dataRowID);
		return !getBaseMapViewPageObject().isDisplaySwitchLisasButtonVisible();
	}

	/**
	 * Executes verifyDisplaySwitchFovsButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyDisplaySwitchFovsButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyDisplaySwitchFovsButtonIsNotVisible", data, dataRowID);
		return !getBaseMapViewPageObject().isDisplaySwitchFovsButtonVisible();
	}

	/**
	 * Executes verifyGisMaterialTypeCastIronButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeCastIronButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypeCastIronButtonIsNotVisible", data, dataRowID);
		return !getBaseMapViewPageObject().isGisMaterialTypeCastIronButtonVisible();
	}

	/**
	 * Executes verifyGisMaterialTypeCopperButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeCopperButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypeCopperButtonIsNotVisible", data, dataRowID);
		return !getBaseMapViewPageObject().isGisMaterialTypeCopperButtonVisible();
	}

	/**
	 * Executes verifyGisMaterialTypeOtherPlasticButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeOtherPlasticButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypeOtherPlasticButtonIsNotVisible", data, dataRowID);
		return !getBaseMapViewPageObject().isGisMaterialTypeOtherPlasticButtonVisible();
	}

	/**
	 * Executes verifyGisMaterialTypePEPlasticButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypePEPlasticButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypePEPlasticButtonIsNotVisible", data, dataRowID);
		return !getBaseMapViewPageObject().isGisMaterialTypePEPlasticButtonVisible();
	}

	/**
	 * Executes verifyGisMaterialTypeProtectedSteelButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeProtectedSteelButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypeProtectedSteelButtonIsNotVisible", data, dataRowID);
		return !getBaseMapViewPageObject().isGisMaterialTypeProtectedSteelButtonVisible();
	}

	/**
	 * Executes verifyGisMaterialTypeUnProtectedSteelButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisMaterialTypeUnProtectedSteelButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisMaterialTypeUnProtectedSteelButtonIsNotVisible", data, dataRowID);
		return !getBaseMapViewPageObject().isGisMaterialTypeUnProtectedSteelButtonVisible();
	}

	/**
	 * Executes verifyGisUseAllPipesButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisUseAllPipesButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisUseAllPipesButtonIsNotVisible", data, dataRowID);
		return !getBaseMapViewPageObject().isGisUseAllPipesButtonVisible();
	}

	/**
	 * Executes verifyGisBoundaryBigBoundaryButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisBoundaryBigBoundaryButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisBoundaryBigBoundaryButtonIsNotVisible", data, dataRowID);
		return !getBaseMapViewPageObject().isGisBoundaryBigBoundaryButtonVisible();
	}

	/**
	 * Executes verifyGisBoundarySmallBoundaryButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisBoundarySmallBoundaryButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisBoundarySmallBoundaryButtonIsNotVisible", data, dataRowID);
		return !getBaseMapViewPageObject().isGisBoundarySmallBoundaryButtonVisible();
	}

	/**
	 * Executes verifyStatusButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyStatusButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyStatusIsNotShownOnMap", data, dataRowID);
		return !getBaseMapViewPageObject().isStatusButtonVisible();
	}

	/**
	 * Executes verifyModeButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyModeButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyStatusIsNotShownOnMap", data, dataRowID);
		return !getBaseMapViewPageObject().isModeButtonVisible();
	}

	/**
	 * Executes verifyShutdownAnalyzerIsNotShownOnMap action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyShutdownAnalyzerIsNotShownOnMap(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyStopSurveyIsNotShownOnMap", data, dataRowID);
		return !getBaseMapViewPageObject().isShutdownAnalyzerButtonVisible();
	}
	/**
	 * Executes verifyFieldNotesIsShownOnMap action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyFieldNotesIsShownOnMap(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifyFieldNotesIsShownOnMap", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASEMAP_VIEW_PAGE_ACTIONS + FN_VERIFY_FIELD_NOTES_IS_SHOWN_ON_MAP, ARG_DATA, data);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isFieldNoteShown(data);
	}

	/**
	 * Executes verifyFeatureInfoPopupAddFieldNotesButtonIsVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyFeatureInfoPopupAddFieldNotesButtonIsVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + "..verifyFeatureInfoPopupAddFieldNotesButtonIsVisible", data, dataRowID);
		return this.getBaseMapViewPageObject().isAddUpdateNoteButtonVisible();
	}

	/**
	 * Executes verifyFeatureInfoPopupAddFieldNotesButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyFeatureInfoPopupAddFieldNotesButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + "..verifyFeatureInfoPopupAddFieldNotesButtonIsNotVisible", data, dataRowID);
		return !this.getBaseMapViewPageObject().isAddUpdateNoteButtonVisible();
	}

	/**
	 * Executes verifyFieldNotesIsNotShownOnMap action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyFieldNotesIsNotShownOnMap(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifyFieldNotesIsNotShownOnMap", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASEMAP_VIEW_PAGE_ACTIONS + FN_VERIFY_FIELD_NOTES_IS_NOT_SHOWN_ON_MAP, ARG_DATA, data);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return !mapUtility.isFieldNoteShown(data);
	}

	/**
	 * Executes verifyIsotopicCaptureResultIsPresentOnMap action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyIsotopicCaptureResultIsPresentOnMap(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifyIsotopicCaptureResultIsPresentOnMap", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASEMAP_VIEW_PAGE_ACTIONS + FN_VERIFY_ISOTOPIC_CAPTURE_RESULT_IS_PRESENT_ON_MAP, ARG_DATA, data);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isIsotopicCaptureResultPresent(data);
	}

	/**
	 * Executes verifyIsotopicCaptureResultIsNotPresentOnMap action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyIsotopicCaptureResultIsNotPresentOnMap(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifyIsotopicCaptureResultIsNotPresentOnMap", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASEMAP_VIEW_PAGE_ACTIONS + FN_VERIFY_ISOTOPIC_CAPTURE_RESULT_IS_NOT_PRESENT_ON_MAP, ARG_DATA, data);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return !mapUtility.isIsotopicCaptureResultPresent(data);
	}

	/**
	 * Executes verifyRefGasCaptureResultIsPresentOnMap action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyRefGasCaptureResultIsPresentOnMap(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifyRefGasCaptureResultIsPresentOnMap", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASEMAP_VIEW_PAGE_ACTIONS + FN_VERIFY_REF_GAS_CAPTURE_RESULT_IS_PRESENT_ON_MAP, ARG_DATA, data);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return mapUtility.isRefGasCaptureResultPresent(data);
	}

	/**
	 * Executes verifyRefGasCaptureResultIsNotPresentOnMap action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyRefGasCaptureResultIsNotPresentOnMap(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifyRefGasCaptureResultIsNotPresentOnMap", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASEMAP_VIEW_PAGE_ACTIONS + FN_VERIFY_REF_GAS_CAPTURE_RESULT_IS_NOT_PRESENT_ON_MAP, ARG_DATA, data);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		return !mapUtility.isRefGasCaptureResultPresent(data);
	}

	/**
	 * Executes verifyMapShownForZoomLevelIsCorrect action.
	 * @param data - specifies the zoom level on the map.
	 * 	 default zoom level is 19.
	 *   for zoom out specify values like 18,17,16, etc
	 *   for zoom in specify values like 20,21,22, etc
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyMapShownForZoomLevelIsCorrect(String data, Integer dataRowID) throws Exception {
		logAction(getRuntimeType() + ".verifyMapShownForZoomLevelIsCorrect", data, dataRowID);
		ActionArguments.verifyNotNullOrEmpty(CLS_BASEMAP_VIEW_PAGE_ACTIONS + FN_VERIFY_MAP_ZOOM_LEVEL_IS_CORRECT, ARG_DATA, data);
		OLMapUtility mapUtility = new OLMapUtility(this.getDriver());
		int zoomLevel = Integer.valueOf(data) - DEFAULT_ZOOM_LEVEL;
		return mapUtility.isMapResolutionCorrect(zoomLevel);
	}

	/**
	 * Executes verifyGisUseAllBoundariesButtonIsNotVisible action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifyGisUseAllBoundariesButtonIsNotVisible(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".verifyGisUseAllBoundariesButtonIsNotVisible", data, dataRowID);
		return !getBaseMapViewPageObject().isGisUseAllBoundariesButtonVisible();
	}

	/**
	 * Executes waitForIndicationsToBeShownOnMap action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean waitForIndicationsToBeShownOnMap(String data, Integer dataRowID) {
		logAction(getRuntimeType() + ".waitForIndicationsToBeShownOnMap", data, dataRowID);
		getBaseMapViewPageObject().waitForIndicationsToBeShownOnMap();
		return true;
	}

	public BaseMapViewPage getBaseMapViewPageObject() {
		return (BaseMapViewPage)getPageObject();
	}

	public Set<Indication> getIndicationsShownOnPage() {
		Log.method("getIndicationsShownOnPage");
		OLMapUtility mapUtility = new OLMapUtility(getDriver());
		Set<Indication> indicationsArray = mapUtility.getIndicationsArray();
		Log.info(String.format("Indications detected in Driver view = %d", indicationsArray.size()));
		indicationsArray.forEach(i -> Log.info(i.toString()));
		return indicationsArray;
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