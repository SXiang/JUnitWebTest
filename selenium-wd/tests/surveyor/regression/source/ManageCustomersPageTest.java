/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.SurveyorBaseTest;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class ManageCustomersPageTest extends SurveyorBaseTest {
	private static ManageCustomersPage manageCustomersPage;
	
	@BeforeClass
	public static void setupManageCustomersPageTest() {
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
	}

	/**
	 * Test Case ID: TC58_AddNewCustomer_PicAdmin
	 * Script:   	
	 * - On Home Page, click Picarro Administration -> Manage Customers
	 * - Click on 'Add New Customer' button
	 * - Provide required customer details and click OK	 
	 * Results: - 
	 * - User is navigated to Manage Customers page and new customer entry is present in the table
	 */
	@Test
	public void TC58_AddNewCustomer_PicAdmin() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "TC58";
		String eula = customerName + ": " + EULASTRING;
		
		Log.info("\nRunning TC58 - Test Description: Adding Customer");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		

		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);		
		assertTrue(manageCustomersPage.findExistingCustomer(customerName));
	}
	
	/**
	 * Test Case ID: TC59_EditCustomer_PicAdmin
	 * Script:   	 	
	 * - On Home Page, click Picarro Administration -> Manage Customers
	 * - Click Edit link
	 * - Modify customer details and click OK
	 * Results: - 
	 * - User is navigated to Manage Customers page and modified customer details are present in the table
	 */
	@Test
	public void TC59_EditCustomer_PicAdmin() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "TC59";
		String eula = customerName + ": " + EULASTRING;
		String newCustomerName = customerName + "NEW";		
		
		Log.info("\nRunning ADM002 - Test Description: Editing Customer");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
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
	@Ignore
	public void MCP000A() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "MCP000A";
		String eula = customerName + ": " + EULASTRING;
		String newCustomerName = customerName + "NEW";
		String eulaNew = customerName + ": " + EULASTRING + "NEW";
		
		Log.info("\nRunning MCP000A - Test Description: Editing Customer with eula change only");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
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
	@Ignore
	public void MCP000B() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "MCP000B";
		String eula = customerName + ": " + EULASTRING;
		
		Log.info("\nRunning MCP000B - Test Description: Checking Customer Account Status");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		manageCustomersPage.open();
		
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		assertTrue(manageCustomersPage.getCustomerStatus(customerName).equalsIgnoreCase(CUSTOMERENABLED));
	}
	
	/**
	 * Test Case ID: MCP000C
	 * Test Description: Changing Customer Account Status
	 * 
	 */
	@Ignore
	public void MCP000C() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "MCP000C";
		String eula = customerName + ": " + EULASTRING;
		
		Log.info("\nRunning MCP000C - Test Description: Changing Customer Account Status");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		manageCustomersPage.open();
		
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		if (manageCustomersPage.changeCustomerAccountStatus(customerName, false))
			assertTrue(manageCustomersPage.getCustomerStatus(customerName).equalsIgnoreCase(CUSTOMERDISABLED));
		else
			fail("\nTest case MCP000C failed!\n");
	}	
}