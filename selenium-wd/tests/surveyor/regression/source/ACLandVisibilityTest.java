/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.SurveyorBaseTest;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class ACLandVisibilityTest extends SurveyorBaseTest {
	private static ManageCustomersPage manageCustomersPage;
	
	@BeforeClass
	public static void setupACLandVisibilityTest() {
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
	}
	
	/**
	 * Test Case ID: TC35
	 * Test Description: Check ACLV for customer user with Driver role
	 * 
	 */
	@Test
	public void TC35() {
		String customerName = SQACUS;
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + testSetup.getFixedSizeRandomNumber(8) + REGBASEUSERNAME;
		String location = customerName + " - " + SQACUSLOC;
		
		System.out.println("\nRunning TC35 - Test Description: Check ACLV for customer user with Driver role");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEDR, location);
		assertTrue(manageUsersPage.findExistingUser(customerName, userName));
		
		ManageCustomersPage manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
		manageCustomersPage.open();
		manageCustomersPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		homePage.open();
		assertTrue(homePage.checkIfAtHomePage());
		assertTrue(homePage.checkVisibilityForCusDR(userName));
		homePage.logout();				
	}
	
	/**
	 * Test Case ID: TC36
	 * Test Description: Check ACLV for customer user with Supervisor role
	 * 
	 */
	@Test
	public void TC36() {
		String customerName = SQACUS;
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + testSetup.getFixedSizeRandomNumber(8) + REGBASEUSERNAME;
		String location = customerName + " - " + SQACUSLOC;
		
		System.out.println("\nRunning TC36 - Test Description: Check ACLV for customer user with Supervisor role");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLESU,location);
		assertTrue(manageUsersPage.findExistingUser(customerName, userName));
		
		ManageCustomersPage manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
		manageCustomersPage.open();
		manageCustomersPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		homePage.open();
		assertTrue(homePage.checkIfAtHomePage());
		assertTrue(homePage.checkVisibilityForCusSU(userName));
		homePage.logout();				
	}

	/**
	 * Test Case ID: TC37
	 * Test Description: Check ACLV for customer user with Utility Administrator role
	 * 
	 */
	@Test
	public void TC37() {
		String customerName = SQACUS;
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + testSetup.getFixedSizeRandomNumber(8) + REGBASEUSERNAME;
		String location = customerName + " - " + SQACUSLOC;
		
		System.out.println("\nRunning TC37 - Test Description: Check ACLV for customer user with Utility Administrator role");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEUA, location);
		assertTrue(manageUsersPage.findExistingUser(customerName, userName));
		
		ManageCustomersPage manageCustomerPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomerPage);
		
		manageCustomerPage.open();
		manageCustomerPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		homePage.open();		
		assertTrue(homePage.checkIfAtHomePage());
		assertTrue(homePage.checkVisibilityForCusUA(userName));
		homePage.logout();
	}

	/**
	 * Test Case ID: TC38
	 * Test Description: Check ACLV for Picarro Administrator role, non-default Administrator account
	 * 
	 */
	@Test
	public void TC38() {
		String userName = PICNAMEPREFIX + "ad" + testSetup.getRandomNumber() + REGBASEPICUSERNAME;
		
		System.out.println("\nRunning TC38 - Test Description: Check ACLV for Picarro Administrator role, non-default Administrator account\n");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		manageUsersPage.open();		
		manageUsersPage.addNewPicarroUser(userName, USERPASSWORD, USERROLEADMIN);		
		if (!manageUsersPage.findExistingUser("Picarro", userName))
			fail("\nTC38: failed to create a non-default Picarro Administrator user.\n");		
		manageUsersPage.logout();
			
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);		
		
		homePage.open();		
		assertTrue(homePage.checkVisibilityForPicarroAdministrator(userName));		
		homePage.logout();
	}
	
	/**
	 * Test Case ID: TC40
	 * Test Description: Check ACLV for Picarro user with Picarro Support role
	 * 
	 */
	@Test
	public void TC40() {
		String userName = PICNAMEPREFIX + "su" + testSetup.getRandomNumber() + REGBASEPICUSERNAME;
		
		System.out.println("\nRunning TC40 - Test Description: Check ACLV for Picarro user with Picarro Support role\n");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		
		manageUsersPage.open();
		manageUsersPage.addNewPicarroUser(userName, USERPASSWORD, PICUSERROLESUP);		
		if (!manageUsersPage.findExistingUser("Picarro", userName))
			fail("\nTC40: failed to create a Picarro user with Picarro Support role.\n");		
		manageUsersPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		homePage.open();		
		assertTrue(homePage.checkVisibilityForPicarroSUP(userName));		
		homePage.logout();
	}
}