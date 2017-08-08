package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.source.Log;

public class BoxTypes extends BaseEntity {
	private static final String CACHE_KEY = "BOXTYPES.";

	private String description;
	private String name;
	private Integer id;

	public BoxTypes() {
		super();
	}

	public BoxTypes(String description, String name, Integer id) {
		super();
		this.description = description;
		this.name = name;
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static BoxTypes getBoxTypeByName(String name) {
		BoxTypes objBoxTypes = new BoxTypes().getByName(name);
		return objBoxTypes;
	}

	public BoxTypes getByName(String name) {
		BoxTypes objBoxTypes = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+name)) {
			objBoxTypes = (BoxTypes)DBCache.INSTANCE.get(CACHE_KEY+name);
		} else {
			String SQL = "SELECT * FROM dbo.[BoxTypes] WHERE Name='" + name + "'";
			ArrayList<BoxTypes> objBoxTypesList = load(SQL);
			if (objBoxTypesList!=null && objBoxTypesList.size()>0) {
				objBoxTypes = objBoxTypesList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + name, objBoxTypes);
			}
		}
		return objBoxTypes;
	}

	private static BoxTypes loadFrom(ResultSet resultSet) {
		BoxTypes objBoxTypes = new BoxTypes();
		try {
			objBoxTypes.setDescription(resultSet.getString("Description"));
			objBoxTypes.setName(resultSet.getString("Name"));
			objBoxTypes.setId(getIntColumnValue(resultSet,"Id"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objBoxTypes;
	}

	public ArrayList<BoxTypes> getAll() {
		String SQL = "SELECT * FROM dbo.[BoxTypes]";
		return load(SQL);
	}

	public ArrayList<BoxTypes> load(String SQL) {
		ArrayList<BoxTypes> objBoxTypesList = new ArrayList<BoxTypes>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				BoxTypes objBoxTypes = loadFrom(resultSet);
				objBoxTypesList.add(objBoxTypes);

				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objBoxTypes.getId(), objBoxTypes);
			}

		} catch (SQLException e) {
			Log.error("Class BoxTypes | " + e.toString());
		}

		return objBoxTypesList;
	}
}
