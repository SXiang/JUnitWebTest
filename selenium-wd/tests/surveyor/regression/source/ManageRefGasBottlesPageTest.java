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
    
    /**
	 * Test Case ID: TC465_AddRefGasBottleLotNumber50CharLimit_PicarroAdminSupport
	 * Test Description:  	More than 50 characters not allowed in Lot Number field present on Add Reference Gas Bottle screens
	 * Test Script: - On Home Page, click Administration -> Manage Reference Gas Bottles -> Add New Reference Gas Bottle
	 * Expected Results: User cannot enter more than 50 characters
	 * Current implementation:   
	 * Current Issue:
     * Future Improvement:
	 */	
	@Test
	@UseDataProvider(value = "dataProviderPicarroAdminSupportRoleInfo", location = UserDataProvider.class)
	@RunAs(users=SQAPICAD_AND_SQAPICSUP)
	public void TC137_TC1253_AddRefGasBottleLotNumber50CharLimit_PicarroAdminSupport(String user, String pwd) {
		String str33chars = "AbcdefghI-AbcdefghI-AbcdefghI-Abc";
		String str34chars = "AbcdefghI-AbcdefghI-AbcdefghI-Abcd";
		String str35chars = "AbcdefghI-AbcdefghI-AbcdefghI-Abcde";
		String isoValue = "-32";
		String tcID, lotNum50Chars, lotNum51Chars;
		
		if (user.equalsIgnoreCase("administrator")) {
			tcID = "TC137";
			lotNum50Chars = testSetup.getFixedSizeRandomNumber(11) + tcID + str34chars;
			lotNum51Chars = testSetup.getFixedSizeRandomNumber(11) + tcID + str35chars;
		} else {
			tcID = "TC1253";
			lotNum50Chars = testSetup.getFixedSizeRandomNumber(11) + tcID + str33chars;
			lotNum51Chars = testSetup.getFixedSizeRandomNumber(11) + tcID + str34chars;
		}
		String password = CryptoUtility.decrypt(pwd);
		
		Log.info("\nRunning "
				+ tcID
				+ "_AddRefGasBottleLotNumber50CharLimit_PicarroAdminSupport - Test Description: More than 50 characters not allowed "
				+ "in Lot Number field present on Add Reference Gas Bottle screens");

		loginPage.open();
		loginPage.loginNormalAs(user, password);
		
		manageRefGasBottlesPage.open();
		manageRefGasBottlesPage.addNewRefGasBottle(lotNum50Chars, isoValue, SQACUS, SQACUSLOC, SQACUSLOCSUR, true);
		manageRefGasBottlesPage.addNewRefGasBottle(lotNum51Chars, isoValue, SQACUS, SQACUSLOC, SQACUSLOCSUR, true);
		
		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(lotNum50Chars, SQACUSLOCSUR));
		manageRefGasBottlesPage.open();
		assertFalse(manageRefGasBottlesPage.findExistingRefGasBottle(lotNum51Chars, SQACUSLOCSUR));
	}
	
	/**
	 * Test Case ID: TC500_ManageRefGasBottle_PicSup 
	 * Test Description: - On Home Page, click on Administration -> Manage Reference gas bottle 
	 * Expected Result: - User can see list of all reference gas bottles 
	 * - User can see Add New Reference Gas Bottle button
	 * 
	 */
	@Test
	public void TC500_ManageRefGasBottle_PicSup() {
		String strLotNumber1 = testSetup.getFixedSizeRandomNumber(5) + "_PicAdmin_TC500";
		String strLotNumber2 = testSetup.getFixedSizeRandomNumber(5) + "_PicSup_TC500";
		
		Log.info("\nRunning TC500_ManageRefGasBottle_PicSup");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageRefGasBottlesPage.open();
		manageRefGasBottlesPage.addNewRefGasBottle(strLotNumber1, "-32", SQACUS, SQACUSLOC, SQACUSLOCSUR);
		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(strLotNumber1, SQACUSLOCSUR));
		
		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		manageRefGasBottlesPage.open();
		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(strLotNumber1, SQACUSLOCSUR));
		manageRefGasBottlesPage.addNewRefGasBottle(strLotNumber2, "-32", SQACUS, SQACUSLOC, SQACUSLOCSUR);
		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(strLotNumber2, SQACUSLOCSUR));
	}
}