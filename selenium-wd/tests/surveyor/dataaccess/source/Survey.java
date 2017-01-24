package surveyor.dataaccess.source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.source.Log;

public class Survey extends BaseEntity {
	private static final String CACHE_KEY = "SURVEY.";

	private Date endDateTime;
	private String surveyModeTypeId;
	private String tag;
	private Float endEpoch;
	private String surveyorUnitId;
	private String stabilityClass;
	private String referenceGasBottleId;
	private Float minimumAmplitude;
	private String status;
	private Date startDateTime;
	private String id;
	private Date processingDateStarted;
	private String locationId;
	private Boolean deleted;
	private String buildNumber;
	private String analyzerId;
	private String userId;
	private Float startEpoch;

	public Survey() {
		super();
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getSurveyModeTypeId() {
		return surveyModeTypeId;
	}

	public void setSurveyModeTypeId(String surveyModeTypeId) {
		this.surveyModeTypeId = surveyModeTypeId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Float getEndEpoch() {
		return endEpoch;
	}

	public void setEndEpoch(Float endEpoch) {
		this.endEpoch = endEpoch;
	}

	public String getSurveyorUnitId() {
		return surveyorUnitId;
	}

	public void setSurveyorUnitId(String surveyorUnitId) {
		this.surveyorUnitId = surveyorUnitId;
	}

	public String getStabilityClass() {
		return stabilityClass;
	}

	public void setStabilityClass(String stabilityClass) {
		this.stabilityClass = stabilityClass;
	}

	public String getReferenceGasBottleId() {
		return referenceGasBottleId;
	}

	public void setReferenceGasBottleId(String referenceGasBottleId) {
		this.referenceGasBottleId = referenceGasBottleId;
	}

	public Float getMinimumAmplitude() {
		return minimumAmplitude;
	}

	public void setMinimumAmplitude(Float minimumAmplitude) {
		this.minimumAmplitude = minimumAmplitude;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getProcessingDateStarted() {
		return processingDateStarted;
	}

	public void setProcessingDateStarted(Date processingDateStarted) {
		this.processingDateStarted = processingDateStarted;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getBuildNumber() {
		return buildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}

	public String getAnalyzerId() {
		return analyzerId;
	}

	public void setAnalyzerId(String analyzerId) {
		this.analyzerId = analyzerId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Float getStartEpoch() {
		return startEpoch;
	}

	public void setStartEpoch(Float startEpoch) {
		this.startEpoch = startEpoch;
	}

	public static Survey getSurvey(String tag) {
		Survey objSurvey = null;
		List<Survey> objSurveyList = new Survey().get(tag);
		if (objSurveyList!=null && objSurveyList.size()>0) {
			objSurvey = objSurveyList.get(0);
			DBCache.INSTANCE.set(CACHE_KEY + tag, objSurvey);
		}
		return objSurvey;
	}

	public static List<Survey> getSurveys(String tag) {
		List<Survey> objSurveys = new Survey().get(tag);
		return objSurveys;
	}

	public static List<Survey> getSurveysForAnalyzer(String analyzerId) {
		List<Survey> objSurveys = new Survey().getForAnalyzerId(analyzerId);
		return objSurveys;
	}

	@SuppressWarnings("unchecked")
	public List<Survey> get(String tag) {
		ArrayList<Survey> objSurveyList = null;

		String SQL = "SELECT * FROM dbo.[Survey] WHERE Tag='" + tag + "'";
		objSurveyList = load(SQL);

		return objSurveyList;
	}

	public List<Survey> getById(String surveyId) {
		ArrayList<Survey> objSurveyList = null;

		String SQL = "SELECT * FROM dbo.[Survey] WHERE Id=" +surveyId;
		objSurveyList = load(SQL);

		return objSurveyList;
	}

	public List<Survey> getForAnalyzerId(String analyzerId) {
		ArrayList<Survey> objSurveyList = null;

		String SQL = "SELECT * FROM dbo.[Survey] WHERE AnalyzerId='" +analyzerId + "'";
		objSurveyList = load(SQL);

		return objSurveyList;
	}

	private static Survey loadFrom(ResultSet resultSet) {
		Survey objSurvey = new Survey();
		try {
			objSurvey.setEndDateTime(resultSet.getDate("EndDateTime"));
			objSurvey.setSurveyModeTypeId(resultSet.getString("SurveyModeTypeId"));
			objSurvey.setTag(resultSet.getString("Tag"));
			objSurvey.setEndEpoch(resultSet.getFloat("EndEpoch"));
			objSurvey.setSurveyorUnitId(resultSet.getString("SurveyorUnitId"));
			objSurvey.setStabilityClass(resultSet.getString("StabilityClass"));
			objSurvey.setReferenceGasBottleId(resultSet.getString("ReferenceGasBottleId"));
			objSurvey.setMinimumAmplitude(resultSet.getFloat("MinimumAmplitude"));
			objSurvey.setStatus(resultSet.getString("Status"));
			objSurvey.setStartDateTime(resultSet.getDate("StartDateTime"));
			objSurvey.setId(resultSet.getString("Id"));
			objSurvey.setProcessingDateStarted(resultSet.getDate("ProcessingDateStarted"));
			objSurvey.setLocationId(resultSet.getString("LocationId"));
			objSurvey.setDeleted(resultSet.getBoolean("Deleted"));
			objSurvey.setBuildNumber(resultSet.getString("BuildNumber"));
			objSurvey.setAnalyzerId(resultSet.getString("AnalyzerId"));
			objSurvey.setUserId(resultSet.getString("UserId"));
			objSurvey.setStartEpoch(resultSet.getFloat("StartEpoch"));
		} catch (SQLException e) {
			Log.error("Class Survey | " + e.toString());
		}

		return objSurvey;
	}

	public ArrayList<Survey> getAll() {
		String SQL = "SELECT * FROM dbo.[Survey]";
		return load(SQL);
	}

	public ArrayList<Survey> load(String SQL) {
		ArrayList<Survey> objSurveyList = new ArrayList<Survey>();

		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);

			while (resultSet.next()) {
				Survey objSurvey = loadFrom(resultSet);
				objSurveyList.add(objSurvey);

				// add to cache.
				DBCache.INSTANCE.set(CACHE_KEY + objSurvey.getTag(), objSurvey);
			}

		} catch (SQLException e) {
			Log.error(e.toString());
		}

		return objSurveyList;
	}
}
