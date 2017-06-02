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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import androidapp.screens.source.AndroidMapScreen;
import androidapp.screens.source.AndroidSettingsScreen;
import common.source.AndroidAutomationTools;
import common.source.BackPackSimulator;
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
	protected AppiumDriver<WebElement> appiumDriver;

	protected AndroidSettingsScreen settingsScreen;
	protected AndroidMapScreen mapScreen;

	private static final String APPIUM_SERVER_HUB_HOST = "http://127.0.0.1:4723/wd/hub";

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
	    BackPackSimulator.restartSimulator();
	    AndroidAutomationTools.restart();
	}

	@AfterClass
	public static void tearDownAfterTestClass() throws Exception {
		BackPackSimulator.stopSimulator();
		AndroidAutomationTools.stop();
	}

	@Before
	public void setupBeforeTest() throws MalformedURLException, IOException {
	}

	@After
	public void tearDownBeforeTest() throws MalformedURLException, IOException {
		cleanUp();
	}

	protected void handleAppPermissionsPrompt() {
		List<WebElement> permissionPrompts = appiumDriver.findElements(MobileBy.xpath("//*[@class='android.widget.Switch']"));
		if (permissionPrompts.size() > 0) {
			permissionPrompts.get(0).click();
		}
	}

	protected void initializeAppiumDriver() throws MalformedURLException {
		// CAPABILITIES: https://appium.io/slate/en/master/?ruby#appium-server-capabilities, https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/caps.md
		DesiredCapabilities capabilities=DesiredCapabilities.android();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
		capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Android Emulator");
		// NOTE: autoGrantPermissions capability is NOT working along with MobileCapabilityType.APP. Use appPackage and appActivity instead of app.
		capabilities.setCapability("appPackage", "com.picarroapp");
		capabilities.setCapability("appActivity", ".MainActivity");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");    // timeout in seconds.
		capabilities.setCapability("autoGrantPermissions", "true");

		// Create object of URL class and specify the appium server address
		URL url= new URL(APPIUM_SERVER_HUB_HOST);

		// Create object of  AndroidDriver class and pass the url and capability that we created
		appiumDriver =  new AndroidDriver<WebElement>(url, capabilities);
	}

	protected void installLaunchApp() throws IOException {
		Path apkFolderPath = Paths.get(TestSetup.getRootPath(), "apk");
		List<String> apkFiles = FileUtility.getFilesInDirectory(apkFolderPath, "*.apk");
		if (apkFiles == null || apkFiles.size() == 0) {
			throw new IOException(String.format("No APK files found in API directory - '%s'", apkFolderPath));
		}

		String apkFilePath = apkFiles.get(0);
		File apkFile = new File(apkFilePath);
		boolean addBundleFetchTime = false;
		if (apkFilePath.toLowerCase().contains("debug")) {
			addBundleFetchTime = true;
		}

		if (appiumDriver != null) {
			appiumDriver.installApp(apkFile.getAbsolutePath());
			appiumDriver.launchApp();
			initializeScreenObjects();
			waitForAppLoad(addBundleFetchTime);
		}
	}

	private void waitForAppLoad(boolean addBundleFetchTime) {
		settingsScreen.waitForFirstAppLoad();
		if (addBundleFetchTime) {
			//TestContext.INSTANCE.stayIdle(Timeout.ANDROID_APP_FIRST_APP_LOAD_TIMEOUT);
		}
	}

	private void initializeScreenObjects() {
		// Time out is set because test can be run on slow Android SDK emulator
		settingsScreen = new AndroidSettingsScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), settingsScreen);

		mapScreen = new AndroidMapScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), mapScreen);
	}

	protected void cleanUp() {
		appiumDriver.quit();
	}
}