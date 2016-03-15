package surveyor.dataaccess.source;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import common.source.Log;
 
public class CaptureEvent extends BaseEntity {
	private static final String CACHE_KEY = "CAPTUREEVENT.";
 
	private Float uncertainty;
	private Object shape;
	private Boolean referenceGas;
	private Float replayMax;
	private String analyzerId;
	private Float replayRMin;
	private Float delta;
	private Date dateTime;
	private String id;
	private Float replayLMin;
	private Float gpsLongitude;
	private String surveyId;
	private Float concentration;
	private Float distance;
	private Float gpsLatitude;
	private Integer disposition;
	private Float epochTime;
 
	public CaptureEvent() {
		super();
	}
 
	public Float getUncertainty() {
		return uncertainty;
	}
 
	public void setUncertainty(Float uncertainty) {
		this.uncertainty = uncertainty;
	}
 
	public Object getShape() {
		return shape;
	}
 
	public void setShape(Object shape) {
		this.shape = shape;
	}
 
	public Boolean getReferenceGas() {
		return referenceGas;
	}
 
	public void setReferenceGas(Boolean referenceGas) {
		this.referenceGas = referenceGas;
	}
 
	public Float getReplayMax() {
		return replayMax;
	}
 
	public void setReplayMax(Float replayMax) {
		this.replayMax = replayMax;
	}
 
	public String getAnalyzerId() {
		return analyzerId;
	}
 
	public void setAnalyzerId(String analyzerId) {
		this.analyzerId = analyzerId;
	}
 
	public Float getReplayRMin() {
		return replayRMin;
	}
 
	public void setReplayRMin(Float replayRMin) {
		this.replayRMin = replayRMin;
	}
 
	public Float getDelta() {
		return delta;
	}
 
	public void setDelta(Float delta) {
		this.delta = delta;
	}
 
	public Date getDateTime() {
		return dateTime;
	}
 
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
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
 
	public Float getEpochTime() {
		return epochTime;
	}
 
	public void setEpochTime(Float epochTime) {
		this.epochTime = epochTime;
	}
 
	public static CaptureEvent getCaptureEvent(String surveyId) {
		CaptureEvent objCaptureEvent = new CaptureEvent().get(surveyId);
		return objCaptureEvent;
	}
 
	public CaptureEvent get(String surveyId) {
		CaptureEvent objCaptureEvent = null;
		
		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+surveyId)) {
			objCaptureEvent = (CaptureEvent)DBCache.INSTANCE.get(CACHE_KEY+surveyId);
		} else {
			String SQL = "SELECT * FROM dbo.[CaptureEvent] WHERE SurveyId='" + surveyId + "'";
			ArrayList<CaptureEvent> objCaptureEventList = load(SQL);
			if (objCaptureEventList!=null && objCaptureEventList.size()>0) {
				objCaptureEvent = objCaptureEventList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + surveyId, objCaptureEvent);
			}
		}
		return objCaptureEvent;
	}

	public ArrayList<CaptureEvent> getAll(String surveyId) {
		ArrayList<CaptureEvent> objCaptureEventList = null;
		
		String SQL = "SELECT * FROM dbo.[CaptureEvent] WHERE SurveyId='" + surveyId + "'";
		objCaptureEventList = load(SQL);
		return objCaptureEventList;
	}

	private static CaptureEvent loadFrom(ResultSet resultSet) {
		CaptureEvent objCaptureEvent = new CaptureEvent();
		try {
			objCaptureEvent.setUncertainty(resultSet.getFloat("Uncertainty"));
			objCaptureEvent.setShape(resultSet.getObject("Shape"));
			objCaptureEvent.setReferenceGas(resultSet.getBoolean("ReferenceGas"));
			objCaptureEvent.setReplayMax(resultSet.getFloat("ReplayMax"));
			objCaptureEvent.setAnalyzerId(resultSet.getString("AnalyzerId"));
			objCaptureEvent.setReplayRMin(resultSet.getFloat("ReplayRMin"));
			objCaptureEvent.setDelta(resultSet.getFloat("Delta"));
			objCaptureEvent.setDateTime(resultSet.getDate("DateTime"));
			objCaptureEvent.setId(resultSet.getString("Id"));
			objCaptureEvent.setReplayLMin(resultSet.getFloat("ReplayLMin"));
			objCaptureEvent.setGpsLongitude(resultSet.getFloat("GpsLongitude"));
			objCaptureEvent.setSurveyId(resultSet.getString("SurveyId"));
			objCaptureEvent.setConcentration(resultSet.getFloat("Concentration"));
			objCaptureEvent.setDistance(resultSet.getFloat("Distance"));
			objCaptureEvent.setGpsLatitude(resultSet.getFloat("GpsLatitude"));
			objCaptureEvent.setDisposition(resultSet.getInt("Disposition"));
			objCaptureEvent.setEpochTime(resultSet.getFloat("EpochTime"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objCaptureEvent;
	}
	
	public ArrayList<CaptureEvent> getAll() {
		String SQL = "SELECT * FROM dbo.[CaptureEvent]";
		return load(SQL);
	}
 
	public ArrayList<CaptureEvent> load(String SQL) {
		ArrayList<CaptureEvent> objCaptureEventList = new ArrayList<CaptureEvent>();
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);
			
			while (resultSet.next()) {
				CaptureEvent objCaptureEvent = loadFrom(resultSet);
				objCaptureEventList.add(objCaptureEvent);
				
				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objCaptureEvent.getId(), objCaptureEvent);
			}
			
		} catch (SQLException e) {
			Log.error("Class CaptureEvent | " + e.toString());
		}
		
		return objCaptureEventList;
	}
}
