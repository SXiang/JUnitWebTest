/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.CUSUSERROLEDR;
import static surveyor.scommon.source.SurveyorConstants.EULASTRING;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.REGBASEUSERNAME;
import static surveyor.scommon.source.SurveyorConstants.SQACUS;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUAUSER;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDRUSER;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.EULAPage;
import surveyor.scommon.source.FleetMapPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.PreferencesPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class LoginPageTest extends SurveyorBaseTest {
	private static ManageUsersPage manageUsersPage;
	private static HomePage homePage;
	private static EULAPage eulaPage;

	@BeforeClass
	public static void setupACLandVisibilityTest() {
		manageUsersPage = new ManageUsersPage(driver, getBaseURL(), getTestSetup());
		PageFactory.initElements(driver, manageUsersPage);
		homePage = new HomePage(driver, getBaseURL(), getTestSetup());
		PageFactory.initElements(driver, homePage);
		eulaPage = new EULAPage(driver, getBaseURL(), getTestSetup());
		PageFactory.initElements(driver, eulaPage);
	}

	@Test
	public void loginTest_TC11_VerifySecureConnection() {
		getLoginPage().open();
		getLoginPage().waitForPageToLoad();
		assertTrue(getLoginPage().getStrPageURL().contains("Account/Login"));
	}

	@Test
	public void loginTest_TC25_PicarroAdmin() {
		getLoginPage().open();
		getLoginPage().waitForPageToLoad();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		homePage.open();
		homePage.waitForPageLoad();
		assertTrue(PICDFADMIN + " user login unsuccessful!", homePage.checkIfAtHomePage());
		setLoginPage(homePage.logout());
	}

	@Test
	public void loginTest_TC25_CustomerAdmin() {
		getLoginPage().open();
		getLoginPage().waitForPageToLoad();
		getLoginPage().loginNormalAs(SQACUSUA, USERPASSWORD);

		homePage.open();
		homePage.waitForPageLoad();
		assertTrue(SQACUSUAUSER + " user login unsuccessful!", homePage.checkIfAtHomePage());
		setLoginPage(homePage.logout());
	}

	@Test
	public void loginTest_TC25_CustomerDriver() {
		getLoginPage().open();
		getLoginPage().loginNormalAs(SQACUSDR, USERPASSWORD);

		homePage.open();
		homePage.waitForPageLoad();
		assertTrue(SQACUSDRUSER + " user login unsuccessful!", homePage.checkIfAtHomePage());
		setLoginPage(homePage.logout());
	}

	@Test
	public void loginTest_TC26_AcceptEUCLA() {
		String customerName = SQACUS;
		String userName = customerName + getTestSetup().getFixedSizeRandomNumber(8) + REGBASEUSERNAME;
		String location = customerName + " - " + SQACUSLOC;
		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		homePage.waitForPageLoad();
		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEDR, location);
		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, USERPASSWORD);
		homePage.waitForPageLoad();
		assertTrue(homePage.checkIfAtHomePage());
	}

	@Test
	public void loginTest_TC29_DriverLogin() {
		String customerName = SQACUS;
		String userName = customerName + getTestSetup().getFixedSizeRandomNumber(8) + REGBASEUSERNAME;
		String location = customerName + " - " + SQACUSLOC;
		getLoginPage().open();
		getLoginPage().loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		homePage.waitForPageLoad();
		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEDR, location);
		getLoginPage().open();
		getLoginPage().loginNormalAs(userName, USERPASSWORD);
		assertTrue(homePage.checkIfAtHomePage());
	}

}