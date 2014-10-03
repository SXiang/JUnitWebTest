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
public class SurveyorBasePage extends BasePage {
	
	@FindBy(how = How.XPATH, using = "//*[@id='wrapper']/nav/ul/li/a")
	protected WebElement dropDownAdministrator;
	
	@FindBy(how = How.XPATH, using = "//*[@id='wrapper']/nav/ul/li/a")
	protected WebElement dropDownUser;
	
	//@FindBy(how = How.XPATH, using = "//a[contains(text(),'Log Out')]")
	@FindBy(how = How.XPATH, using = "//*[@id='wrapper']/nav/ul/li/ul/li[6]/a")
	protected WebElement linkLogOut;
	
//	@FindBy(how = How.XPATH, using = "//*[@id='picarro-administration-menu']/a")
//	protected WebElement linkPicarroAdmin;
	
	@FindBy(how = How.XPATH, using = "//*[@id='picarro-administration-menu']/a")
	protected WebElement linkPicarroAdmin;
	protected String strLinkPicarroAdminXPath = "//*[@id='picarro-administration-menu']/a";
	
	@FindBy(how = How.XPATH, using = "//*[@id='customer-administration-menu']/a")
	protected WebElement linkCusAdmin;
	protected String strLinkCusAdminXPath = "//*[@id='customer-administration-menu']/a";	
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable_length']/label/select")
	protected WebElement paginationInput;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable_filter']/label/input")
	protected WebElement inputSearch;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody")
	protected WebElement table;
	protected String strTRXPath = "//*[@id='datatable']/tbody/tr";
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable_next']")
	protected WebElement nextBtn;

	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public SurveyorBasePage(WebDriver driver, TestSetup testSetup, String strBaseURL, String strPageURL) {
		super(driver, testSetup, strBaseURL, strPageURL);
	}
	
	public LoginPage logout() {
		this.dropDownUser.click();
		this.linkLogOut.click();
		
		LoginPage loginPage = new LoginPage(this.driver, this.strBaseURL, this.testSetup);
		
		return loginPage;
	}
	
	public void login(String user, String password) {
		LoginPage loginPage = new LoginPage(driver, strBaseURL, testSetup);
		PageFactory.initElements(driver,  loginPage);
		
		loginPage.open();
		
		loginPage.loginNormalAs(user, password);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}