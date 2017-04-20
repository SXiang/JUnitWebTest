package common.source;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverWrapper {
	private String runningOnRemoteServer;
	private String browser;
	private String remoteServerHost;
	private String remoteServerPort;
	private String ieDriverPath;
	private String chromeDriverPath;
	private long implicitlyWaitTimeOutInSeconds;
	private String downloadPath;
	private String platform;

	private String appiumServerHost;
	private String appiumServerPort;
	private String iosApp;
	private String androidBrowserName;
	private String iosVersion, androidVersion;
	private String mobilePlatform;
	private String iosDeviceName, androidDeviceName;

	private RemoteWebDriver driver;
	private static RemoteWebDriver appiumDriver;
	private DesiredCapabilities capabilities;
	private boolean isRemoteBrowser;

	public WebDriverWrapper() {
		this.runningOnRemoteServer = TestContext.INSTANCE.getTestSetup().getRunningOnRemoteServer();
		this.browser = TestContext.INSTANCE.getTestSetup().getBrowser();
		this.remoteServerHost = TestContext.INSTANCE.getTestSetup().getRemoteServerHost();
		this.remoteServerPort = TestContext.INSTANCE.getTestSetup().getRemoteServerPort();
		this.ieDriverPath = TestContext.INSTANCE.getTestSetup().getIeDriverPath();
		this.chromeDriverPath = TestContext.INSTANCE.getTestSetup().getChromeDriverPath();
		this.implicitlyWaitTimeOutInSeconds = TestContext.INSTANCE.getTestSetup().getImplicitlyWaitTimeOutInSeconds();
		this.downloadPath = TestContext.INSTANCE.getTestSetup().getDownloadPath();
		this.platform = TestContext.INSTANCE.getTestSetup().getPlatform();
		this.isRemoteBrowser = TestContext.INSTANCE.getTestSetup().isRemoteBrowser();

		this.appiumServerHost = TestContext.INSTANCE.getTestSetup().getAppiumServerHost();
		this.appiumServerPort = TestContext.INSTANCE.getTestSetup().getAppiumServerPort();
		this.iosApp = TestContext.INSTANCE.getTestSetup().getIosApp();
		this.androidBrowserName = TestContext.INSTANCE.getTestSetup().getAndroidBrowserName();
		this.iosVersion = TestContext.INSTANCE.getTestSetup().getIosVersion();
		this.mobilePlatform = TestContext.INSTANCE.getTestSetup().getMobilePlatform();
		this.iosDeviceName = TestContext.INSTANCE.getTestSetup().getIosDeviceName();
		this.androidVersion = TestContext.INSTANCE.getTestSetup().getAndroidVersion();
		this.androidDeviceName = TestContext.INSTANCE.getTestSetup().getAndroidDeviceName();
	}

	public void driverSetup() {
		try {
			if (this.runningOnRemoteServer != null && this.runningOnRemoteServer.trim().equalsIgnoreCase("true")
					&& this.browser != null) {
				switch (this.browser.trim()) {
				case "chrome":
					Log.info("-----Chrome it is ----");
					setChromeBrowserCapabilitiesForGrid();
					break;
				case "ie":
					setDriver(new RemoteWebDriver(new URL("http://" + this.remoteServerHost + ":" + this.remoteServerPort + "/wd/hub/"),
							DesiredCapabilities.internetExplorer()));
					break;
				case "ff":
					this.capabilities = DesiredCapabilities.firefox();
					this.capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					setDriver(new RemoteWebDriver(new URL("http://" + this.remoteServerHost + ":" + this.remoteServerPort + "/wd/hub/"),
							this.capabilities));
					break;
				}
				isRemoteBrowser = true;
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
					setDriver(new InternetExplorerDriver());
					break;
				case "ff":
					setDriver(new FirefoxDriver());
					break;
				}
				Log.info("\nRunning WebDriver API\n");
			} else {
				Log.info("\nWebDriver setup failed, please check the config setting in test.properites file.\n");
				System.exit(1);
			}

			getDriver().manage().timeouts().implicitlyWait(this.implicitlyWaitTimeOutInSeconds,
					TimeUnit.SECONDS);
			System.out.println("\nThe default implicitlyWaitTimeOut has been set to "
					+ String.valueOf(this.implicitlyWaitTimeOutInSeconds) + " seconds" + "\n");

		} catch (Exception e) {

			Log.error(e.toString());
			System.exit(1);

		}
	}

	public RemoteWebDriver setupAppiumRemoteWebDriver(){
		this.capabilities = new DesiredCapabilities();
		this.capabilities.setCapability("platformName", this.mobilePlatform);

		if(this.mobilePlatform.equalsIgnoreCase("iOS")){
			this.capabilities.setCapability("app", this.iosApp);
			this.capabilities.setCapability("platformVersion", this.iosVersion);
			this.capabilities.setCapability("deviceName", this.iosDeviceName);
			this.capabilities.setCapability("automationName", "XCUITest");
			this.capabilities.setCapability("launchTimeout", 600000);
			this.capabilities.setCapability("newCommandTimeout", 360000);
			this.capabilities.setCapability("autoAcceptAlerts", true);
			this.capabilities.setCapability("autoWebview", true);
			this.capabilities.setCapability("orientation", "PORTRAIT");
			this.capabilities.setCapability("fullReset", false);
		}else{
			this.capabilities.setCapability(CapabilityType.BROWSER_NAME, this.androidBrowserName);
			this.capabilities.setCapability("platformVersion", this.androidVersion);
			this.capabilities.setCapability("deviceName", this.androidDeviceName);
			this.capabilities.setCapability("avdLaunchTimeout", 300000);
			this.capabilities.setCapability("automationName", "Appium");
			this.capabilities.setCapability("appPackage", "com.android.chrome");
			this.capabilities.setCapability("appActivity", "com.google.android.apps.chrome.Main");
		}
		try {
			appiumDriver = new RemoteWebDriver(new URL("http://" + this.appiumServerHost + ":" + this.appiumServerPort + "/wd/hub/"),
					capabilities);
		} catch (MalformedURLException e) {
			Log.error(e.toString());
			System.exit(1);
		}
		isRemoteBrowser = true;
		Log.info("\nRunning Appium Server for use with RemoteDrivers and the server is running on: "
				+ this.appiumServerHost + "\n");
		return appiumDriver;
	}

	public void setChromeBrowserCapabilities() {
		setChromeBrowserCapabilities(null);
	}

	public void setChromeBrowserCapabilities(Proxy proxy) {
		Log.info("-----Chrome it is ----");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", this.downloadPath);
		prefs.put("download.prompt_for_download", false);
		prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
		this.capabilities = DesiredCapabilities.chrome();
		this.capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
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
		ChromeDriver chromeDriver = new ChromeDriver(this.capabilities);
		FunctionUtil.warnOnError(() -> allowChromeAutomationExtensionInIncognito(chromeDriver));
		setDriver(chromeDriver);
	}

	public void setChromeBrowserCapabilitiesForGrid() throws MalformedURLException {
		setChromeBrowserCapabilitiesForGrid(null);
	}

	public void setChromeBrowserCapabilitiesForGrid(Proxy proxy) throws MalformedURLException {
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("download.default_directory", this.downloadPath);
		prefs.put("download.prompt_for_download", false);
		prefs.put("profile.default_content_setting_values.automatic_downloads", 1);
		this.capabilities = DesiredCapabilities.chrome();

		if (this.platform.equalsIgnoreCase("windows")) {
			this.capabilities.setPlatform(Platform.WINDOWS);
		} else if (this.platform.equalsIgnoreCase("linux")) {
			this.capabilities.setPlatform(Platform.LINUX);
		}

		this.capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments(Arrays.asList("--incognito", "test-type"));
		options.addArguments("chrome.switches", "--disable-extensions");
		options.setExperimentalOption("prefs", prefs);
		this.capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		if (proxy != null) {
			this.capabilities.setCapability(CapabilityType.PROXY, proxy);
		}
		setDriver(new RemoteWebDriver(new URL("http://" + this.remoteServerHost + ":" + this.remoteServerPort + "/wd/hub/"), this.capabilities));
	}

	public RemoteWebDriver getDriver() {
		return driver;
	}

	public void setDriver(RemoteWebDriver driver) {
		this.driver = driver;
	}

	public static RemoteWebDriver getAppiumDriver() {
		if(!isAppiumDriverInTest()){
			appiumDriver = new WebDriverWrapper().setupAppiumRemoteWebDriver();
		}
		return appiumDriver;
	}

	public static boolean isAppiumDriverInTest(){
		return appiumDriver != null;
	}

	private void allowChromeAutomationExtensionInIncognito(ChromeDriver chromeDriver) {
		chromeDriver.get("chrome://extensions-frame");
		WebElement checkbox = chromeDriver.findElement(By.xpath("//label[@class='incognito-control']/input[@type='checkbox']"));
		if (!checkbox.isSelected()) {
		    checkbox.click();
		}
	}
}