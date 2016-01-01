/**
 * 
 */
package surveyor.scommon.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.source.Log;
import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

/**
 * @author zlu
 *
 */
public class SendFeedbackPage extends UserFeedbackPage {
	public static final String STRURLPath = "/UserFeedback";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.UserFeedback_PageTitle);
	
	@FindBy(how = How.XPATH, using = "//*[@id='Note']")
	private WebElement inputFBNote;
	
	@FindBy(how = How.XPATH, using = "//*[@id='buttonCustomerOk']")
	private WebElement btnSend;

	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 */
	public SendFeedbackPage(WebDriver driver, TestSetup testSetup, String strBaseURL) {
		super(driver, testSetup, strBaseURL, STRURLPath);
		
		Log.info("\nThe Send Feedback Page URL is: " + strBaseURL + STRURLPath);
	}
	
	public void sendFeedback(String strLoginUser, String strFeedback) {
		this.inputFBNote.sendKeys(strFeedback);
		this.btnSend.click();
		this.btnReturnToHomePage.click();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
