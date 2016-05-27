/**
 * 
 */
package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.CUSDRVETHSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.CUSDRVETHRRTAG;
import static surveyor.scommon.source.SurveyorConstants.CUSDRVSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.CUSDRVRAPIDTAG;
import static surveyor.scommon.source.SurveyorConstants.ETHRNELAT;
import static surveyor.scommon.source.SurveyorConstants.ETHRNELON;
import static surveyor.scommon.source.SurveyorConstants.ETHRSWLAT;
import static surveyor.scommon.source.SurveyorConstants.ETHRSWLON;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPHEIGHT;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPWIDTH;
import static surveyor.scommon.source.SurveyorConstants.KEYANNOTATION;
import static surveyor.scommon.source.SurveyorConstants.KEYASSETS;
import static surveyor.scommon.source.SurveyorConstants.KEYBASEMAP;
import static surveyor.scommon.source.SurveyorConstants.KEYBOUNDARIES;
import static surveyor.scommon.source.SurveyorConstants.KEYBREADCRUMB;
import static surveyor.scommon.source.SurveyorConstants.KEYFOV;
import static surveyor.scommon.source.SurveyorConstants.KEYGAPS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDCLR;
import static surveyor.scommon.source.SurveyorConstants.KEYINDICATIONS;
import static surveyor.scommon.source.SurveyorConstants.KEYINDTB;
import static surveyor.scommon.source.SurveyorConstants.KEYISOANA;
import static surveyor.scommon.source.SurveyorConstants.KEYGAPTB;
import static surveyor.scommon.source.SurveyorConstants.KEYISOTOPICCAPTURE;
import static surveyor.scommon.source.SurveyorConstants.KEYPCF;
import static surveyor.scommon.source.SurveyorConstants.KEYLISA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCRA;
import static surveyor.scommon.source.SurveyorConstants.KEYCPSAMPLRPT;
import static surveyor.scommon.source.SurveyorConstants.KEYVIEWNAME;
import static surveyor.scommon.source.SurveyorConstants.RNELAT;
import static surveyor.scommon.source.SurveyorConstants.RNELON;
import static surveyor.scommon.source.SurveyorConstants.RSWLAT;
import static surveyor.scommon.source.SurveyorConstants.RSWLON;
import static surveyor.scommon.source.SurveyorConstants.SQAPICSUP;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEMT;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPT;
import static surveyor.scommon.source.SurveyorConstants.USERPASSWORD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.CryptoUtility;
import common.source.Log;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataprovider.ComplianceReportEthaneDataProvider;
import surveyor.dataprovider.ReportDataProvider;
import surveyor.scommon.source.BaseReportsPageTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.ComplianceReportsPage.ComplianceReportButtonType;
import surveyor.scommon.source.ReportsCompliance.EthaneFilter;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.ReportsSurveyInfo;
import surveyor.scommon.source.SurveyorTestRunner;

/**
 * 
 * 
 */
@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest_Ethane extends BaseReportsPageTest {
	private static ComplianceReportsPage complianceReportsPage = null;
	private String STRReportAreaTooLargeMsg = Resources.getResource(ResourceKeys.ComplianceReport_BoundaryMinSizeMessage);
	private String STRReportAssetNotSelectedMsg = Resources.getResource(ResourceKeys.ComplianceReport_InvalidAssetTypeMessage);
	private String STRReportBoundaryNotSelectedMsg = Resources.getResource(ResourceKeys.ComplianceReport_InvalidBoundaryTypeMessage);
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

	}

	/**
	 * Test Case ID: TC1637 Test Description: Ethane: Compliance Report UI: Verify Ethane Filter is available in Copy Report Page
	 * @throws Exception 
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void TC1637_Ethane_Copy_Report_VerifyCheckBoxes() throws Exception {
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
		tableMap.put(KEYGAPTB, "0");
		tableMap.put(KEYPCA, "0");
		tableMap.put(KEYPCRA, "0");
		tableMap.put(KEYPCF, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
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
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Map));

		viewList.add(viewMap1);

		List<String> tagList = new ArrayList<String>();
		tagList.add(CUSDRVETHSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEPT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser());

		complianceReportsPage.clickOnCopyReport(rptTitle, testSetup.getLoginUser());

		if (complianceReportsPage.getCheckBoxVehicleExhaust().isDisplayed())
			assertTrue(complianceReportsPage.getCheckBoxVehicleExhaust().isDisplayed());
		else
			fail("\nTestcase TC1637 failed.\n");

		if (complianceReportsPage.getCheckBoxEtheneBiogeniceMethane().isDisplayed())
			assertTrue(complianceReportsPage.getCheckBoxEtheneBiogeniceMethane().isDisplayed());
		else
			fail("\nTestcase TC1637 failed.\n");
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
	}

	/**
	 * Test Case ID: TC1636 Test Description: Ethane: Compliance Report UI: Verify Ethane & Non-Ethane Standard mode surveys are displayed
	 * @throws Exception 
	 * 
	 */
	@Test
	public void TC1636_Ethane_Verify_NONEthane_Survey() throws Exception{
		String rptTitle = "TC1636 Ethane" + testSetup.getRandomNumber();
		Log.info("\nRunning TC1636: Ethane: Compliance Report UI: Verify Ethane & Non-Ethane Standard mode surveys are displayed" + rptTitle);

		complianceReportsPage.login(SQAPICSUP, USERPASSWORD);
		complianceReportsPage.open();
		complianceReportsPage.openNewComplianceReportPage();

		assertTrue(complianceReportsPage.verifySurveysTableViaTag(true, ReportModeFilter.Standard, CUSDRVSTDTAG));

		driver.navigate().refresh();
		assertTrue(complianceReportsPage.verifySurveysTableViaTag(true, ReportModeFilter.Standard, CUSDRVETHSTDTAG));

		driver.navigate().refresh();
		assertTrue(complianceReportsPage.verifySurveysTableViaTag(true, ReportModeFilter.RapidResponse, CUSDRVRAPIDTAG));

		driver.navigate().refresh();
		assertTrue(complianceReportsPage.verifySurveysTableViaTag(true, ReportModeFilter.RapidResponse, CUSDRVETHRRTAG));
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
	}

	/**
	 * Test Case ID: TC1653 Test Description: Ethane: Compliance Report UI: Verify Ethane & Non-Ethane Rapid response mode surveys are displayed
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

	}


	//Using data provider 12 test cases-generate reports with different report mode and different ethane checkbox selection
	//(TC1638, TC1642, TC1737 TC1658, TC1710, TC1712, TC1716, TC1714, TC1709, TC1711, TC1715, TC1713)

	@Test
	@UseDataProvider(value = ComplianceReportEthaneDataProvider.COMPLIANCE_ETHANE_REPORT_PROVIDER, location = ComplianceReportEthaneDataProvider.class)
	public void ComplianceReportTest_VerifyEthaneReport(String index, String strCreatedBy, String password, String cutomer, String timeZone, String exclusionRadius, String surveyorUnit, String userName, String startDate, String endDate, String fovOpacity, String lisaOpacity, Boolean geoFilter, ReportModeFilter reportMode, SurveyModeFilter surveyModeFilter, EthaneFilter ethaneFilter, List<String> listBoundary, List<String> tagList, List<Map<String, String>> tablesList,
			List<Map<String, String>> viewList, List<Map<String, String>> viewLayersList) throws Exception {
		String rptTitle = null;
		String testCaseName = getTestCaseName(index);
		rptTitle = testCaseName + " " + "Report" + testSetup.getRandomNumber();

		Log.info("\nRunning " + testCaseName + " - " + rptTitle);

		complianceReportsPage.login(strCreatedBy, CryptoUtility.decrypt(password));
		complianceReportsPage.open();

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, strCreatedBy, cutomer, timeZone, exclusionRadius, surveyorUnit, userName, startDate, endDate, fovOpacity, lisaOpacity, geoFilter, reportMode, surveyModeFilter, ethaneFilter, listBoundary, tagList, tablesList, viewList, viewLayersList);
		List<ReportsSurveyInfo> reportSurveyInfoList = ReportDataProvider.buildReportSurveyInfoList("24");
		rpt.setSurveyInfoList(reportSurveyInfoList);    
		rpt.setCustomerBoundaryInfo(ReportsCompliance.CustomerBoundaryFilterType.SmallBoundary, "TestPlat-Auto-1.5km");

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		if ((complianceReportsPage.checkActionStatus(rptTitle, strCreatedBy, testCaseName))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, strCreatedBy));
			assertTrue(complianceReportsPage.verifyComplianceReportStaticText(rptTitle));
			assertTrue(complianceReportsPage.verifySSRSImages(testSetup.getDownloadPath(), rptTitle, testCaseName));
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
				assertTrue(complianceReportsPage.verifyAllViewsImages(testSetup.getDownloadPath(), rptTitle, testCaseName,viewList.size()));
				if (tablesList.get(0).get(KEYISOANA).equals("1")) {
					assertTrue(complianceReportsPage.verifyIsotopicAnalysisTable(testSetup.getDownloadPath(), rptTitle));
				}
				if (tablesList.get(0).get(KEYINDTB).equals("1")) {
					assertTrue(complianceReportsPage.verifyIndicationTable(testSetup.getDownloadPath(), rptTitle));
				}
			}
		} else
			fail("\nTestcase " + getTestCaseName(index) + " failed.\n");

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

	/**
	 * Test Case ID:TC1717 Test Description: Compliance Report Generation : Remove user selection color for Indications
	 * @throws InterruptedException 
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void TC1717_Ethane_Verify_Indication_Table_Color_Selection_Removal() throws IOException, InterruptedException{
		String rptTitle = "TC1717 Ethane" + testSetup.getRandomNumber();
		Log.info("\nRunning TC1717: Compliance Report Generation : Remove user selection color for Indications" + rptTitle);

		complianceReportsPage.login(SQAPICSUP, USERPASSWORD);
		complianceReportsPage.open();
		complianceReportsPage.openNewComplianceReportPage();

		complianceReportsPage.getBtnSurveySearch().click();
		complianceReportsPage.waitForSurveyTabletoLoad();
		complianceReportsPage.waitForSurveySelectorCheckBoxToLoad();
		complianceReportsPage.waitForSurveySelectorCheckBoxToBeEnabled();
		complianceReportsPage.getCheckboxSurFirst().click();
		complianceReportsPage.getBtnAddSurveys().click();

		complianceReportsPage.verifyIfInDrivingSurvey(KEYINDCLR);

	}

	/**
	 * Test Case ID:TC1718 Test Description: Compliance Report Generation : newly generated report should show default color only for Indication
	 * @throws Exception 
	 * 
	 */
	@Test
	public void TC1718_Ethane_Generate_Report_Verify_Indication_Table_Color() throws Exception{
		String testCaseID = "TC1718";
		String rptTitle = testCaseID + " Ethane" + testSetup.getRandomNumber();
		Log.info("\nRunning " + testCaseID
				+ ": Compliance Report Generation : newly generated report should show default color only for Indication" + rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add(ETHRNELAT);
		listBoundary.add(ETHRNELON);
		listBoundary.add(ETHRSWLAT);
		listBoundary.add(ETHRSWLON);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "0");
		tableMap.put(KEYPCA, "1");
		tableMap.put(KEYPCRA, "1");
		tableMap.put(KEYPCF, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

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
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "1");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));
		viewList.add(viewMap1);

		List<String> tagList = new ArrayList<String>();
		tagList.add(CUSDRVETHSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser());

		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
			assertTrue(complianceReportsPage.validatePdfFiles(rpt, testSetup.getDownloadPath()));
			assertTrue(complianceReportsPage.findReport(rptTitle, testSetup.getLoginUser()));
			assertTrue(complianceReportsPage.verifyComplianceReportStaticText(rptTitle));
			if (tablesList != null) {
				assertTrue(complianceReportsPage.verifyDrivingSurveysTable(testSetup.getDownloadPath(), rptTitle));
			}
		} else
			fail("\nTestcase TC1718 failed.\n");

	}

	/**
	 * Test Case ID:TC1719 Test Description: Compliance Report Generation : COPY generated report should show default color only for Indication
	 * @throws Exception 
	 * 
	 */
	@Test
	public void TC1719_Ethane_Copy_Report_Verify_Indication_Table_Color() throws Exception{
		String rptTitle = "TC1719 Ethane" + testSetup.getRandomNumber();
		Log.info("\nRunning TC1719: Compliance Report Generation : COPY generated report should show default color only for Indication" + rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add(ETHRNELAT);
		listBoundary.add(ETHRNELON);
		listBoundary.add(ETHRSWLAT);
		listBoundary.add(ETHRSWLON);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "0");
		tableMap.put(KEYPCA, "1");
		tableMap.put(KEYPCRA, "1");
		tableMap.put(KEYPCF, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

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
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "1");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));
		viewList.add(viewMap1);

		List<String> tagList = new ArrayList<String>();
		tagList.add(CUSDRVETHSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser());

		complianceReportsPage.getInputSearch().sendKeys(rptTitle);
		complianceReportsPage.clickOnFirstCopyComplianceBtn();

		complianceReportsPage.verifyIfInDrivingSurvey(KEYINDCLR);

	}

	/**
	 * Test Case ID:TC1727 Test Description: Compliance Report Generation : COPY generated report with custom selected Indication color should show default color only for Indication- customized Indication color
	 * @throws Exception 
	 * 
	 */
	@Test
	public void TC1727_Ethane_Verify_Indication_Column_Color_In_Copy_Generated_Report() throws Exception{
		String testCaseID = "TC1727";
		String rptTitle = "TC1727 Ethane" + testSetup.getRandomNumber();
		Log.info("\nRunning TC1727: Compliance Report Generation : COPY generated report with custom selected Indication color should show default color only for Indication- customized Indication color" + rptTitle);


		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add(ETHRNELAT);
		listBoundary.add(ETHRNELON);
		listBoundary.add(ETHRSWLAT);
		listBoundary.add(ETHRSWLON);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "0");
		tableMap.put(KEYPCA, "1");
		tableMap.put(KEYPCRA, "1");
		tableMap.put(KEYPCF, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

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
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "1");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));
		viewList.add(viewMap1);

		List<String> tagList = new ArrayList<String>();
		tagList.add(CUSDRVETHSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser());

		complianceReportsPage.clickComplianceReportButton(rptTitle, testSetup.getLoginUser(), ComplianceReportButtonType.Copy);
		complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser());
		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
				assertTrue(complianceReportsPage.verifyDrivingSurveysTable(testSetup.getDownloadPath(), rptTitle));
		} else
			fail("\n Testcase1727 failed.\n");	
	}

	/**
	 * Test Case ID:TC1729 Test Description: Compliance Report Generation : Picaro admin should resubmit the generate report request and generated report should have default color in Indication- Default Indication color
	 * @throws InterruptedException 
	 * 
	 * @throws IOException
	 * 
	 */
	@Test
	public void TC1729_Ethane_Resubmit_Report_Verify_Indication_Table_Color() throws Exception{
		String testCaseID = "TC1729";
		String rptTitle = testCaseID + " Ethane" + testSetup.getRandomNumber();
		Log.info("\nRunning " + testCaseID
				+ ": Compliance Report Generation : Picaro admin should resubmit the generate report request and generated report should have default color in Indication- Default Indication color" + rptTitle);

		complianceReportsPage.login(testSetup.getLoginUser(), testSetup.getLoginPwd());
		complianceReportsPage.open();

		List<String> listBoundary = new ArrayList<String>();
		listBoundary.add(IMGMAPHEIGHT);
		listBoundary.add(IMGMAPWIDTH);
		listBoundary.add(ETHRNELAT);
		listBoundary.add(ETHRNELON);
		listBoundary.add(ETHRSWLAT);
		listBoundary.add(ETHRSWLON);

		List<Map<String, String>> tablesList = new ArrayList<Map<String, String>>();
		Map<String, String> tableMap = new HashMap<String, String>();
		tableMap.put(KEYINDTB, "1");
		tableMap.put(KEYISOANA, "1");
		tableMap.put(KEYGAPTB, "0");
		tableMap.put(KEYPCA, "1");
		tableMap.put(KEYPCRA, "1");
		tableMap.put(KEYPCF, "0");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Integer> boundaryRowIDs = Arrays.asList(3, 4);				 // Boundary RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createOptionalViewLayersContent(assetRowIDs, boundaryRowIDs));

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
		viewMap1.put(KEYASSETS, "1");
		viewMap1.put(KEYBOUNDARIES, "1");
		viewMap1.put(KEYBASEMAP, Resources.getResource(ResourceKeys.Constant_Satellite));
		viewList.add(viewMap1);

		List<String> tagList = new ArrayList<String>();
		tagList.add(CUSDRVETHSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, testSetup.getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);

		complianceReportsPage.addNewReport(rpt);
		complianceReportsPage.waitForPageLoad();

		complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser());

		complianceReportsPage.clickComplianceReportButton(rptTitle, testSetup.getLoginUser(), ComplianceReportButtonType.Resubmit);
		complianceReportsPage.waitForReportGenerationtoComplete(rptTitle, testSetup.getLoginUser());
		if ((complianceReportsPage.checkActionStatus(rptTitle, testSetup.getLoginUser(), testCaseID))) {
				assertTrue(complianceReportsPage.verifyDrivingSurveysTable(testSetup.getDownloadPath(), rptTitle));
		}
		else
			fail("\nTestcase TC1729 failed.\n");
	}
}
