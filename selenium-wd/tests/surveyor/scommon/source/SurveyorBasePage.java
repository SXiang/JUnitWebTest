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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.BasePage;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class SurveyorBasePage extends BasePage {	
	
	@FindBy(how = How.XPATH, using = "//*[@id='wrapper']/nav/ul/li/a")
	protected WebElement dropDownAdministrator;
	
	@FindBy(how = How.XPATH, using = "//*[@id='wrapper']/nav/div[2]/ul/li/a")
	protected WebElement dropDownUser;
	
	@FindBy(how = How.XPATH, using = "//*[@id='wrapper']/nav/div[2]/ul/li/ul/li[6]/a")
	protected WebElement linkLogOut;
	
	@FindBy(how = How.XPATH, using = "//a[@data-target='#picarro-administration-menu']")
	protected WebElement linkPicarroAdmin;
	protected String strLinkPicarroAdminXPath = "//*[@id='picarro-administration-menu']/a";
	
	@FindBy(how = How.XPATH, using = "//*[@data-target='#customer-administration-menu']")
	protected WebElement linkCusAdmin;
	protected String strLinkCusAdminXPath = "//*[@id='customer-administration-menu']/a";	
	
	@FindBy(name = "datatable_length")
	protected WebElement paginationInput;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable_filter']/label/input")
	protected WebElement inputSearch;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody")
	protected WebElement table;
	protected String strTRXPath = "//*[@id='datatable']/tbody/tr";
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable_next']")
	protected WebElement nextBtn;
	
	@FindBy(how = How.XPATH, using = "//*[@id='buttonOk']")
	protected WebElement btnOk;
	
	@FindBy(how = How.XPATH, using = "//*[@id='page-wrapper']/div/div[2]/div[1]")
	protected WebElement panelDuplicationError;
	protected String panelDuplicationErrorXPath = "//*[@id='page-wrapper']/div/div[2]/div[1]";
	
	@FindBy(how = How.XPATH, using = "//*[@id='customer-administration-manage-users']/a")
	protected WebElement linkAdminManageUsers;

	@FindBy(how = How.XPATH, using = "//*[@id='myModalLabel']")
	protected WebElement popupConfirmationBox;
	protected String popupConfirmationBoxXPath = "//*[@id='myModalLabel']";
	
	@FindBy(how = How.XPATH, using = "//*[@id='myModal']/div/div/div[3]/a[1]")
	protected WebElement btnDelete;
	protected String btnDeleteXPath = "//*[@id='myModal']/div/div/div[3]/a[1]";
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable_info']")
	protected WebElement labelPageTableInfo;
	
	@FindBy(how = How.XPATH, using = "//*[@id='datatable']/tbody/tr/td")
	protected WebElement labelNoMatchingSearch;
	
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
		PageFactory.initElements(driver,  loginPage);
		return loginPage;
	}
	
	public void login(String user, String password) {
		LoginPage loginPage = new LoginPage(driver, strBaseURL, testSetup);
		PageFactory.initElements(driver,  loginPage);
		
		loginPage.open();
		
		loginPage.loginNormalAs(user, password);
	}
	
	public void setPagination(String str) {
		List<WebElement> options = this.paginationInput.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if(str.equals(option.getText().trim()))
				option.click();		
		}
	}
	
	public WebElement getLinkPicarroAdmin() {
		return this.linkPicarroAdmin;
	}
	
	public WebElement getLinkCusAdmin() {
		return this.linkCusAdmin;
	}
	
	public WebElement getLinkAdminManageUsers() {
		return this.linkAdminManageUsers;
	}

	public WebElement getLabelPageTableInfo() {
		return this.labelPageTableInfo;
	}
	
	public WebElement getInputSearch() {
		return this.inputSearch;
	}

	public String getLabelNoMatchingSearch() {
		return this.labelNoMatchingSearch.getText().trim();
	}

	/*
	 * Helper method to wait for an Element to be ready on the page. 
	 */
	public void WaitForElementReady(String elementID) {
		(new WebDriverWait(this.driver, this.timeout))
		  .until(ExpectedConditions.presenceOfElementLocated
				  (By.id(elementID)));
	}
}