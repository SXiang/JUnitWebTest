package common.source;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.model.ITest;

import common.source.Log.LogField;

public enum TestContext {
	INSTANCE;

	private TestSetup testSetup;
	private String userCulture = null;
	private String loggedInUserName;
	private String runUniqueId;

	private ExtentReports report;
	private Map<String, ExtentTest> extentTestMap;
	private Map<String,Object> testMap;
	private List<String> testMessage;
	private Set<String> testReportIdSet;
	private String currentTestStatus = "PASS";
	private int numTestMessagesToRetain = 5;

	private TestContext() {
		// Every time a context is created set a unique run ID.
		this.setRunUniqueId(TestSetup.getUUIDString());
		this.testMessage = Collections.synchronizedList(new ArrayList<String>(numTestMessagesToRetain));
		this.testReportIdSet = Collections.synchronizedSet(new HashSet<String>());
		this.testMap = Collections.synchronizedMap(new HashMap<String, Object>());
		this.testMap.put(LogField.INDEX_ID.toString(), getIndexIdForTestRun());
		this.extentTestMap = Collections.synchronizedMap(new HashMap<String, ExtentTest>());
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


	public Map<String, Object> getTestMap() {
		return testMap;
	}


	public ExtentTest getExtentTest(String className) {
		return extentTestMap.get(className);
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
		this.testMap.put(LogField.TEST_METHOD.toString(), methodName);
		this.testMap.put(LogField.TEST_CLASS.toString(), className);
		this.extentTestMap.put(className, extentTest);
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
		    testMap.put(LogField.TEST_ENVIROMENT.toString(), testSetup.getRunEnvironment());
		    testMap.put(LogField.TEST_URL.toString(), testSetup.getBaseUrl());
		    testMap.put(LogField.TEST_CATEGORY.toString(), testSetup.getTestReportCategory());
		}
	}

	public String getLoggedInUser() {
		return this.loggedInUserName;
	}

	public void setLoggedInUser(String loggedInUserName) {
		this.loggedInUserName = loggedInUserName;
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

	public String getRunUniqueId() {
		return runUniqueId;
	}

	private void setRunUniqueId(String runUniqueId) {
		this.runUniqueId = runUniqueId;
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

}