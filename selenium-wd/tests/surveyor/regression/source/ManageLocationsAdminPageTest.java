/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.BLANKFIELDERROR;
import static surveyor.scommon.source.SurveyorConstants.CUSTOMERNAMEPREFIX;
import static surveyor.scommon.source.SurveyorConstants.EULASTRING;
import static surveyor.scommon.source.SurveyorConstants.NOMATCHINGSEARCH;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_100;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_25;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_50;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.PICNAMEPREFIX;
import static surveyor.scommon.source.SurveyorConstants.REGBASEPICUSERNAME;
import static surveyor.scommon.source.SurveyorConstants.RNELAT;
import static surveyor.scommon.source.SurveyorConstants.RNELON;
import static surveyor.scommon.source.SurveyorConstants.SQACUS;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONECT;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;
import static surveyor.scommon.source.SurveyorConstants.USERROLEADMIN;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsAdminPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageRefGasBottlesAdminPage;
import surveyor.scommon.source.ManageSurveyorAdminPage;
import surveyor.scommon.source.ManageUsersAdminPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorConstants.UserTimezone;
import surveyor.scommon.source.SurveyorTestRunner;
import common.source.BaseHelper;
import common.source.Log;

/**
 * @author zlu
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ManageLocationsAdminPageTest extends SurveyorBaseTest {
	private static HomePage homePage;
	private static ManageLocationsPage manageLocationsPage;
	private static ManageUsersPage manageUsersPage;
	private static ManageLocationsAdminPage manageLocationsAdminPage;
	private static ManageCustomersPage manageCustomersPage;
	private static ManageUsersAdminPage manageUsersAdminPage;
	private static ManageRefGasBottlesAdminPage manageRefGasBottlesAdminPage;
	private static ManageSurveyorAdminPage manageSurveyorAdminPage;

	@BeforeClass
	public static void setupManageLocationsAdminPageTest() {
		homePage = new HomePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, homePage);

		manageLocationsPage = new ManageLocationsPage(driver, baseURL,
				testSetup);
		PageFactory.initElements(driver, manageLocationsPage);

		manageLocationsAdminPage = new ManageLocationsAdminPage(driver,
				baseURL, testSetup);
		PageFactory.initElements(driver, manageLocationsAdminPage);

		manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageUsersPage);

		manageCustomersPage = new ManageCustomersPage(driver, baseURL,
				testSetup);
		PageFactory.initElements(driver, manageCustomersPage);

		manageUsersAdminPage = new ManageUsersAdminPage(driver, baseURL,
				testSetup);
		PageFactory.initElements(driver, manageUsersAdminPage);

		manageRefGasBottlesAdminPage = new ManageRefGasBottlesAdminPage(driver,
				testSetup, baseURL);
		PageFactory.initElements(driver, manageRefGasBottlesAdminPage);

		manageSurveyorAdminPage = new ManageSurveyorAdminPage(driver, baseURL,
				testSetup);
		PageFactory.initElements(driver, manageSurveyorAdminPage);
	}

	/**
	 * Test Case ID: TC21_CancelLatLongSelector_PicAdmin
	 * Test Description: Verify Cancel button of Lat/Long map Selector screen while navigating through Add location screen as Picarro Admin 
	 * Test Script: 
	 *  - Log in as Picarro Admin
	 *  - On Home Page, click Picarro Administration -> Manage Locations
	 *  - Click on 'Add New Location' button
	 *  - Click on 'Lat/Long' Selector button
	 *  - Click on desired location on map and click Cancel
	 * Excepted Result:
	 *  - A map appears with a pin in the default location
	 *  - The map disappears and the Latitude and Longitude fields remain blank
	 */
	@Test
	public void TC21_CancelLatLongSelector_PicAdmin(){
		final int xOffset = 98;
		final int yOffset = 99;

		Log.info("\nRunning - TC21_CancelLatLongSelector_PicAdmin - "+
				"Test Description: Verify Cancel button of adding Lat/Long on map screen\n");

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageLocationsPage.open();
		manageLocationsPage.clickOnAddNewLocationBtn();
		manageLocationsPage.clickOnLatLongSelectorBtn();
		manageLocationsPage.selectOnLatLong(xOffset, yOffset);

		manageLocationsPage.clickOnLatLongCancelBtn();

		assertEquals("The location latitude should be blank after the cancelation in the map screen",
				"",manageLocationsPage.getLocationLatitudeText());

		assertEquals("The location longitude should be blank after the cancelation in the map screen",
				"",manageLocationsPage.getLocationLongitudeText());
	}

	/**
	 * Test Case ID: TC22_CancelEditLatLongSelector_PicAdmin
	 * Test Description: Verify Cancel button of Lat/Long map Selector screen while navigating through Edit location screen as Picarro Admin  
	 * Test Script: 
	 *  - Log in as Picarro Admin
	 *  - On Home Page, click Picarro Administration -> Manage Locations
	 *  - Click on 'Edit' button
	 *  - Click on 'Lat/Long Selector' button
	 *  - Click on desired location on map and click Cancel
	 * Excepted Result:
	 *  - A map appears with a pin in the default location
	 *  - The map disappears and the Latitude and Longitude fields remain blank
	 */
	@Test
	public void TC22_CancelEditLatLongSelector_PicAdmin(){
		final int xOffset = 101;
		final int yOffset = 111;

		String customerName = CUSTOMERNAMEPREFIX + testSetup.getFixedSizeRandomNumber(12) + "TC22";
		String eula = customerName + ": " + EULASTRING;
		String location = "Santa Clara";
		
		Log.info("\nRunning - TC22_CancelEditLatLongSelector_PicAdmin - "+
				"Test Description: Verify Cancel button of editing Lat/Long on map screen\n");		
		
		// *** Add a new location/customer for this test ***
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(location, customerName, location);

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageLocationsPage.open();
		manageLocationsPage.performSearch(location);
		manageLocationsPage.findExistingLocationAndClickEdit(customerName, location);

		String expectedLat = manageLocationsPage.getLocationLatitudeText();
		String expectedLong = manageLocationsPage.getLocationLongitudeText();

		manageLocationsPage.clickOnLatLongSelectorBtn();
		manageLocationsPage.selectOnLatLong(xOffset, yOffset);		
		manageLocationsPage.clickOnLatLongCancelBtn();		

		String actualLat = manageLocationsPage.getLocationLatitudeText();
		String actualLong = manageLocationsPage.getLocationLongitudeText();		
		assertEquals("The location [latitude,longitude] shouldn't be changed after the cancelation of the map editing",
				expectedLat+","+expectedLong, actualLat+","+actualLong);

		manageLocationsPage.clickOnCancelBtn();
	}

	/**
	 * Test Case ID: TC23_ConfirmLatLongSelector_PicAdmin
	 * Test Description: Confirm that map accurately locates manually entered Latitude and Longitude values
	 * Test Script: 
	 *  - Log in as Picarro Admin
	 *  - On Home Page, click Picarro Administration -> Manage Locations
	 *  - Click on 'Add New Location' button
	 *  - Manually enter Latitude and Longitude values
	 *  - Click on 'Lat/Long Selector' button
	 * Excepted Result:
	 *  - The map should display the correct point for the given coordinates, not the default location
	 */
	@Test
	public void TC23_ConfirmLatLongSelector_PicAdmin(){

		String latitude = "50.00000";
		String longitude = "50.00000";

		Log.info("\nRunning - TC23_ConfirmLatLongSelector_PicAdmin - "+
				"Test Description: Confirm that map accurately locates manually entered Latitude and Longitude values\n");

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageLocationsPage.open();
		manageLocationsPage.clickOnAddNewLocationBtn();
		manageLocationsPage.inputLatLong(latitude, longitude);		
		manageLocationsPage.clickOnLatLongSelectorBtn();	

		String actualPoint = manageLocationsPage.getSelectedPoint();		
		manageLocationsPage.clickOnLatLongCancelBtn();

		assertEquals(latitude+", "+longitude,actualPoint);

	}

	/**
	 * Test Case ID: TC24_NotificationLatLongValueMissing_PicAdmin
	 * Test Description: Notification should appear if Latitude is entered but Longitude is not, or vice versa
	 * Test Script: 
	 *  - Log in as Picarro Admin
	 *  - On Home Page, click Picarro Administration -> Manage Locations
	 *  - Click on 'Add New Location' button
	 *  - Enter a Latitude value and leave Longitude blank
	 *  - Click on OK button
	 *  - Enter a Longitude value and delete the Latitude value
	 *  - Click on OK button
	 * Excepted Result:
	 *  - A message will appear under the Longitude field, "This field is required"
     *  - A message will appear under the Latitude field, "This field is required"
	 */
	@Test
	public void TC24_NotificationLatLongValueMissing_PicAdmin(){

		String latitude = "50.00000";
		String longitude = "50.00000";

		Log.info("\nRunning - TC24_NotificationLatLongValueMissing_PicAdmin - "+
				"Test Description: Notification should appear if Latitude is entered but Longitude is not, or vice versa\n");

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageLocationsPage.open();
		manageLocationsPage.clickOnAddNewLocationBtn();
		manageLocationsPage.inputLatLong(latitude, "");		
		manageLocationsPage.clickOnOkBtn();

		assertEquals(BLANKFIELDERROR, manageLocationsPage.getLocationLongitudeError());

		manageLocationsPage.inputLatLong("", longitude);		
		manageLocationsPage.clickOnOkBtn();

		assertEquals(BLANKFIELDERROR, manageLocationsPage.getLocationLatitudeError());

		manageLocationsPage.clickOnCancelBtn();

	}	



	/**
	 * Test Case ID: TC1236_CheckTimeZone_PicAdmin Test Description: 
	   Check Timezone and User Name drop down menu working on Add and Edit Location Page
	 * Test Script: - On Home Page, click Picarro Administration -> Manage Locations 
                    - Click on 'Add New Location' button
                    - Click on Timezone drop menu present in header and change the timezone
                    - Click on User_Name (eg. Administrator)
                    - Click on Log Out link
	 * Expected Results: - User should be able to change the timezone successfully
                         - User should see all the drop down menu - Preferences, Change Password, Release Notes, Manual and Log out links are present
                         - User should be able to logout sucessfully
	 */

	@Test
	public void TC1236_CheckTimeZone_PicAdmin(){
		String userName = PICNAMEPREFIX + "ad" + testSetup.getRandomNumber() + REGBASEPICUSERNAME;
		String customer = "Picarro";
		String location = "Santa Clara";
		String locationDesc = customer + " - " + location;

		Log.info("\nRunning - TC1236_CheckTimeZone_PicAdmin - " +
				"Test Description: Check Timezone change\n");

		// *** Add a new admin user for this test ***
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		manageUsersPage.open();
		manageUsersPage.addNewPicarroUser(userName, USERPASSWORD, USERROLEADMIN, locationDesc, TIMEZONECT);
		manageUsersAdminPage.logout();

		// *** Start test ***

		UserTimezone[] uts = UserTimezone.values();
		UserTimezone ut = uts[0];

		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);

		manageLocationsPage.open();
		manageLocationsPage.clickOnAddNewLocationBtn();

		assertTrue("Failed to change user timezone - '"+ ut+"'",
				manageLocationsPage.changeUserTimezone(ut));

		assertTrue("Dropdown menu item(s) are missing", 
				manageLocationsPage.verifyDropdownMenuItems());
		loginPage = manageUsersPage.logout();

		loginPage.loginNormalAs(userName, USERPASSWORD);
		assertEquals("User timezone has not retained after relogin - '"+ ut+"'",
				ut.toString(),manageLocationsPage.getUserTimezone());


	}

	/**
	 * Test Case ID: TC459_EditLocation_CustUA Test Description: Edit existing
	 * location Test Script: - On Home Page, click Administration -> Manage
	 * Locations - Click Edit link - Modify location details and click OK
	 * Expected Results: User is navigated to Manage Locations page and modified
	 * location details are present in the table Current implementation: Current
	 * Issue: Future Improvement:
	 */
	@Test
	public void TC459_EditLocation_CustUA() {
		String locationName = testSetup.getRandomNumber() + "TC459";
		String locationNameNew = testSetup.getRandomNumber() + "TC459" + "_New";
		String cityName = "Santa Clara";

		Log.info("\nRunning - TC459_EditLocation_CustUA - Test Description: Edit existing location\n");

		// Add Location as Picarro admin.
		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, SQACUS, cityName);

		// Edit Location as Utility admin.
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		manageLocationsAdminPage.open();
		manageLocationsAdminPage.editPDExistingLocation(SQACUS, locationName,
				locationNameNew);
		assertTrue(manageLocationsAdminPage.findExistingLocation(SQACUS,
				locationNameNew));
	}

	/**
	 * Test Case ID: TC460_EditLocation_DuplicateLocationdetails Test
	 * Description: Customer Admin not allowed to edit location name same as
	 * existing one Script: - On Home Page, click Administration -> Manage
	 * Locations - Click Edit button - Provide location details same as existing
	 * location and click OK Expected Results: Duplicate Location creation not
	 * allowed
	 */
	@Test
	public void TC460_DuplicateLocationNotAllowed_CustUA() {
		String locationName1 = testSetup.getRandomNumber() + "TC460_1";
		String locationName2 = testSetup.getRandomNumber() + "TC460_2";
		String cityName = "Santa Clara";

		Log.info("\nRunning - TC460_DuplicateLocationNotAllowed_CustUA - Test Description: Customer Admin not allowed to edit location name same as existing one\n");

		// Add Location as Picarro admin.
		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName1, SQACUS, cityName);
		manageLocationsPage.addNewLocation(locationName2, SQACUS, cityName);

		// Edit Location as Utility admin.
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		manageLocationsAdminPage.open();

		assertFalse(manageLocationsAdminPage.editPDExistingLocation(SQACUS,
				locationName1, locationName2));
		assertTrue(manageLocationsAdminPage.isDuplicateLocMsgPresent());
	}

	/**
	 * Test Case ID: TC461_EditLocBlankRequiredFields_CustUA Test Description:
	 * edit location- blank required fields Test Script: - On Home Page, click
	 * Administration -> Manage Locations - Click on Edit link - Delete
	 * description field data. Click OK Expected Results:
	 * "This field is required" message should be displayed Current
	 * implementation: Current Issue: Future Improvement: deal with the tooltip
	 * text
	 */
	@Test
	public void TC461_EditLocBlankRequiredFields_CustUA() {
		String locationName = testSetup.getRandomNumber() + "TC461";
		String cityName = "Santa Clara";

		Log.info("\nRunning - TC461_EditLocBlankRequiredFields_CustUA - Test Description: edit location- blank required fields\n");

		// Add Location as Picarro admin.
		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, SQACUS, cityName);

		// Edit Location as Utility user. Enter empty description field.
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		// NOTE: The check for required field message is done in edit method.
		manageLocationsAdminPage.open();
		assertFalse(manageLocationsAdminPage.editPDExistingLocation(SQACUS,
				locationName, "", RNELAT, RNELON));
	}

	/**
	 * Test Case ID: TC462_EditLoc50CharLimit_CustUA Test Description: More than
	 * 50 characters not allowed in Location Description field Test Script: - On
	 * Home Page, and click Administration -> Manage Locations - Click on 'Add
	 * New Location' - Provide more than 50 characters in Location Description
	 * field. - Click OK Expected Results: User cannot enter more than 50
	 * characters and message having limit of characters displayed Current
	 * implementation: Current Issue: Future Improvement:
	 */
	@Test
	public void TC462_EditLoc50CharLimit_CustUA() {
		String str34chars = "AbcdefghI-AbcdefghI-AbcdefghI-Abcd";
		String str35chars = "AbcdefghI-AbcdefghI-AbcdefghI-Abcde";
		String cityName = "Santa Clara";

		String locationName50Chars = testSetup.getFixedSizeRandomNumber(11)
				+ "TC462" + str34chars;
		String locationName51Chars = testSetup.getFixedSizeRandomNumber(11)
				+ "TC462" + str35chars;

		Log.info("\nRunning - TC462_EditLoc50CharLimit_CustUA - Test Description: More than 50 characters not allowed in Location Description field\n");

		// Add Location as Picarro admin.
		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageLocationsPage.open();

		manageLocationsPage.addNewLocation(locationName50Chars, SQACUS,
				cityName);
		manageLocationsPage.addNewLocation(locationName51Chars, SQACUS,
				cityName);

		// Edit location as Utility admin. 50 characters works. 51 characters
		// doesn't work.
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		manageLocationsAdminPage.open();
		assertTrue(manageLocationsAdminPage.findExistingLocation(SQACUS,
				locationName50Chars));
		manageLocationsAdminPage.open();
		assertFalse(manageLocationsAdminPage.findExistingLocation(SQACUS,
				locationName51Chars));
	}

	/**
	 * Test Case ID: TC466_VerifyCancelButtonAllScreens_CustAdmin Test
	 * Description: Verify Cancel button for all customer admin screens Test
	 * Script: - Click on Cancel button present on all administrator screen
	 * Expected Results: - User action is Canceled and existing data persists
	 * Current implementation: Current Issue: Future Improvement:
	 */
	@Test
	public void TC466_VerifyCancelButtonAllScreens_CustAdmin() {
		String curURL;

		Log.info("\nRunning - TC466_VerifyCancelButtonAllScreens_CustAdmin - Test Description: Verify Cancel button for all customer admin screens\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		manageSurveyorAdminPage.open();
		curURL = driver.getCurrentUrl();
		manageSurveyorAdminPage.clickOnCustomerFirstEditSurveyorBtn();
		manageSurveyorAdminPage.waitForEditPageLoad();
		manageSurveyorAdminPage.getBtnCancel().click();
		assertTrue(manageSurveyorAdminPage.getStrPageURL().equalsIgnoreCase(
				curURL));

		manageUsersAdminPage.open();
		curURL = driver.getCurrentUrl();
		manageUsersAdminPage.getBtnAddNewUser().click();
		manageUsersAdminPage.waitForNewPageLoad();
		manageUsersAdminPage.getBtnCancel().click();
		assertTrue(manageUsersAdminPage.getStrPageURL()
				.equalsIgnoreCase(curURL));
		manageUsersAdminPage.clickOnCustomerFirstEditUserBtn();
		manageUsersAdminPage.waitForEditPageLoad();
		manageUsersAdminPage.getBtnCancel().click();
		assertTrue(manageUsersAdminPage.getStrPageURL()
				.equalsIgnoreCase(curURL));

		manageLocationsAdminPage.open();
		curURL = driver.getCurrentUrl();
		manageLocationsAdminPage.clickOnFirstEditLocationBtn();
		manageLocationsAdminPage.waitForEditPageLoad();
		manageLocationsAdminPage.getBtnCancel().click();
		assertTrue(manageLocationsAdminPage.getStrPageURL().equalsIgnoreCase(
				curURL));

		manageRefGasBottlesAdminPage.open();
		curURL = driver.getCurrentUrl();
		manageRefGasBottlesAdminPage.getBtnAddNewRefGasBottle().click();
		manageRefGasBottlesAdminPage.waitForNewPageLoad();
		manageRefGasBottlesAdminPage.getBtnCancel().click();
		assertTrue(manageRefGasBottlesAdminPage.getStrPageURL()
				.equalsIgnoreCase(curURL));
	}

	/**
	 * Test Case ID: TC450_ManageLocationsAdminPagination Test Description:
	 * Pagination (Manage Locations Customer Admin) Test Script: 10,25,50 and
	 * 100 records selection on all Customer Administration screens Expected
	 * Results: Specified number of records will be listed in the table Future
	 */
	@Test
	public void TC450_ManageLocationsAdminPagination() {
		List<String> locationList;

		Log.info("\nRunning - TC450_ManageLocationsAdminPagination - Test Description: Pagination (Manage Locations Customer Admin)\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		manageLocationsAdminPage.open();
		manageLocationsAdminPage.setPagination(PAGINATIONSETTING);

		locationList = manageLocationsAdminPage.getLocationList(false,
				Integer.valueOf(PAGINATIONSETTING));
		assertTrue(locationList.size() <= Integer.valueOf(PAGINATIONSETTING));
		assertTrue(manageLocationsAdminPage.getListSize(locationList));

		manageLocationsAdminPage.open();
		manageLocationsAdminPage.setPagination(PAGINATIONSETTING_25);

		locationList = manageLocationsAdminPage.getLocationList(false,
				Integer.valueOf(PAGINATIONSETTING_25));

		assertTrue(manageLocationsAdminPage.getListSize(locationList));

		manageLocationsAdminPage.open();
		manageLocationsAdminPage.setPagination(PAGINATIONSETTING_50);

		locationList = manageLocationsAdminPage.getLocationList(false,
				Integer.valueOf(PAGINATIONSETTING_50));

		assertTrue(locationList.size() <= Integer.valueOf(PAGINATIONSETTING_50));

		assertTrue(manageLocationsAdminPage.getListSize(locationList));

		manageLocationsAdminPage.open();
		manageLocationsAdminPage.setPagination(PAGINATIONSETTING_100);

		locationList = manageLocationsAdminPage.getLocationList(false,
				Integer.valueOf(PAGINATIONSETTING_100));

		assertTrue(locationList.size() <= Integer
				.valueOf(PAGINATIONSETTING_100));

		assertTrue(manageLocationsAdminPage.getListSize(locationList));
	}

	/**
	 * Test Case ID: TC451 Test Description: Search valid location record
	 */
	@Test
	public void TC451_SearchValidLocation() {
		Log.info("\nRunning - TC451 - Test Description: Search valid location record\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		manageLocationsAdminPage.open();
		assertTrue(manageLocationsAdminPage.searchLocation(SQACUS, SQACUSLOC));
	}

	/**
	 * Test Case ID: TC452 Test Description: Search invalid location record
	 */
	@Test
	public void TC452_SearchInvalidLocation() {
		String location = SQACUSLOC + testSetup.getRandomNumber();

		Log.info("\nRunning - TC452 - Test Description: Search invalid location record\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		manageLocationsAdminPage.open();
		manageLocationsAdminPage.waitForPageLoad();
		manageLocationsAdminPage.getInputSearch().sendKeys(location);
		manageLocationsAdminPage.waitForPageToLoad();

		assertTrue(manageLocationsAdminPage.getLabelNoMatchingSearch()
				.equalsIgnoreCase(NOMATCHINGSEARCH));
	}

	/**
	 * Test Case ID: TC453 Test Description: Sort location records based on
	 * attributes present
	 */
	@Test
	public void TC453_SortLocationRecords() {
		List<String> list = new ArrayList<String>();
		Log.info("\nRunning - TC453 - Test Description: Sort location records based on attributes present\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		manageLocationsAdminPage.open();

		manageLocationsAdminPage.getTheadLocation().click();
		list = manageLocationsAdminPage.getLocationList(false,
				Integer.valueOf(PAGINATIONSETTING_100));
		assertTrue(BaseHelper.isStringListSorted(list));
		manageLocationsAdminPage.getTheadLocation().click();
		list = manageLocationsAdminPage.getLocationList(false,
				Integer.valueOf(PAGINATIONSETTING_100));
		assertTrue(BaseHelper.isStringListSortedDes(list));
	}
}

