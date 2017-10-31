package common.source;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.ITest;

public enum TestContext {
	INSTANCE;

	private static final String DEFAULT_TEST_STATUS = "FAIL";
	private static final String EMPTY = "";

	private TestSetup testSetup;
	private String userCulture = null;
	private ThreadLocalMap<String> threadLoggedInUser;
	private ThreadLocalMap<String> threadLoggedInPwd;
	private ExtentReports report;
	private Map<String, ExtentTest> extentTestMap;
	private List<String> testMessage;
	private int numTestMessagesToRetain = 5;
	private String testClassName;
	private ThreadLocalMap<Set<String>> threadReportIdSet;
	private ThreadLocalMap<LogData> threadLogData;
	private ThreadLocalMap<String> threadTestStatus;

	private Boolean isRunningOnAndroidDevice = false;

	private TestContext() {
		this.testMessage = Collections.synchronizedList(new ArrayList<String>(numTestMessagesToRetain));
		this.threadReportIdSet = new ThreadLocalMap<Set<String>>(Collections.synchronizedSet(new HashSet<String>()));
		this.threadLogData = new ThreadLocalMap<LogData>(ThreadLocalStore.getLogData());
		this.threadTestStatus = new ThreadLocalMap<String>(DEFAULT_TEST_STATUS);
		this.threadLoggedInUser = new ThreadLocalMap<String>(EMPTY);
		this.threadLoggedInPwd = new ThreadLocalMap<String>(EMPTY);
		this.getLogData().setIndexID(getIndexIdForTestRun());
		this.extentTestMap = ThreadLocalStore.getExtentTestMap();
	}

	public void captureScreenshot() {
		Log.method("captureScreenshot");
		TestContext.INSTANCE.getTestSetup().getScreenCapture().takeScreenshot(TestContext.INSTANCE.getDriver(),
				TestContext.INSTANCE.getTestClassName(), true /*takeBrowserScreenShot*/, LogStatus.INFO);
	}

	public String getTestStatus() {
		String status = threadTestStatus.getObject();
		if (status != null) {
			return status;
		}

		return DEFAULT_TEST_STATUS;
	}

	public void setTestStatus(String testStatus) {
		this.threadTestStatus.putObject(testStatus);
	}

	public Set<String> getTestReportIdSet(){
		Set<String> reportIdSet = threadReportIdSet.getObject();
		if (reportIdSet != null) {
			return reportIdSet;
		}

		threadReportIdSet.putObject(Collections.synchronizedSet(new HashSet<String>()));;
		return threadReportIdSet.getObject();
	}

	public void clearTestReportSet() {
		Set<String> testReportIdSet = getTestReportIdSet();
		if (testReportIdSet != null) {
			testReportIdSet.clear();
		}
	}

	public boolean addReportId(String reportTitle, String reportId) {
		if(reportId==null||reportId.isEmpty()){
			return false;
		}

		Set<String> testReportIdSet = getTestReportIdSet();
		if (testReportIdSet != null) {
			return testReportIdSet.add(reportId.trim());
		}

		return false;
	}

	public LogData getLogData() {
		if (threadLogData.getObject() == null) {
			// If thread specific LogData not present add new.
			LogData data = ThreadLocalStore.getLogData();
			data.setIndexID(getIndexIdForTestRun());
			threadLogData.putObject(data);
		}

		return threadLogData.getObject();
	}

	public ExtentTest getExtentTest(String className) {
		return getExtentTestMap().get(className);
	}

	public void updateTestMessage(String message){
		if(message==null||message.isEmpty()){
			return;
		}
		while(testMessage.size()>=numTestMessagesToRetain){
			testMessage.remove(0);
		}
		testMessage.add(new java.util.Date() + ": " + message);
	}

	public List<String> getTestMessage(){
		return testMessage;
	}

	public void setExtentTest(ExtentTest extentTest, String className) {
		ITest test = extentTest.getTest();
	    String methodReplacePattern = "[\\s]*\\[[\\s]*([\\d]*)[\\s]*:[\\s]*([\\d]*).+";
	    String methodName = test.getName();
	    try{
	    	methodName = methodName.replaceAll(methodReplacePattern, "[$1:$2]");
	    }catch(Exception e){
	    	Log.warn(e.toString());
	    }

	    this.getLogData().setTestMethod(methodName);
	    this.getLogData().setTestClass(className);

	    threadDebugPrint("TestContext :: TestMap values -> " + TestContext.INSTANCE.getLogData().toString());

		this.getExtentTestMap().put(className, extentTest);
	}

	private void threadDebugPrint(String message) {
		System.out.println(String.format("Thread=[%s], Message=[%s]", Thread.currentThread().getName(), message));
	}

	public String getDbIpAddress() {
		String dbIPAddress = null;
		if (testSetup != null) {
			dbIPAddress = testSetup.getDbIpAddress();
		}
		return dbIPAddress;
	}

	public String getDbPortNo() {
		String dbPortNo = null;
		if (testSetup != null) {
			dbPortNo = testSetup.getDbPortNo();
		}
		return dbPortNo;
	}

	public String getDbName() {
		String dbName = null;
		if (testSetup != null) {
			dbName = testSetup.getDbName();
		}
		return dbName;
	}

	public String getDbUser() {
		String dbUser = null;
		if (testSetup != null) {
			dbUser = testSetup.getDbUser();
		}
		return dbUser;
	}

	public String getDbPassword() {
		String dbPassword = null;
		if (testSetup != null) {
			dbPassword = testSetup.getDbPassword();
		}
		return dbPassword;
	}

	public void setTestSetup(TestSetup testSetup) {
		this.testSetup = testSetup;
		if (this.testSetup != null) {
			getLogData().setTestEnvironment(testSetup.getRunEnvironment());
			getLogData().setTestBaseUrl(testSetup.getBaseUrl());
			getLogData().setTestCategory(testSetup.getTestReportCategory());
		}
	}

	public String getLoggedInUser() {
		return threadLoggedInUser.getObject();
	}

	public void setLoggedInUser(String loggedInUserName) {
		threadLoggedInUser.putObject(loggedInUserName);
	}

	public String getLoggedInPassword() {
		return threadLoggedInPwd.getObject();
	}

	public void setLoggedInPassword(String loggedInPwd) {
		this.threadLoggedInPwd.putObject(loggedInPwd);
	}

	public String getUserCulture() {
		if (userCulture == null) {
			userCulture = getTestSetup().getCulture();
		}
		return userCulture;
	}

	public void setUserCulture(String userCulture) {
		this.userCulture = userCulture;
	}

	public TestSetup getTestSetup() {
		return this.testSetup;
	}

	public void stayIdle(int seconds) {
		TestSetup.idleForSeconds(seconds);
	}

	public String getExecutionPath() {
		String executionPath = null;
		try {
			executionPath = TestSetup.getExecutionPath(TestSetup.getRootPath());
		} catch (IOException e) {
			Log.error(e.toString());
		}
		return executionPath;
	}

	public WebDriver getDriver() {
		WebDriver driver = null;
		if (testSetup != null) {
			driver = testSetup.getDriver();
		}
		return driver;
	}

	public WebDriver getAppiumDriver() {
		WebDriver driver = null;
		if (testSetup != null) {
			driver = testSetup.getAppiumDriver();
		}
		return driver;
	}

	public boolean isAppiumDriverInTest() {
		boolean appiumDriverFound = false;
		if (testSetup != null) {
			appiumDriverFound = testSetup.isAppiumDriverInTest();
		}
		return appiumDriverFound;
	}

	public String getBaseUrl() {
		String baseUrl = null;
		if (testSetup != null) {
			baseUrl = testSetup.getBaseUrl();
		}
		return baseUrl;
	}

	public String getRunEnvironment() {
		String environment = null;
		if (testSetup != null) {
			environment = testSetup.getRunEnvironment();
		}
		return environment;
	}

	public String getTestRunCategory() {
		String testRunCategory = null;
		if (testSetup != null) {
			testRunCategory = testSetup.getTestRunCategory();
		}
		return testRunCategory;
	}

	public String getTestReportCategory() {
		String testReportCategory = null;
		if (testSetup != null) {
			testReportCategory = testSetup.getTestReportCategory();
		}
		return testReportCategory;
	}

	public Long getRunUniqueId() {
		if (testSetup != null) {
			return testSetup.getRunUUID();
		}
		return 0L;
	}

	public ExtentReports getReport() {
		return report;
	}

	public void setReport(ExtentReports report) {
		this.report = report;
	}

	public static String getIndexIdForTestRun(){
		String indexId = System.getProperty("test_run_id");
		if(indexId != null){
			return indexId;
		}
		String pattern = "yyyy.MM.dd.H.mm.ss.SSS";
		SimpleDateFormat formater = new SimpleDateFormat(pattern,Locale.getDefault());
		indexId = formater.format(new Date());
		return indexId;
	}

	public String getTestClassName() {
		return testClassName;
	}

	public void setTestClassName(String testClassName) {
		this.testClassName = testClassName;
	}

	public Map<String, ExtentTest> getExtentTestMap() {
		return extentTestMap;
	}

	public void setExtentTestMap(Map<String, ExtentTest> extentTestMap) {
		this.extentTestMap = extentTestMap;
	}

	public Boolean isRunningOnAndroidDevice() {
		return isRunningOnAndroidDevice;
	}

	public void setIsRunningOnAndroidDevice(Boolean isRunningOnAndroidDevice) {
		this.isRunningOnAndroidDevice = isRunningOnAndroidDevice;
	}
}