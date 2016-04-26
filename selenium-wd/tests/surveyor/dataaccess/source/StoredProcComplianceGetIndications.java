package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.CallableStatement;

import common.source.Log;

public class StoredProcComplianceGetIndications extends BaseEntity {
	private String peakNumber;
	private String dateTime;
	private String surveyorUnitName;
	private float amplitude;
	private float ch4;
	private String text;
	private float aggregatedEthaneRatio;
	private float aggregatedEthaneRatioSdev;
	private String aggregatedClassificationConfidence;;

	public StoredProcComplianceGetIndications() {
		super();
	}

	public String toString() {
		return this.getPeakNumber().concat(" ").concat(this.getSurveyorUnitName()).concat(" ").concat(this.getDateTime())
				.concat(" ").concat(Float.toString(this.getAmplitude())).concat(" ").concat(Float.toString(this.getCh4()))
				.concat(" ").concat(this.getText())
				.concat(" ").concat(Float.toString(this.getAggregatedEthenaRatio()))
				.concat(" ").concat(Float.toString(this.getAggregatedEthaneRatioSdev()))
				.concat(" ").concat(this.getAggregatedClassificationConfidence());
	}

	public String getPeakNumber() {
		return peakNumber;
	}

	public void setPeakNumber(String peakNumber) {
		this.peakNumber = peakNumber;
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

	public float getAmplitude() {
		return amplitude;
	}

	public void setAmplitude(float amplitude) {
		this.amplitude = amplitude;
	}

	public float getCh4() {
		return ch4;
	}

	public void setCh4(float ch4) {
		this.ch4 = ch4;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


	public float getAggregatedEthenaRatio() {
		return aggregatedEthaneRatio;
	}

	public void setAggregatedEthaneRatio(float aggregatedEthaneRatio) {
		this.aggregatedEthaneRatio = aggregatedEthaneRatio;
	}

	public float getAggregatedEthaneRatioSdev() {
		return aggregatedEthaneRatioSdev;
	}

	public void setAggregatedEthaneRatioSdev(float aggregatedEthaneRatioSdev) {
		this.aggregatedEthaneRatioSdev = aggregatedEthaneRatioSdev;
	}

	public String getAggregatedClassificationConfidence() {
		return aggregatedClassificationConfidence;
	}

	public void setAggregatedClassificationConfidence(String agggregatedClassificationConfidence) {
		this.aggregatedClassificationConfidence = agggregatedClassificationConfidence;
	}

	public ArrayList<StoredProcComplianceGetIndications> get(String reportId) {
		ArrayList<StoredProcComplianceGetIndications> objReportList = load(reportId);
		return objReportList;
	}

	public static ArrayList<StoredProcComplianceGetIndications> getReportIndications(String reportId) {
		ArrayList<StoredProcComplianceGetIndications> objStoredProcComplianceGetIndications = new StoredProcComplianceGetIndications().get(reportId);
		return objStoredProcComplianceGetIndications;
	}

	public boolean isEquals(StoredProcComplianceGetIndications obj) {
		if (!this.getPeakNumber().trim().equals(obj.getPeakNumber().trim())) {
			return false;
		}
		if (!this.getSurveyorUnitName().trim().equalsIgnoreCase(obj.getSurveyorUnitName().trim())) {
			return false;
		}
		if (this.getAmplitude() != (obj.getAmplitude())) {
			return false;
		}
		if (this.getCh4() != (obj.getCh4())) {
			return false;
		}
		if (!this.getText().trim().equals(obj.getText().trim())) {
			return false;
		}
		if(!this.getAggregatedClassificationConfidence().equals(obj.getAggregatedClassificationConfidence())){
			return false;
		}
		if(this.getAggregatedEthaneRatioSdev() != obj.getAggregatedEthaneRatioSdev()){
			return false;
		}
		if(this.getAggregatedEthenaRatio() != obj.getAggregatedEthenaRatio()){
			return false;
		}
		return true;
	}

	public boolean isInList(ArrayList<StoredProcComplianceGetIndications> list) {
		for (StoredProcComplianceGetIndications storedProcIndications : list) {
			Log.debug(this.toString()+"  ---   "+storedProcIndications);	
			if (this.isEquals(storedProcIndications)) {
				return true;
			}
		}
		return false;
	}

	private StoredProcComplianceGetIndications loadFrom(ResultSet resultSet) {
		StoredProcComplianceGetIndications objReport = new StoredProcComplianceGetIndications();
		try {
			objReport.setPeakNumber(resultSet.getString("PeakNumber"));
			objReport.setDateTime(resultSet.getString("Date_Time"));
			objReport.setSurveyorUnitName(resultSet.getString("SurveyorUnitName"));
			objReport.setAmplitude(resultSet.getFloat("Amplitude"));
			objReport.setCh4(resultSet.getFloat("CH4"));
			objReport.setText(resultSet.getString("Text"));
			objReport.setAggregatedClassificationConfidence(resultSet.getString("AggregatedClassificationConfidence").replaceFirst(">=", ""));
			objReport.setAggregatedEthaneRatioSdev(resultSet.getFloat("AggregatedEthaneRatioSdev"));
			objReport.setAggregatedEthaneRatio(resultSet.getFloat("AggregatedEthenaRatio"));
		} catch (SQLException e) {
			Log.error("Class Report | " + e.toString());
		}

		return objReport;
	}

	public ArrayList<StoredProcComplianceGetIndications> load(String reportId) {
		ArrayList<StoredProcComplianceGetIndications> objReportList = new ArrayList<StoredProcComplianceGetIndications>();

		try {
			CallableStatement cs = connection.prepareCall("{CALL Compliance_GetIndications(?)}");
			cs.setString(1, reportId);
			cs.execute();
			if (cs.getMoreResults()) {
				resultSet = cs.getResultSet();
				while (resultSet.next()) {
					StoredProcComplianceGetIndications objReport = loadFrom(resultSet);
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
