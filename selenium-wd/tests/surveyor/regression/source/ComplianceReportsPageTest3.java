package surveyor.regression.source;

import common.source.Log;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.*;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.Test;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest3 extends BaseReportsPageActionTest {
	
	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static ComplianceReportsPage complianceReportsPage;

	@BeforeClass
	public static void beforeTestClass() throws Exception {
		initializePageActions();
		complianceReportsPage = new ComplianceReportsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  complianceReportsPage);
	}

	/**
	 * Initializes the page action objects.
	 * @throws Exception 
	 */
	protected static void initializePageActions() throws Exception {
		loginPageAction = new LoginPageActions(driver, baseURL, testSetup);
		complianceReportsPageAction = new ComplianceReportsPageActions(driver, baseURL, testSetup);

		// To run the test locally in UnitTest mode uncomment this line.
		//setTestRunMode(ReportTestRunMode.UnitTestRun);
		
		if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			complianceReportsPageAction.fillWorkingDataForReports(getUnitTestReportRowID());
		}
	}
	
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
	@Ignore @Test /* Same as TC1319 except login user *//* Using Picarro Admin now, Change to Utility Admin when small boundary is available */
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1320, location = ComplianceReportDataProvider.class)
	public void TC1320_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecast2SurveysDifferentTags(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1320_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecast2SurveysDifferentTags ..." +
			 "\nTest Description: Generate Compliance Report as Customer Admin, include Percent Coverage Forecast and 2 surveys with different tags");
		
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
	@Test  /* Using Picarro Admin now, Change to Utility Admin when small boundary is available */
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1339, location = ComplianceReportDataProvider.class)
	public void TC1339_CheckErrorMesageDisplayedIfPercentCoverageForecastCheckBoxSelected(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2, Integer reportDataRowID3) throws Exception {
		Log.info("\nRunning TC1339_CheckErrorMesageDisplayedIfPercentCoverageForecastCheckBoxSelected ..." +
			 "\nTest Description: Check error mesage is displayed if Percent Coverage Forecast check box is selected on New Compliance Report screens and user has included only no or one or multiple survey with same tag");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID)); 
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		
		//Add 1 survey
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPage.verifyErrorMessages(CR_CF_ASSETSINVALID_MESSAGE));
        complianceReportsPageAction.clickOnCancelButton(EMPTY, getReportRowID(reportDataRowID1));
		//Add 2 surveys, same tag
        complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID3));
		assertTrue(complianceReportsPage.verifyErrorMessages(CR_CF_ASSETSINVALID_MESSAGE));
		complianceReportsPageAction.clickOnCancelButton(EMPTY, getReportRowID(reportDataRowID3));
		//Add 2 surveys, different tags
		complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID2));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID2));
		assertFalse(complianceReportsPage.verifyErrorMessages(CR_CF_ASSETSINVALID_MESSAGE));

		
	}
}
