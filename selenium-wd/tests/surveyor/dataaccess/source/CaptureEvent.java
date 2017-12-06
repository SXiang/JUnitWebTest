package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import common.source.Log;

@SuppressWarnings("rawtypes")
public class CaptureEvent extends BaseEntity implements Comparable {
	public static final String CACHE_KEY = "CAPTUREEVENT.";

	private String id;
	private float uncertainty;
	private Boolean captureType;
	private float replayLMin;
	private Integer disposition;
	private float ethaneRatioSdev;
	private String analyzerId;
	private float ethaneRatio;
	private String surveyId;
	private Object shape;
	private float distance;
	private float epochTime;
	private float replayRMin;
	private float replayMax;
	private float gpsLongitude;
	private float gpsLatitude;
	private float delta;
	private float concentration;
	private float classificationConfidence;
	private Date dateTime;

	public CaptureEvent() {
		super();
	}

	@Override
	public String toString() {
		return (this.getAnalyzerId().toString()).concat("|")
				.concat(this.getSurveyId().toString()).concat("|")
				.concat(String.valueOf(this.getDistance())).concat("|")
				.concat(String.valueOf(this.getDelta())).concat("|")
				.concat(String.valueOf(this.getGpsLatitude())).concat("|")
				.concat(String.valueOf(this.getGpsLongitude())).concat("|")
				.concat(String.valueOf(this.getConcentration())).concat("|")
				.concat(String.valueOf(this.getClassificationConfidence())).concat("|")
				.concat(this.getDisposition().toString()).concat("|");
	}

	@Override
	public int compareTo(Object other) {
		return Float.compare(this.getEpochTime(), ((CaptureEvent)other).getEpochTime());
	}

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(nullOrUpper(this.getAnalyzerId()))
        		.append(nullOrUpper(this.getSurveyId()))
        		.append(this.getDistance())
        		.append(this.getDelta())
        		.append(this.getGpsLatitude())
        		.append(this.getGpsLongitude())
        		.append(this.getConcentration())
        		.append(this.getClassificationConfidence())
        		.append(this.getDisposition())
        		.toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CaptureEvent) == false) {
            return false;
        }
        CaptureEvent rhs = ((CaptureEvent) other);
        return new EqualsBuilder().append(nullOrUpper(this.getAnalyzerId()), nullOrUpper(rhs.getAnalyzerId()))
        		.append(nullOrUpper(this.getSurveyId()), nullOrUpper(rhs.getSurveyId()))
        		.append(this.getDistance(), rhs.getDistance()).append(this.getDelta(), rhs.getDelta())
        		.append(this.getGpsLatitude(), rhs.getGpsLatitude()).append(this.getGpsLongitude(), rhs.getGpsLongitude())
        		.append(this.getConcentration(), rhs.getConcentration()).append(this.getClassificationConfidence(), rhs.getClassificationConfidence())
        		.append(this.getDisposition(), rhs.getDisposition())
        		.isEquals();
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public float getUncertainty() {
		return uncertainty;
	}

	public void setUncertainty(float uncertainty) {
		this.uncertainty = uncertainty;
	}

	public Boolean getCaptureType() {
		return captureType;
	}

	public void setCaptureType(Boolean captureType) {
		this.captureType = captureType;
	}

	public float getReplayLMin() {
		return replayLMin;
	}

	public void setReplayLMin(float replayLMin) {
		this.replayLMin = replayLMin;
	}

	public Integer getDisposition() {
		return disposition;
	}

	public void setDisposition(Integer disposition) {
		this.disposition = disposition;
	}

	public float getEthaneRatioSdev() {
		return ethaneRatioSdev;
	}

	public void setEthaneRatioSdev(float ethaneRatioSdev) {
		this.ethaneRatioSdev = ethaneRatioSdev;
	}

	public String getAnalyzerId() {
		return analyzerId;
	}

	public void setAnalyzerId(String analyzerId) {
		this.analyzerId = analyzerId;
	}

	public float getEthaneRatio() {
		return ethaneRatio;
	}

	public void setEthaneRatio(float ethaneRatio) {
		this.ethaneRatio = ethaneRatio;
	}

	public String getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}

	public Object getShape() {
		return shape;
	}

	public void setShape(Object shape) {
		this.shape = shape;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public float getEpochTime() {
		return epochTime;
	}

	public void setEpochTime(float epochTime) {
		this.epochTime = epochTime;
	}

	public float getReplayRMin() {
		return replayRMin;
	}

	public void setReplayRMin(float replayRMin) {
		this.replayRMin = replayRMin;
	}

	public float getReplayMax() {
		return replayMax;
	}

	public void setReplayMax(float replayMax) {
		this.replayMax = replayMax;
	}

	public float getGpsLongitude() {
		return gpsLongitude;
	}

	public void setGpsLongitude(float gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}

	public float getGpsLatitude() {
		return gpsLatitude;
	}

	public void setGpsLatitude(float gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}

	public float getDelta() {
		return delta;
	}

	public void setDelta(float delta) {
		this.delta = delta;
	}

	public float getConcentration() {
		return concentration;
	}

	public void setConcentration(float concentration) {
		this.concentration = concentration;
	}

	public float getClassificationConfidence() {
		return classificationConfidence;
	}

	public void setClassificationConfidence(float classificationConfidence) {
		this.classificationConfidence = classificationConfidence;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
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
			String SQL = "SELECT [Id],[AnalyzerId],[EpochTime],[DateTime],[GpsLatitude],[GpsLongitude],CONVERT(VARBINARY(MAX), [Shape]) AS Shape,[Disposition],[Delta],[Concentration],[Uncertainty],[CaptureType],[Distance],[ReplayMax],[ReplayLMin],[ReplayRMin],[SurveyId],[EthaneRatio],[EthaneRatioSdev],[ClassificationConfidence] FROM dbo.[CaptureEvent] WHERE SurveyId='" + surveyId + "'";
			ArrayList<CaptureEvent> objCaptureEventList = load(SQL);
			if (objCaptureEventList!=null && objCaptureEventList.size()>0) {
				objCaptureEvent = objCaptureEventList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + surveyId, objCaptureEvent);
			}
		}
		return objCaptureEvent;
	}

	private static CaptureEvent loadFrom(ResultSet resultSet) {
		CaptureEvent objCaptureEvent = new CaptureEvent();
		try {
			objCaptureEvent.setId(resultSet.getString("Id"));
			objCaptureEvent.setUncertainty(getFloatColumnValue(resultSet,"Uncertainty"));
			objCaptureEvent.setCaptureType(resultSet.getBoolean("CaptureType"));
			objCaptureEvent.setReplayLMin(getFloatColumnValue(resultSet,"ReplayLMin"));
			objCaptureEvent.setDisposition(getIntColumnValue(resultSet,"Disposition"));
			objCaptureEvent.setEthaneRatioSdev(getFloatColumnValue(resultSet,"EthaneRatioSdev"));
			objCaptureEvent.setAnalyzerId(resultSet.getString("AnalyzerId"));
			objCaptureEvent.setEthaneRatio(getFloatColumnValue(resultSet,"EthaneRatio"));
			objCaptureEvent.setSurveyId(resultSet.getString("SurveyId"));
			objCaptureEvent.setShape(resultSet.getString("Shape"));
			objCaptureEvent.setDistance(getFloatColumnValue(resultSet,"Distance"));
			objCaptureEvent.setEpochTime(getFloatColumnValue(resultSet,"EpochTime"));
			objCaptureEvent.setReplayRMin(getFloatColumnValue(resultSet,"ReplayRMin"));
			objCaptureEvent.setReplayMax(getFloatColumnValue(resultSet,"ReplayMax"));
			objCaptureEvent.setGpsLongitude(getFloatColumnValue(resultSet,"GpsLongitude"));
			objCaptureEvent.setGpsLatitude(getFloatColumnValue(resultSet,"GpsLatitude"));
			objCaptureEvent.setDelta(getFloatColumnValue(resultSet,"Delta"));
			objCaptureEvent.setConcentration(getFloatColumnValue(resultSet,"Concentration"));
			objCaptureEvent.setClassificationConfidence(getFloatColumnValue(resultSet,"ClassificationConfidence"));
			objCaptureEvent.setDateTime(getDateColumnValue(resultSet,"DateTime"));
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
		List<Survey> objSurveys = Survey.getSurveys(tag);
		if (objSurveys != null && objSurveys.size()>0) {
			Analyzer objAnalyzer = Analyzer.getAnalyzerBySerialNumber(analyzer);
			String analyzerId = objAnalyzer.getId().toString();
			for (Survey objSurvey : objSurveys) {
				String surveyId = objSurvey.getId();
				if (objSurvey.getAnalyzerId().toString().equalsIgnoreCase(analyzerId)) {
					// Get from cache if present. Else fetch from Database.
					if (DBCache.INSTANCE.containsKey(CACHE_KEY + surveyId+ "_" + analyzerId)) {
						objCaptureEventList = (List<CaptureEvent>)DBCache.INSTANCE.get(CACHE_KEY + surveyId +  "_" + analyzerId);
					}
					else {
						String SQL = "SELECT [Id],[AnalyzerId],[EpochTime],[DateTime],[GpsLatitude],[GpsLongitude],CONVERT(VARBINARY(MAX), [Shape]) AS Shape,[Disposition],[Delta],[Concentration],[Uncertainty],[CaptureType],[Distance],[ReplayMax],[ReplayLMin],[ReplayRMin],[SurveyId],[EthaneRatio],[EthaneRatioSdev],[ClassificationConfidence] FROM dbo.[CaptureEvent] WHERE SurveyId = '" + surveyId + "' AND AnalyzerId= '" + analyzerId + "'";

						objCaptureEventList = objCaptureEvent.load(SQL);
						if (objCaptureEventList!=null && objCaptureEventList.size()>0)
						{
							DBCache.INSTANCE.set(CACHE_KEY + surveyId + "_" + analyzerId, objCaptureEventList);
						}
					}
				}
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
