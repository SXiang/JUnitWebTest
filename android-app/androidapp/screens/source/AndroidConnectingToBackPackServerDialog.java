package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

	public AndroidConnectingToBackPackServerDialog(WebDriver driver) {
		super(driver);
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

	public String getConnectingLabelText() {
		return connectingLabel.getText();
	}

	public void assertConnectingToBackPackServerMessageIsShown() {
		Log.method("assertConnectingToBackPackServerMessageIsShown");
		screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.LOADERS, BaselineImages.ImageFile.ConnectingToBackPackServer);
	}

	public void assertEditSettingsButtonIsShown() {
		Log.method("assertEditSettingsButtonIsShown");
		screenVerifier.assertImageFoundOnScreen(this, BaselineImages.Folder.LOADERS, BaselineImages.ImageFile.EditSettingsButton);
	}

	@Override
	public Boolean screenLoadCondition() {
		return getEditSettingsButton().isDisplayed();
	}
}