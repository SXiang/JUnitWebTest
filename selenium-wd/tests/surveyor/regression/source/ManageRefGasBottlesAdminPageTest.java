/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;
import static surveyor.scommon.source.SurveyorConstants.SQACUS;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOCSUR;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ManageRefGasBottlesAdminPage;
import surveyor.scommon.source.SurveyorBaseTest;

/**
 * @author zlu
 *
 */
public class ManageRefGasBottlesAdminPageTest extends SurveyorBaseTest {
	private static ManageRefGasBottlesAdminPage manageRefGasBottlesAdminPage;
	
	@BeforeClass
	public static void setupManageRefGasBottlesAdminPageTest () {
		manageRefGasBottlesAdminPage = new ManageRefGasBottlesAdminPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  manageRefGasBottlesAdminPage);
	}

	/**
	 * Test Case ID: TC463_AddRefGasBottle_CustUA
	 * Test Description: Add Reference Gas Bottles
	 * Test Script: - On Home Page, click Administration -> Manage Reference Gas Bottles -> Add New Reference Gas Bottle
					- Provide required details and click OK
	 * Expected Results: User is navigated to Manager Reference Gas Bottles page and its details are displayed in the table
	 * Current implementation:   
	 * Current Issue:
     * Future Improvement:
	 */	
	@Test
	public void TC463_AddRefGasBottle_CustUA() {
		String lotNum = testSetup.getRandomNumber() + "_TC463";
		String isoValue = "-32";
		
		System.out.println("\nRunning TC463_AddRefGasBottle_CustUA - Test Description: Add Reference Gas Bottles");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		manageRefGasBottlesAdminPage.open();
		manageRefGasBottlesAdminPage.addNewRefGasBottle(lotNum, isoValue, SQACUS, SQACUSLOC, SQACUSLOCSUR);
		assertTrue(manageRefGasBottlesAdminPage.findExistingRefGasBottle(lotNum, SQACUSLOCSUR, SQACUSLOC));
	}
	
	/**
	 * Test Case ID: TC464_AddRefGasBottleBlankRequiredFields_CustUA
	 * Test Description: add reference gas bottle - blank required fields
	 * Test Script: - On Home Page, click Administration -> Manage Reference Gas Bottles -> Add New Reference Gas Bottle
					- Keep required fields blank. Click OK
	 * Expected Results: "Please fill out this field." message should be displayed
	 * Current implementation:   
	 * Current Issue: 
     * Future Improvement: deal with the tooltip text
	 */	
	@Test			
	public void TC464_AddRefGasBottleBlankRequiredFields_CustUA() {
		String lotNum="";
		String isoValue = "";
		
		System.out.println("\nRunning TC464_AddRefGasBottleBlankRequiredFields_CustUA - Test Description: add reference gas bottle - "
				+ "blank required fields");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		manageRefGasBottlesAdminPage.open();
		assertTrue(manageRefGasBottlesAdminPage.addNewRefGasBottle(lotNum, isoValue, SQACUS, SQACUSLOC, SQACUSLOCSUR, false));
		
		
	}
	
	/**
	 * Test Case ID: TC465_AddRefGasBottleLotNumber50CharLimit_CustUA
	 * Test Description:  	More than 50 characters not allowed in Lot Number field present on Add Reference Gas Bottle screens
	 * Test Script: - On Home Page, click Administration -> Manage Reference Gas Bottles -> Add New Reference Gas Bottle
	 * Expected Results: User cannot enter more than ... characters and message having limit of characters displayed
	 * Current implementation:   
	 * Current Issue:
     * Future Improvement:
	 */	
	@Test
	public void TC465_AddRefGasBottleLotNumber50CharLimit_CustUA() {
		String str34chars = "AbcdefghI-AbcdefghI-AbcdefghI-Abcd";
		String str35chars = "AbcdefghI-AbcdefghI-AbcdefghI-Abcde";
		
		String lotNum50Chars = testSetup.getFixedSizeRandomNumber(11) + "TC465" + str34chars;
		String lotNum51Chars = testSetup.getFixedSizeRandomNumber(11) + "TC465" + str35chars;
		
		String isoValue = "-32";
		
		System.out.println("\nRunning TC465_AddRefGasBottleLotNumber50CharLimit_CustUA - Test Description: More than 50 characters not allowed "
				+ "in Lot Number field present on Add Reference Gas Bottle screens");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		manageRefGasBottlesAdminPage.open();
		manageRefGasBottlesAdminPage.addNewRefGasBottle(lotNum50Chars, isoValue, SQACUS, SQACUSLOC, SQACUSLOCSUR, true);
		manageRefGasBottlesAdminPage.addNewRefGasBottle(lotNum51Chars, isoValue, SQACUS, SQACUSLOC, SQACUSLOCSUR, true);
		
		assertTrue(manageRefGasBottlesAdminPage.findExistingRefGasBottle(lotNum50Chars, SQACUSLOCSUR, SQACUSLOC));
		manageRefGasBottlesAdminPage.open();
		assertFalse(manageRefGasBottlesAdminPage.findExistingRefGasBottle(lotNum51Chars, SQACUSLOCSUR, SQACUSLOC));
	}
}