package surveyor.api.source;

import java.util.List;

public class ReportJobsStat {
	public String ReportTitle;
	public String Id;
	public String ReportStatus;
	public String ProcessingStarted;
	public String ProcessingCompleted;
	public List<ReportJob> ReportJobs;
	
	public Long getProcessingStartedTimeInMs() {
		return Long.valueOf(ProcessingStarted.replace("/Date(", "").replace(")/", ""));
	}

	public Long getProcessingCompletedTimeInMs() {
		return Long.valueOf(ProcessingCompleted.replace("/Date(", "").replace(")/", ""));
	}
	
	public String toString() {
		StringBuilder reportJobListBuilder = new StringBuilder();
		if (this.ReportJobs != null && this.ReportJobs.size() > 0) {
			for (int i = 0; i < this.ReportJobs.size(); i++) {
				reportJobListBuilder.append(String.format("[%s]", this.ReportJobs.get(i).toString()));
				if (i != this.ReportJobs.size()-1) {
					reportJobListBuilder.append(",");
				}
			}
		}
		
		return String.format("ReportJobsStat=[ReportTitle=%s, Id=%s, ReportStatus=%s, ProcessingStarted=%s, ProcessingCompleted=%s, List[ReportJob]={%s}]", 
				ReportTitle, Id, ReportStatus, ProcessingStarted, ProcessingCompleted, reportJobListBuilder.toString());
	}
}
