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
		
		if (debug) {
			System.out.format("The customer name is \"%s\" and the user name is \"%s\"\n", customerName, userName);
		}
		
		try {
			manageCustomersPage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			manageCustomersPage.addNewCustomer(customerName, eula);
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			manageUsersPage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			manageUsersPage.addNewUser(customerName, userName, USERPASSWORD, USERROLEADMIN);
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			assertTrue(manageUsersPage.findExistingUser(customerName, userName));
			
			manageUsersPage.logout();
			
			loginPage.open();
			HomePage homePage = loginPage.loginNormalAs(userName, USERPASSWORD);
			testSetup.slowdownInSeconds(10); //temporary solution for now
			
			assertTrue(homePage.checkIfAtHomePage());
		}
		catch (Exception e) {
			System.out.format("Exception on test case \"ADM013\": %s\n", e.getMessage());
		}
	}
}