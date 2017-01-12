/**
 *
 */
package surveyor.unittest.mobile.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDRUSER;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUAUSER;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import surveyor.scommon.mobile.source.MobileLoginPage;
import surveyor.scommon.mobile.source.MobileReportsPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class MobileUnitTest extends SurveyorBaseTest {
	private static MobileReportsPage reportsPage;
	private static MobileLoginPage loginPage;

	/**
	 * This method is called by the 'main' thread
	 */
	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects(false);
		loginPage = new MobileLoginPage();
	}

	@Test
	public void TC25_LoginTest_PicarroAdmin() {
		loginPage.open();
		reportsPage = loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);
		reportsPage.open();

		assertTrue(PICDFADMIN + " user login unsuccessful!",
				reportsPage.waitUntilPageLoad());
		loginPage = reportsPage.logout();
	}

	@Test
	public void TC25_LoginTest_CustomerAdmin() {
		loginPage.open();
		reportsPage = loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		reportsPage.open();
		assertTrue(SQACUSUAUSER + " user login unsuccessful!",
				reportsPage.waitUntilPageLoad());
		loginPage = reportsPage.logout();
	}

	@Test /* Failed to check screenshots */
	public void TC25_LoginTest_CustomerDriver() {
		loginPage.open();
//		loginPage.loginNormalAs(SQACUSDR, USERPASSWORD);

		reportsPage.open();
		assertTrue(SQACUSDRUSER + " user login unsuccessful!",
				reportsPage.waitUntilPageLoad());
		loginPage = reportsPage.logout();
	}
}