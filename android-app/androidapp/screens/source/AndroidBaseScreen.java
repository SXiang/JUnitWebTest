package androidapp.screens.source;

import java.util.function.Predicate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.Log;
import common.source.MobileActions;
import common.source.MobileActions.KeyCode;
import common.source.Timeout;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidBaseScreen {

	protected WebDriver driver;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/content\")")
	protected WebElement mainFrameLayout;

	protected Predicate<WebDriver> screenLoadPredicate = d -> screenLoadCondition();

	public AndroidBaseScreen(WebDriver driver) {
		this.driver = driver;
	}

	@SuppressWarnings("rawtypes")
	public AndroidDriver getAndroidDriver() {
		return (AndroidDriver)driver;
	}

	protected void printPageSource() {
		Log.method("printPageSource");
		Log.info(driver.getPageSource());
	}

	public void tap(WebElement element) {
		MobileActions.newAction((MobileDriver<?>)driver).tap(element);
	}

	public void press(WebElement element) {
		MobileActions.newAction((MobileDriver<?>)driver).press(element);
	}

	public void clickAndPressKey(WebElement element, KeyCode keyCode) throws Exception {
		MobileActions.newAction((MobileDriver<?>)driver).clickAndPressKey(element, keyCode);
	}

	public void sendKeys(WebElement element, String text) throws Exception {
		MobileActions.newAction().sendKeys(element, text);
	}

	public void slideBy(WebElement element, WebElement elementContainer, Float value) {
		MobileActions.newAction((MobileDriver<?>)driver).slideBy(element, elementContainer, value);
	}

	public boolean waitForScreenLoad() {
		Log.method("waitForScreenLoad");
		return waitForScreenLoad(driver, Timeout.ANDROID_APP_SCREEN_LOAD_TIMEOUT, screenLoadPredicate);
	}

	protected boolean waitForScreenLoad(Integer timeout, Predicate<WebDriver> waitPredicate) {
		return waitForScreenLoad(this.driver, timeout, waitPredicate);
	}

	protected boolean waitForScreenLoad(WebDriver drv, Integer timeout, Predicate<WebDriver> waitPredicate) {
		Log.method("waitForScreenLoad", drv, timeout, waitPredicate);
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				boolean predicateTest = false;
				try {
					predicateTest = waitPredicate.test(drv);
				} catch (Exception ex) {
					// Ignore errors.
				}
				return predicateTest;
			}
		});

		return true;
	}

	/* Methods to be implemented by derived class. */
	protected Boolean screenLoadCondition() {
		return false;
	}
}
