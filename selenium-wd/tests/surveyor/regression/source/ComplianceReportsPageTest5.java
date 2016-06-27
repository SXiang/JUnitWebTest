package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG2;
import common.source.Log;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.BaseReportsPageTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.BaseReportsPageActionTest.ReportTestRunMode;
import surveyor.scommon.source.ComplianceReportsPage.ComplianceReportButtonType;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SurveyModeFilter;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest5 extends BaseReportsPageActionTest {

	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;

	private static HomePageActions homePageAction;
	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static TestEnvironmentActions testEnvironmentAction;
	private static ManageCustomerPageActions manageCustomerPageAction;

	private static ComplianceReportsPage complianceReportsPage;
	private static MeasurementSessionsPage measurementSessionsPage;

	@BeforeClass
	public static void beforeTestClass() throws Exception {
		initializePageActions();

		homePageAction = new HomePageActions(driver, baseURL, testSetup);
		loginPageAction = new LoginPageActions(driver, baseURL, testSetup);
		testEnvironmentAction = new TestEnvironmentActions();
		measurementSessionsPage = new MeasurementSessionsPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  measurementSessionsPage);
	}

	/**
	 * Initializes the page action objects.
	 * @throws Exception 
	 */
	protected static void initializePageActions() throws Exception {
		loginPageAction = new LoginPageActions(driver, baseURL, testSetup);
		homePageAction = new HomePageActions(driver, baseURL, testSetup);
		complianceReportsPageAction = new ComplianceReportsPageActions(driver, baseURL, testSetup);
		testEnvironmentAction = new TestEnvironmentActions();

		// To run the test locally in UnitTest mode uncomment this line.
		//setTestRunMode(ReportTestRunMode.UnitTestRun);

		if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			complianceReportsPageAction.fillWorkingDataForReports(getUnitTestReportRowID());
		}
	}

	/**
	 * Test Case ID: TC204_GenerateComplianceReportHavingSpecialCharactersReportTitleUsingCopyFunctionality
	 * Test Description: Generate compliance report having special characters in report title using copy functionality
	 * Script: -  	
	 *	Pre-requisite:
	 *	- Generate Compliance report with title having special characters like ', ",
	 *	- , #, "<> etc
	 *	- On Home Page, click Reports -> Compliance -> click on Copy button
	 *	- Click on OK and click Download icon
	 * Results: - 
	 *	- - Report title should not change
	 *	- - Report should be generated successfully
	 *	- - User should be allowed to download the report successfully
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC204, location = ComplianceReportDataProvider.class)
	public void TC204_GenerateComplianceReportHavingSpecialCharactersReportTitleUsingCopyFunctionality(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC204_GenerateComplianceReportHavingSpecialCharactersReportTitleUsingCopyFunctionality ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.copyReport(ComplianceReportsPageActions.workingDataRow.title, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.getComplianceReportsPage().waitForCopyReportPagetoLoad();
		complianceReportsPageAction.clickOnOKButton(ComplianceReportsPageActions.workingDataRow.title, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyPDFZipFilesAreCorrect(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC210_GenerateReportTryDeleteSurveyUsedWhileGeneratingReport
	 * Test Description: Generate report and try to delete the survey used while generating the report
	 * Script: -  	
	 *	- On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- Provide report title, timezone : PST, Survey Mode: Standard, Exclusion Radius:0
	 *	- Select Indications and isotopic table
	 *	- Provide lat long values
	 *	- Include the survey and note down the survey tag
	 *	- Add View 1: select Isocapture, Field Notes, Assets and Boundaries. Base Map Value : Map
	 *	- Click on OK and click Download icon
	 *	- Navigate to Driving Surveys page and search the survey used while generating the report
	 *	- Click on Delete survey button
	 * Results: - 
	 *	- - Report should be generated and user can download the report successfully
	 *	- - Show notification that survey is used in generated report or Delete Survey button itself is unavailable
	 */
	@Ignore  //DE2073
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC210, location = ComplianceReportDataProvider.class)
	public void TC210_GenerateReportTryDeleteSurveyUsedWhileGeneratingReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC210_GenerateReportTryDeleteSurveyUsedWhileGeneratingReport ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		TestEnvironmentActions.generateSurveyForUser(6, 9, 51, 60);
		
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerCloseButton(EMPTY, getReportRowID(reportDataRowID1));

		measurementSessionsPage.open();
		measurementSessionsPage.performSearch(PICADMNSTDTAG2);
		measurementSessionsPage.clickOnFirstSurveyDeleteLink();
		assertTrue(homePage.getReturnHomePage().isEnabled());
		assertTrue(homePage.getReturnHomePage().isDisplayed());
		homePage.getReturnHomePage().click();
	}	

	/**
	 * Test Case ID: TC227_S1ReportSurveyModeShouldNotPresentNewCopyComplianceReportScreens
	 * Test Description: S1 report and survey mode should not be present on New and Copy Compliance report screens
	 * Script: -  	
	 *	- On Home Page, navigate to Report -> Compliance -> New Compliance Report
	 *	- Change the report modes and check the survey mode filters
	 *	- Click on Copy button of existing report
	 *	- Change the report modes and check the survey mode filter
	 * Results: - 
	 *	- - S1 Report mode should not be present and by default Standard mode should be selected on new compliance report screen
	 *	- - Survey mode filter should not show S1 mode
	 *	- - S1 report mode should not be present on Copy compliance screen
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC227, location = ComplianceReportDataProvider.class)
	public void TC227_S1ReportSurveyModeShouldNotPresentNewCopyComplianceReportScreens(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC227_S1ReportSurveyModeShouldNotPresentNewCopyComplianceReportScreens ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openNewReportPage(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.selectReportMode("Standard", getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifySurveyModeFiltersByReportMode("Standard", getReportRowID(reportDataRowID1)));
		complianceReportsPageAction.selectReportMode("RR", getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifySurveyModeFiltersByReportMode("RR", NOTSET));
		complianceReportsPageAction.selectReportMode("Manual", getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifySurveyModeFiltersByReportMode("Manual", NOTSET));
		}

	/**
	 * Test Case ID: TC1505_PercentCoverageForecastFeaturesPermissionCustomer_NewComplianceReportGeneration
	 * Test Description: Percent Coverage Forecast features permission to customer - New Compliance report generation
	 * Script: -  	
	 *	- Log in as Picarro Admin
	 *	- On Manage Customers page, select a customer that does not have Percent Coverage Forecast permission enabled and click the "Edit" button 
	 *	- Confirm that the "Account Enabled" box is checked and check the Percent Coverage Forecast
	 *	- Click OK 
	 *	- Login as Customer User (e.g. Customer admin user)
	 *	- On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- Time Zone : PST, Survey Mode: Standard, Exclusion Radius: 0
	 *	- Add 2 or 3 surveys with different tag values
	 *	- Select Customer boundary and select any Plat 
	 *	- Select Indication table, Isotopic Analysis table
	 *	- Select Percent Coverage Forecast check box
	 *	- Add View with base map value: map
	 *	- Click on OK and click Compliance Viewer button
	 *	- Download PDF, ZIP (PDF)
	 * Results: - 
	 *	- - Percent Coverage Forecast should be present in SSRS PDF
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)- Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 *  - - Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Ignore  //Need to edit and enable/disable customer 
	public void TC1505_PercentCoverageForecastFeaturesPermissionCustomer_NewComplianceReportGeneration() throws Exception {
		Log.info("\nRunning TC1505_PercentCoverageForecastFeaturesPermissionCustomer_NewComplianceReportGeneration ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}

	/**
	 * Test Case ID: TC1506_PercentCoverageForecastFeaturesPermissionCustomer_CopyComplianceReportGeneration
	 * Test Description: Percent Coverage Forecast features permission to customer - Copy Compliance report generation
	 * Script: -  	
	 *	- Log in as Picarro Admin
	 *	- On Manage Customers page, select a customer that does not have Percent Coverage Forecast permission enabled and click the "Edit" button 
	 *	- Confirm that the "Account Enabled" box is checked and check the Percent Coverage Forecast
	 *	- Click OK 
	 *	- Login as Customer User (e.g. Customer Supervisor user)
	 *	- On the Compliance Reports page, click on Copy button of above generated report 
	 *	- Click on Compliance Viewer button
	 *	- Dowload SSRS PDF and Compliance PDF ZIP
	 * Results: - 
	 *	- - Percent Coverage Forecast should be present in SSRS PDF
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)- Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 *  - - Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Ignore //Need to edit and enable/disable customer
	public void TC1506_PercentCoverageForecastFeaturesPermissionCustomer_CopyComplianceReportGeneration() throws Exception {
		Log.info("\nRunning TC1506_PercentCoverageForecastFeaturesPermissionCustomer_CopyComplianceReportGeneration ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}

	/**
	 * Test Case ID: TC1507_PercentCoverageForecastFeaturesPermissionPicarro_ReprocessComplianceReportGenerationPicarroAdmin
	 * Test Description: Percent Coverage Forecast features permission to Picarro - Reprocess Compliance report generation as Picarro Admin
	 * Script: -  	
	 *	- Log in as Picarro Admin
	 *	- On Manage Customers page, select a customer (Picarro Admin) that does not have Percent Coverage Forecast permission enabled and click the "Edit" button 
	 *	- Confirm that the "Account Enabled" box is checked and check the Percent Coverage Forecast
	 *	- Click OK 
	 *	- Logout an Login again as Picarro Admin 
	 *	- On the Compliance Reports page, click on Resubmit button of above generated report 
	 *	- Click on Compliance Viewer button
	 *	- Dowload SSRS PDF and Compliance PDF ZIP
	 * Results: - 
	 *	- - Percent Coverage Forecast should be present in SSRS PDF
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)- Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 *  - - Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Ignore //Need to edit and enable/disable customer
	public void TC1507_PercentCoverageForecastFeaturesPermissionPicarro_ReprocessComplianceReportGenerationPicarroAdmin() throws Exception {
		Log.info("\nRunning TC1507_PercentCoverageForecastFeaturesPermissionPicarro_ReprocessComplianceReportGenerationPicarroAdmin ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}

	/**
	 * Test Case ID: TC1508_GenrateNewComplianceReportPicarroSupportUserIncludePercentCoverageForecast
	 * Test Description: Genrate New Compliance report as Picarro Support user and include Percent Coverage Forecast
	 * Script: -  	
	 *	- Login as Picarro Support user
	 *	- On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- Time Zone : CST, Survey Mode: Standard
	 *	- Add 2 or 3 surveys with different tag values
	 *	- Select Customer boundary and select any Plat 
	 *	- Select Indication table, Isotopic Analysis table
	 *	- Select Percent Coverage Forecast check box
	 *	- Add View with base map value: map
	 *	- Click on OK and click Compliance Viewer button
	 *	- Download PDF, ZIP (PDF)
	 * Results: - 
	 *	- - Percent Coverage Forecast should be present in SSRS PDF
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)- Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 *  - - Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Ignore  //Need actions to choose customer boundary
	public void TC1508_GenrateNewComplianceReportPicarroSupportUserIncludePercentCoverageForecast() throws Exception {
		Log.info("\nRunning TC1508_GenrateNewComplianceReportPicarroSupportUserIncludePercentCoverageForecast ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}

	/**
	 * Test Case ID: TC1509_RemovePercentCoverageForecastFeaturePermissionFromExistingCustomer_NewComplianceReportScreenVerification
	 * Test Description: Remove Percent Coverage Forecast feature permission from existing customer - New Compliance report screen verification
	 * Script: -  	
	 *	- Log in as Picarro Admin
	 *	- On Manage Customers page, select a customer that has Percent Coverage Forecast permission options enabled and click the "Edit" button (eg. PG&E's)
	 *	- Confirm that the "Account Enabled" box is checked and uncheck Percent Coverage Forecast permission check box
	 *	- Click OK
	 *	- Log in as Customer admin or supervisor user
	 *	- Navigate to Reports -> Compliance
	 *	- Click on New Compliance Report button
	 * Results: - 
	 *	- - Percent Coverage Forecast check box is not present on UI
	 */
	@Ignore //Need to edit and enable/disable customer
	public void TC1509_RemovePercentCoverageForecastFeaturePermissionFromExistingCustomer_NewComplianceReportScreenVerification() throws Exception {
		Log.info("\nRunning TC1509_RemovePercentCoverageForecastFeaturePermissionFromExistingCustomer_NewComplianceReportScreenVerification ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}

	/**
	 * Test Case ID: TC1510_RemovePercentCoverageForecastFeaturePermissionFromExistingCustomer_CopyComplianceReportVerification
	 * Test Description: Remove Percent Coverage Forecast feature permission from existing customer - Copy Compliance report verification
	 * Script: -  	
	 *	- Log in as Picarro Admin
	 *	- On Manage Customers page, select a customer that has Percent Coverage Forecast permission options enabled and click the "Edit" button (eg. PG&E's)
	 *	- Confirm that the "Account Enabled" box is checked and uncheck Percent Coverage Forecast
	 *	- Click OK
	 *	- Log in as Customer supervisor user
	 *	- Go to compliance report page and click on Copy button of above generated report. Click OK
	 *	- Click on Compliance Viewer button and download SSRS PDF and Compliance ZIP PDF
	 * Results: - 
	 *	- - On SSRS, Percent Coverage Forecast option should not appear under Show Coverage section and Coverage Forecast section should not b present
	 */
	@Ignore //Need to edit and enable/disable customer
	public void TC1510_RemovePercentCoverageForecastFeaturePermissionFromExistingCustomer_CopyComplianceReportVerification() throws Exception {
		Log.info("\nRunning TC1510_RemovePercentCoverageForecastFeaturePermissionFromExistingCustomer_CopyComplianceReportVerification ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}

	/**
	 * Test Case ID: TC1511_RemovePercentCoverageForecastFeaturePermissionFromExistingCustomer_ReprocessComplianceReportVerification
	 * Test Description: Remove Percent Coverage Forecast feature permission from existing customer - Reprocess Compliance report verification
	 * Script: -  	
	 *	- Log in as Picarro Admin
	 *	- On Manage Customers page, select a customer that has Percent Coverage Forecast permission options enabled and click the "Edit" button (eg. PG&E's)
	 *	- Confirm that the "Account Enabled" box is checked and uncheck Percent Coverage Forecast permission
	 *	- Click OK
	 *	- Log in as Picarro Support user
	 *	- Go to compliance report page and click on Resubmit button of above generated report. Click OK
	 *	- Click on Compliance Viewer button and download SSRS PDF and Compliance ZIP PDF
	 * Results: - 
	 *	- - Percent Coverage Forecast related data should not be present in SSRS PDF
	 */
	@Ignore //Need to edit and enable/disable customer
	public void TC1511_RemovePercentCoverageForecastFeaturePermissionFromExistingCustomer_ReprocessComplianceReportVerification() throws Exception {
		Log.info("\nRunning TC1511_RemovePercentCoverageForecastFeaturePermissionFromExistingCustomer_ReprocessComplianceReportVerification ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}

	/**
	 * Test Case ID: TC1522_CopyButtonPresentCanceledFailedComplianceReportCustomerSupervisorUser
	 * Test Description: Copy button present for canceled/failed compliance report as customer supervisor user
	 * Script: -  	
	 *	- Log in as Customer Supervisor user (eg. PGE or CNP or Atmos)
	 *	- Generate a new compliance report
	 *	- Click on Cancel button present next to in-progress report
	 *	- Click on Copy button and click OK
	 * Results: - 
	 *	- - Report generation action is canceled. Copy button is present next to canceled report
	 *	- - Report should be generated successfully and user is able to download the PDFs
	 */
	@Test //Need customer supervisor assets  (Need to update test case with customer supervisor)
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1522, location = ComplianceReportDataProvider.class)
	public void TC1522_CopyButtonPresentCanceledFailedComplianceReportCustomerSupervisorUser(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1522_CopyButtonPresentCanceledFailedComplianceReportCustomerSupervisorUser ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyPageLoaded(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.cancelInProgressReport(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.getComplianceReportsPage().waitForCopyReportPagetoLoad();
		complianceReportsPageAction.copyReport(ComplianceReportsPageActions.workingDataRow.title, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.getComplianceReportsPage().waitForCopyReportPagetoLoad();
		complianceReportsPageAction.clickOnOKButton(ComplianceReportsPageActions.workingDataRow.title, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID2));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID2));
		assertTrue(complianceReportsPageAction.verifyPDFZipFilesAreCorrect(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC1523_CopyButtonPresentCanceledFailedComplianceReportCustomerAdminUser
	 * Test Description: Copy button present for canceled/failed compliance report as customer admin user
	 * Script: -  	
	 *	- Log in as Customer admin user (eg. PGE or CNP or Atmos)
	 *	- Generate a new compliance report
	 *	- Click on Cancel button present next to in-progress report
	 *	- Click on Copy button and click OK
	 * Results: - 
	 *	- - Report generation action is canceled. Copy button is present next to canceled report
	 *	- - Report should be generated successfully and user is able to download the PDFs
	 */
	@Test //customer admin does not have asset and boundary  (Need to update test case to customer admin)
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1523, location = ComplianceReportDataProvider.class)
	public void TC1523_CopyButtonPresentCanceledFailedComplianceReportCustomerAdminUser(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1523_CopyButtonPresentCanceledFailedComplianceReportCustomerAdminUser...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyPageLoaded(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.cancelInProgressReport(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.getComplianceReportsPage().waitForCopyReportPagetoLoad();
		complianceReportsPageAction.copyReport(ComplianceReportsPageActions.workingDataRow.title, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.getComplianceReportsPage().waitForCopyReportPagetoLoad();
		complianceReportsPageAction.clickOnOKButton(ComplianceReportsPageActions.workingDataRow.title, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyPDFZipFilesAreCorrect(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC1524_ChangeReportModeGenerateComplianceReportUsingCopyFunctionalityIn_ProgressReportCustomerSupervisorUser
	 * Test Description: Change report mode and generate compliance report using Copy functionality for in-progress report as customer supervisor user
	 * Script: -  	
	 *	- Log in as Customer Supervisor user (eg. PGE or CNP or Atmos)
	 *	- Generate a new compliance report in Rapid Response mode
	 *	- Click on Copy button when report is in in-progress state
	 *	- Change the report mode from RR to Standard
	 *	- Click OK
	 *	- Download the report
	 * Results: - 
	 *	- - Report will be in in-progress state and user can see Copy and Cancel Report buttons
	 *	- - Change Report Mode dialog is present.
	 *	- - Report mode is changed as specified by user and all surveys are deleted
	 *	- - Report is generated successfully in Standard mode
	 */
	@Test  //customer supervisor does not have asset and boundary (Need to update test case to customer supervisor)
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1524, location = ComplianceReportDataProvider.class)
	public void TC1524_ChangeReportModeGenerateComplianceReportUsingCopyFunctionalityIn_ProgressReportCustomerSupervisorUser(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1524_ChangeReportModeGenerateComplianceReportUsingCopyFunctionalityIn_ProgressReportCustomerSupervisorUser...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyPageLoaded(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyCancelButtonIsDisplayed(EMPTY,getReportRowID(reportDataRowID1)));
		complianceReportsPageAction.copyInProgressReport(EMPTY,getReportRowID(reportDataRowID1));
		modifyComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID2));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID2));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyPDFZipFilesAreCorrect(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC1525_ChangeReportModeGenerateComplianceReportUsingCopyFunctionalityOfCanceledFailedReportCustomerSupervisorUser
	 * Test Description: Change report mode and generate compliance report using Copy functionality of canceled/failed report as customer supervisor user
	 * Script: -  	
	 *	- Log in as Customer Supervisor user (eg. PGE or CNP or Atmos)
	 *	- Generate a new compliance report in Rapid Response mode
	 *	- Click on Cancel button
	 *	- Click on Copy button when report
	 *	- Change the report mode from RR to Standard
	 *	- Click OK
	 *	- Download the report
	 * Results: - 
	 *	- - Report generation is canceled and Copy button is present
	 *	- - Change Report Mode dialog is present.
	 *	- - Report mode is changed as specified by user and all surveys are deleted
	 *	- - Report is generated successfully in Standard mode
	 */
	//TODO:  Need to implement method to verify "Report mode is changed as specified by user and all surveys are deleted" which is tracked by DE1999
	@Test  //customer supervisor does not have asset and boundary (Need to update test case)
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1525, location = ComplianceReportDataProvider.class)
	public void TC1525_ChangeReportModeGenerateComplianceReportUsingCopyFunctionalityOfCanceledFailedReportCustomerSupervisorUser(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1525_ChangeReportModeGenerateComplianceReportUsingCopyFunctionalityOfCanceledFailedReportCustomerSupervisorUser...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.verifyPageLoaded(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.cancelInProgressReport(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.getComplianceReportsPage().waitForCopyReportPagetoLoad();
		complianceReportsPageAction.copyReport(ComplianceReportsPageActions.workingDataRow.title, getReportRowID(reportDataRowID1));
		modifyComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID2));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID2));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID2));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID2));
		assertTrue(complianceReportsPageAction.verifyPDFZipFilesAreCorrect(EMPTY, NOTSET));
	}

	/**
	 * Test Case ID: TC1532_GenerateNewComplianceReportCustomerUtilityAdminUserAssetsAreNotSelected
	 * Test Description: Generate new Compliance report as customer utility admin user and assets are not selected
	 * Script: -  	
	 *	- Log in as customer util admin
	 *	- On the Compliance Reports page, generate the report by providing all required details. Make sure Assets are not selected in views
	 *	- Click on Compliance Viewer button
	 *	- Download SSRS PDF, Compliance PDF, ShapeFile and MetaData ZIP
	 * Results: - 
	 *	- - Assessment report SSRS PDF should have survey details, view details
	 *	- - Gap Table should be present in SSRS PDF
	 *	- - Maps should have Breadcrumb, FOV and gap data
	 *	- - Shapefile should have Lisa, FOV, Breadcrumb and Gaps should be present. PipesIntersectingGaps file should not be present
	 *	- - Report.csv, ReportSurvey.csv, ReportLisa.csv and ReportGap.csv should be present
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1532, location = ComplianceReportDataProvider.class)
	public void TC1532_GenerateNewComplianceReportCustomerUtilityAdminUserAssetsAreNotSelected(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1532_GenerateNewComplianceReportCustomerUtilityAdminUserAssetsAreNotSelected...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyPDFZipFilesAreCorrect(EMPTY, NOTSET));
		assertTrue(complianceReportsPageAction.verifySSRSDrivingSurveyTableInfo(EMPTY, NOTSET));
		assertTrue(complianceReportsPageAction.verifySSRSViewsTableInfo(EMPTY, NOTSET));
		assertTrue(complianceReportsPageAction.verifyGapsTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
		
		complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
		//The Commented step is not working.  Steven has bug open on his plate.  
		//assertTrue(complianceReportsPageAction.verifyLISASMetaDataFile(EMPTY, getReportRowID(reportDataRowID1)));
		
		//DE2057
		//assertTrue(complianceReportsPageAction.verifyReportSurveyMetadataFile(EMPTY, getReportRowID(reportDataRowID1)));
		
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC1581_ProvideGapGrid10PrivilegeExistingCustomerGenerateComplianceReport
	 * Test Description: Provide Gap Grid 1.0 privilege to existing customer and generate compliance report
	 * Script: -  	
	 *	- Log in to application as Picarro Admin
	 *	- Navigate to Picarro Administration -> Manage Customers page
	 *	- Select a customer and click the Edit button
	 *	- Enable Gap Grid 1.0 feature
	 *	- Click OK
	 *	- Log in to application as customer supervisor user
	 *	- Navigate to Reports -> Compliance Reports and click on the New Compliance Report button
	 *	- Provide all required details and select Gaps in the Views section
	 *	- Select Gap table
	 *	- Click OK
	 *	- Once the report has completed generation, click on the Report Viewer button- Download PDF, Views PDF, Compliance ZIP (PDF), Compliance ZIP (SHAPE) and Compliance ZIP (META)
	 * Results: - 
	 *	- SSRS will have the Gap table. The Gap Table will have numbers corresponding to the gaps in the Compliance View with a check box next to each number. The numbers will run sequentially from left to right and then top to bottom. The numbers in the table should exactly match the number of gaps in the View
	 *	- SSRS Gap table should not show Gaps which are completely covered by FoV and LISA
	 *	- View will have Gaps information
	 *	- Gaps shape file should only have gaps information
	 *	- ReportGaps.csv file in meta data will have gaps information and numbering present
	 */
	@Ignore//Need to edit customer to enable/disable
	public void TC1581_ProvideGapGrid10PrivilegeExistingCustomerGenerateComplianceReport() throws Exception {
		Log.info("\nRunning TC1581_ProvideGapGrid10PrivilegeExistingCustomerGenerateComplianceReport ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}

	/**
	 * Test Case ID: TC1583_ProvideGapGrid10PrivilegeExistingCustomerGenerateComplianceReportUsingCopyFunctionality
	 * Test Description: Provide Gap Grid 1.0 privilege to existing customer and generate compliance report using copy functionality
	 * Script: -  	
	 *	- Log in to application as Picarro Admin
	 *	- Navigate to Picarro Administration -> Manage Customers page
	 *	- Select a customer and click the Edit button
	 *	- Check Gap Grid 1.0 check box
	 *	- Click OK
	 *	- Log in to application as customer admin user
	 *	- Navigate to Reports -> Compliance Reports and click on Copy button of above report
	 *	- Provide all required details and select Gaps in the Views section
	 *	- Select Gap table
	 *	- Click OK
	 *	- Once the report has completed generation, click on the Report Viewer button
	 *	- Download PDF, Views PDF, Compliance ZIP (PDF), Compliance ZIP (SHAPE) and Compliance ZIP (META)
	 * Results: - 
	 *	- SSRS will have the Gap table. The Gap Table will have numbers corresponding to the gaps in the Compliance View with a check box next to each number. The numbers will run sequentially from left to right and then top to bottom. The numbers in the table should exactly match the number of gaps in the View
	 *	- SSRS Gap table should not show Gaps which are completely covered by FoV and LISA
	 *	- View1 will have Gaps information
	 *	- View2 will not have gaps information
	 *	- Gaps shape file should only have gaps information
	 *	- ReportGaps.csv file in meta data will have gaps information and numbering present
	 */
	@Ignore //Need to edit customer to enable/disable
	public void TC1583_ProvideGapGrid10PrivilegeExistingCustomerGenerateComplianceReportUsingCopyFunctionality() throws Exception {
		Log.info("\nRunning TC1583_ProvideGapGrid10PrivilegeExistingCustomerGenerateComplianceReportUsingCopyFunctionality ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}

	/**
	 * Test Case ID: TC1589_ProvideGapGrid10PrivilegeExistingCustomerReprocessComplianceReport
	 * Test Description: Provide Gap Grid 1.0 privilege to existing customer and reprocess compliance report
	 * Script: -  	
	 *	- Log in to application as Picarro Admin
	 *	- Navigate to Picarro Administration -> Manage Customers page
	 *	- Select a customer and click the Edit button
	 *	- Check Gap Grid 1.0 check box
	 *	- Click OK
	 *	- Log out and log in the application again as Picarro Admin
	 *	- Navigate to Reports -> Compliance Reports and click on Reubmit button of above report
	 *	- Once the report has completed generation, click on the Report Viewer button
	 *	- Download PDF, Views PDF, Compliance ZIP (PDF), Compliance ZIP (SHAPE) and Compliance ZIP (META)
	 * Results: - 
	 *	- SSRS will have not have Gap table
	 *	- View will have Gaps information. The numbers will run sequentially from left to right and then top to bottom
	 *	- Gaps shape file should only have gaps information
	 *	- ReportGaps.csv file in meta data will have gaps information and numbering present
	 */ 
	@Ignore//Need to edit customer to enable/disable
	public void TC1589_ProvideGapGrid10PrivilegeExistingCustomerReprocessComplianceReport() throws Exception {
		Log.info("\nRunning TC1589_ProvideGapGrid10PrivilegeExistingCustomerReprocessComplianceReport ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}

	/**
	 * Test Case ID: TC1595_RemoveGapGrid10FeatureFromExistingCustomerGenerateNewComplianceReport
	 * Test Description: Remove Gap Grid 1.0 feature from existing customer and generate new compliance report
	 * Script: -  	
	 *	- Log in to application as Picarro Admin
	 *	- Navigate to Picarro Administration -> Manage Customers page
	 *	- Select a customer that has Gap Grid 1.0 feature enabled and click the Edit button
	 *	- Un-select Gap grid 1.0 check box and click OK
	 *	- Log in as customer supervisor user
	 *	- Navigate to Reports -> Compliance and click on New Compliance Report button
	 *	- Provide all required details and select Gaps in the Views section
	 *	- Click OK
	 *	- Once the report has completed generation, click on the Report Viewer button
	 *	- Download PDF,  Views PDF, Compliance ZIP (PDF), Compliance ZIP (SHAPE) and Compliance ZIP (META)
	 * Results: - 
	 *	- - SSRS will not have Gap table
	 *  - - View will have Gaps information without any grids or numbering
	 *	- - Gaps shape file will only have gaps information
	 *	- - ReportGaps.csv meta data file will have only single row with gaps information
	 */
	@Ignore //Need to edit customer to enable/disable
	public void TC1595_RemoveGapGrid10FeatureFromExistingCustomerGenerateNewComplianceReport() throws Exception {
		Log.info("\nRunning TC1595_RemoveGapGrid10FeatureFromExistingCustomerGenerateNewComplianceReport ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}

	/**
	 * Test Case ID: TC1597_RemoveGapGrid10FeatureFromExistingCustomerGenerateComplianceReportUsingCopyFunctionalty
	 * Test Description: Remove Gap Grid 1.0 feature from existing customer and generate compliance report using copy functionalty
	 * Script: -  	
	 *	- Log in to application as Picarro Admin
	 *	- Navigate to Picarro Administration -> Manage Customers page
	 *	- Select a customer that has Gap Grid 1.0 feature enabled and click the Edit button
	 *	- Un-select Gap grid 1.0 check box and click OK
	 *	- Log in as customer admin user
	 *	- Navigate to Reports -> Compliance and click on Copy button of above report
	 *	- Click OK
	 *	- Once the report has completed generation, click on the Report Viewer button
	 *	- Download PDF,  Views PDF, Compliance ZIP (PDF), Compliance ZIP (SHAPE) and Compliance ZIP (META)
	 * Results: - 
	 *	- - SSRS will not have Gap table
	 *  - - View will have Gaps information without any grids and numbers
	 *	- - Gaps shape file will only have gaps information
	 *	- - ReportGaps.csv meta data file will have only single row with gaps information
	 */
	@Ignore //Need to edit customer to enable/disable
	public void TC1597_RemoveGapGrid10FeatureFromExistingCustomerGenerateComplianceReportUsingCopyFunctionalty() throws Exception {
		Log.info("\nRunning TC1597_RemoveGapGrid10FeatureFromExistingCustomerGenerateComplianceReportUsingCopyFunctionalty ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}

	/**
	 * Test Case ID: TC1599_RemoveGapGrid10FeatureFromExistingCustomerReprocessExistingComplianceReport
	 * Test Description: Remove Gap Grid 1.0 feature from existing customer and reprocess the existing compliance report
	 * Script: -  	
	 *	- Log in to application as Picarro Admin
	 *	- Navigate to Picarro Administration -> Manage Customers page
	 *	- Select a customer that has Gap Grid 1.0 feature enabled and click the Edit button
	 *	- Un-select Gap grid 1.0 check box and click OK
	 *	- Log out and Log in again as Picarro Admin
	 *	- Navigate to Reports -> Compliance and click on Resubmit button of above report
	 *	- Once the report has completed generation, click on the Report Viewer button
	 *	- Download PDF,  Views PDF, Compliance ZIP (PDF), Compliance ZIP (SHAPE) and Compliance ZIP (META)
	 * Results: - 
	 *	- - SSRS will not have Gap table
	 *  - - View will have Gaps information without any grids and numbers
	 *	- - Gaps shape file will only have gaps information
	 *	- - ReportGaps.csv meta data file will have only single row with gaps information
	 */
	@Ignore //Need to edit customer to enable/disable
	public void TC1599_RemoveGapGrid10FeatureFromExistingCustomerReprocessExistingComplianceReport() throws Exception {
		Log.info("\nRunning TC1599_RemoveGapGrid10FeatureFromExistingCustomerReprocessExistingComplianceReport ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}

	/**
	 * Test Case ID: TC1608_GenerateComplianceReportPicarroAdminIncludeAssetsWithoutSelectingGAPLISA
	 * Test Description: Generate Compliance Report as Picarro Admin and include Assets without selecting GAP and LISA
	 * Script: -  	
	 *	- Log in as Picarro Admin
	 *	- On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- Time Zone : PST, Survey Mode: Standard, Exclusion Radius: 0
	 *	- Add 2 or 3 surveys with different tag values
	 *	- Select Custom boundary and exclude LISA and GAP
	 *	- Select Indication table, Isotopic Analysis table, Percent Coverage Forecast
	 *	- Add View with base map value: map
	 *	- Click on OK and click Compliance Viewer button
	 *	- Download PDF, ZIP (PDF)
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - View are showing assets without LISA and GAP correctly. Nohighlightingof assets is shown.
	 *	- - Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1608, location = ComplianceReportDataProvider.class)
	public void TC1608_GenerateComplianceReportPicarroAdminIncludeAssetsWithoutSelectingGAPLISA(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1608_GenerateComplianceReportPicarroAdminIncludeAssetsWithoutSelectingGAPLISA...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyPDFZipFilesAreCorrect(EMPTY, NOTSET));
		
		//Enable commented line of code once DE2071 gets fixed
assertTrue(complianceReportsPageAction.verifySSRSDrivingSurveyTableInfo(EMPTY, NOTSET));
		
		assertTrue(complianceReportsPageAction.verifySSRSViewsTableInfo(EMPTY, NOTSET));
		
		assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
		
	}

	/**
	 * Test Case ID: TC1651_Re_EnablePercentCoverageForecastFeaturesPermissionCustomer_NewComplianceReportGeneration
	 * Test Description: Re-enable Percent Coverage Forecast features permission to customer - New Compliance report generation
	 * Script: -  	
	 *	- Log in as Picarro Admin
	 *	- On Manage Customers page, select a customer that does not have Percent Coverage Forecast permission enabled and click the "Edit" button 
	 *	- Confirm that the "Account Enabled" box is checked and check the Percent Coverage Forecast
	 *	- Click OK 
	 *	- Login as Customer User (e.g. Customer admin user)
	 *	- On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- Time Zone : PST, Survey Mode: Standard, Exclusion Radius: 0
	 *	- Add 2 or 3 surveys with different tag values
	 *	- Select Customer boundary and select any Plat 
	 *	- Select Indication table, Isotopic Analysis table
	 *	- Select Percent Coverage Forecast check box
	 *	- Add View with base map value: map
	 *	- Click on OK and click Compliance Viewer button
	 *	- Download PDF, ZIP (PDF)
	 * Results: - 
	 *	- - Percent Coverage Forecast should be present in SSRS PDF
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)- Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 *  - - Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Ignore  //Need customer enable/Disable
	public void TC1651_Re_EnablePercentCoverageForecastFeaturesPermissionCustomer_NewComplianceReportGeneration() throws Exception {
		Log.info("\nRunning TC1651_Re_EnablePercentCoverageForecastFeaturesPermissionCustomer_NewComplianceReportGeneration ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}

	/**
	 * Test Case ID: TC1652_Re_EnablePercentCoverageForecastFeaturesPermissionCustomer_CopyComplianceReportGeneration
	 * Test Description: Re-enable Percent Coverage Forecast features permission to customer - Copy Compliance report generation
	 * Script: -  	
	 *	- Log in as Picarro Admin
	 *	- On Manage Customers page, select a customer that does not have Percent Coverage Forecast permission enabled and click the "Edit" button 
	 *	- Confirm that the "Account Enabled" box is checked and check the Percent Coverage Forecast
	 *	- Click OK 
	 *	- Login as Customer User (e.g. Customer Supervisor user)
	 *	- On the Compliance Reports page, click on Copy button of above generated report 
	 *	- Click on Compliance Viewer button
	 *	- Dowload SSRS PDF and Compliance PDF ZIP
	 * Results: - 
	 *	- - Percent Coverage Forecast should be present in SSRS PDF
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)- Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 *  - - Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Ignore //Need to edit customer enable/disable
	public void TC1652_Re_EnablePercentCoverageForecastFeaturesPermissionCustomer_CopyComplianceReportGeneration() throws Exception {
		Log.info("\nRunning TC1652_Re_EnablePercentCoverageForecastFeaturesPermissionCustomer_CopyComplianceReportGeneration ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}

	/**
	 * Test Case ID: TC1655_Re_EnabledPercentCoverageForecastGapGridFeaturesPermissionPicarro_ReprocessComplianceReportGenerationPicarroAdmin
	 * Test Description: Re-enabled Percent Coverage Forecast and Gap Grid features permission to Picarro - Reprocess Compliance report generation as Picarro Admin
	 * Script: -  	
	 *	- Log in as Picarro Admin
	 *	- On Manage Customers page, select a customer (Picarro Admin) that does not have Gap Grid 1.0 and Percent Coverage Forecast permission enabled and click the "Edit" button 
	 *	- Confirm that the "Account Enabled" box is checked and check the Percent Coverage Forecast and Gap Grid 1.0
	 *	- Click OK 
	 *	- Logout an Login again as Picarro Admin 
	 *	- On the Compliance Reports page, click on Resubmit button of above generated report 
	 *	- Click on Compliance Viewer button
	 *	- Dowload SSRS PDF and Compliance PDF ZIP
	 * Results: - 
	 *	- - Forecast table and Gap table will not be present in SSRS PDF (by design)
	 *	- - Gap grids will be present in report views
	 */
	@Ignore //Need to edit customer
	public void TC1655_Re_EnabledPercentCoverageForecastGapGridFeaturesPermissionPicarro_ReprocessComplianceReportGenerationPicarroAdmin() throws Exception {
		Log.info("\nRunning TC1655_Re_EnabledPercentCoverageForecastGapGridFeaturesPermissionPicarro_ReprocessComplianceReportGenerationPicarroAdmin ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}
}
