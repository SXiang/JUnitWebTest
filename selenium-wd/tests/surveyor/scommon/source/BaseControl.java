package surveyor.scommon.source;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.Log;

public class BaseControl {
	protected WebDriver driver;
	protected int timeout = 15;
	
	public BaseControl(WebDriver driver) {
		this.driver = driver;
	}
	
    /*
	 * Helper method to wait for an Element to be ready on the page. 
	 */
	public void waitForElementReady(String elementID) {
		waitForElementReady(By.id(elementID));
	}
	
	public void waitForElementReady(By elementBy) {
		(new WebDriverWait(this.driver, this.timeout))
		  .until(ExpectedConditions.presenceOfElementLocated(
				  elementBy));
	}
}
