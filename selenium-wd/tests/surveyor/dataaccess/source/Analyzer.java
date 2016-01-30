package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.source.Log;

public class Analyzer extends BaseEntity {
	private static final String CACHE_KEY = "ANALYZER.";

	private Object id;
	private Object surveyorUnitId;
	private Float sharedKey;
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

	public Float getSharedKey() {
		return sharedKey;
	}

	public void setSharedKey(Float sharedKey) {
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

	private static Analyzer loadFrom(ResultSet resultSet) {
		Analyzer objAnalyzer = new Analyzer();
		try {
			objAnalyzer.setId(resultSet.getObject("Id"));
			objAnalyzer.setSurveyorUnitId(resultSet.getObject("SurveyorUnitId"));
			objAnalyzer.setSharedKey(resultSet.getFloat("SharedKey"));
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
}
