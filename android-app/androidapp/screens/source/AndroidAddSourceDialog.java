package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import common.source.Log;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidAddSourceDialog extends AndroidBaseScreen {

	/****** Button elements ******/

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Add Leak\")")
	@CacheLookup
	private WebElement addLeak;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Add Other Sources\")")
	@CacheLookup
	private WebElement addOtherSources;

	public AndroidAddSourceDialog(WebDriver driver) {
		super(driver);
	}

	/****** Button Methods ******/

	public WebElement getAddLeakButton() {
		Log.method("getAddLeakButton");
		return addLeak;
	}

	public void clickOnAddLeak() {
		Log.method("clickOnAddLeak");
		tap(getAddLeakButton());
	}

	public WebElement getAddOtherSourcesButton() {
		Log.method("getAddOtherSourcesButton");
		return addOtherSources;
	}

	public void clickOnAddOtherSources() {
		Log.method("clickOnAddOtherSources");
		tap(getAddOtherSourcesButton());
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("AndroidAddSourceDialog.screenLoadCondition");
		return getAddLeakButton()!=null && getAddLeakButton().isDisplayed();
	}
}