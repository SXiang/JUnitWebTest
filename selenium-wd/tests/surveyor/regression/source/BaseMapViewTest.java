package surveyor.regression.source;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.DateUtility;
import common.source.ExceptionUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.PageActionsStore;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.actions.data.TestEnvironmentDataReader.TestEnvironmentDataRow;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.SurveyorBaseTest;

public class BaseMapViewTest {

	protected static final int ANALYZER1_INSTRUMENT_WARMING_ROW_ID = 2;
	protected static final int ANALYZER1_REPLAY_ROW_ID = 3;
	protected static final int ANALYZER3_REPLAY_ROW_ID = 9;
	protected static final int ANALYZER3_REPLAY_LONG_ROW_ID = 32;
	protected static final int ANALYZER3_REPLAY_ASSESSMENT_ROW_ID = 14;
	protected static final int ANALYZER3_REPLAY_OPERATOR_ROW_ID = 17;
	protected static final int ONE_SECOND = 5;
	protected static final int SURVEY_STANDARD1_ROW_ID = 3;
	protected static final int SURVEY_OPERATOR1_ROW_ID = 5;
	protected static final int SURVEY_ASSESSMENT1_ROW_ID = 30;
	protected static final int SURVEY_MANUAL1_ROW_ID = 31;
	protected static final int USER_ROW_ID_AUTOMATION_ADMIN = 6;
	protected static final int USER_ROW_ID_PICARRO_DRIVER = 16;
	protected static final int USER_ROW_ID_PICARRO_UTILITYADMIN = 17;
	protected static final int USER_ROW_ID_PICARRO_SUPERVISOR = 18;
	protected static final int USER_ROW_ID_PICARRO_SUPPORT = 11;
	protected static final int USER_ROW_ID_PICARRO_ADMIN = 4;
	
	protected static final int USER_ROW_ID_SQACUS_DRIVER = 3;
	protected static final String SAMPLE_FIELD_NOTES1 = "Test Notes";
	protected static final String SURVEY_INFO_SURVEYOR1_ANALYZER1 = "Surveyor: SimAuto-Surveyor1 - SimAuto-Analyzer1";
	protected static final String SURVEY_INFO_SURVEYOR2_ANALYZER2 = "Surveyor: SimAuto-Surveyor2 - SimAuto-Analyzer2";
	protected static final String SURVEY_INFO_SURVEYOR3_ANALYZER3 = "Surveyor: SimAuto-Surveyor3 - SimAuto-Analyzer3";
	protected static final String SURVEY_INFO_SURVEY_STATUS_ACTIVE = "Survey Active";
	protected static final String SURVEY_INFO_SURVEY_STATUS_INACTIVE = "Survey Inactive";
	protected static final String SURVEY_INFO_SURVEYOR1 = "Surveyor: SimAuto-Surveyor1";
	protected static final String SURVEY_INFO_SURVEYOR2 = "Surveyor: SimAuto-Surveyor2";
	protected static final String SURVEY_INFO_ANALYZER1 = "Analyzer: SimAuto-Analyzer1";
	protected static final String SURVEY_INFO_ANALYZER2 = "Analyzer: SimAuto-Analyzer2";
	protected static final String SURVEY_INFO_REMAINING_PREFIX = "Remaining: ";
	protected static final String SURVEY_INFO_ELAPSED_PREFIX = "Elapsed: ";
	protected static final String SURVEY_INFO_TIME_PREFIX = "Time: ";
	protected static final String SURVEY_INFO_SURVEYOR_PREFIX = "Surveyor: ";
	protected static final String SURVEY_INFO_START_TIME_PREFIX = "Start Time: ";
	protected static final String SURVEY_INFO_END_TIME_PREFIX = "End Time: ";
	protected static final String SURVEY_INFO_TAG_PREFIX = "Tag: ";
	protected static final String SURVEY_INFO_MODE_PREFIX = "Mode: ";
	protected static final String SURVEY_INFO_MODE_STANDARD = "Mode: Standard";
	protected static final String SURVEY_INFO_DRIVER_PREFIX = "Driver: ";
	protected static final String SURVEY_INFO_ELAPSED_TIME_00 = "Elapsed: 00:";
	protected static final String SURVEY_INFO_REMAINING_TIME_07 = "Remaining: 07:";
	protected static final String SURVEY_INFO_ZOOM_LEVEL_X = "Zoom Level: %d";
	protected static final String SURVEY_INFO_STABILITY_CLASS_A = "Stability Class: A";
	protected static final String SURVEY_INFO_STABILITY_CLASS_B = "Stability Class: B";
	protected static final String SURVEY_INFO_STABILITY_CLASS_C = "Stability Class: C";
	protected static final String SURVEY_INFO_STABILITY_CLASS_D = "Stability Class: D";
	protected static final String SIM_AUTO_ANALYZER1 = "SimAuto-Analyzer1";
	protected static final String SIM_AUTO_SURVEYOR1 = "SimAuto-Surveyor1";
	protected static final String SURVEYOR_DB3 = "Surveyor.db3";
	protected static final String INSTR_WARMING_DEFN_FILE = "instr_warming.defn";
	protected static final String INSTR_READY_DEFN_FILE = "instr_ready.defn";
	protected static final String REPLAY_DB3_DEFN_FILE = "replay-db3.defn";

	protected static final String FIELD_NOTE_ISOTOPIC_CAPTURE_NATURAL_GAS = "Natural Gas";
	protected static final String FIELD_NOTE_ISOTOPIC_CAPTURE_NOT_NATURAL_GAS = "Not Natural Gas";
	protected static final String FIELD_NOTE_ISOTOPIC_CAPTURE_CANCELLED = "Iso Cap Canceled";
	protected static final String ISOTOPIC_CANCELLED = "Isotopic Canceled";
	protected static final String EMPTY = "";
	protected static final Integer NOTSET = -1;
	protected static final String COLOR_RED = "Red";
	protected static final String COLOR_GRAY = "Gray";
	protected static final String COLOR_WHITE = "White";
	protected static final String Live_Obvserver_Pattern = "/Live/Observer?serialNumber=%s";
	
	// Instance 1 actions and page objects.
	protected static HomePageActions homePageAction;
	protected static LoginPageActions loginPageAction;
	protected static TestEnvironmentActions testEnvironmentAction;

	protected static HomePage homePage = null;
	protected static LoginPage loginPage = null;

	protected static TestSetup testSetup = null;
	protected static WebDriver driver = null;
	protected static String baseURL = null;

	// Extra instance actions and page objects. 
	// Extra instance can be used for tests running on multiple windows (for eg. DriverView and ObserverView windows). 
	protected ArrayList<HomePageActions> homePageActionList = new ArrayList<HomePageActions>();
	protected ArrayList<LoginPageActions> loginPageActionList = new ArrayList<LoginPageActions>();

	protected ArrayList<HomePage> homePageList = new ArrayList<HomePage>();
	protected ArrayList<LoginPage> loginPageList = new ArrayList<LoginPage>();

	protected ArrayList<TestSetup> testSetupList = new ArrayList<TestSetup>();
	protected ArrayList<WebDriver> driverList = new ArrayList<WebDriver>();
	protected ArrayList<String> baseURLList = new ArrayList<String>(); 

	protected static DateUtility dateUtility = new DateUtility();

	public BaseMapViewTest() {
		initializePageActions();
	}
	
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

	/**
	 * Initializes the page action objects.
	 */
	protected static void initializePageActions() {
		if(testSetup == null || testSetup.getDriver() == null){
			testSetup = new TestSetup();
		}
		driver = testSetup.getDriver();
		baseURL = testSetup.getBaseUrl();
		Log.info("Deleting all cookies...***:" +driver);
		driver.manage().deleteAllCookies();
		TestContext.INSTANCE.setTestSetup(testSetup);
		loginPageAction = new LoginPageActions(driver, baseURL, testSetup);
		homePageAction = new HomePageActions(driver, baseURL, testSetup);
		testEnvironmentAction = new TestEnvironmentActions();
		
		loginPage = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, loginPage);

		homePage = new HomePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, homePage);
	}

	/**
	 * Initializes extra page action objects.
	 */
	protected void initializePageActionsList() {
		addPageActionsForNewDrivers(1);
	}
	protected void addPageActionsForNewDrivers(int num) {
		int currentSize = testSetupList.size();
		for(int index = currentSize; index < num + currentSize; index++ ){
			TestSetup setup= new TestSetup();
			testSetupList.add(setup);
			driverList.add(setup.getDriver());
			baseURLList.add(setup.getBaseUrl());
			Log.info("Deleting all cookies...***:" + setup.getDriver());
			setup.getDriver().manage().deleteAllCookies();
			loginPageActionList.add(new LoginPageActions(setup.getDriver(), setup.getBaseUrl(), setup));
			homePageActionList.add(new HomePageActions(setup.getDriver(), setup.getBaseUrl(), setup));
			
			loginPageList.add(new LoginPage(setup.getDriver(), setup.getBaseUrl(), setup));
			PageFactory.initElements(setup.getDriver(), loginPageList.get(index));

			homePageList.add(new HomePage(setup.getDriver(), setup.getBaseUrl(), setup));
			PageFactory.initElements(setup.getDriver(), homePageList.get(index));
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// Post run result to DB if enabled.
		SurveyorBaseTest.postResultsToAutomationAPI();
	}
	
	protected void afterTest() {
		homePage.open();
		
		if (!driver.getTitle().equalsIgnoreCase("Login"))
			homePage.logout();
		
		driver.quit();
		testSetup = null;
		
		// clean up - extra web drivers 
		for(int index=0;index<testSetupList.size(); index++){
			homePageList.get(index).open();
			if (!driverList.get(index).getTitle().equalsIgnoreCase("Login"))
				homePageList.get(index).logout();
			
			driverList.get(index).quit();
		}
	}

	protected static void disposeProcesses() {
		TestSetup.stopChromeProcesses();
		TestSetup.stopAnalyzer();
	}

	protected void startDrivingSurvey(DriverViewPageActions driverViewPageAction, Integer analyzerRowId, Integer surveyRowId,
			Integer idleTimeInSeconds) throws Exception {
		// Start Analyzer & replay db3
		testEnvironmentAction.startAnalyzer(EMPTY, analyzerRowId); 	
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		testEnvironmentAction.idleForSeconds(String.valueOf(5), NOTSET);
		
		TestEnvironmentDataRow environmentDataRow = testEnvironmentAction.getDataReader().getDataRow(analyzerRowId);
		testEnvironmentAction.startReplay(environmentDataRow.replayScriptDB3File, analyzerRowId); 	
		// Start Operator Survey
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.startDrivingSurvey(EMPTY, surveyRowId);	
		testEnvironmentAction.idleForSeconds(String.valueOf(idleTimeInSeconds), NOTSET);
	}

	protected void startReplay(DriverViewPageActions driverViewPageAction, Integer analyzerRowId) throws Exception {
		testEnvironmentAction.startAnalyzer(EMPTY, analyzerRowId); 	
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		testEnvironmentAction.startReplay(EMPTY, analyzerRowId);
	}

	protected void stopSurvey(DriverViewPageActions driverViewPageAction) {
		// Stop Survey
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);	
	}
	
	protected void stopSurveyAndAnalyzer(DriverViewPageActions driverViewPageAction) {
		stopSurvey(driverViewPageAction);	
		// Stop Analyzer
		testEnvironmentAction.stopAnalyzer(EMPTY, NOTSET);
	}

	protected int getHourOfDay() {
		int hourOfDay = DateUtility.getCalendarForCurrentZone().get(Calendar.HOUR_OF_DAY);
		if (hourOfDay > 12) {
			hourOfDay = hourOfDay - 12;
		}
		return hourOfDay;
	}
	
	protected String getLiveObservePath(){
		String serialNumber = TestEnvironmentActions.workingDataRow.analyzerSerialNumber;
		String path = String.format(Live_Obvserver_Pattern, serialNumber);
		return path;
	}
}
