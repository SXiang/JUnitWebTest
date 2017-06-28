package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import common.source.AccessibilityLabel;
import common.source.Log;
import common.source.TestContext;
import common.source.Timeout;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidMapScreen extends AndroidBaseScreen {

	private static final String MODE_HR = "HR";
	private static final String AMPLITUDE_LABEL = "Max:";

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[3]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement toggleModeButton;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[3]/android.view.ViewGroup[2]")
	@CacheLookup
	private WebElement resetButton;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[3]/android.view.ViewGroup[3]")
	@CacheLookup
	private WebElement investigateButton;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.widget.TextView[1]")
	@CacheLookup
	private WebElement amplitudeText;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.widget.TextView[2]")
	@CacheLookup
	private WebElement maxText;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.widget.TextView[4]")
	@CacheLookup
	private WebElement ppmText;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView")
	@CacheLookup
	private WebElement modeText;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[3]/android.view.ViewGroup[3]/android.widget.EditText")
	@CacheLookup
	private WebElement passwordEditView;
	private Boolean passwordEditViewLocated = true;    // element fetched at page load time. Set to false to detect element post page load.

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[3]/android.view.ViewGroup[5]")
	@CacheLookup
	private WebElement submitButton;
	private Boolean submitButtonLocated = true;        // element fetched at page load time. Set to false to detect element post page load.

	// Following user login elements are currently not used in normal test interaction and therefore NOT fetched at page load time for perf reason.
	// If tests need to interact with these elements, fetch these using @AndroidFindBy.
	private WebElement serverEditView = null;
	private Boolean serverEditViewLocated = false;
	private WebElement usernameEditView = null;
	private Boolean usernameEditViewLocated = false;
	private WebElement cancelButton = null;
	private Boolean cancelButtonLocated = false;

	public AndroidMapScreen(WebDriver driver) {
		super(driver);
	}

	public void clickOnSubmit() {
		Log.method("clickOnSubmit");
		tap(getSubmitButton());
	}

	public void clickOnCancel() {
		Log.method("clickOnCancel");
		getCancelButton().click();
	}

	public void clickOnToggleMode() {
		Log.method("clickOnToggleMode");
		getToggleModeButton().click();
	}

	public void clickOnReset() {
		Log.method("clickOnReset");
		getResetButton().click();
	}

	public void clickOnInvestigate() {
		Log.method("clickOnInvestigate");
		getInvestigateButton().click();
		TestContext.INSTANCE.stayIdle(2);
	}

	public void enterServerUrl(String serverUrl) {
		Log.method("enterServerUrl");
		getServerEditView().sendKeys(serverUrl);
	}

	public void enterUsername(String username) {
		Log.method("enterUsername");
		getUsernameEditView().sendKeys(username);
	}

	public void enterPassword(String password) throws Exception {
		Log.method("enterPassword", password);
		sendKeys(getPasswordEditView(), password);
	}

	public void waitForLoginDialogToShow() {
		waitForScreenLoad(Timeout.ANDROID_APP_SHOW_MODAL_TIMEOUT, d -> loginDialogLoadCondition());
	}

	private Boolean loginDialogLoadCondition() {
		Log.method("AndroidMapScreen.loginDialogShownCondition");
		boolean passwordTextDisplayed = false;
		try {
			passwordTextDisplayed = getPasswordEditView() != null && getPasswordEditView().isDisplayed();
		} catch (Exception ex) {
			// ignore error.
		}

		Log.info(String.format("Password text displayed=[%b]", passwordTextDisplayed));
		return passwordTextDisplayed;
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("AndroidMapScreen.screenLoadCondition");
		boolean toggleModeButtonDisplayed = toggleModeButton!=null && toggleModeButton.isDisplayed();
		boolean modeTextDisplayed = modeText != null && modeText.getText() != null;
		boolean modeTextValid = false;

		if (modeTextDisplayed) {
			String modeTextValue = getModeText();
			Log.info(String.format("modeTextValue=%s", modeTextValue));
			modeTextValid = modeTextValue.contains(MODE_HR);
		}

		Log.info(String.format("toggleModeButtonDisplayed=[%b], modeTextDisplayed=[%b], modeTextValid=[%b]",
				toggleModeButtonDisplayed, modeTextDisplayed, modeTextValid));
		return toggleModeButtonDisplayed && modeTextDisplayed;
	}

	public WebElement getToggleModeButton() {
		return toggleModeButton;
	}

	public WebElement getToggleModeButtonByAccId() {
		return getAndroidDriver().findElementByAccessibilityId(AccessibilityLabel.MapScreen.TOGGLE_MODE_BTN);
	}

	public WebElement getResetButton() {
		return resetButton;
	}

	public WebElement getResetButtonByAccId() {
		return getAndroidDriver().findElementByAccessibilityId(AccessibilityLabel.MapScreen.RESET_MAX_BTN);
	}

	public WebElement getInvestigateButton() {
		return investigateButton;
	}

	public WebElement getInvestigateButtonByAccId() {
		return getAndroidDriver().findElementByAccessibilityId(AccessibilityLabel.MapScreen.INVESTIGATE_BTN);
	}

	public WebElement getServerEditView() {
		if (!serverEditViewLocated) {
			serverEditView = getAndroidDriver().findElementByXPath("//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.EditText");
			serverEditViewLocated = true;
		}

		return serverEditView;
	}

	public WebElement getServerEditViewByAccId() {
		return getAndroidDriver().findElementByAccessibilityId(AccessibilityLabel.LoginDialog.SERVER_URL_EDIT_VIEW);
	}

	public WebElement getUsernameEditView() {
		if (!usernameEditViewLocated) {
			usernameEditView = getAndroidDriver().findElementByXPath("//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[3]/android.view.ViewGroup[2]/android.widget.EditText");
			usernameEditViewLocated = true;
		}

		return usernameEditView;
	}

	public WebElement getUsernameEditViewByAccId() {
		return getAndroidDriver().findElementByAccessibilityId(AccessibilityLabel.LoginDialog.USERNAME_EDIT_VIEW);
	}

	public WebElement getPasswordEditView() {
		if (!passwordEditViewLocated) {
			passwordEditView = getAndroidDriver().findElementByXPath("//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[3]/android.view.ViewGroup[3]/android.widget.EditText");
			passwordEditViewLocated = true;
		}

		return passwordEditView;
	}

	public WebElement getPasswordEditViewByAccId() {
		return getAndroidDriver().findElementByAccessibilityId(AccessibilityLabel.LoginDialog.PASSWORD_EDIT_VIEW);
	}

	public WebElement getSubmitButton() {
		Log.method("getSubmitButton");
		if (!submitButtonLocated) {
			submitButton = getAndroidDriver().findElement(MobileBy.xpath("//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[3]/android.view.ViewGroup[5]"));
			submitButtonLocated = true;
		}

		return submitButton;
	}

	public WebElement getSubmitButtonByAccId() {
		return getAndroidDriver().findElementByAccessibilityId(AccessibilityLabel.LoginDialog.SUBMIT_BTN);
	}

	public WebElement getCancelButton() {
		if (!cancelButtonLocated) {
			cancelButton = getAndroidDriver().findElementByXPath("//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[3]/android.view.ViewGroup[4]");
			cancelButtonLocated = true;
		}

		return cancelButton;
	}

	public WebElement getCancelButtonByAccId() {
		return getAndroidDriver().findElementByAccessibilityId(AccessibilityLabel.LoginDialog.CANCEL_BTN);
	}

	public String getAmplitudeText() {
		Log.method("getAmplitudeText");
		return amplitudeText.getText();
	}

	public Float getAmplitudeTextFloatValue() {
		Log.method("getAmplitudeTextFloatValue");
		Float ampFloat = Float.MIN_VALUE;
		try {
			if (amplitudeText.getText()!=null) {
				ampFloat = Float.valueOf(amplitudeText.getText().replace(AMPLITUDE_LABEL, ""));
			}
		} catch (Exception e) {/*ignore error*/}

		return ampFloat;
	}

	public String getMaxText() {
		Log.method("getMaxText");
		return maxText.getText();
	}

	public String getPpmText() {
		Log.method("getPpmText");
		return ppmText.getText();
	}

	public String getModeText() {
		Log.method("getModeText");
		return modeText.getText();
	}
}