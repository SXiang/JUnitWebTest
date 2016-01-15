/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.NOMATCHINGSEARCH;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_100;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_25;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_50;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;
import static surveyor.scommon.source.SurveyorConstants.SQACUS;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOCSUR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOCANZ;

import java.util.ArrayList;
import java.util.List;

import common.source.BaseHelper;
import common.source.Log;

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
		String isoValue = "-32.7";
		
		Log.info("\nRunning TC463_AddRefGasBottle_CustUA - Test Description: Add Reference Gas Bottles");
		
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
		
		Log.info("\nRunning TC464_AddRefGasBottleBlankRequiredFields_CustUA - Test Description: add reference gas bottle - "
				+ "blank required fields");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		manageRefGasBottlesAdminPage.open();
		assertFalse(manageRefGasBottlesAdminPage.addNewRefGasBottle(lotNum, isoValue, SQACUS, SQACUSLOC, SQACUSLOCSUR, false));
		
		
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
		
		Log.info("\nRunning TC465_AddRefGasBottleLotNumber50CharLimit_CustUA - Test Description: More than 50 characters not allowed "
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
	
	/**
	 * Test Case ID: TC450_ManageRefGasBottlesAdminPagination Test Description:
	 * Pagination (Manage Ref Gas Bottles Customer Admin) Test Script: 10,25,50
	 * and 100 records selection on all Customer Administration screens Expected
	 * Results: Specified number of records will be listed in the table Future
	 */
	@Test
	public void TC450_ManageRefGasBottlesAdminPagination() {
		List<String> lotNumberList;
		String numTextString;
		String[] strList;
		int lotNum = 0;

		System.out
				.println("\nRunning - TC450_ManageRefGasBottlesAdminPagination - Test Description: Pagination (Manage Ref Gas Bottles Customer Admin)\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		manageRefGasBottlesAdminPage.open();
		manageRefGasBottlesAdminPage.setPagination(PAGINATIONSETTING);

		lotNumberList = manageRefGasBottlesAdminPage.getLotNumberList(false,
				Integer.valueOf(PAGINATIONSETTING));

		assertTrue(lotNumberList.size() <= Integer.valueOf(PAGINATIONSETTING));

		numTextString = manageRefGasBottlesAdminPage.getLabelPageTableInfo()
				.getText().trim();
		strList = numTextString.split(" ");
		lotNum = Integer.parseInt(strList[3]);

		assertTrue(lotNumberList.size() == lotNum);

		manageRefGasBottlesAdminPage.open();
		manageRefGasBottlesAdminPage.setPagination(PAGINATIONSETTING_25);

		lotNumberList = manageRefGasBottlesAdminPage.getLotNumberList(false,
				Integer.valueOf(PAGINATIONSETTING_25));

		assertTrue(lotNumberList.size() <= Integer.valueOf(PAGINATIONSETTING_25));

		numTextString = manageRefGasBottlesAdminPage.getLabelPageTableInfo()
				.getText().trim();
		strList = numTextString.split(" ");
		lotNum = Integer.parseInt(strList[3]);

		assertTrue(lotNumberList.size() == lotNum);

		manageRefGasBottlesAdminPage.open();
		manageRefGasBottlesAdminPage.setPagination(PAGINATIONSETTING_50);

		lotNumberList = manageRefGasBottlesAdminPage.getLotNumberList(false,
				Integer.valueOf(PAGINATIONSETTING_50));

		assertTrue(lotNumberList.size() <= Integer.valueOf(PAGINATIONSETTING_50));

		numTextString = manageRefGasBottlesAdminPage.getLabelPageTableInfo()
				.getText().trim();
		strList = numTextString.split(" ");
		lotNum = Integer.parseInt(strList[3]);

		assertTrue(lotNumberList.size() == lotNum);

		manageRefGasBottlesAdminPage.open();
		manageRefGasBottlesAdminPage.setPagination(PAGINATIONSETTING_100);

		lotNumberList = manageRefGasBottlesAdminPage.getLotNumberList(false,
				Integer.valueOf(PAGINATIONSETTING_100));

		assertTrue(lotNumberList.size() <= Integer
				.valueOf(PAGINATIONSETTING_100));

		numTextString = manageRefGasBottlesAdminPage.getLabelPageTableInfo()
				.getText().trim();
		strList = numTextString.split(" ");
		lotNum = Integer.parseInt(strList[3]);

		assertTrue(lotNumberList.size() == lotNum);
	}

	/**
	 * Test Case ID: TC451 Test Description: Search valid Ref Gas Bottle record
	 */
	@Test
	public void TC451_SearchValidRefGasBottle() {
		String lotNumber = "TC451_" + testSetup.getRandomNumber();
		String isoValue = "-32.7";

		System.out
				.println("\nRunning - TC451 - Test Description: Search ref gas bottle record\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		manageRefGasBottlesAdminPage.open();
		manageRefGasBottlesAdminPage.addNewRefGasBottle(lotNumber, isoValue,
				SQACUS, SQACUSLOC, SQACUSLOCSUR);
		assertTrue(manageRefGasBottlesAdminPage.searchRefGasBottle(SQACUSLOC,
				SQACUSLOCSUR, SQACUSLOCANZ, lotNumber, isoValue));
	}

	/**
	 * Test Case ID: TC452 Test Description: Search invalid Ref Gas Bottle record
	 */
	@Test
	public void TC452_SearchInvalidRefGasBottle() {
		String lotNumber = "Invalid_TC452_" + testSetup.getRandomNumber();

		System.out
				.println("\nRunning - TC452 - Test Description: Search invalid Ref Gas Bottle record\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		manageRefGasBottlesAdminPage.open();
		manageRefGasBottlesAdminPage.waitForPageLoad();
		manageRefGasBottlesAdminPage.getInputSearch().sendKeys(lotNumber);
		manageRefGasBottlesAdminPage.waitForPageToLoad();

		assertTrue(manageRefGasBottlesAdminPage.getLabelNoMatchingSearch()
				.equalsIgnoreCase(NOMATCHINGSEARCH));
	}

	/**
	 * Test Case ID: TC453 Test Description: Sort Ref gas bottle records based on
	 * attributes present
	 */
	@Test
	public void TC453_SortRefGasBottleRecords() {
		List<String> list = new ArrayList<String>();

		System.out
				.println("\nRunning - TC453 - Test Description: Sort Ref Gas Bottle records based on attributes present\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		manageRefGasBottlesAdminPage.open();

		manageRefGasBottlesAdminPage.getTheadLotNumber().click();
		list = manageRefGasBottlesAdminPage.getLocationList(false,
				Integer.valueOf(PAGINATIONSETTING_100));
		assertTrue(BaseHelper.isStringListSorted(list));
		manageRefGasBottlesAdminPage.getTheadLocation().click();
		list = manageRefGasBottlesAdminPage.getLocationList(false,
				Integer.valueOf(PAGINATIONSETTING_100));
		assertTrue(BaseHelper.isStringListSortedDes(list));
	}
}