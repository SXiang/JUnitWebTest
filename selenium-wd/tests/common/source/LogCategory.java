package common.source;

public enum LogCategory {
	SSRSPdfContent ("SSRSPdfContent"),
	ComplianceReportActions ("ComplianceReportActions");
	
	private final String name;

	LogCategory(String nm) {
		name = nm;
	}
	
	public String toString() {
		return this.name;
	}
}
