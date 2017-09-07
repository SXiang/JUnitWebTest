package androidapp.regression.source;

import static org.junit.Assert.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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
import androidapp.screens.source.AndroidAlarmSettingsScreen;
import androidapp.screens.source.AndroidConfirmationDialog;
import androidapp.screens.source.AndroidInvestigateMapScreen;
import androidapp.screens.source.AndroidInvestigateReportScreen;
import androidapp.screens.source.AndroidInvestigationScreen;
import androidapp.screens.source.AndroidMarkerTypeListControl;
import androidapp.screens.source.AndroidSettingsScreen;
import common.source.BackPackAnalyzer;
import common.source.Log;
import common.source.TestContext;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataprovider.DataGenerator;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.data.UserDataReader.UserDataRow;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakSourceType;
import surveyor.scommon.mobile.source.ReportDataGenerator;

public class AndroidLeakScreenTest3 extends AndroidLeakScreenTestBase {
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
	protected AndroidConfirmationDialog confirmationDialog;
	protected AndroidSettingsScreen settingsScreen;
	protected AndroidAlarmSettingsScreen alarmSettingsScreen;

	protected AndroidAddOtherSourceFormDialog addOtherSourceFormDialog;

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
	 * Test Case ID: TC2676_ActiveInvestigationScreenShowsStatusOfInvestigation_OtherSourceFound
	 * Test Description: Active Investigation screen shows status of investigation - Other Source Found
	 * Script: -
	 *	- - Log into the tablet and launch the backpack app
	 *	- - Click on the Investigate button at bottom right
	 *	- - Log in with PCubed credentials
	 *	- - Click on a report
	 *	- - Click on a LISA
	 *	- - Click on the Investigate button at bottom left
	 *	- - Click on Add Source button
	 *	- - Click on Add Other Source button
	 *	- - Fill in details and click OK
	 *	- - Click on Mark As Complete button
	 * Results: -
	 *	- - User is navigated to the map
	 *	- - User will be prompted for PCubed password
	 *  - - User will see a list of Compliance Reports- User will see a list of LISAs for investigation
	 *  - - User will see a map centered on the backpack's location. Status is shown at top left of the map as Not Investigated. Pipe data is displayed on the map Follow and Directions buttons are present on the right and Investigate button is present at the bottom left
	 *	- - Status at top left changes to In Progress. Add Source and Add CGI buttons are added on the right. Investigate button disappears and is replaced by Mark As Complete and Pause buttons.
	 *	- - Dialog appears with Add Leak and Add Other Source buttons.
	 *	- - Form appears where user can log leak details
	 *	- - Source List screen appears
	 *	- - User is navigated back to list of LISAs and LISA investigated above has status of Other Source Found
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2676, location = LeakScreenDataProvider.class)
	public void TC2676_ActiveInvestigationScreenShowsStatusOfInvestigation_OtherSourceFound(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2676_ActiveInvestigationScreenShowsStatusOfInvestigation_OtherSourceFound ...");

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

		UserDataRow userDataRow = loginPageAction.getDataRow(userDataRowID);

		String foundOtherSource = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Found_Other_Source);
		final String assignmentInProgress = Resources.getResource(ResourceKeys.LisaInvestigationAssignment_InProgress);
		final String notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);

		navigateToMapScreen(true /*waitForMapScreenLoad*/, userDataRow.username);
		executeWithBackPackDataProcessesPaused(obj -> {
			mapScreen.assertMapIsLoaded();
			mapScreen.assertMapIsCenteredForPicarroUser();
			navigateToInvestigationReportScreen(investigationScreen, userDataRow.password);
			assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, userDataRow.username));
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

		final String selectedLisa = investigationMarkers.get(idx-1).getMarkerNumber();
		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			assertTrue("Add Source button NOT displayed", investigateMapScreen.getAddSourceButton().isDisplayed());

			String actualInvStatusText = investigateMapScreen.getMarkerInvestigationStatusText().trim();
			String expectedInvStatusText = String.format("%s (%s)", selectedLisa, assignmentInProgress);
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
			addOtherSourceFormDialog.selectLeakSource(LeakSourceType.Sewer);
			addOtherSourceFormDialog.enterAdditionalNotes(DataGenerator.getRandomText(30, 200));
			addOtherSourceFormDialog.clickOnSubmit();
			addedSourcesListDialog.waitForScreenLoad();
			assertOtherSourceListInfoIsCorrect(addedSourcesListDialog.getOtherSourcesList());
			investigateMapScreen.dismissPopup();
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnMarkAsComplete();

			investigateReportScreen.waitForScreenLoad();
			String actualMarkerStatus = investigateReportScreen.getInvestigationMarkers().get(idx-1).getInvestigationStatus();
			Log.info(String.format("Expected marker status=[%s]. Found marker status=[%s]", foundOtherSource, actualMarkerStatus));
			assertTrue(String.format("Incorrect marker status found. Expected=[%s]. Actual=[%s]", foundOtherSource, actualMarkerStatus),
					actualMarkerStatus.equals(foundOtherSource));

			return true;
		});
	}

	/**
	 * Test Case ID: TC2677_ActiveInvestigationScreenShowsStatusOfInvestigation_NoGasFound
	 * Test Description: Active Investigation screen shows status of investigation - No Gas Found
	 * Script: -
	 *	- - Log into the tablet and launch the backpack app
	 *	- - Click on the Investigate button at bottom right
	 *	- - Log in with PCubed credentials
	 *	- - Click on a report
	 *	- - Click on a LISA
	 *	- - Click on the Investigate button at bottom left
	 *	- - Click on Mark As Complete button
	 *	- - Click No
	 * Results: -
	 *	- - User is navigated to the map
	 *	- - User will be prompted for PCubed password
	 *  - - User will see a list of Compliance Reports
	 *  - - User will see a list of LISAs for investigation
	 *  - - User will see a map centered on the backpack's location. Status is shown at top left of the map as Not Investigated.
	 *  - - Pipe data is displayed on the map Follow and Directions buttons are present on the right and Investigate button is present at the bottom left
	 *	- - Status at top left changes to In Progress. Add Source and Add CGI buttons are added on the right. Investigate button disappears and is replaced by Mark As Complete and Pause buttons.
	 *	- - Dialog appears asking if leak was found
	 *	- - User is navigated back to list of LISAs and LISA investigated above has status of No Gas Found
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2677, location = LeakScreenDataProvider.class)
	public void TC2677_ActiveInvestigationScreenShowsStatusOfInvestigation_NoGasFound(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2677_ActiveInvestigationScreenShowsStatusOfInvestigation_NoGasFound ...");

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

		UserDataRow userDataRow = loginPageAction.getDataRow(userDataRowID);

		final String noGasFound = Resources.getResource(ResourceKeys.InvestigationStatusTypes_No_Gas_Found);
		final String assignmentInProgress = Resources.getResource(ResourceKeys.LisaInvestigationAssignment_InProgress);
		final String notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);

		navigateToMapScreen(true /*waitForMapScreenLoad*/, userDataRow.username);
		executeWithBackPackDataProcessesPaused(obj -> {
			mapScreen.assertMapIsLoaded();
			mapScreen.assertMapIsCenteredForPicarroUser();
			navigateToInvestigationReportScreen(investigationScreen, userDataRow.password);
			assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, userDataRow.username));
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
		final String selectedLisa = investigationMarkers.get(idx-1).getMarkerNumber();
		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();

			assertTrue("Add CGI button NOT displayed", investigateMapScreen.getAddCGIButton().isDisplayed());
			assertTrue("Add Source button NOT displayed", investigateMapScreen.getAddSourceButton().isDisplayed());
			assertTrue("Follow button NOT displayed", investigateMapScreen.getFollowButton().isDisplayed());
			assertTrue("Directions button NOT displayed", investigateMapScreen.getDirectionsButton().isDisplayed());
			investigateMapScreen.assertMarkAsCompleteAndPauseButtonsAreShown();
			assertTrue("MarkAsComplete button NOT displayed", investigateMapScreen.getMarkAsCompleteButton().isDisplayed());
			assertTrue("Pause button NOT displayed", investigateMapScreen.getPauseButton().isDisplayed());

			String actualInvStatusText = investigateMapScreen.getMarkerInvestigationStatusText().trim();
			String expectedInvStatusText = String.format("%s (%s)", selectedLisa, assignmentInProgress);
			assertTrue(String.format("Investigation marker text NOT correct. Expected=[%s]; Actual=[%s]", expectedInvStatusText, actualInvStatusText),
					actualInvStatusText.equals(expectedInvStatusText));

			investigateMapScreen.clickOnMarkAsComplete();
			confirmationDialog.waitForScreenLoad();
			confirmationDialog.clickOnCancel();
			investigateReportScreen.waitForScreenLoad();
			String actualMarkerStatus = investigateReportScreen.getInvestigationMarkers().get(idx-1).getInvestigationStatus();
			Log.info(String.format("Expected marker status=[%s]. Found marker status=[%s]", noGasFound, actualMarkerStatus));
			assertTrue(String.format("Incorrect marker status found. Expected=[%s]. Actual=[%s]", noGasFound, actualMarkerStatus),
					actualMarkerStatus.equals(noGasFound));

			return true;
		});
	}

	/**
	 * Test Case ID: TC2681_ClickingPauseInvestigationMapReturnsAppInvestigationHomeScreen
	 * Test Description: Clicking Pause on Investigation map returns app to Investigation Home screen
	 * Script: -
	 *	- - Log into the tablet and launch the backpack app
	 *	- - Click on the Investigate button at bottom right
	 *	- - Log in with PCubed credentials
	 *	- - Click on a report
	 *	- - Click on a LISA marked as No Gas Found
	 *	- - Click on the Investigate button at bottom left
	 *	- - Click on the Pause button
	 * Results: -
	 *	- - User is navigated to the map
	 *	- - User will be prompted for PCubed password- User will see a list of Compliance Reports
	 *  - - User will see a list of LISAs for investigation- User will see a map centered on the backpack's location.
	 *  - - Status is shown at top left of the map as No Gas Found. Pipe data is displayed on the map Follow and Directions buttons are present on the right and Investigate button is present at the bottom left
	 *	- - Status at top left changes to In Progress. Add Source and Add CGI buttons are added on the right.
	 *  - - Investigate button disappears and is replaced by Mark As Complete and Pause buttons.
	 *	- - Dialog appears with Add Leak and Add Other Source buttons
	 *	- - App returns to Investigation Home screen with list of LISAs. LISA investigated above will have status of In Progress
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2681, location = LeakScreenDataProvider.class)
	public void TC2681_ClickingPauseInvestigationMapReturnsAppInvestigationHomeScreen(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2681_ClickingPauseInvestigationMapReturnsAppInvestigationHomeScreen ...");

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

		UserDataRow userDataRow = loginPageAction.getDataRow(userDataRowID);

		final String noGasFound = Resources.getResource(ResourceKeys.InvestigationStatusTypes_No_Gas_Found);
		final String assignmentInProgress = Resources.getResource(ResourceKeys.LisaInvestigationAssignment_InProgress);
		final String inProgress = Resources.getResource(ResourceKeys.InvestigationStatusTypes_In_Progress);
		final String notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);

		navigateToMapScreen(true /*waitForMapScreenLoad*/, userDataRow.username);
		executeWithBackPackDataProcessesPaused(obj -> {
			mapScreen.assertMapIsLoaded();
			mapScreen.assertMapIsCenteredForPicarroUser();
			navigateToInvestigationReportScreen(investigationScreen, userDataRow.password);
			assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, userDataRow.username));
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

		if (!hasMarkerOfStatus(investigationMarkers, noGasFound)) {
			Log.info("Did NOT find a marker of status 'No Gas Found'... Investigating a 'Not Investigated' marker and changing status to 'No Gas Found'");
			investigateNotInvestigatedMarkerToNoGasFound(investigateReportScreen, investigateMapScreen, confirmationDialog, noGasFound, notInvestigated);
		}

		List<String> markerStatuses = Arrays.asList(noGasFound);
		int idx = investigateReportScreen.clickFirstMarkerMatchingStatus(markerStatuses);
		final String selectedLisa = investigationMarkers.get(idx-1).getMarkerNumber();
		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();

			assertTrue("Add CGI button NOT displayed", investigateMapScreen.getAddCGIButton().isDisplayed());
			assertTrue("Add Source button NOT displayed", investigateMapScreen.getAddSourceButton().isDisplayed());
			assertTrue("Follow button NOT displayed", investigateMapScreen.getFollowButton().isDisplayed());
			assertTrue("Directions button NOT displayed", investigateMapScreen.getDirectionsButton().isDisplayed());
			investigateMapScreen.assertMarkAsCompleteAndPauseButtonsAreShown();
			assertTrue("MarkAsComplete button NOT displayed", investigateMapScreen.getMarkAsCompleteButton().isDisplayed());
			assertTrue("Pause button NOT displayed", investigateMapScreen.getPauseButton().isDisplayed());

			String actualInvStatusText = investigateMapScreen.getMarkerInvestigationStatusText().trim();
			String expectedInvStatusText = String.format("%s (%s)", selectedLisa, assignmentInProgress);
			assertTrue(String.format("Investigation marker text NOT correct. Expected=[%s]; Actual=[%s]", expectedInvStatusText, actualInvStatusText),
					actualInvStatusText.equals(expectedInvStatusText));

			investigateMapScreen.clickOnPause();
			investigateReportScreen.waitForScreenLoad();
			String actualMarkerStatus = investigateReportScreen.getInvestigationMarkers().get(idx-1).getInvestigationStatus();
			Log.info(String.format("Expected marker status=[%s]. Found marker status=[%s]", inProgress, actualMarkerStatus));
			assertTrue(String.format("Incorrect marker status found. Expected=[%s]. Actual=[%s]", inProgress, actualMarkerStatus),
					actualMarkerStatus.equals(inProgress));

			return true;
		});
	}

	/**
	 * Test Case ID: TC2687_EnergyBackpack_AppAppearane
	 * Test Description: Energy Backpack - App Appearane
	 * Script: -
	 *	- - Log into Tablet and launch Backpack app
	 *	- - Click on the Menu at bottom right
	 *	- - Click Alarm Settings
	 * Results: -
	 *	- - Concentration Chart appears at top left with black background. Methane value appears at top right with black background and disposition.
	 *  - - The Toggle Mode, Reset Max and Investigate buttons at the bottom of the page all appear with black buttons and white font
	 *	- The GPS indicator at bottom left is green when GPS signal is good, red with a line through it when no GPS signal is received
	 *	- - Menu items Clear Heatmap, Alarm Settings, App Settings and Shutdown Instrument appear. Menu background is black, buttons are grey and font is white
	 *	- - Alarm Settings include only Volume, Amplitude and Threshold
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2687, location = LeakScreenDataProvider.class)
	public void TC2687_EnergyBackpack_AppAppearance(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2687_EnergyBackpack_AppAppearance ...");

		navigateToMapScreenUsingDefaultCreds(true /*waitForMapScreenLoad*/);
		executeWithBackPackDataProcessesPaused(obj -> {
			Log.info("Map screen loaded successfully!");

			mapScreen.assertConcentrationChartIsShown();
			mapScreen.assertBottomPaneButtonsAreCorrect();
			mapScreen.assertMethaneModeIsShownInTopPanel();
			mapScreen.assertDefaultMaxValueShownInTopPanelIsCorrect();
			mapScreen.assertDefaultMethaneValueShownInTopPanelIsCorrect();
			mapScreen.assertGpsLabelIsGreen();

			// PARTIAL: As discussed with Praki, Gps is RED check skipped. Turn off GPS on emulator does not simulate this condition.
			// Currently no known way to simulate this condition from code. Skipping the GPS is red check.

			mapScreen.clickOnMenuButton();
			settingsScreen.waitForScreenLoad();
			assertTrue("Clear HeatMap button should be displayed", settingsScreen.getClearHeatmap().isDisplayed());
			assertTrue("Alarm Settings button should be displayed", settingsScreen.getAlarmSettings().isDisplayed());
			assertTrue("App Settings button should be displayed", settingsScreen.getAppSettings().isDisplayed());
			assertTrue("Shutdown Instrument button should be displayed", settingsScreen.getShutdownInstrument().isDisplayed());

			settingsScreen.clickOnAlarmSettings();
			alarmSettingsScreen.waitForScreenLoad();
			alarmSettingsScreen.assertSlidersShownAreCorrect();
			alarmSettingsScreen.slideToVolume(4.0f);
			alarmSettingsScreen.slideToAmplitudeppm(40.0f);
			alarmSettingsScreen.slideToThresholdppm(24.0f);
			alarmSettingsScreen.clickOnApply();
			return true;
		});
	}

	private void createTestCaseData(TestName testName) throws Exception {
		String methodName = testName.getMethodName();
		Integer userDataRowID = defaultUserDataRowID;
		Integer reportDataRowID1 = defaultReportDataRowID;
		String tcId = "";
		String[] lisaNumbers = {"1", "2", "3", "4", "5", "6"};
		if (methodName.startsWith("TC2676")) {
			Object[][] tc2676 = LeakScreenDataProvider.dataProviderAndroidApp_TC2676();
			userDataRowID = (Integer)tc2676[0][1];
			reportDataRowID1 = (Integer)tc2676[0][2];
			tcId = "TC2676";
		} else if (methodName.startsWith("TC2677")) {
			Object[][] tc2677 = LeakScreenDataProvider.dataProviderAndroidApp_TC2677();
			userDataRowID = (Integer)tc2677[0][1];
			reportDataRowID1 = (Integer)tc2677[0][2];
			tcId = "TC2677";
		} else if (methodName.startsWith("TC2681")) {
			Object[][] tc2681 = LeakScreenDataProvider.dataProviderAndroidApp_TC2681();
			userDataRowID = (Integer)tc2681[0][1];
			reportDataRowID1 = (Integer)tc2681[0][2];
			tcId = "TC2681";
		} else if (methodName.startsWith("TC2687")) {
			return;
		}

		String username = loginPageAction.getUsernamePassword(EMPTY, userDataRowID).username;
		Report matchingReport = invReportDataVerifier.findReportOfMatchingPrefixWithNotInvestigatedLisaMarker(new String[] {tcId}, username);
		if (matchingReport != null) {
			generatedInvReportTitle = matchingReport.getReportTitle();
			return;
		}

		generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(false /*isReusable*/).createReportAndAssignLisasToUser(tcId,
				defaultUserDataRowID, userDataRowID, reportDataRowID1, lisaNumbers).getReportTitle();
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
		initializeConfirmationDialog();
		initializeSettingsScreen();
		initializeAlarmSettingsScreen();
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

	private void initializeConfirmationDialog() {
		confirmationDialog = new AndroidConfirmationDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), confirmationDialog);
	}

	protected void initializeSettingsScreen() {
		settingsScreen = new AndroidSettingsScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), settingsScreen);
	}

	protected void initializeAlarmSettingsScreen() {
		alarmSettingsScreen = new AndroidAlarmSettingsScreen(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), alarmSettingsScreen);
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