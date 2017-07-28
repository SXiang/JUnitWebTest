package androidapp.regression.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import androidapp.dataprovider.ReportListDataProvider;
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
	// TBD: Click on Add Other Sources button is currently NOT present in latest APK published in Artifactory. To be implemented seperately post implementation in product.
	@Test
	@UseDataProvider(value = ReportListDataProvider.REPORT_LIST_DATA_PROVIDER_TC2436, location = ReportListDataProvider.class)
	public void TC2436_EnergyBackpackLoggingMultipleOtherSourceLeaksWithinClassicLISAs(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2436_EnergyBackpackLoggingMultipleOtherSourceLeaksWithinClassicLISAs ...");

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
			// TBD: Click on add other sources and fill out form. See comment above.
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
	// TBD: More verifications to be implemented in this test after 'Mark Investigation' implementation Completed in product.
	@Test
	@UseDataProvider(value = ReportListDataProvider.REPORT_LIST_DATA_PROVIDER_TC2438, location = ReportListDataProvider.class)
	public void TC2438_EnergyBackpackLeakCanBeEdited(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2438_EnergyBackpackLeakCanBeEdited ...");

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

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

		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			return true;
		});

		String[] markerStatuses = {notInvestigated};
		investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatuses));

		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			assertTrue(investigateMapScreen.getAddCGIButton().isDisplayed());
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

		// TBD: More verifications to be implemented in this test after 'Mark Investigation' implementation Completed in product.
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
	// TBD: More verifications to be implemented in this test after 'Mark Investigation' implementation Completed in product.
	@Test
	@UseDataProvider(value = ReportListDataProvider.REPORT_LIST_DATA_PROVIDER_TC2439, location = ReportListDataProvider.class)
	public void TC2439_BackpackAppUserShouldBeAbleToDeleteLeakInformationAssociatedWithAGivenLISA(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2439_BackpackAppUserShouldBeAbleToDeleteLeakInformationAssociatedWithAGivenLISA ...");

		if (isRunningInDataGenMode()) {
			Log.info("Running in data generation mode. Skipping test execution...");
			return;
		}

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

		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			return true;
		});

		String[] markerStatuses = {notInvestigated};
		investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatuses));

		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			assertTrue(investigateMapScreen.getAddCGIButton().isDisplayed());
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

		// TBD: More verifications to be implemented in this test after 'Mark Investigation' implementation Completed in product.
	}

	private void createTestCaseData(TestName testName) throws Exception {
		String methodName = testName.getMethodName();
		Integer userDataRowID = defaultUserDataRowID;
		Integer reportDataRowID1 = defaultReportDataRowID;
		String tcId = "";
		String[] lisaNumbers = {"2", "4", "6"};
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