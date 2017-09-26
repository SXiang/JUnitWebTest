package androidapp.performance.source;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import androidapp.dataprovider.StressTestDataProvider;
import androidapp.regression.source.BaseReportTest;
import androidapp.screens.source.AndroidAddLeakSourceFormDialog;
import androidapp.screens.source.AndroidAddOtherSourceFormDialog;
import androidapp.screens.source.AndroidAddSourceDialog;
import androidapp.screens.source.AndroidAddedSourceListDialog;
import androidapp.screens.source.AndroidConfirmationDialog;
import androidapp.screens.source.AndroidInvestigateMapScreen;
import androidapp.screens.source.AndroidInvestigateReportScreen;
import androidapp.screens.source.AndroidInvestigationScreen;
import androidapp.screens.source.AndroidMarkerTypeListControl;
import common.source.BackPackAnalyzer;
import common.source.Log;
import common.source.TestContext;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataprovider.DataGenerator;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.data.UserDataReader.UserDataRow;
import surveyor.scommon.mobile.source.LeakDataGenerator;
import surveyor.scommon.mobile.source.ReportDataGenerator;
import surveyor.scommon.mobile.source.LeakDataGenerator.LeakDataBuilder;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakSourceType;

public class AndroidStressTest extends BaseReportTest {
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
	protected AndroidAddOtherSourceFormDialog addOtherSourceFormDialog;

	private static LoginPageActions loginPageAction;

	private static ThreadLocal<Boolean> appiumTestInitialized = new ThreadLocal<Boolean>();

	private static Integer maxLeaksToAdd = 0;
	private static Integer maxOtherSourcesToAdd = 0;

	@Rule
	public TestName testName = new TestName();

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects(false);
		setMaxLimitsForTest();
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

	private static void setMaxLimitsForTest() {
		maxLeaksToAdd = TestContext.INSTANCE.getTestSetup().getAndroidStressTestMaxLeaksPerTestRun();
		maxOtherSourcesToAdd = TestContext.INSTANCE.getTestSetup().getAndroidStressTestMaxOtherSourcesPerTestRun();
	}

	/**
	 * Initializes the page action objects.
	 * @throws Exception
	 */
	protected static void initializePageActions() throws Exception {
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
	}

	/**
	 *	Test Case: TC2870_StressTestCreateLargeNumberOfLeaks
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
	@UseDataProvider(value = StressTestDataProvider.STRESS_TEST_DATA_PROVIDER_TC2870, location = StressTestDataProvider.class)
	public void TC2870_StressTestCreateLargeNumberOfLeaks (
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2870_StressTestCreateLargeNumberOfLeaks ...");

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
				Log.info(String.format("Add Leak : Stress Test. Iteration - %d", ++i));
				addLeakSourceFormDialog.waitForScreenLoad();
				LeakDataBuilder leakDataBuilder = LeakDataGenerator.newBuilder().generateRandomValuesWithNulls();
				addLeakSourceFormDialog.fillForm(leakDataBuilder.toMap());
				addedSourcesListDialog.screenAndDataLoadCondition();
				addedSourcesListDialog.clickOnAddLeak();

			} while (i < maxLeaksToAdd);

			return true;
		});
	}

	/**
	 *	Test Case: TC2871_StressTestCreateLargeNumberOfOtherSources
	 *	Script:
     *     - Log into Backpack tablet
     *     - Press Investigation button
     *     - Login to Pcubed
     *     - Select a report, select a marker
     *     - Click on Investigate | Add Source
     *     - Trigger the following steps in a loop for multiple iterations
     *     - - - Click on Add Other Source
     *     - - - Fill the form and click Submit
	 *	Expected Result:
	 *	 - Ensure no crashes or data load issues seen in the test
	**/
	@Test
	@UseDataProvider(value = StressTestDataProvider.STRESS_TEST_DATA_PROVIDER_TC2871, location = StressTestDataProvider.class)
	public void TC2871_StressTestCreateLargeNumberOfOtherSources (
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2871_StressTestCreateLargeNumberOfOtherSources ...");

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

		final LeakSourceType[] leakSourceTypes = LeakSourceType.values();

		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			investigateMapScreen.clickOnAddSource();
			addSourceDialog.waitForScreenLoad();
			addSourceDialog.clickOnAddOtherSources();

			int i = 0;
			do {
				Log.info(String.format("Add Other Source : Stress Test. Iteration - %d", ++i));
				addOtherSourceFormDialog.waitForScreenLoad();
				if (i % 2 == 0) { addOtherSourceFormDialog.clickOnUseCurrentLocation(); }
				addOtherSourceFormDialog.selectLeakSource(leakSourceTypes[new Random().nextInt(leakSourceTypes.length)]);
				// TBD: Increase the text length to be greater than 250 post product defect DE3384 is fixed
				addOtherSourceFormDialog.enterAdditionalNotes(DataGenerator.getRandomText(100, 200));
				addOtherSourceFormDialog.clickOnSubmit();
				addedSourcesListDialog.screenAndDataLoadCondition();
				addedSourcesListDialog.clickOnAddOtherSources();

			} while (i < maxOtherSourcesToAdd);

			return true;
		});
	}

	private void createTestCaseData(TestName testName) throws Exception {
		String methodName = testName.getMethodName();
		Integer userDataRowID = defaultUserDataRowID;
		Integer reportDataRowID1 = defaultReportDataRowID;
		String tcId = "";
		boolean reuseReports = false;
		boolean foundReport = false;
		if (methodName.startsWith("TC2870_")) {
			tcId = "TC2870";
			Object[][] tc2870 = StressTestDataProvider.dataProviderStressTest_TC2870();
			userDataRowID = (Integer)tc2870[0][1];
			reportDataRowID1 = (Integer)tc2870[0][2];
		} else if (methodName.startsWith("TC2871_")) {
			tcId = "TC2871";
			Object[][] tc2871 = StressTestDataProvider.dataProviderStressTest_TC2871();
			userDataRowID = (Integer)tc2871[0][1];
			reportDataRowID1 = (Integer)tc2871[0][2];
		}

		foundReport = invReportDataVerifier.hasCompleteInProgressOrNotInvestigatedLisaMarker(tcId, loginPageAction.getUsernamePassword(EMPTY, userDataRowID).username);
		reuseReports = foundReport && !isRunningInDataGenMode();
		generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(reuseReports /*isReusable*/).createReportAndAssignLisasToUser(tcId,
				defaultUserDataRowID, userDataRowID, reportDataRowID1).getReportTitle();
	}

	private void initializeTestScreenObjects() {
		initializeInvestigateMapScreen();
		initializeInvestigationScreen();
		initializeInvestigateReportScreen();
		initializeAddSourceDialog();
		initializeMarkerTypeDialog();
		initializeAddLeakSourceFormDialog();
		initializeAddOtherSourceFormDialog();
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

	protected void initializeAddOtherSourceFormDialog() {
		addOtherSourceFormDialog = new AndroidAddOtherSourceFormDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addOtherSourceFormDialog);
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