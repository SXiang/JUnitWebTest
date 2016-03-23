package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import common.source.Log;

public class SurveyModeType extends BaseEntity {
	private static final String CACHE_KEY = "SURVEYMODETYPE.";

	private String id;
	private String description;

	public SurveyModeType() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static SurveyModeType getSurveyModeType(String id) {
		SurveyModeType objSurveyModeType = new SurveyModeType().get(id);
		return objSurveyModeType;
	}

	public static SurveyModeType getSurveyModeTypeByDescription(String description) {
		SurveyModeType objSurveyModeType = new SurveyModeType().getIdByDescription(description);
		return objSurveyModeType;
	}

	public SurveyModeType get(String id) {
		SurveyModeType objSurveyModeType = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+id)) {
			objSurveyModeType = (SurveyModeType)DBCache.INSTANCE.get(CACHE_KEY+id);
		} else {
			String SQL = "SELECT * FROM dbo.[SurveyModeType] WHERE Id='" + id + "'";
			ArrayList<SurveyModeType> objSurveyModeTypeList = load(SQL);
			if (objSurveyModeTypeList!=null && objSurveyModeTypeList.size()>0) {
				objSurveyModeType = objSurveyModeTypeList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + id, objSurveyModeType);
			}
		}
		return objSurveyModeType;
	}

	public SurveyModeType getIdByDescription(String description) {
		SurveyModeType objSurveyModeType = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+description)) {
			objSurveyModeType = (SurveyModeType)DBCache.INSTANCE.get(CACHE_KEY+description);
		} else {
			
			String SQL = "SELECT * FROM dbo.[SurveyModeType] WHERE Description='" + description + "'";
			ArrayList<SurveyModeType> objSurveyModeTypeList = load(SQL);
			if (objSurveyModeTypeList!=null && objSurveyModeTypeList.size()>0) {
				objSurveyModeType = objSurveyModeTypeList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + description, objSurveyModeType);
			}
		}
		return objSurveyModeType;
	}

	private static SurveyModeType loadFrom(ResultSet resultSet) {
		SurveyModeType objSurveyModeType = new SurveyModeType();
		try {
			objSurveyModeType.setId(resultSet.getString("Id"));
			objSurveyModeType.setDescription(resultSet.getString("Description"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objSurveyModeType;
	}

	public ArrayList<SurveyModeType> getAll() {
		String SQL = "SELECT * FROM dbo.[SurveyModeType]";
		return load(SQL);
	}

	public ArrayList<SurveyModeType> load(String SQL) {
		ArrayList<SurveyModeType> objSurveyModeTypeList = new ArrayList<SurveyModeType>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				SurveyModeType objSurveyModeType = loadFrom(resultSet);
				objSurveyModeTypeList.add(objSurveyModeType);

				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objSurveyModeType.getId(), objSurveyModeType);
			}

		} catch (SQLException e) {
			Log.error("Class SurveyModeType | " + e.toString());
		}

		return objSurveyModeTypeList;
	}
}
