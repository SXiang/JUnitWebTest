/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.SQACUS;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOCSUR;
import static surveyor.scommon.source.SurveyorConstants.SQAPICSUP;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;
import common.source.CryptoUtility;
import common.source.Log;

import surveyor.dataprovider.UserDataProvider;
import surveyor.scommon.source.ManageSurveyorHistoriesPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;

/**
 * @author pmahajan
 * 
 */
@RunWith(SurveyorTestRunner.class)
public class ManageSurveyorHistoriesPageTest extends SurveyorBaseTest {
	private static ManageSurveyorHistoriesPage manageSurveyorHistoriesPage;

	@BeforeClass
	public static void setupManageRefGasBottlesPageTest() {
		manageSurveyorHistoriesPage = new ManageSurveyorHistoriesPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageSurveyorHistoriesPage);
	}

	/**
	 * Test Case ID: TC76 Test Description: Add Surveyor History Note
	 * 
	 */
	@Test
	public void TC76_AddSurveyorHistoryNote_PicarroAdmin() {
		String strNote = "TC76 Automation Note " + testSetup.getRandomNumber();
		String surveyorUnit = SQACUS + " - " + SQACUSLOC + " - " + SQACUSLOCSUR;

		System.out.format("\nRunning TC76 Test Description: Add Surveyor History Note as Picarro Administrator\n");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		manageSurveyorHistoriesPage.open();
		manageSurveyorHistoriesPage.addNewHistoryNote(surveyorUnit, strNote);
		assertTrue("Administrator not able to add new history note!", manageSurveyorHistoriesPage.findExistingHistoryNote(SQACUS, SQACUSLOC, SQACUSLOCSUR, strNote));
	}

	/**
	 * Test Case ID: TC1249 Test Description: Add Surveyor History Note
	 * 
	 */
	@Test
	public void TC1249_AddSurveyorHistoryNote_PicarroSupport() {
		String strNote = "TC1249 Automation Note " + testSetup.getRandomNumber();
		String surveyorUnit = SQACUS + " - " + SQACUSLOC + " - " + SQACUSLOCSUR;

		System.out.format("\nRunning TC1249 Test Description: Add Surveyor History Note as Picarro Support user\n");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);

		manageSurveyorHistoriesPage.open();
		manageSurveyorHistoriesPage.addNewHistoryNote(surveyorUnit, strNote);
		assertTrue("Picarro Support user not able to add new history note!", manageSurveyorHistoriesPage.findExistingHistoryNote(SQACUS, SQACUSLOC, SQACUSLOCSUR, strNote));
	}

	/**
	 * Test Case ID: TC134_TC1254_AddNoteMaxCharLimit More than 1500 characters not allowed in Note field present on Manage Add Surveyor History screen
	 * 
	 */
	@Test
	@UseDataProvider(value = UserDataProvider.USER_ADMIN_SUPPORT_PROVIDER, location = UserDataProvider.class)
	public void TC134_TC1254_AddNoteMaxCharLimit(String user, String pwd) {
		String str1467chars = "AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-AbcdefghI-Abcdefgh";
		String str1468chars = str1467chars + "A";
		String str1469chars = str1468chars + "A";
		String strNote, strNote1500chars, strNote1501chars;
		String surveyorUnit = SQACUS + " - " + SQACUSLOC + " - " + SQACUSLOCSUR;

		String tcID;
		if (user.equalsIgnoreCase("administrator")) {
			tcID = "TC134";
			strNote = tcID + " Automation Note " + testSetup.getFixedSizeRandomNumber(9);
			strNote1500chars = strNote + str1468chars;
			strNote1501chars = strNote + str1469chars;
		} else {
			tcID = "TC1254";
			strNote = tcID + " Automation Note " + testSetup.getFixedSizeRandomNumber(9);
			strNote1500chars = strNote + str1467chars;
			strNote1501chars = strNote + str1468chars;
		}
		String password = CryptoUtility.decrypt(pwd);

		Log.info("\nRunning - " + tcID + "_AddNoteMaxCharLimit - Test Description: More than 1500 characters not allowed in Note field present on Manage Add Surveyor History screen\n");

		loginPage.open();
		loginPage.loginNormalAs(user, password);

		manageSurveyorHistoriesPage.open();
		manageSurveyorHistoriesPage.addNewHistoryNote(surveyorUnit, strNote1500chars);
		assertTrue("Administrator not able to add new history note!", manageSurveyorHistoriesPage.findExistingHistoryNote(SQACUS, SQACUSLOC, SQACUSLOCSUR, strNote1500chars));

		manageSurveyorHistoriesPage.addNewHistoryNote(surveyorUnit, strNote1501chars);
		assertFalse("Administrator was able to add new history note!", manageSurveyorHistoriesPage.findExistingHistoryNote(SQACUS, SQACUSLOC, SQACUSLOCSUR, strNote1501chars));
	}

	/**
	 * Test Case ID: TC1249 Test Description: Add Surveyor History Note
	 * 
	 */
	@Test
	public void TC501_ControlVisibility_PicarroSupport() {
		Log.info("\nRunning TC501_ControlVisibility_PicarroSupport\n");
		String strNote = "TC501 Automation Note " + testSetup.getRandomNumber();
		String surveyorUnit = SQACUS + " - " + SQACUSLOC + " - " + SQACUSLOCSUR;
		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		manageSurveyorHistoriesPage.open();
		manageSurveyorHistoriesPage.addNewHistoryNote(surveyorUnit, strNote);
		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);
		manageSurveyorHistoriesPage.open();
		assertTrue(manageSurveyorHistoriesPage.getBtnAddNewHistoryEntry().isDisplayed());
		assertTrue("Picarro Support user not able to add new history note!", manageSurveyorHistoriesPage.findExistingHistoryNote(SQACUS, SQACUSLOC, SQACUSLOCSUR, strNote));
	}
}