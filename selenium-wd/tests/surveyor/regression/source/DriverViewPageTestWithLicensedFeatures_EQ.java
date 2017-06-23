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
import common.source.Log;
import common.source.TestSetup;
import surveyor.dataaccess.source.Analyzer.CapabilityType;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.DriverViewPageActions.DrivingSurveyType;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.SurveyViewPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;

/*
 * **** NOTES ****:
 *  1. Action based tests that work on MapView (Survey, Observer, Driver) can derive from BaseMapViewTest.
 *  2. If any of the tests do NOT use TestEnvironment actions for starting Analyzer and simulator then
 *  they should follow this convention to start simulator:
 *    Mark the test as TC*_SimulatorTest_* and it will be detected as Simulator based test and will trigger
 *    installation of Simulator pre-requisites before running the test.
 *
 */
@RunWith(SurveyorTestRunner.class)
public class DriverViewPageTestWithLicensedFeatures_EQ extends BaseReportsPageActionTest {

	private static DriverViewPageActions driverViewPageAction;
	private static LoginPageActions loginPageAction;
	TestEnvironmentActions testEnvironmentAction;
	private static ManageCustomerPageActions manageCustomerPageAction;
	private static Map<String, String> testAccount, testSurvey;

	private static String userName;
	private static String userPassword;
	private static String customerName;
	private static String locationName;
	private static String analyzerSharedKey;
	private static String analyzerName;
	private static String analyzerType;
	private static String surveyorName;
	private static String surveyTag;
	private static String customerId;

	private int TestEnvDataRowID = 3, surveyRowID = 65, surveyRuntimeInSeconds = 100;
	private String db3DefnFile = "replay-db3-eth.defn", db3file = "Surveyor_2055-PICARRO_EQ6.db3";
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
	public void beforeTestMethod() throws Exception {
			initializeTestObjects();
			initializePageActions();
			if(testAccount == null){
				testAccount = createTestAccount("EQ-DriverView", CapabilityType.Ethane);
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
				manageCustomerPageAction.getManageCustomersPage().editAndSelectLicensedFeatures(testAccount.get("customerName"), LicensedFeatures.values());
				getHomePage().logout();
			}
	}

	private void initializePageActions() {
		driverViewPageAction = ActionBuilder.createDriverViewPageAction();
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
		manageCustomerPageAction = new ManageCustomerPageActions(getDriver(), getBaseURL(), getTestSetup());
		testEnvironmentAction = ActionBuilder.createTestEnvironmentAction();
		PageObjectFactory pageObjectFactory = new PageObjectFactory();
		setLoginPage(pageObjectFactory.getLoginPage());
		setHomePage(pageObjectFactory.getHomePage());
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
	@Test /* Depend on US4438 */
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
		manageCustomerPageAction.getManageCustomersPage().editAndUnSelectLicensedFeatures(testAccount.get("customerName"), LicensedFeatures.EQ);
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