package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.source.Log;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidConnectingToBackPackServerDialog extends AndroidBaseScreen {

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ViewGroup")
	private WebElement editSettingsButton;

	public AndroidConnectingToBackPackServerDialog(WebDriver driver) {
		super(driver);
	}

	public void clickOnEditSettings() {
		Log.method("clickOnFirstInvestigation");
		editSettingsButton.click();
	}

	@Override
	public Boolean screenLoadCondition() {
		return editSettingsButton!=null && editSettingsButton.isDisplayed();
	}
}