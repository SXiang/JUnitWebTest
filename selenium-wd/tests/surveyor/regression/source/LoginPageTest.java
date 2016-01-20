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

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.EULAPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.SurveyorBaseTest;

public class LoginPageTest extends SurveyorBaseTest {

	@Test
	public void loginTest_TC11_VerifySecureConnection() {
		loginPage.open();
		loginPage.waitForPageToLoad();
		assertTrue(loginPage.getStrPageURL().contains("Account/Login"));
	}

	@Test
	public void loginTest_TC25_PicarroAdmin() {
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		homePage.open();
		assertTrue(PICDFADMIN + " user login unsuccessful!", homePage.checkIfAtHomePage());
		loginPage = homePage.logout();
	}

	@Test
	public void loginTest_TC25_CustomerAdmin() {
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		homePage.open();
		assertTrue(SQACUSUAUSER + " user login unsuccessful!", homePage.checkIfAtHomePage());
		loginPage = homePage.logout();
	}

	@Test
	public void loginTest_TC25_CustomerDriver() {
		loginPage.open();
		loginPage.loginNormalAs(SQACUSDR, USERPASSWORD);

		homePage.open();
		assertTrue(SQACUSDRUSER + " user login unsuccessful!", homePage.checkIfAtHomePage());
		loginPage = homePage.logout();
	}

	@Test
	public void loginTest_TC26_AcceptEUCLA() {
		String customerName = SQACUS;
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + testSetup.getFixedSizeRandomNumber(8) + REGBASEUSERNAME;
		String location = customerName + " - " + SQACUSLOC;
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		homePage.waitForPageLoad();
		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageUsersPage);
		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEDR, location);
		loginPage.open();
		loginPage.login(userName, USERPASSWORD);
		loginPage.waitForPageLoad();
		EULAPage eulaPage = new EULAPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, eulaPage);
		assertTrue(eulaPage.getiAccept().isDisplayed());
		eulaPage.getiAccept().click();
		homePage.waitForPageLoad();
		assertTrue(homePage.checkIfAtHomePage());
	}

	@Test
	public void loginTest_TC29_DriverLogin() {
		String customerName = SQACUS;
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + testSetup.getFixedSizeRandomNumber(8) + REGBASEUSERNAME;
		String location = customerName + " - " + SQACUSLOC;
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		homePage.waitForPageLoad();
		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageUsersPage);
		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEDR, location);
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		assertTrue(homePage.checkIfAtHomePage());
	}

}