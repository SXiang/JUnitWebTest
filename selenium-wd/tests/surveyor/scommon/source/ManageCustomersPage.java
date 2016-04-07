/**
 * 
 */
package surveyor.scommon.source;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;

import common.source.BaseHelper;
import common.source.Log;
import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

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
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div/div/div[1]/div[1]/a")
	private WebElement btnAddNewCustomer;
	
	@FindBy(id = "name")
	private WebElement inputCustomerName;
	
	private static final String EULAXPath = "eula";
	
	@FindBy(how = How.XPATH, using = "//*[@id='eula']")
	private WebElement textAreaEula;
	
	@FindBy(how = How.XPATH, using = "//*[@id='customer-form']/fieldset/div[5]/div[2]/a")
	private WebElement cancelAddBtn;

	@FindBy(how = How.XPATH, using = "//*[@id='customer-form']/fieldset/div[4]/div[2]/a")
	private WebElement cancelEditBtn;
	
	@FindBy(how = How.XPATH, using = "//*[@id='active']")
	private WebElement inputAccountEnabled;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[3]/a")
	private WebElement btnEditCustomer;
	
	@FindBy(how = How.CSS, using = "input[id='LicencedFeatureId-GAP Grid 1.0']")
	private WebElement inputGAPGrid;
	
	@FindBy(how = How.CSS, using = "input[id='LicencedFeatureId-Report Metadata']")
	private WebElement inputReportMetadata;
	
	@FindBy(how = How.CSS, using = "input[id='LicencedFeatureId-Assessment']")
	private WebElement inputAssessment;
	
	@FindBy(how = How.CSS, using = "input[id='LicencedFeatureId-EQ']")
	private WebElement inputEQ;
	
	@FindBy(how = How.CSS, using = "input[id='LicencedFeatureId-LISA Box 1.0']")
	private WebElement inputLISABox;
	
	@FindBy(how = How.CSS, using = "input[id='LicencedFeatureId-Survey Protocol Forecase']")
	private WebElement inputSurveyForecase;
	
	@FindBy(how = How.CSS, using = "input[id='LicencedFeatureId-Report ShapeFile']")
	private WebElement inputReportShapeFile;
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	
	public ManageCustomersPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);
		
		Log.info("\nThe Manager Customers Page URL is: " + this.strPageURL);
	}
	

	public boolean selectLicensedFeatures(LicensedFeatures... lfs){
		if(lfs != null){
		  for(LicensedFeatures lf:lfs){
			  selectLicensedFeature(lf);
		  }		
		}
		return true;
	}
	
	public boolean selectLicensedFeature(LicensedFeatures lf) {
		return selectLicensedFeature(lf, true);
	}
	
	public boolean selectLicensedFeature(LicensedFeatures lf, boolean enableFeature){
		WebElement inputBox = getInputBoxOfLicensedFeature(lf);
		if (enableFeature) {
			if (!inputBox.isSelected())
				inputBox.click();
		}
		else {
			if (inputBox.isSelected())
				inputBox.click();
		}
		return enableFeature;
	}
	
	private WebElement getInputBoxOfLicensedFeature(LicensedFeatures lf){
		WebElement inputBox;
		switch(lf){
		case ASSESSMENT: 
			inputBox = inputAssessment;
		    break;
		case GAPGRID: 
			inputBox = inputGAPGrid;
            break;
		case REPORTMETADATA: 
			inputBox = inputReportMetadata;
            break;
		case EQ: 
			inputBox = inputEQ;
            break;
		case LISABOX: 
			inputBox = inputLISABox;
            break;
		case SURVEYFORECASE: 
			inputBox = inputSurveyForecase;
            break;
		case REPORTSHAPEFILE: 
			inputBox = inputReportShapeFile;
            break;
		default: 
			inputBox = inputGAPGrid;
            break;       
		}
		return inputBox;
	}
	
	public boolean addNewCustomer(String customerName, String eula) {
		return addNewCustomer(customerName, eula, true /*enableCustomer*/);
	}
	
	public boolean addNewCustomer(String customerName, String eula, LicensedFeatures... lfs) {
		return addNewCustomer(customerName, eula, true /*enableCustomer*/, lfs);
	}
	
	public boolean addNewCustomer(String customerName, String eula, boolean enableCustomer) {
		return addNewCustomer(customerName, eula, enableCustomer, null /* licensed features */);
	}
	
	public boolean addNewCustomer(String customerName, String eula, boolean enableCustomer, LicensedFeatures[] lfs ) {
		this.btnAddNewCustomer.click();
		this.waitForNewPageLoad();
		
		this.inputCustomerName = driver.findElement(By.id("name"));
		if (this.inputCustomerName == null) {
			Log.info("Did NOT find this.inputCustomerName element");
		}
		if (!this.inputCustomerName.isDisplayed()) {
			Log.info("this.inputCustomerName is NOT displayed");
		}
		
		this.inputCustomerName.sendKeys(customerName);
		
		setEULAText(eula);
		enabledDisableCustomer(enableCustomer);
		selectLicensedFeatures(lfs);
		this.btnOk.click();
		
		if (isElementPresent(this.panelDuplicationErrorXPath)){
			WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
			if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))) {
				this.cancelAddBtn.click();
				return false;
			}
		}
		
		this.waitForPageLoad();
		return true;
	}
	
	public void setEULAText(String eula) {
		sendKeysToTextArea(this.textAreaEula, eula);
	}
    
	private void enabledDisableCustomer(boolean enableCustomer) {
		if (enableCustomer) {
			if (!inputAccountEnabled.isSelected())
				inputAccountEnabled.click();
		}
		else {
			if (inputAccountEnabled.isSelected())
				inputAccountEnabled.click();
		}
	}

	public boolean isEulaRed(){
		String eulaStyle = this.textAreaEula.getAttribute("style");
		String eulaRed = "border: 1px solid red;";
		return eulaRed.equals(eulaStyle.trim());
	}

	public boolean isNameRed(){
		String nameStyle = this.inputCustomerName.getAttribute("style");
		String nameRed = "border: 1px solid red;";
		return nameRed.equals(nameStyle.trim());
	}
	
	public boolean findExistingCustomer(String customerName, boolean enabledStatus) {
		setPagination(PAGINATIONSETTING_100);
		
		this.waitForTableDataToLoad();
		
		String customerNameXPath;
		String enabledStatusXPath;
		WebElement customerNameCell;
		WebElement enabledStatusCell;	
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			customerNameCell = table.findElement(By.xpath(customerNameXPath));
			
			enabledStatusXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
			enabledStatusCell = table.findElement(By.xpath(enabledStatusXPath));
			
			Log.info(String.format("Customer: %s; Status: %s", customerNameCell.getText(), enabledStatusCell.getText()));			
			
			String enabledStatusString = Resources.getResource(ResourceKeys.Constant_Enabled);
			if (!enabledStatus) {
				enabledStatusString = Resources.getResource(ResourceKeys.Constant_Disabled);
			}
			
			if (customerNameCell.getText().trim().equalsIgnoreCase(customerName) 
					&& enabledStatusCell.getText().trim().equalsIgnoreCase(enabledStatusString)) {
				Log.info(String.format("Found existing customer with name - '%s' and enabled status - '%b' at row number - %d", 
						customerName, enabledStatus, rowNum));
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
	
	public boolean findCustomerAndOpenEditPage(String customerName) {
		setPagination(PAGINATIONSETTING_100);
		
		this.waitForTableDataToLoad();
		
		String customerNameXPath;
		String actionXPath;
		
		WebElement customerNameCell;
		WebElement actionCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			customerNameCell = table.findElement(By.xpath(customerNameXPath));
			
			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName)) {				
				Log.info(String.format("Found existing customer with name - '%s' at row number - %d", 
						customerName, rowNum));
				actionXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[3]";
				actionCell = table.findElement(By.xpath(actionXPath));
				
				actionCell.click();
				this.waitForEditPageLoad();
				
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


	public boolean editExistingCustomerName(String customerName, String eulaNew) {
		setPagination(PAGINATIONSETTING_100);
		
		this.waitForTableDataToLoad();
		
		String customerNameXPath;
		String actionXPath;
		
		WebElement customerNameCell;
		WebElement actionCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			customerNameCell = table.findElement(By.xpath(customerNameXPath));
			
			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName)) {				
				Log.info(String.format("Found existing customer with name - '%s' at row number - %d", 
						customerName, rowNum));
				
				actionXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[3]";
				actionCell = table.findElement(By.xpath(actionXPath));
				
				actionCell.click();
				this.waitForEditPageLoad();
				
				setEULAText(eulaNew);
				
				this.btnOk.click();
				
				if (table.isDisplayed())
					return true;
				
				if (isElementPresent(this.panelDuplicationErrorXPath)){
					WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
					if (panelError.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))) {
						this.cancelEditBtn.click();
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
	
	public String getCustomerStatus(String customerName) {
		setPagination(PAGINATIONSETTING_100);
		
		this.waitForTableDataToLoad();
		
		String customerNameXPath;
		String statusXPath;
		
		WebElement customerNameCell;
		WebElement statusCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			customerNameCell = table.findElement(By.xpath(customerNameXPath));
			
			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName)) {
				statusXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[2]";
				
				statusCell = table.findElement(By.xpath(statusXPath));
				
				return statusCell.getText().trim();
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
		
		return null;
	}
	
	public String getEulaText() {
		return this.textAreaEula.getAttribute("value");
	}
	
	public boolean changeCustomerAccountStatus (String customerName, boolean bEnabled) {
		setPagination(PAGINATIONSETTING_100);
		
		this.waitForTableDataToLoad();
		
		String customerNameXPath;
		String actionXPath;
		
		WebElement customerNameCell;
		WebElement actionCell;
		
		List<WebElement> rows = table.findElements(By.xpath("//*[@id='datatable']/tbody/tr"));
		
		int rowSize = rows.size();
		int loopCount = 0;
		
		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);
		
		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[1]";
			customerNameCell = table.findElement(By.xpath(customerNameXPath));
			
			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName)) {
				Log.info(String.format("Found existing customer with name - '%s' at row number - %d", 
						customerName, rowNum));
				
				actionXPath = "//*[@id='datatable']/tbody/tr["+rowNum+"]/td[3]";
				actionCell = table.findElement(By.xpath(actionXPath));
				
				actionCell.click();
				this.waitForEditPageLoad();
				
				enabledDisableCustomer(bEnabled);
				
				this.btnOk.click();
				
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
	
	public void clickOnAddNewCustomerBtn(){
		this.btnAddNewCustomer.click();
	}
	
	public void clickOnFirstEditCustomerBtn(){
		this.btnEditCustomer.click();
	}
	
	public void clickOnAddCancelBtn(){
		this.cancelAddBtn.click();
	}

	public void clickOnEditCancelBtn(){
		this.cancelEditBtn.click();
	}
	
	public void clickOnEditOkBtn(){
		this.btnOk.click();
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