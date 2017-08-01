package common.source;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class BrowserWindowUtility {

		public static void main (String args[]) throws Exception {
			System.setProperty("webdriver.chrome.driver", "C:\\Repositories\\surveyor-qa\\selenium-wd\\lib\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.get("https://p3sqaauto.picarro.com/");
			for(int i=0; i<50;i++){
				Dimension browserSize = getBrowserSize(driver, new Dimension(1199,600));
				driver.manage().window().setSize(browserSize);
				WebElement page = driver.findElement(By.tagName("body"));
				new Actions(driver).sendKeys(page, Keys.CONTROL, "0", Keys.NULL).perform();
				Dimension actualViewportSize = getViewportSize(driver);
				System.out.println("Set view port to: "+actualViewportSize);
			}
			driver.close();
		}

		public static Dimension getBrowserSize(WebDriver driver, Dimension viewPortSize) throws Exception{
			WebElement page = driver.findElement(By.tagName("body"));
			new Actions(driver).sendKeys(page, Keys.CONTROL, "0", Keys.NULL).perform();
			driver.manage().window().setPosition(new Point(0,0));
			driver.manage().window().setSize(viewPortSize);
			Dimension actualViewportSize = getViewportSize(driver);
			Dimension browserSize = driver.manage().window().getSize();
			int widthDiff = browserSize.width - actualViewportSize.width;
			int heightDiff = browserSize.height - actualViewportSize.height;
			Dimension newBrowserSize = new Dimension(viewPortSize.width + widthDiff, viewPortSize.height + heightDiff);
			driver.manage().window().maximize();
			return newBrowserSize;
		}

		protected static Dimension getViewportSize(WebDriver driver) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int width = extractViewportWidth(driver);
			int height = extractViewportHeight(driver);
			return new Dimension(width, height);
		}

		protected static int extractViewportWidth(WebDriver driver) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			int viewportWidth = Integer.parseInt(js.executeScript(JS_GET_VIEWPORT_WIDTH, new Object[0]).toString());
			return viewportWidth;
		}

		protected static int extractViewportHeight(WebDriver driver) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			int result = Integer.parseInt(js.executeScript(JS_GET_VIEWPORT_HEIGHT, new Object[0]).toString());
			return result;
		}

		private static final String JS_GET_VIEWPORT_WIDTH = "var width = undefined; if (window.innerWidth) {width = window.innerWidth;} else if (document.documentElement && document.documentElement.clientWidth) {width = document.documentElement.clientWidth;} else { var b = document.getElementsByTagName('body')[0]; if (b.clientWidth) {width = b.clientWidth;}};return width;";

		private static final String JS_GET_VIEWPORT_HEIGHT = "var height = undefined;  if (window.innerHeight) {height = window.innerHeight;}  else if (document.documentElement && document.documentElement.clientHeight) {height = document.documentElement.clientHeight;}  else { var b = document.getElementsByTagName('body')[0]; if (b.clientHeight) {height = b.clientHeight;}};return height;";

	}
