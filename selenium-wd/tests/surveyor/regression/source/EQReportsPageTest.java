/**
 *
 */
package surveyor.regression.source;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.SQAPICAD;
import static surveyor.scommon.source.SurveyorConstants.CUSTOMER_PICARRO;
import static surveyor.scommon.source.SurveyorConstants.EQDAYSURVEY;
import org.junit.Before;
import org.junit.BeforeClass;
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
		@Test/* Using Picarro admin now, and need to be changed to customer supervisor */
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
		@Test /* Using Picarro admin now, and need to be changed to customer supervisor */
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
			eqReportsPage.clickOnCopyReport(rptTitle, SQAPICAD);
			assertTrue(eqReportsPage.verifySurveyAlreadyAdded(CUSTOMER_PICARRO, EQDAYSURVEY));
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
		@Test/* Using Picarro admin now, and need to be changed to customer supervisor */
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

}