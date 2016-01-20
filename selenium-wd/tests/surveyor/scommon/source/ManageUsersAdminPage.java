/**
 * 
 */
package surveyor.scommon.source;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

import common.source.Log;
import common.source.TestSetup;

/**
 * Util Admin User's page
 * Code related to Util Admin Manage User's page should be here
 * @author zlu
 *
 */
public class ManageUsersAdminPage extends ManageUsersPage {
	public static final String STRURLPath = "/Admin/ManageUsers";
	public static final String STRPageTitle = "Manage ??? Users - Surveyor";
	private static final int ALLOWED_MAX_EMAIL_LENGTH = 50;
	
	@FindBy(id = "User.UserName-error")
	private WebElement labelUserNameError;
	private String labelUserNameErrorXPath = "//*[@id='User.UserName-error']";
	
	@FindBy(id = "User_Password-error")
	private WebElement labelUserPwdError;
	private String labelUserPwdErrorXPath = "//*[@id='User_Password-error']"; 
	
	@FindBy(id = "PasswordConfirm-error")
	private WebElement labelPwdConfirmError;
	private String labelPwdConfirmErrorXPath = "//*[@id='PasswordConfirm-error']";
	
	/**
	 * @param driver
	 * @param baseURL
	 * @param testSetup
	 */
	public ManageUsersAdminPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, baseURL, testSetup, STRURLPath);
		
		Log.info("\nThe Manager Users Admin Page URL is: %s\n"+ baseURL + STRURLPath);
	}
	
	public void addNewUser(String email, String password, String role) {
		this.btnAddNewCustomerUser.click();
		
		this.inputEmail.clear();
		this.inputEmail.sendKeys(email);
		this.inputPassword.sendKeys(password);
		this.inputPasswordConfirm.sendKeys(password);
		
		List<WebElement> roleOptions = this.dropDownRole.findElements(By.tagName("option"));
		for (WebElement roleOption : roleOptions) {
			if (roleOption.getText().trim().equalsIgnoreCase(role))
				roleOption.click();
		}		
		
		this.btnOk.click();
		
		if (isElementPresent(this.panelDuplicationErrorXPath)){
			WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
			if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle)))
				this.cancelAddBtn.click();
		}
	}

	public void addNewUser(String email, String password, String location, String role, String timeZone) {
		this.btnAddNewCustomerUser.click();
		
		this.waitForNewPageLoad();
		
		Select droplist = new Select(this.dropDownCustomer);
		droplist.selectByVisibleText(location);

		this.inputEmail.clear();
		this.inputEmail.sendKeys(email);
		this.inputPassword.sendKeys(password);
		this.inputPasswordConfirm.sendKeys(password);
		
		List<WebElement> roleOptions = this.dropDownRole.findElements(By.tagName("option"));
		for (WebElement roleOption : roleOptions) {
			if (roleOption.getText().trim().equalsIgnoreCase(role))
				roleOption.click();
		}
		
		List<WebElement> tzOptions = this.dropDownRole.findElements(By.tagName("option"));
		for (WebElement tzOption : tzOptions) {
			if (tzOption.getText().trim().equalsIgnoreCase(timeZone))
				tzOption.click();
		}		
		
		this.btnOk.click();
		
		if (isElementPresent(this.panelDuplicationErrorXPath)){
			WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
			if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle)))
				this.cancelAddBtn.click();
		}
		
		this.waitForPageLoad();
	}
	
	public void addNewUser(String email, String password, String location, String role, String timeZone, boolean accountEnabled) {
		this.btnAddNewCustomerUser.click();
		
		this.waitForNewPageLoad();
		
		Select droplist = new Select(this.dropDownCustomer);
		droplist.selectByVisibleText(location);
		
		this.inputEmail.clear();
		this.inputEmail.sendKeys(email);
		this.inputPassword.sendKeys(password);
		this.inputPasswordConfirm.sendKeys(password);
		
		List<WebElement> roleOptions = this.dropDownRole.findElements(By.tagName("option"));
		for (WebElement roleOption : roleOptions) {
			if (roleOption.getText().trim().equalsIgnoreCase(role))
				roleOption.click();
		}
		
		List<WebElement> tzOptions = this.dropDownRole.findElements(By.tagName("option"));
		for (WebElement tzOption : tzOptions) {
			if (tzOption.getText().trim().equalsIgnoreCase(timeZone))
				tzOption.click();
		}
		
		if (accountEnabled) {
			if (!inputAccountEnabled.isSelected())
				inputAccountEnabled.click();
		}
		else {
			if (inputAccountEnabled.isSelected())
				inputAccountEnabled.click();
		}		
		
		this.btnOk.click();
		
		if (isElementPresent(this.panelDuplicationErrorXPath)){
			WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
			if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle)))
				this.cancelAddBtn.click();
		}
		
		this.waitForPageLoad();
	}
	
	public String addTestUser(String email, String password1, String password2) {
		String rtnMsg = "";
		waitForPageToLoad();
		this.btnAddNewCustomerUser.click();
		this.waitForNewPageLoad();
		
		this.inputEmail.clear();
		this.inputEmail.sendKeys(email);
		
		// If user inputted greater than allowed max characters in Email, then check if 'Max character' message label is shown. 
		// If not remove chars from beginning to format email to valid MAX length.
		if (email.length() > ALLOWED_MAX_EMAIL_LENGTH) {
			if (isElementPresent(this.labelUserNameErrorXPath)) {
				rtnMsg = this.labelUserNameError.getText().trim();
				this.cancelAddBtn.click();
				return rtnMsg;
			} else {
				int difflen = email.length() - ALLOWED_MAX_EMAIL_LENGTH;
				this.inputEmail.clear();
				this.inputEmail.sendKeys(email.substring(difflen, email.length()-1));
			}
		}
		
		this.inputPassword.sendKeys(password1);
		this.inputPasswordConfirm.sendKeys(password2);
		
		this.btnOk.click();
		waitForPageToLoad();
		
		if (isElementPresent(this.labelUserNameErrorXPath)) {
			rtnMsg = this.labelUserNameError.getText().trim();
			this.cancelAddBtn.click();
			return rtnMsg;
		}
		
		if (isElementPresent(this.labelUserPwdErrorXPath)) {
			rtnMsg = this.labelUserPwdError.getText().trim();
			this.cancelAddBtn.click();
			return rtnMsg; 
		}
		
		if (isElementPresent(this.labelPwdConfirmErrorXPath)) {
			rtnMsg = this.labelPwdConfirmError.getText().trim();
			this.cancelAddBtn.click();
			return rtnMsg; 
		}		
		
		if (isElementPresent(this.panelDuplicationErrorXPath)) {
			WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));

			if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))) {
				rtnMsg = panelError.getText();
				this.cancelAddBtn.click();
			}
		}
		
		return rtnMsg;
	}	
	
	public WebElement getBtnAddNewUser() {
		return this.btnAddNewCustomerUser;
	}
	
	public WebElement getBtnCancel() {
		return this.cancelAddBtn;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
}