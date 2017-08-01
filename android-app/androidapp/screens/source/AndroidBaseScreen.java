package androidapp.screens.source;

import common.source.Log;
import common.source.MobileActions.KeyCode;
import common.source.SikuliDecoratedDriver;
import common.source.MobileActions;
import common.source.Timeout;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Predicate;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.sikuli.api.Screen;

import common.source.ExceptionUtility;

public class AndroidBaseScreen implements Screen {
	private Dimension size;
	protected WebDriver driver;

	protected ScreenVerifier screenVerifier;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/content\")")
	protected WebElement mainFrameLayout;

	protected Predicate<WebDriver> screenLoadPredicate = d -> screenLoadCondition();
	protected Predicate<WebDriver> screenAndDataLoadPredicate = d -> screenAndDataLoadCondition();

	public AndroidBaseScreen(WebDriver driver) {
		this.driver = driver;
		this.size = getAndroidDriver().manage().window().getSize();
		this.screenVerifier = ScreenVerifier.getInstance();
	}

	@SuppressWarnings("rawtypes")
	public AndroidDriver getAndroidDriver() {
		return (AndroidDriver)driver;
	}

	public SikuliDecoratedDriver getSikuliDriver() {
		return SikuliDecoratedDriver.getInstance(getAndroidDriver(), this);
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

	public void pressKey(KeyCode keyCode) throws Exception {
		MobileActions.newAction((MobileDriver<?>)driver).pressKey(keyCode);
	}

	public void sendKeys(WebElement element, String text) throws Exception {
		MobileActions.newAction().sendKeys(element, text);
	}

	public void slideBy(WebElement element, WebElement elementContainer, Float value) {
		MobileActions.newAction((MobileDriver<?>)driver).slideBy(element, elementContainer, value);
	}

	public void waitForElementToBeClickable(By byXPath) {
		(new WebDriverWait(driver, Timeout.ANDROID_APP_SCREEN_ELEMENT_CHANGE_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(byXPath));
	}

	public boolean waitForScreenLoad() {
		Log.method("waitForScreenLoad");
		return waitForScreenLoad(driver, getScreenLoadTimeout(), screenLoadPredicate);
	}

	public boolean waitForScreenAndDataLoad() {
		Log.method("waitForScreenAndDataLoad");
		return waitForScreenLoad(driver, getScreenLoadTimeout(), screenAndDataLoadPredicate);
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

	protected Integer getScreenLoadTimeout() {
		return Timeout.ANDROID_APP_SCREEN_LOAD_TIMEOUT;
	}

	/* Methods to be implemented by derived class. */
	protected Boolean screenLoadCondition() {
		return false;
	}

	protected Boolean screenAndDataLoadCondition() {
		return false;
	}

	/* Sikuli screen overrides */

	@Override
	public BufferedImage getScreenshot(int x, int y, int width, int height) {
		File screenshotFile = getAndroidDriver().getScreenshotAs(OutputType.FILE);
		try {
			BufferedImage fullImg = ImageIO.read(screenshotFile);
			BufferedImage croppedImg = crop(fullImg, x, y, width, height);
			size = new Dimension(fullImg.getWidth(), fullImg.getHeight());
			return croppedImg;
		} catch (IOException ex) {
			Log.error(String.format("Error while taking Sikuli screenshot. Exception -> %s", ExceptionUtility.getStackTraceString(ex)));
		}

		return null;
	}

	@Override
	public java.awt.Dimension getSize() {
		java.awt.Dimension dimension = new java.awt.Dimension();
		dimension.setSize(size.getWidth(), size.getHeight());
		return dimension;
	}

    private BufferedImage crop(BufferedImage src, int x, int y, int width, int height) {
        BufferedImage outImg = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics graphics = outImg.getGraphics();
        graphics.drawImage(src, 0, 0, width, height, x, y, x + width, y + height, null);
        graphics.dispose();
        return outImg;
    }
}