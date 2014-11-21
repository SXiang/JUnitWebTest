/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import common.source.BaseHelper;
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
	
	/**
	 * Test Case ID: CUSTADM006
	 * Test Description: Customer admin not allowed to create duplicate User
	 * Test Script: - On Home Page, and click Administration -> Users
	 			    - Click on 'Add New User' button
				    - Provide user name as existing user's name and click OK
	 * Expected Results: Duplicate User creation not allowed
	 * Current implementation:
	 * Current Issue:
     * Future Improvement:
	 */	
	@Test
	public void CUSTADM006() {
		String customerName = SQACUS;
		String userName = customerName + testSetup.getRandomNumber() + "custadm006" + REGBASEUSERNAME;
		
		System.out.println("\nRunning - CUSTADM006 - Customer admin not allowed to create duplicate User\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		homePage.getLinkCusAdmin().click();
		homePage.getLinkAdminManageUsers().click();
		
		manageUsersAdminPage.addTestUser(userName,  USERPASSWORD, USERPASSWORD);
		assertTrue(manageUsersAdminPage.findExistingUser(userName));
		
		manageUsersAdminPage.open();
		assertTrue(manageUsersAdminPage.addTestUser(userName, USERPASSWORD, USERPASSWORD).contains(DUPLICATIONERROR));
	}
	
	/**
	 * Test Case ID: CUSTADM007
	 * Test Description: Add User - Password and Confirm Password values different
	 * Test Script: - On Home Page, and click Administration -> Users
					- Click on 'Add New User' button
					- Provide different values for Password and Confirm Password fields
	 * Expected Results: "Please enter the same value again." message should be displayed
	 * Current implementation:
	 * Current Issue:
     * Future Improvement:
	 */	
	@Test
	public void CUSTADM007() {
		String customerName = SQACUS;
		String userName = customerName + testSetup.getRandomNumber() + "custadm007" + REGBASEUSERNAME;
		
		System.out.println("\nRunning - CUSTADM007 - Test Description: Add User - Password and Confirm Password values different\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		homePage.getLinkCusAdmin().click();
		homePage.getLinkAdminManageUsers().click();
		
		assertTrue(manageUsersAdminPage.addTestUser(userName, USERPASSWORD, USERPASSWORD + "2").contains(PWVALUEERROR));
	}
	
	/**
	 * Test Case ID: CUSTADM008
	 * Test Description: add user - invalid email address values
	 * Test Script: - Login to the site and click Administration -> Users
					- Click on 'Add New User' button
					- Provide invalid email address (e.g. rpitter@b, rpitter, rpitter@b. , rpitter@b.c). Click OK
	 * Expected Results: "Please enter valid email address" message should be displayed
	 * Current implementation:
	 * Current Issue:
     * Future Improvement:
	 */	
	@Test
	public void CUSTADM008() {
		String userName1 = "rpitter@b";
		String userName2 = "rpitter@b.c";
		
		System.out.println("\nRunning - CUSTADM008 - Test Description: add user - invalid email address values\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		homePage.getLinkCusAdmin().click();
		homePage.getLinkAdminManageUsers().click();
		
		assertTrue(manageUsersAdminPage.addTestUser(userName1, USERPASSWORD, USERPASSWORD).contains(EMAILINVALID));
		
		manageUsersAdminPage.open();
		assertTrue(manageUsersAdminPage.addTestUser(userName2, USERPASSWORD, USERPASSWORD).contains(EMAILINVALID));
	}
	
	/**
	 * Test Case ID: CUSTADM009
	 * Test Description: add user - blank required fields
	 * Test Script: - Login to the site and click Administration -> Users
					- Click on 'Add New User' button
					- Keep Email Address, pwd and confirm pwd fields blank. Click OK
	 * Expected Results: "Please fill out this field." message should be displayed
	 * Current implementation:
	 * Current Issue:
     * Future Improvement:
	 */	
	@Test
	public void CUSTADM009() {
		String customerName = SQACUS;
		String userName = customerName + testSetup.getRandomNumber() + "custadm009" + REGBASEUSERNAME;
		
		System.out.println("\nRunning - CUSTADM009 - Test Description: add user - blank required fields\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		homePage.getLinkCusAdmin().click();
		homePage.getLinkAdminManageUsers().click();
		
		assertTrue(manageUsersAdminPage.addTestUser("", USERPASSWORD, USERPASSWORD).contains(BLANKFIELDERROR));
		
		manageUsersAdminPage.open();
		assertTrue(manageUsersAdminPage.addTestUser(userName, "", USERPASSWORD).contains(PWDSAMEVALUE));
		
		manageUsersAdminPage.open();
		assertTrue(manageUsersAdminPage.addTestUser(userName, USERPASSWORD, "").contains(BLANKFIELDERROR));
	}
	
	/**
	 * Test Case ID: CUSTADM010
	 * Test Description: Admin can change role of existing user
	 * Test Script: - On Home Page, and click Administration -> Users
					- Click on Edit link
					- Change the role of the user
	 * Expected Results: - Admin can successfully change the role of user
						 - Op Qual is optional field (invalid for the current product implementation)
	 * Current implementation:
	 * Current Issue:
     * Future Improvement:
	 */	
	@Test
	public void CUSTADM010() {
		String customerName = SQACUS;
		String userName = customerName + testSetup.getRandomNumber() + "custadm010" + REGBASEUSERNAME;
		
		System.out.println("\nRunning - CUSTADM010 - Test Description: Admin can change role of existing user\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		homePage.getLinkCusAdmin().click();
		homePage.getLinkAdminManageUsers().click();
		
		manageUsersAdminPage.addNewUser(userName, USERPASSWORD, CUSUSERROLEDR, TIMEZONEPTUA);		
		manageUsersAdminPage.editUser(userName, CUSUSERROLESU, TIMEZONEPTUA, true);
		
		assertTrue(manageUsersAdminPage.getUserRole(userName).equalsIgnoreCase(CUSUSERROLESU));
	}
	
	/**
	 * Test Case ID: CUSTADM011
	 * Test Description: More than 50 characters not allowed in email address field
	 * Test Script: - On Home Page, and click Administration -> Users
					- Click on 'Add New User' button
					- Provide more than 50 characters in Email Address field and click OK
	 * Expected Results: Please enter no more than 50 characters." message is displayed
	 * Current implementation:
	 * Current Issue:
     * Future Improvement:
	 */	
	@Test
	public void CUSTADM011() {
		String userName = "1111111111aaaaaaaaaa2222222222bbbbbbbbbb1@email.com";
		
		System.out.println("\nRunning - CUSTADM011 - Test Description: More than 50 characters not allowed in email address field\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		homePage.getLinkCusAdmin().click();
		homePage.getLinkAdminManageUsers().click();
		
		String rtnMsg = manageUsersAdminPage.addTestUser(userName, USERPASSWORD, USERPASSWORD);
		
		assertTrue(rtnMsg.equalsIgnoreCase(EMAILTOOLONG));
	}
	
	/**
	 * Test Case ID: CUSTADM014
	 * Test Description: Search valid user record
	 * Test Script: Provide valid user name in search box present on Users screen
	 * Expected Results: Searched user details are displayed
	 * Current implementation:   
	 * Current Issue:
     * Future Improvement:
	 */	
	@Test
	public void CUSTADM014() {
		String customerName = SQACUS;
		String userName = customerName + testSetup.getRandomNumber() + "custadm014" + REGBASEUSERNAME;
		
		System.out.println("\nRunning - CUSTADM014 - Test Description: Search valid user record\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		homePage.getLinkCusAdmin().click();
		homePage.getLinkAdminManageUsers().click();
		
		manageUsersAdminPage.addNewUser(userName, USERPASSWORD, CUSUSERROLEDR, TIMEZONEPTUA, false);
		manageUsersAdminPage.getInputSearch().sendKeys(userName);
		
		assertTrue(manageUsersAdminPage.findExistingUser(SQACUS, userName, CUSUSERROLEDR, false));
		assertTrue(manageUsersAdminPage.getUserStatus(userName).equalsIgnoreCase(USERDISABLED) );
	}
	
	/**
	 * Test Case ID: CUSTADM015
	 * Test Description: Search invalid user record
	 * Test Script: Provide invalid user name in search box present on Users screen 
	 * Expected Results: Message should be displayed : 'No matching records found' 
	 * Current implementation:   
	 * Current Issue:
     * Future Improvement:
	 */	
	@Test
	public void CUSTADM015() {
		String customerName = SQACUS;
		String userName = customerName + testSetup.getRandomNumber() + "custadm015" + REGBASEUSERNAME;
		
		System.out.println("\nRunning - CUSTADM015 - Test Description: Search invalid user record\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		homePage.getLinkCusAdmin().click();
		homePage.getLinkAdminManageUsers().click();
		
		manageUsersAdminPage.addNewUser(userName, USERPASSWORD, CUSUSERROLEDR, TIMEZONEPTUA, true);
		manageUsersAdminPage.getInputSearch().sendKeys(userName + userName);
		
		assertTrue(manageUsersAdminPage.getLabelNoMatchingSearch().getText().trim().equalsIgnoreCase(NOMATCHINGSEARCH));
	}
	
	/**
	 * Test Case ID: CUSTADM016
	 * Test Description: Sort records based on attributes present
	 * Test Script: Sort records on all Administration screens
	 * Expected Results: User is able to sort the list of records based on specifed attribute
	 * Current implementation:   
	 * Current Issue:
     * Future Improvement: validate on more pages and have the relative sorting check covered
	 */	
	@Test
	public void CUSTADM016() {
		List<String> list = new ArrayList<String>();
		
		System.out.println("\nRunning - CUSTADM016 - Test Description: Sort records based on attributes present\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		homePage.getLinkCusAdmin().click();
		homePage.getLinkAdminManageUsers().click();
		
		manageUsersAdminPage.getTheadUserName().click();
		list = manageUsersAdminPage.getUserNameList(false);
		
		assertTrue(BaseHelper.isStringListSorted(list));
		
		manageUsersAdminPage.getTheadUserName().click();
		list = manageUsersAdminPage.getUserNameList(false);
		
		assertTrue(BaseHelper.isStringListSortedDes(list));
		
		manageUsersAdminPage.open();
		
		manageUsersAdminPage.getTheadRoles().click();
		list = manageUsersAdminPage.getRolesList(false);
		
		assertTrue(BaseHelper.isStringListSorted(list));
		
		manageUsersAdminPage.getTheadRoles().click();
		list = manageUsersAdminPage.getRolesList(false);
		
		assertTrue(BaseHelper.isStringListSortedDes(list));
	}
}