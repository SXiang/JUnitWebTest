/**
 * 
 */
package surveyor.scommon.source;

import static surveyor.scommon.source.SurveyorConstants.*;

import java.util.HashMap;
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
import surveyor.scommon.source.DataTablePage.TableColumnType;

/**
 * @author zlu
 *
 */
public class ManageAnalyzersPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Picarro/ManageAnalyzers";
	public static final String STRPageTitle = String.format("%s - %s", Resources.getResource(ResourceKeys.ManageAnalyzers_PageTitle), Resources.getResource(ResourceKeys.Constant_Surveyor));
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.ManageAnalyzers_PageTitle);
	public static final String STRNewPageContentText = Resources.getResource(ResourceKeys.ManageAnalyzer_NewAnalyzer);
	public static final String STREditPageContentText = Resources.getResource(ResourceKeys.ManageAnalyzer_EditAnalyzer);
	public static final String Constant_Customer = Resources.getResource(ResourceKeys.Constant_Customer);
	public static final String Constant_Surveyor = Resources.getResource(ResourceKeys.Constant_Surveyor);
	public static final String Constant_Location = Resources.getResource(ResourceKeys.Constant_Location);
	public static final String Constant_Analyzer = Resources.getResource(ResourceKeys.Constant_Analyzer);
	public static final String Constant_AnalyzerType = Resources.getResource(ResourceKeys.Constant_AnalyzerType);	

	protected String pagination = "100";

	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']//a[@href='/Picarro/ManageAnalyzer']")
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

	@FindBy(how = How.CSS, using = ".validation-summary-errors.panel > .panel-body > ul > li")
	private WebElement warningMsg;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[10]/a[1]")
	protected WebElement btnEditAnalyzer;

	/**
	 * @param driver
	 * @param baseURL
	 * @param testSetup
	 */
	public ManageAnalyzersPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);

		System.out.format("\nThe Manage Analyzers Page URL is: %s\n", this.strPageURL);
	}

	public void addNewAnalyzer(String serialNumber, String sharedKey, String cuslocsur) {
		Log.method("addNewAnalyzer", serialNumber, sharedKey, cuslocsur);
		Log.clickElementInfo("Add New Analyzer");
		this.btnAddNewAnalyzer.click();
		this.waitForNewPageLoad();

		Log.info("Set serial number - '"+serialNumber+"'");
		this.inputSerialNumber.sendKeys(serialNumber);
		Log.info("Set shared key - '"+sharedKey+"'");
		this.inputSharedKey.sendKeys(sharedKey);

		List<WebElement> options = this.dropDownSurveyor.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if (cuslocsur.equals(option.getText().trim())){
				Log.info("Select surveyor '"+cuslocsur+"'");
				option.click();
				break;
			}
		}

		Log.clickElementInfo("OK");
		this.btnOk.click();
	}

	public boolean addNewAnalyzer(String serialNumber, String sharedKey, String surveyor, String customerName, String locationName) {
		Log.method("addNewAnalyzer", serialNumber, sharedKey, surveyor, customerName, locationName);
		boolean result = true;
		Log.clickElementInfo("Add New Analyzer");
		this.btnAddNewAnalyzer.click();
		this.waitForNewPageLoad();

		Log.info("Set serial number - '"+serialNumber+"'");
		this.inputSerialNumber.sendKeys(serialNumber);
		Log.info("Set shared key - '"+sharedKey+"'");
		this.inputSharedKey.sendKeys(sharedKey);

		List<WebElement> options = this.dropDownSurveyor.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if (option.getText().trim().equalsIgnoreCase(customerName + " - " + locationName + " - " + surveyor)) {
				Log.info("Select surveyor '"+customerName + " - " + locationName + " - " + surveyor+"'");
				option.click();
				break;
			}
		}

		Log.clickElementInfo("OK");
		this.btnOk.click();

		if (isElementPresent(this.panelDuplicationErrorXPath)) {
			WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
			String errMsg = panelError.getText();
			if (errMsg.equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))) {
				result = false;
				Log.clickElementInfo("Cancel");
				this.btnCancel.click();
			}
		}
		return result;
	}

	public boolean findExistingAnalyzer(String customerName, String locationName, String surveyorName, String analyzerName) {
		Log.method("findExistingAnalyzer", customerName, locationName, surveyorName, analyzerName);
		Log.info(String.format("Find analyzer '%s', customer = '%s', location = '%s', surveyor = '%s'",
				analyzerName, customerName, locationName, surveyorName));
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

		List<WebElement> rows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			locationXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[2]";
			surveyorXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";
			analyzerXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[4]";

			customerCell = getTable().findElement(By.xpath(customerXPath));
			locationCell = getTable().findElement(By.xpath(locationXPath));
			surveyorCell = getTable().findElement(By.xpath(surveyorXPath));
			analyzerCell = getTable().findElement(By.xpath(analyzerXPath));

			if ((customerCell.getText().trim()).equalsIgnoreCase(customerName) && (locationCell.getText().trim()).equalsIgnoreCase(locationName) && (surveyorCell.getText().trim()).equalsIgnoreCase(surveyorName) && analyzerCell.getText().trim().equalsIgnoreCase(analyzerName)) {
				Log.info("Found entry at row=" + rowNum);
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}
		Log.error(String.format("Analyzer not found: '%s', customer = '%s', location = '%s', surveyor = '%s'",
				analyzerName, customerName, locationName, surveyorName));
		return false;
	}

	public boolean associateAnalyzerToOtherSurveyor(String customerName, String locationName, String surveyorName, String analyzerName, String cuslocsur) {
		Log.method("associateAnalyzerToOtherSurveyor", customerName, locationName, surveyorName, analyzerName, cuslocsur);
		return associateAnalyzerToOtherSurveyor(customerName, locationName, surveyorName, analyzerName, cuslocsur, true);
	}

	public boolean associateAnalyzerToOtherSurveyor(String customerName, String locationName, String surveyorName, String analyzerName, String cuslocsur, boolean confirm) {
		Log.method("associateAnalyzerToOtherSurveyor", customerName, locationName, surveyorName, analyzerName, cuslocsur, confirm);
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

		List<WebElement> rows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			locationXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[2]";
			surveyorXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";
			analyzerXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[4]";

			customerCell = getTable().findElement(By.xpath(customerXPath));
			locationCell = getTable().findElement(By.xpath(locationXPath));
			surveyorCell = getTable().findElement(By.xpath(surveyorXPath));
			analyzerCell = getTable().findElement(By.xpath(analyzerXPath));

			if ((customerCell.getText().trim()).equalsIgnoreCase(customerName) && (locationCell.getText().trim()).equalsIgnoreCase(locationName) && (surveyorCell.getText().trim()).equalsIgnoreCase(surveyorName) && analyzerCell.getText().trim().equalsIgnoreCase(analyzerName)) {
				actionXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[10]/a[1]";
				actionCell = getTable().findElement(By.xpath(actionXPath));
				Log.info("Found entry at row=" + rowNum);
				actionCell.click();
				this.waitForEditPageLoad();

				Log.info("Select surveyor '"+cuslocsur+"'");
				if(!selectDropdownOption(this.dropDownSurveyor, cuslocsur)){
					Log.error("Failed to select surveyor for analyzer '"+cuslocsur+"'");
				}

				Log.clickElementInfo("OK");
				this.btnOk.click();
				
				if (confirm) {	
					this.waitForPageToLoad();
					if (getTable().isDisplayed()) {
						return true;
					}
				}

				else {
					return true;
				}
				if (isElementPresent(this.panelDuplicationErrorXPath)) {
					WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
					if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))) {
						Log.clickElementInfo("Cancel");
						this.btnCancel.click();
						return false;
					}
				}

			}
			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				Log.clickElementInfo("Next");
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

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

	public boolean editExistingAnalyzer(String customerName, String locationName, String surveyorName, String analyzerName, String keyNew, String cuslocsur, String analyzerNew) {
		Log.method("editExistingAnalyzer", customerName, locationName, surveyorName, analyzerName, keyNew, cuslocsur, analyzerNew);
		Log.info(String.format("Edit analyzer '%s', customer = '%s', location = '%s', surveyor = '%s'",
				analyzerName, customerName, locationName, surveyorName));
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

		List<WebElement> rows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			locationXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[2]";
			surveyorXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[3]";
			analyzerXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[4]";

			customerCell = getTable().findElement(By.xpath(customerXPath));
			locationCell = getTable().findElement(By.xpath(locationXPath));
			surveyorCell = getTable().findElement(By.xpath(surveyorXPath));
			analyzerCell = getTable().findElement(By.xpath(analyzerXPath));

			if ((customerCell.getText().trim()).equalsIgnoreCase(customerName) && (locationCell.getText().trim()).equalsIgnoreCase(locationName) && (surveyorCell.getText().trim()).equalsIgnoreCase(surveyorName) && analyzerCell.getText().trim().equalsIgnoreCase(analyzerName)) {

				actionXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[10]/a[1]";
				actionCell = getTable().findElement(By.xpath(actionXPath));
				Log.info("Found entry at row=" + rowNum);
				actionCell.click();
				this.waitForEditPageLoad();

				if (!analyzerNew.isEmpty()) {
					Log.info("Set serial number - '"+analyzerNew+"'");
					this.inputSerialNumber.sendKeys(analyzerNew);
				}

				if (!keyNew.isEmpty()) {
					Log.info("Set shared key - '"+keyNew+"'");
					this.inputSharedKey.clear();
					this.inputSharedKey.sendKeys(keyNew);
				}

				List<WebElement> options = this.dropDownSurveyor.findElements(By.tagName("option"));
				for (WebElement option : options) {
					if (cuslocsur.equals(option.getText().trim())){
						Log.info("Select surveyor '"+cuslocsur+"'");
						option.click();
						break;
					}
				}

				Log.clickElementInfo("OK");
				this.btnOk.click();
				this.waitForPageToLoad();

				if (getTable().isDisplayed())
					return true;

				if (isElementPresent(this.panelDuplicationErrorXPath)) {
					WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
					if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))) {
						Log.clickElementInfo("Cancel");
						this.btnCancel.click();
						return false;
					}
				}
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}
		Log.error(String.format("Analyzer not found: '%s', customer = '%s', location = '%s', surveyor = '%s'",
				analyzerName, customerName, locationName, surveyorName));
		return false;
	}
	
	public boolean areTableColumnsSorted(){
		Log.method("areTableColumnsSorted");
		if(!isCustomerColumnSorted()){
			return false;
		}
		if(!isLocationColumnSorted()){
			return false;
		}
		if(!isSurveyorColumnSorted()){
			return false;
		}
		if(!isAnalyzerColumnSorted()){
			return false;
		}
		if(!isAnalyzerTypeColumnSorted()){
			return false;
		}
		return true;
	}
	
	public boolean isCustomerColumnSorted(){
		Log.method("isCustomerColumnSorted");
		HashMap<String, TableColumnType> columnMap = new HashMap<String, TableColumnType>();
		columnMap.put(Constant_Customer, TableColumnType.String);
		return checkTableSort("datatable_wrapper", columnMap, pagination, getPaginationOption());
	}
	
	public boolean isLocationColumnSorted(){
		Log.method("isLocationColumnSorted");
		HashMap<String, TableColumnType> columnMap = new HashMap<String, TableColumnType>();
		columnMap.put(Constant_Location, TableColumnType.String);
		return checkTableSort("datatable_wrapper", columnMap, pagination, getPaginationOption());
	}
	
	public boolean isSurveyorColumnSorted(){
		Log.method("isSurveyorColumnSorted");
		HashMap<String, TableColumnType> columnMap = new HashMap<String, TableColumnType>();
		columnMap.put(Constant_Surveyor, TableColumnType.String);
		return checkTableSort("datatable_wrapper", columnMap, pagination, getPaginationOption());
	}
	
	public boolean isAnalyzerColumnSorted(){
		Log.method("isAnalyzerColumnSorted");
		HashMap<String, TableColumnType> columnMap = new HashMap<String, TableColumnType>();
		columnMap.put(Constant_Analyzer, TableColumnType.String);
		return checkTableSort("datatable_wrapper", columnMap, pagination, getPaginationOption());
	}
	
	public boolean isAnalyzerTypeColumnSorted(){
		Log.method("isAnalyzerTypeColumnSorted");
		HashMap<String, TableColumnType> columnMap = new HashMap<String, TableColumnType>();
		columnMap.put(Constant_AnalyzerType, TableColumnType.String);
		return checkTableSort("datatable_wrapper", columnMap, pagination, getPaginationOption());
	}
	
	public void clickOnAddNewAnalyzerBtn() {
		Log.clickElementInfo("Add New Analyzer");
		this.btnAddNewAnalyzer.click();
	}

	public void clickOnFirstEditAnalyzerBtn() {
		Log.clickElementInfo("Edit", "on the first analyzer");
		this.btnEditAnalyzer.click();
	}

	public void clickOnCancelBtn() {
		Log.clickElementInfo("Cancel");
		this.btnCancel.click();
	}

	public WebElement getWarningMsg() {
		return warningMsg;
	}

	public WebElement getBtnAddNewAnalyzer() {
		return btnAddNewAnalyzer;
	}

	public WebElement getBtnEditAnalyzer() {
		return btnEditAnalyzer;
	}

	@Override
	public void open(){
		super.open();
		waitForPageLoad();
	}
	
	@Override
	public void waitForPageLoad() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return isPageTitleMatch(d.getTitle(),STRPageContentText);
			}
		});
	}

	public void waitForNewPageLoad() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return isPageTitleMatch(d.getTitle(),STRNewPageContentText);
			}
		});
	}

	public void waitForEditPageLoad() {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return isPageTitleMatch(d.getTitle(),STREditPageContentText);
			}
		});
	}
}