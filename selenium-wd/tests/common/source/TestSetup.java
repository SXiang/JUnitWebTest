/**
 * 
 */
package common.source;

import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.runner.Description;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import surveyor.dataaccess.source.Survey;
import surveyor.scommon.source.LoginPage;

/**
 * This is the initial class to setup up the testing environment and
 * configuration
 * 
 * 1. Load the testing property for test setup information.
 * 
 * 2. Setting up the drivers.
 * 
 * 3. It is ongoing and add more code here later when needed
 * 
 * @version 1.0
 * @author zlu
 * 
 */
public class TestSetup {

	private static final String UPDATE_ANALYZER_CONFIGURATION_CMD = "UpdateAnalyzerConfiguration.cmd";
	private static final String[] CI_MACHINES = { "20.20.20.59", "20.20.10.82", "10.0.2.15", "10.200.2.48" };
	private static String testPropFileName;

	public static final String REPLAY_DEFN_CURL_FILE = "replay-defn-curl.bat";
	public static final String STOP_REPLAY_CURL_FILE = "replay-stop.bat";
	public static final String ANALYZER_EXE_PATH = "C:\\PicarroAnalyzer\\Picarro.Surveyor.Analyzer.exe";
	public static final String TEST_ANALYZER_SERIAL_NUMBER = "SimAuto-Analyzer3";

	private static Process analyzerProcess;
	private DesiredCapabilities capabilities = null;
	private BrowserMobProxy networkProxy;

	private Properties testProp;

	private String baseURL;
	private String runningOnRemoteServer;
	private String remoteServerHost;
	private String remoteServerPort;

	private String loginUser;
	private String loginPwd;

	private String loginUser0000;
	private String loginPwd0000;
	private String loginUser0001;
	private String loginPwd0001;
	private String loginUserDisplayName;

	private String browser;
	private String chromeDriverPath;
	private String ieDriverPath;

	private String runEnvironment;
	private String testRunCategory;

	private String implicitlyWaitTimeOutInSeconds;
	private String implicitlyWaitSpecialTimeOutInSeconds;
	private String implicitlyWaitSpecialTimeOutInMS;

	private String language;
	private boolean debug;

	private DateFormat dateFormat;
	private Calendar calendar;
	private String randomNumber;

	private WebDriver driver;
	private String slowdownInSeconds; // For debugging the code and not
										// recommended to use in real test case

	private String downloadPath;

	private String dbIPAddress;
	private String dbPortNo;
	private String dbName;
	private String dbUser;
	private String dbPassword;
	private String computerName;

	private boolean collectReportJobPerfMetric;
	private boolean generateBaselineSSRSImages;
	private boolean generateBaselineViewImages;
	private boolean generateBaselineShapeFiles;

	public TestSetup() {
		initialize();
	}

	public TestSetup(Boolean initialize) {
		if (initialize) {
			initialize();
		}
	}

	public static ExtentReports createExtentReport(String reportClassName) {
		String executionPath = null;
		try {
			executionPath = TestSetup.getExecutionPath(TestSetup.getRootPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		String runEnvironment = TestContext.INSTANCE.getRunEnvironment();
		String reportFilePath = executionPath + "reports" + File.separator
				+ String.format("report-%s-%s.html", runEnvironment, reportClassName);
		String configFilePath = executionPath + "tests" + File.separator + "extent-config.xml";

		ExtentReports extent = new ExtentReports(reportFilePath, true /* replaceExisting */, DisplayOrder.NEWEST_FIRST,
				NetworkMode.ONLINE, Locale.US);
		extent.addSystemInfo("ChromeDriver Version", "2.20");
		extent.addSystemInfo("Environment", runEnvironment);
		extent.loadConfig(new File(configFilePath));
		return extent;
	}

	private void driverSetup() {
		try {
			if (this.runningOnRemoteServer != null && this.runningOnRemoteServer.trim().equalsIgnoreCase("Yes")
					&& this.browser != null) {
				switch (this.browser.trim()) {
				case "chrome":
					Log.info("-----Chrome it is ----");
					setChromeBrowserCapabilitiesForGrid();
					break;
				case "ie":
					driver = new RemoteWebDriver(new URL("http://" + this.remoteServerHost + ":4444/wd/hub/"),
							DesiredCapabilities.internetExplorer());
					break;
				case "ff":

					this.capabilities = DesiredCapabilities.firefox();
					this.capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					driver = new RemoteWebDriver(new URL("http://" + this.remoteServerHost + ":4444/wd/hub/"),
							this.capabilities);
					break;
				}

				Log.info("\nRunning Selenium Server for use with RemoteDrivers and the server is running on: "
						+ this.remoteServerHost + "\n");
			} else if (this.browser != null && (this.ieDriverPath != null || this.chromeDriverPath != null)) {
				switch (this.browser.trim()) {
				case "chrome":
					setChromeBrowserCapabilities();
					break;
				case "ie":
					System.setProperty("webdriver.ie.driver", this.ieDriverPath);
					System.out.println("\nThe System Propery 'webdriver.ie.driver' is: "
							+ System.getProperty("webdriver.ie.driver").toString() + "\n");
					driver = new InternetExplorerDriver();
					break;
				case "ff":
					driver = new FirefoxDriver();
					break;
				}
				Log.info("\nRunning WebDriver API\n");
			} else {
				Log.info("\nWebDriver setup failed, please check the config setting in test.properites file.\n");
				System.exit(1);
			}

			driver.manage().timeouts().implicitlyWait(Long.parseLong(this.implicitlyWaitTimeOutInSeconds.trim()),
					TimeUnit.SECONDS);
			System.out.println("\nThe default implicitlyWaitTimeOut has been set to "
					+ this.implicitlyWaitTimeOutInSeconds.trim() + " seconds" + "\n");

		} catch (Exception e) {

			Log.error(e.toString());
			System.exit(1);

		}
	}

	private void setChromeBrowserCapabilities() {
		setChromeBrowserCapabilities(null);
	}
	
	private void setChromeBrowserCapabilities(Proxy proxy) {
		Log.info("-----Chrome it is ----");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", this.downloadPath);
		this.capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments(Arrays.asList("--incognito", "test-type"));
		options.addArguments("chrome.switches", "--disable-extensions");
		options.setExperimentalOption("prefs", prefs);
		this.capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		if (proxy != null) {
			this.capabilities.setCapability(CapabilityType.PROXY, proxy);
		}
		System.setProperty("webdriver.chrome.driver", this.chromeDriverPath);
		Log.info("\nThe System Propery 'webdriver.chrome.driver' is: "
				+ System.getProperty("webdriver.chrome.driver").toString() + "\n");
		driver = new ChromeDriver(this.capabilities);
	}

	private void setChromeBrowserCapabilitiesForGrid() throws MalformedURLException {
		setChromeBrowserCapabilitiesForGrid(null);
	}
	
	private void setChromeBrowserCapabilitiesForGrid(Proxy proxy) throws MalformedURLException {
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", this.downloadPath);
		this.capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments(Arrays.asList("--incognito", "test-type"));
		options.addArguments("chrome.switches", "--disable-extensions");
		options.setExperimentalOption("prefs", prefs);
		this.capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		if (proxy != null) {
			this.capabilities.setCapability(CapabilityType.PROXY, proxy);
		}
		driver = new RemoteWebDriver(new URL("http://" + this.remoteServerHost + ":4444/wd/hub/"),
				this.capabilities);
	}

	/* NETWORK PROXY related methods */
	/* EXAMPLE USAGE:
	 * 1. Using Proxy to limit Upstream/Downstream KBPS.
	 *    startNetworkProxy(true|false);
	 *    setNetworkProxyDownstreamKbps(<long>);
	 *    setNetworkProxyUpstreamKbps(<long>);
	 *    ... <perform test actions> ...
	 *    stopNetworkProxy();
	 *    
	 * 2. Using Proxy to turn OFF/ON HTTP Traffic for Selenium tests.
	 *    turnOffHttpTraffic();
	 *    ... <perform test actions> ...
	 *    turnOnHttpTraffic()
	 */

	public void startNetworkProxy(boolean createHarFile) throws MalformedURLException {
		// start the proxy
		networkProxy = new BrowserMobProxyServer();
		networkProxy.start(0);

		// when we start the network proxy we are recycling the driver object. Quit the driver if present.
		if (this.driver != null) {
			this.driver.quit();
		}
		
		// get the selenium proxy object
		Proxy seleniumProxy = ClientUtil.createSeleniumProxy(networkProxy);
		setChromeBrowserCapabilities(seleniumProxy);

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
			startNetworkProxy(false /*createHarFile*/);
		}

		this.setNetworkProxyDownstreamKbps(0);
		this.setNetworkProxyUpstreamKbps(0);
	}

	public void turnOnHttpTraffic() {
		if (networkProxy != null) {
			// when we stop the network proxy we should recycling the driver object to remove the Proxy capability.
			if (this.driver != null) {
				this.driver.quit();
			}

			setChromeBrowserCapabilities();  // No proxy.
			this.stopNetworkProxy();
		}
	}

	private static void executeUpdateConfigCmd(String workingUpdCmdFile) {
		// Execute update analyzer configuration script from the contained folder.
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

	public WebDriver getDriver() {
		return this.driver;
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

	public boolean isGenerateBaselineShapeFiles() {
		return generateBaselineShapeFiles;
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

			this.runningOnRemoteServer = this.testProp.getProperty("runningOnRemoteServer");
			this.remoteServerHost = this.testProp.getProperty("remoteServerHost");
			this.remoteServerPort = this.testProp.getProperty("remoteServerPort");
			this.loginUser = this.testProp.getProperty("loginUser");
			this.loginPwd = this.testProp.getProperty("loginPwd");
			this.loginUser0000 = this.testProp.getProperty("loginUser0000");
			this.loginPwd0000 = this.testProp.getProperty("loginPwd0000");
			this.loginUserDisplayName = this.testProp.getProperty("loginUserDisplayName");

			initializeDBProperties();

			this.browser = this.testProp.getProperty("browser");
			Log.info("\nThe browser is: " + this.browser + "\n");

			this.ieDriverPath = this.testProp.getProperty("ieDriverPath");

			this.chromeDriverPath = getExecutionPath(rootPath) + "lib" + File.separator + "chromedriver.exe";
			this.implicitlyWaitTimeOutInSeconds = this.testProp.getProperty("implicitlyWaitTimeOutInSeconds");
			this.implicitlyWaitSpecialTimeOutInSeconds = this.testProp
					.getProperty("implicitlyWaitSpecialTimeOutInSeconds");
			this.implicitlyWaitSpecialTimeOutInMS = this.testProp.getProperty("implicitlyWaitSpecialTimeOutInMS");

			this.runEnvironment = this.testProp.getProperty("runEnvironment");
			this.testRunCategory = this.testProp.getProperty("testRunCategory");
			
			this.setCollectReportJobPerfMetric(Boolean.valueOf(this.testProp.getProperty("complianceReport_collectReportJobPerfMetric")));
			this.setGenerateBaselineSSRSImages(Boolean.valueOf(this.testProp.getProperty("complianceReport_generateBaselineSSRSImages")));
			this.setGenerateBaselineViewImages(Boolean.valueOf(this.testProp.getProperty("complianceReport_generateBaselineViewImages")));
			this.setGenerateBaselineShapeFiles(Boolean.valueOf(this.testProp.getProperty("complianceReport_generateBaselineShapeFiles")));

			this.language = this.testProp.getProperty("language");

			if (!isRunningLocally()) {
				this.downloadPath = this.testProp.getProperty("downloadPath");
			} else {
				this.downloadPath = System.getProperty("user.home") + File.separator + "Downloads" + File.separator;
			}

			if (this.testProp.getProperty("debug").equals("true")) {
				this.debug = true;
			} else {
				this.debug = false;
			}

			this.slowdownInSeconds = this.testProp.getProperty("slowdownInSeconds");
			this.randomNumber = Long.toString((new Random()).nextInt(1000000));
			Log.info("\nThe random number is: " + this.randomNumber + "\n");

			driverSetup();
			inputStream.close();

		} catch (FileNotFoundException e) {
			Log.error(e.toString());
		} catch (IOException e) {
			Log.error(e.toString());
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
		startAnalyzer();
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
			analyzerProcess = ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);
		} catch (IOException e) {
			Log.error(e.toString());
		}
	}

	public static void simulatorTestStarting(Description description) {
		Log.info("Started executing " + description.getClassName() + "." + description.getMethodName() + "() test...");
		if (TestSetup.isExecutingSimulatorTestMethod(description.getMethodName())) {
			Log.info("Installing simulator pre-reqs. Start Analyzer and Replay DB3 script.");
			try {
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
		analyzerProcess = ProcessUtility.executeProcess(ANALYZER_EXE_PATH, /* isShellCommand */ false,
				/* waitForExit */ false);
		if (analyzerProcess.isAlive()) {
			Log.info("Analyzer EXE started Successfully!");
		} else {
			Log.info("Analyzer EXE did NOT start.");
		}
	}

	// for testing code debug only
	public void slowdownInSeconds(int seconds) {
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
		ProcessUtility.killProcess("supervisor.exe", /* killChildProcesses */ true);
		ProcessUtility.killProcess("Picarro.Surveyor.Analyzer.exe", /* killChildProcesses */ true);
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

	public static void updateAnalyzerConfiguration(String analyzerSerialNumber, String analyzerSharedKey) {
		updateAnalyzerConfiguration(analyzerSerialNumber, analyzerSharedKey, 0);
	}

	public static void updateAnalyzerConfiguration(String analyzerSerialNumber, String analyzerSharedKey,
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

	public static void main(String[] args) {
		TestSetup testSetup = new TestSetup(true /* initialization=TRUE */);
		TestContext.INSTANCE.setTestSetup(testSetup);

		// Run the Unit test for BrowserMob Proxy. 
		testBrowserMobProxyMethods(testSetup);
	}

	/**
	 * Executes the unit tests for BrowserMobProxy related methods.
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
	 * @param testSetup
	 * @throws MalformedURLException 
	 */
	private static void testStartNetworkProxy(TestSetup testSetup) throws MalformedURLException {
		testSetup.startNetworkProxy(true);
	}

	/**
	 * Tests stopNetworkProxy() method.
	 * @param testSetup
	 */
	private static void testStopNetworkProxy(TestSetup testSetup) {
		testSetup.stopNetworkProxy();
	}

	/**
	 * Tests getNetworkProxyHarData() method.
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
		Assert.assertTrue(FileUtility.readFileContents(harDataFullPath).length()>0, "HarData file should have content.");
	}

	/**
	 * Tests turnOnNetworkConnection() method.
	 * @param testSetup
	 */
	private static void testNetworkConnectionOn(TestSetup testSetup, String validTag, Survey objSurvey) {
		testSetup.turnOnHttpTraffic();
		try {
			objSurvey = Survey.getSurvey(validTag);
			testSetup.getDriver().get(testSetup.baseURL);
		} catch (Exception e) {
			// No exception should be thrown here, as Network Connection is back ON.
			Log.error("UNEXPECTED ERROR: " + ExceptionUtility.getStackTraceString(e));
		}
		Assert.assertTrue(objSurvey != null, "Survey object should NOT be NULL.");
		Assert.assertTrue(testSetup.getDriver().getPageSource().contains("Log In"));
	}

	/**
	 * Tests turnOffNetworkConnection() method.
	 * @param testSetup
	 * @throws MalformedURLException 
	 */
	private static void testNetworkConnectionOff(TestSetup testSetup, String validTag, Survey objSurvey) throws MalformedURLException {
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
}