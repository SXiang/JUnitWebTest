package surveyor.regression.source;

import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.BrowserCommands;
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
import surveyor.scommon.source.SurveyorBaseTest;

/*
 * **** IMPORTANT ****:
 *  As a convention for detecting Simulator based tests we are using this naming convention for Simulator tests.
 *  Any tests named TC*_SimulatorTest_* will be detected as Simulator based test and will trigger
 *  installation of Simulator pre-requisites before running the test.
 * 
 */
public class DriverViewPageTest extends SurveyorBaseTest {

	private static final String SIM_AUTO_ANALYZER1 = "SimAuto-Analyzer1";
	private static final String SIM_AUTO_SURVEYOR1 = "SimAuto-Surveyor1";
	private static DriverViewPage driverViewPage;
	private static final String SURVEYOR_DB3 = "Surveyor.db3";
	private static final String INSTR_WARMING_DEFN_FILE = "instr_warming.defn";
	private static final String INSTR_READY_DEFN_FILE = "instr_ready.defn";
	private static final String REPLAY_DB3_DEFN_FILE = "replay-db3.defn";

	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;
	private LoginPageActions loginPageAction;
	private DriverViewPageActions driverViewPageAction;
	private TestEnvironmentActions testEnvironmentAction;

	public DriverViewPageTest() {
		driverViewPage = new DriverViewPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver, driverViewPage);
		
		WebDriver webDriver = TestContext.INSTANCE.getDriver();
		TestSetup testSetup = TestContext.INSTANCE.getTestSetup();
		String baseURL = testSetup.getBaseUrl();
		loginPageAction = new LoginPageActions(webDriver, baseURL, testSetup);
		driverViewPageAction = new DriverViewPageActions(webDriver, baseURL,testSetup);
		testEnvironmentAction = new TestEnvironmentActions();
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
		assertTrue(driverViewPage.getSurveyModeLabelText().equals("Mode: Standard"));
		
		Log.info("SURVEY ACTIVE:[" + driverViewPage.getSurveyStatusLabelText() + "]");
		assertTrue(driverViewPage.getSurveyStatusLabelText().equals("Survey Active"));
		
		Log.info("STABILITY CLASS:[" + driverViewPage.getStabilityClassLabelText() + "]");
		assertTrue(driverViewPage.getStabilityClassLabelText().equals("Stability Class: C"));
		
		Log.info("ELAPSED:[" + driverViewPage.getTimeElapsedLabelText() + "]");
		assertTrue(driverViewPage.getTimeElapsedLabelText().startsWith("Elapsed: 00:"));
		
		Log.info("REMAINING:[" + driverViewPage.getTimeRemainingLabelText() + "]");
		assertTrue(driverViewPage.getTimeRemainingLabelText().startsWith("Remaining: 0"));
		
		Log.info("SURVEYOR:[" + driverViewPage.getSurveyorLabelText() + "]");
		assertTrue(driverViewPage.getSurveyorLabelText().equals("Surveyor: " + SIM_AUTO_SURVEYOR1 + " - " + SIM_AUTO_ANALYZER1));
		
		Log.info("ZOOM LEVEL:[" + driverViewPage.getZoomLevelLabelText() + "]");
		assertTrue(driverViewPage.getZoomLevelLabelText().equals("Zoom Level: 19"));

		Log.info("TAG:[" + driverViewPage.getTagLabelText() + "]");
		Log.info("Tag value is: " + tag);
		assertTrue(driverViewPage.getTagLabelText().equals("Tag: " + tag));

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
		assertTrue(driverViewPage.getSurveyStatusLabelText().equals("Survey Inactive"));
	}

	@Test
	public void TC256_SimulatorTest_DriverViewInstrumentStartWaitStopShutdown() {
		try {
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(testSetup.getLoginUser() + ":" + testSetup.getLoginPwd(), NOTSET);
			testEnvironmentAction.startSimulator(EMPTY, 1);
			driverViewPageAction.open(EMPTY,NOTSET);
			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);
			
			driverViewPageAction.startDrivingSurvey(EMPTY, 3);
			
			// Intentional sleep for 3 minutes as per test case steps.
			TestContext.INSTANCE.stayIdle(3 * 60);
			
			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);
			driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);
			
			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);
			driverViewPageAction.clickOnShutdownButton(EMPTY,NOTSET);
			
			// Verify analyzer is shutdown.
			assertTrue(testEnvironmentAction.verifyAnalyzerIsShutdown(EMPTY, NOTSET));
			
		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

	@Test
	public void TC302_SimulatorTest_DriverViewUserSeesLastTagValue() {
		try {
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(testSetup.getLoginUser() + ":" + testSetup.getLoginPwd(), NOTSET);
			testEnvironmentAction.startSimulator(EMPTY, 1);
			driverViewPageAction.open(EMPTY,NOTSET);
			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);
			
			driverViewPageAction.startDrivingSurvey(EMPTY, 3);
			
			// Run the survey for 50 seconds.
			TestContext.INSTANCE.stayIdle(50);
			
			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);
			driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);
			
			driverViewPageAction.clickOnModeButton(EMPTY,NOTSET);
			driverViewPageAction.openStartSurveyModalDialog(EMPTY,NOTSET);
			
			assertTrue(driverViewPageAction.verifySurveyTagInStartSurveyDialogEquals(driverViewPageAction.workingDataRow.surveyTag, NOTSET));
			assertTrue(driverViewPageAction.verifyStartSurveyButtonFromSurveyDialogIsDisabled(EMPTY, NOTSET));
			
			testEnvironmentAction.stopSimulator(EMPTY, NOTSET);
		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

	@Test
	public void TC777_SimulatorTest_DriverViewFlatteningCustomerBoundaryData() {
		try {
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 3);   /* Customer Driver */
			testEnvironmentAction.startSimulator(EMPTY, 1);
			driverViewPageAction.open(EMPTY,NOTSET);
			driverViewPageAction.clickOnGisButton(EMPTY,NOTSET);
			driverViewPageAction.turnOffBoundariesDistrict(EMPTY, NOTSET);
			driverViewPageAction.turnOffBoundariesDistrictPlat(EMPTY, NOTSET);

			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 2);   /* Customer Supervisor */
			driverViewPageAction.open(EMPTY,NOTSET);
			driverViewPageAction.clickOnGisButton(EMPTY,NOTSET);
			assertTrue(driverViewPageAction.verifyGisSwitchIsOn("BoundariesDistrict",NOTSET));
			assertTrue(driverViewPageAction.verifyGisSwitchIsOn("BoundariesDistrictPlat",NOTSET));
			
			testEnvironmentAction.stopSimulator(EMPTY, NOTSET);
		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

	@Test
	public void TC1095_SimulatorTest_NavigateBetweenDriverViewAndHomePage() {
		try {
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 3);   /* Customer Driver */
			testEnvironmentAction.startSimulator(EMPTY, 1);
			driverViewPageAction.open(EMPTY,NOTSET);

			// goto home by clicking on picarro logo on driver view page.
			driverViewPageAction.clickOnPicarroLogoButton(EMPTY, NOTSET);
			
			// go back to driver view page using browser back button.
			BrowserCommands.goBack();
			
			assertTrue(driverViewPageAction.verifyPageLoaded(EMPTY,NOTSET));
			
			testEnvironmentAction.stopSimulator(EMPTY, NOTSET);
		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

	@Test
	public void TC1103_SimulatorTest_DriverViewStartDrivingSurveySatelliteView() {
		try {
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 3);   /* Customer Driver */
			testEnvironmentAction.startSimulator(EMPTY, 1);
			driverViewPageAction.open(EMPTY,NOTSET);

			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
			
			// Verify 1. and 2.
			// This action internally verifies that the startSurvey button is ONLY enabled after survey mode is selected.
			driverViewPageAction.startDrivingSurvey(EMPTY, 4);	
			
			driverViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			driverViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
			
			// Verify 3.
			driverViewPageAction.verifyMapSwitchOn("Satellite", NOTSET);

			// Verify 3.
			String expectedTagValue = null;
			String expectedModeValue = null;
			String expectedTimeStartsWith = null;
			String expectedSurveyStatus = "Active";
			String expectedDriverInfo = null;
			String expectedTimeElapsedStartsWith = null;
			String expectedTimeRemainingStartsWith = null;
			String expectedZoomLevel = null;
			String expectedAnalyzerValue = null;
			String expectedSurveyorValue = null;
			String expectedStabilityClass = "A";
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
			assertTrue(driverViewPageAction.verifyCrossHairIconIsShownOnMap("Red", NOTSET));
			assertTrue(driverViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
			
			// Verify 5.
			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
			assertTrue(driverViewPageAction.verifyStopDrivingSurveyButtonIsEnabled(EMPTY, NOTSET));
			assertTrue(driverViewPageAction.verifyStartIsotopicCaptureButtonIsEnabled(EMPTY, NOTSET));
			// TODO: ReferenceBottleMeasurement button (check???)
			assertTrue(driverViewPageAction.verifySystemShutdownButtonIsDisabled(EMPTY, NOTSET));
			assertTrue(driverViewPageAction.verifySystemShutdownButtonIsNotDisplayed(EMPTY, NOTSET));
			
			testEnvironmentAction.stopSimulator(EMPTY, NOTSET);
		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

	@Test
	public void TC1104_SimulatorTest_DriverViewStopDrivingSurveySatelliteView() {
		try {
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 3);   /* Customer Driver */
			testEnvironmentAction.startSimulator(EMPTY, 1);
			driverViewPageAction.open(EMPTY,NOTSET);

			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);

			// Verify 1.
			driverViewPageAction.verifyMapSwitchOn("Satellite", NOTSET);

			// Verify 3. and 4.
			// This action internally verifies that the startSurvey button is ONLY enabled after survey mode is selected.
			driverViewPageAction.startDrivingSurvey(EMPTY, 5);  /* Day, Strong, Light, Operator */	
			
			// Verify 1.
			String expectedTagValue = null;
			String expectedModeValue = null;
			String expectedTimeStartsWith = null;
			String expectedSurveyStatus = "Active";
			String expectedDriverInfo = null;
			String expectedTimeElapsedStartsWith = null;
			String expectedTimeRemainingStartsWith = null;
			String expectedZoomLevel = null;
			String expectedAnalyzerValue = null;
			String expectedSurveyorValue = null;
			String expectedStabilityClass = "B";
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
			assertTrue(driverViewPageAction.verifyCrossHairIconIsShownOnMap("Red", NOTSET));
			assertTrue(driverViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
			
			// Stop driving survey.
			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
			driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);
			
			// Verify 6.
			assertTrue(driverViewPageAction.verifyStopDrivingSurveyButtonIsEnabled(EMPTY, NOTSET));
			assertTrue(driverViewPageAction.verifyStartIsotopicCaptureButtonIsEnabled(EMPTY, NOTSET));
			// TODO: ReferenceBottleMeasurement button (check???)
			assertTrue(driverViewPageAction.verifySystemShutdownButtonIsDisabled(EMPTY, NOTSET));
			assertTrue(driverViewPageAction.verifySystemShutdownButtonIsNotDisplayed(EMPTY, NOTSET));

			// Verify 7.
			expectedSurveyStatus = "Inactive";
			assertTrue(driverViewPageAction.verifySurveyInfoSurveyStatusLabelEquals(expectedSurveyStatus, NOTSET));
			
			// Verify 7.
			// (check????) ONLY car icon is present on the map. For this what negative conditions can we check.
			// That is, what can we check as NOT shown on the map.
			
			// Verify 7.
			assertTrue(driverViewPageAction.verifyCrossHairIconIsShownOnMap("Gray", NOTSET));
			assertTrue(driverViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));			
			
			testEnvironmentAction.stopSimulator(EMPTY, NOTSET);
		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

	@Test
	public void TC1133_SimulatorTest_DriverView2SurveysSameTagSameAnalyzer8HrHistoryON() {
		try {
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 3);   /* Customer Driver */
			testEnvironmentAction.startSimulator(EMPTY, 1);
			driverViewPageAction.open(EMPTY,NOTSET);

			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);

			// This action internally verifies that the startSurvey button is ONLY enabled after survey mode is selected.
			driverViewPageAction.startDrivingSurvey(EMPTY, 6);	/* Day, Overcast, Calm, Standard */
			
			// turn ON map view.
			driverViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			driverViewPageAction.turnOnMapView("Map", NOTSET);
			
			// perform the survey for 4 mins.
			TestContext.INSTANCE.stayIdle(4 * 60);

			// Stop driving survey.
			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
			driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);

			// start survey again.
			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
			driverViewPageAction.startDrivingSurvey(EMPTY, 6);	/* Day, Overcast, Calm, Standard */

			// turn ON 8-hour history.
			driverViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			driverViewPageAction.turnOnEightHourHistory(EMPTY, NOTSET);
			
			//-------------------
			// Verify 1.
			String expectedTagValue = null;
			assertTrue(driverViewPageAction.verifySurveyInfoTagLabelEquals(expectedTagValue, NOTSET));			

			// Verify 1.
			// (check???) surveys conducted in the past 8-hour should be displayed on the map.
			
			testEnvironmentAction.stopSimulator(EMPTY, NOTSET);
		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

	@Test
	public void TC1133_SimulatorTest_DriverView2SurveysSameTagDifferentAnalyzers8HrHistoryON() {
		try {
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 3);   /* Customer Driver */
			testEnvironmentAction.startSimulator(EMPTY, 1);
			driverViewPageAction.open(EMPTY,NOTSET);

			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);

			// This action internally verifies that the startSurvey button is ONLY enabled after survey mode is selected.
			driverViewPageAction.startDrivingSurvey(EMPTY, 6);	/* Day, Overcast, Calm, Standard */
			
			// turn ON map view.
			driverViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			driverViewPageAction.turnOnMapView("Map", NOTSET);
			
			// perform the survey for 4 mins.
			TestContext.INSTANCE.stayIdle(4 * 60);

			// Stop driving survey.
			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
			driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);

			// Stop current simulator and start another with a different Analyzer.
			testEnvironmentAction.stopSimulator(EMPTY, NOTSET);
			testEnvironmentAction.startSimulator(EMPTY, 4);

			driverViewPageAction.open(EMPTY,NOTSET);

			// start survey again.
			driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
			driverViewPageAction.startDrivingSurvey(EMPTY, 6);	/* Day, Overcast, Calm, Standard */

			// turn ON map view.
			driverViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			driverViewPageAction.turnOnMapView("Map", NOTSET);

			// turn ON 8-hour history.
			driverViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			driverViewPageAction.turnOnEightHourHistory(EMPTY, NOTSET);
			
			//-------------------
			// Verify 1.
			String expectedAnalyzerValue = null;
			assertTrue(driverViewPageAction.verifySurveyInfoAnalyzerLabelEquals(expectedAnalyzerValue, NOTSET));			

			// Verify 1.
			// (check???) surveys conducted in the past 8-hour should NOT be displayed on the map.
			
			testEnvironmentAction.stopSimulator(EMPTY, NOTSET);
		} catch (Exception e) {
			Log.error(e.toString());
		}
	}
}

