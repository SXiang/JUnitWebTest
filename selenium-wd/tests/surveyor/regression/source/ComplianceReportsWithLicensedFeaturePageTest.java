package surveyor.regression.source;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Before;

import common.source.Constants;
import common.source.Log;
import common.source.LogHelper;
import common.source.RetryUtil;
import common.source.Screenshotter;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.Test;
import org.junit.rules.TestName;

import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.entities.BaseReportEntity.ReportModeFilter;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;
import surveyor.dataaccess.source.CustomerLicenses;
import surveyor.dataaccess.source.Analyzer.CapabilityType;
import surveyor.dataaccess.source.CustomerLicenses.License;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;


@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsWithLicensedFeaturePageTest extends BaseReportsPageActionTest {

	private static final String EMPTY = "";
	private static ManageCustomerPageActions manageCustomerPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static DriverViewPage driverViewPage;
	private static Map<String, String> testAccount, testSurvey, testSurvey2, testReport;
	private static String userName;
	private static String userPassword;
	private static String customerName;
	private static String analyzerSharedKey;
	private static String analyzerName;
	private static String customerId;
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
			testAccount = createTestAccount("LicFeature");	
			userName = testAccount.get("userName");
			userPassword = testAccount.get("userPassword");
			customerName = testAccount.get("customerName");
			analyzerSharedKey = testAccount.get("analyzerSharedKey");
			analyzerName = testAccount.get("analyzerName");
			customerId = testAccount.get("customerId");
			pushGisData(customerId);	
			testSurvey = addTestSurvey(analyzerName, analyzerSharedKey, userName, userPassword);
			testSurvey2 = addTestSurvey(analyzerName, analyzerSharedKey, CapabilityType.IsotopicMethane,
					"Surveyor_FEDS2067_std.db3", "replay-db3.defn", userName, userPassword, 150, SurveyType.Standard);

		}else{
			getLoginPage().open();
			getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
			manageCustomerPageAction.open(EMPTY, NOTSET);
			manageCustomerPageAction.getManageCustomersPage().editAndSelectLicensedFeatures(customerName, LicensedFeatures.values());
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
		manageCustomerPageAction = new ManageCustomerPageActions(getDriver(), getBaseURL(), getTestSetup());
		complianceReportsPageAction = new ComplianceReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
		setReportsPage((ComplianceReportsPage)complianceReportsPageAction.getPageObject());
	}

	/**
	 * Test Case ID: TC710_ShapefileButtonNotAvailableCustomerUserIfCustomerDoesNotShapefileGenerationOptionEnabled
	 * Test Description: Shapefile button not available for Customer user if Customer does not have Shapefile generation option enabled
	 * Script: -
	 *	- - Log in with a Customer Utility Admin user account
	 *	- - On the Compliance Reports page, click the thumbnail preview button
	 * Results: -
	 *	- - The Shapefile button should not appear
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC710, location = ComplianceReportDataProvider.class)
	public void TC710_ShapefileButtonNotAvailableCustomerUserIfCustomerDoesNotShapefileGenerationOptionEnabled(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC710_ShapefileButtonNotAvailableCustomerUserIfCustomerDoesNotShapefileGenerationOptionEnabled ...");
		
		String surveyTag = testSurvey2.get(SurveyType.Standard.toString()+"Tag");
		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.REPORTSHAPEFILE);
		getHomePage().logout();
		
		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);		
		addTestReport(userName, userPassword, customerName, surveyTag, reportDataRowID1, SurveyModeFilter.Standard);
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assertFalse(complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC721_RemoveShapeFileMetaDataFeaturePermissionFromExistingCustomer_NewComplianceReportVerification
	 * Test Description: Remove shape file and meta data feature permission from existing customer - New Compliance report verification
	 * Script: -
	 *	- - Log in as Picarro Admin
	 *	- - On Manage Customers page, select a customer that has Shape file and Meta data permission options enabled and click the Edit button (eg. PG&amp;E's)
	 *	- - Confirm that the Account Enabled box is checked and uncheck the Report Shape file and meta data buttons
	 *	- - Click OK
	 *	- - Log in as Customer user
	 *	- - Go to compliance report page and generate a new report
	 *	- - Click on Compliance Viewer button
	 * Results: -
	 *	- - Compliance Viewer dialog does not have Shape (ZIP) and Meta data (ZIP) export buttons
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC721, location = ComplianceReportDataProvider.class)
	public void TC721_RemoveShapeFileMetaDataFeaturePermissionFromExistingCustomer_NewComplianceReportVerification(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC721_RemoveShapeFileMetaDataFeaturePermissionFromExistingCustomer_NewComplianceReportVerification ...");

		String surveyTag = testSurvey2.get(SurveyType.Standard.toString()+"Tag");
		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.REPORTSHAPEFILE, LicensedFeatures.REPORTMETADATA);
		getHomePage().logout();
		
		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);		
		addTestReport(userName, userPassword, customerName, surveyTag, reportDataRowID1, SurveyModeFilter.Standard);
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		assertFalse(complianceReportsPageAction.verifyShapeZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));
		assertFalse(complianceReportsPageAction.verifyMetaDataZIPThumbnailIsShownInComplianceViewer(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC1496_AddLISABoxOptonToExistingCustomer
	 * Test Description: Add LISA Box option to existing customer
	 * Script: 
	 *	- A customer that does not have LISA Box 1.0 option enabled
	 *	- Customer has license for GIS Layers, Highlight LISA Assets
	 *	- Log into PCubed as Picarro Admin
	 *	- Navigate to Picarro Administration -> Manage Customers page
	 *	- Select a customer and click the Edit button
	 *	- Check LISA Box 1.0 checkbox, and make sure that the Report Shape File box is checked and click OK
	 *	- Navigate to Reports -> Compliance Reports and click on the New Compliance Report button
	 *	- Fill out the necessary fields and select LISAs in the Views section.
	 *	- Click OK
	 *	- Once the report has completed generation, click on the Compliance Viewer button and then the View thumbnail
	 *	- Click on the Shape File button
	 *	- Run the Shape Files through GIS software like ArcGIS
	 * Results: 
	 *	- In the report view, the LISA box should be rectagular in shape and circumscribe the LISA itself
	 *	- All LISA boxes should have a minimum width of 100 feet, regardless of the shape of the LISA itself
	 *	- There should be a 50 foot buffer between the LISA bubble and the side of the box perpendicular to the line bisecting the LISA
	 *	- The segment of Main contained within the LISA box should be highlighted
	 *	-  Any Service assets that are contained in or touch a LISA box should be highlighted along its entire length 
	 *	- The main to which each of the highlighted Services are attached should also be highlighted, up to 25 feet on either side of the "T" connected to the Service
	 *	- Overlapping LISA boxes should not be combined into a single box. Each LISA should have its own LISA box drawn as a distinct box unto itself
	 *	- The report View should have all LISAs in the shape of boxes, not fans or circles
	 *	- The shapes drawn by the GIS software should match those of the Compliance Report views
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1496, location = ComplianceReportDataProvider.class)
	public void TC1496_AddLISABoxOptonToExistingCustomer(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning  TC1496_AddLISABoxOptonToExistingCustomer...");
		
		String surveyTag1 = testSurvey.get(SurveyType.Standard.toString()+"Tag");
		String surveyTag2 = testSurvey2.get(SurveyType.Standard.toString()+"Tag");

		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
		/* UnSelect LisaBox1.0 */
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.LISABOX10);
		getHomePage().logout();
		
		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);		
		addTestReport(userName, userPassword, customerName, surveyTag1+":"+surveyTag2, reportDataRowID2, SurveyModeFilter.Standard);
		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID2));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID2));		
        complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID2));
        complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID2));
        complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID2));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID2));
		
		assertTrue(complianceReportsPageAction.verifyNumberOfLISAsInShapeFilesEquals("2", getReportRowID(reportDataRowID2)));
        assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines_Static("FALSE", getReportRowID(reportDataRowID2)));
        
		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
		/* Select LisaBox1.0 */
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndSelectLicensedFeatures(customerName, LicensedFeatures.LISABOX10);
		getHomePage().logout();
		
		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);		
		addTestReport(userName, userPassword, customerName, surveyTag1+":"+surveyTag2, reportDataRowID1, SurveyModeFilter.Standard);
		
		complianceReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnComplianceViewerPDF(EMPTY, getReportRowID(reportDataRowID1));		
        complianceReportsPageAction.clickOnComplianceViewerViewByIndex("1", getReportRowID(reportDataRowID1));
        complianceReportsPageAction.waitForViewDownloadToCompleteByViewIndex("1", getReportRowID(reportDataRowID1));
        complianceReportsPageAction.clickOnComplianceViewerShapeZIP(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.waitForShapeZIPDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1));
		
		assertTrue(complianceReportsPageAction.verifyNumberOfLISAsInShapeFilesEquals("2", getReportRowID(reportDataRowID1)));
        assertTrue(complianceReportsPageAction.verifyViewsImagesWithBaselines_Static("FALSE", getReportRowID(reportDataRowID1)));
        
		getHomePage().logout();
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

		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		/* Unselect RR and Manual */
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.RAPIDRESPONSE, LicensedFeatures.MANUAL);

		getHomePage().logout();

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);

		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.clickOnNewReportButton(EMPTY, NOTSET);
		assertTrue(complianceReportsPageAction.verifyStandardReportModeIsShownOnPage(EMPTY, NOTSET));
		assertFalse(complianceReportsPageAction.verifyRapidResponseReportModeIsShownOnPage(EMPTY, NOTSET));
		assertFalse(complianceReportsPageAction.verifyManualReportModeIsShownOnPage(EMPTY, NOTSET));
		assertTrue(complianceReportsPageAction.verifyStandardSurveyModeIsShownOnPage(EMPTY, NOTSET));
		assertTrue(complianceReportsPageAction.verifyOperatorSurveyModeIsShownOnPage(EMPTY, NOTSET));
		complianceReportsPageAction.clickOnCancelButton(EMPTY, NOTSET);
		getHomePage().logout();

		/* Select RR */
		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndSelectLicensedFeatures(customerName, LicensedFeatures.RAPIDRESPONSE);

		getHomePage().logout();

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);

		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.clickOnNewReportButton(EMPTY, NOTSET);
		assertTrue(complianceReportsPageAction.verifyRapidResponseReportModeIsShownOnPage(EMPTY, NOTSET));
		complianceReportsPageAction.getComplianceReportsPage().selectReportMode(ReportModeFilter.RapidResponse);
		assertTrue(complianceReportsPageAction.verifyRapidResponseSurveyModeIsShownOnPage(EMPTY, NOTSET));
		complianceReportsPageAction.clickOnCancelButton(EMPTY, NOTSET);

		getHomePage().logout();

		/* Select Manual */
		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndSelectLicensedFeatures(customerName, LicensedFeatures.MANUAL);

		getHomePage().logout();

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);

		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.clickOnNewReportButton(EMPTY, NOTSET);
		assertTrue(complianceReportsPageAction.verifyManualReportModeIsShownOnPage(EMPTY, NOTSET));
		complianceReportsPageAction.getComplianceReportsPage().selectReportMode(ReportModeFilter.Manual);
		assertTrue(complianceReportsPageAction.verifyManualSurveyModeIsShownOnPage(EMPTY, NOTSET));
		complianceReportsPageAction.clickOnCancelButton(EMPTY, NOTSET);
		getHomePage().logout();
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

		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		/* Unselect RR and Manual */
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.RAPIDRESPONSE, LicensedFeatures.MANUAL);
		getHomePage().logout();

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);

		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.getComplianceReportsPage().clickOnCopyReport(rptTitle, strCreatedBy);
		complianceReportsPageAction.getComplianceReportsPage().waitForCopyReportPagetoLoad();
		assertTrue(complianceReportsPageAction.verifyStandardReportModeIsShownOnPage(EMPTY, NOTSET));
		assertFalse(complianceReportsPageAction.verifyRapidResponseReportModeIsShownOnPage(EMPTY, NOTSET));
		assertFalse(complianceReportsPageAction.verifyManualReportModeIsShownOnPage(EMPTY, NOTSET));
		assertTrue(complianceReportsPageAction.verifyStandardSurveyModeIsShownOnPage(EMPTY, NOTSET));
		assertTrue(complianceReportsPageAction.verifyOperatorSurveyModeIsShownOnPage(EMPTY, NOTSET));
		complianceReportsPageAction.clickOnCancelButton(EMPTY, NOTSET);
		getHomePage().logout();

		/* Select RR */
		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndSelectLicensedFeatures(customerName, LicensedFeatures.RAPIDRESPONSE);

		getHomePage().logout();

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);

		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.getComplianceReportsPage().clickOnCopyReport(rptTitle, strCreatedBy);
		complianceReportsPageAction.getComplianceReportsPage().waitForCopyReportPagetoLoad();
		assertTrue(complianceReportsPageAction.verifyOperatorSurveyModeIsShownOnPage(EMPTY, NOTSET));
		complianceReportsPageAction.getComplianceReportsPage().selectReportMode(ReportModeFilter.RapidResponse);
		assertTrue(complianceReportsPageAction.verifyRapidResponseSurveyModeIsShownOnPage(EMPTY, NOTSET));
		complianceReportsPageAction.clickOnCancelButton(EMPTY, NOTSET);

		getHomePage().logout();

		/* Select Manual */
		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndSelectLicensedFeatures(customerName, LicensedFeatures.MANUAL);

		getHomePage().logout();

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);

		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.getComplianceReportsPage().clickOnCopyReport(rptTitle, strCreatedBy);
		complianceReportsPageAction.getComplianceReportsPage().waitForCopyReportPagetoLoad();
		complianceReportsPageAction.getComplianceReportsPage().selectReportMode(ReportModeFilter.Manual);
		assertTrue(complianceReportsPageAction.verifyManualSurveyModeIsShownOnPage(EMPTY, NOTSET));
		complianceReportsPageAction.clickOnCancelButton(EMPTY, NOTSET);
		getHomePage().logout();
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
	 * - Customer user will get an error message �The original report was created with the following survey mode licenses: {0}. Your account currently does not have access to these modes." {0} will contain a list of modes that were available at the time the report was generated
	 */
	@Test
	public void TC2112_CustomerCanNotCopyRROperatorManualReportWithoutSurveyModeLicense() throws Exception {
		Log.info("\nRunning TC2112_CustomerCanNotCopyRROperatorManualReportWithoutSurveyModeLicense");

		LicensedFeatures[] lfs = {LicensedFeatures.OPERATOR, LicensedFeatures.RAPIDRESPONSE, LicensedFeatures.MANUAL};
		SurveyModeFilter[] surveyModeFilter = {SurveyModeFilter.Operator, SurveyModeFilter.RapidResponse, SurveyModeFilter.Manual};
		String errorPattern = ComplianceReportsPage.ComplianceReport_LicenseMissing;
		testReport = addTestReport(userName, userPassword);
		String strCreatedBy = userName;

		for(int i=0; i<lfs.length; i++){
			getLoginPage().open();
			getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

			// unselecting licenses failing intermittently in CI runs. adding retries.
			final Integer idx = i;
			boolean actionSuccess = RetryUtil.retryOnException(
					() -> {
						manageCustomerPageAction.open(EMPTY, NOTSET);
						LicensedFeatures license = lfs[idx];
						Log.info(String.format("Unselecting license - '%s'", license.toString()));
						manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, license);
						List<License> customerLicenses = new CustomerLicenses().getLicenses(customerName);
						Log.info(String.format("Licenses assigned to customer-[%s] -> %s", customerName, LogHelper.collectionToString(customerLicenses, "Customer Licenses")));
						return !customerLicenses.contains(license.toString());
					},
					() -> { return true; },
					Constants.THOUSAND_MSEC_WAIT_BETWEEN_RETRIES,
					Constants.DEFAULT_MAX_RETRIES, true /*takeScreenshotOnFailure*/);

			if (!actionSuccess) {
				LicensedFeatures license = lfs[i];
				Log.info(String.format("License-'%s' was NOT removed correctly.", license.toString()));
			}

			getHomePage().logout();
			getLoginPage().open();
			getLoginPage().loginNormalAs(userName, userPassword);

			String rptTitle = testReport.get(surveyModeFilter[i].toString()+"Title");
			String errorMsg = errorPattern.replace("{0}", surveyModeFilter[i].toString());
			complianceReportsPageAction.open(EMPTY, NOTSET);

			List<License> customerLicenses = new CustomerLicenses().getLicenses(customerName);
			Log.info(String.format("Licenses assigned to customer-[%s] -> %s", customerName, LogHelper.collectionToString(customerLicenses, "Customer Licenses")));

			Screenshotter.captureWebDriverScreenshot(getDriver(), "TC2112_BeforeCopyReportClick");
			complianceReportsPageAction.getComplianceReportsPage().clickOnCopyReport(rptTitle, strCreatedBy);
			Screenshotter.captureWebDriverScreenshot(getDriver(), "TC2112_AfterCopyReportClick");

			List<String> licenseMissingText = getHomePage().getLicenseMissingText();
			Log.info(String.format("[ACTUAL] License missing text found on page -> %s", LogHelper.listToString(licenseMissingText)));
			Log.info(String.format("[EXPECTED] Error message -> %s", errorMsg));
			assertTrue(licenseMissingText.contains(errorMsg));
			getHomePage().logout();
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

		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.CURTAINVIEW);
		getHomePage().logout();

		/* Without License */
		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);
		getHomePage().clickOnFirstMatchingDrivingSurvey(surveyTag);
		assertFalse(driverViewPage.isCurtainButtonPresent());
	}

}
