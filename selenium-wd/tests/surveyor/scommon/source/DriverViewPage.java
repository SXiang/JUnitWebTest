package surveyor.scommon.source;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.TestSetup;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

public class DriverViewPage extends SurveyorBasePage {
	private static final String VIRTUALEARTH_NET_BRANDING_LOGO = "https://dev.virtualearth.net/Branding/logo_powered_by.png";
	private static final String IMG_DATA_DATA_0 = "return imgData.data[0];";
	private static final String IMG_DATA_DATA_1 = "return imgData.data[1];";
	private static final String IMG_DATA_DATA_2 = "return imgData.data[2];";
	private static final String[] GreenRGBPixels = new String[] {"21", "255", "0"};
	private static final String[] RedRGBPixels = new String[] {"255", "2", "0"};

	public static final String STATUS_PRESSURE_CANVAS_CTX = "test_ctx = $(\"#status_pressure_canvas\")[0].getContext('2d');";
	public static final String STATUS_WARM_CANVAS_CTX = "test_ctx = $(\"#status_warm_canvas\")[0].getContext('2d');";
	public static final String STATUS_TEMP_CANVAS_CTX = "test_ctx = $(\"#status_temp_canvas\")[0].getContext('2d');";
	public static final String STATUS_FLOW_CANVAS_CTX = "test_ctx = $(\"#status_flow_canvas\")[0].getContext('2d');";
	public static final String STATUS_GPS_CANVAS_CTX = "test_ctx = $(\"#status_gps_canvas\")[0].getContext('2d');";
	public static final String STATUS_ANEMOMETER_CANVAS_CTX = "test_ctx = $(\"#status_anemometer_canvas\")[0].getContext('2d');";
	private static final String CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA = "centerX = 40;centerY = 40;fontY = 12;paddingY = 5;rectWidth = 1;rectHeight = 1;var imgData=test_ctx.getImageData(centerX,centerY-fontY-paddingY,rectWidth,rectHeight);";

	public static final String STRURLPath = "/Live/Driver?address=https%3A%2F%2Flocalhost&port=5600&serialNumber=" + TestSetup.TEST_ANALYZER_SERIAL_NUMBER;
	public static final String STRPageTitle = "Live";
	public static final String STRPageContentText = "Map View";	

	public enum SurveyTime {
		Day,
		Night
	}

	public enum SolarRadiation {
		Overcast,
		Moderate,
		Strong
	}
	
	public enum Wind {
		Calm,
		Light,
		Strong
	}

	public enum SurveyType {
		Standard,
		RapidResponse,
		Operator,
		Manual
	}

	public enum DisplaySwitchType {
		EightHourHistory,
		WindRose,
		ConcentrationChart,
		Notes,
		IsotopicAnalysis,
		Indications,
		Lisas,
		FOVs
	}

	public enum MapSwitchType {
		Satellite,
		Map
	}

	public enum GisSwitchType {
		MaterialTypeCopper,
		MaterialTypeUnprotectedSteel,
		MaterialTypeProtectedSteel,
		MaterialTypeCastIron,
		MaterialTypeOtherPlastic,
		MaterialTypePEPlastic,
		UseAllPipes,
		BoundariesDistrictPlat,
		BoundariesDistrict,
		UseAllBoundaries
	}
	
	private Map<String, String> data;
    private int timeout = 15;

    /*
	@FindBy(id = "tag")
	@CacheLookup
	private WebElement labelTag;
	
	@FindBy(id = "surveyMode")
	@CacheLookup
	private WebElement labelSurveyMode;
	
	@FindBy(id = "headerInfoStatus")
	@CacheLookup
	private WebElement labelSurveyStatus;
	
	@FindBy(id = "driver")
	@CacheLookup
	private WebElement labelDriver;
	
	@FindBy(id = "stabilityClass")
	@CacheLookup
	private WebElement labelStabilityClass;
	
	@FindBy(id = "timeElapsed")
	@CacheLookup
	private WebElement labelTimeElapsed;
	
	@FindBy(id = "timeRemaining")
	@CacheLookup
	private WebElement labelTimeRemaining;
	
	@FindBy(id = "surveyorAnalyzer")
	@CacheLookup
	private WebElement labelSurveyor;
	
	@FindBy(id = "zoomLevel")
	@CacheLookup
	private WebElement labelZoomLevel;
    */
    
    @FindBy(id = "header_info_box_upper_left")
    @CacheLookup
    private WebElement divHeaderInfoBox;
    
    @FindBy(id = "no_analyzer")
    @CacheLookup
    private WebElement divNoAnalyzer;

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
    
    @FindBy(id = "bottom_button_position_follow")
    @CacheLookup
    private WebElement positionButton;
    
    @FindBy(id = "bottom_button_curtain_view")
    @CacheLookup
    private WebElement curtainButton;
    
    @FindBy(id = "bottom_button_status")
    @CacheLookup
    private WebElement statusButton;

    @FindBy(id = "mode_start_survey")
    @CacheLookup
    private WebElement startSurveyButton;

    @FindBy(id = "mode_start_eq_survey")
    @CacheLookup
    private WebElement startEQSurveyButton;
    
    @FindBy(id = "mode_shutdown_analyzer")
    @CacheLookup
    private WebElement systemShutdownButton;

    @FindBy(id = "mode_stop_survey")
    @CacheLookup
    private WebElement stopSurveyButton;

    @FindBy(id = "mode_start_isotopic_capture")
    @CacheLookup
    private WebElement startIsotopicCaptureButton;

    @FindBy(id = "mode_start_reference")
    @CacheLookup
    private WebElement refBottleMeasButton;

    @FindBy(id = "display_switch_8hour_history")
    @CacheLookup
    private WebElement displaySwitch8HourHistory;

    @FindBy(id = "display_switch_windrose")
    @CacheLookup
    private WebElement displaySwitchWindrose;

    @FindBy(id = "display_switch_concentration_chart")
    @CacheLookup
    private WebElement displaySwitchConcentrationChart;

    @FindBy(id = "display_switch_notes")
    @CacheLookup
    private WebElement displaySwitchNotes;

    @FindBy(id = "display_switch_isotopic_analysis")
    @CacheLookup
    private WebElement displaySwitchIsotopicAnalysis;

    @FindBy(id = "display_switch_indications")
    @CacheLookup
    private WebElement displaySwitchIndications;

    @FindBy(id = "display_switch_lisas")
    @CacheLookup
    private WebElement displaySwitchLisas;

    @FindBy(id = "display_switch_fovs")
    @CacheLookup
    private WebElement displaySwitchFovs;

    @FindBy(id = "map_switch_satellite")
    @CacheLookup
    private WebElement mapSwitchSatellite;

    @FindBy(id = "map_switch_map")
    @CacheLookup
    private WebElement mapSwitchMap;

    @FindBy(id = "d08fc87f-f979-4131-92a9-3d82f37f4bba")
    @CacheLookup
    private WebElement materialTypeCopper;

    @FindBy(id = "f3955e82-dd13-4842-84f7-502bcda6b57a")
    @CacheLookup
    private WebElement materialTypeUnprotectedSteel;

    @FindBy(id = "44353e68-0694-4f05-85cb-84d753ea278c")
    @CacheLookup
    private WebElement materialTypeProtectedSteel;

    @FindBy(id = "96caf1f5-d5c5-461d-9ce3-d210c20a1bb0")
    @CacheLookup
    private WebElement materialTypeCastIron;

    @FindBy(id = "ad701312-c470-482a-be45-ef37770e2ce6")
    @CacheLookup
    private WebElement materialTypeOtherPlastic;

    @FindBy(id = "f14735de-6c9b-4423-8533-f243a7fe4e90")
    @CacheLookup
    private WebElement materialTypePEPlastic;

    @FindBy(id = "gis_switch_all_pipes")
    @CacheLookup
    private WebElement useAllPipes;

    @FindBy(id = "551cb7c0-005b-4e3e-bfae-d19da0ed7efe")
    @CacheLookup
    private WebElement boundariesDistrictPlat;

    @FindBy(id = "024249ae-374b-4f6f-bd87-e8fdcacb48e1")
    @CacheLookup
    private WebElement boundariesDistrict;

    @FindBy(id = "gis_switch_all_boundaries")
    @CacheLookup
    private WebElement useAllBoundaries;    
    
    @FindBy(id = "btn_cancel_annotation")
    @CacheLookup
    private WebElement cancel;

    @FindBy(css = "a[href='http://openlayers.org/']")
    @CacheLookup
    private WebElement imageData;

    @FindBy(id = "survey_wind_calm")
    @CacheLookup
    private WebElement calm;
    
    @FindBy(id = "survey_time_day")
    @CacheLookup
    private WebElement day;

    @FindBy(id = "btn_delete_annotation")
    @CacheLookup
    private WebElement delete;

    @FindBy(css = "button[title='Attributions']")
    @CacheLookup
    private WebElement i;

    @FindBy(id = "survey_wind_light")
    @CacheLookup
    private WebElement light;

    @FindBy(id = "survey_type_manual")
    @CacheLookup
    private WebElement manual;

    @FindBy(id = "manual_min_amplitude")
    @CacheLookup
    private WebElement minAmp;

    @FindBy(id = "survey_radiation_moderate")
    @CacheLookup
    private WebElement moderate;

    @FindBy(id = "survey_time_night")
    @CacheLookup
    private WebElement night;

    @FindBy(id = "btn_survey_warning_ok")
    @CacheLookup
    private WebElement ok1;

    @FindBy(id = "btn_survey_start_warning_ok")
    @CacheLookup
    private WebElement ok2;

    @FindBy(id = "survey_type_operator")
    @CacheLookup
    private WebElement operator;

    @FindBy(id = "survey_radiation_overcast")
    @CacheLookup
    private WebElement overcast;

    @FindBy(id = "survey_type_rapid_response")
    @CacheLookup
    private WebElement rapidResponse;

    @FindBy(id = "btn_save_annotation")
    @CacheLookup
    private WebElement save;

    @FindBy(id = "survey_type_standard")
    @CacheLookup
    private WebElement standard;

    @FindBy(id = "survey_start_survey")
    @CacheLookup
    private WebElement startSurvey;

    @FindBy(id = "survey_radiation_strong")
    @CacheLookup
    private WebElement strong1;

    @FindBy(id = "survey_wind_strong")
    @CacheLookup
    private WebElement strong2;

    @FindBy(css = "a[class='ol-attribution-bing-tos']")
    @CacheLookup
    private WebElement termsOfUse;

    @FindBy(id = "anno_input")
    @CacheLookup
    private WebElement x50CharsRemainingSaveCancel;

    @FindBy(id = "survey_modal_tag")
    @CacheLookup
    private WebElement tagSurvey;

	/**
	 * @param driver
	 * @param baseURL
	 * @param testSetup
	 */
	public DriverViewPage(WebDriver driver, TestSetup testSetup, String baseURL) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);
		
		System.out.println("\nThe Home Page URL is: " + this.strPageURL);
	}
	
	public DriverViewPage clickModeButton() {
		this.modeButton.click();
		return this;
	}

	public DriverViewPage hideModeMenu() {
		clickModeButton();
		return this;
	}

	public DriverViewPage clickMapButton() {
		this.mapButton.click();
		return this;
	}

	public DriverViewPage hideMapMenu() {
		clickMapButton();
		return this;
	}

	public DriverViewPage clickGisButton() {
		this.gisButton.click();
		return this;
	}

	public DriverViewPage hideGisMenu() {
		clickGisButton();
		return this;
	}

	public DriverViewPage clickDisplayButton() {
		this.displayButton.click();
		return this;
	}

	public DriverViewPage hideDisplayMenu() {
		clickDisplayButton();
		return this;
	}

	public DriverViewPage clickPositionButton() {
		this.positionButton.click();
		return this;
	}
	
	public DriverViewPage clickHeaderInfoBox() {
		this.divHeaderInfoBox.click();
		return this;
	}

	public boolean isPositionButtonSelected() {
		return this.positionButton.getAttribute("class").equalsIgnoreCase("bottom_button standard_icon on");
	}

	public WebElement getStartSurveyButton() {
		return this.startSurveyButton;
	}

	public boolean isStartSurveyButtonEnabled() {
		return this.startSurveyButton.getAttribute("class").equalsIgnoreCase("trigger_button on");
	}

	public WebElement getStartEQSurveyButton() {
		return this.startEQSurveyButton;
	}

	public boolean isStartEQSurveyButtonEnabled() {
		return this.startEQSurveyButton.getAttribute("class").equalsIgnoreCase("trigger_button on");
	}

	public WebElement getSystemShutdownButton() {
		return this.systemShutdownButton;
	}

	public boolean isSystemShutdownButtonEnabled() {
		return this.systemShutdownButton.getAttribute("class").equalsIgnoreCase("trigger_button on");
	}

	public WebElement getStopDrivingSurveyButton() {
		return this.stopSurveyButton;
	}

	public boolean isStopDrivingSurveyButtonEnabled() {
		return this.stopSurveyButton.getAttribute("class").equalsIgnoreCase("trigger_button on");
	}

	public WebElement getStartIsotopicCaptureButton() {
		return this.startIsotopicCaptureButton;
	}

	public boolean isStartIsotopicCaptureButtonEnabled() {
		return this.startIsotopicCaptureButton.getAttribute("class").equalsIgnoreCase("trigger_button on");
	}

	public WebElement getRefBottleMeasButton() {
		return this.refBottleMeasButton;
	}

	public boolean isRefBottleMeasButtonEnabled() {
		return this.refBottleMeasButton.getAttribute("class").equalsIgnoreCase("trigger_button on");
	}

	public String getTagLabelText() {
		return driver.findElement(By.id("tag")).getText();
	}

	public String getSurveyModeLabelText() {
		return driver.findElement(By.id("surveyMode")).getText();
	}
	
	public String getSurveyStatusLabelText() {
		return driver.findElement(By.id("headerInfoStatus")).getText();
	}
	
	public String getDriverLabelText() {
		return driver.findElement(By.id("driver")).getText();
	}
	
	public String getStabilityClassLabelText() {
		return driver.findElement(By.id("stabilityClass")).getText();
	}
	
	public String getTimeElapsedLabelText() {
		return driver.findElement(By.id("timeElapsed")).getText();
	}
	
	public String getTimeRemainingLabelText() {
		return driver.findElement(By.id("timeRemaining")).getText();
	}
	
	public String getSurveyorLabelText() {
		return driver.findElement(By.id("surveyorAnalyzer")).getText();
	}
	
	public String getZoomLevelLabelText() {
		return driver.findElement(By.id("zoomLevel")).getText();
	}

	public boolean isGisSwitchOn(GisSwitchType switchType) throws IllegalArgumentException {
		boolean isSelected = false;
		
		switch (switchType)
		{
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
				isSelected = this.materialTypeCopper.getAttribute("class").equalsIgnoreCase("switch material_radio on");				
				break;
			case MaterialTypeOtherPlastic:
				isSelected = this.materialTypeCopper.getAttribute("class").equalsIgnoreCase("switch material_radio on");				
				break;
			case MaterialTypePEPlastic:
				isSelected = this.materialTypeCopper.getAttribute("class").equalsIgnoreCase("switch material_radio on");				
				break;
			case MaterialTypeProtectedSteel:
				isSelected = this.materialTypeCopper.getAttribute("class").equalsIgnoreCase("switch material_radio on");				
				break;
			case MaterialTypeUnprotectedSteel:
				isSelected = this.materialTypeCopper.getAttribute("class").equalsIgnoreCase("switch material_radio on");				
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
		
		switch (switchType)
		{
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
				isSelected = this.materialTypeCopper.getAttribute("class").equalsIgnoreCase("switch material_radio");				
				break;
			case MaterialTypeOtherPlastic:
				isSelected = this.materialTypeCopper.getAttribute("class").equalsIgnoreCase("switch material_radio");				
				break;
			case MaterialTypePEPlastic:
				isSelected = this.materialTypeCopper.getAttribute("class").equalsIgnoreCase("switch material_radio");				
				break;
			case MaterialTypeProtectedSteel:
				isSelected = this.materialTypeCopper.getAttribute("class").equalsIgnoreCase("switch material_radio");				
				break;
			case MaterialTypeUnprotectedSteel:
				isSelected = this.materialTypeCopper.getAttribute("class").equalsIgnoreCase("switch material_radio");				
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

	public void toggleMapSwitch(MapSwitchType switchType, boolean turnOn) {
		switch (switchType)
		{
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

	public boolean verifyLoadedMap(MapSwitchType switchType) {
		
		// Check map logo to ensure BingMap is getting loaded.
		Object mapLogo = ((JavascriptExecutor)driver).executeScript("src = mapLayer.getSource();return src.getLogo();");
		if (!mapLogo.toString().equalsIgnoreCase(VIRTUALEARTH_NET_BRANDING_LOGO)) {
			return false;
		}
		
		// Checks the number of attributions returned to ensure correct map is loaded.
		// Satellite map has 29 attributions. Map map has 7 attributions.
		Object attributionsLength = ((JavascriptExecutor)driver).executeScript("src = mapLayer.getSource();return src.getAttributions().length;");
		
		switch (switchType)
		{
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
		
		switch (switchType)
		{
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
		
		switch (switchType)
		{
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

	public boolean isDisplaySwitchOff(DisplaySwitchType switchType) throws IllegalArgumentException {
		boolean isSelected = false;
		
		switch (switchType)
		{
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
		
		switch (switchType)
		{
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

	public DriverViewPage hidePositionMenu() {
		clickPositionButton();
		return this;
	}

	public boolean isPositionButtonGreen() {
		return this.positionButton.getAttribute("class").equalsIgnoreCase("bottom_button standard_icon on");
	}

	public boolean isPressureButtonRed() {
		Object pixelRed = ((JavascriptExecutor)driver).executeScript(STATUS_PRESSURE_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor)driver).executeScript(STATUS_PRESSURE_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor)driver).executeScript(STATUS_PRESSURE_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(RedRGBPixels[0]) && pixelGreen.toString().equals(RedRGBPixels[1]) && pixelBlue.toString().equals(RedRGBPixels[2]);
	}

	public boolean isPressureButtonGreen() {
		Object pixelRed = ((JavascriptExecutor)driver).executeScript(STATUS_PRESSURE_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor)driver).executeScript(STATUS_PRESSURE_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor)driver).executeScript(STATUS_PRESSURE_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(GreenRGBPixels[0]) && pixelGreen.toString().equals(GreenRGBPixels[1]) && pixelBlue.toString().equals(GreenRGBPixels[2]);
	}

	public boolean isHBTempButtonRed() {
		Object pixelRed = ((JavascriptExecutor)driver).executeScript(STATUS_WARM_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor)driver).executeScript(STATUS_WARM_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor)driver).executeScript(STATUS_WARM_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(RedRGBPixels[0]) && pixelGreen.toString().equals(RedRGBPixels[1]) && pixelBlue.toString().equals(RedRGBPixels[2]);
	}

	public boolean isHBTempButtonGreen() {
		Object pixelRed = ((JavascriptExecutor)driver).executeScript(STATUS_WARM_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor)driver).executeScript(STATUS_WARM_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor)driver).executeScript(STATUS_WARM_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(GreenRGBPixels[0]) && pixelGreen.toString().equals(GreenRGBPixels[1]) && pixelBlue.toString().equals(GreenRGBPixels[2]);
	}

	public boolean isWBTempButtonRed() {
		Object pixelRed = ((JavascriptExecutor)driver).executeScript(STATUS_TEMP_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor)driver).executeScript(STATUS_TEMP_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor)driver).executeScript(STATUS_TEMP_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(RedRGBPixels[0]) && pixelGreen.toString().equals(RedRGBPixels[1]) && pixelBlue.toString().equals(RedRGBPixels[2]);
	}

	public boolean isWBTempButtonGreen() {
		Object pixelRed = ((JavascriptExecutor)driver).executeScript(STATUS_TEMP_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor)driver).executeScript(STATUS_TEMP_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor)driver).executeScript(STATUS_TEMP_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(GreenRGBPixels[0]) && pixelGreen.toString().equals(GreenRGBPixels[1]) && pixelBlue.toString().equals(GreenRGBPixels[2]);
	}

	public boolean isFlowButtonRed() {
		Object pixelRed = ((JavascriptExecutor)driver).executeScript(STATUS_FLOW_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor)driver).executeScript(STATUS_FLOW_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor)driver).executeScript(STATUS_FLOW_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(RedRGBPixels[0]) && pixelGreen.toString().equals(RedRGBPixels[1]) && pixelBlue.toString().equals(RedRGBPixels[2]);
	}

	public boolean isFlowButtonGreen() {
		Object pixelRed = ((JavascriptExecutor)driver).executeScript(STATUS_FLOW_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor)driver).executeScript(STATUS_FLOW_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor)driver).executeScript(STATUS_FLOW_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(GreenRGBPixels[0]) && pixelGreen.toString().equals(GreenRGBPixels[1]) && pixelBlue.toString().equals(GreenRGBPixels[2]);
	}

	public boolean isGPSButtonRed() {
		Object pixelRed = ((JavascriptExecutor)driver).executeScript(STATUS_GPS_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor)driver).executeScript(STATUS_GPS_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor)driver).executeScript(STATUS_GPS_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(RedRGBPixels[0]) && pixelGreen.toString().equals(RedRGBPixels[1]) && pixelBlue.toString().equals(RedRGBPixels[2]);
	}

	public boolean isGPSButtonGreen() {
		Object pixelRed = ((JavascriptExecutor)driver).executeScript(STATUS_GPS_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor)driver).executeScript(STATUS_GPS_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor)driver).executeScript(STATUS_GPS_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(GreenRGBPixels[0]) && pixelGreen.toString().equals(GreenRGBPixels[1]) && pixelBlue.toString().equals(GreenRGBPixels[2]);
	}

	public boolean isAnemometerButtonRed() {
		Object pixelRed = ((JavascriptExecutor)driver).executeScript(STATUS_ANEMOMETER_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor)driver).executeScript(STATUS_ANEMOMETER_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor)driver).executeScript(STATUS_ANEMOMETER_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(RedRGBPixels[0]) && pixelGreen.toString().equals(RedRGBPixels[1]) && pixelBlue.toString().equals(RedRGBPixels[2]);
	}

	public boolean isAnemometerButtonGreen() {
		Object pixelRed = ((JavascriptExecutor)driver).executeScript(STATUS_ANEMOMETER_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_0);
		Object pixelGreen = ((JavascriptExecutor)driver).executeScript(STATUS_ANEMOMETER_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_1);
		Object pixelBlue = ((JavascriptExecutor)driver).executeScript(STATUS_ANEMOMETER_CANVAS_CTX + CIRCLE_BACK_COLOR_1PX_GET_IMAGE_DATA + IMG_DATA_DATA_2);
		return pixelRed.toString().equals(GreenRGBPixels[0]) && pixelGreen.toString().equals(GreenRGBPixels[1]) && pixelBlue.toString().equals(GreenRGBPixels[2]);
	}

	public DriverViewPage clickCurtainButton() {
		this.curtainButton.click();
		return this;
	}

	public DriverViewPage hideCurtainMenu() {
		clickCurtainButton();
		return this;
	}

	public DriverViewPage clickStatusButton() {
		this.statusButton.click();
		return this;
	}

	public boolean isStatusButtonGreen() {
		return this.statusButton.getAttribute("class").equalsIgnoreCase("bottom_button standard_icon");
	}

	public boolean isStatusButtonOpen() {
		return this.statusButton.getAttribute("class").equalsIgnoreCase("bottom_button standard_icon open");
	}

	public boolean isStatusButtonRed() {
		return this.statusButton.getAttribute("class").equalsIgnoreCase("bottom_button standard_icon trouble");
	}

    /**
     * Click on Calm Button.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickCalmButton() {
        calm.click();
        return this;
    }

    /**
     * Click on Cancel Button.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickCancelButton() {
        cancel.click();
        return this;
    }

    /**
     * Click on image data Link.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickImageDataLink() {
        imageData.click();
        return this;
    }

    /**
     * Click on Day Button.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickDayButton() {
        day.click();
        return this;
    }

    /**
     * Click on Delete Button.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickDeleteButton() {
        delete.click();
        return this;
    }

    /**
     * Click on I Button.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickIButton() {
        i.click();
        return this;
    }

    /**
     * Click on Light Button.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickLightButton() {
        light.click();
        return this;
    }

    /**
     * Click on Manual Button.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickManualButton() {
        manual.click();
        return this;
    }

    /**
     * Click on Moderate Button.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickModerateButton() {
        moderate.click();
        return this;
    }

    /**
     * Click on Night Button.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickNightButton() {
        night.click();
        return this;
    }

    /**
     * Click on Ok Button.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickOk1Button() {
        ok1.click();
        return this;
    }

    /**
     * Click on Ok Button.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickOk2Button() {
        ok2.click();
        return this;
    }

    /**
     * Click on Operator Button.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickOperatorButton() {
        operator.click();
        return this;
    }

    /**
     * Click on Overcast Button.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickOvercastButton() {
        overcast.click();
        return this;
    }

    /**
     * Click on Rapid Response Button.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickRapidResponseButton() {
        rapidResponse.click();
        return this;
    }

    /**
     * Click on Save Button.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickSaveButton() {
        save.click();
        return this;
    }

    /**
     * Click on Standard Button.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickStandardButton() {
        standard.click();
        return this;
    }

    /**
     * Click on Start Survey Button inside the Survey modal dialog.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickStartSurvey() {
        startSurvey.click();
        return this;
    }

    /**
     * Click on Start Survey Button in the Driver view page to open the modal Start Survey dialog.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickStartSurveyButton() {
        this.startSurveyButton.click();
        return this;
    }

    
    /**
     * Click on Strong Button.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickStrong1Button() {
        strong1.click();
        return this;
    }

    /**
     * Click on Strong Button.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickStrong2Button() {
        strong2.click();
        return this;
    }

    /**
     * Click on Terms Of Use Link.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickTermsOfUseLink() {
        termsOfUse.click();
        return this;
    }

    /**
     * Set default value to Min Amp Text field.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage setMinAmpTextField() {
        return setMinAmpTextField(data.get("MIN_AMP"));
    }

    /**
     * Set value to Min Amp Text field.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage setMinAmpTextField(String minAmpValue) {
        minAmp.sendKeys(minAmpValue);
        return this;
    }

    /**
     * Set default value to tag field.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage setTagSurveyTextField() {
        return setTagSurveyTextField(data.get("TAG_SURVEY"));
    }

    /**
     * Set value to Tag field.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage setTagSurveyTextField(String tag) {
        tagSurvey.sendKeys(tag);
        return this;
    }

    /**
     * Starts a survey with the specified values.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage startDrivingSurvey(String tag, SurveyTime surveyTime, SolarRadiation solarRadiation, Wind wind, SurveyType surveyType) {
    	this.clickStartSurveyButton();
    	this.waitForPageToLoad();
    	
    	this.setTagSurveyTextField(tag);
    	switch (surveyTime) {
	    	case Day:
	    		this.clickDayButton();
	    		break;
			case Night:
				this.clickNightButton();
				break;
			default:
				break;
    	}
    	
    	switch (solarRadiation) {
			case Moderate:
				this.clickModerateButton();
				break;
			case Overcast:
				this.clickOvercastButton();
				break;
			case Strong:
				this.clickStrong1Button();
				break;
			default:
				break;	    	
    	}
    	
    	switch (wind) {
			case Calm:
				this.clickCalmButton();
				break;
			case Light:
				this.clickLightButton();
				break;
			case Strong:
				this.clickStrong2Button();
				break;
			default:
				break;    	
    	}
    	
    	switch (surveyType) {
			case Manual:
				this.clickManualButton();
				break;
			case Operator:
				this.clickOperatorButton();
				break;
			case RapidResponse:
				this.clickRapidResponseButton();
				break;
			case Standard:
				this.clickStandardButton();
				break;
			default:
				break;
    	}
    	
    	this.clickStartSurvey();
    	this.waitForPageToLoad();
    	
    	return this;
    }
    
    /**
     * Verify that current page URL matches the expected URL.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage verifyPageUrl() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().contains(STRURLPath);
            }
        });
        return this;
    }

    /**
     * Verify that the page loaded completely.
     */
    public void waitForPageLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STRPageContentText);
            }
        });
    }
    
    /**
     * Verifies that the page is done Connecting and Connecting element is hidden.
     */
    public void waitForConnectionComplete() {
        (new WebDriverWait(driver, timeout * 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return divNoAnalyzer.getAttribute("class").equalsIgnoreCase("cssFade ng-hide");
            }
        });
    }
}
