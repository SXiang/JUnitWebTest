/**
 * 
 */
package surveyor.scommon.source;

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
public class ManageUsersPage extends BasePage {
	public static final String STRURLPath = "/Picarro/ManageUsers";
	public static final String STRPageTitle = "Manage Users - Surveyor";
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div/div[1]/div[1]/a")
	private WebElement btnAddNewUser;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Administrator')]")
	private WebElement dropDownAdministrator;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Log Out')]")
	private WebElement linkLogOut;	
	
	@FindBy(how = How.XPATH, using = "//*[@id='User_CustomerId']")
	private WebElement dropDownCustomer;
	
	@FindBy(how = How.XPATH, using = "//*[@id='User_UserName']")
	private WebElement inputEmail;
	
	@FindBy(how = How.XPATH, using = "//*[@id='User_Password']")
	private WebElement inputPassword;
	
	@FindBy(how = How.XPATH, using = "//*[@id='PasswordConfirm']")
	private WebElement inputPasswordConfirm;
	
	@FindBy(how = How.XPATH, using = "//*[@id='User_RoleId']")
	private WebElement dropDownRole;
	
	@FindBy(how = How.XPATH, using = "//*[@id='buttonCustomerOk']")
	private WebElement btnOk;
	
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
	
	public LoginPage logout() {
		this.dropDownAdministrator.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(1);
		
		this.linkLogOut.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);
		
		LoginPage loginPage = new LoginPage(this.driver, this.strBaseURL, this.testSetup);
		
		return loginPage;
	}
	
	public void addNewUser(String customerName, String email, String password, String role) {
		if (this.testSetup.isRunningDebug()) {
			System.out.println(customerName);
			System.out.println(email);
			System.out.println(password);
			System.out.println(role);
		}
		
		this.btnAddNewUser.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);
		
		this.dropDownCustomer.sendKeys(customerName);
		this.inputEmail.sendKeys(email);
		this.inputPassword.sendKeys(password);
		this.inputPasswordConfirm.sendKeys(password);
		
		//ignore role for now since Admin is the only choice
		
		this.btnOk.click();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
