/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import com.google.sitebricks.rendering.resource.Assets;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.CryptoUtility;
import common.source.Log;
import surveyor.dataprovider.RunAs;
import surveyor.dataprovider.UserDataProvider;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageSurveyorAdminPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;
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
	
	@BeforeClass
	public static void setupManageSurveyorPageTest() {
		manageLocationsPage = new ManageLocationsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageLocationsPage);
		
		manageSurveyorPage = new ManageSurveyorPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageSurveyorPage);
		
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
		
		manageSurveyorAdminPage = new ManageSurveyorAdminPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageSurveyorAdminPage);
	}
	
	/**
	 * Test Case ID: TC63_AddSurveyor_PicAdmin
	 * Test Description: Adding Surveyor
	 * 
	 */
	@Test
	public void TC63_AddSurveyor_PicAdmin() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "TC63";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String surveyorName = locationName + "Sur";
		String cityName ="Santa Clara";
		
		Log.info("\nRunning TC63_AddSurveyor_PicAdmin...");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
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
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "TC64";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String surveyorName = locationName + "Sur";
		String surveyorNameNew = surveyorName + "New";
		String cityName ="Santa Clara";
		
		Log.info("\nRunning TC63_AddSurveyor_PicAdmin...");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
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
		manageSurveyorAdminPage.editExistingSurveyor(locationName, surveyorName, surveyorNameNew, false);

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

		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "TC120";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String surveyorName = locationName + "Sur";
		String cityName ="Santa Clara";
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
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
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "TC121";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String surveyorName = locationName + "Sur";
		String surveyorNameNew = surveyorName + "New";
		String cityName ="Santa Clara";
		
		Log.info("\nRunning TC121_EditSurveyor_PicAdmin...");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
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
		manageSurveyorAdminPage.editExistingSurveyor(locationName, surveyorName, surveyorNameNew, false);

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
	 * @param user
	 * @param pwd
	 */
	@Test
	public void TC101_MaxSurDescCharLimit() {
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getFixedSizeRandomNumber(13) + "TC101";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String surveyorName400Chars = locationName + "Sur" + testSetup.getFixedSizeRandomNumber(370);
		String surveyorName401Chars = locationName + "SurA" + testSetup.getFixedSizeRandomNumber(370);
		String cityName ="Santa Clara";
		
		Log.info("\nRunning TC101_MaxSurveyorDescLimit...");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
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
		manageSurveyorAdminPage.editExistingSurveyor(locationName, surveyorName400Chars, surveyorName401Chars, false);
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
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "TC127";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String surveyorName1 = locationName + "Sur1";
		String surveyorName2 = locationName + "Sur2";
		String cityName ="Santa Clara";
		
		Log.info("\nRunning TC127_AddMultipleSurveyor_PicAdmin...");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
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
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + "TC498";
		String eula = customerName + ": " + EULASTRING;
		String locationName = customerName + "Loc";
		String surveyorName = locationName + "Sur";
		String surveyorNewName = locationName + "SurNew";
		String cityName ="Santa Clara";
		
		Log.info("\nRunning TC498_ManageSurveyors_PicSup...");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula);
		
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName, customerName,cityName);
		
		manageSurveyorPage.open();
		
		Log.info(String.format("Adding new Surveyor: Name-[%s]; Location-[%s]; Customer-[%s]", 
				surveyorName, locationName, customerName)); 
		manageSurveyorPage.addNewSurveyor(surveyorName, locationName, customerName);
		assertTrue(manageSurveyorPage.findExistingSurveyor(customerName, locationName, surveyorName));
		
		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		manageSurveyorPage.open();
		assertFalse(manageSurveyorPage.isAddNewSurveyorBtnPresent());
		
		Log.info(String.format("Find existing Surveyor: Customer-[%s]; Location-[%s]; Surveyor Name-[%s]", 
				customerName, locationName, surveyorName)); 
		assertTrue(manageSurveyorPage.findExistingSurveyor(customerName, locationName, surveyorName));
		
		Log.info(String.format("Editing Surveyor: Location-[%s]; Current Surveyor Name-[%s]; New Surveyor Name-[%s]", 
				locationName, surveyorName, surveyorNewName)); 
		manageSurveyorAdminPage.editExistingSurveyor(locationName, surveyorName, surveyorNewName, false);
		assertTrue(manageSurveyorPage.findExistingSurveyor(customerName, locationName, surveyorNewName));
	}
}