package androidapp.screens.source;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import common.source.Log;
import common.source.Timeout;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidMainLoginScreen extends AndroidBaseScreen {
	private static final String BACKPACK_SERVER_EDITVIEW_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.EditText[1]";
	private static final String PCUBED_URL_EDITVIEW_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.EditText[1]";
	private static final String USERNAME_EDITVIEW_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.widget.EditText[1]";
	private static final String CLEAR_BUTTON_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[4]";
	private static final String CANCEL_BUTTON_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[5]";
	private static final String SAVE_BUTTON_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[6]";

	@AndroidFindBy(xpath = BACKPACK_SERVER_EDITVIEW_XPATH)
	private MobileElement bpackServerAddressEditText;

	@AndroidFindBy(xpath = PCUBED_URL_EDITVIEW_XPATH)
	private MobileElement picServerAddressEditText;

	@AndroidFindBy(xpath = USERNAME_EDITVIEW_XPATH)
	private MobileElement usernameEditText;

	@AndroidFindBy(xpath = CANCEL_BUTTON_XPATH)
	private MobileElement cancel;

	@AndroidFindBy(xpath = CLEAR_BUTTON_XPATH)
	private MobileElement clear;

	@AndroidFindBy(xpath = SAVE_BUTTON_XPATH)
	private MobileElement save;

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

		Log.info("Clicking on save ...");
		save.click();
	}

	public void clearSettings() {
		Log.method("clearSettings");
		clear.click();
	}

	public void waitForFirstAppLoad() {
		Log.method("waitForFirstAppLoad");
		waitForScreenLoad(driver, Timeout.ANDROID_APP_FIRST_APP_LOAD_TIMEOUT, screenLoadPredicate);
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("AndroidMainLoginScreen.screenLoadCondition");
		save = (MobileElement) this.driver.findElement(By.xpath(SAVE_BUTTON_XPATH));
		Log.info(String.format("Found save - %s", (save==null)?"NULL":save.toString()));
		return save!=null && save.isDisplayed();
	}
}