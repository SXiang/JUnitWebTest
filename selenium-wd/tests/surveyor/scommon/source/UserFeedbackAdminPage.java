/**
 * 
 */
package surveyor.scommon.source;

import org.openqa.selenium.WebDriver;

import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class UserFeedbackAdminPage extends UserFeedbackPage {
	public static final String STRURLPath = "/Admin/UserFeedback";
	public static final String STRPageTitle = "User Feedback - Surveyor";
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 */
	public UserFeedbackAdminPage(WebDriver driver, TestSetup testSetup, String strBaseURL) {
		super(driver, testSetup, strBaseURL, STRURLPath);
		
		System.out.println("\nThe User Feedback Admin Page URL is: " + strBaseURL + STRURLPath);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
