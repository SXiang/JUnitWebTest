package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.CallableStatement;

import common.source.Log;

public class StoredProcLisaInvestigationShowIndication extends BaseEntity {
	private int numLeaks;
	private int boxTypeId;
	private int boxNumber;
	private String investigationStatus;
	private String investigationDateTime;
	private String peakAssignedUserName;
	private String totalDuration;
	private String boxId;

	public StoredProcLisaInvestigationShowIndication() {
		super();
	}

	public int getNumLeaks() {
		return this.numLeaks;
	}

	public void setNumLeaks(int peakNumber) {
		this.numLeaks = peakNumber;
	}

	public String getInvestigationStatus() {
		return this.investigationStatus;
	}

	public void setInvestigationStatus(String investigationStatus) {
		this.investigationStatus = investigationStatus;
	}

	public String getInvestigationDateTime() {
		return this.investigationDateTime;
	}

	public void setInvestigationDateTime(String investigationDateTime) {
		this.investigationDateTime = investigationDateTime;
	}

	public String getAssignedUserName() {
		return this.peakAssignedUserName;
	}

	public void setAssignedUserName(String peakAssignedUserName) {
		this.peakAssignedUserName = peakAssignedUserName;
	}

	public String getTotalDuration() {
		return this.totalDuration;
	}

	public void setTotalDuration(String totalDuration) {
		this.totalDuration = totalDuration;
	}

	public int getBoxTypeId() {
		return boxTypeId;
	}

	public void setBoxTypeId(int boxTypeId) {
		this.boxTypeId = boxTypeId;
	}

	public Integer getBoxNumber() {
		return boxNumber;
	}

	public void setBoxNumber(int boxNumber) {
		this.boxNumber = boxNumber;
	}

	public String getBoxId() {
		return boxId;
	}

	public void setBoxId(String boxId) {
		this.boxId = boxId;
	}

	public String toString() {
		return Integer.toString(this.getNumLeaks()).trim().concat(String.format("%d", this.getBoxNumber())).concat(String.format("%d", this.getBoxTypeId()))
				.concat(this.getInvestigationStatus()).concat(this.getInvestigationDateTime()).concat(this.getAssignedUserName()).concat(this.getTotalDuration());
	}

	public boolean isEquals(StoredProcLisaInvestigationShowIndication obj) {
		if (this.getNumLeaks()!=obj.getNumLeaks()) {
			return false;
		}
		if (this.getBoxTypeId() != (obj.getBoxTypeId())) {
			return false;
		}
		if (this.getBoxNumber() != (obj.getBoxNumber())) {
			return false;
		}
		if (!this.getInvestigationStatus().equals(obj.getInvestigationStatus())) {
			return false;
		}
		if (!this.getAssignedUserName().equals(obj.getAssignedUserName())) {
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
			objReport.setNumLeaks(getIntColumnValue(resultSet,"NumLeaks"));
			objReport.setBoxTypeId(getIntColumnValue(resultSet,"BoxTypeId"));
			objReport.setBoxNumber(getIntColumnValue(resultSet,"BoxNumber"));
			objReport.setInvestigationStatus(resultSet.getString("InvestigationStatus"));
			objReport.setInvestigationDateTime(resultSet.getString("InvestigationDateTime"));
			if (resultSet.wasNull()) {
				objReport.setInvestigationDateTime(" ");
			}
			objReport.setAssignedUserName(resultSet.getString("AssignedUserName"));
			if (resultSet.wasNull()) {
				objReport.setAssignedUserName(" ");
			}
			objReport.setTotalDuration(resultSet.getString("TotalDuration"));
			if (resultSet.wasNull()) {
				objReport.setTotalDuration(" ");
			}
			objReport.setBoxId(resultSet.getString("BoxId"));

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