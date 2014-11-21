/**
 * 
 */
package surveyor.scommon.source;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class HomePage extends SurveyorBasePage {
	public static final String STRURLPath = "/Home";
	public static final String STRPageTitle = "Home - Surveyor";
	
	public static final String STRSurveyorDashboard = "Surveyor Dashboard";
	
	//@FindBy(how = How.XPATH, using = "/html/body/div[1]/div/div/a/img")
	@FindBy(how = How.XPATH, using = "//*[@id='wrapper']/nav/div[1]/a/img")
	private WebElement picarroLogo;
	
	//@FindBy(how = How.XPATH, using = "/html/body/div[1]/div/ul/li")
	@FindBy(how = How.XPATH, using = "//*[@id='wrapper']/nav/ul/li/a")
	private WebElement dropDownAdministrator;
	
	//@FindBy(how = How.XPATH, using = "/html/body/div[1]/div/ul/li/a")
	@FindBy(how = How.XPATH, using = "//*[@id='wrapper']/nav/ul/li/a")
	private WebElement dropDownLoginUser;	
	
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div/ul/li/ul/li[1]/a")
	private WebElement linkPreference;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div/ul/li/ul/li[2]/a")
	private WebElement linkChangePwd;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div/ul/li/ul/li[3]/a")
	private WebElement linkReleaseNotes;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/div/ul/li/ul/li[4]/a")
	private WebElement linkManual;
	
	//@FindBy(how = How.XPATH, using = "/html/body/div[1]/div/ul/li/ul/li[6]/a")
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
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-menu']/a")
	private WebElement linkReports;
	private String strLinkReportsXPath = "//*[@id='report-menu']/a";

//	@FindBy(how = How.XPATH, using = "//*[@id='picarro-administration-menu']/a")
//	private WebElement linkPicarroAdmin;
//	private String strLinkPicarroAdminXPath = "//*[@id='picarro-administration-menu']/a";
//	
//	@FindBy(how = How.XPATH, using = "//*[@id='customer-administration-menu']/a")
//	private WebElement linkCusAdmin;
//	private String strLinkCusAdminXPath = "//*[@id='customer-administration-menu']/a";
	
	@FindBy(how = How.XPATH, using = "//*[@id='user-feedback']/a")
	private WebElement linkSendFeedback;
	
	//@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[1]/div/h1/strong")
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[1]/div/h1/strong")
	private WebElement labelSurveyorDashboard;
	
	//@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div[1]/div/div[1]/h3")
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div[1]/div/div[1]/h3")
	private WebElement labelActiveSurveyors;
	
	//@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div[1]/div/div[2]/div[2]/a")
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div[1]/div/div[2]/div[2]/a")
	private WebElement linkViewAllSurveyors;
	
	//@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div[2]/div/div[1]/h3")
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div[2]/div/div[1]/h3")
	private WebElement labelRecentDrivingSurveys;
	
	//@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div[2]/div[2]/div/div[2]/div[2]/a")
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div[2]/div/div[2]/div[2]/a")
	private WebElement linkViewAllDrivingSurveys;
	
	@FindBy(how = How.XPATH, using = "//*[@id='footer']/div/footer/p")
	private WebElement labelFooter;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-compliance']")
	private WebElement linkCompliance;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-investigation']")
	private WebElement linkInvestigation;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-reference-gas']")
	private WebElement linkReferenceGas;
	
	@FindBy(how = How.XPATH, using = "//*[@id='report-system-history']")
	private WebElement linkSystemHistory;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable-Session']/tbody")
	private WebElement tableRecentDrivingSurveys;
	//private String tableRecentDrivingSurveysXPath = "//*[@id='datatable-Session']/tbody";
	private String strTRRDSXPath = "//*[@id='datatable-Session']/tbody/tr";

	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable-Surveyor']/tbody")
	private WebElement tableActiveSurveyors;
//	private String tableActiveSurveyorsXPath = "//*[@id='datatable-Surveyor']/tbody";
//	private String strTRASXPath = "//*[@id='datatable-Surveyor']/tbody/tr";
	
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
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public HomePage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);
		
		System.out.println("\nThe Home Page URL is: " + this.strPageURL);
	}
	
	public boolean checkIfAtHomePage() {
		if (driver.getTitle().equalsIgnoreCase(STRPageTitle))
			return true;
		
		return false;
	}
	
	public boolean checkVisitilityForPicarroDR(String loginUser) {
		if (!this.picarroLogo.isDisplayed())
			return false;
		
		if (!this.dropDownLoginUser.getText().trim().equalsIgnoreCase(loginUser))
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
	
	public boolean checkVisitilityForPicarroSU(String loginUser) {
		if (!this.picarroLogo.isDisplayed())
			return false;
		
		if (!this.dropDownLoginUser.getText().trim().equalsIgnoreCase(loginUser))
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
	
	public boolean checkVisitilityForPicarroUA(String loginUser) {
		if (!this.picarroLogo.isDisplayed())
			return false;
		
		if (!this.dropDownLoginUser.getText().trim().equalsIgnoreCase(loginUser))
			return false;
		
		if (!this.linkDashboard.isDisplayed())
			return false;
			
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
		
		if (!this.linkCusAdmin.isDisplayed() && !this.linkCusAdmin.getText().trim().equalsIgnoreCase("Administration"))
			return false;
		
		if (!this.linkSendFeedback.isDisplayed())
			return false;
		
		if (!this.labelFooter.isDisplayed())
			return false;
		
		return true;
	}
	
	public boolean checkVisitilityForPicarroAdministrator(String loginUser) {
		if (!this.picarroLogo.isDisplayed())
			return false;
		
		if (!this.dropDownAdministrator.getText().trim().equalsIgnoreCase(loginUser))
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
	
	public boolean checkVisitilityForCusDR(String loginUser) {
		if (!this.dropDownLoginUser.getText().trim().equalsIgnoreCase(loginUser))
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
	
	public boolean checkVisitilityForCusSU(String loginUser) {
		if (!this.dropDownLoginUser.getText().trim().equalsIgnoreCase(loginUser))
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
	
	public boolean checkVisitilityForCusUA(String loginUser) {	
		if (!this.dropDownLoginUser.getText().trim().equalsIgnoreCase(loginUser))
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
		//high level check for now and more details should be added later
		//testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		this.linkDashboard.click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		this.linkViewAllSurveyors.click();
		
		if (this.testSetup.isRunningDebug()) {
			System.out.format("\nThe current URL is: %s\n", this.driver.getCurrentUrl());
			System.out.format("\nThe current page title is: %s\n", this.driver.getTitle());
		}
		
		if (!this.driver.getCurrentUrl().contains("SurveyorSystems"))
			return false;
		
		if (!this.driver.getTitle().contains("Surveyors - Surveyor"))
			return false;
		
		this.linkDashboard.click();
		
		return true;
	}	
	
	public boolean checkDashBoardViewAllDrivingSurveysLink() {
		this.linkDashboard.click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		this.linkViewAllDrivingSurveys.click();
		
		if (!this.driver.getCurrentUrl().contains("MeasurementSessions"))
			return false;
		
		if (!this.driver.getTitle().contains("Measurement Sessions"))
			return false;
		
		this.linkDashboard.click();
		
		return true;
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}	
}