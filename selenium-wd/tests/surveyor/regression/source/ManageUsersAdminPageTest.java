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

import common.source.Log;
import common.source.BaseHelper;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
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
		manageUsersAdminPage = new ManageUsersAdminPage(driver, baseURL,
				testSetup);
		PageFactory.initElements(driver, manageUsersAdminPage);
	}

	/**
	 * Test Case ID: TC438 Test Description: Customer Admin - add new user
	 */
	@Test
	public void TC438_CustAdmin_AddNewUser() {
		String userName = SQACUS + testSetup.getRandomNumber() + "custadm001"
				+ REGBASEUSERNAME;

		System.out
				.println("\nRunning - TC438 - Customer Admin - add new user\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		homePage.waitForPageLoad();
		homePage.getLinkCusAdmin().click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		homePage.getLinkAdminManageUsers().click();

		manageUsersAdminPage.addNewUser(userName, USERPASSWORD, SQACUSLOC,
				CUSUSERROLEDR, TIMEZONECTUA);

		assertTrue(manageUsersAdminPage.findExistingUser(SQACUSLOC, userName,
				CUSUSERROLEDR));
		manageUsersAdminPage.logout();

		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);

		assertTrue(homePage.checkIfAtHomePage());
	}

	/**
	 * Test Case ID: TC439 Test Description: Customer Admin - edit user
	 */
	@Test
	public void TC439_CustAdmin_EditUser() {
		String userName = SQACUS + testSetup.getRandomNumber() + "custadm002"
				+ REGBASEUSERNAME;

		System.out.println("\nRunning - TC439 - Customer Admin - edit user\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		homePage.waitForPageLoad();
		homePage.getLinkCusAdmin().click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		homePage.getLinkAdminManageUsers().click();

		manageUsersAdminPage.addNewUser(userName, USERPASSWORD, SQACUSLOC,
				CUSUSERROLEDR, TIMEZONECTUA);
		assertTrue(manageUsersAdminPage.findExistingUser(SQACUSLOC, userName,
				CUSUSERROLEDR));

		manageUsersAdminPage.editUser(userName, CUSUSERROLESU, TIMEZONEETUA,
				true);
		assertTrue(manageUsersAdminPage.findExistingUser(SQACUSLOC, userName,
				CUSUSERROLESU));
		assertTrue(manageUsersAdminPage.getUserRole(userName).equalsIgnoreCase(
				CUSUSERROLESU));
		assertTrue(manageUsersAdminPage.getUserStatus(userName)
				.equalsIgnoreCase(
						Resources.getResource(ResourceKeys.Constant_Enabled)));
	}

	/**
	 * Test Case ID: TC440 Test Description: Customer specific user can change
	 * its password
	 */
	@Test
	public void TC440_CustAdmin_ChangePwd() {
		String userName = SQACUS + testSetup.getRandomNumber() + "custadm003"
				+ REGBASEUSERNAME;

		System.out
				.println("\nRunning - TC440 - Customer specific user can change its password\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		manageUsersAdminPage.open();
		manageUsersAdminPage.addNewUser(userName, USERPASSWORD, SQACUSLOC,
				CUSUSERROLEDR, TIMEZONECTUA);

		assertTrue(manageUsersAdminPage.findExistingUser(userName));

		manageUsersAdminPage.logout();

		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		assertTrue(homePage.checkIfAtHomePage());

		manageUsersAdminPage.changeUserPassword(USERPASSWORD, USERPASSWORD
				+ "1");
		assertTrue(homePage.checkIfAtHomePage());
		manageUsersAdminPage.logout();

		loginPage.open();
		if (loginPage.loginNormalAs(userName, USERPASSWORD + "1") != null)
			assertTrue(homePage.checkIfAtHomePage());
		else
			fail("\nTestcase TC440 - failed to login by the new password.\n");
	}

	/**
	 * Test Case ID: TC441 Test Description: Customer Admin - Disabled User
	 */
	@Test
	public void TC441_CustAdmin_DisableUser() {
		String userName = SQACUS + testSetup.getRandomNumber() + "custadm004"
				+ REGBASEUSERNAME;

		System.out
				.println("\nRunning - TC441 - Test Description: Customer Admin - Disabled User\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		homePage.waitForPageLoad();
		homePage.getLinkCusAdmin().click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		homePage.getLinkAdminManageUsers().click();

		manageUsersAdminPage.addNewUser(userName, USERPASSWORD, SQACUSLOC,
				CUSUSERROLEDR, TIMEZONECTUA, false);

		Log.info("userName = " + userName);

		assertTrue(manageUsersAdminPage.findExistingUser(SQACUSLOC, userName,
				CUSUSERROLEDR));
		assertTrue(manageUsersAdminPage.getUserStatus(userName)
				.equalsIgnoreCase(
						Resources.getResource(ResourceKeys.Constant_Disabled)));
		manageUsersAdminPage.logout();

		loginPage.open();
		assertTrue(loginPage.loginNormalAs(userName, USERPASSWORD) == null);
	}

	/**
	 * Test Case ID: TC443 Test Description: Customer admin not allowed to
	 * create duplicate User
	 */
	@Test
	public void TC443_DuplicateUserCreationNotAllowed() {
		String userName = SQACUS + testSetup.getFixedSizePseudoRandomString(24)
				+ "_TC443" + REGBASEUSERNAME;

		Log.info("\nRunning - TC443 - Customer admin not allowed to create duplicate User\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		homePage.waitForPageLoad();
		homePage.getLinkCusAdmin().click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		homePage.getLinkAdminManageUsers().click();

		manageUsersAdminPage.addTestUser(userName, USERPASSWORD, USERPASSWORD);

		manageUsersAdminPage.open();
		String output = manageUsersAdminPage.addTestUser(userName,
				USERPASSWORD, USERPASSWORD);
		assertTrue(output.contains(DUPLICATIONERROR));
	}

	/**
	 * Test Case ID: TC444 Test Description: Add User - Password and Confirm
	 * Password values different
	 */
	@Test
	public void TC444_PwdValuesDiffNotAllowed() {
		String userName = SQACUS + testSetup.getRandomNumber() + "custadm007"
				+ REGBASEUSERNAME;

		System.out
				.println("\nRunning - TC444 - Test Description: Add User - Password and Confirm Password values different\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		homePage.waitForPageLoad();
		homePage.getLinkCusAdmin().click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		homePage.getLinkAdminManageUsers().click();

		assertTrue(manageUsersAdminPage.addTestUser(userName, USERPASSWORD,
				USERPASSWORD + "2").contains(PWVALUEERROR));
	}

	/**
	 * Test Case ID: TC445 Test Description: add user - invalid email
	 */
	@Test
	public void TC445_InvalidEmailAddressNotAllowed() {
		String userName1 = "rpitter@b";
		String userName2 = "rpitter@b.c";

		System.out
				.println("\nRunning - TC445 - Test Description: add user - invalid email address values\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		homePage.waitForPageLoad();
		homePage.getLinkCusAdmin().click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		homePage.getLinkAdminManageUsers().click();

		assertTrue(manageUsersAdminPage.addTestUser(userName1, USERPASSWORD,
				USERPASSWORD).equalsIgnoreCase(EMAILINVALID));

		manageUsersAdminPage.open();
		assertTrue(manageUsersAdminPage.addTestUser(userName2, USERPASSWORD,
				USERPASSWORD).equalsIgnoreCase(EMAILINVALID));
	}

	/**
	 * Test Case ID: TC446 Test Description: add user - blank required
	 */
	@Test
	public void TC446_BlankRequiredFields() {
		String userName = SQACUS + testSetup.getRandomNumber() + "custadm009"
				+ REGBASEUSERNAME;

		System.out
				.println("\nRunning - TC446 - Test Description: add user - blank required fields\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		homePage.waitForPageLoad();
		homePage.getLinkCusAdmin().click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		homePage.getLinkAdminManageUsers().click();

		assertTrue(manageUsersAdminPage.addTestUser("", USERPASSWORD,
				USERPASSWORD).contains(BLANKFIELDERROR));

		manageUsersAdminPage.open();
		assertTrue(manageUsersAdminPage.addTestUser(userName, "", USERPASSWORD)
				.contains(PWDSAMEVALUE));

		manageUsersAdminPage.open();
		assertTrue(manageUsersAdminPage.addTestUser(userName, USERPASSWORD, "")
				.contains(BLANKFIELDERROR));
	}

	/**
	 * Test Case ID: TC447 Test Description: Admin can change role, timezone and
	 * location of existing user
	 */
	@Test
	public void TC447_EditUserRoleTimezone() {
		String userName = SQACUS + testSetup.getRandomNumber() + "custadm010"
				+ REGBASEUSERNAME;

		System.out
				.println("\nRunning - TC447 - Test Description: Admin can change role, timezone and location of existing user\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		homePage.waitForPageLoad();
		homePage.getLinkCusAdmin().click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		homePage.getLinkAdminManageUsers().click();

		manageUsersAdminPage.addNewUser(userName, USERPASSWORD, SQACUSLOC,
				CUSUSERROLEDR, TIMEZONEPTUA);
		manageUsersAdminPage.editUser(userName, CUSUSERROLESU, TIMEZONEPTUA,
				false);

		assertTrue(manageUsersAdminPage.getUserRole(userName).equalsIgnoreCase(
				CUSUSERROLESU));

		assertTrue(manageUsersAdminPage.getUserStatus(userName)
				.equalsIgnoreCase(
						Resources.getResource(ResourceKeys.Constant_Disabled)));
	}

	/**
	 * Test Case ID: TC448 Test Description: More than 50 characters not allowed
	 * in email address field
	 */
	@Test
	public void TC448_LimitOnEmailAddress() {
		String userName = "_TC448_"
				+ testSetup.getFixedSizePseudoRandomString(34) + "@email.com";
		Log.info("\nRunning - TC448 - Test Description: More than 50 characters not allowed in email address field\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		homePage.waitForPageLoad();
		homePage.getLinkCusAdmin().click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		homePage.getLinkAdminManageUsers().click();

		String rtnMsg = manageUsersAdminPage.addTestUser(userName,
				USERPASSWORD, USERPASSWORD);

		assertTrue(rtnMsg.equals("") || rtnMsg.equalsIgnoreCase(EMAILTOOLONG));
	}

	/**
	 * Test Case ID: TC450_ManageUserAdminPagination Test Description:
	 * Pagination (Manage Users Customer Admin) Test Script: 10,25,50 and 100
	 * records selection on all Customer Administration screens Expected
	 * Results: Specified number of records will be listed in the table Future
	 * Improvement: validating on "Manage Users" pages for now and should check
	 * on other pages as well
	 */
	@Test
	public void TC450_ManageUserAdminPagination() {
		List<String> userNameList;
		String numTextString;
		String[] strList;
		int userNum = 0;

		System.out
				.println("\nRunning - TC450_ManageUserAdminPagination - Test Description: Pagination (Manage Users Customer Admin)\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		manageUsersAdminPage.open();
		manageUsersAdminPage.setPagination(PAGINATIONSETTING);

		userNameList = manageUsersAdminPage.getUserNameList(false, Integer.valueOf(PAGINATIONSETTING));

		assertTrue(userNameList.size() <= Integer.valueOf(PAGINATIONSETTING));

		numTextString = manageUsersAdminPage.getLabelPageTableInfo().getText()
				.trim();
		strList = numTextString.split(" ");
		userNum = Integer.parseInt(strList[3]);

		assertTrue(userNameList.size() == userNum);

		manageUsersAdminPage.open();
		manageUsersAdminPage.setPagination(PAGINATIONSETTING_25);

		userNameList = manageUsersAdminPage.getUserNameList(false, Integer.valueOf(PAGINATIONSETTING_25));

		assertTrue(userNameList.size() <= Integer.valueOf(PAGINATIONSETTING_25));

		numTextString = manageUsersAdminPage.getLabelPageTableInfo().getText()
				.trim();
		strList = numTextString.split(" ");
		userNum = Integer.parseInt(strList[3]);

		assertTrue(userNameList.size() == userNum);

		manageUsersAdminPage.open();
		manageUsersAdminPage.setPagination(PAGINATIONSETTING_50);

		userNameList = manageUsersAdminPage.getUserNameList(false, Integer.valueOf(PAGINATIONSETTING_50));

		assertTrue(userNameList.size() <= Integer.valueOf(PAGINATIONSETTING_50));

		numTextString = manageUsersAdminPage.getLabelPageTableInfo().getText()
				.trim();
		strList = numTextString.split(" ");
		userNum = Integer.parseInt(strList[3]);

		assertTrue(userNameList.size() == userNum);

		manageUsersAdminPage.open();
		manageUsersAdminPage.setPagination(PAGINATIONSETTING_100);

		userNameList = manageUsersAdminPage.getUserNameList(false, Integer.valueOf(PAGINATIONSETTING_100));

		assertTrue(userNameList.size() <= Integer.valueOf(PAGINATIONSETTING_100));

		numTextString = manageUsersAdminPage.getLabelPageTableInfo().getText()
				.trim();
		strList = numTextString.split(" ");
		userNum = Integer.parseInt(strList[3]);

		assertTrue(userNameList.size() == userNum);
	}
	
	/**
	 * Test Case ID: TC451 Test Description: Search valid user record
	 */
	@Test
	public void TC451_SearchValidUser() {
		System.out
				.println("\nRunning - TC451 - Test Description: Search valid user record\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		manageUsersAdminPage.open();
		assertTrue(manageUsersAdminPage.searchUser(SQACUSUA, SQACUSLOC,
				CUSUSERROLEUA, USERENABLED));
	}

	/**
	 * Test Case ID: TC452 Test Description: Search invalid user record
	 */
	@Test
	public void TC452_SearchInvalidUser() {
		String userName = SQACUS + testSetup.getRandomNumber() + "custadm015"
				+ REGBASEUSERNAME;

		System.out
				.println("\nRunning - TC452 - Test Description: Search invalid user record\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		homePage.waitForPageLoad();
		homePage.getLinkCusAdmin().click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		homePage.getLinkAdminManageUsers().click();

		manageUsersAdminPage.waitForPageLoad();
		manageUsersAdminPage.getInputSearch().sendKeys(userName + userName);
		manageUsersAdminPage.waitForPageToLoad();

		assertTrue(manageUsersAdminPage.getLabelNoMatchingSearch()
				.equalsIgnoreCase(NOMATCHINGSEARCH));
	}

	/**
	 * Test Case ID: TC453 Test Description: Sort user records based on attributes
	 * present
	 */
	@Test
	public void TC453_SortUserRecords() {
		List<String> list = new ArrayList<String>();

		System.out
				.println("\nRunning - TC453 - Test Description: Sort user records based on attributes present\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		homePage.waitForPageLoad();
		homePage.getLinkCusAdmin().click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		homePage.getLinkAdminManageUsers().click();
		manageUsersAdminPage.getTheadUserName().click();
		list = manageUsersAdminPage.getUserNameList(false);
		assertTrue(BaseHelper.isStringListSortedDes(list));

		manageUsersAdminPage.getTheadName().click();
		list = manageUsersAdminPage.getNameList(false);
		assertTrue(BaseHelper.isStringListSorted(list));
		manageUsersAdminPage.getTheadName().click();
		list = manageUsersAdminPage.getNameList(false);
		assertTrue(BaseHelper.isStringListSortedDes(list));

		manageUsersAdminPage.getTheadLocation().click();
		list = manageUsersAdminPage.getLocationList(false);
		assertTrue(BaseHelper.isStringListSorted(list));
		manageUsersAdminPage.getTheadLocation().click();
		list = manageUsersAdminPage.getLocationList(false);
		assertTrue(BaseHelper.isStringListSortedDes(list));

		manageUsersAdminPage.getTheadRoles().click();
		list = manageUsersAdminPage.getRolesList(false);
		assertTrue(BaseHelper.isStringListSorted(list));
		manageUsersAdminPage.getTheadRoles().click();
		list = manageUsersAdminPage.getRolesList(false);
		assertTrue(BaseHelper.isStringListSortedDes(list));

		manageUsersAdminPage.getTheadStatus().click();
		list = manageUsersAdminPage.getStatusList(false);
		assertTrue(BaseHelper.isStringListSorted(list));
		manageUsersAdminPage.getTheadStatus().click();
		list = manageUsersAdminPage.getStatusList(false);
		assertTrue(BaseHelper.isStringListSortedDes(list));
	}

	/**
	 * Test Case ID: TC468 Test Description: Reset customer user password as
	 * customer admin
	 */
	@Test
	public void TC468_CustAdmin_ResetPwd() {
		String userName = SQACUS + testSetup.getRandomNumber() + "custadm015"
				+ REGBASEUSERNAME;

		System.out
				.println("\nRunning - TC468 - Reset customer user password as customer admin\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		manageUsersAdminPage.open();
		manageUsersAdminPage.addNewUser(userName, USERPASSWORD, SQACUSLOC,
				CUSUSERROLEDR, TIMEZONECTUA);

		assertTrue(manageUsersAdminPage.findExistingUser(userName));

		manageUsersAdminPage.logout();

		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);

		assertTrue(homePage.checkIfAtHomePage());

		manageUsersAdminPage.logout();

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		manageUsersAdminPage.open();
		if (!manageUsersAdminPage.resetUserPassword(userName, USERPASSWORD
				+ "1"))
			fail("\nTestcase TC468 - failed to reset user password.\n");

		manageUsersAdminPage.logout();

		loginPage.open();
		if (loginPage.loginNormalAs(userName, USERPASSWORD + "1") != null)
			assertTrue(homePage.checkIfAtHomePage());
		else
			fail("\nTestcase TC468 - failed to login by the new password.\n");
	}
}