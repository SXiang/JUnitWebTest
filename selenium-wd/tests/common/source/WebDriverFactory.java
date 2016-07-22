package common.source;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

public class WebDriverFactory {
	private ThreadLocal<WebDriverWrapper> threadLocalDriver = new ThreadLocal<WebDriverWrapper>() {
	    @Override protected WebDriverWrapper initialValue() {
	    	WebDriverWrapper webDriver = createDefaultWebDriver();
	        return webDriver;
	    }
	};

	protected WebDriverWrapper createDefaultWebDriver() {
		WebDriverWrapper webDriverWrapper = new WebDriverWrapper();
		webDriverWrapper.driverSetup();
		return webDriverWrapper;
	}
	
	public WebDriver getDriver() {
		return threadLocalDriver.get().getDriver();
	}
	
	public void setChromeBrowserCapabilities() {
		threadLocalDriver.get().setChromeBrowserCapabilities();
	}

	public void setChromeBrowserCapabilities(Proxy seleniumProxy) {
		threadLocalDriver.get().setChromeBrowserCapabilities(seleniumProxy);
	}
}
