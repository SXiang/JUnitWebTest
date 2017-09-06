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

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import androidapp.dataprovider.AndroidSettingsDataProvider;
import androidapp.screens.source.AndroidConnectingToBackPackServerDialog;
import androidapp.screens.source.AndroidInvestigateMapScreen;
import androidapp.screens.source.AndroidInvestigateReportScreen;
import androidapp.screens.source.AndroidInvestigationScreen;
import common.source.BackPackAnalyzer;
import common.source.Log;
import common.source.TestContext;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.data.UserDataReader.UserDataRow;
import surveyor.scommon.source.SurveyorConstants;

public class AndroidSettingsAuthScreenTest extends BaseAndroidTest {
	private static final String INCORRECT_PASSWORD = "IncorrectPassword";
	protected AndroidInvestigationScreen investigationScreen;
	protected AndroidInvestigateReportScreen investigateReportScreen;
	protected AndroidInvestigateMapScreen investigateMapScreen;
	protected AndroidConnectingToBackPackServerDialog connectingDialog;

	private static LoginPageActions loginPageAction;

	private static ThreadLocal<Boolean> appiumTestInitialized = new ThreadLocal<Boolean>();

	@Rule
	public TestName testName = new TestName();

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects(false);
	}

	@Before
	public void beforeTest() throws Exception {
		initializePageActions();
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
	 * Initializes the page action objects.
	 * @throws Exception
	 */
	protected static void initializePageActions() throws Exception {
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
	}

	/**
	 * Test Case ID: TC2443_EnergyBackpackNetworkConfigurationSettingsIncorrectOrMissing
	 * Test Description: Energy Backpack Network Configuration settings incorrect or missing
	 * Script: -
	 *	- - Log into Backpack tablet
	 *	- - Launch Backpack app
	 *  - - Click Menu button at bottom right
	 *	- - Click App Settings button
	 *	- - Enter valid Backpack Server, Picarro Server and Username and click OK
	 * Results: -
	 *	- - If connecting to the Backpack server for the first time, or if network configuration is incorrect, user is prompted to configure settings
	 *	- - PicarroApp Settings screen appears.
	 *	- - If connection to Backpack server is successful, user is navigate to map centered on user's location
	 */
	// PARTIAL: Some test step updates in Rally. Make corresponding changes in automation code. Tracked by US4735.
	@Test
	@UseDataProvider(value = AndroidSettingsDataProvider.SETTINGS_DATA_PROVIDER_TC2443, location = AndroidSettingsDataProvider.class)
	public void TC2443_EnergyBackpackNetworkConfigurationSettingsIncorrectOrMissing(String testCaseID, Integer userDataRowID) throws Exception {
		Log.info("\nRunning TC2443_EnergyBackpackNetworkConfigurationSettingsIncorrectOrMissing ...");

		UserDataRow userDataRow = loginPageAction.getUsernamePassword(EMPTY, userDataRowID);

		final String backpackAddress = TestContext.INSTANCE.getTestSetup().getBackPackServerIpAddress();
		final String picServerAddress = TestContext.INSTANCE.getTestSetup().getBaseUrl();
		final String validPort = "3000";
		final String invalidPort = "3001";
		final String backpackAddressInvalidPort = backpackAddress.replace(validPort, invalidPort);
		mainLoginScreen.saveSettings(backpackAddressInvalidPort, picServerAddress, userDataRow.username);

		executeWithBackPackDataProcessesPaused(obj -> {
			TestContext.INSTANCE.stayIdle(PRE_DATA_PROCESSES_PAUSED_WAIT_TIME_IN_SECONDS);
			connectingDialog.assertConnectingToBackPackServerMessageIsShown();
			Log.info("'Connecting to backpack server' message was shown in Connecting dialog");
			connectingDialog.assertEditSettingsButtonIsShown();
			Log.info("Edit settings button was shown in Connecting dialog");
			assertTrue("'Connecting to backpack server' message shown in Connecting dialog is NOT correct.", connectingDialog.getConnectingLabelText().equals(
					String.format("Connecting to backpack server at %s, please wait!", backpackAddressInvalidPort)));
			return true;
		});
	}

	/**
	 * Test Case ID: TC2444_EnergyBackpackNetworkConfigurationSettingsCorrect
	 * Test Description: Energy Backpack Network Configuration settings correct
	 * Script: -
	 *	- - Log into Backpack tablet
	 *	- - Launch Backpack app
	 * Results: -
	 *	- - User is immediately navigated to map centered on user's location
	 */
	@Test
	@UseDataProvider(value = AndroidSettingsDataProvider.SETTINGS_DATA_PROVIDER_TC2444, location = AndroidSettingsDataProvider.class)
	public void TC2444_EnergyBackpackNetworkConfigurationSettingsCorrect(String testCaseID, Integer userDataRowID) throws Exception {
		Log.info("\nRunning TC2444_EnergyBackpackNetworkConfigurationSettingsCorrect ...");

		UserDataRow userDataRow = loginPageAction.getUsernamePassword(EMPTY, userDataRowID);

		navigateToMapScreen(true /*waitForMapScreenLoad*/, userDataRow.username);
		executeWithBackPackDataProcessesPaused(obj -> {
			mapScreen.waitForScreenLoad();
			mapScreen.assertMapIsCenteredForPicarroUser();
			return true;
		});
	}

	/**
	 *	Test Case: TC2686_EnergyBackpackPCubedAuthenticationError
	 *	Script:
	 *	- Log into the tablet and launch the Backpack app
	 *	- Click on the Investigate button at the bottom right of the screen
	 *	- Log in with invalid PCubed credentials
	 *	Expected Result:
	 *	- User is navigated to the map
	 *	- Tablet displays login screen for PCubed
	 *	- Login is unsuccessful. Message is displayed that authentication has failed
	**/
	@Test
	public void TC2686_EnergyBackpackPCubedAuthenticationError() throws Exception {
		Log.info("\nRunning TC2686_EnergyBackpackPCubedAuthenticationError ...");
		navigateToMapScreenUsingDefaultCreds(true /*waitForMapScreenLoad*/);
		executeWithBackPackDataProcessesPaused(obj -> {
			mapScreen.waitForScreenLoad();
			Log.info("Map screen loaded successfully!");

			mapScreen.clickOnInvestigate();
			initializeMapScreen();
			mapScreen.waitForLoginDialogToShow();
			mapScreen.enterPassword(INCORRECT_PASSWORD);
			mapScreen.clickOnSubmit();
			final String expectedLoginValidationText = SurveyorConstants.INVALIDUSERPASS;
			final String actualLoginValidationText = mapScreen.getLoginValidationLabelText().trim();
			assertTrue(String.format("Incorrect login validation text found. Expected=[%s]; Actual=[%s]", expectedLoginValidationText, actualLoginValidationText),
					expectedLoginValidationText.equals(actualLoginValidationText));
			return true;
		});
	}

	private void initializeTestScreenObjects() {
		initializeInvestigationScreen();
		initializeInvestigateReportScreen();
		initializeInvestigateMapScreen();
		initializeConnectingDialog();
	}

	private void initializeConnectingDialog() {
		connectingDialog = new AndroidConnectingToBackPackServerDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), connectingDialog);
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