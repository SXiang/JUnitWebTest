package common.source;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import common.source.S3Interface.S3File;

/**
 * Capturing ScreenShots Failed Selenium Test
 * -- Screenshots are created in: executionDir/reports/[testCategory]/screenshots/
 * -- A link/thumbnail is inserted in the extent html report in: executionDir/reports/
 * -- Takes browser screen shot if it's a remote browser testing, desktop screen shot otherwise
 * -- junit-noframe.html generated with screenshot links if running a ant junit report task.
 * @author sxiang
 *
 */
public class ScreenShotOnFailure{

	private WebDriver driver;
	private boolean isBrowserScreenshot;
	private String imgPath;
	private String imgLink;
	private String format = "jpg";

	public ScreenShotOnFailure(String imgPath){
		this("screenshots/", imgPath, true);
	}

	public ScreenShotOnFailure(String screenshotFolder, String imgPath, boolean isBrowserScreenshot){
		this.imgLink = "./"+screenshotFolder;
		this.imgPath = imgPath + "/"+screenshotFolder;
		this.isBrowserScreenshot = isBrowserScreenshot;
	}

	public String takeScreenshot(WebDriver driver, String className) {
		// By default log failure. Take browser screenshot if specified.
		return takeScreenshot(driver, className, isBrowserScreenshot, LogStatus.FAIL);
	}

	public String takeScreenshots(WebDriver driver, String className, boolean takeBrowserScreenShot, LogStatus logStatus) {
		String mainImgName = "", mobileImgName = "";
		if(driver != null){
			mainImgName = takeScreenshot(driver, className, takeBrowserScreenShot, logStatus);
		}
		if(TestContext.INSTANCE.isAppiumDriverInTest()){
			mobileImgName = ", " + takeScreenshot(TestContext.INSTANCE.getAppiumDriver(), className, true, logStatus, true);
		}
		return mainImgName + mobileImgName;
	}
	public String takeScreenshot(WebDriver driver, String className, boolean takeBrowserScreenShot, LogStatus logStatus) {
		return takeScreenshot(driver, className, takeBrowserScreenShot, logStatus, false);
	}

	public String takeScreenshot(WebDriver driver, String className, boolean takeBrowserScreenShot, LogStatus logStatus, boolean isMobile) {
		this.driver = driver;
		String imgName = imgPath;
		try{
			ExtentTest reportLogger = TestContext.INSTANCE.getExtentTest(className);
			String fname = reportLogger.getTest().getName();
			String imgFile = fname.split("\\[")[0]  + (isMobile?"_mobile":"") + "."+format;
			imgName += imgFile;
			if(takeBrowserScreenShot && driver!=null){
				captureBrowserScreenShot(imgName);
			}else{
				captureDesktopScreenShot(imgName);
			}

			String imgFileFullPath = Paths.get(imgPath, imgFile).toString();
			String s3Url = uploadScreenShotToS3(imgFileFullPath);
			logReportScreenShot(reportLogger, fname, s3Url, logStatus);
		}catch(Exception e){
			Log.warn("Failed to take screenshot: "+e);
		}
		return imgName;
	}

	private String uploadScreenShotToS3(String fileFullPath) {
		S3Interface s3Interface = S3Interface.newInterface();
		S3File s3File = S3File.fromFileAndUri(Paths.get(fileFullPath), URI.create(TestContext.INSTANCE.getBaseUrl()));
		String fileKey = s3Interface.uploadFile(s3File);
		return String.format("%s/%s", S3Interface.S3_BUCKET_BASE_URL, fileKey);
	}

	public void logReportScreenShot(ExtentTest reportLogger, String fname, String imgFile, LogStatus logStatus) {
		String image = reportLogger.addScreenCapture(imgFile);
			reportLogger.log(logStatus, "Screenshot", image);
	}

	public void captureBrowserScreenShot(String fileName) {
		captureBrowserScreenShot(driver, fileName, null);
	}

	public static void captureBrowserScreenShot(WebDriver driver, String fileName, Rectangle rect) {
		try{
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			if(rect!=null){
				scrFile = getSubImage(scrFile, rect);
			}
			FileUtils.copyFile(scrFile, new File(fileName));
			Log.info("A browser screenshot saved! - '"+fileName+"'");
		}catch(Exception e){
			Log.warn(e.toString());
		}
	}

	public static File getSubImage(File scrFile, Rectangle rect) throws IOException{
		BufferedImage img = ImageIO.read(scrFile);
		/* if(width/height is <=0, they represent the deviations on width/height */
		if(rect.width<=0){
			rect.width = img.getWidth() - rect.x + rect.width;
		}
		if(rect.height<=0){
			rect.height = img.getHeight() - rect.y + rect.height;
		}
		BufferedImage dest = img.getSubimage(rect.x, rect.y, rect.width, rect.height);
		ImageIO.write(dest, "png", scrFile);
		return scrFile;
	}

	public void captureDesktopScreenShot(String fileName) {
		try{
			Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            ImageIO.write(screenFullImage, format, new File(fileName));
            Log.info("A full desktop screenshot saved! - '"+fileName+"'");

		}catch(Exception e){
			Log.warn(e.toString());
		}
	}
}