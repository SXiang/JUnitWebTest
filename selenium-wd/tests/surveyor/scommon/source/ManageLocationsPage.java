/**
 * 
 */
package surveyor.scommon.source;

import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.BLANKFIELDERROR;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_100;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSULOC;
import static surveyor.scommon.source.SurveyorConstants.ETHRNELAT;
import static surveyor.scommon.source.SurveyorConstants.ETHRNELON;
import static surveyor.scommon.source.SurveyorConstants.REQUIRED_FIELD_VAL_MESSAGE;
import static surveyor.scommon.source.SurveyorConstants.SECONDS_10;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.source.LatLongSelectionControl.ControlMode;
import common.source.BaseHelper;
import common.source.Log;
import common.source.RegexUtility;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class ManageLocationsPage extends SurveyorBasePage {
	public static final String STRURLPath = "/Picarro/ManageLocations";
	public static final String STRPageTitle = String.format("%s - %s", 
			Resources.getResource(ResourceKeys.ManageLocations_PageTitle), Resources.getResource(ResourceKeys.Constant_Surveyor));
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.ManageLocations_PageTitle);
	public static final String STRNewPageContentText = Resources.getResource(ResourceKeys.ManageLocation_NewLocation);
	public static final String STREditPageContentText = Resources.getResource(ResourceKeys.ManageLocation_EditLocation);
	public static final String STRDuplicateLocMsg = "Location name already exists for customer, please try another name.";

	@FindBy(css = "a[href='/Picarro/ManageLocation']")
	protected WebElement btnAddNewLocation;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Administrator')]")
	protected WebElement dropDownAdministrator;

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Log out')]")
	protected WebElement linkLogOut;

	@FindBy(id = "Description")
	protected WebElement inputLocationDesc;

	@FindBy(id = "btn-select-point")
	private WebElement latLongSelectorBtn;

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

	@FindBy(id = "EQMinimumAmplitude")
	protected WebElement eqMinAmp;

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

	@FindBy(css = "a[class='button-cancel btn btn-danger']")
	protected WebElement btnCancel;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[5]/a")
	protected WebElement btnEditLocation;

	@FindBy(id = "Description-error")
	private WebElement labelLocDescError;
	private String labelLocDescErrorXPath = "//label[@id='Description-error']";
	private String labelLocDescErrorID = "Description-error";

	@FindBy(id = "point-latitude-error")
	private WebElement labelLatValueError;
	private String labelLatValueErrorXPath = "//label[@id='point-latitude-error']";

	@FindBy(id = "point-longitude-error")
	private WebElement labelLongValueError;
	private String labelLongValueErrorXPath = "//label[@id='point-longitude-error']";

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[1]")
	protected WebElement tdCustomerValue;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr[1]/td[2]")
	protected WebElement tdLocationValue;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/thead/tr/th[2]")
	protected WebElement theadLocation;

	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr")
	protected List<WebElement> rows;

	@FindBy(how = How.XPATH, using = "//*[@id='location-form']/fieldset/div[11]/legend")
	protected WebElement ethMthRtioLbl;

	@FindBy(how = How.ID, using = "Min")
	protected WebElement ethMthMinUnit;

	@FindBy(how = How.ID, using = "Max")
	protected WebElement ethMthMaxUnit;

	@FindBy(id = "info")
	protected WebElement selectedPoint;
	
	private static LatLongSelectionControl latLongSelectionControl = null;

	private String latitude;
	private String longitude;

	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public ManageLocationsPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);

		latLongSelectionControl = new LatLongSelectionControl(driver);
		PageFactory.initElements(driver, latLongSelectionControl);

		Log.info("\nThe Manage Locations Page URL is: " + this.strPageURL);
	}

	public ManageLocationsPage(WebDriver driver, String baseURL, TestSetup testSetup, String urlPath) {
		super(driver, testSetup, baseURL, baseURL + urlPath);
	}

	public void addNewLocation(String locationDesc, String customer, String newLocationName) {
		addNewLocation(locationDesc, customer, newLocationName, false /* UseLatLongSelector */, "1", "2");
	}

	public void addNewLocationUsingLatLongSelector(String locationDesc, String customer, String newLocationName) {
		addNewLocation(locationDesc, customer, newLocationName, true /* UseLatLongSelector */, "1", "2");
	}

	public void addNewLocation(String locationDesc, String customer, String newLocationName, String ethMthMin,
			String ethMthMax) {
		addNewLocation(locationDesc, customer, newLocationName, false /* UseLatLongSelector */, ethMthMin, ethMthMax);
	}

	public void addNewLocationUsingLatLongSelector(String locationDesc, String customer, String newLocationName,
			String ethMthMin, String ethMthMax) {
		addNewLocation(locationDesc, customer, newLocationName, true /* UseLatLongSelector */, ethMthMin, ethMthMax);
	}

	private void addNewLocation(String locationDesc, String customer, String newLocationName,
			boolean useLatLongSelector, String ethMthMin, String ethMthMax) {

	    addNewLocation(locationDesc, customer, newLocationName, useLatLongSelector, ethMthMin,ethMthMax,true);
	}

	public void addNewLocation(String locationDesc, String customer,
			String newLocationName, boolean useLatLongSelector, String ethMthMin, String ethMthMax, boolean checkForError) {
		
		if (newLocationName.equalsIgnoreCase("Santa Clara")) {
			setLatitude("37.3971035425739");
			setLongitude("-121.98343231897");
		}

		this.btnAddNewLocation.click();

		this.inputLocationDesc.sendKeys(locationDesc);

		if (!useLatLongSelector) {
			inputLatLong(getLatitude(), getLongitude());
		} else {
			final int X_OFFSET = 100;
			final int Y_OFFSET = 100;
			
			this.clickOnLatLongSelectorBtn();
            this.selectOnLatLong(X_OFFSET, Y_OFFSET);            
			this.clickOnLatLongOkBtn();
			
			String locationLatitudeText = this.getLocationLatitudeText();
			Log.info("Location Latitude Field value = " + locationLatitudeText);
			assertTrue(!locationLatitudeText.isEmpty());

			String locationLongitudeText = this.getLocationLongitudeText();
			Log.info("Location Longitude Field value = " + locationLongitudeText);
			assertTrue(!locationLongitudeText.isEmpty());
		}

		List<WebElement> options = this.dropDownCustomer.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if (customer.equalsIgnoreCase(option.getText().trim())){
				Log.info("Select customer - '"+customer+"'");
				option.click();
				break;
			}
		}

		this.stdMinAmp.clear();
		this.stdMinAmp.sendKeys("0.035");
		this.opdMinAmp.clear();
		this.opdMinAmp.sendKeys("5");
		this.RRMinAmp.clear();
		this.RRMinAmp.sendKeys("5");
		if (this.assessmentMinAmp.isDisplayed()) {
			this.assessmentMinAmp.clear();
			this.assessmentMinAmp.sendKeys("0.035");
		}
		if (this.eqMinAmp.isDisplayed()) {
			this.eqMinAmp.clear();
			this.eqMinAmp.sendKeys("0.035");
		}

		this.NoLower.clear();
		this.NoLower.sendKeys("-45");
		this.YesLower.clear();
		this.YesLower.sendKeys("-42");
		this.YesUpper.clear();
		this.YesUpper.sendKeys("-30");
		this.NoUpper.clear();
		this.NoUpper.sendKeys("-25");

		if (ethMthMin != null && ethMthMin != "") {
			List<WebElement> optionsMIN = this.ethMthMinUnit.findElements(By.tagName("option"));
			for (WebElement option : optionsMIN) {
				if ((ethMthMin).equalsIgnoreCase(option.getText().trim())) {
					Log.info("Select option - '"+ethMthMin+"'");
					option.click();
					break;
				}
			}
		}

		if (ethMthMax != null && ethMthMax != "") {
			List<WebElement> optionsMAX = this.ethMthMaxUnit.findElements(By.tagName("option"));
			for (WebElement option : optionsMAX) {
				if ((ethMthMax).equalsIgnoreCase(option.getText().trim())) {
					Log.info("Select option - '"+ethMthMax+"'");
					option.click();
					break;
				}
			}
		}

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", this.btnOK);

		this.waitForPageToLoad();
        
		if(checkForError && verifyErrorMessage(null, true /*checkOnlyErrorSummary*/)){
			Log.clickElementInfo("Cancel");
			this.btnCancel.click();
		}
	}
	
	public boolean verifyErrorMessage(String errorMsg){
		return verifyErrorMessage(errorMsg, false /*checkOnlyErrorSummary*/);
	}
	
	private boolean verifyErrorMessage(String errorMsg, boolean checkOnlyErrorSummary){
		boolean found = false;
		if (isElementPresent(this.summaryErrorsBy)) {			
			if (this.summaryErrors.getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))){
				if (checkOnlyErrorSummary) { 
					found = true;
				} else {
					for(WebElement element:this.panelErrors){
						if(element.getText().equals(errorMsg)){
							found = true;
							Log.info("Error message found - '"+errorMsg+"'");
							break;
						}
					}
				}
			}
		}
		return found;
	}
	
	public void inputLatLong(String latitude, String longitude){
		this.inputLocationLat.clear();
		this.inputLocationLong.clear();
		
		Log.info("Set latitude - '"+latitude+"'");
		this.inputLocationLat.sendKeys(latitude);
		Log.info("Set longitude - '"+longitude+"'");
		this.inputLocationLong.sendKeys(longitude);
	}
	

	public void selectOnLatLong(int xOffset, int yOffset){
		String CANVAS_X_PATH = "//*[@id=\"map\"]/div/canvas";
		latLongSelectionControl.waitForModalDialogOpen()
		.switchMode(ControlMode.MapInteraction)
		.waitForMapImageLoad()
		.selectLatLong(CANVAS_X_PATH, xOffset, yOffset);
		latLongSelectionControl.switchMode(ControlMode.Default);		
	}
	
	public void clickOnLatLongCancelBtn(){
		latLongSelectionControl.clickCancelButton()
		.waitForModalDialogToClose();
	}

	public void clickOnLatLongOkBtn(){
		latLongSelectionControl.clickOkButton()
		.waitForModalDialogToClose();
	}
	
	public boolean findExistingLocationAndClickEdit(String customerName, String locationName){
		return editExistingLocation(customerName, locationName, null,null,null,null,null, true, true);
	}
	public boolean editExistingLocation(String customerName, String locationName, String newLocationName, boolean checkForError){
		return editExistingLocation(customerName, locationName, newLocationName,null,null,null,null, false, checkForError);
	}
	public boolean findExistingLocation(String customerName, String locationName) {
		Log.info(String.format("Find Location '%s', customer = '%s'",
				locationName, customerName));
		setPagination(PAGINATIONSETTING_100);
		this.clearSearchFieldUsingSpace();   // clear any previous entries in search.

		this.waitForAJAXCallsToComplete();
		this.waitForTableDataToLoad();
		
		this.searchTable(locationName);
		if (this.searchHasNoMatchingRecords()) {
        	// revert back search field.
        	this.clearSearchField();
        	return false;
		}

		String customerNameXPath;
		String locationNameXPath;

		WebElement customerNameCell;
		WebElement locationNameCell;

		List<WebElement> rows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			locationNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[2]";

			customerNameCell = getTable().findElement(By.xpath(customerNameXPath));
			locationNameCell = getTable().findElement(By.xpath(locationNameXPath));

			Log.info(String.format("Looking for row - [Customer=%s], [Location=%s]", customerName, locationName));
			Log.info(String.format("Found row - [Customer=%s], [Location=%s]", customerNameCell.getText().trim(), 
					locationNameCell.getText().trim()));
			
			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName)
					&& (locationNameCell.getText().trim()).equalsIgnoreCase(locationName)) {
				Log.info("Found entry at row=" + rowNum);
            	// revert back search field.
            	this.clearSearchField();
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
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

    	// revert back search field.
    	this.clearSearchField();
    	Log.error(String.format("Location not found: '%s', customer = '%s'",
				locationName, customerName));
		return false;
	}

	public boolean editPDExistingLocation(String customerName, String locationName, String newLocationName) {
		return this.editExistingLocation(customerName, locationName, newLocationName, null, null, null, null);
	}

	public boolean editPDExistingLocation(String customerName, String locationName, String newLocationName,
			String latValue, String longValue) {
		return this.editExistingLocation(customerName, locationName, newLocationName, latValue, longValue, null, null);
	}

	public boolean editPDExistingLocation(String customerName, String locationName, String newLocationName,
			String latValue, String longValue, String newEthMthMin, String newEthMthMax) {
		return this.editExistingLocation(customerName, locationName, newLocationName, latValue, longValue, newEthMthMin,
				newEthMthMax);
	}
	public boolean editExistingLocation(String customerName,
			String locationName, String newLocationName, String latValue,
			String longValue, String newEthMthMin, String newEthMthMax){
		
		return editExistingLocation(customerName, locationName, newLocationName, latValue,
				longValue, newEthMthMin, newEthMthMax, false,true);
	}
	
	public boolean editExistingLocation(String customerName,
			String locationName, String newLocationName, String latValue,
			String longValue, String newEthMthMin, String newEthMthMax , boolean openEditorOnly, boolean checkForError){
		Log.info(String.format("Edit Location '%s', customer = '%s'",
				locationName, customerName));
		setPagination(PAGINATIONSETTING_100);
		this.clearSearchFieldUsingSpace();		// clear any previous entries in search.

		this.waitForTableDataToLoad();
		this.searchTable(locationName);
		if (this.searchHasNoMatchingRecords()) {
        	// revert back search field.
        	this.clearSearchField();
        	return false;
		}

		String customerNameXPath;
		String locationNameXPath;
		String actionEditXPath;

		WebElement customerNameCell;
		WebElement locationNameCell;
		WebElement actionEditCell;

		List<WebElement> rows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

		int rowSize = rows.size();
		int loopCount = 0;

		if (rowSize < Integer.parseInt(PAGINATIONSETTING_100))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(PAGINATIONSETTING_100);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			customerNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[1]";
			locationNameXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[2]";

			customerNameCell = getTable().findElement(By.xpath(customerNameXPath));
			locationNameCell = getTable().findElement(By.xpath(locationNameXPath));

			if ((customerNameCell.getText().trim()).equalsIgnoreCase(customerName)
					&& (locationNameCell.getText().trim()).equalsIgnoreCase(locationName)) {
				actionEditXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[5]/a";
				actionEditCell = getTable().findElement(By.xpath(actionEditXPath));
				Log.info("Found entry at row=" + rowNum);

				actionEditCell.click();
				this.waitForEditPageLoad();
				
                if(openEditorOnly){
                	return true;
                }
                
				if (this.inputLocationDesc != null) {
					Log.info("Set location desc - '"+newLocationName+"'");
					this.inputLocationDesc.clear();
					this.inputLocationDesc.sendKeys(newLocationName);
				}

				if (latValue != null && latValue != "") {
					if (this.inputLocationLat != null) {
						Log.info("Set location latitude - '"+latValue+"'");
						this.inputLocationLat.clear();
						this.inputLocationLat.sendKeys(latValue);
					}
				}

				if (longValue != null && longValue != "") {
					if (this.inputLocationLong != null) {
						Log.info("Set location latitude - '"+longValue+"'");
						this.inputLocationLong.clear();
						this.inputLocationLong.sendKeys(longValue);
					}
				}

				if (newEthMthMin != null && newEthMthMin != "") {
					List<WebElement> optionsMIN = this.ethMthMinUnit.findElements(By.tagName("option"));
					for (WebElement option : optionsMIN) {
						if ((newEthMthMin).equalsIgnoreCase(option.getText().trim())) {
							Log.info("Select option - '"+option+"'");
							option.click();
							break;
						}
					}
				}

				if (newEthMthMax != null && newEthMthMax != "") {
					List<WebElement> optionsMAX = this.ethMthMaxUnit.findElements(By.tagName("option"));
					for (WebElement option : optionsMAX) {
						if ((newEthMthMax).equalsIgnoreCase(option.getText().trim())) {
							Log.info("Select option - '"+newEthMthMax+"'");
							option.click();
							break;
						}
					}
				}

				String curURL = driver.getCurrentUrl();
				this.btnOK.click();
				Log.info("ok button clicked");
                if(!checkForError){
                	Log.info("Not checking for errors");
                	if (isElementPresent(this.panelDuplicationErrorXPath)) {
                		// We are still on the new locations page. Return.
                    	Log.info("Error on page. Returning to caller.");
                    	return true; 
                	}
                	
                	// Redirected to Manage location page. Revert back search field and return.
                	this.clearSearchField();
                	return true;
                }
				if (newLocationName.equalsIgnoreCase("")) {
					// Required field validation message should be shown.
					this.waitUntilPresenceOfElementLocated(labelLocDescErrorID);
					this.labelLocDescError = this.driver.findElement(By.id(labelLocDescErrorID));
					if (driver.getCurrentUrl().equalsIgnoreCase(curURL)
							&& this.labelLocDescError.getText().equalsIgnoreCase(REQUIRED_FIELD_VAL_MESSAGE))
						return false;
				}

				if (isElementPresent(this.panelDuplicationErrorXPath)) {
					WebElement panelError = driver.findElement(By.xpath(this.panelDuplicationErrorXPath));
					if (panelError.getText()
							.equalsIgnoreCase(Resources.getResource(ResourceKeys.Validation_SummaryTitle))) {
						return false;
					}
				}

            	// revert back search field.
            	this.clearSearchField();
				return true;
			}

			if (rowNum == Integer.parseInt(PAGINATIONSETTING_100)
					&& !this.nextBtn.getAttribute("class").contains("disabled")) {
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

	   	// revert back search field.
    	this.clearSearchField();
    	Log.error(String.format("Location not found: '%s', customer = '%s'",
				locationName, customerName));
		return false;

	}

	public String getSelectedPoint(){
		return getSelectedPoint(SECONDS_10);
	}
	public String getSelectedPoint(int timeout){
		
		latLongSelectionControl.waitForModalDialogOpen()
		.switchMode(ControlMode.MapInteraction);
		
		String pt = null;
		try{
		    pt = (new WebDriverWait(driver,timeout)).until(new ExpectedCondition<String>(){
			public String apply(WebDriver d){
				String text = null;
				try{
					text = selectedPoint.getText();
					if(text.indexOf("[")>=0){
						text = text.substring(text.indexOf("[")+1,text.indexOf("]"));
						}else{
							text = null;
						}
				}catch(Exception e){
					text = null;
				}
				return text;
			}
		});
		}catch(Exception e){
			pt = null;
			Log.warn(e.toString() + "Selected point is "+pt+"?");
		}
		
		latLongSelectionControl
		.switchMode(ControlMode.Default);
		return pt;
	}
	
	public WebElement getBtnAddNewLocation() {
		return this.btnAddNewLocation;
	}

	public WebElement getBtnCancel() {
		return this.btnCancel;
	}

	public String getLocationLatitudeText() {
		return this.inputLocationLat.getAttribute("value");
	}

	public String getLocationLongitudeText() {
		return this.inputLocationLong.getAttribute("value");
	}

	public String getLocationLatitudeError() {
		return this.labelLatValueError.getText();
	}

	public String getLocationLongitudeError() {
		return this.labelLongValueError.getText();
	}
	
	public String getLocationDescriptionError(){
		return this.labelLocDescError.getText();
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public void clickOnAddNewLocationBtn() {
		Log.clickElementInfo("Add New Location");
		this.btnAddNewLocation.click();
		this.waitForNewPageLoad();
	}

	public void clickOnFirstEditLocationBtn() {
		Log.clickElementInfo("Edit Location");
		this.btnEditLocation.click();
	}

	public void clickOnCancelBtn() {
		Log.clickElementInfo("Cancel");
		this.btnCancel.click();
	}

	public void clickOnOkBtn() {
		Log.clickElementInfo("Ok");
		this.btnOk.click();
	}
	
	public void clickOnLatLongSelectorBtn() {
		Log.clickElementInfo("Lat/Long Selector");
		this.latLongSelectorBtn.click();
	}

	public WebElement getTheadLocation() {
		return this.theadLocation;
	}

	public WebElement getEthMthRtoLbl() {
		return ethMthRtioLbl;
	}

	public List<String> getLocationList(boolean allPages, int paginationSize) {
		List<String> locationList = new ArrayList<String>();

		String pageSizeStr = String.valueOf(paginationSize);
		setPagination(pageSizeStr);
		waitForPageLoad();

		String locationXPath;
		WebElement locationCell;

		int rowSize = rows.size();
		int loopCount = 0;
		if (rowSize < Integer.parseInt(pageSizeStr))
			loopCount = rowSize;
		else
			loopCount = Integer.parseInt(pageSizeStr);

		for (int rowNum = 1; rowNum <= loopCount; rowNum++) {
			locationXPath = "//*[@id='datatable']/tbody/tr[" + rowNum + "]/td[2]";
			locationCell = getTable().findElement(By.xpath(locationXPath));

			locationList.add(locationCell.getText().trim());

			if (rowNum == Integer.parseInt(pageSizeStr) && !this.nextBtn.getAttribute("class").contains("disabled")
					&& allPages) {
				this.nextBtn.click();
				this.testSetup.slowdownInSeconds(this.testSetup.getSlowdownInSeconds());
				List<WebElement> newRows = getTable().findElements(By.xpath("//*[@id='datatable']/tbody/tr"));

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

	public boolean searchLocation(String customer, String locationName) {
		this.searchTable(locationName);
		return findExistingLocation(customer, locationName);
	}

	public boolean isDuplicateLocMsgPresent() {
		return this.liDuplicateMsg.getText().equals(STRDuplicateLocMsg);
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