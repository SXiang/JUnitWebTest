/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.*;

import java.util.List;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.UserFeedbackAdminPage;

/**
 * @author zlu
 *
 */
public class UserFeedbackAdminPageTest extends SurveyorBaseTest {
	private HomePage homePage;
	private UserFeedbackAdminPage userFeedbackAdminPage;
	private LoginPage loginPage;
	
	public UserFeedbackAdminPageTest() {
		homePage = new HomePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, homePage);
		
		userFeedbackAdminPage = new UserFeedbackAdminPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver,  userFeedbackAdminPage);
		
		loginPage = new LoginPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, loginPage);
	}
	
	/**
	 * Test Case ID: UFBAP000A
	 * Test Description: Sending feedback from Picarro Utility Administrator
	 * 
	 */
	@Test
	public void UFBAP000A() {
		System.out.println("\nRunning UFBAP000A - Test Description: Sending feedback from Picarro Utility Administrator");
		String feedbackNote = testSetup.getRandomNumber() + ": " + STRFEEDBACK;
		boolean bFound = false;
		
		homePage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(SQAPICUA, USERPASSWORD);
		
		userFeedbackAdminPage.open();
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
		
		userFeedbackAdminPage.logout();
	}	

	/**
	 * Test Case ID: UFBAP000B
	 * Test Description: Sending feedback from Customer Utility Administrator
	 * 
	 */
	@Test
	public void UFBAP000B() {
		System.out.println("\nRunning UFBAP000B - Test Description: Sending feedback from Customer Utility Administrator");
		String feedbackNote = testSetup.getRandomNumber() + ": " + STRFEEDBACK;
		boolean bFound = false;
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		userFeedbackAdminPage.open();
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
