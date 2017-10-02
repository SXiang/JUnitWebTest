/**
 *
 */
package surveyor.regression.mobile.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.CUSUSERROLEDR;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONECT;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;
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
public class ComplianceReportsInvestigationPageTest_Tahoe extends BaseReportsPageActionTest {
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
	 * Test Case ID: TC2201_LisaInvestigatedAndLeakFoundStatusShouldPersistOnMobileView
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
		String reportId = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportName(
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
	 * Test Case ID: TC2202_SearchValidLisaOnInvestigationReportScreen
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
		String reportId = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportName(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());

		// Assign Lisas to user
		String reportName = "CR-"+reportId.substring(0,6).toUpperCase();
		String lisaNumberPrefix = reportName+"-LISA-";

		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		String lisaValue = reportInvestigationsPage.getLisaValue(lisaNumberPrefix+1);
		assertTrue(reportInvestigationsPage.isLisaValueSearchable(lisaValue));
	}
}