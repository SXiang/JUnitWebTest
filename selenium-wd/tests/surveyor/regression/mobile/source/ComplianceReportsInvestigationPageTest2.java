/**
 *
 */
package surveyor.regression.mobile.source;

import static org.junit.Assert.*;
import java.util.List;

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
import surveyor.scommon.source.ReportInvestigationsPage;
import surveyor.scommon.source.ReportInvestigationsPage.IndicationStatus;

/**
 *
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsInvestigationPageTest2 extends BaseReportsPageActionTest {
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
	 * Test Case ID: TC234_InvestigateLisaRandomlyAsDriverUser
	 * Test Description: Investigate Lisa randomly as driver user and check the lisa number on views and pdf reports
	 * Script:
	 * - Generate compliance report for survey having more number of indications in it
	 * - Customer has Mobile view priviledge
	 * - Driver role user
	 * - Login to Web View, as Customer Admin
	 * - On Home Page, navigate to Reports -> Compliance Reports page
	 * - Click on Investigate icon present for the compliance report
	 * - Select few Lisa to assign and click on Assign Investigators button
	 * - Select Assigned To (driver role user) name from the drop down and click OK
	 * - Assign couple of LISA to other role user and keep few of them un-assigned (few of them should be unassigned one and few that were assigned to admin user)
	 * Eg. LISA 1, 4, 8 - assigned to driver user
	 * LISA 2,5, 7 - assigned to utiladmin or supervisor user
	 * LISA 2,6,9,10 - unassigned
	 * - Login to application using mobile with Assigned To user credentials
	 * - Select assigned LISAs for investigation
	 * - Click on Investigate-> Add Source -> Add Leak (Lisa 1 - Leak 1
	 * - Fill in leak details in popup
	 * - Click on Mark as Complete
	 * - Similarly investigate the LISAs in random order
	 * - Navigate to investigation report page
	 * - On compliance report page, download the investigation pdf report and investigation data csv
	 * - Web View, click on Investigate icon and navigate to map view of Lisa assignment page by clicking on Assign Lisa
	 * - Mobile View, log in as Assigned To user credentials
	 * - Click on above investigated Lisa and click on Investigate button
	 * (E.g. Lisa 1 is now in In Progress State)
	 * - Web View, click on Investigate icon and navigate to map view of Lisa assignment page by clicking on Assign Lisa
	 * - Click on Add leak and select above added leak (Leak 1)
	 * - Delete the leak and click OK
	 * - Mark the Lisa as completed and no leak found
	 * - Log in as customer supervisor user 
	 * - Click on above report
	 * - Click on any of the LISA which is assigned to driver user for investigation (LISA 4)
	 * - Click on Investigate
	 * - Click on Add Source -> Add Leak
	 * - Fill out leak details on popup and click OK
	 * - Click Mark as Complete
	 * - Web View, click on Investigate icon and navigate to map view of Lisa assignment page by clicking on Assign Lisa
	 * - On compliance report page, download the investigation pdf report and investigation data csv
	 * Results:
	 * - Driver user should see Lisa numbers that are assigned to user for investigation and not other LISAs that are un-assigned or assigned to other users in mobile view
	 * - Latest reports is displayed first (Report are sorted in descending order by date attribute)
	 * - Lisa are displayed in descending order by amplitude value
	 * - Driver user should not see LISAs that were investigated by util admin or supervisor user (eg. LISA#1, #4 should not be present in list)
	 * - Lisa number should be same on web, mobile, investigation report PDF, investigation data csv and compliance report PDF
	 * - Investigate report PDF and Investigation Data csv should have Lisa Investigation Table with Lisa#, Amplitude, Status (Not Investigated, In Progress, No Gas Found, Found Gas Leak), Investigation Date/Time and Investigator name.
	 * - Investigate report PDF and Investigation Data csv should have Leak information like status, GPS location, notes, Leak rate, etc same as in mobile view
	 * - Investigated and Leak found Lisa is present in red color
	 * - Web View Lisa Assignment page and mobile view should show violet color for In Progress Lisa
	 * - Web View Lisa Assignment page and mobile view should show blue color for Investigated and no leak found Lisa
	 * - Investigation PDF and csv files should not show deleted leak information
	 * - All data present on mobile app should be present in PDF and csv with same details. Eg. Lisa number, amplitude, Status, Investigation Date/Time, Investigator, Duration, Source, Lat/Long, Leak details, notes, Leak source type, etc
	 */
	@Test /* Verification of indication colors is pending -US4581 */
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC234, location = InvestigationReportDataProvider.class)
	public void TC234_InvestigateLisaRandomlyAsDriverUser(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID, Integer mobileUserDataRowID2) throws Exception {
		Log.info("\nRunning TC234_InvestigateLisaRandomlyAsDriverUser ..." +
			 "\nTest Description: Investigate Lisa randomly as driver user and check the lisa number on views and pdf reports");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID)); //Utility Admin
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		String reportId = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportName(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());

		// Verify Lisas are numbered based on their amplitude
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		assertTrue(reportInvestigationsPage.verifyLisasOrderByAmplitude());
		
		// Assign Lisas to user
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";

		UserDataRow mobileUserDataRow2 = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID)); // Supervisor
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID2)); // Driver
		
		reportInvestigationsPage.selectLisa(lisaNumberPrefix+1);
		reportInvestigationsPage.selectLisa(lisaNumberPrefix+4);
		reportInvestigationsPage.selectLisa(lisaNumberPrefix+8);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);
		reportInvestigationsPage.waitForPageLoad();
		reportInvestigationsPage.selectLisa(lisaNumberPrefix+2);
		reportInvestigationsPage.selectLisa(lisaNumberPrefix+5);
		reportInvestigationsPage.selectLisa(lisaNumberPrefix+3);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow2.username);
		
		// Mobile - login and investigate lisas - driver
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);

		List<String> reportIDs =	mobileReportsPage.getReports();	
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+1));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+4));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+8));
		
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+2));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+5));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+3));
		
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+6));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+7));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+9));

		// Mobile - add leak and complete
		LeakDetailEntity leakDetails = new LeakDetailEntity(mobileUserDataRow.username, 4);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+4, leakDetails);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddLeak();
		leakDetails.setDefaultTestData();
		mobileLeakSourcePage.addLeakDetails(leakDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails);

		
		mobileReportsPage.open();
		mobileReportsPage.clickOnReportName(reportName);
		LeakDetailEntity leakDetails2 = new LeakDetailEntity(mobileUserDataRow.username, 8);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, leakDetails2);
		mobileInvestigatePage.clickOnInvestigate(leakDetails2);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddLeak();
		leakDetails2.setDefaultTestData();
		mobileLeakSourcePage.addLeakDetails(leakDetails2);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails2);
		
		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		//Reports(Mobile) are displayed in descending order by Date
		assertTrue(complianceReportsPageAction.getComplianceReportsPage().verifyReportsAreOrderedByDate(reportIDs));
		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationPDF(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID)));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationData(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationCSVFileDownloadToComplete(EMPTY, reportDataRowID));
		complianceReportsPageAction.clickOnCloseReportViewer(EMPTY, getReportRowID(reportDataRowID));

		assertTrue(mobileLeakSourcePage.verifyPDFLeakDetails(leakDetails.toPDFLeakDetails(), complianceReportsPageAction.getLISAInvestigationPDFData(4,reportDataRowID)));
		assertTrue(mobileLeakSourcePage.verifyMetaLeakDetails(leakDetails.toCSVLeakDetails(), complianceReportsPageAction.getLISAInvestigationMetaData(4,reportDataRowID)));
		assertTrue(mobileLeakSourcePage.verifyPDFLeakDetails(leakDetails2.toPDFLeakDetails(), complianceReportsPageAction.getLISAInvestigationPDFData(8,reportDataRowID)));
		assertTrue(mobileLeakSourcePage.verifyMetaLeakDetails(leakDetails2.toCSVLeakDetails(), complianceReportsPageAction.getLISAInvestigationMetaData(8,reportDataRowID)));

		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		assertEquals(IndicationStatus.FOUNDGASLEAK.toString(), reportInvestigationsPage.getLisaStatus(lisaNumberPrefix+4));
		assertEquals(IndicationStatus.FOUNDGASLEAK.toString(),reportInvestigationsPage.getLisaStatus(lisaNumberPrefix+8));
		
		//Web view - investigate
		reportInvestigationsPage.investigateItem(lisaNumberPrefix+4);
		reportInvestigationsPage.clickOnPauseInvestigation();
		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		assertEquals(IndicationStatus.INPROGRESS.toString(), reportInvestigationsPage.getLisaStatus(lisaNumberPrefix+4));
		
		//Mobile view - investigate
		mobileReportsPage.open();
		mobileReportsPage.clickOnReportName(reportName);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, leakDetails2);
		mobileInvestigatePage.clickOnInvestigate(leakDetails2);
		mobileInvestigatePage.clickOnPauseInvestigation();
		
		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		assertEquals(IndicationStatus.INPROGRESS.toString(), reportInvestigationsPage.getLisaStatus(lisaNumberPrefix+8));

        //Delete leaks
		mobileReportsPage.open();
		mobileReportsPage.clickOnReportName(reportName);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+8, leakDetails2);
		mobileInvestigatePage.clickOnInvestigate(leakDetails2);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.deleteLeaks();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails2);
		
		
		complianceReportsPageAction.open(EMPTY, reportDataRowID);
	
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationPDF(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID)));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationData(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationCSVFileDownloadToComplete(EMPTY, reportDataRowID));
		complianceReportsPageAction.clickOnCloseReportViewer(EMPTY, getReportRowID(reportDataRowID));

		// Verify deleted leaks are not showing
		assertFalse(mobileLeakSourcePage.verifyPDFLeakDetails(leakDetails2.toPDFLeakDetails(), complianceReportsPageAction.getLISAInvestigationPDFData(8,reportDataRowID)));
		assertFalse(mobileLeakSourcePage.verifyMetaLeakDetails(leakDetails2.toCSVLeakDetails(), complianceReportsPageAction.getLISAInvestigationMetaData(8,reportDataRowID)));

		// Mobile - login and investigate lisas - Supervisor
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow2.username, mobileUserDataRow2.password);

		// Supervisor can see all the lisas
		reportIDs =	mobileReportsPage.getReports();	
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+1));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+4));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+8));
		
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+2));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+5));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+3));
		
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+6));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+7));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+9));

		// Mobile - add leak and complete
		leakDetails = new LeakDetailEntity(mobileUserDataRow2.username, 2);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+2, leakDetails);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddLeak();
		leakDetails.setDefaultTestData();
		mobileLeakSourcePage.addLeakDetails(leakDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails);
		// Mobile - add other source and complete
		mobileReportsPage.open();
		mobileReportsPage.clickOnReportName(reportName);
		leakDetails2 = new LeakDetailEntity(mobileUserDataRow2.username, 5);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+5, leakDetails2);
		mobileInvestigatePage.clickOnInvestigate(leakDetails2);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddLeak();
		leakDetails2.setDefaultTestData();
		mobileLeakSourcePage.addLeakDetails(leakDetails2);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails2);

		// Todo: Mobile FoundLeak - color verification: Red
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+5);
		mobileInvestigatePage.clickOnFollow();
//		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileFoundLeak-5"));
		
		// PDF and CSV
		complianceReportsPageAction.open(EMPTY, reportDataRowID);

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationPDF(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID)));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationData(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationCSVFileDownloadToComplete(EMPTY, reportDataRowID));
		complianceReportsPageAction.clickOnCloseReportViewer(EMPTY, getReportRowID(reportDataRowID));

		assertTrue(mobileLeakSourcePage.verifyPDFLeakDetails(leakDetails.toPDFLeakDetails(), complianceReportsPageAction.getLISAInvestigationPDFData(2,reportDataRowID)));
		assertTrue(mobileLeakSourcePage.verifyMetaLeakDetails(leakDetails.toCSVLeakDetails(), complianceReportsPageAction.getLISAInvestigationMetaData(2,reportDataRowID)));
		assertTrue(mobileLeakSourcePage.verifyPDFLeakDetails(leakDetails2.toPDFLeakDetails(), complianceReportsPageAction.getLISAInvestigationPDFData(5,reportDataRowID)));
		assertTrue(mobileLeakSourcePage.verifyMetaLeakDetails(leakDetails2.toCSVLeakDetails(), complianceReportsPageAction.getLISAInvestigationMetaData(5,reportDataRowID)));

		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		assertEquals(IndicationStatus.FOUNDGASLEAK.toString(), reportInvestigationsPage.getLisaStatus(lisaNumberPrefix+2));
		assertEquals(IndicationStatus.FOUNDGASLEAK.toString(),reportInvestigationsPage.getLisaStatus(lisaNumberPrefix+5));

		// TODO: Web FoundLeak - color verification: Red
		reportInvestigationsPage.clickOnInvestigate();
		reportInvestigationsPage.clickOnLisa(lisaNumberPrefix+5);
		reportInvestigationsPage.clickOnFollow();
//		assertTrue(reportInvestigationsPage.verifyScreenshotWithBaseline(testCaseID, "webFoundLeak-5"));
		
		//Web view - investigate
		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		reportInvestigationsPage.investigateItem(lisaNumberPrefix+2);
		reportInvestigationsPage.clickOnPauseInvestigation();
		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		assertEquals(IndicationStatus.INPROGRESS.toString(), reportInvestigationsPage.getLisaStatus(lisaNumberPrefix+2));

		
		//Mobile view - investigate
		mobileReportsPage.open();
		mobileReportsPage.clickOnReportName(reportName);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+5, leakDetails2);
		mobileInvestigatePage.clickOnInvestigate(leakDetails2);
		mobileInvestigatePage.clickOnPauseInvestigation();

		// TODO: Mobile InPerogress - color verification: Violet
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+5);
		mobileInvestigatePage.clickOnFollow();
//		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileInprogress-5"));
		
		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		assertEquals(IndicationStatus.INPROGRESS.toString(), reportInvestigationsPage.getLisaStatus(lisaNumberPrefix+5));

		// TODO: Web Inprogress - color verification: Violet
		reportInvestigationsPage.clickOnInvestigate();
		reportInvestigationsPage.clickOnLisa(lisaNumberPrefix+5);
		reportInvestigationsPage.clickOnFollow();
//		assertTrue(reportInvestigationsPage.verifyScreenshotWithBaseline(testCaseID, "webInprogress-5"));
		
        //Delete leaks
		mobileReportsPage.open();
		mobileReportsPage.clickOnReportName(reportName);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+2, leakDetails2);
		mobileInvestigatePage.clickOnInvestigate(leakDetails2);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.deleteLeaks();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails2);

		// TODO: Mobile no leak - color verification: Blue
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+2);
		mobileInvestigatePage.clickOnFollow();
//		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "mobileNoLeak-2"));
		
		complianceReportsPageAction.open(EMPTY, reportDataRowID);
	
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationPDF(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID)));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationData(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationCSVFileDownloadToComplete(EMPTY, reportDataRowID));
		complianceReportsPageAction.clickOnCloseReportViewer(EMPTY, getReportRowID(reportDataRowID));

		// Verify deleted leaks are not showing
		assertFalse(mobileLeakSourcePage.verifyPDFLeakDetails(leakDetails2.toPDFLeakDetails(), complianceReportsPageAction.getLISAInvestigationPDFData(2,reportDataRowID)));
		assertFalse(mobileLeakSourcePage.verifyMetaLeakDetails(leakDetails2.toCSVLeakDetails(), complianceReportsPageAction.getLISAInvestigationMetaData(2,reportDataRowID)));

		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);

		// TODO: Web NoLeak - color verification: Blue
		reportInvestigationsPage.clickOnInvestigate();
		reportInvestigationsPage.clickOnLisa(lisaNumberPrefix+2);
		reportInvestigationsPage.clickOnFollow();
//		assertTrue(reportInvestigationsPage.verifyScreenshotWithBaseline(testCaseID, "webNoLeak-2"));
		
		mobileLoginPage.logout();
	}

	/**
	 * Test Case ID: TC518_GenerateInvestigationReportAsCustomerSupervisorAndAssignLISAToMultiplePeople
	 * Test Description: Generate Investigation report as customer supervisor and assign LISA for investigation to multiple people
	 * Script:
	 * -  Generate compliance report for survey having indications in it
	 * - Customer has mobile view priviledge
	 * - Login as customer supervisor
	 *  - On Home Page, navigate to Reports -> Compliance Reports page
	 *  - Click on Investigate icon present for the compliance report
	 *  - Click on few LISA checkbox and click on Assing Investigators button
	 *  - Select Assigned To name from the drop down (eg. testuser1 - driver role) and click OK
	 *  - Navigate to map view  "Assign Investigators" button
	 *  - Select multiple LISA on map
	 *  - Select Assigned To name from the drop down (eg. testuser2 role other then driver) and click OK
	 *  - Now login to application using mobile with Assigned To user credentials (eg. testuser1)
	 *  - Click on the Report Name
	 *  - Select Indication from the dropdown
	 *  - Select any one of the assigned LISAs
	 *  - Click on Directions and follow the navigation to LISA
	 *  - Click back button to return to mobile app and click on Investigate button
	 *  - Click Add Source button
	 *  - Click Add Leak button
	 *  - Fill out fields on Leak Details page and click OK
	 *  - Click Mark as Complete
	 *  - Repeat same step for second user (eg. testuser2)
	 *  - Once investigation is completed, on web view go to home page and navigate to investigation report page
	 *  - Click on Investigate PDF button present on compliance report page
	 *  - Click on Report Viewer and download Investigation Data csv file
	 * Results:
	 * -  On mobile, user name is displayed in Investigator cell present on Investigation screen table
	 *  - Logged in user having driver role should see Reports assigned to himself/herself. If logged in user is of non driver role then all the LISA present in that report are displayed. 
	 *  - By default, LISAs are sorted in descending order by amplitude value. No Gaps should appear in this list. Gaps will appear if selected from dropdown
	 *  - Satellite view shown with user's position - GPS is turned ON by default
	 *  - User should get the directions on Google Maps to navigate to selected LISA
	 *  - Add Source and Add CGI buttons will appear on the right side
	 *  - Popup will appear with buttons Add Leak and Add Other Source
	 *  - Leak Details page will appear
	 *  - List of leaks entered for that LISA will appear
	 *  - List of leaks will disappear and user will be navigated to list of assigned LISAs
	 *  - Investigation Status, Leak Found and Date columns will be updated accordingly
	 *  - User will be able to download the investigation report successfully
	 *  - Lisa number, amplitude, status, investigator, investigation Date/Time, Duration values in PDF should match UI values. There should be solid lines between one LISA and the next
	 *  - Table should not have a title at the top between LISA Investigation Marker Results and table itself
	 *  - Investigation Data csv file should have LISANumber header and value as LISA 1, LISA 2,..
	 *  - Lisa number, amplitude, status, Investigator, leak time, notes, investigation Date/Time, etc. values in CSV should match UI values
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC518, location = InvestigationReportDataProvider.class)
	public void TC518_GenerateInvestigationReportAsCustomerSupervisorAndAssignLISAToMultiplePeople(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID, Integer mobileUserDataRowID2) throws Exception {
		Log.info("\nRunning TC518_GenerateInvestigationReportAsCustomerSupervisorAndAssignLISAToMultiplePeople ..." +
			 "\nTest Description: Generate Investigation report as customer supervisor and assign LISA for investigation to multiple people");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID)); // Supervisor
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		String reportId = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportName(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());

		// Verify Lisas are numbered based on their amplitude
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		assertTrue(reportInvestigationsPage.verifyLisasOrderByAmplitude());
		
		// Assign Lisas to user
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";

		UserDataRow mobileUserDataRow2 = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID)); //Utility Admin
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID2)); // Driver
		
		reportInvestigationsPage.selectLisa(lisaNumberPrefix+4);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);
		reportInvestigationsPage.waitForPageLoad();
		reportInvestigationsPage.selectLisa(lisaNumberPrefix+2);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow2.username);
		
		// Mobile - login and investigate lisas - driver
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);

		List<String> reportIDs =	mobileReportsPage.getReports();	
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+1));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+4));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+8));
		
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+2));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+5));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+3));
		
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+6));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+7));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+9));

		// Mobile - add leak and complete
		LeakDetailEntity leakDetails = new LeakDetailEntity(mobileUserDataRow.username, 4);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+4, leakDetails);
		mobileInvestigatePage.clickOnDirections();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "directionsMap-4"));
		mobileInvestigatePage.navigateBack();
		mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+4, leakDetails);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "addSourceCGI-4"));
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddLeak();
		leakDetails.setDefaultTestData();
		mobileLeakSourcePage.addLeakDetails(leakDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails);

		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		//Reports(Mobile) are displayed in descending order by Date
		assertTrue(complianceReportsPageAction.getComplianceReportsPage().verifyReportsAreOrderedByDate(reportIDs));
		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationPDF(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID)));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationData(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationCSVFileDownloadToComplete(EMPTY, reportDataRowID));
		complianceReportsPageAction.clickOnCloseReportViewer(EMPTY, getReportRowID(reportDataRowID));

		assertTrue(mobileLeakSourcePage.verifyPDFLeakDetails(leakDetails.toPDFLeakDetails(), complianceReportsPageAction.getLISAInvestigationPDFData(4,reportDataRowID)));
		assertTrue(mobileLeakSourcePage.verifyMetaLeakDetails(leakDetails.toCSVLeakDetails(), complianceReportsPageAction.getLISAInvestigationMetaData(4,reportDataRowID)));

		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		assertEquals(IndicationStatus.FOUNDGASLEAK.toString(), reportInvestigationsPage.getLisaStatus(lisaNumberPrefix+4));

		// Mobile - login and investigate lisas - UtilityAdmin
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow2.username, mobileUserDataRow2.password);

		// UtilityAdmin can see all the lisas
		reportIDs =	mobileReportsPage.getReports();	
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+1));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+4));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+8));
		
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+2));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+5));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+3));
		
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+6));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+7));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+9));

		// Mobile - add leak and complete
		leakDetails = new LeakDetailEntity(mobileUserDataRow2.username, 2);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+2, leakDetails);
		mobileInvestigatePage.clickOnDirections();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "directionsMap-2"));
		mobileInvestigatePage.navigateBack();
		mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+2, leakDetails);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddLeak();
		leakDetails.setDefaultTestData();
		mobileLeakSourcePage.addLeakDetails(leakDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails);

		// PDF and CSV
		complianceReportsPageAction.open(EMPTY, reportDataRowID);

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationPDF(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID)));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationData(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationCSVFileDownloadToComplete(EMPTY, reportDataRowID));
		complianceReportsPageAction.clickOnCloseReportViewer(EMPTY, getReportRowID(reportDataRowID));

		assertTrue(mobileLeakSourcePage.verifyPDFLeakDetails(leakDetails.toPDFLeakDetails(), complianceReportsPageAction.getLISAInvestigationPDFData(2,reportDataRowID)));
		assertTrue(mobileLeakSourcePage.verifyMetaLeakDetails(leakDetails.toCSVLeakDetails(), complianceReportsPageAction.getLISAInvestigationMetaData(2,reportDataRowID)));

		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		assertEquals(IndicationStatus.FOUNDGASLEAK.toString(), reportInvestigationsPage.getLisaStatus(lisaNumberPrefix+2));
		
		mobileLoginPage.logout();
	}

	/**
	 * Test Case ID: TC1573_DriverUserCannotViewUnassignedLISAOnMobileView
	 * Test Description: Driver user cannot view unassigned LISA information on mobile view
	 * Script:
	 * -  Generate compliance report for survey having indications in it
	 *  - On Home Page, navigate to Reports -> Compliance Reports page
	 *  - Click on Investigate icon present for the compliance report
	 *  - Click couple of LISA check box and click on Assign Investigators button
	 *  - Select Assigned To name (Driver role) from the drop down and click OK
	 *  - Click on any one of the report
	 *  - Click on any one of the LISA
	 *  - Click on Investigate -> Add Source -> Add Other source
	 *  - Fill out leak details in popup and click OK
	 *  - Click Mark as Complete
	 *  - Log in as customer supervisor user 
	 *  - Click on above report
	 *  - Click on above investigated Lisa
	 *  - Click on Investigate button
	 * Results:
	 *  - Assigned Lisa's reports are displayed to driver user
	 *  - Only assigned Lisa are displayed to driver user (LISAs not assigned to this user are not present)
	 *  - Latest reports is displayed first (Report are sorted in descending order by date attribute)
	 *  - Lisa are displayed in descending order by amplitude value
	 *  - Lisa will be shown as Found Other Source in Lisa list screen
	 *  - Supervisor user can see above investigated Lisa as Investigation Completed - Found Other Source
	 *  - Status and other values displayed should persist
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1573, location = InvestigationReportDataProvider.class)
	public void TC1573_DriverUserCannotViewUnassignedLISAOnMobileView(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID,Integer mobileUserDataRowID2) throws Exception {
		Log.info("\nRunning TC1573_DriverUserCannotViewUnassignedLISAOnMobileView ..." +
			 "\nTest Description: Driver user cannot view unassigned LISA information on mobile view");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID)); //Utility Admin
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		String reportId = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportName(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());

		// Verify Lisas are numbered based on their amplitude
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		assertTrue(reportInvestigationsPage.verifyLisasOrderByAmplitude());
		
		// Assign Lisas to user
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";

		UserDataRow mobileUserDataRow2 = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID)); // Supervisor
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID2)); // Driver
		
		reportInvestigationsPage.selectLisa(lisaNumberPrefix+4);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);
		reportInvestigationsPage.waitForPageLoad();
		reportInvestigationsPage.selectLisa(lisaNumberPrefix+2);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow2.username);
		
		// Mobile - login and investigate lisas - driver
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);

		List<String> reportIDs =	mobileReportsPage.getReports();	
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+1));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+4));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+8));
		
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+2));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+5));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+3));
		
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+6));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+7));
		assertFalse(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+9));

		// Mobile - add other source and complete
		OtherSourceEntity sourceDetails = new OtherSourceEntity(mobileUserDataRow.username, 4);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+4, sourceDetails);
		mobileInvestigatePage.clickOnInvestigate(sourceDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddOtherSource();
		sourceDetails.setDefaultTestData();
		mobileLeakSourcePage.addOtherSource(sourceDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(sourceDetails);

		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		//Reports(Mobile) are displayed in descending order by Date
		assertTrue(complianceReportsPageAction.getComplianceReportsPage().verifyReportsAreOrderedByDate(reportIDs));
        //FoundOtherSource status is showing
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		assertEquals(IndicationStatus.FOUNDOTHERSOURCE.toString(), reportInvestigationsPage.getLisaStatus(lisaNumberPrefix+4));

		// Mobile - login and investigate lisas - UtilityAdmin
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow2.username, mobileUserDataRow2.password);

		// Supervisor can see all the lisas
		reportIDs =	mobileReportsPage.getReports();	
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+1));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+4));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+8));
		
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+2));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+5));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+3));
		
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+6));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+7));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+9));

		// Mobile - add other source and complete
		sourceDetails = new OtherSourceEntity(mobileUserDataRow2.username, 2);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+2, sourceDetails);
		mobileInvestigatePage.clickOnInvestigate(sourceDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddOtherSource();
		sourceDetails.setDefaultTestData();
		mobileLeakSourcePage.addOtherSource(sourceDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(sourceDetails);

		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		//Reports(Mobile) are displayed in descending order by Date
		assertTrue(complianceReportsPageAction.getComplianceReportsPage().verifyReportsAreOrderedByDate(reportIDs));
        //FoundOtherSource status is showing
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		assertEquals(IndicationStatus.FOUNDOTHERSOURCE.toString(), reportInvestigationsPage.getLisaStatus(lisaNumberPrefix+2));
		
		mobileLoginPage.logout();
	}

	/**
	 * Test Case ID: TC1574_UtilityAdminCanViewAssignedLISAOnMobileView
	 * Test Description: Utility Admin user can view assigned LISA information on mobile view
	 * Script:
	 * -  Customer has Classic LISA feature (LISA 1.0 feature is disabled on Manage Customer Page)
 (eg. CNP customer don't have LISA 1.0 feature)
	 *  - Log in as customer admin or supervisor user
	 *  - Generate compliance report for survey having indications in it (All check box are selected in Views section)
	 *  - On Home Page, navigate to Reports -> Compliance Reports page
	 *  - Click on Investigate icon present for the compliance report
	 *  - Click couple of LISA check box and click on Assign Investigators button
	 *  - Select Assigned To name from the drop down and click OK
	 * - Log in as customer admin user to mobile view (eg. CNP admin user)
	 *  - Click on above report
	 *  - Click on any one of the LISA
	 *  - Click on Investigate -> Add Source -> Add Leak
	 *  - Fill in leak details in popup and click OK
	 *  - Click Mark as Complete
	 * Results:
	 * - Reports associated to that customer are displayed to utility admin user
	 *  - Report are sorted in descending order by date attribute
	 *  - Lisa are displayed in descending order by amplitude value
	 *  - On map, assets intersecting Lisa are highlighted
	 *  - Lisa will be shown as Found Gas Leak on Lisa list screen
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1574, location = InvestigationReportDataProvider.class)
	public void TC1574_UtilityAdminCanViewAssignedLISAOnMobileView(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC1574_UtilityAdminCanViewAssignedLISAOnMobileView ..." +
			 "\nTest Description: Utility Admin user can view assigned LISA information on mobile view");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID)); //Utility Admin
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		String reportId = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportName(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());

		// Verify Lisas are numbered based on their amplitude
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		assertTrue(reportInvestigationsPage.verifyLisasOrderByAmplitude());
		
		// Assign Lisas to user
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";

		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID)); // Utility Admin
		
		reportInvestigationsPage.selectLisa(lisaNumberPrefix+4);
		reportInvestigationsPage.selectLisa(lisaNumberPrefix+8);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);
		// Mobile - login and investigate lisas - driver
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);

		List<String> reportIDs =	mobileReportsPage.getReports();	
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+1));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+4));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+8));
		
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+2));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+5));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+3));
		
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+6));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+7));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+9));

		// Mobile - add leak and complete
		LeakDetailEntity leakDetails = new LeakDetailEntity(mobileUserDataRow.username, 4);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+4, leakDetails);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddLeak();
		leakDetails.setDefaultTestData();
		mobileLeakSourcePage.addLeakDetails(leakDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails);

		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		//Reports(Mobile) are displayed in descending order by Date
		assertTrue(complianceReportsPageAction.getComplianceReportsPage().verifyReportsAreOrderedByDate(reportIDs));
        //FoundOtherSource status is showing
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		assertEquals(IndicationStatus.FOUNDGASLEAK.toString(), reportInvestigationsPage.getLisaStatus(lisaNumberPrefix+4));

		mobileLoginPage.logout();
	}
}