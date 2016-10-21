package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.source.Log;

public class CustomerMaterialType extends BaseEntity {
	private static final String CACHE_KEY = "CUSTOMERMATERIALTYPE.";

	private String id;
	private String description;
	private String customerId;
	private Boolean isDotted;
	private Short lineWeight;
	private String color;

	public CustomerMaterialType() {
		super();
	}

	public CustomerMaterialType(String id, String description, String customerId, Boolean isDotted, Short lineWeight, String color) {
		super();
		this.id = id;
		this.description = description;
		this.customerId = customerId;
		this.isDotted = isDotted;
		this.lineWeight = lineWeight;
		this.color = color;
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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Boolean getIsDotted() {
		return isDotted;
	}

	public void setIsDotted(Boolean isDotted) {
		this.isDotted = isDotted;
	}

	public Short getLineWeight() {
		return lineWeight;
	}

	public void setLineWeight(Short lineWeight) {
		this.lineWeight = lineWeight;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public static CustomerMaterialType getCustomerMaterialType(String id) {
		CustomerMaterialType objCustomerMaterialType = new CustomerMaterialType().get(id);
		return objCustomerMaterialType;
	}

	public CustomerMaterialType get(String id) {
		CustomerMaterialType objCustomerMaterialType = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+id)) {
			objCustomerMaterialType = (CustomerMaterialType)DBCache.INSTANCE.get(CACHE_KEY+id);
		} else {
			String SQL = "SELECT * FROM dbo.[CustomerMaterialType] WHERE Id='" + id + "'";
			ArrayList<CustomerMaterialType> objCustomerMaterialTypeList = load(SQL);
			if (objCustomerMaterialTypeList!=null && objCustomerMaterialTypeList.size()>0) {
				objCustomerMaterialType = objCustomerMaterialTypeList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + id, objCustomerMaterialType);
			}
		}
		return objCustomerMaterialType;
	}

	public Integer getCount(String customerId) {
		return executeSingleInt("SELECT COUNT(*) FROM dbo.[CustomerMaterialType] WHERE CustomerId='" + customerId + "'");
	}

	private static CustomerMaterialType loadFrom(ResultSet resultSet) {
		CustomerMaterialType objCustomerMaterialType = new CustomerMaterialType();
		try {
			objCustomerMaterialType.setId(resultSet.getString("Id"));
			objCustomerMaterialType.setDescription(resultSet.getString("Description"));
			objCustomerMaterialType.setCustomerId(resultSet.getString("CustomerId"));
			objCustomerMaterialType.setIsDotted(resultSet.getBoolean("IsDotted"));
			objCustomerMaterialType.setLineWeight(getShortColumnValue(resultSet,"LineWeight"));
			objCustomerMaterialType.setColor(resultSet.getString("Color"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objCustomerMaterialType;
	}

	public ArrayList<CustomerMaterialType> getAll() {
		String SQL = "SELECT * FROM dbo.[CustomerMaterialType]";
		return load(SQL);
	}

	public ArrayList<CustomerMaterialType> getAllForCustomer(String customerId) {
		String SQL = String.format("SELECT * FROM dbo.[CustomerMaterialType] WHERE CustomerId='%s'", customerId);
		return load(SQL);
	}

	public ArrayList<CustomerMaterialType> load(String SQL) {
		ArrayList<CustomerMaterialType> objCustomerMaterialTypeList = new ArrayList<CustomerMaterialType>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				CustomerMaterialType objCustomerMaterialType = loadFrom(resultSet);
				objCustomerMaterialTypeList.add(objCustomerMaterialType);

				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objCustomerMaterialType.getId(), objCustomerMaterialType);
			}

		} catch (SQLException e) {
			Log.error("Class CustomerMaterialType | " + e.toString());
		}

		return objCustomerMaterialTypeList;
	}
}
