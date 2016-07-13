/**
 * 
 */
package surveyor.scommon.source;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import common.source.DateUtility;
import common.source.FileUtility;
import common.source.Log;
import common.source.RegexUtility;
import common.source.ScreenShotOnFailure;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.dataprovider.DataAnnotations;
import surveyor.scommon.actions.PageActionsStore;

/**
 * @author zlu
 *
 */
public class SurveyorBaseTest {
	public static WebDriver driver;
	public static TestSetup testSetup;
	public static String baseURL;
	public static String screenShotsDir;
	public static String screenShotsSubFolder = "screenshots/";
	public static boolean debug;

	public static LoginPage loginPage;
	public static HomePage homePage;
	
	private static ExtentTest test = null; 
	private static StringBuilder extentReportFile = null;
	private static ScreenShotOnFailure screenCapture;
	protected static final String SQAPICAD_AND_SQAPICSUP = "sqapicad@picarro.com,sqapicsup@picarro.com";
	
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
			SurveyorBaseTest.reportTestConsoleMessage();			
			screenCapture.takeScreenshot(driver);
			Log.error("Exception: "+e+" Description: "+description);
			SurveyorBaseTest.reportTestFailed(e);
		}

		 @Override
		 protected void succeeded(Description description) {
			 SurveyorBaseTest.reportTestSucceeded();
		}
	};

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
		setExtentTest(report.startTest(methodName));
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

	public static void reportTestConsoleMessage() {
		ArrayList<String> testMessage = TestContext.INSTANCE.getTestMessage();
		for(String message:testMessage){
			getExtentTest().log(LogStatus.WARNING, "Extra messages before the failure", "Console Message: " + message);
		}
	}
	
	public static void reportTestFailed(Throwable e) {
		getExtentTest().log(LogStatus.FAIL, "FAILURE: " + e.getMessage());
	}

	public static void reportTestError(String errorMsg) {
		getExtentTest().log(LogStatus.ERROR, "ERROR: " + errorMsg);
	}

	public static void reportTestSucceeded() {
		getExtentTest().log(LogStatus.PASS, "PASSED");
	}

	public static ExtentTest getExtentTest() {
		return test;
	}

	private static void setExtentTest(ExtentTest test) {
		SurveyorBaseTest.test = test;
		TestContext.INSTANCE.setExtentTest(test);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestSetup.stopChromeProcesses();
		initializeTestObjects();
	}

	public static void initializeTestObjects() throws IOException {
		testSetup = new TestSetup();
		driver = testSetup.getDriver();
		baseURL = testSetup.getBaseUrl();		
		debug = testSetup.isRunningDebug();
		TestContext.INSTANCE.setTestSetup(testSetup);
		screenShotsDir = TestSetup.getExecutionPath() + TestSetup.reportDir + testSetup.getTestReportCategory();
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
	
	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		logoutQuitDriver();		
		
		// Post run result to DB if enabled.
		postResultsToAutomationAPI();
	}

	public static void logoutQuitDriver() {
		if (!driver.getTitle().equalsIgnoreCase("Login")) {
			homePage.open();
			homePage.logout();
		}
		
		driver.quit();
	}

	public static void postResultsToAutomationAPI() {
		if (extentReportFile!=null) {
			if (TestContext.INSTANCE.getTestSetup().isAutomationReportingApiEnabled()) {
				TestContext.INSTANCE.getTestSetup().postAutomationRunResult(extentReportFile.toString());
			}
		}
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
	
	protected boolean isValidRunAsUser(String username, String functionName) {
		String runAsUsers = DataAnnotations.getRunAsUsers(getClass(), functionName);
		List<String> listUsers = RegexUtility.split(runAsUsers, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
		if (!listUsers.contains(username)) {
			return false;
		}
		return true;
	}
}