package surveyor.dataaccess.source;

import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG2;
import static surveyor.scommon.source.SurveyorConstants.PICDFADMIN;
import static surveyor.scommon.source.SurveyorConstants.SQAPICLOC0SURANA;
import static surveyor.scommon.source.SurveyorConstants.SQAPICLOC4SUR;
import static surveyor.scommon.source.SurveyorConstants.SQAPICLOC4SURANA;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import common.source.Log;

public class CaptureEvent extends BaseEntity {
	private static final String CACHE_KEY = "CAPTUREEVENT.";

	private float uncertainty;
	private Object shape;
	private Boolean referenceGas;
	private float replayMax;
	private String analyzerId;
	private float replayRMin;
	private float delta;
	private Date dateTime;
	private String id;
	private float replayLMin;
	private float gpsLongitude;
	private String surveyId;
	private float concentration;
	private float distance;
	private float gpsLatitude;
	private Integer disposition;
	private float epochTime;

	public CaptureEvent() {
		super();
	}

	public float getUncertainty() {
		return uncertainty;
	}

	public void setUncertainty(float uncertainty) {
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

	public float getReplayMax() {
		return replayMax;
	}

	public void setReplayMax(float replayMax) {
		this.replayMax = replayMax;
	}

	public String getAnalyzerId() {
		return analyzerId;
	}

	public void setAnalyzerId(String analyzerId) {
		this.analyzerId = analyzerId;
	}

	public float getReplayRMin() {
		return replayRMin;
	}

	public void setReplayRMin(float replayRMin) {
		this.replayRMin = replayRMin;
	}

	public float getDelta() {
		return delta;
	}

	public void setDelta(float delta) {
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

	public float getReplayLMin() {
		return replayLMin;
	}

	public void setReplayLMin(float replayLMin) {
		this.replayLMin = replayLMin;
	}

	public float getGpsLongitude() {
		return gpsLongitude;
	}

	public void setGpsLongitude(float gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}

	public String getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}

	public float getConcentration() {
		return concentration;
	}

	public void setConcentration(float concentration) {
		this.concentration = concentration;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public float getGpsLatitude() {
		return gpsLatitude;
	}

	public void setGpsLatitude(float gpsLatitude) {
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
			objCaptureEvent.setUncertainty(getFloatColumnValue(resultSet,"Uncertainty"));
			objCaptureEvent.setShape(resultSet.getObject("Shape"));
			objCaptureEvent.setReferenceGas(resultSet.getBoolean("ReferenceGas"));
			objCaptureEvent.setReplayMax(getFloatColumnValue(resultSet,"ReplayMax"));
			objCaptureEvent.setAnalyzerId(resultSet.getString("AnalyzerId"));
			objCaptureEvent.setReplayRMin(getFloatColumnValue(resultSet,"ReplayRMin"));
			objCaptureEvent.setDelta(getFloatColumnValue(resultSet,"Delta"));
			objCaptureEvent.setDateTime(getDateColumnValue(resultSet,"DateTime"));
			objCaptureEvent.setId(resultSet.getString("Id"));
			objCaptureEvent.setReplayLMin(getFloatColumnValue(resultSet,"ReplayLMin"));
			objCaptureEvent.setGpsLongitude(getFloatColumnValue(resultSet,"GpsLongitude"));
			objCaptureEvent.setSurveyId(resultSet.getString("SurveyId"));
			objCaptureEvent.setConcentration(getFloatColumnValue(resultSet,"Concentration"));
			objCaptureEvent.setDistance(getFloatColumnValue(resultSet,"Distance"));
			objCaptureEvent.setGpsLatitude(getFloatColumnValue(resultSet,"GpsLatitude"));
			objCaptureEvent.setDisposition(getIntColumnValue(resultSet,"Disposition"));
			objCaptureEvent.setEpochTime(getFloatColumnValue(resultSet,"EpochTime"));
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

	@SuppressWarnings("unchecked")
	public static List<CaptureEvent> getCaptureEvent(String tag, String analyzer) {
		CaptureEvent objCaptureEvent = new CaptureEvent();
		List<CaptureEvent> objCaptureEventList = new ArrayList<CaptureEvent>();
		
		
		Survey objSurvey = Survey.getSurvey(tag);
		String surveyId = objSurvey.getId();
		
		Analyzer objAnalyzer = Analyzer.getAnalyzerBySerialNumber(analyzer);
		String analyzerId = objAnalyzer.getId().toString();
	
		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY + surveyId+ "_" + analyzerId)) {
			objCaptureEventList = (List<CaptureEvent>)DBCache.INSTANCE.get(CACHE_KEY + surveyId +  "_" + analyzerId);
		} 
		else {
			String SQL = "SELECT * FROM dbo.[CaptureEvent] WHERE SurveyId = '" + surveyId + "' AND AnalyzerId= '" + analyzerId + "'";

			objCaptureEventList = objCaptureEvent.load(SQL);
			if (objCaptureEventList!=null && objCaptureEventList.size()>0)
			{
				DBCache.INSTANCE.set(CACHE_KEY + surveyId + "_" + analyzerId, objCaptureEventList);
			}
		}
		return objCaptureEventList;
	}

	public boolean equalsTo(Map<String, String> map) {
		float epochTime = Float.valueOf(map.get("EPOCH_TIME"));
		float distance = Float.valueOf(map.get("DISTANCE"));
		float gps_abs_long= Float.valueOf(map.get("GPS_ABS_LONG"));
		float gps_abs_lat= Float.valueOf(map.get("GPS_ABS_LAT"));
		float concentration= Float.valueOf(map.get("CONC"));
		float delta= Float.valueOf(map.get("DELTA"));
		float uncertainty = Float.valueOf(map.get("UNCERTAINTY"));
		float reply_max = Float.valueOf(map.get("REPLAY_MAX"));
		float reply_rmin = Float.valueOf(map.get("REPLAY_RMIN"));				
		float reply_lmin = Float.valueOf(map.get("REPLAY_LMIN"));				
		float disposition = Float.valueOf(map.get("DISPOSITION"));
		
		if(   (Float.compare(this.getEpochTime(), epochTime)==0) && (Float.compare(this.getDistance(), distance)==0)
				&& (Float.compare(this.getGpsLongitude(), gps_abs_long)==0)  && (Float.compare(this.getGpsLatitude(), gps_abs_lat)==0)
				&& (Float.compare(this.getConcentration(), concentration)==0) && (Float.compare(this.getDelta(), delta)==0)
				&& (Float.compare(this.getUncertainty(), uncertainty)==0) && (Float.compare(this.getReplayMax(), reply_max)==0)
				&& (Float.compare(this.getReplayRMin(), reply_rmin)==0) && (Float.compare(this.getReplayLMin(), reply_lmin)==0)
				&& (Float.compare(this.getDisposition(), disposition)==0)   ){
			return true;
		}

		return false;
	}

}