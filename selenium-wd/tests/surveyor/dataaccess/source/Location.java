package surveyor.dataaccess.source;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.source.Log;
import common.source.NumberUtility;
 
public class Location extends BaseEntity {
	private static final String CACHE_KEY = "LOCATION.";
 
	private Float longitude;
	private String customerId;
	private String description;
	private String id;
	private Float latitude;
 
	public Location() {
		super();
	}
 
	public Location(Float longitude, String customerId, String description, String id, Float latitude) {
		super();
		this.longitude = longitude;
		this.customerId = customerId;
		this.description = description;
		this.id = id;
		this.latitude = latitude;
	}
 
	public Float getLongitude() {
		return longitude;
	}
 
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
 
	public String getCustomerId() {
		return customerId;
	}
 
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
 
	public Float getLatitude() {
		return latitude;
	}
 
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
 
	public static Location getLocation(String description) {
		Location objLocation = new Location().get(description);
		return objLocation;
	}
 
	public Location get(String description) {
		Location objLocation = null;
		
		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+description)) {
			objLocation = (Location)DBCache.INSTANCE.get(CACHE_KEY+description);
		} else {
			String SQL = "SELECT * FROM dbo.[Location] WHERE Description='" + description + "'";
			ArrayList<Location> objLocationList = load(SQL);
			if (objLocationList!=null && objLocationList.size()>0) {
				objLocation = objLocationList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + description, objLocation);
			}
		}
		return objLocation;
	}
 
	public void updateLocation() {
		String SQL = String.format("UPDATE [dbo].[Location] SET Latitude=%s, Longitude=%s WHERE Id='%s'",
				NumberUtility.formatString(getLatitude(), 15), NumberUtility.formatString(getLongitude(), 15),
				getId());
		executeNonQuery(SQL);
	}
	
	private static Location loadFrom(ResultSet resultSet) {
		Location objLocation = new Location();
		try {
			objLocation.setLongitude(getFloatColumnValue(resultSet,"Longitude"));
			objLocation.setCustomerId(resultSet.getString("CustomerId"));
			objLocation.setDescription(resultSet.getString("Description"));
			objLocation.setId(resultSet.getString("Id"));
			objLocation.setLatitude(getFloatColumnValue(resultSet,"Latitude"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objLocation;
	}
	
	public ArrayList<Location> getAll() {
		String SQL = "SELECT * FROM dbo.[Location]";
		return load(SQL);
	}
 
	public ArrayList<Location> load(String SQL) {
		ArrayList<Location> objLocationList = new ArrayList<Location>();
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);
			
			while (resultSet.next()) {
				Location objLocation = loadFrom(resultSet);
				objLocationList.add(objLocation);
				
				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objLocation.getId(), objLocation);
			}
			
		} catch (SQLException e) {
			Log.error("Class Location | " + e.toString());
		}
		
		return objLocationList;
	}
}
