package androidapp.regression.source;

import static org.junit.Assert.assertTrue;

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

import androidapp.dataprovider.AndroidErrorCasesDataProvider;
import androidapp.screens.source.AndroidInvestigateMapScreen;
import androidapp.screens.source.AndroidInvestigateReportScreen;
import androidapp.screens.source.AndroidInvestigationScreen;
import androidapp.screens.source.AndroidSettingsScreen;
import androidapp.screens.source.AndroidSettingsScreen.ParentScreen;
import common.source.BackPackAnalyzer;
import common.source.Log;
import common.source.TestContext;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.data.UserDataReader.UserDataRow;
import surveyor.scommon.source.SurveyorConstants;

public class AndroidLoginErrorCasesTest extends BaseReportTest {
	private static final String LOGIN_FAILED = "Login Failed";
	protected AndroidInvestigationScreen investigationScreen;
	protected AndroidInvestigateReportScreen investigateReportScreen;
	protected AndroidInvestigateMapScreen investigateMapScreen;
	protected AndroidSettingsScreen settingsScreen;

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
		initializeTestDriver();
		initializeTestScreenObjects();
		initializePageActions();
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
	 * Test Case ID: TC2723_EnergyBackpack_IncorrectUsernamePcubedLogin
	 * Test Description:
	 * Script: -
	 *	- - Log into the tablet and launch the Backpack app
	 *	- - Click the Investigate button at bottom right
	 *	- - Enter valid credentials for PCubed annd click OK
	 *	- - Click Menu button at bottom right
	 *	- - Click Logout
	 *	- - Click Investigate button again
	 *	- - Enter same credentials as before, but remove one letter from Username and click OK
	 * Results: -
	 *	- - Tablet displays map centered on backpack's location
	 *	- - Dialog appears where user can enter Username and PW for PCubed
	 *	- - Tablet displays a list of Compliance Reports
	 *	- - Menu appears with Alarm Settings, App Settings, Logout and Shutdown Backpack buttons
	 *	- - Tablet returns to homepage
	 *	- - Login dialog appears again
	 *	- - Message Username or password not recognized appears in red font
	 */
	@Test
	@UseDataProvider(value = AndroidErrorCasesDataProvider.ERROR_CASES_DATA_PROVIDER_TC2723, location = AndroidErrorCasesDataProvider.class)
	public void TC2723_EnergyBackpack_IncorrectUsernamePcubedLogin(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2723_EnergyBackpack_IncorrectUsernamePcubedLogin ...");

		UserDataRow userDataRow = loginPageAction.getUsernamePassword(EMPTY, userDataRowID);
		final String username = userDataRow.username;
		final String password = userDataRow.password;

		navigateToMapScreen(true /*waitForMapScreenLoad*/, username);
		executeWithBackPackDataProcessesPaused(obj -> {
			mapScreen.waitForScreenLoad();
			mapScreen.assertMapIsCenteredForPicarroUser();
			Log.info("Map screen loaded successfully!");

			mapScreen.clickOnInvestigate();
			initializeMapScreen();
			mapScreen.waitForLoginDialogToShow();
			mapScreen.enterPassword(password);
			mapScreen.clickOnSubmit();

			investigationScreen.waitForScreenLoad();
			assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, username));

			investigationScreen.clickOnMenuButton();
			settingsScreen.waitForScreenLoad();
			assertTrue("Alarm Settings button should be displayed", settingsScreen.getAlarmSettings().isDisplayed());
			assertTrue("App Settings button should be displayed", settingsScreen.getAppSettings().isDisplayed());
			assertTrue("Logout button should be displayed", settingsScreen.getLogout().isDisplayed());
			assertTrue("Shutdown Instrument button should be displayed", settingsScreen.getShutdownInstrument().isDisplayed());
			settingsScreen.clickOnLogout();
			mapScreen.waitForScreenLoad();

			mapScreen.clickOnInvestigate();
			initializeMapScreen();
			mapScreen.waitForLoginDialogToShow();
			mapScreen.enterPassword(password);
			mapScreen.clearUsername();
			mapScreen.enterUsername(username.substring(0, username.length()-1));
			mapScreen.clickOnSubmit();

			mapScreen.assertIncorrectCredentialsMessageIsShownInRed();

			final String expectedLoginValidationText = SurveyorConstants.INVALIDUSERPASS;
			final String actualLoginValidationText = mapScreen.getLoginValidationLabelText().trim();
			assertTrue(String.format("Incorrect login validation text found. Expected=[%s]; Actual=[%s]", expectedLoginValidationText, actualLoginValidationText),
					expectedLoginValidationText.equals(actualLoginValidationText));

			return true;
		});
	}

	/**
	 * Test Case ID: TC2724_EnergyBackpack_IncorrectPasswordPcubedLogin
	 * Test Description:
	 * Script: -
	 *	- - Log into the tablet and launch the Backpack app
	 *	- - Click the Investigate button at bottom right
	 *	- - Enter valid credentials for PCubed and click OK
	 *	- - Click Menu button at bottom right
	 *	- - Click Logout
	 *	- - Click Investigate button again
	 *	- - Enter same credentials as before, but remove one letter from password and click OK
	 * Results: -
	 *	- - Tablet displays map centered on backpack's location
	 *	- - Dialog appears where user can enter Username and PW for PCubed
	 *	- - Tablet displays a list of Compliance Reports
	 *	- - Menu appears with Alarm Settings, App Settings, Logout and Shutdown Backpack buttons
	 *	- - Tablet returns to homepage
	 *	- - Login dialog appears again
	 *	- - Message Username or password not recognized appears in red font
	 */
	@Test
	@UseDataProvider(value = AndroidErrorCasesDataProvider.ERROR_CASES_DATA_PROVIDER_TC2724, location = AndroidErrorCasesDataProvider.class)
	public void TC2724_EnergyBackpack_IncorrectPasswordPcubedLogin(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2724_EnergyBackpack_IncorrectPasswordPcubedLogin ...");

		UserDataRow userDataRow = loginPageAction.getUsernamePassword(EMPTY, userDataRowID);
		final String username = userDataRow.username;
		final String password = userDataRow.password;

		navigateToMapScreen(true /*waitForMapScreenLoad*/, username);
		executeWithBackPackDataProcessesPaused(obj -> {
			mapScreen.waitForScreenLoad();
			mapScreen.assertMapIsCenteredForPicarroUser();
			Log.info("Map screen loaded successfully!");

			mapScreen.clickOnInvestigate();
			initializeMapScreen();
			mapScreen.waitForLoginDialogToShow();
			mapScreen.enterPassword(password);
			mapScreen.clickOnSubmit();

			investigationScreen.waitForScreenLoad();
			assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, username));

			investigationScreen.clickOnMenuButton();
			settingsScreen.waitForScreenLoad();
			assertTrue("Alarm Settings button should be displayed", settingsScreen.getAlarmSettings().isDisplayed());
			assertTrue("App Settings button should be displayed", settingsScreen.getAppSettings().isDisplayed());
			assertTrue("Logout button should be displayed", settingsScreen.getLogout().isDisplayed());
			assertTrue("Shutdown Instrument button should be displayed", settingsScreen.getShutdownInstrument().isDisplayed());
			settingsScreen.clickOnLogout();
			mapScreen.waitForScreenLoad();

			mapScreen.clickOnInvestigate();
			initializeMapScreen();
			mapScreen.waitForLoginDialogToShow();
			mapScreen.enterPassword(password.substring(0, password.length()-1));
			mapScreen.clickOnSubmit();

			mapScreen.assertIncorrectCredentialsMessageIsShownInRed();

			final String expectedLoginValidationText = SurveyorConstants.INVALIDUSERPASS;
			final String actualLoginValidationText = mapScreen.getLoginValidationLabelText().trim();
			assertTrue(String.format("Incorrect login validation text found. Expected=[%s]; Actual=[%s]", expectedLoginValidationText, actualLoginValidationText),
					expectedLoginValidationText.equals(actualLoginValidationText));

			return true;
		});
	}

	/**
	 * Test Case ID: TC2725_EnergyBackpack_EmptyPasswordPcubedLogin
	 * Test Description:
	 * Script: -
	 *	- - Log into the tablet and launch the Backpack app
	 *	- - Click the Investigate button at bottom right
	 *	- - Enter valid credentials for PCubed and click Submit
	 *	- - Click Menu button at bottom right
	 *	- - Click Logout
	 *	- - Click Investigate button again
	 *	- - Enter same Username as before, but leave Password field empty and click Submit
	 * Results: -
	 *	- - Tablet displays map centered on backpack's location
	 *	- - Dialog appears where user can enter Username and PW for PCubed
	 *	- - Tablet displays a list of Compliance Reports
	 *	- - Menu appears with Alarm Settings, App Settings, Logout and Shutdown Backpack buttons
	 *	- - Tablet returns to homepage
	 *	- - Login dialog appears again
	 *	- - Message Please enter your password! appears in red font
	 */
	@Test
	@UseDataProvider(value = AndroidErrorCasesDataProvider.ERROR_CASES_DATA_PROVIDER_TC2725, location = AndroidErrorCasesDataProvider.class)
	public void TC2725_EnergyBackpack_EmptyPasswordPcubedLogin(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2725_EnergyBackpack_EmptyPasswordPcubedLogin ...");

		UserDataRow userDataRow = loginPageAction.getUsernamePassword(EMPTY, userDataRowID);
		final String username = userDataRow.username;
		final String password = userDataRow.password;

		navigateToMapScreen(true /*waitForMapScreenLoad*/, username);
		executeWithBackPackDataProcessesPaused(obj -> {
			mapScreen.waitForScreenLoad();
			mapScreen.assertMapIsCenteredForPicarroUser();
			Log.info("Map screen loaded successfully!");

			mapScreen.clickOnInvestigate();
			initializeMapScreen();
			mapScreen.waitForLoginDialogToShow();
			mapScreen.enterPassword(password);
			mapScreen.clickOnSubmit();

			investigationScreen.waitForScreenLoad();
			assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, username));

			investigationScreen.clickOnMenuButton();
			settingsScreen.waitForScreenLoad();
			assertTrue("Alarm Settings button should be displayed", settingsScreen.getAlarmSettings().isDisplayed());
			assertTrue("App Settings button should be displayed", settingsScreen.getAppSettings().isDisplayed());
			assertTrue("Logout button should be displayed", settingsScreen.getLogout().isDisplayed());
			assertTrue("Shutdown Instrument button should be displayed", settingsScreen.getShutdownInstrument().isDisplayed());
			settingsScreen.clickOnLogout();
			mapScreen.waitForScreenLoad();

			mapScreen.clickOnInvestigate();
			initializeMapScreen();
			mapScreen.waitForLoginDialogToShow();
			mapScreen.clickOnSubmit();

			mapScreen.assertLoginFailedErrorIsShownInRed();

			final String expectedLoginValidationText = LOGIN_FAILED;
			final String actualLoginValidationText = mapScreen.getLoginValidationLabelText().trim();
			assertTrue(String.format("Incorrect login validation text found. Expected=[%s]; Actual=[%s]", expectedLoginValidationText, actualLoginValidationText),
					expectedLoginValidationText.equals(actualLoginValidationText));

			return true;
		});
	}

	/**
	 * Test Case ID: TC2730_EnergyBackpack_PicarroappServerSettingsRequireHttpOrHttps
	 * Test Description:
	 * Script: -
	 *	- - Log into the tablet and launch the Backpack app
	 *	- - Click on the menu button at bottom right
	 *	- - Click on App Settings
	 *	- - In the Backpack Server field, enter the correct IP address and port number but omit http://, enter a valid Picarro Server URL and click Save
	 *	- - Add http:/ in front of the IP address and click Save
	 *	- - Replace http:/ with http:// and click Save
	 *	- - Click App Settings again
	 *	- - In the Picarro Server field, remove the http:// from the beginning of the URL and click OK
	 *	- - Add http:/ in front of the URL and click OK
	 *	- - Replace http:/ with http:// and click Save
	 *	- - Click App Settings again
	 * Results: -
	 *	- - Tablet displays map centered on backpack's location
	 *	- - Menu pops up with Alarm Settings, App Settings, Logout and Shutdown Backpack buttons
	 *	- - Dialog appears with Backpack Server, Picarro Server and Username fields
	 *	- - Message appears, Invalid input: URL must start with http:// or https in red font
	 *	- - Same message appears again
	 *	- - Dialog disappears
	 *	- - Backpack Server field has correct URL
	 *	- - Message appears, Invalid input: URL must start with http:// or https in red font
	 *	- - Same message appears again
	 *	- - Dialog disappears
	 *	- - Picarro Server field has correct URL
	 */
	@Test
	@UseDataProvider(value = AndroidErrorCasesDataProvider.ERROR_CASES_DATA_PROVIDER_TC2730, location = AndroidErrorCasesDataProvider.class)
	public void TC2730_EnergyBackpack_PicarroappServerSettingsRequireHttpOrHttps(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2730_EnergyBackpack_PicarroappServerSettingsRequireHttpOrHttps ...");

		UserDataRow userDataRow = loginPageAction.getUsernamePassword(EMPTY, userDataRowID);
		final String username = userDataRow.username;
		final String backpackServerAddress = TestContext.INSTANCE.getTestSetup().getBackPackServerIpAddress();
		final String picServerAddress = TestContext.INSTANCE.getTestSetup().getBaseUrl();

		navigateToMapScreen(true /*waitForMapScreenLoad*/, username);
		executeWithBackPackDataProcessesPaused(obj -> {
			mapScreen.waitForScreenLoad();
			mapScreen.assertMapIsCenteredForPicarroUser();
			Log.info("Map screen loaded successfully!");

			mapScreen.clickOnMenuButton();
			settingsScreen.waitForScreenLoad();
			assertTrue("Alarm Settings button should be displayed", settingsScreen.getAlarmSettings().isDisplayed());
			assertTrue("App Settings button should be displayed", settingsScreen.getAppSettings().isDisplayed());
			assertTrue("Logout button should be displayed", settingsScreen.getLogout().isDisplayed());
			assertTrue("Shutdown Instrument button should be displayed", settingsScreen.getShutdownInstrument().isDisplayed());
			settingsScreen.clickOnAppSettings();

			mainLoginScreen.waitForScreenLoad();

			Log.info("Check 1 : Missing 'http://' in backpack server ...");
			String backpackAddressWithoutHttp = backpackServerAddress.replace("http://", "");
			mainLoginScreen.clearAndSaveSettings(backpackAddressWithoutHttp, picServerAddress, username);
			mainLoginScreen.assertMissingHttpOrHttpsErrorIsShownInRed();

			Log.info("Check 2 : Missing 'http' in backpack server ...");
			backpackAddressWithoutHttp = backpackServerAddress.replace("http", "");
			mainLoginScreen.clearAndSaveSettings(backpackAddressWithoutHttp, picServerAddress, username);
			mainLoginScreen.assertMissingHttpOrHttpsErrorIsShownInRed();

			Log.info("Check 3 : Missing 'http://' in backpack server ...");
			String picServerWithoutHttps = picServerAddress.replace("https://", "");
			mainLoginScreen.clearAndSaveSettings(backpackServerAddress, picServerWithoutHttps, username);
			mainLoginScreen.assertUrlMustStartWithHttpOrHttpsErrorIsShownInRed();

			Log.info("Check 4 : Missing 'http://' in backpack server ...");
			picServerWithoutHttps = picServerAddress.replace("https", "");
			mainLoginScreen.clearAndSaveSettings(backpackServerAddress, picServerWithoutHttps, username);
			mainLoginScreen.assertUrlMustStartWithHttpOrHttpsErrorIsShownInRed();

			// correct values. login successful.
			Log.info("Login with correct values ...");
			mainLoginScreen.clearAndSaveSettings(backpackServerAddress, picServerAddress, username);

			// Dismiss settings dialog.
			settingsScreen.waitForScreenLoad();
			settingsScreen.dismissScreen(ParentScreen.MapScreen);

			// open login screen from app settings and verify field values.
			mapScreen.waitForScreenLoad();
			mapScreen.assertMapIsCenteredForPicarroUser();
			Log.info("Map screen loaded successfully!");

			mapScreen.clickOnMenuButton();
			settingsScreen.waitForScreenLoad();
			settingsScreen.clickOnAppSettings();
			mainLoginScreen.waitForScreenLoad();
			String actualBackPackServerAddress = mainLoginScreen.getBackpackServerText();
			assertTrue(String.format("BackPack server address shown is NOT correct. Expected=[%s]; Actual=[%s]", backpackServerAddress, actualBackPackServerAddress),
					actualBackPackServerAddress.equals(backpackServerAddress));
			String actualPicarroServerAddress = mainLoginScreen.getPicarroServerAddressText();
			assertTrue(String.format("Picarro server address shown is NOT correct. Expected=[%s]; Actual=[%s]", picServerAddress, actualPicarroServerAddress),
					actualPicarroServerAddress.equals(picServerAddress));
			String actualUsername = mainLoginScreen.getUsernameText();
			assertTrue(String.format("Username shown is NOT correct. Expected=[%s]; Actual=[%s]", username, actualUsername),
					actualUsername.equals(username));

			return true;
		});
	}

	/**
	 * Test Case ID: TC2732_EnergyBackpack_EmptyUsernamePcubedLogin
	 * Test Description:
	 * Script: -
	 *	- - Log into the tablet and launch the Backpack app
	 *	- - Click the Investigate button at bottom right
	 *	- - Enter valid credentials for PCubed and click Submit
	 *	- - Click Menu button at bottom right
	 *	- - Click Logout
	 *	- - Click Investigate button again
	 *	- - Clear Username field, but leave Password as is and click Submit
	 * Results: -
	 *	- - Tablet displays map centered on backpack's location
	 *	- - Dialog appears where user can enter Username and PW for PCubed
	 *	- - Tablet displays a list of Compliance Reports
	 *	- - Menu appears with Alarm Settings, App Settings, Logout and Shutdown Backpack buttons
	 *	- - Tablet returns to homepage
	 *	- - Login dialog appears again
	 *	- - Message Please enter your user name! appears in red font
	 */
	@Test
	@UseDataProvider(value = AndroidErrorCasesDataProvider.ERROR_CASES_DATA_PROVIDER_TC2732, location = AndroidErrorCasesDataProvider.class)
	public void TC2732_EnergyBackpack_EmptyUsernamePcubedLogin(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2732_EnergyBackpack_EmptyUsernamePcubedLogin ...");

		UserDataRow userDataRow = loginPageAction.getUsernamePassword(EMPTY, userDataRowID);
		final String username = userDataRow.username;
		final String password = userDataRow.password;

		navigateToMapScreen(true /*waitForMapScreenLoad*/, username);
		executeWithBackPackDataProcessesPaused(obj -> {
			mapScreen.waitForScreenLoad();
			mapScreen.assertMapIsCenteredForPicarroUser();
			Log.info("Map screen loaded successfully!");

			mapScreen.clickOnInvestigate();
			initializeMapScreen();
			mapScreen.waitForLoginDialogToShow();
			mapScreen.enterPassword(password);
			mapScreen.clickOnSubmit();

			investigationScreen.waitForScreenLoad();
			assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, username));

			investigationScreen.clickOnMenuButton();
			settingsScreen.waitForScreenLoad();
			assertTrue("Alarm Settings button should be displayed", settingsScreen.getAlarmSettings().isDisplayed());
			assertTrue("App Settings button should be displayed", settingsScreen.getAppSettings().isDisplayed());
			assertTrue("Logout button should be displayed", settingsScreen.getLogout().isDisplayed());
			assertTrue("Shutdown Instrument button should be displayed", settingsScreen.getShutdownInstrument().isDisplayed());
			settingsScreen.clickOnLogout();
			mapScreen.waitForScreenLoad();

			mapScreen.clickOnInvestigate();
			initializeMapScreen();
			mapScreen.waitForLoginDialogToShow();
			mapScreen.clearUsername();
			mapScreen.enterPassword(password);
			mapScreen.clickOnSubmit();

			mapScreen.assertLoginFailedErrorIsShownInRed();

			final String expectedLoginValidationText = LOGIN_FAILED;
			final String actualLoginValidationText = mapScreen.getLoginValidationLabelText().trim();
			assertTrue(String.format("Incorrect login validation text found. Expected=[%s]; Actual=[%s]", expectedLoginValidationText, actualLoginValidationText),
					expectedLoginValidationText.equals(actualLoginValidationText));

			return true;
		});
	}

	/**
	 * Test Case ID: TC2733_EnergyBackpack_PicarroappBackpackServerSettingRequiresPortNumber
	 * Test Description:
	 * Script: -
	 *	- - Log into the tablet and launch the Backpack app
	 *	- - Click on the menu button at bottom right
	 *	- - Click on App Settings
	 *	- - In the Picarro Server field, enter the correct URL (including http:// or https://
	 *	- - In the Backpack Server field, enter the correct IP address in the correct format (with http:// or https://) but omit the colon and port number and click Submit
	 *	- - Add correct colon and port number and click Submit
	 * Results: -
	 *	- - Tablet displays map centered on backpack's location
	 *	- - Menu pops up with Alarm Settings, App Settings, Logout and Shutdown Backpack buttons
	 *	- - Dialog appears with Backpack Server, Picarro Server and Username fields
	 *	- - Message appears warning user that port number is required for Backpack Server field
	 *	- - Login dialog disappears and tablet navigates to list of Compliance Reports
	 */
	@Test
	@UseDataProvider(value = AndroidErrorCasesDataProvider.ERROR_CASES_DATA_PROVIDER_TC2733, location = AndroidErrorCasesDataProvider.class)
	public void TC2733_EnergyBackpack_PicarroappBackpackServerSettingRequiresPortNumber(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2733_EnergyBackpack_PicarroappBackpackServerSettingRequiresPortNumber ...");

		UserDataRow userDataRow = loginPageAction.getUsernamePassword(EMPTY, userDataRowID);
		final String username = userDataRow.username;
		final String password = userDataRow.password;
		final String backpackServerAddress = TestContext.INSTANCE.getTestSetup().getBackPackServerIpAddress();
		final String picServerAddress = TestContext.INSTANCE.getTestSetup().getBaseUrl();

		navigateToMapScreen(true /*waitForMapScreenLoad*/, username);
		executeWithBackPackDataProcessesPaused(obj -> {
			mapScreen.waitForScreenLoad();
			mapScreen.assertMapIsCenteredForPicarroUser();
			Log.info("Map screen loaded successfully!");

			navigateToInvestigationReportScreen(investigationScreen, password);

			investigationScreen.clickOnMenuButton();
			settingsScreen.waitForScreenLoad();
			assertTrue("Alarm Settings button should be displayed", settingsScreen.getAlarmSettings().isDisplayed());
			assertTrue("App Settings button should be displayed", settingsScreen.getAppSettings().isDisplayed());
			assertTrue("Logout button should be displayed", settingsScreen.getLogout().isDisplayed());
			assertTrue("Shutdown Instrument button should be displayed", settingsScreen.getShutdownInstrument().isDisplayed());
			settingsScreen.clickOnAppSettings();

			mainLoginScreen.waitForScreenLoad();

			Log.info("Check 1 : Missing 'port' in backpack server ...");
			String backpackAddressWithoutPort = backpackServerAddress.substring(0, backpackServerAddress.lastIndexOf(":")) ;
			mainLoginScreen.clearAndSaveSettings(backpackAddressWithoutPort, picServerAddress, username);
			mainLoginScreen.assertSpecifyPortNumberErrorIsShownInRed();

			Log.info("Check 2 : Missing 'http://' and 'port' in backpack server ...");
			String backpackAddressWithoutHttp = backpackServerAddress.replace("http", "");
			String backpackAddressWithoutHttpAndPort = backpackAddressWithoutHttp.substring(0, backpackAddressWithoutHttp.lastIndexOf(":")) ;
			mainLoginScreen.clearAndSaveSettings(backpackAddressWithoutHttpAndPort, picServerAddress, username);
			mainLoginScreen.assertSpecifyPortNumberErrorIsShownInRed();

			// correct values. login successful.
			Log.info("Login with correct values ...");
			mainLoginScreen.clearAndSaveSettings(backpackServerAddress, picServerAddress, username);

			// Dismiss settings dialog.
			settingsScreen.waitForScreenLoad();
			settingsScreen.dismissScreen(ParentScreen.ReportScreen);

			// verify reports for user are shown.
			investigationScreen.waitForScreenLoad();
			assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, username));

			return true;
		});
	}

	private void initializeTestScreenObjects() {
		initializeInvestigationScreen();
		initializeInvestigateReportScreen();
		initializeInvestigateMapScreen();
		initializeSettingsScreen();
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
