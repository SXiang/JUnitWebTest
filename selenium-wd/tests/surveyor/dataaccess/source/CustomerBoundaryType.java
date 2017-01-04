package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.source.Log;

public class CustomerBoundaryType extends BaseEntity {
	private static final String CACHE_KEY = "CUSTOMERBOUNDARYTYPE.";

	private String id;
	private String color;
	private String featureClassDescription;
	private Short zoomlevel;
	private Boolean isDotted;
	private Boolean isReportable;
	private Short lineWeight;
	private String customerId;

	public CustomerBoundaryType() {
		super();
	}

	public CustomerBoundaryType(String id, String color, String featureClassDescription, Short zoomlevel, Boolean isDotted, Boolean isReportable, Short lineWeight, String customerId) {
		super();
		this.id = id;
		this.color = color;
		this.featureClassDescription = featureClassDescription;
		this.zoomlevel = zoomlevel;
		this.isDotted = isDotted;
		this.isReportable = isReportable;
		this.lineWeight = lineWeight;
		this.customerId = customerId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFeatureClassDescription() {
		return featureClassDescription;
	}

	public void setFeatureClassDescription(String featureClassDescription) {
		this.featureClassDescription = featureClassDescription;
	}

	public Short getZoomlevel() {
		return zoomlevel;
	}

	public void setZoomlevel(Short zoomlevel) {
		this.zoomlevel = zoomlevel;
	}

	public Boolean getIsDotted() {
		return isDotted;
	}

	public void setIsDotted(Boolean isDotted) {
		this.isDotted = isDotted;
	}

	public Boolean getIsReportable() {
		return isReportable;
	}

	public void setIsReportable(Boolean isReportable) {
		this.isReportable = isReportable;
	}

	public Short getLineWeight() {
		return lineWeight;
	}

	public void setLineWeight(Short lineWeight) {
		this.lineWeight = lineWeight;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public static CustomerBoundaryType getCustomerBoundaryType(String id) {
		CustomerBoundaryType objCustomerBoundaryType = new CustomerBoundaryType().get(id);
		return objCustomerBoundaryType;
	}

	public static CustomerBoundaryType getCustomerBoundaryTypeByName(String boundaryName, String customerId) {
		CustomerBoundaryType objCustomerBoundaryType = new CustomerBoundaryType().getByName(boundaryName, customerId);
		return objCustomerBoundaryType;
	}

	public CustomerBoundaryType get(String id) {
		CustomerBoundaryType objCustomerBoundaryType = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+id)) {
			objCustomerBoundaryType = (CustomerBoundaryType)DBCache.INSTANCE.get(CACHE_KEY+id);
		} else {
			String SQL = "SELECT * FROM dbo.[CustomerBoundaryType] WHERE Id='" + id + "'";
			ArrayList<CustomerBoundaryType> objCustomerBoundaryTypeList = load(SQL);
			if (objCustomerBoundaryTypeList!=null && objCustomerBoundaryTypeList.size()>0) {
				objCustomerBoundaryType = objCustomerBoundaryTypeList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + id, objCustomerBoundaryType);
			}
		}
		return objCustomerBoundaryType;
	}

	public CustomerBoundaryType getByName(String boundaryName, String customerId) {
		CustomerBoundaryType objCustomerBoundaryType = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+customerId+boundaryName)) {
			objCustomerBoundaryType = (CustomerBoundaryType)DBCache.INSTANCE.get(CACHE_KEY+customerId+boundaryName);
		} else {
			String SQL = "SELECT * FROM dbo.[CustomerBoundaryType] WHERE CustomerId='" + customerId + "' AND [FeatureClassDescription]='" + boundaryName + "'";
			ArrayList<CustomerBoundaryType> objCustomerBoundaryTypeList = load(SQL);
			if (objCustomerBoundaryTypeList!=null && objCustomerBoundaryTypeList.size()>0) {
				objCustomerBoundaryType = objCustomerBoundaryTypeList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY+customerId+boundaryName, objCustomerBoundaryType);
			}
		}
		return objCustomerBoundaryType;
	}

	public Integer getCount(String customerId) {
		return executeSingleInt("SELECT COUNT(*) FROM dbo.[CustomerBoundaryType] WHERE CustomerId='" + customerId + "'");
	}

	private static CustomerBoundaryType loadFrom(ResultSet resultSet) {
		CustomerBoundaryType objCustomerBoundaryType = new CustomerBoundaryType();
		try {
			objCustomerBoundaryType.setId(resultSet.getString("Id"));
			objCustomerBoundaryType.setColor(resultSet.getString("Color"));
			objCustomerBoundaryType.setFeatureClassDescription(resultSet.getString("FeatureClassDescription"));
			objCustomerBoundaryType.setZoomlevel(getShortColumnValue(resultSet,"Zoomlevel"));
			objCustomerBoundaryType.setIsDotted(resultSet.getBoolean("IsDotted"));
			objCustomerBoundaryType.setIsReportable(resultSet.getBoolean("IsReportable"));
			objCustomerBoundaryType.setLineWeight(getShortColumnValue(resultSet,"LineWeight"));
			objCustomerBoundaryType.setCustomerId(resultSet.getString("CustomerId"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objCustomerBoundaryType;
	}

	public ArrayList<CustomerBoundaryType> getAll() {
		String SQL = "SELECT * FROM dbo.[CustomerBoundaryType]";
		return load(SQL);
	}

	public ArrayList<CustomerBoundaryType> getAllForCustomer(String customerId) {
		String SQL = String.format("SELECT * FROM dbo.[CustomerBoundaryType] WHERE CustomerId='%s'", customerId);
		return load(SQL);
	}

	public ArrayList<CustomerBoundaryType> load(String SQL) {
		ArrayList<CustomerBoundaryType> objCustomerBoundaryTypeList = new ArrayList<CustomerBoundaryType>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				CustomerBoundaryType objCustomerBoundaryType = loadFrom(resultSet);
				objCustomerBoundaryTypeList.add(objCustomerBoundaryType);

				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objCustomerBoundaryType.getId(), objCustomerBoundaryType);
			}

		} catch (SQLException e) {
			Log.error("Class CustomerBoundaryType | " + e.toString());
		}

		return objCustomerBoundaryTypeList;
	}
}
