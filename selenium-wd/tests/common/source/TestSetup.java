/**
 * 
 */
package common.source;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
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

	private static String testPropFileName;
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
	
	public TestSetup() {
		try {
			String userDir = System.getProperty("user.dir");
			File propertyfile = new File(userDir+"/..");
			testPropFileName  =propertyfile.getCanonicalPath() +File.separator + "test.properties";
			InputStream inputStream = new FileInputStream(testPropFileName);

			testProp = new Properties();
			testProp.load(inputStream);

			this.dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			this.calendar = Calendar.getInstance();
			
			System.out.println();
			
			System.out.println(dateFormat.format(this.calendar.getTime())
					+ "\n");

			this.baseURL = this.testProp.getProperty("baseURL");
			System.out.println("\nThe baseURL is: " + this.baseURL + "\n");

			this.runningOnRemoteServer = this.testProp
					.getProperty("runningOnRemoteServer");
			this.remoteServerHost = this.testProp
					.getProperty("remoteServerHost");
			this.remoteServerPort = this.testProp
					.getProperty("remoteServerPort");
			
			this.loginUser = this.testProp.getProperty("loginUser");
			this.loginPwd = this.testProp.getProperty("loginPwd");

			this.loginUser0000 = this.testProp.getProperty("loginUser0000");
			this.loginPwd0000 = this.testProp.getProperty("loginPwd0000");

			this.loginUserDisplayName = this.testProp
					.getProperty("loginUserDisplayName");

			this.dbIPAddress = this.testProp.getProperty("dbIPAddress");
			this.dbName = this.testProp.getProperty("dbName");
			this.dbPortNo = this.testProp.getProperty("dbPortNo");
			this.dbUser = this.testProp.getProperty("dbUser");
			this.dbPassword = this.testProp.getProperty("dbPassword");

			this.browser = this.testProp.getProperty("browser");
			System.out.println("\nThe browser is: " + this.browser + "\n");

			this.ieDriverPath = this.testProp.getProperty("ieDriverPath");
			
			File chromepath = new File(userDir+"/.."+"/.."+"/..");
			this.chromeDriverPath = chromepath.getCanonicalPath() +File.separator +"lib" + File.separator+"chromedriver.exe";

			this.implicitlyWaitTimeOutInSeconds = this.testProp
					.getProperty("implicitlyWaitTimeOutInSeconds");
			this.implicitlyWaitSpecialTimeOutInSeconds = this.
					testProp.getProperty("implicitlyWaitSpecialTimeOutInSeconds");
			this.implicitlyWaitSpecialTimeOutInMS = this.testProp
					.getProperty("implicitlyWaitSpecialTimeOutInMS");

			this.language = this.testProp.getProperty("language");
			
			//this.downloadPath = this.testProp.getProperty("downloadPath");
			this.downloadPath = "C:/Users/" + System.getProperty("user.name") + "/Downloads/";

			if (this.testProp.getProperty("debug").equals("true")) {
				
				this.debug = true;
				
			} else {
				
				this.debug = false;
				
			}

			this.slowdownInSeconds = this.testProp
					.getProperty("slowdownInSeconds");

			this.randomNumber = Long.toString((new Random()).nextInt(1000000));
			System.out.println("\nThe random number is: " + this.randomNumber
					+ "\n");

			driverSetup();

			inputStream.close();

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
	}

	private void driverSetup() {

		try {
			if (this.runningOnRemoteServer != null
					&& this.runningOnRemoteServer.trim()
							.equalsIgnoreCase("Yes") && this.browser != null) {
				switch (this.browser.trim()) {
				case "chrome":
					DesiredCapabilities capabilities = DesiredCapabilities.chrome();
					ChromeOptions options = new ChromeOptions();
					options.addArguments(Arrays.asList(
							"--incognito",
//							"allow-running-insecure-content",
//							"ignore-certificate-errors", 
							"test-type"));
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

				System.out.println("\nRunning Selenium Server for use with RemoteDrivers and the server is running on: "
						+ this.remoteServerHost + "\n");
			} else if (this.browser != null && (this.ieDriverPath != null || this.chromeDriverPath != null)) {
				switch (this.browser.trim()) {
				case "chrome":
					DesiredCapabilities capabilities = DesiredCapabilities.chrome();
					ChromeOptions options = new ChromeOptions();
					options.addArguments(Arrays.asList(
						"--incognito",
//						"allow-running-insecure-content",
//						"ignore-certificate-errors", 
						"test-type"));
					capabilities.setCapability(ChromeOptions.CAPABILITY, options);
					
					System.setProperty("webdriver.chrome.driver",
							this.chromeDriverPath);
					System.out.println("\nThe System Propery 'webdriver.chrome.driver' is: "
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

				System.out.println("\nRunning WebDriver API\n");
			} else {
				System.out.println("\nWebDriver setup failed, please check the config setting in test.properites file.\n");
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
			
			e.printStackTrace();
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

//	public String getLoginUser0000() {
//		return this.loginUser0000;
//	}
//
//	public String getLoginPwd0000() {
//		
//		return this.loginPwd0000;
//	}

	public String getLoginUserDisplayName() {
		return this.loginUserDisplayName;
	}

	//for testing code debug only
	public void slowdownInSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {
			e.printStackTrace();
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