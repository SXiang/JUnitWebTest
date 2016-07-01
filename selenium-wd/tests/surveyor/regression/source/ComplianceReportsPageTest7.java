package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.Before;

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
import surveyor.scommon.source.BaseReportsPageActionTest.ReportTestRunMode;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.dataprovider.ComplianceReportDataProvider;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest7 extends BaseReportsPageActionTest {


	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;
	
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
		surveyViewPageAction = new SurveyViewPageActions(driver, baseURL, testSetup);
		loginPageAction = new LoginPageActions(driver, baseURL, testSetup);
		testEnvironmentAction = new TestEnvironmentActions();
	}

	/**
	 * Test Case ID: TC1298_GenerateReportWhenPartialOrNoSurveyCoveredByReportAreaBreadcrumbFovEtcSelected
	 * Test Description: Generate report when partial or no survey is covered by report area and breadcrumb, Fov, etc is selected
	 * Script: -  	
	 *	- - Log in to application and navigate to Reports-& Compliance Report -& New Compliance Report
	 *	- - Select report area and make sure surveys included while generating report are not part of that area
	 *	- - In views - select all (especially breadcrumb and FOV)
	 *	- - Select indications, isotopic and percent calc tables
	 *	- - Click OK
	 *	- - Click on Compliance Viewer
	 *	- - Download the report
	 * Results: - 
	 *	- - Report is generated and downloaded successfully
	 *	- - Indications and Isotopic tables will be empty
	 *	- - Views will not have any data (except Gaps - if user has selected Gaps while generating report)
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1298, location = ComplianceReportDataProvider.class)
	public void TC1298_GenerateReportWhenPartialOrNoSurveyCoveredByReportAreaBreadcrumbFovEtcSelected(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1298_GenerateReportWhenPartialOrNoSurveyCoveredByReportAreaBreadcrumbFovEtcSelected ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyPDFZipFilesArePresent(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyIndicationsTableIsEmpty(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyIsotopicTableIsEmpty(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC1314_CheckErrorMessageDisplayedIfPercentCoverageForecastCheckBoxSelectedCopyComplianceReportScreensAlongCustomBoundary
	 * Test Description: Check error message is displayed if Percent Coverage Forecast check box is selected on Copy Compliance Report screens along with Custom boundary
	 * Script: -  	
	 *	- - Log in to application as Customer admin user and navigate to Copy Compliance Report page
	 *	- - Click OK
	 * Results: - 
	 *	- - User friendly error messages are displayed: Selected Percent Coverage Forecast, Please select Customer Boundary
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1314, location = ComplianceReportDataProvider.class)
	public void TC1314_CheckErrorMessageDisplayedIfPercentCoverageForecastCheckBoxSelectedCopyComplianceReportScreensAlongCustomBoundary(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1314_CheckErrorMessageDisplayedIfPercentCoverageForecastCheckBoxSelectedCopyComplianceReportScreensAlongCustomBoundary ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.copyReport(EMPTY, getReportRowID(reportDataRowID1));
		modifyComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID2));
		assertTrue(complianceReportsPageAction.verifyErrorMessages(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC1353_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecast3SurveysDifferentTagsWhenNoFOVPresentThese3Surveys
	 * Test Description: Generate Compliance Report as Customer Admin, include Percent Coverage Forecast and 3 surveys with different tags when no FOV is present for these 3 surveys
	 * Script: -  	
	 *	- - Log in as Customer Admin user (Eg. PG&amp;E Util Admin)
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button 
	 *	- - Time Zone : MST, Survey Mode: Standard
	 *	- - Select Customer boundary and select any Plat
	 *	- - Add 3 Surveys (present in the selected plat) with different tag values and these surveys should not have any FOV
	 *	- - Select Indication table, Isotopic Analysis table
	 *	- - Select Percent Coverage Forecast check box
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 *	- - Download PDF, ZIP (PDF)
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - Coverage Value should be zero
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1353, location = ComplianceReportDataProvider.class)
	public void TC1353_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecast3SurveysDifferentTagsWhenNoFOVPresentThese3Surveys(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1353_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecast3SurveysDifferentTagsWhenNoFOVPresentThese3Surveys ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyReportFilesArePresent(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC1354_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecast2SurveysDifferentTagsWhenNoFOVPresentThese2Surveys
	 * Test Description: Generate Compliance Report as Picarro Admin, include Percent Coverage Forecast and 2 surveys with different tags when no FOV is present for these 2 surveys
	 * Script: -  	
	 *	- - Log in as Customer Admin user (Eg. PG&amp;E Util Admin)
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button 
	 *	- - Time Zone : EST, Survey Mode: Standard
	 *	- - Select Customer boundary and select any Plat
	 *	- - Add 2 Surveys (present in the selected plat) with different tag values and these surveys should not have any FOV (May be some capture or just a dot no action performed after starting the survey)
	 *	- - Select Indication table, Isotopic Analysis table
	 *	- - Select Percent Coverage Forecast check box
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 *	- - Download PDF, ZIP (PDF)
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - Coverage Value should be zero
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1354, location = ComplianceReportDataProvider.class)
	public void TC1354_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecast2SurveysDifferentTagsWhenNoFOVPresentThese2Surveys(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1354_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecast2SurveysDifferentTagsWhenNoFOVPresentThese2Surveys ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyReportFilesArePresent(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC1355_GenerateComplianceReportPicarroSupportIncludePercentCoverageForecast4OrMoreThan4SurveysDifferentTagsWhenNoFOVPresentTheseSurveys
	 * Test Description: Generate Compliance Report as Picarro Support, include Percent Coverage Forecast and 4 or more than 4 surveys with different tags when no FOV is present for these surveys
	 * Script: -  	
	 *	- - Log in as Customer Admin user (Eg. PG&amp;E Util Admin)
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button 
	 *	- - Time Zone : PST, Survey Mode: Standard
	 *	- - Select Customer boundary and select any Plat
	 *	- - Add 4 Surveys (present in the selected plat) with different tag values and these surveys should not have any FOV (May be some capture or just a dot no action performed after starting the survey)
	 *	- - Select Indication table, Isotopic Analysis table
	 *	- - Select Percent Coverage Forecast check box
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 *	- - Download PDF, ZIP (PDF)
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - Coverage Value should be zero
	 *	- - No Coverage Forecast Available message is displayed
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1355, location = ComplianceReportDataProvider.class)
	public void TC1355_GenerateComplianceReportPicarroSupportIncludePercentCoverageForecast4OrMoreThan4SurveysDifferentTagsWhenNoFOVPresentTheseSurveys(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1355_GenerateComplianceReportPicarroSupportIncludePercentCoverageForecast4OrMoreThan4SurveysDifferentTagsWhenNoFOVPresentTheseSurveys ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyReportFilesArePresent(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyReportPDFMatches("No Coverage Forecast Available", NOTSET));
	}
 
	/**
	 * Test Case ID: TC1356_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecast2Or3SurveysDifferentTagsWhenSurveysAreNotPartOfSelectedPlat
	 * Test Description: Generate Compliance Report as Picarro Admin, include Percent Coverage Forecast and 2 or 3 surveys with different tags when surveys are not part of selected Plat
	 * Script: -  	
	 *	- - Log in as Customer Admin user (Eg. PG&amp;E Util Admin)
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button 
	 *	- - Time Zone : EST, Survey Mode: Standard
	 *	- - Select Customer boundary and select any Plat
	 *	- - Add 2 or 3 Surveys with different tag values and these surveys should not be part of selected plat
	 *	- Eg. Plat selected is in Menlo Park area and survey is from santa clara area
	 *	- - Select Indication table, Isotopic Analysis table
	 *	- - Select Percent Coverage Forecast check box
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 *	- - Download PDF, ZIP (PDF)
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - Coverage Value should be zero
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1356, location = ComplianceReportDataProvider.class)
	public void TC1356_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecast2Or3SurveysDifferentTagsWhenSurveysAreNotPartOfSelectedPlat(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1356_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecast2Or3SurveysDifferentTagsWhenSurveysAreNotPartOfSelectedPlat ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyReportFilesArePresent(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC1357_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecast4OrMoreThan4SurveysDifferentTagsWhereSurveysAreNotPartOfSelectedPlat
	 * Test Description: Generate Compliance Report as Picarro Admin, include Percent Coverage Forecast and 4 or more than 4 surveys with different tags where surveys are not part of selected Plat
	 * Script: -  	
	 *	- - Log in as Customer Admin user (Eg. PG&amp;E Util Admin)
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button 
	 *	- - Time Zone : PST, Survey Mode: Standard
	 *	- - Select Customer boundary and select any Plat
	 *	- - Add 4 or more than 4 surveyswith different tag values and these surveys should not be part of selected plat
	 *	- Eg. Plat selected is in Menlo Park area and survey is from santa clara area
	 *	- - Select Indication table, Isotopic Analysis table
	 *	- - Select Percent Coverage Forecast check box
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 *	- - Download PDF, ZIP (PDF)
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - Reported Coverage should be 0
	 *	- - No Coverage Forecast Available message should be displayed
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1357, location = ComplianceReportDataProvider.class)
	public void TC1357_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecast4OrMoreThan4SurveysDifferentTagsWhereSurveysAreNotPartOfSelectedPlat(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1357_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecast4OrMoreThan4SurveysDifferentTagsWhereSurveysAreNotPartOfSelectedPlat ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyReportFilesArePresent(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyReportPDFMatches("No Coverage Forecast Available", NOTSET));
	}
 
	/**
	 * Test Case ID: TC1384_OnCopyComplianceReportScreenValidationPresentIfNoOptionSelectedViewsSection
	 * Test Description: On Copy compliance report screen, validation is present if no option is selected in Views section
	 * Script: -  	
	 *	- - Select a compliance report and click on Copy button
	 *	- - Add Views but dont select any option OR uncheck all the selected options for Views
	 *	- - Click OK
	 * Results: - 
	 *	- - Validation message should be present same as that present on New Report screen, warning user that at least one option in Views section must be selected
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1384, location = ComplianceReportDataProvider.class)
	public void TC1384_OnCopyComplianceReportScreenValidationPresentIfNoOptionSelectedViewsSection(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1384_OnCopyComplianceReportScreenValidationPresentIfNoOptionSelectedViewsSection ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyErrorMessages(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC1498_ReprocessClassicLISAReportLISABoxReport
	 * Test Description: Reprocess Classic LISA report as LISA Box report
	 * Script: -  	
	 *	- - Log into PCubed as Picarro Admin
	 *	- - Navigate to Picarro Administration -& Manage Customers page
	 *	- - Select a customer that does not have LISA Box 1.0 enabled and click the Edit button
	 *	- - On the Edit Customer page, check the LISA Box 1.0 checkbox and make sure that the Report Shape File box is checked and click OK
	 *	- -Navigate to Reports -& Compliance Reports and select a report for the above customer that has Classic LISA shapes
	 *	- - Click the Resubmit button
	 *	- - Once the report has completed generation, click on the Compliance Viewer button and then the View thumbnail
	 *	- - Click on the Shape File button
	 *	- - Run the Shape Files through GIS software like ArcGIS
	 * Results: - 
	 *	- - The report View should have all LISAs in the shape of boxes, not fans or circles
	 *	- - The shapes drawn by the GIS software should match those of the Compliance Report views
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1498, location = ComplianceReportDataProvider.class)
	public void TC1498_ReprocessClassicLISAReportLISABoxReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1498_ReprocessClassicLISAReportLISABoxReport ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		manageCustomerPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.createNewCustomer(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.editCustomerSelectLicensedFeatures(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnResubmitButton(EMPTY, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", NOTSET);
		assertTrue(complianceReportsPageAction.verifySSRSImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC1499_ReprocessLISABoxReportClassicLISAReport
	 * Test Description: Reprocess LISA Box report as Classic LISA report
	 * Script: -  	
	 *	- - Log into PCubed as Picarro Admin
	 *	- - Navigate to Picarro Administration -& Manage Customers page
	 *	- - Select a customer that does has LISA Box 1.0 enabled and click the Edit button
	 *	- - On the Edit Customer page, uncheck the LISA Box 1.0 checkbox and make sure that the Report Shape File box is checked and click OK
	 *	- -Navigate to Reports -& Compliance Reports and select a report for the above customer that has LISA Box shapes
	 *	- - Click the Resubmit button
	 *	- - Once the report has completed generation, click on the Compliance Viewer button and then the View thumbnail
	 *	- - Click on the Shape File button
	 *	- - Run the Shape Files through GIS software like ArcGIS
	 * Results: - 
	 *	- - The report View should have all LISAs in the shape of fans or circles, not boxes
	 *	- - The shapes drawn by the GIS software should match those of the Compliance Report views
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1499, location = ComplianceReportDataProvider.class)
	public void TC1499_ReprocessLISABoxReportClassicLISAReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1499_ReprocessLISABoxReportClassicLISAReport ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		manageCustomerPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.createNewCustomer(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.editCustomerSelectLicensedFeatures(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnResubmitButton(EMPTY, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", NOTSET);
		assertTrue(complianceReportsPageAction.verifySSRSImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC15_ReportViewThumbnailsManuallyEnteredLatLongValuesMultipleViews
	 * Test Description: Report view thumbnails with manually entered Lat/Long values and multiple views
	 * Script: -  	
	 *	- - Log in as Picarro Support
	 *	- - On Compliance Reports page, click New Compliance Report button  - Select Custom Boundary and enter coordinates manually- Go to Google Maps and determine the rectangular area for the report- Find the NE corner of the area and click on it for the Latitude and Longitude values- Repeat the above step for the SW corner  - Fill out all required fields  - For View1, under View section, select LISAs, FOV, Breadcrumb, Indications, Base Map = Map  - For View2, select LISAs, Indications, Field Notes, Gaps, Base Map = Satellite  - For View3, select FOV, Indications, Isotopic Analysis, Assets, Boundaries, Base Map = Map  - Click OK  - After the report has been generated,click on the Compliance Viewer button and then on the thumbnail for View 1  - Repeat for Views 2 and 3  - Navigate away from Compliance Reports page and return
	 * Results: - 
	 *	- - User is navigated back to Compliance Reports page and after the report has been generated,Compliance Viewer button is present in the Action column for that report- Thumbnails will be present for compliance SSRS PDF, ZIP folders and generated views
	 *	- - View1 should appear and the thumbnail should accurately reflect the view  - The thumbnails for Views 2 and 3 should accurately reflect their respective views  - The thumbnails should still be present
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC15, location = ComplianceReportDataProvider.class)
	public void TC15_ReportViewThumbnailsManuallyEnteredLatLongValuesMultipleViews(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC15_ReportViewThumbnailsManuallyEnteredLatLongValuesMultipleViews ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyPDFThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyPDFZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyViewThumbnailIsShownInComplianceViewerByViewIndex(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC1500_CopyClassicLISAReportLISABoxReport
	 * Test Description: Copy Classic LISA report as LISA Box report
	 * Script: -  	
	 *	- - Log into PCubed as Picarro Admin
	 *	- - Navigate to Picarro Administration -& Manage Customers page
	 *	- - Select a customer that does not have LISA Box 1.0 enabled and click the Edit button
	 *	- - On the Edit Customer page, check the LISA Box 1.0 checkbox and make sure that the Report Shape File box is checked and click OK
	 *	- -Navigate to Reports -& Compliance Reports and select a report for the above customer that has Classic LISA shapes
	 *	- - Click the Copy button
	 *	- - Without changing any details, click OK
	 *	- - Once the report has completed generation, click on the Compliance Viewer button and then the View thumbnail
	 *	- - Click on the Shape File button
	 *	- - Run the Shape Files through GIS software like ArcGIS
	 * Results: - 
	 *	- - The report View should have all LISAs in the shape of boxes, not fans or circles
	 *	- - The shapes drawn by the GIS software should match those of the Compliance Report views
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1500, location = ComplianceReportDataProvider.class)
	public void TC1500_CopyClassicLISAReportLISABoxReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1500_CopyClassicLISAReportLISABoxReport ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		manageCustomerPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.createNewCustomer(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.editCustomerSelectLicensedFeatures(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.copyReport(EMPTY, getReportRowID(reportDataRowID1));
		modifyComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", NOTSET);
		assertTrue(complianceReportsPageAction.verifySSRSImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC1501_CopyLISABoxReportClassicLISAReport
	 * Test Description: Copy LISA Box report as Classic LISA report
	 * Script: -  	
	 *	- - Log into PCubed as Picarro Admin
	 *	- - Navigate to Picarro Administration -& Manage Customers page
	 *	- - Select a customer that does has LISA Box 1.0 enabled and click the Edit button
	 *	- - On the Edit Customer page, uncheck the LISA Box 1.0 box and make sure that the Report Shape File box is checked and click OK
	 *	- -Navigate to Reports -& Compliance Reports and select a report for the above customer that has LISA Box shapes
	 *	- - Click the Copy button
	 *	- - Without changing any details, click OK
	 *	- - Once the report has completed generation, click on the Compliance Viewer button and then the View thumbnail
	 *	- - Click on the Shape File button
	 *	- - Run the Shape Files through GIS software like ArcGIS
	 * Results: - 
	 *	- - The report View should have all LISAs in the shape of fans or circles, not boxes
	 *	- - The shapes drawn by the GIS software should match those of the Compliance Report views
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1501, location = ComplianceReportDataProvider.class)
	public void TC1501_CopyLISABoxReportClassicLISAReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1501_CopyLISABoxReportClassicLISAReport ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		manageCustomerPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.createNewCustomer(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.editCustomerSelectLicensedFeatures(EMPTY, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.copyReport(EMPTY, getReportRowID(reportDataRowID1));
		modifyComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", NOTSET);
		assertTrue(complianceReportsPageAction.verifySSRSImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC1567_ClassicLISASurvey_MainsWithinLISAAreHighlighted
	 * Test Description: Classic LISA Survey - Mains Within LISA Are Highlighted
	 * Script: -  	
	 *	- - Log in as Picarro Admin
	 *	- - On the Compliance Reports page, click the New Compliance Report button
	 *	- - Fill out the required fields
	 *	- - Select a survey that has LISAs and Mains assets touching the LISAs
	 *	- - In the Views section, select LISAs and Assets
	 *	- - Click OK
	 *	- - When the report has finished generating, click on the thumbnail preview button and download the Compliance ZIP PDF file
	 *	- - Extract the contents of the zip
	 * Results: - 
	 *	- - In the report view, the segment of Main contained within the LISA should be highlighted. An additional segment of that main up to 50' from the vertex of the LISA should be highlighted as well.
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1567, location = ComplianceReportDataProvider.class)
	public void TC1567_ClassicLISASurvey_MainsWithinLISAAreHighlighted(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1567_ClassicLISASurvey_MainsWithinLISAAreHighlighted ...");
		
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
	 * Test Case ID: TC1570_ClassicLISASurvey_MainsOutsideLISAAreNotHighlightedUnlessWithin50OfLISAVertex
	 * Test Description: Classic LISA Survey - Mains Outside LISA Are Not Highlighted Unless Within 50' of a LISA vertex
	 * Script: -  	
	 *	- - Log in as Picarro Admin
	 *	- - On the Compliance Reports page, click the New Compliance Report button
	 *	- - Fill out the required fields
	 *	- - Select a survey that has LISAs and Mains assets not touching the LISAs
	 *	- - In the Views section, select LISAs and Assets
	 *	- - Click OK
	 *	- - When the report has finished generating, click on the thumbnail preview button and download the Compliance ZIP PDF file
	 *	- - Extract the contents of the zip
	 * Results: - 
	 *	- - In the report view, any segment of Main not contained within the LISA should not be highlighted unless the segment is within 50 feet of the vertex of the LISA
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1570, location = ComplianceReportDataProvider.class)
	public void TC1570_ClassicLISASurvey_MainsOutsideLISAAreNotHighlightedUnlessWithin50OfLISAVertex(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1570_ClassicLISASurvey_MainsOutsideLISAAreNotHighlightedUnlessWithin50OfLISAVertex ...");
		
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
	 * Test Case ID: TC1572_ClassicLISASurvey_ServicesTouchingLISAAreHighlighted
	 * Test Description: Classic LISA Survey - Services Touching LISA are Highlighted
	 * Script: -  	
	 *	- - Log in as Picarro Admin
	 *	- - On the Compliance Reports page, click the New Compliance Report button
	 *	- - Fill out the required fields
	 *	- - Select a survey that has LISAs with Services touching the LISAs
	 *	- - In the Views section, select LISAs and Assets
	 *	- - Click OK
	 *	- - When the report has finished generating, click on the thumbnail preview button and download the Compliance ZIP PDF file
	 *	- - Extract the contents of the zip
	 * Results: - 
	 *	- - In the report view, any Service assets that are contained in or touch a LISA box should be highlighted along its entire length. The segment of main to which that service is connected should not be highlighted unless it is also within the LISA
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1572, location = ComplianceReportDataProvider.class)
	public void TC1572_ClassicLISASurvey_ServicesTouchingLISAAreHighlighted(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1572_ClassicLISASurvey_ServicesTouchingLISAAreHighlighted ...");
		
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
	 * Test Case ID: TC1577_GapTableShouldNotPresentNewOrCopyComplianceReportScreenClassicGapsFeatureCustomers
	 * Test Description: Gap table should not be present on new or copy compliance report screen for classic gaps feature customers
	 * Script: -  	
	 *	- - Log in as Customer admin or supervisor user
	 *	- - Navigate to Reports -& Compliance -& New Compliance Report
	 *	- - Click on Copy button of above generate report
	 * Results: - 
	 *	- - Gap table check box should not be present in Optional tabular PDF content section
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1577, location = ComplianceReportDataProvider.class)
	public void TC1577_GapTableShouldNotPresentNewOrCopyComplianceReportScreenClassicGapsFeatureCustomers(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1577_GapTableShouldNotPresentNewOrCopyComplianceReportScreenClassicGapsFeatureCustomers ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnCopyButton(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyGapsTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC1578_GapTableShouldNotPresentNewOrCopyAssessmentReportScreenClassicGapsFeatureCustomers
	 * Test Description: Gap table should not be present on new or copy assessment report screen for classic gaps feature customers
	 * Script: -  	
	 *	- - Log in as Customer admin or supervisor user
	 *	- - Navigate to Reports -& Assessment -& New Assessment Report
	 *	- - Click on Copy button of above generate report
	 * Results: - 
	 *	- - Gap table check box should not be present in Optional tabular PDF content section
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1578, location = ComplianceReportDataProvider.class)
	public void TC1578_GapTableShouldNotPresentNewOrCopyAssessmentReportScreenClassicGapsFeatureCustomers(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1578_GapTableShouldNotPresentNewOrCopyAssessmentReportScreenClassicGapsFeatureCustomers ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnCopyButton(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyGapsTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC1611_CheckMaxCharactersAllowedNewComplianceReportScreen
	 * Test Description: Check max characters allowed on New Compliance report screen
	 * Script: -  	
	 *	- - Log in as customer user (non-Picarro user)
	 *	- - On Home Page, and click Reports -& Compliance
	 *	- - Click on 'New Compliance Report' button 
	 *	- - Provide more than 450 characters in Report Title field
	 *	- - Provide more than 50 characters in User Name field
	 *	- - Provide more than 20 characters in Tag field
	 *	- - Provide &0 and &1 value in FOV Opacity field
	 *	- - Provide &0 and &1 value in LISA Opacity field
	 *	- - Provide &8.5 and &40 value in PDF Width field
	 *	- - Provide &8.5 and &40 value in PDF Height field
	 *	- - Provide more than 450 characters in Views name field
	 *	- - Click OK
	 * Results: - 
	 *	- User cannot enter more than specified characters for fields and user friendly message having limit of characters is displayed
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1611, location = ComplianceReportDataProvider.class)
	public void TC1611_CheckMaxCharactersAllowedNewComplianceReportScreen(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1611_CheckMaxCharactersAllowedNewComplianceReportScreen ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyErrorMessages(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC1612_CheckMaxCharactersAllowedCopyComplianceReportScreen
	 * Test Description: Check max characters allowed on Copy Compliance report screen
	 * Script: -  	
	 *	- - On Home Page, and click Reports -& Compliance
	 *	- - Click on Copy button 
	 *	- - Provide more than 450 characters in Report Title field
	 *	- - Provide more than 50 characters in User Name field
	 *	- - Provide more than 20 characters in Tag field
	 *	- - Provide &0 and &1 value in FOV Opacity field
	 *	- - Provide &0 and &1 value in LISA Opacity field
	 *	- - Provide &8.5 and &40 value in PDF Width field
	 *	- - Provide &8.5 and &40 value in PDF Height field
	 *	- - Provide more than 450 characters in Views name field
	 *	- - Click OK
	 * Results: - 
	 *	- User cannot enter more than specified characters for fields and user friendly message having limit of characters is displayed
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1612, location = ComplianceReportDataProvider.class)
	public void TC1612_CheckMaxCharactersAllowedCopyComplianceReportScreen(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1612_CheckMaxCharactersAllowedCopyComplianceReportScreen ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnCopyButton(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyErrorMessages(EMPTY, getReportRowID(reportDataRowID1)));
	}
}
