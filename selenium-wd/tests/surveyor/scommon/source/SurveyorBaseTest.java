/**
 * 
 */
package surveyor.scommon.source;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class SurveyorBaseTest {
	public static final String BASECUSTOMERNAME = "Cus";
	public static final String BASELOCATIONNAME = "Loc";
	public static final String BASESURVEYORNAME = "Sur";
	public static final String BASEANALYZERNAME = "";
	public static final String ANALYZERSHAREDKEY = "sqa#Picarro$0";	
	
	public static final String BASEUSERNAME = "SQA@picarro.com";
	public static final String USERPASSWORD = "sqa#Picarro$0";
	
	public static final int CUSTOMERNUM = 50; //Should be set less than 100 otherwise need review the code
	public static final int LOCATIONNUM = 5; //Should be set less than 100 otherwise need review the code
	public static final int SURVEYORNUM = 1; //Should be set less than 100 otherwise need review the code
	public static final int ANALYZERNUM = 1; //Should be set less than 100 otherwise need review the code
	public static final int USERNUM = 5;    //Should be set less than 100 otherwise need review the code
	
	public static WebDriver driver;
	public static TestSetup testSetup;
	public static String baseURL;
	public static String screenShotsDir;
	public static boolean debug;
	
	public static final String CUSTOMERNAMEPREFIX = "RegCus";
	public static final String CUSTOMERSTATUS = "Enabled";
	public static final String EULASTRING = "Testing";
	public static final String REGBASEUSERNAME = "@email.com";
	
	public static final String USERROLEADMIN = "Administrator";
	
	public static final String CUSUSERROLEUA = "Utility Administrator";
	//public static final String CUSUSERROLEUA = "Utility Administator"; //temporary for now because of the typo bug
	public static final String CUSUSERROLESU = "Supervisor";
	public static final String CUSUSERROLEDR = "Driver";
	
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
			testSetup.slowdownInSeconds(3);
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
			testSetup.slowdownInSeconds(3);
		
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