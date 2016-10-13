package surveyor.scommon.source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.ExcelUtility;
import common.source.Log;
import common.source.OLMapUtility;
import common.source.RegexUtility;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.WebElementExtender;
import surveyor.dataaccess.source.Customer;
import surveyor.dataaccess.source.CustomerBoundaryType;
import surveyor.dataaccess.source.CustomerMaterialType;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.PageActionsFactory;
import surveyor.scommon.actions.data.CustomerDataReader;
import surveyor.scommon.actions.data.CustomerDataReader.CustomerDataRow;

public class BaseMapViewPage extends SurveyorBasePage {

	private static final String VIRTUALEARTH_NET_BRANDING_LOGO = "https://dev.virtualearth.net/Branding/logo_powered_by.png";
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.Dialog_MapView);
	private static final int ASSETS_ZOOM_LEVEL_LOWER_BOUND = 17;

	@FindBy(how = How.XPATH, using = "//*[@id='menu_content']/div[1]")
	private WebElement displaySwitch8HourHistoryDivElement;

	@FindBy(how = How.XPATH, using = "//*[@id='menu_content']/div[2]")
	private WebElement displaySwitchWindroseDivElement;

	@FindBy(how = How.XPATH, using = "//*[@id='menu_content']/div[3]")
	private WebElement displaySwitchConcentrationChartDivElement;

	@FindBy(how = How.XPATH, using = "//*[@id='menu_content']/div[4]")
	private WebElement displaySwitchNotesDivElement;

	@FindBy(how = How.XPATH, using = "//*[@id='menu_content']/div[5]")
	private WebElement displaySwitchIsotopicAnalysisDivElement;

	@FindBy(how = How.XPATH, using = "//*[@id='menu_content']/div[6]/div[1]")
	private WebElement displaySwitchIndicationsDivElement;
	
	@FindBy(how = How.XPATH, using = "//*[@id='menu_content']/div[6]/div[2]")
	private WebElement displaySwitchPossibleNaturalGasDivElement;
	
	@FindBy(how = How.XPATH, using = "//*[@id='menu_content']/div[6]/div[3]")
	private WebElement displaySwitchNotNaturalGasDivElement;
	
	@FindBy(how = How.XPATH, using = "//*[@id='menu_content']/div[6]/div[4]")
	private WebElement displaySwitchVehicleExhaustDivElement;

	@FindBy(how = How.XPATH, using = "//*[@id='menu_content']/div[7]")
	private WebElement displaySwitchLisasDivElement;

	@FindBy(how = How.XPATH, using = "//*[@id='menu_content']/div[8]")
	private WebElement displaySwitchFovsDivElement;

	@FindBy(id = "display_switch_8hour_history")
	protected WebElement displaySwitch8HourHistory;

	@FindBy(id = "display_switch_windrose")
	protected WebElement displaySwitchWindrose;

	@FindBy(id = "display_switch_concentration_chart")
	protected WebElement displaySwitchConcentrationChart;

	@FindBy(id = "display_switch_notes")
	protected WebElement displaySwitchNotes;

	@FindBy(id = "display_switch_isotopic_analysis")
	protected WebElement displaySwitchIsotopicAnalysis;

	@FindBy(id = "display_switch_indications")
	protected WebElement displaySwitchIndications;

	@FindBy(id = "display_switch_possible_natural_gas")
	protected WebElement displaySwitchPossibleNaturalGas;
	
	@FindBy(id = "display_switch_not_natural_gas")
	protected WebElement displaySwitchNotNaturalGas;
	
	@FindBy(id = "display_switch_vehicle_exhaust")
	protected WebElement displaySwitchVehicleExhaust;
	
	@FindBy(id = "display_switch_lisas")
	protected WebElement displaySwitchLisas;

	@FindBy(id = "display_switch_fovs")
	protected WebElement displaySwitchFovs;

	@FindBy(id = "map_switch_satellite")
	protected WebElement mapSwitchSatellite;

	@FindBy(id = "map_switch_map")
	protected WebElement mapSwitchMap;
	
	@FindBy(how = How.XPATH, using = "//*[@id='gis_left']/li[1]/div")
	protected WebElement materialTypeCopperDivElement;

	@FindBy(how = How.XPATH, using = "//*[@id='gis_left']/li[2]/div")
	protected WebElement materialTypeUnProtectedSteelDivElement;

	@FindBy(how = How.XPATH, using = "//*[@id='gis_left']/li[3]/div")
	protected WebElement materialTypeProtectedSteelDivElement;

	@FindBy(how = How.XPATH, using = "//*[@id='gis_left']/li[4]/div")
	protected WebElement materialTypeCastIronDivElement;

	@FindBy(how = How.XPATH, using = "//*[@id='gis_left']/li[5]/div")
	protected WebElement materialTypeOtherPlasticDivElement;

	@FindBy(how = How.XPATH, using = "//*[@id='gis_left']/li[6]/div")
	protected WebElement materialTypePEPlasticDivElement;

	@FindBy(how = How.XPATH, using = "//*[@id='gis_left']/div[3]")
	protected WebElement useAllPipesDivElement;
		
	@FindBy(how = How.XPATH, using = "//*[@id='gis_right']/li[1]/div")
	protected WebElement boundariesSmallBoundaryDivElement;

	@FindBy(how = How.XPATH, using = "//*[@id='gis_right']/li[2]/div")
	protected WebElement boundariesBigBoundaryDivElement;

	@FindBy(how = How.XPATH, using = "//*[@id='gis_right']/div[3]")
	protected WebElement useAllBoundariesDivElement;

	@FindBy(id = "d08fc87f-f979-4131-92a9-3d82f37f4bba")
	private WebElement materialTypeCopper;

	@FindBy(id = "f3955e82-dd13-4842-84f7-502bcda6b57a")
	private WebElement materialTypeUnprotectedSteel;

	@FindBy(id = "44353e68-0694-4f05-85cb-84d753ea278c")
	private WebElement materialTypeProtectedSteel;

	@FindBy(id = "96caf1f5-d5c5-461d-9ce3-d210c20a1bb0")
	private WebElement materialTypeCastIron;

	@FindBy(id = "ad701312-c470-482a-be45-ef37770e2ce6")
	private WebElement materialTypeOtherPlastic;

	@FindBy(id = "f14735de-6c9b-4423-8533-f243a7fe4e90")
	private WebElement materialTypePEPlastic;

	@FindBy(id = "gis_switch_all_pipes")
	private WebElement useAllPipes;

	@FindBy(id = "551cb7c0-005b-4e3e-bfae-d19da0ed7efe")
	private WebElement smallBoundary;

	@FindBy(id = "024249ae-374b-4f6f-bd87-e8fdcacb48e1")
	private WebElement bigBoundary;

	@FindBy(id = "gis_switch_all_boundaries")
	private WebElement useAllBoundaries;

	@FindBy(id = "bottom_button_exit")
	private WebElement curtainReturnButton;

	@FindBy(id = "bottom_button_arrow_up")
	private WebElement curtainArrowUpButton;

	@FindBy(id = "bottom_button_arrow_down")
	private WebElement curtainArrowDownButton;

	@FindBy(id = "bottom_button_arrow_left")
	private WebElement curtainArrowLeftButton;

	@FindBy(id = "bottom_button_arrow_right")
	private WebElement curtainArrowRightButton;

	@FindBy(id = "bottom_button_zoom_in")
	private WebElement curtainZoomInButton;

	@FindBy(id = "bottom_button_zoom_out")
	private WebElement curtainZoomOutButton;

	@FindBy(id = "bottom_button_mode")
	protected WebElement modeButton;

	@FindBy(id = "bottom_button_display")
	protected WebElement displayButton;

	@FindBy(id = "bottom_button_map")
	private WebElement mapButton;

	@FindBy(id = "bottom_button_gis")
	private WebElement gisButton;

	@FindBy(id = "bottom_button_curtain_view")
	private WebElement curtainButton;

	@FindBy(id = "bottom_button_status")
	private WebElement statusButton;
	
	@FindBy(id = "bottom_logo")
	private WebElement picarroLogoButton;

	@FindBy(css = "#map.map button[title='Zoom in']")
	protected WebElement zoomInButton;
	
	@FindBy(css = "#map.map button[title='Zoom out']")
	protected WebElement zoomOutButton;
	
	@FindBy(id = "shutting_down")
	protected WebElement ShutdownAnalyzerButton;
	
	@FindBy(id = "blocked_ui")
	private WebElement divBlockedUI;
	
	@FindBy(id = "btn_close_annotation")
	private WebElement fieldNotesDialogCloseButton;

	@FindBy(id = "display_menu")
	private WebElement displayMenu;

	@FindBy(id = "gis_menu")
	private WebElement gisMenu;
	
	@FindBy(id = "base_map_menu")
	protected WebElement mapMenu;
	
	// Peak info popup values are updated on each peakInfo click. Seek these elements newly when get*() method is called.
	private WebElement peakInfoEpoch;
	private WebElement peakInfoLatitude;
	private WebElement peakInfoLongitude;
	private WebElement peakInfoText;
	private WebElement peakInfoCoordinate;
	private WebElement peakInfoOverlayText;
	private WebElement peakInfoPopupDiv;

    // Survey ID used for opening the specified survey page.
    private String surveyId;
    
	public BaseMapViewPage(WebDriver driver, TestSetup testSetup, String strBaseURL, String strPageURL) {
		super(driver, testSetup, strBaseURL, strPageURL);
	}

	public enum DisplaySwitchType {
		EightHourHistory, WindRose, ConcentrationChart, Notes, IsotopicAnalysis, Indications, PossibleNaturalGas, NotNaturalGas, VehicleExhaust, Lisas, FOVs
	}

	public enum MapSwitchType {
		Satellite, Map
	}

	public enum GisSwitchType {
		MaterialTypeCopper, MaterialTypeUnprotectedSteel, MaterialTypeProtectedSteel, MaterialTypeCastIron, MaterialTypeOtherPlastic, MaterialTypePEPlastic, UseAllPipes, SmallBoundary, BigBoundary, UseAllBoundaries
	}

	public void clickFieldNotesDialogCloseButton() {
		Log.clickElementInfo("FieldNotes Close");
		this.fieldNotesDialogCloseButton.click();
	}

	public BaseMapViewPage clickZoomInButton() {
		Log.clickElementInfo("Zoom in");
		this.zoomInButton.click();
		return this;
	}

	public BaseMapViewPage clickZoomOutButton() {
		Log.clickElementInfo("Zoom out");
		this.zoomOutButton.click();
		return this;
	}
	
	public BaseMapViewPage clickCurtainArrowUpButton() {
		Log.clickElementInfo("Curtain Arrrow Up");
		this.curtainArrowUpButton.click();
		return this;
	}

	public BaseMapViewPage clickCurtainArrowDownButton() {
		Log.clickElementInfo("Curtain Arrow Down");
		this.curtainArrowDownButton.click();
		return this;
	}

	public BaseMapViewPage clickCurtainArrowLeftButton() {
		Log.clickElementInfo("Curtain Arrow Left");
		this.curtainArrowLeftButton.click();
		return this;
	}

	public BaseMapViewPage clickCurtainArrowRightButton() {
		Log.clickElementInfo("Curtain Arrow Right");
		this.curtainArrowRightButton.click();
		return this;
	}

	public BaseMapViewPage clickCurtainZoomInButton() {
		Log.clickElementInfo("Curtain Zoom In");
		this.curtainZoomInButton.click();
		return this;
	}

	public BaseMapViewPage clickCurtainZoomOutButton() {
		Log.clickElementInfo("Curtain Zoom Out");
		this.curtainZoomOutButton.click();
		return this;
	}

	public BaseMapViewPage clickCurtainReturnButton() {
		Log.clickElementInfo("Curtain Return");
		this.curtainReturnButton.click();
		return this;
	}

	public BaseMapViewPage clickPicarroLogoButton() {
		Log.clickElementInfo("Picarro Logo");
		this.picarroLogoButton.click();
		return this;
	}
	
	public BaseMapViewPage clickMapButton() {
		Log.clickElementInfo("Map");
		this.mapButton.click();
		return this;
	}

	public BaseMapViewPage hideMapMenu() {
		if (isMapMenuOpen()) {
			clickMapButton();
		}
		return this;
	}

	public boolean isMapMenuOpen() {
		return !this.mapMenu.getAttribute("class").toLowerCase().contains("ng-hide");
	}

	public boolean isMapMenuClosed() {
		return !isMapMenuOpen();
	}

	public BaseMapViewPage clickGisButton() {
		Log.clickElementInfo("GIS");
		this.gisButton.click();
		return this;
	}

	public BaseMapViewPage hideGisMenu() {
		if (isGisMenuOpen()) {
			clickGisButton();
		}
		return this;
	}

	public boolean isGisMenuOpen() {
		return !this.gisMenu.getAttribute("class").toLowerCase().contains("ng-hide");
	}

	public boolean isGisMenuClosed() {
		return !isGisMenuOpen();
	}

	public BaseMapViewPage clickDisplayButton() {
		Log.clickElementInfo("Display");
		this.displayButton.click();
		return this;
	}

	public BaseMapViewPage hideDisplayMenu() {
		if (isDisplayMenuOpen()) {
			clickDisplayButton();
		}
		return this;
	}
	
	public boolean isDisplayMenuOpen() {
		return !this.displayMenu.getAttribute("class").toLowerCase().contains("ng-hide");
	}

	public boolean isDisplayMenuClosed() {
		return !isDisplayMenuOpen();
	}

	public BaseMapViewPage clickCurtainButton() {
		Log.clickElementInfo("Curtain");
		this.curtainButton.click();
		return this;
	}

	public BaseMapViewPage hideCurtainMenu() {
		clickCurtainButton();
		return this;
	}

	public WebElement getDivBlockedUI() {
		return this.divBlockedUI;
	}

	public boolean isCurtainButtonPresent(){
		return WebElementExtender.isElementPresentAndDisplayed(curtainButton);
	}
	
	public boolean isModeButtonVisible() {
		return !this.modeButton.getAttribute("class").contains("ng-hide");
	}
	public boolean isStatusButtonVisible() {
		return !this.statusButton.getAttribute("class").contains("ng-hide");
	}
	public boolean isShutdownAnalyzerButtonVisible() {
		return !this.ShutdownAnalyzerButton.getAttribute("class").contains("ng-hide");
	}
	public WebElement getPeakInfoPopupTextElement() {
		// element value changes dynamically on peakInfo click. Seek new each time.
		peakInfoOverlayText = this.driver.findElement(By.id("peak_info"));
		return peakInfoOverlayText;
	}

	public WebElement getPeakInfoCoordinateElement() {
		// element value changes dynamically on peakInfo click. Seek new each time.
		peakInfoCoordinate = this.driver.findElement(By.id("annotation-coordinate"));
		return peakInfoCoordinate;
	}

	public WebElement getPeakInfoTextElement() {
		// element value changes dynamically on peakInfo click. Seek new each time.
		peakInfoText = this.driver.findElement(By.id("annotation-text"));
		return peakInfoText;
	}

	public WebElement getPeakInfoLongitudeElement() {
		// element value changes dynamically on peakInfo click. Seek new each time.
		peakInfoLongitude = this.driver.findElement(By.id("annotation-longitude"));
		return peakInfoLongitude;
	}

	public WebElement getPeakInfoLatitudeElement() {
		// element value changes dynamically on peakInfo click. Seek new each time.
		peakInfoLatitude = this.driver.findElement(By.id("annotation-latitude"));
		return peakInfoLatitude;
	}

	public WebElement getPeakInfoEpochElement() {
		// element value changes dynamically on peakInfo click. Seek new each time.
		peakInfoEpoch = this.driver.findElement(By.id("annotation-epoch"));
		return peakInfoEpoch;
	}

	public WebElement getFieldNotesDialogCloseButton() {
		return fieldNotesDialogCloseButton;
	}

	public boolean isDisplaySwitch8HourHistoryButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.displaySwitch8HourHistoryDivElement,"ng-cloak") ||
					this.displaySwitch8HourHistoryDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isDisplaySwitchWindroseButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.displaySwitchWindroseDivElement,"ng-cloak") ||
					this.displaySwitchWindroseDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isDisplaySwitchConcentrationChartButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.displaySwitchConcentrationChartDivElement,"ng-cloak") ||
					this.displaySwitchConcentrationChartDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isDisplaySwitchNotesButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.displaySwitchNotesDivElement,"ng-cloak") ||
					this.displaySwitchNotesDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isDisplaySwitchIsotopicAnalysisButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.displaySwitchIsotopicAnalysisDivElement,"ng-cloak") ||
					this.displaySwitchIsotopicAnalysisDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isDisplaySwitchIndicationsButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.displaySwitchIndicationsDivElement,"ng-cloak") ||
					this.displaySwitchIndicationsDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isDisplaySwitchNotNaturalGasButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.displaySwitchNotNaturalGasDivElement,"ng-cloak") ||
					this.displaySwitchNotNaturalGasDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isDisplaySwitchVehicleExhaustButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.displaySwitchVehicleExhaustDivElement,"ng-cloak") ||
					this.displaySwitchVehicleExhaustDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isDisplaySwitchPossibleNaturalGasButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.displaySwitchPossibleNaturalGasDivElement,"ng-cloak") ||
					this.displaySwitchPossibleNaturalGasDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isDisplaySwitchLisasButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.displaySwitchLisasDivElement,"ng-cloak") ||
					this.displaySwitchLisasDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isDisplaySwitchFovsButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.displaySwitchFovsDivElement,"ng-cloak") ||
					this.displaySwitchFovsDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isGisMaterialTypeCastIronButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.materialTypeCastIronDivElement,"ng-cloak") ||
					this.materialTypeCastIronDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isGisMaterialTypeCopperButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.materialTypeCopperDivElement,"ng-cloak") ||
					this.materialTypeCopperDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isGisMaterialTypeOtherPlasticButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.materialTypeOtherPlasticDivElement,"ng-cloak") ||
					this.materialTypeOtherPlasticDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isGisMaterialTypePEPlasticButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.materialTypePEPlasticDivElement,"ng-cloak") ||
					this.materialTypePEPlasticDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isGisMaterialTypeProtectedSteelButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.materialTypeProtectedSteelDivElement,"ng-cloak") ||
					this.materialTypeProtectedSteelDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isGisMaterialTypeUnProtectedSteelButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.materialTypeUnProtectedSteelDivElement,"ng-cloak") ||
					this.materialTypeUnProtectedSteelDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isGisUseAllPipesButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.useAllPipesDivElement,"ng-cloak") ||
					this.useAllPipesDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isGisBoundaryBigBoundaryButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.boundariesBigBoundaryDivElement,"ng-cloak") ||
					this.boundariesBigBoundaryDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isGisBoundarySmallBoundaryButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.boundariesSmallBoundaryDivElement,"ng-cloak") ||
					this.boundariesSmallBoundaryDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isGisUseAllBoundariesButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.useAllBoundariesDivElement,"ng-cloak") ||
					this.useAllBoundariesDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isGisSwitchOn(GisSwitchType switchType) throws Exception {
		boolean isSelected = false;

		switch (switchType) {
		case BigBoundary:
			isSelected = this.getBigBoundary().getAttribute("class").equalsIgnoreCase("switch material_radio on");
			break;
		case SmallBoundary:
			isSelected = this.getSmallBoundary().getAttribute("class").equalsIgnoreCase("switch material_radio on");
			break;
		case MaterialTypeCopper:
			isSelected = this.getMaterialTypeCopper().getAttribute("class").equalsIgnoreCase("switch material_radio on");
			break;
		case MaterialTypeCastIron:
			isSelected = this.getMaterialTypeCastIron().getAttribute("class").equalsIgnoreCase("switch material_radio on");
			break;
		case MaterialTypeOtherPlastic:
			isSelected = this.getMaterialTypeOtherPlastic().getAttribute("class").equalsIgnoreCase("switch material_radio on");
			break;
		case MaterialTypePEPlastic:
			isSelected = this.getMaterialTypePEPlastic().getAttribute("class").equalsIgnoreCase("switch material_radio on");
			break;
		case MaterialTypeProtectedSteel:
			isSelected = this.getMaterialTypeProtectedSteel().getAttribute("class").equalsIgnoreCase("switch material_radio on");
			break;
		case MaterialTypeUnprotectedSteel:
			isSelected = this.getMaterialTypeUnprotectedSteel().getAttribute("class").equalsIgnoreCase("switch material_radio on");
			break;
		case UseAllBoundaries:
			isSelected = this.getUseAllBoundaries().getAttribute("class").equalsIgnoreCase("switch on");
			break;
		case UseAllPipes:
			isSelected = this.getUseAllPipes().getAttribute("class").equalsIgnoreCase("switch on");
			break;
		default:
			throw new IllegalArgumentException("Gis switch type unknown and not currently handled.");
		}

		Log.info(switchType + " is "+(isSelected?"":" not ") + "selected");
		return isSelected;
	}

	public boolean isGisSwitchOff(GisSwitchType switchType) throws IOException, Exception {
		boolean isSelected = false;

		switch (switchType) {
		case BigBoundary:
			isSelected = this.getBigBoundary().getAttribute("class").equalsIgnoreCase("switch material_radio");
			break;
		case SmallBoundary:
			isSelected = this.getSmallBoundary().getAttribute("class").equalsIgnoreCase("switch material_radio");
			break;
		case MaterialTypeCopper:
			isSelected = this.getMaterialTypeCopper().getAttribute("class").equalsIgnoreCase("switch material_radio");
			break;
		case MaterialTypeCastIron:
			isSelected = this.getMaterialTypeCastIron().getAttribute("class").equalsIgnoreCase("switch material_radio");
			break;
		case MaterialTypeOtherPlastic:
			isSelected = this.getMaterialTypeOtherPlastic().getAttribute("class").equalsIgnoreCase("switch material_radio");
			break;
		case MaterialTypePEPlastic:
			isSelected = this.getMaterialTypePEPlastic().getAttribute("class").equalsIgnoreCase("switch material_radio");
			break;
		case MaterialTypeProtectedSteel:
			isSelected = this.getMaterialTypeProtectedSteel().getAttribute("class").equalsIgnoreCase("switch material_radio");
			break;
		case MaterialTypeUnprotectedSteel:
			isSelected = this.getMaterialTypeUnprotectedSteel().getAttribute("class").equalsIgnoreCase("switch material_radio");
			break;
		case UseAllBoundaries:
			isSelected = this.getUseAllBoundaries().getAttribute("class").equalsIgnoreCase("switch");
			break;
		case UseAllPipes:
			isSelected = this.getUseAllPipes().getAttribute("class").equalsIgnoreCase("switch");
			break;
		default:
			throw new IllegalArgumentException("Gis switch type unknown and not currently handled.");
		}

		Log.info(switchType + " is "+(isSelected?"":" not ") + "selected");
		return isSelected;
	}

	public boolean toggleGisSwitch(GisSwitchType switchType, boolean turnOn) throws IOException, Exception {
		boolean isSelected = false;

		switch (switchType) {
		case BigBoundary:
			if (this.getBigBoundary().getAttribute("class").equalsIgnoreCase("switch material_radio")) {
				if (turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch on");
					this.getBigBoundary().click();
				}
			} else if (this.getBigBoundary().getAttribute("class").equalsIgnoreCase("switch material_radio on")) {
				if (!turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch off");
					this.getBigBoundary().click();
				}
			}
			break;
		case SmallBoundary:
			if (this.getSmallBoundary().getAttribute("class").equalsIgnoreCase("switch material_radio")) {
				if (turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch on");
					this.getSmallBoundary().click();
				}
			} else if (this.getSmallBoundary().getAttribute("class").equalsIgnoreCase("switch material_radio on")) {
				if (!turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch off");
					this.getSmallBoundary().click();
				}
			}
			break;
		case MaterialTypeCopper:
			if (this.getMaterialTypeCopper().getAttribute("class").equalsIgnoreCase("switch material_radio")) {
				if (turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch on");
					this.getMaterialTypeCopper().click();
				}
			} else if (this.getMaterialTypeCopper().getAttribute("class").equalsIgnoreCase("switch material_radio on")) {
				if (!turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch off");
					this.getMaterialTypeCopper().click();
				}
			}
			break;
		case MaterialTypeCastIron:
			if (this.getMaterialTypeCastIron().getAttribute("class").equalsIgnoreCase("switch material_radio")) {
				if (turnOn) {
					Log.clickElementInfo(switchType.toString());
					this.getMaterialTypeCastIron().click();
				}
			} else if (this.getMaterialTypeCastIron().getAttribute("class").equalsIgnoreCase("switch material_radio on")) {
				if (!turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch off");
					this.getMaterialTypeCastIron().click();
				}
			}
			break;
		case MaterialTypeOtherPlastic:
			if (this.getMaterialTypeOtherPlastic().getAttribute("class").equalsIgnoreCase("switch material_radio")) {
				if (turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch on");
					this.getMaterialTypeOtherPlastic().click();
				}
			} else
				if (this.getMaterialTypeOtherPlastic().getAttribute("class").equalsIgnoreCase("switch material_radio on")) {
				if (!turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch off");
					this.getMaterialTypeOtherPlastic().click();
				}
			}
			break;
		case MaterialTypePEPlastic:
			if (this.getMaterialTypePEPlastic().getAttribute("class").equalsIgnoreCase("switch material_radio")) {
				if (turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch on");
					this.getMaterialTypePEPlastic().click();
				}
			} else if (this.getMaterialTypePEPlastic().getAttribute("class").equalsIgnoreCase("switch material_radio on")) {
				if (!turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch off");
					this.getMaterialTypePEPlastic().click();
				}
			}
			break;
		case MaterialTypeProtectedSteel:
			if (this.getMaterialTypeProtectedSteel().getAttribute("class").equalsIgnoreCase("switch material_radio")) {
				if (turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch on");
					this.getMaterialTypeProtectedSteel().click();
				}
			} else if (this.getMaterialTypeProtectedSteel().getAttribute("class")
					.equalsIgnoreCase("switch material_radio on")) {
				if (!turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch off");
					this.getMaterialTypeProtectedSteel().click();
				}
			}
			break;
		case MaterialTypeUnprotectedSteel:
			if (this.getMaterialTypeUnprotectedSteel().getAttribute("class").equalsIgnoreCase("switch material_radio")) {
				if (turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch on");
					this.getMaterialTypeUnprotectedSteel().click();
				}
			} else if (this.getMaterialTypeUnprotectedSteel().getAttribute("class")
					.equalsIgnoreCase("switch material_radio on")) {
				if (!turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch off");
					this.getMaterialTypeUnprotectedSteel().click();
				}
			}
			break;
		case UseAllBoundaries:
			if (this.getUseAllBoundaries().getAttribute("class").equalsIgnoreCase("switch")) {
				if (turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch on");
					this.getUseAllBoundaries().click();
				}
			} else if (this.getUseAllBoundaries().getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch off");
					this.getUseAllBoundaries().click();
				}
			}
			break;
		case UseAllPipes:
			if (this.getUseAllPipes().getAttribute("class").equalsIgnoreCase("switch")) {
				if (turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch on");
					this.getUseAllPipes().click();
				}
			} else if (this.getUseAllPipes().getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch off");
					this.getUseAllPipes().click();
				}
			}
			break;
		default:
			throw new IllegalArgumentException("Gis switch type unknown and not currently handled.");
		}

		return isSelected;
	}
	
	public boolean verifyLoadedMap(MapSwitchType switchType) {

		// Check map logo to ensure BingMap is getting loaded.
		Object mapLogo = ((JavascriptExecutor) driver)
				.executeScript("src = mapLayer.getSource();return src.getLogo();");
		if (!mapLogo.toString().equalsIgnoreCase(VIRTUALEARTH_NET_BRANDING_LOGO)) {
			Log.info("Map is not loaded sucessfully");
			return false;
		}

		// Checks the number of attributions returned to ensure correct map is loaded.
		// Satellite map has 29 attributions. Map map has 7 attributions.
		Object attributionsLength = ((JavascriptExecutor) driver)
				.executeScript("src = mapLayer.getSource();return src.getAttributions().length;");

		switch (switchType) {
		case Satellite:
			if (attributionsLength.toString() != "29") {
				Log.error(switchType.toString() + " map should have 29 attributions");
				return false;
			}
			break;
		case Map:
			if (attributionsLength.toString() != "7") {
				Log.error(switchType.toString() + " should have 7 attributions");
				return false;
			}
			break;
		default:
			throw new IllegalArgumentException("Map switch type unknown and not currently handled.");
		}

		return true;
	}

	public boolean isMapSwitchOn(MapSwitchType switchType) throws IllegalArgumentException {
		boolean isSelected = false;

		switch (switchType) {
		case Satellite:
			isSelected = this.mapSwitchSatellite.getAttribute("class").equalsIgnoreCase("switch radio_switch on");
			break;
		case Map:
			isSelected = this.mapSwitchMap.getAttribute("class").equalsIgnoreCase("switch radio_switch on");
			break;
		default:
			throw new IllegalArgumentException("Map switch type unknown and not currently handled.");
		}
		
		Log.info(switchType + " is "+(isSelected?"":" not ") + "selected");
		return isSelected;
	}

	public boolean isMapSwitchOff(MapSwitchType switchType) throws IllegalArgumentException {
		boolean isSelected = false;

		switch (switchType) {
		case Satellite:
			isSelected = this.mapSwitchSatellite.getAttribute("class").equalsIgnoreCase("switch radio_switch");
			break;
		case Map:
			isSelected = this.mapSwitchMap.getAttribute("class").equalsIgnoreCase("switch radio_switch");
			break;
		default:
			throw new IllegalArgumentException("Map switch type unknown and not currently handled.");
		}
		
		Log.info(switchType + " is "+(isSelected?"":" not ") + "selected");
		return isSelected;
	}

	public void toggleMapSwitch(MapSwitchType switchType, boolean turnOn) {
		switch (switchType) {
		case Satellite:
			if (this.mapSwitchSatellite.getAttribute("class").equalsIgnoreCase("switch radio_switch")) {
				if (turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch on");
					this.mapSwitchSatellite.click();
				}
			} else if (this.mapSwitchSatellite.getAttribute("class").equalsIgnoreCase("switch radio_switch on")) {
				if (!turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch off");
					this.mapSwitchSatellite.click();
				}
			}
			break;
		case Map:
			if (this.mapSwitchMap.getAttribute("class").equalsIgnoreCase("switch radio_switch")) {
				if (turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch on");
					this.mapSwitchMap.click();
				}
			} else if (this.mapSwitchMap.getAttribute("class").equalsIgnoreCase("switch radio_switch on")) {
				if (!turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch off");
					this.mapSwitchMap.click();
				}
			}
			break;
		default:
			throw new IllegalArgumentException("Map switch type unknown and not currently handled.");
		}
	}

	public boolean isDisplaySwitchOff(DisplaySwitchType switchType) throws IllegalArgumentException {
		boolean isSelected = false;

		switch (switchType) {
		case EightHourHistory:
			isSelected = this.displaySwitch8HourHistory.getAttribute("class").equalsIgnoreCase("switch");
			break;
		case ConcentrationChart:
			isSelected = this.displaySwitchConcentrationChart.getAttribute("class").equalsIgnoreCase("switch");
			break;
		case FOVs:
			isSelected = this.displaySwitchFovs.getAttribute("class").equalsIgnoreCase("switch");
			break;
		case Indications:
			isSelected = this.displaySwitchIndications.getAttribute("class").equalsIgnoreCase("switch");
			break;
		case PossibleNaturalGas:
			isSelected = this.displaySwitchPossibleNaturalGas.getAttribute("class").equalsIgnoreCase("switch subswitch");
			break;
		case NotNaturalGas:
			isSelected = this.displaySwitchNotNaturalGas.getAttribute("class").equalsIgnoreCase("switch subswitch");
			break;
		case VehicleExhaust:
			isSelected = this.displaySwitchVehicleExhaust.getAttribute("class").equalsIgnoreCase("switch subswitch");
			break;
		case IsotopicAnalysis:
			isSelected = this.displaySwitchIsotopicAnalysis.getAttribute("class").equalsIgnoreCase("switch");
			break;
		case Lisas:
			isSelected = this.displaySwitchLisas.getAttribute("class").equalsIgnoreCase("switch");
			break;
		case Notes:
			isSelected = this.displaySwitchNotes.getAttribute("class").equalsIgnoreCase("switch");
			break;
		case WindRose:
			isSelected = this.displaySwitchWindrose.getAttribute("class").equalsIgnoreCase("switch");
			break;
		default:
			throw new IllegalArgumentException("Display switch type unknown and not currently handled.");
		}

		Log.info(switchType + " is "+(isSelected?"":" not ") + "selected");
		return isSelected;
	}

	public boolean isDisplaySwitchOn(DisplaySwitchType switchType) throws IllegalArgumentException {
		boolean isSelected = false;

		switch (switchType) {
		case EightHourHistory:
			isSelected = this.displaySwitch8HourHistory.getAttribute("class").equalsIgnoreCase("switch on");
			break;
		case ConcentrationChart:
			isSelected = this.displaySwitchConcentrationChart.getAttribute("class").equalsIgnoreCase("switch on");
			break;
		case FOVs:
			isSelected = this.displaySwitchFovs.getAttribute("class").equalsIgnoreCase("switch on");
			break;
		case Indications:
			isSelected = this.displaySwitchIndications.getAttribute("class").equalsIgnoreCase("switch on");
			break;
		case PossibleNaturalGas:
			isSelected = this.displaySwitchPossibleNaturalGas.getAttribute("class").equalsIgnoreCase("switch on");
			break;
		case NotNaturalGas:
			isSelected = this.displaySwitchNotNaturalGas.getAttribute("class").equalsIgnoreCase("switch on");
			break;
		case VehicleExhaust:
			isSelected = this.displaySwitchVehicleExhaust.getAttribute("class").equalsIgnoreCase("switch on");
			break;
		case IsotopicAnalysis:
			isSelected = this.displaySwitchIsotopicAnalysis.getAttribute("class").equalsIgnoreCase("switch on");
			break;
		case Lisas:
			isSelected = this.displaySwitchLisas.getAttribute("class").equalsIgnoreCase("switch on");
			break;
		case Notes:
			isSelected = this.displaySwitchNotes.getAttribute("class").equalsIgnoreCase("switch on");
			break;
		case WindRose:
			isSelected = this.displaySwitchWindrose.getAttribute("class").equalsIgnoreCase("switch on");
			break;
		default:
			throw new IllegalArgumentException("Display switch type unknown and not currently handled.");
		}

		Log.info(switchType + " is "+(isSelected?"":" not ") + "selected");
		return isSelected;
	}

	public void toggleDisplaySwitch(DisplaySwitchType switchType, boolean turnOn) {
		switch (switchType) {
		case EightHourHistory:
			if (this.displaySwitch8HourHistory.getAttribute("class").equalsIgnoreCase("switch")) {
				if (turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch on");
					this.displaySwitch8HourHistory.click();
				}
			} else if (this.displaySwitch8HourHistory.getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch off");
					this.displaySwitch8HourHistory.click();
				}
			}
			break;
		case ConcentrationChart:
			if (this.displaySwitchConcentrationChart.getAttribute("class").equalsIgnoreCase("switch")) {
				if (turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch on");
					this.displaySwitchConcentrationChart.click();
				}
			} else if (this.displaySwitchConcentrationChart.getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch off");
					this.displaySwitchConcentrationChart.click();
				}
			}
			break;
		case FOVs:
			if (this.displaySwitchFovs.getAttribute("class").equalsIgnoreCase("switch")) {
				if (turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch on");
					this.displaySwitchFovs.click();
				}
			} else if (this.displaySwitchFovs.getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch off");
					this.displaySwitchFovs.click();
				}
			}
			break;
		case Indications:
			if (this.displaySwitchIndications.getAttribute("class").equalsIgnoreCase("switch")) {
				if (turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch on");
					this.displaySwitchIndications.click();
					TestContext.INSTANCE.stayIdle(2);
				}
			} else if (this.displaySwitchIndications.getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch off");
					this.displaySwitchIndications.click();
					TestContext.INSTANCE.stayIdle(2);
				}
			}
			break;
		case PossibleNaturalGas:
			if (this.displaySwitchPossibleNaturalGas.getAttribute("class").equalsIgnoreCase("switch subswitch")) {
				if (turnOn) {
					this.displaySwitchPossibleNaturalGas.click();
				}
			} else if (this.displaySwitchPossibleNaturalGas.getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					this.displaySwitchPossibleNaturalGas.click();
				}
			}
			break;
		case NotNaturalGas:
			if (this.displaySwitchNotNaturalGas.getAttribute("class").equalsIgnoreCase("switch subswitch")) {
				if (turnOn) {
					this.displaySwitchNotNaturalGas.click();
				}
			} else if (this.displaySwitchNotNaturalGas.getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					this.displaySwitchNotNaturalGas.click();
				}
			}
			break;
		case VehicleExhaust:
			if (this.displaySwitchVehicleExhaust.getAttribute("class").equalsIgnoreCase("switch subswitch")) {
				if (turnOn) {
					this.displaySwitchVehicleExhaust.click();
				}
			} else if (this.displaySwitchVehicleExhaust.getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					this.displaySwitchVehicleExhaust.click();
				}
			}
			break;
		case IsotopicAnalysis:
			if (this.displaySwitchIsotopicAnalysis.getAttribute("class").equalsIgnoreCase("switch")) {
				if (turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch on");
					this.displaySwitchIsotopicAnalysis.click();
				}
			} else if (this.displaySwitchIsotopicAnalysis.getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch off");
					this.displaySwitchIsotopicAnalysis.click();
				}
			}
			break;
		case Lisas:
			if (this.displaySwitchLisas.getAttribute("class").equalsIgnoreCase("switch")) {
				if (turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch on");
					this.displaySwitchLisas.click();
				}
			} else if (this.displaySwitchLisas.getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch off");
					this.displaySwitchLisas.click();
				}
			}
			break;
		case Notes:
			if (this.displaySwitchNotes.getAttribute("class").equalsIgnoreCase("switch")) {
				if (turnOn) {
					Log.clickElementInfo(switchType.toString());
					this.displaySwitchNotes.click();
				}
			} else if (this.displaySwitchNotes.getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch off");
					this.displaySwitchNotes.click();
				}
			}
			break;
		case WindRose:
			if (this.displaySwitchWindrose.getAttribute("class").equalsIgnoreCase("switch")) {
				if (turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch on");
					this.displaySwitchWindrose.click();
				}
			} else if (this.displaySwitchWindrose.getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					Log.clickElementInfo(switchType.toString(), "to switch off");
					this.displaySwitchWindrose.click();
				}
			}
			break;
		default:
			throw new IllegalArgumentException("Display switch type unknown and not currently handled.");
		}
	}

	public String getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}
	
	/**
	 * Executes setMapZoomLevel action.
	 * @param zoomlevel - specifies the zoom level on the map.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean setZoomLevel(int zoomlevel) {
		OLMapUtility mapUtility = new OLMapUtility(driver);
		int currentZoomlevel = mapUtility.getMapZoomLevel();
		int numClicks = Math.abs(currentZoomlevel-zoomlevel);
		
		for(int i=0;i<numClicks;i++){
		  if(currentZoomlevel > zoomlevel){
			  clickZoomOutButton();
		  }else if(currentZoomlevel < zoomlevel){
			  clickZoomInButton();
		  }else{
			  return true;
		  }
		}
		return mapUtility.getMapZoomLevel()==zoomlevel;
	}
	
	/**
	 * Executes setMapZoomLevelForAssets action.
	 * @return - returns whether the action was successful or not.
	 */
	public boolean setZoomLevelForAssets() {
		OLMapUtility mapUtility = new OLMapUtility(driver);
		int currentZoomlevel = mapUtility.getMapZoomLevel();
		if(currentZoomlevel >= ASSETS_ZOOM_LEVEL_LOWER_BOUND){
			return true;
		}
		int numClicks = Math.abs(currentZoomlevel-ASSETS_ZOOM_LEVEL_LOWER_BOUND);
		
		for(int i=0;i<numClicks;i++){
			  clickZoomInButton();
		}
		int newZoomlevel = mapUtility.getMapZoomLevel();
		return newZoomlevel==ASSETS_ZOOM_LEVEL_LOWER_BOUND;		
	}

	private CustomerBoundaryType getBoundaryTypeForLoggedInCustomer(String boundaryTypeDescription) throws Exception, IOException {
		Customer loggedInUserCustomer = ((LoginPageActions)PageActionsFactory.getAction("LoginPage")).getLoggedInUserCustomer();
		if (loggedInUserCustomer != null) {
			String customerId = loggedInUserCustomer.getId();    	
	    	CustomerBoundaryType customerBoundaryType = new CustomerBoundaryType();
	    	ArrayList<CustomerBoundaryType> custBoundaryList = customerBoundaryType.getAllForCustomer(customerId);
	    	CustomerBoundaryType boundaryType = custBoundaryList.stream()
	    			.filter(b -> b.getFeatureClassDescription().equals(boundaryTypeDescription))
	    			.findFirst()
	    			.orElse(null);
			return boundaryType;
		}
		
		return null;
	}

	private CustomerMaterialType getMaterialTypeForLoggedInCustomer(String materialTypeDescription) throws Exception, IOException {
		Customer loggedInUserCustomer = ((LoginPageActions)PageActionsFactory.getAction("LoginPage")).getLoggedInUserCustomer();
		if (loggedInUserCustomer != null) {
			String customerId = loggedInUserCustomer.getId();    	
	    	CustomerMaterialType customerMaterialType = new CustomerMaterialType();
	    	ArrayList<CustomerMaterialType> custBoundaryList = customerMaterialType.getAllForCustomer(customerId);
	    	CustomerMaterialType materialType = custBoundaryList.stream()
	    			.filter(b -> b.getDescription().equals(materialTypeDescription))
	    			.findFirst()
	    			.orElse(null);
			return materialType;
		}
		
		return null;
	}

    public WebElement getSmallBoundary() throws NumberFormatException, Exception {
    	Log.method("getSmallBoundary");
    	// Return specific to customer web element.
    	CustomerBoundaryType boundaryType = getBoundaryTypeForLoggedInCustomer("Small Boundary");
    	if (boundaryType != null) {
    		Log.info(String.format("Customer Boundary Type - Small Boundary Element Id = '%s'", boundaryType.getId().toLowerCase()));
    		return driver.findElement(By.id(boundaryType.getId().toLowerCase()));
    	}
    	
    	// Return default.
    	return smallBoundary;
	}

	public void setSmallBoundary(WebElement smallBoundary) {
		this.smallBoundary = smallBoundary;
	}

	public WebElement getBigBoundary() throws IOException, Exception {
		Log.method("getBigBoundary");
		// Return specific to customer web element.
    	CustomerBoundaryType boundaryType = getBoundaryTypeForLoggedInCustomer("Big Boundary");
    	if (boundaryType != null) {
    		Log.info(String.format("Customer Boundary Type - Big Boundary Element Id = '%s'", boundaryType.getId().toLowerCase()));
    		return driver.findElement(By.id(boundaryType.getId().toLowerCase()));
    	}
    	
    	// Return default.
		return bigBoundary;
	}

	public void setBigBoundary(WebElement bigBoundary) {
		this.bigBoundary = bigBoundary;
	}

	public WebElement getUseAllPipes() {
		return useAllPipes;
	}

	public void setUseAllPipes(WebElement useAllPipes) {
		this.useAllPipes = useAllPipes;
	}

	public WebElement getUseAllBoundaries() {
		return useAllBoundaries;
	}

	public void setUseAllBoundaries(WebElement useAllBoundaries) {
		this.useAllBoundaries = useAllBoundaries;
	}

	public WebElement getMaterialTypeCopper() throws IOException, Exception {
		Log.method("getMaterialTypeCopper");
    	// Return specific to customer web element.
    	CustomerMaterialType materialType = getMaterialTypeForLoggedInCustomer("Copper");
    	if (materialType != null) {
    		Log.info(String.format("Customer Material Type - Copper Element Id = '%s'", materialType.getId().toLowerCase()));
    		return driver.findElement(By.id(materialType.getId().toLowerCase()));
    	}

    	// Return default.
		return materialTypeCopper;
	}

	public void setMaterialTypeCopper(WebElement materialTypeCopper) {
		this.materialTypeCopper = materialTypeCopper;
	}

	public WebElement getMaterialTypeUnprotectedSteel() throws IOException, Exception {
		Log.method("getMaterialTypeUnprotectedSteel");
    	// Return specific to customer web element.
    	CustomerMaterialType materialType = getMaterialTypeForLoggedInCustomer("Un-protected Steel");
    	if (materialType != null) {
    		Log.info(String.format("Customer Material Type - Un-Protected Steel Element Id = '%s'", materialType.getId().toLowerCase()));
    		return driver.findElement(By.id(materialType.getId().toLowerCase()));
    	}

    	// Return default.
		return materialTypeUnprotectedSteel;
	}

	public void setMaterialTypeUnprotectedSteel(WebElement materialTypeUnprotectedSteel) {
		this.materialTypeUnprotectedSteel = materialTypeUnprotectedSteel;
	}

	public WebElement getMaterialTypeProtectedSteel() throws IOException, Exception {
		Log.method("getMaterialTypeProtectedSteel");
		// Return specific to customer web element.
    	CustomerMaterialType materialType = getMaterialTypeForLoggedInCustomer("Protected Steel");
    	if (materialType != null) {
    		Log.info(String.format("Customer Material Type - Protected Steel Element Id = '%s'", materialType.getId().toLowerCase()));
    		return driver.findElement(By.id(materialType.getId().toLowerCase()));
    	}

    	// Return default.
		return materialTypeProtectedSteel;
	}

	public void setMaterialTypeProtectedSteel(WebElement materialTypeProtectedSteel) {
		this.materialTypeProtectedSteel = materialTypeProtectedSteel;
	}

	public WebElement getMaterialTypeCastIron() throws IOException, Exception {
		Log.method("getMaterialTypeCastIron");
    	// Return specific to customer web element.
    	CustomerMaterialType materialType = getMaterialTypeForLoggedInCustomer("Cast Iron");
    	if (materialType != null) {
    		Log.info(String.format("Customer Material Type - Cast Iron Element Id = '%s'", materialType.getId().toLowerCase()));
    		return driver.findElement(By.id(materialType.getId().toLowerCase()));
    	}

    	// Return default.
		return materialTypeCastIron;
	}

	public void setMaterialTypeCastIron(WebElement materialTypeCastIron) {
		this.materialTypeCastIron = materialTypeCastIron;
	}

	public WebElement getMaterialTypeOtherPlastic() throws IOException, Exception {
		Log.method("getMaterialTypeOtherPlastic");
    	// Return specific to customer web element.
    	CustomerMaterialType materialType = getMaterialTypeForLoggedInCustomer("Other Plastic");
    	if (materialType != null) {
    		Log.info(String.format("Customer Material Type - Other Plastic Element Id = '%s'", materialType.getId().toLowerCase()));
    		return driver.findElement(By.id(materialType.getId().toLowerCase()));
    	}

    	// Return default.
		return materialTypeOtherPlastic;
	}

	public void setMaterialTypeOtherPlastic(WebElement materialTypeOtherPlastic) {
		this.materialTypeOtherPlastic = materialTypeOtherPlastic;
	}

	public WebElement getMaterialTypePEPlastic() throws IOException, Exception {
		Log.method("getMaterialTypePEPlastic");
    	// Return specific to customer web element.
    	CustomerMaterialType materialType = getMaterialTypeForLoggedInCustomer("PE Plastic");
    	if (materialType != null) {
    		Log.info(String.format("Customer Material Type - PE Plastic Element Id = '%s'", materialType.getId().toLowerCase()));
    		return driver.findElement(By.id(materialType.getId().toLowerCase()));
    	}

    	// Return default.
		return materialTypePEPlastic;
	}

	public void setMaterialTypePEPlastic(WebElement materialTypePEPlastic) {
		this.materialTypePEPlastic = materialTypePEPlastic;
	}

	/**
     * Verify that the page loaded completely.
     *
     * @return the SurveyViewPage class instance.
     */
    public BaseMapViewPage verifyPageLoaded() {
        (new WebDriverWait(driver, timeout * 4)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STRPageContentText);                		 
            }
        });
        return this;
    }

    /**
	 * Verifies that the Disposition value in peak info popup equals the specified value.
	 * @param value - value to compare with.
	 * @return
	 */
	public boolean verifyPeakInfoPopupDispositionEquals(String value) {
		return verifyPeakInfoPopupKeyValue(value, Resources.getResource(ResourceKeys.Survey_Disposition));
	}

	/**
	 * Verifies that the Classification Confidence value in peak info popup equals the specified value.
	 * @param value - value to compare with.
	 * @return
	 */
	public boolean verifyPeakInfoPopupClassificationConfidenceEquals(String value) {
		return verifyPeakInfoPopupKeyValue(value, Resources.getResource(ResourceKeys.Survey_ClassificationConfidence));
	}

	/**
	 * Verifies that the Methane Concentration value in peak info popup equals the specified value.
	 * @param value - value to compare with.
	 * @return
	 */
	public boolean verifyPeakInfoPopupMethaneConcEquals(String value) {
		return verifyPeakInfoPopupKeyValue(value, Resources.getResource(ResourceKeys.Survey_CH4));
	}

	/**
	 * Verifies that the Ethane Ratio value in peak info popup equals the specified value.
	 * @param value - value to compare with.
	 * @return
	 */
	public boolean verifyPeakInfoPopupEthaneRatioEquals(String value) {
		return verifyPeakInfoPopupKeyValue(value, Resources.getResource(ResourceKeys.Survey_EthaneRatio));
	}

	/**
	 * Verifies that the Amplitude value in peak info popup equals the specified value.
	 * @param value - value to compare with.
	 * @return
	 */
	public boolean verifyPeakInfoPopupAmplitudeEquals(String value) {
		return verifyPeakInfoPopupKeyValue(value, Resources.getResource(ResourceKeys.Survey_amplitude));
	}

	private boolean verifyPeakInfoPopupKeyValue(String value, String keyLabel) {
		String popupText = getPeakInfoPopupTextElement().getText();
		List<String> lines = RegexUtility.split(popupText, RegexUtility.NEWLINE_SPLIT_REGEX_PATTERN);
		String keyValue = "";
		for (String line : lines) {
			if (line.startsWith(keyLabel)) {
				keyValue = line.replace(keyLabel, "").trim();
			}
		}
		return keyValue.equals(value);
	}
	
	/**
	 * Wait for peak info popup to be shown.
	 */
	public void waitForPeakInfoPopupToOpen() {
		peakInfoPopupDiv = driver.findElement(By.id("peakinfo_modal"));
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return peakInfoPopupDiv.getAttribute("class").equalsIgnoreCase("balloon");
			}
		});
	}

	/**
	 * Wait for peak info popup to be hidden.
	 */
	public void waitForPeakInfoPopupToClose() {
		peakInfoPopupDiv = driver.findElement(By.id("peakinfo_modal"));
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return peakInfoPopupDiv.getAttribute("class").equalsIgnoreCase("ng-hide");
			}
		});
	}

	/**
	 * Verifies that the page UI is no longer blocked.
	 */
	public void waitForUIUnBlock() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return divBlockedUI.getAttribute("class").equalsIgnoreCase("ng-hide");
			}
		});
	}
	
	/**
	 * Waits for the Gis menu to open.
	 */
	public void waitForGisMenuToOpen() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return isGisMenuOpen();
			}
		});
	}

	/**
	 * Waits for the Gis menu to close.
	 */
	public void waitForGisMenuToClose() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return isGisMenuClosed();
			}
		});
	}

	/**
	 * Waits for the display menu to open.
	 */
	public void waitForDisplayMenuToOpen() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return isDisplayMenuOpen();
			}
		});
	}

	/**
	 * Waits for the display menu to close.
	 */
	public void waitForDisplayMenuToClose() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return isDisplayMenuClosed();
			}
		});
	}

	/**
	 * Waits for the map menu to open.
	 */
	public void waitForMapMenuToOpen() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return isMapMenuOpen();
			}
		});
	}

	/**
	 * Waits for the map menu to close.
	 */
	public void waitForMapMenuToClose() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return isMapMenuClosed();
			}
		});
	}

    /**
	 * Verify that the page loaded completely.
	 */
	public void waitForPageLoad() {
		this.verifyPageLoaded();
	}
}