/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
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
import static surveyor.scommon.source.SurveyorConstants.PAGINATIONSETTING;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.SQACUS;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC1SUR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSLOC2SUR;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSUUSER;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSUTAG;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUA;
import static surveyor.scommon.source.SurveyorConstants.SQACUSUATAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICADMANUALTAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICADRRTAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICADSTNDTAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICADTAG;
import static surveyor.scommon.source.SurveyorConstants.SQACUSDRTAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICSUP;

import static surveyor.scommon.source.SurveyorConstants.SQAPICDRTAG;
import static surveyor.scommon.source.SurveyorConstants.SQAPICLOC3SUR;
import static surveyor.scommon.source.SurveyorConstants.SQAPICSUTAG;

import static surveyor.scommon.source.SurveyorConstants.SQACRPTTAG;
import static surveyor.scommon.source.SurveyorConstants.SURVEYORUNIT;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONECT;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEET;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPHEIGHT;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPWIDTH;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;
import static surveyor.scommon.source.SurveyorConstants.EXCLUSIONRADIUS;
import static surveyor.scommon.source.SurveyorConstants.RNELAT;
import static surveyor.scommon.source.SurveyorConstants.RNELON;
import static surveyor.scommon.source.SurveyorConstants.RSWLAT;
import static surveyor.scommon.source.SurveyorConstants.RSWLON;
import static surveyor.scommon.source.SurveyorConstants.REPORTMODES;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.RSURSTARTDATE;
import static surveyor.scommon.source.SurveyorConstants.RSURENDDATE;
import static surveyor.scommon.source.SurveyorConstants.RSUVMODESTD;
import static surveyor.scommon.source.SurveyorConstants.RSUVMODEOP;
import static surveyor.scommon.source.SurveyorConstants.RSUVMODERR;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSURVEYOR;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEMT;
import static surveyor.scommon.source.SurveyorConstants.PICADMNMANTAG;
import static surveyor.scommon.source.SurveyorConstants.PICADMNRRTAG;
import static surveyor.scommon.source.SurveyorConstants.CUSDRVSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.PICADMNOPTAG;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETCASTIRON;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETCOPPER;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETOTHERPLASTIC;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETPEPLASTIC;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETPROTECTEDSTEEL;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETUNPROTECTEDSTEEL;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARYDISTRICT;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARYDISTRICTPLAT;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEETUA;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONECTUA;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPTUA;
import static surveyor.scommon.source.Reports.EthaneFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.CryptoUtility;
import common.source.Log;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.ComplianceReportsPage.ComplianceReportButtonType;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.dataprovider.ComplianceReportDataProvider;

/**
 * 
 * 
 */
@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest extends SurveyorBaseTest {
	private static ComplianceReportsPage complianceReportsPage = null;
	private String STRReportAreaTooLargeMsg = "Please make sure your selected boundary is more than 0.5kms and less than 25kms";
	private String STRReportAssetNotSelectedMsg = "View(s) with Assets, Please select at least one Asset Layer";
	private String STRReportBoundaryNotSelectedMsg = "View(s) with Boundaries, Please select at least one Boundary Layer";
	private static HashMap<String, String> testCaseMap = new HashMap<String, String>();

	@BeforeClass
	public static void setupComplianceReportsPageTest() {
		complianceReportsPage = new ComplianceReportsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, complianceReportsPage);
		createTestCaseMap();

	}

	/**
	 * Test Case ID: TC517 Test Description: Generate compliance report with all default values/filters selected and download it
	 * 
	 * @throws IOException
	 * @throws InterruptedException 
	 * 
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PROVIDER, location = ComplianceReportDataProvider.class)
	public void ComplianceReportTest_VerifyNonEthaneReport(String index, String strCreatedBy, String password, String cutomer, String timeZone, String exclusionRadius, String surveyorUnit, String userName, String startDate, String endDate, String fovOpacity, String lisaOpacity, Boolean geoFilter, ReportModeFilter reportMode, SurveyModeFilter surveyModeFilter, EthaneFilter ethaneFilter, List<String> listBoundary, List<String> tagList, List<Map<String, String>> tablesList,
			List<Map<String, String>> viewList, List<Map<String, String>> viewLayersList) throws IOException, InterruptedException {
		/*String rptTitle = null;
		if (getTestCaseName(index).equals("TC203")) {
			rptTitle = getTestCaseName(index) + " " + "Report" + testSetup.getRandomNumber() + "#<>$";
		} else {
			rptTitle = getTestCaseName(index) + " " + "Report" + testSetup.getRandomNumber();
		}
		Log.info("\nRunning " + getTestCaseName(index) + " - " + rptTitle);

		complianceReportsPage.login(strCreatedBy, CryptoUtility.decrypt(password));
		complianceReportsPage.open();

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, strCreatedBy, cutomer, timeZone, exclusionRadius, surveyorUnit, userName, startDate, endDate, fovOpacity, lisaOpacity, geoFilter, reportMode, surveyModeFilter, ethaneFilter, listBoundary, tagList, tablesList, viewList, viewLayersList);

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, strCreatedBy))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, strCreatedBy));
			assertTrue(complianceReportsPage.verifyComplianceReportStaticText(rptTitle));
			if (tablesList != null) {
				if ((tablesList.get(0).get(KEYPCA).equals("1")) || (tablesList.get(0).get(KEYPCRA).equals("1"))) {
					assertTrue(complianceReportsPage.verifyShowCoverageTable(testSetup.getDownloadPath(), rptTitle));
					assertTrue(complianceReportsPage.verifyCoverageValuesTable(testSetup.getDownloadPath(), rptTitle, tablesList.get(0)));
				}
				if (cutomer.equalsIgnoreCase("Picarro")) {
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
			fail("\nTestcase " + getTestCaseName(index) + " failed.\n");*/
		assertTrue(complianceReportsPage.verifySSRSImages("C:\\Users\\cnanayakkara\\Downloads\\", "TC249 Report634560","TC249"));

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
	 * Test Case ID: TC153 Test Description: Copy and modify report from previously run reports
	 * 
	 */
	@Test
	public void TC153__ComplianceReportTest_VerifyCopyandModifyReportPreviouslyRun() {
		String rptTitle = "TC153 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC153: Copy and modify report from previously run reports, %s\n", rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
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
		viewMap1.put(KEYLISA, "0");
		viewMap1.put(KEYFOV, "0");
		viewMap1.put(KEYBREADCRUMB, "0");
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

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		assertTrue(complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser()));

		List<String> tagListCopy = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);
		complianceReportsPage.copyReportAndModifyDetails(rptTitle, testSetup.getLoginUser(), rptTitle + "COPY", PICADMNSURVEYOR, tagListCopy, false, null);

		if ((complianceReportsPage.checkActionStatus(rptTitle + "COPY", PICDFADMIN)))
			assertTrue(complianceReportsPage.findReport(rptTitle + "COPY", PICDFADMIN));
		else
			fail("\nTestcase TC153 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC157 Test Description: Check that report cannot be generated unless all filters are selected
	 * 
	 */
	@Test
	public void TC157_ComplianceReportTest_VerifyReportCannotbeGeneratedUnlessAllFiltersarePresent() {
		System.out.format("\nRunning TC157: Check that report cannot be generated unless all filters are selected");

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();
		assertTrue(complianceReportsPage.checkBlankReportErrorTextPresent());

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC160 Test Description: Pagination - 10,25,50 and 100 Reports selection on compliance report screen
	 * 
	 */
	@Test
	public void TC160_ComplianceReportTest_VerifyPagination() {
		System.out.format("\nRunning RPT015: Pagination - 10,25,50 and 100 Reports selection on compliance report screen");

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();
		String paginationSetting25 = "25";
		String paginationSetting50 = "50";
		String paginationSetting100 = "100";

		assertTrue(complianceReportsPage.checkPaginationSetting(PAGINATIONSETTING));
		assertTrue(!(complianceReportsPage.getNumberofRecords() > Integer.parseInt(PAGINATIONSETTING)));
		assertTrue(complianceReportsPage.checkPaginationSetting(paginationSetting25));
		assertTrue(!(complianceReportsPage.getNumberofRecords() > Integer.parseInt(paginationSetting25)));
		assertTrue(complianceReportsPage.checkPaginationSetting(paginationSetting50));
		assertTrue(!(complianceReportsPage.getNumberofRecords() > Integer.parseInt(paginationSetting50)));
		assertTrue(complianceReportsPage.checkPaginationSetting(paginationSetting100));
		assertTrue(!(complianceReportsPage.getNumberofRecords() > Integer.parseInt(paginationSetting100)));

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC163 Test Description: Screen should not refresh while searching an in-progress report, as it completes
	 * 
	 */
	@Test
	public void TC163_ComplianceReportTest_VerifyScreendoesntRefreshwhileSearchingInprogressReport() {
		String rptTitle = "TC163 Report" + testSetup.getRandomNumber();

		System.out.format("\nRunning TC163: Screen should not refresh while searching an in-progress report, as it completes, %s\n", rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();

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
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
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

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		assertTrue(complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser()));

		assertTrue(complianceReportsPage.searchReport(rptTitle, PICDFADMIN));

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC164 Test Description: Search invalid reports
	 * 
	 */
	@Test
	public void TC164_ComplianceReportTest_VerifySearchInvalidReports() {
		String rptTitle = "TC164 Report";
		System.out.format("\nRunning TC164: Search invalid reports, %s\n", rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();

		assertTrue(!complianceReportsPage.searchReport(rptTitle, testSetup.getLoginUser()));

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC166 Test Description: Picarro Administrator can delete the specified report
	 * 
	 */
	@Test
	public void TC166_ComplianceReportTest_AdminCanDeleteReport() {
		String rptTitle = "TC166 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC166: User can delete the specified report, %s\n", rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
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
		tableMap.put(KEYPCA, "1");
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
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "1");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);
		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		assertTrue(complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser()));

		if (complianceReportsPage.deleteReport(rptTitle, testSetup.getLoginUser()))
			assertTrue(!(complianceReportsPage.findReportbySearch(rptTitle, testSetup.getLoginUser())));
		else
			fail("\nTestcase TC166 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC170 Test Description: Duplicate report
	 * 
	 */
	@Test
	public void TC170_ComplianceReportTest_VerifyReportDuplicate() {
		String rptTitle = "TC170 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC170: Duplicate report, %s\n", rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
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

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser())))
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
		else
			fail("\nTestcase TC170 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC174 Test Description: Generate report for same surveys but in different modes
	 * 
	 */
	@Test
	public void TC174_ComplianceReportTest_VerifySameReportDifferentModes() {
		String rptTitle = "TC174 RR Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC174: Generate report for same surveys but in different modes, %s\n", rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
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
		viewMap.put(KEYLISA, "1");
		viewMap.put(KEYFOV, "1");
		viewMap.put(KEYBREADCRUMB, "1");
		viewMap.put(KEYINDICATIONS, "1");
		viewMap.put(KEYISOTOPICCAPTURE, "1");
		viewMap.put(KEYANNOTATION, "1");
		viewMap.put(KEYGAPS, "1");
		viewMap.put(KEYASSETS, "1");
		viewMap.put(KEYBOUNDARIES, "0");
		viewMap.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap);

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard, ReportModeFilter.RapidResponse);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser())))
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
		else
			fail("\n report creation failed.\n");
		complianceReportsPage.open();
		complianceReportsPage.logout();

		rptTitle = "TC174 Standard Report" + testSetup.getRandomNumber();

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();

		rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard, ReportModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser())))
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
		else
			fail("\nTestcase TC174 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC180 Test Description: Generate Manual report from existing reports having surveys of standard or Rapid Response types using copy feature
	 * 
	 */
	@Test
	public void TC180_ComplianceReportTest_VerifyCopyStandardReportAsManual() {
		String rptTitle = "TC180 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC180: Generate Manual report from existing reports having surveys of standard or Rapid Response types using copy feature , %s\n", rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();

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
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
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

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		assertTrue(complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser()));

		String newRptTitle = rptTitle + "COPY";

		List<String> tagListCopy = new ArrayList<String>();
		tagListCopy.add(PICADMNMANTAG);
		complianceReportsPage.copyReportAndModifyDetails(rptTitle, testSetup.getLoginUser(), newRptTitle, "", tagListCopy, true, ReportModeFilter.Manual);
		complianceReportsPage.waitForPageLoad();

		assertTrue(complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser()));

		if ((complianceReportsPage.checkActionStatus(newRptTitle, testSetup.getLoginUser()))) {
			assertTrue(complianceReportsPage.findReport(newRptTitle, testSetup.getLoginUser()));

		} else
			fail("\nTestcase TC180 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC181 Test Description: Generate standard or rapid response report from existing reports having survey of Manual type using copy feature
	 * 
	 */
	@Test
	public void TC181_ComplianceReportTest_VerifyCopyManualReportAsRapidResponse() {
		String rptTitle = "TC181 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC181: Generate  standard or rapid response report from existing reports having survey of Manual type using copy feature , %s\n", rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();

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
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
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

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNMANTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Manual, ReportModeFilter.Manual);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		assertTrue(complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser()));

		String newRptTitle = rptTitle + "COPY";
		List<String> surTag = new ArrayList<String>();
		surTag.add(PICADMNRRTAG);

		complianceReportsPage.copyReportAndModifyDetails(rptTitle, PICDFADMIN, newRptTitle, "", surTag, true, ReportModeFilter.RapidResponse);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(newRptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findReport(newRptTitle, PICDFADMIN));
		else
			fail("\nTestcase TC181 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC182 Test Description: Generate standard report from existing reports having survey of Rapid Response type using copy feature
	 * 
	 */
	@Test
	public void TC182_ComplianceReportTest_VerifyCopyRapidResponseReportAsStandard() {
		String rptTitle = "TC182 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC182: Generate standard report from existing reports having survey of Rapid Response type using copy feature, %s\n", rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();

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
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
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

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNRRTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.RapidResponse, ReportModeFilter.RapidResponse);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		assertTrue(complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser()));

		String newRptTitle = rptTitle + "COPY";
		List<String> surTag = new ArrayList<String>();
		surTag.add(PICADMNSTDTAG);

		complianceReportsPage.copyReportAndModifyDetails(rptTitle, PICDFADMIN, newRptTitle, "", surTag, true, ReportModeFilter.Standard);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(newRptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findReport(newRptTitle, PICDFADMIN));
		else
			fail("\nTestcase TC182 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC184 Test Description: Very small or big report area selection not allowed
	 * 
	 */
	@Test
	public void TC184_ComplianceReportTest_VerifyAreaErrorMessage() {
		System.out.format("\nRunning TC184_: Very small or big report area selection not allowed\n");
		String rptTitle = "TC184_Report" + testSetup.getRandomNumber();
		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add("36.42252593456309");
		listBoundary.add("-122.83494567871095");
		listBoundary.add("38.27989023941680");
		listBoundary.add("-124.05415725708008");

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
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
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

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);
		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard, ReportModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);

		Assert.assertEquals(complianceReportsPage.getAreaErrorText(), STRReportAreaTooLargeMsg);

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC185 Test Description: Click on Cancel button present on compliance report screen
	 * 
	 */
	@Test
	public void TC185_ComplianceReportTest_VerifyCancelButtonFunctionality() {
		System.out.format("\nRunning TC185: Click on Cancel button present on compliance report screen\n");

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();

		assertTrue(complianceReportsPage.verifyCancelButtonFunctionality());

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC197 Test Description: Verify "Add Survey" message is displayed when no Survey added
	 * 
	 */
	@Test
	public void TC197_ComplianceReportTest_VerifyAddSurveyErrorMessages() {
		System.out.format("\nRunning TC197: Verify 'Add Survey' message is displayed when no Survey added\n");
		String rptTitle = "TC197 Report" + testSetup.getRandomNumber();
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
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));
		viewList.add(viewMap1);

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();
		assertTrue(complianceReportsPage.verifySurveyNotAdded(rptTitle, "Picarro", RNELAT, RNELON, RSWLAT, RSWLON, viewList));

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC167 Test Description: Customer Admin can delete the specified report
	 * 
	 */
	@Test
	public void TC167_ComplianceReportTest_CusAdminCanDeleteReport() {
		String rptTitle = "TC167 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC167: User can delete the specified report, %s\n", rptTitle);

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
		tableMap.put(KEYISOANA, "0");
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
		viewMap.put(KEYBREADCRUMB, "0");
		viewMap.put(KEYINDICATIONS, "0");
		viewMap.put(KEYISOTOPICCAPTURE, "0");
		viewMap.put(KEYANNOTATION, "0");
		viewMap.put(KEYGAPS, "0");
		viewMap.put(KEYASSETS, "0");
		viewMap.put(KEYBOUNDARIES, "0");
		viewMap.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));

		viewList.add(viewMap);

		List<String> tagList = new ArrayList<String>();
		tagList.add(CUSDRVSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, SQACUSUA, "sqacus", TIMEZONEET, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		assertTrue(complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, SQACUSUA));

		if (complianceReportsPage.deleteReport(rptTitle, SQACUSUA))
			assertTrue(!(complianceReportsPage.findReportbySearch(rptTitle, SQACUSUA)));
		else
			fail("\nTestcase TC167 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC168 Test Description: Customer Supervisor can delete the specified report
	 * 
	 */
	@Test
	public void TC168_ComplianceReportTest_CusSupervisorCanDeleteReport() {
		String rptTitle = "TC168 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC168: User can delete the specified report, %s\n", rptTitle);

		loginPage.open();
		loginPage.loginNormalAs(SQACUSSU, USERPASSWORD);
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
		tableMap.put(KEYISOANA, "0");
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
		viewMap.put(KEYBREADCRUMB, "0");
		viewMap.put(KEYINDICATIONS, "0");
		viewMap.put(KEYISOTOPICCAPTURE, "0");
		viewMap.put(KEYANNOTATION, "0");
		viewMap.put(KEYGAPS, "0");
		viewMap.put(KEYASSETS, "0");
		viewMap.put(KEYBOUNDARIES, "0");
		viewMap.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));

		viewList.add(viewMap);

		List<String> tagList = new ArrayList<String>();
		tagList.add(CUSDRVSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, SQACUSSU, "sqacus", TIMEZONEET, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		assertTrue(complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, SQACUSSU));

		if (complianceReportsPage.deleteReport(rptTitle, SQACUSSU))
			assertTrue(!(complianceReportsPage.findReportbySearch(rptTitle, SQACUSSU)));
		else
			fail("\nTestcase TC168 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC212 Test Description: Resubmit compliance report from previously generated reports
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void TC212_ComplianceReportTest_VerifyResubmitReport() {
		String rptTitle = "TC212 Report" + testSetup.getRandomNumber();
		Log.info("\nRunning TC212: Resubmit compliance report from previously generated reports, " + rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();

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
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
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

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNMANTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Manual, ReportModeFilter.Manual);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		assertTrue(complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser()));

		try {
			complianceReportsPage.clickComplianceReportButton(rptTitle, testSetup.getLoginUser(), ComplianceReportButtonType.Resubmit);
			complianceReportsPage.waitForResubmitButton();
			complianceReportsPage.getBtnResubmitReport().click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser()))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));

		} else
			fail("\nTestcase TC212 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC797 Test Description: Search compliance reports based on report name
	 * 
	 */
	@Test
	public void TC797_ComplianceReportTest_SearchReportByReportName() {
		String rptTitle = "TC797 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC797: Search compliance reports based on report name, %s\n", rptTitle);

		loginPage.open();
		loginPage.loginNormalAs(SQACUSSU, USERPASSWORD);
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
		tableMap.put(KEYISOANA, "0");
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
		viewMap.put(KEYBREADCRUMB, "0");
		viewMap.put(KEYINDICATIONS, "0");
		viewMap.put(KEYISOTOPICCAPTURE, "0");
		viewMap.put(KEYANNOTATION, "0");
		viewMap.put(KEYGAPS, "0");
		viewMap.put(KEYASSETS, "0");
		viewMap.put(KEYBOUNDARIES, "0");
		viewMap.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));

		viewList.add(viewMap);

		List<String> tagList = new ArrayList<String>();
		tagList.add(CUSDRVSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, SQACUSSU, "sqacus", TIMEZONEET, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if (complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, SQACUSSU))
			assertTrue((complianceReportsPage.findReportbySearch(rptTitle, SQACUSSU)));
		else
			fail("\nTestcase TC797 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC1275 Test Description: User friendly message should be displayed if user has include assets and boundaries in views but not selected any asset and boundaries layers in optional
	 * view layers section
	 * 
	 */
	@Test
	public void TC1275_ComplianceReportTest_VerifyAreaErrorMessage() {
		System.out.format("\nRunning TC1275_: User friendly message should be displayed if user has include assets and boundaries in views but not selected any asset and boundaries layers in optional view layers section\n");
		String rptTitle = "TC1275_Report" + testSetup.getRandomNumber();
		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();

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
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

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

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard, ReportModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);

		Assert.assertEquals(complianceReportsPage.getAssetErrorText(), STRReportAssetNotSelectedMsg);
		Assert.assertEquals(complianceReportsPage.getAssetErrorText(), STRReportBoundaryNotSelectedMsg);

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC297 Test Description: Software version on UI and reports PDF should match
	 * 
	 */
	@Test
	public void TC297_ComplianceReportTest_VerifyVersion() {
		String rptTitle = "TC297 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC297: Software version on UI and reports PDF should match, %s\n", rptTitle);

		loginPage.open();
		loginPage.loginNormalAs(SQACUSSU, USERPASSWORD);
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
		tableMap.put(KEYISOANA, "0");
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
		viewMap.put(KEYGAPS, "1");
		viewMap.put(KEYASSETS, "0");
		viewMap.put(KEYBOUNDARIES, "0");
		viewMap.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));

		viewList.add(viewMap);

		List<String> tagList = new ArrayList<String>();
		tagList.add(CUSDRVSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, SQACUSSU, "sqacus", TIMEZONEPTUA, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSSU))) {
			if (complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findReport(rptTitle, SQACUSSU));
			} else
				fail("\nTestcase TC297 failed.\n");
		} else
			fail("\nTestcase TC297 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

}