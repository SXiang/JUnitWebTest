package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.source.Log;

public class AnalyticsPeak extends BaseEntity {
	private static final String CACHE_KEY = "ANALYTICSPEAK.";

	private Boolean isFiltered;
	private Object reportId;
	private Float priorityScore;
	private Float medianPairwiseDistance;
	private Integer numberOfPasses;
	private Float emissionRate;
	private Float detectionProbability;
	private Float emissionRateUncertainty;
	private Object reportPeakId;
	private Integer numberOfPeaks;
	private Integer rankingGroup;

	public AnalyticsPeak() {
		super();
	}

	public AnalyticsPeak(Boolean isFiltered, Object reportId, Float priorityScore,
			Float medianPairwiseDistance, Integer numberOfPasses, Float emissionRate,
			Float detectionProbability, Float emissionRateUncertainty, Object reportPeakId,
			Integer numberOfPeaks, Integer rankingGroup) {
		super();
		this.isFiltered = isFiltered;
		this.reportId = reportId;
		this.priorityScore = priorityScore;
		this.medianPairwiseDistance = medianPairwiseDistance;
		this.numberOfPasses = numberOfPasses;
		this.emissionRate = emissionRate;
		this.detectionProbability = detectionProbability;
		this.emissionRateUncertainty = emissionRateUncertainty;
		this.reportPeakId = reportPeakId;
		this.numberOfPeaks = numberOfPeaks;
		this.rankingGroup = rankingGroup;
	}

	public Boolean getIsFiltered() {
		return isFiltered;
	}

	public void setIsFiltered(Boolean isFiltered) {
		this.isFiltered = isFiltered;
	}

	public Object getReportId() {
		return reportId;
	}

	public void setReportId(Object reportId) {
		this.reportId = reportId;
	}

	public Float getPriorityScore() {
		return priorityScore;
	}

	public void setPriorityScore(Float priorityScore) {
		this.priorityScore = priorityScore;
	}

	public Float getMedianPairwiseDistance() {
		return medianPairwiseDistance;
	}

	public void setMedianPairwiseDistance(Float medianPairwiseDistance) {
		this.medianPairwiseDistance = medianPairwiseDistance;
	}

	public Integer getNumberOfPasses() {
		return numberOfPasses;
	}

	public void setNumberOfPasses(Integer numberOfPasses) {
		this.numberOfPasses = numberOfPasses;
	}

	public Float getEmissionRate() {
		return emissionRate;
	}

	public void setEmissionRate(Float emissionRate) {
		this.emissionRate = emissionRate;
	}

	public Float getDetectionProbability() {
		return detectionProbability;
	}

	public void setDetectionProbability(Float detectionProbability) {
		this.detectionProbability = detectionProbability;
	}

	public Float getEmissionRateUncertainty() {
		return emissionRateUncertainty;
	}

	public void setEmissionRateUncertainty(Float emissionRateUncertainty) {
		this.emissionRateUncertainty = emissionRateUncertainty;
	}

	public Object getReportPeakId() {
		return reportPeakId;
	}

	public void setReportPeakId(Object reportPeakId) {
		this.reportPeakId = reportPeakId;
	}

	public Integer getNumberOfPeaks() {
		return numberOfPeaks;
	}

	public void setNumberOfPeaks(Integer numberOfPeaks) {
		this.numberOfPeaks = numberOfPeaks;
	}

	public Integer getRankingGroup() {
		return rankingGroup;
	}

	public void setRankingGroup(Integer rankingGroup) {
		this.rankingGroup = rankingGroup;
	}

	public static AnalyticsPeak getAnalyticsPeak(String reportPeakId) {
		AnalyticsPeak objAnalyticsPeak = new AnalyticsPeak().get(reportPeakId);
		return objAnalyticsPeak;
	}

	public AnalyticsPeak get(String reportPeakId) {
		AnalyticsPeak objAnalyticsPeak = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY+reportPeakId)) {
			objAnalyticsPeak = (AnalyticsPeak)DBCache.INSTANCE.get(CACHE_KEY+reportPeakId);
		} else {
			String SQL = "SELECT * FROM dbo.[AnalyticsPeak] WHERE ReportPeakId='" + reportPeakId + "'";
			ArrayList<AnalyticsPeak> objAnalyticsPeakList = load(SQL);
			if (objAnalyticsPeakList!=null && objAnalyticsPeakList.size()>0) {
				objAnalyticsPeak = objAnalyticsPeakList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + reportPeakId, objAnalyticsPeak);
			}
		}
		return objAnalyticsPeak;
	}

	public static List<AnalyticsPeak> getAnalyticsPeaks(String reportId) {
		String SQL = "SELECT * FROM dbo.[AnalyticsPeak] WHERE ReportId='" + reportId + "'";
		AnalyticsPeak objPeak = new AnalyticsPeak();
		return objPeak.load(SQL);
	}

	private static AnalyticsPeak loadFrom(ResultSet resultSet) {
		AnalyticsPeak objAnalyticsPeak = new AnalyticsPeak();
		try {
			objAnalyticsPeak.setIsFiltered(resultSet.getBoolean("IsFiltered"));
			objAnalyticsPeak.setReportId(resultSet.getObject("ReportId"));
			objAnalyticsPeak.setPriorityScore(getFloatColumnValue(resultSet,"PriorityScore"));
			objAnalyticsPeak.setMedianPairwiseDistance(getFloatColumnValue(resultSet,"MedianPairwiseDistance"));
			objAnalyticsPeak.setNumberOfPasses(getIntColumnValue(resultSet,"NumberOfPasses"));
			objAnalyticsPeak.setEmissionRate(getFloatColumnValue(resultSet,"EmissionRate"));
			objAnalyticsPeak.setDetectionProbability(getFloatColumnValue(resultSet,"DetectionProbability"));
			objAnalyticsPeak.setEmissionRateUncertainty(getFloatColumnValue(resultSet,"EmissionRateUncertainty"));
			objAnalyticsPeak.setReportPeakId(resultSet.getObject("ReportPeakId"));
			objAnalyticsPeak.setNumberOfPeaks(getIntColumnValue(resultSet,"NumberOfPeaks"));
			objAnalyticsPeak.setRankingGroup(getIntColumnValue(resultSet,"RankingGroup"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objAnalyticsPeak;
	}

	public ArrayList<AnalyticsPeak> getAll() {
		String SQL = "SELECT * FROM dbo.[AnalyticsPeak]";
		return load(SQL);
	}

	public ArrayList<AnalyticsPeak> load(String SQL) {
		ArrayList<AnalyticsPeak> objAnalyticsPeakList = new ArrayList<AnalyticsPeak>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				AnalyticsPeak objAnalyticsPeak = loadFrom(resultSet);
				objAnalyticsPeakList.add(objAnalyticsPeak);

				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objAnalyticsPeak.getReportPeakId(), objAnalyticsPeak);
			}

		} catch (SQLException e) {
			Log.error("Class AnalyticsPeak | " + e.toString());
		}

		return objAnalyticsPeakList;
	}
}
