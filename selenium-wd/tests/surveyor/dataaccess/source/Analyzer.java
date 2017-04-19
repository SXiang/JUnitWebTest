package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.source.ApiUtility;
import common.source.Log;

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

	public void cascadeDeleteAnalyzer() {
		List<String> queries = new ArrayList<String>();
		queries.add(String.format("DELETE [dbo].[AnalyzerAlarmLog] WHERE AnalyzerId='%s'", getId()));
		queries.add(String.format("DELETE [dbo].[AnalyzerHardwareCapabilityType] WHERE AnalyzerId='%s'", getId()));
		queries.add(String.format("DELETE [dbo].[AnalyzerHeartbeat] WHERE AnalyzerId='%s'", getId()));
		queries.add(String.format("DELETE [dbo].[AnalyzerLog] WHERE AnalyzerId='%s'", getId()));
		queries.add(String.format("DELETE [dbo].[AnalyzerSurveyorUnitHistory] WHERE AnalyzerId='%s'", getId()));
		queries.add(String.format("DELETE [dbo].[AnalyzerUpdateJob] WHERE AnalyzerId='%s'", getId()));
		queries.add(String.format("DELETE [dbo].[AnemometerRaw] WHERE AnalyzerId='%s'", getId()));
		queries.add(String.format("DELETE [dbo].[GPSRaw] WHERE AnalyzerId='%s'", getId()));
		queries.add(String.format("DELETE [dbo].[Measurement] WHERE AnalyzerId='%s'", getId()));
		queries.add(String.format("DELETE [dbo].[Note] WHERE AnalyzerId='%s'", getId()));
		queries.forEach(sql -> executeNonQuery(sql));

		// Delete surveys and associated reports.
		Survey.getSurveysForAnalyzer(getId().toString()).forEach(
			s -> {
				// Delete reports.
				ReportDrivingSurvey.getReportDrivingSurveysBySurveyId(s.getId().toString()).forEach(
						r -> ApiUtility.getApiResponse(String.format(ApiUtility.DELETE_COMPLIANCE_REPORTS_RELATIVE_URL, r.getReportId())));
				ReportEQDrivingSurvey.getReportEQDrivingSurveysBySurveyId(s.getId().toString()).forEach(
						r -> ApiUtility.getApiResponse(String.format(ApiUtility.DELETE_EQ_REPORTS_RELATIVE_URL, r.getReportEQId())));

				// Delete survey.
				ApiUtility.getApiResponse(String.format(ApiUtility.DELETE_MEASUREMENT_SESSION_RELATIVE_URL, s.getId()));
			});

		String SQL = String.format("DELETE [dbo].[Analyzer] WHERE SerialNumber='%s'", getSerialNumber());
		executeNonQuery(SQL);
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
}
