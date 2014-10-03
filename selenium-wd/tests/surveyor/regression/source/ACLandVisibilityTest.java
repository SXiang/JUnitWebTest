/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.SurveyorBaseTest;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class ACLandVisibilityTest extends SurveyorBaseTest {	
////	private static ManageCustomersPage manageCustomersPage;
////	private static ManageUsersPage manageUsersPage;
////	private static LoginPage loginPage;
////	private static HomePage homePage;
//	
//	public ACLandVisibilityTest() {
////		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
////		PageFactory.initElements(driver,  manageCustomersPage);
////		
////		manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
////		PageFactory.initElements(driver,  manageUsersPage);
//		
////		homePage = new HomePage(driver, baseURL, testSetup);
////		PageFactory.initElements(driver,  homePage);
////		
////		loginPage = new LoginPage(driver, baseURL, testSetup);
////		PageFactory.initElements(driver,  loginPage);
//	}
	
	/**
	 * Test Case ID: ACLV000A
	 * Test Description: Check ACLV for customer user with Utility Administrator role
	 * 
	 */
	@Test
	public void ACLV000A() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "aclv000a";
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + REGBASEUSERNAME;
		
		System.out.println("\nRunning ACLV000A - Test Description: Check ACLV for customer user with Utility Administrator role");
		
		manageCustomersPage.open();
		
		if (driver.getTitle().equalsIgnoreCase("Login")) {
			LoginPage loginPage = new LoginPage(driver, baseURL, testSetup);
			PageFactory.initElements(driver,  loginPage);
			
			loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		}			
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		assertTrue(manageCustomersPage.findExistingCustomer(customerName));
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		manageUsersPage.open();
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEUA);
		
		System.out.format("\nThe customer name is: %s, the userName is: %s, the role is: %s\n", customerName, userName, CUSUSERROLEUA);
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		assertTrue(manageUsersPage.findExistingUser(customerName, userName));
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		ManageCustomersPage cusPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  cusPage);
		
		cusPage.open();
		cusPage.logout();
		
		LoginPage loginPage = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  loginPage);
		
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		HomePage homePage = new HomePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  homePage);
		homePage.open();
		
		assertTrue(homePage.checkIfAtHomePage());
		
		assertTrue(homePage.checkVisitilityForCusUA(userName));
		
		homePage.logout();
	}
	
	/**
	 * Test Case ID: ACLV000B
	 * Test Description: Check ACLV for customer user with Supervisor role
	 * 
	 */
	@Test
	public void ACLV000B() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "aclv000b";
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + REGBASEUSERNAME;
		
		System.out.println("\nRunning ACLV000B - Test Description: Check ACLV for customer user with Supervisor role");
		
		manageCustomersPage.open();
		
		if (driver.getTitle().equalsIgnoreCase("Login")) {
			LoginPage loginPage = new LoginPage(driver, baseURL, testSetup);
			PageFactory.initElements(driver,  loginPage);
			
			loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		}
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		assertTrue(manageCustomersPage.findExistingCustomer(customerName));
		
		if (debug)
			testSetup.slowdownInSeconds(3);

		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		manageUsersPage.open();
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLESU);
		
		System.out.format("\nThe customer name is: %s, the userName is: %s, the role is: %s\n", customerName, userName, CUSUSERROLESU);
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		assertTrue(manageUsersPage.findExistingUser(customerName, userName));
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		ManageCustomersPage cusPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  cusPage);
		
		cusPage.open();
		cusPage.logout();
		
		LoginPage loginPage = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  loginPage);
		
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		HomePage homePage = new HomePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  homePage);
		homePage.open();
		
		assertTrue(homePage.checkIfAtHomePage());
		
		assertTrue(homePage.checkVisitilityForCusSU(userName));
		
		homePage.logout();				
	}

	/**
	 * Test Case ID: ACLV000C
	 * Test Description: Check ACLV for customer user with Driver role
	 * 
	 */
	@Test
	public void ACLV000C() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "aclv000c";
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + REGBASEUSERNAME;
		
		System.out.println("\nRunning ACLV000C - Test Description: Check ACLV for customer user with Driver role");
		
		manageCustomersPage.open();
		
		if (driver.getTitle().equalsIgnoreCase("Login")) {
			LoginPage loginPage = new LoginPage(driver, baseURL, testSetup);
			PageFactory.initElements(driver,  loginPage);
			
			loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		}
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		assertTrue(manageCustomersPage.findExistingCustomer(customerName));
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		manageUsersPage.open();
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEDR);
		
		System.out.format("\nThe customer name is: %s, the userName is: %s, the role is: %s\n", customerName, userName, CUSUSERROLEDR);
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		assertTrue(manageUsersPage.findExistingUser(customerName, userName));
		
		if (debug)
			testSetup.slowdownInSeconds(3);
		
		ManageCustomersPage cusPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  cusPage);
		
		cusPage.open();
		cusPage.logout();
		
		LoginPage loginPage = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  loginPage);
		
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		HomePage homePage = new HomePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  homePage);
		homePage.open();
		
		assertTrue(homePage.checkIfAtHomePage());
		
		assertTrue(homePage.checkVisitilityForCusDR(userName));
		
		homePage.logout();				
	}
	
	/**
	 * Test Case ID: ACLV000D
	 * Test Description: Check ACLV for Picarro Administrator role, default Administrator account
	 * 
	 */
	@Test
	public void ACLV000D() {
		System.out.println("\nRunning ACLV000D - Test Description: Check ACLV for Picarro Administrator role, default Administrator account");
		
		HomePage homePage = new HomePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  homePage);
		
		homePage.open();
		
		if (driver.getTitle().equalsIgnoreCase("Login")) {
			LoginPage loginPage = new LoginPage(driver, baseURL, testSetup);
			PageFactory.initElements(driver,  loginPage);
			
			loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		}
		
		assertTrue(homePage.checkVisitilityForPicarroAdministrator("Administrator"));
		
		homePage.logout();
	}
	
	/**
	 * Test Case ID: ACLV000E
	 * Test Description: Check ACLV for Picarro Administrator role, non-default Administrator account
	 * 
	 */
	@Test
	public void ACLV000E() {
		String userName = PICNAMEPREFIX + "ad" + testSetup.getRandomNumber() + REGBASEPICUSERNAME;
		
		System.out.println("\nRunning ACLV000E - Test Description: Check ACLV for Picarro Administrator role, non-default Administrator account\n");
		
		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		manageUsersPage.open();
		
		if (driver.getTitle().equalsIgnoreCase("Login")) {
			LoginPage loginPage = new LoginPage(driver, baseURL, testSetup);
			PageFactory.initElements(driver,  loginPage);
			
			loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		}
		
		manageUsersPage.addNewPicarroUser(userName, USERPASSWORD, USERROLEADMIN);
		
		System.out.format("\nThe customer name is: %s, the userName is: %s, the role is: %s\n", "Picarro", userName, USERROLEADMIN);
		
		if (!manageUsersPage.findExistingUser("Picarro", userName))
			fail("\nACLV000E: failed to create a non-default Picarro Administrator user.\n");
		
		manageUsersPage.logout();
			
		LoginPage loginPage = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  loginPage);
		
		loginPage.loginNormalAs(userName, USERPASSWORD);		
		
		HomePage homePage = new HomePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  homePage);
		
		homePage.open();
		
		assertTrue(homePage.checkVisitilityForPicarroAdministrator(userName));
		
		homePage.logout();
	}
	
	/**
	 * Test Case ID: ACLV000F
	 * Test Description: Check ACLV for Picarro user with Utility Administrator role
	 * 
	 */
	@Test
	public void ACLV000F() {
		String userName = PICNAMEPREFIX + "ua" + testSetup.getRandomNumber() + REGBASEPICUSERNAME;
		
		System.out.println("\nRunning ACLV000F - Test Description: Check ACLV for Picarro user with Utility Administrator role\n");
		
		ManageUsersPage mup = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  mup);
		mup.open();
		
		if (driver.getTitle().equalsIgnoreCase("Login")) {
			LoginPage lpg = new LoginPage(driver, baseURL, testSetup);
			PageFactory.initElements(driver,  lpg);
			lpg.open();
			lpg.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		}
		
		mup.open();
		mup.addNewPicarroUser(userName, USERPASSWORD, CUSUSERROLEUA);
		
		System.out.format("\nThe customer name is: %s, the userName is: %s, the role is: %s\n", "Picarro", userName, CUSUSERROLEUA);
		
		if (!mup.findExistingUser("Picarro", userName))
			fail("\nACLV000F: failed to create a Picarro user with Utility Administrator role.\n");
		
		mup.logout();
		
		LoginPage lpg = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  lpg);
		lpg.open();
		lpg.loginNormalAs(userName, USERPASSWORD);
		
		HomePage hpg = new HomePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  hpg);
		
		hpg.open();
		
		assertTrue(hpg.checkVisitilityForPicarroUA(userName));
		
		hpg.logout();
	}
	
	/**
	 * Test Case ID: ACLV000G
	 * Test Description: Check ACLV for Picarro user with Supervisor role
	 * 
	 */
	@Test
	public void ACLV000G() {
		String userName = PICNAMEPREFIX + "su" + testSetup.getRandomNumber() + REGBASEPICUSERNAME;
		
		System.out.println("\nRunning ACLV000G - Test Description: Check ACLV for Picarro user with Supervisor role\n");
		
		ManageUsersPage mup = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  mup);
		mup.open();
		
		if (driver.getTitle().trim().equalsIgnoreCase("Login")) {
			LoginPage lpg = new LoginPage(driver, baseURL, testSetup);
			PageFactory.initElements(driver,  lpg);
			lpg.open();
			lpg.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		}
		
		mup.open();
		mup.addNewPicarroUser(userName, USERPASSWORD, CUSUSERROLESU);
		
		System.out.format("\nThe customer name is: %s, the userName is: %s, the role is: %s\n", "Picarro", userName, CUSUSERROLESU);
		
		if (!mup.findExistingUser("Picarro", userName))
			fail("\nACLV000G: failed to create a Picarro user with Supervisor role.\n");
		
		mup.logout();
		
		LoginPage lpg = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  lpg);
		lpg.open();
		lpg.loginNormalAs(userName, USERPASSWORD);
		
		HomePage hpg = new HomePage (driver, baseURL, testSetup);
		PageFactory.initElements(driver,  hpg);
		
		hpg.open();
		
		assertTrue(hpg.checkVisitilityForPicarroSU(userName));
		
		hpg.logout();
	}
	
	/**
	 * Test Case ID: ACLV000H
	 * Test Description: Check ACLV for Picarro user with Driver role
	 * 
	 */
	@Test
	public void ACLV000H() {
		String userName = PICNAMEPREFIX + "dr" + testSetup.getRandomNumber() + REGBASEPICUSERNAME;
		
		System.out.println("\nRunning ACLV000H - Test Description: Check ACLV for Picarro user with Driver role\n");
		
		ManageUsersPage mup = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  mup);
		mup.open();
		
		if (driver.getTitle().trim().equalsIgnoreCase("Login")) {
			LoginPage lpg = new LoginPage(driver, baseURL, testSetup);
			PageFactory.initElements(driver,  lpg);
			lpg.open();
			lpg.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		}
		
		mup.open();
		mup.addNewPicarroUser(userName, USERPASSWORD, CUSUSERROLEDR);
		
		System.out.format("\nThe customer name is: %s, the userName is: %s, the role is: %s\n", "Picarro", userName, CUSUSERROLEDR);
		
		if (!mup.findExistingUser("Picarro", userName))
			fail("\nACLV000H: failed to create a Picarro user with Driver role.\n");
		
		mup.logout();
		
		LoginPage lpg = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  lpg);
		lpg.open();
		lpg.loginNormalAs(userName, USERPASSWORD);
		
		HomePage hpg = new HomePage (driver, baseURL, testSetup);
		PageFactory.initElements(driver,  hpg);
		
		hpg.open();
		
		assertTrue(hpg.checkVisitilityForPicarroDR(userName));
		
		hpg.logout();
	}	
}