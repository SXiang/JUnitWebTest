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
		
		System.out.println("\nRunning ADM004 - Test Description: Adding Location");
		
		System.out.format("\nThe customer name is: %s\n", customerName);
		System.out.format("\nThe location is: %s\n", locationName);
	
		manageCustomersPage.open();
		
		if (debug)
			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		if (debug)
			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		manageLocationsPage.open();
		
		if (debug)
			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());			
		
		manageLocationsPage.addNewLocation(locationName,  customerName);
		
		if (debug)
			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		assertTrue(manageLocationsPage.findExistingLocation(customerName, locationName));
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
		
		System.out.println("\nRunning ADM005 - Test Description: Editing Location");
		
		System.out.format("\nThe customer name is: %s\n", customerName);
		System.out.format("\nThe location is: %s\n", locationName);

		manageCustomersPage.open();
		
		if (debug)
			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		if (debug)
			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		manageLocationsPage.open();
		
		if (debug)
			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		manageLocationsPage.addNewLocation(locationName,  customerName);
		
		if (debug)
			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		manageLocationsPage.editExistingLocation(customerName, locationName, newLocationName);
		
		if (debug)
			testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		assertTrue(manageLocationsPage.findExistingLocation(customerName, newLocationName));
	}	
}