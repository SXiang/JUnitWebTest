/**
 * 
 */
package surveyor.scommon.source;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import common.source.Log;
import common.source.TestSetup;

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
	protected String btnAddNewSurveyorXPath = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a";
	
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
	
	@FindBy(css = "a[class='button-cancel btn btn-danger']")
	protected WebElement btnEditCancel;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[4]/a")
	protected WebElement btnEditSurveyor;

	//*[@id="datatable"]/tbody/tr[1]/td[3]/a
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[3]/a")
	protected WebElement btnEditCustomerSurveyor;
	
    @FindBy(name = "datatable_length")
    private WebElement recordsPerPage;
    

    @FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[1]")
    protected WebElement tdLocationValue;
    
    @FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[2]")
    protected WebElement tdSurveyorValue;
    
    @FindBy(how = How.XPATH, using = "//*[@id='datatable']/thead/tr/th[1]")
	protected WebElement theadLocation;

    @FindBy(how = How.XPATH, using = "//*[@id='datatable_filter']/label/input")
	protected WebElement txtSurveyorSearch;
    
    @FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr")
   	protected List<WebElement> tableRows;
    
    @FindBy(how = How.XPATH, using = "//*[@id='datatable']")
   	protected WebElement surveyorsTable;


	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/thead/tr/th[2]")
	protected WebElement theadSurveyor;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr")
	protected List<WebElement> rows;
	
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
	
	public boolean addNewSurveyor(String surveyorDesc, String locationName, String customerName) {
		boolean result = true;
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
			String errMsg = panelError.getText();
			if (errMsg.equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))){
				result = false;
				this.btnAddCancel.click();
			}
		}
		
		return result;
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
		
		List<WebElement> rows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
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
			
			customerNameCell = getTable().findElement(By.xpath(customerNameXPath));
			locationNameCell = getTable().findElement(By.xpath(locationNameXPath));
			surveyorNameCell = getTable().findElement(By.xpath(surveyorNameXPath));
			
			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName) && (locationNameCell.getText().trim()).equalsIgnoreCase(locationName) 
					&& (surveyorNameCell.getText().trim()).equalsIgnoreCase(surveyorName)) {
				Log.info("Found entry at row=" + rowNum);
				return true;
			}
				
			if (rowNum == Integer.parseInt(PAGE_PAGINATIONSETTING) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				
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
		
		List<WebElement> rows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
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
			
			customerNameCell = getTable().findElement(By.xpath(customerNameXPath));
			locationNameCell = getTable().findElement(By.xpath(locationNameXPath));
			surveyorNameCell = getTable().findElement(By.xpath(surveyorNameXPath));
			
			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName) && (locationNameCell.getText().trim()).equalsIgnoreCase(locationName) 
					&& (surveyorNameCell.getText().trim()).equalsIgnoreCase(surveyorName)) {
				actionEditXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[4]/a";
				actionEditCell = getTable().findElement(By.xpath(actionEditXPath));
				
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
				List<WebElement> newRows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
				
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
	
	public void clickOnCustomerFirstEditSurveyorBtn() {
		this.btnEditCustomerSurveyor.click();
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
    

	public boolean searchSurveyor(String locationName, String surveyorName) {
		this.getInputSearch().sendKeys(surveyorName);
		try {
			if (this.tdSurveyorValue.getText().contentEquals(surveyorName)) {
				if (this.tdLocationValue.getText().contentEquals(locationName))
					return true;
			}
		} catch (NoSuchElementException ne) {
			Log.info(ne.toString());
			return false;
		}
		return false;
	}

	public WebElement getTheadSurveyor() {
		return this.theadSurveyor;
	}

	public WebElement getTheadLocation() {
		return this.theadLocation;
	}
	
	public List<String> getSurveyorList(boolean allPages, int paginationSize) {
		List<String> surveyorList = new ArrayList<String>();

		String pageSizeStr = String.valueOf(paginationSize);
		setPagination(pageSizeStr);
		waitForPageLoad();

		String surveyorXPath;
		WebElement surveyorCell;

		
		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(pageSizeStr))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(pageSizeStr);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			surveyorXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[2]";
			surveyorCell = getTable().findElement(By.xpath(surveyorXPath));

			surveyorList.add(surveyorCell.getText().trim());

			if (rowNum == Integer.parseInt(pageSizeStr)
					&& !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = getTable().findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(pageSizeStr))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(pageSizeStr);

				rowNum = 0;
			}
		}
		return surveyorList;
	}
	
	public List<String> getLocationList(boolean allPages, int paginationSize) {
		List<String> locationList = new ArrayList<String>();

		String pageSizeStr = String.valueOf(paginationSize);
		setPagination(pageSizeStr);
		waitForPageLoad();

		String locationXPath;
		WebElement locationCell;

		List<WebElement> rows = getTable().findElements(By
				.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(pageSizeStr))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(pageSizeStr);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			locationXPath = "//*[@id='datatable']/tbody/tr[" + rowNum
					+ "]/td[1]";
			locationCell = getTable().findElement(By.xpath(locationXPath));

			locationList.add(locationCell.getText().trim());

			if (rowNum == Integer.parseInt(pageSizeStr)
					&& !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup
						.getSlowdownInSeconds());
				List<WebElement> newRows = getTable().findElements(By
						.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(pageSizeStr))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(pageSizeStr);

				rowNum = 0;
			}
		}
		return locationList;
	}
	
	public boolean isDuplicateSurMsgPresent(String locationName){
		String STRDuplicateSurMsg = "Surveyor name already exists for location " + locationName +", please try another name.";
		return this.liDuplicateMsg.getText().equals(STRDuplicateSurMsg);
	}


	public void waitForDataTabletoLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return getSurveyorsTable().isDisplayed();
            }
        });
    }

	public boolean isAddNewSurveyorBtnPresent() {
		return isElementPresent(this.btnAddNewSurveyorXPath);
	}

}