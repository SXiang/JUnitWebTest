package surveyor.scommon.source;

import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.CUSTOMERNAMEPREFIX;
import static surveyor.scommon.source.SurveyorConstants.CUSUSERROLEUA;
import static surveyor.scommon.source.SurveyorConstants.EULASTRING;
import static surveyor.scommon.source.SurveyorConstants.REGBASEUSERNAME;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
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
import common.source.FileUtility;
import common.source.Log;
import common.source.RegexUtility;
import common.source.ScreenShotOnFailure;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.dataprovider.DataAnnotations;
import surveyor.scommon.actions.ActionBuilder;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.DriverViewPageActions;
import surveyor.scommon.actions.PageActionsStore;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.ComplianceReportsPage.ComplianceReportButtonType;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;

public class BaseTest {

	public static WebDriver driver;
	public static TestSetup testSetup;
	public static String baseURL;
	public static String screenShotsDir;
	public static String screenShotsSubFolder = "screenshots/";
	public static boolean debug;

	public static LoginPage loginPage;
	public static HomePage homePage;

	protected static ExtentTest test = null;
	protected static StringBuilder extentReportFile = null;
	protected static ScreenShotOnFailure screenCapture;

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
			BaseTest.reportTestFailed(e);
			postTestMethodProcessing();
		}

		 @Override
		 protected void succeeded(Description description) {
			 BaseTest.reportTestSucceeded();
			 postTestMethodProcessing();
		}
	};

	public static void initializeTestObjects(){
		baseURL = testSetup.getBaseUrl();
		debug = testSetup.isRunningDebug();
		TestContext.INSTANCE.setTestSetup(testSetup);
		driver = testSetup.getDriver();

		try {
			screenShotsDir = TestSetup.getExecutionPath() + TestSetup.reportDir + testSetup.getTestReportCategory();
		} catch (IOException e) {
			Log.error(e.toString());
		}

		Path screenShotsPath = Paths.get(screenShotsDir, screenShotsSubFolder);
		FileUtility.createDirectoryIfNotExists(screenShotsPath.toString());
		screenCapture = new ScreenShotOnFailure(screenShotsSubFolder,
				screenShotsDir, testSetup.isRemoteBrowser);

		driver.manage().deleteAllCookies();

		loginPage = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  loginPage);

		homePage = new HomePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  homePage);
	}

	private static ExtentReports getExtentReport(String className) {
		   ExtentReports extentReport = TestContext.INSTANCE.getReport();
		   if (extentReport == null) {
			   extentReportFile = new StringBuilder();
			   extentReport = TestSetup.createExtentReport(className, extentReportFile);
			   TestContext.INSTANCE.setReport(extentReport);
		   }
		   return extentReport;
		}

		public static void reportTestStarting(Description description) {
			reportTestStarting(description.getClassName(), description.getMethodName(), description.toString());
		}

		public static void reportTestStarting(String className, String methodName, String firstLogLine) {
			ExtentReports report = getExtentReport(className);
			setExtentTest(report.startTest(methodName), className);
			getExtentTest().assignCategory(TestContext.INSTANCE.getTestRunCategory());
			getExtentTest().log(LogStatus.INFO, firstLogLine);
			getExtentTest().log(LogStatus.INFO, String.format("Starting test.. [Start Time:%s]",
					DateUtility.getCurrentDate()));
		}

		public static void reportTestFinished(String className) {
			ExtentReports report = getExtentReport(className);
			getExtentTest().log(LogStatus.INFO, String.format("Finished test. [End Time:%s]",
					DateUtility.getCurrentDate()));
			report.endTest(getExtentTest());
			report.flush();
		}

		public static void reportTestLogMessage() {
			ArrayList<String> testMessage = TestContext.INSTANCE.getTestMessage();
			for(String message:testMessage){
				getExtentTest().log(LogStatus.WARNING, "Extra messages before the failure", "Log Message: " + message);
			}
		}

		public static void reportTestFailed(Throwable e) {
			BaseTest.reportTestLogMessage();
			screenCapture.takeScreenshot(driver);
			Log.error("_FAIL_ Exception: " + ExceptionUtility.getStackTraceString(e));
			TestContext.INSTANCE.setTestStatus("FAIL");
			getExtentTest().log(LogStatus.FAIL, "FAILURE: " + ExceptionUtility.getStackTraceString(e));
		}

		public static void reportTestError(String errorMsg) {
			getExtentTest().log(LogStatus.ERROR, "ERROR: " + errorMsg);
		}

		public static void reportTestSucceeded() {
			Log.info("_PASS_ ");
			getExtentTest().log(LogStatus.PASS, "PASSED");
		}

		public static ExtentTest getExtentTest() {
			return test;
		}

		private static void setExtentTest(ExtentTest test, String className) {
			BaseTest.test = test;
			TestContext.INSTANCE.setExtentTest(test, className);
		}

	public static void logoutQuitDriver() {
		if(driver == null){
			return;
		}
		if (!driver.getTitle().equalsIgnoreCase("Login")) {
			homePage.open();
			homePage.logout();
		}

		driver.quit();
		driver = null;
	}

	public static void postResultsToAutomationAPI() {
		if (extentReportFile!=null) {
			if (TestContext.INSTANCE.getTestSetup().isAutomationReportingApiEnabled()) {
				TestContext.INSTANCE.getTestSetup().postAutomationRunResult(extentReportFile.toString());
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

	public Map<String, String> createTestAccount(String testCase, LicensedFeatures[] lfsToExclude){
		return createTestAccount(testCase, lfsToExclude, true);
	}
	
	public Map<String, String> createTestAccount(String testCase, LicensedFeatures[] lfsToExclude, boolean addTestSurveyor){
		String uniqueNumber = testSetup.getFixedSizeRandomNumber(6);
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
		String lotNum = testSetup.getRandomNumber() + testCase;
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
		

		
		ManageCustomersPage	manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
		ManageUsersPage	manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageUsersPage);		
		ManageLocationsPage	manageLocationsPage = new ManageLocationsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageLocationsPage);
		
		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

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
		ManageSurveyorPage manageSurveyorPage = new ManageSurveyorPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageSurveyorPage);
		ManageAnalyzersPage manageAnalyzersPage = new ManageAnalyzersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageAnalyzersPage);	
		ManageRefGasBottlesPage manageRefGasBottlesPage = new ManageRefGasBottlesPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  manageRefGasBottlesPage);
		
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
		return testAccount;
	}

	public Map<String, String> addTestReport() throws Exception{
		return addTestReport(testSetup.getLoginUser(), testSetup.getLoginPwd());
	}
	public Map<String, String> addTestReport(String userName, String Password, SurveyModeFilter... surveyModeFilter)throws Exception{
		int testRowID = 115;
		ReportModeFilter[] reportMode = {ReportModeFilter.Standard, ReportModeFilter.Standard, ReportModeFilter.RapidResponse, ReportModeFilter.Manual};
		SurveyModeFilter[] surveyMode = {SurveyModeFilter.Standard, SurveyModeFilter.Operator, SurveyModeFilter.RapidResponse, SurveyModeFilter.Manual};
		if(surveyModeFilter==null||surveyModeFilter.length==0){
			surveyModeFilter = SurveyModeFilter.values();
		}
		
		HashMap<String, String> testReport = new HashMap<String, String>();

		loginPage.open();
		loginPage.loginNormalAs(userName, Password);
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
				if(smf!=null)
					smf.setSurveyModeFilter(sm);
			}
			testReport.put(sm.toString()+"Title", rpt.rptTitle);
			
			complianceReportsPage.addNewReport(rpt, true);
			
			/* Enable waitForReportGenerationtoComplete and remove clickComplianceReportButton after DE2382 is done */
//			complianceReportsPage.waitForReportGenerationtoComplete(rpt.rptTitle, rpt.strCreatedBy);
			complianceReportsPage.clickComplianceReportButton(rpt.rptTitle, rpt.strCreatedBy, ComplianceReportButtonType.Cancel);
		}
		return testReport;

	}
	public Map<String, String> addTestSurvey(String analyzerName, String analyzerSharedKey) throws Exception{
		return addTestSurvey(analyzerName, analyzerSharedKey, testSetup.getLoginUser(), testSetup.getLoginPwd());
	}
	public Map<String, String> addTestSurvey(String analyzerName, String analyzerSharedKey, String userName, String Password, SurveyType... surveyTypes) throws Exception{
		int surveyRuntimeInSeconds = 2;
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

		loginPage.open();
		loginPage.loginNormalAs(userName, Password);
		DriverViewPageActions driverViewPageAction = ActionBuilder.createDriverViewPageAction();
		TestEnvironmentActions testEnvironmentAction = ActionBuilder.createTestEnvironmentAction();
		//Using analyzer created at runtime for this test - impacts open Rrl of driver view
		TestEnvironmentActions.workingDataRow = testEnvironmentAction.getDataReader().getDataRow(3);
		TestEnvironmentActions.workingDataRow.analyzerSerialNumber = analyzerName;
		TestEnvironmentActions.workingDataRow.analyzerSharedKey = analyzerSharedKey;
		TestEnvironmentActions.workingDataRow.analyzerRowID = "";
		
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
			testSurvey.put(st.toString()+"Tag", DriverViewPageActions.workingDataRow.surveyTag);
			testEnvironmentAction.idleForSeconds(String.valueOf(surveyRuntimeInSeconds), -1);
			driverViewPageAction.clickOnModeButton("", -1);
			driverViewPageAction.stopDrivingSurvey("", -1);
			driverViewPageAction.clickOnModeButton("", -1);
			driverViewPageAction.clickOnShutdownButton("", -1);
			driverViewPageAction.clickOnShutdownConfirmButton("", -1);
			testEnvironmentAction.idleForSeconds(String.valueOf(10), -1);
			TestSetup.stopAnalyzer();
			TestSetup.startAnalyzer();
		}

		return testSurvey;
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

}
