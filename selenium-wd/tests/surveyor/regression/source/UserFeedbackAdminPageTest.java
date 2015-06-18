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

import surveyor.scommon.source.ManageUsersAdminPage;
import surveyor.scommon.source.SendFeedbackPage;
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
	
	/**
	 * Test Case ID: CUSTADM018
	 * Test Description: Customer Admin can view the feedback notes sent by that customer's user 
	 * Test Script: - Login as specified customer's user (e.g. PGE's user - driver, supervisor)
					- On Home Page, click on Send Feedback
					- Provide feedback text and click Send button
					- Login as specified customer's Administrator (e.g. PGE Admin)
					- On Home Page, click Administration -> View User Feedback
	 * Expected Results: - User friendly message should be displayed. ""Successfully sent Feedback to Picarro Administrator"
						 - Feedback notes, date, customer and user name details are present in the list
	 * Current implementation:   
	 * Current Issue:
     * Future Improvement:
	 */	
	@Test
	public void CUSTADM018() {
		ManageUsersAdminPage manageUsersAdminPage = new ManageUsersAdminPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageUsersAdminPage);
		
		SendFeedbackPage sendFeedbackPage = new SendFeedbackPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver, sendFeedbackPage);
		
		String customerName = SQACUS;
		String userName = customerName + testSetup.getRandomNumber() + "custadm018" + REGBASEUSERNAME;
		String strFeedback = "Feedback sent by: " + userName;
		
		System.out.println("\nRunning - CUSTADM018 - Test Description: Customer Admin can view the feedback notes sent by that customer's user\n");
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		homePage.getLinkCusAdmin().click();
		homePage.getLinkAdminManageUsers().click();
		
		manageUsersAdminPage.addNewUser(userName, USERPASSWORD, CUSUSERROLEDR, TIMEZONEPTUA, true);
		manageUsersAdminPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		
		homePage.getLinkSendFB().click();
		sendFeedbackPage.sendFeedback(userName, strFeedback);
		sendFeedbackPage.logout();
		
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		userFeedbackAdminPage.open();
		assertTrue(userFeedbackAdminPage.getUserFeedbackNote(customerName, userName).equalsIgnoreCase(strFeedback));
	}	
}