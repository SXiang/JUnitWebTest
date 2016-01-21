package surveyor.regression.source;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.CUSUSERROLEDR;
import static surveyor.scommon.source.SurveyorConstants.REGBASEUSERNAME;
import static surveyor.scommon.source.SurveyorConstants.SQACUS;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;

import java.io.IOException;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.BrowserCommands;
import common.source.DateUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.DriverViewPage.CloudCover;
import surveyor.scommon.source.DriverViewPage.DisplaySwitchType;
import surveyor.scommon.source.DriverViewPage.GisSwitchType;
import surveyor.scommon.source.DriverViewPage.MapSwitchType;
import surveyor.scommon.source.DriverViewPage.SolarRadiation;
import surveyor.scommon.source.DriverViewPage.SurveyTime;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.DriverViewPage.Wind;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageAnalyzersPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.SurveyorBaseTest;

/*
 * **** IMPORTANT ****:
 *  As a convention for detecting Simulator based tests we are using this naming convention for Simulator tests.
 *  Any tests named TC*_SimulatorTest_* will be detected as Simulator based test and will trigger
 *  installation of Simulator pre-requisites before running the test.
 * 
 */
public class DriverViewPageTest /*extends SurveyorBaseTest*/ {

	private static final String SURVEY_INFO_SURVEYOR1_ANALYZER1 = "Surveyor: SimAuto-Surveyor1 - SimAuto-Analyzer1";
	private static final String SURVEY_INFO_SURVEYOR2_ANALYZER2 = "Surveyor: SimAuto-Surveyor2 - SimAuto-Analyzer2";
	private static final String SURVEY_INFO_SURVEY_STATUS_ACTIVE = "Survey Active";
	private static final String SURVEY_INFO_SURVEY_STATUS_INACTIVE = "Survey Inactive";
	private static final String SURVEY_INFO_SURVEYOR_PREFIX = "Surveyor: ";
	private static final String SURVEY_INFO_REMAINING_PREFIX = "Remaining: ";
	private static final String SURVEY_INFO_ELAPSED_PREFIX = "Elapsed: ";
	private static final String SURVEY_INFO_TAG_PREFIX = "Tag: ";
	private static final String SURVEY_INFO_MODE_PREFIX = "Mode: ";
	private static final String SURVEY_INFO_MODE_STANDARD = "Mode: Standard";
	private static final String SURVEY_INFO_TIME_PREFIX = "Time: ";
	private static final String SURVEY_INFO_DRIVER_PREFIX = "Driver: ";
	private static final String SURVEY_INFO_ELAPSED_TIME_00 = "Elapsed: 00:";
	private static final String SURVEY_INFO_REMAINING_TIME_07 = "Remaining: 07:";
	private static final String SURVEY_INFO_ZOOM_LEVEL_19 = "Zoom Level: 19";
	private static final String SURVEY_INFO_STABILITY_CLASS_A = "Stability Class: A";
	private static final String SURVEY_INFO_STABILITY_CLASS_B = "Stability Class: B";
	private static final String SURVEY_INFO_STABILITY_CLASS_C = "Stability Class: C";
	private static final String SURVEY_INFO_STABILITY_CLASS_D = "Stability Class: D";
	private static final String SIM_AUTO_ANALYZER1 = "SimAuto-Analyzer1";
	private static final String SIM_AUTO_SURVEYOR1 = "SimAuto-Surveyor1";
	private static final String SURVEYOR_DB3 = "Surveyor.db3";
	private static final String INSTR_WARMING_DEFN_FILE = "instr_warming.defn";
	private static final String INSTR_READY_DEFN_FILE = "instr_ready.defn";
	private static final String REPLAY_DB3_DEFN_FILE = "replay-db3.defn";

	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;
	
	private LoginPageActions loginPageAction;
	private DriverViewPageActions driverViewPageAction;
	private TestEnvironmentActions testEnvironmentAction;

	private static DriverViewPage driverViewPage;
	private static ManageCustomersPage manageCustomersPage = null;
	private static ManageUsersPage manageUsersPage = null;
	private static HomePage homePage = null;
	private static LoginPage loginPage = null;

	private TestSetup testSetup = null;
	private WebDriver driver = null;
	private String baseURL = null;
	
	// JUnit does NOT give a good way to detect which TestClass is executing.
	// So we watch for the Test method under execution and install simulator pre-reqs
	// if the test under execution is a Simulator test.
	// NOTE that all simulator tests MUST follow this naming pattern: TC*_SimulatorTest_* 
	@Rule
	public TestWatcher watcher = new TestWatcher() {
		@Override
		public void starting(Description description) {
			Log.info("Started executing " + description.getClassName() + "." + description.getMethodName() + "() test...");
			if (isExecutingSimulatorTestMethod(description.getMethodName())) {
				Log.info("Installing simulator pre-reqs. Start Analyzer and Replay DB3 script.");
				try {
					TestSetup.setupSimulatorPreReqs();
					TestSetup.startAnalyzer();
				} catch (IOException e) {
					Log.error(e.toString());
				}	
			}
		}
		
		@Override
		public void finished(Description description) {
			Log.info("Finished executing " + description.getClassName() + "." + description.getMethodName() + "() test...");
			if (isExecutingSimulatorTestMethod(description.getMethodName())) {
				Log.info("Stop Analyzer.");
				TestSetup.stopAnalyzer();
			}
		}
	};

	private static boolean isExecutingSimulatorTestMethod(String methodName) {
		String[] nameParts = methodName.split("\\_");
		if (nameParts != null && nameParts.length > 1)
		{
			if (nameParts[1].equalsIgnoreCase("SimulatorTest")) {
				return true;
			}
		}
		
		return false;
	}

	@Before
	public void beforeTestMethod() {
		try {
			testSetup = new TestSetup();
			driver = testSetup.getDriver();
			baseURL = testSetup.getBaseUrl();

			Log.info("debuggug null - driver:***:" +driver);
			driver.manage().deleteAllCookies();
			
			TestContext.INSTANCE.setTestSetup(testSetup);

			loginPage = new LoginPage(driver, baseURL, testSetup);
			PageFactory.initElements(driver,  loginPage);
			
			homePage = new HomePage(driver, baseURL, testSetup);
			PageFactory.initElements(driver,  homePage);

			driverViewPage = new DriverViewPage(driver, testSetup, baseURL);
			PageFactory.initElements(driver, driverViewPage);

			// Additional page objects.
			manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
			PageFactory.initElements(driver,  manageCustomersPage);
			
			manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
			PageFactory.initElements(driver,  manageUsersPage);
			
			loginPageAction = new LoginPageActions(driver, baseURL, testSetup);
			driverViewPageAction = new DriverViewPageActions(driver, baseURL,testSetup);
			testEnvironmentAction = new TestEnvironmentActions();

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@After
    public void afterTestMethod() {
		try {
			homePage.open();
			
			if (!driver.getTitle().equalsIgnoreCase("Login"))
				homePage.logout();
			
			driver.quit();		
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	 */
	@Ignore
	public void TC1093_SimulatorTest_VerifyInstrumentWarmUp_PicAdmin() {
		Log.info("Running TC1093_SimulatorTest_VerifyInstrumentWarmUp_PicAdmin");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		TestSetup.replayDB3Script(INSTR_WARMING_DEFN_FILE);

		driverViewPage.open();
		driverViewPage.waitForPageLoad();
		driverViewPage.waitForConnectionComplete();
		
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
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.BoundariesDistrict));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.BoundariesDistrictPlat));
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
	 */
	@Test
	public void TC1094_SimulatorTest_VerifyInstrumentReady_PicAdmin() {
		Log.info("Running TC1094_SimulatorTest_VerifyInstrumentReady_PicAdmin");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		TestSetup.replayDB3Script(INSTR_READY_DEFN_FILE);
		
		driverViewPage.open();
		driverViewPage.waitForPageLoad();
		driverViewPage.waitForConnectionComplete();
		
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
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.BoundariesDistrict));
		assertTrue(driverViewPage.isGisSwitchOff(GisSwitchType.BoundariesDistrictPlat));
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
		assertTrue(driverViewPage.isStatusButtonOpen());
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
		assertTrue(driverViewPage.getZoomLevelLabelText().equals(SURVEY_INFO_ZOOM_LEVEL_19));

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

	@Test
	public void TC256_ActionTest_DriverViewInstrumentStartWaitStopShutdown() {
		try {
			Log.info("\nRunning TC256_SimulatorTest_DriverViewInstrumentStartWaitStopShutdown");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(testSetup.getLoginUser() + ":" + testSetup.getLoginPwd(), NOTSET);
			testEnvironmentAction.startSimulator(EMPTY, 3); 	// start simulator and replay db3 file.
			driverViewPageAction.open(EMPTY,NOTSET);
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
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}

	@Test
	public void TC302_ActionTest_DriverViewUserSeesLastTagValue() {
		try {
			Log.info("\nRunning TC302_SimulatorTest_DriverViewUserSeesLastTagValue");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(testSetup.getLoginUser() + ":" + testSetup.getLoginPwd(), NOTSET);
			testEnvironmentAction.startSimulator(EMPTY, 3); 	// start simulator and replay db3 file.
			driverViewPageAction.open(EMPTY,NOTSET);
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
			
			testEnvironmentAction.stopSimulator(EMPTY, NOTSET);
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}

	// TEST needs more work on correct verification.
	@Ignore
	public void TC777_ActionTest_DriverViewFlatteningCustomerBoundaryData() {
		try {
			Log.info("\nRunning TC777_SimulatorTest_DriverViewFlatteningCustomerBoundaryData");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 9);   /* PG&E Driver */
			testEnvironmentAction.startSimulator(EMPTY, 3); 	// start simulator and replay db3 file.
			driverViewPageAction.open(EMPTY,NOTSET);
			driverViewPageAction.clickOnGisButton(EMPTY,NOTSET);
			driverViewPageAction.turnOffBoundariesDistrict(EMPTY, NOTSET);
			driverViewPageAction.turnOffBoundariesDistrictPlat(EMPTY, NOTSET);
			testEnvironmentAction.stopSimulator(EMPTY, NOTSET);

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 2);   /* Customer Supervisor */
			testEnvironmentAction.startSimulator(EMPTY, 3); 	// start simulator and replay db3 file.
			// To get a new instance of Driver view page, initialize the driver view page.
			driverViewPageAction.initializeDriverViewPage(TestContext.INSTANCE.getDriver(), 
					TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());		
			driverViewPageAction.open(EMPTY,NOTSET);
			driverViewPageAction.clickOnGisButton(EMPTY,NOTSET);
			assertTrue(driverViewPageAction.verifyGisSwitchIsOn("BoundariesDistrict",NOTSET));
			assertTrue(driverViewPageAction.verifyGisSwitchIsOn("BoundariesDistrictPlat",NOTSET));
			
			testEnvironmentAction.stopSimulator(EMPTY, NOTSET);
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}

	@Test
	public void TC1095_ActionTest_NavigateBetweenDriverViewAndHomePage() {
		try {
			Log.info("\nRunning TC1095_SimulatorTest_NavigateBetweenDriverViewAndHomePage");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 3);   /* Customer Driver */
			testEnvironmentAction.startSimulator(EMPTY, 3); 	// start simulator and replay db3 file.
			driverViewPageAction.open(EMPTY,NOTSET);

			// goto home by clicking on picarro logo on driver view page.
			driverViewPageAction.clickOnPicarroLogoButton(EMPTY, NOTSET);
			
			homePage.waitForPageLoad();
			
			// go back to driver view page using browser back button.
			BrowserCommands.goBack();
			
			// To get a new instance of Driver view page, initialize the driver view page.
			driverViewPageAction.initializeDriverViewPage(TestContext.INSTANCE.getDriver(), 
					TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());		
			
			assertTrue(driverViewPageAction.verifyPageLoaded(EMPTY,NOTSET));
			
			testEnvironmentAction.stopSimulator(EMPTY, NOTSET);
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}

	@Test
	public void TC1103_ActionTest_DriverViewStartDrivingSurveySatelliteView() {
		try {
			Log.info("\nRunning TC1103_SimulatorTest_DriverViewStartDrivingSurveySatelliteView");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 3);   /* Customer Driver */
			testEnvironmentAction.startSimulator(EMPTY, 3); 	// start simulator and replay db3 file.
			driverViewPageAction.open(EMPTY,NOTSET);

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
			// Get current hour in 24 hrs format and then convert to 12 hr format
			int hourOfDay = DateUtility.getCalendarForCurrentZone().get(Calendar.HOUR_OF_DAY);
			if (hourOfDay > 12) {
				hourOfDay = hourOfDay - 12;
			}			
			String expectedTimeStartsWith = SURVEY_INFO_TIME_PREFIX + String.valueOf(hourOfDay);
			String expectedSurveyStatus = SURVEY_INFO_SURVEY_STATUS_ACTIVE;
			String expectedDriverInfo = SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username;
			String expectedTimeElapsedStartsWith = SURVEY_INFO_ELAPSED_TIME_00;
			String expectedTimeRemainingStartsWith = SURVEY_INFO_REMAINING_TIME_07;
			String expectedZoomLevel = SURVEY_INFO_ZOOM_LEVEL_19;
			String expectedAnalyzerValue = SURVEY_INFO_SURVEY_STATUS_ACTIVE;
			String expectedSurveyorValue = SURVEY_INFO_SURVEYOR1_ANALYZER1;
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
			
			testEnvironmentAction.stopSimulator(EMPTY, NOTSET);
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}

	@Test
	public void TC1104_ActionTest_DriverViewStopDrivingSurveySatelliteView() {
		try {
			Log.info("\nRunning TC1104_SimulatorTest_DriverViewStopDrivingSurveySatelliteView");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 3);   /* Customer Driver */
			testEnvironmentAction.startSimulator(EMPTY, 3); 	// start simulator and replay db3 file.
			driverViewPageAction.open(EMPTY,NOTSET);

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
			// Get current hour in 24 hrs format and then convert to 12 hr format
			int hourOfDay = DateUtility.getCalendarForCurrentZone().get(Calendar.HOUR_OF_DAY);
			if (hourOfDay > 12) {
				hourOfDay = hourOfDay - 12;
			}			
			String expectedTimeStartsWith = SURVEY_INFO_TIME_PREFIX + String.valueOf(hourOfDay);
			String expectedSurveyStatus = SURVEY_INFO_SURVEY_STATUS_ACTIVE;
			String expectedDriverInfo = SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username;
			String expectedTimeElapsedStartsWith = SURVEY_INFO_ELAPSED_TIME_00;
			String expectedTimeRemainingStartsWith = SURVEY_INFO_REMAINING_TIME_07;
			String expectedZoomLevel = SURVEY_INFO_ZOOM_LEVEL_19;
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
			
			testEnvironmentAction.stopSimulator(EMPTY, NOTSET);
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}

	@Test
	public void TC1133_ActionTest_DriverView2SurveysSameTagSameAnalyzer8HrHistoryON() {
		try {
			Log.info("\nRunning TC1133_SimulatorTest_DriverView2SurveysSameTagSameAnalyzer8HrHistoryON");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 3);   /* Customer Driver */
			testEnvironmentAction.startSimulator(EMPTY, 3); 	// start simulator and replay db3 file.
			driverViewPageAction.open(EMPTY,NOTSET);

			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);

			// This action internally verifies that the startSurvey button is ONLY enabled after survey mode is selected.
			driverViewPageAction.startDrivingSurvey(EMPTY, 11);	/* Static Survey tag, Day, Overcast, Calm, Standard */
			
			// turn ON map view.
			driverViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			driverViewPageAction.turnOnMapView("Map", NOTSET);
			
			// perform the survey for 4 mins. (Use 1/4th the time)
			testEnvironmentAction.idleForSeconds(String.valueOf(60), NOTSET);

			// Stop driving survey.
			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
			driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);

			// start survey again.
			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
			driverViewPageAction.startDrivingSurvey(EMPTY, 11);	/* Static Survey tag, Day, Overcast, Calm, Standard */

			// turn ON 8-hour history.
			driverViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			driverViewPageAction.turnOnEightHourHistory(EMPTY, NOTSET);
			
			//-------------------
			// Verify 1.
			String expectedTagValue = DriverViewPageActions.workingDataRow.surveyTag;
			assertTrue(driverViewPageAction.verifySurveyInfoTagLabelEquals(expectedTagValue, NOTSET));			

			// Verify 1.
			// (check???) surveys conducted in the past 8-hour should be displayed on the map.
			
			testEnvironmentAction.stopSimulator(EMPTY, NOTSET);
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}

	@Test
	public void TC1134_ActionTest_DriverView2SurveysSameTagDifferentAnalyzers8HrHistoryON() {
		try {
			Log.info("\nRunning TC1134_SimulatorTest_DriverView2SurveysSameTagDifferentAnalyzers8HrHistoryON");

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 3);   /* Customer Driver */
			testEnvironmentAction.startSimulator(EMPTY, 3); 	// start simulator and replay db3 file.
			driverViewPageAction.open(EMPTY,NOTSET);

			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);

			// This action internally verifies that the startSurvey button is ONLY enabled after survey mode is selected.
			driverViewPageAction.startDrivingSurvey(EMPTY, 12);	/* Static Survey tag, Day, Overcast, Calm, Standard */
			
			// turn ON map view.
			driverViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			driverViewPageAction.turnOnMapView("Map", NOTSET);
			
			// perform the survey for 4 mins. (Use 1/4th the time)
			testEnvironmentAction.idleForSeconds(String.valueOf(60), NOTSET);

			// Stop driving survey.
			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
			driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);

			// Stop current simulator and start another with a different Analyzer.
			testEnvironmentAction.stopSimulator(EMPTY, NOTSET);

			// To get a new instance of Driver view page, initialize the driver view page.
			driverViewPageAction.initializeDriverViewPage(TestContext.INSTANCE.getDriver(), 
					TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());		

			testEnvironmentAction.startSimulator(EMPTY, 4);
			driverViewPageAction.open(EMPTY,NOTSET);

			// start survey again.
			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
			driverViewPageAction.startDrivingSurvey(EMPTY, 12);	/* Static Survey tag, Day, Overcast, Calm, Standard */

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
			
			testEnvironmentAction.stopSimulator(EMPTY, NOTSET);
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}

	@Test
	public void TC1212_ActionTest_DriverViewStandardSurveyNewDriver() {
		try {
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
			
			testEnvironmentAction.startSimulator(EMPTY, 3); 	// start simulator and replay db3 file.
			driverViewPageAction.open(EMPTY,NOTSET);
			
			// Verify that Driver view page was opened.
			assertTrue(driverViewPageAction.verifyDriverViewPageIsOpened(EMPTY, NOTSET));

			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);

			driverViewPageAction.startDrivingSurvey(EMPTY, 6);	/* Day, Overcast, Calm, Standard */
			
			// turn ON all Display options.
			driverViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			driverViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
			
			driverViewPageAction.clickOnHeaderInfoBox(EMPTY, NOTSET);
			
			//-------------------
			// Verify 
			String expectedTagValue = DriverViewPageActions.workingDataRow.surveyTag;
			String expectedModeValue = SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType;
			// Get current hour in 24 hrs format and then convert to 12 hr format
			int hourOfDay = DateUtility.getCalendarForCurrentZone().get(Calendar.HOUR_OF_DAY);
			if (hourOfDay > 12) {
				hourOfDay = hourOfDay - 12;
			}			
			String expectedTimeStartsWith = SURVEY_INFO_TIME_PREFIX + String.valueOf(hourOfDay);
			String expectedSurveyStatus = SURVEY_INFO_SURVEY_STATUS_ACTIVE;
			String expectedDriverInfo = SURVEY_INFO_DRIVER_PREFIX + userName;
			String expectedTimeElapsedStartsWith = SURVEY_INFO_ELAPSED_TIME_00;
			String expectedTimeRemainingStartsWith = SURVEY_INFO_REMAINING_TIME_07;
			String expectedZoomLevel = SURVEY_INFO_ZOOM_LEVEL_19;
			String expectedAnalyzerValue = SURVEY_INFO_SURVEY_STATUS_ACTIVE;
			String expectedSurveyorValue = SURVEY_INFO_SURVEYOR1_ANALYZER1;
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
			
			testEnvironmentAction.stopSimulator(EMPTY, NOTSET);
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.			
		}
	}

	@Test
	public void TC1213_SimulatorTest_NewDriverNavigatedToHomePage() {
		try {
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
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}

	@Test
	public void TC1215_ActionTest_CannotLoginDriverViewInvalidCredentials() {
		try {
			Log.info("\nRunning TC1215_SimulatorTest_CannotLoginDriverViewInvalidCredentials");

			testEnvironmentAction.startSimulator(EMPTY, 3); 	// start simulator and replay db3 file.
			driverViewPage.open();

			loginPage.waitForPageLoad();
			assertTrue(loginPage.checkIfAtLoginPage());
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
	
	@Test
	public void TC1232_ActionTest_DriverViewRefreshBrowser() {
		try {
			Log.info("\nRunning TC1232_SimulatorTest_DriverViewRefreshBrowser");

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 3);   /* Customer Driver */
			testEnvironmentAction.startSimulator(EMPTY, 3); 	// start simulator and replay db3 file.
			driverViewPageAction.open(EMPTY,NOTSET);

			// start survey.
			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
			driverViewPageAction.startDrivingSurvey(EMPTY, 9);	/* Day, Overcast, Strong, Standard */
			testEnvironmentAction.idleForSeconds(String.valueOf(30), NOTSET);

			driverViewPageAction.clickOnHeaderInfoBox(EMPTY, NOTSET);
			String elapsedTimeBeforeRefresh = driverViewPageAction.getDriverViewPage().getTimeElapsedLabelText().replace(SURVEY_INFO_ELAPSED_PREFIX, "");
			String remainingTimeBeforeRefresh = driverViewPageAction.getDriverViewPage().getTimeRemainingLabelText().replace(SURVEY_INFO_REMAINING_PREFIX, "");
			
			// Refresh the page in browser.
			driverViewPageAction.refreshPage(EMPTY, NOTSET);
			
			driverViewPageAction.getDriverViewPage().waitForPageLoad();
			driverViewPageAction.getDriverViewPage().waitForConnectionComplete();
			
			// Verify Survey properties.
			String expectedTagValue = DriverViewPageActions.workingDataRow.surveyTag;
			String expectedModeValue = SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType;
			// Get current hour in 24 hrs format and then convert to 12 hr format
			int hourOfDay = DateUtility.getCalendarForCurrentZone().get(Calendar.HOUR_OF_DAY);
			if (hourOfDay > 12) {
				hourOfDay = hourOfDay - 12;
			}			
			String expectedTimeStartsWith = SURVEY_INFO_TIME_PREFIX + String.valueOf(hourOfDay);
			String expectedSurveyStatus = SURVEY_INFO_SURVEY_STATUS_ACTIVE;
			String expectedDriverInfo = SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username;
			String expectedTimeElapsedStartsWith = SURVEY_INFO_ELAPSED_TIME_00;
			String expectedTimeRemainingStartsWith = SURVEY_INFO_REMAINING_TIME_07;
			String expectedZoomLevel = SURVEY_INFO_ZOOM_LEVEL_19;
			String expectedSurveyorValue = SURVEY_INFO_SURVEYOR1_ANALYZER1;
			String expectedStabilityClass = SURVEY_INFO_STABILITY_CLASS_D;
			
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
			testEnvironmentAction.stopSimulator(EMPTY, NOTSET);
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}

	@Test
	public void TC1241_ActionTest_DriverViewStartSurveyMultipleTimes() {
		try {
			Log.info("\nRunning TC1241_SimulatorTest_DriverViewStartSurveyMultipleTimes");

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 3);   /* Customer Driver */
			testEnvironmentAction.startSimulator(EMPTY, 3); 	// start simulator and replay db3 file.
			driverViewPageAction.open(EMPTY,NOTSET);

			// start survey.
			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
			driverViewPageAction.startDrivingSurvey(EMPTY, 9);	/* Day, Overcast, Strong, Standard */
			testEnvironmentAction.idleForSeconds(String.valueOf(10), NOTSET);

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
			
			testEnvironmentAction.idleForSeconds(String.valueOf(10), NOTSET);

			// Verify Survey has started.
			String expectedTagValue = DriverViewPageActions.workingDataRow.surveyTag;
			String expectedModeValue = SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType;
			// Get current hour in 24 hrs format and then convert to 12 hr format
			int hourOfDay = DateUtility.getCalendarForCurrentZone().get(Calendar.HOUR_OF_DAY);
			if (hourOfDay > 12) {
				hourOfDay = hourOfDay - 12;
			}			
			String expectedTimeStartsWith = SURVEY_INFO_TIME_PREFIX + String.valueOf(hourOfDay);
			String expectedSurveyStatus = SURVEY_INFO_SURVEY_STATUS_ACTIVE;
			String expectedDriverInfo = SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username;
			String expectedTimeElapsedStartsWith = SURVEY_INFO_ELAPSED_TIME_00;
			String expectedTimeRemainingStartsWith = SURVEY_INFO_REMAINING_TIME_07;
			String expectedZoomLevel = SURVEY_INFO_ZOOM_LEVEL_19;
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
			driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);	
			
			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
			driverViewPageAction.startDrivingSurvey(EMPTY, 10);	/* Night, Calm, CloudCover<50, Standard */
			testEnvironmentAction.idleForSeconds(String.valueOf(10), NOTSET);

			expectedTagValue = DriverViewPageActions.workingDataRow.surveyTag;
			expectedModeValue = SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType;
			// Get current hour in 24 hrs format and then convert to 12 hr format
			hourOfDay = DateUtility.getCalendarForCurrentZone().get(Calendar.HOUR_OF_DAY);
			if (hourOfDay > 12) {
				hourOfDay = hourOfDay - 12;
			}			
			expectedTimeStartsWith = SURVEY_INFO_TIME_PREFIX + String.valueOf(hourOfDay);
			expectedSurveyStatus = SURVEY_INFO_SURVEY_STATUS_ACTIVE;
			expectedDriverInfo = SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username;
			expectedTimeElapsedStartsWith = SURVEY_INFO_ELAPSED_TIME_00;
			expectedTimeRemainingStartsWith = SURVEY_INFO_REMAINING_TIME_07;
			expectedZoomLevel = SURVEY_INFO_ZOOM_LEVEL_19;
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
			testEnvironmentAction.stopSimulator(EMPTY, NOTSET);
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}

	@Test
	public void TC1277_ActionTest_DriverViewCustUserManualSurveyNotAllowed() {
		try {
			Log.info("\nRunning TC1277_SimulatorTest_DriverViewCustUserManualSurveyNotAllowed");

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 1);   /* Customer Utility admin */
			testEnvironmentAction.startSimulator(EMPTY, 3); 	// start simulator and replay db3 file.
			driverViewPageAction.open(EMPTY,NOTSET);

			// start survey.
			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
			// click Start Survey button.
			driverViewPageAction.getDriverViewPage().clickStartSurveyButton();
			driverViewPageAction.getDriverViewPage().waitForPageToLoad();
			// verify manual button is NOT showing.
			assertFalse(driverViewPageAction.getDriverViewPage().getManualButton().isDisplayed());
	
			// Stop current simulator and start another with a different Analyzer.
			testEnvironmentAction.stopSimulator(EMPTY, NOTSET);
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}

	// Ignoring the test for now, till we figure out how to find specific AssetType and BoundaryType is displaying on Map.
	@Ignore
	public void TC1385_ActionTest_DriverViewGISDataPersistsPostDisplayOptionsOff() {
		try {
			Log.info("\nRunning TC1385_SimulatorTest_DriverViewGISDataPersistsPostDisplayOptionsOff");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 3);   /* Customer Driver user */

			testEnvironmentAction.startSimulator(EMPTY, 3); 	// start simulator and replay db3 file.
			driverViewPageAction.open(EMPTY,NOTSET);

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
			
			driverViewPageAction.turnOffBoundariesDistrict(EMPTY, NOTSET);
			assertTrue(driverViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));		// TODO Look into how to check for specific BoundaryType NOT showing.
			
			driverViewPageAction.turnOffBoundariesDistrictPlat(EMPTY, NOTSET);
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
			
			driverViewPageAction.turnOnBoundariesDistrict(EMPTY, NOTSET);
			assertTrue(driverViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));		// TODO Look into how to check for specific BoundaryType showing.
			
			driverViewPageAction.turnOnBoundariesDistrictPlat(EMPTY, NOTSET);
			assertTrue(driverViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));		// TODO Look into how to check for specific BoundaryType showing.

			testEnvironmentAction.stopSimulator(EMPTY, NOTSET);
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
}

