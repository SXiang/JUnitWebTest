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
	
	public String toString() {
		return String.format("ReportJob=[ReportJobId=%s, ReportJobType=%s, ReportJobStatus=%s, ProcessingStarted=%s, ProcessingCompleted=%s]", 
				ReportJobId, ReportJobType, ReportJobStatus, ProcessingStarted, ProcessingCompleted);
	}
}
