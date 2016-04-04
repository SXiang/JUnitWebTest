/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.CryptoUtility;
import common.source.Log;
import surveyor.dataprovider.UserDataProvider;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ManageLocationsPageTest extends SurveyorBaseTest {
	private static ManageLocationsPage manageLocationsPage;
	private static ManageCustomersPage manageCustomersPage;
	
	@BeforeClass
	public static void setupManageLocationsPageTest() {
		manageLocationsPage = new ManageLocationsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageLocationsPage);
		
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
	}
	// TODO [GENERIC]: Utilize data driven approach for Username/Password.
	
	/**
	 * Test Case ID: TC16_AddLocationUsingLatLongSelector_PicAdmin
	 * Script: -  	
	 * - Log in as Picarro Admin
	 * - On Home Page, click Picarro Administration -> Manage Locations
	 * - Click on 'Add New Location' button
	 * - Click on 'Lat/Long Selector' button
	 * - Click on desired location on map (SHIFT+ENTER+MouseClick) and click OK    	 
	 * Results: - 
	 * - A map appears with a pin in the default location
	 * - The Latitude and Longitude fields are auto-populated and accurately reflect the coordinates of the selected point
	 * - User is navigated to Manage Locations page and new location entry is present in the table	 
	 */
	@Test
	@UseDataProvider(value =UserDataProvider.USER_ADMIN_SUPPORT_PROVIDER, location=UserDataProvider.class )
	public void TC16_TC18_AddLocationUsingLatLongSelector_PicAdminSupport(String user, String pswd ) {
		String tcID ;
		if(user.equalsIgnoreCase("AutomationAdmin")){
			tcID ="TC16";
		}else {
			tcID ="TC18";
		}
		String password = CryptoUtility.decrypt(pswd);
		String locationName = testSetup.getFixedSizeRandomNumber(8) + tcID;
		String cityName = "Santa Clara";
		System.out.println("user: "+user);
		System.out.println("pswd: "+pswd);
		System.out.println("------------------------");
		Log.info("\nRunning -"+ tcID+"_AddLocationUsingLatLongSelector_PicAdmin - Test Description: Add new location\n");
		// Add Location as Picarro admin.
		loginPage.open();
		loginPage.loginNormalAs(user, password);

		manageLocationsPage.open();
		manageLocationsPage.addNewLocationUsingLatLongSelector(locationName, SQACUS, cityName);
		
		
		// TODO$: Check the locator icon shows up correctly in the selector dialog.
		
		assertTrue(manageLocationsPage.findExistingLocation(SQACUS, locationName));
	}
		
	/**
	 * Test Case ID: TC17_TC19_EditLocationAddedUsingLatLongSelector_PicAdmin
	 * Script: -  	
	 * - Log in as Picarro Admin
	 * - On Home Page, click Picarro Administration -> Manage Locations
	 * - Click on 'Edit' button
	 * - Click on 'Lat/Long Selector' button
	 * - Click on desired location on map away from original location (SHIFT+ENTER+MouseClick) and click OK
	 * - Fill in other required fields on Edit Location page and click OK  	 
	 * Results: - 
	 * - A map appears with a pin in the original location
	 * - The new Latitude and Longitude fields accurately reflect the position of the newly selected point
	 * - User is navigated to Manage Locations page and Latitude and Longitude have new values 
	 */
	@Test
	@UseDataProvider(value =UserDataProvider.USER_ADMIN_SUPPORT_PROVIDER, location=UserDataProvider.class )
	public void TC17_TC19_EditLocationAddedUsingLatLongSelector_PicAdmin_PicSupport(String user, String pwd) {
		String tcID;
		if (user.equalsIgnoreCase("AutomationAdmin")) {
			tcID = "TC17";
		} else {
			tcID = "TC19";
		}
		String password = CryptoUtility.decrypt(pwd);
		String locationName = testSetup.getFixedSizeRandomNumber(8) + tcID;
		String locationNameNew = testSetup.getFixedSizeRandomNumber(8) + tcID
				+ "_New";
		String cityName = "Santa Clara";

		Log.info("\nRunning -"
				+ tcID
				+ "_EditLocationAddedUsingLatLongSelector - Test Description: Add new location\n");

		// Add Location as Picarro admin.	
		loginPage.open();
		loginPage.loginNormalAs(user, password);

		manageLocationsPage.open();
		manageLocationsPage.addNewLocationUsingLatLongSelector(locationName, SQACUS, cityName);
		
		// TODO$: Check the locator icon shows up correctly in the selector dialog.
		
		manageLocationsPage.editPDExistingLocation(SQACUS, locationName, locationNameNew);
		assertTrue(manageLocationsPage.findExistingLocation(SQACUS, locationNameNew));
	}
	
	/**
	 * Test Case ID: TC60_AddLocation_PicAdmin
	 * Test Description: Adding Location
	 * 
	 */
	@Test
	@UseDataProvider(value =UserDataProvider.USER_ADMIN_SUPPORT_PROVIDER, location=UserDataProvider.class )
	public void TC60_TC489_AddLocation_PicAdmin_PicSupport(String user, String pwd) {
		String tcID;
		if (user.equalsIgnoreCase("AutomationAdmin")) {
			tcID = "TC60";
		} else {
			tcID = "TC489";
		}
		String password = CryptoUtility.decrypt(pwd);
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + tcID;
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String cityName="Santa Clara";
	
		
		Log.info("\nRunning TC60_TC489_AddLocation_PicAdmin_PicSupport - Test Description: Adding Location");
		
		loginPage.open();
		loginPage.loginNormalAs(user, password);		
	
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageLocationsPage.open();			
		manageLocationsPage.addNewLocation(locationName,  customerName, cityName );
		
		assertTrue(manageLocationsPage.findExistingLocation(customerName, locationName));
	}
	
	/**
	 * Test Case ID: TC61_TC490_EditLocation_PicAdmin
	 * Test Description: Editing Location
	 * 
	 */
	@Test
	@UseDataProvider(value = UserDataProvider.USER_ADMIN_SUPPORT_PROVIDER, location = UserDataProvider.class)
	public void TC61_TC490_EditLocation_PicAdmin(String user, String pwd) {
		String tcID;
		if (user.equalsIgnoreCase("AutomationAdmin")) {
			tcID = "TC61";
		} else {
			tcID = "TC490";
		}
		String password = CryptoUtility.decrypt(pwd);
		String customerName = CUSTOMERNAMEPREFIX
				+ testSetup.getFixedSizeRandomNumber(8) + tcID;
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc"
				+ testSetup.getFixedSizeRandomNumber(8) + tcID;
		String newLocationName = locationName + "NEW"
				+ testSetup.getFixedSizeRandomNumber(8) + tcID;
		String cityName = "Santa Clara";

		Log.info("\nRunning " + tcID
				+ "_EditLocation - Test Description: Editing Location");
		
		loginPage.open();
		loginPage.loginNormalAs(user, password);

		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName,  customerName, cityName);
		
		manageLocationsPage.editPDExistingLocation(customerName, locationName, newLocationName);
		assertTrue(manageLocationsPage.findExistingLocation(customerName, newLocationName));
	}
	
	/**
	 * Test Case ID: TC62_EditLocationCustomerCannotBeChanged_PicAdmin
	 * Script:   	 	
	 * - On Home Page, click Picarro Administration -> Manage Locations
	 * - Click Edit link
	 * - Modify location details
	 * Results: - 
	 * - Admin cannot change Customer associated with the Location
	 */
	@Test
	public void TC62_EditLocationCustomerCannotBeChanged_PicAdmin() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "TC62";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String newLocationName = locationName + "NEW";
		String cityName="Santa Clara";
		
		Log.info("\nRunning TC61_EditLocation_PicAdmin - Test Description: Editing Location");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
	
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName,  customerName, cityName);
		
		manageLocationsPage.editPDExistingLocation(customerName, locationName, newLocationName);
		assertTrue(manageLocationsPage.findExistingLocation(customerName, newLocationName));
	}
	
	/**
	 * Test Case ID: TC100_TC495_More than 50 characters not allowed in Location
	 * Description field 
	 * Test Script: - On Home Page, and click Administration
	 * -> Manage Locations - Click on 'Add New Location' button - Provide more
	 * than 50 characters in Location Description field. Click OK 
	 * Expected Result: User cannot enter more than 50 characters and message 
	 * having limit of characters displayed
	 */
	@Test
	@UseDataProvider(value = UserDataProvider.USER_ADMIN_SUPPORT_PROVIDER, location = UserDataProvider.class)
	public void TC100_TC495_EditLoc50CharLimit(String user, String pwd) {
		String str34chars = "AbcdefghI-AbcdefghI-AbcdefghI-Abcd";
		String str35chars = "AbcdefghI-AbcdefghI-AbcdefghI-Abcde";
		String cityName = "Santa Clara";

		String tcID;
		if (user.equalsIgnoreCase("AutomationAdmin")) {
			tcID = "TC100";
		} else {
			tcID = "TC495";
		}
		String password = CryptoUtility.decrypt(pwd);

		String locationName50Chars = testSetup.getFixedSizeRandomNumber(11)
				+ tcID + str34chars;
		String locationName51Chars = testSetup.getFixedSizeRandomNumber(11)
				+ tcID + str35chars;
		String newLocationName50Chars = "New"
				+ testSetup.getFixedSizeRandomNumber(8) + tcID + str34chars;
		String newLocationName51Chars = "New"
				+ testSetup.getFixedSizeRandomNumber(8) + tcID + str35chars;

		Log.info("\nRunning - "
				+ tcID
				+ "_EditLoc50CharLimit - Test Description: More than 50 characters not allowed in Location Description field\n");

		loginPage.open();
		loginPage.loginNormalAs(user, password);

		manageLocationsPage.open();

		manageLocationsPage.addNewLocation(locationName50Chars, SQACUS,
				cityName);
		manageLocationsPage.addNewLocation(locationName51Chars, SQACUS,
				cityName);

		assertTrue(manageLocationsPage.findExistingLocation(SQACUS,
				locationName50Chars));
		manageLocationsPage.open();
		assertFalse(manageLocationsPage.findExistingLocation(SQACUS,
				locationName51Chars));

		System.out.println("Loc 50 char:-" + locationName50Chars);
		System.out.println("New Loc 50 char:-" + newLocationName50Chars);
		
		manageLocationsPage.editPDExistingLocation(SQACUS, locationName50Chars,
				newLocationName50Chars);
		assertTrue(manageLocationsPage.findExistingLocation(SQACUS,
				newLocationName50Chars));

		manageLocationsPage.editPDExistingLocation(SQACUS, newLocationName50Chars,
				newLocationName51Chars);
		//manageLocationsPage.open();
		assertFalse(manageLocationsPage.findExistingLocation(SQACUS,
				newLocationName51Chars));
	}
	
	/**
	 * Test Case ID: TC496 Test Description: Search valid location record
	 */
	@Test
	public void TC496_SearchValidLocation() {
		Log.info("\nRunning - TC451 - Test Description: Search valid location record\n");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);

		manageLocationsPage.open();
		assertTrue(manageLocationsPage.searchLocation(SQACUS, SQACUSLOC));
	}
}