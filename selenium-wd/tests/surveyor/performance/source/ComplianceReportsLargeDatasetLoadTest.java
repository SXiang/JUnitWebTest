package surveyor.performance.source;

import static org.junit.Assert.*;
import static surveyor.scommon.source.SurveyorConstants.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.source.CryptoUtility;
import common.source.ExceptionUtility;
import common.source.Log;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.Test;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import surveyor.scommon.source.SurveyorTestRunner;
import surveyor.scommon.source.Reports.ReportModeFilter;
import surveyor.scommon.source.Reports.SurveyModeFilter;
import surveyor.scommon.source.ReportsCompliance.EthaneFilter;
import surveyor.scommon.source.BaseReportsPageActionTest;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.ReportsCompliance;
import surveyor.dataprovider.ComplianceReportDataProvider;
import surveyor.dbseed.source.DbSeedExecutor;

@RunWith(SurveyorTestRunner.class)
public class ComplianceReportsLargeDatasetLoadTest extends BaseReportsPageActionTest {

	private static HashMap<String, String> testCaseMap = new HashMap<String, String>();

	@BeforeClass
	public static void setupComplianceReportsPageTest() {
		initializePageObjects();
		createTestCaseMap();

		// Ensure surveys required for the test cases are present in DB.
		ensureTestSurveysArePresent();
	}

	private static void ensureTestSurveysArePresent() {
		final String[] surveyTags = {PICGREATER4HRTAG, PICLESS4HRTAG
				,PIC8HR01TAG, PIC8HR02TAG, PIC8HR03TAG, PIC8HR04TAG, PIC8HR05TAG, PIC8HR06TAG
				,PIC8HR07TAG, PIC8HR08TAG, PIC8HR09TAG, PIC8HR10TAG, PIC8HR11TAG, PIC8HR12TAG};
		try {
			DbSeedExecutor.executeSurveyDataSeed(surveyTags);
		} catch (Exception e) {
			Log.error(ExceptionUtility.getStackTraceString(e));
		}
	}

	private static void initializePageObjects() {
		initializePageObjects(new ComplianceReportsPage(driver, baseURL, testSetup));
	}

	private ComplianceReportsPage getComplianceReportsPage() {
		return (ComplianceReportsPage)getReportsPage();
	}

	/**
	 * Compliance Report Large DataSet Load Test Cases:-
	 * 1. TC249 - Generating the report from long run (8 hour survey)
	 * 2. TC250 - Generating the report from multiple runs (100 hour surveys)
	 *
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws Exception
	 */
	@Test
	@UseDataProvider(value = ComplianceReportDataProvider.COMPLIANCE_REPORT_LOAD_TESTS_PROVIDER, location = ComplianceReportDataProvider.class)
	public void ComplianceReportsLargeDataSet_LoadTests(String index, String strCreatedBy, String password, String customer,
			String timeZone, String exclusionRadius, String surveyorUnit, String userName, String startDate, String endDate,
			String fovOpacity, String lisaOpacity, Boolean geoFilter, ReportModeFilter reportMode, SurveyModeFilter surveyModeFilter,
			EthaneFilter ethaneFilter, List<String> listBoundary, List<String> tagList, List<Map<String, String>> tablesList,
			List<Map<String, String>> viewList, List<Map<String, String>> viewLayersList) throws Exception {
		String testCaseName = getTestCaseName(index);
		String rptTitle = testCaseName + " " + "Report" + testSetup.getRandomNumber();
		Log.info("\nRunning " + testCaseName + " - " + rptTitle);

		this.getComplianceReportsPage().login(strCreatedBy, CryptoUtility.decrypt(password));
		this.getComplianceReportsPage().open();

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, surveyorUnit,
				userName, startDate, endDate, fovOpacity, lisaOpacity, geoFilter, reportMode, surveyModeFilter, ethaneFilter,
				listBoundary, tagList, tablesList, viewList, viewLayersList);

		this.getComplianceReportsPage().addNewReport(rpt);
		this.getComplianceReportsPage().waitForPageLoad();

		if ((this.getComplianceReportsPage().checkActionStatus(rptTitle, strCreatedBy, testCaseName))) {
			this.getComplianceReportsPage().clickOnReportViewerCloseButton();
		} else
			fail("\nTestcase " + getTestCaseName(index) + " failed.\n");
	}

	private static String getTestCaseName(String key) {
		return testCaseMap.get(key);
	}

	private static void createTestCaseMap() {
		testCaseMap.put("17", "TC249");
		testCaseMap.put("19", "TC250");
	}
}