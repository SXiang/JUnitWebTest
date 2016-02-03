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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

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

	public boolean isElementPresent(String strXPath) {
		try {
			this.driver.findElement(By.xpath(strXPath));
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
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

	public void waitForPageToLoad() {
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
	}

	/*
	 * Default implementation of this method makes a call to waitForPageToLoad(). Inherited Page classes can provide a page specific implementation of this method.
	 */
	public void waitForPageLoad() {
		waitForPageToLoad();
	}
}