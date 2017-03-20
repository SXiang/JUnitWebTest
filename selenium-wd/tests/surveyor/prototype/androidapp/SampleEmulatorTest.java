package surveyor.prototype.androidapp;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

//Pre-req and set up details can be found in wiki page "http://confluence.picarro.int/display/SD/Mobile+Automation"
public class SampleEmulatorTest {

	@Test
	public void test() throws MalformedURLException, InterruptedException {


		// Create object of  DesiredCapabilities class and specify android platform
		DesiredCapabilities capabilities=DesiredCapabilities.android();


		// set the capability to execute test in chrome browser
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,"Browser");

		// set the capability to execute our test in Android Platform
		capabilities.setCapability(MobileCapabilityType.PLATFORM,Platform.ANDROID);

		// we need to define platform name
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");

		// Set the device name as well (you can give any name)
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"android emulator");

		// set the android version as well
		capabilities.setCapability(MobileCapabilityType.VERSION,"5.1.1");

		// Create object of URL class and specify the appium server address
		URL url= new URL("http://127.0.0.1:4723/wd/hub");

		// Create object of  AndroidDriver class and pass the url and capability that we created
		WebDriver driver = new AndroidDriver(url, capabilities);

		// Open url
		driver.get("https://p3prodstg.picarro.com");

		// print the title
		System.out.println("Title "+driver.getTitle());

		// enter username
		driver.findElement(By.id("Username")).sendKeys("kpatel@picarro.com");

		// enter password
		driver.findElement(By.id("Password")).sendKeys("Test!123");

		// click on submit button
		driver.findElement(By.cssSelector("button[type='submit'][class='btn btn-success btn-lg btn-block']")).click();
		Thread.sleep(10000);

		// close the browser
		driver.quit();

	}

}
