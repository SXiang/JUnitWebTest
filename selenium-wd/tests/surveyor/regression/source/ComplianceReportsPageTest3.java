package surveyor.regression.source;

import common.source.Log;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.testng.Assert;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.Test;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.ReportsCompliance.CustomerBoundaryFilterType;
import surveyor.scommon.source.ReportsCompliance.EthaneFilter;
import surveyor.scommon.source.ReportsSurveyInfo;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.ReportsCompliance;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest3 extends BaseReportsPageActionTest {

	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;	
	private static LoginPageActions loginPageAction;	
	private static ComplianceReportsPage complianceReportsPage;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	
	@BeforeClass
	public static void beforeTestClass() throws Exception {
		initializePageActions();
		
		loginPageAction = new LoginPageActions(driver, baseURL, testSetup);
	}

	/**
	 * Initializes the page action objects.
	 * @throws Exception 
	 */
	protected static void initializePageActions() throws Exception {
		loginPageAction = new LoginPageActions(driver, baseURL, testSetup);
		complianceReportsPageAction = new ComplianceReportsPageActions(driver, baseURL, testSetup);
		// To run the test locally in UnitTest mode uncomment this line.
		setTestRunMode(ReportTestRunMode.UnitTestRun);
		
		if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			complianceReportsPageAction.fillWorkingDataForReports(getUnitTestReportRowID());
		}
			
	}

// TODO DE1898: https://rally1.rallydev.com/#/53512905526d/detail/defect/54567703274
	
	/**
	 * Test Case ID: TC1319_GenerateComplianceReportPicarroSupportUserIncludePercentCoverageForecast
	 * Test Description: - Generate Compliance Report as Picarro Support user and include Percent Coverage Forecast
	 * Script: -  	
	 *	- - Log in as Picarro Support user
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button 
	 *	- - Select 2 or 3 surveys with different tag values
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)
     *  - - Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1319, location = ComplianceReportDataProvider.class)
	public void TC1319_GenerateComplianceReportPicarroSupportUserIncludePercentCoverageForecast(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1319_GenerateComplianceReportPicarroSupportUserIncludePercentCoverageForecast ..." +
			 "\nTest Description: Generate Compliance Report as Picarro Support user and include Percent Coverage Forecast");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));  
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		Assert.assertTrue(complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));

		Assert.assertTrue(complianceReportsPageAction.verifySSRSCoverageTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
		Assert.assertTrue(complianceReportsPageAction.verifySSRSCoverageForecastTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
	}

 
	/**
	 * Test Case ID: TC1320_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecast2SurveysDifferentTags
	 * Test Description: - Generate Compliance Report as Customer Admin, include Percent Coverage Forecast and 2 surveys with different tags
	 * Script: -  	
	 *	- - Log in as Customer Admin user (Eg. PG&amp;E Util Admin)
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button 
	 *	- - Add 2 Surveys (present in the selected plat) with different tag values (try to include approx 8 hours surveys)
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 * Results: - 
	 *	- - Report generated successfully- Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)
	 *  - - Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1320, location = ComplianceReportDataProvider.class)
	public void TC1320_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecast2SurveysDifferentTags(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1320_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecast2SurveysDifferentTags ..." +
			 "\nTest Description: Generate Compliance Report as Customer Admin, include Percent Coverage Forecast and 2 surveys with different tags");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));  /* Change to Utility Admin when customer boundary is available */
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		Assert.assertTrue(complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));

		Assert.assertTrue(complianceReportsPageAction.verifySSRSCoverageTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
		Assert.assertTrue(complianceReportsPageAction.verifySSRSCoverageForecastTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
	}

 
	/**
	 * Test Case ID: TC1339_CheckErrorMesageDisplayedIfPercentCoverageForecastCheckBoxSelected
	 * Test Description: - Check error message is displayed if Percent Coverage Forecast check box is selected on New Compliance Report screens 
	 *                     and user has included only no or one or multiple survey with same tag
	 * Script: -  	
	 *  Log in to application as Customer admin user and navigate to New Compliance Report page
     * - -  Select Customer Boundary
     *  - - Select Percent Coverage Forecast check box
     *  - - Click OK
	 *	- - Add one survey
	 *	- - Add 2 or 3 surveys with same tag value
	 *	- - Add 2 or 3 surveys with different tag value
	 * Results: - 
	 *  - - User friendly error messages are displayed: "Selected Percent Coverage Forecast, Please select at least two surveys with different tags"
	 *	- - Error message will not be displayed to user when different tag value surveys are included
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1319, location = ComplianceReportDataProvider.class)
	public void TC1339_CheckErrorMesageDisplayedIfPercentCoverageForecastCheckBoxSelected(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2, Integer reportDataRowID3) throws Exception {
		Log.info("\nRunning TC1339_CheckErrorMesageDisplayedIfPercentCoverageForecastCheckBoxSelected ..." +
			 "\nTest Description: Check error mesage is displayed if Percent Coverage Forecast check box is selected on New Compliance Report screens and user has included only no or one or multiple survey with same tag");

		String expectedError = CR_CF_ASSETSINVALID_MESSAGE;
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID)); /* Using Picarro Admin now, Change to Utility Admin when small boundary is available */
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		
		//Add no surveys
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPage.verifyErrorMessages(expectedError));
        complianceReportsPage.clickOnCancelBtn();
        
		//Add 2 surveys, different tags
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID2));
		assertFalse(complianceReportsPage.verifyErrorMessages(expectedError));
		
		//Add 2 surveys, same tag
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID3));
		assertFalse(complianceReportsPage.verifyErrorMessages(expectedError));

		complianceReportsPage.logout();
		
	}
 
	/**
	 * Test Case ID: TC1340_CheckErrorMesageDisplayedIfPercentCoverageForecastCheckBoxSelectedOnCopyCR
	 * Test Description: - Check error mesage is displayed if Percent Coverage Forecast check box is selected on Copy Compliance Report 
	 *                     screens and user has included no or only one or multiple survey with same tag
	 * Script: -  	
	 *	- Log in to application as Customer admin user and navigate to Copy Compliance Report page
     *  - Select Percent Coverage Forecast check box
     *  - Click OK
     *  - Add 2 or 3 survey with same tag value
     *  - Click OK
     *  - Delete all the survey and don't included any survey
     *  - Click OK
     *  - Add 2 or 3 surveys with different tag value
	 * Results: - 
	 *  - - User friendly error messages are displayed: "Selected Percent Coverage Forecast, Please select at least two surveys with different tags"
	 *	- - Error message will not be displayed to user when different tag value surveys are included
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1340, location = ComplianceReportDataProvider.class)
	public void TC1340_CheckErrorMesageDisplayedIfPercentCoverageForecastCheckBoxSelectedOnCopyCR(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2,Integer reportDataRowID3, Integer reportDataRowID4,Integer reportDataRowID5) throws Exception {
		Log.info("\nRunning TC1340_CheckErrorMesageDisplayedIfPercentCoverageForecastCheckBoxSelected ..." +
			 "\nTest Description: Check error mesage is displayed if Percent Coverage Forecast check box is selected on Copy Compliance Report screens and user has included no or only one or multiple survey with same tag");
		
		String expectedError = CR_CF_ASSETSINVALID_MESSAGE;
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));  /* Using Picarro Admin now, Change to Utility Admin when small boundary is available */
		       
		//Add 2 surveys, different tags
        complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID2));
        complianceReportsPageAction.clickOnFirstCopyComplianceButton(EMPTY, getReportRowID(reportDataRowID1));
		modifyComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID2));
		assertFalse(complianceReportsPage.verifyErrorMessages(expectedError));
		
		//Add no surveys
        complianceReportsPageAction.clickOnFirstCopyComplianceButton(EMPTY, getReportRowID(reportDataRowID1));
        complianceReportsPage.deleteAllDrivingSurveys();
		modifyComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID3));
		assertTrue(complianceReportsPage.verifyErrorMessages(expectedError));
		        
		//Add 2 surveys, same tag
        complianceReportsPageAction.clickOnFirstCopyComplianceButton(EMPTY, getReportRowID(reportDataRowID1));
		modifyComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID4));
		assertFalse(complianceReportsPage.verifyErrorMessages(expectedError));
		
		complianceReportsPage.clickOnCancelBtn();		
		complianceReportsPage.logout();
	}
 
	/**
	 * Test Case ID: TC1352_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecast4OrMoreSurveysDifferentTags
	 * Test Description: - Generate Compliance Report as Customer Admin, include Percent Coverage Forecast and 4 or more surveys with different tags
	 * Script: -  	
	 *	- - Log in as Customer Admin user (Eg. PG&E Util Admin)
     *	- - On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- - Time Zone : MST, Survey Mode: Rapid Response
	 *	- - Select Customer boundary and select any Plat 
	 *	- - Add 4 or more Surveys (present in the selected plat) with different tag values
	 *	- - Select Indication table, Isotopic Analysis table
	 *	- - Select Percent Coverage Forecast check box
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 *	- - Download PDF, ZIP (PDF)
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - Percent Service Coverage Values should be calculated for both With/Without LISAs
	 *	- - Prediction table will not show any records as 4 or more surveys with different tag values are not supported.
	 *	- - "No Coverage Forecast Available" message is displayed
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1352, location = ComplianceReportDataProvider.class)
	public void TC1352_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecast4OrMoreSurveysDifferentTags(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1352_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecast4OrMoreSurveysDifferentTags ..." +
			 "\nTest Description: Generate Compliance Report as Customer Admin, include Percent Coverage Forecast and 4 or more surveys with different tags");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));  /* Using Picarro Admin now, Change to Utility Admin when small boundary is available */
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		Assert.assertTrue(complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));

		Assert.assertTrue(complianceReportsPageAction.verifySSRSCoverageTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
		//TODO, CHECK ERROR MESSAGE
		Assert.assertFalse(complianceReportsPageAction.verifySSRSCoverageForecastTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC1363_GenerateComplianceReportCustomerAdminUsingCopyFunctionalityIncludePercentCoverageForecast2SurveysDifferentTags
	 * Test Description: - Generate Compliance Report as Customer Admin using Copy functionality, include Percent Coverage Forecast and 2 surveys with different tags
	 * Script: -  	
	 *	- - Log in as Customer Admin user (Eg. PG&amp;E Util Admin)
	 *	- - Click on Copy button of above generated report
	 *	- - Click on OK and click Compliance Viewer button
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - Values should match with above report values
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)
	 *	- - Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1363, location = ComplianceReportDataProvider.class)
	public void TC1363_GenerateComplianceReportCustomerAdminUsingCopyFunctionalityIncludePercentCoverageForecast2SurveysDifferentTags(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1363_GenerateComplianceReportCustomerAdminUsingCopyFunctionalityIncludePercentCoverageForecast2SurveysDifferentTags ..." +
			 "\nTest Description: Generate Compliance Report as Customer Admin using Copy functionality, include Percent Coverage Forecast and 2 surveys with different tags");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));  /* Using Picarro Admin now, Change to Utility Admin when small boundary is available */
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.copyReport(complianceReportsPageAction.workingReportsComp.getRptTitle(), getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnOKButton(EMPTY, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

		Assert.assertTrue(complianceReportsPageAction.verifySSRSCoverageForecastTableInfoWithPreviousResult(EMPTY, getReportRowID(reportDataRowID1)));

	}
 
	/**
	 * Test Case ID: TC1364_GenerateComplianceReportPicarroSupportUserUsingReprocessFunctionalityIncludePercentCoverageForecast3SurveysDifferentTags
	 * Test Description: - Generate Compliance Report as Picarro Support user using Reprocess functionality, include Percent Coverage Forecast and 3 surveys with different tags
	 * Script: -  	
	 *	- - Log in as Picarro Support user
	 *	- - Click on Reprocess button of above generated report
	 *	- - Click Compliance Viewer button
	 *	- - Download PDF, ZIP (PDF)
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - Values should match with above report values
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)
	 *  - - Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1364, location = ComplianceReportDataProvider.class)
	public void TC1364_GenerateComplianceReportPicarroSupportUserUsingReprocessFunctionalityIncludePercentCoverageForecast3SurveysDifferentTags(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1364_GenerateComplianceReportPicarroSupportUserUsingReprocessFunctionalityIncludePercentCoverageForecast3SurveysDifferentTags ..." +
			 "\nTest Description: Generate Compliance Report as Picarro Support user using Reprocess functionality, include Percent Coverage Forecast and 3 surveys with different tags");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID)); 
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.clickOnResubmitButton(EMPTY, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		

		Assert.assertTrue(complianceReportsPageAction.verifySSRSCoverageForecastTableInfoWithPreviousResult(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC1365_GenerateComplianceReportPicarroAdminUsingCopyFunctionalityIncludePercentCoverageForecast3SurveysDifferentTags
	 * Test Description: - Generate Compliance Report as Picarro Admin using Copy functionality, include Percent Coverage Forecast and 3 surveys with different tags
	 * Script: -  	
	 *	- - Log in as Picarro Admin user
	 *	- - Click on Copy button of above generated report
	 *	- - Click Compliance Viewer button
	 *	- - Download PDF, ZIP (PDF)
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - Values should match with above report values
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)
	 *  - - Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1365, location = ComplianceReportDataProvider.class)
	public void TC1365_GenerateComplianceReportPicarroAdminUsingCopyFunctionalityIncludePercentCoverageForecast3SurveysDifferentTags(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1365_GenerateComplianceReportPicarroAdminUsingCopyFunctionalityIncludePercentCoverageForecast3SurveysDifferentTags ..." +
			 "\nTest Description: Generate Compliance Report as Picarro Admin using Copy functionality, include Percent Coverage Forecast and 3 surveys with different tags");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID)); 
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.copyReport(ComplianceReportsPageActions.workingReportsComp.getRptTitle(), getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnOKButton(EMPTY, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		

		Assert.assertTrue(complianceReportsPageAction.verifySSRSCoverageForecastTableInfoWithPreviousResult(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC1366_GenerateComplianceReportPicarroAdminUsingReprocessFunctionalityIncludePercentCoverageForecast2SurveysDifferentTags
	 * Test Description: - Generate Compliance Report as Picarro Admin using Reprocess functionality, include Percent Coverage Forecast and 2 surveys with different tags
	 * Script: -  	
	 *	- - Log in as Picarro Admin user
	 *	- - Click on Reprocess button of above generated report
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - Values should match with above report values
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)
	 *  - - Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1366, location = ComplianceReportDataProvider.class)
	public void TC1366_GenerateComplianceReportPicarroAdminUsingReprocessFunctionalityIncludePercentCoverageForecast2SurveysDifferentTags(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1366_GenerateComplianceReportPicarroAdminUsingReprocessFunctionalityIncludePercentCoverageForecast2SurveysDifferentTags ..." +
			 "\nTest Description: Generate Compliance Report as Picarro Admin using Reprocess functionality, include Percent Coverage Forecast and 2 surveys with different tags");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));  
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.clickOnResubmitButton(EMPTY, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		

		Assert.assertTrue(complianceReportsPageAction.verifySSRSCoverageForecastTableInfoWithPreviousResult(EMPTY, getReportRowID(reportDataRowID1)));


	}
 
	/**
	 * Test Case ID: TC1367_GenerateComplianceReportCustomerSupervisorUserUsingCopyFunctionalityIncludePercentCoverageForecast3SurveysDifferentTags
	 * Test Description: - Generate Compliance Report as Customer Supervisor user using Copy functionality, include Percent Coverage Forecast and 3 surveys with different tags
	 * Script: -  	
	 *	- - Log in as Customer Supervisor user (Eg. PG&amp;E Supervisor)
	 *	- - Click on Copy button of above generated report
	 *	- - Click on OK and click Compliance Viewer button
	 *  - - Download PDF, ZIP(PDF)
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - Values should match with above report values
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)
	 *  - - Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1367, location = ComplianceReportDataProvider.class)
	public void TC1367_GenerateComplianceReportCustomerSupervisorUserUsingCopyFunctionalityIncludePercentCoverageForecast3SurveysDifferentTags(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1367_GenerateComplianceReportCustomerSupervisorUserUsingCopyFunctionalityIncludePercentCoverageForecast3SurveysDifferentTags ..." +
			 "\nTest Description: Generate Compliance Report as Customer Supervisor user using Copy functionality, include Percent Coverage Forecast and 3 surveys with different tags");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));  /* Using Picarro Admin now, Change to Customer supervisor when small boundary is available */
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.copyReport(ComplianceReportsPageActions.workingReportsComp.getRptTitle(), getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnOKButton(EMPTY, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		

		Assert.assertTrue(complianceReportsPageAction.verifySSRSCoverageForecastTableInfoWithPreviousResult(EMPTY, getReportRowID(reportDataRowID1)));


	}
 
	/**
	 * Test Case ID: TC1370_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecastMultipleSurveys
	 * Test Description: - Generate Compliance Report as Customer Admin, include Percent Coverage Forecast and multiple surveys
	 * Script: -  	
	 *	- - Log in as Customer Admin user (Eg. PG&E Util Admin)
	 *	- - On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- - Time Zone : PST, Survey Mode: Rapid Response
	 *	- - Select Customer boundary and select any Plat 
	 *	- - Add 4 Surveys (present in the selected plat) with different tag values (try to include approx 8 hours surveys)
	 *	    (eg. 2 surveys with same tag and other surveys with other tag)
	 *	- - Select Indication table, Isotopic Analysis table
	 *	- - Select Percent Coverage Forecast check box
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 *	- - Download PDF, ZIP (PDF)
	 * Results: - 
	 *	- - Report generated successfully
	 *  - - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)
	 *  - - Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1370, location = ComplianceReportDataProvider.class)
	public void TC1370_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecastMultipleSurveys(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1370_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecastMultipleSurveys ..." +
			 "\nTest Description: Generate Compliance Report as Customer Admin, include Percent Coverage Forecast and multiple surveys");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));  /* Change to Utility Admin when customer boundary is available */
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		Assert.assertTrue(complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		
		Assert.assertTrue(complianceReportsPageAction.verifySSRSCoverageTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
		Assert.assertTrue(complianceReportsPageAction.verifySSRSCoverageForecastTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC1371_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecastMultipleSurveys
	 * Test Description: - Generate Compliance Report as Customer Admin, include Percent Coverage Forecast and multiple surveys
	 * Script: -  	
	 *	- - Log in as Picarro Admin user
	 *	- - On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- - Time Zone : PST, Survey Mode: Rapid Response
	 *	- - Select Customer boundary and select any Plat 
	 *	- - Add 4 Surveys (present in the selected plat) with different tag values (try to include approx 8 hours surveys)
	 *	    (eg. 2 surveys with same tag and other surveys with other tag)
	 *	- - Select Indication table, Isotopic Analysis table
	 *	- - Select Percent Coverage Forecast check box
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 *	- - Download PDF, ZIP (PDF)
	 * Results: - 
	 *	- - Report generated successfully
	 *  - - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)
	 *  - - Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1371, location = ComplianceReportDataProvider.class)
	public void TC1371_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecastMultipleSurveys(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1371_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecastMultipleSurveys ..." +
			 "\nTest Description: Generate Compliance Report as Picarro Admin, include Percent Coverage Forecast and multiple surveys");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));  
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		
        complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		
		Assert.assertTrue(complianceReportsPageAction.verifySSRSCoverageTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
		Assert.assertTrue(complianceReportsPageAction.verifySSRSCoverageForecastTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC1373_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecastAssetsReportArea
	 * Test Description: - Generate Compliance Report as Picarro Admin and include Percent Coverage Forecast, Assets and Report Area
	 * Script: -  	
	 *	- -  Log in as Picarro Admin
	 *	- - On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- - Time Zone : PST, Survey Mode: Standard, Exclusion Radius: 0
	 *	- - Add 2 or 3 surveys with different tag values
	 *	- - Select Customer boundary and select any Plat 
	 *	- - Select Indication table, Isotopic Analysis table
	 *	- - Select Percent Coverage Assets, Report Area and Forecast check box
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 *	- - Download PDF, ZIP (PDF)
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - Percent Coverage Assets and Report Area value is displayed
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)
	 *	- - Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1373, location = ComplianceReportDataProvider.class)
	public void TC1373_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecastAssetsReportArea(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1373_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecastAssetsReportArea ..." +
			 "\nTest Description: Generate Compliance Report as Picarro Admin and include Percent Coverage Forecast, Assets and Report Area");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));  
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		
        complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		
		Assert.assertTrue(complianceReportsPageAction.verifySSRSCoverageTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
		Assert.assertTrue(complianceReportsPageAction.verifySSRSCoverageForecastTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC1389_MetadataExport_CSVFileMultipleSurvey_MultipleLisasISO
	 * Test Description: - MetaData Export -CSV file (multiple survey -multiple LISAs and ISO)
	 * Script: -  	
	 *	- 1. Login to Pcubed as customer admin (CNP or any other customer). Click on the above mentioned Report . Click on Compliance ZIP(META) and download
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
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));		
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
        complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

        Assert.assertTrue(complianceReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));
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
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		String rptTitle = ComplianceReportsPageActions.workingReportsComp.getRptTitle();
		//complianceReportsPage.isCancelButtonDisplayed(rtpTitle,getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.copyReport(rptTitle, getReportRowID(reportDataRowID1));	
		complianceReportsPageAction.clickOnOKButton(EMPTY, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
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
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		String rptTitle = ComplianceReportsPageActions.workingReportsComp.getRptTitle();
        //complianceReportsPage.isCancelButtonDisplayed(rtpTitle,getReportRowID(reportDataRowID1));
		complianceReportsPageAction.copyReport(rptTitle, getReportRowID(reportDataRowID1));	
		complianceReportsPageAction.clickOnOKButton(EMPTY, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
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
	 *	- - User is navigated back to Compliance Reports page and after the report has been generated, Compliance Viewer button is present in the Action column for that report
	 *	- - Thumbnails will be present  for compliance SSRS PDF, ZIP folders and generated view
	 *	- - The thumbnail should still be present
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC12, location = ComplianceReportDataProvider.class)
	public void TC12_ReportViewThumbnailsCustomBoundarySingleView(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
			 Log.info("\nTest Description: Generate Compliance Report as Picarro Admin using Custom Boundary with single view of default size to verify thumbnails");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));  
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));	
		//TODO: confirm these verification steps:
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
        complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

        Assert.assertTrue(complianceReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC13_ReportViewThumbnailsCustomBoundaryMultipleViews
	 * Test Description: - Generate Compliance Report as Picarro Supervisor using Custom Boundary with multiple views of default size to verify thumbnails
	 * Script: -  	
	 * - Log in as Picarro Supervisor
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
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));	
		//TODO: confirm these verification steps:
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
        complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

        Assert.assertTrue(complianceReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));

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
	 *	- - The thumbnail should still be present	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC14, location = ComplianceReportDataProvider.class)
	public void TC14_ReportViewThumbnailsCustomerBoundarySingleView(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC14_ReportViewThumbnailsCustomerBoundarySingleView ..." +
			 "\nTest Description: Generate Compliance Report as Customer Admin using Customer Boundary with single view of default size to verify thumbnails");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));  
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));	
		//TODO: confirm these verification steps:
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
        complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

        Assert.assertTrue(complianceReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));

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
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));	
		//TODO: confirm these verification steps:
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
        complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

        Assert.assertTrue(complianceReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));

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
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1490, location = ComplianceReportDataProvider.class)
	public void TC1490_CreateNewCustomerLISABoxOption(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1490_CreateNewCustomerLISABoxOption ..." +
			 "\nTest Description: Create new customer with LISA Box option");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));  
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));	
		//TODO: confirm these verification steps:
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
        complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

        Assert.assertTrue(complianceReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));

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
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1491, location = ComplianceReportDataProvider.class)
	public void TC1491_CreateNewCustomerWithoutLISABoxOption(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1491_CreateNewCustomerWithoutLISABoxOption ..." +
			 "\nTest Description: create new customer without LISA Box option");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));  
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));	
		//TODO: confirm these verification steps:
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
        complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

        Assert.assertTrue(complianceReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC1496_AddLISABoxOptionExistingCustomer
	 * Test Description: - Add LISA Box option to existing customer
	 * Script: -  	
	 *	-  Log into PCubed as Picarro Admin
	 *	- - Navigate to Picarro Administration -> Manage Customers page
	 *	- - Select a customer and click the Edit button
	 *	- - Check LISA Box 1.0 checkbox, and make sure that the Report Shape File box is checked and click OK
	 *	- - Navigate to Reports -> Compliance Reports and click on the New Compliance Report button
	 *	- - Fill out the necessary fields and select LISAs in the Views section.
	 *	- - Click OK
	 *	- - Once the report has completed generation, click on the Compliance Viewer button and then the View thumbnail
	 *	- - Click on the Shape File button
	 *	- - Run the Shape Files through GIS software like ArcGIS
	 * Results: - 
	 *	- - The report View should have all LISAs in the shape of boxes, not fans or circles
	 *	- - The shapes drawn by the GIS software should match those of the Compliance Report views
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1496, location = ComplianceReportDataProvider.class)
	public void TC1496_AddLISABoxOptionExistingCustomer(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1496_AddLISABoxOptionExistingCustomer ..." +
			 "\nTest Description: Add LISA Box option to existing customer");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));  
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));	
		//TODO: confirm these verification steps:
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
        complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

        Assert.assertTrue(complianceReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));

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
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1497, location = ComplianceReportDataProvider.class)
	public void TC1497_RemoveLISABoxOptionFromExistingCustomer(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1497_RemoveLISABoxOptionFromExistingCustomer ..." +
			 "\nTest Description: Remove LISA Box option from existing customer");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));  
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));	
		//TODO: confirm these verification steps:
		complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
        complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

        Assert.assertTrue(complianceReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));

	}

	
	
	/**
	 * Provide a adaptor of ReportsCompliance for tests, you need to do modification based on your test case
	 * 1. List<Map<String, String>> viewList = rpt.getViewList();
     *    Map<String, String> viewMap = viewList.get(0);
	 *
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @return
	 */
	public ReportsCompliance getSampleComplianceReport(){
		return getSampleComplianceReport("");
	}
	 
	public ReportsCompliance getSampleComplianceReport(String rptTitle){
		//*** Report
		//*** Other parameters
		EthaneFilter ethaneFilter = EthaneFilter.None;
		ReportModeFilter reportModeFilter = ReportModeFilter.Standard;
		boolean geoFilter = true;

		//*** Area Selector ***
		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("");//IMGMAPHEIGHT
		listBoundary.add("");//IMGMAPWIDTH
		//Custom boundary
		//listBoundary.add("");//RNELAT
		//listBoundary.add("");//RNELON
		//listBoundary.add("");//RSWLAT
		//listBoundary.add("");//RSWLON
		//Custom boundary - Lat/Long Map Selector
		int latLongXOffset = 0;
		int latLongYOffset = 0;
		int latLongRectHeight = 0;
		int latLongRectWidth = 0;		
		
		//customer boundary
		CustomerBoundaryFilterType customerBoundaryFilterType = CustomerBoundaryFilterType.SmallBoundary;
		String customerBoundaryName = "TestPlat-Auto-01";
		
		//*** Optional Tabular PDF Content ***
		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "0");
		tableMap.put(KEYISOANA, "0");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tableMap.put(KEYPCF, "0");
		
		//***  & Optional View Layers ***
		List<Map<String, String>> viewLayersList = new ArrayList<Map<String, String>>();
		new HashMap<String, String>();
		tableMap.put(KEYASSETCASTIRON, "0");
		tableMap.put(KEYASSETCOPPER, "0");
		tableMap.put(KEYASSETOTHERPLASTIC, "0");
		tableMap.put(KEYASSETPEPLASTIC, "0");
		tableMap.put(KEYASSETPROTECTEDSTEEL, "0");
		tableMap.put(KEYASSETUNPROTECTEDSTEEL, "0");
		tableMap.put(KEYBOUNDARYDISTRICT, "0");
		tableMap.put(KEYBOUNDARYDISTRICTPLAT, "0");
		tableMap.put(KEYGAPTB, "0");
		tablesList.add(tableMap);

		//*** Views ***
		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap = new HashMap<String, String>();

		viewMap.put(KEYVIEWNAME, "Test View");
		viewMap.put(KEYLISA, "1");
		viewMap.put(KEYFOV, "0");
		viewMap.put(KEYBREADCRUMB, "0");
		viewMap.put(KEYINDICATIONS, "0");
		viewMap.put(KEYISOTOPICCAPTURE, "0");
		viewMap.put(KEYANNOTATION, "0");
		viewMap.put(KEYGAPS, "0");
		viewMap.put(KEYASSETS, "0");
		viewMap.put(KEYBOUNDARIES, "0");
		viewMap.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap);

		//*** Survey Selector ***
		List<String> tagList = new ArrayList<String>();
		tagList.add("");		
		
		//*** Opacity Fine-Tuning ***
		String fovOpacity = "0.5";
		String lisaOpacity = "0.5";
		String strCreatedBy = PICDFADMIN;
		String customer = "Picarro";
		String timeZone = TIMEZONEET;
		String exclusionRadius = "0";
		SurveyModeFilter  surveyMode = SurveyModeFilter.Standard;
		String surveyorUnit = "";
		String startDate = "";
		String endDate = "";
		ReportsCompliance sampleReport = new ReportsCompliance(rptTitle, 
				strCreatedBy, customer, timeZone, exclusionRadius,
				listBoundary, tablesList,
				surveyorUnit, tagList, 
				startDate, endDate, viewList, 
				surveyMode);
		
        sampleReport.setViewLayersList(viewLayersList);
        sampleReport.setEthaneFilter(ethaneFilter);
        sampleReport.setFovOpacity(fovOpacity);
        sampleReport.setLisaOpacity(lisaOpacity);
        sampleReport.setReportModeFilter(reportModeFilter);
        sampleReport.setGeoFilter(geoFilter);
        sampleReport.setCustomBoundaryInfo(latLongXOffset, latLongYOffset, latLongRectHeight, latLongRectWidth);
        sampleReport.setCustomerBoundaryInfo(customerBoundaryFilterType, customerBoundaryName);
        
        //*** Prepare ReportsSurveyInfo
        List<ReportsSurveyInfo> surveyInfoList = new ArrayList<ReportsSurveyInfo>();
        
        ReportsSurveyInfo survey = getReportsSurveyInfoSample(sampleReport);
        //2. Survey2
        //3. Survey3
        //...
		surveyInfoList.add(survey);
		

		
		sampleReport.setSurveyInfoList(surveyInfoList);
        return sampleReport;
	}
	
	public ReportsSurveyInfo getReportsSurveyInfoSample(ReportsCompliance sampleReport){
        //1. Survey
        String surveyor = sampleReport.getSurveyorUnit();
        String username = sampleReport.getUsername();
        String tag = sampleReport.getTagList().get(0);
        String startDate = sampleReport.getStartDate();
        String endDate = sampleReport.getEndDate();
        SurveyModeFilter surveyModeFilter = sampleReport.getSurveyModeFilter();
        Integer numberOfSurveysToSelect = 1;
        boolean selectAllSurveys = false;
        boolean isGeoFilterOn = sampleReport.getGeoFilter();
        
        ReportsSurveyInfo survey = new ReportsSurveyInfo(surveyor, username, tag, startDate, endDate,
        		surveyModeFilter, isGeoFilterOn, numberOfSurveysToSelect, selectAllSurveys);
        return survey;
	}
	/**
	 * Returns the testCase ID based on the username provided by DataProvider.
	 */
//	private String getTestCaseName(ManageUserTestCaseType testCaseType, String username) {//TODO
//		String testCase = "";		
//		switch (testCaseType) {
//		case :
//			if (username.equalsIgnoreCase(SQAPICAD)) {
//				testCase = "TC118";
//			} else if (username.equalsIgnoreCase(SQAPICSUP)) {
//				testCase = "TC491";
//			}
//			break;
//		case :
//			if (username.equalsIgnoreCase(SQAPICAD)) {
//				testCase = "TC119";
//			} else if (username.equalsIgnoreCase(SQAPICSUP)) {
//				testCase = "TC492";
//			}
//			break;
//		}
//		return testCase;
//	}
	// Old way for TC1319
//	String testCaseName = "TC1319";		
//	String rptTitle = testCaseName + " " + "Report" + testSetup.getRandomNumber();
//	String tag1 = "stnd-pic", tag2 = "iso cap", tag3 = "standard";
//	String username = SQAPICSUP;
//	String password = USERPASSWORD;
//	String customer = "Picarro";
//	
//	complianceReportsPage.login(username, password);
//	complianceReportsPage.open();
//
//	ReportsCompliance rpt = getSampleComplianceReport();
//	rpt.setRptTitle(rptTitle);
//	rpt.setCustomer(customer);
//	rpt.setStrCreatedBy(username);
//	
//	//Optional Tabular PDF Content
//	Map<String,String> tableMap = rpt.getTablesList().get(0);
//	tableMap.put(KEYPCA, "1");
//	tableMap.put(KEYPCRA, "1");
//	tableMap.put(KEYPCF, "1");	
//	
//	//Configure Surveys
//	List<ReportsSurveyInfo> surveyInfoList = rpt.getSurveyInfoList();
//	ReportsSurveyInfo survey1 = surveyInfoList.get(0);
//	ReportsSurveyInfo survey2 = getReportsSurveyInfoSample(rpt);
//	ReportsSurveyInfo survey3 = getReportsSurveyInfoSample(rpt);
//	
//	survey1.setTag(tag1);
//	
//	survey2.setTag(tag2);		
//	survey3.setTag(tag3);
//	
//	surveyInfoList.add(survey2);
//	surveyInfoList.add(survey3);
//	
//	complianceReportsPage.addNewReport(rpt);
//	complianceReportsPage.waitForPageLoad();
//	
//	if ((complianceReportsPage.checkActionStatus(rptTitle, username, testCaseName))) {
//		assertTrue(complianceReportsPage.verifyShowCoverageTable( testSetup.getDownloadPath(), rptTitle));
//		assertTrue(complianceReportsPage.verifyCoverageForecastValuesTable(testSetup.getDownloadPath(), rptTitle));
//	} else {
//		fail("\nTestcase " + testCaseName + " failed.\n");
//	}
	
//	@BeforeClass
//	public static void beforeTestClass() throws Exception {
//		initializePageActions();
//	}
//
//	/**
//	 * Initializes the page action objects.
//	 */
//	protected static void initializePageActions() {
//		loginPageAction = new LoginPageActions(driver, baseURL, testSetup);
//		homePageAction = new HomePageActions(driver, baseURL, testSetup);
//		
//		complianceReportsPage = new ComplianceReportsPage(driver, baseURL, testSetup);
//		PageFactory.initElements(driver, complianceReportsPage);
//		manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
//		PageFactory.initElements(driver, manageUsersPage);
//		manageCustomersPage = new ManageCustomersPage(driver, baseURL,testSetup);
//		PageFactory.initElements(driver, manageCustomersPage);
//		manageLocationsPage = new ManageLocationsPage(driver, baseURL,testSetup);
//		PageFactory.initElements(driver, manageLocationsPage);
//		testEnvironmentAction = new TestEnvironmentActions();
//	}
}
