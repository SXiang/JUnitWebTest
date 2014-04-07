/**
 * 
 */
package surveyor.scommon.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.source.BasePage;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class AdministrationPage extends BasePage {
	
	public static final String STRURLPath = "/PicarroAdministration";
	public static final String STRPageTitle = "Index - Surveyor";	
	
	@FindBy(how = How.CSS, using = "[href='/Account/Logout']")
	private WebElement btnLogout;
	
	@FindBy(how = How.CSS, using = "[href='/PicarroAdministration']")
	private WebElement btnAdministration;

	/**
	 * @param driver
	 * @param pageTitle
	 */
	public AdministrationPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		
		super(driver, testSetup, baseURL, baseURL + STRURLPath);
		
		System.out.println("The Administration Page URL is: " + this.strPageURL);
	}
	
	public LoginPage logout() {
		
		this.btnLogout.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);
		
		LoginPage loginPage = new LoginPage(this.driver, this.strBaseURL, this.testSetup);
		
		return loginPage;
	}
}