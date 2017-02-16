package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.ALL_LICENSED_FEATURES_ROWIDS_NOLISABOX;
import static surveyor.scommon.source.SurveyorConstants.ALL_LICENSED_FEATURES_ROWIDS;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG2;
import java.util.Map;
import common.source.ExceptionUtility;
import common.source.Log;

import common.source.WebElementExtender;
import org.junit.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import surveyor.dataaccess.source.Customer;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.dbseed.source.DbSeedExecutor;
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

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsWithNewSurveyPageTest extends BaseReportsPageActionTest {

	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;

	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static MeasurementSessionsPage measurementSessionsPage;

	private static ManageCustomerPageActions manageCustomerPageAction;
	private static Map<String, String> testAccount, testSurvey, testReport;

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

		if(testAccount == null){
			testAccount = createTestAccount("CusWithoutAsset");
			testSurvey = addTestSurvey(testAccount.get("analyzerName"), testAccount.get("analyzerSharedKey")
					,testAccount.get("userName"), testAccount.get("userPassword"), SurveyType.Standard);
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

		manageCustomerPageAction = ActionBuilder.createManageCustomerPageAction();
		manageUsersPageAction = ActionBuilder.createManageUsersPageAction();
		manageLocationPageAction = ActionBuilder.createManageLocationPageAction();

		manageAnalyzerPageAction = ActionBuilder.createManageAnalyzerPageAction();
		manageSurveyorPageAction = ActionBuilder.createManageSurveyorPageAction();
		manageRefGasBottlesPageAction = ActionBuilder.createManageRefGasBottlePageAction();
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
		manageCustomerPageAction.createNewCustomer(EMPTY, newCustomerRowID /*customerRowID*/);
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
		String allCustomerLicenseRowIDs = ALL_LICENSED_FEATURES_ROWIDS;
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.createNewCustomer(EMPTY, newCustomerRowID /*customerRowID*/);
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
}
