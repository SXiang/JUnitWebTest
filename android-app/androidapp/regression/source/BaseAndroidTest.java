package androidapp.regression.source;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.support.PageFactory;

import com.android.ddmlib.IDevice;

import androidapp.screens.source.AndroidMapScreen;
import androidapp.screens.source.AndroidMainLoginScreen;
import common.source.AdbInterface;
import common.source.AndroidAutomationTools;
import common.source.AppConstants;
import common.source.BackPackAnalyzer;
import common.source.BaseHelper;
import common.source.CheckedPredicate;
import common.source.EnumUtility;
import common.source.ExceptionUtility;
import common.source.FileUtility;
import common.source.FunctionUtil;
import common.source.Log;
import common.source.LogCollector;
import common.source.MobileActions;
import common.source.NetworkEmulation;
import common.source.NetworkEmulation.NetworkDelay;
import common.source.NetworkEmulation.NetworkSpeed;
import common.source.NumberUtility;
import common.source.PerfmonDataCollector;
import common.source.ProcessUtility;
import common.source.RegexUtility;
import common.source.ScreenRecorder;
import common.source.ServiceCorrector;
import common.source.ServiceCorrector.ServiceInfo;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.Timeout;
import common.source.WebDriverFactory;
import common.source.WebDriverWrapper;
import common.source.AndroidAutomationTools.ShellCommands;
import common.source.AndroidDeviceInterface;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.scommon.actions.BaseActions;
import surveyor.scommon.source.BaseTest;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class BaseAndroidTest extends BaseTest {
	private static final String SYSTEM_PROP_RO_BUILD_CHARACTERISTICS = "ro.build.characteristics";
	private static final String SIM_DATA_MANAGER_BROADCASTER_PY = "simDataManagerBroadcaster.py";
	private static final String SIM_LINEAR_FITTER_BROADCASTER_PY = "simLinearFitterBroadcaster.py";
	private static final String DUMMYLINEARFITTERALARM_PY = "dummylinearfitteralarm.py";
	private static final String ODORCALL_SERVER_PY = "odorcallServer.py";
	private static final String DUMMYINSTRMGR_PY = "dummyinstrmgr.py";
	private static final String PYTHON_EXE = "python.exe";
	private static final double DEFAULT_ALTITUDE = 0.0;
	private static final double DEFAULT_LONGITUDE = -121.9863994;
	private static final double DEFAULT_LATITUDE = 37.3965775;
	private static final String APK_VERSION_MARKER_FILE_PATH = "C:\\QATestLogs\\installed-apk.md";
	private static final String LOGS_BASE_FOLDER = "C:\\QATestLogs";
	private static final String TABLET = "tablet";
	private static final String ADB_EXE = "adb.exe";

	protected static final String TRUE = "true";
	protected static final String EMPTY = BaseActions.EMPTY;
	protected static final Integer NOTSET = BaseActions.NOTSET;

	private boolean isLoggingEnabled = false;

	private static ThreadLocal<Boolean> reactNativeInitStatus = new ThreadLocal<Boolean>() {
	    @Override
	    protected Boolean initialValue() {
	    	return false;
	    }
	};

	protected static Integer PRE_DATA_PROCESSES_PAUSED_WAIT_TIME_IN_SECONDS = 2;

	protected AppiumDriver<WebElement> appiumDriver;
	protected AppiumDriver<WebElement> appiumWebDriver;
	protected AndroidMainLoginScreen mainLoginScreen;
	protected AndroidMapScreen mapScreen;

	private PerfmonDataCollector perfmonCollector;
	private ServiceCorrector serviceCorrector;
	private ScreenRecorder screenRecorder;
	private LogCollector logCollector;

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
		Log.method("setUpBeforeTestClass");

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
			detectTargetDeviceType();

			if (TestContext.INSTANCE.isRunningOnAndroidDevice()) {
				AndroidAutomationTools.startAppiumServer();
			} else {
				NetworkEmulation networkEmulation = detectCreateNetworkEmulation();
				if (networkEmulation == null) {
					AndroidAutomationTools.start();
				} else {
					AndroidAutomationTools.start(networkEmulation);
				}

			    AndroidAutomationTools.disableAnimations();  // perf optimization.
			}
		}
	}

	private static NetworkEmulation detectCreateNetworkEmulation() {
		TestSetup testSetup = TestContext.INSTANCE.getTestSetup();
		if (!testSetup.isAndroidNetworkThrottleEnabled()) {
			return null;
		}

		String tcpDumpFile = String.format("%s\\%d-%s.tcpdump.cap", LOGS_BASE_FOLDER, getRunUUID(), TestSetup.getUUIDString());

		String delayValue = testSetup.getAndroidNetworkDelay().trim();
		String speedValue = testSetup.getAndroidNetworkSpeed().trim();

		Integer iDelay = 0;
		Integer iMinDelay = 0;
		Integer iMaxDelay = 0;
		Integer iSpeed = 0;
		Integer iUpSpeed = 0;
		Integer iDownSpeed = 0;

		if (delayValue.contains(":")) {
			List<String> delayValueParts = RegexUtility.split(delayValue, RegexUtility.COLON_SPLIT_REGEX_PATTERN);
			iMinDelay = NumberUtility.getIntegerValueOf(delayValueParts.get(0));
			iMaxDelay = NumberUtility.getIntegerValueOf(delayValueParts.get(1));
		} else {
			iDelay = NumberUtility.getIntegerValueOf(delayValue);
		}

		if (speedValue.contains(":")) {
			List<String> speedValueParts = RegexUtility.split(speedValue, RegexUtility.COLON_SPLIT_REGEX_PATTERN);
			iUpSpeed = NumberUtility.getIntegerValueOf(speedValueParts.get(0));
			iDownSpeed = NumberUtility.getIntegerValueOf(speedValueParts.get(1));
		} else {
			iSpeed = NumberUtility.getIntegerValueOf(speedValue);
		}

		NetworkEmulation emulation = createNetworkEmulation(tcpDumpFile, delayValue, speedValue, iDelay, iMinDelay, iMaxDelay, iSpeed, iUpSpeed, iDownSpeed);
		TestContext.INSTANCE.getTestSetup().setNetworkEmulation(emulation);
		return emulation;
	}

	private static NetworkEmulation createNetworkEmulation(String tcpDumpFile, String delayValue, String speedValue, Integer iDelay,
			Integer iMinDelay, Integer iMaxDelay, Integer iSpeed, Integer iUpSpeed, Integer iDownSpeed) {
		NetworkDelay delay = NetworkDelay.NONE;
		NetworkSpeed speed = NetworkSpeed.FULL;
		if (iMinDelay>0 && iMaxDelay>0 && iUpSpeed>0 && iDownSpeed>0) {
			return NetworkEmulation.createNew(iMinDelay, iMaxDelay, iUpSpeed, iDownSpeed, tcpDumpFile);
		} else if (iMinDelay>0 && iMaxDelay>0) {
			speed = EnumUtility.fromName(speedValue, () -> NetworkEmulation.NetworkSpeed.values());
			return NetworkEmulation.createNew(speed, iMinDelay, iMaxDelay, tcpDumpFile);
		} else if (iUpSpeed>0 && iDownSpeed>0) {
			delay = EnumUtility.fromName(delayValue, () -> NetworkEmulation.NetworkDelay.values());
			return NetworkEmulation.createNew(delay, iUpSpeed, iDownSpeed, tcpDumpFile);
		} else if (iDelay>0 && iSpeed>0) {
			return NetworkEmulation.createNew(iDelay, iSpeed, tcpDumpFile);
		} else if (iDelay>0) {
			speed = EnumUtility.fromName(speedValue, () -> NetworkEmulation.NetworkSpeed.values());
			return NetworkEmulation.createNew(speed, iDelay, tcpDumpFile);
		} else if (iSpeed>0) {
			delay = EnumUtility.fromName(delayValue, () -> NetworkEmulation.NetworkDelay.values());
			return NetworkEmulation.createNew(delay, iSpeed, tcpDumpFile);
		} else {
			delay = EnumUtility.fromName(delayValue, () -> NetworkEmulation.NetworkDelay.values());
			speed = EnumUtility.fromName(speedValue, () -> NetworkEmulation.NetworkSpeed.values());
			return NetworkEmulation.createNew(delay, speed, tcpDumpFile);
		}
	}

	private static void detectTargetDeviceType() throws Exception {
		if (areMultipleDevicesConnected()) {
			throw new Exception("Detected multiple connected devices. Current configuration supports automation run on a single device. Disconnect extra connected devices.");
		}

		IDevice device = AdbInterface.getConnectedDevice();
		if (device != null) {
			String buildCharacteristic = device.getProperty(SYSTEM_PROP_RO_BUILD_CHARACTERISTICS);
			TestContext.INSTANCE.setIsRunningOnAndroidDevice(buildCharacteristic.toLowerCase().equals(TABLET));
		}
	}

	private static boolean areMultipleDevicesConnected() {
		IDevice[] devices = AdbInterface.getConnectedDevices();
		if (devices != null) {
			return (devices.length > 1);
		}

		return false;
	}

	@AfterClass
	public static void tearDownAfterTestClass() throws Exception {
		Log.method("tearDownAfterTestClass");
		AdbInterface.stop();
		cleanupProcesses();
	}

	@Before
	public void setupBeforeTest() throws Exception {
		AdbInterface.reset();      // reset to minimize adb hangs leading to appium server hangs.
	}

	@After
	public void tearDownAfterTest() throws MalformedURLException, IOException {
	}

	@Override
	public void onTestFailureProcessing() {
		Log.method("onTestFailureProcessing");
		Log.info(getAndroidDriver().getPageSource());
	}

	@Override
	public void onTestSuccessProcessing() {
	}

	@Override
	public void postTestMethodProcessing() {
		cleanUp();
	}

	public void startTestRecording(String testName) throws Exception {
		Log.method("startTestRecording", testName);
		startTestRecording(testName, true /*enableLogging*/);
	}

	public void stopTestRecording(String testName) throws Exception {
		Log.method("stopTestRecording", testName);
		testName = BaseHelper.toAlphaNumeric(testName, '_');
		if (TestContext.INSTANCE.getTestSetup().isAndroidTestPerfMetricsEnabled()) {
			collectPerfmonMetrics(testName);
		}

		stopServiceCorrectors(testName);
		createRecording(testName);

		if (isLoggingEnabled) {
			collectAdbLogs(testName);
		}
	}

	private void stopServiceCorrectors(String testName) throws Exception {
		if (!TestContext.INSTANCE.getTestSetup().isRunningOnBackPackAnalyzer()) {
			if (TestContext.INSTANCE.getTestSetup().isAndroidTestServiceCorrectorsEnabled()) {
				collectServiceInfos(testName);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	protected AndroidDriver getAndroidDriver() {
		return (AndroidDriver)appiumDriver;
	}

	protected void dumpSysActivity() {
		try {
			String dumpSysActivity = AdbInterface.executeShellCmd(AdbInterface.getAdbLocation(), ShellCommands.DUMPSYS_ACTIVITY);
			Log.info(String.format("DumpSys activity -> %s", dumpSysActivity));
		} catch (Exception e) {
			Log.error(String.format("Error in dumpSysActivity() -> %s", ExceptionUtility.getStackTraceString(e)));
		}
	}

	private void initPerfmonDataCollector() {
		if (perfmonCollector == null) {
			perfmonCollector = new PerfmonDataCollector();
		}
	}

	private void initServiceCorrector() {
		if (serviceCorrector == null) {
			serviceCorrector = new ServiceCorrector();
		}
	}

	private void initScreenRecorder() {
		if (screenRecorder == null) {
			screenRecorder = new ScreenRecorder();
		}
	}

	private void initLogCollector() {
		if (logCollector == null) {
			logCollector = new LogCollector();
		}
	}

	private static Long getRunUUID() {
		Long runUUID = TestContext.INSTANCE.getTestSetup().getRunUUID();
		if (runUUID == null) {
			return 0L;
		}

		return runUUID;
	}

	private void startTestRecording(String testName, Boolean enableLogging) throws Exception {
		Log.method("startTestRecording", testName, enableLogging);
		testName = BaseHelper.toAlphaNumeric(testName, '_');
		initScreenRecorder();
		screenRecorder.startRecording(String.format("/sdcard/%s-%d.mp4", testName, getRunUUID()));

		if (TestContext.INSTANCE.getTestSetup().isAndroidTestPerfMetricsEnabled()) {
			initPerfmonDataCollector();
			perfmonCollector.startCollectors();
		}

		startServiceCorrectors();

		if (enableLogging) {
			initLogCollector();
			logCollector.startLogging(appiumDriver);
			isLoggingEnabled = true;
		}
	}

	private void startServiceCorrectors() {
		if (!TestContext.INSTANCE.getTestSetup().isRunningOnBackPackAnalyzer()) {
			if (TestContext.INSTANCE.getTestSetup().isAndroidTestServiceCorrectorsEnabled()) {
				initServiceCorrector();
				List<ServiceInfo> expectedServices = new ArrayList<ServiceInfo>();
				expectedServices.add(new ServiceInfo(PYTHON_EXE, DUMMYINSTRMGR_PY, null, getBackPackSimulatorServiceInfoPredicate()));
				expectedServices.add(new ServiceInfo(PYTHON_EXE, ODORCALL_SERVER_PY, null, getBackPackSimulatorServiceInfoPredicate()));
				expectedServices.add(new ServiceInfo(PYTHON_EXE, DUMMYLINEARFITTERALARM_PY, null, getBackPackSimulatorServiceInfoPredicate()));
				expectedServices.add(new ServiceInfo(PYTHON_EXE, SIM_LINEAR_FITTER_BROADCASTER_PY, null, getBackPackSimulatorServiceInfoPredicate()));
				expectedServices.add(new ServiceInfo(PYTHON_EXE, SIM_DATA_MANAGER_BROADCASTER_PY, null, getBackPackSimulatorServiceInfoPredicate()));
				serviceCorrector.startCorrector(expectedServices.toArray(new ServiceInfo[expectedServices.size()]));
			}
		}
	}

	private void collectPerfmonMetrics(String testName) throws Exception {
		Log.method("collectPerfmonMetrics", testName);
		String gfxDataFile = String.format(LOGS_BASE_FOLDER + "\\%s", String.format("%s.perf.gfx.dat", testName));
		String cpuDataFile = String.format(LOGS_BASE_FOLDER + "\\%s", String.format("%s.perf.cpu.dat", testName));

		if(FileUtility.fileExists(gfxDataFile)) {
			FileUtility.deleteFile(Paths.get(gfxDataFile));
		}

		if(FileUtility.fileExists(cpuDataFile)) {
			FileUtility.deleteFile(Paths.get(cpuDataFile));
		}

		List<String> gfxMetrics = this.perfmonCollector.getGfxMetrics();
		List<String> cpuMetrics = this.perfmonCollector.getCpuMetrics();

		List<String> allGfxMetrics = new ArrayList<String>();
		gfxMetrics.stream()
			.map(e -> e.split(BaseHelper.getLineSeperator()))
			.forEach(arr -> {
				for (String str : arr) {
					allGfxMetrics.add(str);
				}
			});

		List<String> allCpuMetrics = new ArrayList<String>();
		cpuMetrics.stream()
			.map(e -> e.split(BaseHelper.getLineSeperator()))
			.forEach(arr -> {
				for (String str : arr) {
					allCpuMetrics.add(str);
				}
			});

		Log.info("Stop perfmon data collectors");
		perfmonCollector.stopCollectors();

		Log.info(String.format("Writing gfx metrics data to - '%s'", gfxDataFile));
		FileUtility.writeToFile(gfxDataFile, allGfxMetrics.toArray(new String[allGfxMetrics.size()]));

		Log.info(String.format("Writing cpu metrics data to - '%s'", cpuDataFile));
		FileUtility.writeToFile(cpuDataFile, allCpuMetrics.toArray(new String[allCpuMetrics.size()]));
	}

	private void collectServiceInfos(String testName) throws Exception {
		Log.method("collectServiceInfos", testName);
		String svcInfoFile = String.format(LOGS_BASE_FOLDER + "\\%s", String.format("%s-%d.svcinfo.dat", testName, getRunUUID()));

		if(FileUtility.fileExists(svcInfoFile)) {
			FileUtility.deleteFile(Paths.get(svcInfoFile));
		}

		Log.info("Stop service corrector");
		this.serviceCorrector.stopCorrector();

		List<String> svcInfos = this.serviceCorrector.getRunningServices();

		Log.info(String.format("Writing service info to - '%s'", svcInfoFile));
		FileUtility.writeToFile(svcInfoFile, svcInfos.toArray(new String[svcInfos.size()]));
	}

	private void collectAdbLogs(String testName) throws IOException {
		Log.method("collectAdbLogs", testName);
		String logcatLog = String.format(LOGS_BASE_FOLDER + "\\%s", String.format("%s-%d.log", testName, getRunUUID()));
		if(FileUtility.fileExists(logcatLog)) {
			FileUtility.deleteFile(Paths.get(logcatLog));
		}

		Log.info(String.format("Writing adb logs to - '%s'", logcatLog));
		String log = logCollector.grabLogs();

		Log.info("Stop logging");
		logCollector.stopLogging();
		FileUtility.createTextFile(Paths.get(logcatLog), log);
	}

	private void createRecording(String testName) throws Exception {
		Log.method("createRecording", testName);
		MobileActions action = MobileActions.newAction();
		String videoFileName = String.format("%s-%d.mp4", testName, getRunUUID());
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

	// Perf optimization. pause simulator processes to prevent delay in fetching element using Appium driver.
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

		if (TestContext.INSTANCE.getTestSetup().isAndroidTestWebSocketPauseResumeEnabled()) {
			BackPackAnalyzer.pauseDataProcesses();
		}

		retVal = predicate.test(null);

		if (TestContext.INSTANCE.getTestSetup().isAndroidTestWebSocketPauseResumeEnabled()) {
			BackPackAnalyzer.resumeDataProcesses();
		}

		return retVal;
	}

	private Predicate<String> getBackPackSimulatorServiceInfoPredicate() {
		Predicate<String> predicate = el -> {
			FunctionUtil.warnOnError(() -> BackPackAnalyzer.restartSimulator());
			return true;
		};

		return predicate;
	}

	private static void cleanupProcesses() throws Exception {
		Log.method("cleanupProcesses");

		if (!TestContext.INSTANCE.getTestSetup().isRunningOnBackPackAnalyzer()) {
			BackPackAnalyzer.stopSimulator();
		}

		if (TestContext.INSTANCE.isRunningOnAndroidDevice()) {
			AndroidAutomationTools.stopAppiumServer();
		} else {
			AndroidAutomationTools.stop();
		}

		ProcessUtility.killProcess(ADB_EXE, false /*killChildProcesses*/);

		TestContext.INSTANCE.stayIdle(3);    // restarting processes immediately after cleanup could give errors.
	}

	protected void cleanUp() {
		if (appiumDriver != null) {
			FunctionUtil.warnOnError(() -> appiumDriver.quit());
		}

		if (appiumWebDriver != null) {
			FunctionUtil.warnOnError(() -> appiumWebDriver.quit());
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
		mainLoginScreen = new AndroidMainLoginScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), mainLoginScreen);
	}

	protected void initializeMapScreen() {
		mapScreen = new AndroidMapScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), mapScreen);
	}

	protected void initializeAppiumTest() throws MalformedURLException, IOException, Exception {
		Log.method("initializeAppiumTest");
		if (TestContext.INSTANCE.isRunningOnAndroidDevice()) {
			ensureDeviceIsTurnedOnAndUnlocked();
		}

		ensureApkExistsInConnectedDevice();
		installApkFileVersionMarkerOnDevice();
		initializeAppiumDriver();
		setDefaultLocation();
		if (!TestContext.INSTANCE.getTestSetup().isAndroidTestReleaseEnabled()) {
			startReactNativePackager();
		}

		AdbInterface.clearAppCache(AppConstants.APP_PACKAGE_NAME);
		AdbInterface.grantPermissions(AppConstants.APP_PACKAGE_NAME, AppConstants.APP_NEEDED_PERMISSIONS);
		installLaunchApp(AndroidActivities.APP_DRAW_OVERLAY_SETTINGS_ACTIVITY);
		if (AndroidAutomationTools.isAppDrawOverlayDisplayed()) {
			handlePermissionsPrompt();
			installLaunchApp(AndroidActivities.MAIN_ACTIVITY);
		}

		waitForAppLoad();
	}

	private void ensureDeviceIsTurnedOnAndUnlocked() throws Exception {
		Log.method("ensureDeviceIsTurnedOnAndUnlocked");
		String pin = TestContext.INSTANCE.getTestSetup().getAndroidDevicePin();
		if (AndroidDeviceInterface.isScreenTurnedOff()) {
			AndroidDeviceInterface.turnOnScreen();
			AndroidDeviceInterface.unlockDevice();
			AndroidDeviceInterface.enterPin(pin);
		} else {
			if (AndroidDeviceInterface.isDeviceLocked()) {
				AndroidDeviceInterface.unlockDevice();
				AndroidDeviceInterface.enterPin(pin);
			}
		}
	}

	private void installApkFileVersionMarkerOnDevice() throws IOException {
		Log.method("installApkFileVersionMarkerOnDevice");
		String apkFilename = getApkFile().getName();
		String installMarkerFile = APK_VERSION_MARKER_FILE_PATH;
		FileUtility.createOrWriteToExistingTextFile(Paths.get(installMarkerFile), apkFilename);
		AdbInterface.pushFile(installMarkerFile, "/sdcard/installed-apk.md");
	}

	@SuppressWarnings("unchecked")
	protected void initializeAppiumDriver() throws IOException {
		Log.method("initializeAppiumDriver");
		// Server must be running, else webdriver creation will fail.
		AndroidAutomationTools.ensureAppiumServerIsRunning();
		appiumDriver =  (AppiumDriver<WebElement>) WebDriverFactory.getAndroidAppNativeDriver(TestContext.INSTANCE.isRunningOnAndroidDevice());
	}

	@SuppressWarnings("unchecked")
	protected void initializeAppiumWebDriver() throws MalformedURLException {
		Log.method("initializeAppiumWebDriver");
		appiumWebDriver =  (AppiumDriver<WebElement>) WebDriverFactory.getAndroidAppWebDriver(TestContext.INSTANCE.isRunningOnAndroidDevice());
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

	private void setDefaultLocation() {
		appiumDriver.setLocation(new Location(DEFAULT_LATITUDE, DEFAULT_LONGITUDE, DEFAULT_ALTITUDE));
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

		mainLoginScreen.saveSettings(backpackAddress, picServerAddress, username);

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
		mainLoginScreen.waitForFirstAppLoad();
	}
}