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
	public static final String STRURLPath = "";
	public static final String STRPageTitle = "Home - Surveyor";
	public static final String STRSurveyorDashboard = "Surveyor Dashboard";
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Administrator')]")
	private WebElement dropDownAdministrator;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Log Out')]")
	private WebElement linkLogOut;
	
	@FindBy(how = How.CSS, using = "css=h1")
	private WebElement dbLabel; 
	
//  commented out the follwoing code because of UI changes
//	@FindBy(how = How.CSS, using = "[href='/PicarroAdministration']")
//	private WebElement btnAdministration;
	
//	@FindBy(how = How.XPATH, using = "/html/body/div[2]/div[2]/div/div/p[2]/a")
//	private WebElement linkFollowASurveyor;
	
//	@FindBy(how = How.CSS, using = "[href='/Account/Logout']")
//	private WebElement btnLogout;	
	
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
	
	public boolean checkIfAtHomePage() {
		if (driver.getTitle().equalsIgnoreCase(STRPageTitle))
			return true;
		
		return false;
	}
	
//  commented out the following code because of UI changes 	
//	public AdministrationPage navigateToAdministationPage() {
//		
//		this.btnAdministration.click();
//		
//		if (this.testSetup.isRunningDebug())
//			this.testSetup.slowdownInSeconds(3);
//		
//		AdministrationPage administrationPage = new AdministrationPage(this.driver, this.strBaseURL, this.testSetup);
//		PageFactory.initElements(this.driver,  administrationPage);
//		
//		return administrationPage;
//	}
//	
//	public PrimePage navigateToPrimePage() {
//		
//		this.linkFollowASurveyor.click();
//		
//		if (this.testSetup.isRunningDebug())
//			this.testSetup.slowdownInSeconds(3);
//		
//		PrimePage primePage = new PrimePage(this.driver, this.strBaseURL, this.testSetup);
//		PageFactory.initElements(this.driver,  primePage);
//		
//		return primePage;
//	}
}