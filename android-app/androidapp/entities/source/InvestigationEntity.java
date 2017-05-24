package androidapp.entities.source;

public class InvestigationEntity {
	private String lisaId;
	private String investigationStatus;

	public String getReportTitle() {
		return lisaId;
	}

	public void setReportTitle(String reportTitle) {
		this.lisaId = reportTitle;
	}

	public String getReportName() {
		return investigationStatus;
	}

	public void setReportName(String reportName) {
		this.investigationStatus = reportName;
	}
}