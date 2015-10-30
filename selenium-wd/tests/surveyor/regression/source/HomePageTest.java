/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.FleetMapPage;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorSystemsPage;
import surveyor.scommon.source.UserFeedbackPage;

import static surveyor.scommon.source.SurveyorP3URLs.*;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
public class HomePageTest extends SurveyorBaseTest {

	private static MeasurementSessionsPage measurementSessionsPage;
	private static UserFeedbackPage userFeedbackPage;
	private static FleetMapPage fleetMapPage;
	private static SurveyorSystemsPage surveyorSystemsPage;

	public HomePageTest() {
		measurementSessionsPage = new MeasurementSessionsPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver, measurementSessionsPage);

		userFeedbackPage = new UserFeedbackPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver, userFeedbackPage);

		fleetMapPage = new FleetMapPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver, fleetMapPage);
		
		surveyorSystemsPage = new SurveyorSystemsPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver, surveyorSystemsPage);
	}

	/**
	 * Test Case ID: TC44 Test Description: Picarro Surveyors link working Test
	 * Script: - Login to p-cubed - Click on Picarro Surveyors link Expected
	 * Results: - User is navigated to Picarro Surveyors page
	 */
	@Test
	public void TC44() {
		System.out.println("\nRunning - TC44 - Test Description: Picarro Surveyors link working\n");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		homePage.open();

		homePage.getLinkSurveyors().click();
		surveyorSystemsPage.waitForPageLoad();
		
		assertTrue(driver.getCurrentUrl().equalsIgnoreCase(testSetup.getBaseUrl() + SURVEYORS));
		assertTrue(homePage.getSubTitleSurveyors().isDisplayed()
				&& homePage.getSubTitleSurveyors().getText().trim().equalsIgnoreCase("Surveyors"));
	}

	/**
	 * Test Case ID: TC45 Test Description: Picarro Administrator link working
	 * Test Script: - Login to p-cubed - Click on Picarro Administrator link
	 * Expected Results: - Administrator Menu is displayed
	 */
	@Test
	public void TC45() {
		System.out.println("\nRunning - TC45 - Test Description: Picarro Administrator link working\n");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		homePage.open();

		homePage.getLinkPicarroAdmin().click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		assertTrue(homePage.getLinkPicAdminManageCus().isDisplayed());
	}

	/**
	 * Test Case ID: TC46 Test Description: Send Feedback link working Test
	 * Script: - Login to p-cubed and click on Send Feedback link - Click on
	 * Send button" Expected Results: - User is navigated to Send Feedback page
	 */
	@Test
	public void TC46() {
		System.out.println("\nRunning - TC46 - Test Description: Send Feedback link working\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSDR, USERPASSWORD);

		homePage.open();

		homePage.getLinkSendFB().click();
		userFeedbackPage.waitForPageLoad();

		assertTrue(driver.getCurrentUrl().equalsIgnoreCase(testSetup.getBaseUrl() + SENDFEEDBACK));
	}

	/**
	 * Test Case ID: TC47 Test Description: Reports link working and user is
	 * able to see the report menu Test Script: - Login to the site and click on
	 * Reports link Expected Results: - Report Menu is displayed
	 */
	@Test
	public void TC47() {
		System.out.println(
				"\nRunning - TC47 - Test Description: Reports link working and user is able to see the report menu\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSSU, USERPASSWORD);

		homePage.open();

		homePage.getLinkReports().click();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());

		assertTrue(homePage.getLinkCompliance().isDisplayed());
		assertTrue(homePage.getLinkInvestigation().isDisplayed());
		assertTrue(homePage.getLinkReferenceGas().isDisplayed());
		assertTrue(homePage.getLinkSystemHistory().isDisplayed());
	}

	/**
	 * Test Case ID: TC48 Test Description: Driving Surveys link working Test
	 * Script: - Login to p-cubed and click on Driving Surveys link Expected
	 * Results: - User is navigated to Driving Surveys page
	 */
	@Test
	public void TC48() {
		System.out.println("\nRunning - TC48 - Test Description: Driving Surveys link working\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSDR, USERPASSWORD);

		homePage.open();

		homePage.getLinkDrivingSurveys().click();
		measurementSessionsPage.waitForPageLoad();

		assertTrue(driver.getCurrentUrl().equalsIgnoreCase(testSetup.getBaseUrl() + DRIVINGSURVEYS));
		assertTrue(homePage.getSubTitleDrivingSurveys().isDisplayed()
				&& homePage.getSubTitleDrivingSurveys().getText().trim().equalsIgnoreCase("Driving Surveys"));
	}

	/**
	 * Test Case ID: TC50 Test Description: Fleet Map link working Test Script:
	 * - On Home Page, click Fleet Map Expected Results: - User is navigated to
	 * Fleet Map page
	 */
	@Test
	public void TC50() {
		System.out.println("\nRunning - TC50 - Test Description: Fleet Map link working\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSSU, USERPASSWORD);

		homePage.open();

		homePage.getLinkFleetMap().click();
		fleetMapPage.waitForPageLoad();

		assertTrue(driver.getCurrentUrl().equalsIgnoreCase(testSetup.getBaseUrl() + FLEETMAP));
	}

	/**
	 * Test Case ID: TC140 Test Description: Sanity check on home page
	 * DashBoard/View All Driving Surveys Link with Picarro Admin login
	 */
	@Test
	public void TC140() {
		System.out.println(
				"\nTestcase - TC140: Sanity check on home page DashBoard/View All Driving Surveys Link with Picarro Admin login\n");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		homePage.open();

		assertTrue(homePage.checkDashBoardViewAllDrivingSurveysLink());
	}

	/**
	 * Test Case ID: TC141 Test Description: Sanity check on home page
	 * DashBoard/View All Surveyors Link with Picarro Admin login
	 */
	@Test
	public void TC141() {
		System.out.println(
				"\nTestcase - TC141: Sanity check on home page DashBoard/View All Surveyors Link with Picarro Admin login\n");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		homePage.open();

		assertTrue(homePage.checkDashBoardViewAllSurveyorsLink());
	}
}