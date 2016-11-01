/**
 *
 */
package surveyor.regression.source;

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
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;

import static surveyor.scommon.source.SurveyorConstants.TIMEZONEET;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPHEIGHT;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPWIDTH;
import static surveyor.scommon.source.SurveyorConstants.RNELAT;
import static surveyor.scommon.source.SurveyorConstants.RNELON;
import static surveyor.scommon.source.SurveyorConstants.RSWLAT;
import static surveyor.scommon.source.SurveyorConstants.RSWLON;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.PICADMNRRTAG;
import static surveyor.scommon.source.SurveyorConstants.CUSDRVSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.PICADMNOPTAG;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataprovider.ReportDataProvider;
import surveyor.scommon.source.BaseReportsPageTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.PageObjectFactory;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.scommon.source.ReportsSurveyInfo;
import surveyor.scommon.source.SurveyorTestRunner;

/**
 *
 *
 */
@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsPageTest_CustomBuildRunner extends BaseReportsPageTest {
	private static LoginPage loginPage;

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects();
	}

	@Before
	public void beforeTest() {
		initializeTestObjects();

		PageObjectFactory pageObjectFactory = new PageObjectFactory();
		loginPage = pageObjectFactory.getLoginPage();
		PageFactory.initElements(getDriver(), loginPage);

		initializePageObjects(pageObjectFactory.getComplianceReportsPage());
	}

	private ComplianceReportsPage getComplianceReportsPage() {
		return (ComplianceReportsPage)getReportsPage();
	}

	/**
	 * Test Case ID: TC183 Test Description: Generate report having multiple surveys of Standard, Operator and Rapid Response types in Rapid Response report mode
	 * @throws Exception
	 *
	 */
	@Test
	public void TC183_ComplianceReportTest_VerifyReportwithMultipleSurveys() throws Exception {
		String testCaseID = "TC183";
		String rptTitle = testCaseID + " Report" + getTestSetup().getRandomNumber();
		System.out.format("\nRunning " + testCaseID
				+ ": Generate report having multiple surveys of Standard, Operator and Rapid Response types in Rapid Response report mode, %s\n", rptTitle);

		this.getComplianceReportsPage().login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		this.getComplianceReportsPage().open();

		String surUnit = "";
		List<String> surTag = new ArrayList<String>();
		surTag.add(PICADMNSTDTAG);
		surTag.add(PICADMNRRTAG);
		surTag.add(PICADMNOPTAG);
		String reportMode = "Rapid Response";
		boolean changeMode = true;

		this.getComplianceReportsPage().addNewPDReport(rptTitle, "Picarro", surUnit, surTag, changeMode, reportMode);
		this.getComplianceReportsPage().waitForPageLoad();

		if ((this.getComplianceReportsPage().checkActionStatus(rptTitle, PICDFADMIN, testCaseID))) {
			this.getComplianceReportsPage().clickOnReportViewerCloseButton();
			assertTrue(this.getComplianceReportsPage().findReport(rptTitle, PICDFADMIN));
		} else {
			fail("\nTestcase TC183 failed.\n");
		}
	}

	/**
	 * Test Case ID: TC191 Test Description: Generate report having multiple surveys and verify Gaps for them
	 * @throws Exception
	 *
	 */
	@Test
	public void TC191_ComplianceReportTest_VerifyMultipleSurveyGaps() throws Exception {
		String testCaseID = "TC191";
		String rptTitle = testCaseID + " Report" + getTestSetup().getRandomNumber();
		System.out.format("\nRunning " + testCaseID + ": Generate report having multiple surveys and verify Gaps for them, %s\n", rptTitle);

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
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
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createViewLayerAssetsContent(assetRowIDs));

		String surUnit = "";
		String exclusionRadius = "0";
		String strCustomer = "Picarro";

		List<String> surTag = new ArrayList<String>();
		surTag.add(PICADMNSTDTAG);
		surTag.add(CUSDRVSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, PICDFADMIN, strCustomer, TIMEZONEET, exclusionRadius, listBoundary, tablesList, surUnit, surTag, viewList);
		rpt.setViewLayersList(viewLayerList);

		this.getComplianceReportsPage().addNewReport(rpt);
		this.getComplianceReportsPage().waitForPageLoad();

		if ((this.getComplianceReportsPage().checkActionStatus(rptTitle, PICDFADMIN, testCaseID))) {
			this.getComplianceReportsPage().clickOnReportViewerCloseButton();
			assertTrue(this.getComplianceReportsPage().findReport(rptTitle, PICDFADMIN));

		} else
			fail("\nTestcase TC191 failed.\n");
	}

	/**
	 * Test Case ID: TC192 Test Description: Generate report having multiple surveys and provide exclusion radius
	 * @throws Exception
	 *
	 */
	@Test
	public void TC192_ComplianceReportTest_VerifyMultipleSurveyExclusionRadius() throws Exception {
		String testCaseID = "TC192";
		String rptTitle = testCaseID + " Report" + getTestSetup().getRandomNumber();
		System.out.format("\nRunning " + testCaseID + ": Generate report having multiple surveys and provide exclusion radius, %s\n", rptTitle);

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
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
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createViewLayerAssetsContent(assetRowIDs));

		String surUnit = "";
		String exclusionRadius = "100";
		String strCustomer = "Picarro";
		List<String> surTag = new ArrayList<String>();
		surTag.add(PICADMNSTDTAG);
		surTag.add(PICADMNOPTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, PICDFADMIN, strCustomer, TIMEZONEET, exclusionRadius, listBoundary, tablesList, surUnit, surTag, viewList);
		rpt.setViewLayersList(viewLayerList);

		this.getComplianceReportsPage().addNewReport(rpt);
		this.getComplianceReportsPage().waitForPageLoad();

		if ((this.getComplianceReportsPage().checkActionStatus(rptTitle, PICDFADMIN, testCaseID))) {
			this.getComplianceReportsPage().clickOnReportViewerCloseButton();
			if (this.getComplianceReportsPage().validatePdfFiles(rpt, getTestSetup().getDownloadPath())) {
				assertTrue(this.getComplianceReportsPage().findReport(rptTitle, PICDFADMIN));
			} else
				fail("\nTestcase TC192 failed.\n");
		} else
			fail("\nTestcase TC192 failed.\n");
	}

	/**
	 * Test Case ID: TC198 Test Description: Verify "Already Added" message is displayed if user tries to add the same survey again using copy functionality
	 * @throws Exception
	 *
	 */
	@Test
	public void TC198_ComplianceReportTest_VerifyAlreadyAddedMessageforCopy() throws Exception {
		System.out.format("\nRunning TC198: Verify 'Already Added' message is displayed if user tries to add the same survey again using copy functionality\n");

		this.getComplianceReportsPage().login(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
		this.getComplianceReportsPage().open();
		String rptTitle = "TC198 Report" + getTestSetup().getRandomNumber();
		String surUnit = "";

		this.getComplianceReportsPage().addNewPDReport(rptTitle, surUnit, PICADMNSTDTAG);
		this.getComplianceReportsPage().waitForPageLoad();

		assertTrue(this.getComplianceReportsPage().waitForReportGenerationtoComplete(rptTitle, getTestSetup().getLoginUser()));

		this.getComplianceReportsPage().clickOnCopyReport(rptTitle, PICDFADMIN);

		assertTrue(this.getComplianceReportsPage().verifySurveyAlreadyAdded("Picarro", PICADMNSTDTAG));
	}

	/**
	 * Test Case ID: TC1321 Test Description: Generate Compliance Report as Customer Supervisor user and include Percent Coverage Forecast Note: Need 3 standard mode survey tags, Need to change qacus
	 * to include Percentage Forecast
	 * @throws Exception
	 */
	@Test
	public void TC1321_ComplianceReportTest_VerifyMultipleSurveyGaps() throws Exception {
		String testCaseID = "TC1321";
		String rptTitle = testCaseID + " Report" + getTestSetup().getRandomNumber();
		System.out.format("\nRunning " + testCaseID
				+ ": Generate Compliance Report as Customer Supervisor user and include Percent Coverage Forecast, %s\n", rptTitle);

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
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
		tableMap.put(KEYPCA, "1");
		tableMap.put(KEYPCRA, "1");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createViewLayerAssetsContent(assetRowIDs));

		String surUnit = "";
		String exclusionRadius = "0";
		String strCustomer = "Picarro";
		List<String> surTag = new ArrayList<String>();
		surTag.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, getTestSetup().getLoginUser(), strCustomer, TIMEZONEET, exclusionRadius, listBoundary, tablesList, surUnit, surTag, viewList, ReportModeFilter.RapidResponse);
		rpt.setViewLayersList(viewLayerList);

		this.getComplianceReportsPage().addNewReport(rpt);
		this.getComplianceReportsPage().waitForPageLoad();

		if ((this.getComplianceReportsPage().checkActionStatus(rptTitle, getTestSetup().getLoginUser(), testCaseID))) {
			this.getComplianceReportsPage().clickOnReportViewerCloseButton();
			assertTrue(this.getComplianceReportsPage().findReport(rptTitle, getTestSetup().getLoginUser()));
		} else
			fail("\nTestcase TC1321 failed.\n");
	}

	/**
	 * Test Case ID: TC1351 Test Description: Generate Compliance Report as Customer Admin, include Percent Coverage Forecast and 3 surveys with different tags Note: Need 3 standard mode survey tags,
	 * Need to change qacus to include Percentage Forecast
	 * @throws Exception
	 */
	@Test
	public void TC1351_ComplianceReportTest_VerifyMultipleSurveyGaps() throws Exception {
		String testCaseID = "TC1321";
		String rptTitle = testCaseID + " Report" + getTestSetup().getRandomNumber();
		System.out.format("\nRunning " + testCaseID
				+ ": Generate Compliance Report as Customer Admin, include Percent Coverage Forecast and 3 surveys with different tags, %s\n", rptTitle);

		loginPage.open();
		loginPage.loginNormalAs(getTestSetup().getLoginUser(), getTestSetup().getLoginPwd());
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
		tableMap.put(KEYPCA, "1");
		tableMap.put(KEYPCRA, "1");
		tablesList.add(tableMap);

		List<Integer> assetRowIDs = Arrays.asList(8, 9, 10, 11, 12, 13);    // Asset RowIDs from TestCaseData xlsx
		List<Map<String, String>> viewLayerList = new ArrayList<Map<String, String>>();
		viewLayerList.add(ReportDataProvider.createViewLayerAssetsContent(assetRowIDs));

		String surUnit = "";
		String exclusionRadius = "0";
		String strCustomer = "Picarro";
		List<String> surTag = new ArrayList<String>();
		surTag.add(PICADMNSTDTAG);

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, getTestSetup().getLoginUser(), strCustomer, TIMEZONEET, exclusionRadius, listBoundary, tablesList, surUnit, surTag, viewList, ReportModeFilter.RapidResponse);
		rpt.setViewLayersList(viewLayerList);

		this.getComplianceReportsPage().addNewReport(rpt);
		this.getComplianceReportsPage().waitForPageLoad();

		if ((this.getComplianceReportsPage().checkActionStatus(rptTitle, getTestSetup().getLoginUser(), testCaseID))) {
			this.getComplianceReportsPage().clickOnReportViewerCloseButton();
			assertTrue(this.getComplianceReportsPage().findReport(rptTitle, getTestSetup().getLoginUser()));
		} else
			fail("\nTestcase TC1351 failed.\n");
	}
}