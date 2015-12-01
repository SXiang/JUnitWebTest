package surveyor.dataaccess.source;

import java.sql.SQLException;
import java.util.ArrayList;

import common.source.TestContext;

public class Resources extends BaseEntity {
	private static final String CACHE_KEY = "RESOURCES.";
	private String culture;
	private String name;
	private String value;
	
	public Resources() {
		super();
	}
	
	public Resources(String culture, String name, String value) {
		super();
		this.culture = culture;
		this.name = name;
		this.value = value;
	}
	
	public static String getResource(String name) {
		return (new Resources()).get(name);
	}
	
	public String getCulture() {
		return culture;
	}
	public String getName() {
		return name;
	}
	public String getValue() {
		return value;
	}

	/*
	 * Gets the Resource string with the specified resource name.
	 * Uses the culture of the logged-in user when looking up the resource.
	 */
	public String get(String name) {
		String resx = "";
		String culture = TestContext.INSTANCE.getUserCulture();
		
		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+name)) {
			Resources resource = (Resources)DBCache.INSTANCE.get(CACHE_KEY+name);
			resx = resource.getValue();
		} else {
			String SQL = "SELECT * FROM dbo.Resources WHERE Name='" + name + "' AND Culture='" + culture + "'";
			ArrayList<Resources> resList = load(SQL);
			if (resList!=null && resList.size()>0) {
				Resources resource = resList.get(0);
				resx = resource.getValue();
				DBCache.INSTANCE.set(CACHE_KEY + name, resource);
			}
		}
		return resx;
	}

	/*
	 * Gets all the Resource strings for the culture of the logged-in user.
	 */
	public ArrayList<Resources> getAll() {
		String culture = TestContext.INSTANCE.getUserCulture();
		String SQL = "SELECT * FROM dbo.Resources WHERE Culture='" + culture + "'";
        return load(SQL);
	}

	public ArrayList<Resources> load(String SQL) {
		ArrayList<Resources> resourcesList = new ArrayList<Resources>();
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);
			
			while (resultSet.next()) {
				String name = resultSet.getString("Name");
				Resources resource = new Resources(resultSet.getString("Culture"), 
	            		name, resultSet.getString("Value"));
				resourcesList.add(resource);
				
				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + name, resource);
	         }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resourcesList;
	}
}
