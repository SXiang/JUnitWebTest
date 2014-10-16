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
	
	public ACLandVisibilityTest() {
		
	}
	
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
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageCustomersPage.open();
		
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		assertTrue(manageCustomersPage.findExistingCustomer(customerName));
		
		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		manageUsersPage.open();
		
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEUA);
		
		assertTrue(manageUsersPage.findExistingUser(customerName, userName));
		
		ManageCustomersPage cusPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  cusPage);
		
		cusPage.open();
		cusPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
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
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageCustomersPage.open();
		
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		assertTrue(manageCustomersPage.findExistingCustomer(customerName));

		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		manageUsersPage.open();
		
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLESU);
		
		assertTrue(manageUsersPage.findExistingUser(customerName, userName));
		
		ManageCustomersPage cusPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  cusPage);
		
		cusPage.open();
		cusPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
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
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageCustomersPage.open();
		
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		assertTrue(manageCustomersPage.findExistingCustomer(customerName));
		
		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		manageUsersPage.open();
		
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEDR);
		
		assertTrue(manageUsersPage.findExistingUser(customerName, userName));
		
		ManageCustomersPage cusPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  cusPage);
		
		cusPage.open();
		cusPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
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
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		homePage.open();
		
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
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		manageUsersPage.open();
		
		manageUsersPage.addNewPicarroUser(userName, USERPASSWORD, USERROLEADMIN);
		
		if (!manageUsersPage.findExistingUser("Picarro", userName))
			fail("\nACLV000E: failed to create a non-default Picarro Administrator user.\n");
		
		manageUsersPage.logout();
			
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);		
		
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
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		ManageUsersPage mup = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  mup);
		
		mup.open();
		mup.addNewPicarroUser(userName, USERPASSWORD, CUSUSERROLEUA);
		
		if (!mup.findExistingUser("Picarro", userName))
			fail("\nACLV000F: failed to create a Picarro user with Utility Administrator role.\n");
		
		mup.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		homePage.open();
		
		assertTrue(homePage.checkVisitilityForPicarroUA(userName));
		
		homePage.logout();
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
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());			
		
		ManageUsersPage mup = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  mup);
		
		mup.open();
		mup.addNewPicarroUser(userName, USERPASSWORD, CUSUSERROLESU);
		
		if (!mup.findExistingUser("Picarro", userName))
			fail("\nACLV000G: failed to create a Picarro user with Supervisor role.\n");
		
		mup.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		homePage.open();
		
		assertTrue(homePage.checkVisitilityForPicarroSU(userName));
		
		homePage.logout();
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
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		ManageUsersPage mup = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  mup);
		
		mup.open();
		mup.addNewPicarroUser(userName, USERPASSWORD, CUSUSERROLEDR);
		
		if (!mup.findExistingUser("Picarro", userName))
			fail("\nACLV000H: failed to create a Picarro user with Driver role.\n");
		
		mup.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		homePage.open();
		
		assertTrue(homePage.checkVisitilityForPicarroDR(userName));
		
		homePage.logout();
	}	
}