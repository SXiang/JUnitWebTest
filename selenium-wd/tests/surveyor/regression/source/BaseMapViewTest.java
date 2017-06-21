package surveyor.regression.source;

import static surveyor.regression.source.BaseMapViewTest.testIndex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.CheckedPredicate;
import common.source.DateUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.WebDriverFactory;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.actions.data.TestEnvironmentDataReader.TestEnvironmentDataRow;
import surveyor.scommon.source.BaseTest;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;

public class BaseMapViewTest extends BaseTest{
	protected static final int ANALYZER1_INSTRUMENT_WARMING_ROW_ID = 2;
	protected static final int ANALYZER1_REPLAY_ROW_ID = 3;
	protected static final int ANALYZER3_REPLAY_ROW_ID = 9;
	protected static final int ANALYZER3_REPLAY_LONG_ROW_ID = 32;
	protected static final int ANALYZER3_REPLAY_ASSESSMENT_ROW_ID = 14;
	protected static final int ANALYZER3_REPLAY_OPERATOR_ROW_ID = 17;
	protected static final int ETH_ANALYZER1_REPLAY_ROW_ID = 60;
	protected static final int NOLICANA_REPLAY_ROW_ID = 39;
	protected static final int ONE_SECOND = 5;
	protected static final int SURVEY_STANDARD1_ROW_ID = 3;
	protected static final int SURVEY_OPERATOR1_ROW_ID = 5;
	protected static final int SURVEY_ASSESSMENT1_ROW_ID = 30;
	protected static final int SURVEY_MANUAL1_ROW_ID = 31;
	protected static final int SURVEY_ANALYTICS1_ROW_ID = 63;
	protected static final int USER_ROW_ID_AUTOMATION_ADMIN = 6;
	protected static final int USER_ROW_ID_PICARRO_DRIVER = 16;
	protected static final int USER_ROW_ID_PICARRO_UTILITYADMIN = 17;
	protected static final int USER_ROW_ID_PICARRO_SUPERVISOR = 18;
	protected static final int USER_ROW_ID_PICARRO_SUPPORT = 11;
	protected static final int USER_ROW_ID_PICARRO_ADMIN = 4;
	protected static final int USER_ROW_ID_CUSTOMER_ADMIN_NOLIC = 19;

	protected static final int USER_ROW_ID_SQACUS_DRIVER = 3;
	protected static final String SAMPLE_FIELD_NOTES1 = "Test Notes";
	protected static final String SURVEY_INFO_SURVEYOR1_ANALYZER1 = "Surveyor: SimAuto-Surveyor1 - SimAuto-Analyzer1";
	protected static final String SURVEY_INFO_SURVEYOR2_ANALYZER2 = "Surveyor: SimAuto-Surveyor2 - SimAuto-Analyzer2";
	protected static final String SURVEY_INFO_SURVEYOR3_ANALYZER3 = "Surveyor: SimAuto-Surveyor3 - SimAuto-Analyzer3";
	protected static final String SURVEY_INFO_SURVEYOR4_ANALYZER4 = "Surveyor: SimAuto-Surveyor4 - SimAuto-Analyzer4";
	protected static final String SURVEY_INFO_SURVEYOR5_ANALYZER5 = "Surveyor: SimAuto-Surveyor5 - SimAuto-Analyzer5";
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
	protected static final String SURVEY_INFO_STABILITY_CLASS_E = "Stability Class: E";
	protected static final String SURVEY_INFO_STABILITY_CLASS_F = "Stability Class: F";
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
	private static ThreadLocal<HomePageActions> homePageAction = new ThreadLocal<HomePageActions>();
	protected static ThreadLocal<LoginPageActions> loginPageAction = new ThreadLocal<LoginPageActions>();
	protected static ThreadLocal<TestEnvironmentActions> testEnvironmentAction = new ThreadLocal<TestEnvironmentActions>();

	// Extra instance actions and page objects.
	// Extra instance can be used for tests running on multiple windows (for eg. DriverView and ObserverView windows).
	protected List<HomePageActions> homePageActionList = Collections.synchronizedList(new ArrayList<HomePageActions>());
	protected List<LoginPageActions> loginPageActionList = Collections.synchronizedList(new ArrayList<LoginPageActions>());

	protected List<HomePage> homePageList = Collections.synchronizedList(new ArrayList<HomePage>());
	protected List<LoginPage> loginPageList = Collections.synchronizedList(new ArrayList<LoginPage>());

	protected List<TestSetup> testSetupList = Collections.synchronizedList(new ArrayList<TestSetup>());
	protected List<WebDriver> driverList = Collections.synchronizedList(new ArrayList<WebDriver>());
	protected List<String> baseURLList = Collections.synchronizedList(new ArrayList<String>());

	// Data members for working with new webdriver instance/test.
	protected static AtomicInteger testIndex = new AtomicInteger();
	protected ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

	public BaseMapViewTest() {
		initializeBasePageActions();
	}

	/**
	 * Initializes the page action objects.
	 * @throws IOException
	 */
	protected static void initializeBasePageActions(){
		setLoginPageAction(new LoginPageActions(getDriver(), getBaseURL(), getTestSetup()));
		setHomePageAction(new HomePageActions(getDriver(), getBaseURL(), getTestSetup()));
		setTestEnvironmentAction(new TestEnvironmentActions());
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
			// Index 0 is default web driver. Start from 1 for additional web drivers.
			WebDriver driver = setup.getDriver(index+1);
			driverList.add(driver);
			baseURLList.add(setup.getBaseUrl());
			Log.info("Deleting all cookies...***:" + driver);
			driver.manage().deleteAllCookies();
			loginPageActionList.add(new LoginPageActions(driver, setup.getBaseUrl(), setup));
			homePageActionList.add(new HomePageActions(driver, setup.getBaseUrl(), setup));

			loginPageList.add(new LoginPage(driver, setup.getBaseUrl(), setup));
			PageFactory.initElements(driver, loginPageList.get(index));

			homePageList.add(new HomePage(driver, setup.getBaseUrl(), setup));
			PageFactory.initElements(driver, homePageList.get(index));
		}
	}

	// Data methods for working with new webdriver instance/test -->
	protected WebDriver createDriver(boolean createNewDriver) {
		WebDriver driver = null;
		if (createNewDriver) {
			driver = createNewDriver();
			webDriver.set(driver);
		} else {
			driver = getDriver();
		}
		return driver;
	}

	protected WebDriver createNewDriver() {
		return WebDriverFactory.getDriver(testIndex.incrementAndGet(), false /*reuse*/);
	}

	protected void executeAsNewWebDriver(CheckedPredicate<Object> testActions) throws Exception {
		initializePageObjects(true /*createNewDriver*/);
		try {
			testActions.test(null);
		} finally {
			quitWebDriver();
		}
	}

	protected void initializePageObjects(boolean createNewDriver) throws Exception {
		throw new Exception("Implementation for this method needs to be provided by derived class.");
	}

	protected void quitWebDriver() {
		this.webDriver.get().quit();
	}

	// <--

	@BeforeClass
	public static void beforeTestClass() throws Exception {
		disposeProcesses();
	}

	@AfterClass
	public static void afterTestClass() {
		// This implementation is to support quit all drivers for tests that spin up multiple web drivers (for eg. Observer view)
		// Currently we do NOT support running multiple web driver tests (eg. Observer view tests) in parallel and hence disabled in parallel tests.
		if (!TestSetup.isParallelBuildEnabled()) {
			// dispose all web drivers
			WebDriverFactory.quitDrivers();
		}
	}

	@After
	public void afterTest() {
		//setTestSetup(null);

		// logout from page for extra web drivers.
		for(int index=0;index<testSetupList.size(); index++){
			homePageList.get(index).open();
			if (!driverList.get(index).getTitle().equalsIgnoreCase("Login"))
				homePageList.get(index).logout();
		}

		// This implementation is to support quit all extra drivers for tests that spin up multiple web drivers (for eg. Observer view)
		// Currently we do NOT support running multiple web driver tests (eg. Observer view tests) in parallel and hence disabled in parallel tests.
		if (!TestSetup.isParallelBuildEnabled()) {
			// dispose extra web drivers
			WebDriverFactory.quitDrivers(false /*quitDefaultDriver*/);
		}
	}

	protected static void disposeProcesses() {
		if (!TestSetup.isParallelBuildEnabled()) {
			TestSetup.stopChromeProcesses();
			TestSetup.stopAnalyzer();
		}
	}

	protected void startDrivingSurvey(DriverViewPageActions driverViewPageAction, Integer analyzerRowId, Integer surveyRowId,
			Integer idleTimeInSeconds) throws Exception {
		// Start Analyzer & replay db3
		getTestEnvironmentAction().startAnalyzer(EMPTY, analyzerRowId);
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		getTestEnvironmentAction().idleForSeconds(String.valueOf(5), NOTSET);

		TestEnvironmentDataRow environmentDataRow = getTestEnvironmentAction().getDataReader().getDataRow(analyzerRowId);
		getTestEnvironmentAction().startReplay(environmentDataRow.replayScriptDB3File, analyzerRowId);
		// Start Operator Survey
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.startDrivingSurvey(EMPTY, surveyRowId);
		getTestEnvironmentAction().idleForSeconds(String.valueOf(idleTimeInSeconds), NOTSET);
	}

	protected void startDrivingSurvey(DriverViewPageActions driverViewPageAction, String analyzerSerialNumber, String analyzerSharedKey, Integer analyzerRowId, Integer surveyRowId,
			Integer idleTimeInSeconds) throws Exception {
		// Start Analyzer & replay db3
		startAnalyzer(analyzerSerialNumber, analyzerSharedKey, analyzerRowId);

		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		testEnvironmentAction.get().idleForSeconds(String.valueOf(5), NOTSET);

		testEnvironmentAction.get().startReplay(EMPTY, analyzerRowId);

		// Start Operator Survey
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.startDrivingSurvey(EMPTY, surveyRowId);
		testEnvironmentAction.get().idleForSeconds(String.valueOf(idleTimeInSeconds), NOTSET);
	}

	protected void startAnalyzer(String analyzerSerialNumber, String analyzerSharedKey, Integer analyzerRowId) throws Exception{
		//Using analyzer created at runtime for this test
		TestEnvironmentActions.workingDataRow.set(testEnvironmentAction.get().getDataReader().getDataRow(analyzerRowId));
		TestEnvironmentActions.workingDataRow.get().analyzerSerialNumber = analyzerSerialNumber;
		TestEnvironmentActions.workingDataRow.get().analyzerSharedKey = analyzerSharedKey;
		TestEnvironmentActions.workingDataRow.get().analyzerRowID = "";

		TestSetup.updateAnalyzerConfiguration(TestContext.INSTANCE.getBaseUrl(), analyzerSerialNumber, analyzerSharedKey);
		TestSetup.restartAnalyzer();
	}

	protected void startReplay(DriverViewPageActions driverViewPageAction, Integer analyzerRowId) throws Exception {
		getTestEnvironmentAction().startAnalyzer(EMPTY, analyzerRowId);
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);
		getTestEnvironmentAction().startReplay(EMPTY, analyzerRowId);
	}

	protected void stopSurvey(DriverViewPageActions driverViewPageAction) {
		// Stop Survey
		driverViewPageAction.clickOnModeButton(EMPTY, NOTSET);
		driverViewPageAction.stopDrivingSurvey(EMPTY, NOTSET);
	}

	protected void stopSurveyAndAnalyzer(DriverViewPageActions driverViewPageAction) {
		stopSurvey(driverViewPageAction);
		// Stop Analyzer
		getTestEnvironmentAction().stopAnalyzer(EMPTY, NOTSET);
	}

	protected int getHourOfDay() {
		int hourOfDay = DateUtility.getCalendarForCurrentZone().get(Calendar.HOUR_OF_DAY);
		if (hourOfDay > 12) {
			hourOfDay = hourOfDay - 12;
		}
		return hourOfDay;
	}

	protected String getLiveObservePath(){
		String serialNumber = TestEnvironmentActions.workingDataRow.get().analyzerSerialNumber;
		String path = String.format(Live_Obvserver_Pattern, serialNumber);
		return path;
	}

	protected static LoginPageActions getLoginPageAction() {
		return loginPageAction.get();
	}

	private static void setLoginPageAction(LoginPageActions loginPgAction) {
		loginPageAction.set(loginPgAction);
	}

	protected static HomePageActions getHomePageAction() {
		return homePageAction.get();
	}

	private static void setHomePageAction(HomePageActions homePgAction) {
		homePageAction.set(homePgAction);
	}

	protected static TestEnvironmentActions getTestEnvironmentAction() {
		return testEnvironmentAction.get();
	}

	private static void setTestEnvironmentAction(TestEnvironmentActions testEnvAction) {
		testEnvironmentAction.set(testEnvAction);
	}
}