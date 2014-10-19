/**
 * 
 */
package surveyor.scommon.source;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class SurveyorBaseTest {
	public static WebDriver driver;
	public static TestSetup testSetup;
	public static String baseURL;
	public static String screenShotsDir;
	public static boolean debug;

	public static LoginPage loginPage;
	public static HomePage homePage;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testSetup = new TestSetup();
		driver = testSetup.getDriver();
		baseURL = testSetup.getBaseUrl();
		screenShotsDir = "./screenshots/";
		debug = testSetup.isRunningDebug();
		driver.manage().deleteAllCookies();
		
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
		
		if (!driver.getTitle().equalsIgnoreCase("Login"))
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
}