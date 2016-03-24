package surveyor.dataaccess.source;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public static float getFloatColumnValue(ResultSet resultSet, String columnName) throws SQLException
	{
		float columnValue = resultSet.getFloat(columnName);
		if (resultSet.wasNull()){
			return Float.MIN_VALUE;
		}
		return columnValue;
	}
}
