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
import common.source.RegexUtility;
import surveyor.dataprovider.DriverViewDataProvider;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.SurveyViewPageActions;
import surveyor.scommon.entities.CustomerSurveyInfoEntity;
import surveyor.scommon.generators.TestDataGenerator;
import surveyor.scommon.source.LoginPage;
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
	@Ignore // Disabled due to product issue: DE2939
	public void TC2365_SurveyView_OnlyPeaksAboveSurveyMinAmplitudeAppearInAnalyticsSurveyMode() throws Exception {
		Log.info("\nTestcase - TC2365_SurveyView_OnlyPeaksAboveSurveyMinAmplitudeAppearInAnalyticsSurveyMode ...\n");

		final String testCaseId = "TC2365";

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

		Float LOCATION_MIN_AMP = 5.0F;
		Log.info(String.format("Confirm indications shown in Survey view are above MinAmplitude[%f] of the location ", LOCATION_MIN_AMP));
		indicationsOnSurveyView.forEach(i -> assertTrue(Float.valueOf(i.amplitude) > LOCATION_MIN_AMP));
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
			.addMeasurementAction(Action.InsertPeak, Measurement.Column.CH4, "6.5", "0.16", "0.01", "TC2413_insert_peak_ampl_6_5_sigma_0_1_6_randomizer_1.log");
		} else if (testCaseId.equalsIgnoreCase("TC2414")) {
			measInstructions.addSelector(Selector.EveryMK, 1000000, 2000)
			.addMeasurementAction(Action.Update, Measurement.Column.GPS_FIT, "0")
			.addMeasurementAction(Action.UpdateFieldBy, Measurement.Column.GPS_ABS_LAT, "numpy.float64(numpy.nan)")
			.addMeasurementAction(Action.UpdateFieldBy, Measurement.Column.GPS_ABS_LONG, "numpy.float64(numpy.nan)")
			.addMeasurementAction(Action.InsertPeak, Measurement.Column.CH4, "7.0", "0.16", "0.01", "TC2414_insert_peak_ampl_7_0_sigma_0_1_6_randomizer_1.log");
		} else if (testCaseId.equalsIgnoreCase("TC2417")) {
			measInstructions.addSelector(Selector.WithProbability, 0.95)
			.addMeasurementAction(Action.Update, Measurement.Column.C2H6, "numpy.float64(numpy.nan)")
			.addMeasurementAction(Action.InsertPeak, Measurement.Column.CH4, "6.0", "0.16", "0.01", "TC2417_insert_peak_ampl_6_0_sigma_0_1_6_randomizer_1.log");
		} else if (testCaseId.equalsIgnoreCase("TC2365")) {
			measInstructions.addSelector(Selector.EveryMK, 1000000, 2000)
			.addMeasurementAction(Action.InsertPeak, Measurement.Column.CH4, "7.5", "0.16", "0.01", "TC2365_insert_peak_ampl_7_5_sigma_0_1_6_randomizer_1.log");
		}
		else if (testCaseId.equalsIgnoreCase("TC2345")) {
			measInstructions.addSelector(Selector.EveryMK, 1000000, 2000)
			.addMeasurementAction(Action.InsertPeak, Measurement.Column.CH4, "5.5", "0.16", "0.01", "TC2345_insert_peak_ampl_5_5_sigma_0_1_6_randomizer_1.log");
		}
		else if (testCaseId.equalsIgnoreCase("TC2355")) {
			measInstructions.addSelector(Selector.EveryMK, 1000000, 2000)
			.addMeasurementAction(Action.InsertPeak, Measurement.Column.CH4, "5.5", "0.16", "0.01", "TC2345_insert_peak_ampl_5_5_sigma_0_1_6_randomizer_1.log");
		}
		return String.join(",", measInstructions.createFile());
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

		final Integer analyzerDb3DataRowID = 58;
		final Integer surveyDataRowID = 61;

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

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

		final String testCaseId = "TC2365";

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
			driverPageAction.clickOnFirstIndicationShownOnMap(EMPTY, NOTSET);
			assertTrue(driverPageAction.verifyFieldNotesDialogIsNotShown(EMPTY, NOTSET));
			return true;
		});
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
}