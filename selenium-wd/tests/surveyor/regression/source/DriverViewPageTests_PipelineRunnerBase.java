package surveyor.regression.source;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Random;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import common.source.ExceptionUtility;
import common.source.Log;
import common.source.TestSetup;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.SurveyViewPageActions;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyorTestRunner;

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
public class DriverViewPageTests_PipelineRunnerBase extends BaseMapViewTest {

	protected DriverViewPageActions driverViewPageAction;
	protected SurveyViewPageActions surveyViewPageAction;
	protected static DriverViewPage driverViewPage;
	protected static ManageCustomersPage manageCustomersPage = null;
	protected static ManageUsersPage manageUsersPage = null;
	protected static HomePage homePage;
	protected static LoginPage loginPage;

	public DriverViewPageTests_PipelineRunnerBase() throws IOException {
		super();
	}

	@BeforeClass
	public static void beforeTestClass() {
			initializeTestObjects();
	}

	@Before
	public void beforeTestMethod() {
		try {
			initializeTestObjects();
			initializePageObjects();
			driverViewPageAction = new DriverViewPageActions(getDriver(), getBaseURL(),getTestSetup());
			surveyViewPageAction = new SurveyViewPageActions(getDriver(), getBaseURL(),getTestSetup());
			TestSetup.restartAnalyzer();
		} catch (UnknownHostException e) {
			Log.info(ExceptionUtility.getStackTraceString(e));
		} catch (IOException e) {
			Log.info(ExceptionUtility.getStackTraceString(e));
		}
	}

	private void initializePageObjects() {
		PageObjectFactory pageObjectFactory = new PageObjectFactory();

		homePage = pageObjectFactory.getHomePage();
		PageFactory.initElements(getDriver(), homePage);

		loginPage = pageObjectFactory.getLoginPage();
		PageFactory.initElements(getDriver(), loginPage);

		driverViewPage = pageObjectFactory.getDriverViewPage();
		PageFactory.initElements(getDriver(), driverViewPage);

		// Additional page objects.
		manageCustomersPage = pageObjectFactory.getManageCustomersPage();
		PageFactory.initElements(getDriver(),  manageCustomersPage);

		manageUsersPage = pageObjectFactory.getManageUsersPage();
		PageFactory.initElements(getDriver(),  manageUsersPage);
	}

	protected void executeReplayWithLongDB3(final Integer userDataRowID, final Integer analyzerDb3DataRowID,
			final Integer[] surveyDataRowIDs, final Integer surveyRuntimeInSeconds,
			final Integer numberOfSurveys) throws Exception {
		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, userDataRowID);

		Log.info("Starting Analyzer...");
		getTestEnvironmentAction().startAnalyzer(EMPTY, analyzerDb3DataRowID); 	// start analyzer.
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);

		Log.info("Starting Replay...");
		getTestEnvironmentAction().startReplay(EMPTY, analyzerDb3DataRowID); 	// start replay db3 file.

		int iteration = 0;
		int surveyIdx = 0;
		while (iteration < numberOfSurveys) {
			Log.info(String.format("Initiating survey number-[%d]. [%d] more surveys before completion", iteration, numberOfSurveys-iteration-1));

			// pick a random survey from the list.
			surveyIdx = new Random().nextInt(surveyDataRowIDs.length-1);
			int surveyDataRowID = surveyDataRowIDs[surveyIdx];

			// start survey.
			driverViewPageAction.getDriverViewPage().setUseAnalyzerReadyLongTimeout(true);   // Use longer timeout.
			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
			driverViewPageAction.startDrivingSurvey(EMPTY, surveyDataRowID);
			getTestEnvironmentAction().idleForSeconds(String.valueOf(surveyRuntimeInSeconds), NOTSET);

			// stop survey.
			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
			driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);

			iteration++;
			surveyIdx++;
		}

		// Stop simulator and PSA.
		Log.info("Stopping Analyzer...");
		getTestEnvironmentAction().stopAnalyzer(EMPTY, NOTSET);
	}
}