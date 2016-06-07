package surveyor.regression.source;

import static org.junit.Assert.*;
import common.source.Log;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.junit.Test;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.dataprovider.ComplianceReportDataProvider;
import static surveyor.scommon.source.SurveyorConstants.*;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest8 extends BaseReportsPageActionTest {


	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;
	private static Object PreCustomerValue = null;
	
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

		// Select run mode here.
		setTestRunMode(ReportTestRunMode.FullTestRun);
		
		if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			complianceReportsPageAction.fillWorkingDataForReports(getUnitTestReportRowID());
		}
	}

	/**
	 * Test Case ID: TC1635_GapsRestrictionsShouldPresentWhenUserSelectLargerReportAreaGapsClassicGapsFeatureCustomers
	 * Test Description: Gaps restrictions should be present when user select larger report area and gaps for classic gaps feature customers
	 * Script: -  	
	 *	- - Log in as Customer admin or supervisor user
	 *      Customer not having Gap Grid 1.0 feature privilege. 
	 *      Customer has classic gaps 
	 *  - - Generate compliance report
	 *	- - Navigate to Reports -& Compliance -& New Compliance Report
	 *	- - Select larger area (& 1.5 sq kms)
	 *	- - Provide all other required details
	 *	- - Click OK
	 *	- - Click on Copy button of above generate report
	 *	- - Select larger area (& 1.5 sq kms)
	 *	- - Click OK
	 * Results: - 
	 *	- - Report should get generated successfully with gaps present in views
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1635, location = ComplianceReportDataProvider.class)
	public void TC1635_GapsRestrictionsShouldPresentWhenUserSelectLargerReportAreaGapsClassicGapsFeatureCustomers(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1635_GapsRestrictionsShouldPresentWhenUserSelectLargerReportAreaGapsClassicGapsFeatureCustomers ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		String rptTitle = ComplianceReportsPageActions.workingDataRow.title;
		complianceReportsPageAction.copyReport(rptTitle, getReportRowID(reportDataRowID1));
		
		modifyComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID2));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID2));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID2));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID2));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID2));

		assertTrue(complianceReportsPageAction.verifySSRSImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID2)));
	}
 
	/**
	 * Test Case ID: TC1730_ReportGenerationWhenAssetsAreSelectedViewsButSurveyAreaNoAssetData
	 * Test Description: Report generation when Assets are selected in Views but survey area has no asset data
	 * Script: -  	
	 *	- 1) On Compliance Reports page, click New Compliance Report button
	 *	- 2) Fill out all necessary report details
	 *	- 3) Select a survey in an area where there is no asset data for that customer
	 *	- 4) Select a boundary that includes the survey area
	 *	- 5) Select Assets in the views section along with LISAs, FOV, Indications, Gaps and Breadcrumb and check all asset and boundary layer check boxes
	 *	- 6) Click OK
	 * Results: - 
	 *	- 1) User is navigated to report generation page
	 *	- 2) The report should generate successfully and LISAs, FOV, Gaps, Indications and Breadcrumb will appear in views. Assets and boundaries will not appear
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1730, location = ComplianceReportDataProvider.class)
	public void TC1730_ReportGenerationWhenAssetsAreSelectedViewsButSurveyAreaNoAssetData(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1730_ReportGenerationWhenAssetsAreSelectedViewsButSurveyAreaNoAssetData ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
        complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));		
        complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID1));
        
        Assert.assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines("FALSE", getReportRowID(reportDataRowID1)));
		
	}
 
	/**
	 * Test Case ID: TC1798_ValidateInformationPresentReportViewsPDFComplianceReport
	 * Test Description: Validate information present in report views PDF for compliance report
	 * Script: -  	
	 *	- - Login as customer supervisor
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button
	 *	- - Timezone : PDT, Report Mode: Rapid Response, Exclusion Radius: 0
	 *	- - Select 'Customer Boundary' and click on Boundary Selector button
	 *	- - Select any boundary and click OK
	 *	- - View 1: Select all Base Map: Satellite
	 *	- - View 2: FOV and Gaps. Base Map: Satellite
	 *	- - Select All Assets and boundary layers
	 *	- - Click on OK
	 *	- - Click on Report Viewer and download View PDF and ZIP(PDF) icon
	 * Results: - 
	 *	- - Report is generated successfully
	 *	- - Both View PDFs should have Report Title, Report View, Date Generated, Report Mode, Report Author and Report Name details displayed in footer are as expected
	 */
	@Test /* Using Picarro Admin */
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1798, location = ComplianceReportDataProvider.class)
	public void TC1798_ValidateInformationPresentReportViewsPDFComplianceReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1798_ValidateInformationPresentReportViewsPDFComplianceReport ...");
		
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
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		
		assertTrue(complianceReportsPageAction.verifySSRSPDFFooter(EMPTY, getReportRowID(reportDataRowID1)));

	}
 
	/**
	 * Test Case ID: TC188_ClickCancelButtonPresentProgressComplianceReport
	 * Test Description: Click on Cancel button present for in progress compliance report
	 * Script: -  	
	 *	- - Generate a new compliance report
	 *	- - Click on Cancel button present next to inprogress bar
	 * Results: - 
	 *	- - Report generation action is canceled
	 *	- - Delete, Copy buttons are displayed to customer user
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC188, location = ComplianceReportDataProvider.class)
	public void TC188_ClickCancelButtonPresentProgressComplianceReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC188_ClickCancelButtonPresentProgressComplianceReport ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.cancelInProgressReport(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyCopyButtonIsDisplayed(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyDeleteButtonIsDisplayed(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyReportGenerationIsCancelled(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC193_GenerateReportCustomerBoundarySelectGaps
	 * Test Description: Generate report for customer boundary and select gaps
	 * Script: -  	
	 *	- - Generate report and select customer boundary 
	 *	- - Add view having Gaps in it
	 * Results: - 
	 *	- - Report is generated successfully for specified customer boundary along with gaps. Edges of shaded areas should be aligned with red boundary
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC193, location = ComplianceReportDataProvider.class)
	public void TC193_GenerateReportCustomerBoundarySelectGaps(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC193_GenerateReportCustomerBoundarySelectGaps ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
	
		assertTrue(complianceReportsPageAction.verifySSRSImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC199_VerifySpecifiedCustomersSurveyorUnitsSurveysAreDisplayedWhenUserSelectsPicarroOrAnyOtherCustomer
	 * Test Description: Verify specified customer's Surveyor Units and Surveys are displayed when user selects "Picarro" or any other customer
	 * Script: -  	
	 *	- - On Compliance Reports page, check the Surveyor dropdown in the Survey Selector section  
	 *	- - Select a different customer, click the Change Customer button on the popup
	 * Results: - 
	 *	- - Verify that the Surveyor units in the Survey Selector section belong to selected customer
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC199, location = ComplianceReportDataProvider.class)
	public void TC199_VerifySpecifiedCustomersSurveyorUnitsSurveysAreDisplayedWhenUserSelectsPicarroOrAnyOtherCustomer(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC199_VerifySpecifiedCustomersSurveyorUnitsSurveysAreDisplayedWhenUserSelectsPicarroOrAnyOtherCustomer ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnNewComplianceReport(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.selectCustomer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnSurveySelectorSearchButton(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifySearchedSurveysAreForSpecifiedCustomer(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC200_VerifyCorrectSurveyModeDisplayedDependingReportModeSelection
	 * Test Description: Verify correct Survey mode is displayed depending on report mode selection
	 * Script: -  	
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button 
	 *	- - Click on Standard report mode and check the survey mode 
	 *	- - Click on Rapid Response report mode and check the survey mode 
	 *	- - Click on Manual report mode and check the survey mode
	 * Results: - 
	 *	- - Standard and Operator survey mode should be present for standard report mode
	 *	- - Standard, Rapid Response and Operator survey mode should be present for rapid response report mode
	 *	- - Manual survey mode should be present for manual report mode
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC200, location = ComplianceReportDataProvider.class)
	public void TC200_VerifyCorrectSurveyModeDisplayedDependingReportModeSelection(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC200_VerifyCorrectSurveyModeDisplayedDependingReportModeSelection ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnNewComplianceReport(EMPTY, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.selectReportMode("Standard", getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyStandardSurveyModeIsShownOnPage(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyOperatorSurveyModeIsShownOnPage(EMPTY, getReportRowID(reportDataRowID1)));
		
		complianceReportsPageAction.selectReportMode("RR", getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyStandardSurveyModeIsShownOnPage(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyOperatorSurveyModeIsShownOnPage(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyRapidResponseSurveyModeIsShownOnPage(EMPTY, getReportRowID(reportDataRowID1)));
		
		complianceReportsPageAction.selectReportMode("Manual", getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyManualSurveyModeIsShownOnPage(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC201_VerifyCorrectSurveyModeDisplayedDependingReportModeSelectionUsingCopyFeature
	 * Test Description: Verify correct Survey mode is displayed depending on report mode selection using copy feature
	 * Script: -  	
	 *	- Pre-requisities: 
	 *	- - Create a compliance report  
	 *	- - On Home Page, click Reports -& Compliance -& Copy button 
	 *	- - Click on Standard report mode and check the survey mode 
	 *	- - Click on Rapid Response report mode and check the survey mode 
	 *	- - Click on Manual report mode and check the survey mode
	 * Results: - 
	 *	- - Standard and Operator survey mode should be present for standard report mode
	 *	- - Standard, Rapid Response and Operator survey mode should be present for rapid response report mode
	 *	- - Manual survey mode should be present for manual report mode
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC201, location = ComplianceReportDataProvider.class)
	public void TC201_VerifyCorrectSurveyModeDisplayedDependingReportModeSelectionUsingCopyFeature(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC201_VerifyCorrectSurveyModeDisplayedDependingReportModeSelectionUsingCopyFeature ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnFirstCopyComplianceButton(EMPTY, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.selectReportMode("Standard", getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyStandardSurveyModeIsShownOnPage(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyOperatorSurveyModeIsShownOnPage(EMPTY, getReportRowID(reportDataRowID1)));
		
		complianceReportsPageAction.selectReportMode("RR", getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyStandardSurveyModeIsShownOnPage(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyOperatorSurveyModeIsShownOnPage(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyRapidResponseSurveyModeIsShownOnPage(EMPTY, getReportRowID(reportDataRowID1)));
		
		complianceReportsPageAction.selectReportMode("Manual", getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyManualSurveyModeIsShownOnPage(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC205_VerifyCustomerNotChangedIfUserClicksNOChangeCustomerButton
	 * Test Description: Verify customer is not changed if user clicks on NO change customer button
	 * Script: -  	
	 *	- - Login as Picarro Admin 
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button 
	 *	- - Select customer other than Picarro (eg. PG&amp;E) 
	 *	- - Click on NO button present on change report mode dialog pop up
	 * Results: - 
	 *	- - Customer Name should presists and is not modified to other
	 */
	@Test 
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC205, location = ComplianceReportDataProvider.class)
	public void TC205_VerifyCustomerNotChangedIfUserClicksNOChangeCustomerButton(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC205_VerifyCustomerNotChangedIfUserClicksNOChangeCustomerButton ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnNewComplianceReport(EMPTY, getReportRowID(reportDataRowID1));
		PreCustomerValue = complianceReportsPage.getCustomerValue();
		complianceReportsPage.selectCustomer(CUSNAMEBASE,false);
		assertEquals(PreCustomerValue,complianceReportsPage.getCustomerValue());
	}
 
	/**
	 * Test Case ID: TC206_VerifyCustomerNotChangedIfUserClicksNOChangeCustomerButtonReportsUsingCopyFunctionality
	 * Test Description: Verify customer is not changed if user clicks on NO change customer button for reports using copy functionality
	 * Script: -  	
	 *	- - Login as Picarro Admin 
	 *	- - On Home Page, click Reports -& Compliance -& Copy report button 
	 *	- - Select customer other than selected one 
	 *	- - Click on NO button present on change report mode dialog pop up
	 * Results: - 
	 *	- - Customer Name should presists and is not modified to other
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC206, location = ComplianceReportDataProvider.class)
	public void TC206_VerifyCustomerNotChangedIfUserClicksNOChangeCustomerButtonReportsUsingCopyFunctionality(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC206_VerifyCustomerNotChangedIfUserClicksNOChangeCustomerButtonReportsUsingCopyFunctionality ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnFirstCopyComplianceButton(EMPTY, getReportRowID(reportDataRowID1));
		PreCustomerValue = complianceReportsPage.getCustomerValue();
		complianceReportsPage.selectCustomer(CUSNAMEBASE,false);
		assertEquals(PreCustomerValue,complianceReportsPage.getCustomerValue());
		
	}
 
	/**
	 * Test Case ID: TC211_GenerateReportSurveyWhichDurationLessThanMin
	 * Test Description: Generate Report for survey which has duration less than a min
	 * Script: -  	
	 *	- - Include the survey which has duration as less than a min (shown as 0 min in driving surveys page) 
	 *      and generate the compliance report
	 * Results: - 
	 *	- - Report should be generated successfully and user should be able to download it
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC211, location = ComplianceReportDataProvider.class)
	public void TC211_GenerateReportSurveyWhichDurationLessThanMin(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC211_GenerateReportSurveyWhichDurationLessThanMin ...");
		
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
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyPDFZipFilesAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC236_VerySmallOrBigReportAreaSelectionNotAllowedNewComplianceReportScreen
	 * Test Description: Very small or big report area selection not allowed on new compliance report screen
	 * Script: -  	
	 *	- - Navigate to New compliance report screen 
	 *	- - Provide lat/ long co-ordiates value such that area smaller than 500 m in diagonal 
	 *	- - Click OK 
	 *	- - Provide lat/ long co-ordiates value such that area greater than 15.5 miles in diagonal 
	 *	- - Click OK
	 * Results: - 
	 *	- - Please make sure your selected boundary is more than 0.5kms and less than 25kms
	 */
	@Test /*Input Lat/Lon manually,  Implementation of selecting area from the Map Selector is not available yet*/
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC236, location = ComplianceReportDataProvider.class)
	public void TC236_VerySmallOrBigReportAreaSelectionNotAllowedNewComplianceReportScreen(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC236_VerySmallOrBigReportAreaSelectionNotAllowedNewComplianceReportScreen ...");
		String xsBoundary = NELAT_XSMALL+","+NELON_XSMALL+","+SWLAT_XSMALL+","+SWLON_XSMALL;
		String xlBoundary = NELAT_XLARGE+","+NELON_XLARGE+","+SWLAT_XLARGE+","+SWLON_XLARGE;
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnNewComplianceReport(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.enterCustomBoundaryUsingTextFields(xsBoundary, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnOKButton(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyErrorMessages(CR_BOUNDARYMINSIZE_MESSAGE, NOTSET));

		complianceReportsPageAction.enterCustomBoundaryUsingTextFields(xlBoundary, getReportRowID(reportDataRowID2));
		complianceReportsPageAction.clickOnOKButton(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyErrorMessages(CR_BOUNDARYMINSIZE_MESSAGE, NOTSET));
	}
 
	/**
	 * Test Case ID: TC522_CloseComplianceViewerDialog
	 * Test Description: Close Compliance Viewer dialog
	 * Script: -  	
	 *	- - Navigate to the Compliance Reports page
	 *	- - Select a report and click on the Compliance Viewer button
	 *	- - Click on the Close button
	 *	- - Repeat the first two steps above
	 *	- - Click on the X at the top right
	 * Results: - 
	 *	- - The Compliance Viewer dialog should close
	 *	- - The Compliance Viewer dialog should close
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC522, location = ComplianceReportDataProvider.class)
	public void TC522_CloseComplianceViewerDialog(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC522_CloseComplianceViewerDialog ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnCloseReportViewer(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyComplianceViewerDialogIsClosed(EMPTY, getReportRowID(reportDataRowID1)));
		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnCloseReportViewer("XBUTTON", getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyComplianceViewerDialogIsClosed(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC524_LatLongCo_OrdinatesPersistCustomBoundary
	 * Test Description: Lat/Long co-ordinates persist on Custom Boundary
	 * Script: -  	
	 *	- - Login to p3sqa.picarro.com
	 *	- - Navigate New or Copy compliance report screen
	 *	- - Select Custom boundary and click on Lat/Long Map Selector
	 *	- - Select the area of interest and click OK
	 *	- - Click on Lat/Long Map Selector button
	 *	- - Click OK
	 * Results: - 
	 *	- - Lat and Long co-ordinates should persist
	 */
	@Ignore @Test /*Map selector/verification implementation needs to be redefined*/
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC524, location = ComplianceReportDataProvider.class)
	public void TC524_LatLongCo_OrdinatesPersistCustomBoundary(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC524_LatLongCo_OrdinatesPersistCustomBoundary ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnNewComplianceReport(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.enterCustomBoundaryUsingAreaSelector(EMPTY, getReportRowID(reportDataRowID1));
	}
 
	/**
	 * Test Case ID: TC529_CanceledIsotopicCaptureAppearsOnlyComplianceReportSSRSPDF
	 * Test Description: Canceled Isotopic Capture appears only in Compliance Report SSRS PDF
	 * Script: -  	
	 *	- - Generate Compliance report for the above survey
	 *	- - Download Compliance PDF, Compliance ZIP (PDF) and View
	 * Results: - 
	 *	- - SSRS PDF should display Isotopic Canceled result in isotopic analysis table
	 *	- (Result should be same as that present in Survey view for the included survey)
	 *  - Views should not display Isotopic Canceled result (Notes associated to isotopic capture result should not be displayed)
	 *	- (If Reference gas canceled capture is present, then that result and notes should not be displayed in SSRS and View)
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC529, location = ComplianceReportDataProvider.class)
	public void TC529_CanceledIsotopicCaptureAppearsOnlyComplianceReportSSRSPDF(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC529_CanceledIsotopicCaptureAppearsOnlyComplianceReportSSRSPDF ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
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
	 * Test Case ID: TC579_IndicationCallOutsTooSmallIncreaseDPI
	 * Test Description: Indication call outs too small & increase DPI
	 * Script: -  	
	 *	- Steps: Login to Pcubed as customer administrator (utility admin)
	 *	- Go Compliance report section under Report section
	 *	- 1.Generate Compliance report with survey that contains good amount of leak indication
	 *	- Choose the size of PDF 8.5X11
	 *	- Print the map on 8.5X11
	 *	- 2. Repeat the test on 11X13 size
	 *	- 3. Generate the reports with big surveys simultaneously (5 at a time)
	 *	- 4. Repeat the above test for Picarro administrator
	 * Results: - 
	 *	- Verify that when downloaded the PDF file Indications and capture results are readable
	 *	- Verify on the printed paper Indicationnumberand capture results are readable
	 *	- 3. Verify that all the load is consumed properly and reports gets generated without error.
	 *	- 4. Verify all of the above is working as expected.
	 */
	@Ignore @Test /* Not Automatable */
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC579, location = ComplianceReportDataProvider.class)
	public void TC579_IndicationCallOutsTooSmallIncreaseDPI(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC579_IndicationCallOutsTooSmallIncreaseDPI ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyPDFZipFilesArePresent(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyLISAsIndicationTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifyIsotopicAnalysisTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
		assertTrue(complianceReportsPageAction.verifySSRSImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC679_ShapefileButtonUnavailablePicarroAdminIfCustomersReportReprocessedCustomerDoesNotShapefileGenerationOptionEnabled
	 * Test Description: Shapefile button unavailable for Picarro Admin if Customer's report is reprocessed 
	 * 					and customer does not have Shapefile generation option enabled
	 * Script: -  	
	 *	- - Log in with a Picarro Admin user account
	 *	- - Generate above report using reprocess functionality, click the thumbnail preview button
	 * Results: - 
	 *	- - The Shapefile button should not be present for customer' reports
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC679, location = ComplianceReportDataProvider.class)
	public void TC679_ShapefileButtonUnavailablePicarroAdminIfCustomersReportReprocessedCustomerDoesNotShapefileGenerationOptionEnabled(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC679_ShapefileButtonUnavailablePicarroAdminIfCustomersReportReprocessedCustomerDoesNotShapefileGenerationOptionEnabled ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assertFalse(complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));
		
		complianceReportsPageAction.clickOnCloseReportViewer(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnResubmitButton(EMPTY, getReportRowID(reportDataRowID1));		
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assertFalse(complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC682_ShapefileButtonNotAvailableIfReportDoesNotIncludeFollowingLisasFOVGapsBreadcrumb
	 * Test Description: Shapefile button not available if report does not include the following: LISAs, FOV, Gaps, Breadcrumb
	 * Script: -  	
	 *	- - Log in as Picarro Admin
	 *	- - On the Compliance Reports page, click the New Compliance Report button
	 *	- - Fill out all of the required fields
	 *	- - In the Views section, leave LISAs, FOV, Breadcrumb, Gaps unselected and click OK
	 *	- - When the report has finished generating, click the thumbnail preview button
	 * Results: - 
	 *	- - There should be no Shapefile buttons
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC682, location = ComplianceReportDataProvider.class)
	public void TC682_ShapefileButtonNotAvailableIfReportDoesNotIncludeFollowingLisasFOVGapsBreadcrumb(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC682_ShapefileButtonNotAvailableIfReportDoesNotIncludeFollowingLisasFOVGapsBreadcrumb ...");
		
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assertFalse(complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));
	}
 
	/**
	 * Test Case ID: TC704_LISABox10_ServicesTouchingLISABoxesAreHighlightedAlong25FeetOfMainWhichTheyAreAttachedEitherSideOfT
	 * Test Description: LISA Box 1.0 - Services Touching LISA boxes are Highlighted, along with 25 feet of the Main to which they are attached, on either side of the "T"
	 * Script: -  	
	 *	- - Log in as Picarro Admin
	 *	- - On the Compliance Reports page, click the New Compliance Report button
	 *	- - Fill out the required fields
	 *	- - Select a survey that has LISAs with Services touching the LISA boxes
	 *	- - In the Views section, select LISAs and Assets
	 *	- - Click OK
	 *	- - When the report has finished generating, click on the thumbnail preview button and download the Compliance ZIP PDF file
	 *	- - Extract the contents of the zip
	 * Results: - 
	 *	- - In the report view, any Service assets that are contained in or touch a LISA box should be highlighted along its entire length
	 *	- - The main to which each of the highlighted Services are attached should also be highlighted, up to 25 feet on either side of the T connected to the Service
	 */
	@Ignore @Test /* Services asset is not available on sqaauto */
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC704, location = ComplianceReportDataProvider.class)
	public void TC704_LISABox10_ServicesTouchingLISABoxesAreHighlightedAlong25FeetOfMainWhichTheyAreAttachedEitherSideOfT(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC704_LISABox10_ServicesTouchingLISABoxesAreHighlightedAlong25FeetOfMainWhichTheyAreAttachedEitherSideOfT ...");
		
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
	 * Test Case ID: TC705_LISABox10_MainsWithinLISABoxAreHighlighted
	 * Test Description: LISA Box 1.0 - Mains Within LISA Box Are Highlighted
	 * Script: -  	
	 *	- - Log in as Picarro Admin
	 *	- - On the Compliance Reports page, click the New Compliance Report button
	 *	- - Fill out the required fields
	 *	- - Select a survey that has LISAs and Mains assets touching the LISA boxes
	 *	- - In the Views section, select LISAs and Assets
	 *	- - Click OK
	 *	- - When the report has finished generating, click on the thumbnail preview button and download the Compliance ZIP PDF file
	 *	- - Extract the contents of the zip
	 * Results: - 
	 *	- - In the report view, only the segment of Main contained within the LISA box should be highlighted
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC705, location = ComplianceReportDataProvider.class)
	public void TC705_LISABox10_MainsWithinLISABoxAreHighlighted(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC705_LISABox10_MainsWithinLISABoxAreHighlighted ...");
		
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
}
