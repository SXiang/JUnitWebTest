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
	private static final String MODE_HP = "HP";

	private static final String MAX_LABEL = "Max:";

	/******* Label elements *******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.TextView[1]")
	@CacheLookup
	private WebElement modeText;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.TextView[2]")
	@CacheLookup
	private WebElement maxText;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.TextView[3]")
	@CacheLookup
	private WebElement amplitude;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.TextView[4]")
	@CacheLookup
	private WebElement c2h6;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.TextView[5]")
	@CacheLookup
	private WebElement cH4ppm;

	/******* Button elements *******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[3]")
	@CacheLookup
	private WebElement investigate;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[2]")
	@CacheLookup
	private WebElement resetMax;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement toggleMode;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.widget.TextView[3]")
	@CacheLookup
	private WebElement menuButton;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[3]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement passwordEditView;
	private Boolean passwordEditViewLocated = true;    // element fetched at page load time. Set to false to detect element post page load.

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[5]")
	@CacheLookup
	private WebElement submit;
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

	public void clickOnMenuButton() {
		Log.method("clickOnMenuButton");
		getMenuButton().click();
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
		boolean toggleModeButtonDisplayed = toggleMode!=null && toggleMode.isDisplayed();
		boolean modeTextDisplayed = modeText != null && modeText.getText() != null;
		boolean modeTextValid = false;

		if (modeTextDisplayed) {
			String modeTextValue = getModeText();
			Log.info(String.format("modeTextValue=%s", modeTextValue));
			modeTextValid = modeTextValue.contains(MODE_HP);
		}

		Log.info(String.format("toggleModeButtonDisplayed=[%b], modeTextDisplayed=[%b], modeTextValid=[%b]",
				toggleModeButtonDisplayed, modeTextDisplayed, modeTextValid));
		return toggleModeButtonDisplayed && modeTextDisplayed && modeTextValid;
	}

	public WebElement getMenuButton() {
		return menuButton;
	}

	public WebElement getToggleModeButton() {
		return toggleMode;
	}

	public WebElement getToggleModeButtonByAccId() {
		return getAndroidDriver().findElementByAccessibilityId(AccessibilityLabel.MapScreen.TOGGLE_MODE_BTN);
	}

	public WebElement getResetButton() {
		return resetMax;
	}

	public WebElement getResetButtonByAccId() {
		return getAndroidDriver().findElementByAccessibilityId(AccessibilityLabel.MapScreen.RESET_MAX_BTN);
	}

	public WebElement getInvestigateButton() {
		return investigate;
	}

	public WebElement getInvestigateButtonByAccId() {
		return getAndroidDriver().findElementByAccessibilityId(AccessibilityLabel.MapScreen.INVESTIGATE_BTN);
	}

	public WebElement getServerEditView() {
		if (!serverEditViewLocated) {
			serverEditView = getAndroidDriver().findElementByXPath("//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.EditText[1]");
			serverEditViewLocated = true;
		}

		return serverEditView;
	}

	public WebElement getServerEditViewByAccId() {
		return getAndroidDriver().findElementByAccessibilityId(AccessibilityLabel.LoginDialog.SERVER_URL_EDIT_VIEW);
	}

	public WebElement getUsernameEditView() {
		if (!usernameEditViewLocated) {
			usernameEditView = getAndroidDriver().findElementByXPath("//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[2]/android.widget.EditText[1]");
			usernameEditViewLocated = true;
		}

		return usernameEditView;
	}

	public WebElement getUsernameEditViewByAccId() {
		return getAndroidDriver().findElementByAccessibilityId(AccessibilityLabel.LoginDialog.USERNAME_EDIT_VIEW);
	}

	public WebElement getPasswordEditView() {
		if (!passwordEditViewLocated) {
			passwordEditView = getAndroidDriver().findElementByXPath("//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[3]/android.widget.EditText[1]");
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
			submit = getAndroidDriver().findElement(MobileBy.xpath("//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[5]"));
			submitButtonLocated = true;
		}

		return submit;
	}

	public WebElement getSubmitButtonByAccId() {
		return getAndroidDriver().findElementByAccessibilityId(AccessibilityLabel.LoginDialog.SUBMIT_BTN);
	}

	public WebElement getCancelButton() {
		if (!cancelButtonLocated) {
			cancelButton = getAndroidDriver().findElementByXPath("//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[4]");
			cancelButtonLocated = true;
		}

		return cancelButton;
	}

	public WebElement getCancelButtonByAccId() {
		return getAndroidDriver().findElementByAccessibilityId(AccessibilityLabel.LoginDialog.CANCEL_BTN);
	}

	public String getAmplitudeText() {
		Log.method("getAmplitudeText");
		return amplitude.getText();
	}

	public Float getAmplitudeTextFloatValue() {
		Log.method("getAmplitudeTextFloatValue");
		Float ampFloat = Float.MIN_VALUE;
		try {
			if (amplitude.getText()!=null) {
				ampFloat = Float.valueOf(amplitude.getText());
			}
		} catch (Exception e) {/*ignore error*/}

		return ampFloat;
	}

	public String getMaxText() {
		Log.method("getMaxText");
		return maxText.getText();
	}

	public Float getMaxTextFloatValue() {
		Log.method("getMaxTextFloatValue");
		Float maxFloat = Float.MIN_VALUE;
		try {
			if (maxText.getText()!=null) {
				maxFloat = Float.valueOf(maxText.getText().replace(MAX_LABEL, ""));
			}
		} catch (Exception e) {/*ignore error*/}

		return maxFloat;
	}

	public String getCh4PpmText() {
		Log.method("getPpmText");
		return cH4ppm.getText();
	}

	public String getC2h6Text() {
		Log.method("getC2h6Text");
		return c2h6.getText();
	}

	public String getModeText() {
		Log.method("getModeText");
		return modeText.getText();
	}
}