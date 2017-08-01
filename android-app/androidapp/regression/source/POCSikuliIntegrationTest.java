package androidapp.regression.source;

import static org.junit.Assert.assertTrue;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.support.PageFactory;

import androidapp.screens.source.AndroidSettingsScreen;
import common.source.BackPackAnalyzer;
import common.source.Log;
import common.source.Screenshotter;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class POCSikuliIntegrationTest extends BaseAndroidTest {

	protected AndroidSettingsScreen settingsScreen;

	private static ThreadLocal<Boolean> appiumTestInitialized = new ThreadLocal<Boolean>();

	@Rule
	public TestName testName = new TestName();

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
	 *	Proof of concept test for sikuli integration to create clip of desired bounds on the screen.
	**/
	@Test
	public void test_ImageClipGenerationTest() throws Exception {
		Log.info("\nRunning test_ImageClipGenerationTest ...");
		navigateToMapScreenUsingDefaultCreds(true /*waitForMapScreenLoad*/);
		TestContext.INSTANCE.stayIdle(30);    // allow time for heatmap data to process.
		executeWithBackPackDataProcessesPaused(obj -> {
			Log.info("Capturing screenshot...");
			Rectangle rect = new Rectangle();
			rect.setBounds(751, 1452, 120, 99);
			Screenshotter.captureCroppedWebDriverScreenshot(getAndroidDriver(), "MapScreen", rect);
			Screenshotter.captureWebDriverScreenshot(getAndroidDriver(), "MapScreenFull");
			Log.info("Screenshot captured successfully!");
			return true;
		});
	}

	/**
	 *	Proof of concept test for sikuli integration to detect images on screen. Valid image case.
	**/
	@Test
	public void test_ImageDetection_ValidImage() throws Exception {
		Log.info("\nRunning test_ImageDetection_ValidImage ...");
		final File imageFile = new File(TestSetup.getRootPath() + File.separator + "android-app" + File.separator + "data" + File.separator + "test-expected-data" +
				File.separator + "clips" + File.separator + "sikuli-poc-test" + File.separator + "heatmap.png");
		final Integer waitTimeInSecs = 30;
		navigateToMapScreenUsingDefaultCreds(true /*waitForMapScreenLoad*/);
		TestContext.INSTANCE.stayIdle(20);    // allow time for heatmap data to process.
		executeWithBackPackDataProcessesPaused(obj -> {
			Log.info(String.format("Detecting presence of image-'%s' on screen. Max wait time = %d secs.", imageFile, waitTimeInSecs));
			Rectangle imageBounds = mapScreen.getSikuliDriver().getImageBounds(imageFile, waitTimeInSecs);
			assertTrue(imageBounds != null);
			Log.info(String.format("Found image on screen at bounds -> [x=%,.2f, y=%,.2f, width=%,.2f, height=%,.2f]",
					imageBounds.getX(), imageBounds.getY(), imageBounds.getWidth(), imageBounds.getHeight()));
			return true;
		});
	}

	/**
	 *	Proof of concept test for sikuli integration to detect images on screen. Invalid image case.
	**/
	@Test
	public void test_ImageDetection_InvalidImage() throws Exception {
		Log.info("\nRunning test_ImageDetection_InvalidImage ...");
		final File imageFile = new File(TestSetup.getRootPath() + File.separator + "android-app" + File.separator + "data" + File.separator + "test-expected-data" +
				File.separator + "clips" + File.separator + "sikuli-poc-test" + File.separator + "invalid-image.png");
		final Integer waitTimeInSecs = 30;
		navigateToMapScreenUsingDefaultCreds(true /*waitForMapScreenLoad*/);
		TestContext.INSTANCE.stayIdle(20);    // allow time for heatmap data to process.
		executeWithBackPackDataProcessesPaused(obj -> {
			Log.info(String.format("Detecting presence of image-'%s' on screen. Max wait time = %d secs.", imageFile, waitTimeInSecs));
			Rectangle imageBounds = mapScreen.getSikuliDriver().getImageBounds(imageFile, waitTimeInSecs);
			if (imageBounds != null) {
				Log.info(String.format("Found image on screen at bounds -> [x=%,.2f, y=%,.2f, width=%,.2f, height=%,.2f]",
						imageBounds.getX(), imageBounds.getY(), imageBounds.getWidth(), imageBounds.getHeight()));
			}

			assertTrue(imageBounds == null);
			Log.info("As expected specified image was NOT found on screen.");
			return true;
		});
	}

	private void initializeTestScreenObjects() {
		initializeSettingsScreen();
	}

	protected void initializeSettingsScreen() {
		settingsScreen = new AndroidSettingsScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), settingsScreen);
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