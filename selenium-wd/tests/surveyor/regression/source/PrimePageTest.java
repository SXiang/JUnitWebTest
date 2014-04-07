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

import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.PrimePage;
import common.source.ImagingUtility;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class PrimePageTest {

	private static WebDriver driver;
	private static TestSetup testSetup;
	private static String baseURL;
	private static String screenShotsDir;
	private static boolean debug;
	
	private static PrimePage primePage;
	private static HomePage homePage;
	private static LoginPage loginPage;	
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		System.out.println("\nRunning test suite of PrimePageTest...");
		
		testSetup = new TestSetup();
		driver = testSetup.getDriver();
		baseURL = testSetup.getBaseUrl();
		screenShotsDir = "./screenshots/";
		debug = testSetup.isRunningDebug();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		primePage = new PrimePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  primePage);
		
		homePage = new HomePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, homePage);
		
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
	 *  Test Case: primePage_TC001, browsing the setting controls
	 */
	@Test
	public void primePage_TC001() {
		
		System.out.println("\nRunning test case of primePage_TC001...");
		
		primePage.open();
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		assertTrue(primePage != null && driver.getTitle().compareTo(PrimePage.STRPageTitle) == 0);
		
		primePage.browsingSettingControls();
		
		ImagingUtility.takeScreenShot(driver, screenShotsDir, "primePage_TC001");
	}
}