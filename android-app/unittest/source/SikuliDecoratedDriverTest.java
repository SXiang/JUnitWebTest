package unittest.source;

import static org.junit.Assert.*;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.sikuli.api.Screen;
import common.source.ExceptionUtility;
import common.source.ImagePixelMatcher;
import common.source.ImagingUtility;
import common.source.Log;
import common.source.SikuliDecoratedDriver;
import common.source.TestSetup;

public class SikuliDecoratedDriverTest {
	private static class StaticImageScreen implements Screen {
		private BufferedImage image;
		private Dimension size;

		public StaticImageScreen(BufferedImage image) {
			this.image = image;
			this.size = new Dimension(image.getWidth(), image.getHeight());
		}

		/* Sikuli screen overrides */
		@Override
		public BufferedImage getScreenshot(int x, int y, int width, int height) {
			try {
				return crop(image, x, y, width, height);
			} catch (Exception ex) {
				Log.error(String.format("Error while taking StaticImageScreen screenshot. Exception -> %s", ExceptionUtility.getStackTraceString(ex)));
			}

			return null;
		}

		@Override
		public java.awt.Dimension getSize() {
			java.awt.Dimension dimension = new java.awt.Dimension();
			dimension.setSize(size.getWidth(), size.getHeight());
			return dimension;
		}

	    private BufferedImage crop(BufferedImage src, int x, int y, int width, int height) {
	        BufferedImage outImg = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
	        Graphics graphics = outImg.getGraphics();
	        graphics.drawImage(src, 0, 0, width, height, x, y, x + width, y + height, null);
	        graphics.dispose();
	        return outImg;
	    }
	}

	private static String rootPath;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		rootPath = TestSetup.getRootPath();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void beforeTest() throws Exception {
	}

	private StaticImageScreen createImageScreen(String imageFilePath) throws IOException {
		File input = new File(imageFilePath);
		BufferedImage image = ImageIO.read(input);
		StaticImageScreen imageScreen = new StaticImageScreen(image);
		return imageScreen;
	}

	@Test
	public void sikuliDriverGetBoundsTest_SameImageSameBackground_TopOffsetColorMatch_Success() throws Exception {
		Log.info("Executing test -> sikuliDriverGetBoundsTest_SameImageSameBackground_TopOffsetColorMatch_Success() ...");

		String INPUT_IMAGE = Paths.get(rootPath, "android-app\\data\\test-expected-data\\unit-tests\\sikuli-tests\\full-screen-investigated-lisa2.png").toString();
		String findImageFile = Paths.get(rootPath, "android-app\\data\\test-expected-data\\unit-tests\\sikuli-tests\\find-image-investigated-lisa2-same-color.png").toString();
		SikuliDecoratedDriver sikuliDriver = SikuliDecoratedDriver.getInstance(null, createImageScreen(INPUT_IMAGE));
		sikuliDriver.setSimilarityScore(1.0);
		Rectangle imageBounds = sikuliDriver.getImageBounds(new File(findImageFile), 30 /*waitTimeInSeconds*/);
		if (imageBounds != null) {
			Log.info(String.format("Found image on screen at bounds -> [x=%,.2f, y=%,.2f, width=%,.2f, height=%,.2f]",
					imageBounds.getX(), imageBounds.getY(), imageBounds.getWidth(), imageBounds.getHeight()));
		}

		assertTrue(String.format("Image Bounds is expected to be found for - '%s'.", findImageFile), imageBounds != null);
		Double x = imageBounds.getX(); Double y = imageBounds.getY();
		Rectangle imageTopOffsetBounds = new Rectangle(x.intValue(), y.intValue(), 5, 5);
		Rectangle findImageTopOffsetBounds = new Rectangle(0, 0, 5, 5);
		boolean colorMatch = ImagePixelMatcher.defaultMatcher().matches(INPUT_IMAGE, findImageFile, imageTopOffsetBounds, findImageTopOffsetBounds);
		assertTrue(String.format("Pixels expected to match for - '%s'.", findImageFile), colorMatch == true);
	}

	@Test
	public void sikuliDriverGetBoundsTest_SameImageSameBackground_FullImageColorMatch_Success() throws Exception {
		Log.info("Executing test -> sikuliDriverGetBoundsTest_SameImageSameBackground_FullImageColorMatch_Success() ...");

		String INPUT_IMAGE = Paths.get(rootPath, "android-app\\data\\test-expected-data\\unit-tests\\sikuli-tests\\full-screen-investigated-lisa2.png").toString();
		String findImageFile = Paths.get(rootPath, "android-app\\data\\test-expected-data\\unit-tests\\sikuli-tests\\find-image-investigated-lisa2-same-color.png").toString();
		SikuliDecoratedDriver sikuliDriver = SikuliDecoratedDriver.getInstance(null, createImageScreen(INPUT_IMAGE));
		sikuliDriver.setSimilarityScore(1.0);
		Rectangle imageBounds = sikuliDriver.getImageBounds(new File(findImageFile), 30 /*waitTimeInSeconds*/);
		if (imageBounds != null) {
			Log.info(String.format("Found image on screen at bounds -> [x=%,.2f, y=%,.2f, width=%,.2f, height=%,.2f]",
					imageBounds.getX(), imageBounds.getY(), imageBounds.getWidth(), imageBounds.getHeight()));
		}

		assertTrue(String.format("Image Bounds is expected to be found for - '%s'.", findImageFile), imageBounds != null);
		Rectangle findImageBounds = ImagingUtility.getImageBounds(findImageFile);
		boolean colorMatch = ImagePixelMatcher.defaultMatcher().matches(INPUT_IMAGE, findImageFile, imageBounds, findImageBounds);
		assertTrue(String.format("Pixels expected to match for - '%s'.", findImageFile), colorMatch == true);
	}

	@Test
	public void sikuliDriverGetBoundsTest_SameImageDiffBackground_Failure() throws Exception {
		Log.info("Executing test -> sikuliDriverGetBoundsTest_SameImageDiffBackground_Failure() ...");

		String INPUT_IMAGE = Paths.get(rootPath, "android-app\\data\\test-expected-data\\unit-tests\\sikuli-tests\\full-screen-investigated-lisa2.png").toString();
		String findImageFile = Paths.get(rootPath, "android-app\\data\\test-expected-data\\unit-tests\\sikuli-tests\\find-image-investigated-lisa2-color-red.png").toString();
		SikuliDecoratedDriver sikuliDriver = SikuliDecoratedDriver.getInstance(null, createImageScreen(INPUT_IMAGE));
		sikuliDriver.setSimilarityScore(1.0);
		Rectangle imageBounds = sikuliDriver.getImageBounds(new File(findImageFile), 30 /*waitTimeInSeconds*/);
		if (imageBounds != null) {
			Log.info(String.format("Found image on screen at bounds -> [x=%,.2f, y=%,.2f, width=%,.2f, height=%,.2f]",
					imageBounds.getX(), imageBounds.getY(), imageBounds.getWidth(), imageBounds.getHeight()));
		}

		assertTrue(String.format("Image Bounds is expected to be found for - '%s' although backgrounds don't match.", findImageFile), imageBounds != null);
		Rectangle findImageBounds = ImagingUtility.getImageBounds(findImageFile);
		boolean colorMatch = ImagePixelMatcher.defaultMatcher().matches(INPUT_IMAGE, findImageFile, imageBounds, findImageBounds);
		assertTrue(String.format("Colors NOT expected to match for - '%s'.", findImageFile), colorMatch == false);
	}

	@Test
	public void sikuliDriverGetBoundsTest_DifferentImage_Failure() throws Exception {
		Log.info("Executing test -> sikuliDriverGetBoundsTest_DifferentImage_Failure() ...");

		String INPUT_IMAGE = Paths.get(rootPath, "android-app\\data\\test-expected-data\\unit-tests\\sikuli-tests\\full-screen-investigated-lisa2.png").toString();
		String findImageFile = Paths.get(rootPath, "android-app\\data\\test-expected-data\\unit-tests\\sikuli-tests\\find-image-investigated-lisa6.png").toString();
		SikuliDecoratedDriver sikuliDriver = SikuliDecoratedDriver.getInstance(null, createImageScreen(INPUT_IMAGE));
		sikuliDriver.setSimilarityScore(1.0);
		Rectangle imageBounds = sikuliDriver.getImageBounds(new File(findImageFile), 30 /*waitTimeInSeconds*/);
		if (imageBounds != null) {
			Log.info(String.format("Found image on screen at bounds -> [x=%,.2f, y=%,.2f, width=%,.2f, height=%,.2f]",
					imageBounds.getX(), imageBounds.getY(), imageBounds.getWidth(), imageBounds.getHeight()));
		}

		assertTrue(String.format("Searched image - '%s' should NOT be found.", findImageFile), imageBounds == null);
	}
}
