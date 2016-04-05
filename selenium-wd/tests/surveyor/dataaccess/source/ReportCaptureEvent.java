package surveyor.dataaccess.source;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import common.source.Log;
 
public class ReportCaptureEvent extends BaseEntity {
	private static final String CACHE_KEY = "REPORTCAPTUREEVENT.";
 
	private Float uncertainty;
	private Float replayMax;
	private Boolean referenceGas;
	private Float epochTime;
	private Object shape;
	private String reportId;
	private Float delta;
	private String id;
	private Float replayLMin;
	private Float gpsLongitude;
	private Date datetime;
	private String surveyId;
	private Float concentration;
	private Float distance;
	private Float replayRMin;
	private Float gpsLatitude;
	private Integer disposition;
 
	public ReportCaptureEvent() {
		super();
	}
 
	public Float getUncertainty() {
		return uncertainty;
	}
 
	public void setUncertainty(Float uncertainty) {
		this.uncertainty = uncertainty;
	}
 
	public Float getReplayMax() {
		return replayMax;
	}
 
	public void setReplayMax(Float replayMax) {
		this.replayMax = replayMax;
	}
 
	public Boolean getReferenceGas() {
		return referenceGas;
	}
 
	public void setReferenceGas(Boolean referenceGas) {
		this.referenceGas = referenceGas;
	}
 
	public Float getEpochTime() {
		return epochTime;
	}
 
	public void setEpochTime(Float epochTime) {
		this.epochTime = epochTime;
	}
 
	public Object getShape() {
		return shape;
	}
 
	public void setShape(Object shape) {
		this.shape = shape;
	}
 
	public String getReportId() {
		return reportId;
	}
 
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
 
	public Float getDelta() {
		return delta;
	}
 
	public void setDelta(Float delta) {
		this.delta = delta;
	}
 
	public String getId() {
		return id;
	}
 
	public void setId(String id) {
		this.id = id;
	}
 
	public Float getReplayLMin() {
		return replayLMin;
	}
 
	public void setReplayLMin(Float replayLMin) {
		this.replayLMin = replayLMin;
	}
 
	public Float getGpsLongitude() {
		return gpsLongitude;
	}
 
	public void setGpsLongitude(Float gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}
 
	public Date getDateTime() {
		return datetime;
	}
 
	public void setDateTime(Date date) {
		this.datetime = date;
	}
 
	public String getSurveyId() {
		return surveyId;
	}
 
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}
 
	public Float getConcentration() {
		return concentration;
	}
 
	public void setConcentration(Float concentration) {
		this.concentration = concentration;
	}
 
	public Float getDistance() {
		return distance;
	}
 
	public void setDistance(Float distance) {
		this.distance = distance;
	}
 
	public Float getReplayRMin() {
		return replayRMin;
	}
 
	public void setReplayRMin(Float replayRMin) {
		this.replayRMin = replayRMin;
	}
 
	public Float getGpsLatitude() {
		return gpsLatitude;
	}
 
	public void setGpsLatitude(Float gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}
 
	public Integer getDisposition() {
		return disposition;
	}
 
	public void setDisposition(Integer disposition) {
		this.disposition = disposition;
	}
 
	public static ReportCaptureEvent getReportCaptureEvent(String reportId, String surveyId) {
		ReportCaptureEvent objReportCaptureEvent = new ReportCaptureEvent().get(reportId, surveyId);
		return objReportCaptureEvent;
	}
 
	public ReportCaptureEvent get(String reportId, String surveyId) {
		ReportCaptureEvent objReportCaptureEvent = null;
		
		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+reportId+"_"+surveyId)) {
			objReportCaptureEvent = (ReportCaptureEvent)DBCache.INSTANCE.get(CACHE_KEY+reportId+"_"+surveyId);
		} else {
			String SQL = "SELECT * FROM dbo.[ReportCaptureEvent] WHERE ReportId='" + reportId + "' AND SurveyId='" + surveyId + "'";
			ArrayList<ReportCaptureEvent> objReportCaptureEventList = load(SQL);
			if (objReportCaptureEventList!=null && objReportCaptureEventList.size()>0) {
				objReportCaptureEvent = objReportCaptureEventList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + reportId + "_" + surveyId, objReportCaptureEvent);
			}
		}
		return objReportCaptureEvent;
	}
 
	private static ReportCaptureEvent loadFrom(ResultSet resultSet) {
		ReportCaptureEvent objReportCaptureEvent = new ReportCaptureEvent();
		try {
			objReportCaptureEvent.setUncertainty(resultSet.getFloat("Uncertainty"));
			objReportCaptureEvent.setReplayMax(resultSet.getFloat("ReplayMax"));
			objReportCaptureEvent.setReferenceGas(resultSet.getBoolean("ReferenceGas"));
			objReportCaptureEvent.setEpochTime(resultSet.getFloat("EpochTime"));
			objReportCaptureEvent.setShape(resultSet.getObject("Shape"));
			objReportCaptureEvent.setReportId(resultSet.getString("ReportId"));
			objReportCaptureEvent.setDelta(resultSet.getFloat("Delta"));
			objReportCaptureEvent.setId(resultSet.getString("Id"));
			objReportCaptureEvent.setReplayLMin(resultSet.getFloat("ReplayLMin"));
			objReportCaptureEvent.setGpsLongitude(resultSet.getFloat("GpsLongitude"));
			objReportCaptureEvent.setDateTime(resultSet.getDate("DateTime"));
			objReportCaptureEvent.setSurveyId(resultSet.getString("SurveyId"));
			objReportCaptureEvent.setConcentration(resultSet.getFloat("Concentration"));
			objReportCaptureEvent.setDistance(resultSet.getFloat("Distance"));
			objReportCaptureEvent.setReplayRMin(resultSet.getFloat("ReplayRMin"));
			objReportCaptureEvent.setGpsLatitude(resultSet.getFloat("GpsLatitude"));
			objReportCaptureEvent.setDisposition(resultSet.getInt("Disposition"));
		} catch (SQLException e) {
			Log.error("Class ReportCaptureEvent | " + e.toString());
		}

		return objReportCaptureEvent;
	}
	
	public ArrayList<ReportCaptureEvent> getAll() {
		String SQL = "SELECT * FROM dbo.[ReportCaptureEvent]";
		return load(SQL);
	}
 
	public ArrayList<ReportCaptureEvent> load(String SQL) {
		ArrayList<ReportCaptureEvent> objReportCaptureEventList = new ArrayList<ReportCaptureEvent>();
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);
			
			while (resultSet.next()) {
				ReportCaptureEvent objReportCaptureEvent = loadFrom(resultSet);
				objReportCaptureEventList.add(objReportCaptureEvent);
				
				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objReportCaptureEvent.getReportId(), objReportCaptureEvent);
			}
			
		} catch (SQLException e) {
			Log.error(e.toString());
		}
		
		return objReportCaptureEventList;
	}
}
