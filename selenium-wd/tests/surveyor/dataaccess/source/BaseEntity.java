package surveyor.dataaccess.source;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import common.source.DateUtility;
import common.source.Log;

public class BaseEntity {
    protected Connection connection = null;
    protected Statement statement = null;
    protected ResultSet resultSet = null;
    protected float floatPrecision = 0.00000001F;

    // Enable this flag when debugging data access classes to print log that we do not want printed in normal run.
    protected static boolean DEBUG_LOG = false;
    
    public BaseEntity() {
    	this.connection = ConnectionFactory.createConnection();
    }
    
    public Connection getConnection() {
		return connection;
	}
	public Statement getStatement() {
		return statement;
	}
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	public ResultSet getResultSet() {
		return resultSet;
	}
	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public int executeNonQuery(String SQL) {
		try {
			statement = connection.createStatement();
			return statement.executeUpdate(SQL);
			
		} catch (SQLException e) {
			Log.error(String.format("Class %s | ", this.getClass().toString()) + e.toString());
		}
		return -1;
	}
	
	public static String getStringColumnValue(ResultSet resultSet, String columnName) throws SQLException
	{
		Object columnValue = resultSet.getString(columnName);
		if (resultSet.wasNull()){
			return "";
		}
		return String.valueOf(columnValue);
	}

	public static Object getBooleanColumnValue(ResultSet resultSet, String columnName) throws SQLException
	{
		Object columnValue = resultSet.getBoolean(columnName);
		if (resultSet.wasNull()){
			return null;
		}
		return (Boolean)columnValue;
	}

	public static float getFloatColumnValue(ResultSet resultSet, String columnName) throws SQLException
	{
		Object columnValue = resultSet.getFloat(columnName);
		if (resultSet.wasNull()){
			return Float.MIN_VALUE;
		}
		return (float)columnValue;
	}

	public static Date getDateColumnValue(ResultSet resultSet, String columnName) throws SQLException
	{
		Object columnValue = resultSet.getTimestamp(columnName);
		if (resultSet.wasNull()){
			return DateUtility.DATE_MINVALUE;
		} else {
			// convert to timestamp and get time to preserve the time component of the date.
			Timestamp timestamp = (Timestamp)columnValue;
			columnValue = new Date(timestamp.getTime());
		}
		return (Date)columnValue;
	}

	public static Double getDoubleColumnValue(ResultSet resultSet, String columnName) throws SQLException
	{
		Object columnValue = resultSet.getDouble(columnName);
		if (resultSet.wasNull()){
			return Double.MIN_VALUE;
		} 
		return (Double)columnValue;
	}

	public static Long getLongColumnValue(ResultSet resultSet, String columnName) throws SQLException
	{
		Object columnValue = resultSet.getLong(columnName);
		if (resultSet.wasNull()){
			return Long.MIN_VALUE;
		} 
		return (Long)columnValue;
	}

	public static Short getShortColumnValue(ResultSet resultSet, String columnName) throws SQLException
	{
		Object columnValue = resultSet.getShort(columnName);
		if (resultSet.wasNull()){
			return Short.MIN_VALUE;
		} 
		return (Short)columnValue;
	}

	public static Integer getIntColumnValue(ResultSet resultSet, String columnName) throws SQLException
	{
		Object columnValue = resultSet.getInt(columnName);
		if (resultSet.wasNull()){
			return Integer.MIN_VALUE;
		} 
		return (Integer)columnValue;
	}
	
	public static String trim(String str){
		return str==null?"":str.trim();
	}
	
	public Integer floatCompare(Float value1, Float value2) {
		if (Math.abs(value1 - value2) < floatPrecision) {
			return 0;
		} else if (value1>value2) {
			return 1;
		}
		return -1;
	}
}