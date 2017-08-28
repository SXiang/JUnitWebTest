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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

public class WebDriverWrapper {
	public static final String ANDROID_APP_PACKAGE_NAME = "com.picarroapp";
	protected static final String APPIUM_SERVER_HUB_HOST = "http://127.0.0.1:4723/wd/hub";

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

	private boolean deviceEmulationEnabled;
	private String emulatedDeviceName;

	private WebDriver androidAppNativeDriver;
	private WebDriver androidAppWebDriver;

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
			if (this.isDeviceEmulationEnabled()) {
				Log.info(String.format("-----Chrome : Using Device Emulation. (Emulated device name='%s') ----", this.getEmulatedDeviceName()));
				setChromeBrowserCapabilities(null /*proxy*/, this.getEmulatedDeviceName());
				Log.info("\nRunning WebDriver with device emulation enabled\n");
			} else if (this.runningOnRemoteServer != null && this.runningOnRemoteServer.trim().equalsIgnoreCase("true")
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

	public RemoteWebDriver createAndroidAppWebDriver(boolean isDevice) throws MalformedURLException{
		// CAPABILITIES: https://appium.io/slate/en/master/?ruby#appium-server-capabilities, https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/caps.md
		DesiredCapabilities capabilities=DesiredCapabilities.android();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
		capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, getDeviceNameCapability(isDevice));
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,BrowserType.CHROME);
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");    // timeout in seconds.

		// Create object of URL class and specify the appium server address
		URL url= new URL(APPIUM_SERVER_HUB_HOST);

		AndroidDriver<WebElement> androidDriver = new AndroidDriver<WebElement>(url, capabilities);
		setAndroidAppWebDriver(androidDriver);
		return androidDriver;
	}

	public RemoteWebDriver createAndroidAppNativeDriver(boolean isDevice) throws MalformedURLException{
		// CAPABILITIES: https://appium.io/slate/en/master/?ruby#appium-server-capabilities, https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/caps.md
		DesiredCapabilities capabilities=DesiredCapabilities.android();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.APPIUM);
		capabilities.setCapability(MobileCapabilityType.PLATFORM, Platform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, getDeviceNameCapability(isDevice));
		// NOTE: autoGrantPermissions capability is NOT working along with MobileCapabilityType.APP. Use appPackage and appActivity instead of app.
		capabilities.setCapability("appPackage", ANDROID_APP_PACKAGE_NAME);
		capabilities.setCapability("appActivity", ".MainActivity");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");    // timeout in seconds.
		capabilities.setCapability("autoGrantPermissions", "true");

		// Following capabilities have been used for perf optimization.
		capabilities.setCapability("disableAndroidWatchers", true);
		capabilities.setCapability("resetKeyboard", true);  		// hide keyboard
		capabilities.setCapability("unicodeKeyboard", true);		// hide keyboard

		// Create object of URL class and specify the appium server address
		URL url= new URL(APPIUM_SERVER_HUB_HOST);

		// Create object of  AndroidDriver class and pass the url and capability that we created
		AndroidDriver<WebElement> androidDriver = new AndroidDriver<WebElement>(url, capabilities);
		setAndroidAppNativeDriver(androidDriver);
		return androidDriver;
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
		setChromeBrowserCapabilities(null /*proxy*/, null /*emulatedDeviceName*/);
	}

	public void setChromeBrowserCapabilities(Proxy proxy, String emulatedDeviceName) {
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

		if (emulatedDeviceName != null) {
			Map<String, String> mobileEmulation = new HashMap<String, String>();
			mobileEmulation.put("deviceName", emulatedDeviceName);
			options.setExperimentalOption("mobileEmulation", mobileEmulation);
		}

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

	public WebDriver checkGetDriver() {
		WebDriver driver = getDriver();
		if (driver == null) {
			driver = getAndroidAppNativeDriver();
		}

		if (driver == null) {
			driver = getAndroidAppWebDriver();
		}

		return driver;
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

	public boolean isDeviceEmulationEnabled() {
		return deviceEmulationEnabled;
	}

	public void setDeviceEmulationEnabled(boolean deviceEmulationEnabled) {
		this.deviceEmulationEnabled = deviceEmulationEnabled;
	}

	public String getEmulatedDeviceName() {
		return emulatedDeviceName;
	}

	public void setEmulatedDeviceName(String emulatedDeviceName) {
		this.emulatedDeviceName = emulatedDeviceName;
	}

	public WebDriver getAndroidAppNativeDriver() {
		return androidAppNativeDriver;
	}

	public void setAndroidAppNativeDriver(WebDriver androidAppNativeDriver) {
		this.androidAppNativeDriver = androidAppNativeDriver;
	}

	public WebDriver getAndroidAppWebDriver() {
		return androidAppWebDriver;
	}

	public void setAndroidAppWebDriver(WebDriver androidAppWebDriver) {
		this.androidAppWebDriver = androidAppWebDriver;
	}

	private void allowChromeAutomationExtensionInIncognito(ChromeDriver chromeDriver) {
		chromeDriver.get("chrome://extensions-frame");
		WebElement checkbox = chromeDriver.findElement(By.xpath("//label[@class='incognito-control']/input[@type='checkbox']"));
		if (!checkbox.isSelected()) {
		    checkbox.click();
		}
	}

	private String getDeviceNameCapability(boolean isDevice) {
		String deviceName = "Android Emulator";
		if (isDevice) {
			deviceName = "Galaxy Tab S2";
		}
		return deviceName;
	}
}