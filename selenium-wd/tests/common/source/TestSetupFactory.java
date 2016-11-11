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
		ScreenShotOnFailure screenshotOnFailure = null;
		Lock lock = new ReentrantLock();
		try {
			lock.lock();
			String screenshotsDir = TestSetup.getExecutionPath() + ExtentReportGenerator.reportDir + getTestSetup().getTestReportCategory();
			Path screenShotsPath = Paths.get(screenshotsDir, screenShotsSubFolder);
			FileUtility.createDirectoryIfNotExists(screenShotsPath.toString());
			screenshotOnFailure = new ScreenShotOnFailure(screenShotsSubFolder, screenshotsDir, getTestSetup().isRemoteBrowser()); 
		} finally {
			lock.unlock();
		}
		
		return screenshotOnFailure;
	}
	
	public static ScreenShotOnFailure getScreenShotOnFailure() {
		return threadLocalScreenShotOnFailure.get();
	}
}