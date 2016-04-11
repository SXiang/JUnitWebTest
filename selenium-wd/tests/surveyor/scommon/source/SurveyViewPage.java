package surveyor.scommon.source;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.Log;
import common.source.OLMapUtility;
import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SurveyViewPage extends BaseMapViewPage {
	private static final String SURVEY_INFO_ANALYZER_LABEL_XPATH = "//*[@id='header_info_historical']/div[5]";
	private static final String SURVEY_INFO_SURVEYOR_LABEL_XPATH = "//*[@id='header_info_historical']/div[6]";
	private static final String SURVEY_INFO_END_TIME_LABEL_XPATH = "//*[@id='header_info_historical']/div[8]";
	private static final String SURVEY_INFO_START_TIME_LABEL_XPATH = "//*[@id='header_info_historical']/div[7]";
	private static final String SURVEY_INFO_STABILITY_CLASS_LABEL_XPATH = "//*[@id='header_info_historical']/div[4]";
	private static final String SURVEY_INFO_DRIVER_LABEL_XPATH = "//*[@id='header_info_historical']/div[3]";
	private static final String SURVEY_INFO_MODE_LABEL_XPATH = "//*[@id='header_info_historical']/div[2]";
	private static final String SURVEY_INFO_TAG_LABEL_XPATH = "//*[@id='header_info_historical']/div[1]";
	private static final int ASSETS_ZOOM_LEVEL_LOWER_BOUND = 17;
	public static final String STRURLPath = "/Live/Survey/";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.Constant_Survey);
	public static final String STRPageContentText = "Map View";
	
    @FindBy(id = "survey_wind_calm")
    @CacheLookup
    private WebElement calm;

    @FindBy(id = "btn_cancel_annotation")
    @CacheLookup
    private WebElement cancel;

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
    
    @FindBy(how = How.XPATH, using = SURVEY_INFO_TAG_LABEL_XPATH)
	private WebElement labelTag;

    @FindBy(how = How.XPATH, using = SURVEY_INFO_MODE_LABEL_XPATH)
	private WebElement labelMode;

    @FindBy(how = How.XPATH, using = SURVEY_INFO_DRIVER_LABEL_XPATH)
	private WebElement labelDriver;

    @FindBy(how = How.XPATH, using = SURVEY_INFO_STABILITY_CLASS_LABEL_XPATH)
	private WebElement labelStabilityClass;

    @FindBy(how = How.XPATH, using = SURVEY_INFO_ANALYZER_LABEL_XPATH)
	private WebElement labelAnalyzer;

    @FindBy(how = How.XPATH, using = SURVEY_INFO_SURVEYOR_LABEL_XPATH)
	private WebElement labelSurveyor;

    @FindBy(how = How.XPATH, using = SURVEY_INFO_START_TIME_LABEL_XPATH)
	private WebElement labelStartTime;

    @FindBy(how = How.XPATH, using = SURVEY_INFO_END_TIME_LABEL_XPATH)
	private WebElement labelEndTime;
    
    // Survey ID used for opening the specified survey page.
    private String surveyId;    
    private By blockedUINotShown = By.cssSelector("#blocked_ui.ng-hide");

	/**
	 * @param driver
	 * @param baseURL
	 * @param testSetup
	 */
	public SurveyViewPage(WebDriver driver, TestSetup testSetup, String baseURL) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);

		Log.info("\nThe SurveyView Page URL is: " + this.strPageURL);
	}

	@Override
	public void open() {
		driver.get(strPageURL + getSurveyId());
		this.waitForPageLoad();
	}
	
	public boolean checkIfAtSurveyViewPage() {
		if (driver.getTitle().equalsIgnoreCase(STRPageTitle))
			return true;
		
		return false;
	}

    /**
     * Click on Calm Button.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage clickCalmButton() {
        calm.click();
        return this;
    }

    /**
     * Click on Cancel Button.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage clickCancelButton() {
        cancel.click();
        return this;
    }

    /**
     * Click on Day Button.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage clickDayButton() {
        day.click();
        return this;
    }

    /**
     * Click on Delete Button.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage clickDeleteButton() {
        delete.click();
        return this;
    }

    /**
     * Click on I Button.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage clickIButton() {
        i.click();
        return this;
    }

    /**
     * Click on Light Button.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage clickLightButton() {
        light.click();
        return this;
    }

    /**
     * Click on Manual Button.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage clickManualButton() {
        manual.click();
        return this;
    }

    /**
     * Click on Moderate Button.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage clickModerateButton() {
        moderate.click();
        return this;
    }

    /**
     * Click on Night Button.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage clickNightButton() {
        night.click();
        return this;
    }

    /**
     * Click on Ok Button.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage clickOk1Button() {
        ok1.click();
        return this;
    }

    /**
     * Click on Ok Button.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage clickOk2Button() {
        ok2.click();
        return this;
    }

    /**
     * Click on Operator Button.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage clickOperatorButton() {
        operator.click();
        return this;
    }

    /**
     * Click on Overcast Button.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage clickOvercastButton() {
        overcast.click();
        return this;
    }

    /**
     * Click on Rapid Response Button.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage clickRapidResponseButton() {
        rapidResponse.click();
        return this;
    }

    /**
     * Click on Save Button.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage clickSaveButton() {
        save.click();
        return this;
    }

    /**
     * Click on Standard Button.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage clickStandardButton() {
        standard.click();
        return this;
    }

    /**
     * Click on Start Survey Button.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage clickStartSurveyButton() {
        startSurvey.click();
        return this;
    }

    /**
     * Click on Strong Button.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage clickStrong1Button() {
        strong1.click();
        return this;
    }

    /**
     * Click on Strong Button.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage clickStrong2Button() {
        strong2.click();
        return this;
    }

    /**
     * Click on Terms Of Use Link.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage clickTermsOfUseLink() {
        termsOfUse.click();
        return this;
    }

	public String getTagLabelText() {
		return driver.findElement(By.xpath(SURVEY_INFO_TAG_LABEL_XPATH)).getText();
	}

	public String getSurveyModeLabelText() {
		return driver.findElement(By.xpath(SURVEY_INFO_MODE_LABEL_XPATH)).getText();
	}

	public String getDriverLabelText() {
		return driver.findElement(By.xpath(SURVEY_INFO_DRIVER_LABEL_XPATH)).getText();
	}

	public String getStabilityClassLabelText() {
		return driver.findElement(By.xpath(SURVEY_INFO_STABILITY_CLASS_LABEL_XPATH)).getText();
	}

	public WebElement getStartTimeLabel() {
		return driver.findElement(By.xpath(SURVEY_INFO_START_TIME_LABEL_XPATH));
	}

	public String getStartTimeLabelText() {
		return driver.findElement(By.xpath(SURVEY_INFO_START_TIME_LABEL_XPATH)).getText();
	}

	public WebElement getEndTimeLabel() {
		return driver.findElement(By.xpath(SURVEY_INFO_END_TIME_LABEL_XPATH));
	}

	public String getEndTimeLabelText() {
		return driver.findElement(By.xpath(SURVEY_INFO_END_TIME_LABEL_XPATH)).getText();
	}

	public String getSurveyorLabelText() {
		return driver.findElement(By.xpath(SURVEY_INFO_SURVEYOR_LABEL_XPATH)).getText();
	}

	public String getAnalyzerLabelText() {
		return driver.findElement(By.xpath(SURVEY_INFO_ANALYZER_LABEL_XPATH)).getText();
	}

	/**
     * Submit the form to target page.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage submit() {
        clickSaveButton();
        return this;
    }

    /**
     * Verify that the page loaded completely.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage verifyPageLoaded() {
        (new WebDriverWait(driver, timeout * 2)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STRPageContentText)
                		 && isElementPresent(blockedUINotShown);
            }
        });
        return this;
    }

    /**
     * Verify that current page URL matches the expected URL.
     *
     * @return the SurveyViewPage class instance.
     */
    public SurveyViewPage verifyPageUrl() {
        (new WebDriverWait(driver, timeout * 2)).until(new ExpectedCondition<Boolean>() {
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
		this.verifyPageLoaded();
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
	 * @param ASSETS_ZOOM_LEVEL_LOWER_BOUND - lower bound of zoom level to see assets on the map.
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
}
