package common.source;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

import common.source.ImagingUtility.ARGBPixel;

public class ImagePixelMatcher {
	private Float tolerance = 0.0f;
	private Integer allowedPixelDelta = 0;

	public static ImagePixelMatcher defaultMatcher() {
		return new ImagePixelMatcher();
	}

	public static ImagePixelMatcher matcherWithTolerance(Float tolerance, Integer allowedPixelDelta) {
		return new ImagePixelMatcher().setTolerance(tolerance).setAllowedPixelDelta(allowedPixelDelta);
	}

	public boolean matches(String imageFile1, String imageFile2, Rectangle image1Region, Rectangle image2Region) throws IOException {
		Log.method("matches", imageFile1, imageFile2, image1Region, image2Region);

		ARGBPixel[] argbPixels1 = ImagingUtility.imageToARGBArray(imageFile1, image1Region);
		ARGBPixel[] argbPixels2 = ImagingUtility.imageToARGBArray(imageFile2, image2Region);

		Log.info(String.format("Image1 - '%s' : Pixel region array length = %s", imageFile1, String.valueOf(argbPixels1.length)));
		Log.info(String.format("Image2 - '%s' : Pixel region array length = %s", imageFile2, String.valueOf(argbPixels2.length)));

		return matchPixels(argbPixels1, argbPixels2);
	}

	public boolean matches(BufferedImage bufferedImage1, BufferedImage bufferedImage2, Rectangle image1Region, Rectangle image2Region) throws IOException {
		Log.method("matches", bufferedImage1, bufferedImage2, image1Region, image2Region);

		ARGBPixel[] argbPixels1 = ImagingUtility.imageToARGBArray(bufferedImage1, image1Region);
		ARGBPixel[] argbPixels2 = ImagingUtility.imageToARGBArray(bufferedImage2, image2Region);

		Log.info(String.format("Image1 : Pixel region array length = %s", String.valueOf(argbPixels1.length)));
		Log.info(String.format("Image2 : Pixel region array length = %s", String.valueOf(argbPixels2.length)));

		return matchPixels(argbPixels1, argbPixels2);
	}

	public Float getTolerance() {
		return tolerance;
	}

	public ImagePixelMatcher setTolerance(Float tolerance) {
		this.tolerance = tolerance;
		return this;
	}

	public Integer getAllowedPixelDelta() {
		return allowedPixelDelta;
	}

	public ImagePixelMatcher setAllowedPixelDelta(Integer allowedPixelDelta) {
		this.allowedPixelDelta = allowedPixelDelta;
		return this;
	}

	private boolean matchPixels(ARGBPixel[] argbPixels1, ARGBPixel[] argbPixels2) {
		Log.method("matchPixels", argbPixels1, argbPixels2);
		if (argbPixels1 == null || argbPixels2 == null) {
			throw new IllegalArgumentException("'argbPixels1' and 'argbPixels2' should NOT be null.");
		}

		if (getTolerance() == 0.0f) {
			return Arrays.equals(argbPixels1, argbPixels2);
		}

		if (argbPixels1.length != argbPixels2.length) {
			Log.error(String.format("Pixel array lengths do NOT match. Pixel Array1 length=%d. Pixel Array2 length=%d.",
					argbPixels1.length, argbPixels2.length));
			return false;
		}

		Float percDiff = computePercentDiff(argbPixels1, argbPixels2);
		Boolean match = (percDiff <= getTolerance());
		if (!match) {
			Log.error(String.format("Percent diff - [%.4f] is greater than specified tolerance limit of - [%.4f]",
					percDiff, getTolerance()));
		} else {
			Log.info(String.format("Pixel percent diff is within the allowed tolerance of [%.4f]. Percent diff = [%.4f]",
				getTolerance(), percDiff));
		}

		return match;
	}

	private Float computePercentDiff(ARGBPixel[] argbPixels1, ARGBPixel[] argbPixels2) {
		Integer matchingPx = 0;
		for (int i = 0; i < argbPixels1.length; i++) {
			if (argbPixels1[i].equalsWithPixelDelta(argbPixels2[i], this.getAllowedPixelDelta())) {
				matchingPx++;
			}
		}

		return 100.0f - ((matchingPx * 100.0f)/argbPixels1.length);
	}
}
