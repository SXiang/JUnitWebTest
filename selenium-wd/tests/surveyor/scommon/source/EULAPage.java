package surveyor.scommon.source;

import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EULAPage extends SurveyorBasePage {
    private WebDriver driver;

    @FindBy(css = "button[class='btn btn-success btn-lg btn-block']")
    @CacheLookup
    private WebElement iAccept;

    private final static String STRPageContentText = Resources.getResource(ResourceKeys.Dialog_PleaseReviewEULA);

    private final static String STRURLPath = "/Eula";

    @FindBy(css = "textarea[class='form-control']")
    @CacheLookup
    private WebElement pleaseReviewTheEulaBelowLorem;

	/**
	 * @param driver
	 * @param baseURL
	 * @param testSetup
	 */
	public EULAPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);
		
		System.out.println("\nThe EULA Page URL is: " + this.strPageURL);
	}

    /**
     * Click on I Accept Button.
     *
     * @return the EULAPage class instance.
     */
    public EULAPage clickIAcceptButton() {
        iAccept.click();
        return this;
    }

	@Override
	public void waitForPageLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STRPageContentText);
            }
        });
    }

    /**
     * Verify that current page URL matches the expected URL.
     *
     * @return the EULAPage class instance.
     */
    public EULAPage verifyPageUrl() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().contains(STRURLPath);
            }
        });
        return this;
    }
}
