package surveyor.regression.source;

import common.source.BaseHelper;
import common.source.HostSimDefinitionGenerator;
import common.source.Log;
import common.source.TestContext;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;

import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.Test;
import org.junit.rules.TestName;

import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.dataaccess.source.Analyzer.CapabilityType;
import surveyor.dataaccess.source.Peak;
import surveyor.dataprovider.AnalyticReportDataProvider;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.ReportsCommonPage.ReportsButtonType;

/*
 * Use shared analyzer in this class only !!!
 */
@RunWith(SurveyorTestRunner.class)
public class AnalyticsReportsWithNewSurveyPageTest2 extends BaseReportsPageActionTest {

	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static ManageLocationPageActions manageLocationPageActions;
	private static DriverViewPage driverViewPage;

	private static MeasurementSessionsPage measurementSessionsPage;


	private static Map<String, String> testAccount, testSurvey;
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
	private static String surveyMinAmplitude;
	private static String rankingMinAmplitude;

	@Rule
	public TestName testName = new TestName();

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
				testAccount = createTestAccount("Analytics_Report", CapabilityType.Ethane);				
				userName = testAccount.get("userName");
				userPassword = testAccount.get("userPassword");
				customerName = testAccount.get("customerName");
				locationName = testAccount.get("locationName");
				analyzerSharedKey = testAccount.get("analyzerSharedKey");
				analyzerName = testAccount.get("analyzerName");
				analyzerType = testAccount.get("analyzerType");
				surveyorName = testAccount.get("surveyorName");
				customerId = testAccount.get("customerId");
				surveyMinAmplitude = "0.035";
				rankingMinAmplitude = "0.035";
				manageLocationPageActions.open(EMPTY, NOTSET);
				manageLocationPageActions.getManageLocationsPage().editSurveyMinAmplitude(customerName,locationName,surveyMinAmplitude);
				manageLocationPageActions.getManageLocationsPage().editRankingMinAmplitude(customerName,locationName,rankingMinAmplitude);
				testSurvey = addTestSurvey(testAccount.get("analyzerName"), testAccount.get("analyzerSharedKey"), CapabilityType.Ethane
						,testAccount.get("userName"), testAccount.get("userPassword"), 220, SurveyType.Analytics);
				pushGisData(testAccount.get("customerId"));
				surveyTag = testSurvey.get(SurveyType.Analytics.toString()+"Tag");
			} else {
				getLoginPage().open();
				getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
				manageLocationPageActions.open(EMPTY, NOTSET);
				manageLocationPageActions.getManageLocationsPage().editSurveyMinAmplitude(customerName,locationName,surveyMinAmplitude);
				manageLocationPageActions.getManageLocationsPage().editRankingMinAmplitude(customerName,locationName,rankingMinAmplitude);
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

		measurementSessionsPage = pageObjectFactory.getMeasurementSessionsPage();
		PageFactory.initElements(getDriver(), measurementSessionsPage);
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

		String[] psFilters = {"0.01", "0.2"};

		//Modify psFilter
		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
		manageLocationPageActions.open(EMPTY, NOTSET);
		manageLocationPageActions.getManageLocationsPage().editLocationPSFilterThreshold(customerName,locationName,psFilters[0]);
		getHomePage().logout();

		//Generate report and verify indications
		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);

		Map<String, String> testReport = addTestReport(userName, userPassword, customerName, surveyTag, reportDataRowID1, SurveyModeFilter.Analytics);

		String reportTitle = testReport.get(SurveyModeFilter.Analytics.toString()+"Title");
		String reportName = testReport.get(SurveyModeFilter.Analytics.toString()+"ReportName");
		reportName = complianceReportsPageAction.getComplianceReportsPage().getReportPrefix() + "-"+reportName.substring(0, 6);

		complianceReportsPageAction.getComplianceReportsPage().clickComplianceReportButton(reportTitle, userName, ReportsButtonType.ReportViewer, false);
		complianceReportsPageAction.getComplianceReportsPage().waitForReportViewerDialogToOpen();
		complianceReportsPageAction.getComplianceReportsPage().waitForReportViewImagetoAppear();
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
		complianceReportsPageAction.getComplianceReportsPage().clickOnButtonInReportPage(reportTitle, userName, ReportsButtonType.Copy);
		complianceReportsPageAction.getComplianceReportsPage().clickOnOKButton();
		reportName = complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportName(reportTitle, userName);
		reportName = complianceReportsPageAction.getComplianceReportsPage().getReportPrefix() + "-"+reportName.substring(0, 6);

		complianceReportsPageAction.getComplianceReportsPage().clickComplianceReportButton(reportTitle, userName, ReportsButtonType.ReportViewer, false);
		complianceReportsPageAction.getComplianceReportsPage().waitForReportViewerDialogToOpen();
		complianceReportsPageAction.getComplianceReportsPage().waitForReportViewImagetoAppear();
		complianceReportsPageAction.getComplianceReportsPage().invokeMetaZipFileDownload(reportTitle);
		complianceReportsPageAction.getComplianceReportsPage().waitForMetadataZIPFileDownload(reportName);
		BaseHelper.deCompressZipFile(reportName+"-Meta", TestContext.INSTANCE.getTestSetup().getDownloadPath());
		Map<Integer, Integer> rankingMap2 = complianceReportsPageAction.getComplianceReportsPage().getLISASAnalyticsRankingMap(reportTitle);

		assertEquals(rankingMap1.get(Integer.valueOf(1)), rankingMap2.get(Integer.valueOf(1)));
		assertEquals(rankingMap1.get(Integer.valueOf(2)), rankingMap2.get(Integer.valueOf(2)));
		assertEquals(rankingMap1.get(Integer.valueOf(3)), rankingMap2.get(Integer.valueOf(3)));
		assertTrue(rankingMap1.get(Integer.valueOf(4)) > rankingMap2.get(Integer.valueOf(4)));

		getHomePage().logout();
	}

	/**
	 *  Test Case ID: TC2418_AnalyticsLowSurveyMinAmplitudeAndHighRankingMinAmplitude() {
	 *  Description: Analytics - Low Survey Min Amplitude and High Ranking Min Amplitude
	 *	Script:
	 *	- Log into the UI as Picarro Admin
	 *	- Navigate to the Manage Locations page
	 *	- Select a customer Location and click Edit
	 *	- Scroll down to the Survey Mode: Analytics section
	 *	- Set the Survey Min Amplitude to a low value like 0.035
	 *	- Set the Ranking Min Amplitude to a high value like 2.5 and click OK
	 *	- Log into the tablet as a user of that customer
	 *	- Enter a tag, select the appropriate environmental conditions and select Survey Mode: Analytics and click Start Survey
	 *	- Collect peaks of different amplitudes, from below 1.0 to above 3
	 *	- After the survey has been uploaded, log into the UI and navigate to the Compliance Reports page
	 *	- Click on New Compliance Report, enter a Report Title, select Report Mode: Analytics, select a boundary that includes the above survey area, 
	 *		select LISAs and indications from the View options and select Indication Table and click OK
	 *	- Once the report generates, check the SSRS PDF
	 *
	 *	Verifications:
	 *	- All of the indications below the Ranking Min Amplitude should have been filtered out.
	 **/
	@Test
	@UseDataProvider(value = AnalyticReportDataProvider.ANALYTIC_REPORT_DATA_PROVIDER_TC2418, location = AnalyticReportDataProvider.class)
	public void TC2418_AnalyticsLowSurveyMinAmplitudeAndHighRankingMinAmplitude(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2418_AnalyticsLowSurveyMinAmplitudeAndHighRankingMinAmplitude ...");

		String rankingMinAmplitudeHigh = "0.5";
		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
		manageLocationPageActions.open(EMPTY, NOTSET);
		manageLocationPageActions.getManageLocationsPage().editRankingMinAmplitude(customerName,locationName,rankingMinAmplitudeHigh);
		getHomePage().logout();

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);
		List<Peak> listOfDBPeak = Peak.getPeaks(surveyTag, analyzerName);
		int numPeaksBelowRankingMin = listOfDBPeak.stream().filter(p -> p.getAmplitude() < Float.valueOf(rankingMinAmplitudeHigh)).mapToInt((x) -> 1).sum();
		assertTrue( numPeaksBelowRankingMin > 0);
		Map<String, String> testReport = addTestReport(userName, userPassword, customerName, surveyTag, reportDataRowID1, SurveyModeFilter.Analytics);

		String reportTitle = testReport.get(SurveyModeFilter.Analytics.toString()+"Title");
		String reportName = testReport.get(SurveyModeFilter.Analytics.toString()+"ReportName");
		reportName = complianceReportsPageAction.getComplianceReportsPage().getReportPrefix() + "-"+reportName.substring(0, 6);

		complianceReportsPageAction.getComplianceReportsPage().clickComplianceReportButton(reportTitle, userName, ReportsButtonType.ReportViewer, false);
		complianceReportsPageAction.getComplianceReportsPage().waitForReportViewerDialogToOpen();
		complianceReportsPageAction.getComplianceReportsPage().waitForReportViewImagetoAppear();
		complianceReportsPageAction.getComplianceReportsPage().invokePDFFileDownload(reportTitle);
		complianceReportsPageAction.getComplianceReportsPage().waitForPDFFileDownload(reportName);
		assertTrue(complianceReportsPageAction.verifyLISAsIndicationTableMinAmplitudeValues(rankingMinAmplitudeHigh, NOTSET));
	}

	/**
	 *  Test Case ID: TC2398_AnalyticsLisasAndIndicationsWithREdiGPSIndicator() {
	 *  Description: Analytics - Analytics survey that produced gaps in breadcrumbs due to iGPS indicator turning red. 
	 *  The survey should include LISAs/indications in the areas where the iGPS had not turned red.
	 *	Script:
	 * - Log into the UI and navigate to the Compliance Reports page
	 * - Click on New Compliance Report
	 * - Enter a Report Title and select Report Mode: Analytics
	 * - Select an area, add the appropriate survey(s), select all View options and GIS layers, select Indication Table and click OK
	 * - When the report has completed, download the Map View pdf
	 *
	 *	Verifications:
	 *	- The data on the Map View pdf (breadcrumb, FOV, LISAs/indications) should exactly match that of the included survey(s). 
	 *	The gaps in breadcrumb on the survey should also be present on the Map View pdf. 
	 *	There should be no FOV, LISAs or indications along the gaps
	 **/
	@Test
	@UseDataProvider(value = AnalyticReportDataProvider.ANALYTIC_REPORT_DATA_PROVIDER_TC2398, location = AnalyticReportDataProvider.class)
	public void TC2398_AnalyticsLisasAndIndicationsWithREdiGPSIndicator(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2398_AnalyticsLisasAndIndicationsWithREdiGPSIndicator ...");
		String[] ch4Values = {"5.5", "6.5", "7.5", "8.5", "9.5"};
		String[] c2h6Values = {"3.5", "3.2", "3.0", "3.5", "2.5"};
		String defnFilePath = new HostSimDefinitionGenerator().generateEthDefinitionForiGPSGoingFromBlueToYellowToRed(ch4Values, c2h6Values);

		Map<String, String> testSurvey = addTestSurvey(analyzerName, analyzerSharedKey, CapabilityType.Ethane
				,defnFilePath, userName, userPassword, 300, SurveyType.Analytics);
		String surveyTag = testSurvey.get(SurveyType.Analytics.toString()+"Tag");
		Map<String, String> testReport = addTestReport(userName, userPassword, customerName,  surveyTag,
				reportDataRowID1, SurveyModeFilter.Analytics);

		String reportTitle = testReport.get(SurveyModeFilter.Analytics.toString()+"Title");
		complianceReportsPageAction.getComplianceReportsPage().clickComplianceReportButton(reportTitle, userName, ReportsButtonType.ReportViewer, false);
		complianceReportsPageAction.getComplianceReportsPage().waitForReportViewerDialogToOpen();
		complianceReportsPageAction.getComplianceReportsPage().waitForReportViewImagetoAppear();
		complianceReportsPageAction.clickOnReportViewerView(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForViewDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines("FALSE", getReportRowID(reportDataRowID1)));
	}	

	/**
	 *  Test Case ID: TC2395_ComplianceReportLisasAndIndicationsWithRediGPSIndication() {
	 *  Description: Compliance Report -  LISAs and Indications with red iGPS indicator 
	 *	Script:
	 *	-  Log into the UI and navigate to the Compliance Reports page
	 * - Click on New Compliance Report
	 * - Enter a Report Title and select Report Mode: Standard
	 * - Select an area, add the appropriate survey(s), select all View options and GIS layers, select Indication Table and click OK
	 * - When the report has completed, download the Map View pdf
	 *
	 *	Verifications:
	 *	-The data on the Map View pdf (breadcrumb, FOV, LISAs/indications) should exactly match that of the included survey(s). 
	 * The gaps in breadcrumb on the survey should also be present on the Map View pdf.
	 * There should be no FOV, LISAs or indications along the gaps
	 **/
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2395, location = ComplianceReportDataProvider.class)
	public void TC2395_ComplianceReportLisasAndIndicationsWithRediGPSIndication(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2395_ComplianceReportLisasAndIndicationsWithRediGPSIndication ...");
		String[] ch4Values = {"5.5", "6.5", "7.5", "8.5", "9.5"};
		String[] c2h6Values = {"3.5", "3.2", "3.0", "3.5", "2.5"};
		String defnFilePath = new HostSimDefinitionGenerator().generateMethDefinitionForiGPSGoingFromBlueToYellowToRed(ch4Values, c2h6Values);
		Map<String, String> testSurvey = addTestSurvey(analyzerName, analyzerSharedKey, CapabilityType.IsotopicMethane
				,defnFilePath, userName, userPassword, 300, SurveyType.Standard);
		String surveyTag = testSurvey.get(SurveyType.Standard.toString()+"Tag");
		Map<String, String> testReport = addTestReport(userName, userPassword, customerName, surveyTag, 
				reportDataRowID1, SurveyModeFilter.Standard);
		String reportTitle = testReport.get(SurveyModeFilter.Standard.toString()+"Title");
		String reportName = testReport.get(SurveyModeFilter.Standard.toString()+"ReportName");
		reportName = complianceReportsPageAction.getComplianceReportsPage().getReportPrefix() + "-"+reportName.substring(0, 6);
		
		complianceReportsPageAction.getComplianceReportsPage().clickComplianceReportButton(reportTitle, userName, ReportsButtonType.ReportViewer, false);
		complianceReportsPageAction.getComplianceReportsPage().waitForReportViewerDialogToOpen();
		complianceReportsPageAction.getComplianceReportsPage().waitForReportViewImagetoAppear();
		complianceReportsPageAction.clickOnReportViewerView(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForViewDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines("FALSE", getReportRowID(reportDataRowID1)));
	}
}
