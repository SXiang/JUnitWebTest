/**
 * 
 */
package surveyor.scommon.source;

import static surveyor.scommon.source.SurveyorConstants.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;

import common.source.Log;
import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

/**
 * @author zlu
 *
 */
public class ManageAnalyzersPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Picarro/ManageAnalyzers";
	public static final String STRPageTitle = "Manage Analyzers - Surveyor";
	public static final String STRPageContentText = "Manage Analyzers";
	public static final String STRNewPageContentText = Resources.getResource(ResourceKeys.ManageAnalyzer_NewAnalyzer);
	public static final String STREditPageContentText = Resources.getResource(ResourceKeys.ManageAnalyzer_EditAnalyzer);
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a")
	private WebElement btnAddNewAnalyzer;	
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Administrator')]")
	private WebElement dropDownAdministrator;
	
	@FindBy(how = How.XPATH, using = "//*[@id='SerialNumber']")
	private WebElement inputSerialNumber;
	
	@FindBy(how = How.XPATH, using = "//*[@id='SharedKey']")
	private WebElement inputSharedKey;
	
	@FindBy(how = How.XPATH, using = "//*[@id='SurveyorUnitId']")
	private WebElement dropDownSurveyor;
	
	@FindBy(how = How.XPATH, using = "//*[@class='button-cancel btn btn-danger']")
	private WebElement btnCancel;
	
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[6]/a")
	protected WebElement btnEditAnalyzer;
	
	/**
	 * @param driver
	 * @param baseURL
	 * @param testSetup
	 */
	public ManageAnalyzersPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);
		
		System.out.format("\nThe Manager Analyzers Page URL is: %s\n", this.strPageURL);
	}
		
	public void addNewAnalyzer(String serialNumber, String sharedKey, String cuslocsur) {
		this.btnAddNewAnalyzer.click();
		this.waitForNewPageLoad();
				
		this.inputSerialNumber.sendKeys(serialNumber);
		this.inputSharedKey.sendKeys(sharedKey);
		
		List<WebElement> options = this.dropDownSurveyor.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if(cuslocsur.equals(option.getText().trim()))
				option.click();		
		}		
		
		this.btnOk.click();		
	}	
	
	public boolean addNewAnalyzer(String serialNumber, String sharedKey, String surveyor, String customerName, String locationName) {
		boolean result = true;
		this.btnAddNewAnalyzer.click();
		this.waitForNewPageLoad();
		
		this.inputSerialNumber.sendKeys(serialNumber);
		this.inputSharedKey.sendKeys(sharedKey);
		
		List<WebElement> options = this.dropDownSurveyor.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if (option.getText().trim().equalsIgnoreCase(customerName + " - " + locationName + " - " + surveyor)) {
				option.click();
			}
		}
		
		this.btnOk.click();
		
		if (isElementPresent(this.panelDuplicationErrorXPath)) {
			WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
			String errMsg = panelError.getText();
			if (errMsg.equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))){
				result = false;
				this.btnCancel.click();
			}
		}
		return result;
	}
	
	public boolean findExistingAnalyzer(String customerName, String locationName, String surveyorName, String analyzerName) {
		setPagination(PAGINATIONSETTING_100);
		
		this.waitForTableDataToLoad();
		
		String customerXPath;
		String locationXPath;
		String surveyorXPath;
		String analyzerXPath;
		
		WebElement customerCell;
		WebElement locationCell;
		WebElement surveyorCell;
		WebElement analyzerCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			locationXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			surveyorXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[3]";
			analyzerXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[4]";

			customerCell = table.findElement(By.xpath(customerXPath));
			locationCell = table.findElement(By.xpath(locationXPath));
			surveyorCell = table.findElement(By.xpath(surveyorXPath));
			analyzerCell = table.findElement(By.xpath(analyzerXPath));    
			
			if ((customerCell.getText().trim()).equalsIgnoreCase(customerName) && (locationCell.getText().trim()).equalsIgnoreCase(locationName) 
					&& (surveyorCell.getText().trim()).equalsIgnoreCase(surveyorName) && analyzerCell.getText().trim().equalsIgnoreCase(analyzerName)) {
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
	
	public boolean associateAnalyzerToOtherSurveyor (String customerName, String locationName, String surveyorName, 
			String analyzerName, String cuslocsur) {
		setPagination(PAGINATIONSETTING_100);
		
		this.waitForTableDataToLoad();
		
		String customerXPath;
		String locationXPath;
		String surveyorXPath;
		String analyzerXPath;
		String actionXPath;
		
		WebElement customerCell;
		WebElement locationCell;
		WebElement surveyorCell;
		WebElement analyzerCell;
		WebElement actionCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			locationXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			surveyorXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[3]";
			analyzerXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[4]";

			customerCell = table.findElement(By.xpath(customerXPath));
			locationCell = table.findElement(By.xpath(locationXPath));
			surveyorCell = table.findElement(By.xpath(surveyorXPath));
			analyzerCell = table.findElement(By.xpath(analyzerXPath));    
			
			if ((customerCell.getText().trim()).equalsIgnoreCase(customerName) && 
					(locationCell.getText().trim()).equalsIgnoreCase(locationName) && 
					(surveyorCell.getText().trim()).equalsIgnoreCase(surveyorName) && 
					analyzerCell.getText().trim().equalsIgnoreCase(analyzerName)) {
				Log.info("Found entry at row=" + rowNum);
				actionXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[7]/a[1]";								
				actionCell = table.findElement(By.xpath(actionXPath));
				Log.info("Found entry at row=" + rowNum);
				actionCell.click();
				this.waitForEditPageLoad();
				
				List<WebElement> options = this.dropDownSurveyor.findElements(By.tagName("option"));
				for (WebElement option : options) {
					if(cuslocsur.equals(option.getText().trim()))
						option.click();		
				}
				
				this.btnOk.click();		
				this.waitForPageToLoad();
				
				if (table.isDisplayed())
					return true;
				
				if (isElementPresent(this.panelDuplicationErrorXPath)){
					WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
					if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))) {
						this.btnCancel.click();
						return false;
					}
				}				
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
	
	public boolean editExistingAnalyzer(String customerName, String locationName, String surveyorName, String analyzerName, 
			String keyNew, String cuslocsur, String analyzerNew) {
		setPagination(PAGINATIONSETTING_100);
		
		this.waitForTableDataToLoad();
		
		String customerXPath;
		String locationXPath;
		String surveyorXPath;
		String analyzerXPath;
		String actionXPath;
		
		WebElement customerCell;
		WebElement locationCell;
		WebElement surveyorCell;
		WebElement analyzerCell;
		WebElement actionCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			locationXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			surveyorXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[3]";
			analyzerXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[4]";

			customerCell = table.findElement(By.xpath(customerXPath));
			locationCell = table.findElement(By.xpath(locationXPath));
			surveyorCell = table.findElement(By.xpath(surveyorXPath));
			analyzerCell = table.findElement(By.xpath(analyzerXPath));    
			
			if ((customerCell.getText().trim()).equalsIgnoreCase(customerName) && 
					(locationCell.getText().trim()).equalsIgnoreCase(locationName) && 
					(surveyorCell.getText().trim()).equalsIgnoreCase(surveyorName) && 
					analyzerCell.getText().trim().equalsIgnoreCase(analyzerName)) {
				
				actionXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[7]/a[1]";
				actionCell = table.findElement(By.xpath(actionXPath));
				Log.info("Found entry at row=" + rowNum);
				actionCell.click();
				this.waitForEditPageLoad();
				
				if (!analyzerNew.isEmpty()) {
					this.inputSerialNumber.sendKeys(analyzerNew);
				}
				
				if (!keyNew.isEmpty()) {
					this.inputSharedKey.clear();
					this.inputSharedKey.sendKeys(keyNew);
				}
				
				List<WebElement> options = this.dropDownSurveyor.findElements(By.tagName("option"));
				for (WebElement option : options) {
					if(cuslocsur.equals(option.getText().trim()))
						option.click();		
				}
				
				this.btnOk.click();
				this.waitForPageToLoad();
				
				if (table.isDisplayed())
					return true;
				
				if (isElementPresent(this.panelDuplicationErrorXPath)){
					WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
					if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))) {
						this.btnCancel.click();
						return false;
					}
				}				
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
	
	public void clickOnAddNewAnalyzerBtn() {
		this.btnAddNewAnalyzer.click();
	}
	
	public void clickOnFirstEditAnalyzerBtn() {
		this.btnEditAnalyzer.click();
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

    public void waitForEditPageLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STREditPageContentText);
            }
        });
    }
}