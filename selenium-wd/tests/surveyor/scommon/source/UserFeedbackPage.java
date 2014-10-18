/**
 * 
 */
package surveyor.scommon.source;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.source.TestSetup;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class UserFeedbackPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Picarro/UserFeedback";
	public static final String STRPageTitle = "User Feedback - Surveyor";
	
	@FindBy(how = How.XPATH, using = "//*[@id='user-feedback']/a")
	protected WebElement linkSendFeedback;
	
	@FindBy(how = How.XPATH, using = "//*[@id='Note']")
	protected WebElement inputFBNote;
	
	@FindBy(how = How.XPATH, using = "//*[@id='buttonCustomerOk']")
	protected WebElement btnSend;
	
	@FindBy(how = How.XPATH, using = "//*[@id='feedback-form']/fieldset/div[3]/div[2]/a")
	protected WebElement btnCancel;
	
	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/div/div/div[3]/a")
	protected WebElement linkToHomePage;
	
	@FindBy(how = How.XPATH, using = "//*[@id='picarro-administration-user-feedback']/a")
	protected WebElement linkViewUserFeedback;
	
	@FindBy(how = How.XPATH, using = "//*[@id='customer-administration-user-feedback']/a")
	protected WebElement linkCusAdminViewUserFB;	

	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public UserFeedbackPage(WebDriver driver, TestSetup testSetup,
			String strBaseURL) {
		super(driver, testSetup, strBaseURL, strBaseURL + STRURLPath);
		
		System.out.println("\nThe User Feedback Page URL is: " + strBaseURL + STRURLPath);
	}
	
	public UserFeedbackPage(WebDriver driver, TestSetup testSetup, String strBaseURL, String urlPath) {
		super(driver, testSetup, strBaseURL, strBaseURL + urlPath);
	}	
	
	public void sendFeedback(String strLoginUser, String strFeedback) {
		this.linkSendFeedback.click();
		this.inputFBNote.sendKeys(strFeedback);
		this.btnSend.click();
		this.linkToHomePage.click();
	}
	
	public boolean checkUserFeedback(String user, String strFeedback) {
		setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		String strUserXPath;
		String strNoteXPath;
		WebElement userCell;
		WebElement noteCell;
		
		List<WebElement> rows = table.findElements(By.xpath(strTRXPath));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);		
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			strUserXPath = strTRXPath + "["+rowNum+"]/td[2]";
			strNoteXPath = strTRXPath + "["+rowNum+"]/td[3]";
			
			userCell = table.findElement(By.xpath(strUserXPath));
			noteCell = table.findElement(By.xpath(strNoteXPath));
			
			if (userCell.getText().equalsIgnoreCase(user) && noteCell.getText().equalsIgnoreCase(strFeedback)) {				
				return true;
			}
			
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				
				List<WebElement> newRows = table.findElements(By.xpath(strTRXPath));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);
				
				rowNum = 0;
			}
		}
		
		return false;
	}
	
	public String getUserFeedbackNote(String customer, String user) {
		setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		String customerXPath;
		String strUserXPath;
		String strNoteXPath;
		
		WebElement customerCell;
		WebElement userCell;
		WebElement noteCell;
		
		List<WebElement> rows = table.findElements(By.xpath(strTRXPath));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);		
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerXPath = strTRXPath + "["+rowNum+"]/td[1]";
			strUserXPath = strTRXPath + "["+rowNum+"]/td[2]";
			
			customerCell = table.findElement(By.xpath(customerXPath));
			userCell = table.findElement(By.xpath(strUserXPath));
			
			if (customerCell.getText().trim().equalsIgnoreCase(customer) && userCell.getText().trim().equalsIgnoreCase(user)) {				
				strNoteXPath = strTRXPath + "["+rowNum+"]/td[3]";
				noteCell = table.findElement(By.xpath(strNoteXPath));
				
				return noteCell.getText().trim();
			}
			
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				
				List<WebElement> newRows = table.findElements(By.xpath(strTRXPath));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);
				
				rowNum = 0;
			}
		}
		
		return null;		
		
	}
	
	public List<String> getUserFeedbackNotes(String customer, String user) {
		List<String> list = new ArrayList<String>();		
		
		setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		String customerXPath;
		String strUserXPath;
		String strNoteXPath;
		
		WebElement customerCell;
		WebElement userCell;
		WebElement noteCell;
		
		List<WebElement> rows = table.findElements(By.xpath(strTRXPath));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);		
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerXPath = strTRXPath + "["+rowNum+"]/td[1]";
			strUserXPath = strTRXPath + "["+rowNum+"]/td[2]";
			
			customerCell = table.findElement(By.xpath(customerXPath));
			userCell = table.findElement(By.xpath(strUserXPath));
			
			if (customerCell.getText().trim().equalsIgnoreCase(customer) && userCell.getText().trim().equalsIgnoreCase(user)) {				
				strNoteXPath = strTRXPath + "["+rowNum+"]/td[3]";
				noteCell = table.findElement(By.xpath(strNoteXPath));
				
				list.add(noteCell.getText().trim());
			}
			
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				
				List<WebElement> newRows = table.findElements(By.xpath(strTRXPath));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING);
				
				rowNum = 0;
			}
		}	
		
		return list; 
	}	

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
