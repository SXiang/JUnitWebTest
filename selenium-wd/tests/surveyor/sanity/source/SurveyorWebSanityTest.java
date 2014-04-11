package surveyor.sanity.source;

import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SurveyorWebSanityTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
  private String user = "testaccount";
  private String pwd = "winning123!";

  @Before
  public void setUp() throws Exception {
	  
    driver = new FirefoxDriver();
    baseUrl = "http://p3int.picarro.com/";
    
    driver.manage().window().maximize();
    
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void SurveyorWebBuildGUISanityTest() throws Exception {
    
	driver.get(this.baseUrl);
	
	if (driver.getTitle().contains("Problem loading page")) {
		
		fail("Site Error: Problem loading page");
		
	}
	
	if (driver.getTitle().compareTo("Login") != 0) {
		
	    driver.findElement(By.linkText("Log out")).click();
	    assertEquals("Login", driver.getTitle());		
		
		sleep(3);
	}
	
    assertEquals("Login", driver.getTitle());
    
    sleep(3);
    
    driver.findElement(By.id("Username")).sendKeys(user);
    driver.findElement(By.id("Password")).sendKeys(pwd);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    assertEquals("Home - Surveyor", driver.getTitle());
    
    sleep(3);
    
    driver.findElement(By.linkText("Administration")).click();
    assertEquals("Index - Surveyor", driver.getTitle());
    
    sleep(3);
    
    //driver.findElement(By.cssSelector("a.btn.btn-default")).click();
    driver.findElement(By.xpath("/html/body/div/div/div/a/img")).click();
    assertEquals("Home - Surveyor", driver.getTitle());
    
    sleep(3);
    
    driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/p[2]/a")).click();
    assertEquals("Index", driver.getTitle());
    
    sleep(3);
    
    //UI changed so comment out for now until more stable 
    //this.browsingPrimeSettings();
    
    sleep(3);
    
    //driver.get("http://p3int.picarro.com");
    driver.navigate().back();
    assertEquals("Home - Surveyor", driver.getTitle());
    
    sleep(3);
    
    driver.findElement(By.linkText("Log out")).click();
    assertEquals("Login", driver.getTitle());
    
    sleep(3);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }

  public void browsingPrimeSettings() {

    driver.findElement(By.cssSelector("div.standard_icon_inner")).click();
    sleep(3);
    
    driver.findElement(By.cssSelector("#side_content > div.content_element > #switch_peaks")).click();
    sleep(3);
    
    driver.findElement(By.id("switch_iso")).click();
    sleep(3);
    
    driver.findElement(By.cssSelector("div.standard_icon_inner")).click();
    sleep(3);
    
    driver.findElement(By.cssSelector("#bottom_button_car > div.standard_icon_inner")).click();
    sleep(3);
    
    driver.findElement(By.id("start_reference")).click();
    sleep(3);
    
    driver.findElement(By.cssSelector("#bottom_button_car > div.standard_icon_inner")).click();
    sleep(3);
    
    driver.findElement(By.cssSelector("#bottom_button_cursor > div.standard_icon_inner")).click();
    sleep(3);
    
    driver.findElement(By.cssSelector("#bottom_button_cursor > div.standard_icon_inner")).click();
    sleep(3);
    
    driver.findElement(By.cssSelector("#bottom_button_map > div.standard_icon_inner")).click();
    sleep(3);
    
    driver.findElement(By.id("switch_peaks")).click();
    sleep(3);
    
    driver.findElement(By.xpath("(//div[@id='switch_peaks'])[2]")).click();
    sleep(3);
    
    driver.findElement(By.cssSelector("#bottom_button_map > div.standard_icon_inner")).click();
    sleep(3);
    
    driver.findElement(By.cssSelector("#bottom_button_weather > div.standard_icon_inner")).click();
    sleep(3);
    
    driver.findElement(By.cssSelector("button.toggle_button.wide")).click();
    sleep(3);
    
    driver.findElement(By.cssSelector("#bottom_button_weather > div.standard_icon_inner")).click();
    sleep(3);
    
    driver.findElement(By.cssSelector("#bottom_button_type > div.standard_icon_inner")).click();
    sleep(3);
    
    driver.findElement(By.cssSelector("#type_panel > div > div.content_element > #switch_peaks")).click();
    sleep(3);
    
    driver.findElement(By.cssSelector("#bottom_button_type > div.standard_icon_inner")).click();
    sleep(3);
    
    driver.findElement(By.cssSelector("#bottom_button_status > div.standard_icon_inner")).click();
    sleep(3);
    
    driver.findElement(By.cssSelector("#bottom_button_status > div.standard_icon_inner")).click();
    sleep(3);
    
  }
  
  public void sleep(int seconds) {
	try {
		Thread.sleep(seconds * 1000);
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
}