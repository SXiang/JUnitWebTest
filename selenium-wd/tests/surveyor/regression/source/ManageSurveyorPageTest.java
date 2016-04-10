/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.CryptoUtility;
import common.source.Log;
import surveyor.dataprovider.RunAs;
import surveyor.dataprovider.UserDataProvider;
import surveyor.regression.source.ManageLocationsPageTest.ManageLocationTestCaseType;
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
	 * 
	 * @param user
	 * @param pwd
	 */
	@Test
	public void TC101_MaxSurDescCharLimit(String user, String pwd) {
		final int MAX_CHARS = 255;
		String customerName255 = CUSTOMERNAMEPREFIX + testSetup.getFixedSizePseudoRandomString(245) + "TC97";
		String customerName256 = CUSTOMERNAMEPREFIX + testSetup.getFixedSizePseudoRandomString(245) + "TC97" + "A";
		
		String cityName = "Santa Clara";

		String tcID = getTestCaseName(ManageLocationTestCaseType.MaxLocChar, user);
		String password = CryptoUtility.decrypt(pwd);

		String locationName50Chars = testSetup.getFixedSizeRandomNumber(11)
				+ tcID + str34chars;
		String locationName51Chars = testSetup.getFixedSizeRandomNumber(11)
				+ tcID + str35chars;
		String newLocationName50Chars = "New"
				+ testSetup.getFixedSizeRandomNumber(8) + tcID + str34chars;
		String newLocationName51Chars = "New"
				+ testSetup.getFixedSizeRandomNumber(8) + tcID + str35chars;

		Log.info("\nRunning - "
				+ tcID
				+ "_EditLoc50CharLimit - Test Description: More than 50 characters not allowed in Location Description field\n");

		loginPage.open();
		loginPage.loginNormalAs(user, password);

		manageLocationsPage.open();

		manageLocationsPage.addNewLocation(locationName50Chars, SQACUS,
				cityName);
		manageLocationsPage.addNewLocation(locationName51Chars, SQACUS,
				cityName);

		assertTrue(manageLocationsPage.findExistingLocation(SQACUS,
				locationName50Chars));
		manageLocationsPage.open();
		assertFalse(manageLocationsPage.findExistingLocation(SQACUS,
				locationName51Chars));

		manageLocationsPage.editPDExistingLocation(SQACUS, locationName50Chars,
				newLocationName50Chars);
		assertTrue(manageLocationsPage.findExistingLocation(SQACUS,
				newLocationName50Chars));

		manageLocationsPage.editPDExistingLocation(SQACUS, newLocationName50Chars,
				newLocationName51Chars);
		assertFalse(manageLocationsPage.findExistingLocation(SQACUS,
				newLocationName51Chars));
	}
}