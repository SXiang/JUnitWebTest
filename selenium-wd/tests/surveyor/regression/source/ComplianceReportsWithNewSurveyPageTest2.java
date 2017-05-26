package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.ALL_LICENSED_FEATURES_ROWIDS_NOLISABOX;
import static surveyor.scommon.source.SurveyorConstants.ALL_LICENSED_FEATURES_ROWIDS_NO_ANALYTICS;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG2;
import java.util.Map;

import common.source.HostSimDefinitionGenerator;
import common.source.Log;
import common.source.WebElementExtender;

import org.junit.AfterClass;
import org.junit.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import surveyor.dataaccess.source.Customer;
import surveyor.dataaccess.source.Analyzer.CapabilityType;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.scommon.actions.LoginPageActions;

import surveyor.scommon.actions.ManageAnalyzerPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.ManageRefGasBottlesPageActions;
import surveyor.scommon.actions.ManageSurveyorPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;

import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;

import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.PageObjectFactory;

import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.ReportsCommonPage.ReportsButtonType;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsWithNewSurveyPageTest2 extends BaseReportsPageActionTest {

	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;

	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static MeasurementSessionsPage measurementSessionsPage;
	private static Map<String, String> testAccount, testSurvey, testReport;
	private static ManageLocationPageActions manageLocationPageAction;

	private static String userName;
	private static String userPassword;
	private static String customerName;
	private static String locationName;
	private static String analyzerSharedKey;
	private static String analyzerName;
	private static String analyzerType;
	private static String surveyorName;
	private static String surveyTag;
	private static String customerId;
	private static String ethMthMin;
	private static String ethMthMax;
	
	@BeforeClass
	public static void beforeClass() {
		testAccount = null;
		initializeTestObjects();
	}
	
	@AfterClass
	public static void afterClass() {
		if(testAccount!=null && customerId!=null){
			cleanUpGisData(customerId);
		}
	}
	
	@Before
	public void beforeTest() throws Exception {
		initializeTestObjects();

		initializePageActions();

		initializePageObjects();

		// Select run mode here.
		setPropertiesForTestRunMode();

		if(testAccount == null){
			testAccount = createTestAccount("CusWithoutAsset");
			userName = testAccount.get("userName");
			userPassword = testAccount.get("userPassword");
			customerName = testAccount.get("customerName");
			locationName = testAccount.get("locationName");
			analyzerSharedKey = testAccount.get("analyzerSharedKey");
			analyzerName = testAccount.get("analyzerName");
			analyzerType = testAccount.get("analyzerType");
			surveyorName = testAccount.get("surveyorName");
			customerId = testAccount.get("customerId");
			ethMthMin = "1";
			ethMthMax = "2";
			testSurvey = addTestSurvey(analyzerName, analyzerSharedKey, userName, userPassword, SurveyType.Standard);
			surveyTag = testSurvey.get(SurveyType.Standard.toString()+"Tag");
		} else {
			getLoginPage().open();
			getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
			manageLocationPageAction.open(EMPTY, NOTSET);
			manageLocationPageAction.getManageLocationsPage().editEthaneToMethaneRatio(customerName,locationName,ethMthMin, ethMthMax);
			getHomePage().logout();
		}
	}

	private void initializePageObjects() {
		PageObjectFactory pageObjectFactory = new PageObjectFactory();

		measurementSessionsPage = pageObjectFactory.getMeasurementSessionsPage();
		PageFactory.initElements(getDriver(),  measurementSessionsPage);

		LoginPage loginPage = pageObjectFactory.getLoginPage();
		setLoginPage(loginPage);
		PageFactory.initElements(getDriver(), loginPage);

		HomePage homePage = pageObjectFactory.getHomePage();
		setHomePage(homePage);
		PageFactory.initElements(getDriver(), homePage);
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
		loginPageAction = ActionBuilder.createLoginPageAction();
		complianceReportsPageAction = ActionBuilder.createComplianceReportsPageAction();

		setReportsPage((ComplianceReportsPage)complianceReportsPageAction.getPageObject());

		ActionBuilder.createManageCustomerPageAction();
		ActionBuilder.createManageUsersPageAction();
		manageLocationPageAction = ActionBuilder.createManageLocationPageAction();

		ActionBuilder.createManageAnalyzerPageAction();
		ActionBuilder.createManageSurveyorPageAction();
		ActionBuilder.createManageRefGasBottlePageAction();
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
		createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerCloseButton(EMPTY, getReportRowID(reportDataRowID1));

		measurementSessionsPage.open();
		measurementSessionsPage.performSearch(PICADMNSTDTAG2);
		measurementSessionsPage.clickOnFirstSurveyDeleteLink();
		assertTrue(getHomePage().getReturnHomePage().isEnabled());
		assertTrue(getHomePage().getReturnHomePage().isDisplayed());
		getHomePage().getReturnHomePage().click();
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

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);

		this.getComplianceReportsPage().open();

		this.getComplianceReportsPage().getNewReportBtn().click();
		this.getComplianceReportsPage().waitForNewPageLoad();

		assertFalse(WebElementExtender.isElementPresentAndDisplayed(this.getComplianceReportsPage().getPercentCoverForecast()));
		this.getComplianceReportsPage().clickOnCancelBtn();

		this.getComplianceReportsPage().clickOnFirstCopyComplianceBtn();
		this.getComplianceReportsPage().waitForCopyReportPagetoLoad();
		assertFalse(WebElementExtender.isElementPresentAndDisplayed(this.getComplianceReportsPage().getPercentCoverForecast()));
	}

	/**
	 *  Test Case ID: TC2423_ReportsUsEToMRatioFromLatestSurvey() {
	 *  Description: Analytics - Use the values from latest survey included in report - (sort by date desc)
	 *   for both report and copy report to avoid picking up the survey which was inserted first in ReportDrivingSurveys table.
	 * 	 Reports should use Ethane to Methane Ratio from latest survey
	 *	Script:
	 * - Log into the UI as Picarro Admin
	 * - Navigate to the Manage Locations page, select a location, click on Edit
	 * - Set Ethane to Methane Ratio to 2 to 10 and click OK
	 * - Log into the tablet for an analyzer associated to the above location
	 * - Click Mode -> Start Survey
	 * - Enter a tag, select environmental conditions and select Standard Survey Mode and click Start Survey
	 * - Run a survey that includes several indications of different dispositions
	 * - Log back into the UI as Picarro Admin and set Ethane to Methane Ratio to 5 to 10 and click OK
	 * - Restart the PSA
	 * - Log into the tablet and start a new survey
	 * - Log into the UI and navigate to the Compliance Report page
	 * - Click on the New Compliance Report page
	 * - Enter a tag, uncheck all Indication Filters, set Exclusion Radius to 0, select boundary area, add the second survey, then the first survey
	 * - Select LISAs, Indications in Views section, select Indication Table and click OK
	 * - Repeat report generation, but add surveys in reverse order
	 * - Check DB and run these queries:
	 * select  * from ReportDrivingSurvey where reportid like '<report 1 id>'
	 * select  * from ReportDrivingSurvey where reportid like '<report 2 id>'
	 * - Compare SSRS for both reports
	 *
	 *	Verifications:
	 * - After adding surveys for first report, surveys should appear with first survey on top and second survey below
	 * - After adding surveys for second report, surveys should appear with second survey on top and first survey below
	 * - Query results should show that surveys were added in different order for both reports
	 * - SSRS for both reports should be identical
	 **/
	@Ignore /* Description changed, depends on US4458 */
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2423, location = ComplianceReportDataProvider.class)
	public void TC2423_ReportsUsEToMRatioFromLatestSurvey(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2423_ReportsUsEToMRatioFromLatestSurvey ...");
		
		String newEthMthMin = "2";
		String newEthMthMax = "10";
		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
		manageLocationPageAction.open(EMPTY, NOTSET);
		manageLocationPageAction.getManageLocationsPage().editEthaneToMethaneRatio(customerName,locationName,newEthMthMin, newEthMthMax);
		getHomePage().logout();

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);
		Map<String, String> testSurvey1 = addTestSurvey(analyzerName, analyzerSharedKey, CapabilityType.IsotopicMethane,
				 userName, userPassword, 100, SurveyType.Standard);
		String surveyTag1 = testSurvey1.get(SurveyType.Standard.toString()+"Tag");
		
		newEthMthMin = "5";
		newEthMthMax = "10";
		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
		manageLocationPageAction.open(EMPTY, NOTSET);
		manageLocationPageAction.getManageLocationsPage().editEthaneToMethaneRatio(customerName,locationName,newEthMthMin, newEthMthMax);
		getHomePage().logout();

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);
		Map<String, String> testSurvey2 = addTestSurvey(analyzerName, analyzerSharedKey, CapabilityType.IsotopicMethane,
				 userName, userPassword, 100, SurveyType.Standard);
		String surveyTag2 = testSurvey2.get(SurveyType.Standard.toString()+"Tag");

		Map<String, String> testReport1 = addTestReport(userName, userPassword, customerName, surveyTag1+":"+surveyTag2, reportDataRowID1, SurveyModeFilter.Standard);
		String reportTitle1 = testReport1.get(SurveyModeFilter.Standard.toString()+"Title");
		String reportId1 = testReport1.get(SurveyModeFilter.Standard.toString()+"ReportName");
		String reportName1 = complianceReportsPageAction.getComplianceReportsPage().getReportPrefix() + "-"+reportId1.substring(0, 6);
		complianceReportsPageAction.getComplianceReportsPage().clickComplianceReportButton(reportTitle1, userName, ReportsButtonType.ReportViewer, false);
		complianceReportsPageAction.getComplianceReportsPage().waitForReportViewerDialogToOpen();
		complianceReportsPageAction.getComplianceReportsPage().waitForPdfReportIcontoAppear();
		complianceReportsPageAction.getComplianceReportsPage().invokePDFFileDownload(reportTitle1);
		complianceReportsPageAction.getComplianceReportsPage().waitForPDFFileDownload(reportName1);
		
		Map<String, String> testReport2 = addTestReport(userName, userPassword, customerName, surveyTag1+":"+surveyTag2, reportDataRowID1, SurveyModeFilter.Standard);
		String reportTitle2 = testReport2.get(SurveyModeFilter.Standard.toString()+"Title");
		String reportId2 = testReport2.get(SurveyModeFilter.Standard.toString()+"ReportName");
		String reportName2 = complianceReportsPageAction.getComplianceReportsPage().getReportPrefix() + "-"+reportId2.substring(0, 6);
		
		complianceReportsPageAction.getComplianceReportsPage().clickComplianceReportButton(reportTitle2, userName, ReportsButtonType.ReportViewer, false);
		complianceReportsPageAction.getComplianceReportsPage().waitForReportViewerDialogToOpen();
		complianceReportsPageAction.getComplianceReportsPage().waitForPdfReportIcontoAppear();
		complianceReportsPageAction.getComplianceReportsPage().invokePDFFileDownload(reportTitle2);
		complianceReportsPageAction.getComplianceReportsPage().waitForPDFFileDownload(reportName2);
	}
}
