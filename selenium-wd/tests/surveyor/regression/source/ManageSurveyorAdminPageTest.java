/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
import surveyor.scommon.source.ManageLocationsAdminPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageSurveyorAdminPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.SurveyorBaseTest;

/**
 * @author zlu
 *
 */
public class ManageSurveyorAdminPageTest extends SurveyorBaseTest {
	private static ManageLocationsPage manageLocationsPage;
	private static ManageLocationsAdminPage manageLocationsAdminPage;
	private static ManageSurveyorPage manageSurveyorPage;
	private static ManageSurveyorAdminPage manageSurveyorAdminPage;
	
	@BeforeClass
	public static void setupManageSurveyorAdminPageTest() {
		manageLocationsPage = new ManageLocationsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageLocationsPage);
		
		manageLocationsAdminPage = new ManageLocationsAdminPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageLocationsAdminPage);		
		
		manageSurveyorPage = new ManageSurveyorPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageSurveyorPage);
		
		manageSurveyorAdminPage = new ManageSurveyorAdminPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageSurveyorAdminPage);
	}	
	
	/**
	 * Test Case ID: TC455_EditSurveyor_CustUA
	 * Test Description: edit surveyor
	 * Test Script: - On Home Page, click Administration -> Manage Customer's Surveyors
					- Click on Edit link
					- Modify Surveyor details and click OK
	 * Expected Results: - Customer Admin is navigated to Manage Customer's Surveyors page and the newly created Surveyor is present in the table
	 * Current implementation:   
	 * Current Issue:
     * Future Improvement: editing a surveyor to have it associate to a different customer location is covered in CUSTADM020
	 */	
	@Test
	public void TC455_EditSurveyor_CustUA() {
		String locationName = SQACUSLOC;
		String surveyorName = SQACUSLOCSUR + testSetup.getRandomNumber() + "TC455";
		String surveyorNameNew = surveyorName + "New";
		
		Log.info("\nRunning - TC455_EditSurveyor_CustUA - Test Description: edit surveyor\n");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageSurveyorPage.open();
		manageSurveyorPage.addNewSurveyor(surveyorName, SQACUS + " - " + SQACUSLOC);
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);		
		
		manageSurveyorAdminPage.open();
		manageSurveyorAdminPage.editExistingSurveyor(locationName, surveyorName, surveyorNameNew);
		
		assertTrue(manageSurveyorAdminPage.findExistingSurveyor(locationName, surveyorNameNew));
	}
	
	/**
	 * Test Case ID: TC456_EditSurveyorAssignLoc_CustUA
	 * Test Description: Administrator is allowed to associate and disassociate Surveyor Units within Locations associated only to his customer
	 * Test Script: Pre-requisite: Location1 and Location2 are associated to same customer of admin
					- On Home Page, click Administration -> Manage Customer's Surveyors
					- Click on Edit link
					- Deassociate SurveyorUnit1 from Location1 and associate it to Location2
	 * Expected Results: - Customer Admin is navigated to Manage Customer's Surveyors page and SurveyorUnit1 will now be associated with Location2
	 * Current implementation:   
	 * Current Issue:
     * Future Improvement:
	 */	
	@Test
	public void TC456_EditSurveyorAssignLoc_CustUA() {
		String locationName1 = SQACUSLOC;
		String locationName2 = SQACUSLOC + testSetup.getRandomNumber();
		String locationName3 = SQAPICLOC;
		String surveyorName = SQACUSLOCSUR + testSetup.getRandomNumber() + "TC456";
		String cityName = "Santa Clara";
		
		Log.info("\nRunning - TC456_EditSurveyorAssignLoc_CustUA - Test Description: Administrator is allowed to associate and "
				+ "disassociate Surveyor Units within Locations associated only to his customer\n");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		// Add Surveyor with Location1
		manageSurveyorPage.open();
		manageSurveyorPage.addNewSurveyor(surveyorName, locationName1, SQACUS);

		// Add Location2 for the Customer.
		manageLocationsPage.open();
		manageLocationsPage.addNewLocation(locationName2, SQACUS, cityName);

		// Login as Customer Utility Admin.
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		// Edit Surveyor and assign to Location2.
		manageSurveyorAdminPage.open();
		manageSurveyorAdminPage.editExistingSurveyor(locationName1, surveyorName, locationName2, surveyorName);
		
		// Verify Surveyor is found in Location2.
		assertTrue(manageSurveyorAdminPage.findExistingSurveyor(locationName2, surveyorName));
		// Verify Surveyor is NOT found in Location1.
		assertFalse(manageSurveyorAdminPage.findExistingSurveyor(locationName1, surveyorName));
		// Verify Surveyor cannot be edited with Location1.
		assertFalse(manageSurveyorAdminPage.editExistingSurveyor(locationName1, surveyorName, locationName3, surveyorName));
	}
	
	/**
	 * Test Case ID: TC457_EditSurveyorDesc50CharLimit_CustUA
	 * Test Description: More than 400 characters not allowed in Surveyor Description field
	 * Test Script: - On Home Page, and click Administration -> Manage Surveyors
					- Click on 'Edit' button
					- Provide more than 400 characters in Surveyor Description field and click OK
	 * Expected Results: User cannot enter more than 400 characters and message having limit of characters displayed
	 * Current implementation:   
     * Future Improvement:
	 */	
	@Test
	public void TC457_EditSurveyorDesc400CharLimit_CustUA() {
		String str14chars = "AbcdefghI-Abcd";
		String str15chars = "AbcdefghI-Abcde";
		
		String surveyorName400Chars = testSetup.getFixedSizePseudoRandomString(381) + "TC457" + str14chars;
		String surveyorName401Chars = testSetup.getFixedSizePseudoRandomString(381) + "TC457" + str15chars;
		
		Log.info("\nRunning - TC457_EditSurveyorDesc50CharLimit_CustUA - Test Description: More than 400 characters not allowed "
				+ "in Surveyor Description field\n");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageSurveyorPage.open();
		manageSurveyorPage.addNewSurveyor(surveyorName400Chars, SQACUSLOC, SQACUS);
		manageSurveyorPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		Log.info("surveyorName400Chars=" + surveyorName400Chars);
		Log.info("surveyorName401Chars=" + surveyorName401Chars);
		Log.info("Starting to edit the surveyor...");

		manageSurveyorAdminPage.open();
		manageSurveyorAdminPage.editExistingSurveyor(SQACUSLOC, surveyorName400Chars, surveyorName401Chars);
		
		String allowedSurveyorName = surveyorName401Chars.substring(0,400);
		Log.info("allowedSurveyorName=" + allowedSurveyorName);

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		manageSurveyorAdminPage.open();
		assertTrue(manageSurveyorAdminPage.findExistingSurveyor(SQACUSLOC, allowedSurveyorName));
	}
	
	/**
	 * Test Case ID: TC458_EditSurveyorBlankRequiredFields_CustUA
	 * Test Description: edit surveyor - blank required fields
	 * Test Script: - On Home Page, click Administration -> Manage Customer's Surveyors
					- Click on Edit link
					- Delete description field data. Click OK
	 * Expected Results: "Please fill out this field." message should be displayed
	 * Current implementation:   
	 * Current Issue:
     * Future Improvement: deal with the tooltip text
	 */	
	@Test
	public void TC458_EditSurveyorBlankRequiredFields_CustUA() {
		String surveyorName = testSetup.getRandomNumber() + "TC458";
		
		Log.info("\nRunning - TC458_EditSurveyorBlankRequiredFields_CustUA - Test Description: edit surveyor - blank required fields\n");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageSurveyorPage.open();
		manageSurveyorPage.addNewSurveyor(surveyorName, SQACUSLOC, SQACUS);
		manageSurveyorPage.logout();		
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		manageSurveyorAdminPage.open();
		assertFalse(manageSurveyorAdminPage.editExistingSurveyor(SQACUSLOC, surveyorName, ""));
	}
}