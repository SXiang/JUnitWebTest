/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import surveyor.dataprovider.DataGenerator;
import surveyor.scommon.actions.PageActionsStore;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.PageObjectFactory;
import common.source.Log;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;
import static surveyor.scommon.source.SurveyorConstants.*;
import org.openqa.selenium.WebDriver;

/**
 * @author zlu
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ManageCustomersPageTest extends SurveyorBaseTest {
	private static ManageCustomersPage manageCustomersPage;
	private static ManageUsersPage manageUsersPage;
	private static ManageLocationsPage manageLocationsPage;
	private static HomePage homePage;
	private static LoginPage loginPage;
	protected static WebDriver driver;
	@BeforeClass
	public static void setupManageCustomersPageTest() {
		initializeTestObjects(); // ensures TestSetup and TestContext are initialized before Page object creation.
	}

	/**
	 * This method is called by the 'worker' thread
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Log.info("[THREAD Debug Log] - Calling setup beforeTest()");
		PageActionsStore.INSTANCE.clearStore();
		
		initializeTestObjects();

		PageObjectFactory pageObjectFactory = new PageObjectFactory();
		
		manageCustomersPage = pageObjectFactory.getManageCustomersPage();
		PageFactory.initElements(getDriver(),  manageCustomersPage);

		manageUsersPage = pageObjectFactory.getManageUsersPage();
		PageFactory.initElements(getDriver(), manageUsersPage);

		manageLocationsPage = pageObjectFactory.getManageLocationsPage();
		PageFactory.initElements(getDriver(), manageLocationsPage);

		homePage = pageObjectFactory.getHomePage();
		PageFactory.initElements(getDriver(), homePage);

		loginPage = pageObjectFactory.getLoginPage();
		PageFactory.initElements(getDriver(), loginPage);
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
		String customerName = CUSTOMERNAMEPREFIX + getTestSetup().getFixedSizeRandomNumber(12) + "TC1243";
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + REGBASEPICUSERNAME;
		String location = "Santa Clara";
		String locationDesc = location + " for test";

		 Log.info("\nRunning TC1243_DisableExistingCustomer_PicAdmin - "+
                 "Test Description: Disable Existing Customer can not login");
		
		// *** Add a new user/customer for this test ***
		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationDesc, customerName, location);
		
		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEDR, TIMEZONECT, locationDesc);
		
		loginPage = manageCustomersPage.logout();
		
		// *** Start test ***
       
		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());		

		manageCustomersPage.open();
		manageCustomersPage.performSearch(customerName);
		manageCustomersPage.changeCustomerAccountStatus(customerName, false);
		
		loginPage = manageUsersPage.logout();

		// verify disabled customer user cannot login.
		loginPage.open();
		assertTrue(loginPage.loginNormalAs(userName, USERPASSWORD) == null);
				
		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());		

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
		String customerName = CUSTOMERNAMEPREFIX + getTestSetup().getFixedSizeRandomNumber(12) + "TC77";
		String eula = customerName + ": " + EULASTRING;
		
		Log.info("\nRunning TC77_addCustomerBlankRequiredFields_PicAdmin - "+
		         "Test Description: add customer - blank required fields");
		
		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());		

		manageCustomersPage.open();
		// add customer with an empty Eula
		manageCustomersPage.addNewCustomer(customerName, "");
		Log.info("Looking for field required validation message on Eula text area - when it's empty");
		assertTrue("There is no field required validation message on Eula text area when it's empty!", manageCustomersPage.verifyEulaValidation());
		
		// cancel add 
		manageCustomersPage.clickOnAddCancelBtn();
		
		// add customer with an empty Name		
		manageCustomersPage.addNewCustomer("", eula);
		Log.info("Looking for field required validation message on Name input field - when it's empty");
		assertTrue("There is no field required validation message on Name input field when it's empty!", manageCustomersPage.verifyNameValidation());
		
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
		String customerName = CUSTOMERNAMEPREFIX + getTestSetup().getRandomNumber() + "TC78";
		String eula = customerName + ": " + EULASTRING;
		Log.info("\nRunning TC78_editCustomerBlankRequiredFields_PicAdmin - "+
		         "Test Description: edit customer - blank required fields");
		
		// *** Add a new customer for this test ***
		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);	
		loginPage = manageUsersPage.logout();
		
		// *** Start test ***
		
		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());		

		manageCustomersPage.open();
		
		// edit customer with an empty Eula
		manageCustomersPage.performSearch(customerName);		
		manageCustomersPage.findCustomerAndOpenEditPage(customerName);
		manageCustomersPage.setEULAText("");
		manageCustomersPage.clickOnEditOkBtn();
		
		Log.info("Looking for field required validation message on Eula text area - when it's empty");
		assertTrue("There is no field required validation message on Eula text area when it's empty!", manageCustomersPage.verifyEulaValidation());
				
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
		String customerName = CUSTOMERNAMEPREFIX + getTestSetup().getRandomNumber() + "TC58";
		String eula = customerName + ": " + EULASTRING;

		Log.info("\nRunning TC58_AddNewCustomer_PicAdmin - Test Description: Adding Customer");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());		

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
		String customerName = CUSTOMERNAMEPREFIX + getTestSetup().getRandomNumber() + "TC59";
		String eula = customerName + ": " + EULASTRING;

		Log.info("\nRunning TC59_EditCustomer_PicAdmin - Test Description: Editing Customer");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());		

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
	 * - Duplicate Customer creation not allowed
	 */
	@Test
	public void TC88_DuplicateCustomerNotAllowed_PicAdmin() {
		String customerName = CUSTOMERNAMEPREFIX + getTestSetup().getRandomNumber() + "TC88";
		Log.info("\nRunning TC88_DuplicateCustomerNotAllowed_PicAdmin - Test Description: Admin not allowed to create duplicate Customer");
		
		String eula = customerName + ": " + EULASTRING;

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());		

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
		String customerName = CUSTOMERNAMEPREFIX + getTestSetup().getFixedSizeRandomNumber(12) + "TC93";
		String userName = customerName + getTestSetup().getFixedSizeRandomNumber(12) + REGBASEUSERNAME;
		String eula = customerName + ": " + EULASTRING;
		String cityName = "Santa Clara";
		String locationName = customerName + "loc";
		Log.info("\nRunning TC93_ReenableCustomer_PicAdmin - Test Description: Re-Enable Customer");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());		

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
		String customerName = CUSTOMERNAMEPREFIX + getTestSetup().getFixedSizeRandomNumber(12) + "TC93";
		String userName = customerName + getTestSetup().getFixedSizeRandomNumber(12) + REGBASEUSERNAME;
		String eula = customerName + ": " + EULASTRING;

		String cityName = "Santa Clara";
		String locationName = customerName + "loc";


		Log.info("\nRunning TC93_ReenableCustomer_PicAdmin - Test Description: Re-Enable Customer");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		manageCustomersPage.open();
		// create customer (not enabled).
		manageCustomersPage.addNewCustomer(customerName, eula, false);
		manageCustomersPage.open();
		manageCustomersPage.changeCustomerAccountStatus(customerName, true);

		manageCustomersPage.open();
		Log.info(String.format("Looking for customer - '%s' with enabled status - '%b'", customerName, true));
		assertTrue(manageCustomersPage.findExistingCustomer(customerName, true));

		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, customerName, cityName);

		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, 
				CUSUSERROLEUA, locationName);

		manageUsersPage.open();
		Log.info(String.format("Looking for User: Location-[%s], UserName-[%s]", customerName, userName));
		assertTrue(manageUsersPage.findExistingUser(locationName, userName, false));

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
		String customerName = CUSTOMERNAMEPREFIX + getTestSetup().getRandomNumber() + "TC96";
		Log.info("\nRunning TC96_Max100KCharsInEULA_PicAdmin - Test Description: More than 100,000 characters not allowed in EULA field");

		String eula100K = DataGenerator.getRandomWords(HUNDRED_K);
		String eula100KPlusOne = eula100K + "A";
		assertTrue(eula100K.length() == HUNDRED_K);

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());		

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
		String customerName255 = CUSTOMERNAMEPREFIX + getTestSetup().getFixedSizePseudoRandomString(245) + "TC97";
		String customerName256 = CUSTOMERNAMEPREFIX + getTestSetup().getFixedSizePseudoRandomString(245) + "TC97" + "A";
		String eula = customerName255 + ": " + EULASTRING;

		Log.info("\nRunning TC97_CustomerNameMax255Chars_PicAdmin - Test Description: More than 255 characters not allowed in Name field");

		assertTrue(customerName255.length() == MAX_CHARS);

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());		

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
		String customerName = CUSTOMERNAMEPREFIX + getTestSetup().getRandomNumber() + "MCP000B";
		String eula = customerName + ": " + EULASTRING;

		Log.info("\nRunning MCP000B - Test Description: Checking Customer Account Status");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());		

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
		String customerName = CUSTOMERNAMEPREFIX + getTestSetup().getRandomNumber() + "MCP000C";
		String eula = customerName + ": " + EULASTRING;

		Log.info("\nRunning MCP000C - Test Description: Changing Customer Account Status");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());		

		manageCustomersPage.open();

		manageCustomersPage.addNewCustomer(customerName, eula);

		if (manageCustomersPage.changeCustomerAccountStatus(customerName, false))
			assertTrue(manageCustomersPage.getCustomerStatus(customerName).equalsIgnoreCase(CUSTOMERDISABLED));
		else
			fail("\nTest case MCP000C failed!\n");
	}	
	
	/**
	 * Test Case ID: TC469_ManageCustomer
	 * Script:   	
	 * - On Home Page, click on Administration -> Manage Customer 
	 * Results: - 
	 * - User can see customers added by picarro admin
	 * - User cannot add or edit the customer
	 */
	@Test
	public void TC469_ManageCustomer_PicSupport() {
		String customerName = CUSTOMERNAMEPREFIX + getTestSetup().getRandomNumber()
				+ "TC469";
		String eula = customerName + ": " + EULASTRING;

		Log.info("\nRunning TC469_ManageCustomer_PicSupport - Test Description: View Customer");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(),
				getTestSetup().getLoginPwd());

		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		Log.info(String.format(
				"Looking for customer - '%s' with enabled status - '%b'",
				customerName, true));
		assertTrue(manageCustomersPage.findExistingCustomer(customerName, true));

		manageCustomersPage.logout();

		loginPage.login(SQAPICSUP, USERPASSWORD);
		homePage.waitForPageLoad();
		
		manageCustomersPage.open();
		manageCustomersPage.performSearch(customerName);
		assertTrue(manageCustomersPage.findExistingCustomer(customerName, true));
		assertFalse(manageCustomersPage.isAddCustomerBtnPresent());
		assertFalse(manageCustomersPage.isEditBtnPresent());
	}

	/**
	 * Test Case ID: TC132_ManageCustomer_SortColumns
	 * Script:   	
	 * - Sort records based on attributes present
	 * Results: - 
	 * - User is able to sort the list of records based on specified attribute
	 */
	@Test
	public void TC132_ManageCustomer_SortColumns() {
		Log.info("\nRunning TC132_ManageCustomer_SortColumns");
		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());		
		manageCustomersPage.open();
		assertTrue(manageCustomersPage.areTableColumnsSorted());		
	}
}