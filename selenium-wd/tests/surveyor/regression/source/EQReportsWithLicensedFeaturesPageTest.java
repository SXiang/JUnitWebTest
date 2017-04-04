/**
 *
 */
package surveyor.regression.source;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEET;

import java.util.Map;

import static surveyor.scommon.source.SurveyorConstants.CUSTOMER_SQACUS;
import static surveyor.scommon.source.SurveyorConstants.CUSUSERROLESU;
import static surveyor.scommon.source.SurveyorConstants.EQDAYSURVEY;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.Log;
import surveyor.scommon.source.EQReportsPage;
import surveyor.scommon.source.ManageUsersAdminPage;
import surveyor.scommon.source.ReportsCommonPage;
import surveyor.dataprovider.EQReportDataProvider;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.EQReportsPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;

/**
 *
 *
 */
@RunWith(SurveyorTestRunner.class)
public class EQReportsWithLicensedFeaturesPageTest extends BaseReportsPageActionTest {

		private static LoginPageActions loginPageAction;
		private static EQReportsPageActions eqReportsPageAction;
		private static EQReportsPage eqReportsPage;
		private static ManageCustomerPageActions manageCustomerPageAction;
		private static ManageUsersAdminPage manageUsersAdminPage;
		private static Map<String, String> testAccount;
		@BeforeClass
		public static void beforeClass() {
			initializeTestObjects();
		}

		@Before
		public void beforeTest() throws Exception {
			initializeTestObjects();

			initializePageActions();

			// Select run mode here.
			setPropertiesForTestRunMode();
			
			if(testAccount == null){
				testAccount = createTestAccount("EQ_LicFeature");
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
			manageUsersAdminPage = new ManageUsersAdminPage(getDriver(), getBaseURL(), getTestSetup());
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

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
			eqReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
			createNewReport(eqReportsPageAction, getReportRowID(reportDataRowID1));
			assertTrue(eqReportsPageAction.waitForReportGenerationToComplete(EMPTY,  getReportRowID(reportDataRowID1)));

			String userName = testAccount.get("userName");
			String userPassword = testAccount.get("userPassword");
			String customerName = testAccount.get("customerName");

			getLoginPage().open();
			getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
			manageCustomerPageAction.open(EMPTY, NOTSET);
			manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.EQ);
			manageUsersAdminPage.editUser(userName, CUSUSERROLESU, TIMEZONEET,true, true);
			getHomePage().logout();

			/* Without License */
			getLoginPage().open();
			getLoginPage().loginNormalAs(userName, userPassword);
			getHomePage().clickOnReport();
			assertFalse(getHomePage().isEQLinkVisible());
		}
}