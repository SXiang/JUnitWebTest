/**
 * 
 */
package surveyor.ondemand.source;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageUsersPage;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class ManageUsersPageTest {
	public static final String BASECUSTOMERNAME = "CustomerSQA"; //will load this from property file later
	public static final String BASEUSERNAME = "SQA@picarro.com";
	public static final String USERPASSWORD = "sqa#Picarro$0";

	private static WebDriver driver;
	private static TestSetup testSetup;
	private static String baseURL;
	private static String screenShotsDir;
	private static boolean debug;
	
	private static ManageUsersPage manageUsersPage;
	private static LoginPage loginPage;	
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("\nRunning ManageUsersPageTest...");
		
		testSetup = new TestSetup();
		driver = testSetup.getDriver();
		baseURL = testSetup.getBaseUrl();
		screenShotsDir = "./screenshots/";
		debug = testSetup.isRunningDebug();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		
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
		manageUsersPage.open();
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		manageUsersPage.logout();
		
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
	 * Test Case: Adding new users
	 */
	@Test
	public void manageUsersPage_AddNewUsers() {
		System.out.println("\nRunning manageUsersPage_AddNewUsers...");
		
		try {
			manageUsersPage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			String strCustomerName = "";
			String strUserName = "";			
			
			for (int i = 0; i < 10; i++) {
				strCustomerName = BASECUSTOMERNAME + "0" + Integer.toString(i);
				for (int ii = 0; ii < 50; ii++) {
					if (ii < 10)
						strUserName = strCustomerName + "_" + "0" + Integer.toString(ii) + BASEUSERNAME;
					else
						strUserName = strCustomerName + "_" + Integer.toString(ii) + BASEUSERNAME;
					
					manageUsersPage.addNewUser(strCustomerName, strUserName, USERPASSWORD, "Administrator");
				}
			}
		}
		catch (Exception e) {
			System.out.println("Exception on test case \"manageUsersPage_AddNewUsers\": " + e.getMessage());
		}
		
	}
}