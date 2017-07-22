package androidapp.regression.source;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import androidapp.screens.source.AndroidMapScreen;
import androidapp.screens.source.AndroidMainLoginScreen;
import common.source.AdbInterface;
import common.source.AndroidAutomationTools;
import common.source.BackPackAnalyzer;
import common.source.BaseHelper;
import common.source.CheckedPredicate;
import common.source.FileUtility;
import common.source.Log;
import common.source.MobileActions;
import common.source.ScreenRecorder;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.Timeout;
import common.source.WebDriverFactory;
import common.source.WebDriverWrapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.scommon.source.BaseTest;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class BaseAndroidTest extends BaseTest {
	private static final String LOGS_BASE_FOLDER = "C:\\QATestLogs";
	private static final String TRUE = "true";

	private static ThreadLocal<Boolean> reactNativeInitStatus = new ThreadLocal<Boolean>() {
	    @Override
	    protected Boolean initialValue() {
	    	return false;
	    }
	};

	protected static Integer PRE_DATA_PROCESSES_PAUSED_WAIT_TIME_IN_SECONDS = 2;

	protected AppiumDriver<WebElement> appiumDriver;
	protected AppiumDriver<WebElement> appiumWebDriver;
	protected AndroidMainLoginScreen settingsScreen;
	protected AndroidMapScreen mapScreen;

	private ScreenRecorder screenRecorder;

	private boolean devMachineOverride = false;         // set to TRUE to disable wait for map screen load when executing on dev machine while authoring tests.

	public static class AndroidActivities {
		public static final String APP_DRAW_OVERLAY_SETTINGS_ACTIVITY = "AppDrawOverlaySettingsActivity";
		public static final String MAIN_ACTIVITY = "MainActivity";
	}

	protected static boolean isRunningInDataGenMode() {
		Log.method("isRunningInDataGenMode");
		String dataGenMode = System.getProperty("isRunningInDataGenMode");
		if (!BaseHelper.isNullOrEmpty(dataGenMode)) {
			Log.info("dataGenMode=" + dataGenMode);
			return dataGenMode.toLowerCase().equals(TRUE);
		}

		return false;
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

		if (!isRunningInDataGenMode()) {
			// Start backpack simulator and android automation tools (emulator, appium server).
			cleanupProcesses();

			AdbInterface.init(testSetup.getAdbLocation());
		    AndroidAutomationTools.start();
		    AndroidAutomationTools.disableAnimations();  // perf optimization.
		}
	}

	@AfterClass
	public static void tearDownAfterTestClass() throws Exception {
		if (!isRunningInDataGenMode()) {
			AdbInterface.stop();
			cleanupProcesses();
		}
	}

	@Before
	public void setupBeforeTest() throws Exception {
	}

	@After
	public void tearDownAfterTest() throws MalformedURLException, IOException {
	}

	private void initScreenRecorder() {
		if (screenRecorder == null) {
			screenRecorder = new ScreenRecorder();
		}
	}

	public void startTestRecording(String testName) throws Exception {
		Log.method("startTestRecording", testName);
		testName = BaseHelper.toAlphaNumeric(testName, '_');
		initScreenRecorder();
		screenRecorder.startRecording(String.format("/sdcard/%s.mp4", testName));
	}

	public void stopTestRecording(String testName) throws Exception {
		Log.method("stopTestRecording", testName);
		testName = BaseHelper.toAlphaNumeric(testName, '_');
		MobileActions action = MobileActions.newAction();
		String videoFileName = String.format("%s.mp4", testName);
		String saveFileLocation = String.format(LOGS_BASE_FOLDER + "\\%s", videoFileName);
		if(FileUtility.fileExists(saveFileLocation)) {
			FileUtility.deleteFile(Paths.get(saveFileLocation));
		}

		Log.info("Stop recording");
		screenRecorder.stopRecording();

		Log.info(String.format("Pulling recording-'%s' from device to '%s'", videoFileName, saveFileLocation));
		AdbInterface.pullFile(String.format("/sdcard/%s", videoFileName), saveFileLocation);

		Log.info(String.format("Removing recording-'%s' from device", videoFileName));
		action.removeSdcardFile(videoFileName);
	}

	// Perf optimization. pause simulator processes causing delay in fetching element using Appium driver.
	// This method will execute test steps specified by pausing the backpack simulator and resume simulator after completion.
	protected boolean executeWithBackPackDataProcessesPaused(boolean applyInitialPause, CheckedPredicate<Object> predicate) throws Exception {
		return executeWithBackPackDataProcessesPausedInternal(applyInitialPause, predicate);
	}

	protected boolean executeWithBackPackDataProcessesPaused(CheckedPredicate<Object> predicate) throws Exception {
		return executeWithBackPackDataProcessesPausedInternal(false /*applyInitialPause*/, predicate);
	}

	private boolean executeWithBackPackDataProcessesPausedInternal(boolean applyInitialPause, CheckedPredicate<Object> predicate) throws Exception {
		boolean retVal = false;
		if (applyInitialPause) {
			TestContext.INSTANCE.stayIdle(PRE_DATA_PROCESSES_PAUSED_WAIT_TIME_IN_SECONDS);
		}

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

	@Override
	public void postTestMethodProcessing() {
		cleanUp();
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
		if (!TestContext.INSTANCE.getTestSetup().isAndroidTestReleaseEnabled()) {
			startReactNativePackager();
		}

		installLaunchApp(AndroidActivities.APP_DRAW_OVERLAY_SETTINGS_ACTIVITY);
		if (AndroidAutomationTools.isAppDrawOverlayDisplayed()) {
			handlePermissionsPrompt();
			installLaunchApp(AndroidActivities.MAIN_ACTIVITY);
		}

		waitForAppLoad();
	}

	@SuppressWarnings("unchecked")
	protected void initializeAppiumDriver() throws MalformedURLException {
		Log.method("initializeAppiumDriver");
		appiumDriver =  (AppiumDriver<WebElement>) WebDriverFactory.getAndroidAppNativeDriver();
	}

	@SuppressWarnings("unchecked")
	protected void initializeAppiumWebDriver() throws MalformedURLException {
		Log.method("initializeAppiumWebDriver");
		appiumWebDriver =  (AppiumDriver<WebElement>) WebDriverFactory.getAndroidAppWebDriver();
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
		if (!AndroidAutomationTools.isPackageInstalled(WebDriverWrapper.ANDROID_APP_PACKAGE_NAME)) {
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
		navigateToMapScreen(waitForMapScreenLoad, TestContext.INSTANCE.getTestSetup().getLoginUser());
	}

	protected void navigateToMapScreen(boolean waitForMapScreenLoad, String username) throws Exception {
		final String backpackAddress = TestContext.INSTANCE.getTestSetup().getBackPackServerIpAddress();
		final String picServerAddress = TestContext.INSTANCE.getTestSetup().getBaseUrl();

		settingsScreen.saveSettings(backpackAddress, picServerAddress, username);

		if (waitForMapScreenLoad) {
			if (devMachineOverride) {
				TestContext.INSTANCE.stayIdle(PRE_DATA_PROCESSES_PAUSED_WAIT_TIME_IN_SECONDS);
			} else {
				mapScreen.waitForScreenLoad();
				Log.info("Map screen loaded successfully!");
			}
		}
	}

	public void waitForAppLoad() {
		settingsScreen.waitForFirstAppLoad();
	}
}