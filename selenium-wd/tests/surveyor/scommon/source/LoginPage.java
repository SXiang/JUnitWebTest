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
public class LoginPage extends BasePage {
	public static final String STRURLPath = "/Account/Login";
	public static final String STRPageTitle = "Login";		

	@FindBy(how = How.ID, using = "Username")
	private WebElement tbUserName;

	@FindBy(how = How.ID, using = "Password")
	private WebElement tbPassword;

	@FindBy(how = How.CSS, using = "[type='submit']")
	private WebElement btnLogin;
	
	@FindBy(how = How.CSS, using = "[type='submit']")
	private WebElement btnAccept;
	
	/**
	 * @param driver
	 * @param testSetup
	 * @param strBaseURL
	 * @param strPageURL
	 */
	public LoginPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		super(driver, testSetup, baseURL, baseURL + STRURLPath);
		System.out.println("\nThe Login Page URL is: " + this.strPageURL);
	}
	 
	public HomePage loginNormalAs(String userName, String password)  {
		this.tbUserName.sendKeys(userName);
		this.tbPassword.sendKeys(password);
		this.btnLogin.click();

		waitForPageToLoad();
		if (driver.getCurrentUrl().contains("Eula") && driver.getTitle().contains("Eula")) {
			btnAccept.click();
		}	
		
		HomePage homePage = new HomePage(this.driver, this.strBaseURL, this.testSetup);
		PageFactory.initElements(driver,  homePage);
		long startTime = System.currentTimeMillis();
		long elapsedTime = 0;
		
		while (true) {
			if (homePage.checkIfAtHomePage()){
				return homePage;
			}else if (driver.getCurrentUrl().equalsIgnoreCase(this.strPageURL) && driver.getTitle().equalsIgnoreCase(LoginPage.STRPageTitle)){
				return null;
			}
			elapsedTime = System.currentTimeMillis() - startTime;
			if (elapsedTime >= (30 * 1000)) {
				return null;
			} 
		}
	}
}