/**
 * 
 */
package surveyor.scommon.source;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.Log;
import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class UserFeedbackPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Picarro/UserFeedback";
	public static final String STRPageTitle = "User Feedback - Surveyor";
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.UserFeedback_PromptTitle);
	public String feedbackRecievedTxt = Resources.getResource(ResourceKeys.UserFeedback_ThankYou_PromptText);
	
	@FindBy(how = How.XPATH, using = "//*[@id='user-feedback']/a")
	protected WebElement linkSendFeedback;
	
	@FindBy(how = How.XPATH, using = "//*[@id='Note']")
	protected WebElement inputFBNote;
	
	@FindBy(how = How.XPATH, using = "//*[@id='buttonOk']")
	protected WebElement btnSend;
	
	@FindBy(how = How.XPATH, using = "//*[@id='feedback-form']/fieldset/div[3]/div[2]/a")
	protected WebElement btnCancel;
	
	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/div/div/div[3]/a")
	protected WebElement btnReturnToHomePage;
	
	@FindBy(how = How.XPATH, using = "//*[@id='picarro-administration-user-feedback']/a")
	protected WebElement linkViewUserFeedback;
	
	@FindBy(how = How.XPATH, using = "//*[@id='customer-administration-user-feedback']/a")
	protected WebElement linkCusAdminViewUserFB;	
	
	@FindBy(how = How.XPATH, using = "/html/body/div/div[2]/div/div/div[2]/p")
	protected WebElement textFeedbackRecieved;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr")
	protected List<WebElement> rows;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[1]")
    protected WebElement tdCustomerValue;
    
    @FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[2]")
    protected WebElement tdUserValue;
    
    @FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[3]")
    protected WebElement tdNoteValue;
    
    @FindBy(how = How.XPATH, using = "//*[@id='datatable']/thead/tr/th[3]")
	protected WebElement theadNote;

	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 */
	public UserFeedbackPage(WebDriver driver, TestSetup testSetup,
			String strBaseURL) {
		super(driver, testSetup, strBaseURL, strBaseURL + STRURLPath);
		
		Log.info("\nThe User Feedback Page URL is: " + strBaseURL + STRURLPath);
	}
	
	public UserFeedbackPage(WebDriver driver, TestSetup testSetup, String strBaseURL, String urlPath) {
		super(driver, testSetup, strBaseURL, strBaseURL + urlPath);
	}	
	
	public void sendFeedback(String strLoginUser, String strFeedback) {
		this.linkSendFeedback.click();
		this.inputFBNote.sendKeys(strFeedback);
		this.btnSend.click();
	}
	
	public HomePage clickBtnReturnToHomePage() {
		this.btnReturnToHomePage.click();
		HomePage homePage = new HomePage(this.driver, this.strBaseURL, this.testSetup);
		PageFactory.initElements(driver,  homePage);
		return homePage;
	}
	
	public boolean checkSuccessMsg(){
		if (this.textFeedbackRecieved.getText().equalsIgnoreCase(feedbackRecievedTxt))
			return true;
		else
			return false;
	}
	
	public boolean checkUserFeedback(String user, String strFeedback) {
		setPagination(PAGINATIONSETTING_100);
		
		this.waitForTableDataToLoad();
		
		String strUserXPath;
		String strNoteXPath;
		WebElement userCell;
		WebElement noteCell;
		
		List<WebElement> rows = table.findElements(By.xpath(strTRXPath));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);		
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			strUserXPath = strTRXPath + "["+rowNum+"]/td[2]";
			strNoteXPath = strTRXPath + "["+rowNum+"]/td[3]";
			
			userCell = table.findElement(By.xpath(strUserXPath));
			noteCell = table.findElement(By.xpath(strNoteXPath));
			
			if (userCell.getText().equalsIgnoreCase(user) && noteCell.getText().equalsIgnoreCase(strFeedback)) {			
				Log.info("Found entry at row=" + rowNum);
				return true;
			}
			
			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				
				List<WebElement> newRows = table.findElements(By.xpath(strTRXPath));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);
				
				rowNum = 0;
			}
		}
		
		return false;
	}
	
	public String getUserFeedbackNote(String customer, String user) {
		setPagination(PAGINATIONSETTING_100);
		
		this.waitForTableDataToLoad();
		
		String customerXPath;
		String strUserXPath;
		String strNoteXPath;
		
		WebElement customerCell;
		WebElement userCell;
		WebElement noteCell;
		
		List<WebElement> rows = table.findElements(By.xpath(strTRXPath));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);		
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerXPath = strTRXPath + "["+rowNum+"]/td[1]";
			strUserXPath = strTRXPath + "["+rowNum+"]/td[2]";
			
			customerCell = table.findElement(By.xpath(customerXPath));
			userCell = table.findElement(By.xpath(strUserXPath));
			
			if (customerCell.getText().trim().equalsIgnoreCase(customer) && userCell.getText().trim().equalsIgnoreCase(user)) {				
				strNoteXPath = strTRXPath + "["+rowNum+"]/td[3]";
				noteCell = table.findElement(By.xpath(strNoteXPath));
				Log.info("Found entry at row=" + rowNum);
				return noteCell.getText().trim();
			}
			
			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				
				List<WebElement> newRows = table.findElements(By.xpath(strTRXPath));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);
				
				rowNum = 0;
			}
		}
		
		return null;		
		
	}
	
	public List<String> getUserFeedbackNotes(String customer, String user) {
		List<String> list = new ArrayList<String>();		
		
		setPagination(PAGINATIONSETTING_100);
		
		this.waitForTableDataToLoad();
		
		String customerXPath;
		String strUserXPath;
		String strNoteXPath;
		
		WebElement customerCell;
		WebElement userCell;
		WebElement noteCell;
		
		List<WebElement> rows = table.findElements(By.xpath(strTRXPath));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);		
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerXPath = strTRXPath + "["+rowNum+"]/td[1]";
			strUserXPath = strTRXPath + "["+rowNum+"]/td[2]";
			
			customerCell = table.findElement(By.xpath(customerXPath));
			userCell = table.findElement(By.xpath(strUserXPath));
			
			if (customerCell.getText().trim().equalsIgnoreCase(customer) && userCell.getText().trim().equalsIgnoreCase(user)) {				
				strNoteXPath = strTRXPath + "["+rowNum+"]/td[3]";
				noteCell = table.findElement(By.xpath(strNoteXPath));
				Log.info("Found entry at row=" + rowNum);
				list.add(noteCell.getText().trim());
			}
			
			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				
				List<WebElement> newRows = table.findElements(By.xpath(strTRXPath));
				rowSize = newRows.size();
				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);
				
				rowNum = 0;
			}
		}	
		
		return list; 
	}	

	@Override
	public void waitForPageLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STRPageContentText);
            }
        });
    }
	
	public List<String> getNotesList(boolean allPages, int paginationSize) {
		List<String> noteList = new ArrayList<String>();
		String pageSizeStr = String.valueOf(paginationSize);
		setPagination(pageSizeStr);

		String noteXPath;
		WebElement noteCell;

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(pageSizeStr))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(pageSizeStr);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			noteXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[3]";
			noteCell = table.findElement(By.xpath(noteXPath));

			noteList.add(noteCell.getText().trim());

			if (rowNum == Integer.parseInt(pageSizeStr)
					&& !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));
				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(pageSizeStr))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(pageSizeStr);

				rowNum = 0;
			}
		}
		return noteList;
	}
	
	public boolean searchNote(String customer, String userName, String note) {
		this.getInputSearch().sendKeys(note);
		try {
			if (this.tdNoteValue.getText().contentEquals(note)) {
				if (this.tdUserValue.getText().contentEquals(userName)) {
					if (this.tdCustomerValue.getText().contentEquals(customer))
						return true;
				}
			}
		} catch (NoSuchElementException ne) {
			Log.info(ne.toString());
			return false;
		}
		return false;
	}
	
	public WebElement getTheadNotes() {
		return this.theadNote;
	}
}
