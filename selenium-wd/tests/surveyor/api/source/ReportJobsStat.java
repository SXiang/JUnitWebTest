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
}
