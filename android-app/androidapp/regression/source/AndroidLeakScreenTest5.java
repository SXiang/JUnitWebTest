package androidapp.regression.source;

import static org.junit.Assert.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.support.PageFactory;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import androidapp.dataprovider.LeakScreenDataProvider;
import androidapp.entities.source.InvestigationMarkerEntity;
import androidapp.screens.source.AndroidAddLeakSourceFormDialog;
import androidapp.screens.source.AndroidAddOtherSourceFormDialog;
import androidapp.screens.source.AndroidAddSourceDialog;
import androidapp.screens.source.AndroidAddedSourceListDialog;
import androidapp.screens.source.AndroidInvestigateMapScreen;
import androidapp.screens.source.AndroidInvestigateReportScreen;
import androidapp.screens.source.AndroidInvestigationScreen;
import androidapp.screens.source.AndroidMarkerTypeListControl;
import androidapp.screens.source.AndroidMarkerTypeListControl.MarkerType;
import common.source.BackPackAnalyzer;
import common.source.BaselineImages;
import common.source.Log;
import common.source.LogHelper;
import common.source.TestContext;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.mobile.source.LeakDataGenerator;
import surveyor.scommon.mobile.source.LeakDataGenerator.LeakDataBuilder;
import surveyor.scommon.mobile.source.ReportDataGenerator;
import surveyor.scommon.source.SurveyorConstants;

public class AndroidLeakScreenTest5 extends AndroidLeakScreenTestBase {
	private static final Integer defaultAssignedUserDataRowID = 16;
	private static final Integer defaultUserDataRowID = 6;
	private static final Integer defaultReportDataRowID = 6;

	private static final Integer PICARRO_SUPERVISOR_USER_ROW_ID = 18;

	private static String generatedInvReportTitle;

	protected AndroidInvestigationScreen investigationScreen;
	protected AndroidInvestigateReportScreen investigateReportScreen;
	protected AndroidMarkerTypeListControl markerTypeDialog;

	protected AndroidInvestigateMapScreen investigateMapScreen;
	protected AndroidAddSourceDialog addSourceDialog;

	protected AndroidAddLeakSourceFormDialog addLeakSourceFormDialog;
	protected AndroidAddedSourceListDialog addedSourcesListDialog;

	protected AndroidAddOtherSourceFormDialog addOtherSourceFormDialog;

	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static LoginPageActions loginPageAction;

	private static ThreadLocal<Boolean> appiumTestInitialized = new ThreadLocal<Boolean>();

	private static String foundGasLeak;
	private static String notInvestigated;

	@Rule
	public TestName testName = new TestName();

	@Before
	public void beforeTest() throws Exception {
		notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);
		foundGasLeak = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Found_Gas_Leak);

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
	 * Test Case ID: TC2445_EnergyBackpack_FollowButtonShowsUserItemInvestigation
	 * Test Description: Energy Backpack - "Follow" button shows user item for investigation
	 * Script: -
	 *	- - Log into the Backpack tablet
	 *	- - Launch the Backpack app
	 *	- - Click on Investigate button
	 *	- - Click on a report
	 *	- - (If no LISAs for investigation are present in that report, click on the dropdown arrow at top right and select Gaps)
	 *	- - Click on a LISA (or Gap)
	 *	- - Click on Follow button
	 *	- - Click on Follow button again
	 * Results: -
	 *	- - User is navigated to map centered on user's location
	 *	- - List of Compliance Reports is displayed
	 *	- - List of LISAs (if any) for that report is displayed
	 *	- - (List of Gaps (if any) for that report is displayed)
	 *	- - User is navigated to map centered on user's location
	 *	- - Map correctly shows location of selected LISA (or Gap). Investigation item is highlighted
	 *	- - Map is redirected back to user's location
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2445, location = LeakScreenDataProvider.class)
	public void TC2445_EnergyBackpack_FollowButtonShowsUserItemInvestigation(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2445_EnergyBackpack_FollowButtonShowsUserItemInvestigation ...");

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
		executeWithBackPackDataProcessesPaused(obj -> {
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

		List<String> markerStatuses = Arrays.asList(notInvestigated);
		int idx = investigateReportScreen.clickFirstMarkerMatchingStatus(markerStatuses);
		int markerNum = getMarkerNumber(investigationMarkers, idx-1);
		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnFollow();
			investigateMapScreen.assertPipesAndMarkerShownAreCorrect(BaselineImages.Folder.TC2639, String.format(BaselineImages.ImageFile.LisaScreenshotWithIndexPlaceholder, markerNum));
			investigateMapScreen.clickOnFollow();
			investigateMapScreen.waitForScreenLoad();
			// TBD: To be implemented.
			//investigateMapScreen.assertMapShowsPicarroUserCurrentLocation();
			return true;
		});
	}

	/**
	 * Test Case ID: TC2448_EnergyBackpack_SettingGPSCoordinates
	 * Test Description: Energy Backpack - Setting GPS coordinates
	 * Script: -
	 *	- - Log into Backpack tablet
	 *	- - Press Investigation button
	 *	- - Select a report
	 *	- - Select a LISA
	 *	- - Click Investigate button
	 *	- - Click on Add Source button
	 *	- - Click on Add Leak button
	 *	- - On Leak Details screen, click Use Current Location button
	 *	- - Add some other details and click OK
	 *	- - Click Mark As Complete
	 *	- - Check Investigation PDF or CSV for this report
	 * Results: -
	 *	- - User will see satellite map view
	 *	- - User will see a list of Compliance Reports assigned to him/her
	 *	- - User will see a list of LISAs from that report assigned to him/her
	 *	- - User is navigated to map showing user's position
	 *	- - Investigation Status at top of screen is set to In-Progress
	 *	- - A summary popup will appear that lists sources already added (if any) and has two buttons, Add Leak and Add Other Source
	 *	- - Leak Details popup appears where user can enter information for that leak
	 *	- - Latitude and Longitude values are automatically populated
	 *	- - User will see a summary of this leak
	 *	- - Investigation PDF and CSV will have the correct Latitude and Longitude coordinates
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2448, location = LeakScreenDataProvider.class)
	public void TC2448_EnergyBackpack_SettingGPSCoordinates(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2448_EnergyBackpack_SettingGPSCoordinates ...");

		String foundGasLeak = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Found_Other_Source);

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
		executeWithBackPackDataProcessesPaused(obj -> {
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

		// Markers screen. Click on LISA marked as Not Investigated
		String[] markerStatuses = {notInvestigated};
		int idx = investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatuses));

		final String selectedLisa = investigationMarkers.get(idx-1).getMarkerNumber();
		final Integer selectedLisaNum = Integer.valueOf(Arrays.asList(selectedLisa.split("-")).stream().reduce((a,b) -> b).orElse("-1").trim());

		final String inProgress = Resources.getResource(ResourceKeys.LisaInvestigationAssignment_InProgress);
		List<Map<String, Object>> listStoreMap = new ArrayList<Map<String, Object>>();
		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();

			// click on 'Investigate' and verify Investigation status.
			investigateMapScreen.clickOnInvestigate();
			String actualInvStatusText = investigateMapScreen.getMarkerInvestigationStatusText().trim();
			String expectedInvStatusText = String.format("%s (%s)", selectedLisa, inProgress);
			assertTrue(String.format("Investigation marker text NOT correct. Expected=[%s]; Actual=[%s]", expectedInvStatusText, actualInvStatusText),
					actualInvStatusText.equals(expectedInvStatusText));

			investigateMapScreen.clickOnAddSource();
			addSourceDialog.waitForScreenLoad();
			assertTrue("Add Leak button is NOT shown.", addSourceDialog.getAddLeakButton().isDisplayed());
			assertTrue("Add Other Source button is NOT shown.", addSourceDialog.getAddOtherSourcesButton().isDisplayed());

			// Add new leak.
			addSourceDialog.clickOnAddLeak();
			addLeakSourceFormDialog.waitForScreenLoad();
			LeakDataBuilder leakDataBuilder = LeakDataGenerator.newBuilder().generateDefaultValues();
			Map<String, Object> leakMap = leakDataBuilder.toMap();
			listStoreMap.add(leakMap);
			addLeakSourceFormDialog.fillForm(leakMap);
			addedSourcesListDialog.waitForScreenAndDataLoad();

			investigateMapScreen.clickOnMarkAsComplete();
			investigateReportScreen.waitForScreenLoad();
			List<InvestigationMarkerEntity> investigationMarkers2 = investigateReportScreen.getInvestigationMarkers();
			String actualMarkerStatus = investigationMarkers2.get(idx-1).getInvestigationStatus();
			assertTrue(String.format("Expected marker status NOT correct. Expected=[%s]. Actual=[%s]", foundGasLeak, actualMarkerStatus),
					actualMarkerStatus.equals(foundGasLeak));
			return true;
		});

		// In web view download investigation PDF and CSV and verify data.
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, userDataRowID);
		complianceReportsPageAction.open(EMPTY, reportDataRowID1);
		complianceReportsPageAction.updateWorkingDataRowReportTitle(reportDataRowID1, generatedInvReportTitle);
		complianceReportsPageAction.findReport(EMPTY, reportDataRowID1);
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, reportDataRowID1);
		complianceReportsPageAction.clickOnComplianceViewerInvestigationPDF(EMPTY, reportDataRowID1);
		assertTrue(complianceReportsPageAction.waitForInvestigationPDFDownloadToComplete(EMPTY, reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationData(EMPTY, reportDataRowID1);
		assertTrue(complianceReportsPageAction.waitForInvestigationCSVFileDownloadToComplete(EMPTY, reportDataRowID1));
		complianceReportsPageAction.clickOnCloseReportViewer(EMPTY, reportDataRowID1);

		List<String> lisaInvestigationPDFData = complianceReportsPageAction.getLISAInvestigationPDFData(selectedLisaNum, reportDataRowID1);
		Log.info(LogHelper.collectionToString(lisaInvestigationPDFData, "Investigation PDF table data"));

		assertTrue(String.format("PDF -> Expected marker status NOT correct. Expected=[%s]. Actual Full Text=[%s]", foundGasLeak, lisaInvestigationPDFData.get(0)),
				lisaInvestigationPDFData.get(0).contains(foundGasLeak));
		assertTrue(String.format("PDF -> Expected assigned user NOT found. Expected=[%s]. Actual Full Text=[%s]", SurveyorConstants.SQAPICDR, lisaInvestigationPDFData.get(0)),
				lisaInvestigationPDFData.get(0).contains(SurveyorConstants.SQAPICDR));

		Map<String, String> lisaInvestigationMetaData = complianceReportsPageAction.getLISAInvestigationMetaData(selectedLisaNum, reportDataRowID1);
		Log.info(String.format("Investigation CSV data -> %s", LogHelper.mapToString(lisaInvestigationMetaData)));
		assertTrue(String.format("CSV -> Expected FoundDateTime NOT correct. Length<=4. Actual Full Text=[%s]", lisaInvestigationMetaData.get("FoundDateTime")),
				lisaInvestigationMetaData.get("FoundDateTime").length()>4);
		assertTrue(String.format("CSV -> Expected Investigator NOT correct. Expected=[%s]. Actual=[%s]", SurveyorConstants.SQAPICDR, lisaInvestigationMetaData.get("Investigator")),
				lisaInvestigationMetaData.get("Investigator").equals(SurveyorConstants.SQAPICDR));
		assertTrue(String.format("CSV -> Expected InvestigationStatus NOT correct. Expected=[%s]. Actual=[%s]", foundGasLeak, lisaInvestigationMetaData.get("InvestigationStatus")),
				lisaInvestigationMetaData.get("InvestigationStatus").equals(foundGasLeak));
	}

	/**
	 * Test Case ID: TC2673_EnergyBackpack_ReportListScreenSupervisorOrHigherRole
	 * Test Description: Energy Backpack - Report List screen for Supervisor or higher role
	 * Script: -
	 *	- - Log into the tablet
	 *	- - Click on the Investigate button at the bottom of the screen
	 *	- - Log in with PCubed credentials for Supervisor role or higher user
	 *	- - Click on a report
	 *	- - Click on the Indications button near the top right. Select Gaps from the pop up menu
	 * Results: -
	 *	- - User is navigated to the map
	 *	- - User will see a list of Compliance Reports
	 *	- - User will see a list of all LISAs whether assigned to user, other user or unassigned
	 *	- - User will see a list of Gaps whether assigned to user, other user or unassigned
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2673, location = LeakScreenDataProvider.class)
	public void TC2673_EnergyBackpack_ReportListScreenSupervisorOrHigherRole(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2673_EnergyBackpack_ReportListScreenSupervisorOrHigherRole ...");

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICSU);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreen(investigationScreen, SurveyorConstants.USERPASSWORD);
			assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, SurveyorConstants.SQAPICSU));
			searchForReportId(investigationScreen, generatedInvReportTitle);
			initializeInvestigationScreen();
			return true;
		});

		clickOnFirstInvestigationReport(investigationScreen);

		List<InvestigationMarkerEntity> investigationMarkers = new ArrayList<InvestigationMarkerEntity>();
		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			assertTrue(investigateReportScreen.verifyMarkersForReportAreShown(generatedInvReportTitle));
			investigateReportScreen.clickOnInvestigationMarkerType();
			markerTypeDialog.selectMarkerType(MarkerType.Gap);
			initializeInvestigateReportScreen();
			assertTrue(investigateReportScreen.verifyMarkersForReportAreShown(generatedInvReportTitle));
			investigateReportScreen.getInvestigationMarkers().stream()
				.forEach(m -> investigationMarkers.add(m));
			return true;
		});
	}

	private void createTestCaseData(TestName testName) throws Exception {
		String methodName = testName.getMethodName();
		Integer userDataRowID = defaultUserDataRowID;
		Integer reportDataRowID1 = defaultReportDataRowID;
		String tcId = "";

		if (methodName.startsWith("TC2445")) {
			Object[][] tc2445 = LeakScreenDataProvider.dataProviderAndroidApp_TC2445();
			userDataRowID = (Integer)tc2445[0][1];
			reportDataRowID1 = (Integer)tc2445[0][2];
			tcId = "TC2445";
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(true /*isReusable*/).createReportAndAssignLisasToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1).getReportTitle();
		} else if (methodName.startsWith("TC2448")) {
			Object[][] tc2448 = LeakScreenDataProvider.dataProviderAndroidApp_TC2448();
			userDataRowID = (Integer)tc2448[0][1];
			reportDataRowID1 = (Integer)tc2448[0][2];
			tcId = "TC2448";
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(true /*isReusable*/).createReportAndAssignLisasToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1).getReportTitle();
		} else if (methodName.startsWith("TC2673")) {
			Object[][] tc2673 = LeakScreenDataProvider.dataProviderAndroidApp_TC2673();
			userDataRowID = (Integer)tc2673[0][1];
			reportDataRowID1 = (Integer)tc2673[0][2];
			tcId = "TC2673";
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(true /*isReusable*/).createReportAndAssignLisasToUser(tcId,
					userDataRowID, PICARRO_SUPERVISOR_USER_ROW_ID, reportDataRowID1).getReportTitle();
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
	}

	@Override
	protected void initializeInvestigateReportScreen() {
		investigateReportScreen = new AndroidInvestigateReportScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigateReportScreen);
	}

	@Override
	protected void initializeInvestigationScreen() {
		investigationScreen = new AndroidInvestigationScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigationScreen);
	}

	@Override
	protected void initializeInvestigateMapScreen() {
		investigateMapScreen = new AndroidInvestigateMapScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), investigateMapScreen);
	}

	@Override
	protected void initializeAddSourceDialog() {
		addSourceDialog = new AndroidAddSourceDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addSourceDialog);
	}

	@Override
	protected void initializeMarkerTypeDialog() {
		markerTypeDialog = new AndroidMarkerTypeListControl(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), markerTypeDialog);
	}

	@Override
	protected void initializeAddLeakSourceFormDialog() {
		addLeakSourceFormDialog = new AndroidAddLeakSourceFormDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addLeakSourceFormDialog);
	}

	@Override
	protected void initializeAndroidAddedLeakListDialog() {
		addedSourcesListDialog = new AndroidAddedSourceListDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addedSourcesListDialog);
	}

	@Override
	protected void initializeAddOtherSourceFormDialog() {
		addOtherSourceFormDialog = new AndroidAddOtherSourceFormDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), addOtherSourceFormDialog);
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
