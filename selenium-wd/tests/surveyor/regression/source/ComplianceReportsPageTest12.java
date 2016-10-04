package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.Before;

import common.source.Log;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.Test;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.actions.SurveyViewPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest.ReportTestRunMode;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.dataprovider.ComplianceReportDataProvider;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest12 extends BaseReportsPageActionTest {


	private static final String EMPTY = "";
	private static HomePageActions homePageAction;
	private static LoginPageActions loginPageAction;
	private static ManageCustomerPageActions manageCustomerPageAction;
	private static ManageUsersPageActions manageUsersPageAction;
	private static ManageLocationPageActions manageLocationPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static TestEnvironmentActions testEnvironmentAction;
	private static SurveyViewPageActions surveyViewPageAction;

	@BeforeClass
	public static void beforeTestClass() throws Exception {
		initializePageActions();

		// Select run mode here.
		setPropertiesForTestRunMode();
	}

	@Before
	public void beforeTest() throws Exception{
		setPropertiesForTestRunMode();
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
		homePageAction = new HomePageActions(driver, baseURL, testSetup);
		manageCustomerPageAction = new ManageCustomerPageActions(driver, baseURL, testSetup);
		manageUsersPageAction = new ManageUsersPageActions(driver, baseURL, testSetup);
		manageLocationPageAction = new ManageLocationPageActions(driver, baseURL, testSetup);
		surveyViewPageAction = new SurveyViewPageActions(driver, baseURL, testSetup);
		loginPageAction = new LoginPageActions(driver, baseURL, testSetup);
		complianceReportsPageAction = new ComplianceReportsPageActions(driver, baseURL, testSetup);
		setReportsPage((ComplianceReportsPage)complianceReportsPageAction.getPageObject());
		testEnvironmentAction = new TestEnvironmentActions();
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
	@Ignore /*TBD*/
//	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2100, location = ComplianceReportDataProvider.class)
	public void TC2100_CustomerCanSelectOperatorRROrManulReportModesWithLicense(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2100_CustomerCanSelectOperatorRROrManulReportModesWithLicense");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		manageCustomerPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.createNewCustomer(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.editCustomerUnSelectLicensedFeatures(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.clickOnResubmitButton(EMPTY, getReportRowID(reportDataRowID1));
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getReportRowID(reportDataRowID1));
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
	@Ignore /*TBD*/
//	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2100, location = ComplianceReportDataProvider.class)
	public void TC2102_CustomerCanSelectOperatorRROrManulReportModesWithLicense_CopyCompliance(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2102_CustomerCanSelectOperatorRROrManulReportModesWithLicense_CopyCompliance");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		manageCustomerPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.createNewCustomer(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.editCustomerUnSelectLicensedFeatures(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.clickOnResubmitButton(EMPTY, getReportRowID(reportDataRowID1));
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getReportRowID(reportDataRowID1));
	}

	/* * Test Case ID: TC2109_CustomerHasMinAmpValuesForOperatorRRManualSurveyModesWithLicense
	 * Script:
	 * 	 * - Log into UI as Picarro Admin
	 * - Navigate to Manage Locations page
	 * - Select a location for target customer and click Edit
	 * - Navigate to Manage Customers page, select target customer and click Edit
	 * - Check the Operator checkbox
	 * - Log out and log back in and navigate to same Locations page
	 * - Repeat with Rapid Response
	 * Results:
	 * 	 * - List of locations for all customer is displayed
	 * - Survey Mode: Standard is present, but Survey Mode:Operator and Survey Mode:Rapid Response are not
	 * - After logging back in, Survey Mode: Operator will be present
	 * - After logging back in again, Survey Mode: Rapid Response will be present
	 */
	@Ignore /*TBD*/
//	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2100, location = ComplianceReportDataProvider.class)
	public void TC2109_CustomerHasMinAmpValuesForOperatorRRManualSurveyModesWithLicense(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2109_CustomerHasMinAmpValuesForOperatorRRManualSurveyModesWithLicense");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		manageCustomerPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.createNewCustomer(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.editCustomerUnSelectLicensedFeatures(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.clickOnResubmitButton(EMPTY, getReportRowID(reportDataRowID1));
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getReportRowID(reportDataRowID1));
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
	@Ignore /*TBD*/
//	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC2100, location = ComplianceReportDataProvider.class)
	public void TC2112_CustomerCanNotCopyRROperatorManualReportWithoutSurveyModeLicense(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC2112_CustomerCanNotCopyRROperatorManualReportWithoutSurveyModeLicense");

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));

		manageCustomerPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.createNewCustomer(EMPTY, getReportRowID(reportDataRowID1));
		manageCustomerPageAction.editCustomerUnSelectLicensedFeatures(EMPTY, getReportRowID(reportDataRowID1));

		complianceReportsPageAction.clickOnResubmitButton(EMPTY, getReportRowID(reportDataRowID1));
		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getReportRowID(reportDataRowID1));
	}
}
