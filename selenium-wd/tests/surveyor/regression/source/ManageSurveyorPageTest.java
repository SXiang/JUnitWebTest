/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
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
		
}