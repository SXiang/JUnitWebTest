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

/**
 * @author zlu
 *
 */
public class ManageUsersPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Picarro/ManageUsers";
	public static final String STRPageTitle = "Manage Users - Surveyor";
	
	//@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div/div[1]/div[1]/a[1]")
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a[1]")
	private WebElement btnAddNewCustomerUser;
	
	//@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div/div[1]/div[1]/a[2]")
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a[2]")
	private WebElement btnAddNewPicarroUser;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div[1]")
	private WebElement panelDupUserError;
	private String panelDupUserErrorXPath = "//*[@id='page-wrapper']/div/div[2]/div[1]";
	
//	//@FindBy(how = How.XPATH, using = "//a[contains(text(),'Administrator')]")
//	@FindBy(how = How.XPATH, using = "//*[@id='wrapper']/nav/ul/li/a")
//	private WebElement dropDownAdministrator;
//	
//	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Log Out')]")
//	private WebElement linkLogOut;	

	@FindBy(how = How.XPATH, using = "//*[@id='User_CustomerId']")
	private WebElement dropDownCustomer;
	
	//@FindBy(how = How.XPATH, using = "//*[@id='User_UserName']")
	@FindBy(how = How.XPATH, using = "//*[@id='User.UserName']")
	private WebElement inputEmail;
	
	@FindBy(how = How.XPATH, using = "//*[@id='User_Password']")
	private WebElement inputPassword;
	
	@FindBy(how = How.XPATH, using = "//*[@id='PasswordConfirm']")
	private WebElement inputPasswordConfirm;
	
	@FindBy(how = How.XPATH, using = "//*[@id='User_RoleId']")
	private WebElement dropDownRole;
	
	@FindBy(how = How.XPATH, using = "//*[@id='buttonCustomerOk']")
	private WebElement btnOk;
	
	@FindBy(how = How.XPATH, using = "//*[@id='user-form']/fieldset/div[9]/div[2]/a")
	private WebElement cancelBtn;	
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody")
	private WebElement userTB;	
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable_length']/label/select")
	private WebElement paginationInput;
	
	//@FindBy(how = How.XPATH, using = "//a[contains(text(),'Next')]")
	@FindBy(how = How.XPATH, using = "//*[@id='datatable_next']")
	private WebElement nextBtn;	
	
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
		
		if (isElementPresent(this.panelDupUserErrorXPath)){
			WebElement panelError = driver.findElement(By.xpath(this.panelDupUserErrorXPath));
			if (panelError.getText().equalsIgnoreCase("Please, correct the following errors:"))
				this.cancelBtn.click();
		}
	}
	
	public void addNewCustomerUser(String customerName, String email, String password, String role) {
		if (this.testSetup.isRunningDebug()) {
			System.out.println(customerName);
			System.out.println(email);
			System.out.println(password);
			System.out.println(role);
		}
		
		this.btnAddNewCustomerUser.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
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
		
		if (isElementPresent(this.panelDupUserErrorXPath)){
			WebElement panelError = driver.findElement(By.xpath(this.panelDupUserErrorXPath));
			if (panelError.getText().equalsIgnoreCase("Please, correct the following errors:"))
				this.cancelBtn.click();
		}		
	}	
	
	public boolean findExistingUser(String customerName, String userName) {
		paginationInput.sendKeys("100");
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		String customerNameXPath;
		String userNameXPath;
		
		WebElement customerNameCell;
		WebElement userNameCell;
		
		//For time being, more generic code should be implemented for iterating the table elements
		List<WebElement> rows = userTB.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < 100)
			loopCount = rowSize;
		else
			loopCount = 100;
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			userNameXPath =     "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			
			customerNameCell = userTB.findElement(By.xpath(customerNameXPath));
			userNameCell = userTB.findElement(By.xpath(userNameXPath));
			
//			System.out.format("\nThe customerName passed is: %s and the userName passed is: %s\n", customerName, userName);
//			System.out.format("\nThe customerName from the cell is: %s and the userName from the cell is: %s\n", customerNameCell.getText().trim(), userNameCell.getText().trim());
			
			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName) && (userNameCell.getText().trim()).equalsIgnoreCase(userName))
				return true;
			
			if (rowNum == 100 && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = userTB.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				
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