package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.source.Log;

public class Boundary extends BaseEntity {
	private static final String CACHE_KEY = "BOUNDARY.";

	private String id;
	private Object shape;
	private Integer level;
	private String state;
	private String description;
	private String customerBoundaryTypeID;
	private String customerId;
	private String externalId;

	public Boundary() {
		super();
	}

	public Boundary(String id, Object shape, Integer level, String state, String description, String customerBoundaryTypeID, String customerId, String externalId) {
		super();
		this.id = id;
		this.shape = shape;
		this.level = level;
		this.state = state;
		this.description = description;
		this.customerBoundaryTypeID = customerBoundaryTypeID;
		this.customerId = customerId;
		this.externalId = externalId;
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCustomerBoundaryTypeID() {
		return customerBoundaryTypeID;
	}

	public void setCustomerBoundaryTypeID(String customerBoundaryTypeID) {
		this.customerBoundaryTypeID = customerBoundaryTypeID;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public static Boundary getBoundary(String id) {
		Boundary objBoundary = new Boundary().get(id);
		return objBoundary;
	}

	public Boundary get(String id) {
		Boundary objBoundary = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+id)) {
			objBoundary = (Boundary)DBCache.INSTANCE.get(CACHE_KEY+id);
		} else {
			String SQL = "SELECT * FROM dbo.[Boundary] WHERE Id='" + id + "'";
			ArrayList<Boundary> objBoundaryList = load(SQL);
			if (objBoundaryList!=null && objBoundaryList.size()>0) {
				objBoundary = objBoundaryList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + id, objBoundary);
			}
		}
		return objBoundary;
	}

	public Integer getCount(String customerId) {
		return executeSingleInt("SELECT COUNT(*) FROM dbo.[Boundary] WHERE CustomerId='" + customerId + "'");
	}

	private static Boundary loadFrom(ResultSet resultSet) {
		Boundary objBoundary = new Boundary();
		try {
			objBoundary.setId(resultSet.getString("Id"));
			objBoundary.setShape(resultSet.getObject("Shape"));
			objBoundary.setLevel(getIntColumnValue(resultSet,"Level"));
			objBoundary.setState(resultSet.getString("State"));
			objBoundary.setDescription(resultSet.getString("Description"));
			objBoundary.setCustomerBoundaryTypeID(resultSet.getString("CustomerBoundaryTypeID"));
			objBoundary.setCustomerId(resultSet.getString("CustomerId"));
			objBoundary.setExternalId(resultSet.getString("ExternalId"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objBoundary;
	}

	public ArrayList<Boundary> getAll() {
		String SQL = "SELECT * FROM dbo.[Boundary]";
		return load(SQL);
	}

	public ArrayList<Boundary> load(String SQL) {
		ArrayList<Boundary> objBoundaryList = new ArrayList<Boundary>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				Boundary objBoundary = loadFrom(resultSet);
				objBoundaryList.add(objBoundary);

				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objBoundary.getId(), objBoundary);
			}

		} catch (SQLException e) {
			Log.error("Class Boundary | " + e.toString());
		}

		return objBoundaryList;
	}
}
