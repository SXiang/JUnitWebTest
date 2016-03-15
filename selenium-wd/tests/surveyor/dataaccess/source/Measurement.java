package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.source.Log;

public class Measurement extends BaseEntity {
	private static final String CACHE_KEY = "MEASUREMENT.";

	private Float windSpeedLongitudinal;
	private Integer gpsFit;
	private Integer peripheralStatus;
	private Date createDate;
	private Float windSpeedEast;
	private String shape;
	private Float mobileFlowRate;
	private Float cH4;
	private Float windDirectionStdDev;
	private Float epochTime;
	private String analyzerId;
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
	private Date gpsLatitude;
	private Float hotBoxTemperature;
	private Object chemDetect;
	public Measurement() {
		super();
	}

	public String getAnalyzerId() {
		return analyzerId;
	}

	public Float getWindSpeedLongitudinal() {
		return windSpeedLongitudinal;
	}

	public void setWindSpeedLongitudinal(Float windSpeedLongitudinal) {
		this.windSpeedLongitudinal = windSpeedLongitudinal;
	}

	public Integer getGpsFit() {
		return gpsFit;
	}

	public void setGpsFit(Integer gpsFit) {
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

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
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

	public Object getInstrumentStatus() {
		return instrumentStatus;
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

/*	public Float getC2H4() {
		return c2H4;
	}

	public void setC2H4(Float c2H4) {
		this.c2H4 = c2H4;
	}
*/
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

	public Float getHotBoxTemperature() {
		return hotBoxTemperature;
	}

	public void setHotBoxTemperature(Float hotBoxTemperature) {
		this.hotBoxTemperature = hotBoxTemperature;
	}

	public Object getChemDetect() {
		return chemDetect;
	}

	public void setChemDetect(Object chemDetect) {
		this.chemDetect = chemDetect;
	}

	public static List<Measurement> getMeasurements(String  analyzerId, Double startEpochTime, Double endEpochTime) {
		Measurement objMeasurement = new Measurement();
		String SQL = "SELECT * FROM dbo.[Measurement] WHERE AnalyzerId='" + analyzerId + "' AND EpochTime >= " + startEpochTime + " AND EpochTime <= " + endEpochTime;
		return objMeasurement.load(SQL);
	}

	public Measurement getFirst(String  analyzerId, Double startEpochTime, Double endEpochTime) {
		Measurement objMeasurement = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY + analyzerId + "_" + startEpochTime + "_" + endEpochTime)) {
			objMeasurement = (Measurement)DBCache.INSTANCE.get(CACHE_KEY + analyzerId + "_" + startEpochTime + "_" + endEpochTime);
		} else {
			String SQL = "SELECT * FROM dbo.[Measurement] WHERE AnalyzerId='" + analyzerId + "' AND EpochTime >= " + startEpochTime + " AND EpochTime <= " + endEpochTime;
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
			objMeasurement.setWindSpeedLongitudinal(resultSet.getFloat("WindSpeedLongitudinal"));
			objMeasurement.setGpsFit(resultSet.getInt("GpsFit"));
			objMeasurement.setPeripheralStatus(resultSet.getInt("PeripheralStatus"));
			objMeasurement.setCreateDate(resultSet.getDate("CreateDate"));
			objMeasurement.setWindSpeedEast(resultSet.getFloat("WindSpeedEast"));
			objMeasurement.setShape(resultSet.getString("Shape"));
			objMeasurement.setMobileFlowRate(resultSet.getFloat("MobileFlowRate"));
			objMeasurement.setCH4(resultSet.getFloat("CH4"));
			objMeasurement.setWindDirectionStdDev(resultSet.getFloat("WindDirectionStdDev"));
			objMeasurement.setEpochTime(resultSet.getFloat("EpochTime"));
			objMeasurement.setDeltaCH4(resultSet.getFloat("DeltaCH4"));
			objMeasurement.setCarSpeedNorth(resultSet.getFloat("CarSpeedNorth"));
			objMeasurement.setCarSpeedEast(resultSet.getFloat("CarSpeedEast"));
			objMeasurement.setCavityPressure(resultSet.getFloat("CavityPressure"));
			objMeasurement.setWarmBoxTemperature(resultSet.getFloat("WarmBoxTemperature"));
			objMeasurement.setAnalyzerMode(resultSet.getInt("AnalyzerMode"));
			objMeasurement.setWindSpeedNorth(resultSet.getFloat("WindSpeedNorth"));
			objMeasurement.setAnalyzerStatus(resultSet.getInt("AnalyzerStatus"));
			objMeasurement.setValveMask(resultSet.getFloat("ValveMask"));
			objMeasurement.setCO2(resultSet.getFloat("CO2"));
			objMeasurement.setGpsLongitude(resultSet.getFloat("GpsLongitude"));
			objMeasurement.setH2OPercent(resultSet.getFloat("H2OPercent"));
			objMeasurement.setWindSpeedLateral(resultSet.getFloat("WindSpeedLateral"));
			objMeasurement.setWeatherStationRotation(resultSet.getFloat("WeatherStationRotation"));
			objMeasurement.setSpecies(resultSet.getInt("Species"));
			objMeasurement.setHotBoxTemperature(resultSet.getFloat("HotBoxTemperature"));
			objMeasurement.setChemDetect(resultSet.getObject("ChemDetect"));
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
}
