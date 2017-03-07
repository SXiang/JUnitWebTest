package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.CUSDRVETHSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPHEIGHT;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPWIDTH;
import static surveyor.scommon.source.SurveyorConstants.KEYGAPTB;
import static surveyor.scommon.source.SurveyorConstants.KEYINDTB;
import static surveyor.scommon.source.SurveyorConstants.KEYISOANA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCF;
import static surveyor.scommon.source.SurveyorConstants.KEYPCRA;
import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG;
import static surveyor.scommon.source.SurveyorConstants.RNELAT;
import static surveyor.scommon.source.SurveyorConstants.RNELON;
import static surveyor.scommon.source.SurveyorConstants.RSWLAT;
import static surveyor.scommon.source.SurveyorConstants.RSWLON;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEMT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.Log;
import surveyor.dataaccess.source.ResourceKeys;
import surveyor.dataaccess.source.Resources;
import surveyor.dataprovider.AssessmentReportDataProvider;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.dataprovider.ComplianceReportEthaneDataProvider;
import surveyor.dataprovider.ReportDataProvider;
import surveyor.scommon.actions.AssessmentReportsPageActions;
import surveyor.scommon.actions.ComplianceReportsPageActions;
import surveyor.scommon.actions.LoginPageActions;
import surveyor.scommon.entities.ComplianceReportEntity;
import surveyor.scommon.entities.BaseReportEntity.ReportModeFilter;
import surveyor.scommon.entities.BaseReportEntity.SearchAreaPreference;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.scommon.entities.ReportCommonEntity.EthaneFilter;
import surveyor.scommon.source.AssessmentReportsPage;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class LatLongSelectionReportsPageTest extends BaseReportsPageActionTest {

	private static final String EMPTY = "";
	private static final Integer NOTSET = -1;

	private static LoginPageActions loginPageAction;
	private static AssessmentReportsPageActions assessmentReportsPageAction;
	private static ComplianceReportsPageActions complianceReportsPageAction;

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects();
	}

	@Before
	public void beforeTest() throws Exception {
		initializeTestObjects();

		initializePageActions();
	}

	private ComplianceReportsPage getComplianceReportsPage() {
		return (ComplianceReportsPage)getReportsPage();
	}

	private void initializeComplianceReportTest() throws Exception {
		initializeComplianceReportPageActions();
		setPropertiesForComplianceTestRunMode();
	}

	private void initializeAssessmentReportTest() throws Exception {
		initializeAssessmentReportPageActions();
		setPropertiesForAssessmentTestRunMode();
	}

	private static void setPropertiesForComplianceTestRunMode() throws Exception {
		setTestRunMode(ReportTestRunMode.FullTestRun);

		if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			complianceReportsPageAction.fillWorkingDataForReports(getUnitTestReportRowID());
		}
	}

	private static void setPropertiesForAssessmentTestRunMode() throws Exception {
		setTestRunMode(ReportTestRunMode.FullTestRun);

		if (getTestRunMode() == ReportTestRunMode.UnitTestRun) {
			assessmentReportsPageAction.fillWorkingDataForReports(getUnitTestReportRowID());
		}
	}

	/**
	 * Initializes the page action objects for Assessment reports.
	 * @throws Exception
	 */
	protected static void initializeAssessmentReportPageActions() throws Exception {
		assessmentReportsPageAction = new AssessmentReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
		setReportsPage((AssessmentReportsPage)assessmentReportsPageAction.getPageObject());
	}

	/**
	 * Initializes the page action objects for Assessment reports.
	 * @throws Exception
	 */
	protected static void initializeComplianceReportPageActions() throws Exception {
		complianceReportsPageAction = new ComplianceReportsPageActions(getDriver(), getBaseURL(), getTestSetup());
		setReportsPage((ComplianceReportsPage)complianceReportsPageAction.getPageObject());
	}

	private static void initializePageActions() {
		loginPageAction = new LoginPageActions(getDriver(), getBaseURL(), getTestSetup());
	}

	/**
	 * Test Case ID: TC1484_SearchValidCustomerBoundaryBoundarySelectorScreenOfAssessmentReport
	 * Test Description: Search valid customer boundary on boundary selector screen of Assessment report
	 * Script: -
	 *	- - On Home Page, click Reports -&Assessment-& 'NewAssessmentReport' button
	 *	- - Select 'Customer Boundary' and click on Boundary Selector button
	 *	- - Search valid boundary using Select By Boundary Name
	 *	- Eg. CNP customer - in Dallas area search 455
	 *	- eg. PG&amp;E customer - search boundary as 52-
	 * Results: -
	 *	-
	 *	- - Customer Boundaries are present on map
	 *	- - Boundaries having exact same name or similar to it are searched and displayed to the user in alphabetical list
	 *	- Eg. All boundaries in map area having 455 should be searched
	 *	- eg. All boundaries in map area having *52- * should be searched
	 */
	@Test
	@UseDataProvider(value = AssessmentReportDataProvider.ASSESSMENT_REPORT_PAGE_ACTION_DATA_PROVIDER_TC1484, location = AssessmentReportDataProvider.class)
	public void TC1484_SearchValidCustomerBoundaryBoundarySelectorScreenOfAssessmentReport(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC1484_SearchValidCustomerBoundaryBoundarySelectorScreenOfAssessmentReport ...");

		initializeAssessmentReportTest();

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));   /* Picarro Admin */

		assessmentReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		assessmentReportsPageAction.clickOnNewReportButton(EMPTY, reportDataRowID1);
		assessmentReportsPageAction.verifyNewPageLoaded(EMPTY, reportDataRowID1);
		assertTrue(assessmentReportsPageAction.fillAndVerifyCustomerBoundaryNamesListInAreaSelectorIsCorrect(EMPTY, getReportRowID(reportDataRowID1)));
	}

	/**
	 * Test Case ID: TC238_SearchInvalidCustomerBoundaryBoundarySelectorScreen
	 * Test Description: Search invalid customer boundary on boundary selector screen
	 * Script: -
	 *	- - On Home Page, click Reports -& Compliance -& 'New Compliance Report' button
	 *	- - Select 'Customer Boundary' and click on Boundary Selector button
	 *	- - Search invalid boundary
	 * Results: -
	 *	- - Customer Boundaries are present on map
	 *	- - Invalid boundary name is not present and user is notified that no such boundary exists
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_PAGE_ACTION_DATA_PROVIDER_TC238, location = ComplianceReportDataProvider.class)
	public void TC238_SearchInvalidCustomerBoundaryBoundarySelectorScreen(
			String testCaseID, Integer userDataRowID, Integer reportDataRowID1, Integer reportDataRowID2) throws Exception {
		Log.info("\nRunning TC238_SearchInvalidCustomerBoundaryBoundarySelectorScreen ...");

		initializeComplianceReportTest();

		loginPageAction.open(EMPTY, getUserRowID(userDataRowID));
		loginPageAction.login(EMPTY, getUserRowID(userDataRowID));
		complianceReportsPageAction.open(EMPTY, getReportRowID(reportDataRowID1));
		complianceReportsPageAction.clickOnNewReportButton(EMPTY, getReportRowID(reportDataRowID1));
		boolean retVal = false;
		try{
			// NOTE: With invalid entry enterCustomerBoundaryUsingAreaSelector() return true only if 'invalid results' entry was found.
			retVal = complianceReportsPageAction.enterCustomerBoundaryUsingAreaSelector(EMPTY, getReportRowID(reportDataRowID1));
		}catch(Exception e){
			Log.warn("Exception when selecting an invalid customer boundary name: "+e);
		}
		assertTrue(retVal == true);
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

		initializeComplianceReportTest();

		loginPageAction.open(EMPTY, NOTSET);
		loginPageAction.login(EMPTY, 6);

		String testCaseID = "TC1318";
		String rptTitle = testCaseID + " Report" + getTestSetup().getRandomNumber();
		String rptTitle2 = testCaseID + " Report" + getTestSetup().getRandomNumber();
		String rptTitle3 = testCaseID + " Report" + getTestSetup().getRandomNumber();

		this.getComplianceReportsPage().open();

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
		viewList1.add(ReportDataProvider.createViewsMapTable("First View", "0", "1", "1", "1", "1", "1", "1", "1", "1", Resources.getResource(ResourceKeys.Constant_Map)));
		viewList3.add(ReportDataProvider.createViewsMapTable("First View", "0", "1", "1", "1", "1", "1", "1", "0", "0", Resources.getResource(ResourceKeys.Constant_Map)));

		List<String> tagList = new ArrayList<String>();
		tagList.add(PICADMNSTDTAG);
		tagList.add(CUSDRVETHSTDTAG);

		ComplianceReportEntity rpt = new ComplianceReportEntity(rptTitle, getTestSetup().getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		rpt.setViewLayersList(viewLayerList);

		rpt.setCustomerBoundaryInfo(ComplianceReportEntity.CustomerBoundaryFilterType.SmallBoundary, "TESTPlat-Auto-1.5km");
		this.getComplianceReportsPage().addNewReport(rpt);

		this.getComplianceReportsPage().waitForPageLoad();

		if ((this.getComplianceReportsPage().checkActionStatus(rptTitle, getTestSetup().getLoginUser(), testCaseID))) {
			assertTrue(this.getComplianceReportsPage().validatePdfFiles(rpt, getTestSetup().getDownloadPath()));
			if (tablesList != null) {
				if ((tablesList.get(0).get(KEYPCF).equals("1"))) {
					assertTrue(this.getComplianceReportsPage().verifyShowCoverageTable(getTestSetup().getDownloadPath(), rptTitle));
				}
			}
		}
		else
			fail("\nTestcase TC1318 failed.\n");

		this.getComplianceReportsPage().open();
		ComplianceReportEntity rpt2 = new ComplianceReportEntity(rptTitle2, getTestSetup().getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList1, SurveyModeFilter.Standard);
		rpt2.setViewLayersList(viewLayerList2);
		rpt2.setCustomerBoundaryInfo(ComplianceReportEntity.CustomerBoundaryFilterType.SmallBoundary, "TESTPlat-Auto-1.5km");
		this.getComplianceReportsPage().addNewReport(rpt2);
		this.getComplianceReportsPage().waitForPageLoad();

		if ((this.getComplianceReportsPage().checkActionStatus(rptTitle2, getTestSetup().getLoginUser(), testCaseID+"_2"))) {
			assertTrue(this.getComplianceReportsPage().validatePdfFiles(rpt2, getTestSetup().getDownloadPath()));
			if (tablesList != null) {
				if ((tablesList.get(0).get(KEYPCF).equals("1"))) {
					assertTrue(this.getComplianceReportsPage().verifyShowCoverageTable(getTestSetup().getDownloadPath(), rptTitle2));
				}
			}
		}
		else
			fail("\nTestcase TC1318 failed.\n");

		this.getComplianceReportsPage().open();
		ComplianceReportEntity rpt3 = new ComplianceReportEntity(rptTitle3, getTestSetup().getLoginUser(), "Picarro", TIMEZONEMT, "0", listBoundary, tablesList, "", tagList, "", "", viewList3, SurveyModeFilter.Standard);
		rpt3.setCustomerBoundaryInfo(ComplianceReportEntity.CustomerBoundaryFilterType.SmallBoundary, "TESTPlat-Auto-1.5km");
		this.getComplianceReportsPage().addNewReport(rpt3);
		this.getComplianceReportsPage().waitForPageLoad();

		if ((this.getComplianceReportsPage().checkActionStatus(rptTitle3, getTestSetup().getLoginUser(), testCaseID+"_3"))) {
			assertTrue(this.getComplianceReportsPage().validatePdfFiles(rpt3, getTestSetup().getDownloadPath()));
			if (tablesList != null) {
				if ((tablesList.get(0).get(KEYPCF).equals("1"))) {
					assertTrue(this.getComplianceReportsPage().verifyShowCoverageTable(getTestSetup().getDownloadPath(), rptTitle3));
				}
			}
		}
		else
			fail("\nTestcase TC1318 failed.\n");

	}
}