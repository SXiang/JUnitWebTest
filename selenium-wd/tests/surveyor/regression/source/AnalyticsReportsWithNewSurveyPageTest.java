package surveyor.regression.source;

import common.source.BaseHelper;
import common.source.Log;
import common.source.TestContext;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.DEFAULT_PSFILTER_THRESHOLD;

import java.util.Map;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.Test;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.dataaccess.source.Analyzer.CapabilityType;
import surveyor.dataprovider.AnalyticReportDataProvider;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.ReportsCommonPage.ReportFileType;
import surveyor.scommon.source.ReportsCommonPage.ReportsButtonType;

@RunWith(SurveyorTestRunner.class)
public class AnalyticsReportsWithNewSurveyPageTest extends BaseReportsPageActionTest {

	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static ManageLocationPageActions manageLocationPageActions;
	private static DriverViewPage driverViewPage;
	private static Map<String, String> testAccount, testSurvey;
	private static String userName;
	private static String userPassword;
	private static String customerName;
	private static String locationName;
	private static String surveyTag;
	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects();
	}

	@AfterClass
	public static void afterClass() {
		if(testAccount!=null && testAccount.get("customerId")!=null){
			cleanUpGisData(testAccount.get("customerId"));
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
			testAccount = createTestAccount("Analytics_Report", CapabilityType.Ethane);
			userName = testAccount.get("userName");
			userPassword = testAccount.get("userPassword");
			customerName = testAccount.get("customerName");
			locationName = testAccount.get("locationName");
			manageLocationPageActions.open(EMPTY, NOTSET);
			manageLocationPageActions.getManageLocationsPage().editSurveyMinAmplitude(customerName,locationName,"0.035");
			testSurvey = addTestSurvey(testAccount.get("analyzerName"), testAccount.get("analyzerSharedKey"), CapabilityType.Ethane
					,testAccount.get("userName"), testAccount.get("userPassword"), 300, SurveyType.Analytics);
			pushGisData(testAccount.get("customerId"));
			surveyTag = testSurvey.get(SurveyType.Analytics.toString()+"Tag");
		}else{
			getLoginPage().open();
			getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
			manageLocationPageActions.open(EMPTY, NOTSET);
			manageLocationPageActions.getManageLocationsPage().editSurveyMinAmplitude(customerName,locationName,"0.035");
		}
	}

	private static void setPropertiesForTestRunMode() throws Exception {
		setTestRunMode(ReportTestRunMode.FullTestRun);

		if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			complianceReportsPageAction.fillWorkingDataForReports(getUnitTestReportRowID());
		}
	}

	private void initializePageObjects() {
		PageObjectFactory pageObjectFactory = new PageObjectFactory();

		driverViewPage = pageObjectFactory.getDriverViewPage();
		PageFactory.initElements(getDriver(), driverViewPage);

		LoginPage loginPage = pageObjectFactory.getLoginPage();
		setLoginPage(loginPage);
		PageFactory.initElements(getDriver(), loginPage);

		HomePage homePage = pageObjectFactory.getHomePage();
		setHomePage(homePage);
		PageFactory.initElements(getDriver(), homePage);
	}

	/**
	 * Initializes the page action objects.
	 * @throws Exception
	 */
	protected static void initializePageActions() throws Exception {
		new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
		complianceReportsPageAction = new ComplianceReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
		manageLocationPageActions = new ManageLocationPageActions(getDriver(), getBaseURL(), getTestSetup());
		setReportsPage((ComplianceReportsPage)complianceReportsPageAction.getPageObject());
	}

	/**
	 * Test Case ID: TC2383_AnalyticsReportRankingGroup4WithFilterPS
	 * Test Description: - Admin configuration screen for customer-location-specific analytics parameters - Filter PS
	 * Script:
	 *	- Log into the UI as a Picarro Admin and navigate to the Compliance Reports page
	 *	- Click on New Compliance Report
	 *	- Select a customer that has existing Analytics surveys available, enter a Report Title, select Report Mode: Analytics
	 *	- Select or draw a report boundary and add surveys (all surveys should be from analyzers tied to the same Customer Location)
	 *	- In the Views section, select all options, all GIS layers and Indication table
	 *	- Click OK
	 *	- After the report has generated, navigate to the Location page for the selected surveys and change the Filter PS value to a higher number (for example, from 0.5 to 0.9)
	 *	- Click the Copy button for the report generated above
	 *	- Once the second report has completed, click on the Report Viewer -> Compliance ZIP Meta button and open the ReportLISA_analytics.csv file
	 *	- Open the same file for the first report and compare them
	 *	- Check the AnalyticsPeak table in the DB
	 * Results:
	 *	- Upon comparison, the second report should have fewer peaks marked as Ranking Group 4 than in the first report.
	 *	- For Ranking Group 1, 2 and 3, the counts should be identical

	 */
	@Test
	@UseDataProvider(value = AnalyticReportDataProvider.ANALYTIC_REPORT_DATA_PROVIDER_TC2383, location = AnalyticReportDataProvider.class)
	public void TC2383_AnalyticsReportRankingGroup4WithFilterPS(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2383_AnalyticsReportRankingGroup4WithFilterPS ..." +
				"\nTest Description: Admin configuration screen for customer-location-specific analytics parameters - Filter PS");

		String[] psFilters = {"0.01", DEFAULT_PSFILTER_THRESHOLD};

			//Modify psFilter
			getLoginPage().open();
			getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
			manageLocationPageActions.open(EMPTY, NOTSET);
			manageLocationPageActions.getManageLocationsPage().editLocationPSFilterThreshold(customerName,locationName,psFilters[0]);
			getHomePage().logout();

			//Generate report and verify indications
			getLoginPage().open();
			getLoginPage().loginNormalAs(userName, userPassword);

			Map<String, String> testReport = addTestReport(userName, userPassword, surveyTag, reportDataRowID1, SurveyModeFilter.Analytics);

			String reportTitle = testReport.get(SurveyModeFilter.Analytics.toString()+"Title");
			String reportName = testReport.get(SurveyModeFilter.Analytics.toString()+"ReportName");
			reportName = complianceReportsPageAction.getComplianceReportsPage().getReportPrefix() + "-"+reportName.substring(0, 6);

			complianceReportsPageAction.getComplianceReportsPage().clickComplianceReportButton(reportTitle, userName, ReportsButtonType.ReportViewer, false);
			complianceReportsPageAction.getComplianceReportsPage().invokeMetaZipFileDownload(reportTitle);
			complianceReportsPageAction.getComplianceReportsPage().waitForMetadataZIPFileDownload(reportName);
			BaseHelper.deCompressZipFile(reportName+"-Meta", TestContext.INSTANCE.getTestSetup().getDownloadPath());
			Map<Integer, Integer> rankingMap1 = complianceReportsPageAction.getComplianceReportsPage().getLISASAnalyticsRankingMap(reportTitle);

			//Modify psFilter
			getLoginPage().open();
			getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
			manageLocationPageActions.open(EMPTY, NOTSET);
			manageLocationPageActions.getManageLocationsPage().editLocationPSFilterThreshold(customerName,locationName,psFilters[1]);
			getHomePage().logout();
			
			getLoginPage().open();
			getLoginPage().loginNormalAs(userName, userPassword);
			complianceReportsPageAction.open(EMPTY, NOTSET);
			complianceReportsPageAction.getComplianceReportsPage().copyReport(reportTitle, userName);
			complianceReportsPageAction.getComplianceReportsPage().clickOnOKButton();
			reportName = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportName(reportTitle, userName);
			reportName = complianceReportsPageAction.getComplianceReportsPage().getReportPrefix() + "-"+reportName.substring(0, 6);
			
			complianceReportsPageAction.getComplianceReportsPage().clickComplianceReportButton(reportTitle, userName, ReportsButtonType.ReportViewer, false);
			complianceReportsPageAction.getComplianceReportsPage().invokeMetaZipFileDownload(reportTitle);
			complianceReportsPageAction.getComplianceReportsPage().waitForMetadataZIPFileDownload(reportName);
			BaseHelper.deCompressZipFile(reportName+"-Meta", TestContext.INSTANCE.getTestSetup().getDownloadPath());
			Map<Integer, Integer> rankingMap2 = complianceReportsPageAction.getComplianceReportsPage().getLISASAnalyticsRankingMap(reportTitle);
			
//			assertEquals(rankingMap1.get(Integer.valueOf(1)), rankingMap2.get(Integer.valueOf(1)));
//			assertEquals(rankingMap1.get(Integer.valueOf(2)), rankingMap2.get(Integer.valueOf(2)));
//			assertEquals(rankingMap1.get(Integer.valueOf(3)), rankingMap2.get(Integer.valueOf(3)));
//			assertTrue(rankingMap1.get(Integer.valueOf(4)) > rankingMap2.get(Integer.valueOf(4)));
			
			getHomePage().logout();
	}
}