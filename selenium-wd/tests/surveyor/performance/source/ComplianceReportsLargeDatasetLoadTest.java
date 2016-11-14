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

import org.junit.Before;
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

	protected static final Integer LOAD_REPORT_GENERATION_TIMEOUT_IN_SECONDS = 3600;  // Max timeout= 1hr for report gen.

	@BeforeClass
	public static void beforeClass() {
		initializeTestObjects();
		initializePageObjects();
		createTestCaseMap();

		// Ensure surveys required for the test cases are present in DB.
		ensureTestSurveysArePresent();
	}

	@Before
	public void beforeTest() {
		initializeTestObjects();
		initializePageObjects();
	}

	private static void ensureTestSurveysArePresent() {
		final String[] surveyDataFiles = {PICGREATER4HR_DATAFILE, PICLESS4HR_DATAFILE
				,PIC8HR01_DATAFILE, PIC8HR02_DATAFILE, PIC8HR03_DATAFILE, PIC8HR04_DATAFILE, PIC8HR05_DATAFILE, PIC8HR06_DATAFILE
				,PIC8HR07_DATAFILE, PIC8HR08_DATAFILE, PIC8HR09_DATAFILE, PIC8HR10_DATAFILE, PIC8HR11_DATAFILE, PIC8HR12_DATAFILE};
		try {
			DbSeedExecutor.executeSurveyDataSeed(surveyDataFiles);
		} catch (Exception e) {
			Log.error(ExceptionUtility.getStackTraceString(e));
		}
	}

	private static void initializePageObjects() {
		initializePageObjects(new ComplianceReportsPage(getDriver(), getBaseURL(), getTestSetup()));
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
		String rptTitle = testCaseName + " " + "Report" + getTestSetup().getRandomNumber();
		Log.info("\nRunning " + testCaseName + " - " + rptTitle);

		this.getComplianceReportsPage().login(strCreatedBy, new CryptoUtility().decrypt(password));
		this.getComplianceReportsPage().open();

		ReportsCompliance rpt = new ReportsCompliance(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, surveyorUnit,
				userName, startDate, endDate, fovOpacity, lisaOpacity, geoFilter, reportMode, surveyModeFilter, ethaneFilter,
				listBoundary, tagList, tablesList, viewList, viewLayersList);

		this.getComplianceReportsPage().addNewReport(rpt);
		this.getComplianceReportsPage().waitForPageLoad();
		this.getComplianceReportsPage().setReportGenerationTimeout(LOAD_REPORT_GENERATION_TIMEOUT_IN_SECONDS);

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