/**
 * 
 */
package surveyor.scommon.source;

import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_100;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.FindBy;

import common.source.Log;
import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

/**
 * @author zlu
 *
 */
public class ManageLocationsPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Picarro/ManageLocations";
	public static final String STRPageTitle = "Manage Locations - Surveyor";
	String latitude;
	String longitude;
	//@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div/div/div[1]/div[1]/a")
	//@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a")
	@FindBy(css = "a[href='/Picarro/ManageLocation']")
	protected WebElement btnAddNewLocation;	
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Administrator')]")
	protected WebElement dropDownAdministrator;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Log out')]")
	protected WebElement linkLogOut;
	
	@FindBy(id = "Description")
	protected WebElement inputLocationDesc;
	
	@FindBy(how = How.XPATH, using = "//*[@id='point-latitude']")
	protected WebElement inputLocationLat;
	
	@FindBy(how = How.XPATH, using = "//*[@id='point-longitude']")
	protected WebElement inputLocationLong;
	
	@FindBy(how = How.XPATH, using = "//*[@id='CustomerId']")
	protected WebElement dropDownCustomer;
	
	@FindBy(how = How.XPATH, using = "//*[@id='StandardMinimumAmplitude']")
	protected WebElement stdMinAmp;
	
	@FindBy(id = "AssessmentMinimumAmplitude")
	protected WebElement assessmentMinAmp;
	
	@FindBy(how = How.XPATH, using = "//*[@id='OperatorMinimumAmplitude']")
	protected WebElement opdMinAmp;
	
	@FindBy(how = How.XPATH, using = "//*[@id='RapidResponseMinimumAmplitude']")
	protected WebElement RRMinAmp;
	
	@FindBy(how = How.XPATH, using = "//*[@id='NoLowerBound']")
	protected WebElement NoLower;
	
	@FindBy(how = How.XPATH, using = "//*[@id='YesLowerBound']")
	protected WebElement YesLower;
	
	@FindBy(how = How.XPATH, using = "//*[@id='NoUpperBound']")
	protected WebElement NoUpper;
	
	@FindBy(how = How.XPATH, using = "//*[@id='YesUpperBound']")
	protected WebElement YesUpper;
	
	@FindBy(how = How.XPATH, using = "//*[@id='buttonOk']")
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
		
		Log.info("\nThe Manager Locations Page URL is: " + this.strPageURL);
	}
	
	public ManageLocationsPage(WebDriver driver, String baseURL, TestSetup testSetup, String urlPath) {
		super(driver, testSetup, baseURL, baseURL + urlPath);
	}
	
	public void addNewLocation(String locationDesc, String customer, String newLocationName) {
		
		if(newLocationName.equalsIgnoreCase("Santa Clara")){
			 latitude= "37.3971035425739";
		      longitude="-121.98343231897";
		}
		
		this.btnAddNewLocation.click();
				
		this.inputLocationDesc.sendKeys(locationDesc);
		this.inputLocationLat.sendKeys(latitude);
		this.inputLocationLong.sendKeys(longitude);
		
		List<WebElement> options = this.dropDownCustomer.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if(customer.equalsIgnoreCase(option.getText().trim()))
				option.click();
		}
		
		this.stdMinAmp.sendKeys("0.035");
		this.opdMinAmp.sendKeys("5");
		this.RRMinAmp.sendKeys("5");
		this.assessmentMinAmp.sendKeys("0.035");
		
		this.NoLower.sendKeys("-45");
		this.YesLower.sendKeys("-42");
		this.YesUpper.sendKeys("-30");
		this.NoUpper.sendKeys("-25");
		
		this.btnOK.click();
		
		if (isElementPresent(this.panelDuplicationErrorXPath)){
			WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
			if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle)))
				this.btnCancel.click();
		}		
	}
	
	public boolean findExistingLocation(String customerName, String locationName) {
		setPagination(PAGINATIONSETTING_100);
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		String customerNameXPath;
		String locationNameXPath;
		
		WebElement customerNameCell;
		WebElement locationNameCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			locationNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			
			customerNameCell = table.findElement(By.xpath(customerNameXPath));
			locationNameCell = table.findElement(By.xpath(locationNameXPath));
			
			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName) 
					&& (locationNameCell.getText().trim()).equalsIgnoreCase(locationName)) {
				Log.info("Found entry at row=" + rowNum);
				return true;
			}
				
			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				
				rowSize = newRows.size();
				
				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);
				
				rowNum = 0;
			}
		}
		
		return false;
	}
	
	public boolean editExistingLocation(String customerName, String locationName, String newLocationName) {
		setPagination(PAGINATIONSETTING_100);
		
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

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);		
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			locationNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			
			customerNameCell = table.findElement(By.xpath(customerNameXPath));
			locationNameCell = table.findElement(By.xpath(locationNameXPath));
			
			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName) && (locationNameCell.getText().trim()).equalsIgnoreCase(locationName)) {
				actionEditXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[5]";
				actionEditCell = table.findElement(By.xpath(actionEditXPath));
				Log.info("Found entry at row=" + rowNum);
				
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
					if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))) {
						this.btnCancel.click();
						return false;
					}
				}

				return true;
			}
				
			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				
				rowSize = newRows.size();
				
				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);
				
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