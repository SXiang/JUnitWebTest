package androidapp.regression.source;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.Timeout;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import androidapp.screens.source.AndroidInvestigationScreen;
import surveyor.scommon.source.SurveyorTestRunner;
import androidapp.entities.source.InvestigationMarkerEntity;
import androidapp.entities.source.InvestigationEntity;
import androidapp.screens.source.AndroidInvestigateMapScreen;
import androidapp.screens.source.AndroidInvestigateReportScreen;

@RunWith(SurveyorTestRunner.class)
public class POCInvestigateReportTest {

	private AppiumDriver<WebElement> appiumDriver;
	private AndroidInvestigateReportScreen investigateReportScreen;
	private AndroidInvestigationScreen investigationScreen;
	private AndroidInvestigateMapScreen investigateMapScreen;

	@Before
	public void setUpBeforeTest() throws Exception {
		initializeAppiumDriver();

	    initializeScreenObjects();
	}

	@After
	public void tearDownAfterTest() throws Exception {
		cleanUp();
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
		investigateReportScreen = new AndroidInvestigateReportScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigateReportScreen);

		investigationScreen = new AndroidInvestigationScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigationScreen);

		investigateMapScreen = new AndroidInvestigateMapScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigateMapScreen);
	}

	private void cleanUp() {
		appiumDriver.quit();
	}

	@Test
	public void test_MobileDriverLaunch() {
		Log.info("Executing test_MobileDriverLaunch ...");
		assertTrue("Appium Driver was NOT initialized correctly.", appiumDriver != null);
	}

	@Test
	public void test_ScreenInteractions() {
		Log.info("Executing test_ScreenInteractions ...");

		investigateReportScreen.waitForScreenLoad();
		List<InvestigationMarkerEntity> investigationReports = investigateReportScreen.getInvestigationMarkers();
		Log.info(String.format("Found %d investigation reports", investigationReports.size()));
		assertTrue("Investigation reports should be displayed", investigationReports != null && investigationReports.size() > 1);

		investigateReportScreen.clickOnFirstInvestigationMarker();
		investigationScreen.waitForScreenLoad();
		List<InvestigationEntity> investigations = investigationScreen.getInvestigations();
		Log.info(String.format("Found %d investigations", investigations.size()));
		assertTrue("Investigation should be displayed", investigations != null && investigations.size() > 1);

		investigationScreen.clickOnFirstInvestigation();
		investigateMapScreen.waitForScreenLoad();
		assertTrue("Follow button should be displayed.", investigateMapScreen.getFollowButton().isDisplayed());
		assertTrue("Investigate button should be displayed", investigateMapScreen.getInvestigateButton().isDisplayed());

		investigateMapScreen.clickOnFollow();

		// wait for the popup to load, before ending test.
		TestContext.INSTANCE.stayIdle(5);
	}
}