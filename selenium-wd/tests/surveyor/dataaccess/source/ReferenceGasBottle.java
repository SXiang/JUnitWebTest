package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import common.source.Log;

public class ReferenceGasBottle extends BaseEntity {
	private static final String CACHE_KEY = "REFERENCEGASBOTTLE.";

	private Object id;
	private Object surveyorUnitId;
	private Float isotopicValue;
	private Date date;
	private String batchId;

	public ReferenceGasBottle() {
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

	public Float getIsotopicValue() {
		return isotopicValue;
	}

	public void setIsotopicValue(Float isotopicValue) {
		this.isotopicValue = isotopicValue;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public static ReferenceGasBottle getReferenceGasBottle(String id) {
		ReferenceGasBottle objReferenceGasBottle = new ReferenceGasBottle().get(id);
		return objReferenceGasBottle;
	}

	public static ReferenceGasBottle getReferenceGasBottleBySurveorId(String id) {
		ReferenceGasBottle objReferenceGasBottle = new ReferenceGasBottle().getTop1BySurveyorId(id);
		return objReferenceGasBottle;
	}

	public ReferenceGasBottle get(String id) {
		ReferenceGasBottle objReferenceGasBottle = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY + id)) {
			objReferenceGasBottle = (ReferenceGasBottle) DBCache.INSTANCE.get(CACHE_KEY + id);
		} else {
			String SQL = "SELECT * FROM dbo.[ReferenceGasBottle] WHERE Id='" + id + "'";
			ArrayList<ReferenceGasBottle> objReferenceGasBottleList = load(SQL);
			if (objReferenceGasBottleList != null && objReferenceGasBottleList.size() > 0) {
				objReferenceGasBottle = objReferenceGasBottleList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + id, objReferenceGasBottle);
			}
		}
		return objReferenceGasBottle;
	}

	public ReferenceGasBottle getTop1BySurveyorId(String surveyorId) {
		ReferenceGasBottle objReferenceGasBottle = null;

		// Get from cache if present. Else fetch from Database.
		if (DBCache.INSTANCE.containsKey(CACHE_KEY + surveyorId)) {
			objReferenceGasBottle = (ReferenceGasBottle) DBCache.INSTANCE.get(CACHE_KEY + surveyorId);
		} else {
			String SQL = "SELECT TOP 1 [Id],[SurveyorUnitId],[BatchId],[IsotopicValue],[Date] FROM dbo.[ReferenceGasBottle] WHERE SurveyorUnitId='" + surveyorId + "'order by date DESC";
			ArrayList<ReferenceGasBottle> objReferenceGasBottleList = load(SQL);
			if (objReferenceGasBottleList != null && objReferenceGasBottleList.size() > 0) {
				objReferenceGasBottle = objReferenceGasBottleList.get(0);
				DBCache.INSTANCE.set(CACHE_KEY + surveyorId, objReferenceGasBottle);
			}
		}
		return objReferenceGasBottle;
	}

	private static ReferenceGasBottle loadFrom(ResultSet resultSet) {
		ReferenceGasBottle objReferenceGasBottle = new ReferenceGasBottle();
		try {
			objReferenceGasBottle.setId(resultSet.getObject("Id"));
			objReferenceGasBottle.setSurveyorUnitId(resultSet.getObject("SurveyorUnitId"));
			objReferenceGasBottle.setIsotopicValue(resultSet.getFloat("IsotopicValue"));
			objReferenceGasBottle.setDate(resultSet.getDate("Date"));
			objReferenceGasBottle.setBatchId(resultSet.getString("BatchId"));
		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objReferenceGasBottle;
	}

	public ArrayList<ReferenceGasBottle> getAll() {
		String SQL = "SELECT * FROM dbo.[ReferenceGasBottle]";
		return load(SQL);
	}

	public ArrayList<ReferenceGasBottle> load(String SQL) {
		ArrayList<ReferenceGasBottle> objReferenceGasBottleList = new ArrayList<ReferenceGasBottle>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				ReferenceGasBottle objReferenceGasBottle = loadFrom(resultSet);
				objReferenceGasBottleList.add(objReferenceGasBottle);

				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objReferenceGasBottle.getId(), objReferenceGasBottle);
			}

		} catch (SQLException e) {
			Log.error("Class ReferenceGasBottle | " + e.toString());
		}

		return objReferenceGasBottleList;
	}
}
