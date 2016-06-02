package common.source;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ScreenShotOnFailure implements MethodRule {

	private WebDriver driver;
	private boolean isRemoteBrowser = true;
	private String imgPath = "/screenshots/";
	private String format = "jpg";

	public ScreenShotOnFailure(WebDriver driver, String imgPath){
		this(driver, imgPath, true);
	}
	public ScreenShotOnFailure(WebDriver driver, String imgPath, boolean isRemoteBrowser){
		this.driver = driver;	
		this.imgPath = imgPath + this.imgPath;
		this.isRemoteBrowser = isRemoteBrowser;
	}
	@Override
	public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {				
				try {
					statement.evaluate();
				} catch (Throwable t) {	
					String fname = frameworkMethod.getName();
					String imgFile = fname.split("\\[")[0] + "."+format;
				    String imgName = imgPath + imgFile;
					if(isRemoteBrowser){
						captureBrowserScreenShot(imgName);
					}else{
						captureBrowserScreenShot(imgName);
						//captureDesktopScreenShot(imgName);
					}
					logReportScreenShot(fname, imgName, t);			
				}
			}
		};
	}


	public void logReportScreenShot(String fname, String imgFile, Throwable t) throws Throwable{
		String errMsg = t.getMessage();
		try{
		String executionPath = TestSetup.getExecutionPath().replaceAll("\\\\", "/");
		imgFile = imgFile.replaceAll("\\\\", "/");
		imgFile = imgFile.replaceFirst(executionPath+TestSetup.reportDir, "./");
		}catch(Exception e){
			
		}
		ExtentTest reportLogger = TestContext.INSTANCE.getExtentTest();
		if(reportLogger!=null){
			String image = reportLogger.addScreenCapture(imgFile);
			reportLogger.log(LogStatus.FAIL, errMsg, image);
		}else{		
		  errMsg = ">>> TestMethod: "+fname+System.lineSeparator()+ errMsg;
		  errMsg = System.lineSeparator()+">>> ScreenShot: "+imgFile+System.lineSeparator()+errMsg;		
		}
		throw new Throwable(errMsg);
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
