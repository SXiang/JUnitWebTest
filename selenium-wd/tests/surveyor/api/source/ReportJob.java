package surveyor.api.source;

public class ReportJob {
	public String ReportJobId;
	public String ReportJobType;
	public String ReportJobStatus;
	public String ProcessingStarted;
	public String ProcessingCompleted;
	
	public Long getProcessingStartedTimeInMs() {
		return Long.valueOf(ProcessingStarted.replace("/Date(", "").replace(")/", ""));
	}

	public Long getProcessingCompletedTimeInMs() {
		return Long.valueOf(ProcessingCompleted.replace("/Date(", "").replace(")/", ""));
	}
}
