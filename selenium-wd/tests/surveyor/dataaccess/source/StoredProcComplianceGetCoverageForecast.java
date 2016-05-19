package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;

import common.source.Log;

public class StoredProcComplianceGetCoverageForecast extends BaseEntity {
	private String percentageWithLisa;
	private String percentageWithoutLisa;
	private String coverageProbability0;
	private String coverageProbability1;
	private String coverageProbability2;

	public StoredProcComplianceGetCoverageForecast() {
		super();
	}
	
	public String getPercentageWithLisa() {
		return percentageWithLisa;
	}

	public void setPercentageWithLisa(String percentageWithLisa) {
		this.percentageWithLisa = trim(percentageWithLisa);
	}

	public String getPercentageWithoutLisa() {
		return percentageWithoutLisa;
	}

	public void setPercentageWithoutLisa(String percentageWithoutLisa) {
		this.percentageWithoutLisa = trim(percentageWithoutLisa);
	}


	public String getCoverageProbability0() {
		return coverageProbability0;
	}

	public void setCoverageProbability0(String coverageProbability0) {
		this.coverageProbability0 = trim(coverageProbability0);
	}

	public String getCoverageProbability1() {
		return coverageProbability1;
	}

	public void setCoverageProbability1(String coverageProbability1) {
		this.coverageProbability1 = trim(coverageProbability1);
	}

	public String getCoverageProbability2() {
		return coverageProbability2;
	}

	public void setCoverageProbability2(String coverageProbability2) {
		this.coverageProbability2 = trim(coverageProbability2);
	}
	public boolean isCoverageValuesEquals(StoredProcComplianceGetCoverageForecast obj) {
	    return isCoverageValuesEquals(obj,true);
	}
	public boolean isCoverageValuesEquals(StoredProcComplianceGetCoverageForecast obj, boolean withPredication) {
		String thisPercentageWithLisa = getPercentageWithLisa();
		String objPercentageWithLisa = obj.getPercentageWithLisa();
		String thisPercentageWithoutLisa = getPercentageWithoutLisa();
		String objPercentageWithoutLisa = obj.getPercentageWithoutLisa();		
		if(withPredication){
			String thisCoverageProbability0 = getCoverageProbability0();
			String objCoverageProbability0 = obj.getCoverageProbability0();
			String thisCoverageProbability1 = getCoverageProbability1();
			String objCoverageProbability1 = obj.getCoverageProbability1();
			String thisCoverageProbability2 = getCoverageProbability2();
			String objCoverageProbability2 = obj.getCoverageProbability2();
			return thisPercentageWithLisa.equals(objPercentageWithLisa)
					&&thisPercentageWithoutLisa.equals(objPercentageWithoutLisa)
					&&thisCoverageProbability0.equals(objCoverageProbability0)
					&&thisCoverageProbability1.equals(objCoverageProbability1)
					&&thisCoverageProbability2.equals(objCoverageProbability2);
		}
		return thisPercentageWithLisa.equals(objPercentageWithLisa)
				&&thisPercentageWithoutLisa.equals(objPercentageWithoutLisa);
	}

	public boolean isCoverageValuesFormated(StoredProcComplianceGetCoverageForecast obj, boolean withPredication) {
		String invalidChars = ".";
		if (obj.getPercentageWithLisa().contains(invalidChars)) {
			return false;
		}
		if (obj.getPercentageWithoutLisa().contains(invalidChars)) {
			return false;
		}
		if(withPredication){
			if (obj.getCoverageProbability0().contains(invalidChars)) {
				return false;
			}
			if (obj.getCoverageProbability0().contains(invalidChars)) {
				return false;
			}
			if (obj.getCoverageProbability0().contains(invalidChars)) {
				return false;
			}
		}
		return true;
	}
	
	public static StoredProcComplianceGetCoverageForecast getCoverage(String reportId) {
		StoredProcComplianceGetCoverageForecast objStoredProcCoverageForecast = new StoredProcComplianceGetCoverageForecast().get(reportId);
		return objStoredProcCoverageForecast;
	}

	public StoredProcComplianceGetCoverageForecast get(String reportId) {
		StoredProcComplianceGetCoverageForecast objCoverageForecast = loadCoverageLisa(reportId);
		objCoverageForecast = appendCoveragePredication(reportId, objCoverageForecast);
		return objCoverageForecast;
	}

	public StoredProcComplianceGetCoverageForecast loadCoverageLisaFrom(ResultSet resultSet) {
		StoredProcComplianceGetCoverageForecast objCoverageLisa = new StoredProcComplianceGetCoverageForecast();
		return appendCoverageLisaFrom(resultSet, objCoverageLisa);
	}
	
	private StoredProcComplianceGetCoverageForecast appendCoverageLisaFrom(ResultSet resultSet,StoredProcComplianceGetCoverageForecast objCoverageLisa) {
		try {
			objCoverageLisa.setPercentageWithLisa(resultSet.getString("PercentageWithLisa"));
			objCoverageLisa.setPercentageWithoutLisa(resultSet.getString("PercentageWithoutLisa"));
		} catch (SQLException e) {
			Log.error("Class StoredProcComplianceGetCoverageForecast - load coverage lisa| " + e.toString());
		}

		return objCoverageLisa;
	}
	
	public StoredProcComplianceGetCoverageForecast loadCoverageForecastFrom(ResultSet resultSet) {
		StoredProcComplianceGetCoverageForecast objCoveragePredication = new StoredProcComplianceGetCoverageForecast();
		return appendCoverageForecastFrom(resultSet, objCoveragePredication);
	}
	private StoredProcComplianceGetCoverageForecast appendCoverageForecastFrom(ResultSet resultSet,StoredProcComplianceGetCoverageForecast objCoveragePredication) {
		try {		
			if(resultSet.next())
			    objCoveragePredication.setCoverageProbability0((resultSet).getString("CoverageProbability"));
			if(resultSet.next())
			    objCoveragePredication.setCoverageProbability1((resultSet).getString("CoverageProbability"));
			if(resultSet.next())
			    objCoveragePredication.setCoverageProbability2((resultSet).getString("CoverageProbability"));
		} catch (SQLException e) {
			Log.error("Class StoredProcComplianceGetCoverageForecast - load predication | " + e.toString());
		}

		return objCoveragePredication;
	}
	
	public StoredProcComplianceGetCoverageForecast loadCoveragePredication(String reportId) {
		StoredProcComplianceGetCoverageForecast objCoveragePredication = new StoredProcComplianceGetCoverageForecast();
		return appendCoveragePredication(reportId, objCoveragePredication);
	}
	
	private  StoredProcComplianceGetCoverageForecast appendCoveragePredication(String reportId, StoredProcComplianceGetCoverageForecast objCoveragePredication) {

		try {
			CallableStatement proc_stmt = connection.prepareCall("{ call Compliance_GetCoveragePrediction(?) }");
			proc_stmt.setString(1, reportId);
			resultSet = proc_stmt.executeQuery();

			objCoveragePredication = appendCoverageForecastFrom(resultSet, objCoveragePredication);

		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objCoveragePredication;
	}
	
	public StoredProcComplianceGetCoverageForecast loadCoverageLisa(String reportId) {
		StoredProcComplianceGetCoverageForecast objCoverageLisa = new StoredProcComplianceGetCoverageForecast();
		return appendCoverageLisa(reportId, objCoverageLisa);
	}
	
	private StoredProcComplianceGetCoverageForecast appendCoverageLisa(String reportId,StoredProcComplianceGetCoverageForecast objCoverageLisa) {
		try {
			CallableStatement proc_stmt = connection.prepareCall("{ call Compliance_GetCoverageResult(?) }");
			proc_stmt.setString(1, reportId);
			resultSet = proc_stmt.executeQuery();
			if (resultSet.next()) {
				objCoverageLisa = appendCoverageLisaFrom(resultSet, objCoverageLisa);
			}

		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objCoverageLisa;
	}
}
