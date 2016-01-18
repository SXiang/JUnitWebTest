/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.SurveyorBaseTest;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
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