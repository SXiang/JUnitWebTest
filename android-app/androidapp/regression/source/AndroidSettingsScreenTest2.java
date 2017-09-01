package androidapp.regression.source;

import static org.junit.Assert.*;

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
import androidapp.screens.source.AndroidInvestigateMapScreen;
import androidapp.screens.source.AndroidInvestigateReportScreen;
import androidapp.screens.source.AndroidInvestigationScreen;
import common.source.BackPackAnalyzer;
import common.source.BaselineImages;
import common.source.ExceptionUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.scommon.source.SurveyorConstants;

public class AndroidSettingsScreenTest2 extends BaseReportTest {
	protected AndroidInvestigationScreen investigationScreen;
	protected AndroidInvestigateReportScreen investigateReportScreen;
	protected AndroidInvestigateMapScreen investigateMapScreen;

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
	 * Test Case ID: TC2548_EnergyBackpack_ScreenShowsCH4MeasurementByDefault
	 * Test Description: Energy Backpack - Screen shows CH4 measurement by default
	 * Script: -
	 *	- - Launch the Backpack app
	 * Results: -
	 *	- - Tablet displays the Map screen. The Concentration Chart appears in a small box at top left and current CH4 value appears in another box at top right.
	 *  - - At the top left of the latter box, HR appears, indicating that it is measuring High Range (CH4). The maximum CH4 value appears at the top right of this box.
	 */
	@Test
	public void TC2548_EnergyBackpack_ScreenShowsCH4MeasurementByDefault() throws Exception {
		Log.info("\nRunning TC2548_EnergyBackpack_ScreenShowsCH4MeasurementByDefault ...");
		navigateToMapScreenUsingDefaultCreds(true /*waitForMapScreenLoad*/);
		executeWithBackPackDataProcessesPaused(obj -> {
			mapScreen.assertConcentrationChartIsShown();
			mapScreen.assertMapIsLoaded();
			Log.info("Map screen loaded successfully!");

			final String actualMaxText = mapScreen.getMaxText();
			final String expectedMaxText = String.format("Max %s", "2.0 ppm");
			final String actualModeText = mapScreen.getModeText();
			final String expectedModeText = "Methane Mode";
			final String actualAmplitudeText = mapScreen.getAmplitudeText();
			final String expectedAmplitudeText = "2.0";

			assertTrue(String.format("Mode text is NOT correct. Expected=[%s]; Actual=[%s]", expectedModeText, actualModeText), actualModeText.equals(expectedModeText));
			if (!TestContext.INSTANCE.getTestSetup().isRunningOnBackPackAnalyzer()) {
				assertTrue(String.format("Max text is NOT correct. Expected=[%s]; Actual=[%s]", expectedMaxText, actualMaxText), actualMaxText.equals(expectedMaxText));
				assertTrue(String.format("Amplitude text is NOT correct. Expected=[%s]; Actual=[%s]", expectedAmplitudeText, actualAmplitudeText), actualAmplitudeText.equals(expectedAmplitudeText));
			} else {
				Float maxTextFloatValue = mapScreen.getMaxTextFloatValue();
				assertTrue(String.format("Max text should be > 0.9; Actual=[%f]", maxTextFloatValue), maxTextFloatValue > 0.9f);
				assertTrue(String.format("Amplitude should be > 0.9; Actual=[%s]", actualAmplitudeText), Float.valueOf(actualAmplitudeText) > 0.9f);
			}

			return true;
		});
	}

	/**
	 * Test Case ID: TC2549_EnergyBackpack_UserCanToggleBetweenHighRangeHighPrecisionModes
	 * Test Description: Energy Backpack - User can toggle between High Range and High Precision modes
	 * Script: -
	 *	- - Launch the Backpack app
	 *	- - Click the Toggle Mode button at the bottom of the screen
	 * Results: -
	 *	- - Tablet displays the Map screen. The current CH4 value appears in a box at top right. HR appears at the top right inside this box
	 *	- - HR changes to HP
	 */
	@Test
	public void TC2549_EnergyBackpack_UserCanToggleBetweenHighRangeHighPrecisionModes() throws Exception {
		Log.info("\nRunning TC2549_EnergyBackpack_UserCanToggleBetweenHighRangeHighPrecisionModes ...");

		navigateToMapScreenUsingDefaultCreds(true /*waitForMapScreenLoad*/);
		executeWithBackPackDataProcessesPaused(obj -> {
			mapScreen.assertMapIsLoaded();
			Log.info("Map screen loaded successfully!");

			try {
				final String actualAmplitudeText = mapScreen.getAmplitudeText();
				final String expectedAmplitudeText = "2.0";
				if (TestContext.INSTANCE.getTestSetup().isRunningOnBackPackAnalyzer()) {
					mapScreen.ensureAnalyzerIsInMethaneMode();
					mapScreen.clickOnToggleMode();
					assertTrue(String.format("Amplitude should be greater than 0.9. Actual=[%s]", actualAmplitudeText), Float.valueOf(actualAmplitudeText) > 0.9f);
				} else {
					assertTrue(String.format("Amplitude text is NOT correct. Expected=[%s]; Actual=[%s]", expectedAmplitudeText, actualAmplitudeText), actualAmplitudeText.equals(expectedAmplitudeText));
				}

				if (TestContext.INSTANCE.getTestSetup().isRunningOnBackPackAnalyzer()) {
					mapScreen.assertEthaneModeIsShownInTopPanel();
				}
			} catch (Exception ex) {
				fail(String.format("Test failed on exception -> %s", ExceptionUtility.getStackTraceString(ex)));
			} finally {
				// revert back to Methane mode
				if (TestContext.INSTANCE.getTestSetup().isRunningOnBackPackAnalyzer()) {
					mapScreen.ensureAnalyzerIsInMethaneMode();
				}
			}

			return true;
		});
	}

	/**
	 * Test Case ID: TC2551_EnergyBackpack_InvestigationScreenTablet
	 * Test Description: Energy Backpack - Investigation screen on tablet
	 * Script: -
	 *	- - Click on the Investigate button
	 *	- - Enter PCubed password
	 *	- - Launch the Backpack app
	 * Results: -
	 *	- - Tablet displays the Map screen
	 *	- - PCubed Login window pops up with Server and Username prepopulated. Password field shows Enter Password
	 *	- - List of Compliance Reports appears
	 */
	@Test
	public void TC2551_EnergyBackpack_InvestigationScreenTablet() throws Exception {
		Log.info("\nRunning TC2551_EnergyBackpack_InvestigationScreenTablet ...");

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
		executeWithBackPackDataProcessesPaused(obj -> {
			mapScreen.waitForScreenLoad();
			Log.info("Map screen loaded successfully!");

			mapScreen.clickOnInvestigate();
			initializeMapScreen();
			mapScreen.waitForLoginDialogToShow();

			mapScreen.assertEnterPasswordHintTextIsShown(BaselineImages.Folder.TC2551, BaselineImages.ImageFile.EnterPassword);

			final String actualUsername = mapScreen.getUsernameEditView().getText();
			final String expectedUsername = SurveyorConstants.SQAPICDR;
			assertTrue(String.format("Username is NOT correct. Expected=[%s]; Actual=[%s]", expectedUsername, actualUsername), actualUsername.equals(expectedUsername));

			mapScreen.enterPassword(SurveyorConstants.PICADMINPSWD);
			mapScreen.clickOnSubmit();
			investigationScreen.waitForScreenLoad();
			assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, SurveyorConstants.SQAPICDR));
			return true;
		});
	}

	private void initializeTestScreenObjects() {
		initializeInvestigationScreen();
		initializeInvestigateReportScreen();
		initializeInvestigateMapScreen();
	}

	private void initializeInvestigateReportScreen() {
		investigateReportScreen = new AndroidInvestigateReportScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigateReportScreen);
	}

	private void initializeInvestigationScreen() {
		investigationScreen = new AndroidInvestigationScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigationScreen);
	}

	private void initializeInvestigateMapScreen() {
		investigateMapScreen = new AndroidInvestigateMapScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigateMapScreen);
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