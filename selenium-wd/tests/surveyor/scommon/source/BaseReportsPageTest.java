package surveyor.scommon.source;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.openqa.selenium.support.PageFactory;

import common.source.ExceptionUtility;
import common.source.FileUtility;
import common.source.Log;
import common.source.NumberUtility;
import common.source.TestContext;
import surveyor.scommon.source.Reports.ReportJobType;
import surveyor.scommon.source.SurveyorBaseTest;
import surveyor.scommon.source.BaseReportsPageActionTest.ReportTestRunMode;

public class BaseReportsPageTest extends SurveyorBaseTest {

	private static ReportsBasePage reportsPage = null;

	private boolean isCollectReportJobPerfMetric;
	private boolean isGenerateBaselineSSRSImages;
	private boolean isGenerateBaselineViewImages;
	private boolean isGenerateBaselineShapeFiles;
	
	private static HashMap<ReportJobType, NumberUtility> reportJobProcessingTimeNumberMap;
	private static ReportTestRunMode testRunMode = ReportTestRunMode.FullTestRun;
	
	protected static void initializePageObjects(ReportsBasePage reportsBasePage) {
		setReportsPage(reportsBasePage);
		PageFactory.initElements(driver, getReportsPage());
	}

	public BaseReportsPageTest() {
		this.isCollectReportJobPerfMetric = TestContext.INSTANCE.getTestSetup().isCollectReportJobPerfMetric();
		this.isGenerateBaselineSSRSImages = TestContext.INSTANCE.getTestSetup().isGenerateBaselineSSRSImages();
		this.isGenerateBaselineViewImages = TestContext.INSTANCE.getTestSetup().isGenerateBaselineViewImages();
		this.isGenerateBaselineShapeFiles = TestContext.INSTANCE.getTestSetup().isGenerateBaselineShapeFiles();
		initializeProperties();
	}

	public static void initializeProperties() {
		reportJobProcessingTimeNumberMap = new HashMap<ReportJobType, NumberUtility>();
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

	protected static HashMap<ReportJobType, NumberUtility> getReportJobProcessingTimeNumberMap() {
		return reportJobProcessingTimeNumberMap;
	}

	public static ReportsBasePage getReportsPage() {
		if(reportsPage==null){
			reportsPage= new ReportsBasePage(driver, baseURL, testSetup, "/Reports/ComplianceReports");
			PageFactory.initElements(driver, reportsPage);
		}
		return reportsPage;
	}

	public static void setReportsPage(ReportsBasePage reportsPage) {
		BaseReportsPageTest.reportsPage = reportsPage;
	}
		
	@After
	public void afterTestMethod() {
		try {
			reportsPage = getReportsPage();
			reportsPage.open();
			cleanUp();
			reportsPage.logout();
		} catch (Exception e) {
			Log.warn("Exception in BaseReportsPageActionTest.afterTestMethod(). Exception message:");
			Log.warn(ExceptionUtility.getStackTraceString(e));
		}
	}

	protected static ReportTestRunMode getTestRunMode() {
		return testRunMode;
	}

	protected static void setTestRunMode(ReportTestRunMode testRunModeValue) {
		testRunMode = testRunModeValue;
	}
	
	private void cleanUp() throws Exception {
		if(keepTestData()){
			return;
		}
		Set<String> reportNameSet = TestContext.INSTANCE.getTestReportNameSet();
		String downloadDirectory = TestContext.INSTANCE.getTestSetup().getDownloadPath();
		//Delete report and related downloads
		for(String reportName:reportNameSet){			
			reportsPage.deleteReportByName(reportName);
			FileUtility.deleteFilesAndSubFoldersInDirectory(downloadDirectory, reportName);
		}
		TestContext.INSTANCE.clearTestReportSet();
	}	
	
	private boolean keepTestData(){
		if(getTestRunMode() != ReportTestRunMode.FullTestRun){
			return true;
		}
		int testCleanUpMode = TestContext.INSTANCE.getTestSetup().getTestCleanUpMode();
		if(testCleanUpMode == 2){//# 2: keep all the test data in place
			return true;
		}else if(testCleanUpMode == 0){//# 0: clean up test data after each test			
			return false;
		}else if(testCleanUpMode == 1){//# 1: clean up test data if test passed
			String testStatus = TestContext.INSTANCE.getTestStatus();
			return testStatus.equalsIgnoreCase("FAIL");
		}
		return false;
	}
}
