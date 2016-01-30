package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.CallableStatement;

import common.source.Log;

public class StoredProcReferenceGas extends BaseEntity {
	private String installationDate;
	private String userName;
	private String uncertainty;
	private String delta;
	private String testResult;
	private String analyzerId;

	public StoredProcReferenceGas() {
		super();
	}

	public String getInstallationDate() {
		return installationDate;
	}

	public String getUserName() {
		return userName;
	}

	public String getUncertainty() {
		return uncertainty;
	}

	public String getDelta() {
		return delta;
	}

	public String getTestResult() {
		return testResult;
	}

	public String getAnalyzerId() {
		return analyzerId;
	}

	public void setAnalyzerId(String analyzerId) {
		this.analyzerId = analyzerId;
	}

	public void setInstallationDate(String installationDate) {
		this.installationDate = installationDate;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setDelta(String delta) {
		this.delta = delta;
	}

	public void setUncertainty(String uncertainty) {
		this.uncertainty = uncertainty;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}

	public boolean isEquals(StoredProcReferenceGas obj) {
		if (!this.getInstallationDate().equals(obj.getInstallationDate())) {
			return false;
		}
		if (!this.getUserName().equals(obj.getUserName())) {
			return false;
		}
		if (!this.getUncertainty().equals(obj.getUncertainty())) {
			return false;
		}
		if (!this.getDelta().equals(obj.getDelta())) {
			return false;
		}
		if (!this.getTestResult().equals(obj.getTestResult())) {
			return false;
		}
		return true;
	}

	public boolean isInList(ArrayList<StoredProcReferenceGas> list) {
		for (StoredProcReferenceGas storedProcSystemHistory : list) {
			if (this.isEquals(storedProcSystemHistory)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<StoredProcReferenceGas> get(String reportId) {
		// Get from cache if present. Else fetch from Database.
		ArrayList<StoredProcReferenceGas> objReportList = load(reportId);
		return objReportList;
	}

	public static ArrayList<StoredProcReferenceGas> getReferenceGas(String reportId) {
		// Get from cache if present. Else fetch from Database.
		ArrayList<StoredProcReferenceGas> objStoredProcReferenceGas = new StoredProcReferenceGas().get(reportId);
		return objStoredProcReferenceGas;
	}

	private StoredProcReferenceGas loadFrom(ResultSet resultSet) {
		StoredProcReferenceGas objReport = new StoredProcReferenceGas();
		try {
			objReport.setInstallationDate(resultSet.getString("ReferenceGasDate"));
			objReport.setUserName(resultSet.getString("DriverName"));
			objReport.setUncertainty(resultSet.getString("Uncertainty"));
			objReport.setDelta(resultSet.getString("Delta"));
			objReport.setTestResult(resultSet.getString("Disposition"));
			objReport.setAnalyzerId(resultSet.getString("AnalyzerId"));

		} catch (SQLException e) {
			Log.error("Class Report | " + e.toString());
		}

		return objReport;
	}

	public ArrayList<StoredProcReferenceGas> load(String reportId) {
		ArrayList<StoredProcReferenceGas> objReportList = new ArrayList<StoredProcReferenceGas>();

		try {
			CallableStatement proc_stmt = connection.prepareCall("{ call RefGas_GetData(?) }");
			proc_stmt.setString(1, reportId);
			resultSet = proc_stmt.executeQuery();
			while (resultSet.next()) {
				StoredProcReferenceGas objReport = loadFrom(resultSet);
				objReportList.add(objReport);

			}

		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objReportList;
	}

}
