/**
 *
 */
package surveyor.regression.source;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.actions.PageActionsStore;
import surveyor.scommon.entities.ComplianceReportEntity;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.EQReportsPage;
import surveyor.scommon.source.FacilityEQReportsPage;
import surveyor.scommon.source.FleetMapPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.PreferencesPage;
import surveyor.scommon.source.ReferenceGasReportsPage;
import surveyor.scommon.source.SurveyViewPage;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorSystemsPage;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.SystemHistoryReportsPage;
import surveyor.scommon.source.AssessmentReportsPage;
import surveyor.scommon.source.BaseMapViewPage.GisSwitchType;
import surveyor.scommon.source.MeasurementSessionsPage.UserRoleType;

import static surveyor.scommon.source.SurveyorConstants.*;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import common.source.Log;
import common.source.WebElementExtender;

/**
 * @author zlu
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ACLandVisibilityTest extends SurveyorBaseTest {
	private static final Integer REPORT_MENU_DISPLAY_TIMEOUT = 60;
	private static HomePage homePage;
	private static LoginPage loginPage;
	private static SurveyorSystemsPage surveyorPage;
	private static MeasurementSessionsPage measurementSessionsPage;
	private static SurveyViewPage surveyViewPage;
	private static PreferencesPage preferencesPage;
	private static FleetMapPage fleetMapPage;
	private static ManageCustomersPage manageCustomersPage;
	private static ManageUsersPage manageUsersPage;
	private static EQReportsPage eqReportsPage;
	private static FacilityEQReportsPage facilityEQReportsPage;
	private static AssessmentReportsPage assessmentReportsPage;
	private static ReferenceGasReportsPage referenceGasReportsPage;
	private static SystemHistoryReportsPage systemHistoryReportsPage;
	private static ComplianceReportsPage complianceReportsPage;
	private static List<String> strListTagCus = null;
	private static List<String> strListTagPic = null;
	/**
	 * This method is called by the 'main' thread
	 */
	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects(); // ensures TestSetup and TestContext are initialized before Page object creation.
	}

	/**
	 * This method is called by the 'worker' thread
	 *
	 * @throws java.lang.Exception
	 */
	@Before
	public void beforeTest() throws Exception {
		Log.info("[THREAD Debug Log] - Calling setup beforeTest()");
		PageActionsStore.INSTANCE.clearStore();

		initializeTestObjects();
		PageObjectFactory pageObjectFactory = new PageObjectFactory();
		homePage = pageObjectFactory.getHomePage();
		PageFactory.initElements(getDriver(), homePage);
		loginPage = pageObjectFactory.getLoginPage();
		PageFactory.initElements(getDriver(), loginPage);
		surveyorPage = pageObjectFactory.getSurveyorSystemsPage();
		PageFactory.initElements(getDriver(), surveyorPage);
		measurementSessionsPage = pageObjectFactory.getMeasurementSessionsPage();
		PageFactory.initElements(getDriver(), measurementSessionsPage);
		surveyViewPage = pageObjectFactory.getSurveyViewPage();
		PageFactory.initElements(getDriver(), surveyViewPage);
		
		preferencesPage = pageObjectFactory.getPreferencesPage();
		PageFactory.initElements(getDriver(), preferencesPage);
		fleetMapPage = pageObjectFactory.getFleetMapPage();
		PageFactory.initElements(getDriver(), fleetMapPage);
		manageCustomersPage = pageObjectFactory.getManageCustomersPage();
		PageFactory.initElements(getDriver(), manageCustomersPage);
		manageUsersPage = pageObjectFactory.getManageUsersPage();
		PageFactory.initElements(getDriver(), manageUsersPage);

		eqReportsPage = pageObjectFactory.getEqReportsPage();
		PageFactory.initElements(getDriver(), eqReportsPage);
		facilityEQReportsPage = pageObjectFactory.getFacilityEQReportsPage();
		PageFactory.initElements(getDriver(), facilityEQReportsPage);
		assessmentReportsPage = pageObjectFactory.getAssessmentReportsPage();
		PageFactory.initElements(getDriver(), assessmentReportsPage);
		systemHistoryReportsPage = pageObjectFactory.getSystemHistoryReportsPage();
		PageFactory.initElements(getDriver(), systemHistoryReportsPage);
		referenceGasReportsPage = pageObjectFactory.getReferenceGasReportsPage();
		PageFactory.initElements(getDriver(), referenceGasReportsPage);
		complianceReportsPage = pageObjectFactory.getComplianceReportsPage();
		PageFactory.initElements(getDriver(), complianceReportsPage);

		strListTagCus = new ArrayList<String>();
		strListTagPic = new ArrayList<String>();
		strListTagCus.add(CUSDRVSTDTAG2);
		strListTagCus.add(CUSDRVRRTAG2);
		strListTagCus.add(CUSDRVOPTAG2);
		strListTagPic.add(PICADMNSTDTAG2);
		strListTagPic.add(PICADMNRRTAG2);
		strListTagPic.add(PICADMNOPTAG2);
		strListTagPic.add(PICADMNMANTAG2);
	}

	/**
	 * Test Case ID: TC29_VerifyLoginProfile_UtilityAdmin():
	 * Verify Login Profile for Utility Admin
	 * Script:
	 * - Login with user credentials on customer utility admin user [E1]
	 * - Click on Administration link [E3]
	 * - Click on Surveyor [E6]
	 * - Click on Driving Survey link [E7]
	 * - Click on View link of any one of the survey and turn on ALL GIS data [E8]
	 * - Click on Reports [E9]
	 * Results:
	 * - E1. Login successful and user navigated to expected customer's home page
	 * - E3. Administration menu is displayed
	 * - E6. User is able to see surveyor units and analyzers associated with that particular Customer and not other analyzers information not associated to that Customer
	 * - E7. User is able to see surveys associated only to his surveyor units and not other user's surveys
	 * - E8. User is able to see GIS Assets data associated with that particular Customer and not other GIS Assets not associated to that Customer (must zoom in to one of the 3 highest levels)
	 * - E9 User is able to see reports associated only to that customer and not other customer's user's reports
	 */

	@Test
	public void TC29_VerifyLoginProfile_UtilityAdmin() throws Exception {
		String userName = SQACUSUA;
		String password = USERPASSWORD;
		String customerName = SQACUS;

		Log.info(
				"\nRunning TC29_VerifyLoginProfile_UtilityAdmin - Test Description: Verify Login Profile for Utility Admin");

		loginPage.open();
		loginPage.loginNormalAs(userName, password);
		
		/* Verify Customer Administration Link */
		homePage.waitForPageLoad();
		homePage.openCusAdminMenu();
		assertTrue(homePage.verifyCustomerAdministrationLinks());
		
		/* Verify Customer Surveyors */ 
		homePage.getLinkSurveyors().click();
		surveyorPage.waitForPageLoad();
		assertTrue(surveyorPage.verifyCustomerSpecificSurveyorsAreShown(customerName));

		/* Verify Customer Driving Survey Link */ 
		homePage.getLinkDrivingSurveys().click();
		measurementSessionsPage.waitForPageLoad();
		assertTrue(measurementSessionsPage.checkVisibilityForDrivingSurveys(userName, UserRoleType.UtilityAdmin, strListTagCus, strListTagPic));

		/* Verify  Survey View */
		measurementSessionsPage.performSearch(CUSDRVSTDLEAK);
		measurementSessionsPage.clickOnFirstViewSurvey();
		surveyViewPage.waitForPageLoad();
		surveyViewPage.clickGisButton();
		surveyViewPage.toggleGisSwitch(GisSwitchType.UseAllBoundaries, true);
		surveyViewPage.toggleGisSwitch(GisSwitchType.UseAllPipes, true);
		surveyViewPage.clickGisButton();
		surveyViewPage.setZoomLevelForAssets();
		assertTrue(surveyViewPage.verifyScreenshotWithBaseline("TC29", "SQACUSUA-AssetAndBoundaries", new Rectangle(500, 100, 500, 350), true));
		
		/* Verify  Reports */
		homePage.open();
		homePage.openReportsMenu();
		homePage.getLinkCompliance().click();
		complianceReportsPage.waitForPageLoad();
		assertTrue(complianceReportsPage.verifyReportsAreCreatedBy(customerName));
		
		homePage.getLinkAssessment().click();
		assessmentReportsPage.waitForPageLoad();
		assertTrue(assessmentReportsPage.verifyReportsAreCreatedBy(customerName));
		
		homePage.getLinkEQ().click();
		eqReportsPage.waitForPageLoad();
		assertTrue(eqReportsPage.verifyReportsAreCreatedBy(customerName));
		
		homePage.getLinkFacilityEQ().click();
		facilityEQReportsPage.waitForPageLoad();
		assertTrue(facilityEQReportsPage.verifyReportsAreCreatedBy(customerName));
		
		homePage.getLinkReferenceGas().click();
		referenceGasReportsPage.waitForPageLoad();
		assertTrue(referenceGasReportsPage.verifyReportsAreCreatedBy(customerName));
		
		homePage.getLinkSystemHistory().click();
		systemHistoryReportsPage.waitForPageLoad();
		assertTrue(systemHistoryReportsPage.verifyReportsAreCreatedBy(customerName));		
	}
	

	/**
	 * Test Case ID: TC29_VerifyLoginProfile_Supervisor():
	 * Verify Login Profile for Supervisor
	 * Script:
	 * - Login with user credentials on customer utility supervisor user [E1]
	 * - Click on Surveyor [E6]
	 * - Click on Driving Survey link [E7]
	 * - Click on View link of any one of the survey and turn on ALL GIS data [E8]
	 * - Click on Reports [E9]
	 * Results:
	 * - E1. Login successful and user navigated to expected customer's home page
	 * - E6. User is able to see surveyor units and analyzers associated with that particular Customer and not other analyzers information not associated to that Customer
	 * - E7. User is able to see surveys associated only to his surveyor units and not other user's surveys
	 * - E8. User is able to see GIS Assets data associated with that particular Customer and not other GIS Assets not associated to that Customer (must zoom in to one of the 3 highest levels)
	 * - E9 User is able to see reports associated only to that customer and not other customer's user's reports
	 * @throws Exception 
	 */
	
	@Test
	public void TC29_VerifyLoginProfile_Supervisor() throws Exception {
		String userName = SQACUSSU;
		String password = USERPASSWORD;
		String customerName = SQACUS;

		Log.info(
				"\nRunning TC29_VerifyLoginProfile_Supervisor - Test Description: Verify Login Profile for Supervisor");

		loginPage.open();
		loginPage.loginNormalAs(userName, password);

		/* Verify Customer Surveyors */ 
		homePage.getLinkSurveyors().click();
		surveyorPage.waitForPageLoad();
		assertTrue(surveyorPage.verifyCustomerSpecificSurveyorsAreShown(customerName));

		/* Verify Customer Driving Survey Link */ 
		homePage.getLinkDrivingSurveys().click();
		measurementSessionsPage.waitForPageLoad();
		assertTrue(measurementSessionsPage.checkVisibilityForDrivingSurveys(userName, UserRoleType.Supervisor, strListTagCus, strListTagPic));

		/* Verify  Survey View */
		measurementSessionsPage.performSearch(CUSDRVSTDLEAK);
		measurementSessionsPage.clickOnFirstViewSurvey();
		surveyViewPage.waitForPageLoad();
		surveyViewPage.clickGisButton();
		surveyViewPage.toggleGisSwitch(GisSwitchType.UseAllBoundaries, true);
		surveyViewPage.toggleGisSwitch(GisSwitchType.UseAllPipes, true);
		surveyViewPage.clickGisButton();
		surveyViewPage.setZoomLevelForAssets();
		assertTrue(surveyViewPage.verifyScreenshotWithBaseline("TC29", "SQACUSSU-AssetAndBoundaries", new Rectangle(500, 100, 500, 350), true));
		
		/* Verify  Reports */
		homePage.open();
		homePage.openReportsMenu();
		homePage.getLinkCompliance().click();
		complianceReportsPage.waitForPageLoad();
		assertTrue(complianceReportsPage.verifyReportsAreCreatedBy(customerName));
		
		homePage.getLinkAssessment().click();
		assessmentReportsPage.waitForPageLoad();
		assertTrue(assessmentReportsPage.verifyReportsAreCreatedBy(customerName));
		
		homePage.getLinkEQ().click();
		eqReportsPage.waitForPageLoad();
		assertTrue(eqReportsPage.verifyReportsAreCreatedBy(customerName));
		
		homePage.getLinkFacilityEQ().click();
		facilityEQReportsPage.waitForPageLoad();
		assertTrue(facilityEQReportsPage.verifyReportsAreCreatedBy(customerName));
		
		homePage.getLinkReferenceGas().click();
		referenceGasReportsPage.waitForPageLoad();
		assertTrue(referenceGasReportsPage.verifyReportsAreCreatedBy(customerName));
		
		homePage.getLinkSystemHistory().click();
		systemHistoryReportsPage.waitForPageLoad();
		assertTrue(systemHistoryReportsPage.verifyReportsAreCreatedBy(customerName));		
	}
	
	/**
	 * Test Case ID: TC29_VerifyLoginProfile_PicarroAdmin():
	 * Verify Login Profile for Picarro Admin
	 * Script:
	 * - Login with user credentials on customer picarro admin user [E1]
	 * - Click on Administration link [E3]
	 * Results:
	 * - E1. Login successful and user navigated to expected customer's home page
	 * - E4. Picarro Administrator Menu is displayed
	 * - E5. Customer Alarm and Raw Data logs are displayed
	 */
	@Test
	public void TC29_VerifyLoginProfile_PicarroAdmin() {
		String userName = PICDFADMIN;
		String password = PICADMINPSWD;
		

		Log.info(
				"\nRunning TC29_VerifyLoginProfile_PicarroAdmin - Test Description: Verify Login Profile for Picarro Admin");

		loginPage.open();
		loginPage.loginNormalAs(userName, password);
		
		/* Verify Picarro Administration Links/Logs */
		homePage.waitForPageLoad();
		homePage.openPicarroAdminMenu();
		assertTrue(homePage.verifyPicarroAdministrationLinks());
		assertTrue(homePage.verifyPicarroAdministrationLogs());
	}
	
	/**
	 * Test Case ID: TC35_CheckACLVCustomerUser_DriverRole Test Description:
	 * Check ACLV for customer user with Driver role
	 *
	 */
	@Test
	public void TC35_CheckACLVCustomerUser_DriverRole() {
		String customerName = SQACUS;
		String userName = customerName + getTestSetup().getFixedSizeRandomNumber(8) + REGBASEUSERNAME;
		String location = SQACUSLOC;

		Log.info(
				"\nRunning TC35_CheckACLVCustomerUser_DriverRole - Test Description: Check ACLV for customer user with Driver role");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

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
	 * Test Case ID: TC35_CheckACLVCustomerUser_DriverRole_Preferences Test
	 * Description: Preferences link is working
	 *
	 */
	@Test
	public void TC35_CheckACLVCustomerUser_DriverRole_Preferences() {
		Log.info(
				"\nRunning TC35_CheckACLVCustomerUser_DriverRole_Preferences - Test Description: RPreferences link is working");
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
	 * Test Case ID: TC36_CheckACLVCustomerUser_SupervisorRole Test Description:
	 * Check ACLV for customer user with Supervisor role
	 *
	 */
	@Test
	public void TC36_CheckACLVCustomerUser_SupervisorRole() {
		String userName = SQACUS + getTestSetup().getFixedSizeRandomNumber(8) + REGBASEUSERNAME;
		String location = SQACUSLOC;

		Log.info(
				"\nRunning TC36_CheckACLVCustomerUser_SupervisorRole - Test Description: Check ACLV for customer user with Supervisor role");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(SQACUS, userName, USERPASSWORD, CUSUSERROLESU, location);

		manageCustomersPage.open();
		manageCustomersPage.logout();

		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);

		homePage.open();
		homePage.waitForPageLoad();
		assertTrue(homePage.checkIfAtHomePage());
		assertTrue(homePage.checkVisibilityForCusSU(userName));
		homePage.logout();
	}

	/**
	 * Test Case ID: TC36_CheckReportLink_SupervisorRole Test Description: Check
	 * Reports link for customer user with Supervisor role
	 *
	 * @throws Exception
	 *
	 */

	@Test
	public void TC36_CheckReportLink_SupervisorRole() throws Exception {
		String testCaseID = "TC36";
		String rptTitle = testCaseID + " Report" + getTestSetup().getRandomNumber();
		Log.info("\nRunning " + testCaseID
				+ "_CheckReportLink_SupervisorRole - Test Description: Report link is working and user is able to view report's menu");
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

		List<String> tagList = new ArrayList<String>();
		tagList.add(CUSDRVSTDTAG);

		ComplianceReportEntity rpt = new ComplianceReportEntity(rptTitle, SQACUSSU, "sqacus", TIMEZONEET, "0", listBoundary,
				tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);

		complianceReportsPage.addNewReport(rpt);

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSUA, testCaseID))) {
			complianceReportsPage.clickOnReportViewerCloseButton();
			if (complianceReportsPage.validatePdfFiles(rpt, getTestSetup().getDownloadPath())) {
				assertTrue(complianceReportsPage.findReport(rptTitle, SQACUSUA));
			} else
				fail("\nTC36__CheckReportLink_SupervisorRole: Generate Report as Customer Admin failed.\n");
		} else
			fail("\nTC36__CheckReportLink_SupervisorRole: Generate Report as Customer Admin failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();

		complianceReportsPage.login(SQACUSSU, USERPASSWORD);
		assertTrue(WebElementExtender.verifyElementIsDisplayed(getDriver(), complianceReportsPage.getLinkReportMenu(), REPORT_MENU_DISPLAY_TIMEOUT));
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

	}

	/**
	 * Test Case ID: TC36_CheckUserLink_ReleaseNotes Test Description: Release
	 * Notes link is working and can download Release Notes
	 *
	 */
	@Ignore
	public void TC36_CheckUserLink_ReleaseNotes() {
		Log.info(
				"\nRunning TC36_CheckReportLink_CustomerSupervisorRole - Test Description: Release Notes link is working and can download Release Notes");
		loginPage.open();
		loginPage.loginNormalAs(SQACUSSU, USERPASSWORD);

		homePage.open();
		homePage.waitForPageLoad();
		homePage.getDropDownLoginUser().click();
		homePage.waitForReleaseNotesLinktoLoad();
		assertTrue(homePage.getReleaseNotesLink());
	}

	/**
	 * Test Case ID: TC36_CheckUserLink_Preferences Test Description:
	 * Preferences link is working
	 *
	 */
	@Test
	public void TC36_CheckUserLink_Preferences() {
		Log.info(
				"\nRunning TC36_CheckReportLink_CustomerSupervisorRole - Test Description: Preferncess link is working");
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
		assertTrue(surveyorPage.verifyCustomerSpecificSurveyorsAreShown(SQACUS));
	}

	/**
	 * Test Case ID: TC37_CheckACLVCustomerUser_UtilityAdminRole Test
	 * Description: Check ACLV for customer user with Utility Administrator role
	 *
	 */
	@Test
	public void TC37_CheckACLVCustomerUser_UtilityAdminRole() {
		String userName = SQACUS + getTestSetup().getFixedSizeRandomNumber(8) + REGBASEUSERNAME;
		String location = SQACUSLOC;

		Log.info(
				"\nRunning TC37_CheckACLVCustomerUser_UtilityAdminRole - Test Description: Check ACLV for customer user with Utility Administrator role");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		manageUsersPage.open();
		manageUsersPage.addNewCustomerUser(SQACUS, userName, USERPASSWORD, CUSUSERROLEUA, location);

		manageCustomersPage.open();
		manageCustomersPage.logout();

		loginPage.open();
		loginPage.loginNormalAs(userName, USERPASSWORD);

		homePage.open();
		homePage.waitForPageLoad();
		assertTrue(homePage.checkIfAtHomePage());
		assertTrue(homePage.checkVisibilityForCusUA(userName));
		homePage.logout();
	}

	/**
	 * Test Case ID: TC37_CheckReportLink_UtilityAdminRole Test Description:
	 * Check ACLV for customer user with Utility Administrator role
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void TC37_CheckReportLink_UtilityAdminRole() throws Exception {
		String testCaseID = "TC37";
		String rptTitle = testCaseID + " Report" + getTestSetup().getRandomNumber();
		Log.info("\nRunning " + testCaseID
				+ "_CheckReportLink_UtilityAdminRole - Test Description: Report link is working and user is able to view report's menu");
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

		List<String> tagList = new ArrayList<String>();
		tagList.add(CUSDRVSTDTAG);

		ComplianceReportEntity rpt = new ComplianceReportEntity(rptTitle, SQACUSUA, "sqacus", TIMEZONEET, "0", listBoundary,
				tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);

		complianceReportsPage.addNewReport(rpt);

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSUA, testCaseID))) {
			complianceReportsPage.clickOnReportViewerCloseButton();
			if (complianceReportsPage.validatePdfFiles(rpt, getTestSetup().getDownloadPath())) {
				assertTrue(complianceReportsPage.findReport(rptTitle, SQACUSUA));
			} else
				fail("\nTC37__CheckReportLink_UtilityAdminRole: Generate Report failed.\n");
		} else
			fail("\nTC37__CheckReportLink_UtilityAdminRole: Generate Report failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();

		complianceReportsPage.login(SQACUSUA, USERPASSWORD);
		assertTrue(WebElementExtender.verifyElementIsDisplayed(getDriver(), complianceReportsPage.getLinkReportMenu(), REPORT_MENU_DISPLAY_TIMEOUT));
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
	}

	/**
	 * Test Case ID: TC37_CheckUserLink_ReleaseNotes Test Description: Release
	 * Notes link is working and can download Release Notes
	 *
	 */
	@Ignore
	public void TC37_CheckUserLink_ReleaseNotes() {
		Log.info(
				"\nRunning TC37_CheckReportLink_UtilityAdminRole - Test Description: Release Notes link is working and can download Release Notes");
		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		homePage.open();
		homePage.waitForPageLoad();
		homePage.getDropDownLoginUser().click();
		homePage.waitForReleaseNotesLinktoLoad();
		assertTrue(homePage.getReleaseNotesLink());

	}

	/**
	 * Test Case ID: TC37_CheckUserLink_Preferences Test Description:
	 * Preferences link is working
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
		assertTrue(surveyorPage.verifyCustomerSpecificSurveyorsAreShown(SQACUS));
	}
	/**
	 * Test Case ID: TC38_CheckACLV_PicAdminRole Test Description: Check ACLV
	 * for Picarro Administrator role, non-default Administrator account
	 *
	 */
	@Test
	public void TC38_CheckACLV_PicAdminRole() {
		String userName = PICNAMEPREFIX + "ad" + getTestSetup().getRandomNumber() + REGBASEPICUSERNAME;
		String customer = "Picarro";
		String location = "Santa Clara";
		String locationDesc = customer + " - " + location;

		Log.info(
				"\nRunning TC38_CheckACLV_PicAdminRole - Test Description: Check ACLV for Picarro Administrator role, non-default Administrator account\n");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		manageUsersPage.open();

		Log.info(String.format("Adding new Picarro user: Username-[%s];Role-[%s];Location-[%s];Timezone-[%s]", userName,
				USERROLEADMIN, locationDesc, TIMEZONECT));
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
	 * Test Case ID: TC38_CheckReportLink_PicarroAdminRole Test Description:
	 * Check ACLV for customer user with Utility Administrator role
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void TC38_CheckReportLink_PicarroAdminRole() throws Exception {
		String testCaseID = "TC38";
		String rptTitle = testCaseID + " Report" + getTestSetup().getRandomNumber();
		Log.info("\nRunning " + testCaseID
				+ "_CheckReportLink_PicarroAdminRole - Test Description: Report link is working and user is able to view report's menu");
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

		List<String> tagList = new ArrayList<String>();
		tagList.add(CUSDRVSTDTAG);

		ComplianceReportEntity rpt = new ComplianceReportEntity(rptTitle, PICDFADMIN, "sqacus", TIMEZONEET, "0", listBoundary,
				tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);

		complianceReportsPage.addNewReport(rpt);

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN, testCaseID))) {
			complianceReportsPage.clickOnReportViewerCloseButton();
			if (complianceReportsPage.validatePdfFiles(rpt, getTestSetup().getDownloadPath())) {
				assertTrue(complianceReportsPage.findReport(rptTitle, PICDFADMIN));
			} else
				fail("\nTC37__CheckReportLink_PicarroAdminRole: Report is not valid .\n");
		} else
			fail("\nTC37__CheckReportLink_PicarroAdminRole: Generate Report failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();

		complianceReportsPage.login(PICDFADMIN, PICADMINPSWD);
		assertTrue(WebElementExtender.verifyElementIsDisplayed(getDriver(), complianceReportsPage.getLinkReportMenu(), REPORT_MENU_DISPLAY_TIMEOUT));
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

	}

	/**
	 * Test Case ID: TC38_CheckUserLink_ReleaseNotes Test Description: Release
	 * Notes link is working and can download Release Notes
	 *
	 */
	@Ignore
	public void TC38_CheckUserLink_ReleaseNotes_PicarroAdminRole() {
		Log.info(
				"\nRunning TC38_CheckReportLink_PicarroAdminRole - Test Description: Release Notes link is working and can download Release Notes");
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
	 * Test Case ID: TC38_CheckUserLink_Preferences Test Description:
	 * Preferences link is working
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
		assertTrue(preferencesPage.getSelectedLocation().getText().equalsIgnoreCase(DEFAULTLOC));
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
	 * Test Case ID: TC40_CheckReportLink_PicarroSupportRole Test Description:
	 * Check ACLV for Picarro Support role
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void TC40_CheckReportLink_PicarroSupportRole() throws Exception {
		String testCaseID = "TC40";
		String rptTitle = testCaseID + " Report" + getTestSetup().getRandomNumber();
		Log.info("\nRunning " + testCaseID
				+ "_CheckReportLink_PicarroSupportRole - Test Description: Report link is working and user is able to view report's menu");
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

		List<String> tagList = new ArrayList<String>();
		tagList.add(CUSDRVSTDTAG);

		ComplianceReportEntity rpt = new ComplianceReportEntity(rptTitle, SQAPICSUP, "sqacus", TIMEZONEET, "0", listBoundary,
				tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);

		complianceReportsPage.addNewReport(rpt);

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQAPICSUP, testCaseID))) {
			complianceReportsPage.clickOnReportViewerCloseButton();
			if (complianceReportsPage.validatePdfFiles(rpt, getTestSetup().getDownloadPath())) {
				assertTrue(complianceReportsPage.findReport(rptTitle, SQAPICSUP));
			} else
				fail("\nTC40__CheckReportLink_PicarroSupportRole: Generate Report failed.\n");
		} else
			fail("\nTC40__CheckReportLink_PicarroSupportRole: Generate Report failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();

		complianceReportsPage.login(SQAPICSUP, USERPASSWORD);
		assertTrue(WebElementExtender.verifyElementIsDisplayed(getDriver(), complianceReportsPage.getLinkReportMenu(), REPORT_MENU_DISPLAY_TIMEOUT));
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

	}

	/**
	 * Test Case ID: TC40_CheckUserLink_ReleaseNotes Test Description: Release
	 * Notes link is working and can download Release Notes
	 *
	 */
	@Ignore
	public void TC40_CheckUserLink_ReleaseNotes_PicarroSupportRole() {
		Log.info(
				"\nRunning TC40_CheckReportLink_PicarroSupportRole - Test Description: Release Notes link is working and can download Release Notes");
		loginPage.open();
		loginPage.loginNormalAs(SQAPICSUP, USERPASSWORD);

		homePage.open();
		homePage.waitForPageLoad();
		homePage.getDropDownLoginUser().click();

		homePage.waitForReleaseNotesLinktoLoad();
		assertTrue(homePage.getReleaseNotesLink());

	}

	/**
	 * Test Case ID: TC40_CheckUserLink_Preferences Test Description:
	 * Preferences link is working
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

		Log.info(String.format("EXPECTED timezone='%s', Actual timezone='%s'",
				TIMEZONE, preferencesPage.getSelectedTimeZone().getText()));
		assertTrue(preferencesPage.getSelectedTimeZone().getText().equalsIgnoreCase(TIMEZONE));
		Log.info(String.format("EXPECTED selected location='%s', Actual selected location='%s'",
				PICARROLOC, preferencesPage.getSelectedLocation().getText()));
		assertTrue(preferencesPage.getSelectedLocation().getText().equalsIgnoreCase(PICARROLOC));
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
	 * Test Case ID: TC40_CheckACLV_PicSupportRole Test Description: Check ACLV
	 * for Picarro user with Picarro Support role
	 *
	 */
	@Test
	public void TC40_CheckACLV_PicSupportRole() {
		String userName = PICNAMEPREFIX + "su" + getTestSetup().getRandomNumber() + REGBASEPICUSERNAME;
		String customer = "Picarro";
		String location = "Santa Clara";
		String locationDesc = customer + " - " + location;

		Log.info(
				"\nRunning TC40_CheckACLV_PicSupportRole - Test Description: Check ACLV for Picarro user with Picarro Support role\n");

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());

		Log.info("Creating Picarro support user with username-" + userName);
		manageUsersPage.open();

		Log.info(String.format("Adding new Picarro user: Username-[%s];Role-[%s];Location-[%s];Timezone-[%s]", userName,
				PICUSERROLESUP, locationDesc, TIMEZONECT));
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
	
	/**
	 * Test Case ID: TC57_FleetMap_PicAdminRole
	 * Test Description: Fleet Map displaying labels and last known locations of all vehicles to picarro admin
	 * One or more surveyors are online 
	 * Customer has Fleet map and Observer View priviledge
	 * Steps:
	 * - Log in and navigate to home page
	 * - Click Fleet Map
	 * - Zoom in/out
	 * - Click on vehicle icon
	 * - Click on the link for one of the surveyors that is online
	 * Results:
	 * - Fleet Map link is present
	 * - If customer has any vehicles that were active in the past 30 days, these should appear on the map
	 * - Car icons should appear as top view, not side view
	 * - Vehicles which are online should show correct bearing (pointing in the right direction)
	 * - Initial zoom level should include all vehicles on map
	 * - Vehicles which are too close to each other should appear with a circle marker which contains the number of vehicles (clustered view)
	 * - Zooming in/out should change view between clustered view and individual car icons (if vehicles are too close together, they will remain in clustered view even at maximum zoom)
	 * - Vehicle is offline: icon will be displayed in black
	 * - Vehicle is online: icon will be displayed in green
	 * - User should see details for each vehicle - Status: offline (red font)/online (green font) IP Address of the analyzer, Customer Name, Driver, Build #
	 * - User will be navigated to Observer View for that surveyor
	 */
	@Test
	public void TC57_FleetMap_PicAdminRole() {
		Log.info("\nRunning TC57_FleetMap_PicAdminRole - Test Description: Fleet Map displaying labels and last known locations of all vehicles to picarro admin\n");

		loginPage.open();
		loginPage.loginNormalAs(SQAPICAD, USERPASSWORD);

		homePage.open();
		homePage.waitForPageLoad();
		homePage.getLinkFleetMap().click();

		fleetMapPage.waitForPageLoad();
		fleetMapPage.waitForFleetMaptoLoad();
		assertTrue(fleetMapPage.checkIfAtFleetMapPage());
		assertTrue(fleetMapPage.getFleetMap().isDisplayed());
		homePage.logout();
	}
	
}