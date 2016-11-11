/**
 * 
 */
package surveyor.scommon.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.BasePage;
import common.source.Log;
import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

/**
 * @author zlu
 *
 */
public class FleetMapPage extends BasePage {
	public static final String STRURLPath = "/Home/FleetMap";
	public static final String STRPageTitle = String.format("%s - %s", 
			Resources.getResource(ResourceKeys.FleetMap_PageTitle), Resources.getResource(ResourceKeys.Constant_Surveyor));
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.FleetMap_PageTitle);
	
	@FindBy(how = How.XPATH, using = "//*[@class='ol-unselectable']")
	protected WebElement fleetMap;

	/**
	 * @param driver
	 * @param testSetup
	 * @param baseURL
	 */
	public FleetMapPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);

		Log.info("\nThe FleetMap Page URL is: "
				+ this.strPageURL);
	}
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public FleetMapPage(WebDriver driver, TestSetup testSetup,
			String strBaseURL, String strPageURL) {
		super(driver, testSetup, strBaseURL, strBaseURL + STRURLPath);
	}

	public WebElement getFleetMap() {
		return fleetMap;
	}

	public boolean checkIfAtFleetMapPage() {
		if (driver.getTitle().equalsIgnoreCase(STRPageTitle))
			return true;
		Log.warn("Current page is not '"+STRPageTitle+"'");
		return false;
	}

	public void waitForFleetMaptoLoad() {
    (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
        public Boolean apply(WebDriver d) {
            return getFleetMap().isDisplayed();
        }
    });
}
	
    @Override
	public void waitForPageLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STRPageContentText);
            }
        });
    }
}
