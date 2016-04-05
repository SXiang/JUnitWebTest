/**
 * 
 */
package common.source;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import net.avh4.util.imagecomparison.ImageComparison;
import net.avh4.util.imagecomparison.ImageComparisonResult;
import net.avh4.util.imagecomparison.NoReferenceImageResult;
import net.avh4.util.imagecomparison.PixelMismatchResult;
import net.avh4.util.imagecomparison.SizeMismatchResult;

public class ImagingUtility {

	private static final String NET_AVH4_UTIL_IMAGECOMPARISON_IMAGE_COMPARISON_SUCCESS = "net.avh4.util.imagecomparison.ImageComparisonSuccess";

	public static void takeScreenShot(WebDriver driver, String path,
			String fileName) {
		
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		File screenShotImage = ((TakesScreenshot) augmentedDriver)
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShotImage, new File(path + fileName
					+ ".jpg"));
		} catch (IOException e) {
			Log.error(e.toString());
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

	/**
	 * Executes the unit tests for the two compareImages() method overloads. 
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
