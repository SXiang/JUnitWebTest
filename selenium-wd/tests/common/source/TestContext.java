package common.source;

public enum TestContext {
	INSTANCE;
	
	private TestSetup testSetup;
	private String userCulture = "en-US";
	private String loggedInUserName;

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

	public String getUserCulture() {
		return userCulture;
	}

	public void setUserCulture(String userCulture) {
		this.userCulture = userCulture;
	}
}
