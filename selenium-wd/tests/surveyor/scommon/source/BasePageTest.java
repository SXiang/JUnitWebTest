package surveyor.scommon.source;

import java.io.IOException;
import java.util.List;

import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.source.DateUtility;
import common.source.ExtentReportGenerator;
import common.source.ScreenShotOnFailure;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.TestSetupFactory;

public class BasePageTest {
	
	public static WebDriver driver;     
	
	private static TestSetup testSetup;
	private static LoginPage loginPage;
	private static HomePage homePage;

	private static ScreenShotOnFailure screenCapture;

	private static String baseURL;
	private static Boolean debug;
	
	private static ExtentTest extentTest; 
	private static StringBuilder extentReportFilePath;
	
	public static void initializeTestObjects() throws IOException {
		setTestSetup(TestSetupFactory.getTestSetup());
		setBaseURL(getTestSetup().getBaseUrl());
		setIsDebug(getTestSetup().isRunningDebug());
		TestContext.INSTANCE.setTestSetup(getTestSetup());
		
		driver = getTestSetup().getDriver();
		driver.manage().deleteAllCookies();

		screenCapture = TestSetupFactory.getScreenShotOnFailure();
		
		PageObjectFactory pageObjectFactory = new PageObjectFactory();
		setLoginPage(pageObjectFactory.getLoginPage());
		setHomePage(pageObjectFactory.getHomePage());
	}

	private static void setTestSetup(TestSetup tstSetup) {
		testSetup = tstSetup;
	}

	public static Boolean isDebug() {
		return debug;
	}

	public static void setIsDebug(Boolean isDebug) {
		debug = isDebug;
	}

	public static LoginPage getLoginPage() {
		return loginPage;
	}

	public static void setLoginPage(LoginPage loginPg) {
		loginPage = loginPg;
	}

	public static HomePage getHomePage() {
		return homePage;
	}

	public static void setHomePage(HomePage homePg) {
		homePage = homePg;
	}

	public static String getBaseURL() {
		return baseURL;
	}

	public static void setBaseURL(String baseUrl) {
		baseURL = baseUrl;
	}

	public static StringBuilder getExtentReportFilePath() {
		return extentReportFilePath;
	}
	
	public static ScreenShotOnFailure getScreenCapture() {
		return screenCapture;
	}

	public static TestSetup getTestSetup() {
		return testSetup;
	}
	
	public static ExtentTest getExtentTest() {
		return extentTest;
	}

	private static void setExtentTest(ExtentTest test, String className) {
		BasePageTest.extentTest = test;
		TestContext.INSTANCE.setExtentTest(test, className);
	}

	private static ExtentReports getExtentReport(String className) {
	   ExtentReports extentReport = TestContext.INSTANCE.getReport();
	   if (extentReport == null) {
		   StringBuilder outReportFilePath = new StringBuilder();
		   extentReport = ExtentReportGenerator.createExtentReport(className, outReportFilePath);
		   extentReportFilePath = outReportFilePath;
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
		List<String> testMessage = TestContext.INSTANCE.getTestMessage();
		for(String message:testMessage){
			getExtentTest().log(LogStatus.WARNING, "Extra messages before the failure", "Log Message: " + message);
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
}