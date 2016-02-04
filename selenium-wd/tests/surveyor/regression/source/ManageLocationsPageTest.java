/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
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
	public void TC16_AddLocationUsingLatLongSelector_PicAdmin() {
		String locationName = testSetup.getRandomNumber() + "TC16";
		String cityName = "Santa Clara";

		Log.info("\nRunning - TC16_AddLocationUsingLatLongSelector_PicAdmin - Test Description: Add new location\n");
		// Add Location as Picarro admin.
		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageLocationsPage.open();
		manageLocationsPage.addNewLocationUsingLatLongSelector(locationName, SQACUS, cityName);
		
		// TODO$: Check the locator icon shows up correctly in the selector dialog.
		
		assertTrue(manageLocationsPage.findExistingLocation(SQACUS, locationName));
	}
		
	/**
	 * Test Case ID: TC17_EditLocationAddedUsingLatLongSelector_PicAdmin
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
	public void TC17_EditLocationAddedUsingLatLongSelector_PicAdmin() {
		String locationName = testSetup.getRandomNumber() + "TC17";
		String locationNameNew = testSetup.getRandomNumber() + "TC17" + "_New";
		String cityName = "Santa Clara";

		Log.info("\nRunning - TC17_EditLocationAddedUsingLatLongSelector_PicAdmin - Test Description: Add new location\n");

		// Add Location as Picarro admin.	
		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		manageLocationsPage.open();
		manageLocationsPage.addNewLocationUsingLatLongSelector(locationName, SQACUS, cityName);
		
		// TODO$: Check the locator icon shows up correctly in the selector dialog.
		
		manageLocationsPage.editExistingLocation(SQACUS, locationName, locationNameNew);
		assertTrue(manageLocationsPage.findExistingLocation(SQACUS, locationNameNew));
	}
	
	/**
	 * Test Case ID: TC60_AddLocation_PicAdmin
	 * Test Description: Adding Location
	 * 
	 */
	@Test
	public void TC60_AddLocation_PicAdmin() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "TC60";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String cityName="Santa Clara";
	
		
		Log.info("\nRunning TC60_AddLocation_PicAdmin - Test Description: Adding Location");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
	
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageLocationsPage.open();			
		manageLocationsPage.addNewLocation(locationName,  customerName, cityName );
		
		assertTrue(manageLocationsPage.findExistingLocation(customerName, locationName));
	}
	
	/**
	 * Test Case ID: TC61_EditLocation_PicAdmin
	 * Test Description: Editing Location
	 * 
	 */
	@Test
	public void TC61_EditLocation_PicAdmin() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "TC61";
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
		
		manageLocationsPage.editExistingLocation(customerName, locationName, newLocationName);
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
		
		manageLocationsPage.editExistingLocation(customerName, locationName, newLocationName);
		assertTrue(manageLocationsPage.findExistingLocation(customerName, newLocationName));
	}
	
	/**
	 * Test Case ID: TC489_AddLocation_PicarroSupport 
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
	public void TC489_AddLocation_PicarroSupport() {
		String locationName = testSetup.getRandomNumber() + "TC489";
		String cityName = "Santa Clara";

		Log.info("\nRunning - TC489_AddLocation_PicarroSupport - Test Description: Add location\n");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);

		manageLocationsPage.open();
		Log.info("Adding location: " + locationName);
		manageLocationsPage.addNewLocation(locationName, SQACUS, cityName);

		assertTrue(manageLocationsPage.findExistingLocation(SQACUS, locationName));
	}
}