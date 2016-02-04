/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.NOMATCHINGSEARCH;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_100;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_25;
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING_50;
import static surveyor.scommon.source.SurveyorConstants.SQACUS;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.STRFEEDBACK;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.UserFeedbackAdminPage;

import common.source.BaseHelper;
import common.source.Log;

/**
 * @author zlu
 *
 */
@RunWith(SurveyorTestRunner.class)
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
		Log.info("\nRunning TC454 - Test Description: Customer Admin can view the feedback notes sent by that customer's user");

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
	
	/**
	 * Test Case ID: TC450_ViewUserFeedbackAdminPagination Test Description:
	 * Pagination (View User Feedback Customer Admin) Test Script: 10,25,50 and
	 * 100 records selection on all Customer Administration screens Expected
	 * Results: Specified number of records will be listed in the table Future
	 */
	@Test
	public void TC450_ViewUserFeedbackAdminPagination() {
		List<String> locationList;
		Log.info("\nRunning - TC450_ViewUserFeedbackAdminPagination - Test Description: Pagination (View User Feedback Customer Admin)\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		userFeedbackAdminPage.open();
		userFeedbackAdminPage.setPagination(PAGINATIONSETTING);

		locationList = userFeedbackAdminPage.getNotesList(false,
				Integer.valueOf(PAGINATIONSETTING));

		assertTrue(locationList.size() <= Integer.valueOf(PAGINATIONSETTING));
		assertTrue(userFeedbackAdminPage.getListSize(locationList));

		userFeedbackAdminPage.open();
		userFeedbackAdminPage.setPagination(PAGINATIONSETTING_25);

		locationList = userFeedbackAdminPage.getNotesList(false,
				Integer.valueOf(PAGINATIONSETTING_25));

		assertTrue(locationList.size() <= Integer.valueOf(PAGINATIONSETTING_25));
		assertTrue(userFeedbackAdminPage.getListSize(locationList));

		userFeedbackAdminPage.open();
		userFeedbackAdminPage.setPagination(PAGINATIONSETTING_50);

		locationList = userFeedbackAdminPage.getNotesList(false,
				Integer.valueOf(PAGINATIONSETTING_50));

		assertTrue(locationList.size() <= Integer.valueOf(PAGINATIONSETTING_50));
		assertTrue(userFeedbackAdminPage.getListSize(locationList));

		userFeedbackAdminPage.open();
		userFeedbackAdminPage.setPagination(PAGINATIONSETTING_100);

		locationList = userFeedbackAdminPage.getNotesList(false,
				Integer.valueOf(PAGINATIONSETTING_100));

		assertTrue(locationList.size() <= Integer
				.valueOf(PAGINATIONSETTING_100));
		assertTrue(userFeedbackAdminPage.getListSize(locationList));
	}

	/**
	 * Test Case ID: TC451 Test Description: Search valid user feedback note record
	 */
	@Test
	public void TC451_SearchValidUserFeedbackNote() {
		String feedbackNote = "TC451_" + testSetup.getRandomNumber();
		Log.info("\nRunning - TC451 - Test Description: Search valid user feedback note record\n");
		
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
		assertTrue(userFeedbackAdminPage.searchNote(SQACUS, SQACUSSU, feedbackNote));
	}

	/**
	 * Test Case ID: TC452 Test Description: Search invalid user feedback note record
	 */
	@Test
	public void TC452_SearchInvalidUserFeedbackNote() {
		String feedbackNote = "TC452_" + testSetup.getRandomNumber();
		Log.info("\nRunning - TC452 - Test Description: Search invalid user feedback note record\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		userFeedbackAdminPage.open();
		userFeedbackAdminPage.getInputSearch().sendKeys(feedbackNote);
		userFeedbackAdminPage.waitForPageToLoad();

		assertTrue(userFeedbackAdminPage.getLabelNoMatchingSearch()
				.equalsIgnoreCase(NOMATCHINGSEARCH));
	}

	/**
	 * Test Case ID: TC453 Test Description: Sort feedback note records based on
	 * attributes present
	 */
	@Test
	public void TC453_SortUserFeedbackNotesRecords() {
		List<String> list = new ArrayList<String>();
		Log.info("\nRunning - TC453 - Test Description: Sort user feedback notes records based on attributes present\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		userFeedbackAdminPage.open();

		userFeedbackAdminPage.getTheadNotes().click();
		list = userFeedbackAdminPage.getNotesList(false,
				Integer.valueOf(PAGINATIONSETTING_100));
		assertTrue(BaseHelper.isStringListSorted(list));
		userFeedbackAdminPage.getTheadNotes().click();
		list = userFeedbackAdminPage.getNotesList(false,
				Integer.valueOf(PAGINATIONSETTING_100));
		assertTrue(BaseHelper.isStringListSortedDes(list));
	}
}