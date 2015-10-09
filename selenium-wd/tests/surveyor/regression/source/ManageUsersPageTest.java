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
	 * Test Case ID: TC69 Test Description: Adding a customer and a User with
	 * Utility Administrator role
	 * 
	 */
	@Test
	public void TC69_AddCustomerUser() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber()
				+ "TC69";
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + REGBASEUSERNAME;
		String cityName = "Santa Clara";
		String locationDesc = customerName + "-" + cityName;

		System.out
				.println("\nRunning TC69 - Test Description: Adding a customer and a User with Utility Administrator role");

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
	 * Test Case ID: TC115 Test Description: Pagination (Manage Users) Test
	 * Script: 10,25,50 and 100 records selection on all Administration screens
	 * Expected Results: Specified number of records will be listed in the table
	 * Current implementation: Current Issue: Future Improvement: validating on
	 * "Manage Users" pages for now and should check on other pages as well
	 */
	@Test
	public void TC115_ManageUserPagination() {
		List<String> userNameList;
		String numTextString;
		String[] strList;
		int userNum = 0;

		System.out
				.println("\nRunning - TC115 - Test Description: Pagination (Manage Users)\n");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICAD, USERPASSWORD);

		manageUsersPage.open();
		manageUsersPage.setPagination("10");

		userNameList = manageUsersPage.getUserNameList(false);

		assertTrue(userNameList.size() <= 10);

		numTextString = manageUsersPage.getLabelPageTableInfo().getText()
				.trim();
		strList = numTextString.split(" ");
		userNum = Integer.parseInt(strList[3]);

		assertTrue(userNameList.size() == userNum);

		manageUsersPage.open();
		manageUsersPage.setPagination("25");

		userNameList = manageUsersPage.getUserNameList(false);

		assertTrue(userNameList.size() <= 25);

		numTextString = manageUsersPage.getLabelPageTableInfo().getText()
				.trim();
		strList = numTextString.split(" ");
		userNum = Integer.parseInt(strList[3]);

		assertTrue(userNameList.size() == userNum);

		manageUsersPage.open();
		manageUsersPage.setPagination("50");

		userNameList = manageUsersPage.getUserNameList(false);

		assertTrue(userNameList.size() <= 50);

		numTextString = manageUsersPage.getLabelPageTableInfo().getText()
				.trim();
		strList = numTextString.split(" ");
		userNum = Integer.parseInt(strList[3]);

		assertTrue(userNameList.size() == userNum);

		manageUsersPage.open();
		manageUsersPage.setPagination("100");

		userNameList = manageUsersPage.getUserNameList(false);

		assertTrue(userNameList.size() <= 100);

		numTextString = manageUsersPage.getLabelPageTableInfo().getText()
				.trim();
		strList = numTextString.split(" ");
		userNum = Integer.parseInt(strList[3]);

		assertTrue(userNameList.size() == userNum);
	}
}