package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static surveyor.scommon.source.SurveyorConstants.*;

import java.util.List;

import common.source.Log;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.Test;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.ReportsCompliance.IsotopicAnalysisTableColumns;
import surveyor.scommon.source.ReportsCompliance.LISAIndicationTableColumns;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest6 extends BaseReportsPageActionTest {

	private static HomePageActions homePageAction;
	private static LoginPageActions loginPageAction;
	private static ManageCustomerPageActions manageCustomerPageAction;
	private static ManageUsersPageActions manageUsersPageAction;
	private static ManageLocationPageActions manageLocationPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static TestEnvironmentActions testEnvironmentAction;

	@BeforeClass
	public static void beforeTestClass() throws Exception {
		initializePageActions();

		// Select run mode here.
		setPropertiesForTestRunMode();
	}

	@Before
	public void beforeTest() throws Exception{
		setPropertiesForTestRunMode();
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
		homePageAction = new HomePageActions(driver, baseURL, testSetup);
		manageCustomerPageAction = new ManageCustomerPageActions(driver, baseURL, testSetup);
		manageUsersPageAction = new ManageUsersPageActions(driver, baseURL, testSetup);
		manageLocationPageAction = new ManageLocationPageActions(driver, baseURL, testSetup);
		loginPageAction = new LoginPageActions(driver, baseURL, testSetup);
		complianceReportsPageAction = new ComplianceReportsPageActions(driver, baseURL, testSetup);
		setReportsPage((ComplianceReportsPage)complianceReportsPageAction.getPageObject());
		testEnvironmentAction = new TestEnvironmentActions();
	}

	/**
	 * Test Case ID: TC231_GenerateReportStandardModeSurveysLocationHavingValuesOtherThanDefaultOne
	 * Test Description: Generate the report in Standard mode for survey's location having values other than default one
	 * Script: -
	 *	- Login as customer1 admin
	 *	- On Home Page, navigate to Report -> Compliance -> New Compliance Report
	 *	- Generate Standard report for the surveys associated to specified Surveyor (eg. SurveyorUnit1)
	 *	- Download the generated report
	 * Results: -
	 *	- - Report should have indications as per the min amp for Location i.e. indications should be =& min amp
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC231, location = ComplianceReportDataProvider.class)
	public void TC231_GenerateReportStandardModeSurveysLocationHavingValuesOtherThanDefaultOne(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC231_GenerateReportStandardModeSurveysLocationHavingValuesOtherThanDefaultOne ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* NOTE: Use Customer Admin when assets populated */

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

		// Get min amplitude for the Analyzer and assert all values in Indication table > MinAmp.
		List<Float> minAmps = complianceReportsPageAction.getMinAmplitudesForSurveys(reportDataRowID1);
		Float locationMinAmp = manageLocationPageAction.getMinAmplitudeForLocation(DEFAULT_LOCATION_DATAROWID, SurveyModeType.Standard);
		assertTrue(complianceReportsPageAction.verifyLISAsIndicationTableMinAmplitudeValues(String.valueOf(locationMinAmp), NOTSET));
	}

	/**
	 * Test Case ID: TC235_GenerateComplianceReportByProvidingReportAreaLatLongValuesManually
	 * Test Description: Generate compliance report by providing report area lat/long values manually
	 * Script: -
	 *	- On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- Timezone : CST, Survey Mode: Standard, Exclusion Radius:
	 *	- Provide Lat/Long corodinates values manually
	 *	- Select Isotopic Analyses, Indications, Field Notes
	 *	- Asset Layer : Plastic, Copper. Boundary Layer : Level 1. Base Map Value: Map
	 *	- Add By Date Range and tag filters
	 *	- Add View 1 : select assets layer and boundaries. Base Map Value : Map
	 *	- Add View 2 : unselect assets and boundaries checkbox. Base Map Value : Satellite
	 *	- Click on OK and click ZIP(PDF) icon
	 * Results: -
	 *	- Surveys present in the grid will be for specifed tag and date range
	 *	- Report generated successfully having specified timezone and asset data for specified tag id and date range surveys
	 *	- User is allowed to downlaod the report
	 *	- Zip folder will have the report pdf and maps pdf for specified report area
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC235, location = ComplianceReportDataProvider.class)
	public void TC235_GenerateComplianceReportByProvidingReportAreaLatLongValuesManually(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC235_GenerateComplianceReportByProvidingReportAreaLatLongValuesManually ...");

		String TIMEZONE_STRING = null;
		String ASSET_DATA_STRING = null;

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyPDFZipFilesAreCorrect(EMPTY, NOTSET));

		// TODO: Check with owner on these verifications.
		// "Report generated successfully having specified timezone and asset data for specified tag id and date range surveys"
		//assertTrue(complianceReportsPageAction.verifyPDFContainsInputtedInformation(TIMEZONE_STRING, NOTSET));
		//assertTrue(complianceReportsPageAction.verifyPDFContainsInputtedInformation(ASSET_DATA_STRING, NOTSET));
	}

	/**
	 * Test Case ID: TC237_SearchValidCustomerBoundaryBoundarySelectorScreen
	 * Test Description: Search valid customer boundary on boundary selector screen
	 * Script: -
	 *	- On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- Select 'Customer Boundary' and click on Boundary Selector button
	 *	- Search valid boundary using "Select By Boundary Name"
	 * Results: -
	 *	- Customer Boundaries are present on map
	 *	- Boundaries having exact same name or similar to it are searched and displayed to the user in alphabetical list
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC237, location = ComplianceReportDataProvider.class)
	public void TC237_SearchValidCustomerBoundaryBoundarySelectorScreen(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC237_SearchValidCustomerBoundaryBoundarySelectorScreen ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openNewReportPage(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.enterCustomerBoundaryUsingAreaSelector(EMPTY, getReportRowID(reportDataRowID1));

		// We type 'Level 2-A' in boundary name and expect the following 2 entries to show up in the auto-complete list.
		String expectedBoundaries = "Level 2-AA,Level 2-AB";
		assertTrue(complianceReportsPageAction.verifyBoundariesAutoCompleteListContains(expectedBoundaries/*comma seperated list of boundaries*/, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC509_GenerateComplianceReportUsingCreateNewFunctionality
	 * Test Description: Generate Compliance Report using create new functionality
	 * Script: -
	 *	- On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- TimeZone : PST, Survey Mode: Standard, Exclusion Radius: 3
	 *	- Provide lat long values in Decimal format
	 *	- Add Views with base map value: map, satellite and none for all the values
	 *	- Asset Layer : All. Boundary Layer : All levels
	 *	- Select Indication table, Analysis
	 *	- Click OK and click Download/Zip Icon
	 * Results: -
	 *	- - Report generated successfully
	 *	- - Download report in zipped folder which will contain 8.5 X 11 reports and full size maps present in PDF format
	 *	- - SSRS will have the tables and input values information
	 *	- - Views will have the breadcrumb, FOV, indications, LISA and other information
	 *	- - LISA number present in indication table in SSRS should match the indication number present in maps
	 *	- - Indication tables should have values sorted by amplitude values
	 *	- - Isotopic analysis tables should have value sorted by date/time
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC509, location = ComplianceReportDataProvider.class)
	public void TC509_GenerateComplianceReportUsingCreateNewFunctionality(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC509_GenerateComplianceReportUsingCreateNewFunctionality ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));

		assertTrue(complianceReportsPageAction.verifyPDFZipFilesAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyAllSSRSTableInfos(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));

		Integer expectedLisaRows = 5;
		assertTrue(complianceReportsPageAction.verifyLISAsIndicationTableRowCountEquals(String.valueOf(expectedLisaRows), NOTSET));
		assertTrue(complianceReportsPageAction.verifyLISAsIndicationTableSortedDescByColumn(LISAIndicationTableColumns.Amplitude.toString(), NOTSET));
		assertTrue(complianceReportsPageAction.verifyIsotopicTableSortedAscByColumn(IsotopicAnalysisTableColumns.DateTime.toString(), NOTSET));
	}

	/**
	 * Test Case ID: TC510_GenerateComplianceReportUsingCopyFunctionality
	 * Test Description: Generate Compliance Report using copy functionality
	 * Script: -
	 *	Pre-requisite:
	 *	- Generate compliance report
	 *	- On Home Page, click Reports -> Compliance
	 *	- Click on 'Copy' button
	 *	- Modify few details and generate report
	 *	- Base Map Value as None
	 *	- Click on OK and click ZIP (PDF) icon
	 * Results: -
	 *	- - Report with specified modification is generated successfully
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC510, location = ComplianceReportDataProvider.class)
	public void TC510_GenerateComplianceReportUsingCopyFunctionality(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC510_GenerateComplianceReportUsingCopyFunctionality ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.copyReport(ComplianceReportsPageActions.workingDataRow.title, getReportRowID(reportDataRowID1));
		modifyComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID2));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID2));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID2));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID2));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID2));
		assertTrue(complianceReportsPageAction.verifyPDFZipFilesAreCorrect(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC514_DeleteComplianceReport
	 * Test Description: Delete Compliance report
	 * Script: -
	 *	Pre-requisite:
	 *	- Generate a new compliance report
	 *	- On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- Click on Delete icon
	 * Results: -
	 *	- - Warning should be present : ?Do you really want to delete report ID + title?
	 *	- - Deleted report is not present on compliance report list screen
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC514, location = ComplianceReportDataProvider.class)
	public void TC514_DeleteComplianceReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC514_DeleteComplianceReport ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnDeleteButton(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForConfirmDeletePopupToShow(EMPTY, getReportRowID(reportDataRowID1));
		clickOnConfirmDeleteReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyReportDeletedSuccessfully(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC520_ReportViewThumbnailsCustomerBoundaryMultipleViewsCustomerSupervisorUser
	 * Test Description: Report view thumbnails with Customer Boundary and multiple views as customer supervisor user
	 * Script: -
	 *	- Login as customer supervisor user
	 *	- On Compliance Reports page, click New Compliance Report button
	 *	- Select Customer Boundary and select any boundary from map
	 *	- Fill out all required fields
	 *	- Under "View" section, select LISAs, FOV, Breadcrumb, Indications, Base Map: Map
	 *	- View2 : Select All and Base Map: Satellite
	 *	- Click OK
	 *	- After the report has been generated, click on the Compliance Viewer button and then on a thumbnail
	 *	- Navigate away from Compliance Reports page and return
	 * Results: -
	 *	- User is navigated back to Compliance Reports page and after the report has been generated, a thumbnail of the view will appear in the Action column for that report
	 *	- The view should appear and the thumbnail should accurately reflect the view
	 *	- The thumbnail should still be present
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC520, location = ComplianceReportDataProvider.class)
	public void TC520_ReportViewThumbnailsCustomerBoundaryMultipleViewsCustomerSupervisorUser(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC520_ReportViewThumbnailsCustomerBoundaryMultipleViewsCustomerSupervisorUser ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Supervisor */

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyPDFThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyPDFZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyViewThumbnailIsShownInComplianceViewerByViewIndex("1", getReportRowID(reportDataRowID1));

		// TODO: Navigate away verifications remaining. To be added post US2400 merge that implements this..
	}

	/**
	 * Test Case ID: TC521_GenerateComplianceReportBySelectingCustomBoundaryMoreThanOneViewUsingCopyFunctionalityDownloadReport
	 * Test Description: Generate compliance report by selecting custom boundary for more than one view using copy functionality and download the report
	 * Script: -
	 *	- On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- Timezone : PST, Survey Mode: Standard, Exclusion Radius: 0
	 *	- Select 'Custom Boundary' and click Lat/Long Map Selector button, select area of interest and click OK
	 *	- Select Isotopic Analyses, Indications, Field Notes
	 *	- Asset Layer : Plastic, Copper. Boundary Layer : Level 1. Base Map Value: Map
	 *	- Add By Date Range and tag filters
	 *	- Add View 1 : select assets layer and boundaries. Base Map Value : Map
	 *	- Click on OK and allow report to generate
	 *	- For the report that was just generated, click 'Copy' button
	 *	- Add View 2 : unselect assets and boundaries checkbox. Base Map Value : Satellite
	 *	- Add View 3: select LISAs, FOV and Breadcrumb
	 *	- Add View 4: select Breadcrumb, Indications and Isotopic Analysis
	 *	- Add View 5: select Indications, Isotopic Analysis and Field Notes
	 *	- Add View 6: select Isotopic Analysis, Field Notes and Assets
	 *	- Add View 7: select Field Notes, Assets and Boundaries
	 *	- Click on OK and click Export icon
	 * Results: -
	 *	- Surveys present in the grid will be for specified tag and date range
	 *	- Report generated successfully having specified timezone and asset data for specified tag id and date range surveys
	 *	- User is allowed to download the report
	 *	- Zip folder will have the maps for the specified boundary
	 *	- All views should be in the same sequence as created (View 1, View 2, View 3, etc.)
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC521, location = ComplianceReportDataProvider.class)
	public void TC521_GenerateComplianceReportBySelectingCustomBoundaryMoreThanOneViewUsingCopyFunctionalityDownloadReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC521_GenerateComplianceReportBySelectingCustomBoundaryMoreThanOneViewUsingCopyFunctionalityDownloadReport ...");


		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));

		assertTrue(complianceReportsPageAction.verifyPDFZipFilesAreCorrect(EMPTY, NOTSET));

		// TODO: Check implementation.
		//assertTrue(complianceReportsPageAction.verifySearchedSurveysMatchTag(EMPTY, NOTSET));
		// TODO: Check implementation.
		//assertTrue(complianceReportsPageAction.verifySearchedSurveysMatchDateRange(EMPTY, NOTSET));

		String TIMEZONE_STRING = ComplianceReportsPageActions.workingDataRow.timezone;
		String ASSET_DATA_STRING = complianceReportsPageAction.getSelectedAssetNames(getReportRowID(reportDataRowID1));

		assertTrue(complianceReportsPageAction.verifySSRSDrivingSurveyTableInfo(EMPTY, NOTSET));
		assertTrue(complianceReportsPageAction.verifyPDFContainsInputtedInformation(TIMEZONE_STRING, NOTSET));
		assertTrue(complianceReportsPageAction.verifyPDFContainsInputtedInformation(ASSET_DATA_STRING, NOTSET));
		assertTrue(complianceReportsPageAction.verifyViewsInSSRSPDFAreInCorrectSequence(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC526_VerifyCustomerBoundariesDoNotExceedMaxArea
	 * Test Description: Verify customer boundaries do not exceed max area
	 * Script: -
	 *	- On the Compliance Reports page, click the "New Compliance Report" button, select PG&E customer
	 *	- Click on the "Customer Boundary" button, then the "Boundary Selector" button
	 *	- Click on the various levels
	 * Results: -
	 *	- - The areas for each of the levels should not exceed the maximum area for reports
	 *	- Eg. District boundaries are Not Reportable
	 *	- DistrictPlat boundaries can be selected
	 */
	@Ignore
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC526, location = ComplianceReportDataProvider.class)
	public void TC526_VerifyCustomerBoundariesDoNotExceedMaxArea(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC526_VerifyCustomerBoundariesDoNotExceedMaxArea ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.enterCustomerBoundaryUsingAreaSelector(EMPTY, getReportRowID(reportDataRowID1));
	}

	/**
	 * Test Case ID: TC678_ShapefileButtonAvailableComplianceReport_PicarroAdmin
	 * Test Description: Shapefile button available for Compliance Report - Picarro Admin
	 * Script: -
	 *	- Log in with a Picarro Admin user account
	 *	- On the Compliance Reports page, click the "New Compliance Report" button
	 *	- Fill out the required fields
	 *	- In the Views section, select only LISAs
	 *	- When the report finishes generating, click the thumbnail preview button
	 *	- Repeat the test, but in the Views section, select only FOV
	 *	- Repeat the test, but in the Views section, select only Breadcrumb
	 *	- Repeat the test, but in the Views section, select only Gaps
	 *	- Repeat the test, but in the Views section, select only Assets
	 *	- Repeat the test, but in the Views section, select all five of the features named above
	 * Results: -
	 *	- A window will pop up displaying all of the available files for download. Included among them should be a "Compliance ZIP (Shape)" button. This button should appear regardless of which of the named features are selected.
	 *	- Lisa, FoV, Breadcrumb, PipeIntersectingLisa and PipeIntersectingGaps shape files should be available in downloaded ZIP folder
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC678, location = ComplianceReportDataProvider.class)
	public void TC678_ShapefileButtonAvailableComplianceReport_PicarroAdmin(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC678_ShapefileButtonAvailableComplianceReport_PicarroAdmin ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC680_ShapefileExportReportOneView
	 * Test Description: Shapefile export for report with one view
	 * Script: -
	 *	- Log in as Picarro Admin
	 *	- On the Compliance Reports page, click the New Compliance Report" button.
	 *	- Fill out the required fields
	 *	- Select a survey that includes LISAs
	 *	- In the Views section, select only LISAs and generate the report
	 *	- Click the thumbnail preview button
	 *	- Click on the "Compliance.zip (Shape)" button
	 *	- Extract the individual files from the zipped file
	 *	- View the Shapefile content in ArcGIS
	 *	- Repeat the test with FOVs, Breadcrumbs, Assets and Gaps (Assets are selected, asset types must also be selected)
	 *	- Repeat the test with different combinations such as FOV and Breadcrumb
	 * Results: -
	 *	- A window will pop up, displaying all of the files available for download. There should be a "Compliance.zip (Shape) button
	 *	- The Shapefile zip should download
	 *	- There should only be four LISA shapefiles
	 *	- All of the features should appear correctly on the ArcGIS map
	 *	- The Shapefiles should have the following data:
	 *	LISA box
	 *	ID (Attribute column, unique identifier)
	 *	Shape (Spatial column)
	 *	Label
	 *	FOV
	 *	ID (Attribute column, unique identifier)
	 *	Shape (Spatial column)
	 *	Label
	 *	GAP box
	 *	ID (Attribute column, unique identifier)
	 *	Shape (Spatial column)
	 *	GapNumber
	 *	Row
	 *	Column
	 *	BREADCRUMB
	 *	ID (Attribute column, unique identifier)
	 *	Shape (Spatial column)
	 *	Label
	 *	LISA-Highlighted Assets
	 *	ID (Attribute column, unique identifier)
	 *	- There should only be Shapefiles for the selected features (Asset Shape files will appear only if LISAs and/or Gaps are also selected, and as "PipeIntersectingLISA" or "PipeIntersectingGap")
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC680, location = ComplianceReportDataProvider.class)
	public void TC680_ShapefileExportReportOneView(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC680_ShapefileExportReportOneView ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC681_ShapefileExportReportMultipleViews
	 * Test Description: Shapefile export for report with multiple views
	 * Script: -
	 *	- Log in as Picarro Admin
	 *	- On the Compliance Reports page, click the "New Compliance Report" button
	 *	- Fill out the required fields
	 *	- Select one survey that includes LISAs and FOV
	 *	- In the views section, select LISAs
	 *	- Add another view and select FOV and click OK
	 *	- When the report is finished generating, click the thumbnail preview button
	 *	- Click on the "Compliance.zip (Shape)" button
	 *	- Extract the individual files from the zipped file
	 *	- View the Shapefile content in ArcGIS
	 *	- Repeat the test with different customer selection (Shape file export should remain standard)
	 * Results: -
	 *	- A window will pop up, displaying all of the files available for download. There should be one Shapefile and raw data button per report view
	 *	- The Shapefile zip should download. Shape files should be present for FOV, LISA, GAP, BreadCrumb, PipesIntersectingLISA and PipesIntersectionGAP
	 *	- The features should be drawn in ArcGIS should correspond to those in the view
	 *	- Even if the same feature is included in multiple views, there should only be one Shapefile for that feature
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC681, location = ComplianceReportDataProvider.class)
	public void TC681_ShapefileExportReportMultipleViews(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC681_ShapefileExportReportMultipleViews ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC689_ShapefileAccessExistingCustomer
	 * Test Description: Shapefile access for existing customer
	 * Script: -
	 *	- Log in as Picarro Admin
	 *	- On Manage Customers page, select a customer that does not have Shapefile generation enabled and click the "Edit" button
	 *	- Confirm that the "Account Enabled" box is checked and check the "Shapefile Generation Enabled" button
	 *	- Click OK
	 *	- On the Compliance Reports page, select a report for that customer that has LISAs, FOV, Breadcrumb, Gaps and/or Assets and click on the thumbnail preview button
	 *	- Click on the Shapefile export button
	 * Results: -
	 *  - - The thumbnail preview grid should include a button for Shapefile export
	 *	- - The Shapefile should download
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC689, location = ComplianceReportDataProvider.class)
	public void TC689_ShapefileAccessExistingCustomer(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC689_ShapefileAccessExistingCustomer ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		// Add a new user customer with Report ShapeFile first disabled and then enable it.
		String allCustomerLicenseRowIDs = ALL_LICENSED_FEATURES_ROWIDS_NOLISABOX;
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.createNewCustomer(EMPTY, 6 /*customerRowID*/);
		manageCustomerPageAction.editCustomerSelectLicensedFeatures(allCustomerLicenseRowIDs, NOTSET);

		manageLocationPageAction.open(EMPTY, NOTSET);
		manageLocationPageAction.createNewLocation(EMPTY, 9);

		// Create the report with the specified customer.
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
	}

	/**
	 * Test Case ID: TC690_RemoveShapefileGenerationOptionFromExistingCustomer
	 * Test Description: Remove Shapefile generation option from existing customer
	 * Script: -
	 *	- Log in as a customer user that has shapefile generation enabled
	 *	- Generate a Compliance Report and click on the thumbnail preview button
	 *	-  Log in as Picarro Admin
	 *	- On Manage Customers page, select a customer that has Shapefile generation enabled and click the "Edit" button
	 *	- Confirm that the "Account Enabled" box is checked and uncheck the "Shapefile Generation Enabled" button
	 *	- Click OK
	 *	- Generate a Compliance Report and click on the thumbnail preview button
	 * Results: -
	 *  - - The thumbnail preview grid should include a button for Shapefile export
	 *	- - The thumbnail preview grid should not include a button for Shapefile export
	 */
	@Ignore    // Survey data upload needs to be handled for New Customer report generation scenarios.
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC690, location = ComplianceReportDataProvider.class)
	public void TC690_RemoveShapefileGenerationOptionFromExistingCustomer(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC690_RemoveShapefileGenerationOptionFromExistingCustomer ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		// Create new customer with Report ShapeFile enabled and login as new customer user.
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.createNewCustomer(EMPTY, 7 /*customerRowID*/);

		manageLocationPageAction.open(EMPTY, NOTSET);
		manageLocationPageAction.createNewLocation(EMPTY, 4 /*locationRowID*/);

		manageUsersPageAction.open(EMPTY, NOTSET);
		manageUsersPageAction.createNewCustomerUser(EMPTY, 12 /*userRowID*/);

		String usernameColonPassword = String.format("%s:%s", ManageUsersPageActions.workingDataRow.username, ManageUsersPageActions.workingDataRow.password);
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(usernameColonPassword, NOTSET);   /* login using newly created user */

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, reportDataRowID1));

		String reportShapeLicensedFeaturesRowID = "7";
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.editCustomerUnSelectLicensedFeatures(reportShapeLicensedFeaturesRowID, NOTSET);

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assertFalse(complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, reportDataRowID1));
	}

	/**
	 * Test Case ID: TC698_LISABoxesUniqueNumbersPrescribedFormat
	 * Test Description: LISA Boxes have unique numbers in prescribed format
	 * Script: -
	 *	- Log in as Picarro Admin
	 *	- On the Compliance Reports page, click "New Compliance Report"
	 *	- Fill out the required fields
	 *	- In the Views section, select LISAs but do not select Gaps, and click OK
	 * Results: -
	 *	- The SSRS report should list the LISA boxes with sequentially-generated numbers
	 *	- The report view should show all LISA boxes, numbered and corresponding to the SSRS list
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC698, location = ComplianceReportDataProvider.class)
	public void TC698_LISABoxesUniqueNumbersPrescribedFormat(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC698_LISABoxesUniqueNumbersPrescribedFormat ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyLISAsIndicationTableSortedAscByColumn(LISAIndicationTableColumns.LISANum.toString(), getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC699_CheckGapNumbersArePresentGapTableMapViewComplianceReport
	 * Test Description: Check Gap numbers are present in gap table and map view in compliance report
	 * Script: -
	 *	- Log in as Picarro Admin
	 *	- On the Compliance Reports page, click the "New Compliance Report" button
	 *	- Fill out the required fields
	 *	- In the Views section, select Gaps but do not select LISAs, and click OK
	 *	- When the report has finished generating, click on the thumbnail preview button and download the Compliance ZIP (PDF)
	 *	- On the View PDF, zoom in to the maximum level
	 * Results: -
	 *	- The SSRS report should list all Gap boxes, listed and numbered with regard to their location as per map view
	 *	- The Gap numbers in the SSRS report should be in sequential order, from left to right and top to bottom and correspond to their location on the grid
	 *	- The SSRS report should not list Gaps that are completely covered by FOV
	 *	- The report map view should show the map overlaid with a grid, with columns marked A to Z and rows marked 1 to n
	 *	- The grid cells should be approximately 200 feet square
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC699, location = ComplianceReportDataProvider.class)
	public void TC699_CheckGapNumbersArePresentGapTableMapViewComplianceReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC699_CheckGapNumbersArePresentGapTableMapViewComplianceReport ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyGapsTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC700_GridNumberCoveredCompletelyFOVLISABoxesShouldNotPresentGapTableOfComplianceReport
	 * Test Description: Grid Number covered completely with FOV and LISA boxes should not be present in Gap Table of compliance report
	 * Script: -
	 *	- Log in as Picarro Admin
	 *	- On the Compliance Reports page, click the "New Compliance Report" button
	 *	- Fill out the required fields
	 *	- In the Views section, select FOV, LISAs and Gaps and click OK
	 *	- When the report finishes generating, click the thumbnail preview button and download the Compliance ZIP (PDF) file
	 *	- Extract the contents of the file
	 * Results: -
	 *	- The SSRS PDF report should not have Gap grid numbers that are completely covered by LISA or FOV
	 *	- The view should not show gaps in LISA boxes
	 *	(For eg. Lisa color is Yellow and Gap color is Red. So Lisa box yellow shade should not have red gap as GAPs is area not covered by FOV and LISA)
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC700, location = ComplianceReportDataProvider.class)
	public void TC700_GridNumberCoveredCompletelyFOVLISABoxesShouldNotPresentGapTableOfComplianceReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC700_GridNumberCoveredCompletelyFOVLISABoxesShouldNotPresentGapTableOfComplianceReport ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifySSRSImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC701_OverlappingLISABoxes
	 * Test Description: Overlapping LISA boxes
	 * Script: -
	 *	- Log in as Picarro Admin
	 *	- On the Compliance Reports page, click the "New Compliance Report" button
	 *	- Fill out the required fields
	 *	- Select a survey that has LISAs that are close enough to overlap each other
	 *	- In the Views section, select LISAs
	 *	- Click OK
	 *	- When the report has finished generating, click on the thumbnail preview button and download the Compliance ZIP PDF file
	 *	- Extract the contents of the zip
	 * Results: -
	 *	- In the report view, the LISA box should be rectagular in shape and circumscribe the LISA itself
	 *	- All LISA boxes should have a minimum width of 100 feet, regardless of the shape of the LISA itself
	 *	- There should be a 50 foot buffer between the LISA bubble and the side of the box perpendicular to the line bisecting the LISA
	 *	- Overlapping LISA boxes should not be combined into a single box. Each LISA should have its own LISA box drawn as a distinct box unto itself
	 */
	@Test   // TODO: Need surveys that have LISAs close enough to overlap each other added by Automation seed.
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC701, location = ComplianceReportDataProvider.class)
	public void TC701_OverlappingLISABoxes(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC701_OverlappingLISABoxes ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifySSRSImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC706_TabularOutputGapLISABoxes
	 * Test Description: Tabular Output for Gap and LISA Boxes
	 * Script: -
	 *	- Log in as Picarro Admin
	 *	- On the Compliance Reports page, click the "New Compliance Report" button
	 *	- Fill out the required fields
	 *	- In the Views section, select LISAs and Gaps
	 *	- Click OK
	 *	- When the report finishes generating, click the thumbnail preview button and download the Compliance ZIP (PDF) file
	 * Results: -
	 *  - - The PDF report should have the LISA and Gap data listed in tabular format
	 *	- - Lisa should be sorted by amplitude value and Gap should be present from A to Z and 1 to n
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC706, location = ComplianceReportDataProvider.class)
	public void TC706_TabularOutputGapLISABoxes(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC706_TabularOutputGapLISABoxes ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyLISAsIndicationTableInfo(EMPTY, NOTSET));
		assertTrue(complianceReportsPageAction.verifyGapsTableInfo(EMPTY, NOTSET));
		assertTrue(complianceReportsPageAction.verifyLISAsIndicationTableSortedDescByColumn(
				ReportsCompliance.LISAIndicationTableColumns.Amplitude.toString(), NOTSET));
	}

	/**
	 * Test Case ID: TC719_ShapeFileMetaDataReportFeaturePermissionNewCustomer
	 * Test Description: Shape file and meta data report feature permission for new customer
	 * Script: -
	 *	- Log in as Picarro Admin
	 *	- On Manage Customers page, click "Add New Customer"
	 *	- Fill out Name and EULA fields, then check "Account Enabled" , "Report ShapeFile" and  "Report Meta Data" permissions checkboxes
	 *	- Click OK
	 *	- Log in as customer's user
	 *	- On the Compliance Reports page, generate report for survey that has LISAs, FOV, Indications, Breadcrumb, Gaps and/or Assets
	 *	- Click on Compliance Viewer button
	 *	- Click on Compliance ZIP (Shape) download button
	 *	- Click on Compliance ZIP (Meta) download button
	 * Results: -
	 *  - -  Compliance Viewer display will have Compliance ZIP (Shape) and (Meta) buttons
	 *	- - Shape zip file will be downloaded successfully and it will have shape files
	 *	- - Meta zip file will be downloaded successfully and it will have meta data files
	 */
	@Ignore    // Survey data upload needs to be handled for New Customer report generation scenarios.
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC719, location = ComplianceReportDataProvider.class)
	public void TC719_ShapeFileMetaDataReportFeaturePermissionNewCustomer(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC719_ShapeFileMetaDataReportFeaturePermissionNewCustomer ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		// Create new customer with Report ShapeFile and Metadata feature and login as new customer user.
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.createNewCustomer(EMPTY, 8 /*customerRowID*/);

		manageLocationPageAction.open(EMPTY, NOTSET);
		manageLocationPageAction.createNewLocation(EMPTY, 5 /*locationRowID*/);

		manageUsersPageAction.open(EMPTY, NOTSET);
		manageUsersPageAction.createNewCustomerUser(EMPTY, 13 /*userRowID*/);

		String usernameColonPassword = String.format("%s:%s", ManageUsersPageActions.workingDataRow.username, ManageUsersPageActions.workingDataRow.password);
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(usernameColonPassword, NOTSET);   /* login using newly created user */

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(EMPTY, reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
		// TODO: Enable after method is implemented.
		//assertTrue(complianceReportsPageAction.verifyMetaDataZIPFilesAreCorrect(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC722_ShapefileExportReportMultipleSurveys
	 * Test Description: Shapefile export for report with multiple surveys
	 * Script: -
	 *	- Log in as Picarro Admin
	 *	- On the Compliance Reports page, click the "New Compliance Report" button
	 *	- Fill out the required fields
	 *	- Select one survey that includes LISAs
	 *	- Select another survey that includes LISAs
	 *	- In the views section, select LISAs
	 *	- When the report is finished generating, click the thumbnail preview button
	 *	- Click on the "Compliance.zip (Shape)" button
	 *	- Extract the individual files from the zipped file
	 *	- View the Shapefile content in ArcGIS
	 *	- Repeat the test with different combinations of features for each view such as FOV and Breadcrumb
	 * Results: -
	 *	- A window will pop up, displaying all of the files available for download. There should be one Shapefile button
	 *	- The Shapefile zip should download
	 *	- There should be four LISA Shapefiles
	 *	- The features drawn in ArcGIS should correspond to those in the view
	 *	- Even if the same feature is included in multiple survey, there should only be one Shapefile for each category of feature
	 *	- Shape file data should be same as report map PDF
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC722, location = ComplianceReportDataProvider.class)
	public void TC722_ShapefileExportReportMultipleSurveys(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC722_ShapefileExportReportMultipleSurveys ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifySSRSImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC736_ShapefileExportReportLISA_HighlightedAssets
	 * Test Description: Shapefile export for report with LISA-Highlighted Assets
	 * Script: -
	 *	- Log in as Picarro Admin
	 *	- On the Compliance Reports page, click the New Compliance Report" button.
	 *	- Fill out the required fields
	 *	- Select a survey that includes LISA boxes that have Assets running through them
	 *	- In the Views section, select only LISAs and Assets and generate the report
	 *	- Click the thumbnail preview button
	 *	- Click on the "Compliance.zip (Shape)" button
	 *	- Extract the individual files from the zipped file
	 *	- View the Shapefile content in ArcGIS
	 * Results: -
	 *	- A window will pop up, displaying all of the files available for download. There should be a "Compliance ZIP (Shape) button
	 *	- The Shapefile zip should download
	 *	- There should be shapefiles for PipeIntersectingLISA
	 *	- All of the features should appear correctly on the ArcGIS map
	 *	- The Shapefiles should have the following data:
	 *	ID (Attribute column, unique identifier)
	 *	Asset Type (Attribute column)
	 *	Material Type (Attribute column)
	 *	External ID (Attribute column, unique identifier given by the customer)
	 *	Shape (Spatial column)
	 */
	@Ignore  // Need survey with Assets running through LISA boxes added to seed script.
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC736, location = ComplianceReportDataProvider.class)
	public void TC736_ShapefileExportReportLISA_HighlightedAssets(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC736_ShapefileExportReportLISA_HighlightedAssets ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		// TODO: Enable after action is implemented.
		//assertTrue(complianceReportsPageAction.verifyShapeZIPFilesAreCorrect(EMPTY /*hasPipeIntersectingLISA*/, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC737_ShapefileExportReportGap_HighlightedAssets
	 * Test Description: Shapefile export for report with Gap-Highlighted Assets
	 * Script: -
	 *	- Log in as Picarro Admin
	 *	- On the Compliance Reports page, click the New Compliance Report" button.
	 *	- Fill out the required fields
	 *	- Select a survey that includes Gap boxes that have Assets running through them
	 *	- In the Views section, select only Gaps and Assets and generate the report
	 *	- Click the thumbnail preview button
	 *	- Click on the "Compliance.zip (Shape)" button
	 *	- Extract the individual files from the zipped file
	 *	- View the Shapefile content in ArcGIS
	 * Results: -
	 *	- A window will pop up, displaying all of the files available for download. There should be a "Compliance ZIP (Shape) button
	 *	- The Shapefile zip should download
	 *	- There should be shapefiles for PipeIntersectingGap
	 *	- All of the features should appear correctly on the ArcGIS map
	 *	- The Shapefiles should have the following data:
	 *	ID (Attribute column, unique identifier)
	 *	Asset Type (Attribute column)
	 *	Material Type (Attribute column)
	 *	External ID (Attribute column, unique identifier given by the customer)
	 *	Shape (Spatial column)
	 */
	@Ignore   // Need survey that includes Gap boxes that have Assets running through them added to automation seed script.
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC737, location = ComplianceReportDataProvider.class)
	public void TC737_ShapefileExportReportGap_HighlightedAssets(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC737_ShapefileExportReportGap_HighlightedAssets ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC786_ShapefileMetaDataReportFeaturePermissionExistingCustomer_CopyComplianceReportGeneration
	 * Test Description: Shapefile and meta data report feature permission to existing customer - Copy Compliance report generation
	 * Script: -
	 *	- Log in as Picarro Admin
	 *	- On Manage Customers page, select a customer that does not have Report Shapefile and Report Meta Data permission enabled and click the "Edit" button
	 *	- Confirm that the "Account Enabled" box is checked and check the Report Shape File and Report Meta data button
	 *	- Click OK
	 *	- Login as Customer User
	 *	- On the Compliance Reports page, click on Copy button of above generated report  (For eg. Report tile: US895  Test Report 1) and click OK
	 *	- Click on Compliance Viewer button
	 *	- Click on the Shape file and meta file export button
	 * Results: -
	 *	- - Compliance Viewer dialog has Shape (ZIP) and Meta data (ZIP) export buttons
	 *	- - User can download the Shape files and meta data files successfully
	 */
	@Ignore    // Survey data upload needs to be handled for New Customer report generation scenarios.
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC786, location = ComplianceReportDataProvider.class)
	public void TC786_ShapefileMetaDataReportFeaturePermissionExistingCustomer_CopyComplianceReportGeneration(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC786_ShapefileMetaDataReportFeaturePermissionExistingCustomer_CopyComplianceReportGeneration ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		// Create new customer with Report ShapeFile and Metadata feature and login as new customer user.
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.createNewCustomer(EMPTY, 8 /*customerRowID*/);

		manageLocationPageAction.open(EMPTY, NOTSET);
		manageLocationPageAction.createNewLocation(EMPTY, 5 /*locationRowID*/);

		manageUsersPageAction.open(EMPTY, NOTSET);
		manageUsersPageAction.createNewCustomerUser(EMPTY, 13 /*userRowID*/);

		String usernameColonPassword = String.format("%s:%s", ManageUsersPageActions.workingDataRow.username, ManageUsersPageActions.workingDataRow.password);
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(usernameColonPassword, NOTSET);   /* login using newly created user */

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, NOTSET));
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(EMPTY, NOTSET));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));

		// TODO: Enable after implementation.
		//assertTrue(complianceReportsPageAction.verifyMetaDataZIPFilesAreCorrect(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC798_CheckPaginationSortingComplianceReportsPage
	 * Test Description: Check Pagination and Sorting on compliance reports page
	 * Script: -
	 *	- Log in to application and navigate to compliance report page
	 *	- Sort compliance report list with report name and other available attributes
	 *	- Select No of Rows as 25 and perform sorting for attributes till the last available page
	 *	- Select No of Rows as 50 and perform sorting for attributes till the last available page
	 *	- Select No of Rows as 100 and perform sorting for attributes till the last available page
	 * Results: -
	 *  - - Reports list is sorted based on attribute selected
	 *	- - Pagination and sorting should be working on all available pages
	 */
	@Ignore    // Add more verifications in verifyPaginationAndSortingOnAllColumns()
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC798, location = ComplianceReportDataProvider.class)
	public void TC798_CheckPaginationSortingComplianceReportsPage(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC798_CheckPaginationSortingComplianceReportsPage ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.selectPaginationRows(PAGINATIONSETTING_25, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyPaginationAndSortingOnAllColumns(PAGINATIONSETTING_25, NOTSET));

		complianceReportsPageAction.selectPaginationRows(PAGINATIONSETTING_50, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyPaginationAndSortingOnAllColumns(PAGINATIONSETTING_50, NOTSET));

		complianceReportsPageAction.selectPaginationRows(PAGINATIONSETTING_100, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyPaginationAndSortingOnAllColumns(PAGINATIONSETTING_100, NOTSET));

		complianceReportsPageAction.selectPaginationRows(PAGINATIONSETTING, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyPaginationAndSortingOnAllColumns(PAGINATIONSETTING, NOTSET));
	}
}
