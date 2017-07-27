package common.source;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class BrowserWindowUtility {

		public static void main (String args[]) {
			System.setProperty("webdriver.chrome.driver", "C:\\Repositories\\surveyor-qa\\selenium-wd\\lib\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.get("https://p3sqaauto.picarro.com/");			
            Dimension browserSize = getBrowserSize(driver, new Dimension(1200,600));
			driver.manage().window().setSize(browserSize);
			driver.close();
		}

		public static Dimension getBrowserSize(WebDriver driver, Dimension viewPortSize){
			driver.manage().window().setPosition(new Point(0,0));
			driver.manage().window().setSize(viewPortSize);
			Dimension browserSize = driver.manage().window().getSize();
			Dimension actualViewportSize = getViewportSize(driver);
			Dimension newBrowserSize = new Dimension(2 * browserSize.width - actualViewportSize
					.width, 2 * browserSize.height - actualViewportSize.height);
			driver.manage().window().setPosition(new Point(0,0));
			driver.manage().window().setSize(newBrowserSize);
			browserSize = driver.manage().window().getSize();
			
			actualViewportSize = getViewportSize(driver);
			browserSize = new Dimension(newBrowserSize.width - actualViewportSize.width + viewPortSize.width,
					newBrowserSize.height - actualViewportSize.height + viewPortSize.height);
			driver.manage().window().maximize();
			return browserSize;
		}
		
		protected static Dimension getViewportSize(WebDriver driver) {
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
