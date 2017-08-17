package androidapp.regression.source;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import androidapp.dataprovider.FormCancelReportDataProvider;
import androidapp.entities.source.InvestigationMarkerEntity;
import androidapp.screens.source.AndroidAddCgiFormDialog;
import androidapp.screens.source.AndroidAddLeakSourceFormDialog;
import androidapp.screens.source.AndroidAddOtherSourceFormDialog;
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
import common.source.Timeout;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataprovider.DataGenerator;
import surveyor.scommon.mobile.source.LeakDataGenerator;
import surveyor.scommon.mobile.source.ReportDataGenerator;
import surveyor.scommon.mobile.source.LeakDataGenerator.LeakDataBuilder;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakSourceType;
import surveyor.scommon.source.SurveyorConstants;

public class AndroidFormCancelVerificationTest extends BaseReportTest {
	private static final Integer defaultAssignedUserDataRowID = 16;
	private static final Integer defaultUserDataRowID = 6;
	private static final Integer defaultReportDataRowID = 6;
	private static String generatedInvReportTitle;

	protected AndroidInvestigationScreen investigationScreen;
	protected AndroidInvestigateReportScreen investigateReportScreen;
	protected AndroidMarkerTypeListControl markerTypeDialog;

	protected AndroidInvestigateMapScreen investigateMapScreen;
	protected AndroidAddSourceDialog addSourceDialog;

	protected AndroidAddLeakSourceFormDialog addLeakSourceFormDialog;
	protected AndroidAddedSourceListDialog addedSourcesListDialog;

	protected AndroidAddOtherSourceFormDialog addOtherSourceFormDialog;
	protected AndroidConfirmationDialog confirmationDialog;
	protected AndroidAddCgiFormDialog addCgiFormDialog;

	private static ThreadLocal<Boolean> appiumTestInitialized = new ThreadLocal<Boolean>();

	@Rule
	public TestName testName = new TestName();

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects(false);
	}

	@Before
	public void beforeTest() throws Exception {
		createTestCaseData(testName);
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
	 * Test Case ID: TC2682_EnergyBackpack_LoggingCGI_Cancel
	 * Test Description: Energy Backpack - Logging CGI - Cancel
	 * Script: -
	 *	- - Log into Backpack tablet
	 *	- - Press Investigation button
	 *	- - Select a report
	 *	- - Select a LISA
	 *	- - Click Investigate button
	 *	- - Click on Add CGI button
	 *	- - Enter an address and click Cancel
	 * Results: -
	 *	- - User will see satellite map view
	 *	- - User will see a list of Compliance Reports assigned to him/her
	 *	- - User will see a list of LISAs from that report assigned to him/her
	 *	- - User is navigated to map showing user's position
	 *	- - Investigation Status at top of screen is set to In-Progress
	 *	- - A popup will appear where user can add addresses
	 *	- - Popup disappears. Investigation status at top left is still In Progress
	 */
	@Test
	@UseDataProvider(value = FormCancelReportDataProvider.REPORT_DATA_PROVIDER_TC2682, location = FormCancelReportDataProvider.class)
	public void TC2682_EnergyBackpack_LoggingCGI_Cancel(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2682_EnergyBackpack_LoggingCGI_Cancel ...");

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
		executeWithBackPackDataProcessesPaused(obj -> {
			mapScreen.assertMapIsLoaded();
			mapScreen.assertMapIsCenteredForPicarroUser();
			navigateToInvestigationReportScreen(investigationScreen, SurveyorConstants.USERPASSWORD);
			assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, SurveyorConstants.SQAPICDR));
			searchForReportId(investigationScreen, generatedInvReportTitle);
			initializeInvestigationScreen();
			return true;
		});

		clickOnFirstInvestigationReport(investigationScreen);

		List<InvestigationMarkerEntity> investigationMarkers = new ArrayList<InvestigationMarkerEntity>();
		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			assertTrue(investigateReportScreen.verifyMarkersForReportAreShown(generatedInvReportTitle));
			investigateReportScreen.getInvestigationMarkers().stream()
				.forEach(m -> investigationMarkers.add(m));
			return true;
		});

		final String notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);
		final String inProgress = Resources.getResource(ResourceKeys.LisaInvestigationAssignment_InProgress);
		List<String> markerStatuses = Arrays.asList(notInvestigated);
		int idx = investigateReportScreen.clickFirstMarkerMatchingStatus(markerStatuses);

		final String selectedLisa = investigationMarkers.get(idx-1).getMarkerNumber();
		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();

			String actualInvStatusText = investigateMapScreen.getMarkerInvestigationStatusText().trim();
			String expectedInvStatusText = String.format("%s (%s)", selectedLisa, inProgress);
			assertTrue(String.format("Investigation marker text NOT correct. Expected=[%s]; Actual=[%s]", expectedInvStatusText, actualInvStatusText),
					actualInvStatusText.equals(expectedInvStatusText));

			investigateMapScreen.clickOnAddCGI();
			addCgiFormDialog.waitForScreenLoad();
			addCgiFormDialog.enterCgiText(DataGenerator.getAddressString());
			addCgiFormDialog.clickOnCancel();
			assertTrue("Investigation Map screen did NOT load after Cancel click on Add CGI form", investigateMapScreen.waitForScreenLoad());
			return true;
		});
	}

	/**
	 * Test Case ID: TC2683_EnergyBackpack_LoggingMultipleOtherSource_Cancel
	 * Test Description: Energy Backpack - Logging multiple Other Source - Cancel
	 * Script: -
	 *	- - Log into Backpack tablet
	 *	- - Press Investigation button
	 *	- - Select a report
	 *	- - Select a LISA
	 *	- - Click Investigate button
	 *	- - Click on Add Source button
	 *	- - Click on Add Other Source button
	 *	- - Fill out all fields including GPS coordinates and click Cancel
	 * Results: -
	 *	- - User will see satellite map view
	 *	- - User will see a list of Compliance Reports assigned to him/her
	 *	- - User will see a list of LISAs from that report assigned to him/her
	 *	- - User is navigated to map showing user's position
	 *	- - Investigation Status at top of screen is set to In-Progress
	 *	- - A summary popup will appear that lists sources already added (if any) and has two buttons, Add Leak and Add Other Source
	 *	- - A popup appears where user can enter details for that leak
	 *	- - Popup disappears and Investigation status is still In Progress
	 */
	@Test
	@UseDataProvider(value = FormCancelReportDataProvider.REPORT_DATA_PROVIDER_TC2683, location = FormCancelReportDataProvider.class)
	public void TC2683_EnergyBackpack_LoggingMultipleOtherSource_Cancel(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2683_EnergyBackpack_LoggingMultipleOtherSource_Cancel ...");

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
		executeWithBackPackDataProcessesPaused(obj -> {
			mapScreen.assertMapIsLoaded();
			mapScreen.assertMapIsCenteredForPicarroUser();
			navigateToInvestigationReportScreen(investigationScreen, SurveyorConstants.USERPASSWORD);
			assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, SurveyorConstants.SQAPICDR));
			searchForReportId(investigationScreen, generatedInvReportTitle);
			initializeInvestigationScreen();
			return true;
		});

		clickOnFirstInvestigationReport(investigationScreen);

		List<InvestigationMarkerEntity> investigationMarkers = new ArrayList<InvestigationMarkerEntity>();
		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			assertTrue(investigateReportScreen.verifyMarkersForReportAreShown(generatedInvReportTitle));
			investigateReportScreen.getInvestigationMarkers().stream()
				.forEach(m -> investigationMarkers.add(m));
			return true;
		});

		final String notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);
		final String inProgress = Resources.getResource(ResourceKeys.LisaInvestigationAssignment_InProgress);
		List<String> markerStatuses = Arrays.asList(notInvestigated);
		int idx = investigateReportScreen.clickFirstMarkerMatchingStatus(markerStatuses);

		final String selectedLisa = investigationMarkers.get(idx-1).getMarkerNumber();

		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			assertTrue("Add Source button NOT displayed", investigateMapScreen.getAddSourceButton().isDisplayed());

			String actualInvStatusText = investigateMapScreen.getMarkerInvestigationStatusText().trim();
			String expectedInvStatusText = String.format("%s (%s)", selectedLisa, inProgress);
			assertTrue(String.format("Investigation marker text NOT correct. Expected=[%s]; Actual=[%s]", expectedInvStatusText, actualInvStatusText),
					actualInvStatusText.equals(expectedInvStatusText));

			// Verify buttons are displayed.
			investigateMapScreen.clickOnAddSource();
			addSourceDialog.waitForScreenLoad();
			assertTrue("Add Leak button is NOT shown.", addSourceDialog.getAddLeakButton().isDisplayed());
			assertTrue("Add Other Source button is NOT shown.", addSourceDialog.getAddOtherSourcesButton().isDisplayed());

			// Cancel add other source.
			addedSourcesListDialog.clickOnAddOtherSources();
			addOtherSourceFormDialog.waitForScreenLoad();
			addOtherSourceFormDialog.clickOnUseCurrentLocation();
			addOtherSourceFormDialog.selectLeakSource(LeakSourceType.Other_Enclosure);
			addOtherSourceFormDialog.enterAdditionalNotes(DataGenerator.getRandomText(30, 200));
			addOtherSourceFormDialog.clickOnCancel();
			addedSourcesListDialog.waitForScreenLoad();
			addedSourcesListDialog.clickOnCancel();
			assertTrue("Investigation Map screen did NOT load after Cancel click on Add Other Source form and list dialog", investigateMapScreen.waitForScreenLoad());

			actualInvStatusText = investigateMapScreen.getMarkerInvestigationStatusText().trim();
			expectedInvStatusText = String.format("%s (%s)", selectedLisa, inProgress);
			assertTrue(String.format("Investigation marker text NOT correct. Expected=[%s]; Actual=[%s]", expectedInvStatusText, actualInvStatusText),
					actualInvStatusText.equals(expectedInvStatusText));

			return true;
		});
	}

	/**
	 * Test Case ID: TC2684_EnergyBackpack_LoggingGasFound_Cancel
	 * Test Description: Energy Backpack - Logging Gas Found - Cancel
	 * Script: -
	 *	- - Log into Backpack tablet
	 *	- - Press Investigation button
	 *	- - Select a report
	 *	- - Select a LISA
	 *	- - Click Investigate button
	 *	- - Click on Add Source button
	 *	- - Click on Add Leak button
	 *	- - Fill out all fields including GPS coordinates and click Cancel
	 * Results: -
	 *	- - User will see satellite map view
	 *	- - User will see a list of Compliance Reports assigned to him/her
	 *	- - User will see a list of LISAs from that report assigned to him/her
	 *	- - User is navigated to map showing user's position
	 *	- - Investigation Status at top of screen is set to In-Progress
	 *	- - A summary popup will appear that lists sources already added (if any) and has two buttons, Add Leak and Add Other Source
	 *	- - A popup appears where user can enter details for that leak
	 *	- - Popup disappears and Investigation status is still In Progress
	 */
	@Test
	@UseDataProvider(value = FormCancelReportDataProvider.REPORT_DATA_PROVIDER_TC2684, location = FormCancelReportDataProvider.class)
	public void TC2684_EnergyBackpack_LoggingGasFound_Cancel(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2684_EnergyBackpack_LoggingGasFound_Cancel ...");

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
		executeWithBackPackDataProcessesPaused(obj -> {
			mapScreen.assertMapIsLoaded();
			mapScreen.assertMapIsCenteredForPicarroUser();
			navigateToInvestigationReportScreen(investigationScreen, SurveyorConstants.USERPASSWORD);
			assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, SurveyorConstants.SQAPICDR));
			searchForReportId(investigationScreen, generatedInvReportTitle);
			initializeInvestigationScreen();
			return true;
		});

		clickOnFirstInvestigationReport(investigationScreen);

		List<InvestigationMarkerEntity> investigationMarkers = new ArrayList<InvestigationMarkerEntity>();
		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			assertTrue(investigateReportScreen.verifyMarkersForReportAreShown(generatedInvReportTitle));
			investigateReportScreen.getInvestigationMarkers().stream()
				.forEach(m -> investigationMarkers.add(m));
			return true;
		});

		final String notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);
		final String inProgress = Resources.getResource(ResourceKeys.LisaInvestigationAssignment_InProgress);
		List<String> markerStatuses = Arrays.asList(notInvestigated);
		int idx = investigateReportScreen.clickFirstMarkerMatchingStatus(markerStatuses);

		final String selectedLisa = investigationMarkers.get(idx-1).getMarkerNumber();
		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			assertTrue("Add Source button NOT displayed", investigateMapScreen.getAddSourceButton().isDisplayed());

			// Add leak and cancel.
			investigateMapScreen.clickOnAddSource();
			addSourceDialog.waitForScreenLoad();
			assertTrue("Add Leak button is NOT shown.", addSourceDialog.getAddLeakButton().isDisplayed());
			assertTrue("Add Other Source button is NOT shown.", addSourceDialog.getAddOtherSourcesButton().isDisplayed());

			addSourceDialog.clickOnAddLeak();
			addLeakSourceFormDialog.waitForScreenLoad();
			LeakDataBuilder leakDataBuilder = LeakDataGenerator.newBuilder().generateDefaultValues();
			Map<String, Object> leakMap = leakDataBuilder.toMap();
			addLeakSourceFormDialog.fillFormAndCancel(leakMap);
			addedSourcesListDialog.waitForScreenLoad();
			addedSourcesListDialog.clickOnCancel();
			assertTrue("Investigation Map screen did NOT load after Cancel click on Add Leak form and list dialog", investigateMapScreen.waitForScreenLoad());

			String actualInvStatusText = investigateMapScreen.getMarkerInvestigationStatusText().trim();
			String expectedInvStatusText = String.format("%s (%s)", selectedLisa, inProgress);
			assertTrue(String.format("Investigation marker text NOT correct. Expected=[%s]; Actual=[%s]", expectedInvStatusText, actualInvStatusText),
					actualInvStatusText.equals(expectedInvStatusText));

			return true;
		});
	}

	// Cancel form test cases do NOT modify report data. We attempt to find existing reports in DB that can be used in the test cases.
	// In case no matching report is found in DB we create a new one.
	private void createTestCaseData(TestName testName) throws Exception {
		String methodName = testName.getMethodName();
		Integer userDataRowID = defaultUserDataRowID;
		Integer reportDataRowID1 = defaultReportDataRowID;
		String tcId = "";
		String[] lisaNumbers = {"2", "4", "6"};
		String[] tcsWithReportsThatHaveLisas = {"TC2434", "TC2436", "TC2438", "TC2440", "TC2682", "TC2683", "TC2684"};
		ArrayUtility.shuffle(tcsWithReportsThatHaveLisas);     // add randomness to input data.
		Report matchingReport = invReportDataVerifier.findReportOfMatchingPrefixWithNotInvestigatedLisaMarker(tcsWithReportsThatHaveLisas, SurveyorConstants.SQAPICDR);
		if (matchingReport != null) {
			generatedInvReportTitle = matchingReport.getReportTitle();
			return;
		}

		if (methodName.startsWith("TC2682")) {
			Object[][] tc2682 = FormCancelReportDataProvider.dataProviderAndroidApp_TC2682();
			userDataRowID = (Integer)tc2682[0][1];
			reportDataRowID1 = (Integer)tc2682[0][2];
			tcId = "TC2682";
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(true /*isReusable*/).createReportAndAssignLisasToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2683")) {
			Object[][] tc2683 = FormCancelReportDataProvider.dataProviderAndroidApp_TC2683();
			userDataRowID = (Integer)tc2683[0][1];
			reportDataRowID1 = (Integer)tc2683[0][2];
			tcId = "TC2683";
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(true /*isReusable*/).createReportAndAssignLisasToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2684")) {
			Object[][] tc2684 = FormCancelReportDataProvider.dataProviderAndroidApp_TC2684();
			userDataRowID = (Integer)tc2684[0][1];
			reportDataRowID1 = (Integer)tc2684[0][2];
			tcId = "TC2684";
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(true /*isReusable*/).createReportAndAssignLisasToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers).getReportTitle();
		}
	}

	private void initializeTestScreenObjects() {
		initializeInvestigationScreen();
		initializeInvestigateReportScreen();
		initializeInvestigateMapScreen();
		initializeAddSourceDialog();
		initializeAddLeakSourceFormDialog();
		initializeAndroidAddedLeakListDialog();
		initializeMarkerTypeDialog();
		initializeAddOtherSourceFormDialog();
		initializeAddCgiFormDialog();
		initializeConfirmationDialog();
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

	private void initializeAddSourceDialog() {
		addSourceDialog = new AndroidAddSourceDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addSourceDialog);
	}

	private void initializeMarkerTypeDialog() {
		markerTypeDialog = new AndroidMarkerTypeListControl(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), markerTypeDialog);
	}

	private void initializeAddLeakSourceFormDialog() {
		addLeakSourceFormDialog = new AndroidAddLeakSourceFormDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addLeakSourceFormDialog);
	}

	private void initializeAndroidAddedLeakListDialog() {
		addedSourcesListDialog = new AndroidAddedSourceListDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addedSourcesListDialog);
	}

	private void initializeAddOtherSourceFormDialog() {
		addOtherSourceFormDialog = new AndroidAddOtherSourceFormDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addOtherSourceFormDialog);
	}

	private void initializeAddCgiFormDialog() {
		addCgiFormDialog = new AndroidAddCgiFormDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addCgiFormDialog);
	}


	private void initializeConfirmationDialog() {
		confirmationDialog = new AndroidConfirmationDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), confirmationDialog);
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
