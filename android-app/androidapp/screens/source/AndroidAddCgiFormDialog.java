package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import common.source.Log;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidAddCgiFormDialog extends AndroidBaseScreen {
	private static final String OK_BUTTON_UI_SELECTOR = "new UiSelector().text(\"OK\")";
	private static final String CANCEL_BUTTON_UI_SELECTOR = "new UiSelector().text(\"Cancel\")";

	/****** Button elements ******/

	@AndroidFindBy(uiAutomator = OK_BUTTON_UI_SELECTOR)
	private WebElement oK;

	@AndroidFindBy(uiAutomator = CANCEL_BUTTON_UI_SELECTOR)
	private WebElement cancel;

	/****** Label elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.widget.TextView[1]")
	@CacheLookup
	private WebElement markerStatusLabel;

	/****** TextField elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.EditText[1]")
	@CacheLookup
	private WebElement cgiTextField;

	public AndroidAddCgiFormDialog(WebDriver driver) {
		super(driver);
	}

	/****** Button Methods ******/

	public WebElement getCancelButton() {
		Log.method("getCancelButton");
		cancel = getAndroidDriver().findElementByAndroidUIAutomator(CANCEL_BUTTON_UI_SELECTOR);
		return cancel;
	}

	public void clickOnCancel() {
		Log.method("clickOnCancel");
		tap(getCancelButton());      // single action click.
	}

	public WebElement getOKButton() {
		Log.method("getOKButton");
		oK = getAndroidDriver().findElementByAndroidUIAutomator(OK_BUTTON_UI_SELECTOR);
		return oK;
	}

	public void clickOnOK() {
		Log.method("clickOnOK");
		tap(getOKButton());
	}

	/****** Label Methods ******/

	public String getMarkerStatusText() {
		Log.method("getMarkerStatusText");
		return markerStatusLabel.getText();
	}

	/****** TextField Methods ******/

	public String getCgiText() {
		Log.method("getCgiText");
		return cgiTextField.getText();
	}

	public void enterCgiText(String value) throws Exception {
		Log.method("enterCgiText");
		sendKeys(cgiTextField, value);
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("screenLoadCondition");
		return getOKButton()!=null && getOKButton().isDisplayed();
	}
}