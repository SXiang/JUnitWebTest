package surveyor.scommon.source;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.TestSetup;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

public class DriverViewPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Live/Driver?address=https%3A%2F%2Flocalhost&port=5600&serialNumber=" + TestSetup.TEST_ANALYZER_SERIAL_NUMBER;
	public static final String STRPageTitle = "Live";
	public static final String STRPageContentText = "Map View";	

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

    @FindBy(id = "mode_shutdown_analyzer")
    @CacheLookup
    private WebElement systemShutdownButton;
    
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
    private WebElement xTagSurveyTimeDayNight;

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

	public boolean isPositionButtonSelected() {
		return this.positionButton.getAttribute("class").equalsIgnoreCase("bottom_button standard_icon on");
	}
		
	public WebElement getSystemShutdownButton() {
		return this.systemShutdownButton;
	}

	public boolean isSystemShutdownButtonEnabled() {
		return this.systemShutdownButton.getAttribute("class").equalsIgnoreCase("trigger_button on");
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
     * Click on Start Survey Button.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage clickStartSurveyButton() {
        startSurvey.click();
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
     * Set default value to X 50 Chars Remaining Save Cancel Delete Textarea field.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage setX50CharsRemainingSaveCancelTextareaField() {
        return setX50CharsRemainingSaveCancelTextareaField(data.get("X_50_CHARS__REMAINING_SAVE_CANCEL"));
    }

    /**
     * Set value to X 50 Chars Remaining Save Cancel Delete Textarea field.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage setX50CharsRemainingSaveCancelTextareaField(String x50CharsRemainingSaveCancelValue) {
        x50CharsRemainingSaveCancel.sendKeys(x50CharsRemainingSaveCancelValue);
        return this;
    }

    /**
     * Set default value to X Tag Survey Time Day Night Solar Radiation Overcast Moderate Strong Cloud Cover 50 50 Wind Calm Light Strong Survey Type Standard Rapid Response Operator Manual Min Amp Start Survey Text field.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage setXTagSurveyTimeDayNightTextField() {
        return setXTagSurveyTimeDayNightTextField(data.get("X_TAG_SURVEY_TIME_DAY_NIGHT"));
    }

    /**
     * Set value to X Tag Survey Time Day Night Solar Radiation Overcast Moderate Strong Cloud Cover 50 50 Wind Calm Light Strong Survey Type Standard Rapid Response Operator Manual Min Amp Start Survey Text field.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage setXTagSurveyTimeDayNightTextField(String xTagSurveyTimeDayNightValue) {
        xTagSurveyTimeDayNight.sendKeys(xTagSurveyTimeDayNightValue);
        return this;
    }

    /**
     * Submit the form to target page.
     *
     * @return the DriverViewPage class instance.
     */
    public DriverViewPage submit() {
        clickSaveButton();
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
     *
     * @return the DriverViewPage class instance.
     */
    public void waitForPageLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STRPageContentText);
            }
        });
    }
}
