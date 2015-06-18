/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUAUSER;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDRUSER;

import org.junit.Test;

import surveyor.scommon.source.SurveyorBaseTest;

public class LoginPageTest extends SurveyorBaseTest {
	
	@Test
	public void loginTest_TC25_PicarroAdmin() {
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(),
				testSetup.getLoginPwd());
		
		homePage.open();
		assertTrue(PICDFADMIN + " user login unsuccessful!",
				homePage.checkIfAtHomePage());
		assertTrue(homePage.checkAdministratorDashboard());
		loginPage = homePage.logout();
	}
	
	@Test
	public void loginTest_TC25_CustomerAdmin() {
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		
		homePage.open();
		assertTrue(SQACUSUAUSER
				+ " user login unsuccessful!", homePage.checkIfAtHomePage());
		assertTrue(homePage.checkVisitilityForCusUA(SQACUSUA));
		loginPage = homePage.logout();
	}
	
	@Test
	public void loginTest_TC25_CustomerDriver() {
		loginPage.open();
		loginPage.loginNormalAs(SQACUSDR, USERPASSWORD);

		homePage.open();
		assertTrue(SQACUSDRUSER
				+ " user login unsuccessful!", homePage.checkIfAtHomePage());
		assertTrue(homePage.checkVisitilityForCusDR(SQACUSDR));
		loginPage = homePage.logout();
	}
}