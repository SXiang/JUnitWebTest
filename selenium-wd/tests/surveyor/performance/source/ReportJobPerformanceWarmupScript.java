package surveyor.performance.source;

import org.junit.Test;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.Log;
import surveyor.dataprovider.PerformanceReportJobDataProvider;

public class ReportJobPerformanceWarmupScript extends BaseReportJobPerformanceTest {

	/**
	 * Test Case ID: ReportJob_PerformanceTest
	 * This is a warmup script for report job perf tests which runs prior to actual perf test metric collection test execution.
	 * Script: -
	 *  - This script creates a report, waits for completion and ignores any perf metrics from this report.
	 */
	@Test
	@UseDataProvider(value = PerformanceReportJobDataProvider.REPORT_JOB_PERFORMANCE_PROVIDER_WARMUP_SCRIPT, location = PerformanceReportJobDataProvider.class)
	public void ReportJob_PerformanceTest(String rallyTestCaseID, Integer userDataRowID, Integer reportDataRowID,
			Integer executionTimesForBaselines, String category) throws Exception {
		Log.info("\nRunning Performance Warmup Script ...");

		initializePageActions();
		createAndWaitForReportGeneration(userDataRowID, reportDataRowID);;
	}
}
