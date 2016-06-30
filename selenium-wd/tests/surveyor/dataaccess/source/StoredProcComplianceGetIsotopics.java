package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import static surveyor.dataaccess.source.ResourceKeys.CaptureAnalysisDispositionTypesPrefix;

import common.source.Log;

public class StoredProcComplianceGetIsotopics extends BaseEntity {
	private String dateTime;
	private String surveyorUnitName;
	private String disposition;
	private float delta;
	private float uncertainty;
	private String text;

	public StoredProcComplianceGetIsotopics() {
		super();
	}

	public String toString() {
		String delta = String.format("%.2f",this.getDelta());
		String uncertainty = String.format("%.2f",this.getUncertainty());
		return this.getSurveyorUnitName().concat(" ").concat(this.getDateTime()).concat(" ").concat(this.getDisposition()).concat(" ").concat(delta).concat("+/-").concat(uncertainty).concat(" ").concat(this.getText());

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

	public float getDelta() {
		return delta;
	}

	public void setDelta(float delta) {
		this.delta = delta;
	}

	public float getUncertainty() {
		return uncertainty;
	}

	public void setUncertainty(float unsertainty) {
		this.uncertainty = unsertainty;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isEquals(StoredProcComplianceGetIsotopics obj) {
		if (!this.getSurveyorUnitName().trim().equals(obj.getSurveyorUnitName().trim())) {
			Log.error(String.format("SurveyorUnitName is not match, Expect '%s', Actual '%s'", obj.getSurveyorUnitName().trim(), this.getSurveyorUnitName().trim()));
			return false;
		}
		
		String dispositionType = CaptureAnalysisDispositionTypesPrefix+(" "+obj.getDisposition().trim()).replaceAll(" ", "_");
		String dispositionValue = Resources.getResource(dispositionType);
		if (!(this.getDisposition().trim().equals(obj.getDisposition().trim())
				|| dispositionValue.trim().equals(this.getDisposition().trim()))) {
			Log.error(String.format("Disposition is not match, Expect '%s', Actual '%s'", obj.getDisposition().trim()+"/"+dispositionValue, this.getDisposition().trim()));
			return false;
		}
		if (this.getDelta() != (obj.getDelta())) {
			Log.error(String.format("Delta is not match, Expect '%s', Actual '%s'", obj.getDelta(), this.getDelta()));
			return false;
		}
		if (this.getUncertainty() != (obj.getUncertainty())) {
			Log.error(String.format("Uncertainty is not match, Expect '%s', Actual '%s'", obj.getUncertainty(), this.getUncertainty()));
			return false;
		}
		if (!this.getText().trim().equals(obj.getText().trim())) {
			Log.error(String.format("FieldNotes is not match, Expect '%s', Actual '%s'", obj.getText().trim(), this.getText().trim()));
			return false;
		}
		return true;
	}

	public boolean isInList(ArrayList<StoredProcComplianceGetIsotopics> list) {
		for (StoredProcComplianceGetIsotopics storedProcIsotopics : list) {
			if (this.isEquals(storedProcIsotopics)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<StoredProcComplianceGetIsotopics> get(String reportId) {
		ArrayList<StoredProcComplianceGetIsotopics> objReportList = load(reportId);
		return objReportList;
	}

	public static ArrayList<StoredProcComplianceGetIsotopics> getReportIsotopics(String reportId) {
		ArrayList<StoredProcComplianceGetIsotopics> objStoredProcComplianceGetIsotopics = new StoredProcComplianceGetIsotopics().get(reportId);
		return objStoredProcComplianceGetIsotopics;
	}

	private StoredProcComplianceGetIsotopics loadFrom(ResultSet resultSet) {
		StoredProcComplianceGetIsotopics objReport = new StoredProcComplianceGetIsotopics();
		try {
			objReport.setDateTime(resultSet.getString("Date_Time"));
			objReport.setSurveyorUnitName(resultSet.getString("SurveyorUnitName"));
			objReport.setDisposition(resultSet.getString("Disposition"));
			objReport.setDelta(resultSet.getFloat("Delta"));
			objReport.setUncertainty(resultSet.getFloat("Uncertainty"));
			objReport.setText(resultSet.getString("Text"));
		} catch (SQLException e) {
			Log.error("Class Report | " + e.toString());
		}

		return objReport;
	}

	public ArrayList<StoredProcComplianceGetIsotopics> load(String reportId) {
		ArrayList<StoredProcComplianceGetIsotopics> objReportList = new ArrayList<StoredProcComplianceGetIsotopics>();

		try {
			CallableStatement cs = connection.prepareCall("{CALL Compliance_GetIsotopics(?)}");
			cs.setString(1, reportId);
			cs.execute();
			if (cs.getMoreResults()) {
				resultSet = cs.getResultSet();
				while (resultSet.next()) {
					StoredProcComplianceGetIsotopics objReport = loadFrom(resultSet);
					objReportList.add(objReport);
				}
				resultSet.close();

			}
			cs.close();

		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objReportList;
	}

}
