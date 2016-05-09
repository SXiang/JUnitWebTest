package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.CallableStatement;

import common.source.Log;

public class StoredProcLisaInvestigationShowIndication extends BaseEntity {
	private String PeakNumber;
	private float Amplitude;
	private String CH4;
	private String InvestigationStatus;
	private String InvestigationDateTime;
	private String PeakAssignedUserName;
	private String TotalDuration;

	public StoredProcLisaInvestigationShowIndication() {
		super();
	}

	public String getPeakNumber() {
		return this.PeakNumber;
	}

	public void setPeakNumber(String peakNumber) {
		this.PeakNumber = peakNumber;
	}

	public float getAmplitude() {
		return this.Amplitude;
	}

	public void setAmplitude(float amplitude) {
		this.Amplitude = amplitude;
	}

	public String getCH4() {
		return this.CH4;
	}

	public void setCH4(String cH4) {
		this.CH4 = cH4;
	}

	public String getInvestigationStatus() {
		return this.InvestigationStatus;
	}

	public void setInvestigationStatus(String investigationStatus) {
		this.InvestigationStatus = investigationStatus;
	}

	public String getInvestigationDateTime() {
		return this.InvestigationDateTime;
	}

	public void setInvestigationDateTime(String investigationDateTime) {
		this.InvestigationDateTime = investigationDateTime;
	}

	public String getPeakAssignedUserName() {
		return this.PeakAssignedUserName;
	}

	public void setPeakAssignedUserName(String peakAssignedUserName) {
		this.PeakAssignedUserName = peakAssignedUserName;
	}

	public String getTotalDuration() {
		return this.TotalDuration;
	}

	public void setTotalDuration(String totalDuration) {
		this.TotalDuration = totalDuration;
	}

	public String toString() {
		return this.getPeakNumber().trim().concat(String.format("%.2f", this.getAmplitude())).concat(this.getInvestigationStatus()).concat(this.getInvestigationDateTime()).concat(this.getPeakAssignedUserName()).concat(this.getTotalDuration());
	}

	public boolean isEquals(StoredProcLisaInvestigationShowIndication obj) {
		if (!this.getPeakNumber().equals(obj.getPeakNumber())) {
			return false;
		}
		if (this.getAmplitude() != (obj.getAmplitude())) {
			return false;
		}
		if (!this.getCH4().equals(obj.getCH4())) {
			return false;
		}
		if (!this.getInvestigationStatus().equals(obj.getInvestigationStatus())) {
			return false;
		}
		if (!this.getPeakAssignedUserName().equals(obj.getPeakAssignedUserName())) {
			return false;
		}
		if (!this.getInvestigationDateTime().equals(obj.getInvestigationDateTime())) {
			return false;
		}
		if (!this.getTotalDuration().equals(obj.getTotalDuration())) {
			return false;
		}
		return true;
	}

	public boolean isInList(ArrayList<StoredProcLisaInvestigationShowIndication> list) {
		for (StoredProcLisaInvestigationShowIndication storedProcInvestigation : list) {
			if (this.isEquals(storedProcInvestigation)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<StoredProcLisaInvestigationShowIndication> get(String reportId) {
		ArrayList<StoredProcLisaInvestigationShowIndication> objReportList = load(reportId);
		return objReportList;
	}

	public static ArrayList<StoredProcLisaInvestigationShowIndication> getLisaInvestigation(String reportId) {
		ArrayList<StoredProcLisaInvestigationShowIndication> objLisaInvestigation = new StoredProcLisaInvestigationShowIndication().get(reportId);
		return objLisaInvestigation;
	}

	private StoredProcLisaInvestigationShowIndication loadFrom(ResultSet resultSet) {
		StoredProcLisaInvestigationShowIndication objReport = new StoredProcLisaInvestigationShowIndication();
		try {
			objReport.setPeakNumber(resultSet.getString("PeakNumber"));
			objReport.setAmplitude(resultSet.getFloat("Amplitude"));
			objReport.setCH4(resultSet.getString("CH4"));
			objReport.setInvestigationStatus(resultSet.getString("InvestigationStatus"));
			objReport.setInvestigationDateTime(resultSet.getString("InvestigationDateTime"));
			if (resultSet.wasNull()) {
				objReport.setInvestigationDateTime(" ");
			}
			objReport.setPeakAssignedUserName(resultSet.getString("PeakAssignedUserName"));
			if (resultSet.wasNull()) {
				objReport.setPeakAssignedUserName(" ");
			}
			objReport.setTotalDuration(resultSet.getString("TotalDuration"));
			if (resultSet.wasNull()) {
				objReport.setTotalDuration(" ");
			}

		} catch (SQLException e) {
			Log.error("Class Report | " + e.toString());
		}

		return objReport;
	}

	public ArrayList<StoredProcLisaInvestigationShowIndication> load(String reportId) {
		ArrayList<StoredProcLisaInvestigationShowIndication> objReportList = new ArrayList<StoredProcLisaInvestigationShowIndication>();

		try {
			CallableStatement proc_stmt = connection.prepareCall("{ call LisaInvestigation_ShowIndication(?) }");
			proc_stmt.setString(1, reportId);
			resultSet = proc_stmt.executeQuery();
			while (resultSet.next()) {
				StoredProcLisaInvestigationShowIndication objReport = loadFrom(resultSet);
				objReportList.add(objReport);

			}

		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objReportList;
	}

}
