package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.CacheLookup;

import common.source.BaselineImages;
import common.source.Log;
import common.source.Timeout;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidMainLoginScreen extends AndroidBaseScreen {
	private static final String CANCEL_BUTTON_UISELECTOR = "new UiSelector().text(\"Cancel\")";
	private static final String CLEAR_BUTTON_UISELECTOR = "new UiSelector().text(\"Clear\")";
	private static final String SAVE_BUTTON_UISELECTOR = "new UiSelector().text(\"Save\")";

	private static final String BACKPACK_SERVER_EDITVIEW_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.EditText[1]";
	private static final String PCUBED_URL_EDITVIEW_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.EditText[1]";
	private static final String USERNAME_EDITVIEW_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.widget.EditText[1]";

	private static final String BACKPACK_SERVER_VALIDATION_LABEL_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.TextView[3]";
	private static final String PCUBED_URL_VALIDATION_LABEL_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.TextView[5]";
	private static final String USERNAME_VALIDATION_LABEL_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.TextView[7]";

	@AndroidFindBy(xpath = BACKPACK_SERVER_EDITVIEW_XPATH)
	private MobileElement bpackServerAddressEditText;

	@AndroidFindBy(xpath = PCUBED_URL_EDITVIEW_XPATH)
	private MobileElement picServerAddressEditText;

	@AndroidFindBy(xpath = USERNAME_EDITVIEW_XPATH)
	private MobileElement usernameEditText;

	@AndroidFindBy(xpath = BACKPACK_SERVER_VALIDATION_LABEL_XPATH)
	private MobileElement bpackServerAddressValidationText;

	@AndroidFindBy(xpath = PCUBED_URL_VALIDATION_LABEL_XPATH)
	private MobileElement picServerAddressValidationText;

	@AndroidFindBy(xpath = USERNAME_VALIDATION_LABEL_XPATH)
	private MobileElement usernameValidationText;

	@AndroidFindBy(uiAutomator = CANCEL_BUTTON_UISELECTOR)
	@CacheLookup
	private MobileElement cancel;

	@AndroidFindBy(uiAutomator = CLEAR_BUTTON_UISELECTOR)
	@CacheLookup
	private MobileElement clear;

	@AndroidFindBy(uiAutomator = SAVE_BUTTON_UISELECTOR)
	@CacheLookup
	private MobileElement save;

	public AndroidMainLoginScreen(WebDriver driver) {
		super(driver);
	}

	public void assertMissingHttpOrHttpsErrorIsShownInRed() {
		Log.method("assertMissingHttpOrHttpsErrorIsShownInRed");
		screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.COMMON, BaselineImages.ImageFile.ErrorMissingHttpOrHttps);
	}

	public void assertUrlMustStartWithHttpOrHttpsErrorIsShownInRed() {
		Log.method("assertUrlMustStartWithHttpOrHttpsErrorIsShownInRed");
		screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.COMMON, BaselineImages.ImageFile.ErrorUrlMustStartWithHttpOrHttps);
	}

	public void assertSpecifyPortNumberErrorIsShownInRed() {
		Log.method("assertSpecifyPortNumberErrorIsShownInRed");
		screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.COMMON, BaselineImages.ImageFile.ErrorSpecifyPortNumber);
	}

	public void saveSettings(String backpackAddress, String picServerAddress, String username) throws Exception {
		Log.method("saveSettings", backpackAddress, picServerAddress, username);
		enterBackPackServerAddress(backpackAddress);
		enterPicarroServerUrl(picServerAddress);
		enterPicarroServerUsername(username);
		hideKeyboard();
		clickOnSaveButton();
	}

	public void clearAndSaveSettings(String backpackAddress, String picServerAddress, String username) throws Exception {
		Log.method("saveSettings", backpackAddress, picServerAddress, username);
		if (!getBackpackServerText().equals(backpackAddress)) {
			clearBackPackServerAddress();
			enterBackPackServerAddress(backpackAddress);
		}
		if (!getPicarroServerAddressText().equals(picServerAddress)) {
			clearPicarroServerUrl();
			enterPicarroServerUrl(picServerAddress);
		}
		if (!getUsernameText().equals(username)) {
			clearPicarroServerUsername();
			enterPicarroServerUsername(username);
		}

		hideKeyboard();
		clickOnSaveButton();
	}

	public void clickOnSaveButton() {
		Log.info("Clicking on save ...");
		save.click();
	}

	public void clearBackPackServerAddress() throws Exception {
		Log.method("clearBackPackServerAddress");
		getBpackServerAddressEditText().clear();
	}

	public void clearPicarroServerUrl() throws Exception {
		Log.method("clearPicarroServerUrl");
		picServerAddressEditText.clear();
	}

	public void clearPicarroServerUsername() throws Exception {
		Log.method("clearPicarroServerUsername");
		usernameEditText.clear();
	}

	public void clearSettings() {
		Log.method("clearSettings");
		clear.click();
	}

	public void enterBackPackServerAddress(String backpackServerAddress) throws Exception {
		Log.method("enterPicarroServerUsername", backpackServerAddress);
		sendKeys(getBpackServerAddressEditText(), backpackServerAddress);
	}

	public void enterPicarroServerUrl(String picServerUrl) throws Exception {
		Log.method("enterPicarroServerUsername", picServerUrl);
		sendKeys(picServerAddressEditText, picServerUrl);
	}

	public void enterPicarroServerUsername(String username) throws Exception {
		Log.method("enterPicarroServerUsername", username);
		sendKeys(usernameEditText, username);
	}

	public String getBackpackServerText() {
		return bpackServerAddressEditText.getText();
	}

	public String getPicarroServerAddressText() {
		return picServerAddressEditText.getText();
	}

	public String getUsernameText() {
		return usernameEditText.getText();
	}

	public MobileElement getBackpackServerAddressValidationText() {
		return bpackServerAddressValidationText;
	}

	public MobileElement getPicarroServerAddressValidationText() {
		return picServerAddressValidationText;
	}

	public MobileElement getUsernameValidationText() {
		return usernameValidationText;
	}

	public void waitForFirstAppLoad() {
		Log.method("waitForFirstAppLoad");
		waitForScreenLoad(driver, Timeout.ANDROID_APP_FIRST_APP_LOAD_TIMEOUT, screenLoadPredicate);
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("AndroidMainLoginScreen.screenLoadCondition");
		save = (MobileElement) this.getAndroidDriver().findElementByAndroidUIAutomator(SAVE_BUTTON_UISELECTOR);
		Log.info(String.format("Found save - %s", (save==null)?"NULL":save.toString()));
		return save!=null && save.isDisplayed();
	}

	public MobileElement getBpackServerAddressEditText() {
		return bpackServerAddressEditText;
	}

	public void setBpackServerAddressEditText(MobileElement bpackServerAddressEditText) {
		this.bpackServerAddressEditText = bpackServerAddressEditText;
	}
}