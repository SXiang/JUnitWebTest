/**
 * 
 */
package common.source;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

/**
 * @author zlu
 * 
 */
public class ImagingUtility {

	public static void takeScreenShot(WebDriver driver, String path,
			String fileName) {
		
		WebDriver augmentedDriver = new Augmenter().augment(driver);
		File screenShotImage = ((TakesScreenshot) augmentedDriver)
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShotImage, new File(path + fileName
					+ ".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public ImagingUtility() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//for code testing
	}

}
