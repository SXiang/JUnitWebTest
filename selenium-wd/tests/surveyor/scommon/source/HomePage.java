/**
 * 
 */
package surveyor.scommon.source;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.BrowserCommands;
import common.source.Log;
import common.source.TestSetup;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;

/**
 * @author zlu
 *
 */
public class HomePage extends SurveyorBasePage {
	public static final String STRURLPath = "/Home";
	public static final String STRPageTitle = String.format("%s - %s", 
			Resources.getResource(ResourceKeys.Index_Title), Resources.getResource(ResourceKeys.Constant_Surveyor));
	public static final String STRPageContentText = Resources.getResource(ResourceKeys.Index_Heading);
	public static final String STRSurveyorDashboard = "Surveyor Dashboard";
	public static final String STRReleaseNotes="ReleaseNotes";
	public static final String STRReleaseNotesLink="Surveyor_v2.1_ReleaseNotes.pdf";	
	public static final String EQ_REPORT_LINK_XPATH = "//*[@id='report-investigation']";
	
	@FindBy(how = How.XPATH, using = "//*[@id='wrapper']/nav/div[1]/a/img")
	private WebElement picarroLogo;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Administrator')]")
	private WebElement dropDownAdministrator;
	
	@FindBy(how = How.XPATH, using = "//*[@id='wrapper']/nav/div[2]/ul/li/a")
	private WebElement dropDownLoginUser;	
	
	@FindBy(how = How.XPATH, using = "//*[@id='selected-timezone']")
	private WebElement dropDownTimeZone;
	
	@FindBy(how = How.XPATH, using = "//*[@id='user-preferences']/a")
	private WebElement linkPreference;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div/ul/li/ul/li[2]/a")
	private WebElement linkChangePwd;
	
	@FindBy(how = How.XPATH, using = "//*[@id='user-release-notes']/a")
	private WebElement linkReleaseNotes;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div/ul/li/ul/li[4]/a")
	private WebElement linkManual;
	
	@FindBy(how = How.XPATH, using = "//*[@id='wrapper']/nav/ul/li/ul/li[6]/a")
	private WebElement linkLogOut;
	
	@FindBy(how = How.XPATH, using = "//*[@id='home']/a")
	private WebElement linkDashboard;
	
	@FindBy(how = How.XPATH, using = "//*[@id='driving-surveys']/a")
	private WebElement linkDrivingSurveys;
	
	@FindBy(how = How.XPATH, using = "//*[@id='picarro-surveyors']/a")
	private WebElement linkSurveyors;
	
	@FindBy(how = How.XPATH, using = "//*[@id='fleet-map']/a")
	private WebElement linkFleetMap;
	
	@FindBy(how = How.XPATH, using = "//a[@data-target='#report-menu']")
	private WebElement linkReports;
	
	private String strLinkReportsXPath = "//*[@id='report-menu']/a";

	@FindBy(how = How.XPATH, using = "//*[@id='user-feedback']/a")
	private WebElement linkSendFeedback;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[1]/div/h1/strong")
	private WebElement labelSurveyorDashboard;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div[1]/div/div[1]/h3")
	private WebElement labelActiveSurveyors;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div[1]/div/div[2]/div[2]/a")
	private WebElement linkViewAllSurveyors;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div[2]/div/div[1]/h3")
	private WebElement labelRecentDrivingSurveys;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div[2]/div/div[2]/div[2]/a")
	private WebElement linkViewAllDrivingSurveys;
	
	@FindBy(how = How.XPATH, using = "//*[@id='footer']/div/footer/p")
	private WebElement labelFooter;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-compliance']")
	private WebElement linkCompliance;

	// Link may NOT be present for all users. Conditionally detect this link. 
	private WebElement linkInvestigation;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-reference-gas']")
	private WebElement linkReferenceGas;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-system-history']")
	private WebElement linkSystemHistory;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable-Surveyor']/tbody/tr[1]/td[3]/a")
	private WebElement linkFirstOnlineSurveyor;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable-Session']/tbody")
	private WebElement tableRecentDrivingSurveys;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable-Session']/tbody/tr[1]/td[5]/a")
	private WebElement firstDrivingSurveyLink;

	private String strTRRDSXPath = "//*[@id='datatable-Session']/tbody/tr";
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable-Surveyor']/tbody")
	private WebElement tableActiveSurveyors;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable-Session_length']/label/select")
	private WebElement paginationInputRDS;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable-Surveyor_length']/label/select")
	private WebElement paginationInputAS;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable-Session_info']")
	private WebElement labelRDSPagesInfo;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable-Surveyor_info']")
	private WebElement lableASPagesInfo;
	
	private String pageNumLinkBaseXPath = "//*[@id='datatable-Session_paginate']/span";
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable-Session_next']")
	private WebElement btnPageNext;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[1]/div/h1/strong")
	private WebElement subTitleSurveyors;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[1]/div/h1/strong")
	private WebElement subTitileDrivingSurveys;
	
	@FindBy(how = How.XPATH, using = "//*[@id='customer-administration-manage-surveyors']/a")
	private WebElement linkManageSurveyors;
	
	@FindBy(how = How.XPATH, using = "//*[@id='picarro-administration-calibration']/a")
	protected WebElement linkPicAdminCalibration;
	
	@FindBy(how = How.XPATH, using = "//*[@id='picarro-administration-user-feedback']/a")
	protected WebElement linkPicAdminViewUserFeedback;

	@FindBy(how = How.XPATH, using = "//*[@id='picarro-administration-manage-release-notes']/a")
	protected WebElement linkPicAdminManageReleaseNotes;
	
	@FindBy(how = How.XPATH, using = "//*[@id='picarro-administration-manage-customers']/a")
	protected WebElement linkPicAdminManageCus;

	@FindBy(how = How.XPATH, using = "//*[@id='picarro-administration-manage-users']/a")
	protected WebElement linkPicAdminManageUsers;

	@FindBy(how = How.XPATH, using = "//*[@id='picarro-administration-manage-locations']/a")
	protected WebElement linkPicAdminManageLoc;

	@FindBy(how = How.XPATH, using = "//*[@id='picarro-administration-manage-surveyors']/a")
	protected WebElement linkPicAdminManageSur;

	@FindBy(how = How.XPATH, using = "//*[@id='picarro-administration-manage-analyzers']/a")
	protected WebElement linkPicAdminManageAnl;

	@FindBy(how = How.XPATH, using = "//*[@id='picarro-administration-manage-ref-gas-bottles']/a")
	protected WebElement linkPicAdminManageRefGasBottles;

	@FindBy(how = How.XPATH, using = "//*[@id='picarro-administration-manage-surveyor-history']/a")
	protected WebElement linkPicAdminManageSurHistories;

	@FindBy(how = How.XPATH, using = "//*[@id='picarro-administration-analyzer-logs']/a")
	protected WebElement linkPicAdminViewAnlLogs;

	@FindBy(how = How.XPATH, using = "//*[@id='picarro-administration-server-log']/a")
	protected WebElement linkPicAdminViewSurLogs;
	
	@FindBy(how = How.XPATH, using = "//*[@id='00000000-0000-0000-0001-000000000000']/a")
	protected WebElement dropDownPST;

	/**
	 * @param driver
	 * @param baseURL
	 * @param testSetup
	 */
	public HomePage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);
		
		Log.info("\nThe Home Page URL is: " + this.strPageURL);
	}
	
	public boolean checkIfAtHomePage() {
		if (driver.getTitle().equalsIgnoreCase(STRPageTitle))
			return true;
		
		return false;
	}
	
	@Override
	public void open() {	
		driver.get(strPageURL);
		this.waitForPageLoad();
	}

	public WebElement getLinkFirstOnlineSurveyor() {
		return this.linkFirstOnlineSurveyor;
	}

	public WebElement getLinkPicAdminCalibration() {
		return this.linkPicAdminCalibration;
	}

	public WebElement getLinkPicAdminViewUserFeedback() {
		return this.linkPicAdminViewUserFeedback;
	}
	public WebElement getLinkPicAdminManageReleaseNotes() {
		return this.linkPicAdminManageReleaseNotes;
	}
	
	public WebElement getLinkPicAdminManageCus() {
		return this.linkPicAdminManageCus;
	}

	public WebElement getLinkPicAdminManageUsers() {
		return this.linkPicAdminManageUsers;
	}	

	public WebElement getLinkPicAdminManageLoc() {
		return this.linkPicAdminManageLoc;
	}	

	public WebElement getLinkPicAdminManageSur() {
		return this.linkPicAdminManageSur;
	}	

	public WebElement getLinkPicAdminManageAnl() {
		return this.linkPicAdminManageAnl;
	}	

	public WebElement getLinkPicAdminManageRefGasBottles() {
		return this.linkPicAdminManageRefGasBottles;
	}	

	public WebElement getLinkPicAdminManageSurHistories() {
		return this.linkPicAdminManageSurHistories;
	}	

	public WebElement getLinkPicAdminViewAnlLogs() {
		return this.linkPicAdminViewAnlLogs;
	}	

	public WebElement getLinkPicAdminViewSurLogs() {
		return this.linkPicAdminViewSurLogs;
	}
	
	public WebElement getDropDownLoginUser() {
		return this.dropDownLoginUser;
	}

	public WebElement getLinkReleaseNotes() {
		return this.linkReleaseNotes;
	}

	public WebElement getLinkPreference() {
		return linkPreference;
	}

	public boolean checkVisibilityForPicarroSUP(String loginUser) {
		if (!this.picarroLogo.isDisplayed())
			return false;
		
		WebElement userDropDown = this.driver.findElement(By.xpath("//a[contains(text(),'" + loginUser + "')]"));
		if (!userDropDown.getText().trim().equalsIgnoreCase(loginUser))
			return false;

		if (!this.linkDashboard.isDisplayed())
			return false;
		
		if (this.isElementPresent(this.strLinkCusAdminXPath)) {
			System.out.format("\nlinkCusAdmin\n");
			return false;
		}
			
		if (this.isElementPresent(this.strLinkPicarroAdminXPath)) {
			System.out.format("\nlinkPicarroAdmin\n");
			return false;
		}
		
		if (!this.linkDrivingSurveys.isDisplayed())
			return false;
		
		if (!this.linkSurveyors.isDisplayed())
			return false;
		
		if (!this.linkFleetMap.isDisplayed())
			return false;
		
		if (!this.linkReports.isDisplayed())
			return false;
		
		if (!this.linkSendFeedback.isDisplayed())
			return false;
		
		if (!this.labelFooter.isDisplayed())
			return false;
		
		return true;
	}

	public boolean checkVisibilityForPicarroAdministrator(String loginUser) {
		if (!this.picarroLogo.isDisplayed())
			return false;
		
		WebElement userDropDown = this.driver.findElement(By.xpath("//a[contains(text(),'" + loginUser + "')]"));
		if (!userDropDown.getText().trim().equalsIgnoreCase(loginUser))
			return false;		
		
		if (!this.linkDashboard.isDisplayed())
			return false;
		
		if (!this.linkPicarroAdmin.isDisplayed())
			return false;
		
		if (!this.linkDrivingSurveys.isDisplayed())
			return false;
		
		if (!this.linkSurveyors.isDisplayed())
			return false;
		
		if (!this.linkFleetMap.isDisplayed())
			return false;
		
		if (!this.linkReports.isDisplayed())
			return false;
		
		if (!this.linkSendFeedback.isDisplayed())
			return false;
		
		if (!this.labelFooter.isDisplayed())
			return false;
		
		return true;		
	}
	
	public boolean checkVisibilityForCusDR(String loginUser) {
		WebElement userDropDown = this.driver.findElement(By.xpath("//a[contains(text(),'" + loginUser + "')]"));
		if (!userDropDown.getText().trim().equalsIgnoreCase(loginUser))
			return false;
		
		if (!this.linkDashboard.isDisplayed())
			return false;
		
		if (this.isElementPresent(this.strLinkCusAdminXPath)) {
			System.out.format("\nlinkCusAdmin\n");
			return false;
		}
			
		if (this.isElementPresent(this.strLinkPicarroAdminXPath)) {
			System.out.format("\nlinkPicarroAdmin\n");
			return false;
		}		
		
		if (!this.linkDrivingSurveys.isDisplayed())
			return false;
		
		if (!this.linkSurveyors.isDisplayed())
			return false;
		
		//Need check with Chris Vale if "Fleet Map" should be invisible to driver?
//		if (!this.linkFleetMap.isDisplayed())
//			return false;
		
		if (this.isElementPresent(this.strLinkReportsXPath))
			return false;
		
		if (!this.linkSendFeedback.isDisplayed())
			return false;
		
		if (!this.labelFooter.isDisplayed())
			return false;
		
		return true;
	}
	
	public boolean checkVisibilityForCusSU(String loginUser) {
		WebElement userDropDown = this.driver.findElement(By.xpath("//a[contains(text(),'" + loginUser + "')]"));
		if (!userDropDown.getText().trim().equalsIgnoreCase(loginUser))
			return false;
		
		if (!this.linkDashboard.isDisplayed())
			return false;
		
		if (!this.linkDrivingSurveys.isDisplayed())
			return false;
		
		if (!this.linkSurveyors.isDisplayed())
			return false;
		
		if (!this.linkFleetMap.isDisplayed())
			return false;
		
		if (!this.linkReports.isDisplayed())
			return false;
		
		if (this.isElementPresent(this.strLinkCusAdminXPath)) {
			System.out.format("\nlinkCusAdmin\n");
			return false;
		}
			
		if (this.isElementPresent(this.strLinkPicarroAdminXPath)) {
			System.out.format("\nlinkPicarroAdmin\n");
			return false;
		}
		
		if (!this.linkSendFeedback.isDisplayed())
			return false;
		
		if (!this.labelFooter.isDisplayed())
			return false;
		
		return true;
	}
	
	public boolean checkVisibilityForCusUA(String loginUser) {	
		WebElement userDropDown = this.driver.findElement(By.xpath("//a[contains(text(),'" + loginUser + "')]"));
		if (!userDropDown.getText().trim().equalsIgnoreCase(loginUser))
			return false;
		
		if (!this.linkDashboard.isDisplayed())
			return false;
		
		if (!this.linkDrivingSurveys.isDisplayed())
			return false;
		
		if (!this.linkSurveyors.isDisplayed())
			return false;
		
		if (!this.linkFleetMap.isDisplayed())
			return false;
		
		if (!this.linkReports.isDisplayed())
			return false;
		
		if (!this.linkCusAdmin.isDisplayed())
			return false;
		
		try {
			if (this.linkPicarroAdmin.isDisplayed())
				return false;
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
		}
		
		if (!this.linkSendFeedback.isDisplayed())
			return false;
		
		if (!this.labelFooter.isDisplayed())
			return false;
		
		return true;
	}
	
	public boolean checkAdministratorHomePage() {
		if (!this.picarroLogo.isDisplayed())
			return false;
		
		if (!this.dropDownAdministrator.isDisplayed())
			return false;
		
		if (!this.linkDashboard.isDisplayed())
			return false;
		
		if (!this.linkDrivingSurveys.isDisplayed())
			return false;
		
		if (!this.linkSurveyors.isDisplayed())
			return false;
		
		if (!this.linkFleetMap.isDisplayed())
			return false;
		
		if (!this.linkReports.isDisplayed())
			return false;
		
		if (!this.linkPicarroAdmin.isDisplayed())
			return false;
		
		if (!this.linkSendFeedback.isDisplayed())
			return false;
		
		return true;	
	}	
	
	public boolean checkAdministratorDashboard() {
		this.linkDashboard.click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if (!this.labelSurveyorDashboard.isDisplayed())
			return false;
		
		if (!this.labelActiveSurveyors.isDisplayed())
			return false;
		
		if (!this.linkViewAllSurveyors.isDisplayed())
			return false;
		
		if (!this.labelRecentDrivingSurveys.isDisplayed())
			return false;
		
		if (!this.linkViewAllDrivingSurveys.isDisplayed())
			return false;
		
		if (!this.labelFooter.isDisplayed())
			return false;
		
		return true;
	}	
	
	public boolean checkDashBoardViewAllSurveyorsLink() {
		// 1. Verify link is Online and is clickable.
		if (!this.getLinkFirstOnlineSurveyor().getText().equals("Online")) 
			return false;

		// Click on the first Online Surveyor
		this.clickOnFirstOnlineSurveyorLink();
		
		// 2. Verify user is navigated to Observer view
		// Wait for Observer view page to load.
		ObserverViewPage observerViewPage = new ObserverViewPage(driver, testSetup, ObserverViewPage.STRURLPath);
		PageFactory.initElements(driver, observerViewPage);		
		observerViewPage.waitForPageLoad();
		observerViewPage.waitForConnectionComplete();
		
		// Click on Picarro icon present at bottom of the page 
		observerViewPage.clickPicarroLogoButton();
		
		// 3. Verify User is navigated back to Home page
		// Wait for Home page to load.
		this.waitForPageLoad();

		// Click on View All Surveyors link present in Active Surveyors section		
		this.linkViewAllSurveyors.click();
		
		// 4. Verify User is navigated to Surveyors Page
		// Wait for Surveyor Systems page to load.
		SurveyorSystemsPage surveyorSystemsPage = new SurveyorSystemsPage(driver, testSetup, SurveyorSystemsPage.STRURLPath);
		PageFactory.initElements(driver, surveyorSystemsPage);		
		surveyorSystemsPage.waitForPageLoad();
		
		Log.info(String.format("\nThe current URL is: %s\n", this.driver.getCurrentUrl()));
		Log.info(String.format("\nThe current page title is: %s\n", this.driver.getTitle()));
		
		if (!this.driver.getCurrentUrl().contains("SurveyorSystems"))
			return false;
		
		if (!this.driver.getTitle().contains("Surveyors - Surveyor"))
			return false;
		
		return true;
	}	
	
	public boolean checkDashBoardViewAllDrivingSurveysLink() {
		// Click on View all driving surveys.
		this.linkViewAllDrivingSurveys.click();

		// Wait for driving survey page to load.
		MeasurementSessionsPage measurementSessionsPage = new MeasurementSessionsPage(driver, testSetup, MeasurementSessionsPage.STRURLPath);
		PageFactory.initElements(driver, measurementSessionsPage);		
		measurementSessionsPage.waitForPageLoad();

		if (!this.driver.getCurrentUrl().contains("MeasurementSessions"))
			return false;
		
		if (!this.driver.getTitle().contains("Driving Surveys"))
			return false;
		
		// Wait for the survey to become Completed.
		measurementSessionsPage.waitForFirstSurveyInTableToBeCompleted();
		
		// Click on a recent driving survey
		measurementSessionsPage.clickOnFirstViewSurveyLink();
		
		// 1. Verify User is navigated to Survey view 
		// Wait for Survey view to load.
		SurveyViewPage surveyViewPage = new SurveyViewPage(driver, testSetup, SurveyViewPage.STRURLPath);
		PageFactory.initElements(driver, surveyViewPage);		
		surveyViewPage.waitForPageLoad();
		surveyViewPage.checkIfAtSurveyViewPage();
		
		// Click on Picarro icon present at bottom of the page
		surveyViewPage.clickPicarroLogoButton();
		this.waitForPageLoad();
		
		// 2. Verify User is navigated to Driving Surveys Page
		// Click on View All Driving Surveys link present under recent driving surveys section
		this.clickOnViewAllDrivingSurveysLink();
		measurementSessionsPage.waitForPageLoad();
		
		// 3. Verify User is navigated to Survey view
		// Select a survey and click on View Survey button
		measurementSessionsPage.clickOnFirstViewSurveyLink();
		surveyViewPage.waitForPageLoad();
		surveyViewPage.checkIfAtSurveyViewPage();

		return true;
	}
	
	public void clickOnViewAllDrivingSurveysLink() {
		this.linkViewAllDrivingSurveys.click();
	}

	public void clickOnFirstOnlineSurveyorLink() {
		getLinkFirstOnlineSurveyor().click();
	}

	public WebElement getLinkSurveyors() {
		return this.linkSurveyors;
	}
	
	public WebElement getLinkSendFB() {
		return this.linkSendFeedback;
	}
	
	public WebElement getLinkReports() {
		return this.linkReports;
	}
	
	public WebElement getLinkCompliance() {
		return this.linkCompliance;
	}
	
	public WebElement getLinkInvestigation() {
		try {
			this.linkInvestigation = driver.findElement(By.xpath(EQ_REPORT_LINK_XPATH));
		} catch (NoSuchElementException  e) {
			Log.error(e.toString());
		}
		return this.linkInvestigation;
	}
	
	public WebElement getLinkReferenceGas() {
		return this.linkReferenceGas;
	}
	
	public WebElement getLinkSystemHistory() {
		return this.linkSystemHistory;
	}
	
	public WebElement getLinkDrivingSurveys() {
		return this.linkDrivingSurveys;
	}
	
	public WebElement getLinkFleetMap() {
		return this.linkFleetMap;
	}
	
	public String getPageNumLinkBaseXPath() {
		return this.pageNumLinkBaseXPath;
	}
	
	public WebElement getLabelRDSPagesInfo() {
		return this.labelRDSPagesInfo;
	}
	
	public WebElement getLabelActiveSurveyors() {
		return this.labelActiveSurveyors;
	}
	
	public WebElement getBtnPageNext() {
		return this.btnPageNext;
	}
	
	public WebElement getSubTitleSurveyors() {
		return this.subTitleSurveyors;
	}
	
	public WebElement getSubTitleDrivingSurveys() {
		return this.subTitileDrivingSurveys;
	}
	
	public WebElement getLinkManageSurveyors() {
		return this.linkManageSurveyors;
	}
	
	public void setPagination(String str) {
		List<WebElement> options = this.paginationInputRDS.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if(str.equals(option.getText().trim()))
				option.click();		
		}
	}	

	public void clickOnFirstMatchingDrivingSurvey(String surveyTag) {
		this.testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		String tagXPath;
		String completedLinkXPath;
		WebElement tagCell;
		WebElement completedLink;
		
		List<WebElement> rows = this.tableRecentDrivingSurveys.findElements(By.xpath(this.strTRRDSXPath));
		for (int rowNum = 1; rowNum <= rows.size(); rowNum++) {
			tagXPath = this.strTRRDSXPath + "["+rowNum+"]/td[1]";
			tagCell = this.tableRecentDrivingSurveys.findElement(By.xpath(tagXPath));
			
			if (tagCell.getText().trim().equalsIgnoreCase(surveyTag)) {
				completedLinkXPath = this.strTRRDSXPath + "["+rowNum+"]/td[5]/a";
				completedLink = this.tableRecentDrivingSurveys.findElement(By.xpath(completedLinkXPath));
				Log.info("Found survey tag. Clicking on the Completed link");
				completedLink.click();
				Log.info("DONE clicking on the Completed link");
				break;
			}
		}
	}

	public List<String> getTagListRecentDrivingSurveys() {
		List<String> tagList = new ArrayList<String>();
		
		this.testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		String tagXPath;
		WebElement tagCell;
		
		List<WebElement> rows = this.tableRecentDrivingSurveys.findElements(By.xpath(this.strTRRDSXPath));
		
		for (int rowNum = 1; rowNum <= rows.size(); rowNum++) {
			tagXPath = this.strTRRDSXPath + "["+rowNum+"]/td[1]";
			tagCell = this.tableRecentDrivingSurveys.findElement(By.xpath(tagXPath));
			
			tagList.add(tagCell.getText().trim());
		}
		
		return tagList;
	}
	
	public WebElement getDropDownTimeZone() {
		return dropDownTimeZone;
	}
	
	public void setTimeZoneToPST() {
		getDropDownPST().click();
	}
	
	public WebElement getDropDownPST() {
		return this.dropDownPST;
	}

	public boolean getReleaseNotesLink() {
		return getLinkReleaseNotes().getAttribute("href").contains(STRReleaseNotesLink);
	}

	public boolean isReleaseNotes(WebDriver driver,String winHandle){
		if(driver.switchTo().window(winHandle).getCurrentUrl().contains(STRReleaseNotes)){
			return true;
		}
		return false;
	}
	
	public void waitForReleaseNotesToLoad(WebDriver driver,String winHandle) {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	return d.switchTo().window(winHandle).getCurrentUrl().contains(STRReleaseNotes);
            }
        });
    }
	
	public void waitForReleaseNotesLinktoLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	return getLinkReleaseNotes().isDisplayed() ;
            }
        });
    }
	
	@Override
	public void waitForPageLoad() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(STRPageContentText);
            }
        });
    }
	
	public void waitForFirstDrivingSurveyToBeCompleted() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
            	BrowserCommands.refresh();
            	return firstDrivingSurveyLink.getText().equals("Completed");
            }
        });
    }
}