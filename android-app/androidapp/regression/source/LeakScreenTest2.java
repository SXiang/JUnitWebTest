package androidapp.regression.source;

import static org.junit.Assert.*;
import java.io.IOException;
import java.net.MalformedURLException;
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
import androidapp.entities.source.LeakListInfoEntity;
import androidapp.entities.source.OtherSourceListInfoEntity;
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
import surveyor.scommon.mobile.source.LeakDataGenerator;
import surveyor.scommon.mobile.source.LeakDataGenerator.LeakDataBuilder;
import surveyor.scommon.mobile.source.LeakDataTypes.LeakSourceType;
import surveyor.scommon.mobile.source.ReportDataGenerator;
import surveyor.scommon.source.SurveyorConstants;

public class LeakScreenTest2 extends BaseReportTest {
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

	private static ThreadLocal<Boolean> appiumTestInitialized = new ThreadLocal<Boolean>();

	@Rule
	public TestName testName = new TestName();

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
	 *	-
	 * Results: -
	 *	- - User will see a list of Compliance Reports assigned to him/her
	 *	- - User will see a list of LISAs from that report assigned to him/her
	 *	- - User is directed to map showing user's position
	 *	- - Investigation Status at top of screen is set to In-Progress, timer is started, GPS breadcrumb collection is started
	 *	- - Confirmation popup will appear asking if Gas was found
	 *	- - Found Other Source will appear on popup
	 *	-
	 *	- - LISA color should be blue
	 *	- - PDF will have all fields populated and show Status Found Other Source for first LISA and No Gas Found for second LISA
	 *	- - CSV will have ReportName, ReportTitle, LISANumber, TotalInvestigationTime, Investigator, FoundDate, Investigator, Status
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2440, location = LeakScreenDataProvider.class)
	public void TC2440_EnergyBackpack_LoggingOtherSourceLeaksBackpackApp_BypassingAddSourceButton(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2440_EnergyBackpack_LoggingOtherSourceLeaksBackpackApp_BypassingAddSourceButton ...");

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
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
	 *	-
	 *	- - Select a LISA
	 *	- - Click Investigate button
	 *	- - Click Mark as Complete
	 *	- - Click No on confirmation popup
	 *	-
	 *	- - Click on LISA to view it on map
	 *	-
	 *	- - Check Investigation PDF
	 *	- - Check Investigation CSV
	 *	-
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
	 *	-
	 *	-
	 *	-
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2441, location = LeakScreenDataProvider.class)
	public void TC2441_EnergyBackpack_LoggingNoLeaksBackpackApp_BypassingAddSourceButton(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2441_EnergyBackpack_LoggingNoLeaksBackpackApp_BypassingAddSourceButton ...");

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
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
	 *	-
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

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreen(investigationScreen, SurveyorConstants.USERPASSWORD);
			searchForReportId(investigationScreen, generatedInvReportTitle);
			initializeInvestigationScreen();
			return true;
		});
		clickOnFirstInvestigationReport(investigationScreen);
		executeWithBackPackDataProcessesPaused(obj -> {
			//addedSourcesListDialog.clickInvestigatedLeak("In-Progress");
			investigateMapScreen.waitForScreenLoad();
			//investigateMapScreen.clickOnInvestigateButton();
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnAddSource();
			//addedSourcesListDialog.clickInvestigatedOtherSource("In-Progress");
			addOtherSourceFormDialog.waitForScreenLoad();
			addOtherSourceFormDialog.clickOnUseCurrentLocation();
			addOtherSourceFormDialog.selectLeakSource(LeakSourceType.Catch_Basin);
			addOtherSourceFormDialog.enterAdditionalNotes("test additional notes");
			addOtherSourceFormDialog.clickOnOK();
			return true;
		});
		investigateReportScreen.clickOnFirstInvestigationMarker();
		assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, SurveyorConstants.SQAPICDR));
		assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, SurveyorConstants.SQAPICDR));
		addedSourcesListDialog.waitForScreenLoad();
			List<OtherSourceListInfoEntity> otherSourcesList = addedSourcesListDialog.getOtherSourcesList();
			assertTrue(otherSourcesList!=null && otherSourcesList.size()>0);
			otherSourcesList.stream()
				.forEach(el -> {
					assertTrue(el.getSource().trim().equals("Other Source"));
					assertTrue(el.getTime().length()>10);
				});
	}

	/**
	 * Test Case ID: TC2545_EnergyBackpack_OtherSourceCanDeleted
	 * Test Description: Energy Backpack - Other Source Can be Deleted
	 * Script: -
	 *	-
	 *	-
	 *	- - Log into Energy Backpack tablet
	 *	- - Click on Investigation button
	 *	- - Select a Compliance Report
	 *	- - Click on an already investigated LISA (marked as either Complete or In Progress)
	 *	- - Click Investigate button
	 *	- - Click Add Source button
	 *	- - Select an item marked as a Other Source and click on it
	 *	- - Click on Delete
	 *	- - Click OK
	 *	-
	 * Results: -
	 *	-
	 *	- - User will see a list of Compliance Reports assigned to him/her
	 *	- - User will see a list of LISAs from that report assigned to him/her- User is directed to map showing user's position
	 *	- - Details of previously entered Other Source data appears
	 *	- - Confirmation popup will appear
	 *	- - Details of that Other Source will no longer be present on the screen
	 *	-
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2545, location = LeakScreenDataProvider.class)
	public void TC2545_EnergyBackpack_OtherSourceCanDeleted(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2545_EnergyBackpack_OtherSourceCanDeleted ...");

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
		executeWithBackPackDataProcessesPaused(obj -> {
			navigateToInvestigationReportScreen(investigationScreen, SurveyorConstants.USERPASSWORD);
			searchForReportId(investigationScreen, generatedInvReportTitle);
			initializeInvestigationScreen();
			return true;
		});
		clickOnFirstInvestigationReport(investigationScreen);
		executeWithBackPackDataProcessesPaused(obj -> {
			//addedSourcesListDialog.clickInvestigatedLeak("In-Progress");
			investigateMapScreen.waitForScreenLoad();
			//investigateMapScreen.clickOnInvestigateButton();
			investigateMapScreen.waitForScreenLoad();
			investigateMapScreen.clickOnAddSource();
			//addedSourcesListDialog.clickInvestigatedOtherSource("In-Progress");
			return true;
		});
		assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, SurveyorConstants.SQAPICDR));
		assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, SurveyorConstants.SQAPICDR));
		addedSourcesListDialog.waitForScreenLoad();
			List<OtherSourceListInfoEntity> otherSourcesList = addedSourcesListDialog.getOtherSourcesList();
			assertTrue(otherSourcesList!=null && otherSourcesList.size()>0);
			otherSourcesList.stream()
				.forEach(el -> {
					assertTrue(el.getSource().trim().equals("Other Source"));
					assertTrue(el.getTime().length()>10);
				});
	}

	/**
	 * Test Case ID: TC2546_EnergyBackpack_LoggingCGI_GasFound
	 * Test Description: Energy Backpack - Logging CGI - Gas Found
	 * Script: -
	 *	- - Log into Backpack tablet
	 *	- - Press Investigation button
	 *	-
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
	public void TC2546_EnergyBackpack_LoggingCGI_GasFound(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2546_EnergyBackpack_LoggingCGI_GasFound ...");

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
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
			investigateMapScreen.waitForScreenLoad();
			//investigateMapScreen.clickOnInvestigateButton();
			investigateMapScreen.clickOnAddCGI();
			//investigateMapScreen.clickOnMarkAsCompleteButton();
			//gasSourceConfirmDialog.clickOnYesButton();
			return true;
		});
		//assertTrue(investigationScreen.verifySatelliteMapIsShown(mapImage));
		assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, SurveyorConstants.SQAPICDR));
		assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, SurveyorConstants.SQAPICDR));
		//assertTrue(verifyMapShowsUserLocation(investigationScreen));
		//assertTrue(addSourceDialog.verifyInvestigationStatus("In-Progress"));
		//assertTrue(verifyMapShowsUserLocation(investigationScreen));
	}

	/**
	 * Test Case ID: TC2547_EnergyBackpack_LoggingCGI_GasNotFound
	 * Test Description: Energy Backpack - Logging CGI - Gas Not Found
	 * Script: -
	 *	- - Log into Backpack tablet
	 *	- - Press Investigation button
	 *	-
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
	public void TC2547_EnergyBackpack_LoggingCGI_GasNotFound(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2547_EnergyBackpack_LoggingCGI_GasNotFound ...");

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
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
			investigateMapScreen.waitForScreenLoad();
			//investigateMapScreen.clickOnInvestigateButton();
			investigateMapScreen.clickOnAddCGI();
			//investigateMapScreen.clickOnMarkAsCompleteButton();
			//gasSourceConfirmDialog.clickOnNoButton();
			return true;
		});
		//assertTrue(investigationScreen.verifySatelliteMapIsShown(mapImage));
		assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, SurveyorConstants.SQAPICDR));
		assertTrue(verifyReportsAssignedToUserAreShown(investigationScreen, SurveyorConstants.SQAPICDR));
		//assertTrue(verifyMapShowsUserLocation(investigationScreen));
		//assertTrue(addSourceDialog.verifyInvestigationStatus("In-Progress"));
		//assertTrue(verifyMapShowsUserLocation(investigationScreen));
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
	 *	-
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
	 *	-
	 */
	@Test
	@UseDataProvider(value = LeakScreenDataProvider.LEAK_SCREEN_DATA_PROVIDER_TC2555, location = LeakScreenDataProvider.class)
	public void TC2555_EnergyBackpack_ListOfPreviouslyLoggedSources(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2555_EnergyBackpack_ListOfPreviouslyLoggedSources ...");

		navigateToMapScreen(true /*waitForMapScreenLoad*/, SurveyorConstants.SQAPICDR);
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
			addLeakSourceFormDialog.fillForm(LeakDataGenerator.newBuilder().setDefaultValues().toMap());
			addSourceDialog.waitForScreenLoad();
			//addSourceDialog.clickOnAddOtherSourcesButton();
			addLeakSourceFormDialog.fillForm(LeakDataGenerator.newBuilder().setDefaultValues().toMap());
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
		String[] lisaNumbers = {"2", "4", "6"};
		String[] gapNumbers = {"1", "2", "3"};
		if (methodName.startsWith("TC2440")) {
			Object[][] tc2440 = LeakScreenDataProvider.dataProviderAndroidApp_TC2440();
			userDataRowID = (Integer)tc2440[0][1];
			reportDataRowID1 = (Integer)tc2440[0][2];
			tcId = "TC2440";
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(true /*isReusable*/).createReportAndAssignLisasAndGapsToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers, gapNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2441")) {
			Object[][] tc2441 = LeakScreenDataProvider.dataProviderAndroidApp_TC2441();
			userDataRowID = (Integer)tc2441[0][1];
			reportDataRowID1 = (Integer)tc2441[0][2];
			tcId = "TC2441";
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(true /*isReusable*/).createReportAndAssignLisasAndGapsToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers, gapNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2543")) {
			Object[][] tc2543 = LeakScreenDataProvider.dataProviderAndroidApp_TC2543();
			userDataRowID = (Integer)tc2543[0][1];
			reportDataRowID1 = (Integer)tc2543[0][2];
			tcId = "TC2543";
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(true /*isReusable*/).createReportAndAssignLisasAndGapsToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers, gapNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2545")) {
			Object[][] tc2545 = LeakScreenDataProvider.dataProviderAndroidApp_TC2545();
			userDataRowID = (Integer)tc2545[0][1];
			reportDataRowID1 = (Integer)tc2545[0][2];
			tcId = "TC2545";
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(true /*isReusable*/).createReportAndAssignLisasAndGapsToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers, gapNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2546")) {
			Object[][] tc2546 = LeakScreenDataProvider.dataProviderAndroidApp_TC2546();
			userDataRowID = (Integer)tc2546[0][1];
			reportDataRowID1 = (Integer)tc2546[0][2];
			tcId = "TC2546";
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(true /*isReusable*/).createReportAndAssignLisasAndGapsToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers, gapNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2547")) {
			Object[][] tc2547 = LeakScreenDataProvider.dataProviderAndroidApp_TC2547();
			userDataRowID = (Integer)tc2547[0][1];
			reportDataRowID1 = (Integer)tc2547[0][2];
			tcId = "TC2547";
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(true /*isReusable*/).createReportAndAssignLisasAndGapsToUser(tcId,
					userDataRowID, defaultAssignedUserDataRowID, reportDataRowID1, lisaNumbers, gapNumbers).getReportTitle();
		} else if (methodName.startsWith("TC2555")) {
			Object[][] tc2555 = LeakScreenDataProvider.dataProviderAndroidApp_TC2555();
			userDataRowID = (Integer)tc2555[0][1];
			reportDataRowID1 = (Integer)tc2555[0][2];
			tcId = "TC2555";
			generatedInvReportTitle = ReportDataGenerator.newSingleUseGenerator(true /*isReusable*/).createReportAndAssignLisasAndGapsToUser(tcId,
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
