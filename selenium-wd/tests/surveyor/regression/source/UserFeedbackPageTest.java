/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.CUSNAMEBASE;
import static surveyor.scommon.source.SurveyorConstants.CUSNAMEBASELOC;
import static surveyor.scommon.source.SurveyorConstants.CUSNAMEBASESUR;
import static surveyor.scommon.source.SurveyorConstants.RGBNAMEBASE;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.HomePage;
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
	private HomePage homePage;
	private UserFeedbackPage userFeedbackPage;
	private LoginPage loginPage;
	
	public UserFeedbackPageTest() {
		homePage = new HomePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, homePage);
		
		userFeedbackPage = new UserFeedbackPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  userFeedbackPage);
		
		loginPage = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, loginPage);
	}
	
	/**
	 * Test Case ID: UFBP000A
	 * Test Description: Sending feedback from Picarro Default Administrator
	 * 
	 */
	@Test
	public void UFBP000A() {
		System.out.println("\nRunning UFBP000A - Test Description: Sending feedback from Picarro Default Administrator");
		String feedbackNote = testSetup.getRandomNumber() + ": " + STRFEEDBACK;
		
		boolean bFound = false;
		
		userFeedbackPage.sendFeedback(testSetup.getLoginUser(),  feedbackNote);
		
		userFeedbackPage.open();
		
		List<String> list = userFeedbackPage.getUserFeedbackNotes("Picarro",  testSetup.getLoginUser());
		
		for (String note : list) {
			if (note.equalsIgnoreCase(feedbackNote)) {
				bFound = true;
				break;
			}
		}
		
		if (!bFound)
			fail("\nTese case UFBP000A failed.\n");
		
		userFeedbackPage.logout();
	}
	
	/**
	 * Test Case ID: UFBP000B
	 * Test Description: Sending feedback from Picarro User with Administrator Role
	 * 
	 */
	@Test
	public void UFBP000B() {
		System.out.println("\nRunning UFBP000B - Test Description: Sending feedback from Picarro User with Administrator Role");
		String feedbackNote = testSetup.getRandomNumber() + ": " + STRFEEDBACK;
		boolean bFound = false;
		
		loginPage.open();
		loginPage.loginNormalAs(SQAPICAD, USERPASSWORD);
		
		userFeedbackPage.sendFeedback(SQAPICAD, feedbackNote);
		
		userFeedbackPage.open();
		
		List<String> list = userFeedbackPage.getUserFeedbackNotes("Picarro",  SQAPICAD);
		
		for (String note : list) {
			if (note.equalsIgnoreCase(feedbackNote)) {
				bFound = true;
				break;
			}
		}
		
		if (!bFound)
			fail("\nTese case UFBP000B failed.\n");
		
		userFeedbackPage.logout();
	}	
}
