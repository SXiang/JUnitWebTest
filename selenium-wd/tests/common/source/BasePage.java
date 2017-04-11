/**
 *
 */
package common.source;

import static surveyor.scommon.source.SurveyorConstants.DRIVINGSURVEYHEADER;
import static surveyor.scommon.source.SurveyorConstants.DRIVINGSURVEYTITLE;
import static surveyor.scommon.source.SurveyorConstants.HOMEDHEADER;
import static surveyor.scommon.source.SurveyorConstants.HOMETITLE;
import static surveyor.scommon.source.SurveyorConstants.LOGINTITLE;
import static surveyor.scommon.source.SurveyorConstants.SUBTITLE;
import static surveyor.scommon.source.SurveyorConstants.UNKNOWN_TEXT;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BooleanSupplier;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.avh4.util.imagecomparison.ImageComparisonResult;
import surveyor.scommon.source.DataTablePage;

/**
 * @author zlu
 *
 *         Add more general code later for pages
 *
 */
public class BasePage {
	protected String strBaseURL;
	protected String strPageURL;
	protected WebDriver driver;
	protected TestSetup testSetup;

	protected int timeout = 60;   // For parallel execution increasing timeout to 60 seconds.
	
	@FindBy(how = How.CSS, using = ".navbar-header > .navbar-brand > .logo")
	public WebElement siteLogo;

	@FindBy(how = How.CSS, using = ".pcubed > img")
	public WebElement pcubedLogo;

	@FindBy(how = How.XPATH, using = "//h1/strong")
	private WebElement pageHeader;

	@FindBy(how = How.XPATH, using = "//li[@id='home']/a")
	private WebElement linkDashboard;

	@FindBy(how = How.XPATH, using = "//li[@id='driving-surveys']/a")
	private WebElement linkDrivingSurveys;

	@FindBy(how = How.XPATH, using = "//li[@id='picarro-surveyors']/a")
	private WebElement linkSurveyors;

	@FindBy(how = How.XPATH, using = "//li[@id='fleet-map']/a")
	private WebElement linkFleetMap;

	@FindBy(how = How.XPATH, using = "//li/a[@data-target='#report-menu']")
	private WebElement linkReports;

	@FindBy(id = "report-compliance")
	private WebElement linkComplianceReport;

	@FindBy(id = "report-investigation")
	private WebElement linkEQReport;

	@FindBy(id = "report-reference-gas")
	private WebElement linkReferenceGasReport;

	@FindBy(id = "report-system-history")
	private WebElement linkSystemHistoryReport;

	@FindBy(how = How.XPATH, using = "//li/a[@data-target='#picarro-administration-menu']")
	private WebElement linkPicarroAdmin;

	@FindBy(how = How.XPATH, using = "//li[@id='picarro-administration-calibration']/a")
	private WebElement linkCalibration;

	@FindBy(how = How.XPATH, using = "//li[@id='picarro-administration-manage-customers']/a")
	private WebElement linkManageCustomers;

	@FindBy(how = How.XPATH, using = "//li[@id='picarro-administration-manage-users']/a")
	private WebElement linkManageUsers;

	@FindBy(how = How.XPATH, using = "//li[@id='picarro-administration-manage-locations']/a")
	private WebElement linkManageLocations;

	@FindBy(how = How.XPATH, using = "//li[@id='picarro-administration-manage-surveyors']/a")
	private WebElement linkManageSurveyors;

	@FindBy(how = How.XPATH, using = "//li[@id='picarro-administration-manage-analyzers']/a")
	private WebElement linkManageAnalyzers;

	@FindBy(how = How.XPATH, using = "//li[@id='picarro-administration-manage-ref-gas-bottles']/a")
	private WebElement linkManageRefGasBottles;

	@FindBy(how = How.XPATH, using = "//li[@id='user-release-notes']/a")
	private WebElement linkManageReleaseNotes;

	@FindBy(how = How.XPATH, using = "//li[@id='picarro-administration-manage-surveyor-history']/a")
	private WebElement linkManageSurveyorHistories;

	@FindBy(how = How.XPATH, using = "//li[@id='picarro-administration-analyzer-logs']/a")
	private WebElement linkViewAnalyzerLogs;

	@FindBy(how = How.XPATH, using = "//li[@id='picarro-administration-server-log']/a")
	private WebElement linkViewServerLogs;

	@FindBy(how = How.CSS, using = "#datatable td.dataTables_empty")
	private WebElement emptyDataTableMessage;

	@FindBy(how = How.CSS, using = "body.login-background div.panel-body > p")
	private List<WebElement> siteErrorMessage;

	@FindBy(how = How.CSS, using = "[id='licenseMissingModal'][style='display: block;'] > .modal-dialog .modal-body > p")
	private List<WebElement> licenseMissingText;

	@FindBy(how = How.CSS, using = "[id='licenseMissingModal'][style='display: block;'] > .modal-dialog .modal-footer > a.btn")
	private WebElement licenseMissingModalOKBtn;

	public static enum ElementType{BUTTON,LABEL,CHECKBOX,RADIOBUTTON,INPUT
		,DIVISION, LINK, OPTION, ICON, DROPDOWN};

	public BasePage(WebDriver driver, TestSetup testSetup, String strBaseURL, String strPageURL) {
		this.driver = driver;
		this.testSetup = testSetup;
		this.strBaseURL = strBaseURL;
		this.strPageURL = strPageURL;
	}

	public void open() {
		open(strPageURL);
		this.waitForPageToLoad();
	}

	public void open(String path) {
		String url = path;
		if(url.startsWith("/")){
			url = strBaseURL + url;
		}
		Log.info("Get URL: '"+url+"'");
		driver.get(url);
	}

	public String getStrPageURL() {
		return this.strPageURL;
	}

	public boolean isElementPresent(By by) {
		return WebElementExtender.findElementBy(this.driver, by);
	}

	public boolean isElementPresent(By by, int timeout) {
		return WebElementExtender.findElementBy(this.driver, by, timeout);
	}

	public boolean isElementPresent(String strXPath) {
		return isElementPresent(By.xpath(strXPath));
	}

	public boolean isElementPresent(String strXPath, int timeout) {
		return isElementPresent(By.xpath(strXPath), timeout);
	}

	public void clickOnDashboardLink() {
		Log.clickElementInfo("Dashboard", ElementType.LINK);
		this.linkDashboard.click();
	}

	public void clickOnDrivingSurveyLink() {
		Log.clickElementInfo("Driving Surveys", ElementType.LINK);
		this.linkDrivingSurveys.click();
	}

	public void clickOnSurveyorsLink() {
		Log.clickElementInfo("Surveyors", ElementType.LINK);
		this.linkSurveyors.click();
	}

	public void clickOnFleetMapLink() {
		Log.clickElementInfo("Fleet Map", ElementType.LINK);
		this.linkFleetMap.click();
	}

	public void clickOnReportsLink() {
		Log.clickElementInfo("Reports", ElementType.LINK);
		this.linkReports.click();
	}

	public void clickOnComplianceReportLink() {
		Log.clickElementInfo("Compliance Report", ElementType.LINK);
		this.linkComplianceReport.click();
	}

	public void clickOnEQReportLink() {
		Log.clickElementInfo("EQ Report", ElementType.LINK);
		this.linkEQReport.click();
	}

	public void clickOnReferenceGasReportLink() {
		Log.clickElementInfo("Reference Gas Report", ElementType.LINK);
		this.linkReferenceGasReport.click();
	}

	public void clickOnSystemHistoryReportLink() {
		Log.clickElementInfo("System History Report", ElementType.LINK);
		this.linkSystemHistoryReport.click();
	}

	public void clickOnPicarroAdminLink() {
		Log.clickElementInfo("Picarro Admin", ElementType.LINK);
		this.linkPicarroAdmin.click();
	}

	public void clickOnCalibrationLink() {
		Log.clickElementInfo("Calibration ", ElementType.LINK);
		this.linkCalibration.click();
	}

	public void clickOnManageCustomersLink() {
		Log.clickElementInfo("Manage Customers", ElementType.LINK);
		this.linkManageCustomers.click();
	}

	public void clickOnManageUsersLink() {
		Log.clickElementInfo("Manage Users", ElementType.LINK);
		this.linkManageUsers.click();
	}

	public void clickOnManageLocationsLink() {
		Log.clickElementInfo("Manage Locations", ElementType.LINK);
		this.linkManageLocations.click();
	}

	public void clickOnManageSurveyorsLink() {
		Log.clickElementInfo("Manage Surveyors", ElementType.LINK);
		this.linkManageSurveyors.click();
	}

	public void clickOnManageAnalyzersLink() {
		Log.clickElementInfo("Manage Analyzers", ElementType.LINK);
		this.linkManageAnalyzers.click();
	}

	public void clickOnManageRefGasBottlesLink() {
		Log.clickElementInfo("Manage Ref Gas Bottles", ElementType.LINK);
		this.linkManageRefGasBottles.click();
	}

	public void clickOnManageReleaseNotesLink() {
		Log.clickElementInfo("Manage Release Notes", ElementType.LINK);
		this.linkManageReleaseNotes.click();
	}

	public void clickOnManageSurveyorHistoriesLink() {
		Log.clickElementInfo("Manage Surveyor Histories", ElementType.LINK);
		this.linkManageSurveyorHistories.click();
	}

	public void clickOnViewAnalyzerLogsLink(String strBaseURL) {
		Log.info("Navigate to Analyzer Logs page");
		driver.get(strBaseURL + "/Picarro/AnalyzerLogs");
	}

	public void clickOnViewServerlogsLink(String strBaseURL) {
		Log.info("Navigate to Server Logs page");
		driver.get(strBaseURL + "/Picarro/ServerLog");
	}

	public boolean verifyFleetMapLinkIsClickable() {
		Log.method("Verify if Fleet Map link is clickable");
		if(WebElementExtender.isElementPresentAndDisplayed(linkFleetMap)){
			return linkFleetMap.isEnabled();
		}
		return false;
	}

	public boolean isLinkBroken() {
		boolean result = false;
		waitForPageToLoad();
		String pageTitle = this.driver.getTitle();

		if (pageTitle.contains(LOGINTITLE)) {
			Log.info("PageTitle: " + pageTitle);
			result = true;
			return result;
		} else if (pageTitle.contains(HOMETITLE)) {
			Log.info("PageTitle: " + pageTitle);
			result = this.pageHeader.getText().equalsIgnoreCase(HOMEDHEADER);
			return result;
		} else if (pageTitle.contains(DRIVINGSURVEYTITLE)) {
			Log.info("PageTitle: " + pageTitle);
			result = this.pageHeader.getText().equalsIgnoreCase(DRIVINGSURVEYHEADER);
			return result;
		} else {
			String pageHeader = this.pageHeader.getText();
			Log.info("PageTitle: " + pageTitle);
			result = pageTitle.contentEquals(pageHeader + SUBTITLE);
			return result;
		}
	}

	protected boolean isAttributePresentOnElement(WebElement element, String attribute) {
		try {
			String value = element.getAttribute(attribute);
			if (value != null){
				return true;
			}
		} catch (Exception e) {}

		return false;
	}

	protected void sendKeysToElement(WebElement element, String key) {
		// Chromedriver does NOT send keys correctly to TextArea for some controls.
		// Use set text in the following order of precedence:
		//  1. JS set value
		//  2. Actions sendKeys
		//  3. WebElement sendKeys
		if(key == null){
			return;
		}

		try {
			Log.info("[sendKeysToElement] -> Wait for element to be clickable");
			waitForElementToBeClickable(element);
			Log.info("[sendKeysToElement] -> Clear element");
			element.clear();
		} catch (Exception ex) {
			Log.warn("Caught exception while waiting for element to be clickable/clearing element. Exception message -> " + ex.getMessage());
		}

		try {
			Log.info(String.format("Send keys to element using JS set value - '%s'", key));
			jsSendKeys(element, key);
		} catch (Exception e) {
			try {
				Log.info("Send '"+key+"' to text element(field/area) using Actions");
				Actions actions = new Actions(driver);
				actions.moveToElement(element);
				actions.click();
				actions.sendKeys(key);
				actions.build().perform();
			} catch (InvalidElementStateException ex) {
				Log.warn("Caught exception when sending keys to element using Actions. Exception message -> " + ex.getMessage());
				Log.info("Send '"+key+"' to text element(field/area) using element.sendKeys()");
				element.sendKeys(key);
			}
		}
	}

	protected WebElement waitForElementToBeClickable(WebElement element){
		return WebElementExtender.waitForElementToBeClickable(timeout, driver, element);
	}

	protected void waitAndClickElement(WebElement element) {
		Log.method("waitAndClickElement", element);
		waitForElementToBeClickable(element);
		element.click();
	}

	protected WebElement waitUntilPresenceOfElementLocated(String elementID) {
		return waitUntilPresenceOfElementLocated(By.id(elementID));
	}

	protected WebElement waitUntilPresenceOfElementLocated(By locator){
		return (new WebDriverWait(driver, timeout)).until(
				ExpectedConditions.presenceOfElementLocated(locator));
	}

	protected String waitForPresenceOfElementText(By locator){
		return waitForPresenceOfElementText(locator, UNKNOWN_TEXT);
	}

	protected String waitForPresenceOfElementText(By locator, String expectedText){
		String actualText = null;
		try {
			actualText = (new WebDriverWait(driver,timeout)).until(
					new ExpectedCondition<String>(){
						public String apply(WebDriver d){
							String value = d.findElement(locator).getText().trim();
							if(expectedText.equals(UNKNOWN_TEXT)&&!value.equals("")){
								return value;
							}else if(expectedText.equals(value)){
								return value;
							}else{
								Log.warn("Expecting '"+expectedText +"', found '"+value+"'");
								return null;
							}
						}
					});
		}catch(Exception e){
			actualText = null;
		}
		return actualText;
	}

	public void waitForPageToLoad(){
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
	}

	/*
	 * Default implementation of this method makes a call to waitForPageToLoad(). Inherited Page classes can provide a page specific implementation of this method.
	 */
	public void waitForPageLoad() {
		waitForPageToLoad();
	}

	/**
	 * Javascript Click
	 * @param element - element to be clicked
	 */
	public void jsClick(WebElement element){
		WebElementExtender.executeScript(element, driver, "arguments[0].click();");
	}

	/**
	 * Javascript Click
	 * @param element - element to send keys to.
	 * @param value - value to set in the element.
	 */
	public void jsSendKeys(WebElement element, String value) {
		WebElementExtender.executeScript(element, driver, String.format("arguments[0].value = '%s';", value));
	}

	/**
	 * Javascript Scroll
	 * @param element - element to be clicked
	 */
	public void jsScrollToView(WebElement element){
		WebElementExtender.executeScript(element, driver, "arguments[0].scrollIntoView();");
	}

	public void focusOnPage(By locator){
		WebElement element = waitUntilPresenceOfElementLocated(locator);
		focusOnPage(element);
	}
	public void focusOnPage(WebElement element){
		Actions action = new Actions(driver);
		try{
			action.moveToElement(element).click().click().perform();
			action.moveToElement(element).click().click().perform();
		}catch(Exception e){
			Log.warn("Failed to focusOnPage by clicking on element: "+e);
		}
	}

	public void minimizeBrowserWindow(){
		Log.info("Minimize browser window");
		driver.manage().window().setSize(new Dimension(0,0));
	}

    public void maxmizeBrowserWindow(){
    	Log.info("Maximize browser window");
    	driver.manage().window().maximize();
	}

    public void SelectElement(WebElement checkbox) {
    	Log.method("SelectElement", checkbox);
    	if (!checkbox.isSelected()){
    		Log.info("Element is NOT selected. Selecting element with JSClick");
    		jsClick(checkbox);
    	}
    }

    public void UnselectCheckbox(WebElement checkbox) {
    	Log.method("UnselectCheckbox", checkbox);
    	if (checkbox.isSelected()){
    		Log.info("Element is selected. Un-selecting element with JSClick");
    		jsClick(checkbox);
    	}
    }

	protected boolean selectDropdownOption(WebElement dropdown, String option){
		boolean selected = false;
		int numTry = 0;
		By optBy = By.xpath("option[text()='"+option.trim()+"']");
		do{
			try{
				WebElement opt =  dropdown.findElement(optBy);
				opt.click();
				selected = opt.isSelected();
			}catch(Exception e){
				numTry++;
				Log.error("Failed to select option '"+option+"'");
				waitForPageToLoad();
			}
		}while(!selected&&numTry<5);

		return selected;
	}

	public boolean selectDropdownItem(WebElement buttonDropdown, String item){
		if(isItemSelected(buttonDropdown, item)){
			return true;
		}
		buttonDropdown.click();
		WebElement listItem = buttonDropdown.findElement(By.xpath("../ul/li/a[starts-with(text(), '"+item+"')]"));
		listItem.click();
		waitForPageToLoad();
		return isItemSelected(buttonDropdown, item);
	}

	private boolean isItemSelected(WebElement buttonDropdown, String item){
		try{
			buttonDropdown.findElement(By.xpath("span[text()='"+item+"']"));
		}catch(Exception e){
			return false;
		}
		return true;
	}

    public String getElementText(WebElement element) {
    	String text = "";
    	try{
    		text = element.getText();
    	}catch(Exception e){
    		Log.error("Failed to get text of element '"+element+"'");
    	}
    	return text;
    }

    public String getElementAttribute(WebElement element, String attr) {
    	String text = "";
    	try{
    		text = element.getAttribute(attr);
    	}catch(Exception e){
    		Log.error("Failed to get attribute value of element '"+attr+"'");
    	}
    	return text;
    }

    public boolean isPageTitleMatch(String title, String keywords){
    	if(title.contains(keywords)){
    		return true;
    	}
    	String[] words = keywords.split(" ");
    	for(String word:words){
    		if(!title.contains(word.trim())){
    			return false;
    		}
    	}
    	return true;
    }

    public String getSiteErrorMsg(){
    	String errMsg = "";
    	for(WebElement msg:siteErrorMessage){
    		errMsg += msg.getText();
    	}
    	return errMsg;
    }

    public List<String> getLicenseMissingText(){
    	List<String> licenseMissingMsg = new ArrayList<String>();
    	for(WebElement p:licenseMissingText){
    		String text = getElementText(p).trim();
    		licenseMissingMsg.add(text);
    	}
    	if(licenseMissingMsg.size()>0){
    		licenseMissingModalOKBtn.click();
    	}
    	return licenseMissingMsg;
    }

	public ExcelUtility getExcelUtility() {
		return TestSetup.getExcelUtility();
	}

    public DataTablePage buildDataTablePage(By tableBy) {
		WebElement tableContext = driver.findElement(tableBy);
		DataTablePage dataTable = DataTablePage.getDataTablePage(driver, tableContext,
				this.testSetup, this.strBaseURL, this.strPageURL);
		return dataTable;
	}

	public static void verifyPageLoadedInNewTab(WebDriver webDriver, BooleanSupplier pageLoadedMethod) {
		String parentWindow = webDriver.getWindowHandle();
		try {
			Set<String> handles = webDriver.getWindowHandles();
			for (String windowHandle : handles) {
				if (!windowHandle.equals(parentWindow)) {
					webDriver.switchTo().window(windowHandle);
					pageLoadedMethod.getAsBoolean();
					break;
				}
			}
		} finally {
			webDriver.switchTo().window(parentWindow);
		}
	}
	
	public boolean verifyScreenshotWithBaseline(String testCaseID, String name) throws IOException{
		return verifyScreenshotWithBaseline(testCaseID, name, null);
	}
	
	public boolean verifyScreenshotWithBaseline(String testCaseID, String name, Rectangle rect) throws IOException{
		String baseFile = Paths
				.get(TestSetup.getRootPath(), "\\selenium-wd\\data\\test-expected-data\\screenshots")
				.toString() + File.separator + testCaseID + File.separator + name + ".png";
		String actualFile = Paths
				.get(TestSetup.getRootPath(), "\\selenium-wd\\data\\test-data\\screenshots")
				.toString() + File.separator + testCaseID + File.separator + name + ".png";
		ScreenShotOnFailure.captureBrowserScreenShot(driver, actualFile, rect);
		boolean generateBaseline = TestContext.INSTANCE.getTestSetup().isGenerateBaselineScreenshots();
		
		if (!verifyActualImageWithBase(actualFile, baseFile, generateBaseline)) {
			return false;
		}
		Files.delete(Paths.get(actualFile));
		return true;
	}
	
	public Dimension getBrowserSize(){
		return driver.manage().window().getSize();
	}
	public BufferedImage cropImage(BufferedImage src, Rectangle rect) {
		BufferedImage dest = src.getSubimage(rect.x, rect.y, rect.width, rect.height);
		return dest;
	}

	public boolean verifyActualImageWithBase(String pathToActualImage, String pathToBaseImage) throws IOException{
		return verifyActualImageWithBase(pathToActualImage, pathToBaseImage, false);
	}

	public boolean verifyActualImageWithBase(String pathToActualImage, String pathToBaseImage, boolean generateBaseline) throws IOException {
		if(generateBaseline){
			FileUtility.copyFile(pathToActualImage, pathToBaseImage);
			return true;
		}
		ImageComparisonResult result = ImagingUtility.compareImages(pathToActualImage, pathToBaseImage);
		if ((result.getFailureMessage() != null) && (result.isEqual() == true)) {
			return false;
		}
		return true;
	}

	public void waitForAJAXCallsToComplete() {
		ExpectedCondition<Boolean> jQueryActiveComplete = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				try {
					Object jQueryActive = ((JavascriptExecutor)d).executeScript("return jQuery.active");
					if (jQueryActive.toString().equalsIgnoreCase("0")) {
						return true;
					}
				} catch (WebDriverException e) {
					Log.info("jQuery NOT available. Skipping wait on jQuery.active");
					return true;
				}
				return false;
			}
		};
		ExpectedCondition<Boolean> documentReadyComplete = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				Object documentReadyState = ((JavascriptExecutor)d).executeScript("return document.readyState");
				if (documentReadyState.toString().equalsIgnoreCase("complete")) {
					return true;
				}
				return false;
			}
		};
		(new WebDriverWait(driver, timeout)).until(jQueryActiveComplete);
		(new WebDriverWait(driver, timeout)).until(documentReadyComplete);
	}
}