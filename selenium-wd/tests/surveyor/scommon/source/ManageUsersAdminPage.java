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
 * @author zlu
 *
 */
public class ManageUsersAdminPage extends ManageUsersPage {
	public static final String STRURLPath = "/Admin/ManageUsers";
	public static final String STRPageTitle = "Manage ??? Users - Surveyor";
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a")
	private WebElement btnAddNewUser;
	
	@FindBy(how = How.XPATH, using = "//*[@id='user-form']/fieldset/div[8]/div[2]/a")
	private WebElement cancelBtn;

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
		this.btnAddNewUser.click();
		
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
				this.cancelBtn.click();
		}
	}

	public void addNewUser(String email, String password, String role, String timeZone) {
		this.btnAddNewUser.click();
		
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
				this.cancelBtn.click();
		}
	}
	
	public void addNewUser(String email, String password, String role, String timeZone, boolean accountEnabled) {
		this.btnAddNewUser.click();
		
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
				this.cancelBtn.click();
		}
	}	
	
	public WebElement getBtnAddNewUser() {
		return this.btnAddNewUser;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
}