package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;

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
