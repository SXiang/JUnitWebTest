package surveyor.regression.androidapp.source;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import common.source.TestSetup;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class MainScreenTest {

	private AppiumDriver<WebElement> appiumDriver;

	@Before
	public void setUpBeforeTest() throws Exception {
		String apkFilePath = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "android\\app\\app-release.apk";
		File apkFile = new File(apkFilePath);

		// CAPABILITIES: https://appium.io/slate/en/master/?ruby#appium-server-capabilities
		DesiredCapabilities capabilities=DesiredCapabilities.android();

		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
		capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"Android Emulator");
		capabilities.setCapability(MobileCapabilityType.APP, apkFile.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,"Browser");
		capabilities.setCapability(MobileCapabilityType.VERSION, "7.1.1");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");    // timeout in seconds.
		capabilities.setCapability(MobileCapabilityType.ORIENTATION, "LANDSCAPE");
		capabilities.setCapability(MobileCapabilityType.FULL_RESET, "true");

		// Create object of URL class and specify the appium server address
		URL url= new URL("http://127.0.0.1:4723/wd/hub");

		// Create object of  AndroidDriver class and pass the url and capability that we created
		appiumDriver =  new AndroidDriver<WebElement>(url, capabilities);
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
