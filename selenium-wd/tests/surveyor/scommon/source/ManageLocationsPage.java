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
public class ManageLocationsPage extends BasePage {
	public static final String STRURLPath = "/Picarro/ManageLocations";
	public static final String STRPageTitle = "Manage Locations - Surveyor";
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div/div[1]/div[1]/a")
	private WebElement btnAddNewLocation;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Administrator')]")
	private WebElement dropDownAdministrator;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Log Out')]")
	private WebElement linkLogOut;
	
	@FindBy(how = How.XPATH, using = "//*[@id='Description']")
	private WebElement inputLocationDesc;
	
	@FindBy(how = How.XPATH, using = "//*[@id='CustomerId']")
	private WebElement dropDownCustomer;
	
	@FindBy(how = How.XPATH, using = "//*[@id='buttonCustomerOk']")
	private WebElement btnOK;
	
	//add more @FindBy here later
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public ManageLocationsPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);
		
		System.out.println("\nThe Manager Locations Page URL is: " + this.strPageURL);
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
	
	public void addNewLocation(String locationDesc, String customer) {
		if (this.testSetup.isRunningDebug()) {
			System.out.println(locationDesc);
			System.out.println(customer);
		}
		
		this.btnAddNewLocation.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);
		
		this.inputLocationDesc.sendKeys(locationDesc);
		this.dropDownCustomer.sendKeys(customer);
		
		this.btnOK.click();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
