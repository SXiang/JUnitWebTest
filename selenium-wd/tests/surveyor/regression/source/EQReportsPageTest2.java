/**
 *
 */
package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.CR_VALUEMISSING_MESSAGE;
import static surveyor.scommon.source.SurveyorConstants.CR_EQLINES_MESSAGE;
import static surveyor.scommon.source.SurveyorConstants.CR_SURVEYMISSING_MESSAGE;
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
		@Test/* Need surveys with indications */
		@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC532, location = EQReportDataProvider.class)
		public void TC532_GenerateEQReportAsCustomerSupervisorWhenOverlappingMultiSegments(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
			Log.info("\nRunning TC532_GenerateEQReportAsCustomerSupervisorWhenOverlappingMultiSegments ...");

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
			eqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
			createNewReport(eqReportsPageAction, getReportRowID(reportDataRowID1));
			assertTrue(eqReportsPageAction.waitForReportGenerationToComplete(EMPTY,  getReportRowID(reportDataRowID1)));
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
			loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
			eqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
			createNewReport(eqReportsPageAction, getReportRowID(reportDataRowID1));
			assertTrue(eqReportsPageAction.waitForReportGenerationToComplete(EMPTY,  getReportRowID(reportDataRowID1)));

			eqReportsPageAction.clickOnDeleteButton(EMPTY, getReportRowID(reportDataRowID1));
			eqReportsPageAction.waitForConfirmDeletePopupToShow(EMPTY, getReportRowID(reportDataRowID1));
			eqReportsPageAction.verifyWarningMessageOnDeleteButtonClickEquals(EMPTY, getReportRowID(reportDataRowID1));
			clickOnConfirmDeleteReport(eqReportsPageAction, getReportRowID(reportDataRowID1));
			assertTrue(eqReportsPageAction.verifyReportDeletedSuccessfully(EMPTY, NOTSET));
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
		@Test /* Need surveys with indications */
		@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC537, location = EQReportDataProvider.class)
		public void TC537_GenerateEQReportAsCustomerAdminWhenOverlappingMultiSegments(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
			Log.info("\nRunning TC537_GenerateEQReportAsCustomerAdminWhenOverlappingMultiSegments ...");

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
			eqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
			createNewReport(eqReportsPageAction, getReportRowID(reportDataRowID1));
			assertTrue(eqReportsPageAction.waitForReportGenerationToComplete(EMPTY,  getReportRowID(reportDataRowID1)));
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
			assertTrue(eqReportsPageAction.waitForReportGenerationToComplete(EMPTY,  getReportRowID(reportDataRowID1)));

			eqReportsPageAction.clickOnDeleteButton(EMPTY, getReportRowID(reportDataRowID1));
			eqReportsPageAction.waitForConfirmDeletePopupToShow(EMPTY, getReportRowID(reportDataRowID1));
			eqReportsPageAction.verifyWarningMessageOnDeleteButtonClickEquals(EMPTY, getReportRowID(reportDataRowID1));
			clickOnConfirmDeleteReport(eqReportsPageAction, getReportRowID(reportDataRowID1));
			assertTrue(eqReportsPageAction.verifyReportDeletedSuccessfully(EMPTY, NOTSET));
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
		@Test/* Need surveys with indications */
		@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC544, location = EQReportDataProvider.class)
		public void TC544_GenerateEQReportAsPicarroAdminWhenMultipleLinesAllFilters(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
			Log.info("\nRunning TC544_GenerateEQReportAsPicarroAdminWhenMultipleLinesAllFilters ...");

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
			eqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
			createNewReport(eqReportsPageAction, getReportRowID(reportDataRowID1));
			assertTrue(eqReportsPageAction.waitForReportGenerationToComplete(EMPTY,  getReportRowID(reportDataRowID1)));
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
		 *	 OR "No EQ Records present"
		 * - Map should display the selected line segments with numbers
		 */
		@Test/* Need surveys with indications */
		@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC554, location = EQReportDataProvider.class)
		public void TC554_GenerateEQReportAsPicarroSupportWhenMultiLinesPartiallyOutsideSurveyArea(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
			Log.info("\nRunning TC554_GenerateEQReportAsPicarroSupportWhenMultiLinesPartiallyOutsideSurveyArea ...");

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
			eqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
			createNewReport(eqReportsPageAction, getReportRowID(reportDataRowID1));
			assertTrue(eqReportsPageAction.waitForReportGenerationToComplete(EMPTY,  getReportRowID(reportDataRowID1)));
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
			loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
			eqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
			createNewReport(eqReportsPageAction, getReportRowID(reportDataRowID1));
			assertTrue(eqReportsPageAction.waitForReportGenerationToComplete(EMPTY,  getReportRowID(reportDataRowID1)));
		}


		/**
		 * Test Case ID: TC559_EQReportCannotBeGeneratedUnlessAllRequiredFieldsAreFilled
		 * Test Description: Check that EQ report cannot be generated unless all required fields are filled out
		 * Script:
		 * -  Login as Customer Supervisor user
		 *	- On Home Page, click Reports -> EQ -> 'New EQ Report' button
		 *	- Don't provide report title
		 *	- Don't select any line segment
		 *	- Don't include any survey
		 *	- Click OK
		 * Results:
		 *	-  Required fields should be highlighted in red color and user friendly message is displayed. "Please enter highlighted value"
		 *	- "Please add a survey" message is displayed
		 *	- "Please select more than one line segment" message should be present
		 */
		@Test
		@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC559, location = EQReportDataProvider.class)
		public void TC559_EQReportCannotBeGeneratedUnlessAllRequiredFieldsAreFilled(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
			Log.info("\nRunning TC559_EQReportCannotBeGeneratedUnlessAllRequiredFieldsAreFilled ...");
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
			eqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
			createNewReport(eqReportsPageAction, getReportRowID(reportDataRowID1));

			assertTrue(eqReportsPage.verifyErrorMessages(CR_VALUEMISSING_MESSAGE, CR_EQLINES_MESSAGE, CR_SURVEYMISSING_MESSAGE));
			assertTrue(eqReportsPage.isInputTitleHighlightedInRed());
		}
}