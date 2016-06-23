package surveyor.dataaccess.source;

import static surveyor.scommon.source.SurveyorConstants.PICADMNSTDTAG2;
import static surveyor.scommon.source.SurveyorConstants.SQAPICLOC4SURANA;
import static surveyor.scommon.source.SurveyorConstants.RSUVMODESTD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import common.source.Log;

public class Peak extends BaseEntity {
	private static final String CACHE_KEY = "PEAK.";

	private Object position;
	private float windSpeedEast;
	private String surveyModeTypeId;
	private Float carSpeedEast;
	private float cH4;
	private float minor;
	private float windDirectionStdDev;
	private float epochTime;
	private Object passedAutoThreshold;
	private float carSpeedNorth;
	private float lisaOpeningAngle;
	private float windSpeedNorth;
	private float sigma;
	private float gpsLatitude;
	private float lisaBearing;
	private float gpsLongitude;
	private String surveyId;
	private float distance;
	private Object lisa;
	private float amplitude;
	private float carBearing;
	private float major;
	private String analyzerId;


	public Peak() {
		super();
	}

	@Override
	public String toString() {
		return (this.getAnalyzerId().toString()).concat("|")
				.concat(this.getSurveyId().toString()).concat("|")
				.concat(String.valueOf(this.getCH4())).concat("|")
				.concat(String.valueOf(this.getAmplitude())).concat("|")
				.concat(String.valueOf(this.getLisa())).concat("|")
				.concat(this.getWindSpeedEast().toString()).concat("|")
				.concat(this.getWindSpeedNorth().toString()).concat("|")
				.concat(this.getGpsLatitude().toString()).concat("|")
				.concat(this.getGpsLongitude().toString()).concat("|")
				.concat(this.getEpochTime().toString());
	}
	
	public float getCH4() {
		return cH4;
	}

	public void setCH4(float cH4) {
		this.cH4 = cH4;
	}

	public Float getSigma() {
		return sigma;
	}

	public void setSigma(Float sigma) {
		this.sigma = sigma;
	}

	public String getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}

	public String getAnalyzerId() {
		return analyzerId;
	}

	public void setAnalyzerId(String analyzerId) {
		this.analyzerId = analyzerId;
	}

	public String getSurveyModeTypeId() {
		return surveyModeTypeId;
	}

	public void setSurveyModeTypeId(String surveyModeTypeId) {
		this.surveyModeTypeId = surveyModeTypeId;
	}

	public Float getCarBearing() {
		return carBearing;
	}

	public void setCarBearing(Float carBearing) {
		this.carBearing = carBearing;
	}

	public Float getWindSpeedNorth() {
		return windSpeedNorth;
	}

	public void setWindSpeedNorth(Float windSpeedNorth) {
		this.windSpeedNorth = windSpeedNorth;
	}

	public Float getGpsLatitude() {
		return gpsLatitude;
	}

	public void setGpsLatitude(Float gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}

	public Float getDistance() {
		return distance;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}

	public Float getCarSpeedEast() {
		return carSpeedEast;
	}

	public void setCarSpeedEast(Float carSpeedEast) {
		this.carSpeedEast = carSpeedEast;
	}

	public Object getPassedAutoThreshold() {
		return passedAutoThreshold;
	}

	public void setPassedAutoThreshold(Object passedAutoThreshold) {
		this.passedAutoThreshold = passedAutoThreshold;
	}

	public Float getWindDirectionStdDev() {
		return windDirectionStdDev;
	}

	public void setWindDirectionStdDev(Float windDirectionStdDev) {
		this.windDirectionStdDev = windDirectionStdDev;
	}

	public Float getGpsLongitude() {
		return gpsLongitude;
	}

	public void setGpsLongitude(Float gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}

	public Float getWindSpeedEast() {
		return windSpeedEast;
	}

	public void setWindSpeedEast(Float windSpeedEast) {
		this.windSpeedEast = windSpeedEast;
	}

	public Float getCarSpeedNorth() {
		return carSpeedNorth;
	}

	public void setCarSpeedNorth(Float carSpeedNorth) {
		this.carSpeedNorth = carSpeedNorth;
	}

	public Object getPosition() {
		return position;
	}

	public void setPosition(Object position) {
		this.position = position;
	}

	public Float getLisaOpeningAngle() {
		return lisaOpeningAngle;
	}

	public void setLisaOpeningAngle(Float lisaOpeningAngle) {
		this.lisaOpeningAngle = lisaOpeningAngle;
	}

	public Float getMajor() {
		return major;
	}

	public void setMajor(Float major) {
		this.major = major;
	}

	public Object getLisa() {
		return lisa;
	}

	public void setLisa(Object lisa) {
		this.lisa = lisa;
	}

	public Float getMinor() {
		return minor;
	}

	public void setMinor(Float minor) {
		this.minor = minor;
	}

	public Float getLisaBearing() {
		return lisaBearing;
	}

	public void setLisaBearing(Float lisaBearing) {
		this.lisaBearing = lisaBearing;
	}

	public Float getAmplitude() {
		return amplitude;
	}

	public void setAmplitude(Float amplitude) {
		this.amplitude = amplitude;
	}

	public Float getEpochTime() {
		return epochTime;
	}

	public void setEpochTime(Float epochTime) {
		this.epochTime = epochTime;
	}

	public static List<Peak>  getPeaks(String  analyzerId, Double startEpochTime, Double endEpochTime, String surveyModeTypeId) {
		Peak objPeak = new Peak();
		String SQL = "SELECT * FROM dbo.[Peak] WHERE AnalyzerId='" + analyzerId + "' AND EpochTime >= " + startEpochTime + " AND EpochTime <= " + endEpochTime + " AND SurveyModeTypeId= '" + surveyModeTypeId + "'";
		return objPeak.load(SQL);
	}

	@SuppressWarnings("unchecked")
	public static List<Peak> getPeaks(String tag, String analyzer, String mode) {
		Peak objPeak = new Peak();
		List<Peak> objPeakList = new ArrayList<Peak>();

		Survey objSurvey = Survey.getSurvey(tag);
		String surveyId = objSurvey.getId();
		Analyzer objAnalyzer = Analyzer.getAnalyzerBySerialNumber(analyzer);
		String analyzerId = objAnalyzer.getId().toString();
		
		SurveyModeType objSurveyModeType = SurveyModeType.getSurveyModeTypeByDescription(mode);
		String surveyModeTypeId = objSurveyModeType.getId();
		
		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY + analyzerId + "_" + surveyId+ "_" + surveyModeTypeId)) {
			objPeakList = (List<Peak>)DBCache.INSTANCE.get(CACHE_KEY + analyzerId + "_" + surveyId +  "_" + surveyModeTypeId);
		} 
		else {
			String SQL = "SELECT * FROM dbo.[Peak] WHERE AnalyzerId='" + analyzerId + "' AND SurveyId = '" + surveyId + "' AND SurveyModeTypeId= '" + surveyModeTypeId + "'";
			objPeakList = objPeak.load(SQL);
			if (objPeakList!=null && objPeakList.size()>0)
			{
				DBCache.INSTANCE.set(CACHE_KEY + analyzerId + "_" + surveyId + "_" + surveyModeTypeId, objPeakList);
				
			}
		}
		return objPeakList;
	}

	public Peak getFirst(String  analyzerId, Double startEpochTime, Double endEpochTime, String surveyModeTypeId) {
		Peak objPeak = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY + analyzerId + "_" + startEpochTime + "_" + endEpochTime + "_" + surveyModeTypeId)) {
			objPeak = (Peak)DBCache.INSTANCE.get(CACHE_KEY + analyzerId + "_" + startEpochTime + "_" + endEpochTime + "_" + surveyModeTypeId);
		} else {
			String SQL = "SELECT * FROM dbo.[Peak] WHERE AnalyzerId='" + analyzerId + "' AND EpochTime >= " + startEpochTime + " AND EpochTime <= " + endEpochTime + " AND SurveyModeTypeId = '" + surveyModeTypeId + "'";
			ArrayList<Peak> objPeakList = load(SQL);
			if (objPeakList!=null && objPeakList.size()>0) {
				objPeak = objPeakList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + analyzerId + "_" + startEpochTime + "_" + endEpochTime + "_" + surveyModeTypeId, objPeak);
			}
		}
		return objPeak;
	}



	private static Peak loadFrom(ResultSet resultSet) {
		Peak objPeak = new Peak();
		try {
			objPeak.setCH4(getFloatColumnValue(resultSet,"CH4"));
			objPeak.setSigma(getFloatColumnValue(resultSet,"Sigma"));
			objPeak.setSurveyId(resultSet.getString("SurveyId"));
			objPeak.setAnalyzerId(resultSet.getString("AnalyzerId"));
			objPeak.setSurveyModeTypeId(resultSet.getString("SurveyModeTypeId"));
			objPeak.setCarBearing(getFloatColumnValue(resultSet,"CarBearing"));
			objPeak.setWindSpeedNorth(getFloatColumnValue(resultSet,"WindSpeedNorth"));
			objPeak.setGpsLatitude(getFloatColumnValue(resultSet,"GpsLatitude"));
			objPeak.setDistance(getFloatColumnValue(resultSet,"Distance"));
			objPeak.setCarSpeedEast(getFloatColumnValue(resultSet,"CarSpeedEast"));
			objPeak.setPassedAutoThreshold(resultSet.getObject("PassedAutoThreshold"));
			objPeak.setWindDirectionStdDev(getFloatColumnValue(resultSet,"WindDirectionStdDev"));
			objPeak.setGpsLongitude(getFloatColumnValue(resultSet,"GpsLongitude"));
			objPeak.setWindSpeedEast(getFloatColumnValue(resultSet,"WindSpeedEast"));
			objPeak.setCarSpeedNorth(getFloatColumnValue(resultSet,"CarSpeedNorth"));
			objPeak.setPosition(resultSet.getObject("Position"));
			objPeak.setLisaOpeningAngle(getFloatColumnValue(resultSet,"LisaOpeningAngle"));
			objPeak.setMajor(getFloatColumnValue(resultSet,"Major"));
			objPeak.setLisa(resultSet.getObject("Lisa"));
			objPeak.setMinor(getFloatColumnValue(resultSet,"Minor"));
			objPeak.setLisaBearing(getFloatColumnValue(resultSet,"LisaBearing"));
			objPeak.setAmplitude(getFloatColumnValue(resultSet,"Amplitude"));
			objPeak.setEpochTime(getFloatColumnValue(resultSet,"EpochTime"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objPeak;
	}

	public ArrayList<Peak> getAll() {
		String SQL = "SELECT * FROM dbo.[Peak]";
		return load(SQL);
	}

	public  ArrayList<Peak> load(String SQL) {
		ArrayList<Peak> objPeakList = new ArrayList<Peak>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				Peak objPeak = loadFrom(resultSet);
				objPeakList.add(objPeak);
			}

		} catch (SQLException e) {
			Log.error("Class Peak | " + e.toString());
		}

		return objPeakList;
	}
	
	public boolean equalsTo(Map<String, String> map) {
		float ch4 = Float.valueOf(map.get("CH4"));				
		float epochTime = Float.valueOf(map.get("EPOCH_TIME"));
		float sigma = Float.valueOf(map.get("SIGMA"));
		float amplitude= Float.valueOf(map.get("AMPLITUDE"));
		float wind_dir_sdev= Float.valueOf(map.get("WIND_DIR_SDEV"));
		float wind_n= Float.valueOf(map.get("WIND_N"));
		float wind_e= Float.valueOf(map.get("WIND_E"));

		
		if(   (Float.compare(this.getEpochTime(), epochTime)==0)   && (Float.compare(this.getCH4(), ch4)==0)
				&& (Float.compare(this.getSigma(), sigma)==0) && (Float.compare(this.getAmplitude(), amplitude)==0)
				&& (Float.compare(this.getWindDirectionStdDev(), wind_dir_sdev)==0) && (Float.compare(this.getWindSpeedNorth(), wind_n)==0)
				&& (Float.compare(this.getWindSpeedEast(), wind_e)==0) ){
			return true;

		}

	return false;
	}

}
