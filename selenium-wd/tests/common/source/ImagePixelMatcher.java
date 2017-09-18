package common.source;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

import common.source.ImagingUtility.ARGBPixel;

public class ImagePixelMatcher {
	public static ImagePixelMatcher defaultMatcher() {
		return new ImagePixelMatcher();
	}

	public boolean matches(String imageFile1, String imageFile2, Rectangle image1Region, Rectangle image2Region) throws IOException {
		Log.method("matches", imageFile1, imageFile2, image1Region, image2Region);

		ARGBPixel[] argbPixels1 = ImagingUtility.imageToARGBArray(imageFile1, image1Region);
		ARGBPixel[] argbPixels2 = ImagingUtility.imageToARGBArray(imageFile2, image2Region);

		Log.info(String.format("Image1 - '%s' : Pixel region array length = %s", imageFile1, String.valueOf(argbPixels1.length)));
		Log.info(String.format("Image2 - '%s' : Pixel region array length = %s", imageFile2, String.valueOf(argbPixels2.length)));

		return Arrays.equals(argbPixels1, argbPixels2);
	}

	public boolean matches(BufferedImage bufferedImage1, BufferedImage bufferedImage2, Rectangle image1Region, Rectangle image2Region) throws IOException {
		Log.method("matches", bufferedImage1, bufferedImage2, image1Region, image2Region);

		ARGBPixel[] argbPixels1 = ImagingUtility.imageToARGBArray(bufferedImage1, image1Region);
		ARGBPixel[] argbPixels2 = ImagingUtility.imageToARGBArray(bufferedImage2, image2Region);

		Log.info(String.format("Image1 : Pixel region array length = %s", String.valueOf(argbPixels1.length)));
		Log.info(String.format("Image2 : Pixel region array length = %s", String.valueOf(argbPixels2.length)));

		return Arrays.equals(argbPixels1, argbPixels2);
	}
}
