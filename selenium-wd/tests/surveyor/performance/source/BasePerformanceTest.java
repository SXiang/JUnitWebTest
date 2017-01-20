package surveyor.performance.source;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import org.junit.After;
import common.source.FileUtility;
import common.source.Log;
import common.source.NumberUtility;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.scommon.entities.BaseReportEntity.ReportJobType;
import surveyor.scommon.source.BaseReportsPageTest;

public class BasePerformanceTest extends BaseReportsPageTest {

	public BasePerformanceTest() {
	}
	
	@After
	public void afterTestMethod() {
		if (TestContext.INSTANCE.getTestSetup().isCollectReportJobPerfMetric()) {
			logReportJobBaselineMetrics();
		}
	}

	protected void generateReportJobBaselineRunExecutionCsv(String testCaseID) throws IOException {
		Log.method("generateReportJobBaselineRunExecutionCsv", testCaseID);

		Log.info(String.format("Generating baseline run execution csv for : [TestCase=%s]", testCaseID));

		String rootFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "data";
		String expectedDataFolderPath = rootFolder + File.separator + "perf-metric" + File.separator
				+ "report-job-metrics" + File.separator + testCaseID;
		// Create the directory for test case if it does not exist.
		FileUtility.createDirectoryIfNotExists(expectedDataFolderPath);
		Path expectedFilePath = Paths.get(expectedDataFolderPath, "baseline-run-data.csv");

		StringBuilder fileContent = new StringBuilder();
		Map<ReportJobType, NumberUtility> timeNumberMap = getReportJobProcessingTimeNumberMap();
		for(Map.Entry<ReportJobType, NumberUtility> entry : timeNumberMap.entrySet()){
			NumberUtility numberUtility = entry.getValue();
			List<Integer> rollingNumbers = numberUtility.getRollingNumbers();
			int size = rollingNumbers.size();
			if (size > 0) {
				fileContent.append(String.format("%s,%s", entry.getKey().toString(), rollingNumbers.get(0)));
				fileContent.append(System.lineSeparator());
			}
			if (size > 1) {
				for (int i = 1; i < size; i++) {
					fileContent.append(String.format("%s,%s", entry.getKey().toString(), rollingNumbers.get(i)));
					fileContent.append(System.lineSeparator());
				}
			}
		}
		
		FileUtility.createTextFile(expectedFilePath, fileContent.toString());
	}
	
	private void logReportJobBaselineMetrics() {
		Log.info("----------------------------------------------------------------");
		Log.info("Baseline Collection Metrics");
		Log.info("Report Job Type | Processing Times (in msec) for various runs");
		Map<ReportJobType, NumberUtility> timeNumberMap = getReportJobProcessingTimeNumberMap();
		for(Map.Entry<ReportJobType, NumberUtility> entry : timeNumberMap.entrySet()){
			NumberUtility numberUtility = entry.getValue();
			List<Integer> rollingNumbers = numberUtility.getRollingNumbers();
			StringBuilder builder = new StringBuilder();
			int size = rollingNumbers.size();
			if (size > 0) {
				builder.append(rollingNumbers.get(0));
			}
			if (size > 1) {
				for (int i = 1; i < size; i++) {
					builder.append("," + rollingNumbers.get(i));
				}
			}
			Log.info(entry.getKey().toString() + " | " + builder.toString());
		}
		Log.info("----------------------------------------------------------------");
	}
}
