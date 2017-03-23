package surveyor.scommon.androidapp.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.source.Log;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidInvestigateMapScreen extends AndroidBaseScreen {

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]")
	private WebElement followButton;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Investigate\")")
	private WebElement investigateButton;

	public AndroidInvestigateMapScreen(WebDriver driver) {
		super(driver);
	}

	public void clickOnFollow() {
		Log.method("clickOnFollow");
		getFollowButton().click();
	}

	public void clickOnInvestigate() {
		Log.method("clickOnInvestigate");
		getInvestigateButton().click();
	}

	@Override
	public Boolean screenLoadCondition() {
		return mainFrameLayout!=null && mainFrameLayout.isDisplayed();
	}

	public WebElement getFollowButton() {
		return followButton;
	}

	public WebElement getInvestigateButton() {
		return investigateButton;
	}
}
