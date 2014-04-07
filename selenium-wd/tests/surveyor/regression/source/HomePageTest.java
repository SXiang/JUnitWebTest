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

import surveyor.scommon.source.AdministrationPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.PrimePage;
import common.source.ImagingUtility;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class HomePageTest {
	
	private static WebDriver driver;
	private static TestSetup testSetup;
	private static String baseURL;
	private static String screenShotsDir;
	private static boolean debug;
	
	private static HomePage homePage;
	private static LoginPage loginPage;	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		System.out.println("\nRunning suite of HomePageTest...");
		
		testSetup = new TestSetup();
		driver = testSetup.getDriver();
		baseURL = testSetup.getBaseUrl();
		screenShotsDir = "./screenshots/";
		debug = testSetup.isRunningDebug();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		homePage = new HomePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  homePage);
		
		loginPage = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  loginPage);
		
		loginPage.open();
		if (debug)
			testSetup.slowdownInSeconds(3);		
		loginPage.loginNormalAs(testSetup.getLoginUser0000(), testSetup.getLoginPwd0000());
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
	 *	Test Case: homePage_TC001, check the "Administration" link on the home page 	
	 */	
	@Test
	public void homePage_TC001() {
		
		System.out.println("\nRunning test case of homePage_TC001...");
		
		try {
			
			homePage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			assertTrue(driver.getTitle().compareTo(HomePage.STRPageTitle) == 0);
			
			ImagingUtility.takeScreenShot(driver, screenShotsDir, "homePage_TC001_HomePage");
		}
		catch (Exception e) {
			
			ImagingUtility.takeScreenShot(driver, screenShotsDir, "homePage_TC001_exception");
			
			System.out.println("Exception on test case \"homePage_TC001\": " + e.getMessage());
		}
		
		AdministrationPage administrationPage = homePage.navigateToAdministationPage();
		assertTrue(administrationPage != null && driver.getTitle().compareTo(AdministrationPage.STRPageTitle) == 0);
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		ImagingUtility.takeScreenShot(driver, screenShotsDir, "homePage_TC001_AdministrationPage");
	}
	
	/**
	 *	Test Case: homePage_TC002, check the "Follow a Surveyor" link on the home page 	
	 */	
	@Test
	public void homePage_TC002() {
		
		System.out.println("\nRunning test case of homePage_TC002...");
		
		try {
			
			homePage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			ImagingUtility.takeScreenShot(driver, screenShotsDir, "homePage_TC002_HomePage");
		}
		catch (Exception e) {
			
			ImagingUtility.takeScreenShot(driver, screenShotsDir, "homePage_TC002_exception");
			
			System.out.println("Exception on test case \"homePage_TC002\": " + e.getMessage());
		}
		
		PrimePage primePage = homePage.navigateToPrimePage();
		assertTrue(primePage != null && driver.getTitle().compareTo(PrimePage.STRPageTitle) == 0);
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		ImagingUtility.takeScreenShot(driver, screenShotsDir, "homePage_TC002_PrimePage");		
	}	
}