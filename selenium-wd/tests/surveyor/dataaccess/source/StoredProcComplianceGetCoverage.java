package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.CallableStatement;

import common.source.Log;

public class StoredProcComplianceGetCoverage extends BaseEntity {
	private Integer showPercentCoverageAssetsasINT;
	private Integer showPercentCoverageReportAreaasINT;
	private Integer showPercentCoverageForecastasINT;
	private String showPercentCoverageAssets;
	private String showPercentCoverageReportArea;
	private String showPercentCoverageForecast;
	private String percentCoverageAssets;
	private String percentCoverageReportArea;
	

	public StoredProcComplianceGetCoverage() {
		super();
	}

	

	public Integer getShowPercentCoverageAssetsasINT() {
		return this.showPercentCoverageAssetsasINT;
	}



	public void setShowPercentCoverageAssetsasINT(Integer showPercentCoverageAssetsasINT) {
		this.showPercentCoverageAssetsasINT = showPercentCoverageAssetsasINT;
	}



	public Integer getShowPercentCoverageReportAreaasINT() {
		return this.showPercentCoverageReportAreaasINT;
	}



	public void setShowPercentCoverageReportAreaasINT(Integer showPercentCoverageReportAreaasINT) {
		this.showPercentCoverageReportAreaasINT = showPercentCoverageReportAreaasINT;
	}



	public Integer getShowPercentCoverageForecastasINT() {
		return this.showPercentCoverageForecastasINT;
	}



	public void setShowPercentCoverageForecastasINT(Integer showPercentCoverageForecastasINT) {
		this.showPercentCoverageForecastasINT = showPercentCoverageForecastasINT;
	}



	public String getShowPercentCoverageAssets() {
		return this.showPercentCoverageAssets;
	}



	public void setShowPercentCoverageAssets(String showPercentCoverageAssets) {
		this.showPercentCoverageAssets = showPercentCoverageAssets;
	}



	public String getShowPercentCoverageReportArea() {
		return this.showPercentCoverageReportArea;
	}



	public void setShowPercentCoverageReportArea(String showPercentCoverageReportArea) {
		this.showPercentCoverageReportArea = showPercentCoverageReportArea;
	}



	public String getShowPercentCoverageForecast() {
		return this.showPercentCoverageForecast;
	}



	public void setShowPercentCoverageForecast(String showPercentCoverageForecast) {
		this.showPercentCoverageForecast = showPercentCoverageForecast;
	}



	public String getPercentCoverageAssets() {
		return this.percentCoverageAssets;
	}



	public void setPercentCoverageAssets(String percentCoverageAssets) {
		this.percentCoverageAssets = percentCoverageAssets;
	}



	public String getPercentCoverageReportArea() {
		return this.percentCoverageReportArea;
	}



	public void setPercentCoverageReportArea(String percentCoverageReportArea) {
		this.percentCoverageReportArea = percentCoverageReportArea;
	}



	public boolean isCoverageValuesEquals(StoredProcComplianceGetCoverage obj) {
		if (!((this.getPercentCoverageAssets().replaceAll("%", "").trim()).equals(obj.getPercentCoverageAssets().trim()))) {
			return false;
		}
		if (!((this.getPercentCoverageReportArea().replaceAll("%", "").trim()).equals(obj.getPercentCoverageReportArea().trim()))) {
			return false;
		}
		return true;
	}
	
	public static StoredProcComplianceGetCoverage getCoverage(String reportId) {
		StoredProcComplianceGetCoverage objStoredProcCoverage = new StoredProcComplianceGetCoverage().get(reportId);
		return objStoredProcCoverage;
	}

	
	public StoredProcComplianceGetCoverage get(String reportId) {
		StoredProcComplianceGetCoverage objReportList = load(reportId);
		return objReportList;
	}


	private StoredProcComplianceGetCoverage loadFrom(ResultSet resultSet) {
		StoredProcComplianceGetCoverage objReport = new StoredProcComplianceGetCoverage();
		try {
			objReport.setShowPercentCoverageAssetsasINT(resultSet.getInt("ShowPercentCoverageAssetsasINT"));
			objReport.setShowPercentCoverageReportAreaasINT(resultSet.getInt("ShowPercentCoverageReportAreaasINT"));
			objReport.setShowPercentCoverageForecastasINT(resultSet.getInt("ShowPercentCoverageForecastasINT"));
			objReport.setShowPercentCoverageAssets(resultSet.getString("ShowPercentCoverageAssets"));
			objReport.setShowPercentCoverageReportArea(resultSet.getString("ShowPercentCoverageReportArea"));
			objReport.setShowPercentCoverageForecast(resultSet.getString("ShowPercentCoverageForecast"));
			objReport.setPercentCoverageAssets(resultSet.getString("PercentCoverageAssets"));
			objReport.setPercentCoverageReportArea(resultSet.getString("PercentCoverageReportArea"));			
			
		} catch (SQLException e) {
			Log.error("Class Report | " + e.toString());
		}

		return objReport;
	}

	public StoredProcComplianceGetCoverage load(String reportId) {
		StoredProcComplianceGetCoverage objReport = new StoredProcComplianceGetCoverage();

		try {
			CallableStatement proc_stmt = connection.prepareCall("{ call Compliance_GetCoverage(?) }");
			proc_stmt.setString(1, reportId);
			resultSet = proc_stmt.executeQuery();
			while (resultSet.next()) {
				objReport = loadFrom(resultSet);
							}

		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objReport;
	}

}
