/**
 *
 */
package surveyor.regression.source;

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

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
import surveyor.scommon.actions.PageActionsStore;
import surveyor.scommon.source.EULAPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class LoginPageTest extends SurveyorBaseTest {
	private static ManageUsersPage manageUsersPage;
	private static HomePage homePage;
	private static LoginPage loginPage;
	private static EULAPage eulaPage;

	@BeforeClass
	public static void setupACLandVisibilityTest() {
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
		manageUsersPage = pageObjectFactory.getManageUsersPage();
		PageFactory.initElements(getDriver(), manageUsersPage);
		homePage = pageObjectFactory.getHomePage();
		PageFactory.initElements(getDriver(), homePage);
		loginPage = pageObjectFactory.getLoginPage();
		PageFactory.initElements(getDriver(), loginPage);
		eulaPage = pageObjectFactory.getEULAPage();
		PageFactory.initElements(getDriver(), eulaPage);
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
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		homePage.open();
		homePage.waitForPageLoad();
		assertTrue(PICDFADMIN + " user login unsuccessful!", homePage.checkIfAtHomePage());
		loginPage = homePage.logout();
	}

	@Test
	public void loginTest_TC25_CustomerAdmin() {
		loginPage.open();
		loginPage.waitForPageToLoad();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		homePage.open();
		homePage.waitForPageLoad();
		assertTrue(SQACUSUAUSER + " user login unsuccessful!", homePage.checkIfAtHomePage());
		loginPage = homePage.logout();
	}

	@Test
	public void loginTest_TC25_CustomerDriver() {
		loginPage.open();
		loginPage.loginNormalAs(SQACUSDR, USERPASSWORD);

		homePage.open();
		homePage.waitForPageLoad();
		assertTrue(SQACUSDRUSER + " user login unsuccessful!", homePage.checkIfAtHomePage());
		loginPage = homePage.logout();
	}

	@Test
	public void loginTest_TC26_AcceptEUCLA() {
		String customerName = SQACUS;

		String userName = customerName + getTestSetup().getFixedSizeRandomNumber(8) + REGBASEUSERNAME;
		String location = SQACUSLOC;

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		homePage.waitForPageLoad();
		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEDR, location);
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		homePage.waitForPageLoad();
		assertTrue(homePage.checkIfAtHomePage());
	}

	@Test
	public void loginTest_TC27_InvalidCredLogin() {

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, "1234");
		loginPage.waitForPageLoad();
		assertTrue(loginPage.getTxtErrLogin().getText().equalsIgnoreCase(INVALIDUSERPASS));
	}

	
	@Test
	public void loginTest_TC29_DriverLogin() {
		String customerName = SQACUS;

		String userName = customerName + getTestSetup().getFixedSizeRandomNumber(8) + REGBASEUSERNAME;
		String location = SQACUSLOC;

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		homePage.waitForPageLoad();
		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEDR, location);
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		assertTrue(homePage.checkIfAtHomePage());
	}
}