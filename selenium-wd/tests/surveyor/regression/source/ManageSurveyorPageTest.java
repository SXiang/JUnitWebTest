/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.SurveyorBaseTest;

/**
 * @author zlu
 *
 */
public class ManageSurveyorPageTest extends SurveyorBaseTest {
	private static ManageCustomersPage manageCustomersPage;
	private static ManageLocationsPage manageLocationsPage;
	private static ManageSurveyorPage manageSurveyorPage;
	
	public ManageSurveyorPageTest() {
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
		
		manageLocationsPage = new ManageLocationsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageLocationsPage);
		
		manageSurveyorPage = new ManageSurveyorPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageSurveyorPage);
	}
	
	/**
	 * Test Case ID: ADM007
	 * Test Description: Adding Surveyor
	 * 
	 */
	@Test
	public void ADM007() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "ADM007";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String surveyorName = locationName + "Sur";
		
		System.out.println("\nRunning ADM007...");
		
		if (debug) {
			System.out.format("The customer name is \"%s\", the location name is \"%s\" and the surveyor name is \"%s\"\n", customerName, locationName, surveyorName);
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
			
			manageLocationsPage.addNewLocation(locationName, customerName);
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			manageSurveyorPage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			
			manageSurveyorPage.addNewSurveyor(surveyorName, locationName, customerName);
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			assertTrue(manageSurveyorPage.findExistingSurveyor(customerName, locationName, surveyorName));
		}
		catch (Exception e) {
			System.out.format("Exception on test case \"ADM007\": %s\n", e.getMessage());
		}
	}
}