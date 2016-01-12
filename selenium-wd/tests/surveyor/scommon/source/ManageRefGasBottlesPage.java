/**
 * 
 */
package surveyor.scommon.source;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class ManageRefGasBottlesPage extends SurveyorBasePage {
	public static final String STRURLPATH = "/Picarro/ManageRefGasBottles";
	public static final String STRPAGETITLE = Resources.getResource(ResourceKeys.ManageRefGasBottles_PageTitle);
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.ManageRefGasBottles_PageTitle);
	public static final String STRNewPageContentText = Resources.getResource(ResourceKeys.AddRefGasBottle_PageTitle);
	
	@FindBy(css = "a[class='btn btn-primary']")
	private WebElement btnAddNewRefGasBottle;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div[1]")
	private WebElement panelDupRgbError;
	private String panelDupRgbErrorXPath = "//*[@id='page-wrapper']/div/div[2]/div[1]";	
	
	@FindBy(id = "BatchId")
	private WebElement inputLotNumber;
	
	@FindBy(how = How.XPATH, using = "//*[@id='IsotopicValue']")
	private WebElement inputIsoValue;
	
	@FindBy(how = How.XPATH, using = "//*[@id='SurveyorUnitId']")
	private WebElement dropdownSurveyor;
	
	@FindBy(id = "buttonOk")
	private WebElement btnOK;
	
	@FindBy(how = How.XPATH, using = "//*[@id='ref-gas-bottle-form']/fieldset/div[4]/div[2]/a")
	private WebElement btnCancel;

	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 */
	public ManageRefGasBottlesPage(WebDriver driver, TestSetup testSetup, String strBaseURL) {
		super(driver, testSetup, strBaseURL, strBaseURL + STRURLPATH);
		
		System.out.format("\nThe Manage Ref Gas Bottles Page URL is: %s\n", this.strPageURL);
	}
	
	public ManageRefGasBottlesPage(WebDriver driver, TestSetup testSetup, String baseURL, String urlPath) {
		super(driver, testSetup, baseURL, baseURL + urlPath);
	}	
	
	public void addNewRefGasBottle(String strLotNumber, String strIsoValue, String strCusName, String strLocName, String strSurveyor) {
		this.btnAddNewRefGasBottle.click();
		
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
			if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle)))
				this.btnCancel.click();
		}
		
		this.waitForPageLoad();
	}
	
	public boolean addNewRefGasBottle(String strLotNumber, String strIsoValue, String strCusName, String strLocName, String strSurveyor, boolean bFlag)  {
		this.btnAddNewRefGasBottle.click();
		this.inputLotNumber.sendKeys(strLotNumber);
		this.inputIsoValue.clear();
		this.inputIsoValue.sendKeys(strIsoValue);
		
		List<WebElement> options = this.dropdownSurveyor.findElements(By.tagName("option"));
		for (WebElement option : options) { 	
			if (option.getText().trim().equalsIgnoreCase(strCusName + " - " + strLocName + " - " + strSurveyor))
				option.click();
		}
		
		this.btnOK.click();
		JavascriptExecutor js = (JavascriptExecutor)driver; 
		js.executeScript("arguments[0].click();", btnOK);  	
	
		if(bFlag==false){
			String att1 =this.inputLotNumber.getAttribute("required");
			String att2 =this.inputIsoValue.getAttribute("required");
			if (att1 != null && att2 != null) {

				return false;
			}
		}
		
		if (isElementPresent(this.panelDupRgbErrorXPath)){
			WebElement panelError = driver.findElement(By.xpath(this.panelDupRgbErrorXPath));
			if (panelError.getText().equalsIgnoreCase("Please fill out this field.")) {
				this.btnCancel.click();
				return false;
			}
		}
		this.waitForPageLoad();
		
		return true;
	}	
	
	public boolean findExistingRefGasBottle(String strLotNumber, String strSurveyor) {
		setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		String strSurveyorXPath;
		String strLotNumberXPath;
		WebElement surveyorCell;
		WebElement lotNumberCell;
		
		List<WebElement> rows = table.findElements(By.xpath(strTRXPath));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);		
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			strSurveyorXPath = strTRXPath + "["+rowNum+"]/td[3]";
			strLotNumberXPath = strTRXPath + "["+rowNum+"]/td[5]";
			
			surveyorCell = table.findElement(By.xpath(strSurveyorXPath));
			lotNumberCell = table.findElement(By.xpath(strLotNumberXPath));
			
			if (surveyorCell.getText().equalsIgnoreCase(strSurveyor) && lotNumberCell.getText().equalsIgnoreCase(strLotNumber)) {				
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

	public boolean findExistingRefGasBottle(String strLotNumber, String strSurveyor, String location, String customer) {
		setPagination(PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		String locationXPath;
		String strSurveyorXPath;
		String strLotNumberXPath;
		
		WebElement locationCell;
		WebElement surveyorCell;
		WebElement lotNumberCell;
		
		List<WebElement> rows = table.findElements(By.xpath(strTRXPath));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING);		
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			locationXPath = strTRXPath + "["+rowNum+"]/td[1]";
			strSurveyorXPath = strTRXPath + "["+rowNum+"]/td[2]";
			strLotNumberXPath = strTRXPath + "["+rowNum+"]/td[4]";
			
			locationCell = table.findElement(By.xpath(locationXPath));
			surveyorCell = table.findElement(By.xpath(strSurveyorXPath));
			lotNumberCell = table.findElement(By.xpath(strLotNumberXPath));
			
			if (locationCell.getText().trim().equalsIgnoreCase(location) &&
					surveyorCell.getText().trim().equalsIgnoreCase(strSurveyor) && lotNumberCell.getText().trim().equalsIgnoreCase(strLotNumber)) {				
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

    @Override
	public void waitForPageLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STRPageContentText);
            }
        });
    }

	public void waitForNewPageLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STRNewPageContentText);
            }
        });
    }
}