package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import common.source.Log;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidAddSourceDialog extends AndroidBaseScreen {

	/****** Button elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement addLeak;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[2]")
	@CacheLookup
	private WebElement addOtherSources;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[3]/android.view.ViewGroup[3]")
	@CacheLookup
	private WebElement cancel;
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
		return getAddLeakButton()!=null && getAddLeakButton().isDisplayed();
	}
}