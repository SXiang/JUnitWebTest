package androidapp.screens.source;

import static org.junit.Assert.assertTrue;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import common.source.ExceptionUtility;
import common.source.Log;
import common.source.LogHelper;
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
		assertImageFoundOnScreen(screen, imageFolderName, imageFileName, 1 /*attempts*/);
	}

	public void assertImageFoundOnScreen(AndroidBaseScreen screen, String imageFolderName, String imageFileName, Integer attempts) {
		Log.method("assertImageFoundOnScreen", screen, imageFolderName, imageFileName, attempts);
		assertAtleastOneImageFoundOnScreen(screen, Arrays.asList(imageFolderName), Arrays.asList(imageFileName), attempts);
	}

	public void assertAtleastOneImageFoundOnScreen(AndroidBaseScreen screen, List<String> imageFolderNames, List<String> imageFileNames) {
		Log.method("assertAtleastOneImageFoundOnScreen", screen, LogHelper.listToString(imageFolderNames), LogHelper.listToString(imageFileNames));
		assertAtleastOneImageFoundOnScreen(screen, imageFolderNames, imageFileNames, 1 /*attempts*/);
	}

	public void assertAtleastOneImageFoundOnScreen(AndroidBaseScreen screen, List<String> imageFolderNames, List<String> imageFileNames, Integer attempts) {
		Log.method("assertAtleastOneImageFoundOnScreen", screen, LogHelper.listToString(imageFolderNames), LogHelper.listToString(imageFileNames), attempts);
		try {
			boolean found = false;
			for (int i = 0; i < imageFolderNames.size(); i++) {
				String imageFolderName = imageFolderNames.get(i);
				String imageFileName = imageFileNames.get(i);
				for (int j = 0; i < attempts; j++) {
					found = verifyImageFoundOnScreen(screen, imageFolderName, imageFileName);
					if (found) {
						break;
					}
				}
			}

			if (!found) {
				logScreenShot(screen, imageFolderNames, imageFileNames);
			}

			assertTrue(getImageNotFoundLog(imageFolderNames, imageFileNames), found);
		} catch (IOException ex) {
			Log.error(ExceptionUtility.getStackTraceString(ex));
			logScreenShot(screen, imageFolderNames, imageFileNames);
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

	private String getImageNotFoundLog(List<String> imageFolderNames, List<String> imageFileNames) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < imageFolderNames.size(); i++) {
			builder.append(String.format("Image-[%s/%s] was NOT found on screen;", imageFolderNames.get(i), imageFileNames.get(i)));
		}

		return builder.toString();
	}

	private void logScreenShot(AndroidBaseScreen screen, List<String> imageFolderNames, List<String> imageFileNames) {
		Log.info(getImageNotFoundLog(imageFolderNames, imageFileNames));
		try {
			String imageFolderName = imageFolderNames.get(0);
			String imageFileName = imageFileNames.get(0);
			Screenshotter.captureWebDriverScreenshot(screen.getAndroidDriver(), TextUtility.removeNonAsciiSpecialChars(imageFolderName + "_" + imageFileName));
		} catch (IOException ex) {
			Log.error(ExceptionUtility.getStackTraceString(ex));
		}
	}
}