package surveyor.regression.source;

import common.source.FunctionUtil;
import common.source.Log;
import common.source.TestContext;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.Test;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.entities.CustomerSurveyInfoEntity;
import surveyor.scommon.generators.TestDataGenerator;
import surveyor.dataaccess.source.Customer;
import surveyor.dataaccess.source.CustomerWithGisDataPool;
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
import surveyor.scommon.source.SurveyorConstants.MinAmplitudeType;

@RunWith(SurveyorTestRunner.class)
public class AnalyticsReportsWithNewSurveyPageTest extends BaseReportsPageActionTest {

	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static ManageLocationPageActions manageLocationPageActions;
	private static LoginPageActions loginPageAction;
	private static DriverViewPage driverViewPage;

	private static MeasurementSessionsPage measurementSessionsPage;

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects();
	}

	@Before
	public void beforeTest() throws Exception {
		initializeTestObjects();
		initializePageActions();
		initializePageObjects();
		// Select run mode here.
		setPropertiesForTestRunMode();
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
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
		complianceReportsPageAction = new ComplianceReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
		manageLocationPageActions = new ManageLocationPageActions(getDriver(), getBaseURL(), getTestSetup());
		setReportsPage((ComplianceReportsPage)complianceReportsPageAction.getPageObject());
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

		Boolean testFailed = false;

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
				assertTrue(driverPageAction.verifyCorrectSurveyActiveMessageIsShownOnMap(EMPTY, NOTSET));
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
			testFailed = true;
			BaseTest.reportTestFailed(ex, AnalyticsReportsWithNewSurveyPageTest.class.getName());
		} finally {
			if (TestContext.INSTANCE.getTestSetup().isGeoServerEnabled()) {
				CustomerWithGisDataPool.releaseCustomer(ManageCustomerPageActions.workingDataRow.get().name);
			}

			if (!testFailed) {
				cleanupReports(ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());
				// Remove GIS seed from the customer.
				FunctionUtil.warnOnError(() -> DbSeedExecutor.cleanUpGisSeed(Customer.getCustomer(ManageCustomerPageActions.workingDataRow.get().name).getId()));
			}
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

		Boolean testFailed = false;

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
				assertTrue(driverPageAction.verifyCorrectSurveyActiveMessageIsShownOnMap(EMPTY, NOTSET));
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

			complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));

			assertTrue(complianceReportsPageAction.verifyMetaDataZIPFilesArePresent("True:True:True:True", getReportRowID(reportDataRowID1)));
			assertTrue(complianceReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));

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

			complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.extractMetaZIP(EMPTY, getReportRowID(reportDataRowID1));

			assertTrue(complianceReportsPageAction.verifyMetaDataZIPFilesArePresent("True:True:True:True", getReportRowID(reportDataRowID1)));
			assertTrue(complianceReportsPageAction.verifyAllMetadataFiles(EMPTY, getReportRowID(reportDataRowID1)));

		} catch (Exception ex) {
			testFailed = true;
			BaseTest.reportTestFailed(ex, AnalyticsReportsWithNewSurveyPageTest.class.getName());
		} finally {
			if (TestContext.INSTANCE.getTestSetup().isGeoServerEnabled()) {
				CustomerWithGisDataPool.releaseCustomer(ManageCustomerPageActions.workingDataRow.get().name);
			}

			if (!testFailed) {
				cleanupReports(ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());
				// Remove GIS seed from the customer.
				FunctionUtil.warnOnError(() -> DbSeedExecutor.cleanUpGisSeed(Customer.getCustomer(ManageCustomerPageActions.workingDataRow.get().name).getId()));
			}
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
