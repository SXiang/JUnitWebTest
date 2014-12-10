/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsAdminPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.SurveyorBaseTest;

/**
 * @author zlu
 *
 */
public class ManageLocationsAdminPageTest extends SurveyorBaseTest {
	private static ManageUsersPage manageUsersPage;
	private static ManageLocationsAdminPage manageLocationsAdminPage;
	private static ManageCustomersPage manageCustomersPage;
	
	@BeforeClass
	public static void setupManageLocationsAdminPageTest() {
		manageLocationsAdminPage = new ManageLocationsAdminPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageLocationsAdminPage);
		
		manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersPage);
		
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
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
	
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEUA);
		
		manageUsersPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		manageLocationsAdminPage.open();
		manageLocationsAdminPage.addNewLocation(locationName);
		
		assertTrue(manageLocationsAdminPage.findExistingLocation(customerName, locationName));
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
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageUsersPage.open();
		manageUsersPage.addNewPicarroUser(userName, USERPASSWORD, CUSUSERROLEUA);
		
		manageUsersPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		manageLocationsAdminPage.open();
		manageLocationsAdminPage.addNewLocation(locationName);
		
		assertTrue(manageLocationsAdminPage.findExistingLocation(customerName, locationName));
	}
	
	/**
	 * Test Case ID: CUSTADM023
	 * Test Description: Add location
	 * Test Script: - On Home Page, click Administration -> Manage Locations
					- Click on 'Add New Location' button
					- Provide required location details and click OK
	 * Expected Results: User is navigated to Manage Locations page and new location entry is present in the table
	 * Current implementation:   
	 * Current Issue:
     * Future Improvement:
	 */	
	@Test
	public void CUSTADM023() {
		String locationName = testSetup.getRandomNumber() + "custadm023";
		
		System.out.println("\nRunning - CUSTADM023 - Test Description: Add location\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		manageLocationsAdminPage.open();
		manageLocationsAdminPage.addNewLocation(locationName);
		
		assertTrue(manageLocationsAdminPage.findExistingLocation(SQACUS, locationName));
	}
	
	/**
	 * Test Case ID: CUSTADM024
	 * Test Description: Edit existing location
	 * Test Script: - On Home Page, click Administration -> Manage Locations
					- Click Edit link
					- Modify location details and click OK
	 * Expected Results: User is navigated to Manage Locations page and modified location details are present in the table
	 * Current implementation:   
	 * Current Issue:
     * Future Improvement:
	 */	
	@Test
	public void CUSTADM024() {
		String locationName = testSetup.getRandomNumber() + "custadm024";
		String locationNameNew = testSetup.getRandomNumber() + "custadm024" + "_New";
		
		System.out.println("\nRunning - CUSTADM024 - Test Description: Edit existing location\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA,  USERPASSWORD);
		
		manageLocationsAdminPage.open();
		manageLocationsAdminPage.addNewLocation(locationName);
		
		manageLocationsAdminPage.editExistingLocation(SQACUS, locationName,locationNameNew);
		assertTrue(manageLocationsAdminPage.findExistingLocation(SQACUS, locationNameNew));
	}
	
	/**
	 * Test Case ID: CUSTADM025
	 * Test Description: Customer Admin not allowed to create duplicate Location
	 * Test Script: - On Home Page, click Administration -> Manage Locations
					- Click on 'Add New Location' button
					- Provide location details same as existing location and click OK
	 * Expected Results: Duplicate Location creation not allowed
	 * Current implementation:   
	 * Current Issue:
     * Future Improvement:
	 */	
	@Test
	public void CUSTADM025() {
		String locationName = testSetup.getRandomNumber() + "custadm025";
		
		System.out.println("\nRunning - CUSTADM025 - Test Description: Customer Admin not allowed to create duplicate Location\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA,  USERPASSWORD);
		
		manageLocationsAdminPage.open();
		manageLocationsAdminPage.addNewLocation(locationName);
		
		assertFalse(manageLocationsAdminPage.addNewLocation(locationName, true));
	}
	
	/**
	 * Test Case ID: CUSTADM026
	 * Test Description: add location- blank required fields
	 * Test Script: - On Home Page, click Administration -> Manage Locations
					- Click on 'Add New Location' button
					- Keep description field blank. Click OK
	 * Expected Results: "Please fill out this field." message should be displayed 
	 * Current implementation:   
	 * Current Issue:
     * Future Improvement: deal with the tooltip text
	 */	
	@Test
	public void CUSTADM026() {
		System.out.println("\nRunning - CUSTADM026 - Test Description: add location- blank required fields\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA,  USERPASSWORD);
		
		manageLocationsAdminPage.open();
		assertFalse(manageLocationsAdminPage.addNewLocation("", true));
	}
	
	/**
	 * Test Case ID: CUSTADM027
	 * Test Description: edit location- blank required fields
	 * Test Script: - On Home Page, click Administration -> Manage Locations
					- Click on Edit link
					- Delete description field data. Click OK
	 * Expected Results: "Please fill out this field." message should be displayed
	 * Current implementation:   
	 * Current Issue:
     * Future Improvement: deal with the tooltip text
	 */	
	@Test
	public void CUSTADM027() {
		String locationName = testSetup.getRandomNumber() + "custadm027";
		
		System.out.println("\nRunning - CUSTADM027 - Test Description: edit location- blank required fields\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA,  USERPASSWORD);
		
		manageLocationsAdminPage.open();
		manageLocationsAdminPage.addNewLocation(locationName);
		
		assertFalse(manageLocationsAdminPage.editExistingLocation(SQACUS, locationName, ""));
	}
}
