/**
 *
 */
package surveyor.regression.mobile.source;

import static org.junit.Assert.*;
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
import surveyor.scommon.mobile.source.MobileInvestigatePage;
import surveyor.scommon.mobile.source.MobileInvestigationPage;
import surveyor.scommon.mobile.source.MobileLoginPage;
import surveyor.scommon.mobile.source.MobileReportsPage;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.ReportInvestigationsPage;

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
	 * Test Case ID: TC2201_HighlightLISAAssetsSelectedInViewsSection
	 * Test Description: Compliance Report generated with Asset Boxes selected from Search Area Preference dropdown. 
	 * 						Make sure Highlight LISA Assets, LISA and Assets are selected in Views section
	 * Script:
	 * - Log in as Customer Supervisor user
	 * - Navigate to Compliance Reports page
	 * - Click on Investigate button
	 * - Select one or more LISAs by checking the checkbox(es) on the right
	 * - There are two Assign Investigators buttons near the top right. Click the leftmost of these buttons.
	 * - From the dropdown, select a user to whom to assign the LISA(s)
	 * - On a mobile device, log in as the selected user
	 * - Select the appropriate report
	 * - Select "LISA" from the dropdown
	 * - Click on a LISA
	 * - Click on Follow button
	 * Results:
	 * - User is navigated to LISA Investigations page by default. Dropdown is present at top left to select LISAs or Gaps
	 * - A dropdown menu will appear with names of users for this customer
	 * - The dropdown will disappear and the selected name will appear in the Investigator column of the selected LISA(s)
	 * - User is navigated to a page with a list of one or more reports 
	 * - User is navigated to a page with a list of one or more LISAs and a dropdown menu with selections "Indication" and "Gap". Gaps should not appear on this list unless selected from the dropdown
	 * - User is navigated to a map showing user's location
	 * - On map, assets contained within Asset Box are highlighted  in green. Asset Box is outlined in red
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2201, location = InvestigationReportDataProvider.class)
	public void TC2201_HighlightLISAAssetsSelectedInViewsSection(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC2201_HighlightLISAAssetsSelectedInViewsSection ..." +
			 "\nTest Description: Compliance Report generated with Asset Boxes selected from Search Area Preference dropdown. Make sure Highlight LISA Assets, LISA and Assets are selected in Views section");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		// Generate report
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		String reportName = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportName(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID));

		// Assign Lisas to user
		int workingLisa = 3;
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		reportInvestigationsPage.selectLisasByNumber(reportName, workingLisa);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		// Mobile - login and investigate lisas
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);

		// Mobile - add leak and complete
		mobileInvestigatePage = mobileInvestigationPage.clickOnLisa(reportName, workingLisa);
		mobileInvestigatePage.clickOnFollow();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "investigationLisaMap"));

		mobileLoginPage.logout();
	}

	/**
	 * Test Case ID: TC2202_HighlightGapAssetsSelectedInViewsSection
	 * Test Description: Search valid lisa on investigation report screen
	 * Script:
	 * - Log in as Customer Supervisor user
	 * - Navigate to Compliance Reports page
	 * - Click on Investigate button
	 * - Select Gaps from dropdown near top left
	 * - Select one or more Gaps by checking the checkbox(es) on the right
	 * - There are two Assign Investigators buttons near the top right. Click the leftmost of these buttons.
	 * - From the dropdown, select a user to whom to assign the Gap(s)
	 * - On a mobile device, log in as the selected user
	 * - Select the appropriate report
	 * - Select Gap from the dropdown
	 * - Click on a Gap
	 * - Click on Follow button
	 * Results:
	 * -  User is navigated to Gap Investigations page
	 * - A dropdown menu will appear with names of users for this customer
	 * - The dropdown will disappear and the selected name will appear in the Investigator column of the selected Gap(s)
	 * - User is navigated to a page with a list of one or more reports 
	 * - User is navigated to a page with a list of one or more LISAs and a dropdown menu with selections "Indication" and "Gap"
	 * - User is navigated to a map showing user's location
	 * - On map, assets contained within Asset Box are highlighted in green. Asset Box is outlined in red
	 */
	@Test
	@UseDataProvider(value = InvestigationReportDataProvider.INVESTIGATION_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2202, location = InvestigationReportDataProvider.class)
	public void TC2202_HighlightGapAssetsSelectedInViewsSection(
			String testCaseID, Integer userDataRowID, Integer mobileUserDataRowID, Integer reportDataRowID) throws Exception {
		Log.info("\nRunning TC2202_HighlightGapAssetsSelectedInViewsSection ..." +
			 "\nTest Description: Compliance Report generated with Asset Boxes selected from Search Area Preference dropdown. Make sure Highlight Gap Assets, Gaps and Assets are selected in Views section");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		// Generate report
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID));
		String reportName = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportName(
				ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());
		UserDataRow mobileUserDataRow = loginPageAction.getDataRow(getReportRowID(mobileUserDataRowID));
		// Assign Lisas to user
		int workingGap = 9;
		complianceReportsPageAction.clickOnInvestigateButton(EMPTY, reportDataRowID);
		reportInvestigationsPage.selectGapsByNumber(reportName, workingGap);
		reportInvestigationsPage.assignPeaks(mobileUserDataRow.username);

		// Mobile - login and investigate lisas
		mobileLoginPage.open();
		mobileReportsPage = mobileLoginPage.loginNormalAs(mobileUserDataRow.username, mobileUserDataRow.password);
		mobileInvestigationPage = mobileReportsPage.clickOnReportName(reportName);

		// Mobile - add leak and complete
		mobileInvestigatePage = mobileInvestigationPage.clickOnGap(reportName, workingGap);
		mobileInvestigatePage.clickOnFollow();
		assertTrue(mobileInvestigatePage.verifyScreenshotWithBaseline(testCaseID, "investigationGapMap"));

		mobileLoginPage.logout();
	}
}