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
import surveyor.scommon.source.ManageAnalyzersPage;
import surveyor.scommon.source.ManageSurveyorPage;
import common.source.TestSetup;

/**
 * @author zlu
 *
 */
public class ManageAnalyzersPageTest {
	public static final String BASECUSTOMERNAME = "CustomerSQA";  //temp solution for now
	public static final String BASELOCATIONNAME = "LocationSQA";
	public static final String BASESURVEYORNAME = "SurveyorSQA";
	public static final String BASEANALYZERNAME = "";
	public static final String ANALYZERSHAREDKEY = "sqa#Picarro$0";
	public static final int CUSTOMERNUM = 10;  //temp solution for now
	public static final int LOCATIONNUM = 5;
	public static final int SURVEYORNUM = 2;
	public static final int ANALYZERNUM = 1;
	
	private static WebDriver driver;
	private static TestSetup testSetup;
	private static String baseURL;
	private static String screenShotsDir;
	private static boolean debug;
	
	private static ManageAnalyzersPage manageAnalyzersPage;
	private static LoginPage loginPage;	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("\nRunning ManageAnalyzersPageTest...");
		
		testSetup = new TestSetup();
		driver = testSetup.getDriver();
		baseURL = testSetup.getBaseUrl();
		screenShotsDir = "./screenshots/";
		debug = testSetup.isRunningDebug();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
		manageAnalyzersPage = new ManageAnalyzersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageAnalyzersPage);
		
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
		manageAnalyzersPage.open();
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		manageAnalyzersPage.logout();
		
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
	 * Test Case: Adding new Analyzers
	 */	
	@Test
	public void manageAnalyzersPage_AddNewAnalyzers() {
		System.out.println("\nRunning manageAnalyzersPage_AddNewAnalyzers...");
		
		try {
			manageAnalyzersPage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			String strSurveyorDesc;
			String strCustomerName;
			String strLocation;
			String strAnalyzer;
			
			String strCusDigits;
			String strLocDigits;
			String strSurDigits;
			
			int customerNum = CUSTOMERNUM;
			int locationNum = LOCATIONNUM;
			int surveyorNum = SURVEYORNUM;
			int analyzerNum = ANALYZERNUM;
			
			for (int i = 0; i < customerNum; i++) {
				if (i < 10) {
					strCustomerName = BASECUSTOMERNAME + "0" + Integer.toString(i);
					strCusDigits = "0" + Integer.toString(i);
				}
				else {
					strCustomerName = BASECUSTOMERNAME + Integer.toString(i);
					strCusDigits = Integer.toString(i);
				}
				
				for (int ii = 0; ii < locationNum; ii++) {
					if (ii < 10) {
						strLocation = strCustomerName + "_" + "0" + Integer.toString(ii) + BASELOCATIONNAME;
						strLocDigits = "0" + Integer.toString(ii);
					}
					else {
						strLocation = strCustomerName + "_" + Integer.toString(ii) + BASELOCATIONNAME;
						strLocDigits = Integer.toString(ii);
					}
					
					for (int iii = 0; iii < surveyorNum; iii++) {
						if (iii < 10) {
							strSurveyorDesc = strLocation + "_" + "0" + Integer.toString(iii) + BASESURVEYORNAME;
							strSurDigits = "0" + Integer.toString(iii);
						}
						else {
							strSurveyorDesc = strLocation + "_" + Integer.toString(iii) + BASESURVEYORNAME;
							strSurDigits = Integer.toString(iii);
						}
						
						for (int iiii = 1; iiii < analyzerNum + 1; iiii++) {
							if (iiii < 10)
								strAnalyzer = strCusDigits + strLocDigits + strSurDigits + "0" + Integer.toString(iiii);
							else
								strAnalyzer = strCusDigits + strLocDigits + strSurDigits + Integer.toString(iiii);
							
							manageAnalyzersPage.addNewAnalyzer(strAnalyzer, ANALYZERSHAREDKEY, strSurveyorDesc);
						}
					}
				}
			}
		}
		catch (Exception e) {
			System.out.println("\nException on test case \"manageAnalyzersPage_AddNewAnalyzers\": " + e.getMessage());
		}
	}
}
