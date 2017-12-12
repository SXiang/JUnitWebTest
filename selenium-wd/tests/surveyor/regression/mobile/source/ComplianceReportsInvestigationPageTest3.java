/**
 *
 */
package surveyor.regression.mobile.source;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Rectangle;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.Log;
import common.source.TestContext;
import surveyor.dataprovider.InvestigationReportDataProvider;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.data.UserDataReader.UserDataRow;
import surveyor.scommon.entities.LeakDetailEntity;
import surveyor.scommon.entities.OtherSourceEntity;
import surveyor.scommon.mobile.source.MobileInvestigatePage;
import surveyor.scommon.mobile.source.MobileInvestigationPage;
import surveyor.scommon.mobile.source.MobileLeakSourcePage;
import surveyor.scommon.mobile.source.MobileLoginPage;
import surveyor.scommon.mobile.source.MobileReportsPage;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.ReportInvestigationsPage.IndicationStatus;
import surveyor.scommon.source.ReportInvestigationsPage;

/**
 *
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsInvestigationPageTest3 extends BaseReportsPageActionTest {
	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static ReportInvestigationsPage reportInvestigationsPage;
	private static MobileLoginPage mobileLoginPage;
	private static MobileReportsPage mobileReportsPage;
	private static MobileInvestigationPage mobileInvestigationPage;
	private static MobileInvestigatePage mobileInvestigatePage;
	private static MobileLeakSourcePage mobileLeakSourcePage;

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects();
	}

	@Before
	public void beforeTest() throws Exception {
		initializeTestObjects();
		initializePageActions();
		// Select run mode here.
		setPropertiesForTestRunMode();
		mobileLoginPage = new MobileLoginPage();
		reportInvestigationsPage = new ReportInvestigationsPage(getDriver(), getBaseURL(), getTestSetup());
	}

	private static void setPropertiesForTestRunMode() throws Exception {
		setTestRunMode(ReportTestRunMode.FullTestRun);

		if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			complianceReportsPageAction.fillWorkingDataForReports(getUnitTestReportRowID());
		}
	}

	/**
	 * Initializes the page action objects.
	 * @throws Exception
	 */
	protected static void initializePageActions() throws Exception {
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
		complianceReportsPageAction = new ComplianceReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
		setReportsPage((ComplianceReportsPage)complianceReportsPageAction.getPageObject());
	}

	/**
	 * Test Case ID: TC1722_InProgressStateForInvestigationsInMobileApp
	 * Test Description: Mobile: "in progress" state for investigations in mobile app
	 * Script:
	 * - Log into mobile app
	 * - Select a report
	 * - Select a LISA marked as Not Investigated)
	 * - Click Investigate button
	 * - Use back button to return to previous page and click Refresh
	 * Results:
	 * - User will see list of reports containing LISAs assigned to user
	 * - User will see LISAs for that report. If user has supervisory role, all LISAs for that report will be displayed. Otherwise, only LISAs assigned to user will be displayed
	 * - User will be navigated to map view showing user's position on map
	 * - At top of screen, next to LISA ID, "In Progress" will be displayed.
	 * - LISA will be marked "In Progress"
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1722, location = InvestigationReportDataProvider.class)
	public void TC1722_InProgressStateForInvestigationsInMobileApp(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID, Integer mobileUserDataRowID2) throws Exception {
		Log.info("\nRunning TC1722_InProgressStateForInvestigationsInMobileApp ..." +
			 "\nTest Description: Mobile: 'in progress' state for investigations in mobile app");
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID)); //Utility Admin
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		String reportId = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportId(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());

		// Verify Lisas are numbered based on their amplitude
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		assertTrue(reportInvestigationsPage.verifyLisasOrderByAmplitude());
		
		// Assign Lisas to user
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";

		UserDataRow mobileUserDataRow2 = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID)); // Supervisor
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID2)); // Driver
		
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+1);
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+2);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);
		reportInvestigationsPage.waitForPageLoad();
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+3);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow2.username);
		
		// Mobile - login and investigate lisas - driver
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);

		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+1));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+3));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+2));

		// Mobile - add leak and complete
		LeakDetailEntity leakDetails = new LeakDetailEntity(mobileUserDataRow.username, 1);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1,  IndicationStatus.NOTINVESTIGATED, leakDetails);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "investigateMap-1"));
		mobileInvestigatePage.navigateBack();
		mobileInvestigationPage.waitUntilPageLoad();
		mobileInvestigatePage.refreshPage();
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+1, IndicationStatus.INPROGRESS));

		// Mobile - login and investigate lisas - Supervisor
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow2.username, mobileUserDataRow2.password);

		// Supervisor can see all the lisas
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+1));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+2));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+3));

		// Mobile - add leak and complete
		leakDetails = new LeakDetailEntity(mobileUserDataRow2.username, 2);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+2,  IndicationStatus.NOTINVESTIGATED, leakDetails);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "investigateMap-2"));
		mobileInvestigatePage.navigateBack();
		mobileInvestigationPage.waitUntilPageLoad();
		mobileInvestigatePage.refreshPage();
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+2,  IndicationStatus.INPROGRESS));

		mobileLoginPage.logout();	
	}

	/** Description needs to  be updated per Richard 
	 * Test Case ID: TC1723_MakeColorsOfInvestigationConsistentForDisposition
	 * Test Description: Mobile: Make colors of investigation boxes and gaps consistent for their disposition
	 * Script:
	 * - Log into Pcubed as a Supervisor or admin role user
	 * -  From the Compliance Reports page, find a report that has 6 or more LISAs and click the Investigate button
	 * - Select 4 LISAs and assign them to a driver-role user by clicking on a username from the dropdown
	 * -  As the user selected above, log into the mobile app
	 * -  Click on the report above
	 * -  Click on a LISA
	 * -  Click on Investigate button
	 * -  Click on the Found Gas Leak button
	 * -  Select Leak Found
	 * -  Select In Progress
	 * -  Select another LISA and set it as Complete/No Leak Found
	 * -  Select another LISA and set it as Complete/Leak Found
	 * -  Log into the mobile app as a supervisor-role user and select the same report
	 * Results:
	 * - User is navigated to dashboard
	 * -  A list of LISAs included in that report will be displayed
	 * -  The username will appear in the Investigator column of the table for the LISAs selected
	 * -  The mobile app shows a list of reports that contain LISAs assigned to the user
	 * -  The app shows a list of the LISAs for that report that are assigned to the user
	 * -  User is taken to a map view that shows the selected LISA and its color should be light grey color
	 * - The user sees options to select Not Investigated/Found Gas Leak/Found Methane Leak/No Leak Found
	 * -  The user sees options to select In Progress/Completed
	 * -  The LISA color should turn violet ( In-Progress)
	 * -  The LISA color should turn blue (Found No Leak)
	 * -  The LISA color should turn red (Leak Found)
	 * -  The app will display all LISAs for that report regardless of assignment. All unassigned LISAs should be light orange (Not Investigated)
	 * -  All assigned bot not investigated LISA should be displayed in light grey color (Not Investigated (Light Grey))
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1723, location = InvestigationReportDataProvider.class)
	public void TC1723_MakeColorsOfInvestigationConsistentForDisposition(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID, Integer mobileUserDataRowID2) throws Exception {
		Log.info("\nRunning TC1723_MakeColorsOfInvestigationConsistentForDisposition ..." +
			 "\nTest Description: Mobile: Make colors of investigation boxes and gaps consistent for their disposition");
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID)); //Utility Admin
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		String reportId = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportId(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());

		// Verify Lisas are numbered based on their amplitude
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);

		// Assign Lisas to user
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";

		UserDataRow mobileUserDataRow2 = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID)); // Supervisor
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID2)); // Driver
		
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+1,lisaNumberPrefix+4,lisaNumberPrefix+7);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);
		reportInvestigationsPage.waitForPageLoad();
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+8,lisaNumberPrefix+2,lisaNumberPrefix+3);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow2.username);
		
		// Mobile - login and investigate lisas - driver
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);

		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+1));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+4));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+2));
		
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+8));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+5));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+3));
		
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+7));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+6));

		// Mobile - no leak found
		mobileReportsPage.open();
		mobileReportsPage.clickOnReportName(reportName);
		LeakDetailEntity leakDetails3 = new LeakDetailEntity(mobileUserDataRow.username, 13);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1, leakDetails3);
		mobileInvestigatePage.clickOnInvestigate(leakDetails3);
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails3, false);
		// Mobile Found no Leak - color verification: blue
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1, IndicationStatus.NOGASFOUND);
		mobileInvestigatePage.clickOnFollow();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileNoGasFound-1", getStndSQACusLisaRectangle(1)));
		
		// Mobile - add leak and complete
		mobileReportsPage.open();
		mobileReportsPage.clickOnReportName(reportName);
		LeakDetailEntity leakDetails = new LeakDetailEntity(mobileUserDataRow.username, 1);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1, leakDetails);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddLeak();
		leakDetails.setDefaultTestData();
		mobileLeakSourcePage.addLeakDetails(leakDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails);		
		// Mobile FoundLeak - color verification: Red
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1, IndicationStatus.FOUNDGASLEAK);
		mobileInvestigatePage.clickOnFollow();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileFoundLeak-1", getStndSQACusLisaRectangle(1)));
		
		// Mobile - add leak and pause
		mobileReportsPage.open();
		mobileReportsPage.clickOnReportName(reportName);
		LeakDetailEntity leakDetails2 = new LeakDetailEntity(mobileUserDataRow.username, 12);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1, leakDetails2);
		mobileInvestigatePage.clickOnInvestigate(leakDetails2);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddLeak();
		leakDetails2.setDefaultTestData();
		mobileLeakSourcePage.addLeakDetails(leakDetails2);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnPauseInvestigation();
		// Mobile Inprogress - color verification: violet
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1, IndicationStatus.INPROGRESS);
		mobileInvestigatePage.clickOnFollow();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileInprogress-1", getStndSQACusLisaRectangle(1)));

		// Mobile - not investigated
		mobileReportsPage.open();
		mobileReportsPage.clickOnReportName(reportName);
		// Mobile not investigated - color verification: light grey
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+7, IndicationStatus.NOTINVESTIGATED);
		mobileInvestigatePage.clickOnFollow();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileNotInvestigated-7", getStndSQACusLisaRectangle(7)));
		
		// Mobile - login and investigate lisas - Supervisor
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow2.username, mobileUserDataRow2.password);

		// Supervisor can see all the lisas
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+1));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+4));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+8));
		
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+2));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+5));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+3));
		
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+6));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+7));

		// Mobile - no leak found
		mobileReportsPage.open();
		mobileReportsPage.clickOnReportName(reportName);
		leakDetails3 = new LeakDetailEntity(mobileUserDataRow2.username, 83);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, leakDetails3);
		mobileInvestigatePage.clickOnInvestigate(leakDetails3);
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails3, false);
		// Mobile Found no Leak - color verification: blue
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, IndicationStatus.NOGASFOUND);
		mobileInvestigatePage.clickOnFollow();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileNoGasFound-8", getStndSQACusLisaRectangle(8)));
		
		// Mobile - add leak and complete
		mobileReportsPage.open();
		mobileReportsPage.clickOnReportName(reportName);
		leakDetails = new LeakDetailEntity(mobileUserDataRow2.username, 8);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, leakDetails);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddLeak();
		leakDetails.setDefaultTestData();
		mobileLeakSourcePage.addLeakDetails(leakDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails);		
		// Mobile FoundLeak - color verification: Red
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, IndicationStatus.FOUNDGASLEAK);
		mobileInvestigatePage.clickOnFollow();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileFoundLeak-8",getStndSQACusLisaRectangle(8)));
		
		// Mobile - add leak and pause
		mobileReportsPage.open();
		mobileReportsPage.clickOnReportName(reportName);
		leakDetails2 = new LeakDetailEntity(mobileUserDataRow2.username, 82);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, leakDetails2);
		mobileInvestigatePage.clickOnInvestigate(leakDetails2);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddLeak();
		leakDetails2.setDefaultTestData();
		mobileLeakSourcePage.addLeakDetails(leakDetails2);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnPauseInvestigation();
		// Mobile Inprogress - color verification: violet
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8);
		mobileInvestigatePage.clickOnFollow();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileInprogress-8",getStndSQACusLisaRectangle(8)));

		// Mobile - not assigned
		mobileReportsPage.open();
		mobileReportsPage.clickOnReportName(reportName);
		// Mobile not assigned - color verification: orange
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+5, IndicationStatus.NOTINVESTIGATED);
		mobileInvestigatePage.clickOnFollow();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileNotAssigned-5", getStndSQACusLisaRectangle(5)));
		mobileLoginPage.logout();
	}

	/**
	 * Test Case ID: TC1761_MoibleUserIsAbleToEditLeakDetails
	 * Test Description: Mobile app user should be able to edit leak information associated with a given LISA
	 * Script:
	 * - Log into the mobile app as a user to whom LISAs have been assigned
	 * -  Click on a report
	 * -  Click on an already investigated LISA (marked as either Complete or In Progress)
	 * -  Click on the Investigate button
	 * -  Click on the Add Source button
	 * -  Select an item marked as a Leak and click on it
	 * -  Change some of the details - be sure that all fields are filled in - and click OK
	 * -  Select the LISA again and verify the changes 
	 * Results:
	 * - User should see a list of reports containing LISAs which were assigned to user
	 * -  List of LISAs for that report should appear. If the user has a supervisory role, all LISAs for that report will appear. Otherwise, only LISAs assigned to the user will appear
	 * -  The user is navigated to the map view showing user's position with Follow and Directions buttons on the right and Investigate button at bottom left
	 * -  Add Source and Add CGI buttons will appear on the right
	 * -  A popup will appear with the details of leak(s) for that LISA
	 * -  The details for that leak will be displayed.
	 * -  All drop-down menus should drop DOWN, not go UP. The user will be navigated to the previous screen showing the list of leaks
	 * -  The edited details should persist
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1761, location = InvestigationReportDataProvider.class)
	public void TC1761_MoibleUserIsAbleToEditLeakDetails(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID, Integer mobileUserDataRowID2) throws Exception {
		Log.info("\nRunning TC1761_MoibleUserIsAbleToEditLeakDetails ..." +
			 "\nTest Description: Mobile app user should be able to edit leak information associated with a given LISA");
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID)); //Utility Admin
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		complianceReportsPageAction.setReportGenerationTimeout(String.valueOf(REPORT_GENERATION_TIMEOUT_90MIN_IN_SECONDS), reportDataRowID);
		String reportId = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportId(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());

		// Verify Lisas are numbered based on their amplitude
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		assertTrue(reportInvestigationsPage.verifyLisasOrderByAmplitude());
		
		// Assign Lisas to user
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";

		UserDataRow mobileUserDataRow2 = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID)); // Supervisor
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID2)); // Driver
		
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+1);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);
		reportInvestigationsPage.waitForPageLoad();
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+8);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow2.username);
		
		// Mobile - login and investigate lisas - driver
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);

		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+1));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+4));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+8));
		
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+2));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+5));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+3));
		
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+6));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+7));

		// Mobile - add leak and complete
		LeakDetailEntity leakDetails = new LeakDetailEntity(mobileUserDataRow.username, 1);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1, leakDetails);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddLeak();
		leakDetails.setDefaultTestData();
		mobileLeakSourcePage.addLeakDetails(leakDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails);	

		// Mobile - modify leak details
		LeakDetailEntity leakDetails2 = leakDetails;
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1, IndicationStatus.FOUNDGASLEAK);
		mobileInvestigatePage.clickOnInvestigate(leakDetails2);
		mobileInvestigatePage.clickOnAddSource();
		mobileInvestigatePage.openLeakDetail();
		leakDetails2.modifyTestData();		
		mobileLeakSourcePage.addLeakDetails(leakDetails2);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails2);
		
		// Mobile - verify leak details
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1, IndicationStatus.FOUNDGASLEAK);
		mobileInvestigatePage.clickOnInvestigate(leakDetails2);
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "investigateMap-1"));
		mobileInvestigatePage.clickOnAddSource();
		mobileInvestigatePage.openLeakDetail();
		assertTrue(mobileLeakSourcePage.verifyLeakDetails(leakDetails2));
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails2);

		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1, IndicationStatus.FOUNDGASLEAK);
		mobileInvestigatePage.clickOnFollow();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileFollowLeak-1", getStndSQACusLisaRectangle(1)));
		
		mobileInvestigatePage.clickOnDirections();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileDirectionsLeak-1", new Rectangle(0,120,0,-1000)));
		
		// Mobile - login and investigate lisas - Supervisor
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow2.username, mobileUserDataRow2.password);

		// Supervisor can see all the lisas
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+1));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+4));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+8));
		
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+2));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+5));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+3));
		
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+6));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+7));

		// Mobile - add leak and complete
		leakDetails = new LeakDetailEntity(mobileUserDataRow.username, 2);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, leakDetails);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddLeak();
		leakDetails.setDefaultTestData();
		mobileLeakSourcePage.addLeakDetails(leakDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails);	

		// Mobile - modify leak details
		leakDetails2 = leakDetails;
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, IndicationStatus.FOUNDGASLEAK);
		mobileInvestigatePage.clickOnInvestigate(leakDetails2);
		mobileInvestigatePage.clickOnAddSource();
		mobileInvestigatePage.openLeakDetail();
		leakDetails2.modifyTestData();		
		mobileLeakSourcePage.addLeakDetails(leakDetails2);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails2);
		
		// Mobile - verify leak details
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, IndicationStatus.FOUNDGASLEAK);
		mobileInvestigatePage.clickOnInvestigate(leakDetails2);
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "investigateMap-8"));
		mobileInvestigatePage.clickOnAddSource();
		mobileInvestigatePage.openLeakDetail();
		assertTrue(mobileLeakSourcePage.verifyLeakDetails(leakDetails2));
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails2);

		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, IndicationStatus.FOUNDGASLEAK);
		mobileInvestigatePage.clickOnFollow();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileFollowLeak-8", getStndSQACusLisaRectangle(8)));
		
		mobileInvestigatePage.clickOnDirections();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileDirectionsLeak-8", new Rectangle(0,120,0,-1000)));
		
		mobileLoginPage.logout();	
	
	}

	/**
	 * Test Case ID: TC1763_MobileUserAbleToEditOtherSource
	 * Test Description: Mobile app user should be able to edit leak information associated with a given LISA
	 * Script:
	 * -  Log into the mobile app as a user to whom LISAs have been assigned
	 * -  Click on a report
	 * -  Click on an already investigated LISA (marked as either Complete or In Progress)
	 * -  Click on the Investigate button
	 * -  Click on the Add Source button
	 * -  Select an item marked as a Other Source and click on it
	 * -  Change some of the details and click OK
	 * -  Select the LISA again and verify the changes 


	 * Results:
	 * - User should see a list of reports containing LISAs which were assigned to user
	 * -  List of LISAs for that report should appear. If the user has a supervisory role, all LISAs for that report will appear. Otherwise, only LISAs assigned to the user will appear
	 * -  The user is navigated to the map view showing user's position with Follow and Directions buttons on the right and Investigate button at bottom left
	 * -  Add Source and Add CGI buttons will appear on the right
	 * -  A popup will appear with the details of leak(s) for that LISA
	 * -    Location - Latitude, Longitude
	 * -    Source - Sewer, Catch Basin, Landfill, Swamp, Customer, Other Enclosure, Other Natural Source
	 * -    Additional Notes
	 * -  The details for that leak will be displayed.
	 * -  The user will be navigated to the previous screen showing the list of leaks
	 * -  The edited details should persist
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1763, location = InvestigationReportDataProvider.class)
	public void TC1763_MobileUserAbleToEditOtherSource(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID, Integer mobileUserDataRowID2) throws Exception {
		Log.info("\nRunning TC1763_MobileUserAbleToEditOtherSource ..." +
			 "\nTest Description: Mobile app user should be able to edit leak information associated with a given LISA");
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID)); //Utility Admin
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		String reportId = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportId(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());

		// Verify Lisas are numbered based on their amplitude
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		assertTrue(reportInvestigationsPage.verifyLisasOrderByAmplitude());
		
		// Assign Lisas to user
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";

		UserDataRow mobileUserDataRow2 = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID)); // Supervisor
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID2)); // Driver
		
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+1);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);
		reportInvestigationsPage.waitForPageLoad();
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+8);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow2.username);
		
		// Mobile - login and investigate lisas - driver
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);

		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+1));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+4));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+8));
		
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+8));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+5));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+3));
		
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+6));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+7));

		// Mobile - add leak and complete
		OtherSourceEntity sourceDetails = new OtherSourceEntity(mobileUserDataRow.username, 1);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1, sourceDetails);
		mobileInvestigatePage.clickOnInvestigate(sourceDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddOtherSource();
		sourceDetails.setDefaultTestData();
		mobileLeakSourcePage.addOtherSource(sourceDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(sourceDetails);	

		// Mobile - modify leak details
		OtherSourceEntity sourceDetails2 = sourceDetails;
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1, IndicationStatus.FOUNDOTHERSOURCE);
		mobileInvestigatePage.clickOnInvestigate(sourceDetails2);
		mobileInvestigatePage.clickOnAddSource();
		mobileInvestigatePage.openOtherSourceDetail();
		sourceDetails2.modifyTestData();
		assertTrue(mobileLeakSourcePage.verifyOtherSourceTypeOptions());
		mobileLeakSourcePage.addOtherSource(sourceDetails2);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(sourceDetails2);
		
		// Mobile - verify leak details
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1, IndicationStatus.FOUNDOTHERSOURCE);
		mobileInvestigatePage.clickOnInvestigate(sourceDetails2);
		mobileInvestigatePage.clickOnAddSource();
		mobileInvestigatePage.openOtherSourceDetail();		
		assertTrue(mobileLeakSourcePage.verifyOtherSource(sourceDetails2));
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(sourceDetails2);

		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1, IndicationStatus.FOUNDOTHERSOURCE);
		mobileInvestigatePage.clickOnFollow();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileFollowOtherSource-1", getStndSQACusLisaRectangle(1)));
		
		mobileInvestigatePage.clickOnDirections();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileDirectionsOtherSource-1", new Rectangle(0,120,0,-1000)));
		
		// Mobile - login and investigate lisas - Supervisor
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow2.username, mobileUserDataRow2.password);

		// Supervisor can see all the lisas
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+1));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+4));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+8));
		
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+2));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+5));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+3));
		
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+6));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+7));

		// Mobile - add leak and complete
		sourceDetails = new OtherSourceEntity(mobileUserDataRow.username, 2);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, sourceDetails);
		mobileInvestigatePage.clickOnInvestigate(sourceDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddOtherSource();
		sourceDetails.setDefaultTestData();
		mobileLeakSourcePage.addOtherSource(sourceDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(sourceDetails);	

		// Mobile - modify leak details
		sourceDetails2 = sourceDetails;
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, IndicationStatus.FOUNDOTHERSOURCE);
		mobileInvestigatePage.clickOnInvestigate(sourceDetails2);
		mobileInvestigatePage.clickOnAddSource();
		mobileInvestigatePage.openOtherSourceDetail();
		sourceDetails2.modifyTestData();
		assertTrue(mobileLeakSourcePage.verifyOtherSourceTypeOptions());
		mobileLeakSourcePage.addOtherSource(sourceDetails2);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(sourceDetails2);
		
		// Mobile - verify leak details
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, IndicationStatus.FOUNDOTHERSOURCE);
		mobileInvestigatePage.clickOnInvestigate(sourceDetails2);
		mobileInvestigatePage.clickOnAddSource();
		mobileInvestigatePage.openOtherSourceDetail();		
		assertTrue(mobileLeakSourcePage.verifyOtherSource(sourceDetails2));
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(sourceDetails2);

		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, IndicationStatus.FOUNDOTHERSOURCE);
		mobileInvestigatePage.clickOnFollow();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileFollowOtherSource-8", getStndSQACusLisaRectangle(8)));
		
		mobileInvestigatePage.clickOnDirections();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileDirectionsOtherSource-8", new Rectangle(0,120,0,-1000)));
		
		mobileLoginPage.logout();	
	
	
	}

	/**
	 * Test Case ID: TC1764_MobileUserIsAbleToDeleteLeaks
	 * Test Description: Mobile app user should be able to delete leak information associated with a given LISA
	 * Script:
	 * - Log into the mobile app as a user to whom LISAs have been assigned
	 * -  Click on a report
	 * -  Click on an already investigated LISA (marked as either Complete or In Progress)
	 * -  Click on the Investigate button
	 * -  Click on the Add Source button
	 * -  Select an item marked as a Leak and click on it
	 * -  Click the Delete button
	 * -  Click OK on the Confirmation


	 * Results:
	 * -  User should see a list of reports containing LISAs which were assigned to user
	 * -  List of LISAs for that report should appear. If the user has a supervisory role, all LISAs for that report will appear. Otherwise, only LISAs assigned to the user will appear
	 * -  The user is navigated to the map view showing user's position with Follow and Directions buttons on the right and Investigate button at bottom left
	 * -  Add Source and Add CGI buttons will appear on the right
	 * -  A popup will appear with the details of leak(s) for that LISA
	 * -  The details for that leak will be displayed.
	 * -  A confirmation will appear
	 * -  The deleted leak will no longer appear in the list
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1764, location = InvestigationReportDataProvider.class)
	public void TC1764_MobileUserIsAbleToDeleteLeaks(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID, Integer mobileUserDataRowID2) throws Exception {
		Log.info("\nRunning TC1764_MobileUserIsAbleToDeleteLeaks ..." +
			 "\nTest Description: Mobile app user should be able to delete leak information associated with a given LISA");
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID)); //Utility Admin
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		String reportId = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportId(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());

		// Verify Lisas are numbered based on their amplitude
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		assertTrue(reportInvestigationsPage.verifyLisasOrderByAmplitude());
		
		// Assign Lisas to user
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";

		UserDataRow mobileUserDataRow2 = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID)); // Supervisor
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID2)); // Driver
		
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+1);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);
		reportInvestigationsPage.waitForPageLoad();
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+8);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow2.username);
		
		// Mobile - login and investigate lisas - driver
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);

		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+1));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+4));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+8));
		
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+2));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+5));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+3));
		
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+6));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+7));

		// Mobile - add leak and complete
		LeakDetailEntity leakDetails = new LeakDetailEntity(mobileUserDataRow.username, 1);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1, leakDetails);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddLeak();
		leakDetails.setDefaultTestData();
		mobileLeakSourcePage.addLeakDetails(leakDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails);	

		// Mobile - delete leaks
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1, IndicationStatus.FOUNDGASLEAK);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileInvestigatePage.deleteLeaks();
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails, false);
		
		// Mobile - verify leak details
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1, IndicationStatus.NOGASFOUND);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "investigateMap-1"));
		mobileInvestigatePage.clickOnAddSource();
		assertFalse(mobileInvestigatePage.isLeakShowing());
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails,false);

		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1, IndicationStatus.NOGASFOUND);
		mobileInvestigatePage.clickOnFollow();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileFollowNoGasFound-1", getStndSQACusLisaRectangle(1)));
		
		mobileInvestigatePage.clickOnDirections();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileDirectionsNoGasFound-1", new Rectangle(0,120,0,-1000)));
		
		// Mobile - login and investigate lisas - Supervisor
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow2.username, mobileUserDataRow2.password);

		// Supervisor can see all the lisas
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+1));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+4));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+8));
		
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+2));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+5));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+3));
		
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+6));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+7));

		// Mobile - add leak and complete
		leakDetails = new LeakDetailEntity(mobileUserDataRow.username, 2);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, leakDetails);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddLeak();
		leakDetails.setDefaultTestData();
		mobileLeakSourcePage.addLeakDetails(leakDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails);	

		// Mobile - delete leaks
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, IndicationStatus.FOUNDGASLEAK);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileInvestigatePage.deleteLeaks();
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails, false);
		
		// Mobile - verify leak details
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, IndicationStatus.NOGASFOUND);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "investigateMap-8", getStndSQACusLisaRectangle(8)));
		mobileInvestigatePage.clickOnAddSource();
		assertFalse(mobileInvestigatePage.isLeakShowing());
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails,false);

		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, IndicationStatus.NOGASFOUND);
		mobileInvestigatePage.clickOnFollow();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileFollowNoGasFound-8", getStndSQACusLisaRectangle(8)));
		
		mobileInvestigatePage.clickOnDirections();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileDirectionsNoGasFound-8", new Rectangle(0,120,0,-1000)));
	
		mobileLoginPage.logout();	
	}

	/**
	 * Test Case ID: TC1767_MobileUserIsAbleToDeleteAllOtherSource
	 * Test Description: Mobile app user should be able to delete information for all leaks associated with a given LISA
	 * Script:
	 * -  Log into the mobile app as a user to whom LISAs have been assigned
	 * -  Click on a report
	 * -  Click on an already investigated LISA (marked as either Complete or In Progress)
	 * -  Click on the Investigate button
	 * -  Click on the Add Source button
	 * -  Select an item marked as a Leak and click on it
	 * -  Click the Delete button
	 * -  Click OK on the Confirmation
	 * -  Repeat until all leaks have been deleted
	 * Results:
	 * - User should see a list of reports containing LISAs which were assigned to user
	 * -  List of LISAs for that report should appear. If the user has a supervisory role, all LISAs for that report will appear. Otherwise, only LISAs assigned to the user will appear
	 * -  The user is navigated to the map view showing user's position with Follow and Directions buttons on the right and Investigate button at bottom left
	 * -  Add Source and Add CGI buttons will appear on the right
	 * -  A popup will appear with the details of leak(s) for that LISA
	 * -  The details for that leak will be displayed.
	 * -  A confirmation will appear
	 * -  The deleted leak will no longer appear in the list
	 * -  No leaks will appear for that LISA
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1767, location = InvestigationReportDataProvider.class)
	public void TC1767_MobileUserIsAbleToDeleteAllOtherSource(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID, Integer mobileUserDataRowID2) throws Exception {
		Log.info("\nRunning TC1767_MobileUserIsAbleToDeleteAllOtherSource ..." +
			 "\nTest Description: Mobile app user should be able to delete information for all leaks associated with a given LISA");
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID)); //Utility Admin
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		complianceReportsPageAction.setReportGenerationTimeout(String.valueOf(REPORT_GENERATION_TIMEOUT_90MIN_IN_SECONDS), reportDataRowID);
		String reportId = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportId(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());

		// Verify Lisas are numbered based on their amplitude
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		assertTrue(reportInvestigationsPage.verifyLisasOrderByAmplitude());
		
		// Assign Lisas to user
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";

		UserDataRow mobileUserDataRow2 = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID)); // Supervisor
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID2)); // Driver
		
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+1);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);
		reportInvestigationsPage.waitForPageLoad();
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+8);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow2.username);
		
		// Mobile - login and investigate lisas - driver
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);

		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+1));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+4));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+8));
		
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+2));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+5));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+3));
		
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+6));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+7));

		// Mobile - add leak and complete
		OtherSourceEntity sourceDetails = new OtherSourceEntity(mobileUserDataRow.username, 1);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1, sourceDetails);
		mobileInvestigatePage.clickOnInvestigate(sourceDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddOtherSource();
		sourceDetails.setDefaultTestData();
		mobileLeakSourcePage.addOtherSource(sourceDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(sourceDetails);	
		
		// Mobile - delete other sources
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1, IndicationStatus.FOUNDOTHERSOURCE);
		mobileInvestigatePage.clickOnInvestigate(sourceDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileInvestigatePage.deleteOtherSources();
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(sourceDetails, false);
		
		// Mobile - verify leak details
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1, IndicationStatus.NOGASFOUND);
		mobileInvestigatePage.clickOnInvestigate(sourceDetails);
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "investigateMap-1"));
		mobileInvestigatePage.clickOnAddSource();
		assertFalse(mobileInvestigatePage.isOtherSourceShowing());
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(sourceDetails,false);

		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+1, IndicationStatus.NOGASFOUND);
		mobileInvestigatePage.clickOnFollow();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileFollowNoGasFound-1", getStndSQACusLisaRectangle(1)));
		
		mobileInvestigatePage.clickOnDirections();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileDirectionsNoGasFound-1", new Rectangle(0,120,0,-1000)));
		
		// Mobile - login and investigate lisas - Supervisor
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow2.username, mobileUserDataRow2.password);

		// Supervisor can see all the lisas
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+1));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+4));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+8));
		
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+2));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+5));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+3));
		
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+6));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+7));

		// Mobile - add leak and complete
		sourceDetails = new OtherSourceEntity(mobileUserDataRow.username, 2);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, sourceDetails);
		mobileInvestigatePage.clickOnInvestigate(sourceDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddOtherSource();
		sourceDetails.setDefaultTestData();
		mobileLeakSourcePage.addOtherSource(sourceDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(sourceDetails);	
		
		// Mobile - delete other sources
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, IndicationStatus.FOUNDOTHERSOURCE);
		mobileInvestigatePage.clickOnInvestigate(sourceDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileInvestigatePage.deleteOtherSources();
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(sourceDetails, false);
		
		// Mobile - verify leak details
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, IndicationStatus.NOGASFOUND);
		mobileInvestigatePage.clickOnInvestigate(sourceDetails);
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "investigateMap-8"));
		mobileInvestigatePage.clickOnAddSource();
		assertFalse(mobileInvestigatePage.isOtherSourceShowing());
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(sourceDetails,false);

		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, IndicationStatus.NOGASFOUND);
		mobileInvestigatePage.clickOnFollow();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileFollowNoGasFound-8", getStndSQACusLisaRectangle(8)));
		
		mobileInvestigatePage.clickOnDirections();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileDirectionsNoGasFound-8", new Rectangle(0,120,0,-1000)));
			
		mobileLoginPage.logout();	
	}

	private Rectangle getStndSQACusLisaRectangle(int i){
		switch(i){
		case 1: return new Rectangle(200, 615, 350, 200);
		case 3: return new Rectangle(200, 450, 350, 200);
		case 6: return new Rectangle(200, 600, 350, 200);
		case 7: return new Rectangle(400, 590, 50, 30);
		case 8: return new Rectangle(340, 560, 110, 60);
		case 5: return new Rectangle(350,590,50,30);
		default: return new Rectangle(350,590,50,30);
		}
	}
}