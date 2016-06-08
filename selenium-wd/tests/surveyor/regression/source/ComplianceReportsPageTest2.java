package surveyor.regression.source;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.source.Log;
import common.source.WebElementExtender;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.CUSTOMERNAMEPREFIX;
import static surveyor.scommon.source.SurveyorConstants.CUSUSERROLEUA;
import static surveyor.scommon.source.SurveyorConstants.EULASTRING;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPHEIGHT;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPWIDTH;
import static surveyor.scommon.source.SurveyorConstants.KEYANNOTATION;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYBASEMAP;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARIES;
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
import static surveyor.scommon.source.SurveyorConstants.KEYPCF;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.CUSDRVETHSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.REGBASEUSERNAME;
import static surveyor.scommon.source.SurveyorConstants.RNELAT;
import static surveyor.scommon.source.SurveyorConstants.RNELON;
import static surveyor.scommon.source.SurveyorConstants.RSWLAT;
import static surveyor.scommon.source.SurveyorConstants.RSWLON;
import static surveyor.scommon.source.SurveyorConstants.NELAT;
import static surveyor.scommon.source.SurveyorConstants.NELON;
import static surveyor.scommon.source.SurveyorConstants.SWLAT;
import static surveyor.scommon.source.SurveyorConstants.SWLON;
import static surveyor.scommon.source.SurveyorConstants.NELAT_SMALL;
import static surveyor.scommon.source.SurveyorConstants.NELON_SMALL;
import static surveyor.scommon.source.SurveyorConstants.SWLAT_SMALL;
import static surveyor.scommon.source.SurveyorConstants.SWLON_SMALL;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEMT;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataprovider.ReportDataProvider;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.ComplianceReportsPage.ComplianceReportButtonType;
import surveyor.scommon.source.Reports.SurveyModeFilter;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest2 extends BaseReportsPageActionTest {

	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;

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
	@Ignore //Need to Enable and Disable Accounts
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
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tableMap.put(KEYPCF, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "0", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);
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
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tableMap.put(KEYPCF, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "0", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			assertTrue(complianceReportsPage.verifyComplianceReportStaticText(rpt));
			if (tablesList != null) {
				if (tablesList.get(0).get(KEYINDTB).equals("1")) {
					assertTrue(complianceReportsPage.verifyIndicationTable(testSetup.getDownloadPath(), rptTitle));
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
		listBoundary1.add(NELAT);
		listBoundary1.add(NELON);
		listBoundary1.add(SWLAT);
		listBoundary1.add(SWLON);

		List<String> listBoundary2 = new ArrayList<String>();
		listBoundary2.add(IMGMAPHEIGHT);
		listBoundary2.add(IMGMAPWIDTH);
		listBoundary2.add(RNELAT);
		listBoundary2.add(RNELON);
		listBoundary2.add(RSWLAT);
		listBoundary2.add(RSWLON);

		List<Map<String, String>> tablesList1 = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap1 = new HashMap<String, String>();
		tableMap1.put(KEYINDTB, "1");
		tableMap1.put(KEYISOANA, "1");
		tableMap1.put(KEYGAPTB, "0");
		tableMap1.put(KEYPCA, "0");
		tableMap1.put(KEYPCRA, "0");
		tablesList1.add(tableMap1);

		List<Map<String, String>> tablesList2 = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap2 = new HashMap<String, String>();
		tableMap2.put(KEYINDTB, "1");
		tableMap2.put(KEYISOANA, "1");
		tableMap2.put(KEYGAPTB, "1");
		tableMap2.put(KEYPCA, "0");
		tableMap2.put(KEYPCRA, "0");
		tablesList2.add(tableMap2);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));

		List<Map<String, String>> viewList2 = new ArrayList<Map<String, String>>();
		viewList2.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt1 = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary1, tablesList1, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt1);
		assertTrue(complianceReportsPage.getAssetErrorText().getText().equals("Please make sure your selected boundary is less than 1.5 sq km when Gaps are selected"));

		testEnvironmentAction.idleForSeconds(String.valueOf(10), NOTSET);
		complianceReportsPage.clickOnCancelBtn();

		ReportsCompliance rpt2 = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary1, tablesList2, "", tagList, "", "", viewList2, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt2);
		assertTrue(complianceReportsPage.getAssetErrorText().getText().equals("Please make sure your selected boundary is less than 1.5 sq km when Gaps are selected"));

		testEnvironmentAction.idleForSeconds(String.valueOf(10), NOTSET);
		complianceReportsPage.clickOnCancelBtn();

		ReportsCompliance rpt3 = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary2, tablesList1, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt3);
		complianceReportsPage.waitForPageLoad();
		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt3, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
		}else{
			fail("\nTestcase TC1038 failed.\n");
		}
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
		listBoundary.add(RNELAT);
		listBoundary.add(RNELON);
		listBoundary.add(RSWLAT);
		listBoundary.add(RSWLON);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "0", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			if (tablesList != null) {
				if ((tablesList.get(0).get(KEYGAPTB).equals("1"))) {
					assertTrue(complianceReportsPage.verifyGapsTable(testSetup.getDownloadPath(), rptTitle));
				}
			}
		}else
			fail("\nTestcase TC1041 failed.\n");

	}

	/**
	 * Test Case ID: TC1091_GenerateComplianceReportWhenGapTableSelectedButGapsPresentViewsSectionNotSelected
	 * Script: -  
	 *  - - On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *  - - TimeZone : PST, Report Mode: Standard, Exclusion Radius: 0
	 *  - - Click Custom Boundary
	 *  - - Click Lat/Long Map Selector
	 *  - - Press the Shift key, then right-click and drag to select the desired area on the map and click OK
	 *  - - (Select area smaller then or equal to 1.5 sqkms)
	 *  - - Select one or more surveys
	 *  - - Add View with base map value: map, satellite and none (don't select Gaps in views)
	 *  - - Select Gap table checkbox
	 *  - - Click OK and click Download/Zip Icon	
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - SSRS will have the Gap table. The Gap Table will have numbers corresponding to the gaps in the Compliance View with a check box next to each number. The numbers will run sequentially from left to right and then top to bottom. The numbers in the table should exactly match the number of gaps in the View
	 *	- - SSRS Gap table should not show Gaps which are completely covered by FoV and LISA
	 *	- - Views will not show Gaps
	 */

	//Using NetLat and NetLong Selector as it needs specific area where peaks are present

	@Test
	public void TC1091_GenerateComplianceReportWhenGapTableSelectedButGapsPresentViewsSectionNotSelected() throws Exception {
		Log.info("\nRunning TC1091_GenerateComplianceReportWhenGapTableSelectedButGapsPresentViewsSectionNotSelected ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

		String testCaseID = "TC1091";
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
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "0", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList1.add(createViewsMapTable("Second View", "1", "1", "1", "1", "1", "1", "0", "1", "1", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList1.add(createViewsMapTable("Third View", "1", "1", "1", "1", "1", "1", "0", "1", "1", "None"));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);
		complianceReportsPage.waitForPageLoad();

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();
		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			if (tablesList != null) {
				if ((tablesList.get(0).get(KEYGAPTB).equals("1"))) {
					assertTrue(complianceReportsPage.verifyGapsTable(testSetup.getDownloadPath(), rptTitle));
				}
			}
		}else
			fail("\nTestcase TC1091 failed.\n");
	}

	/**
	 * Test Case ID: TC1092_GenerateComplianceReportWhenGapsPresentViewsSectionSelectedButGapTableNotSelected
	 * Script: -  	
	 *	- -  On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- -  TimeZone : PST, Report Mode: Standard, Exclusion Radius: 0
	 *	- -  Click Custom Boundary
	 *	- -  Click Lat/Long Map Selector
	 *	- -  Press the Shift key, then right-click and drag to select the desired area on the map and click OK
	 *	- -  (Select area smaller then or equal to 1.5 sqkms)
	 *	- -  Select one or more surveys
	 *	- -  Add View with base map value: map, satellite and none (select Gaps in views)
	 *	- -  Do not select Gap table check box (Keep it unchecked)
	 *	- -  Click OK and click Download/Zip Icon
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - Views will show Gaps
	 *	- - SSRS will not show Gaps Table (not even the header)--
	 */

	//** - - SSRS will not show Gaps Table (not even the header) -- This step is not verified as Gap Gird and Gap Table might be deprecated**

	@Test
	public void TC1092_GenerateComplianceReportWhenGapsPresentViewsSectionSelectedButGapTableNotSelected() throws Exception {
		Log.info("\nRunning TC1092_GenerateComplianceReportWhenGapsPresentViewsSectionSelectedButGapTableNotSelected ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

		String testCaseID = "TC1092";
		String rptTitle = testCaseID + " Report" + testSetup.getRandomNumber();

		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "0");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList1.add(createViewsMapTable("Second View", "1", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList1.add(createViewsMapTable("Third View", "1", "1", "1", "1", "1", "1", "1", "1", "1", "None"));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		// Selenium bug: https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/5233
		// ISSUE: Selenium won't click at the correct offset in Lat/Long map selector.
		// WORKAROUND: Use input text fields instead of using Lat/Long -> rpt.setCustomBoundaryInfo(X_OFFSET, Y_OFFSET, RECT_HEIGHT, RECT_WIDTH);
		listBoundary.add(NELAT_SMALL);
		listBoundary.add(NELON_SMALL);
		listBoundary.add(SWLAT_SMALL);
		listBoundary.add(SWLON_SMALL);
		
		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);
		complianceReportsPage.waitForPageLoad();

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();
		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
		}else
			fail("\nTestcase TC1092 failed.\n");
	}

	/**
	 * Test Case ID: TC1237_GenerateComplianceReportWhenGapTableGapsInOneOfViewsSelected
	 * Script: -  	
	 *	- - On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- - TimeZone : PST, Report Mode: Standard, Exclusion Radius: 0
	 *	- - Click Custom Boundary
	 *	- - Click Lat/Long Map Selector
	 *	- - Press the Shift key, then right-click and drag to select the desired area on the map and click OK
	 *	- - (Select area smaller then or equal to 1.5 sqkms)
	 *	- - Select one or more surveys
	 *	- - Add View1 with base map value: map- for all the values (make sure to select Gaps in views)
	 *	- - Add View2 with base map value: satellite for all the values (except Gaps)
	 *	- - Select Gap table checkbox
	 *	- - Click OK and click Download/Zip Icon
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - SSRS will have the Gap table. The Gap Table will have numbers corresponding to the gaps in the Compliance View with a check box next to each number. The numbers will run sequentially from left to right and then top to bottom. The numbers in the table should exactly match the number of gaps in the View
	 *	- - SSRS Gap table should not show Gaps which are completely covered by FoV and LISA
	 *	- - View1 will have Gaps information
	 *	- - View2 will not have gaps information
	 */

	//Using NetLat and NetLong for this test case instead of custom boundary
	@Test
	public void TC1237_GenerateComplianceReportWhenGapTableGapsInOneOfViewsSelected() throws Exception {
		Log.info("\nRunning TC1237_GenerateComplianceReportWhenGapTableGapsInOneOfViewsSelected ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

		String testCaseID = "TC1237";
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
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "0");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList1.add(createViewsMapTable("Second View", "1", "1", "1", "1", "1", "1", "0", "1", "1", Resources.getResource(ResourceKeys.Constant_Map)));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);
		complianceReportsPage.waitForPageLoad();

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();
		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			if (tablesList != null) {
				if ((tablesList.get(0).get(KEYGAPTB).equals("1"))) {
					assertTrue(complianceReportsPage.verifyShowCoverageTable(testSetup.getDownloadPath(), rptTitle));
					assertTrue(complianceReportsPage.verifyCoverageValuesTable(testSetup.getDownloadPath(), rptTitle, tablesList.get(0)));
					assertTrue(complianceReportsPage.verifyGapsTable(testSetup.getDownloadPath(), rptTitle));
				}
			}
		}else
			fail("\nTestcase TC1237 failed.\n");
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
	@Test
	public void TC1257_GenerateComplianceReportSurveyHavingMultipleFieldNotesIndications() throws Exception {
		Log.info("\nRunning TC1257_GenerateComplianceReportSurveyHavingMultipleFieldNotesIndications ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

		String testCaseID = "TC1257";
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
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "0");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "0", "0", "0", "1", "1", "1", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList1.add(createViewsMapTable("Second View", "0", "0", "0", "1", "1", "1", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			assertTrue(complianceReportsPage.verifyComplianceReportStaticText(rpt));
			if (tablesList != null) {
				if (tablesList.get(0).get(KEYINDTB).equals("1") || tablesList.get(0).get(KEYISOANA).equals("1")) {
					assertTrue(complianceReportsPage.verifyIndicationTable(testSetup.getDownloadPath(), rptTitle));
					assertTrue(complianceReportsPage.verifyIsotopicAnalysisTable(testSetup.getDownloadPath(), rptTitle));
				}
			}
		}else
			fail("\nTestcase TC1257 failed.\n");
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
		listBoundary.add(RNELAT);
		listBoundary.add(RNELON);
		listBoundary.add(RSWLAT);
		listBoundary.add(RSWLON);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "0");
		tableMap.put(KEYPCA, "1");
		tableMap.put(KEYPCRA, "1");
		tablesList.add(tableMap);

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "1", "1", "1", "1", "0", "0", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			assertTrue(complianceReportsPage.verifyComplianceReportStaticText(rpt));
			if (tablesList != null) {
				if ((tablesList.get(0).get(KEYPCA).equals("1")) || (tablesList.get(0).get(KEYPCRA).equals("1"))) {
					assertTrue(complianceReportsPage.verifyShowCoverageTable(testSetup.getDownloadPath(), rptTitle));
					assertTrue(complianceReportsPage.verifyCoverageValuesTable(testSetup.getDownloadPath(), rptTitle, tablesList.get(0)));
				}
				assertTrue(complianceReportsPage.verifyViewsTable(testSetup.getDownloadPath(), rptTitle, viewList1));
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
	@Test
	public void TC1268_ValidationMessageShouldDisplayedUserCustomerBoundaryAreaSelectionWhenGapsAreSelectedWhileGeneratingReport() throws Exception {
		Log.info("\nRunning TC1268_ValidationMessageShouldDisplayedUserCustomerBoundaryAreaSelectionWhenGapsAreSelectedWhileGeneratingReport ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

		String testCaseID = "TC1268";
		String rptTitle = testCaseID + " Report" + testSetup.getRandomNumber();
		String smallBoundary = "TestPlat-Auto-1.5km";
		String bigBoundary = "Level 1";
		
		complianceReportsPage.open();

		complianceReportsPage.open();
		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);

		List<Map<String, String>> tablesList1 = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap1 = new HashMap<String, String>();
		tableMap1.put(KEYINDTB, "1");
		tableMap1.put(KEYISOANA, "1");
		tableMap1.put(KEYGAPTB, "0");
		tableMap1.put(KEYPCA, "0");
		tableMap1.put(KEYPCRA, "0");
		tableMap1.put(KEYPCF, "0");
		tablesList1.add(tableMap1);

		List<Map<String, String>> tablesList2 = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap2 = new HashMap<String, String>();
		tableMap2.put(KEYINDTB, "1");
		tableMap2.put(KEYISOANA, "1");
		tableMap2.put(KEYGAPTB, "1");
		tableMap2.put(KEYPCA, "0");
		tableMap2.put(KEYPCRA, "0");
		tableMap2.put(KEYPCF, "0");
		tablesList2.add(tableMap2);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));

		List<Map<String, String>> viewList2 = new ArrayList<Map<String, String>>();
		viewList2.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "0", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt1 = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList1, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		rpt1.setCustomerBoundaryInfo(ReportsCompliance.CustomerBoundaryFilterType.BigBoundary, bigBoundary);
		complianceReportsPage.addNewReport(rpt1);
		assertTrue(complianceReportsPage.getAssetErrorText().getText().equals("Please make sure your selected boundary is less than 1.5 sq km when Gaps are selected"));

		testEnvironmentAction.idleForSeconds(String.valueOf(10), NOTSET);
		complianceReportsPage.clickOnCancelBtn();
		complianceReportsPage.open();

		ReportsCompliance rpt2 = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList2, "", tagList, "", "", viewList2, SurveyModeFilter.Standard);
		rpt2.setCustomerBoundaryInfo(ReportsCompliance.CustomerBoundaryFilterType.BigBoundary, bigBoundary);
		complianceReportsPage.addNewReport(rpt2);
		assertTrue(complianceReportsPage.getAssetErrorText().getText().equals("Please make sure your selected boundary is less than 1.5 sq km when Gaps are selected"));

		testEnvironmentAction.idleForSeconds(String.valueOf(10), NOTSET);
		complianceReportsPage.clickOnCancelBtn();
		complianceReportsPage.open();

		ReportsCompliance rpt3 = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList1, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		rpt3.setCustomerBoundaryInfo(ReportsCompliance.CustomerBoundaryFilterType.SmallBoundary, smallBoundary);
		complianceReportsPage.addNewReport(rpt3);
		complianceReportsPage.waitForPageLoad();
		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt3, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
		}else
			fail("\nTestcase TC1268 failed.\n");

		complianceReportsPage.open();

		ReportsCompliance rpt4 = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList2, "", tagList, "", "", viewList2, SurveyModeFilter.Standard);
		rpt4.setCustomerBoundaryInfo(ReportsCompliance.CustomerBoundaryFilterType.SmallBoundary, smallBoundary);
		complianceReportsPage.addNewReport(rpt4);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt4, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
		}else
			fail("\nTestcase TC1268 failed.\n");

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
		listBoundary.add(RNELAT);
		listBoundary.add(RNELON);
		listBoundary.add(RSWLAT);
		listBoundary.add(RSWLON);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "0", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);

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
		listBoundary.add(RNELAT);
		listBoundary.add(RNELON);
		listBoundary.add(RSWLAT);
		listBoundary.add(RSWLON);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "0", "1", "1", "1", "1", "1", "1", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);

		complianceReportsPage.addNewReport(rpt);
		assertTrue(complianceReportsPage.getAssetErrorText().getText().equals("Selected Asset Layer(s), Please select at least one view with Assets"));
		assertTrue(complianceReportsPage.getBoundaryErrorText().getText().equals("Selected Boundary Layer(s), Please select at least one view with Boundaries"));
	}

	/**
	 * Test Case ID: TC1301_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecast
	 * Script: -  	
	 *	- - Log in as Picarro Admin
	 *	- - On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- - Time Zone : PST, Survey Mode: Standard, Exclusion Radius: 0
	 *	- - Add 2 or 3 surveys with different tag values
	 *	- - Select Customer boundary and select any Plat 
	 *	- - Select Indication table, Isotopic Analysis table
	 *	- - Select Percent Coverage Forecast check box
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 *	- - Download PDF, ZIP (PDF)
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)
	 *	- - Additional Surveys, Probability to Obtain 70% Coverage (No decimals should be present)
	 */
	@Test
	public void TC1301_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecast() throws Exception {
		Log.info("\nRunning TC1301_GenerateComplianceReportPicarroAdminIncludePercentCoverageForecast ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		
		String testCaseID = "TC1301";
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
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "0", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);
		tagList.add("Standard");
		

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);
		
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
			fail("\nTestcase TC1301 failed.\n");
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
		listBoundary.add(RNELAT);
		listBoundary.add(RNELON);
		listBoundary.add(RSWLAT);
		listBoundary.add(RSWLON);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "0", "1", "1", "1", "1", "1", "1", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));

		List<Map<String, String>> viewList2 = new ArrayList<Map<String, String>>();
		viewList2.add(createViewsMapTable("First View", "0", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));

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
		testEnvironmentAction.idleForSeconds(String.valueOf(10), NOTSET);
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
		listBoundary.add(RNELAT);
		listBoundary.add(RNELON);
		listBoundary.add(RSWLAT);
		listBoundary.add(RSWLON);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "0", "1", "1", "1", "1", "1", "1", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));

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
		testEnvironmentAction.idleForSeconds(String.valueOf(10), NOTSET);
		complianceReportsPage.getRptFirstAsset().click();
		complianceReportsPage.getRptSmallBoundary().click();
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
		assertTrue(complianceReportsPage.getPercentCoverReportArea().isDisplayed());
		complianceReportsPage.clickOnCancelBtn();

		String copyImgXPath = "//*[@id='datatable']/tbody/tr[1]/td[5]/a[2]/img";
		WebElement copyImg = driver.findElement(By.xpath(copyImgXPath));

		copyImg.click();
		complianceReportsPage.waitForCopyReportPagetoLoad();
		testEnvironmentAction.idleForSeconds(String.valueOf(10), NOTSET);
		assertTrue(complianceReportsPage.getPercentCoverReportArea().isDisplayed());
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

		complianceReportsPage.clickOnNewComplianceReportBtn();
		assertTrue(complianceReportsPage.getPercentCoverForecast().isDisplayed());
		complianceReportsPage.clickOnCancelBtn();
		complianceReportsPage.waitForPageLoad();
		
		String testCaseID = "TC1306";
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
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "0", "1", "1", "1", "1", "1", "1", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));

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
		assertTrue(complianceReportsPage.getPercentCoverForecast().isDisplayed());
		complianceReportsPage.clickOnCancelBtn();
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
		assertFalse(WebElementExtender.isElementPresentAndDisplayed(complianceReportsPage.getPercentCoverForecast()));
		complianceReportsPage.clickOnCancelBtn();

		complianceReportsPage.clickOnFirstCopyComplianceBtn();
		complianceReportsPage.waitForCopyReportPagetoLoad();
		assertFalse(WebElementExtender.isElementPresentAndDisplayed(complianceReportsPage.getPercentCoverForecast()));
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
	@Test
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
		listBoundary.add(RNELAT);
		listBoundary.add(RNELON);
		listBoundary.add(RSWLAT);
		listBoundary.add(RSWLON);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "0", "1", "1", "1", "1", "1", "1", "0", "0", Resources.getResource(ResourceKeys.Constant_Satellite)));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();
		complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser());

		complianceReportsPage.findReportbySearch(rptTitle, testSetup.getLoginUser());


		try {
			complianceReportsPage.clickComplianceReportButton(rptTitle, testSetup.getLoginUser(), ComplianceReportButtonType.Resubmit);
			complianceReportsPage.waitForResubmitButton();
			complianceReportsPage.getBtnResubmitReport().click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			if (tablesList != null) {
				assertTrue(complianceReportsPage.verifyReportSurveyMetaDataFile(testSetup.getDownloadPath(), rptTitle));
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
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "0");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "0", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);
		
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();
		complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser());
		
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
	 * - - Log in to application as Customer admin user and navigate to New Compliance Report page
	 * - - Select Custom Boundary and provide Lat/Long co-ordinates
	 * - - Add 2 surveys with different tag value
	 * - - Select Percent Coverage Forecast check box
	 * - - Click OK
	 * Results: - 
	 *	- - User friendly error messages are displayed: "Selected Percent Coverage Forecast, Please select Customer Boundary"
	 */
	@Test
	public void TC1313_CheckErrorMesageDisplayedIfPercentCoverageForecastCheckBoxSelectedNewComplianceReportScreensAlongCustomBoundary() throws Exception {
		Log.info("\nRunning TC1313_CheckErrorMesageDisplayedIfPercentCoverageForecastCheckBoxSelectedNewComplianceReportScreensAlongCustomBoundary ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

		String testCaseID = "TC1313";
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
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "1");
		tableMap.put(KEYPCA, "1");
		tableMap.put(KEYPCRA, "1");
		tableMap.put(KEYPCF, "1");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "0", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);
		tagList.add("Standard");
		
		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);
		complianceReportsPage.addNewReport(rpt);
		assertTrue(complianceReportsPage.getAssetErrorText().getText().equals("Selected Percent Coverage Forecast, Please select Customer Boundary"));

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

		String testCaseID = "TC1313";
		String rptTitle = testCaseID + " Report" + testSetup.getRandomNumber();

		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);

		List<String> listBoundary2 = new ArrayList<String>();
		listBoundary2.add(IMGMAPHEIGHT);
		listBoundary2.add(IMGMAPWIDTH);
		listBoundary2.add(RNELAT);
		listBoundary2.add(RNELON);
		listBoundary2.add(RSWLAT);
		listBoundary2.add(RSWLON);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "1");
		tableMap.put(KEYPCA, "1");
		tableMap.put(KEYPCRA, "1");
		tableMap.put(KEYPCF, "1");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "0", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));

		List<String> tagList1 = new ArrayList<String>();
		tagList1.add(PICADMNSTDTAG);
		
		List<String> tagList2 = new ArrayList<String>();
		tagList2.add(PICADMNSTDTAG);
		tagList2.add("standard");

		// Selenium bug: https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/5233
		// ISSUE: Selenium won't click at the correct offset in Lat/Long map selector.
		// WORKAROUND: Use input text fields instead of using Lat/Long -> rpt.setCustomBoundaryInfo(X_OFFSET, Y_OFFSET, RECT_HEIGHT, RECT_WIDTH);
		listBoundary.add(NELAT_SMALL);
		listBoundary.add(NELON_SMALL);
		listBoundary.add(SWLAT_SMALL);
		listBoundary.add(SWLON_SMALL);
		
		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList1, "", "", viewList1, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);
		complianceReportsPage.addNewReport(rpt);
		assertTrue(complianceReportsPage.getAssetErrorText().getText().equals("Selected Percent Coverage Forecast, Please select Customer Boundary"));
		assertTrue(complianceReportsPage.getBoundaryErrorText().getText().equals("Selected Percent Coverage Forecast, Please select at least two surveys with different tags"));
		testEnvironmentAction.idleForSeconds(String.valueOf(10), NOTSET);
		complianceReportsPage.clickOnCancelBtn();
		
		listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		
		ReportsCompliance rpt2 = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList1, "", "", viewList1, SurveyModeFilter.Standard);
		rpt2.setViewLayersList(viewLayerList);
		rpt2.setCustomerBoundaryInfo(ReportsCompliance.CustomerBoundaryFilterType.SmallBoundary, "TestPlat-Auto-1.5km");
		complianceReportsPage.addNewReport(rpt2);
		Log.info("!!!!!" + complianceReportsPage.getAssetErrorText().getText()+ "!!!!!");
		assertTrue(complianceReportsPage.getAssetErrorText().getText().equals("Selected Percent Coverage Forecast, Please select at least two surveys with different tags"));
		testEnvironmentAction.idleForSeconds(String.valueOf(10), NOTSET);
		complianceReportsPage.clickOnCancelBtn();
		
		ReportsCompliance rpt3 = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary2, tablesList, "", tagList1, "", "", viewList1, SurveyModeFilter.Standard);
		rpt3.setViewLayersList(viewLayerList);
		
		complianceReportsPage.addNewReport(rpt3);
		assertTrue(complianceReportsPage.getAssetErrorText().getText().equals("Selected Percent Coverage Forecast, Please select Customer Boundary"));
		testEnvironmentAction.idleForSeconds(String.valueOf(10), NOTSET);
		complianceReportsPage.clickOnCancelBtn();
	}

	/**
	 * Test Case ID: TC1318_GenerateMultipleComplianceReportsDifferentAssetLayerIncludePercentCoverageForecast
	 * Script: -  	
	 *	- - Log in as Customer Admin (eg. PG&E util admin)
	 *	- - On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 *	- - Time Zone : PST, Survey Mode: Standard
	 *	- - Select Customer boundary and select any Plat 
	 *	- - Select Indication table, Isotopic Analysis table
	 *	- - Select Percent Coverage Forecast check box
	 *	- - Asset Layer : All (Eg. Copper and Protected Steel)
	 *	- - Add View with base map value: map
	 *	- - Click on OK and click Compliance Viewer button
	 *	- - Download PDF, ZIP (PDF)
	 *	- - Generate other report with same parameter as above but make sure you select only Copper Asset layer
	 *	- - Generate other report with same parameter as above but make sure no assets are selected
	 * Results: - 
	 *	- - Report generated successfully
	 *	- - Percent Coverage value is not dependent of Assets so value should not change
	 *	- - Percent Service Coverage with LISAs , Percent Service Coverage Without LISAs (No decimals should be present for the calculation)
	 *  - - Additional Surveys , Probability to Obtain 70% Coverage
	 */
	@Test //using user as "picarro admin"
	public void TC1318_GenerateMultipleComplianceReportsDifferentAssetLayerIncludePercentCoverageForecast() throws Exception {
		Log.info("\nRunning TC1318_GenerateMultipleComplianceReportsDifferentAssetLayerIncludePercentCoverageForecast ...");

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);

		String testCaseID = "TC1318";
		String rptTitle = testCaseID + " Report" + testSetup.getRandomNumber();
		String rptTitle2 = testCaseID + " Report" + testSetup.getRandomNumber();
		String rptTitle3 = testCaseID + " Report" + testSetup.getRandomNumber();
		
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);

		List<String> listBoundary2 = new ArrayList<String>();
		listBoundary2.add(IMGMAPHEIGHT);
		listBoundary2.add(IMGMAPWIDTH);
		listBoundary2.add(RNELAT);
		listBoundary2.add(RNELON);
		listBoundary2.add(RSWLAT);
		listBoundary2.add(RSWLON);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "0");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tableMap.put(KEYPCF, "1");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<Integer> assetRowIDs2 = Arrays.asList(8, 9, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs2 = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList2 = new ArrayList<Map<String, String>>();
		viewLayerList2.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs2, boundaryRowIDs2));

		
		List<Map<String, String>> viewList1 = new ArrayList<Map<String, String>>();
		List<Map<String, String>> viewList3 = new ArrayList<Map<String, String>>();
		viewList1.add(createViewsMapTable("First View", "0", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList3.add(createViewsMapTable("First View", "0", "1", "1", "1", "1", "1", "1", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);
		tagList.add(CUSDRVETHSTDTAG);
		
		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);
		rpt.setCustomerBoundaryInfo(ReportsCompliance.CustomerBoundaryFilterType.SmallBoundary, "TestPlat-Auto-1.5km");
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			if (tablesList != null) {
				if ((tablesList.get(0).get(KEYPCF).equals("1"))) {
					assertTrue(complianceReportsPage.verifyShowCoverageTable(testSetup.getDownloadPath(), rptTitle));
				}
			}
		}
		else
			fail("\nTestcase TC1318 failed.\n");

		complianceReportsPage.open();
		ReportsCompliance rpt2 = new ReportsCompliance(rptTitle2, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		rpt2.setViewLayersList(viewLayerList2);
		rpt2.setCustomerBoundaryInfo(ReportsCompliance.CustomerBoundaryFilterType.SmallBoundary, "TestPlat-Auto-1.5km");
		complianceReportsPage.addNewReport(rpt2);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle2, testSetup.getLoginUser(), testCaseID+"_2"))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt2, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle2, testSetup.getLoginUser()));
			if (tablesList != null) {
				if ((tablesList.get(0).get(KEYPCF).equals("1"))) {
					assertTrue(complianceReportsPage.verifyShowCoverageTable(testSetup.getDownloadPath(), rptTitle2));
				}
			}
		}
		else
			fail("\nTestcase TC1318 failed.\n");

		complianceReportsPage.open();
		ReportsCompliance rpt3 = new ReportsCompliance(rptTitle3, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList3, SurveyModeFilter.Standard);
		rpt3.setCustomerBoundaryInfo(ReportsCompliance.CustomerBoundaryFilterType.SmallBoundary, "TestPlat-Auto-1.5km");
		complianceReportsPage.addNewReport(rpt3);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle3, testSetup.getLoginUser(), testCaseID+"_3"))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt3, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle3, testSetup.getLoginUser()));
			if (tablesList != null) {
				if ((tablesList.get(0).get(KEYPCF).equals("1"))) {
					assertTrue(complianceReportsPage.verifyShowCoverageTable(testSetup.getDownloadPath(), rptTitle3));
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
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "0");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tableMap.put(KEYPCF, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		viewList.add(createViewsMapTable("First View", "1", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Satellite)));
		viewList.add(createViewsMapTable("Second View", "1", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList.add(createViewsMapTable("Third View", "1", "1", "1", "1", "1", "1", "1", "1", "1", "None"));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		// Selenium bug: https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/5233
		// ISSUE: Selenium won't click at the correct offset in Lat/Long map selector.
		// WORKAROUND: Use input text fields instead of using Lat/Long -> rpt.setCustomBoundaryInfo(X_OFFSET, Y_OFFSET, RECT_HEIGHT, RECT_WIDTH);
		listBoundary.add(NELAT_SMALL);
		listBoundary.add(NELON_SMALL);
		listBoundary.add(SWLAT_SMALL);
		listBoundary.add(SWLON_SMALL);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);
		rpt.setListBoundary(listBoundary);
		
		complianceReportsPage.waitForPageLoad();
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();
		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			if (tablesList != null) {
				if ((tablesList.get(0).get(KEYPCA).equals("1")) || (tablesList.get(0).get(KEYPCRA).equals("1"))) {
					assertTrue(complianceReportsPage.verifyGapsTable(testSetup.getDownloadPath(), rptTitle));
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
