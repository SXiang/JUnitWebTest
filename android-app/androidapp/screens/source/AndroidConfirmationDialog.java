package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import common.source.Log;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidConfirmationDialog extends AndroidBaseScreen {

	private static final String UI_SELECTOR_MESSAGE = "new UiSelector().resourceId(\"android:id/message\")";
	private static final String UI_SELECTOR_ALERT_TITLE = "new UiSelector().resourceId(\"android:id/alertTitle\")";
	private static final String UI_SELECTOR_CANCEL_BUTTON = "new UiSelector().resourceId(\"android:id/button1\")";
	private static final String UI_SELECTOR_OK_BUTTON = "new UiSelector().resourceId(\"android:id/button2\")";

	/****** Label elements ******/

	@AndroidFindBy(uiAutomator = UI_SELECTOR_ALERT_TITLE)
	private WebElement dialogTitle;

	@AndroidFindBy(uiAutomator = UI_SELECTOR_MESSAGE)
	private WebElement dialogMessage;

	@AndroidFindBy(uiAutomator = UI_SELECTOR_OK_BUTTON)
	private WebElement oK;

	@AndroidFindBy(uiAutomator = UI_SELECTOR_CANCEL_BUTTON)
	private WebElement cancel;

	public AndroidConfirmationDialog(WebDriver driver) {
		super(driver);
	}

	/****** Label Methods ******/

	public String getDialogTitleText() {
		Log.method("getDialogTitleText");
		dialogTitle = getAndroidDriver().findElementByAndroidUIAutomator(UI_SELECTOR_ALERT_TITLE);
		return dialogTitle.getText();
	}

	public String getDialogMessageText() {
		Log.method("getDialogMessageText");
		dialogMessage = getAndroidDriver().findElementByAndroidUIAutomator(UI_SELECTOR_MESSAGE);
		return dialogMessage.getText();
	}

	/****** Button Methods ******/

	public WebElement getOKButton() {
		Log.method("getOKButton");
		oK = getAndroidDriver().findElementByAndroidUIAutomator(UI_SELECTOR_OK_BUTTON);
		return oK;
	}

	public void clickOnOK() throws Exception {
		Log.method("clickOnOK");
		tap(getOKButton());
	}

	public WebElement getCancelButton() {
		Log.method("getCancelButton");
		cancel = getAndroidDriver().findElementByAndroidUIAutomator(UI_SELECTOR_CANCEL_BUTTON);
		return cancel;
	}

	public void clickOnCancel() {
		Log.method("clickOnCancel");
		tap(getCancelButton());
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("AndroidConfirmationDialog.screenLoadCondition");
		return dialogMessage!=null && dialogMessage.isDisplayed();
	}
}