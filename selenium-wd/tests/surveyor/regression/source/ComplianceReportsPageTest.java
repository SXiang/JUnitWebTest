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
import static surveyor.scommon.source.SurveyorConstants.REPORTTITLE;
import static surveyor.scommon.source.SurveyorConstants.LISAINV;
import static surveyor.scommon.source.SurveyorConstants.GAPINV;
import static surveyor.scommon.source.SurveyorConstants.CGIINV;
import static surveyor.scommon.source.SurveyorConstants.APPRNAME;
import static surveyor.scommon.source.SurveyorConstants.APPRSIG;
import static surveyor.scommon.source.SurveyorConstants.RPTCRTDATE;
import static surveyor.scommon.source.SurveyorConstants.DATE;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import common.source.Log;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.SurveyorBaseTest;

/**
 * 
 * 
 */
public class ComplianceReportsPageTest extends SurveyorBaseTest {
	private static ComplianceReportsPage complianceReportsPage = null;
	private String STRReportAreaTooLargeMsg = "Please make sure your selected boundary is more than 0.5kms and less than 25kms";

	@BeforeClass
	public static void setupComplianceReportsPageTest() {
		complianceReportsPage = new ComplianceReportsPage(driver, baseURL, testSetup);
		PageFactory.initElements(driver, complianceReportsPage);

	}

	/**
	 * Test Case ID: TC517 Test Description: Generate compliance report with all default values/filters selected and download it
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void TC517_ComplianceReportTest_VerifywithDefaults() {
		String rptTitle = "TC517 Report" + testSetup.getRandomNumber();
		Log.info("\nRunning TC517: Generate compliance report with all default values/filters selected and download it, " + rptTitle);

		complianceReportsPage.login(SQAPICSUP, USERPASSWORD);
		complianceReportsPage.open();

		HashMap<String, String> expectedTextMap = new HashMap<String, String>();
		expectedTextMap.put("Map Height & Width", IMGMAPHEIGHT + " X " + IMGMAPWIDTH + " in");
		expectedTextMap.put("Time Zone", TIMEZONEPT);
		expectedTextMap.put("Exclusion Radius", EXCLUSIONRADIUS + " m");
		expectedTextMap.put("Report Mode", REPORTMODES);
		expectedTextMap.put("NE Lat & NE Long", RNELAT + " X " + RNELON);
		expectedTextMap.put("SW Lat & SW Long", RSWLAT + " X " + RSWLON);

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add(RNELAT);
		listBoundary.add(RNELON);
		listBoundary.add(RSWLAT);
		listBoundary.add(RSWLON);

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
		viewMap1.put(KEYGAPS, "1");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "1");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));

		viewMap2.put(KEYVIEWNAME, "Second View");
		viewMap2.put(KEYLISA, "1");
		viewMap2.put(KEYFOV, "1");
		viewMap2.put(KEYBREADCRUMB, "0");
		viewMap2.put(KEYINDICATIONS, "0");
		viewMap2.put(KEYISOTOPICCAPTURE, "0");
		viewMap2.put(KEYANNOTATION, "0");
		viewMap2.put(KEYGAPS, "0");
		viewMap2.put(KEYASSETS, "0");
		viewMap2.put(KEYBOUNDARIES, "0");
		viewMap2.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewMap3.put(KEYVIEWNAME, "Third View");
		viewMap3.put(KEYLISA, "0");
		viewMap3.put(KEYFOV, "0");
		viewMap3.put(KEYBREADCRUMB, "1");
		viewMap3.put(KEYINDICATIONS, "1");
		viewMap3.put(KEYISOTOPICCAPTURE, "1");
		viewMap3.put(KEYANNOTATION, "1");
		viewMap3.put(KEYGAPS, "0");
		viewMap3.put(KEYASSETS, "0");
		viewMap3.put(KEYBOUNDARIES, "0");
		viewMap3.put(KEYBASEMAP, "None");

		viewList.add(viewMap1);
		viewList.add(viewMap2);
		viewList.add(viewMap3);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();

		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYPCA, "1");
		tableMap.put(KEYPCRA, "1");
		tableMap.put(KEYASSETCASTIRON, "1");
		tableMap.put(KEYASSETCOPPER, "1");
		tableMap.put(KEYASSETOTHERPLASTIC, "1");
		tableMap.put(KEYASSETPEPLASTIC, "1");
		tableMap.put(KEYASSETPROTECTEDSTEEL, "1");
		tableMap.put(KEYASSETUNPROTECTEDSTEEL, "1");
		tableMap.put(KEYBOUNDARYDISTRICT, "1");
		tableMap.put(KEYBOUNDARYDISTRICTPLAT, "1");

		tablesList.add(tableMap);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, SQAPICSUP, "Picarro", TIMEZONEPT, EXCLUSIONRADIUS, listBoundary, tablesList, "", SQACUSDRTAG, viewList);

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQAPICSUP))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, SQAPICSUP));
			try {
				assertTrue(complianceReportsPage.compareComplianceRptFirstPageStaticText(testSetup.getDownloadPath()));

			} catch (IOException e) {
				Log.error(e.toString());
				fail("\nTestcase TC517 failed.\n");
			}

		} else
			fail("\nTestcase TC517 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC148 Test Description: Generate compliance report by selecting custom boundary using date range and tag filters for more than one view and export the report
	 * 
	 */
	@Test
	public void TC148_ComplianceReportTest_VerifyCustomBoundarywithMultiViews() {
		String rptTitle = "TC148 Report" + testSetup.getRandomNumber();

		System.out.format("\nRunning TC148: Generate compliance report by selecting custom boundary using date range and tag filters for more than one view and export the report", rptTitle);

		complianceReportsPage.login(SQAPICSUP, USERPASSWORD);
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
		Map<String, String> viewMap2 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "0");
		viewMap1.put(KEYFOV, "0");
		viewMap1.put(KEYBREADCRUMB, "0");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "0");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewMap2.put(KEYVIEWNAME, "Second View");
		viewMap2.put(KEYLISA, "1");
		viewMap2.put(KEYFOV, "1");
		viewMap2.put(KEYBREADCRUMB, "0");
		viewMap2.put(KEYINDICATIONS, "1");
		viewMap2.put(KEYISOTOPICCAPTURE, "1");
		viewMap2.put(KEYANNOTATION, "0");
		viewMap2.put(KEYGAPS, "0");
		viewMap2.put(KEYASSETS, "0");
		viewMap2.put(KEYBOUNDARIES, "0");
		viewMap2.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));

		viewList.add(viewMap1);
		viewList.add(viewMap2);

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

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, SQAPICSUP, "Picarro", TIMEZONECT, "0", listBoundary, tablesList, "", PICADMNSTDTAG, RSURSTARTDATE, RSURENDDATE, viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQAPICSUP))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, SQAPICSUP));
			try {
				assertTrue(complianceReportsPage.compareComplianceRptFirstPageStaticText(testSetup.getDownloadPath()));
			} catch (IOException e) {
				Log.error(e.toString());
				fail("\nTestcase TC148 failed.\n");
			}

		} else
			fail("\nTestcase TC148 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();

	}

	/**
	 * Test Case ID: TC150 Test Description: Generate Compliance Report and include Picarro Surveyor Unit filter
	 * 
	 */
	@Test
	public void TC150_ComplianceReportTest_VerifyReportGenerationbySurveyor() {
		String rptTitle = "TC150 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC150: Generate Compliance Report and include Picarro Surveyor Unit filter, %s\n", rptTitle);

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
		viewMap1.put(KEYLISA, "0");
		viewMap1.put(KEYFOV, "0");
		viewMap1.put(KEYBREADCRUMB, "0");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "0");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "0");
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

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONECT, "0", listBoundary, tablesList, PICADMNSURVEYOR, "", "", "", viewList, SurveyModeFilter.All);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			} else
				fail("\nTestcase TC150 failed.\n");
		} else
			fail("\nTestcase TC150 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC151 Test Description: Generate report by providing tag filter
	 * 
	 */
	@Test
	public void TC151_ComplianceReportTest_VerifyReportGenerationbyTagFilter() {
		String rptTitle = "TC151 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC151: Generate report by providing tag filter including survey with isotopic analysis data, %s\n", rptTitle);

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
		Map<String, String> viewMap1 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "0");
		viewMap1.put(KEYFOV, "0");
		viewMap1.put(KEYBREADCRUMB, "0");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "0");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, SQAPICSUP, "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", PICADMNSTDTAG, RSURSTARTDATE, RSURENDDATE, viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			} else
				fail("\nTestcase TC151 failed.\n");
		} else
			fail("\nTestcase TC151 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC152 Test Description: Generate compliance report using date range and surveyor unit filters for more than one view and download the report
	 * 
	 */
	@Test
	public void TC152_ComplianceReportTest_VerifyReportGenerationbyTagFilter() {
		String rptTitle = "TC152 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC152: Generate compliance report using date range and surveyor unit filters for more than one view and download the report, %s\n", rptTitle);

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
		Map<String, String> viewMap1 = new HashMap<String, String>();
		Map<String, String> viewMap2 = new HashMap<String, String>();

		viewMap1.put(KEYVIEWNAME, "First View");
		viewMap1.put(KEYLISA, "0");
		viewMap1.put(KEYFOV, "0");
		viewMap1.put(KEYBREADCRUMB, "0");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "1");
		viewMap1.put(KEYANNOTATION, "1");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "0");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewMap2.put(KEYVIEWNAME, "Second View");
		viewMap2.put(KEYLISA, "1");
		viewMap2.put(KEYFOV, "1");
		viewMap2.put(KEYBREADCRUMB, "0");
		viewMap2.put(KEYINDICATIONS, "1");
		viewMap2.put(KEYISOTOPICCAPTURE, "1");
		viewMap2.put(KEYANNOTATION, "0");
		viewMap2.put(KEYGAPS, "0");
		viewMap2.put(KEYASSETS, "1");
		viewMap2.put(KEYBOUNDARIES, "0");
		viewMap2.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));

		viewList.add(viewMap1);
		viewList.add(viewMap2);

		// Start and end date filter insertion remaining
		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONECT, "0", listBoundary, tablesList, PICADMNSURVEYOR, "", RSURSTARTDATE, RSURENDDATE, viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		assertTrue(complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser()));

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			} else
				fail("\nTestcase TC152 failed.\n");
		} else
			fail("\nTestcase TC152 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
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

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", PICADMNSTDTAG, RSURSTARTDATE, RSURENDDATE, viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		assertTrue(complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser()));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);
		complianceReportsPage.copyReportAndModifyDetails(rptTitle, testSetup.getLoginUser(), rptTitle + "COPY", PICADMNSURVEYOR, tagList, false, "");

		if ((complianceReportsPage.checkActionStatus(rptTitle + "COPY", PICDFADMIN)))
			assertTrue(complianceReportsPage.findReport(rptTitle + "COPY", PICDFADMIN));
		else
			fail("\nTestcase TC153 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC154 Test Description: Generate Compliance Report and include Coverage Percentage of the assets
	 * 
	 */
	@Test
	public void TC154_ComplianceReportTest_VerifyGenerateComplianceReportforCoverageofAssets() {
		String rptTitle = "TC154 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC154: Generate Compliance Report and include Coverage Percentage of the assets, %s\n", rptTitle);

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

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", PICADMNSTDTAG, "", "", viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			} else
				fail("\nTestcase TC154 failed.\n");
		} else
			fail("\nTestcase TC154 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC155 Test Description: Generate Compliance Report and include Coverage Percentage by area
	 * 
	 */
	@Test
	public void TC155__ComplianceReportTest_VerifyComplianeReportGenerationbyCoveragePercentageArea() {
		String rptTitle = "TC155 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC155: Generate Compliance Report and include Coverage Percentage by area, %s\n", rptTitle);

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
		tableMap.put(KEYPCRA, "1");
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

		// Start and end date filter insertion remaining
		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", PICADMNSTDTAG, "", "", viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			} else
				fail("\nTestcase TC155 failed.\n");
		} else
			fail("\nTestcase TC155 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC156 Generate reports showing gaps that are not covered
	 * 
	 */
	@Test
	public void TC156_ComplianceReportTest_VerifyComplianeReportGenerationGapsNotCoveredbySurvey() {
		String rptTitle = "TC156 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC156: Generate reports showing gaps for report area that is not covered by survey, %s\n", rptTitle);

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

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", PICADMNSTDTAG, "", "", viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			} else
				fail("\nTestcase TC156 failed.\n");
		} else
			fail("\nTestcase TC156 failed.\n");

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

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", PICADMNSTDTAG, "", "", viewList, SurveyModeFilter.Standard);
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

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", PICADMNSTDTAG, "", "", viewList, SurveyModeFilter.Standard);
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
	 * Test Case ID: TC169 Generate compliance report for provided custom boundary without having the indications, Isotopic Analysis tables data and download it
	 * 
	 */
	@Test
	public void TC169_ComplianceReportTest_VerifyReportGenerationwithoutIndicationsIsotopicTable() {
		String rptTitle = "TC169 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC169: Generate compliance report for provided custom boundary without having the indications, Isotopic Analysis tables data and download it, %s\n", rptTitle);

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

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEET, "0", listBoundary, tablesList, "", PICADMNSTDTAG, "", "", viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();
		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser()))) {
			if (complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			} else
				fail("\nTestcase TC169 failed.\n");
		} else
			fail("\nTestcase TC169 failed.\n");

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

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", PICADMNSTDTAG, RSURSTARTDATE, RSURENDDATE, viewList, SurveyModeFilter.Standard);
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
	 * Test Case ID: TC173 Test Description: Generate compliance report for provided custom boundary using date range filter for manual surveys and download it
	 * 
	 */
	@Test
	public void TC173_ComplianceReportTest_VerifyManualReport() {
		String rptTitle = "TC173 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC173: Generate compliance report for provided custom boundary using date range filter for manual surveys and download it, %s\n", rptTitle);

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

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", PICADMNMANTAG, "", "", viewList, SurveyModeFilter.Manual, ReportModeFilter.Manual);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser())))
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
		else
			fail("\nTestcase TC173 failed.\n");

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

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", PICADMNSTDTAG, "", "", viewList, SurveyModeFilter.Standard, ReportModeFilter.RapidResponse);
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
		rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", PICADMNSTDTAG, "", "", viewList, SurveyModeFilter.Standard, ReportModeFilter.Standard);
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
	 * Test Case ID: TC175 Test Description: Generate report as Picarro Admin user for the survey done by any of the customer user
	 * 
	 */
	@Test
	public void TC175_ComplianceReportTest_VerifyGenerateReportasAdminforOtherUserSurveys() {
		String rptTitle = "TC175 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC175: Generate report as Picarro Admin user for the survey done by any of the customer user, %s\n", rptTitle);

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
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
		viewMap.put(KEYASSETS, "0");
		viewMap.put(KEYBOUNDARIES, "0");
		viewMap.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "sqacus", TIMEZONEMT, "0", listBoundary, tablesList, "", CUSDRVSTDTAG, "", "", viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN))) {
			if (complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findReport(rptTitle, PICDFADMIN));
			} else
				fail("\nTestcase TC175 failed.\n");
		} else
			fail("\nTestcase TC175 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC176 Test Description: Generate compliance report as customer supervisor
	 * 
	 */
	@Test
	public void TC176_ComplianceReportTest_VerifyReportasCustomerSupervisor() {
		String rptTitle = "TC176 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC176: Generate report as Picarro Admin user for the survey done by any of the customer user, %s\n", rptTitle);

		loginPage.open();
		complianceReportsPage.login(SQAPICSUP, USERPASSWORD);
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
		Map<String, String> viewMap1 = new HashMap<String, String>();
		Map<String, String> viewMap2 = new HashMap<String, String>();
		Map<String, String> viewMap3 = new HashMap<String, String>();
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

		viewMap1.put(KEYVIEWNAME, "Second View");
		viewMap1.put(KEYLISA, "0");
		viewMap1.put(KEYFOV, "0");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "1");
		viewMap1.put(KEYISOTOPICCAPTURE, "0");
		viewMap1.put(KEYANNOTATION, "0");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "0");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

		viewMap2.put(KEYVIEWNAME, "Third View");
		viewMap2.put(KEYLISA, "1");
		viewMap2.put(KEYFOV, "1");
		viewMap2.put(KEYBREADCRUMB, "0");
		viewMap2.put(KEYINDICATIONS, "0");
		viewMap2.put(KEYISOTOPICCAPTURE, "1");
		viewMap2.put(KEYANNOTATION, "0");
		viewMap2.put(KEYGAPS, "0");
		viewMap2.put(KEYASSETS, "0");
		viewMap2.put(KEYBOUNDARIES, "0");
		viewMap2.put(KEYBASEMAP, "None");

		viewList.add(viewMap2);

		viewMap3.put(KEYVIEWNAME, "Fourth View");
		viewMap3.put(KEYLISA, "0");
		viewMap3.put(KEYFOV, "1");
		viewMap3.put(KEYBREADCRUMB, "0");
		viewMap3.put(KEYINDICATIONS, "0");
		viewMap3.put(KEYISOTOPICCAPTURE, "0");
		viewMap3.put(KEYANNOTATION, "0");
		viewMap3.put(KEYGAPS, "1");
		viewMap3.put(KEYASSETS, "0");
		viewMap3.put(KEYBOUNDARIES, "0");
		viewMap3.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));

		viewList.add(viewMap3);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, SQAPICSUP, "sqacus", TIMEZONEPT, "0", listBoundary, tablesList, "", CUSDRVSTDTAG, "", "", viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQAPICSUP))) {
			if (complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findReport(rptTitle, SQAPICSUP));
			} else
				fail("\nTestcase TC176 failed.\n");
		} else
			fail("\nTestcase TC176 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC177 Test Description: Generate compliance report as customer admin
	 * 
	 */
	@Test
	public void TC177_ComplianceReportTest_VerifyReportsCustomerAdmin() {
		String rptTitle = "TC177 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC177: Generate compliance report as customer admin, survey owner, %s\n", rptTitle);

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
		Map<String, String> viewMap1 = new HashMap<String, String>();

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

		viewMap1.put(KEYVIEWNAME, "Second View");
		viewMap1.put(KEYLISA, "0");
		viewMap1.put(KEYFOV, "0");
		viewMap1.put(KEYBREADCRUMB, "1");
		viewMap1.put(KEYINDICATIONS, "0");
		viewMap1.put(KEYISOTOPICCAPTURE, "0");
		viewMap1.put(KEYANNOTATION, "0");
		viewMap1.put(KEYGAPS, "0");
		viewMap1.put(KEYASSETS, "0");
		viewMap1.put(KEYBOUNDARIES, "0");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, SQACUSSU, "sqacus", TIMEZONEET, "0", listBoundary, tablesList, "", CUSDRVSTDTAG, "", "", viewList, SurveyModeFilter.Standard);

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, SQACUSUA))) {
			if (complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findReport(rptTitle, SQACUSUA));
			} else
				fail("\nTestcase TC177 failed.\n");
		} else
			fail("\nTestcase TC177 failed.\n");

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

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", PICADMNSTDTAG, "", "", viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		assertTrue(complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser()));

		String newRptTitle = rptTitle + "COPY";

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNMANTAG);
		complianceReportsPage.copyReportAndModifyDetails(rptTitle, testSetup.getLoginUser(), newRptTitle, "", tagList, true, "manual");
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

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", PICADMNMANTAG, "", "", viewList, SurveyModeFilter.Manual, ReportModeFilter.Manual);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		assertTrue(complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser()));

		String newRptTitle = rptTitle + "COPY";
		List<String> surTag = new ArrayList<String>();
		surTag.add(PICADMNRRTAG);

		String changeReportMode = "rr";
		complianceReportsPage.copyReportAndModifyDetails(rptTitle, PICDFADMIN, newRptTitle, "", surTag, true, changeReportMode);
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

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", PICADMNRRTAG, "", "", viewList, SurveyModeFilter.RapidResponse, ReportModeFilter.RapidResponse);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		assertTrue(complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser()));

		String newRptTitle = rptTitle + "COPY";
		List<String> surTag = new ArrayList<String>();
		surTag.add(PICADMNSTDTAG);

		String changeReportMode = "standard";
		complianceReportsPage.copyReportAndModifyDetails(rptTitle, PICDFADMIN, newRptTitle, "", surTag, true, changeReportMode);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(newRptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findReport(newRptTitle, PICDFADMIN));
		else
			fail("\nTestcase TC182 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC183 Test Description: Generate report having multiple surveys of Standard, Operator and Rapid Response types in Rapid Response report mode
	 * 
	 */
	@Test
	public void TC183_ComplianceReportTest_VerifyReportwithMultipleSurveys() {
		String rptTitle = "TC183 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC183: Generate report having multiple surveys of Standard, Operator and Rapid Response types in Rapid Response report mode, %s\n", rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();

		String surUnit = "";
		List<String> surTag = new ArrayList<String>();
		surTag.add(PICADMNSTDTAG);
		surTag.add(PICADMNRRTAG);
		surTag.add(PICADMNOPTAG);
		String reportMode = "Rapid Response";
		boolean changeMode = true;

		complianceReportsPage.addNewPDReport(rptTitle, "Picarro" ,surUnit, surTag, changeMode, reportMode);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN)))
			assertTrue(complianceReportsPage.findReport(rptTitle, PICDFADMIN));
		else
			fail("\nTestcase TC183 failed.\n");

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

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", PICADMNSTDTAG, "", "", viewList, SurveyModeFilter.Standard, ReportModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

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
	 * Test Case ID: TC191 Test Description: Generate report having multiple surveys and verify Gaps for them
	 * 
	 */
	@Test
	public void TC191_ComplianceReportTest_VerifyMultipleSurveyGaps() {
		String rptTitle = "TC191 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC191: Generate report having multiple surveys and verify Gaps for them, %s\n", rptTitle);

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
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
		viewMap1.put(KEYLISA, "0");
		viewMap1.put(KEYFOV, "1");
		viewMap1.put(KEYBREADCRUMB, "0");
		viewMap1.put(KEYINDICATIONS, "0");
		viewMap1.put(KEYISOTOPICCAPTURE, "0");
		viewMap1.put(KEYANNOTATION, "0");
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

		String surUnit = "";
		String exclusionRadius = "0";
		String strCustomer = "Picarro";
		List<String> surTag = new ArrayList<String>();
		surTag.add(PICADMNSTDTAG);
		surTag.add(CUSDRVSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, PICDFADMIN, strCustomer, TIMEZONEET, exclusionRadius, listBoundary, tablesList, surUnit, surTag, viewList);
		complianceReportsPage.addNewReportWithMultipleSurveysIncluded(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN))) {
			assertTrue(complianceReportsPage.findReport(rptTitle, PICDFADMIN));

		} else
			fail("\nTestcase TC191 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC192 Test Description: Generate report having multiple surveys and provide exclusion radius
	 * 
	 */
	@Test
	public void TC192_ComplianceReportTest_VerifyMultipleSurveyExclusionRadius() {
		String rptTitle = "TC192 Report" + testSetup.getRandomNumber();
		System.out.format("\nRunning TC192: Generate report having multiple surveys and provide exclusion radius, %s\n", rptTitle);

		loginPage.open();
		loginPage.loginNormalAs(testSetup.getLoginUser(), testSetup.getLoginPwd());
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
		Map<String, String> viewMap2 = new HashMap<String, String>();

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

		viewMap2.put(KEYVIEWNAME, "Second View");
		viewMap2.put(KEYLISA, "1");
		viewMap2.put(KEYFOV, "1");
		viewMap2.put(KEYBREADCRUMB, "1");
		viewMap2.put(KEYINDICATIONS, "1");
		viewMap2.put(KEYISOTOPICCAPTURE, "1");
		viewMap2.put(KEYANNOTATION, "1");
		viewMap2.put(KEYGAPS, "0");
		viewMap2.put(KEYASSETS, "1");
		viewMap2.put(KEYBOUNDARIES, "0");
		viewMap2.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));

		viewList.add(viewMap1);
		viewList.add(viewMap2);

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

		String surUnit = "";
		String exclusionRadius = "100";
		String strCustomer = "Picarro";
		List<String> surTag = new ArrayList<String>();
		surTag.add(PICADMNSTDTAG);
		surTag.add(PICADMNOPTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, PICDFADMIN, strCustomer, TIMEZONEET, exclusionRadius, listBoundary, tablesList, surUnit, surTag, viewList);
		complianceReportsPage.addNewReportWithMultipleSurveysIncluded(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, PICDFADMIN))) {
			if (complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath())) {
				assertTrue(complianceReportsPage.findReport(rptTitle, PICDFADMIN));
			} else
				fail("\nTestcase TC192 failed.\n");
		} else
			fail("\nTestcase TC192 failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC197 Test Description: Verify "Already Added" message is displayed if user tries to add the same survey again
	 * 
	 */
	@Test
	public void TC197_ComplianceReportTest_VerifyAddSurveyErrorMessages() {
		System.out.format("\nRunning TC197: Verify 'Already Added' message is displayed if user tries to add the same survey again\n");

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();
		complianceReportsPage.openNewComplianceReportPage();

		assertTrue(complianceReportsPage.verifySurveyAlreadyAdded("Picarro", PICADMNSTDTAG));

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC198 Test Description: Verify "Already Added" message is displayed if user tries to add the same survey again using copy functionality
	 * 
	 */
	@Test
	public void TC198_ComplianceReportTest_VerifyAlreadyAddedMessageforCopy() {
		System.out.format("\nRunning TC198: Verify 'Already Added' message is displayed if user tries to add the same survey again using copy functionality\n");

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();
		String rptTitle = "TC198 Report" + testSetup.getRandomNumber();
		String surUnit = "";

		complianceReportsPage.addNewPDReport(rptTitle, surUnit, PICADMNSTDTAG);
		complianceReportsPage.waitForPageLoad();

		assertTrue(complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser()));

		complianceReportsPage.clickOnCopyReport(rptTitle, PICDFADMIN);

		assertTrue(complianceReportsPage.verifySurveyAlreadyAdded("Picarro", PICADMNSTDTAG));

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}

}