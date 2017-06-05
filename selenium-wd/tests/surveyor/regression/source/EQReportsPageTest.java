/**
 *
 */
package surveyor.regression.source;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.SQACUS;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
import static surveyor.scommon.source.SurveyorConstants.CUSTOMER_SQACUS;
import static surveyor.scommon.source.SurveyorConstants.EQDAYSURVEY;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.Log;
import surveyor.scommon.source.EQReportsPage;
import surveyor.dataprovider.EQReportDataProvider;
import surveyor.scommon.actions.EQReportsPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.SurveyorTestRunner;

/**
 *
 *
 */
@RunWith(SurveyorTestRunner.class)
public class EQReportsPageTest extends BaseReportsPageActionTest {

	private static LoginPageActions loginPageAction;
	private static EQReportsPageActions eqReportsPageAction;
	private static EQReportsPage eqReportsPage;

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
	}

	private static void setPropertiesForTestRunMode() throws Exception {
		setTestRunMode(ReportTestRunMode.FullTestRun);

		if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			eqReportsPageAction.fillWorkingDataForReports(getUnitTestReportRowID());
		}
	}

	/**
	 * Initializes the page action objects.
	 * @throws Exception
	 */
	protected static void initializePageActions() throws Exception {
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
		eqReportsPageAction = new EQReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
		eqReportsPage = (EQReportsPage)eqReportsPageAction.getPageObject();
		setReportsPage(eqReportsPage);
	}

	/**
	 * Test Case ID: TC561_EQReportPagination
	 * Test Description: Pagination - EQ Report - Picarro Admin user
	 * Script:
	 *	- Login as Picarro Admin user
	 *	- On Home Page, click Reports -> EQ 
	 *	- 10,25,50 and 100 Reports selection on EQ report screen
	 * Results:
	 *	- Selected number of reports will be listed in the table
	 */
	@Test
	@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC561, location = EQReportDataProvider.class)
	public void TC561_EQReportPagination(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC561_EQReportPagination ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		eqReportsPageAction.open(EMPTY, NOTSET);

		// Create some reports to ensure data table gets populated over multiple executions of the test.
		createMultipleReports(eqReportsPageAction, reportDataRowID1, 3 /*numReportsToCreate*/);

		String paginationSetting25 = "25";
		String paginationSetting50 = "50";
		String paginationSetting100 = "100";

		assertTrue(eqReportsPage.checkPaginationSetting(PAGINATIONSETTING));
		assertTrue(!(eqReportsPage.getNumberofRecords() > Integer.parseInt(PAGINATIONSETTING)));
		assertTrue(eqReportsPage.checkPaginationSetting(paginationSetting25));
		assertTrue(!(eqReportsPage.getNumberofRecords() > Integer.parseInt(paginationSetting25)));
		assertTrue(eqReportsPage.checkPaginationSetting(paginationSetting50));
		assertTrue(!(eqReportsPage.getNumberofRecords() > Integer.parseInt(paginationSetting50)));
		assertTrue(eqReportsPage.checkPaginationSetting(paginationSetting100));
		assertTrue(!(eqReportsPage.getNumberofRecords() > Integer.parseInt(paginationSetting100)));
	}

	/**
	 * Test Case ID: TC562_EQReportSortOnAttributes
	 * Test Description: Picarro Admin user - Sort EQ Report list based on Title, Created by, date and other attibutes if any
	 * Script:
	 *	- Login as Picarro Admin user
	 *	- On Home Page, click Reports -> EQ 
	 *	- Sort report list by report title or created by or date attributes
	 * Results:
	 *	- User is able to sort the list of reports based on selected attribute
	 */
	@Test
	@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC562, location = EQReportDataProvider.class)
	public void TC562_EQReportSortOnAttributes(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC562_EQReportSortOnAttributes ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

		// Create some reports to ensure data table gets populated over multiple executions of the test.
		createMultipleReports(eqReportsPageAction, reportDataRowID1, 3 /*numReportsToCreate*/);

		eqReportsPageAction.open(EMPTY, NOTSET);

		assertTrue(eqReportsPage.isReportColumnSorted("Report Title","String"));

		eqReportsPageAction.open(EMPTY, NOTSET);
		assertTrue(eqReportsPage.isReportColumnSorted("Created By","String"));

		eqReportsPageAction.open(EMPTY, NOTSET);
		assertTrue(eqReportsPage.isReportColumnSorted("Date","Date"));

		eqReportsPageAction.open(EMPTY, NOTSET);
		assertFalse(eqReportsPage.isReportColumnSorted("Report Name","String"));
	}

	/**
	 * Test Case ID: TC566_CancelButtonForNewAndCopyEQReport
	 * Test Description: check Cancel button present on new and copy EQ report screens
	 * Script:
	 *	- Login as Customer Supervisor user
	 *	- On Home Page, click Reports -> EQ -> 'New EQ Report' button
	 *	- Provide report title
	 *	- Click on Cancel button
	 *	- Click on 'Copy EQ Report' button
	 *	- Click on Cancel button
	 * Results:
	 *	- Report is not generated and user is navigated back to EQ report list page
	 */
	@Test
	@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC566, location = EQReportDataProvider.class)
	public void TC566_CancelButtonForNewAndCopyEQReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC566_CancelButtonForNewAndCopyEQReport ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		eqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(eqReportsPageAction, getReportRowID(reportDataRowID1));
		eqReportsPageAction.verifyPageLoaded(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(eqReportsPageAction.cancelInProgressReport(EMPTY, getReportRowID(reportDataRowID1)));

		eqReportsPage.waitForCopyReportPagetoLoad();
		eqReportsPageAction.copyReport(EQReportsPageActions.workingDataRow.get().title, getReportRowID(reportDataRowID1));
		eqReportsPage.waitForCopyReportPagetoLoad();
		eqReportsPageAction.clickOnOKButton(EQReportsPageActions.workingDataRow.get().title, getReportRowID(reportDataRowID1));
		eqReportsPageAction.verifyPageLoaded(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(eqReportsPageAction.cancelInProgressReport(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC651_AlreadyAddedMessageForNewEQReport
	 * Test Description: verify "Already Added" message is displayed if user tries to add the same survey again on New EQ report survey selector section
	 * Script:
	 *	- Login as Customer Supervisor user
	 *	- On Home Page, click Reports -> EQ -> 'New EQ Report' button
	 *	- Search the Survey and add one of the survey
	 *	- Now try to add the same survey again
	 *	- Delete the survey and try to add the same survey
	 * Results:
	 *	- Only EQ customer's surveys should be present in the searched list
	 *	- "Already Added" message is displayed on the search button
	 *	- User is able to add the survey
	 */
	@Test
	@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC651, location = EQReportDataProvider.class)
	public void TC651_AlreadyAddedMessageForNewEQReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC651_AlreadyAddedMessageForNewEQReport ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		eqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(eqReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(eqReportsPageAction, getReportRowID(reportDataRowID1));
		String rptTitle = EQReportsPageActions.workingDataRow.get().title;
		eqReportsPage.clickOnCopyReport(rptTitle, SQACUSSU);
		assertTrue(eqReportsPage.verifySurveyAlreadyAdded(CUSTOMER_SQACUS, EQDAYSURVEY));
	}

	/**
	 * Test Case ID: TC655_EQSurveySelectorPagination
	 * Test Description: Pagination - EQ Survey Selector Section
	 * Script:
	 *	- Login as Customer Supervisor user
	 *	- On Home Page, click Reports -> EQ -> 'New EQ Report' button
	 *	- Click on Search button
	 *	- 10,25,50 and 100 records per page in survey selector section
	 * Results:
	 *	- Selected number of records will be listed in the table
	 */
	@Test
	@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC655, location = EQReportDataProvider.class)
	public void TC655_EQSurveySelectorPagination(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC655_EQSurveySelectorPagination ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		eqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		eqReportsPage.openNewReportPage();
		eqReportsPage.clickOnSearchSurveyButton();

		String paginationSetting25 = "25";
		String paginationSetting50 = "50";
		String paginationSetting100 = "100";

		eqReportsPage.setSurveyRowsPagination(PAGINATIONSETTING);
		assertTrue(!(eqReportsPage.getNumberofSurveyRecords() > Integer.parseInt(PAGINATIONSETTING)));
		eqReportsPage.setSurveyRowsPagination(paginationSetting25);
		assertTrue(!(eqReportsPage.getNumberofSurveyRecords() > Integer.parseInt(paginationSetting25)));
		eqReportsPage.setSurveyRowsPagination(paginationSetting50);
		assertTrue(!(eqReportsPage.getNumberofSurveyRecords() > Integer.parseInt(paginationSetting50)));
		eqReportsPage.setSurveyRowsPagination(paginationSetting100);
		assertTrue(!(eqReportsPage.getNumberofSurveyRecords() > Integer.parseInt(paginationSetting100)));
	}		

	/**
	 * Test Case ID: TC2419_MobileEQReportsWithAnalyticsSurveys
	 * Test Description: Mobile EQ reports can be run with Analytics surveys
	 * Script:
	 * - Log into the UI
	 * - Navigate to the EQ Reports page (NOT Facility EQ Reports page)
	 * - Click on New EQ Report
	 * - Enter a Report Title, select EQ Location Parameter, select line segments, add at least one EQ survey and one Analytics survey and click OK
	 * - Download the EQ Table PDF and EQ View PDF
	 * Results:
	 * - In the Survey Selector section of the New EQ Report page, the Survey Mode Filter should include EQ, Standard and Analytics (depending on licensing, Rapid Response, Operator and Manual may also be among the choices)
	 * - The report should generate successfully
	 * - The EQ Table PDF should have the correct surveys listed along with line segments and indications (if any)
	 * - The EQ View PDF should show the correct line segments as drawn by the user
	 */
	@Test
	@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2419, location = EQReportDataProvider.class)
	public void TC2419_MobileEQReportsWithAnalyticsSurveys(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2419_MobileEQReportsWithAnalyticsSurveys ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		eqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));

		eqReportsPage.openNewReportPage();
		assertTrue(eqReportsPage.isAllSurveyModeShown());
		assertTrue(eqReportsPage.isManualSurveyModeShown());
		assertTrue(eqReportsPage.isStandardSurveyModeShown());
		assertTrue(eqReportsPage.isOperatorSurveyModeShown());
		assertTrue(eqReportsPage.isAnalyticsSurveyModeShown());
		assertTrue(eqReportsPage.isRapidResponseSurveyModeShown());
		assertTrue(eqReportsPage.isEQSurveyModeShown());
		eqReportsPageAction.fillAndCreateNewReport(getReportRowID(reportDataRowID1),false);
		eqReportsPageAction.waitForReportGenerationToComplete(EMPTY,  getReportRowID(reportDataRowID1));
		eqReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		eqReportsPageAction.clickOnViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(eqReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
		eqReportsPageAction.clickOnReportViewerView(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(eqReportsPageAction.waitForViewDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(eqReportsPageAction.verifyViewsImagesWithBaselines("FALSE", getReportRowID(reportDataRowID1)));
		assertTrue(eqReportsPageAction.verifyAllSSRSTableInfos(EMPTY, reportDataRowID1));
		eqReportsPageAction.clickOnCloseReportViewer(EMPTY, getReportRowID(reportDataRowID1));
	}

	/**
	 * Test Case ID: TC2407_MobileEQReportsWithSurveyWithNoPeaks
	 * Test Description: Generate EQ report with the survey with no peaks
	 * Script:
	 * - Customer should have EQ license
	 * - Customer should have EQ survey with no peaks.
	 * - login to pcube
	 * - Generate EQ report including above mentioned survey (survey with no peaks)
	 * Results:
	 * - Report should generate successfully.
	 */
	@Test
	@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2407, location = EQReportDataProvider.class)
	public void TC2407_MobileEQReportsWithSurveyWithNoPeaks(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2407_MobileEQReportsWithSurveyWithNoPeaks ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		eqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));

		eqReportsPage.openNewReportPage();
		eqReportsPageAction.fillAndCreateNewReport(getReportRowID(reportDataRowID1),false);
		eqReportsPageAction.waitForReportGenerationToComplete(EMPTY,  getReportRowID(reportDataRowID1));
		eqReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		eqReportsPageAction.clickOnViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(eqReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC2409_GenerateEQReportWithLocationParameterMinClusterSize1AndDBScanUncheck
	 * Test Description: Generate EQ report with the location parameter which has Min cluster size 1 and DBScan uncheck
	 * Script:
	 * - Customer should have EQ license
	 * - Customer should have EQ survey with peaks.
	 * - login to pcube
	 * - Go to Manage Location and create new location for that customer where Min cluster size = 1 and DBScan uncheck
	 * -Generate EQ report using above created location parameter and include survey which has indications.
	 * -Reprocess the same report for (min-2 and max-6 times -depends on howmany linux nodes are there in the environment) 
	 * 	 * Results:
	 * -Report should faild, but EQWorker should still be alive and user should generate other EQ report.
	 */
	@Ignore  // Complete test case implementation once US4475 completes.  Enable the test case once US4403 gets fixed.
	@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2409, location = EQReportDataProvider.class)
	public void TC2409_GenerateEQReportWithLocationParameterMinClusterSize1AndDBScanUncheck(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2409_GenerateEQReportWithLocationParameterMinClusterSize1AndDBScanUncheck ...");

		
		// Implementation left for "Go to Manage Location and create new location for that customer where Min cluster size = 1 and DBScan uncheck"
		 
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		eqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));

		eqReportsPage.openNewReportPage();
		eqReportsPageAction.fillAndCreateNewReport(getReportRowID(reportDataRowID1),false);
		assertFalse(eqReportsPageAction.waitForReportGenerationToComplete(EMPTY,  getReportRowID(reportDataRowID1)));
		
		for (int i=0; i <4; i++)
		{
			eqReportsPageAction.clickOnResubmitButton(EMPTY, getReportRowID(reportDataRowID1));
			assertFalse(eqReportsPageAction.waitForReportGenerationToComplete(EMPTY,  getReportRowID(reportDataRowID1)));					
		}
		
	}
}