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
import static surveyor.scommon.source.SurveyorConstants.CUSDRVETHTAG;
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
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.CryptoUtility;
import common.source.Log;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.dataprovider.ComplianceReportEthaneDataProvider;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.Reports.EthaneFilter;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.SurveyorTestRunner;

/**
 * 
 * 
 */
@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest_Ethane extends SurveyorBaseTest {
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
	 * Test Case ID: TC1634 Test Description: Ethane: Compliance Report UI: Verify Ethane Filter is available - New Compliance Report
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void TC1634_Ethane_VerifyCheckBoxes() {
		String rptTitle = "TC1634 Ethane" + testSetup.getRandomNumber();
		Log.info("\nRunning TC1634: Ethane: Compliance Report UI: Verify Ethane Filter is available - New Compliance Report, " + rptTitle);

		complianceReportsPage.login(SQAPICSUP, USERPASSWORD);
		complianceReportsPage.open();
		complianceReportsPage.openNewComplianceReportPage();

		assertTrue(complianceReportsPage.getCheckBoxVehicleExhaust().isDisplayed());
		assertTrue(complianceReportsPage.getCheckBoxEtheneBiogeniceMethane().isDisplayed());

		complianceReportsPage.logout();

	}

	/**
	 * Test Case ID: TC1637 Test Description: Ethane: Compliance Report UI: Verify Ethane Filter is available in Copy Report Page
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void TC1637_Ethane_Copy_Report_VerifyCheckBoxes() {
		String rptTitle = "TC1637 Ethane" + testSetup.getRandomNumber();
		Log.info("\nRunning TC1637: Ethane: Compliance Report UI: Verify Ethane Filter is available in Copy Report Page, " + rptTitle);

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

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		assertTrue(complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser()));
		complianceReportsPage.waitForPageLoad();

		complianceReportsPage.clickOnCopyReport(rptTitle, testSetup.getLoginUser());
		complianceReportsPage.waitForPageLoad();

		if (complianceReportsPage.getCheckBoxVehicleExhaust().isDisplayed())
			assertTrue(complianceReportsPage.getCheckBoxVehicleExhaust().isDisplayed());
		else
			fail("\nTestcase TC1637 failed.\n");

		if (complianceReportsPage.getCheckBoxEtheneBiogeniceMethane().isDisplayed())
			assertTrue(complianceReportsPage.getCheckBoxEtheneBiogeniceMethane().isDisplayed());
		else
			fail("\nTestcase TC1637 failed.\n");

		complianceReportsPage.logout();

	}

	/**
	 * Test Case ID: TC1654 Test Description: Ethane: Compliance Report UI: Verify Analysis column in Views table
	 * @throws InterruptedException 
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void TC1654_Ethane_Verify_Analyses_Column_View_Table() throws IOException{
		String rptTitle = "TC1654 Ethane" + testSetup.getRandomNumber();
		Log.info("\nRunning TC1654: Ethane: Compliance Report UI: Verify Analysis column in Views table, " + rptTitle);

		complianceReportsPage.login(SQAPICSUP, USERPASSWORD);
		complianceReportsPage.open();
		complianceReportsPage.openNewComplianceReportPage();

		assertTrue(complianceReportsPage.getViewsAnalysesColumn().getText().equalsIgnoreCase(Resources.getResource(ResourceKeys.ComplianceReportSSRS_Analysis)));

		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC1636 Test Description: Ethane: Compliance Report UI: Verify Ethane & Non-Ethane Standard mode surveys are displayed
	 * @throws InterruptedException 
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void TC1636_Ethane_Verify_NONEthane_Survey() throws IOException, InterruptedException{
		String rptTitle = "TC1636 Ethane" + testSetup.getRandomNumber();
		Log.info("\nRunning TC1636: Ethane: Compliance Report UI: Verify Ethane & Non-Ethane Standard mode surveys are displayed" + rptTitle);

		complianceReportsPage.login(SQAPICSUP, USERPASSWORD);
		complianceReportsPage.open();
		complianceReportsPage.openNewComplianceReportPage();

		assertTrue(complianceReportsPage.verifySurveysTableViaTag(true, ReportModeFilter.Standard, CUSDRVSTDTAG));

		driver.navigate().refresh();
		assertTrue(complianceReportsPage.verifySurveysTableViaTag(true, ReportModeFilter.Standard, CUSDRVETHTAG));

		driver.navigate().refresh();
		assertTrue(complianceReportsPage.verifySurveysTableViaTag(true, ReportModeFilter.RapidResponse, CUSDRVSTDTAG));

		driver.navigate().refresh();
		assertTrue(complianceReportsPage.verifySurveysTableViaTag(true, ReportModeFilter.RapidResponse, CUSDRVETHTAG));

		complianceReportsPage.logout();

	}

	/**
	 * Test Case ID: TC1654 Test Description: Ethane: Compliance Report UI: Verify Analysis column in Optional Tabular PDF Content table
	 * @throws InterruptedException 
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void TC1725_Ethane_Verify_Analysis_Column_Tubular_Content() throws IOException{
		String rptTitle = "TC1725 Ethane" + testSetup.getRandomNumber();
		Log.info("\nRunning TC1725: Ethane: Compliance Report UI: Verify Analysis column in Optional Tabular PDF Content table" + rptTitle);

		complianceReportsPage.login(SQAPICSUP, USERPASSWORD);
		complianceReportsPage.open();
		complianceReportsPage.openNewComplianceReportPage();

		System.out.println(complianceReportsPage.getTubularAnalysisOption().getText());
		assertTrue(complianceReportsPage.getTubularAnalysisOption().getText().equalsIgnoreCase(" " + Resources.getResource(ResourceKeys.ComplianceReportSSRS_Analysis)));

		complianceReportsPage.logout();
	}

	/**
	 * Test Case ID: TC1639 Test Description: Ethane: Compliance Report UI: Verify Ethane & Non-Ethane operator mode surveys are displayed
	 * @throws InterruptedException 
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void TC1639_Ethane_Verify_Ethane_NONEthane_Survey_Via_Operator_SurveyMode() throws IOException, InterruptedException{
		String rptTitle = "TC1639 Ethane" + testSetup.getRandomNumber();
		Log.info("\nRunning TC1639: Ethane: Compliance Report UI: Verify Ethane & Non-Ethane operator mode surveys are displayed" + rptTitle);

		complianceReportsPage.login(SQAPICSUP, USERPASSWORD);
		complianceReportsPage.open();
		complianceReportsPage.openNewComplianceReportPage();

		assertTrue(complianceReportsPage.verifySurveysTableViaSurveyMode(true, ReportModeFilter.Standard, SurveyModeFilter.Operator));

		driver.navigate().refresh();
		assertTrue(complianceReportsPage.verifySurveysTableViaSurveyMode(true, ReportModeFilter.RapidResponse, SurveyModeFilter.Operator));

		complianceReportsPage.logout();

	}

	/**
	 * Test Case ID:  	TC1653 Test Description: Ethane: Compliance Report UI: Verify Ethane & Non-Ethane Rapid response mode surveys are displayed
	 * @throws InterruptedException 
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void TC1653_Ethane_Verify_Ethane_NONEthane_Survey_Via_RapidResponse_SurveyMode() throws IOException, InterruptedException{
		String rptTitle = "TC1653 Ethane" + testSetup.getRandomNumber();
		Log.info("\nRunning TC1653: Ethane: Compliance Report UI: Verify Ethane & Non-Ethane Rapid response mode surveys are displayed" + rptTitle);

		complianceReportsPage.login(SQAPICSUP, USERPASSWORD);
		complianceReportsPage.open();
		complianceReportsPage.openNewComplianceReportPage();

		assertTrue(complianceReportsPage.verifySurveysTableViaSurveyMode(true, ReportModeFilter.RapidResponse, SurveyModeFilter.RapidResponse));


		complianceReportsPage.getCheckBoxVehicleExhaust().isDisplayed();
		complianceReportsPage.getCheckBoxEtheneBiogeniceMethane().isDisplayed();


		complianceReportsPage.logout();

	}

	/**
	 * Test Case ID:TC1640 Test Description: Ethane: Compliance Report UI: Verify Ethane & Non-Ethane manual mode surveys are displayed
	 * @throws InterruptedException 
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void TC1640_Ethane_Verify_Ethane_NONEthane_Survey_Via_Manual_SurveyMode() throws IOException, InterruptedException{
		String rptTitle = "TC1640 Ethane" + testSetup.getRandomNumber();
		Log.info("\nRunning TC1640: Ethane: Compliance Report UI: Verify Ethane & Non-Ethane manual mode surveys are displayed" + rptTitle);

		complianceReportsPage.login(SQAPICSUP, USERPASSWORD);
		complianceReportsPage.open();
		complianceReportsPage.openNewComplianceReportPage();

		assertTrue(complianceReportsPage.verifySurveysTableViaSurveyMode(true, ReportModeFilter.Manual, SurveyModeFilter.Manual));

		complianceReportsPage.logout();

	}


	//Using data provider 12 test cases-generate reports with different report mode and different ethane checkbox selection
	//(TC1638, TC1642, TC1737 TC1658, TC1710, TC1712, TC1716, TC1714, TC1709, TC1711, TC1715, TC1713)

	@Test
	@UseDataProvider(value = ComplianceReportEthaneDataProvider.COMPLIANCE_ETHANE_REPORT_PROVIDER, location = ComplianceReportEthaneDataProvider.class)
	public void ComplianceReportTest_VerifyEthaneReport(String index, String strCreatedBy, String password, String cutomer, String timeZone, String exclusionRadius, String surveyorUnit, String userName, String startDate, String endDate, String fovOpacity, String lisaOpacity, Boolean geoFilter, ReportModeFilter reportMode, SurveyModeFilter surveyModeFilter, EthaneFilter ethaneFilter, List<String> listBoundary, List<String> tagList, List<Map<String, String>> tablesList,
			List<Map<String, String>> viewList, List<Map<String, String>> viewLayersList) {
		String rptTitle = null;
		rptTitle = getTestCaseName(index) + " " + "Report" + testSetup.getRandomNumber();

		Log.info("\nRunning " + getTestCaseName(index) + " - " + rptTitle);

		complianceReportsPage.login(strCreatedBy, CryptoUtility.decrypt(password));
		complianceReportsPage.open();

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, strCreatedBy, cutomer, timeZone, exclusionRadius, surveyorUnit, userName, startDate, endDate, fovOpacity, lisaOpacity, geoFilter, reportMode, surveyModeFilter, ethaneFilter, listBoundary, tagList, tablesList, viewList, viewLayersList);

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, strCreatedBy))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, strCreatedBy));

		} else
			fail("\nTestcase " + getTestCaseName(index) + " failed.\n");

		complianceReportsPage.open();
		complianceReportsPage.logout();
	}
	private static String getTestCaseName(String key) {
		return testCaseMap.get(key);
	}

	private static void createTestCaseMap() {
		testCaseMap.put("1", "TC1638");  //std--exclude vehicle exhaust
		testCaseMap.put("2", "TC1642");  //std--exclude biogenic methane
		testCaseMap.put("3", "TC1737");  //std--both
		testCaseMap.put("4", "TC1658");  //std--none
		testCaseMap.put("5", "TC1710");  //manual--exclude vehicle exhaust
		testCaseMap.put("6", "TC1712");  //manual--exclude biogenic methane
		testCaseMap.put("7", "TC1716");  //manual--both
		testCaseMap.put("8", "TC1714");  //manual--none
		testCaseMap.put("9", "TC1709");  //rapid--exclude vehicle exhaust
		testCaseMap.put("10", "TC1711");  //rapid--exclude biogenic methane
		testCaseMap.put("11", "TC1715");  //rapid--both
		testCaseMap.put("12", "TC1713");  //rapid--none		
	}
}
