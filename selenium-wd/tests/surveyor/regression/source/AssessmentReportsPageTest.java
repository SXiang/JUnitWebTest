package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.NOMATCHINGSEARCH;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;

import common.source.Log;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.Test;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.AssessmentReportsPageActions;
import surveyor.dataprovider.AssessmentReportDataProvider;
import surveyor.scommon.source.AssessmentReportsPage;
import surveyor.scommon.source.BaseReportsPageActionTest;

@RunWith(SurveyorTestRunner.class)
public class AssessmentReportsPageTest extends BaseReportsPageActionTest {

	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;

	private static HomePageActions homePageAction;
	private static LoginPageActions loginPageAction;
	private static ManageLocationPageActions manageLocationPageAction;
	private static ManageCustomerPageActions manageCustomerPageAction;
	private static ManageUsersPageActions manageUsersPageAction;
	private static AssessmentReportsPageActions assessmentReportsPageAction;

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

	private AssessmentReportsPage getAssessmentReportsPage() {
		return (AssessmentReportsPage)getReportsPage();
	}

	private static void setPropertiesForTestRunMode() throws Exception {
		setTestRunMode(ReportTestRunMode.FullTestRun);

		if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			assessmentReportsPageAction.fillWorkingDataForReports(getUnitTestReportRowID());
		}
	}

	/**
	 * Initializes the page action objects.
	 * @throws Exception
	 */
	protected static void initializePageActions() throws Exception {
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
		homePageAction = new HomePageActions(getDriver(), getBaseURL(), getTestSetup());
		assessmentReportsPageAction = new AssessmentReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
		setReportsPage((AssessmentReportsPage)assessmentReportsPageAction.getPageObject());
		manageCustomerPageAction = ActionBuilder.createManageCustomerPageAction();
		manageUsersPageAction = ActionBuilder.createManageUsersPageAction();
		manageLocationPageAction = ActionBuilder.createManageLocationPageAction();
	}

	/**
	 * Test Case ID: TC1437_DisableAssessmentFeatureAssessmentPriviledgedCustomer
	 * Test Description: Disable Assessment feature for assessment priviledged Customer
	 * Script: -
	 *	- - Login as Picarro Admin
	 *	- - Navigate Picarro Admin -& Manage Customer -& Edit
	 *	- - Unselect Assessment privilege check box
	 *	- - Click OK
	 *	- - Login as customer admin or supervisor user
	 *	- - Click on Reports link
	 * Results: -
	 *	- - Assessment privilege is not granted to Customer
	 *	- - Assessment report not present
	 */
	// Verified.
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1437, location = AssessmentReportDataProvider.class)
	public void TC1437_DisableAssessmentFeatureAssessmentPriviledgedCustomer(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1437_DisableAssessmentFeatureAssessmentPriviledgedCustomer ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		// Create new customer without Assessment permission and login as new customer user.
		manageCustomerPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.createNewCustomer(EMPTY, 11 /*customerRowID*/);

		manageLocationPageAction.open(EMPTY, NOTSET);
		manageLocationPageAction.createNewLocation(EMPTY, 12 /*locationRowID*/);

		manageUsersPageAction.open(EMPTY, NOTSET);
		manageUsersPageAction.createNewCustomerUser(EMPTY, 22 /*userRowID*/);

		String usernameColonPassword = String.format("%s:%s", ManageUsersPageActions.workingDataRow.get().username, ManageUsersPageActions.workingDataRow.get().password);
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(usernameColonPassword, NOTSET);   /* login using newly created user */

		assessmentReportsPageAction.getAssessmentReportsPage().open();  // open without waiting for load complete.
		assertTrue(homePageAction.verifyPageLoaded(EMPTY, NOTSET));		// verify user is redirected to home page.
	}

	/**
	 * Test Case ID: TC1438_AssessmentReportCanIncludeOnlyAssessmentSurveysNewScreen
	 * Test Description: Assessment Report can include only Assessment surveys on new screen
	 * Script: -
	 *	- - Login as Customer Supervisor or Customer Admin user
	 *	- - On Home Page, click Reports -& Assessment -& 'NewAssessmentReport' button
	 *	- - Search the Survey
	 * Results: -
	 *	-
	 *	- - Only Assessment customer's surveys should be present in the searched list
	 */
	// Verified.
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1438, location = AssessmentReportDataProvider.class)
	public void TC1438_AssessmentReportCanIncludeOnlyAssessmentSurveysNewScreen(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1438_AssessmentReportCanIncludeOnlyAssessmentSurveysNewScreen ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		assessmentReportsPageAction.open(EMPTY, NOTSET);
		assessmentReportsPageAction.clickOnNewReportButton(EMPTY, reportDataRowID1);
		assessmentReportsPageAction.verifyNewPageLoaded(EMPTY, reportDataRowID1);
		assessmentReportsPageAction.clickOnSurveySelectorSearchButton(EMPTY, reportDataRowID1);

		assertTrue(assessmentReportsPageAction.verifySearchedSurveysAreForSpecifiedCustomer(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC1439_AssessmentReportCanIncludeOnlyAssessmentSurveysCopyScreen
	 * Test Description: Assessment Report can include only Assessment surveys on copy screen
	 * Script: -
	 *	- - Login as Customer Supervisor or Customer Admin user
	 *	- - On Home Page, click Reports -& Assessment -& 'Copy' button
	 *	- - Search the Survey
	 * Results: -
	 *	-
	 *	- - Only assessment customer's surveys should be present in the searched list on copy screen
	 */
	// Verified.
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1439, location = AssessmentReportDataProvider.class)
	public void TC1439_AssessmentReportCanIncludeOnlyAssessmentSurveysCopyScreen(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1439_AssessmentReportCanIncludeOnlyAssessmentSurveysCopyScreen ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		assessmentReportsPageAction.open(EMPTY, NOTSET);
		createNewReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));

		assessmentReportsPageAction.copyReport(AssessmentReportsPageActions.workingDataRow.get().title, NOTSET);
		assertTrue(assessmentReportsPageAction.verifyReportPageFieldsAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC1440_NewComplianceReportScreenShouldNotDisplayAssessmentModeSurveysAssessmentReportMode
	 * Test Description: New Compliance Report screen should not display Assessment mode surveys and assessment report mode
	 * Script: -
	 *	- - Login as Customer Supervisor or Customer admin user
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button
	 *	- - Search the Survey
	 * Results: -
	 *	-
	 *	- - Assessment report mode is not displayed in Report mode section
	 *	- - Assessment surveys should not be displayed in searched survey grid
	 */
	// Verified.
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1440, location = AssessmentReportDataProvider.class)
	public void TC1440_NewComplianceReportScreenShouldNotDisplayAssessmentModeSurveysAssessmentReportMode(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1440_NewComplianceReportScreenShouldNotDisplayAssessmentModeSurveysAssessmentReportMode ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnNewReportButton(EMPTY, reportDataRowID1);
		assessmentReportsPageAction.verifyNewPageLoaded(EMPTY, reportDataRowID1);

		assertTrue(assessmentReportsPageAction.verifyReportModeIsNotShownOnPage(EMPTY, getReportRowID(reportDataRowID1)));

		assessmentReportsPageAction.selectCustomer(EMPTY, reportDataRowID1);
		assessmentReportsPageAction.clickOnSurveySelectorSearchButton(EMPTY, reportDataRowID1);

		assertTrue(assessmentReportsPageAction.verifySearchedSurveysAreForSpecifiedCustomer(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC1448_DeleteAssessmentReportCustomerSupervisorUser
	 * Test Description: Delete Assessment report as Customer Supervisor user
	 * Script: -
	 *	- - Log in as Customer Supervisor
	 *	- - On Home Page, click Reports -& Assessment
	 *	- - Click on Delete icon
	 * Results: -
	 *	-
	 *	- - Warning should be present : ?Do you really want to delete report ID + title?
	 *	- - Deleted report is not present on assessment report list screen
	 */
	// Verified.
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1448, location = AssessmentReportDataProvider.class)
	public void TC1448_DeleteAssessmentReportCustomerSupervisorUser(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1448_DeleteAssessmentReportCustomerSupervisorUser ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnDeleteButton(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForConfirmDeletePopupToShow(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.verifyWarningMessageOnDeleteButtonClickEquals(EMPTY, getReportRowID(reportDataRowID1));
		clickOnConfirmDeleteReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		assertTrue(assessmentReportsPageAction.verifyReportDeletedSuccessfully(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC1478_Pagination_AssessmentReport_CustomerSupervisorUser
	 * Test Description: Pagination - Assessment Report - Customer Supervisor User
	 * Script: -
	 *	- - Login as Customer Supervisor user- On Home Page, click Reports -&Assessment
	 *	- -10,25,50 and 100 Reports selection on Assessment report screen
	 * Results: -
	 *	- - Selected number of reports will be listed in the table
	 */
	// Verified.
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1478, location = AssessmentReportDataProvider.class)
	public void TC1478_Pagination_AssessmentReport_CustomerSupervisorUser(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1478_Pagination_AssessmentReport_CustomerSupervisorUser ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		// Create some reports to ensure data table gets populated over multiple executions of the test.
		createMultipleReports(assessmentReportsPageAction, reportDataRowID1, 3 /*numReportsToCreate*/);

		String paginationSetting25 = "25";
		String paginationSetting50 = "50";
		String paginationSetting100 = "100";

		assertTrue(this.getAssessmentReportsPage().checkPaginationSetting(PAGINATIONSETTING));
		assertTrue(!(this.getAssessmentReportsPage().getNumberofRecords() > Integer.parseInt(PAGINATIONSETTING)));
		assertTrue(this.getAssessmentReportsPage().checkPaginationSetting(paginationSetting25));
		assertTrue(!(this.getAssessmentReportsPage().getNumberofRecords() > Integer.parseInt(paginationSetting25)));
		assertTrue(this.getAssessmentReportsPage().checkPaginationSetting(paginationSetting50));
		assertTrue(!(this.getAssessmentReportsPage().getNumberofRecords() > Integer.parseInt(paginationSetting50)));
		assertTrue(this.getAssessmentReportsPage().checkPaginationSetting(paginationSetting100));
		assertTrue(!(this.getAssessmentReportsPage().getNumberofRecords() > Integer.parseInt(paginationSetting100)));
	}

	/**
	 * Test Case ID: TC1482_SearchValidAssessmentReport
	 * Test Description: Search valid Assessment Report
	 * Script: -
	 *	- - Login as Customer Supervisor user- On Home Page, click Reports -&Assessment
	 *	- - Search valid report title
	 *	- - Search valid report name
	 * Results: -
	 *	- - Report search successful
	 */
	// Verified.
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1482, location = AssessmentReportDataProvider.class)
	public void TC1482_SearchValidAssessmentReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1482_SearchValidAssessmentReport ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		assertTrue(assessmentReportsPageAction.findReport(EMPTY, getReportRowID(reportDataRowID1)));

		assessmentReportsPageAction.getAssessmentReportsPage().logout();
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(assessmentReportsPageAction.findReportByName(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC1483_SearchNonExistingAssessmentReport
	 * Test Description: Search non-existing Assessment Report
	 * Script: -
	 *	- - Login as Customer Supervisor user- On Home Page, click Reports -&Assessment
	 *	- -Search non-existing report title (eg. zzzzzz) or invalid report name (eg. CR-845673)
	 * Results: -
	 *	- - Message should be displayed : 'No matching records found'
	 */
	// Verified.
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1483, location = AssessmentReportDataProvider.class)
	public void TC1483_SearchNonExistingAssessmentReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1483_SearchNonExistingAssessmentReport ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));

		// Search for report without creation (non-existent report)
		assertFalse(assessmentReportsPageAction.getAssessmentReportsPage().searchReport("INVALID_REPORT_TITLE987", LoginPageActions.workingDataRow.get().username));
		assertEquals(NOMATCHINGSEARCH, assessmentReportsPageAction.getAssessmentReportsPage().getEmptyTableMessage());
	}

	/**
	 * Test Case ID: TC1484_SearchValidCustomerBoundaryBoundarySelectorScreenOfAssessmentReport
	 * Test Description: Search valid customer boundary on boundary selector screen of Assessment report
	 * Script: -
	 *	- - On Home Page, click Reports -&Assessment-& 'NewAssessmentReport' button
	 *	- - Select 'Customer Boundary' and click on Boundary Selector button
	 *	- - Search valid boundary using Select By Boundary Name
	 *	- Eg. CNP customer - in Dallas area search 455
	 *	- eg. PG&amp;E customer - search boundary as 52-
	 * Results: -
	 *	-
	 *	- - Customer Boundaries are present on map
	 *	- - Boundaries having exact same name or similar to it are searched and displayed to the user in alphabetical list
	 *	- Eg. All boundaries in map area having 455 should be searched
	 *	- eg. All boundaries in map area having *52- * should be searched
	 */
	// Verified.
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1484, location = AssessmentReportDataProvider.class)
	public void TC1484_SearchValidCustomerBoundaryBoundarySelectorScreenOfAssessmentReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1484_SearchValidCustomerBoundaryBoundarySelectorScreenOfAssessmentReport ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnNewReportButton(EMPTY, reportDataRowID1);
		assessmentReportsPageAction.verifyNewPageLoaded(EMPTY, reportDataRowID1);
		assertTrue(assessmentReportsPageAction.fillAndVerifyCustomerBoundaryNamesListInAreaSelectorIsCorrect(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC1488_GenerateAssessmentReportAllDefaultValuesFiltersSelectedUsingCustomBoundaryCustomerSupervisorUserWtihNonZeroExclusionValueDownloadIt
	 * Test Description: Generate assessment report with all default values/filters selected using custom boundary as customer supervisor user wtih non zero exclusion value and download it
	 * Script: -
	 *	- - Login as Customer Supervisor user
	 *	- - On Home Page, click Reports -& Assessment-& 'NewAssessmentReport' button
	 *	- - TimeZone : PST/PDT , Survey Mode: Standard, Exclusion Radius: 0
	 *	- - Provide lat long values
	 *	- - Provide non-default FOV opacity value (E.g FOV opacity - 0.9)
	 *	- - Select Gap table, Percent Assets Covered and Percent Report Area covered
	 *	- - Asset Layer : All. Boundary Layer : All levels
	 *	- - Add Survey
	 *	- - Add View1 with base map value: satellite and all values selected
	 *	- - Add View2 with base map value: map and select fov, gaps
	 *	- - Add View3 with base map value: none and select breadcrumb, assets and boundaries
	 *	- - Click OK
	 *	- - Click on ReportViewer button
	 *	- - Download SSRS PDF, Assessment PDF, ShapeFile and MetaData ZIP
	 *	- - Generate report with same parameters except exclusion radius (set it to 50 or 100) ad compare results of 2 reports
	 * Results: -
	 *	-
	 *	- - Report generated successfully
	 *	- - Download report in zipped folder which will contain 8.5 X 11 reports and full size maps present in PDF format
	 *	-
	 *	- -Assessment report SSRS PDF should have survey details, view details and assets
	 *	-
	 *	- - LISA, CGI and Gap Investigation Complete labels should not be present on first page of PDF
	 *	- - SSRS should not have anything related to Indications, LISA, Isotopic Analysis, Field Notes, Report Mode etc.
	 *	- - Maps should have Breadcrumb, FOV, Assets, Boundaries and gap data
	 *	- - Report creation date, date printed, Survey Start/End time details displayed in SSRS PDF is as expected
	 *	-
	 *	- -The Shapefile zip should download. Shape files should be present for FOV, LISA, GAP, BreadCrumb, PipeAll, PipesIntersectingLISA and PipesIntersectionGAP
	 *	- - LISA shape file's attribute table should have Label (LISA 1, LISA 2, etc), Lat and Long Co-ordinates
	 *	- - Meta Data zip should download. Report.csv,ReportSurvey.csv,ReportIsotopic.csv,ReportLISAS.csv,ReportGap.csv files are present.
	 *	- - All the information present in ReportLISA.csv file ReportId, ReportName, LISAId, LISANumber,Surveyor,LISADate/Time,Amplitude,Concentration,Field Notes, IndicationCoordinates, LatCoord, LongCoordis correct and matches report PDF.Verify that unique LISA numbers in the format of XXXXXX-L#, where XXXXXX is the sequentially auto-incrementing Report ID and # is the sequential LISA number. All Lisa instances should be in Caps (Eg. LISANumber values shuold be LISA 1, LISA 2, etc.)Data present in ReportLisa.csv should be same as SSRS PDF indication table
	 *	- - ReportLisa.csv and Lisa shape file should have suppressed LISAs for report having exclusion radius parameter value non zero (50 or 100)
	 */
	// Verified (some methods remaining to be verified)
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1488, location = AssessmentReportDataProvider.class)
	public void TC1488_GenerateAssessmentReportAllDefaultValuesFiltersSelectedUsingCustomBoundaryCustomerSupervisorUserWtihNonZeroExclusionValueDownloadIt(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1488_GenerateAssessmentReportAllDefaultValuesFiltersSelectedUsingCustomBoundaryCustomerSupervisorUserWtihNonZeroExclusionValueDownloadIt ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Customer Supervisor */

		assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));

		assessmentReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID1));
        assessmentReportsPageAction.clickOnComplianceViewerViewByIndex("2", getReportRowID(reportDataRowID1));
        assessmentReportsPageAction.waitForViewDownloadToCompleteByViewIndex("2", getReportRowID(reportDataRowID1));
        assessmentReportsPageAction.clickOnComplianceViewerViewByIndex("3", getReportRowID(reportDataRowID1));
        assessmentReportsPageAction.waitForViewDownloadToCompleteByViewIndex("3", getReportRowID(reportDataRowID1));

        assessmentReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.extractShapeZIP(EMPTY, getReportRowID(reportDataRowID1));

		// Maps should have Breadcrumb, FOV, Assets, Boundaries and gap data
		assertTrue(assessmentReportsPageAction.verifyViewsImagesWithBaselines("FALSE", getReportRowID(reportDataRowID1)));

		assertTrue(assessmentReportsPageAction.verifySSRSImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));

		// Shape files should be present for FOV, LISA, GAP, BreadCrumb, PipeAll, PipesIntersectingLISA and PipesIntersectionGAP
		assertTrue(assessmentReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));

		/* TURN ON THESE ASSERTS after local verification.

		// (PDF should have survey details, view details and assets)
		assertTrue(assessmentReportsPageAction.verifySSRSDrivingSurveyTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(assessmentReportsPageAction.verifySSRSViewsTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(assessmentReportsPageAction.verifySSRSLayersTableInfo(EMPTY, getReportRowID(reportDataRowID1)));

		//(Report creation date, date printed, Survey Start/End time details displayed in SSRS PDF is as expected)
		// date printed -> verified in Footer verification.
		// survey start/end date -> verified in verifySSRSDrivingSurveyTableInfo
		assertTrue(assessmentReportsPageAction.verifyReportCreationInSSRSPDFIsCorrect(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(assessmentReportsPageAction.verifySSRSPDFFooter(EMPTY, getReportRowID(reportDataRowID1)));

		// SSRS should not have anything related to Indications, LISA, Isotopic Analysis, Field Notes, Report Mode etc
		String notContainsText = "Indications:LISA:Isotopic Analysis:Field Notes:Report Mode";
		assertTrue(assessmentReportsPageAction.verifyPDFDoesNotContainInputtedInformation(notContainsText, getReportRowID(reportDataRowID1)));

		// Download report in zipped folder which will contain 8.5 X 11 reports and full size maps present in PDF format
		assertTrue(assessmentReportsPageAction.verifyPDFZipFilesAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));

		// Meta Data zip should download. Report.csv,ReportSurvey.csv,ReportIsotopic.csv,ReportLISAS.csv,ReportGap.csv files are present.

		// All the information present in ReportLISA.csv file
		//   ReportId, ReportName, LISAId, LISANumber,Surveyor,LISADate/Time,Amplitude,Concentration,Field Notes, IndicationCoordinates, LatCoord, LongCoordis correct and matches report PDF.
		// Verify that unique LISA numbers in the format of XXXXXX-L#, where XXXXXX is the sequentially auto-incrementing Report ID and # is the sequential LISA number.
		// All Lisa instances should be in Caps (Eg. LISANumber values shuold be LISA 1, LISA 2, etc.)
		// Data present in ReportLisa.csv should be same as SSRS PDF indication table.

		//assertTrue(assessmentReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));
		*/
	}

	/**
	 * Test Case ID: TC1800_ValidateInformationPresentReportViewsPDFAssessmentReport
	 * Test Description: Validate information present in report views PDF for assessment report
	 * Script: -
	 *	- - Login as customer supervisor
	 *	- - On Home Page, click Reports -& Assessment -& 'New Assessment Report' button
	 *	- - Timezone : EDT, Exclusion Radius: 0
	 *	- - Select 'Custom Boundary' and select any boundary area
	 *	- - View 1: Select all Base Map: Map
	 *	- - View 2: Select all Base Map: Satellite
	 *	- - Click on OK
	 *	- - Click on Report Viewer and download View PDF and ZIP(PDF) icon
	 * Results: -
	 *	-
	 *	- - Report is generated successfully
	 *	- - Both View PDFs should have Report Title, Report View, Date Generated, Report Mode, Report Author and Report Name details displayed in footer are as expected
	 *	-
	 */
	// Verified.
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1800, location = AssessmentReportDataProvider.class)
	public void TC1800_ValidateInformationPresentReportViewsPDFAssessmentReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1800_ValidateInformationPresentReportViewsPDFAssessmentReport ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));    /* Customer Supervisor */

		assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

		String reportTitle = AssessmentReportsPageActions.workingDataRow.get().title;
		assertTrue(assessmentReportsPageAction.verifyPDFContainsInputtedInformation(reportTitle, getReportRowID(reportDataRowID1)));
		assertTrue(assessmentReportsPageAction.verifySSRSPDFFooter(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC2019_VerifyAssessmentReportViewPDFShapefileExportHighlightedGapAssetsSelected
	 * Test Description: Verify assessment report view PDF and Shapefile export with Highlighted Gap Assets selected
	 * Script: -
	 *	- - Log in as Picarro Admin
	 *	- - On the Assessment Reports page, click the NewAssessmentReport button.
	 *	- - Fill out the required fields
	 *	- - Select a survey that includes gaps and Assets running through them
	 *	- - In the Views section, select only Highlight Gap Assets and generate the report
	 *	- - Click the thumbnail preview button
	 *	- - Download report view PDF
	 *	- - Click on the Assessment.zip (Shape) button
	 *	- - Extract the individual files from the zipped file
	 *	- - View the Shapefile content in ArcGIS
	 * Results: -
	 *	-
	 *	- - SSRS PDF should have Highlight Gap Assets checked in Views section
	 *	- - Report View PDF should have assets intersecting Gaps.
	 *	- - The Shapefile zip should download
	 *	- - There should be shapefiles for PipeIntersectingGap, PipeIntersectingLISA, LISA
	 *	- -LISA shape file's attribute table should have Label (LISA 1, LISA 2, etc), Lat and Long Co-ordinates
	 *	- - PipeAll, Gap, FOV shape files should not be present
	 *	-
	 */
	// Need specific survey
	@Ignore
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2019, location = AssessmentReportDataProvider.class)
	public void TC2019_VerifyAssessmentReportViewPDFShapefileExportHighlightedGapAssetsSelected(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2019_VerifyAssessmentReportViewPDFShapefileExportHighlightedGapAssetsSelected ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));

		assessmentReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(assessmentReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(assessmentReportsPageAction.verifyViewsImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(assessmentReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}
}