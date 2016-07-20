package surveyor.dataaccess.source;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.source.Log;
 
public class Customer extends BaseEntity {
	private static final String CACHE_KEY = "CUSTOMER.";
 
	private Boolean active;
	private String name;
	private String id;
	private String eula;
 
	public Customer() {
		super();
	}
 
	public Boolean getActive() {
		return active;
	}
 
	public void setActive(Boolean active) {
		this.active = active;
	}
 
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public String getId() {
		return id;
	}
 
	public void setId(String id) {
		this.id = id;
	}
 
	public String getEula() {
		return eula;
	}
 
	public void setEula(String eula) {
		this.eula = eula;
	}
 
	public static Customer getCustomer(String name) {
		Customer objCustomer = new Customer().get(name);
		return objCustomer;
	}
 
	public Customer get(String name) {
		Customer objCustomer = null;
		
		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+name)) {
			objCustomer = (Customer)DBCache.INSTANCE.get(CACHE_KEY+name);
		} else {
			String SQL = "SELECT * FROM dbo.[Customer] WHERE Name='" + name + "'";
			ArrayList<Customer> objCustomerList = load(SQL);
			if (objCustomerList!=null && objCustomerList.size()>0) {
				objCustomer = objCustomerList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + name, objCustomer);
			}
		}
		return objCustomer;
	}
 
	private static Customer loadFrom(ResultSet resultSet) {
		Customer objCustomer = new Customer();
		try {
			objCustomer.setActive(resultSet.getBoolean("Active"));
			objCustomer.setName(resultSet.getString("Name"));
			objCustomer.setId(resultSet.getString("Id"));
			objCustomer.setEula(resultSet.getString("Eula"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objCustomer;
	}
	
	public ArrayList<Customer> getAll() {
		String SQL = "SELECT * FROM dbo.[Customer]";
		return load(SQL);
	}

	public ArrayList<Customer> load(String SQL) {
		ArrayList<Customer> objCustomerList = new ArrayList<Customer>();
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);
			
			while (resultSet.next()) {
				Customer objCustomer = loadFrom(resultSet);
				objCustomerList.add(objCustomer);
				
				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objCustomer.getId(), objCustomer);
			}
			
		} catch (SQLException e) {
			Log.error("Class Customer | " + e.toString());
		}
		
		return objCustomerList;
	}
}
