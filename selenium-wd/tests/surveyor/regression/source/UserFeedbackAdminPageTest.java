/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.SQACUS;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.STRFEEDBACK;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;

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
		userFeedbackAdminPage = new UserFeedbackAdminPage(driver, testSetup,
				baseURL);
		PageFactory.initElements(driver, userFeedbackAdminPage);
	}

	/**
	 * Test Case ID: TC454 Test Description: Customer Admin can view the
	 * feedback notes sent by that customer's user
	 */
	@Test
	public void TC454_CustSupervisorSendFeedback_CustAdminViewIt() {
		String feedbackNote = testSetup.getRandomNumber() + ": " + STRFEEDBACK
				+ " - TC454";
		boolean bFound = false;

		System.out
				.println("\nRunning TC454 - Test Description: Customer Admin can view the feedback notes sent by that customer's user");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSSU, USERPASSWORD);

		userFeedbackAdminPage.sendFeedback(SQACUSSU, feedbackNote);
		userFeedbackAdminPage.waitForPageLoad();
		assertTrue(SQACUSSU + " not able to send the feedback note!!",
				userFeedbackAdminPage.checkSuccessMsg());
		userFeedbackAdminPage.clickBtnReturnToHomePage();
		homePage.logout();

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		userFeedbackAdminPage.open();
		List<String> picFeedbackList = userFeedbackAdminPage
				.getUserFeedbackNotes(SQACUS, SQACUSSU);

		for (String note : picFeedbackList) {
			if (note.equalsIgnoreCase(feedbackNote)) {
				bFound = true;
				assertTrue("TC454 Failed - Feedback Note not found!!",
						note.equalsIgnoreCase(feedbackNote));
				break;
			}
		}

		if (!bFound)
			fail("\nTese case TC454 failed.\n");
	}
}