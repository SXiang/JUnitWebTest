/**
 * 
 */
package common.source;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author zlu
 *
 */
public class Util {

	public static boolean isTextPresent(WebDriver driver, String text) {
		
		return driver.getPageSource().contains(text);
		
	}

	public static boolean isElementPresent(WebDriver driver, By by) {
		try {
			
			driver.findElement(by);
			
			return true;
			
		} catch (NoSuchElementException e) {
			
			return false;
			
		}
	}

	public static boolean isElementPresent(WebDriver driver, String _cssSelector) {
		try {
			
			driver.findElement(By.cssSelector(_cssSelector));
			
			return true;
			
		} catch (NoSuchElementException e) {
			
			return false;
			
		}
	}

	public static boolean isElementPresentAndDisplay(WebDriver driver, By by) {
		
		try {
			
			return driver.findElement(by).isDisplayed();
			
		} catch (NoSuchElementException e) {
			
			return false;
			
		}
	}

	public static WebElement getWebElement(WebDriver driver, By by) {
	
		return driver.findElement(by);
		
	}


	//wait or timeout should be improved in the next step
	public static WebElement findElement(WebDriver driver, By by, int timeoutInSeconds) {
		
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		/*
		 * throws a timeout exception if element not present after waiting
		 * <timeoutInSeconds> seconds
		 */
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		
		return driver.findElement(by);
		
	}


	public static boolean isElementPresent(WebDriver driver, By by,
			int timeoutInSeconds) {
	
		try {
		
			for (int i = 0; i < timeoutInSeconds;) {
				if (findElement(driver, by, timeoutInSeconds) != null)
					return true;
				else
					i++;
			}
		} catch (NoSuchElementException e) {
			
			return false;
			
		} catch (TimeoutException e) {
			
			return false;
			
		} catch (Exception e) {
			
			return false;
			
		}
		
		return false;
	}

	public static void switchWindow(WebDriver driver) {
		
		String parentWindow = driver.getWindowHandle();
		
		Set<String> handles = driver.getWindowHandles();
		
		for (String windowHandle : handles) {
			
			if (!windowHandle.equals(parentWindow)) {
				
				driver.switchTo().window(windowHandle);
				
			}
		}
		
	}


	public static String acceptAlert(WebDriver driver) {
		
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		alert.accept();
		
		return alertMessage;
		
	}


	public static String cancelAlert(WebDriver driver) {
		
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		alert.dismiss();
		
		return alertMessage;
		
	}
}