package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.source.Log;

public class ReportEQDrivingSurvey extends BaseEntity {
	private static final String CACHE_KEY = "REPORTEQDRIVINGSURVEY.";

	private Object lisaColor;
	private Object surveyId;
	private Object id;
	private Object fovColor;
	private Object reportEQId;

	public ReportEQDrivingSurvey() {
		super();
	}

	public ReportEQDrivingSurvey(Object lisaColor, Object surveyId, Object id, Object fovColor, Object reportEQId) {
		super();
		this.lisaColor = lisaColor;
		this.surveyId = surveyId;
		this.id = id;
		this.fovColor = fovColor;
		this.reportEQId = reportEQId;
	}

	public Object getLisaColor() {
		return lisaColor;
	}

	public void setLisaColor(Object lisaColor) {
		this.lisaColor = lisaColor;
	}

	public Object getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Object surveyId) {
		this.surveyId = surveyId;
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public Object getFovColor() {
		return fovColor;
	}

	public void setFovColor(Object fovColor) {
		this.fovColor = fovColor;
	}

	public Object getReportEQId() {
		return reportEQId;
	}

	public void setReportEQId(Object reportEQId) {
		this.reportEQId = reportEQId;
	}

	public static ReportEQDrivingSurvey getReportEQDrivingSurvey(String id) {
		ReportEQDrivingSurvey objReportEQDrivingSurvey = new ReportEQDrivingSurvey().get(id);
		return objReportEQDrivingSurvey;
	}

	public static List<ReportEQDrivingSurvey> getReportEQDrivingSurveysBySurveyId(String surveyId) {
		return new ReportEQDrivingSurvey().getBySurveyId(surveyId);
	}

	public List<ReportEQDrivingSurvey> getBySurveyId(String surveyId) {
		List<ReportEQDrivingSurvey> objReportEQDrivingSurveyList = null;

		String SQL = "SELECT * FROM dbo.[ReportEQDrivingSurvey] WHERE SurveyId='" + surveyId + "'";
		objReportEQDrivingSurveyList = load(SQL);

		return objReportEQDrivingSurveyList;
	}

	public ReportEQDrivingSurvey get(String id) {
		ReportEQDrivingSurvey objReportEQDrivingSurvey = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+id)) {
			objReportEQDrivingSurvey = (ReportEQDrivingSurvey)DBCache.INSTANCE.get(CACHE_KEY+id);
		} else {
			String SQL = "SELECT * FROM dbo.[ReportEQDrivingSurvey] WHERE Id='" + id + "'";
			ArrayList<ReportEQDrivingSurvey> objReportEQDrivingSurveyList = load(SQL);
			if (objReportEQDrivingSurveyList!=null && objReportEQDrivingSurveyList.size()>0) {
				objReportEQDrivingSurvey = objReportEQDrivingSurveyList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + id, objReportEQDrivingSurvey);
			}
		}
		return objReportEQDrivingSurvey;
	}

	private static ReportEQDrivingSurvey loadFrom(ResultSet resultSet) {
		ReportEQDrivingSurvey objReportEQDrivingSurvey = new ReportEQDrivingSurvey();
		try {
			objReportEQDrivingSurvey.setLisaColor(resultSet.getObject("LisaColor"));
			objReportEQDrivingSurvey.setSurveyId(resultSet.getObject("SurveyId"));
			objReportEQDrivingSurvey.setId(resultSet.getObject("Id"));
			objReportEQDrivingSurvey.setFovColor(resultSet.getObject("FovColor"));
			objReportEQDrivingSurvey.setReportEQId(resultSet.getObject("ReportEQId"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objReportEQDrivingSurvey;
	}

	public ArrayList<ReportEQDrivingSurvey> getAll() {
		String SQL = "SELECT * FROM dbo.[ReportEQDrivingSurvey]";
		return load(SQL);
	}

	public ArrayList<ReportEQDrivingSurvey> load(String SQL) {
		ArrayList<ReportEQDrivingSurvey> objReportEQDrivingSurveyList = new ArrayList<ReportEQDrivingSurvey>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				ReportEQDrivingSurvey objReportEQDrivingSurvey = loadFrom(resultSet);
				objReportEQDrivingSurveyList.add(objReportEQDrivingSurvey);

				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objReportEQDrivingSurvey.getId(), objReportEQDrivingSurvey);
			}

		} catch (SQLException e) {
			Log.error("Class ReportEQDrivingSurvey | " + e.toString());
		}

		return objReportEQDrivingSurveyList;
	}
}
