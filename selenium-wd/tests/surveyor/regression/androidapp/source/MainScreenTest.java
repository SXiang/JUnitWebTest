package surveyor.regression.androidapp.source;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
import common.source.TestSetup;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import surveyor.scommon.androidapp.source.AndroidMainScreen;

public class MainScreenTest {

	private AppiumDriver<WebElement> appiumDriver;
	private AndroidMainScreen mainScreen;

	@Before
	public void setUpBeforeTest() throws Exception {
		initializeAppiumDriver();

	    initializeScreenObjects();
	}

	private void initializeAppiumDriver() throws IOException, MalformedURLException {
		String apkFilePath = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "android\\app\\app-release.apk";
		File apkFile = new File(apkFilePath);

		// CAPABILITIES: https://appium.io/slate/en/master/?ruby#appium-server-capabilities
		DesiredCapabilities capabilities=DesiredCapabilities.android();

		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
		capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Android Emulator");
		capabilities.setCapability(MobileCapabilityType.APP, apkFile.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.VERSION, "7.1.1");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");    // timeout in seconds.
		capabilities.setCapability(MobileCapabilityType.ORIENTATION, "LANDSCAPE");
		capabilities.setCapability(MobileCapabilityType.FULL_RESET, "true");

		// Create object of URL class and specify the appium server address
		URL url= new URL("http://127.0.0.1:4723/wd/hub");

		// Create object of  AndroidDriver class and pass the url and capability that we created
		appiumDriver =  new AndroidDriver<WebElement>(url, capabilities);
	}

	private void initializeScreenObjects() {
		// Time out is set because test can be run on slow Android SDK emulator
		mainScreen = new AndroidMainScreen();
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, 5, TimeUnit.SECONDS), mainScreen);
	}

	@Test
	public void test_MobileDriverLaunch() {
		Log.info("Verifying MobileDriverLaunch ...");
		assertTrue("Appium Driver was NOT initialized correctly.", appiumDriver != null);
		appiumDriver.quit();
	}

	@Test
	public void test_MainScreenInteractions() {
		assertTrue("Object should NOT be NULL", mainScreen.mainFrameLayout != null);
		assertTrue("Object should NOT be NULL", mainScreen.mainViewGroups != null);
		assertTrue("Object should NOT be NULL", mainScreen.scrollableView != null);
	}
}
