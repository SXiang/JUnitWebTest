/**
 * 
 */
package common.source;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import surveyor.scommon.source.LoginPage;

/**
 * @author zlu
 * 
 *         Add more general code later for pages
 * 
 */
public class BasePage {
	protected String strBaseURL;
	protected String strPageURL;
	protected WebDriver driver;
	protected TestSetup testSetup;
	
	@FindBy(how = How.XPATH, using = "//*[@id='wrapper']/nav/ul/li/a")
	private WebElement dropDownAdministrator;
	
	@FindBy(how = How.XPATH, using = "//*[@id='wrapper']/nav/ul/li/a")
	private WebElement dropDownUser;
	
	//@FindBy(how = How.XPATH, using = "//a[contains(text(),'Log Out')]")
	@FindBy(how = How.XPATH, using = "//*[@id='wrapper']/nav/ul/li/ul/li[6]/a")
	private WebElement linkLogOut;	

	public BasePage(WebDriver driver, TestSetup testSetup, String strBaseURL, String strPageURL) {
		this.driver = driver;
		this.testSetup = testSetup;
		this.strBaseURL = strBaseURL;
		this.strPageURL = strPageURL;
	}
	
	public LoginPage logout() {
		this.dropDownUser.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		this.linkLogOut.click();
		
		if (this.testSetup.isRunningDebug())
			this.testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		LoginPage loginPage = new LoginPage(this.driver, this.strBaseURL, this.testSetup);
		
		return loginPage;
	}	

	public void open() {	
		driver.get(strPageURL);
	}

	public String getStrPageURL() {
		return this.strPageURL;
	}
	
	public boolean isElementPresent(String strXPath) {
		try {
			this.driver.findElement(By.xpath(strXPath));
			return true;
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
	//more generic code will be followed
}