package surveyor.regression.source;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static surveyor.scommon.source.SurveyorConstants.KEYINDTB;
import static surveyor.scommon.source.SurveyorConstants.KEYISOANA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCA;
import static surveyor.scommon.source.SurveyorConstants.KEYPCRA;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.CryptoUtility;
import common.source.Log;
import surveyor.dataprovider.ComplianceReportEthaneDataProvider;
import surveyor.dataprovider.ReportDataProvider;
import surveyor.scommon.entities.ComplianceReportEntity;
import surveyor.scommon.entities.ReportsSurveyInfo;
import surveyor.scommon.entities.BaseReportEntity.ReportModeFilter;
import surveyor.scommon.entities.BaseReportEntity.SearchAreaPreference;
import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;
import surveyor.scommon.entities.ReportCommonEntity.EthaneFilter;
import surveyor.scommon.source.BaseReportsPageTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.SurveyorTestRunner;

/**
 * Reports Data provider tests using LatLongSelectionControl.
 *
 */
@RunWith(SurveyorTestRunner.class)
public class LatLongSelectionReportsPageTest2 extends BaseReportsPageTest {

	private static Map<String, String> testCaseMap = Collections.synchronizedMap(new HashMap<String, String>());

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects();

		createTestCaseMap();
	}

	@Before
	public void beforeTest() {
		initializeTestObjects();

		initializePageObjects(new ComplianceReportsPage(getDriver(), getBaseURL(), getTestSetup()));
	}

	private ComplianceReportsPage getComplianceReportsPage() {
		return (ComplianceReportsPage)getReportsPage();
	}

	// Using data provider 12 test cases-generate reports with different report mode and different ethane checkbox selection
	// (TC1638, TC1642, TC1737, TC1642, TC1737 TC1658, TC1710, TC1712, TC1716, TC1714, TC1709, TC1711, TC1715, TC1713)

	@Test
	@UseDataProvider(value = ComplianceReportEthaneDataProvider.COMPLIANCE_ETHANE_REPORT_PROVIDER, location = ComplianceReportEthaneDataProvider.class)
	public void ComplianceReportTest_VerifyEthaneSTDRRReport(String index, String strCreatedBy, String password, String cutomer, String timeZone, String exclusionRadius, String surveyorUnit, String userName, String startDate, String endDate, String fovOpacity, String lisaOpacity, Boolean geoFilter, ReportModeFilter reportMode, SurveyModeFilter surveyModeFilter, EthaneFilter ethaneFilter, List<String> listBoundary, List<String> tagList, List<Map<String, String>> tablesList,
			List<Map<String, String>> viewList, List<Map<String, String>> viewLayersList, SearchAreaPreference srchAreaPref) throws Exception {
		executeVerifyEthaneReportTest(index, strCreatedBy, password, cutomer, timeZone,
				exclusionRadius, surveyorUnit, userName, startDate, endDate,
				fovOpacity, lisaOpacity, geoFilter, reportMode,
				surveyModeFilter, ethaneFilter, listBoundary, tagList,
				tablesList, viewList, viewLayersList, srchAreaPref);
	}

	@Test
	@UseDataProvider(value = ComplianceReportEthaneDataProvider.COMPLIANCE_ETHANE_MANUAL_REPORT_PROVIDER, location = ComplianceReportEthaneDataProvider.class)
	public void ComplianceReportTest_VerifyEthaneManualReport(String index, String strCreatedBy, String password, String cutomer, String timeZone, String exclusionRadius, String surveyorUnit, String userName, String startDate, String endDate, String fovOpacity, String lisaOpacity, Boolean geoFilter, ReportModeFilter reportMode, SurveyModeFilter surveyModeFilter, EthaneFilter ethaneFilter, List<String> listBoundary, List<String> tagList, List<Map<String, String>> tablesList,
			List<Map<String, String>> viewList, List<Map<String, String>> viewLayersList, SearchAreaPreference srchAreaPref) throws Exception {
		executeVerifyEthaneReportTest(index, strCreatedBy, password, cutomer, timeZone,
				exclusionRadius, surveyorUnit, userName, startDate, endDate,
				fovOpacity, lisaOpacity, geoFilter, reportMode,
				surveyModeFilter, ethaneFilter, listBoundary, tagList,
				tablesList, viewList, viewLayersList, srchAreaPref);
	}

	private void executeVerifyEthaneReportTest(String index, String strCreatedBy, String password,
			String cutomer, String timeZone, String exclusionRadius,
			String surveyorUnit, String userName, String startDate,
			String endDate, String fovOpacity, String lisaOpacity,
			Boolean geoFilter, ReportModeFilter reportMode,
			SurveyModeFilter surveyModeFilter, EthaneFilter ethaneFilter,
			List<String> listBoundary, List<String> tagList,
			List<Map<String, String>> tablesList,
			List<Map<String, String>> viewList,
			List<Map<String, String>> viewLayersList,
			SearchAreaPreference srchAreaPref) throws Exception,
			IOException, InterruptedException {
		String rptTitle = null;
		String testCaseName = getTestCaseName(index);

		rptTitle = testCaseName + " " + "Report" + getTestSetup().getRandomNumber();

		Log.info("\nRunning " + testCaseName + " - " + rptTitle);

		this.getComplianceReportsPage().login(strCreatedBy, new CryptoUtility().decrypt(password));
		this.getComplianceReportsPage().open();

		ComplianceReportEntity rpt = new ComplianceReportEntity(rptTitle, strCreatedBy, cutomer, timeZone, exclusionRadius, surveyorUnit, userName, startDate, endDate, fovOpacity, lisaOpacity, geoFilter, reportMode, surveyModeFilter, ethaneFilter, listBoundary, tagList, tablesList, viewList, viewLayersList);
		if(!reportMode.equals(ReportModeFilter.Manual) ){
			List<ReportsSurveyInfo> reportSurveyInfoList = ReportDataProvider.buildReportSurveyInfoList("36");
			rpt.setSurveyInfoList(reportSurveyInfoList);
		}

		rpt.setCustomerBoundaryInfo(ComplianceReportEntity.CustomerBoundaryFilterType.SmallBoundary, "TESTPlat-Auto-1.5km");
		rpt.setSearchAreaPreference(srchAreaPref);

		this.getComplianceReportsPage().addNewReport(rpt);
		this.getComplianceReportsPage().waitForPageLoad();

		if ((this.getComplianceReportsPage().checkActionStatus(rptTitle, strCreatedBy, testCaseName))) {
			assertTrue(this.getComplianceReportsPage().validatePdfFiles(rpt, getTestSetup().getDownloadPath()));
			assertTrue(this.getComplianceReportsPage().verifyReportStaticText(rpt));
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
					assertTrue(this.getComplianceReportsPage().verifyEthaneAnalysisTable(getTestSetup().getDownloadPath(), rptTitle));
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
		testCaseMap.put("1", "TC1638"); // std--exclude vehicle exhaust
		testCaseMap.put("2", "TC1642"); // std--exclude biogenic methane
		testCaseMap.put("3", "TC1737"); // std--both
		testCaseMap.put("4", "TC1658"); // std--none
		testCaseMap.put("5", "TC1710"); // manual--exclude vehicle exhaust
		testCaseMap.put("6", "TC1712"); // manual--exclude biogenic methane
		testCaseMap.put("7", "TC1716"); // manual--both
		testCaseMap.put("8", "TC1714"); // manual--none
		testCaseMap.put("9", "TC1709"); // rapid--exclude vehicle exhaust
		testCaseMap.put("10", "TC1711"); // rapid--exclude biogenic methane
		testCaseMap.put("11", "TC1715"); // rapid--both
		testCaseMap.put("12", "TC1713"); // rapid--none
	}
}