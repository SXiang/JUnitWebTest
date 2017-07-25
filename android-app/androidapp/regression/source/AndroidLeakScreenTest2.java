package androidapp.regression.source;

import static org.junit.Assert.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
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
import androidapp.entities.source.OtherSourceListInfoEntity;
import androidapp.screens.source.AndroidAddLeakSourceFormDialog;
import androidapp.screens.source.AndroidAddOtherSourceFormDialog;
import androidapp.screens.source.AndroidAddCgiFormDialog;
import androidapp.screens.source.AndroidAddSourceDialog;
import androidapp.screens.source.AndroidAddedSourceListDialog;
import androidapp.screens.source.AndroidInvestigateMapScreen;
import androidapp.screens.source.AndroidInvestigateReportScreen;
import androidapp.screens.source.AndroidInvestigationScreen;
import androidapp.screens.source.AndroidMarkerTypeListControl;
import androidapp.screens.source.AndroidMarkerTypeListControl.MarkerType;
import androidapp.screens.source.AndroidConfirmationDialog;
import common.source.BackPackAnalyzer;
import common.source.CollectionsUtil;
import common.source.Log;
import common.source.TestContext;
import common.source.Timeout;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataprovider.DataGenerator;
import surveyor.scommon.mobile.source.LeakDataGenerator;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakSourceType;
import surveyor.scommon.mobile.source.LeakDataTypes.SourceType;
import surveyor.scommon.mobile.source.ReportDataGenerator;
import surveyor.scommon.source.SurveyorConstants;

public class AndroidLeakScreenTest2 extends BaseReportTest {
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

	@Before
	public void beforeTest() throws Exception {
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
	 * Test Case ID: TC2440_EnergyBackpack_LoggingOtherSourceLeaksBackpackApp_BypassingAddSourceButton
	 * Test Description: Energy Backpack - Logging Other Source leaks in Backpack app - bypassing Add Source button
	 * Script: -
	 *	- - Log into Energy Backpack tablet
	 *	- - Click on Investigation button
	 *	- - Select a Compliance Report
	 *	- - Select a LISA
	 *	- - Click Investigate button
	 *	- - Click Mark as Complete
	 *	- - Click Yes on confirmation popup
	 *	- - Click on LISA to view it in map
	 *	- - Check Investigation PDF
	 *	- - Check Investigation CSV
	 * Results: -
	 *	- - User will see a list of Compliance Reports assigned to him/her
	 *	- - User will see a list of LISAs from that report assigned to him/her
	 *	- - User is directed to map showing user's position
	 *	- - Investigation Status at top of screen is set to In-Progress, timer is started, GPS breadcrumb collection is started
	 *	- - Confirmation popup will appear asking if Gas was found
	 *	- - Found Other Source will appear on popup
	 *	- - LISA color should be blue
	 *	- - PDF will have all fields populated and show Status Found Other Source for first LISA and No Gas Found for second LISA
	 *	- - CSV will have ReportName, ReportTitle, LISANumber, TotalInvestigationTime, Investigator, FoundDate, Investigator, Status
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2440, location = LeakScreenDataProvider.class)
	public void TC2440_EnergyBackpack_LoggingOtherSourceLeaksBackpackApp_BypassingAddSourceButton(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2440_EnergyBackpack_LoggingOtherSourceLeaksBackpackApp_BypassingAddSourceButton ...");

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
			markerTypeDialog.selectMarkerType(MarkerType.LISA);
			investigateMapScreen.waitForScreenLoad();
			//investigateMapScreen.clickOnInvestigateButton();
			//investigateMapScreen.clickOnMarkAsCompleteButton();
			//gasSourceConfirmDialog.clickOnYesButton();
			return true;
		});

		investigateReportScreen.clickOnFirstInvestigationMarker();
		assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, SurveyorConstants.SQAPICDR));
		//assertTrue(verifyMapShowsUserLocation(investigationScreen));
		//assertTrue(addSourceDialog.verifyInvestigationStatus("In-Progress"));
	}

	/**
	 * Test Case ID: TC2441_EnergyBackpack_LoggingNoLeaksBackpackApp_BypassingAddSourceButton
	 * Test Description: Energy Backpack - Logging No Leaks in Backpack app - bypassing Add Source button
	 * Script: -
	 *	- - Log into Energy Backpack tablet
	 *	- - Click on Investigation button
	 *	- - Select a Compliance Report
	 *	- - Select a LISA
	 *	- - Click Investigate button
	 *	- - Click Mark as Complete
	 *	- - Click No on confirmation popup
	 *	- - Click on LISA to view it on map
	 *	- - Check Investigation PDF
	 *	- - Check Investigation CSV
	 * Results: -
	 *	- - User will see a list of Compliance Reports assigned to him/her
	 *	- - User will see a list of LISAs from that report assigned to him/her
	 *	- - User is directed to map showing user's position
	 *	- - Investigation Status at top of screen is set to In-Progress, timer is started, GPS breadcrumb collection is started
	 *	- - Confirmation popup will appear asking if Gas was found
	 *	- - No Gas Found will appear on popup
	 *	- - LISA color should be blue
	 *	- - PDF will have all fields populated and show No Gas Found for LISA
	 *	- - CSV will have ReportName, ReportTitle, LISANumber, TotalInvestigationTime, Investigator, FoundDate, Investigator, Status
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2441, location = LeakScreenDataProvider.class)
	public void TC2441_EnergyBackpack_LoggingNoLeaksBackpackApp_BypassingAddSourceButton(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2441_EnergyBackpack_LoggingNoLeaksBackpackApp_BypassingAddSourceButton ...");

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
			markerTypeDialog.selectMarkerType(MarkerType.LISA);
			investigateMapScreen.waitForScreenLoad();
			//investigateMapScreen.clickOnInvestigateButton();
			//investigateMapScreen.clickOnMarkAsCompleteButton();
			//gasSourceConfirmDialog.clickOnNoButton();
			return true;
		});

		investigateReportScreen.clickOnFirstInvestigationMarker();
		assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, SurveyorConstants.SQAPICDR));
		//assertTrue(verifyMapShowsUserLocation(investigationScreen));
		//assertTrue(addSourceDialog.verifyInvestigationStatus("In-Progress"));
	}

	/**
	 * Test Case ID: TC2543_EnergyBackpack_OtherSourceCanEdited
	 * Test Description: Energy Backpack - Other Source Can be Edited
	 * Script: -
	 *	- - Log into Energy Backpack tablet
	 *	- - Click on Investigation button
	 *	- - Select a Compliance Report
	 *	- - Click on an already investigated LISA (marked as either Complete or In Progress)
	 *	- - Click Investigate button
	 *	- - Click Add Source button
	 *	- - Select an item marked as a Other Source and click on it
	 *	- - Change some of the details and click OK
	 *	- - Select the LISA again and verify the changes
	 * Results: -
	 *	- - User will see a list of Compliance Reports assigned to him/her
	 *	- - User will see a list of LISAs from that report assigned to him/her- User is directed to map showing user's position
	 *	- - Details of previously entered Other Source data appears
	 *	- - Edited data should appear
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2543, location = LeakScreenDataProvider.class)
	public void TC2543_EnergyBackpack_OtherSourceCanEdited(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2543_EnergyBackpack_OtherSourceCanEdited ...");

		final String complete = Resources.getResource(ResourceKeys.Constant_Complete);
		final String inProgress = Resources.getResource(ResourceKeys.InvestigationStatusTypes_In_Progress);

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

		StringBuilder markerVerifier = new StringBuilder();
		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			assertTrue(investigateReportScreen.verifyLisasForReportAreShown(generatedInvReportTitle));
			Log.info("Checking for presence of existing marker with status -> 'Complete' or 'In-Progress' ...");
			List<InvestigationMarkerEntity> investigationMarkers = investigateReportScreen.getInvestigationMarkers();
			boolean match = investigationMarkers.stream()
				.anyMatch(i -> i.getInvestigationStatus().equals(complete) || i.getInvestigationStatus().equals(inProgress));
			Log.info(String.format("Found=[%b]", match));
			markerVerifier.append(String.valueOf(match));
			return true;
		});

		// If no existing marker of desired status, create new.
		if (!markerVerifier.toString().equalsIgnoreCase(TRUE)) {
			investigateFirstNonInvestigatedMarkerAsOtherSourceAndMarkAsComplete();
		}

		// Markers screen. Click on LISA marked as either Complete or In Progress
		String[] markerStatuses = {complete, inProgress};
		investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatuses));

		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			investigateMapScreen.clickOnAddSource();

			// verify if there is an 'other source' entry. if not create a new one.
			boolean listShown = addedSourcesListDialog.isListDisplayed();
			int totalOtherSources = 0;
			boolean oSrcMatch = false;
			if (listShown) {
				List<OtherSourceListInfoEntity> otherSourcesList = addedSourcesListDialog.getOtherSourcesList();
				totalOtherSources = (otherSourcesList != null) ? otherSourcesList.size() : 0;
				oSrcMatch = otherSourcesList.stream()
					.anyMatch(o -> o.getSource().trim().equals("Other Source"));
			}

			if (!oSrcMatch) {
				addNewOtherSource();
				totalOtherSources++;
			}

			// edit existing 'Other Source' and verify.
			addedSourcesListDialog.clickOnFirstMatchingListItemOfType(SourceType.OtherSource);
			addOtherSourceFormDialog.waitForScreenLoad();
			addOtherSourceFormDialog.selectLeakSource(LeakSourceType.Other_Enclosure);
			addOtherSourceFormDialog.enterAdditionalNotes(DataGenerator.getRandomText(20, 100));
			addOtherSourceFormDialog.clickOnOK();
			addedSourcesListDialog.waitForScreenLoad();
			List<OtherSourceListInfoEntity> otherSourcesList2 = addedSourcesListDialog.getOtherSourcesList();
			assertTrue("Sources list length post edit should be same as list length prior to edit", totalOtherSources == otherSourcesList2.size());
			assertTrue("Sources list length should be greater than 0", otherSourcesList2!=null && otherSourcesList2.size()>0);
			otherSourcesList2.stream()
				.forEach(el -> {
					assertTrue(el.getSource().trim().equals("Other Source"));
					assertTrue(el.getTime().length()>10);
				});

			return true;
		});
	}

	/**
	 * Test Case ID: TC2545_EnergyBackpack_OtherSourceCanDeleted
	 * Test Description: Energy Backpack - Other Source Can be Deleted
	 * Script: -
	 *	- - Log into Energy Backpack tablet
	 *	- - Click on Investigation button
	 *	- - Select a Compliance Report
	 *	- - Click on an already investigated LISA (marked as either Complete or In Progress)
	 *	- - Click Investigate button
	 *	- - Click Add Source button
	 *	- - Select an item marked as a Other Source and click on it
	 *	- - Click on Delete
	 *	- - Click OK
	 * Results: -
	 *	- - User will see a list of Compliance Reports assigned to him/her
	 *	- - User will see a list of LISAs from that report assigned to him/her- User is directed to map showing user's position
	 *	- - Details of previously entered Other Source data appears
	 *	- - Confirmation popup will appear
	 *	- - Details of that Other Source will no longer be present on the screen
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2545, location = LeakScreenDataProvider.class)
	public void TC2545_EnergyBackpack_OtherSourceCanDeleted(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2545_EnergyBackpack_OtherSourceCanDeleted ...");

		final String complete = Resources.getResource(ResourceKeys.Constant_Complete);
		final String inProgress = Resources.getResource(ResourceKeys.InvestigationStatusTypes_In_Progress);

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

		StringBuilder markerVerifier = new StringBuilder();
		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			assertTrue(investigateReportScreen.verifyLisasForReportAreShown(generatedInvReportTitle));
			Log.info("Checking for presence of existing marker with status -> 'Complete' or 'In-Progress' ...");
			List<InvestigationMarkerEntity> investigationMarkers = investigateReportScreen.getInvestigationMarkers();
			boolean match = investigationMarkers.stream()
				.anyMatch(i -> i.getInvestigationStatus().equals(complete) || i.getInvestigationStatus().equals(inProgress));
			Log.info(String.format("Found=[%b]", match));
			markerVerifier.append(String.valueOf(match));
			return true;
		});

		// If no existing marker of desired status, create new.
		if (!markerVerifier.toString().equalsIgnoreCase(TRUE)) {
			investigateFirstNonInvestigatedMarkerAsOtherSourceAndMarkAsComplete();
		}

		// Markers screen. Click on LISA marked as either Complete or In Progress
		String[] markerStatuses = {complete, inProgress};
		investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatuses));

		// Add new other source. Mark as Complete.
		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			investigateMapScreen.clickOnAddSource();
			addSourceDialog.waitForScreenLoad();
			addSourceDialog.clickOnAddOtherSources();
			addOtherSourceFormDialog.waitForScreenLoad();
			addOtherSourceFormDialog.clickOnUseCurrentLocation();
			addOtherSourceFormDialog.selectLeakSource(LeakSourceType.Other_Natural_Source);
			addOtherSourceFormDialog.enterAdditionalNotes(DataGenerator.getRandomText(20, 100));
			addOtherSourceFormDialog.clickOnOK();
			addedSourcesListDialog.waitForScreenLoad();
			List<OtherSourceListInfoEntity> otherSourcesList = addedSourcesListDialog.getOtherSourcesList();
			assertTrue(otherSourcesList!=null && otherSourcesList.size()>0);
			addedSourcesListDialog.clickOnCancel();
			investigateMapScreen.clickOnMarkAsComplete();
			investigateReportScreen.waitForScreenLoad();
			return true;
		});

		// Markers screen. Find LISA (marked as either Complete or In Progress)
		String[] markerStatuses2 = {"Complete", "In Progress"};
		investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatuses2));

		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			investigateMapScreen.clickOnAddSource();
			addedSourcesListDialog.waitForScreenLoad();
			List<OtherSourceListInfoEntity> sourcesList = addedSourcesListDialog.getOtherSourcesList();
			addedSourcesListDialog.clickOnFirstMatchingListItemOfType(SourceType.OtherSource);
			addOtherSourceFormDialog.waitForScreenLoad();
			addOtherSourceFormDialog.clickOnDelete();
			confirmationDialog.waitForScreenLoad();
			confirmationDialog.clickOnOK();
			addedSourcesListDialog.waitForScreenLoad();
			Integer initialSize = CollectionsUtil.getListSize(sourcesList);
			List<OtherSourceListInfoEntity> sourcesListAfterDelete = addedSourcesListDialog.getOtherSourcesList();
			Integer sizeAfterDelete = CollectionsUtil.getListSize(sourcesListAfterDelete);
			assertTrue(String.format("Expected condition NOT met. Initial list size=[%d], list size after delete=[%d].", initialSize, sizeAfterDelete),
					sizeAfterDelete==initialSize-1);
			return true;
		});
	}

	/**
	 * Test Case ID: TC2546_EnergyBackpack_LoggingCGI_GasFound
	 * Test Description: Energy Backpack - Logging CGI - Gas Found
	 * Script: -
	 *	- - Log into Backpack tablet
	 *	- - Press Investigation button
	 *	- - Select a report
	 *	- - Select a LISA
	 *	- - Click Investigate button
	 *	- - Click on Add CGI button
	 *	- - Enter an address and click OK
	 *	- - Click on Mark as Complete
	 *	- - Click Yes
	 * Results: -
	 *	- - User will see satellite map view
	 *	- - User will see a list of Compliance Reports assigned to him/her
	 *	- - User will see a list of LISAs from that report assigned to him/her
	 *	- - User is navigated to map showing user's position
	 *	- - Investigation Status at top of screen is set to In-Progress
	 *	- - A popup will appear where user can add addresses
	 *	- - Popup disappears
	 *	- - A popup appears asking if gas source was found
	 *	- - User is navigated back to list of LISAs. The LISA investigated is now marked as Found Other Source
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2546, location = LeakScreenDataProvider.class)
	// NOTES: Failing in APK 110 due to product defect DE3154.
	public void TC2546_EnergyBackpack_LoggingCGI_GasFound(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2546_EnergyBackpack_LoggingCGI_GasFound ...");

		final String notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);
		final String foundOtherSource = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Found_Other_Source);

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

		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			assertTrue(investigateReportScreen.verifyLisasForReportAreShown(generatedInvReportTitle));
			return true;
		});

		// Markers screen. Click on LISA marked as Not Investigated
		String[] markerStatuses = {notInvestigated};
		int idx = investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatuses));

		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			investigateMapScreen.clickOnAddCGI();
			addCgiFormDialog.waitForScreenLoad();
			addCgiFormDialog.enterCgiText(DataGenerator.getAddressString());
			addCgiFormDialog.clickOnOK();
			investigateMapScreen.clickOnMarkAsComplete();
			confirmationDialog.waitForScreenLoad();
			confirmationDialog.clickOnOK();
			investigateReportScreen.waitForScreenLoad();
			String actualMarkerStatus = investigateReportScreen.getInvestigationMarkers().get(idx-1).getInvestigationStatus();
			Log.info(String.format("Expected marker status=[%s]. Found marker status=[%s]", foundOtherSource, actualMarkerStatus));
			assertTrue(actualMarkerStatus.equals(foundOtherSource));

			return true;
		});
	}

	/**
	 * Test Case ID: TC2547_EnergyBackpack_LoggingCGI_GasNotFound
	 * Test Description: Energy Backpack - Logging CGI - Gas Not Found
	 * Script: -
	 *	- - Log into Backpack tablet
	 *	- - Press Investigation button
	 *	- - Select a report
	 *	- - Select a LISA
	 *	- - Click Investigate button
	 *	- - Click on Add CGI button
	 *	- - Enter an address and click OK
	 *	- - Click on Mark as Complete
	 *	- - Click No
	 * Results: -
	 *	- - User will see satellite map view
	 *	- - User will see a list of Compliance Reports assigned to him/her
	 *	- - User will see a list of LISAs from that report assigned to him/her
	 *	- - User is navigated to map showing user's position
	 *	- - Investigation Status at top of screen is set to In-Progress
	 *	- - A popup will appear where user can add addresses
	 *	- - Popup disappears
	 *	- - A popup appears asking if gas source was found
	 *	- - User is navigated back to list of LISAs. The LISA investigated is now marked as No Gas Found
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2547, location = LeakScreenDataProvider.class)
	// NOTES: Failing in APK 110 due to product defect DE3150.
	public void TC2547_EnergyBackpack_LoggingCGI_GasNotFound(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2547_EnergyBackpack_LoggingCGI_GasNotFound ...");

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

		executeWithBackPackDataProcessesPaused(true /*applyInitialPause*/, obj -> {
			investigateReportScreen.waitForScreenLoad();
			return true;
		});

		String noGasFound = Resources.getResource(ResourceKeys.InvestigationStatusTypes_No_Gas_Found);
		String notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);
		List<String> markerStatuses = Arrays.asList(notInvestigated);
		int idx = investigateReportScreen.clickFirstMarkerMatchingStatus(markerStatuses);

		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			investigateMapScreen.clickOnAddCGI();
			addCgiFormDialog.waitForScreenLoad();
			addCgiFormDialog.enterCgiText(DataGenerator.getAddressString());
			addCgiFormDialog.clickOnOK();
			investigateMapScreen.clickOnMarkAsComplete();
			confirmationDialog.waitForScreenLoad();
			confirmationDialog.clickOnCancel();
			investigateReportScreen.waitForScreenLoad();
			String actualMarkerStatus = investigateReportScreen.getInvestigationMarkers().get(idx-1).getInvestigationStatus();
			Log.info(String.format("Expected marker status=[%s]. Found marker status=[%s]", noGasFound, actualMarkerStatus));
			assertTrue(actualMarkerStatus.equals(noGasFound));
			return true;
		});
	}

	/**
	 * Test Case ID: TC2555_EnergyBackpack_ListOfPreviouslyLoggedSources
	 * Test Description: Energy Backpack - List of previously logged sources
	 * Script: -
	 *	- - Log into the tablet and launch the Backpack app
	 *	- - Click on the Investigate button
	 *	- - Click on a report
	 *	- - Click on a LISA
	 *	- - Click Investigate button
	 *	- - Click Add Source button
	 *	- - Click Add Leak button
	 *	- - Enter some leak details and click OK
	 *	- - Click Add Other Source button
	 *	- - Enter some details and click OK
	 *	- - Click Mark As Complete
	 *	- - Click on same report and same LISA again
	 *	- - Click Mark as Complete
	 *	- - Click on same LISA
	 *	- - Click on Investigate button
	 *	- - Click on Add Source button
	 * Results: -
	 *	- - Map page is displayed
	 *	- - List of Compliance Reports is listed
	 *	- - List of LISAs is listed
	 *	- - Map appears
	 *	- - Add Source and Add CGI button appear
	 *	- - Leak details screen appears
	 *	- - Leak summary appears with details of just-entered leak
	 *	- - Other Source details screen appears
	 *	- - Leak summary appears with details of both Leak and Other Source
	 *	- - List of LISAs appears
	 *	- - Map appears
	 *	- - Add Source and Add CGI buttons appear
	 *	- - Leak summary for same two items appears again
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2555, location = LeakScreenDataProvider.class)
	public void TC2555_EnergyBackpack_ListOfPreviouslyLoggedSources(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2555_EnergyBackpack_ListOfPreviouslyLoggedSources ...");

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
		investigateReportScreen.clickOnFirstInvestigationMarker();
		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			//investigateMapScreen.clickOnInvestigateButton();
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnAddSource();
			addSourceDialog.waitForScreenLoad();
			addSourceDialog.clickOnAddLeak();
			addLeakSourceFormDialog.fillForm(LeakDataGenerator.newBuilder().generateDefaultValues().toMap());
			addSourceDialog.waitForScreenLoad();
			//addSourceDialog.clickOnAddOtherSourcesButton();
			addLeakSourceFormDialog.fillForm(LeakDataGenerator.newBuilder().generateDefaultValues().toMap());
			//investigateMapScreen.clickOnMarkAsCompleteButton();
			return true;
		});

		investigateReportScreen.clickOnFirstInvestigationMarker();
		executeWithBackPackDataProcessesPaused(obj -> {
			//investigateMapScreen.clickOnMarkAsCompleteButton();
			return true;
		});

		investigateReportScreen.clickOnFirstInvestigationMarker();
		executeWithBackPackDataProcessesPaused(obj -> {
			//investigateMapScreen.clickOnInvestigateButton();
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnAddSource();
			return true;
		});

		assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, SurveyorConstants.SQAPICDR));
		//assertTrue(investigateReportScreen.verifyLisasForReportAreShown(reportId));
		investigateMapScreen.getAddSourceButton().isDisplayed();
		investigateMapScreen.getAddCGIButton().isDisplayed();
		//assertTrue(investigateReportScreen.verifyLisasForReportAreShown(reportId));
		investigateMapScreen.getAddSourceButton().isDisplayed();
		investigateMapScreen.getAddCGIButton().isDisplayed();
	}

	private void createTestCaseData(TestName testName) throws Exception {
		String methodName = testName.getMethodName();
		Integer userDataRowID = defaultUserDataRowID;
		Integer reportDataRowID1 = defaultReportDataRowID;
		String tcId = "";
		String[] lisaNumbers = {"1", "2", "3", "4", "5", "6"};
		boolean reuseReports = !isRunningInDataGenMode();
		if (methodName.startsWith("TC2440")) {
			Object[][] tc2440 = LeakScreenDataProvider.dataProviderAndroidApp_TC2440();
			userDataRowID = (Integer)tc2440[0][1];
			reportDataRowID1 = (Integer)tc2440[0][2];
			tcId = "TC2440";
			if (!invReportDataVerifier.hasNotInvestigatedMarker(tcId)) {
				reuseReports = false;
			}
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(reuseReports /*isReusable*/).createReportAndAssignLisasToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2441")) {
			Object[][] tc2441 = LeakScreenDataProvider.dataProviderAndroidApp_TC2441();
			userDataRowID = (Integer)tc2441[0][1];
			reportDataRowID1 = (Integer)tc2441[0][2];
			tcId = "TC2441";
			if (!invReportDataVerifier.hasNotInvestigatedMarker(tcId)) {
				reuseReports = false;
			}
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(reuseReports /*isReusable*/).createReportAndAssignLisasToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2543")) {
			Object[][] tc2543 = LeakScreenDataProvider.dataProviderAndroidApp_TC2543();
			userDataRowID = (Integer)tc2543[0][1];
			reportDataRowID1 = (Integer)tc2543[0][2];
			tcId = "TC2543";
			if (!invReportDataVerifier.hasNotInvestigatedMarker(tcId)) {
				reuseReports = false;
			}
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(reuseReports /*isReusable*/).createReportAndAssignLisasToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2545")) {
			Object[][] tc2545 = LeakScreenDataProvider.dataProviderAndroidApp_TC2545();
			userDataRowID = (Integer)tc2545[0][1];
			reportDataRowID1 = (Integer)tc2545[0][2];
			tcId = "TC2545";
			if (!invReportDataVerifier.hasNotInvestigatedMarker(tcId)) {
				reuseReports = false;
			}
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(reuseReports /*isReusable*/).createReportAndAssignLisasToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2546")) {
			Object[][] tc2546 = LeakScreenDataProvider.dataProviderAndroidApp_TC2546();
			userDataRowID = (Integer)tc2546[0][1];
			reportDataRowID1 = (Integer)tc2546[0][2];
			tcId = "TC2546";
			if (!invReportDataVerifier.hasNotInvestigatedMarker(tcId)) {
				reuseReports = false;
			}
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(reuseReports /*isReusable*/).createReportAndAssignLisasToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2547")) {
			Object[][] tc2547 = LeakScreenDataProvider.dataProviderAndroidApp_TC2547();
			userDataRowID = (Integer)tc2547[0][1];
			reportDataRowID1 = (Integer)tc2547[0][2];
			tcId = "TC2547";
			if (!invReportDataVerifier.hasNotInvestigatedMarker(tcId)) {
				reuseReports = false;
			}
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(reuseReports /*isReusable*/).createReportAndAssignLisasToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2555")) {
			Object[][] tc2555 = LeakScreenDataProvider.dataProviderAndroidApp_TC2555();
			userDataRowID = (Integer)tc2555[0][1];
			reportDataRowID1 = (Integer)tc2555[0][2];
			tcId = "TC2555";
			if (!invReportDataVerifier.hasNotInvestigatedMarker(tcId)) {
				reuseReports = false;
			}
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(reuseReports /*isReusable*/).createReportAndAssignLisasToUser(tcId,
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
		initializeDeleteConfirmationDialog();
		initializeAddCgiFormDialog();
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

	private void initializeDeleteConfirmationDialog() {
		confirmationDialog = new AndroidConfirmationDialog(appiumDriver);
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Timeout.ANDROID_APP_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS), confirmationDialog);
	}

	private void installApkStartAppiumDriver() throws MalformedURLException, IOException {
		initializeAppiumDriver();
		installLaunchApp(AndroidActivities.MAIN_ACTIVITY);
	}

	private void addNewOtherSource() throws Exception {
		Log.method("addNewOtherSource");
		addSourceDialog.waitForScreenLoad();
		addSourceDialog.clickOnAddOtherSources();
		addOtherSourceFormDialog.waitForScreenLoad();
		addOtherSourceFormDialog.clickOnUseCurrentLocation();
		addOtherSourceFormDialog.selectLeakSource(LeakSourceType.Other_Natural_Source);
		addOtherSourceFormDialog.enterAdditionalNotes(DataGenerator.getRandomText(20, 100));
		addOtherSourceFormDialog.clickOnOK();
		addedSourcesListDialog.waitForScreenLoad();
		List<OtherSourceListInfoEntity> otherSourcesList = addedSourcesListDialog.getOtherSourcesList();
		assertTrue("Sources list length should be greater than 0", otherSourcesList!=null && otherSourcesList.size()>0);
		otherSourcesList.stream()
			.forEach(el -> {
				assertTrue("Source type should match 'Other Source'", el.getSource().trim().equals("Other Source"));
				assertTrue("Source time should be valid", el.getTime().length()>10);
			});
	}

	private void investigateFirstNonInvestigatedMarkerAsOtherSourceAndMarkAsComplete() throws Exception {
		Log.method("investigateFirstNonInvestigatedMarkerAsOtherSourceAndMarkAsComplete");
		String notInvestigated = Resources.getResource(ResourceKeys.InvestigationStatusTypes_Not_Investigated);
		String[] markerStatusNotInv = {notInvestigated};
		investigateReportScreen.clickFirstMarkerMatchingStatus(Arrays.asList(markerStatusNotInv));

		// Add new other source. Mark as Complete.
		executeWithBackPackDataProcessesPaused(obj -> {
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnInvestigate();
			investigateMapScreen.clickOnAddSource();
			addSourceDialog.waitForScreenLoad();
			addSourceDialog.clickOnAddOtherSources();
			addOtherSourceFormDialog.waitForScreenLoad();
			addOtherSourceFormDialog.clickOnUseCurrentLocation();
			addOtherSourceFormDialog.selectLeakSource(LeakSourceType.Other_Natural_Source);
			addOtherSourceFormDialog.enterAdditionalNotes(DataGenerator.getRandomText(20, 100));
			addOtherSourceFormDialog.clickOnOK();
			addedSourcesListDialog.waitForScreenLoad();
			List<OtherSourceListInfoEntity> otherSourcesList = addedSourcesListDialog.getOtherSourcesList();
			assertTrue("Sources list length should be greater than 0", otherSourcesList!=null && otherSourcesList.size()>0);
			addedSourcesListDialog.clickOnCancel();
			investigateMapScreen.clickOnMarkAsComplete();
			confirmationDialog.waitForScreenLoad();
			confirmationDialog.clickOnOK();
			investigateReportScreen.waitForScreenLoad();
			return true;
		});
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