package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static surveyor.dataaccess.source.ResourceKeys.CaptureAnalysisDispositionTypesPrefix;

import java.sql.CallableStatement;

import common.source.Log;

public class StoredProcComplianceGetEthaneCapture extends BaseEntity {
	private String dateTime;
	private String surveyorUnitName;
	private String disposition;
	private float ethaneRatio;
	private float ethaneRatioSdev;
    
	public StoredProcComplianceGetEthaneCapture() {
		super();
	}

	public String toString() {
		String ethaneRatio = String.format("%.2f", this.getEthaneRatio());
		String ethaneRatioSdev = String.format("%.2f",this.getEthaneRatioSdev());
		return this.getSurveyorUnitName().concat(" ").concat(this.getDateTime()).concat(" ")
				.concat(this.getDisposition()).concat(" ")
				.concat(ethaneRatio).concat("+/-")
				.concat(ethaneRatioSdev);
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getSurveyorUnitName() {
		return surveyorUnitName;
	}

	public void setSurveyorUnitName(String surveyorUnitName) {
		this.surveyorUnitName = surveyorUnitName;
	}



	public String getDisposition() {
		return disposition;
	}

	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}

	public float getEthaneRatio() {
		return ethaneRatio;
	}

	public void setEthaneRatio(float ethaneRatio) {
		this.ethaneRatio = ethaneRatio;
	}

	public float getEthaneRatioSdev() {
		return ethaneRatioSdev;
	}

	public void setEthaneRatioSdev(float ethaneRatioSdev) {
		this.ethaneRatioSdev = ethaneRatioSdev;
	}


	public ArrayList<StoredProcComplianceGetEthaneCapture> get(String reportId) {
		ArrayList<StoredProcComplianceGetEthaneCapture> ethaneCaptureList = load(reportId);
		return ethaneCaptureList;
	}

	public static ArrayList<StoredProcComplianceGetEthaneCapture> getReportEthaneCapture(String reportId) {
		ArrayList<StoredProcComplianceGetEthaneCapture> objStoredProcComplianceGetEthaneCapture = new StoredProcComplianceGetEthaneCapture().get(reportId);
		return objStoredProcComplianceGetEthaneCapture;
	}

	public boolean isEquals(StoredProcComplianceGetEthaneCapture obj) {
		if (!this.getSurveyorUnitName().trim().equalsIgnoreCase(obj.getSurveyorUnitName().trim())) {
			Log.warn(String.format("Surveyor Name not equal - Expect '%s', Actual '%s'",
					this.getSurveyorUnitName().trim(),obj.getSurveyorUnitName().trim()));
			return false; 
		}
		
		String dispositionType = CaptureAnalysisDispositionTypesPrefix+(" "+obj.getDisposition().trim()).replaceAll(" ", "_");
		String dispositionValue = Resources.getResource(dispositionType);
		if (!(this.getDisposition().trim().equals(obj.getDisposition().trim())
				|| dispositionValue.trim().equals(this.getDisposition().trim()))) {
			Log.warn(String.format("Disposition is not match, Expect '%s', Actual '%s'", obj.getDisposition().trim()+"/"+dispositionValue, this.getDisposition().trim()));
			return false;
		}

		if (this.getEthaneRatio() != (obj.getEthaneRatio())) {
			Log.warn(String.format("EthaneRatio not equal - Expect '%s', Actual '%s'",
					this.getEthaneRatio(),obj.getEthaneRatio()));
			return false;
		}
		if (this.getEthaneRatioSdev() != (obj.getEthaneRatioSdev())) {
			Log.warn(String.format("Ethane Ratio Sdev not equal - Expect '%s', Actual '%s'",
					this.getEthaneRatioSdev(),obj.getEthaneRatioSdev()));
			return false;
		}
		return true;
	}

	public boolean isInList(ArrayList<StoredProcComplianceGetEthaneCapture> list) {
		for (StoredProcComplianceGetEthaneCapture storedProcEthaneCapture : list) {
			if (this.isEquals(storedProcEthaneCapture)) {
				return true;
			}
		}
		
		return false;
	}

	private StoredProcComplianceGetEthaneCapture loadFrom(ResultSet resultSet) {
		StoredProcComplianceGetEthaneCapture objEthaneCapture = new StoredProcComplianceGetEthaneCapture();
		try {
			objEthaneCapture.setDateTime(resultSet.getString("Date_Time"));
			objEthaneCapture.setSurveyorUnitName(resultSet.getString("SurveyorUnitName"));
			objEthaneCapture.setDisposition(resultSet.getString("Disposition"));
			objEthaneCapture.setEthaneRatioSdev(resultSet.getFloat("EthaneRatioSdev"));
			objEthaneCapture.setEthaneRatio(resultSet.getFloat("EthaneRatio"));
		} catch (SQLException e) {
			Log.error("Class StoredProcComplianceGetEthaneCapture | " + e.toString());
		}
		return objEthaneCapture;
	}

	public ArrayList<StoredProcComplianceGetEthaneCapture> load(String reportId) {
		ArrayList<StoredProcComplianceGetEthaneCapture> ethaneCaptureList = new ArrayList<StoredProcComplianceGetEthaneCapture>();

		try {
			CallableStatement cs = connection.prepareCall("{CALL Compliance_GetEthaneCaptures(?)}");
			cs.setString(1, reportId);
			cs.execute();
			if (cs.getMoreResults()) {
				resultSet = cs.getResultSet();
				while (resultSet.next()) {
					StoredProcComplianceGetEthaneCapture objReport = loadFrom(resultSet);
					ethaneCaptureList.add(objReport);
				}
				resultSet.close();

			}
			cs.close();

		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return ethaneCaptureList;
	}

}
