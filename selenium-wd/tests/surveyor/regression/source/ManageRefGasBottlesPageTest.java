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
import surveyor.scommon.source.ManageRefGasBottlesPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;

import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ManageRefGasBottlesPageTest extends SurveyorBaseTest {
	private static ManageRefGasBottlesPage manageRefGasBottlesPage;
	
	@BeforeClass
	public static void setupManageRefGasBottlesPageTest() {
		manageRefGasBottlesPage = new ManageRefGasBottlesPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  manageRefGasBottlesPage);
	}
	
	/**
	 * Test Case ID: TC135_AddRefGasBottle_PicAdmin
	 * Test Description: Adding a Ref Gas Bottle to a customer surveyor by Picarro Default Administrator
	 * 
	 */
	@Test
	public void TC135_AddRefGasBottle_PicAdmin() {
		String strLotNumber = testSetup.getFixedSizeRandomNumber(5) + "TC135";
		
		Log.info("\nRunning TC135_AddRefGasBottle_PicAdmin - Test Description: Adding a Ref Gas Bottle to a customer surveyor by "
				+ "Picarro Default Administrator");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageRefGasBottlesPage.open();
		manageRefGasBottlesPage.addNewRefGasBottle(strLotNumber, "-32", SQACUS, SQACUSLOC, SQACUSLOCSUR);
		
		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(strLotNumber, SQACUSLOCSUR));
	}

	/**
	 * Test Case ID: TC1250_AddRefGasBottle_PicSU
	 * Test Description: Add Reference Gas Bottles as Picarro Support user
	 * 
	 */
	@Test
	public void TC1250_AddRefGasBottle_PicSU() {
		String strLotNumber = testSetup.getRandomNumber() + "TC1250";
		
		Log.info("\nRunning TC1250_AddRefGasBottle_PicSU - Test Description: Add Reference Gas Bottles as Picarro Support user");
		
		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		
		manageRefGasBottlesPage.open();
		manageRefGasBottlesPage.addNewRefGasBottle(strLotNumber, "-32", SQACUS, SQACUSLOC, SQACUSLOCSUR);
		
		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(strLotNumber, SQACUSLOCSUR));
	}	
	
    /**
	 * Test Case ID: TC1251
     * Test Description: Picarro Support user - Add reference gas bottle - blank required fields
	 * Script:
	 *  - Log in as Picarro Support user
     *  - On Home Page, click Administration -> Manage Reference Gas Bottles -> Add New Reference Gas Bottle
     *  - Keep required fields blank. Click OK
     * Results:
     *  - "The field is required." message should be displayed
	 */
    @Test
	public void TC1251_AddLocationBlankFields_PicSupport(){
        
		Log.info("\nRunning TC1251_AddLocationBlankFields_PicSupport - "+
		         "Test Description: Add reference gas bottle - blank required fields");
		
		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		manageRefGasBottlesPage.open();
		manageRefGasBottlesPage.addRefGasBottle("", "", null, SQACUS, SQACUSLOC, SQACUSLOCSUR, false);
		
		assertEquals(BLANKFIELDERROR, manageRefGasBottlesPage.getLotNumberError());
		assertEquals(BLANKFIELDERROR, manageRefGasBottlesPage.getIsotopicValueError());
		
		manageRefGasBottlesPage.clickOnCancelBtn();

	}	   
    
}