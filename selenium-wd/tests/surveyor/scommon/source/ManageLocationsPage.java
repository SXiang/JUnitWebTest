/**
 * 
 */
package surveyor.scommon.source;

import java.util.List;

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
	
	//@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div/div[1]/div[1]/a")
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a")
	private WebElement btnAddNewLocation;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div[1]")
	private WebElement panelDupLocError;
	private String panelDupLocErrorXPath = "//*[@id='page-wrapper']/div/div[2]/div[1]";	
	
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
	
	@FindBy(how = How.XPATH, using = "//*[@id='location-form']/fieldset/div[3]/div[2]/a")
	private WebElement btnCancel;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody")
	private WebElement locationTB;	
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable_length']/label/select")
	private WebElement paginationInput;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Next')]")
	private WebElement nextBtn;
	
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
		this.btnAddNewLocation.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);
		
		this.inputLocationDesc.sendKeys(locationDesc);
		
		List<WebElement> options = this.dropDownCustomer.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if(customer.equalsIgnoreCase(option.getText().trim()))
				option.click();
		}
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);
		
		this.btnOK.click();
		
		if (isElementPresent(this.panelDupLocErrorXPath)){
			WebElement panelError = driver.findElement(By.xpath(this.panelDupLocErrorXPath));
			if (panelError.getText().equalsIgnoreCase("Please, correct the following errors:"))
				this.btnCancel.click();
		}		
	}
	
	public boolean findExistingLocation(String customerName, String locationName) {
		paginationInput.sendKeys("100");
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		//For time being, more generic code should be implemented for iterating the table elements
		List<WebElement> rows = locationTB.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowNum = 1;
		for (WebElement row : rows) {
			if (rowNum > rows.size())
				break;
			
			List<WebElement> cols = row.findElements(By.xpath("//*[@id='datatable']/tbody/tr["+rowNum+"]/td"));
			
			int colNum = 1;
			for (WebElement col : cols) {
				if (colNum == 1 && col.getText().equalsIgnoreCase(customerName)) {
					String strLocationXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
					WebElement location = driver.findElement(By.xpath(strLocationXPath));
					if (location.getText().equalsIgnoreCase(locationName)) {
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
	
	public void editExistingLocation(String customerName, String locationName, String newLocationName) {
		paginationInput.sendKeys("100");
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		//For time being, more generic code should be implemented for iterating the table elements
		List<WebElement> rows = locationTB.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowNum = 1;
		for (WebElement row : rows) {
			if (rowNum > rows.size())
				break;
			
			List<WebElement> cols = row.findElements(By.xpath("//*[@id='datatable']/tbody/tr["+rowNum+"]/td"));
			
			int colNum = 1;
			for (WebElement col : cols) {
				if (colNum == 1 && col.getText().equalsIgnoreCase(customerName)) {
					String strLocationXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
					WebElement location = locationTB.findElement(By.xpath(strLocationXPath));
					if (location.getText().equalsIgnoreCase(locationName)) {
						String strEditXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[3]/a";
						WebElement editBtn = locationTB.findElement(By.xpath(strEditXPath));
						editBtn.click();
						
						if (testSetup.isRunningDebug()) {
							testSetup.slowdownInSeconds(3);
						}
						
						this.inputLocationDesc.clear();
						this.inputLocationDesc.sendKeys(newLocationName);
						this.btnOK.click();
						
						return;
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
	}		
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}