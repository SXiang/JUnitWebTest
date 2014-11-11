/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ManageUsersAdminPage;
import surveyor.scommon.source.SurveyorBaseTest;

/**
 * @author zlu
 *
 */
public class ManageUsersAdminPageTest extends SurveyorBaseTest {
	private static ManageUsersAdminPage manageUsersAdminPage;
	
	@BeforeClass
	public static void setupManageUsersAdminPageTest() {
		manageUsersAdminPage = new ManageUsersAdminPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersAdminPage);
	}

	/**
	 * Test Case ID: CUSTADM001
	 * Test Description: add user
	 * Test Script: - On Home Page, click Administration -> Manage Users
				    - Click on 'Add New User' button
					- Provide required user details and click OK [E1]
					- Login to p-cubed with newly created user credentials [E2]
	 * Expected Results: E1. Admin user is navigated to Manage Users page and new user details are present in the table
						 E2. New user is able to login the application successfully and navigated to valid page
     * Future Improvement: 1. Create user by Default "Administrator", Picarro Administrator and Utility Administrator from both Picarro and customer
     * 					   2. Create users with different roles 
     * 					   3. Create users with different TimeZone
     * 					   4. Create users with the status "disabled" (non default)
	 */	
	@Test
	public void CUSTADM001() {
		String customerName = SQACUS;
		String userName = customerName + testSetup.getRandomNumber() + "custadm001" + REGBASEUSERNAME;
		
		System.out.println("\nRunning - CUSTADM001 - add user\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		homePage.getLinkCusAdmin().click();
		homePage.getLinkAdminManageUsers().click();
		
		manageUsersAdminPage.addNewUser(userName, USERPASSWORD, CUSUSERROLEDR, TIMEZONECTUA);
		
		assertTrue(manageUsersAdminPage.findExistingUser(SQACUS, userName, CUSUSERROLEDR));
		manageUsersAdminPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
	
		assertTrue(homePage.checkIfAtHomePage());
	}
	
	/**
	 * Test Case ID: CUSTADM002
	 * Test Description: edit user
	 * Test Script: - On Home Page, click Administration -> Manage Users
	     		    - Click on Edit link of the desired user
				    - Modify user details and click OK
	 * Expected Results: - User details are modified successfully 
     * Future Improvement:
	 */	
	@Test
	public void CUSTADM002() {
		String customerName = SQACUS;
		String userName = customerName + testSetup.getRandomNumber() + "custadm002" + REGBASEUSERNAME;
		
		System.out.println("\nRunning - CUSTADM002 - edit user\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		homePage.getLinkCusAdmin().click();
		homePage.getLinkAdminManageUsers().click();
		
		manageUsersAdminPage.addNewUser(userName, USERPASSWORD, CUSUSERROLEDR, TIMEZONECTUA);
		assertTrue(manageUsersAdminPage.findExistingUser(SQACUS, userName, CUSUSERROLEDR));
		
		manageUsersAdminPage.editUser(userName, CUSUSERROLESU, TIMEZONEETUA, true);
		assertTrue(manageUsersAdminPage.findExistingUser(SQACUS, userName, CUSUSERROLESU));
		assertTrue(manageUsersAdminPage.getUserRole(userName).equalsIgnoreCase(CUSUSERROLESU));
		assertTrue(manageUsersAdminPage.getUserStatus(userName).equalsIgnoreCase("Enabled"));
	}
}