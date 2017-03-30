package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.ADMINISTRATORUSER;
import static surveyor.scommon.source.SurveyorConstants.EULASTRING;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG2;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOCSUR;
import static surveyor.scommon.source.SurveyorConstants.SQAPICLOC4SUR;
import static surveyor.scommon.source.SurveyorConstants.SQAPICLOC4SURANA;
import static surveyor.scommon.source.SurveyorConstants.CUSUSERROLESU;

import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
import surveyor.dataaccess.source.Customer;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.actions.PageActionsStore;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.EULAPage;
import surveyor.scommon.source.FleetMapPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyViewPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.BaseReportsPageActionTest.ReportTestRunMode;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.MeasurementSessionsPage.DrivingSurveyButtonType;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.SurveyorSystemsPage;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;

@RunWith(SurveyorTestRunner.class)
public class SimulatorGeneralTests extends SurveyorBaseTest {
	private static ManageUsersPage manageUsersPage;
	private static HomePage homePage;
	private static LoginPage loginPage;
	private static EULAPage eulaPage;
	private static ManageCustomersPage manageCustomersPage;
	private static ManageLocationsPage manageLocationsPage;
	private static DriverViewPage driverViewPage;
	private static FleetMapPage fleetMapPage;
	private static SurveyorSystemsPage surveyorsPage;
	private static MeasurementSessionsPage measurementSessionsPage;
	private static SurveyViewPage surveyViewPage;
	private static ComplianceReportsPage complinaceReportsPage;
	private static Map<String, String> testAccount;
	private static Map<String, String> testSupervisorAccount;

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects();
	}

	@Before
	public void beforeTest() throws Exception {
		initializeTestObjects();
		initializePageObjects();

		if(testAccount == null){
			testAccount = createTestAccount("TC29");
			addTestSurvey(testAccount.get("analyzerName"), testAccount.get("analyzerSharedKey")
					,testAccount.get("userName"), testAccount.get("userPassword"), SurveyType.Standard);
			addTestReport(testAccount.get("userName"), testAccount.get("userPassword"), SurveyModeFilter.Standard);

		}else{
			loginPage.open();
			loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
			manageCustomersPage.open();
			manageCustomersPage.editAndSelectLicensedFeatures(testAccount.get("customerName"), LicensedFeatures.values());
		}

		if(testSupervisorAccount == null){
			testSupervisorAccount = createTestAccount("TC29Cus2");
		}else{
			loginPage.open();
			loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
			manageCustomersPage.open();
			manageCustomersPage.editAndSelectLicensedFeatures(testSupervisorAccount.get("customerName"), LicensedFeatures.values());
			manageCustomersPage.waitForPageLoad();
		}
	}

	private void initializePageObjects() {
		PageObjectFactory pageObjectFactory = new PageObjectFactory();

		manageCustomersPage = pageObjectFactory.getManageCustomersPage();
		PageFactory.initElements(getDriver(), manageCustomersPage);

		manageLocationsPage = pageObjectFactory.getManageLocationsPage();
		PageFactory.initElements(getDriver(), manageLocationsPage);

		driverViewPage = pageObjectFactory.getDriverViewPage();
		PageFactory.initElements(getDriver(), driverViewPage);

		fleetMapPage = pageObjectFactory.getFleetMapPage();
		PageFactory.initElements(getDriver(), fleetMapPage);

		loginPage = pageObjectFactory.getLoginPage();
		PageFactory.initElements(getDriver(), loginPage);
		setLoginPage(loginPage);

		homePage = pageObjectFactory.getHomePage();
		PageFactory.initElements(getDriver(), homePage);
		setHomePage(homePage);

		surveyorsPage = pageObjectFactory.getSurveyorSystemsPage();
		PageFactory.initElements(getDriver(), surveyorsPage);

		manageUsersPage = pageObjectFactory.getManageUsersPage();
		PageFactory.initElements(getDriver(), manageUsersPage);

		eulaPage = pageObjectFactory.getEULAPage();
		PageFactory.initElements(getDriver(), eulaPage);

		measurementSessionsPage = pageObjectFactory.getMeasurementSessionsPage();
		PageFactory.initElements(getDriver(), measurementSessionsPage);

		surveyViewPage = pageObjectFactory.getSurveyViewPage();
		PageFactory.initElements(getDriver(), surveyViewPage);

		complinaceReportsPage = pageObjectFactory.getComplianceReportsPage();
		PageFactory.initElements(getDriver(), complinaceReportsPage);
	}

	@Test
	public void loginTest_TC29_DiffCustRoleLogin() throws Exception {

		String userName = testAccount.get("userName");
		String userPassword = testAccount.get("userPassword");
		String customerName = testAccount.get("customerName");
		Customer customer = Customer.getCustomer(customerName);
		String customerId = customer.getId();
		String supervisorName = testSupervisorAccount.get("userName");
		String supervisorPassword = testSupervisorAccount.get("userPassword");


		loginPage.open();
		loginPage.loginNormalAs(userName, userPassword);
		homePage.waitForPageLoad();

		assertTrue(getHomePage().getLinkReports().isEnabled());
		getHomePage().getLinkReports().click();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

		assertTrue(getHomePage().getLinkCusAdmin().isEnabled());
		getHomePage().getLinkCusAdmin().click();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());
		assertTrue(getHomePage().getLinkManageSurveyors().isDisplayed());
		assertTrue(getHomePage().getLinkAdminManageUsers().isDisplayed());
		assertTrue(getHomePage().getLinkCusManageLocations().isDisplayed());
		assertTrue(getHomePage().getLinkCusManageRefGasBottles().isDisplayed());
		getHomePage().getLinkCusAdmin().click();

		homePage.getLinkSurveyors().click();
		surveyorsPage.waitForDataTabletoLoad();
		assertTrue(surveyorsPage.getTableRows().size() > 0);
		surveyorsPage.performSearch(testAccount.get("surveyorName"));
		//surveyorsPage.getTxtSurveyorSearch().sendKeys(testAccount.get("surveyorName"));
		surveyorsPage.waitForDataTabletoLoad();
		assertTrue(surveyorsPage.getTableRows().size() > 0);


		getHomePage().clickOnDrivingSurveyLink();
		measurementSessionsPage.waitForTableDataToLoad();
		assertTrue(measurementSessionsPage.getTableRows().size() > 0);
		measurementSessionsPage.performSearch(testAccount.get("surveyorName"));
		//measurementSessionsPage.getInputSearch().sendKeys(testAccount.get("surveyorName"));
		measurementSessionsPage.waitForTableDataToLoad();
		assertTrue(measurementSessionsPage.getTableRows().size() > 0);

		homePage.getLinkDrivingSurveys().click();
		measurementSessionsPage.waitForPageLoad();

		measurementSessionsPage.clickOnFirstViewSurvey();
		surveyViewPage.waitForPageLoad();
		assertTrue(surveyViewPage.checkIfAtSurveyViewPage());

		surveyViewPage.clickGisButton();

		surveyViewPage.clickPicarroLogoButton();
		homePage.waitForPageLoad();
		assertTrue(homePage.checkIfAtHomePage());

		homePage.getLinkReports().click();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());
		assertTrue(homePage.getLinkCompliance().isDisplayed());
		homePage.getLinkCompliance().click();
		complinaceReportsPage.waitForPageLoad();
		String createdByXPath = "//*[@id='datatable']/tbody/tr[1]/td[3]";
		String createdByCellText = complinaceReportsPage.getTable().findElement(By.xpath(createdByXPath)).getText().trim();
		assertTrue(createdByCellText.equals(testAccount.get("userName")));
		homePage.logout();

		loginPage.open();
		loginPage.loginNormalAs(supervisorName, supervisorPassword);
		homePage.waitForPageLoad();
		assertTrue (homePage.checkIfAtHomePage());
		homePage.logout();

		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);
		homePage.waitForPageLoad();
		assertTrue (homePage.checkIfAtHomePage());
		assertTrue (homePage.getLinkPicarroAdmin().isEnabled());
		homePage.clickOnPicarroAdminLink();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());
		assertTrue(getHomePage().getLinkPicAdminViewAnlLogs().isEnabled());
		assertTrue(getHomePage().getLinkPicAdminViewSurLogs().isEnabled());
	}
}
