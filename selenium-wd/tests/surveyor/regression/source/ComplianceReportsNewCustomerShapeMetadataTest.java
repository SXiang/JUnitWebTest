package surveyor.regression.source;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import common.source.ExceptionUtility;
import common.source.FunctionUtil;
import common.source.Log;
import common.source.TestContext;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.Test;

import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.BaseTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.ReportsCommonPage.ReportsButtonType;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;
import surveyor.dataaccess.source.Customer;
import surveyor.dataaccess.source.CustomerWithGisDataPool;
import surveyor.dataaccess.source.Report;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.dbseed.source.DbSeedExecutor;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.LoginPageActions;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsNewCustomerShapeMetadataTest extends BaseReportsPageActionTest {

	private static final String EMPTY = "";

	private static LoginPageActions loginPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static MeasurementSessionsPage measurementSessionsPage;

	private static ManageCustomerPageActions manageCustomerPageAction;
	private static Map<String, String> testAccount, testSurvey, testReport;

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
			if (TestContext.INSTANCE.getTestSetup().isGeoServerEnabled()) {
				testAccount = createTestAccountWithGisCustomer("CusWithAsset");
			} else {
				testAccount = createTestAccount("CusWithoutAsset");
			}

			testSurvey = addTestSurvey(testAccount.get("analyzerName"), testAccount.get("analyzerSharedKey")
					,testAccount.get("userName"), testAccount.get("userPassword"), 20 /*surveyRuntimeInSeconds*/, SurveyType.Standard);
		}
	}

	@After
	public void afterTest() throws Exception {
		String customerName = testAccount.get("customerName");
		CustomerWithGisDataPool.releaseCustomer(customerName);
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
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
		manageCustomerPageAction = new ManageCustomerPageActions(getDriver(), getBaseURL(), getTestSetup());
		complianceReportsPageAction = new ComplianceReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
		setReportsPage((ComplianceReportsPage)complianceReportsPageAction.getPageObject());
	}

	/**
	 * Test Case ID: TC786_ShapefileMetaDataReportFeaturePermissionExistingCustomer_CopyComplianceReportGeneration
	 * Test Description: Shapefile and meta data report feature permission to existing customer - Copy Compliance report generation
	 * Script: -
	 *	- Log in as Picarro Admin
	 *	- On Manage Customers page, select a customer that does not have Report Shapefile and Report Meta Data permission enabled and click the "Edit" button
	 *	- Confirm that the "Account Enabled" box is checked and check the Report Shape File and Report Meta data button
	 *	- Click OK
	 *	- Login as Customer User
	 *	- On the Compliance Reports page, click on Copy button of above generated report  (For eg. Report tile: US895  Test Report 1) and click OK
	 *	- Click on Compliance Viewer button
	 *	- Click on the Shape file and meta file export button
	 * Results: -
	 *	- - Compliance Viewer dialog has Shape (ZIP) and Meta data (ZIP) export buttons
	 *	- - User can download the Shape files and meta data files successfully
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC786, location = ComplianceReportDataProvider.class)
	public void TC786_ShapefileMetaDataReportFeaturePermissionExistingCustomer_CopyComplianceReportGeneration(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC786_ShapefileMetaDataReportFeaturePermissionExistingCustomer_CopyComplianceReportGeneration ...");

		String userName = testAccount.get("userName");
		String userPassword = testAccount.get("userPassword");
		String customerName = testAccount.get("customerName");

		Customer customer = Customer.getCustomer(customerName);
		String customerId = customer.getId();
		String rptTitle = "";
		String strCreatedBy = "";

		try {
			// Push GIS seed for newly created customer
			DbSeedExecutor.executeGisSeed(customerId);
			String surveyTag = testSurvey.get(SurveyType.Standard.toString()+"Tag");

			testReport = addTestReport(testAccount.get("userName"), testAccount.get("userPassword"), surveyTag,
					132 /*reportDataRowID*/, SurveyModeFilter.Standard);

			rptTitle = testReport.get(SurveyType.Standard+"Title");
			strCreatedBy = testReport.get("userName");

			// Ensure customer has Report metadata and Report ShapeFile license features.
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(String.format("%s:%s", PICDFADMIN, PICADMINPSWD), NOTSET);
			manageCustomerPageAction.open(EMPTY, NOTSET);
			manageCustomerPageAction.getManageCustomersPage().editAndSelectLicensedFeatures(customerName, getReportMetaReportShapeLicFeatures());
			getHomePage().logout();

			// Login as new customer user.
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(String.format("%s:%s", userName, userPassword), NOTSET);   /* login using newly created user */

			// Copy report as new customer user.
			copyReportAndWaitForReportGenerationToComplete(rptTitle, strCreatedBy);

			// Verify report meta and report shape files are generated successfully.
			clickOnComplianceReportButton(rptTitle, strCreatedBy, ReportsButtonType.ReportViewer);
			verifyShapeAndMetaZipFilesAreGeneratedCorrectly(rptTitle);
		} catch (Exception ex) {
			BaseTest.reportTestFailed(ex, ComplianceReportsNewCustomerShapeMetadataTest.class.getName());
		} finally {
			cleanupReports(rptTitle, strCreatedBy);
			// Remove GIS seed from the customer.
			FunctionUtil.warnOnError(() -> DbSeedExecutor.cleanUpGisSeed(customerId));
		}
	}

	/**
	 * Test Case ID: TC788_RemoveShapeFileMetaDataFeaturePermissionFromExistingCustomer_CopyComplianceReportVerification
	 * Test Description: Remove shape file and meta data feature permission from existing customer - Copy Compliance report verification
	 * Script: -
	 *	- - Log in as Picarro Admin- On Manage Customers page, select a customer that has Shape file and Meta data permission options enabled and click the Edit button (eg. PG&amp;E's)- Confirm that the Account Enabled box is checked and uncheck the Report Shape file and meta data buttons- Click OK - Log in as Customer user- Go to compliance report page- Click on Copy button of above generated report and click OK- Click on Compliance Viewer button
	 * Results: -
	 *	- - Compliance Viewer dialog does not have Shape (ZIP) and Meta data (ZIP) export buttons
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC788, location = ComplianceReportDataProvider.class)
	public void TC788_RemoveShapeFileMetaDataFeaturePermissionFromExistingCustomer_CopyComplianceReportVerification(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC788_RemoveShapeFileMetaDataFeaturePermissionFromExistingCustomer_CopyComplianceReportVerification ...");

		Boolean testFailed = false;
		String userName = testAccount.get("userName");
		String userPassword = testAccount.get("userPassword");
		String customerName = testAccount.get("customerName");

		Customer customer = Customer.getCustomer(customerName);
		String customerId = customer.getId();
		String rptTitle = "";
		String strCreatedBy = "";

		try {

			// Push GIS seed for newly created customer
			DbSeedExecutor.executeGisSeed(customerId);
			String surveyTag = testSurvey.get(SurveyType.Standard.toString()+"Tag");

			testReport = addTestReport(testAccount.get("userName"), testAccount.get("userPassword"), surveyTag,
					132 /*reportDataRowID*/, SurveyModeFilter.Standard);

			rptTitle = testReport.get(SurveyType.Standard+"Title");
			strCreatedBy = testReport.get("userName");

			// Unselect Report metadata and Report ShapeFile license features for the new customer.
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(String.format("%s:%s", PICDFADMIN, PICADMINPSWD), NOTSET);

			manageCustomerPageAction.open(EMPTY, NOTSET);
			manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, getReportMetaReportShapeLicFeatures());
			getHomePage().logout();

			// Login as new customer user.
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(String.format("%s:%s", userName, userPassword), NOTSET);   /* login using newly created user */

			// Copy report as new customer user.
			copyReportAndWaitForReportGenerationToComplete(rptTitle, strCreatedBy);

			// Verify report meta and report shape files are NOT shown in Compliance Viewer.
			clickOnComplianceReportButton(rptTitle, strCreatedBy, ReportsButtonType.ReportViewer);
			assertFalse(complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, NOTSET));
			assertFalse(complianceReportsPageAction.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(EMPTY, NOTSET));

		} catch (Exception ex) {
			testFailed = true;
			BaseTest.reportTestFailed(ex, ComplianceReportsNewCustomerShapeMetadataTest.class.getName());
		} finally {
			if (!testFailed) {
				cleanupReports(rptTitle, strCreatedBy);
				// Remove GIS seed from the customer.
				FunctionUtil.warnOnError(() -> DbSeedExecutor.cleanUpGisSeed(customerId));
			}
		}
	}

	/**
	 * Test Case ID: TC790_ShapefileMetaDataReportFeaturePermissionCustomerGenerateComplianceReportPicarroAdminSpecifiedCustomer
	 * Test Description: Shapefile and meta data report feature permission to customer and generate Compliance report as picarro admin for specified customer
	 * Script: -
	 *	- - Log in as Picarro Admin
	 *	- - On the Compliance Reports page, generate a new report for survey having Indications, LISA, FOV, breadcrumb, isotopic analysis. Add View having Indications, LISA, FOV, breadcrumb, isotopic analysis selected
	 *	- - Click on Compliance Viewer button
	 *	- - Click on Compliance ZIP (Shape) and Compliance ZIP (Meta) download buttons
	 * Results: -
	 *	- - Compliance Viewer dialog hasCompliance ZIP (Shape) and Compliance ZIP (Meta)export buttons
	 *	- - Picarro Admin can download the Shape files and meta data files successfully
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC790, location = ComplianceReportDataProvider.class)
	public void TC790_ShapefileMetaDataReportFeaturePermissionCustomerGenerateComplianceReportPicarroAdminSpecifiedCustomer(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC790_ShapefileMetaDataReportFeaturePermissionCustomerGenerateComplianceReportPicarroAdminSpecifiedCustomer ...");

		Boolean testFailed = false;

		// Unselect Report metadata and Report ShapeFile license features for the new customer.
		String customerName = testAccount.get("customerName");

		Customer customer = Customer.getCustomer(customerName);
		String customerId = customer.getId();
		String rptTitle = "";
		String strCreatedBy = "";

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(String.format("%s:%s", PICDFADMIN, PICADMINPSWD), NOTSET);

		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndSelectLicensedFeatures(customerName, getReportMetaReportShapeLicFeatures());
		getHomePage().logout();

		try {
			// Push GIS seed for newly created customer
			DbSeedExecutor.executeGisSeed(customerId);
			String surveyTag = testSurvey.get(SurveyType.Standard.toString()+"Tag");

			testReport = addTestReport(testAccount.get("userName"), testAccount.get("userPassword"), surveyTag,
					132 /*reportDataRowID*/, SurveyModeFilter.Standard);

			rptTitle = testReport.get(SurveyType.Standard+"Title");
			strCreatedBy = testReport.get("userName");

			// Login as Picarro admin.
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(String.format("%s:%s", PICDFADMIN, PICADMINPSWD), NOTSET);

			// Copy report as Picarro admin.
			copyReportAndWaitForReportGenerationToComplete(rptTitle, strCreatedBy, PICDFADMIN);

			// Verify report meta and report shape files are generated successfully.
			clickOnComplianceReportButton(rptTitle, PICDFADMIN, ReportsButtonType.ReportViewer);
			verifyShapeAndMetaZipFilesAreGeneratedCorrectly(rptTitle);

		} catch (Exception ex) {
			testFailed = true;
			Assert.fail(String.format("Exception: %s", ExceptionUtility.getStackTraceString(ex)));

		} finally {
			if (!testFailed) {
				cleanupReports(rptTitle, strCreatedBy);
				// Remove GIS seed from the customer.
				FunctionUtil.warnOnError(() -> DbSeedExecutor.cleanUpGisSeed(customerId));
			}
		}
	}

	private LicensedFeatures[] getReportMetaReportShapeLicFeatures() {
		LicensedFeatures[] lfs = new LicensedFeatures[2];
		lfs[0] = LicensedFeatures.REPORTMETADATA;
		lfs[1] = LicensedFeatures.REPORTSHAPEFILE;
		return lfs;
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

	private void clickOnComplianceReportButton(String rptTitle, String strCreatedBy, ReportsButtonType reportButtonType) throws Exception {
		complianceReportsPageAction.getComplianceReportsPage().clickOnButtonInReportPage(rptTitle, strCreatedBy, reportButtonType);
		complianceReportsPageAction.getComplianceReportsPage().waitForReportViewerDialogToOpen();
		complianceReportsPageAction.getComplianceReportsPage().waitForReportViewImagetoAppear();
	}

	private void copyReportAndWaitForReportGenerationToComplete(String rptTitle, String strCreatedBy) {
		copyReportAndWaitForReportGenerationToComplete(rptTitle, strCreatedBy, strCreatedBy);
	}

	private void copyReportAndWaitForReportGenerationToComplete(String rptTitle, String strCreatedBy, String strCreatedBy1 ) {
		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.getComplianceReportsPage().clickOnCopyReport(rptTitle, strCreatedBy);
		complianceReportsPageAction.getComplianceReportsPage().waitForCopyReportPagetoLoad();
		complianceReportsPageAction.getComplianceReportsPage().clickOnOKButton();
		complianceReportsPageAction.getComplianceReportsPage().waitForReportGenerationtoComplete(rptTitle, strCreatedBy1);
		// Purge DB cache to look for new copied report.
		new Report().purgeCache();
	}

	private void verifyShapeAndMetaZipFilesAreGeneratedCorrectly(String rptTitle) throws Exception {
		assertTrue(complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, NOTSET));
		assertTrue(complianceReportsPageAction.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(EMPTY, NOTSET));

		complianceReportsPageAction.getComplianceReportsPage().invokeMetaZipFileDownload(rptTitle);
		complianceReportsPageAction.getComplianceReportsPage().invokeShapeZipFileDownload(rptTitle);

		String reportName = "CR-" + complianceReportsPageAction.getComplianceReportsPage().getReportName(rptTitle);
		complianceReportsPageAction.getComplianceReportsPage().waitForMetadataZIPFileDownload(reportName, 0 /*zipIndex*/);
		complianceReportsPageAction.getComplianceReportsPage().waitForShapeZIPFileDownload(reportName, 0 /*zipIndex*/);
	}
}