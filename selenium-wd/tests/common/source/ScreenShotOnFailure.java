package common.source;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
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
	private boolean isRemoteBrowser;
	private String imgPath;
	private String imgLink;
	private String format = "jpg";

	public ScreenShotOnFailure(String imgPath){
		this("screenshots/", imgPath, true);
	}
	public ScreenShotOnFailure(String screenshotFolder, String imgPath, boolean isRemoteBrowser){
		this.imgLink = "./"+screenshotFolder;
		this.imgPath = imgPath + "/"+screenshotFolder;
		this.isRemoteBrowser = isRemoteBrowser;
	}

	public String takeScreenshot(WebDriver driver, String className) {
		this.driver = driver;
		String imgName = imgPath;
		try{
			ExtentTest reportLogger = TestContext.INSTANCE.getExtentTest(className);
			String fname = reportLogger.getTest().getName();
			String imgFile = fname.split("\\[")[0] + "."+format;
			imgName += imgFile;
			if(isRemoteBrowser && driver!=null){
				captureBrowserScreenShot(imgName);
			}else{
				captureDesktopScreenShot(imgName);
			}
			logReportScreenShot(reportLogger, fname, imgLink+imgFile);
		}catch(Exception e){
			Log.warn("Failed to take screenshot: "+e);
		}
		return imgName;
	}


	public void logReportScreenShot(ExtentTest reportLogger, String fname, String imgFile){
		String image = reportLogger.addScreenCapture(imgFile);
			reportLogger.log(LogStatus.FAIL, "Screenshot", image);
	}

	public void captureBrowserScreenShot(String fileName){
		try{
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(fileName));
			Log.info("A browser screenshot saved! - '"+fileName+"'");
		}catch(Exception e){
			Log.warn(e.toString());
		}
	}

	public void captureDesktopScreenShot(String fileName){
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
