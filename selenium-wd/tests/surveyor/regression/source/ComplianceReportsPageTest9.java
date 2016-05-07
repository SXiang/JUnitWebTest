package surveyor.regression.source;

import static org.junit.Assert.*;
import common.source.Log;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.Test;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.actions.SurveyViewPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.dataprovider.ComplianceReportDataProvider;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest9 extends BaseReportsPageActionTest {


	private static final String EMPTY = "";
	private static HomePageActions homePageAction;
	private static LoginPageActions loginPageAction;
	private static ManageCustomerPageActions manageCustomerPageAction;
	private static ManageUsersPageActions manageUsersPageAction;
	private static ManageLocationPageActions manageLocationPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static TestEnvironmentActions testEnvironmentAction;
	private static SurveyViewPageActions surveyViewPageAction;

	@BeforeClass
	public static void beforeTestClass() throws Exception {
		initializePageActions();
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
		surveyViewPageAction = new SurveyViewPageActions(driver, baseURL, testSetup);
		loginPageAction = new LoginPageActions(driver, baseURL, testSetup);
		testEnvironmentAction = new TestEnvironmentActions();

		// Select run mode here.
		setTestRunMode(ReportTestRunMode.UnitTestRun);
		
		if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			complianceReportsPageAction.fillWorkingDataForReports(getUnitTestReportRowID());
		}
	}

	/**
	 * Test Case ID: TC709_ShapefileButtonAvailableComplianceReport_CustomerUser
	 * Test Description: Shapefile button available for Compliance Report - Customer User
	 * Script: -  	
	 *	- - Log in with a Customer user account that has Shapefile download permission
	 *	- - On the Compliance Reports page, click the thumbnail preview button
	 * Results: - 
	 *	- - A window will pop up displaying all of the available files for download. Included among them should be a Compliance ZIP (Shape) button.
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC709, location = ComplianceReportDataProvider.class)
	public void TC709_ShapefileButtonAvailableComplianceReport_CustomerUser(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC709_ShapefileButtonAvailableComplianceReport_CustomerUser ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC710_ShapefileButtonNotAvailableCustomerUserIfCustomerDoesNotShapefileGenerationOptionEnabled
	 * Test Description: Shapefile button not available for Customer user if Customer does not have Shapefile generation option enabled
	 * Script: -  	
	 *	- - Log in with a Customer Utility Admin user account
	 *	- - On the Compliance Reports page, click the thumbnail preview button
	 * Results: - 
	 *	- - The Shapefile button should not appear
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC710, location = ComplianceReportDataProvider.class)
	public void TC710_ShapefileButtonNotAvailableCustomerUserIfCustomerDoesNotShapefileGenerationOptionEnabled(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC710_ShapefileButtonNotAvailableCustomerUserIfCustomerDoesNotShapefileGenerationOptionEnabled ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assertFalse(complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC711_MetadataExport_CSVFileSingleSurvey_PicarroAdmin_OneLISAOneIsoCapture
	 * Test Description: MetaData Export -CSV file (Single survey - Picarro admin - one LISA and one Iso capture)
	 * Script: -  	
	 *	- 1. Login to Pcubed with admin credentials. Click on the above mentioned Report . Click on Compliance ZIP(META) and download
	 *	- 2. Open report.csv file.
	 *	- 3. Open survey.csv file.
	 *	- 4.Open isoCapture.csv file.
	 *	- 5. Open LISA.csv file.
	 *	- 6. Open gap.csv file.
	 * Results: - 
	 *	- Verify that download is successful and Report.csv,ReportSurvey.csv,ReportIsotopic.csv,ReportLISAS.csv,ReportGap.csv are presentVerify that there is only one record present inReport.csv. All the information forReport Title, Report Name, Report ID, Report Creation Date, Report Author, Software Version, Exclusion Radius, Report Mode, NE Lat &amp; NE Long map boundary, SW Lat &amp; SW Long map boundary, Map Boundary ID, Percent coverage Assets, Percent Coverage Report Area is correct.Verify that additional columns ReportSurveyCount, ReportIsotopicAnalysisCount, ReportLISAsCount,ReportGapCount has numbers depending upon surveys, LISAs , ISo's, Gaps in the report.Data should be same as SSRS PDFVerify that there are multiple records (same as number of survey) present inReportSurvey.csv. All the information forReportSurvey Start Date/Time, Survey End Date/Time,Survey Duration, User Name, Surveyor, Analyzer, Tag, Stability Classis correct and matches with driving survey in the report.Date present in ReportSurvey.csv should be same as SSRS PDF survey sectionVerify that there are multiple recordspresent inReportIsotopic.csv. All the information forIsotopic Analysis Surveyor,Isotopic Analysis Date/Time,Isotopic Analysis Result,Isotopic Value and Uncertainty,Isotopic Analysis Field Notesis correct and matches with driving survey in the report.Data present in ReportIsotopic.csv should be same as SSRS PDF isotopic tableVerify that there are multiple record present inReportLISAS.csv. All the information for ReportId, ReportName, Lisa Number,Surveyor, LISA Date/Time, Amplitude, Concentration,Lat/Long co-ordinates,Field Notesis correct and matches with driving survey in the report.Data present in ReportLisa.csv should be same as SSRS PDF indication tableVerify that there is only the record present inReportGap.csvmatches with the information in thedriving survey in the report.Data present in ReportGap.csv should be same as SSRS PDF gap table
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC711, location = ComplianceReportDataProvider.class)
	public void TC711_MetadataExport_CSVFileSingleSurvey_PicarroAdmin_OneLISAOneIsoCapture(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC711_MetadataExport_CSVFileSingleSurvey_PicarroAdmin_OneLISAOneIsoCapture ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPFilesAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC713_LISABox10_MainsOutsideLISABoxAreNotHighlighted
	 * Test Description: LISA Box 1.0 - Mains Outside LISA Box Are Not Highlighted
	 * Script: -  	
	 *	- - Log in as Picarro Admin
	 *	- - On the Compliance Reports page, click the New Compliance Report button
	 *	- - Fill out the required fields
	 *	- - Select a survey that has LISAs and Mains assets not touching the LISA boxes
	 *	- - In the Views section, select LISAs and Assets
	 *	- - Click OK
	 *	- - When the report has finished generating, click on the thumbnail preview button and download the Compliance ZIP PDF file
	 *	- - Extract the contents of the zip
	 * Results: - 
	 *	- - In the report view, the segment of Main not contained within the LISA box (and not connected to a service inside the LISA box) should not be highlighted
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC713, location = ComplianceReportDataProvider.class)
	public void TC713_LISABox10_MainsOutsideLISABoxAreNotHighlighted(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC713_LISABox10_MainsOutsideLISABoxAreNotHighlighted ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC715_MetadataExport_CSVFileMultipleSurvey_PicarroSupport_MultipleLisasNoIso
	 * Test Description: MetaData Export -CSV file (multiple survey - Picarro support - multiple LISAs and no Iso)
	 * Script: -  	
	 *	- 1. Login to Pcubed with support credentials. Click on the above mentioned Report . Click on Compliance ZIP(META) and download
	 *	- 2. Open report.csv file.
	 *	- 3. Open survey.csv file.
	 *	- 4. Open LISA.csv file.
	 *	- 5. Open gap.csv file.
	 * Results: - 
	 *	- Verify that download is successful andReport.csv,ReportSurvey.csv,ReportIsotopic.csv,ReportLISAS.csv,ReportGap.csv are present.Verify that there is only one record present in Report.csv. All the information forReport Title,Report ID,Report Creation Date,Report Author,Software Version,Exclusion Radius,Report Mode,NE Lat &amp; NE Long map boundary,SW Lat &amp; SW Long map boundary,Map Boundary ID,Percent ,overage Assets,Percent Coverage Report Area is correct.Verify that additional columns ReportSurveyCount, ReportIsotopicAnalysisCount, ReportLISAsCount,ReportGapCount has numbers depending upon surveys, LISAs , ISo's, Gaps in the report.Data should be same as SSRS PDFVerify that there are multiple records (same as number of survey) present inReportSurvey.csv. All the information forReportSurvey Start Date/Time,Survey End Date/Time,Survey Duration,User Name,Surveyor,Analyzer,Tag,Stability Classis correct and matches with driving survey in the report.Date present in ReportSurvey.csv should be same as SSRS PDF survey sectionVerify that there are multiple record present in ReportLISAs.csv. All the information forSurveyor,Date/Time,Amplitude,Concentration,Field Notesis correct and matches with driving survey in the report.Verify that unique LISA numbers in the format of XXXXXX-L#, where XXXXXX is the sequentially auto-incrementing Report ID and # is the sequential LISA number.Data present in ReportLisa.csv should be same as SSRS PDF indication tableVerify that there is only the record present in Reportgap.csvmatches with the information in thedriving survey in the report.Data present in ReportGap.csv should be same as SSRS PDF gap table
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC715, location = ComplianceReportDataProvider.class)
	public void TC715_MetadataExport_CSVFileMultipleSurvey_PicarroSupport_MultipleLisasNoIso(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC715_MetadataExport_CSVFileMultipleSurvey_PicarroSupport_MultipleLisasNoIso ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPFilesAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC716_MetadataExport_CSVFileMultipleSurvey_MultipleLisasISO
	 * Test Description: MetaData Export -CSV file (multiple survey  -multiple LISAs and ISO)
	 * Script: -  	
	 *	- 1. Login to Pcubed as customer supervisor (PGE). Click on the above mentioned Report . Click on Compliance ZIP(META) and download
	 *	- 2. Open report.csv file.
	 *	- 3. Open survey.csv file.
	 *	- 4.Open isoCapture.csv file.
	 *	- 5. Open LISA.csv file.
	 *	- 6. Open gap.csv file.
	 * Results: - 
	 *	- Verify that download is successful and Report.csv,ReportSurvey.csv,ReportIsotopic.csv,ReportLISAS.csv,ReportGap.csv are present.Verify that there is only one record present in Report.csv. All the information forReport Title, Report Name, Report ID, Report Creation Date, Report Author, Software Version, Exclusion Radius, Report Mode, NE Lat &amp; NE Long map boundary, SW Lat &amp; SW Long map boundary, Map Boundary ID, Percent coverage Assets, Percent Coverage Report Area is correct.Verify that additional columns ReportSurveyCount, ReportIsotopicAnalysisCount, ReportLISAsCount,ReportGapCount has numbers depending upon surveys, LISAs , ISo's, Gaps in the report.Data should be same as SSRS PDFVerify that there are multiple records (same as number of survey) present in ReportSurvey.csv. All the information forReportSurvey Start Date/Time, Survey End Date/Time,Survey Duration, User Name, Surveyor, Analyzer, Tag, Stability Classis correct and matches with driving survey in the report.Date present in ReportSurvey.csv should be same as SSRS PDF survey sectionVerify that there are multiple recordspresent inReportIsotopic.csv. All the information forIsotopic Analysis Surveyor,Isotopic Analysis Date/Time,Isotopic Analysis Result,Isotopic Value and Uncertainty,Isotopic Analysis Field Notesis correct and matches with driving survey in the report.Data present in ReportIsotopic.csv should be same as SSRS PDF isotopic tableVerify that there are multiple record present inReportLISAS.csv. All the information for ReportId, ReportName, Lisa Number,Surveyor, LISA Date/Time, Amplitude, Concentration,Lat/Long co-ordinates,Field Notesis correct and matches with driving survey in the report.Data present in ReportLisa.csv should be same as SSRS PDF indication tableVerify that there is only the record present inReportGap.csvmatches with the information in thedriving survey in the report.Data present in ReportGap.csv should be same as SSRS PDF gap table
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC716, location = ComplianceReportDataProvider.class)
	public void TC716_MetadataExport_CSVFileMultipleSurvey_MultipleLisasISO(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC716_MetadataExport_CSVFileMultipleSurvey_MultipleLisasISO ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPFilesAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC718_MetadataExport_CSVFileSingleSurvey_NoLISAOrISO
	 * Test Description: MetaData Export -CSV file (single survey -no LISA or ISO)
	 * Script: -  	
	 *	- 1. Login to Pcubed as customer admin. Click on the above mentioned Report . Click on Compliance ZIP(META) and download
	 *	- 2. Open report.csv file.
	 *	- 3. Open survey.csv file.
	 *	- 4. Open gap.csv file.
	 * Results: - 
	 *	- Verify that download is successful and Report.csv,ReportSurvey.csv,ReportGap.csv are present.Verify that there is only one record present in Report.csv. All the information forReport Title,Report ID,Report Creation Date,Report Author,Software Version,Exclusion Radius,Report Mode,NE Lat &amp; NE Long map boundary,SW Lat &amp; SW Long map boundary,Map Boundary ID,Percent ,overage Assets,Percent Coverage Report Area is correct.Verify that additional columns ReportSurveyCount, ReportIsotopicAnalysisCount, ReportLISAsCount,ReportGapCount has numbers depending upon surveys, LISAs , ISo's, Gaps in the report.Verify that there are multiple records (same as number of survey) present in ReportSurvey.csv. All the information forReportSurvey Start Date/Time,Survey End Date/Time,Survey Duration,User Name,Surveyor,Analyzer,Tag,Stability Classis correct and matches with driving survey in the report.Verify that there is only the record present in ReportGap.csvmatches with the information in thedriving survey in the report.ReportIsotopic.csv,ReportLISAS.csv files are not present
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC718, location = ComplianceReportDataProvider.class)
	public void TC718_MetadataExport_CSVFileSingleSurvey_NoLISAOrISO(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC718_MetadataExport_CSVFileSingleSurvey_NoLISAOrISO ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPFilesAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC721_RemoveShapeFileMetaDataFeaturePermissionFromExistingCustomer_NewComplianceReportVerification
	 * Test Description: Remove shape file and meta data feature permission from existing customer - New Compliance report verification
	 * Script: -  	
	 *	- - Log in as Picarro Admin
	 *	- - On Manage Customers page, select a customer that has Shape file and Meta data permission options enabled and click the Edit button (eg. PG&amp;E's)
	 *	- - Confirm that the Account Enabled box is checked and uncheck the Report Shape file and meta data buttons
	 *	- - Click OK
	 *	- - Log in as Customer user
	 *	- - Go to compliance report page and generate a new report
	 *	- - Click on Compliance Viewer button
	 * Results: - 
	 *	- - Compliance Viewer dialog does not have Shape (ZIP) and Meta data (ZIP) export buttons
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC721, location = ComplianceReportDataProvider.class)
	public void TC721_RemoveShapeFileMetaDataFeaturePermissionFromExistingCustomer_NewComplianceReportVerification(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC721_RemoveShapeFileMetaDataFeaturePermissionFromExistingCustomer_NewComplianceReportVerification ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		manageCustomerPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.createNewCustomer(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.editCustomerUnSelectLicensedFeatures(EMPTY, getReportRowID(reportDataRowID1));

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC723_LISABOX10_AssetsWithinGapAreHighlighted
	 * Test Description: LISA BOX 1.0 - Assets Within Gap Are Highlighted
	 * Script: -  	
	 *	- - Log in as Picarro Admin
	 *	- - On the Compliance Reports page, click the New Compliance Report button
	 *	- - Fill out the required fields
	 *	- - Select a survey that has Gaps and Assets inside the Gap boxes
	 *	- - In the Views section, select Gaps and Assets
	 *	- - Click OK
	 *	- - When the report has finished generating, click on the thumbnail preview button and download the Compliance ZIP PDF file
	 *	- - Extract the contents of the zip
	 * Results: - 
	 *	- - In the report view, the segment of assets contained within the Gap should be highlighted
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC723, location = ComplianceReportDataProvider.class)
	public void TC723_LISABOX10_AssetsWithinGapAreHighlighted(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC723_LISABOX10_AssetsWithinGapAreHighlighted ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC727_GapBoxesUniqueNumbersPrescribedFormat
	 * Test Description: Gap Boxes have unique numbers in prescribed format
	 * Script: -  	
	 *	- - Log in as Picarro Admin
	 *	- - On the Compliance Reports page, click the New Compliance Report button
	 *	- - Fill out the required fields
	 *	- - In the Views section, select Gaps but do not select LISAs, and click OK
	 *	- - When the report has finished generating, click on the thumbnail preview button and download the Compliance ZIP (PDF)
	 *	- - On the View PDF, zoom in to the maximum level
	 * Results: - 
	 *	- - The SSRS report should list all Gap boxes, listed and numbered with regard to their location according to the coordinates on the grid (A1, B1, C5, etc)
	 *	- - The report view should show the map overlaid with a grid, with columns marked A to Z and rows marked 1 to n
	 *	- - The Gap numbers in the SSRS report should correspond to their location on the grid
	 *	- - The grid cells should be approximately 200 feet square
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC727, location = ComplianceReportDataProvider.class)
	public void TC727_GapBoxesUniqueNumbersPrescribedFormat(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC727_GapBoxesUniqueNumbersPrescribedFormat ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyGapsTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifySSRSImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyGapsTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC771_VerifyReportNameSpecifiedComplianceReport
	 * Test Description: Verify report name is as specified for compliance report
	 * Script: -  	
	 *	- - Generate compliance report- Click on Compliance Viewer -& PDF- Click on ZIP(PDF)- Click on View files (no. of  view files will depend on Views selected while generating the report)
	 *	- - Click on Compliance ZIP (Shape)
	 *	- - Click on Compliance ZIP (Meta)
	 * Results: - 
	 *	- - All downloaded files should have Report Name : CR-first_6_report_id_characters
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC771, location = ComplianceReportDataProvider.class)
	public void TC771_VerifyReportNameSpecifiedComplianceReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC771_VerifyReportNameSpecifiedComplianceReport ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
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
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyPDFZipFilesAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPFilesAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyShapeZIPFilesAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC779_FlatteningCustomerBoundaryData_NewComplianceReport
	 * Test Description: Flattening Customer Boundary Data - New Compliance Report
	 * Script: -  	
	 *	- 1. Login to Pcubed with pg&amp;e utility admin and point car to pcubed (bound to PG&amp;E customer ) and
	 *	- go to Compliance Report Section.Click on New Report
	 *	- 2. Generate Report with District Plat selected in one of the view. Download the report.
	 * Results: - 
	 *	- 1. Verify that in the Optional View Layers section Boundaries are shown as per customer. e.g. for PG&amp;E it is should show 2 options 1. District and 2.District Plat
	 *	- 2. Verify the view shows district plat boundary correctly.
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC779, location = ComplianceReportDataProvider.class)
	public void TC779_FlatteningCustomerBoundaryData_NewComplianceReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC779_FlatteningCustomerBoundaryData_NewComplianceReport ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnNewComplianceReport(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.selectCustomer(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyCustomerSpecificBoundariesAreDisplayed(EMPTY, getReportRowID(reportDataRowID1)));
		complianceReportsPageAction.selectCustomer(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyCustomerSpecificBoundariesAreDisplayed(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC781_FlatteningCustomerBoundaryData_CopyComplianceReport
	 * Test Description: Flattening Customer Boundary Data - Copy Compliance Report
	 * Script: -  	
	 *	- 1. Login as Picarro customer support role and go toto compliance Report page click on copy button for Picarro compliance report
	 *	- 2. Login as Picarro customer support role and go toto compliance Report page click on copy button for PG&amp;E compliance report
	 * Results: - 
	 *	- 1.Verify that in the Optional View Layers section Boundaries are shown as 1. District and 2.District Plat
	 *	- 2.Verify that in the Optional View Layers section Boundaries are shown as 1. District and 2.District Plat
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC781, location = ComplianceReportDataProvider.class)
	public void TC781_FlatteningCustomerBoundaryData_CopyComplianceReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC781_FlatteningCustomerBoundaryData_CopyComplianceReport ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnNewComplianceReport(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.selectCustomer(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyCustomerSpecificBoundariesAreDisplayed(EMPTY, getReportRowID(reportDataRowID1)));
		complianceReportsPageAction.selectCustomer(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyCustomerSpecificBoundariesAreDisplayed(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC787_ShapefileMetaDataReportFeaturePermissionExistingCustomer_ReprocessComplianceReportGeneration
	 * Test Description: Shapefile and meta data report feature permission to existing customer - Reprocess Compliance report generation
	 * Script: -  	
	 *	- - Log in as Picarro Admin
	 *	- - On Manage Customers page, select a customer that does not have Report Shapefile and Report Meta Data permission enabled and click the Edit button
	 *	- - Confirm that the Account Enabled box is checked and check the Report Shape File and Report Meta data button
	 *	- - Click OK
	 *	- - On the Compliance Reports page, click on Reprocess button of above generated report (For eg. Report tile: US895 Test Report 2)
	 *	- - Click on Compliance Viewer button
	 *	- - Click on the Shape file and meta file export button
	 * Results: - 
	 *	- - Compliance Viewer dialog has Shape (ZIP) and Meta data (ZIP) export buttons
	 *	- - User can download the Shape files and meta data files successfully
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC787, location = ComplianceReportDataProvider.class)
	public void TC787_ShapefileMetaDataReportFeaturePermissionExistingCustomer_ReprocessComplianceReportGeneration(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC787_ShapefileMetaDataReportFeaturePermissionExistingCustomer_ReprocessComplianceReportGeneration ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		manageCustomerPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.createNewCustomer(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.editCustomerUnSelectLicensedFeatures(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnResubmitButton(EMPTY, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));

		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
	}
 
	/**
	 * Test Case ID: TC788_RemoveShapeFileMetaDataFeaturePermissionFromExistingCustomer_CopyComplianceReportVerification
	 * Test Description: Remove shape file and meta data feature permission from existing customer - Copy Compliance report verification
	 * Script: -  	
	 *	- - Log in as Picarro Admin- On Manage Customers page, select a customer that has Shape file and Meta data permission options enabled and click the Edit button (eg. PG&amp;E's)- Confirm that the Account Enabled box is checked and uncheck the Report Shape file and meta data buttons- Click OK - Log in as Customer user- Go to compliance report page- Click on Copy button of above generated report and click OK- Click on Compliance Viewer button
	 * Results: - 
	 *	- - Compliance Viewer dialog does not have Shape (ZIP) and Meta data (ZIP) export buttons
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC788, location = ComplianceReportDataProvider.class)
	public void TC788_RemoveShapeFileMetaDataFeaturePermissionFromExistingCustomer_CopyComplianceReportVerification(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC788_RemoveShapeFileMetaDataFeaturePermissionFromExistingCustomer_CopyComplianceReportVerification ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		
		manageCustomerPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.createNewCustomer(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.editCustomerUnSelectLicensedFeatures(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		
		assertTrue(complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC789_RemoveShapeFileMetaDataFeaturePermissionFromExistingCustomer_ReprocessComplianceReport
	 * Test Description: Remove shape file and meta data feature permission from existing customer - Reprocess Compliance report
	 * Script: -  	
	 *	- - Log in as Picarro Admin- On Manage Customers page, select a customer that has Shape file and Meta data permission options enabled and click the Edit button (eg. PG&amp;E's)- Confirm that the Account Enabled box is checked and uncheck the Report Shape file and meta data buttons- Click OK - Go to compliance report page- Click on Reprocess button of above generated report (eg. Report title: US895 Test Report 3)- Log in as Customer user and navigate to compliance report page- Click on Compliance Viewer button of above generated report (eg. Report title: US895 Test Report 3)
	 * Results: - 
	 *	- - Compliance Viewer dialog does not have Shape (ZIP) and Meta data (ZIP) export buttons
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC789, location = ComplianceReportDataProvider.class)
	public void TC789_RemoveShapeFileMetaDataFeaturePermissionFromExistingCustomer_ReprocessComplianceReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC789_RemoveShapeFileMetaDataFeaturePermissionFromExistingCustomer_ReprocessComplianceReport ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		manageCustomerPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.createNewCustomer(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.editCustomerUnSelectLicensedFeatures(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));

		assertTrue(complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC790_ShapefileMetaDataReportFeaturePermissionCustomerGenerateComplianceReportPicarroAdminSpecifiedCustomer
	 * Test Description: Shapefile and meta data report feature permission to customer and generate Compliance report as picarro admin for specified customer
	 * Script: -  	
	 *	- - Log in as Picarro Admin
	 *	- - On the Compliance Reports page, generate a new report for survey having Indications, LISA, FOV, breadcrumb, isotopic analysis. Add View having Indications, LISA, FOV, breadcrumb, isotopic analysis selected
	 *	- - Click on Compliance Viewer button
	 *	- - Click on Compliance ZIP (Shape) and Compliance ZIP (Meta) download buttons
	 * Results: - 
	 *	- - Compliance Viewer dialog hasCompliance ZIP (Shape) and Compliance ZIP (Meta)export buttons
	 *	- - Picarro Admin can download the Shape files and meta data files successfully
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC790, location = ComplianceReportDataProvider.class)
	public void TC790_ShapefileMetaDataReportFeaturePermissionCustomerGenerateComplianceReportPicarroAdminSpecifiedCustomer(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC790_ShapefileMetaDataReportFeaturePermissionCustomerGenerateComplianceReportPicarroAdminSpecifiedCustomer ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));

		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		
		assertTrue(complianceReportsPageAction.verifyShapeZIPFilesAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPFilesAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC791_ReportShapefilePermissionExistingCustomer_NewComplianceReportGeneration
	 * Test Description: Report Shapefile permission to existing customer - New Compliance report generation
	 * Script: -  	
	 *	- - Log in as Picarro Admin
	 *	- - On Manage Customers page, select a customer that does not have Report Shapefile and Report Meta Data permission enabled and click the Edit button
	 *	- - Confirm that the Account Enabled box is checked and check the Report Shape File button
	 *	- - Click OK
	 *	- - Login as Customer User
	 *	- - On the Compliance Reports page, generate the report and select LISAs, FOV, Breadcrumb, Gaps and/or Assets
	 *	- - Click on Compliance Viewer button
	 *	- - Click on the Shape file export button
	 * Results: - 
	 *	- - Compliance Viewer dialog hasCompliance ZIP (Shape)export button
	 *	- - User can download the Shape files files successfully
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC791, location = ComplianceReportDataProvider.class)
	public void TC791_ReportShapefilePermissionExistingCustomer_NewComplianceReportGeneration(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC791_ReportShapefilePermissionExistingCustomer_NewComplianceReportGeneration ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		manageCustomerPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.createNewCustomer(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.editCustomerUnSelectLicensedFeatures(EMPTY, getReportRowID(reportDataRowID1));

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));

		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyShapeZIPFilesAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC792_ReportMetaDataPermissionExistingCustomer_NewComplianceReportGeneration
	 * Test Description: Report Meta Data permission to existing customer - New Compliance report generation
	 * Script: -  	
	 *	- - Log in as Picarro Admin
	 *	- - On Manage Customers page, select a customer (CNP) that does not have Report Meta Data permission enabled and click the Edit button
	 *	- - Confirm that the Account Enabled box is checked and check the Report Meta data File checkbox
	 *	- - Click OK
	 *	- - Login as Customer Supervisor or Util admin User (CNP util admin)
	 *	- - On the Compliance Reports page, generate the report and select LISAs, FOV, Breadcrumb, Gaps and/or Assets
	 *	- - Click on Compliance Viewer button
	 *	- - Click on the Meta data export button
	 * Results: - 
	 *	- - Compliance Viewer dialog has Compliance ZIP (Meta)export button
	 *	- - User can download the Meta Data files successfully.Report.csv, ReportSurvey.csv, ReportIsotopic.csv, ReportLISAS.csv, ReportGap.csv should be present as per survey data included to generate the report
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC792, location = ComplianceReportDataProvider.class)
	public void TC792_ReportMetaDataPermissionExistingCustomer_NewComplianceReportGeneration(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC792_ReportMetaDataPermissionExistingCustomer_NewComplianceReportGeneration ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		manageCustomerPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.createNewCustomer(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.editCustomerUnSelectLicensedFeatures(EMPTY, getReportRowID(reportDataRowID1));

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));

		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyPDFZipFilesArePresent(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPFilesAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC793_ReportShapefilePermissionExistingCustomer_CopyComplianceReportGeneration
	 * Test Description: Report Shapefile permission to existing customer - Copy Compliance report generation
	 * Script: -  	
	 *	- - Log in as Picarro Admin
	 *	- - On Manage Customers page, select a customer that does not have Report Shapefile and Report Meta Data permission enabled and click the Edit button
	 *	- - Confirm that the Account Enabled box is checked and check the Report Shape File button
	 *	- - Click OK
	 *	- - Login as Customer User
	 *	- - On the Compliance Reports page, click on Copy button of above generated report (For eg. Report tile: US895 Test Report 5) and click OK
	 *	- - Click on Compliance Viewer button
	 *	- - Click on the Shape file export button
	 * Results: - 
	 *	- - Compliance Viewer dialog hasCompliance ZIP (Shape)export button
	 *	- - User can download the Shape files successfully
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC793, location = ComplianceReportDataProvider.class)
	public void TC793_ReportShapefilePermissionExistingCustomer_CopyComplianceReportGeneration(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC793_ReportShapefilePermissionExistingCustomer_CopyComplianceReportGeneration ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		manageCustomerPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.createNewCustomer(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.editCustomerUnSelectLicensedFeatures(EMPTY, getReportRowID(reportDataRowID1));

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.copyReport(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.modifyReport(EMPTY, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));
		
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyShapeZIPFilesAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC794_ReportShapefilePermissionExistingCustomer_ReprocessComplianceReport
	 * Test Description: Report Shapefile permission to existing customer - Reprocess Compliance report
	 * Script: -  	
	 *	- - Log in as Picarro Admin
	 *	- - On Manage Customers page, select a customer that does not have Report Shapefile and Report Meta Data permission enabled and click the Edit button
	 *	- - Confirm that the Account Enabled box is checked and check the Report Shape File button
	 *	- - Click OK
	 *	- - On the Compliance Reports page, click on Reprocess button of above generated report (For eg. Report tile: US895 Test Report 6)
	 *	- - Log in as Customer's user
	 *	- - Click on Compliance Viewer button
	 *	- - Click on the Shape file export button
	 * Results: - 
	 *	- - Compliance Viewer dialog hasCompliance ZIP (Shape)export button
	 *	- - User can download the Shape files successfully
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC794, location = ComplianceReportDataProvider.class)
	public void TC794_ReportShapefilePermissionExistingCustomer_ReprocessComplianceReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC794_ReportShapefilePermissionExistingCustomer_ReprocessComplianceReport ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		
		manageCustomerPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.createNewCustomer(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.editCustomerUnSelectLicensedFeatures(EMPTY, getReportRowID(reportDataRowID1));

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnResubmitButton(EMPTY, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));

		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyShapeZIPFilesAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC795_ReportMetaDataPermissionExistingCustomer_CopyComplianceReportGeneration
	 * Test Description: Report Meta Data permission to existing customer - Copy Compliance report generation
	 * Script: -  	
	 *	- - Log in as Picarro Admin
	 *	- - On Manage Customers page, select a customer that does not have Report Meta Data permission enabled and click the Edit button
	 *	- - Confirm that the Account Enabled box is checked and check the Report Meta Data File button
	 *	- - Click OK
	 *	- - Login as Customer supervisor User (CNP supervisor user)
	 *	- - On the Compliance Reports page, click on Copy button of above generated report (For eg. Report tile: US1200 Test Report 7) and click OK
	 *	- - Click on Compliance Viewer button
	 *	- - Click on the Meta Data file export button
	 * Results: - 
	 *	- - Compliance Viewer dialog hasCompliance ZIP (Meta)export button
	 *	- - User can download the Meta Data files successfully.Report.csv, ReportSurvey.csv, ReportIsotopic.csv, ReportLISAS.csv, ReportGap.csv should be present as per survey data included to generate the report
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC795, location = ComplianceReportDataProvider.class)
	public void TC795_ReportMetaDataPermissionExistingCustomer_CopyComplianceReportGeneration(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC795_ReportMetaDataPermissionExistingCustomer_CopyComplianceReportGeneration ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		manageCustomerPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.createNewCustomer(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.editCustomerUnSelectLicensedFeatures(EMPTY, getReportRowID(reportDataRowID1));

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.copyReport(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.modifyReport(EMPTY, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));

		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyPDFZipFilesArePresent(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPFilesAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC796_ReportMetaDataPermissionExistingCustomer_ReprocessComplianceReport
	 * Test Description: Report Meta Data permission to existing customer - Reprocess Compliance report
	 * Script: -  	
	 *	- - Log in as Picarro Admin
	 *	- - On Manage Customers page, select a customer that does not have Report Shapefile and Report Meta Data permission enabled and click the Edit button
	 *	- - Confirm that the Account Enabled box is checked and check the Report Meta data checkbox
	 *	- - Click OK
	 *	- - On the Compliance Reports page, click on Reprocess button of above generated report (For eg. Report tile: US1200 Test Report 6)
	 *	- - Log in as Customer's user (Atmo's util admin user)
	 *	- - Click on Compliance Viewer button
	 *	- - Click on the Meta Data file export button
	 * Results: - 
	 *	- - Compliance Viewer dialog hasCompliance ZIP (Meta)export button
	 *	- - User can download the Meta Data files successfully.Report.csv, ReportSurvey.csv, ReportIsotopic.csv, ReportLISAS.csv, ReportGap.csv should be present as per survey data included to generate the report
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC796, location = ComplianceReportDataProvider.class)
	public void TC796_ReportMetaDataPermissionExistingCustomer_ReprocessComplianceReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC796_ReportMetaDataPermissionExistingCustomer_ReprocessComplianceReport ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		manageCustomerPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.createNewCustomer(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.editCustomerUnSelectLicensedFeatures(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.clickOnResubmitButton(EMPTY, getReportRowID(reportDataRowID1));
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnResubmitButton(EMPTY, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));
		
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyPDFZipFilesArePresent(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPFilesAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));
	}
}
