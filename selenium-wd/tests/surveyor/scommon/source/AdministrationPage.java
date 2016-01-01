/**
 * 
 */
package surveyor.scommon.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import common.source.BasePage;
import common.source.Log;
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
	
	//temporary for testing on the following tree nodes 
	@FindBy(how = How.XPATH, using = "/html/body/div[2]/div[2]/div/div/div/ul/li/ul/li/ul/li/ul/li/ul/li/ul/li/a")
	public WebElement nodePicarroManufacturing;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[2]/div[2]/div/div/div/ul/li/ul/li/ul/li/ul/li/ul/li/ul/li/ul/li[3]/a")
	public WebElement nodePSQA01;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[2]/div[2]/div/div/div/ul/li/ul/li/ul/li[2]/ul/li/ul/li/ul/li/ul/li[2]/a")
	public WebElement nodePSQA01_;
	
	@FindBy(how = How.XPATH, using = "/html/body/div[2]/div[2]/div/div/div/ul/li/ul/li/ul/li[2]/ul/li/ul/li/ul/li/a")
	public WebElement nodePGEUnit1;

	/**
	 * @param driver
	 * @param pageTitle
	 */
	public AdministrationPage(WebDriver driver, String baseURL, TestSetup testSetup) {
		
		super(driver, testSetup, baseURL, baseURL + STRURLPath);
		
		Log.info("The Administration Page URL is: " + this.strPageURL);
	}
	
	public LoginPage logout() {
		
		this.btnLogout.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(3);
		
		LoginPage loginPage = new LoginPage(this.driver, this.strBaseURL, this.testSetup);
		
		return loginPage;
	}
	
	public boolean dragAndDropNode(WebElement source, WebElement target) {
		
		Actions action = new Actions(driver);
		
		try {
			
			action.dragAndDrop(source, target).perform();
			
		} catch (Exception e) {
			
			return false;
			
		}
		
		return true;
		
	}
}