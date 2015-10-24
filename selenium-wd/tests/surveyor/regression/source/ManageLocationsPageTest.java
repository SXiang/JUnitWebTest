/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
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
	private static ManageCustomersPage manageCustomersPage;
	
	@BeforeClass
	public static void setupManageLocationsPageTest() {
		manageLocationsPage = new ManageLocationsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageLocationsPage);
		
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
	}
	
	
	/**
	 * Test Case ID: TC60
	 * Test Description: Adding Location
	 * 
	 */
	@Test
	public void TC60() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "TC60";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String cityName="Santa Clara";
	
		
		System.out.println("\nRunning TC60 - Test Description: Adding Location");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
	
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageLocationsPage.open();			
		manageLocationsPage.addNewLocation(locationName,  customerName, cityName );
		
		assertTrue(manageLocationsPage.findExistingLocation(customerName, locationName));
	}
	
	/**
	 * Test Case ID: TC61
	 * Test Description: Editing Location
	 * 
	 */
	@Test
	public void TC61() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "TC61";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String newLocationName = locationName + "NEW";
		String cityName="Santa Clara";
		
		System.out.println("\nRunning TC61 - Test Description: Editing Location");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName,  customerName, cityName);
		
		manageLocationsPage.editExistingLocation(customerName, locationName, newLocationName);
		
		assertTrue(manageLocationsPage.findExistingLocation(customerName, newLocationName));
	}	
}