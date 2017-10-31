package androidapp.screens.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import common.source.Log;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AndroidSettingsScreen extends AndroidBaseScreen {

	public static enum ParentScreen {
		ReportScreen,
		MapScreen
	}

	private static final String ALARM_SETTINGS_UI_SELECTOR = "new UiSelector().text(\"Alarm Settings\")";
	private WebElement alarmSettings;
	private boolean alarmSettingsDisplayed;

	private static final String APP_SETTINGS_UI_SELECTOR = "new UiSelector().text(\"App Settings\")";
	private WebElement appSettings;
	private boolean appSettingsDisplayed;

	private static final String CLEAR_HEATMAP_UI_SELECTOR = "new UiSelector().text(\"Clear Heatmap\")";
	private WebElement clearHeatmap;
	private boolean clearHeatmapDisplayed;

	private static final String LOGOUT_UI_SELECTOR = "new UiSelector().text(\"Logout\")";
	private WebElement logout;
	private boolean logoutDisplayed;

	private static final String SHUTDOWN_BACKPACK_UI_SELECTOR = "new UiSelector().text(\"Shutdown Backpack\")";
	private WebElement shutdownInstrument;
	private boolean shutdownInstrumentDisplayed;

	/****** Button elements ******/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[4]/android.view.ViewGroup[4]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement investigate;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[4]/android.view.ViewGroup[2]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement resetMax;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[4]/android.view.ViewGroup[1]/android.view.ViewGroup[1]")
	@CacheLookup
	private WebElement toggleMode;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[4]/android.widget.TextView[1]")
	@CacheLookup
	private WebElement menuButtonOnMapScreen;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.view.ViewGroup[2]/android.widget.TextView[1]")
	@CacheLookup
	private WebElement menuButtonOnReportScreen;

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

	public WebElement getMenuButton(ParentScreen parent) {
		Log.method("getMenuButton", parent);
		if (parent.equals(ParentScreen.MapScreen)) {
			return menuButtonOnMapScreen;
		} else if (parent.equals(ParentScreen.ReportScreen)) {
			return menuButtonOnReportScreen;
		}

		return menuButtonOnMapScreen;
	}

	public void tapOnMenuButton(ParentScreen parent) {
		Log.method("tapOnMenuButton", parent);
		tap(getMenuButton(parent));
	}

	public void dismissScreen(ParentScreen parent) {
		Log.method("dismissScreen", parent);
		tapOnMenuButton(parent);
	}

	/**** Elements in the Settings dialog. Shown after menuButton is clicked. ****/

	public WebElement getAlarmSettings() {
		Log.method("getAlarmSettings");
		if (!alarmSettingsDisplayed) {
			alarmSettings = getAndroidDriver().findElementByAndroidUIAutomator(ALARM_SETTINGS_UI_SELECTOR);
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
			appSettings = getAndroidDriver().findElementByAndroidUIAutomator(APP_SETTINGS_UI_SELECTOR);
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
			clearHeatmap = getAndroidDriver().findElementByAndroidUIAutomator(CLEAR_HEATMAP_UI_SELECTOR);
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
			shutdownInstrument = getAndroidDriver().findElementByAndroidUIAutomator(SHUTDOWN_BACKPACK_UI_SELECTOR);
			shutdownInstrumentDisplayed = true;
		}

		return shutdownInstrument;
	}

	public void clickOnShutdownInstrument() {
		Log.method("clickOnShutdownInstrument");
		tap(getShutdownInstrument());
	}

	public WebElement getLogout() {
		Log.method("getLogout");
		if (!logoutDisplayed) {
			logout = getAndroidDriver().findElementByAndroidUIAutomator(LOGOUT_UI_SELECTOR);
			logoutDisplayed = true;
		}

		return logout;
	}

	public void clickOnLogout() {
		Log.method("clickOnLogout");
		tap(getLogout());
	}

	@Override
	public Boolean screenLoadCondition() {
		Log.method("AndroidSettingsScreen.screenLoadCondition");
		WebElement element = getShutdownInstrument();
		return (element != null) && (element.isDisplayed());
	}
}