package common.source;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.openqa.selenium.logging.LogEntry;

import io.appium.java_client.AppiumDriver;

public class CrashLogger {
	private static final Integer LOG_PROCESSING_TIME = 10;
	private static final Integer MAX_LOG_LINES = 100;
	private ExecutorService loggingExecutor;
	private LogcatLog logger;

	public static class LogcatLog implements Runnable {
		private List<String> logs = new ArrayList<String>();
		private AppiumDriver<?> appiumDriver;

		public LogcatLog(AppiumDriver<?> appiumDriver) {
			this.appiumDriver = appiumDriver;
		}

		@Override
		public void run() {
			List<LogEntry> logEntries = appiumDriver.manage().logs().get("logcat").getAll();
			logEntries.stream()
				.map(l -> String.format("[%d] : %s %s", l.getTimestamp(), l.getMessage(), BaseHelper.getLineSeperator()))
				.collect(Collectors.toList())
				.forEach(s -> logs.add(s));
		}

		public String getLogs() {
			if (logs!=null) {
				int len = logs.size();
				List<String> subList = logs;
				if (len > MAX_LOG_LINES) {
					subList = logs.subList(len-MAX_LOG_LINES, len-1);

				}
				StringBuilder builder = new StringBuilder();
				for (String value : subList) {
					builder.append(value);
				}

				return builder.toString();
			}

			return "";
		}
	}

	public String grabLogs(AppiumDriver<?> appiumDriver) {
		loggingExecutor = Executors.newSingleThreadExecutor();
		this.logger = new LogcatLog(appiumDriver);
		loggingExecutor.execute(this.logger);
		TestContext.INSTANCE.stayIdle(LOG_PROCESSING_TIME);
		return this.logger.getLogs();
	}

	public void close() {
		loggingExecutor.shutdownNow();
	}
}