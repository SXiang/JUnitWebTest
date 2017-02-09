package surveyor.regression.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.ExceptionUtility;
import common.source.Log;
import common.source.OLMapEntities.Indication;
import common.source.TestSetup;
import surveyor.dataprovider.DriverViewDataProvider;
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
public class DriverViewPageTest3 extends BaseMapViewTest {

	private DriverViewPageActions driverViewPageAction;
	private SurveyViewPageActions surveyViewPageAction;
	private static DriverViewPage driverViewPage;
	private static ManageCustomersPage manageCustomersPage = null;
	private static ManageUsersPage manageUsersPage = null;
	private static HomePage homePage;
	private static LoginPage loginPage;

	public DriverViewPageTest3() throws IOException {
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

	/**
	 * Test Case ID: TC_TBD_SimulatorTest_DrivingSurvey_CompareIndications_DriverView_SurveyView_UtilityAdmin
	 * Script: -
	 * 1. Login to driver view as Pic Utility admin
	 * 2. Start PSA and Host Simulator.
	 * 3. Click on Start Survey button. Create following surveys
	 *    a) Manual
	 *    b) Standard
	 *    c) Operator
	 * 4. Let the survey run for few seconds
	 * 5. Count the number of indications shown in Driver view
	 * 6. Stop survey
	 * 7. Wait for survey to be uploaded to cloud.
	 * 8. Goto survey view and count the number of indications shown in survey view.
	 * Results: -
	 * 1. Verify number of indications shown in survey view matches the number of indications shown in driver view.
	 *
	 * @throws Exception
	 */
	@Test
	@UseDataProvider(value = DriverViewDataProvider.DRIVERVIEW_COMPARE_INDICATIONS_DRIVERVIEW_SURVEYVIEW_PROVIDER, location = DriverViewDataProvider.class)
	public void TC_TBD_SimulatorTest_DrivingSurvey_CompareIndications_DriverView_SurveyView_UtilityAdmin_Driver(Integer userDataRowID,
			Integer analyzerDb3DataRowID, Integer surveyRuntimeInSeconds, Integer surveyDataRowID) throws Exception {
		Log.info("TC_TBD_SimulatorTest_DrivingSurvey_CompareIndications_DriverView_SurveyView_UtilityAdmin_Driver");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, userDataRowID);   /* Customer Driver */

		Log.info("Starting Analyzer...");
		getTestEnvironmentAction().startAnalyzer(EMPTY, analyzerDb3DataRowID); 	// start analyzer.
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);

		Log.info("Starting Replay...");
		getTestEnvironmentAction().startReplay(EMPTY, analyzerDb3DataRowID); 	// start replay db3 file.

		// start survey.
		driverViewPageAction.getDriverViewPage().setUseAnalyzerReadyLongTimeout(true);   // Use longer timeout.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.startDrivingSurvey(EMPTY, surveyDataRowID);

		// zoom out on driver view page.
		driverViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		driverViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);

		// collect indications shown during the survey.
		Set<Indication> indicationsOnDriverView = driverViewPageAction.collectIndicationsDuringSurvey(surveyRuntimeInSeconds);

		// stop survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);

		// wait for a while before shutting down Analyzer.
		getTestEnvironmentAction().idleForSeconds(String.valueOf(20), -1);

		// shutdown Analyzer.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.clickOnShutdownButton(EMPTY, NOTSET);
		driverViewPageAction.clickOnShutdownConfirmButton(EMPTY, NOTSET);
		getTestEnvironmentAction().idleForSeconds(String.valueOf(10), -1);

		// check/post survey session from DB3 to cloud.
		getTestEnvironmentAction().checkPostSurveySessionsFromDB3ToCloud(EMPTY, analyzerDb3DataRowID);

		// Stop simulator and PSA.
		Log.info("Stopping Analyzer...");
		getTestEnvironmentAction().stopAnalyzer(EMPTY, NOTSET);

		// Goto survey view page for current survey and collect indications shown on page.
		getHomePageAction().open(EMPTY, NOTSET);
		getHomePageAction().clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.get().surveyTag, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.waitForIndicationsToBeShownOnMap(EMPTY, NOTSET);
		Set<Indication> indicationsOnSurveyView = surveyViewPageAction.getIndicationsShownOnPage();

		// Compare indications on driver view and survey view.
		assertTrue(indicationsOnDriverView.equals(indicationsOnSurveyView));
	}

	/**
	 * Test Case ID: TC_NA_SimulatorTest_DrivingSurvey_CH4_C2H6_Missing_UtilityAdmin
	 * Script: -
	 * 1. Login to driver view as Pic Utility admin
	 * 2. Start PSA and Host Simulator.
	 * 3. Replay Ethane and Methane DB3s by skipping following values from measurement:
	 *    a) CH4
	 *    b) C2H6
	 *    c) CH4 & C2H6
	 * 4. Create survey tag, select  Survey Time: Day Solar Radiation: Moderate Wind: Light Survey Type: Standard
	 * 4. Click on Start Survey button
	 * 5. Let the survey run for few seconds
	 * 6. Stop survey
	 * Results: -
	 * 1. Verify surveys can be started and stopped correctly.
	 * 2. Verify there is no runtime error in pipelinerunner.
	 * @throws Exception
	 */
	@Test
	@UseDataProvider(value = DriverViewDataProvider.DRIVERVIEW_MISSING_COLUMN_VALUES_PROVIDER, location = DriverViewDataProvider.class)
	public void TC_TBD_SimulatorTest_DrivingSurvey_CH4_C2H6_Missing_UtilityAdmin(Integer userDataRowID,
			Integer analyzerDb3DataRowID, Integer surveyRuntimeInSeconds, Integer surveyDataRowID) throws Exception {
		Log.info("TC_NA_SimulatorTest_StartDrivingSurvey_CH4_C2H6_Missing_PicAdmin");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, userDataRowID);   /* Customer Driver */

		Log.info("Starting Analyzer...");
		getTestEnvironmentAction().startAnalyzer(EMPTY, analyzerDb3DataRowID); 	// start analyzer.
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);

		Log.info("Starting Replay...");
		getTestEnvironmentAction().startReplay(EMPTY, analyzerDb3DataRowID); 	// start replay db3 file.

		// start survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.startDrivingSurvey(EMPTY, surveyDataRowID);
		getTestEnvironmentAction().idleForSeconds(String.valueOf(surveyRuntimeInSeconds), NOTSET);

		// stop survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);

		// Stop simulator and PSA.
		Log.info("Stopping Analyzer...");
		getTestEnvironmentAction().stopAnalyzer(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC_TBD_ActionTest_DriverViewStartSurveyMultipleTimesMethaneReplayWithLongDB3
	 * Script: -
	 *	- Start a survey using Methane Analyzer with appropriate survey details in the dialog, select Survey Type "Standard" and click OK
	 *	- Run survey for about 2 minutes.
	 *	- From the Mode menu, click Stop Survey
	 *	- From the Mode menu, click Start Survey again
	 *	- When the dialog pops up, change the survey type "Manual", "RapidResponse", "Assessment" or "Operator" and click Start Survey
	 *	- From the Mode menu, click Stop Survey again
	 *	- Repeat start/stop sequence for multiple times.
	 * Results: -
	 *	- Verify surveys are created correctly and there is NO runtime error in PipelineRunner.
	 *
	 * @throws Exception
 	 **/
	@Test
	@UseDataProvider(value = DriverViewDataProvider.DRIVERVIEW_START_STOP_SURVEY_METH_PROVIDER, location = DriverViewDataProvider.class)
	public void TC_TBD_ActionTest_DriverViewStartSurveyMultipleTimesMethaneSurveys(Integer userDataRowID,
			Integer analyzerDb3DataRowID, Integer[] surveyDataRowIDs, Integer surveyRuntimeInSeconds,
			Integer numberOfSurveys) throws Exception {
		Log.info("\nRunning TC_TBD_ActionTest_DriverViewStartSurveyMultipleTimesMethaneSurveys");

		executeReplayWithLongDB3(userDataRowID, analyzerDb3DataRowID, surveyDataRowIDs, surveyRuntimeInSeconds, numberOfSurveys);
	}

	/**
	 * Test Case ID: TC_TBD_ActionTest_DriverViewStartSurveyMultipleTimesEthMethSurveys
	 * Script: -
	 *	- Start a survey using Ethane Analyzer with appropriate survey details in the dialog, select Survey Type "Standard" and click OK
	 *	- Run survey for about 2 minutes.
	 *	- From the Mode menu, click Stop Survey
	 *	- From the Mode menu, click Start Survey again
	 *	- When the dialog pops up, change the survey type "Manual", "RapidResponse", "Assessment" or "Operator" and click Start Survey
	 *	- From the Mode menu, click Stop Survey again
	 *	- Repeat start/stop sequence for multiple times.
	 * Results: -
	 *	- Verify surveys are created correctly and there is NO runtime error in PipelineRunner.
	 *
	 * @throws Exception
 	 **/
	@Test
	@UseDataProvider(value = DriverViewDataProvider.DRIVERVIEW_START_STOP_SURVEY_ETH_PROVIDER, location = DriverViewDataProvider.class)
	public void TC_TBD_ActionTest_DriverViewStartSurveyMultipleTimesEthaneSurveys(Integer userDataRowID,
			Integer analyzerDb3DataRowID, Integer[] surveyDataRowIDs, Integer surveyRuntimeInSeconds,
			Integer numberOfSurveys) throws Exception {
		Log.info("\nRunning TC_TBD_ActionTest_DriverViewStartSurveyMultipleTimesEthMethSurveys");

		executeReplayWithLongDB3(userDataRowID, analyzerDb3DataRowID, surveyDataRowIDs, surveyRuntimeInSeconds, numberOfSurveys);
	}

	private void executeReplayWithLongDB3(final Integer userDataRowID, final Integer analyzerDb3DataRowID,
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