/**
 *
 */
package common.source;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import net.avh4.util.imagecomparison.ImageComparison;
import net.avh4.util.imagecomparison.ImageComparisonResult;
import net.avh4.util.imagecomparison.NoReferenceImageResult;
import net.avh4.util.imagecomparison.PixelMismatchResult;
import net.avh4.util.imagecomparison.SizeMismatchResult;

public class ImagingUtility {

	private static final String NET_AVH4_UTIL_IMAGECOMPARISON_IMAGE_COMPARISON_SUCCESS = "net.avh4.util.imagecomparison.ImageComparisonSuccess";

	public static class ARGBPixel {
		private int red;
		private int green;
		private int blue;
		private int alpha;

		public ARGBPixel(int a, int r, int g, int b) {
			this.alpha = a;
			this.red = r;
			this.green = g;
			this.blue = b;
		}

		public int getAlpha() {
			return alpha;
		}
		public void setAlpha(int alpha) {
			this.alpha = alpha;
		}
		public int getRed() {
			return red;
		}
		public void setRed(int red) {
			this.red = red;
		}
		public int getGreen() {
			return green;
		}
		public void setGreen(int green) {
			this.green = green;
		}
		public int getBlue() {
			return blue;
		}
		public void setBlue(int blue) {
			this.blue = blue;
		}

	    @Override
	    public String toString() {
	        return ToStringBuilder.reflectionToString(this);
	    }

	    @Override
	    public int hashCode() {
	        return new HashCodeBuilder().append(alpha).append(red).append(green).append(blue).toHashCode();
	    }

		@Override
		public boolean equals(Object other) {
	        if (other == this) {
	            return true;
	        }
	        if ((other instanceof ARGBPixel) == false) {
	            return false;
	        }

	        ARGBPixel rhs = ((ARGBPixel) other);
	        return new EqualsBuilder().append(alpha, rhs.alpha).append(red, rhs.red).append(green,  rhs.green).append(blue, rhs.blue).isEquals();
		}

		public boolean equalsWithPixelDelta(ARGBPixel otherPixel, Integer pixelDelta) {
	        if (otherPixel == this) {
	            return true;
	        }

	        if ((Math.abs(this.getAlpha()-otherPixel.getAlpha())>pixelDelta) ||
	        		(Math.abs(this.getRed()-otherPixel.getRed())>pixelDelta) ||
	        		(Math.abs(this.getGreen()-otherPixel.getGreen())>pixelDelta) ||
	        		(Math.abs(this.getBlue()-otherPixel.getBlue())>pixelDelta)) {
	        	return false;
	        }

	        return true;
		}
	}

	public ImagingUtility() {
	}

	/*
	 * Compares the specified images and returns an ImageComparisonResult object.
	 */
	public static ImageComparisonResult compareImages(Image firstImage, Image secondImage) {
		return ImageComparison.compare(firstImage, secondImage);
	}

	/*
	 * Compares the specified image files and returns an ImageComparisonResult object.
	 */
	public static ImageComparisonResult compareImages(String firstImagePath, String secondImagePath) {
		BufferedImage imgFirst = null;
		BufferedImage imgSecond = null;
		try {
		    if (firstImagePath != null) {
		    	imgFirst = ImageIO.read(new File(firstImagePath));
		    }
		    if (secondImagePath != null) {
		    	imgSecond = ImageIO.read(new File(secondImagePath));
		    }
		} catch (IOException e) {
		}
		return ImageComparison.compare(imgFirst, imgSecond);
	}

	/*
	 * Gets bufferedImage from image.
	 */
	public static BufferedImage imageToBufferedImage(Image image) {
		return imageToBufferedImage(image, BufferedImage.TYPE_INT_RGB);
	}

	public static BufferedImage imageToBufferedImage(String imageFilePath, int type) {
		Image image = loadImage(imageFilePath);
		return imageToBufferedImage(image, type);
	}

	private static BufferedImage imageToBufferedImage(Image image, int type) {
		BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
		Graphics2D g2 = bi.createGraphics();
		g2.drawImage(image, null, null);
		return bi;
	}

	/*
	 * Gets pixel bytes from image.
	 */
	public static byte[] imageToBytes(Image image) {
		return imageToBytes(image, BufferedImage.TYPE_INT_ARGB);
	}

	public static byte[] imageToBytes(Image image, int type) {
		if (image != null) {
			BufferedImage bufferedImg = imageToBufferedImage(image, type);
			return bufferedImageToBytes(bufferedImg);
		}

		return null;
	}

	public static Image loadImage(String filename) {
		return loadImage(filename, new String[] {".png"});
	}

	private static Image loadImage(String filename, String[] supportedExts) {
		BufferedImage bi = null;
		boolean isSupported = (supportedExts == null);
		if (supportedExts != null) {
			isSupported = Arrays.asList(supportedExts).stream().anyMatch(e -> filename.endsWith(e));
		}

		if(isSupported){
			try{
				bi = ImageIO.read(new File(filename));
			}catch(java.io.IOException io){
				Log.warn("File Not Found");
			}
		}else{
			Log.warn("Image format is not supported: "+filename);
		}
		return bi;
	}

	private static byte[] bufferedImageToBytes(BufferedImage bufferedImg) {
		if (bufferedImg != null) {
			return ((DataBufferByte)bufferedImg.getRaster().getDataBuffer()).getData();
		}

		return null;
	}

	public static ARGBPixel[] imageToARGBArray(String imageFile, Rectangle regionOfInterest) throws IOException {
	    BufferedImage bufferedImg = imageToBufferedImage(loadImage(imageFile), BufferedImage.TYPE_4BYTE_ABGR);
	    return imageToARGBArray(bufferedImg, regionOfInterest);
	}

	public static ARGBPixel[] imageToARGBArray(BufferedImage bufferedImg, Rectangle regionOfInterest) {
		int imgWidth = bufferedImg.getWidth();
	    Double rStartX = regionOfInterest.getX();
	    Double rStartY = regionOfInterest.getY();
	    Double rEndX = rStartX + regionOfInterest.getWidth();
	    Double rEndY = rStartY + regionOfInterest.getHeight();
	    byte[] bytes = bufferedImageToBytes(bufferedImg);
		ARGBPixel[] argbArr = null;
		if (bytes != null && bytes.length > 0) {
			Double dLen = regionOfInterest.getWidth() * regionOfInterest.getHeight();
			argbArr = new ARGBPixel[dLen.intValue()];
			int x = 0; int y = 0; int j = 0;
			for (int i = 0; i < bytes.length; i+=4) {
				if (x >=rStartX.intValue() && x <rEndX.intValue() && y >=rStartY.intValue() && y<rEndY.intValue()) {
					int a = (bytes[i]) & 0xff;
					int r = (bytes[i+1]) & 0xff;
					int g = (bytes[i+2]) & 0xff;
					int b = (bytes[i+3]) & 0xff;
					if (j < argbArr.length) {
						argbArr[j] = new ARGBPixel(a, r, g, b);
					}

					j++;
				}

				x++;

				if (x % imgWidth == 0) {
					x = 0;
					y++;
				}
			}
		}

		return argbArr;
	}

	public static Rectangle getImageBounds(String imageFile) throws IOException {
		File file = new File(imageFile);
		return getImageBounds(file);
	}

	public static Rectangle getImageBounds(File file) throws IOException {
		BufferedImage image = ImageIO.read(file);
		return new Rectangle(0, 0, image.getWidth(), image.getHeight());
	}

	/**
	 * Executes the unit tests for the two		 compareImages() method overloads.
	 * @param args
	 */
	public static void main(String[] args) {
		Path screenshotDirectory;
		List<String> filesInDirectory = null;

		try {
			screenshotDirectory = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\test-data\\screenshots");
			filesInDirectory = FileUtility.getFilesInDirectory(screenshotDirectory);

			Log.info(screenshotDirectory.toString() + File.separator + "driver-view-01-different-size.png");

			String differentSizeFilePath = screenshotDirectory.toString() + File.separator + "driver-view-01-different-size.png";
		    BufferedImage differentSizeImage = ImageIO.read(new File(differentSizeFilePath));

			for (String filePath : filesInDirectory) {
				String duplicateFilePath = filePath;
				String copyFilePath = "copy-" + filePath;
				String modifiedFilePath = createModifiedFile(filePath);

			    BufferedImage actualImage = ImageIO.read(new File(filePath));
			    BufferedImage actualImageDuplicate = ImageIO.read(new File(duplicateFilePath));
			    BufferedImage actualImageModified = getModifiedImage(filePath);

		    	BufferedImage actualImageCopy = null;
		    	boolean copyFileExists = true;
		    	try {
		    		actualImageCopy = ImageIO.read(new File(copyFilePath));
				} catch (IOException e) {
					copyFileExists = false;
				}

			    // Tests for method overload1
		    	ImageUtility_compareImagesOverload1_identicalImages_shouldReturnSuccess(actualImage, actualImageDuplicate);
			    if (copyFileExists) {
			    	ImageUtility_compareImagesOverload1_imageCopy_shouldReturnSuccess(actualImage, actualImageCopy);
			    }
			    ImageUtility_compareImagesOverload1_withNoReferenceImage_shouldReturnNoReferenceImageFailure(actualImage);
			    ImageUtility_compareImagesOverload1_imagesWithDifferingPixels_shouldReturnPixelMismatch(actualImage, actualImageModified);
			    ImageUtility_compareImagesOverload1_imagesWithDifferentDimensions_shouldReturnSizeMismatch(actualImage, differentSizeImage);

			    // Tests for method overload2
		    	ImageUtility_compareImagesOverload2_identicalImages_shouldReturnSuccess(filePath, duplicateFilePath);
			    if (copyFileExists) {
			    	ImageUtility_compareImagesOverload2_imageCopy_shouldReturnSuccess(filePath, copyFilePath);
			    }
			    ImageUtility_compareImagesOverload2_withNoReferenceImage_shouldReturnNoReferenceImageFailure(filePath);
			    ImageUtility_compareImagesOverload2_imagesWithDifferingPixels_shouldReturnPixelMismatch(filePath, modifiedFilePath);
			    ImageUtility_compareImagesOverload2_imagesWithDifferentDimensions_shouldReturnSizeMismatch(filePath, differentSizeFilePath);

			    Files.delete(Paths.get(modifiedFilePath));
			}
		} catch (IOException e) {
			Log.error(e.toString());
		}
	}

	private static String createModifiedFile(String filePath) throws IOException {
		Path fpath = Paths.get(filePath);
		String fileName = fpath.getFileName().toString();
		String fileDirectory = fpath.getParent().toString();
		String modifiedFilePath = fileDirectory + File.separator + "modified-" + fileName;
		try {
			Files.copy(Paths.get(filePath), Paths.get(modifiedFilePath));
			BufferedImage bi = getModifiedImage(filePath);
	    	File outputfile = new File(modifiedFilePath);
			ImageIO.write(bi, "png", outputfile);
		} catch (Exception ex) {
			Log.error(ex.toString());
		}
		return modifiedFilePath;
	}

	private static BufferedImage getModifiedImage(String filePath) throws IOException {
		BufferedImage bi = ImageIO.read(new File(filePath));
		bi.setRGB(0, 0, 0x111111);
		bi.setRGB(1, 0, 0x222222);
		return bi;
	}

    private static void ImageUtility_compareImagesOverload1_identicalImages_shouldReturnSuccess(Image image1, Image image2) {
    	Log.info("Running ImageUtility_compareImagesOverload1_identicalImages_shouldReturnSuccess ...");
    	ImageComparisonResult result = compareImages(image1, image2);
    	assert(result.isEqual() && result.getClass().getName().equals(NET_AVH4_UTIL_IMAGECOMPARISON_IMAGE_COMPARISON_SUCCESS));
    }

    private static void ImageUtility_compareImagesOverload1_imageCopy_shouldReturnSuccess(Image image1, Image image2) {
    	Log.info("Running ImageUtility_compareImagesOverload1_imageCopy_shouldReturnSuccess ...");
    	ImageComparisonResult result = compareImages(image1, image2);
    	assert(result.isEqual() && result.getClass().getName().equals(NET_AVH4_UTIL_IMAGECOMPARISON_IMAGE_COMPARISON_SUCCESS));
    }

    private static void ImageUtility_compareImagesOverload1_withNoReferenceImage_shouldReturnNoReferenceImageFailure(Image image) {
    	Log.info("Running ImageUtility_compareImagesOverload1_withNoReferenceImage_shouldReturnNoReferenceImageFailure ...");
    	ImageComparisonResult result = compareImages(image, null);
    	assert(result instanceof NoReferenceImageResult);
    }

    private static void ImageUtility_compareImagesOverload1_imagesWithDifferingPixels_shouldReturnPixelMismatch(Image image1, Image image2) {
    	Log.info("Running ImageUtility_compareImagesOverload1_imagesWithDifferingPixels_shouldReturnPixelMismatch ...");
    	ImageComparisonResult result = compareImages(image1, image2);
    	assert(result instanceof PixelMismatchResult);
	}

    private static void ImageUtility_compareImagesOverload1_imagesWithDifferentDimensions_shouldReturnSizeMismatch(Image image1, Image image2) {
    	Log.info("Running ImageUtility_compareImagesOverload1_imagesWithDifferentDimensions_shouldReturnSizeMismatch ...");
    	ImageComparisonResult result = compareImages(image2, image1);
    	assert(result instanceof SizeMismatchResult);
    }

    private static void ImageUtility_compareImagesOverload2_identicalImages_shouldReturnSuccess(String imageFile1, String imageFile2) {
    	Log.info("Running ImageUtility_compareImagesOverload2_identicalImages_shouldReturnSuccess ...");
    	ImageComparisonResult result = compareImages(imageFile1, imageFile2);
    	assert(result.isEqual() && result.getClass().getName().equals(NET_AVH4_UTIL_IMAGECOMPARISON_IMAGE_COMPARISON_SUCCESS));
    }

    private static void ImageUtility_compareImagesOverload2_imageCopy_shouldReturnSuccess(String imageFile1, String imageFile2) {
    	Log.info("Running ImageUtility_compareImagesOverload2_imageCopy_shouldReturnSuccess ...");
    	ImageComparisonResult result = compareImages(imageFile1, imageFile2);
    	assert(result.isEqual() && result.getClass().getName().equals(NET_AVH4_UTIL_IMAGECOMPARISON_IMAGE_COMPARISON_SUCCESS));
    }

    private static void ImageUtility_compareImagesOverload2_withNoReferenceImage_shouldReturnNoReferenceImageFailure(String imageFile) {
    	Log.info("Running ImageUtility_compareImagesOverload2_withNoReferenceImage_shouldReturnNoReferenceImageFailure ...");
    	ImageComparisonResult result = compareImages(imageFile, null);
    	assert(result instanceof NoReferenceImageResult);
    }

    private static void ImageUtility_compareImagesOverload2_imagesWithDifferingPixels_shouldReturnPixelMismatch(String imageFile1, String imageFile2) {
    	Log.info("Running ImageUtility_compareImagesOverload2_imagesWithDifferingPixels_shouldReturnPixelMismatch ...");
    	ImageComparisonResult result = compareImages(imageFile1, imageFile2);
    	assert(result instanceof PixelMismatchResult);
	}

    private static void ImageUtility_compareImagesOverload2_imagesWithDifferentDimensions_shouldReturnSizeMismatch(String imageFile1, String imageFile2) {
    	Log.info("Running ImageUtility_compareImagesOverload2_imagesWithDifferentDimensions_shouldReturnSizeMismatch ...");
    	ImageComparisonResult result = compareImages(imageFile1, imageFile2);
    	assert(result instanceof SizeMismatchResult);
    }
}
