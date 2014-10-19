/**
 * 
 */
package surveyor.scommon.source;

import static surveyor.scommon.source.SurveyorConstants.*;

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
public class ManageUsersPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Picarro/ManageUsers";
	public static final String STRPageTitle = "Manage Users - Surveyor";
	
	//@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div/div[1]/div[1]/a[1]")
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a[1]")
	protected WebElement btnAddNewCustomerUser;
	
	//@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div/div[1]/div[1]/a[2]")
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a[2]")
	protected WebElement btnAddNewPicarroUser;
	
//	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div[1]")
//	protected WebElement panelDupUserError;
//	protected String panelDupUserErrorXPath = "//*[@id='page-wrapper']/div/div[2]/div[1]";
	
//	//@FindBy(how = How.XPATH, using = "//a[contains(text(),'Administrator')]")
//	@FindBy(how = How.XPATH, using = "//*[@id='wrapper']/nav/ul/li/a")
//	private WebElement dropDownAdministrator;
//	
//	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Log Out')]")
//	private WebElement linkLogOut;	

	@FindBy(how = How.XPATH, using = "//*[@id='User_CustomerId']")
	protected WebElement dropDownCustomer;
	
	//@FindBy(how = How.XPATH, using = "//*[@id='User_UserName']")
	@FindBy(how = How.XPATH, using = "//*[@id='User.UserName']")
	protected WebElement inputEmail;
	
	@FindBy(how = How.XPATH, using = "//*[@id='User_Password']")
	protected WebElement inputPassword;
	
	@FindBy(how = How.XPATH, using = "//*[@id='PasswordConfirm']")
	protected WebElement inputPasswordConfirm;
	
	@FindBy(how = How.XPATH, using = "//*[@id='User_RoleId']")
	protected WebElement dropDownRole;
	
//	@FindBy(how = How.XPATH, using = "//*[@id='buttonCustomerOk']")
//	protected WebElement btnOk;
	
	@FindBy(how = How.XPATH, using = "//*[@id='user-form']/fieldset/div[9]/div[2]/a")
	protected WebElement cancelBtn;	
	
//	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody")
//	protected WebElement userTB;	
	
//	@FindBy(how = How.XPATH, using = "//*[@id='datatable_length']/label/select")
//	protected WebElement paginationInput;
	
	//@FindBy(how = How.XPATH, using = "//a[contains(text(),'Next')]")
//	@FindBy(how = How.XPATH, using = "//*[@id='datatable_next']")
//	protected WebElement nextBtn;
	
	@FindBy(how = How.XPATH, using = "//*[@id='NewPassword']")
	protected WebElement inputNewPassword;
	
	@FindBy(how = How.XPATH, using = "//*[@id='PasswordConfirm']")
	protected WebElement inputNewPasswordConfirm;
	
	@FindBy(how = How.XPATH, using = "//*[@id='User_Active']")
	protected WebElement inputAccountEnabled;
	
	@FindBy(how = How.XPATH, using = "//*[@id='User_TimeZoneId']")
	protected WebElement dropDownTimeZone;
	
	//add more web elements here later

	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public ManageUsersPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);
		
		System.out.println("\nThe Manager Users Page URL is: " + this.strPageURL);
	}
	
	public ManageUsersPage(WebDriver driver, String baseURL, TestSetup testSetup, String urlPath) {
		super(driver, testSetup, baseURL, baseURL + urlPath);
	}	
	
	public void addNewPicarroUser(String email, String password, String role) {
		this.btnAddNewPicarroUser.click();
		
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
	
	public void addNewPicarroUser(String email, String password, String role, String timeZone) {
		this.btnAddNewPicarroUser.click();
		
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
	
	public void addNewCustomerUser(String customerName, String email, String password, String role) {
		this.btnAddNewCustomerUser.click();
		
		List<WebElement> options = this.dropDownCustomer.findElements(By.tagName("option"));
		for (WebElement option : options) { 	
			if (customerName.equalsIgnoreCase(option.getText().trim()))
				option.click();
		}
		
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
	
	public void addNewCustomerUser(String customerName, String email, String password, String role, String timeZone) {
		this.btnAddNewCustomerUser.click();
		
		List<WebElement> options = this.dropDownCustomer.findElements(By.tagName("option"));
		for (WebElement option : options) { 	
			if (customerName.equalsIgnoreCase(option.getText().trim()))
				option.click();
		}
		
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
	
	public boolean findExistingUser(String userName) {
		setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		String userNameXPath;
		
		WebElement userNameCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			userNameXPath     = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			
			userNameCell      = table.findElement(By.xpath(userNameXPath));
			
			if ((userNameCell.getText().trim()).equalsIgnoreCase(userName)) {
				return true;
			}
				
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				
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
	
	public boolean findExistingUser(String customerName, String userName) {
		setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		String customerNameXPath;
		String userNameXPath;
		
		WebElement customerNameCell;
		WebElement userNameCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			userNameXPath     = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";

			customerNameCell = table.findElement(By.xpath(customerNameXPath));
			userNameCell     = table.findElement(By.xpath(userNameXPath));
			
			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName) 
					&& (userNameCell.getText().trim()).equalsIgnoreCase(userName)) {
				return true;
			}
				
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				
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
	
	public boolean findExistingUser(String customerName, String userName, String roleName) {
		setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		String customerNameXPath;
		String userNameXPath;
		String roleNameXPath;
		
		WebElement customerNameCell;
		WebElement userNameCell;
		WebElement roleNameCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			userNameXPath     = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			roleNameXPath     = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[3]";

			customerNameCell = table.findElement(By.xpath(customerNameXPath));
			userNameCell     = table.findElement(By.xpath(userNameXPath));
			roleNameCell     = table.findElement(By.xpath(roleNameXPath));
			
			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName) && (userNameCell.getText().trim()).equalsIgnoreCase(userName) 
					&& (roleNameCell.getText().trim()).equalsIgnoreCase(roleName)) {
				return true;
			}
				
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				
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
	
	public String getUserRole(String userName) {
		setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		String userNameXPath;
		String roleNameXPath;
		
		WebElement userNameCell;
		WebElement roleNameCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			userNameXPath     = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			
			userNameCell     = table.findElement(By.xpath(userNameXPath));
			
			if ((userNameCell.getText().trim()).equalsIgnoreCase(userName)) {
				roleNameXPath   = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[3]";
				roleNameCell = table.findElement(By.xpath(roleNameXPath));
				
				return roleNameCell.getText().trim();
			}
				
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				
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
	
	public String getUserStatus (String userName) {
		setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		String userNameXPath;
		String userStatusXPath;
		
		WebElement userNameCell;
		WebElement userStatusCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			userNameXPath     = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			
			userNameCell     = table.findElement(By.xpath(userNameXPath));
			
			if ((userNameCell.getText().trim()).equalsIgnoreCase(userName)) {
				userStatusXPath   = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[4]";
				userStatusCell = table.findElement(By.xpath(userStatusXPath));
				
				return userStatusCell.getText().trim();
			}
				
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				
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
	
	public boolean editUser(String userName, String role, String timeZone, boolean accountEnable) {
		setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		String userNameXPath;
		String actionEditXPath;
		
		WebElement userNameCell;
		WebElement actionEditCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			userNameXPath     = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			
			userNameCell      = table.findElement(By.xpath(userNameXPath));
			
			if ((userNameCell.getText().trim()).equalsIgnoreCase(userName)) {
				actionEditXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[5]/a[1]";
				
				actionEditCell = table.findElement(By.xpath(actionEditXPath));
				
				actionEditCell.click();
				
				List<WebElement> options = dropDownRole.findElements(By.tagName("option"));
				for (WebElement option : options) {
					if(option.getText().trim().equals(role))
						option.click();
				}
				
				List<WebElement> optionsTZ = dropDownTimeZone.findElements(By.tagName("option"));
				for (WebElement optionTZ : optionsTZ) {
					if(optionTZ.getText().trim().equals(timeZone))
						optionTZ.click();
				}
				
				if (accountEnable) {
					if (!inputAccountEnabled.isSelected())
						inputAccountEnabled.click();
				}
				else {
					if (inputAccountEnabled.isSelected())
						inputAccountEnabled.click();
				}
				
				this.btnOk.click();
				
				if (table.isDisplayed())
					return true;
				
				return false;
			}
				
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				
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
	
	public boolean resetUserPassword(String userName, String newPassword) {
		setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		String userNameXPath;
		String actionResetPWDXPath;
		
		WebElement userNameCell;
		WebElement actionResetPWDCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			userNameXPath     = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			
			userNameCell      = table.findElement(By.xpath(userNameXPath));
			
			if ((userNameCell.getText().trim()).equalsIgnoreCase(userName)) {
				actionResetPWDXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[5]/a[2]";
				
				actionResetPWDCell = table.findElement(By.xpath(actionResetPWDXPath));
				
				actionResetPWDCell.click();
				
				inputNewPassword.sendKeys(newPassword);
				inputNewPasswordConfirm.sendKeys(newPassword);
				
				btnOk.click();
				
				if (table.isDisplayed())
					return true;
				
				return false;
			}
				
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}