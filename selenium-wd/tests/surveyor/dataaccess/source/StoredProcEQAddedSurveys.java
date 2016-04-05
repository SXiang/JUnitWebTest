package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.CallableStatement;

import common.source.Log;

public class StoredProcEQAddedSurveys extends BaseEntity {
	private String analyzerId;
	private String startDateTimeWithTZ;
	private String endDateTimeWithTZ;
	private String userName;
	private String tag;
	private String stabilityClass;
	private String description;

	public StoredProcEQAddedSurveys() {
		super();
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



	public ArrayList<StoredProcEQAddedSurveys> get(String reportId) {
		ArrayList<StoredProcEQAddedSurveys> objReportList = load(reportId);
		return objReportList;
	}

	public static ArrayList<StoredProcEQAddedSurveys> getReportDrivingSurveys(String reportId) {
		ArrayList<StoredProcEQAddedSurveys> objStoredProcReportDrivingSurveys = new StoredProcEQAddedSurveys().get(reportId);
		return objStoredProcReportDrivingSurveys;
	}

	private StoredProcEQAddedSurveys loadFrom(ResultSet resultSet) {
		StoredProcEQAddedSurveys objReport = new StoredProcEQAddedSurveys();
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

	public ArrayList<StoredProcEQAddedSurveys> load(String reportId) {
		ArrayList<StoredProcEQAddedSurveys> objReportList = new ArrayList<StoredProcEQAddedSurveys>();

		try {
			CallableStatement proc_stmt = connection.prepareCall("{ call EQ_AddedSurveys(?) }");
			proc_stmt.setString(1, reportId);
			resultSet = proc_stmt.executeQuery();
			while (resultSet.next()) {
				StoredProcEQAddedSurveys objReport = loadFrom(resultSet);
				objReportList.add(objReport);
			}

		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objReportList;
	}

}
