package surveyor.regression.source;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.CUSUSERROLEDR;
import static surveyor.scommon.source.SurveyorConstants.REGBASEUSERNAME;
import static surveyor.scommon.source.SurveyorConstants.SQACUS;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;

import java.io.IOException;
import java.net.UnknownHostException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import common.source.BrowserCommands;
import common.source.DateUtility;
import common.source.ExceptionUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.BaseMapViewPage.DisplaySwitchType;
import surveyor.scommon.source.BaseMapViewPage.GisSwitchType;
import surveyor.scommon.source.BaseMapViewPage.MapSwitchType;
import surveyor.scommon.source.DriverViewPage.CloudCover;
import surveyor.scommon.source.DriverViewPage.SolarRadiation;
import surveyor.scommon.source.DriverViewPage.SurveyTime;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.DriverViewPage.Wind;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageUsersPage;
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
public class DriverViewPageTest extends BaseMapViewTest {

	protected DriverViewPageActions driverViewPageAction;
	protected static DriverViewPage driverViewPage;
	protected static ManageCustomersPage manageCustomersPage = null;
	protected static ManageUsersPage manageUsersPage = null;
	
	public DriverViewPageTest() throws IOException {
		super();
	}

	@Before
	public void beforeTestMethod() {
		try {
			initializePageObjects();
			driverViewPageAction = new DriverViewPageActions(driver, baseURL,testSetup);
			TestSetup.restartAnalyzer();
		} catch (UnknownHostException e) {
			Log.info(ExceptionUtility.getStackTraceString(e));
		} catch (IOException e) {
			Log.info(ExceptionUtility.getStackTraceString(e));
		}
	}

	private void initializePageObjects() {
		driverViewPage = new DriverViewPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver, driverViewPage);

		// Additional page objects.
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
		
		manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
	}

	/**
	 * Test Case ID: TC1093_SimulatorTest_VerifyInstrumentWarmUp_PicAdmin
	 * Script: -  	
	 * 	1. Log in to driver view 
	 * 	2. Click on Mode button 
	 * 	3. Click on Display button 
	 * 	4. Click on Map button 
	 * 	5. Click on GIS button 
	 * 	6. Click on Status button
	 * Results: - 
	 * 1. Position button is by default selected and car icon is displayed 
	 * 2. Start Driving Survey, Start Isotopic Capture, Reference Bottle Measurement buttons are not present. System Shutdown button is present and is in enabled state 
	 * 3. All the Display options like 8 Hour History, Indications, LISA, FoV, Concentration Chart, WindRose, Field Notes and Isotopic Analysis are ON 
	 * 4. Satellite View is by default ON and Map view is OFF 
	 * 5. All Asset types and boundaries level are OFF 
	 * 6. Status is red and on expanding flow, temp gauges are also red
	 * @throws Exception 
	 */
	@Ignore
	public void TC1093_SimulatorTest_VerifyInstrumentWarmUp_PicAdmin() throws Exception {
		Log.info("Running TC1093_SimulatorTest_VerifyInstrumentWarmUp_PicAdmin");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		testEnvironmentAction.startAnalyzer(EMPTY, 2);  // start Analyzer instr_ready.defn

		driverViewPage.open();
		driverViewPage.waitForPageLoad();
		driverViewPage.waitForConnectionComplete();

		testEnvironmentAction.startReplay(EMPTY, 2);  // start Analyzer instr_ready.defn

		Log.info("Clicking on MODE button");
		driverViewPage.clickModeButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		// Verify 1.
		assertTrue(driverViewPage.isPositionButtonGreen());
		
		// "Car icon not displayed." <-- [Check Feasibility in Open Layer]

		// Verify 2.
		assertTrue(!driverViewPage.getStartSurveyButton().isDisplayed());
		assertTrue(!driverViewPage.getStartIsotopicCaptureButton().isDisplayed());
		assertTrue(!driverViewPage.getRefBottleMeasButton().isDisplayed());
		
		assertTrue(driverViewPage.getSystemShutdownButton().isDisplayed());
		assertTrue(driverViewPage.isSystemShutdownButtonEnabled());
		
		Log.info("Clicking on DISPLAY button");
		driverViewPage.clickDisplayButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		// Verify 3.
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.EightHourHistory));
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.ConcentrationChart));
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.FOVs));
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.Indications));
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.IsotopicAnalysis));
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.Lisas));
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.Notes));
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.WindRose));
		
		Log.info("Clicking on MAP button");
		driverViewPage.clickMapButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		// Verify 4.
		assertTrue(driverViewPage.isMapSwitchOn(MapSwitchType.Satellite));
		assertTrue(driverViewPage.isMapSwitchOff(MapSwitchType.Map));

		Log.info("Clicking on GIS button");
		driverViewPage.clickGisButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		// Verify 5.
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.BigBoundary));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.SmallBoundary));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.MaterialTypeCastIron));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.MaterialTypeCopper));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.MaterialTypeOtherPlastic));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.MaterialTypePEPlastic));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.MaterialTypeProtectedSteel));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.MaterialTypeUnprotectedSteel));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.UseAllBoundaries));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.UseAllPipes));
		
		Log.info("Clicking on STATUS button");
		driverViewPage.clickStatusButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		// Verify 6.
		assertTrue(driverViewPage.isStatusButtonRed());
		assertTrue(driverViewPage.isPressureButtonRed());
		assertTrue(driverViewPage.isHBTempButtonRed());
		assertTrue(driverViewPage.isWBTempButtonRed());
		assertTrue(driverViewPage.isFlowButtonRed());
		assertTrue(driverViewPage.isGPSButtonRed());
		assertTrue(driverViewPage.isAnemometerButtonRed());
	}
	
	/**
	 * Test Case ID: TC1094_SimulatorTest_VerifyInstrumentReady_PicAdmin
	 * Script: -  	
	 * 1. Log in to driver view 
	 * 2. Click on Mode button 
	 * 3. Click on Display button 
	 * 4. Click on Map button 
	 * 5. Click on GIS button 
	 * 6. Click on Status button
	 * Results: - 
	 * 1. Position button is by default selected and car icon is displayed in grey color. Breadcrumb will be displayed in grey if user is driving the car 
	 * 2. Start Driving Survey and System Shutdown buttons are enabled. Start Isotopic Capture, Reference Bottle Measurement buttons are  not present 
 	 * 3. All the Display options like 8 Hour History, Breadcrumb, Indications, LISA, FoV, Concentration Chart, WindRose, Field Notes and Isotopic Analysis are ON 
	 * 4. Satellite View is by default ON and Map view is OFF 
	 * 5. All Asset types and boundaries level are OFF 
	 * 6. Status is green and all the gauges present on expanding are green
	 * @throws Exception 
	 */
	@Test
	public void TC1094_SimulatorTest_VerifyInstrumentReady_PicAdmin() throws Exception {
		Log.info("Running TC1094_SimulatorTest_VerifyInstrumentReady_PicAdmin");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		testEnvironmentAction.startAnalyzer(EMPTY, 1);  // start Analyzer instr_ready.defn
		
		driverViewPage.open();
		driverViewPage.waitForPageLoad();
		driverViewPage.waitForConnectionComplete();

		testEnvironmentAction.startReplay(EMPTY, 1);	// start replay instr_ready.defn

		Log.info("Clicking on MODE button");
		driverViewPage.clickModeButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		// Verify 1.
		assertTrue(driverViewPage.isPositionButtonGreen());

		// "Car icon is displayed in grey color." <-- [Check Feasibility in Open Layer]

		// Verify 2.
		assertTrue(driverViewPage.getStartSurveyButton().isDisplayed());
		assertTrue(driverViewPage.isStartSurveyButtonEnabled());
		assertTrue(driverViewPage.getSystemShutdownButton().isDisplayed());
		assertTrue(driverViewPage.isSystemShutdownButtonEnabled());

		// Verify Isotopic Capture and Ref Bottle Measurement buttons are NOT displayed.
		assertTrue(!driverViewPage.getStartIsotopicCaptureButton().isDisplayed());
		assertTrue(!driverViewPage.getRefBottleMeasButton().isDisplayed());
		
		// Start Driving Survey.
		String tag = testSetup.getFixedSizePseudoRandomString(10) + "_TC1094";
		driverViewPage.startDrivingSurvey(tag, SurveyTime.Day, SolarRadiation.Moderate, Wind.Calm, CloudCover.LessThan50, SurveyType.Standard);

		// Verify Isotopic Capture and Ref Bottle Measurement buttons ARE displayed.
		driverViewPage.clickModeButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		assertTrue(driverViewPage.getStartIsotopicCaptureButton().isDisplayed());
		assertTrue(driverViewPage.getRefBottleMeasButton().isDisplayed());

		Log.info("Clicking on DISPLAY button");
		driverViewPage.clickDisplayButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		// Verify 3.
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.EightHourHistory));
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.ConcentrationChart));
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.FOVs));
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.Indications));
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.IsotopicAnalysis));
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.Lisas));
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.Notes));
		assertTrue(driverViewPage.isDisplaySwitchOn(DisplaySwitchType.WindRose));
		
		Log.info("Clicking on MAP button");
		driverViewPage.clickMapButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		// Verify 4.
		assertTrue(driverViewPage.isMapSwitchOn(MapSwitchType.Satellite));
		assertTrue(driverViewPage.isMapSwitchOff(MapSwitchType.Map));

		Log.info("Clicking on GIS button");
		driverViewPage.clickGisButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		// Verify 5.
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.BigBoundary));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.SmallBoundary));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.MaterialTypeCastIron));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.MaterialTypeCopper));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.MaterialTypeOtherPlastic));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.MaterialTypePEPlastic));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.MaterialTypeProtectedSteel));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.MaterialTypeUnprotectedSteel));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.UseAllBoundaries));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.UseAllPipes));

		// Verify 6.
		assertTrue(driverViewPage.isStatusButtonGreen());

		Log.info("Clicking on STATUS button to expand gauges");
		driverViewPage.clickStatusButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		// Verify 6.
		// TODO: Check may not be accurate anymore.
		//assertTrue(driverViewPage.isStatusButtonOpen());
		assertTrue(driverViewPage.isPressureButtonGreen());
		assertTrue(driverViewPage.isHBTempButtonGreen());
		assertTrue(driverViewPage.isWBTempButtonGreen());
		assertTrue(driverViewPage.isFlowButtonGreen());
		assertTrue(driverViewPage.isGPSButtonGreen());
		assertTrue(driverViewPage.isAnemometerButtonGreen());
	}
	
	/**
	 * Test Case ID: TC1097_SimulatorTest_StartDrivingSurvey_PicAdmin
	 * Script: -  	
	 * 1. Login to driver view 
	 * 2. Click on Mode -> Start Driving Survey 
	 * 3. Provide survey tag, select  Survey Time: Day Solar Radiation: Overcast Wind: Calm Survey Type: Standard 
	 * 4. Click on Start Survey button 
	 * 5. Click on Map and turn Map View ON 
	 * 6. Click on Mode button
	 * Results: - 
	 * 1. Survey conditions screen is present. Start Survey button is not present 
	 * 2. Once all the values are selected, "Start Survey" button should be present and enabled 
	 * 3. Survey Information is displayed in map view - Tag, Mode, Time, Survey Active, Driver Info, Elapsed time, Remaining Time, Zoom level, Surveyor and analyzer info 
	 * 4. Stability Class value displayed is C
	 * 5. Time limit warning should not appear before 7 hours have elapsed
	 * 6. Car icon is displayed in red color. Breadcrumb will  be displayed in blue color 
	 * 7. Stop Driving Survey, Start Isotopic Capture, Reference Bottle Measurement buttons are enabled and System Shutdown button is not present
	 */
	@Test
	public void TC1097_SimulatorTest_StartDrivingSurvey_PicAdmin() {
		Log.info("Running TC1097_SimulatorTest_StartDrivingSurvey_PicAdmin");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		TestSetup.replayDB3Script(REPLAY_DB3_DEFN_FILE, SURVEYOR_DB3);

		driverViewPage.open();
		driverViewPage.waitForPageLoad();
		driverViewPage.waitForConnectionComplete();
		
		Log.info("Clicking on MODE button");
		driverViewPage.clickModeButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		// 1., 2., 3., 4.
		// Start Driving Survey. Survey Time: Day, Solar Radiation: Overcast, Wind: Calm, Survey Type: Standard 
		String tag = testSetup.getFixedSizePseudoRandomString(10) + "_TC1097";
		driverViewPage.startDrivingSurvey(tag, SurveyTime.Day, SolarRadiation.Overcast, Wind.Calm, CloudCover.LessThan50, SurveyType.Standard);
		
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		// Open Header box and check the survey information.
		driverViewPage.clickHeaderInfoBox();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		Log.info("MODE:[" + driverViewPage.getSurveyModeLabelText() + "]");
		assertTrue(driverViewPage.getSurveyModeLabelText().equals(SURVEY_INFO_MODE_STANDARD));
		
		Log.info("SURVEY ACTIVE:[" + driverViewPage.getSurveyStatusLabelText() + "]");
		assertTrue(driverViewPage.getSurveyStatusLabelText().equals(SURVEY_INFO_SURVEY_STATUS_ACTIVE));
		
		Log.info("STABILITY CLASS:[" + driverViewPage.getStabilityClassLabelText() + "]");
		assertTrue(driverViewPage.getStabilityClassLabelText().equals(SURVEY_INFO_STABILITY_CLASS_C));
		
		Log.info("ELAPSED:[" + driverViewPage.getTimeElapsedLabelText() + "]");
		assertTrue(driverViewPage.getTimeElapsedLabelText().startsWith(SURVEY_INFO_ELAPSED_TIME_00));
		
		Log.info("REMAINING:[" + driverViewPage.getTimeRemainingLabelText() + "]");
		assertTrue(driverViewPage.getTimeRemainingLabelText().startsWith(SURVEY_INFO_REMAINING_TIME_07));
		
		Log.info("SURVEYOR:[" + driverViewPage.getSurveyorLabelText() + "]");
		assertTrue(driverViewPage.getSurveyorLabelText().equals(SURVEY_INFO_SURVEYOR_PREFIX + SIM_AUTO_SURVEYOR1 + " - " + SIM_AUTO_ANALYZER1));
		
		Log.info("ZOOM LEVEL:[" + driverViewPage.getZoomLevelLabelText() + "]");
		assertTrue(driverViewPage.getZoomLevelLabelText().equals(String.format(SURVEY_INFO_ZOOM_LEVEL_X, 19)));

		Log.info("TAG:[" + driverViewPage.getTagLabelText() + "]");
		Log.info("Tag value is: " + tag);
		assertTrue(driverViewPage.getTagLabelText().equals(SURVEY_INFO_TAG_PREFIX + tag));

		// 5. "Time limit warning should not appear before 7 hours have elapsed" <-- [Perform manually]
		
		// 6. "Car icon is displayed in red color. Breadcrumb will  be displayed in blue color" <-- [Check Feasibility in OpenLayer] 

		Log.info("Clicking on MODE button");
		driverViewPage.clickModeButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		// 7.
		assertTrue(driverViewPage.getStopDrivingSurveyButton().isDisplayed());
		assertTrue(driverViewPage.getStartIsotopicCaptureButton().isDisplayed());
		assertTrue(driverViewPage.getRefBottleMeasButton().isDisplayed());

		assertTrue(!driverViewPage.getSystemShutdownButton().isDisplayed());
	}
	
	/**
	 * Test Case ID: TC1098_SimulatorTest_StopDrivingSurvey_PicAdmin
	 * Script: -  	
	 * 1. Login to driver view 
	 * 2. Click on the menu button at top right on Chrome and select More Tools -> Developer Tools to open the Java console
	 * 3. Click on Mode button
	 * 4. Click on Start Driving Survey button
	 * 5. Provide survey tag, select  Survey Time: Day 
	 * Solar Radiation: Strong Wind: Light Survey Type: Operator and click on Start Survey button 
	 * 6. Click on Map and turn Map ON
	 * 7. Click on Mode
	 * 8. Click on Stop Driving Survey button
	 * Results: - 
	 * 1. Survey Information is displayed in satellite view - Tag, Mode, Time, Survey Active, Driver Info, Elapsed time, Remaining Time, Zoom level, Surveyor and analyzer info.
	 * - Stability Class value displayed is C
	 * 2. The Java console should open up at the bottom of the screen (or at the right of the screen)
	 * 3. Start Driving Survey and System shutdown buttons are present. There should be no errors on the console
	 * 4. Survey conditions window should pop up
	 * 5. Car icon is displayed in red color. Breadcrumb will  be displayed in blue color 
	 * 6. View will change from Satellite to Map
	 * 7. Stop Driving Survey, Isotopic Capture and Reference Bottle Measurement buttons are present
	 * 8. Survey Inactive message is displayed and only car icon is present on map. Car icon is displayed in grey color. Breadcrumb will  be displayed in grey color. There should be no errors on the consolebuttons are disabled
	 */
	// Partially automated. Console windows error checking NOT present.
	@Test
	public void TC1098_SimulatorTest_StopDrivingSurvey_PicAdmin() {
		Log.info("Running TC1098_SimulatorTest_StopDrivingSurvey_PicAdmin");

		TestSetup.replayDB3Script(REPLAY_DB3_DEFN_FILE, SURVEYOR_DB3);

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		driverViewPage.open();
		driverViewPage.waitForPageLoad();
		driverViewPage.waitForConnectionComplete();
		
		Log.info("Clicking on MODE button");
		driverViewPage.clickModeButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		// 3.
		assertTrue(driverViewPage.getStartSurveyButton().isDisplayed());
		assertTrue(driverViewPage.getSystemShutdownButton().isDisplayed());
		
		driverViewPage.clickStartSurveyButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		// 1., 2. 4.
		// Start Driving Survey. Survey Time: Day, Solar Radiation: Strong, Wind: Light, Survey Type: Operator 
		String tag = testSetup.getFixedSizePseudoRandomString(10) + "_TC1098";
		driverViewPage.startDrivingSurvey(tag, SurveyTime.Day, SolarRadiation.Strong, Wind.Light, CloudCover.LessThan50, SurveyType.Operator);

		// 5. "Car icon is displayed in red color. Breadcrumb will  be displayed in blue color" <-- [Check feasibility in Open Layer]
		
		// Open Header box and check the survey information.
		driverViewPage.clickMapButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		driverViewPage.toggleMapSwitch(MapSwitchType.Map, true /*turnOn*/);
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		// 6.
		driverViewPage.verifyLoadedMap(MapSwitchType.Map);
		
		Log.info("Clicking on MODE button");
		driverViewPage.clickModeButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		// 7.
		assertTrue(driverViewPage.getStopDrivingSurveyButton().isDisplayed());
		assertTrue(driverViewPage.getStartIsotopicCaptureButton().isDisplayed());
		assertTrue(driverViewPage.getRefBottleMeasButton().isDisplayed());

		Log.info("Clicking on STOP SURVEY");
		driverViewPage.getStopDrivingSurveyButton().click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		Log.info("Waiting on UI unblock.");
		driverViewPage.waitForUIUnBlock();
		
		// 8. "Only car icon is present on map. Car icon is displayed in grey color. Breadcrumb will  be displayed in grey color." <-- [Check Feasibility]
		Log.info("Click Header Info Box.");
		driverViewPage.clickHeaderInfoBox();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		// 8.
		Log.info("SURVEY INACTIVE:[" + driverViewPage.getSurveyStatusLabelText() + "]");
		assertTrue(driverViewPage.getSurveyStatusLabelText().equals(SURVEY_INFO_SURVEY_STATUS_INACTIVE));
	}

	/**
	 * Test Case ID: TC256_ActionTest_DriverViewInstrumentStartWaitStopShutdown
	 * Script: -  	
	 *	- Enter driver view
	 *	- Click Start Driving Survey from "Mode"
	 *	- After few mins click on stop driving survey
	 *	- Click on system shutdown button
	 * Results: - 
	 *	- Survey capture should start
	 *	- Survey capture should stop. Screen darkens and Mode menu disappears (or Start Survey button is unavailable) and survey is uploaded
	 *	- Analyzer is powering down and user is navigated to home page
	 *	- Analyzer will turn off
	 * @throws Exception 
	 */
	// Partially automated. Analyzer powering down might need work from Simulator.
	@Test
	public void TC256_ActionTest_DriverViewInstrumentStartWaitStopShutdown() throws Exception {
		Log.info("\nRunning TC256_SimulatorTest_DriverViewInstrumentStartWaitStopShutdown");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(testSetup.getLoginUser() + ":" + testSetup.getLoginPwd(), NOTSET);
		testEnvironmentAction.startAnalyzer(EMPTY, 3); 	// start simulator and replay db3 file.
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);

		testEnvironmentAction.startReplay(EMPTY, 3); 	// start simulator and replay db3 file.
		driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);
		
		driverViewPageAction.startDrivingSurvey(EMPTY, 3);
		
		// Intentional sleep for 3 minutes as per test case steps. (Use 1/4th the time)
		testEnvironmentAction.idleForSeconds(String.valueOf(45), NOTSET);
		
		driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);
		driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);
		
		driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);
		assertTrue(driverViewPageAction.clickOnShutdownButton(EMPTY,NOTSET));
		assertTrue(driverViewPageAction.clickOnShutdownConfirmButton(EMPTY,NOTSET));
		
		// Wait for 15 seconds to allow Analyzer EXE to shutdown and browser to close and get redirected to home.
		testEnvironmentAction.idleForSeconds(String.valueOf(15), NOTSET);			

		// Verify browser is redirected to Home
		assertTrue(driver.getCurrentUrl().contains(HomePage.STRURLPath));
		
		// TODO : This capability needs to be verified in Simulator.
		//assertTrue(testEnvironmentAction.verifyAnalyzerIsShutdown(EMPTY, NOTSET));			
	}

	/**
	 * Test Case ID: TC302_ActionTest_DriverViewUserSeesLastTagValue
	 * Script: -  	
	 *	- Driver is in same network of analyzer
	 *	- Click on Mode -> Start Driving Survey [E1-1]
	 *	- Navigate to Dashboard and then back to Driver View.[E1-2]
	 *	- Stop Survey and Start new Survey again [E1 -3]
	 *	- Select all required fields and keep tag field empty [E2]
	 *	- Input value in tag field [E3]
	 * Results: - 
	 *	E1. Last survey tag (if any) should be present 
	 *	E2. Start Survey button is not enabled 
	 *	E3. Survey tag will have the specified value
	 * @throws Exception 
	 */
	@Test
	public void TC302_ActionTest_DriverViewUserSeesLastTagValue() throws Exception {
		Log.info("\nRunning TC302_SimulatorTest_DriverViewUserSeesLastTagValue");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(testSetup.getLoginUser() + ":" + testSetup.getLoginPwd(), NOTSET);
		testEnvironmentAction.startAnalyzer(EMPTY, 3); 	// start analyzer.
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
		testEnvironmentAction.startReplay(EMPTY, 3); 	// start replay db3 file.
		driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);
		
		driverViewPageAction.startDrivingSurvey(EMPTY, 3);
		
		// Run the survey for 50 seconds.. (Use ~1/4th the time)
		testEnvironmentAction.idleForSeconds(String.valueOf(15), NOTSET);
		
		driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);
		driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);
		
		driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);
		driverViewPageAction.openStartSurveyModalDialog(EMPTY,NOTSET);
		
		assertTrue(driverViewPageAction.verifySurveyTagInStartSurveyDialogEquals(DriverViewPageActions.workingDataRow.surveyTag, NOTSET));
		
		// {DEFECT}: Check if this test step is correct. If YES, this is a probable defect.
		//assertTrue(driverViewPageAction.verifyStartSurveyButtonFromSurveyDialogIsDisabled(EMPTY, NOTSET));
		
		testEnvironmentAction.stopAnalyzer(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC777_ActionTest_DriverViewFlatteningCustomerBoundaryData
	 * Script: -  	
	 *	1. Login to Pcubed via car tablet with PG&E Driver credentials and click on GIS option
	 *	2. Toggle the ON and OFF for both options boundary options.
	 *	3. Login to Pcubed via car tablet with Picarro Supervisor credentials and click on GIS option
	 * Results: - 
	 *	1. For Boundaries it should show only 2 options 1. District 2. District Plat
	 *	2.Verify that when Each type (District and District Plat) is selected then it appears on Map and when it is set to OFF it does not show on Map.
	 *	3.Verify that 2 options shown as District and District Plat. When Any one of those toggled to ON,it  is selected then it appears on Map and when it is set to OFF it is not shown on map.
	 * @throws Exception 
	 */
	@Test
	public void TC777_ActionTest_DriverViewFlatteningCustomerBoundaryData() throws Exception {
		Log.info("\nRunning TC777_SimulatorTest_DriverViewFlatteningCustomerBoundaryData");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 9);   /* PG&E Driver */
		testEnvironmentAction.startAnalyzer(EMPTY, 34); 	// start simulator and replay db3 file. (SimAuto-Analyzer5)
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);

		testEnvironmentAction.startReplay(EMPTY, 34); 	// start simulator and replay db3 file.
		driverViewPageAction.clickOnGisButton(EMPTY,NOTSET);
		driverViewPageAction.turnOffBigBoundary(EMPTY, NOTSET);
		driverViewPageAction.turnOffSmallBoundary(EMPTY, NOTSET);
		testEnvironmentAction.stopAnalyzer(EMPTY, NOTSET);

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 8);   /* PG&E Customer Supervisor */
		testEnvironmentAction.startAnalyzer(EMPTY, 34); 	// start simulator and replay db3 file.
		// To get a new instance of Driver view page, initialize the driver view page.
		DriverViewPage driverViewPage = new DriverViewPage(TestContext.INSTANCE.getDriver(), 
				TestContext.INSTANCE.getTestSetup(),
				TestContext.INSTANCE.getBaseUrl());
		driverViewPageAction.initializePageObject(TestContext.INSTANCE.getDriver(), driverViewPage);
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
		
		driverViewPageAction.clickOnGisButton(EMPTY,NOTSET);
		assertTrue(driverViewPageAction.verifyGisSwitchIsOff("BigBoundary",NOTSET));
		assertTrue(driverViewPageAction.verifyGisSwitchIsOff("SmallBoundary",NOTSET));
		driverViewPageAction.turnOnBigBoundary(EMPTY, NOTSET);
		driverViewPageAction.turnOnSmallBoundary(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.verifyGisSwitchIsOn("BigBoundary",NOTSET));
		assertTrue(driverViewPageAction.verifyGisSwitchIsOn("SmallBoundary",NOTSET));
		assertTrue(driverViewPageAction.verifyBoundariesIsShownOnMap(EMPTY,NOTSET));
		
		testEnvironmentAction.stopAnalyzer(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC1095_ActionTest_NavigateBetweenDriverViewAndHomePage
	 * Script: -  	
	 * 1. Log in to driver view 
	 * 2. Click on Picarro Icon present at bottom 
	 * 3. Go back to driver view (Click on Driver View link present below dashboard on left side menu) 
	 * 4. Click on Back button of the browser	 
	 * Results: - 
	 * 1. User is navigated to Home Page 
	 * 2. User is navigated to Driver view page 
	 * 3. User is navigated back to Home Page
	 * @throws Exception 
	 */
	@Test
	public void TC1095_ActionTest_NavigateBetweenDriverViewAndHomePage() throws Exception {
		Log.info("\nRunning TC1095_SimulatorTest_NavigateBetweenDriverViewAndHomePage");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 16);   /* Picarro Driver */
		testEnvironmentAction.startAnalyzer(EMPTY, 3); 	// start analyzer.
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
		testEnvironmentAction.startReplay(EMPTY, 3); 	// start replay db3 file.

		// Let replay run for 5 seconds.
		testEnvironmentAction.idleForSeconds("5", NOTSET);
		
		// goto home by clicking on picarro logo on driver view page.
		driverViewPageAction.clickOnPicarroLogoButton(EMPTY, NOTSET);
		
		homePage.waitForPageLoad();
		
		// go back to driver view page using browser back button.
		BrowserCommands.goBack();
		
		// To get a new instance of Driver view page, initialize the driver view page.
		DriverViewPage driverViewPage = new DriverViewPage(TestContext.INSTANCE.getDriver(), 
				TestContext.INSTANCE.getTestSetup(),
				TestContext.INSTANCE.getBaseUrl());
		driverViewPageAction.initializePageObject(TestContext.INSTANCE.getDriver(), driverViewPage);	
		
		assertTrue(driverViewPageAction.verifyPageLoaded(EMPTY,NOTSET));
		
		testEnvironmentAction.stopAnalyzer(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC1103_ActionTest_DriverViewStartDrivingSurveySatelliteView
	 * Script: -  	
	 *	1. Login to driver view 
	 *	2. Click on Mode -> Start Driving Survey 
	 *	3. Provide survey tag, select 
	 *	Survey Time: Day 
	 *	Solar Radiation: Strong 
	 *	Wind: Calm 
	 *	Survey Type: Standard 
	 *	4. Click on Start Survey button 
	 *	5. Click on Map and turn Satellite ON 
	 *	6. Click on Mode button
	 * Results: - 
	 *	1. Survey conditions screen is present. Start Survey button is not present 
	 *	2. Once all the values are selected, "Start Survey" button should be present and enabled 
	 *	3. Survey Information is displayed in satellite view - Tag, Mode, Time, Survey Active, Driver Info, Elapsed time, Remaining Time, Zoom level, Surveyor and analyzer info 
	 *	- Stability Class value displayed is A
	 *	4. Car icon is displayed in red color. Breadcrumb will  be displayed in blue color 
	 *	5. Stop Driving Survey, Start Isotopic Capture and Reference Bottle Measurement buttons are enabled. System Shutdown button is not present	 
	 * @throws Exception 
	 **/
	@Test
	public void TC1103_ActionTest_DriverViewStartDrivingSurveySatelliteView() throws Exception {
		Log.info("\nRunning TC1103_SimulatorTest_DriverViewStartDrivingSurveySatelliteView");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 3);   /* Customer Driver */
		testEnvironmentAction.startAnalyzer(EMPTY, 33); 	// start simulator and replay db3 file.
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		testEnvironmentAction.startReplay(EMPTY, 33); 	// start simulator and replay db3 file.

		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		
		// Verify 1. and 2.
		// This action internally verifies that the startSurvey button is ONLY enabled after survey mode is selected.
		driverViewPageAction.startDrivingSurvey(EMPTY, 4);	
		testEnvironmentAction.idleForSeconds(String.valueOf(5), NOTSET);
		
		driverViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		driverViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
		
		// Verify 3.
		driverViewPageAction.verifyMapSwitchOn("Satellite", NOTSET);
		
		driverViewPageAction.clickOnHeaderInfoBox(EMPTY, NOTSET);

		// Verify 3.
		String expectedTagValue = DriverViewPageActions.workingDataRow.surveyTag;
		String expectedModeValue = SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType;
		String expectedTimeStartsWith = SURVEY_INFO_TIME_PREFIX + String.valueOf(DateUtility.getSystemHourIn12HourFormat());
		String expectedSurveyStatus = SURVEY_INFO_SURVEY_STATUS_ACTIVE;
		String expectedDriverInfo = SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username;
		String expectedTimeElapsedStartsWith = SURVEY_INFO_ELAPSED_TIME_00;
		String expectedTimeRemainingStartsWith = SURVEY_INFO_REMAINING_TIME_07;
		String expectedZoomLevel = String.format(SURVEY_INFO_ZOOM_LEVEL_X, 19);
		String expectedAnalyzerValue = SURVEY_INFO_SURVEY_STATUS_ACTIVE;
		String expectedSurveyorValue = SURVEY_INFO_SURVEYOR4_ANALYZER4;
		String expectedStabilityClass = SURVEY_INFO_STABILITY_CLASS_A;
		
		assertTrue(driverViewPageAction.verifySurveyInfoTagLabelEquals(expectedTagValue, NOTSET));			
		assertTrue(driverViewPageAction.verifySurveyInfoModeLabelEquals(expectedModeValue, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoTimeLabelStartsWith(expectedTimeStartsWith, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoSurveyStatusLabelEquals(expectedSurveyStatus, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoDriverLabelEquals(expectedDriverInfo, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoTimeElapsedLabelStartsWith(expectedTimeElapsedStartsWith, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoTimeRemainingLabelStartsWith(expectedTimeRemainingStartsWith, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoZoomLevelLabelEquals(expectedZoomLevel, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoAnalyzerLabelEquals(expectedAnalyzerValue, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoSurveyorLabelEquals(expectedSurveyorValue, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoStabilityClassLabelEquals(expectedStabilityClass, NOTSET));
		
		// Verify 4.
		// TODO : Car Icon is NOT getting detected. Verify.
		//assertTrue(driverViewPageAction.verifyCrossHairIconIsShownOnMap("Red", NOTSET));
		assertTrue(driverViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		
		// Verify 5.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.verifyStopDrivingSurveyButtonIsEnabled(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifyStartIsotopicCaptureButtonIsEnabled(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifyRefBottleMeasButtonIsEnabled(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifySystemShutdownButtonIsNotDisplayed(EMPTY, NOTSET));
		
		testEnvironmentAction.stopAnalyzer(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC1104_ActionTest_DriverViewStopDrivingSurveySatelliteView
	 * Script: -  	
	 *	1. Login to driver view 
	 *	2. Click on the menu button at top right on Chrome and select More Tools -> Developer Tools to open the Java console
	 *	3. Click on Mode button
	 *	4. Click on Start Driving Survey button
	 *	5. Provide survey tag, select  Survey Time: Day Solar Radiation: Strong Wind: Light Survey Type: Operator and click on Start Survey button 
	 *	6. Click on Mode
	 *	7. Click on Stop Driving Survey button
	 * Results: - 
	 *	1. Survey Information is displayed in satellite view - Tag, Mode, Time, Survey Active, Driver Info, Elapsed time, Remaining Time, Zoom level, Surveyor and analyzer info.
	 *	- Stability Class value displayed is B
	 *	2. The Java console should open up at the bottom of the screen (or at the right of the screen)
	 *	3. Start Driving Survey and System shutdown buttons are present. There should be no errors on the console
	 *	4. Survey conditions window should pop up
	 *	5. Car icon is displayed in red color. Breadcrumb will  be displayed in blue color 
	 *	6. Stop Driving Survey, Isotopic Capture and Reference Bottle Measurement buttons are present
	 *	7. Survey Inactive message is displayed and only car icon is present on map. Car icon is displayed in grey color. Breadcrumb will  be displayed in grey color. There should be no errors on the console
	 * @throws Exception 
	 **/
	@Test
	public void TC1104_ActionTest_DriverViewStopDrivingSurveySatelliteView() throws Exception {
		Log.info("\nRunning TC1104_SimulatorTest_DriverViewStopDrivingSurveySatelliteView");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 16);   /* Picarro Driver */
		testEnvironmentAction.startAnalyzer(EMPTY, 3); 	// start analyzer.
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
		testEnvironmentAction.startReplay(EMPTY, 3); 	// start replay db3 file.

		// Verify 1.
		driverViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		driverViewPageAction.verifyMapSwitchOn("Satellite", NOTSET);

		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);

		// Verify 3. and 4.
		// This action internally verifies that the startSurvey button is ONLY enabled after survey mode is selected.
		driverViewPageAction.startDrivingSurvey(EMPTY, 5);  /* Day, Strong, Light, Operator */	
		
		testEnvironmentAction.idleForSeconds(String.valueOf(10), NOTSET);
		driverViewPageAction.clickOnHeaderInfoBox(EMPTY, NOTSET);

		// Verify 1.
		String expectedTagValue = DriverViewPageActions.workingDataRow.surveyTag;
		String expectedModeValue = SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType;
		String expectedTimeStartsWith = SURVEY_INFO_TIME_PREFIX + String.valueOf(DateUtility.getSystemHourIn12HourFormat());
		String expectedSurveyStatus = SURVEY_INFO_SURVEY_STATUS_ACTIVE;
		String expectedDriverInfo = SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username;
		String expectedTimeElapsedStartsWith = SURVEY_INFO_ELAPSED_TIME_00;
		String expectedTimeRemainingStartsWith = SURVEY_INFO_REMAINING_TIME_07;
		String expectedZoomLevel = String.format(SURVEY_INFO_ZOOM_LEVEL_X, 19);
		String expectedAnalyzerValue = SURVEY_INFO_SURVEY_STATUS_ACTIVE;
		String expectedSurveyorValue = SURVEY_INFO_SURVEYOR1_ANALYZER1;
		String expectedStabilityClass = SURVEY_INFO_STABILITY_CLASS_B;
		
		assertTrue(driverViewPageAction.verifySurveyInfoTagLabelEquals(expectedTagValue, NOTSET));			
		assertTrue(driverViewPageAction.verifySurveyInfoModeLabelEquals(expectedModeValue, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoTimeLabelStartsWith(expectedTimeStartsWith, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoSurveyStatusLabelEquals(expectedSurveyStatus, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoDriverLabelEquals(expectedDriverInfo, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoTimeElapsedLabelStartsWith(expectedTimeElapsedStartsWith, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoTimeRemainingLabelStartsWith(expectedTimeRemainingStartsWith, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoZoomLevelLabelEquals(expectedZoomLevel, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoAnalyzerLabelEquals(expectedAnalyzerValue, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoSurveyorLabelEquals(expectedSurveyorValue, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoStabilityClassLabelEquals(expectedStabilityClass, NOTSET));

		// Verify 5.
		// TODO : Car Icon is NOT getting detected. Verify.
		//assertTrue(driverViewPageAction.verifyCrossHairIconIsShownOnMap("Red", NOTSET));
		assertTrue(driverViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));

		// Verify 6.
		assertTrue(driverViewPageAction.verifyStopDrivingSurveyButtonIsEnabled(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifyStartIsotopicCaptureButtonIsEnabled(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifyRefBottleMeasButtonIsEnabled(EMPTY, NOTSET));

		// Stop driving survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);
		
		assertTrue(driverViewPageAction.verifySystemShutdownButtonIsNotDisplayed(EMPTY, NOTSET));

		// Verify 7.
		expectedSurveyStatus = SURVEY_INFO_SURVEY_STATUS_INACTIVE;
		assertTrue(driverViewPageAction.verifySurveyInfoSurveyStatusLabelEquals(expectedSurveyStatus, NOTSET));
		
		// Verify 7.
		// (check????) ONLY car icon is present on the map. For this what negative conditions can we check.
		// That is, what can we check as NOT shown on the map.
		
		// Verify 7.
		// TODO : Car Icon is NOT getting detected. Verify.
		//assertTrue(driverViewPageAction.verifyCrossHairIconIsShownOnMap("Gray", NOTSET));
		assertTrue(driverViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));			
		
		testEnvironmentAction.stopAnalyzer(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC1133_ActionTest_DriverView2SurveysSameTagSameAnalyzer8HrHistoryON
	 * Script: -  	
	 *	1. Login to driver view 
	 *	2. Click on Mode -> Start Driving Survey 
	 *	3. Provide survey tag, select  Survey Time: Day Solar Radiation: Overcast Wind: Calm Survey Type: Standard 
	 *	4. Click on Start Survey button 
	 *	5. Click on Map and turn Map view ON 
	 *	6. Do the survey for 4-5 mins and Stop the survey 
	 *	7. Click on Mode -> Start Driving Survey 
	 *	8. Provide the exact same survey tag 
	 *	9. Click on Start Survey button 
	 *	10. Click on Display -> turn 8 hour history ON
	 * Results: - 
	 *	1. Any surveys conducted within the past 8 hours that have the same tag for same surveyor and analyzer will be displayed on the map
	 * @throws Exception 
	 **/
	// Partially automated.
	@Test
	public void TC1133_ActionTest_DriverView2SurveysSameTagSameAnalyzer8HrHistoryON() throws Exception {
		Log.info("\nRunning TC1133_SimulatorTest_DriverView2SurveysSameTagSameAnalyzer8HrHistoryON");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 16);   /* Picarro Driver */
		testEnvironmentAction.startAnalyzer(EMPTY, 3); 	// start simulator and replay db3 file.
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);

		testEnvironmentAction.startReplay(EMPTY, 3); 	// start simulator and replay db3 file.

		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);

		// This action internally verifies that the startSurvey button is ONLY enabled after survey mode is selected.
		driverViewPageAction.startDrivingSurvey(EMPTY, 19);	/* Static Survey tag, Day, Overcast, Calm, Standard */
		
		// turn ON map view.
		driverViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		driverViewPageAction.turnOnMapView("Map", NOTSET);
		
		// perform the survey for 1 min. (Use 1/4th the time)
		testEnvironmentAction.idleForSeconds(String.valueOf(15), NOTSET);

		// Stop driving survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);

		// start survey again.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.startDrivingSurvey(EMPTY, 19);	/* Static Survey tag, Day, Overcast, Calm, Standard */

		// turn ON 8-hour history.
		driverViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		driverViewPageAction.turnOnEightHourHistory(EMPTY, NOTSET);
		
		//-------------------
		// Verify 1.
		String expectedTagValue = DriverViewPageActions.workingDataRow.surveyTag;
		assertTrue(driverViewPageAction.verifySurveyInfoTagLabelEquals(expectedTagValue, NOTSET));			

		// Verify 1.
		// (check???) surveys conducted in the past 8-hour should be displayed on the map.
		
		testEnvironmentAction.stopAnalyzer(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC1134_ActionTest_DriverView2SurveysSameTagDifferentAnalyzers8HrHistoryON
	 * Script: -  	
	 *	1. Login to driver view (eg. Surveyor1 and analyzer1) 
	 *	2. Click on Mode -> Start Driving Survey 
	 *	3. Provide survey tag, select  Survey Time: Day Solar Radiation: Overcast Wind: Calm Survey Type: Standard 
	 *	4. Click on Start Survey button 
	 *	5. Click on Map and turn Map view ON 
	 *	6. Do the survey for 4-5 mins and Stop the survey 
	 *	7. Change the analyzer and log in to driver view (eg. Surveyor2 and analyzer2) 
	 *	8. Click on Mode -> Start Driving Survey 
	 *	9. Provide the exact same survey tag 
	 *	10. Click on Start Survey button 
	 *	11. Click on Map and turn Map View ON 
	 *	12. Click on Display -> turn 8 hour history ON
	 * Results: - 
	 *	1. Previous survey having same tag value will not be present as user has changed the surveyor\analyzer
	 * @throws Exception 
	 **/
	// Partially automated.
	@Test
	public void TC1134_ActionTest_DriverView2SurveysSameTagDifferentAnalyzers8HrHistoryON() throws Exception {
		Log.info("\nRunning TC1134_SimulatorTest_DriverView2SurveysSameTagDifferentAnalyzers8HrHistoryON");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 16);   /* Customer Driver */
		
		testEnvironmentAction.startAnalyzer(EMPTY, 3); 	// start simulator and replay db3 file.
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);

		testEnvironmentAction.startReplay(EMPTY, 3); 	// start simulator and replay db3 file.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);

		// This action internally verifies that the startSurvey button is ONLY enabled after survey mode is selected.
		driverViewPageAction.startDrivingSurvey(EMPTY, 20);	/* Static Survey tag, Day, Overcast, Calm, Standard */
		
		driverViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		driverViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		
		// turn ON map view.
		driverViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		driverViewPageAction.turnOnMapView("Map", NOTSET);
		
		// perform the survey for 2 mins. (Use 1/4th the time)
		testEnvironmentAction.idleForSeconds(String.valueOf(30), NOTSET);

		// Stop driving survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);

		// Stop current simulator and start another with a different Analyzer.
		testEnvironmentAction.stopAnalyzer(EMPTY, NOTSET);

		// To get a new instance of Driver view page, initialize the driver view page.
		DriverViewPage driverViewPage = new DriverViewPage(TestContext.INSTANCE.getDriver(), 
				TestContext.INSTANCE.getTestSetup(),
				TestContext.INSTANCE.getBaseUrl());
		driverViewPageAction.initializePageObject(TestContext.INSTANCE.getDriver(), driverViewPage);		

		testEnvironmentAction.startAnalyzer(EMPTY, 4);
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);

		testEnvironmentAction.startReplay(EMPTY, 4); 	// start simulator and replay db3 file.

		// start survey again.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.startDrivingSurvey(EMPTY, 20);	/* Static Survey tag, Day, Overcast, Calm, Standard */

		// turn ON map view.
		driverViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		driverViewPageAction.turnOnMapView("Map", NOTSET);

		// turn ON 8-hour history.
		driverViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		driverViewPageAction.turnOnEightHourHistory(EMPTY, NOTSET);
		
		driverViewPageAction.clickOnHeaderInfoBox(EMPTY, NOTSET);
		
		//-------------------
		// Verify 1.
		String expectedSurveyorValue = SURVEY_INFO_SURVEYOR2_ANALYZER2;
		assertTrue(driverViewPageAction.verifySurveyInfoSurveyorLabelEquals(expectedSurveyorValue, NOTSET));			

		// Verify 1.
		// (check???) surveys conducted in the past 8-hour should NOT be displayed on the map.
		
		testEnvironmentAction.stopAnalyzer(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC1212_ActionTest_DriverViewStandardSurveyNewDriver
	 * Script: -  	
	 *	1. Log in to driver view as new driver user 
	 *	2. Accept the EULA 
	 *	3. Click on Mode -> Start Driving Survey, answer all the questions and click on Start Survey 
	 *	4. Click on Display and turn all options ON
	 * Results: - 
	 *	1. Eula screen should be displayed 2. User is navigated to driver view 3. User is able to perform survey and can see all the survey data on map	 
	 * @throws Exception 
	 **/
	// Partially automated.
	@Test
	public void TC1212_ActionTest_DriverViewStandardSurveyNewDriver() throws Exception {
		String userName = SQACUS + testSetup.getFixedSizeRandomNumber(8) + REGBASEUSERNAME;
		String location = SQACUS + " - " + SQACUSLOC;
		
		Log.info("\nRunning TC1212_SimulatorTest_DriverViewStandardSurveyNewDriver - Test Description: Standard Survey as new driver user");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(SQACUS, userName, USERPASSWORD, CUSUSERROLEDR,location);
		
		manageCustomersPage.open();
		manageCustomersPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		testEnvironmentAction.startAnalyzer(EMPTY, 33); 	// start simulator and replay db3 file.
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);

		testEnvironmentAction.startReplay(EMPTY, 33); 	// start simulator and replay db3 file.
		
		// Verify that Driver view page was opened.
		assertTrue(driverViewPageAction.verifyDriverViewPageIsOpened(EMPTY, NOTSET));

		// Turn ON possible natural gas.
		driverViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		driverViewPageAction.turnOnPossibleNaturalGas(EMPTY, NOTSET);
		driverViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);

		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);

		driverViewPageAction.startDrivingSurvey(EMPTY, 6);	/* Day, Overcast, Calm, Standard */

		driverViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		driverViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		driverViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		
		// turn ON all Display options.
		driverViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		driverViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
		
		// perform the survey for 1 mins. (Use 1/4th the time)
		testEnvironmentAction.idleForSeconds(String.valueOf(15), NOTSET);
		
		driverViewPageAction.clickOnHeaderInfoBox(EMPTY, NOTSET);
		
		//-------------------
		// Verify 
		String expectedTagValue = DriverViewPageActions.workingDataRow.surveyTag;
		String expectedModeValue = SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType;
		String expectedTimeStartsWith = SURVEY_INFO_TIME_PREFIX + String.valueOf(DateUtility.getSystemHourIn12HourFormat());
		String expectedSurveyStatus = SURVEY_INFO_SURVEY_STATUS_ACTIVE;
		String expectedDriverInfo = SURVEY_INFO_DRIVER_PREFIX + userName;
		String expectedTimeElapsedStartsWith = SURVEY_INFO_ELAPSED_TIME_00;
		String expectedTimeRemainingStartsWith = SURVEY_INFO_REMAINING_TIME_07;
		String expectedZoomLevel = String.format(SURVEY_INFO_ZOOM_LEVEL_X, 16);
		String expectedAnalyzerValue = SURVEY_INFO_SURVEY_STATUS_ACTIVE;
		String expectedSurveyorValue = SURVEY_INFO_SURVEYOR4_ANALYZER4;
		String expectedStabilityClass = SURVEY_INFO_STABILITY_CLASS_C;
		
		assertTrue(driverViewPageAction.verifySurveyInfoTagLabelEquals(expectedTagValue, NOTSET));			
		assertTrue(driverViewPageAction.verifySurveyInfoModeLabelEquals(expectedModeValue, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoTimeLabelStartsWith(expectedTimeStartsWith, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoSurveyStatusLabelEquals(expectedSurveyStatus, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoDriverLabelEquals(expectedDriverInfo, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoTimeElapsedLabelStartsWith(expectedTimeElapsedStartsWith, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoTimeRemainingLabelStartsWith(expectedTimeRemainingStartsWith, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoZoomLevelLabelEquals(expectedZoomLevel, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoAnalyzerLabelEquals(expectedAnalyzerValue, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoSurveyorLabelEquals(expectedSurveyorValue, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoStabilityClassLabelEquals(expectedStabilityClass, NOTSET));
		
		// Verify FOV, Indications, Breadcrumbs, ConcentrationChart, WindRose are shown on Map.
		assertTrue(driverViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		// TODO FOV is currently NOT showing in the Map with Simulator. Tracked by DE1484
		// assertTrue(driverViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));

		assertTrue(driverViewPageAction.verifyConcentrationChartIsShownOnMap(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifyWindRoseIsShownOnMap(EMPTY, NOTSET));
		assertTrue(driverViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
		
		testEnvironmentAction.stopAnalyzer(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC1213_SimulatorTest_NewDriverNavigatedToHomePage
	 * Script: -  	
	 *	1. Log in to tablet as new driver user 
	 *	2. Accept the EULA
	 * Results: - 
	 *	1. User should be navigated to Home page and not to driver view page
	 **/
	@Test
	public void TC1213_SimulatorTest_NewDriverNavigatedToHomePage() {
		String userName = SQACUS + testSetup.getFixedSizeRandomNumber(8) + REGBASEUSERNAME;
		String location = SQACUS + " - " + SQACUSLOC;
		
		Log.info("\nRunning TC1213_SimulatorTest_NewDriverNavigatedToHomePage - Test Description: Standard Survey as new driver user");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(SQACUS, userName, USERPASSWORD, CUSUSERROLEDR,location);

		manageCustomersPage.open();
		manageCustomersPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		assertTrue(homePage.checkIfAtHomePage());
	}

	/**
	 * Test Case ID: TC1215_ActionTest_CannotLoginDriverViewInvalidCredentials
	 * Script: -  	
	 *	1. Log in to driver view with non-existing or invalid user credentails
	 * Results: - 
	 *	1. User remains on log in page
	 * @throws Exception 
	 **/
	@Test
	public void TC1215_ActionTest_CannotLoginDriverViewInvalidCredentials() throws Exception {
		Log.info("\nRunning TC1215_SimulatorTest_CannotLoginDriverViewInvalidCredentials");

		testEnvironmentAction.startAnalyzer(EMPTY, 3); 	// start simulator and replay db3 file.
		driverViewPage.open();

		loginPage.waitForPageLoad();
		assertTrue(loginPage.checkIfAtLoginPage());
	}
	
	/**
	 * Test Case ID: TC1232_ActionTest_DriverViewRefreshBrowser
	 * Script: -  	
	 *	1. Log in to Driver View 
	 *	2. Click on Mode -> Start Driving Survey
	 *	3. Provide survey tag, select Survey Time: Day Solar Radiation: Overcast Wind: Strong Survey Type: Standard 
	 *	4. Click on Start Survey button  
	 *	5. Wait at least 30 seconds to let the Time Elapsed and Time Remaining values to run a bit 
	 *	6. Refresh the browser
	 * Results: - 
	 *	1. After refreshing, survey details should return, including Tag, Mode, Current Time, Survey Active, Driver Name, Stability Class, Elapsed Time, Remaining Time, Surveyor Name and Zoom Level. 
	 *	2. None of the values should be missing. Elapsed and Remaining Times should not reset after the refresh.
	 * @throws Exception 
 	 **/
	@Ignore // NOTES: Connection does NOT complete in Product after refresh when used w/ simulator. Checked by Kamini not repro on car. Disabled w/ simulator.
	public void TC1232_ActionTest_DriverViewRefreshBrowser() throws Exception {
		Log.info("\nRunning TC1232_SimulatorTest_DriverViewRefreshBrowser");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 3);   /* Customer Driver */
		testEnvironmentAction.startAnalyzer(EMPTY, 3); 	// start simulator.
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
		testEnvironmentAction.startReplay(EMPTY, 3); 	// start replay db3 file.

		// start survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.startDrivingSurvey(EMPTY, 17);	/* Day, Overcast, Strong, Standard */
		testEnvironmentAction.idleForSeconds(String.valueOf(30), NOTSET);

		driverViewPageAction.clickOnHeaderInfoBox(EMPTY, NOTSET);
		String elapsedTimeBeforeRefresh = driverViewPageAction.getDriverViewPage().getTimeElapsedLabelText().replace(SURVEY_INFO_ELAPSED_PREFIX, "");
		String remainingTimeBeforeRefresh = driverViewPageAction.getDriverViewPage().getTimeRemainingLabelText().replace(SURVEY_INFO_REMAINING_PREFIX, "");
		
		// Refresh the page in browser.
		driverViewPageAction.refreshPage(EMPTY, NOTSET);
		Log.info("Page has been refreshed");
		
		driverViewPageAction.getDriverViewPage().waitForPageLoad();
		Log.info("Wait complete for page load");

		driverViewPageAction.getDriverViewPage().waitForConnectionComplete();
		Log.info("Wait complete for connection complete");

		// Verify Survey properties.
		String expectedTagValue = DriverViewPageActions.workingDataRow.surveyTag;
		String expectedModeValue = SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType;
		String expectedTimeStartsWith = SURVEY_INFO_TIME_PREFIX + String.valueOf(DateUtility.getSystemHourIn12HourFormat());
		String expectedSurveyStatus = SURVEY_INFO_SURVEY_STATUS_ACTIVE;
		String expectedDriverInfo = SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username;
		String expectedTimeElapsedStartsWith = SURVEY_INFO_ELAPSED_TIME_00;
		String expectedTimeRemainingStartsWith = SURVEY_INFO_REMAINING_TIME_07;
		String expectedZoomLevel = String.format(SURVEY_INFO_ZOOM_LEVEL_X, 19);
		String expectedSurveyorValue = SURVEY_INFO_SURVEYOR1_ANALYZER1;
		String expectedStabilityClass = SURVEY_INFO_STABILITY_CLASS_D;

		Log.info("Created all the expected strings. Going to click on Header info box.");

		driverViewPageAction.clickOnHeaderInfoBox(EMPTY, NOTSET);
		String elapsedTimeAfterRefresh = driverViewPageAction.getDriverViewPage().getTimeElapsedLabelText().replace(SURVEY_INFO_ELAPSED_PREFIX, "");
		String remainingTimeAfterRefresh = driverViewPageAction.getDriverViewPage().getTimeRemainingLabelText().replace(SURVEY_INFO_REMAINING_PREFIX, "");

		// Verify: Elapsed and Remaining Times should not reset after the refresh.
		Log.info(String.format("Verifying ElapsedTimeAfterRefresh-[%s] is greater than ElapsedTimeBeforeRefresh-[%s]", 
				elapsedTimeAfterRefresh, elapsedTimeBeforeRefresh));
		assertTrue(DateUtility.isFirstTimeGreater(elapsedTimeAfterRefresh, elapsedTimeBeforeRefresh));

		Log.info(String.format("Verifying RemainingTimeAfterRefresh-[%s] is NOT greater than RemainingTimeBeforeRefresh-[%s]", 
				remainingTimeAfterRefresh, remainingTimeBeforeRefresh));
		assertTrue(!DateUtility.isFirstTimeGreater(remainingTimeAfterRefresh, remainingTimeBeforeRefresh));	

		assertTrue(driverViewPageAction.verifySurveyInfoTagLabelEquals(expectedTagValue, NOTSET));			
		assertTrue(driverViewPageAction.verifySurveyInfoModeLabelEquals(expectedModeValue, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoTimeLabelStartsWith(expectedTimeStartsWith, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoSurveyStatusLabelEquals(expectedSurveyStatus, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoDriverLabelEquals(expectedDriverInfo, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoTimeElapsedLabelStartsWith(expectedTimeElapsedStartsWith, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoTimeRemainingLabelStartsWith(expectedTimeRemainingStartsWith, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoZoomLevelLabelEquals(expectedZoomLevel, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoSurveyorLabelEquals(expectedSurveyorValue, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoStabilityClassLabelEquals(expectedStabilityClass, NOTSET));
	
		// Stop current simulator and start another with a different Analyzer.
		testEnvironmentAction.stopAnalyzer(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC1241_ActionTest_DriverViewStartSurveyMultipleTimes
	 * Script: -  	
	 *	- Start a survey with appropriate survey details in the dialog, select Survey Type "Standard" and click OK
	 *	- From the Mode menu, click Stop Survey
	 *	- From the Mode menu, click Start Survey
	 *	- When the dialog pops up, do not change any details and click Start Survey
	 *	- From the Mode menu, click Stop Survey again
	 *	- From the Mode menu, click Start Survey again
	 *	- When the dialog pops up, change all details except Survey Type and click Start Survey
	 * Results: - 
	 *	- Once the Start Survey button is clicked on the second survey, the dialog should disappear and the survey should start (car icon turns red, Elapsed Time begins to advance, etc.)
	 *	- Once the Start Survey button is clicked on the third survey, the dialog should disappear and the survey should start (car icon turns red, Elapsed Time begins to advance, etc.)
	 * @throws Exception 
 	 **/
	@Ignore 
	// NOTES: When run with simulator we see the following behavior:
	// After Start -> Stop -> Start -> Stop -> Start, Elapsed and Remaining time labels are BLANK. Checked by Kamini NOT repro in CAR. Disabled w/ simulator. 
	public void TC1241_ActionTest_DriverViewStartSurveyMultipleTimes() throws Exception {
		Log.info("\nRunning TC1241_SimulatorTest_DriverViewStartSurveyMultipleTimes");

		final int userDataRowID = 3;
		final int analyzerDb3DataRowID = 3;
		final int surveyDataRowID = 22;
		final int surveyRuntimeInSeconds = 5;

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, userDataRowID);   /* Customer Driver */
		
		Log.info("Starting Analyzer...");
		testEnvironmentAction.startAnalyzer(EMPTY, analyzerDb3DataRowID); 	// start analyzer.
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
		
		Log.info("Starting Replay...");
		testEnvironmentAction.startReplay(EMPTY, analyzerDb3DataRowID); 	// start replay db3 file.

		// start survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.startDrivingSurvey(EMPTY, surveyDataRowID);	/* Day, Overcast, Strong, Standard */
		testEnvironmentAction.idleForSeconds(String.valueOf(surveyRuntimeInSeconds), NOTSET);

		// stop survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);

		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		// click Start Survey button.
		driverViewPageAction.getDriverViewPage().clickStartSurveyButton();
		driverViewPageAction.getDriverViewPage().waitForPageToLoad();
		// click Start Survey inside the modal popup.
		driverViewPageAction.getDriverViewPage().clickStartSurvey();
		driverViewPageAction.getDriverViewPage().waitForPageToLoad();
		
		testEnvironmentAction.idleForSeconds(String.valueOf(surveyRuntimeInSeconds), NOTSET);

		// Verify Survey has started.
		String expectedTagValue = DriverViewPageActions.workingDataRow.surveyTag;
		String expectedModeValue = SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType;
		String expectedTimeStartsWith = SURVEY_INFO_TIME_PREFIX + String.valueOf(DateUtility.getSystemHourIn12HourFormat());
		String expectedSurveyStatus = SURVEY_INFO_SURVEY_STATUS_ACTIVE;
		String expectedDriverInfo = SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username;
		String expectedTimeElapsedStartsWith = SURVEY_INFO_ELAPSED_TIME_00;
		String expectedTimeRemainingStartsWith = SURVEY_INFO_REMAINING_TIME_07;
		String expectedZoomLevel = String.format(SURVEY_INFO_ZOOM_LEVEL_X, 19);
		String expectedSurveyorValue = SURVEY_INFO_SURVEYOR1_ANALYZER1;
		
		driverViewPageAction.clickOnHeaderInfoBox(EMPTY, NOTSET);			
		assertTrue(driverViewPageAction.verifySurveyInfoTagLabelEquals(expectedTagValue, NOTSET));			
		assertTrue(driverViewPageAction.verifySurveyInfoModeLabelEquals(expectedModeValue, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoTimeLabelStartsWith(expectedTimeStartsWith, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoSurveyStatusLabelEquals(expectedSurveyStatus, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoDriverLabelEquals(expectedDriverInfo, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoTimeElapsedLabelStartsWith(expectedTimeElapsedStartsWith, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoTimeRemainingLabelStartsWith(expectedTimeRemainingStartsWith, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoZoomLevelLabelEquals(expectedZoomLevel, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoSurveyorLabelEquals(expectedSurveyorValue, NOTSET));
		
		// click on Header info again to close Header.
		driverViewPageAction.clickOnHeaderInfoBox(EMPTY, NOTSET);
		
		// stop and start survey again (with different survey parameters, except Survey Type).
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		
		Log.info("Stopping the Driving Survey...");			
		driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);	
		
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		
		Log.info("Starting the Driving Survey...");
		driverViewPageAction.startDrivingSurvey(EMPTY, 18);	/* Night, Calm, CloudCover<50, Standard */
		testEnvironmentAction.idleForSeconds(String.valueOf(5), NOTSET);

		expectedTagValue = DriverViewPageActions.workingDataRow.surveyTag;
		expectedModeValue = SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType;
		expectedTimeStartsWith = SURVEY_INFO_TIME_PREFIX + String.valueOf(DateUtility.getSystemHourIn12HourFormat());
		expectedSurveyStatus = SURVEY_INFO_SURVEY_STATUS_ACTIVE;
		expectedDriverInfo = SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username;
		expectedTimeElapsedStartsWith = SURVEY_INFO_ELAPSED_TIME_00;
		expectedTimeRemainingStartsWith = SURVEY_INFO_REMAINING_TIME_07;
		expectedZoomLevel = String.format(SURVEY_INFO_ZOOM_LEVEL_X, 19);
		expectedSurveyorValue = SURVEY_INFO_SURVEYOR1_ANALYZER1;

		// Verify Survey has started again.
		driverViewPageAction.clickOnHeaderInfoBox(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.verifySurveyInfoTagLabelEquals(expectedTagValue, NOTSET));			
		assertTrue(driverViewPageAction.verifySurveyInfoModeLabelEquals(expectedModeValue, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoTimeLabelStartsWith(expectedTimeStartsWith, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoSurveyStatusLabelEquals(expectedSurveyStatus, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoDriverLabelEquals(expectedDriverInfo, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoTimeElapsedLabelStartsWith(expectedTimeElapsedStartsWith, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoTimeRemainingLabelStartsWith(expectedTimeRemainingStartsWith, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoZoomLevelLabelEquals(expectedZoomLevel, NOTSET));
		assertTrue(driverViewPageAction.verifySurveyInfoSurveyorLabelEquals(expectedSurveyorValue, NOTSET));

		// Stop current simulator and start another with a different Analyzer.
		Log.info("Stopping Analyzer...");
		testEnvironmentAction.stopAnalyzer(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC1277_ActionTest_DriverViewCustUserManualSurveyNotAllowed
	 * Script: -  	
	 *	- Log into the tablet as a customer user (not Utility admin)
	 *	- Click on the Mode button
	 *	- Click on the Start Survey button
	 * Results: - 
	 *	- On the survey details popup, customer user should only see Survey Types Standard, Operator and Rapid Response. Survey Type Manual should not be present
	 * @throws Exception 
 	 **/
	@Test
	public void TC1277_ActionTest_DriverViewCustUserManualSurveyNotAllowed() throws Exception {
		Log.info("\nRunning TC1277_SimulatorTest_DriverViewCustUserManualSurveyNotAllowed");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 3);   /* sqacus -> Customer driver */
		testEnvironmentAction.startAnalyzer(EMPTY, 33); 	// start analyzer. SimAuto-Analyzer4
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
		testEnvironmentAction.startReplay(EMPTY, 33); 	// start replay db3 file.

		// start survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		// click Start Survey button.
		driverViewPageAction.getDriverViewPage().clickStartSurveyButton();
		driverViewPageAction.getDriverViewPage().waitForStartSurveyModalDialogToShow();

		// verify manual button is NOT showing.
		assertFalse(driverViewPageAction.getDriverViewPage().getManualButton().isDisplayed());

		// Stop current simulator and start another with a different Analyzer.
		testEnvironmentAction.stopAnalyzer(EMPTY, NOTSET);
	}

	/**
	 * Test Case ID: TC1385_ActionTest_DriverViewGISDataPersistsPostDisplayOptionsOff
	 * Script: -  	
	 *	- While survey is running, turn on all GIS options and select a zoom level that displays pipe data (17 to 19)
	 *	- Turn off Display options one-by-one
	 *	- Turn on Display options one-by-one
	 * Results: - 
	 *	- All boundary and pipe data should persist as options are turned off/on
	 * @throws Exception 
 	 **/
	// Ignoring the test for now, till we figure out how to find specific AssetType and BoundaryType is displaying on Map.
	@Ignore
	public void TC1385_ActionTest_DriverViewGISDataPersistsPostDisplayOptionsOff() throws Exception {
		Log.info("\nRunning TC1385_SimulatorTest_DriverViewGISDataPersistsPostDisplayOptionsOff");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 3);   /* Customer Driver user */

		testEnvironmentAction.startAnalyzer(EMPTY, 3); 	// start simulator and replay db3 file.
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);

		testEnvironmentAction.startReplay(EMPTY, 3); 	// start simulator and replay db3 file.

		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);

		driverViewPageAction.startDrivingSurvey(EMPTY, 6);	/* Day, Overcast, Calm, Standard */
		testEnvironmentAction.idleForSeconds(String.valueOf(10), NOTSET);
		
		// turn ON all Display options.
		driverViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		driverViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);

		// turn ON all GIS options.
		driverViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		driverViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);

		// Turn OFF GIS options, one by one, and check they do NOT show up in the Map.
		driverViewPageAction.turnOffMaterialTypeCastIron(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));		// TODO Look into how to check for specific AssetType NOT showing.
		
		driverViewPageAction.turnOffMaterialTypeCopper(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));		// TODO Look into how to check for specific AssetType NOT showing.
		
		driverViewPageAction.turnOffMaterialTypeOtherPlastic(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));		// TODO Look into how to check for specific AssetType NOT showing.
		
		driverViewPageAction.turnOffMaterialTypePEPlastic(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));		// TODO Look into how to check for specific AssetType NOT showing.
		
		driverViewPageAction.turnOffMaterialTypeProtectedSteel(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));		// TODO Look into how to check for specific AssetType NOT showing.
		
		driverViewPageAction.turnOffMaterialTypeUnprotectedSteel(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));		// TODO Look into how to check for specific AssetType NOT showing.
		
		driverViewPageAction.turnOffBigBoundary(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));		// TODO Look into how to check for specific BoundaryType NOT showing.
		
		driverViewPageAction.turnOffSmallBoundary(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));		// TODO Look into how to check for specific BoundaryType NOT showing.
		
		// Turn ON GIS options, one by one, and check they DO show up in the Map.
		driverViewPageAction.turnOnMaterialTypeCastIron(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));		// TODO Look into how to check for specific AssetType showing.
		
		driverViewPageAction.turnOnMaterialTypeCopper(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));		// TODO Look into how to check for specific AssetType showing.
		
		driverViewPageAction.turnOnMaterialTypeOtherPlastic(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));		// TODO Look into how to check for specific AssetType showing.
		
		driverViewPageAction.turnOnMaterialTypePEPlastic(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));		// TODO Look into how to check for specific AssetType showing.
		
		driverViewPageAction.turnOnMaterialTypeProtectedSteel(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));		// TODO Look into how to check for specific AssetType showing.
		
		driverViewPageAction.turnOnMaterialTypeUnprotectedSteel(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));		// TODO Look into how to check for specific AssetType showing.
		
		driverViewPageAction.turnOnBigBoundary(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));		// TODO Look into how to check for specific BoundaryType showing.
		
		driverViewPageAction.turnOnSmallBoundary(EMPTY, NOTSET);
		assertTrue(driverViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));		// TODO Look into how to check for specific BoundaryType showing.

		testEnvironmentAction.stopAnalyzer(EMPTY, NOTSET);
	}
}