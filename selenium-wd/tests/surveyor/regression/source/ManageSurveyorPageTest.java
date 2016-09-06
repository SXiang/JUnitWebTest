/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.CUSTOMERNAMEPREFIX;
import static surveyor.scommon.source.SurveyorConstants.EULASTRING;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.SQAPICSUP;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageSurveyorAdminPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.DataTablePage.TableColumnType;
import common.source.Log;

import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ManageSurveyorPageTest extends SurveyorBaseTest {
	private static ManageLocationsPage manageLocationsPage;
	private static ManageSurveyorPage manageSurveyorPage;
	private static ManageSurveyorAdminPage manageSurveyorAdminPage;
	private static ManageCustomersPage manageCustomersPage;
	protected String pagination = "100";
	
	@BeforeClass
	public static void setupManageSurveyorPageTest() {
		manageLocationsPage = new ManageLocationsPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(),  manageLocationsPage);
		
		manageSurveyorPage = new ManageSurveyorPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(),  manageSurveyorPage);
		
		manageCustomersPage = new ManageCustomersPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(),  manageCustomersPage);
		
		manageSurveyorAdminPage = new ManageSurveyorAdminPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(),  manageSurveyorAdminPage);
	}
	
	/**
	 * Test Case ID: TC63_AddSurveyor_PicAdmin
	 * Test Description: Adding Surveyor
	 * 
	 */
	@Test
	public void TC63_AddSurveyor_PicAdmin() {
		String customerName = CUSTOMERNAMEPREFIX + getTestSetup().getRandomNumber() + "TC63";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String surveyorName = locationName + "Sur";
		String cityName ="Santa Clara";
		
		Log.info("\nRunning TC63_AddSurveyor_PicAdmin...");
		
		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, customerName,cityName);
		
		manageSurveyorPage.open();
		
		Log.info(String.format("Adding new Surveyor: Name-[%s]; Location-[%s]; Customer-[%s]", 
				surveyorName, locationName, customerName)); 
		manageSurveyorPage.addNewSurveyor(surveyorName, locationName, customerName);
		
		Log.info(String.format("Find existing Surveyor: Customer-[%s]; Location-[%s]; Surveyor Name-[%s]", 
				customerName, locationName, surveyorName)); 
		assertTrue(manageSurveyorPage.findExistingSurveyor(customerName, locationName, surveyorName));
	}
	
	/**
	 * Test Case ID: TC64_EditSurveyor_PicAdmin
	 * Script:   	 	 	
	 * - On Home Page, click Picarro Administration -> Manage Surveyors
	 * - Click on Edit link
	 * - Modify Surveyor details and click O
	 * Results: - 
	 * - User is navigated to Manage Surveyors page and modified Surveyor details are present in the table
	 */
	@Test
	public void TC64_EditSurveyor_PicAdmin() {
		String customerName = CUSTOMERNAMEPREFIX + getTestSetup().getRandomNumber() + "TC64";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String surveyorName = locationName + "Sur";
		String surveyorNameNew = surveyorName + "New";
		String cityName ="Santa Clara";
		
		Log.info("\nRunning TC63_AddSurveyor_PicAdmin...");
		
		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, customerName,cityName);
		
		manageSurveyorPage.open();
		
		Log.info(String.format("Adding new Surveyor: Name-[%s]; Location-[%s]; Customer-[%s]", 
				surveyorName, locationName, customerName)); 
		manageSurveyorPage.addNewSurveyor(surveyorName, locationName, customerName);
		
		Log.info(String.format("Editing Surveyor: Location-[%s]; Current Surveyor Name-[%s]; New Surveyor Name-[%s]", 
				locationName, surveyorName, surveyorNameNew)); 
		manageSurveyorAdminPage.editExistingSurveyor(customerName,locationName, surveyorName, locationName,surveyorNameNew, false);

		Log.info(String.format("Find existing Surveyor: Customer-[%s]; Location-[%s]; New Surveyor Name-[%s]", 
				customerName, locationName, surveyorNameNew)); 
		assertTrue(manageSurveyorPage.findExistingSurveyor(customerName, locationName, surveyorNameNew));
	}
	
	/**
	 * Test Case ID: TC120 Test Description: Picarro admin not allowed to
	 * create duplicate Surveyor
	 */
	@Test
	public void TC120_DuplicateSurveyorCreationNotAllowed() {
		Log.info("\nRunning - TC120 - Picarro admin not allowed to create duplicate surveyor\n");

		String customerName = CUSTOMERNAMEPREFIX + getTestSetup().getRandomNumber() + "TC120";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String surveyorName = locationName + "Sur";
		String cityName ="Santa Clara";
		
		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, customerName,cityName);
		
		manageSurveyorPage.open();
		
		Log.info(String.format("Adding new Surveyor: Name-[%s]; Location-[%s]; Customer-[%s]", 
				surveyorName, locationName, customerName)); 
		manageSurveyorPage.addNewSurveyor(surveyorName, locationName, customerName);
		
		Log.info(String.format("Find existing Surveyor: Customer-[%s]; Location-[%s]; Surveyor Name-[%s]", 
				customerName, locationName, surveyorName)); 
		assertTrue(manageSurveyorPage.findExistingSurveyor(customerName, locationName, surveyorName));

		manageSurveyorPage.open();
		assertFalse(manageSurveyorPage.addNewSurveyor(surveyorName, locationName, customerName));
	}

	/**
	 * Test Case ID: TC121 Test Description: Admin not allowed to edit Surveyor
	 * Unit having details same as existing surveyor unit
	 */
	@Test
	public void TC121_DuplicateEditSurveyor_PicAdmin() {
		String customerName = CUSTOMERNAMEPREFIX + getTestSetup().getRandomNumber() + "TC121";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String surveyorName = locationName + "Sur";
		String surveyorNameNew = surveyorName + "New";
		String cityName ="Santa Clara";
		
		Log.info("\nRunning TC121_EditSurveyor_PicAdmin...");
		
		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, customerName,cityName);
		
		manageSurveyorPage.open();
		
		Log.info(String.format("Adding new Surveyor: Name-[%s]; Location-[%s]; Customer-[%s]", 
				surveyorName, locationName, customerName)); 
		manageSurveyorPage.addNewSurveyor(surveyorName, locationName, customerName);
		
		Log.info(String.format("Editing Surveyor: Location-[%s]; Current Surveyor Name-[%s]; New Surveyor Name-[%s]", 
				locationName, surveyorName, surveyorNameNew)); 
		manageSurveyorAdminPage.editExistingSurveyor(customerName, locationName, surveyorName, locationName, surveyorNameNew, false);

		Log.info(String.format("Find existing Surveyor: Customer-[%s]; Location-[%s]; New Surveyor Name-[%s]", 
				customerName, locationName, surveyorNameNew)); 
		assertTrue(manageSurveyorPage.findExistingSurveyor(customerName, locationName, surveyorNameNew));
		
		manageSurveyorPage.open();
		assertFalse(manageSurveyorPage.editExistingSurveyor(locationName, surveyorName, surveyorNameNew, surveyorName));
	}	
	
	/**
	 * Test Case ID: TC101_MaxSurDescCharLimit
	 * Test Description: More than 400 characters not allowed in Surveyor Description field
	 * Expected Result: User cannot enter more than 400 characters
	 * 
	 */
	@Test
	public void TC101_MaxSurDescCharLimit() {
		String customerName = CUSTOMERNAMEPREFIX + getTestSetup().getFixedSizeRandomNumber(13) + "TC101";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String surveyorName400Chars = locationName + "Sur" + getTestSetup().getFixedSizeRandomNumber(370);
		String surveyorName401Chars = locationName + "SurA" + getTestSetup().getFixedSizeRandomNumber(370);
		String cityName ="Santa Clara";
		
		Log.info("\nRunning TC101_MaxSurveyorDescLimit...");
		
		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, customerName,cityName);
		
		manageSurveyorPage.open();
		
		Log.info(String.format("Adding new Surveyor: Name-[%s]; Location-[%s]; Customer-[%s]", 
				surveyorName400Chars, locationName, customerName)); 
		manageSurveyorPage.addNewSurveyor(surveyorName400Chars, locationName, customerName);
		Log.info(String.format("Adding new Surveyor: Name-[%s]; Location-[%s]; Customer-[%s]", 
				surveyorName401Chars, locationName, customerName)); 
		manageSurveyorPage.addNewSurveyor(surveyorName401Chars, locationName, customerName);
		
		Log.info(String.format("Find existing Surveyor: Customer-[%s]; Location-[%s]; Surveyor Name-[%s]", 
				customerName, locationName, surveyorName400Chars)); 
		assertTrue(manageSurveyorPage.findExistingSurveyor(customerName, locationName, surveyorName400Chars));
		Log.info(String.format("Find existing Surveyor: Customer-[%s]; Location-[%s]; Surveyor Name-[%s]", 
				customerName, locationName, surveyorName401Chars)); 
		manageSurveyorPage.open();
		assertFalse(manageSurveyorPage.findExistingSurveyor(customerName, locationName, surveyorName401Chars));
		
		Log.info(String.format("Editing Surveyor: Location-[%s]; Current Surveyor Name-[%s]; New Surveyor Name-[%s]", 
				locationName, surveyorName400Chars, surveyorName401Chars)); 
		manageSurveyorAdminPage.editExistingSurveyor(customerName,locationName, surveyorName400Chars, locationName,surveyorName401Chars, false);
		assertFalse(manageSurveyorPage.findExistingSurveyor(customerName, locationName, surveyorName401Chars));
	}
	
	/**
	 * Test Case ID: TC127_AddMultipleSurveyor_PicAdmin 
	 * Test Description: Customer (e.g. PGE) can have multiple Surveyor Unit Test Script: - On
	 * Home Page, click Picarro Administration -> Manage Surveyors - Click on
	 * 'Add New Surveyor' button - Provide required surveyor unit details and
	 * click OK - Add second surveyor unit 
	 * Expected Result: - Surveyor units are added successfully
	 */
	@Test
	public void TC127_AddMultipleSurveyor_PicAdmin() {
		String customerName = CUSTOMERNAMEPREFIX + getTestSetup().getRandomNumber() + "TC127";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String surveyorName1 = locationName + "Sur1";
		String surveyorName2 = locationName + "Sur2";
		String cityName ="Santa Clara";
		
		Log.info("\nRunning TC127_AddMultipleSurveyor_PicAdmin...");
		
		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, customerName,cityName);
		
		manageSurveyorPage.open();
		
		Log.info(String.format("Adding new Surveyor: Name-[%s]; Location-[%s]; Customer-[%s]", 
				surveyorName1, locationName, customerName)); 
		manageSurveyorPage.addNewSurveyor(surveyorName1, locationName, customerName);
		
		Log.info(String.format("Adding new Surveyor: Name-[%s]; Location-[%s]; Customer-[%s]", 
				surveyorName2, locationName, customerName));
		manageSurveyorPage.addNewSurveyor(surveyorName2, locationName, customerName);
		
		Log.info(String.format("Find existing Surveyor: Customer-[%s]; Location-[%s]; Surveyor Name-[%s]", 
				customerName, locationName, surveyorName1)); 
		assertTrue(manageSurveyorPage.findExistingSurveyor(customerName, locationName, surveyorName1));
		
		Log.info(String.format("Find existing Surveyor: Customer-[%s]; Location-[%s]; Surveyor Name-[%s]", 
				customerName, locationName, surveyorName2)); 
		assertTrue(manageSurveyorPage.findExistingSurveyor(customerName, locationName, surveyorName2));
	}
	
	/**
	 * Test Case ID: TC498_ManageSurveyors_PicSup Test Description: Manage
	 * Surveyors Test Script: - On Home Page, click on Administration -> Manage
	 * Surveyors Expected Result: - User can see list of all surveyors - User
	 * cannot add, but can edit the surveyor unit
	 */
	@Test
	public void TC498_ManageSurveyors_PicSup() {
		String customerName = CUSTOMERNAMEPREFIX + getTestSetup().getRandomNumber() + "TC498";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String surveyorName = locationName + "Sur";
		String surveyorNewName = locationName + "SurNew";
		String cityName ="Santa Clara";
		
		Log.info("\nRunning TC498_ManageSurveyors_PicSup...");
		
		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, customerName,cityName);
		
		manageSurveyorPage.open();
		
		Log.info(String.format("Adding new Surveyor: Name-[%s]; Location-[%s]; Customer-[%s]", 
				surveyorName, locationName, customerName)); 
		manageSurveyorPage.addNewSurveyor(surveyorName, locationName, customerName);
		assertTrue(manageSurveyorPage.findExistingSurveyor(customerName, locationName, surveyorName));
		
		getLoginPage().open();
		getLoginPage().loginNormalAs(SQAPICSUP, USERPASSWORD);
		manageSurveyorPage.open();
		assertFalse(manageSurveyorPage.isAddNewSurveyorBtnPresent());
		
		Log.info(String.format("Find existing Surveyor: Customer-[%s]; Location-[%s]; Surveyor Name-[%s]", 
				customerName, locationName, surveyorName)); 
		assertTrue(manageSurveyorPage.findExistingSurveyor(customerName, locationName, surveyorName));
		
		Log.info(String.format("Editing Surveyor: Location-[%s]; Current Surveyor Name-[%s]; New Surveyor Name-[%s]", 
				locationName, surveyorName, surveyorNewName)); 
		manageSurveyorAdminPage.editExistingSurveyor(customerName,locationName, surveyorName, locationName, surveyorName, true);
		assertTrue(manageSurveyorPage.findExistingSurveyor(customerName, locationName, surveyorName));
	}
	
	/**
	 * Test Case ID: TC132_ManageSurveyors_SortColumns Script: - Sort records based on attributes present Results: - - User is able to sort the list of records based on specified attribute
	 */
	@Test
	public void TC132_ManageSurveyors_SortColumns() {
		Log.info("\nRunning TC132_ManageUsers_SortColumns");
		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		manageSurveyorPage.open();
		HashMap<String, TableColumnType> columnMap = new HashMap<String, TableColumnType>();
		columnMap.put(CONSTANT_CUSTOMER, TableColumnType.String);
		assertTrue(manageSurveyorPage.checkTableSort("datatable_wrapper", columnMap, pagination, manageSurveyorPage.getPaginationOption()));
		columnMap.remove(CONSTANT_CUSTOMER);
		columnMap.put(CONSTANT_LOCATION, TableColumnType.String);
		assertTrue(manageSurveyorPage.checkTableSort("datatable_wrapper", columnMap, pagination, manageSurveyorPage.getPaginationOption()));
		columnMap.remove(CONSTANT_LOCATION);
		columnMap.put(CONSTANT_SURVEYOR, TableColumnType.String);
		assertTrue(manageSurveyorPage.checkTableSort("datatable_wrapper", columnMap, pagination, manageSurveyorPage.getPaginationOption()));		
	}
	
	/**
	 * Test Case ID: TC144_ManageSurveyors_VerifyPagination: - Verify pagination settings
	 */
	@Test
	public void TC144_ManageSurveyors_VerifyPagination() {
		Log.info("\nRunning Pagination - 10,25,50 and 100 Pagination ManageSurveyors");
		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		manageSurveyorPage.open();
		String paginationSetting25 = "25";
		String paginationSetting50 = "50";
		String paginationSetting100 = "100";

		assertTrue(manageSurveyorPage.checkPaginationSetting(PAGINATIONSETTING));
		assertTrue(!(manageSurveyorPage.getNumberofRecords() > Integer.parseInt(PAGINATIONSETTING)));
		assertTrue(manageSurveyorPage.checkPaginationSetting(paginationSetting25));
		assertTrue(!(manageSurveyorPage.getNumberofRecords() > Integer.parseInt(paginationSetting25)));
		manageSurveyorPage.getNextBtn().click();
		manageSurveyorPage.waitForPageToLoad();
		assertTrue(!(manageSurveyorPage.getNumberofRecords() > Integer.parseInt(paginationSetting25)));
		manageSurveyorPage.getPreviousBtn().click();
		manageSurveyorPage.waitForPageToLoad();
		assertTrue(manageSurveyorPage.checkPaginationSetting(paginationSetting50));
		assertTrue(!(manageSurveyorPage.getNumberofRecords() > Integer.parseInt(paginationSetting50)));
		manageSurveyorPage.getNextBtn().click();
		manageSurveyorPage.waitForPageToLoad();
		assertTrue(!(manageSurveyorPage.getNumberofRecords() > Integer.parseInt(paginationSetting50)));
		manageSurveyorPage.getPreviousBtn().click();
		manageSurveyorPage.waitForPageToLoad();
		assertTrue(manageSurveyorPage.checkPaginationSetting(paginationSetting100));
		assertTrue(!(manageSurveyorPage.getNumberofRecords() > Integer.parseInt(paginationSetting100)));
	}
}