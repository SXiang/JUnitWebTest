package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import common.source.Log;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidSettingsScreen extends AndroidBaseScreen {

	/****** Button elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[4]/android.view.ViewGroup[3]")
	@CacheLookup
	private WebElement investigate;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[2]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement mode_HR;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[4]/android.view.ViewGroup[2]")
	@CacheLookup
	private WebElement resetMax;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[4]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement toggleMode;

	private WebElement clearHeatmap;
	private boolean clearHeatmapDisplayed;
	private WebElement alarmSettings;
	private boolean alarmSettingsDisplayed;
	private WebElement appSettings;
	private boolean appSettingsDisplayed;
	private WebElement shutdownInstrument;
	private boolean shutdownInstrumentDisplayed;

	public AndroidSettingsScreen(WebDriver driver) {
		super(driver);
	}

	/****** Button Methods ******/

	public WebElement getInvestigate() {
		Log.method("getInvestigate");
		return investigate;
	}

	public void clickOnInvestigate() {
		Log.method("clickOnInvestigate");
		tap(investigate);
	}

	public WebElement getMode_HR() {
		Log.method("getMode_HR");
		return mode_HR;
	}

	public void clickOnMode_HR() {
		Log.method("clickOnMode_HR");
		tap(mode_HR);
	}

	public WebElement getResetMax() {
		Log.method("getResetMax");
		return resetMax;
	}

	public void clickOnResetMax() {
		Log.method("clickOnResetMax");
		tap(resetMax);
	}

	public WebElement getToggleMode() {
		Log.method("getToggleMode");
		return toggleMode;
	}

	public void clickOnToggleMode() {
		Log.method("clickOnToggleMode");
		tap(toggleMode);
	}

	/**** Elements in the Settings dialog. Shown after menuButton is clicked. ****/

	public WebElement getAlarmSettings() {
		Log.method("getAlarmSettings");
		if (!alarmSettingsDisplayed) {
			alarmSettings = getAndroidDriver().findElementByXPath("//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[1]");
			alarmSettingsDisplayed = true;
		}

		return alarmSettings;
	}

	public void clickOnAlarmSettings() {
		Log.method("clickOnAlarmSettings");
		tap(alarmSettings);
	}

	public WebElement getAppSettings() {
		Log.method("getAppSettings");
		if (!appSettingsDisplayed) {
			appSettings = getAndroidDriver().findElementByXPath("//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]/android.view.ViewGroup[1]");
			appSettingsDisplayed = true;
		}

		return appSettings;
	}

	public void clickOnAppSettings() {
		Log.method("clickOnAppSettings");
		tap(appSettings);
	}

	public WebElement getClearHeatmap() {
		Log.method("getClearHeatmap");
		if (!clearHeatmapDisplayed) {
			clearHeatmap = getAndroidDriver().findElementByXPath("//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]");
			clearHeatmapDisplayed = true;
		}

		return clearHeatmap;
	}

	public void clickOnClearHeatmap() {
		Log.method("clickOnClearHeatmap");
		tap(clearHeatmap);
	}

	public WebElement getShutdownInstrument() {
		Log.method("getShutdownInstrument");
		if (!shutdownInstrumentDisplayed) {
			shutdownInstrument = getAndroidDriver().findElementByXPath("//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[4]/android.view.ViewGroup[1]");
			shutdownInstrumentDisplayed = true;
		}

		return shutdownInstrument;
	}

	public void clickOnShutdownInstrument() {
		Log.method("clickOnShutdownInstrument");
		tap(shutdownInstrument);
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("screenLoadCondition");
		WebElement element = getShutdownInstrument();
		return (element != null) && (element.isDisplayed());
	}
}