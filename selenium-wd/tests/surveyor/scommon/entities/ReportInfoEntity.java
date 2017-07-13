package surveyor.scommon.entities;

public class ReportInfoEntity {
	private String reportTitle;
	private String reportName;

	public ReportInfoEntity(String reportTitle, String reportName) {
		this.reportName = reportName;
		this.reportTitle = reportTitle;
	}

	public String getReportTitle() {
		return reportTitle;
	}
	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
}