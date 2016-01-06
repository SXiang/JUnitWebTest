package surveyor.dataaccess.source;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import common.source.Log;
 
public class ReportCompliance extends BaseEntity {
	private static final String CACHE_KEY = "REPORTCOMPLIANCE.";
 
	private Float percentCoverageAssets;
	private Float exclusionRadius;
	private String surveyModeTypeId;
	private Boolean isGapSurveyComplete;
	private Float minimumAmplitude;
	private Boolean showPercentCoverageReportArea;
	private String reportId;
	private Date reportStartDateTime;
	private Float lisaOpacity;
	private Float percentCoverageReportArea;
	private Object gap;
	private Boolean showGaps;
	private Float mapBufferArea;
	private Boolean isCGIInvestigationComplete;
	private Boolean showPercentCoverageAssets;
	private Float fovOpacity;
	private Boolean isLisaInvestigationComplete;
	private Boolean showIsotopicAnalysis;
	private Boolean showIndications;
	private Boolean showPercentCoverageForecast;
 
	public ReportCompliance() {
		super();
	}
 
	public Float getPercentCoverageAssets() {
		return percentCoverageAssets;
	}
 
	public void setPercentCoverageAssets(Float percentCoverageAssets) {
		this.percentCoverageAssets = percentCoverageAssets;
	}
 
	public Float getExclusionRadius() {
		return exclusionRadius;
	}
 
	public void setExclusionRadius(Float exclusionRadius) {
		this.exclusionRadius = exclusionRadius;
	}
 
	public String getSurveyModeTypeId() {
		return surveyModeTypeId;
	}
 
	public void setSurveyModeTypeId(String surveyModeTypeId) {
		this.surveyModeTypeId = surveyModeTypeId;
	}
 
	public Boolean getIsGapSurveyComplete() {
		return isGapSurveyComplete;
	}
 
	public void setIsGapSurveyComplete(Boolean isGapSurveyComplete) {
		this.isGapSurveyComplete = isGapSurveyComplete;
	}
 
	public Float getMinimumAmplitude() {
		return minimumAmplitude;
	}
 
	public void setMinimumAmplitude(Float minimumAmplitude) {
		this.minimumAmplitude = minimumAmplitude;
	}
 
	public Boolean getShowPercentCoverageReportArea() {
		return showPercentCoverageReportArea;
	}
 
	public void setShowPercentCoverageReportArea(Boolean showPercentCoverageReportArea) {
		this.showPercentCoverageReportArea = showPercentCoverageReportArea;
	}
 
	public String getReportId() {
		return reportId;
	}
 
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
 
	public Date getReportStartDateTime() {
		return reportStartDateTime;
	}
 
	public void setReportStartDateTime(Date ReportStartDateTime) {
		this.reportStartDateTime = ReportStartDateTime;
	}
 
	public Float getLisaOpacity() {
		return lisaOpacity;
	}
 
	public void setLisaOpacity(Float lisaOpacity) {
		this.lisaOpacity = lisaOpacity;
	}
 
	public Float getPercentCoverageReportArea() {
		return percentCoverageReportArea;
	}
 
	public void setPercentCoverageReportArea(Float percentCoverageReportArea) {
		this.percentCoverageReportArea = percentCoverageReportArea;
	}
 
	public Object getGap() {
		return gap;
	}
 
	public void setGap(Object gap) {
		this.gap = gap;
	}
 
	public Boolean getShowGaps() {
		return showGaps;
	}
 
	public void setShowGaps(Boolean showGaps) {
		this.showGaps = showGaps;
	}
 
	public Float getMapBufferArea() {
		return mapBufferArea;
	}
 
	public void setMapBufferArea(Float mapBufferArea) {
		this.mapBufferArea = mapBufferArea;
	}
 
	public Boolean getIsCGIInvestigationComplete() {
		return isCGIInvestigationComplete;
	}
 
	public void setIsCGIInvestigationComplete(Boolean isCGIInvestigationComplete) {
		this.isCGIInvestigationComplete = isCGIInvestigationComplete;
	}
 
	public Boolean getShowPercentCoverageAssets() {
		return showPercentCoverageAssets;
	}
 
	public void setShowPercentCoverageAssets(Boolean showPercentCoverageAssets) {
		this.showPercentCoverageAssets = showPercentCoverageAssets;
	}
 
	public Float getFovOpacity() {
		return fovOpacity;
	}
 
	public void setFovOpacity(Float fovOpacity) {
		this.fovOpacity = fovOpacity;
	}
 
	public Boolean getIsLisaInvestigationComplete() {
		return isLisaInvestigationComplete;
	}
 
	public void setIsLisaInvestigationComplete(Boolean isLisaInvestigationComplete) {
		this.isLisaInvestigationComplete = isLisaInvestigationComplete;
	}
 
	public Boolean getShowIsotopicAnalysis() {
		return showIsotopicAnalysis;
	}
 
	public void setShowIsotopicAnalysis(Boolean showIsotopicAnalysis) {
		this.showIsotopicAnalysis = showIsotopicAnalysis;
	}
 
	public Boolean getShowIndications() {
		return showIndications;
	}
 
	public void setShowIndications(Boolean showIndications) {
		this.showIndications = showIndications;
	}
 
	public Boolean getShowPercentCoverageForecast() {
		return showPercentCoverageForecast;
	}
 
	public void setShowPercentCoverageForecast(Boolean showPercentCoverageForecast) {
		this.showPercentCoverageForecast = showPercentCoverageForecast;
	}
 
	public static ReportCompliance getReportCompliance(String reportId) {
		ReportCompliance objReportCompliance = new ReportCompliance().get(reportId);
		return objReportCompliance;
	}
 
	public ReportCompliance get(String reportId) {
		ReportCompliance objReportCompliance = null;
		
		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+reportId)) {
			objReportCompliance = (ReportCompliance)DBCache.INSTANCE.get(CACHE_KEY+reportId);
		} else {
			String SQL = "SELECT * FROM dbo.[ReportCompliance] WHERE ReportId='" + reportId + "'";
			ArrayList<ReportCompliance> objReportComplianceList = load(SQL);
			if (objReportComplianceList!=null && objReportComplianceList.size()>0) {
				objReportCompliance = objReportComplianceList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + reportId, objReportCompliance);
			}
		}
		return objReportCompliance;
	}
 
	private static ReportCompliance loadFrom(ResultSet resultSet) {
		ReportCompliance objReportCompliance = new ReportCompliance();
		try {
			objReportCompliance.setPercentCoverageAssets(resultSet.getFloat("PercentCoverageAssets"));
			objReportCompliance.setExclusionRadius(resultSet.getFloat("ExclusionRadius"));
			objReportCompliance.setSurveyModeTypeId(resultSet.getString("SurveyModeTypeId"));
			objReportCompliance.setIsGapSurveyComplete(resultSet.getBoolean("IsGapSurveyComplete"));
			objReportCompliance.setMinimumAmplitude(resultSet.getFloat("MinimumAmplitude"));
			objReportCompliance.setShowPercentCoverageReportArea(resultSet.getBoolean("ShowPercentCoverageReportArea"));
			objReportCompliance.setReportId(resultSet.getString("ReportId"));
			objReportCompliance.setReportStartDateTime(resultSet.getDate("ReportStartDateTime"));
			objReportCompliance.setLisaOpacity(resultSet.getFloat("LisaOpacity"));
			objReportCompliance.setPercentCoverageReportArea(resultSet.getFloat("PercentCoverageReportArea"));
			objReportCompliance.setGap(resultSet.getObject("Gap"));
			objReportCompliance.setShowGaps(resultSet.getBoolean("ShowGaps"));
			objReportCompliance.setMapBufferArea(resultSet.getFloat("MapBufferArea"));
			objReportCompliance.setIsCGIInvestigationComplete(resultSet.getBoolean("IsCGIInvestigationComplete"));
			objReportCompliance.setShowPercentCoverageAssets(resultSet.getBoolean("ShowPercentCoverageAssets"));
			objReportCompliance.setFovOpacity(resultSet.getFloat("FovOpacity"));
			objReportCompliance.setIsLisaInvestigationComplete(resultSet.getBoolean("IsLisaInvestigationComplete"));
			objReportCompliance.setShowIsotopicAnalysis(resultSet.getBoolean("ShowIsotopicAnalysis"));
			objReportCompliance.setShowIndications(resultSet.getBoolean("ShowIndications"));
			objReportCompliance.setShowPercentCoverageForecast(resultSet.getBoolean("ShowPercentCoverageForecast"));
		} catch (SQLException e) {
			Log.error("Class ReportCompliance | " + e.toString());
		}

		return objReportCompliance;
	}
	
	public ArrayList<ReportCompliance> getAll() {
		String SQL = "SELECT * FROM dbo.[ReportCompliance]";
		return load(SQL);
	}
 
	public ArrayList<ReportCompliance> load(String SQL) {
		ArrayList<ReportCompliance> objReportComplianceList = new ArrayList<ReportCompliance>();
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);
			
			while (resultSet.next()) {
				ReportCompliance objReportCompliance = loadFrom(resultSet);
				objReportComplianceList.add(objReportCompliance);
				
				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objReportCompliance.getReportId(), objReportCompliance);
			}
			
		} catch (SQLException e) {
			Log.error(e.toString());
		}
		
		return objReportComplianceList;
	}
}
