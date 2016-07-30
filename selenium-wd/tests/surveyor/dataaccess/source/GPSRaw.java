package surveyor.dataaccess.source;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.source.Log;
 
public class GPSRaw extends BaseEntity {
	private static final String CACHE_KEY_FIRST = "GPSRAW.FIRST.";
	private static final String CACHE_KEY_ALL = "GPSRAW.ALL.";
 
	private Integer gpsFit;
	private Float gpsTime;
	private String analyzerId;
	private Float gPSLatitudeUncertainty;
	private Float gpsLongitude;
	private Float gPSLongitudeUncertainty;
	private Float gpsLatitude;
	private Float epochTime;
 
	public GPSRaw() {
		super();
	}
 
	public GPSRaw(Integer gpsFit, Float gpsTime, String analyzerId, Float gPSLatitudeUncertainty, Float gpsLongitude, Float gPSLongitudeUncertainty, Float gpsLatitude, Float epochTime) {
		super();
		this.gpsFit = gpsFit;
		this.gpsTime = gpsTime;
		this.analyzerId = analyzerId;
		this.gPSLatitudeUncertainty = gPSLatitudeUncertainty;
		this.gpsLongitude = gpsLongitude;
		this.gPSLongitudeUncertainty = gPSLongitudeUncertainty;
		this.gpsLatitude = gpsLatitude;
		this.epochTime = epochTime;
	}
 
	public void purgeCache() {
		DBCache.INSTANCE.purgeCache(CACHE_KEY_FIRST);
		DBCache.INSTANCE.purgeCache(CACHE_KEY_ALL);
	}
	
	public Integer getGpsFit() {
		return gpsFit;
	}
 
	public void setGpsFit(Integer gpsFit) {
		this.gpsFit = gpsFit;
	}
 
	public Float getGpsTime() {
		return gpsTime;
	}
 
	public void setGpsTime(Float gpsTime) {
		this.gpsTime = gpsTime;
	}
 
	public String getAnalyzerId() {
		return analyzerId;
	}
 
	public void setAnalyzerId(String analyzerId) {
		this.analyzerId = analyzerId;
	}
 
	public Float getGPSLatitudeUncertainty() {
		return gPSLatitudeUncertainty;
	}
 
	public void setGPSLatitudeUncertainty(Float gPSLatitudeUncertainty) {
		this.gPSLatitudeUncertainty = gPSLatitudeUncertainty;
	}
 
	public Float getGpsLongitude() {
		return gpsLongitude;
	}
 
	public void setGpsLongitude(Float gpsLongitude) {
		this.gpsLongitude = gpsLongitude;
	}
 
	public Float getGPSLongitudeUncertainty() {
		return gPSLongitudeUncertainty;
	}
 
	public void setGPSLongitudeUncertainty(Float gPSLongitudeUncertainty) {
		this.gPSLongitudeUncertainty = gPSLongitudeUncertainty;
	}
 
	public Float getGpsLatitude() {
		return gpsLatitude;
	}
 
	public void setGpsLatitude(Float gpsLatitude) {
		this.gpsLatitude = gpsLatitude;
	}
 
	public Float getEpochTime() {
		return epochTime;
	}
 
	public void setEpochTime(Float epochTime) {
		this.epochTime = epochTime;
	}
 
	public static List<GPSRaw> getGPSRaw(String id) {
		List<GPSRaw> objGPSRawList = new GPSRaw().get(id);
		return objGPSRawList;
	}
 
	@SuppressWarnings("unchecked")
	public List<GPSRaw> get(String id) {
		ArrayList<GPSRaw> objGPSRawList = null;
		
		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY_ALL+id)) {
			objGPSRawList = (ArrayList<GPSRaw>)DBCache.INSTANCE.get(CACHE_KEY_ALL+id);
		} else {
			String SQL = "SELECT * FROM dbo.[GPSRaw] WHERE Id='" + id + "'";
			objGPSRawList = load(SQL);
			if (objGPSRawList!=null && objGPSRawList.size()>0) {
				DBCache.INSTANCE.set(CACHE_KEY_ALL + id, objGPSRawList);
			}
		}
		return objGPSRawList;
	}
	
	public GPSRaw getFirst(String analyzerId) {
		GPSRaw objGPSRaw = null;
		
		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY_FIRST+analyzerId)) {
			objGPSRaw = (GPSRaw)DBCache.INSTANCE.get(CACHE_KEY_FIRST+analyzerId);
		} else {
			String SQL = "SELECT TOP 1 * FROM dbo.[GPSRaw] WHERE AnalyzerId='" + analyzerId + "' ORDER BY EpochTime ASC";
			ArrayList<GPSRaw> objGPSRawList = load(SQL);
			if (objGPSRawList!=null && objGPSRawList.size()>0) {
				objGPSRaw = objGPSRawList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY_FIRST+analyzerId, objGPSRaw);
			}
		}
		return objGPSRaw;
	}
 
	private static GPSRaw loadFrom(ResultSet resultSet) {
		GPSRaw objGPSRaw = new GPSRaw();
		try {
			objGPSRaw.setGpsFit(getIntColumnValue(resultSet,"GpsFit"));
			objGPSRaw.setGpsTime(getFloatColumnValue(resultSet,"GpsTime"));
			objGPSRaw.setAnalyzerId(resultSet.getString("AnalyzerId"));
			objGPSRaw.setGPSLatitudeUncertainty(getFloatColumnValue(resultSet,"GPSLatitudeUncertainty"));
			objGPSRaw.setGpsLongitude(getFloatColumnValue(resultSet,"GpsLongitude"));
			objGPSRaw.setGPSLongitudeUncertainty(getFloatColumnValue(resultSet,"GPSLongitudeUncertainty"));
			objGPSRaw.setGpsLatitude(getFloatColumnValue(resultSet,"GpsLatitude"));
			objGPSRaw.setEpochTime(getFloatColumnValue(resultSet,"EpochTime"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objGPSRaw;
	}
	
	public ArrayList<GPSRaw> getAll() {
		String SQL = "SELECT * FROM dbo.[GPSRaw]";
		return load(SQL);
	}
 
	public ArrayList<GPSRaw> load(String SQL) {
		ArrayList<GPSRaw> objGPSRawList = new ArrayList<GPSRaw>();
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);
			
			while (resultSet.next()) {
				GPSRaw objGPSRaw = loadFrom(resultSet);
				objGPSRawList.add(objGPSRaw);
			}
			
		} catch (SQLException e) {
			Log.error("Class GPSRaw | " + e.toString());
		}
		
		return objGPSRawList;
	}
}
