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
		if(user.equalsIgnoreCase("administrator")){
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
		
		manageLocationsPage.editPDExistingLocation(SQACUS, locationName, locationNameNew);
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
	
	
	/**
	 * Test Case ID: TC491
     * Test Description: Picarro support user not allowed to create duplicate location
	 * Script:
	 *  - On Home Page, click Administration -> Manage Locations
     *  - Click on 'Add New Location' button
     *  - Provide location details same as existing location and click OK
     * Results:
     *  - Duplicate Location creation not allowed
	 */
    @Test
	public void TC491_DuplicateLocationNotAllowed_PicSupport(){
		
		String locationName = testSetup.getRandomNumber() + "TC491";
		String cityName = "Santa Clara";
        String errorMsg = "Location name already exists for customer, please try another name.";
        		
		// *** Adding a location for this test 
		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		manageLocationsPage.open();
		Log.info("Adding location: " + locationName);
		manageLocationsPage.addNewLocation(locationName, SQACUS, cityName, false, null,null,false);    
		manageLocationsPage.logout();

		// *** Starting test
		Log.info("\nRunning - TC491_DuplicateLocationNotAllowed_PicSupport - "+
				"Test Description: Picarro support user not allowed to create duplicate location\n");
		
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		manageLocationsPage.open();
		Log.info("Adding location: " + locationName);
		manageLocationsPage.performSearch(locationName);
		manageLocationsPage.addNewLocation(locationName, SQACUS, cityName, false, null,null,false);     
		
		assertTrue(manageLocationsPage.correctPossibleError(errorMsg));

		manageLocationsPage.clickOnCancelBtn();

	}	

	/**
	 * Test Case ID: TC492
     * Test Description: Picarro support user not allowed to edit location details same as existing location details
	 * Script:
	 *  - On Home Page, click Administration -> Manage Locations
     *  - Click on 'Edit' button
     *  - Provide location details same as existing location and click OK
     * Results:
     *  - Duplicate Location update not allowed
	 */
    @Test
	public void TC492_DuplicateLocationEditNotAllowed_PicSupport(){
		
		String locationName = testSetup.getRandomNumber() + "TC492";
		String cityName = "Santa Clara";
        String errorMsg = "Location name already exists for customer, please try another name.";
        		
		// *** Adding a location for this test 
		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		manageLocationsPage.open();
		Log.info("Adding location: " + locationName);
		manageLocationsPage.addNewLocation(locationName, SQACUS, cityName, false, null,null,false);    
		manageLocationsPage.logout();
		
		// *** Starting test
		Log.info("\nRunning - TC492_DuplicateLocationEditNotAllowed_PicSupport - "+
				"Test Description: Picarro support user not allowed to edit location details same as existing location details\n");
		
		String newLocationName = SQAPICLOC;

		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		manageLocationsPage.open();
		Log.info("Editing location: " + locationName + " -> " + newLocationName);
		manageLocationsPage.performSearch(locationName);
		manageLocationsPage.editExistingLocation(SQACUS, locationName, newLocationName,false);
		
		assertTrue(manageLocationsPage.correctPossibleError(errorMsg));

		manageLocationsPage.clickOnCancelBtn();

	}	
    
    /**
	 * Test Case ID: TC493
     * Test Description: Picarro Support - Add location- blank required fields
	 * Script:
	 *  - On Home Page, click Administration -> Manage Locations
     *  - Click on 'Add New Locaiton' button
     *  - Keep description field blank. Click OK
     * Results:
     *  - "The field is required." message should be displayed
	 */
    @Test
	public void TC493_AddLocationBlankFields_PicSupport(){
		
		String locationName = testSetup.getRandomNumber() + "TC493";
		String cityName = "Santa Clara";
        		
		Log.info("\nRunning - TC493_AddLocationBlankFields_PicSupport - "+
				"Test Description: Picarro support user not allowed to edit location details same as existing location details\n");
	
		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		manageLocationsPage.open();
		Log.info("Adding location empty description: required field" + locationName);
		manageLocationsPage.addNewLocation("", SQACUS, cityName, false, null,null,false);  
		
		assertEquals(BLANKFIELDERROR, manageLocationsPage.getLocationDescriptionError());
		
		manageLocationsPage.clickOnCancelBtn();

	}	    
    
    /**
	 * Test Case ID: TC494
     * Test Description: Picarro Support - Add location- blank required fields
	 * Script:
	 *  - On Home Page, click Administration -> Manage Locations
     *  - Click on 'Edit' button
     *  - Delete description field data. Click OK
     * Results:
     *  - "The field is required." message should be displayed
	 */
    @Test
	public void TC494_EditLocationBlankFields_PicSupport(){
		
		String locationName = testSetup.getRandomNumber() + "TC494";
		String cityName = "Santa Clara";
        		
		Log.info("\nRunning - TC493_AddLocationBlankFields_PicSupport - "+
				"Test Description: Picarro support user not allowed to edit location details same as existing location details\n");
	
		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, SQACUS, cityName, false, null,null,false);  
		
		Log.info("Editing location(empty string): " + locationName + " -> ");
		manageLocationsPage.performSearch(locationName);
		manageLocationsPage.editPDExistingLocation(SQACUS, locationName, "");
		
		assertEquals(BLANKFIELDERROR, manageLocationsPage.getLocationDescriptionError());
		
		manageLocationsPage.clickOnCancelBtn();

	}	        
    
	/**
	 * Test Case ID: TC497
     * Test Description: Search invalid location record
	 * Script:
	 *  -   Provide invalid location in search box present on Manage Location screen
     * Result:
     *  - "Message should be displayed : 'No matching records found'
     */
	@Test
	public void TC497_NoMatchingLocationFound_PicSupport(){
		String errorMsg = NOMATCHINGRECORDS;		
		String invalidKey = "whichLocationIsNotValidHowever";

		Log.info("\nRunning - TC497_NoMatchingLocationFound_PicSupport - "+
				"Test Description: Add user - blank required fields\n");

		loginPage.open();
		loginPage.loginNormalAs( SQAPICSUP,USERPASSWORD);
		manageLocationsPage.open();

		manageLocationsPage.performSearch(invalidKey);
		assertEquals(manageLocationsPage.getLabelNoMatchingSearch(),errorMsg);
	}		   
  
}