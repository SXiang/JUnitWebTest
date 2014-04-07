/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.*;
import common.source.ImagingUtility;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class LoginPageTest {
	
	private static WebDriver driver;
	private static TestSetup testSetup;
	private static String baseURL;
	private static String screenShotsDir;
	private static boolean debug;
	
	private static LoginPage loginPage;
	private static HomePage homePage;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		System.out.println("\nRunning test suite of LoginPageTest...");
		
		testSetup = new TestSetup();
		driver = testSetup.getDriver();
		baseURL = testSetup.getBaseUrl();
		screenShotsDir = "./screenshots/";
		debug = testSetup.isRunningDebug();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		loginPage = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  loginPage);
		
		homePage = new HomePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  homePage);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		homePage.open();
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		homePage.logout();
		
		driver.quit();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 *	Test Case: LoginPage_TC001, check normal login (with right credentials) 	
	 */
	@Test
	public void loginPage_TC001() {
		
		System.out.println("\nRunning test case of loginPage_TC001...");
		
		try {
			
			loginPage.open();			
			
			if (debug)
				testSetup.slowdownInSeconds(3);
				
			HomePage hp = loginPage.loginNormalAs(testSetup.getLoginUser0000(), testSetup.getLoginPwd0000());
			PageFactory.initElements(driver,  hp);
			
			if (debug) {
					
				System.out.println("The login user is : " + testSetup.getLoginUser0000());
				System.out.println("The login pwd is : " + testSetup.getLoginPwd0000());
			}
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			assertTrue(hp != null && driver.getTitle().compareTo(HomePage.STRPageTitle) == 0);
			
			ImagingUtility.takeScreenShot(driver, screenShotsDir, "loginPage_TC001");
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
		}
	}
}