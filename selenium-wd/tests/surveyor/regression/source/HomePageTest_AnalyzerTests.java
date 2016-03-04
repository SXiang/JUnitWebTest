/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
import common.source.TestSetup;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.DriverViewPage.CloudCover;
import surveyor.scommon.source.DriverViewPage.SolarRadiation;
import surveyor.scommon.source.DriverViewPage.SurveyTime;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.DriverViewPage.Wind;

/**
 * @author zlu
 *
 */
@RunWith(SurveyorTestRunner.class)
public class HomePageTest_AnalyzerTests extends SurveyorBaseTest {

	private static final String SURVEYOR_DB3 = "Surveyor.db3";
	private static final String REPLAY_DB3_DEFN_FILE = "replay-db3.defn";
	private static final String INSTR_READY_DEFN_FILE = "instr_ready.defn";

	private static MeasurementSessionsPage measurementSessionsPage;
	private static DriverViewPage driverViewPage;

	public HomePageTest_AnalyzerTests() {
		homePage = new HomePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, homePage);

		measurementSessionsPage = new MeasurementSessionsPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver, measurementSessionsPage);

		driverViewPage = new DriverViewPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver, driverViewPage);
	}


	/**
	 * Test Case ID: TC140_SimulatorTest_VerifyAllDrivingSurveysLink_PicAdminRole
	 * Test Description: Sanity check on home page DashBoard/View All Driving Surveys Link with Picarro Admin login
	 */
	@Test
	public void TC140_SimulatorTest_VerifyAllDrivingSurveysLink_PicAdminRole() {
		Log.info("\nTestcase - TC140_SimulatorTest_VerifyAllDrivingSurveysLink_PicAdminRole: Sanity check on home page DashBoard/View All Driving Surveys " + "Link with Picarro Admin login\n");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		// Create a driving survey.
		TestSetup.replayDB3Script(REPLAY_DB3_DEFN_FILE, SURVEYOR_DB3);
		driverViewPage.open();
		driverViewPage.waitForPageLoad();
		driverViewPage.waitForConnectionComplete();
		Log.info("Clicking on MODE button");
		driverViewPage.clickModeButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		String tag = testSetup.getFixedSizePseudoRandomString(10) + "_TC1097";
		driverViewPage.startDrivingSurvey(tag, SurveyTime.Day, SolarRadiation.Overcast, Wind.Calm, CloudCover.LessThan50, SurveyType.Standard);
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		Log.info("Clicking on MODE button");
		driverViewPage.clickModeButton();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		Log.info("Clicking on STOP SURVEY");
		driverViewPage.getStopDrivingSurveyButton().click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		Log.info("Waiting on UI unblock.");
		driverViewPage.waitForUIUnBlock();		
		
		// wait for data upload.
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		// Run the verifications.
		homePage.open();
		assertTrue(homePage.checkDashBoardViewAllDrivingSurveysLink());

		TestSetup.stopAnalyzer();
	}

	/**
	 * Test Case ID: TC141_SimulatorTest_VerifyAllSurveyorsLink_PicAdminRole 
	 * Test Description: Sanity check on home page DashBoard/View All Surveyors Link with Picarro Admin login
	 */
	@Test
	public void TC141_SimulatorTest_VerifyAllSurveyorsLink_PicAdminRole() {
		Log.info("\nTestcase - TC141_SimulatorTest_VerifyAllSurveyorsLink_PicAdminRole: Sanity check on home page DashBoard/View All Surveyors Link with Picarro Admin login\n");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		// Start Driving Survey to get the Surveyor online.
		TestSetup.replayDB3Script(INSTR_READY_DEFN_FILE);
		driverViewPage.open();
		driverViewPage.waitForPageLoad();
		driverViewPage.waitForConnectionComplete();
		
		// Keep the replay running for a few seconds.
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		
		// Run the verifications.
		homePage.open();
		assertTrue(homePage.checkDashBoardViewAllSurveyorsLink());
		
		TestSetup.stopAnalyzer();
	}
}