/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.CUSNAMEBASE;
import static surveyor.scommon.source.SurveyorConstants.CUSNAMEBASELOC;
import static surveyor.scommon.source.SurveyorConstants.CUSNAMEBASESUR;
import static surveyor.scommon.source.SurveyorConstants.RGBNAMEBASE;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ManageRefGasBottlesAdminPage;
import surveyor.scommon.source.SurveyorBaseTest;

/**
 * @author zlu
 *
 */
public class ManageRefGasBottlesAdminPageTest extends SurveyorBaseTest {
	private ManageRefGasBottlesAdminPage manageRefGasBottlesAdminPage;
	
	public ManageRefGasBottlesAdminPageTest () {
		manageRefGasBottlesAdminPage = new ManageRefGasBottlesAdminPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  manageRefGasBottlesAdminPage);
	}


	/**
	 * Test Case ID: MRGBP000C
	 * Test Description: Adding a Ref Gas Bottle to a customer surveyor by Customer User with Utility Administrator Role
	 * 
	 */
	@Test
	public void MRGBP000C() {
		String strItemNumber = "Reg" + RGBNAMEBASE + testSetup.getRandomNumber() + "MRGBP000C";
		
		System.out.println("\nRunning MRGBP000C - Test Description: Adding a Ref Gas Bottle to a customer surveyor by Customer User with Utility Administrator Role");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		manageRefGasBottlesAdminPage.open();
		manageRefGasBottlesAdminPage.addNewRefGasBottle(strItemNumber, testSetup.getRandomNumber(), "-32", CUSNAMEBASE, CUSNAMEBASELOC, CUSNAMEBASESUR);
		
		assertTrue(manageRefGasBottlesAdminPage.findExistingRefGasBottle(strItemNumber, CUSNAMEBASESUR, CUSNAMEBASELOC));
	}
}
