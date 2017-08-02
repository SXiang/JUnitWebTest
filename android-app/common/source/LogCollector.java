package common.source;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.openqa.selenium.logging.LogEntry;

import io.appium.java_client.AppiumDriver;

public class LogCollector {
	private static final Integer LOG_PROCESSING_TIME_IN_SECONDS = 3;
	private static final Integer MAX_ATTEMPTS = 1000;
	private static final Integer MAX_LOG_LINES = 3000;
	private static final String[] EXCLUDE_LOGS_FILTER = {"AppiumUnicodeIME", "AccessibilityNodeInfoDumper", "SurfaceFlinger", "injectKeyEvent"};

	private ExecutorService loggingExecutor;
	private LogcatLog logger;

	public static class LogcatLog implements Runnable {
		private List<String> logs = new ArrayList<String>();
		private AppiumDriver<?> appiumDriver;
		private List<String> excludeFilter;
		private boolean cancelled;

		public LogcatLog(AppiumDriver<?> appiumDriver) {
			this.appiumDriver = appiumDriver;
			this.excludeFilter = Arrays.asList(EXCLUDE_LOGS_FILTER);
		}

		@Override
		public void run() {
			int attempt = 0;
			while (attempt < MAX_ATTEMPTS) {
				if (isCancelled()) {
					break;
				}

				flushLogs();
				try {
					Thread.sleep(LOG_PROCESSING_TIME_IN_SECONDS * 1000);
				} catch (Exception e) {
					Log.warn(e.getMessage());
				}

				attempt++;
			}
		}

		public void flushLogs() {
			List<LogEntry> logEntries = appiumDriver.manage().logs().get("logcat").getAll();
			logEntries.stream()
				.map(l -> String.format("[%d] : %s %s", l.getTimestamp(), l.getMessage(), BaseHelper.getLineSeperator()))
				.collect(Collectors.toList())
				.forEach(s -> {
					if (!isInExclude(excludeFilter, s)) {
						logs.add(s);
					}});
		}

		private boolean isInExclude(List<String> excludeFilter, String str) {
			return this.excludeFilter.stream().anyMatch(e -> str.contains(e)
					&& !str.toLowerCase().contains("react")
					&& !str.toLowerCase().contains("picarro"));
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

		public boolean isCancelled() {
			return cancelled;
		}

		public void setCancelled(boolean cancelled) {
			this.cancelled = cancelled;
		}
	}

	public String grabLogs() {
		this.logger.flushLogs();
		return this.logger.getLogs();
	}

	public void startLogging(AppiumDriver<?> appiumDriver) {
		loggingExecutor = Executors.newSingleThreadExecutor();
		this.logger = new LogcatLog(appiumDriver);
		loggingExecutor.execute(this.logger);
	}

	public void stopLogging() {
		this.logger.setCancelled(true);
		close();
	}

	private void close() {
		loggingExecutor.shutdownNow();
	}
}