package surveyor.dataaccess.source;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import common.source.Log;
 
public class Asset extends BaseEntity {
	private static final String CACHE_KEY = "ASSET.";
 
	private String id;
	private Object shape;
	private String customerId;
	private String assetTypeId;
	private String state;
	private Date date;
	private String externalId;
	private String description;
	private String customerMaterialTypeId;
 
	public Asset() {
		super();
	}
 
	public Asset(String id, Object shape, String customerId, String assetTypeId, String state, Date date, String externalId, String description, String customerMaterialTypeId) {
		super();
		this.id = id;
		this.shape = shape;
		this.customerId = customerId;
		this.assetTypeId = assetTypeId;
		this.state = state;
		this.date = date;
		this.externalId = externalId;
		this.description = description;
		this.customerMaterialTypeId = customerMaterialTypeId;
	}
 
	public String getId() {
		return id;
	}
 
	public void setId(String id) {
		this.id = id;
	}
 
	public Object getShape() {
		return shape;
	}
 
	public void setShape(Object shape) {
		this.shape = shape;
	}
 
	public String getCustomerId() {
		return customerId;
	}
 
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
 
	public String getAssetTypeId() {
		return assetTypeId;
	}
 
	public void setAssetTypeId(String assetTypeId) {
		this.assetTypeId = assetTypeId;
	}
 
	public String getState() {
		return state;
	}
 
	public void setState(String state) {
		this.state = state;
	}
 
	public Date getDate() {
		return date;
	}
 
	public void setDate(Date date) {
		this.date = date;
	}
 
	public String getExternalId() {
		return externalId;
	}
 
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
 
	public String getDescription() {
		return description;
	}
 
	public void setDescription(String description) {
		this.description = description;
	}
 
	public String getCustomerMaterialTypeId() {
		return customerMaterialTypeId;
	}
 
	public void setCustomerMaterialTypeId(String customerMaterialTypeId) {
		this.customerMaterialTypeId = customerMaterialTypeId;
	}
 
	public static Asset getAsset(String id) {
		Asset objAsset = new Asset().get(id);
		return objAsset;
	}
 
	public Asset get(String id) {
		Asset objAsset = null;
		
		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+id)) {
			objAsset = (Asset)DBCache.INSTANCE.get(CACHE_KEY+id);
		} else {
			String SQL = "SELECT * FROM dbo.[Asset] WHERE Id='" + id + "'";
			ArrayList<Asset> objAssetList = load(SQL);
			if (objAssetList!=null && objAssetList.size()>0) {
				objAsset = objAssetList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + id, objAsset);
			}
		}
		return objAsset;
	}
 
	private static Asset loadFrom(ResultSet resultSet) {
		Asset objAsset = new Asset();
		try {
			objAsset.setId(resultSet.getString("Id"));
			objAsset.setShape(resultSet.getObject("Shape"));
			objAsset.setCustomerId(resultSet.getString("CustomerId"));
			objAsset.setAssetTypeId(resultSet.getString("AssetTypeId"));
			objAsset.setState(resultSet.getString("State"));
			objAsset.setDate(getDateColumnValue(resultSet,"Date"));
			objAsset.setExternalId(resultSet.getString("ExternalId"));
			objAsset.setDescription(resultSet.getString("Description"));
			objAsset.setCustomerMaterialTypeId(resultSet.getString("CustomerMaterialTypeId"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objAsset;
	}
	
	public ArrayList<Asset> getAll() {
		String SQL = "SELECT * FROM dbo.[Asset]";
		return load(SQL);
	}
 
	public ArrayList<Asset> load(String SQL) {
		ArrayList<Asset> objAssetList = new ArrayList<Asset>();
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);
			
			while (resultSet.next()) {
				Asset objAsset = loadFrom(resultSet);
				objAssetList.add(objAsset);
				
				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objAsset.getId(), objAsset);
			}
			
		} catch (SQLException e) {
			Log.error("Class Asset | " + e.toString());
		}
		
		return objAssetList;
	}
}
