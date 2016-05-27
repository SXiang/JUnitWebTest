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

	protected int timeout = 15;

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

	@FindBy(how = How.XPATH, using = "//li[@id='user-feedback']/a")
	private WebElement linkFeedback;

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

	@FindBy(how = How.XPATH, using = "//li[@id='picarro-administration-user-feedback']/a")
	private WebElement linkViewUserFeedback;

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
		this.linkDashboard.click();
	}

	public void clickOnDrivingSurveyLink() {
		this.linkDrivingSurveys.click();
	}

	public void clickOnSurveyorsLink() {
		this.linkSurveyors.click();
	}

	public void clickOnFleetMapLink() {
		this.linkFleetMap.click();
	}

	public void clickOnFeedbackLink() {
		this.linkFeedback.click();
	}

	public void clickOnReportsLink() {
		this.linkReports.click();
	}

	public void clickOnComplianceReportLink() {
		this.linkComplianceReport.click();
	}

	public void clickOnEQReportLink() {
		this.linkEQReport.click();
	}

	public void clickOnReferenceGasReportLink() {
		this.linkReferenceGasReport.click();
	}

	public void clickOnSystemHistoryReportLink() {
		this.linkSystemHistoryReport.click();
	}

	public void clickOnPicarroAdminLink() {
		this.linkPicarroAdmin.click();
	}

	public void clickOnCalibrationLink() {
		this.linkCalibration.click();
	}

	public void clickOnViewUserFeedbackLink() {
		this.linkViewUserFeedback.click();
	}

	public void clickOnManageCustomersLink() {
		this.linkManageCustomers.click();
	}

	public void clickOnManageUsersLink() {
		this.linkManageUsers.click();
	}

	public void clickOnManageLocationsLink() {
		this.linkManageLocations.click();
	}

	public void clickOnManageSurveyorsLink() {
		this.linkManageSurveyors.click();
	}

	public void clickOnManageAnalyzersLink() {
		this.linkManageAnalyzers.click();
	}

	public void clickOnManageRefGasBottlesLink() {
		this.linkManageRefGasBottles.click();
	}

	public void clickOnManageReleaseNotesLink() {
		this.linkManageReleaseNotes.click();
	}

	public void clickOnManageSurveyorHistoriesLink() {
		this.linkManageSurveyorHistories.click();
	}

	public void clickOnViewAnalyzerLogsLink(String strBaseURL) {
		driver.get(strBaseURL + "/Picarro/AnalyzerLogs");
	}

	public void clickOnViewServerlogsLink(String strBaseURL) {
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
		driver.manage().window().setSize(new Dimension(0,0));
	}
    public void maxmizeBrowserWindow(){
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
}