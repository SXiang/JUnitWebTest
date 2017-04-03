/**
 *
 */
package surveyor.regression.source;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
import static surveyor.scommon.source.SurveyorConstants.CUSTOMER_SQACUS;
import static surveyor.scommon.source.SurveyorConstants.EQDAYSURVEY;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.Log;
import surveyor.scommon.source.EQReportsPage;
import surveyor.scommon.source.ReportsCommonPage;
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
public class EQReportsPageTest2 extends BaseReportsPageActionTest {

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
		 * Test Case ID: TC532_GenerateEQReportAsCustomerSupervisorWhenOverlappingMultiSegments
		 * Test Description: Generate EQ Report as Customer supervisor user when overlapping multi segments selected in survey area, all filters are selected to search and include the surveys
		 * Script:
		 *	 Login as Customer Supervisor user
		 * - On Home Page, click Reports -> EQ -> 'New EQ Report' button
		 * -  Provide report title and select time zone : Eastern time
		 * - Click on "Choose Area Selector" button, select multiple overlapping line segments in area where more than 6 surveys with 2 passes are present and click OK
		 * - Search the Survey by selecting all the filter values and Add 6 or more survey
		 * - Click OK
		 * - Click on "EQ Viewer" -> PDF button to download the tabular report
		 * - Click on Map button to download the report map view
		 * Results:
		 *	- Selected number of line segments are displayed
		 * - Only EQ surveys should be present in the searched list
		 * - Report title entry is present on EQ report list screen and report is generated successfully
		 * - PDF and Map thumbnails are present and user can download them
		 * - PDF will have Emission Ranking table with list of Pipe Segment IDs, Emissions ranked highest to lowest, emission rate,emission range, segment length, emission factor, estimated # of leaks, # leaks / ft, Emission Rate / Leak *NOTE - there should be no Fractional Uncertainty column
		 * - Map View should display the selected line segments with numbers.
		 */
		@Test /* Need EQ records in PDF and verification */
		@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC532, location = EQReportDataProvider.class)
		public void TC532_GenerateEQReportAsCustomerSupervisorWhenOverlappingMultiSegments(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
			Log.info("\nRunning TC532_GenerateEQReportAsCustomerSupervisorWhenOverlappingMultiSegments ...");

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
			eqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
			createNewReport(eqReportsPageAction, getReportRowID(reportDataRowID1));
			eqReportsPageAction.waitForReportGenerationToComplete(EMPTY,  getReportRowID(reportDataRowID1));
			eqReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
			eqReportsPageAction.clickOnViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
			assertTrue(eqReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
			eqReportsPageAction.clickOnReportViewerView(EMPTY, getReportRowID(reportDataRowID1));			
			assertTrue(eqReportsPageAction.waitForViewDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
			assertTrue(eqReportsPageAction.verifyViewsImagesWithBaselines("FALSE", getReportRowID(reportDataRowID1)));
//			assertTrue(eqReportsPageAction.verifyAllSSRSTableInfos(EMPTY, reportDataRowID1));
//			eqReportsPageAction.clickOnCloseReportViewer(EMPTY, getReportRowID(reportDataRowID1));
		}

		/**
		 * Test Case ID: TC535_DeleteEQReportAsCustomerSupervisor
		 * Test Description: Delete EQ Report as Customer Supervisor user
		 * Script:
		 *	- Login as Customer Supervisor user
		 * - On Home Page, click Reports -> EQ -> 'Delete EQ Report' button
		 * - Click OK
		 * Results:
		 *	- Warning should be present : “do you really want to delete report title”
		 * - Deleted report is not present on EQ report list screen
		 * - Report title, customer id, survey details, View Content details are not present in Report, ReportEQ and ReportEQArea tables (Check attached db schema in US850 for more detials)
		 */
		@Test
		@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC535, location = EQReportDataProvider.class)
		public void TC535_DeleteEQReportAsCustomerSupervisor(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
			Log.info("\nRunning TC535_DeleteEQReportAsCustomerSupervisor ...");

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
		 * Test Case ID: TC537_GenerateEQReportAsCustomerAdminWhenOverlappingMultiSegments
		 * Test Description: Generate EQ Report as Customer Admin user when multiple line segments are selected, geographic filter is used to search and include the surveys
		 * Script:
		 *	- Login as Customer Admin user
		 * - On Home Page, click Reports -> EQ -> 'New EQ Report' button
		 * -  Provide report title and select time zone : CST
		 * - Click on "Choose Area Selector" button, draw multiple (more than 10 line segments) line segments same area where more than 6 surveys with 2 passes and click OK
		 * - Search the Survey by selecting geographic filter and Add the survey
		 * - Click OK
		 * - Click on "EQ Viewer" -> PDF button to download the report
		 * - Click on Map button to download the report
		 * Results:
		 *	- Selected number of line segments are displayed
		 * - EQ surveys present in the geographic area should be present in the searched list 
		 * - Survey search should show User, Surveyor,Start Date,End date,Type ,Tag and Selected columns with correct data.
		 * - Report title entry is present on EQ report list screen and report is generated successfully
		 * - PDF and Map thumbnails are present and user can download them
		 * - PDF will have Emission Quantification Data table with list of Pipe Segment IDs, Emissions ranked highest to lowest, emission rate, emission range, segment length, emission factor, estimated # of leaks, # leaks / ft, Emission Rate / Leak
		 * - PDF and map view line segment IDs should not shuffle and correct data should be present in emission table
		 * - PDF should have Selected Driving surveys with Start and End date/time,Duration,Username,Surveyor,Analyzer,Tag and Stability class info.
		 * - Map should display the selected line segments with numbers
		 * - Validate report creation date, date printed, Survey Start/End time present in SSRS PDF is as expected
		 */
		@Test
		@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC537, location = EQReportDataProvider.class)
		public void TC537_GenerateEQReportAsCustomerAdminWhenOverlappingMultiSegments(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
			Log.info("\nRunning TC537_GenerateEQReportAsCustomerAdminWhenOverlappingMultiSegments ...");

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
		 * Test Case ID: TC542_DeleteEQReportAsPicarroSupportUser
		 * Test Description: Delete EQ Report as Picarro Support user
		 * Script:
		 *	-  Login as Picarro Support user
		 * - On Home Page, click Reports -> EQ -> 'Delete EQ Report' button
		 * - Click OK
		 * Results:
		 *	- Warning should be present : “do you really want to delete report title”
		 * - Deleted report is not present on EQ report list screen
		 * - Report title, customer id, survey details, View Content details are not present in Report, ReportEQ and ReportEQArea tables (Check attached db schema in US850 for more detials)
		 */
		@Test
		@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC542, location = EQReportDataProvider.class)
		public void TC542_DeleteEQReportAsPicarroSupportUser(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
			Log.info("\nRunning TC542_DeleteEQReportAsPicarroSupportUser ...");

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
		 * Test Case ID: TC544_GenerateEQReportAsPicarroAdminWhenMultipleLinesAllFilters
		 * Test Description: Pagination - Generate EQ Report as Picarro Admin user when multiple line segements are selected, all filters are selected to search and include the picarro surveys
		 * Script:
		 *	- Login as Picarro Admin user
		 * - On Home Page, click Reports -> EQ -> 'New EQ Report' button
		 * -  Provide report title, select Customer as "Picarro" and select time zone : PST
		 * - Click on "Choose Area Selector" button, select multiple line segments on different area/drives and click OK
		 * - Search the Survey by selecting all the filter values and Add the survey
		 * - Click OK
		 * - Click on "EQ Viewer" -> PDF button to download the report
		 * - Click on Map button to download the report
		 * Results:
		 *	- Selected number of line segments are displayed, "n line(s) selected"
		 * - EQ surveys should be present in the searched list
		 * - Report title entry is present on EQ report list screen and report is generated successfully
		 * - PDF and Map thumbnails are present and user can download them
		 * - PDF will have Emission Ranking table with list of Pipe Segment IDs, Emissions ranked highest to lowest, emission rate, emission range, segment length, emission factor, estimated # of leaks, # leaks / ft, Emission Rate / Leak
		 * - Map View should display the selected line segments with numbers
		 */
		@Test
		@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC544, location = EQReportDataProvider.class)
		public void TC544_GenerateEQReportAsPicarroAdminWhenMultipleLinesAllFilters(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
			Log.info("\nRunning TC544_GenerateEQReportAsPicarroAdminWhenMultipleLinesAllFilters ...");

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
		 * Test Case ID: TC554_GenerateEQReportAsPicarroSupportWhenMultiLinesPartiallyOutsideSurveyArea
		 * Test Description: Generate EQ Report as Picarro Support user when multiple line segments, partially outside survey area are selected and include customer surveys
		 * Script:
		 * - Login as Picarro Support user
		 * - On Home Page, click Reports -> EQ -> 'New EQ Report' button
		 * -  Provide report title, select non Picarro Customer and select time zone : PST
		 * - Click on "Choose Area Selector" button,  select multiple line segments present few outside area and few withing survey area and click OK
		 * - Search the Survey and add them
		 * - Click OK
		 * - Click on "EQ Viewer" -> PDF button to download the report
		 * - Click on Map button to download the report
		 * Results:
		 *	- Report title entry is present on EQ report list screen and report is generated successfully
		 * - PDF and Map thumbnails are present and user can download them
		 * - PDF will have Emission Ranking table with list of Pipe Segment IDs, Emissions ranked highest to lowest, emission rate, emission range, segment length, emission factor, estimated # of leaks, # leaks / ft, Emission Rate / Leak
OR "No EQ Records present"
		 * - Map should display the selected line segments with numbers
		 */
		@Test
		@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC554, location = EQReportDataProvider.class)
		public void TC554_GenerateEQReportAsPicarroSupportWhenMultiLinesPartiallyOutsideSurveyArea(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
			Log.info("\nRunning TC554_GenerateEQReportAsPicarroSupportWhenMultiLinesPartiallyOutsideSurveyArea ...");

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
		 * Test Case ID: TC556_EQReportCanIncludeEQSurveysAndSurveyorSurveys
		 * Test Description: EQ Report can include EQ surveys and surveyor surveys
		 * Script:
		 *	- Login as Customer Supervisor or Picarro Admin user
		 * - On Home Page, click Reports -> EQ -> 'New EQ Report' button
		 * - Search the Survey
		 * Results:
		 *	-  EQ customer's surveys should be present in the searched list along with surveyor surveys.
		 */
		@Test
		@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC556, location = EQReportDataProvider.class)
		public void TC556_EQReportCanIncludeEQSurveysAndSurveyorSurveys(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
			Log.info("\nRunning TC556_EQReportCanIncludeEQSurveysAndSurveyorSurveys ...");

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
		 * Test Case ID: TC557_ComplianceReportSurveySearchGridShouldNotDisplayEQModeSurvey
		 * Test Description: Compliance Report survey search grid should not display EQ mode surveys
		 * Script:
		 *	- Login as Customer Supervisor user
		 * - On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
		 * - Search the Survey
		 * Results:
		 *- EQ surveys should not be displayed in searched survey grid
		 */
		@Test
		@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC557, location = EQReportDataProvider.class)
		public void TC557_ComplianceReportSurveySearchGridShouldNotDisplayEQModeSurvey(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
			Log.info("\nRunning TC557_ComplianceReportSurveySearchGridShouldNotDisplayEQModeSurvey ...");

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
		 * Test Case ID: TC558_EQReportIsNotAccessibleWithoutEQPrivilege
		 * Test Description: EQ Report page is not accessible if customer don’t have EQ privilege - Customer Supervisor user
		 * Script:
		 *	- Login as Customer1's Supervisor user
		 * - Click on Reports (left side menu)
		 * - Click on EQ and generate the EQ report
		 * - Login as Customer2's Supervisor user
		 * - Click on Reports (left side menu)
		 * Results:
		 *	- EQ Report option is present
		 * - User can generate EQ report successfully
		 * - EQ report link is not present for Customer2's user and user cannot access EQ report page
		 */
		@Test
		@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC558, location = EQReportDataProvider.class)
		public void TC558_EQReportIsNotAccessibleWithoutEQPrivilege(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
			Log.info("\nRunning TC558_EQReportIsNotAccessibleWithoutEQPrivilege ...");

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
		 * Test Case ID: TC559_EQReportCannotBeGeneratedUnlessAllRequiredFieldsAreFilled
		 * Test Description: Check that EQ report cannot be generated unless all required fields are filled out
		 * Script:
		 * - Login as Picarro Admin user
		 * - On Home Page, click Reports -> EQ -> 'New EQ Report' button
		 * - Provide report title, select Customer as "Picarro" and select time zone : PST
		 * - Click on "Choose Area Selector" button, select multiple line segments on different area/drives and click OK
		 * - Search the Survey by selecting all the filter values and Add the survey
		 * - Click OK
		 * - Click on "EQ Viewer" -> PDF button to download the report
		 * - Click on Map button to download the report
		 * Results:
		 *	- Selected number of line segments are displayed, "n line(s) selected"
		 * - EQ surveys should be present in the searched list
		 * - Report title entry is present on EQ report list screen and report is generated successfully
		 * - PDF and Map thumbnails are present and user can download them
		 * - PDF will have Emission Ranking table with list of Pipe Segment IDs, Emissions ranked highest to lowest, emission rate, emission range, segment length, emission factor, estimated # of leaks, # leaks / ft, Emission Rate / Leak
		 * - Map View should display the selected line segments with numbers
		 */
		@Test
		@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC559, location = EQReportDataProvider.class)
		public void TC559_EQReportCannotBeGeneratedUnlessAllRequiredFieldsAreFilled(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
			Log.info("\nRunning TC559_EQReportCannotBeGeneratedUnlessAllRequiredFieldsAreFilled ...");

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
//		assertTrue(assessmentReportsPageAction.verifyViewsImagesWithBaselines("FALSE", getReportRowID(reportDataRowID1)));
//
//		assertTrue(assessmentReportsPageAction.verifySSRSImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
//
//		// Shape files should be present for FOV, LISA, GAP, BreadCrumb, PipeAll, PipesIntersectingLISA and PipesIntersectionGAP
//		assertTrue(assessmentReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
//
//		// (PDF should have survey details, view details and assets)
//		assertTrue(assessmentReportsPageAction.verifySSRSDrivingSurveyTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
//		assertTrue(assessmentReportsPageAction.verifySSRSViewsTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
//		assertTrue(assessmentReportsPageAction.verifySSRSLayersTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
//
//		//(Report creation date, date printed, Survey Start/End time details displayed in SSRS PDF is as expected)
//		// date printed -> verified in Footer verification.
//		// survey start/end date -> verified in verifySSRSDrivingSurveyTableInfo
//		assertTrue(assessmentReportsPageAction.verifyReportCreationInSSRSPDFIsCorrect(EMPTY, getReportRowID(reportDataRowID1)));
//		assertTrue(assessmentReportsPageAction.verifySSRSPDFFooter(EMPTY, getReportRowID(reportDataRowID1)));
//
//		// SSRS should not have anything related to Indications, LISA, Isotopic Analysis, Field Notes, Report Mode etc
//		String notContainsText = "Indications:LISA:Isotopic Analysis:Field Notes:Report Mode";
//		// LISA, CGI and Gap Investigation Complete labels should not be present on first page of PDF
//		notContainsText += String.format("%s:%s:%s", ReportsCommonPage.ComplianceReportSSRS_LISAInvestigationComplete,
//				ReportsCommonPage.ComplianceReportSSRS_GAPInvestigationComplete, ReportsCommonPage.ComplianceReportSSRS_CGIInvestigationComplete);
//
//		assertTrue(assessmentReportsPageAction.verifyPDFDoesNotContainInputtedInformation(notContainsText, getReportRowID(reportDataRowID1)));
//
//		// Download report in zipped folder which will reports and full size maps present in PDF format
//		assertTrue(assessmentReportsPageAction.verifyPDFZipFilesAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));
//
//		// Meta Data zip should download. Report.csv,ReportSurvey.csv,ReportIsotopic.csv,ReportLISAS.csv,ReportGap.csv files are present.
//		String metadataZipFileVerifications = "True:True:True:False";  // "verifyGapMetaPresent=[TRUE]:verifyLisaMetaPresent=[TRUE]:verifySurveyMetaPresent=[TRUE]:verifyIsotopicMetaPresent=[FALSE]"
//		assertTrue(assessmentReportsPageAction.verifyMetaDataZIPFilesArePresent(metadataZipFileVerifications, getReportRowID(reportDataRowID1)));
//
//		// [Metadata verifications] ->
//		// - All the information present in ReportLISA.csv file
//		// - ReportId, ReportName, LISAId, LISANumber,Surveyor,LISADate/Time,Amplitude,Concentration,Field Notes, IndicationCoordinates, LatCoord, LongCoordis correct and matches report PDF.
//		// - Verify that unique LISA numbers in the format of XXXXXX-L#, where XXXXXX is the sequentially auto-incrementing Report ID and # is the sequential LISA number.
//		// - All Lisa instances should be in Caps (Eg. LISANumber values shuold be LISA 1, LISA 2, etc.)
//		// - Data present in ReportLisa.csv should be same as SSRS PDF indication table.
//		assertTrue(assessmentReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));
//
//		assertTrue(runTestCaseSpecificVerifications(assessmentReportsPageAction, testCaseID, getReportRowID(reportDataRowID1)).test(assessmentReportsPageAction));
}