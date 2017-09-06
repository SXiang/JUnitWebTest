package androidapp.regression.source;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.support.PageFactory;

import androidapp.screens.source.AndroidAlarmSettingsScreen;
import androidapp.screens.source.AndroidInvestigateReportScreen;
import androidapp.screens.source.AndroidInvestigationScreen;
import androidapp.screens.source.AndroidMarkerTypeListControl;
import androidapp.screens.source.AndroidSettingsScreen;
import common.source.BackPackAnalyzer;
import common.source.Log;
import common.source.TestContext;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AndroidSettingsScreenTest extends BaseAndroidTest {

	protected AndroidInvestigationScreen investigationScreen;
	protected AndroidInvestigateReportScreen investigateReportScreen;
	protected AndroidMarkerTypeListControl markerTypeDialog;
	protected AndroidSettingsScreen settingsScreen;
	protected AndroidAlarmSettingsScreen alarmSettingsScreen;

	private static ThreadLocal<Boolean> appiumTestInitialized = new ThreadLocal<Boolean>();

	@Rule
	public TestName testName = new TestName();

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects(false);
	}

	@Before
	public void beforeTest() throws Exception {
		initializeTestDriver();
		initializeTestScreenObjects();
		if (!TestContext.INSTANCE.getTestSetup().isRunningOnBackPackAnalyzer()) {
			BackPackAnalyzer.restartSimulator();
		}

		startTestRecording(testName.getMethodName());
	}

	@After
	public void afterTest() throws Exception {
		stopTestRecording(testName.getMethodName());
		if (!TestContext.INSTANCE.getTestSetup().isRunningOnBackPackAnalyzer()) {
			BackPackAnalyzer.stopSimulator();
		}
	}

	/**
	 *	Test Case: TC2385_EnergyBackpackAppSettingsConfiguration
	 *	Script:
	 *	- Log into Backpack tablet
	 *	- Launch the Backpack app
	 *	- Click the Menu icon at bottom right
	 *	- Select App Settings
	 *	- Change some settings and click Save
	 *	- Click App Settings again
	 *	Expected Result:
	 *	- If connection to Backpack server is successful, user is navigated to map centered on user's location
	 *	- Window will appear with the following choices -  Clear Heatmap, Alarm Settings, App Settings and Shutdown Instrument
	 *	- User is navigated to PicarroApp Settings page
	 *	- User sees menu again
	 *	- Changed settings should persist
	**/
	@Test
	public void TC2385_EnergyBackpackAppSettingsConfiguration() throws Exception {
		Log.info("\nRunning TC2385_EnergyBackpackAppSettingsConfiguration ...");
		navigateToMapScreenUsingDefaultCreds(true /*waitForMapScreenLoad*/);
		executeWithBackPackDataProcessesPaused(obj -> {
			Log.info("Map screen loaded successfully!");
			mapScreen.assertGpsLabelIsGreen();
			mapScreen.clickOnMenuButton();
			settingsScreen.waitForScreenLoad();
			assertTrue("Clear HeatMap button should be displayed", settingsScreen.getClearHeatmap().isDisplayed());
			assertTrue("Alarm Settings button should be displayed", settingsScreen.getAlarmSettings().isDisplayed());
			assertTrue("App Settings button should be displayed", settingsScreen.getAppSettings().isDisplayed());
			assertTrue("Shutdown Instrument button should be displayed", settingsScreen.getShutdownInstrument().isDisplayed());

			return true;
		});
	}

	/**
	 *	Test Case: TC2386_EnergyBackpackAlarmSettings
	 *	Script:
	 *	- Log into Backpack tablet
	 *	- Click on the menu icon at bottom right of the screen
	 *	- Set Speaker icon to enabled
	 *	- Click on Alarm Settings
	 *	- Set Volume to 5, Amplitude to 0.2ppm and Threshold to 2ppm and click Apply
	 *	- Wave wand over controlled leak set just above these levels
	 *	- Increase volume to 10 and click Apply
	 *	- Increase volume to 15 and click Apply
	 *	- Decrease volume to 10 and click Apply
	 *	Expected Result:
	 *	- Window will appear with the following choices - Alarm Settings, App Settings and Shutdown Instrument
	 *	- Window will pop up with the following settings - Volume , Amplitude and Threshold
	 *	- Alarm should sound with faint beeping when sample is taken
	 *  - Alarm should sound with louder beeping
	 *	- Alarm should sound with still louder beeping
	 *	- Alarm should sound with fainter beeping	 *
	**/
	// PARTIAL: Analyzer device sound verifications NOT included.
	@Test
	public void TC2386_EnergyBackpackAlarmSettings() throws Exception {
		Log.info("\nRunning TC2386_EnergyBackpackAlarmSettings ...");
		navigateToMapScreenUsingDefaultCreds(true /*waitForMapScreenLoad*/);
		executeWithBackPackDataProcessesPaused(obj -> {
			Log.info("Map screen loaded successfully!");
			mapScreen.assertGpsLabelIsGreen();
			mapScreen.clickOnMenuButton();
			settingsScreen.waitForScreenLoad();
			assertTrue("Clear HeatMap button should be displayed", settingsScreen.getClearHeatmap().isDisplayed());
			assertTrue("Alarm Settings button should be displayed", settingsScreen.getAlarmSettings().isDisplayed());
			assertTrue("App Settings button should be displayed", settingsScreen.getAppSettings().isDisplayed());
			assertTrue("Shutdown Instrument button should be displayed", settingsScreen.getShutdownInstrument().isDisplayed());

			settingsScreen.clickOnAlarmSettings();
			alarmSettingsScreen.waitForScreenLoad();
			alarmSettingsScreen.slideToVolume(5.0f);
			alarmSettingsScreen.slideToThresholdppm(20.0f);
			alarmSettingsScreen.clickOnApply();
			return true;
		});
	}

	private void initializeTestScreenObjects() {
		initializeInvestigationScreen();
		initializeInvestigateReportScreen();
		initializeSettingsScreen();
		initializeAlarmSettingsScreen();
	}

	private void initializeInvestigateReportScreen() {
		investigateReportScreen = new AndroidInvestigateReportScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigateReportScreen);
	}

	private void initializeInvestigationScreen() {
		investigationScreen = new AndroidInvestigationScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigationScreen);
	}

	protected void initializeSettingsScreen() {
		settingsScreen = new AndroidSettingsScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), settingsScreen);
	}

	protected void initializeAlarmSettingsScreen() {
		alarmSettingsScreen = new AndroidAlarmSettingsScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), alarmSettingsScreen);
	}

	private void installApkStartAppiumDriver() throws MalformedURLException, IOException {
		initializeAppiumDriver();
		installLaunchApp(AndroidActivities.MAIN_ACTIVITY);
	}

	private void initializeTestDriver() throws MalformedURLException, IOException, Exception {
		boolean isAppiumTestInitialized = appiumTestInitialized.get() != null && appiumTestInitialized.get();
		if (!isAppiumTestInitialized) {
			initializeAppiumTest();
			appiumTestInitialized.set(true);
		} else {
			installApkStartAppiumDriver();
		}
	}
}