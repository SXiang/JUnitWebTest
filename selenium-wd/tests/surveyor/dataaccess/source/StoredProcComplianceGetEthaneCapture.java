package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.CallableStatement;

import common.source.Log;
import common.source.NumberUtility;

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
		String ethaneRatio = (this.getEthaneRatio() == 0.0) ? "0.00" : Float.toString(this.getEthaneRatio());
		String ethaneRatioSdev = (this.getEthaneRatioSdev() == 0.0) ? "0.00" : Float.toString(this.getEthaneRatioSdev());
		return this.getSurveyorUnitName().concat(" ").concat(this.getDateTime()).concat(" ")
				.concat(this.getDisposition()).concat(" ")
				.concat(ethaneRatio).concat("+/-")
				.concat(ethaneRatioSdev).concat(" ").concat(this.getText());
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
			Log.error(String.format("Surveyor Name not equal - Expect '%s', Actual '%s'",
					this.getSurveyorUnitName().trim(),obj.getSurveyorUnitName().trim()));
			return false; 
		}

		if (!this.getDisposition().trim().equals(obj.getDisposition().trim())) {
			Log.error(String.format("Disposition not equal - Expect '%s', Actual '%s'",
					this.getDisposition().trim(),obj.getDisposition().trim()));
			return false;
		}
		if (this.getEthaneRatio() != (obj.getEthaneRatio())) {
			Log.error(String.format("EthaneRatio not equal - Expect '%s', Actual '%s'",
					this.getEthaneRatio(),obj.getEthaneRatio()));
			return false;
		}
		if (this.getEthaneRatioSdev() != (obj.getEthaneRatioSdev())) {
			Log.error(String.format("Ethane Ratio Sdev not equal - Expect '%s', Actual '%s'",
					this.getEthaneRatioSdev(),obj.getEthaneRatioSdev()));
			return false;
		}
		if (!this.getText().equals(obj.getText().trim())) {
			Log.error(String.format("Field Notes not equal - Expect '%s', Actual '%s'",
					this.getText().trim(),obj.getText().trim()));
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
		StoredProcComplianceGetEthaneCapture objEthaneCapture = new StoredProcComplianceGetEthaneCapture();
		try {
			objEthaneCapture.setDateTime(resultSet.getString("Date_Time"));
			objEthaneCapture.setSurveyorUnitName(resultSet.getString("SurveyorUnitName"));
			objEthaneCapture.setDisposition(resultSet.getString("Disposition"));
			objEthaneCapture.setEthaneRatioSdev(resultSet.getFloat("EthaneRatioSdev"));
			objEthaneCapture.setEthaneRatio(resultSet.getFloat("EthaneRatio"));
			objEthaneCapture.setText(resultSet.getString("Text"));
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
