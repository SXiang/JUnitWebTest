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
import org.junit.Assert;
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
import common.source.ThreadLocalStore;
import surveyor.dataaccess.source.Analyzer;
import surveyor.dataaccess.source.Analyzer.CapabilityType;
import surveyor.dataaccess.source.Customer;
import surveyor.dataaccess.source.SurveyorUnit;
import surveyor.dataprovider.DataAnnotations;
import surveyor.dbseed.source.DbSeedExecutor;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.PageActionsStore;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.entities.ComplianceReportEntity;
import surveyor.scommon.entities.ReportsSurveyInfo;
import surveyor.scommon.entities.BaseReportEntity.ReportModeFilter;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.scommon.source.DriverViewPage.SurveyType;
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
			postTestMethodProcessing();
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
			setTestSetup(ThreadLocalStore.getTestSetup());
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
			ScreenShotOnFailure screenShotOnFailure = ThreadLocalStore.getScreenShotOnFailure();
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
		ExtentReports report = getExtentReport(className);
		setExtentTest(report.startTest(methodName), className);
		getExtentTest(className).assignCategory(TestContext.INSTANCE.getTestRunCategory());
		getExtentTest(className).log(LogStatus.INFO, firstLogLine);
		getExtentTest(className).log(LogStatus.INFO, String.format("Starting test.. [Start Time:%s]",
				DateUtility.getCurrentDate()));
		TestContext.INSTANCE.setTestClassName(className);
	}

	public static void reportTestFinished(String className) {
		Log.method("reportTestFinished", className);
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
		Log.method("reportTestFailed", e, className);
		BaseTest.reportTestLogMessage(className);
		getScreenCapture().takeScreenshots(getDriver(), className, true /*takeBrowserScreenShot*/, LogStatus.ERROR);
		Log.error("_FAIL_ Exception: " + ExceptionUtility.getStackTraceString(e));
		TestContext.INSTANCE.setTestStatus("FAIL");
		getExtentTest(className).log(LogStatus.FAIL, "FAILURE: " + ExceptionUtility.getStackTraceString(e));
	}

	public static void reportTestSucceeded(String className) {
		Log.method("reportTestSucceeded", className);
		Log.info("_PASS_ ");
		TestContext.INSTANCE.setTestStatus("PASS");
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
		return createTestAccount(testCase, CapabilityType.IsotopicMethane);
	}
	
	public Map<String, String> createTestAccount(String testCase, CapabilityType analyzerType){
		return createTestAccount(testCase,  null, analyzerType, true, true);
	}
	
	public Map<String, String> createTestAccount(String testCase, boolean addTestSurveyor){
		return createTestAccount(testCase, addTestSurveyor, true);
	}

	public Map<String, String> createTestAccount(String testCase, boolean addTestSurveyor, boolean fetchAnalyzerFromPool){
		return createTestAccount(testCase,  null, CapabilityType.IsotopicMethane, addTestSurveyor, fetchAnalyzerFromPool);
	}

	public Map<String, String> createTestAccount(String testCase, LicensedFeatures[] lfsToExclude){
		return createTestAccount(testCase, lfsToExclude, true);
	}

	public Map<String, String> createTestAccount(String testCase, LicensedFeatures[] lfsToExclude, boolean addTestSurveyor){
		return createTestAccount(testCase, lfsToExclude, addTestSurveyor, true /*fetchAnalyzerFromPool*/);
	}
	public Map<String, String> createTestAccount(String testCase, LicensedFeatures[] lfsToExclude, boolean addTestSurveyor, boolean fetchAnalyzerFromPool){
		return createTestAccount(testCase, lfsToExclude, CapabilityType.IsotopicMethane, addTestSurveyor, true /*fetchAnalyzerFromPool*/);
	}
	public Map<String, String> createTestAccount(String testCase, LicensedFeatures[] lfsToExclude, CapabilityType analyzerType, boolean addTestSurveyor, boolean fetchAnalyzerFromPool){
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
		String analyzerSharedKey = analyzerName + "Key";

		if (fetchAnalyzerFromPool) {
			// Fetch Analyzer from pool. Delete analyzer if already exists in DB.
			analyzerName = AnalyzerSerialNumberPool.INSTANCE.fetchNext();
			Log.info(String.format("Fetched Analyzer with serial number-'%s' from pool", analyzerName));
			Analyzer analyzer = new Analyzer().getBySerialNumber(analyzerName);
			if (analyzer != null) {
				analyzerSharedKey = analyzer.getSharedKey();
				Log.info(String.format("Analyzer with serial number-'%s', sharedKey-'%s' fetched from pool ALREADY EXISTS in DB. "
						+ "Deleting Analyzer.", analyzerName, analyzerSharedKey));
				analyzer.cascadeDeleteAnalyzer();
			}
		}

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

		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		ManageCustomersPage	manageCustomersPage = new ManageCustomersPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(),  manageCustomersPage);
		manageCustomersPage.open();
		if(!manageCustomersPage.addNewCustomer(customerName, eula, true,lfs)){
			fail(String.format("Failed to add a new customer %s, %s, %s",customerName, eula, true));
		}

		ManageLocationsPage	manageLocationsPage = new ManageLocationsPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(), manageLocationsPage);
		manageLocationsPage.open();
		if(!manageLocationsPage.addNewLocation(locationName, customerName, cityName)){
			fail(String.format("Failed to add a new location %s, %s, %s",locationName, customerName, cityName));
		}

		addTestUser(customerName, userName, userPassword, userRole, locationName);

		Customer customer = Customer.getCustomer(customerName);
		testAccount.put("customerId", customer.getId());
		
		if(!addTestSurveyor){
			return testAccount;
		}

		testAccount.put("analyzerSharedKey", analyzerSharedKey);
		testAccount.put("analyzerName", analyzerName);
		testAccount.put("analyzerType", analyzerType.toString());
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
		Analyzer analyzer = new Analyzer().getBySerialNumber(analyzerName);
		analyzer.updateCapabilityType(analyzerType);

		manageRefGasBottlesPage.open();
		if(!manageRefGasBottlesPage.addNewRefGasBottle(lotNum, isoValue, customerName, locationName, surveyorName)){
			fail(String.format("Failed to add a new analyzer %s, %s, %s, %s, %s",lotNum, isoValue, customerName, locationName, surveyorName));
		}

		return Collections.synchronizedMap(testAccount);
	}

	public void addTestUser(String customerName, String userName, String userPassword, String userRole, String locationName){
		ManageUsersPage	manageUsersPage = new ManageUsersPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(), manageUsersPage);
		manageUsersPage.open();
		if(!manageUsersPage.addNewCustomerUser(customerName, userName, userPassword, userRole, locationName)){
			fail(String.format("Failed to add a new customer user %s, %s, %s, %s, %s",customerName, userName, userPassword, userRole, locationName));
		}
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
		ReportModeFilter[] reportMode = {ReportModeFilter.Standard, ReportModeFilter.Standard, ReportModeFilter.RapidResponse, ReportModeFilter.Manual, ReportModeFilter.Analytics};
		SurveyModeFilter[] surveyMode = {SurveyModeFilter.Standard, SurveyModeFilter.Operator, SurveyModeFilter.RapidResponse, SurveyModeFilter.Manual, SurveyModeFilter.Analytics};
		SurveyModeFilter[] defaultTestSurveyModeFilter =  {SurveyModeFilter.Standard, SurveyModeFilter.Operator, SurveyModeFilter.RapidResponse, SurveyModeFilter.Manual};
		if(surveyModeFilter==null||surveyModeFilter.length==0){
			surveyModeFilter = defaultTestSurveyModeFilter;
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
			ComplianceReportEntity rpt = (ComplianceReportEntity) complianceReportsPageAction.fillWorkingDataForReports(testRowID);
			rpt.setRptTitle(rpt.getRptTitle() + rm.toString()+sm.toString());
			rpt.setReportModeFilter(rm);
			rpt.setSurveyModeFilter(sm);
			rpt.setStrCreatedBy(userName);

			for(ReportsSurveyInfo smf:rpt.getSurveyInfoList()){
				if(smf!=null) {
					smf.setSurveyModeFilter(sm);
					if (surveyTag != null && surveyTag != "") {
						smf.setTag(surveyTag);
					}
				}
			}
			testReport.put(sm.toString()+"Title", rpt.getRptTitle());
			complianceReportsPage.addNewReport(rpt, true);
			String reportName = complianceReportsPage.waitForReportGenerationtoCompleteAndGetReportName(rpt.getRptTitle(), rpt.getStrCreatedBy(), null, null);
			testReport.put(sm.toString()+"ReportName", reportName);
		}

		return Collections.synchronizedMap(testReport);
	}

	public Map<String, String> addTestSurvey(String analyzerName, String analyzerSharedKey) throws Exception{
		return addTestSurvey(analyzerName, analyzerSharedKey, CapabilityType.IsotopicMethane);
	}
	public Map<String, String> addTestSurvey(String analyzerName, String analyzerSharedKey, CapabilityType analyzerType) throws Exception{
		return addTestSurvey(analyzerName, analyzerSharedKey, analyzerType, getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
	}
	public Map<String, String> addTestSurvey(String analyzerName, String analyzerSharedKey, String userName, String password, SurveyType... surveyTypes) throws Exception{
		return addTestSurvey(analyzerName, analyzerSharedKey, CapabilityType.IsotopicMethane, userName, password, surveyTypes);
	}
	public Map<String, String> addTestSurvey(String analyzerName, String analyzerSharedKey, CapabilityType analyzerType, String userName, String password, SurveyType... surveyTypes) throws Exception{
		int surveyRuntimeInSeconds = 2;
		return addTestSurvey(analyzerName, analyzerSharedKey, analyzerType, userName, password, surveyRuntimeInSeconds, surveyTypes);
	}
	
	public Map<String, String> addTestSurvey(String analyzerName, String analyzerSharedKey, String userName, String password, int surveyRuntimeInSeconds, SurveyType... surveyTypes) throws Exception{
		return addTestSurvey(analyzerName, analyzerSharedKey, CapabilityType.IsotopicMethane, userName, password, surveyRuntimeInSeconds, surveyTypes);
	}
	public Map<String, String> addTestSurvey(String analyzerName, String analyzerSharedKey, CapabilityType analyzerType, String userName, String password, int surveyRuntimeInSeconds, SurveyType... surveyTypes) throws Exception{
		String replayScriptDefnFile = "replay-db3.defn";
		String replayScriptEthaneDefnFile = "replay-db3-eth.defn";
		String replayScriptDB3File = "Surveyor.db3";
		String replayAnalyticsScriptDB3File = "AnalyticsSurvey-RFADS2024-03.db3";
		int[] surveyRowIDs = {3, 5, 9, 31, 30, 62};
		SurveyType[] surveyType = {SurveyType.Standard, SurveyType.Operator, SurveyType.RapidResponse, SurveyType.Assessment, SurveyType.Manual, SurveyType.Analytics};
		SurveyType[] defaultTestSurveyType = {SurveyType.Standard, SurveyType.Operator, SurveyType.RapidResponse, SurveyType.Assessment, SurveyType.Manual};
		String[] db3Type = {"P3200", "P3200","P3200","P3200","P3200","P3300"};
		
		if(surveyTypes==null||surveyTypes.length==0){
			surveyTypes = defaultTestSurveyType;
		}

		HashMap<String, String> testSurvey = new HashMap<String, String>();
		testSurvey.put("analyzerName", analyzerName);
		testSurvey.put("analyzerSharedKey", analyzerSharedKey);

		String surveyorUnitId = new Analyzer().getBySerialNumber(analyzerName).getSurveyorUnitId().toString();
		String surveyorName = new SurveyorUnit().getById(surveyorUnitId).getDescription();

		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, password);
		DriverViewPageActions driverViewPageAction = ActionBuilder.createDriverViewPageAction();

		final int TEST_ENVIRONMENT_DATA_ROW_ID = 3;
		TestEnvironmentActions testEnvironmentAction = ActionBuilder.createTestEnvironmentAction();
		
		/*Step 1. setup analyzer configuration */
		updateAnalyzerConfiguration(testEnvironmentAction, analyzerName, analyzerSharedKey, TEST_ENVIRONMENT_DATA_ROW_ID);

		for(SurveyType st : surveyTypes){
			int i=0;
			while(i<surveyType.length){
				if(st.equals(surveyType[i])){
					break;
				}
				i++;
			}
			if(i==surveyType.length){
				Log.warn("SurveyType '"+st+"' is not valid");
				continue;
			}
			if(!CapabilityType.fromString(db3Type[i]).equals(analyzerType)){
				continue;
			}
			
			String db3file = replayScriptDB3File;
			String db3DefnFile = replayScriptDefnFile;
			if(analyzerType.equals(CapabilityType.Ethane)){
				db3DefnFile = replayScriptEthaneDefnFile;
			}
			if(st.equals(SurveyType.Analytics)){
				db3file = replayAnalyticsScriptDB3File;
			}
			int surveyRowID = surveyRowIDs[0];
			for(int j=0; j<surveyType.length; j++){
				if(st.equals(surveyType[j])){
					surveyRowID = surveyRowIDs[j];
					break;
				}
			}

			/* Step 2: startAnalyzerSurvey */
			startAnalyzerSurvey(testEnvironmentAction, driverViewPageAction, db3DefnFile, db3file, surveyRowID, surveyRuntimeInSeconds);
			/* Step 3: stopAnalyzerSurvey */
			stopAnalyzerSurvey(testEnvironmentAction, driverViewPageAction,analyzerName, analyzerSharedKey, surveyorName);
			testSurvey.put(st.name()+"Tag", DriverViewPageActions.workingDataRow.get().surveyTag);
		}

		/* Step 4: stopAnalyzer */
		TestSetup.stopAnalyzer();

		return Collections.synchronizedMap(testSurvey);
	}

	protected void updateAnalyzerConfiguration(TestEnvironmentActions testEnvironmentAction, String analyzerName, String analyzerSharedKey, int TEST_ENVIRONMENT_DATA_ROW_ID) throws Exception{
		//Using analyzer created at runtime for this test - impacts open Rrl of driver view
		TestEnvironmentActions.workingDataRow.set(testEnvironmentAction.getDataReader().getDataRow(TEST_ENVIRONMENT_DATA_ROW_ID));
		TestEnvironmentActions.workingDataRow.get().analyzerSerialNumber = analyzerName;
		TestEnvironmentActions.workingDataRow.get().analyzerSharedKey = analyzerSharedKey;
		TestEnvironmentActions.workingDataRow.get().analyzerRowID = "";
		TestSetup.updateAnalyzerConfiguration(TestContext.INSTANCE.getBaseUrl(),
			analyzerName, analyzerSharedKey);
	}

	protected void startAnalyzerSurvey(TestEnvironmentActions testEnvironmentAction, DriverViewPageActions driverViewPageAction, 
			String db3DefnFile, String db3file, int surveyRowID, int surveyRuntimeInSeconds) throws Exception{
		TestSetup.restartAnalyzer();
		driverViewPageAction.open("", -1);
		driverViewPageAction.waitForConnectionToComplete("", -1);

		TestSetup.replayDB3Script(db3DefnFile, db3file);
		driverViewPageAction.clickOnModeButton("", -1);
		driverViewPageAction.startDrivingSurvey("", surveyRowID);
		testEnvironmentAction.idleForSeconds(String.valueOf(surveyRuntimeInSeconds), -1);
	}

	protected void stopAnalyzerSurvey(TestEnvironmentActions testEnvironmentAction, DriverViewPageActions driverViewPageAction, 
			String analyzerName, String analyzerSharedKey, String surveyorName) throws Exception{
		driverViewPageAction.clickOnModeButton("", -1);
		driverViewPageAction.stopDrivingSurvey("", -1);

		// wait for a while before shutting down Analyzer.
		testEnvironmentAction.idleForSeconds(String.valueOf(30), -1);

		driverViewPageAction.clickOnModeButton("", -1);
		driverViewPageAction.clickOnShutdownButton("", -1);
		driverViewPageAction.clickOnShutdownConfirmButton("", -1);
		testEnvironmentAction.idleForSeconds(String.valueOf(10), -1);
		TestContext.INSTANCE.getTestSetup().checkPostSurveySessionFromDB3(analyzerName, analyzerSharedKey, surveyorName);
	}
	protected static boolean pushGisData(String customerId) {
		try {
			// Add GIS seed for customer.
			DbSeedExecutor.executeGisSeed(customerId);
		} catch (Exception ex) {
			Assert.fail(String.format("Exception: %s", ExceptionUtility.getStackTraceString(ex)));
		}
		return true;
	}
	
	protected static boolean cleanUpGisData(String customerId){
		try {
				DbSeedExecutor.cleanUpGisSeed(customerId);
			} catch (Exception e) {
				Log.error(String.format("Error in cleanUpGisSeed. Exception - %s", ExceptionUtility.getStackTraceString(e)));
				return false;
			}
		return true;
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