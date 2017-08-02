/**
 * 
 */
package surveyor.scommon.source;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	public static final String STRPageTitle = String.format("%s - %s", 
			Resources.getResource(ResourceKeys.ManageUsers_PageTitle), Resources.getResource(ResourceKeys.Constant_Surveyor));

	/**
	 * @param driver
	 * @param baseURL
	 * @param testSetup
	 */
	public ManageUsersAdminPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, baseURL, testSetup, STRURLPath);
		
		Log.info("\nThe Manage Users Admin Page URL is: %s\n"+ baseURL + STRURLPath);
	}
	
	public void addNewUser(String email, String password, String role) {
		Log.clickElementInfo("Add New Customer User");
		this.btnAddNewCustomerUser.click();
		Log.info("Set email - '"+email+"'");
		this.inputEmail.clear();
		this.inputEmail.sendKeys(email);
		Log.info("Set password - '<HIDDEN>'");
		this.inputPassword.sendKeys(password);
		this.inputPasswordConfirm.sendKeys(password);
		
		List<WebElement> roleOptions = this.dropDownRole.findElements(By.tagName("option"));
		for (WebElement roleOption : roleOptions) {
			if (roleOption.getText().trim().equalsIgnoreCase(role)){
				Log.info("Selection Role - '"+roleOption+"'");
				roleOption.click();
				break;
			}
		}		
		Log.clickElementInfo("Ok");
		this.btnOk.click();
		
		if (isElementPresent(this.summaryErrorsBy)){
			WebElement panelError = driver.findElement(summaryErrorsBy);
			if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))){
				Log.clickElementInfo("Cancel");
				this.cancelAddBtn.click();
			}
		}
	}

	public void addNewUser(String email, String password, String location, String role, String timeZone) {
		Log.clickElementInfo("Add New Customer User");
		this.btnAddNewCustomerUser.click();
		
		this.waitForNewPageLoad();
		
		Select droplist = new Select(this.dropDownCustomer);
		droplist.selectByVisibleText(location);

		Log.info("Set email - '"+email+"'");
		this.inputEmail.clear();
		this.inputEmail.sendKeys(email);
		Log.info("Set password - '<HIDDEN>'");
		this.inputPassword.sendKeys(password);
		this.inputPasswordConfirm.sendKeys(password);
		
		List<WebElement> roleOptions = this.dropDownRole.findElements(By.tagName("option"));
		for (WebElement roleOption : roleOptions) {
			if (roleOption.getText().trim().equalsIgnoreCase(role)){
				Log.info("Selection Role - '"+roleOption+"'");
				roleOption.click();
				break;
			}
		}
		
		List<WebElement> tzOptions = this.dropDownTimeZone.findElements(By.tagName("option"));
		for (WebElement tzOption : tzOptions) {
			if (tzOption.getText().trim().equalsIgnoreCase(timeZone)){
				Log.info("Selection Timezone - '"+tzOption+"'");
				tzOption.click();
				break;
			}
		}		
		Log.clickElementInfo("Ok");
		this.btnOk.click();
		
		if (isElementPresent(this.summaryErrorsBy)){
			WebElement panelError = driver.findElement(summaryErrorsBy);
			if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))){
				Log.clickElementInfo("Cancel");
				this.cancelAddBtn.click();
			}
		}
		
		this.waitForPageLoad();
	}
	
	public void addNewUser(String email, String password, String location, String role, String timeZone, boolean accountEnabled) {
		Log.clickElementInfo("Add New Customer User");
		this.btnAddNewCustomerUser.click();
		
		this.waitForNewPageLoad();
		
		Select droplist = new Select(this.dropDownCustomer);
		droplist.selectByVisibleText(location);

		Log.info("Set email - '"+email+"'");
		this.inputEmail.clear();
		this.inputEmail.sendKeys(email);
		Log.info("Set password - '<HIDDEN>'");
		this.inputPassword.sendKeys(password);
		this.inputPasswordConfirm.sendKeys(password);
		
		List<WebElement> roleOptions = this.dropDownRole.findElements(By.tagName("option"));
		for (WebElement roleOption : roleOptions) {
			if (roleOption.getText().trim().equalsIgnoreCase(role)){
				Log.info("Selection Role - '"+roleOption+"'");
				roleOption.click();
				break;
			}
		}
		
		List<WebElement> tzOptions = this.dropDownTimeZone.findElements(By.tagName("option"));
		for (WebElement tzOption : tzOptions) {
			if (tzOption.getText().trim().equalsIgnoreCase(timeZone)){
				Log.info("Selection Timezone - '"+tzOption+"'");
				tzOption.click();
				break;
			}
		}
		
		if (accountEnabled) {
			if (!inputAccountEnabled.isSelected()){
				Log.info("Check to enable account");
				inputAccountEnabled.click();
			}
		}
		else {
			if (inputAccountEnabled.isSelected()){
				Log.info("Uncheck to disable account");
				inputAccountEnabled.click();
			}
		}		
		Log.clickElementInfo("Ok");
		this.btnOk.click();
		
		if (isElementPresent(this.summaryErrorsBy)){
			WebElement panelError = driver.findElement(summaryErrorsBy);
			if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))){
				Log.clickElementInfo("Cancel");
				this.cancelAddBtn.click();
			}
		}
		
		this.waitForPageLoad();
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