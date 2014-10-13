/**
 * 
 */
package surveyor.regression.source;

import org.junit.Test;

import static org.junit.Assert.*;

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
public class ManageUsersPageTest extends SurveyorBaseTest {
	private static ManageCustomersPage manageCustomersPage;
	private static ManageUsersPage manageUsersPage;
	private static LoginPage loginPage;
	
	public ManageUsersPageTest() {
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
		
		manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		
		loginPage = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  loginPage);
	}
	
	/**
	 * Test Case ID: ADM000
	 * Test Description: skipping for now for some special test case here later
	 * 
	 */
	@Test
	public void ADM000() {
		System.out.println("\nRunning ADM00 and skipping for now for some special test case here later...");
	}	
	
	/**
	 * Test Case ID: ADM013
	 * Test Description: Adding User, utility admin role
	 * 
	 */	
	@Test
	public void ADM013() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "adm013";
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + REGBASEUSERNAME;
		
		System.out.println("\nRunning ADM013 - Test Description: Adding User, utility admin role");
		
		manageCustomersPage.open();
		
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageUsersPage.open();
		
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEUA);
		
		assertTrue(manageUsersPage.findExistingUser(customerName, userName));
			
		manageUsersPage.logout();
		
		loginPage.open();
		HomePage homePage = loginPage.loginNormalAs(userName, USERPASSWORD);
		
		assertTrue(homePage.checkIfAtHomePage());
	}
}