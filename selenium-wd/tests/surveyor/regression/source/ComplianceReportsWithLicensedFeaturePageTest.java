package surveyor.regression.source;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;

import common.source.Log;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import org.junit.Test;

import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;


@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsWithLicensedFeaturePageTest extends BaseReportsPageActionTest {

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
		driverViewPage = new DriverViewPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(), driverViewPage);
		manageCustomerPageAction = new ManageCustomerPageActions(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(), manageCustomerPageAction);
	}

	@Before
	public void beforeTest() throws Exception{
		setPropertiesForTestRunMode();
		if(testAccount == null){
			testAccount = createTestAccount("LicFeature");
			testSurvey = addTestSurvey(testAccount.get("analyzerName"), testAccount.get("analyzerSharedKey")
					,testAccount.get("userName"), testAccount.get("userPassword"));
		}else{
			getLoginPage().open();
			getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
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
		manageCustomerPageAction = new ManageCustomerPageActions(getDriver(), getBaseURL(), getTestSetup());
		complianceReportsPageAction = new ComplianceReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
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

		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		/* Unselect RR and Manual */
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.RAPIDRESPONSE, LicensedFeatures.MANUAL);

		getHomePage().logout();

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);

		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.clickOnNewComplianceReport(EMPTY, NOTSET);
		complianceReportsPageAction.verifyNewPageLoaded(EMPTY, NOTSET);
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
		complianceReportsPageAction.clickOnNewComplianceReport(EMPTY, NOTSET);
		complianceReportsPageAction.verifyNewPageLoaded(EMPTY, NOTSET);
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
		complianceReportsPageAction.clickOnNewComplianceReport(EMPTY, NOTSET);
		complianceReportsPageAction.verifyNewPageLoaded(EMPTY, NOTSET);
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

		String userName = testAccount.get("userName");
		String userPassword = testAccount.get("userPassword");
		String customerName = testAccount.get("customerName");

		LicensedFeatures[] lfs = {LicensedFeatures.OPERATOR, LicensedFeatures.RAPIDRESPONSE, LicensedFeatures.MANUAL};
		SurveyModeFilter[] surveyModeFilter = {SurveyModeFilter.Operator, SurveyModeFilter.RapidResponse, SurveyModeFilter.Manual};
		String errorPattern = ComplianceReportsPage.ComplianceReport_LicenseMissing;
		testReport = addTestReport(testAccount.get("userName"), testAccount.get("userPassword"));
		String strCreatedBy = testReport.get("userName");

		for(int i=0; i<lfs.length; i++){
			getLoginPage().open();
			getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

			manageCustomerPageAction.open(EMPTY, NOTSET);
			manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, lfs[i]);
			getHomePage().logout();

			getLoginPage().open();
			getLoginPage().loginNormalAs(userName, userPassword);

			String rptTitle = testReport.get(surveyModeFilter[i].toString()+"Title");
			String errorMsg = errorPattern.replace("{0}", surveyModeFilter[i].toString());
			complianceReportsPageAction.open(EMPTY, NOTSET);
			complianceReportsPageAction.getComplianceReportsPage().clickOnCopyReport(rptTitle, strCreatedBy);

			assertTrue(getHomePage().getLicenseMissingText().contains(errorMsg));
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
