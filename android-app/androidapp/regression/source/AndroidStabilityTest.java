package androidapp.regression.source;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import androidapp.dataprovider.StabilityTestDataProvider;
import androidapp.screens.source.AndroidInvestigateMapScreen;
import androidapp.screens.source.AndroidInvestigateReportScreen;
import androidapp.screens.source.AndroidInvestigationScreen;
import androidapp.screens.source.AndroidMarkerTypeListControl;
import common.source.ArrayUtility;
import common.source.BackPackAnalyzer;
import common.source.Log;
import common.source.TestContext;
import common.source.Timeout;
import common.source.Timer;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.data.UserDataReader.UserDataRow;
import surveyor.scommon.mobile.source.ReportDataGenerator;

public class AndroidStabilityTest extends BaseReportTest {
	private static final Integer defaultUserDataRowID = 6;
	private static final Integer defaultReportDataRowID = 6;
	private static String generatedInvReportTitle;

	protected AndroidInvestigationScreen investigationScreen;
	protected AndroidInvestigateReportScreen investigateReportScreen;
	protected AndroidMarkerTypeListControl markerTypeDialog;

	protected AndroidInvestigateMapScreen investigateMapScreen;

	private static LoginPageActions loginPageAction;

	private static ThreadLocal<Boolean> appiumTestInitialized = new ThreadLocal<Boolean>();

	private static Timer timer = null;

	@Rule
	public TestName testName = new TestName();

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects(false);
	}

	@Before
	public void beforeTest() throws Exception {
		initializePageActions();
		createTestCaseData(testName);
		if (!isRunningInDataGenMode()) {
			initializeTestDriver();
			initializeTestScreenObjects();
			if (!TestContext.INSTANCE.getTestSetup().isRunningOnBackPackAnalyzer()) {
				BackPackAnalyzer.restartSimulator();
			}

			startTestRecording(testName.getMethodName());
		}

		createNewTimer();
	}

	@After
	public void afterTest() throws Exception {
		if (!isRunningInDataGenMode()) {
			stopTestRecording(testName.getMethodName());
			if (!TestContext.INSTANCE.getTestSetup().isRunningOnBackPackAnalyzer()) {
				BackPackAnalyzer.stopSimulator();
			}
		}
	}

	/**
	 * Initializes the page action objects.
	 * @throws Exception
	 */
	protected static void initializePageActions() throws Exception {
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
	}

	private static void createNewTimer() {
		timer = Timer.createNew(TestContext.INSTANCE.getTestSetup().getAndroidStabilityTestMaxHrs(), TestContext.INSTANCE.getTestSetup().getAndroidStabilityTestMaxMinutes(), 0);
	}

	/**
	 *	Test Case: TC2811_StabilityTestVerifyContinuousUseOfPicarroAppWorksWithoutErrors
	 *	Script:
	 *	 - Log into Backpack tablet
	 *	 - Press Investigation button
	 *	 - Login to Pcubed
	 *	 - Trigger the following steps in a loop for multiple hours
	 *	 - - - Select a report
	 *	 - - - Select a LISA
	 *	 - - - Tap investigate and go back to reports screen
	 *	Expected Result:
	 *	 - Ensure no crashes or data load issues seen in the test
	**/
	@Test
	@UseDataProvider(value = StabilityTestDataProvider.STABILITY_TEST_DATA_PROVIDER_TC2811, location = StabilityTestDataProvider.class)
	public void TC2811_StabilityTestVerifyContinuousUseOfPicarroAppWorksWithoutErrors(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2811_StabilityTestVerifyContinuousUseOfPicarroAppWorksWithoutErrors ...");

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

		UserDataRow userDataRow = loginPageAction.getDataRow(userDataRowID);

		final String notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);
		final String inProgress = Resources.getResource(ResourceKeys.InvestigationStatusTypes_In_Progress);

		navigateToMapScreen(true /*waitForMapScreenLoad*/, userDataRow.username);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreen(investigationScreen, userDataRow.password);
			assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, userDataRow.username));
			searchForReportId(investigationScreen, generatedInvReportTitle);
			initializeInvestigationScreen();
			return true;
		});

		do {
			clickOnFirstInvestigationReport(investigationScreen);
			executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
				investigateReportScreen.waitForScreenLoad();
				assertTrue(investigateReportScreen.verifyMarkersForReportAreShown(generatedInvReportTitle));
				return true;
			});

			String[] markerStatuses = {notInvestigated, inProgress};
			investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatuses));

			executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
				investigateMapScreen.waitForScreenLoad();
				investigateMapScreen.clickOnFooterInvestigate();
				investigationScreen.waitForScreenLoad();
				assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, userDataRow.username));
				searchForReportId(investigationScreen, generatedInvReportTitle);
				initializeInvestigationScreen();
				return true;
			});

		} while (!timer.hasElapsed());
	}

	private void createTestCaseData(TestName testName) throws Exception {
		String methodName = testName.getMethodName();
		Integer userDataRowID = defaultUserDataRowID;
		Integer reportDataRowID1 = defaultReportDataRowID;
		String tcId = "";
		boolean reuseReports = false;
		String[] tcsWithReportsThatHaveLisas = {"TC2434", "TC2436", "TC2438", "TC2440", "TC2441", "TC2543", "TC2682", "TC2683", "TC2684", "TC2811"};
		ArrayUtility.shuffle(tcsWithReportsThatHaveLisas);     // add randomness to input data.
		if (methodName.startsWith("TC2811_")) {
			Object[][] tc2811 = StabilityTestDataProvider.dataProviderStabilityTest_TC2811();
			userDataRowID = (Integer)tc2811[0][1];
			reportDataRowID1 = (Integer)tc2811[0][2];
			boolean foundReport = false;
			for (String testCase : tcsWithReportsThatHaveLisas) {
				tcId = testCase;
				if (invReportDataVerifier.hasCompleteOrInProgressLisaMarker(tcId, loginPageAction.getUsernamePassword(EMPTY, userDataRowID).username)) {
					foundReport = true;
					break;
				}
			}

			reuseReports = foundReport && !isRunningInDataGenMode();
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(reuseReports /*isReusable*/).createReportAndAssignLisasToUser(tcId,
					defaultUserDataRowID, userDataRowID, reportDataRowID1).getReportTitle();
		}
	}

	private void initializeTestScreenObjects() {
		initializeInvestigateMapScreen();
		initializeInvestigationScreen();
		initializeInvestigateReportScreen();
	}

	private void initializeInvestigateMapScreen() {
		investigateMapScreen = new AndroidInvestigateMapScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigateMapScreen);
	}

	private void initializeInvestigateReportScreen() {
		investigateReportScreen = new AndroidInvestigateReportScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigateReportScreen);
	}

	private void initializeInvestigationScreen() {
		investigationScreen = new AndroidInvestigationScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigationScreen);
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