package androidapp.screens.source;

import static org.junit.Assert.assertTrue;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import common.source.ExceptionUtility;
import common.source.Log;
import common.source.Screenshotter;
import common.source.TestSetup;
import common.source.TextUtility;

public class ScreenVerifier {

	private ScreenVerifier() {}

	public static ScreenVerifier getInstance() {
		return new ScreenVerifier();
	}

	public void assertImageFoundOnScreen(AndroidBaseScreen screen, String imageFolderName, String imageFileName) {
		Log.method("assertImageFoundOnScreen", screen, imageFolderName, imageFileName);
		try {
			boolean found = verifyImageFoundOnScreen(screen, imageFolderName, imageFileName);
			if (!found) {
				logScreenShot(screen, imageFolderName, imageFileName);
			}

			assertTrue(String.format("Image-[%s/%s] was NOT found on screen", imageFolderName, imageFileName), found);
		} catch (IOException ex) {
			Log.error(ExceptionUtility.getStackTraceString(ex));
			logScreenShot(screen, imageFolderName, imageFileName);
		}
	}

	public boolean verifyImageFoundOnScreen(AndroidBaseScreen screen, String imageFolderName, String imageFileName) throws IOException {
		Log.method("verifyImageFoundOnScreen", screen, imageFolderName, imageFileName);
		final File imageFile = new File(TestSetup.getRootPath() + File.separator + "android-app" + File.separator + "data" + File.separator + "test-expected-data" +
				File.separator + "clips" + File.separator + imageFolderName + File.separator + imageFileName);
		final Integer waitTimeInSecs = 30;
		Log.info(String.format("Detecting presence of image-'%s' on screen. Max wait time = %d secs.", imageFile, waitTimeInSecs));
		Rectangle imageBounds = screen.getSikuliDriver().getImageBounds(imageFile, waitTimeInSecs);
		if (imageBounds != null) {
			Log.info(String.format("Found image on screen at bounds -> [x=%,.2f, y=%,.2f, width=%,.2f, height=%,.2f]",
					imageBounds.getX(), imageBounds.getY(), imageBounds.getWidth(), imageBounds.getHeight()));
			return true;
		}

		return false;
	}

	private void logScreenShot(AndroidBaseScreen screen, String imageFolderName, String imageFileName) {
		Log.info(String.format("Image-[%s/%s] was NOT found on screen. Capturing current display screenshot...", imageFolderName, imageFileName));
		try {
			Screenshotter.captureWebDriverScreenshot(screen.getAndroidDriver(), TextUtility.removeNonAsciiSpecialChars(imageFolderName + "_" + imageFileName));
		} catch (IOException ex) {
			Log.error(ExceptionUtility.getStackTraceString(ex));
		}
	}
}