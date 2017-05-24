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

	public void waitForScreenLoad() {
		Log.method("waitForScreenLoad");
		waitForScreenLoad(driver, screenLoadPredicate);
	}

	private void waitForScreenLoad(WebDriver drv, Predicate<WebDriver> waitPredicate) {
		Log.method("waitForScreenLoad", drv, waitPredicate);
		(new WebDriverWait(driver, Timeout.ANDROID_APP_SCREEN_LOAD_TIMEOUT)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return waitPredicate.test(drv);
			}
		});
	}

	/* Methods to be implemented by derived class. */
	protected Boolean screenLoadCondition() {
		return false;
	}
}
