package surveyor.regression.source;

import static org.junit.Assert.*;
import common.source.Log;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.AssessmentReportsPageActions;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.dataprovider.AssessmentReportDataProvider;
import surveyor.scommon.source.AssessmentReportsPage;
import surveyor.scommon.source.BaseReportsPageActionTest;

@RunWith(SurveyorTestRunner.class)
public class AssessmentReportsPageTest extends BaseReportsPageActionTest {

	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;

	private static HomePageActions homePageAction;
	private static LoginPageActions loginPageAction;
	private static TestEnvironmentActions testEnvironmentAction;
	private static ManageLocationPageActions manageLocationPageAction;
	private static ManageCustomerPageActions manageCustomerPageAction;
	private static ManageUsersPageActions manageUsersPageAction;
	private static AssessmentReportsPageActions assessmentReportsPageAction;

	private static HomePage homePage;
	private static AssessmentReportsPage assessmentReportsPage;
	private static MeasurementSessionsPage measurementSessionsPage;

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects();
	}

	@Before
	public void beforeTest() throws Exception {
		initializeTestObjects();

		initializePageActions();

		PageObjectFactory pageObjectFactory = new PageObjectFactory();
		homePage = pageObjectFactory.getHomePage();
		PageFactory.initElements(getDriver(), homePage);
		measurementSessionsPage = pageObjectFactory.getMeasurementSessionsPage();
		PageFactory.initElements(getDriver(),  measurementSessionsPage);
	}

	private AssessmentReportsPage getAssessmentReportsPage() {
		return (AssessmentReportsPage)getReportsPage();
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
		testEnvironmentAction = new TestEnvironmentActions();
		manageCustomerPageAction = ActionBuilder.createManageCustomerPageAction();
		manageUsersPageAction = ActionBuilder.createManageUsersPageAction();
		manageLocationPageAction = ActionBuilder.createManageLocationPageAction();
	}

	/**
	 * Test Case ID: TC1434_PicarroAdminCanProvideAssessmentPrivilegeExistingCustomer_NewAssessmentReport
	 * Test Description: Picarro Admin can provide Assessment privilege for existing Customer - New Assessment Report
	 * Script: -
	 *	- - Login as Picarro Admin
	 *	- - Navigate Picarro Admin -& Manage Customer -& Edit
	 *	- - Provide all required details and select Assessment privilege check box
	 *	- - Click OK
	 *	- - Login as customer admin or supervisor user
	 *	- - Click on Report link
	 *	- - Generate Assessment report (Select all options in views section)
	 *	- - Click on Report Viewer
	 *	- - Download Shape file and meta data
	 * Results: -
	 *	- - Assessment report link should be present on left navigation panel
	 *	- - Report is generated successfully
	 *	- - SSRS PDF, AssessmentZIP (PDF), AssessmentZIP (Shape), AssessmentZIP (Meta), Views PDF should be generated successfully
	 *	- -The Shapefile zip should download. Shape files should be present for FOV, LISA, GAP, BreadCrumb, PipeAll, PipesIntersectingLISA and PipesIntersectionGAP
	 *	- - LISA shape file's attribute table should have Label (LISA 1, LISA 2, etc), Lat and Long Co-ordinates
	 *	- - Meta Data zip should download. Report.csv,ReportSurvey.csv,ReportIsotopic.csv,ReportLISAS.csv,ReportGap.csv files are present.
	 *	- - All the information present in ReportLISA.csv file ReportId, ReportName, LISAId, LISANumber,Surveyor,LISADate/Time,Amplitude,Concentration,Field Notes, IndicationCoordinates, LatCoord, LongCoordis correct and matches report PDF.Verify that unique LISA numbers in the format of XXXXXX-L#, where XXXXXX is the sequentially auto-incrementing Report ID and # is the sequential LISA number. All Lisa instances should be in Caps (Eg. LISANumber values shuold be LISA 1, LISA 2, etc.)Data present in ReportLisa.csv should be same as SSRS PDF indication table
	 */
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1434, location = AssessmentReportDataProvider.class)
	public void TC1434_PicarroAdminCanProvideAssessmentPrivilegeExistingCustomer_NewAssessmentReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1434_PicarroAdminCanProvideAssessmentPrivilegeExistingCustomer_NewAssessmentReport ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		// Create new customer with Report ShapeFile enabled and login as new customer user.
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.createNewCustomer(EMPTY, 10 /*customerRowID*/);

		manageLocationPageAction.open(EMPTY, NOTSET);
		manageLocationPageAction.createNewLocation(EMPTY, 11 /*locationRowID*/);

		manageUsersPageAction.open(EMPTY, NOTSET);
		manageUsersPageAction.createNewCustomerUser(EMPTY, 21 /*userRowID*/);

		String usernameColonPassword = String.format("%s:%s", ManageUsersPageActions.workingDataRow.get().username, ManageUsersPageActions.workingDataRow.get().password);
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(usernameColonPassword, NOTSET);   /* login using newly created user */

		assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForViewDownloadToCompleteByViewIndex(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(assessmentReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(assessmentReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC1435_Re_EnableAssessmentPrivilegeExistingCustomer_CopyAssessmentReport
	 * Test Description: Re-enable Assessment privilege for existing Customer - Copy Assessment Report
	 * Script: -
	 *	- - Login as Picarro Admin
	 *	- - Navigate Picarro Admin -& Manage Customer -& Edit
	 *	- - Select Assessment privilege check box
	 *	- - Click OK
	 *	- - Login as customer admin or supervisor user
	 *	- - Click on Report link
	 *	- - Generate Assessment report using Copy functionality or customer boundary (e.g. Click on Copy button of 'PGE Assessment Report' generated above)
	 *	- - Modify few details and generate report
	 *	- - Click on Report Viewer
	 *	- - Download SSRS PDF, Assessment PDF, ShapeFile and MetaData ZIP
	 * Results: -
	 *	- - Assessment report link should be present on left navigation panel
	 *	- - CopyAssessmentReport screen should have same details that were present while generating report. Report title, report area, surveys, colors selected, viewcombinations, etc
	 *	- - Report is generated successfully
	 *	- - SSRS PDF,AssessmentZIP (PDF),AssessmentZIP (Shape),AssessmentZIP (Meta), Views PDF should be generated successfully
	 *	-
	 *	- -The Shapefile zip should download. Shape files should be present for FOV, LISA, GAP, BreadCrumb, PipeAll, PipesIntersectingLISA and PipesIntersectionGAP
	 *	- - LISA shape file's attribute table should have Label (LISA 1, LISA 2, etc), Lat and Long Co-ordinates
	 *	- - Meta Data zip should download. Report.csv,ReportSurvey.csv,ReportIsotopic.csv,ReportLISAS.csv,ReportGap.csv files are present.
	 *	- - All the information present in ReportLISA.csv file ReportId, ReportName, LISAId, LISANumber,Surveyor,LISADate/Time,Amplitude,Concentration,Field Notes, IndicationCoordinates, LatCoord, LongCoordis correct and matches report PDF.Verify that unique LISA numbers in the format of XXXXXX-L#, where XXXXXX is the sequentially auto-incrementing Report ID and # is the sequential LISA number. All Lisa instances should be in Caps (Eg. LISANumber values shuold be LISA 1, LISA 2, etc.)Data present in ReportLisa.csv should be same as SSRS PDF indication table
	 */
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1435, location = AssessmentReportDataProvider.class)
	public void TC1435_Re_EnableAssessmentPrivilegeExistingCustomer_CopyAssessmentReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1435_Re_EnableAssessmentPrivilegeExistingCustomer_CopyAssessmentReport ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.createNewCustomer(EMPTY, NOTSET);
		manageCustomerPageAction.editCustomerSelectLicensedFeatures(EMPTY, NOTSET);
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.copyReport(AssessmentReportsPageActions.workingDataRow.get().title, getReportRowID(reportDataRowID1));
		modifyReport(assessmentReportsPageAction, getReportRowID(reportDataRowID2));
		waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForViewDownloadToCompleteByViewIndex(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(assessmentReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(assessmentReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));
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
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1437, location = AssessmentReportDataProvider.class)
	public void TC1437_DisableAssessmentFeatureAssessmentPriviledgedCustomer(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1437_DisableAssessmentFeatureAssessmentPriviledgedCustomer ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.createNewCustomer(EMPTY, NOTSET);
		manageCustomerPageAction.editCustomerSelectLicensedFeatures(EMPTY, NOTSET);
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
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
	// TODO: Check surveys are assessment surveys.
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1438, location = AssessmentReportDataProvider.class)
	public void TC1438_AssessmentReportCanIncludeOnlyAssessmentSurveysNewScreen(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1438_AssessmentReportCanIncludeOnlyAssessmentSurveysNewScreen ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		assessmentReportsPageAction.open(EMPTY, NOTSET);
		assessmentReportsPageAction.clickOnNewReportButton(EMPTY, reportDataRowID1);
		assessmentReportsPageAction.selectCustomer(EMPTY, reportDataRowID1);
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
	// TODO: Check surveys are assessment surveys.
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

		assessmentReportsPageAction.copyReport(AssessmentReportsPageActions.workingDataRow.get().title, getReportRowID(reportDataRowID1));
		modifyReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));

		assessmentReportsPageAction.open(EMPTY, NOTSET);
		assessmentReportsPageAction.clickOnNewReportButton(EMPTY, reportDataRowID1);
		assessmentReportsPageAction.selectCustomer(EMPTY, reportDataRowID1);
		assessmentReportsPageAction.clickOnSurveySelectorSearchButton(EMPTY, reportDataRowID1);

		assertTrue(assessmentReportsPageAction.verifySearchedSurveysAreForSpecifiedCustomer(EMPTY, getReportRowID(reportDataRowID1)));
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
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1440, location = AssessmentReportDataProvider.class)
	public void TC1440_NewComplianceReportScreenShouldNotDisplayAssessmentModeSurveysAssessmentReportMode(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1440_NewComplianceReportScreenShouldNotDisplayAssessmentModeSurveysAssessmentReportMode ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
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
		assessmentReportsPageAction.deleteReport(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(assessmentReportsPageAction.verifyReportDeletedSuccessfully(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC1478_Pagination_AssessmentReport_CustomerSupervisorUser
	 * Test Description: Pagination - Assessment Report - Customer Supervisor User
	 * Script: -
	 *	- - Login as Customer Supervisor user- On Home Page, click Reports -&Assessment
	 *	- -10,25,50 and 100 Reports selection on Assessmentreport screen
	 * Results: -
	 *	- - Selected number of reports will be listed in the table
	 */
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1478, location = AssessmentReportDataProvider.class)
	public void TC1478_Pagination_AssessmentReport_CustomerSupervisorUser(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1478_Pagination_AssessmentReport_CustomerSupervisorUser ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		//assessmentReportsPageAction.setPagination(EMPTY, NOTSET);
		//assessmentReportsPageAction.verifyPagination(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC1482_SearchValidAssessmentReport
	 * Test Description: Search valid Assessment Report
	 * Script: -
	 *	- - Login as Customer Supervisor user- On Home Page, click Reports -&Assessment
	 *	- -Search valid report title
	 *	- - Search valid report name
	 * Results: -
	 *	- - Report search successful
	 */
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
	}

	/**
	 * Test Case ID: TC1483_SearchNon_ExistingAssessmentReport
	 * Test Description: Search non-existing Assessment Report
	 * Script: -
	 *	- - Login as Customer Supervisor user- On Home Page, click Reports -&Assessment
	 *	- -Search non-existing report title (eg. zzzzzz) or invalid report name (eg. CR-845673)
	 * Results: -
	 *	- - Message should be displayed : 'No matching records found'
	 */
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1483, location = AssessmentReportDataProvider.class)
	public void TC1483_SearchNon_ExistingAssessmentReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1483_SearchNon_ExistingAssessmentReport ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		assertTrue(assessmentReportsPageAction.findReport(EMPTY, getReportRowID(reportDataRowID1)));
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
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1484, location = AssessmentReportDataProvider.class)
	public void TC1484_SearchValidCustomerBoundaryBoundarySelectorScreenOfAssessmentReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1484_SearchValidCustomerBoundaryBoundarySelectorScreenOfAssessmentReport ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		assertTrue(assessmentReportsPageAction.verifyViewsImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
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
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1488, location = AssessmentReportDataProvider.class)
	public void TC1488_GenerateAssessmentReportAllDefaultValuesFiltersSelectedUsingCustomBoundaryCustomerSupervisorUserWtihNonZeroExclusionValueDownloadIt(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1488_GenerateAssessmentReportAllDefaultValuesFiltersSelectedUsingCustomBoundaryCustomerSupervisorUserWtihNonZeroExclusionValueDownloadIt ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(assessmentReportsPageAction.verifyPDFZipFilesAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(assessmentReportsPageAction.verifyReportPDFMatches(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(assessmentReportsPageAction.verifyViewsImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(assessmentReportsPageAction.verifyPDFContainsInputtedInformation(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(assessmentReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(assessmentReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC1588_AssessmentReport_RemoveLISABoxOptionFromExistingCustomer
	 * Test Description: Assessment Report - Remove LISA Box option from existing customer
	 * Script: -
	 *	- - Log into PCubed as Picarro Admin
	 *	- - Navigate to Picarro Administration -& Manage Customers page
	 *	- - Select a customer and click the Edit button
	 *	- - Uncheck LISA Box 1.0 checkbox, check Report Shape File checkbox and Assessment checkbox and click OK
	 *	- - Navigate to Reports -& Assessment Reports and click on the New Assessment Report button
	 *	- - Fill out the necessary fields and select one or more options in the Views section.
	 *	- - Click OK
	 *	- - Once the report has completed generation, click on theReport Viewbutton and the Shape File button
	 *	- - Run the Shape Files through GIS software like ArcGIS
	 * Results: -
	 *	- - The LISA shapes drawn by the GIS software should be in the shapes of fans or circles, not boxes
	 *	-
	 *	- - LISA shape file's attribute table should have Label (LISA 1, LISA 2, etc), Lat and Long Co-ordinates
	 *	-
	 */
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1588, location = AssessmentReportDataProvider.class)
	public void TC1588_AssessmentReport_RemoveLISABoxOptionFromExistingCustomer(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1588_AssessmentReport_RemoveLISABoxOptionFromExistingCustomer ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.createNewCustomer(EMPTY, NOTSET);
		manageCustomerPageAction.editCustomerSelectLicensedFeatures(EMPTY, NOTSET);
		assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(assessmentReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC1594_AssessmentReport_CopyLISABoxReportClassicLISAReport
	 * Test Description: Assessment Report - Copy LISA Box report as Classic LISA report
	 * Script: -
	 *	- - Log into PCubed as Picarro Admin
	 *	- - Navigate to Picarro Administration -& Manage Customers page
	 *	- - Select a customer that does has LISA Box 1.0 enabled and click the Edit button
	 *	- - On the Edit Customer page, uncheck the LISA Box 1.0 box and make sure that the Report Shape File and Assessment boxes are checked and click OK
	 *	- -Navigate to Reports -& Assessment Reports and select a report for the above customer that has LISA Box shapes
	 *	- - Click the Copy button
	 *	- - Without changing any details, click OK
	 *	- - Once the report has completed generation, click on the Report View button and then click on the Shape File button
	 *	- - Run the Shape Files through GIS software like ArcGIS
	 * Results: -
	 *	- - The GIS software should draw all LISAs in the shape of fans or circles, not boxes
	 *	-
	 *	- - LISA shape file's attribute table should have Label (LISA 1, LISA 2, etc), Lat and Long Co-ordinates
	 *	-
	 */
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1594, location = AssessmentReportDataProvider.class)
	public void TC1594_AssessmentReport_CopyLISABoxReportClassicLISAReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1594_AssessmentReport_CopyLISABoxReportClassicLISAReport ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.createNewCustomer(EMPTY, NOTSET);
		manageCustomerPageAction.editCustomerSelectLicensedFeatures(EMPTY, NOTSET);
		assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		modifyReport(assessmentReportsPageAction, getReportRowID(reportDataRowID2));
		waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(assessmentReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
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
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1800, location = AssessmentReportDataProvider.class)
	public void TC1800_ValidateInformationPresentReportViewsPDFAssessmentReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1800_ValidateInformationPresentReportViewsPDFAssessmentReport ...");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(assessmentReportsPageAction, getReportRowID(reportDataRowID1));
		assertTrue(assessmentReportsPageAction.verifySSRSPDFFooter(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(assessmentReportsPageAction.verifyPDFContainsInputtedInformation(EMPTY, getReportRowID(reportDataRowID1)));
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
	@Test
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