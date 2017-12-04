package common.source;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;

public class BrowserConsoleLogCollector {
	private WebDriver driver;
	private StringBuilder errors;

	public static BrowserConsoleLogCollector newInstance(WebDriver driver) {
		BrowserConsoleLogCollector consoleLogCollector = new BrowserConsoleLogCollector();
		consoleLogCollector.setDriver(driver);
		consoleLogCollector.errors = new StringBuilder();
		return consoleLogCollector;
	}

	public List<String> collectLogs() {
		List<LogEntry> logEntries = getDriver().manage().logs().get("browser").getAll();
		List<String> consoleLogs = logEntries.stream()
			.map(l -> String.format("[%d] : %s %s", l.getTimestamp(), l.getMessage(), BaseHelper.getLineSeperator()))
			.collect(Collectors.toList());
		return consoleLogs;
	}

	/**
	 * Redirects browser console logs to automation logs.
	 */
	public BrowserConsoleLogCollector redirectOutputToLog() {
		List<String> consoleLogs = collectLogs();
		consoleLogs.forEach(log -> {
			if (isErrorLine(log)) {
				errors.append(log + BaseHelper.getLineSeperator());
				Log.error(log);
			} else {
				Log.info(log);
			}
		});

		return this;
	}

	public String getErrors() {
		return this.errors.toString();
	}

	public Boolean hasErrors() {
		return this.errors.length() > 0;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	private boolean isErrorLine(String log) {
		return log.toLowerCase().contains("error");
	}
}