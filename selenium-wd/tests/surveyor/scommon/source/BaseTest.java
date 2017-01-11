package surveyor.scommon.source;

import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.CUSTOMERNAMEPREFIX;
import static surveyor.scommon.source.SurveyorConstants.CUSUSERROLEUA;
import static surveyor.scommon.source.SurveyorConstants.EULASTRING;
import static surveyor.scommon.source.SurveyorConstants.REGBASEUSERNAME;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.source.DateUtility;
import common.source.ExceptionUtility;
import common.source.ExtentReportGenerator;
import common.source.Log;
import common.source.RegexUtility;
import common.source.ScreenShotOnFailure;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.TestSetupFactory;
import surveyor.dataaccess.source.Analyzer;
import surveyor.dataprovider.DataAnnotations;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.PageActionsStore;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;

public class BaseTest {

	private static List<WebDriver> spawnedWebDrivers = Collections.synchronizedList(new ArrayList<WebDriver>());
	private static Map<String, ExtentTest> extentTestMap = Collections.synchronizedMap(new HashMap<String, ExtentTest>());

	private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<WebDriver>();
	private static ThreadLocal<String> baseURLThreadLocal = new ThreadLocal<String>();
	private static ThreadLocal<StringBuilder> extentReportFilePathThreadLocal = new ThreadLocal<StringBuilder>();
	private static ThreadLocal<ScreenShotOnFailure> screenCaptureThreadLocal = new ThreadLocal<ScreenShotOnFailure>();

	public static String screenShotsDir;
	public static String screenShotsSubFolder = "screenshots/";
	private static boolean debug;

	private static LoginPage loginPage;
	private static HomePage homePage;

	// JUnit does NOT give a good way to detect which TestClass is executing.
	// So we watch for the Test method under execution and install simulator pre-reqs
	// if the test under execution is a Simulator test.
	// NOTE that all simulator tests MUST follow this naming pattern: TC*_SimulatorTest_*
	@Rule
	public TestWatcher watcher = new TestWatcher() {
		@Override
		public void starting(Description description) {
			BaseTest.reportTestStarting(description);
			TestSetup.simulatorTestStarting(description);
		}

		@Override
		public void finished(Description description) {
			BaseTest.reportTestFinished(description.getClassName());
			TestSetup.simulatorTestFinishing(description);
		}

		@Override
		protected void failed(Throwable e, Description description) {
			BaseTest.reportTestFailed(e, description.getClassName());
			postTestMethodProcessing();
		}

		 @Override
		 protected void succeeded(Description description) {
			BaseTest.reportTestSucceeded(description.getClassName());
		}
	};

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		if (!TestSetup.isParallelBuildEnabled()) {
			TestSetup.stopChromeProcesses();
		}

		initializeTestObjects();
	}

	public static void initializeTestObjects(){
		initializeTestObjects(true /*initializeDriver*/);
	}

	protected static void initializeTestObjects(boolean initializeDriver) {
		if (getTestSetup()==null) {
			setTestSetup(TestSetupFactory.getTestSetup());
			Log.info(String.format("[THREAD Debug Log].. Set TestSetup - '%s'", getTestSetup()));
		}
		if (getBaseURL() == null) {
			setBaseURL(getTestSetup().getBaseUrl());
			Log.info(String.format("[THREAD Debug Log].. Set BaseURL - '%s'", getBaseURL()));
		}

		setDebug(getTestSetup().isRunningDebug());

		if (initializeDriver) {
			// Store webdriver instances that are spawned.
			WebDriver webDriver = getTestSetup().getDriver();
			List<WebDriver> list = Collections.synchronizedList(spawnedWebDrivers);
			synchronized (list) {
				if (!list.contains(webDriver)) {
					Log.info(String.format("[THREAD Debug Log]..Adding webdriver instance -'%s' to the LIST", webDriver));
					list.add(webDriver);
				}
			}

			if (getDriver() == null) {
				driverThreadLocal.set(webDriver);
				getDriver().manage().deleteAllCookies();
				Log.info(String.format("[THREAD Debug Log].. Set WebDriver - '%s'", getDriver()));
			}
		}

		if (getScreenCapture() == null) {
			ScreenShotOnFailure screenShotOnFailure = TestSetupFactory.getScreenShotOnFailure();
			setScreenCapture(screenShotOnFailure);
			getTestSetup().setScreenCapture(screenShotOnFailure);
			Log.info(String.format("[THREAD Debug Log].. Set ScreenCapture - '%s'", getScreenCapture()));
		}
	}

	private static ExtentReports getExtentReport(String className) {
		Log.info(String.format("[THREAD Debug Log] - calling getExtentReport(className=[%s])", className));
		ExtentReports extentReport = ExtentReportGenerator.getExtentReport(className);
		if (extentReport == null) {
		   StringBuilder outReportFilePath = new StringBuilder();
		   extentReport = ExtentReportGenerator.createExtentReport(className, outReportFilePath);
		   extentReportFilePathThreadLocal.set(outReportFilePath);
		   ExtentReportGenerator.setExtentReport(extentReport, className);
		}
		return extentReport;
	}

	public static void reportTestStarting(Description description) {
		reportTestStarting(description.getClassName(), description.getMethodName(), description.toString());
	}

	public static void reportTestStarting(String className, String methodName, String firstLogLine) {
		Log.method("reportTestStarting", className, methodName, firstLogLine);
		ExtentReports report = getExtentReport(className);
		setExtentTest(report.startTest(methodName), className);
		getExtentTest(className).assignCategory(TestContext.INSTANCE.getTestRunCategory());
		getExtentTest(className).log(LogStatus.INFO, firstLogLine);
		getExtentTest(className).log(LogStatus.INFO, String.format("Starting test.. [Start Time:%s]",
				DateUtility.getCurrentDate()));
		TestContext.INSTANCE.setTestClassName(className);
	}

	public static void reportTestFinished(String className) {
		ExtentReports report = getExtentReport(className);
		getExtentTest(className).log(LogStatus.INFO, String.format("Finished test. [End Time:%s]",
				DateUtility.getCurrentDate()));
		report.endTest(getExtentTest(className));
		report.flush();
	}

	public static void reportTestLogMessage(String className) {
		List<String> testMessage = TestContext.INSTANCE.getTestMessage();
		for(String message:testMessage){
			getExtentTest(className).log(LogStatus.WARNING, "Extra messages before the failure", "Log Message: " + message);
		}
	}

	public static void reportTestFailed(Throwable e, String className) {
		BaseTest.reportTestLogMessage(className);
		getScreenCapture().takeScreenshot(getDriver(), className, true /*takeBrowserScreenShot*/, LogStatus.ERROR);
		Log.error("_FAIL_ Exception: " + ExceptionUtility.getStackTraceString(e));
		TestContext.INSTANCE.setTestStatus("FAIL");
		getExtentTest(className).log(LogStatus.FAIL, "FAILURE: " + ExceptionUtility.getStackTraceString(e));
	}

	public static void reportTestSucceeded(String className) {
		Log.info("_PASS_ ");
		getExtentTest(className).log(LogStatus.PASS, "PASSED");
	}

	protected static ExtentTest getExtentTest(String className) {
		return extentTestMap.get(className);
	}

	private static void setExtentTest(ExtentTest test, String className) {
		extentTestMap.put(className, test);
		TestContext.INSTANCE.setExtentTest(test, className);
	}

	public static void logoutQuitDriver() {
		Log.method("BaseTest.logoutQuitDriver");
		if(getDriver() == null){
			return;
		}

		logout();

		getDriver().quit();
		setDriver(null);
	}

	public static void logout() {
		if (getHomePage() != null) {
			if (!getDriver().getTitle().equalsIgnoreCase("Login")) {
				getHomePage().open();
				getHomePage().logout();
			}
		}
	}

	public static void postResultsToAutomationAPI() {
		if (getExtentReportFilePath()!=null) {
			if (TestContext.INSTANCE.getTestSetup().isAutomationReportingApiEnabled()) {
				TestContext.INSTANCE.getTestSetup().postAutomationRunResult(getExtentReportFilePath().toString());
			}
		}
	}

	public void postTestMethodProcessing() {
	}

	protected boolean isValidRunAsUser(String username, String functionName) {
		String runAsUsers = DataAnnotations.getRunAsUsers(getClass(), functionName);
		List<String> listUsers = RegexUtility.split(runAsUsers, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
		if (!listUsers.contains(username)) {
			return false;
		}
		return true;
	}

	public Map<String, String> createTestAccount(String testCase){
		return createTestAccount(testCase, null);
	}

	public Map<String, String> createTestAccount(String testCase, boolean addTestSurveyor){
		return createTestAccount(testCase, null, addTestSurveyor);
	}

	public Map<String, String> createTestAccount(String testCase, boolean addTestSurveyor, boolean fetchAnalyzerFromPool){
		return createTestAccount(testCase, null, addTestSurveyor, fetchAnalyzerFromPool);
	}

	public Map<String, String> createTestAccount(String testCase, LicensedFeatures[] lfsToExclude){
		return createTestAccount(testCase, lfsToExclude, true);
	}

	public Map<String, String> createTestAccount(String testCase, LicensedFeatures[] lfsToExclude, boolean addTestSurveyor){
		return createTestAccount(testCase, lfsToExclude, addTestSurveyor, true /*fetchAnalyzerFromPool*/);
	}

	public Map<String, String> createTestAccount(String testCase, LicensedFeatures[] lfsToExclude, boolean addTestSurveyor, boolean fetchAnalyzerFromPool){
		String uniqueNumber = getTestSetup().getFixedSizeRandomNumber(6);
		String customerName = CUSTOMERNAMEPREFIX + uniqueNumber + testCase;
		String userName = uniqueNumber + REGBASEUSERNAME;
		String userRole = CUSUSERROLEUA;
		String userPassword = USERPASSWORD;
		String eula = customerName + ": " + EULASTRING;
		String cityName = "Santa Clara";
		String locationName = uniqueNumber + "Loc";
		String surveyorName = uniqueNumber + "Sur";
		String analyzerName = uniqueNumber + "Ana";

		if (fetchAnalyzerFromPool) {
			// Fetch Analyzer from pool. Delete if already exists.
			analyzerName = AnalyzerSerialNumberPool.INSTANCE.fetchNext();
			Log.info(String.format("Fetched Analyzer with serial number-'%s' from pool", analyzerName));
			Analyzer analyzer = new Analyzer().getBySerialNumber(analyzerName);
			if (analyzer != null) {
				Log.info(String.format("Deleting Analyzer with serial number-'%s' using data access objects", analyzerName));
				analyzer.deleteAnalyzer();
			}
		}

		String analyzerSharedKey = analyzerName + "Key";
		String lotNum = getTestSetup().getRandomNumber() + testCase;
		String isoValue = "-32.7";

		LicensedFeatures[] lfs = LicensedFeatures.values(lfsToExclude);

		HashMap<String, String> testAccount = new HashMap<String, String>();
		testAccount.put("customerName", customerName);
		testAccount.put("userName", userName);
		testAccount.put("locationName", locationName);
		testAccount.put("cityName", cityName);
		testAccount.put("userPassword", userPassword);
		testAccount.put("userRole", userRole);
		testAccount.put("eula", eula);

		ManageCustomersPage	manageCustomersPage = new ManageCustomersPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(),  manageCustomersPage);
		ManageUsersPage	manageUsersPage = new ManageUsersPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(), manageUsersPage);
		ManageLocationsPage	manageLocationsPage = new ManageLocationsPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(), manageLocationsPage);

		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageCustomersPage.open();
		if(!manageCustomersPage.addNewCustomer(customerName, eula, true,lfs)){
			fail(String.format("Failed to add a new customer %s, %s, %s",customerName, eula, true));
		}

		manageLocationsPage.open();
		if(!manageLocationsPage.addNewLocation(locationName, customerName, cityName)){
			fail(String.format("Failed to add a new location %s, %s, %s",locationName, customerName, cityName));
		}

		manageUsersPage.open();
		if(!manageUsersPage.addNewCustomerUser(customerName, userName, userPassword, userRole, locationName)){
			fail(String.format("Failed to add a new analyzer %s, %s, %s, %s, %s",customerName, userName, userPassword, userRole, locationName));
		}

		if(!addTestSurveyor){
			return testAccount;
		}

		testAccount.put("analyzerSharedKey", analyzerSharedKey);
		testAccount.put("analyzerName", analyzerName);
		testAccount.put("surveyorName", surveyorName);

		ManageSurveyorPage manageSurveyorPage = new ManageSurveyorPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(),  manageSurveyorPage);
		ManageAnalyzersPage manageAnalyzersPage = new ManageAnalyzersPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(),  manageAnalyzersPage);
		ManageRefGasBottlesPage manageRefGasBottlesPage = new ManageRefGasBottlesPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(),  manageRefGasBottlesPage);

		manageSurveyorPage.open();
		if(!manageSurveyorPage.addNewSurveyor(surveyorName, locationName, customerName)){
			fail(String.format("Failed to add a new Surveyor %s, %s, %s",surveyorName, locationName, customerName));
		}

		manageAnalyzersPage.open();
		if(!manageAnalyzersPage.addNewAnalyzer(analyzerName, analyzerSharedKey, surveyorName, customerName, locationName)){
			fail(String.format("Failed to add a new analyzer %s, %s, %s, %s, %s",analyzerName, analyzerSharedKey, surveyorName, customerName, locationName));
		}

		manageRefGasBottlesPage.open();
		if(!manageRefGasBottlesPage.addNewRefGasBottle(lotNum, isoValue, customerName, locationName, surveyorName)){
			fail(String.format("Failed to add a new analyzer %s, %s, %s, %s, %s",lotNum, isoValue, customerName, locationName, surveyorName));
		}

		return Collections.synchronizedMap(testAccount);
	}

	public Map<String, String> addTestReport() throws Exception{
		return addTestReport(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
	}

	public Map<String, String> addTestReport(String userName, String Password, SurveyModeFilter... surveyModeFilter)throws Exception{
		int testRowID = 115;
		return addTestReport(userName, Password, testRowID, surveyModeFilter);
	}

	public Map<String, String> addTestReport(String userName, String Password, int testRowID, SurveyModeFilter... surveyModeFilter)throws Exception{
		return addTestReport(userName, Password, null /*surveyTag*/, testRowID, surveyModeFilter);
	}

	public Map<String, String> addTestReport(String userName, String Password, String surveyTag, int testRowID, SurveyModeFilter... surveyModeFilter)throws Exception{
		ReportModeFilter[] reportMode = {ReportModeFilter.Standard, ReportModeFilter.Standard, ReportModeFilter.RapidResponse, ReportModeFilter.Manual};
		SurveyModeFilter[] surveyMode = {SurveyModeFilter.Standard, SurveyModeFilter.Operator, SurveyModeFilter.RapidResponse, SurveyModeFilter.Manual};
		if(surveyModeFilter==null||surveyModeFilter.length==0){
			surveyModeFilter = SurveyModeFilter.values();
		}

		HashMap<String, String> testReport = new HashMap<String, String>();

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, Password);
		testReport.put("userName", userName);

		ComplianceReportsPageActions complianceReportsPageAction = ActionBuilder.createComplianceReportsPageAction();
		ComplianceReportsPage complianceReportsPage = complianceReportsPageAction.getComplianceReportsPage();

		for(SurveyModeFilter sm:surveyModeFilter){
			boolean found = false;
			ReportModeFilter rm = null;
			for(int i=0; i<surveyMode.length; i++){
				if(sm.equals(surveyMode[i])){
					rm = reportMode[i];
					found = true;
					break;
				}
			}
			if(!found){
				continue;
			}

			complianceReportsPageAction.open("", -1);
			ReportsCompliance rpt = complianceReportsPageAction.fillWorkingDataForReports(testRowID);
			rpt.rptTitle += rm.toString()+sm.toString();
			rpt.reportModeFilter = rm;
			rpt.surveyModeFilter = sm;
			rpt.strCreatedBy = userName;

			for(ReportsSurveyInfo smf:rpt.getSurveyInfoList()){
				if(smf!=null) {
					smf.setSurveyModeFilter(sm);
					if (surveyTag != null && surveyTag != "") {
						smf.setTag(surveyTag);
					}
				}
			}
			testReport.put(sm.toString()+"Title", rpt.rptTitle);

			complianceReportsPage.addNewReport(rpt, true);
			complianceReportsPage.waitForReportGenerationtoComplete(rpt.rptTitle, rpt.strCreatedBy);
		}

		return Collections.synchronizedMap(testReport);
	}

	public Map<String, String> addTestSurvey(String analyzerName, String analyzerSharedKey) throws Exception{
		return addTestSurvey(analyzerName, analyzerSharedKey, getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
	}

	public Map<String, String> addTestSurvey(String analyzerName, String analyzerSharedKey, String userName, String Password, SurveyType... surveyTypes) throws Exception{
		int surveyRuntimeInSeconds = 2;
		return addTestSurvey(analyzerName, analyzerSharedKey, userName, Password, surveyRuntimeInSeconds, surveyTypes);
	}

	public Map<String, String> addTestSurvey(String analyzerName, String analyzerSharedKey, String userName, String Password, int surveyRuntimeInSeconds, SurveyType... surveyTypes) throws Exception{
		String replayScriptDefnFile = "replay-db3.defn";
		String replayScriptDB3File = "Surveyor.db3";
		int[] surveyRowIDs = {3, 5, 9, 31, 30};
		String[] surveyType = {"Standard", "Operator", "RapidResponse", "Assessment", "Manual"};

		if(surveyTypes==null||surveyTypes.length==0){
			surveyTypes = SurveyType.values();
		}

		HashMap<String, String> testSurvey = new HashMap<String, String>();
		testSurvey.put("analyzerName", analyzerName);
		testSurvey.put("analyzerShearedKey", analyzerSharedKey);

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, Password);
		DriverViewPageActions driverViewPageAction = ActionBuilder.createDriverViewPageAction();
		TestEnvironmentActions testEnvironmentAction = ActionBuilder.createTestEnvironmentAction();
		//Using analyzer created at runtime for this test - impacts open Rrl of driver view

		TestEnvironmentActions.workingDataRow.set(testEnvironmentAction.getDataReader().getDataRow(3));
		TestEnvironmentActions.workingDataRow.get().analyzerSerialNumber = analyzerName;
		TestEnvironmentActions.workingDataRow.get().analyzerSharedKey = analyzerSharedKey;
		TestEnvironmentActions.workingDataRow.get().analyzerRowID = "";

		TestSetup.updateAnalyzerConfiguration(TestContext.INSTANCE.getBaseUrl(),
				analyzerName, analyzerSharedKey);
		TestSetup.restartAnalyzer();

		for(SurveyType st:surveyTypes){
			driverViewPageAction.open("", -1);
			driverViewPageAction.waitForConnectionToComplete("", -1);

			int surveyRowID = surveyRowIDs[0];
			for(int i=0; i<surveyType.length; i++){
				if(st.toString().equalsIgnoreCase(surveyType[i])){
					surveyRowID = surveyRowIDs[i];
					break;
				}
			}
			TestSetup.replayDB3Script(replayScriptDefnFile, replayScriptDB3File);
			driverViewPageAction.clickOnModeButton("", -1);
			driverViewPageAction.startDrivingSurvey("", surveyRowID);
			testSurvey.put(st.toString()+"Tag", DriverViewPageActions.workingDataRow.get().surveyTag);
			testEnvironmentAction.idleForSeconds(String.valueOf(surveyRuntimeInSeconds), -1);
			driverViewPageAction.clickOnModeButton("", -1);
			driverViewPageAction.stopDrivingSurvey("", -1);

			// wait for a while before shutting down Analyzer.
			testEnvironmentAction.idleForSeconds(String.valueOf(30), -1);

			driverViewPageAction.clickOnModeButton("", -1);
			driverViewPageAction.clickOnShutdownButton("", -1);
			driverViewPageAction.clickOnShutdownConfirmButton("", -1);
			testEnvironmentAction.idleForSeconds(String.valueOf(10), -1);
			TestSetup.stopAnalyzer();
			TestSetup.startAnalyzer();
		}

		return Collections.synchronizedMap(testSurvey);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logoutQuitDriver();
		// Post run result to DB if enabled.
		postResultsToAutomationAPI();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		PageActionsStore.INSTANCE.clearStore();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	protected static WebDriver getDriver() {
		return driverThreadLocal.get();
	}

	private static void setDriver(WebDriver webDriver) {
		driverThreadLocal.set(webDriver);
	}

	protected static TestSetup getTestSetup() {
		return TestContext.INSTANCE.getTestSetup();
	}

	protected static void setTestSetup(TestSetup tstSetup) {
		TestContext.INSTANCE.setTestSetup(tstSetup);
	}

	protected static String getBaseURL() {
		return baseURLThreadLocal.get();
	}

	protected static void setBaseURL(String baseUrl) {
		baseURLThreadLocal.set(baseUrl);
	}

	protected static StringBuilder getExtentReportFilePath() {
		return extentReportFilePathThreadLocal.get();
	}

	public static boolean isDebug() {
		return debug;
	}

	public static void setDebug(boolean debug) {
		BaseTest.debug = debug;
	}

	public static ScreenShotOnFailure getScreenCapture() {
		return screenCaptureThreadLocal.get();
	}

	public static void setScreenCapture(ScreenShotOnFailure screenCapture) {
		screenCaptureThreadLocal.set(screenCapture);
	}

	public static LoginPage getLoginPage() {
		return loginPage;
	}

	public static void setLoginPage(LoginPage loginPage) {
		BaseTest.loginPage = loginPage;
	}

	public static HomePage getHomePage() {
		return homePage;
	}

	public static void setHomePage(HomePage homePage) {
		BaseTest.homePage = homePage;
	}
}