/**
 *
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.actions.BaseActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageUsersPageActions;
import surveyor.scommon.actions.PageActionsStore;
import surveyor.scommon.source.FleetMapPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.MeasurementSessionsPage.DrivingSurveyButtonType;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.PreferencesPage;
import surveyor.scommon.source.SurveyViewPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorSystemsPage;
import surveyor.scommon.source.SurveyorTestRunner;
import static surveyor.scommon.source.SurveyorP3URLs.*;
import static surveyor.scommon.source.SurveyorConstants.*;

/**
 * @author zlu
 *
 */
@RunWith(SurveyorTestRunner.class)
public class HomePageTest extends SurveyorBaseTest {

	private static MeasurementSessionsPage measurementSessionsPage;
	private static FleetMapPage fleetMapPage;
	private static SurveyorSystemsPage surveyorSystemsPage;
	private static PreferencesPage preferencesPage;
	private static SurveyViewPage surveyViewPage;
	private static HomePage homePage;
	private static LoginPage loginPage;
	private static ManageUsersPageActions manageUsersPageAction;
	private static LoginPageActions loginPageAction;

	@BeforeClass
	public static void setupHomePageTest() {
		initializeTestObjects(); // ensures TestSetup and TestContext are initialized before Page object creation.
	}

	/**
	 * This method is called by the 'worker' thread
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		Log.info("[THREAD Debug Log] - Calling setup beforeTest()");
		PageActionsStore.INSTANCE.clearStore();

		initializeTestObjects();

		PageObjectFactory pageObjectFactory = new PageObjectFactory();
		measurementSessionsPage = pageObjectFactory.getMeasurementSessionsPage();
		PageFactory.initElements(getDriver(), measurementSessionsPage);

		loginPage = pageObjectFactory.getLoginPage();
		PageFactory.initElements(getDriver(), loginPage);

		homePage = pageObjectFactory.getHomePage();
		PageFactory.initElements(getDriver(), homePage);

		fleetMapPage = pageObjectFactory.getFleetMapPage();
		PageFactory.initElements(getDriver(), fleetMapPage);

		surveyorSystemsPage = pageObjectFactory.getSurveyorSystemsPage();
		PageFactory.initElements(getDriver(), surveyorSystemsPage);

		preferencesPage = pageObjectFactory.getPreferencesPage();
		PageFactory.initElements(getDriver(), preferencesPage);

		surveyViewPage = pageObjectFactory.getSurveyViewPage();
		PageFactory.initElements(getDriver(), surveyViewPage);

		manageUsersPageAction = new ManageUsersPageActions(getDriver(), getBaseURL(), getTestSetup());
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
	}

	/**
	 * Test Case ID: TC1308: Picarro Admin cannot see Manage Release Notes page Script: - Log in as Picarro Admin - Click on Administration Results: Manage Release Notes link is not present
	 */
	@Test(expected = NoSuchElementException.class)
	public void TC1308_ReleaseNotesLinkNotPresent_PicAdminRole() {

		Log.info("\nTC1308_ReleaseNotesLinkNotPresent_PicAdminRole - " + "Test Description: Picarro Admin cannot see Manage Release Notes page");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		homePage.open();

		homePage.getLinkPicarroAdmin().click();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

		homePage.getLinkPicAdminManageReleaseNotes().isDisplayed();
	}

	/**
	 * Test Case ID: TC44_VerifySurveyorLink_PicAdminRole Test Description: Picarro Surveyors link working Test Script: - Login to p-cubed - Click on Picarro Surveyors link Expected Results: - User is
	 * navigated to Picarro Surveyors page
	 */
	@Test
	public void TC44_VerifySurveyorLink_PicAdminRole() {
		Log.info("\nRunning - TC44_VerifySurveyorLink_PicAdminRole - Test Description: Picarro Surveyors link working\n");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		homePage.open();

		homePage.getLinkSurveyors().click();
		surveyorSystemsPage.waitForPageLoad();

		assertTrue(getDriver().getCurrentUrl().equalsIgnoreCase(getTestSetup().getBaseUrl() + SURVEYORS));
		assertTrue(homePage.getSubTitleSurveyors().isDisplayed() && homePage.getSubTitleSurveyors().getText().trim().equalsIgnoreCase(Resources.getResource(ResourceKeys.Constant_Surveyors)));
	}

	/**
	 * Test Case ID: TC45_VerifyAdministratorMenu_PicAdminRole Test Description: Picarro Administrator link working Test Script: - Login to p-cubed - Click on Picarro Administrator link Expected
	 * Results: - Administrator Menu is displayed
	 */
	@Test
	public void TC45_VerifyAdministratorMenu_PicAdminRole() {
		Log.info("\nRunning - TC45_VerifyAdministratorMenu_PicAdminRole - Test Description: Picarro Administrator link working\n");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		homePage.open();
		homePage.waitForPageLoad();

		homePage.getLinkPicarroAdmin().click();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

		assertTrue(homePage.getLinkPicAdminCalibration().isDisplayed());
		assertTrue(homePage.getLinkPicAdminManageCus().isDisplayed());
		assertTrue(homePage.getLinkPicAdminManageUsers().isDisplayed());
		assertTrue(homePage.getLinkPicAdminManageLoc().isDisplayed());
		assertTrue(homePage.getLinkPicAdminManageSur().isDisplayed());
		assertTrue(homePage.getLinkPicAdminManageAnl().isDisplayed());
		assertTrue(homePage.getLinkPicAdminManageRefGasBottles().isDisplayed());
		assertTrue(homePage.getLinkPicAdminManageSurHistories().isDisplayed());
		assertTrue(homePage.getLinkPicAdminViewAnlLogs().isDisplayed());
		assertTrue(homePage.getLinkPicAdminViewSurLogs().isDisplayed());
	}

	/**
	 * Test Case ID: TC47_VerifyReportMenu_PicarroSURole Test Description: Reports link working and user is able to see the report menu Test Script: - Login to the site and click on Reports link
	 * Expected Results: - Report Menu is displayed
	 */
	@Test
	public void TC47_VerifyReportMenu_PicarroSURole() {
		Log.info("\nRunning - TC47_VerifyReportMenu_PicarroSURole - Test Description: Reports link working and user is able to see the report menu\n");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICSU, USERPASSWORD);

		homePage.open();

		homePage.getLinkReports().click();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

		assertTrue(homePage.getLinkCompliance().isDisplayed());
		assertTrue(homePage.getLinkReferenceGas().isDisplayed());
		assertTrue(homePage.getLinkSystemHistory().isDisplayed());
	}

	/**
	 * Test Case ID: TC48_VerifyDrivingSurveysLink_CustDriverRole Test Description: Driving Surveys link working Test Script: - Login to p-cubed and click on Driving Surveys link Expected Results: -
	 * User is navigated to Driving Surveys page
	 */
	@Test
	public void TC48_VerifyDrivingSurveysLink_CustDriverRole() {
		Log.info("\nRunning - TC48_VerifyDrivingSurveysLink_CustDriverRole - Test Description: Driving Surveys link working\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSDR, USERPASSWORD);

		homePage.open();

		homePage.getLinkDrivingSurveys().click();
		measurementSessionsPage.waitForPageLoad();

		assertTrue(getDriver().getCurrentUrl().equalsIgnoreCase(getTestSetup().getBaseUrl() + DRIVINGSURVEYS));
		assertTrue(homePage.getSubTitleDrivingSurveys().isDisplayed() && homePage.getSubTitleDrivingSurveys().getText().trim().equalsIgnoreCase("Driving Surveys"));
	}

	/**
	 * Test Case ID: TC50_VerifyFleetMapLink_CustSURole Test Description: Fleet Map link working Test Script: - On Home Page, click Fleet Map Expected Results: - User is navigated to Fleet Map page
	 */
	@Test
	public void TC50_VerifyFleetMapLink_CustSURole() {
		Log.info("\nRunning - TC50_VerifyFleetMapLink_CustSURole - Test Description: Fleet Map link working\n");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSSU, USERPASSWORD);

		homePage.open();

		homePage.getLinkFleetMap().click();
		fleetMapPage.waitForPageLoad();

		assertTrue(getDriver().getCurrentUrl().equalsIgnoreCase(getTestSetup().getBaseUrl() + FLEETMAP));
	}

	/**
	 * Test Case ID: TC54_VerifyEditUserPreferences Test Description: Modify timezone of user in Preferences
	 * @throws Exception
	 */
	@Test
	public void TC54_VerifyEditUserPreferences() throws Exception {
		Log.info("\nRunning - TC54_VerifyEditUserPreferences Test Description: Modify timezone of user in Preferences\n");

		/* Login as automation admin and create new Picarro admin user. Do NOT alter existing user. */
		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		manageUsersPageAction.open(BaseActions.EMPTY, BaseActions.NOTSET);
		manageUsersPageAction.createNewPicarroUser(BaseActions.EMPTY, 14 /*userRowID*/);

		String usernameColonPassword = String.format("%s:%s", ManageUsersPageActions.workingDataRow.get().username,
				ManageUsersPageActions.workingDataRow.get().password);
		loginPageAction.open(BaseActions.EMPTY, BaseActions.NOTSET);
		loginPageAction.login(usernameColonPassword, BaseActions.NOTSET);   /* login using newly created user */

		homePage.waitForPageLoad();
		homePage.getDropDownLoginUser().click();
		homePage.getLinkPreference().click();

		preferencesPage.waitForPageLoad();
		preferencesPage.setSelectedTimeZone(TIMEZONECT);
		preferencesPage.getBtnOk().click();
		homePage.waitForPageLoad();
		assertTrue(homePage.getDropDownTimeZone().getText().equals(TIMEZONECT));
	}

	/**
	 * Test Case ID: TC55_VerifyEditUserPreferences Test Description: Modify timezone of user from drop-down
	 * @throws Exception
	 */
	@Test
	public void TC55_VerifyEditUserPreferencesfromDropDown() throws Exception {
		Log.info("\nRunning - TC55_VerifyEditUserPreferences Test Description: Modify timezone of user from drop-down\n");

		/* Login as automation admin and create new Picarro admin user. Do NOT alter existing user. */
		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		manageUsersPageAction.open(BaseActions.EMPTY, BaseActions.NOTSET);
		manageUsersPageAction.createNewPicarroUser(BaseActions.EMPTY, 14 /*userRowID*/);

		String usernameColonPassword = String.format("%s:%s", ManageUsersPageActions.workingDataRow.get().username,
				ManageUsersPageActions.workingDataRow.get().password);
		loginPageAction.open(BaseActions.EMPTY, BaseActions.NOTSET);
		loginPageAction.login(usernameColonPassword, BaseActions.NOTSET);   /* login using newly created user */

		homePage.waitForPageLoad();
		homePage.getDropDownTimeZone().click();
		homePage.waitForPageLoad();
		homePage.setTimeZoneToPST();
		homePage.waitForPageLoad();
		homePage.getDropDownLoginUser().click();
		homePage.getLinkPreference().click();
		preferencesPage.waitForPageLoad();
		assertTrue(preferencesPage.getSelectedTimeZone().getText().equals(TIMEZONEPT));

	}

	/**
	 * Test Case ID: TC140_VerifyUserCanClickViewAllSurveysAndViewSurvey Test Description: User is able to click on View All Surveys and View Survey links present on Dashboard
	 */
	@Test
	public void TC140_VerifyUserCanClickViewAllSurveysAndViewSurvey() {
		Log.info("\nRunning - TC140_VerifyUserCanClickViewAllSurveysAndViewSurvey\n");
		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		homePage.waitForPageLoad();
		homePage.getFirstSurvey().click();
		surveyViewPage.waitForPageLoad();
		surveyViewPage.waitForAJAXCallsToComplete();
		surveyViewPage.waitForUIUnBlock();
		assertTrue(surveyViewPage.checkIfAtSurveyViewPage());
		surveyViewPage.clickPicarroLogoButton();
		homePage.waitForPageLoad();
		assertTrue(homePage.checkIfAtHomePage());
		homePage.getLinkDrivingSurveys().click();
		measurementSessionsPage.waitForPageLoad();
		try {
			measurementSessionsPage.actionOnDrivingSurvey(PICADMNSTDTAG2, ADMINISTRATORUSER, SQAPICLOC4SUR, SQAPICLOC4SURANA, DrivingSurveyButtonType.ViewSurvey);
		} catch (Exception e) {
			Log.error(e.toString());
		}
		surveyViewPage.waitForPageLoad();
		assertTrue(surveyViewPage.checkIfAtSurveyViewPage());
	}
}