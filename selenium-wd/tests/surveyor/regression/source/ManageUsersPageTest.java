/**
 *
 */
package surveyor.regression.source;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.CryptoUtility;
import common.source.Log;
import common.source.TestContext;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataprovider.RunAs;
import surveyor.dataprovider.UserDataProvider;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ManageUsersPageTest extends SurveyorBaseTest {
	private static final String FN_TC71_TC473_USER_RESET_PWD = "TC71_TC473_User_ResetPwd";
	private static final String SQAPICAD_AND_SQAPICSUP = "sqapicad@picarro.com,sqapicsup@picarro.com";

	private static ManageUsersPage manageUsersPage;
	private static ManageCustomersPage manageCustomersPage;
	private static ManageLocationsPage manageLocationsPage;
	private static HomePage homePage;
	private static LoginPage loginPage;

	private enum ManageUserTestCaseType {
		ResetPwd, DuplicateUser, DisabledUser, SearchValidUser, MaxEmailChars, ReEnableUsers, EditUser, ChangeRoleLocTz, AddCustUser, AddPicUser
	}

	/**
	 * This method is called by the 'main' thread
	 */
	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects(); // ensures TestSetup and TestContext are initialized before Page object creation.
	}

	/**
	 * This method is called by the 'worker' thread
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void beforeTest() throws Exception {
		initializeTestObjects();

		PageObjectFactory pageObjectFactory = new PageObjectFactory();

		homePage = pageObjectFactory.getHomePage();
		PageFactory.initElements(getDriver(), homePage);

		loginPage = pageObjectFactory.getLoginPage();
		PageFactory.initElements(getDriver(), loginPage);

		manageUsersPage = pageObjectFactory.getManageUsersPage();
		PageFactory.initElements(getDriver(), manageUsersPage);

		manageCustomersPage = pageObjectFactory.getManageCustomersPage();
		PageFactory.initElements(getDriver(), manageCustomersPage);

		manageLocationsPage = pageObjectFactory.getManageLocationsPage();
		PageFactory.initElements(getDriver(), manageLocationsPage);
	}

	/**
	 * Test Case ID: TC1675: More than 15 characters not allowed in New password and confirm password fields on Change User Password page Script: - On Home Page, and click UserName -> Change Password
	 * - Provide more than 15 characters in New Password and Confirm Password fields and click OK Results: - "Please enter no more than 15 characters." message is displayed to user
	 */
	@Test
	public void TC1675_MaxPasswordLength_PicUser() {
		String password = USERPASSWORD;
		String password_16 = password + "abc"; // 16 chars
		String password_80 = password_16 + password_16 + password_16 + password_16 + password_16;
		String errorMsg = PASSWORDTOOLONG;

		String userName = PICNAMEPREFIX + "dr" + getTestSetup().getRandomNumber() + REGBASEPICUSERNAME;
		String customerName = "Picarro";
		String location = "Santa Clara";
		String locationDesc = customerName + " - " + location;

		// *** Add a new user for this test ***
		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		manageUsersPage.open();
		manageUsersPage.addNewPicarroUser(userName, USERPASSWORD, CUSUSERROLEDR, locationDesc, TIMEZONECT);
		loginPage = manageUsersPage.logout();

		// *** Start test ***

		Log.info("\nRunning - TC1675_MaxPasswordLength_PicUser - " + "Test Description: More than 15 characters not allowed in New password and confirm password fields\n");

		loginPage.open();
		loginPage.loginNormalAs(userName, password);

		manageUsersPage.changeUserPassword(password, password_16);
		assertEquals(manageUsersPage.getNewPasswordError(), errorMsg);
		assertEquals(manageUsersPage.getConfirmPasswordError(), errorMsg);

		manageUsersPage.changeUserPassword(password, password_80);
		assertEquals(manageUsersPage.getNewPasswordError(), errorMsg);
		assertEquals(manageUsersPage.getConfirmPasswordError(), errorMsg);

	}

	/**
	 * Test Case ID: TC68 Test Description: Picarro Admin - Add New Picarro User
	 *
	 */
	@Test
	@UseDataProvider(value = "dataProviderPicarroAdminSupportRoleInfo", location = UserDataProvider.class)
	@RunAs(users = SQAPICAD_AND_SQAPICSUP)
	public void TC68_TC470_AddNewPicarroUser(String user, String pwd) {
		String tcID = getTestCaseName(ManageUserTestCaseType.AddPicUser, user);
		String password = new CryptoUtility().decrypt(pwd);
		String userName = "PicarroUser" + getTestSetup().getRandomNumber() + tcID + REGBASEUSERNAME;
		String customer = "Picarro";
		String location = "Santa Clara";
		String locationDesc = customer + " - " + location;

		Log.info("\nRunning " + tcID + " - Test Description: Picarro Admin - Add New Picarro user");

		loginPage.open();
		loginPage.loginNormalAs(user, password);

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewPicarroUser(userName, USERPASSWORD, CUSUSERROLEUA, locationDesc, TIMEZONECT);

		assertTrue(manageUsersPage.findExistingUser(location, userName, false));
		loginPage = manageUsersPage.logout();

		loginPage.open();
		HomePage homePage = loginPage.loginNormalAs(userName, USERPASSWORD);
		assertTrue(homePage.checkIfAtHomePage());
	}

	/**
	 * Test Case ID: TC69_TC471 Test Description: Picarro Admin - Add New Customer User
	 *
	 */
	@Test
	@UseDataProvider(value = "dataProviderPicarroAdminSupportRoleInfo", location = UserDataProvider.class)
	@RunAs(users = SQAPICAD_AND_SQAPICSUP)
	public void TC69_TC471_AddNewCustomerUser(String user, String pwd) {
		String tcID = getTestCaseName(ManageUserTestCaseType.AddCustUser, user);
		String password = new CryptoUtility().decrypt(pwd);
		String customerName = SQACUS;
		String userName = customerName + tcID + getTestSetup().getFixedSizePseudoRandomString(11) + REGBASEUSERNAME;
		String locationName = SQACUSLOC;

		Log.info("\nRunning " + tcID + " - Test Description: Picarro Admin - Add New Customer user");

		loginPage.open();
		loginPage.loginNormalAs(user, password);

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEUA, locationName);

		assertTrue(manageUsersPage.findExistingUser(locationName, userName, false));
		loginPage = manageUsersPage.logout();

		loginPage.open();
		HomePage homePage = loginPage.loginNormalAs(userName, USERPASSWORD);
		assertTrue(homePage.checkIfAtHomePage());
	}

	/**
	 * Test Case ID: TC70_TC472_EditUser_PicAdmin_PicSupport Script: - On Home Page, click Picarro Administration -> Manage Users - Click on Edit link - Modify user details and click OK Results: - -
	 * User details are modified successfully
	 */
	@Test
	@UseDataProvider(value = "dataProviderPicarroAdminSupportRoleInfo", location = UserDataProvider.class)
	@RunAs(users = SQAPICAD_AND_SQAPICSUP)
	public void TC70_TC472_EditUser_PicAdminSupport(String user, String pwd) {
		String tcID = getTestCaseName(ManageUserTestCaseType.EditUser, user);
		String password = new CryptoUtility().decrypt(pwd);
		String customerName = SQACUS;
		String userName = customerName + tcID + getTestSetup().getFixedSizePseudoRandomString(11) + REGBASEUSERNAME;
		String locationName = SQACUSLOC;

		Log.info("\nRunning " + tcID + " - Test Description: Picarro Admin - edit user");

		loginPage.open();
		loginPage.loginNormalAs(user, password);

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();

		Log.info(String.format("Adding new user: CustomerName=[%s]; Username=[%s]; Role=[%s]; Location=[%s]", customerName, userName, CUSUSERROLEUA, locationName));
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEUA, locationName);

		Log.info(String.format("Editing user: Username=[%s]; Role=[%s]; Timezone=[%s]; Enabled=[%b]; IsCustomerUser=[%b]", userName, CUSUSERROLESU, TIMEZONEET, true, false));
		manageUsersPage.editUser(userName, CUSUSERROLESU, TIMEZONEET, true, false);

		Log.info(String.format("Editing user: Location=[%s]; Username=[%s]; IsCustomerUser=[%b]", locationName, userName, false));
		assertTrue(manageUsersPage.findExistingUser(locationName, userName, false));
		loginPage = manageUsersPage.logout();

		loginPage.open();
		HomePage homePage = loginPage.loginNormalAs(userName, USERPASSWORD);
		assertTrue(homePage.checkIfAtHomePage());
	}

	/**
	 * Test Case IDs: TC71 Test Description: Reset user password as Picarro Admin. TC473 Test Description: Reset user password as Picarro Support.
	 */
	@Test
	@UseDataProvider(value = "dataProviderPicarroUserRoleInfo", location = UserDataProvider.class)
	@RunAs(users = SQAPICAD_AND_SQAPICSUP)
	public void TC71_TC473_User_ResetPwd(String username, String password, String role, String customerName, String customerLocation) {
		if (!isValidRunAsUser(username, FN_TC71_TC473_USER_RESET_PWD)) {
			return;
		}

		String testCaseID = getTestCaseName(ManageUserTestCaseType.ResetPwd, username);
		password = new CryptoUtility().decrypt(password);

		String usernameNew = SQACUS + getTestSetup().getRandomNumber() + testCaseID + REGBASEUSERNAME;
		Log.info(String.format("\nRunning - %s - Reset customer user password as %s \n", testCaseID, role));

		loginPage.open();
		loginPage.loginNormalAs(username, password);

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewCustomerUser(SQACUS, usernameNew, password, CUSUSERROLEDR, TIMEZONECT, SQACUSLOC);

		Log.info(String.format("Looking for user: Location-[%s]; Username-[%s]", SQACUSLOC, usernameNew));
		assertTrue(manageUsersPage.findExistingUser(SQACUSLOC, usernameNew, false));

		manageUsersPage.logout();

		loginPage.open();
		loginPage.loginNormalAs(usernameNew, password);

		assertTrue(homePage.checkIfAtHomePage());

		manageUsersPage.logout();

		loginPage.open();
		loginPage.loginNormalAs(username, password);

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		if (!manageUsersPage.resetUserPassword(usernameNew, password + "1", false))
			fail(String.format("\nTestcase %s - failed to reset user password.\n", testCaseID));

		manageUsersPage.logout();

		loginPage.open();
		if (loginPage.loginNormalAs(usernameNew, password + "1") != null)
			assertTrue(homePage.checkIfAtHomePage());
		else
			fail(String.format("\nTestcase %s - failed to login by the new password.\n", testCaseID));
	}

	/**
	 * Test Case ID: TC72 Test Description: Change password as Picarro admin
	 */
	@Test
	public void TC72_ChangePwd_PicAdmin() {
		String usernameNew = SQACUS + getTestSetup().getRandomNumber() + "TC72" + REGBASEUSERNAME;
		Log.info("\nRunning - TC72 - Change customer user password \n");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewCustomerUser(SQACUS, usernameNew, USERPASSWORD, CUSUSERROLEDR, TIMEZONECT, SQACUSLOC);

		Log.info(String.format("Looking for user: Location-[%s]; Username-[%s]", SQACUSLOC, usernameNew));
		assertTrue(manageUsersPage.findExistingUser(SQACUSLOC, usernameNew, false));

		manageUsersPage.logout();

		loginPage.open();
		loginPage.loginNormalAs(usernameNew, USERPASSWORD);
		assertTrue(homePage.checkIfAtHomePage());

		manageUsersPage.changeUserPassword(USERPASSWORD, USERPASSWORD + "1");
		assertTrue(homePage.checkIfAtHomePage());
		manageUsersPage.logout();

		loginPage.open();
		if (loginPage.loginNormalAs(usernameNew, USERPASSWORD + "1") != null)
			assertTrue(homePage.checkIfAtHomePage());
		else
			fail("\nTestcase TC72 - failed to login by the new password.\n");
	}

	/**
	 * Test Case ID: TC87_TC483_TC484_TC485_EditUserChangeRoleLocationTimezone_PicAdminSupport Script: - On Home Page, and click Picarro Administration -> Manage Users - Click on Edit link and change
	 * the role, timzone and location of existing user Results: - - Admin can chang the role of user - Admin can change time zone of the user - Admin can change location of the user
	 */
	@Test
	@UseDataProvider(value = "dataProviderPicarroAdminSupportRoleInfo", location = UserDataProvider.class)
	@RunAs(users = SQAPICAD_AND_SQAPICSUP)
	public void TC87_TC483_TC484_TC485_EditUserChangeRoleLocationTimezone_PicAdminSupport(String user, String pwd) {
		String tcID = getTestCaseName(ManageUserTestCaseType.ChangeRoleLocTz, user);
		String password = new CryptoUtility().decrypt(pwd);
		String customerName = SQACUS;
		String userName = customerName + "User" + tcID + REGBASEUSERNAME;
		String cityName = "Santa Clara";
		String cityNameNew = "Santa Clara2";
		String locationName = customerName + getTestSetup().getFixedSizePseudoRandomString(10) + "loc";
		String locationNameNew = locationName + "New";
		String locationNameNewDesc = customerName + " - " + locationNameNew;

		Log.info("\nRunning TC87_TC483_TC484_TC485 - Test Description: Picarro Admin Support - edit user - change role, location and timezone of existing user");

		loginPage.open();
		loginPage.loginNormalAs(user, password);

		manageLocationsPage.open();
		manageLocationsPage.waitForPageLoad();
		manageLocationsPage.addNewLocation(locationName, customerName, cityName);

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();

		Log.info(String.format("Creating new user - Customer=%s;Username=%s;Role=%s;Location=%s;Enabled=%b", customerName, userName, CUSUSERROLEUA, locationName, true));
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEUA, locationName);

		// Add another location for the customer.
		manageLocationsPage.open();
		manageLocationsPage.waitForPageLoad();
		manageLocationsPage.addNewLocation(locationNameNew, customerName, cityNameNew);

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();

		// Edit role, location and timezone
		Log.info(String.format("Editing user - Username=%s;Role=%s;Timezone=%s;Location=%s;Enabled=%b;IsCustomerUser=%b", userName, CUSUSERROLESU, TIMEZONEET, locationNameNew, true, false));
		manageUsersPage.editUser(userName, CUSUSERROLESU, TIMEZONEET, locationNameNewDesc, true, false);

		Log.info(String.format("Finding user - Location=%s;Username=%s;IsCustomerUser=%b", locationNameNew, userName, false));
		assertTrue(manageUsersPage.findExistingUser(locationNameNew, userName, false));
		loginPage = manageUsersPage.logout();

		loginPage.open();
		HomePage homePage = loginPage.loginNormalAs(userName, USERPASSWORD);
		assertTrue(homePage.checkIfAtHomePage());
	}

	/**
	 * Test Case IDs: TC90 Test Description: Pic admin not allowed to create duplicate User TC479 Test Description: Pic support not allowed to create duplicate User
	 */
	@Test
	@UseDataProvider(value = "dataProviderPicarroUserRoleInfo", location = UserDataProvider.class)
	@RunAs(users = SQAPICAD_AND_SQAPICSUP)
	public void TC90_TC479_DuplicateUserCreationNotAllowed(String username, String password, String role, String customerName, String customerLocation) {
		String testCaseID = getTestCaseName(ManageUserTestCaseType.ResetPwd, username);
		password = new CryptoUtility().decrypt(password);

		String usernameNew = SQACUS + getTestSetup().getFixedSizePseudoRandomString(12) + testCaseID + REGBASEUSERNAME;

		Log.info(String.format("\nRunning - %s - %s not allowed to create duplicate User\n", testCaseID, role));

		loginPage.open();
		loginPage.loginNormalAs(username, password);

		homePage.waitForPageLoad();

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addTestUser(usernameNew, USERPASSWORD, USERPASSWORD);

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		String output = manageUsersPage.addTestUser(usernameNew, USERPASSWORD, USERPASSWORD);
		Log.info("Found error message:" + output);
		Log.info("Looking for error message: " + DUPLICATIONERROR);
		assertTrue(output.contains(DUPLICATIONERROR));
	}

	/**
	 * Test Case IDs: TC94 Test Description: Picarro Admin - Disabled User TC474 Test Description: Picarro Support - Disabled User
	 */
	@Test
	@UseDataProvider(value = "dataProviderPicarroUserRoleInfo", location = UserDataProvider.class)
	@RunAs(users = SQAPICAD_AND_SQAPICSUP)
	public void TC94_TC474_DisableUser(String username, String password, String role, String customerName, String customerLocation) {
		String testCaseID = getTestCaseName(ManageUserTestCaseType.DisabledUser, username);
		password = new CryptoUtility().decrypt(password);

		String usernameNew = SQACUS + getTestSetup().getRandomNumber() + testCaseID + REGBASEUSERNAME;
		Log.info(String.format("\nRunning - %s - Test Description: %s - Disabled User\n", testCaseID, role));

		loginPage.open();
		loginPage.loginNormalAs(username, password);

		homePage.waitForPageLoad();

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewCustomerUser(SQACUS, usernameNew, password, CUSUSERROLEDR, SQACUSLOC, false);

		Log.info(String.format("Looking for user: Location-[%s]; Username-[%s]", SQACUSLOC, usernameNew));
		assertTrue(manageUsersPage.findExistingUser(SQACUSLOC, usernameNew, false));
		assertTrue(manageUsersPage.getUserStatus(usernameNew, false).equalsIgnoreCase(Resources.getResource(ResourceKeys.Constant_Disabled)));
		manageUsersPage.logout();

		loginPage.open();
		assertTrue(loginPage.loginNormalAs(usernameNew, USERPASSWORD) == null);
	}

	/**
	 * Test Case ID: TC95_TC478_ReEnableUser_PicAdminSupport Script: Pre-requisite: Create Disabled user - On Home Page, and click Picarro Administration -> Manage Users - Click on Edit button of
	 * disabled user - Enable the user and click OK Results: - - User should be able to log in the application
	 */
	@Test
	@UseDataProvider(value = "dataProviderPicarroAdminSupportRoleInfo", location = UserDataProvider.class)
	@RunAs(users = SQAPICAD_AND_SQAPICSUP)
	public void TC95_TC478_ReEnableUser_PicAdminSupport(String user, String pwd) {
		String tcID = getTestCaseName(ManageUserTestCaseType.ReEnableUsers, user);
		String password = new CryptoUtility().decrypt(pwd);
		String userName = SQACUS + getTestSetup().getRandomNumber() + tcID + REGBASEUSERNAME;
		String locationDesc = SQACUS + " - " + SQACUSLOC;

		Log.info("\nRunning - " + tcID + "_ReEnableUser_PicAdminSupport - Test Description: Picarro Admin - Re-Enable User\n");

		loginPage.open();
		loginPage.loginNormalAs(user, password);

		homePage.waitForPageLoad();

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();

		// create disabled user.
		Log.info(String.format("Creating new user - Customer=%s;Username=%s;Role=%s;Location=%s;Enabled=%b", SQACUS, userName, CUSUSERROLEDR, SQACUSLOC, false));
		manageUsersPage.addNewCustomerUser(SQACUS, userName, USERPASSWORD, CUSUSERROLEDR, SQACUSLOC, false);

		// enable the user
		assertTrue(manageUsersPage.editUser(userName, CUSUSERROLEDR, TIMEZONEET, locationDesc, true /* enable user */, false));
		manageUsersPage.logout();

		// verify user can login correctly
		loginPage.open();
		HomePage homePage = loginPage.loginNormalAs(userName, USERPASSWORD);
		assertTrue(homePage.checkIfAtHomePage());
	}

	/**
	 * Test Case ID: TC98_TC486_UserEmailMax50Chars_PicAdminSupport Script: - On Home Page, and click Picarro Administration -> Manage Users - Click on 'Add New User' button - Provide more than 50
	 * characters in Email Address field and click OK - Repeat the same steps for Edit user page Results: - - "Please enter no more than 50 characters." message is displayed
	 */
	@Test
	@UseDataProvider(value = "dataProviderPicarroAdminSupportRoleInfo", location = UserDataProvider.class)
	@RunAs(users = SQAPICAD_AND_SQAPICSUP)
	public void TC98_TC486_UserEmailMax50Chars_PicAdminSupport(String user, String pwd) {
		final int MAX_SIZE = 50;
		String tcID = getTestCaseName(ManageUserTestCaseType.MaxEmailChars, user);
		String password = new CryptoUtility().decrypt(pwd);
		String userName50 = SQACUS + getTestSetup().getFixedSizePseudoRandomString(MAX_SIZE - 16) + REGBASEUSERNAME;
		String userName51 = SQACUS + getTestSetup().getFixedSizePseudoRandomString(MAX_SIZE - 16) + REGBASEUSERNAME + "A";
		Log.info("\nRunning - " + tcID + "_UserEmailMax50Chars_PicAdmin - Test Description: Picarro Admin - " + "More than 50 characters not allowed in email address field\n");

		loginPage.open();
		loginPage.loginNormalAs(user, password);

		homePage.waitForPageLoad();

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();

		// Create user with 50 characters in email.
		Log.info(String.format("Creating new user - Customer=%s;Username=%s;Role=%s;Location=%s;Enabled=%b", SQACUS, userName50, CUSUSERROLEDR, SQACUSLOC, false));
		manageUsersPage.addNewCustomerUser(SQACUS, userName50, USERPASSWORD, CUSUSERROLEDR, SQACUSLOC, false);

		Log.info(String.format("Finding user - Location=%s;Username=%s;IsCustomerUser=%b", SQACUSLOC, userName50, false));
		assertTrue(manageUsersPage.findExistingUser(SQACUSLOC, userName50, false));

		// Create user with 51 characters in email.
		userName51 = SQACUS + getTestSetup().getFixedSizePseudoRandomString(MAX_SIZE - 20) + "TC98" + REGBASEUSERNAME + "A";
		Log.info(String.format("Creating new user - Customer=%s;Username=%s;Role=%s;Location=%s;Enabled=%b", SQACUS, userName51, CUSUSERROLEDR, SQACUSLOC, false));
		manageUsersPage.addNewCustomerUser(SQACUS, userName51, USERPASSWORD, CUSUSERROLEDR, SQACUSLOC, false);

		Log.info(String.format("Finding user - Location=%s;Username=%s;IsCustomerUser=%b", SQACUSLOC, userName51.substring(0, MAX_SIZE), false));
		assertTrue(manageUsersPage.findExistingUser(SQACUSLOC, userName51.substring(0, MAX_SIZE), false));

		Log.info(String.format("Finding user - Location=%s;Username=%s;IsCustomerUser=%b", SQACUSLOC, userName51, false));
		assertFalse(manageUsersPage.findExistingUser(SQACUSLOC, userName51, false));
	}

	/**
	 * Test Case ID: TC115 Test Description: Pagination (Manage Users) Test Script: 10,25,50 and 100 records selection on all Administration screens Expected Results: Specified number of records will
	 * be listed in the table Future Improvement: validating on "Manage Users" pages for now and should check on other pages as well
	 */
	@Test
	public void TC115_ManageUserPagination() {
		List<String> userNameList;
		String numTextString;
		String[] strList;
		int userNum = 0;
		int pageSize = 0;

		Log.info("\nRunning - TC115 - Test Description: Pagination (Manage Users)\n");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		pageSize = 10;
		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.setPagination(String.valueOf(pageSize));

		userNameList = manageUsersPage.getUserNameList(false, pageSize);

		assertTrue(userNameList.size() <= pageSize);

		numTextString = manageUsersPage.getLabelPageTableInfo().getText().trim();
		strList = numTextString.split(" ");
		userNum = Integer.parseInt(strList[3]);

		assertTrue(userNameList.size() == userNum);

		pageSize = 25;
		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.setPagination(String.valueOf(pageSize));

		userNameList = manageUsersPage.getUserNameList(false, pageSize);

		assertTrue(userNameList.size() <= pageSize);

		numTextString = manageUsersPage.getLabelPageTableInfo().getText().trim();
		strList = numTextString.split(" ");
		userNum = Integer.parseInt(strList[3]);

		assertTrue(userNameList.size() == userNum);

		pageSize = 50;
		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.setPagination(String.valueOf(pageSize));

		userNameList = manageUsersPage.getUserNameList(false, pageSize);

		assertTrue(userNameList.size() <= pageSize);

		numTextString = manageUsersPage.getLabelPageTableInfo().getText().trim();
		strList = numTextString.split(" ");
		userNum = Integer.parseInt(strList[3]);

		assertTrue(userNameList.size() == userNum);

		pageSize = 100;
		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.setPagination(String.valueOf(pageSize));

		userNameList = manageUsersPage.getUserNameList(false, pageSize);

		assertTrue(userNameList.size() <= pageSize);

		numTextString = manageUsersPage.getLabelPageTableInfo().getText().trim();
		strList = numTextString.split(" ");
		userNum = Integer.parseInt(strList[3]);

		assertTrue(userNameList.size() == userNum);
	}

	/**
	 * Test Case IDs: TC475 Test Description: Picarro Support - Disable Customer User
	 */
	@Test
	public void TC475_DisableCustomerUser() {
		String usernameNew = SQACUS + getTestSetup().getRandomNumber() + "TC475" + REGBASEUSERNAME;
		Log.info(String.format("\nRunning - TC475 - Test Description: Picarro Support - Disabled Customer User\n"));

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);

		homePage.waitForPageLoad();

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewCustomerUser(SQACUS, usernameNew, USERPASSWORD, CUSUSERROLEDR, SQACUSLOC, false);

		Log.info(String.format("Looking for user: Location-[%s]; Username-[%s]", SQACUSLOC, usernameNew));
		assertTrue(manageUsersPage.findExistingUser(SQACUSLOC, usernameNew, false));
		assertTrue(manageUsersPage.getUserStatus(usernameNew, false).equalsIgnoreCase(Resources.getResource(ResourceKeys.Constant_Disabled)));
		manageUsersPage.logout();

		loginPage.open();
		assertTrue(loginPage.loginNormalAs(usernameNew, USERPASSWORD) == null);
	}

	/**
	 * Test Case IDs: TC476 Test Description: Picarro Support - Disable Existing Picarro User
	 */
	@Test
	public void TC476_DisableExistingPicarroUser() {
		String userName = "PicarroUser" + getTestSetup().getRandomNumber() + "TC476" + REGBASEUSERNAME;
		String location = "Default";

		Log.info(userName);
		Log.info(String.format("\nRunning - TC476 - Test Description: Picarro Support - Disabled Existing Picarro User\n"));

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);

		homePage.waitForPageLoad();

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewPicarroUser(userName, USERPASSWORD, false);
		manageUsersPage.waitForPageLoad();

		assertTrue(manageUsersPage.findExistingUser(location, userName, false));
		assertTrue(manageUsersPage.getUserStatus(userName, false).equalsIgnoreCase(Resources.getResource(ResourceKeys.Constant_Disabled)));
		manageUsersPage.logout();

		loginPage.open();
		assertTrue(loginPage.loginNormalAs(userName, USERPASSWORD) == null);
	}

	/**
	 * Test Case IDs: TC477 Test Description: Picarro Support - Disable Existing Customer User
	 */
	@Test
	public void TC477_DisableExistingCustomerUser() {
		String usernameNew = SQACUS + getTestSetup().getRandomNumber() + "TC477" + REGBASEUSERNAME;
		Log.info(String.format("\nRunning - TC477 - Test Description: Picarro Support - Disabled Existing Customer User\n"));

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);

		homePage.waitForPageLoad();

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewCustomerUser(SQACUS, usernameNew, USERPASSWORD, CUSUSERROLEDR, SQACUSLOC, false);

		Log.info(String.format("Looking for user: Location-[%s]; Username-[%s]", SQACUSLOC, usernameNew));
		assertTrue(manageUsersPage.findExistingUser(SQACUSLOC, usernameNew, false));
		assertTrue(manageUsersPage.getUserStatus(usernameNew, false).equalsIgnoreCase(Resources.getResource(ResourceKeys.Constant_Disabled)));
		manageUsersPage.logout();

		loginPage.open();
		assertTrue(loginPage.loginNormalAs(usernameNew, USERPASSWORD) == null);
	}

	/**
	 * Test Case ID: TC116_TC487 Test Description: Search valid user record
	 */
	@Test
	@UseDataProvider(value = "dataProviderPicarroAdminSupportRoleInfo", location = UserDataProvider.class)
	@RunAs(users = SQAPICAD_AND_SQAPICSUP)
	public void TC116_TC487_SearchValidUser(String user, String pwd) {
		String tcID = getTestCaseName(ManageUserTestCaseType.SearchValidUser, user);
		String password = new CryptoUtility().decrypt(pwd);
		Log.info("\nRunning - " + tcID + " - Test Description: Search valid user record\n");

		loginPage.open();
		loginPage.loginNormalAs(user, password);

		manageUsersPage.open();
		assertTrue(manageUsersPage.searchUser(SQACUSUA, SQACUSLOC, CUSUSERROLEUA, USERENABLED, false /* searchAsCustomerAdmin */));
	}

	/**
	 * Test Case ID: TC117 Test Description: Search invalid user record
	 */
	@Test
	public void TC117_SearchInvalidUser() {
		String userName = SQACUS + getTestSetup().getRandomNumber() + "custadm117" + REGBASEUSERNAME;
		Log.info("\nRunning - TC117 - Test Description: Search invalid user record\n");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		manageUsersPage.open();
		manageUsersPage.waitForPageToLoad();
		manageUsersPage.getInputSearch().sendKeys(userName + userName);
		manageUsersPage.waitForPageToLoad();

		assertTrue(manageUsersPage.getLabelNoMatchingSearch().equalsIgnoreCase(NOMATCHINGSEARCH));
	}

	/**
	 * Returns the testCase ID based on the username provided by DataProvider.
	 */
	private String getTestCaseName(ManageUserTestCaseType testCaseType, String username) {
		String testCase = "";
		switch (testCaseType) {
		case ResetPwd:
			if (username.equalsIgnoreCase(SQAPICAD)) {
				testCase = "TC71";
			} else if (username.equalsIgnoreCase(SQAPICSUP)) {
				testCase = "TC473";
			}
			break;
		case DuplicateUser:
			if (username.equalsIgnoreCase(SQAPICAD)) {
				testCase = "TC90";
			} else if (username.equalsIgnoreCase(SQAPICSUP)) {
				testCase = "TC479";
			}
			break;
		case DisabledUser:
			if (username.equalsIgnoreCase(SQAPICAD)) {
				testCase = "TC94";
			} else if (username.equalsIgnoreCase(SQAPICSUP)) {
				testCase = "TC474";
			}
			break;
		case SearchValidUser:
			if (username.equalsIgnoreCase(SQAPICAD)) {
				testCase = "TC116";
			} else if (username.equalsIgnoreCase(SQAPICSUP)) {
				testCase = "TC487";
			}
			break;
		case MaxEmailChars:
			if (username.equalsIgnoreCase(SQAPICAD)) {
				testCase = "TC98";
			} else if (username.equalsIgnoreCase(SQAPICSUP)) {
				testCase = "TC486";
			}
			break;
		case ReEnableUsers:
			if (username.equalsIgnoreCase(SQAPICAD)) {
				testCase = "TC95";
			} else if (username.equalsIgnoreCase(SQAPICSUP)) {
				testCase = "TC478";
			}
			break;
		case EditUser:
			if (username.equalsIgnoreCase(SQAPICAD)) {
				testCase = "TC70";
			} else if (username.equalsIgnoreCase(SQAPICSUP)) {
				testCase = "TC472";
			}
			break;
		case ChangeRoleLocTz:
			if (username.equalsIgnoreCase(SQAPICAD)) {
				testCase = "TC87";
			} else if (username.equalsIgnoreCase(SQAPICSUP)) {
				testCase = "TC483_TC484_TC485";
			}
			break;
		case AddCustUser:
			if (username.equalsIgnoreCase(SQAPICAD)) {
				testCase = "TC69";
			} else if (username.equalsIgnoreCase(SQAPICSUP)) {
				testCase = "TC471";
			}
			break;
		case AddPicUser:
			if (username.equalsIgnoreCase(SQAPICAD)) {
				testCase = "TC68";
			} else if (username.equalsIgnoreCase(SQAPICSUP)) {
				testCase = "TC470";
			}
			break;
		}
		return testCase;
	}

	/**
	 * Test Case ID: TC480 Test Description: Add User - Password and Confirm Password values different Script: - Log into the site and click Administration -> Users - Click on 'Add New Picarro (or
	 * Customer) User' button - Provide different values for Password and Confirm Password fields Results: - "Please enter the same value again." message should be displayed
	 */
	@Test
	public void TC480_ConfirmPasswordDifferent_PicSupport() {
		String errorMsg = PWDSAMEVALUE;
		String email = PICNAMEPREFIX + "dr" + getTestSetup().getRandomNumber() + REGBASEPICUSERNAME;
		String customerName = "Picarro";
		String location = SQACUSSULOC;
		String locationDesc = customerName + " - " + location;

		Log.info("\nRunning - TC480_ConfirmPasswordDifferent_PicSupport - " + "Test Description:  Add User - Password and Confirm Password values different\n");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		manageUsersPage.open();

		// Picarro user
		manageUsersPage.addNewPicarroUser(email, USERPASSWORD, USERPASSWORD + " ", CUSUSERROLEDR, locationDesc, TIMEZONECT);
		String confirmPasswordError = manageUsersPage.getConfirmPasswordError();
		Log.info(String.format("Picarro User error message comparison: Expected='%s', Actual='%s'", errorMsg, confirmPasswordError));
		Log.info(String.format("Resource 'Validation_EnterSameValueAgain' value='%s'", Resources.getResource(ResourceKeys.Validation_EnterSameValueAgain)));
		assertEquals(errorMsg, confirmPasswordError);

		manageUsersPage.clickOnCancelAddBtn();

		// Customer User
		customerName = SQACUS;
		location = SQACUSLOC;
		manageUsersPage.addNewCustomerUser(customerName, email, "_" + USERPASSWORD, USERPASSWORD, CUSUSERROLEDR, location, true);
		confirmPasswordError = manageUsersPage.getConfirmPasswordError();
		Log.info(String.format("Customer User error message comparison: Expected='%s', Actual='%s'", errorMsg, confirmPasswordError));
		Log.info(String.format("Resource 'Validation_EnterSameValueAgain' value='%s'", Resources.getResource(ResourceKeys.Validation_EnterSameValueAgain)));
		TestContext.INSTANCE.captureScreenshot();

		assertEquals(errorMsg, confirmPasswordError);

		manageUsersPage.clickOnCancelAddBtn();
	}

	/**
	 * Test Case ID: TC481 Test Description: Add user - invalid email address values Script: - Log into the site and click Administration -> Users - Click on 'Add New Picarro (or Customer) User'
	 * button - Provide invalid email address (e.g. rpitter@b, rpitter, rpitter@b. , rpitter@b.c). Click OK Results: - "Please enter valid email address" message should be displayed
	 */
	@Test
	public void TC481_InvalidEmailAddress_PicSupport() {
		String errorMsg = EMAILINVALID;
		String emailU = PICNAMEPREFIX + "dr" + getTestSetup().getRandomNumber() + "@invalid.u";
		String emailC = PICNAMEPREFIX + "dr" + getTestSetup().getRandomNumber() + "";

		String customerName = "Picarro";
		String location = SQACUSSULOC;
		String locationDesc = customerName + " - " + location;

		Log.info("\nRunning - TC481_InvalidEmailAddress_PicSupport - " + "Test Description: Add user - invalid email address values\n");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		manageUsersPage.open();

		// Picarro user
		manageUsersPage.addNewPicarroUser(emailU, USERPASSWORD, CUSUSERROLEDR, locationDesc, TIMEZONECT);
		assertEquals(manageUsersPage.getInvalidEmailError(), errorMsg);

		manageUsersPage.clickOnCancelAddBtn();

		// Customer User
		customerName = SQACUS;
		location = SQACUSLOC;
		locationDesc = customerName + " - " + location;

		manageUsersPage.addNewCustomerUser(customerName, emailC, USERPASSWORD, CUSUSERROLEDR, location);
		assertEquals(manageUsersPage.getInvalidEmailError(), errorMsg);

		manageUsersPage.clickOnCancelAddBtn();
	}

	/**
	 * Test Case ID: TC482 Test Description: Add user - blank required fields Script: - Log into the site and click Administration -> Users - Click on 'Add New Picarro (or Customer) User' button -
	 * Keep Email Address, pwd and confirm pwd fields blank. Click OK Result: - "This field is required." message should be displayed"
	 */
	@Test
	public void TC482_AddUserBlankFields_PicSupport() {
		String errorMsg = BLANKFIELDERROR;
		String email = "";
		String password = "";

		String customerName = "Picarro";
		String location = SQACUSSULOC;
		String locationDesc = customerName + " - " + location;

		Log.info("\nRunning - TC482_AddUserBlankFields_PicSupport - " + "Test Description: Add user - blank required fields\n");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		manageUsersPage.open();

		// Add Picarro User
		manageUsersPage.addNewPicarroUser(email, password, CUSUSERROLEDR, locationDesc, TIMEZONECT);
		assertEquals(manageUsersPage.getInvalidEmailError(), errorMsg);
		assertEquals(manageUsersPage.getPasswordError(), errorMsg);
		assertEquals(manageUsersPage.getConfirmPasswordError(), errorMsg);

		manageUsersPage.clickOnCancelAddBtn();

		// Add Customer User
		customerName = SQACUS;
		location = SQACUSLOC;
		locationDesc = customerName + " - " + location;

		manageUsersPage.addNewCustomerUser(customerName, email, password, CUSUSERROLEDR, location);
		assertEquals(manageUsersPage.getInvalidEmailError(), errorMsg);
		assertEquals(manageUsersPage.getPasswordError(), errorMsg);
		assertEquals(manageUsersPage.getConfirmPasswordError(), errorMsg);

		manageUsersPage.clickOnCancelAddBtn();
	}

	/**
	 * Test Case ID: TC488 Test Description: Search invalid user record Script: - Log into the site and Provide invalid user name in search box present on Users screen Result: - "Message should be
	 * displayed : 'No matching records found'
	 */
	@Test
	public void TC488_NoMatchingUsersFound_PicSupport() {
		String errorMsg = NOMATCHINGRECORDS;
		String invalidKey = "whichUserIsNotValidHowever";

		Log.info("\nRunning - TC488_NoMatchingUsersFound_PicSupport - " + "Test Description: Add user - blank required fields\n");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		manageUsersPage.open();

		manageUsersPage.performSearch(invalidKey);
		assertEquals(manageUsersPage.getLabelNoMatchingSearch(), errorMsg);
	}

	/**
	 * Test Case ID: TC132_ManageUsers_SortColumns Script: - Sort records based on attributes present Results: - - User is able to sort the list of records based on specified attribute
	 */
	@Test
	public void TC132_ManageUsers_SortColumns() {
		Log.info("\nRunning TC132_ManageUsers_SortColumns");
		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		manageUsersPage.open();
		assertTrue(manageUsersPage.areTableColumnsSorted());
	}
}