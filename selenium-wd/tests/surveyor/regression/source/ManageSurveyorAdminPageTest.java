/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

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
	 * Test Case ID: CUSTADM019
	 * Test Description: edit surveyor
	 * Test Script: - On Home Page, click Administration -> Manage Customer's Surveyors
					- Click on Edit link
					- Modify Surveyor details and click OK
	 * Expected Results: - Customer Admin is navigated to Manage Customer's Surveyors page and modified Surveyor details are present in the table
	 * Current implementation:   
	 * Current Issue:
     * Future Improvement: editing a surveyor to have it associate to a different customer location is covered in CUSTADM020
	 */	
	@Test
	public void CUSTADM019() {
		//String customerName = SQACUS;
		String locationName = SQACUSLOC;
		String surveyorName = SQACUSLOCSUR + testSetup.getRandomNumber() + "custadm019";
		String surveyorNameNew = surveyorName + "New";
		
		System.out.println("\nRunning - CUSTADM019 - Test Description: edit surveyor\n");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageSurveyorPage.open();
		manageSurveyorPage.addNewSurveyor(surveyorName, SQACUS + " - " + SQACUSLOC);
		manageSurveyorPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);		
		
		homePage.getLinkCusAdmin().click();
		homePage.getLinkManageSurveyors().click();
		
		manageSurveyorAdminPage.editExistingSurveyor(locationName, surveyorName, surveyorNameNew);
		
		assertTrue(manageSurveyorAdminPage.findExistingSurveyor(locationName, surveyorNameNew));
	}
	
	/**
	 * Test Case ID: CUSTADM020
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
	public void CUSTADM020() {
		String locationName1 = SQACUSLOC;
		String locationName2 = SQACUSLOC + testSetup.getRandomNumber();
		String locationName3 = SQAPICLOC;
		String surveyorName = SQACUSLOCSUR + testSetup.getRandomNumber() + "custadm020";
		
		System.out.println("\nRunning - CUSTADM020 - Test Description: Administrator is allowed to associate and disassociate Surveyor Units within Locations associated only to his customer\n");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageSurveyorPage.open();
		manageSurveyorPage.addNewSurveyor(surveyorName, SQACUS + " - " + locationName1);
		manageSurveyorPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		manageLocationsAdminPage.open();
		manageLocationsAdminPage.addNewLocation(locationName2);
		
		homePage.getLinkCusAdmin().click();
		homePage.getLinkManageSurveyors().click();
		
		manageSurveyorAdminPage.editExistingSurveyor(locationName1, surveyorName, locationName2, surveyorName);
		
		assertTrue(manageSurveyorAdminPage.findExistingSurveyor(locationName2, surveyorName));
		assertFalse(manageSurveyorAdminPage.findExistingSurveyor(locationName1, surveyorName));
		assertFalse(manageSurveyorAdminPage.editExistingSurveyor(locationName1, surveyorName, locationName3, surveyorName));
	}
	
	/**
	 * Test Case ID: CUSTADM021
	 * Test Description: More than 50 characters not allowed in Surveyor Description field
	 * Test Script: - On Home Page, and click Administration -> Manage Surveyors
					- Click on 'Edit' button
					- Provide more than 50 characters in Surveyor Description field and click OK
	 * Expected Results: User cannot enter more than 50 characters and message having limit of characters displayed
	 * Current implementation:   
	 * Current Issue: DE461, Error msg improvement: customer Utility Admin rename a surveyor to an existing name in the same location
     * Future Improvement:
	 */	
	@Test
	public void CUSTADM021() {
		String str34chars = "AbcdefghI-AbcdefghI-AbcdefghI-Abcd";
		String str35chars = "AbcdefghI-AbcdefghI-AbcdefghI-Abcde";
		
		String surveyorName50Chars = testSetup.getRandomNumber() + "custadm021" + str34chars;
		String surveyorName51Chars = testSetup.getRandomNumber() + "custadm021" + str35chars;
		
		System.out.println("\nRunning - CUSTADM021 - Test Description: More than 50 characters not allowed in Surveyor Description field\n");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageSurveyorPage.open();
		manageSurveyorPage.addNewSurveyor(surveyorName51Chars, SQACUSLOC, SQACUS);
		manageSurveyorPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		manageSurveyorAdminPage.open();
		manageSurveyorAdminPage.editExistingSurveyor(SQACUSLOC, surveyorName50Chars, surveyorName51Chars);
		
		assertTrue(manageSurveyorAdminPage.findExistingSurveyor(SQACUSLOC, surveyorName50Chars));
	}
	
	/**
	 * Test Case ID: CUSTADM022
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
	public void CUSTADM022() {
		String surveyorName = testSetup.getRandomNumber() + "custadm022";
		
		System.out.println("\nRunning - CUSTADM022 - Test Description: edit surveyor - blank required fields\n");
		
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