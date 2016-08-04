package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import common.source.Log;

public class Measurement extends BaseEntity {
	private static final String CACHE_KEY = "MEASUREMENT.";
	private static final String CACHE_KEY_FIRST = "MEASUREMENT.FIRST";
	private static final String CACHE_KEY_ALL = "MEASUREMENT.ALL";

	private Float windSpeedLongitudinal;
	private Short gpsFit;
	private Integer peripheralStatus;
	private Date createDate;
	private Float windSpeedEast;
	private Object shape;
	private Float mobileFlowRate;
	private Float cH4;
	private Float windDirectionStdDev;
	private Float epochTime;
	private Object analyzerId;
	private Integer instrumentStatus;
	private Float deltaCH4;
	private Float carSpeedNorth;
	private Float carSpeedEast;
	private Float cavityPressure;
	private Float warmBoxTemperature;
	private Integer analyzerMode;
	private Float windSpeedNorth;
	private Integer analyzerStatus;
	private Float valveMask;
	private Float cO2;
	private Float gpsLongitude;
	private Float h2OPercent;
	private Float windSpeedLateral;
	private Float weatherStationRotation;
	private Integer species;
	private Float gpsLatitude;
	private Float hotBoxTemperature;
	private Boolean chemDetect;
	
	public Measurement() {
		super();
	}

	@Override
	public String toString() {
		return ("AnalyzerId=" + this.getAnalyzerId().toString().concat("|")
				.concat("CH4=" + this.getCH4().toString()).concat("|")
				.concat("DeltaCH4=" + this.getDeltaCH4().toString()).concat("|")
				.concat("WindSpeedEast=" + this.getWindSpeedEast().toString()).concat("|")
				.concat("WindSpeedLateral=" + this.getWindSpeedLateral().toString()).concat("|")
				.concat("WindSpeedLongitudinal=" + this.getWindSpeedLongitudinal().toString()).concat("|")
				.concat("WindSpeedNorth=" + this.getWindSpeedNorth().toString()).concat("|")
				.concat("GpsFit=" + this.getGpsFit().toString()).concat("|")
				.concat("GpsLatitude=" + this.getGpsLatitude().toString()).concat("|")
				.concat("getGpsLongitude=" + this.getGpsLongitude().toString()).concat("|")
				.concat("getEpochTime=" + this.getEpochTime().toString()));
	}

	public void purgeCache() {
		DBCache.INSTANCE.purgeCache(CACHE_KEY_FIRST);
		DBCache.INSTANCE.purgeCache(CACHE_KEY_ALL);
	}

	public Float getWindSpeedLongitudinal() {
		return windSpeedLongitudinal;
	}
 
	public void setWindSpeedLongitudinal(Float windSpeedLongitudinal) {
		this.windSpeedLongitudinal = windSpeedLongitudinal;
	}
 
	public Short getGpsFit() {
		return gpsFit;
	}
 
	public void setGpsFit(Short gpsFit) {
		this.gpsFit = gpsFit;
	}
 
	public Integer getPeripheralStatus() {
		return peripheralStatus;
	}
 
	public void setPeripheralStatus(Integer peripheralStatus) {
		this.peripheralStatus = peripheralStatus;
	}
 
	public Date getCreateDate() {
		return createDate;
	}
 
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
 
	public Float getWindSpeedEast() {
		return windSpeedEast;
	}
 
	public void setWindSpeedEast(Float windSpeedEast) {
		this.windSpeedEast = windSpeedEast;
	}
 
	public Object getShape() {
		return shape;
	}
 
	public void setShape(Object shape) {
		this.shape = shape;
	}
 
	public Float getMobileFlowRate() {
		return mobileFlowRate;
	}
 
	public void setMobileFlowRate(Float mobileFlowRate) {
		this.mobileFlowRate = mobileFlowRate;
	}
 
	public Float getCH4() {
		return cH4;
	}
 
	public void setCH4(Float cH4) {
		this.cH4 = cH4;
	}
 
	public Float getWindDirectionStdDev() {
		return windDirectionStdDev;
	}
 
	public void setWindDirectionStdDev(Float windDirectionStdDev) {
		this.windDirectionStdDev = windDirectionStdDev;
	}
 
	public Float getEpochTime() {
		return epochTime;
	}
 
	public void setEpochTime(Float epochTime) {
		this.epochTime = epochTime;
	}
 
	public Object getAnalyzerId() {
		return analyzerId;
	}
 
	public void setAnalyzerId(Object analyzerId) {
		this.analyzerId = analyzerId;
	}
 
	public Integer getInstrumentStatus() {
		return instrumentStatus;
	}
 
	public void setInstrumentStatus(Integer instrumentStatus) {
		this.instrumentStatus = instrumentStatus;
	}
 
	public Float getDeltaCH4() {
		return deltaCH4;
	}
 
	public void setDeltaCH4(Float deltaCH4) {
		this.deltaCH4 = deltaCH4;
	}
 
	public Float getCarSpeedNorth() {
		return carSpeedNorth;
	}
 
	public void setCarSpeedNorth(Float carSpeedNorth) {
		this.carSpeedNorth = carSpeedNorth;
	}
 
	public Float getCarSpeedEast() {
		return carSpeedEast;
	}
 
	public void setCarSpeedEast(Float carSpeedEast) {
		this.carSpeedEast = carSpeedEast;
	}
 
	public Float getCavityPressure() {
		return cavityPressure;
	}
 
	public void setCavityPressure(Float cavityPressure) {
		this.cavityPressure = cavityPressure;
	}
 
	public Float getWarmBoxTemperature() {
		return warmBoxTemperature;
	}
 
	public void setWarmBoxTemperature(Float warmBoxTemperature) {
		this.warmBoxTemperature = warmBoxTemperature;
	}
 
	public Integer getAnalyzerMode() {
		return analyzerMode;
	}
 
	public void setAnalyzerMode(Integer analyzerMode) {
		this.analyzerMode = analyzerMode;
	}
 
	public Float getWindSpeedNorth() {
		return windSpeedNorth;
	}
 
	public void setWindSpeedNorth(Float windSpeedNorth) {
		this.windSpeedNorth = windSpeedNorth;
	}
 
	public Integer getAnalyzerStatus() {
		return analyzerStatus;
	}
 
	public void setAnalyzerStatus(Integer analyzerStatus) {
		this.analyzerStatus = analyzerStatus;
	}
 
	public Float getValveMask() {
		return valveMask;
	}
 
	public void setValveMask(Float valveMask) {
		this.valveMask = valveMask;
	}
 
	public Float getCO2() {
		return cO2;
	}
 
	public void setCO2(Float cO2) {
		this.cO2 = cO2;
	}
 
	public Float getGpsLongitude() {
		return gpsLongitude;
	}
 
	public void setGpsLongitude(Float gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}
 
	public Float getH2OPercent() {
		return h2OPercent;
	}
 
	public void setH2OPercent(Float h2OPercent) {
		this.h2OPercent = h2OPercent;
	}
 
	public Float getWindSpeedLateral() {
		return windSpeedLateral;
	}
 
	public void setWindSpeedLateral(Float windSpeedLateral) {
		this.windSpeedLateral = windSpeedLateral;
	}
 
	public Float getWeatherStationRotation() {
		return weatherStationRotation;
	}
 
	public void setWeatherStationRotation(Float weatherStationRotation) {
		this.weatherStationRotation = weatherStationRotation;
	}
 
	public Integer getSpecies() {
		return species;
	}
 
	public void setSpecies(Integer species) {
		this.species = species;
	}
 
	public Float getGpsLatitude() {
		return gpsLatitude;
	}
 
	public void setGpsLatitude(Float gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}
 
	public Float getHotBoxTemperature() {
		return hotBoxTemperature;
	}
 
	public void setHotBoxTemperature(Float hotBoxTemperature) {
		this.hotBoxTemperature = hotBoxTemperature;
	}
 
	public Boolean getChemDetect() {
		return chemDetect;
	}
 
	public void setChemDetect(Boolean chemDetect) {
		this.chemDetect = chemDetect;
	}

	public static List<Measurement> getMeasurements(String analyzerId, String startEpochTime, String endEpochTime) {
		Measurement objMeasurement = new Measurement();
		String SQL = "SELECT * FROM dbo.[Measurement] WHERE AnalyzerId='" + analyzerId + "' AND EpochTime > " + startEpochTime + " AND EpochTime < " + endEpochTime;
		return objMeasurement.load(SQL);
	}

	public Measurement getFirst(String  analyzerId, Double startEpochTime, Double endEpochTime) {
		Measurement objMeasurement = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY + analyzerId + "_" + startEpochTime + "_" + endEpochTime)) {
			objMeasurement = (Measurement)DBCache.INSTANCE.get(CACHE_KEY + analyzerId + "_" + startEpochTime + "_" + endEpochTime);
		} else {
			String SQL = "SELECT * FROM dbo.[Measurement] WHERE AnalyzerId='" + analyzerId + "' AND EpochTime > " + startEpochTime + " AND EpochTime < " + endEpochTime;
			ArrayList<Measurement> objMeasurementList = load(SQL);
			if (objMeasurementList!=null && objMeasurementList.size()>0) {
				objMeasurement = objMeasurementList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + analyzerId + "_" + startEpochTime + "_" + endEpochTime, objMeasurement);
			}
		}
		return objMeasurement;
	}

	private static Measurement loadFrom(ResultSet resultSet) {
		Measurement objMeasurement = new Measurement();
		try {
			objMeasurement.setWindSpeedLongitudinal(getFloatColumnValue(resultSet,"WindSpeedLongitudinal"));
			objMeasurement.setGpsFit(getShortColumnValue(resultSet,"GpsFit"));
			objMeasurement.setPeripheralStatus(getIntColumnValue(resultSet,"PeripheralStatus"));
			objMeasurement.setCreateDate(getDateColumnValue(resultSet,"CreateDate"));
			objMeasurement.setWindSpeedEast(getFloatColumnValue(resultSet,"WindSpeedEast"));
			objMeasurement.setShape(resultSet.getObject("Shape"));
			objMeasurement.setMobileFlowRate(getFloatColumnValue(resultSet,"MobileFlowRate"));
			objMeasurement.setCH4(getFloatColumnValue(resultSet,"CH4"));
			objMeasurement.setWindDirectionStdDev(getFloatColumnValue(resultSet,"WindDirectionStdDev"));
			objMeasurement.setEpochTime(getFloatColumnValue(resultSet,"EpochTime"));
			objMeasurement.setAnalyzerId(resultSet.getObject("AnalyzerId"));
			objMeasurement.setInstrumentStatus(getIntColumnValue(resultSet,"InstrumentStatus"));
			objMeasurement.setDeltaCH4(getFloatColumnValue(resultSet,"DeltaCH4"));
			objMeasurement.setCarSpeedNorth(getFloatColumnValue(resultSet,"CarSpeedNorth"));
			objMeasurement.setCarSpeedEast(getFloatColumnValue(resultSet,"CarSpeedEast"));
			objMeasurement.setCavityPressure(getFloatColumnValue(resultSet,"CavityPressure"));
			objMeasurement.setWarmBoxTemperature(getFloatColumnValue(resultSet,"WarmBoxTemperature"));
			objMeasurement.setAnalyzerMode(getIntColumnValue(resultSet,"AnalyzerMode"));
			objMeasurement.setWindSpeedNorth(getFloatColumnValue(resultSet,"WindSpeedNorth"));
			objMeasurement.setAnalyzerStatus(getIntColumnValue(resultSet,"AnalyzerStatus"));
			objMeasurement.setValveMask(getFloatColumnValue(resultSet,"ValveMask"));
			objMeasurement.setCO2(getFloatColumnValue(resultSet,"CO2"));
			objMeasurement.setGpsLongitude(getFloatColumnValue(resultSet,"GpsLongitude"));
			objMeasurement.setH2OPercent(getFloatColumnValue(resultSet,"H2OPercent"));
			objMeasurement.setWindSpeedLateral(getFloatColumnValue(resultSet,"WindSpeedLateral"));
			objMeasurement.setWeatherStationRotation(getFloatColumnValue(resultSet,"WeatherStationRotation"));
			objMeasurement.setSpecies(getIntColumnValue(resultSet,"Species"));
			objMeasurement.setGpsLatitude(getFloatColumnValue(resultSet,"GpsLatitude"));
			objMeasurement.setHotBoxTemperature(getFloatColumnValue(resultSet,"HotBoxTemperature"));
			objMeasurement.setChemDetect(resultSet.getBoolean("ChemDetect"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objMeasurement;
	}

	public ArrayList<Measurement> getAll() {
		String SQL = "SELECT * FROM dbo.[Measurement]";
		return load(SQL);
	}

	public ArrayList<Measurement> load(String SQL) {
		ArrayList<Measurement> objMeasurementList = new ArrayList<Measurement>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				Measurement objMeasurement = loadFrom(resultSet);
				objMeasurementList.add(objMeasurement);
			}

		} catch (SQLException e) {
			Log.error("Class Measurement | " + e.toString());
		}

		return objMeasurementList;
	}

	@SuppressWarnings("unchecked")
	public static List<Measurement> getMeasurements(String analyzer) {
		Measurement objMeasurement = new Measurement();
		List<Measurement> objMeasurementList = new ArrayList<Measurement>();

		Analyzer objAnalyzer = Analyzer.getAnalyzerBySerialNumber(analyzer);
		String analyzerId = objAnalyzer.getId().toString();

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY_ALL + analyzerId)) {
			objMeasurementList = (List<Measurement>)DBCache.INSTANCE.get(CACHE_KEY_ALL + analyzerId);
		} 
		else {
			String SQL = "SELECT * FROM dbo.[Measurement] WHERE AnalyzerId='" + analyzerId  + "'";

			objMeasurementList = objMeasurement.load(SQL);
			if (objMeasurementList!=null && objMeasurementList.size()>0)
			{
				DBCache.INSTANCE.set(CACHE_KEY_ALL + analyzerId , objMeasurementList);
			}
		}
		return objMeasurementList;
	}

	/*
	@SuppressWarnings("unchecked")
	public Measurement getFirstMeasurement() {
		Measurement objMeasurement = null;
		String SQL = "SELECT TOP 1 * FROM dbo.[Measurement] ORDER BY [EpochTime] ASC";
		ArrayList<Measurement> objMeasurementList = load(SQL);
		if (objMeasurementList!=null && objMeasurementList.size()>0)
		{
			objMeasurement = objMeasurementList.get(0);
		}
		return objMeasurement;
	}
	*/

	@SuppressWarnings("unchecked")
	public Measurement getFirstMeasurement(String analyzerId) {
		Measurement objMeasurement = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY_FIRST + analyzerId)) {
			objMeasurement = (Measurement)DBCache.INSTANCE.get(CACHE_KEY_FIRST + analyzerId);
		} 
		else {
			String SQL = "SELECT TOP 1 * FROM dbo.[Measurement] WHERE AnalyzerId='" + analyzerId  + "' ORDER BY [EpochTime] ASC";

			ArrayList<Measurement> objMeasurementList = load(SQL);
			if (objMeasurementList!=null && objMeasurementList.size()>0)
			{
				objMeasurement = objMeasurementList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY_FIRST + analyzerId, objMeasurement);
			}
		}
		return objMeasurement;
	}

	public boolean equalsTo(Map<String, String> map) {
		float epochTime = Float.valueOf(map.get("EPOCH_TIME"));
		float ch4 = Float.valueOf(map.get("CH4"));				
		float ws_wind_lon= Float.valueOf(map.get("WS_WIND_LON"));
		float ws_wind_lat= Float.valueOf(map.get("WS_WIND_LAT"));
		float gps_abs_long = Float.valueOf(map.get("GPS_ABS_LONG"));
		float gps_fit = Float.valueOf(map.get("GPS_FIT"));
		
		boolean epochTimeCompare = floatCompare(this.getEpochTime(), epochTime)==0;
		boolean ch4Compare = floatCompare(this.getCH4(), ch4)==0;
		boolean windSpeedLongCompare = floatCompare(this.getWindSpeedLongitudinal(), ws_wind_lon)==0;
		boolean windSpeedLatCompare = floatCompare(this.getWindSpeedLateral(), ws_wind_lat)==0;
		boolean gpsLongCompare = floatCompare(this.getGpsLongitude(), gps_abs_long)==0;
		boolean gpsFitCompare = floatCompare((float)this.getGpsFit(), gps_fit)==0;

		if(epochTimeCompare && ch4Compare && windSpeedLongCompare && windSpeedLatCompare && gpsLongCompare && gpsFitCompare){
			return true;
		}

		if (DEBUG_LOG) {
			Log.info(String.format("Values from DB -> EpochTime=[%f],CH4=[%f],WindSpeedLongitudinal=[%f],WindSpeedLateral=[%f],GpsLongitude=[%f],GpsFit=[%f]", 
					this.getEpochTime(), this.getCH4(), this.getWindSpeedLongitudinal(), this.getWindSpeedLateral(), this.getGpsLongitude(), (float)this.getGpsFit()));
			Log.info(String.format("Values from CSV -> EpochTime=[%f],CH4=[%f],WindSpeedLongitudinal=[%f],WindSpeedLateral=[%f],GpsLongitude=[%f],GpsFit=[%f]",
					epochTime, ch4, ws_wind_lon, ws_wind_lat, gps_abs_long, gps_fit));
			Log.info(String.format("epochTimeCompare=%b, ch4Compare=%b, windSpeedLongCompare=%b, windSpeedLatCompare=%b, gpsLongCompare=%b, gpsFitCompare=%b", 
					epochTimeCompare, ch4Compare, windSpeedLongCompare, windSpeedLatCompare, gpsLongCompare, gpsFitCompare));
		}
		
		return false;
	}
}

