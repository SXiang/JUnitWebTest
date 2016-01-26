package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.CallableStatement;

import common.source.DateUtility;
import common.source.Log;

public class StoredProcSystemHistory extends BaseEntity {
	private String dateCreated;
	private String note;
	private String username;

	public StoredProcSystemHistory() {
		super();
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public String getNote() {
		return note;
	}

	public String getusername() {
		return username;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public boolean isEquals(StoredProcSystemHistory obj) {
		// if all 3 fields match, return true.
		if (!this.getDateCreated().equals(obj.getDateCreated())) {
			return false;
		}
		if (!this.getNote().equals(obj.getNote())) {
			return false;
		}
		if (!this.getusername().equals(obj.getusername())) {
			return false;
		}
		return true;
	}

	public boolean isInList(ArrayList<StoredProcSystemHistory> list) {
		for (StoredProcSystemHistory storedProcSystemHistory : list) {
			if (this.isEquals(storedProcSystemHistory)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<StoredProcSystemHistory> get(String reportId) {
		// Get from cache if present. Else fetch from Database.
		ArrayList<StoredProcSystemHistory> objReportList = load(reportId);
		return objReportList;
	}

	public static ArrayList<StoredProcSystemHistory> getSystemHistory(String reportId) {
		// Get from cache if present. Else fetch from Database.
		ArrayList<StoredProcSystemHistory> objStoredProcSystemHistory = new StoredProcSystemHistory().get(reportId);
		return objStoredProcSystemHistory;
	}

	private StoredProcSystemHistory loadFrom(ResultSet resultSet) {
		StoredProcSystemHistory objReport = new StoredProcSystemHistory();
		try {
			objReport.setDateCreated(resultSet.getString("DateCreated"));
			objReport.setNote(resultSet.getString("Note"));
			objReport.setUserName(resultSet.getString("username"));
		} catch (SQLException e) {
			Log.error("Class Report | " + e.toString());
		}

		return objReport;
	}

	public ArrayList<StoredProcSystemHistory> load(String reportId) {
		ArrayList<StoredProcSystemHistory> objReportList = new ArrayList<StoredProcSystemHistory>();

		try {
			CallableStatement proc_stmt = connection.prepareCall("{ call SysHis_GetData(?) }");
			proc_stmt.setString(1, reportId);
			resultSet = proc_stmt.executeQuery();
			while (resultSet.next()) {
				StoredProcSystemHistory objReport = loadFrom(resultSet);
				objReportList.add(objReport);

			}

		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objReportList;
	}

}
