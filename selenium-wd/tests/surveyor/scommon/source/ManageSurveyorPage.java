/**
 * 
 */
package surveyor.scommon.source;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;

import common.source.Log;
import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class ManageSurveyorPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Picarro/ManageSurveyors";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.ManageSurveyors_PageTitle);
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.ManageSurveyors_PageTitle);
	public static final String STRNewPageContentText = Resources.getResource(ResourceKeys.ManageSurveyor_NewSurveyor);
	public static final String STREditPageContentText = Resources.getResource(ResourceKeys.ManageSurveyor_EditSurveyor);
	public static final String PAGE_PAGINATIONSETTING = "100";
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a")
	protected WebElement btnAddNewSurveyor;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div[1]")
	protected WebElement panelDupSurError;
	protected String panelDupSurErrorXPath = "//*[@id='page-wrapper']/div/div[2]/div[1]";
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Administrator')]")
	protected WebElement dropDownAdministrator;
	
	@FindBy(id = "Description")
	protected WebElement inputSurveyorDesc;
	
	@FindBy(how = How.XPATH, using = "//*[@id='LocationId']")
	protected WebElement dropDownLocation;
	
	@FindBy(id = "buttonOk")
	protected WebElement btnOK;
	
	@FindBy(how = How.XPATH, using = "//*[@id='surveyor-form']/fieldset/div[3]/div[2]/a")
	protected WebElement btnAddCancel;
	
	@FindBy(how = How.XPATH, using = "//*[@id='surveyor-form']/fieldset/div[4]/div[2]/a")
	protected WebElement btnEditCancel;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[4]/a")
	protected WebElement btnEditSurveyor;
	
    @FindBy(name = "datatable_length")
    private WebElement recordsPerPage;
    
    @FindBy(how = How.XPATH, using = "//*[@id='datatable_filter']/label/input")
	protected WebElement txtSurveyorSearch;
    
    @FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr")
   	protected List<WebElement> tableRows;
    
    @FindBy(how = How.XPATH, using = "//*[@id='datatable']")
   	protected WebElement surveyorsTable;

	//add more @FindBy here later
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public ManageSurveyorPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);
		
		Log.info("\nThe Manager Surveyor Page URL is: " + this.strPageURL);
	}
	
	public ManageSurveyorPage(WebDriver driver, String baseURL, TestSetup testSetup, String urlPath) {
		super(driver, testSetup, baseURL, baseURL + urlPath);
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
	
    public void setRecordsPerPageDropDownListField(String recordsPerPageValue) {
        new Select(recordsPerPage).selectByVisibleText(recordsPerPageValue);
    }

	public void addNewSurveyor(String surveyorDesc, String location) {
		if (this.testSetup.isRunningDebug()) {
			Log.info(surveyorDesc);
			Log.info(location);
		}
		
		this.btnAddNewSurveyor.click();
		
		this.inputSurveyorDesc.sendKeys(surveyorDesc);
		
		List<WebElement> options = this.dropDownLocation.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if (option.getText().trim().equalsIgnoreCase(location))
				option.click();
		}
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(5);		
		
		this.btnOK.click();
		
		this.waitForPageLoad();
	}
	
	public void addNewSurveyor(String surveyorDesc, String locationName, String customerName) {
		if (this.testSetup.isRunningDebug()) {
			Log.info(surveyorDesc);
			Log.info(locationName);
			Log.info(customerName);
		}
		
		this.btnAddNewSurveyor.click();
		this.inputSurveyorDesc.sendKeys(surveyorDesc);
		
		List<WebElement> options = this.dropDownLocation.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if (option.getText().trim().equalsIgnoreCase(customerName + " - " + locationName))
				option.click();		
		}
		
		this.btnOK.click();
		
		if (isElementPresent(this.panelDupSurErrorXPath)){
			WebElement panelError = driver.findElement(By.xpath(this.panelDupSurErrorXPath));
			if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle)))
				this.btnAddCancel.click();
		}
		
		this.waitForPageLoad();
	}	
	
	public boolean findExistingSurveyor(String customerName, String locationName, String surveyorName) {
		setPagination(PAGE_PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		String customerNameXPath;
		String locationNameXPath;
		String surveyorNameXPath;
		
		WebElement customerNameCell;
		WebElement locationNameCell;
		WebElement surveyorNameCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGE_PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGE_PAGINATIONSETTING);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			locationNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			surveyorNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[3]";
			
			customerNameCell = table.findElement(By.xpath(customerNameXPath));
			locationNameCell = table.findElement(By.xpath(locationNameXPath));
			surveyorNameCell = table.findElement(By.xpath(surveyorNameXPath));
			
			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName) && (locationNameCell.getText().trim()).equalsIgnoreCase(locationName) 
					&& (surveyorNameCell.getText().trim()).equalsIgnoreCase(surveyorName)) {
				Log.info("Found entry at row=" + rowNum);
				return true;
			}
				
			if (rowNum == Integer.parseInt(PAGE_PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				
				rowSize = newRows.size();
				
				if (rowSize < Integer.parseInt(PAGE_PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGE_PAGINATIONSETTING);
				
				rowNum = 0;
			}	
		}
		
		return false;
	}
	
	public boolean editExistingSurveyor(String customerName, String locationName, String surveyorName, String surveyorNameNew) {
		setPagination(PAGE_PAGINATIONSETTING);
		
		this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
		
		String customerNameXPath;
		String locationNameXPath;
		String surveyorNameXPath;
		String actionEditXPath;
		
		WebElement customerNameCell;
		WebElement locationNameCell;
		WebElement surveyorNameCell;
		WebElement actionEditCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGE_PAGINATIONSETTING))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGE_PAGINATIONSETTING);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			locationNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			surveyorNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[3]";
			
			customerNameCell = table.findElement(By.xpath(customerNameXPath));
			locationNameCell = table.findElement(By.xpath(locationNameXPath));
			surveyorNameCell = table.findElement(By.xpath(surveyorNameXPath));
			
			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName) && (locationNameCell.getText().trim()).equalsIgnoreCase(locationName) 
					&& (surveyorNameCell.getText().trim()).equalsIgnoreCase(surveyorName)) {
				actionEditXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[4]/a";
				actionEditCell = table.findElement(By.xpath(actionEditXPath));
				
				Log.info("Found cell at xpath=" + actionEditXPath);
				actionEditCell.click();
				this.waitForEditPageLoad();
				
				this.inputSurveyorDesc.clear();
				this.inputSurveyorDesc.sendKeys(surveyorNameNew);
				
				List<WebElement> options = this.dropDownLocation.findElements(By.tagName("option"));
				for (WebElement option : options) {
					if (option.getText().trim().equalsIgnoreCase(customerName + " - " + locationName))
						option.click();
				}
				
				this.btnOK.click();
				
				if (isElementPresent(this.panelDuplicationErrorXPath)) {
					WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
					if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))) {
						this.btnEditCancel.click();
						return false;
					}
				}

				return true;
			}
				
			if (rowNum == Integer.parseInt(PAGE_PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				
				rowSize = newRows.size();
				
				if (rowSize < Integer.parseInt(PAGE_PAGINATIONSETTING))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGE_PAGINATIONSETTING);
				
				rowNum = 0;
			}	
		}
		
		return false;
	}
	
	public void clickOnAddNewSurveyorBtn() {
		this.btnAddNewSurveyor.click();
	}
	
	public void clickOnFirstEditSurveyorBtn() {
		this.btnEditSurveyor.click();
	}
	
	public void clickOnAddCancelBtn() {
		this.btnAddCancel.click();
	}
	
	public void clickOnEditCancelBtn() {
		this.btnEditCancel.click();
	}
	
    public WebElement getTxtSurveyorSearch() {
		return txtSurveyorSearch;
	}

	public List<WebElement> getTableRows() {
		return tableRows;
	}

	public WebElement getSurveyorsTable() {
		return surveyorsTable;
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
            	Log.info("Checking for content on EDIT page: " + STREditPageContentText);
                return d.getPageSource().contains(STREditPageContentText);
            }
        });
    }
    
	public void waitForDataTabletoLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return getSurveyorsTable().isDisplayed();
            }
        });
    }

	
}