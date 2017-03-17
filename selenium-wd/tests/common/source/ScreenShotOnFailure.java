package common.source;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

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
			logReportScreenShot(reportLogger, fname, imgLink+imgFile, logStatus);
		}catch(Exception e){
			Log.warn("Failed to take screenshot: "+e);
		}
		return imgName;
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