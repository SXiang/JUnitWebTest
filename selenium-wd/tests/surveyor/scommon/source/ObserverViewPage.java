package surveyor.scommon.source;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.Log;
import common.source.TestSetup;
import common.source.WebElementExtender;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ObserverViewPage extends BaseDrivingViewPage {
	public static final String STRURLPath = "/Live/Observer";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.Constant_Observer);
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.Dialog_MapView);
	
    @FindBy(id ="tag")
	private WebElement labelTag;

    @FindBy(id ="currentTime")
	private WebElement labelCurrentTime;

    @FindBy(id ="surveyMode")
	private WebElement labelMode;

    @FindBy(id ="headerInfoStatus")
	private WebElement labelStatus;

    @FindBy(id ="driver")
	private WebElement labelDriver;

    @FindBy(id ="timeElapsed")
	private WebElement labelTimeElapsed;

    @FindBy(id ="surveyorAnalyzer")
	private WebElement labelAnalyzer;

    @FindBy(id ="stabilityClass")
	private WebElement labelStabilityClass;

    @FindBy(id ="timeRemaining")
	private WebElement labelTimeRemaining;

    @FindBy(id ="zoomLevel")
	private WebElement labelZoomLevel;

	/**
	 * @param driver
	 * @param baseURL
	 * @param testSetup
	 */
	public ObserverViewPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);

		Log.info("\nThe ObserverView Page URL is: " + this.strPageURL);
	}
	
	public boolean checkIfAtObserverViewPage() {
		if (driver.getTitle().equalsIgnoreCase(STRPageTitle))
			return true;
		
		return false;
	}
	
	public boolean isAnalysisDisplayOptionOnViewPage() {
		return WebElementExtender.isElementPresentAndDisplayed(displaySwitchIsotopicAnalysis);
	}
	
	public boolean isFieldNotesDisplayOptionOnViewPage() {
		return WebElementExtender.isElementPresentAndDisplayed(displaySwitchNotes);
	}
    /**
     * Verify that the page loaded completely.
     *
     * @return the ObserverViewPage class instance.
     */
    public ObserverViewPage verifyPageLoaded() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STRPageContentText);
            }
        });
        return this;
    }

    /**
     * Verify that current page URL matches the expected URL.
     *
     * @return the ObserverViewPage class instance.
     */
    public ObserverViewPage verifyPageUrl() {
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
}
