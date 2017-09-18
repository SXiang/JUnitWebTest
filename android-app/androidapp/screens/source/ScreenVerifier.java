package androidapp.screens.source;

import static org.junit.Assert.assertTrue;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.sikuli.api.DefaultScreenRegion;
import org.sikuli.api.ScreenRegion;

import common.source.ExceptionUtility;
import common.source.FileUtility;
import common.source.ImagePixelMatcher;
import common.source.ImagingUtility;
import common.source.Log;
import common.source.LogHelper;
import common.source.NetworkEmulation;
import common.source.NetworkEmulation.NetworkDelay;
import common.source.NetworkEmulation.NetworkSpeed;
import common.source.Screenshotter;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.TextUtility;

public class ScreenVerifier {

	private ScreenVerifier() {}

	public static ScreenVerifier newDefaultVerifier() {
		return createInstance();
	}

	public static ScreenVerifier newVerifierWithPixelMatch() {
		return createInstance().setPixelMatch(true);
	}

	private static ScreenVerifier createInstance() {
		ScreenVerifier screenVerifier = new ScreenVerifier();
		NetworkEmulation networkEmulation = (NetworkEmulation)TestContext.INSTANCE.getTestSetup().getNetworkEmulation();
		if (networkEmulation != null) {
			if (!networkEmulation.getNetworkDelay().equals(NetworkDelay.HSDPA) && !networkEmulation.getNetworkDelay().equals(NetworkDelay.EVDO)
					&& !networkEmulation.getNetworkDelay().equals(NetworkDelay.LTE) && !networkEmulation.getNetworkDelay().equals(NetworkDelay.NONE)) {
				screenVerifier.setTurnedOff(true);
			}

			if (!networkEmulation.getNetworkSpeed().equals(NetworkSpeed.HSDPA) && !networkEmulation.getNetworkSpeed().equals(NetworkSpeed.EVDO)
					&& !networkEmulation.getNetworkSpeed().equals(NetworkSpeed.LTE) && !networkEmulation.getNetworkSpeed().equals(NetworkSpeed.FULL)) {
				screenVerifier.setTurnedOff(true);
			}
		}

		return screenVerifier;
	}

	private boolean pixelMatch = false;
	public boolean isPixelMatchEnabled() {
		return pixelMatch;
	}

	public ScreenVerifier setPixelMatch(boolean pixelMatch) {
		this.pixelMatch = pixelMatch;
		return this;
	}

	private boolean isTurnedOff = false;
	private boolean isTurnedOff() {
		return isTurnedOff;
	}

	private void setTurnedOff(boolean isTurnedOff) {
		this.isTurnedOff = isTurnedOff;
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

	@SuppressWarnings("null")
	public void assertAtleastOneImageFoundOnScreen(AndroidBaseScreen screen, List<String> imageFolderNames, List<String> imageFileNames, Integer attempts) {
		Log.method("assertAtleastOneImageFoundOnScreen", screen, LogHelper.listToString(imageFolderNames), LogHelper.listToString(imageFileNames), attempts);
		if (TestContext.INSTANCE.isRunningOnAndroidDevice()) {
			Log.info("To be implemented. Currently image verification is supported only in Emulator runs. Skipping verification...");
			return;
		}

		if (isTurnedOff()) {
			Log.info("Screen verifier is turned OFF due to bandwidth restriction set in Network Emulation. Skipping verification...");
			return;
		}

		try {
			boolean found = false;
			ScreenRegion screenRegion = null;
			int foundImgIdx = -1;
			for (int i = 0; i < imageFolderNames.size(); i++) {
				String imageFolderName = imageFolderNames.get(i);
				String imageFileName = imageFileNames.get(i);
				for (int j = 0; j < attempts; j++) {
					screenRegion = findMatchingRegionOnScreen(screen, imageFolderName, imageFileName);
					if (screenRegion != null) {
						found = true;
						foundImgIdx = i;
						break;
					}
				}
			}

			if (found && isPixelMatchEnabled()) {
				final String findImgFolderName = imageFolderNames.get(foundImgIdx);
				final String findImgFileName = imageFileNames.get(foundImgIdx);
				final File findImageFile = new File(TestSetup.getRootPath() + File.separator + "android-app" + File.separator + "data" + File.separator + "test-expected-data" +
						File.separator + "clips" + File.separator + findImgFolderName + File.separator + findImgFileName);

				Rectangle findImageBounds = ImagingUtility.getImageBounds(findImageFile);
				Rectangle screenImageBounds = screenRegion.getBounds();

				BufferedImage bufferedFindImage = ImagingUtility.imageToBufferedImage(findImageFile.getAbsolutePath(), BufferedImage.TYPE_4BYTE_ABGR);
				String fullScreenImage = Screenshotter.captureWebDriverScreenshot(screen.getAndroidDriver(), TextUtility.removeNonAsciiSpecialChars(findImgFolderName + "_" + findImgFileName));
				BufferedImage bufferedScreenImage = ImagingUtility.imageToBufferedImage(fullScreenImage, BufferedImage.TYPE_4BYTE_ABGR);
				FileUtility.deleteFile(Paths.get(fullScreenImage));

				boolean pixelMatch = ImagePixelMatcher.defaultMatcher().matches(bufferedScreenImage, bufferedFindImage, screenImageBounds, findImageBounds);
				if (!pixelMatch) {
					Log.warn("PIXEL MATCH FAILURE - Matching image found on screen. However pixel match failed!");
				}

				found = pixelMatch;
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
		if (isTurnedOff()) {
			Log.info("Screen verifier is turned OFF due to bandwidth restriction set in Network Emulation. Skipping verification...");
			return true;
		}

		ScreenRegion screenRegion = findMatchingRegionOnScreen(screen, imageFolderName, imageFileName);
		return screenRegion != null;
	}

	private ScreenRegion findMatchingRegionOnScreen(AndroidBaseScreen screen, String imageFolderName, String imageFileName) throws IOException {
		Log.method("findMatchingRegionOnScreen", screen, imageFolderName, imageFileName);
		ScreenRegion screenRegion = getMatchingRegionOnScreen(screen, imageFolderName, imageFileName);
		if (screenRegion != null) {
			Rectangle imageBounds = screenRegion.getBounds();
			Log.info(String.format("Found image on screen at bounds -> [x=%,.2f, y=%,.2f, width=%,.2f, height=%,.2f]",
					imageBounds.getX(), imageBounds.getY(), imageBounds.getWidth(), imageBounds.getHeight()));
		}

		return screenRegion;
	}

	private ScreenRegion getMatchingRegionOnScreen(AndroidBaseScreen screen, String imageFolderName, String imageFileName)
			throws IOException {
		final File imageFile = new File(TestSetup.getRootPath() + File.separator + "android-app" + File.separator + "data" + File.separator + "test-expected-data" +
				File.separator + "clips" + File.separator + imageFolderName + File.separator + imageFileName);
		final Integer waitTimeInSecs = 30;
		Log.info(String.format("Detecting presence of image-'%s' on screen. Max wait time = %d secs.", imageFile, waitTimeInSecs));
		return screen.getSikuliDriver().getMatchingRegion(imageFile, waitTimeInSecs);
	}

	private String getImageNotFoundLog(List<String> imageFolderNames, List<String> imageFileNames) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < imageFolderNames.size(); i++) {
			builder.append(String.format("Image-[%s/%s] was NOT found on screen;", imageFolderNames.get(i), imageFileNames.get(i)));
		}

		return builder.toString();
	}

	private String logScreenShot(AndroidBaseScreen screen, List<String> imageFolderNames, List<String> imageFileNames) {
		Log.info(getImageNotFoundLog(imageFolderNames, imageFileNames));
		try {
			String imageFolderName = imageFolderNames.get(0);
			String imageFileName = imageFileNames.get(0);
			return Screenshotter.captureWebDriverScreenshot(screen.getAndroidDriver(), TextUtility.removeNonAsciiSpecialChars(imageFolderName + "_" + imageFileName));
		} catch (IOException ex) {
			Log.error(ExceptionUtility.getStackTraceString(ex));
		}

		return null;
	}
}