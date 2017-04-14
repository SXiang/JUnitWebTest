package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.CallableStatement;

import common.source.BaseHelper;
import common.source.Log;

public class StoredProcComplianceGetIndications extends BaseEntity {
	private String peakNumber;
	private String dateTime;
	private String surveyorUnitName;
	private float amplitude;
	private float ch4;
	private String text;
	private String aggregatedEthaneToMethaneRatio;
	private String aggregatedClassificationConfidence;
	private String aggregatedClassificationConfidenceReport;
	private String aggregateDisposition;

	public StoredProcComplianceGetIndications() {
		super();
	}

	public String toString() {
		return this.getPeakNumber().concat(this.getSurveyorUnitName()).concat(this.getDateTime())
				.concat(String.format("%.2f",this.getAmplitude())).concat(String.format("%.2f", this.getCh4()))
				.concat(this.getAggregatedEthaneToMethaneRatio()!=null?this.getAggregatedEthaneToMethaneRatio().trim():"[AggregatedEthaneToMethaneRatio=NULL]").concat(this.getAggregateDisposition()!=null?this.getAggregateDisposition():"[AggregateDisposition=NULL]")
				.concat(this.getAggregatedClassificationConfidence()!=null?this.getAggregatedClassificationConfidence():"[AggregatedClassificationConfidence=NULL]").concat(this.getText());
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
		if(text==null){
			text = "";
		}
		this.text = text;
	}

	public String getAggregatedEthaneToMethaneRatio() {
		return aggregatedEthaneToMethaneRatio;
	}
	
	public void setAggregatedEthaneToMethaneRatio(String aggregatedEthaneToMethaneRatio) {
		this.aggregatedEthaneToMethaneRatio = aggregatedEthaneToMethaneRatio;
	}

	public String getAggregatedClassificationConfidence() {
		return aggregatedClassificationConfidence;
	}

	public void setAggregatedClassificationConfidence(String aggregatedClassificationConfidence) {
		this.aggregatedClassificationConfidence = aggregatedClassificationConfidence;
	}

	public String getAggregateDisposition() {
		return aggregateDisposition;
	}

	public void setAggregateDisposition(String aggregateDisposition) {
		this.aggregateDisposition = aggregateDisposition;
	}

	public String getAggregatedClassificationConfidenceReport() {
		return aggregatedClassificationConfidenceReport;
	}

	public void setAggregatedClassificationConfidenceReport(String aggregatedClassificationConfidenceReport) {
		this.aggregatedClassificationConfidenceReport = aggregatedClassificationConfidenceReport;
	}

	public ArrayList<StoredProcComplianceGetIndications> get(String reportId) {
		return get(reportId, false);
	}

	public ArrayList<StoredProcComplianceGetIndications> get(String reportId, boolean checkPSFilter) {
		ArrayList<StoredProcComplianceGetIndications> objReportList = load(reportId);
		applyPSFilter(objReportList, checkPSFilter);
		return objReportList;
	}

	public void applyPSFilter(ArrayList<StoredProcComplianceGetIndications> objReportList, boolean checkPSFilter){
		if(!checkPSFilter) 
			return;
		Iterator<StoredProcComplianceGetIndications> it = objReportList.iterator();
		while(it.hasNext()){
			StoredProcComplianceGetIndications indication = it.next();
			String surveyorUnitName = indication.getSurveyorUnitName();
			try{
				float psFilterThreshold = getPSFilterThreshold(surveyorUnitName);
				float ch4 = indication.getCh4();
				if(ch4 < psFilterThreshold){
					it.remove();
					Log.warn("Removed indication: "+indication+" based on psShreshold '"+psFilterThreshold+"' for surveyor '"+surveyorUnitName+"'");
				}
			}catch(Exception e){
				Log.error("Failed to get psFilterTreshold for "+surveyorUnitName);
			}
		}
	}
	public static ArrayList<StoredProcComplianceGetIndications> getReportIndications(String reportId) {
		return getReportIndications(reportId, false);
	}

	public static ArrayList<StoredProcComplianceGetIndications> getReportIndications(String reportId, boolean checkPSFilter) {
		ArrayList<StoredProcComplianceGetIndications> objStoredProcComplianceGetIndications = new StoredProcComplianceGetIndications().get(reportId, checkPSFilter);
		return objStoredProcComplianceGetIndications;
	}
	
	public boolean isEquals(StoredProcComplianceGetIndications obj) {
		Log.method("StoredProcComplianceGetIndications.isEquals", obj.toString());
		if (!this.getPeakNumber().trim().equals(obj.getPeakNumber().trim())) {
			Log.warn(String.format("[isEquals=FALSE] : PeakNumber is not match, Expect '%s', Actual '%s'", obj.getPeakNumber().trim(), getPeakNumber().trim()));
			return false;
		}
		if (!this.getSurveyorUnitName().trim().equalsIgnoreCase(obj.getSurveyorUnitName().trim())) {
			Log.warn(String.format("[isEquals=FALSE] : SurveyorUnitName is not match, Expect '%s', Actual '%s'", obj.getPeakNumber().trim(), getPeakNumber().trim()));
			return false;
		}
		if (this.getAmplitude() != (obj.getAmplitude())) {
			Log.warn(String.format("[isEquals=FALSE] : Amplitude is not match, Expect '%s', Actual '%s'", obj.getAmplitude(), getAmplitude()));
			return false;
		}
		if (this.getCh4() != (obj.getCh4())) {
			Log.warn(String.format("[isEquals=FALSE] : Ch4 is not match, Expect '%s', Actual '%s'", obj.getCh4(), getCh4()));
			return false;
		}
		if (!this.getText().trim().equals(obj.getText().trim())) {
			Log.warn(String.format("[isEquals=FALSE] : FieldNotes is not match, Expect '%s', Actual '%s'", obj.getText().trim(), getText().trim()));
			return false;
		}
		if(!this.getAggregatedClassificationConfidence().equals(obj.getAggregatedClassificationConfidence())){
			Log.warn(String.format("[isEquals=FALSE] : AggregatedClassificationConfidence is not match, Expect '%s', Actual '%s'", obj.getAggregatedClassificationConfidence().trim(), getAggregatedClassificationConfidence().trim()));
			return false;
		}
		if(!this.getAggregatedEthaneToMethaneRatio().equals(obj.getAggregatedEthaneToMethaneRatio())){
			Log.warn(String.format("[isEquals=FALSE] : AggregatedEthaneToMethaneRatio is not match, Expect '%s', Actual '%s'", obj.getAggregatedEthaneToMethaneRatio().trim(), getAggregatedEthaneToMethaneRatio().trim()));
			return false;
		}
		
		Log.info("[isEquals=TRUE]"); 
		return true;
	}

	public boolean isInList(ArrayList<StoredProcComplianceGetIndications> list) {
		Log.method("StoredProcComplianceGetIndications.isInList", String.format("[this=%s]", this.toString()), list);
		for (StoredProcComplianceGetIndications storedProcIndications : list) {
			if (this.isEquals(storedProcIndications)) {
				return true;
			}
		}
		return false;
	}

	private StoredProcComplianceGetIndications loadFrom(ResultSet resultSet) {
		StoredProcComplianceGetIndications objReport = new StoredProcComplianceGetIndications();
		try {
			objReport.setPeakNumber(getStringColumnValue(resultSet, "PeakNumber"));
			objReport.setDateTime(getStringColumnValue(resultSet, "Date_Time"));
			objReport.setSurveyorUnitName(getStringColumnValue(resultSet, "SurveyorUnitName"));
			objReport.setAmplitude(getFloatColumnValue(resultSet, "Amplitude"));
			objReport.setCh4(getFloatColumnValue(resultSet, "CH4"));
			objReport.setText(getStringColumnValue(resultSet, "Text"));
			String aggrClassConf = getStringColumnValue(resultSet, "AggregatedClassificationConfidence");
			if (!BaseHelper.isNullOrEmpty(aggrClassConf)) {
				objReport.setAggregatedClassificationConfidence(aggrClassConf.replaceFirst(">=", ""));
			}
			objReport.setAggregatedClassificationConfidenceReport(getStringColumnValue(resultSet, "AggregatedClassificationConfidence"));
			objReport.setAggregatedEthaneToMethaneRatio(getStringColumnValue(resultSet, "AggregatedEthaneToMethaneRatio"));
			objReport.setAggregateDisposition(getStringColumnValue(resultSet, "AggregatedDisposition"));
		} catch (SQLException e) {
			Log.error("Class StoredProcComplianceGetIndications | " + e.toString());
		}

		return objReport;
	}

	public float getPSFilterThreshold(String surveyorUnitName) throws SQLException{
		String sql = "Select PriorityScoreFilterThreshold from dbo.LocationAnalyticsParameter, dbo.SurveyorUnit "+
					"Where LocationAnalyticsParameter.LocationId = SurveyorUnit.LocationId And SurveyorUnit.Description = '" + surveyorUnitName+"'";
		ResultSet rs = executeQuery(sql);
		rs.next();
		float psFilterThreshold = rs.getFloat("PriorityScoreFilterThreshold");
		return psFilterThreshold;
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
