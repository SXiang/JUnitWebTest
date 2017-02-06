package common.source;

import java.util.function.Predicate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementPredicates {
	public static Predicate<WebElement> getClickPredicate(String elementName) {
		Predicate<WebElement> predicate = el -> {
			Log.info(String.format("Clicking on '%s'", elementName));
			el.click();
			Log.info(String.format("DONE clicking on '%s'", elementName));
			return true;
		};

		return predicate;
	}

	public static Predicate<WebElement> getWaitForElementTextReadyPredicate(WebDriver driver, Integer waitTimeout, String elementName, String elementReadyText) {
		return getWaitForElementTextReadyPredicateInternal(driver, waitTimeout, elementName, elementReadyText);
	}

	public static Predicate<WebElement> getWaitForElementReadyPredicate(WebElement parentElement, WebDriver driver, Integer timeout, Predicate<WebElement> expectedCondition) {
		return getWaitForElementReadyPredicateInternal(parentElement, driver, timeout, expectedCondition);
	}

	private static Predicate<WebElement> getWaitForElementTextReadyPredicateInternal(WebDriver driver, Integer waitTimeout, String elementName, String elementReadyText) {
		Predicate<WebElement> waitForElementTextReadyPredicate = el -> {
			Predicate<WebElement> elementReadyPredicate = getWaitForElementReadyPredicate(el, driver, waitTimeout, pEl ->
				{
					// use tryFetch to handle exceptions in case element is not available in DOM.
					Log.info("Try fetching element text...");
					String elementText = WebElementFunctionUtil.tryFetchValue(el, e -> e.getText());
					Log.info(String.format("Fetched element text is - [%s]", elementText));
					return (elementText != null) && elementText.equals(elementReadyText);
				});
			Log.info(String.format("Waiting for '%s' element text to become '%s'", elementName, elementReadyText));
			elementReadyPredicate.test(el);
			Log.info(String.format("'%s' element text is now '%s'", elementName, elementReadyText));
			return true;
		};

		return waitForElementTextReadyPredicate;
	}

	private static Predicate<WebElement> getWaitForElementReadyPredicateInternal(WebElement parentElement, WebDriver driver, Integer timeout, Predicate<WebElement> expectedCondition) {
		Predicate<WebElement> predicate = el -> {
			(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return expectedCondition.test(parentElement);
				}
			});
			return true;
		};
		return predicate;
	}
}
