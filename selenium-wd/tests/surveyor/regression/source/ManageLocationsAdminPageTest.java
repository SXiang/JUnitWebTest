/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.*;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsAdminPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.SurveyorBaseTest;

/**
 * @author zlu
 *
 */
public class ManageLocationsAdminPageTest extends SurveyorBaseTest {
	private static ManageCustomersPage manageCustomersPage;
	private static ManageUsersPage manageUsersPage;
	private static ManageLocationsAdminPage manageLocationsAdminPage;
	
	public ManageLocationsAdminPageTest() {
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
		
		manageLocationsAdminPage = new ManageLocationsAdminPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageLocationsAdminPage);
		
		manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
	}

	/**
	 * Test Case ID: MLAP000A
	 * Test Description: Adding a Customer by Administrator then add Location by Customer Utility Administrator
	 * 
	 */
	@Test
	public void MLAP000A() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "mlap000a";
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + "_ua" + REGBASEUSERNAME;
		String locationName = customerName + "Loc";
		
		System.out.println("\nRunning MLAP000A - Test Description: Adding a Customer by Administrator then add Location by Customer Utility Administrator");
	
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEUA);
		
		manageUsersPage.logout();
		
		manageLocationsAdminPage.login(userName, USERPASSWORD);
		manageLocationsAdminPage.open();
		manageLocationsAdminPage.addNewLocation(locationName);
		
		assertTrue(manageLocationsAdminPage.findExistingLocation(customerName, locationName));
		
		manageLocationsAdminPage.logout();
	}
	
	/**
	 * Test Case ID: MLAP000B
	 * Test Description: Adding a Location for Picarro by Picarro Utility Administrator
	 * 
	 */
	@Test
	public void MLAP000B() {
		String customerName = "Picarro";
		String strRandomNum = testSetup.getRandomNumber();
		String userName = "picarro" + strRandomNum + "mlap000b" + "_ua" + REGBASEPICUSERNAME;
		String locationName = strRandomNum + "mlap000b" + "Loc";
		
		System.out.println("\nRunning MLAP000B - Test Description: Adding a Location for Picarro by Picarro Utility Administrator");
		
		manageUsersPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		manageUsersPage.open();
		manageUsersPage.addNewPicarroUser(userName, USERPASSWORD, CUSUSERROLEUA);
		
		manageUsersPage.logout();
		
		manageLocationsAdminPage.login(userName, USERPASSWORD);
		manageLocationsAdminPage.open();
		manageLocationsAdminPage.addNewLocation(locationName);
		
		assertTrue(manageLocationsAdminPage.findExistingLocation(customerName, locationName));
		
		manageLocationsAdminPage.logout();
	}	

}
