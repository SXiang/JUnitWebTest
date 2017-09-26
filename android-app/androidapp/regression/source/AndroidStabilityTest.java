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
import androidapp.screens.source.AndroidAddLeakSourceFormDialog;
import androidapp.screens.source.AndroidAddSourceDialog;
import androidapp.screens.source.AndroidAddedSourceListDialog;
import androidapp.screens.source.AndroidConfirmationDialog;
import androidapp.screens.source.AndroidInvestigateMapScreen;
import androidapp.screens.source.AndroidInvestigateReportScreen;
import androidapp.screens.source.AndroidInvestigationScreen;
import androidapp.screens.source.AndroidMarkerTypeListControl;
import common.source.ArrayUtility;
import common.source.BackPackAnalyzer;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.Timeout;
import common.source.Timer;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.data.UserDataReader.UserDataRow;
import surveyor.scommon.mobile.source.LeakDataGenerator;
import surveyor.scommon.mobile.source.ReportDataGenerator;
import surveyor.scommon.mobile.source.LeakDataGenerator.LeakDataBuilder;

public class AndroidStabilityTest extends BaseReportTest {
	private static final Integer defaultUserDataRowID = 6;
	private static final Integer defaultReportDataRowID = 6;
	private static String generatedInvReportTitle;

	protected AndroidInvestigationScreen investigationScreen;
	protected AndroidInvestigateReportScreen investigateReportScreen;
	protected AndroidMarkerTypeListControl markerTypeDialog;

	protected AndroidInvestigateMapScreen investigateMapScreen;
	protected AndroidAddSourceDialog addSourceDialog;
	protected AndroidConfirmationDialog confirmationDialog;

	protected AndroidAddLeakSourceFormDialog addLeakSourceFormDialog;
	protected AndroidAddedSourceListDialog addedSourcesListDialog;

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
	public void TC2811a_StabilityTestVerifyContinuousUseOfPicarroAppWorksWithoutErrors(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2811a_StabilityTestVerifyContinuousUseOfPicarroAppWorksWithoutErrors ...");

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

		int i = 0;
		do {

			Log.info(String.format("Stability Test. Iteration - %d", ++i));

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
				investigationScreen.refreshListViewElements();
				assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, userDataRow.username));
				searchForReportId(investigationScreen, generatedInvReportTitle);
				initializeInvestigationScreen();
				return true;
			});

		} while (!timer.hasElapsed());
	}

	/**
	 *	Test Case: TC2811b_StabilityTestVerifyContinuousUseOfPicarroAppWorksWithoutErrors
	 *	Script:
	 *	 - Log into Backpack tablet
	 *	 - Press Investigation button
	 *	 - Login to Pcubed
	 *	 - Select a report, select a marker
	 *	 - Trigger the following steps in a loop for multiple hours
	 *	 - - - Click on Investigate | Add Source
	 *	 - - - Click on Add Leak
	 *	 - - - Fill the form and click Submit
	 *	 - - - Click on Mark As Complete
	 *	Expected Result:
	 *	 - Ensure no crashes or data load issues seen in the test
	**/
	@Test
	@UseDataProvider(value = StabilityTestDataProvider.STABILITY_TEST_DATA_PROVIDER_TC2811, location = StabilityTestDataProvider.class)
	public void TC2811b_StabilityTestVerifyContinuousUseOfPicarroAppWorksWithoutErrors(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2811b_StabilityTestVerifyContinuousUseOfPicarroAppWorksWithoutErrors ...");

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

		UserDataRow userDataRow = loginPageAction.getDataRow(userDataRowID);

		final String notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);
		final String inProgress = Resources.getResource(ResourceKeys.InvestigationStatusTypes_In_Progress);
		final String foundGasLeak = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Found_Gas_Leak);

		navigateToMapScreen(true /*waitForMapScreenLoad*/, userDataRow.username);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreen(investigationScreen, userDataRow.password);
			assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, userDataRow.username));
			searchForReportId(investigationScreen, generatedInvReportTitle);
			initializeInvestigationScreen();
			return true;
		});

		clickOnFirstInvestigationReport(investigationScreen);
		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			assertTrue(investigateReportScreen.verifyMarkersForReportAreShown(generatedInvReportTitle));
			return true;
		});

		final String[] markerStatuses = {notInvestigated, inProgress, foundGasLeak};

		int i = 0;
		do {
			Log.info(String.format("Stability Test. Iteration - %d", ++i));

			investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatuses));

			executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
				investigateMapScreen.waitForScreenLoad();
				investigateMapScreen.clickOnInvestigate();
				investigateMapScreen.clickOnAddSource();
				addSourceDialog.waitForScreenLoad();
				addSourceDialog.clickOnAddLeak();
				addLeakSourceFormDialog.waitForScreenLoad();
				LeakDataBuilder leakDataBuilder = LeakDataGenerator.newBuilder().generateDefaultValues();
				addLeakSourceFormDialog.fillForm(leakDataBuilder.toMap());
				addedSourcesListDialog.waitForScreenLoad();
				investigateMapScreen.dismissPopup();
				investigateMapScreen.waitForScreenLoad();
				investigateMapScreen.clickOnMarkAsComplete();
				investigateReportScreen.waitForScreenLoad();
				return true;
			});

		} while (!timer.hasElapsed());
	}

	/**
	 *	Test Case: TC2811c_StabilityTestVerifyContinuousUseOfPicarroAppWorksWithoutErrors
	 *	Script:
	 *	 - Log into Backpack tablet
	 *	 - Press Investigation button
	 *	 - Login to Pcubed
	 *	 - Select a report, select a marker
	 *	 - Click on Investigate | Add Source
	 *	 - Trigger the following steps in a loop for multiple hours
	 *	 - - - Click on Add Leak
	 *	 - - - Fill the form and click Submit
	 *	Expected Result:
	 *	 - Ensure no crashes or data load issues seen in the test
	**/
	@Test
	@UseDataProvider(value = StabilityTestDataProvider.STABILITY_TEST_DATA_PROVIDER_TC2811, location = StabilityTestDataProvider.class)
	public void TC2811c_StabilityTestVerifyContinuousUseOfPicarroAppWorksWithoutErrors(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2811c_StabilityTestVerifyContinuousUseOfPicarroAppWorksWithoutErrors ...");

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

		UserDataRow userDataRow = loginPageAction.getDataRow(userDataRowID);

		final String notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);
		final String inProgress = Resources.getResource(ResourceKeys.InvestigationStatusTypes_In_Progress);
		final String foundGasLeak = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Found_Gas_Leak);

		navigateToMapScreen(true /*waitForMapScreenLoad*/, userDataRow.username);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreen(investigationScreen, userDataRow.password);
			assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, userDataRow.username));
			searchForReportId(investigationScreen, generatedInvReportTitle);
			initializeInvestigationScreen();
			return true;
		});

		clickOnFirstInvestigationReport(investigationScreen);
		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			assertTrue(investigateReportScreen.verifyMarkersForReportAreShown(generatedInvReportTitle));
			return true;
		});

		final String[] markerStatuses = {notInvestigated, inProgress, foundGasLeak};

		investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatuses));

		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			investigateMapScreen.clickOnAddSource();
			addSourceDialog.waitForScreenLoad();
			addSourceDialog.clickOnAddLeak();

			int i = 0;
			do {
				Log.info(String.format("Stability Test. Iteration - %d", ++i));
				addLeakSourceFormDialog.waitForScreenLoad();
				LeakDataBuilder leakDataBuilder = LeakDataGenerator.newBuilder().generateRandomValuesWithNulls();
				addLeakSourceFormDialog.fillForm(leakDataBuilder.toMap());
				addedSourcesListDialog.screenAndDataLoadCondition();
				addedSourcesListDialog.clickOnAddLeak();

			} while (!timer.hasElapsed());

			return true;
		});
	}

	private void createTestCaseData(TestName testName) throws Exception {
		String methodName = testName.getMethodName();
		Integer userDataRowID = defaultUserDataRowID;
		Integer reportDataRowID1 = defaultReportDataRowID;
		String tcId = "";
		boolean reuseReports = false;
		String[] tcsWithReportsThatHaveLisas = {"TC2811"};
		ArrayUtility.shuffle(tcsWithReportsThatHaveLisas);
		if (methodName.startsWith("TC2811a_") || methodName.startsWith("TC2811b_") || methodName.startsWith("TC2811c_")) {
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
		initializeAddSourceDialog();
		initializeMarkerTypeDialog();
		initializeAddLeakSourceFormDialog();
		initializeAndroidAddedLeakListDialog();
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

	protected void initializeAddSourceDialog() {
		addSourceDialog = new AndroidAddSourceDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addSourceDialog);
	}

	protected void initializeMarkerTypeDialog() {
		markerTypeDialog = new AndroidMarkerTypeListControl(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), markerTypeDialog);
	}

	protected void initializeAddLeakSourceFormDialog() {
		addLeakSourceFormDialog = new AndroidAddLeakSourceFormDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addLeakSourceFormDialog);
	}

	protected void initializeAndroidAddedLeakListDialog() {
		addedSourcesListDialog = new AndroidAddedSourceListDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addedSourcesListDialog);
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