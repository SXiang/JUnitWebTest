/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageRefGasBottlesAdminPage;
import surveyor.scommon.source.ManageRefGasBottlesPage;
import surveyor.scommon.source.SurveyorBaseTest;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class ManageRefGasBottlesPageTest extends SurveyorBaseTest {
	
	/**
	 * Test Case ID: MRGBP000A
	 * Test Description: Adding a Ref Gas Bottle to a customer surveyor by Picarro Default Administrator
	 * 
	 */
	@Test
	public void MRGBP000A() {
		System.out.println("\nRunning MRGBP000A - Test Description: Adding a Ref Gas Bottle to a customer surveyor by Picarro Default Administrator");
		
		LoginPage lpg = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  lpg);
		
		lpg.open();
		lpg.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		ManageRefGasBottlesPage mrgbp = new ManageRefGasBottlesPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  mrgbp);
		
		mrgbp.open();
		String strItemNumber = "Reg" + RGBNAMEBASE + testSetup.getRandomNumber();
		mrgbp.addNewRefGasBottle(strItemNumber, testSetup.getRandomNumber(), "-32", CUSNAMEBASE, CUSNAMEBASELOC, CUSNAMEBASESUR);
		
		assertTrue(mrgbp.findExistingRefGasBottle(strItemNumber, CUSNAMEBASESUR));
		
		mrgbp.logout();
	}

	/**
	 * Test Case ID: MRGBP000B
	 * Test Description: Adding a Ref Gas Bottle to a customer surveyor by Picarro User with Administrator Role
	 * 
	 */
	@Test
	public void MRGBP000B() {
		System.out.println("\nRunning MRGBP000B - Test Description: Adding a Ref Gas Bottle to a customer surveyor by Picarro User with Administrator Role");
		
		LoginPage lpg = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  lpg);
		
		lpg.open();
		lpg.loginNormalAs(SQAPICAD, USERPASSWORD);
		
		ManageRefGasBottlesPage mrgbp = new ManageRefGasBottlesPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  mrgbp);
		
		mrgbp.open();
		String strItemNumber = "Reg" + RGBNAMEBASE + testSetup.getRandomNumber();
		mrgbp.addNewRefGasBottle(strItemNumber, testSetup.getRandomNumber(), "-32", CUSNAMEBASE, CUSNAMEBASELOC, CUSNAMEBASESUR);
		
		assertTrue(mrgbp.findExistingRefGasBottle(strItemNumber, CUSNAMEBASESUR));
		
		mrgbp.logout();
	}
	
	/**
	 * Test Case ID: MRGBP000C
	 * Test Description: Adding a Ref Gas Bottle to a customer surveyor by Customer User with Utility Administrator Role
	 * 
	 */
	@Test
	public void MRGBP000C() {
		System.out.println("\nRunning MRGBP000C - Test Description: Adding a Ref Gas Bottle to a customer surveyor by Customer User with Utility Administrator Role");
		
		LoginPage lpg = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  lpg);
		
		lpg.open();
		lpg.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		ManageRefGasBottlesAdminPage mrgbp = new ManageRefGasBottlesAdminPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  mrgbp);
		
		mrgbp.open();
		String strItemNumber = "Reg" + RGBNAMEBASE + testSetup.getRandomNumber();
		mrgbp.addNewRefGasBottle(strItemNumber, testSetup.getRandomNumber(), "-32", CUSNAMEBASE, CUSNAMEBASELOC, CUSNAMEBASESUR);
		
		assertTrue(mrgbp.findExistingRefGasBottle(strItemNumber, CUSNAMEBASESUR, CUSNAMEBASELOC));
		
		mrgbp.logout();
	}	
}