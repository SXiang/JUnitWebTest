package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import common.source.Log;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidConfirmationDialog extends AndroidBaseScreen {

	/****** Label elements ******/

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/alertTitle\")")
	@CacheLookup
	private WebElement dialogTitle;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/message\")")
	@CacheLookup
	private WebElement dialogMessage;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/button2\")")
	@CacheLookup
	private WebElement oK;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/button1\")")
	@CacheLookup
	private WebElement cancel;

	public AndroidConfirmationDialog(WebDriver driver) {
		super(driver);
	}

	/****** Label Methods ******/

	public String getDialogTitleText() {
		Log.method("getDialogTitleText");
		return dialogTitle.getText();
	}

	public String getDialogMessageText() {
		Log.method("getDialogMessageText");
		return dialogMessage.getText();
	}

	/****** Button Methods ******/

	public WebElement getOKButton() {
		Log.method("getOKButton");
		return oK;
	}

	public void clickOnOK() throws Exception {
		Log.method("clickOnOK");
		tap(getOKButton());
	}

	public WebElement getCancelButton() {
		Log.method("getCancelButton");
		return cancel;
	}

	public void clickOnCancel() {
		Log.method("clickOnCancel");
		tap(getCancelButton());
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("screenLoadCondition");
		return dialogMessage!=null && dialogMessage.isDisplayed();
	}
}