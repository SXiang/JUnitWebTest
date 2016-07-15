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

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

	protected int timeout = 30;   // Intermittent test failures seen with 15 seconds. Increasing timeout to 30 seconds.

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

	public static enum ElementType{BUTTON,LABEL,CHECKBOX,RADIOBUTTON,INPUT
		,DIVISION, LINK, OPTION, ICON, DROPDOWN};
	public BasePage(WebDriver driver, TestSetup testSetup, String strBaseURL, String strPageURL) {
		this.driver = driver;
		this.testSetup = testSetup;
		this.strBaseURL = strBaseURL;
		this.strPageURL = strPageURL;
	}

	public void open() {
		driver.get(strPageURL);
		this.waitForPageToLoad();
	}

	public String getStrPageURL() {
		return this.strPageURL;
	}

	public boolean isElementPresent(By by) {
		try {
			this.driver.findElement(by);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}

	public boolean isElementPresent(String strXPath) {
		return isElementPresent(By.xpath(strXPath));
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

	protected void sendKeysToTextArea(WebElement textAreaEula, String eula) {
		// Chromedriver does NOT send keys correctly to TextArea for some controls. 
		// Use Actions workaround to send keys instead.
		if(eula == null){
			return;
		}
		Log.info("Send '"+eula+"' to eula text area");
		textAreaEula.clear();
		Actions actions = new Actions(driver);
		actions.moveToElement(textAreaEula);
		actions.click();
		actions.sendKeys(eula);
		actions.build().perform();
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
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public void minimizeBrowserWindow(){
		Log.info("Minimize browser window");
		driver.manage().window().setSize(new Dimension(0,0));
	}
    public void maxmizeBrowserWindow(){
    	Log.info("Maximize browser window");
    	driver.manage().window().maximize();
	}

    public void SelectCheckbox(WebElement checkbox) {
    	if (!checkbox.isSelected()){
    		jsClick(checkbox);
    	}
    }

    public void UnselectCheckbox(WebElement checkbox) {
    	if (checkbox.isSelected()){
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
				selected = true;
			}catch(Exception e){
				numTry++;
				Log.error("Failed to select option '"+option+"'");
			}
		}while(!selected&&numTry<5);
		if(!selected){
			WebElement opt =  dropdown.findElement(optBy);
			opt.click();
			selected = true;
		}
		return selected;
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
}