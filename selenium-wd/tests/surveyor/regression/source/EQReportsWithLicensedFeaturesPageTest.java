/**
 *
 */
package surveyor.regression.source;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEET;
import java.util.Map;
import static surveyor.scommon.source.SurveyorConstants.CUSUSERROLESU;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import common.source.Log;
import common.source.TestSetup;
import surveyor.scommon.source.EQReportsPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.dataaccess.source.Analyzer.CapabilityType;
import surveyor.dataprovider.EQReportDataProvider;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.EQReportsPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.actions.DriverViewPageActions.DrivingSurveyType;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;

/**
 *
 *
 */
@RunWith(SurveyorTestRunner.class)
public class EQReportsWithLicensedFeaturesPageTest extends BaseReportsPageActionTest {

		private static DriverViewPageActions driverViewPageAction;
		private static TestEnvironmentActions testEnvironmentAction;
		private static LoginPageActions loginPageAction;
		private static EQReportsPageActions eqReportsPageAction;
		private static EQReportsPage eqReportsPage;
		private static ManageCustomerPageActions manageCustomerPageAction;
		private static ManageUsersPage manageUsersPage;
		private static Map<String, String> testAccount;
		private static String userName;
		private static String userPassword;
		private static String customerName;
		private static String locationName;
		private static String analyzerSharedKey;
		private static String analyzerName;
		private static String analyzerType;
		private static String surveyorName;
		private static String customerId;
		
		private int TestEnvDataRowID = 3, surveyRowID = 65, surveyRuntimeInSeconds = 100;
		private String db3DefnFile = "replay-db3.defn", db3file = "Surveyor.db3";
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

			// Select run mode here.
			setPropertiesForTestRunMode();
			if(testAccount == null){
				testAccount = createTestAccount("EQ_LicFeature", CapabilityType.IsotopicMethane);
				userName = testAccount.get("userName");
				userPassword = testAccount.get("userPassword");
				customerName = testAccount.get("customerName");
				locationName = testAccount.get("locationName");
				analyzerSharedKey = testAccount.get("analyzerSharedKey");
				analyzerName = testAccount.get("analyzerName");
				analyzerType = testAccount.get("analyzerType");
				surveyorName = testAccount.get("surveyorName");
				customerId = testAccount.get("customerId");
			} else {
				getLoginPage().open();
				getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
				manageCustomerPageAction.open(EMPTY, NOTSET);
				manageCustomerPageAction.getManageCustomersPage().editAndSelectLicensedFeatures(customerName, LicensedFeatures.values());
				getHomePage().logout();
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
			driverViewPageAction = ActionBuilder.createDriverViewPageAction();
			testEnvironmentAction = ActionBuilder.createTestEnvironmentAction();
			PageObjectFactory pageObjectFactory = new PageObjectFactory();		
			manageUsersPage = pageObjectFactory.getManageUsersPage();
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

			/* Skipped positive test as it's a common case already covered in other tests */
			
			String userName = testAccount.get("userName");
			String userPassword = testAccount.get("userPassword");
			String customerName = testAccount.get("customerName");

			getLoginPage().open();
			getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
			manageCustomerPageAction.open(EMPTY, NOTSET);
			manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.EQ);
			manageUsersPage.open();
			manageUsersPage.editUser(userName, CUSUSERROLESU, TIMEZONEET,true, false);
			getHomePage().logout();

			/* Without License */
			getLoginPage().open();
			getLoginPage().loginNormalAs(userName, userPassword);
			getHomePage().clickOnReport();
			assertFalse(getHomePage().isEQLinkVisible());
		}
		
		/**
		 *	Test Case ID: TC1042
		 *	Test Case Description:  Disable EQ feature for customer having EQ access and check driver view
		 *	SCRIPT:
		 *	-  Login to driver view as Customer's user
		 *	- Click on Mode button
		 *	- Start the EQ survey and complete it
		 *	- Login as Picarro Admin
		 *	- Navigate to Manage Customer Page
		 *	- Click on Edit button of Customer having EQ privilege
		 *	- Disable the EQ access by un-selecting the EQ check box
		 *	- Click on OK button
		 *	- Login to driver view as Customer's user
		 *	- Click on Mode button
		 *	RESULT:
		 *	- EQ Survey mode option is present
		 *	- EQ survey should be completed successfully
		 *	- EQ survey mode option is not present and user cannot perform EQ survey
		**/
		@Test
		public void TC1042_DisableEQFeatureAndCheckDriverView() throws Exception {
			Log.info("\nTestcase - TC1042_DisableEQFeatureAndCheckDriverView ...\n");
		
			getLoginPage().open();
			getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
			/*Step 1. setup analyzer configuration */
			updateAnalyzerConfiguration(testEnvironmentAction, analyzerName, analyzerSharedKey, TestEnvDataRowID);

			/* Step 2: startAnalyzerSurvey */
			startAnalyzerSurvey(testEnvironmentAction, DrivingSurveyType.EQ, driverViewPageAction, db3DefnFile, db3file, surveyRowID, surveyRuntimeInSeconds);
			TestSetup.restartAnalyzer();
			driverViewPageAction.open("", -1);
			driverViewPageAction.waitForConnectionToComplete("", -1);
			TestSetup.replayDB3Script(db3DefnFile, db3file);
			driverViewPageAction.clickOnModeButton("", -1);
			assertTrue(driverViewPageAction.verifyStartEQSurveyButtonIsVisible(EMPTY, surveyRowID));
			driverViewPageAction.startEQDrivingSurvey("", surveyRowID);
			
			/* Step 3: stopAnalyzerSurvey */
			stopAnalyzerSurvey(testEnvironmentAction, driverViewPageAction,analyzerName, analyzerSharedKey, surveyorName);		
			TestSetup.stopAnalyzer();

			/* Step 4: disable EQ license on the customer */
			getLoginPage().open();
			getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
			manageCustomerPageAction.open(EMPTY, NOTSET);
			manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(customerName, LicensedFeatures.EQ);
			getHomePage().logout();

			getLoginPage().open();
			getLoginPage().loginNormalAs(userName, userPassword);

			/* Step 5: startAnalyzerSurvey and check EQ Mode */
			TestSetup.restartAnalyzer();
			driverViewPageAction.open("", -1);
			driverViewPageAction.waitForConnectionToComplete("", -1);
			TestSetup.replayDB3Script(db3DefnFile, db3file);
			driverViewPageAction.clickOnModeButton("", -1);
			assertTrue(driverViewPageAction.verifyStartEQSurveyButtonIsNotVisible(EMPTY, surveyRowID));
			TestSetup.stopAnalyzer();
		}
}