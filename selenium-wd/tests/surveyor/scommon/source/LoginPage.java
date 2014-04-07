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

	public LoginPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		
		super(driver, testSetup, baseURL, baseURL + STRURLPath);
		
		System.out.println("\nThe Login Page URL is: " + this.strPageURL);
	}
	
	//Login from Login URL 
	public HomePage loginNormalAs(String userName, String password) {
		
		tbUserName.sendKeys(userName);
		tbPassword.sendKeys(password);
		btnLogin.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);
			
		HomePage homePage = new HomePage(this.driver, this.strBaseURL, this.testSetup);
		PageFactory.initElements(driver, homePage);
			
		return homePage;
	}
}