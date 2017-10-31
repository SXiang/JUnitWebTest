/**
 * 
 */
package surveyor.grid.poc.source;

import static org.junit.Assert.assertTrue;
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
import org.openqa.selenium.support.PageFactory;

import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.ManageAnalyzersPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageRefGasBottlesPage;
import surveyor.scommon.source.ManageReleaseNotesPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class GridPOCTestClass1 extends GridPOCBaseTest {
	private static ComplianceReportsPage complianceReportsPage;
	private static ManageCustomersPage manageCustomersPage;
	private static ManageUsersPage manageUsersPage;
	private static ManageLocationsPage manageLocationsPage;
	private static ManageSurveyorPage manageSurveyorsPage;
	private static ManageAnalyzersPage manageAnalyzersPage;
	private static ManageRefGasBottlesPage manageRefGasBottlesPage;
	private static ManageReleaseNotesPage manageReleaseNotesPage;

	@BeforeClass
	public static void setupBeforeClass() {
		complianceReportsPage = new ComplianceReportsPage(getDriver(), getBaseURL(),
				getTestSetup());
		PageFactory.initElements(getDriver(), complianceReportsPage);

		manageCustomersPage = new ManageCustomersPage(getDriver(), getBaseURL(),
				getTestSetup());
		PageFactory.initElements(getDriver(), manageCustomersPage);

		manageUsersPage = new ManageUsersPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(), manageUsersPage);

		manageLocationsPage = new ManageLocationsPage(getDriver(), getBaseURL(),
				getTestSetup());
		PageFactory.initElements(getDriver(), manageLocationsPage);

		manageSurveyorsPage = new ManageSurveyorPage(getDriver(), getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(), manageSurveyorsPage);

		manageAnalyzersPage = new ManageAnalyzersPage(getDriver(), getBaseURL(),
				getTestSetup());
		PageFactory.initElements(getDriver(), manageAnalyzersPage);

		manageRefGasBottlesPage = new ManageRefGasBottlesPage(getDriver(),
				getBaseURL(), getTestSetup());
		PageFactory.initElements(getDriver(), manageRefGasBottlesPage);
		
		manageReleaseNotesPage = new ManageReleaseNotesPage(getDriver(), getBaseURL(),
				getTestSetup());
		PageFactory.initElements(getDriver(), manageReleaseNotesPage);
	}

	@Test
	public void TC25_LoginTest_PicarroAdmin() {
		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);
		getHomePage().open();
	
		assertTrue(PICDFADMIN + " user login unsuccessful!",
				getHomePage().checkIfAtHomePage());
	}

	@Test
	public void TC25_LoginTest_CustomerAdmin() {
		getLoginPage().open();
		getLoginPage().loginNormalAs(SQACUSUA, USERPASSWORD);

		getHomePage().open();
		assertTrue(SQACUSUAUSER + " user login unsuccessful!",
				getHomePage().checkIfAtHomePage());
	}

	@Test
	public void TC25_LoginTest_CustomerDriver() {
		getLoginPage().open();
		getLoginPage().loginNormalAs(SQACUSDR, USERPASSWORD);

		getHomePage().open();
		assertTrue(SQACUSDRUSER + " user login unsuccessful!",
				getHomePage().checkIfAtHomePage());
	}
}