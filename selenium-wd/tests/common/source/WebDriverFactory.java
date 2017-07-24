package common.source;

import java.net.MalformedURLException;
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

	protected static WebDriverWrapper createAndroidAppNativeDriver() throws MalformedURLException {
		WebDriverWrapper webDriverWrapper = new WebDriverWrapper();
		webDriverWrapper.createAndroidAppNativeDriver();
		return webDriverWrapper;
	}

	protected static WebDriverWrapper createAndroidAppWebDriver() throws MalformedURLException {
		WebDriverWrapper webDriverWrapper = new WebDriverWrapper();
		webDriverWrapper.createAndroidAppWebDriver();
		return webDriverWrapper;
	}

	public static WebDriver getAppiumDriver() {
		return WebDriverWrapper.getAppiumDriver();
	}

	public static WebDriver getEmulationDriver(String emulatedDeviceName) {
		WebDriverWrapper webDriverWrapper = new WebDriverWrapper();
		webDriverWrapper.setDeviceEmulationEnabled(true);
		webDriverWrapper.setEmulatedDeviceName(emulatedDeviceName);
		webDriverWrapper.driverSetup();
		return webDriverWrapper.getDriver();
	}

	public static WebDriver getDriver() {
		return getDriver(0);
	}

	public static WebDriver getDriver(Integer index) {
		return getDriver(index, true /*reuse*/);
	}

	public static WebDriver getAndroidAppNativeDriver() {
		return getAndroidAppDriver(true /*isNative*/);
	}

	public static WebDriver getAndroidAppWebDriver() {
		return getAndroidAppDriver(false /*isNative*/);
	}

	private static WebDriver getAndroidAppDriver(boolean isNative) {
		ThreadLocal<WebDriverWrapper> threadLocalDriver = new ThreadLocal<WebDriverWrapper>() {
		    @Override
		    protected WebDriverWrapper initialValue() {
		    	WebDriverWrapper webDriver = null;
				try {
					if (isNative) {
						webDriver = createAndroidAppNativeDriver();
					} else {
						webDriver = createAndroidAppWebDriver();
					}
				} catch (MalformedURLException ex) {
					Log.error(ExceptionUtility.getStackTraceString(ex));
				}
		        return webDriver;
		    }
		};

		threadLocalDriverList.add(threadLocalDriver);

		if (isNative) {
			return threadLocalDriver.get().getAndroidAppNativeDriver();
		}

		return threadLocalDriver.get().getAndroidAppWebDriver();
	}

	public static WebDriver getDriver(Integer index, Boolean reuse) {
		if (reuse) {
			// If driver threadLocal object exists return it.
			if (threadLocalDriverList.size() > index) {
				return threadLocalDriverList.get(index).get().checkGetDriver();
			}
		}

		// Create new threadLocal driver and add to List.
		ThreadLocal<WebDriverWrapper> threadLocalDriver = new ThreadLocal<WebDriverWrapper>() {
		    @Override
		    protected WebDriverWrapper initialValue() {
		    	WebDriverWrapper webDriver = createDefaultWebDriver();
		        return webDriver;
		    }
		};

		if (!reuse) {
			// If driver threadLocal object exists replace it.
			if (threadLocalDriverList.size() > index) {
				threadLocalDriverList.set(index, threadLocalDriver);
			}
		}

		threadLocalDriverList.add(threadLocalDriver);
		return threadLocalDriver.get().checkGetDriver();
	}

	public static int getDriversCount() {
		int count = 0;
		if (threadLocalDriverList != null) {
			count = threadLocalDriverList.size();
		}

		return count;
	}

	public static void quitDrivers() {
		quitDrivers(true /*quitDefaultDriver*/);
	}

	public static void quitDrivers(Boolean quitDefaultDriver) {
		if (threadLocalDriverList.size() > 0) {
			int startIndex = 1;
			if (quitDefaultDriver) {
				startIndex = 0;
			}

			int lenMinusOne = threadLocalDriverList.size()-1;
			for (int index = lenMinusOne; index >= startIndex; index--) {
				threadLocalDriverList.get(index).get().checkGetDriver().quit();
				threadLocalDriverList.remove(index);
			}
		}
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
		threadLocalDriverList.get(index).get().setChromeBrowserCapabilities(seleniumProxy, null /*emulatedDeviceName*/);
	}
}