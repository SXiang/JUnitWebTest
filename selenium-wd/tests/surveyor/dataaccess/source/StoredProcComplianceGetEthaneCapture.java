package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.CallableStatement;

import common.source.Log;

public class StoredProcComplianceGetEthaneCapture extends BaseEntity {
	private String dateTime;
	private String surveyorUnitName;
	private String disposition;
	private float ethaneRatio;
	private float ethaneRatioSdev;
	private String text;
    
	public StoredProcComplianceGetEthaneCapture() {
		super();
	}

	public String toString() {
		return this.getSurveyorUnitName().concat(" ").concat(this.getDateTime()).concat(" ")
				.concat(this.getDisposition()).concat(" ").concat(Float.toString(this.getEthaneRatio())).concat(" ")
				.concat(Float.toString(this.getEthaneRatioSdev()).concat(" ").concat(this.getText()));
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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
			return false;
		}

		if (!this.getDisposition().trim().equals(obj.getDisposition().trim())) {
			return false;
		}
		if (this.getEthaneRatio() != (obj.getEthaneRatio())) {
			return false;
		}
		if (this.getEthaneRatioSdev() != (obj.getEthaneRatioSdev())) {
			return false;
		}
		if (!this.getText().equals(obj.getText().trim())) {
			return false;
		}		
		return true;
	}

	public boolean isInList(ArrayList<StoredProcComplianceGetEthaneCapture> list) {
		for (StoredProcComplianceGetEthaneCapture storedProcEthaneCapture : list) {
			Log.debug(this.toString()+"  ---   "+storedProcEthaneCapture);	
			if (this.isEquals(storedProcEthaneCapture)) {
				return true;
			}
		}
		
		return false;
	}

	private StoredProcComplianceGetEthaneCapture loadFrom(ResultSet resultSet) {
		StoredProcComplianceGetEthaneCapture objReport = new StoredProcComplianceGetEthaneCapture();
		try {
			objReport.setDateTime(resultSet.getString("Date_Time"));
			objReport.setSurveyorUnitName(resultSet.getString("SurveyorUnitName"));
			objReport.setDisposition(resultSet.getString("Disposition"));
			objReport.setEthaneRatioSdev(resultSet.getFloat("EthaneRatioSdev"));
			objReport.setEthaneRatio(resultSet.getFloat("EthaneRatio"));
			objReport.setText(resultSet.getString("Text"));
		} catch (SQLException e) {
			Log.error("Class Report | " + e.toString());
		}
		return objReport;
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
