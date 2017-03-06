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
import com.relevantcodes.extentreports.model.ITest;

public enum TestContext {
	INSTANCE;

	private TestSetup testSetup;
	private String userCulture = null;
	private String loggedInUserName;
	private String loggedInPwd;
	private ExtentReports report;
	private Map<String, ExtentTest> extentTestMap;
//	private Map<String,Object> testMap;
	private List<String> testMessage;
	private Set<String> testReportIdSet;
	private String currentTestStatus = "PASS";
	private int numTestMessagesToRetain = 5;
	private String testClassName;
	private ThreadLocalMap<LogData> threadLogData;

	private TestContext() {
		this.testMessage = Collections.synchronizedList(new ArrayList<String>(numTestMessagesToRetain));
		this.testReportIdSet = Collections.synchronizedSet(new HashSet<String>());
		this.threadLogData = new ThreadLocalMap<LogData>(ThreadLocalStore.getLogData());
		this.getLogData().setIndexID(getIndexIdForTestRun());
		this.extentTestMap = ThreadLocalStore.getExtentTestMap();
	}

	public String getTestStatus() {
		return currentTestStatus;
	}

	public Set<String> getTestReportIdSet(){
		return testReportIdSet;
	}
	public void clearTestReportSet() {
		testReportIdSet.clear();
	}

	public boolean addReportId(String reportId) {
		if(reportId==null||reportId.isEmpty()){
			return false;
		}
		return this.testReportIdSet.add(reportId.trim());
	}

	public void setTestStatus(String testStatus) {
		this.currentTestStatus = testStatus;
	}

//	public Map<String, Object> getTestMap() {
//		return testMap;
//	}
//
//	public void setTestMap(Map<String, Object> testMap) {
//		this.testMap = testMap;
//		this.testMap.put(LogField.INDEX_ID.toString(), getIndexIdForTestRun());
//	}

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

//	    this.testMap.put(LogField.TEST_METHOD.toString(), methodName);
//		this.testMap.put(LogField.TEST_CLASS.toString(), className);

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
//		    testMap.put(LogField.TEST_ENVIROMENT.toString(), testSetup.getRunEnvironment());
//		    testMap.put(LogField.TEST_URL.toString(), testSetup.getBaseUrl());
//		    testMap.put(LogField.TEST_CATEGORY.toString(), testSetup.getTestReportCategory());
		}
	}

	public String getLoggedInUser() {
		return this.loggedInUserName;
	}

	public void setLoggedInUser(String loggedInUserName) {
		this.loggedInUserName = loggedInUserName;
	}

	public String getLoggedInPassword() {
		return loggedInPwd;
	}

	public void setLoggedInPassword(String loggedInPwd) {
		this.loggedInPwd = loggedInPwd;
	}

	public String getLoggedInUserPassword() {
		String loggedInUserPassword = null;
		if (testSetup != null) {
			loggedInUserPassword = testSetup.getLoginPwd();
		}
		return loggedInUserPassword;
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

	public LogData getLogData() {
		if (threadLogData.getObject() == null) {
			// If thread specific LogData not present add new.
			LogData data = ThreadLocalStore.getLogData();
			data.setIndexID(getIndexIdForTestRun());
			threadLogData.putObject(data);
		}

		return threadLogData.getObject();
	}
}