/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.FleetMapPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.PreferencesPage;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;

import static surveyor.scommon.source.SurveyorConstants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import common.source.Log;

/**
 * @author zlu
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ACLandVisibilityTest extends SurveyorBaseTest {
	private static ManageCustomersPage manageCustomersPage;
	private static ManageUsersPage manageUsersPage;
	private static ComplianceReportsPage complianceReportsPage;
	private static HomePage homePage;
	private static PreferencesPage preferencesPage;
	private static FleetMapPage fleetMapPage;
	private static ManageSurveyorPage surveyorPage;

	@BeforeClass
	public static void setupACLandVisibilityTest() {
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageCustomersPage);
		manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageUsersPage);
		homePage = new HomePage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, homePage);
		preferencesPage = new PreferencesPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, preferencesPage);
		fleetMapPage = new FleetMapPage(driver, testSetup, baseURL);
		PageFactory.initElements(driver, fleetMapPage);
		surveyorPage = new ManageSurveyorPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, surveyorPage);
		complianceReportsPage = new ComplianceReportsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, complianceReportsPage);
	}

	/**
	 * Test Case ID: TC35_CheckACLVCustomerUser_DriverRole Test Description: Check ACLV for customer user with Driver role
	 * 
	 */
	@Test
	public void TC35_CheckACLVCustomerUser_DriverRole() {
		String customerName = SQACUS;
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + testSetup.getFixedSizeRandomNumber(8) + REGBASEUSERNAME;
		String location = customerName + " - " + SQACUSLOC;

		Log.info("\nRunning TC35_CheckACLVCustomerUser_DriverRole - Test Description: Check ACLV for customer user with Driver role");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEDR, location);

		manageCustomersPage.open();
		manageCustomersPage.logout();

		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);

		homePage.open();
		assertTrue(homePage.checkIfAtHomePage());
		assertTrue(homePage.checkVisibilityForCusDR(userName));
		homePage.logout();
	}

	/**
	 * Test Case ID: TC35_CheckACLVCustomerUser_DriverRole_ReleaseNotes Test Description: Release Notes link is working and can download Release Notes
	 * 
	 */
	@Ignore
	public void TC35_CheckACLVCustomerUser_DriverRole_ReleaseNotes() {
		Log.info("\nRunning TC35_CheckACLVCustomerUser_DriverRole_ReleaseNotes - Test Description: Release Notes link is working and can download Release Notes");
		loginPage.open();
		loginPage.loginNormalAs(SQACUSDR, USERPASSWORD);
		homePage.open();
		homePage.waitForPageLoad();
		homePage.getDropDownLoginUser().click();
		homePage.waitForReleaseNotesLinktoLoad();
		assertTrue(homePage.getReleaseNotesLink());

	}

	/**
	 * Test Case ID: TC35_CheckACLVCustomerUser_DriverRole_Preferences Test Description: Preferences link is working
	 * 
	 */
	@Test
	public void TC35_CheckACLVCustomerUser_DriverRole_Preferences() {
		Log.info("\nRunning TC35_CheckACLVCustomerUser_DriverRole_Preferences - Test Description: RPreferences link is working");
		loginPage.open();
		loginPage.loginNormalAs(SQACUSDR, USERPASSWORD);

		homePage.open();
		homePage.waitForPageLoad();
		homePage.getDropDownLoginUser().click();
		homePage.getLinkPreference().click();
		preferencesPage.waitForPageLoad();
		assertTrue(preferencesPage.getSelectedTimeZone().getText().equalsIgnoreCase(TIMEZONE));
		assertTrue(preferencesPage.getSelectedLocation().getText().equalsIgnoreCase(SQACUSLOC));
		homePage.getLinkFleetMap().click();
		fleetMapPage.waitForPageLoad();
		fleetMapPage.waitForFleetMaptoLoad();
		assertTrue(fleetMapPage.checkIfAtFleetMapPage());
		assertTrue(fleetMapPage.getFleetMap().isDisplayed());
		homePage.getLinkSurveyors().click();
		surveyorPage.getTxtSurveyorSearch().sendKeys(SQACUSLOCSUR);
		surveyorPage.waitForDataTabletoLoad();
		assertTrue(surveyorPage.getTableRows().size() > 0);
	}

	/**
	 * Test Case ID: TC36_CheckACLVCustomerUser_SupervisorRole Test Description: Check ACLV for customer user with Supervisor role
	 * 
	 */
	@Test
	public void TC36_CheckACLVCustomerUser_SupervisorRole() {
		String eula = SQACUS + ": " + EULASTRING;
		String userName = SQACUS + testSetup.getFixedSizeRandomNumber(8) + REGBASEUSERNAME;
		String location = SQACUS + " - " + SQACUSLOC;

		Log.info("\nRunning TC36_CheckACLVCustomerUser_SupervisorRole - Test Description: Check ACLV for customer user with Supervisor role");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(SQACUS, userName, USERPASSWORD, CUSUSERROLESU, location);

		ManageCustomersPage manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageCustomersPage);
		manageCustomersPage.open();
		manageCustomersPage.logout();

		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);

		homePage.open();
		assertTrue(homePage.checkIfAtHomePage());
		assertTrue(homePage.checkVisibilityForCusSU(userName));
		homePage.logout();
	}

	/**
	 * Test Case ID: TC36_CheckReportLink_SupervisorRole Test Description: Check Reports link for customer user with Supervisor role
	 * 
	 */

	@Test
	public void TC36_CheckReportLink_SupervisorRole() {
		String rptTitle = "TC36 Report" + testSetup.getRandomNumber();
		Log.info("\nRunning TC36_CheckReportLink_SupervisorRole - Test Description: Report link is working and user is able to view report's menu");
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add(RNELAT);
		listBoundary.add(RNELON);
		listBoundary.add(RSWLAT);
		listBoundary.add(RSWLON);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tableMap.put(KEYASSETCASTIRON, "0");
		tableMap.put(KEYASSETCOPPER, "0");
		tableMap.put(KEYASSETOTHERPLASTIC, "0");
		tableMap.put(KEYASSETPEPLASTIC, "0");
		tableMap.put(KEYASSETPROTECTEDSTEEL, "0");
		tableMap.put(KEYASSETUNPROTECTEDSTEEL, "0");
		tableMap.put(KEYBOUNDARYDISTRICT, "0");
		tableMap.put(KEYBOUNDARYDISTRICTPLAT, "0");
		tablesList.add(tableMap);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap = new HashMap<String, String>();

		viewMap.put(KEYVIEWNAME, "First View");
		viewMap.put(KEYLISA, "1");
		viewMap.put(KEYFOV, "1");
		viewMap.put(KEYBREADCRUMB, "1");
		viewMap.put(KEYINDICATIONS, "1");
		viewMap.put(KEYISOTOPICCAPTURE, "0");
		viewMap.put(KEYANNOTATION, "0");
		viewMap.put(KEYGAPS, "0");
		viewMap.put(KEYASSETS, "0");
		viewMap.put(KEYBOUNDARIES, "0");
		viewMap.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));

		viewList.add(viewMap);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, SQACUSSU, "sqacus", TIMEZONEET, "0", listBoundary, tablesList, "", CUSDRVSTDTAG, "", "", viewList, SurveyModeFilter.Standard);

		complianceReportsPage.addNewReport(rpt);

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSUA))) {
			if (complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findReport(rptTitle, SQACUSUA));
			} else
				fail("\nTC36__CheckReportLink_SupervisorRole: Generate Report as Customer Admin failed.\n");
		} else
			fail("\nTC36__CheckReportLink_SupervisorRole: Generate Report as Customer Admin failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();

		complianceReportsPage.login(SQACUSSU, USERPASSWORD);
		assertTrue(complianceReportsPage.getLinkReportMenu().isDisplayed());
		complianceReportsPage.getLinkReportMenu().click();
		complianceReportsPage.waitForPageToLoad();
		assertTrue(complianceReportsPage.getLinkComplianceReportMenu().isDisplayed());
		complianceReportsPage.getLinkComplianceReportMenu().click();
		complianceReportsPage.waitForPageLoad();
		assertTrue(complianceReportsPage.getBtnNewComplianceRpt().isDisplayed());
		assertTrue(complianceReportsPage.findReport(rptTitle, SQACUSUA));
		if (complianceReportsPage.deleteReport(rptTitle, SQACUSUA))
			assertTrue(!(complianceReportsPage.findReportbySearch(rptTitle, SQACUSUA)));
		else
			fail("\nTC36__CheckReportLink_SupervisorRole: Delete Report as Customer Supervisor failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();

	}

	/**
	 * Test Case ID: TC36_CheckUserLink_ReleaseNotes Test Description: Release Notes link is working and can download Release Notes
	 * 
	 */
	@Ignore
	public void TC36_CheckUserLink_ReleaseNotes() {
		Log.info("\nRunning TC36_CheckReportLink_CustomerSupervisorRole - Test Description: Release Notes link is working and can download Release Notes");
		loginPage.open();
		loginPage.loginNormalAs(SQACUSSU, USERPASSWORD);

		homePage.open();
		homePage.waitForPageLoad();
		homePage.getDropDownLoginUser().click();
		homePage.waitForReleaseNotesLinktoLoad();
		assertTrue(homePage.getReleaseNotesLink());
	}

	/**
	 * Test Case ID: TC36_CheckUserLink_Preferences Test Description: Preferences link is working
	 * 
	 */
	@Test
	public void TC36_CheckUserLink_Preferences() {
		Log.info("\nRunning TC36_CheckReportLink_CustomerSupervisorRole - Test Description: Preferncess link is working");
		loginPage.open();
		loginPage.loginNormalAs(SQACUSSU, USERPASSWORD);
		homePage.open();
		homePage.waitForPageLoad();
		homePage.getDropDownLoginUser().click();
		homePage.getLinkPreference().click();
		preferencesPage.waitForPageLoad();
		assertTrue(preferencesPage.getSelectedTimeZone().getText().equalsIgnoreCase(TIMEZONE));
		assertTrue(preferencesPage.getSelectedLocation().getText().equalsIgnoreCase(SQACUSLOC));
		homePage.getLinkFleetMap().click();
		fleetMapPage.waitForPageLoad();
		fleetMapPage.waitForFleetMaptoLoad();
		assertTrue(fleetMapPage.checkIfAtFleetMapPage());
		assertTrue(fleetMapPage.getFleetMap().isDisplayed());
		homePage.getLinkSurveyors().click();
		surveyorPage.getTxtSurveyorSearch().sendKeys(SQACUSLOCSUR);
		surveyorPage.waitForDataTabletoLoad();
		assertTrue(surveyorPage.getTableRows().size() > 0);
	}

	/**
	 * Test Case ID: TC37_CheckACLVCustomerUser_UtilityAdminRole Test Description: Check ACLV for customer user with Utility Administrator role
	 * 
	 */
	@Test
	public void TC37_CheckACLVCustomerUser_UtilityAdminRole() {
		String eula = SQACUS + ": " + EULASTRING;
		String userName = SQACUS + testSetup.getFixedSizeRandomNumber(8) + REGBASEUSERNAME;
		String location = SQACUS + " - " + SQACUSLOC;

		Log.info("\nRunning TC37_CheckACLVCustomerUser_UtilityAdminRole - Test Description: Check ACLV for customer user with Utility Administrator role");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(SQACUS, userName, USERPASSWORD, CUSUSERROLEUA, location);

		manageCustomersPage.open();
		manageCustomersPage.logout();

		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);

		homePage.open();
		assertTrue(homePage.checkIfAtHomePage());
		assertTrue(homePage.checkVisibilityForCusUA(userName));
		homePage.logout();
	}

	/**
	 * Test Case ID: TC37_CheckReportLink_UtilityAdminRole Test Description: Check ACLV for customer user with Utility Administrator role
	 * 
	 */
	@Test
	public void TC37_CheckReportLink_UtilityAdminRole() {
		String rptTitle = "TC37 Report" + testSetup.getRandomNumber();
		Log.info("\nRunning TC37_CheckReportLink_UtilityAdminRole - Test Description: Report link is working and user is able to view report's menu");
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add(RNELAT);
		listBoundary.add(RNELON);
		listBoundary.add(RSWLAT);
		listBoundary.add(RSWLON);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tableMap.put(KEYASSETCASTIRON, "0");
		tableMap.put(KEYASSETCOPPER, "0");
		tableMap.put(KEYASSETOTHERPLASTIC, "0");
		tableMap.put(KEYASSETPEPLASTIC, "0");
		tableMap.put(KEYASSETPROTECTEDSTEEL, "0");
		tableMap.put(KEYASSETUNPROTECTEDSTEEL, "0");
		tableMap.put(KEYBOUNDARYDISTRICT, "0");
		tableMap.put(KEYBOUNDARYDISTRICTPLAT, "0");
		tablesList.add(tableMap);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap = new HashMap<String, String>();

		viewMap.put(KEYVIEWNAME, "First View");
		viewMap.put(KEYLISA, "1");
		viewMap.put(KEYFOV, "1");
		viewMap.put(KEYBREADCRUMB, "1");
		viewMap.put(KEYINDICATIONS, "1");
		viewMap.put(KEYISOTOPICCAPTURE, "0");
		viewMap.put(KEYANNOTATION, "0");
		viewMap.put(KEYGAPS, "0");
		viewMap.put(KEYASSETS, "0");
		viewMap.put(KEYBOUNDARIES, "0");
		viewMap.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));

		viewList.add(viewMap);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, SQACUSUA, "sqacus", TIMEZONEET, "0", listBoundary, tablesList, "", CUSDRVSTDTAG, "", "", viewList, SurveyModeFilter.Standard);

		complianceReportsPage.addNewReport(rpt);

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSUA))) {
			if (complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findReport(rptTitle, SQACUSUA));
			} else
				fail("\nTC37__CheckReportLink_UtilityAdminRole: Generate Report failed.\n");
		} else
			fail("\nTC37__CheckReportLink_UtilityAdminRole: Generate Report failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();

		complianceReportsPage.login(SQACUSUA, USERPASSWORD);
		assertTrue(complianceReportsPage.getLinkReportMenu().isDisplayed());
		complianceReportsPage.getLinkReportMenu().click();
		complianceReportsPage.waitForPageToLoad();
		assertTrue(complianceReportsPage.getLinkComplianceReportMenu().isDisplayed());
		complianceReportsPage.getLinkComplianceReportMenu().click();
		complianceReportsPage.waitForPageLoad();
		assertTrue(complianceReportsPage.getBtnNewComplianceRpt().isDisplayed());
		assertTrue(complianceReportsPage.findReport(rptTitle, SQACUSUA));
		if (complianceReportsPage.deleteReport(rptTitle, SQACUSUA))
			assertTrue(!(complianceReportsPage.findReportbySearch(rptTitle, SQACUSUA)));
		else
			fail("\nTC37__CheckReportLink_SupervisorRole: Delete Report as Customer Supervisor failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();

	}

	/**
	 * Test Case ID: TC37_CheckUserLink_ReleaseNotes Test Description: Release Notes link is working and can download Release Notes
	 * 
	 */
	@Ignore
	public void TC37_CheckUserLink_ReleaseNotes() {
		Log.info("\nRunning TC37_CheckReportLink_UtilityAdminRole - Test Description: Release Notes link is working and can download Release Notes");
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		homePage.open();
		homePage.waitForPageLoad();
		homePage.getDropDownLoginUser().click();
		homePage.waitForReleaseNotesLinktoLoad();
		assertTrue(homePage.getReleaseNotesLink());

	}

	/**
	 * Test Case ID: TC37_CheckUserLink_Preferences Test Description: Preferences link is working
	 * 
	 */
	@Test
	public void TC37_CheckUserLink_Preferences() {
		Log.info("\nRunning TC37_CheckReportLink_UtilityAdminRole - Test Description: Prefernces link");
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);

		homePage.open();
		homePage.waitForPageLoad();
		homePage.getDropDownLoginUser().click();
		homePage.getLinkPreference().click();

		preferencesPage.waitForPageLoad();
		assertTrue(preferencesPage.getSelectedTimeZone().getText().equalsIgnoreCase(TIMEZONE));
		assertTrue(preferencesPage.getSelectedLocation().getText().equalsIgnoreCase(SQACUSLOC));
		homePage.getLinkFleetMap().click();

		fleetMapPage.waitForPageLoad();
		fleetMapPage.waitForFleetMaptoLoad();
		assertTrue(fleetMapPage.checkIfAtFleetMapPage());
		assertTrue(fleetMapPage.getFleetMap().isDisplayed());
		homePage.getLinkSurveyors().click();
		surveyorPage.waitForPageLoad();
		surveyorPage.getTxtSurveyorSearch().sendKeys(SQACUSLOCSUR);
		surveyorPage.waitForDataTabletoLoad();
		assertTrue(surveyorPage.getTableRows().size() > 0);
	}

	/**
	 * Test Case ID: TC38_CheckACLV_PicAdminRole Test Description: Check ACLV for Picarro Administrator role, non-default Administrator account
	 * 
	 */
	@Test
	public void TC38_CheckACLV_PicAdminRole() {
		String userName = PICNAMEPREFIX + "ad" + testSetup.getRandomNumber() + REGBASEPICUSERNAME;
		String customer = "Picarro";
		String location = "Santa Clara";
		String locationDesc = customer + " - " + location;
		
		Log.info("\nRunning TC38_CheckACLV_PicAdminRole - Test Description: Check ACLV for Picarro Administrator role, non-default Administrator account\n");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		manageUsersPage.open();
		
		Log.info(String.format("Adding new Picarro user: Username-[%s];Role-[%s];Location-[%s];Timezone-[%s]", 
				userName, USERROLEADMIN, locationDesc, TIMEZONECT));		
		manageUsersPage.addNewPicarroUser(userName, USERPASSWORD, USERROLEADMIN, locationDesc, TIMEZONECT);
		
		Log.info(String.format("Finding user: Location-[%s];Username-[%s]", location, userName));
		if (!manageUsersPage.findExistingUser(location, userName, false))
			fail("\nTC38: failed to create a non-default Picarro Administrator user.\n");
		manageUsersPage.logout();

		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);

		homePage.open();
		assertTrue(homePage.checkVisibilityForPicarroAdministrator(userName));
		homePage.logout();
	}

	/**
	 * Test Case ID: TC38_CheckReportLink_PicarroAdminRole Test Description: Check ACLV for customer user with Utility Administrator role
	 * 
	 */
	@Test
	public void TC38_CheckReportLink_PicarroAdminRole() {
		String rptTitle = "TC38 Report" + testSetup.getRandomNumber();
		Log.info("\nRunning TC38_CheckReportLink_PicarroAdminRole - Test Description: Report link is working and user is able to view report's menu");
		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add(RNELAT);
		listBoundary.add(RNELON);
		listBoundary.add(RSWLAT);
		listBoundary.add(RSWLON);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tableMap.put(KEYASSETCASTIRON, "0");
		tableMap.put(KEYASSETCOPPER, "0");
		tableMap.put(KEYASSETOTHERPLASTIC, "0");
		tableMap.put(KEYASSETPEPLASTIC, "0");
		tableMap.put(KEYASSETPROTECTEDSTEEL, "0");
		tableMap.put(KEYASSETUNPROTECTEDSTEEL, "0");
		tableMap.put(KEYBOUNDARYDISTRICT, "0");
		tableMap.put(KEYBOUNDARYDISTRICTPLAT, "0");
		tablesList.add(tableMap);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap = new HashMap<String, String>();

		viewMap.put(KEYVIEWNAME, "First View");
		viewMap.put(KEYLISA, "1");
		viewMap.put(KEYFOV, "1");
		viewMap.put(KEYBREADCRUMB, "1");
		viewMap.put(KEYINDICATIONS, "1");
		viewMap.put(KEYISOTOPICCAPTURE, "0");
		viewMap.put(KEYANNOTATION, "0");
		viewMap.put(KEYGAPS, "0");
		viewMap.put(KEYASSETS, "0");
		viewMap.put(KEYBOUNDARIES, "0");
		viewMap.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));

		viewList.add(viewMap);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, PICDFADMIN, "sqacus", TIMEZONEET, "0", listBoundary, tablesList, "", CUSDRVSTDTAG, "", "", viewList, SurveyModeFilter.Standard);

		complianceReportsPage.addNewReport(rpt);

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN))) {
			if (complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findReport(rptTitle, PICDFADMIN));
			} else
				fail("\nTC37__CheckReportLink_PicarroAdminRole: Generate Report failed.\n");
		} else
			fail("\nTC37__CheckReportLink_PicarroAdminRole: Generate Report failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();

		complianceReportsPage.login(PICDFADMIN, PICADMINPSWD);
		assertTrue(complianceReportsPage.getLinkReportMenu().isDisplayed());
		complianceReportsPage.getLinkReportMenu().click();
		complianceReportsPage.waitForPageToLoad();
		assertTrue(complianceReportsPage.getLinkComplianceReportMenu().isDisplayed());
		complianceReportsPage.getLinkComplianceReportMenu().click();
		complianceReportsPage.waitForPageLoad();
		assertTrue(complianceReportsPage.getBtnNewComplianceRpt().isDisplayed());
		assertTrue(complianceReportsPage.findReport(rptTitle, PICDFADMIN));
		if (complianceReportsPage.deleteReport(rptTitle, PICDFADMIN))
			assertTrue(!(complianceReportsPage.findReportbySearch(rptTitle, PICDFADMIN)));
		else
			fail("\nTC37__CheckReportLink_PicarroAdminRole: Delete Report as Picarro Admin failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();

	}

	/**
	 * Test Case ID: TC38_CheckUserLink_ReleaseNotes Test Description: Release Notes link is working and can download Release Notes
	 * 
	 */
	@Ignore
	public void TC38_CheckUserLink_ReleaseNotes_PicarroAdminRole() {
		Log.info("\nRunning TC38_CheckReportLink_PicarroAdminRole - Test Description: Release Notes link is working and can download Release Notes");
		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		homePage.open();
		homePage.waitForPageLoad();
		homePage.getDropDownLoginUser().click();
		homePage.waitForReleaseNotesLinktoLoad();

		homePage.waitForReleaseNotesLinktoLoad();
		assertTrue(homePage.getReleaseNotesLink());

	}

	/**
	 * Test Case ID: TC38_CheckUserLink_Preferences Test Description: Preferences link is working
	 * 
	 */
	@Test
	public void TC38_CheckUserLink_Preferences() {
		Log.info("\nRunning TC38_CheckReportLink_UtilityAdminRole - Test Description: Prefernces link is working");
		loginPage.open();
		loginPage.loginNormalAs(PICDFADMIN, PICADMINPSWD);

		homePage.open();

		homePage.open();
		homePage.waitForPageLoad();
		homePage.getDropDownTimeZone().click();
		homePage.waitForPageLoad();
		homePage.setTimeZoneToPST();
		homePage.waitForPageLoad();
		homePage.getDropDownLoginUser().click();
		homePage.getLinkPreference().click();
		preferencesPage.waitForPageLoad();

		assertTrue(preferencesPage.getSelectedTimeZone().getText().equalsIgnoreCase(TIMEZONE));
		assertTrue(preferencesPage.getSelectedLocation().getText().equalsIgnoreCase(SQACUSSULOC));
		homePage.getLinkFleetMap().click();

		fleetMapPage.waitForPageLoad();
		fleetMapPage.waitForFleetMaptoLoad();
		assertTrue(fleetMapPage.checkIfAtFleetMapPage());
		assertTrue(fleetMapPage.getFleetMap().isDisplayed());
		homePage.getLinkSurveyors().click();
		surveyorPage.waitForPageLoad();
		surveyorPage.getTxtSurveyorSearch().sendKeys(SQACUSLOCSUR);
		surveyorPage.waitForDataTabletoLoad();
		assertTrue(surveyorPage.getTableRows().size() > 0);
	}

	/**
	 * Test Case ID: TC40_CheckReportLink_PicarroSupportRole Test Description: Check ACLV for Picarro Support role
	 * 
	 */
	@Test
	public void TC40_CheckReportLink_PicarroSupportRole() {
		String rptTitle = "TC40 Report" + testSetup.getRandomNumber();
		Log.info("\nRunning TC40_CheckReportLink_PicarroSupportRole - Test Description: Report link is working and user is able to view report's menu");
		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);

		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add(RNELAT);
		listBoundary.add(RNELON);
		listBoundary.add(RSWLAT);
		listBoundary.add(RSWLON);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tableMap.put(KEYASSETCASTIRON, "0");
		tableMap.put(KEYASSETCOPPER, "0");
		tableMap.put(KEYASSETOTHERPLASTIC, "0");
		tableMap.put(KEYASSETPEPLASTIC, "0");
		tableMap.put(KEYASSETPROTECTEDSTEEL, "0");
		tableMap.put(KEYASSETUNPROTECTEDSTEEL, "0");
		tableMap.put(KEYBOUNDARYDISTRICT, "0");
		tableMap.put(KEYBOUNDARYDISTRICTPLAT, "0");
		tablesList.add(tableMap);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap = new HashMap<String, String>();

		viewMap.put(KEYVIEWNAME, "First View");
		viewMap.put(KEYLISA, "1");
		viewMap.put(KEYFOV, "1");
		viewMap.put(KEYBREADCRUMB, "1");
		viewMap.put(KEYINDICATIONS, "1");
		viewMap.put(KEYISOTOPICCAPTURE, "0");
		viewMap.put(KEYANNOTATION, "0");
		viewMap.put(KEYGAPS, "0");
		viewMap.put(KEYASSETS, "0");
		viewMap.put(KEYBOUNDARIES, "0");
		viewMap.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));

		viewList.add(viewMap);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, SQAPICSUP, "sqacus", TIMEZONEET, "0", listBoundary, tablesList, "", CUSDRVSTDTAG, "", "", viewList, SurveyModeFilter.Standard);

		complianceReportsPage.addNewReport(rpt);

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQAPICSUP))) {
			if (complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findReport(rptTitle, SQAPICSUP));
			} else
				fail("\nTC40__CheckReportLink_PicarroSupportRole: Generate Report failed.\n");
		} else
			fail("\nTC40__CheckReportLink_PicarroSupportRole: Generate Report failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();

		complianceReportsPage.login(SQAPICSUP, USERPASSWORD);
		assertTrue(complianceReportsPage.getLinkReportMenu().isDisplayed());
		complianceReportsPage.getLinkReportMenu().click();
		complianceReportsPage.waitForPageToLoad();
		assertTrue(complianceReportsPage.getLinkComplianceReportMenu().isDisplayed());
		complianceReportsPage.getLinkComplianceReportMenu().click();
		complianceReportsPage.waitForPageLoad();
		assertTrue(complianceReportsPage.getBtnNewComplianceRpt().isDisplayed());
		assertTrue(complianceReportsPage.findReport(rptTitle, SQAPICSUP));
		if (complianceReportsPage.deleteReport(rptTitle, SQAPICSUP))
			assertTrue(!(complianceReportsPage.findReportbySearch(rptTitle, SQAPICSUP)));
		else
			fail("\nTC40__CheckReportLink_SupervisorRole: Delete Report as Picarro Support failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();

	}

	/**
	 * Test Case ID: TC40_CheckUserLink_ReleaseNotes Test Description: Release Notes link is working and can download Release Notes
	 * 
	 */
	@Ignore
	public void TC40_CheckUserLink_ReleaseNotes_PicarroSupportRole() {
		Log.info("\nRunning TC40_CheckReportLink_PicarroSupportRole - Test Description: Release Notes link is working and can download Release Notes");
		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);

		homePage.open();
		homePage.waitForPageLoad();
		homePage.getDropDownLoginUser().click();

		homePage.waitForReleaseNotesLinktoLoad();
		assertTrue(homePage.getReleaseNotesLink());

	}

	/**
	 * Test Case ID: TC40_CheckUserLink_Preferences Test Description: Preferences link is working
	 * 
	 */
	@Test
	public void TC40_CheckUserLink_Preferences() {
		Log.info("\nRunning TC38_CheckReportLink_PicarroSupportRole - Test Description: Prefernces link is working");
		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);

		homePage.open();
		homePage.waitForPageLoad();
		homePage.getDropDownTimeZone().click();
		homePage.waitForPageLoad();
		homePage.setTimeZoneToPST();
		homePage.waitForPageLoad();
		homePage.getDropDownLoginUser().click();
		homePage.getLinkPreference().click();

		preferencesPage.waitForPageLoad();

		assertTrue(preferencesPage.getSelectedTimeZone().getText().equalsIgnoreCase(TIMEZONE));
		assertTrue(preferencesPage.getSelectedLocation().getText().equalsIgnoreCase(SQACUSSULOC));
		homePage.getLinkFleetMap().click();

		fleetMapPage.waitForPageLoad();
		fleetMapPage.waitForFleetMaptoLoad();
		assertTrue(fleetMapPage.checkIfAtFleetMapPage());
		assertTrue(fleetMapPage.getFleetMap().isDisplayed());
		homePage.getLinkSurveyors().click();
		surveyorPage.waitForPageLoad();
		surveyorPage.getTxtSurveyorSearch().sendKeys(PICADMNSURVEYORSHORT);
		surveyorPage.waitForDataTabletoLoad();
		assertTrue(surveyorPage.getTableRows().size() > 0);
	}

	/**
	 * Test Case ID: TC40_CheckACLV_PicSupportRole Test Description: Check ACLV for Picarro user with Picarro Support role
	 * 
	 */
	@Test
	public void TC40_CheckACLV_PicSupportRole() {
		String userName = PICNAMEPREFIX + "su" + testSetup.getRandomNumber() + REGBASEPICUSERNAME;
		String customer = "Picarro";
		String location = "Santa Clara";
		String locationDesc = customer + " - " + location;

		Log.info("\nRunning TC40_CheckACLV_PicSupportRole - Test Description: Check ACLV for Picarro user with Picarro Support role\n");

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());

		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageUsersPage);

		Log.info("Creating Picarro support user with username-" + userName);

		manageUsersPage.open();
		
		Log.info(String.format("Adding new Picarro user: Username-[%s];Role-[%s];Location-[%s];Timezone-[%s]", 
				userName, PICUSERROLESUP, locationDesc, TIMEZONECT));
		manageUsersPage.addNewPicarroUser(userName, USERPASSWORD, PICUSERROLESUP, locationDesc, TIMEZONECT);
		
		Log.info(String.format("Finding user: Location-[%s];Username-[%s]", location, userName));
		if (!manageUsersPage.findExistingUser(location, userName, false))
			fail("\nTC40: failed to create a Picarro user with Picarro Support role.\n");
		manageUsersPage.logout();

		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);

		homePage.open();
		assertTrue(homePage.checkVisibilityForPicarroSUP(userName));
		homePage.logout();
	}
}