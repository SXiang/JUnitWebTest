package common.source;

import java.util.HashMap;

public enum LogSwitches {
	INSTANCE;

	private HashMap<LogCategory, Boolean> switchMap = new HashMap<LogCategory, Boolean>();
	
	private LogSwitches() {
		switchMap.put(LogCategory.SSRSPdfContent, TestContext.INSTANCE.getTestSetup().isLogCategorySSRSPdfContentEnabled());
		switchMap.put(LogCategory.ComplianceReportActions, TestContext.INSTANCE.getTestSetup().isLogCategoryComplianceReportActionsEnabled());
		switchMap.put(LogCategory.ClickWebElement, TestContext.INSTANCE.getTestSetup().isLogCategoryClickWebElementEnabled());
		switchMap.put(LogCategory.VerboseLogging, TestContext.INSTANCE.getTestSetup().isLogCategoryVerboseLoggingEnabled());
	}
	
	public Boolean isEnabled(LogCategory logCategory) {
		return switchMap.get(logCategory);
	}
}
