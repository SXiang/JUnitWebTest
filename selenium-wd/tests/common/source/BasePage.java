/**
 * 
 */
package common.source;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


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

	public BasePage(WebDriver driver, TestSetup testSetup, String strBaseURL, String strPageURL) {
		this.driver = driver;
		this.testSetup = testSetup;
		this.strBaseURL = strBaseURL;
		this.strPageURL = strPageURL;
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