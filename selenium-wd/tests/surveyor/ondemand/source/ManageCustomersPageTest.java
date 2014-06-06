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
import surveyor.scommon.source.ManageCustomersPage;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class ManageCustomersPageTest {
	public static final String BASECUSTOMERNAME = "CustomerSQA"; 
	
	private static WebDriver driver;
	private static TestSetup testSetup;
	private static String baseURL;
	private static String screenShotsDir;
	private static boolean debug;
	
	private static ManageCustomersPage manageCustomersPage;
	private static LoginPage loginPage;		

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("\nRunning ManageCustomersPageTest...");
		
		testSetup = new TestSetup();
		driver = testSetup.getDriver();
		baseURL = testSetup.getBaseUrl();
		screenShotsDir = "./screenshots/";
		debug = testSetup.isRunningDebug();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
		
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
		manageCustomersPage.open();
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
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

	/**
	 * Test Case: Adding new customers
	 */
	@Test
	public void manageCustomersPage_AddNewCustomers() {
		System.out.println("\nRunning manageCustomersPage_AddNewCustomers...");
	
		try {
			manageCustomersPage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			String strCustomerName = "";
			
			for (int i = 0; i < 10; i++) {
				strCustomerName = BASECUSTOMERNAME + "0" + Integer.toString(i);
				manageCustomersPage.addNewCustomer(strCustomerName, strCustomerName + " eula");
			}
		}
		catch (Exception e) {
			System.out.println("Exception on test case \"manageCustomersPage_AddNewCustomers\": " + e.getMessage());
		}
	}
}
