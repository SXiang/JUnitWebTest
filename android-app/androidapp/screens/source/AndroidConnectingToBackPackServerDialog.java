package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import common.source.BaselineImages;
import common.source.Log;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidConnectingToBackPackServerDialog extends AndroidBaseScreen {

	private static final String EDIT_SETTINGS_BUTTON = "new UiSelector().text(\"Edit Settings\")";

	/****** Button elements ******/

	@AndroidFindBy(uiAutomator = EDIT_SETTINGS_BUTTON)
	private WebElement editSettings;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.widget.TextView[1]")
	private WebElement connectingLabel;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]")
	@CacheLookup
	private WebElement buildNumber;


	public AndroidConnectingToBackPackServerDialog(WebDriver driver) {
		super(driver);
	}

	public void assertConnectingToBackPackServerMessageIsShown() {
		Log.method("assertConnectingToBackPackServerMessageIsShown");
		screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.LOADERS, BaselineImages.ImageFile.ConnectingToBackPackServer);
	}

	public void assertEditSettingsButtonIsShown() {
		Log.method("assertEditSettingsButtonIsShown");
		screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.LOADERS, BaselineImages.ImageFile.EditSettingsButton);
	}

	/****** Button Methods ******/

	public WebElement getEditSettingsButton() {
		Log.method("getEditSettingsButton");
		return editSettings;
	}

	public void clickOnEditSettings() {
		Log.method("clickOnEditSettings");
		tap(getEditSettingsButton());
	}

	/****** Label Methods ******/

	public String getBuildNumberLabelText() {
		Log.method("getBuildNumberLabelText");
		return buildNumber.getText();
	}

	public String getConnectingLabelText() {
		return connectingLabel.getText();
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("AndroidConnectingToBackPackServerDialog.screenLoadCondition");
		return getEditSettingsButton().isDisplayed();
	}
}