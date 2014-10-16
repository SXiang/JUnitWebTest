/**
 * 
 */
package surveyor.regression.source;

import org.junit.Test;

import static org.junit.Assert.*;

import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.SurveyorBaseTest;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class ManageUsersPageTest extends SurveyorBaseTest {
	private static ManageUsersPage manageUsersPage;
	private HomePage homePage;

	public ManageUsersPageTest() {
		manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		
		homePage = new HomePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  homePage);
	}
	
//	/**
//	 * Test Case ID: ADM000
//	 * Test Description: skipping for now for some special test case here later
//	 * 
//	 */
//	@Test
//	public void ADM000() {
//		System.out.println("\nRunning ADM00 and skipping for now for some special test case here later...");
//	}	
	
	/**
	 * Test Case ID: ADM013
	 * Test Description: Adding a customer and a User with Utility Administrator role
	 * 
	 */	
	@Test
	public void ADM013() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "adm013";
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + REGBASEUSERNAME;
		
		System.out.println("\nRunning ADM013 - Test Description: Adding a customer and a User with Utility Administrator role");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEUA);
		
		assertTrue(manageUsersPage.findExistingUser(customerName, userName));
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		assertTrue(homePage.checkIfAtHomePage());
	}

	@Test
	/**
	 * Test Case ID: ADM013A
	 * Test Description: Adding New Customer User - Supervisor Role, Driver Role, Utility Admin Role
	 * 
	 */
	public void ADM013A(){
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "sk001";
		String eula = customerName + ":" + EULASTRING;
		String newCustomerName = customerName + "new";
		String adminUser = testSetup.getLoginUser();
		String adminPwd = testSetup.getLoginPwd();
		String userName_su = customerName+".su" + REGBASEPICUSERNAME;
		String userName_dr = customerName+".dr" + REGBASEPICUSERNAME;
		String userName_ua = customerName+".ua" + REGBASEPICUSERNAME;
		
		System.out.println("\nRunning ADM013A - Test Description: Adding New Customer User with supervisor Role");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageCustomersPage.open();

		manageCustomersPage.addNewCustomer(newCustomerName, eula);
					
		assertTrue(manageCustomersPage.findExistingCustomer(newCustomerName));
		
		manageUsersPage.open();
				
		manageUsersPage.addNewCustomerUser(newCustomerName, userName_su, USERPASSWORD, CUSUSERROLESU);
		
		assertTrue(manageUsersPage.findExistingUser(newCustomerName, userName_su));
		
		manageUsersPage.logout();
		
		loginPage.loginNormalAs(userName_su, USERPASSWORD);
		
		HomePage skuser = new HomePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  skuser);
		
		System.out.println("\n Verifying Customer user login  \t"+userName_su);
		
		skuser.open();
		
		assertTrue(skuser.checkIfAtHomePage());
		
		skuser.logout();
		
		System.out.println("\nRunning ADM013A - Test Description: Adding New Customer User with Driver Role");
				
		
		loginPage.loginNormalAs(adminUser,adminPwd);
		
		manageUsersPage.open();
		
		manageUsersPage.addNewCustomerUser(newCustomerName, userName_dr, USERPASSWORD, CUSUSERROLEDR);
		
		assertTrue(manageUsersPage.findExistingUser(newCustomerName, userName_dr));
		
		manageUsersPage.logout();
		
		loginPage.loginNormalAs(userName_dr, USERPASSWORD);
		
		System.out.println("\n Verifying Customer user login: \t"+userName_dr);
		
		skuser.open();
		
		assertTrue(skuser.checkIfAtHomePage());
		
		skuser.logout();
		
		System.out.println("\nRunning ADM013A - Test Description: Adding New Customer User with Utility Administrator Role");
				
		loginPage.loginNormalAs(adminUser,adminPwd);
		
		manageUsersPage.open();
		
		manageUsersPage.addNewCustomerUser(newCustomerName, userName_ua, USERPASSWORD, CUSUSERROLEDR);
		
		assertTrue(manageUsersPage.findExistingUser(newCustomerName, userName_ua));
		
		manageUsersPage.logout();
		
		loginPage.loginNormalAs(userName_ua, USERPASSWORD);
		
		System.out.println("\n Verifying Customer user login:  \t"+userName_ua);
		
		skuser.open();
		
		assertTrue(skuser.checkIfAtHomePage());
		
		skuser.logout();
	}
}