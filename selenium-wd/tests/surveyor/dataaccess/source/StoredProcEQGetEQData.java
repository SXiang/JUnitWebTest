package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.CallableStatement;

import common.source.Log;

public class StoredProcEQGetEQData extends BaseEntity {
	private String name;
	private String emissionRank;
	private double emissionRate;
	private String confideneceGroup;
	private int length;
	private double emissionFactor;
	private int numLeaks;
	private double leaksPerFt;
	private double ratePerLeak;

	public StoredProcEQGetEQData() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmissionRank() {
		return emissionRank;
	}

	public void setEmissionRank(String emissionRank) {
		this.emissionRank = emissionRank;
	}

	public double getEmissionRate() {
		return emissionRate;
	}

	public void setEmissionRate(double emissionRate) {
		this.emissionRate = emissionRate;
	}

	public String getConfideneceGroup() {
		return confideneceGroup;
	}

	public void setConfideneceGroup(String confideneceGroup) {
		this.confideneceGroup = confideneceGroup;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getEmissionFactor() {
		return emissionFactor;
	}

	public void setEmissionFactor(double emissionFactor) {
		this.emissionFactor = emissionFactor;
	}

	public int getNumLeaks() {
		return numLeaks;
	}

	public void setNumLeaks(int numLeaks) {
		this.numLeaks = numLeaks;
	}

	public double getLeaksPerFt() {
		return leaksPerFt;
	}

	public void setLeaksPerFt(double leaksPerFt) {
		this.leaksPerFt = leaksPerFt;
	}

	public double getRatePerLeak() {
		return ratePerLeak;
	}

	public void setRatePerLeak(double ratePerLeak) {
		this.ratePerLeak = ratePerLeak;
	}

	public boolean isEquals(StoredProcEQGetEQData obj) {

		if (!(this.getName().trim()).equals(obj.getName().trim())) {
			return false;
		}
		if (!((this.getEmissionRank().trim()).equals(obj.getEmissionRank().trim()))) {
			return false;
		}
		if (!((this.getEmissionRate() == obj.getEmissionRate()))) {
			return false;
		}
		if (!((this.getConfideneceGroup().trim()).equals(obj.getConfideneceGroup().trim()))) {
			return false;
		}
		if (!((this.getLength()) == (obj.getLength()))) {
			return false;
		}
		if (!((this.getEmissionFactor()) == (obj.getEmissionFactor()))) {
			return false;
		}
		if (!((this.getNumLeaks()) == (obj.getNumLeaks()))) {
			return false;
		}
		if (!((this.getLeaksPerFt()) == (obj.getLeaksPerFt()))) {
			return false;
		}
		if (!((this.getRatePerLeak()) == (obj.getRatePerLeak()))) {
			return false;
		}

		return true;
	}

	public boolean isInList(ArrayList<StoredProcEQGetEQData> list) {
		for (StoredProcEQGetEQData storedProcEQGetEQData : list) {
			if (this.isEquals(storedProcEQGetEQData)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<StoredProcEQGetEQData> get(String reportId) {
		ArrayList<StoredProcEQGetEQData> objReportList = load(reportId);
		return objReportList;
	}

	public static ArrayList<StoredProcEQGetEQData> getEQData(String reportId) {
		ArrayList<StoredProcEQGetEQData> objStoredProcEQGetEQData = new StoredProcEQGetEQData().get(reportId);
		return objStoredProcEQGetEQData;
	}

	private StoredProcEQGetEQData loadFrom(ResultSet resultSet) {
		StoredProcEQGetEQData objReport = new StoredProcEQGetEQData();
		try {
			objReport.setName(resultSet.getString("Name"));
			objReport.setEmissionRank(resultSet.getString("EmissionRank"));
			objReport.setEmissionRate(getDoubleColumnValue(resultSet, "EmissionRate"));
			objReport.setConfideneceGroup(resultSet.getString("ConfidenceGroup"));
			objReport.setLength(getIntColumnValue(resultSet, "Length"));
			objReport.setEmissionFactor(getDoubleColumnValue(resultSet, "EmissionsFactor"));
			objReport.setNumLeaks(resultSet.getInt("NumLeaks"));
			objReport.setLeaksPerFt(getDoubleColumnValue(resultSet, "LeaksPerFt"));
			objReport.setRatePerLeak(getDoubleColumnValue(resultSet, "RatePerLeak"));

		} catch (SQLException e) {
			Log.error("Class Report | " + e.toString());
		}

		return objReport;
	}

	public ArrayList<StoredProcEQGetEQData> load(String reportId) {
		ArrayList<StoredProcEQGetEQData> objReportList = new ArrayList<StoredProcEQGetEQData>();

		try {
			CallableStatement proc_stmt = connection.prepareCall("{ call EQ_GetEQData(?) }");
			proc_stmt.setString(1, reportId);
			resultSet = proc_stmt.executeQuery();
			while (resultSet.next()) {
				StoredProcEQGetEQData objReport = loadFrom(resultSet);
				objReportList.add(objReport);
			}

		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objReportList;
	}

}
