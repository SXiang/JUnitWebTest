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
public class SurveyorSystemsPage extends BasePage {
	public static final String STRURLPath = "/Home/SurveyorSystems";
	public static final String STRPageTitle = "Surveyors - Surveyor";

	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public SurveyorSystemsPage(WebDriver driver, TestSetup testSetup,
			String strBaseURL, String strPageURL) {
		super(driver, testSetup, strBaseURL, strBaseURL + STRURLPath);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
