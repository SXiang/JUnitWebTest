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
	
	@BeforeClass
	public static void setupManageUsersPageTest() {
		manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
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
	 * Test Description: Adding a customer and a User with Utility Administrator role
	 * 
	 */	
	@Test
	public void ADM013() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "adm013";
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + REGBASEUSERNAME;
		
		System.out.println("\nRunning ADM013 - Test Description: Adding a customer and a User with Utility Administrator role");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEUA);
		
		assertTrue(manageUsersPage.findExistingUser(customerName, userName));
		
		loginPage.open();
		HomePage homePage = loginPage.loginNormalAs(userName, USERPASSWORD);
		
		assertTrue(homePage.checkIfAtHomePage());
	}
	
	/**
	 * Test Case ID: CUSTADM013
	 * Test Description: Pagination (Manage Users)
	 * Test Script: 10,25,50 and 100 records selection on all Administration screens
	 * Expected Results: Specified number of records will be listed in the table
	 * Current implementation:   
	 * Current Issue:
     * Future Improvement: validating on "Manage Users" pages for now and should check on other pages as well
	 */	
	@Test
	public void CUSTADM013() {
		List<String> userNameList;
		String numTextString;
		String[] strList;
		int userNum = 0;
		
		System.out.println("\nRunning - CUSTADM013 - Test Description: Pagination (Manage Users)\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQAPICAD, USERPASSWORD);
		
		manageUsersPage.open();		
		manageUsersPage.setPagination("10");
		
		userNameList = manageUsersPage.getUserNameList(false);
		
		assertTrue(userNameList.size() <= 10);
		
		numTextString = manageUsersPage.getLabelPageTableInfo().getText().trim();
		strList = numTextString.split(" ");
		userNum = Integer.parseInt(strList[3]);
		
		assertTrue(userNameList.size() == userNum);
		
		manageUsersPage.open();		
		manageUsersPage.setPagination("25");
		
		userNameList = manageUsersPage.getUserNameList(false);
		
		assertTrue(userNameList.size() <= 25);
		
		numTextString = manageUsersPage.getLabelPageTableInfo().getText().trim();
		strList = numTextString.split(" ");
		userNum = Integer.parseInt(strList[3]);
		
		assertTrue(userNameList.size() == userNum);
		
		manageUsersPage.open();		
		manageUsersPage.setPagination("50");
		
		userNameList = manageUsersPage.getUserNameList(false);
		
		assertTrue(userNameList.size() <= 50);
		
		numTextString = manageUsersPage.getLabelPageTableInfo().getText().trim();
		strList = numTextString.split(" ");
		userNum = Integer.parseInt(strList[3]);
		
		assertTrue(userNameList.size() == userNum);
		
		manageUsersPage.open();		
		manageUsersPage.setPagination("100");
		
		userNameList = manageUsersPage.getUserNameList(false);
		
		assertTrue(userNameList.size() <= 100);
		
		numTextString = manageUsersPage.getLabelPageTableInfo().getText().trim();
		strList = numTextString.split(" ");
		userNum = Integer.parseInt(strList[3]);
		
		assertTrue(userNameList.size() == userNum);
	}	
}