/**
 * 
 */
package surveyor.regression.source;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.CryptoUtility;
import common.source.Log;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataprovider.RunAs;
import surveyor.dataprovider.UserDataProvider;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.SurveyorBaseTest;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
@RunWith(DataProviderRunner.class)
public class ManageUsersPageTest extends SurveyorBaseTest {
	private static final String FN_TC71_TC473_USER_RESET_PWD = "TC71_TC473_User_ResetPwd";
	private static final String SQAPICAD_AND_SQAPICSUP = "sqapicad@picarro.com,sqapicsup@picarro.com";
	private static ManageUsersPage manageUsersPage;
	private static ManageCustomersPage manageCustomersPage;
	private static ManageLocationsPage manageLocationsPage;

	private enum ManageUserTestCaseType {
		ResetPwd,
		DuplicateUser,
		DisabledUser
	}
	
	@BeforeClass
	public static void setupManageUsersPageTest() {
		manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageUsersPage);

		manageCustomersPage = new ManageCustomersPage(driver, baseURL,
				testSetup);
		PageFactory.initElements(driver, manageCustomersPage);

		manageLocationsPage = new ManageLocationsPage(driver, baseURL,
				testSetup);
		PageFactory.initElements(driver, manageLocationsPage);
	}

	/**
	 * Test Case ID: TC68 Test Description: Picarro Admin - Add New Picarro User
	 * 
	 */
	@Test
	public void TC68_AddNewPicarroUser() {
		String userName = "PicarroUser" + testSetup.getRandomNumber()
				+ "picarroUser01" + REGBASEUSERNAME;
		String customer = "Picarro";
		String location = "Santa Clara";
		String locationDesc = customer + " - " + location;

		System.out
				.println("\nRunning TC68 - Test Description: Picarro Admin - Add New Picarro user");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(),
				testSetup.getLoginPwd());

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewPicarroUser(userName, USERPASSWORD,
				CUSUSERROLEUA, locationDesc, TIMEZONECT);

		assertTrue(manageUsersPage.findExistingUser(location, userName, false));
		loginPage = manageUsersPage.logout();

		loginPage.open();
		HomePage homePage = loginPage.loginNormalAs(userName, USERPASSWORD);
		assertTrue(homePage.checkIfAtHomePage());
	}

	/**
	 * Test Case ID: TC69 Test Description: Picarro Admin - Add New Customer
	 * User
	 * 
	 */
	@Test
	public void TC69_AddNewCustomerUser() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber()
				+ "TC69";
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + "customerUser01" + REGBASEUSERNAME;
		String cityName = "Santa Clara";
		String locationName = customerName + "loc";

		System.out
				.println("\nRunning TC69 - Test Description: Picarro Admin - Add New Customer user");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(),
				testSetup.getLoginPwd());

		manageCustomersPage.open();
		manageCustomersPage.waitForPageLoad();
		manageCustomersPage.addNewCustomer(customerName, eula);

		manageLocationsPage.open();
		manageLocationsPage.waitForPageLoad();
		manageLocationsPage
				.addNewLocation(locationName, customerName, cityName);

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewCustomerUser(customerName, userName,
				USERPASSWORD, CUSUSERROLEUA, locationName);

		assertTrue(manageUsersPage.findExistingUser(locationName, userName, false));
		loginPage = manageUsersPage.logout();

		loginPage.open();
		HomePage homePage = loginPage.loginNormalAs(userName, USERPASSWORD);
		assertTrue(homePage.checkIfAtHomePage());
	}

	
	/**
	 * Test Case ID: TC70_EditUser_PicAdmin
	 * Script:
	 * - On Home Page, click Picarro Administration -> Manage Users
	 * - Click on Edit link
	 * - Modify user details and click OK
	 * Results: - 
	 * - User details are modified successfully
	 */
	@Test
	public void TC70_EditUser_PicAdmin() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "TC70";
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + "customerUser01" + REGBASEUSERNAME;
		String cityName = "Santa Clara";
		String locationName = customerName + "loc";

		System.out.println("\nRunning TC70 - Test Description: Picarro Admin - edit user");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		manageCustomersPage.open();
		manageCustomersPage.waitForPageLoad();
		manageCustomersPage.addNewCustomer(customerName, eula);

		manageLocationsPage.open();
		manageLocationsPage.waitForPageLoad();
		manageLocationsPage.addNewLocation(locationName, customerName, cityName);

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		
		Log.info(String.format("Adding new user: CustomerName=[%s]; Username=[%s]; Role=[%s]; Location=[%s]", 
				customerName, userName, CUSUSERROLEUA, locationName)); 
		manageUsersPage.addNewCustomerUser(customerName, userName,
				USERPASSWORD, CUSUSERROLEUA, locationName);

		Log.info(String.format("Editing user: Username=[%s]; Role=[%s]; Timezone=[%s]; Enabled=[%b]; IsCustomerUser=[%b]", 
				userName, CUSUSERROLESU, TIMEZONEETUA, true, false)); 
		manageUsersPage.editUser(userName, CUSUSERROLESU, TIMEZONEETUA, true, false);
		
		Log.info(String.format("Editing user: Location=[%s]; Username=[%s]; IsCustomerUser=[%b]", 
				cityName, userName, false)); 
		assertTrue(manageUsersPage.findExistingUser(locationName, userName, false));
		loginPage = manageUsersPage.logout();

		loginPage.open();
		HomePage homePage = loginPage.loginNormalAs(userName, USERPASSWORD);
		assertTrue(homePage.checkIfAtHomePage());
	}
	
	/**
	 * Test Case IDs: 
	 * TC71  Test Description: Reset user password as Picarro Admin.
	 * TC473 Test Description: Reset user password as Picarro Support.
	 */
	@Test
	@UseDataProvider(value = "dataProviderPicarroUserRoleInfo", location = UserDataProvider.class)
	@RunAs(users=SQAPICAD_AND_SQAPICSUP)
	public void TC71_TC473_User_ResetPwd(String username, String password, String role, 
			String customerName, String customerLocation) {		
		if (!isValidRunAsUser(username, FN_TC71_TC473_USER_RESET_PWD)) {
			return;
		}					
		
		String testCaseID = getTestCaseName(ManageUserTestCaseType.ResetPwd, username);		
		password = CryptoUtility.decrypt(password);
		
		String usernameNew = SQACUS + testSetup.getRandomNumber() + testCaseID + REGBASEUSERNAME;
		Log.info(String.format("\nRunning - %s - Reset customer user password as %s \n", 
				testCaseID, role));

		loginPage.open();
		loginPage.loginNormalAs(username, password);

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewCustomerUser(SQACUS, usernameNew, password, CUSUSERROLEDR, TIMEZONECTUA, SQACUSLOC);

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
	 * Test Case ID: 
	 * TC72 Test Description: Change password as Picarro admin
	 */
	@Test
	public void TC72_ChangePwd_PicAdmin() {
		String usernameNew = SQACUS + testSetup.getRandomNumber() + "TC72" + REGBASEUSERNAME;
		Log.info("\nRunning - TC72 - Change customer user password \n");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewCustomerUser(SQACUS, usernameNew, USERPASSWORD, CUSUSERROLEDR, TIMEZONECTUA, SQACUSLOC);

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
	 * Test Case ID: TC87_EditUserChangeRoleLocationTimezone_PicAdmin
	 * Script:
	 * - On Home Page, and click Picarro Administration -> Manage Users
	 * - Click on Edit link and change the role, timzone and location of existing user
	 * Results: - 
	 * - Admin can chang the role of user
	 * - Admin can change time zone of the user
	 * - Admin can change location of the user
	 */
	@Test
	public void TC87_EditUserChangeRoleLocationTimezone_PicAdmin() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "TC87";
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + "customerUser01" + REGBASEUSERNAME;
		String cityName = "Santa Clara";
		String cityNameNew = "Santa Clara2";
		String locationName = customerName + "loc";
		String locationNameNew = customerName + "locNew";
		String locationNameNewDesc = customerName + " - " + locationNameNew;

		System.out.println("\nRunning TC87 - Test Description: Picarro Admin - edit user - change role, location and timezone of existing user");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		manageCustomersPage.open();
		manageCustomersPage.waitForPageLoad();
		manageCustomersPage.addNewCustomer(customerName, eula);

		manageLocationsPage.open();
		manageLocationsPage.waitForPageLoad();
		manageLocationsPage.addNewLocation(locationName, customerName, cityName);

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();

		Log.info(String.format("Creating new user - Customer=%s;Username=%s;Role=%s;Location=%s;Enabled=%b",
				customerName, userName, CUSUSERROLEUA, locationName, true));
		manageUsersPage.addNewCustomerUser(customerName, userName,
				USERPASSWORD, CUSUSERROLEUA, locationName);

		// Add another location for the customer.
		manageLocationsPage.open();
		manageLocationsPage.waitForPageLoad();
		manageLocationsPage.addNewLocation(locationNameNew, customerName, cityNameNew);		
		
		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		
		// Edit role, location and timezone
		Log.info(String.format("Editing user - Username=%s;Role=%s;Timezone=%s;Location=%s;Enabled=%b;IsCustomerUser=%b",
				userName, CUSUSERROLESU, TIMEZONEETUA, locationNameNew, true, false));
		manageUsersPage.editUser(userName, CUSUSERROLESU, TIMEZONEETUA, locationNameNewDesc, true, false);

		Log.info(String.format("Finding user - Location=%s;Username=%s;IsCustomerUser=%b",
				locationNameNew, userName, false));
		assertTrue(manageUsersPage.findExistingUser(locationNameNew, userName, false));
		loginPage = manageUsersPage.logout();

		loginPage.open();
		HomePage homePage = loginPage.loginNormalAs(userName, USERPASSWORD);
		assertTrue(homePage.checkIfAtHomePage());
	}

	/**
	 * Test Case IDs: 
	 * TC90  Test Description: Pic admin not allowed to create duplicate User
	 * TC479 Test Description: Pic support not allowed to create duplicate User
	 */
	@Test
	@UseDataProvider(value = "dataProviderPicarroUserRoleInfo", location = UserDataProvider.class)
	@RunAs(users=SQAPICAD_AND_SQAPICSUP)	
	public void TC90_TC479_DuplicateUserCreationNotAllowed(String username, String password, String role, 
			String customerName, String customerLocation) {
		String testCaseID = getTestCaseName(ManageUserTestCaseType.ResetPwd, username);		
		password = CryptoUtility.decrypt(password);

		String usernameNew = SQACUS + testSetup.getFixedSizePseudoRandomString(24) + "_" + testCaseID + REGBASEUSERNAME;

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
		assertTrue(output.contains(DUPLICATIONERROR));
	}
	
	/**
	 * Test Case IDs: 
	 * TC94  Test Description: Picarro Admin - Disabled User
	 * TC474 Test Description: Picarro Support - Disabled User
	 */
	@Test
	@UseDataProvider(value = "dataProviderPicarroUserRoleInfo", location = UserDataProvider.class)
	@RunAs(users=SQAPICAD_AND_SQAPICSUP)	
	public void TC94_TC474_DisableUser(String username, String password, String role, 
			String customerName, String customerLocation) {
		String testCaseID = getTestCaseName(ManageUserTestCaseType.DisabledUser, username);		
		password = CryptoUtility.decrypt(password);
		
		String usernameNew = SQACUS + testSetup.getRandomNumber() + testCaseID + REGBASEUSERNAME;
		Log.info(String.format("\nRunning - %s - Test Description: %s - Disabled User\n", testCaseID, role));

		loginPage.open();
		loginPage.loginNormalAs(username, password);

		homePage.waitForPageLoad();

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewCustomerUser(SQACUS, usernameNew, password, CUSUSERROLEDR, SQACUSLOC, false);

		Log.info(String.format("Looking for user: Location-[%s]; Username-[%s]", SQACUSLOC, usernameNew));
		assertTrue(manageUsersPage.findExistingUser(SQACUSLOC, usernameNew, false));
		assertTrue(manageUsersPage.getUserStatus(usernameNew, false)
				.equalsIgnoreCase(Resources.getResource(ResourceKeys.Constant_Disabled)));
		manageUsersPage.logout();

		loginPage.open();
		assertTrue(loginPage.loginNormalAs(usernameNew, USERPASSWORD) == null);
	}

	/**
	 * Test Case ID: TC95_ReEnableUser_PicAdmin
	 * Script:
	 * Pre-requisite: Create Disabled user
	 * - On Home Page, and click Picarro Administration -> Manage Users
	 * - Click on Edit button of disabled user
	 * - Enable the user and click OK
	 * Results: - 
	 * - User should be able to log in the application
	 */
	@Test
	public void TC95_ReEnableUser_PicAdmin() {
		String userName = SQACUS + testSetup.getRandomNumber() + "TC95" + REGBASEUSERNAME;
		String locationDesc = SQACUS + " - " + SQACUSLOC;
		
		Log.info("\nRunning - TC95_ReEnableUser_PicAdmin - Test Description: Picarro Admin - Re-Enable User\n");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		homePage.waitForPageLoad();
		
		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();

		// create disabled user.
		Log.info(String.format("Creating new user - Customer=%s;Username=%s;Role=%s;Location=%s;Enabled=%b",
				SQACUS, userName, CUSUSERROLEDR, SQACUSLOC, false));
		manageUsersPage.addNewCustomerUser(SQACUS, userName, USERPASSWORD, CUSUSERROLEDR, SQACUSLOC, false);

		// enable the user
		assertTrue(manageUsersPage.editUser(userName, CUSUSERROLEDR, TIMEZONEETUA, locationDesc, true /*enable user*/, false));
		manageUsersPage.logout();

		// verify user can login correctly
		loginPage.open();
		HomePage homePage = loginPage.loginNormalAs(userName, USERPASSWORD);
		assertTrue(homePage.checkIfAtHomePage());
	}

	/**
	 * Test Case ID: TC98_UserEmailMax50Chars_PicAdmin
	 * Script:
	 * - On Home Page, and click Picarro Administration -> Manage Users
	 * - Click on 'Add New User' button
	 * - Provide more than 50 characters in Email Address field and click OK
	 * - Repeat the same steps for Edit user page
	 * Results: - 
	 * - "Please enter no more than 50 characters." message is displayed
	 */
	@Test
	public void TC98_UserEmailMax50Chars_PicAdmin() {
		final int MAX_SIZE = 50;
		String userName50 = SQACUS + testSetup.getFixedSizePseudoRandomString(MAX_SIZE-20) + "TC98" + REGBASEUSERNAME;
		String userName51 = SQACUS + testSetup.getFixedSizePseudoRandomString(MAX_SIZE-20) + "TC98" + REGBASEUSERNAME + "A";
		Log.info("\nRunning - TC98_UserEmailMax50Chars_PicAdmin - Test Description: Picarro Admin - "
				+ "More than 50 characters not allowed in email address field\n");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		homePage.waitForPageLoad();
		
		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();

		// Create user with 50 characters in email.
		Log.info(String.format("Creating new user - Customer=%s;Username=%s;Role=%s;Location=%s;Enabled=%b",
				SQACUS, userName50, CUSUSERROLEDR, SQACUSLOC, false));
		manageUsersPage.addNewCustomerUser(SQACUS, userName50, USERPASSWORD, CUSUSERROLEDR, SQACUSLOC, false);
		
		Log.info(String.format("Finding user - Location=%s;Username=%s;IsCustomerUser=%b",
				SQACUSLOC, userName50, false));
		assertTrue(manageUsersPage.findExistingUser(SQACUSLOC, userName50, false));

		// Create user with 51 characters in email.
		userName51 = SQACUS + testSetup.getFixedSizePseudoRandomString(MAX_SIZE-20) + "TC98" + REGBASEUSERNAME + "A";
		Log.info(String.format("Creating new user - Customer=%s;Username=%s;Role=%s;Location=%s;Enabled=%b",
				SQACUS, userName51, CUSUSERROLEDR, SQACUSLOC, false));
		manageUsersPage.addNewCustomerUser(SQACUS, userName51, USERPASSWORD, CUSUSERROLEDR, SQACUSLOC, false);

		Log.info(String.format("Finding user - Location=%s;Username=%s;IsCustomerUser=%b",
				SQACUSLOC, userName51.substring(0, MAX_SIZE), false));
		assertTrue(manageUsersPage.findExistingUser(SQACUSLOC, userName51.substring(0, MAX_SIZE), false));

		Log.info(String.format("Finding user - Location=%s;Username=%s;IsCustomerUser=%b",
				SQACUSLOC, userName51, false));
		assertFalse(manageUsersPage.findExistingUser(SQACUSLOC, userName51, false));
	}
	
	/**
	 * Test Case ID: TC115 Test Description: Pagination (Manage Users) Test
	 * Script: 10,25,50 and 100 records selection on all Administration screens
	 * Expected Results: Specified number of records will be listed in the table
	 * Future Improvement: validating on "Manage Users" pages for now and should
	 * check on other pages as well
	 */
	@Test
	public void TC115_ManageUserPagination() {
		List<String> userNameList;
		String numTextString;
		String[] strList;
		int userNum = 0;
		int pageSize = 0;

		System.out
				.println("\nRunning - TC115 - Test Description: Pagination (Manage Users)\n");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(),
				testSetup.getLoginPwd());

		pageSize = 10;
		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.setPagination(String.valueOf(pageSize));

		userNameList = manageUsersPage.getUserNameList(false, pageSize);

		assertTrue(userNameList.size() <= pageSize);

		numTextString = manageUsersPage.getLabelPageTableInfo().getText()
				.trim();
		strList = numTextString.split(" ");
		userNum = Integer.parseInt(strList[3]);

		assertTrue(userNameList.size() == userNum);

		pageSize = 25;
		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.setPagination(String.valueOf(pageSize));

		userNameList = manageUsersPage.getUserNameList(false, pageSize);

		assertTrue(userNameList.size() <= pageSize);

		numTextString = manageUsersPage.getLabelPageTableInfo().getText()
				.trim();
		strList = numTextString.split(" ");
		userNum = Integer.parseInt(strList[3]);

		assertTrue(userNameList.size() == userNum);

		pageSize = 50;
		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.setPagination(String.valueOf(pageSize));

		userNameList = manageUsersPage.getUserNameList(false, pageSize);

		assertTrue(userNameList.size() <= pageSize);

		numTextString = manageUsersPage.getLabelPageTableInfo().getText()
				.trim();
		strList = numTextString.split(" ");
		userNum = Integer.parseInt(strList[3]);

		assertTrue(userNameList.size() == userNum);

		pageSize = 100;
		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.setPagination(String.valueOf(pageSize));

		userNameList = manageUsersPage.getUserNameList(false, pageSize);

		assertTrue(userNameList.size() <= pageSize);

		numTextString = manageUsersPage.getLabelPageTableInfo().getText()
				.trim();
		strList = numTextString.split(" ");
		userNum = Integer.parseInt(strList[3]);

		assertTrue(userNameList.size() == userNum);
	}

	/**
	 * Test Case ID: TC470 Test Description: Picarro Support - Add New Picarro
	 * User
	 * 
	 */
	@Test
	public void TC470_AddNewPicarroUser() {
		String userName = "PicarroUser" + testSetup.getRandomNumber()
				+ "picarroUser02" + REGBASEUSERNAME;
		String customer = "Picarro";
		String location = "Santa Clara";
		String locationDesc = customer + " - " + location;

		System.out
				.println("\nRunning TC470 - Test Description: Picarro Support - Add New Picarro user");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewPicarroUser(userName, USERPASSWORD,
				CUSUSERROLESU, locationDesc, TIMEZONECT);

		assertTrue(manageUsersPage.findExistingUser(location, userName, false));
		loginPage = manageUsersPage.logout();

		loginPage.open();
		HomePage homePage = loginPage.loginNormalAs(userName, USERPASSWORD);
		assertTrue(homePage.checkIfAtHomePage());
	}

	/**
	 * Test Case ID: TC471 Test Description: Picarro Support - Add New Customer
	 * User
	 * 
	 */
	@Test
	public void TC471_AddNewCustomerUser() {
		String userName = SQACUS + testSetup.getRandomNumber()
				+ "customerUser02" + REGBASEUSERNAME;

		System.out
				.println("\nRunning TC471 - Test Description: Picarro Support - Add New Customer user");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);

		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewCustomerUser(SQACUS, userName, USERPASSWORD,
				CUSUSERROLEUA, SQACUSLOC);

		assertTrue(manageUsersPage.findExistingUser(SQACUSLOC, userName, false));
		loginPage = manageUsersPage.logout();

		loginPage.open();
		HomePage homePage = loginPage.loginNormalAs(userName, USERPASSWORD);
		assertTrue(homePage.checkIfAtHomePage());
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
		}
		return testCase;
	}
}