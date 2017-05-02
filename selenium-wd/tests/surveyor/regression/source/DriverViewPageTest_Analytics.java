package surveyor.regression.source;

import static org.junit.Assert.*;
import java.io.IOException;
import java.net.UnknownHostException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import common.source.ExceptionUtility;
import common.source.Log;
import common.source.TestSetup;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.SurveyViewPageActions;
import surveyor.scommon.source.LoginPage;
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
public class DriverViewPageTest_Analytics extends BaseMapViewTest {

	private DriverViewPageActions driverViewPageAction;
	private SurveyViewPageActions surveyViewPageAction;
	private static LoginPage loginPage;

	public DriverViewPageTest_Analytics() throws IOException {
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
			initializePageActions();
			TestSetup.restartAnalyzer();
		} catch (UnknownHostException e) {
			Log.info(ExceptionUtility.getStackTraceString(e));
		} catch (IOException e) {
			Log.info(ExceptionUtility.getStackTraceString(e));
		}
	}

	private void initializePageActions() {
		driverViewPageAction = new DriverViewPageActions(getDriver(), getBaseURL(), getTestSetup());
		surveyViewPageAction = new SurveyViewPageActions(getDriver(), getBaseURL(), getTestSetup());
	}

	private void initializePageObjects() {
		PageObjectFactory pageObjectFactory = new PageObjectFactory();

		loginPage = pageObjectFactory.getLoginPage();
		PageFactory.initElements(getDriver(), loginPage);
	}

	/** Test Case ID: TC2368_DriverView_IndicationsAndLISAButtonsAreNotPresentInDisplayMenu
	 *  Script:
	 *	- Log into the tablet
	 *	- Click on Mode -> Start Survey
	 *	- Enter a tag, fill out environmental conditions, select Analytics survey mode and click Start Survey
	 *	- Click Display
	 *
	 *	Results:
	 *	- User is taken to Driver View
	 *	- Popup appears where user can configure survey
	 *	- Car icon turns red and "Analytics Survey Active" appears in bold green font at top left of map
	 *	- 8 Hour History, Concentration Chart, WindRose and FOV buttons are present. Indications, LISAs, Analysis and Field Notes buttons are not present
	 **/
	@Test
	public void TC2368_DriverView_IndicationsAndLISAButtonsAreNotPresentInDisplayMenu() throws Exception{
		Log.info("\nTestcase - TC2368_DriverView_IndicationsAndLISAButtonsAreNotPresentInDisplayMenu\n");

		final Integer analyzerDb3DataRowID = 58;
		final Integer surveyDataRowID = 61;

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		getTestEnvironmentAction().startAnalyzer(EMPTY, analyzerDb3DataRowID); 	// start analyzer. RFADS2004-PICARRO
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
		getTestEnvironmentAction().startReplay(EMPTY, analyzerDb3DataRowID);


		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.startDrivingSurvey(EMPTY, surveyDataRowID);
		getTestEnvironmentAction().idleForSeconds(String.valueOf(2), NOTSET);

		assertTrue(driverViewPageAction.verifyCrossHairIconIsShownOnMap("Red", NOTSET));
		assertTrue(driverViewPageAction.verifyCorrectAnalyticsSurveyActiveMessageIsShownOnMap(EMPTY, NOTSET));

		driverViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		// assert visible buttons in Display menu
		assertTrue(driverViewPageAction.verifyDisplaySwitch8HourHistoryButtonIsVisible(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifyDisplaySwitchWindroseButtonIsVisible(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifyDisplaySwitchFovsButtonIsVisible(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifyDisplaySwitchConcentrationChartButtonIsVisible(EMPTY, NOTSET));
		// assert hidden buttons in Display menu
		assertTrue(driverViewPageAction.verifyDisplaySwitchIndicationsButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifyDisplaySwitchLisasButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifyDisplaySwitchIsotopicAnalysisButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifyDisplaySwitchNotesButtonIsNotVisible(EMPTY, NOTSET));

		// Stop current simulator.
		getTestEnvironmentAction().stopAnalyzer(EMPTY, NOTSET);
	}

    /**
	 *	Test Case ID: TC2365
	 *	Test Case Description: Survey View - Only peaks above Survey Min Amplitude appear in Analytics Survey mode
	 *	Peaks in Analytics Survey mode should all be at or above the Survey Min Amplitude level set in the Locations page in the "Survey Mode: Analytics" section
	 *	SCRIPT:
	 *	- Log into UI as Picarro Admin
	 *	- Navigate to the Locations page
	 *	- Select a customer location and click Edit
	 *	- Set the Survey Min Amplitude level to a certain level (ex. 0.4)
	 *	- Click OK
	 *	- Have driver start an Analytics Survey
	 *	- Have driver drive to an area where indications are reliably found
	 *	- Have driver stop survey
	 *	- When survey has uploaded, click on the View Survey button
	 *	RESULT:
	 *	- Only indications above Survey Min Amplitude level will appear in Survey View
	 */
	@Ignore
	public void TC2365_SurveyView_OnlyPeaksAboveSurveyMinAmplitudeAppearInAnalyticsSurvey() throws Exception {
		Log.info("\nRunning TC2365_SurveyView_OnlyPeaksAboveSurveyMinAmplitudeAppearInAnalyticsSurvey ...");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		// NOTE: This test case requires host simulator update to generate Peaks of desired amplitude.
		// Test case has been included in US4307 to automate along with updated HostSim integration in automation framework.
	}

	/**
	 * Test Case ID: TC2370_SurveyView_IndicationsAndLISAButtonsAreNotPresentInDisplayMenu
	 * Script:
	 *	- Log into the UI
	 *	- Navigate to the Driving Surveys page
	 *	- Select an Analytics Survey and click the View Survey button
	 *	- Click Display
	 *
	 * Results:
	 *	- User is taken to Survey View of selected survey
	 *	- 8 Hour History and FOV buttons are present. Indications, LISAs, Analysis and Field Notes buttons are not present
	**/
	@Test
	public void TC2370_SurveyView_IndicationsAndLISAButtonsAreNotPresentInDisplayMenu() throws Exception {
		Log.info("\nRunning TC2370_SurveyView_IndicationsAndLISAButtonsAreNotPresentInDisplayMenu ...");

		final Integer analyzerDb3DataRowID = 59;
		final Integer surveyDataRowID = 61;

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		getTestEnvironmentAction().startAnalyzer(EMPTY, analyzerDb3DataRowID); 	// start analyzer. RFADS2004-PICARRO
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
		getTestEnvironmentAction().startReplay(EMPTY, analyzerDb3DataRowID);

		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.startDrivingSurvey(EMPTY, surveyDataRowID);
		getTestEnvironmentAction().idleForSeconds(String.valueOf(5), NOTSET);

		// stop survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);

		// wait for a while before shutting down Analyzer.
		getTestEnvironmentAction().idleForSeconds(String.valueOf(10), -1);

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

		// Goto survey view page for current survey and verify Display menu.
		getHomePageAction().open(EMPTY, NOTSET);
		getHomePageAction().clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.get().surveyTag, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);

		// assert visible buttons in Display menu
		assertTrue(surveyViewPageAction.verifyDisplaySwitch8HourHistoryButtonIsVisible(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyDisplaySwitchFovsButtonIsVisible(EMPTY, NOTSET));
		// assert hidden buttons in Display menu
		assertTrue(surveyViewPageAction.verifyDisplaySwitchWindroseButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyDisplaySwitchIndicationsButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyDisplaySwitchLisasButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyDisplaySwitchIsotopicAnalysisButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyDisplaySwitchNotesButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyDisplaySwitchConcentrationChartButtonIsNotVisible(EMPTY, NOTSET));
	}
}