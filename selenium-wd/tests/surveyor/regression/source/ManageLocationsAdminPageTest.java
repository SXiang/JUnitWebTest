/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsAdminPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageRefGasBottlesAdminPage;
import surveyor.scommon.source.ManageUsersAdminPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.SurveyorBaseTest;

/**
 * @author zlu
 *
 */
public class ManageLocationsAdminPageTest extends SurveyorBaseTest {
	private static HomePage homePage;
	private static ManageLocationsPage manageLocationsPage;
	private static ManageUsersPage manageUsersPage;
	private static ManageLocationsAdminPage manageLocationsAdminPage;
	private static ManageCustomersPage manageCustomersPage;
	private static ManageUsersAdminPage manageUsersAdminPage;
	private static ManageRefGasBottlesAdminPage manageRefGasBottlesAdminPage;

	@BeforeClass
	public static void setupManageLocationsAdminPageTest() {
		homePage = new HomePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, homePage);

		manageLocationsPage = new ManageLocationsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageLocationsPage);

		manageLocationsAdminPage = new ManageLocationsAdminPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageLocationsAdminPage);

		manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageUsersPage);

		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageCustomersPage);

		manageUsersAdminPage = new ManageUsersAdminPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageUsersAdminPage);

		manageRefGasBottlesAdminPage = new ManageRefGasBottlesAdminPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver, manageRefGasBottlesAdminPage);
	}

	/**
	 * Test Case ID: TC489_AddLocation_CustUA 
	 * Test Description: Add location 
	 * Test Script: - On Home Page, click Administration -> Manage Locations 
	 * - Click on 'Add New Location' button 
	 * - Provide required location details and click OK
	 * Expected Results: User is navigated to Manage Locations page and new
	 * location entry is present in the table 
	 * Current implementation: 
	 * Current Issue: 
	 * Future Improvement:
	 */
	@Test
	public void TC489_AddLocation_CustUA() {
		String locationName = testSetup.getRandomNumber() + "TC489";
		String cityName = "Santa Clara";

		System.out.println("\nRunning - TC489_AddLocation_CustUA - Test Description: Add location\n");

		// Add Location as Picarro admin.
		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, SQACUS, cityName);

		// Verify location as Utility Admin.
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		manageLocationsAdminPage.open();
		assertTrue(manageLocationsAdminPage.findExistingLocation(SQACUS, locationName));
	}

	/**
	 * Test Case ID: TC459_EditLocation_CustUA 
	 * Test Description: Edit existing location Test
	 * Script: - On Home Page, click Administration -> Manage Locations 
	 * - Click Edit link 
	 * - Modify location details and click OK 
	 * Expected Results: User is navigated to Manage Locations page and modified location details are
	 * present in the table 
	 * Current implementation: 
	 * Current Issue: 
	 * Future Improvement:
	 */
	@Test
	public void TC459_EditLocation_CustUA() {
		String locationName = testSetup.getRandomNumber() + "TC459";
		String locationNameNew = testSetup.getRandomNumber() + "TC459" + "_New";
		String cityName = "Santa Clara";

		System.out.println("\nRunning - TC459_EditLocation_CustUA - Test Description: Edit existing location\n");

		// Add Location as Picarro admin.
		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, SQACUS, cityName);

		// Edit Location as Utility admin.
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		manageLocationsAdminPage.open();
		manageLocationsAdminPage.editExistingLocation(SQACUS, locationName, locationNameNew);
		assertTrue(manageLocationsAdminPage.findExistingLocation(SQACUS, locationNameNew));
	}

	/**
	 * Test Case ID: TC461_EditLocBlankRequiredFields_CustUA 
	 * Test Description: edit location- blank required fields 
	 * Test Script: - On Home Page, click Administration -> Manage Locations 
	 * - Click on Edit link 
	 * - Delete description field data. Click OK
	 * Expected Results: "Please fill out this field." message should be displayed 
	 * Current implementation: 
	 * Current Issue: 
	 * Future Improvement: deal with the tooltip text
	 */
	@Test
	public void TC461_EditLocBlankRequiredFields_CustUA() {
		String locationName = testSetup.getRandomNumber() + "TC461";
		String cityName = "Santa Clara";

		System.out.println("\nRunning - TC461_EditLocBlankRequiredFields_CustUA - Test Description: edit location- blank required fields\n");

		// Add Location as Picarro admin.
		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, SQACUS, cityName);

		// Edit Location as Utility user. Enter empty description field.
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		manageLocationsAdminPage.open();
		assertFalse(manageLocationsAdminPage.editExistingLocation(SQACUS, locationName, ""));
	}

	/**
	 * Test Case ID: TC462_EditLoc50CharLimit_CustUA
	 * Test Description: More than 50 characters not allowed in Location Description field 
	 * Test Script: 
	 * - On Home Page, and click Administration -> Manage Locations 
	 * - Click on 'Add New Location'
	 * - Provide more than 50 characters in Location Description field.
	 * - Click OK 
	 * Expected Results: User cannot enter more than 50 characters and
	 * message having limit of characters displayed Current implementation:
	 * Current Issue: Future Improvement:
	 */
	@Test
	public void TC462_EditLoc50CharLimit_CustUA() {
		String str34chars = "AbcdefghI-AbcdefghI-AbcdefghI-Abcd";
		String str35chars = "AbcdefghI-AbcdefghI-AbcdefghI-Abcde";
		String cityName = "Santa Clara";

		String locationName50Chars = testSetup.getFixedSizeRandomNumber(11) + "TC462" + str34chars;
		String locationName51Chars = testSetup.getFixedSizeRandomNumber(11) + "TC462" + str35chars;

		System.out.println(
				"\nRunning - TC462_EditLoc50CharLimit_CustUA - Test Description: More than 50 characters not allowed in Location Description field\n");

		// Add Location as Picarro admin.
		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageLocationsPage.open();
		
		manageLocationsPage.addNewLocation(locationName50Chars, SQACUS, cityName);
		manageLocationsPage.addNewLocation(locationName51Chars, SQACUS, cityName);

		// Edit location as Utility admin. 50 characters works. 51 characters
		// doesn't work.
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		manageLocationsAdminPage.open();
		assertTrue(manageLocationsAdminPage.findExistingLocation(SQACUS, locationName50Chars));
		manageLocationsAdminPage.open();
		assertFalse(manageLocationsAdminPage.findExistingLocation(SQACUS, locationName51Chars));
	}

	/**
	 * Test Case ID: TC466_VerifyCancelButtonAllScreens_CustAdmin 
	 * Test Description: Verify Cancel button for all customer admin screens 
	 * Test Script: - Click on Cancel button present on all administrator screen 
	 * Expected Results: - User action is Canceled and existing data persists 
	 * Current implementation: 
	 * Current Issue: 
	 * Future Improvement:
	 */
	@Test
	public void TC466_VerifyCancelButtonAllScreens_CustAdmin() {
		String curURL;

		System.out.println(
				"\nRunning - TC466_VerifyCancelButtonAllScreens_CustAdmin - Test Description: Verify Cancel button for all customer admin screens\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		manageUsersAdminPage.open();
		curURL = driver.getCurrentUrl();
		manageUsersAdminPage.getBtnAddNewUser().click();
		manageUsersAdminPage.getBtnCancel().click();
		assertTrue(manageUsersAdminPage.getStrPageURL().equalsIgnoreCase(curURL));

		manageRefGasBottlesAdminPage.open();
		curURL = driver.getCurrentUrl();
		manageRefGasBottlesAdminPage.getBtnAddNewRefGasBottle().click();
		manageRefGasBottlesAdminPage.getBtnCancel().click();
		assertTrue(manageRefGasBottlesAdminPage.getStrPageURL().equalsIgnoreCase(curURL));
	}
}
