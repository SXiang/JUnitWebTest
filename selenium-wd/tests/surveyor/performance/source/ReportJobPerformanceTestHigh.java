package surveyor.performance.source;

import org.junit.Test;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.Log;
import surveyor.dataprovider.PerformanceReportJobDataProvider;

public class ReportJobPerformanceTestHigh extends BaseReportJobPerformanceTest {

	/**
	 * Test Case ID: ReportJob_PerformanceTest
	 * Script: -
	 *	- - Login as specified user
	 *	- - Create New Compliance Report with specified report data
	 *  - - Wait for Report to be generated
	 *  - - Query DB to find processing time for each report job
	 * Results: -
	 *	- - Verify report job processing time values confirm to the baseline values.
	 */
	@Test
	@UseDataProvider(value = PerformanceReportJobDataProvider.REPORT_JOB_PERFORMANCE_PROVIDER_HIGH_LOAD, location = PerformanceReportJobDataProvider.class)
	public void ReportJob_PerformanceTest(String rallyTestCaseID, Integer userDataRowID, Integer reportDataRowID,
			Integer executionTimesForBaselines, String category) throws Exception {
		Log.info(String.format("\nRunning [%s] ReportJob_PerformanceTest ...", rallyTestCaseID));

		executePerformanceTest(userDataRowID, reportDataRowID, executionTimesForBaselines, category);
	}
}
