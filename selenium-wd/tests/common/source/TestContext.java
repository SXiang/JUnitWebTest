package common.source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public enum TestContext {
	INSTANCE;
	
	private TestSetup testSetup;
	private String userCulture = null;
	private String loggedInUserName;
	private String runUniqueId;

	private ExtentReports report;
	private ExtentTest extentTest;
	private ArrayList<String> testMessage;
	private Set<String> testReportIdSet;
	private String currentTestStatus = "PASS";
	private int numTestMessagesToRetain = 5;

	private TestContext() {
		// Every time a context is created set a unique run ID.
		this.setRunUniqueId(TestSetup.getUUIDString());
		this.testMessage = new ArrayList<String>(numTestMessagesToRetain);
		this.testReportIdSet = new HashSet<String>();
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


	public ExtentTest getExtentTest() {
		return extentTest;
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
	
	public ArrayList<String> getTestMessage(){
		return testMessage;
	}
	public void setExtentTest(ExtentTest extentTest) {
		this.extentTest = extentTest;
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
		this.testSetup.slowdownInSeconds(seconds);
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
}
