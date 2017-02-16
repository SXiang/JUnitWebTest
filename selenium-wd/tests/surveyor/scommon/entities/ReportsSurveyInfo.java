package surveyor.scommon.entities;

import surveyor.scommon.entities.BaseReportEntity.SurveyModeFilter;

public class ReportsSurveyInfo {

	public enum ColumnHeaders {
		User ("User", 1),
		Surveyor ("Surveyor", 2),
		StartDate ("Start Date/Time", 3),
		EndDate ("End Date/Time", 4),
		SurveyType ("Type", 5),
		Tag ("Tag", 6),
		IsSurveySelected ("Selected?", 7);

		private final Integer index;
		private final String name;

		ColumnHeaders(String nm, Integer idx) {
			name = nm;
			index = idx;
		}

		public String getName() {
			return this.name;
		}

		public Integer getIndex() {
			return this.index;
		}
	}

	private String surveyor;
	private String username;
	private String tag;
	private String startDate;
	private String endDate;
	private SurveyModeFilter surveyModeFilter;
	private Integer numberOfSurveysToSelect;
	private boolean selectAllSurveys;
	private boolean isGeoFilterOn;

	public ReportsSurveyInfo(String surveyor, String username, String tag, String startDate, String endDate, SurveyModeFilter surveyModeFilter,
			boolean isGeoFilterOn, Integer numberOfSurveysToSelect, boolean selectAllSurveys) {
		this.surveyor = surveyor;
		this.username = username;
		this.tag = tag;
		this.startDate = startDate;
		this.endDate = endDate;
		this.surveyModeFilter = surveyModeFilter;
		this.numberOfSurveysToSelect = numberOfSurveysToSelect;
		this.selectAllSurveys = selectAllSurveys;
		this.isGeoFilterOn = isGeoFilterOn;
	}

	public boolean isGeoFilterOn() {
		return isGeoFilterOn;
	}
	public void setGeoFilterOn(boolean isGeoFilterOn) {
		this.isGeoFilterOn = isGeoFilterOn;
	}
	public SurveyModeFilter getSurveyModeFilter() {
		return surveyModeFilter;
	}
	public void setSurveyModeFilter(SurveyModeFilter surveyModeFilter) {
		this.surveyModeFilter = surveyModeFilter;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getNumberOfSurveysToSelect() {
		return numberOfSurveysToSelect;
	}
	public void setNumberOfSurveysToSelect(Integer numberOfSurveysToSelect) {
		this.numberOfSurveysToSelect = numberOfSurveysToSelect;
	}
	public String getSurveyor() {
		return surveyor;
	}
	public void setSurveyor(String surveyor) {
		this.surveyor = surveyor;
	}
	public boolean isSelectAllSurveys() {
		return selectAllSurveys;
	}
	public void setSelectAllSurveys(boolean selectAllSurveys) {
		this.selectAllSurveys = selectAllSurveys;
	}
}