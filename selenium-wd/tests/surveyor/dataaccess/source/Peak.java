package surveyor.dataaccess.source;

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
	private Float windSpeedEast;
	private String surveyModeTypeId;
	private Float carSpeedEast;
	private Float cH4;
	private Float minor;
	private Float windDirectionStdDev;
	private Float epochTime;
	private Object passedAutoThreshold;
	private Float carSpeedNorth;
	private Float lisaOpeningAngle;
	private Float windSpeedNorth;
	private Float sigma;
	private Float gpsLatitude;
	private Float lisaBearing;
	private Float gpsLongitude;
	private String surveyId;
	private Float distance;
	private Object lisa;
	private Float amplitude;
	private Float carBearing;
	private Float major;
	private String analyzerId;
	
	
	public Peak() {
		super();
	}

	public Float getCH4() {
		return cH4;
	}

	public void setCH4(Float cH4) {
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

	public static List <Peak> getPeaks(String  analyzerId, Double startEpochTime, Double endEpochTime, String surveyModeTypeId) {
		Peak objPeak = new Peak();
		String SQL = "SELECT * FROM dbo.[Peak] WHERE AnalyzerId='" + analyzerId + "' AND EpochTime >= " + startEpochTime + " AND EpochTime <= " + endEpochTime + " AND SurveyModeTypeId= '" + surveyModeTypeId + "'";
		return objPeak.load(SQL);
	}

	public static boolean isRecordExistsInDB(Map<String,String> record){
		Peak objPeak = new Peak();
		boolean doesExists=false;
		
		String SQL = "SELECT* FROM dbo.[Peak] WHERE ABS(CH4 - " + Double.valueOf(record.get("CH4")) + ") > 0.001 "
				+ " AND ABS(Sigma - " + Double.valueOf(record.get("SIGMA")) + ") > 0.001 AND "
						+ " ABS(Amplitude - "+Double.valueOf(record.get("AMPLITUDE"))+") > 0.001 AND "
								+ " ABS(WindSpeedEast - "+Double.valueOf(record.get("WIND_E")) +  ") > 0.001 AND "
										+ " ABS(WindSpeedNorth - "+Double.valueOf(record.get("WIND_N")) + ") > 0.001 AND "
										+ " ABS(EpochTime - "+Double.valueOf(record.get("EPOCH_TIME")) + ") > 0.001";
		
		System.out.println(">>>>" + SQL);
		if (objPeak.load(SQL).size() > 0){
			doesExists = true;
		}
			
		return doesExists;
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
			objPeak.setCH4(resultSet.getFloat("CH4"));
			objPeak.setSigma(resultSet.getFloat("Sigma"));
			objPeak.setSurveyId(resultSet.getString("SurveyId"));
			objPeak.setAnalyzerId(resultSet.getString("AnalyzerId"));
			objPeak.setSurveyModeTypeId(resultSet.getString("SurveyModeTypeId"));
			objPeak.setCarBearing(resultSet.getFloat("CarBearing"));
			objPeak.setWindSpeedNorth(resultSet.getFloat("WindSpeedNorth"));
			objPeak.setGpsLatitude(resultSet.getFloat("GpsLatitude"));
			objPeak.setDistance(resultSet.getFloat("Distance"));
			objPeak.setCarSpeedEast(resultSet.getFloat("CarSpeedEast"));
			objPeak.setPassedAutoThreshold(resultSet.getObject("PassedAutoThreshold"));
			objPeak.setWindDirectionStdDev(resultSet.getFloat("WindDirectionStdDev"));
			objPeak.setGpsLongitude(resultSet.getFloat("GpsLongitude"));
			objPeak.setWindSpeedEast(resultSet.getFloat("WindSpeedEast"));
			objPeak.setCarSpeedNorth(resultSet.getFloat("CarSpeedNorth"));
			objPeak.setPosition(resultSet.getObject("Position"));
			objPeak.setLisaOpeningAngle(resultSet.getFloat("LisaOpeningAngle"));
			objPeak.setMajor(resultSet.getFloat("Major"));
			objPeak.setLisa(resultSet.getObject("Lisa"));
			objPeak.setMinor(resultSet.getFloat("Minor"));
			objPeak.setLisaBearing(resultSet.getFloat("LisaBearing"));
			objPeak.setAmplitude(resultSet.getFloat("Amplitude"));
			objPeak.setEpochTime(resultSet.getFloat("EpochTime"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objPeak;
	}

	public ArrayList<Peak> getAll() {
		String SQL = "SELECT * FROM dbo.[Peak]";
		return load(SQL);
	}

	public ArrayList<Peak> load(String SQL) {
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
}
