package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.source.Log;

public class LocationAnalyticsParameter extends BaseEntity {
	private static final String CACHE_KEY = "LOCATIONANALYTICSPARAMETER.";
	private static final String CACHE_ID_KEY = "LOCATIONANALYTICSPARAMETER.ID.";

	private float rankingMinAmplitude;
	private float priorityScoreFilterThreshold;
	private String fromDate;
	private String locationId;

	public LocationAnalyticsParameter() {
		super();
	}

	public LocationAnalyticsParameter(String locationId, String fromDate, float rankingMinAmplitude, float priorityScoreFilterThreshold) {
		super();
		this.fromDate = fromDate;
		this.rankingMinAmplitude = rankingMinAmplitude;
		this.priorityScoreFilterThreshold = priorityScoreFilterThreshold;
		this.locationId = locationId;
	}


	public float getRankingMinAmplitude() {
		return rankingMinAmplitude;
	}

	public void setRankingMinAmplitude(float rankingMinAmplitude) {
		this.rankingMinAmplitude = rankingMinAmplitude;
	}

	public float getPriorityScoreFilterThreshold() {
		return priorityScoreFilterThreshold;
	}

	public void setPriorityScoreFilterThreshold(float priorityScoreFilterThreshold) {
		this.priorityScoreFilterThreshold = priorityScoreFilterThreshold;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public static LocationAnalyticsParameter getLocationAnalyticsParameterById(String locationId) {
		LocationAnalyticsParameter objLocationAnalyticsParameter = new LocationAnalyticsParameter().getById(locationId);
		return objLocationAnalyticsParameter;
	}

	public LocationAnalyticsParameter getById(String locationId) {
		LocationAnalyticsParameter objLocationAnalyticsParameter = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_ID_KEY+locationId+fromDate)) {
			objLocationAnalyticsParameter = (LocationAnalyticsParameter)DBCache.INSTANCE.get(CACHE_ID_KEY+locationId+fromDate);
		} else {
			String SQL = "SELECT * FROM dbo.[LocationAnalyticsParameter] WHERE LocationId='" + locationId + "'";
			ArrayList<LocationAnalyticsParameter> objLocationAnalyticsParameterList = load(SQL);
			if (objLocationAnalyticsParameterList!=null && objLocationAnalyticsParameterList.size()>0) {
				objLocationAnalyticsParameter = objLocationAnalyticsParameterList.get(0);
				DBCache.INSTANCE.set(CACHE_ID_KEY+locationId+fromDate, objLocationAnalyticsParameter);
			}
		}
		return objLocationAnalyticsParameter;
	}

	private static LocationAnalyticsParameter loadFrom(ResultSet resultSet) {
		LocationAnalyticsParameter objLocationAnalyticsParameter = new LocationAnalyticsParameter();
		try {
			objLocationAnalyticsParameter.setFromDate(resultSet.getString("FromDate"));
			objLocationAnalyticsParameter.setRankingMinAmplitude(resultSet.getFloat("RankingMinAmplitude"));
			objLocationAnalyticsParameter.setPriorityScoreFilterThreshold(resultSet.getFloat("PriorityScoreFilterThreshold"));
			objLocationAnalyticsParameter.setLocationId(resultSet.getString("LocationId"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objLocationAnalyticsParameter;
	}

	public ArrayList<LocationAnalyticsParameter> getAll() {
		String SQL = "SELECT * FROM dbo.[LocationAnalyticsParameter]";
		return load(SQL);
	}

	public ArrayList<LocationAnalyticsParameter> load(String SQL) {
		ArrayList<LocationAnalyticsParameter> objLocationAnalyticsParameterList = new ArrayList<LocationAnalyticsParameter>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				LocationAnalyticsParameter objLocationAnalyticsParameter = loadFrom(resultSet);
				objLocationAnalyticsParameterList.add(objLocationAnalyticsParameter);

				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objLocationAnalyticsParameter.getLocationId()+objLocationAnalyticsParameter.getFromDate(), objLocationAnalyticsParameter);
			}

		} catch (SQLException e) {
			Log.error("Class LocationAnalyticsParameter | " + e.toString());
		}

		return objLocationAnalyticsParameterList;
	}
}
