package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.source.Log;

public class SurveyorUnit extends BaseEntity {
	private static final String CACHE_KEY = "SURVEYORUNIT.";
	private static final String CACHE_ID_KEY = "SURVEYORUNIT.ID.";

	private String description;
	private String id;
	private String locationId;

	public SurveyorUnit() {
		super();
	}

	public SurveyorUnit(String description, String id, String locationId) {
		super();
		this.description = description;
		this.id = id;
		this.locationId = locationId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public static SurveyorUnit getSurveyorUnit(String description) {
		SurveyorUnit objSurveyorUnit = new SurveyorUnit().get(description);
		return objSurveyorUnit;
	}

	public static SurveyorUnit getSurveyorUnitById(String surveyorUnitId) {
		SurveyorUnit objSurveyorUnit = new SurveyorUnit().getById(surveyorUnitId);
		return objSurveyorUnit;
	}

	public SurveyorUnit getById(String surveyorUnitId) {
		SurveyorUnit objSurveyorUnit = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_ID_KEY+surveyorUnitId)) {
			objSurveyorUnit = (SurveyorUnit)DBCache.INSTANCE.get(CACHE_ID_KEY+surveyorUnitId);
		} else {
			String SQL = "SELECT * FROM dbo.[SurveyorUnit] WHERE Id='" + surveyorUnitId + "'";
			ArrayList<SurveyorUnit> objSurveyorUnitList = load(SQL);
			if (objSurveyorUnitList!=null && objSurveyorUnitList.size()>0) {
				objSurveyorUnit = objSurveyorUnitList.get(0);
				DBCache.INSTANCE.set(CACHE_ID_KEY + surveyorUnitId, objSurveyorUnit);
			}
		}
		return objSurveyorUnit;
	}

	public SurveyorUnit get(String description) {
		SurveyorUnit objSurveyorUnit = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+description)) {
			objSurveyorUnit = (SurveyorUnit)DBCache.INSTANCE.get(CACHE_KEY+description);
		} else {
			String SQL = "SELECT * FROM dbo.[SurveyorUnit] WHERE Description='" + description + "'";
			ArrayList<SurveyorUnit> objSurveyorUnitList = load(SQL);
			if (objSurveyorUnitList!=null && objSurveyorUnitList.size()>0) {
				objSurveyorUnit = objSurveyorUnitList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + description, objSurveyorUnit);
			}
		}
		return objSurveyorUnit;
	}

	private static SurveyorUnit loadFrom(ResultSet resultSet) {
		SurveyorUnit objSurveyorUnit = new SurveyorUnit();
		try {
			objSurveyorUnit.setDescription(resultSet.getString("Description"));
			objSurveyorUnit.setId(resultSet.getString("Id"));
			objSurveyorUnit.setLocationId(resultSet.getString("LocationId"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objSurveyorUnit;
	}

	public ArrayList<SurveyorUnit> getAll() {
		String SQL = "SELECT * FROM dbo.[SurveyorUnit]";
		return load(SQL);
	}

	public ArrayList<SurveyorUnit> load(String SQL) {
		ArrayList<SurveyorUnit> objSurveyorUnitList = new ArrayList<SurveyorUnit>();
		SQL ="SELECT * FROM dbo.[SurveyorUnit] WHERE Description='iGPS car'";
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				SurveyorUnit objSurveyorUnit = loadFrom(resultSet);
				objSurveyorUnitList.add(objSurveyorUnit);

				// add to cache.
//				DBCache.INSTANCE.set(CACHE_KEY + objSurveyorUnit.getId(), objSurveyorUnit);
			}

		} catch (SQLException e) {
			Log.error("Class SurveyorUnit | " + e.toString());
		}

		return objSurveyorUnitList;
	}
}
