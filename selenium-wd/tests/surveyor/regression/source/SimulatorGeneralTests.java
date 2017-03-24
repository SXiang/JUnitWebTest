package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static surveyor.scommon.source.SurveyorConstants.ADMINISTRATORUSER;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG2;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.SQAPICLOC4SUR;
import static surveyor.scommon.source.SurveyorConstants.SQAPICLOC4SURANA;

import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import common.source.Log;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.ManageCustomerPageActions;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.FleetMapPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.SurveyViewPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.DriverViewPage.SurveyType;
import surveyor.scommon.source.MeasurementSessionsPage.DrivingSurveyButtonType;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.SurveyorSystemsPage;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;

@RunWith(SurveyorTestRunner.class)
public class SimulatorGeneralTests extends SurveyorBaseTest {
	private static ManageCustomersPage manageCustomersPage;
	private static DriverViewPage driverViewPage;
	private static ManageLocationsPage manageLocationsPage;
	private static FleetMapPage fleetMapPage;
	private static LoginPage loginPage;
	private static HomePage homePage;
	private static SurveyorSystemsPage surveyorSystemsPage;
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
		
		surveyorSystemsPage = pageObjectFactory.getSurveyorSystemsPage();
		PageFactory.initElements(getDriver(), surveyorSystemsPage);
	
		measurementSessionsPage = pageObjectFactory.getMeasurementSessionsPage();
		PageFactory.initElements(getDriver(), measurementSessionsPage);

		surveyViewPage = pageObjectFactory.getSurveyViewPage();
		PageFactory.initElements(getDriver(), surveyViewPage);
		
		complinaceReportsPage = pageObjectFactory.getComplianceReportsPage();
		PageFactory.initElements(getDriver(), complinaceReportsPage);
		
	}

	@Test
	public void loginTest_TC29_DiffCustRoleLogin() throws Exception {

		
		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		getHomePage().waitForPageLoad();
		
		if(testAccount == null){
			testAccount = createTestAccount("TC29");
			addTestSurvey(testAccount.get("analyzerName"), testAccount.get("analyzerSharedKey")
					,testAccount.get("userName"), testAccount.get("userPassword"), SurveyType.Standard);
			addTestReport(testAccount.get("userName"), testAccount.get("userPassword"), SurveyModeFilter.Standard);
			
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

			getHomePage().clickOnSurveyorsLink();
			
			surveyorSystemsPage.waitForDataTabletoLoad();
			assertTrue(surveyorSystemsPage.getTableRows().size() > 0);
			surveyorSystemsPage.getTxtSurveyorSearch().sendKeys(testAccount.get("surveyorName"));
			surveyorSystemsPage.waitForDataTabletoLoad();
			assertTrue(surveyorSystemsPage.getTableRows().size() > 0);
			
			getHomePage().clickOnDrivingSurveyLink();
			measurementSessionsPage.waitForTableDataToLoad();
			assertTrue(measurementSessionsPage.getTableRows().size() > 0);
			measurementSessionsPage.getInputSearch().sendKeys(testAccount.get("surveyorName"));
			measurementSessionsPage.waitForTableDataToLoad();
			assertTrue(measurementSessionsPage.getTableRows().size() > 0);
			
			homePage.getLinkDrivingSurveys().click();
			measurementSessionsPage.waitForPageLoad();
			
			measurementSessionsPage.clickOnFirstViewSurveyLink();
			surveyViewPage.waitForPageLoad();
			assertTrue(surveyViewPage.checkIfAtSurveyViewPage());

			surveyViewPage.clickGisButton();
			surveyViewPage.waitForPageLoad();
			//Commenting the steps as we do not have GIS Data for the customer and verification step 
			// * Click on View link of any one of the survey and turn on ALL GIS data [E8]
			// * E8. User is able to see GIS Assets data associated with that particular Customer and not other GIS Assets not associated to that Customert
			
			//surveyViewPage.getAllPipesSwitch().click();
			//surveyViewPage.getAllBoundriesSwitch().click();
			
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
		}

		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		getHomePage().waitForPageLoad();
		
		if(testSupervisorAccount == null){
			testSupervisorAccount = createTestSupervisorAccount("TC29");
			addTestSurvey(testSupervisorAccount.get("analyzerName"), testSupervisorAccount.get("analyzerSharedKey")
					,testSupervisorAccount.get("userName"), testSupervisorAccount.get("userPassword"), SurveyType.Standard);
			addTestReport(testSupervisorAccount.get("userName"), testSupervisorAccount.get("userPassword"), SurveyModeFilter.Standard);
			
			assertTrue(getHomePage().getLinkReports().isEnabled());
			getHomePage().getLinkReports().click();
			getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

			getHomePage().clickOnSurveyorsLink();
			surveyorSystemsPage.waitForDataTabletoLoad();
			assertTrue(surveyorSystemsPage.getTableRows().size() > 0);
			surveyorSystemsPage.getTxtSurveyorSearch().sendKeys(testSupervisorAccount.get("surveyorName"));
			surveyorSystemsPage.waitForDataTabletoLoad();
			assertTrue(surveyorSystemsPage.getTableRows().size() > 0);
			
			getHomePage().clickOnDrivingSurveyLink();
			measurementSessionsPage.waitForTableDataToLoad();
			assertTrue(measurementSessionsPage.getTableRows().size() > 0);
			measurementSessionsPage.getInputSearch().sendKeys(testSupervisorAccount.get("surveyorName"));
			measurementSessionsPage.waitForTableDataToLoad();
			assertTrue(measurementSessionsPage.getTableRows().size() > 0);
			
			homePage.getLinkDrivingSurveys().click();
			measurementSessionsPage.waitForPageLoad();
			
			measurementSessionsPage.clickOnFirstViewSurveyLink();
			surveyViewPage.waitForPageLoad();
			assertTrue(surveyViewPage.checkIfAtSurveyViewPage());

			surveyViewPage.clickGisButton();
			surveyViewPage.waitForPageLoad();
			//Commenting the steps as we do not have GIS Data for the customer and verification step 
			// * Click on View link of any one of the survey and turn on ALL GIS data [E8]
			 //* E8. User is able to see GIS Assets data associated with that particular Customer and not other GIS Assets not associated to that Customert
			
			//surveyViewPage.getAllPipesSwitch().click();
			//surveyViewPage.getAllBoundriesSwitch().click();
			
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
			assertTrue(createdByCellText.equals(testSupervisorAccount.get("userName")));
			homePage.logout();
		}
		
		getLoginPage().open();
		getLoginPage().loginNormalAs(PICDFADMIN, PICADMINPSWD);

		getHomePage().waitForPageLoad();
		
		assertTrue(getHomePage().getLinkPicarroAdmin().isEnabled());
		getHomePage().getLinkPicarroAdmin().click();
		getTestSetup().slowdownInSeconds(getTestSetup().getSlowdownInSeconds());

		homePage.clickOnViewAnalyzerLogsLink(getBaseURL());
		assertTrue(homePage.isLinkBroken());
		
		
		
		
	}

	/*@Test
	public void loginTest_TC35_CustDriverLogin() throws Exception {

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		homePage.waitForPageLoad();


		// Create new user.
		manageUsersPageAction.open(EMPTY, NOTSET);
		manageUsersPageAction.createNewCustomerUser(EMPTY, 20 userRowID);

		// Create new surveyor.
		manageSurveyorPageAction.open(EMPTY, NOTSET);
		manageSurveyorPageAction.createNewSurveyor(EMPTY, 4 surveyorRowID);

		// Create new analyzer.
		manageAnalyzerPageAction.open(EMPTY, NOTSET);
		manageAnalyzerPageAction.createNewAnalyzer(EMPTY, 4 analyzerRowID);


		String newUsername = ManageUsersPageActions.workingDataRow.username;
		String newUserPass = ManageUsersPageActions.workingDataRow.password;

		TestEnvironmentActions.generateSurveyForUser(newUsername, newUserPass, 
				31, 4, 60);

		assertTrue(driverViewPageAction.verifyDriverViewPageIsOpened(EMPTY, NOTSET));
		driverViewPageAction.clickOnPicarroLogoButton(EMPTY, NOTSET);
		 

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(String.format("%s:%s", newUsername, newUserPass), NOTSET);  

		testEnvironmentAction.startAnalyzer(EMPTY, 31); 	
		driverViewPageAction.open(EMPTY,NOTSET);
		driverViewPageAction.waitForConnectionToComplete(EMPTY, NOTSET);






		
		String customerName = SQACUS;
		String userName = customerName + testSetup.getFixedSizeRandomNumber(8) + REGBASEUSERNAME;
		String location = customerName + " - " + SQACUSLOC;
		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
		homePage.waitForPageLoad();
		manageUsersPage.open();
		manageUsersPage.waitForPageLoad();
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEDR, location);
		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);
		assertTrue(homePage.checkIfAtHomePage());
		 	}


*/
}
