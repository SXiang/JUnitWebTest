/**
 * 
 */
package common.source;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

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
	private static final String[] CI_MACHINES = {"20.20.20.59", "20.20.10.82", "10.0.2.15", "10.200.2.48"};
	private static String testPropFileName;
	
	public static final String REPLAY_DEFN_CURL_FILE = "replay-defn-curl.bat";
	public static final String ANALYZER_EXE_PATH = "C:\\PicarroAnalyzer\\Picarro.Surveyor.Analyzer.exe";
	public static final String TEST_ANALYZER_SERIAL_NUMBER = "SimAuto-Analyzer1";
	
	private static Process analyzerProcess;
	
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

	private String implicitlyWaitTimeOutInSeconds;
	private String implicitlyWaitSpecialTimeOutInSeconds;
	private String implicitlyWaitSpecialTimeOutInMS;

	private String language;
	private boolean debug;

	private DateFormat dateFormat;
	private Calendar calendar;
	private String randomNumber;

	private WebDriver driver;
	private String slowdownInSeconds; // For debugging the code and not recommended to use in real test case
	
	private String downloadPath;
	
	private String dbIPAddress;
	private String dbPortNo;
	private String dbName;
	private String dbUser;
	private String dbPassword;
	private String computerName;
	
	public TestSetup() {
		initialize();
	}

	public TestSetup(Boolean initialize) {
		if (initialize) {
			initialize();
		}
	}

	public void initialize() {
		try {

			this.computerName=InetAddress.getLocalHost().getHostAddress();
			Log.info("IP Address: " +computerName);			

			String rootPath = getRootPath();
			InputStream inputStream = loadTestProperties(rootPath);

			this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			this.calendar = Calendar.getInstance();

			Log.info(dateFormat.format(this.calendar.getTime())
					+ "\n");
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
			this.implicitlyWaitSpecialTimeOutInSeconds = this.testProp.getProperty("implicitlyWaitSpecialTimeOutInSeconds");
			this.implicitlyWaitSpecialTimeOutInMS = this.testProp.getProperty("implicitlyWaitSpecialTimeOutInMS");

			this.language = this.testProp.getProperty("language");

			if (!isRunningLocally()) {
				this.downloadPath = this.testProp.getProperty("downloadPath");
			} else {
				this.downloadPath = System.getProperty("user.home")+File.separator+"Downloads"+File.separator;
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

	public InputStream loadTestProperties(String rootPath) throws FileNotFoundException, IOException {
		testPropFileName = getExecutionPath(rootPath) + "tests" + File.separator + "surveyor" + File.separator + "test.properties";
		InputStream inputStream = new FileInputStream(testPropFileName);
		testProp = new Properties();
		testProp.load(inputStream);
		return inputStream;
	}

	public void initializeDBProperties() {
		this.dbIPAddress = this.testProp.getProperty("dbIPAddress");
		this.dbName = this.testProp.getProperty("dbName");
		this.dbPortNo = this.testProp.getProperty("dbPortNo");
		this.dbUser = this.testProp.getProperty("dbUser");
		this.dbPassword = this.testProp.getProperty("dbPassword");
	}

	public static String getRootPath() throws IOException {
		File propertyfile = new File(".");
		return propertyfile.getCanonicalPath();
	}

	public static String getExecutionPath(String rootPath) {
		/* For CI and Eclipse run setup  */
		String executionPath = rootPath +File.separator+"selenium-wd"+ File.separator;; 
		/* For build.xml run locally  */		
		//String executionPath = rootPath+ File.separator;
		return executionPath;
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

	/*
	 * Verify and install pre-requisites (if any) needed for running Simulator based test cases.
	 */
	public static void setupSimulatorPreReqs() throws IOException {
		String batchFileDirectory = getExecutionPath(getRootPath()) + "lib" + File.separator;

		String command = "SetupSimulatorPreReqs-Local.cmd";
		ProcessUtility.executeProcess(batchFileDirectory + command, /*isShellCommand*/ true, /*waitForExit*/ true);
	}
	
	public static void startAnalyzer() throws IOException {
		// Kill any existing instance of Analyzer if running.
		Log.info("Starting Analyzer EXE...");
		stopAnalyzerIfRunning();
		
		// Start the Analyzer process.
		analyzerProcess = ProcessUtility.executeProcess(ANALYZER_EXE_PATH, /*isShellCommand*/ false, /*waitForExit*/ false);
		if (analyzerProcess.isAlive()) {
			Log.info("Analyzer EXE started Successfully!");
		} else {
			Log.info("Analyzer EXE did NOT start.");
		}
	}

	private static void stopAnalyzerIfRunning() throws UnknownHostException {
		if (isAnalyzerRunning()) {
			Log.info("An instance of Analyzer EXE is currently running. Stopping...");
			stopAnalyzer();
		}
	}

	public static void restartAnalyzer() throws IOException {
		Log.info("Restarting Analyzer EXE...");
		stopAnalyzerIfRunning();
		startAnalyzer();
	}
	
	public static void updateAnalyzerConfiguration(String analyzerSerialNumber, String analyzerSharedKey) {
		try {
			// Replace %DB3_FILE_PATH% in defn file with full path to db3FileName.
			String workingFolder = getExecutionPath(getRootPath());
			String libFolder = workingFolder + "lib";
			String updCmdFullPath = libFolder + File.separator + UPDATE_ANALYZER_CONFIGURATION_CMD;
			
			String workingUpdCmdFile = getUUIDString() + "_" + UPDATE_ANALYZER_CONFIGURATION_CMD;
			String workingUpdCmdFullPath = Paths.get(libFolder + File.separator, workingUpdCmdFile).toString();
			
			// Create a copy of the update config cmd file.
			Files.copy(Paths.get(updCmdFullPath), Paths.get(workingUpdCmdFullPath));

			// Update the working copy.
			FileUtility.updateFile(workingUpdCmdFullPath, "%WORKING_DIR%", workingFolder);
			FileUtility.updateFile(workingUpdCmdFullPath, "%1%", analyzerSerialNumber);
			FileUtility.updateFile(workingUpdCmdFullPath, "%2%", analyzerSharedKey);
			
			// Replay DB3 script
			executeUpdateConfigCmd(workingUpdCmdFile);
			
			// Delete the working copy of the defn file.
			Files.delete(Paths.get(workingUpdCmdFullPath));
		} catch (IOException e) {
			Log.error(e.toString());
		}
	}

	private static void executeUpdateConfigCmd(String workingUpdCmdFile) {
		// Execute update analyzer configuration script from the contained folder.
		try {
			String updateCmdFolder = getExecutionPath(getRootPath()) + "lib";
			String updateCmdFileFullPath = updateCmdFolder + File.separator + workingUpdCmdFile;
			String command = "cd \"" + updateCmdFolder + "\" && " + updateCmdFileFullPath;
			Log.info("Executing update Analyzer configuration script. Command -> " + command);
			ProcessUtility.executeProcess(command, /*isShellCommand*/ true, /*waitForExit*/ true);
		} catch (IOException e) {
			Log.error(e.toString());
		}
	}

	public static void replayDB3Script(String defnFileName, String db3FileName) {
		try {
			// Replace %DB3_FILE_PATH% in defn file with full path to db3FileName.
			String rootFolder = getExecutionPath(getRootPath()) + "data";
			String defnFullPath = rootFolder + File.separator + "defn" + File.separator + defnFileName;
			String db3FileFullPath = rootFolder + File.separator + "db3" + File.separator + db3FileName;
			
			String workingDefnFile = getUUIDString() + "_" + defnFileName;
			String workingDefnFullPath = Paths.get(rootFolder + File.separator + "defn", workingDefnFile).toString();
			
			// Create a copy of the defn file in %TEMP% folder.
			Files.copy(Paths.get(defnFullPath), Paths.get(workingDefnFullPath));

			// Update the working copy.
			FileUtility.updateFile(workingDefnFullPath, "%DB3_FILE_PATH%", db3FileFullPath);
			
			// Replay DB3 script
			replayDB3Script(workingDefnFile);
			
			// Delete the working copy of the defn file.
			Files.delete(Paths.get(workingDefnFullPath));
		} catch (IOException e) {
			Log.error(e.toString());
		}
	}

	public static String getSystemTempDirectory() {
		return System.getProperty("java.io.tmpdir");
	}

	public static void replayDB3Script(String defnFileName) {
		// Execute replay script from the contained folder.
		try {
			String replayCmdFolder = getExecutionPath(getRootPath()) + "data" + File.separator + "defn";
			//String defnFullPath = replayCmdFolder + File.separator + defnFileName;	
			String replayCmdFullPath = replayCmdFolder + File.separator + REPLAY_DEFN_CURL_FILE;
			String command = "cd \"" + replayCmdFolder + "\" && " + replayCmdFullPath + " " + defnFileName;
			Log.info("Executing replay script. Command -> " + command);
			analyzerProcess = ProcessUtility.executeProcess(command, /*isShellCommand*/ true, /*waitForExit*/ true);
		} catch (IOException e) {
			Log.error(e.toString());
		}
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

	public static void stopAnalyzer() {
		ProcessUtility.killProcess("Picarro.Surveyor.Analyzer.exe", /*killChildProcesses*/ true);
		ProcessUtility.killProcess("supervisor.exe", /*killChildProcesses*/ true);
	}

	public void startReplay(String defnFileName) throws InstantiationException, IllegalAccessException, IOException {
		String replayCmdFolder = getExecutionPath(getRootPath()) + "data" + File.separator + "defn";
		String defnFullPath = replayCmdFolder + File.separator + defnFileName;			

		HostSimInvoker simulator = new HostSimInvoker();
		simulator.startReplay(defnFullPath);
	}

	public void stopReplay() throws InstantiationException, IllegalAccessException, IOException {
		HostSimInvoker simulator = new HostSimInvoker();
		simulator.stopReplay();
	}

	public boolean hasBrowserQuit() {
		Log.info("Checking if Browser has QUIT. Driver String value: " + this.getDriver().toString());
		return this.getDriver().toString().contains("(null)");
	}

	private void driverSetup() {

		try {
			if (this.runningOnRemoteServer != null
					&& this.runningOnRemoteServer.trim()
							.equalsIgnoreCase("Yes") && this.browser != null) {
				switch (this.browser.trim()) {
					case "chrome":
						Log.info("-----Chrome it is ----");
						Map<String, Object> prefs = new HashMap<String, Object>();
						prefs.put("download.default_directory", this.downloadPath);
						DesiredCapabilities capabilities = DesiredCapabilities.chrome();
						ChromeOptions options = new ChromeOptions();
						options.addArguments(Arrays.asList(
								"--incognito",
								"test-type"));
						options.addArguments("chrome.switches","--disable-extensions");
						options.setExperimentalOptions("prefs", prefs);
						capabilities.setCapability(ChromeOptions.CAPABILITY, options);
						driver = new RemoteWebDriver(new URL("http://"
								+ this.remoteServerHost + ":4444/wd/hub/"),
								capabilities);
						break;
					case "ie":
						driver = new RemoteWebDriver(new URL("http://"
								+ this.remoteServerHost + ":4444/wd/hub/"),
								DesiredCapabilities.internetExplorer());
						break;
					case "ff":
	
						DesiredCapabilities capabilitiesFF = DesiredCapabilities
								.firefox();
						capabilitiesFF.setCapability(
								CapabilityType.ACCEPT_SSL_CERTS, true);
						driver = new RemoteWebDriver(new URL("http://"
								+ this.remoteServerHost + ":4444/wd/hub/"),
								capabilitiesFF);
						break;
				}

				Log.info("\nRunning Selenium Server for use with RemoteDrivers and the server is running on: "
						+ this.remoteServerHost + "\n");
			} else if (this.browser != null && (this.ieDriverPath != null || this.chromeDriverPath != null)) {
				switch (this.browser.trim()) {
					case "chrome":
						Log.info("-----Chrome it is ----");
						Map<String, Object> prefs = new HashMap<String, Object>();
						prefs.put("download.default_directory", this.downloadPath);
						DesiredCapabilities capabilities = DesiredCapabilities.chrome();
						ChromeOptions options = new ChromeOptions();
						options.addArguments(Arrays.asList(
							"--incognito",
							"test-type"));
						options.addArguments("chrome.switches","--disable-extensions");
						options.setExperimentalOptions("prefs", prefs);
						capabilities.setCapability(ChromeOptions.CAPABILITY, options);
										
						System.setProperty("webdriver.chrome.driver",
								this.chromeDriverPath);
						Log.info("\nThe System Propery 'webdriver.chrome.driver' is: "
										+ System.getProperty(
												"webdriver.chrome.driver")
												.toString() + "\n");
						driver = new ChromeDriver(capabilities);
						break;
					case "ie":
						System.setProperty("webdriver.ie.driver", this.ieDriverPath);
						System.out
								.println("\nThe System Propery 'webdriver.ie.driver' is: "
										+ System.getProperty("webdriver.ie.driver")
												.toString() + "\n");
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

			driver.manage()
					.timeouts()
					.implicitlyWait(
							Long.parseLong(this.implicitlyWaitTimeOutInSeconds
									.trim()), TimeUnit.SECONDS);
			System.out
					.println("\nThe default implicitlyWaitTimeOut has been set to "
							+ this.implicitlyWaitTimeOutInSeconds.trim()
							+ " seconds" + "\n");

		} catch (Exception e) {
			
			Log.error(e.toString());
			System.exit(1);
			
		}
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

	//for testing code debug only
	public void slowdownInSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {
			Log.error(e.toString());
		}
	}
	
	public int getSlowdownInSeconds() {
		return Integer.parseInt(this.slowdownInSeconds);
	}

	public boolean isRunningDebug() {
		if (debug)
			return true;
		else
			return false;
	}
	
	public String getRandomNumber() {
		return this.randomNumber;
	}

	public String getFixedSizeRandomNumber(int size) {
		int len = this.randomNumber.length();
		if (len > size) {
			return this.randomNumber.substring(0, size-1);
		} else if (len < size){
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
		} else if (len < size){
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

	public static void main(String[] args) {
		//for code testing
		TestSetup obj = new TestSetup();
		obj.getDriver().quit();
	}
}