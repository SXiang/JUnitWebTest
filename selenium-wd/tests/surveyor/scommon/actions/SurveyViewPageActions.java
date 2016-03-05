package surveyor.scommon.actions;

import org.openqa.selenium.WebDriver;

import common.source.TestSetup;
import surveyor.scommon.actions.data.DriverViewDataReader;
import surveyor.scommon.source.SurveyViewPage;

public class SurveyViewPageActions extends BaseMapViewPageActions {

	// Use the Driver view data reader as the input could be read from DriverViewTestData.
	private DriverViewDataReader dataReader = null;

	public SurveyViewPageActions(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup);
		setDataReader(new DriverViewDataReader(this.excelUtility));
	}

	/**
	 * Executes open action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean open(String data, Integer dataRowID) {
		logAction("SurveyViewPageActions.open", data, dataRowID);
		getSurveyViewPage().open();
		return true;
	}
 
	/**
	 * Executes verifySurveyViewPageIsOpened action.
	 * @param data - specifies the input data passed to the action.
	 * @param dataRowID - specifies the rowID in the test data sheet from where data for this action is to be read.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean verifySurveyViewPageIsOpened(String data, Integer dataRowID) {
		logAction("SurveyViewPageActions.verifySurveyViewPageIsOpened", data, dataRowID);
		return getSurveyViewPage().checkIfAtSurveyViewPage();
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
		else if (actionName.equals("clickOnMapButton")) { return this.clickOnMapButton(data, dataRowID); }
		else if (actionName.equals("clickOnPicarroLogoButton")) { return this.clickOnPicarroLogoButton(data, dataRowID); }
		else if (actionName.equals("clickOnZoomInButton")) { return this.clickOnZoomInButton(data, dataRowID); }
		else if (actionName.equals("clickOnZoomOutButton")) { return this.clickOnZoomOutButton(data, dataRowID); }
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
		else if (actionName.equals("turnOnSatelliteView")) { return this.turnOnSatelliteView(data, dataRowID); }
		else if (actionName.equals("turnOnUseAllBoundaries")) { return this.turnOnUseAllBoundaries(data, dataRowID); }
		else if (actionName.equals("turnOnUseAllPipes")) { return this.turnOnUseAllPipes(data, dataRowID); }
		else if (actionName.equals("turnOnWindRose")) { return this.turnOnWindRose(data, dataRowID); }
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
		else if (actionName.equals("verifySurveyViewPageIsOpened")) { return this.verifySurveyViewPageIsOpened(data, dataRowID); }
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
		return (SurveyViewPage)this.pageObject;
	}
}
