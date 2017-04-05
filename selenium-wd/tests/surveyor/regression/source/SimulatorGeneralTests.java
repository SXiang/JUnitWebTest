package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.CUSTOMERNAMEPREFIX;
import static surveyor.scommon.source.SurveyorConstants.EULASTRING;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.REGBASEUSERNAME;
import static surveyor.scommon.source.SurveyorConstants.CUSUSERROLESU;

import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

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
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;
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
					,testAccount.get("driverUserName"), testAccount.get("userPassword"), SurveyType.Standard);
			addTestReport(testAccount.get("utilityAdminUserName"), testAccount.get("userPassword"), SurveyModeFilter.Standard);

		}else{
			loginPage.open();
			loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
			manageCustomersPage.open();
			manageCustomersPage.editAndSelectLicensedFeatures(testAccount.get("customerName"), LicensedFeatures.values());
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

		LicensedFeatures[] lfs = LicensedFeatures.values(null);
		String uniqueNumber = getTestSetup().getFixedSizeRandomNumber(6);
		String customer2Name = CUSTOMERNAMEPREFIX + uniqueNumber + "TC29";
		String eula = customer2Name + ": " + EULASTRING;
		String cityName = "Santa Clara";
		String location2Name = uniqueNumber + "Loc2";
		String supervisorUserName = uniqueNumber + REGBASEUSERNAME;

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
		surveyorsPage.waitForDataTabletoLoad();
		assertTrue(surveyorsPage.getTableRows().size() > 0);


		getHomePage().clickOnDrivingSurveyLink();
		measurementSessionsPage.waitForTableDataToLoad();
		assertTrue(measurementSessionsPage.getTableRows().size() > 0);
		measurementSessionsPage.performSearch(testAccount.get("surveyorName"));
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
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);
		homePage.waitForPageLoad();
		assertTrue (homePage.checkIfAtHomePage());
		assertTrue (homePage.getLinkPicarroAdmin().isEnabled());
		homePage.clickOnPicarroAdminLink();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());
		assertTrue(getHomePage().getLinkPicAdminViewAnlLogs().isEnabled());
		assertTrue(getHomePage().getLinkPicAdminViewSurLogs().isEnabled());

		manageCustomersPage.open();
		if(!manageCustomersPage.addNewCustomer(customer2Name, eula, true,lfs)){
			fail(String.format("Failed to add a new customer %s, %s, %s",customer2Name, eula, true));
		}

		manageLocationsPage.open();
		if(!manageLocationsPage.addNewLocation(location2Name, customer2Name, cityName)){
			fail(String.format("Failed to add a new location %s, %s, %s",location2Name, customer2Name, cityName));
		}

		manageUsersPage.open();
		if(!manageUsersPage.addNewCustomerUser(customer2Name, supervisorUserName, userPassword, CUSUSERROLESU, location2Name)){
			fail(String.format("Failed to add a new customer user %s, %s, %s, %s, %s",customerName, supervisorUserName, userPassword, CUSUSERROLESU, location2Name));
		}	

		homePage.logout();

		loginPage.open();
		loginPage.loginNormalAs(supervisorUserName, userPassword);
		homePage.waitForPageLoad();
		assertTrue (homePage.checkIfAtHomePage());
		homePage.logout();
	}
	
	
	@Test
	public void loginTest_TC36_VerifyCustomerSupervisorLoginProfile() throws Exception {

		String utilityAdminUserName = testAccount.get("utilityAdminUserName");
		String supervisorUserName = testAccount.get("supervisorUserName");
		String driverUserName = testAccount.get("driverUserName");
		String userPassword = testAccount.get("userPassword");
		String customerName = testAccount.get("customerName");

		loginPage.open();
		loginPage.loginNormalAs(supervisorUserName, userPassword);
		homePage.waitForPageLoad();
		assertFalse(homePage.getLinkCusAdmin().isEnabled());
		assertFalse(homePage.getLinkPicarroAdmin().isEnabled());
		
		assertTrue(homePage.getLinkReports().isEnabled());
		homePage.getLinkReports().click();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());
		assertTrue(homePage.getLinkCompliance().isDisplayed());
		homePage.getLinkCompliance().click();
		complinaceReportsPage.waitForPageLoad();
		String createdByXPath = "//*[@id='datatable']/tbody/tr[1]/td[3]";
		String createdByCellText = complinaceReportsPage.getTable().findElement(By.xpath(createdByXPath)).getText().trim();
		assertTrue(createdByCellText.equals(testAccount.get("utilityAdminUserName")));
	
		String reportTitleXPath = "//*[@id='datatable']/tbody/tr[1]/td[1]";
		String reportTitleCellText = complinaceReportsPage.getTable().findElement(By.xpath(reportTitleXPath)).getText().trim();
		System.out.println("!!!!!!!!!!!!!!!!!!!!" + reportTitleCellText);
		complinaceReportsPage.deleteReport(reportTitleCellText, testAccount.get(utilityAdminUserName));

		complinaceReportsPage.getInputSearch().sendKeys(reportTitleCellText);
		complinaceReportsPage.waitForSearchResultsToLoad();
		assertFalse(complinaceReportsPage.getTableRows().size() >0);
		
		
		getHomePage().clickOnDrivingSurveyLink();
		measurementSessionsPage.waitForTableDataToLoad();
		assertTrue(measurementSessionsPage.getTableRows().size() > 0);
		measurementSessionsPage.performSearch(testAccount.get("surveyorName"));
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
	//	String createdByXPath = "//*[@id='datatable']/tbody/tr[1]/td[3]";
	//	String createdByCellText = complinaceReportsPage.getTable().findElement(By.xpath(createdByXPath)).getText().trim();
		assertTrue(createdByCellText.equals(testAccount.get("userName")));
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

		
		homePage.logout();

		loginPage.open();
		loginPage.loginNormalAs(supervisorUserName, userPassword);
		homePage.waitForPageLoad();
		assertTrue (homePage.checkIfAtHomePage());
		homePage.logout();
	}
}