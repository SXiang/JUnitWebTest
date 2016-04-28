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
import jdk.nashorn.internal.ir.annotations.Ignore;
import surveyor.dataprovider.RunAs;
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
	private enum ManageUserTestCaseType {
		AddDuplicateLocation,
		EditDuplicateLocation
	}
	private static final String SQAPICAD_AND_SQAPICSUP = "sqapicad@picarro.com,sqapicsup@picarro.com";
	
	private enum ManageLocationTestCaseType {
		AddLocUsingSelector,
		EditLocUsingSelector,
		AddLoc,
		EditLoc,
		MaxLocChar
	}
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
	@UseDataProvider(value = "dataProviderPicarroAdminSupportRoleInfo", location = UserDataProvider.class)
	@RunAs(users=SQAPICAD_AND_SQAPICSUP)
	public void TC16_TC18_AddLocationUsingLatLongSelector_PicAdminSupport(String user, String pswd ) {
		String tcID = getTestCaseName(ManageLocationTestCaseType.AddLocUsingSelector, user);
		String password = CryptoUtility.decrypt(pswd);
		String locationName = testSetup.getFixedSizeRandomNumber(8) + tcID;
		String cityName = "Santa Clara";
		Log.info("user: "+user);
		Log.info("pswd: "+pswd);
		Log.info("------------------------");
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
	@UseDataProvider(value = "dataProviderPicarroAdminSupportRoleInfo", location = UserDataProvider.class)
	@RunAs(users=SQAPICAD_AND_SQAPICSUP)
	public void TC17_TC19_EditLocationAddedUsingLatLongSelector_PicAdmin_PicSupport(String user, String pwd) {
		String tcID = getTestCaseName(ManageLocationTestCaseType.EditLocUsingSelector, user);
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
	@UseDataProvider(value = "dataProviderPicarroAdminSupportRoleInfo", location = UserDataProvider.class)
	@RunAs(users=SQAPICAD_AND_SQAPICSUP)
	public void TC60_TC489_AddLocation_PicAdmin_PicSupport(String user, String pwd) {
		String tcID = getTestCaseName(ManageLocationTestCaseType.AddLoc, user);
		String password = CryptoUtility.decrypt(pwd);
		String customerName = SQACUS;
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String cityName="Santa Clara";	
		
		Log.info("\nRunning TC60_TC489_AddLocation_PicAdmin_PicSupport - Test Description: Adding Location");
		
		loginPage.open();
		loginPage.loginNormalAs(user, password);		
	
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
	@UseDataProvider(value = "dataProviderPicarroAdminSupportRoleInfo", location = UserDataProvider.class)
	@RunAs(users=SQAPICAD_AND_SQAPICSUP)
	public void TC61_TC490_EditLocation_PicAdmin(String user, String pwd) {
		String tcID = getTestCaseName(ManageLocationTestCaseType.EditLoc, user);
		String password = CryptoUtility.decrypt(pwd);
		String customerName = SQACUS;
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc"
				+ testSetup.getFixedSizeRandomNumber(8) + tcID;
		String newLocationName = locationName + "NEW"
				+ testSetup.getFixedSizeRandomNumber(8) + tcID;
		String cityName = "Santa Clara";

		Log.info(user);
		Log.info(password);
		Log.info("\nRunning " + tcID
				+ "_EditLocation - Test Description: Editing Location");
		
		loginPage.open();
		loginPage.loginNormalAs(user, password);

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
	@UseDataProvider(value = "dataProviderPicarroAdminSupportRoleInfo", location = UserDataProvider.class)
	@RunAs(users=SQAPICAD_AND_SQAPICSUP)
	public void TC100_TC495_EditLoc50CharLimit(String user, String pwd) {
		String str34chars = "AbcdefghI-AbcdefghI-AbcdefghI-Abcd";
		String str35chars = "AbcdefghI-AbcdefghI-AbcdefghI-Abcde";
		String cityName = "Santa Clara";

		String tcID = getTestCaseName(ManageLocationTestCaseType.MaxLocChar, user);
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

		Log.info(String.format("Adding new location - [Name=%s], [Customer=%s], [City=%s]", locationName50Chars, SQACUS,
				cityName));
		manageLocationsPage.addNewLocation(locationName50Chars, SQACUS,
				cityName);
		Log.info(String.format("Adding new location - [Name=%s], [Customer=%s], [City=%s]", locationName51Chars, SQACUS,
				cityName));
		manageLocationsPage.addNewLocation(locationName51Chars, SQACUS,
				cityName);

		assertTrue(manageLocationsPage.findExistingLocation(SQACUS,
				locationName50Chars));
		manageLocationsPage.open();
		assertFalse(manageLocationsPage.findExistingLocation(SQACUS,
				locationName51Chars));

		manageLocationsPage.editPDExistingLocation(SQACUS, locationName50Chars,
				newLocationName50Chars);
		assertTrue(manageLocationsPage.findExistingLocation(SQACUS,
				newLocationName50Chars));

		manageLocationsPage.editPDExistingLocation(SQACUS, newLocationName50Chars,
				newLocationName51Chars);
		assertFalse(manageLocationsPage.findExistingLocation(SQACUS,
				newLocationName51Chars));
	}
	
	/**
	 * Test Case ID: TC496 Test Description: Search valid location record
	 * Test Script: - Provide valid location in search box present on Manage Location screen
	 * Expected Result: Searched location details are displayed
	 */
	@Test
	public void TC496_SearchValidLocation() {
		Log.info("\nRunning - TC451 - Test Description: Search valid location record\n");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);

		manageLocationsPage.open();
		assertTrue(manageLocationsPage.searchLocation(SQACUS, SQACUSLOC));
	}
	
	/**
	 * Returns the testCase ID based on the username provided by DataProvider.
	 */
	private String getTestCaseName(ManageLocationTestCaseType testCaseType, String username) {
		String testCase = "";		
		switch (testCaseType) {
		case AddLocUsingSelector:
			if (username.equalsIgnoreCase(SQAPICAD)) {
				testCase = "TC16";
			} else if (username.equalsIgnoreCase(SQAPICSUP)) {
				testCase = "TC18";
			}
			break;
		case EditLocUsingSelector:
			if (username.equalsIgnoreCase(SQAPICAD)) {
				testCase = "T17";
			} else if (username.equalsIgnoreCase(SQAPICSUP)) {
				testCase = "TC19";
			}
			break;
		case AddLoc:
			if (username.equalsIgnoreCase(SQAPICAD)) {
				testCase = "TC60";
			} else if (username.equalsIgnoreCase(SQAPICSUP)) {
				testCase = "TC489";
			}
			break;
		case EditLoc:
			if (username.equalsIgnoreCase(SQAPICAD)) {
				testCase = "TC61";
			} else if (username.equalsIgnoreCase(SQAPICSUP)) {
				testCase = "TC490";
			}
			break;
		case MaxLocChar:
			if (username.equalsIgnoreCase(SQAPICAD)) {
				testCase = "TC100";
			} else if (username.equalsIgnoreCase(SQAPICSUP)) {
				testCase = "TC495";
			}
			break;
		}
		return testCase;
	}
	
	
	/**
	 * Test Case ID: TC491 - TC118
	 * Test Description: - TC491 Picarro support user not allowed to create duplicate location
	 *                     TC118 Picarro admin user not allowed to create duplicate location
	 * Script:
	 *  - On Home Page, click Administration -> Manage Locations
	 *  - Click on 'Add New Location' button
	 *  - Provide location details same as existing location and click OK
	 * Results:
	 *  - Duplicate Location creation not allowed
	 *  
	 *  Defect TC1841 on Rally - {@link https://rally1.rallydev.com/#/53512905526d/detail/defect/53793371641}
	 *  Defect Description: L10N: Error message 'Location name already exists...' is not in Resources table
	 */
	@Test
	@UseDataProvider(value = "dataProviderPicarroUserRoleInfo", location = UserDataProvider.class)
	public void TC491_TC118_DuplicateLocationNotAllowed_PicSupportAdmin(String username, String password, String role, 
			String customerName, String customerLocation) {

		String testCaseID = getTestCaseName(ManageUserTestCaseType.AddDuplicateLocation, username);		
		password = CryptoUtility.decrypt(password);

		String locationName = testSetup.getFixedSizeRandomNumber(8) + testCaseID;
		String cityName = "Santa Clara";
		String errorMsg = "Location name already exists for customer, please try another name.";

		Log.info("\nRunning - "+testCaseID+"_DuplicateLocationNotAllowed_["+role+"] - "+
				"Test Description: Picarro user not allowed to create duplicate location\n");

		// *** Adding a location for this test 
		loginPage.open();
		loginPage.loginNormalAs(username, password);
		manageLocationsPage.open();
		Log.info("Adding location: " + locationName);
		manageLocationsPage.addNewLocation(locationName, SQACUS, cityName, false, "1","2",false);    
		manageLocationsPage.logout();

		// *** Starting test

		loginPage.loginNormalAs(username, password);
		manageLocationsPage.open();
		Log.info("Adding location: " + locationName);
		manageLocationsPage.performSearch(locationName);
		manageLocationsPage.addNewLocation(locationName, SQACUS, cityName, false, "1","2",false);     

		assertTrue(manageLocationsPage.verifyErrorMessage(errorMsg));

		manageLocationsPage.clickOnCancelBtn();

	}	

	/**
	 * Test Case ID: TC492
	 * Test Description: TC492 - Picarro support user not allowed to edit location details same as existing location details
	 *                   TC119 - Picarro admin user not allowed to edit location details same as existing location details
	 * Script:
	 *  - On Home Page, click Administration -> Manage Locations
	 *  - Click on 'Edit' button
	 *  - Provide location details same as existing location and click OK
	 * Results:
	 *  - Duplicate Location update not allowed
	 */
	@Test
	@UseDataProvider(value = "dataProviderPicarroUserRoleInfo", location = UserDataProvider.class)
	public void TC492_TC119_DuplicateLocationEditNotAllowed_PicSupport(String username, String password, String role, 
			String customerName, String customerLocation) {

		String testCaseID = getTestCaseName(ManageUserTestCaseType.EditDuplicateLocation, username);		
		password = CryptoUtility.decrypt(password);

		String locationName = testSetup.getFixedSizeRandomNumber(8) + testCaseID;
		String cityName = "Santa Clara";
		String errorMsg = "Location name already exists for customer, please try another name.";

		Log.info("\nRunning - "+testCaseID+"_DuplicateLocationNotAllowed_["+role+"] - "+
				"Test Description: Picarro user not allowed to edit duplicate location details same as existing location details\n");

		// *** Adding a location for this test 
		loginPage.open();
		loginPage.loginNormalAs(username, password);
		manageLocationsPage.open();
		Log.info("Adding location: " + locationName);
		manageLocationsPage.addNewLocation(locationName, SQACUS, cityName, false, "1","2",false);    
		manageLocationsPage.logout();

		// *** Starting test

		String newLocationName = SQAPICLOC;

		loginPage.loginNormalAs(username, password);
		manageLocationsPage.open();
		Log.info("Editing location: " + locationName + " -> " + newLocationName);
		manageLocationsPage.performSearch(locationName);
		manageLocationsPage.editExistingLocation(SQACUS, locationName, newLocationName,false);

		assertTrue(manageLocationsPage.verifyErrorMessage(errorMsg));

		manageLocationsPage.clickOnCancelBtn();

	}	
    
    /**
	 * Test Case ID: TC493
     * Test Description: Picarro Support - Add location- blank required fields
	 * Script:
	 *  - On Home Page, click Administration -> Manage Locations
     *  - Click on 'Add New Location' button
     *  - Keep description field blank. Click OK
     * Results:
     *  - "The field is required." message should be displayed
	 */
    @Test
	public void TC493_AddLocationBlankFields_PicSupport(){
		
		String locationName = testSetup.getRandomNumber() + "TC493";
		String cityName = "Santa Clara";
        		
		Log.info("\nRunning - TC493_AddLocationBlankFields_PicSupport - "+
				"Test Description: Add location- blank required fields\n");
	
		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		manageLocationsPage.open();
		Log.info("Adding location empty description: required field" + locationName);
		manageLocationsPage.addNewLocation("", SQACUS, cityName, false, "1","2",false);  
		
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
        		
		Log.info("\nRunning - TC494_EditLocationBlankFields_PicSupport - "+
				"Test Description:  - Edit location- blank required fields\n");
	
		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, SQACUS, cityName, false, "1","2",false);  
		
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
				"Test Description: Search invalid location record\n");

		loginPage.open();
		loginPage.loginNormalAs( SQAPICSUP,USERPASSWORD);
		manageLocationsPage.open();

		manageLocationsPage.performSearch(invalidKey);
		assertEquals(manageLocationsPage.getLabelNoMatchingSearch(),errorMsg);
	}		
	
	/**
	 * Returns the testCase ID based on the username provided by DataProvider.
	 */
	private String getTestCaseName(ManageUserTestCaseType testCaseType, String username) {
		String testCase = "";		
		switch (testCaseType) {
		case AddDuplicateLocation:
			if (username.equalsIgnoreCase(SQAPICAD)) {
				testCase = "TC118";
			} else if (username.equalsIgnoreCase(SQAPICSUP)) {
				testCase = "TC491";
			}
			break;
		case EditDuplicateLocation:
			if (username.equalsIgnoreCase(SQAPICAD)) {
				testCase = "TC119";
			} else if (username.equalsIgnoreCase(SQAPICSUP)) {
				testCase = "TC492";
			}
			break;
		}
		return testCase;
	}

  
}