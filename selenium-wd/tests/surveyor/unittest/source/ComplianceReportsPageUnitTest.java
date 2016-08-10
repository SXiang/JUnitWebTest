package surveyor.unittest.source;

import common.source.Log;
import common.source.PDFUtility;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import org.junit.Test;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest.ReportTestRunMode;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.actions.data.ComplianceReportDataReader;
import surveyor.scommon.actions.data.ComplianceReportDataReader.ComplianceReportsDataRow;
import surveyor.scommon.actions.data.UserDataReader;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.BaseReportsPageTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.ManageCustomersPage;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageUnitTest  extends BaseReportsPageActionTest {

	private static HomePageActions homePageAction;
	private static LoginPageActions loginPageAction;
	private static ManageCustomerPageActions manageCustomerPageAction;
	private static ManageUsersPageActions manageUsersPageAction;
	private static ManageLocationPageActions manageLocationPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static TestEnvironmentActions testEnvironmentAction;
	private static ComplianceReportsPage complianceReportsPage;
	
	@BeforeClass
	public static void beforeTestClass() throws Exception {
		complianceReportsPage = new ComplianceReportsPage(driver, getBaseURL(), getTestSetup());
		PageFactory.initElements(driver,  complianceReportsPage);
		initializePageActions();
	}

	/**
	 * Initializes the page action objects.
	 * @throws Exception 
	 */
	protected static void initializePageActions() throws Exception {
		homePageAction = new HomePageActions(driver, getBaseURL(), getTestSetup());
		manageCustomerPageAction = new ManageCustomerPageActions(driver, getBaseURL(), getTestSetup());
		manageUsersPageAction = new ManageUsersPageActions(driver, getBaseURL(), getTestSetup());
		manageLocationPageAction = new ManageLocationPageActions(driver, getBaseURL(), getTestSetup());
		loginPageAction = new LoginPageActions(driver, getBaseURL(), getTestSetup());
		complianceReportsPageAction = new ComplianceReportsPageActions(driver, getBaseURL(), getTestSetup());
		testEnvironmentAction = new TestEnvironmentActions();

		// Select run mode here.
		setTestRunMode(ReportTestRunMode.FullTestRun);
		
		if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			complianceReportsPageAction.fillWorkingDataForReports(getUnitTestReportRowID());
		}
	}

	@Test
	public void US2774_EnableBaselineShapeFilesForComplianceReports() throws Exception {
        Log.info("\nUS2774_EnableBaselineShapeFilesForComplianceReports");
        complianceReportsPageAction.workingDataRow = new ComplianceReportDataReader(null).new ComplianceReportsDataRow(null,null,
        		null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
        		null);
        complianceReportsPageAction.workingDataRow.title = "TC148 Report639729";
        complianceReportsPageAction.workingDataRow.tCID = "UnitTest-US2774";
        
        
        complianceReportsPage.login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		complianceReportsPage.open();
		complianceReportsPage.performSearch(complianceReportsPageAction.workingDataRow.title);
		LoginPageActions.workingDataRow = new UserDataReader(null).new UserDataRow(null,null,null,null,null,null,null,null,null,null,null,null);
		LoginPageActions.workingDataRow.username = "sqapicsup@picarro.com";
		
		//Delete all the download zips before test or change the parameter "0" to the download index of the this zip
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, 0);
		complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, 0);
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete("0", 0);

		for(int i=0;i<10;i++){ // Testing IO exceptions
		   Assert.assertTrue(complianceReportsPageAction.verifyShapeFilesWithBaselines("0", 0));
		}
	}

	/**
	 * TA862 - Searched Surveys should be filtered by selected report mode
	 * @throws Exception
	 */
	@Test
	public void TA862_ComplianceReportTest_VerifySurveyFilters() throws Exception {
		
		Log.info("\nTA862 - Searched Surveys should be filtered by selected report mode");
		
		complianceReportsPage.login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		complianceReportsPage.open();
		complianceReportsPage.openNewReportPage();
		
		ReportModeFilter rmode = ReportModeFilter.Standard;
		testReportFilters(rmode);
		SurveyModeFilter smode = SurveyModeFilter.All;
		testSurveyFilters(smode);
		
		smode = SurveyModeFilter.Standard;
		testSurveyFilters(smode);
		
		smode = SurveyModeFilter.Operator;
		testSurveyFilters(smode);
		
		rmode = ReportModeFilter.RapidResponse;
		testReportFilters(rmode);
		smode = SurveyModeFilter.RapidResponse;
		testSurveyFilters(smode);
		
		rmode = ReportModeFilter.Manual;
		testReportFilters(rmode);
		smode = SurveyModeFilter.Manual;
		testSurveyFilters(smode);
	
		complianceReportsPage.logout();
	}
	
	private void testReportFilters(ReportModeFilter rmode){
		complianceReportsPage.selectReportMode(rmode);
		Assert.assertTrue(complianceReportsPage.verifySurveyModeFilters(rmode));	
	}
	private void testSurveyFilters(SurveyModeFilter smode){
		complianceReportsPage.selectSurveyModeForSurvey(smode);
		complianceReportsPage.clickOnSearchSurveyButton();
		Assert.assertTrue(complianceReportsPage.verifySurveySelectorWithFilter(smode));
	}
}
