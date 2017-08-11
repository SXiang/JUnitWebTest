package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.source.Log;

public class CustomerLicensedFeatureOptions extends BaseEntity {
	private Object customerId;
	private Object licensedFeatureOptionId;

	public CustomerLicensedFeatureOptions() {
		super();
	}

	public CustomerLicensedFeatureOptions(Object customerId, Object licensedFeatureOptionId) {
		super();
		this.customerId = customerId;
		this.licensedFeatureOptionId = licensedFeatureOptionId;
	}

	public Object getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Object customerId) {
		this.customerId = customerId;
	}

	public Object getLicensedFeatureOptionId() {
		return licensedFeatureOptionId;
	}

	public void setLicensedFeatureOptionId(Object licensedFeatureOptionId) {
		this.licensedFeatureOptionId = licensedFeatureOptionId;
	}

	public static List<CustomerLicensedFeatureOptions> getCustomerLicensedFeatureOptions(String customerId) {
		return new CustomerLicensedFeatureOptions().get(customerId);
	}

	public List<CustomerLicensedFeatureOptions> get(String customerId) {
		String SQL = "SELECT * FROM dbo.[CustomerLicensedFeatureOptions] WHERE CustomerId='" + customerId + "'";
		return load(SQL);
	}

	private static CustomerLicensedFeatureOptions loadFrom(ResultSet resultSet) {
		CustomerLicensedFeatureOptions objCustomerLicensedFeatureOptions = new CustomerLicensedFeatureOptions();
		try {
			objCustomerLicensedFeatureOptions.setCustomerId(resultSet.getObject("CustomerId"));
			objCustomerLicensedFeatureOptions.setLicensedFeatureOptionId(resultSet.getObject("LicensedFeatureOptionId"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objCustomerLicensedFeatureOptions;
	}

	public ArrayList<CustomerLicensedFeatureOptions> getAll() {
		String SQL = "SELECT * FROM dbo.[CustomerLicensedFeatureOptions]";
		return load(SQL);
	}

	public ArrayList<CustomerLicensedFeatureOptions> load(String SQL) {
		ArrayList<CustomerLicensedFeatureOptions> objCustomerLicensedFeatureOptionsList = new ArrayList<CustomerLicensedFeatureOptions>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				CustomerLicensedFeatureOptions objCustomerLicensedFeatureOptions = loadFrom(resultSet);
				objCustomerLicensedFeatureOptionsList.add(objCustomerLicensedFeatureOptions);
			}

		} catch (SQLException e) {
			Log.error("Class CustomerLicensedFeatureOptions | " + e.toString());
		}

		return objCustomerLicensedFeatureOptionsList;
	}
}
