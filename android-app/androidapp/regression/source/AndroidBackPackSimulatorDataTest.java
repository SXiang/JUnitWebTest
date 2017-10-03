package androidapp.regression.source;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import androidapp.dataprovider.BackPackSimDataProvider;
import androidapp.screens.source.AndroidInvestigateMapScreen;
import androidapp.screens.source.AndroidInvestigateReportScreen;
import androidapp.screens.source.AndroidInvestigationScreen;
import androidapp.screens.source.AndroidMarkerTypeListControl;
import androidapp.screens.source.AndroidMarkerTypeListControl.MarkerType;
import common.source.BackPackAnalyzer;
import common.source.BackPackSimDataTransformer;
import common.source.BackPackSimInstructionsGenerator;
import common.source.BaselineImages;
import common.source.CheckedConsumer;
import common.source.Log;
import common.source.Screenshotter;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.data.UserDataReader.UserDataRow;
import surveyor.scommon.mobile.source.ReportDataGenerator;

public class AndroidBackPackSimulatorDataTest extends BaseReportTest {

	private static final String TC2875 = "TC2875";
	private static final String TC2876 = "TC2876";
	private static final String TC2877 = "TC2877";
	private static final String BACKPACK_SIMULATOR_INPUT_DATA_FILENAME = "Nomad4001-20170713-212401Z-DataLog_User_Minimal.dat";
	private static final Integer defaultUserDataRowID = 6;
	private static final Integer defaultReportDataRowID = 6;

	private static String generatedInvReportTitle;

	protected AndroidInvestigateMapScreen investigateMapScreen;
	protected AndroidInvestigationScreen investigationScreen;
	protected AndroidInvestigateReportScreen investigateReportScreen;
	protected AndroidMarkerTypeListControl markerTypeDialog;

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
		createTestCaseData(testName);
		if (!isRunningInDataGenMode()) {
			initializeTestDriver();
			initializeTestScreenObjects();
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

	/**
	 * Initializes the page action objects.
	 * @throws Exception
	 */
	protected static void initializePageActions() throws Exception {
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
	}

	/**
	 *	Test Case: TC2875_SimulateColumnValuesDroppedInBackpackSimulatorAndVerifyAppBehavesCorrectly
	 *	Script:
	 *	- Start backpack simulator and simulator conditions of column dropped by tweaking simulator input data
	 *       Columns to verify drop:-
	 *       - time
	 *       - CH4
	 *       - EthaneRatio
	 *       - EthaneRatioSdev
	 *       - SpectrumID
	 *	- Login to tablet
	 *	- Navigate to various screens and verify app behaves correctly
	 *	Expected Result:
	 *	 -  App does not hang or crash
	**/
	@Test
	@UseDataProvider(value = BackPackSimDataProvider.BACKPACK_SIM_DATA_PROVIDER_TC2875, location = BackPackSimDataProvider.class)
	public void TC2875_SimulateColumnValuesDroppedInBackpackSimulatorAndVerifyAppBehavesCorrectly(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, String[] dataColumnNames) throws Exception {
		Log.info("\nRunning TC2875_SimulateColumnValuesDroppedInBackpackSimulatorAndVerifyAppBehavesCorrectly ...");

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

		final String inputDataFile = getBackpackSimInputDataFile();
		String instructionsFile = generateInstructionFile(TC2875, dataColumnNames, null /*dataColumnValue*/);
		Boolean replaceInputFile = true;
		BackPackSimDataTransformer.newDataTransformer(inputDataFile).transformDataFileWithRevertAndClean(instructionsFile, replaceInputFile, () -> {
			startBackPackSimulatorAndRecording();
			navigateToDifferentScreens(userDataRowID, generatedInvReportTitle, null);
		});
	}

	/**
	 *	Test Case: TC2876_SimulateVariousDispositionValuesInBackpackSimulatorAndVerifyAppBehavesCorrectly
	 *	Script:
	 *	- Start backpack simulator and simulate various disposition values (see below for supported values in code) by tweaking simulator input data
	 *	- Login to tablet
	 *	- Navigate to various screens and verify app behaves correctly
	 *
	 *	FROM CODE:
	 *	export const NATURAL_GAS = 1
	 *	export const NOT_NATURAL_GAS = 2
	 *	export const POSSIBLE_NATURAL_GAS = 3
	 *	export const DISPOSITION_ERROR = 4
	 *	export const DISPOSITION_UNKNOWN = 5
	 *
	 *	Expected Result:
	 *	 -  App does not hang or crash
	**/
	@Test
	@UseDataProvider(value = BackPackSimDataProvider.BACKPACK_SIM_DATA_PROVIDER_TC2876, location = BackPackSimDataProvider.class)
	public void TC2876_SimulateVariousDispositionValuesInBackpackSimulatorAndVerifyAppBehavesCorrectly(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, String[] dataColumnNames, String[] dataColumnValues) throws Exception {
		Log.info("\nRunning TC2876_SimulateVariousDispositionValuesInBackpackSimulatorAndVerifyAppBehavesCorrectly ...");

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

		final String inputDataFile = getBackpackSimInputDataFile();
		String instructionsFile = generateInstructionFile(TC2876, dataColumnNames, dataColumnValues);
		Boolean replaceInputFile = true;
		BackPackSimDataTransformer.newDataTransformer(inputDataFile).transformDataFileWithRevertAndClean(instructionsFile, replaceInputFile, () -> {
			startBackPackSimulatorAndRecording();
			navigateToDifferentScreens(userDataRowID, generatedInvReportTitle, (investigationScreen) -> {
				verifyCorrectValuesShownInPanelForDisposition(dataColumnNames, dataColumnValues, investigationScreen);
			});
		});
	}

	/**
	 *	Test Case: TC2877_SimulateCH4ValuesInBackpackSimulatorAndVerifyAppBehavesCorrectly
	 *	Script:
	 *	- Start backpack simulator and simulate values of CH4<=0.0 by tweaking simulator input data
	 *	- Login to tablet
	 *	- Navigate to various screens and verify app behaves correctly
	 *	Expected Result:
	 *	 -  App does not hang or crash
	**/
	@Test
	@UseDataProvider(value = BackPackSimDataProvider.BACKPACK_SIM_DATA_PROVIDER_TC2877, location = BackPackSimDataProvider.class)
	public void TC2877_SimulateCH4ValuesInBackpackSimulatorAndVerifyAppBehavesCorrectly(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, String[] dataColumnNames, String[] dataColumnValues) throws Exception {
		Log.info("\nRunning TC2877_SimulateCH4ValuesInBackpackSimulatorAndVerifyAppBehavesCorrectly ...");

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

		final String inputDataFile = getBackpackSimInputDataFile();
		String instructionsFile = generateInstructionFile(TC2876, dataColumnNames, dataColumnValues);
		Boolean replaceInputFile = true;
		BackPackSimDataTransformer.newDataTransformer(inputDataFile).transformDataFileWithRevertAndClean(instructionsFile, replaceInputFile, () -> {
			startBackPackSimulatorAndRecording();
			navigateToDifferentScreens(userDataRowID, generatedInvReportTitle, null);
		});
	}

	private void createTestCaseData(TestName testName) throws Exception {
		String methodName = testName.getMethodName();
		Integer userDataRowID = defaultUserDataRowID;
		Integer reportDataRowID1 = defaultReportDataRowID;
		String tcId = "";
		boolean reuseReports = !isRunningInDataGenMode();
		if (methodName.startsWith(TC2875)) {
			Object[][] tc2875 = BackPackSimDataProvider.dataProviderBackPackSim_TC2875();
			userDataRowID = (Integer)tc2875[0][1];
			reportDataRowID1 = (Integer)tc2875[0][2];
			tcId = TC2875;
		} else if (methodName.startsWith(TC2876)) {
			Object[][] tc2876 = BackPackSimDataProvider.dataProviderBackPackSim_TC2876();
			userDataRowID = (Integer)tc2876[0][1];
			reportDataRowID1 = (Integer)tc2876[0][2];
			tcId = TC2876;
		} else if (methodName.startsWith(TC2877)) {
			Object[][] tc2877 = BackPackSimDataProvider.dataProviderBackPackSim_TC2877();
			userDataRowID = (Integer)tc2877[0][1];
			reportDataRowID1 = (Integer)tc2877[0][2];
			tcId = TC2877;
		}

		if (!invReportDataVerifier.hasNotInvestigatedGapMarker(tcId, loginPageAction.getUsernamePassword(EMPTY, userDataRowID).username)) {
			reuseReports = false;
		}

		generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(reuseReports /*isReusable*/).createReportAndAssignLisasAndGapsToUser(tcId,
				defaultUserDataRowID, userDataRowID, reportDataRowID1).getReportTitle();
	}

	private static String getBackpackSimInputDataFile() throws IOException {
		String repoRootFolder = TestSetup.getRootPath().replace("\\surveyor-qa", "");
		return String.format("%s\\host\\src\\main\\python\\Host\\Utilities\\BackpackServer\\TestUtilities\\%s",
				repoRootFolder, BACKPACK_SIMULATOR_INPUT_DATA_FILENAME);
	}

	private static String generateInstructionFile(String tcID, String[] dataColumnNames, String[] dataColumnValues) throws IOException {
		String filename = String.format("%s-inst.ini", tcID);
		if (tcID.equals(TC2875)) {
			filename = String.format("%s_%s-inst.ini", tcID, dataColumnNames);
			List<String> dropColumns = new ArrayList<>();
			for (int i = 0; i < dataColumnNames.length; i++) {
				dropColumns.add(dataColumnNames[i]);
			}
			return BackPackSimInstructionsGenerator.generateInstructionFileForDrop(filename, dropColumns);
		} else if (tcID.equals(TC2876)) {
			Map<String, String> replaceColsMap = new HashMap<>();
			for (int i = 0; i < dataColumnNames.length; i++) {
				replaceColsMap.put(dataColumnNames[i], dataColumnValues[i]);
			}
			return BackPackSimInstructionsGenerator.generateInstructionFileForReplace(filename, replaceColsMap);
		} else if (tcID.equals(TC2877)) {
			Map<String, String> replaceColsMap = new HashMap<>();
			for (int i = 0; i < dataColumnNames.length; i++) {
				replaceColsMap.put(dataColumnNames[i], dataColumnValues[i]);
			}
			return BackPackSimInstructionsGenerator.generateInstructionFileForReplace(filename, replaceColsMap);
		}

		throw new RuntimeException(String.format("Unsupported test case ID - '%s' for generating instructions file.", tcID));
	}

	private void navigateToDifferentScreens(Integer userDataRowID, String reportTitle, Consumer<AndroidInvestigationScreen> reportScreenTestSteps) throws Exception {
		Log.method("navigateToDifferentScreens", userDataRowID, reportTitle);

		UserDataRow userDataRow = loginPageAction.getDataRow(userDataRowID);

		final String notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);
		final String inProgress = Resources.getResource(ResourceKeys.InvestigationStatusTypes_In_Progress);
		final String foundGasLeak = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Found_Gas_Leak);

		navigateToMapScreen(true /*waitForMapScreenLoad*/, userDataRow.username);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreen(investigationScreen, userDataRow.password);
			assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, userDataRow.username));

			if (reportScreenTestSteps != null) {
				reportScreenTestSteps.accept(investigationScreen);
			}

			searchForReportId(investigationScreen, reportTitle);
			initializeInvestigationScreen();
			return true;
		});

		Screenshotter.captureWebDriverScreenshot(getAndroidDriver(), "DispositionValueChange");

		clickOnFirstInvestigationReport(investigationScreen);
		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			assertTrue(investigateReportScreen.verifyMarkersForReportAreShown(reportTitle));
			investigateReportScreen.clickOnInvestigationMarkerType();
			markerTypeDialog.selectMarkerType(MarkerType.Gap);
			investigateReportScreen.waitForMarkerTypeGapToBeSelected();
			initializeInvestigateReportScreen();
			return true;
		});

		String[] markerStatuses = {notInvestigated, inProgress, foundGasLeak};
		investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatuses));

		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateMapScreen.waitForScreenLoad();
			return true;
		});
	}

	private void startBackPackSimulatorAndRecording() throws IOException, Exception {
		if (!TestContext.INSTANCE.getTestSetup().isRunningOnBackPackAnalyzer()) {
			BackPackAnalyzer.restartSimulator();
		}

		startTestRecording(testName.getMethodName());
	}

	private void verifyCorrectValuesShownInPanelForDisposition(String[] dataColumnNames, String[] dataColumnValues,
			AndroidInvestigationScreen investigationScreen) {
		int dispositionColIdx = -1;
		for (int i = 0; i < dataColumnNames.length; i++) {
			if (dataColumnNames[i].equals(BackPackSimDataProvider.DISPOSITION)) {
				dispositionColIdx = i;
				break;
			}
		}

		String dispositionValue = dataColumnValues[dispositionColIdx];
		if (dispositionValue.equals(BackPackSimDataProvider.DISPOSITION_1)) {
			investigationScreen.assertCorrectValuesShownInPanelForDisposition(BaselineImages.ImageFile.BackPackPanelDisposition_1);
		} else if (dispositionValue.equals(BackPackSimDataProvider.DISPOSITION_2)) {
			investigationScreen.assertCorrectValuesShownInPanelForDisposition(BaselineImages.ImageFile.BackPackPanelDisposition_2);
		} else {
			investigationScreen.assertCorrectValuesShownInPanelForDisposition(BaselineImages.ImageFile.BackPackPanelDisposition_3_4_5);
		}
	}

	private void initializeTestScreenObjects() {
		initializeInvestigateMapScreen();
		initializeInvestigationScreen();
		initializeInvestigateReportScreen();
		initializeMarkerTypeDialog();
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

	protected void initializeMarkerTypeDialog() {
		markerTypeDialog = new AndroidMarkerTypeListControl(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), markerTypeDialog);
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