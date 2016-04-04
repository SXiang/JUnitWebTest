package surveyor.regression.source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.source.Log;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.CUSTOMERNAMEPREFIX;
import static surveyor.scommon.source.SurveyorConstants.EULASTRING;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPHEIGHT;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPWIDTH;
import static surveyor.scommon.source.SurveyorConstants.KEYANNOTATION;
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
import static surveyor.scommon.source.SurveyorConstants.KEYISOANA;
import static surveyor.scommon.source.SurveyorConstants.KEYISOTOPICCAPTURE;
import static surveyor.scommon.source.SurveyorConstants.KEYLISA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCRA;
import static surveyor.scommon.source.SurveyorConstants.KEYVIEWNAME;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.RNELAT;
import static surveyor.scommon.source.SurveyorConstants.RNELON;
import static surveyor.scommon.source.SurveyorConstants.RSWLAT;
import static surveyor.scommon.source.SurveyorConstants.RSWLON;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEMT;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.actions.HomePageActions;
import surveyor.scommon.actions.TestEnvironmentActions;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.ComplianceReportsPage;
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
		complianceReportsPage = new ComplianceReportsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, complianceReportsPage);
		testEnvironmentAction = new TestEnvironmentActions();
	}

	/**
	 * Test Case ID: TC720_ShapefileMetaDataReportFeaturePermissionExistingCustomer_NewComplianceReportGeneration
	 * Script: -  	
	 *	- - On Manage Customers page, select a customer that does not have Report Shapefile and Report Meta Data permission enabled and click the Edit button- Confirm that the Account Enabled box is checked and check the Report Shape File and Report Meta data button
	 *	- - Login as Customer User
	 *	- - On the Compliance Reports page, generate the report and select LISAs, FOV, Breadcrumb, Gaps and/or Assets
	 *	- - Click on Compliance Viewer button
	 *	- - Click on the Shape file and meta file export button
	 *	- - Compliance Viewer dialog has Shape (ZIP) and Meta data (ZIP) export buttons
	 * Results: - 
	 *	- - User can download the Shape files and meta data files successfully
	 */
	@Test
	public void TC720_ShapefileMetaDataReportFeaturePermissionExistingCustomer_NewComplianceReportGeneration() throws Exception {
		Log.info("\nRunning TC720_ShapefileMetaDataReportFeaturePermissionExistingCustomer_NewComplianceReportGeneration ...");
		
		String testCaseID = "TC720";
		String customerName = CUSTOMERNAMEPREFIX + testSetup.getRandomNumber() + testCaseID;
		String eula = customerName + ": " + EULASTRING;
		String rptTitle = testCaseID + " Report" + testSetup.getRandomNumber();
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		
		manageCustomersPage.open();
		manageCustomersPage.addNewCustomer(customerName, eula, false);		
		assertTrue(manageCustomersPage.findExistingCustomer(customerName, false));
		assertTrue(manageCustomersPage.findCustomerAndOpenEditPage(customerName));
		manageCustomersPage.enabledDisableCustomer(true);
		manageCustomersPage.getRptMetaDataCheckBox().click();
		manageCustomersPage.getRptMetaDataCheckBox().click();
		manageCustomersPage.getbtnOk().click();
		
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
				if ((tablesList.get(0).get(KEYPCA).equals("1")) || (tablesList.get(0).get(KEYPCRA).equals("1"))) {
					assertTrue(complianceReportsPage.verifyShowCoverageTable(testSetup.getDownloadPath(), rptTitle));
					assertTrue(complianceReportsPage.verifyCoverageValuesTable(testSetup.getDownloadPath(), rptTitle, tablesList.get(0)));
				}
				if (("Picarro").equalsIgnoreCase("Picarro")) {
					assertTrue(complianceReportsPage.verifyLayersTable(testSetup.getDownloadPath(), rptTitle, tablesList.get(0)));
				}
				assertTrue(complianceReportsPage.verifyViewsTable(testSetup.getDownloadPath(), rptTitle, viewList));
				assertTrue(complianceReportsPage.verifyDrivingSurveysTable(testSetup.getDownloadPath(), rptTitle));
				if (tablesList.get(0).get(KEYISOANA).equals("1")) {
					assertTrue(complianceReportsPage.verifyIsotopicAnalysisTable(testSetup.getDownloadPath(), rptTitle));
				}
				if (tablesList.get(0).get(KEYINDTB).equals("1")) {
					assertTrue(complianceReportsPage.verifyIndicationTable(testSetup.getDownloadPath(), rptTitle));
				}
			}
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
	 *	- - Lisa_Number present in Indications table should be sequential
	 */
	@Test
	public void TC824_CheckFileNameLisaNumbersArePresentComplianceReportPDF() throws Exception {
		Log.info("\nRunning TC824_CheckFileNameLisaNumbersArePresentComplianceReportPDF ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}

	/**
	 * Test Case ID: TC1038_ValidationMessageShouldDisplayedUserAreaSelectionWhenGapsAreSelectedWhileGeneratingReport
	 * Script: -  	
	 *	- - On the Compliance Reports page, click the New Compliance Report button
	 *	- - Message displayed to user: Please make sure your selected boundary is less than 1.5 sqkms when Gaps are selected
	 * Results: - 
	 *	- - Report is generated succesfully
	 */
	@Test
	public void TC1038_ValidationMessageShouldDisplayedUserAreaSelectionWhenGapsAreSelectedWhileGeneratingReport() throws Exception {
		Log.info("\nRunning TC1038_ValidationMessageShouldDisplayedUserAreaSelectionWhenGapsAreSelectedWhileGeneratingReport ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}
 
	/**
	 * Test Case ID: TC1041_StandardSurvey_GapBoxesUniqueNumbersPrescribedFormat
	 * Script: -  	
	 *	- - On the Compliance Reports page, click the New Compliance Report button
	 *	- - In the Views section, select Gaps but do not select LISAs, and click OK
	 *	- - When the report has finished generating, click on the thumbnail preview button and download the Compliance ZIP (PDF)
	 * Results: - 
	 *	- - The report view should show the map overlaid with a grid, with columns marked A to Z and rows marked 1 to n
	 *	- - The Gap numbers in the SSRS report should correspond to their location on the grid
	 *	- - The grid cells should be approximately 200 feet square
	 */
	@Test
	public void TC1041_StandardSurvey_GapBoxesUniqueNumbersPrescribedFormat() throws Exception {
		Log.info("\nRunning TC1041_StandardSurvey_GapBoxesUniqueNumbersPrescribedFormat ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}
 
	/**
	 * Test Case ID: TC1090_GenerateComplianceReportWhenGapTableGapsInViewsSectionSelected
	 * Script: -  	
	 * Results: - 
	 *	- - SSRS Gap table should not show Gaps which are completely covered by FoV and LISA- Views will have the Gaps
	 */
	@Test
	public void TC1090_GenerateComplianceReportWhenGapTableGapsInViewsSectionSelected() throws Exception {
		Log.info("\nRunning TC1090_GenerateComplianceReportWhenGapTableGapsInViewsSectionSelected ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}
 
	/**
	 * Test Case ID: TC1091_GenerateComplianceReportWhenGapTableSelectedButGapsPresentViewsSectionNotSelected
	 * Script: -  	
	 * Results: - 
	 *	- - SSRS Gap table should not show Gaps which are completely covered by FoV and LISA- Views will not show Gaps
	 */
	@Test
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
	@Test
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
	@Test
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
	@Test
	public void TC1257_GenerateComplianceReportSurveyHavingMultipleFieldNotesIndications() throws Exception {
		Log.info("\nRunning TC1257_GenerateComplianceReportSurveyHavingMultipleFieldNotesIndications ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
	}
 
	/**
	 * Test Case ID: TC1267_GenerateComplianceReportCustomerWhoDoesNotAssetLoaded
	 * Script: -  	
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button 
	 *	- - Add View: Select Indications, Field Notes , FOV, Breadcrumb, Lisa, indication Base Map Value : Map
	 *	- - Click on OK and click Download icon
	 *	- - In Views section, Assets and Boundaries check box is not present
	 *	- - In Optional Views Layers, Assets and Boundaries types are not displayed
	 * Results: - 
	 *	- - Report generated successfully having specified timezone and asset data for specified tag id and date range surveys
	 *	- - User is allowed to download the report
	 *	- - Export image should show the map for the specified Lat-Long boundary
	 */
	@Test
	public void TC1267_GenerateComplianceReportCustomerWhoDoesNotAssetLoaded() throws Exception {
		Log.info("\nRunning TC1267_GenerateComplianceReportCustomerWhoDoesNotAssetLoaded ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}
 
	/**
	 * Test Case ID: TC1268_ValidationMessageShouldDisplayedUserCustomerBoundaryAreaSelectionWhenGapsAreSelectedWhileGeneratingReport
	 * Script: -  	
	 *	- - On the Compliance Reports page, click the New Compliance Report button
	 *	- - Message displayed to user: Please make sure your selected boundary is less than 1.5 sqkms when Gaps are selected
	 * Results: - 
	 *	- - Report is generated succesfully
	 */
	@Test
	public void TC1268_ValidationMessageShouldDisplayedUserCustomerBoundaryAreaSelectionWhenGapsAreSelectedWhileGeneratingReport() throws Exception {
		Log.info("\nRunning TC1268_ValidationMessageShouldDisplayedUserCustomerBoundaryAreaSelectionWhenGapsAreSelectedWhileGeneratingReport ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}
 
	/**
	 * Test Case ID: TC1297_SoftwareVersionUIReportsPDFShouldMatch
	 * Script: -  	
	 *	- - Generate compliance, investigation, ref gas, system history reports
	 * Results: - 
	 *	- - Software version present at bottom of the page should be same as team city version
	 */
	@Test
	public void TC1297_SoftwareVersionUIReportsPDFShouldMatch() throws Exception {
		Log.info("\nRunning TC1297_SoftwareVersionUIReportsPDFShouldMatch ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}
 
	/**
	 * Test Case ID: TC1299_CheckFileNameOfCsvShapeFilesPresentMetaDataShapeFileZIPFolderRespectively
	 * Script: -  	
	 *	- - Generate Compliance report (Include surveys having indications, isotopic capture, field notes etc)
	 *	- - Click on Compliance Viewer
	 *	- - Click on Compliance PDF (Meta)
	 *	- - Click on Compliance PDF (Shape)
	 *	- Eg. ReportName: CR-DC3080
	 * Results: - 
	 *	- - User can open the files successfully without any error
	 */
	@Test
	public void TC1299_CheckFileNameOfCsvShapeFilesPresentMetaDataShapeFileZIPFolderRespectively() throws Exception {
		Log.info("\nRunning TC1299_CheckFileNameOfCsvShapeFilesPresentMetaDataShapeFileZIPFolderRespectively ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}
	
	/**
	 * Test Case ID: TC1300_UserFriendlyMessageShouldDisplayedIfUserIncludeAssetsBoundariesLayersOptionalViewLayersSection
	 * Script: -  	
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button
	 *	- - Add View with base map value: map and select Lisa, Indication, etc but don't select asset and boundaries check box
	 * Results: - 
	 *	- Selected Asset Layer(s), Please select at least one view with Assets
	 *	- Selected Boundary Layer(s), Please select at least one view with Boundaries
	 */
	@Test
	public void TC1300_UserFriendlyMessageShouldDisplayedIfUserIncludeAssetsBoundariesLayersOptionalViewLayersSection() throws Exception {
		Log.info("\nRunning TC1300_UserFriendlyMessageShouldDisplayedIfUserIncludeAssetsBoundariesLayersOptionalViewLayersSection ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

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
	@Test
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

	}
 
	/**
	 * Test Case ID: ButNotSelectedAssetBoundariesCheckBoxAddViewSectionCopyComplianceReportScreen
	 * Script: -  	
	 *	- - On Home Page, click Reports -& Compliance -& 'Copy Compliance Report' button
	 *	- - Add View with base map value: map and select Lisa, Indication, etc but don't select asset and boundaries check box
	 *	- Selected Asset Layer(s), Please select at least one view with Assets
	 * Results: - 
	 *	- Selected Boundary Layer(s), Please select at least one view with Boundaries
	 */
	@Test
	public void TC1304_UserFriendlyMessageShouldDisplayedIfUserIncludeAssetsBoundariesLayersOptionalViewLayersSection() throws Exception {
		Log.info("\nRunning TC1304_UserFriendlyMessageShouldDisplayedIfUserIncludeAssetsBoundariesLayersOptionalViewLayersSection ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}
 
	/**
	 * Test Case ID: TC1305_CheckPercentCoverageForecastCheckBoxPresentNewCopyComplianceReportScreensPicarroUser
	 * Script: -  	
	 * Results: - 
	 *	- - Click on Cancel and navigate to Copy compliance screen
	 */
	@Test
	public void TC1305_CheckPercentCoverageForecastCheckBoxPresentNewCopyComplianceReportScreensPicarroUser() throws Exception {
		Log.info("\nRunning TC1305_CheckPercentCoverageForecastCheckBoxPresentNewCopyComplianceReportScreensPicarroUser ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}
 
	/**
	 * Test Case ID: TC1306_CheckPercentCoverageForecastCheckBoxPresentNewCopyComplianceReportScreensOfCustomerUserHavingAssets
	 * Script: -  	
	 * Results: - 
	 *	- - Click on Cancel and navigate to Copy compliance screen
	 */
	@Test
	public void TC1306_CheckPercentCoverageForecastCheckBoxPresentNewCopyComplianceReportScreensOfCustomerUserHavingAssets() throws Exception {
		Log.info("\nRunning TC1306_CheckPercentCoverageForecastCheckBoxPresentNewCopyComplianceReportScreensOfCustomerUserHavingAssets ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}
 
	/**
	 * Test Case ID: TC1307_CheckPercentCoverageForecastCheckBoxNotPresentNewCopyComplianceReportScreensOfCustomerUserNotHavingAssets
	 * Script: -  	
	 * Results: - 
	 *	- - Click on Cancel and navigate to Copy compliance screen
	 */
	@Test
	public void TC1307_CheckPercentCoverageForecastCheckBoxNotPresentNewCopyComplianceReportScreensOfCustomerUserNotHavingAssets() throws Exception {
		Log.info("\nRunning TC1307_CheckPercentCoverageForecastCheckBoxNotPresentNewCopyComplianceReportScreensOfCustomerUserNotHavingAssets ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}
 
	/**
	 * Test Case ID: TC1310_CheckFileNamesOfCsvShapeFilesPresentMetaDataShapeFileZIPFolderRespectivelyWhenUserReprocessExistingOldReports
	 * Script: -  	
	 *	- - Reprocess the existing reports
	 *	- - Click on Compliance Viewer
	 *	- - Click on Compliance PDF (Meta)
	 *	- - Click on Compliance PDF (Shape)
	 *	- Eg. ReportName: CR-DC3080
	 * Results: - 
	 *	- - User can open the files successfully without any error
	 */
	@Test
	public void TC1310_CheckFileNamesOfCsvShapeFilesPresentMetaDataShapeFileZIPFolderRespectivelyWhenUserReprocessExistingOldReports() throws Exception {
		Log.info("\nRunning TC1310_CheckFileNamesOfCsvShapeFilesPresentMetaDataShapeFileZIPFolderRespectivelyWhenUserReprocessExistingOldReports ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}
 
	/**
	 * Test Case ID: TC1311_CheckFileNameOfCsvShapeFilesPresentMetaDataShapeFileZIPFolderRespectivelyUsingCopyFunctionality
	 * Script: -  	
	 *	- - Generate Compliance report (Include surveys having indications, isotopic capture, field notes etc) using Copy functionality
	 *	- - Click on Compliance Viewer
	 *	- - Click on Compliance PDF (Meta)
	 *	- - Click on Compliance PDF (Shape)
	 *	- Eg. ReportName: CR-DC3080
	 * Results: - 
	 *	- - User can open the files successfully without any error
	 */
	@Test
	public void TC1311_CheckFileNameOfCsvShapeFilesPresentMetaDataShapeFileZIPFolderRespectivelyUsingCopyFunctionality() throws Exception {
		Log.info("\nRunning TC1311_CheckFileNameOfCsvShapeFilesPresentMetaDataShapeFileZIPFolderRespectivelyUsingCopyFunctionality ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}
 
	/**
	 * Test Case ID: TC1313_CheckErrorMesageDisplayedIfPercentCoverageForecastCheckBoxSelectedNewComplianceReportScreensAlongCustomBoundary
	 * Script: -  	
	 * Results: - 
	 *	- - Add 2 surveys with different tag value
	 */
	@Test
	public void TC1313_CheckErrorMesageDisplayedIfPercentCoverageForecastCheckBoxSelectedNewComplianceReportScreensAlongCustomBoundary() throws Exception {
		Log.info("\nRunning TC1313_CheckErrorMesageDisplayedIfPercentCoverageForecastCheckBoxSelectedNewComplianceReportScreensAlongCustomBoundary ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */

	}
 
	/**
	 * Test Case ID: TC1315_CheckErrorMessagePresentIfPercentCoverageForecastCheckBoxSelectedCopyComplianceReportScreens
	 * Script: -  	
	 *	- - Add 2 or more surveys with different tag values
	 *	- Selected Percent Coverage Forecast, Please select Customer Boundary
	 *	- Selected Percent Coverage Forecast, Please select at least two surveys with different tags
	 * Results: - 
	 *	- - User friendly error messages are displayed:Selected Percent Coverage Forecast, Please select at least two surveys with different tags
	 *	- - User friendly error messages are displayed:
	 *	- Selected Percent Coverage Forecast, Please select Customer Boundary
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
	@Test
	public void TC1318_GenerateMultipleComplianceReportsDifferentAssetLayerIncludePercentCoverageForecast() throws Exception {
		Log.info("\nRunning TC1318_GenerateMultipleComplianceReportsDifferentAssetLayerIncludePercentCoverageForecast ...");
		
		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);   /* Picarro Admin */
		loginPageAction.login(EMPTY, 1);
	}

}
