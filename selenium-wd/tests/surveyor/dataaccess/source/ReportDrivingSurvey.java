package surveyor.dataaccess.source;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.source.Log;
 
public class ReportDrivingSurvey extends BaseEntity {
	private static final String CACHE_KEY = "REPORTDRIVINGSURVEY.";
 
	private String isotopicColor;
	private String id;
	private String reportId;
	private String lisaColor;
	private Float fovOpacity;
	private String peakColor;
	private Float lisaOpacity;
	private String surveyId;
	private String fovColor;
 
	public ReportDrivingSurvey() {
		super();
	}
 
	public String getIsotopicColor() {
		return isotopicColor;
	}
 
	public void setIsotopicColor(String isotopicColor) {
		this.isotopicColor = isotopicColor;
	}
 
	public String getId() {
		return id;
	}
 
	public void setId(String id) {
		this.id = id;
	}
 
	public String getReportId() {
		return reportId;
	}
 
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
 
	public String getLisaColor() {
		return lisaColor;
	}
 
	public void setLisaColor(String lisaColor) {
		this.lisaColor = lisaColor;
	}
 
	public Float getFovOpacity() {
		return fovOpacity;
	}
 
	public void setFovOpacity(Float fovOpacity) {
		this.fovOpacity = fovOpacity;
	}
 
	public String getPeakColor() {
		return peakColor;
	}
 
	public void setPeakColor(String peakColor) {
		this.peakColor = peakColor;
	}
 
	public Float getLisaOpacity() {
		return lisaOpacity;
	}
 
	public void setLisaOpacity(Float lisaOpacity) {
		this.lisaOpacity = lisaOpacity;
	}
 
	public String getSurveyId() {
		return surveyId;
	}
 
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}
 
	public String getFovColor() {
		return fovColor;
	}
 
	public void setFovColor(String fovColor) {
		this.fovColor = fovColor;
	}
 
	public static ReportDrivingSurvey getReportDrivingSurvey(String reportId, String surveyId) {
		ReportDrivingSurvey objReportDrivingSurvey = new ReportDrivingSurvey().get(reportId, surveyId);
		return objReportDrivingSurvey;
	}
 
	public ReportDrivingSurvey get(String reportId, String surveyId) {
		ReportDrivingSurvey objReportDrivingSurvey = null;
		
		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+reportId)) {
			objReportDrivingSurvey = (ReportDrivingSurvey)DBCache.INSTANCE.get(CACHE_KEY+reportId);
		} else {
			String SQL = "SELECT * FROM dbo.[ReportDrivingSurvey] WHERE ReportId='" + reportId + "' AND SurveyId='" + surveyId + "'";
			ArrayList<ReportDrivingSurvey> objReportDrivingSurveyList = load(SQL);
			if (objReportDrivingSurveyList!=null && objReportDrivingSurveyList.size()>0) {
				objReportDrivingSurvey = objReportDrivingSurveyList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + reportId, objReportDrivingSurvey);
			}
		}
		return objReportDrivingSurvey;
	}
 
	private static ReportDrivingSurvey loadFrom(ResultSet resultSet) {
		ReportDrivingSurvey objReportDrivingSurvey = new ReportDrivingSurvey();
		try {
			objReportDrivingSurvey.setIsotopicColor(resultSet.getString("IsotopicColor"));
			objReportDrivingSurvey.setId(resultSet.getString("Id"));
			objReportDrivingSurvey.setReportId(resultSet.getString("ReportId"));
			objReportDrivingSurvey.setLisaColor(resultSet.getString("LisaColor"));
			objReportDrivingSurvey.setFovOpacity(resultSet.getFloat("FovOpacity"));
			objReportDrivingSurvey.setPeakColor(resultSet.getString("PeakColor"));
			objReportDrivingSurvey.setLisaOpacity(resultSet.getFloat("LisaOpacity"));
			objReportDrivingSurvey.setSurveyId(resultSet.getString("SurveyId"));
			objReportDrivingSurvey.setFovColor(resultSet.getString("FovColor"));
		} catch (SQLException e) {
			Log.error("Class ReportDrivingSurvey | " + e.toString());
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
				DBCache.INSTANCE.set(CACHE_KEY + objReportDrivingSurvey.getReportId(), objReportDrivingSurvey);
			}
			
		} catch (SQLException e) {
			Log.error(e.toString());
		}
		
		return objReportDrivingSurveyList;
	}
}
