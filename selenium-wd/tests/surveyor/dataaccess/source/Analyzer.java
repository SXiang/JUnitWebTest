package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.source.ApiUtility;
import common.source.EnumUtility;
import common.source.Log;
import surveyor.scommon.entities.BaseReportEntity;
import surveyor.scommon.entities.BaseReportEntity.ReportType;

public class Analyzer extends BaseEntity {
	public static final String CACHE_KEY = "ANALYZER.";

	public static enum CapabilityType {
		Ethane ("Ethane", 0),
		IsotopicMethane ("IsotopicMethane", 1),
		Mast ("Mast", 2),
		InertialGPS ("InertialGPS", 3);

		private final String name;
		private final Integer colIndex;

		CapabilityType(String nm) {
			this(nm, -1);
		}

		CapabilityType(String nm, Integer colIdx) {
			name = nm;
			colIndex = colIdx;
		}

		public Integer getIndex() {
			return colIndex;
		}


		public String toString() {
			return this.name;
		}

		public static CapabilityType fromString(String value) {
			CapabilityType type = CapabilityType.IsotopicMethane;
			if (value.equals("P3300") || value.equals("Ethane")) {
				type = CapabilityType.Ethane;
			} else if (value.equals("P3200") || value.equals("IsotopicMethane")) {
				type = CapabilityType.IsotopicMethane;
			} else if (value.equals("Mast")) {
				type = CapabilityType.Mast;
			} else if (value.equals("InertialGPS")) {
				type = CapabilityType.InertialGPS;
			}

			return type;
		}
	}

	private Object id;
	private Object surveyorUnitId;
	private String sharedKey;
	private String serialNumber;

	public Analyzer() {
		super();
	}

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public Object getSurveyorUnitId() {
		return surveyorUnitId;
	}

	public void setSurveyorUnitId(Object surveyorUnitId) {
		this.surveyorUnitId = surveyorUnitId;
	}

	public String getSharedKey() {
		return sharedKey;
	}

	public void setSharedKey(String sharedKey) {
		this.sharedKey = sharedKey;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public static Analyzer getAnalyzer(String id) {
		Analyzer objAnalyzer = new Analyzer().get(id);
		return objAnalyzer;
	}

	public static Analyzer getAnalyzerBySerialNumber(String serialNum) {
		Analyzer objAnalyzer = new Analyzer().getBySerialNumber(serialNum);
		return objAnalyzer;
	}

	public static ArrayList<Analyzer> getAnalyzersForCustomer(String customerId) {
		return new Analyzer().getAnalyzersForCustomerInternal(customerId);
	}

	public Analyzer get(String id) {
		Analyzer objAnalyzer = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY + id)) {
			objAnalyzer = (Analyzer) DBCache.INSTANCE.get(CACHE_KEY + id);
		} else {
			String SQL = "SELECT * FROM dbo.[Analyzer] WHERE Id='" + id + "'";
			ArrayList<Analyzer> objAnalyzerList = load(SQL);
			if (objAnalyzerList != null && objAnalyzerList.size() > 0) {
				objAnalyzer = objAnalyzerList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + id, objAnalyzer);
			}
		}
		return objAnalyzer;
	}

	public List<CapabilityType> getCapabilityTypes() {
		String SQL = "SELECT HCT.Id, HCT.Name, HCT.Description FROM [dbo].[AnalyzerHardwareCapabilityType] AS AHCT"
				+ " INNER JOIN [dbo].[HardwareCapabilityTypes] AS HCT ON AHCT.HardwareCapabilityTypeId=HCT.Id"
				+ " WHERE AHCT.AnalyzerId = '" + this.getId() + "'";
		return loadCapability(SQL);
	}

	public Analyzer getBySerialNumber(String serialNum) {
		Analyzer objAnalyzer = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY + serialNum)) {
			objAnalyzer = (Analyzer) DBCache.INSTANCE.get(CACHE_KEY + serialNum);
		} else {
			String SQL = "SELECT * FROM dbo.[Analyzer] WHERE SerialNumber='" + serialNum + "'";
			ArrayList<Analyzer> objAnalyzerList = load(SQL);
			if (objAnalyzerList != null && objAnalyzerList.size() > 0) {
				objAnalyzer = objAnalyzerList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + serialNum, objAnalyzer);
			}
		}
		return objAnalyzer;
	}

	private static Analyzer loadFrom(ResultSet resultSet) {
		Analyzer objAnalyzer = new Analyzer();
		try {
			objAnalyzer.setId(resultSet.getObject("Id"));
			objAnalyzer.setSurveyorUnitId(resultSet.getObject("SurveyorUnitId"));
			objAnalyzer.setSharedKey(resultSet.getString("SharedKey"));
			objAnalyzer.setSerialNumber(resultSet.getString("SerialNumber"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objAnalyzer;
	}

	public ArrayList<Analyzer> getAll() {
		String SQL = "SELECT * FROM dbo.[Analyzer]";
		return load(SQL);
	}

	private ArrayList<Analyzer> getAnalyzersForCustomerInternal(String customerId) {
		String SQL = String.format("SELECT * FROM [dbo].[Analyzer] WHERE (SurveyorUnitId IN (SELECT [Id] FROM [dbo].[SurveyorUnit] "
				+ " WHERE [LocationId] IN (SELECT [Id] FROM [dbo].[Location] WHERE CustomerID='%s')))", customerId);
		return load(SQL);
	}

	public void cascadeDeleteAnalyzer() {
		List<String> queries = new ArrayList<String>();
		queries.add(String.format("DELETE [dbo].[AnalyzerAlarmLog] WHERE AnalyzerId='%s'", getId()));
		queries.add(String.format("DELETE [dbo].[AnalyzerHardwareCapabilityType] WHERE AnalyzerId='%s'", getId()));
		queries.add(String.format("DELETE [dbo].[AnalyzerHeartbeat] WHERE AnalyzerId='%s'", getId()));
		queries.add(String.format("DELETE [dbo].[AnalyzerLog] WHERE AnalyzerId='%s'", getId()));
		queries.add(String.format("DELETE [dbo].[AnalyzerUpdateJob] WHERE AnalyzerId='%s'", getId()));
		queries.add(String.format("DELETE [dbo].[AnemometerRaw] WHERE AnalyzerId='%s'", getId()));
		queries.add(String.format("DELETE [dbo].[GPSRaw] WHERE AnalyzerId='%s'", getId()));
		queries.add(String.format("DELETE [dbo].[Measurement] WHERE AnalyzerId='%s'", getId()));
		queries.forEach(sql -> executeNonQuery(sql));

		// Delete surveys and associated reports.
		Survey.getSurveysForAnalyzer(getId().toString()).forEach(
			s -> {
				// Delete reports.
				ReportDrivingSurvey.getReportDrivingSurveysBySurveyId(s.getId().toString()).forEach(
						r -> {
							String reportId = r.getReportId().toString();
							String reportTypeId = Report.getReportById(reportId).getReportTypeId();
							ReportType reportType = BaseReportEntity.ReportTypeGuids.get(reportTypeId);
							if (reportType.equals(ReportType.COMPLIANCE)) {
								Log.info(String.format("Deleting Compliance Report Id='%s', type='%s' for survey tag='%s'", reportId, reportTypeId, s.getTag()));
								ApiUtility.getApiResponse(String.format(ApiUtility.DELETE_COMPLIANCE_REPORTS_RELATIVE_URL, r.getReportId()));
							} else if (reportType.equals(ReportType.ASSESSMENT)) {
								Log.info(String.format("Deleting Assessment Report Id='%s', type='%s' for survey tag='%s'", reportId, reportTypeId, s.getTag()));
								ApiUtility.getApiResponse(String.format(ApiUtility.DELETE_ASSESSMENT_REPORTS_RELATIVE_URL, r.getReportId()));
							}
						});
				ReportEQDrivingSurvey.getReportEQDrivingSurveysBySurveyId(s.getId().toString()).forEach(
						r -> {
							String reportEQId = r.getReportEQId().toString();
							String reportTypeId = Report.getReportById(reportEQId).getReportTypeId();
							ReportType reportType = BaseReportEntity.ReportTypeGuids.get(reportTypeId);
							if (reportType.equals(ReportType.EQ)) {
								Log.info(String.format("Deleting EQ report Id='%s', type='%s' for survey tag='%s'", reportEQId, reportTypeId, s.getTag()));
								ApiUtility.getApiResponse(String.format(ApiUtility.DELETE_EQ_REPORTS_RELATIVE_URL, r.getReportEQId()));
							} else if (reportType.equals(ReportType.FACILITYEQ)) {
								Log.info(String.format("Deleting FacilityEQ report Id='%s', type='%s' for survey tag='%s'", reportEQId, reportTypeId, s.getTag()));
								ApiUtility.getApiResponse(String.format(ApiUtility.DELETE_FACILITY_EQ_REPORTS_RELATIVE_URL, r.getReportEQId()));
							}
						});

				// DE3052 prevents survey deletion when there is [EQMeasurementResult] or [ReportFieldOfViewAggregated] reference.
				// Currently explicitly deleting referenced [EQMeasurementResult] and [ReportFieldOfViewAggregated] rows to ensure Test Analyzers with certs can be reused.
				// Removing this workaround once DE3052 fixed tracked in automation user story - US4562
				List<String> surveyDepsQ = new ArrayList<String>();
				surveyDepsQ.add(String.format("DELETE [dbo].[EQMeasurementResult] WHERE SurveyId='%s'", s.getId()));
				surveyDepsQ.add(String.format("DELETE [dbo].[ReportFieldOfViewAggregated] WHERE SurveyId='%s'", s.getId()));
				surveyDepsQ.forEach(sql -> executeNonQuery(sql));

				// Delete survey.
				ApiUtility.getApiResponse(String.format(ApiUtility.DELETE_MEASUREMENT_SESSION_RELATIVE_URL, s.getId()));
			});

		String SQL = String.format("DELETE [dbo].[Analyzer] WHERE SerialNumber='%s'", getSerialNumber());
		executeNonQuery(SQL);
		DBCache.INSTANCE.remove(CACHE_KEY + getSerialNumber());
		DBCache.INSTANCE.remove(CACHE_KEY + getId());
	}

	public ArrayList<Analyzer> load(String SQL) {
		ArrayList<Analyzer> objAnalyzerList = new ArrayList<Analyzer>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				Analyzer objAnalyzer = loadFrom(resultSet);
				objAnalyzerList.add(objAnalyzer);

				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objAnalyzer.getId(), objAnalyzer);
			}

		} catch (SQLException e) {
			Log.error("Class Analyzer | " + e.toString());
		}

		return objAnalyzerList;
	}

	public void updateCapabilityType(CapabilityType capabilityType) {
		String SQL = String.format("UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=%d WHERE [AnalyzerId]=N'%s'; "
				+ "IF @@ROWCOUNT=0 INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'%s', %d);",
				capabilityType.getIndex(), getId(), getId(), capabilityType.getIndex());
		executeNonQuery(SQL);
	}


	public void addCapabilityType(CapabilityType capabilityType) {
		String SQL = String.format("UPDATE [dbo].[AnalyzerHardwareCapabilityType] SET [HardwareCapabilityTypeId]=%d WHERE [AnalyzerId]=N'%s' AND [HardwareCapabilityTypeId]=%d; "
				+ "IF @@ROWCOUNT=0 INSERT [dbo].[AnalyzerHardwareCapabilityType] ([AnalyzerId], [HardwareCapabilityTypeId]) VALUES (N'%s', %d);",
				capabilityType.getIndex(), getId(), capabilityType.getIndex(), getId(), capabilityType.getIndex());
		executeNonQuery(SQL);
	}

	public void removeCapabilityType(CapabilityType capabilityType) {
		String SQL = String.format("DELETE [dbo].[AnalyzerHardwareCapabilityType] WHERE [AnalyzerId]=N'%s' AND [HardwareCapabilityTypeId]=%d",
				getId(), capabilityType.getIndex());
		executeNonQuery(SQL);
	}

	private static CapabilityType loadCapabilityFrom(ResultSet resultSet) {
		CapabilityType capability = CapabilityType.Ethane;
		try {
			capability = EnumUtility.fromName(resultSet.getString("Description"), () -> CapabilityType.values());
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return capability;
	}

	private List<CapabilityType> loadCapability(String SQL) {
		List<CapabilityType> objCapabilityList = new ArrayList<CapabilityType>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				CapabilityType capability = loadCapabilityFrom(resultSet);
				objCapabilityList.add(capability);
			}

		} catch (SQLException e) {
			Log.error("Class Analyzer | " + e.toString());
		}

		return objCapabilityList;
	}
}
