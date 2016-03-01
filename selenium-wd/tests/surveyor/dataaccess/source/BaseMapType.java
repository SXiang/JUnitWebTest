package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import common.source.Log;

public class BaseMapType extends BaseEntity {
	private static final String CACHE_KEY = "BASEMAPTYPE.";

	private Object id;
	private String description;

	public BaseMapType() {
		super();
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static String getBaseMapTypeId(String description) {
		BaseMapType objBaseMapType = new BaseMapType().get(description);
		return objBaseMapType.getId().toString();
	}

	public BaseMapType get(String description) {
		BaseMapType objBaseMapType = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY + description)) {
			objBaseMapType = (BaseMapType) DBCache.INSTANCE.get(CACHE_KEY + description);
		} else {
			String SQL = "SELECT * FROM dbo.[BaseMapType] WHERE Description='" + description + "'";
			ArrayList<BaseMapType> objBaseMapTypeList = load(SQL);
			if (objBaseMapTypeList != null && objBaseMapTypeList.size() > 0) {
				objBaseMapType = objBaseMapTypeList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + description, objBaseMapType);
			}
		}
		return objBaseMapType;
	}

	private static BaseMapType loadFrom(ResultSet resultSet) {
		BaseMapType objBaseMapType = new BaseMapType();
		try {
			objBaseMapType.setId(resultSet.getObject("Id"));
			objBaseMapType.setDescription(resultSet.getString("Description"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objBaseMapType;
	}

	public ArrayList<BaseMapType> getAll() {
		String SQL = "SELECT * FROM dbo.[BaseMapType]";
		return load(SQL);
	}

	public ArrayList<BaseMapType> load(String SQL) {
		ArrayList<BaseMapType> objBaseMapTypeList = new ArrayList<BaseMapType>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				BaseMapType objBaseMapType = loadFrom(resultSet);
				objBaseMapTypeList.add(objBaseMapType);

				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objBaseMapType.getDescription(), objBaseMapType);
			}

		} catch (SQLException e) {
			Log.error("Class BaseMapType | " + e.toString());
		}

		return objBaseMapTypeList;
	}
}
