package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import common.source.Log;

public class Report extends BaseEntity {
	public static final String CACHE_KEY = "REPORT.";

	private Date dateStarted;
	private String id;
	private String userId;
	private String reportTypeId;
	private Double mapHeight;
	private String selectedCustomerId;
	private String buildNumber;
	private String timeZoneId;
	private String customerId;
	private Double mapWidth;
	private String reportTitle;
	private String reportStatusTypeId;

	public Report() {
		super();
	}

	public void purgeCache() {
		DBCache.INSTANCE.purgeCache(CACHE_KEY);
	}

	public Date getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(Date dateStarted) {
		this.dateStarted = dateStarted;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReportTypeId() {
		return reportTypeId;
	}

	public void setReportTypeId(String reportTypeId) {
		this.reportTypeId = reportTypeId;
	}

	public Double getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(Double mapHeight) {
		this.mapHeight = mapHeight;
	}

	public String getSelectedCustomerId() {
		return selectedCustomerId;
	}

	public void setSelectedCustomerId(String selectedCustomerId) {
		this.selectedCustomerId = selectedCustomerId;
	}

	public String getBuildNumber() {
		return buildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}

	public String getTimeZoneId() {
		return timeZoneId;
	}

	public void setTimeZoneId(String timeZoneId) {
		this.timeZoneId = timeZoneId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Double getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(Double mapWidth) {
		this.mapWidth = mapWidth;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public String getReportStatusTypeId() {
		return reportStatusTypeId;
	}

	public void setReportStatusTypeId(String reportStatusTypeId) {
		this.reportStatusTypeId = reportStatusTypeId;
	}

	public static Report getReport(String reportTitle) {
		Report objReport = new Report().get(reportTitle);
		return objReport;
	}

	public Report get(String reportTitle) {
		Report objReport = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+reportTitle)) {
			objReport = (Report)DBCache.INSTANCE.get(CACHE_KEY+reportTitle);
		} else {
			String SQL = "SELECT * FROM dbo.[Report] WHERE ReportTitle='" + reportTitle + "' ORDER BY DateStarted DESC ";
			ArrayList<Report> objReportList = load(SQL);
			if (objReportList!=null && objReportList.size()>0) {
				objReport = objReportList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + reportTitle, objReport);
			}
		}
		return objReport;
	}

	private static Report loadFrom(ResultSet resultSet) {
		Report objReport = new Report();
		try {
			objReport.setDateStarted(resultSet.getDate("DateStarted"));
			objReport.setId(resultSet.getString("Id"));
			objReport.setUserId(resultSet.getString("UserId"));
			objReport.setReportTypeId(resultSet.getString("ReportTypeId"));
			objReport.setMapHeight(resultSet.getDouble("MapHeight"));
			objReport.setSelectedCustomerId(resultSet.getString("SelectedCustomerId"));
			objReport.setBuildNumber(resultSet.getString("BuildNumber"));
			objReport.setTimeZoneId(resultSet.getString("TimeZoneId"));
			objReport.setCustomerId(resultSet.getString("CustomerId"));
			objReport.setMapWidth(resultSet.getDouble("MapWidth"));
			objReport.setReportTitle(resultSet.getString("ReportTitle"));
			objReport.setReportStatusTypeId(resultSet.getString("ReportStatusTypeId"));
		} catch (SQLException e) {
			Log.error("Class Report | " + e.toString());
		}

		return objReport;
	}

	public ArrayList<Report> getAll() {
		String SQL = "SELECT * FROM dbo.[Report]";
		return load(SQL);
	}

	public ArrayList<Report> load(String SQL) {
		ArrayList<Report> objReportList = new ArrayList<Report>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				Report objReport = loadFrom(resultSet);
				objReportList.add(objReport);

				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objReport.getReportTitle(), objReport);
			}

		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objReportList;
	}
}
