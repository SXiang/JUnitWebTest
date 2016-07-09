package common.source;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverThread {
	private WebDriver driver;
	private final String operatingSystem = System.getProperty("os.name").toUpperCase();
	private final String systemArchitecture = System.getProperty("os.arch");

	public WebDriver getDriver() throws Exception {
		if (null == driver) {
			Log.info(" ");
			Log.info("Current Operating System: " + operatingSystem);
			Log.info("Current Architecture: " + systemArchitecture);
			Log.info("Current Browser Selection: Chrome");
			Log.info(" ");

			Log.info("-----Chrome it is ----");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("download.default_directory", TestContext.INSTANCE.getTestSetup().getDownloadPath());
			DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
			desiredCapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments(Arrays.asList("--incognito", "test-type"));
			options.addArguments("chrome.switches", "--disable-extensions");
			options.setExperimentalOption("prefs", prefs);
			desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
			System.setProperty("webdriver.chrome.driver", TestContext.INSTANCE.getTestSetup().getChromeDriverPath());
			Log.info("\nThe System Propery 'webdriver.chrome.driver' is: "
					+ System.getProperty("webdriver.chrome.driver").toString() + "\n");
			driver = new ChromeDriver(desiredCapabilities);
		}

		return driver;
	}
	
	public void quitDriver() {
		if (null != driver) {
			driver.quit();
			driver = null;
		}
	}
}