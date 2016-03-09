package surveyor.regression.source;

import static org.junit.Assert.*;
import common.source.BrowserCommands;
import common.source.DateUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import java.util.Calendar;
import org.junit.After;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.BaseMapViewPage.GisSwitchType;
import surveyor.scommon.source.BaseMapViewPage.MapSwitchType;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;

import surveyor.scommon.actions.SurveyViewPageActions;
import surveyor.scommon.source.SurveyViewPage;

/*
 * **** IMPORTANT ****:
 *  As a convention for detecting Simulator based tests we are using this naming convention for Simulator tests.
 *  Any tests named TC*_SimulatorTest_* will be detected as Simulator based test and will trigger
 *  installation of Simulator pre-requisites before running the test.
 * 
 */
@RunWith(SurveyorTestRunner.class)
public class SurveyViewPageTest {

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
	
	private static HomePageActions homePageAction;
	private static LoginPageActions loginPageAction;
	private static DriverViewPageActions driverViewPageAction;
	private static SurveyViewPageActions surveyViewPageAction;
	private static TestEnvironmentActions testEnvironmentAction;

	private static SurveyViewPage surveyViewPage;
	private static HomePage homePage = null;
	private static LoginPage loginPage = null;

	private static TestSetup testSetup = null;
	private static WebDriver driver = null;
	private static String baseURL = null;
	
	// JUnit does NOT give a good way to detect which TestClass is executing.
	// So we watch for the Test method under execution and install simulator pre-reqs
	// if the test under execution is a Simulator test.
	// NOTE that all simulator tests MUST follow this naming pattern: TC*_SimulatorTest_* 
	@Rule
	public TestWatcher watcher = new TestWatcher() {
		@Override
		public void starting(Description description) {
			SurveyorBaseTest.reportTestStarting(description);
			TestSetup.simulatorTestStarting(description);
		}

		@Override
		public void finished(Description description) {
			SurveyorBaseTest.reportTestFinished(description.getClassName());
			TestSetup.simulatorTestFinishing(description);
		}

		@Override
		protected void failed(Throwable e, Description description) {
			SurveyorBaseTest.reportTestFailed(e);
		}

		 @Override
		 protected void succeeded(Description description) {
			 SurveyorBaseTest.reportTestSucceeded();
		}
	};

	/**
	 * Initializes the page action objects.
	 */
	private static void initializePageActions() {
		testSetup = new TestSetup();
		driver = testSetup.getDriver();
		baseURL = testSetup.getBaseUrl();
		Log.info("Deleting all cookies...***:" +driver);
		driver.manage().deleteAllCookies();
		TestContext.INSTANCE.setTestSetup(testSetup);
		loginPageAction = new LoginPageActions(driver, baseURL, testSetup);
		testEnvironmentAction = new TestEnvironmentActions();
		driverViewPageAction = new DriverViewPageActions(driver, baseURL, testSetup);
	}
	
	@BeforeClass
	public static void beforeTestClass() throws Exception {
		initializePageActions();
		
		homePageAction = new HomePageActions(driver, baseURL, testSetup);
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		// start analyzer, open driver view, start replay script
		testEnvironmentAction.startAnalyzer(EMPTY, 3); 	// start analyzer.
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY,NOTSET);
		testEnvironmentAction.startReplay(EMPTY, 3); 	// start replay db3 file.
		// start survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.startDrivingSurvey(EMPTY, 3);	/* Day, Moderate, Light, Standard */
		testEnvironmentAction.idleForSeconds(String.valueOf(15), NOTSET);
		// stop survey.
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);
		// wait for data to upload.
		testEnvironmentAction.idleForSeconds(String.valueOf(10), NOTSET);
		// stop analyzer.
		testEnvironmentAction.stopAnalyzer(EMPTY, NOTSET);
	}

	@Before
	public void beforeTestMethod() {
		try {
			initializePageActions();
			
			// Additional page actions.
			homePageAction = new HomePageActions(driver, baseURL,testSetup);
			surveyViewPageAction = new SurveyViewPageActions(driver, baseURL,testSetup);

			// Initialize page objects.
			loginPage = new LoginPage(driver, baseURL, testSetup);
			PageFactory.initElements(driver,  loginPage);
			
			homePage = new HomePage(driver, baseURL, testSetup);
			PageFactory.initElements(driver,  homePage);

			surveyViewPage = new SurveyViewPage(driver, testSetup, baseURL);
			PageFactory.initElements(driver, surveyViewPage);

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
	 * Test Case ID: TC1000_ActionTest_SurveyView_ViewIndicationsRapidResponseSurveySatelliteViewWhenNoGISLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- Indications - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 *	- - Breadcrumb and Indications are displayed on map in satellite view
	 * Results: - 
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1000_SurveyView_ViewIndicationsRapidResponseSurveySatelliteViewWhenNoGISLoaded() {
		try {
			Log.info("\nRunning TC1000_ActionTest_SurveyView_ViewIndicationsRapidResponseSurveySatelliteViewWhenNoGISLoaded ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
			//assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1001_ActionTest_SurveyView_ViewRapidResponseSurveyMapViewWhenLISAFieldNotesAreOnGISOff
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- LISA - ON
	 *	- Field Notes - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 *	- - Survey Information is displayed in map view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- - Breadcrumb, Lisa and field notes are present on map
	 * Results: - 
	 *	- - Indications and FOV are not displayed on map
	 */
	@Test
	public void TC1001_SurveyView_ViewRapidResponseSurveyMapViewWhenLISAFieldNotesAreOnGISOff() {
		try {
			Log.info("\nRunning TC1001_ActionTest_SurveyView_ViewRapidResponseSurveyMapViewWhenLISAFieldNotesAreOnGISOff ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
			surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.surveyTag, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_SURVEY_STATUS_ACTIVE, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR1_ANALYZER1, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_A, NOTSET));
			assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
			//assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1002_ActionTest_SurveyView_ViewRapidResponseSurveySatelliteViewWhenLISAFieldNotesAreOnGISOff
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- LISA - ON
	 *	- Field Notes - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- - Breadcrumb, Lisa and field notes are present on map in satellite view
	 * Results: - 
	 *	- - Indications and FOV are not displayed on map in satellite view
	 */
	@Test
	public void TC1002_SurveyView_ViewRapidResponseSurveySatelliteViewWhenLISAFieldNotesAreOnGISOff() {
		try {
			Log.info("\nRunning TC1002_ActionTest_SurveyView_ViewRapidResponseSurveySatelliteViewWhenLISAFieldNotesAreOnGISOff ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
			surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.surveyTag, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_SURVEY_STATUS_ACTIVE, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR1_ANALYZER1, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_A, NOTSET));
			assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
			//assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1003_ActionTest_SurveyView_ViewRapidResponseSurveyMapViewWhenFovIndicationOnGISOn
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- FOV - ON
	 *	- Indications - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options ON
	 *	- - Click on Map and turn Map view ON
	 *	- Survey Information is displayed in map view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- -Breadcrumb,Indications and FOV are displayed on map along with GIS data
	 * Results: - 
	 *	- - Lisa and field notes (if any) are not present on map
	 */
	@Test
	public void TC1003_SurveyView_ViewRapidResponseSurveyMapViewWhenFovIndicationOnGISOn() {
		try {
			Log.info("\nRunning TC1003_ActionTest_SurveyView_ViewRapidResponseSurveyMapViewWhenFovIndicationOnGISOn ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
			surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.surveyTag, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_SURVEY_STATUS_ACTIVE, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR1_ANALYZER1, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_A, NOTSET));
			assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
			//assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1004_ActionTest_SurveyView_ViewRapidResponseSurveySatelliteViewWhenFovIndicationOnGISOn
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- FOV - ON
	 *	- Indications - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options ON
	 *	- - Click on Map and turn Satellite view ON
	 *	- Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- -Breadcrumb,Indications and FOV are displayed on map in satellite view along with GIS data
	 * Results: - 
	 *	- - Lisa and field notes (if any) are not present on map
	 */
	@Test
	public void TC1004_SurveyView_ViewRapidResponseSurveySatelliteViewWhenFovIndicationOnGISOn() {
		try {
			Log.info("\nRunning TC1004_ActionTest_SurveyView_ViewRapidResponseSurveySatelliteViewWhenFovIndicationOnGISOn ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
			surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.surveyTag, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_SURVEY_STATUS_ACTIVE, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR1_ANALYZER1, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_A, NOTSET));
			assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
			//assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1005_ActionTest_SurveyView_ViewRapidResponseSurveyMapViewWhenAllDisplayOptionsAreOn
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All options - ON
	 *	- - Click GIS
	 *	- Assets: Protected steel, UnProtected Steel - ON
	 *	- Boundaries: OFF
	 *	- - Click on Map and turn Map view ON
	 *	- - Breadcrumb, Indications, LISA, FOV and survey information is displayed on map
	 * Results: - 
	 *	- - Boundaries not displayed on map
	 */
	@Test
	public void TC1005_SurveyView_ViewRapidResponseSurveyMapViewWhenAllDisplayOptionsAreOn() {
		try {
			Log.info("\nRunning TC1005_ActionTest_SurveyView_ViewRapidResponseSurveyMapViewWhenAllDisplayOptionsAreOn ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
			surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllAssets(EMPTY, NOTSET);
			surveyViewPageAction.turnOnMaterialTypeOtherPlastic(EMPTY, NOTSET);
			surveyViewPageAction.turnOnMaterialTypeCopper(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
			//assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1006_ActionTest_SurveyView_ViewRapidResponseSurveySatelliteViewWhenAllDisplayOptionsAreONOtherPlasticCopperAssetsONBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All options - ON
	 *	- - Click GIS
	 *	- Assets: Other Plastic, Copper - ON
	 *	- Boundaries: OFF
	 *	- - Click on Map and turn Satellite view ON
	 *	- - Breadcrumb, Indications, LISA, FOV and survey information is displayed on map in satellite view
	 * Results: - 
	 *	- - Boundaries not displayed on map in satellite view
	 */
	@Test
	public void TC1006_SurveyView_ViewRapidResponseSurveySatelliteViewWhenAllDisplayOptionsAreONOtherPlasticCopperAssetsONBoundariesOFF() {
		try {
			Log.info("\nRunning TC1006_ActionTest_SurveyView_ViewRapidResponseSurveySatelliteViewWhenAllDisplayOptionsAreONOtherPlasticCopperAssetsONBoundariesOFF ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
			surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllAssets(EMPTY, NOTSET);
			surveyViewPageAction.turnOnMaterialTypeProtectedSteel(EMPTY, NOTSET);
			surveyViewPageAction.turnOnMaterialTypeUnprotectedSteel(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
			//assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1007_ActionTest_SurveyView_ViewRapidResponseSurveyMapViewWhenLISAONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- Lisa - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 *	- - Breadcrumb and Lisa are displayed on map
	 * Results: - 
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1007_SurveyView_ViewRapidResponseSurveyMapViewWhenLISAONAssetsBoundariesOFF() {
		try {
			Log.info("\nRunning TC1007_ActionTest_SurveyView_ViewRapidResponseSurveyMapViewWhenLISAONAssetsBoundariesOFF ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1008_ActionTest_SurveyView_ViewRapidResponseSurveySatelliteViewWhenLISAONAssetsBoundariesOFF
	 * Script: -  	
	 *	- -On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- Lisa - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 *	- - Breadcrumb and Lisa are displayed on map in satellite view
	 * Results: - 
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1008_SurveyView_ViewRapidResponseSurveySatelliteViewWhenLISAONAssetsBoundariesOFF() {
		try {
			Log.info("\nRunning TC1008_ActionTest_SurveyView_ViewRapidResponseSurveySatelliteViewWhenLISAONAssetsBoundariesOFF ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1009_ActionTest_SurveyView_ViewRapidResponseSurveyMapViewWhenFOVONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- FOV - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 *	- - Breadcrumb and FOV are displayed on map
	 * Results: - 
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1009_SurveyView_ViewRapidResponseSurveyMapViewWhenFOVONAssetsBoundariesOFF() {
		try {
			Log.info("\nRunning TC1009_ActionTest_SurveyView_ViewRapidResponseSurveyMapViewWhenFOVONAssetsBoundariesOFF ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1010_ActionTest_SurveyView_ViewRapidResponseSurveySatelliteViewWhenFOVONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- FOV - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 *	- - Breadcrumb and FOV are displayed on map in satellite view
	 * Results: - 
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1010_SurveyView_ViewRapidResponseSurveySatelliteViewWhenFOVONAssetsBoundariesOFF() {
		try {
			Log.info("\nRunning TC1010_ActionTest_SurveyView_ViewRapidResponseSurveySatelliteViewWhenFOVONAssetsBoundariesOFF ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1011_ActionTest_SurveyView_ViewRapidResponseSurveyMapViewWhenFieldNotesONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- Field Notes - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 *	- - Breadcrumb and Field Notes are displayed on map
	 * Results: - 
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1011_SurveyView_ViewRapidResponseSurveyMapViewWhenFieldNotesONAssetsBoundariesOFF() {
		try {
			Log.info("\nRunning TC1011_ActionTest_SurveyView_ViewRapidResponseSurveyMapViewWhenFieldNotesONAssetsBoundariesOFF ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1012_ActionTest_SurveyView_ViewRapidResponseSurveySatellliteViewWhenFieldNotesONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- Field Notes - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 *	- - Breadcrumb and Field Notes are displayed on map in satellite view
	 * Results: - 
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1012_SurveyView_ViewRapidResponseSurveySatellliteViewWhenFieldNotesONAssetsBoundariesOFF() {
		try {
			Log.info("\nRunning TC1012_ActionTest_SurveyView_ViewRapidResponseSurveySatellliteViewWhenFieldNotesONAssetsBoundariesOFF ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1013_ActionTest_SurveyView_ViewRapidResponseSurveyMapViewWhenIsotopicAnalysisONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- Isotopic Analysis - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1013_SurveyView_ViewRapidResponseSurveyMapViewWhenIsotopicAnalysisONAssetsBoundariesOFF() {
		try {
			Log.info("\nRunning TC1013_ActionTest_SurveyView_ViewRapidResponseSurveyMapViewWhenIsotopicAnalysisONAssetsBoundariesOFF ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnIsotopicAnalysis(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1014_ActionTest_SurveyView_ViewRapidResponseSurveySatelliteViewWhenIsotopicAnalysisONAssetsBoundariesOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- Isotopic Analysis - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 * Results: - 
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1014_SurveyView_ViewRapidResponseSurveySatelliteViewWhenIsotopicAnalysisONAssetsBoundariesOFF() {
		try {
			Log.info("\nRunning TC1014_ActionTest_SurveyView_ViewRapidResponseSurveySatelliteViewWhenIsotopicAnalysisONAssetsBoundariesOFF ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnIsotopicAnalysis(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1016_ActionTest_SurveyView_ViewManualSurveySatelliteViewWhenGISDisplayOptionsAreOFF
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All OFF
	 *	- - Map View: Satellite
	 *	- - GIS: All OFF
	 *	- - User should see only Breadcrumb in satellite view
	 * Results: - 
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1016_SurveyView_ViewManualSurveySatelliteViewWhenGISDisplayOptionsAreOFF() {
		try {
			Log.info("\nRunning TC1016_ActionTest_SurveyView_ViewManualSurveySatelliteViewWhenGISDisplayOptionsAreOFF ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1017_ActionTest_SurveyView_ViewIndicationsDataManualSurveySatelliteViewWhenGISDataLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: Indication ON
	 *	- All other options OFF
	 *	- - Map View: Satellite
	 *	- - GIS: All ON
	 * Results: - 
	 *	- - All other survey data is not displayed
	 */
	@Test
	public void TC1017_SurveyView_ViewIndicationsDataManualSurveySatelliteViewWhenGISDataLoaded() {
		try {
			Log.info("\nRunning TC1017_ActionTest_SurveyView_ViewIndicationsDataManualSurveySatelliteViewWhenGISDataLoaded ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
			surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1018_ActionTest_SurveyView_ViewFOVDataManualSurveySatellliteViewWhenGISDataLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: FOV ON
	 *	- All other options OFF
	 *	- - Map View: Satellite
	 *	- - GIS: All ON
	 * Results: - 
	 *	- - All other survey data is not displayed
	 */
	@Test
	public void TC1018_SurveyView_ViewFOVDataManualSurveySatellliteViewWhenGISDataLoaded() {
		try {
			Log.info("\nRunning TC1018_ActionTest_SurveyView_ViewFOVDataManualSurveySatellliteViewWhenGISDataLoaded ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
			surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1019_ActionTest_SurveyView_ViewFOVIndicationsLisasDataManualSurveySatelliteViewWhenAssetsAreLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All ON
	 *	- - Map View: Satellite
	 *	- - GIS: Assets ON
	 *	- Boundaries OFF
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 * Results: - 
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map in satellite view
	 */
	@Test
	public void TC1019_SurveyView_ViewFOVIndicationsLisasDataManualSurveySatelliteViewWhenAssetsAreLoaded() {
		try {
			Log.info("\nRunning TC1019_ActionTest_SurveyView_ViewFOVIndicationsLisasDataManualSurveySatelliteViewWhenAssetsAreLoaded ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
			surveyViewPageAction.turnOnAllAssets(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllBoundaries(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.surveyTag, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_SURVEY_STATUS_ACTIVE, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR1_ANALYZER1, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_A, NOTSET));
			assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
			//assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1020_ActionTest_SurveyView_ViewFOVIndicationsLisasDataManualSurveySatelliteViewWhenBoundariesLoadedIntoGIS
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: All ON
	 *	- - Map View: Satellite
	 *	- - GIS: Assets OFF
	 *	- Boundaries ON
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Stability Class,Surveyor and analyzer info,Start Time, End Time
	 *	- - User should see Breadcrumb, FOV, Indications, LISA, Capture results (if any) and Field Notes (if any) on map in satellite view
	 * Results: - 
	 *	- - Only boundaries are displayed. Assets are not displayed
	 */
	@Test
	public void TC1020_SurveyView_ViewFOVIndicationsLisasDataManualSurveySatelliteViewWhenBoundariesLoadedIntoGIS() {
		try {
			Log.info("\nRunning TC1020_ActionTest_SurveyView_ViewFOVIndicationsLisasDataManualSurveySatelliteViewWhenBoundariesLoadedIntoGIS ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllAssets(EMPTY, NOTSET);
			surveyViewPageAction.turnOnAllBoundaries(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.surveyTag, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_SURVEY_STATUS_ACTIVE, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR1_ANALYZER1, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_A, NOTSET));
			assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
			//assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1021_ActionTest_SurveyView_ViewIndicationsLisasDataManualSurveySatelliteViewWhenGISDataLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Display: Indications ON
	 *	- LISA ON
	 *	- All other options OFF
	 *	- - Map View: Satellite
	 *	- - GIS: All ON
	 * Results: - 
	 *	- - All other survey data is not displayed
	 */
	@Test
	public void TC1021_SurveyView_ViewIndicationsLisasDataManualSurveySatelliteViewWhenGISDataLoaded() {
		try {
			Log.info("\nRunning TC1021_ActionTest_SurveyView_ViewIndicationsLisasDataManualSurveySatelliteViewWhenGISDataLoaded ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
			surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
			surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1022_ActionTest_SurveyView_ViewIndicationsManualSurveySatelliteViewWhenNoGISLoaded
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- Indications - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 *	- - Breadcrumb and Indications are displayed on map in satellite view
	 * Results: - 
	 *	- - All other survey data, assets and boundaries are not displayed
	 */
	@Test
	public void TC1022_SurveyView_ViewIndicationsManualSurveySatelliteViewWhenNoGISLoaded() {
		try {
			Log.info("\nRunning TC1022_ActionTest_SurveyView_ViewIndicationsManualSurveySatelliteViewWhenNoGISLoaded ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
			//assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyAssetIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyBoundariesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1023_ActionTest_SurveyView_ViewManualSurveyMapViewWhenLISAFieldNotesAreOnGISOff
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- LISA - ON
	 *	- Field Notes - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 *	- - Survey Information is displayed in map view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- - Breadcrumb, Lisa and field notes are present on map
	 * Results: - 
	 *	- - Indications and FOV are not displayed on map
	 */
	@Test
	public void TC1023_SurveyView_ViewManualSurveyMapViewWhenLISAFieldNotesAreOnGISOff() {
		try {
			Log.info("\nRunning TC1023_ActionTest_SurveyView_ViewManualSurveyMapViewWhenLISAFieldNotesAreOnGISOff ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
			surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.surveyTag, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_SURVEY_STATUS_ACTIVE, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR1_ANALYZER1, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_A, NOTSET));
			assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
			//assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1024_ActionTest_SurveyView_ViewManualSurveySatelliteViewWhenLISAFieldNotesAreOnGISOff
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- LISA - ON
	 *	- Field Notes - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Satellite view ON
	 *	- - Survey Information is displayed in satellite view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- - Breadcrumb, Lisa and field notes are present on map in satellite view
	 * Results: - 
	 *	- - Indications and FOV are not displayed on map in satellite view
	 */
	@Test
	public void TC1024_SurveyView_ViewManualSurveySatelliteViewWhenLISAFieldNotesAreOnGISOff() {
		try {
			Log.info("\nRunning TC1024_ActionTest_SurveyView_ViewManualSurveySatelliteViewWhenLISAFieldNotesAreOnGISOff ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
			surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnSatelliteView(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.surveyTag, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_SURVEY_STATUS_ACTIVE, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR1_ANALYZER1, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_A, NOTSET));
			assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
			//assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
 
	/**
	 * Test Case ID: TC1025_ActionTest_SurveyView_ViewManualSurveyMapViewWhenFovIndicationOnGISOn
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- FOV - ON
	 *	- Indications - ON
	 *	- All other options - OFF
	 *	- - Click GIS
	 *	- All options ON
	 *	- - Click on Map and turn Map view ON
	 *	- Survey Information is displayed in map view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- -Breadcrumb,Indications and FOV are displayed on map along with GIS data
	 * Results: - 
	 *	- - Lisa and field notes (if any) are not present on map
	 */
	@Test
	public void TC1025_SurveyView_ViewManualSurveyMapViewWhenFovIndicationOnGISOn() {
		try {
			Log.info("\nRunning TC1025_ActionTest_SurveyView_ViewManualSurveyMapViewWhenFovIndicationOnGISOn ...");
			
			loginPageAction.open(EMPTY, NOTSET);
			loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
			homePageAction.clickOnFirstMatchingDrivingSurvey(DriverViewPageActions.workingDataRow.surveyTag, NOTSET);
			assertTrue(surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET));
			surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
			surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnFOVs(EMPTY, NOTSET);
			surveyViewPageAction.turnOnIndications(EMPTY, NOTSET);
			surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
			surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnAllAssetsAndBoundaries(EMPTY, NOTSET);
			surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
			surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
			assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(DriverViewPageActions.workingDataRow.surveyTag, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + DriverViewPageActions.workingDataRow.surveyType, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + LoginPageActions.workingDataRow.username, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelStartsWith(SURVEY_INFO_ELAPSED_TIME_00, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelStartsWith(SURVEY_INFO_REMAINING_TIME_07, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_SURVEY_STATUS_ACTIVE, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR1_ANALYZER1, NOTSET));
			assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_A, NOTSET));
			assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
			//assertTrue(surveyViewPageAction.verifyIndicationsIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFOVIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyAssetIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyBoundariesIsShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyLISAIsNotShownOnMap(EMPTY, NOTSET));
			assertTrue(surveyViewPageAction.verifyFieldNotesIsNotShownOnMap(EMPTY, NOTSET));
		} catch (Exception e) {
			Log.error(e.toString());
			assertTrue(false);	// fail test on exception.
		}
	}
}