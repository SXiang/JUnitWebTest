package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.CallableStatement;
import java.sql.Date;

import common.source.DateUtility;
import common.source.Log;

public class StoredProcComplianceAssessmentGetReportDrivingSurveys extends BaseEntity {
	private String reportId;
	private String analyzerId;
	private String preferredPreferredStartDateTimeWithTZ;
	private String preferredEndDateTimeWithTZ;
	private String userName;
	private String tag;
	private String stabilityClass;
	private String description;
	private String duration;

	
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public StoredProcComplianceAssessmentGetReportDrivingSurveys() {
		super();
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getAnalyzerId() {
		return analyzerId;
	}

	public void setAnalyzerId(String analyzerId) {
		this.analyzerId = analyzerId;
	}

	public String getPreferredStartDateTimeWithTZ() {
		return preferredPreferredStartDateTimeWithTZ;
	}

	public void setPreferredStartDateTimeWithTZ(String preferredPreferredStartDateTimeWithTZ) {
		this.preferredPreferredStartDateTimeWithTZ = preferredPreferredStartDateTimeWithTZ;
	}

	public String getPreferredEndDateTimeWithTZ() {
		return preferredEndDateTimeWithTZ;
	}

	public void setPreferredEndDateTimeWithTZ(String preferredEndDateTimeWithTZ) {
		this.preferredEndDateTimeWithTZ = preferredEndDateTimeWithTZ;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getStabilityClass() {
		return stabilityClass;
	}

	public void setStabilityClass(String stabilityClass) {
		this.stabilityClass = stabilityClass;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString(){
		String wsp = " ";
		String text = getPreferredStartDateTimeWithTZ() + wsp + getPreferredEndDateTimeWithTZ() + wsp + duration + wsp + getUserName()
				+ wsp + getDescription()
				+ wsp + getAnalyzerId() + wsp + getTag() + wsp+getStabilityClass();
		return text;
	}

	public boolean isEquals(StoredProcComplianceAssessmentGetReportDrivingSurveys obj) {

		if (!((this.getAnalyzerId().trim()).equals(obj.getAnalyzerId().trim()))) {
			Log.error(String.format("AnalyzerId is not match, Expect '%s', Actual '%s'", this.getAnalyzerId().trim(), obj.getAnalyzerId().trim()));
			return false;
		}
		String minutesPattern = "(\\d{1,2}:\\d{1,2}):\\d{1,2}";
		String expectedDate = obj.getPreferredStartDateTimeWithTZ().trim().replaceFirst(minutesPattern,"$1").replaceAll("\\s+", " ");
		String actualDate = this.getPreferredStartDateTimeWithTZ().trim().replaceFirst(minutesPattern,"$1").replaceAll("\\s+", " ");

		if(DateUtility.compareDatesWithTZ(expectedDate, false, actualDate, false)!=0){
			Log.warn(String.format("StartDate is not match, Expect '%s', Actual '%s'", expectedDate, actualDate));
			return false;
		}
		expectedDate = obj.getPreferredEndDateTimeWithTZ().trim().replaceFirst(minutesPattern,"$1").replaceAll("\\s+", " ");
		actualDate = this.getPreferredEndDateTimeWithTZ().trim().replaceFirst(minutesPattern,"$1").replaceAll("\\s+", " ");
		if(DateUtility.compareDatesWithTZ(expectedDate, false, actualDate, false)!=0){
			Log.warn(String.format("EndDate is not match, Expect '%s', Actual '%s'", expectedDate, actualDate));
			return false;
		}
		if (!((this.getUserName().trim()).equals(obj.getUserName().trim()))) {
			Log.warn(String.format("UserName is not match, Expect '%s', Actual '%s'", this.getUserName().trim(), obj.getUserName().trim()));
			return false;
		}
		if (!((this.getStabilityClass().trim()).equals(obj.getStabilityClass().trim()))) {
			Log.warn(String.format("Stability is not match, Expect '%s', Actual '%s'", this.getStabilityClass().trim(), obj.getStabilityClass().trim()));
			return false;
		}
		if (!((this.getDescription().trim()).equals(obj.getDescription().trim()))) {
			Log.warn(String.format("Description is not match, Expect '%s', Actual '%s'", this.getDescription().trim(), obj.getDescription().trim()));
			return false;
		}
		return true;
	}

	public boolean isInList(ArrayList<StoredProcComplianceAssessmentGetReportDrivingSurveys> list) {
		for (StoredProcComplianceAssessmentGetReportDrivingSurveys storedProcComplianceAssessmentGetReportDrivingSurveys : list) {
			if (this.isEquals(storedProcComplianceAssessmentGetReportDrivingSurveys)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<StoredProcComplianceAssessmentGetReportDrivingSurveys> get(String reportId) {
		ArrayList<StoredProcComplianceAssessmentGetReportDrivingSurveys> objReportList = load(reportId);
		return objReportList;
	}

	public static ArrayList<StoredProcComplianceAssessmentGetReportDrivingSurveys> getReportDrivingSurveys(String reportId) {
		ArrayList<StoredProcComplianceAssessmentGetReportDrivingSurveys> objStoredProcReportDrivingSurveys = new StoredProcComplianceAssessmentGetReportDrivingSurveys().get(reportId);
		return objStoredProcReportDrivingSurveys;
	}

	private StoredProcComplianceAssessmentGetReportDrivingSurveys loadFrom(ResultSet resultSet) {
		StoredProcComplianceAssessmentGetReportDrivingSurveys objReport = new StoredProcComplianceAssessmentGetReportDrivingSurveys();
		try {
			objReport.setAnalyzerId(resultSet.getString("AnalyzerId"));
			float epochStart = resultSet.getFloat("StartEpoch");
			float epochEnd = resultSet.getFloat("EndEpoch");
			objReport.setDuration(""+ Math.round((epochEnd - epochStart)/60));
			objReport.setPreferredStartDateTimeWithTZ(resultSet.getString("PreferredStartDateTimeWithTZ"));
			objReport.setPreferredEndDateTimeWithTZ(resultSet.getString("PreferredEndDateTimeWithTZ"));
			objReport.setUserName(resultSet.getString("UserName"));
			objReport.setTag(resultSet.getString("Tag"));
			objReport.setStabilityClass(resultSet.getString("StabilityClass"));
			objReport.setDescription(resultSet.getString("Description"));
		} catch (SQLException e) {
			Log.error("Class Report | " + e.toString());
		}

		return objReport;
	}

	public ArrayList<StoredProcComplianceAssessmentGetReportDrivingSurveys> load(String reportId) {
		ArrayList<StoredProcComplianceAssessmentGetReportDrivingSurveys> objReportList = new ArrayList<StoredProcComplianceAssessmentGetReportDrivingSurveys>();

		try {
			CallableStatement proc_stmt = connection.prepareCall("{ call Compliance_Assessment_GetReportDrivingSurveys(?) }");
			proc_stmt.setString(1, reportId);
			resultSet = proc_stmt.executeQuery();
			while (resultSet.next()) {
				StoredProcComplianceAssessmentGetReportDrivingSurveys objReport = loadFrom(resultSet);
				objReportList.add(objReport);
			}

		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objReportList;
	}

}
