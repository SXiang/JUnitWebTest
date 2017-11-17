package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.ALL_LICENSED_FEATURES_ROWIDS_NOLISABOX;
import static surveyor.scommon.source.SurveyorConstants.ALL_LICENSED_FEATURES_ROWIDS_NO_ANALYTICS;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG2;
import common.source.Log;
import common.source.TestContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import surveyor.dataaccess.source.Customer;
import surveyor.dataaccess.source.CustomerWithGisDataPool;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageAnalyzerPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.ManageRefGasBottlesPageActions;
import surveyor.scommon.actions.ManageSurveyorPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.entities.CustomerSurveyInfoEntity;
import surveyor.scommon.generators.TestDataGenerator;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.BaseTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.PageObjectFactory;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsWithNewSurveyPageTest extends BaseReportsPageActionTest {

	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;

	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static MeasurementSessionsPage measurementSessionsPage;

	private static ManageCustomerPageActions manageCustomerPageAction;

	private static ManageUsersPageActions manageUsersPageAction;
	private static ManageLocationPageActions manageLocationPageAction;

	private static ManageAnalyzerPageActions manageAnalyzerPageAction;
	private static ManageSurveyorPageActions manageSurveyorPageAction;
	private static ManageRefGasBottlesPageActions manageRefGasBottlesPageAction;

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

		manageCustomerPageAction = ActionBuilder.createManageCustomerPageAction();
		manageUsersPageAction = ActionBuilder.createManageUsersPageAction();
		manageLocationPageAction = ActionBuilder.createManageLocationPageAction();

		manageAnalyzerPageAction = ActionBuilder.createManageAnalyzerPageAction();
		manageSurveyorPageAction = ActionBuilder.createManageSurveyorPageAction();
		manageRefGasBottlesPageAction = ActionBuilder.createManageRefGasBottlePageAction();
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
	 * Test Case ID: TC689_ShapefileAccessExistingCustomer
	 * Test Description: Shapefile access for existing customer
	 * Script: -
	 *	- Log in as Picarro Admin
	 *	- On Manage Customers page, select a customer that does not have Shapefile generation enabled and click the "Edit" button
	 *	- Confirm that the "Account Enabled" box is checked and check the "Shapefile Generation Enabled" button
	 *	- Click OK
	 *	- On the Compliance Reports page, select a report for that customer that has LISAs, FOV, Breadcrumb, Gaps and/or Assets and click on the thumbnail preview button
	 *	- Click on the Shapefile export button
	 * Results: -
	 *  - - The thumbnail preview grid should include a button for Shapefile export
	 *	- - The Shapefile should download
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC689, location = ComplianceReportDataProvider.class)
	public void TC689_ShapefileAccessExistingCustomer(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC689_ShapefileAccessExistingCustomer ...");

		final int DB3_ANALYZER_ROW_ID = 31;	 	/* TestEnvironment datasheet rowID (specifies Analyzer, Replay DB3) */
		final int SURVEY_ROW_ID = 53;	 		/* Survey information  */

		final int SURVEY_RUNTIME_IN_SECONDS = 15; /* Number of seconds to run the survey for. */

		final int newCustomerRowID = 6;
		final int newLocationRowID = 9;
		final int newCustomerUserRowID = 20;
		final int newSurveyorRowID = 5;
		final int newAnalyzerRowID = 4;
		final int newRefGasBottleRowID = 2;

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		// Add a new user customer with Report ShapeFile first disabled and then enable it.
		String allCustomerLicenseRowIDs = ALL_LICENSED_FEATURES_ROWIDS_NOLISABOX;
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.createOrFetchNewGisCustomer(EMPTY, newCustomerRowID /*customerRowID*/);
		manageCustomerPageAction.editCustomerSelectLicensedFeatures(allCustomerLicenseRowIDs, NOTSET);

		// Create new location.
		manageLocationPageAction.open(EMPTY, NOTSET);
		manageLocationPageAction.createNewLocation(EMPTY, newLocationRowID);

		// Create new user.
		manageUsersPageAction.open(EMPTY, NOTSET);
		manageUsersPageAction.createNewCustomerUser(EMPTY, newCustomerUserRowID /*userRowID*/);

		// Create new surveyor.
		manageSurveyorPageAction.open(EMPTY, NOTSET);
		manageSurveyorPageAction.createNewSurveyor(EMPTY, newSurveyorRowID /*surveyorRowID*/);

		// Create new analyzer.
		manageAnalyzerPageAction.open(EMPTY, NOTSET);
		manageAnalyzerPageAction.createNewAnalyzer(EMPTY, newAnalyzerRowID /*analyzerRowID*/);

		// Create new ref gas bottle.
		manageRefGasBottlesPageAction.open(EMPTY, NOTSET);
		manageRefGasBottlesPageAction.createNewRefGasBottle(EMPTY, newRefGasBottleRowID /*refGasBottleRowID*/);

		// Email ID for the new created user was generated dynamically in this case by using 'GenerateRandomEmail(20)' function.
		// For such cases, use the overload with username and password for generateSurveyForUser().
		String newUsername = ManageUsersPageActions.workingDataRow.get().username;
		String newUserPass = ManageUsersPageActions.workingDataRow.get().password;

		Customer customer = Customer.getCustomer(ManageCustomerPageActions.workingDataRow.get().name);

		Assert.assertTrue(executeAsCustomerWithGISData(complianceReportsPageAction, customer.getId(), reportDataRowID1, pageAction -> {
			try {
				// Create a survey for the new user.
				TestEnvironmentActions.generateSurveyForUser(newUsername, newUserPass,
						DB3_ANALYZER_ROW_ID, SURVEY_ROW_ID, SURVEY_RUNTIME_IN_SECONDS);

				// Re-login as admin user (with username:password format to enforce specific username/password) and create report.
				loginPageAction.open(EMPTY, NOTSET);
				loginPageAction.login(String.format("%s:%s", PICDFADMIN, PICADMINPSWD), NOTSET);

				// Create report for the new customer.
				pageAction.open(EMPTY, getReportRowID(reportDataRowID1));
				createNewReport(pageAction, getReportRowID(reportDataRowID1));
				waitForReportGenerationToComplete(pageAction, getReportRowID(reportDataRowID1));
				pageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
				pageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
				pageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
				pageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
				pageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
				pageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
				pageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
			} catch (Exception ex) {
				return false;
			}
			return true;
		}));
	}

	/* * Test Case ID: TC1960_VerifyReportViewPDFandshapefilesWhenOnlyLISAGapsAndAssetsSelected
	 * Description: Verify report view PDF and shapefiles when only LISA, Gaps and Assets are selected and no assets are highlighted
	 * Script:
	 * - Log in as Picarro Admin
	 * - On the Compliance Reports page, click the New Compliance Report" button.
	 * - Fill out the required fields
	 * - Select a survey that includes LISA boxes that have Assets running through them
	 * - In the Views section, select LISAs, Gaps and  Assets and generate the report
	 * - Click the thumbnail preview button
	 * - Download report view PDF
	 * - Click on the "Compliance.zip (Shape)" button
	 * - Extract the individual files from the zipped file
	 * - View the Shapefile content in ArcGIS
	 * Results:
	 * 	 - SSRS PDF should have Highlight LISA Assets and Highlight Gap Assets are not checked in Views section
	 * 	 - Report View PDF should have LISA, Gaps and assets are displayed.
	 * 	 - Assets Intersecting LISA and Gaps are not highlighted
	 * 	 - The Shapefile zip should download
	 * 	 - The Breadcrumb, LISAs, Indications and FOV should reflect only the data from the selected survey. There should be no data from surveys that were not included in the report
	 * 	 - There should not be shapefiles for PipeIntersectingLISA and PipeIntersectingGap
	 * 	 - PipeAll, LISA, Gap shape files should be present
	 * 	 - LISA shape file's attribute table should have Label (LISA 1, LISA 2, etc), Lat and Long Co-ordinates
	 */

	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1960, location = ComplianceReportDataProvider.class)
	public void TC1960_VerifyReportViewPDFandshapefilesWhenOnlyLISAGapsAndAssetsSelected(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1960_VerifyReportViewPDFandshapefilesWhenOnlyLISAGapsAndAssetsSelected ...");

		final int DB3_ANALYZER_ROW_ID = 31;	 	/* TestEnvironment datasheet rowID (specifies Analyzer, Replay DB3) */
		final int SURVEY_ROW_ID = 54;	 		/* Survey information  */

		final int SURVEY_RUNTIME_IN_SECONDS = 15; /* Number of seconds to run the survey for. */

		final int newCustomerRowID = 6;
		final int newLocationRowID = 9;
		final int newCustomerUserRowID = 20;
		final int newSurveyorRowID = 5;
		final int newAnalyzerRowID = 4;
		final int newRefGasBottleRowID = 2;

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		// Add a new user customer with Report ShapeFile first disabled and then enable it.
		String allCustomerLicenseRowIDs = ALL_LICENSED_FEATURES_ROWIDS_NO_ANALYTICS;
		manageCustomerPageAction.open(EMPTY, NOTSET);

		manageCustomerPageAction.createOrFetchNewGisCustomer(EMPTY, newCustomerRowID /*customerRowID*/);
		manageCustomerPageAction.editCustomerSelectLicensedFeatures(allCustomerLicenseRowIDs, NOTSET);

		// Create new location.
		manageLocationPageAction.open(EMPTY, NOTSET);
		manageLocationPageAction.createNewLocation(EMPTY, newLocationRowID);

		// Create new user.
		manageUsersPageAction.open(EMPTY, NOTSET);
		manageUsersPageAction.createNewCustomerUser(EMPTY, newCustomerUserRowID /*userRowID*/);

		// Create new surveyor.
		manageSurveyorPageAction.open(EMPTY, NOTSET);
		manageSurveyorPageAction.createNewSurveyor(EMPTY, newSurveyorRowID /*surveyorRowID*/);

		// Create new analyzer.
		manageAnalyzerPageAction.open(EMPTY, NOTSET);
		manageAnalyzerPageAction.createNewAnalyzer(EMPTY, newAnalyzerRowID /*analyzerRowID*/);

		// Create new ref gas bottle.
		manageRefGasBottlesPageAction.open(EMPTY, NOTSET);
		manageRefGasBottlesPageAction.createNewRefGasBottle(EMPTY, newRefGasBottleRowID /*refGasBottleRowID*/);

		// Email ID for the new created user was generated dynamically in this case by using 'GenerateRandomEmail(20)' function.
		// For such cases, use the overload with username and password for generateSurveyForUser().
		String newUsername = ManageUsersPageActions.workingDataRow.get().username;
		String newUserPass = ManageUsersPageActions.workingDataRow.get().password;

		Customer customer = Customer.getCustomer(ManageCustomerPageActions.workingDataRow.get().name);

		Assert.assertTrue(executeAsCustomerWithGISData(complianceReportsPageAction, customer.getId(), reportDataRowID1, pageAction -> {
			try {
				// Create a survey for the new user.
				TestEnvironmentActions.generateSurveyForUser(newUsername, newUserPass,
						DB3_ANALYZER_ROW_ID, SURVEY_ROW_ID, SURVEY_RUNTIME_IN_SECONDS);

				// Re-login as admin user (with username:password format to enforce specific username/password) and create report.
				loginPageAction.open(EMPTY, NOTSET);
				loginPageAction.login(String.format("%s:%s", PICDFADMIN, PICADMINPSWD), NOTSET);

				// Create report for the new customer.
				pageAction.open(EMPTY, getReportRowID(reportDataRowID1));
				createNewReport(pageAction, getReportRowID(reportDataRowID1));
				waitForReportGenerationToComplete(pageAction, getReportRowID(reportDataRowID1));
				pageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
				pageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
				pageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
				assertTrue(pageAction.verifySSRSViewsTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
				pageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
				pageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
				pageAction.extractPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
				assertTrue(pageAction.verifyPDFZipFilesAreCorrect(EMPTY, getReportRowID(reportDataRowID1)));
				assertTrue(pageAction.verifySSRSViewsTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
				assertTrue(pageAction.verifyViewsImagesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
				pageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
				pageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
				pageAction.extractShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
				assertTrue(pageAction.verifyShapeFilesWithBaselines(EMPTY, getReportRowID(reportDataRowID1)));
			} catch (Exception ex) {
				return false;
			}
			return true;
		}));
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
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1320, location = ComplianceReportDataProvider.class)
	public void TC1320_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecast2SurveysDifferentTags(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1320_GenerateComplianceReportCustomerAdminIncludePercentCoverageForecast2SurveysDifferentTags ..." +
			 "\nTest Description: Generate Compliance Report as Customer Admin, include Percent Coverage Forecast and 2 surveys with different tags");

		final int DB3_ANALYZER_ROW_ID = 75;	 	  /* TestEnvironment datasheet rowID (specifies Analyzer, Replay DB3) */
		final int SURVEY_ROW_ID = 5;	 		  /* Survey information  */
		final int SURVEY_RUNTIME_IN_SECONDS = 120; /* Number of seconds to run the survey for. */
		final int newCustomerRowID = 15;
		final int newLocationRowID = 23;
		final int newCustomerUserRowID = 31;
		final int newSurveyorRowID = 29;
		final int newAnalyzerRowID = 27;
		final int newRefGasBottleRowID = 11;

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		CustomerSurveyInfoEntity custSrvInfo = new CustomerSurveyInfoEntity(newCustomerRowID, newLocationRowID, newCustomerUserRowID, newAnalyzerRowID,
				newSurveyorRowID, newRefGasBottleRowID, DB3_ANALYZER_ROW_ID, SURVEY_RUNTIME_IN_SECONDS, SURVEY_ROW_ID);
		custSrvInfo.setUseCustomerWithGISSeed(true);
		custSrvInfo.setRetainGISSeedData(true);

		boolean testFailed = false;
		try {
			new TestDataGenerator().generateNewCustomerAndSurvey(custSrvInfo);

			String newUsername = ManageUsersPageActions.workingDataRow.get().username;
			String newUserPass = ManageUsersPageActions.workingDataRow.get().password;
			TestEnvironmentActions.generateSurveyForUser(newUsername, newUserPass,
					DB3_ANALYZER_ROW_ID, SURVEY_ROW_ID, SURVEY_RUNTIME_IN_SECONDS);

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.getLoginPage().loginNormalAs(ManageUsersPageActions.workingDataRow.get().username, ManageUsersPageActions.workingDataRow.get().password);

			complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
			createNewReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
			waitForReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));

			complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
			assertTrue(complianceReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));

			assertTrue(complianceReportsPageAction.verifySSRSCoverageTableInfo(EMPTY, getReportRowID(reportDataRowID1)));
			assertTrue(complianceReportsPageAction.verifySSRSCoverageForecastTableInfo(EMPTY, getReportRowID(reportDataRowID1)));

		} catch (Exception ex) {
			testFailed = true;
			BaseTest.reportTestFailed(ex, ComplianceReportsPageTest3.class.getName());
		} finally {
			if (!testFailed) {
				cleanupReports(ComplianceReportsPageActions.workingDataRow.get().title, TestContext.INSTANCE.getLoggedInUser());
				if (TestContext.INSTANCE.getTestSetup().isGeoServerEnabled()) {
					// monitor should cleanup customers locked for a longer period of time.
					CustomerWithGisDataPool.releaseCustomer(ManageCustomerPageActions.workingDataRow.get().name);
				}
			}
		}
	}

	private void cleanupReports(String rptTitle, String strCreatedBy) throws Exception {
		// Delete report before deleting GIS data pushed by test to prevent FK constraint violation.
		for (int i = 0; i < 1; i++) {
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(String.format("%s:%s", PICDFADMIN, PICADMINPSWD), NOTSET);

			complianceReportsPageAction.open(EMPTY, NOTSET);
			complianceReportsPageAction.getComplianceReportsPage().searchAndDeleteReport(rptTitle, strCreatedBy);
		}
	}
}