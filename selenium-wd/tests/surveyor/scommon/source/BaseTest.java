package surveyor.scommon.source;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.source.DateUtility;
import common.source.ExtentReportGenerator;
import common.source.Log;
import common.source.RegexUtility;
import common.source.ScreenShotOnFailure;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.TestSetupFactory;
import surveyor.dataprovider.DataAnnotations;
import surveyor.scommon.actions.PageActionsStore;

public class BaseTest {

	private static List<WebDriver> spawnedWebDrivers = Collections.synchronizedList(new ArrayList<WebDriver>());

	private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<WebDriver>();     
	//private static ThreadLocal<TestSetup> testSetupThreadLocal = new ThreadLocal<TestSetup>();
	private static ThreadLocal<String> baseURLThreadLocal = new ThreadLocal<String>();

	private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<ExtentTest>(); 
	private static ThreadLocal<StringBuilder> extentReportFilePathThreadLocal = new ThreadLocal<StringBuilder>();
	private static ThreadLocal<ScreenShotOnFailure> screenCaptureThreadLocal = new ThreadLocal<ScreenShotOnFailure>();

	public static String screenShotsDir;
	public static String screenShotsSubFolder = "screenshots/";
	private static boolean debug;

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
		}

		 @Override
		 protected void succeeded(Description description) {
			 BaseTest.reportTestSucceeded();
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
		if (getTestSetup()==null) {
			setTestSetup(TestSetupFactory.getTestSetup());
			Log.info(String.format("[THREAD Debug Log].. Set TestSetup - '%s'", getTestSetup()));
		}
		if (getBaseURL() == null) {		
			setBaseURL(getTestSetup().getBaseUrl());
			Log.info(String.format("[THREAD Debug Log].. Set BaseURL - '%s'", getBaseURL()));
		}
		
		setDebug(getTestSetup().isRunningDebug());

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

		if (getScreenCapture() == null) {
			setScreenCapture(TestSetupFactory.getScreenShotOnFailure());
			Log.info(String.format("[THREAD Debug Log].. Set ScreenCapture - '%s'", getScreenCapture()));
		}
	}
	
	private static ExtentReports getExtentReport(String className) {
		Log.info(String.format("[THREAD Debug Log] - calling getExtentReport(className=[%s])", className));
		ExtentReports extentReport = TestContext.INSTANCE.getReport();
		if (extentReport == null) {
		   StringBuilder outReportFilePath = new StringBuilder();
		   extentReport = ExtentReportGenerator.createExtentReport(className, outReportFilePath);
		   extentReportFilePathThreadLocal.set(outReportFilePath);
		   TestContext.INSTANCE.setReport(extentReport);
		}
		return extentReport;
	}
		
	public static void reportTestStarting(Description description) {
		reportTestStarting(description.getClassName(), description.getMethodName(), description.toString());
	}

	public static void reportTestStarting(String className, String methodName, String firstLogLine) {
		Log.info("[THREAD Debug Log] - calling reportTestStarting()");
		ExtentReports report = getExtentReport(className);
		setExtentTest(report.startTest(methodName), className);
		getExtentTest().assignCategory(TestContext.INSTANCE.getTestRunCategory());
		getExtentTest().log(LogStatus.INFO, firstLogLine);
		getExtentTest().log(LogStatus.INFO, String.format("Starting test.. [Start Time:%s]", 
				DateUtility.getCurrentDate()));
	}

	public static void reportTestFinished(String className) {
		Log.info("[THREAD Debug Log] - calling reportTestFinished()");
		ExtentReports report = getExtentReport(className);
		getExtentTest().log(LogStatus.INFO, String.format("Finished test. [End Time:%s]", 
				DateUtility.getCurrentDate()));
		report.endTest(getExtentTest());
		report.flush();
	}

	public static void reportTestLogMessage() {
		Log.info("[THREAD Debug Log] - calling reportTestLogMessage()");
		List<String> testMessage = TestContext.INSTANCE.getTestMessage();
		for(String message:testMessage){
			getExtentTest().log(LogStatus.WARNING, "Extra messages before the failure", "Log Message: " + message);
		}
	}
	
	public static void reportTestFailed(Throwable e) {
		Log.info("[THREAD Debug Log] - calling reportTestFailed()");
		BaseTest.reportTestLogMessage();			
		getScreenCapture().takeScreenshot(getDriver());
		Log.error("_FAIL_ Exception: "+e);
		TestContext.INSTANCE.setTestStatus("FAIL");
		getExtentTest().log(LogStatus.FAIL, "FAILURE: " + e.getMessage());
	}

	public static void reportTestError(String errorMsg) {
		Log.info("[THREAD Debug Log] - calling reportTestError()");
		getExtentTest().log(LogStatus.ERROR, "ERROR: " + errorMsg);
	}

	public static void reportTestSucceeded() {
		Log.info("[THREAD Debug Log] - calling reportTestSucceeded()");
		Log.info("_PASS_ ");
		getExtentTest().log(LogStatus.PASS, "PASSED");
	}

	protected static ExtentTest getExtentTest() {
		return extentTestThreadLocal.get();
	}
	
	private static void setExtentTest(ExtentTest test, String className) {
		BaseTest.extentTestThreadLocal.set(test);
		TestContext.INSTANCE.setExtentTest(test, className);
	}

	public static void logoutQuitDriver() {
		Log.method("BaseTest.logoutQuitDriver");
		if(getDriver() == null){
			return;
		}

		getDriver().quit();
		setDriver(null);
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
}