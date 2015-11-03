/**
 * 
 */
package surveyor.scommon.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.BasePage;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class FleetMapPage extends BasePage {
	public static final String STRURLPath = "/Home/FleetMap";
	public static final String STRPageTitle = "Fleet Map - Surveyor";
	public static final String STRPageContentText = "Fleet Map";

	/**
	 * @param driver
	 * @param testSetup
	 * @param baseURL
	 */
	public FleetMapPage(WebDriver driver, TestSetup testSetup, String baseURL) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);

		System.out.println("\nThe FleetMap Page URL is: "
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

    @Override
	public void waitForPageLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STRPageContentText);
            }
        });
    }
}
