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
	public static ManageCustomersPage manageCustomersPage;

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
		//driver.manage().window().maximize();
		
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);		
		
		loginPage = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  loginPage);
		
		loginPage.open();
		if (debug)
			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		//testSetup.slowdownInSeconds(10); // temporary solution for now
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		manageCustomersPage.open();
		
		if (debug)
			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		if (!driver.getTitle().equalsIgnoreCase("Login"))
			manageCustomersPage.logout();
		
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