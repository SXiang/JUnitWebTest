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
import androidapp.screens.source.ScreenVerifier;
import common.source.BackPackAnalyzer;
import common.source.BaselineImages;
import common.source.Log;
import common.source.RegexUtility;
import common.source.TestContext;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.data.UserDataReader.UserDataRow;
import surveyor.scommon.mobile.source.LeakDataGenerator;
import surveyor.scommon.mobile.source.ReportDataGenerator;
import surveyor.scommon.mobile.source.LeakDataGenerator.LeakDataBuilder;
import surveyor.scommon.mobile.source.LeakDataTypes.SourceType;

public class AndroidLeakScreenTest4 extends AndroidLeakScreenTestBase {
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

	private static LoginPageActions loginPageAction;

	private static ThreadLocal<Boolean> appiumTestInitialized = new ThreadLocal<Boolean>();

	private static String foundGasLeak;
	private static String notInvestigated;

	@Rule
	public TestName testName = new TestName();

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects(false);
	}

	@Before
	public void beforeTest() throws Exception {
		notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);
		foundGasLeak = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Found_Gas_Leak);

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
	 * Test Case ID: TC2432_EnergyBackpack_InvestigateLISA
	 * Test Description: Energy Backpack - Investigate LISA
	 * Script: -
	 *	- - Log into the tablet and launch the Backpack app
	 *	- - Click on the Investigate button at the bottom right of the screen
	 *	- - Log in with PCubed credentials, then click on a report
	 *	- - Click on one of the LISAs
	 *	- - Click on the Follow button
	 *	- - Click the Investigate button at bottom left
	 *	- - Click the Add Source button
	 *	- - Click the Add Leak button
	 *	- - Fill out each of the fields and click OK
	 *	- - Click Mark As Complete
	 * Results: -
	 *	- - User is navigated to the map
	 *	- - User will see a list of Compliance Reports
	 *	- - User will see a list of LISAs for investigation
	 *	- -  User will see a map centered on the backpack's location. Pipe data is  displayed on the map Follow and Directions buttons are present on  the right and Investigate button is present at the bottom left
	 *	- - Map is now centered on selected LISA. Pipe data is displayed on the map. Bubble indicates LISA number and bubble color indicates investigation status.
	 *	- -  Add Source and Add CGI buttons are added on the right.  Investigate button disappears and is replaced by Mark As Complete  and Pause buttons
	 *	- - Dialog appears with Add Leak and Add Other Source buttons
	 *	- - A form appears where user can log details of the leak
	 *	- - The previous dialog appears with a summary of the leak details that were just entered
	 *	- - User is navigated back to the list of LISAs
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2432, location = LeakScreenDataProvider.class)
	public void TC2432_EnergyBackpack_InvestigateLISA(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2432_EnergyBackpack_InvestigateLISA ...");

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

		UserDataRow userDataRow = loginPageAction.getDataRow(userDataRowID);

		navigateToMapScreen(true /*waitForMapScreenLoad*/, userDataRow.username);
		executeWithBackPackDataProcessesPaused(obj -> {
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
		int markerNum = getMarkerNumber(investigationMarkers, idx-1);
		List<Map<String, Object>> listStoreMap = new ArrayList<Map<String, Object>>();
		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();

			// Verify 'not investigated' LISA and bubble color
			investigateMapScreen.clickOnFollow();
			investigateMapScreen.assertPipesAndMarkerShownAreCorrect(BaselineImages.Folder.TC2432, String.format(BaselineImages.ImageFile.LisaScreenshotWithIndexPlaceholder, markerNum));
			investigateMapScreen.clickOnFollow();

			investigateMapScreen.clickOnInvestigate();
			assertTrue("Add Source button NOT displayed", investigateMapScreen.getAddSourceButton().isDisplayed());
			assertTrue("Add CGI button NOT displayed", investigateMapScreen.getAddCGIButton().isDisplayed());

			investigateMapScreen.clickOnFooterInvestigate();
			investigationScreen.waitForScreenLoad();
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

		investigateReportScreen.clickOnMarkerAtIndex(idx);

		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();

			// Verify 'in-progress' LISA and bubble color.
			investigateMapScreen.clickOnFollow();
			investigateMapScreen.assertPipesAndMarkerShownAreCorrect(BaselineImages.Folder.TC2432, String.format(BaselineImages.ImageFile.LisaInProgressScreenshotWithIndexPlaceholder, markerNum));
			investigateMapScreen.clickOnFollow();

			// Verify buttons are displayed.
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

			// Open added leak, verify data is correct.
			assertLeakListInfoIsCorrect(leakDataBuilder, addedSourcesListDialog.getLeaksList(), 0);
			addedSourcesListDialog.clickOnMatchingListItemOfTypeAtIndex(SourceType.Leak, 0);
			addLeakSourceFormDialog.waitForScreenLoad();
			assertTrue("Leak Info shown in form is NOT correct.", addLeakSourceFormDialog.verifyCorrectDataIsShown(leakMap, true /*isInEditMode*/));
			addLeakSourceFormDialog.tapOnCancel();

			// Mark as complete.
			investigateMapScreen.dismissPopup();
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnMarkAsComplete();
			investigateReportScreen.waitForScreenLoad();
			String actualMarkerStatus = investigateReportScreen.getInvestigationMarkers().get(idx-1).getInvestigationStatus();
			Log.info(String.format("Expected marker status=[%s]. Found marker status=[%s]", foundGasLeak, actualMarkerStatus));
			assertTrue(String.format("Incorrect marker status found. Expected=[%s]. Actual=[%s]", foundGasLeak, actualMarkerStatus),
					actualMarkerStatus.equals(foundGasLeak));

			return true;
		});

		// Re-open the investigated marker and verify LISA and bubble color are for an investigated marker.
		investigateReportScreen.clickOnMarkerAtIndex(idx);
		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnFollow();
			investigateMapScreen.assertPipesAndMarkerShownAreCorrect(BaselineImages.Folder.TC2432, String.format(BaselineImages.ImageFile.LisaFoundGasLeakScreenshotWithIndexPlaceholder, markerNum));
			return true;
		});
	}

	/**
	 * Test Case ID: TC2639_EnergyBackpack_InvestigateLISAAssetBox
	 * Test Description: Energy Backpack - Investigate LISA Asset Box
	 * Script: -
	 *	- - Log into the tablet and launch the Backpack app
	 *	- - Click on the Investigate button at the bottom right of the screen
	 *	- - Log in with PCubed credentials, then click on a report
	 *	- - Click on one of the LISAs
	 *	- - Click on the Follow button
	 *	- - Click the Investigate button at bottom left
	 *	- - Click the Add Source button
	 *	- - Click the Add Leak button
	 *	- - Fill out each of the fields and click OK
	 *	- - Click Mark As Complete
	 * Results: -
	 *	- - User is navigated to the map
	 *	- - User will see a list of Compliance Reports
	 *	- - User will see a list of LISAs for investigation
	 *	- - User will see a map centered on the backpack's location. Follow and Directions buttons are present on the right and Investigate button is present at the bottom left. LISA name is present at top left along with Status of "Not Investigated", Precision, Velocity and Lat Long. Assets are displayed in purple
	 *	- - Map is now centered on selected LISA Asset Box. Box is outlined in red and pipe is highlighted in green. Bubble indicates LISA asset box number and bubble color indicates investigation status. Border around highlighted pipe matches color of bubble. Lat Long, Precision and Velocity disappear
	 *	- - Add Source and Add CGI buttons are added on the right. Investigate button disappears and is replaced by Mark As Complete and Pause buttons. Status at top left changes to "In Progress"
	 *	- - Dialog appears with Add Leak and Add Other Source buttons
	 *	- - A form appears where user can log details of the leak
	 *	- - The previous dialog appears with a summary of the leak details that were just entered
	 *	- - User is navigated back to the list of LISAs
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2639, location = LeakScreenDataProvider.class)
	public void TC2639_EnergyBackpack_InvestigateLISAAssetBox(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2639_EnergyBackpack_InvestigateLISAAssetBox ...");

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

		UserDataRow userDataRow = loginPageAction.getDataRow(userDataRowID);

		navigateToMapScreen(true /*waitForMapScreenLoad*/, userDataRow.username);
		executeWithBackPackDataProcessesPaused(obj -> {
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

		final String inProgress = Resources.getResource(ResourceKeys.LisaInvestigationAssignment_InProgress);

		List<String> markerStatuses = Arrays.asList(notInvestigated);
		int idx = investigateReportScreen.clickFirstMarkerMatchingStatus(markerStatuses);
		int markerNum = getMarkerNumber(investigationMarkers, idx-1);
		final String selectedLisa = investigationMarkers.get(idx-1).getMarkerNumber();
		List<Map<String, Object>> listStoreMap = new ArrayList<Map<String, Object>>();
		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();

			String actualInvStatusText = investigateMapScreen.getMarkerInvestigationStatusText().trim();
			String expectedInvStatusText = String.format("%s (%s)", selectedLisa, notInvestigated);
			assertTrue(String.format("Investigation marker text NOT correct. Expected=[%s]; Actual=[%s]", expectedInvStatusText, actualInvStatusText),
					actualInvStatusText.equals(expectedInvStatusText));

			if (TestContext.INSTANCE.isRunningOnAndroidDevice()) {
				String actualLatLong = investigateMapScreen.getLatitudeLongitudeText().trim();
				assertTrue(String.format("Latitude Longitude text - [%s] did NOT match expression - [%s]", actualLatLong,
						RegexUtility.PICARRO_APP_MARKER_LAT_LONG_MATCH_PATTERN), RegexUtility.matchesPattern(actualLatLong, RegexUtility.PICARRO_APP_MARKER_LAT_LONG_MATCH_PATTERN));
			}

			// Verify Box color for 'Not-Investigated' state
			investigateMapScreen.clickOnFollow();
			investigateMapScreen.assertPipesAndMarkerShownAreCorrect(BaselineImages.Folder.TC2639, String.format(BaselineImages.ImageFile.AssetBoxScreenshotWithIndexPlaceholder, markerNum));
			investigateMapScreen.clickOnFollow();

			if (TestContext.INSTANCE.isRunningOnAndroidDevice()) {
				String actualLatLong = investigateMapScreen.getLatitudeLongitudeText().trim();
				assertTrue(String.format("Latitude Longitude text - [%s] did NOT match expression - [%s]", actualLatLong,
						RegexUtility.PICARRO_APP_MARKER_LAT_LONG_MATCH_PATTERN), RegexUtility.matchesPattern(actualLatLong, RegexUtility.PICARRO_APP_MARKER_LAT_LONG_MATCH_PATTERN));
			}

			investigateMapScreen.clickOnInvestigate();
			assertTrue("Add CGI button NOT displayed", investigateMapScreen.getAddCGIButton().isDisplayed());
			assertTrue("Add Source button NOT displayed", investigateMapScreen.getAddSourceButton().isDisplayed());
			assertTrue("Follow button NOT displayed", investigateMapScreen.getFollowButton().isDisplayed());
			assertTrue("Directions button NOT displayed", investigateMapScreen.getDirectionsButton().isDisplayed());
			investigateMapScreen.assertMarkAsCompleteAndPauseButtonsAreShown();

			// Status changes to 'In-Progress'
			actualInvStatusText = investigateMapScreen.getMarkerInvestigationStatusText().trim();
			expectedInvStatusText = String.format("%s (%s)", selectedLisa, inProgress);
			assertTrue(String.format("Investigation marker text NOT correct. Expected=[%s]; Actual=[%s]", expectedInvStatusText, actualInvStatusText),
					actualInvStatusText.equals(expectedInvStatusText));

			investigateMapScreen.clickOnFooterInvestigate();
			investigationScreen.waitForScreenLoad();
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

		investigateReportScreen.clickOnMarkerAtIndex(idx);

		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();

			investigateMapScreen.clickOnInvestigate();

			// Verify Box color in 'In-Progress' state
			investigateMapScreen.clickOnFollow();
			investigateMapScreen.assertPipesAndMarkerShownAreCorrect(BaselineImages.Folder.TC2639, String.format(BaselineImages.ImageFile.AssetBoxInProgressScreenshotWithIndexPlaceholder, markerNum));
			investigateMapScreen.clickOnFollow();

			// Verify buttons are displayed.
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

			// Open added leak, verify data is correct.
			assertLeakListInfoIsCorrect(leakDataBuilder, addedSourcesListDialog.getLeaksList(), 0);
			addedSourcesListDialog.clickOnMatchingListItemOfTypeAtIndex(SourceType.Leak, 0);
			addLeakSourceFormDialog.waitForScreenLoad();
			assertTrue("Leak Info shown in form is NOT correct.", addLeakSourceFormDialog.verifyCorrectDataIsShown(leakMap, true /*isInEditMode*/));
			addLeakSourceFormDialog.tapOnCancel();

			// Mark as complete.
			investigateMapScreen.dismissPopup();
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnMarkAsComplete();
			investigateReportScreen.waitForScreenLoad();
			String actualMarkerStatus = investigateReportScreen.getInvestigationMarkers().get(idx-1).getInvestigationStatus();
			Log.info(String.format("Expected marker status=[%s]. Found marker status=[%s]", foundGasLeak, actualMarkerStatus));
			assertTrue(String.format("Incorrect marker status found. Expected=[%s]. Actual=[%s]", foundGasLeak, actualMarkerStatus),
					actualMarkerStatus.equals(foundGasLeak));

			return true;
		});

		// Re-open the investigated marker and verify assetbox and bubble color are for an investigated marker.
		investigateReportScreen.clickOnMarkerAtIndex(idx);
		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnFollow();
			investigateMapScreen.assertPipesAndMarkerShownAreCorrect(BaselineImages.Folder.TC2639, String.format(BaselineImages.ImageFile.AssetBoxFoundGasLeakScreenshotWithIndexPlaceholder, markerNum));
			return true;
		});
	}

	/**
	 * Test Case ID: TC2640_EnergyBackpack_InvestigateGapBox
	 * Test Description: Energy Backpack - Investigate Gap Box
	 * Script: -
	 *	- - Log into the tablet and launch the Backpack app
	 *	- - Click on the Investigate button at the bottom right of the screen
	 *	- - Log in with PCubed credentials, then click on a report
	 *	- - From the dropdown, select Gaps
	 *	- - Click on one of the Gaps
	 *	- - Click on the Follow button
	 *	- - Click the Investigate button at bottom left
	 *	- - Click the Add Source button
	 *	- - Click the Add Leak button
	 *	- - Fill out each of the fields and click OK
	 *	- - Click Mark As Complete
	 * Results: -
	 *	- - User is navigated to the map
	 *	- - User will see a list of Compliance Reports
	 *	- - User will see a list of LISAs for investigation
	 *	- - User will see a list of Gaps for investigation
	 *	- - User will see a map centered on the backpack's location. "Follow" and "Directions" buttons are present on the right and "Investigate" button is present at the bottom left. Gap name is present at top left along with Status of "Not Investigated" and Lat Long coordinates. Assets are displayed in purple
	 *	- - Map is now centered on selected Gap Asset Box. Box is outlined in red and pipe is highlighted in green. Bubble indicates Gap box number and bubble color indicates investigation status. Border around highlighted pipe matches color of bubble.
	 *	- - Add Source and Add CGI buttons are added on the right. Investigate button disappears and is replaced by Mark As Complete and Pause buttons
	 *	- - Dialog appears with Add Leak and Add Other Source buttons
	 *	- - A form appears where user can log details of the leak
	 *	- - The previous dialog appears with a summary of the leak details that were just entered
	 *	- - User is navigated back to the list of Gapss
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2640, location = LeakScreenDataProvider.class)
	public void TC2640_EnergyBackpack_InvestigateGapBox(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2640_EnergyBackpack_InvestigateGapBox ...");

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

		UserDataRow userDataRow = loginPageAction.getDataRow(userDataRowID);

		navigateToMapScreen(true /*waitForMapScreenLoad*/, userDataRow.username);
		executeWithBackPackDataProcessesPaused(obj -> {
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
			investigateReportScreen.clickOnInvestigationMarkerType();
			markerTypeDialog.selectMarkerType(MarkerType.Gap);
			investigateReportScreen.waitForMarkerTypeGapToBeSelected();
			initializeInvestigateReportScreen();
			assertTrue(investigateReportScreen.verifyMarkersForReportAreShown(generatedInvReportTitle));
			investigateReportScreen.getInvestigationMarkers().stream()
				.forEach(m -> investigationMarkers.add(m));
			return true;
		});

		List<String> markerStatuses = Arrays.asList(notInvestigated);
		int idx = investigateReportScreen.clickFirstMarkerMatchingStatus(markerStatuses);
		int markerNum = getMarkerNumber(investigationMarkers, idx-1);
		List<Map<String, Object>> listStoreMap = new ArrayList<Map<String, Object>>();
		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			final String selectedGap = investigationMarkers.get(idx-1).getMarkerNumber();
			String actualInvStatusText = investigateMapScreen.getMarkerInvestigationStatusText().trim();
			String expectedInvStatusText = String.format("%s (%s)", selectedGap, notInvestigated);
			assertTrue(String.format("Investigation marker text NOT correct. Expected=[%s]; Actual=[%s]", expectedInvStatusText, actualInvStatusText),
					actualInvStatusText.equals(expectedInvStatusText));

			if (TestContext.INSTANCE.isRunningOnAndroidDevice()) {
				String actualLatLong = investigateMapScreen.getLatitudeLongitudeText().trim();
				assertTrue(String.format("Latitude Longitude text - [%s] did NOT match expression - [%s]", actualLatLong,
						RegexUtility.PICARRO_APP_MARKER_LAT_LONG_MATCH_PATTERN), RegexUtility.matchesPattern(actualLatLong, RegexUtility.PICARRO_APP_MARKER_LAT_LONG_MATCH_PATTERN));
			}

			// Verify 'not-investigated' Gap and bubble color.
			investigateMapScreen.clickOnFollow();
			investigateMapScreen.assertPipesAndMarkerShownAreCorrect(BaselineImages.Folder.TC2640, String.format(BaselineImages.ImageFile.GapScreenshotWithIndexPlaceholder, idx));
			investigateMapScreen.clickOnFollow();

			investigateMapScreen.clickOnInvestigate();
			assertTrue("Add Source button NOT displayed", investigateMapScreen.getAddSourceButton().isDisplayed());
			assertTrue("Add CGI button NOT displayed", investigateMapScreen.getAddCGIButton().isDisplayed());

			investigateMapScreen.clickOnFooterInvestigate();
			investigationScreen.waitForScreenLoad();
			searchForReportId(investigationScreen, generatedInvReportTitle);
			initializeInvestigationScreen();
			return true;
		});

		clickOnFirstInvestigationReport(investigationScreen);
		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			assertTrue(investigateReportScreen.verifyMarkersForReportAreShown(generatedInvReportTitle));
			investigateReportScreen.clickOnInvestigationMarkerType();
			markerTypeDialog.selectMarkerType(MarkerType.Gap);
			investigateReportScreen.waitForMarkerTypeGapToBeSelected();
			initializeInvestigateReportScreen();
			return true;
		});

		investigateReportScreen.clickOnMarkerAtIndex(idx);

		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();

			investigateMapScreen.clickOnInvestigate();
			assertTrue("Add Source button NOT displayed", investigateMapScreen.getAddSourceButton().isDisplayed());
			assertTrue("Add CGI button NOT displayed", investigateMapScreen.getAddCGIButton().isDisplayed());

			// Verify 'in-progress' Gap and bubble color.
			investigateMapScreen.clickOnFollow();
			investigateMapScreen.assertPipesAndMarkerShownAreCorrect(BaselineImages.Folder.TC2640, String.format(BaselineImages.ImageFile.GapInProgressScreenshotWithIndexPlaceholder, markerNum));
			investigateMapScreen.clickOnFollow();

			if (TestContext.INSTANCE.isRunningOnAndroidDevice()) {
				String actualLatLong = investigateMapScreen.getLatitudeLongitudeText().trim();
				assertTrue(String.format("Latitude Longitude text - [%s] did NOT match expression - [%s]", actualLatLong,
						RegexUtility.PICARRO_APP_MARKER_LAT_LONG_MATCH_PATTERN), RegexUtility.matchesPattern(actualLatLong, RegexUtility.PICARRO_APP_MARKER_LAT_LONG_MATCH_PATTERN));
			}

			// Verify buttons are displayed.
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

			// Open added leak, verify data is correct.
			assertLeakListInfoIsCorrect(leakDataBuilder, addedSourcesListDialog.getLeaksList(), 0);
			addedSourcesListDialog.clickOnMatchingListItemOfTypeAtIndex(SourceType.Leak, 0);
			addLeakSourceFormDialog.waitForScreenLoad();
			assertTrue("Leak Info shown in form is NOT correct.", addLeakSourceFormDialog.verifyCorrectDataIsShown(leakMap, true /*isInEditMode*/));
			addLeakSourceFormDialog.tapOnCancel();

			// Mark as complete.
			investigateMapScreen.dismissPopup();
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnMarkAsComplete();
			investigateReportScreen.waitForScreenLoad();
			investigateReportScreen.clickOnInvestigationMarkerType();
			markerTypeDialog.selectMarkerType(MarkerType.Gap);
			investigateReportScreen.waitForMarkerTypeGapToBeSelected();
			initializeInvestigateReportScreen();
			String actualMarkerStatus = investigateReportScreen.getInvestigationMarkers().get(idx-1).getInvestigationStatus();
			Log.info(String.format("Expected marker status=[%s]. Found marker status=[%s]", foundGasLeak, actualMarkerStatus));
			assertTrue(String.format("Incorrect marker status found. Expected=[%s]. Actual=[%s]", foundGasLeak, actualMarkerStatus),
					actualMarkerStatus.equals(foundGasLeak));

			return true;
		});

		// Re-open the investigated marker and verify Gap and bubble color are for an investigated marker.
		investigateReportScreen.clickOnMarkerAtIndex(idx);
		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnFollow();
			investigateMapScreen.assertPipesAndMarkerShownAreCorrect(BaselineImages.Folder.TC2640, String.format(BaselineImages.ImageFile.GapFoundGasLeakScreenshotWithIndexPlaceholder, markerNum));
			return true;
		});
	}

	private void createTestCaseData(TestName testName) throws Exception {
		String methodName = testName.getMethodName();
		Integer userDataRowID = defaultUserDataRowID;
		Integer reportDataRowID1 = defaultReportDataRowID;
		String tcId = methodName.substring(0, 6);
		boolean reuseReports = !isRunningInDataGenMode();

		if (methodName.startsWith("TC2432")) {
			Object[][] tc2432 = LeakScreenDataProvider.dataProviderAndroidApp_TC2432();
			userDataRowID = (Integer)tc2432[0][1];
			reportDataRowID1 = (Integer)tc2432[0][2];
			if (!invReportDataVerifier.hasNotInvestigatedLisaMarker(tcId, loginPageAction.getUsernamePassword(EMPTY, userDataRowID).username)) {
				reuseReports = false;
			}

			String[] lisaNumbers = {"1", "2", "6", "7", "8"};
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(reuseReports /*isReusable*/).createReportAndAssignLisasToUser(tcId,
					defaultUserDataRowID, userDataRowID, reportDataRowID1, lisaNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2639")) {
			Object[][] tc2639 = LeakScreenDataProvider.dataProviderAndroidApp_TC2639();
			userDataRowID = (Integer)tc2639[0][1];
			reportDataRowID1 = (Integer)tc2639[0][2];
			if (!invReportDataVerifier.hasNotInvestigatedLisaMarker(tcId, loginPageAction.getUsernamePassword(EMPTY, userDataRowID).username)) {
				reuseReports = false;
			}

			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(reuseReports /*isReusable*/).createReportAndAssignLisasToUser(tcId,
					defaultUserDataRowID, userDataRowID, reportDataRowID1).getReportTitle();
		} else if (methodName.startsWith("TC2640")) {
			Object[][] tc2640 = LeakScreenDataProvider.dataProviderAndroidApp_TC2640();
			userDataRowID = (Integer)tc2640[0][1];
			reportDataRowID1 = (Integer)tc2640[0][2];
			if (!invReportDataVerifier.hasNotInvestigatedGapMarker(tcId, loginPageAction.getUsernamePassword(EMPTY, userDataRowID).username)) {
				reuseReports = false;
			}

			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(reuseReports /*isReusable*/).createReportAndAssignLisasAndGapsToUser(tcId,
					defaultUserDataRowID, userDataRowID, reportDataRowID1).getReportTitle();
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