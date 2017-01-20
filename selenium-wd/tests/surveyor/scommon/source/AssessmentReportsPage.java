package surveyor.scommon.source;

import org.openqa.selenium.WebDriver;

import common.source.Log;
import common.source.TestSetup;

/**
 * Assessment Reports Page model
 */
public class AssessmentReportsPage extends ReportsBasePage {

	public static final String STRURLPath = "/Reports/AssessmentReport";

	/**
	 * @param driver
	 * @param strBaseURL
	 * @param testSetup
	 */
	public AssessmentReportsPage(WebDriver driver, String strBaseURL, TestSetup testSetup) {
		super(driver, strBaseURL, testSetup, strBaseURL + STRURLPath);

		Log.info(String.format("\nThe Assessment Reports Page URL is: %s\n", this.strPageURL));
	}
}
