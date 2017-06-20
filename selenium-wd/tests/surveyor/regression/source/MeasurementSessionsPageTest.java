/**
 *
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyViewPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.MeasurementSessionsPage.DrivingSurveyButtonType;
import surveyor.scommon.source.MeasurementSessionsPage.UserRoleType;
import static surveyor.scommon.source.SurveyorConstants.*;
import static surveyor.scommon.source.SurveyorP3URLs.*;

/**
 * @author zlu
 *
 */
@RunWith(SurveyorTestRunner.class)
public class MeasurementSessionsPageTest extends SurveyorBaseTest {
	private static MeasurementSessionsPage measurementSessionsPage;
	private static SurveyViewPage surveyViewPage;
	private static HomePage homePage;
	private static LoginPage loginPage;

	private static List<String> strListTagCus = null;
	private static List<String> strListTagPic = null;
	private static List<String> strListTagCusDr = null;
	
	/**
	 * This method is called by the 'main' thread
	 */
	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects(); // ensures TestSetup and TestContext are initialized before Page object creation.
	}

	/**
	 * This method is called by the 'worker' thread
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void beforeTest() throws Exception {
		initializeTestObjects();

		PageObjectFactory pageObjectFactory = new PageObjectFactory();

		homePage = pageObjectFactory.getHomePage();
		PageFactory.initElements(getDriver(), homePage);

		loginPage = pageObjectFactory.getLoginPage();
		PageFactory.initElements(getDriver(), loginPage);

		measurementSessionsPage = pageObjectFactory.getMeasurementSessionsPage();
		PageFactory.initElements(getDriver(), measurementSessionsPage);

		surveyViewPage = pageObjectFactory.getSurveyViewPage();
		PageFactory.initElements(getDriver(), surveyViewPage);

		strListTagCus = new ArrayList<String>();
		strListTagPic = new ArrayList<String>();
		strListTagCusDr = new ArrayList<String>();
		
		strListTagCusDr.add(SQACUSDRTAG2);
		strListTagCus.add(CUSDRVSTDTAG2);
		strListTagCus.add(CUSDRVRRTAG2);
		strListTagCus.add(CUSDRVOPTAG2);
		strListTagPic.add(PICADMNSTDTAG2);
		strListTagPic.add(PICADMNRRTAG2);
		strListTagPic.add(PICADMNOPTAG2);
		strListTagPic.add(PICADMNMANTAG2);
	}

	/**
	 * Test Case ID: TC35_CheckVisibilityDriverRole Test Description: Visibility check of Driving Surveys Measurement Sessions for customer user with Driver role Notes: This test case is dependent on
	 * upload of survey tags. This test case can be fixed post Simulator Integration in Automation framework. Work tracked by US1210
	 *
	 * @throws Exception
	 */
	@Test
	public void TC35_CheckVisibilityDriverRole() throws Exception {
		Log.info("\nRunning TC35_CheckVisibilityDriverRole - Test Description: Visibility check of Driving Surveys Measurement Sessions for customer user with Driver role");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSDR, USERPASSWORD);

		measurementSessionsPage.open();

		assertTrue(measurementSessionsPage.checkVisibilityForDrivingSurveys(SQACUSDR, UserRoleType.Driver, strListTagCus, strListTagPic));

	}

	/**
	 * Test Case ID: TC36_CheckVisibilityCustomerSupervisorRole Test Description: Visibility check of Driving Surveys Measurement Sessions for customer user with Supervisor role Notes: This test case
	 * is dependent on upload of survey tags. This test case can be fixed post Simulator Integration in Automation framework. Work tracked by US1210
	 *
	 * @throws Exception
	 */
	@Test
	public void TC36_CheckVisibilityCustomerSupervisorRole() throws Exception {
		Log.info("\nRunning TC36_CheckVisibilityCustomerSupervisorRole - Test Description: Visibility check of Driving Surveys Measurement Sessions for customer user with Supervisor role");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSSU, USERPASSWORD);

		measurementSessionsPage.open();

		strListTagCus.add(CUSDRVSTDTAG3200);

		assertTrue(measurementSessionsPage.checkVisibilityForDrivingSurveys(SQACUSSU, UserRoleType.Supervisor, strListTagCus, strListTagPic));

		try {
			measurementSessionsPage.actionOnDrivingSurvey(CUSDRVSTDTAG3200, SQACUSDR, SURVEYOR_SQACUSUNIT1, SQACUS20161, DrivingSurveyButtonType.ViewSurvey);
		} catch (Exception e) {
			Log.error(e.toString());
		}
		surveyViewPage.waitForPageLoad();
		assertTrue(surveyViewPage.checkIfAtSurveyViewPage());

		measurementSessionsPage.open();

		assertFalse(measurementSessionsPage.isButtonPresent(DrivingSurveyButtonType.DeleteSurvey));
	}

	/**
	 * Test Case ID: TC37_CheckVisibilityUtilityAdminRole Test Description: Visibility check of Driving Surveys Measurement Sessions for customer user with Utility Administrator role Notes: This test
	 * case is dependent on upload of survey tags. This test case can be fixed post Simulator Integration in Automation framework. Work tracked by US1210
	 *
	 * @throws Exception
	 */
	@Test
	public void TC37_CheckVisibilityUtilityAdminRole() throws Exception {
		Log.info("\nRunning TC37_CheckVisibilityUtilityAdminRole - Test Description: Visibility check of Driving Surveys Measurement Sessions for customer user with Utility Administrator role");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		measurementSessionsPage.open();

		strListTagCus.add(CUSDRVSTDTAG3200);
		
		assertTrue(measurementSessionsPage.checkVisibilityForDrivingSurveys(SQACUSSU, UserRoleType.Supervisor, strListTagCus, strListTagPic));

		try {
			measurementSessionsPage.actionOnDrivingSurvey(CUSDRVSTDTAG3200, SQACUSDR, SURVEYOR_SQACUSUNIT1, SQACUS20161, DrivingSurveyButtonType.ViewSurvey);
		} catch (Exception e) {
			Log.error(e.toString());
		}
		surveyViewPage.waitForPageLoad();
		assertTrue(surveyViewPage.checkIfAtSurveyViewPage());	
	}

	/**
	 * Test Case ID: TC38_CheckVisibilityPicarroAdminRole Test Description: Visibility check of Driving Surveys Measurement Sessions for Picarro user with Administrator role Notes: This test case is
	 * dependent on upload of survey tags. This test case can be fixed post Simulator Integration in Automation framework. Work tracked by US1210
	 *
	 * @throws Exception
	 */
	@Test
	public void TC38_CheckVisibilityPicarroAdminRole() throws Exception {
		Log.info("\nRunning TC38_CheckVisibilityPicarroAdminRole - Test Description: Visibility check of Driving Surveys Measurement Sessions for Picarro user with Administrator role");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICAD, USERPASSWORD);

		measurementSessionsPage.open();

		assertTrue(measurementSessionsPage.checkVisibilityForDrivingSurveys(SQAPICAD, UserRoleType.Admin, strListTagCus, strListTagPic));

	}

	/**
	 * Test Case ID: TC51_CheckRecentDrivingSurveyInDashboard Test Description: Recent Driving Surveys Section present on Dashboard should display latest surveys Test Script: - Login and verify recent
	 * driving surveys section Expected Results: - User should be able to see recent driving surveys Future Improvement: - Visibility check, a customer user should be able to see all surveys generated
	 * by the other customer users with different roles
	 */
	@Test
	public void TC51_CheckRecentDrivingSurveyInDashboard() {
		Log.info("\nRunning - TC51_CheckRecentDrivingSurveyInDashboard - Test Description: Recent Driving Surveys Section present on Dashboard should display latest surveys\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSDR, USERPASSWORD);

		homePage.open();
		assertTrue(homePage.checkIfAtHomePage());

		MeasurementSessionsPage msp = new PageObjectFactory().getMeasurementSessionsPage();
		PageFactory.initElements(getDriver(), msp);

		homePage.getLinkDrivingSurveys().click();
		measurementSessionsPage.waitForPageLoad();

		assertTrue(getDriver().getCurrentUrl().equalsIgnoreCase(getTestSetup().getBaseUrl() + DRIVINGSURVEYS));

		assertTrue(getDriver().getCurrentUrl().equalsIgnoreCase(msp.getStrPageURL()));

		assertTrue(msp.getTagNameList(SQACUSDR).containsAll(strListTagCusDr));
	}

	/**
	 * Test Case ID: TC49_DeleteSurveys Test Description: Delete surveys Test Script: - On Home Page, Dashboard (test script might change once US is implemented) - Delete Standard survey - Delete
	 * Operator survey - Delete S1 survey - Delete Rapid Response survey" Expected Results: - Picarro Admin is able to delete surveys Future Improvement: 1. Delete S1 survey for now and should be the
	 * same for deleting surveys with different types 2. Delete surveys generated by different users with different roles 3. Delete surveys by different login users with different roles Notes: This
	 * test case takes 10+ mins to run on P3SQA environment because of large number of rows in DB. Better approach for automation is to use a clean DB for automation test execution. Use of clean DB in
	 * automation, Work tracked by US1210
	 *
	 * @throws Exception
	 */
	// @Test /* Disabled for now. Refer Notes section*/
	public void TC49_DeleteSurveys() throws Exception {
		String tagName = "dmcs1-pgeua";
		boolean deleteAll = false;
		List<String> tagList;

		Log.info("\nRunning - TC49_DeleteSurveys - Delete surveys\n");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICAD, USERPASSWORD);

		homePage.open();
		assertTrue(homePage.checkIfAtHomePage());

		MeasurementSessionsPage msp = new PageObjectFactory().getMeasurementSessionsPage();
		PageFactory.initElements(getDriver(), msp);

		msp.open();

		assertTrue(msp.actionOnDrivingSurvey(tagName, null, null, null, DrivingSurveyButtonType.DeleteSurvey));

		msp.open();
		tagList = msp.getTagNameList();

		assertTrue(!tagList.contains(tagName));
	}

	/**
	 * Test Case ID: TC125_ExportSurveyPeaksAnalysis Test Description: Export Raw Data Test Script: - On Home Page, click on Driving Surveys - Click on Export Survey - Click on Export Peaks - Click on
	 * Export Analysis Expected Results: - Customer Administrator should be allowed to export raw data and it should be .dat text file Current implementation: Raw Data is available to Picarro
	 * Administrator only now Current Issue: Future Improvement: Notes: This test case takes 10+ mins to run on P3SQA environment because of large number of rows in DB. Better approach for automation
	 * is to use a clean DB for automation test execution. Use of clean DB in automation, Work tracked by US1210
	 *
	 * @throws Exception
	 */
	@Test
	public void TC125_ExportSurveyPeaksAnalysis() throws Exception {
		Log.info("\nRunning - TC125_ExportSurveyPeaksAnalysis - Test Description: Export Raw Data\n");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICAD, USERPASSWORD);

		homePage.getLinkDrivingSurveys().click();

		measurementSessionsPage.actionOnDrivingSurvey(PICADMNSTDTAG2, ADMINISTRATORUSER, SQAPICLOC4SUR, SQAPICLOC4SURANA, DrivingSurveyButtonType.ExportSurvey);
		assertTrue(measurementSessionsPage.validateDatFiles(DRIVINGSURVEYSEXPORTSURVEY, PICADMNSTDTAG2, SQAPICLOC4SURANA, getTestSetup().getDownloadPath(), DRIVINGSURVEYSSTNDMODE,
				PICADMNSTDTAG2_STARTEPOCH_MINUS_EPSILON, PICADMNSTDTAG2_ENDEPOCH_PLUS_EPSILON, true));

		measurementSessionsPage.actionOnDrivingSurvey(PICADMNSTDTAG2, ADMINISTRATORUSER, SQAPICLOC4SUR, SQAPICLOC4SURANA, DrivingSurveyButtonType.ExportPeaks);
		assertTrue(measurementSessionsPage.validateDatFiles(DRIVINGSURVEYSEXPORTPEAKS, PICADMNSTDTAG2, SQAPICLOC4SURANA, getTestSetup().getDownloadPath(), DRIVINGSURVEYSSTNDMODE,
				PICADMNSTDTAG2_STARTEPOCH_MINUS_EPSILON, PICADMNSTDTAG2_ENDEPOCH_PLUS_EPSILON, true));

		measurementSessionsPage.actionOnDrivingSurvey(PICADMNSTDTAG2, ADMINISTRATORUSER, SQAPICLOC4SUR, SQAPICLOC4SURANA, DrivingSurveyButtonType.ExportAnalysis);
		assertTrue(measurementSessionsPage.validateDatFiles(DRIVINGSURVEYSEXPORTANALYSIS, PICADMNSTDTAG2, SQAPICLOC4SURANA, getTestSetup().getDownloadPath(), DRIVINGSURVEYSSTNDMODE,
				PICADMNSTDTAG2_STARTEPOCH_MINUS_EPSILON, PICADMNSTDTAG2_ENDEPOCH_PLUS_EPSILON, true));
	}

	/**
	 * Test Case ID: TC144_MeasurementSessions_VerifyPagination: - Verify pagination settings
	 */
	@Test
	public void TC144_MeasurementSessions_VerifyPagination() {
		Log.info("\nRunning Pagination - 10,25,50 and 100 Pagination MeasurementSessions");
		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		measurementSessionsPage.open();
		String paginationSetting25 = "25";
		String paginationSetting50 = "50";
		String paginationSetting100 = "100";

		assertTrue(measurementSessionsPage.checkPaginationSetting(PAGINATIONSETTING));
		assertTrue(!(measurementSessionsPage.getNumberofRecords() > Integer.parseInt(PAGINATIONSETTING)));
		assertTrue(measurementSessionsPage.checkPaginationSetting(paginationSetting25));
		assertTrue(!(measurementSessionsPage.getNumberofRecords() > Integer.parseInt(paginationSetting25)));
		measurementSessionsPage.getNextBtn().click();
		measurementSessionsPage.waitForPageToLoad();
		assertTrue(!(measurementSessionsPage.getNumberofRecords() > Integer.parseInt(paginationSetting25)));
		measurementSessionsPage.getPreviousBtn().click();
		measurementSessionsPage.waitForPageToLoad();
		assertTrue(measurementSessionsPage.checkPaginationSetting(paginationSetting50));
		assertTrue(!(measurementSessionsPage.getNumberofRecords() > Integer.parseInt(paginationSetting50)));
		measurementSessionsPage.getNextBtn().click();
		measurementSessionsPage.waitForPageToLoad();
		assertTrue(!(measurementSessionsPage.getNumberofRecords() > Integer.parseInt(paginationSetting50)));
		measurementSessionsPage.getPreviousBtn().click();
		measurementSessionsPage.waitForPageToLoad();
		assertTrue(measurementSessionsPage.checkPaginationSetting(paginationSetting100));
		assertTrue(!(measurementSessionsPage.getNumberofRecords() > Integer.parseInt(paginationSetting100)));
	}
}