package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.CallableStatement;

import common.source.Log;

public class StoredProcComplianceAssessmentGetReportDrivingSurveys extends BaseEntity {
	private String reportId;
	private String analyzerId;
	private String startDateTimeWithTZ;
	private String endDateTimeWithTZ;
	private String userName;
	private String tag;
	private String stabilityClass;
	private String description;

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

	public String getStartDateTimeWithTZ() {
		return startDateTimeWithTZ;
	}

	public void setStartDateTimeWithTZ(String startDateTimeWithTZ) {
		this.startDateTimeWithTZ = startDateTimeWithTZ;
	}

	public String getEndDateTimeWithTZ() {
		return endDateTimeWithTZ;
	}

	public void setEndDateTimeWithTZ(String endDateTimeWithTZ) {
		this.endDateTimeWithTZ = endDateTimeWithTZ;
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

	public boolean isEquals(StoredProcComplianceAssessmentGetReportDrivingSurveys obj) {

		if (!((this.getAnalyzerId().trim()).equals(obj.getAnalyzerId().trim()))) {
			return false;
		}
		if (!((this.getStartDateTimeWithTZ().trim().replaceAll("\\s+", "")).equals(obj.getStartDateTimeWithTZ().trim().replaceAll("\\s+", "")))) {
			return false;
		}
		if (!((this.getEndDateTimeWithTZ().trim().replaceAll("\\s+", "")).equals(obj.getEndDateTimeWithTZ().trim().replaceAll("\\s+", "")))) {
			return false;
		}
		if (!((this.getUserName().trim()).equals(obj.getUserName().trim()))) {
			return false;
		}
		if (!((this.getStabilityClass().trim()).equals(obj.getStabilityClass().trim()))) {
			return false;
		}
		if (!((this.getDescription().trim()).equals(obj.getDescription().trim()))) {
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
			objReport.setStartDateTimeWithTZ(resultSet.getString("StartDateTimeWithTZ"));
			objReport.setEndDateTimeWithTZ(resultSet.getString("EndDateTimeWithTZ"));
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
