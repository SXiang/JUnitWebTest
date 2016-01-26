/**
 * 
 */
package surveyor.regression.source;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import org.openqa.selenium.support.PageFactory;

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
public class ManageUsersPageTest extends SurveyorBaseTest {
	private static ManageUsersPage manageUsersPage;
	private static ManageCustomersPage manageCustomersPage;
	private static ManageLocationsPage manageLocationsPage;

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
		String locationDesc = "Picarro - Santa Clara";

		System.out
				.println("\nRunning TC68 - Test Description: Picarro Admin - Add New Picarro user");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(),
				testSetup.getLoginPwd());

		manageUsersPage.open();
		manageUsersPage.addNewPicarroUser(userName, USERPASSWORD,
				CUSUSERROLEUA, locationDesc, TIMEZONECT);

		assertTrue(manageUsersPage.findExistingUser("Picarro", userName));
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
		String locationDesc = customerName + "-" + cityName;

		System.out
				.println("\nRunning TC69 - Test Description: Picarro Admin - Add New Customer user");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(),
				testSetup.getLoginPwd());

		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);

		manageLocationsPage.open();
		manageLocationsPage
				.addNewLocation(locationDesc, customerName, cityName);

		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(customerName, userName,
				USERPASSWORD, CUSUSERROLEUA, locationDesc);

		assertTrue(manageUsersPage.findExistingUser(customerName, userName));
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
		String locationDesc = customerName + "-" + cityName;

		System.out.println("\nRunning TC70 - Test Description: Picarro Admin - edit user");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);

		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationDesc, customerName, cityName);

		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(customerName, userName,
				USERPASSWORD, CUSUSERROLEUA, locationDesc);

		manageUsersPage.editUser(userName, CUSUSERROLESU, TIMEZONEETUA, true);
		assertTrue(manageUsersPage.findExistingUser(customerName, userName));
		loginPage = manageUsersPage.logout();

		loginPage.open();
		HomePage homePage = loginPage.loginNormalAs(userName, USERPASSWORD);
		assertTrue(homePage.checkIfAtHomePage());
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
		String locationDesc = "Picarro - Santa Clara";

		System.out
				.println("\nRunning TC470 - Test Description: Picarro Support - Add New Picarro user");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);

		manageUsersPage.open();
		manageUsersPage.addNewPicarroUser(userName, USERPASSWORD,
				CUSUSERROLESU, locationDesc, TIMEZONECT);

		assertTrue(manageUsersPage.findExistingUser("Picarro", userName));
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
		manageUsersPage.addNewCustomerUser(SQACUS, userName, USERPASSWORD,
				CUSUSERROLEUA, SQACUSLOC);

		assertTrue(manageUsersPage.findExistingUser(SQACUS, userName));
		loginPage = manageUsersPage.logout();

		loginPage.open();
		HomePage homePage = loginPage.loginNormalAs(userName, USERPASSWORD);
		assertTrue(homePage.checkIfAtHomePage());
	}
}