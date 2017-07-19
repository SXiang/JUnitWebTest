package androidapp.regression.source;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import androidapp.screens.source.AndroidMapScreen;
import androidapp.screens.source.AndroidMainLoginScreen;
import common.source.AdbInterface;
import common.source.AndroidAutomationTools;
import common.source.BackPackAnalyzer;
import common.source.CheckedPredicate;
import common.source.FileUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.Timeout;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import surveyor.scommon.source.BaseTest;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class BaseAndroidTest extends BaseTest {
	private static ThreadLocal<Boolean> reactNativeInitStatus = new ThreadLocal<Boolean>() {
	    @Override
	    protected Boolean initialValue() {
	    	return false;
	    }
	};

	protected AppiumDriver<WebElement> appiumDriver;
	protected AppiumDriver<WebElement> appiumWebDriver;
	protected AndroidMainLoginScreen settingsScreen;
	protected AndroidMapScreen mapScreen;

	protected static final String APP_PACKAGE_NAME = "com.picarroapp";
	protected static final String APPIUM_SERVER_HUB_HOST = "http://127.0.0.1:4723/wd/hub";


	public static class AndroidActivities {
		public static final String APP_DRAW_OVERLAY_SETTINGS_ACTIVITY = "AppDrawOverlaySettingsActivity";
		public static final String MAIN_ACTIVITY = "MainActivity";
	}

	@BeforeClass
	public static void setUpBeforeTestClass() throws Exception {
		// Initialize TestSetup to instantiate the TestContext.
		TestSetup testSetup = new TestSetup(false /* skip initialization */);
		String rootPath;
		try {
			rootPath = TestSetup.getRootPath();
			testSetup.loadTestProperties(rootPath);
		} catch (IOException e) {
			Log.error(e.getMessage());
		}
		testSetup.initialize();
		TestContext.INSTANCE.setTestSetup(testSetup);

		// Start backpack simulator and android automation tools (emulator, appium server).
		cleanupProcesses();

		if (!testSetup.isRunningOnBackPackAnalyzer()) {
			BackPackAnalyzer.startSimulator();
		}

		AdbInterface.init(testSetup.getAdbLocation());
	    AndroidAutomationTools.start();
	    AndroidAutomationTools.disableAnimations();  // perf optimization.
	}

	@AfterClass
	public static void tearDownAfterTestClass() throws Exception {
		AdbInterface.stop();
		cleanupProcesses();
	}

	@Before
	public void setupBeforeTest() throws Exception {
	}

	@After
	public void tearDownBeforeTest() throws MalformedURLException, IOException {
		cleanUp();
	}

	// Perf optimization. pause simulator processes causing delay in fetching element using Appium driver.
	// This method will execute test steps specified by pausing the backpack simulator and resume simulator after completion.
	protected boolean executeWithBackPackDataProcessesPaused(CheckedPredicate<Object> predicate) throws Exception {
		boolean retVal = false;
		BackPackAnalyzer.pauseDataProcesses();
		retVal = predicate.test(null);
		BackPackAnalyzer.resumeDataProcesses();
		return retVal;
	}

	private static void cleanupProcesses() throws IOException {
		Log.method("cleanupProcesses");

		if (!TestContext.INSTANCE.getTestSetup().isRunningOnBackPackAnalyzer()) {
			BackPackAnalyzer.stopSimulator();
		}

		AndroidAutomationTools.stop();
		TestContext.INSTANCE.stayIdle(3);    // restarting processes immediately after cleanup could give errors.
	}

	protected void cleanUp() {
		if (appiumDriver != null) {
			appiumDriver.quit();
		}

		if (appiumWebDriver != null) {
			appiumWebDriver.quit();
		}
	}

	protected void handlePermissionsPrompt() {
		Log.method("handlePermissionsPrompt");
		List<WebElement> permissionPrompts = appiumDriver.findElements(MobileBy.xpath("//*[@class='android.widget.Switch']"));
		if (permissionPrompts.size() > 0) {
			permissionPrompts.get(0).click();
		}
	}

	private void initializeScreenObjects() {
		Log.method("initializeScreenObjects");
		initializeMainLoginScreen();
		initializeMapScreen();
	}

	protected void initializeMainLoginScreen() {
		settingsScreen = new AndroidMainLoginScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), settingsScreen);
	}

	protected void initializeMapScreen() {
		mapScreen = new AndroidMapScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), mapScreen);
	}

	protected void initializeAppiumTest() throws MalformedURLException, IOException, Exception {
		Log.method("initializeAppiumTest");
		ensureApkExistsInConnectedDevice();
		initializeAppiumDriver();
		startReactNativePackager();
		installLaunchApp(AndroidActivities.APP_DRAW_OVERLAY_SETTINGS_ACTIVITY);
		if (AndroidAutomationTools.isAppDrawOverlayDisplayed()) {
			handlePermissionsPrompt();
			installLaunchApp(AndroidActivities.MAIN_ACTIVITY);
		}

		waitForAppLoad();
	}

	protected void initializeAppiumDriver() throws MalformedURLException {
		Log.method("initializeAppiumDriver");
		// CAPABILITIES: https://appium.io/slate/en/master/?ruby#appium-server-capabilities, https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/caps.md
		DesiredCapabilities capabilities=DesiredCapabilities.android();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
		capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Android Emulator");
		// NOTE: autoGrantPermissions capability is NOT working along with MobileCapabilityType.APP. Use appPackage and appActivity instead of app.
		capabilities.setCapability("appPackage", APP_PACKAGE_NAME);
		capabilities.setCapability("appActivity", ".MainActivity");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");    // timeout in seconds.
		capabilities.setCapability("autoGrantPermissions", "true");

		// Following capabilities have been used for perf optimization.
		capabilities.setCapability("disableAndroidWatchers", true);
		capabilities.setCapability("resetKeyboard", true);  		// hide keyboard
		capabilities.setCapability("unicodeKeyboard", true);		// hide keyboard

		// Create object of URL class and specify the appium server address
		URL url= new URL(APPIUM_SERVER_HUB_HOST);

		// Create object of  AndroidDriver class and pass the url and capability that we created
		appiumDriver =  new AndroidDriver<WebElement>(url, capabilities);

	}

	protected void initializeAppiumWebDriver() throws MalformedURLException {
		Log.method("initializeAppiumWebDriver");
		// CAPABILITIES: https://appium.io/slate/en/master/?ruby#appium-server-capabilities, https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/caps.md
		DesiredCapabilities capabilities=DesiredCapabilities.android();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
		capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Android Emulator");
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");    // timeout in seconds.

		// Create object of URL class and specify the appium server address
		URL url= new URL(APPIUM_SERVER_HUB_HOST);

		// Create object of  AndroidDriver class and pass the url and capability that we created
		appiumWebDriver =  new AndroidDriver<WebElement>(url, capabilities);

	}

	protected void installLaunchApp(String waitActivityName) throws IOException {
		Log.method("installLaunchApp", waitActivityName);
		File apkFile = getApkFile();
		if (appiumDriver != null) {
			AndroidAutomationTools.installLaunchAPK(apkFile.getAbsolutePath(), waitActivityName);
			initializeScreenObjects();
		}
	}

	private void ensureApkExistsInConnectedDevice() throws Exception {
		Log.method("ensureApkExistsInConnectedDevice");
		if (!AndroidAutomationTools.isPackageInstalled(APP_PACKAGE_NAME)) {
			AdbInterface.installPackage(getApkFile().getAbsolutePath(), true /*replaceExisting*/, true /*allowVersionDowngrade*/, true /*grantAllRuntimePermissions*/);
		}
	}

	private File getApkFile() throws IOException {
		Path apkFolderPath = Paths.get(TestSetup.getRootPath(), "apk");
		List<String> apkFiles = FileUtility.getFilesInDirectory(apkFolderPath, "*.apk");
		if (apkFiles == null || apkFiles.size() == 0) {
			throw new IOException(String.format("No APK files found in API directory - '%s'", apkFolderPath));
		}

		String apkFilePath = apkFiles.get(0);
		File apkFile = new File(apkFilePath);
		return apkFile;
	}

	private void startReactNativePackager() throws IOException {
		Log.method("startReactNativePackager");
		if (!reactNativeInitStatus.get()) {
			AndroidAutomationTools.startReactNative();
			reactNativeInitStatus.set(true);
		}
	}

	protected void navigateToMapScreenUsingDefaultCreds(boolean waitForMapScreenLoad) throws Exception {
		final String backpackAddress = TestContext.INSTANCE.getTestSetup().getBackPackServerIpAddress();
		final String picServerAddress = TestContext.INSTANCE.getTestSetup().getBaseUrl();
		final String username = TestContext.INSTANCE.getTestSetup().getLoginUser();

		settingsScreen.saveSettings(backpackAddress, picServerAddress, username);

		if (waitForMapScreenLoad) {
			mapScreen.waitForScreenLoad();
			Log.info("Map screen loaded successfully!");
		}
	}

	public void waitForAppLoad() {
		settingsScreen.waitForFirstAppLoad();
	}
}