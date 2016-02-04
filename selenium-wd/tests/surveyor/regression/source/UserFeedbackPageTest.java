/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.UserFeedbackPage;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
@RunWith(SurveyorTestRunner.class)
public class UserFeedbackPageTest extends SurveyorBaseTest {
	private static UserFeedbackPage userFeedbackPage;

	@BeforeClass
	public static void setupUserFeedbackPageTest() {
		userFeedbackPage = new UserFeedbackPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver, userFeedbackPage);
	}

	/**
	 * Test Case ID: TC124 Test Description: Customer User is able to send the
	 * feedback and Picarro Administrator can view the note
	 * 
	 */
	@Test
	public void TC124_CustAdminSendFeedback_PicarroAdminViewIt() {
		String feedbackNote = testSetup.getRandomNumber() + ": " + STRFEEDBACK
				+ " - TC124";
		boolean bFound = false;

		System.out
				.println("\nRunning TC124 - Test Description: Customer User is able to send the feedback and Picarro Administrator can view the note");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		userFeedbackPage.sendFeedback(SQACUSUA, feedbackNote);
		assertTrue(SQACUSUA + " not able to send the feedback note!!",
				userFeedbackPage.checkSuccessMsg());
		userFeedbackPage.clickBtnReturnToHomePage();
		homePage.logout();

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		userFeedbackPage.open();
		List<String> picFeedbackList = userFeedbackPage.getUserFeedbackNotes(
				SQACUS, SQACUSUA);

		for (String note : picFeedbackList) {
			if (note.equalsIgnoreCase(feedbackNote)) {
				bFound = true;
				assertTrue("TC124 Failed - Feedback Note not found!!",
						note.equalsIgnoreCase(feedbackNote));
				break;
			}
		}

		if (!bFound)
			fail("\nTese case TC124 failed.\n");
	}

	/**
	 * Test Case ID: 508 Test Description: User is able to send the feedback and
	 * Picarro support user can view the note
	 * 
	 */
	@Test
	public void TC508_PicarroSupport_ViewFeedbackNote() {
		String feedbackNote = testSetup.getRandomNumber() + ": " + STRFEEDBACK
				+ " - TC508";
		boolean bFound = false;

		System.out
				.println("\nRunning TC508 - Test Description: User is able to send the feedback and Picarro support user can view the note");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSDR, USERPASSWORD);

		userFeedbackPage.sendFeedback(SQACUSDR, feedbackNote);
		assertTrue(SQACUSDR + " not able to send the feedback note!!",
				userFeedbackPage.checkSuccessMsg());
		userFeedbackPage.clickBtnReturnToHomePage();
		homePage.logout();

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		userFeedbackPage.open();
		List<String> picFeedbackList = userFeedbackPage.getUserFeedbackNotes(
				SQACUS, SQACUSDR);

		for (String note : picFeedbackList) {
			if (note.equalsIgnoreCase(feedbackNote)) {
				bFound = true;
				assertTrue("TC508 Failed - Feedback Note not found!!",
						note.equalsIgnoreCase(feedbackNote));
				break;
			}
		}

		if (!bFound)
			fail("\nTese case TC508 failed.\n");
	}

	/**
	 * Test Case ID: TC1328 Test Description: Picarro Admin is able to send the
	 * feedback and Picarro Support can view the note
	 * 
	 */
	@Test
	public void TC1328_PicAdminSendFeedback_PicSupViewIt() {
		String feedbackNote = testSetup.getRandomNumber() + ": " + STRFEEDBACK
				+ " - TC1328";
		boolean bFound = false;

		System.out
				.println("\nRunning TC1328 - Test Description: Picarro Admin is able to send the feedback and Picarro Support can view the note");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(),
				testSetup.getLoginPwd());

		userFeedbackPage.sendFeedback(testSetup.getLoginUser(), feedbackNote);
		assertTrue(testSetup.getLoginUser()
				+ " not able to send the feedback note!!",
				userFeedbackPage.checkSuccessMsg());
		userFeedbackPage.clickBtnReturnToHomePage();
		homePage.logout();

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		userFeedbackPage.open();
		List<String> picFeedbackList = userFeedbackPage.getUserFeedbackNotes(
				"Picarro", testSetup.getLoginUser());

		for (String note : picFeedbackList) {
			if (note.equalsIgnoreCase(feedbackNote)) {
				bFound = true;
				assertTrue("TC1328 Failed - Feedback Note not found!!",
						note.equalsIgnoreCase(feedbackNote));
				break;
			}
		}

		if (!bFound)
			fail("\nTese case TC1328 failed.\n");
	}

	/**
	 * Test Case ID: TC1329 Test Description: Picarro Support user is able to
	 * send the feedback and Picarro Admin can view the note
	 * 
	 */
	@Test
	public void TC1329_PicSupSendFeedback_PicAdminViewIt() {
		String feedbackNote = testSetup.getRandomNumber() + ": " + STRFEEDBACK
				+ " - TC1329";
		boolean bFound = false;

		System.out
				.println("\nRunning TC1329 - Test Description: Picarro Support user is able to send the feedback and Picarro Admin can view the note");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);

		userFeedbackPage.sendFeedback(testSetup.getLoginUser(), feedbackNote);
		assertTrue(SQAPICSUP + " not able to send the feedback note!!",
				userFeedbackPage.checkSuccessMsg());
		userFeedbackPage.clickBtnReturnToHomePage();
		homePage.logout();

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		userFeedbackPage.open();
		List<String> picFeedbackList = userFeedbackPage.getUserFeedbackNotes(
				"Picarro", SQAPICSUP);

		for (String note : picFeedbackList) {
			if (note.equalsIgnoreCase(feedbackNote)) {
				bFound = true;
				assertTrue("TC1329 Failed - Feedback Note not found!!",
						note.equalsIgnoreCase(feedbackNote));
				break;
			}
		}

		if (!bFound)
			fail("\nTese case TC1329 failed.\n");
	}
}
