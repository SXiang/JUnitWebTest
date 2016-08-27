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
import static surveyor.scommon.source.SurveyorConstants.SQAPICLOC4SURANA;

import java.util.ArrayList;
import java.util.List;

import common.source.BaseHelper;
import common.source.Log;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ManageRefGasBottlesAdminPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;

/**
 * @author zlu
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ManageRefGasBottlesAdminPageTest extends SurveyorBaseTest {
	private static ManageRefGasBottlesAdminPage manageRefGasBottlesAdminPage;


	
	@BeforeClass
	public static void setupManageRefGasBottlesAdminPageTest () {
		manageRefGasBottlesAdminPage = new ManageRefGasBottlesAdminPage(driver, getTestSetup(), getBaseURL());
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
		String lotNum = getTestSetup().getRandomNumber() + "_TC463";
		String isoValue = "-32.7";
		
		Log.info("\nRunning TC463_AddRefGasBottle_CustUA - Test Description: Add Reference Gas Bottles");
		
		getLoginPage().open();
		getLoginPage().loginNormalAs(SQACUSUA, USERPASSWORD);
		
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
		String ethaneMethaneRatio = "1";
		
		Log.info("\nRunning TC464_AddRefGasBottleBlankRequiredFields_CustUA - Test Description: add reference gas bottle - "
				+ "blank required fields");
		
		getLoginPage().open();
		getLoginPage().loginNormalAs(SQACUSUA, USERPASSWORD);
		
		manageRefGasBottlesAdminPage.open();
		assertFalse(manageRefGasBottlesAdminPage.addNewRefGasBottle(lotNum, isoValue, ethaneMethaneRatio, SQACUS, SQACUSLOC, SQACUSLOCSUR, false));
		
		
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
		
		String lotNum50Chars = getTestSetup().getFixedSizeRandomNumber(11) + "TC465" + str34chars;
		String lotNum51Chars = getTestSetup().getFixedSizeRandomNumber(11) + "TC465" + str35chars;
		
		String isoValue = "-32";
		String ethMthRto = "1";
		
		Log.info("\nRunning TC465_AddRefGasBottleLotNumber50CharLimit_CustUA - Test Description: More than 50 characters not allowed "
				+ "in Lot Number field present on Add Reference Gas Bottle screens");
		
		getLoginPage().open();
		getLoginPage().loginNormalAs(SQACUSUA, USERPASSWORD);
		
		manageRefGasBottlesAdminPage.open();
		manageRefGasBottlesAdminPage.addNewRefGasBottle(lotNum50Chars, isoValue, ethMthRto, SQACUS, SQACUSLOC, SQACUSLOCSUR, true);
		manageRefGasBottlesAdminPage.addNewRefGasBottle(lotNum51Chars, isoValue, ethMthRto, SQACUS, SQACUSLOC, SQACUSLOCSUR, true);
		
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
		Log.info("\nRunning - TC450_ManageRefGasBottlesAdminPagination - Test Description: Pagination (Manage Ref Gas Bottles Customer Admin)\n");

		getLoginPage().open();
		getLoginPage().loginNormalAs(SQACUSUA, USERPASSWORD);
		manageRefGasBottlesAdminPage.open();
		manageRefGasBottlesAdminPage.setPagination(PAGINATIONSETTING);

		lotNumberList = manageRefGasBottlesAdminPage.getLotNumberList(false,
				Integer.valueOf(PAGINATIONSETTING));

		assertTrue(lotNumberList.size() <= Integer.valueOf(PAGINATIONSETTING));
		assertTrue(manageRefGasBottlesAdminPage.getListSize(lotNumberList));

		manageRefGasBottlesAdminPage.open();
		manageRefGasBottlesAdminPage.setPagination(PAGINATIONSETTING_25);

		lotNumberList = manageRefGasBottlesAdminPage.getLotNumberList(false,
				Integer.valueOf(PAGINATIONSETTING_25));

		assertTrue(lotNumberList.size() <= Integer.valueOf(PAGINATIONSETTING_25));
		assertTrue(manageRefGasBottlesAdminPage.getListSize(lotNumberList));

		manageRefGasBottlesAdminPage.open();
		manageRefGasBottlesAdminPage.setPagination(PAGINATIONSETTING_50);

		lotNumberList = manageRefGasBottlesAdminPage.getLotNumberList(false,
				Integer.valueOf(PAGINATIONSETTING_50));

		assertTrue(lotNumberList.size() <= Integer.valueOf(PAGINATIONSETTING_50));
		assertTrue(manageRefGasBottlesAdminPage.getListSize(lotNumberList));

		manageRefGasBottlesAdminPage.open();
		manageRefGasBottlesAdminPage.setPagination(PAGINATIONSETTING_100);

		lotNumberList = manageRefGasBottlesAdminPage.getLotNumberList(false,
				Integer.valueOf(PAGINATIONSETTING_100));

		assertTrue(lotNumberList.size() <= Integer
				.valueOf(PAGINATIONSETTING_100));
		assertTrue(manageRefGasBottlesAdminPage.getListSize(lotNumberList));
	}

	/**
	 * Test Case ID: TC451 Test Description: Search valid Ref Gas Bottle record
	 */
	@Test
	public void TC451_SearchValidRefGasBottle() {
		String lotNumber = "TC451_" + getTestSetup().getRandomNumber();
		String isoValue = "-32.7";
		Log.info("\nRunning - TC451 - Test Description: Search ref gas bottle record\n");

		getLoginPage().open();
		getLoginPage().loginNormalAs(SQACUSUA, USERPASSWORD);

		manageRefGasBottlesAdminPage.open();
		manageRefGasBottlesAdminPage.addNewRefGasBottle(lotNumber, isoValue,
				SQACUS, SQACUSLOC, SQACUSLOCSUR);
		assertTrue(manageRefGasBottlesAdminPage.searchRefGasBottle(SQACUSLOC,
				SQACUSLOCSUR, SQAPICLOC4SURANA, lotNumber, isoValue));
	}

	/**
	 * Test Case ID: TC452 Test Description: Search invalid Ref Gas Bottle record
	 */
	@Test
	public void TC452_SearchInvalidRefGasBottle() {
		String lotNumber = "Invalid_TC452_" + getTestSetup().getRandomNumber();
		Log.info("\nRunning - TC452 - Test Description: Search invalid Ref Gas Bottle record\n");

		getLoginPage().open();
		getLoginPage().loginNormalAs(SQACUSUA, USERPASSWORD);
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
		Log.info("\nRunning - TC453 - Test Description: Sort Ref Gas Bottle records based on attributes present\n");

		getLoginPage().open();
		getLoginPage().loginNormalAs(SQACUSUA, USERPASSWORD);
		manageRefGasBottlesAdminPage.open();
		
		manageRefGasBottlesAdminPage.getTheadSurveyor().click();
		list = manageRefGasBottlesAdminPage.getSurveyorList(false,
				Integer.valueOf(PAGINATIONSETTING_100));
		assertTrue(BaseHelper.isStringListSorted(list));
		manageRefGasBottlesAdminPage.getTheadSurveyor().click();
		list = manageRefGasBottlesAdminPage.getSurveyorList(false,
				Integer.valueOf(PAGINATIONSETTING_100));
		assertTrue(BaseHelper.isStringListSortedDes(list));
		
		manageRefGasBottlesAdminPage.getTheadAnalyzer().click();
		list = manageRefGasBottlesAdminPage.getAnalyzerList(false,
				Integer.valueOf(PAGINATIONSETTING_100));
		assertTrue(BaseHelper.isStringListSorted(list));
		manageRefGasBottlesAdminPage.getTheadAnalyzer().click();
		list = manageRefGasBottlesAdminPage.getAnalyzerList(false,
				Integer.valueOf(PAGINATIONSETTING_100));
		assertTrue(BaseHelper.isStringListSortedDes(list));
		
		manageRefGasBottlesAdminPage.getTheadLotNumber().click();
		list = manageRefGasBottlesAdminPage.getLotNumberList(false,
				Integer.valueOf(PAGINATIONSETTING_100));
		assertTrue(BaseHelper.isStringListSorted(list));
		manageRefGasBottlesAdminPage.getTheadLotNumber().click();
		list = manageRefGasBottlesAdminPage.getLotNumberList(false,
				Integer.valueOf(PAGINATIONSETTING_100));
		assertTrue(BaseHelper.isStringListSortedDes(list));
	}
	
	/**
	 * Test Case ID: TC132_ManageRefGas_SortColumns Script: - Sort records based on attributes present Results: - - User is able to sort the list of records based on specified attribute
	 */
	@Test
	public void TC132_ManageRefGas_SortColumns() {
		Log.info("\nRunning TC132_ManageRefGas_SortColumns");
		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		manageRefGasBottlesAdminPage.open();		
		assertTrue(manageRefGasBottlesAdminPage.areTableColumnsSorted());
	}
}