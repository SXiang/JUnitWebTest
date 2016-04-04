package surveyor.dataaccess.source;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import common.source.DateUtility;

public class BaseEntity {
    protected Connection connection = null;
    protected Statement statement = null;
    protected ResultSet resultSet = null;
	
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
		Object columnValue = resultSet.getDate(columnName);
		if (resultSet.wasNull()){
			return DateUtility.DATE_MINVALUE;
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

	public static Integer getIntColumnValue(ResultSet resultSet, String columnName) throws SQLException
	{
		Object columnValue = resultSet.getInt(columnName);
		if (resultSet.wasNull()){
			return Integer.MIN_VALUE;
		} 
		return (Integer)columnValue;
	}
}