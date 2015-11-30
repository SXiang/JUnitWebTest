/**
 * 
 */
package surveyor.scommon.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.BasePage;
import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

/**
 * @author zlu
 *
 */
public class SurveyorSystemsPage extends BasePage {
	public static final String STRURLPath = "/Home/SurveyorSystems";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.Constant_Surveyors) + " - " + Resources.getResource(ResourceKeys.Constant_Surveyors);
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.Constant_Surveyors);

	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 */
	public SurveyorSystemsPage(WebDriver driver, TestSetup testSetup,
			String strBaseURL) {
		super(driver, testSetup, strBaseURL, strBaseURL + STRURLPath);
	}
	
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

	@Override
	public void waitForPageLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STRPageContentText);
            }
        });
    }
}
