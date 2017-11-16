/**
 *
 */
package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;
import common.source.Log;
import surveyor.scommon.source.EQReportsPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageSurveyorAdminPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.dataprovider.EQReportDataProvider;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.EQReportsPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.entities.CustomerSurveyInfoEntity;
import surveyor.scommon.generators.TestDataGenerator;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;

/**
 *
 *
 */
@RunWith(SurveyorTestRunner.class)
public class EQReportsWithLicensedFeaturesPageTest extends BaseReportsPageActionTest {

		private static LoginPageActions loginPageAction;
		private static ManageCustomerPageActions manageCustomerPageAction;
		private static ManageLocationPageActions manageLocationPageAction;
		private static ManageUsersPageActions manageUsersPageAction;
		private static EQReportsPageActions eqReportsPageAction;
		private static ManageSurveyorPage manageSurveyorPage;
		private static ManageSurveyorAdminPage manageSurveyorAdminPage;
		private static CustomerSurveyInfoEntity custSrvInfo ;
		private static EQReportsPage eqReportsPage;


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

			EQReportsPage eqReportsPage = pageObjectFactory.getEqReportsPage();
			PageFactory.initElements(getDriver(),  eqReportsPage);

			manageSurveyorPage = pageObjectFactory.getManageSurveyorPage();
			PageFactory.initElements(getDriver(), manageSurveyorPage);

			manageSurveyorAdminPage = pageObjectFactory.getManageSurveyorAdminPage();
			PageFactory.initElements(getDriver(), manageSurveyorAdminPage);

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
				eqReportsPageAction.fillWorkingDataForReports(getUnitTestReportRowID());
			}
		}

		/**
		 * Initializes the page action objects.
		 * @throws Exception
		 */
		protected static void initializePageActions() throws Exception {
			loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
			eqReportsPageAction = new EQReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
			manageCustomerPageAction = new ManageCustomerPageActions(getDriver(), getBaseURL(), getTestSetup());
			manageUsersPageAction = ActionBuilder.createManageUsersPageAction();
			manageLocationPageAction = ActionBuilder.createManageLocationPageAction();
			PageObjectFactory pageObjectFactory = new PageObjectFactory();
			setLoginPage(pageObjectFactory.getLoginPage());
			setHomePage(pageObjectFactory.getHomePage());
			eqReportsPage = (EQReportsPage)eqReportsPageAction.getPageObject();
			setReportsPage(eqReportsPage);
		}

		/**
		 * Test Case ID: TC558_EQReportIsNotAccessibleWithoutEQPrivilege
		 * Test Description: EQ Report page is not accessible if customer don’t have EQ privilege - Customer Supervisor user
		 * Script:
		 *	- Login as Customer1's Supervisor user
		 * - Click on Reports (left side menu)
		 * - Click on EQ and generate the EQ report
		 * - Login as Customer2's Supervisor user
		 * - Click on Reports (left side menu)
		 * Results:
		 *	- EQ Report option is present
		 * - User can generate EQ report successfully
		 * - EQ report link is not present for Customer2's user and user cannot access EQ report page
		 */
		@Test
		@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC558, location = EQReportDataProvider.class)
		public void TC558_EQReportIsNotAccessibleWithoutEQPrivilege(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
			Log.info("\nRunning TC558_EQReportIsNotAccessibleWithoutEQPrivilege ...");

			final int newCustomerRowID = 14;
			final int newLocationRowID = 26;
			final int newCustomerUserRowID = 32;
			final int newSurveyorRowID = 35;
			final int newAnalyzerRowID = 29;
			final int newRefGasBottleRowID = 12;
			final int DB3_ANALYZER_ROW_ID = 76;	 	  /* TestEnvironment datasheet rowID (specifies Analyzer, Replay DB3) */
			final int SURVEY_ROW_ID = 3;	 		  /* Survey information  */
			final int SURVEY_RUNTIME_IN_SECONDS = 20; /* Number of seconds to run the survey for. */
			boolean calibrationRecord = true;

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(String.format("%s:%s", PICDFADMIN, PICADMINPSWD), NOTSET);

			custSrvInfo = new CustomerSurveyInfoEntity(newCustomerRowID, newLocationRowID, newCustomerUserRowID, newAnalyzerRowID,
					newSurveyorRowID, newRefGasBottleRowID, DB3_ANALYZER_ROW_ID, SURVEY_RUNTIME_IN_SECONDS, SURVEY_ROW_ID, calibrationRecord);
			new TestDataGenerator().generateNewCustomerAndSurvey(custSrvInfo);

			String newUsername = ManageUsersPageActions.workingDataRow.get().username;
			String newUserPass = ManageUsersPageActions.workingDataRow.get().password;

			/*Login With EQ License */
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.getLoginPage().loginNormalAs(newUsername, newUserPass);

			eqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
			createNewReport(eqReportsPageAction, getReportRowID(reportDataRowID1));
			waitForReportGenerationToComplete(eqReportsPageAction, getReportRowID(reportDataRowID1));
			eqReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
			eqReportsPageAction.clickOnViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
			assertTrue(eqReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
			getHomePage().logout();

			//Create customer user without EQ License
			final int newCustomer2RowID = 10;
			final int newLocation2RowID = 11;
			final int newCustomer2UserRowID = 21;

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(String.format("%s:%s", PICDFADMIN, PICADMINPSWD), NOTSET);

			manageCustomerPageAction.open(EMPTY, NOTSET);
			manageCustomerPageAction.createNewCustomer(EMPTY, newCustomer2RowID);

			manageLocationPageAction.open(EMPTY, NOTSET);
			manageLocationPageAction.createNewLocation(EMPTY, newLocation2RowID);

			manageUsersPageAction.open(EMPTY, NOTSET);
			manageUsersPageAction.createNewCustomerUser(EMPTY, newCustomer2UserRowID);
			getHomePage().logout();

			String userName = ManageUsersPageActions.workingDataRow.get().username;
			String userPassword = ManageUsersPageActions.workingDataRow.get().password;

			/*Login Without EQ License */
			loginPageAction.getLoginPage().open();
			loginPageAction.getLoginPage().loginNormalAs(userName, userPassword);
			getHomePage().clickOnReport();
			assertFalse(getHomePage().isEQLinkVisible());
			getHomePage().logout();
		}

		/**
		 * Test Case ID: TC574_ReenableEQPrivileges
		 * Test Description: Re-enable EQ privilege for customer - Customer Supervisor user
		 * Script:
		 * - Customer don't have EQ privilege
		 * - Login as Customer's Supervisor user
		 * - Click on Reports (left side menu)
		 * - Login as Picarro Admin
		 * - Navigate to Manage Customer - > Edit (Customer's page)
		 * - Enable EQ privilege for the customer
		 * - Login again as Customer's Supervisor user
		 * - Click on Reports (left side menu)
		 * - Click on EQ and generate the EQ report
		 * Results:
		 * - EQ report link is not present for Customer's user and user cannot access EQ report page
		 * - EQ Report option is present
		 * - User can generate EQ report successfully
		 **/
		@Test
		@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC574, location = EQReportDataProvider.class)
		public void TC574_ReenableEQPrivileges(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
			Log.info("\nRunning TC574_ReenableEQPrivileges ...");

			final int newCustomerRowID = 14;
			final int newLocationRowID = 26;
			final int newCustomerUserRowID = 32;
			final int newSurveyorRowID = 35;
			final int newAnalyzerRowID = 29;
			final int newRefGasBottleRowID = 12;
			final int DB3_ANALYZER_ROW_ID = 76;	 	  /* TestEnvironment datasheet rowID (specifies Analyzer, Replay DB3) */
			final int SURVEY_ROW_ID = 3;	 		  /* Survey information  */
			final int SURVEY_RUNTIME_IN_SECONDS = 20; /* Number of seconds to run the survey for. */
			boolean calibrationRecord = true;

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(String.format("%s:%s", PICDFADMIN, PICADMINPSWD), NOTSET);

			custSrvInfo = new CustomerSurveyInfoEntity(newCustomerRowID, newLocationRowID, newCustomerUserRowID, newAnalyzerRowID,
					newSurveyorRowID, newRefGasBottleRowID, DB3_ANALYZER_ROW_ID, SURVEY_RUNTIME_IN_SECONDS, SURVEY_ROW_ID, calibrationRecord);
			new TestDataGenerator().generateNewCustomerAndSurvey(custSrvInfo);

			String customerName = ManageCustomerPageActions.workingDataRow.get().name;
			String newUsername = ManageUsersPageActions.workingDataRow.get().username;
			String newUserPass = ManageUsersPageActions.workingDataRow.get().password;

			/* Unselect EQ*/
			getLoginPage().open();
			getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
			manageCustomerPageAction.open(EMPTY, NOTSET);
			manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.EQ);
			getHomePage().logout();

			/*Login Without EQ License */
			loginPageAction.getLoginPage().open();
			loginPageAction.getLoginPage().loginNormalAs(newUsername, newUserPass);
			getHomePage().clickOnReport();
			assertFalse(getHomePage().isEQLinkVisible());
			getHomePage().logout();

			/* Select EQ*/
			getLoginPage().open();
			getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
			manageCustomerPageAction.open(EMPTY, NOTSET);
			manageCustomerPageAction.getManageCustomersPage().editAndSelectLicensedFeatures(customerName, LicensedFeatures.EQ);
			getHomePage().logout();

			/*Login With EQ License*/
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.getLoginPage().loginNormalAs(newUsername, newUserPass);
			eqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
			createNewReport(eqReportsPageAction, getReportRowID(reportDataRowID1));
			waitForReportGenerationToComplete(eqReportsPageAction, getReportRowID(reportDataRowID1));
			eqReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
			eqReportsPageAction.clickOnViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
			assertTrue(eqReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
			getHomePage().logout();
		}

		/**
		 * Test Case ID: TC574_ReenableEQPrivileges
		 * Test Description: Disable EQ feature for customer having EQ access
		 * Script:
		 * - Customer having EQ privilege
		 * - Login as Customer's Supervisor user
		 * - Click on Reports (left side menu)
		 * - Click on EQ and generate the EQ report
		 * - Login as Picarro Admin
		 * - Navigate to Manage Customer Page
		 * - Click on Edit button of Customer having EQ privilege
		 * - Disable the EQ access by un-selecting the EQ check box
		 * - Click on OK button
		 * - Log in as Customer's Supervisor user
		 * - Click on Report link
		 * - Login as Customer's Util admin
		 * - Click on Report link
		 * Results:
		 * - EQ Report option is present
		 * - User can generate EQ report successfully
		 * - EQ report link is not present for Customer's user and user cannot access EQ report page	 **/
		@Test
		@UseDataProvider(value = EQReportDataProvider.EQ_REPORT_PAGE_ACTION_DATA_PROVIDER_TC775, location = EQReportDataProvider.class)
		public void TC775_DisableEQPrivileges(
				String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
			Log.info("\nRunning TC775_DisableEQPrivileges ...");

			final int newCustomerRowID = 14;
			final int newLocationRowID = 26;
			final int newCustomerUserRowID = 32;
			final int newSurveyorRowID = 35;
			final int newAnalyzerRowID = 29;
			final int newRefGasBottleRowID = 12;
			final int DB3_ANALYZER_ROW_ID = 76;	 	  /* TestEnvironment datasheet rowID (specifies Analyzer, Replay DB3) */
			final int SURVEY_ROW_ID = 3;	 		  /* Survey information  */
			final int SURVEY_RUNTIME_IN_SECONDS = 20; /* Number of seconds to run the survey for. */
			boolean calibrationRecord = true;

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(String.format("%s:%s", PICDFADMIN, PICADMINPSWD), NOTSET);

			custSrvInfo = new CustomerSurveyInfoEntity(newCustomerRowID, newLocationRowID, newCustomerUserRowID, newAnalyzerRowID,
					newSurveyorRowID, newRefGasBottleRowID, DB3_ANALYZER_ROW_ID, SURVEY_RUNTIME_IN_SECONDS, SURVEY_ROW_ID, calibrationRecord);
			new TestDataGenerator().generateNewCustomerAndSurvey(custSrvInfo);

			String customerName = ManageCustomerPageActions.workingDataRow.get().name;
			String newUsername = ManageUsersPageActions.workingDataRow.get().username;
			String newUserPass = ManageUsersPageActions.workingDataRow.get().password;


			/*Login With EQ License*/
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.getLoginPage().loginNormalAs(newUsername, newUserPass);
			eqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
			createNewReport(eqReportsPageAction, getReportRowID(reportDataRowID1));
			waitForReportGenerationToComplete(eqReportsPageAction, getReportRowID(reportDataRowID1));
			eqReportsPageAction.openComplianceViewerDialog(EMPTY, getReportRowID(reportDataRowID1));
			eqReportsPageAction.clickOnViewerPDF(EMPTY, getReportRowID(reportDataRowID1));
			assertTrue(eqReportsPageAction.waitForPDFDownloadToComplete(EMPTY, getReportRowID(reportDataRowID1)));
			getHomePage().logout();


			/* Unselect EQ*/
			getLoginPage().open();
			getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
			manageCustomerPageAction.open(EMPTY, NOTSET);
			manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.EQ);
			getHomePage().logout();

			/*Login Supervisor Without EQ License */
			loginPageAction.getLoginPage().open();
			loginPageAction.getLoginPage().loginNormalAs(newUsername, newUserPass);
			getHomePage().clickOnReport();
			assertFalse(getHomePage().isEQLinkVisible());
			getHomePage().logout();

			/* Add Customer Utility admin user*/
			final int newCustomerUAUserRowID = 32;

			getLoginPage().open();
			getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
			manageUsersPageAction.open(EMPTY, NOTSET);
			manageUsersPageAction.createNewCustomerUser(EMPTY, newCustomerUAUserRowID);
			String utilityAdminUsername = ManageUsersPageActions.workingDataRow.get().username;
			getHomePage().logout();

			/*Login as Utility Admin Without EQ License */
			loginPageAction.getLoginPage().open();
			loginPageAction.getLoginPage().loginNormalAs(utilityAdminUsername, newUserPass);
			getHomePage().clickOnReport();
			assertFalse(getHomePage().isEQLinkVisible());
			getHomePage().logout();

		}
}