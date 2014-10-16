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
	private UserFeedbackPage userFeedbackPage;
	
	public UserFeedbackPageTest() {
		userFeedbackPage = new UserFeedbackPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  userFeedbackPage);
	}
	
	/**
	 * Test Case ID: UFBP000A
	 * Test Description: Sending feedback from Picarro Default Administrator
	 * 
	 */
	@Test
	public void UFBP000A() {
		String feedbackNote = testSetup.getRandomNumber() + ": " + STRFEEDBACK + " - UFBP000A";
		boolean bFound = false;
		
		System.out.println("\nRunning UFBP000A - Test Description: Sending feedback from Picarro Default Administrator");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());		
		
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
	}
	
	/**
	 * Test Case ID: UFBP000B
	 * Test Description: Sending feedback from Picarro User with Administrator Role
	 * 
	 */
	@Test
	public void UFBP000B() {
		String feedbackNote = testSetup.getRandomNumber() + ": " + STRFEEDBACK + " - UFBP000B";
		boolean bFound = false;
		
		System.out.println("\nRunning UFBP000B - Test Description: Sending feedback from Picarro User with Administrator Role");

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
	}	
}
