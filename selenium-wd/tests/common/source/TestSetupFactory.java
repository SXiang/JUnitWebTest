package common.source;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.relevantcodes.extentreports.ExtentTest;

public class TestSetupFactory {
	public static final String screenShotsSubFolder = "screenshots/";

	public static TestSetup getTestSetup() {
		return threadLocalTestSetup.get();
	}

	public static Map<String, ExtentTest> getExtentTestMap() {
		return threadLocalExtentTestMap.get();
	}

	public static Map<String, Object> getTestMap() {
		return threadLocalTestMap.get();
	}

	private static ThreadLocal<TestSetup> threadLocalTestSetup = new ThreadLocal<TestSetup>() {
	    @Override
	    protected TestSetup initialValue() {
	    	return createDefaultTestSetup();
	    }
	};

	private static ThreadLocal<Map<String, ExtentTest>> threadLocalExtentTestMap = new ThreadLocal<Map<String, ExtentTest>>() {
	    @Override
	    protected Map<String, ExtentTest> initialValue() {
	    	return createDefaultExtentTestMap();
	    }
	};

	private static ThreadLocal<Map<String,Object>> threadLocalTestMap = new ThreadLocal<Map<String,Object>>() {
	    @Override
	    protected Map<String,Object> initialValue() {
	    	return createDefaultTestMap();
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

	private static TestSetup createDefaultTestSetup() {
		return new TestSetup();
	}

	private static Map<String, ExtentTest> createDefaultExtentTestMap() {
		return Collections.synchronizedMap(new HashMap<String, ExtentTest>());
	}

	private static Map<String, Object> createDefaultTestMap() {
		return Collections.synchronizedMap(new HashMap<String, Object>());
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

	public static ScreenShotOnFailure getScreenShotOnFailure() {
		return threadLocalScreenShotOnFailure.get();
	}
}