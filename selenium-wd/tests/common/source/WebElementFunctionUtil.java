package common.source;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Utility class to find web element(s) while handling exceptions that could happen during find.
 *
 * @author spulikkal
 *
 */
public class WebElementFunctionUtil {

	/**
	 * Tries to find element specified by the function and returns the element.
	 * Handles NoSuchElementException & StaleElementReferenceException that could be thrown when fetching the element.
	 * If no element is found, return NULL.
	 *
	 * @param parentElement - parent element from where to find element.
	 * @param findElementFunc - function to find element.
	 * @return
	 */
	public static WebElement tryFindElement(WebElement parentElement, Function<WebElement, Object> findElementFunc) {
		Object element = tryFind(parentElement, findElementFunc);
		return (element != null) ? ((WebElement)element) : null;
	}

	/**
	 * Tries to find elements specified by the function and returns the elements.
	 * Handles NoSuchElementException & StaleElementReferenceException that could be thrown when fetching the element.
	 * If no elements are found, return NULL.
	 *
	 * @param parentElement - parent element from where to find elements.
	 * @param findElementsFunc - function to find elements.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<WebElement> tryFindElements(WebElement parentElement, Function<WebElement, Object> findElementsFunc) {
		Object element = tryFind(parentElement, findElementsFunc);
		return (element != null) ? ((List<WebElement>)element) : null;
	}

	/**
	 * Waits for the specified time for function element to be ready on the page and then return the element.
	 * Handles NoSuchElementException & StaleElementReferenceException that could be thrown when fetching the element.
	 *
	 * @param parentElement - parent element from where to find elements.
	 * @param driver - web driver.
	 * @param timeout - time to wait until timeout exception.
	 * @param findElementFunc - function to find element.
	 * @return
	 */
	public static WebElement waitAndTryFindElement(WebElement parentElement, WebDriver driver, Integer timeout, Function<WebElement, Object> findElementFunc) {
		waitForElementReady(parentElement, driver, timeout, (parentEl) -> tryFindElement(parentEl, findElementFunc) != null);
		return tryFindElement(parentElement, findElementFunc);
	}

	/**
	 * Waits for the specified time for function elements to be ready on the page and then returns the elements.
	 * Handles NoSuchElementException & StaleElementReferenceException that could be thrown when fetching the element.
	 *
	 * @param parentElement - parent element from where to find elements.
	 * @param driver - web driver.
	 * @param timeout - time to wait until timeout exception.
	 * @param findElementsFunc - function to find elements.
	 * @return
	 */
	public static List<WebElement> waitAndTryFindElements(WebElement parentElement, WebDriver driver, Integer timeout, Function<WebElement, Object> findElementsFunc) {
		waitForElementReady(parentElement, driver, timeout, (parentEl) -> tryFindElements(parentEl, findElementsFunc) != null);
		return tryFindElements(parentElement, findElementsFunc);
	}

	private static Object tryFind(WebElement parentElement, Function<WebElement, Object> func) {
		Object element = null;
		try {
			element = func.apply(parentElement);
		} catch (NoSuchElementException e) {
		   Log.warn("Element NOT found. NoSuchElementException encountered.");
		} catch (StaleElementReferenceException e) {
		   Log.warn("Element NOT found. StaleElementReferenceException encountered.");
		}

		return element;
	}

	private static void waitForElementReady(WebElement parentElement, WebDriver driver, Integer timeout, Predicate<WebElement> isElementReady) {
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return isElementReady.test(parentElement);
			}
		});
	}
}