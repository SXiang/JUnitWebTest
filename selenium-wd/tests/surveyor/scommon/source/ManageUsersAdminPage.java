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
	
	@FindBy(how = How.XPATH, using = "//*[@id='User.UserName-error']")
	private WebElement labelUserNameError;
	private String labelUserNameErrorXPath = "//*[@id='User.UserName-error']";
	
	@FindBy(how = How.XPATH, using = "//*[@id='User_Password-error']")
	private WebElement labelUserPwdError;
	private String labelUserPwdErrorXPath = "//*[@id='User_Password-error']"; 
	
	@FindBy(how = How.XPATH, using = "//*[@id='PasswordConfirm-error']")
	private WebElement labelPwdConfirmError;
	private String labelPwdConfirmErrorXPath = "//*[@id='PasswordConfirm-error']";
	
	/**
	 * @param driver
	 * @param baseURL
	 * @param testSetup
	 */
	public ManageUsersAdminPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, baseURL, testSetup, STRURLPath);
		
		System.out.format("\nThe Manager Users Admin Page URL is: %s\n", baseURL + STRURLPath);
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
			if (panelError.getText().equalsIgnoreCase("Please, correct the following errors:"))
				this.cancelAddBtn.click();
		}
	}

	public void addNewUser(String email, String password, String role, String timeZone) {
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
		
		List<WebElement> tzOptions = this.dropDownRole.findElements(By.tagName("option"));
		for (WebElement tzOption : tzOptions) {
			if (tzOption.getText().trim().equalsIgnoreCase(timeZone))
				tzOption.click();
		}		
		
		this.btnOk.click();
		
		if (isElementPresent(this.panelDuplicationErrorXPath)){
			WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
			if (panelError.getText().equalsIgnoreCase("Please, correct the following errors:"))
				this.cancelAddBtn.click();
		}
	}
	
	public void addNewUser(String email, String password, String role, String timeZone, boolean accountEnabled) {
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
			if (panelError.getText().equalsIgnoreCase("Please, correct the following errors:"))
				this.cancelAddBtn.click();
		}
	}
	
	//temporary solution for now and should be improved with a better approach 
	public String addTestUser(String email, String password1, String password2) {
		String rtnMsg = "";
		
		this.btnAddNewCustomerUser.click();
		
		this.inputEmail.clear();
		this.inputEmail.sendKeys(email);
		this.inputPassword.sendKeys(password1);
		this.inputPasswordConfirm.sendKeys(password2);
		
		this.btnOk.click();
		
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
			if (panelError.getText().equalsIgnoreCase("Please, correct the following errors:")) {
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