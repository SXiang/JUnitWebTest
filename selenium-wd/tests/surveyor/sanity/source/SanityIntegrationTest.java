/**
 * 
 */
package surveyor.sanity.source;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.CUSNAMEBASE;
import static surveyor.scommon.source.SurveyorConstants.KEYANNOTATION;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYBASEMAP;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARIES;
import static surveyor.scommon.source.SurveyorConstants.KEYBREADCRUMB;
import static surveyor.scommon.source.SurveyorConstants.KEYFOV;
import static surveyor.scommon.source.SurveyorConstants.KEYGAPS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDICATIONS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDTB;
import static surveyor.scommon.source.SurveyorConstants.KEYISOANA;
import static surveyor.scommon.source.SurveyorConstants.KEYISOTOPICCAPTURE;
import static surveyor.scommon.source.SurveyorConstants.KEYLISA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCRA;
import static surveyor.scommon.source.SurveyorConstants.KEYVIEWNAME;
import static surveyor.scommon.source.SurveyorConstants.PICADMINPSWD;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDRTAG;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDRUSER;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOCSUR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUAUSER;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.ManageAnalyzersPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageRefGasBottlesPage;
import surveyor.scommon.source.ManageReleaseNotesPage;
import surveyor.scommon.source.ManageSurveyorHistoriesPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.SurveyorBaseTest;

public class SanityIntegrationTest extends SurveyorBaseTest {
	private static ComplianceReportsPage complianceReportsPage;
	private static ManageCustomersPage manageCustomersPage;
	private static ManageUsersPage manageUsersPage;
	private static ManageLocationsPage manageLocationsPage;
	private static ManageSurveyorPage manageSurveyorsPage;
	private static ManageAnalyzersPage manageAnalyzersPage;
	private static ManageRefGasBottlesPage manageRefGasBottlesPage;
	private static ManageSurveyorHistoriesPage manageSurveyorHistoriesPage;
	private static ManageReleaseNotesPage manageReleaseNotesPage;

	@BeforeClass
	public static void SetupSanityIntegrationTest() {
		complianceReportsPage = new ComplianceReportsPage(driver, baseURL,
				testSetup);
		PageFactory.initElements(driver, complianceReportsPage);

		manageCustomersPage = new ManageCustomersPage(driver, baseURL,
				testSetup);
		PageFactory.initElements(driver, manageCustomersPage);

		manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageUsersPage);

		manageLocationsPage = new ManageLocationsPage(driver, baseURL,
				testSetup);
		PageFactory.initElements(driver, manageLocationsPage);

		manageSurveyorsPage = new ManageSurveyorPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageSurveyorsPage);

		manageAnalyzersPage = new ManageAnalyzersPage(driver, baseURL,
				testSetup);
		PageFactory.initElements(driver, manageAnalyzersPage);

		manageRefGasBottlesPage = new ManageRefGasBottlesPage(driver,
				testSetup, baseURL);
		PageFactory.initElements(driver, manageRefGasBottlesPage);

		manageSurveyorHistoriesPage = new ManageSurveyorHistoriesPage(driver,
				baseURL, testSetup);
		PageFactory.initElements(driver, manageSurveyorHistoriesPage);

		manageReleaseNotesPage = new ManageReleaseNotesPage(driver, baseURL,
				testSetup);
		PageFactory.initElements(driver, manageReleaseNotesPage);
	}

	@Test
	public void TC25_LoginTest_PicarroAdmin() {
		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);
		homePage.open();
	
		assertTrue(PICDFADMIN + " user login unsuccessful!",
				homePage.checkIfAtHomePage());
		loginPage = homePage.logout();
	}

	@Test
	public void TC25_LoginTest_CustomerAdmin() {
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		homePage.open();
		assertTrue(SQACUSUAUSER + " user login unsuccessful!",
				homePage.checkIfAtHomePage());
		loginPage = homePage.logout();
	}

	@Test
	public void TC25_LoginTest_CustomerDriver() {
		loginPage.open();
		loginPage.loginNormalAs(SQACUSDR, USERPASSWORD);

		homePage.open();
		assertTrue(SQACUSDRUSER + " user login unsuccessful!",
				homePage.checkIfAtHomePage());
		loginPage = homePage.logout();
	}

	/**
	 * Test Case ID: TC739 Test Description: Generate compliance report as
	 * customer supervisor user by selecting report area using custom boundary
	 * 
	 */
	@Test
	public void TC739_GenerateComplianceReport_CustomerSupervisor() {
		String rptTitle = "Customer Supervisor Report TC739 "
				+ testSetup.getRandomNumber();
		System.out
				.format("\nRunning TC739: Generate compliance report as customer supervisor user by selecting report area using custom boundary, %s\n",
						rptTitle);

		complianceReportsPage.login(SQACUSSU, USERPASSWORD);
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add("8.5");
		listBoundary.add("11");
		listBoundary.add("37.42152227696863");
		listBoundary.add("-121.93495276739418");
		listBoundary.add("37.4128647496036");
		listBoundary.add("-121.94370749761877");

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();
		Map<String, String> viewMap2 = new HashMap<String, String>();
		Map<String, String> viewMap3 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "0");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));

		viewMap2.put(KEYVIEWNAME, "Second View");
		viewMap2.put(KEYLISA, "0");
		viewMap2.put(KEYFOV, "0");
		viewMap2.put(KEYBREADCRUMB, "1");
		viewMap2.put(KEYINDICATIONS, "1");
		viewMap2.put(KEYISOTOPICCAPTURE, "0");
		viewMap2.put(KEYANNOTATION, "0");
		viewMap2.put(KEYGAPS, "1");
		viewMap2.put(KEYASSETS, "0");
		viewMap2.put(KEYBOUNDARIES, "0");
		viewMap2.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));

		viewMap3.put(KEYVIEWNAME, "Third View");
		viewMap3.put(KEYLISA, "1");
		viewMap3.put(KEYFOV, "1");
		viewMap3.put(KEYBREADCRUMB, "0");
		viewMap3.put(KEYINDICATIONS, "0");
		viewMap3.put(KEYISOTOPICCAPTURE, "1");
		viewMap3.put(KEYANNOTATION, "1");
		viewMap3.put(KEYGAPS, "0");
		viewMap3.put(KEYASSETS, "0");
		viewMap3.put(KEYBOUNDARIES, "0");
		viewMap3.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);
		viewList.add(viewMap2);
		viewList.add(viewMap3);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "1");
		tablesList.add(tableMap);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, SQACUSSU,
				CUSNAMEBASE, TIMEZONEPT, "0", listBoundary, tablesList,
				SQACUSLOCSUR, SQACUSDRTAG, viewList);
		complianceReportsPage.addNewReport(rpt);

		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSSU))) {
			assertTrue(complianceReportsPage.findReport(rptTitle, SQACUSSU));
			assertTrue(complianceReportsPage.validatePdfFiles(rpt,
					testSetup.getDownloadPath()));
		} else
			fail("\nTestcase TC739 failed." + rptTitle
					+ " report failed to generate by " + SQACUSSU + " user!!\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC519 
	 * Test Description: Check links with the page.
	 * 
	 */
	@Test
	public void TC519_CheckBrokenPages() {
		loginPage.open();
		assertTrue(loginPage.isLinkBroken());

		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);
		assertTrue(homePage.isLinkBroken());

		homePage.clickOnDrivingSurveyLink();
		assertTrue(homePage.isLinkBroken());

		homePage.clickOnSurveyorsLink();
		assertTrue(homePage.isLinkBroken());

		homePage.clickOnFleetMapLink();
		assertTrue(homePage.isLinkBroken());

		homePage.clickOnFeedbackLink();
		assertTrue(homePage.isLinkBroken());

		homePage.clickOnReportsLink();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		homePage.clickOnComplianceReportLink();
		assertTrue(complianceReportsPage.isLinkBroken());

		// homePage.clickOnEQReportLink();
		// assertTrue(homePage.isLinkBroken());

		homePage.clickOnReferenceGasReportLink();
		assertTrue(homePage.isLinkBroken());

		homePage.clickOnSystemHistoryReportLink();
		assertTrue(homePage.isLinkBroken());

		homePage.clickOnPicarroAdminLink();
		testSetup.slowdownInSeconds(testSetup.getSlowdownInSeconds());
		homePage.clickOnCalibrationLink();
		assertTrue(homePage.isLinkBroken());

		homePage.clickOnViewUserFeedbackLink();
		assertTrue(homePage.isLinkBroken());

		homePage.clickOnManageCustomersLink();
		assertTrue(manageCustomersPage.isLinkBroken());
		manageCustomersPage.clickOnAddNewCustomerBtn();
		assertTrue(manageCustomersPage.isLinkBroken());
		manageCustomersPage.clickOnCancelBtn();
		manageCustomersPage.waitForPageToLoad();
		manageCustomersPage.clickOnFirstEditCustomerBtn();
		assertTrue(manageCustomersPage.isLinkBroken());
		manageCustomersPage.clickOnCancelBtn();
		
		manageCustomersPage.waitForPageToLoad();

		homePage.clickOnManageUsersLink();
		assertTrue(manageUsersPage.isLinkBroken());
		manageUsersPage.clickOnAddNewPicarroUserBtn();
		assertTrue(manageUsersPage.isLinkBroken());
		manageUsersPage.clickOnCancelAddBtn();
		manageCustomersPage.waitForPageToLoad();
		manageUsersPage.clickOnAddNewCustomerUserBtn();
		assertTrue(manageUsersPage.isLinkBroken());
		manageUsersPage.clickOnCancelAddBtn();
		manageCustomersPage.waitForPageToLoad();
		manageUsersPage.clickOnFirstEditUserBtn();
		assertTrue(manageUsersPage.isLinkBroken());
		manageUsersPage.clickOnCancelEditBtn();

		manageUsersPage.waitForPageToLoad();
		
		homePage.clickOnManageLocationsLink();
		assertTrue(manageLocationsPage.isLinkBroken());
		manageLocationsPage.clickOnAddNewLocationBtn();
		assertTrue(manageLocationsPage.isLinkBroken());
		manageLocationsPage.clickOnCancelBtn();
		manageCustomersPage.waitForPageToLoad();
		manageLocationsPage.clickOnFirstEditLocationBtn();
		assertTrue(manageLocationsPage.isLinkBroken());
		manageLocationsPage.clickOnCancelBtn();
		
		manageLocationsPage.waitForPageToLoad();
		
		homePage.clickOnManageSurveyorsLink();
		assertTrue(manageSurveyorsPage.isLinkBroken());
		manageSurveyorsPage.clickOnAddNewSurveyorBtn();
		assertTrue(manageSurveyorsPage.isLinkBroken());
		manageSurveyorsPage.clickOnAddCancelBtn();
		manageCustomersPage.waitForPageToLoad();
		manageSurveyorsPage.clickOnFirstEditSurveyorBtn();
		assertTrue(manageSurveyorsPage.isLinkBroken());
		manageSurveyorsPage.clickOnEditCancelBtn();
		
		manageSurveyorsPage.waitForPageToLoad();

		homePage.clickOnManageAnalyzersLink();
		assertTrue(manageAnalyzersPage.isLinkBroken());
		manageAnalyzersPage.clickOnAddNewAnalyzerBtn();
		assertTrue(manageAnalyzersPage.isLinkBroken());
		manageAnalyzersPage.clickOnCancelBtn();
		manageCustomersPage.waitForPageToLoad();
		manageAnalyzersPage.clickOnFirstEditAnalyzerBtn();
		assertTrue(manageAnalyzersPage.isLinkBroken());
		manageAnalyzersPage.clickOnCancelBtn();
		
		manageAnalyzersPage.waitForPageToLoad();

		homePage.clickOnManageRefGasBottlesLink();
		assertTrue(manageRefGasBottlesPage.isLinkBroken());
		manageRefGasBottlesPage.clickOnAddNewRefGasBottleBtn();
		assertTrue(manageRefGasBottlesPage.isLinkBroken());
		manageRefGasBottlesPage.clickOnCancelBtn();
		
		manageRefGasBottlesPage.waitForPageToLoad();

		homePage.clickOnManageSurveyorHistoriesLink();
		assertTrue(manageSurveyorHistoriesPage.isLinkBroken());
		manageSurveyorHistoriesPage.clickOnAddNewHistoryEntryBtn();
		assertTrue(manageSurveyorHistoriesPage.isLinkBroken());
		manageSurveyorHistoriesPage.clickOnCancelBtn();
		
		manageSurveyorHistoriesPage.waitForPageToLoad();

		/* Commenting, while change is being deployed to P3SQA.
		homePage.clickOnManageReleaseNotesLink();
		assertTrue(manageReleaseNotesPage.isLinkBroken());
		manageReleaseNotesPage.clickOnAddNewReleaseNoteBtn();
		assertTrue(manageReleaseNotesPage.isLinkBroken());
		manageReleaseNotesPage.clickOnCancelBtn();
		*/
		
		homePage.clickOnViewAnalyzerLogsLink(baseURL);
		assertTrue(homePage.isLinkBroken());

		homePage.clickOnViewServerlogsLink(baseURL);
		assertTrue(homePage.isLinkBroken());
	}
}