package surveyor.scommon.source;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.support.PageFactory;

import common.source.ExceptionUtility;
import common.source.FileUtility;
import common.source.Log;
import common.source.LogHelper;
import common.source.NumberUtility;
import common.source.TestContext;
import surveyor.dataaccess.source.Report;
import surveyor.scommon.entities.BaseReportEntity.ReportJobType;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.BaseReportsPageActionTest.ReportTestRunMode;

public class BaseReportsPageTest extends SurveyorBaseTest {

	protected static final Integer REPORT_GENERATION_TIMEOUT_1HR_IN_SECONDS = 3600;    // Timeout= 1hr for report gen.
	protected static final Integer REPORT_GENERATION_TIMEOUT_30MIN_IN_SECONDS = 1800;  // Timeout= 30 mins for report gen.

	private static ThreadLocal<ReportsBasePage> reportsPageThreadLocal = new ThreadLocal<ReportsBasePage>();

	private boolean isCollectReportJobPerfMetric;
	private boolean isGenerateBaselineSSRSImages;
	private boolean isGenerateBaselineViewImages;
	private boolean isGenerateBaselineShapeFiles;

	private static Map<ReportJobType, NumberUtility> reportJobProcessingTimeNumberMap;
	private static ReportTestRunMode testRunMode = ReportTestRunMode.FullTestRun;

	protected static void initializePageObjects(ReportsBasePage reportsBasePage) {
		setReportsPage(reportsBasePage);
		PageFactory.initElements(getDriver(), reportsBasePage);
	}

	public BaseReportsPageTest() {
		this.isCollectReportJobPerfMetric = TestContext.INSTANCE.getTestSetup().isCollectReportJobPerfMetric();
		this.isGenerateBaselineSSRSImages = TestContext.INSTANCE.getTestSetup().isGenerateBaselineSSRSImages();
		this.isGenerateBaselineViewImages = TestContext.INSTANCE.getTestSetup().isGenerateBaselineViewImages();
		this.isGenerateBaselineShapeFiles = TestContext.INSTANCE.getTestSetup().isGenerateBaselineShapeFiles();
		initializeProperties();
	}

	public static void initializeProperties() {
		reportJobProcessingTimeNumberMap = Collections.synchronizedMap(new HashMap<ReportJobType, NumberUtility>());
		reportJobProcessingTimeNumberMap.put(ReportJobType.DataGeneration, new NumberUtility());
		reportJobProcessingTimeNumberMap.put(ReportJobType.EQDataGeneration, new NumberUtility());
		reportJobProcessingTimeNumberMap.put(ReportJobType.EQMap, new NumberUtility());
		reportJobProcessingTimeNumberMap.put(ReportJobType.EQSSRS, new NumberUtility());
		reportJobProcessingTimeNumberMap.put(ReportJobType.Map, new NumberUtility());
		reportJobProcessingTimeNumberMap.put(ReportJobType.PercentCoverageForecast, new NumberUtility());
		reportJobProcessingTimeNumberMap.put(ReportJobType.ReportMeta, new NumberUtility());
		reportJobProcessingTimeNumberMap.put(ReportJobType.ShapeFile, new NumberUtility());
		reportJobProcessingTimeNumberMap.put(ReportJobType.SSRS, new NumberUtility());
		reportJobProcessingTimeNumberMap.put(ReportJobType.Zip, new NumberUtility());
		reportJobProcessingTimeNumberMap.put(ReportJobType.AssetBoxHighlight, new NumberUtility());
	}

	public static Integer getReportJobRollingProcessingTimeAvg(ReportJobType reportJobType) {
		NumberUtility numberUtility = reportJobProcessingTimeNumberMap.get(reportJobType);
		return numberUtility.getMovingAverage();
	}

	public static void setRollingReportJobProcessingTime(ReportJobType reportJobType, Integer reportJobProcessingTime) {
		// Add number to NumberUtility of the job type.
		NumberUtility numberUtility = reportJobProcessingTimeNumberMap.get(reportJobType);
		numberUtility.addRollingNumber(reportJobProcessingTime);
		reportJobProcessingTimeNumberMap.put(reportJobType, numberUtility);
	}

	protected static Map<ReportJobType, NumberUtility> getReportJobProcessingTimeNumberMap() {
		return reportJobProcessingTimeNumberMap;
	}

	public static void setReportsPage(ReportsBasePage reportsPage) {
		reportsPageThreadLocal.set(reportsPage);
	}

	@Override
	public void postTestMethodProcessing() {
		Log.method("BaseReportsPageTest.postTestMethodProcessing");
		try {
			cleanUp();
			if (getReportsPage() != null) {
				getReportsPage().logout();
			}
		} catch (Exception e) {
			Log.warn(String.format("Exception in BaseReportsPageTest.postTestMethodProcessing(). Exception message: %s",
					ExceptionUtility.getStackTraceString(e)));
		}
	}

	public static ReportsBasePage getReportsPage() {
		return reportsPageThreadLocal.get();
	}

	protected static ReportTestRunMode getTestRunMode() {
		return testRunMode;
	}

	protected static void setTestRunMode(ReportTestRunMode testRunModeValue) {
		testRunMode = testRunModeValue;
	}

	private void cleanUp() throws Exception {
		Log.method("BaseReportsPageTest.cleanUp");
		if(getReportsPage()==null||keepTestData()){
			Log.info("Clearing test report set and RETURNING ..");
			TestContext.INSTANCE.clearTestReportSet();
			return;
		}

		//Delete report and related downloads
		String downloadDirectory = TestContext.INSTANCE.getTestSetup().getDownloadPath();
		Set<String> reportIdSet = TestContext.INSTANCE.getTestReportIdSet();

		getReportsPage().open();

		//Synchronize iteration on the backed set.
		synchronized(reportIdSet) {
			Iterator<String> setIteration = reportIdSet.iterator();
			while (setIteration.hasNext()) {
				String reportId = setIteration.next();
				String reportName = "CR-" + reportId.substring(0,6).toUpperCase();
				FileUtility.deleteFilesAndSubFoldersInDirectory(downloadDirectory, reportName);
				getReportsPage().deleteReportWithApiCall(reportId);
			}
		}

		getReportsPage().open();

		Log.info("Clearing test report set ..");
		TestContext.INSTANCE.clearTestReportSet();
	}

	private boolean keepTestData() {
		Log.method("BaseReportsPageTest.keepTestData");
		if(getTestRunMode() != ReportTestRunMode.FullTestRun){
			Log.info("NOT FullTestRun -> [TRUE]");
			return true;
		}
		int testCleanUpMode = TestContext.INSTANCE.getTestSetup().getTestCleanUpMode();
		if(testCleanUpMode == 2){//# 2: keep all the test data in place
			Log.info("TestCleanupMode=2 -> [TRUE]");
			return true;
		}else if(testCleanUpMode == 0){//# 0: clean up test data after each test
			Log.info("TestCleanupMode=0 -> [FALSE]");
			return false;
		}else if(testCleanUpMode == 1){//# 1: clean up test data if test passed
			String testStatus = TestContext.INSTANCE.getTestStatus();
			boolean retVal = testStatus.equalsIgnoreCase("FAIL");
			Log.info(String.format("testStatus.equalsIgnoreCase(FAIL)==%b -> [%b]", retVal, retVal));
			return retVal;
		}
		return false;
	}
}