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
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class ManageRefGasBottlesPage extends SurveyorBasePage {
	public static final String STRURLPATH = "/Picarro/ManageRefGasBottles";
	public static final String STRPAGETITLE = "Manage Reference Gas Bottles - Surveyor";
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a")
	private WebElement btnAddNewRefGasBottle;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div[1]")
	private WebElement panelDupRgbError;
	private String panelDupRgbErrorXPath = "//*[@id='page-wrapper']/div/div[2]/div[1]";	
	
	@FindBy(how = How.XPATH, using = "//*[@id='SerialNumber']")
	private WebElement inputItemNumber;
	
	@FindBy(how = How.XPATH, using = "//*[@id='BatchId']")
	private WebElement inputLotNumber;
	
	@FindBy(how = How.XPATH, using = "//*[@id='IsotopicValue']")
	private WebElement inputIsoValue;
	
	@FindBy(how = How.XPATH, using = "//*[@id='SurveyorUnitId']")
	private WebElement dropdownSurveyor;
	
	@FindBy(how = How.XPATH, using = "//*[@id='buttonCustomerOk']")
	private WebElement btnOK;
	
	@FindBy(how = How.XPATH, using = "//*[@id='ref-gas-bottle-form']/fieldset/div[4]/div[2]/a")
	private WebElement btnCancel;

	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public ManageRefGasBottlesPage(WebDriver driver, TestSetup testSetup, String strBaseURL) {
		super(driver, testSetup, strBaseURL, strBaseURL + STRURLPATH);
		
		System.out.format("\nThe Manage Ref Gas Bottles Page URL is: %s\n", this.strPageURL);
	}
	
	public ManageRefGasBottlesPage(WebDriver driver, TestSetup testSetup, String baseURL, String urlPath) {
		super(driver, testSetup, baseURL, baseURL + urlPath);
	}	
	
	public void addNewRefGasBottle(String strItemNumber, String strLotNumber, String strIsoValue, String strCusName, String strLocName, String strSurveyor) {
		this.btnAddNewRefGasBottle.click();
		
		this.inputItemNumber.clear();
		this.inputItemNumber.sendKeys(strItemNumber);
		this.inputLotNumber.clear();
		this.inputLotNumber.sendKeys(strLotNumber);
		this.inputIsoValue.clear();
		this.inputIsoValue.sendKeys(strIsoValue);
		
		List<WebElement> options = this.dropdownSurveyor.findElements(By.tagName("option"));
		for (WebElement option : options) { 	
			if (option.getText().trim().equalsIgnoreCase(strCusName + " - " + strLocName + " - " + strSurveyor))
				option.click();
		}
		
		this.btnOK.click();
		
		if (isElementPresent(this.panelDupRgbErrorXPath)){
			WebElement panelError = driver.findElement(By.xpath(this.panelDupRgbErrorXPath));
			if (panelError.getText().equalsIgnoreCase("Please, correct the following errors:"))
				this.btnCancel.click();
		}
	}
	
	public boolean addNewRefGasBottle(String strItemNumber, String strLotNumber, String strIsoValue, String strCusName, String strLocName, String strSurveyor, boolean bFlag) {
		this.btnAddNewRefGasBottle.click();
		
		this.inputItemNumber.sendKeys(strItemNumber);
		this.inputLotNumber.sendKeys(strLotNumber);
		this.inputIsoValue.clear();
		this.inputIsoValue.sendKeys(strIsoValue);
		
		List<WebElement> options = this.dropdownSurveyor.findElements(By.tagName("option"));
		for (WebElement option : options) { 	
			if (option.getText().trim().equalsIgnoreCase(strCusName + " - " + strLocName + " - " + strSurveyor))
				option.click();
		}
		
		String curURL = driver.getCurrentUrl();
		
		this.btnOK.click();
		
		if (strItemNumber.equalsIgnoreCase(""))
			if (driver.getCurrentUrl().equalsIgnoreCase(curURL))
				return false;
		
		if (isElementPresent(this.panelDupRgbErrorXPath)){
			WebElement panelError = driver.findElement(By.xpath(this.panelDupRgbErrorXPath));
			if (panelError.getText().equalsIgnoreCase("Please, correct the following errors:")) {
				this.btnCancel.click();
				return false;
			}
		}
		
		return true;
	}	
	
	public boolean findExistingRefGasBottle(String strItemNumber, String strSurveyor) {
		setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		String strSurveyorXPath;
		String strRGBXPath;
		WebElement surveyorCell;
		WebElement rgbCell;
		
		List<WebElement> rows = table.findElements(By.xpath(strTRXPath));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);		
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			strSurveyorXPath = strTRXPath + "["+rowNum+"]/td[3]";
			strRGBXPath = strTRXPath + "["+rowNum+"]/td[4]";
			
			surveyorCell = table.findElement(By.xpath(strSurveyorXPath));
			rgbCell = table.findElement(By.xpath(strRGBXPath));
			
			if (surveyorCell.getText().equalsIgnoreCase(strSurveyor) && rgbCell.getText().equalsIgnoreCase(strItemNumber)) {				
				return true;
			}
			
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				
				List<WebElement> newRows = table.findElements(By.xpath(strTRXPath));
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

	public boolean findExistingRefGasBottle(String strItemNumber, String strSurveyor, String location, String customer) {
		setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		String customerXPath;
		String locationXPath;
		String strSurveyorXPath;
		String strRGBXPath;
		
		WebElement customerCell;
		WebElement locationCell;
		WebElement surveyorCell;
		WebElement rgbCell;
		
		List<WebElement> rows = table.findElements(By.xpath(strTRXPath));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);		
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerXPath = strTRXPath + "["+rowNum+"]/td[1]";
			locationXPath = strTRXPath + "["+rowNum+"]/td[2]";
			strSurveyorXPath = strTRXPath + "["+rowNum+"]/td[3]";
			strRGBXPath = strTRXPath + "["+rowNum+"]/td[4]";
			
			customerCell = table.findElement(By.xpath(customerXPath));
			locationCell = table.findElement(By.xpath(locationXPath));
			surveyorCell = table.findElement(By.xpath(strSurveyorXPath));
			rgbCell = table.findElement(By.xpath(strRGBXPath));
			
			if (customerCell.getText().trim().equalsIgnoreCase(customer) && locationCell.getText().trim().equalsIgnoreCase(location) &&
					surveyorCell.getText().trim().equalsIgnoreCase(strSurveyor) && rgbCell.getText().trim().equalsIgnoreCase(strItemNumber)) {				
				return true;
			}
			
			if (rowNum == Integer.parseInt(PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				
				List<WebElement> newRows = table.findElements(By.xpath(strTRXPath));
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
	
	public WebElement getBtnAddNewRefGasBottle() {
		return this.btnAddNewRefGasBottle;
	}
	
	public WebElement getBtnCancel() {
		return this.btnCancel;
	}
	
	public void clickOnAddNewRefGasBottleBtn() {
		this.btnAddNewRefGasBottle.click();
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