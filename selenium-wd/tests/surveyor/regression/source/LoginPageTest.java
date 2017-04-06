/**
 *
 */
package surveyor.regression.source;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.CUSUSERROLEDR;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.REGBASEUSERNAME;
import static surveyor.scommon.source.SurveyorConstants.SQACUS;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUAUSER;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDRUSER;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC;
import static surveyor.scommon.source.SurveyorConstants.INVALIDUSERPASS;
import static surveyor.scommon.source.SurveyorConstants.INVALIDPSWD;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONE;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOCSUR;
import static surveyor.scommon.source.SurveyorConstants.ADMINISTRATORUSER;
import static surveyor.scommon.source.SurveyorConstants.SQACUS20161;
import static surveyor.scommon.source.SurveyorConstants.SURVEYOR_SQACUSUNIT1;
import static surveyor.scommon.source.SurveyorConstants.CUSDRVSTDTAG3200;
import static surveyor.scommon.source.SurveyorConstants.CUSUSERROLESU;






import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
import common.source.WebElementExtender;
import surveyor.scommon.actions.PageActionsStore;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.EULAPage;
import surveyor.scommon.source.FleetMapPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.PreferencesPage;
import surveyor.scommon.source.SurveyViewPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorSystemsPage;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.MeasurementSessionsPage.DrivingSurveyButtonType;
import surveyor.scommon.source.MeasurementSessionsPage.UserRoleType;


@RunWith(SurveyorTestRunner.class)
public class LoginPageTest extends SurveyorBaseTest {
	private static ManageUsersPage manageUsersPage;
	private static HomePage homePage;
	private static LoginPage loginPage;
	private static EULAPage eulaPage;
	private static ComplianceReportsPage complinaceReportsPage;
	private static PreferencesPage preferencesPage;
	private static FleetMapPage fleetMapPage;
	private static SurveyorSystemsPage surveyorPage;
	private static MeasurementSessionsPage measurementSessionsPage;
	private static SurveyViewPage surveyViewPage;
	private static Map<String, String> testReport;
	
	@BeforeClass
	public static void setupACLandVisibilityTest() {
		initializeTestObjects(); // ensures TestSetup and TestContext are
									// initialized before Page object creation.

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
		manageUsersPage = pageObjectFactory.getManageUsersPage();
		PageFactory.initElements(getDriver(), manageUsersPage);

		loginPage = pageObjectFactory.getLoginPage();
		setLoginPage(loginPage);
		PageFactory.initElements(getDriver(), loginPage);

		homePage = pageObjectFactory.getHomePage();
		setHomePage(homePage);
		PageFactory.initElements(getDriver(), homePage);

		eulaPage = pageObjectFactory.getEULAPage();
		PageFactory.initElements(getDriver(), eulaPage);
		
		complinaceReportsPage = pageObjectFactory.getComplianceReportsPage();
		PageFactory.initElements(getDriver(), complinaceReportsPage);

		preferencesPage = pageObjectFactory.getPreferencesPage();
		PageFactory.initElements(getDriver(), preferencesPage);

		fleetMapPage = pageObjectFactory.getFleetMapPage();
		PageFactory.initElements(getDriver(), fleetMapPage);

		surveyorPage = pageObjectFactory.getSurveyorSystemsPage();
		PageFactory.initElements(getDriver(), surveyorPage);

		measurementSessionsPage = pageObjectFactory.getMeasurementSessionsPage();
		PageFactory.initElements(getDriver(), measurementSessionsPage);
		
		surveyViewPage = pageObjectFactory.getSurveyViewPage();
		PageFactory.initElements(getDriver(), surveyViewPage);
	}

	@Test
	public void loginTest_TC11_VerifySecureConnection() {
		loginPage.open();
		loginPage.waitForPageToLoad();
		assertTrue(loginPage.getStrPageURL().contains("Account/Login"));
	}

	@Test
	public void loginTest_TC25_PicarroAdmin() {
		loginPage.open();
		loginPage.waitForPageToLoad();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup()
				.getLoginPwd());

		homePage.open();
		homePage.waitForPageLoad();
		assertTrue(PICDFADMIN + " user login unsuccessful!",
				homePage.checkIfAtHomePage());
		loginPage = homePage.logout();
	}

	@Test
	public void loginTest_TC25_CustomerAdmin() {
		loginPage.open();
		loginPage.waitForPageToLoad();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		homePage.open();
		homePage.waitForPageLoad();
		assertTrue(SQACUSUAUSER + " user login unsuccessful!",
				homePage.checkIfAtHomePage());
		loginPage = homePage.logout();
	}

	@Test
	public void loginTest_TC25_CustomerDriver() {
		loginPage.open();
		loginPage.loginNormalAs(SQACUSDR, USERPASSWORD);

		homePage.open();
		homePage.waitForPageLoad();
		assertTrue(SQACUSDRUSER + " user login unsuccessful!",
				homePage.checkIfAtHomePage());
		loginPage = homePage.logout();
	}

	@Test
	public void loginTest_TC26_AcceptEUCLA() {
		String customerName = SQACUS;

		String userName = customerName
				+ getTestSetup().getFixedSizeRandomNumber(8) + REGBASEUSERNAME;
		String location = SQACUSLOC;

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup()
				.getLoginPwd());
		homePage.waitForPageLoad();
		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewCustomerUser(customerName, userName,
				USERPASSWORD, CUSUSERROLEDR, location);
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		homePage.waitForPageLoad();
		assertTrue(homePage.checkIfAtHomePage());
	}

	@Test
	public void loginTest_TC27_InvalidCredLogin() {

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, INVALIDPSWD);
		loginPage.waitForPageLoad();
		assertTrue(loginPage.getTxtErrLogin().getText()
				.equalsIgnoreCase(INVALIDUSERPASS));
	}

	@Test
	public void loginTest_TC36_VerifyCustomerSupervisorLoginProfile() throws Exception {

		List<String> strListTagCus = new ArrayList<String> ();
		List<String> strListTagPic = new ArrayList<String> ();
			
		testReport = addTestReport(SQACUSUA, USERPASSWORD,
				SurveyModeFilter.Standard);
		String rptTitle = testReport.get(SurveyType.Standard + "Title");
		String strCreatedBy = testReport.get("userName");

		getLoginPage().open();
		getLoginPage().loginNormalAs(SQACUSSU, USERPASSWORD);

		assertFalse(WebElementExtender.isElementPresentAndDisplayed(homePage.getLinkCusAdmin()));
		assertFalse(WebElementExtender.isElementPresentAndDisplayed(homePage.getLinkPicarroAdmin()));

		complinaceReportsPage.open();
		complinaceReportsPage.waitForPageLoad();
		complinaceReportsPage.searchAndDeleteReport(rptTitle, strCreatedBy);
		
		homePage.open();
		homePage.waitForPageLoad();
		homePage.getDropDownLoginUser().click();
		homePage.getLinkPreference().click();
		preferencesPage.waitForPageLoad();
		assertTrue(preferencesPage.getSelectedTimeZone().getText().equalsIgnoreCase(TIMEZONE));
		assertTrue(preferencesPage.getSelectedLocation().getText().equalsIgnoreCase(SQACUSLOC));
		homePage.getLinkFleetMap().click();
		fleetMapPage.waitForPageLoad();
		fleetMapPage.waitForFleetMaptoLoad();
		assertTrue(fleetMapPage.checkIfAtFleetMapPage());
		assertTrue(fleetMapPage.getFleetMap().isDisplayed());
		homePage.getLinkSurveyors().click();
		surveyorPage.getTxtSurveyorSearch().sendKeys(SQACUSLOCSUR);
		surveyorPage.waitForDataTabletoLoad();
		assertTrue(surveyorPage.getTableRows().size() > 0);
		
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
}