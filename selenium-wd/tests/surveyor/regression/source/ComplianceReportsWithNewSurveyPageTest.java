package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG2;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;

import java.util.Map;

import common.source.Log;
import common.source.WebElementExtender;

import org.junit.Assert;
import org.junit.Before;
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
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsWithNewSurveyPageTest extends BaseReportsPageActionTest {

	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;

	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static MeasurementSessionsPage measurementSessionsPage;
	private static Map<String, String> testAccount, testSurvey, testReport;
	
	@BeforeClass
	public static void beforeTestClass() throws Exception {
		initializePageActions();

		loginPageAction = new LoginPageActions(driver, baseURL, testSetup);;
		measurementSessionsPage = new MeasurementSessionsPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  measurementSessionsPage);

		// Select run mode here.
		setPropertiesForTestRunMode();
	}

	@Before
	public void beforeTest() throws Exception{
		setPropertiesForTestRunMode();
		if(testAccount == null){
			testAccount = createTestAccount("CusWithoutAsset");
			testSurvey = addTestSurvey(testAccount.get("analyzerName"), testAccount.get("analyzerSharedKey")
					,testAccount.get("userName"), testAccount.get("userPassword"), SurveyType.Standard);		
		}
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
		loginPageAction = new LoginPageActions(driver, baseURL, testSetup);
		complianceReportsPageAction = new ComplianceReportsPageActions(driver, baseURL, testSetup);
		setReportsPage((ComplianceReportsPage)complianceReportsPageAction.getPageObject());
	}

	private ComplianceReportsPage getComplianceReportsPage() {
		return (ComplianceReportsPage)getReportsPage();
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
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC210, location = ComplianceReportDataProvider.class)
	public void TC210_GenerateReportTryDeleteSurveyUsedWhileGeneratingReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC210_GenerateReportTryDeleteSurveyUsedWhileGeneratingReport ...");
		
		final int LOGIN_USER_ROW_ID = 6;        /* LoginRowID. AutomationAdmin */
	    final int DB3_ANALYZER_ROW_ID = 9;      /* Analyzer3/Surveyor3. Replay db3 file rowID */
	    final int SURVEY_ROW_ID = 51;           /* Survey information rowID */
	    final int SURVEY_RUNTIME_IN_SECONDS = 20; /* Number of seconds to run the survey for. */
	    
		TestEnvironmentActions.generateSurveyForUser(LOGIN_USER_ROW_ID, DB3_ANALYZER_ROW_ID, SURVEY_ROW_ID, SURVEY_RUNTIME_IN_SECONDS);

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

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
	 * Test Case ID: TC1307_CheckPercentCoverageForecastCheckBoxNotPresentNewCopyComplianceReportScreensOfCustomerUserNotHavingAssets
	 * Script: -  	
	 *  - - Log in to application as Customer admin user and navigate to New Compliance Report page
	 *  - - Click on Cancel and navigate to Copy compliance screen
	 * Results: - 
	 *	- - Percent Coverage Forecast check box is not present on UI
	 */
	@Test
	public void TC1307_CheckPercentCoverageForecastCheckBoxNotPresentNewCopyComplianceReportScreensOfCustomerUserNotHavingAssets() throws Exception {
		Log.info("\nRunning TC1307_CheckPercentCoverageForecastCheckBoxNotPresentNewCopyComplianceReportScreensOfCustomerUserNotHavingAssets ...");

		String userName = testAccount.get("userName");
		String userPassword = testAccount.get("userPassword");
		
		testReport = addTestReport(testAccount.get("userName"), testAccount.get("userPassword"), SurveyModeFilter.Standard);

		loginPage.open();
		loginPage.loginNormalAs(userName, userPassword);

		this.getComplianceReportsPage().open();

		this.getComplianceReportsPage().getNewComplianceReportBtn().click();
		assertFalse(WebElementExtender.isElementPresentAndDisplayed(this.getComplianceReportsPage().getPercentCoverForecast()));
		this.getComplianceReportsPage().clickOnCancelBtn();

		this.getComplianceReportsPage().clickOnFirstCopyComplianceBtn();
		this.getComplianceReportsPage().waitForCopyReportPagetoLoad();
		assertFalse(WebElementExtender.isElementPresentAndDisplayed(this.getComplianceReportsPage().getPercentCoverForecast()));
	}
}
