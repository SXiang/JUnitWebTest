package surveyor.regression.source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.source.Log;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.CUSTOMERNAMEPREFIX;
import static surveyor.scommon.source.SurveyorConstants.CUSUSERROLEUA;
import static surveyor.scommon.source.SurveyorConstants.EULASTRING;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPHEIGHT;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPWIDTH;
import static surveyor.scommon.source.SurveyorConstants.KEYANNOTATION;
import static surveyor.scommon.source.SurveyorConstants.KEYFIELDNOTES;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETCASTIRON;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETCOPPER;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETOTHERPLASTIC;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETPEPLASTIC;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETPROTECTEDSTEEL;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETUNPROTECTEDSTEEL;
import static surveyor.scommon.source.SurveyorConstants.KEYBASEMAP;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARIES;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARYDISTRICT;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARYDISTRICTPLAT;
import static surveyor.scommon.source.SurveyorConstants.KEYBREADCRUMB;
import static surveyor.scommon.source.SurveyorConstants.KEYFOV;
import static surveyor.scommon.source.SurveyorConstants.KEYGAPS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDICATIONS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDTB;
import static surveyor.scommon.source.SurveyorConstants.KEYGAPTB;
import static surveyor.scommon.source.SurveyorConstants.KEYISOANA;
import static surveyor.scommon.source.SurveyorConstants.KEYISOTOPICCAPTURE;
import static surveyor.scommon.source.SurveyorConstants.KEYLISA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCRA;
import static surveyor.scommon.source.SurveyorConstants.KEYVIEWNAME;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.REGBASEUSERNAME;
import static surveyor.scommon.source.SurveyorConstants.RNELAT;
import static surveyor.scommon.source.SurveyorConstants.RNELON;
import static surveyor.scommon.source.SurveyorConstants.RSURENDDATE;
import static surveyor.scommon.source.SurveyorConstants.RSURSTARTDATE;
import static surveyor.scommon.source.SurveyorConstants.RSWLAT;
import static surveyor.scommon.source.SurveyorConstants.RSWLON;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEMT;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;
import static surveyor.scommon.source.SurveyorConstants.X_OFFSET;
import static surveyor.scommon.source.SurveyorConstants.Y_OFFSET;
import static surveyor.scommon.source.SurveyorConstants.RECT_WIDTH;
import static surveyor.scommon.source.SurveyorConstants.RECT_HEIGHT;









import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.ComplianceReportsPage.ComplianceReportButtonType;
import surveyor.scommon.source.Reports.SurveyModeFilter;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest2 extends BaseReportsPageTest {

	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;

	private static HomePageActions homePageAction;
	private static LoginPageActions loginPageAction;
	private static TestEnvironmentActions testEnvironmentAction;

	private static ComplianceReportsPage complianceReportsPage;
	private static ManageCustomersPage manageCustomersPage;
	private static ManageLocationsPage manageLocationsPage;
	private static ManageUsersPage manageUsersPage;

	@BeforeClass
	public static void beforeTestClass() throws Exception {
		initializePageActions();
	}

	/**
	 * Initializes the page action objects.
	 */
	protected static void initializePageActions() {
		loginPageAction = new LoginPageActions(driver, baseURL, testSetup);
		homePageAction = new HomePageActions(driver, baseURL, testSetup);
		manageCustomersPage = new ManageCustomersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver,  manageCustomersPage);
		manageLocationsPage = new ManageLocationsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageLocationsPage);
		manageUsersPage = new ManageUsersPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, manageUsersPage);
		complianceReportsPage = new ComplianceReportsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, complianceReportsPage);
		testEnvironmentAction = new TestEnvironmentActions();
	}

	/**
	 * Test Case ID: TC720_ShapefileMetaDataReportFeaturePermissionExistingCustomer_NewComplianceReportGeneration
	 * Script: -  	
	 *	-- Log in as Picarro Admin
	 *	-- On Manage Customers page, select a customer that does not have Report Shapefile and Report Meta Data permission enabled and click the "Edit" button 
	*	-- Confirm that the "Account Enabled" box is checked and check the Report Shape File and Report Meta data button 
	*	-- Click OK 
	*	-- Login as Customer User
	*	-- On the Compliance Reports page, generate the report and select LISAs, FOV, Breadcrumb, Gaps and/or Assets 
	*	-- Click on Compliance Viewer button
	*	-- Click on the Shape file and meta file export button
	 * Results: - 
	 *	- -Compliance Viewer dialog has Shape (ZIP) and Meta data (ZIP) export buttons
	 *  - -User can download the Shape files and meta data files successfully
	 */
	@Ignore
	public void TC720_ShapefileMetaDataReportFeaturePermissionExistingCustomer_NewComplianceReportGeneration() throws Exception {
		Log.info("\nRunning TC720_ShapefileMetaDataReportFeaturePermissionExistingCustomer_NewComplianceReportGeneration ...");

		String testCaseID = "TC720";
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + testCaseID;
		String eula = customerName + ": " + EULASTRING;
		String userName = customerName + "customerUser01" + REGBASEUSERNAME;
		String cityName = "Santa Clara";
		String locationName = customerName + "loc";
		String rptTitle = testCaseID + " Report" + testSetup.getRandomNumber();

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula, false);		
		assertTrue(manageCustomersPage.findExistingCustomer(customerName, false));
		assertTrue(manageCustomersPage.findCustomerAndOpenEditPage(customerName));
		manageCustomersPage.enabledDisableCustomer(true);
		manageCustomersPage.getInputReportMetadata().click();
		manageCustomersPage.getInputReportShapeFile().click();
		manageCustomersPage.getbtnOk().click();

		 manageLocationsPage.open();
		 manageLocationsPage.waitForPageLoad();
		 manageLocationsPage.addNewLocation(locationName, customerName, cityName);

		 manageUsersPage.open();
		 manageUsersPage.waitForPageLoad();
		 manageUsersPage.addNewCustomerUser(customerName, userName, USERPASSWORD, CUSUSERROLEUA, locationName);

		 assertTrue(manageUsersPage.findExistingUser(locationName, userName, false));
		 loginPage = manageUsersPage.logout();

		 loginPage.open();
		 HomePage homePage = loginPage.loginNormalAs(userName, USERPASSWORD);
		 assertTrue(homePage.checkIfAtHomePage());

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

		 tableMap.put(KEYINDTB, "0");
		 tableMap.put(KEYISOANA, "0");
		 tableMap.put(KEYPCA, "0");
		 tableMap.put(KEYPCRA, "0");
		 tableMap.put(KEYASSETCASTIRON, "1");
		 tableMap.put(KEYASSETCOPPER, "1");
		 tableMap.put(KEYASSETOTHERPLASTIC, "1");
		 tableMap.put(KEYASSETPEPLASTIC, "1");
		 tableMap.put(KEYASSETPROTECTEDSTEEL, "1");
		 tableMap.put(KEYASSETUNPROTECTEDSTEEL, "1");
		 tableMap.put(KEYBOUNDARYDISTRICT, "0");
		 tableMap.put(KEYBOUNDARYDISTRICTPLAT, "0");
		 tablesList.add(tableMap);

		 List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		 Map<String, String> viewMap1 = new HashMap<String, String>();

		 viewMap1.put(KEYVIEWNAME, "First View");
		 viewMap1.put(KEYLISA, "1");
		 viewMap1.put(KEYFOV, "1");
		 viewMap1.put(KEYBREADCRUMB, "1");
		 viewMap1.put(KEYINDICATIONS, "1");
		 viewMap1.put(KEYISOTOPICCAPTURE, "0");
		 viewMap1.put(KEYANNOTATION, "0");
		 viewMap1.put(KEYGAPS, "1");
		 viewMap1.put(KEYASSETS, "1");
		 viewMap1.put(KEYBOUNDARIES, "0");
		 viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));
		 viewList.add(viewMap1);

		 List<String> tagList = new ArrayList<String>();
		 tagList.add(PICADMNSTDTAG);

		 ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		 complianceReportsPage.addNewReport(rpt);
		 complianceReportsPage.waitForPageLoad();

		 if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			 assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			 assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
		 } else
			 fail("\nTestcase TC720 failed.\n");
	}

	/**
	 * Test Case ID: TC824_CheckFileNameLisaNumbersArePresentComplianceReportPDF
	 * Script: -  	
	 *	- - Generate Compliance report (Include surveys having large number of indications)
	 *	- - Click on Compliance Viewer
	 *	- - Click on Compliance Table PDF
	 *	- - Click on Compliance ZIP (PDF)
	 * Results: - 
	 *  - - SSRS PDF should have File_Name at the top of the 1st page 
	 *	- - Lisa_Number present in Indications table should be sequential
	 */
	@Test
	public void TC824_CheckFileNameLisaNumbersArePresentComplianceReportPDF() throws Exception {
		Log.info("\nRunning TC824_CheckFileNameLisaNumbersArePresentComplianceReportPDF ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

		String testCaseID = "TC824";
		String rptTitle = testCaseID + " Report" + testSetup.getRandomNumber();

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

		tableMap.put(KEYINDTB, "0");
		tableMap.put(KEYISOANA, "0");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tableMap.put(KEYASSETCASTIRON, "1");
		tableMap.put(KEYASSETCOPPER, "1");
		tableMap.put(KEYASSETOTHERPLASTIC, "1");
		tableMap.put(KEYASSETPEPLASTIC, "1");
		tableMap.put(KEYASSETPROTECTEDSTEEL, "1");
		tableMap.put(KEYASSETUNPROTECTEDSTEEL, "1");
		tableMap.put(KEYBOUNDARYDISTRICT, "0");
		tableMap.put(KEYBOUNDARYDISTRICTPLAT, "0");
		tablesList.add(tableMap);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "0");
		viewMap1.put(KEYANNOTATION, "0");
		viewMap1.put(KEYGAPS, "1");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));
		viewList.add(viewMap1);

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			assertTrue(complianceReportsPage.verifyComplianceReportStaticText(rptTitle));
			if (tablesList != null) {
				if (tablesList.get(0).get(KEYINDTB).equals("1")) {
					assertTrue(complianceReportsPage.verifyIndicationTable(testSetup.getDownloadPath(), rptTitle));
					//Lisa_Number present in Indications table should be sequential--check is left to do
				}
			}
		}else
			fail("\nTestcase TC824 failed.\n");

	}

	/**
	 * Test Case ID: TC1038_ValidationMessageShouldDisplayedUserAreaSelectionWhenGapsAreSelectedWhileGeneratingReport
	 * Script: -  	
	 *	- - On the Compliance Reports page, click the New Compliance Report button
	 *	- - Message displayed to user: Please make sure your selected boundary is less than 1.5 sqkms when Gaps are selected
	 * Results: - 
	 *  - - - Message displayed to user: "Please make sure your selected boundary is less than 1.5 sqkms when Gaps are selected"
	 *	- - Report is generated successfully
	 */
	@Test
	public void TC1038_ValidationMessageShouldDisplayedUserAreaSelectionWhenGapsAreSelectedWhileGeneratingReport() throws Exception {
		Log.info("\nRunning TC1038_ValidationMessageShouldDisplayedUserAreaSelectionWhenGapsAreSelectedWhileGeneratingReport ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

		String testCaseID = "TC1038";
		String rptTitle = testCaseID + " Report" + testSetup.getRandomNumber();

		complianceReportsPage.open();
		List<String> listBoundary1 = new ArrayList<String>();
		listBoundary1.add(IMGMAPHEIGHT);
		listBoundary1.add(IMGMAPWIDTH);
		listBoundary1.add("37.410444553141716");
		listBoundary1.add("-121.95666793157869");
		listBoundary1.add("37.39367146608184");
		listBoundary1.add("-122.00717929174715");


		List<String> listBoundary2 = new ArrayList<String>();
		listBoundary2.add(IMGMAPHEIGHT);
		listBoundary2.add(IMGMAPWIDTH);
		listBoundary2.add("37.4206");
		listBoundary2.add("-121.9725");
		listBoundary2.add("37.4157");
		listBoundary2.add("-121.9839");

		List<Map<String, String>> tablesList1 = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap1 = new HashMap<String, String>();

		tableMap1.put(KEYINDTB, "0");
		tableMap1.put(KEYISOANA, "0");
		tableMap1.put(KEYGAPTB, "0");
		tableMap1.put(KEYPCA, "0");
		tableMap1.put(KEYPCRA, "0");
		tableMap1.put(KEYASSETCASTIRON, "1");
		tableMap1.put(KEYASSETCOPPER, "1");
		tableMap1.put(KEYASSETOTHERPLASTIC, "1");
		tableMap1.put(KEYASSETPEPLASTIC, "1");
		tableMap1.put(KEYASSETPROTECTEDSTEEL, "1");
		tableMap1.put(KEYASSETUNPROTECTEDSTEEL, "1");
		tableMap1.put(KEYBOUNDARYDISTRICT, "0");
		tableMap1.put(KEYBOUNDARYDISTRICTPLAT, "0");
		tablesList1.add(tableMap1);

		List<Map<String, String>> tablesList2 = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap2 = new HashMap<String, String>();

		tableMap2.put(KEYINDTB, "0");
		tableMap2.put(KEYISOANA, "0");
		tableMap2.put(KEYGAPTB, "1");
		tableMap2.put(KEYPCA, "0");
		tableMap2.put(KEYPCRA, "0");
		tableMap2.put(KEYASSETCASTIRON, "1");
		tableMap2.put(KEYASSETCOPPER, "1");
		tableMap2.put(KEYASSETOTHERPLASTIC, "1");
		tableMap2.put(KEYASSETPEPLASTIC, "1");
		tableMap2.put(KEYASSETPROTECTEDSTEEL, "1");
		tableMap2.put(KEYASSETUNPROTECTEDSTEEL, "1");
		tableMap2.put(KEYBOUNDARYDISTRICT, "0");
		tableMap2.put(KEYBOUNDARYDISTRICTPLAT, "0");
		tablesList2.add(tableMap2);

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "0");
		viewMap1.put(KEYANNOTATION, "0");
		viewMap1.put(KEYGAPS, "1");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));
		viewList1.add(viewMap1);


		List<Map<String, String>> viewList2 = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap2 = new HashMap<String, String>();

		viewMap2.put(KEYVIEWNAME, "First View");
		viewMap2.put(KEYLISA, "1");
		viewMap2.put(KEYFOV, "1");
		viewMap2.put(KEYBREADCRUMB, "1");
		viewMap2.put(KEYINDICATIONS, "1");
		viewMap2.put(KEYISOTOPICCAPTURE, "0");
		viewMap2.put(KEYANNOTATION, "0");
		viewMap2.put(KEYGAPS, "0");
		viewMap2.put(KEYASSETS, "1");
		viewMap2.put(KEYBOUNDARIES, "0");
		viewMap2.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));
		viewList2.add(viewMap1);

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt1 = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary1, tablesList1, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt1);
		assertTrue(complianceReportsPage.getAssetErrorText().getText().equals("Please make sure your selected boundary is less than 1.5 sq km when Gaps are selected"));

		testEnvironmentAction.idleForSeconds(String.valueOf(10), NOTSET);
		complianceReportsPage.clickOnCancelBtn();
		complianceReportsPage.open();

		ReportsCompliance rpt2 = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary1, tablesList2, "", tagList, "", "", viewList2, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt2);
		assertTrue(complianceReportsPage.getAssetErrorText().getText().equals("Please make sure your selected boundary is less than 1.5 sq km when Gaps are selected"));

		testEnvironmentAction.idleForSeconds(String.valueOf(10), NOTSET);
		complianceReportsPage.clickOnCancelBtn();
		complianceReportsPage.open();

		ReportsCompliance rpt3 = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary2, tablesList1, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt3);
		complianceReportsPage.waitForPageLoad();
		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt3, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
		}else
			fail("\nTestcase TC1038 failed.\n");

		complianceReportsPage.open();

		ReportsCompliance rpt4 = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary2, tablesList2, "", tagList, "", "", viewList2, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt4);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt4, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
		}else
			fail("\nTestcase TC1038 failed.\n");

	}

	/**
	 * Test Case ID: TC1041_StandardSurvey_GapBoxesUniqueNumbersPrescribedFormat
	 * Script: -  
	 *  - - Log in as Picarro Admin	
	 *	- - On the Compliance Reports page, click the New Compliance Report button
	 *	- - In the Views section, select Gaps but do not select LISAs, and click OK
	 *	- - When the report has finished generating, click on the thumbnail preview button and download the Compliance ZIP (PDF)
	 *  - -  On the View PDF, zoom in to the maximum level
	 * Results: - 
	 *  - -  The SSRS report should list all Gap boxes, listed and numbered with regard to their location according to the coordinates on the grid (A1, B1, C5, etc) 
	 *	- - The report view should show the map overlaid with a grid, with columns marked A to Z and rows marked 1 to n
	 *	- - The Gap numbers in the SSRS report should correspond to their location on the grid
	 *	- - The grid cells should be approximately 200 feet square
	 */
	@Test
	public void TC1041_StandardSurvey_GapBoxesUniqueNumbersPrescribedFormat() throws Exception {
		Log.info("\nRunning TC1041_StandardSurvey_GapBoxesUniqueNumbersPrescribedFormat ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

		String testCaseID = "TC1041";
		String rptTitle = testCaseID + " Report" + testSetup.getRandomNumber();

		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add("37.4206");
		listBoundary.add("-121.9725");
		listBoundary.add("37.4157");
		listBoundary.add("-121.9839");

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "0");
		tableMap.put(KEYISOANA, "0");
		tableMap.put(KEYGAPTB, "0");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tableMap.put(KEYASSETCASTIRON, "1");
		tableMap.put(KEYASSETCOPPER, "1");
		tableMap.put(KEYASSETOTHERPLASTIC, "1");
		tableMap.put(KEYASSETPEPLASTIC, "1");
		tableMap.put(KEYASSETPROTECTEDSTEEL, "1");
		tableMap.put(KEYASSETUNPROTECTEDSTEEL, "1");
		tableMap.put(KEYBOUNDARYDISTRICT, "0");
		tableMap.put(KEYBOUNDARYDISTRICTPLAT, "0");
		tablesList.add(tableMap);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap = new HashMap<String, String>();

		viewMap.put(KEYVIEWNAME, "First View");
		viewMap.put(KEYLISA, "0");
		viewMap.put(KEYFOV, "0");
		viewMap.put(KEYBREADCRUMB, "0");
		viewMap.put(KEYINDICATIONS, "0");
		viewMap.put(KEYISOTOPICCAPTURE, "0");
		viewMap.put(KEYANNOTATION, "0");
		viewMap.put(KEYGAPS, "1");
		viewMap.put(KEYASSETS, "1");
		viewMap.put(KEYBOUNDARIES, "0");
		viewMap.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));
		viewList.add(viewMap);

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();
		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			if (tablesList != null) {
				if ((tablesList.get(0).get(KEYPCA).equals("1")) || (tablesList.get(0).get(KEYPCRA).equals("1"))) {
					assertTrue(complianceReportsPage.verifyGapsTable(testSetup.getDownloadPath(), rptTitle));
					//assertTrue(complianceReportsPage.verifyViewsImages(testSetup.getDownloadPath(), rptTitle, testCaseID, destViewTitle));
				}
			}
		}else
			fail("\nTestcase TC1041 failed.\n");

	}

	/**
	 * Test Case ID: TC1091_GenerateComplianceReportWhenGapTableSelectedButGapsPresentViewsSectionNotSelected
	 * Script: -  	
	 * Results: - 
	 *	- - SSRS Gap table should not show Gaps which are completely covered by FoV and LISA- Views will not show Gaps
	 */
	@Ignore
	public void TC1091_GenerateComplianceReportWhenGapTableSelectedButGapsPresentViewsSectionNotSelected() throws Exception {
		Log.info("\nRunning TC1091_GenerateComplianceReportWhenGapTableSelectedButGapsPresentViewsSectionNotSelected ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}

	/**
	 * Test Case ID: TC1092_GenerateComplianceReportWhenGapsPresentViewsSectionSelectedButGapTableNotSelected
	 * Script: -  	
	 *	- - Views will show Gaps
	 * Results: - 
	 *	- - SSRS will not show Gaps Table (not even the header)
	 */
	@Ignore
	public void TC1092_GenerateComplianceReportWhenGapsPresentViewsSectionSelectedButGapTableNotSelected() throws Exception {
		Log.info("\nRunning TC1092_GenerateComplianceReportWhenGapsPresentViewsSectionSelectedButGapTableNotSelected ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}

	/**
	 * Test Case ID: TC1237_GenerateComplianceReportWhenGapTableGapsInOneOfViewsSelected
	 * Script: -  	
	 *	- - Add View2 with base map value: satellite for all the values (except Gaps)
	 *	- - SSRS Gap table should not show Gaps which are completely covered by FoV and LISA- View1 will have Gaps information
	 * Results: - 
	 *	- - View2 will not have gaps information
	 */
	@Ignore
	public void TC1237_GenerateComplianceReportWhenGapTableGapsInOneOfViewsSelected() throws Exception {
		Log.info("\nRunning TC1237_GenerateComplianceReportWhenGapTableGapsInOneOfViewsSelected ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}

	/**
	 * Test Case ID: TC1257_GenerateComplianceReportSurveyHavingMultipleFieldNotesIndications
	 * Script: -  	
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button 
	 *	- - Add the survey
	 *	- - Add View 1 : selectIsotopic Analysis, Indications, Field Notes. Base Map Value : Map 
	 *	- - Add View 2 : selectIsotopic Analysis, Indications, Field Notes. Base Map Value : Satellite 
	 *	- - Click on OK and click Compliance (ZIP) icon
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - User is allowed to download the report
	 *	- - Field Notes present in Isotopic Analysis and Indications table should be present in both views as well
	 *	- - Reference gas capture result and note should not be displayed
	 */
	@Ignore
	public void TC1257_GenerateComplianceReportSurveyHavingMultipleFieldNotesIndications() throws Exception {
		Log.info("\nRunning TC1257_GenerateComplianceReportSurveyHavingMultipleFieldNotesIndications ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}

	/**
	 * Test Case ID: TC1267_GenerateComplianceReportCustomerWhoDoesNotAssetLoaded
	 * Script: -  	
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button 
	 *  - - Timezone : PST, Survey Mode: Standard
	 *  - - Provide lat long values 
	 *	- - Add View: Select Indications, Field Notes , FOV, Breadcrumb, Lisa, indication Base Map Value : Map
	 *	- - Click on OK and click Download icon
	 * Results: - 
	 *  - - In Views section, Assets and Boundaries check box is not present
	 *	- - In Optional Views Layers, Assets and Boundaries types are not displayed
	 *	- - Report generated successfully having specified timezone and asset data for specified tag id and date range surveys
	 *	- - User is allowed to download the report
	 *	- - Export image should show the map for the specified Lat-Long boundary
	 */
	@Test
	public void TC1267_GenerateComplianceReportCustomerWhoDoesNotAssetLoaded() throws Exception {
		Log.info("\nRunning TC1267_GenerateComplianceReportCustomerWhoDoesNotAssetLoaded ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

		String testCaseID = "TC1267";
		String rptTitle = testCaseID + " Report" + testSetup.getRandomNumber();

		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add("37.4206");
		listBoundary.add("-121.9725");
		listBoundary.add("37.4157");
		listBoundary.add("-121.9839");

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "0");
		tableMap.put(KEYISOANA, "0");
		tableMap.put(KEYGAPTB, "0");
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
		viewMap.put(KEYISOTOPICCAPTURE, "1");
		viewMap.put(KEYANNOTATION, "1");
		viewMap.put(KEYFIELDNOTES, "1");
		viewMap.put(KEYGAPS, "1");
		viewMap.put(KEYASSETS, "0");
		viewMap.put(KEYBOUNDARIES, "0");
		viewMap.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));
		viewList.add(viewMap);

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();
		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			assertTrue(complianceReportsPage.verifyComplianceReportStaticText(rptTitle));
			assertTrue(complianceReportsPage.verifySSRSImages(testSetup.getDownloadPath(), rptTitle, testCaseID));
			if (tablesList != null) {
				if ((tablesList.get(0).get(KEYPCA).equals("1")) || (tablesList.get(0).get(KEYPCRA).equals("1"))) {
					assertTrue(complianceReportsPage.verifyShowCoverageTable(testSetup.getDownloadPath(), rptTitle));
					assertTrue(complianceReportsPage.verifyCoverageValuesTable(testSetup.getDownloadPath(), rptTitle, tablesList.get(0)));
				}
			}
		}
		else
			fail("\nTestcase TC1267 failed.\n");
	}

	/**
	 * Test Case ID: TC1268_ValidationMessageShouldDisplayedUserCustomerBoundaryAreaSelectionWhenGapsAreSelectedWhileGeneratingReport
	 * Script: -  	
	 *	- - Log in as Picarro Admin
	 *  - - On the Compliance Reports page, click the New Compliance Report button
	 *  - - Fill out the required fields
	 *  - - Select Customer boundary area larger then 1.5 sqkms
	 *  - - (eg. Picarro Customer - District)
	 *  - - Select Gaps in Views
	 *  - - Click on OK
	 *  - -  Un select Gaps from views section 
	 *  - - Select Gap Table present in Optional Tabular PDF Content section and click OK
	 *  - - Select Area smaller then 1.5 sqkms
	 *  - - (eg. Picarro Customer - DistrictPlat 
	 *  - - P&E Customer - DistrictPlat)
	 *  - - Select Gaps in Views
	 *  - - Click on OK
	 *  - - Un select Gaps from views section 
	 *  - - Select Gap Table present in Optional Tabular PDF Content section and click OK
	 * Results: - 
	 *	- - Message displayed to user: Please make sure your selected boundary is less than 1.5 sqkms when Gaps are selected
	 *  - - Report is generated successfully
	 */
	@Ignore
	public void TC1268_ValidationMessageShouldDisplayedUserCustomerBoundaryAreaSelectionWhenGapsAreSelectedWhileGeneratingReport() throws Exception {
		Log.info("\nRunning TC1268_ValidationMessageShouldDisplayedUserCustomerBoundaryAreaSelectionWhenGapsAreSelectedWhileGeneratingReport ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}

	/**
	 * Test Case ID: TC1299_CheckFileNameOfCsvShapeFilesPresentMetaDataShapeFileZIPFolderRespectively
	 * Script: -  	
	 * 	- -  Log in to application as picarro admin   
	 *	- - Generate Compliance report (Include surveys having indications, isotopic capture, field notes etc)
	 *	- - Click on Compliance Viewer
	 *	- - Click on Compliance PDF (Meta)
	 *	- - Click on Compliance PDF (Shape)
	 * Results: - 
	 *  - - Shape file or csv file present in Shape or Meta ZIP folder should have ReportName as suffix
	 *	- - Eg. ReportName: CR-DC3080
	 *	- - User can open the files successfully without any error
	 */
	@Test
	public void TC1299_CheckFileNameOfCsvShapeFilesPresentMetaDataShapeFileZIPFolderRespectively() throws Exception {
		Log.info("\nRunning TC1299_CheckFileNameOfCsvShapeFilesPresentMetaDataShapeFileZIPFolderRespectively ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

		String testCaseID = "TC1299";
		String rptTitle = testCaseID + " Report" + testSetup.getRandomNumber();

		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add("37.4206");
		listBoundary.add("-121.9725");
		listBoundary.add("37.4157");
		listBoundary.add("-121.9839");

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "0");
		tableMap.put(KEYISOANA, "0");
		tableMap.put(KEYGAPTB, "0");
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
		viewMap.put(KEYISOTOPICCAPTURE, "1");
		viewMap.put(KEYANNOTATION, "1");
		viewMap.put(KEYFIELDNOTES, "1");
		viewMap.put(KEYGAPS, "1");
		viewMap.put(KEYASSETS, "0");
		viewMap.put(KEYBOUNDARIES, "0");
		viewMap.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));
		viewList.add(viewMap);

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();
		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			if (tablesList != null) {
				if ((tablesList.get(0).get(KEYPCA).equals("1")) || (tablesList.get(0).get(KEYPCRA).equals("1"))) {
					assertTrue(complianceReportsPage.verifyReportSurveyMetaDataFile(testSetup.getDownloadPath(), rptTitle));
				}
			}
		}
		else
			fail("\nTestcase TC1299 failed.\n");
	}

	/**
	 * Test Case ID: TC1300_UserFriendlyMessageShouldDisplayedIfUserIncludeAssetsBoundariesLayersOptionalViewLayersSection
	 * Script: -  	
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button
	 *	- - Add View with base map value: map and select Lisa, Indication, etc but don't select asset and boundaries check box
	 *  - - Select any assets or boundaries layers in optional View Layers section
	 *  - - Click on OK
	 * Results: - 
	 *  - User friendly message should be displayed to user:
	 *	- Selected Asset Layer(s), Please select at least one view with Assets
	 *	- Selected Boundary Layer(s), Please select at least one view with Boundaries
	 */
	@Test
	public void TC1300_UserFriendlyMessageShouldDisplayedIfUserIncludeAssetsBoundariesLayersOptionalViewLayersSection() throws Exception {
		Log.info("\nRunning TC1300_UserFriendlyMessageShouldDisplayedIfUserIncludeAssetsBoundariesLayersOptionalViewLayersSection ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

		String testCaseID = "TC1038";
		String rptTitle = testCaseID + " Report" + testSetup.getRandomNumber();

		complianceReportsPage.open();
		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add("37.410444553141716");
		listBoundary.add("-121.95666793157869");
		listBoundary.add("37.39367146608184");
		listBoundary.add("-122.00717929174715");


		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "0");
		tableMap.put(KEYISOANA, "0");
		tableMap.put(KEYGAPTB, "0");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tableMap.put(KEYASSETCASTIRON, "1");
		tableMap.put(KEYASSETCOPPER, "1");
		tableMap.put(KEYASSETOTHERPLASTIC, "1");
		tableMap.put(KEYASSETPEPLASTIC, "1");
		tableMap.put(KEYASSETPROTECTEDSTEEL, "1");
		tableMap.put(KEYASSETUNPROTECTEDSTEEL, "1");
		tableMap.put(KEYBOUNDARYDISTRICT, "1");
		tableMap.put(KEYBOUNDARYDISTRICTPLAT, "1");
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
		viewMap.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));
		viewList.add(viewMap);

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt1 = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt1);
		assertTrue(complianceReportsPage.getAssetErrorText().getText().equals("Selected Asset Layer(s), Please select at least one view with Assets"));
		assertTrue(complianceReportsPage.getBoundaryErrorText().getText().equals("Selected Boundary Layer(s), Please select at least one view with Boundaries"));
	}

	/**
	 * Test Case ID: TC1301_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecast
	 * Script: -  	
	 *	- - Log in as Picarro Admin
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button 
	 *	- - Add 2 or 3 surveys with different tag values
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)- Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Ignore
	public void TC1301_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecast() throws Exception {
		Log.info("\nRunning TC1301_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecast ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}

	/**
	 * Test Case ID: TC1303_UserFriendlyMessageShouldDisplayedIfUserIncludeAssetsBoundariesViews
	 * Script: -  	
	 *	- - On Home Page, click Reports -& Compliance -& 'Copy Compliance Report' button
	 *	- - Add View with base map value: map and select asset and boundaries
	 *  - - Do not select any assets or boundaries layers
	 *  - - Click on OK
	 * Results: - 
	 *	- - User friendly message should be displayed to user:
	 *	- View(s) with Assets, Please select at least one Asset Layer
	 *	- View(s) with Boundaries, Please select at least one Boundary Layer
	 */
	@Test
	public void TC1303_UserFriendlyMessageShouldDisplayedIfUserIncludeAssetsBoundariesViews() throws Exception {
		Log.info("\nRunning TC1303_UserFriendlyMessageShouldDisplayedIfUserIncludeAssetsBoundariesViews ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

		String testCaseID = "TC1303";
		String rptTitle = testCaseID + " Report" + testSetup.getRandomNumber();

		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add("37.4206");
		listBoundary.add("-121.9725");
		listBoundary.add("37.4157");
		listBoundary.add("-121.9839");

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "0");
		tableMap.put(KEYISOANA, "0");
		tableMap.put(KEYGAPTB, "0");
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

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYFIELDNOTES, "1");
		viewMap1.put(KEYGAPS, "1");
		viewMap1.put(KEYASSETS, "0");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));
		viewList1.add(viewMap1);

		List<Map<String, String>> viewList2 = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap2 = new HashMap<String, String>();

		viewMap2.put(KEYVIEWNAME, "First View");
		viewMap2.put(KEYLISA, "0");
		viewMap2.put(KEYFOV, "0");
		viewMap2.put(KEYBREADCRUMB, "0");
		viewMap2.put(KEYINDICATIONS, "0");
		viewMap2.put(KEYISOTOPICCAPTURE, "0");
		viewMap2.put(KEYANNOTATION, "0");
		viewMap2.put(KEYFIELDNOTES, "0");
		viewMap2.put(KEYGAPS, "0");
		viewMap2.put(KEYASSETS, "1");
		viewMap2.put(KEYBOUNDARIES, "1");
		viewMap2.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));
		viewList2.add(viewMap2);

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();
		complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser());
		complianceReportsPage.getInputSearch().sendKeys(rptTitle);
		complianceReportsPage.waitForPageLoad();
		complianceReportsPage.clickOnCopyReport(rptTitle, testSetup.getLoginUser());
		complianceReportsPage.waitForCopyReportPagetoLoad();
		Thread.sleep(2000);;
		complianceReportsPage.addViews(testSetup.getLoginUser(), viewList2);
		complianceReportsPage.clickOnOKButton();
		assertTrue(complianceReportsPage.getAssetErrorText().getText().equals("View(s) with Assets, Please select at least one Asset Layer"));
		assertTrue(complianceReportsPage.getBoundaryErrorText().getText().equals("View(s) with Boundaries, Please select at least one Boundary Layer"));
	}

	/**
	 * Test Case ID: ButNotSelectedAssetBoundariesCheckBoxAddViewSectionCopyComplianceReportScreen
	 * Script: -  	
	 *	- - On Home Page, click Reports -& Compliance -& 'Copy Compliance Report' button
	 *	- - Add View with base map value: map and select Lisa, Indication, etc but don't select asset and boundaries check box
	 *	- Selected Asset Layer(s), Please select at least one view with Assets
	 * Results: - 
	 * - - User friendly message should be displayed to user:
	 * - - Selected Asset Layer(s), Please select at least one view with Assets
	 * - - Selected Boundary Layer(s), Please select at least one view with Boundaries
	 */
	@Test
	public void TC1304_UserFriendlyMessageShouldDisplayedIfUserIncludeAssetsBoundariesLayersOptionalViewLayersSection() throws Exception {
		Log.info("\nRunning TC1304_UserFriendlyMessageShouldDisplayedIfUserIncludeAssetsBoundariesLayersOptionalViewLayersSection ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

		String testCaseID = "TC1304";
		String rptTitle = testCaseID + " Report" + testSetup.getRandomNumber();

		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add("37.4206");
		listBoundary.add("-121.9725");
		listBoundary.add("37.4157");
		listBoundary.add("-121.9839");

		List<Map<String, String>> tablesList1 = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap1 = new HashMap<String, String>();

		tableMap1.put(KEYINDTB, "0");
		tableMap1.put(KEYISOANA, "0");
		tableMap1.put(KEYGAPTB, "0");
		tableMap1.put(KEYPCA, "0");
		tableMap1.put(KEYPCRA, "0");
		tableMap1.put(KEYASSETCASTIRON, "0");
		tableMap1.put(KEYASSETCOPPER, "0");
		tableMap1.put(KEYASSETOTHERPLASTIC, "0");
		tableMap1.put(KEYASSETPEPLASTIC, "0");
		tableMap1.put(KEYASSETPROTECTEDSTEEL, "0");
		tableMap1.put(KEYASSETUNPROTECTEDSTEEL, "0");
		tableMap1.put(KEYBOUNDARYDISTRICT, "0");
		tableMap1.put(KEYBOUNDARYDISTRICTPLAT, "0");
		tablesList1.add(tableMap1);

		List<Map<String, String>> tablesList2 = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap2 = new HashMap<String, String>();

		tableMap2.put(KEYINDTB, "0");
		tableMap2.put(KEYISOANA, "0");
		tableMap2.put(KEYGAPTB, "0");
		tableMap2.put(KEYPCA, "0");
		tableMap2.put(KEYPCRA, "0");
		tableMap2.put(KEYASSETCASTIRON, "1");
		tableMap2.put(KEYASSETCOPPER, "1");
		tableMap2.put(KEYASSETOTHERPLASTIC, "0");
		tableMap2.put(KEYASSETPEPLASTIC, "0");
		tableMap2.put(KEYASSETPROTECTEDSTEEL, "0");
		tableMap2.put(KEYASSETUNPROTECTEDSTEEL, "0");
		tableMap2.put(KEYBOUNDARYDISTRICT, "1");
		tableMap2.put(KEYBOUNDARYDISTRICTPLAT, "0");
		tablesList2.add(tableMap2);

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYFIELDNOTES, "1");
		viewMap1.put(KEYGAPS, "1");
		viewMap1.put(KEYASSETS, "0");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));
		viewList1.add(viewMap1);

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList1, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();
		complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser());
		complianceReportsPage.getInputSearch().sendKeys(rptTitle);
		complianceReportsPage.waitForPageLoad();
		complianceReportsPage.clickOnCopyReport(rptTitle, testSetup.getLoginUser());
		complianceReportsPage.waitForCopyReportPagetoLoad();
		Thread.sleep(2000);
		complianceReportsPage.handleOptionalViewLayersSection(tablesList2);
		complianceReportsPage.clickOnOKButton();
		assertTrue(complianceReportsPage.getAssetErrorText().getText().equals("Selected Asset Layer(s), Please select at least one view with Assets"));
		assertTrue(complianceReportsPage.getBoundaryErrorText().getText().equals("Selected Boundary Layer(s), Please select at least one view with Boundaries"));
	}

	/**
	 * Test Case ID: TC1305_CheckPercentCoverageForecastCheckBoxPresentNewCopyComplianceReportScreensPicarroUser
	 * Script: -  
	 * - - Log in to application as Picarro user and navigate to New Compliance Report page
	 * - - Click on Cancel and navigate to Copy compliance screen	
	 * Results: - 
	 *	- - Percent Coverage Forecast check box is present on UI
	 */
	@Test
	public void TC1305_CheckPercentCoverageForecastCheckBoxPresentNewCopyComplianceReportScreensPicarroUser() throws Exception {
		Log.info("\nRunning TC1305_CheckPercentCoverageForecastCheckBoxPresentNewCopyComplianceReportScreensPicarroUser ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 5);   /* Picarro Customer*/

		complianceReportsPage.open();

		complianceReportsPage.getNewComplianceReportBtn().click();
		complianceReportsPage.getPercentCoverReportArea().isDisplayed();
		complianceReportsPage.clickOnCancelBtn();
		
		String copyImgXPath = "//*[@id='datatable']/tbody/tr[1]/td[5]/a[2]/img";
		WebElement copyImg = driver.findElement(By.xpath(copyImgXPath));

		copyImg.click();
		complianceReportsPage.waitForCopyReportPagetoLoad();
		Thread.sleep(2000);;
		complianceReportsPage.getPercentCoverReportArea().isDisplayed();
		
	}

	/**
	 * Test Case ID: TC1306_CheckPercentCoverageForecastCheckBoxPresentNewCopyComplianceReportScreensOfCustomerUserHavingAssets
	 * Script: -  
	 * - - Log in to application as Customer admin user and navigate to New Compliance Report page
	 * - - Click on Cancel and navigate to Copy compliance screen	
	 * Results: - 
	 *	- - Percent Coverage Forecast check box is present on UI
	 */
	@Test
	public void TC1306_CheckPercentCoverageForecastCheckBoxPresentNewCopyComplianceReportScreensOfCustomerUserHavingAssets() throws Exception {
		Log.info("\nRunning TC1306_CheckPercentCoverageForecastCheckBoxPresentNewCopyComplianceReportScreensOfCustomerUserHavingAssets ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin*/

		complianceReportsPage.open();

		complianceReportsPage.getNewComplianceReportBtn().click();
		complianceReportsPage.getPercentCoverReportArea().isDisplayed();
		complianceReportsPage.clickOnCancelBtn();
		
		String copyImgXPath = "//*[@id='datatable']/tbody/tr[1]/td[5]/a[2]/img";
		WebElement copyImg = driver.findElement(By.xpath(copyImgXPath));

		copyImg.click();
		complianceReportsPage.waitForCopyReportPagetoLoad();
		Thread.sleep(2000);;
		complianceReportsPage.getPercentCoverReportArea().isDisplayed();
	}

	/**
	 * Test Case ID: TC1307_CheckPercentCoverageForecastCheckBoxNotPresentNewCopyComplianceReportScreensOfCustomerUserNotHavingAssets
	 * Script: -  	
	 *  - - Log in to application as Customer admin user and navigate to New Compliance Report page
	 *  - - Click on Cancel and navigate to Copy compliance screen
	 * Results: - 
	 *	- - Percent Coverage Forecast check box is not present on UI
	 */
	@Test
	public void TC1307_CheckPercentCoverageForecastCheckBoxNotPresentNewCopyComplianceReportScreensOfCustomerUserNotHavingAssets() throws Exception {
		Log.info("\nRunning TC1307_CheckPercentCoverageForecastCheckBoxNotPresentNewCopyComplianceReportScreensOfCustomerUserNotHavingAssets ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 1);   /* Picarro Customer*/

		complianceReportsPage.open();

		complianceReportsPage.getNewComplianceReportBtn().click();
		complianceReportsPage.getPercentCoverReportArea().isDisplayed();
		complianceReportsPage.clickOnCancelBtn();
		
		String copyImgXPath = "//*[@id='datatable']/tbody/tr[1]/td[5]/a[2]/img";
		WebElement copyImg = driver.findElement(By.xpath(copyImgXPath));

		copyImg.click();
		complianceReportsPage.waitForCopyReportPagetoLoad();
		Thread.sleep(2000);;
		complianceReportsPage.getPercentCoverReportArea().isDisplayed();

	}

	/**
	 * Test Case ID: TC1310_CheckFileNamesOfCsvShapeFilesPresentMetaDataShapeFileZIPFolderRespectivelyWhenUserReprocessExistingOldReports
	 * Script: -  	
	 *	- - Log in to application as picarro admin
	 *	- - Reprocess the existing reports
	 *	- - Click on Compliance Viewer
	 *	- - Click on "Compliance PDF (Meta)"
	 *	- - Click on "Compliance PDF (Shape)"
	 *	- - Try to open multiple reports Meta Data files simultaneously
	 * Results: - 
	 *  - - Shape file or csv file present in Shape or Meta ZIP folder should have ReportName as suffix
	 *  - - Eg. ReportName: CR-DC3080
	 *  - - CR-DC3080_ReportLisa.csv
	 *	- - User can open the files successfully without any error
	 */
	//@Test--DE1874
	public void TC1310_CheckFileNamesOfCsvShapeFilesPresentMetaDataShapeFileZIPFolderRespectivelyWhenUserReprocessExistingOldReports() throws Exception {
		Log.info("\nRunning TC1310_CheckFileNamesOfCsvShapeFilesPresentMetaDataShapeFileZIPFolderRespectivelyWhenUserReprocessExistingOldReports ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

		String testCaseID = "TC1310";
		String rptTitle = testCaseID + " Report" + testSetup.getRandomNumber();

		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add("37.4206");
		listBoundary.add("-121.9725");
		listBoundary.add("37.4157");
		listBoundary.add("-121.9839");

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "0");
		tableMap.put(KEYISOANA, "0");
		tableMap.put(KEYGAPTB, "0");
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
		viewMap.put(KEYISOTOPICCAPTURE, "1");
		viewMap.put(KEYANNOTATION, "1");
		viewMap.put(KEYFIELDNOTES, "1");
		viewMap.put(KEYGAPS, "1");
		viewMap.put(KEYASSETS, "0");
		viewMap.put(KEYBOUNDARIES, "0");
		viewMap.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));
		viewList.add(viewMap);

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);


		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser());

		complianceReportsPage.findReportbySearch(rptTitle, testSetup.getLoginUser());

		complianceReportsPage.clickComplianceReportButton(rptTitle, testSetup.getLoginUser(), ComplianceReportButtonType.Resubmit);
		complianceReportsPage.waitForPageLoad();
		complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser());

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			if (tablesList != null) {
				if ((tablesList.get(0).get(KEYPCA).equals("1")) || (tablesList.get(0).get(KEYPCRA).equals("1"))) {
					assertTrue(complianceReportsPage.verifyReportSurveyMetaDataFile(testSetup.getDownloadPath(), rptTitle));
				}
			}
		}
		else
			fail("\nTestcase TC1310 failed.\n");
	}

	/**
	 * Test Case ID: TC1311_CheckFileNameOfCsvShapeFilesPresentMetaDataShapeFileZIPFolderRespectivelyUsingCopyFunctionality
	 * Script: -  	
	 *  - - Log in to application as picarro admin
	 *	- - Generate Compliance report (Include surveys having indications, isotopic capture, field notes etc) using Copy functionality
	 *	- - Click on Compliance Viewer
	 *	- - Click on Compliance PDF (Meta)
	 *	- - Click on Compliance PDF (Shape)
	 *	- - Try to open multiple reports Meta Data files simultaneously
	 * Results: - 
	 *	- - Shape file or csv file present in Shape or Meta ZIP folder should have ReportName as suffix
	 *	- - Eg. ReportName: CR-DC3080
	 *	- - CR-DC3080_ReportLisa.csv
	 *	- - User can open the files successfully without any error
	 */
	@Test
	public void TC1311_CheckFileNameOfCsvShapeFilesPresentMetaDataShapeFileZIPFolderRespectivelyUsingCopyFunctionality() throws Exception {
		Log.info("\nRunning TC1311_CheckFileNameOfCsvShapeFilesPresentMetaDataShapeFileZIPFolderRespectivelyUsingCopyFunctionality ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

		String testCaseID = "TC1311";
		String rptTitle = testCaseID + " Report" + testSetup.getRandomNumber();

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

		tableMap.put(KEYINDTB, "0");
		tableMap.put(KEYISOANA, "0");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tableMap.put(KEYASSETCASTIRON, "1");
		tableMap.put(KEYASSETCOPPER, "1");
		tableMap.put(KEYASSETOTHERPLASTIC, "1");
		tableMap.put(KEYASSETPEPLASTIC, "1");
		tableMap.put(KEYASSETPROTECTEDSTEEL, "1");
		tableMap.put(KEYASSETUNPROTECTEDSTEEL, "1");
		tableMap.put(KEYBOUNDARYDISTRICT, "0");
		tableMap.put(KEYBOUNDARYDISTRICTPLAT, "0");
		tablesList.add(tableMap);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "0");
		viewMap1.put(KEYANNOTATION, "0");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, RSURSTARTDATE, RSURENDDATE, viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		assertTrue(complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser()));

		String newReportTitle = rptTitle + "COPY";
		complianceReportsPage.copyReport(rptTitle, testSetup.getLoginUser(), newReportTitle);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			if (tablesList != null) {
				if ((tablesList.get(0).get(KEYPCA).equals("1")) || (tablesList.get(0).get(KEYPCRA).equals("1"))) {
					assertTrue(complianceReportsPage.verifyReportSurveyMetaDataFile(testSetup.getDownloadPath(), rptTitle));
				}
			}
		}
		else
			fail("\nTestcase TC1311 failed.\n");

	}

	/**
	 * Test Case ID: TC1313_CheckErrorMesageDisplayedIfPercentCoverageForecastCheckBoxSelectedNewComplianceReportScreensAlongCustomBoundary
	 * Script: -  	
	 * Results: - 
	 *	- - Add 2 surveys with different tag value
	 */
	//@Test --latlong selector
 	public void TC1313_CheckErrorMesageDisplayedIfPercentCoverageForecastCheckBoxSelectedNewComplianceReportScreensAlongCustomBoundary() throws Exception {
		Log.info("\nRunning TC1313_CheckErrorMesageDisplayedIfPercentCoverageForecastCheckBoxSelectedNewComplianceReportScreensAlongCustomBoundary ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 1);   /* Picarro Admin */

	}

	/**
	 * Test Case ID: TC1315_CheckErrorMessagePresentIfPercentCoverageForecastCheckBoxSelectedCopyComplianceReportScreens
	 * Script: -  	
	 *	- - Log in to application as Customer admin user and navigate to Copy Compliance Report page
	 *	- - Select Custom boundary and select Lat/Long co-ordinates
	 *	- - Click OK
	 *	- - Select Customer boundary and click OK
	 *	- - Select Custom boundary and provide Lat/Long co-ordinates
	 *	- - Add 2 or more surveys with different tag values
	 *	- - Click OK

	 * Results: - 
	 *	- - User friendly error messages are displayed:
	 *	- -  "Selected Percent Coverage Forecast, Please select Customer Boundary"
	 *	- - "Selected Percent Coverage Forecast, Please select at least two surveys with different tags"
	 *	- - User friendly error messages are displayed: 
	 *	- - "Selected Percent Coverage Forecast, Please select at least two surveys with different tags"
	 *	- - User friendly error messages are displayed: 
	 *	- - "Selected Percent Coverage Forecast, Please select Customer Boundary"
	 */
	@Test
	public void TC1315_CheckErrorMessagePresentIfPercentCoverageForecastCheckBoxSelectedCopyComplianceReportScreens() throws Exception {
		Log.info("\nRunning TC1315_CheckErrorMessagePresentIfPercentCoverageForecastCheckBoxSelectedCopyComplianceReportScreens ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}

	/**
	 * Test Case ID: TC1318_GenerateMultipleComplianceReportsDifferentAssetLayerIncludePercentCoverageForecast
	 * Script: -  	
	 *	- - Log in as Customer Admin (eg. PG&amp;E util admin)
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button 
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 *	- - Generate other report with same parameter as above but make sure you select only Copper Asset layer
	 *	- - Generate other report with same parameter as above but make sure no assets are selected
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - Percent Coverage value is not dependent of Assets so value should not change
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)- Additional Surveys , Probability to Obtain 70% Coverage
	 */
	@Test  //Need to change the user to "customer admin"
	public void TC1318_GenerateMultipleComplianceReportsDifferentAssetLayerIncludePercentCoverageForecast() throws Exception {
		Log.info("\nRunning TC1318_GenerateMultipleComplianceReportsDifferentAssetLayerIncludePercentCoverageForecast ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);
		
		String testCaseID = "TC1318";
		String rptTitle = testCaseID + " Report" + testSetup.getRandomNumber();
		String boundaryName = "Level 2-AA";

		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "0");
		tableMap.put(KEYISOANA, "0");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tableMap.put(KEYASSETCASTIRON, "1");
		tableMap.put(KEYASSETCOPPER, "1");
		tableMap.put(KEYASSETOTHERPLASTIC, "1");
		tableMap.put(KEYASSETPEPLASTIC, "1");
		tableMap.put(KEYASSETPROTECTEDSTEEL, "1");
		tableMap.put(KEYASSETUNPROTECTEDSTEEL, "1");
		tableMap.put(KEYBOUNDARYDISTRICT, "0");
		tableMap.put(KEYBOUNDARYDISTRICTPLAT, "0");
		tablesList.add(tableMap);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "0");
		viewMap1.put(KEYANNOTATION, "0");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, RSURSTARTDATE, RSURENDDATE, viewList, SurveyModeFilter.Standard);
		rpt.setCustomerBoundaryInfo(ReportsCompliance.CustomerBoundaryFilterType.SmallBoundary, boundaryName);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			assertTrue(complianceReportsPage.verifyComplianceReportStaticText(rptTitle));
			assertTrue(complianceReportsPage.verifySSRSImages(testSetup.getDownloadPath(), rptTitle, testCaseID));
			if (tablesList != null) {
				if ((tablesList.get(0).get(KEYPCA).equals("1")) || (tablesList.get(0).get(KEYPCRA).equals("1"))) {
					assertTrue(complianceReportsPage.verifyShowCoverageTable(testSetup.getDownloadPath(), rptTitle));
					assertTrue(complianceReportsPage.verifyCoverageValuesTable(testSetup.getDownloadPath(), rptTitle, tablesList.get(0)));
				}
			}
		}
		else
			fail("\nTestcase TC1318 failed.\n");
	}

	/**
	 * Test Case ID: TC1090_GenerateComplianceReportWhenGapTableGapsInViewsSectionSelected
	 * Script: - 
	 * - - On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 * - - TimeZone : PST, Report Mode: Standard, Exclusion Radius: 0
	 * - - Click Custom Boundary
	 * - - Click Lat/Long Map Selector
	 * - - Press the Shift key, then right-click and drag to select the desired area on the map and click OK
	 * - - (Select area smaller then or equal to 1.5 sqkms)
	 * - - Select one or more surveys
	 * - - Add View with base map value: map, satellite and none for all the values (make sure to select Gaps in views)
	 * - - Select Gap table checkbox
	 * - - Click OK and click Download/Zip Icon 	
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - SSRS will have the Gap table. The Gap Table will have numbers corresponding to the gaps in the Compliance View with a check box next to each number. The numbers will run sequentially from left to right and then top to bottom. The numbers in the table should exactly match the number of gaps in the View
	 *	- - SSRS Gap table should not show Gaps which are completely covered by FoV and LISA
	 *	- - Views will have the Gaps
	 */
	@Test
	public void TC1090_GenerateComplianceReportWhenGapTableGapsInViewsSectionSelected() throws Exception {
		Log.info("\nRunning TC1090_GenerateComplianceReportWhenGapTableGapsInViewsSectionSelected ...");
	
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	
		String testCaseID = "TC1090";
		String rptTitle = testCaseID + " Report" + testSetup.getRandomNumber();

		complianceReportsPage.open();
	
		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();
	
		tableMap.put(KEYINDTB, "0");
		tableMap.put(KEYISOANA, "0");
		tableMap.put(KEYGAPTB, "0");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tableMap.put(KEYASSETCASTIRON, "1");
		tableMap.put(KEYASSETCOPPER, "1");
		tableMap.put(KEYASSETOTHERPLASTIC, "1");
		tableMap.put(KEYASSETPEPLASTIC, "1");
		tableMap.put(KEYASSETPROTECTEDSTEEL, "1");
		tableMap.put(KEYASSETUNPROTECTEDSTEEL, "1");
		tableMap.put(KEYBOUNDARYDISTRICT, "0");
		tableMap.put(KEYBOUNDARYDISTRICTPLAT, "0");
		tablesList.add(tableMap);
	
		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		viewList.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList.add(createViewsMapTable("Second View", "1", "1", "1", "1", "1", "1", "1", "1", "0", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList.add(createViewsMapTable("Third View", "1", "1", "1", "1", "1", "1", "1", "1", "0", "None"));
		
		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);
	
		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);

		rpt.setCustomBoundaryInfo(X_OFFSET, Y_OFFSET, RECT_HEIGHT, RECT_WIDTH);
		complianceReportsPage.waitForPageLoad();
		
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();
		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			if (tablesList != null) {
				if ((tablesList.get(0).get(KEYPCA).equals("1")) || (tablesList.get(0).get(KEYPCRA).equals("1"))) {
					assertTrue(complianceReportsPage.verifyGapsTable(testSetup.getDownloadPath(), rptTitle));
					//assertTrue(complianceReportsPage.verifyViewsImages(testSetup.getDownloadPath(), rptTitle, testCaseID, destViewTitle));
				}
			}
		}else
	fail("\nTestcase TC1090 failed.\n");
	
	
	}

	public static HashMap<String, String> createViewsMapTable(String viewName, String lisa, String fov, String breadcrumb, String indications, String isotopic, String annotation, String gap, String asset, String boundary, String map) {
		HashMap<String, String> viewMap = new HashMap<String, String>();
		viewMap.put(KEYVIEWNAME, viewName);
		viewMap.put(KEYLISA, lisa);
		viewMap.put(KEYFOV, fov);
		viewMap.put(KEYBREADCRUMB, breadcrumb);
		viewMap.put(KEYINDICATIONS, indications);
		viewMap.put(KEYISOTOPICCAPTURE, isotopic);
		viewMap.put(KEYANNOTATION, annotation);
		viewMap.put(KEYGAPS, gap);
		viewMap.put(KEYASSETS, asset);
		viewMap.put(KEYBOUNDARIES, boundary);
		viewMap.put(KEYBASEMAP, map);
		return viewMap;
	}

}
