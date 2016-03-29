/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import surveyor.dataprovider.DataGenerator;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageUsersPage;
import common.source.Log;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;

import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ManageCustomersPageTest extends SurveyorBaseTest {
	private static ManageCustomersPage manageCustomersPage;
	private static ManageUsersPage manageUsersPage;
	private static ManageLocationsPage manageLocationsPage;
	
	@BeforeClass
	public static void setupManageCustomersPageTest() {
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
		
		manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageUsersPage);
		
		manageLocationsPage = new ManageLocationsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageLocationsPage);
	}

	/**
	 * Test Case ID: TC1243_DisableExistingCustomer_PicAdmin
	 * Script:
	 * - On Home Page, click Picarro Administration -> Manage Customers
     * - Click on 'Edit' button
     * - Disable the Customer account. Click OK
     * Results:
     * Disabled Customer's User will not be allowed to log in the application
	 */
	@Test
	public void TC1243_DisableExistingCustomer_PicAdmin(){
		String customerName = "TestCustomer";
		String userName = customerName+REGBASEUSERNAME;
		
        Log.info("\nRunning TC1243_DisableExistingCustomer_PicAdmin - "+
                 "Test Description: Disable Existing Customer can not login");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		

		manageCustomersPage.open();
		manageCustomersPage.performSearch(customerName);
		manageCustomersPage.changeCustomerAccountStatus(customerName, false);
		loginPage = manageUsersPage.logout();

		// verify disabled customer user cannot login.
		loginPage.open();
		assertTrue(loginPage.loginNormalAs(userName, USERPASSWORD) == null);
				
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		

		manageCustomersPage.open();
		manageCustomersPage.performSearch(customerName);
		manageCustomersPage.changeCustomerAccountStatus(customerName, true);
		loginPage = manageUsersPage.logout();

		// verify disabled customer user cannot login.
		loginPage.open();
		assertNotNull(loginPage.loginNormalAs(userName, USERPASSWORD));
	}
	
	/**
	 * Test Case ID: TC77_addCustomerBlankRequiredFields_PicAdmin
	 * Test Description: add customer - blank required fields
	 * Script:
	 * - On Home Page, click Picarro Administration -> Manage Customers
     * - Click on 'Add New Customer' button
     * - Keep Name and Eula fields blank. Click OK
     * Results:
     * Required Fields boxes should be Highlighted in red.
	 */
	@Test
	public void TC77_addCustomerBlankRequiredFields_PicAdmin(){
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getFixedSizeRandomNumber(12) + "TC77";
		String eula = customerName + ": " + EULASTRING;
		
		Log.info("\nRunning TC77_addCustomerBlankRequiredFields_PicAdmin - "+
		         "Test Description: add customer - blank required fields");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		

		manageCustomersPage.open();
		// add customer with an empty Eula
		manageCustomersPage.addNewCustomer(customerName, "");
		Log.info("Looking for a red border around the Eula text area - when it's empty");
		assertTrue("There is no red line around the Eula text area when it's empty!",manageCustomersPage.isEulaRed());
		
		// candel add 
		manageCustomersPage.clickOnAddCancelBtn();
		
		// add customer with an empty Name		
		manageCustomersPage.addNewCustomer("", eula);
		Log.info("Looking for a red border around the Name input field - when it's empty");
		assertTrue("There is no red line around the Name input field when it's empty!",manageCustomersPage.isNameRed());
		
	}	
	
	/**
	 * Test Case ID: TC78_editCustomerBlankRequiredFields_PicAdmin
	 * Test Description: edit customer - blank required fields
	 * Script:
	 * - On Home Page, click Picarro Administration -> Manage Customers
     * - Click on Edit link - Delete Eula fields data. Click OK
     * Results:
     * Required Fields boxes should be Highlighted in red.
	 */
	@Test
	public void TC78_editCustomerBlankRequiredFields_PicAdmin(){
		String customerName = "TestCustomer";
		
		
		Log.info("\nRunning TC78_editCustomerBlankRequiredFields_PicAdmin - "+
		         "Test Description: edit customer - blank required fields");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		

		manageCustomersPage.open();
		
		// edit customer with an empty Eula
		manageCustomersPage.performSearch(customerName);		
		manageCustomersPage.findCustomerAndOpenEditPage(customerName);
		manageCustomersPage.setEULAText("");
		manageCustomersPage.clickOnEditOkBtn();
		
		Log.info("Looking for a red border around the Eula text area - when it's empty");
		assertTrue("There is no red line around the Eula text area when it's empty!",manageCustomersPage.isEulaRed());
				
		// cancel add 
		manageCustomersPage.clickOnEditCancelBtn();
				
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
		
		Log.info("\nRunning TC58_AddNewCustomer_PicAdmin - Test Description: Adding Customer");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		

		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);		
		Log.info(String.format("Looking for customer - '%s' with enabled status - '%b'", customerName, true));
		assertTrue(manageCustomersPage.findExistingCustomer(customerName, true));
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
		
		Log.info("\nRunning TC59_EditCustomer_PicAdmin - Test Description: Editing Customer");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		manageCustomersPage.open();		
		
		Log.info("Adding new Customer - " + customerName);
		manageCustomersPage.addNewCustomer(customerName, eula);		
		
		Log.info("Modifying Customer account status - " + customerName);
		manageCustomersPage.changeCustomerAccountStatus(customerName, false);
	
		Log.info(String.format("Looking for customer - '%s' with enabled status - '%b'", customerName, false));
		assertTrue(manageCustomersPage.findExistingCustomer(customerName, false));
	}
	
	
	/**
	 * Test Case ID: TC88_DuplicateCustomerNotAllowed_PicAdmin
	 * Script:   	
	 * - On Home Page, click Picarro Administration -> Manage Customers
	 * - Click on 'Add New Customer' button
	 * - Provide required customer details and click OK	 
	 * Results: - 
	 * - User is navigated to Manage Customers page and new customer entry is present in the table
	 * 
	 */
	@Ignore    // Ignoring. Validation message NOT showing correctly in Product. Check if SEED script update is needed.
	public void TC88_DuplicateCustomerNotAllowed_PicAdmin() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "TC88";
		Log.info("\nRunning TC88_DuplicateCustomerNotAllowed_PicAdmin - Test Description: Admin not allowed to create duplicate Customer");
		
		String eula = customerName + ": " + EULASTRING;
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);		
		
		Log.info(String.format("Looking for customer - '%s' with enabled status - '%b'", customerName, true));
		assertTrue(manageCustomersPage.findExistingCustomer(customerName, true));
		
		// Verify cannot create duplicate customer.
		assertFalse(manageCustomersPage.addNewCustomer(customerName, eula));
	}
	
	/**
	 * Test Case ID: TC92_DisabledCustomer_PicAdmin
	 * Script:
	 * - On Home Page, click Picarro Administration -> Manage Customers
	 * - Click on 'Add New Customer' button
	 * - Provide customer details and check the Disabled checkbox. Click OK	 
	 * Results: - 
	 * - Disabled Customer's User will not be allowed to log in the application
	 */
	@Test  
	public void TC92_DisabledCustomer_PicAdmin() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getFixedSizeRandomNumber(12) + "TC93";
		String userName = customerName + testSetup.getFixedSizeRandomNumber(12) + REGBASEUSERNAME;
		String eula = customerName + ": " + EULASTRING;
		String cityName = "Santa Clara";
		String locationName = customerName + "loc";
		Log.info("\nRunning TC93_ReenableCustomer_PicAdmin - Test Description: Re-Enable Customer");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		

		manageCustomersPage.open();
		// create disabled customer.
		manageCustomersPage.addNewCustomer(customerName, eula, false);
		
		Log.info(String.format("Looking for customer - '%s' with enabled status - '%b'", customerName, false));
		assertTrue(manageCustomersPage.findExistingCustomer(customerName, false));

		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, customerName, cityName);

		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(customerName, userName,
				USERPASSWORD, CUSUSERROLEUA, locationName);

		Log.info(String.format("Looking for user: Location-[%s]; Username-[%s]", locationName, userName));
		assertTrue(manageUsersPage.findExistingUser(locationName, userName, false));
		loginPage = manageUsersPage.logout();

		// verify disabled customer user cannot login.
		loginPage.open();
		assertTrue(loginPage.loginNormalAs(userName, USERPASSWORD) == null);
	}
	
	/**
	 * Test Case ID: TC93_ReenableCustomer_PicAdmin
	 * Script:
	 * Pre-requisite: Create Disabled Customer
	 * - On Home Page, and click Picarro Administration -> Manage Customers
	 * - Click on Edit button of disabled customer
	 * - Enable the customer and click OK
	 * Results: - 
	 * - Customer's User should be able to log in the application
	 * 
	 */
	@Test
	public void TC93_ReenableCustomer_PicAdmin() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getFixedSizeRandomNumber(12) + "TC93";
		String userName = customerName + testSetup.getFixedSizeRandomNumber(12) + REGBASEUSERNAME;
		String eula = customerName + ": " + EULASTRING;

		String cityName = "Santa Clara";
		String locationName = customerName + "loc";
		
		Log.info("\nRunning TC93_ReenableCustomer_PicAdmin - Test Description: Re-Enable Customer");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageCustomersPage.open();
		// create customer (not enabled).
		manageCustomersPage.addNewCustomer(customerName, eula, false);
		manageCustomersPage.changeCustomerAccountStatus(customerName, true);
		
		Log.info(String.format("Looking for customer - '%s' with enabled status - '%b'", customerName, true));
		assertTrue(manageCustomersPage.findExistingCustomer(customerName, true));

		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, customerName, cityName);

		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, 
				CUSUSERROLEUA, locationName);

		Log.info(String.format("Looking for User: Location-[%s], UserName-[%s]", customerName, userName));
		assertTrue(manageUsersPage.findExistingUser(locationName, userName, false));
		loginPage = manageUsersPage.logout();

		loginPage.open();
		HomePage homePage = loginPage.loginNormalAs(userName, USERPASSWORD);
		assertTrue(homePage.checkIfAtHomePage());
	}

	/**
	 * Test Case ID: TC96_Max100KCharsInEULA_PicAdmin
	 * Script:
	 * - On Home Page, click Picarro Administration -> Manage Customers
	 * - Click on 'Add New Customer' button
	 * - Provide more than 100,000 characters in Eula field and click OK
	 * - Repeat the same steps for Edit Customer form
	 * Results: - 
	 * - User cannot enter more than 100,000 characters and message having limit of characters displayed
	 */
	@Ignore
	// DEFECT: Sending 100K characters to TextArea is causing Chrome browser to NOT respond correct. Look for workaround.
	public void TC96_Max100KCharsInEULA_PicAdmin() {
		final int HUNDRED_K = 100000;
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "TC96";
		Log.info("\nRunning TC96_Max100KCharsInEULA_PicAdmin - Test Description: More than 100,000 characters not allowed in EULA field");

		String eula100K = DataGenerator.getRandomWords(HUNDRED_K);
		String eula100KPlusOne = eula100K + "A";
assertTrue(eula100K.length() == HUNDRED_K);
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		

		manageCustomersPage.open();
		// Add customer with 100K characters in EULA.
		manageCustomersPage.addNewCustomer(customerName, eula100K, true);
		Log.info(String.format("Looking for customer - '%s' with enabled status - '%b'", customerName, true));
		assertTrue(manageCustomersPage.findExistingCustomer(customerName, true));

		// Add customer with 100K+1 characters in EULA.
		manageCustomersPage.addNewCustomer(customerName, eula100KPlusOne, true);
		manageCustomersPage.findCustomerAndOpenEditPage(customerName);
		
		// Verify ONLY 100K characters got inputted in EULA.
		assertTrue(manageCustomersPage.getEulaText().length() == HUNDRED_K);
	}
		
	/**
	 * Test Case ID: TC97_CustomerNameMax255Chars_PicAdmin
	 * Script:
	 * - On Home Page, click Picarro Administration -> Manage Customers
	 * - Click on 'Add New Customer' button
	 * - Provide more than 255 characters in Name field and click OK
	 * - Repeat the same steps for Edit customer
	 * Results: - 
	 * - User cannot enter more than 255 characters and message having limit of characters displayed
	 */
	@Test
	public void TC97_CustomerNameMax255Chars_PicAdmin() {
		final int MAX_CHARS = 255;
		String customerName255 = CUSTOMERNAMEPREFIX + testSetup.getFixedSizePseudoRandomString(245) + "TC97";
		String customerName256 = CUSTOMERNAMEPREFIX + testSetup.getFixedSizePseudoRandomString(245) + "TC97" + "A";
		String eula = customerName255 + ": " + EULASTRING;
		
		Log.info("\nRunning TC97_CustomerNameMax255Chars_PicAdmin - Test Description: More than 255 characters not allowed in Name field");

		assertTrue(customerName255.length() == MAX_CHARS);
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		

		manageCustomersPage.open();
		// Add customer with 255 characters Customer name.
		manageCustomersPage.addNewCustomer(customerName255, eula, true);
		Log.info(String.format("Looking for customer - '%s' with enabled status - '%b'", customerName255, true));
		assertTrue(manageCustomersPage.findExistingCustomer(customerName255, true));

		// Add customer with 256 characters in Customer name.
		manageCustomersPage.addNewCustomer(customerName256, eula, true);
		assertTrue(manageCustomersPage.findExistingCustomer(customerName256.substring(0, MAX_CHARS), true));
		assertFalse(manageCustomersPage.findExistingCustomer(customerName256, true));
		
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