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
}