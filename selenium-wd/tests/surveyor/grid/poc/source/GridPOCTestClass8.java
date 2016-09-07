package surveyor.grid.poc.source;

import static org.junit.Assert.*;

import common.source.ExceptionUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.junit.Test;
import org.junit.rules.TestWatcher;

import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.PageActionsStore;
import surveyor.scommon.actions.SurveyViewPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.SurveyViewPage;
import surveyor.scommon.source.SurveyorBaseTest;
import java.io.IOException;
import java.net.UnknownHostException;

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
public class GridPOCTestClass8 {

	protected static final String SAMPLE_SURVEY_FIELD_NOTES1 = "Ref Gas Canceled";
	protected static final String SURVEY_INFO_SURVEYOR = "Surveyor: Software Car";
	protected static final String SURVEY_INFO_ANALYZER = "Analyzer: FDDS2038";
	protected static final String SURVEY_INFO_ASSESSMENT_SURVEYOR = "Surveyor: Picarro Production #10";
	protected static final String SURVEY_INFO_ASSESSMENT_ANALYZER = "Analyzer: FEDS2055";
	protected static final String SURVEY_INFO_MODE_PREFIX = "Mode: ";
	protected static final String SURVEY_INFO_DRIVER_PREFIX = "Driver: ";
	protected static final String SURVEY_INFO_STABILITY_CLASS_B = "Stability Class: B";
	protected static final String TEST_SURVEY_STANDARD1_ID = "c8782024-cc65-b53a-5317-39d4b4f4731f";
	protected static final String TEST_SURVEY_STANDARD1_TAG = "stnd-pic";
	protected static final String TEST_SURVEY_STANDARD1_TYPE = "Standard";
	protected static final String TEST_SURVEY_STANDARD1_USERNAME = "Administrator";
	protected static final String TEST_SURVEY_ASSESSMENT1_ID = "028f8fb7-8ba3-a44b-f778-39d626cd322b";
	protected static final String TEST_SURVEY_ASSESSMENT1_TAG = "assessment";
	protected static final String TEST_SURVEY_ASSESSMENT1_TYPE = "Assessment";
	protected static final String TEST_SURVEY_ASSESSMENT1_USERNAME = "driver1@picarro.com";
	protected static final String TEST_SURVEY_RAPID_RESP_ID = "04B2FEDA-EE18-A49E-3208-39D4B50F48FE";
	protected static final String TEST_SURVEY_RAPID_RESP_TAG = "rr-pic";
	protected static final String TEST_SURVEY_RAPID_RESP_TYPE = "Rapid Response";
	protected static final String TEST_SURVEY_RAPID_RESP_USERNAME = "Administrator";
	protected static final String TEST_SURVEY_MANUAL1_ID = "4D7BF7AC-6CC2-9B18-1894-39D4B546927B";
	protected static final String TEST_SURVEY_MANUAL1_TAG = "man-pic";
	protected static final String TEST_SURVEY_MANUAL1_TYPE = "Manual";
	protected static final String TEST_SURVEY_MANUAL1_USERNAME = "Administrator";
	// Use this Manual survey for verifying Field notes are present.
	protected static final String TEST_SURVEY_MANUAL2_ID = "2278D26F-8D69-B070-56FD-39D4B552F8F2";
	protected static final String TEST_SURVEY_MANUAL2_TAG = "man-pic";
	protected static final String TEST_SURVEY_MANUAL2_TYPE = "Manual";
	protected static final String TEST_SURVEY_MANUAL2_USERNAME = "Administrator";
	protected static final String TEST_SURVEY_OPERATOR1_ID = "1556ac85-a125-0347-2a02-39d4b529c6bd";
	protected static final String TEST_SURVEY_OPERATOR1_TAG = "op-pic";
	protected static final String TEST_SURVEY_OPERATOR1_TYPE = "Operator";
	protected static final String TEST_SURVEY_OPERATOR1_USERNAME = "Administrator";
	
	// Ethane specific constants.
	protected static final String ETHANE_SAMPLE_FIELD_NOTES = "3.6 big leak location";
	protected static final String ETHANE_SURVEY_INFO_SURVEYOR = "Surveyor: Green-Escape";
	protected static final String ETHANE_SURVEY_INFO_ANALYZER = "Analyzer: FEDS2059";
	protected static final String TEST_ETHANE_SURVEY_STANDARD_ID = "183fcb51-e968-bb81-0c98-39d666857ae7";
	protected static final String TEST_ETHANE_SURVEY_STANDARD_TAG = "ethane night sf b1";
	protected static final String TEST_ETHANE_SURVEY_STANDARD_TYPE = "Standard";
	protected static final String TEST_ETHANE_SURVEY_STANDARD_USERNAME = "picscdr@picarro.com";

	protected static final int USER_ROW_ID_PICARRO_ADMIN = 6;

	protected static final String EMPTY = "";
	protected static final Integer NOTSET = -1;
	
	// Instance 1 actions and page objects.
	protected static HomePageActions homePageAction;
	protected static LoginPageActions loginPageAction;
	protected static TestEnvironmentActions testEnvironmentAction;

	protected static SurveyViewPageActions surveyViewPageAction;
	protected static SurveyViewPage surveyViewPage;

	protected static HomePage homePage = null;
	protected static LoginPage loginPage = null;

	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	protected static ThreadLocal<TestSetup> testSetup = new ThreadLocal<TestSetup>();

	protected static String baseURL = null;
	
	// JUnit does NOT give a good way to detect which TestClass is executing.
	// So we watch for the Test method under execution and install simulator pre-reqs
	// if the test under execution is a Simulator test.
	// NOTE that all simulator tests MUST follow this naming pattern: TC*_SimulatorTest_* 
	@Rule
	public TestWatcher watcher = new TestWatcher() {
		@Override
		public void starting(Description description) {
			PageActionsStore.INSTANCE.clearStore();
			try {
				TestSetup.deleteAnalyzerLocalDB3();
			} catch (UnknownHostException e) {
				Log.info(ExceptionUtility.getStackTraceString(e));
			}
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
		
	public GridPOCTestClass8() throws IOException {
		super();
	}
	
	protected static void disposeProcesses() {
		if (!TestSetup.isParallelBuildEnabled()) {
			TestSetup.stopChromeProcesses();
		}
		TestSetup.stopAnalyzer();
	}

	/**
	 * Initializes the page action objects.
	 */
	protected static void initializePageActions() {
		testSetup.set(new TestSetup());
		driver.set(testSetup.get().getDriver());
		baseURL = testSetup.get().getBaseUrl();
		Log.info("Deleting all cookies...***:" +driver);
		driver.get().manage().deleteAllCookies();
		TestContext.INSTANCE.setTestSetup(testSetup.get());
		loginPageAction = new LoginPageActions(driver.get(), baseURL, testSetup.get());
		homePageAction = new HomePageActions(driver.get(), baseURL, testSetup.get());
		testEnvironmentAction = new TestEnvironmentActions();
		
		loginPage = new LoginPage(driver.get(), baseURL, testSetup.get());
		PageFactory.initElements(driver.get(), loginPage);

		homePage = new HomePage(driver.get(), baseURL, testSetup.get());
		PageFactory.initElements(driver.get(), homePage);
	}
	
	protected void afterTest() {
		homePage.open();
		
		if (!driver.get().getTitle().equalsIgnoreCase("Login"))
			homePage.logout();
		
		driver.get().quit();
	}
	
	@BeforeClass
	public static void beforeTestClass() throws Exception {
		disposeProcesses();
		
		// Initialization needed at class level for automation reports.
		initializePageActions();
	}

	@Before
	public void beforeTestMethod() {
		try {
			initializePageActions();
			
			surveyViewPageAction = new SurveyViewPageActions(driver.get(), baseURL,testSetup.get());

			// Initialize page objects.
			surveyViewPage = new SurveyViewPage(driver.get(), baseURL, testSetup.get());
			PageFactory.initElements(driver.get(), surveyViewPage);

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@After
    public void afterTestMethod() {
		try {
			afterTest();
			
			disposeProcesses();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test Case ID: TC1001_SurveyView_ViewRapidResponseSurveyMapViewWhenLISAFieldNotesAreOnGISOff
	 * Script: -  	
	 *	- - On Home Page, click on Driving Surveys -& View Survey
	 *	- - Click Display
	 *	- All other options - OFF
	 *	- LISA - ON
	 *	- Field Notes - ON
	 *	- - Click GIS
	 *	- All options OFF
	 *	- - Click on Map and turn Map view ON
	 * Results: - 
	 *	- - Survey Information is displayed in map view - Tag, Mode, Driver Info, Start time, End Time, Surveyor and analyzer info
	 *	- - Breadcrumb, Lisa and field notes are present on map
	 *	- - Indications and FOV are not displayed on map
	 */
	@Test
	public void TC10012_SurveyView_ViewRapidResponseSurveyMapViewWhenLISAFieldNotesAreOnGISOff() throws Exception {
		Log.info("\nRunning TC1001_SurveyView_ViewRapidResponseSurveyMapViewWhenLISAFieldNotesAreOnGISOff ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, USER_ROW_ID_PICARRO_ADMIN);   /* Picarro Admin */
		surveyViewPageAction.open(TEST_SURVEY_RAPID_RESP_ID, NOTSET);
		surveyViewPageAction.verifyPageLoaded(EMPTY, NOTSET);
		surveyViewPageAction.clickOnZoomOutButton(EMPTY, NOTSET);
		surveyViewPageAction.clickOnDisplayButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllDisplayOptions(EMPTY, NOTSET);
		surveyViewPageAction.turnOnLisas(EMPTY, NOTSET);
		surveyViewPageAction.turnOnNotes(EMPTY, NOTSET);
		surveyViewPageAction.clickOnGisButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOffAllAssetsAndBoundaries(EMPTY, NOTSET);
		surveyViewPageAction.clickOnMapButton(EMPTY, NOTSET);
		surveyViewPageAction.turnOnMapView(EMPTY, NOTSET);
		assertTrue(surveyViewPageAction.verifySurveyInfoTagLabelEquals(TEST_SURVEY_RAPID_RESP_TAG, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoModeLabelEquals(SURVEY_INFO_MODE_PREFIX + TEST_SURVEY_RAPID_RESP_TYPE, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoDriverLabelEquals(SURVEY_INFO_DRIVER_PREFIX + TEST_SURVEY_RAPID_RESP_USERNAME, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStartTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoEndTimeLabelHasCorrectTimeFormat(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoAnalyzerLabelEquals(SURVEY_INFO_ANALYZER, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoSurveyorLabelEquals(SURVEY_INFO_SURVEYOR, NOTSET));
		assertTrue(surveyViewPageAction.verifySurveyInfoStabilityClassLabelEquals(SURVEY_INFO_STABILITY_CLASS_B, NOTSET));
		assertTrue(surveyViewPageAction.verifyBreadcrumbIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyLISAIsShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFieldNotesIsShownOnMap(SAMPLE_SURVEY_FIELD_NOTES1, NOTSET));
		assertTrue(surveyViewPageAction.verifyIndicationsIsNotShownOnMap(EMPTY, NOTSET));
		assertTrue(surveyViewPageAction.verifyFOVIsNotShownOnMap(EMPTY, NOTSET));
	}
}