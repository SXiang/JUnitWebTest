package surveyor.dataaccess.source;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import common.source.Log;
 
public class ReportPeak extends BaseEntity {
	private static final String CACHE_KEY = "REPORTPEAK.";
 
	private Object position;
	private Float windSpeedEast;
	private Float cH4;
	private Float minor;
	private Float lisaOpeningAngle;
	private Object lisa;
	private Date investigationDateTime;
	private Float epochTime;
	private Boolean passedAutoThreshold;
	private String reportId;
	private Boolean isLeakFound;
	private Float carSpeedNorth;
	private String surveyId;
	private String id;
	private Float windDirectionStdDev;
	private Float windSpeedNorth;
	private Boolean isPeakInvestigated;
	private Float sigma;
	private Integer peakNumber;
	private Float lisaBearing;
	private Float gpsLongitude;
	private Float distance;
	private Float carSpeedEast;
	private Float amplitude;
	private Float carBearing;
	private Float major;
	private Float gpsLatitude;
 
	public ReportPeak() {
		super();
	}
 
	public Object getPosition() {
		return position;
	}
 
	public void setPosition(Object position) {
		this.position = position;
	}
 
	public Float getWindSpeedEast() {
		return windSpeedEast;
	}
 
	public void setWindSpeedEast(Float windSpeedEast) {
		this.windSpeedEast = windSpeedEast;
	}
 
	public Float getCH4() {
		return cH4;
	}
 
	public void setCH4(Float cH4) {
		this.cH4 = cH4;
	}
 
	public Float getMinor() {
		return minor;
	}
 
	public void setMinor(Float minor) {
		this.minor = minor;
	}
 
	public Float getLisaOpeningAngle() {
		return lisaOpeningAngle;
	}
 
	public void setLisaOpeningAngle(Float lisaOpeningAngle) {
		this.lisaOpeningAngle = lisaOpeningAngle;
	}
 
	public Object getLisa() {
		return lisa;
	}
 
	public void setLisa(Object lisa) {
		this.lisa = lisa;
	}
 
	public Date getInvestigationDateTime() {
		return investigationDateTime;
	}
 
	public void setInvestigationDateTime(Date investigationDateTime) {
		this.investigationDateTime = investigationDateTime;
	}
 
	public Float getEpochTime() {
		return epochTime;
	}
 
	public void setEpochTime(Float epochTime) {
		this.epochTime = epochTime;
	}
 
	public Boolean getPassedAutoThreshold() {
		return passedAutoThreshold;
	}
 
	public void setPassedAutoThreshold(Boolean passedAutoThreshold) {
		this.passedAutoThreshold = passedAutoThreshold;
	}
 
	public String getReportId() {
		return reportId;
	}
 
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
 
	public Boolean getIsLeakFound() {
		return isLeakFound;
	}
 
	public void setIsLeakFound(Boolean isLeakFound) {
		this.isLeakFound = isLeakFound;
	}
 
	public Float getCarSpeedNorth() {
		return carSpeedNorth;
	}
 
	public void setCarSpeedNorth(Float carSpeedNorth) {
		this.carSpeedNorth = carSpeedNorth;
	}
 
	public String getSurveyId() {
		return surveyId;
	}
 
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}
 
	public String getId() {
		return id;
	}
 
	public void setId(String id) {
		this.id = id;
	}
 
	public Float getWindDirectionStdDev() {
		return windDirectionStdDev;
	}
 
	public void setWindDirectionStdDev(Float windDirectionStdDev) {
		this.windDirectionStdDev = windDirectionStdDev;
	}
 
	public Float getWindSpeedNorth() {
		return windSpeedNorth;
	}
 
	public void setWindSpeedNorth(Float windSpeedNorth) {
		this.windSpeedNorth = windSpeedNorth;
	}
 
	public Boolean getIsPeakInvestigated() {
		return isPeakInvestigated;
	}
 
	public void setIsPeakInvestigated(Boolean isPeakInvestigated) {
		this.isPeakInvestigated = isPeakInvestigated;
	}
 
	public Float getSigma() {
		return sigma;
	}
 
	public void setSigma(Float sigma) {
		this.sigma = sigma;
	}
 
	public Integer getPeakNumber() {
		return peakNumber;
	}
 
	public void setPeakNumber(Integer peakNumber) {
		this.peakNumber = peakNumber;
	}
 
	public Float getLisaBearing() {
		return lisaBearing;
	}
 
	public void setLisaBearing(Float lisaBearing) {
		this.lisaBearing = lisaBearing;
	}
 
	public Float getGpsLongitude() {
		return gpsLongitude;
	}
 
	public void setGpsLongitude(Float gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
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
 
	public Float getAmplitude() {
		return amplitude;
	}
 
	public void setAmplitude(Float amplitude) {
		this.amplitude = amplitude;
	}
 
	public Float getCarBearing() {
		return carBearing;
	}
 
	public void setCarBearing(Float carBearing) {
		this.carBearing = carBearing;
	}
 
	public Float getMajor() {
		return major;
	}
 
	public void setMajor(Float major) {
		this.major = major;
	}
 
	public Float getGpsLatitude() {
		return gpsLatitude;
	}
 
	public void setGpsLatitude(Float gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}
 
	public static ReportPeak getReportPeak(Integer peakNumber) {
		ReportPeak objReportPeak = new ReportPeak().get(peakNumber);
		return objReportPeak;
	}
 
	public ReportPeak get(Integer peakNumber) {
		ReportPeak objReportPeak = null;
		
		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+peakNumber)) {
			objReportPeak = (ReportPeak)DBCache.INSTANCE.get(CACHE_KEY+peakNumber);
		} else {
			String SQL = "SELECT * FROM dbo.[ReportPeak] WHERE PeakNumber=" + peakNumber;
			ArrayList<ReportPeak> objReportPeakList = load(SQL);
			if (objReportPeakList!=null && objReportPeakList.size()>0) {
				objReportPeak = objReportPeakList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + peakNumber, objReportPeak);
			}
		}
		return objReportPeak;
	}
 
	private static ReportPeak loadFrom(ResultSet resultSet) {
		ReportPeak objReportPeak = new ReportPeak();
		try {
			objReportPeak.setPosition(resultSet.getObject("Position"));
			objReportPeak.setWindSpeedEast(resultSet.getFloat("WindSpeedEast"));
			objReportPeak.setCH4(resultSet.getFloat("CH4"));
			objReportPeak.setMinor(resultSet.getFloat("Minor"));
			objReportPeak.setLisaOpeningAngle(resultSet.getFloat("LisaOpeningAngle"));
			objReportPeak.setLisa(resultSet.getObject("Lisa"));
			objReportPeak.setInvestigationDateTime(resultSet.getDate("InvestigationDateTime"));
			objReportPeak.setEpochTime(resultSet.getFloat("EpochTime"));
			objReportPeak.setPassedAutoThreshold(resultSet.getBoolean("PassedAutoThreshold"));
			objReportPeak.setReportId(resultSet.getString("ReportId"));
			objReportPeak.setIsLeakFound(resultSet.getBoolean("IsLeakFound"));
			objReportPeak.setCarSpeedNorth(resultSet.getFloat("CarSpeedNorth"));
			objReportPeak.setSurveyId(resultSet.getString("SurveyId"));
			objReportPeak.setId(resultSet.getString("Id"));
			objReportPeak.setWindDirectionStdDev(resultSet.getFloat("WindDirectionStdDev"));
			objReportPeak.setWindSpeedNorth(resultSet.getFloat("WindSpeedNorth"));
			objReportPeak.setIsPeakInvestigated(resultSet.getBoolean("IsPeakInvestigated"));
			objReportPeak.setSigma(resultSet.getFloat("Sigma"));
			objReportPeak.setPeakNumber(resultSet.getInt("PeakNumber"));
			objReportPeak.setLisaBearing(resultSet.getFloat("LisaBearing"));
			objReportPeak.setGpsLongitude(resultSet.getFloat("GpsLongitude"));
			objReportPeak.setDistance(resultSet.getFloat("Distance"));
			objReportPeak.setCarSpeedEast(resultSet.getFloat("CarSpeedEast"));
			objReportPeak.setAmplitude(resultSet.getFloat("Amplitude"));
			objReportPeak.setCarBearing(resultSet.getFloat("CarBearing"));
			objReportPeak.setMajor(resultSet.getFloat("Major"));
			objReportPeak.setGpsLatitude(resultSet.getFloat("GpsLatitude"));
		} catch (SQLException e) {
			Log.error("Class ReportPeak | " + e.toString());
		}

		return objReportPeak;
	}
	
	public ArrayList<ReportPeak> getAll() {
		String SQL = "SELECT * FROM dbo.[ReportPeak]";
		return load(SQL);
	}
 
	public ArrayList<ReportPeak> load(String SQL) {
		ArrayList<ReportPeak> objReportPeakList = new ArrayList<ReportPeak>();
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);
			
			while (resultSet.next()) {
				ReportPeak objReportPeak = loadFrom(resultSet);
				objReportPeakList.add(objReportPeak);
				
				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objReportPeak.getPeakNumber(), objReportPeak);
			}
			
		} catch (SQLException e) {
			Log.error(e.toString());
		}
		
		return objReportPeakList;
	}
}
