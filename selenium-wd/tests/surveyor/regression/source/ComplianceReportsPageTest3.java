package surveyor.regression.source;

import common.source.Log;
import common.source.PDFTableUtility;
import common.source.TestContext;
import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.Test;

import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.entities.CustomerSurveyInfoEntity;
import surveyor.scommon.generators.TestDataGenerator;
import surveyor.scommon.source.BaseTest;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest3 extends BaseReportsPageActionTest {

	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;

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
	 * Test Case ID: TC1319_GenerateComplianceReportPicarroSupportUser
	 * Test Description: - Generate Compliance Report as Picarro Support user
	 * Script: -
	 *	- - Log in as Picarro Support user
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button
	 *	- - Select 2 or 3 surveys with different tag values
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 * Results: -
	 *	- - Report generated successfully
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1319, location = ComplianceReportDataProvider.class)
	public void TC1319_GenerateComplianceReportPicarroSupportUser(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1319_GenerateComplianceReportPicarroSupportUser ..." +
			 "\nTest Description: Generate Compliance Report as Picarro Support user");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));

		assertTrue(complianceReportsPageAction.verifySSRSCoverageTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC1370_GenerateComplianceReportCustomerAdminIncludeMultipleSurveys
	 * Test Description: - Generate Compliance Report as Customer Admin, include multiple surveys
	 * Script: -
	 *	- - Log in as Customer Admin user (Eg. PG&E Util Admin)
	 *	- - On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- - Time Zone : PST, Survey Mode: Rapid Response
	 *	- - Select Customer boundary and select any Plat
	 *	- - Add 4 Surveys (present in the selected plat) with different tag values (try to include approx 8 hours surveys)
	 *	    (eg. 2 surveys with same tag and other surveys with other tag)
	 *	- - Select Indication table, Isotopic Analysis table
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 *	- - Download PDF, ZIP (PDF)
	 * Results: -
	 *	- - Report generated successfully
	 *  - - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1370, location = ComplianceReportDataProvider.class)
	public void TC1370_GenerateComplianceReportCustomerAdminIncludeMultipleSurveys(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1370_GenerateComplianceReportCustomerAdminIncludeMultipleSurveys ..." +
			 "\nTest Description: Generate Compliance Report as Customer Admin, include multiple surveys");
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifySSRSCoverageTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC1373_GenerateComplianceReportPicarroAdminAssetsReportArea
	 * Test Description: - Generate Compliance Report as Picarro Admin, Assets and Report Area
	 * Script: -
	 *	- -  Log in as Picarro Admin
	 *	- - On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- - Time Zone : PST, Survey Mode: Standard, Exclusion Radius: 0
	 *	- - Add 2 or 3 surveys with different tag values
	 *	- - Select Customer boundary and select any Plat
	 *	- - Select Indication table, Isotopic Analysis table
	 *	- - Select Percent Coverage Assets, Report Area check box
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 *	- - Download PDF, ZIP (PDF)
	 * Results: -
	 *	- - Report generated successfully
	 *	- - Percent Coverage Assets and Report Area value is displayed
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1373, location = ComplianceReportDataProvider.class)
	public void TC1373_GenerateComplianceReportPicarroAdminAssetsReportArea(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1373_GenerateComplianceReportPicarroAdminAssetsReportArea ..." +
			 "\nTest Description: Generate Compliance Report as Picarro Admin, Assets and Report Area");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
        complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

		assertTrue(complianceReportsPageAction.verifySSRSCoverageTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC1389_MetadataExport_CSVFileMultipleSurvey_MultipleLisasISO
	 * Test Description: - MetaData Export -CSV file (multiple survey -multiple LISAs and ISO)
	 * Script: -
	 *	- 1. Login to Pcubed as customer admin (CNP or any other customer).
	 *       Click on the above mentioned Report .
	 *       Click on Compliance ZIP(META) and download
	 *	- 2. Open report.csv file.
	 *	- 3. Open survey.csv file.
	 *	- 4.Open isoCapture.csv file.
	 *	- 5. Open LISA.csv file.
	 *	- 6. Open gap.csv file.
	 * Results: -
	 *	- Verify that download is successful and Report.csv,ReportSurvey.csv,ReportIsotopic.csv,ReportLISAS.csv,ReportGap.csv are present.
	 *	- Verify that there is only one record present in Report.csv. All the information for Report Title, Report Name, Report ID, Report Creation Date, Report Author, Software Version, Exclusion Radius, Report Mode, NE Lat & NE Long map boundary, SW Lat & SW Long map boundary, Map Boundary ID, Percent coverage Assets, Percent Coverage Report Area is correct.
	 *	- Verify that additional columns ReportSurveyCount, ReportIsotopicAnalysisCount, ReportLISAsCount,ReportGapCount has numbers depending upon surveys, LISAs , ISo's, Gaps in the report. Data should be same as SSRS PDF
	 *	- Verify that there are multiple records (same as number of survey) present in ReportSurvey.csv. All the information for Report Survey Start Date/Time, Survey End Date/Time,Survey Duration, User Name, Surveyor, Analyzer, Tag, Stability Class is correct and matches with driving survey in the report. Date present in ReportSurvey.csv should be same as SSRS PDF survey section
	 *	- Verify that there are multiple records present in ReportIsotopic.csv. All the information for Isotopic Analysis Surveyor,Isotopic Analysis Date/Time,Isotopic Analysis Result,Isotopic Value and Uncertainty,Isotopic Analysis Field Notes is correct and matches with driving survey in the report. Data present in ReportIsotopic.csv should be same as SSRS PDF isotopic table
	 *	- Verify that there are multiple record present in ReportLISAS.csv. All the information for ReportId, ReportName, Lisa Number, Surveyor, LISA Date/Time, Amplitude, Concentration, Lat/Long co-ordinates, Field Notes is correct and matches with driving survey in the report. Data present in ReportLisa.csv should be same as SSRS PDF indication table
	 *	- Verify that there is only the record present in ReportGap.csv matches with the information in the driving survey in the report. Data present in ReportGap.csv should be same as SSRS PDF gap table
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1389, location = ComplianceReportDataProvider.class)
	public void TC1389_MetadataExport_CSVFileMultipleSurvey_MultipleLisasISO(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1389_MetadataExport_CSVFileMultipleSurvey_MultipleLisasISO ..." +
			 "\nTest Description: MetaData Export -CSV file (multiple survey -multiple LISAs and ISO)");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));

		assertTrue(complianceReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC1394_CopyButtonPresentIn_ProgressComplianceReportCustomerSupervisorUser
	 * Test Description: - Copy button present for in-progress compliance report as customer supervisor user
	 * Script: -
	 *	- - Log in as Customer Supervisor user (eg. PGE or CNP or Atmos)
	 *	- - Generate a new compliance report
	 *	- - Click on Copy button when report is in in-progress state
	 * Results: -
	 *	- - Report will be in in-progress state and user can see Copy and Cancel Report buttons
	 *	- - Report is generated successfully
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1394, location = ComplianceReportDataProvider.class)
	public void TC1394_CopyButtonPresentIn_ProgressComplianceReportCustomerSupervisorUser(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1394_CopyButtonPresentIn_ProgressComplianceReportCustomerSupervisorUser ..." +
			 "\nTest Description: Copy button present for in-progress compliance report as customer supervisor user");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyCancelButtonIsDisplayed(EMPTY,getReportRowID(reportDataRowID1)));

		complianceReportsPageAction.copyInProgressReport(EMPTY,getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnOKButton(EMPTY, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
	}

	/**
	 * Test Case ID: TC1395_CopyButtonPresentIn_ProgressComplianceReportCustomerAdminUser
	 * Test Description: - Copy button present for in-progress compliance report as customer admin user
	 * Script: -
	 *	- - Log in as Customer admin user (eg. PGE or CNP or Atmos)
	 *	- - Generate a new compliance report
	 *	- - Click on Copy button when report is in in-progress state
	 * Results: -
	 *	- - Report will be in in-progress state and user can see Copy and Cancel Report buttons
	 *	- - Report is generated successfully
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1395, location = ComplianceReportDataProvider.class)
	public void TC1395_CopyButtonPresentIn_ProgressComplianceReportCustomerAdminUser(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1395_CopyButtonPresentIn_ProgressComplianceReportCustomerAdminUser ..." +
			 "\nTest Description: Copy button present for in-progress compliance report as customer admin user");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyCancelButtonIsDisplayed(EMPTY,getReportRowID(reportDataRowID1)));

		complianceReportsPageAction.copyInProgressReport(EMPTY,getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnOKButton(EMPTY, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
	}

	/**
	 * Test Case ID: TC12_ReportViewThumbnailsCustomBoundarySingleView
	 * Test Description: - Generate Compliance Report as Picarro Admin using Custom Boundary with single view of default size to verify thumbnails
	 * Script: -
	 *	- - Log in as Picarro Admin
	 *	- - On Compliance Reports page, click New Compliance Report button
	 *	- - Select Custom Boundary, click the Lat/Long Selector button and select an area
	 *	- - Fill out all required fields, leaving View Size default values of 8.5 x 11 inches
	 *	- - Under "View" section, select LISAs, FOV, Breadcrumb, Indications, Base Map = Map
	 *	- - Click OK
	 *	- - After the report has been generated, click on the Compliance Viewer button and then on a thumbnail
	 *	- - Navigate away from Compliance Reports page and return
	 * Results: -
	 *	- - User is navigated back to Compliance Reports page and after the report has been generated,
	 *      Compliance Viewer button is present in the Action column for that report
	 *	- - Thumbnails will be present  for compliance SSRS PDF, ZIP folders and generated view
	 *	- - The thumbnail should still be present
	 */
	@Test /* Input Custom Boundary manually */
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC12, location = ComplianceReportDataProvider.class)
	public void TC12_ReportViewThumbnailsCustomBoundarySingleView(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
			 Log.info("\nTest Description: Generate Compliance Report as Picarro Admin using Custom Boundary with single view of default size to verify thumbnails");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.verifyPDFThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyPDFZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyViewThumbnailIsShownInComplianceViewerByViewIndex("1", getReportRowID(reportDataRowID1));

		complianceReportsPageAction.getComplianceReportsPage().minimizeBrowserWindow();
		complianceReportsPageAction.getComplianceReportsPage().maxmizeBrowserWindow();

		complianceReportsPageAction.verifyPDFThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyPDFZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyViewThumbnailIsShownInComplianceViewerByViewIndex("1", getReportRowID(reportDataRowID1));

	}

	/**
	 * Test Case ID: TC13_ReportViewThumbnailsCustomBoundaryMultipleViews
	 * Test Description: - Generate Compliance Report as Customer Supervisor using Custom Boundary with multiple views of default size to verify thumbnails
	 * Script: -
	 * - Log in as Customer Supervisor
	 * - On Compliance Reports page, click New Compliance Report button
	 * - Select Custom Boundary, click the Lat/Long Selector button and select an area
	 * - Set View Size values to 20 x 20 inches
	 * - Fill out all other required fields
	 * - For View1, under "View" section, select LISAs, FOV, Breadcrumb, Indications, Base Map = Map
	 * - For View2, select LISAs, Indications, Field Notes, Gaps, Base Map = Satellite
	 * - For View3, select FOV, Indications, Isotopic Analysis, Assets, Boundaries, Base Map = Map
	 * - Click OK
	 * - After the report has been generated, click on the Compliance Viewer button and then on the thumbnail for View 1
	 * - Repeat for Views 2 and 3
	 * - Navigate away from Compliance Reports page and return
	 * Results: -
	 * - User is navigated back to Compliance Reports page and after the report has been generated, a thumbnail of the view will appear in the Action column for that report
	 * - View1 should appear and the thumbnail should accurately reflect the view
	 * - The thumbnails for Views 2 and 3 should accurately reflect their respective views
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC13, location = ComplianceReportDataProvider.class)
	public void TC13_ReportViewThumbnailsCustomBoundaryMultipleViews(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC13_ReportViewThumbnailsCustomBoundaryMultipleViews ..." +
			 "\nTest Description: Generate Compliance Report as Picarro Supervisor using Custom Boundary with multiple views of default size to verify thumbnails");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
 		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.verifyPDFThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyPDFZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyViewThumbnailIsShownInComplianceViewerByViewIndex("1", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyViewThumbnailIsShownInComplianceViewerByViewIndex("2", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyViewThumbnailIsShownInComplianceViewerByViewIndex("3", getReportRowID(reportDataRowID1));

		complianceReportsPageAction.getComplianceReportsPage().minimizeBrowserWindow();
		complianceReportsPageAction.getComplianceReportsPage().maxmizeBrowserWindow();

		complianceReportsPageAction.verifyPDFThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyPDFZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyViewThumbnailIsShownInComplianceViewerByViewIndex("1", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyViewThumbnailIsShownInComplianceViewerByViewIndex("2", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyViewThumbnailIsShownInComplianceViewerByViewIndex("3", getReportRowID(reportDataRowID1));

	}

	/**
	 * Test Case ID: TC14_ReportViewThumbnailsCustomerBoundarySingleView
	 * Test Description: - Generate Compliance Report as Customer Admin using Customer Boundary with single view of default size to verify thumbnails
	 * Script: -
	 *	- - Log in as Customer Admin
	 *	- - On Compliance Reports page, click New Compliance Report button
	 *	- - Select Customer Boundary and select an area from the tree
	 *	- - Fill out all required fields
	 *	- - Under "View" section, select LISAs, FOV, Breadcrumb, Indications, Base Map = Map
	 *	- - Click OK
	 *	- - After the report has been generated, click on the Compliance Viewer button and then on a thumbnail
	 *	- - Navigate away from Compliance Reports page and return
	 * Results: -
	 *	- - User is navigated back to Compliance Reports page and after the report has been generated, Compliance Viewer button is present in the Action column for that report
	 *	- - Thumbnails will be present  for compliance SSRS PDF, ZIP folders and generated view
	 *	- - The thumbnail should still be present
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC14, location = ComplianceReportDataProvider.class)
	public void TC14_ReportViewThumbnailsCustomerBoundarySingleView(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC14_ReportViewThumbnailsCustomerBoundarySingleView ..." +
			 "\nTest Description: Generate Compliance Report as Customer Admin using Customer Boundary with single view of default size to verify thumbnails");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.verifyPDFThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyPDFZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyViewThumbnailIsShownInComplianceViewerByViewIndex("1", getReportRowID(reportDataRowID1));

		complianceReportsPageAction.getComplianceReportsPage().minimizeBrowserWindow();
		complianceReportsPageAction.getComplianceReportsPage().maxmizeBrowserWindow();

		complianceReportsPageAction.verifyPDFThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyPDFZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyViewThumbnailIsShownInComplianceViewerByViewIndex("1", getReportRowID(reportDataRowID1));
	}

	/**
	 * Test Case ID: TC149_GenerateComplianceReportUsingSurveyorUnitTagFiltersMoreThanOneViewDownloadReport
	 * Test Description: - Generate compliance report using surveyor unit and tag filters for more than one view and download the report
	 * Script: -
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button
	 *	- - Timezone : PST, Survey Mode: Standard
	 *	- - Provide lat long values
	 *	- - Select Indications, Field Notes
	 *	- - Asset Layer : All. Boundary Layer : Level 2
	 *	- - Add By Surveyor Unit and tag filter
	 *	- - Add View 1: select Isocapture, Annotations, Assets and Boundaries. Base Map Value : Map
	 *	- - Add View 2: select LISA and FOV. Base Map Value : Satellite
	 *	- - Add View 3: select Indications, breadcrum and annotations. Base Map: Map
	 *	- - Click on OK and click Download icon
	 * Results: -
	 *	- - Report generated successfully having specified timezone and asset data for specified tag id and date range surveys
	 *	- - User is allowed to download the report
	 *	- - View names and option selections on SSRS should match those in UI when creating report
	 *	- - Export image should show the map for the specified Lat-Long boundary
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC149, location = ComplianceReportDataProvider.class)
	public void TC149_GenerateComplianceReportUsingSurveyorUnitTagFiltersMoreThanOneViewDownloadReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC149_GenerateComplianceReportUsingSurveyorUnitTagFiltersMoreThanOneViewDownloadReport ..." +
			 "\nTest Description: Generate compliance report using surveyor unit and tag filters for more than one view and download the report");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
        complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
        complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));
        complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID1));
        complianceReportsPageAction.clickOnComplianceViewerViewByIndex("2", getReportRowID(reportDataRowID1));
        complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("2", getReportRowID(reportDataRowID1));
        complianceReportsPageAction.clickOnComplianceViewerViewByIndex("3", getReportRowID(reportDataRowID1));
        complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("3", getReportRowID(reportDataRowID1));

        assertTrue(complianceReportsPageAction.verifyPDFContainsInputtedInformation(ComplianceReportsPageActions.workingDataRow.get().timezone, getReportRowID(reportDataRowID1)));
        assertTrue(complianceReportsPageAction.verifySSRSViewsTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifySSRSImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
        assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines("FALSE", getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC1490_CreateNewCustomerLISABoxOption
	 * Test Description: - Create new customer with LISA Box option
	 * Script: -
	 *	- - Log into PCubed as Picarro Admin
	 *	- - Navigate to Picarro Administration -> Manage Customers page
	 *	- - Click on Add New Customer button
	 *	- - Fill out Name and EULA fields, check LISA Box 1.0 checkbox, and make sure that the Report Shape File box is checked and click OK
	 *	- - For this customer, create: User; Location; Surveyor; Analyzer; Reference Gas Bottle
	 *	- - Log into the tablet on a Surveyor that is assigned to this customer
	 *	- - Click Mode-> Start a Survey
	 *	- - On the Start Survey popup, fill out the details, select Standard mode and click Start a Survey
	 *	- - Collect several LISAs, then click Mode -> Stop Survey
	 *	- - Log into PCubed, navigate to Reports -> Compliance Reports and click on the New Compliance Report button
	 *	- - Fill out the necessary fields, add the just-completed survey and select LISAs in the Views section. Click OK
	 *	- - Once the report has completed generation, click on the Compliance Viewer button and then the View thumbnail
	 *	- - Click on the Shape File button
	 *	- - Run the Shape Files through GIS software like ArcGIS
	 * Results: -
	 *	- - The report View should have all LISAs in the shape of boxes, not fans or circles
	 *	- - The shapes drawn by the GIS software should match those of the Compliance Report views
	 */
	@Ignore /* Start Stop survey is not a candidate of automated test */
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1490, location = ComplianceReportDataProvider.class)
	public void TC1490_CreateNewCustomerLISABoxOption(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1490_CreateNewCustomerLISABoxOption ..." +
			 "\nTest Description: Create new customer with LISA Box option");


	}

	/**
	 * Test Case ID: TC1491_CreateNewCustomerWithoutLISABoxOption
	 * Test Description: - create new customer without LISA Box option
	 * Script: -
	 *	- - Log into PCubed as Picarro Admin
	 *	- - Navigate to Picarro Administration -> Manage Customers page
	 *	- - Click on Add New Customer button
	 *	- - Fill out Name and EULA fields, leave LISA Box 1.0 checkbox unchecked, and make sure that the Report Shape File box is checked and click OK
	 *	- - For this customer, create: User; Location; Surveyor; Analyzer; Reference Gas Bottle
	 *	- - Log into the tablet on a Surveyor that is assigned to this customer
	 *	- - Click Mode-> Start a Survey
	 *	- - On the Start Survey popup, fill out the details, select Standard mode and click Start a Survey
	 *	- - Collect several LISAs, then click Mode -> Stop Survey
	 *	- - Log into PCubed, navigate to Reports -> Compliance Reports and click on the New Compliance Report button
	 *	- - Fill out the necessary fields, add the just-completed survey and select LISAs in the Views section. Click OK
	 *	- - Once the report has completed generation, click on the Compliance Viewer button and then the View thumbnail
	 *	- - Click on the Shape File button
     *	- - Run the Shape Files through GIS software like ArcGIS
	 * Results: -
	 *  - - The report View should have all LISAs in the shape of fans or circles, not boxes
	 *	- - The shapes drawn by the GIS software should match those of the Compliance Report views
	 */
	@Ignore /* Start Stop survey is not a candidate of automated test */
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1491, location = ComplianceReportDataProvider.class)
	public void TC1491_CreateNewCustomerWithoutLISABoxOption(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1491_CreateNewCustomerWithoutLISABoxOption ..." +
			 "\nTest Description: create new customer without LISA Box option");

	}

	/**
	 * Test Case ID: TC1497_RemoveLISABoxOptionFromExistingCustomer
	 * Test Description: - Remove LISA Box option from existing customer
	 * Script: -
	 *	- - Log into PCubed as Picarro Admin
	 *	- - Navigate to Picarro Administration -> Manage Customers page
	 *	- - Select a customer that has LISA Box 1.0 option enabled and click the Edit button
	 *	- - Uncheck LISA Box 1.0 checkbox, and make sure that the Report Shape File box is checked and click OK
	 *	- -Navigate to Reports -> Compliance Reports and click on the New Compliance Report button
	 *	- - Fill out the necessary fields and select LISAs in the Views section. Click OK
	 *	- - Once the report has completed generation, click on the Compliance Viewer button and then the View thumbnail
	 *	- - Click on the Shape File button
	 *	- - Run the Shape Files through GIS software like ArcGIS
	 * Results: -
	 *	- - The report View should have all LISAs in the shape of fans or circles, not boxes
	 *	- - The shapes drawn by the GIS software should match those of the Compliance Report views
	 */
	@Test /* Without changing of licensed feature */
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1497, location = ComplianceReportDataProvider.class)
	public void TC1497_RemoveLISABoxOptionFromExistingCustomer(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1497_RemoveLISABoxOptionFromExistingCustomer ..." +
			 "\nTest Description: Remove LISA Box option from existing customer");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro without Lisabox 1.0 */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));

	}
}
