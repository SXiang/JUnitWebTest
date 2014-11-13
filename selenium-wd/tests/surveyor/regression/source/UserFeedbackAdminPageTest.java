/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.UserFeedbackAdminPage;

/**
 * @author zlu
 *
 */
public class UserFeedbackAdminPageTest extends SurveyorBaseTest {
	private static UserFeedbackAdminPage userFeedbackAdminPage;

	@BeforeClass
	public static void setupUserFeedbackAdminPageTest() {
		userFeedbackAdminPage = new UserFeedbackAdminPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  userFeedbackAdminPage);
	}
	
	/**
	 * Test Case ID: UFBAP000A
	 * Test Description: Sending feedback from Picarro Utility Administrator
	 * 
	 */
	@Test
	public void UFBAP000A() {
		String feedbackNote = testSetup.getRandomNumber() + ": " + STRFEEDBACK + " - UFBAP000A";
		boolean bFound = false;
		
		System.out.println("\nRunning UFBAP000A - Test Description: Sending feedback from Picarro Utility Administrator");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICUA, USERPASSWORD);
		
		userFeedbackAdminPage.sendFeedback(SQAPICUA,  feedbackNote);
		
		userFeedbackAdminPage.open();
		List<String> list = userFeedbackAdminPage.getUserFeedbackNotes("Picarro",  SQAPICUA);
		
		for (String note : list) {
			if (note.equalsIgnoreCase(feedbackNote)) {
				bFound = true;
				break;
			}
		}
		
		if (!bFound)
			fail("\nTese case UFBAP000A failed.\n");
	}	

	/**
	 * Test Case ID: UFBAP000B
	 * Test Description: Sending feedback from Customer Utility Administrator
	 * 
	 */
	@Test
	public void UFBAP000B() {
		String feedbackNote = testSetup.getRandomNumber() + ": " + STRFEEDBACK + " - UFBAP000B";
		boolean bFound = false;
		
		System.out.println("\nRunning UFBAP000B - Test Description: Sending feedback from Customer Utility Administrator");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		userFeedbackAdminPage.sendFeedback(SQACUSUA,  feedbackNote);
		
		userFeedbackAdminPage.open();
		List<String> list = userFeedbackAdminPage.getUserFeedbackNotes(SQACUS,  SQACUSUA);
		
		for (String note : list) {
			if (note.equalsIgnoreCase(feedbackNote)) {
				bFound = true;
				break;
			}
		}
		
		if (!bFound)
			fail("\nTese case UFBAP000B failed.\n");
		
		userFeedbackAdminPage.logout();
	}	
}