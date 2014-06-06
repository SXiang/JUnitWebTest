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
import surveyor.scommon.source.ManageLocationsPage;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class ManageLocationsPageTest {
	public static final String BASECUSTOMERNAME = "CustomerSQA";
	public static final String BASELOCATIONNAME = "LocationSQA";
	
	private static WebDriver driver;
	private static TestSetup testSetup;
	private static String baseURL;
	private static String screenShotsDir;
	private static boolean debug;
	
	private static ManageLocationsPage manageLocationsPage;
	private static LoginPage loginPage;	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("\nRunning ManageLocationsPageTest...");
		
		testSetup = new TestSetup();
		driver = testSetup.getDriver();
		baseURL = testSetup.getBaseUrl();
		screenShotsDir = "./screenshots/";
		debug = testSetup.isRunningDebug();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		manageLocationsPage = new ManageLocationsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageLocationsPage);
		
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
		manageLocationsPage.open();
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		manageLocationsPage.logout();
		
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
	 * Test Case: Adding new locations
	 */	
	@Test
	public void manageLocationsPage_AddNewLocations() {
		System.out.println("\nRunning manageLocationsPage_AddNewLocations...");
		
		try {
			manageLocationsPage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			String strCustomerName = "";
			String strLocation = "";
			
			for (int i = 0; i < 10; i++) {
				strCustomerName = BASECUSTOMERNAME + "0" + Integer.toString(i);
				for (int ii = 0; ii < 5; ii++) {
					if (ii < 10)
						strLocation = strCustomerName + "_" + "0" + Integer.toString(ii) + BASELOCATIONNAME;
					else
						strLocation = strCustomerName + "_" + Integer.toString(ii) + BASELOCATIONNAME;
					
					manageLocationsPage.addNewLocation(strLocation, strCustomerName);
				}
			}
		}
		catch (Exception e) {
			System.out.println("Exception on test case \"manageLocationsPage_AddNewLocations\": " + e.getMessage());
		}
	}
}