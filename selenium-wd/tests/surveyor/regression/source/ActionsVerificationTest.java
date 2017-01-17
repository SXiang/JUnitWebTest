package surveyor.regression.source;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.dataaccess.source.Customer;
import surveyor.dbseed.source.DbSeedExecutor;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.BaseActions;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageAnalyzerPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.ManageLocationPageActions;
import surveyor.scommon.actions.ManageRefGasBottlesPageActions;
import surveyor.scommon.actions.ManageSurveyorPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorConstants;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class ActionsVerificationTest extends SurveyorBaseTest {
	private static final String SURVEYOR_NAME = "SimAuto-Surveyor1";
	private static final String ANALYZER_SERIAL_NUMBER = "SimAuto-Analyzer1";

	private LoginPageActions loginPageAction;
	private DriverViewPageActions driverViewPageAction;
	private TestEnvironmentActions testEnvironmentAction;

	private static ManageCustomerPageActions manageCustomerPageAction;
	private static ManageUsersPageActions manageUsersPageAction;
	private static ManageLocationPageActions manageLocationPageAction;
	private static ManageAnalyzerPageActions manageAnalyzerPageAction;
	private static ManageSurveyorPageActions manageSurveyorPageAction;
	private static ManageRefGasBottlesPageActions manageRefGasBottlesPageAction;

	private static final String EMPTY = BaseActions.EMPTY;
	private static final Integer NOTSET = BaseActions.NOTSET;

	@BeforeClass
	public static void BeforeClass()	{
		TestSetup testSetup = new TestSetup(true /* initialize */);
		String rootPath;
		try {
			rootPath = TestSetup.getRootPath();
			testSetup.loadTestProperties(rootPath);
		} catch (IOException e) {
			Log.error(e.getMessage());
		}
		testSetup.initializeDBProperties();
		TestContext.INSTANCE.setTestSetup(testSetup);

		manageCustomerPageAction = ActionBuilder.createManageCustomerPageAction();
		manageUsersPageAction = ActionBuilder.createManageUsersPageAction();
		manageLocationPageAction = ActionBuilder.createManageLocationPageAction();
		manageAnalyzerPageAction = ActionBuilder.createManageAnalyzerPageAction();
		manageSurveyorPageAction = ActionBuilder.createManageSurveyorPageAction();
		manageRefGasBottlesPageAction = ActionBuilder.createManageRefGasBottlePageAction();
	}

	public ActionsVerificationTest() {
		loginPageAction = ActionBuilder.createLoginPageAction();
		driverViewPageAction = ActionBuilder.createDriverViewPageAction();
		testEnvironmentAction = ActionBuilder.createTestEnvironmentAction();
	}

	@Test
	public void TC_SimulatorTest_DriverViewInstrumentReady() {
		try {
			final int analyzerDb3DataRowID = 1;
			final int surveyDataRowID = 3;

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(SurveyorConstants.PICDFADMIN + ":" + SurveyorConstants.PICADMINPSWD, NOTSET);
			testEnvironmentAction.startAnalyzer(EMPTY, analyzerDb3DataRowID);
			driverViewPageAction.open(EMPTY,NOTSET);
			driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
			testEnvironmentAction.startReplay(EMPTY, analyzerDb3DataRowID); 	// start replay db3 file.
			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);

			assertTrue(driverViewPageAction.verifyPositionButtonIsGreen(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyStartSurveyButtonIsEnabled(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifySystemShutdownButtonIsEnabled(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyStartIsotopicCaptureButtonIsEnabled(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyRefBottleMeasButtonIsEnabled(EMPTY,NOTSET));

			driverViewPageAction.startDrivingSurvey(EMPTY, surveyDataRowID);

			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);
			assertTrue(driverViewPageAction.verifyStartIsotopicCaptureButtonIsEnabled(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyRefBottleMeasButtonIsEnabled(EMPTY,NOTSET));

			driverViewPageAction.clickOnDisplayButton(EMPTY,NOTSET);

			driverViewPageAction.verifyDisplaySwitchIsOn("EightHourHistory",NOTSET);
			driverViewPageAction.verifyDisplaySwitchIsOn("ConcentrationChart",NOTSET);
			driverViewPageAction.verifyDisplaySwitchIsOn("FOVs",NOTSET);
			driverViewPageAction.verifyDisplaySwitchIsOn("Indications",NOTSET);
			driverViewPageAction.verifyDisplaySwitchIsOn("IsotopicAnalysis",NOTSET);
			driverViewPageAction.verifyDisplaySwitchIsOn("Lisas",NOTSET);
			driverViewPageAction.verifyDisplaySwitchIsOn("Notes",NOTSET);
			driverViewPageAction.verifyDisplaySwitchIsOn("WindRose",NOTSET);

			driverViewPageAction.clickOnMapButton(EMPTY,NOTSET);

			assertTrue(driverViewPageAction.verifyMapSwitchOn("Satellite",NOTSET));
			assertTrue(driverViewPageAction.verifyMapSwitchOff("Map",NOTSET));

			driverViewPageAction.clickOnGisButton(EMPTY,NOTSET);

			assertTrue(driverViewPageAction.verifyGisSwitchIsOff("BoundariesDistrict",NOTSET));
			assertTrue(driverViewPageAction.verifyGisSwitchIsOff("BoundariesDistrictPlat",NOTSET));
			assertTrue(driverViewPageAction.verifyGisSwitchIsOff("MaterialTypeCastIron",NOTSET));
			assertTrue(driverViewPageAction.verifyGisSwitchIsOff("MaterialTypeCopper",NOTSET));
			assertTrue(driverViewPageAction.verifyGisSwitchIsOff("MaterialTypeOtherPlastic",NOTSET));
			assertTrue(driverViewPageAction.verifyGisSwitchIsOff("MaterialTypePEPlastic",NOTSET));
			assertTrue(driverViewPageAction.verifyGisSwitchIsOff("MaterialTypeProtectedSteel",NOTSET));
			assertTrue(driverViewPageAction.verifyGisSwitchIsOff("MaterialTypeUnprotectedSteel",NOTSET));
			assertTrue(driverViewPageAction.verifyGisSwitchIsOff("UseAllBoundaries",NOTSET));
			assertTrue(driverViewPageAction.verifyGisSwitchIsOff("UseAllPipes",NOTSET));

			assertTrue(driverViewPageAction.verifyStatusButtonIsGreen(EMPTY,NOTSET));

			driverViewPageAction.clickOnStatusButton(EMPTY,NOTSET);

			assertTrue(driverViewPageAction.verifyStatusButtonIsExpanded(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyPressureButtonIsGreen(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyHBTempButtonIsGreen(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyWBTempButtonIsGreen(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyFlowButtonIsGreen(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyGPSButtonIsGreen(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyAnemometerButtonIsGreen(EMPTY,NOTSET));

		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

	@Test
	public void TC_SimulatorTest_DriverViewStartDrivingSurvey() {
		try {
			final int analyzerDb3DataRowID = 3;
			final int surveyDataRowID = 3;

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(SurveyorConstants.PICDFADMIN + ":" + SurveyorConstants.PICADMINPSWD, NOTSET);
			testEnvironmentAction.startAnalyzer(EMPTY, analyzerDb3DataRowID);
			driverViewPageAction.open(EMPTY,NOTSET);
			driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
			testEnvironmentAction.startReplay(EMPTY, analyzerDb3DataRowID); 	// start replay db3 file.
			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);

			String surveyTag = getTestSetup().getFixedSizePseudoRandomString(13) + "_TEST";
			String surveyArg = String.format("%s,%s,%s,%s,%s,%s,%s",
					surveyTag, "Day", "Overcast", "Calm", "LessThan50", "Standard", "");
			driverViewPageAction.startDrivingSurvey(surveyArg, surveyDataRowID);
			driverViewPageAction.clickOnHeaderInfoBox(EMPTY,NOTSET);

			assertTrue(driverViewPageAction.verifySurveyInfoModeLabelEquals("Mode: Standard",NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoSurveyStatusLabelEquals("Survey Active",NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoStabilityClassLabelEquals("Stability Class: C",NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoTimeElapsedLabelStartsWith("Elapsed: 00:",NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoTimeRemainingLabelStartsWith("Remaining: 0",NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoSurveyorLabelEquals("Surveyor: " + SURVEYOR_NAME + " - " + ANALYZER_SERIAL_NUMBER,NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoZoomLevelLabelEquals("Zoom Level: 19",NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoTagLabelEquals(surveyTag, NOTSET));

			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);

			assertTrue(driverViewPageAction.verifyStopDrivingSurveyButtonIsEnabled(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyStartIsotopicCaptureButtonIsEnabled(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyRefBottleMeasButtonIsEnabled(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifySystemShutdownButtonIsEnabled(EMPTY,NOTSET));

		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

	@Test
	public void TC_SimulatorTest_DriverViewStartDrivingSurvey_ManualSurvey() {
		try {
			final int analyzerDb3DataRowID = 3;
			final int surveyDataRowID = 52;


			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(SurveyorConstants.PICDFADMIN + ":" + SurveyorConstants.PICADMINPSWD, NOTSET);

			testEnvironmentAction.startAnalyzer(EMPTY, analyzerDb3DataRowID);
			driverViewPageAction.open(EMPTY,NOTSET);
			driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
			testEnvironmentAction.startReplay(EMPTY, analyzerDb3DataRowID); 	// start replay db3 file.
			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);

			driverViewPageAction.startDrivingSurvey(EMPTY, surveyDataRowID);
			driverViewPageAction.clickOnHeaderInfoBox(EMPTY,NOTSET);

			assertTrue(driverViewPageAction.verifySurveyInfoModeLabelEquals("Mode: Manual",NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoSurveyStatusLabelEquals("Survey Active",NOTSET));

			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);

			assertTrue(driverViewPageAction.verifyStopDrivingSurveyButtonIsEnabled(EMPTY,NOTSET));

			driverViewPageAction.stopDrivingSurvey(EMPTY,NOTSET);

		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

	@Test
	public void TC_SimulatorTest_DriverViewStopDrivingSurvey() {
		try {
			final int analyzerDb3DataRowID = 3;
			final int surveyDataRowID = 3;

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(SurveyorConstants.PICDFADMIN + ":" + SurveyorConstants.PICADMINPSWD, NOTSET);
			testEnvironmentAction.startAnalyzer(EMPTY, analyzerDb3DataRowID);
			driverViewPageAction.open(EMPTY,NOTSET);
			driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
			testEnvironmentAction.startReplay(EMPTY, analyzerDb3DataRowID); 	// start replay db3 file.
			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);

			assertTrue(driverViewPageAction.verifyStartSurveyButtonIsEnabled(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifySystemShutdownButtonIsEnabled(EMPTY,NOTSET));

			String surveyTag = getTestSetup().getFixedSizePseudoRandomString(13) + "_TEST";
			String surveyArg = String.format("%s,%s,%s,%s,%s,%s",
					surveyTag, "Night", "Overcast", "Calm", "MoreThan50", "Standard");

			driverViewPageAction.startDrivingSurvey(surveyArg, surveyDataRowID);

			driverViewPageAction.clickOnMapButton(EMPTY,NOTSET);
			driverViewPageAction.turnOnMapView(EMPTY,NOTSET);

			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);

			assertTrue(driverViewPageAction.verifyStopDrivingSurveyButtonIsEnabled(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyStartIsotopicCaptureButtonIsEnabled(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyRefBottleMeasButtonIsEnabled(EMPTY,NOTSET));

			driverViewPageAction.stopDrivingSurvey(EMPTY,NOTSET);

			driverViewPageAction.clickOnHeaderInfoBox(EMPTY,NOTSET);

			assertTrue(driverViewPageAction.verifySurveyInfoSurveyStatusLabelEquals("Survey Inactive",NOTSET));

		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

	@Test
	public void TC_SimulatorTest_TC1147_DriverViewSurveyVerification() {
		try {
			final int analyzerDb3DataRowID = 3;
			final int surveyDataRowID = 3;

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(SurveyorConstants.PICDFADMIN + ":" + SurveyorConstants.PICADMINPSWD, NOTSET);
			testEnvironmentAction.startAnalyzer(EMPTY, analyzerDb3DataRowID);
			driverViewPageAction.open(EMPTY,NOTSET);
			driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
			testEnvironmentAction.startReplay(EMPTY, analyzerDb3DataRowID); 	// start replay db3 file.
			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);
			driverViewPageAction.startDrivingSurvey(EMPTY, surveyDataRowID);
			driverViewPageAction.clickOnMapButton(EMPTY,NOTSET);
			driverViewPageAction.turnOnMapView(EMPTY,NOTSET);

			driverViewPageAction.clickOnDisplayButton(EMPTY,NOTSET);
			driverViewPageAction.turnOnLisas(EMPTY,NOTSET);
			driverViewPageAction.turnOnNotes(EMPTY,NOTSET);
			driverViewPageAction.turnOffEightHourHistory(EMPTY,NOTSET);
			driverViewPageAction.turnOffConcentrationChart(EMPTY,NOTSET);
			driverViewPageAction.turnOffFOVs(EMPTY,NOTSET);
			driverViewPageAction.turnOffIndications(EMPTY,NOTSET);
			driverViewPageAction.turnOffIsotopicAnalysis(EMPTY,NOTSET);
			driverViewPageAction.turnOffWindRose(EMPTY,NOTSET);

			driverViewPageAction.clickOnGisButton(EMPTY,NOTSET);
			driverViewPageAction.turnOffBigBoundary(EMPTY,NOTSET);
			driverViewPageAction.turnOffSmallBoundary(EMPTY,NOTSET);
			driverViewPageAction.turnOffMaterialTypeCopper (EMPTY,NOTSET);
			driverViewPageAction.turnOffMaterialTypeCastIron(EMPTY,NOTSET);
			driverViewPageAction.turnOffMaterialTypeOtherPlastic(EMPTY,NOTSET);
			driverViewPageAction.turnOffMaterialTypePEPlastic(EMPTY,NOTSET);
			driverViewPageAction.turnOffMaterialTypeProtectedSteel(EMPTY,NOTSET);
			driverViewPageAction.turnOffMaterialTypeUnprotectedSteel(EMPTY,NOTSET);
			driverViewPageAction.turnOffUseAllBoundaries(EMPTY,NOTSET);
			driverViewPageAction.turnOnUseAllPipes(EMPTY,NOTSET);

			driverViewPageAction.clickOnPositionButton(EMPTY,NOTSET);

			assertTrue(driverViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY,NOTSET));
			assertTrue(driverViewPageAction.verifyFOVIsNotShownOnMap(EMPTY,NOTSET));

			driverViewPageAction.clickOnHeaderInfoBox(EMPTY,NOTSET);
			assertTrue(driverViewPageAction.verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.get().surveyTag,NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoModeLabelEquals("Mode: " + DriverViewPageActions.workingDataRow.get().surveyType,NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoTimeElapsedLabelStartsWith("Elapsed: 00:",NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoSurveyStatusLabelEquals("Survey Active",NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoDriverLabelEquals("Driver: " + SurveyorConstants.PICDFADMIN,NOTSET));
			assertTrue(driverViewPageAction.verifySurveyInfoTimeRemainingLabelStartsWith("Remaining: 0",NOTSET));
			driverViewPageAction.verifySurveyInfoZoomLevelLabelEquals("Zoom Level: 19",NOTSET);
			driverViewPageAction.verifySurveyInfoSurveyorLabelEquals("Surveyor: " + SURVEYOR_NAME + " - " + ANALYZER_SERIAL_NUMBER,NOTSET);

			// Analyzer info shows up in Observer view. NA here.
			//driverViewPageAction.verifySurveyInfoAnalyzerLabelEquals("Analyzer: ",NOTSET);

			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);
			driverViewPageAction.stopDrivingSurvey(EMPTY,NOTSET);
		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

	/**
	 * Unit test for TestEnvironmentActions.generateSurveyForUser() with existing user.
	 * @throws Exception
	 */
	@Test
	public void Test_generateSurveyForExistingUser() throws Exception {
		Log.info("\nRunning Test_generateSurveyForExistingUser ...");

		final int LOGIN_USER_ROW_ID = 6;	 	/* LoginRowID. AutomationAdmin */
		final int DB3_ANALYZER_ROW_ID = 9;	 	/* Analyzer3/Surveyor3. Replay db3 file rowID */
		final int SURVEY_ROW_ID = 4;	 		/* Survey information rowID */
		final int SURVEY_RUNTIME_IN_SECONDS = 60; /* Number of seconds to run the survey for. */

		// Existing user with static username/password. Use overload with LoginUserRowID.
		TestEnvironmentActions.generateSurveyForUser(LOGIN_USER_ROW_ID,
				DB3_ANALYZER_ROW_ID, SURVEY_ROW_ID, SURVEY_RUNTIME_IN_SECONDS);
	}

	/**
	 * Unit test for TestEnvironmentActions.generateSurveyForUser() with new customer user.
	 * @throws Exception
	 */
	@Test
	public void Test_generateSurveyForNewCustomerUser() throws Exception {
		Log.info("\nRunning Test_generateSurveyForNewCustomerUser ...");

		final int LOGIN_USER_ROW_ID = 6;	 	/* LoginRowID. AutomationAdmin */
		final int DB3_ANALYZER_ROW_ID = 31;	 	/* TestEnvironment datasheet rowID (specifies Analyzer, Replay DB3) */
		final int SURVEY_ROW_ID = 4;	 		/* Survey information  */

		final int SURVEY_RUNTIME_IN_SECONDS = 60; /* Number of seconds to run the survey for. */

		final int newCustomerRowID = 7;
		final int newLocationRowID = 4;
		final int newCustomerUserRowID = 12;
		final int newSurveyorRowID = 3;
		final int newAnalyzerRowID = 3;
		final int newRefGasBottleRowID = 1;

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, LOGIN_USER_ROW_ID);

		// Create new customer.
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.createNewCustomer(EMPTY, newCustomerRowID /*customerRowID*/);

		// Create new location.
		manageLocationPageAction.open(EMPTY, NOTSET);
		manageLocationPageAction.createNewLocation(EMPTY, newLocationRowID /*locationRowID*/);

		// Create new user.
		manageUsersPageAction.open(EMPTY, NOTSET);
		manageUsersPageAction.createNewCustomerUser(EMPTY, newCustomerUserRowID /*userRowID*/);

		// Create new surveyor.
		manageSurveyorPageAction.open(EMPTY, NOTSET);
		manageSurveyorPageAction.createNewSurveyor(EMPTY, newSurveyorRowID /*surveyorRowID*/);

		// Create new analyzer.
		manageAnalyzerPageAction.open(EMPTY, NOTSET);
		manageAnalyzerPageAction.createNewAnalyzer(EMPTY, newAnalyzerRowID /*analyzerRowID*/);

		// Create new ref gas bottle.
		manageRefGasBottlesPageAction.open(EMPTY, NOTSET);
		manageRefGasBottlesPageAction.createNewRefGasBottle(EMPTY, newRefGasBottleRowID /*refGasBottleRowID*/);

		// Email ID for the new created user was generated dynamically in this case by using 'GenerateRandomEmail(20)' function.
		// For such cases, use the overload with username and password for generateSurveyForUser().
		String newUsername = ManageUsersPageActions.workingDataRow.get().username;
		String newUserPass = ManageUsersPageActions.workingDataRow.get().password;
		TestEnvironmentActions.generateSurveyForUser(newUsername, newUserPass,
				DB3_ANALYZER_ROW_ID, SURVEY_ROW_ID, SURVEY_RUNTIME_IN_SECONDS);
	}

	/**
	 * Unit test for TestEnvironmentActions.generateSurveyForUser() with new Picarro user.
	 * @throws Exception
	 */
	@Test
	public void Test_generateSurveyForNewPicarroUser() throws Exception {
		Log.info("\nRunning Test_generateSurveyForNewCustomerUser ...");

		final int LOGIN_USER_ROW_ID = 6;	 	/* LoginRowID. AutomationAdmin */
		final int DB3_ANALYZER_ROW_ID = 9;	 	/* Analyzer3/Surveyor3. Replay db3 file rowID */
		final int SURVEY_ROW_ID = 4;	 		/* Survey information  */

		final int SURVEY_RUNTIME_IN_SECONDS = 60; /* Number of seconds to run the survey for. */

		final int newPicarroUserRowID = 14;

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, LOGIN_USER_ROW_ID);

		// Create new Picarro user.
		manageUsersPageAction.open(EMPTY, NOTSET);
		manageUsersPageAction.createNewPicarroUser(EMPTY, newPicarroUserRowID /*userRowID*/);

		// Email ID for the new created user was generated dynamically in this case by using 'GenerateRandomEmail(20)' function.
		// For such cases, use the overload with username and password for generateSurveyForUser().
		String newUsername = ManageUsersPageActions.workingDataRow.get().username;
		String newUserPass = ManageUsersPageActions.workingDataRow.get().password;
		TestEnvironmentActions.generateSurveyForUser(newUsername, newUserPass,
				DB3_ANALYZER_ROW_ID, SURVEY_ROW_ID, SURVEY_RUNTIME_IN_SECONDS);
	}

	/**
	 * Unit test for DBSeedExecutor.executeGisSeed() with new customer user.
	 * @throws Exception
	 */
	@Test
	public void Test_executeGisSeedForNewCustomer() throws Exception {
		Log.info("\nRunning Test_executeGisSeedForNewCustomer ...");

		final int LOGIN_USER_ROW_ID = 6;	 	/* LoginRowID. AutomationAdmin */
		final int newCustomerRowID = 7;

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, LOGIN_USER_ROW_ID);

		// Create new customer/location/user.
		manageCustomerPageAction.open(EMPTY, NOTSET);
		manageCustomerPageAction.createNewCustomer(EMPTY, newCustomerRowID /*customerRowID*/);

		Customer customer = Customer.getCustomer(ManageCustomerPageActions.workingDataRow.get().name);
		DbSeedExecutor.executeGisSeed(customer.getId());
	}}
