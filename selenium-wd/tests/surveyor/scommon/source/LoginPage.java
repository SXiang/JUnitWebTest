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
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.dataaccess.source.User;

/**
 * @author zlu
 *
 */
public class LoginPage extends BasePage {
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

	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public LoginPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);
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

	public HomePage loginNormalAs(String userName, String password) {
		login(userName, password);

		waitForPageToLoad();
		handleEULA();

		waitForPageToLoad();
		HomePage homePage = new HomePage(this.driver, this.strBaseURL,
				this.testSetup);
		PageFactory.initElements(driver, homePage);
		long startTime = System.currentTimeMillis();
		long elapsedTime = 0;

		while (true) {
			if (homePage.checkIfAtHomePage()) {
				// Set LoggedInUser/Pwd and Culture to context.
				TestContext.INSTANCE.setLoggedInUser(userName);
				TestContext.INSTANCE.setLoggedInPassword(password);
				User loggedInUser = User.getUser(userName);
				if (loggedInUser != null) {
					TestContext.INSTANCE.setUserCulture(loggedInUser.getCultureId());
				}
				return homePage;
			} else if (driver.getCurrentUrl().equalsIgnoreCase(this.strPageURL)
					&& driver.getTitle().equalsIgnoreCase(
							LoginPage.STRPageTitle)) {
				return null;
			}

			elapsedTime = System.currentTimeMillis() - startTime;
			if (elapsedTime >= (30 * 1000)) {
				return null;
			}
		}
	}

	public void login(String userName, String password) {
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