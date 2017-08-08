package common.source;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;

public class Screenshotter {
	public static void captureWebDriverScreenshot(WebDriver driver) throws IOException {
		captureCroppedWebDriverScreenshot(driver, null, null);
	}

	public static void captureWebDriverScreenshot(WebDriver driver, String screenshotName) throws IOException {
		captureCroppedWebDriverScreenshot(driver, screenshotName, null);
	}

	public static void captureCroppedWebDriverScreenshot(WebDriver driver, Rectangle rect) throws IOException {
		captureCroppedWebDriverScreenshot(driver, null /*screenshotName*/, rect);
	}

	public static void captureCroppedWebDriverScreenshot(WebDriver driver, String screenshotName, Rectangle rect) throws IOException {
		String folderPath = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "data" + File.separator + "test-data" + File.separator + "screenshots";
		if (screenshotName == null) {
			screenshotName = "Test";
		}

		String filename = screenshotName + "_" + TestSetup.getUUIDString() + ".png";
		ScreenShotOnFailure.captureWebDriverScreenShot(driver, Paths.get(folderPath, filename).toString(), rect);
	}
}