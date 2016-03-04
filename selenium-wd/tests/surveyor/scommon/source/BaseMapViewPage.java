package surveyor.scommon.source;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.TestSetup;
import common.source.WebElementExtender;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

public class BaseMapViewPage extends SurveyorBasePage {

	private static final String VIRTUALEARTH_NET_BRANDING_LOGO = "https://dev.virtualearth.net/Branding/logo_powered_by.png";

	@FindBy(id = "bottom_button_position_follow")
	@CacheLookup
	private WebElement positionButton;

	@FindBy(id = "bottom_button_status")
	@CacheLookup
	private WebElement statusButton;
	
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

	@FindBy(how = How.XPATH, using = "//*[@id='menu_content']/div[6]")
	private WebElement displaySwitchIndicationsDivElement;

	@FindBy(how = How.XPATH, using = "//*[@id='menu_content']/div[7]")
	private WebElement displaySwitchLisasDivElement;

	@FindBy(how = How.XPATH, using = "//*[@id='menu_content']/div[8]")
	private WebElement displaySwitchFovsDivElement;

	@FindBy(id = "display_switch_8hour_history")
	@CacheLookup
	protected WebElement displaySwitch8HourHistory;

	@FindBy(id = "display_switch_windrose")
	@CacheLookup
	protected WebElement displaySwitchWindrose;

	@FindBy(id = "display_switch_concentration_chart")
	@CacheLookup
	protected WebElement displaySwitchConcentrationChart;

	@FindBy(id = "display_switch_notes")
	@CacheLookup
	protected WebElement displaySwitchNotes;

	@FindBy(id = "display_switch_isotopic_analysis")
	@CacheLookup
	protected WebElement displaySwitchIsotopicAnalysis;

	@FindBy(id = "display_switch_indications")
	@CacheLookup
	protected WebElement displaySwitchIndications;

	@FindBy(id = "display_switch_lisas")
	@CacheLookup
	protected WebElement displaySwitchLisas;

	@FindBy(id = "display_switch_fovs")
	@CacheLookup
	protected WebElement displaySwitchFovs;

	@FindBy(id = "map_switch_satellite")
	@CacheLookup
	protected WebElement mapSwitchSatellite;

	@FindBy(id = "map_switch_map")
	@CacheLookup
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
	@CacheLookup
	protected WebElement materialTypeCopper;

	@FindBy(id = "f3955e82-dd13-4842-84f7-502bcda6b57a")
	@CacheLookup
	protected WebElement materialTypeUnprotectedSteel;

	@FindBy(id = "44353e68-0694-4f05-85cb-84d753ea278c")
	@CacheLookup
	protected WebElement materialTypeProtectedSteel;

	@FindBy(id = "96caf1f5-d5c5-461d-9ce3-d210c20a1bb0")
	@CacheLookup
	protected WebElement materialTypeCastIron;

	@FindBy(id = "ad701312-c470-482a-be45-ef37770e2ce6")
	@CacheLookup
	protected WebElement materialTypeOtherPlastic;

	@FindBy(id = "f14735de-6c9b-4423-8533-f243a7fe4e90")
	@CacheLookup
	protected WebElement materialTypePEPlastic;

	@FindBy(id = "gis_switch_all_pipes")
	@CacheLookup
	protected WebElement useAllPipes;

	@FindBy(id = "551cb7c0-005b-4e3e-bfae-d19da0ed7efe")
	@CacheLookup
	protected WebElement boundariesDistrictPlat;

	@FindBy(id = "024249ae-374b-4f6f-bd87-e8fdcacb48e1")
	@CacheLookup
	protected WebElement boundariesDistrict;

	@FindBy(id = "gis_switch_all_boundaries")
	@CacheLookup
	protected WebElement useAllBoundaries;

	@FindBy(id = "bottom_button_exit")
	@CacheLookup
	private WebElement curtainReturnButton;

	@FindBy(id = "bottom_button_arrow_up")
	@CacheLookup
	private WebElement curtainArrowUpButton;

	@FindBy(id = "bottom_button_arrow_down")
	@CacheLookup
	private WebElement curtainArrowDownButton;

	@FindBy(id = "bottom_button_arrow_left")
	@CacheLookup
	private WebElement curtainArrowLeftButton;

	@FindBy(id = "bottom_button_arrow_right")
	@CacheLookup
	private WebElement curtainArrowRightButton;

	@FindBy(id = "bottom_button_zoom_in")
	@CacheLookup
	private WebElement curtainZoomInButton;

	@FindBy(id = "bottom_button_zoom_out")
	@CacheLookup
	private WebElement curtainZoomOutButton;

	@FindBy(id = "bottom_button_mode")
	@CacheLookup
	private WebElement modeButton;

	@FindBy(id = "bottom_button_display")
	@CacheLookup
	private WebElement displayButton;

	@FindBy(id = "bottom_button_map")
	@CacheLookup
	private WebElement mapButton;

	@FindBy(id = "bottom_button_gis")
	@CacheLookup
	private WebElement gisButton;

	@FindBy(id = "bottom_button_curtain_view")
	@CacheLookup
	private WebElement curtainButton;
	
	@FindBy(id = "bottom_logo")
	@CacheLookup
	private WebElement picarroLogoButton;

	@FindBy(id = "btn_survey_warning_ok")
	@CacheLookup
	private WebElement surveyDurationWarningDialogOkButton;

	@FindBy(id = "btn_survey_start_warning_ok")
	@CacheLookup
	private WebElement failedToStartSurveyDialogOkButton;

	@FindBy(id = "no_analyzer")
	@CacheLookup
	private WebElement divNoAnalyzer;

	@FindBy(id = "survey_duration_warning")
	@CacheLookup
	protected WebElement divSurveyDurationWarning;

	@FindBy(id = "survey_start_warning")
	@CacheLookup
	protected WebElement divSurveyStartWarning;
	
	@FindBy(id = "survey_warning_message")
	@CacheLookup
	protected WebElement spanSurveyWarningMessage;
	
	@FindBy(id = "survey_start_warning_message")
	@CacheLookup
	protected WebElement spanSurveyFailedToStartMessage;

	@FindBy(how = How.XPATH, using = "//*[@id='map']/div/div[2]/div[2]/button[1]")
	@CacheLookup
	protected WebElement zoomInButton;
	
	@FindBy(how = How.XPATH, using = "//*[@id='map']/div/div[2]/div[2]/button[2]")
	@CacheLookup
	protected WebElement zoomOutButton;

	public BaseMapViewPage(WebDriver driver, TestSetup testSetup, String strBaseURL, String strPageURL) {
		super(driver, testSetup, strBaseURL, strPageURL);
	}

	public enum DisplaySwitchType {
		EightHourHistory, WindRose, ConcentrationChart, Notes, IsotopicAnalysis, Indications, Lisas, FOVs
	}

	public enum MapSwitchType {
		Satellite, Map
	}

	public enum GisSwitchType {
		MaterialTypeCopper, MaterialTypeUnprotectedSteel, MaterialTypeProtectedSteel, MaterialTypeCastIron, MaterialTypeOtherPlastic, MaterialTypePEPlastic, UseAllPipes, BoundariesDistrictPlat, BoundariesDistrict, UseAllBoundaries
	}

	public BaseMapViewPage clickPositionButton() {
		this.positionButton.click();
		return this;
	}
	
	public BaseMapViewPage clickZoomInButton() {
		this.zoomInButton.click();
		return this;
	}

	public BaseMapViewPage clickZoomOutButton() {
		this.zoomOutButton.click();
		return this;
	}
	
	public BaseMapViewPage clickCurtainArrowUpButton() {
		this.curtainArrowUpButton.click();
		return this;
	}

	public BaseMapViewPage clickCurtainArrowDownButton() {
		this.curtainArrowDownButton.click();
		return this;
	}

	public BaseMapViewPage clickCurtainArrowLeftButton() {
		this.curtainArrowLeftButton.click();
		return this;
	}

	public BaseMapViewPage clickCurtainArrowRightButton() {
		this.curtainArrowRightButton.click();
		return this;
	}

	public BaseMapViewPage clickCurtainZoomInButton() {
		this.curtainZoomInButton.click();
		return this;
	}

	public BaseMapViewPage clickCurtainZoomOutButton() {
		this.curtainZoomOutButton.click();
		return this;
	}

	public BaseMapViewPage clickCurtainReturnButton() {
		this.curtainReturnButton.click();
		return this;
	}

	public BaseMapViewPage clickPicarroLogoButton() {
		this.picarroLogoButton.click();
		return this;
	}

	public BaseMapViewPage clickModeButton() {
		this.modeButton.click();
		return this;
	}

	public BaseMapViewPage clickSurveyDurationWarningDialogOkButton() {
		this.surveyDurationWarningDialogOkButton.click();
		return this;
	}

	public BaseMapViewPage clickFailedToStartSurveyDialogOkButton() {
		this.failedToStartSurveyDialogOkButton.click();
		return this;
	}
	
	public BaseMapViewPage hideModeMenu() {
		clickModeButton();
		return this;
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

	public boolean isDisplaySwitchLisasButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.displaySwitchLisasDivElement,"ng-cloak") ||
					this.displaySwitchLisasDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isDisplaySwitchFovsButtonVisible() {
		return !(WebElementExtender.isAttributePresent(this.displaySwitchFovsDivElement,"ng-cloak") ||
					this.displaySwitchFovsDivElement.getAttribute("class").contains("ng-hide"));
	}

	public boolean isPositionButtonSelected() {
		return this.positionButton.getAttribute("class").equalsIgnoreCase("bottom_button standard_icon on");
	}

	public boolean isPositionButtonGreen() {
		return this.positionButton.getAttribute("class").equalsIgnoreCase("bottom_button standard_icon on");
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

	/**
	 * Verifies that the page is done Connecting dialog is shown.
	 */
	public boolean isConnectionCompleteDialogShown() {
		if (divNoAnalyzer != null && divNoAnalyzer.isDisplayed()) {
			return divNoAnalyzer.getAttribute("class").equalsIgnoreCase("cssFade");
		}
		return false;
	}

	/**
	 * Verifies that the survey warning dialog is shown.
	 */
	public boolean isSurveyDurationWarningDialogShown() {
		if (divSurveyDurationWarning != null && divSurveyDurationWarning.isDisplayed()) {
			return divSurveyDurationWarning.getAttribute("class").equalsIgnoreCase("cssFade");
		}
		return false;
	}

	/**
	 * Verifies that correct message is shown in Survey Duration warning dialog.
	 */
	public boolean isCorrectSurveyDurationWarningMessageShowing() {
		if (isSurveyDurationWarningDialogShown()) {
			String surveyStopInMessage = Resources.getResource(ResourceKeys.Survey_SurveyWillStopIn);
			String surveyMinutesAndMessage = Resources.getResource(ResourceKeys.Survey_MinutesAnd);
			String surveySecondsMessage = Resources.getResource(ResourceKeys.Survey_Seconds);
			String msgText = spanSurveyWarningMessage.getText();
			return msgText.contains(surveyStopInMessage) && msgText.contains(surveyMinutesAndMessage) &&
					msgText.contains(surveySecondsMessage);
		}
		return false;
	}

	/**
	 * Verifies that the survey start dialog is shown.
	 */
	public boolean isSurveyFailedToStartDialogShown() {
		if (divSurveyStartWarning != null && divSurveyStartWarning.isDisplayed()) {
			return divSurveyStartWarning.getAttribute("class").equalsIgnoreCase("cssFade");
		}
		return false;
	}

	/**
	 * Verifies that correct message is shown in Survey Duration warning dialog.
	 */
	public boolean isCorrectSurveyFailedToStartMessageShowing() {
		if (isSurveyFailedToStartDialogShown()) {
			String msgText = spanSurveyFailedToStartMessage.getText();
			return msgText.equals(Resources.getResource(ResourceKeys.Dialog_SurveyFailedtoStart));
		}
		return false;
	}
	
	public boolean isStatusButtonGreen() {
		return this.statusButton.getAttribute("class").equalsIgnoreCase("bottom_button standard_icon");
	}

	public boolean isStatusButtonGreenWithPlus() {
		return this.statusButton.getAttribute("class").equalsIgnoreCase("bottom_button standard_icon open");
	}

	public boolean isStatusButtonOpen() {
		return this.statusButton.getAttribute("class").equalsIgnoreCase("bottom_button standard_icon open");
	}

	public boolean isStatusButtonRed() {
		return this.statusButton.getAttribute("class").equalsIgnoreCase("bottom_button standard_icon trouble");
	}

	public boolean togglePositionButton(boolean turnOn) throws IllegalArgumentException {
		boolean isSelected = isPositionButtonGreen();
		if ((isSelected && !turnOn) || (!isSelected && turnOn)) {
			clickPositionButton();
		}
		return isPositionButtonGreen();
	}

	public BaseMapViewPage clickStatusButton() {
		this.statusButton.click();
		return this;
	}

	public BaseMapViewPage clickMapButton() {
		this.mapButton.click();
		return this;
	}

	public BaseMapViewPage hideMapMenu() {
		clickMapButton();
		return this;
	}

	public BaseMapViewPage clickGisButton() {
		this.gisButton.click();
		return this;
	}

	public BaseMapViewPage hideGisMenu() {
		clickGisButton();
		return this;
	}

	public BaseMapViewPage clickDisplayButton() {
		this.displayButton.click();
		return this;
	}

	public BaseMapViewPage hideDisplayMenu() {
		clickDisplayButton();
		return this;
	}

	public BaseMapViewPage clickCurtainButton() {
		this.curtainButton.click();
		return this;
	}

	public BaseMapViewPage hideCurtainMenu() {
		clickCurtainButton();
		return this;
	}

	public boolean isGisSwitchOn(GisSwitchType switchType) throws IllegalArgumentException {
		boolean isSelected = false;

		switch (switchType) {
		case BoundariesDistrict:
			isSelected = this.boundariesDistrict.getAttribute("class").equalsIgnoreCase("switch material_radio on");
			break;
		case BoundariesDistrictPlat:
			isSelected = this.boundariesDistrictPlat.getAttribute("class").equalsIgnoreCase("switch material_radio on");
			break;
		case MaterialTypeCopper:
			isSelected = this.materialTypeCopper.getAttribute("class").equalsIgnoreCase("switch material_radio on");
			break;
		case MaterialTypeCastIron:
			isSelected = this.materialTypeCastIron.getAttribute("class").equalsIgnoreCase("switch material_radio on");
			break;
		case MaterialTypeOtherPlastic:
			isSelected = this.materialTypeOtherPlastic.getAttribute("class")
					.equalsIgnoreCase("switch material_radio on");
			break;
		case MaterialTypePEPlastic:
			isSelected = this.materialTypePEPlastic.getAttribute("class").equalsIgnoreCase("switch material_radio on");
			break;
		case MaterialTypeProtectedSteel:
			isSelected = this.materialTypeProtectedSteel.getAttribute("class")
					.equalsIgnoreCase("switch material_radio on");
			break;
		case MaterialTypeUnprotectedSteel:
			isSelected = this.materialTypeUnprotectedSteel.getAttribute("class")
					.equalsIgnoreCase("switch material_radio on");
			break;
		case UseAllBoundaries:
			isSelected = this.useAllBoundaries.getAttribute("class").equalsIgnoreCase("switch on");
			break;
		case UseAllPipes:
			isSelected = this.useAllPipes.getAttribute("class").equalsIgnoreCase("switch on");
			break;
		default:
			throw new IllegalArgumentException("Gis switch type unknown and not currently handled.");
		}

		return isSelected;
	}

	public boolean isGisSwitchOff(GisSwitchType switchType) throws IllegalArgumentException {
		boolean isSelected = false;

		switch (switchType) {
		case BoundariesDistrict:
			isSelected = this.boundariesDistrict.getAttribute("class").equalsIgnoreCase("switch material_radio");
			break;
		case BoundariesDistrictPlat:
			isSelected = this.boundariesDistrictPlat.getAttribute("class").equalsIgnoreCase("switch material_radio");
			break;
		case MaterialTypeCopper:
			isSelected = this.materialTypeCopper.getAttribute("class").equalsIgnoreCase("switch material_radio");
			break;
		case MaterialTypeCastIron:
			isSelected = this.materialTypeCastIron.getAttribute("class").equalsIgnoreCase("switch material_radio");
			break;
		case MaterialTypeOtherPlastic:
			isSelected = this.materialTypeOtherPlastic.getAttribute("class").equalsIgnoreCase("switch material_radio");
			break;
		case MaterialTypePEPlastic:
			isSelected = this.materialTypePEPlastic.getAttribute("class").equalsIgnoreCase("switch material_radio");
			break;
		case MaterialTypeProtectedSteel:
			isSelected = this.materialTypeProtectedSteel.getAttribute("class")
					.equalsIgnoreCase("switch material_radio");
			break;
		case MaterialTypeUnprotectedSteel:
			isSelected = this.materialTypeUnprotectedSteel.getAttribute("class")
					.equalsIgnoreCase("switch material_radio");
			break;
		case UseAllBoundaries:
			isSelected = this.useAllBoundaries.getAttribute("class").equalsIgnoreCase("switch");
			break;
		case UseAllPipes:
			isSelected = this.useAllPipes.getAttribute("class").equalsIgnoreCase("switch");
			break;
		default:
			throw new IllegalArgumentException("Gis switch type unknown and not currently handled.");
		}

		return isSelected;
	}

	public boolean toggleGisSwitch(GisSwitchType switchType, boolean turnOn) throws IllegalArgumentException {
		boolean isSelected = false;

		switch (switchType) {
		case BoundariesDistrict:
			if (this.boundariesDistrict.getAttribute("class").equalsIgnoreCase("switch material_radio")) {
				if (turnOn) {
					this.boundariesDistrict.click();
				}
			} else if (this.boundariesDistrict.getAttribute("class").equalsIgnoreCase("switch material_radio on")) {
				if (!turnOn) {
					this.boundariesDistrict.click();
				}
			}
			break;
		case BoundariesDistrictPlat:
			if (this.boundariesDistrictPlat.getAttribute("class").equalsIgnoreCase("switch material_radio")) {
				if (turnOn) {
					this.boundariesDistrictPlat.click();
				}
			} else if (this.boundariesDistrictPlat.getAttribute("class").equalsIgnoreCase("switch material_radio on")) {
				if (!turnOn) {
					this.boundariesDistrictPlat.click();
				}
			}
			break;
		case MaterialTypeCopper:
			if (this.materialTypeCopper.getAttribute("class").equalsIgnoreCase("switch material_radio")) {
				if (turnOn) {
					this.materialTypeCopper.click();
				}
			} else if (this.materialTypeCopper.getAttribute("class").equalsIgnoreCase("switch material_radio on")) {
				if (!turnOn) {
					this.materialTypeCopper.click();
				}
			}
			break;
		case MaterialTypeCastIron:
			if (this.materialTypeCastIron.getAttribute("class").equalsIgnoreCase("switch material_radio")) {
				if (turnOn) {
					this.materialTypeCastIron.click();
				}
			} else if (this.materialTypeCastIron.getAttribute("class").equalsIgnoreCase("switch material_radio on")) {
				if (!turnOn) {
					this.materialTypeCastIron.click();
				}
			}
			break;
		case MaterialTypeOtherPlastic:
			if (this.materialTypeOtherPlastic.getAttribute("class").equalsIgnoreCase("switch material_radio")) {
				if (turnOn) {
					this.materialTypeOtherPlastic.click();
				}
			} else
				if (this.materialTypeOtherPlastic.getAttribute("class").equalsIgnoreCase("switch material_radio on")) {
				if (!turnOn) {
					this.materialTypeOtherPlastic.click();
				}
			}
			break;
		case MaterialTypePEPlastic:
			if (this.materialTypePEPlastic.getAttribute("class").equalsIgnoreCase("switch material_radio")) {
				if (turnOn) {
					this.materialTypePEPlastic.click();
				}
			} else if (this.materialTypePEPlastic.getAttribute("class").equalsIgnoreCase("switch material_radio on")) {
				if (!turnOn) {
					this.materialTypePEPlastic.click();
				}
			}
			break;
		case MaterialTypeProtectedSteel:
			if (this.materialTypeProtectedSteel.getAttribute("class").equalsIgnoreCase("switch material_radio")) {
				if (turnOn) {
					this.materialTypeProtectedSteel.click();
				}
			} else if (this.materialTypeProtectedSteel.getAttribute("class")
					.equalsIgnoreCase("switch material_radio on")) {
				if (!turnOn) {
					this.materialTypeProtectedSteel.click();
				}
			}
			break;
		case MaterialTypeUnprotectedSteel:
			if (this.materialTypeUnprotectedSteel.getAttribute("class").equalsIgnoreCase("switch material_radio")) {
				if (turnOn) {
					this.materialTypeUnprotectedSteel.click();
				}
			} else if (this.materialTypeUnprotectedSteel.getAttribute("class")
					.equalsIgnoreCase("switch material_radio on")) {
				if (!turnOn) {
					this.materialTypeUnprotectedSteel.click();
				}
			}
			break;
		case UseAllBoundaries:
			if (this.useAllBoundaries.getAttribute("class").equalsIgnoreCase("switch")) {
				if (turnOn) {
					this.useAllBoundaries.click();
				}
			} else if (this.useAllBoundaries.getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					this.useAllBoundaries.click();
				}
			}
			break;
		case UseAllPipes:
			if (this.useAllPipes.getAttribute("class").equalsIgnoreCase("switch")) {
				if (turnOn) {
					this.useAllPipes.click();
				}
			} else if (this.useAllPipes.getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					this.useAllPipes.click();
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
			return false;
		}

		// Checks the number of attributions returned to ensure correct map is
		// loaded.
		// Satellite map has 29 attributions. Map map has 7 attributions.
		Object attributionsLength = ((JavascriptExecutor) driver)
				.executeScript("src = mapLayer.getSource();return src.getAttributions().length;");

		switch (switchType) {
		case Satellite:
			if (attributionsLength.toString() != "29") {
				return false;
			}
			break;
		case Map:
			if (attributionsLength.toString() != "7") {
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

		return isSelected;
	}

	public void toggleMapSwitch(MapSwitchType switchType, boolean turnOn) {
		switch (switchType) {
		case Satellite:
			if (this.mapSwitchSatellite.getAttribute("class").equalsIgnoreCase("switch radio_switch")) {
				if (turnOn) {
					this.mapSwitchSatellite.click();
				}
			} else if (this.mapSwitchSatellite.getAttribute("class").equalsIgnoreCase("switch radio_switch on")) {
				if (!turnOn) {
					this.mapSwitchSatellite.click();
				}
			}
			break;
		case Map:
			if (this.mapSwitchMap.getAttribute("class").equalsIgnoreCase("switch radio_switch")) {
				if (turnOn) {
					this.mapSwitchMap.click();
				}
			} else if (this.mapSwitchMap.getAttribute("class").equalsIgnoreCase("switch radio_switch on")) {
				if (!turnOn) {
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

		return isSelected;
	}

	public void toggleDisplaySwitch(DisplaySwitchType switchType, boolean turnOn) {
		switch (switchType) {
		case EightHourHistory:
			if (this.displaySwitch8HourHistory.getAttribute("class").equalsIgnoreCase("switch")) {
				if (turnOn) {
					this.displaySwitch8HourHistory.click();
				}
			} else if (this.displaySwitch8HourHistory.getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					this.displaySwitch8HourHistory.click();
				}
			}
			break;
		case ConcentrationChart:
			if (this.displaySwitchConcentrationChart.getAttribute("class").equalsIgnoreCase("switch")) {
				if (turnOn) {
					this.displaySwitchConcentrationChart.click();
				}
			} else if (this.displaySwitchConcentrationChart.getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					this.displaySwitchConcentrationChart.click();
				}
			}
			break;
		case FOVs:
			if (this.displaySwitchFovs.getAttribute("class").equalsIgnoreCase("switch")) {
				if (turnOn) {
					this.displaySwitchFovs.click();
				}
			} else if (this.displaySwitchFovs.getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					this.displaySwitchFovs.click();
				}
			}
			break;
		case Indications:
			if (this.displaySwitchIndications.getAttribute("class").equalsIgnoreCase("switch")) {
				if (turnOn) {
					this.displaySwitchIndications.click();
				}
			} else if (this.displaySwitchIndications.getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					this.displaySwitchIndications.click();
				}
			}
			break;
		case IsotopicAnalysis:
			if (this.displaySwitchIsotopicAnalysis.getAttribute("class").equalsIgnoreCase("switch")) {
				if (turnOn) {
					this.displaySwitchIsotopicAnalysis.click();
				}
			} else if (this.displaySwitchIsotopicAnalysis.getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					this.displaySwitchIsotopicAnalysis.click();
				}
			}
			break;
		case Lisas:
			if (this.displaySwitchLisas.getAttribute("class").equalsIgnoreCase("switch")) {
				if (turnOn) {
					this.displaySwitchLisas.click();
				}
			} else if (this.displaySwitchLisas.getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					this.displaySwitchLisas.click();
				}
			}
			break;
		case Notes:
			if (this.displaySwitchNotes.getAttribute("class").equalsIgnoreCase("switch")) {
				if (turnOn) {
					this.displaySwitchNotes.click();
				}
			} else if (this.displaySwitchNotes.getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					this.displaySwitchNotes.click();
				}
			}
			break;
		case WindRose:
			if (this.displaySwitchWindrose.getAttribute("class").equalsIgnoreCase("switch")) {
				if (turnOn) {
					this.displaySwitchWindrose.click();
				}
			} else if (this.displaySwitchWindrose.getAttribute("class").equalsIgnoreCase("switch on")) {
				if (!turnOn) {
					this.displaySwitchWindrose.click();
				}
			}
			break;
		default:
			throw new IllegalArgumentException("Display switch type unknown and not currently handled.");
		}
	}

	/**
	 * Verifies that the page is done Connecting and Connecting element is
	 * hidden.
	 */
	public void waitForConnectionComplete() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return divNoAnalyzer.getAttribute("class").equalsIgnoreCase("cssFade ng-hide");
			}
		});
	}

	/**
	 * Waits for the survey duration warning dialog to close.
	 */
	public void waitForSurveyDurationWarningDialogToClose() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return divSurveyDurationWarning.getAttribute("class").equalsIgnoreCase("cssFade ng-hide");
			}
		});
	}

	/**
	 * Waits for the survey start warning dialog to close.
	 */
	public void waitForSurveyFailedToStartDialogToClose() {
		(new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return divSurveyStartWarning.getAttribute("class").equalsIgnoreCase("cssFade ng-hide");
			}
		});
	}
}
