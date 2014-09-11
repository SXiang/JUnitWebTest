/**
 * 
 */
package surveyor.scommon.source;

import org.openqa.selenium.WebDriver;

import common.source.BasePage;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class FleetMapPage extends BasePage {
	public static final String STRURLPath = "/Home/FleetMap";
	public static final String STRPageTitle = "Fleet Map - Surveyor";

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

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
