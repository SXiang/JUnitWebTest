/**
 * 
 */
package surveyor.scommon.source;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.FindBy;

import common.source.BasePage;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class ManageCustomersPage extends BasePage {
	public static final String STRURLPath = "/Picarro/ManageCustomers";
	public static final String STRPageTitle = "Manage Customers - Surveyor";
	
	//@FindBy(how = How.XPATH, using = "//a[contains(text(),'Add New Customer')]") //firefox
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div/div[1]/div[1]/a")
	private WebElement btnAddNewCustomer;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Administrator')]")
	private WebElement dropDownAdministrator;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Log Out')]")
	private WebElement linkLogOut;
	
	//@FindBy(how = How.CSS, using = "/PicarroAdministration/ManageCustomers") //firefox
	@FindBy(how = How.XPATH, using = "//*[@id='Name']")
	private WebElement inputCustomerName;
	
	//@FindBy(how = How.ID, using = "id=Eula") //firefox
	@FindBy(how = How.XPATH, using = "//*[@id='Eula']")
	private WebElement textAreaEula;
	
	//@FindBy(how = How.ID, using = "id=buttonCustomerOk")    //firefox
	@FindBy(how=How.XPATH, using = "//*[@id='buttonCustomerOk']")
	private WebElement okButton;
	
	//add more @FindBy here later
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public ManageCustomersPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);
		
		System.out.println("\nThe Manager Customers Page URL is: " + this.strPageURL);
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
	
	public void addNewCustomer(String customerName, String eula) {
		if (this.testSetup.isRunningDebug()) {
			System.out.println(customerName);
			System.out.println(eula);
			System.out.println();
		}
		
		this.btnAddNewCustomer.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);
		
		this.inputCustomerName.sendKeys(customerName);
		this.textAreaEula.sendKeys(eula);
		
		this.okButton.click();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
