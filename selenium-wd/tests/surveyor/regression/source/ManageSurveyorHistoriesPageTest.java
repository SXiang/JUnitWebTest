/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.SQACUS;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC0SUR;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ManageSurveyorHistoriesPage;
import surveyor.scommon.source.SurveyorBaseTest;

/**
 * @author pmahajan
 * 
 */
public class ManageSurveyorHistoriesPageTest extends SurveyorBaseTest {
	private static ManageSurveyorHistoriesPage manageSurveyorHistoriesPage;

	@BeforeClass
	public static void setupManageRefGasBottlesPageTest() {
		manageSurveyorHistoriesPage = new ManageSurveyorHistoriesPage(driver,
				baseURL, testSetup);
		PageFactory.initElements(driver, manageSurveyorHistoriesPage);
	}

	/**
	 * Test Case ID: ADM019 Test Description: Add Surveyor History Note
	 * 
	 */
	@Test
	public void ADM019() {
		String strNote = "ADM019 Automation Note "
				+ testSetup.getRandomNumber();
		String surveyorUnit = SQACUS + " - " + SQACUSLOC + "0" + " - "
				+ SQACUSLOC0SUR;
		System.out.println(surveyorUnit);

		System.out
				.format("\nRunning ADM019 Test Description: Add Surveyor History Note as Administrator\n");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(),
				testSetup.getLoginPwd());

		manageSurveyorHistoriesPage.open();
		manageSurveyorHistoriesPage.addNewHistoryNote(surveyorUnit, strNote);
		assertTrue("Administrator not able to add new history note!",
				manageSurveyorHistoriesPage.findExistingHistoryNote(SQACUS,
						SQACUSLOC + "0", SQACUSLOC0SUR, strNote));
	}
}