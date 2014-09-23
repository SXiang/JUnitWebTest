/**
 * 
 */
package surveyor.scommon.source;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.source.BasePage;
import common.source.TestSetup;

import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class UserFeedbackPage extends SurveyorBasePage {
	public static final String STRURLPath = "/UserFeedback";
	public static final String STRPageTitle = "Feedback - Surveyor";
	
	@FindBy(how = How.XPATH, using = "//*[@id='user-feedback']/a")
	WebElement linkSendFeedback;
	
	@FindBy(how = How.XPATH, using = "//*[@id='Note']")
	WebElement inputFBNote;
	
	@FindBy(how = How.XPATH, using = "//*[@id='buttonCustomerOk']")
	WebElement btnSend;
	
	@FindBy(how = How.XPATH, using = "//*[@id='feedback-form']/fieldset/div[3]/div[2]/a")
	WebElement btnCancel;
	
	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/div/div/div[3]/a")
	WebElement linkToHomePage;
	
	@FindBy(how = How.XPATH, using = "//*[@id='picarro-administration-user-feedback']/a")
	WebElement linkViewUserFeedback;
	
	@FindBy(how = How.XPATH, using = "//*[@id='customer-administration-user-feedback']/a")
	WebElement linkCusAdminViewUserFB;	

	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public UserFeedbackPage(WebDriver driver, TestSetup testSetup,
			String strBaseURL) {
		super(driver, testSetup, strBaseURL, strBaseURL + STRURLPath);
	}
	
	public void sendFeedback(String strLoginUser, String strFeedback) {
		this.linkSendFeedback.click();
		this.inputFBNote.sendKeys(strFeedback);
		this.btnSend.click();
		this.linkToHomePage.click();
	}
	
	public boolean checkUserFeedback(String strLoginUser, String strFeedback) {
		if (!strLoginUser.contains(REGBASEPICUSERNAME) && !strLoginUser.equalsIgnoreCase(PICDFADMIN)) {
			this.linkCusAdmin.click();
			this.linkCusAdminViewUserFB.click();
		}
		else {
			linkPicarroAdmin.click();
			linkViewUserFeedback.click();
		}
		
		this.paginationInput.sendKeys("100");
		this.testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		String strUserXPath;
		String strNoteXPath;
		WebElement userCell;
		WebElement noteCell;
		
		List<WebElement> rows = table.findElements(By.xpath(strTRXPath));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < 100)
			loopCount = rowSize;
		else
			loopCount = 100;		
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			strUserXPath = strTRXPath + "["+rowNum+"]/td[2]";
			strNoteXPath = strTRXPath + "["+rowNum+"]/td[3]";
			
			userCell = table.findElement(By.xpath(strUserXPath));
			noteCell = table.findElement(By.xpath(strNoteXPath));
			
			if (userCell.getText().equalsIgnoreCase(strLoginUser) && noteCell.getText().equalsIgnoreCase(strFeedback)) {				
				return true;
			}
			
			if (rowNum == 100 && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				
				List<WebElement> newRows = table.findElements(By.xpath(strTRXPath));
				rowSize = newRows.size();
				if (rowSize < 100)
					loopCount = rowSize;
				else
					loopCount = 100;
				rowNum = 1;
			}
		}
		
		return false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
