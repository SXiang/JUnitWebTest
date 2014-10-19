/**
 * 
 */
package surveyor.scommon.source;

import java.util.List;

import org.openqa.selenium.By;
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
	
	@FindBy(how = How.XPATH, using = "//*[@id='buttonCustomerOk']")
	protected WebElement btnOk;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div[1]")
	protected WebElement panelDuplicationError;
	protected String panelDuplicationErrorXPath = "//*[@id='page-wrapper']/div/div[2]/div[1]";

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
	
	public void setPagination(String str) {
		List<WebElement> options = paginationInput.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if(str.equals(option.getText().trim()))
				option.click();		
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}