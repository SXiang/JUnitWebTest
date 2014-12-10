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
	
	/**
	 * Test Case ID: CUSTADM029
	 * Test Description: Add Reference Gas Bottles
	 * Test Script: - On Home Page, click Administration -> Manage Reference Gas Bottles -> Add New Reference Gas Bottle
					- Provide required details and click OK
	 * Expected Results: User is navigated to Manager Reference Gas Bottles page and its details are displayed in the table
	 * Current implementation:   
	 * Current Issue:
     * Future Improvement:
	 */	
	@Test
	public void CUSTADM029() {
		String rgbNumber = RGBNAMEBASE + testSetup.getRandomNumber() + "CUSTADM029";
		String lotNum = testSetup.getRandomNumber();
		String isoValue = "-32";
		
		System.out.println("\nRunning CUSTADM029 - Test Description: Add Reference Gas Bottles");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		manageRefGasBottlesAdminPage.open();
		manageRefGasBottlesAdminPage.addNewRefGasBottle(rgbNumber, lotNum, isoValue, SQACUS, SQACUSLOC, SQACUSLOCSUR);
		
		assertTrue(manageRefGasBottlesAdminPage.findExistingRefGasBottle(rgbNumber, SQACUSLOCSUR, SQACUSLOC));
	}
}
