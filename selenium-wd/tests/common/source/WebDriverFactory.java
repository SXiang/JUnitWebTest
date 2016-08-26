package common.source;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

public class WebDriverFactory {
	private static List<ThreadLocal<WebDriverWrapper>> threadLocalDriverList = Collections.synchronizedList(new ArrayList<ThreadLocal<WebDriverWrapper>>()); 

	protected static WebDriverWrapper createDefaultWebDriver() {
		WebDriverWrapper webDriverWrapper = new WebDriverWrapper();
		webDriverWrapper.driverSetup();
		return webDriverWrapper;
	}
	
	public static WebDriver getDriver() {
		return getDriver(0);
	}
	
	public static WebDriver getDriver(Integer index) {
		// If driver threadLocal object exists return it. 
		if (threadLocalDriverList.size() > index) {
			return threadLocalDriverList.get(index).get().getDriver();
		}
		
		// Create new threadLocal driver and add to List.
		ThreadLocal<WebDriverWrapper> threadLocalDriver = new ThreadLocal<WebDriverWrapper>() {
		    @Override 
		    protected WebDriverWrapper initialValue() {
		    	WebDriverWrapper webDriver = createDefaultWebDriver();
		        return webDriver;
		    }
		};
		
		threadLocalDriverList.add(threadLocalDriver);
		return threadLocalDriver.get().getDriver();
	}
	
	public static void setChromeBrowserCapabilities() {
		setChromeBrowserCapabilities(0);
	}

	public static void setChromeBrowserCapabilities(Integer index) {
		threadLocalDriverList.get(index).get().setChromeBrowserCapabilities();
	}

	public static void setChromeBrowserCapabilities(Proxy seleniumProxy) {
		setChromeBrowserCapabilities(seleniumProxy, 0);
	}

	public static void setChromeBrowserCapabilities(Proxy seleniumProxy, Integer index) {
		threadLocalDriverList.get(index).get().setChromeBrowserCapabilities(seleniumProxy);
	}
}