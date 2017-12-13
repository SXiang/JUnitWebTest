/**
 *
 */
package surveyor.regression.mobile.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.CUSUSERROLEDR;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONECT;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;

import java.awt.Rectangle;

import static surveyor.scommon.source.SurveyorConstants.NOMATCHINGSEARCH;
import static surveyor.scommon.source.SurveyorConstants.REGBASEPICUSERNAME;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
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
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.ReportInvestigationsPage;
import surveyor.scommon.source.ReportInvestigationsPage.IndicationStatus;

/**
 *
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsInvestigationPageTest extends BaseReportsPageActionTest {
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
	 * Test Case ID: TC219_LisaInvestigatedAndLeakFoundStatusShouldPersistOnMobileView
	 * Test Description: Lisa Investigated and Leak found status should persist on Mobile View
	 * Script:
	 * - Generate compliance report for survey having indications in it
	 * - On Home Page, navigate to Reports -> Compliance Reports page
	 * - Click on Investigate icon present for the compliance report
	 * - Select the Lisa to assign and click on Assign Investigators button
	 * - Select Assigned To name from the drop down and click OK
	 * - Now login to application using mobile with Assigned To user credentials
	 * - Click on Report Name and any one of the assigned LISA
	 * - Click on Investigate
	 * - Click on Add Source -> Add Leak button
	 * - Fill out leak details in popup window and click OK, the click on Mark as Complete button
	 * - Select another LISA and repeat, but do not click on Mark as Complete button
	 * - Select a third LISA and click on Add Source -> Add Other Source button, fill in details and click OK, then click Mark as Complete
	 * - Click on Investigate button on Compliance Reports page
	 * Results:
	 * - First LISA should be marked as Investigated, Leak Found, with Status of Found Gas Leak
	 * - Second LISA should be marked as Investigated, Leak Found, with Status of In Progress
	 * - Third LISA should be marked as Investigated, Leak Not Found, with Status of Found Other Source
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC219, location = InvestigationReportDataProvider.class)
	public void TC219_LisaInvestigatedAndLeakFoundStatusShouldPersistOnMobileView(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC219_LisaInvestigatedAndLeakFoundStatusShouldPersistOnMobileView ..." +
			 "\nTest Description: Lisa Investigated and Leak found status should persist on Mobile View");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		String reportId = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportId(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());

		// Assign Lisas to user
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";

		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID));

		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+1);
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+2);
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+3);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		// Mobile - login and investigate lisas
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);

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

		// Mobile - add leak
		mobileReportsPage.open();
		mobileReportsPage.clickOnReportName(reportName);
		leakDetails = new LeakDetailEntity(mobileUserDataRow.username, 2);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+2, leakDetails);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddLeak();
		leakDetails.setDefaultTestData();
		mobileLeakSourcePage.addLeakDetails(leakDetails);
		mobileLeakSourcePage.closeAddSourceDialog();

		// Mobile - add other source
		mobileReportsPage.open();
		mobileReportsPage.clickOnReportName(reportName);
		OtherSourceEntity sourceDetails = new OtherSourceEntity(mobileUserDataRow.username, 3);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+3, sourceDetails);
		mobileInvestigatePage.clickOnInvestigate(sourceDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddOtherSource();
		sourceDetails.setDefaultTestData();
		mobileLeakSourcePage.addOtherSource(sourceDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(sourceDetails);

		// Verify investigation status
		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);

		assertEquals(IndicationStatus.FOUNDGASLEAK.toString(), reportInvestigationsPage.getLisaStatus(lisaNumberPrefix+1));
		assertEquals(IndicationStatus.INPROGRESS.toString(), reportInvestigationsPage.getLisaStatus(lisaNumberPrefix+2));
		assertEquals(IndicationStatus.FOUNDOTHERSOURCE.toString(),reportInvestigationsPage.getLisaStatus(lisaNumberPrefix+3));
		
		mobileLoginPage.logout();
	}

	/**
	 * Test Case ID: TC224_SearchValidLisaOnInvestigationReportScreen
	 * Test Description: Search valid lisa on investigation report screen
	 * Script:
	 * - Navigate to investigation report screen
	 * - Search the Lisa with valid CH4 value
	 * Results:
	 * -  Search result should display the lisa with that provided CH4 value
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC224, location = InvestigationReportDataProvider.class)
	public void TC224_SearchValidLisaOnInvestigationReportScreen(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC224_SearchValidLisaOnInvestigationReportScreen ..." +
			 "\nTest Description: Search valid lisa on investigation report screen");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		String reportId = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportId(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());

		// Assign Lisas to user
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";

		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		String lisaValue = reportInvestigationsPage.getLisaValue(lisaNumberPrefix+1);
		assertTrue(reportInvestigationsPage.isLisaValueSearchable(lisaValue));
	}

	/**
	 * Test Case ID: TC807_InvestigateLisaAsNewUser
	 * Test Description: Investigate Lisa as new user
	 * Script:
	 * - Generate a new driver user (eg. driveruser@picarro.com)
	 * - Generate Compliance report (eg. Test report)
	 * -  Log in to Mobile View as new user (eg. driveruser@picarro.com)
	 * - Click on "I Accept" button present on EULA screen
	 * - Log in to application as admin
	 * - Navigate to compliance report page and click on Investigate button (eg. Test report)
	 * - Assign Lisa to newly created user (eg. driveruser@picarro.com)
	 * - Log in to Mobile View as new user (eg. driveruser@picarro.com)
	 * - Click on Report (eg: Test Report) -> Lisa (eg: LISA-1)
	 * - Click Investigate -> Add Source -> Add Leak
	 * - Fill out details of leak on popup and click OK
	 * - Click on Mark as Complete button
	 * - On web view, check for the above Lisa
	 * Results:
	 *  -  User should be able to log in and accept the EULA successfully
	 * - User is navigated to report list page and should see only those reports of which the Lisa's are assigned (eg. Test report)
	 * - User can successfully investigate the Lisa and Found Gas Leak is displayed after that Lisa in mobile view
	 * - Investigation status, Leak found, Date values should be updated
	 * - All data present on mobile app should be present in PDF and csv with same details. Eg. Lisa number, amplitude, Status, Investigation Date/Time, Investigator, Duration, Source, Lat/Long, Leak details, notes, etc
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC807, location = InvestigationReportDataProvider.class)
	public void TC807_InvestigateLisaAsNewUser(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC807_InvestigateLisaAsNewUser ..." +
			 "\nTest Description: Investigate Lisa as new user");
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		// Create a new user
		String userName = getTestSetup().getFixedSizeRandomNumber(6) + REGBASEPICUSERNAME;
		ManageUsersPage	manageUsersPage = new ManageUsersPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(), manageUsersPage);
		manageUsersPage.open();
		if(!manageUsersPage.addNewPicarroUser(userName, USERPASSWORD,
				CUSUSERROLEDR, "Picarro - Default", TIMEZONECT)){
			fail(String.format("Failed to add a new picarro user %s, %s",userName, USERPASSWORD));
		}

		// Generate report
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		String reportId = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportId(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());
		// Assign Lisas to user
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";
		int workingLisa = 4;
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		String dateValue = reportInvestigationsPage.getLisaDate(lisaNumberPrefix+workingLisa);
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+workingLisa);
		reportInvestigationsPage.assignPeaks(userName);

		// Mobile - login and investigate lisas
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(userName, USERPASSWORD);
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);

		// Mobile - add leak and complete
		LeakDetailEntity leakDetails = new LeakDetailEntity(userName, workingLisa);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+workingLisa, leakDetails);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddLeak();
		leakDetails.setDefaultTestData();
		mobileLeakSourcePage.addLeakDetails(leakDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails);

		mobileLoginPage.logout();

		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationPDF(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID)));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationData(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationCSVFileDownloadToComplete(EMPTY, reportDataRowID));
		complianceReportsPageAction.clickOnCloseReportViewer(EMPTY, getReportRowID(reportDataRowID));

		assertTrue(mobileLeakSourcePage.verifyPDFLeakDetails(leakDetails.toPDFLeakDetails(), complianceReportsPageAction.getLISAInvestigationPDFData(workingLisa,reportDataRowID)));
		assertTrue(mobileLeakSourcePage.verifyMetaLeakDetails(leakDetails.toCSVLeakDetails(), complianceReportsPageAction.getLISAInvestigationMetaData(workingLisa,reportDataRowID)));

		// Verify investigation status
		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);

		assertEquals(IndicationStatus.FOUNDGASLEAK.toString(), reportInvestigationsPage.getLisaStatus(lisaNumberPrefix+workingLisa));
		assertNotEquals(dateValue, reportInvestigationsPage.getLisaDate(lisaNumberPrefix+workingLisa));
	}

	/**
	 * Test Case ID: TC1553_CheckCustomerDriverCanLogoutFromMapPageInMobileView
	 * Test Description: Check customer driver user can logout from map page in mobile view
	 * Script:
	 * - Generate report and assign LISA for investigation to customer supervisor user
	 * - Log in to mobile application as customer driver user
	 * - Click on any one of the LISA for investigation
	 * - Click on Investigate -> Add Source -> Add Leak
	 * - Fill in leak details in popup and click OK
	 * - Click Mark as Complete
	 * - Click on Log out button
	 * - Download investigation report
	 * Results:
	 * - User is navigated to map page and Logout button is present
	 * - LISA investigation status is saved
	 * - User is logged out successfully
	 * - Investigation report SSRS PDF is displaying correct LISA investigation status.  Lisa investigated: Yes  Leak Found: Yes
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1553, location = InvestigationReportDataProvider.class)
	public void TC1553_CheckCustomerDriverCanLogoutFromMapPageInMobileView(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC1553_CheckCustomerDriverCanLogoutFromMapPageInMobileView ..." +
			 "\nTest Description: Check customer driver user can logout from map page in mobile view");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		// Generate report
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		String reportId = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportId(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID));

		// Assign Lisas to user
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";
		int workingLisa = 5;
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+workingLisa);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		// Mobile - login and investigate lisas
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);

		// Mobile - add leak and complete
		LeakDetailEntity leakDetails = new LeakDetailEntity(mobileUserDataRow.username, workingLisa);
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+workingLisa, leakDetails);
		mobileInvestigatePage.clickOnInvestigate(leakDetails);
		mobileInvestigatePage.clickOnAddSource();
		mobileLeakSourcePage = mobileInvestigatePage.clickOnAddLeak();
		leakDetails.setDefaultTestData();
		mobileLeakSourcePage.addLeakDetails(leakDetails);
		mobileLeakSourcePage.closeAddSourceDialog();
		mobileInvestigatePage.clickOnMarkAsComplete(leakDetails);

		mobileLoginPage.logout();

		// Verify investigation data
		complianceReportsPageAction.open(EMPTY, reportDataRowID);
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID));
		complianceReportsPageAction.clickOnComplianceViewerInvestigationPDF(EMPTY, getReportRowID(reportDataRowID));
		assertTrue(complianceReportsPageAction.waitForInvestigationPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID)));
		/* Verification of Investigation PDF */
		assertTrue(mobileLeakSourcePage.verifyPDFLeakDetails(leakDetails.toPDFLeakDetails(), complianceReportsPageAction.getLISAInvestigationPDFData(workingLisa,reportDataRowID)));
	}

	/**
	 * Test Case ID: TC1623_CustomerUserCanSearchValidInvestigationReportOnMobileView
	 * Test Description: Customer user can search a valid investigation report on mobile view
	 * Script:
	 * - Log in as customer supervisor or admin user
	 * - Generate compliance report for survey having indications in it
	 * - On Home Page, navigate to Reports -> Compliance Reports page
	 * - Click on Investigate icon present for the compliance report
	 * - Click couple of LISA check box and click on Assign Investigators button
	 * - Select Assigned To name from the drop down and click OK
	 * - Log in as customer supervisor user to mobile view (eg. PGE supervisor user)
	 * - Type report title in search box
	 * - Click on searched report
	 * Results:
	 * - Report is searched successfully
	 * - User can see LISA associated searched report
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1623, location = InvestigationReportDataProvider.class)
	public void TC1623_CustomerUserCanSearchValidInvestigationReportOnMobileView(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC1623_CustomerUserCanSearchValidInvestigationReportOnMobileView ..." +
			 "\nTest Description: Customer user can search a valid investigation report on mobile view");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		// Generate report
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		String reportTitle = ComplianceReportsPageActions.workingDataRow.get().title;
		String reportId = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportId(
				reportTitle, TestContext.INSTANCE.getLoggedInUser());
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID));

		// Assign Lisas to user
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";
		int workingLisa = 6;
		int workingLisa1 = 7;
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+workingLisa);
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+workingLisa1);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		// Mobile - login and investigate lisas
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);

		assertTrue(mobileReportsPage.isReportTitleSearchable(reportTitle));
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+workingLisa));
		assertTrue(mobileInvestigationPage.isLisaShowing(lisaNumberPrefix+workingLisa1));

		mobileLoginPage.logout();
	}

	/**
	 * Test Case ID: TC1624_MessageToUserInvalidInvestigationReportOnMobileView
	 * Test Description: Message is displayed to user if Customer user search invalid investigation report on mobile view
	 * Script:
	 * - Log in as customer supervisor or admin user
	 * - Generate compliance report for survey having indications in it
	 * - On Home Page, navigate to Reports -> Compliance Reports page
	 * - Click on Investigate icon present for the compliance report
	 * - Click couple of LISA check box and click on Assign Investigators button
	 * - Select Assigned To name from the drop down and click OK
	 * - Log in as customer supervisor user to mobile view (eg. PGE supervisor user)
	 * - Type invalid or non-existing report title in search box (eg. zzzzakfjd)
	 * Results:
	 * -  "No matching records found" or user friendly message should be displayed to the user
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1624, location = InvestigationReportDataProvider.class)
	public void TC1624_MessageToUserInvalidInvestigationReportOnMobileView(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC1624_MessageToUserInvalidInvestigationReportOnMobileView ..." +
			 "\nTest Description: Message is displayed to user if Customer user search invalid investigation report on mobile view");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		// Generate report
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		String reportTitle = ComplianceReportsPageActions.workingDataRow.get().title;
		String reportId = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportId(
				reportTitle, TestContext.INSTANCE.getLoggedInUser());
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID));

		// Assign Lisas to user
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";
		int workingLisa = 6;
		int workingLisa1 = 7;
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+workingLisa);
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+workingLisa1);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		// Mobile - login and investigate lisas
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);

		assertEquals(NOMATCHINGSEARCH, mobileReportsPage.performSearch(reportTitle+"InvalidTitle"));

		mobileLoginPage.logout();
	}

	/**
	 * Test Case ID: TC1628_MobileViewClassicLISAshape
	 * Test Description: Mobile View - Classic LISA shape
	 * - Customer should have assets and assets should be intersected with LISAs
	 * - Compliance Report that contains LISAs for customer that does not have LISA Box 1.0 enabled. Make sure Highlight LISA Assets, LISA and Assets are selected in Views section.
	 * Script:
	 * - Log in as Customer Supervisor user
	 * - Navigate to Compliance Reports page
	 * - Click on Investigate button
	 * - Select one or more LISAs by checking the checkbox(es) on the right
	 * - There are two Assign Investigators buttons near the top right. Click the leftmost of these buttons.
	 * - From the dropdown, select a user to whom to assign the LISA(s)
	 * - On a mobile device, log in as the selected user
	 * - Select the appropriate report
	 * - Select Indication from the dropdown
	 * - Click on a LISA
	 * - Click on Follow button
	 * Results:
	 * -  User is navigated to LISA Investigations page
	 * - A drop-down menu will appear with names of users for this customer
	 * - The drop-down will disappear and the selected name will appear in the Investigator column of the selected LISA(s)
	 * - User is navigated to a page with a list of one or more reports
	 * - User is navigated to a page with a list of one or more LISAs and a drop-down menu with selections "LISA" and "Gap"
	 * - User is navigated to a map showing user's location
	 * - On map, assets intersecting Classic LISAs are highlighted
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1628, location = InvestigationReportDataProvider.class)
	public void TC1628_MobileViewClassicLISAshape(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC1628_MobileViewClassicLISAshape ..." +
			 "\nTest Description: Mobile View - Classic LISA shape");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		// Generate report
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		String reportId = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportId(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID));

		// Assign Lisas to user
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";
		int workingLisa = 8;
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+workingLisa);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		// Mobile - login and investigate lisas
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);

		// Mobile - add leak and complete
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+workingLisa);
		mobileInvestigatePage.clickOnFollow();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "investigationMap", new Rectangle(420, 700, 0, 0)));

		mobileLoginPage.logout();
	}

	/**
	 * Test Case ID: TC1629_MobileViewWithLISABoxShape
	 * Test Description: Mobile View - LISA Box shape
	 * Pre-Conditions:
	 * - Customer should have assets and assets should be intersected with LISAs
	 * - Compliance Report that contains LISAs for customer that has LISA Box 1.0 enabled. Make sure LISA, Assets and Highlight LISA Assets are selected in report's view section
	 * Script:
	 * - Log in as Customer Supervisor user
	 * - Navigate to Compliance Reports page
	 * - Click on Investigate button
	 * - Select one or more LISAs by checking the checkbox(es) on the right
	 * - There are two Assign Investigators buttons near the top right. Click the leftmost of these buttons.
	 * - From the dropdown, select a user to whom to assign the LISA(s)
	 * - On a mobile device, log in as the selected user
	 * - Select the appropriate report
	 * - Click on a LISA
	 * - Click on Follow button
	 * Results:
	 * - User is navigated to LISA Investigations page
	 * - A dropdown menu will appear with names of users for this customer
	 * - The dropdown will disappear and the selected name will appear in the Investigator column of the selected LISA(s)
	 * - User is navigated to a page with a list of one or more reports
	 * - User is navigated to a page with a list of one or more LISAs
	 * - User is navigated to a map showing user's location
	 * - On map, LISA Boxes are displayed and assets intersecting Lisa are highlighted
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1629, location = InvestigationReportDataProvider.class)
	public void TC1629_MobileViewWithLISABoxShape(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC1629_MobileViewWithLISABoxShape ..." +
			 "\nTest Description: Mobile View - LISA Box shape");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		// Generate report
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		String reportId = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportId(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID));

		// Assign Lisas to user
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";
		int workingLisa = 1;
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		reportInvestigationsPage.selectLisas(lisaNumberPrefix+workingLisa);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		// Mobile - login and investigate lisas
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);

		// Mobile - add leak and complete
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(lisaNumberPrefix+workingLisa);
		mobileInvestigatePage.clickOnFollow();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "investigationMap",new Rectangle(0,750,0,0)));
		mobileLoginPage.logout();
	}

}