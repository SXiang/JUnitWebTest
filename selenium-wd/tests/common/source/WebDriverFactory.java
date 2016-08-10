package common.source;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

public class WebDriverFactory {
	private static ThreadLocal<WebDriverWrapper> threadLocalDriver = new ThreadLocal<WebDriverWrapper>() {
	    @Override 
	    protected WebDriverWrapper initialValue() {
	    	WebDriverWrapper webDriver = createDefaultWebDriver();
	        return webDriver;
	    }
	};

	protected static WebDriverWrapper createDefaultWebDriver() {
		WebDriverWrapper webDriverWrapper = new WebDriverWrapper();
		webDriverWrapper.driverSetup();
		return webDriverWrapper;
	}
	
	public static WebDriver getDriver() {
		return threadLocalDriver.get().getDriver();
	}
	
	public static void setChromeBrowserCapabilities() {
		threadLocalDriver.get().setChromeBrowserCapabilities();
	}

	public static void setChromeBrowserCapabilities(Proxy seleniumProxy) {
		threadLocalDriver.get().setChromeBrowserCapabilities(seleniumProxy);
	}
}
