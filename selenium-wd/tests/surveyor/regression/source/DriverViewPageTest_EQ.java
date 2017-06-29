/**
 *
 */
package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import java.util.Map;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
import common.source.TestSetup;
import surveyor.scommon.source.EQReportsPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageSurveyorAdminPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.dataaccess.source.Analyzer.CapabilityType;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.EQReportsPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.actions.DriverViewPageActions.DrivingSurveyType;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;

/**
 * 
 * Use shared analyzer in this class only !!!
 */
@RunWith(SurveyorTestRunner.class)
public class DriverViewPageTest_EQ extends BaseReportsPageActionTest {

		private static DriverViewPageActions driverViewPageAction;
		private static TestEnvironmentActions testEnvironmentAction;
		private static ManageCustomerPageActions manageCustomerPageAction;
		private static EQReportsPageActions eqReportsPageAction;
		private static ManageSurveyorPage manageSurveyorPage;
		private static ManageSurveyorAdminPage manageSurveyorAdminPage;
		private static EQReportsPage eqReportsPage;
		private static Map<String, String> testAccount;
		private static String userName;
		private static String userPassword;
		private static String customerName;
		private static String analyzerSharedKey;
		private static String analyzerName;
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
			initializePageObjects();
			
			// Select run mode here.
			setPropertiesForTestRunMode();
			if(testAccount == null){
				testAccount = createTestAccount("EQ_LicFeature", CapabilityType.IsotopicMethane);
				userName = testAccount.get("userName");
				userPassword = testAccount.get("userPassword");
				customerName = testAccount.get("customerName");
				analyzerSharedKey = testAccount.get("analyzerSharedKey");
				analyzerName = testAccount.get("analyzerName");
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
			eqReportsPageAction = new EQReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
			manageCustomerPageAction = new ManageCustomerPageActions(getDriver(), getBaseURL(), getTestSetup());
			driverViewPageAction = ActionBuilder.createDriverViewPageAction();
			testEnvironmentAction = ActionBuilder.createTestEnvironmentAction();
			PageObjectFactory pageObjectFactory = new PageObjectFactory();		
			setLoginPage(pageObjectFactory.getLoginPage());
			setHomePage(pageObjectFactory.getHomePage());
			eqReportsPage = (EQReportsPage)eqReportsPageAction.getPageObject();
			setReportsPage(eqReportsPage);
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