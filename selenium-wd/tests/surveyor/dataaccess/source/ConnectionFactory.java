package surveyor.dataaccess.source;

import java.sql.*;
import java.util.Hashtable;
import common.source.Log;
import common.source.TestContext;

public class ConnectionFactory {

	private static final String JDBC_SQLSERVER_CONNECTION_STRING = "jdbc:sqlserver://%s:%s;databaseName=%s;";
	private static final String MICROSOFT_SQLSERVER_JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	private static Hashtable<String, Connection> connectionCache = new Hashtable<String, Connection>();

	public static Connection createConnection() {
		Connection conn = null;

		String dbIpAddress = TestContext.INSTANCE.getDbIpAddress();
		String dbPortNo = TestContext.INSTANCE.getDbPortNo();
		String dbName = TestContext.INSTANCE.getDbName();
		String dbUser = TestContext.INSTANCE.getDbUser();
		String dbPassword = TestContext.INSTANCE.getDbPassword();
		String connectionUrl = String.format(JDBC_SQLSERVER_CONNECTION_STRING, dbIpAddress, dbPortNo, dbName);

		if (connectionCache.containsKey(connectionUrl)) {
			conn = connectionCache.get(connectionUrl);
		} else {		
			try {
				Class.forName(MICROSOFT_SQLSERVER_JDBC_DRIVER);
				conn = DriverManager.getConnection(connectionUrl, dbUser, dbPassword);
				connectionCache.put(connectionUrl, conn);
			} catch (ClassNotFoundException | SQLException e) {
				Log.error(e.toString());
			}
		}
		return conn;
	}
}
