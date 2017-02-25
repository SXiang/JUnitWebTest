package surveyor.performance.source;

import java.time.LocalDateTime;

import common.source.DateUtility;

public class BaseReportJobPerformanceAssetBoxHighlightTest extends BaseReportJobPerformanceTest {

	protected void executeAssetBoxHighlightTest(Integer userDataRowID, Integer reportDataRowID) throws Exception {
		LocalDateTime startDate = LocalDateTime.now();
		createAndAssertOnReportGenerationComplete(userDataRowID, reportDataRowID);
		getComplianceReportsPageAction().getComplianceReportsPage().setReportEndEpochTime(DateUtility.getCurrentUnixEpochTime());
		// Post execution results to automation DB.
		LocalDateTime endDate = LocalDateTime.now();
		postRunResultsToAutomationDB(reportDataRowID, startDate, endDate);
	}
}
