package androidapp.screens.source;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import common.source.Log;
import common.source.Timeout;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidMainLoginScreen extends AndroidBaseScreen {

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText[1]")
	private MobileElement bpackServerAddressEditText;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText[2]")
	private MobileElement picServerAddressEditText;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText[3]")
	private MobileElement usernameEditText;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]")
	private MobileElement clearButton;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]")
	private MobileElement saveButton;

	public AndroidMainLoginScreen(WebDriver driver) {
		super(driver);
	}

	public void saveSettings(String backpackAddress, String picServerAddress, String username) throws Exception {
		Log.method("saveSettings", backpackAddress, picServerAddress, username);
		Log.info("Clicking on bpackServerAddressEditText ...");
		sendKeys(bpackServerAddressEditText, backpackAddress);

		Log.info("Clicking on picServerAddressEditText ...");
		sendKeys(picServerAddressEditText, picServerAddress);

		Log.info("Clicking on usernameEditText ...");
		sendKeys(usernameEditText, username);

		Log.info("Hiding keyboard, before clicking on Save button");
		((AppiumDriver)this.driver).hideKeyboard();

		Log.info("Clicking on saveButton ...");
		saveButton.click();
	}

	public void clearSettings() {
		Log.method("clearSettings");
		clearButton.click();
	}

	public void waitForFirstAppLoad() {
		Log.method("waitForFirstAppLoad");
		waitForScreenLoad(driver, Timeout.ANDROID_APP_FIRST_APP_LOAD_TIMEOUT, screenLoadPredicate);
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("AndroidMainLoginScreen.screenLoadCondition");
		saveButton = (MobileElement) this.driver.findElement(By.xpath("//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]"));
		Log.info(String.format("Found saveButton - %s", (saveButton==null)?"NULL":saveButton.toString()));
		return saveButton!=null && saveButton.isDisplayed();
	}
}