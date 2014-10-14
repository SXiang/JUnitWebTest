/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.SurveyorBaseTest;
import static surveyor.scommon.source.SurveyorConstants.*;

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
		
		System.out.println("\nRunning ADM001 - Test Description: Adding Customer");

		manageCustomersPage.open();
		
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		assertTrue(manageCustomersPage.findExistingCustomer(customerName));
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
		
		System.out.println("\nRunning ADM002 - Test Description: Editing Customer");
		
		manageCustomersPage.open();
		
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageCustomersPage.editExistingCustomerName(customerName, newCustomerName);
		
		assertTrue(manageCustomersPage.findExistingCustomer(newCustomerName));
	}
	
	/**
	 * Test Case ID: MCP000A
	 * Test Description: Editing Customer with eula change only
	 * 
	 */
	@Test
	public void MCP000A() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "MCP000A";
		String eula = customerName + ": " + EULASTRING;
		String newCustomerName = customerName + "NEW";
		String eulaNew = customerName + ": " + EULASTRING + "NEW";
		
		System.out.println("\nRunning MCP000A - Test Description: Editing Customer with eula change only");
		
		manageCustomersPage.open();
		
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageCustomersPage.editExistingCustomerName(customerName, newCustomerName, eulaNew);
		
		assertTrue(manageCustomersPage.findExistingCustomer(newCustomerName));
	}
	
	/**
	 * Test Case ID: MCP000B
	 * Test Description: Checking Customer Account Status
	 * 
	 */
	@Test
	public void MCP000B() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "MCP000B";
		String eula = customerName + ": " + EULASTRING;
		
		System.out.println("\nRunning MCP000B - Test Description: Checking Customer Account Status");
		
		manageCustomersPage.open();
		
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		assertTrue(manageCustomersPage.getCustomerStatus(customerName).equalsIgnoreCase(CUSTOMERENABLED));
	}
	
	/**
	 * Test Case ID: MCP000C
	 * Test Description: Changing Customer Account Status
	 * 
	 */
	@Test
	public void MCP000C() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "MCP000C";
		String eula = customerName + ": " + EULASTRING;
		
		System.out.println("\nRunning MCP000C - Test Description: Changing Customer Account Status");
		
		manageCustomersPage.open();
		
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		if (manageCustomersPage.changeCustomerAccountStatus(customerName, false))
			assertTrue(manageCustomersPage.getCustomerStatus(customerName).equalsIgnoreCase(CUSTOMERDISABLED));
	}	
}