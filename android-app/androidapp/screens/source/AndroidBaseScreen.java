package androidapp.screens.source;

import java.util.function.Predicate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.source.Log;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidBaseScreen {

	protected WebDriver driver;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/content\")")
	protected WebElement mainFrameLayout;

	protected Predicate<WebDriver> screenLoadPredicate = d -> screenLoadCondition();

	public AndroidBaseScreen(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForFirstAppLoad() {
		Log.method("waitForFirstAppLoad");
		waitForScreenLoad(driver, Timeout.ANDROID_APP_FIRST_APP_LOAD_TIMEOUT, screenLoadPredicate);
	}

	public boolean waitForScreenLoad() {
		Log.method("waitForScreenLoad");
		return waitForScreenLoad(driver, Timeout.ANDROID_APP_SCREEN_LOAD_TIMEOUT, screenLoadPredicate);
	}

	private boolean waitForScreenLoad(WebDriver drv, Integer timeout, Predicate<WebDriver> waitPredicate) {
		Log.method("waitForScreenLoad", drv, timeout, waitPredicate);
		(new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return waitPredicate.test(drv);
			}
		});

		return true;
	}

	/* Methods to be implemented by derived class. */
	protected Boolean screenLoadCondition() {
		return false;
	}
}