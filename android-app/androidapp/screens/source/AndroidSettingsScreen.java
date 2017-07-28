package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import common.source.Log;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidSettingsScreen extends AndroidBaseScreen {

	private static final String ALARM_SETTINGS_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[2]";
	private WebElement alarmSettings;
	private boolean alarmSettingsDisplayed;

	private static final String APP_SETTINGS_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[3]";
	private WebElement appSettings;
	private boolean appSettingsDisplayed;

	private static final String CLEAR_HEATMAP_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]";
	private WebElement clearHeatmap;
	private boolean clearHeatmapDisplayed;

	private static final String SHUTDOWN_INSTRUMENT_XPATH = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[1]/android.view.ViewGroup[1]/android.view.ViewGroup[4]";
	private WebElement shutdownInstrument;
	private boolean shutdownInstrumentDisplayed;

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
			alarmSettings = getAndroidDriver().findElementByXPath(ALARM_SETTINGS_XPATH);
			alarmSettingsDisplayed = true;
		}

		return alarmSettings;
	}

	public void clickOnAlarmSettings() {
		Log.method("clickOnAlarmSettings");
		tap(getAlarmSettings());
	}

	public WebElement getAppSettings() {
		Log.method("getAppSettings");
		if (!appSettingsDisplayed) {
			appSettings = getAndroidDriver().findElementByXPath(APP_SETTINGS_XPATH);
			appSettingsDisplayed = true;
		}

		return appSettings;
	}

	public void clickOnAppSettings() {
		Log.method("clickOnAppSettings");
		tap(getAppSettings());
	}

	public WebElement getClearHeatmap() {
		Log.method("getClearHeatmap");
		if (!clearHeatmapDisplayed) {
			clearHeatmap = getAndroidDriver().findElementByXPath(CLEAR_HEATMAP_XPATH);
			clearHeatmapDisplayed = true;
		}

		return clearHeatmap;
	}

	public void clickOnClearHeatmap() {
		Log.method("clickOnClearHeatmap");
		tap(getClearHeatmap());
	}

	public WebElement getShutdownInstrument() {
		Log.method("getShutdownInstrument");
		if (!shutdownInstrumentDisplayed) {
			shutdownInstrument = getAndroidDriver().findElementByXPath(SHUTDOWN_INSTRUMENT_XPATH);
			shutdownInstrumentDisplayed = true;
		}

		return shutdownInstrument;
	}

	public void clickOnShutdownInstrument() {
		Log.method("clickOnShutdownInstrument");
		tap(getShutdownInstrument());
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("screenLoadCondition");
		WebElement element = getShutdownInstrument();
		return (element != null) && (element.isDisplayed());
	}
}