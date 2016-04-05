/**
 * 
 */
package surveyor.regression.source;

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
		manageSurveyorHistoriesPage = new ManageSurveyorHistoriesPage(driver,
				baseURL, testSetup);
		PageFactory.initElements(driver, manageSurveyorHistoriesPage);
	}

	/**
	 * Test Case ID: TC76 Test Description: Add Surveyor History Note
	 * 
	 */
	@Test
	public void TC76_AddSurveyorHistoryNote_PicarroAdmin() {
		String strNote = "TC76 Automation Note "
				+ testSetup.getRandomNumber();
		String surveyorUnit = SQACUS + " - " + SQACUSLOC + " - "
				+ SQACUSLOCSUR;

		System.out
				.format("\nRunning TC76 Test Description: Add Surveyor History Note as Picarro Administrator\n");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(),
				testSetup.getLoginPwd());

		manageSurveyorHistoriesPage.open();
		manageSurveyorHistoriesPage.addNewHistoryNote(surveyorUnit, strNote);
		assertTrue("Administrator not able to add new history note!",
				manageSurveyorHistoriesPage.findExistingHistoryNote(SQACUS,
						SQACUSLOC, SQACUSLOCSUR, strNote));
	}
	
	/**
	 * Test Case ID: TC1249 Test Description: Add Surveyor History Note
	 * 
	 */
	@Test
	public void TC1249_AddSurveyorHistoryNote_PicarroSupport() {
		String strNote = "TC1249 Automation Note "
				+ testSetup.getRandomNumber();
		String surveyorUnit = SQACUS + " - " + SQACUSLOC + " - "
				+ SQACUSLOCSUR;

		System.out
				.format("\nRunning TC1249 Test Description: Add Surveyor History Note as Picarro Support user\n");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);

		manageSurveyorHistoriesPage.open();
		manageSurveyorHistoriesPage.addNewHistoryNote(surveyorUnit, strNote);
		assertTrue("Picarro Support user not able to add new history note!",
				manageSurveyorHistoriesPage.findExistingHistoryNote(SQACUS,
						SQACUSLOC, SQACUSLOCSUR, strNote));
	}
}