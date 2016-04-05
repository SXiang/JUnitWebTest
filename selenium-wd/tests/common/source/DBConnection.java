package common.source;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	String dbClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	private Connection establishConnection(String dbUrl, String dbUser,
			String dbPassword) {
		try {
			Class.forName(dbClass);
			con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			Log.info("Connection Established!");
		} catch (ClassNotFoundException e) {
			Log.error(e.toString());
		} catch (SQLException e) {
			Log.error(e.toString());
		}
		return con;
	}

	private ResultSet executeQuery(Connection con, String query) {
		try {
			if (con != null) {
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
			}
		} catch (SQLException e) {
			Log.error(e.toString());
		}
		return rs;
	}

	private void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
				Log.info("Connection Closed!");
			} catch (Exception e) {
				Log.error(e.toString());
			}
		}
	}

	public String getIdOfSpecifiedReportTitle(String reportTitle, TestSetup testSetup) {
		String reportId = "";
		String dbUrl = "jdbc:sqlserver://" + testSetup.getDbIpAddress() + ":"
				+ testSetup.getDbPortNo() + ";DatabaseName="
				+ testSetup.getDbName();
		
		String query = "Select Id from Report where reportTitle='" + reportTitle + "'";

		try {
			con = this.establishConnection(dbUrl, testSetup.getDbUser(),
					testSetup.getDbPassword());
			rs = this.executeQuery(con, query);
			while (rs.next()) {
				reportId = rs.getString(1);
				Log.info("Inner reportId: " + reportId);
				return reportId;
			}
		} catch (SQLException e) {
			Log.error(e.toString());
		} finally {
			this.closeConnection(con);
		}
		return reportId;
	}

	public static void main(String args[]) {
		
	}
}
