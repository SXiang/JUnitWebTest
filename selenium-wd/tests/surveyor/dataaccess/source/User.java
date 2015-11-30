package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User extends BaseEntity {
	private static final String CACHE_KEY = "USER.";
	
	private String firstName;
	private String lastName;
	private String email;
	private boolean emailConfirmed;
	private String phoneNumber;
	private boolean phoneNumberConfirmed;
	private boolean twoFactorEnabled;
	private boolean active;
	private boolean eulaAccepted;
	private String userName;
	private String cultureId;	
	
	public User() {
		super();
	}

	public static User getUser(String username) {
		User user = new User().get(username);
		return user;
	}

	public User get(String username) {
		User user = null;
		
		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+username)) {
			user = (User)DBCache.INSTANCE.get(CACHE_KEY+username);
		} else {
			String SQL = "SELECT * FROM dbo.[User] WHERE UserName='" + username + "'";
			ArrayList<User> userList = load(SQL);
			if (userList!=null && userList.size()>0) {
				user = userList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + username, user);
			}
		}
		return user;
	}

	private static User loadFrom(ResultSet resultSet) {
		User user = new User();
		try {
			user.setFirstName(resultSet.getString("FirstName"));
			user.setLastName(resultSet.getString("LastName"));
			user.setEmail(resultSet.getString("Email"));
			user.setEmailConfirmed(resultSet.getBoolean("EmailConfirmed"));
			user.setPhoneNumber(resultSet.getString("PhoneNumber"));
			user.setPhoneNumberConfirmed(resultSet.getBoolean("PhoneNumberConfirmed"));
			user.setTwoFactorEnabled(resultSet.getBoolean("TwoFactorEnabled"));
			user.setActive(resultSet.getBoolean("Active"));
			user.setEulaAccepted(resultSet.getBoolean("EulaAccepted"));
			user.setUserName(resultSet.getString("UserName"));
			user.setCultureId(resultSet.getString("CultureId"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}
	
	public ArrayList<User> getAll() {
		String SQL = "SELECT * FROM dbo.[User]";
        return load(SQL);
	}
	
	public ArrayList<User> load(String SQL) {
		ArrayList<User> userList = new ArrayList<User>();
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);
			
			while (resultSet.next()) {
				User user = loadFrom(resultSet);
				userList.add(loadFrom(resultSet));
				
				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + user.getUserName(), user);
	         }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userList;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEmailConfirmed() {
		return emailConfirmed;
	}

	public void setEmailConfirmed(boolean emailConfirmed) {
		this.emailConfirmed = emailConfirmed;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isPhoneNumberConfirmed() {
		return phoneNumberConfirmed;
	}

	public void setPhoneNumberConfirmed(boolean phoneNumberConfirmed) {
		this.phoneNumberConfirmed = phoneNumberConfirmed;
	}

	public boolean isTwoFactorEnabled() {
		return twoFactorEnabled;
	}

	public void setTwoFactorEnabled(boolean twoFactorEnabled) {
		this.twoFactorEnabled = twoFactorEnabled;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isEulaAccepted() {
		return eulaAccepted;
	}

	public void setEulaAccepted(boolean eulaAccepted) {
		this.eulaAccepted = eulaAccepted;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCultureId() {
		return cultureId;
	}

	public void setCultureId(String cultureId) {
		this.cultureId = cultureId;
	}

}
