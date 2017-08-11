package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.source.Log;

public class LicensedFeatureOptions extends BaseEntity {
	private static final String CACHE_KEY = "LICENSEDFEATUREOPTIONS.";

	private String description;
	private Object id;
	private Object licensedFeatureId;

	public LicensedFeatureOptions() {
		super();
	}

	public LicensedFeatureOptions(String description, Object id, Object licensedFeatureId) {
		super();
		this.description = description;
		this.id = id;
		this.licensedFeatureId = licensedFeatureId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public Object getLicensedFeatureId() {
		return licensedFeatureId;
	}

	public void setLicensedFeatureId(Object licensedFeatureId) {
		this.licensedFeatureId = licensedFeatureId;
	}

	public static LicensedFeatureOptions getLicensedFeatureOption(String id) {
		LicensedFeatureOptions objLicensedFeatureOptions = new LicensedFeatureOptions().get(id);
		return objLicensedFeatureOptions;
	}

	public LicensedFeatureOptions get(String id) {
		LicensedFeatureOptions objLicensedFeatureOptions = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+id)) {
			objLicensedFeatureOptions = (LicensedFeatureOptions)DBCache.INSTANCE.get(CACHE_KEY+id);
		} else {
			String SQL = "SELECT * FROM dbo.[LicensedFeatureOptions] WHERE Id='" + id + "'";
			ArrayList<LicensedFeatureOptions> objLicensedFeatureOptionsList = load(SQL);
			if (objLicensedFeatureOptionsList!=null && objLicensedFeatureOptionsList.size()>0) {
				objLicensedFeatureOptions = objLicensedFeatureOptionsList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + id, objLicensedFeatureOptions);
			}
		}
		return objLicensedFeatureOptions;
	}

	private static LicensedFeatureOptions loadFrom(ResultSet resultSet) {
		LicensedFeatureOptions objLicensedFeatureOptions = new LicensedFeatureOptions();
		try {
			objLicensedFeatureOptions.setDescription(resultSet.getString("Description"));
			objLicensedFeatureOptions.setId(resultSet.getObject("Id"));
			objLicensedFeatureOptions.setLicensedFeatureId(resultSet.getObject("LicensedFeatureId"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objLicensedFeatureOptions;
	}

	public ArrayList<LicensedFeatureOptions> getAll() {
		String SQL = "SELECT * FROM dbo.[LicensedFeatureOptions]";
		return load(SQL);
	}

	public ArrayList<LicensedFeatureOptions> load(String SQL) {
		ArrayList<LicensedFeatureOptions> objLicensedFeatureOptionsList = new ArrayList<LicensedFeatureOptions>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				LicensedFeatureOptions objLicensedFeatureOptions = loadFrom(resultSet);
				objLicensedFeatureOptionsList.add(objLicensedFeatureOptions);

				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objLicensedFeatureOptions.getId(), objLicensedFeatureOptions);
			}

		} catch (SQLException e) {
			Log.error("Class LicensedFeatureOptions | " + e.toString());
		}

		return objLicensedFeatureOptionsList;
	}
}
