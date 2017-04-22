package surveyor.regression.source;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;

import common.source.Log;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.junit.Test;

import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageSurveyorPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.entities.CustomerSurveyInfoEntity;
import surveyor.scommon.entities.BaseReportEntity.ReportModeFilter;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.scommon.generators.TestDataGenerator;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;


@RunWith(SurveyorTestRunner.class)
public class AnalyticsLicenseFeature_ComplianceReportTests extends BaseReportsPageActionTest {

	private static final String EMPTY = "";
	private static LoginPage loginPage;
	private static ManageCustomerPageActions manageCustomerPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;
	private static DriverViewPage driverViewPage;
	private static ComplianceReportsPage complianceReportsPage;
	
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

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		final int DB3_ANALYZER_ROW_ID = 61;	 	/* TestEnvironment datasheet rowID (specifies Analyzer, Replay DB3) */
		final int SURVEY_ROW_ID = 64;	 		/* Survey information  */
		final int SURVEY_RUNTIME_IN_SECONDS = 60; /* Number of seconds to run the survey for. */
		final int newCustomerRowID = 14;
		final int newLocationRowID = 17;
		final int newCustomerUserRowID = 26;
		final int newSurveyorRowID = 25;
		final int newAnalyzerRowID = 23;
		final int newRefGasBottleRowID = 7;

		CustomerSurveyInfoEntity custSrvInfo = new CustomerSurveyInfoEntity(newCustomerRowID, newLocationRowID, newCustomerUserRowID, newAnalyzerRowID,
				newSurveyorRowID, newRefGasBottleRowID, DB3_ANALYZER_ROW_ID, SURVEY_RUNTIME_IN_SECONDS, SURVEY_ROW_ID);
		new TestDataGenerator().generateNewCustomerAndSurvey(custSrvInfo, (driverPageAction) -> {
			assertTrue(driverPageAction.verifyCorrectAnalyticsSurveyActiveMessageIsShownOnMap(EMPTY, NOTSET));
			return true;
		});
	}

	private void initializePageObjects() {
		PageObjectFactory pageObjectFactory = new PageObjectFactory();

		driverViewPage = pageObjectFactory.getDriverViewPage();
		PageFactory.initElements(getDriver(), driverViewPage);

		loginPage = pageObjectFactory.getLoginPage();
		setLoginPage(loginPage);
		PageFactory.initElements(getDriver(), loginPage);

		HomePage homePage = pageObjectFactory.getHomePage();
		setHomePage(homePage);
		PageFactory.initElements(getDriver(), homePage);
		
		complianceReportsPage = pageObjectFactory.getComplianceReportsPage();
		PageFactory.initElements(getDriver(), complianceReportsPage);
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

	/* * Test Case ID: TC2358_AnalyticsReportModeForLicensedCustomers
	 * Script:
	 * - Log into the UI as Picarro Admin
	 * - Navigate to the Manage Customers page
	 * - Select a customer that does not have Analytics license enabled and click on the Edit button
	 * - Click on the Analytics checkbox
	 * - Click OK
	 * - Log out of the UI
	 * - Log back in as a customer supervisor or admin user
	 * - Click on Reports -> Compliance on the menu at left
	 * - Click on New Compliance Report button
	 * Results:
	 * - User will see a list of customers	
	 * - User will see configuration page for that customer with list of licensable features
	 * - Analytics checkbox is now checked
	 * - User is taken back to list of customers
	 * - Customer user sees dashboard specific to that customer
	 * - User is shown a list of existing Compliance Reports
	 * - In the Report Mode section near the top, "Analytics" radio button is present
	 */

	@Test 
	public void TC2358_AnalyticsReportModeForLicensedCustomers() throws Exception{
		Log.info("\nTestcase - TC2358_AnalyticsReportModeForLicensedCustomers\n");

		String customerName = ManageCustomerPageActions.workingDataRow.get().name;
		String userName = ManageUsersPageActions.workingDataRow.get().username;
		String userPassword = ManageUsersPageActions.workingDataRow.get().password;

		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		/* Unselect Anaytics*/
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.ANALYTICS);

		getHomePage().logout();

		/* Select Analytics*/
		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndSelectLicensedFeatures(customerName, LicensedFeatures.ANALYTICS);

		getHomePage().logout();

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);

		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.clickOnNewReportButton(EMPTY, NOTSET);
		complianceReportsPageAction.verifyNewPageLoaded(EMPTY, NOTSET);
		assertTrue(complianceReportsPageAction.verifyAnalyticsReportModeIsShownOnPage(EMPTY, NOTSET));
		complianceReportsPageAction.getComplianceReportsPage().selectReportMode(ReportModeFilter.Analytics);
		complianceReportsPageAction.clickOnCancelButton(EMPTY, NOTSET);

		getHomePage().logout();
	}

	/* * Test Case ID: TC2361_CreateNewCustomerWithAnalyticsReportLicense
	 * Script:
	 * - Log into the UI as Picarro Admin
	 * - Navigate to the Manage Customers page and click on the Add New Customer button
	 * - Fill out the Name and EULA fields, and check the Account Enabled and Analytics buttons and click OK
	 * - Navigate to the Manage Locations page and click on the Add New Locations button
	 * - Fill out the necessary fields and click OK
	 * - Navigate to the Manage Users page and click on the Add New Customer User button
	 * - Select the newly-created customer from the dropdown, select Supervisor or Utility Admin role and fill out the other necessary information and click OK
	 * - Log out of the UI
	 * - Log back into the UI as the newly created user
	 * - Click on Reports -> Compliance on the left
	 * - Click on New Compliance Report
	 * Results:
	 * - User if taken to the Dashboard page
	 * - User is navigated to the New Customer page
	 * - User is navigated back to the list of customers and newly created customer is present
	 * - User is navigated to the New Location page
	 * - User is navigated back to the list of locations and newly created location is present
	 * - User is navigated to the New User page
	 * - User is navigated back to the list of users and the newly created user is present
	 * - Customer user is shown dashboard specific to that customer
	 * - User is shown the Compliance Reports page (no reports exist yet)
	 * - In the Report Mode section at the top of the New Compliance Report page, "Analytics" is present
	 */
	@Test 
	public void TC2361_CreateNewCustomerWithAnalyticsReportLicense() throws Exception{
		Log.info("\nTestcase - TC2361_CreateNewCustomerWithAnalyticsReportLicense\n");


		String userName = ManageUsersPageActions.workingDataRow.get().username;
		String userPassword = ManageUsersPageActions.workingDataRow.get().password;
		String surveyorName = ManageSurveyorPageActions.workingDataRow.get().description;
		String surveyTag = DriverViewPageActions.workingDataRow.get().surveyTag;

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);

		getHomePage().clickOnDashboardLink();
		assertTrue(getHomePage().getTagListRecentDrivingSurveys().size() == 1);
		assertTrue(getHomePage().getTagListRecentDrivingSurveys().get(0).equals(surveyTag));
		assertTrue(getHomePage().getSurveyorListActiveSurveyors().size() == 1);
		assertTrue(getHomePage().getSurveyorListActiveSurveyors().get(0).equals(surveyorName));


		complianceReportsPageAction.open(EMPTY, NOTSET);
		assertTrue(complianceReportsPage.getListComplianceReports().get(0).equals(""));
		complianceReportsPageAction.clickOnNewReportButton(EMPTY, NOTSET);
		complianceReportsPageAction.verifyNewPageLoaded(EMPTY, NOTSET);
		assertTrue(complianceReportsPageAction.verifyAnalyticsReportModeIsShownOnPage(EMPTY, NOTSET));
		complianceReportsPageAction.getComplianceReportsPage().selectReportMode(ReportModeFilter.Analytics);
		assertTrue(complianceReportsPageAction.verifyAnalyticsReportModeIsShownOnPage(EMPTY, NOTSET));
		complianceReportsPageAction.clickOnCancelButton(EMPTY, NOTSET);

		getHomePage().logout();
	}

	/* * Test Case ID: TC2360_AnalyticsReportModeLicenseRevokedFromCustomer
	 * Script:
	 * - Log into the UI as Picarro Admin
	 * - Navigate to the Manage Customers page
	 * - Select a customer that has an Analytics license and click the Edit button
	 * - Uncheck the Analytics checkbox and click OK
	 * - Log out of the UI
	 * - Log back into the UI as a supervisor or admin of the above customer
	 * - Click on Reports -> Compliance at left
	 * - Click on the New Compliance Report button
	 * Results:
	 * - User is taken to Dashboard
	 * - User is shown a list of customers
	 * - User is shown the configuration for that customer and a list of licensable features
	 * - User is taken back to list of customers
	 * - Customer user sees dashboard specific to that customer
	 * - User is shown a list of existing Compliance Reports
	 * - In the Report Mode section near the top, "Analytics" radio button is not present
	 */

	@Test 
	public void TC2360_AnalyticsReportModeLicenseRevokedFromCustomer() throws Exception{
		Log.info("\nTestcase - TC2360_AnalyticsReportModeLicenseRevokedFromCustomer\n");

		String customerName = ManageCustomerPageActions.workingDataRow.get().name;
		String userName = ManageUsersPageActions.workingDataRow.get().username;
		String userPassword = ManageUsersPageActions.workingDataRow.get().password;

		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		/* Unselect Anaytics*/
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.ANALYTICS);

		getHomePage().logout();

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);

		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.clickOnNewReportButton(EMPTY, NOTSET);
		complianceReportsPageAction.verifyNewPageLoaded(EMPTY, NOTSET);
		assertFalse(complianceReportsPageAction.verifyAnalyticsReportModeIsShownOnPage(EMPTY, NOTSET));

		getHomePage().logout();
	}

	/* * Test Case ID: TC2359_AnalyticsReportModeNotAvailableForUnlicensedCustomers
	 * Script:
	 * - Log into the UI as a customer supervisor or admin user
	 * - Click on Reports -> Compliance on the menu at left
	 * - Click on New Compliance Report button
	 * Results:
	 * - Customer user sees dashboard specific to that customer
	 * - User is shown a list of existing Compliance Reports
	 * - In the Report Mode section near the top, "Analytics" radio button is not present
	 */

	@Test 
	public void TC2359_AnalyticsReportModeNotAvailableForUnlicensedCustomers() throws Exception{
		Log.info("\nTestcase - TC2359_AnalyticsReportModeNotAvailableForUnlicensedCustomers\n");

		String customerName = ManageCustomerPageActions.workingDataRow.get().name;
		String userName = ManageUsersPageActions.workingDataRow.get().username;
		String userPassword = ManageUsersPageActions.workingDataRow.get().password;

		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		/* Unselect Anaytics*/
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.ANALYTICS);

		getHomePage().logout();

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);

		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.clickOnNewReportButton(EMPTY, NOTSET);
		complianceReportsPageAction.verifyNewPageLoaded(EMPTY, NOTSET);
		assertFalse(complianceReportsPageAction.verifyAnalyticsReportModeIsShownOnPage(EMPTY, NOTSET));

		getHomePage().logout();
	}

	/* * Test Case ID: TC2374_VerifyUserNotAllowedCopyExistingAnalyticsReportIfAnalyticsLicenseDisabled
	 * Script:
	 * - Generate Analytics report
	 * - Disable analytics license feature for above customer
	 * - Click on Copy button
	 * - Click OK
	 * Results:
	 * - Validation message is displayed
	 * - Missing License
	 * - The original report was created with the following survey mode licenses: Analytics. Your account currently does not have access to these modes.
	 */

	@Test 
	public void TC2374_VerifyUserNotAllowedCopyExistingAnalyticsReportIfAnalyticsLicenseDisabled() throws Exception{
		Log.info("\nTestcase - TC2374_VerifyUserNotAllowedCopyExistingAnalyticsReportIfAnalyticsLicenseDisabled\n");

		String customerName = ManageCustomerPageActions.workingDataRow.get().name;
		String userName = ManageUsersPageActions.workingDataRow.get().username;
		String userPassword = ManageUsersPageActions.workingDataRow.get().password;
		String surveyTag = DriverViewPageActions.workingDataRow.get().surveyTag;
		
		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);
		
		final Integer reportDataRowID = 212;
		Map<String, String> testReport = addTestReport(userName, userPassword, surveyTag, reportDataRowID, SurveyModeFilter.Analytics);
	 	
		getHomePage().logout();
		
		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		 //Unselect Anaytics
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.ANALYTICS);

		getHomePage().logout();

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, userPassword);

		complianceReportsPageAction.open(EMPTY, NOTSET);
		complianceReportsPageAction.getComplianceReportsPage().clickOnFirstCopyComplianceBtn();
		assertTrue(complianceReportsPageAction.waitForLicenseMissingPopupToShow(EMPTY, NOTSET));
		getHomePage().logout();
	}
}
