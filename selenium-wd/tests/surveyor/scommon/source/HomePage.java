/**
 * 
 */
package surveyor.scommon.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import common.source.BasePage;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class HomePage extends BasePage {
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
	
	@FindBy(how = How.XPATH, using = "//*[@id='picarro-administration-menu']/a")
	private WebElement linkPicarroAdmin;
	private String strLinkPicarroAdminXPath = "//*[@id='picarro-administration-menu']/a";
	
	@FindBy(how = How.XPATH, using = "//*[@id='customer-administration-menu']/a")
	private WebElement linkCusAdmin;
	private String strLinkCusAdminXPath = "//*[@id='customer-administration-menu']/a";
	
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
	
	public LoginPage logout() {
		this.dropDownAdministrator.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);
		
		this.linkLogOut.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);
		
		LoginPage loginPage = new LoginPage(this.driver, this.strBaseURL, this.testSetup);
		
		return loginPage;
	}
	
	public boolean checkVisitilityForPicarroDR(String loginUser) {
		return true;
	}
	
	public boolean checkVisitilityForPicarroSU(String loginUser) {
		return true;
	}
	
	public boolean checkVisitilityForPicarroUA(String loginUser) {
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
		
		if (!this.linkFleetMap.isDisplayed())
			return false;
		
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
		this.linkDashboard.click();
		
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
		
		this.linkViewAllDrivingSurveys.click();
		
		if (!this.driver.getCurrentUrl().contains("MeasurementSessions"))
			return false;
		
		if (!this.driver.getTitle().contains("Measurement Sessions"))
			return false;
		
		this.linkDashboard.click();
		
		return true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	}	
}