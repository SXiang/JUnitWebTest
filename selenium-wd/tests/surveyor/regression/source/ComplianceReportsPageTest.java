/**
 *
 */
package surveyor.regression.source;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
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
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEET;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPHEIGHT;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPWIDTH;
import static surveyor.scommon.source.SurveyorConstants.RNELAT;
import static surveyor.scommon.source.SurveyorConstants.RNELON;
import static surveyor.scommon.source.SurveyorConstants.RSWLAT;
import static surveyor.scommon.source.SurveyorConstants.RSWLON;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEMT;
import static surveyor.scommon.source.SurveyorConstants.PICADMNMANTAG;
import static surveyor.scommon.source.SurveyorConstants.PICADMNRRTAG;
import static surveyor.scommon.source.SurveyorConstants.CUSDRVSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.KEYHIGHLIGHTLISAASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYHIGHLIGHTGAPASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETBOXNUMBER;
import static surveyor.scommon.source.SurveyorConstants.NOMATCHINGSEARCH;
import static surveyor.scommon.source.ReportsCompliance.EthaneFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.CryptoUtility;
import common.source.Log;
import surveyor.dataaccess.source.DBCache;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.dataprovider.ReportDataProvider;
import surveyor.scommon.source.BaseReportsPageTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.ComplianceReportsPage.ComplianceReportButtonType;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SearchAreaPreference;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.SurveyorTestRunner;

/**
 *
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest extends BaseReportsPageTest {

	protected static final Integer DATAPROVIDER_REPORT_GENERATION_TIMEOUT_IN_SECONDS = 2400;  // Max timeout= 40 mins for report gen.

	private String STRReportAreaTooLargeMsg = "Please make sure your selected boundary is more than 0.5kms and less than 25kms";
	private String STRReportAssetNotSelectedMsg = "View(s) with Assets, Please select at least one Asset Layer";
	private String STRReportBoundaryNotSelectedMsg = "View(s) with Boundaries, Please select at least one Boundary Layer";
	private static Map<String, String> testCaseMap = Collections.synchronizedMap(new HashMap<String, String>());

	private static LoginPage loginPage;

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects();

		createTestCaseMap();
	}

	@Before
	public void beforeTest() {
		initializeTestObjects();

		initializePageObjects();

		PageObjectFactory pageObjectFactory = new PageObjectFactory();
		loginPage = pageObjectFactory.getLoginPage();
		PageFactory.initElements(getDriver(), loginPage);
	}

	private static void initializePageObjects() {
		initializePageObjects(new ComplianceReportsPage(getDriver(), getBaseURL(), getTestSetup()));
	}

	private ComplianceReportsPage getComplianceReportsPage() {
		return (ComplianceReportsPage)getReportsPage();
	}

	/**
	 * Test Case ID: TC517 Test Description: Generate compliance report with all default values/filters selected and download it
	 *
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws Exception
	 *
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PROVIDER, location = ComplianceReportDataProvider.class)
	public void ComplianceReportTest_VerifyNonEthaneReport(String index, String strCreatedBy, String password, String cutomer, String timeZone, String exclusionRadius, String surveyorUnit, String userName, String startDate, String endDate, String fovOpacity, String lisaOpacity, Boolean geoFilter, ReportModeFilter reportMode, SurveyModeFilter surveyModeFilter, EthaneFilter ethaneFilter, List<String> listBoundary, List<String> tagList, List<Map<String, String>> tablesList,
			List<Map<String, String>> viewList, List<Map<String, String>> viewLayersList, SearchAreaPreference srchAreaPref) throws Exception {
		String rptTitle = null;
		String testCaseName = getTestCaseName(index);

		if (testCaseName.equals("TC203")) {
			rptTitle = testCaseName + " " + "Report" + getTestSetup().getRandomNumber() + "#%$,\"<>";
		} else {
			rptTitle = testCaseName + " " + "Report" + getTestSetup().getRandomNumber();
		}

		Log.info("\nRunning " + testCaseName + " - " + rptTitle);

		this.getComplianceReportsPage().login(strCreatedBy, new CryptoUtility().decrypt(password));
		this.getComplianceReportsPage().open();

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, strCreatedBy, cutomer, timeZone, exclusionRadius, surveyorUnit, userName, startDate, endDate, fovOpacity, lisaOpacity, geoFilter, reportMode, surveyModeFilter, ethaneFilter, listBoundary, tagList, tablesList, viewList, viewLayersList);
		rpt.setSearchAreaPreference(srchAreaPref);
		this.getComplianceReportsPage().setReportGenerationTimeout(DATAPROVIDER_REPORT_GENERATION_TIMEOUT_IN_SECONDS);
		this.getComplianceReportsPage().addNewReport(rpt);
		this.getComplianceReportsPage().waitForPageLoad();

		if ((this.getComplianceReportsPage().checkActionStatus(rptTitle, strCreatedBy, testCaseName))) {
			this.getComplianceReportsPage().clickOnReportViewerCloseButton();
			assertTrue(this.getComplianceReportsPage().validatePdfFiles(rpt, getTestSetup().getDownloadPath()));
			assertTrue(this.getComplianceReportsPage().verifyComplianceReportStaticText(rpt));
			assertTrue(this.getComplianceReportsPage().verifySSRSImages(getTestSetup().getDownloadPath(), rptTitle, testCaseName));
			if (tablesList != null) {
				if ((tablesList.get(0).get(KEYPCA).equals("1")) || (tablesList.get(0).get(KEYPCRA).equals("1"))) {
					assertTrue(this.getComplianceReportsPage().verifyShowCoverageTable(getTestSetup().getDownloadPath(), rptTitle));
					assertTrue(this.getComplianceReportsPage().verifyCoverageValuesTable(getTestSetup().getDownloadPath(), rptTitle, tablesList.get(0)));
				}
				if (cutomer.equalsIgnoreCase("Picarro")) {
					assertTrue(this.getComplianceReportsPage().verifyLayersTable(getTestSetup().getDownloadPath(), rptTitle, tablesList.get(0)));
				}
				assertTrue(this.getComplianceReportsPage().verifyViewsTable(getTestSetup().getDownloadPath(), rptTitle, viewList));
				assertTrue(this.getComplianceReportsPage().verifyDrivingSurveysTable(getTestSetup().getDownloadPath(), rptTitle));
				assertTrue(this.getComplianceReportsPage().verifyAllViewsImages(getTestSetup().getDownloadPath(), rptTitle, testCaseName,viewList.size()));
				if (tablesList.get(0).get(KEYISOANA).equals("1")) {
					assertTrue(this.getComplianceReportsPage().verifyIsotopicAnalysisTable(getTestSetup().getDownloadPath(), rptTitle));
				}
				if (tablesList.get(0).get(KEYINDTB).equals("1")) {
					assertTrue(this.getComplianceReportsPage().verifyIndicationTable(getTestSetup().getDownloadPath(), rptTitle));
				}
			}
		} else
			fail("\nTestcase " + getTestCaseName(index) + " failed.\n");
	}

	private static String getTestCaseName(String key) {
		return testCaseMap.get(key);
	}

	private static void createTestCaseMap() {
		testCaseMap.put("1", "TC517");
		testCaseMap.put("2", "TC148");
		testCaseMap.put("3", "TC150");
		testCaseMap.put("4", "TC151");
		testCaseMap.put("5", "TC152");
		testCaseMap.put("6", "TC154");
		testCaseMap.put("7", "TC155");
		testCaseMap.put("8", "TC156");
		testCaseMap.put("9", "TC169");
		testCaseMap.put("10", "TC173");
		testCaseMap.put("11", "TC175");
		testCaseMap.put("12", "TC176");
		testCaseMap.put("13", "TC177");
		testCaseMap.put("14", "TC146");
		testCaseMap.put("15", "TC147");
		testCaseMap.put("16", "TC202");
		testCaseMap.put("17", "TC249");
		testCaseMap.put("18", "TC203");
		testCaseMap.put("19", "TC250");
		testCaseMap.put("20", "TC725");
		testCaseMap.put("21", "TC739");
		testCaseMap.put("22", "TC926");
		testCaseMap.put("23", "TC1321");
	}

	/**
	 * Test Case ID: TC157 Test Description: Check that report cannot be generated unless all filters are selected
	 * On Home Page, click Reports -> Compliance -> 'New Compliance Report' button
	 * Scripts: -
	 *	- Don't provide report title, lat long co-ordinates
	 *	- Don't include any survey
	 *	- Include Views but dont select any option to display in view
	 *	- Provide same view names
	 * Results: -
	 *  - Required fields should be highlighted in red color
	 */
	@Test
	public void TC157_ComplianceReportTest_VerifyReportCannotbeGeneratedUnlessAllFiltersarePresent() {
		Log.info("\nRunning TC157: Check that report cannot be generated unless all filters are selected");

		this.getComplianceReportsPage().login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		this.getComplianceReportsPage().open();
		assertTrue(this.getComplianceReportsPage().checkBlankReportErrorTextPresentAndRequiredFieldsHighlighted());
	}

	/**
	 * Test Case ID: TC160 Test Description: Pagination - 10,25,50 and 100 Reports selection on compliance report screen
	 *
	 */
	@Test
	public void TC160_ComplianceReportTest_VerifyPagination() {
		Log.info("\nRunning RPT015: Pagination - 10,25,50 and 100 Reports selection on compliance report screen");

		this.getComplianceReportsPage().login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		this.getComplianceReportsPage().open();
		String paginationSetting25 = "25";
		String paginationSetting50 = "50";
		String paginationSetting100 = "100";

		assertTrue(this.getComplianceReportsPage().checkPaginationSetting(PAGINATIONSETTING));
		assertTrue(!(this.getComplianceReportsPage().getNumberofRecords() > Integer.parseInt(PAGINATIONSETTING)));
		assertTrue(this.getComplianceReportsPage().checkPaginationSetting(paginationSetting25));
		assertTrue(!(this.getComplianceReportsPage().getNumberofRecords() > Integer.parseInt(paginationSetting25)));
		assertTrue(this.getComplianceReportsPage().checkPaginationSetting(paginationSetting50));
		assertTrue(!(this.getComplianceReportsPage().getNumberofRecords() > Integer.parseInt(paginationSetting50)));
		assertTrue(this.getComplianceReportsPage().checkPaginationSetting(paginationSetting100));
		assertTrue(!(this.getComplianceReportsPage().getNumberofRecords() > Integer.parseInt(paginationSetting100)));
	}

	/**
	 * Test Case ID: TC163 Test Description: Screen should not refresh while searching an in-progress report, as it completes
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void TC163_ComplianceReportTest_VerifyScreendoesntRefreshwhileSearchingInprogressReport() throws Exception {
		String rptTitle = "TC163 Report" + getTestSetup().getRandomNumber();

		Log.info("Running TC163: Screen should not refresh while searching an in-progress report, as it completes " + rptTitle);

		this.getComplianceReportsPage().login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		this.getComplianceReportsPage().open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add(RNELAT);
		listBoundary.add(RNELON);
		listBoundary.add(RSWLAT);
		listBoundary.add(RSWLON);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "1");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYHIGHLIGHTLISAASSETS, "1");
		viewMap1.put(KEYHIGHLIGHTGAPASSETS, "1");
		viewMap1.put(KEYASSETBOXNUMBER, "0");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createViewLayerAssetsContent(assetRowIDs));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, getTestSetup().getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);
		rpt.setSearchAreaPreference(SearchAreaPreference.LISAS);

		this.getComplianceReportsPage().addNewReport(rpt);
		this.getComplianceReportsPage().waitForPageLoad();

		assertTrue(this.getComplianceReportsPage().waitForReportGenerationtoComplete(rptTitle, getTestSetup().getLoginUser()));
		assertTrue(this.getComplianceReportsPage().searchReport(rptTitle, PICDFADMIN));
	}

	/**
	 * Test Case ID: TC164 Test Description: Search invalid reports
	 * Script: -
	 * 	-Provide any invalid report title on compliance or investigation or reference gas or system history report screen
	 * Result: -
	 *  Message should be displayed : 'No matching records found'
	 * 	-
	 */
	@Test
	public void TC164_ComplianceReportTest_VerifySearchInvalidReports() {
		String rptTitle = "TC164 Report Not Exists";
		Log.info("Running TC164: Search invalid reports " + rptTitle);

		this.getComplianceReportsPage().login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		this.getComplianceReportsPage().open();

		assertTrue(!this.getComplianceReportsPage().searchReport(rptTitle, getTestSetup().getLoginUser()));
		assertEquals(NOMATCHINGSEARCH, this.getComplianceReportsPage().getEmptyTableMessage());
	}

	/**
	 * Test Case ID: TC166 Test Description: Picarro Administrator can delete the specified report
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void TC166_ComplianceReportTest_AdminCanDeleteReport() throws Exception {
		String rptTitle = "TC166 Report" + getTestSetup().getRandomNumber();
		Log.info("Running TC166: User can delete the specified report " + rptTitle);

		this.getComplianceReportsPage().login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		this.getComplianceReportsPage().open();

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
		tableMap.put(KEYPCA, "1");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createViewLayerAssetsContent(assetRowIDs));

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "1");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYHIGHLIGHTLISAASSETS, "1");
		viewMap1.put(KEYHIGHLIGHTGAPASSETS, "1");
		viewMap1.put(KEYASSETBOXNUMBER, "0");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);
		ReportsCompliance rpt = new ReportsCompliance(rptTitle, getTestSetup().getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);
		rpt.setSearchAreaPreference(SearchAreaPreference.LISAS);

		this.getComplianceReportsPage().addNewReport(rpt);
		this.getComplianceReportsPage().waitForPageLoad();

		assertTrue(this.getComplianceReportsPage().waitForReportGenerationtoComplete(rptTitle, getTestSetup().getLoginUser()));

		if (this.getComplianceReportsPage().deleteReport(rptTitle, getTestSetup().getLoginUser()))
			assertTrue(!(this.getComplianceReportsPage().findReportbySearch(rptTitle, getTestSetup().getLoginUser())));
		else
			fail("\nTestcase TC166 failed.\n");
	}

	/**
	 * Test Case ID: TC170 Test Description: Duplicate report
	 * @throws Exception
	 *
	 * US3605 - This test currently fails on Assertion since we always get back the oldest report ID from GetReportStat API.
	 * Opened US3605 to track updating GetReportStat API to return the most recent report when there is more than 1 match for report title.
	 */
	@Test
	public void TC170_ComplianceReportTest_VerifyReportDuplicate() throws Exception {
		String testCaseID = "TC170";
		String rptTitle = testCaseID + " Report" + getTestSetup().getRandomNumber();
		Log.info("Running " + testCaseID + ": Duplicate report, " + rptTitle);

		this.getComplianceReportsPage().login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		this.getComplianceReportsPage().open();

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
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createViewLayerAssetsContent(assetRowIDs));

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
		viewMap1.put(KEYHIGHLIGHTLISAASSETS, "1");
		viewMap1.put(KEYHIGHLIGHTGAPASSETS, "0");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, getTestSetup().getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);

		this.getComplianceReportsPage().addNewReport(rpt);
		this.getComplianceReportsPage().waitForPageLoad();

		String reportName1 = this.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportName(rptTitle, getTestSetup().getLoginUser());
		assertNotNull(reportName1);

		this.getComplianceReportsPage().addNewReport(rpt);
		this.getComplianceReportsPage().waitForPageLoad();

		DBCache.INSTANCE.remove(Report.CACHE_KEY+rptTitle);
		String reportName2 = this.getComplianceReportsPage().waitForReportGenerationtoCompleteAndGetReportName(rptTitle, getTestSetup().getLoginUser());
		assertNotNull(reportName2);

		assertNotEquals(reportName1, reportName2);
	}

	/**
	 * Test Case ID: TC174 Test Description: Generate report for same surveys but in different modes
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void TC174_ComplianceReportTest_VerifySameReportDifferentModes() throws Exception {
		String testCaseID = "TC174";
		String rptTitle = testCaseID + " RR Report" + getTestSetup().getRandomNumber();
		Log.info("Running " + testCaseID + ": Generate report for same surveys but in different modes " + rptTitle);

		ReportModeFilter[] reportModes = {ReportModeFilter.Standard, ReportModeFilter.RapidResponse};
		this.getComplianceReportsPage().login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		this.getComplianceReportsPage().open();

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
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createViewLayerAssetsContent(assetRowIDs));

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap = new HashMap<String, String>();

		viewMap.put(KEYVIEWNAME, "First View");
		viewMap.put(KEYLISA, "1");
		viewMap.put(KEYFOV, "1");
		viewMap.put(KEYBREADCRUMB, "1");
		viewMap.put(KEYINDICATIONS, "1");
		viewMap.put(KEYISOTOPICCAPTURE, "1");
		viewMap.put(KEYANNOTATION, "1");
		viewMap.put(KEYGAPS, "1");
		viewMap.put(KEYASSETS, "1");
		viewMap.put(KEYBOUNDARIES, "0");
		viewMap.put(KEYHIGHLIGHTLISAASSETS, "1");
		viewMap.put(KEYHIGHLIGHTGAPASSETS, "1");
		viewMap.put(KEYASSETBOXNUMBER, "0");
		viewMap.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap);

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		for(ReportModeFilter reportMode : reportModes){
			rptTitle = testCaseID + " "+reportMode.toString() + " " + getTestSetup().getRandomNumber();
			ReportsCompliance rpt = new ReportsCompliance(rptTitle, getTestSetup().getLoginUser(), "Picarro", TIMEZONEMT, "0",
					listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard, reportMode);
			rpt.setSearchAreaPreference(SearchAreaPreference.LISAS);
			rpt.setViewLayersList(viewLayerList);
			this.getComplianceReportsPage().addNewReport(rpt);
			this.getComplianceReportsPage().waitForPageLoad();

			if (!this.getComplianceReportsPage().checkActionStatus(rptTitle, getTestSetup().getLoginUser(), testCaseID)){
				fail("\n report "+rptTitle+" creation failed.\n");
			}
		}
	}

	/**
	 * Test Case ID: TC181 Test Description: Generate standard or rapid response report from existing reports having survey of Manual type using copy feature
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void TC181_ComplianceReportTest_VerifyCopyManualReportAsRapidResponse() throws Exception {
		String testCaseID = "TC181";
		String rptTitle = testCaseID + " Report" + getTestSetup().getRandomNumber();
		Log.info("Running " + testCaseID + ": Generate  standard or rapid response report from existing reports having survey of Manual type using copy feature, " + rptTitle);

		this.getComplianceReportsPage().login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		this.getComplianceReportsPage().open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add(RNELAT);
		listBoundary.add(RNELON);
		listBoundary.add(RSWLAT);
		listBoundary.add(RSWLON);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "1");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYHIGHLIGHTLISAASSETS, "1");
		viewMap1.put(KEYHIGHLIGHTGAPASSETS, "1");
		viewMap1.put(KEYASSETBOXNUMBER, "0");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createViewLayerAssetsContent(assetRowIDs));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNMANTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, getTestSetup().getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Manual, ReportModeFilter.Manual);
		rpt.setViewLayersList(viewLayerList);
		rpt.setSearchAreaPreference(SearchAreaPreference.LISAS);

		this.getComplianceReportsPage().addNewReport(rpt);
		this.getComplianceReportsPage().waitForPageLoad();

		assertTrue(this.getComplianceReportsPage().waitForReportGenerationtoComplete(rptTitle, getTestSetup().getLoginUser()));

		String newRptTitle = rptTitle + "COPY";
		List<String> surTag = new ArrayList<String>();
		surTag.add(PICADMNRRTAG);
		this.getComplianceReportsPage().copyReport(rptTitle, PICDFADMIN);
		initializePageObjects();
		this.getComplianceReportsPage().modifyReportDetails(newRptTitle, "", surTag, true, ReportModeFilter.RapidResponse);
		this.getComplianceReportsPage().waitForPageLoad();

		assertTrue(this.getComplianceReportsPage().waitForReportGenerationtoComplete(newRptTitle, getTestSetup().getLoginUser()));
	}

	/**
	 * Test Case ID: TC184 Test Description: Very small or big report area selection not allowed
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void TC184_ComplianceReportTest_VerifyAreaErrorMessage() throws Exception {
		Log.info("\nRunning TC184_: Very small or big report area selection not allowed\n");
		String rptTitle = "TC184_Report" +" SmallArea "+ getTestSetup().getRandomNumber();
		this.getComplianceReportsPage().login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		this.getComplianceReportsPage().open();

		//Small Area
		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add("36.42252593456309");
		listBoundary.add("-122.83494567871095");
		listBoundary.add("36.42252593456300");
		listBoundary.add("-122.83494567871090");

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "1");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYHIGHLIGHTLISAASSETS, "1");
		viewMap1.put(KEYHIGHLIGHTGAPASSETS, "1");
		viewMap1.put(KEYASSETBOXNUMBER, "0");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4); // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);
		ReportsCompliance rpt = new ReportsCompliance(rptTitle, getTestSetup().getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard, false);
		rpt.setViewLayersList(viewLayerList);
		rpt.setSearchAreaPreference(SearchAreaPreference.LISAS);

		this.getComplianceReportsPage().addNewReport(rpt);

		Assert.assertEquals(this.getComplianceReportsPage().getAreaErrorText(), STRReportAreaTooLargeMsg);

		// Big Area
		rptTitle = "TC184_Report" +" BigArea "+ getTestSetup().getRandomNumber();
		this.getComplianceReportsPage().open();
		listBoundary.clear();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add("36.42252593456309");
		listBoundary.add("-122.83494567871095");
		listBoundary.add("38.27989023941680");
		listBoundary.add("-124.05415725708008");
		rpt = new ReportsCompliance(rptTitle, getTestSetup().getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard, false);
		this.getComplianceReportsPage().addNewReport(rpt);
		Assert.assertEquals(this.getComplianceReportsPage().getAreaErrorText(), STRReportAreaTooLargeMsg);

	}

	/**
	 * Test Case ID: TC185 Test Description: Click on Cancel button present on compliance report screen
	 *
	 */
	@Test
	public void TC185_ComplianceReportTest_VerifyCancelButtonFunctionality() {
		Log.info("\nRunning TC185: Click on Cancel button present on compliance report screen\n");

		this.getComplianceReportsPage().login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		this.getComplianceReportsPage().open();

		assertTrue(this.getComplianceReportsPage().verifyCancelButtonFunctionality());
	}

	/**
	 * Test Case ID: TC197 Test Description: Verify "Add Survey" message is displayed when no Survey added
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void TC197_ComplianceReportTest_VerifyAddSurveyErrorMessages() throws Exception {
		Log.info("\nRunning TC197: Verify 'Add Survey' message is displayed when no Survey added\n");
		String rptTitle = "TC197 Report" + getTestSetup().getRandomNumber();
		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();
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
		viewMap1.put(KEYHIGHLIGHTLISAASSETS, "0");
		viewMap1.put(KEYHIGHLIGHTGAPASSETS, "0");
		viewMap1.put(KEYASSETBOXNUMBER, "0");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));
		viewList.add(viewMap1);

		this.getComplianceReportsPage().login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		this.getComplianceReportsPage().open();
		assertTrue(this.getComplianceReportsPage().verifySurveyNotAdded(rptTitle, "Picarro", RNELAT, RNELON, RSWLAT, RSWLON, viewList));
	}

	/**
	 * Test Case ID: TC167 Test Description: Customer Admin can delete the specified report
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void TC167_ComplianceReportTest_CusAdminCanDeleteReport() throws Exception {
		String rptTitle = "TC167 Report" + getTestSetup().getRandomNumber();
		Log.info("Running TC167: User can delete the specified report, " + rptTitle);

		loginPage.open();
		loginPage.loginNormalAs(SQACUSUA, USERPASSWORD);
		this.getComplianceReportsPage().open();

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
		tableMap.put(KEYISOANA, "0");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap = new HashMap<String, String>();

		viewMap.put(KEYVIEWNAME, "First View");
		viewMap.put(KEYLISA, "1");
		viewMap.put(KEYFOV, "1");
		viewMap.put(KEYBREADCRUMB, "0");
		viewMap.put(KEYINDICATIONS, "0");
		viewMap.put(KEYISOTOPICCAPTURE, "0");
		viewMap.put(KEYANNOTATION, "0");
		viewMap.put(KEYGAPS, "0");
		viewMap.put(KEYASSETS, "0");
		viewMap.put(KEYBOUNDARIES, "0");
		viewMap.put(KEYHIGHLIGHTLISAASSETS, "0");
		viewMap.put(KEYHIGHLIGHTGAPASSETS, "0");
		viewMap.put(KEYASSETBOXNUMBER, "0");
		viewMap.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));

		viewList.add(viewMap);

		List<String> tagList = new ArrayList<String>();
		tagList.add(CUSDRVSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, SQACUSUA, "sqacus", TIMEZONEET, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		rpt.setSearchAreaPreference(SearchAreaPreference.LISAS);

		this.getComplianceReportsPage().addNewReport(rpt);
		this.getComplianceReportsPage().waitForPageLoad();

		assertTrue(this.getComplianceReportsPage().waitForReportGenerationtoComplete(rptTitle, SQACUSUA));

		if (this.getComplianceReportsPage().deleteReport(rptTitle, SQACUSUA))
			assertTrue(!(this.getComplianceReportsPage().findReportbySearch(rptTitle, SQACUSUA)));
		else
			fail("\nTestcase TC167 failed.\n");
	}

	/**
	 * Test Case ID: TC168 Test Description: Customer Supervisor can delete the specified report
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void TC168_ComplianceReportTest_CusSupervisorCanDeleteReport() throws Exception {
		String rptTitle = "TC168 Report" + getTestSetup().getRandomNumber();
		Log.info("Running TC168: User can delete the specified report, " + rptTitle);

		loginPage.open();
		loginPage.loginNormalAs(SQACUSSU, USERPASSWORD);
		this.getComplianceReportsPage().open();

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
		tableMap.put(KEYISOANA, "0");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap = new HashMap<String, String>();

		viewMap.put(KEYVIEWNAME, "First View");
		viewMap.put(KEYLISA, "1");
		viewMap.put(KEYFOV, "1");
		viewMap.put(KEYBREADCRUMB, "0");
		viewMap.put(KEYINDICATIONS, "0");
		viewMap.put(KEYISOTOPICCAPTURE, "0");
		viewMap.put(KEYANNOTATION, "0");
		viewMap.put(KEYGAPS, "0");
		viewMap.put(KEYASSETS, "0");
		viewMap.put(KEYBOUNDARIES, "0");
		viewMap.put(KEYHIGHLIGHTLISAASSETS, "0");
		viewMap.put(KEYHIGHLIGHTGAPASSETS, "0");
		viewMap.put(KEYASSETBOXNUMBER, "0");
		viewMap.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));

		viewList.add(viewMap);

		List<String> tagList = new ArrayList<String>();
		tagList.add(CUSDRVSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, SQACUSSU, "sqacus", TIMEZONEET, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		rpt.setSearchAreaPreference(SearchAreaPreference.LISAS);

		this.getComplianceReportsPage().addNewReport(rpt);
		this.getComplianceReportsPage().waitForPageLoad();

		assertTrue(this.getComplianceReportsPage().waitForReportGenerationtoComplete(rptTitle, SQACUSSU));

		if (this.getComplianceReportsPage().deleteReport(rptTitle, SQACUSSU))
			assertTrue(!(this.getComplianceReportsPage().findReportbySearch(rptTitle, SQACUSSU)));
		else
			fail("\nTestcase TC168 failed.\n");
	}

	/**
	 * Test Case ID: TC212 Test Description: Resubmit compliance report from previously generated reports
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void TC212_ComplianceReportTest_VerifyResubmitReport() throws Exception {
		String testCaseID = "TC212";
		String rptTitle = testCaseID + " Report" + getTestSetup().getRandomNumber();
		Log.info("\nRunning " + testCaseID + ": Resubmit compliance report from previously generated reports, " + rptTitle);

		this.getComplianceReportsPage().login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		this.getComplianceReportsPage().open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add(RNELAT);
		listBoundary.add(RNELON);
		listBoundary.add(RSWLAT);
		listBoundary.add(RSWLON);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "1");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYHIGHLIGHTLISAASSETS, "1");
		viewMap1.put(KEYHIGHLIGHTGAPASSETS, "1");
		viewMap1.put(KEYASSETBOXNUMBER, "0");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13); // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createViewLayerAssetsContent(assetRowIDs));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNMANTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, getTestSetup().getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Manual, ReportModeFilter.Manual);
		rpt.setViewLayersList(viewLayerList);
		rpt.setSearchAreaPreference(SearchAreaPreference.LISAS);

		this.getComplianceReportsPage().addNewReport(rpt);
		this.getComplianceReportsPage().waitForPageLoad();

		assertTrue(this.getComplianceReportsPage().waitForReportGenerationtoComplete(rptTitle, getTestSetup().getLoginUser()));

		this.getComplianceReportsPage().clickComplianceReportButton(rptTitle, getTestSetup().getLoginUser(), ComplianceReportButtonType.Resubmit);

		this.getComplianceReportsPage().waitForPageLoad();

		if ((this.getComplianceReportsPage().checkActionStatus(rptTitle, getTestSetup().getLoginUser(), testCaseID))) {
			assertTrue(this.getComplianceReportsPage().validatePdfFiles(rpt, getTestSetup().getDownloadPath()));
		} else
			fail("\nTestcase TC212 failed.\n");
	}

	/**
	 * Test Case ID: TC797 Test Description: Search compliance reports based on report name
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void TC797_ComplianceReportTest_SearchReportByReportName() throws Exception {
		String rptTitle = "TC797 Report" + getTestSetup().getRandomNumber();
		Log.info("Running TC797: Search compliance reports based on report name, " + rptTitle);

		loginPage.open();
		loginPage.loginNormalAs(SQACUSSU, USERPASSWORD);
		this.getComplianceReportsPage().open();

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
		tableMap.put(KEYISOANA, "0");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap = new HashMap<String, String>();

		viewMap.put(KEYVIEWNAME, "First View");
		viewMap.put(KEYLISA, "1");
		viewMap.put(KEYFOV, "1");
		viewMap.put(KEYBREADCRUMB, "0");
		viewMap.put(KEYINDICATIONS, "0");
		viewMap.put(KEYISOTOPICCAPTURE, "0");
		viewMap.put(KEYANNOTATION, "0");
		viewMap.put(KEYGAPS, "0");
		viewMap.put(KEYASSETS, "0");
		viewMap.put(KEYBOUNDARIES, "0");
		viewMap.put(KEYHIGHLIGHTLISAASSETS, "0");
		viewMap.put(KEYHIGHLIGHTGAPASSETS, "0");
		viewMap.put(KEYASSETBOXNUMBER, "0");

		viewMap.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));

		viewList.add(viewMap);

		List<String> tagList = new ArrayList<String>();
		tagList.add(CUSDRVSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, SQACUSSU, "sqacus", TIMEZONEET, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		rpt.setSearchAreaPreference(SearchAreaPreference.LISAS);

		this.getComplianceReportsPage().addNewReport(rpt);
		this.getComplianceReportsPage().waitForPageLoad();

		if (this.getComplianceReportsPage().waitForReportGenerationtoComplete(rptTitle, SQACUSSU))
			assertTrue((this.getComplianceReportsPage().findReportbySearch(rptTitle, SQACUSSU)));
		else
			fail("\nTestcase TC797 failed.\n");
	}

	/**
	 * Test Case ID: TC1275 Test Description: User friendly message should be displayed if user has include assets and boundaries in views but not selected any asset and boundaries layers in optional
	 * view layers section
	 *
	 * @throws Exception
	 *
	 */
	@Test
	public void TC1275_ComplianceReportTest_VerifyAreaErrorMessage() throws Exception {
		Log.info("\nRunning TC1275_: User friendly message should be displayed if user has include assets and boundaries in views but not selected any asset and boundaries layers in optional view layers section\n");
		String rptTitle = "TC1275_Report" + getTestSetup().getRandomNumber();
		this.getComplianceReportsPage().login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		this.getComplianceReportsPage().open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add(RNELAT);
		listBoundary.add(RNELON);
		listBoundary.add(RSWLAT);
		listBoundary.add(RSWLON);

		List<Map<String, String>> viewList = new ArrayList<Map<String, String>>();
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "1");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "1");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "1");
		viewMap1.put(KEYHIGHLIGHTLISAASSETS, "1");
		viewMap1.put(KEYHIGHLIGHTGAPASSETS, "1");
		viewMap1.put(KEYASSETBOXNUMBER, "0");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tablesList.add(tableMap);

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, getTestSetup().getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard, ReportModeFilter.Standard);
		rpt.setSearchAreaPreference(SearchAreaPreference.LISAS);

		this.getComplianceReportsPage().addNewReport(rpt);
		Assert.assertEquals(this.getComplianceReportsPage().getAssetErrorText().getText(), STRReportAssetNotSelectedMsg);
		Assert.assertEquals(this.getComplianceReportsPage().getBoundaryErrorText().getText(), STRReportBoundaryNotSelectedMsg);
	}

	/**
	 * Test Case ID: TC1297 Test Description:
	 * Software version present at bottom of the page should be same as team city version
	 * @throws Exception
	 *
	 */
	@Test
	public void TC1297_ComplianceReportTest_VerifyWebAppAndCIVersion() throws Exception {
		Log.info("Running TC1297-1: Software version present at bottom of the page should be same as team city version");

		loginPage.open();
		loginPage.loginNormalAs(SQACUSSU, USERPASSWORD);
		this.getComplianceReportsPage().open();

		String webAppVersionNumber = this.getComplianceReportsPage().getWebAppVersion();

		// 1. Verify web app version matches the CI build version.
		String ciVersionNumber = getTestSetup().getCIEnvironmentBuildNumber();
		Log.info(String.format("Verifying web app version matches CI version. WebAppVersion='%s', CIVersion='%s'",
			webAppVersionNumber, ciVersionNumber));
		assertTrue(webAppVersionNumber.equals(ciVersionNumber));
	}

	/**
	 * Test Case ID: TC1297 Test Description:
	 * Software version present on report PDF should match with UI software version
	 * @throws Exception
	 *
	 */
	@Test
	public void TC1297_ComplianceReportTest_VerifyWebAppAndPDFVersion() throws Exception {
		String testCaseID = "TC1297";
		String rptTitle = testCaseID + " Report" + getTestSetup().getRandomNumber();
		Log.info("Running " + testCaseID + ": Software version on UI and reports PDF should match, " + rptTitle);

		loginPage.open();
		loginPage.loginNormalAs(SQACUSSU, USERPASSWORD);
		this.getComplianceReportsPage().open();

		String webAppVersionNumber = this.getComplianceReportsPage().getWebAppVersion();

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
		tableMap.put(KEYISOANA, "0");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
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
		viewMap.put(KEYGAPS, "1");
		viewMap.put(KEYASSETS, "0");
		viewMap.put(KEYBOUNDARIES, "0");
		viewMap.put(KEYHIGHLIGHTLISAASSETS, "0");
		viewMap.put(KEYHIGHLIGHTGAPASSETS, "0");
		viewMap.put(KEYASSETBOXNUMBER, "0");
		viewMap.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));

		viewList.add(viewMap);

		List<String> tagList = new ArrayList<String>();
		tagList.add(CUSDRVSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, SQACUSSU, "sqacus", TIMEZONEPT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		rpt.setSearchAreaPreference(SearchAreaPreference.LISAS);

		this.getComplianceReportsPage().addNewReport(rpt);
		this.getComplianceReportsPage().waitForPageLoad();

		if ((this.getComplianceReportsPage().checkActionStatus(rptTitle, SQACUSSU, testCaseID))) {
			// 1. Verify PDF footer version number and web app version numbers match.
			String downloadPath = getTestSetup().getDownloadPath();
			assertTrue(this.getComplianceReportsPage().validatePdfFiles(rpt, downloadPath));
			assertTrue(this.getComplianceReportsPage().verifySSRSPDFFooter(downloadPath, rptTitle, webAppVersionNumber , SQACUSSU));

			// 2. Verify version number in Investigation PDF.
			String reportName = this.getComplianceReportsPage().getReportPDFFileName(rptTitle, false /*includeExtension*/);
			this.getComplianceReportsPage().invokeInvestigationPDFFileDownload(rptTitle);
			this.getComplianceReportsPage().waitForInvestigationPDFFileDownload(reportName);

			String pdfSoftwareVersion = this.getComplianceReportsPage().getSoftwareVersionFromInvestigationPDF(rptTitle, downloadPath);
			Log.info(String.format("Comparing web app version and PDF software version. WebAppVersion = '%s', PDFSoftwareVersion = '%s'",
					webAppVersionNumber, pdfSoftwareVersion));
			assertTrue(webAppVersionNumber.equals(pdfSoftwareVersion));

		} else
			fail("\nTestcase TC1297 failed.\n");
	}
}