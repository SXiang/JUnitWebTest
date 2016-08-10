package common.source;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestSetupFactory {
	public static final String screenShotsSubFolder = "screenshots/";
	
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
	
	private static TestSetup createDefaultTestSetup() {
		return new TestSetup();
	}
	
	public static TestSetup getTestSetup() {
		return threadLocalTestSetup.get();
	}

	private static ScreenShotOnFailure createDefaultScreenShotOnFailure() throws IOException {
		String screenshotsDir = TestSetup.getExecutionPath() + TestSetup.reportDir + getTestSetup().getTestReportCategory();
		Path screenShotsPath = Paths.get(screenshotsDir, screenShotsSubFolder);
		
		Lock lock = new ReentrantLock();
		try {
			lock.lock();
			FileUtility.createDirectoryIfNotExists(screenShotsPath.toString());
		} finally {
			lock.unlock();
		}
		
		return new ScreenShotOnFailure(screenShotsSubFolder, screenshotsDir, getTestSetup().isRemoteBrowser());
	}
	
	public static ScreenShotOnFailure getScreenShotOnFailure() {
		return threadLocalScreenShotOnFailure.get();
	}
}
