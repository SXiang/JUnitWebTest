/**
 *
 */
package surveyor.scommon.source;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;

import common.source.Constants;
import common.source.EnumUtility;
import common.source.Log;
import common.source.RetryUtil;
import common.source.TestSetup;
import common.source.WebElementExtender;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.source.DataTablePage.TableColumnType;

import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class ManageCustomersPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Picarro/ManageCustomers";
	public static final String STRPageTitle = Resources.getResource(ResourceKeys.ManageCustomers_PageTitle);
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.ManageCustomers_PageTitle);
	public static final String STRNewPageContentText = Resources.getResource(ResourceKeys.ManageCustomer_NewCustomer);
	public static final String STREditPageContentText = Resources.getResource(ResourceKeys.ManageCustomer_EditCustomer);
	public static final String Constant_Customer = Resources.getResource(ResourceKeys.Constant_Customer);
	public static final String Constant_Status = Resources.getResource(ResourceKeys.Constant_Status);
	protected String pagination = "100";


	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a")
	public WebElement btnAddNewCustomer;
	private String btnAddNewCustomerXPath = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a";

	@FindBy(how = How.XPATH, using = "//*[@id='name']")
	public WebElement inputCustomerName;

	@FindBy(how = How.XPATH, using = "//*[@id='name-error']")
	public WebElement lblNameError;
	
	private static final String EULAXPath = "eula";

	@FindBy(how = How.XPATH, using = "//*[@id='eula']")
	private WebElement textAreaEula;

	@FindBy(id = "eula-error")
	private WebElement lblEulaError;

	@FindBy(how = How.XPATH, using = "//*[@id='customer-form']/fieldset/div[6]/div[2]/a")
	private WebElement cancelAddBtn;

	@FindBy(how = How.XPATH, using = "//*[@id='customer-form']/fieldset/div[5]/div[2]/a")
	private WebElement cancelEditBtn;

	@FindBy(how = How.XPATH, using = "//*[@id='active']")
	private WebElement inputAccountEnabled;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[3]/a")
	private WebElement btnEditCustomer;
	private String btnEditCustomerXPath = "//*[@id='datatable']/tbody/tr[1]/td[3]/a";

	@FindBy(id = "LicencedFeatureId-Report Metadata")
	private WebElement inputReportMetadata;

	@FindBy(id = "LicencedFeatureId-Report ShapeFile")
	private WebElement inputReportShapeFile;

	public WebElement getLblNameError(){
		return this.lblNameError;
	}
	
	public WebElement getInputCustomerName(){
		return this.inputCustomerName;
	}
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */

	public ManageCustomersPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);

		Log.info("\nThe Manage Customers Page URL is: " + this.strPageURL);
	}

	public boolean selectLicensedFeatures(LicensedFeatures... lfs) {
		Log.method("selectLicensedFeatures", Arrays.toString(lfs));
		if (lfs != null) {
			for (LicensedFeatures lf : lfs) {
				selectLicensedFeature(lf);
			}
		}
		return true;
	}

	public boolean unselectLicensedFeatures(LicensedFeatures... lfs) {
		Log.method("unselectLicensedFeatures", Arrays.toString(lfs));
		if (lfs != null) {
			for (LicensedFeatures lf : lfs) {
				selectLicensedFeature(lf, false);
			}
		}
		return true;
	}

	public boolean selectLicensedFeature(LicensedFeatures lf) {
		Log.method("selectLicensedFeature", lf);
		return selectLicensedFeature(lf, true);
	}

	public boolean selectLicensedFeature(LicensedFeatures lf, boolean enableFeature) {
		Log.method("selectLicensedFeature", lf, enableFeature);
		WebElement inputBox = getInputBoxOfLicensedFeature(lf);
		if (inputBox != null) {
			if (enableFeature) {
				if (!inputBox.isSelected()){
					Log.info("Select licensed features - '"+lf+"'");
					jsClick(inputBox);
				}
			} else {
				if (inputBox.isSelected()){
					Log.info("Unselect licensed features - '"+lf+"'");
					jsClick(inputBox);
				}
			}
		}
		return enableFeature;
	}

	private WebElement getInputBoxOfLicensedFeature(LicensedFeatures lf) {
		Log.method("getInputBoxOfLicensedFeature", lf);
		String elementId = String.format("LicencedFeatureId-%s", lf.toString());
		Log.info(String.format("Checkbox element id -> %s", elementId));
		return WebElementExtender.findElementIfExists(driver, elementId);
	}

	public boolean addNewCustomer(String customerName, String eula) {
		Log.method("addNewCustomer", customerName, eula);
		return addNewCustomer(customerName, eula, true /* enableCustomer */);
	}

	public boolean addNewCustomer(String customerName, String eula, LicensedFeatures... lfs) {
		Log.method("addNewCustomer", customerName, eula, Arrays.toString(lfs));
		return addNewCustomer(customerName, eula, true /* enableCustomer */, lfs);
	}

	public boolean addNewCustomer(String customerName, String eula, boolean enableCustomer) {
		Log.method("addNewCustomer", customerName, eula, enableCustomer);
		return addNewCustomer(customerName, eula, enableCustomer, null /* licensed features */);
	}

	public boolean addNewCustomer(String customerName, String eula, boolean enableCustomer, LicensedFeatures[] lfs) {
		Log.method("addNewCustomer", customerName, eula, enableCustomer, Arrays.toString(lfs));
		Log.clickElementInfo("Add New Customer");
		this.btnAddNewCustomer.click();
		this.waitForNewPageLoad();

		this.waitForElementToBeDisplayed(By.id("name"));
		if (this.inputCustomerName == null) {
			Log.info("Did NOT find this.inputCustomerName element");
		}
		if (!this.inputCustomerName.isDisplayed()) {
			Log.info("this.inputCustomerName is NOT displayed");
		}

		Log.info("Set customer name - '"+customerName+"'");
		this.inputCustomerName.sendKeys(customerName);

		setEULAText(eula);
		enabledDisableCustomer(enableCustomer);
		selectLicensedFeatures(lfs);
		Log.clickElementInfo("Ok");
		this.btnOk.click();

		if (isElementPresent(this.summaryErrorsBy)) {
			WebElement panelError = panelErrors.get(0);
			if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.ManageCustomer_ErrorMsg))) {
				Log.clickElementInfo("Cancel");
				this.cancelAddBtn.click();
				return false;
			}
		}

		this.waitForPageLoad();
		return true;
	}

	public void setEULAText(String eula) {
		Log.method("setEULAText", eula);
		sendKeysToElement(this.textAreaEula, eula);
	}

	public void enabledDisableCustomer(boolean enableCustomer) {
		Log.method("enabledDisableCustomer", enableCustomer);
		Log.info("Customer account "+(enableCustomer?"enabled":"disabled"));
		if (enableCustomer) {
			if (!isAccountEnabled())
				inputAccountEnabled.click();
		} else {
			if (isAccountEnabled())
				inputAccountEnabled.click();
		}
	}

	public boolean isAccountEnabled() {
		Log.method("isAccountEnabled");
		return inputAccountEnabled.isSelected();
	}

	public boolean verifyEulaValidation() {
		Log.method("verifyEulaValidation");
		return verifyFieldNotBlank(this.lblEulaError, "Eula");
	}

	public boolean verifyNameValidation() {
		Log.method("verifyNameValidation");
		return verifyFieldNotBlank(this.lblNameError, "Name");
	}

	public WebElement getCancelAddBtn(){
		return this.cancelAddBtn;
	}
	
	public boolean findExistingCustomer(String customerName, boolean enabledStatus) {
		Log.method("findExistingCustomer", customerName, enabledStatus);
		Log.info(String.format("Find customer '%s'",customerName));
		setPaginationAny(PAGINATIONSETTING_100);

		String customerNameXPath;
		String enabledStatusXPath;
		WebElement customerNameCell;
		WebElement enabledStatusCell;

		List<WebElement> rows = getTable().findElements(By.xpath(DATATABLE_TBODY_TR));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = DATATABLE_TBODY_TR + "[" + rowNum + "]/td[1]";
			customerNameCell = getTable().findElement(By.xpath(customerNameXPath));

			enabledStatusXPath = DATATABLE_TBODY_TR + "[" + rowNum + "]/td[2]";
			enabledStatusCell = getTable().findElement(By.xpath(enabledStatusXPath));

			Log.info(String.format("Customer: %s; Status: %s", customerNameCell.getText(), enabledStatusCell.getText()));

			String enabledStatusString = Resources.getResource(ResourceKeys.Constant_Enabled);
			if (!enabledStatus) {
				enabledStatusString = Resources.getResource(ResourceKeys.Constant_Disabled);
			}

			if (customerNameCell.getText().trim().equalsIgnoreCase(customerName) && enabledStatusCell.getText().trim().equalsIgnoreCase(enabledStatusString)) {
				Log.info(String.format("Found existing customer with name - '%s' and enabled status - '%b' at row number - %d", customerName, enabledStatus, rowNum));
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				Log.clickElementInfo("Next");
				toNextPage();
				List<WebElement> newRows = getTable().findElements(By.xpath(DATATABLE_TBODY_TR));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}
		Log.info(String.format("Customer not found: '%s'",customerName));
		return false;
	}

	public boolean findCustomerAndOpenEditPage(String customerName) {
		Log.method("findCustomerAndOpenEditPage", customerName);
		Log.info(String.format("Find customer '%s'",customerName));
		setPaginationAny(PAGINATIONSETTING_100);

		String customerNameXPath;
		String actionXPath;

		WebElement customerNameCell;
		WebElement actionCell;

		List<WebElement> rows = getTable().findElements(By.xpath(DATATABLE_TBODY_TR));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = DATATABLE_TBODY_TR + "[" + rowNum + "]/td[1]";
			customerNameCell = getTable().findElement(By.xpath(customerNameXPath));

			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName)) {
				Log.info(String.format("Found existing customer with name - '%s' at row number - %d", customerName, rowNum));
				actionXPath = DATATABLE_TBODY_TR + "[" + rowNum + "]/td[3]";
				actionCell = getTable().findElement(By.xpath(actionXPath));

				actionCell.click();
				this.waitForEditPageLoad();

				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				Log.clickElementInfo("Next");
				toNextPage();
				List<WebElement> newRows = getTable().findElements(By.xpath(DATATABLE_TBODY_TR));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}
		Log.info(String.format("Customer not found: '%s'",customerName));
		return false;
	}

	public boolean editExistingCustomerName(String customerName, String eulaNew, boolean enableCustomer) {
		Log.method("editExistingCustomerName", customerName, eulaNew, enableCustomer);
		Log.info(String.format("Edit customer '%s'",customerName));
		setPaginationAny(PAGINATIONSETTING_100);

		String customerNameXPath;
		String actionXPath;

		WebElement customerNameCell;
		WebElement actionCell;

		List<WebElement> rows = getTable().findElements(By.xpath(DATATABLE_TBODY_TR));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = DATATABLE_TBODY_TR + "[" + rowNum + "]/td[1]";
			customerNameCell = getTable().findElement(By.xpath(customerNameXPath));

			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName)) {
				Log.info(String.format("Found existing customer with name - '%s' at row number - %d", customerName, rowNum));

				actionXPath = DATATABLE_TBODY_TR + "[" + rowNum + "]/td[3]";
				actionCell = getTable().findElement(By.xpath(actionXPath));

				actionCell.click();
				this.waitForEditPageLoad();

				Log.info("Set EULA - '"+eulaNew+"'");
				setEULAText(eulaNew);
				enabledDisableCustomer(enableCustomer);
				Log.clickElementInfo("Ok");
				this.btnOk.click();

				if (getTable().isDisplayed())
					return true;

				if (isElementPresent(this.summaryErrorsBy)) {
					WebElement panelError = driver.findElement(summaryErrorsBy);
					if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))) {
						Log.clickElementInfo("Cancel");
						this.cancelEditBtn.click();
						return false;
					}
				}
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				Log.clickElementInfo("Next");
				toNextPage();
				List<WebElement> newRows = getTable().findElements(By.xpath(DATATABLE_TBODY_TR));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}
		Log.info(String.format("Customer not found: '%s'",customerName));
		return false;
	}

	public boolean editAndSelectLicensedFeatures(String customerName, LicensedFeatures... lfs) {
		Log.method("editAndSelectLicensedFeatures", customerName, Arrays.toString(lfs));
		findCustomerAndOpenEditPage(customerName);
		selectLicensedFeatures(lfs);
		clickOnEditOkBtn();
		return true;
	}

	public boolean editAndUnSelectLicensedFeatures(String customerName, LicensedFeatures... lfs) {
		Log.method("editAndUnSelectLicensedFeatures", customerName, Arrays.toString(lfs));
		findCustomerAndOpenEditPage(customerName);
		unselectLicensedFeatures(lfs);
		clickOnEditOkBtn();
		return true;
	}

	public String getCustomerStatus(String customerName) {
		Log.method("getCustomerStatus", customerName);
		setPaginationAny(PAGINATIONSETTING_100);

		String customerNameXPath;
		String statusXPath;

		WebElement customerNameCell;
		WebElement statusCell;

		List<WebElement> rows = getTable().findElements(By.xpath(DATATABLE_TBODY_TR));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = DATATABLE_TBODY_TR + "[" + rowNum + "]/td[1]";
			customerNameCell = getTable().findElement(By.xpath(customerNameXPath));

			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName)) {
				statusXPath = DATATABLE_TBODY_TR + "[" + rowNum + "]/td[2]";

				statusCell = getTable().findElement(By.xpath(statusXPath));

				return statusCell.getText().trim();
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				toNextPage();
				List<WebElement> newRows = getTable().findElements(By.xpath(DATATABLE_TBODY_TR));

				rowSize = newRows.size();

				if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
					loopCount = rowSize;
				else
					loopCount = Integer.parseInt(PAGINATIONSETTING_100);

				rowNum = 0;
			}
		}

		return null;
	}

	public WebElement getInputReportMetadata() {
		Log.method("getInputReportMetadata");
		return this.inputReportMetadata;
	}

	public WebElement getInputReportShapeFile() {
		Log.method("getInputReportShapeFile");
		return this.inputReportShapeFile;
	}

	public LicensedFeatures getLicensedFeature(String licFeatureName) {
		Log.method("getLicensedFeature", licFeatureName);
		LicensedFeatures licensedFeature = null;
		try{
			licensedFeature = EnumUtility.fromName(licFeatureName, () -> LicensedFeatures.values());
		}catch(Exception e){
			Log.error(e.toString());
		}

		return licensedFeature;
	}

	public boolean verifyCustomerLicensedFeatures(LicensedFeatures[] lfs){
		for(LicensedFeatures lf:lfs){
			WebElement checkBox = getInputBoxOfLicensedFeature(lf);
			if(!checkBox.isSelected()){
				Log.error(String.format("Licensed Features '%s' is not selected for this customer", lf));
				return false;
			}
		}
		return true;
	}
	public String getEulaText() {
		Log.method("getEulaText");
		return this.textAreaEula.getAttribute("value");
	}

	public boolean changeCustomerAccountStatus(String customerName, boolean bEnabled) {
		Log.method("changeCustomerAccountStatus", customerName, bEnabled);
		setPaginationAny(PAGINATIONSETTING_100);

		String customerNameXPath;
		String actionXPath;

		WebElement customerNameCell;
		WebElement actionCell;

		List<WebElement> rows = getTable().findElements(By.xpath(DATATABLE_TBODY_TR));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = DATATABLE_TBODY_TR + "[" + rowNum + "]/td";
			customerNameCell = getTable().findElement(By.xpath(customerNameXPath));

			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName)) {
				Log.info(String.format("Found existing customer with name - '%s' at row number - %d", customerName, rowNum));

				actionXPath = DATATABLE_TBODY_TR + "[" + rowNum + "]/td/a[text()='Edit']";
				actionCell = getTable().findElement(By.xpath(actionXPath));

				actionCell.click();
				this.waitForEditPageLoad();

				enabledDisableCustomer(bEnabled);

				Log.clickElementInfo("Ok");
				this.btnOk.click();

				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100) && !this.nextBtn.getAttribute("class").contains("disabled")) {
				Log.clickElementInfo("Next");
				toNextPage();
				List<WebElement> newRows = getTable().findElements(By.xpath(DATATABLE_TBODY_TR));

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

	public boolean areTableColumnsSorted(){
		Log.method("areTableColumnsSorted");
		if(!isCustomerColumnSorted()){
			return false;
		}
		if(!isCustomerColumnSorted()){
			return false;
		}
		if(!isStatusColumnSorted()){
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

	public boolean isStatusColumnSorted(){
		Log.method("isStatusColumnSorted");
		HashMap<String, TableColumnType> columnMap = new HashMap<String, TableColumnType>();
		columnMap.put(Constant_Status, TableColumnType.String);
		return checkTableSort("datatable_wrapper", columnMap, pagination, getPaginationOption());
	}

	public boolean isAddCustomerBtnPresent() {
		Log.method("isAddCustomerBtnPresent");
		return isElementPresent(this.btnAddNewCustomerXPath);
	}

	public boolean isEditBtnPresent() {
		Log.method("isEditBtnPresent");
		return isElementPresent(this.btnEditCustomerXPath);
	}

	public void clickOnAddNewCustomerBtn() {
		Log.clickElementInfo("Add New Customer");
		this.btnAddNewCustomer.click();
	}

	public void clickOnFirstEditCustomerBtn() {
		Log.clickElementInfo("Edit Customer");
		this.btnEditCustomer.click();
	}

	public void clickOnAddCancelBtn() {
		Log.clickElementInfo("Cancel(add customer)");
		this.cancelAddBtn.click();
	}

	public void clickOnEditCancelBtn() {
		Log.clickElementInfo("Cancel(edit customer)");
		this.cancelEditBtn.click();
	}

	public void clickOnEditOkBtn() {
		Log.clickElementInfo("Ok");
		this.btnOk.click();
	}

	@Override
	public void open() {
		super.open();
		waitForPageLoad();
	}

	@Override
	public void waitForPageLoad() {
		RetryUtil.retryOnException(
				() -> {new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						return d.getPageSource().contains(STRPageContentText);
					}
				}); return true;
				}, () -> { super.open(); return true;}, Constants.DEFAULT_WAIT_BETWEEN_RETRIES_IN_MSEC,
				Constants.DEFAULT_MAX_RETRIES, true /*takeScreenshotOnFailure*/);
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