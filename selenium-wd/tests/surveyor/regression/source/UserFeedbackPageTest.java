/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.CUSNAMEBASE;
import static surveyor.scommon.source.SurveyorConstants.CUSNAMEBASELOC;
import static surveyor.scommon.source.SurveyorConstants.CUSNAMEBASESUR;
import static surveyor.scommon.source.SurveyorConstants.RGBNAMEBASE;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageRefGasBottlesPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.UserFeedbackPage;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class UserFeedbackPageTest extends SurveyorBaseTest {
	
	/**
	 * Test Case ID: UFBP000A
	 * Test Description: Sending feedback from Picarro Default Administrator
	 * 
	 */
	@Test
	public void UFBP000A() {
		System.out.println("\nRunning UFBP000A - Test Description: Sending feedback from Picarro Default Administrator");
		
		LoginPage lpg = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  lpg);
		
		lpg.open();
		lpg.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		
		UserFeedbackPage ufbp = new UserFeedbackPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  ufbp);
		
		ufbp.sendFeedback(testSetup.getLoginUser(), STRFEEDBACK);
		
		assertTrue(ufbp.checkUserFeedback(testSetup.getLoginUser(),  STRFEEDBACK));
		
		ufbp.logout();
	}
	
	/**
	 * Test Case ID: UFBP000B
	 * Test Description: Sending feedback from Picarro User with Administrator Role
	 * 
	 */
	@Test
	public void UFBP000B() {
		System.out.println("\nRunning UFBP000B - Test Description: Sending feedback from Picarro User with Administrator Role");
		
		LoginPage lpg = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  lpg);
		
		lpg.open();
		lpg.loginNormalAs(SQAPICAD, USERPASSWORD);
		
		UserFeedbackPage ufbp = new UserFeedbackPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  ufbp);
		
		ufbp.sendFeedback(SQAPICAD, STRFEEDBACK);
		
		assertTrue(ufbp.checkUserFeedback(SQAPICAD,  STRFEEDBACK));
		
		ufbp.logout();
	}
	
	/**
	 * Test Case ID: UFBP000C
	 * Test Description: Sending feedback from Customer User with Utility Administrator Role
	 * 
	 */
	@Test
	public void UFBP000C() {
		System.out.println("\nRunning UFBP000C - Test Description: Sending feedback from Customer User with Utility Administrator Role");
		
		LoginPage lpg = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  lpg);
		
		lpg.open();
		lpg.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		UserFeedbackPage ufbp = new UserFeedbackPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  ufbp);
		
		ufbp.sendFeedback(SQACUSUA, STRFEEDBACK);
		
		assertTrue(ufbp.checkUserFeedback(SQACUSUA,  STRFEEDBACK));
		
		ufbp.logout();
	}	
}
