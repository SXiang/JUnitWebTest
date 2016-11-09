package surveyor.dataaccess.source;

import java.sql.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.rules.ExternalResource;

import common.source.ExceptionUtility;
import common.source.Log;
import common.source.TestContext;

public class ConnectionFactory {

	private static final String JDBC_SQLSERVER_CONNECTION_STRING = "jdbc:sqlserver://%s:%s;databaseName=%s;";
	private static final String MICROSOFT_SQLSERVER_JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	private static Map<String, Connection> connectionCache = Collections.synchronizedMap(new HashMap<String, Connection>());

	@Rule
	public ExternalResource resource= new ExternalResource() {
		@Override
		protected void before() throws Throwable {
			// Connections can be opened on-demand by the tests.
		};
		
		@Override
		protected void after() {
			if (connectionCache!=null && connectionCache.size() > 0) {
				// If there is an open connection after the test, close it.
				for (String connString : connectionCache.keySet()) {
					Connection conn = connectionCache.get(connString);
					try {
						if (conn!=null) {
							conn.close();
						}
					} catch (SQLException e) {
						Log.error(String.format("Exception when closing connection: Exception message - ", 
								ExceptionUtility.getStackTraceString(e)));
					}
				}
			}
		};
	};
	
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
			try {
				if (conn.isClosed()) {
					conn = createNewConnection(conn, dbUser, dbPassword, connectionUrl);
				}
			} catch (SQLException e) {
				Log.error(String.format("Error creating connection. EXCEPTION: %s", ExceptionUtility.getStackTraceString(e)));
			}
		} else {		
			conn = createNewConnection(conn, dbUser, dbPassword, connectionUrl);
		}
		return conn;
	}

	private static Connection createNewConnection(Connection conn, String dbUser, String dbPassword, String connectionUrl) {
		try {
			Class.forName(MICROSOFT_SQLSERVER_JDBC_DRIVER);
			conn = DriverManager.getConnection(connectionUrl, dbUser, dbPassword);
			if (!connectionCache.containsKey(connectionUrl)) {
				connectionCache.put(connectionUrl, conn);
			} else {
				connectionCache.replace(connectionUrl, conn);
			}
		} catch (ClassNotFoundException | SQLException e) {
			Log.error(e.toString());
		}
		return conn;
	}
}
