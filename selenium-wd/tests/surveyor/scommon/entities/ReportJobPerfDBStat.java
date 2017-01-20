package surveyor.scommon.entities;

import java.time.LocalDateTime;

import common.source.DateUtility;
import surveyor.scommon.source.SurveyorConstants.Environment;

public class ReportJobPerfDBStat {
	private String reportJobTypeId;
	private String reportJobTypeName;
	private LocalDateTime reportJobStartTime;
	private LocalDateTime reportJobEndTime;
	private LocalDateTime testExecutionStartDate;
	private LocalDateTime testExecutionEndDate;
	private String buildNumber;
	private Environment environment;

	public String getReportJobTypeId() {
		return reportJobTypeId;
	}
	public void setReportJobTypeId(String reportJobTypeId) {
		this.reportJobTypeId = reportJobTypeId;
	}
	public String getReportJobTypeName() {
		return reportJobTypeName;
	}
	public void setReportJobTypeName(String reportJobTypeName) {
		this.reportJobTypeName = reportJobTypeName;
	}
	public LocalDateTime getReportJobStartTime() {
		return reportJobStartTime;
	}
	public void setReportJobStartTime(LocalDateTime reportJobStartTime) {
		this.reportJobStartTime = reportJobStartTime;
	}
	public LocalDateTime getReportJobEndTime() {
		return reportJobEndTime;
	}
	public void setReportJobEndTime(LocalDateTime reportJobEndTime) {
		this.reportJobEndTime = reportJobEndTime;
	}
	public LocalDateTime getTestExecutionStartDate() {
		return testExecutionStartDate;
	}
	public void setTestExecutionStartDate(LocalDateTime testExecutionStartDate) {
		this.testExecutionStartDate = testExecutionStartDate;
	}
	public LocalDateTime getTestExecutionEndDate() {
		return testExecutionEndDate;
	}
	public void setTestExecutionEndDate(LocalDateTime testExecutionEndDate) {
		this.testExecutionEndDate = testExecutionEndDate;
	}
	public String getBuildNumber() {
		return buildNumber;
	}
	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}
	public Environment getEnvironment() {
		return environment;
	}
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public String toString() {
		return String.format("ReportJobPerfDBStat=[reportJobTypeId=%s, reportJobTypeName=%s, reportJobStartTime=%s, reportJobEndTime=%s, "
				+ "testExecutionStartDate=%s, testExecutionEndDate=%s, buildNumber=%s, environment=%s]",
					reportJobTypeId, reportJobTypeName,
					reportJobStartTime != null ? DateUtility.getLongDateString(reportJobStartTime) : "",
					reportJobEndTime != null ? DateUtility.getLongDateString(reportJobEndTime) : "",
					testExecutionStartDate != null ? DateUtility.getLongDateString(testExecutionStartDate) : "",
					testExecutionEndDate!= null ? DateUtility.getLongDateString(testExecutionEndDate) : "",
					buildNumber, environment.toString());
	}
}
