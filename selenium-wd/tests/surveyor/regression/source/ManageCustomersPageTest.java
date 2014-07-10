/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.SurveyorBaseTest;

/**
 * @author zlu
 *
 */
public class ManageCustomersPageTest extends SurveyorBaseTest {
	private static ManageCustomersPage manageCustomersPage;
	
	public ManageCustomersPageTest() {
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);		
	}

	/**
	 * Test Case ID: ADM001
	 * Test Description: Adding Customer
	 * 
	 */
	@Test
	public void ADM001() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "ADM001";
		String eula = customerName + ": " + EULASTRING;
		
		System.out.println("\nRunning ADM001...");
		
		if (debug) {
			System.out.format("The customer name is: %s\n", customerName);
		}
	
		try {
			manageCustomersPage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			manageCustomersPage.addNewCustomer(customerName, eula);
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			assertTrue(manageCustomersPage.findExistingCustomer(customerName));
		}
		catch (Exception e) {
			System.out.format("Exception on test case \"ADM001\": %s\n", e.getMessage());
		}
	}
	
	/**
	 * Test Case ID: ADM002
	 * Test Description: Editing Customer
	 * 
	 */
	@Test
	public void ADM002() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "ADM002";
		String eula = customerName + ": " + EULASTRING;
		String newCustomerName = customerName + "NEW";		
		
		System.out.println("\nRunning ADM002...");
		
		try {
			manageCustomersPage.open();
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			manageCustomersPage.addNewCustomer(customerName, eula);
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			manageCustomersPage.editExistingCustomerName(customerName, newCustomerName);
			
			if (debug)
				testSetup.slowdownInSeconds(3);
			
			assertTrue(manageCustomersPage.findExistingCustomer(newCustomerName));
		}
		catch (Exception e) {
			System.out.format("Exception on test case \"ADM002\": %s\n", e.getMessage());
		}
	}
}