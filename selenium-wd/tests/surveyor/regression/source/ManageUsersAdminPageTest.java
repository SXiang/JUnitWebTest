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
	
	/**
	 * Test Case ID: CUSTADM003
	 * Test Description: Customer specific user can change its password
	 * Test Script: - On Home Page, click on Reset Password
	 *				- Change the user password
	 *				- Login with modified user password
	 * Expected Results: - Message should be displayed as : User password modified successfully
	 *					 - User can login the application successfully
	 * Current implementation: User will be brought to the "ManageUsers" page after clicking "OK" button on resetting 
	 * 						   password without message displayed as "User password modified successfully"
	 * Current Issue: New password can be reset to the same as the current one
     * Future Improvement:
	 */	
	@Test
	public void CUSTADM003() {
		String customerName = SQACUS;
		String userName = customerName + testSetup.getRandomNumber() + "custadm003" + REGBASEUSERNAME;
		
		System.out.println("\nRunning - CUSTADM003 - Customer specific user can change its password\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		manageUsersAdminPage.open();
		manageUsersAdminPage.addNewUser(userName, USERPASSWORD, CUSUSERROLEDR, TIMEZONECTUA);
		
		assertTrue(manageUsersAdminPage.findExistingUser(userName));
		
		manageUsersAdminPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		assertTrue(homePage.checkIfAtHomePage());
		
		manageUsersAdminPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		manageUsersAdminPage.open();
		if (!manageUsersAdminPage.resetUserPassword(userName, USERPASSWORD + "1"))
			fail("\nTestcase CUSTADM003 - failed to reset user password.\n");
		
		manageUsersAdminPage.logout();
		
		loginPage.open();
		if (loginPage.loginNormalAs(userName, USERPASSWORD + "1") != null)
			assertTrue(homePage.checkIfAtHomePage());
		else
			fail("\nTestcase CUSTADM003 - failed to login by the new password.\n");
	}
	
	/**
	 * Test Case ID: CUSTADM004
	 * Test Description: Disabled User
	 * Test Script: - On Home Page, and click Administration -> Users
				    - Click on 'Add New User' button
					- Provide user details and check the Disabled checkbox. Click OK
	 * Expected Results: - Disabled User will not be able login the application
	 * Current implementation:
	 * Current Issue:
     * Future Improvement: Adding user with disabled status by Picarro default Admin, Picarro Admin and Picarro Utility Admin
	 */	
	@Test
	public void CUSTADM004() {
		String customerName = SQACUS;
		String userName = customerName + testSetup.getRandomNumber() + "custadm004" + REGBASEUSERNAME;
		
		System.out.println("\nRunning - CUSTADM004 - Test Description: Disabled User\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		homePage.getLinkCusAdmin().click();
		homePage.getLinkAdminManageUsers().click();
		
		manageUsersAdminPage.addNewUser(userName, USERPASSWORD, CUSUSERROLEDR, TIMEZONECTUA, false);
		assertTrue(manageUsersAdminPage.findExistingUser(SQACUS, userName, CUSUSERROLEDR));
		
		manageUsersAdminPage.logout();
		
		loginPage.open();
		assertTrue(loginPage.loginNormalAs(userName, USERPASSWORD) == null);
	}
}