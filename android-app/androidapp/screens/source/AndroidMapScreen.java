package androidapp.screens.source;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import common.source.AccessibilityLabel;
import common.source.BaseHelper;
import common.source.BaselineImages;
import common.source.ExceptionUtility;
import common.source.Log;
import common.source.RegexUtility;
import common.source.TestContext;
import common.source.Timeout;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidMapScreen extends AndroidBaseScreen {
	private static final String TOGGLE_MODE_BUTTON_UI_SELECTOR = "new UiSelector().text(\"Toggle Mode\")";
	private static final String RESET_MAX_BUTTON_UI_SELECTOR = "new UiSelector().text(\"Reset Max\")";
	private static final String CLEAR_HEATMAP_BUTTON_UI_SELECTOR = "new UiSelector().text(\"Clear Heatmap\")";
	private static final String CANCEL_BUTTON_UI_SELECTOR = "new UiSelector().text(\"Cancel\")";
	private static final String SUBMIT_BUTTON_UI_SELECTOR = "new UiSelector().text(\"Submit\")";

	private static final String INVESTIGATE_BUTTON_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[4]/android.view.ViewGroup[1]";
	private static final String MENU_BUTTON_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.widget.TextView[1]";
	private static final String LOGIN_VALIDATION_LABEL_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.widget.TextView[2]";
	private static final String USERNAME_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.EditText[1]";
	private static final String PASSWORD_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[2]/android.widget.EditText[1]";
	private static final String METHANE_MODE = "Methane Mode";
	private static final String ETHANE_MODE = "Ethane Mode";
	private static final String MAX_LABEL = "Max:";
	private static final String PPM = "ppm";

	/******* Label elements *******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.TextView[1]")
	@CacheLookup
	private WebElement modeText;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.TextView[2]")
	@CacheLookup
	private WebElement maxText;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.TextView[1]")
	@CacheLookup
	private WebElement amplitude;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.TextView[5]")
	@CacheLookup
	private WebElement c2h6;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.widget.TextView[3]")
	@CacheLookup
	private WebElement cH4ppm;

	/******* Button elements *******/

	@AndroidFindBy(xpath = INVESTIGATE_BUTTON_XPATH)
	@CacheLookup
	private WebElement investigate;

	@AndroidFindBy(uiAutomator = CLEAR_HEATMAP_BUTTON_UI_SELECTOR)
	@CacheLookup
	private WebElement clearHeatmap;

	@AndroidFindBy(uiAutomator = RESET_MAX_BUTTON_UI_SELECTOR)
	@CacheLookup
	private WebElement resetMax;

	@AndroidFindBy(uiAutomator = TOGGLE_MODE_BUTTON_UI_SELECTOR)
	@CacheLookup
	private WebElement toggleMode;

	@AndroidFindBy(xpath = MENU_BUTTON_XPATH)
	@CacheLookup
	private WebElement menuButton;

	@AndroidFindBy(xpath = LOGIN_VALIDATION_LABEL_XPATH)
	@CacheLookup
	private WebElement loginValidationLabel;
	private Boolean loginValidationLabelLocated = true;    // element fetched at page load time. Set to false to detect element post page load.

	@AndroidFindBy(xpath = PASSWORD_XPATH)
	@CacheLookup
	private WebElement passwordEditView;
	private Boolean passwordEditViewLocated = true;    // element fetched at page load time. Set to false to detect element post page load.

	@AndroidFindBy(uiAutomator = SUBMIT_BUTTON_UI_SELECTOR)
	@CacheLookup
	private WebElement submit;
	private Boolean submitButtonLocated = true;        // element fetched at page load time. Set to false to detect element post page load.

	// Following user login elements are currently not used in normal test interaction and therefore NOT fetched at page load time for perf reason.
	// If tests need to interact with these elements, fetch these using @AndroidFindBy.
	private WebElement usernameEditView = null;
	private Boolean usernameEditViewLocated = false;
	private WebElement cancelButton = null;
	private Boolean cancelButtonLocated = false;

	public AndroidMapScreen(WebDriver driver) {
		super(driver);
	}

	public void clickOnClearHeatmap() {
		Log.method("clickOnClearHeatmap");
		tap(getClearHeatmapButton());
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

	public void clearUsername() {
		Log.method("clearUsername");
		getUsernameEditView().clear();;
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
			modeTextValid = !BaseHelper.isNullOrEmpty(modeTextValue);
		}

		Log.info(String.format("toggleModeButtonDisplayed=[%b], modeTextDisplayed=[%b], modeTextValid=[%b]",
				toggleModeButtonDisplayed, modeTextDisplayed, modeTextValid));
		return toggleModeButtonDisplayed && modeTextDisplayed && modeTextValid;
	}

	public void assertConcentrationChartIsShown() {
		Log.method("assertConcentrationChartIsShown");
		if (!TestContext.INSTANCE.getTestSetup().isRunningOnBackPackAnalyzer()) {
			screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.LOADERS, BaselineImages.ImageFile.DefaultConcChart, 3 /*attempts*/);
		} else {
			Log.info("Skipping ConcentrationChart verification. This step is verified in simulator runs. Run test targetting backpack simulator to enable this verification");
		}
	}

	public void assertEnterPasswordHintTextIsShown(String folderName, String imageFileName) {
		Log.method("assertEnterPasswordHintTextIsShown", folderName, imageFileName);
		screenVerifier.assertImageFoundOnScreen(this, folderName, imageFileName);
	}

	public void assertIncorrectCredentialsMessageIsShownInRed() {
		Log.method("assertIncorrectCredentialsMessageIsShownInRed");
		screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.COMMON, BaselineImages.ImageFile.LoginErrorRedText);
	}

	public void assertPleaseEnterYourPasswordMessageIsShownInRed() {
		Log.method("assertPleaseEnterYourPasswordMessageIsShownInRed");
		screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.COMMON, BaselineImages.ImageFile.PleaseEnterYourPassword);
	}

	public void assertPleaseEnterYourUsernameMessageIsShownInRed() {
		Log.method("assertPleaseEnterYourUsernameMessageIsShownInRed");
		screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.COMMON, BaselineImages.ImageFile.PleaseEnterUsername);
	}

	public void assertMapIsLoaded() {
		Log.method("assertMapIsLoaded");
		if (!TestContext.INSTANCE.getTestSetup().isRunningOnBackPackAnalyzer()) {
			screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.LOADERS, BaselineImages.ImageFile.DefaultMapScreenTopLeft);
			screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.LOADERS, BaselineImages.ImageFile.DefaultMapScreenBottomRight);
		} else {
			Log.info("Skipping map is loaded verification. This step is verified in simulator runs. Run test targetting backpack simulator to enable this verification");
		}
	}

	public void assertMapIsCenteredForPicarroUser() {
		Log.method("assertMapIsCenteredForPicarroUser");
		if (!TestContext.INSTANCE.getTestSetup().isRunningOnBackPackAnalyzer()) {
			screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.LOADERS, BaselineImages.ImageFile.DefaultMapScreenPicarroLoc);
		} else {
			Log.info("Skipping map is centered verification. This step is verified in simulator runs. Run test targetting backpack simulator to enable this verification");
		}
	}

	public void assertMethaneModeIsShownInTopPanel() {
		Log.method("assertMethaneModeIsShownInTopPanel");
		screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.COMMON, BaselineImages.ImageFile.TopPanelMethaneModeLabel);
	}

	public void assertEthaneModeIsShownInTopPanel() {
		Log.method("assertEthaneModeIsShownInTopPanel");
		screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.COMMON, BaselineImages.ImageFile.TopPanelEthaneModeLabel);
	}

	public void assertDefaultMethaneValueShownInTopPanelIsCorrect() {
		Log.method("assertDefaultMethaneValueShownInTopPanelIsCorrect");
		screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.COMMON, BaselineImages.ImageFile.TopPanelMethaneValue2ppm);
	}

	public void assertDefaultMaxValueShownInTopPanelIsCorrect() {
		Log.method("assertDefaultMaxValueShownInTopPanelIsCorrect");
		screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.COMMON, BaselineImages.ImageFile.TopPanelMaxValue2ppm);
	}

	public void assertBottomPaneButtonsAreCorrect() {
		Log.method("assertBottomPaneButtonsAreCorrect");
		screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.COMMON, BaselineImages.ImageFile.BottomPaneButtons);
	}

	public void assertGpsLabelIsGreen() {
		Log.method("assertGpsLabelIsGreen");
		screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.COMMON, BaselineImages.ImageFile.GpsLabelGreen);
	}

	public WebElement getClearHeatmapButton() {
		Log.method("getClearHeatmapButton");
		return clearHeatmap;
	}

	public WebElement getMenuButton() {
		menuButton = getAndroidDriver().findElementByXPath(MENU_BUTTON_XPATH);
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

	public boolean isEthaneModeShown() throws IOException {
		Log.method("isEthaneModeShown");
		return this.getModeText().equals(ETHANE_MODE);
	}

	public boolean isMethaneModeShown() throws IOException {
		Log.method("isMethaneModeShown");
		return this.getModeText().equals(METHANE_MODE);
	}

	public void ensureAnalyzerIsInMethaneMode() throws IOException {
		Log.method("ensureAnalyzerIsInMethaneMode");
		if (isEthaneModeShown()) {
			Log.info("Analyzer detected in Ethane mode. Switching to Methane mode");
			this.clickOnToggleMode();
			this.assertMethaneModeIsShownInTopPanel();
		}
	}

	public void clickOnResetButton() {
		Log.method("clickOnResetButton");
		tap(resetMax);
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

	public WebElement getUsernameEditView() {
		if (!usernameEditViewLocated) {
			usernameEditView = getAndroidDriver().findElementByXPath(USERNAME_XPATH);
			usernameEditViewLocated = true;
		}

		return usernameEditView;
	}

	public WebElement getUsernameEditViewByAccId() {
		return getAndroidDriver().findElementByAccessibilityId(AccessibilityLabel.LoginDialog.USERNAME_EDIT_VIEW);
	}

	public WebElement getPasswordEditView() {
		if (!passwordEditViewLocated) {
			passwordEditView = getAndroidDriver().findElementByXPath(PASSWORD_XPATH);
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
			submit = getAndroidDriver().findElementByAndroidUIAutomator(SUBMIT_BUTTON_UI_SELECTOR);
			submitButtonLocated = true;
		}

		return submit;
	}

	public WebElement getSubmitButtonByAccId() {
		return getAndroidDriver().findElementByAccessibilityId(AccessibilityLabel.LoginDialog.SUBMIT_BTN);
	}

	public WebElement getCancelButton() {
		if (!cancelButtonLocated) {
			cancelButton = getAndroidDriver().findElementByAndroidUIAutomator(CANCEL_BUTTON_UI_SELECTOR);
			cancelButtonLocated = true;
		}

		return cancelButton;
	}

	public WebElement getLoginValidationLabel() {
		Log.method("getLoginValidationLabel");
		if (!loginValidationLabelLocated) {
			loginValidationLabel = getAndroidDriver().findElement(MobileBy.xpath(LOGIN_VALIDATION_LABEL_XPATH));
			loginValidationLabelLocated = true;
		}

		return loginValidationLabel;
	}

	public String getLoginValidationLabelText() {
		Log.method("getLoginValidationLabelText");
		return getLoginValidationLabel().getText();
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
		Float fMax = Float.MIN_VALUE;
		String strMax = maxText.getText();
		if (!BaseHelper.isNullOrEmpty(strMax)) {
			try {
				List<String> matchingGroups = RegexUtility.getMatchingGroups(strMax, RegexUtility.PICARRO_APP_MAX_AMPLITUDE_REGEX);
				String maxTextValue = matchingGroups.get(1);
				fMax = Float.valueOf(maxTextValue);
			} catch (Exception e) {
				Log.warn(String.format("Error getting max text value. Exception is -> %s", ExceptionUtility.getStackTraceString(e)));
			}
		}

		return fMax;
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