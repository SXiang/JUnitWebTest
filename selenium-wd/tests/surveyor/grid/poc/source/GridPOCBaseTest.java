/**
 * 
 */
package surveyor.grid.poc.source;

import java.io.IOException;
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
import common.source.Log;
import common.source.LogCategory;
import common.source.RegexUtility;
import common.source.ScreenShotOnFailure;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.TestSetupFactory;
import surveyor.dataprovider.DataAnnotations;
import surveyor.scommon.actions.PageActionsStore;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.PageObjectFactory;

/**
 * @author zlu
 *
 */
public class GridPOCBaseTest {

	private static List<WebDriver> spawnedWebDrivers = new ArrayList<WebDriver>();
	
	private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<WebDriver>();     
	private static ThreadLocal<TestSetup> testSetupThreadLocal = new ThreadLocal<TestSetup>();
	private static ThreadLocal<LoginPage> loginPageThreadLocal = new ThreadLocal<LoginPage>();
	private static ThreadLocal<HomePage> homePageThreadLocal = new ThreadLocal<HomePage>();
	private static ThreadLocal<ScreenShotOnFailure> screenCaptureThreadLocal = new ThreadLocal<ScreenShotOnFailure>();
	private static ThreadLocal<String> baseURLThreadLocal = new ThreadLocal<String>();

	private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<ExtentTest>(); 
	private static ThreadLocal<StringBuilder> extentReportFilePathThreadLocal = new ThreadLocal<StringBuilder>();

	private static Boolean debug;
	
	private static ThreadLocal<Boolean> isTestRunningThreadLocal = new ThreadLocal<Boolean>();

	protected static final String SQAPICAD_AND_SQAPICSUP = "sqapicad@picarro.com,sqapicsup@picarro.com";
	
	// JUnit does NOT give a good way to detect which TestClass is executing.
	// So we watch for the Test method under execution and install simulator pre-reqs
	// if the test under execution is a Simulator test.
	// NOTE that all simulator tests MUST follow this naming pattern: TC*_SimulatorTest_* 
	@Rule
	public TestWatcher watcher = new TestWatcher() {
		@Override
		public void starting(Description description) {
			//Disabled in Prototype (ExtentReport).
			Log.info(String.format("Test-'%s.%s' is STARTING..", description.getClassName(), description.getMethodName()));
		}

		@Override
		public void finished(Description description) {
			//Disabled in Prototype (ExtentReport).
			Log.info(String.format("Test-'%s.%s' FINISHED", description.getClassName(), description.getMethodName()));
		}

		@Override
		protected void failed(Throwable e, Description description) {
			//Disabled in Prototype (ExtentReport).
			Log.error(String.format("Test-'%s.%s' FAILED!", description.getClassName(), description.getMethodName()));
		}

		 @Override
		 protected void succeeded(Description description) {
			//Disabled in Prototype (ExtentReport).
			Log.info(String.format("Test-'%s.%s' SUCCEEDED!", description.getClassName(), description.getMethodName()));
		}
	};

	/**
	 * This method is called by the 'main' thread.
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Log.info("[THREAD Debug Log] - Calling setUpBeforeClass()");
		setIsTestRunning(true);
		
		if (!TestSetup.isParallelBuildEnabled()) {
			TestSetup.stopChromeProcesses();
		}
		initializeTestObjects();
	}

	/**
	 * This method is called by the 'main' thread
	 * 
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Log.info("[THREAD Debug Log] - Calling tearDownAfterClass()");
		setIsTestRunning(false);

		logoutQuitDriver();		
		
		// Post run result to DB if enabled.
		postResultsToAutomationAPI();
	}

	/**
	 * This method is called by the 'worker' thread
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Log.info("[THREAD Debug Log] - Calling setup beforeTest()");
		PageActionsStore.INSTANCE.clearStore();
		
		initializeTestObjects();
	}

	/**
	 * This method is called by the 'worker' thread
	 * 
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		Log.info("[THREAD Debug Log] - Calling tearDown afterTest()");
	}
	
	protected static void initializeTestObjects() throws IOException {
		if (getTestSetup()==null) {
			setTestSetup(TestSetupFactory.getTestSetup());
			Log.info(String.format("[THREAD Debug Log].. Set TestSetup - '%s'", getTestSetup()));
		}
		if (getBaseURL() == null) {
			setBaseURL(getTestSetup().getBaseUrl());
			Log.info(String.format("[THREAD Debug Log].. Set BaseURL - '%s'", getBaseURL()));
		}
		setIsDebug(getTestSetup().isRunningDebug());
		
		TestContext.INSTANCE.setTestSetup(getTestSetup());
		
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

		if (TestSetupFactory.getScreenShotOnFailure() == null) {
			screenCaptureThreadLocal.set(TestSetupFactory.getScreenShotOnFailure());
			Log.info(String.format("[THREAD Debug Log].. Set ScreenCapture - '%s'", getScreenCapture()));
		}

		// Initialize the page objects
		initializePageObjects();
	}

	private static void initializePageObjects() {
		PageObjectFactory pageObjectFactory = new PageObjectFactory();
		if (getLoginPage() == null) {		
			setLoginPage(pageObjectFactory.getLoginPage());
			Log.info(String.format("[THREAD Debug Log].. Set LoginPage - '%s'", getLoginPage()));
		}
		
		if (getHomePage() == null) {
			setHomePage(pageObjectFactory.getHomePage());
			Log.info(String.format("[THREAD Debug Log].. Set HomePage - '%s'", getHomePage()));
		}
	}

	protected static void logoutQuitDriver() {
		Log.method("GridPOCBaseTest.logoutQuitDriver");
		// Logout after each test method.
		if (!getDriver().getTitle().equalsIgnoreCase("Login")) {
			Log.info(String.format("getDriver().getTitle()=%s", getDriver().getTitle()), LogCategory.VerboseLogging);
			Log.info(String.format("getDriver().getTitle().equalsIgnoreCase('Login')=%b", getDriver().getTitle().equalsIgnoreCase("Login")), 
					LogCategory.VerboseLogging);
			getHomePage().open();
			getHomePage().logout();
		}
		
		getDriver().quit();
	}

	protected static void postResultsToAutomationAPI() {
		if (getExtentReportFilePath() != null) {
			if (TestContext.INSTANCE.getTestSetup().isAutomationReportingApiEnabled()) {
				TestContext.INSTANCE.getTestSetup().postAutomationRunResult(getExtentReportFilePath().toString());
			}
		}
	}

	protected boolean isValidRunAsUser(String username, String functionName) {
		String runAsUsers = DataAnnotations.getRunAsUsers(getClass(), functionName);
		List<String> listUsers = RegexUtility.split(runAsUsers, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
		if (!listUsers.contains(username)) {
			return false;
		}
		return true;
	}

	protected static Boolean isDebug() {
		return debug;
	}

	protected static void setIsDebug(Boolean isDebug) {
		debug = isDebug;
	}

	protected static WebDriver getDriver() {
		return driverThreadLocal.get();
	}

	protected static LoginPage getLoginPage() {
		return loginPageThreadLocal.get();
	}

	protected static void setLoginPage(LoginPage loginPg) {
		loginPageThreadLocal.set(loginPg);
	}

	protected static HomePage getHomePage() {
		return homePageThreadLocal.get();
	}

	protected static void setHomePage(HomePage homePg) {
		homePageThreadLocal.set(homePg);
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
	
	protected static ScreenShotOnFailure getScreenCapture() {
		return screenCaptureThreadLocal.get();
	}

	protected static TestSetup getTestSetup() {
		return testSetupThreadLocal.get();
	}
	
	protected static ExtentTest getExtentTest() {
		return extentTestThreadLocal.get();
	}
	
	private static Boolean isTestRunning() {
		return isTestRunningThreadLocal.get();
	}

	private static void setIsTestRunning(Boolean isRunning) {
		isTestRunningThreadLocal.set(isRunning);
	}
	
	private static void setTestSetup(TestSetup tstSetup) {
		testSetupThreadLocal.set(tstSetup);
	}

	private static void setExtentTest(ExtentTest test) {
		GridPOCBaseTest.extentTestThreadLocal.set(test);
		TestContext.INSTANCE.setExtentTest(test);
	}

	private static ExtentReports getExtentReport(String className) {
		Log.info(String.format("[THREAD Debug Log] - calling getExtentReport(className=[%s])", className));
		ExtentReports extentReport = TestContext.INSTANCE.getReport();
		if (extentReport == null) {
		   StringBuilder outReportFilePath = new StringBuilder();
		   extentReport = TestSetup.createExtentReport(className, outReportFilePath);
		   extentReportFilePathThreadLocal.set(outReportFilePath);
		   TestContext.INSTANCE.setReport(extentReport);
		}
		return extentReport;
	}
	
	protected static void reportTestStarting(Description description) {
		Log.info("[THREAD Debug Log] - calling reportTestStarting()");
		reportTestStarting(description.getClassName(), description.getMethodName(), description.toString());
	}

	protected static void reportTestStarting(String className, String methodName, String firstLogLine) {
		ExtentReports report = getExtentReport(className);
		setExtentTest(report.startTest(methodName));
		getExtentTest().assignCategory(TestContext.INSTANCE.getTestRunCategory());
		getExtentTest().log(LogStatus.INFO, firstLogLine);
		getExtentTest().log(LogStatus.INFO, String.format("Starting test.. [Start Time:%s]", 
				DateUtility.getCurrentDate()));
	}

	protected static void reportTestFinished(String className) {
		Log.info("[THREAD Debug Log] - calling reportTestFinished()");
		ExtentReports report = getExtentReport(className);
		getExtentTest().log(LogStatus.INFO, String.format("Finished test. [End Time:%s]", 
				DateUtility.getCurrentDate()));
		report.endTest(getExtentTest());
		report.flush();
	}

	protected static void reportTestLogMessage() {
		Log.info("[THREAD Debug Log] - calling reportTestLogMessage()");
		List<String> testMessage = TestContext.INSTANCE.getTestMessage();
		for(String message:testMessage){
			getExtentTest().log(LogStatus.WARNING, "Extra messages before the failure", "Log Message: " + message);
		}
	}
	
	protected static void reportTestFailed(Throwable e) {
		Log.info("[THREAD Debug Log] - calling reportTestFailed()");
		getExtentTest().log(LogStatus.FAIL, "FAILURE: " + e.getMessage());
	}

	protected static void reportTestError(String errorMsg) {
		Log.info("[THREAD Debug Log] - calling reportTestError()");
		getExtentTest().log(LogStatus.ERROR, "ERROR: " + errorMsg);
	}

	protected static void reportTestSucceeded() {
		Log.info("[THREAD Debug Log] - calling reportTestSucceeded()");
		getExtentTest().log(LogStatus.PASS, "PASSED");
	}
}