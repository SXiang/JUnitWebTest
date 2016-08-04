package surveyor.dataaccess.source;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.source.Log;
 
public class AnemometerRaw extends BaseEntity {
	private static final String CACHE_KEY_FIRST = "ANEMOMETERRAW.FIRST.";
	private static final String CACHE_KEY_ALL = "ANEMOMETERRAW.ALL.";
 
	private Float windSpeedLateral;
	private String analyzerId;
	private Float epochTime;
	private Float windSpeedLongitudinal;
	private Float status;
	private Integer index;
 
	public AnemometerRaw() {
		super();
	}
 
	public AnemometerRaw(Float windSpeedLateral, String analyzerId, Float epochTime, Float windSpeedLongitudinal, Float status, Integer index) {
		super();
		this.windSpeedLateral = windSpeedLateral;
		this.analyzerId = analyzerId;
		this.epochTime = epochTime;
		this.windSpeedLongitudinal = windSpeedLongitudinal;
		this.status = status;
		this.index = index;
	}
 
	public void purgeCache() {
		DBCache.INSTANCE.purgeCache(CACHE_KEY_FIRST);
		DBCache.INSTANCE.purgeCache(CACHE_KEY_ALL);
	}
	
	public Float getWindSpeedLateral() {
		return windSpeedLateral;
	}
 
	public void setWindSpeedLateral(Float windSpeedLateral) {
		this.windSpeedLateral = windSpeedLateral;
	}
 
	public String getAnalyzerId() {
		return analyzerId;
	}
 
	public void setAnalyzerId(String analyzerId) {
		this.analyzerId = analyzerId;
	}
 
	public Float getEpochTime() {
		return epochTime;
	}
 
	public void setEpochTime(Float epochTime) {
		this.epochTime = epochTime;
	}
 
	public Float getWindSpeedLongitudinal() {
		return windSpeedLongitudinal;
	}
 
	public void setWindSpeedLongitudinal(Float windSpeedLongitudinal) {
		this.windSpeedLongitudinal = windSpeedLongitudinal;
	}
 
	public Float getStatus() {
		return status;
	}
 
	public void setStatus(Float status) {
		this.status = status;
	}
 
	public Integer getIndex() {
		return index;
	}
 
	public void setIndex(Integer index) {
		this.index = index;
	}
 
	public static List<AnemometerRaw> getAnemometerRaw(String analyzerId) {
		ArrayList<AnemometerRaw> objAnemometerRawList = new AnemometerRaw().get(analyzerId);
		return objAnemometerRawList;
	}
 
	@SuppressWarnings("unchecked")
	public ArrayList<AnemometerRaw> get(String analyzer) {
		ArrayList<AnemometerRaw> objAnemometerRawList = null;
		
		Analyzer objAnalyzer = Analyzer.getAnalyzerBySerialNumber(analyzer);
		String analyzerId = objAnalyzer.getId().toString();

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY_ALL+analyzerId)) {
			objAnemometerRawList = (ArrayList<AnemometerRaw>)DBCache.INSTANCE.get(CACHE_KEY_ALL+analyzerId);
		} else {
			String SQL = "SELECT * FROM dbo.[AnemometerRaw] WHERE AnalyzerId='" + analyzerId + "'";
			objAnemometerRawList = load(SQL);
			if (objAnemometerRawList!=null && objAnemometerRawList.size()>0) {
				DBCache.INSTANCE.set(CACHE_KEY_ALL+analyzerId, objAnemometerRawList);
			}
		}
		return objAnemometerRawList;
	}
 
	/*
	public AnemometerRaw getFirst() {
		AnemometerRaw objAnemometerRaw = null;
		String SQL = "SELECT TOP 1 * FROM dbo.[AnemometerRaw] ORDER BY EpochTime ASC";
		ArrayList<AnemometerRaw> objAnemometerRawList = load(SQL);
		if (objAnemometerRawList!=null && objAnemometerRawList.size()>0) {
			objAnemometerRaw = objAnemometerRawList.get(0);
		}

		return objAnemometerRaw;
	}
	*/

	public AnemometerRaw getFirst(String analyzerId) {
		AnemometerRaw objAnemometerRaw = null;
		
		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY_FIRST+analyzerId)) {
			objAnemometerRaw = (AnemometerRaw)DBCache.INSTANCE.get(CACHE_KEY_FIRST+analyzerId);
		} else {
			String SQL = "SELECT TOP 1 * FROM dbo.[AnemometerRaw] WHERE AnalyzerId='" + analyzerId + "' ORDER BY EpochTime ASC";
			ArrayList<AnemometerRaw> objAnemometerRawList = load(SQL);
			if (objAnemometerRawList!=null && objAnemometerRawList.size()>0) {
				objAnemometerRaw = objAnemometerRawList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY_FIRST+analyzerId, objAnemometerRaw);
			}
		}
		return objAnemometerRaw;
	}
	
	private static AnemometerRaw loadFrom(ResultSet resultSet) {
		AnemometerRaw objAnemometerRaw = new AnemometerRaw();
		try {
			objAnemometerRaw.setWindSpeedLateral(getFloatColumnValue(resultSet,"WindSpeedLateral"));
			objAnemometerRaw.setAnalyzerId(resultSet.getString("AnalyzerId"));
			objAnemometerRaw.setEpochTime(getFloatColumnValue(resultSet,"EpochTime"));
			objAnemometerRaw.setWindSpeedLongitudinal(getFloatColumnValue(resultSet,"WindSpeedLongitudinal"));
			objAnemometerRaw.setStatus(getFloatColumnValue(resultSet,"Status"));
			objAnemometerRaw.setIndex(getIntColumnValue(resultSet,"Index"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objAnemometerRaw;
	}
	
	public ArrayList<AnemometerRaw> getAll() {
		String SQL = "SELECT * FROM dbo.[AnemometerRaw]";
		return load(SQL);
	}
 
	public ArrayList<AnemometerRaw> load(String SQL) {
		ArrayList<AnemometerRaw> objAnemometerRawList = new ArrayList<AnemometerRaw>();
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);
			
			while (resultSet.next()) {
				AnemometerRaw objAnemometerRaw = loadFrom(resultSet);
				objAnemometerRawList.add(objAnemometerRaw);
			}
			
		} catch (SQLException e) {
			Log.error("Class AnemometerRaw | " + e.toString());
		}
		
		return objAnemometerRawList;
	}
}
