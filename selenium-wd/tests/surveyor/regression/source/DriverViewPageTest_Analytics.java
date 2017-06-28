package surveyor.regression.source;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.ExceptionUtility;
import common.source.HostSimInstructions;
import common.source.Log;
import common.source.TestSetup;
import common.source.WebElementExtender;
import common.source.HostSimInstructions.Action;
import common.source.HostSimInstructions.Measurement;
import common.source.HostSimInstructions.Selector;
import common.source.OLMapEntities.Indication;
import common.source.OLMapUtility.BreadcrumbColor;
import common.source.RegexUtility;
import surveyor.dataprovider.DriverViewDataProvider;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.actions.SurveyViewPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.entities.CustomerSurveyInfoEntity;
import surveyor.scommon.generators.TestDataGenerator;
import surveyor.scommon.source.SurveyorConstants.MinAmplitudeType;
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

	private static DriverViewPageActions driverViewPageAction;
	private static SurveyViewPageActions surveyViewPageAction;
	private static ManageLocationPageActions manageLocationPageActions;

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
			initializePageActions();
			TestSetup.restartAnalyzer();
		} catch (UnknownHostException e) {
			Log.info(ExceptionUtility.getStackTraceString(e));
		} catch (IOException e) {
			Log.info(ExceptionUtility.getStackTraceString(e));
		}
	}

	private void initializePageActions() {
		driverViewPageAction = ActionBuilder.createDriverViewPageAction();
		surveyViewPageAction = ActionBuilder.createSurveyViewPageAction();
		manageLocationPageActions = ActionBuilder.createManageLocationPageAction();
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
	 **/
	@Test
	public void TC2365_SurveyView_OnlyPeaksAboveSurveyMinAmplitudeAppearInAnalyticsSurveyMode() throws Exception {
		Log.info("\nTestcase - TC2365_SurveyView_OnlyPeaksAboveSurveyMinAmplitudeAppearInAnalyticsSurveyMode ...\n");

		final int picAdminUserDataRowID = 6;
		final int DB3_ANALYZER_ROW_ID = 72;	 	/* TestEnvironment datasheet rowID (specifies Analyzer, Replay DB3) */
		final int SURVEY_ROW_ID = 61;	 		/* Survey information  */
		final int SURVEY_RUNTIME_IN_SECONDS = 90; /* Number of seconds to run the survey for. */
		final int newCustomerRowID = 14;
		final int newLocationRowID = 17;
		final int newCustomerUserRowID = 26;
		final int newSurveyorRowID = 25;
		final int newAnalyzerRowID = 23;
		final int newRefGasBottleRowID = 7;

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, picAdminUserDataRowID);   /* Picarro Admin */

		CustomerSurveyInfoEntity custSrvInfo = new CustomerSurveyInfoEntity(newCustomerRowID, newLocationRowID, newCustomerUserRowID, newAnalyzerRowID,
				newSurveyorRowID, newRefGasBottleRowID, DB3_ANALYZER_ROW_ID, SURVEY_RUNTIME_IN_SECONDS, SURVEY_ROW_ID, null /*instFiles*/);
		new TestDataGenerator().generateNewCustomerAndSurvey(custSrvInfo, (driverPageAction) -> {
			assertTrue(driverPageAction.verifyCorrectAnalyticsSurveyActiveMessageIsShownOnMap(EMPTY, NOTSET));
			return true;
		});

		// Goto survey view page for current survey and verify indications shown are above min amplitude of location.
		getHomePageAction().open(EMPTY, NOTSET);
		getHomePageAction().clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.get().surveyTag, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.waitForIndicationsToBeShownOnMap(EMPTY, NOTSET);
		Set<Indication> indicationsOnSurveyView = surveyViewPageAction.getIndicationsShownOnPage();

		Log.info(String.format("Indications detected in Survey view = %d", indicationsOnSurveyView.size()));
		indicationsOnSurveyView.forEach(i -> Log.info(i.toString()));

		Float locationMinAmp = manageLocationPageActions.getMinAmplitudeForLocation(newLocationRowID, MinAmplitudeType.Survey_Analytics_Survey);
		Log.info(String.format("Confirm indications shown in Survey view are above MinAmplitude[%f] of the location ", locationMinAmp));
		indicationsOnSurveyView.forEach(i -> assertTrue(Float.valueOf(i.amplitude) > locationMinAmp));
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
		final Integer userDataRowID = 6;

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, userDataRowID);   /* Picarro Admin */

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
		final Integer userDataRowID = 6;

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, userDataRowID);   /* Picarro Admin */

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

	/**
	 * Test Case IDs: TC2411_2412_2413_2414_2417_SimulatorTest_DrivingSurvey_RawDataUpdates
	 * Script: -
	 * 1. Login to driver view as Pic Utility admin
	 * 2. Start PSA and Host Simulator.
	 * 3. Replay Ethane DB3s and apply transformations to Raw data (GPS and Measurements)
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
	@UseDataProvider(value = DriverViewDataProvider.DRIVERVIEW_RAWDATA_UPDATES_TC2411_2412_2413_2414_2417, location = DriverViewDataProvider.class)
	public void TC2411_2412_2413_2414_2417_SimulatorTest_DrivingSurvey_RawDataUpdates(String testCaseId, Integer userDataRowID,
			Integer analyzerDb3DataRowID, Integer surveyRuntimeInSeconds, Integer surveyDataRowID) throws Exception {
		Log.info("TC2411_2412_2413_2414_2417_SimulatorTest_DrivingSurvey_RawDataUpdates");

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, userDataRowID);   /* Customer Driver */

		Log.info("Starting Analyzer...");
		getTestEnvironmentAction().startAnalyzer(EMPTY, analyzerDb3DataRowID); 	// start analyzer.
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);

		String instFilesPath = generateInstructionFiles(testCaseId);

		Log.info("Starting Replay...");
		getTestEnvironmentAction().startReplay(instFilesPath, analyzerDb3DataRowID); 	// start replay db3 file.

		// start survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.startDrivingSurvey(EMPTY, surveyDataRowID));

		// collect indications shown during the survey.
		Set<Indication> indicationsOnDriverView = driverViewPageAction.collectIndicationsDuringSurvey(surveyRuntimeInSeconds);

		// stop survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET));

		// stop simulator and PSA.
		Log.info("Stopping Analyzer...");
		getTestEnvironmentAction().stopAnalyzer(EMPTY, NOTSET);

		if (testCaseId.equalsIgnoreCase("TC2413") || testCaseId.equalsIgnoreCase("TC2417")) {
			Log.info(String.format("Indications detected in Driver view = %d", indicationsOnDriverView.size()));
			indicationsOnDriverView.forEach(i -> Log.info(i.toString()));

			// confirm indication was shown in Driver view.
			assertTrue(indicationsOnDriverView.size() > 0);
		}
	}

	/**
	 * Test Case ID: TC2336_DriverView_AnalyticsSurveyModeHasNoCaptureOrRefGasFeatures
	 * Script:
	 *	- Log into the tablet
	 *	- Click on Mode -> Start Survey
	 *	- Enter a tag and the environmental conditions and select Survey Mode "Analytics"
	 *	- Click on Mode again
	 *	- Click on Display
	 *
	 * Results:
	 *	- User is taken to Driver View
	 *	- Survey details popup appears
	 *	- Car icon turns red, "Analytics Survey Active" appears in bold green font at upper left of map, "Mode: Analytics" appears at top right
	 *	- Only "Stop Survey" appears as an option on Mode menu. "Reference Bottle Measurement" and "Start Capture" do not appear in Mode menu
	 *	- Display menu does not have "Analysis Results" option
	 **/
	@Test
	public void TC2336_DriverView_AnalyticsSurveyModeHasNoCaptureOrRefGasFeatures() throws Exception {
		Log.info("\nRunning TC2336_DriverView_AnalyticsSurveyModeHasNoCaptureOrRefGasFeatures ...");

		final int picAdminUserDataRowID = 6;
		final Integer analyzerDb3DataRowID = 58;
		final Integer surveyDataRowID = 61;

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, picAdminUserDataRowID);   /* Picarro Admin */

		getTestEnvironmentAction().startAnalyzer(EMPTY, analyzerDb3DataRowID); 	// start analyzer. RFADS2004-PICARRO
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
		getTestEnvironmentAction().startReplay(EMPTY, analyzerDb3DataRowID);


		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.startDrivingSurvey(EMPTY, surveyDataRowID);
		getTestEnvironmentAction().idleForSeconds(String.valueOf(2), NOTSET);

		assertTrue(driverViewPageAction.verifyCrossHairIconIsShownOnMap("Red", NOTSET));
		assertTrue(driverViewPageAction.verifyCorrectAnalyticsSurveyActiveMessageIsShownOnMap(EMPTY, NOTSET));

		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.verifyStopDrivingSurveyButtonIsEnabled(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifyRefBottleMeasButtonIsNotVisible(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifyStartIsotopicCaptureButtonIsNotVisible(EMPTY, NOTSET));

		driverViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.verifyDisplaySwitchIsotopicAnalysisButtonIsNotVisible(EMPTY, NOTSET));

		// Stop current simulator.
		getTestEnvironmentAction().stopAnalyzer(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC2343_DriverView_AnalyticsSurveyActiveIsDisplayed
	 * Script:
	 *	- Log into tablet as Picarro Admin
	 *	- Click on Mode
	 *	- Click on Start Analytics Survey
	 *	- Enter the tag and environmental conditions and click Start Survey
	 *
	 * Results:
	 *	- User is taken to Driver View
	 *	- Mode menu appears with Start Survey, Start Analytics Survey and System Shutdown buttons (Start EQ or Start Facility EQ buttons may also be present)
	 *	- Start Survey popup appears
	 *	- "Analytics Survey Active" appears in bold green font at top left of map and "Mode: Analytics" appears in the Survey Information block at top right
	 **/
	@Test
	public void TC2343_DriverView_AnalyticsSurveyActiveIsDisplayed() throws Exception {
		Log.info("\nRunning TC2343_DriverView_AnalyticsSurveyActiveIsDisplayed ...");

		final int picAdminUserDataRowID = 6;
		final Integer analyzerDb3DataRowID = 58;
		final Integer surveyDataRowID = 61;

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, picAdminUserDataRowID);   /* Picarro Admin */

		getTestEnvironmentAction().startAnalyzer(EMPTY, analyzerDb3DataRowID); 	// start analyzer. RFADS2004-PICARRO
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
		getTestEnvironmentAction().startReplay(EMPTY, analyzerDb3DataRowID);


		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);

		assertTrue(driverViewPageAction.verifyStartSurveyButtonIsEnabled(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifySystemShutdownButtonIsEnabled(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifyStartEQSurveyButtonIsEnabled(EMPTY, NOTSET));

		driverViewPageAction.startDrivingSurvey(EMPTY, surveyDataRowID);
		getTestEnvironmentAction().idleForSeconds(String.valueOf(2), NOTSET);

		assertTrue(driverViewPageAction.verifyCorrectAnalyticsSurveyActiveMessageIsShownOnMap(EMPTY, NOTSET));
		String expectedModeValue = SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.get().surveyType;
		assertTrue(driverViewPageAction.verifySurveyInfoModeLabelEquals(expectedModeValue, NOTSET));

		// Stop current simulator.
		getTestEnvironmentAction().stopAnalyzer(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC2345_DriverView_OnlyPeaksAboveSurveyMinAmpAppearInAnalyticsSurveyMode
	 * Script:
	 *	- Log into UI as Picarro Admin
	 *	- Navigate to the Locations page
	 *	- Select a customer location and click Edit
	 *	- Set the Survey Min Amplitude level to a certain level (ex. 0.4)
	 *	- Click OK
	 *	- Log into tablet as Picarro Admin
	 *	- Click on Mode
	 *	- Click on Start Analytics Survey
	 *	- Enter the tag and environmental conditions and click Start Survey
	 *	- Click on Display
	 *	- Turn on all Indications options
	 *	- Drive to areas where indications reliably appear
	 *
	 * Results:
	 *	- User is taken to Driver View
	 *	- Mode menu appears with Start Survey, Start Analytics Survey and System Shutdown buttons (Start EQ or Start Facility EQ buttons may also be present)
	 *	- Start Survey popup appears
	 *	- Display menu appears with Indications option and indications sub-options
	 *	- Only indications with amplitudes above the Survey Min Amplitude level should appear during survey
	 **/
	@Test
	public void TC2345_DriverView_OnlyPeaksAboveSurveyMinAmpAppearInAnalyticsSurveyMode() throws Exception {
		Log.info("\nRunning TC2345_DriverView_OnlyPeaksAboveSurveyMinAmpAppearInAnalyticsSurveyMode ...");

		final String testCaseId = "TC2345";

		final int picAdminUserDataRowID = 6;
		final int DB3_ANALYZER_ROW_ID = 66;	 	/* TestEnvironment datasheet rowID (specifies Analyzer, Replay DB3) */
		final int SURVEY_ROW_ID = 61;	 		/* Survey information  */
		final int SURVEY_RUNTIME_IN_SECONDS = 60; /* Number of seconds to run the survey for. */
		final int newCustomerRowID = 14;
		final int newLocationRowID = 17;
		final int newCustomerUserRowID = 26;
		final int newSurveyorRowID = 25;
		final int newAnalyzerRowID = 23;
		final int newRefGasBottleRowID = 7;

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, picAdminUserDataRowID);   /* Picarro Admin */

		final int numInstFiles = 1;
		String[] instFiles = RegexUtility.split(generateInstructionFiles(testCaseId), RegexUtility.COMMA_SPLIT_REGEX_PATTERN).toArray(new String[numInstFiles]);

		CustomerSurveyInfoEntity custSrvInfo = new CustomerSurveyInfoEntity(newCustomerRowID, newLocationRowID, newCustomerUserRowID, newAnalyzerRowID,
				newSurveyorRowID, newRefGasBottleRowID, DB3_ANALYZER_ROW_ID, SURVEY_RUNTIME_IN_SECONDS, SURVEY_ROW_ID, instFiles);
		new TestDataGenerator().generateNewCustomerAndSurvey(custSrvInfo, (driverPageAction) -> {
			assertTrue(driverPageAction.verifyCorrectAnalyticsSurveyActiveMessageIsShownOnMap(EMPTY, NOTSET));
			Set<Indication> indicationsOnDriverView = driverPageAction.getIndicationsShownOnPage();

			Log.info(String.format("Indications detected in DriverView = %d", indicationsOnDriverView.size()));
			indicationsOnDriverView.forEach(i -> Log.info(i.toString()));

			Float LOCATION_MIN_AMP = 2.0F;
			Log.info(String.format("Confirm indications shown in DriverView are above MinAmplitude[%f] of the location ", LOCATION_MIN_AMP));
			indicationsOnDriverView.forEach(i -> assertTrue(Float.valueOf(i.amplitude) > LOCATION_MIN_AMP));

			return true;
		});
	}

	/**
	 * Test Case ID: TC2355_DriverView_NoFieldNotesOptionForAnalyticsSurveys
	 * Script:
	 *	- Log into the tablet as Picarro Admin
	 *	- Click Mode
	 *	- Click Start Analytics Survey
	 *	- Fill out tag and environmental conditions in popup and click OK
	 *	- Collect several indications
	 *	- Click on one or more of the indications
	 *	- Click on Display
	 *
	 * Results:
	 *	- Upon clicking an indication, a popup should appear with details about that leak. The popup should not have a button for adding Field Notes
	 *	- The Display menu does not have an option for Field Notes
	 **/
	@Test
	public void TC2355_DriverView_NoFieldNotesOptionForAnalyticsSurveys() throws Exception {
		Log.info("\nRunning TC2355_DriverView_NoFieldNotesOptionForAnalyticsSurveys ...");
		final String testCaseId = "TC2365";
		final int picAdminUserDataRowID = 6;

		final int DB3_ANALYZER_ROW_ID = 71;	 	  /* TestEnvironment datasheet rowID (specifies Analyzer, Replay DB3) */
		final int SURVEY_ROW_ID = 61;	 		  /* Survey information  */
		final int SURVEY_RUNTIME_IN_SECONDS = 60; /* Number of seconds to run the survey for. */
		final int newCustomerRowID = 14;
		final int newLocationRowID = 20;
		final int newCustomerUserRowID = 30;
		final int newSurveyorRowID = 28;
		final int newAnalyzerRowID = 26;
		final int newRefGasBottleRowID = 10;

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, picAdminUserDataRowID);   /* Picarro Admin */

		final int numInstFiles = 1;
		String[] instFiles = RegexUtility.split(generateInstructionFiles(testCaseId), RegexUtility.COMMA_SPLIT_REGEX_PATTERN).toArray(new String[numInstFiles]);

		CustomerSurveyInfoEntity custSrvInfo = new CustomerSurveyInfoEntity(newCustomerRowID, newLocationRowID, newCustomerUserRowID, newAnalyzerRowID,
				newSurveyorRowID, newRefGasBottleRowID, DB3_ANALYZER_ROW_ID, SURVEY_RUNTIME_IN_SECONDS, SURVEY_ROW_ID, instFiles);
		new TestDataGenerator().generateNewCustomerAndSurvey(custSrvInfo, (driverPageAction) -> {
			assertTrue(driverPageAction.verifyCorrectAnalyticsSurveyActiveMessageIsShownOnMap(EMPTY, NOTSET));
			Set<Indication> indicationsOnDriverView = driverPageAction.getIndicationsShownOnPage();
			Log.info(String.format("Indications detected in DriverView = %d", indicationsOnDriverView.size()));

			// stop replay. click on indication and verify field notes button is not present.
			testEnvironmentAction.get().stopReplay(EMPTY, NOTSET);
			testEnvironmentAction.get().idleForSeconds("5", NOTSET);
			driverPageAction.clickOnFirstIndicationShownOnMap(EMPTY, NOTSET);
			assertTrue(driverViewPageAction.verifyFeatureInfoPopupAddFieldNotesButtonIsNotVisible(EMPTY, NOTSET));
			return true;
		});
	}

	/**
	 *	Test Case: TC2390_DriverViewLISAsAndIndicationsWithBlueiGPSIndicator
	 *	Description: Driver View - LISAs and Indications with blue iGPS indicator
	 *	Script:
	 *	- Log into the tablet
	 *	- Click on Mode -> Start Survey
	 *	- Enter a survey tag, fill out the environmental conditions and select "Analytics" as the survey mode and click Start Survey
	 *	- Drive for a short distance to produce a blue breadcrumb
	 *	- Disconnect the GPS cable from the GPS antenna
	 *	- Drive in an area where indications reliably appear
	 *	Verifications:
	 *	- Once the cable is disconnected from the GPS antenna, the "GPS" indicator text should change to "iGPS" and the color of the indicator should change from green to blue
	 *	- Breadcrumb should be blue and closely follow the path of the car with minimal straying off the actual course and indications/LISAs should appear as normal
	 * @throws Exception
	**/
	@Test
	public void TC2390_DriverViewLISAsAndIndicationsWithBlueiGPSIndicator() throws Exception {
		Log.info("\nRunning test case - TC2390_DriverViewLISAsAndIndicationsWithBlueiGPSIndicator\n");

		final int userDataRowID = 16;
		final int analyzerDb3DataRowID = 62;
		final int surveyRuntimeInSeconds = 90;
		final int surveyDataRowID = 61;

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, userDataRowID);   /* Customer Driver */

		Log.info("Starting Analyzer...");
		getTestEnvironmentAction().startAnalyzer(EMPTY, analyzerDb3DataRowID); 	// start analyzer.
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);

		String[] ch4Values = {"8.5", "9.5", "10.5", "11.5", "9.5"};
		String[] c2h6Values = {"3.5", "3.5", "3.5", "3.5", "3.5"};
		getTestEnvironmentAction().generateiGPSGoingToBlueWithPeaksDefnForEthaneSurvey(ch4Values, c2h6Values);

		Log.info("Starting Replay...");
		getTestEnvironmentAction().startReplay(EMPTY, analyzerDb3DataRowID); 	// start replay db3 file.

		// start survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.startDrivingSurvey(EMPTY, surveyDataRowID));

		// collect indications shown during the survey.
		Set<Indication> indicationsOnDriverView = driverViewPageAction.collectIndicationsDuringSurvey(surveyRuntimeInSeconds);

		assertTrue(driverViewPageAction.verifyGPSButtonIsBlue(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifyBreadcrumbIsShownOnMap(BreadcrumbColor.Blue.toString(), NOTSET));

		// stop survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET));

		// stop simulator and PSA.
		Log.info("Stopping Analyzer...");
		getTestEnvironmentAction().stopAnalyzer(EMPTY, NOTSET);

		Log.info(String.format("Indications detected in Driver view = %d", indicationsOnDriverView.size()));
		indicationsOnDriverView.forEach(i -> Log.info(i.toString()));

		// confirm indication was shown in Driver view.
		assertTrue(indicationsOnDriverView.size() >= 3);
	}

	/**
	 *	Test Case: TC2392_DriverViewLISAsAndIndicationsWithRediGPSIndicator
	 *	Description: Driver View - LISAs and Indications with red iGPS indicator
	 *	Script:
	 *	- Log into the tablet
	 *	- Click on Mode -> Start Survey
	 *	- Enter a survey tag, fill out the environmental conditions and select "Analytics" as the survey mode and click Start Survey
	 *	- Drive for a short distance to produce a blue breadcrumb
	 *	- Disconnect the GPS cable from the GPS antenna
	 *	- Drive until the iGPS indicator turns from blue to yellow to red
	 *	- Drive in an area where indications reliably appear
	 *	Verifications:
	 *	- Once the cable is disconnected from the GPS antenna, the "GPS" indicator text should change to "iGPS" and the color of the indicator should change from green to blue
	 *	- When the iGPS indicator turns from blue to yellow to red, a warning message should appear at the top left in red font
	 *	- While the iGPS indicator is red, there should be no breadcrumb drawn in any color. FOV and indications/LISAs should also be absent from the survey where the iGPS indicator was red
	**/
	@Test
	public void TC2392_DriverViewLISAsAndIndicationsWithRediGPSIndicator() throws Exception {
		Log.info("\nRunning test case - TC2392_DriverViewLISAsAndIndicationsWithRediGPSIndicator\n");

		final int userDataRowID = 16;
		final int analyzerDb3DataRowID = 62;
		final int surveyRuntimeToGetDegradediGPS = 75;
		final int additionalSurveyRuntimeToGetBadiGPS = 25;
		final int surveyDataRowID = 61;

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, userDataRowID);   /* Customer Driver */

		Log.info("Starting Analyzer...");
		getTestEnvironmentAction().startAnalyzer(EMPTY, analyzerDb3DataRowID); 	// start analyzer.
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);

		String[] ch4Values = {"9.5", "10.5"};
		String[] c2h6Values = {"3.5", "3.5"};
		getTestEnvironmentAction().generateiGPSGoingFromBlueToYellowToRedWithPeaksDefnForEthaneSurvey(ch4Values, c2h6Values);

		Log.info("Starting Replay...");
		getTestEnvironmentAction().startReplay(EMPTY, analyzerDb3DataRowID); 	// start replay db3 file.

		// start survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.startDrivingSurvey(EMPTY, surveyDataRowID));

		// collect indications shown during the survey.
		Set<Indication> indicationsOnDriverView1 = driverViewPageAction.collectIndicationsDuringSurvey(surveyRuntimeToGetDegradediGPS);
		assertTrue(driverViewPageAction.verifyGPSButtonIsYellow(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifyiGPSDriftWarningMessageIsShowing(EMPTY, NOTSET));

		// blue breadcrumbs when iGPS blue or yellow
		assertTrue(driverViewPageAction.verifyBreadcrumbIsShownOnMap(BreadcrumbColor.Blue.toString(), NOTSET));

		Set<Indication> indicationsOnDriverView2 = driverViewPageAction.collectIndicationsDuringSurvey(additionalSurveyRuntimeToGetBadiGPS);
		assertTrue(driverViewPageAction.verifyGPSButtonIsRed(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifyiGPSDriftErrorMessageIsShowing(EMPTY, NOTSET));

		// gray breadcrumbs when iGPS is red
		assertTrue(driverViewPageAction.verifyBreadcrumbIsShownOnMap(BreadcrumbColor.Gray.toString(), NOTSET));

		// stop survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET));

		// stop simulator and PSA.
		Log.info("Stopping Analyzer...");
		getTestEnvironmentAction().stopAnalyzer(EMPTY, NOTSET);

		int totalIndications = indicationsOnDriverView2.size();   // Indications collected in the end will have all the indications shown in UI.
		Log.info(String.format("Indications detected in Driver view = %d", totalIndications));
		indicationsOnDriverView1.forEach(i -> Log.info(i.toString()));
		indicationsOnDriverView2.forEach(i -> Log.info(i.toString()));

		// confirm correct number of indication. Totally 6 peaks generated. 2 generated right after setting iGPS to bad.
		// show 3 or 4 peaks when iGPS is blue or yellow. no peaks when iGPS set to bad.
		assertTrue(totalIndications == 3 || totalIndications == 4);
	}

	/* * Test Case ID: TC2406_CustomerCannotGenerateAnalyticsSurveyInFEDSAnalyzer
	 * Script:
	 * - Customer has Analytics License.
	 * - Customer has P3200 (FEDS) Analyzer up and running.
	 * - login as customer driver go on Driver view on FEDS Analyzer.
	 * - Click on Start survey
	 * Results:
	 * - User should not able to see Analytics survey mode option.
	 * - User can see Standard/Rapid Response/Operator/Manual survey mode according to the license feature, but not Analytics.
	 */
	@Ignore  //Disabling test case is failing due to product defect DE2942.  Tracking with DE2964
	public void TC2406_CustomerCannotGenerateAnalyticsSurveyInFEDSAnalyzer() throws Exception{
		Log.info("\nTestcase - TC2406_CustomerCannotGenerateAnalyticsSurveyInFEDSAnalyzer\n");

		loginPageAction.get().open(EMPTY, NOTSET);
		loginPageAction.get().login(EMPTY, 6);   /* Utility Admin */
		testEnvironmentAction.get().startAnalyzer(EMPTY, 24);

		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
		testEnvironmentAction.get().startReplay(EMPTY, 31); 	// start simulator and replay db3 file.

		// start survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.getDriverViewPage().getSystemShutdownButton().isDisplayed());
		assertTrue(driverViewPageAction.getDriverViewPage().getStartSurveyButton().isDisplayed());

		// click Start Survey button.
		driverViewPageAction.getDriverViewPage().clickStartSurveyButton();
		driverViewPageAction.getDriverViewPage().waitForStartSurveyModalDialogToShow();

		// verify Analytics button is NOT showing.
		assertFalse(WebElementExtender.isElementPresentAndDisplayed(driverViewPageAction.getDriverViewPage().getAnalyticsButton()));

		// Stop current simulator
		testEnvironmentAction.get().stopAnalyzer(EMPTY, NOTSET);
	}

	/* * Test Case ID: TC2382_AdminConfigurationScreenForCustomer_Location_SpecificAnalyticsParameters_SurveyMinAmplitude
	 * Script:
	 * - Log into the UI as a Picarro Admin and navigate to the Locations configuration page for a customer
	 * - Set the Survey Min Amplitude value to 0.035 and click OK
	 * - Log into the tablet for an analyzer belonging to the above customer and begin an Analytics survey
	 * - Collect several peaks of different amplitudes and then stop the survey and log out
	 * - Log into the UI as a Picarro Admin and change the Survey Min Amplitude for the above location to 0.1 (or some other value that is midway between the amplitudes collected in the above survey) and click OK
	 * - Restart the PSA, log back into the tablet and run a new survey over the same route as before.
	 * Results:
	 * - During the first survey, Driver View should display peaks with amplitudes as low as 0.035.
	 * - During the second survey, Driver View should only display peaks with amplitude above 0.1 (or whatever value was entered the second time). Peaks that appeared in the first survey with amplitudes between 0.035 and 0.1 should not be present in the second survey
	 */
	@Test
	public void TC2382_AdminConfigurationScreenForCustomer_Location_SpecificAnalyticsParameters_SurveyMinAmplitude() throws Exception{
		Log.info("\nTestcase - TC2382_AdminConfigurationScreenForCustomer_Location_SpecificAnalyticsParameters_SurveyMinAmplitude\n");

		final int DB3_ANALYZER_ROW_ID = 71;	 	  /* TestEnvironment datasheet rowID (specifies Analyzer, Replay DB3) */
		final int SURVEY_ROW_ID = 61;	 		  /* Survey information  */
		final int SURVEY_RUNTIME_IN_SECONDS = 200; /* Number of seconds to run the survey for. */
		final int newCustomerRowID = 14;
		final int newLocationRowID = 20;
		final int newCustomerUserRowID = 30;
		final int newSurveyorRowID = 28;
		final int newAnalyzerRowID = 26;
		final int newRefGasBottleRowID = 10;

		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().login(EMPTY, 6);   /* Picarro Admin */

		CustomerSurveyInfoEntity custSrvInfo = new CustomerSurveyInfoEntity(newCustomerRowID, newLocationRowID, newCustomerUserRowID, newAnalyzerRowID,
				newSurveyorRowID, newRefGasBottleRowID, DB3_ANALYZER_ROW_ID, SURVEY_RUNTIME_IN_SECONDS, SURVEY_ROW_ID);

		new TestDataGenerator().generateNewCustomerAndSurvey(custSrvInfo, (driverPageAction) -> {
			assertTrue(driverPageAction.verifyCorrectAnalyticsSurveyActiveMessageIsShownOnMap(EMPTY, NOTSET));
			Set<Indication> indicationsOnDriverView1 = driverPageAction.getIndicationsShownOnPage();

			Log.info(String.format("Indications detected in DriverView = %d", indicationsOnDriverView1.size()));
			indicationsOnDriverView1.forEach(i -> Log.info(i.toString()));

			Float LOCATION_MIN_AMP_1 = Float.valueOf(ManageLocationPageActions.workingDataRow.get().surMinAmp);

			Log.info(String.format("Confirm indications shown in DriverView are above MinAmplitude[%f] of the location ", LOCATION_MIN_AMP_1));
			indicationsOnDriverView1.forEach(i -> assertTrue(Float.valueOf(i.amplitude) > LOCATION_MIN_AMP_1));
			return true;
		});

		final String customerName = ManageCustomerPageActions.workingDataRow.get().name;
		final String locationName = ManageLocationPageActions.workingDataRow.get().name;
		final String newUsername = ManageUsersPageActions.workingDataRow.get().username;
		final String newUserPass = ManageUsersPageActions.workingDataRow.get().password;
		final String SurveyMinAmp = "0.1";

		// login as admin and update analytics location properties.
		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		manageLocationPageActions.open(EMPTY, NOTSET);
		manageLocationPageActions.getManageLocationsPage().performSearch(locationName);
		manageLocationPageActions.getManageLocationsPage().editSurveyMinAmplitude(customerName, locationName, SurveyMinAmp);

		// login back as user and create analytics report.
		getLoginPageAction().open(EMPTY, NOTSET);
		getLoginPageAction().getLoginPage().loginNormalAs(ManageUsersPageActions.workingDataRow.get().username, ManageUsersPageActions.workingDataRow.get().password);

		TestEnvironmentActions. generateSurveyForUser(newUsername, newUserPass,
				custSrvInfo.getDb3AnalyzerRowID(), custSrvInfo.getSurveyRowID(), custSrvInfo.getSurveyRuntimeInSeconds(), (driverPageAction) -> {
					assertTrue(driverPageAction.verifyCorrectAnalyticsSurveyActiveMessageIsShownOnMap(EMPTY, NOTSET));
					Set<Indication> indicationsOnDriverView2 = driverPageAction.getIndicationsShownOnPage();

					Log.info(String.format("Indications detected in DriverView = %d", indicationsOnDriverView2.size()));
					indicationsOnDriverView2.forEach(i -> Log.info(i.toString()));

					Float LOCATION_MIN_AMP_2 = Float.valueOf(SurveyMinAmp);

					Log.info(String.format("Confirm indications shown in DriverView are above MinAmplitude[%f] of the location ", LOCATION_MIN_AMP_2));
					indicationsOnDriverView2.forEach(i -> assertTrue(Float.valueOf(i.amplitude) > LOCATION_MIN_AMP_2));
					return true;
				});
	}

	/**
	 * Generates comma separated list of Instruction files for the specified test case.
	 * @param testCaseId - test case identifier.
	 * @return
	 * @throws IOException
	 */
	private String generateInstructionFiles(String testCaseId) throws IOException {
		Log.method("generateInstructionFiles", testCaseId);

		HostSimInstructions measInstructions = new HostSimInstructions(testCaseId);
		if (testCaseId.equalsIgnoreCase("TC2411")) {
			measInstructions.addSelector(Selector.EveryMK, 1000000, 2000)
			.addMeasurementAction(Action.Update, Measurement.Column.GPS_FIT, "6")
			.addMeasurementAction(Action.UpdateFieldBy, Measurement.Column.GPS_ABS_LAT, "0.5")
			.addMeasurementAction(Action.UpdateFieldBy, Measurement.Column.GPS_ABS_LONG, "0.5")
			.addMeasurementAction(Action.Update, Measurement.Column.PeripheralStatus, "524288")   // 2^19
			.addMeasurementAction(Action.InsertPeak, Measurement.Column.CH4, "5.5", "0.16", "0.01", "TC2411_insert_peak_ampl_5_5_sigma_0_1_6_randomizer_1.log");
		} else if (testCaseId.equalsIgnoreCase("TC2412")) {
			measInstructions.addSelector(Selector.EveryMK, 1000000, 2000)
			.addMeasurementAction(Action.Update, Measurement.Column.WIND_N, "numpy.float64(numpy.nan)")
			.addMeasurementAction(Action.Update, Measurement.Column.WIND_E, "numpy.float64(numpy.nan)")
			.addMeasurementAction(Action.Update, Measurement.Column.WIND_DIR_SDEV, "numpy.float64(numpy.nan)")
			.addMeasurementAction(Action.InsertPeak, Measurement.Column.CH4, "7.0", "0.16", "0.01", "TC2412_insert_peak_ampl_7_0_sigma_0_1_6_randomizer_1.log");
		} else if (testCaseId.equalsIgnoreCase("TC2413")) {
			measInstructions.addSelector(Selector.EveryMK, 1000000, 2000)
			.addMeasurementAction(Action.Update, Measurement.Column.C2H6, "numpy.float64(numpy.nan)")
			.addMeasurementAction(Action.InsertPeak, Measurement.Column.CH4, "9.5", "0.16", "0.01", "TC2413_insert_peak_ampl_6_5_sigma_0_1_6_randomizer_1.log");
		} else if (testCaseId.equalsIgnoreCase("TC2414")) {
			measInstructions.addSelector(Selector.EveryMK, 1000000, 2000)
			.addMeasurementAction(Action.Update, Measurement.Column.GPS_FIT, "0")
			.addMeasurementAction(Action.UpdateFieldBy, Measurement.Column.GPS_ABS_LAT, "numpy.float64(numpy.nan)")
			.addMeasurementAction(Action.UpdateFieldBy, Measurement.Column.GPS_ABS_LONG, "numpy.float64(numpy.nan)")
			.addMeasurementAction(Action.InsertPeak, Measurement.Column.CH4, "7.0", "0.16", "0.01", "TC2414_insert_peak_ampl_7_0_sigma_0_1_6_randomizer_1.log");
		} else if (testCaseId.equalsIgnoreCase("TC2417")) {
			measInstructions.addSelector(Selector.WithProbability, 0.95)
			.addMeasurementAction(Action.Update, Measurement.Column.C2H6, "numpy.float64(numpy.nan)")
			.addMeasurementAction(Action.InsertPeak, Measurement.Column.CH4, "12.0", "0.16", "0.01", "TC2417_insert_peak_ampl_6_0_sigma_0_1_6_randomizer_1.log");
		} else if (testCaseId.equalsIgnoreCase("TC2345")) {
			measInstructions.addSelector(Selector.EveryMK, 1000000, 2000)
			.addMeasurementAction(Action.InsertPeak, Measurement.Column.CH4, "5.5", "0.16", "0.01", "TC2345_insert_peak_ampl_5_5_sigma_0_1_6_randomizer_1.log");
		} else if (testCaseId.equalsIgnoreCase("TC2355")) {
			measInstructions.addSelector(Selector.EveryMK, 1000000, 2000)
			.addMeasurementAction(Action.InsertPeak, Measurement.Column.CH4, "5.5", "0.16", "0.01", "TC2355_insert_peak_ampl_5_5_sigma_0_1_6_randomizer_1.log");
		}

		return String.join(",", measInstructions.createFile());
	}
}