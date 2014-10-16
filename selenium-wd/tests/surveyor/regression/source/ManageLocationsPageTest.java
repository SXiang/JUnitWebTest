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
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class ManageLocationsPageTest extends SurveyorBaseTest {
	private static ManageLocationsPage manageLocationsPage;
	
	public ManageLocationsPageTest() {
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
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
	
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageLocationsPage.open();			
		manageLocationsPage.addNewLocation(locationName,  customerName);
		
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
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName,  customerName);
		
		manageLocationsPage.editExistingLocation(customerName, locationName, newLocationName);
		
		assertTrue(manageLocationsPage.findExistingLocation(customerName, newLocationName));
	}	
}