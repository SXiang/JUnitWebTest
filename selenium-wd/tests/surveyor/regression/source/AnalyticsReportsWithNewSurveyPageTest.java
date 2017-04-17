package surveyor.regression.source;

import common.source.Log;

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
import surveyor.scommon.entities.BaseReportEntity.ReportModeFilter;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
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
import surveyor.scommon.source.ReportsCommonPage.ReportsButtonType;

@RunWith(SurveyorTestRunner.class)
public class AnalyticsReportsWithNewSurveyPageTest extends BaseReportsPageActionTest {

	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static ManageLocationPageActions manageLocationPageActions;
	private static DriverViewPage driverViewPage;
	private static Map<String, String> testAccount, testSurvey;
	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects();
	}

	@AfterClass
	public static void afterClass() {
		if(testAccount!=null){
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
			testAccount = createTestAccount("Analytics_Report");
			testSurvey = addTestSurvey(testAccount.get("analyzerName"), testAccount.get("analyzerSharedKey")
					,testAccount.get("userName"), testAccount.get("userPassword"), 1500, SurveyType.Analytics);
			pushGisData(testAccount.get("customerId"));
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
	 * Test Case ID: TC2339_LisasAreFilteredOutAccordingToThresholdLevel
	 * Test Description: - Analytics Report - LISAs are filtered out according to threshold level set on Locations page
	 * 			When an Analytics Report is run, LISAs below the threshold value indicated on the Location page should be filtered out.
	 * 			Changing the threshold level and generating a new report with the same survey should result in filtering according to the newly entered values
	 * Script: -
	 *	- - Log in as Picarro admin user
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button
	 *	- - Select Analytics report and select a survey
	 *	- - Add 2 Views with base map value, satellite map
	 *	- - Click on OK and click Compliance Viewer button
	 * Results: -
	 *	- - Report generated successfully
	 */
	@Test
	@UseDataProvider(value = AnalyticReportDataProvider.ANALYTIC_REPORT_DATA_PROVIDER_TC2339, location = AnalyticReportDataProvider.class)
	public void TC2339_LisasAreFilteredOutAccordingToThresholdLevel(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2339_LisasAreFilteredOutAccordingToThresholdLevel ..." +
			 "\nTest Description:  Analytics Report - LISAs are filtered out according to threshold level set on Locations paget");

		String userName = testAccount.get("userName");
		String userPassword = testAccount.get("userPassword");
		String customerName = testAccount.get("customerName");
		String locationName = testAccount.get("locationName");
		String surveyTag = testSurvey.get(SurveyType.Analytics.toString()+"Tag");
		float[] psFilters = {2.5f,DEFAULT_PSFILTER_THRESHOLD};

		for(float psFilter:psFilters){
			//Modify psFilter
			getLoginPage().open();
			getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
			manageLocationPageActions.open(EMPTY, NOTSET);
			manageLocationPageActions.getManageLocationsPage().editLocationPSFilterThreshold(customerName,locationName,psFilter);
			getHomePage().logout();

			//Generate report and verify indications
			getLoginPage().open();
			getLoginPage().loginNormalAs(userName, userPassword);

			Map<String, String> testReport = addTestReport(userName, userPassword, surveyTag, reportDataRowID1, SurveyModeFilter.Analytics);

			String reportTitle = testReport.get(SurveyModeFilter.Analytics.toString()+"Title");
			String reportName = testReport.get(SurveyModeFilter.Analytics.toString()+"ReportName");
			reportName = complianceReportsPageAction.getComplianceReportsPage().getReportPrefix() + "-"+reportName.substring(0, 6);

			complianceReportsPageAction.getComplianceReportsPage().clickComplianceReportButton(reportTitle, userName, ReportsButtonType.ReportViewer, false);
			complianceReportsPageAction.getComplianceReportsPage().invokePDFFileDownload(reportTitle);
			complianceReportsPageAction.getComplianceReportsPage().waitForPDFFileDownload(reportName);
			assertTrue(complianceReportsPageAction.verifyLISAsIndicationTableInfo(ReportModeFilter.Analytics.toString(), getReportRowID(reportDataRowID1)));
			getHomePage().logout();
		}
	}

}
