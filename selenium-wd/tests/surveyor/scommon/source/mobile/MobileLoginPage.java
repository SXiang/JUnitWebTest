/**
 * 
 */
package surveyor.scommon.source.mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
import surveyor.scommon.source.EULAPage;

/**
 * @author sxiang
 *
 */
public class MobileLoginPage extends MobileBasePage {
	public static final String STRURLPath = "/Account/Login";
	public static final String STRPageTitle = "Login";
	public String strDisplayedMsg = "Your account is inactive. Please contact your administrator to re-enable your account.";

	@FindBy(how = How.ID, using = "Username")
	private WebElement tbUserName;

	@FindBy(how = How.ID, using = "Password")
	private WebElement tbPassword;

	@FindBy(how = How.CSS, using = "[type='submit']")
	private WebElement btnLogin;

	@FindBy(how = How.CSS, using = "[type='submit']")
	private WebElement btnAccept;
	
	@FindBy(how = How.XPATH, using = "//div[@class='validation-summary-errors'/ul/li")
	private WebElement txtAccountDisabled;	

	public MobileLoginPage(){
		super(STRURLPath);
		pageKey = By.cssSelector("[type='submit']");
		Log.info("The Login Page URL is: " + this.strPageURL);
	}
	
	private void handleEULA() {
		if (driver.getCurrentUrl().contains("/Eula")) {
			// If user is redirected to EULA then click on Accept.
			EULAPage eulaPage = new EULAPage(driver, this.strBaseURL, testSetup);
			PageFactory.initElements(driver, eulaPage);
			Log.clickElementInfo("Accept(EULA");
			eulaPage.clickIAcceptButton();
		}
	}

	public boolean checkIfAtLoginPage() {
		if (driver.getTitle().equalsIgnoreCase(STRPageTitle))
			return true;
		
		return false;
	}

	public MobileReportsPage loginNormalAs(String userName, String password) {
		login(userName, password);
		MobileReportsPage reportsPage = new MobileReportsPage();
		handleEULA();	
		reportsPage.waitUntillPageLoad();
		return reportsPage;
	}
	
	private void login(String userName, String password) {
		Log.info("Input username as '"+userName+"'");
		this.tbUserName.sendKeys(userName);
		Log.info("Input password as '<HIDDEN>'");
		this.tbPassword.sendKeys(password);
		Log.clickElementInfo("Login");
		this.btnLogin.click();
	}


	public boolean isAccountDisabled(){
		return this.txtAccountDisabled.getText().equalsIgnoreCase(strDisplayedMsg);
	}
}