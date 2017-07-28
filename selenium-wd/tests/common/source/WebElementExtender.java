/*
 * WebElement Extension class with helper methods for WebElement.
 * These methods - setAttribute(), highlightElement(), captureElementBitmap() have been used from:
 *     https://github.com/enricjaen/SELENIUM2/blob/master/prueba/books/Selenium%20Testing%20Tools%20Cookbook/Chapter%206/WebElementExtender/WebElementExtender.java
 *
 */

package common.source;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;

public class WebElementExtender {

	/*
    * Captures Screenshot of the element and saves to a file.
    * NOTES: This method is useful for cases where test wants to capture bitmap of image shown in Canvas element for example.
    */
   public static File captureElementBitmap(WebElement element) throws Exception {
		//Get the WrapsDriver of the WebElement
	   	WrapsDriver wrapsDriver = (WrapsDriver) element;

	   	//Get the entire Screenshot from the driver of passed WebElement
	   	File screen = ((TakesScreenshot)  wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);

	   	//Create an instance of Buffered Image from captured screenshot
	   	BufferedImage img = ImageIO.read(screen);

	   	// Get the Width and Height of the WebElement using getSize()
	   	int width = element.getSize().getWidth();
	    int height = element.getSize().getHeight();

	    //Create a rectangle using Width and Height
	    Rectangle rect = new Rectangle(width, height);

	    //Get the Location of WebElement in a Point.
	    //This will provide X & Y co-ordinates of the WebElement
	    Point p = element.getLocation();

	    //Create image by for element using its location and size.
	    //This will give image data specific to the WebElement
	    BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width,
	                                          rect.height);

	    //Write back the image data for element in File object
	    ImageIO.write(dest, "png", screen);

	    //Return the File object containing image data
	    return screen;
   	}

   /**
    * Checks if LI elements in UI are matching the entries specified in list.
    * An LI element is a match if LI element text is contained in any of the entry in specified list.
    * @param driver - web driver instance.
    * @param liElementsXPath - xPath for LI elements in UI.
    * @param entriesToFind - list entries to match.
    * @return - true if match, false otherwise.
    */
	public static boolean checkElementsListContains(WebDriver driver, String liElementsXPath, List<String> entriesToFind) {
		Log.method("checkElementsListContains", driver, liElementsXPath, LogHelper.listToString(entriesToFind));
		List<WebElement> listElements = driver.findElements(By.xpath(liElementsXPath));
		for (WebElement element : listElements) {
			boolean match = entriesToFind.stream()
				.anyMatch(s -> element.getText().contains(s));
			if (!match) {
				return false;
			}
		}

		return true;
	}

	public static Object executeScript(WebElement element, WebDriver driver, String jsScript) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(jsScript, element);
	}

	public static boolean findElementBy(WebDriver driver, By by) {
		return findElementBy(driver, by, -1);
	}

	public static boolean findElementBy(WebDriver driver, By by, int timeout) {
		try {
			if(timeout > 0){
				(new WebDriverWait(driver, timeout))
				.until(ExpectedConditions.presenceOfElementLocated(by));
			} else {
				driver.findElement(by);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static WebElement findElementIfExists(WebDriver driver, String elementId) {
		return findElementIfExists(driver, By.id(elementId));
	}

	public static WebElement findElementIfExists(WebDriver driver, By elementBy) {
		WebElement element = null;
		try {
			element = driver.findElement(elementBy);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			Log.warn(String.format("Element with By='%s' was NOT found: "+e, elementBy));
		}

		return element;
	}

	public static WebElement findParentElement(WebDriver driver, WebElement element) {
		return (WebElement)executeScript(element, driver, "return arguments[0].parentNode;");
	}


	public static String getInnerHtml(WebElement element) {
	   return element.getAttribute("innerHTML");
   	}

	/**
	 * Returns the applied CSS property value for specified CSS property name for a web element.
	 * @param element - Web element for which the CSS property value is to be retrieved.
	 * @param cssPropertyName - name of the CSS property to be retrieved.
	 * @return
	 */
	public static String getCssPropertyValue(WebDriver driver, WebElement element, String cssPropertyName) {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	Object cssPropertyValue = js.executeScript("return window.getComputedStyle(arguments[0], null).getPropertyValue('" + cssPropertyName +  "');",
				element);
		return cssPropertyValue.toString();
	}

	public static void highlightElement(WebElement element) {
	    for (int i = 0; i < 5; i++) {
	    	WrapsDriver wrappedElement = (WrapsDriver) element;
	    	JavascriptExecutor driver = (JavascriptExecutor) wrappedElement.getWrappedDriver();
	    	driver.executeScript("arguments[0].setAttribute('style', arguments[1]);",
	                element, "color: green; border: 2px solid green;");
	    	driver.executeScript("arguments[0].setAttribute('style', arguments[1]);",
	                element, "");
	    }
	}

	public static boolean isAttributePresent(WebElement element, String attributeName)
	{
	   try {
	       String attrValue = element.getAttribute(attributeName);
	       return (attrValue != null);
	   } catch (Exception e) {}
	   return false;
	}

   /**
    * Use this method for elements detected using PageFactory and you want to confirm
    * this element is present on the web page.
    * NOTE: This method might not work as expected if element is detected using driver.FindElement(...)
    */
   public static boolean isElementPresentAndDisplayed(WebElement element)
   {
	   Log.method("isElementPresentAndDisplayed");
	   try {
	       if (element != null) {
	    	   if (element.isDisplayed()) {
	    		   return true;
	    	   } else {
	    		   Log.warn("Element is NOT displayed");
	    	   }
	       } else {
	    	   Log.warn("Element is NULL");
	       }
	   } catch (NoSuchElementException e) {
		   Log.warn("Element NOT found. NoSuchElementException encountered.");
		   return false;
	   } catch (StaleElementReferenceException e) {
		   Log.warn("Element NOT found. StaleElementReferenceException encountered.");
		   return false;
	   }
	   return false;
   }

	public static void printAllElementAttributes(WebElement element, WebDriver driver) {
		Log.method("WebElementExtender.printAllElementAttributes", element, driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Object output = js.executeScript("var items={}; for(index=0;index<arguments[0].attributes.length;++index){items[arguments[0].attributes[index].name]=arguments[0].attributes[index].value};return items;", element);
		Log.info(output.toString());
	}

	public static boolean selectDropdownValue(WebElement element, String value) {
		Log.method("WebElementExtender.selectDropdownValue", element, value);
		if (element.isDisplayed()) {
			List<WebElement> optionsCustomer = element.findElements(By.tagName("option"));
			for (WebElement option : optionsCustomer) {
				if (value.equalsIgnoreCase(option.getText().trim())) {
					Log.info(String.format("Select dropdown item - '%s'", value));
					option.click();
					return true;
				}
			}
		}

		return false;
	}

    public static void sendKeys(WebElement selectByNameTextField, String boundaryName) {
		try {
			setFocusOnElement(selectByNameTextField);
			selectByNameTextField.clear();
		} catch(Exception e) {
			Log.warn(String.format("Exception with sending key ? '%s', %s", boundaryName, e.toString()));
		} finally {
			selectByNameTextField.sendKeys(boundaryName);
		}
   	}

	public static void setAttribute(WebElement element, String attributeName, String value)
	{
       WrapsDriver wrappedElement = (WrapsDriver) element;

       JavascriptExecutor driver = (JavascriptExecutor) wrappedElement.getWrappedDriver();
       driver.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, attributeName, value);
	}

    public static void setElementAttribute(WebElement element, String attributeName, String value)
	{
       JavascriptExecutor driver = (JavascriptExecutor) TestContext.INSTANCE.getDriver();
       driver.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, attributeName, value);
	}

	public static void setFocusOnElement(WebElement element) {
	   new Actions(TestContext.INSTANCE.getDriver()).moveToElement(element).perform();
	}

	/**
	 * Wait for element to be displayed.
	 */
	public static boolean verifyElementIsDisplayed(WebDriver driver, WebElement element, Integer timeout) {
		(new WebDriverWait(driver, timeout + 30)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return WebElementExtender.isElementPresentAndDisplayed(element);
			}
		});
		return true;
	}

	public static WebElement waitForElementToBeClickable(final Integer timeout, WebDriver webDriver, WebElement element) {
		return (new WebDriverWait(webDriver, timeout)).until(
				ExpectedConditions.elementToBeClickable(element));
	}
	
	public static Boolean waitForElementToBeDisplayed(final Integer timeout, WebDriver webDriver, By elementBy) {
		Boolean isDisplayed = Boolean.valueOf(false);
		try{
			isDisplayed = (new WebDriverWait(webDriver, timeout)).until (new ExpectedCondition<Boolean>(){
				WebElement we = null;
				public Boolean apply(WebDriver d) {
					we = d.findElement(elementBy);
					return we.isDisplayed();
				}
			});
		}catch(Exception e){
			Log.warn(e.toString());
		}
		return isDisplayed;
	}

	public static void waitForPageLoad(final String pageText, final Integer timeout, WebDriver webDriver) {
		(new WebDriverWait(webDriver, timeout)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		        return d.getPageSource().contains(pageText);
		    }
		});
	}
}