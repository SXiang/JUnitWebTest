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

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
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

	public static void executeScript(WebElement element, WebDriver driver, String jsScript) {
		Log.method("WebElementExtender.executeScript", element, driver, jsScript);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try{
			js.executeScript(jsScript, element);
		}catch(Exception e){
			Log.warn("Failed to click on the WebElement: "+e.toString());
		}
	}

	public static void printAllElementAttributes(WebElement element, WebDriver driver) {
		Log.method("WebElementExtender.printAllElementAttributes", element, driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Object output = js.executeScript("var items={}; for(index=0;index<arguments[0].attributes.length;++index){items[arguments[0].attributes[index].name]=arguments[0].attributes[index].value};return items;", element);
		Log.info(output.toString());
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
	    		   Log.warn(String.format("Element [%s] is NOT displayed", element));
	    	   }
	       } else {
	    	   Log.warn(String.format("Element [%s] is NULL", element));
	       }
	   } catch (NoSuchElementException e) {
		   Log.warn(String.format("Element [%s] NOT found. Exception - '%s'", element, ExceptionUtility.getStackTraceString(e)));
		   return false;
	   }
	   return false;
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

   public static boolean findElementBy(WebDriver driver, By by) {
	  return findElementBy(driver, by, -1);
   }
   public static boolean findElementBy(WebDriver driver, By by, int timeout) {
	   try {
		   if(timeout > 0){
			   (new WebDriverWait(driver, timeout))
			   .until(ExpectedConditions.presenceOfElementLocated(by));
		   }else{
			   driver.findElement(by);
		   }
		   return true;
	   } catch (Exception e) {
		   return false;
	   }
   }

   public static WebElement findElementIfExists(WebDriver driver, String elementId) {
	   WebElement element = null;
	   try {
		   element = driver.findElement(By.id(elementId));
	   } catch (org.openqa.selenium.NoSuchElementException e) {
		   Log.warn(String.format("Element with ID='%s' was NOT found", elementId));;
	   }
	   return element;
   }

   public static String getInnerHtml(WebElement element) {
	   return element.getAttribute("innerHTML");
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

	public static boolean checkElementsListContains(WebDriver driver, String listElementXPath, List<String> entriesToFind) {
		Hashtable<String, Boolean> listMap = new Hashtable<String, Boolean>();
		List<WebElement> listElements = driver.findElements(By.xpath(listElementXPath));
		for (WebElement element : listElements) {
			String autoText = element.getText();
			if (!listMap.containsKey(autoText)) {
				listMap.put(autoText, true);
			}
		}

		for (String entry : entriesToFind) {
			if (!listMap.containsKey(entry)) {
				return false;
			}
		}

		return true;
	}

	public static void waitForPageLoad(final String pageText, final Integer timeout, WebDriver webDriver) {
		(new WebDriverWait(webDriver, timeout)).until(new ExpectedCondition<Boolean>() {
		    public Boolean apply(WebDriver d) {
		        return d.getPageSource().contains(pageText);
		    }
		});
	}

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

   public static void sendKeys(WebElement selectByNameTextField, String boundaryName) {
	try{
		setFocusOnElement(selectByNameTextField);
		selectByNameTextField.clear();
	}catch(Exception e){
		Log.warn(String.format("Exception with sending key ? '%s', %s", boundaryName, e.toString()));
	}finally{
		selectByNameTextField.sendKeys(boundaryName);
	}
   }
}