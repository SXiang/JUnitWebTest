package unittest.source;

import static org.junit.Assert.*;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.sikuli.api.Screen;
import common.source.ExceptionUtility;
import common.source.Log;
import common.source.SikuliDecoratedDriver;

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

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void sikuliDriverGetBoundsTest_Success() throws Exception {
		Log.info("Executing test -> sikuliDriverGetBoundsTest() ...");

		// todo: create paths using base path
		String INPUT_IMAGE = "C:\\Repositories\\surveyor-qa\\android-app\\data\\test-expected-data\\unit-tests\\sikuli-tests\\full-screen-investigated-lisa2.png";
		String findImageFile = "C:\\Repositories\\surveyor-qa\\android-app\\data\\test-expected-data\\unit-tests\\sikuli-tests\\find-image-investigated-lisa2.png";
		SikuliDecoratedDriver sikuliDriver = SikuliDecoratedDriver.getInstance(null, createImageScreen(INPUT_IMAGE));
		sikuliDriver.setSimilarityScore(1.0);
		Rectangle imageBounds = sikuliDriver.getImageBounds(new File(findImageFile), 30 /*waitTimeInSeconds*/);
		if (imageBounds != null) {
			Log.info(String.format("Found image on screen at bounds -> [x=%,.2f, y=%,.2f, width=%,.2f, height=%,.2f]",
					imageBounds.getX(), imageBounds.getY(), imageBounds.getWidth(), imageBounds.getHeight()));
		}

		assertTrue(String.format("Searched image - '%s' was NOT found.", findImageFile), imageBounds != null);
	}

	@Test
	public void sikuliDriverGetBoundsTest_Failure() throws Exception {
		Log.info("Executing test -> sikuliDriverGetBoundsTest_Failure() ...");

		// todo: create paths using base path
		String INPUT_IMAGE = "C:\\Repositories\\surveyor-qa\\android-app\\data\\test-expected-data\\unit-tests\\sikuli-tests\\full-screen-investigated-lisa2.png";
		String findImageFile = "C:\\Repositories\\surveyor-qa\\android-app\\data\\test-expected-data\\unit-tests\\sikuli-tests\\find-image-investigated-lisa6.png";

		SikuliDecoratedDriver sikuliDriver = SikuliDecoratedDriver.getInstance(null, createImageScreen(INPUT_IMAGE));
		sikuliDriver.setSimilarityScore(1.0);
		Rectangle imageBounds = sikuliDriver.getImageBounds(new File(findImageFile), 30 /*waitTimeInSeconds*/);
		if (imageBounds != null) {
			Log.info(String.format("Found image on screen at bounds -> [x=%,.2f, y=%,.2f, width=%,.2f, height=%,.2f]",
					imageBounds.getX(), imageBounds.getY(), imageBounds.getWidth(), imageBounds.getHeight()));
		}

		assertTrue(String.format("Searched image - '%s' was NOT found.", findImageFile), imageBounds == null);
	}
}
