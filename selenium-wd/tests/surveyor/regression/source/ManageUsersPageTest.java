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
	 * Test Description: Adding User
	 * 
	 */	
	@Test
	public void ADM013() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "ADM013";
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + REGBASEUSERNAME;
		
		System.out.println("\nRunning ADM013...");
		
		System.out.println("\nStill working on it and will be ready soon\n");
	}
//		
//		if (debug) {
//			System.out.format("\nThe customer name is \"%s\" and the user name is \"%s\"\n", customerName, userName);
//		}
//		
//		try {
//			manageCustomersPage.open();
//			
//			if (debug)
//				testSetup.slowdownInSeconds(3);
//			
//			manageCustomersPage.addNewCustomer(customerName, eula);
//			
//			if (debug)
//				testSetup.slowdownInSeconds(3);
//			
//			manageUsersPage.open();
//			
//			if (debug)
//				testSetup.slowdownInSeconds(3);
//			
//			manageUsersPage.addNewCustomerUser(customerName, userName, USERROLEADMIN, USERROLEADMIN);
//			
//			(customerName, userName, USERPASSWORD, USERROLEADMIN);
//			
//			if (debug)
//				testSetup.slowdownInSeconds(3);
//			
//			assertTrue(manageUsersPage.findExistingUser(customerName, userName));
//			
//			//tempoary comment out the following code and will be add back after have the roles defined
////			manageUsersPage.logout();
////			
////			loginPage.open();
////			HomePage homePage = loginPage.loginNormalAs(userName, USERPASSWORD);
////			
////			assertTrue(homePage.checkIfAtHomePage());
//			
//			if (debug)
//				testSetup.slowdownInSeconds(3);
//		}
//		catch (Exception e) {
//			System.out.format("Exception on test case \"ADM013\": %s\n", e.getMessage());
//		}
//	}
}