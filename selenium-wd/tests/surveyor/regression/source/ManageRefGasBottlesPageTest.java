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
	public void MRGBP000A() {
		String strItemNumber = "Reg" + RGBNAMEBASE + testSetup.getRandomNumber() + "MRGBP000A";
		
		System.out.println("\nRunning MRGBP000A - Test Description: Adding a Ref Gas Bottle to a customer surveyor by Picarro Default Administrator");
		
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		manageRefGasBottlesPage.open();
		manageRefGasBottlesPage.addNewRefGasBottle(strItemNumber, testSetup.getRandomNumber(), "-32", CUSNAMEBASE, CUSNAMEBASELOC, CUSNAMEBASESUR);
		
		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(strItemNumber, CUSNAMEBASESUR));
	}

	/**
	 * Test Case ID: MRGBP000B
	 * Test Description: Adding a Ref Gas Bottle to a customer surveyor by Picarro User with Administrator Role
	 * 
	 */
	@Test
	public void MRGBP000B() {
		String strItemNumber = "Reg" + RGBNAMEBASE + testSetup.getRandomNumber() + "MRGBP000B";
		
		System.out.println("\nRunning MRGBP000B - Test Description: Adding a Ref Gas Bottle to a customer surveyor by Picarro User with Administrator Role");
		
		loginPage.open();
		loginPage.loginNormalAs(SQAPICAD, USERPASSWORD);
		
		manageRefGasBottlesPage.open();
		manageRefGasBottlesPage.addNewRefGasBottle(strItemNumber, testSetup.getRandomNumber(), "-32", CUSNAMEBASE, CUSNAMEBASELOC, CUSNAMEBASESUR);
		
		assertTrue(manageRefGasBottlesPage.findExistingRefGasBottle(strItemNumber, CUSNAMEBASESUR));
	}	
}