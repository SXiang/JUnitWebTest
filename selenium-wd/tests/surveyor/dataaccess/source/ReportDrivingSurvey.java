package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.source.Log;

public class ReportDrivingSurvey extends BaseEntity {
	private static final String CACHE_KEY = "REPORTDRIVINGSURVEY.";

	private Object id;
	private Object reportId;
	private Float lisaOpacity;
	private Object lisaColor;
	private Float fovOpacity;
	private Object fovColor;
	private Object surveyId;

	public ReportDrivingSurvey() {
		super();
	}

	public ReportDrivingSurvey(Object id, Object reportId, Float lisaOpacity, Object lisaColor, Float fovOpacity, Object fovColor, Object surveyId) {
		super();
		this.id = id;
		this.reportId = reportId;
		this.lisaOpacity = lisaOpacity;
		this.lisaColor = lisaColor;
		this.fovOpacity = fovOpacity;
		this.fovColor = fovColor;
		this.surveyId = surveyId;
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public Object getReportId() {
		return reportId;
	}

	public void setReportId(Object reportId) {
		this.reportId = reportId;
	}

	public Float getLisaOpacity() {
		return lisaOpacity;
	}

	public void setLisaOpacity(Float lisaOpacity) {
		this.lisaOpacity = lisaOpacity;
	}

	public Object getLisaColor() {
		return lisaColor;
	}

	public void setLisaColor(Object lisaColor) {
		this.lisaColor = lisaColor;
	}

	public Float getFovOpacity() {
		return fovOpacity;
	}

	public void setFovOpacity(Float fovOpacity) {
		this.fovOpacity = fovOpacity;
	}

	public Object getFovColor() {
		return fovColor;
	}

	public void setFovColor(Object fovColor) {
		this.fovColor = fovColor;
	}

	public Object getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(Object surveyId) {
		this.surveyId = surveyId;
	}

	public static ReportDrivingSurvey getReportDrivingSurvey(String id) {
		ReportDrivingSurvey objReportDrivingSurvey = new ReportDrivingSurvey().get(id);
		return objReportDrivingSurvey;
	}

	public static List<ReportDrivingSurvey> getReportDrivingSurveysBySurveyId(String surveyId) {
		return new ReportDrivingSurvey().getBySurveyId(surveyId);
	}

	public List<ReportDrivingSurvey> getBySurveyId(String surveyId) {
		List<ReportDrivingSurvey> objReportDrivingSurveyList = null;
		String SQL = "SELECT * FROM dbo.[ReportDrivingSurvey] WHERE SurveyId='" + surveyId + "'";
		objReportDrivingSurveyList = load(SQL);

		return objReportDrivingSurveyList;
	}

	public ReportDrivingSurvey get(String id) {
		ReportDrivingSurvey objReportDrivingSurvey = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+id)) {
			objReportDrivingSurvey = (ReportDrivingSurvey)DBCache.INSTANCE.get(CACHE_KEY+id);
		} else {
			String SQL = "SELECT * FROM dbo.[ReportDrivingSurvey] WHERE Id='" + id + "'";
			ArrayList<ReportDrivingSurvey> objReportDrivingSurveyList = load(SQL);
			if (objReportDrivingSurveyList!=null && objReportDrivingSurveyList.size()>0) {
				objReportDrivingSurvey = objReportDrivingSurveyList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + id, objReportDrivingSurvey);
			}
		}
		return objReportDrivingSurvey;
	}

	private static ReportDrivingSurvey loadFrom(ResultSet resultSet) {
		ReportDrivingSurvey objReportDrivingSurvey = new ReportDrivingSurvey();
		try {
			objReportDrivingSurvey.setId(resultSet.getObject("Id"));
			objReportDrivingSurvey.setReportId(resultSet.getObject("ReportId"));
			objReportDrivingSurvey.setLisaOpacity(getFloatColumnValue(resultSet,"LisaOpacity"));
			objReportDrivingSurvey.setLisaColor(resultSet.getObject("LisaColor"));
			objReportDrivingSurvey.setFovOpacity(getFloatColumnValue(resultSet,"FovOpacity"));
			objReportDrivingSurvey.setFovColor(resultSet.getObject("FovColor"));
			objReportDrivingSurvey.setSurveyId(resultSet.getObject("SurveyId"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objReportDrivingSurvey;
	}

	public ArrayList<ReportDrivingSurvey> getAll() {
		String SQL = "SELECT * FROM dbo.[ReportDrivingSurvey]";
		return load(SQL);
	}

	public ArrayList<ReportDrivingSurvey> load(String SQL) {
		ArrayList<ReportDrivingSurvey> objReportDrivingSurveyList = new ArrayList<ReportDrivingSurvey>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				ReportDrivingSurvey objReportDrivingSurvey = loadFrom(resultSet);
				objReportDrivingSurveyList.add(objReportDrivingSurvey);

				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objReportDrivingSurvey.getId(), objReportDrivingSurvey);
			}

		} catch (SQLException e) {
			Log.error("Class ReportDrivingSurvey | " + e.toString());
		}

		return objReportDrivingSurveyList;
	}
}
