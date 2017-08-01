package androidapp.regression.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import androidapp.dataprovider.ReportListDataProvider;
import androidapp.entities.source.InvestigationMarkerEntity;
import androidapp.entities.source.LeakListInfoEntity;
import androidapp.entities.source.OtherSourceListInfoEntity;
import androidapp.screens.source.AndroidAddLeakSourceFormDialog;
import androidapp.screens.source.AndroidAddOtherSourceFormDialog;
import androidapp.screens.source.AndroidAddSourceDialog;
import androidapp.screens.source.AndroidAddedSourceListDialog;
import androidapp.screens.source.AndroidConfirmationDialog;
import androidapp.screens.source.AndroidInvestigateMapScreen;
import androidapp.screens.source.AndroidInvestigateReportScreen;
import androidapp.screens.source.AndroidInvestigationScreen;
import androidapp.screens.source.AndroidMarkerTypeListControl;
import androidapp.screens.source.AndroidMarkerTypeListControl.MarkerType;
import common.source.BackPackAnalyzer;
import common.source.CollectionsUtil;
import common.source.FunctionUtil;
import common.source.Log;
import common.source.TestContext;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataprovider.DataGenerator;
import surveyor.scommon.mobile.source.LeakDataGenerator;
import surveyor.scommon.mobile.source.LeakDataGenerator.LeakDataBuilder;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakSourceType;
import surveyor.scommon.mobile.source.LeakDataTypes.SourceType;
import surveyor.scommon.mobile.source.ReportDataGenerator;
import surveyor.scommon.source.SurveyorConstants;

public class AndroidLeakScreenTest extends AndroidLeakScreenTestBase {
	private static final Integer defaultAssignedUserDataRowID = 16;
	private static final Integer defaultUserDataRowID = 6;
	private static final Integer defaultReportDataRowID = 6;

	private static String generatedInvReportTitle;
	private static String notInvestigated;

	protected AndroidInvestigationScreen investigationScreen;
	protected AndroidInvestigateReportScreen investigateReportScreen;
	protected AndroidMarkerTypeListControl markerTypeDialog;

	protected AndroidInvestigateMapScreen investigateMapScreen;
	protected AndroidAddSourceDialog addSourceDialog;
	protected AndroidConfirmationDialog confirmationDialog;

	protected AndroidAddLeakSourceFormDialog addLeakSourceFormDialog;
	protected AndroidAddedSourceListDialog addedSourcesListDialog;

	protected AndroidAddOtherSourceFormDialog addOtherSourceFormDialog;

	private static ThreadLocal<Boolean> appiumTestInitialized = new ThreadLocal<Boolean>();

	@Rule
	public TestName testName = new TestName();

	@Before
	public void beforeTest() throws Exception {
		notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);
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

	/**
	 *	Test Case: TC2434_EnergyBackpackLoggingMultipleLeaksWithinClassicLISAs
	 *	Pre-Conditions:	Compliance Reports with LISAs assigned to user
	 *	Script:
	 *	- Log into Backpack tablet
	 *	- Press Investigation button
	 *	- Select a report
	 *	- Select a LISA
	 *	- Click Investigate button
	 *	- Click on Add Source button
	 *	- Click on Add Leak button
	 *	- Fill out all fields including GPS coordinates and click OK
	 *	- Repeat last two steps
	 *	Expected Result:
	 *	- User will see satellite map view
	 *	- User will see a list of Compliance Reports assigned to him/her
	 *	- User will see a list of LISAs from that report assigned to him/her
	 *	- User is navigated to map showing user's position
	 *	- Investigation Status at top of screen is set to In-Progress
	 *	- A summary popup will appear that lists sources already added (if any) and has two buttons, Add Leak and Add Other Source
	 *	- A popup appears where user can enter details for that leak
	 *	- User is navigated to previous page and details of newly-added leak appear
	 *	- All leaks entered should appear with correct data
	**/
	@Test
	@UseDataProvider(value = ReportListDataProvider.REPORT_LIST_DATA_PROVIDER_TC2434, location = ReportListDataProvider.class)
	public void TC2434_EnergyBackpackLoggingMultipleLeaksWithinClassicLISAs(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2434_EnergyBackpackLoggingMultipleLeaksWithinClassicLISAs ...");

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreen(investigationScreen, SurveyorConstants.USERPASSWORD);
			searchForReportId(investigationScreen, generatedInvReportTitle);
			initializeInvestigationScreen();
			return true;
		});

		clickOnFirstInvestigationReport(investigationScreen);

		executeWithBackPackDataProcessesPaused(obj -> {
			investigateReportScreen.waitForScreenLoad();
			return true;
		});

		String[] markerStatuses = {notInvestigated};
		investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatuses));

		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			investigateMapScreen.clickOnAddSource();
			addSourceDialog.waitForScreenLoad();
			assertTrue(addSourceDialog.getAddOtherSourcesButton().isDisplayed());
			addSourceDialog.clickOnAddLeak();
			addLeakSourceFormDialog.waitForScreenLoad();
			LeakDataBuilder leakDataBuilder = LeakDataGenerator.newBuilder().generateDefaultValues();
			addLeakSourceFormDialog.fillForm(leakDataBuilder.toMap());
			addedSourcesListDialog.waitForScreenLoad();
			assertLeakListInfoIsCorrect(leakDataBuilder, addedSourcesListDialog.getLeaksList());
			return true;
		});
	}

	/**
	 *	Test Case: TC2435_EnergyBackpackLoggingMultipleLeaksWithinGaps
	 *	Pre-Conditions:	Compliance Reports with Gaps assigned to user
	 *	Script:
	 *	- Log into Backpack tablet
	 *	- Press Investigation button
	 *	- Select a report
	 *	- Press arrow near top right of screen
	 *	- Select Gaps in popup
	 *	- Select a Gap
	 *	- Click Investigate button
	 *	- Click on Add Source button
	 *	- Click on Add Leak button
	 *	- Fill out all fields including GPS coordinates and click OK
	 *	- Repeat last two steps
	 *	Expected Result:
	 *	- User will see satellite map view
	 *	- User will see a list of Compliance Reports assigned to him/her
	 *	- User will see a list of LISAs from that report assigned to him/her
	 *	- Popup appears where user can select LISAs or Gaps
	 *	- User will see a list of Gaps from that report assigned to him/her
	 *	- User is navigated to map showing user's position
	 *	- Investigation Status at top of screen is set to In-Progress
	 *	- A summary popup will appear that lists sources already added (if any) and has two buttons, Add Leak and Add Other Source
	 *	- A popup appears where user can enter details for that leak
	 *	- User is navigated to previous page and details of newly-added leak appear
	 *	- All leaks entered should appear with correct data
	**/
	@Test
	@UseDataProvider(value = ReportListDataProvider.REPORT_LIST_DATA_PROVIDER_TC2435, location = ReportListDataProvider.class)
	public void TC2435_EnergyBackpackLoggingMultipleLeaksWithinGaps(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2435_EnergyBackpackLoggingMultipleLeaksWithinGaps ...");

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreen(investigationScreen, SurveyorConstants.USERPASSWORD);
			searchForReportId(investigationScreen, generatedInvReportTitle);
			initializeInvestigationScreen();
			return true;
		});

		clickOnFirstInvestigationReport(investigationScreen);

		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			investigateReportScreen.clickOnInvestigationMarkerType();
			markerTypeDialog.selectMarkerType(MarkerType.Gap);
			initializeInvestigateReportScreen();
			return true;
		});

		String[] markerStatuses = {notInvestigated};
		investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatuses));

		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			investigateMapScreen.clickOnAddSource();
			addSourceDialog.waitForScreenLoad();
			assertTrue(addSourceDialog.getAddOtherSourcesButton().isDisplayed());
			addSourceDialog.clickOnAddLeak();
			addLeakSourceFormDialog.waitForScreenLoad();
			LeakDataBuilder leakDataBuilder = LeakDataGenerator.newBuilder().generateDefaultValues();
			addLeakSourceFormDialog.fillForm(leakDataBuilder.toMap());
			addedSourcesListDialog.waitForScreenLoad();
			assertLeakListInfoIsCorrect(leakDataBuilder, addedSourcesListDialog.getLeaksList());
			return true;
		});
	}

	/**
	 *	Test Case: TC2436_EnergyBackpackLoggingMultipleOtherSourceLeaksWithinClassicLISAs
	 *	Pre-Conditions:	Compliance Reports with LISAs assigned to user
	 *	Script:
	 *	- Log into Backpack tablet
	 *	- Press Investigation button
	 *	- Select a report
	 *	- Select a LISA
	 *	- Click Investigate button
	 *	- Click on Add Source button
	 *	- Click on Add Other Source button
	 *	- Fill out all fields including GPS coordinates and click OK
	 *	- Repeat last two steps
	 *	Expected Result:
	 *	- User will see satellite map view
	 *	- User will see a list of Compliance Reports assigned to him/her
	 *	- User will see a list of LISAs from that report assigned to him/her
	 *	- User is navigated to map showing user's position
	 *	- Investigation Status at top of screen is set to In-Progress
	 *	- A summary popup will appear that lists sources already added (if any) and has two buttons, Add Leak and Add Other Source
	 *	- A popup appears where user can enter details for that leak
	 *	- User is navigated to previous page and details of newly-added leak appear
	 *	- All leaks entered should appear with correct data
	**/
	@Test
	@UseDataProvider(value = ReportListDataProvider.REPORT_LIST_DATA_PROVIDER_TC2436, location = ReportListDataProvider.class)
	public void TC2436_EnergyBackpackLoggingMultipleOtherSourceLeaksWithinClassicLISAs(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2436_EnergyBackpackLoggingMultipleOtherSourceLeaksWithinClassicLISAs ...");

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

		String inProgress = Resources.getResource(ResourceKeys.InvestigationStatusTypes_In_Progress);

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreen(investigationScreen, SurveyorConstants.USERPASSWORD);
			searchForReportId(investigationScreen, generatedInvReportTitle);
			initializeInvestigationScreen();
			return true;
		});

        clickOnFirstInvestigationReport(investigationScreen);

        List<InvestigationMarkerEntity> investigationMarkers = new ArrayList<InvestigationMarkerEntity>();
		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			//assertTrue(verifyMapShowsUserLocation(investigationScreen));
			assertTrue(investigateReportScreen.verifyLisasForReportAreShown(generatedInvReportTitle));
			investigateReportScreen.getInvestigationMarkers().stream()
				.forEach(m -> investigationMarkers.add(m));
			return true;
		});

		String[] markerStatuses = {notInvestigated};
		int idx = investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatuses));
		final String selectedLisa = investigationMarkers.get(idx-1).getLisaNumber();

        executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();

			// TBD: Disabled due to product defect DE3162
			/*
			String actualInvStatusText = investigateMapScreen.getMarkerInvestigationStatusText();
			String expectedInvStatusText = String.format("%s (%s)", selectedLisa, inProgress);
			assertTrue(String.format("Investigation marker text NOT correct. Expected=[%s]; Actual=[%s]", expectedInvStatusText, actualInvStatusText),
					actualInvStatusText.equals(expectedInvStatusText));
			*/

			investigateMapScreen.clickOnAddSource();

			addSourceDialog.waitForScreenLoad();
			assertTrue("Add Leak button is NOT shown.", addSourceDialog.getAddLeakButton().isDisplayed());
			assertTrue("Add Other Sources button in NOT shown.", addSourceDialog.getAddOtherSourcesButton().isDisplayed());

			// get count of current 'other source' entries.
			boolean listShown = addedSourcesListDialog.isListDisplayed();
			int leakCountBeforeAdd = 0;
			if (listShown) {
				List<OtherSourceListInfoEntity> otherSourcesList = addedSourcesListDialog.getOtherSourcesList();
				leakCountBeforeAdd = (otherSourcesList != null) ? otherSourcesList.size() : 0;
			}

			final LeakSourceType otherSourceLeakSourceType = LeakSourceType.Other_Natural_Source;
			final String otherSourceAdditionalNotes = DataGenerator.getRandomText(20, 100);
			IntStream.of(0,1).forEach(i -> {
				FunctionUtil.warnOnError(() -> addNewOtherSource(addSourceDialog, addOtherSourceFormDialog, addedSourcesListDialog, otherSourceLeakSourceType, otherSourceAdditionalNotes));
			});

			// verify other source got added correctly.
			int leakCountAfterAdd = leakCountBeforeAdd + 2;
			List<OtherSourceListInfoEntity> otherSourcesList2 = addedSourcesListDialog.getOtherSourcesList();
			assertTrue(String.format("Other sources list length post adding 2 new items is NOT correct. Size before add=[%d]; Size after add=[%d]",
					leakCountBeforeAdd, otherSourcesList2.size()), otherSourcesList2.size()==leakCountAfterAdd);

			// verify details of last 2 added other sources is correct.
			IntStream.of(1,2).forEach(i -> {
				addedSourcesListDialog.clickOnMatchingListItemOfTypeAtIndex(SourceType.OtherSource, leakCountAfterAdd-i);
				addOtherSourceFormDialog.waitForScreenLoad();
				String actualAdditionalNotes = addOtherSourceFormDialog.getAdditionalNotesText();
				assertTrue(String.format("Additional notes string does NOT match. Expected=[%s]; Actual=[%s]", otherSourceAdditionalNotes, actualAdditionalNotes),
						actualAdditionalNotes.equals(otherSourceAdditionalNotes));
				LeakSourceType actualSourceLeakSourceType = addOtherSourceFormDialog.getSelectedLeakSource();
				assertTrue(String.format("LeakSourceType does NOT match. Expected=[%s]; Actual=[%s]", otherSourceLeakSourceType, actualSourceLeakSourceType),
						actualSourceLeakSourceType.equals(otherSourceLeakSourceType));
				addOtherSourceFormDialog.clickOnCancelForExistingItem();
				addedSourcesListDialog.waitForScreenLoad();
			});

			return true;
		});
	}

	/**
	 *	Test Case: TC2437_EnergyBackpackLoggingMultipleOtherSourceLeaksWithinGaps
	 *	Pre-Conditions:	Compliance Reports with Gaps assigned to user
	 *	Script:
	 *	- Log into Backpack tablet
	 *	- Press Investigation button
	 *	- Select a report
	 *	- Press arrow near top right of screen
	 *	- Select Gaps in popup
	 *	- Select a Gap
	 *	- Click Investigate button
	 *	- Click on Add Source button
	 *	- Click on Add Other Source button
	 *	- Fill out all fields including GPS coordinates and click OK
	 *	- Repeat last two steps
	 *	Verifications:
	 *	- User will see satellite map view
	 *	- User will see a list of Compliance Reports assigned to him/her
	 *	- User will see a list of LISAs from that report assigned to him/her
	 *	- Popup appears where user can select LISAs or Gaps
	 *	- User will see a list of Gaps from that report assigned to him/her
	 *	- User is navigated to map showing user's position
	 *	- Investigation Status at top of screen is set to In-Progress
	 *	- A summary popup will appear that lists sources already added (if any) and has two buttons, Add Leak and Add Other Source
	 *	- A popup appears where user can enter details for that leak
	 *	- User is navigated to previous page and details of newly-added leak appear
	 *	- All leaks entered should appear with correct data
	**/
	@Test
	@UseDataProvider(value = ReportListDataProvider.REPORT_LIST_DATA_PROVIDER_TC2437, location = ReportListDataProvider.class)
	public void TC2437_EnergyBackpackLoggingMultipleOtherSourceLeaksWithinGaps(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2437_EnergyBackpackLoggingMultipleOtherSourceLeaksWithinGaps ...");

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreen(investigationScreen, SurveyorConstants.USERPASSWORD);
			searchForReportId(investigationScreen, generatedInvReportTitle);
			initializeInvestigationScreen();
			return true;
		});

		clickOnFirstInvestigationReport(investigationScreen);

		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			investigateReportScreen.clickOnInvestigationMarkerType();
			markerTypeDialog.selectMarkerType(MarkerType.Gap);
			initializeInvestigateReportScreen();
			return true;
		});

		String[] markerStatuses = {notInvestigated};
		investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatuses));

		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			investigateMapScreen.clickOnAddSource();
			addSourceDialog.waitForScreenLoad();
			assertTrue(addSourceDialog.getAddOtherSourcesButton().isDisplayed());
			addSourceDialog.clickOnAddOtherSources();
			addOtherSourceFormDialog.waitForScreenLoad();
			addOtherSourceFormDialog.clickOnUseCurrentLocation();
			addOtherSourceFormDialog.selectLeakSource(LeakSourceType.Catch_Basin);
			addOtherSourceFormDialog.enterAdditionalNotes(DataGenerator.getRandomText(20, 100));
			addOtherSourceFormDialog.clickOnOKForNewItem();
			addedSourcesListDialog.waitForScreenLoad();
			assertOtherSourceListInfoIsCorrect(addedSourcesListDialog.getOtherSourcesList());
			return true;
		});
	}

	/**
	 *	Test Case: TC2438_EnergyBackpackLeakCanBeEdited
	 *	Pre-Conditions:	Compliance Report with some LISAs that have already been investigated
	 *	Script:
	 *	- Log into the Backpack tablet
	 *	- Click the Investigate button
	 *	- Click on a report
	 *	- Click on an already investigated LISA (marked as either Complete or In Progress)
	 *	- Click on the Investigate button
	 *	- Click on the Add Source button
	 *	- Select an item marked as a Leak and click on it
	 *	- Change some of the details - be sure that all fields are filled in - and click OK
	 *	- Select the LISA again and verify the changes
	 *	Verifications:
	 *	- User should see a list of reports containing LISAs which were assigned to user
	 *	- List of LISAs for that report should appear. If the user has a supervisory role, all LISAs for that report will appear. Otherwise, only LISAs assigned to the user will appear
	 *	- The user is navigated to the map view showing user's position with Follow and Directions buttons on the right and Investigate button at bottom left
	 *	- Add Source and Add CGI buttons will appear on the right
	 *	- A popup will appear with the details of leak(s) for that LISA
	 *	- The details for that leak will be displayed.
	 *	- All drop-down menus should drop DOWN, not go UP. The user will be navigated to the previous screen showing the list of leaks
	 *	- The edited details should persist
	**/
	@Test
	@UseDataProvider(value = ReportListDataProvider.REPORT_LIST_DATA_PROVIDER_TC2438, location = ReportListDataProvider.class)
	public void TC2438_EnergyBackpackLeakCanBeEdited(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2438_EnergyBackpackLeakCanBeEdited ...");

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

		final String inProgress = Resources.getResource(ResourceKeys.InvestigationStatusTypes_In_Progress);
		final String foundGasLeak = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Found_Gas_Leak);

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreen(investigationScreen, SurveyorConstants.USERPASSWORD);
			assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, SurveyorConstants.SQAPICDR));
			searchForReportId(investigationScreen, generatedInvReportTitle);
			initializeInvestigationScreen();
			return true;
		});

		clickOnFirstInvestigationReport(investigationScreen);

		StringBuilder markerVerifier = new StringBuilder();
		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			assertTrue(investigateReportScreen.verifyLisasForReportAreShown(generatedInvReportTitle));
			Log.info("Checking for presence of existing marker with status -> 'Found Gas Leak' or 'In Progress' ...");
			List<InvestigationMarkerEntity> investigationMarkers = investigateReportScreen.getInvestigationMarkers();
			boolean match = investigationMarkers.stream()
				.anyMatch(i -> i.getInvestigationStatus().equals(foundGasLeak) || i.getInvestigationStatus().equals(inProgress));
			Log.info(String.format("Found=[%b]", match));
			markerVerifier.append(String.valueOf(match));
			return true;
		});

		// If no existing marker of desired status, create new.
		if (!markerVerifier.toString().equalsIgnoreCase(TRUE)) {
			Log.info("No existing marker found with status - 'Found Gas Leak' or 'In Progress'. Investigating and marking as Complete.");
			investigateFirstNonInvestigatedMarkerAsLeakAndMarkAsComplete();
		}

		initializeAddLeakSourceFormDialog();

		// Markers screen. Click on LISA marked as Found Gas Leak
		String[] markerStatuses = {foundGasLeak, inProgress};
		investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatuses));

		// Store last edited leak index and leak info for verifications later.
		final List<Integer> lastEditedLeakIndex = new ArrayList<Integer>();
		final List<Map<String, Object>> lastEditedLeakInfo = new ArrayList<>();
		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			assertTrue(investigateMapScreen.getFollowButton().isDisplayed());
			assertTrue(investigateMapScreen.getInvestigateButton().isDisplayed());
			investigateMapScreen.clickOnInvestigate();
			assertTrue(investigateMapScreen.getAddCGIButton().isDisplayed());
			investigateMapScreen.clickOnAddSource();
			addSourceDialog.waitForScreenLoad();
			assertTrue(addSourceDialog.getAddOtherSourcesButton().isDisplayed());
			assertTrue(addSourceDialog.getAddLeakButton().isDisplayed());

			// get count of current 'leak' entries.
			boolean listShown = addedSourcesListDialog.isListDisplayed();
			int leakCountBeforeAdd = 0;
			if (listShown) {
				List<LeakListInfoEntity> leaksList = addedSourcesListDialog.getLeaksList();
				leakCountBeforeAdd = (leaksList != null) ? leaksList.size() : 0;
			}

			// add new leak if none present.
			LeakDataBuilder leakDataBuilder = new LeakDataBuilder().generateDefaultValues();
			int leakCountAfterAdd = leakCountBeforeAdd;
			if (leakCountBeforeAdd == 0) {
				leakDataBuilder = addNewLeak(addSourceDialog, addLeakSourceFormDialog, addedSourcesListDialog);
				// verify leak got added correctly.
				List<LeakListInfoEntity> leaksList2 = addedSourcesListDialog.getLeaksList();
				leakCountAfterAdd = leaksList2.size();
				assertTrue(String.format("Leak list length post adding 1 new item is NOT correct. Size before add=[%d]; Size after add=[%d]",
						leakCountBeforeAdd, leakCountAfterAdd), leaksList2.size()==leakCountAfterAdd);
				assertLeakListInfoIsCorrect(leakDataBuilder, leaksList2, leakCountAfterAdd-1);
			}

			// edit item. close, reopen and verify form was filled correctly.
			lastEditedLeakIndex.add(leakCountAfterAdd-1);
			leakDataBuilder = leakDataBuilder.generateDefaultValues();
			lastEditedLeakInfo.add(leakDataBuilder.toMap());
			addedSourcesListDialog.clickOnMatchingListItemOfTypeAtIndex(SourceType.Leak, lastEditedLeakIndex.get(0));
			addLeakSourceFormDialog.waitForScreenLoad();
			addLeakSourceFormDialog.clearAndFillForm(leakDataBuilder.toMap());
			addedSourcesListDialog.waitForScreenLoad();
			addedSourcesListDialog.clickOnMatchingListItemOfTypeAtIndex(SourceType.Leak, lastEditedLeakIndex.get(0));
			addLeakSourceFormDialog.waitForScreenLoad();
			assertTrue("Leak Info shown in form is NOT correct.", addLeakSourceFormDialog.verifyCorrectDataIsShown(leakDataBuilder.toMap(), true /*isEditMode*/));
			addLeakSourceFormDialog.clickOnCancel();

			// cancel leak form. cancel list dialog.
			addedSourcesListDialog.clickOnCancel();
			investigateMapScreen.waitForScreenLoad();

			return true;
		});

		// click on investigate and search for the report again.
		Log.info("Navigating to previously added leak from reports screen and verifying leak info.");
		initializeInvestigateMapScreen();
		investigateMapScreen.clickOnInvestigate();
		initializeInvestigationScreen();

		executeWithBackPackDataProcessesPaused(obj -> {
			investigationScreen.waitForScreenLoad();
			searchForReportId(investigationScreen, generatedInvReportTitle);
			return true;
		});

		// click first matching report from search. click first matching marker of type.
		clickOnFirstInvestigationReport(investigationScreen);
		initializeAddLeakSourceFormDialog();
		investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatuses));

		// open last edited leak info and verify data is correct.
		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			investigateMapScreen.clickOnAddSource();
			addedSourcesListDialog.waitForScreenAndDataLoad();
			Integer lastAddedLeakIdx = lastEditedLeakIndex.get(0);
			addedSourcesListDialog.clickOnMatchingListItemOfTypeAtIndex(SourceType.Leak, lastAddedLeakIdx);
			addLeakSourceFormDialog.waitForScreenLoad();
			assertTrue("Leak Info shown in form is NOT correct.", addLeakSourceFormDialog.verifyCorrectDataIsShown(lastEditedLeakInfo.get(0), true /*isEditMode*/));
			return true;
		});
	}

	/**
	 *	Test Case: TC2439_BackpackAppUserShouldBeAbleToDeleteLeakInformationAssociatedWithAGivenLISA
	 *	Pre-Conditions:	Compliance Report with some LISAs that were previously-investigated
	 *	Script:
	 *	1. Log into the mobile app as a user to whom LISAs have been assigned
	 *	2. Click on a report
	 *	3. Click on an already investigated LISA (marked as either Complete or In Progress)
	 *	4. Click on the Investigate button
	 *	5. Click on the Add Source button
	 *	6. Select an item marked as a Leak and click on it
	 *	7. Click the Delete button
	 *	8. Click OK on the Confirmation
	 *	Verifications:
	 *	1. User should see a list of reports containing LISAs which were assigned to user
	 *	2. List of LISAs for that report should appear. If the user has a supervisory role, all LISAs for that report will appear. Otherwise, only LISAs assigned to the user will appear
	 *	3. The user is navigated to the map view showing user's position with Follow and Directions buttons on the right and Investigate button at bottom left
	 *	4. Add Source and Add CGI buttons will appear on the right
	 *	5. A popup will appear with the details of leak(s) for that LISA
	 *	6. The details for that leak will be displayed.
	 *	7. A confirmation will appear
	 *	8. The deleted leak will no longer appear in the list
	**/
	@Test
	@UseDataProvider(value = ReportListDataProvider.REPORT_LIST_DATA_PROVIDER_TC2439, location = ReportListDataProvider.class)
	public void TC2439_BackpackAppUserShouldBeAbleToDeleteLeakInformationAssociatedWithAGivenLISA(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2439_BackpackAppUserShouldBeAbleToDeleteLeakInformationAssociatedWithAGivenLISA ...");

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

		final String foundGasLeak = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Found_Gas_Leak);
		final String inProgress = Resources.getResource(ResourceKeys.InvestigationStatusTypes_In_Progress);

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreen(investigationScreen, SurveyorConstants.USERPASSWORD);
			investigationScreen.waitForResultsToLoad();
			assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, SurveyorConstants.SQAPICDR));
			searchForReportId(investigationScreen, generatedInvReportTitle);
			initializeInvestigationScreen();
			return true;
		});

		clickOnFirstInvestigationReport(investigationScreen);

		StringBuilder markerVerifier = new StringBuilder();
		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			assertTrue(investigateReportScreen.verifyLisasForReportAreShown(generatedInvReportTitle));
			Log.info("Checking for presence of existing marker with status -> 'Found Gas Leak' or 'In-Progress' ...");
			List<InvestigationMarkerEntity> investigationMarkers = investigateReportScreen.getInvestigationMarkers();
			boolean match = investigationMarkers.stream()
				.anyMatch(i -> i.getInvestigationStatus().equals(foundGasLeak) || i.getInvestigationStatus().equals(inProgress));
			Log.info(String.format("Found=[%b]", match));
			markerVerifier.append(String.valueOf(match));
			return true;
		});

		// If no existing marker of desired status, create new.
		if (!markerVerifier.toString().equalsIgnoreCase(TRUE)) {
			investigateFirstNonInvestigatedMarkerAsLeakAndMarkAsComplete();
		}

		initializeAddLeakSourceFormDialog();

		// Markers screen. Click on LISA marked as either 'Found Gas Leak' or In-Progress
		String[] markerStatuses = {foundGasLeak, inProgress};
		investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatuses));

		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			assertTrue(investigateMapScreen.getAddCGIButton().isDisplayed());
			investigateMapScreen.clickOnAddSource();
			addedSourcesListDialog.waitForScreenLoad();
			assertTrue(addedSourcesListDialog.getAddOtherSourcesButton().isDisplayed());

			// get count of current 'leak' entries.
			boolean listShown = addedSourcesListDialog.isListDisplayed();
			int leakCountBeforeAdd = 0;
			if (listShown) {
				List<LeakListInfoEntity> leaksList = addedSourcesListDialog.getLeaksList();
				leakCountBeforeAdd = (leaksList != null) ? leaksList.size() : 0;
			}

			// add a new leak.
			addSourceDialog.clickOnAddLeak();
			addLeakSourceFormDialog.waitForScreenLoad();
			LeakDataBuilder leakDataBuilder = LeakDataGenerator.newBuilder().generateDefaultValues();
			Map<String, Object> leakMap = leakDataBuilder.toMap();
			addLeakSourceFormDialog.fillForm(leakMap);
			addedSourcesListDialog.waitForScreenLoad();

			// verify leak got added correctly.
			List<LeakListInfoEntity> leaksList = addedSourcesListDialog.getLeaksList();
			int leakCountAfterAdd = leaksList.size();
			assertTrue(String.format("Leak list length post adding 1 new item is NOT correct. Size before add=[%d]; Size after add=[%d]",
					leakCountBeforeAdd, leakCountAfterAdd), leaksList.size()==leakCountAfterAdd);
			assertLeakListInfoIsCorrect(leakDataBuilder, leaksList, leakCountAfterAdd-1);

			// click on newly added leak and delete
			addedSourcesListDialog.clickOnMatchingListItemOfTypeAtIndex(SourceType.Leak, leakCountAfterAdd-1);
			addLeakSourceFormDialog.waitForScreenLoad();
			addLeakSourceFormDialog.scrollToNextPage();
			addLeakSourceFormDialog.clickOnDelete();
			confirmationDialog.waitForScreenLoad();
			confirmationDialog.clickOnOK();

			// verfiy leak got deleted correctly.
			initializeAndroidAddedLeakListDialog();
			addedSourcesListDialog.waitForScreenLoad();
			Integer initialSize = CollectionsUtil.getListSize(leaksList);
			List<LeakListInfoEntity> sourcesListAfterDelete = addedSourcesListDialog.getLeaksList();
			Integer sizeAfterDelete = CollectionsUtil.getListSize(sourcesListAfterDelete);
			assertTrue(String.format("Expected condition NOT met. Initial list size=[%d], list size after delete=[%d].", initialSize, sizeAfterDelete),
					sizeAfterDelete==initialSize-1);

			return true;
		});
	}

	private void createTestCaseData(TestName testName) throws Exception {
		String methodName = testName.getMethodName();
		Integer userDataRowID = defaultUserDataRowID;
		Integer reportDataRowID1 = defaultReportDataRowID;
		String tcId = "";
		String[] lisaNumbers = {"2", "4", "6", "7", "8"};
		String[] gapNumbers = {"1", "2", "3"};
		boolean reuseReports = !isRunningInDataGenMode();

		if (methodName.startsWith("TC2434_")) {
			Object[][] tc2434 = ReportListDataProvider.dataProviderReportList_TC2434();
			userDataRowID = (Integer)tc2434[0][1];
			reportDataRowID1 = (Integer)tc2434[0][2];
			tcId = "TC2434";
			if (!invReportDataVerifier.hasNotInvestigatedLisaMarker(tcId, SurveyorConstants.SQAPICDR)) {
				reuseReports = false;
			}
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(reuseReports /*isReusable*/).createReportAndAssignLisasAndGapsToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers, gapNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2435_")) {
			Object[][] tc2435 = ReportListDataProvider.dataProviderReportList_TC2435();
			userDataRowID = (Integer)tc2435[0][1];
			reportDataRowID1 = (Integer)tc2435[0][2];
			tcId = "TC2435";
			if (!invReportDataVerifier.hasNotInvestigatedGapMarker(tcId, SurveyorConstants.SQAPICDR)) {
				reuseReports = false;
			}
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(reuseReports /*isReusable*/).createReportAndAssignLisasAndGapsToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers, gapNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2436_")) {
			Object[][] tc2436 = ReportListDataProvider.dataProviderReportList_TC2436();
			userDataRowID = (Integer)tc2436[0][1];
			reportDataRowID1 = (Integer)tc2436[0][2];
			tcId = "TC2436";
			if (!invReportDataVerifier.hasNotInvestigatedLisaMarker(tcId, SurveyorConstants.SQAPICDR)) {
				reuseReports = false;
			}
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(reuseReports /*isReusable*/).createReportAndAssignLisasAndGapsToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers, gapNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2437_")) {
			Object[][] tc2437 = ReportListDataProvider.dataProviderReportList_TC2437();
			userDataRowID = (Integer)tc2437[0][1];
			reportDataRowID1 = (Integer)tc2437[0][2];
			tcId = "TC2437";
			if (!invReportDataVerifier.hasNotInvestigatedGapMarker(tcId, SurveyorConstants.SQAPICDR)) {
				reuseReports = false;
			}
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(reuseReports /*isReusable*/).createReportAndAssignLisasAndGapsToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers, gapNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2438_")) {
			Object[][] tc2438 = ReportListDataProvider.dataProviderReportList_TC2438();
			userDataRowID = (Integer)tc2438[0][1];
			reportDataRowID1 = (Integer)tc2438[0][2];
			tcId = "TC2438";
			if (!invReportDataVerifier.hasNotInvestigatedLisaMarker(tcId, SurveyorConstants.SQAPICDR)) {
				reuseReports = false;
			}
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(reuseReports /*isReusable*/).createReportAndAssignLisasAndGapsToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers, gapNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2439_")) {
			Object[][] tc2439 = ReportListDataProvider.dataProviderReportList_TC2439();
			userDataRowID = (Integer)tc2439[0][1];
			reportDataRowID1 = (Integer)tc2439[0][2];
			tcId = "TC2439";
			if (!invReportDataVerifier.hasNotInvestigatedLisaMarker(tcId, SurveyorConstants.SQAPICDR)) {
				reuseReports = false;
			}
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(reuseReports /*isReusable*/).createReportAndAssignLisasAndGapsToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers, gapNumbers).getReportTitle();
		}
	}

	private void investigateFirstNonInvestigatedMarkerAsLeakAndMarkAsComplete() throws Exception {
		Log.method("investigateFirstNonInvestigatedMarkerAsLeakAndMarkAsComplete");
		String notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);
		String[] markerStatusNotInv = {notInvestigated};
		investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatusNotInv));

		// Add new other source. Mark as Complete.
		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			investigateMapScreen.clickOnAddSource();
			addSourceDialog.waitForScreenLoad();
			addSourceDialog.clickOnAddLeak();
			addLeakSourceFormDialog.waitForScreenLoad();
			LeakDataBuilder leakDataBuilder = LeakDataGenerator.newBuilder().generateDefaultValues();
			addLeakSourceFormDialog.fillForm(leakDataBuilder.toMap());
			addedSourcesListDialog.waitForScreenLoad();
			assertLeakListInfoIsCorrect(leakDataBuilder, addedSourcesListDialog.getLeaksList());
			addedSourcesListDialog.clickOnCancel();
			investigateMapScreen.clickOnMarkAsComplete();
			confirmationDialog.waitForScreenLoad();
			confirmationDialog.clickOnOK();
			investigateReportScreen.waitForScreenLoad();
			return true;
		});
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