package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.source.Log;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidMapScreen extends AndroidBaseScreen {

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[3]/android.view.ViewGroup[1]")
	private WebElement toggleModeButton;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[3]/android.view.ViewGroup[2]")
	private WebElement resetButton;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.widget.TextView[1]")
	private WebElement amplitudeText;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.widget.TextView[2]")
	private WebElement maxText;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.widget.TextView[4]")
	private WebElement ppmText;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[1]/android.view.ViewGroup/android.widget.TextView")
	private WebElement modeText;

	public AndroidMapScreen(WebDriver driver) {
		super(driver);
	}

	public void clickOnToggleMode() {
		Log.method("clickOnToggleMode");
		getToggleModeButton().click();
	}

	public void clickOnReset() {
		Log.method("clickOnReset");
		getResetButton().click();
	}

	@Override
	public Boolean screenLoadCondition() {
		return toggleModeButton!=null && toggleModeButton.isDisplayed();
	}

	public WebElement getToggleModeButton() {
		return toggleModeButton;
	}

	public WebElement getResetButton() {
		return resetButton;
	}

	public String getAmplitudeText() {
		Log.method("getAmplitudeText");
		return amplitudeText.getText();
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