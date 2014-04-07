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
import common.source.ImagingUtility;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class AdministrationPageTest {
	
	private static WebDriver driver;
	private static TestSetup testSetup;
	private static String baseURL;
	private static String screenShotsDir;
	private static boolean debug;

	private static AdministrationPage administrationPage;
	private static HomePage homePage;
	private static LoginPage loginPage;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		System.out.println("\nRunning suite of AdministrationPageTest...");
		
		testSetup = new TestSetup();
		driver = testSetup.getDriver();
		baseURL = testSetup.getBaseUrl();
		screenShotsDir = "./screenshots/";
		debug = testSetup.isRunningDebug();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		administrationPage = new AdministrationPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  administrationPage);
		
		homePage = new HomePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  homePage);
		
		loginPage = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  loginPage);
		
		loginPage.open();
		if (debug)
			testSetup.slowdownInSeconds(3);
		loginPage.loginNormalAs(testSetup.getLoginUser0000(),  testSetup.getLoginPwd0000());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		administrationPage.open();
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		administrationPage.logout();
		
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
	 *  Test Case: administrationPage_TC001 
	 */
	@Test
	public void administrationPage_TC001() {
		
		System.out.println("\nRunning test case of administrationPage_TC001...");
		
		administrationPage.open();
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		assertTrue(driver.getTitle().compareTo(AdministrationPage.STRPageTitle) == 0);
		
		ImagingUtility.takeScreenShot(driver, screenShotsDir, "administrationPage_TC001");
	}
}