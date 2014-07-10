/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.SurveyorBaseTest;

/**
 * @author zlu
 *
 */
public class ManageLocationsPageTest extends SurveyorBaseTest {
	private static ManageCustomersPage manageCustomersPage;
	private static ManageLocationsPage manageLocationsPage;
	
	public ManageLocationsPageTest() {
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
		
		manageLocationsPage = new ManageLocationsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageLocationsPage);
	}

	/**
	 * Test Case ID: ADM004
	 * Test Description: Adding Location
	 * 
	 */
	@Test
	public void ADM004() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "ADM004";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		
		System.out.println("\nRunning ADM004...");
		
		if (debug) {
			System.out.format("The customer name is: %s\n", customerName);
			System.out.format("The customer status is: %s\n", locationName);
		}
	
		try {
			manageCustomersPage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			manageCustomersPage.addNewCustomer(customerName, eula);
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			manageLocationsPage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);			
			
			manageLocationsPage.addNewLocation(locationName,  customerName);
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			assertTrue(manageLocationsPage.findExistingLocation(customerName, locationName));
			
		}
		catch (Exception e) {
			System.out.format("Exception on test case \"ADM004\": %s\n", e.getMessage());
		}
	}
	
	/**
	 * Test Case ID: ADM005
	 * Test Description: Editing Location
	 * 
	 */
	@Test
	public void ADM005() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "ADM005";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String newLocationName = locationName + "NEW";
		
		System.out.println("\nRunning ADM005...");
		
		if (debug) {
			System.out.format("The customer name is: %s\n", customerName);
			System.out.format("The customer status is: %s\n", locationName);
		}
	
		try {
			manageCustomersPage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			manageCustomersPage.addNewCustomer(customerName, eula);
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			manageLocationsPage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);			
			
			manageLocationsPage.addNewLocation(locationName,  customerName);
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			manageLocationsPage.editExistingLocation(customerName, locationName, newLocationName);
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			assertTrue(manageLocationsPage.findExistingLocation(customerName, newLocationName));
		}
		catch (Exception e) {
			System.out.format("Exception on test case \"ADM005\": %s\n", e.getMessage());
		}
	}	
}