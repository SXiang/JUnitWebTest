/**
 *
 */
package common.source;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import surveyor.dataaccess.source.Survey;
import surveyor.dataaccess.source.User;
import surveyor.dbseed.source.DbSeedExecutor;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.SurveyorConstants.Environment;

/**
 * This is the initial class to setup up the testing environment and
 * configuration
 *
 * 1. Load the testing property for test setup information.
<<<<<<< HEAD
 * 2. Check and upload DB seed data.
=======
 *
 * 2. Setting up the drivers.
 *
 * 3. It is ongoing and add more code here later when needed
>>>>>>> master
 *
 * @version 1.0
 * @author zlu
 *
 */
public class TestSetup {

	private static final String UPDATE_ANALYZER_CONFIGURATION_CMD = "UpdateAnalyzerConfiguration.cmd";
	private static final String POST_AUTOMATION_RUN_RESULT_CMD = "Post-AutomationRunResult.cmd";
	private static final String POST_REPORT_JOB_PERF_STAT_CMD = "Post-ReportJobPerfStat.cmd";
	private static final String POST_ANALYZER_API_PERF_STAT_CMD = "Post-AnalyzerAPIPerfStat.cmd";
	private static final String[] CI_MACHINES = { "20.20.20.59", "20.20.10.82", "10.0.2.15", "10.200.2.48"};
	private static String testPropFileName;

	public static final String REPLAY_DEFN_CURL_FILE = "replay-defn-curl.bat";
	public static final String STOP_REPLAY_CURL_FILE = "replay-stop.bat";
	public static final String ANALYZER_EXE_PATH = "C:\\PicarroAnalyzer\\Picarro.Surveyor.Analyzer.exe";
	public static final String TEST_ANALYZER_SERIAL_NUMBER = "SimAuto-Analyzer1";

	public static final String DATA_FOLDER = "data";
	public static final String SQL_DATA_FOLDER = "data\\sql";
	public static final String TEST_DATA_XLSX = "TestCaseData.xlsx";

	private static Process analyzerProcess;
	private BrowserMobProxy networkProxy;

	private Properties testProp;

	private String baseURL;
	private String runningOnRemoteServer;
	private String remoteServerHost;
	private String remoteServerPort;
	private String isLocalGridRun;

	private String loginUser;
	private String loginPwd;

	private String firstTimeLoginUser;
	private String firstTimeLoginPwd;

	private String loginUser0000;
	private String loginPwd0000;
	private String loginUserDisplayName;

	private String browser;
	private String chromeDriverPath;
	private String ieDriverPath;

	private String runEnvironment;
	private String testRunCategory;

	private String implicitlyWaitTimeOutInSeconds;
	private String implicitlyWaitSpecialTimeOutInSeconds;
	private String implicitlyWaitSpecialTimeOutInMS;

	private String culture;
	private String language;
	private boolean debug;

	private DateFormat dateFormat;
	private Calendar calendar;
	private String randomNumber;

	private String slowdownInSeconds; // For debugging the code and not
										// recommended to use in real test case
	private String testCleanUpMode;
	public boolean isRemoteBrowser;

	public static String reportDir = "reports/";

	private String downloadPath;

	private String dbIPAddress;
	private String dbPortNo;
	private String dbName;
	private String dbUser;
	private String dbPassword;
	private String computerName;
	private String softwareVersion;
	private String platform;

	private boolean collectReportJobPerfMetric;
	private boolean generateBaselineSSRSImages;
	private boolean generateBaselineViewImages;
	private boolean generateBaselineShapeFiles;

	private Integer executionTimesForLightLoadReportJobPerfBaseline;
	private Integer executionTimesForMediumLoadReportJobPerfBaseline;
	private Integer executionTimesForHighLoadReportJobPerfBaseline;
	private Integer executionTimesForUltraHighLoadReportJobPerfBaseline;

	private boolean logCategorySSRSPdfContentEnabled;
	private boolean logCategoryComplianceReportActionsEnabled;
	private boolean logCategoryWebElementEnabled;
	private boolean logCategoryVerboseLoggingEnabled;

	private String surveysToUpload;
	private boolean uploadSurveyEnabled;
	private String surveyUploadBaseUrl;

	private boolean pushDBSeedEnabled;
	private String pushDBSeedBaseUrl;

	private String automationReportingApiEndpoint;
	private boolean automationReportingApiEnabled;

	private static boolean parallelBuildEnabled;

	private String parallelBuildRunUUID;
	private Integer parallelBuildRequiredNodes;

	private static final AtomicBoolean singleExecutionUnitProcessed = new AtomicBoolean();
	private static final CountDownLatch singleExecutionCountDown = new CountDownLatch(1);

	public TestSetup() {
		initialize();
	}

	public TestSetup(Boolean initialize) {
		if (initialize) {
			initialize();
		}
	}

	/* NETWORK PROXY related methods */
	/*
	 * EXAMPLE USAGE: 1. Using Proxy to limit Upstream/Downstream KBPS.
	 * startNetworkProxy(true|false); setNetworkProxyDownstreamKbps(<long>);
	 * setNetworkProxyUpstreamKbps(<long>); ... <perform test actions> ...
	 * stopNetworkProxy();
	 *
	 * 2. Using Proxy to turn OFF/ON HTTP Traffic for Selenium tests.
	 * turnOffHttpTraffic(); ... <perform test actions> ... turnOnHttpTraffic()
	 */

	public void startNetworkProxy(boolean createHarFile) throws MalformedURLException {
		// start the proxy
		networkProxy = new BrowserMobProxyServer();
		networkProxy.start(0);

		// when we start the network proxy we are recycling the driver object.
		// Quit the driver if present.
		if (WebDriverFactory.getDriver() != null) {
			WebDriverFactory.getDriver().quit();
		}

		// get the selenium proxy object
		Proxy seleniumProxy = ClientUtil.createSeleniumProxy(networkProxy);
		WebDriverFactory.setChromeBrowserCapabilities(seleniumProxy);

		if (createHarFile) {
			// create new Har file.
			networkProxy.newHar("Automation Proxy Results");
		}
	}

	public void stopNetworkProxy() {
		if (networkProxy != null) {
			if (networkProxy.isStarted()) {
				networkProxy.stop();
			}
		}
	}

	public Har getNetworkProxyHarData() {
		if (networkProxy != null) {
			return networkProxy.getHar();
		}
		return null;
	}

	public void setNetworkProxyDownstreamKbps(long kbps) {
		if (networkProxy != null) {
			networkProxy.setReadBandwidthLimit(kbps);
		}
	}

	public void setNetworkProxyUpstreamKbps(long kbps) {
		if (networkProxy != null) {
			networkProxy.setWriteBandwidthLimit(kbps);
		}
	}

	public void setNetworkProxyLatency(long latency, TimeUnit timeUnit) {
		if (networkProxy != null) {
			networkProxy.setLatency(latency, timeUnit);
		}
	}

	public void turnOffHttpTraffic() throws MalformedURLException {
		if (networkProxy == null || !networkProxy.isStarted()) {
			startNetworkProxy(false /* createHarFile */);
		}

		this.setNetworkProxyDownstreamKbps(0);
		this.setNetworkProxyUpstreamKbps(0);
	}

	public void turnOnHttpTraffic() {
		if (networkProxy != null) {
			// when we stop the network proxy we should recycling the driver
			// object to remove the Proxy capability.
			if (WebDriverFactory.getDriver() != null) {
				WebDriverFactory.getDriver().quit();
			}

			WebDriverFactory.setChromeBrowserCapabilities(); // No proxy.
			this.stopNetworkProxy();
		}
	}

	private static void executeUpdateConfigCmd(String workingUpdCmdFile) {
		// Execute update analyzer configuration script from the contained
		// folder.
		try {
			String updateCmdFolder = getExecutionPath(getRootPath()) + "lib";
			String updateCmdFileFullPath = updateCmdFolder + File.separator + workingUpdCmdFile;
			String command = "cd \"" + updateCmdFolder + "\" && " + updateCmdFileFullPath;
			Log.info("Executing update Analyzer configuration script. Command -> " + command);
			ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);
		} catch (IOException e) {
			Log.error(e.toString());
		}
	}

	public static String getRootPath() throws IOException {
		File propertyfile = new File(".");
		return propertyfile.getCanonicalPath();
	}

	public static String getExecutionPath() throws IOException{
		return getExecutionPath(getRootPath());
	}
	public static String getExecutionPath(String rootPath) {
		/* For CI and Eclipse run setup */
		String executionPath = rootPath + File.separator + "selenium-wd" + File.separator;
		/* For build.xml run locally */
		//String executionPath = rootPath+ File.separator;
		return executionPath;
	}

	public static String getSystemTempDirectory() {
		return System.getProperty("java.io.tmpdir");
	}

	public boolean hasBrowserQuit() {
		Log.info("Checking if Browser has QUIT. Driver String value: " + this.getDriver().toString());
		return this.getDriver().toString().contains("(null)");
	}

	public int getSlowdownInSeconds() {
		return Integer.parseInt(this.slowdownInSeconds);
	}

	public int getTestCleanUpMode() {
		return Integer.parseInt(this.testCleanUpMode);
	}
	public WebDriver getDriver() {
		return WebDriverFactory.getDriver();
	}

	public String getCulture() {
		return this.culture;
	}

	public String getBaseUrl() {
		return this.baseURL;
	}

	public String getLoginUser() {
		return this.loginUser;
	}

	public String getLoginPwd() {
		return this.loginPwd;
	}

	public String getLoginUserDisplayName() {
		return this.loginUserDisplayName;
	}

	public String getFirstTimeLoginUser() {
		return this.firstTimeLoginUser;
	}

	public String getFirstTimeLoginPwd() {
		return this.firstTimeLoginPwd;
	}

	public String getRandomNumber() {
		return this.randomNumber;
	}

	public String getFixedSizeRandomNumber(int size) {
		int len = this.randomNumber.length();
		if (len > size) {
			return this.randomNumber.substring(0, size - 1);
		} else if (len < size) {
			return BaseHelper.prependStringWithChar(this.randomNumber, '0', size - len);
		}

		// length same as size. Return the number as it.
		return this.randomNumber;
	}

	public String getFixedSizePseudoRandomString(int size) {
		String uuidString = getUUIDString();

		// UUID string without '-' is of length=32
		// Adjust size of the string as per the requested size.
		int len = uuidString.length();
		if (len > size) {
			return uuidString.substring(0, size);
		} else if (len < size) {
			return BaseHelper.prependStringWithChar(uuidString, '0', size - len);
		}

		// length same as size. Return.
		return uuidString;
	}

	public static String getUUIDString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public long getImplicitlyWaitTimeOutInSeconds() {
		return Long.parseLong(this.implicitlyWaitTimeOutInSeconds.trim());
	}

	public long getImplicitlySpecialWaitTimeOutinSeconds() {
		return Long.parseLong(this.implicitlyWaitSpecialTimeOutInSeconds.trim());
	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public String getDbIpAddress() {
		return dbIPAddress;
	}

	public String getDbPortNo() {
		return dbPortNo;
	}

	public String getDbName() {
		return dbName;
	}

	public String getDbUser() {
		return dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public String getRunEnvironment() {
		return runEnvironment;
	}

	public String getTestRunCategory() {
		return testRunCategory;
	}

	public String getTestReportCategory() {
		return getSystemProperty(this.testProp, "testReportCategory","testRunCategory");
	}

	public boolean isGenerateBaselineShapeFiles() {
		return generateBaselineShapeFiles;
	}

	public String getTestCaseDataPath() throws IOException {
		String rootPath = getRootPath();
		return getExecutionPath(rootPath) + DATA_FOLDER + File.separator + TEST_DATA_XLSX;
	}

	public void setGenerateBaselineShapeFiles(boolean generateBaselineShapeFiles) {
		this.generateBaselineShapeFiles = generateBaselineShapeFiles;
	}

	public boolean isGenerateBaselineViewImages() {
		return generateBaselineViewImages;
	}

	public void setGenerateBaselineViewImages(boolean generateBaselineViewImages) {
		this.generateBaselineViewImages = generateBaselineViewImages;
	}

	public boolean isGenerateBaselineSSRSImages() {
		return generateBaselineSSRSImages;
	}

	public void setGenerateBaselineSSRSImages(boolean generateBaselineSSRSImages) {
		this.generateBaselineSSRSImages = generateBaselineSSRSImages;
	}

	public boolean isCollectReportJobPerfMetric() {
		return collectReportJobPerfMetric;
	}

	public void setCollectReportJobPerfMetric(boolean collectReportJobPerfMetric) {
		this.collectReportJobPerfMetric = collectReportJobPerfMetric;
	}

	public Integer getExecutionTimesForLightLoadReportJobPerfBaseline() {
		return executionTimesForLightLoadReportJobPerfBaseline;
	}

	public void setExecutionTimesForLightLoadReportJobPerfBaseline(
			Integer executionTimesForLightLoadReportJobPerfBaseline) {
		this.executionTimesForLightLoadReportJobPerfBaseline = executionTimesForLightLoadReportJobPerfBaseline;
	}

	public Integer getExecutionTimesForMediumLoadReportJobPerfBaseline() {
		return executionTimesForMediumLoadReportJobPerfBaseline;
	}

	public void setExecutionTimesForMediumLoadReportJobPerfBaseline(
			Integer executionTimesForMediumLoadReportJobPerfBaseline) {
		this.executionTimesForMediumLoadReportJobPerfBaseline = executionTimesForMediumLoadReportJobPerfBaseline;
	}

	public Integer getExecutionTimesForHighLoadReportJobPerfBaseline() {
		return executionTimesForHighLoadReportJobPerfBaseline;
	}

	public void setExecutionTimesForHighLoadReportJobPerfBaseline(
			Integer executionTimesForHighLoadReportJobPerfBaseline) {
		this.executionTimesForHighLoadReportJobPerfBaseline = executionTimesForHighLoadReportJobPerfBaseline;
	}

	public Integer getExecutionTimesForUltraHighLoadReportJobPerfBaseline() {
		return executionTimesForUltraHighLoadReportJobPerfBaseline;
	}

	public void setExecutionTimesForUltraHighLoadReportJobPerfBaseline(
			Integer executionTimesForUltraHighLoadReportJobPerfBaseline) {
		this.executionTimesForUltraHighLoadReportJobPerfBaseline = executionTimesForUltraHighLoadReportJobPerfBaseline;
	}

	public boolean isLogCategorySSRSPdfContentEnabled() {
		return logCategorySSRSPdfContentEnabled;
	}

	public void setLogCategorySSRSPdfContentEnabled(boolean logCategorySSRSPdfContentEnabled) {
		this.logCategorySSRSPdfContentEnabled = logCategorySSRSPdfContentEnabled;
	}

	public boolean isLogCategoryComplianceReportActionsEnabled() {
		return logCategoryComplianceReportActionsEnabled;
	}

	public void setLogCategoryComplianceReportActionsEnabled(boolean logCategoryComplianceReportActionsEnabled) {
		this.logCategoryComplianceReportActionsEnabled = logCategoryComplianceReportActionsEnabled;
	}

	public boolean isLogCategoryClickWebElementEnabled() {
		return logCategoryWebElementEnabled;
	}

	public void setLogCategoryClickWebElementEnabled(boolean logCategoryWebElementEnabled) {
		this.logCategoryWebElementEnabled = logCategoryWebElementEnabled;
	}

	public boolean isLogCategoryVerboseLoggingEnabled() {
		return logCategoryVerboseLoggingEnabled;
	}

	public void setLogCategoryVerboseLoggingEnabled(boolean logCategoryVerboseEnabled) {
		this.logCategoryVerboseLoggingEnabled = logCategoryVerboseEnabled;
	}

	public String getSoftwareVersion() {
		return this.softwareVersion;
	}

	public void initialize() {
		try {

			this.computerName = InetAddress.getLocalHost().getHostAddress();
			Log.info("IP Address: " + computerName);

			String rootPath = getRootPath();
			InputStream inputStream = loadTestProperties(rootPath);

			this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			this.calendar = Calendar.getInstance();

			Log.info(dateFormat.format(this.calendar.getTime()) + "\n");
			this.baseURL = this.testProp.getProperty("baseURL");
			Log.info("\nThe baseURL is: " + this.baseURL + "\n");

			this.setRunningOnRemoteServer(this.testProp.getProperty("runningOnRemoteServer"));
			this.setRemoteServerHost(this.testProp.getProperty("remoteServerHost"));
			this.setRemoteServerPort(this.testProp.getProperty("remoteServerPort"));
			this.setIsLocalGridRun(this.testProp.getProperty("isLocalGridRun"));
			this.loginUser = this.testProp.getProperty("loginUser");
			this.loginPwd = this.testProp.getProperty("loginPwd");
			this.loginUser0000 = this.testProp.getProperty("loginUser0000");
			this.loginPwd0000 = this.testProp.getProperty("loginPwd0000");
			this.loginUserDisplayName = this.testProp.getProperty("loginUserDisplayName");

			this.firstTimeLoginUser = this.testProp.getProperty("firstTimeLoginUser");
			this.firstTimeLoginPwd = this.testProp.getProperty("firstTimeLoginPwd");

			initializeDBProperties();

			this.setBrowser(this.testProp.getProperty("browser"));
			Log.info("\nThe browser is: " + this.getBrowser() + "\n");

			this.setIeDriverPath(this.testProp.getProperty("ieDriverPath"));

			this.setChromeDriverPath(getExecutionPath(rootPath) + "lib" + File.separator + "chromedriver.exe");
			this.implicitlyWaitTimeOutInSeconds = this.testProp.getProperty("implicitlyWaitTimeOutInSeconds");
			this.implicitlyWaitSpecialTimeOutInSeconds = this.testProp
					.getProperty("implicitlyWaitSpecialTimeOutInSeconds");
			this.setImplicitlyWaitSpecialTimeOutInMS(this.testProp.getProperty("implicitlyWaitSpecialTimeOutInMS"));

			this.runEnvironment = this.testProp.getProperty("runEnvironment");
			this.testRunCategory = this.testProp.getProperty("testRunCategory");

			setLoggingTestProperties();
			setComplianceReportBaselineGenerationTestProperties();
			setPerformanceExecutionTestProperties();
			setUploadSurveyTestProperties();
			setPushDBSeedTestProperties();
			setParallelBuildTestProperties();

			this.language = this.testProp.getProperty("language");
			this.culture = this.testProp.getProperty("culture");
			this.softwareVersion = this.testProp.getProperty("softwareVersion");
			this.setPlatform(this.testProp.getProperty("platform"));

			this.setAutomationReportingApiEndpoint(this.testProp.getProperty("automationReporting.ApiEndPoint"));
			String automationReportingApiEnabledValue = this.testProp.getProperty("automationReporting.APIEnabled");
			if (automationReportingApiEnabledValue != null && automationReportingApiEnabledValue != "") {
				this.automationReportingApiEnabled = Boolean.valueOf(automationReportingApiEnabledValue);
			}

			if (!isRunningLocally()) {
				this.downloadPath = this.testProp.getProperty("downloadPath") + File.separator;
			} else {
				this.downloadPath = System.getProperty("user.home") + File.separator + "Downloads" + File.separator;
			}

			if (this.testProp.getProperty("debug").equals("true")) {
				this.debug = true;
			} else {
				this.debug = false;
			}

			this.testCleanUpMode = this.testProp.getProperty("testCleanUpMode");
			this.slowdownInSeconds = this.testProp.getProperty("slowdownInSeconds");
			this.randomNumber = Long.toString((new Random()).nextInt(1000000));
			Log.info("\nThe random number is: " + this.randomNumber + "\n");

			inputStream.close();

			TestContext.INSTANCE.setTestSetup(this);

			// process the single execution method only once for all threads of execution.
			if (singleExecutionUnitProcessed.compareAndSet(false, true)) {
				Log.info(String.format("[Thread - '%s'] Processing Single execution unit method...",
						Thread.currentThread().getName()));
				processSingleExecutionUnit();
				singleExecutionCountDown.countDown();
			} else {
				Log.info(String.format("[Thread - '%s'] Waiting for Single execution unit method processing to complete",
						Thread.currentThread().getName()));
				singleExecutionCountDown.await();
			}

		} catch (FileNotFoundException e) {
			Log.error(e.toString());
		} catch (IOException e) {
			Log.error(e.toString());
		} catch (InterruptedException e) {
			Log.error(e.toString());
		}
	}

	// Perform login with Administrator user. Not using page object intentionally to avoid cyclic dependency amongst packages.
	private void initiateFirstTimeLogin() {
		Log.method("initiateFirstTimeLogin");
		// Check if 'AutomationAdmin' user is present in database. If NOT initiate first time login.
		User user = null;
		try {
			user = User.getUser(this.getLoginUser());
		} catch (Exception ex) {
			Log.warn(String.format("Did NOT find '%s' user", this.getLoginUser()));
		}
		if (user == null) {
			Log.info("Initiating first time login...");
			final String loginPageText = "Login";
			final String homePageText = "Home - Surveyor";
			final Integer timeout = 30;
			String loginPageUrl = String.format("%s/Account/Login", this.getBaseUrl());
			WebDriver webDriver = this.getDriver();
			webDriver.get(loginPageUrl);
			WebElementExtender.waitForPageLoad(loginPageText, timeout, webDriver);
			WebElement tbUserName = webDriver.findElement(By.id("Username"));
			WebElement tbPassword = webDriver.findElement(By.id("Password"));
			WebElement btnLogin = webDriver.findElement(By.cssSelector("[type='submit']"));
			Log.info("Input username as '" + this.getFirstTimeLoginUser() + "'");
			tbUserName.sendKeys(this.getFirstTimeLoginUser());
			Log.info("Input password as '<HIDDEN>'");
			tbPassword.sendKeys(this.getFirstTimeLoginPwd());
			Log.clickElementInfo("Login");
			btnLogin.click();
			WebElementExtender.waitForPageLoad(homePageText, timeout, webDriver);
		}
	}

	private void processSingleExecutionUnit() throws IOException {
		// cleanup processes once for all tests.
		TestSetup.stopChromeProcesses();

		// If running against GRID wait for nodes to become available. If necessary spin up more nodes.
		boolean isRunningOnGrid = this.getRunningOnRemoteServer() != null && this.getRunningOnRemoteServer().trim().equalsIgnoreCase("true");
		if (isRunningOnGrid) {
			// If parallel is not specified run on Single node. Else get specified number of nodes.
			Integer parallelNodes = 1;
			if (isParallelBuildEnabled()) {
				parallelNodes = getParallelBuildRequiredNodes();
			}

			// Final local variable as in-arg to PollManager PollCondition.
			final Integer requiredNodes = parallelNodes;
			Log.info("Automation Run Triggered on Grid. Checking for available grid nodes");
			Integer availableNodes = GridNodesManager.getAvailableNodes(requiredNodes, getParallelBuildRunUUID(), getBrowser(), getPlatform());
			Log.info(String.format("%d nodes are required for test run. Available nodes = %d", requiredNodes, availableNodes));
			if (availableNodes < requiredNodes) {
				Integer nodesToSpin = requiredNodes - availableNodes;
				Log.info(String.format("Short of %d grid nodes. Spinning %d EXTRA grid nodes...", nodesToSpin, nodesToSpin));
				GridNodesManager.requestGridNodes(nodesToSpin, getParallelBuildRunUUID(), getBrowser(), getPlatform());
				Log.info(String.format("Waiting for %d grid nodes to become available..", requiredNodes));

				PollManager.poll(() -> (GridNodesManager.getAvailableNodes(requiredNodes, getParallelBuildRunUUID(), getBrowser(), getPlatform()) < requiredNodes),
						Constants.DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC, Constants.DEFAULT_MAX_RETRIES);

			} else {
				Log.info(String.format("%d grid nodes are available for running the tests!", requiredNodes));
			}
		}

		// Verify and invoke first time login with 'Administrator' user if necessary.
		initiateFirstTimeLogin();

		// If survey upload is enabled, upload the specified surveys to
		// environment.
		// We have a 2nd level of check (ie matching base url provided) to
		// prevent accidental upload to unintended environment.
		if (isUploadSurveyEnabled() && this.getSurveyUploadBaseUrl().equalsIgnoreCase(this.baseURL)) {
			try {
				uploadSurveys();
			} catch (Exception e) {
				Log.error(String.format("ERROR when uploading survey. EXCEPTION: %s", e.toString()));
			}
		}

		// If pushDBSeed is enabled, push DB seed data to environment.
		// We have a 2nd level of check (ie matching base url provided) to
		// prevent accidental upload to unintended environment.
		if (isPushDBSeedEnabled() && this.getPushDBSeedBaseUrl().equalsIgnoreCase(this.baseURL)) {
			try {
				DbSeedExecutor.executeAllDataSeed();
			} catch (Exception e) {
				Log.error(String.format("ERROR when pushing DB seed. EXCEPTION: %s", e.toString()));
			}
		}
	}

	private void setParallelBuildTestProperties() {
		String parallelBuildEnabledValue = this.testProp.getProperty("parallelBuild.Enabled");
		if (parallelBuildEnabledValue != null && !parallelBuildEnabledValue.isEmpty()) {
			TestSetup.setParallelBuildEnabled(Boolean.valueOf(parallelBuildEnabledValue));
		}
		String requiredNodes = this.testProp.getProperty("parallelBuild.RequiredNodes");
		if (requiredNodes != null && requiredNodes != "") {
			this.setParallelBuildRequiredNodes(Integer.valueOf(requiredNodes));
		}
		this.setParallelBuildRunUUID(this.testProp.getProperty("parallelBuild.UUID"));
	}

	private void setComplianceReportBaselineGenerationTestProperties() {
		String collectReportJobPerfMetric = this.testProp.getProperty("complianceReport_collectReportJobPerfMetric");
		if (collectReportJobPerfMetric != null && collectReportJobPerfMetric != "") {
			this.setCollectReportJobPerfMetric(Boolean.valueOf(collectReportJobPerfMetric));
		}
		String generateBaselineSSRSImages = this.testProp.getProperty("complianceReport_generateBaselineSSRSImages");
		if (generateBaselineSSRSImages != null && generateBaselineSSRSImages != "") {
			this.setGenerateBaselineSSRSImages(Boolean.valueOf(generateBaselineSSRSImages));
		}
		String generateBaselineViewImages = this.testProp.getProperty("complianceReport_generateBaselineViewImages");
		if (generateBaselineViewImages != null && generateBaselineViewImages != "") {
			this.setGenerateBaselineViewImages(Boolean.valueOf(generateBaselineViewImages));
		}
		String generateBaselineShapeFiles = this.testProp.getProperty("complianceReport_generateBaselineShapeFiles");
		if (generateBaselineShapeFiles != null && generateBaselineShapeFiles != "") {
			this.setGenerateBaselineShapeFiles(Boolean.valueOf(generateBaselineShapeFiles));
		}
	}

	private void setUploadSurveyTestProperties() {
		String uploadSurveyEnabledValue = this.testProp.getProperty("surveyUpload.Enabled");
		if (uploadSurveyEnabledValue != null && uploadSurveyEnabledValue != "") {
			this.setUploadSurveyEnabled(Boolean.valueOf(uploadSurveyEnabledValue));
		}
		this.setSurveysToUpload(this.testProp.getProperty("surveyUpload.Surveys"));
		this.setSurveyUploadBaseUrl(this.testProp.getProperty("surveyUpload.BaseUrl"));
	}

	private void setPushDBSeedTestProperties() {
		String pushDBSeedEnabledValue = this.testProp.getProperty("pushDBSeed.Enabled");
		if (pushDBSeedEnabledValue != null && pushDBSeedEnabledValue != "") {
			this.setPushDBSeedEnabled(Boolean.valueOf(pushDBSeedEnabledValue));
		}
		this.setPushDBSeedBaseUrl(this.testProp.getProperty("pushDBSeed.BaseUrl"));
	}

	private void setPerformanceExecutionTestProperties() {
		String executionTimesForLightLoadBaselineCollection = this.testProp
				.getProperty("complianceReport_executionTimesForLightLoadBaselineCollection");
		if (executionTimesForLightLoadBaselineCollection != null
				&& executionTimesForLightLoadBaselineCollection != "") {
			this.setExecutionTimesForLightLoadReportJobPerfBaseline(
					Integer.valueOf(executionTimesForLightLoadBaselineCollection));
		}
		String executionTimesForMediumLoadBaselineCollection = this.testProp
				.getProperty("complianceReport_executionTimesForMediumLoadBaselineCollection");
		if (executionTimesForMediumLoadBaselineCollection != null
				&& executionTimesForMediumLoadBaselineCollection != "") {
			this.setExecutionTimesForMediumLoadReportJobPerfBaseline(
					Integer.valueOf(executionTimesForMediumLoadBaselineCollection));
		}
		String executionTimesForHighLoadBaselineCollection = this.testProp
				.getProperty("complianceReport_executionTimesForHighLoadBaselineCollection");
		if (executionTimesForHighLoadBaselineCollection != null && executionTimesForHighLoadBaselineCollection != "") {
			this.setExecutionTimesForHighLoadReportJobPerfBaseline(
					Integer.valueOf(executionTimesForHighLoadBaselineCollection));
		}
		String executionTimesForUltraHighLoadBaselineCollection = this.testProp
				.getProperty("complianceReport_executionTimesForUltraHighLoadBaselineCollection");
		if (executionTimesForUltraHighLoadBaselineCollection != null
				&& executionTimesForUltraHighLoadBaselineCollection != "") {
			this.setExecutionTimesForUltraHighLoadReportJobPerfBaseline(
					Integer.valueOf(executionTimesForUltraHighLoadBaselineCollection));
		}
	}

	private void setLoggingTestProperties() {
		String logCategorySSRSPdfContent = this.testProp.getProperty("logCategory.SSRSPdfContent.Enabled");
		if (logCategorySSRSPdfContent != null && logCategorySSRSPdfContent != "") {
			this.setLogCategorySSRSPdfContentEnabled(Boolean.valueOf(logCategorySSRSPdfContent));
		}
		String logCategoryComplianceReportActions = this.testProp
				.getProperty("logCategory.ComplianceReportActions.Enabled");
		if (logCategoryComplianceReportActions != null && logCategoryComplianceReportActions != "") {
			this.setLogCategoryComplianceReportActionsEnabled(Boolean.valueOf(logCategoryComplianceReportActions));
		}
		String logCategoryClickWebElement = this.testProp
				.getProperty("logCategory.ClickWebElement.Enabled");
		if (logCategoryClickWebElement != null && logCategoryClickWebElement != "") {
			this.setLogCategoryClickWebElementEnabled(Boolean.valueOf(logCategoryClickWebElement));
		}
		String logCategoryVerboseLogging = this.testProp
				.getProperty("logCategory.VerboseLogging.Enabled");
		if (logCategoryVerboseLogging != null && logCategoryVerboseLogging != "") {
			this.setLogCategoryVerboseLoggingEnabled(Boolean.valueOf(logCategoryVerboseLogging));
		}
	}

	private static boolean isExecutingSimulatorTestMethod(String methodName) {
		String[] nameParts = methodName.split("\\_");
		if (nameParts != null && nameParts.length > 1) {
			if (nameParts[1].equalsIgnoreCase("SimulatorTest")) {
				return true;
			}
		}

		return false;
	}

	public void initializeDBProperties() {
		this.dbIPAddress = this.testProp.getProperty("dbIPAddress");
		this.dbName = this.testProp.getProperty("dbName");
		this.dbPortNo = this.testProp.getProperty("dbPortNo");
		this.dbUser = this.testProp.getProperty("dbUser");
		this.dbPassword = this.testProp.getProperty("dbPassword");
	}

	public static boolean isAnalyzerRunning() {
		return ProcessUtility.isProcessRunning("Picarro.Surveyor.Analyzer.exe");
	}

	public static boolean isAnalyzerShutdown() {
		return !isAnalyzerRunning();
	}

	public static boolean isSupervisorRunning() {
		return ProcessUtility.isProcessRunning("supervisor.exe");
	}

	public boolean isRunningDebug() {
		return debug;
	}

	private static boolean isRunningLocally() throws UnknownHostException {
		Log.info("Checking if test is running locally.");
		boolean isRunningLocally = true;
		String hostAddress = InetAddress.getLocalHost().getHostAddress();
		Log.info("Executing machine IP is: " + hostAddress);
		for (String machineIp : CI_MACHINES) {
			Log.info("Checking with IP: " + machineIp);
			if (hostAddress.equalsIgnoreCase(machineIp)) {
				isRunningLocally = false;
				break;
			}
		}

		return isRunningLocally;
	}

	public InputStream loadTestProperties(String rootPath) throws FileNotFoundException, IOException {
		testPropFileName = getExecutionPath(rootPath) + "tests" + File.separator + "surveyor" + File.separator
				+ "test.properties";
		InputStream inputStream = new FileInputStream(testPropFileName);
		testProp = new Properties();
		testProp.load(inputStream);
		return inputStream;
	}

	public static void restartAnalyzer() throws IOException {
		Log.info("Restarting Analyzer EXE...");
		stopAnalyzerIfRunning();
		deleteAnalyzerLocalDB3();
		startAnalyzer();
	}

	public static void deleteAnalyzerLocalDB3() throws IOException {
		Log.method("deleteAnalyzerLocalDB3");
		stopAnalyzerIfRunning();
		String appDataFolder = SystemUtility.getAppDataFolder();
		Path surveyorDb3Path = Paths.get(appDataFolder,
				"Picarro" + File.separator + "Surveyor" + File.separator + "Data" + File.separator + "Surveyor.db3");
		Log.info(String.format("Deleting file - '%s'", surveyorDb3Path.toString()));
		FileUtility.deleteFile(surveyorDb3Path);
	}

	public static void replayDB3Script(String defnFileName, String db3FileName) {
		try {
			// Replace %DB3_FILE_PATH% in defn file with full path to
			// db3FileName.
			String rootFolder = getExecutionPath(getRootPath()) + "data";
			String defnFullPath = rootFolder + File.separator + "defn" + File.separator + defnFileName;
			String db3FileFullPath = rootFolder + File.separator + "db3" + File.separator + db3FileName;

			String workingDefnFile = getUUIDString() + "_" + defnFileName;
			String workingDefnFullPath = Paths.get(rootFolder + File.separator + "defn", workingDefnFile).toString();

			// Create a copy of the defn file in %TEMP% folder.
			Files.copy(Paths.get(defnFullPath), Paths.get(workingDefnFullPath));

			// Update the working copy.
			Hashtable<String, String> placeholderMap = new Hashtable<String, String>();
			placeholderMap.put("%DB3_FILE_PATH%", db3FileFullPath);
			FileUtility.updateFile(workingDefnFullPath, placeholderMap);

			// Replay DB3 script
			replayDB3Script(workingDefnFile);

			// Delete the working copy of the defn file.
			Files.delete(Paths.get(workingDefnFullPath));
		} catch (IOException e) {
			Log.error(e.toString());
		}
	}

	public static void replayDB3Script(String defnFileName) {
		// Execute replay script from the contained folder.
		try {
			String replayCmdFolder = getExecutionPath(getRootPath()) + "data" + File.separator + "defn";
			String replayCmdFullPath = replayCmdFolder + File.separator + REPLAY_DEFN_CURL_FILE;
			String command = "cd \"" + replayCmdFolder + "\" && " + replayCmdFullPath + " " + defnFileName;
			Log.info("Executing replay script. Command -> " + command);
			ProcessOutputInfo processOutputInfo = ProcessUtility.executeProcess(command, /* isShellCommand */ true,
					/* waitForExit */ true);
			analyzerProcess = processOutputInfo.getProcess();
		} catch (IOException e) {
			Log.error(e.toString());
		}
	}

	public static void simulatorTestStarting(Description description) {
		Log.info("Started executing " + description.getClassName() + "." + description.getMethodName() + "() test...");
		if (TestSetup.isExecutingSimulatorTestMethod(description.getMethodName())) {
			Log.info("Installing simulator pre-reqs. Start Analyzer and Replay DB3 script.");
			try {
				TestSetup.stopAnalyzerIfRunning();
				TestSetup.setupSimulatorPreReqs();
				TestSetup.startAnalyzer();
			} catch (IOException e) {
				Log.error(e.toString());
			}
		}
	}

	public static void simulatorTestFinishing(Description description) {
		Log.info("Finished executing " + description.getClassName() + "." + description.getMethodName() + "() test...");
		if (TestSetup.isExecutingSimulatorTestMethod(description.getMethodName())) {
			Log.info("Stop Analyzer.");
			TestSetup.stopAnalyzer();
		}
	}

	/*
	 * Verify and install pre-requisites (if any) needed for running Simulator
	 * based test cases.
	 */
	public static void setupSimulatorPreReqs() throws IOException {
		String batchFileDirectory = getExecutionPath(getRootPath()) + "lib" + File.separator;

		String command = "SetupSimulatorPreReqs-Local.cmd";
		ProcessUtility.executeProcess(batchFileDirectory + command, /* isShellCommand */ true, /* waitForExit */ true);
	}

	public static void startAnalyzer() throws IOException {
		// Kill any existing instance of Analyzer if running.
		Log.info("Starting Analyzer EXE...");
		stopAnalyzerIfRunning();

		// Start the Analyzer process.
		ProcessOutputInfo processOutputInfo = ProcessUtility.executeProcess(ANALYZER_EXE_PATH,
				/* isShellCommand */ false, /* waitForExit */ false);
		analyzerProcess = processOutputInfo.getProcess();
		if (analyzerProcess.isAlive()) {
			Log.info("Analyzer EXE started Successfully!");
		} else {
			Log.info("Analyzer EXE did NOT start.");
		}
	}

	// for testing code debug only
	public void slowdownInSeconds(int seconds) {
		idleForSeconds(seconds);
	}

	public static void idleForSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

	public static void stopChromeProcesses() {
		ProcessUtility.killProcess("chromedriver.exe", /* killChildProcesses */ true);
		ProcessUtility.killProcess("chrome.exe", /* killChildProcesses */ true);
	}

	public void startReplay(String defnFileName) throws InstantiationException, IllegalAccessException, IOException {
		String replayCmdFolder = getExecutionPath(getRootPath()) + "data" + File.separator + "defn";
		String defnFullPath = replayCmdFolder + File.separator + defnFileName;

		HostSimInvoker simulator = new HostSimInvoker();
		simulator.startReplay(defnFullPath);
	}

	public static void stopAnalyzer() {
		ProcessUtility.killProcess("Picarro.Surveyor.Analyzer.exe", /* killChildProcesses */ true);
		ProcessUtility.killProcess("supervisor.exe", /* killChildProcesses */ true);
	}

	public static String getNetworkProxyHarFileContent() throws Exception {
		Har harData = TestContext.INSTANCE.getTestSetup().getNetworkProxyHarData();
		String harDataFile = TestSetup.getUUIDString() + "_HarData.dat";
		String harDataFullPath = Paths.get(TestSetup.getSystemTempDirectory(), harDataFile).toString();
		Log.info(String.format("Creating HAR data file at: %s", harDataFullPath));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(harDataFullPath));
		try {
			// HarData file should be created in the system temp directory.
			harData.writeTo(bufferedWriter);
			Log.info(String.format("Created HAR data file at: %s", harDataFullPath));
		} catch (IOException e) {
			Log.error(e.toString());
		} finally {
			bufferedWriter.close();
		}

		return FileUtility.readFileContents(harDataFullPath);
	}

	private static void stopAnalyzerIfRunning() throws UnknownHostException {
		if (isAnalyzerRunning()) {
			Log.info("An instance of Analyzer EXE is currently running. Stopping...");
			stopAnalyzer();
		}
	}

	public static void stopReplay() {
		// Execute replay script from the contained folder.
		try {
			String stopReplayCmdFolder = getExecutionPath(getRootPath()) + "data" + File.separator + "defn";
			String stopReplayCmdFullPath = stopReplayCmdFolder + File.separator + STOP_REPLAY_CURL_FILE;
			String command = "cd \"" + stopReplayCmdFolder + "\" && " + stopReplayCmdFullPath;
			Log.info("Executing stop replay command. Command -> " + command);
			ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);
		} catch (IOException e) {
			Log.error(e.toString());
		}
	}

	public static void updateAnalyzerConfiguration(String p3Url, String analyzerSerialNumber,
			String analyzerSharedKey) {
		updateAnalyzerConfiguration(p3Url, analyzerSerialNumber, analyzerSharedKey, 0);
	}

	public static void updateAnalyzerConfiguration(String p3Url, String analyzerSerialNumber, String analyzerSharedKey,
			Integer maxSurveyDuration) {
		try {
			String workingFolder = getExecutionPath(getRootPath());
			String libFolder = workingFolder + "lib";
			String updCmdFullPath = libFolder + File.separator + UPDATE_ANALYZER_CONFIGURATION_CMD;

			String workingUpdCmdFile = getUUIDString() + "_" + UPDATE_ANALYZER_CONFIGURATION_CMD;
			String workingUpdCmdFullPath = Paths.get(libFolder + File.separator, workingUpdCmdFile).toString();

			// Create a copy of the update config cmd file.
			Files.copy(Paths.get(updCmdFullPath), Paths.get(workingUpdCmdFullPath));

			// Update the working copy.
			Hashtable<String, String> placeholderMap = new Hashtable<String, String>();
			placeholderMap.put("%WORKING_DIR%", workingFolder);
			placeholderMap.put("%0%", p3Url);
			placeholderMap.put("%1%", analyzerSerialNumber);
			placeholderMap.put("%2%", analyzerSharedKey);
			placeholderMap.put("%3%", String.valueOf(maxSurveyDuration));
			FileUtility.updateFile(workingUpdCmdFullPath, placeholderMap);

			// Execute update config cmd.
			executeUpdateConfigCmd(workingUpdCmdFile);

			// Delete the working copy of the update config cmd file.
			Files.delete(Paths.get(workingUpdCmdFullPath));
		} catch (IOException e) {
			Log.error(e.toString());
		}
	}

	public void postAutomationRunResult(String htmlResultFilePath) {
		try {
			String workingFolder = getRootPath();
			String postResultCmdFolder = getExecutionPath(getRootPath()) + "lib";
			String postResultCmdFullPath = postResultCmdFolder + File.separator + POST_AUTOMATION_RUN_RESULT_CMD;
			String command = "cd \"" + postResultCmdFolder + "\" && " + postResultCmdFullPath +
					String.format(" \"%s\" \"%s\" \"%s\"", workingFolder, getAutomationReportingApiEndpoint(), htmlResultFilePath);
			Log.info("Posting automation run result. Command -> " + command);
			ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);
		} catch (IOException e) {
			Log.error(e.toString());
		}
	}

	public void postReportJobPerfStat(String reportTitle, String reportJobTypeId, String reportJobTypeName,
			 LocalDateTime reportJobStartTime, LocalDateTime reportJobEndTime,
			 LocalDateTime testExecutionStartDate, LocalDateTime testExecutionEndDate,
			 LocalDateTime reportStartTime, LocalDateTime reportEndTime,
			 String buildNumber, String testCaseID, Environment environment) {
		try {
			String workingFolder = getRootPath();
			String reportJobStatCmdFolder = getExecutionPath(getRootPath()) + "lib";
			String reportJobStatCmdFullPath = reportJobStatCmdFolder + File.separator + POST_REPORT_JOB_PERF_STAT_CMD;
			String command = "cd \"" + reportJobStatCmdFolder + "\" && " + reportJobStatCmdFullPath +
					String.format(" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\"",
							workingFolder, getAutomationReportingApiEndpoint(), reportTitle,
							 reportJobTypeId, reportJobTypeName,
							 DateUtility.getLongDateString(reportJobStartTime),
							 DateUtility.getLongDateString(reportJobEndTime),
							 DateUtility.getLongDateString(testExecutionStartDate),
							 DateUtility.getLongDateString(testExecutionEndDate),
							 DateUtility.getLongDateString(reportStartTime),
							 DateUtility.getLongDateString(reportEndTime),
							 buildNumber, testCaseID, environment.getIndex());
			Log.info("Posting report job perf stat. Command -> " + command);
			ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);
		} catch (IOException e) {
			Log.error(e.toString());
		}
	}

	public void postAnalyzerApiPerfStat(String aPIName, String aPIUrl, int numberOfSamples,
			 float average, float median, float responseTime90Pctl, float responseTime95Pctl, float responsetime99Pctl,
			 float min, float max, float errorPercent, float throughputPerSec, float kBPerSec, LocalDateTime testExecutionStartDate,
			 LocalDateTime testExecutionEndDate, String buildNumber, String testCaseID, Environment environment) {
		try {
			String workingFolder = getRootPath();
			String analyzerApiCmdFolder = getExecutionPath(getRootPath()) + "lib";
			String analyzerApiCmdFullPath = analyzerApiCmdFolder + File.separator + POST_ANALYZER_API_PERF_STAT_CMD;
			String command = "cd \"" + analyzerApiCmdFolder + "\" && " + analyzerApiCmdFullPath +
					String.format(" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\""
							+ " \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\"", workingFolder, getAutomationReportingApiEndpoint(), aPIName,
							 aPIUrl, numberOfSamples, average, median, responseTime90Pctl, responseTime95Pctl, responsetime99Pctl,
							 min, max, errorPercent, throughputPerSec, kBPerSec,
							 DateUtility.getLongDateString(testExecutionStartDate),
							 DateUtility.getLongDateString(testExecutionEndDate),
							 buildNumber, testCaseID, environment.getIndex());
			Log.info("Posting analyzer api perf stat. Command -> " + command);
			ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);
		} catch (IOException e) {
			Log.error(e.toString());
		}
	}

	public static void main(String[] args) {
		TestSetup testSetup = new TestSetup(true /* initialization=TRUE */);
		TestContext.INSTANCE.setTestSetup(testSetup);

		// Run the Unit test for BrowserMob Proxy.
		testBrowserMobProxyMethods(testSetup);
	}

	/**
	 * Executes the unit tests for BrowserMobProxy related methods.
	 *
	 * @param testSetup
	 */
	private static void testBrowserMobProxyMethods(TestSetup testSetup) {
		String validTag = "stnd-sqacudr";
		Survey objSurvey = null;
		boolean stoppedProxy = false;
		try {
			Log.info("Running test - testStartNetworkProxy() ...");
			testStartNetworkProxy(testSetup);
			Log.info("Running test - testNetworkConnectionOff() ...");
			testNetworkConnectionOff(testSetup, validTag, objSurvey);
			Log.info("Running test - testNetworkConnectionOn() ...");
			testNetworkConnectionOn(testSetup, validTag, objSurvey);
			stoppedProxy = true;
			Log.info("Running test - testHarDataFile() ...");
			testHarDataFile(testSetup);
		} catch (Exception e) {
			Assert.fail("UNEXPECTED EXCEPTION: " + ExceptionUtility.getStackTraceString(e));
		} finally {
			if (!stoppedProxy) {
				testStopNetworkProxy(testSetup);
				if (testSetup.getDriver() != null) {
					testSetup.getDriver().quit();
				}
			}
		}
	}

	/**
	 * Tests startNetworkProxy() method.
	 *
	 * @param testSetup
	 * @throws MalformedURLException
	 */
	private static void testStartNetworkProxy(TestSetup testSetup) throws MalformedURLException {
		testSetup.startNetworkProxy(true);
	}

	/**
	 * Tests stopNetworkProxy() method.
	 *
	 * @param testSetup
	 */
	private static void testStopNetworkProxy(TestSetup testSetup) {
		testSetup.stopNetworkProxy();
	}

	/**
	 * Tests getNetworkProxyHarData() method.
	 *
	 * @param testSetup
	 */
	private static void testHarDataFile(TestSetup testSetup) throws IOException {
		Har harData = testSetup.getNetworkProxyHarData();
		Assert.assertTrue(harData != null, "Har Data should NOT be NULL.");
		String harDataFile = TestSetup.getUUIDString() + "_HarData.dat";
		String harDataFullPath = Paths.get(TestSetup.getSystemTempDirectory(), harDataFile).toString();
		Log.info(String.format("Creating HAR data file at: %s", harDataFullPath));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(harDataFullPath));
		try {
			// HarData file should be created in the system temp directory.
			harData.writeTo(bufferedWriter);
			Log.info(String.format("Created HAR data file at: %s", harDataFullPath));
		} catch (IOException e) {
			// No exception thrown here.
			e.printStackTrace();
		} finally {
			bufferedWriter.close();
		}
		Assert.assertTrue(FileUtility.readFileContents(harDataFullPath).length() > 0,
				"HarData file should have content.");
	}

	/**
	 * Tests turnOnNetworkConnection() method.
	 *
	 * @param testSetup
	 */
	private static void testNetworkConnectionOn(TestSetup testSetup, String validTag, Survey objSurvey) {
		testSetup.turnOnHttpTraffic();
		try {
			objSurvey = Survey.getSurvey(validTag);
			testSetup.getDriver().get(testSetup.baseURL);
		} catch (Exception e) {
			// No exception should be thrown here, as Network Connection is back
			// ON.
			Log.error("UNEXPECTED ERROR: " + ExceptionUtility.getStackTraceString(e));
		}
		Assert.assertTrue(objSurvey != null, "Survey object should NOT be NULL.");
		Assert.assertTrue(testSetup.getDriver().getPageSource().contains("Log In"));
	}

	/**
	 * Tests turnOffNetworkConnection() method.
	 *
	 * @param testSetup
	 * @throws MalformedURLException
	 */
	private static void testNetworkConnectionOff(TestSetup testSetup, String validTag, Survey objSurvey)
			throws MalformedURLException {
		testSetup.turnOffHttpTraffic();
		try {
			objSurvey = Survey.getSurvey(validTag);
			testSetup.getDriver().get(testSetup.baseURL);
		} catch (Exception e) {
			// This should throw an exception as Network Connection is OFF.
			Log.info("EXPECTED ERROR: " + ExceptionUtility.getStackTraceString(e));
		}
		Assert.assertTrue(objSurvey != null, "Survey object should NOT be NULL. "
				+ "Network connection is NOT disabled. ONLY Http traffic should be disabled.");
		Assert.assertTrue(testSetup.getDriver().getPageSource().contains("ERR_EMPTY_RESPONSE"));
	}

	public void uploadSurveys() throws Exception {
		List<String> surveyInfos = RegexUtility.split(surveysToUpload, RegexUtility.SEMI_COLON_SPLIT_REGEX_PATTERN);
		for (String surveyInfo : surveyInfos) {
			surveyInfo = surveyInfo.replace("{", "");
			surveyInfo = surveyInfo.replace("}", "");
			List<String> surveyInfoParts = RegexUtility.split(surveyInfo, RegexUtility.COMMA_SPLIT_REGEX_PATTERN);
			if (surveyInfoParts == null || surveyInfoParts.size() != 4) {
				throw new IllegalArgumentException(
						"Found incorrect specification for 'surveysToUpload' in test.properties");
			}

			Integer loginUserRowID = Integer.valueOf(surveyInfoParts.get(0));
			Integer db3AnalyzerRowID = Integer.valueOf(surveyInfoParts.get(1));
			Integer surveyRowID = Integer.valueOf(surveyInfoParts.get(2));
			Integer runtimeInSeconds = Integer.valueOf(surveyInfoParts.get(3));

			TestEnvironmentActions.generateSurveyForUser(loginUserRowID, db3AnalyzerRowID, surveyRowID,
					runtimeInSeconds);
		}
	}

	public String getSurveysToUpload() {
		return surveysToUpload;
	}

	public void setSurveysToUpload(String surveysToUpload) {
		this.surveysToUpload = surveysToUpload;
	}

	public boolean isUploadSurveyEnabled() {
		return uploadSurveyEnabled;
	}

	public void setUploadSurveyEnabled(boolean uploadSurveyEnabled) {
		this.uploadSurveyEnabled = uploadSurveyEnabled;
	}

	public boolean isPushDBSeedEnabled() {
		return pushDBSeedEnabled;
	}

	public void setPushDBSeedEnabled(boolean pushDBSeedEnabled) {
		this.pushDBSeedEnabled = pushDBSeedEnabled;
	}

	public boolean isAutomationReportingApiEnabled() {
		return automationReportingApiEnabled;
	}

	public String getSurveyUploadBaseUrl() {
		return surveyUploadBaseUrl;
	}

	public void setSurveyUploadBaseUrl(String surveyUploadBaseUrl) {
		this.surveyUploadBaseUrl = surveyUploadBaseUrl;
	}

	public String getPushDBSeedBaseUrl() {
		return pushDBSeedBaseUrl;
	}

	public void setPushDBSeedBaseUrl(String pushDBSeedBaseUrl) {
		this.pushDBSeedBaseUrl = pushDBSeedBaseUrl;
	}

	/**
	 * Use value of System property over VM property
	 * @param key
	 * @return System property if it's been set, VM property otherwise
	 */
	public String getSystemProperty(String key){
		return getSystemProperty(testProp, key, key);
	}

	public String getSystemProperty(Properties testProp, String sysKey, String propKey){
		String propValue = null;
		try{
		     propValue = System.getProperty(sysKey);
		}catch(Exception e){
			Log.warn(e.toString());
		}
		if(propValue==null){
			propValue = testProp.getProperty(propKey);
		}
		return propValue;
	}

	public String getRunningOnRemoteServer() {
		return runningOnRemoteServer;
	}

	private void setRunningOnRemoteServer(String runningOnRemoteServer) {
		this.runningOnRemoteServer = runningOnRemoteServer;
	}

	public boolean isLocalGridRun() {
		if (!BaseHelper.isNullOrEmpty(this.isLocalGridRun) && this.isLocalGridRun.trim().equalsIgnoreCase("true"))
			return true;

		return false;
	}

	public void setIsLocalGridRun(String isLocalGridRun) {
		this.isLocalGridRun = isLocalGridRun;
	}

	public String getRemoteServerHost() {
		return remoteServerHost;
	}

	private void setRemoteServerHost(String remoteServerHost) {
		this.remoteServerHost = remoteServerHost;
	}

	public String getRemoteServerPort() {
		return remoteServerPort;
	}

	private void setRemoteServerPort(String remoteServerPort) {
		this.remoteServerPort = remoteServerPort;
	}

	public String getChromeDriverPath() {
		return chromeDriverPath;
	}

	private void setChromeDriverPath(String chromeDriverPath) {
		this.chromeDriverPath = chromeDriverPath;
	}

	public String getIeDriverPath() {
		return ieDriverPath;
	}

	private void setIeDriverPath(String ieDriverPath) {
		this.ieDriverPath = ieDriverPath;
	}

	public String getImplicitlyWaitSpecialTimeOutInMS() {
		return implicitlyWaitSpecialTimeOutInMS;
	}

	private void setImplicitlyWaitSpecialTimeOutInMS(String implicitlyWaitSpecialTimeOutInMS) {
		this.implicitlyWaitSpecialTimeOutInMS = implicitlyWaitSpecialTimeOutInMS;
	}

	public String getPlatform() {
		return platform;
	}

	private void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getBrowser() {
		return browser;
	}

	private void setBrowser(String browser) {
		this.browser = browser;
	}

	public boolean isRemoteBrowser() {
		return isRemoteBrowser;
	}

	private void setRemoteBrowser(boolean isRemoteBrowser) {
		this.isRemoteBrowser = isRemoteBrowser;
	}

	public static boolean isParallelBuildEnabled() {
		return parallelBuildEnabled;
	}

	public static void setParallelBuildEnabled(boolean parallelBldEnabled) {
		parallelBuildEnabled = parallelBldEnabled;
	}

	public String getParallelBuildRunUUID() {
		return parallelBuildRunUUID;
	}

	public void setParallelBuildRunUUID(String parallelBuildRunUUID) {
		this.parallelBuildRunUUID = parallelBuildRunUUID;
	}

	public Integer getParallelBuildRequiredNodes() {
		return parallelBuildRequiredNodes;
	}

	public void setParallelBuildRequiredNodes(Integer parallelBuildRequiredNodes) {
		this.parallelBuildRequiredNodes = parallelBuildRequiredNodes;
	}


	public String getAutomationReportingApiEndpoint() {
		return automationReportingApiEndpoint;
	}

	public void setAutomationReportingApiEndpoint(String automationReportingApiEndpoint) {
		this.automationReportingApiEndpoint = automationReportingApiEndpoint;
	}
}