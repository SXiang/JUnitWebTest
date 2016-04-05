/**
 * 
 */
package surveyor.scommon.source;

import org.openqa.selenium.WebDriver;

import common.source.Log;
import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

/**
 * @author zlu
 *
 */
public class UserFeedbackAdminPage extends UserFeedbackPage {
	public static final String STRURLPath = "/Admin/UserFeedback";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.UserFeedback_PageTitle);
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 */
	public UserFeedbackAdminPage(WebDriver driver, TestSetup testSetup, String strBaseURL) {
		super(driver, testSetup, strBaseURL, STRURLPath);
		
		Log.info("\nThe User Feedback Admin Page URL is: " + strBaseURL + STRURLPath);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
