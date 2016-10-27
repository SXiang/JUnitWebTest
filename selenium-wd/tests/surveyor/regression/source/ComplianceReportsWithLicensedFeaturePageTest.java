package surveyor.regression.source;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;

import common.source.ExceptionUtility;
import common.source.Log;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import org.junit.Test;

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
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import static surveyor.scommon.source.SurveyorConstants.ALL_LICENSED_FEATURES_ROWIDS_NOLISABOX;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;


@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsWithLicensedFeaturePageTest extends BaseReportsPageActionTest {

	private static LoginPageActions loginPageAction;
	private static ManageUsersPageActions manageUsersPageAction;
	private static ManageLocationPageActions manageLocationPageAction;

	private static ManageAnalyzerPageActions manageAnalyzerPageAction;
	private static ManageSurveyorPageActions manageSurveyorPageAction;
	private static ManageRefGasBottlesPageActions manageRefGasBottlesPageAction;

	private static final String EMPTY = "";
	private static ManageCustomerPageActions manageCustomerPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static DriverViewPage driverViewPage;
	private static Map<String, String> testAccount, testSurvey, testReport;
	@BeforeClass
	public static void beforeTestClass() throws Exception {
		initializePageActions();

		// Select run mode here.
		setPropertiesForTestRunMode();
		driverViewPage = new DriverViewPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver, driverViewPage);
		manageCustomerPageAction = new ManageCustomerPageActions(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageCustomerPageAction);
	}

	@Before
	public void beforeTest() throws Exception{
		setPropertiesForTestRunMode();
		if(testAccount == null){
			testAccount = createTestAccount("LicFeature");
			testSurvey = addTestSurvey(testAccount.get("analyzerName"), testAccount.get("analyzerSharedKey")
					,testAccount.get("userName"), testAccount.get("userPassword"));		
		}else{
			loginPage.open();
			loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);
			manageCustomerPageAction.open(EMPTY, NOTSET);
			manageCustomerPageAction.getManageCustomersPage().editAndSelectLicensedFeatures(testAccount.get("customerName"), LicensedFeatures.values());
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
		manageCustomerPageAction = new ManageCustomerPageActions(driver, baseURL, testSetup);
		complianceReportsPageAction = new ComplianceReportsPageActions(driver, baseURL, testSetup);
		setReportsPage((ComplianceReportsPage)complianceReportsPageAction.getPageObject());
	}

	/* * Test Case ID: TC2100_CustomerCanSelectOperatorRROrManulReportModesWithLicense
	 * Script:
	 * 	 * - Log into UI as Picarro admin and navigate to Manage Customers page
	 * - Check list of enabled features
	 * - Log out and log back in as customer user and navigate to Compliance Reports page and click on New Compliance Report button
	 * - Log back in as Picarro admin and navigate back to Manage Customers page and check Rapid Response checkbox
	 * - Log back in as customer user and navigate back to New Compliance Reports page
	 * - Log back in as Picarro admin and navigate back to Manage Customers page and check Manual checkbox
	 * - Log back in as customer user and navigate back to New Compliance Reports page
	 * Results:
	 * 	 * - List of customers is displayed
	 * - Operator checkbox is checked and Rapid Response and Manual are unchecked
	 * - In top section, only Standard Report mode is present, in Survey Selector section, only Standard and Operator are present
	 * - Rapid Response should be present in both sections
	 * - Manual should be present in both sections
	 */
	@Test
	public void TC2100_CustomerCanSelectOperatorRROrManulReportModesWithLicense() throws Exception {
		Log.info("\nRunning TC2100_CustomerCanSelectOperatorRROrManulReportModesWithLicense");

		String userName = testAccount.get("userName");
		String userPassword = testAccount.get("userPassword");
		String customerName = testAccount.get("customerName");


		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		/* Unselect RR and Manual */
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.RAPIDRESPONSE, LicensedFeatures.MANUAL);
		homePage.logout();

		loginPage.open();
		loginPage.loginNormalAs(userName, userPassword);

		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.clickOnNewComplianceReport(EMPTY, NOTSET);
		complianceReportsPageAction.verifyNewPageLoaded(EMPTY, NOTSET);
		assertTrue(complianceReportsPageAction.verifyStandardReportModeIsShownOnPage(EMPTY, NOTSET));
		assertFalse(complianceReportsPageAction.verifyRapidResponseReportModeIsShownOnPage(EMPTY, NOTSET));
		assertFalse(complianceReportsPageAction.verifyManualReportModeIsShownOnPage(EMPTY, NOTSET));
		assertTrue(complianceReportsPageAction.verifyStandardSurveyModeIsShownOnPage(EMPTY, NOTSET));
		assertTrue(complianceReportsPageAction.verifyOperatorSurveyModeIsShownOnPage(EMPTY, NOTSET));
		complianceReportsPageAction.clickOnCancelButton(EMPTY, NOTSET);
		homePage.logout();

		/* Select RR */
		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndSelectLicensedFeatures(customerName, LicensedFeatures.RAPIDRESPONSE);
		homePage.logout();

		loginPage.open();
		loginPage.loginNormalAs(userName, userPassword);

		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.clickOnNewComplianceReport(EMPTY, NOTSET);
		complianceReportsPageAction.verifyNewPageLoaded(EMPTY, NOTSET);
		assertTrue(complianceReportsPageAction.verifyRapidResponseReportModeIsShownOnPage(EMPTY, NOTSET));
		complianceReportsPageAction.getComplianceReportsPage().selectReportMode(ReportModeFilter.RapidResponse);
		assertTrue(complianceReportsPageAction.verifyRapidResponseSurveyModeIsShownOnPage(EMPTY, NOTSET));
		complianceReportsPageAction.clickOnCancelButton(EMPTY, NOTSET);
		homePage.logout();

		/* Select Manual */
		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndSelectLicensedFeatures(customerName, LicensedFeatures.MANUAL);
		homePage.logout();

		loginPage.open();
		loginPage.loginNormalAs(userName, userPassword);

		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.clickOnNewComplianceReport(EMPTY, NOTSET);
		complianceReportsPageAction.verifyNewPageLoaded(EMPTY, NOTSET);
		assertTrue(complianceReportsPageAction.verifyManualReportModeIsShownOnPage(EMPTY, NOTSET));
		complianceReportsPageAction.getComplianceReportsPage().selectReportMode(ReportModeFilter.Manual);
		assertTrue(complianceReportsPageAction.verifyManualSurveyModeIsShownOnPage(EMPTY, NOTSET));
		complianceReportsPageAction.clickOnCancelButton(EMPTY, NOTSET);
		homePage.logout();
	}

	/* * Test Case ID: TC2102_CustomerCanSelectOperatorRROrManulReportModesWithLicense_CopyCompliance
	 * Script:
	 * 	 * - 	 * - Log into UI as Picarro admin and navigate to Manage Customers page
	 * - Check list of enabled features
	 * - Log out and log back in as customer user and navigate to Compliance Reports page, select an existing report and click on Copy button
	 * - Log back in as Picarro admin and navigate back to Manage Customers page and check Rapid Response checkbox
	 * - Log back in as customer user and navigate back to Copy Compliance Report page
	 * - Log back in as Picarro admin and navigate back to Manage Customers page and check Manual checkbox
	 * - Log back in as customer user and navigate back to Copy Compliance Report page
	 * Results:
	 * 	 * - List of customers is displayed
	 * - Operator checkbox is checked and Rapid Response and Manual are unchecked
	 * - In top section, only Standard Report mode is present, in Survey Selector section, only Standard and Operator are present
	 * - Rapid Response should be present in both sections
	 * - Manual should be present in both sections
	 */
	@Test
	public void TC2102_CustomerCanSelectOperatorRROrManulReportModesWithLicense_CopyCompliance() throws Exception {
		Log.info("\nRunning TC2102_CustomerCanSelectOperatorRROrManulReportModesWithLicense_CopyCompliance");

		String userName = testAccount.get("userName");
		String userPassword = testAccount.get("userPassword");
		String customerName = testAccount.get("customerName");

		testReport = addTestReport(testAccount.get("userName"), testAccount.get("userPassword"));
		String rptTitle = testReport.get(SurveyType.Standard+"Title");
		String strCreatedBy = testReport.get("userName");

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		/* Unselect RR and Manual */
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.RAPIDRESPONSE, LicensedFeatures.MANUAL);
		homePage.logout();

		loginPage.open();
		loginPage.loginNormalAs(userName, userPassword);

		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.getComplianceReportsPage().clickOnCopyReport(rptTitle, strCreatedBy);
		complianceReportsPageAction.getComplianceReportsPage().waitForCopyReportPagetoLoad();
		assertTrue(complianceReportsPageAction.verifyStandardReportModeIsShownOnPage(EMPTY, NOTSET));
		assertFalse(complianceReportsPageAction.verifyRapidResponseReportModeIsShownOnPage(EMPTY, NOTSET));
		assertFalse(complianceReportsPageAction.verifyManualReportModeIsShownOnPage(EMPTY, NOTSET));
		assertTrue(complianceReportsPageAction.verifyStandardSurveyModeIsShownOnPage(EMPTY, NOTSET));
		assertTrue(complianceReportsPageAction.verifyOperatorSurveyModeIsShownOnPage(EMPTY, NOTSET));
		complianceReportsPageAction.clickOnCancelButton(EMPTY, NOTSET);
		homePage.logout();

		/* Select RR */
		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndSelectLicensedFeatures(customerName, LicensedFeatures.RAPIDRESPONSE);
		homePage.logout();

		loginPage.open();
		loginPage.loginNormalAs(userName, userPassword);

		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.getComplianceReportsPage().clickOnCopyReport(rptTitle, strCreatedBy);
		complianceReportsPageAction.getComplianceReportsPage().waitForCopyReportPagetoLoad();
		assertTrue(complianceReportsPageAction.verifyOperatorSurveyModeIsShownOnPage(EMPTY, NOTSET));
		complianceReportsPageAction.getComplianceReportsPage().selectReportMode(ReportModeFilter.RapidResponse);
		assertTrue(complianceReportsPageAction.verifyRapidResponseSurveyModeIsShownOnPage(EMPTY, NOTSET));
		complianceReportsPageAction.clickOnCancelButton(EMPTY, NOTSET);
		homePage.logout();

		/* Select Manual */
		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndSelectLicensedFeatures(customerName, LicensedFeatures.MANUAL);
		homePage.logout();

		loginPage.open();
		loginPage.loginNormalAs(userName, userPassword);

		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.getComplianceReportsPage().clickOnCopyReport(rptTitle, strCreatedBy);
		complianceReportsPageAction.getComplianceReportsPage().waitForCopyReportPagetoLoad();
		complianceReportsPageAction.getComplianceReportsPage().selectReportMode(ReportModeFilter.Manual);
		assertTrue(complianceReportsPageAction.verifyManualSurveyModeIsShownOnPage(EMPTY, NOTSET));
		complianceReportsPageAction.clickOnCancelButton(EMPTY, NOTSET);
		homePage.logout();
	}

	/* * Test Case ID: TC2112_CustomerCanNotCopyRROperatorManualReportWithoutSurveyModeLicense
	 * Description: US3344: [Continued] F133: Make existing 'Surveyor modes' feature licensable
	 * Script:
	 * 	 * - Log in as Picarro Admin and navigate to Manage Customers page
	 * - Select target customer and click Edit button
	 * - Uncheck the Operator checkbox and click OK
	 * - Log out and log back in as customer user, select a report that was generated using Operator surveys and click the Copy button
	 * - Repeat above steps with Rapid Response and Manual modes
	 * Results:
	 * 	 * - User will see a list of customers
	 * - User will see customer details
	 * - Customer user will get an error message “The original report was created with the following survey mode licenses: {0}. Your account currently does not have access to these modes." {0} will contain a list of modes that were available at the time the report was generated
	 */
	@Test
	public void TC2112_CustomerCanNotCopyRROperatorManualReportWithoutSurveyModeLicense() throws Exception {
		Log.info("\nRunning TC2112_CustomerCanNotCopyRROperatorManualReportWithoutSurveyModeLicense");

		String userName = testAccount.get("userName");
		String userPassword = testAccount.get("userPassword");
		String customerName = testAccount.get("customerName");

		LicensedFeatures[] lfs = {LicensedFeatures.OPERATOR, LicensedFeatures.RAPIDRESPONSE, LicensedFeatures.MANUAL};
		SurveyModeFilter[] surveyModeFilter = {SurveyModeFilter.Operator, SurveyModeFilter.RapidResponse, SurveyModeFilter.Manual};
		String errorPattern = ComplianceReportsPage.ComplianceReport_LicenseMissing;
		testReport = addTestReport(testAccount.get("userName"), testAccount.get("userPassword"));
		String strCreatedBy = testReport.get("userName");

		for(int i=0; i<lfs.length; i++){
			loginPage.open();
			loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

			manageCustomerPageAction.open(EMPTY, NOTSET);
			manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, lfs[i]);
			homePage.logout();

			loginPage.open();
			loginPage.loginNormalAs(userName, userPassword);

			String rptTitle = testReport.get(surveyModeFilter[i].toString()+"Title");
			String errorMsg = errorPattern.replace("{0}", surveyModeFilter[i].toString()); 
			complianceReportsPageAction.open(EMPTY, NOTSET);
			complianceReportsPageAction.getComplianceReportsPage().clickOnCopyReport(rptTitle, strCreatedBy);
			assertEquals(errorMsg, homePage.getSiteErrorMsg());
			homePage.logout();
		}
	}

	/* * Test Case ID: TC2134_CurtainViewNotAvailableWithoutLicense
	 * Script:
	 * - Log into Driver View
	 * - Click on Driving Surveys link at left
	 * - Select a survey
	 * - Check bottom panel for Curtain View button
	 * Results:
	 * - User is navigated to selected survey
	 * - Curtain View button should not be present
	 */
	@Test
	public void TC2134_CurtainViewNotAvailableWithoutLicense() throws Exception {
		Log.info("\nRunning TC2134_CurtainViewNotAvailableWithoutLicense");

		String userName = testAccount.get("userName");
		String userPassword = testAccount.get("userPassword");
		String customerName = testAccount.get("customerName");
		String surveyTag = testSurvey.get(SurveyType.Standard+"Tag");

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.CURTAINVIEW);
		homePage.logout();

		/* Without License */
		loginPage.open();
		loginPage.loginNormalAs(userName, userPassword);
		homePage.clickOnFirstMatchingDrivingSurvey(surveyTag);
		assertFalse(driverViewPage.isCurtainButtonPresent());
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
		String newUsername = ManageUsersPageActions.workingDataRow.username;
		String newUserPass = ManageUsersPageActions.workingDataRow.password;

		Customer customer = Customer.getCustomer(ManageCustomerPageActions.workingDataRow.name);
		String customerId = customer.getId();

		try {
			// Add GIS seed for customer to enable GIS elements in New Compliance reports -> views.
			DbSeedExecutor.executeGisSeed(customerId);

			// Create a survey for the new user.
			TestEnvironmentActions.generateSurveyForUser(newUsername, newUserPass,
					DB3_ANALYZER_ROW_ID, SURVEY_ROW_ID, SURVEY_RUNTIME_IN_SECONDS);

			// Re-login as admin user (with username:password format to enforce specific username/password) and create report.
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(String.format("%s:%s", PICDFADMIN, PICADMINPSWD), NOTSET);

			// Create report for the new customer.
			complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
			createNewComplianceReport(complianceReportsPageAction, getReportRowID(reportDataRowID1));
			waitForComplianceReportGenerationToComplete(complianceReportsPageAction, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.clickOnComplianceViewerPDFZIP(EMPTY, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.clickOnComplianceViewerMetaZIP(EMPTY, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.waitForPDFZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.waitForMetaZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
			complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));

		} catch (Exception ex) {
			Assert.fail(String.format("Exception: %s", ExceptionUtility.getStackTraceString(ex)));

		} finally {
			// Remove GIS seed from the customer.
			DbSeedExecutor.cleanUpGisSeed(customerId);
		}
	}
	

	/*@Test
	public void TC1960_VerifyReportViewPDFandshapefilesWhenOnlyLISAGapsAndAssetsSelected() throws Exception {
		Log.info("\nRunning TC1960_VerifyReportViewPDFandshapefilesWhenOnlyLISAGapsAndAssetsSelected");

		String userName = testAccount.get("userName");
		String userPassword = testAccount.get("userPassword");
		String customerName = testAccount.get("customerName");

		testReport = addTestReport(testAccount.get("userName"), testAccount.get("userPassword"), SurveyModeFilter.Standard);
		String rptTitle = testReport.get(SurveyType.Standard+"Title");
		String strCreatedBy = testReport.get("userName");
		
		
	}*/
}
