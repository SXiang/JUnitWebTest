package surveyor.regression.source;

import common.source.BaseHelper;
import common.source.FunctionUtil;
import common.source.Log;
import common.source.TestContext;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
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
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.entities.CustomerSurveyInfoEntity;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.scommon.generators.TestDataGenerator;
import surveyor.dataaccess.source.Analyzer.CapabilityType;
import surveyor.dataaccess.source.Customer;
import surveyor.dataprovider.AnalyticReportDataProvider;
import surveyor.dbseed.source.DbSeedExecutor;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.BaseTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.ReportsCommonPage.ReportsButtonType;
import surveyor.scommon.source.SurveyorConstants.MinAmplitudeType;

@RunWith(SurveyorTestRunner.class)
public class AnalyticsReportsWithNewSurveyPageTest extends BaseReportsPageActionTest {

	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static ManageLocationPageActions manageLocationPageActions;
	private static LoginPageActions loginPageAction;
	private static DriverViewPage driverViewPage;

	private static MeasurementSessionsPage measurementSessionsPage;


	private static Map<String, String> testAccount, testSurvey;
	private static String userName;
	private static String userPassword;
	private static String customerName;
	private static String locationName;

	@Rule
	public TestName testName = new TestName();

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

		if (testCaseNeedsSetupData(testName)) {
			if(testAccount == null){
				testAccount = createTestAccount("Analytics_Report", CapabilityType.Ethane);
				userName = testAccount.get("userName");
				userPassword = testAccount.get("userPassword");
				customerName = testAccount.get("customerName");
				locationName = testAccount.get("locationName");
				manageLocationPageActions.open(EMPTY, NOTSET);
				manageLocationPageActions.getManageLocationsPage().editSurveyMinAmplitude(customerName,locationName,"0.035");
				testSurvey = addTestSurvey(testAccount.get("analyzerName"), testAccount.get("analyzerSharedKey"), CapabilityType.Ethane
						,testAccount.get("userName"), testAccount.get("userPassword"), 220, SurveyType.Analytics);
				pushGisData(testAccount.get("customerId"));
				surveyTag = testSurvey.get(SurveyType.Analytics.toString()+"Tag");
			} else {
				getLoginPage().open();
				getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
				manageLocationPageActions.open(EMPTY, NOTSET);
				manageLocationPageActions.getManageLocationsPage().editSurveyMinAmplitude(customerName,locationName,"0.035");
			}
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

	private boolean testCaseNeedsSetupData(TestName testName) {
		String methodName = testName.getMethodName();
		if (!methodName.startsWith("TC2418_AnalyticsLowSurveyMinAmplitudeAndHighRankingMinAmplitude")
				&& !methodName.startsWith("TC2401_AdminConfigurationScreenForCustomerLocationSpecificAnalyticsParametersTopPercentPS")
				&& !methodName.startsWith("TC2389_AdminConfigurationScreenCustomerLocationSpecificAnalyticsParametersRankingMinAmplitude")) {
			return true;
		}

		return false;
	}

	/**
	 * Initializes the page action objects.
	 * @throws Exception
	 */
	protected static void initializePageActions() throws Exception {
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
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
	 *	- Click on New Compliance Report, enter a Report Title, select Report Mode: Analytics, select a boundary that includes the above survey area, select LISAs and indications from the View options and select Indication Table and click OK
	 *	- Once the report generates, check the SSRS PDF
	 *
	 *	Verifications:
	 *	- All of the indications below the Ranking Min Amplitude should have been filtered out.
	 * @throws Exception
	 **/
	@Test
	@UseDataProvider(value = AnalyticReportDataProvider.ANALYTIC_REPORT_DATA_PROVIDER_TC2418, location = AnalyticReportDataProvider.class)
	public void TC2418_AnalyticsLowSurveyMinAmplitudeAndHighRankingMinAmplitude(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2418_AnalyticsLowSurveyMinAmplitudeAndHighRankingMinAmplitude ...");

		// Create location with desired min amp. Generate survey with multiple peaks above and below Ranking min amp.

		final int DB3_ANALYZER_ROW_ID = 68;	 	  /* TestEnvironment datasheet rowID (specifies Analyzer, Replay DB3) */
		final int SURVEY_ROW_ID = 61;	 		  /* Survey information  */
		final int SURVEY_RUNTIME_IN_SECONDS = 200; /* Number of seconds to run the survey for. */
		final int newCustomerRowID = 14;
		final int newLocationRowID = 18;
		final int newCustomerUserRowID = 28;
		final int newSurveyorRowID = 26;
		final int newAnalyzerRowID = 24;
		final int newRefGasBottleRowID = 7;

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		CustomerSurveyInfoEntity custSrvInfo = new CustomerSurveyInfoEntity(newCustomerRowID, newLocationRowID, newCustomerUserRowID, newAnalyzerRowID,
				newSurveyorRowID, newRefGasBottleRowID, DB3_ANALYZER_ROW_ID, SURVEY_RUNTIME_IN_SECONDS, SURVEY_ROW_ID);
		custSrvInfo.setPushGISSeedData(true);
		custSrvInfo.setRetainGISSeedData(true);

		try {
			new TestDataGenerator().generateNewCustomerAndSurvey(custSrvInfo, (driverPageAction) -> {
				assertTrue(driverPageAction.verifyCorrectAnalyticsSurveyActiveMessageIsShownOnMap(EMPTY, NOTSET));
				return true;
			});

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.getLoginPage().loginNormalAs(ManageUsersPageActions.workingDataRow.get().username, ManageUsersPageActions.workingDataRow.get().password);

			measurementSessionsPage.open();
			measurementSessionsPage.waitForFirstSurveyInTableToBeCompleted();

			complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
			createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
			waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

			assertTrue(complianceReportsPageAction.verifyLISAsIndicationTableInfo(EMPTY, getReportRowID(reportDataRowID1)));

			// TODO: Add additional verifications post DE2950 fixed to ensure correct indications are showing up in report.

		} catch (Exception ex) {
			BaseTest.reportTestFailed(ex, AnalyticsReportsWithNewSurveyPageTest.class.getName());
		} finally {
			cleanupReports(ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());
			// Remove GIS seed from the customer.
			FunctionUtil.warnOnError(() -> DbSeedExecutor.cleanUpGisSeed(Customer.getCustomer(ManageCustomerPageActions.workingDataRow.get().name).getId()));
		}
	}

	/**
	 * Test Case: TC2389_AdminConfigurationScreenCustomerLocationSpecificAnalyticsParametersRankingMinAmplitude
	 * Description: Admin configuration screen for customer-location-specific analytics parameters - Ranking Min Amplitude
	 * Pre-Conditions:
	 *  - Existing customer with Analytics license
	 *  - Existing Analytics surveys
	 * Script:
	 *	- Log into the UI as a Picarro Admin and navigate to the Locations configuration page for a customer
	 *	- Set the Ranking Min Amplitude value to 0.035 and click OK
	 *	- Generate an Analytics Report
	 *	- Check the SSRS
	 *	- Navigate back to the Locations configuration page and set the Ranking Min Amplitude value to 0.1 (or higher, depending on the peaks that are present in the report) and click OK
	 *	- Generate another Analytics Report
	 *	- Check the SSRS
	 * Verifications:
	 *	- After the first report is run, the SSRS should show that peaks with amplitude as low as 0.035 are included in the rankings. Peaks below this threshold are not included
	 *	- After the second report is run, the SSRS should show that peaks only down to 0.1 (or whatever the new value entered) are included in the rankings
	**/
	@Test
	@UseDataProvider(value = AnalyticReportDataProvider.ANALYTIC_REPORT_DATA_PROVIDER_TC2389, location = AnalyticReportDataProvider.class)
	public void TC2389_AdminConfigurationScreenCustomerLocationSpecificAnalyticsParametersRankingMinAmplitude(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2389_AdminConfigurationScreenCustomerLocationSpecificAnalyticsParametersRankingMinAmplitude ...");

		// Create location with desired min amp. Generate survey with multiple peaks above and below Ranking min amp.

		final int DB3_ANALYZER_ROW_ID = 71;	 	  /* TestEnvironment datasheet rowID (specifies Analyzer, Replay DB3) */
		final int SURVEY_ROW_ID = 61;	 		  /* Survey information  */
		final int SURVEY_RUNTIME_IN_SECONDS = 200; /* Number of seconds to run the survey for. */
		final int newCustomerRowID = 14;
		final int newLocationRowID = 20;
		final int newCustomerUserRowID = 30;
		final int newSurveyorRowID = 28;
		final int newAnalyzerRowID = 26;
		final int newRefGasBottleRowID = 10;

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		CustomerSurveyInfoEntity custSrvInfo = new CustomerSurveyInfoEntity(newCustomerRowID, newLocationRowID, newCustomerUserRowID, newAnalyzerRowID,
				newSurveyorRowID, newRefGasBottleRowID, DB3_ANALYZER_ROW_ID, SURVEY_RUNTIME_IN_SECONDS, SURVEY_ROW_ID);
		custSrvInfo.setPushGISSeedData(true);
		custSrvInfo.setRetainGISSeedData(true);

		try {
			new TestDataGenerator().generateNewCustomerAndSurvey(custSrvInfo, (driverPageAction) -> {
				assertTrue(driverPageAction.verifyCorrectAnalyticsSurveyActiveMessageIsShownOnMap(EMPTY, NOTSET));
				return true;
			});

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.getLoginPage().loginNormalAs(ManageUsersPageActions.workingDataRow.get().username, ManageUsersPageActions.workingDataRow.get().password);

			measurementSessionsPage.open();
			measurementSessionsPage.waitForFirstSurveyInTableToBeCompleted();

			complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
			createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
			waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

			Float locationMinAmp = manageLocationPageActions.getMinAmplitudeForLocation(newLocationRowID, MinAmplitudeType.Survey_Analytics_Ranking);
			assertTrue(complianceReportsPageAction.verifyLISAsIndicationTableMinAmplitudeValues(String.valueOf(locationMinAmp), NOTSET));

			final String customerName = ManageCustomerPageActions.workingDataRow.get().name;
			final String locationName = ManageLocationPageActions.workingDataRow.get().name;
			final String newAnalyticsRankingMinAmp = "0.1";

			// login as admin and update analytics location properties.
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

			manageLocationPageActions.open(EMPTY, NOTSET);
			manageLocationPageActions.getManageLocationsPage().performSearch(locationName);
			manageLocationPageActions.getManageLocationsPage().editRankingMinAmplitude(customerName,locationName,newAnalyticsRankingMinAmp);

			// login back as user and create analytics report.
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.getLoginPage().loginNormalAs(ManageUsersPageActions.workingDataRow.get().username, ManageUsersPageActions.workingDataRow.get().password);

			complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
			createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
			waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
			assertTrue(complianceReportsPageAction.verifyLISAsIndicationTableMinAmplitudeValues(newAnalyticsRankingMinAmp, NOTSET));

		} catch (Exception ex) {
			BaseTest.reportTestFailed(ex, AnalyticsReportsWithNewSurveyPageTest.class.getName());
		} finally {
			cleanupReports(ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());
			// Remove GIS seed from the customer.
			FunctionUtil.warnOnError(() -> DbSeedExecutor.cleanUpGisSeed(Customer.getCustomer(ManageCustomerPageActions.workingDataRow.get().name).getId()));
		}
	}

	/**
	 * Test Case: TC2401_AdminConfigurationScreenForCustomerLocationSpecificAnalyticsParametersTopPercentPS
	 * Description: Admin configuration screen for customer-location-specific analytics parameters - Top % PS
	 * Pre-Conditions:
	 *  - Existing customer with Analytics license
 	 *  - Existing Analytics surveys with LISAs of different amplitudes - ideally, these would produce a report that has LISAs with Ranking Grades  of 1, 2, 3 and 4
	 * Script:
	 *	- Log into the UI as a Picarro Admin and navigate to the Locations configuration page for a customer
	 *	- Set the Top 10% PS value to 1, the Top 25% PS value to .5, the Top 50% PS value to .1 and click OK
	 *	- Navigate to Reports -> Compliance Reports and click on New Compliance Report
	 *	- Enter a report Title, select the above customer, select Report Mode: Analytics
	 *	- Select a boundary, select one or more surveys run by a surveyor associated to the above location that have several indications of varying amplitudes, select all View and GIS options, select Indication table and click OK
	 *	- Once the report has completed, check the AnalyticsPeak table in the DB for that report
	 *	- Navigate back to the Locations page and change the Top 10% PS value to .1, Top 25% PS value to .05, Top 50% PS value to .035 and click OK
	 *	- Copy the above generated report and compare the PDF and CSV of the first report with those of the second
	 * Verifications:
	 *	- Verify that the peaks with PS above 1 are in Ranking Grade 1, those between.5 and 1 are in Ranking Grade 2, those between .1 and .5 are in Ranking Grade 3 and there are no peaks below .1The second report's results should reflect the changes made on the Locations page
	 *	- In the second report's ReportLISAs_Analytics.csv, the Ranking Grades should have changed - some of the LISAs should have jumped to a higher rank. Verify that the DB reflects the changes made to the PS values
	**/
	@Test
	@UseDataProvider(value = AnalyticReportDataProvider.ANALYTIC_REPORT_DATA_PROVIDER_TC2401, location = AnalyticReportDataProvider.class)
	public void TC2401_AdminConfigurationScreenForCustomerLocationSpecificAnalyticsParametersTopPercentPS(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2401_AdminConfigurationScreenForCustomerLocationSpecificAnalyticsParametersTopPercentPS ...");

		// Create location with desired min amp. Generate survey with multiple peaks above and below Ranking min amp.

		final int DB3_ANALYZER_ROW_ID = 69;	 	  /* TestEnvironment datasheet rowID (specifies Analyzer, Replay DB3) */
		final int SURVEY_ROW_ID = 61;	 		  /* Survey information  */
		final int SURVEY_RUNTIME_IN_SECONDS = 180; /* Number of seconds to run the survey for. */
		final int newCustomerRowID = 14;
		final int newLocationRowID = 19;
		final int newCustomerUserRowID = 29;
		final int newSurveyorRowID = 27;
		final int newAnalyzerRowID = 25;
		final int newRefGasBottleRowID = 9;

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		CustomerSurveyInfoEntity custSrvInfo = new CustomerSurveyInfoEntity(newCustomerRowID, newLocationRowID, newCustomerUserRowID, newAnalyzerRowID,
				newSurveyorRowID, newRefGasBottleRowID, DB3_ANALYZER_ROW_ID, SURVEY_RUNTIME_IN_SECONDS, SURVEY_ROW_ID);
		custSrvInfo.setPushGISSeedData(true);
		custSrvInfo.setRetainGISSeedData(true);

		try {
			new TestDataGenerator().generateNewCustomerAndSurvey(custSrvInfo, (driverPageAction) -> {
				assertTrue(driverPageAction.verifyCorrectAnalyticsSurveyActiveMessageIsShownOnMap(EMPTY, NOTSET));
				return true;
			});

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.getLoginPage().loginNormalAs(ManageUsersPageActions.workingDataRow.get().username, ManageUsersPageActions.workingDataRow.get().password);

			measurementSessionsPage.open();
			measurementSessionsPage.waitForFirstSurveyInTableToBeCompleted();

			complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
			createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
			waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.verifyAnalyticsPeakInfoIsCorrectInDB(EMPTY, getReportRowID(reportDataRowID1));

			final String customerName = ManageCustomerPageActions.workingDataRow.get().name;
			final String locationName = ManageLocationPageActions.workingDataRow.get().name;
			final String newTop10PS = "0.1";
			final String newTop25PS = "0.05";
			final String newTop50PS = "0.035";

			// login as admin and update analytics location properties.
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

			manageLocationPageActions.open(EMPTY, NOTSET);
			manageLocationPageActions.getManageLocationsPage().performSearch(locationName);
			manageLocationPageActions.getManageLocationsPage().editLocationTopPSValues(customerName, locationName, newTop10PS, newTop25PS, newTop50PS);

			// login back as user and create analytics report.
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.getLoginPage().loginNormalAs(ManageUsersPageActions.workingDataRow.get().username, ManageUsersPageActions.workingDataRow.get().password);

			complianceReportsPageAction.open(testCaseID, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.copyReport(ComplianceReportsPageActions.workingDataRow.get().title, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.clickOnOKButton(EMPTY, getReportRowID(reportDataRowID1));
			waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.verifyAnalyticsPeakInfoIsCorrectInDB(String.format("%s:%s:%s", newTop10PS, newTop25PS, newTop50PS),
					getReportRowID(reportDataRowID1));

		} catch (Exception ex) {
			BaseTest.reportTestFailed(ex, AnalyticsReportsWithNewSurveyPageTest.class.getName());
		} finally {
			cleanupReports(ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());
			// Remove GIS seed from the customer.
			FunctionUtil.warnOnError(() -> DbSeedExecutor.cleanUpGisSeed(Customer.getCustomer(ManageCustomerPageActions.workingDataRow.get().name).getId()));
		}
	}

	private void cleanupReports(String rptTitle, String strCreatedBy) throws Exception {
		// Delete report before deleting GIS data pushed by test to prevent FK constraint violation.
		// Delete both the original report and the copy compliance report.
		for (int i = 0; i < 2; i++) {
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(String.format("%s:%s", PICDFADMIN, PICADMINPSWD), NOTSET);

			complianceReportsPageAction.open(EMPTY, NOTSET);
			complianceReportsPageAction.getComplianceReportsPage().searchAndDeleteReport(rptTitle, strCreatedBy);
		}
	}
}
