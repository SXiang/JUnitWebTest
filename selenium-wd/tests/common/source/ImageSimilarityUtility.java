package common.source;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.GrayFilter;

import org.junit.Assert;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import net.avh4.util.imagecomparison.ImageComparisonResult;
public class ImageSimilarityUtility {

		// parameters might need tune-up
		private static  int comparex;	// The number of vertical columns in the comparison grid.
		private static  int comparey;	// The number of horizontal rows in the comparison grid.
		private static  int factorA = 0;	// The threshold value: If the difference in brightness exceeds this then the region is considered different.
		private static  int factorD = 10;	// The stabilization factor.
		private static int debugMode = 0; // 0: show only exception message; 1: textual indication of change; 2: difference of factors
		private static boolean match = false;
		private static boolean average = true;

		private static BufferedImage img1;
		private static BufferedImage img2;
		private static BufferedImage imgc;
		
		public static final String diffImageSuffix = "_diff.jpeg";

		public static boolean isSimilaryImage(String file1, String file2,boolean small){
			factorA = 0;
			factorD = 5;
			return isSimilaryImage(file1, file2);
		}

		// constructor 1. use filenames
		private ImageSimilarityUtility(String file1, String file2) {
			this(loadImage(file1), loadImage(file2));
		}

		// constructor 2. use awt images.
		private ImageSimilarityUtility(Image _img1, Image _img2) {
			img1 = imageToBufferedImage(_img1);
			img2 = imageToBufferedImage(_img2);
		}

		
		public static void setFactorA(int factorA) {
			ImageSimilarityUtility.factorA = factorA;
		}

		public static void setFactorD(int factorD) {
			ImageSimilarityUtility.factorD = factorD;
		}

		public static void setDebugMode(int debugMode) {
			ImageSimilarityUtility.debugMode = debugMode;
		}

		public static boolean isSimilaryImage(String file1, String file2) {
			String imgDiffFileName = file1+diffImageSuffix;
			return isSimilaryImage(file1,file2,imgDiffFileName);
		}
		public static boolean isSimilaryImage(String file1, String file2,String imgDiffFileName) {
			ImageSimilarityUtility ic = new ImageSimilarityUtility(file1, file2);
	        
			try{
			   ic.compare();
			}catch(Exception e){
				System.out.println("Exception thrown when compare images '"+e.toString()+"'");
			}
				if(! match) {			
					Log.error("Not Match: check image difference in file [" + imgDiffFileName + "]");
					saveJPG(imgc, imgDiffFileName);
				}

			return match;
		}

		// compare the two images in this object.
		private boolean isSameSize(){
			if(img1==null||img2==null){
				return false;
			}
			int height = img1.getHeight();
			int width = img1.getWidth();
			int px = 20,py = 20;
			
			comparex = height/px;
			comparey = width/py;
			
			if(img1.getWidth()!=img2.getWidth()
					||img1.getHeight()!=img2.getHeight()){
				Log.error("Images are in different size?");
			    return false;
			}
			return true;
		}
		private void compare() {
			// setup change display image
			if(!isSameSize()){
				match = false;
				return;
			}
		

			imgc = imageToBufferedImage(img1);
			Graphics2D gc = imgc.createGraphics();
			gc.setColor(Color.RED);

			// convert to gray images.
			img1 = imageToBufferedImage(GrayFilter.createDisabledImage(img1));
			img2 = imageToBufferedImage(GrayFilter.createDisabledImage(img2));

			// how big are each section
			int blocksx = img1.getWidth() / comparex;
			int blocksy = img1.getHeight() / comparey;

			// set to a match by default, if a change is found then flag non-match
			match = true;

			// loop through whole image and compare individual blocks of images
			for (int y = 1; y < comparey-1; y++) {
				if (debugMode > 0) System.out.print("|");

				for (int x = 1; x < comparex-1; x++) {
					int b1 = getAverageBrightness(img1.getSubimage(x*blocksx, y*blocksy, blocksx - 1, blocksy - 1));
					int b2 = getAverageBrightness(img2.getSubimage(x*blocksx, y*blocksy, blocksx - 1, blocksy - 1));

					int diff = Math.abs(b1 - b2);
					if (diff > factorA) { 
						// the difference in a certain region has passed the threshold value of factorA
						// draw an indicator on the change image to show where change was detected.
						Log.warn("Brightness difference is too high - "+diff+" > "+factorA);
						gc.drawRect(x*blocksx, y*blocksy, blocksx - 1, blocksy - 1);
						match = false;
					}else if(diff>0){
						if(debugMode>0){
							Log.debug("Brightness difference found - "+diff);
						}
					}
				}
			}
		}

		// returns a value specifying some kind of average brightness in the image.
		private int getAverageBrightness(BufferedImage img) {
			Raster r = img.getData();
	       
			int total = 0;
			for (int y = 0; y < r.getHeight(); y++) {
				for (int x = 0; x < r.getWidth(); x++) {
					try{
					    total += r.getSample(r.getMinX() + x, r.getMinY() + y, 0);
					}catch(Exception e){
						Log.warn("Warning: "+e.toString());
					}
				}
			}
			if(average)
	           return total / ((r.getWidth()/factorD)*(r.getHeight()/factorD));
			return total;
		}

		// buffered images are just better. 
		private static BufferedImage imageToBufferedImage(Image img) {
			
			BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = bi.createGraphics();
			g2.drawImage(img, null, null);
			return bi;
		}

		private static void saveJPG(Image img, String filename) {
			BufferedImage bi = imageToBufferedImage(img);
			FileOutputStream out = null;
			File file = new File(filename);
			if(file.exists()){
				file.delete();
			}
			try { 
				out = new FileOutputStream(filename);
			} catch (java.io.FileNotFoundException io) { 
				System.out.println("File Not Found"); 
			}
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
			param.setQuality(0.8f, false);
			encoder.setJPEGEncodeParam(param);
			try { 
				encoder.encode(bi); 
				out.close(); 
			} catch (java.io.IOException io) {
				System.out.println("IOException"); 
			}
		}

		private static Image loadImage(String filename) {
			BufferedImage bi = null;
			if(filename.endsWith(".png")){
				try{
					bi = ImageIO.read(new File(filename));
				}catch(java.io.IOException io){ 
					Log.warn("File Not Found"); 
				}
			}else if(filename.endsWith(".jpeg")||filename.endsWith(".jpg")){
				FileInputStream in = null;
				try { 
					in = new FileInputStream(filename);
				} catch (java.io.FileNotFoundException io) { 
					Log.warn("File Not Found"); 
				}

				JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(in);
				try { 
					bi = decoder.decodeAsBufferedImage(); 
					in.close(); 
				} catch (java.io.IOException io) {
					Log.warn("IOException");
				}
			}else{
				Log.warn("Image format is not supported: "+filename);
			}
			return bi;
		}

		/**
		 * Executes the unit tests for the two compareImages() method overloads. 
		 * @param args
		 * @throws IOException 
		 */
		public static void main(String[] args) throws IOException {
			Path screenshotDirectory = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\test-data\\screenshots\\UnitTest");
			
			String[] expectedImages = {"\\pdf\\TC700\\Page_1.png","\\pdf\\TC700\\Page_2.png","\\pdf\\TC700\\Page_3.png",
					"\\view\\TC700\\View_View030_1.png"};
			String[] actualImages = {"\\pdf\\TC700_2\\Page_1.png","\\pdf\\TC700_2\\Page_2.png","\\pdf\\TC700_2\\Page_3.png",
					"\\view\\TC700_2\\View_View030_1.png"};
			String[] expectedGISImages = {"\\mobile\\TC1628\\investigationMap.png"};
			String[] actualGISImages = {"\\mobile\\TC1628_2\\investigationMap.png"};
			
			String[] modifiedGISImages = {"\\mobile\\TC1628_2\\_investigationMap.png"};
			String[] smallerGISImages = {"\\mobile\\TC1628_2\\investigationMap_smaller.png"};
				for (int i=0; i<expectedImages.length; i++) {
					Assert.assertTrue(isEqual(screenshotDirectory+actualImages[i], screenshotDirectory+expectedImages[i]));
				}
				
				for (int i=0; i<expectedGISImages.length; i++) {
					Assert.assertTrue(isEqual(screenshotDirectory+actualImages[i], screenshotDirectory+expectedImages[i]));
				}

				for (int i=0; i<expectedGISImages.length; i++) {
					Assert.assertFalse(isEqual(screenshotDirectory+actualGISImages[i], screenshotDirectory+expectedGISImages[i]));
				}
				
				for (int i=0; i<expectedGISImages.length; i++) {
					Assert.assertFalse(isEqual(screenshotDirectory+modifiedGISImages[i], screenshotDirectory+expectedGISImages[i]));
				}
				
				for (int i=0; i<expectedGISImages.length; i++) {
					Assert.assertTrue(ImageSimilarityUtility.isSimilaryImage(screenshotDirectory+actualGISImages[i], screenshotDirectory+expectedGISImages[i]));
				}
				
				for (int i=0; i<expectedGISImages.length; i++) {
					Assert.assertFalse(ImageSimilarityUtility.isSimilaryImage(screenshotDirectory+modifiedGISImages[i], screenshotDirectory+expectedGISImages[i]));
				}
				
				for (int i=0; i<expectedGISImages.length; i++) {
					Assert.assertFalse(ImageSimilarityUtility.isSimilaryImage(screenshotDirectory+smallerGISImages[i], screenshotDirectory+expectedGISImages[i]));
				}
		}
		public static boolean isEqual(String actualImage, String baselineImage){
			ImageComparisonResult result = ImagingUtility.compareImages(actualImage, baselineImage);
			String error = result.getFailureMessage();
			
			boolean  isEqual = result.isEqual();
			if(!isEqual){
				Log.error("Images are not match - baseline: '"+baselineImage+", actual '"+actualImage+"'");
				if(error!=null){
					Log.error("Images comparison error: "+error);
				}
			}
			return isEqual;
		}
	}