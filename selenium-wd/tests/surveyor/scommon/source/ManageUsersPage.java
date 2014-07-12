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
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody")
	private WebElement userTB;	
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable_length']/label/select")
	private WebElement paginationInput;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Next')]")
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
		
		List<WebElement> options = this.dropDownCustomer.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if(customerName.equalsIgnoreCase(option.getText().trim()))
				option.click();
		}		
		this.inputEmail.sendKeys(email);
		this.inputPassword.sendKeys(password);
		this.inputPasswordConfirm.sendKeys(password);
		
		//ignore role for now since Admin is the only choice
		this.btnOk.click();
	}
	
	public boolean findExistingUser(String customerName, String userName) {
		paginationInput.sendKeys("100");
		
		//For time being, more generic code should be implemented for iterating the table elements
		List<WebElement> rows = userTB.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowNum = 1;
		for (WebElement row : rows) {
			List<WebElement> cols = row.findElements(By.xpath("//*[@id='datatable']/tbody/tr["+rowNum+"]/td"));
			
			int colNum = 1;
			for (WebElement col : cols) {
				if (colNum == 1 && col.getText().equalsIgnoreCase(customerName)) {
					String strUserXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
					WebElement user = driver.findElement(By.xpath(strUserXPath));
					if (user.getText().equalsIgnoreCase(userName)) {
						if (testSetup.isRunningDebug())
							System.out.format("The user name found is: %s", user.getText());
						return true;
					}
				}
				
				colNum = colNum + 1;
			}
			
			if (rowNum == 100 && this.nextBtn.isEnabled()) {
				this.nextBtn.click();
				rowNum = 1;
			}
			else {
				rowNum = rowNum + 1;
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