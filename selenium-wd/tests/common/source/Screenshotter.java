package common.source;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;

public class Screenshotter {
	public static String captureWebDriverScreenshot(WebDriver driver) throws IOException {
		return captureCroppedWebDriverScreenshot(driver, null, null);
	}

	public static String captureWebDriverScreenshot(WebDriver driver, String screenshotName) throws IOException {
		return captureCroppedWebDriverScreenshot(driver, screenshotName, null);
	}

	public static String captureCroppedWebDriverScreenshot(WebDriver driver, Rectangle rect) throws IOException {
		return captureCroppedWebDriverScreenshot(driver, null /*screenshotName*/, rect);
	}

	public static String captureCroppedWebDriverScreenshot(WebDriver driver, String screenshotName, Rectangle rect) throws IOException {
		String folderPath = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "data" + File.separator + "test-data" + File.separator + "screenshots";
		if (screenshotName == null) {
			screenshotName = "Test";
		}

		String filename = screenshotName + "_" + TestSetup.getUUIDString() + ".png";
		String captureFilePath = Paths.get(folderPath, filename).toString();
		ScreenShotOnFailure.captureWebDriverScreenShot(driver, captureFilePath, rect);
		return captureFilePath;
	}
}