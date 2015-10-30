/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ManageRefGasBottlesPage;
import surveyor.scommon.source.SurveyorBaseTest;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class ManageRefGasBottlesPageTest extends SurveyorBaseTest {
	private static ManageRefGasBottlesPage manageRefGasBottlesPage;
	
	@BeforeClass
	public static void setupManageRefGasBottlesPageTest() {
		manageRefGasBottlesPage = new ManageRefGasBottlesPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  manageRefGasBottlesPage);
	}
	
	/**
	 * Test Case ID: MRGBP000A
	 * Test Description: Adding a Ref Gas Bottle to a customer surveyor by Picarro Default Administrator
	 * 
	 */
	@Test
	public void TC135() {
		String strLotNumber = testSetup.getFixedSizeRandomNumber(5) + "TC135";
		
		System.out.println("\nRunning TC135 - Test Description: Adding a Ref Gas Bottle to a customer surveyor by Picarro Default Administrator");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageRefGasBottlesPage.open();
		manageRefGasBottlesPage.addNewRefGasBottle(strLotNumber, "-32", SQACUS, SQACUSLOC, SQACUSLOCSUR);
		
		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(strLotNumber, SQACUSLOCSUR));
	}

	/**
	 * Test Case ID: TC1250
	 * Test Description: Add Reference Gas Bottles as Picarro Support user
	 * 
	 */
	@Test
	public void TC1250() {
		String strLotNumber = testSetup.getRandomNumber() + "TC1250";
		
		System.out.println("\nRunning TC1250 - Test Description: Add Reference Gas Bottles as Picarro Support user");
		
		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		
		manageRefGasBottlesPage.open();
		manageRefGasBottlesPage.addNewRefGasBottle(strLotNumber, "-32", SQACUS, SQACUSLOC, SQACUSLOCSUR);
		
		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(strLotNumber, SQACUSLOCSUR));
	}	
}