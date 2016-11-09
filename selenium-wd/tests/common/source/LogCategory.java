package common.source;

public enum LogCategory {
	SSRSPdfContent ("SSRSPdfContent"),
	ComplianceReportActions ("ComplianceReportActions"),
	ClickWebElement ("ClickWebElement"),
	VerboseLogging ("VerboseLogging");
	
	private final String name;

	LogCategory(String nm) {
		name = nm;
	}
	
	public String toString() {
		return this.name;
	}
}
