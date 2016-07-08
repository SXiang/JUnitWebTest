package common.source;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

	private static List<WebDriverThread> webDriverThreadPool = Collections.synchronizedList(new ArrayList<WebDriverThread>());
	private static ThreadLocal<WebDriverThread> driverThread;
	
	private static void initialize() {
		if (driverThread == null) {
			instantiateDriverObject();
		}
	}
	
	public static void instantiateDriverObject() {
		driverThread = new ThreadLocal<WebDriverThread>() {
			@Override
			protected WebDriverThread initialValue() {
					WebDriverThread webDriverThread = new WebDriverThread();
					webDriverThreadPool.add(webDriverThread);
					return webDriverThread;
			}
		};
	}
	
	public static WebDriver getDriver() throws Exception {
		initialize();
		return driverThread.get().getDriver();
	}
	
	public static void quitDriver() throws Exception {
		initialize();
		driverThread.get().quitDriver();
	}
}
