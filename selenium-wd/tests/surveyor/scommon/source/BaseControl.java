package surveyor.scommon.source;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseControl {
	protected WebDriver driver;
	protected int timeout = 15;
	
	public BaseControl(WebDriver driver) {
		this.driver = driver;
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
