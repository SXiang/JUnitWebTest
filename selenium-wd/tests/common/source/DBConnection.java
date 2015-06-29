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

	public Connection establishConnection(String dbUrl, String dbUser,
			String dbPassword) {
		try {
			Class.forName(dbClass);
			con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			System.out.println("Connection Established!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public ResultSet executeQuery(Connection con, String query) {
		try {
			if (con != null) {
				stmt = con.createStatement();
				rs = stmt.executeQuery(query);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return rs;
	}

	public void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
				System.out.println("Connection Closed!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String getIdOfSpecifiedReportTitle(String reportTitle, TestSetup testSetup) {
		String reportId = "";
		String dbUrl = "jdbc:sqlserver://" + testSetup.getDbIpAddress() + ":"
				+ testSetup.getDbPortNo() + ";DatabaseName="
				+ testSetup.getDbName();
		
		String query = "Select Id from Report where reportTitle='"
				+ reportTitle + "'";

		con = this.establishConnection(dbUrl, testSetup.getDbUser(),
				testSetup.getDbPassword());
		rs = this.executeQuery(con, query);
		try {
			while (rs.next()) {
				reportId = rs.getString(1);
				System.out.println("Inner reportId: " + reportId);
				return reportId;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			this.closeConnection(con);
		}
		return reportId;
	}

	public static void main(String args[]) {
		
	}
}
