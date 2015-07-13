/**
 * 
 */
package surveyor.scommon.source;

import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.FindBy;

import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class ManageLocationsPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Picarro/ManageLocations";
	public static final String STRPageTitle = "Manage Locations - Surveyor";
	
	//@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div/div[1]/div[1]/a")
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a")
	protected WebElement btnAddNewLocation;	
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Administrator')]")
	protected WebElement dropDownAdministrator;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Log Out')]")
	protected WebElement linkLogOut;
	
	@FindBy(how = How.XPATH, using = "//*[@id='Description']")
	protected WebElement inputLocationDesc;
	
	@FindBy(how = How.XPATH, using = "//*[@id='CustomerId']")
	protected WebElement dropDownCustomer;
	
	@FindBy(how = How.XPATH, using = "//*[@id='buttonCustomerOk']")
	protected WebElement btnOK;
	
	@FindBy(how = How.ID, using = "buttonCancel")
	protected WebElement btnCancel;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[5]/a")
	protected WebElement btnEditLocation;
	
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
	
	public ManageLocationsPage(WebDriver driver, String baseURL, TestSetup testSetup, String urlPath) {
		super(driver, testSetup, baseURL, baseURL + urlPath);
	}
	
	public void addNewLocation(String locationDesc, String customer) {
		this.btnAddNewLocation.click();
				
		this.inputLocationDesc.sendKeys(locationDesc);
		
		List<WebElement> options = this.dropDownCustomer.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if(customer.equalsIgnoreCase(option.getText().trim()))
				option.click();
		}
		
		this.btnOK.click();
		
		if (isElementPresent(this.panelDuplicationErrorXPath)){
			WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
			if (panelError.getText().equalsIgnoreCase("Please, correct the following errors:"))
				this.btnCancel.click();
		}		
	}
	
	public boolean findExistingLocation(String customerName, String locationName) {
		setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		String customerNameXPath;
		String locationNameXPath;
		
		WebElement customerNameCell;
		WebElement locationNameCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			locationNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			
			customerNameCell = table.findElement(By.xpath(customerNameXPath));
			locationNameCell = table.findElement(By.xpath(locationNameXPath));
			
			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName) 
					&& (locationNameCell.getText().trim()).equalsIgnoreCase(locationName)) {
				return true;
			}
				
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
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
	
	public boolean editExistingLocation(String customerName, String locationName, String newLocationName) {
		setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		String customerNameXPath;
		String locationNameXPath;
		String actionEditXPath;
		
		WebElement customerNameCell;
		WebElement locationNameCell;
		WebElement actionEditCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);		
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			locationNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			
			customerNameCell = table.findElement(By.xpath(customerNameXPath));
			locationNameCell = table.findElement(By.xpath(locationNameXPath));
			
			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName) && (locationNameCell.getText().trim()).equalsIgnoreCase(locationName)) {
				actionEditXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[3]";
				actionEditCell = table.findElement(By.xpath(actionEditXPath));
				
				actionEditCell.click();
				
				this.inputLocationDesc.clear();
				this.inputLocationDesc.sendKeys(newLocationName);
				
				String curURL = driver.getCurrentUrl();
				
				this.btnOK.click();
				
				if (newLocationName.equalsIgnoreCase("")) {
					if (driver.getCurrentUrl().equalsIgnoreCase(curURL))
						return false;
				}
				
				if (isElementPresent(this.panelDuplicationErrorXPath)) {
					WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
					if (panelError.getText().equalsIgnoreCase("Please, correct the following errors:")) {
						this.btnCancel.click();
						return false;
					}
				}

				return true;
			}
				
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
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
	
	public WebElement getBtnAddNewLocation() {
		return this.btnAddNewLocation;
	}
	
	public WebElement getBtnCancel() {
		return this.btnCancel;
	}
	
	public void clickOnAddNewLocationBtn() {
		this.btnAddNewLocation.click();
	}
	
	public void clickOnFirstEditLocationBtn() {
		this.btnEditLocation.click();
	}
	
	public void clickOnCancelBtn() {
		this.btnCancel.click();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}