package common.source;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.relevantcodes.extentreports.ExtentTest;

public class ThreadLocalStore {
	public static final String screenShotsSubFolder = "screenshots/";

	private static ThreadLocal<Logger> threadLocalLog = new ThreadLocal<Logger>() {
	    @Override
	    protected Logger initialValue() {
	    	return createDefaultLog();
	    }
	};

	private static ThreadLocal<Logger> threadLocalStashLog = new ThreadLocal<Logger>() {
	    @Override
	    protected Logger initialValue() {
	    	return createDefaultStashLog();
	    }
	};

	private static ThreadLocal<LogData> threadLocalLogData = new ThreadLocal<LogData>() {
	    @Override
	    protected LogData initialValue() {
	    	return createDefaultLogData();
	    }
	};

	private static ThreadLocal<Map<String, ExtentTest>> threadLocalExtentTestMap = new ThreadLocal<Map<String, ExtentTest>>() {
	    @Override
	    protected Map<String, ExtentTest> initialValue() {
	    	return createDefaultExtentTestMap();
	    }
	};

	private static ThreadLocal<TestSetup> threadLocalTestSetup = new ThreadLocal<TestSetup>() {
	    @Override
	    protected TestSetup initialValue() {
	    	return createDefaultTestSetup();
	    }
	};

	private static ThreadLocal<ScreenShotOnFailure> threadLocalScreenShotOnFailure = new ThreadLocal<ScreenShotOnFailure>() {
	    @Override
	    protected ScreenShotOnFailure initialValue() {
	    	try {
				return createDefaultScreenShotOnFailure();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	return null;
	    }
	};

	public static Logger getLog() {
		return threadLocalLog.get();
	}

	public static Logger getStashLog() {
		return threadLocalStashLog.get();
	}

	public static LogData getLogData() {
		return threadLocalLogData.get();
	}

	public static Map<String, ExtentTest> getExtentTestMap() {
		return threadLocalExtentTestMap.get();
	}

	public static TestSetup getTestSetup() {
		return threadLocalTestSetup.get();
	}

	public static ScreenShotOnFailure getScreenShotOnFailure() {
		return threadLocalScreenShotOnFailure.get();
	}

	private static Map<String, ExtentTest> createDefaultExtentTestMap() {
		return Collections.synchronizedMap(new HashMap<String, ExtentTest>());
	}

	private static Logger createDefaultLog() {
		TestContext.INSTANCE.getTestSetup();
		try {
			System.setProperty("log4j.configurationFile", TestSetup.getExecutionPath(TestSetup.getRootPath()) + "log4j2" + File.separator + "log4j2.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return LogManager.getLogger(Log.class.getName());
	}

	private static Logger createDefaultStashLog() {
		TestContext.INSTANCE.getTestSetup();
		try {
			System.setProperty("log4j.configurationFile", TestSetup.getExecutionPath(TestSetup.getRootPath()) + "log4j2" + File.separator + "log4j2.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return LogManager.getLogger("logstash.json");
	}

	private static LogData createDefaultLogData() {
		return new LogData();
	}

	private static TestSetup createDefaultTestSetup() {
		return new TestSetup();
	}

	private static ScreenShotOnFailure createDefaultScreenShotOnFailure() throws IOException {
		ScreenShotOnFailure screenshotOnFailure = null;
		Lock lock = new ReentrantLock();
		try {
			lock.lock();
			String screenshotsDir = TestSetup.getExecutionPath() + ExtentReportGenerator.reportDir + getTestSetup().getTestReportCategory();
			Path screenShotsPath = Paths.get(screenshotsDir, screenShotsSubFolder);
			FileUtility.createDirectoryIfNotExists(screenShotsPath.toString());
			screenshotOnFailure = new ScreenShotOnFailure(screenShotsSubFolder, screenshotsDir,
					getTestSetup().isRemoteBrowser() || TestSetup.isParallelBuildEnabled());
		} finally {
			lock.unlock();
		}

		return screenshotOnFailure;
	}
}