package surveyor.scommon.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CustomerSurveyInfoEntity {

	private Integer surveyRuntimeInSeconds;
	private Integer locationRowID;
	private Integer surveyorRowID;
	private Integer refGasBottleRowID;
	private Integer analyzerRowID;
	private Integer userRowID;
	private Integer surveyRowID;
	private Integer customerRowID;
	private Integer db3AnalyzerRowID;

	public CustomerSurveyInfoEntity() {
	}

	public CustomerSurveyInfoEntity(Integer customerRowID, Integer locationRowID, Integer userRowID, Integer analyzerRowID, Integer surveyorRowID, Integer refGasBottleRowID,
			Integer db3AnalyzerRowID, Integer surveyRuntimeInSeconds, Integer surveyRowID) {
		this.surveyRuntimeInSeconds = surveyRuntimeInSeconds;
		this.locationRowID = locationRowID;
		this.surveyorRowID = surveyorRowID;
		this.refGasBottleRowID = refGasBottleRowID;
		this.analyzerRowID = analyzerRowID;
		this.userRowID = userRowID;
		this.surveyRowID = surveyRowID;
		this.customerRowID = customerRowID;
		this.db3AnalyzerRowID = db3AnalyzerRowID;
	}

	public Integer getSurveyRuntimeInSeconds() {
		return surveyRuntimeInSeconds;
	}

	public void setSurveyRuntimeInSeconds(Integer surveyRuntimeInSeconds) {
		this.surveyRuntimeInSeconds = surveyRuntimeInSeconds;
	}

	public Integer getLocationRowID() {
		return locationRowID;
	}

	public void setLocationRowID(Integer locationRowID) {
		this.locationRowID = locationRowID;
	}

	public Integer getSurveyorRowID() {
		return surveyorRowID;
	}

	public void setSurveyorRowID(Integer surveyorRowID) {
		this.surveyorRowID = surveyorRowID;
	}

	public Integer getRefGasBottleRowID() {
		return refGasBottleRowID;
	}

	public void setRefGasBottleRowID(Integer refGasBottleRowID) {
		this.refGasBottleRowID = refGasBottleRowID;
	}

	public Integer getAnalyzerRowID() {
		return analyzerRowID;
	}

	public void setAnalyzerRowID(Integer analyzerRowID) {
		this.analyzerRowID = analyzerRowID;
	}

	public Integer getUserRowID() {
		return userRowID;
	}

	public void setUserRowID(Integer userRowID) {
		this.userRowID = userRowID;
	}

	public Integer getSurveyRowID() {
		return surveyRowID;
	}

	public void setSurveyRowID(Integer surveyRowID) {
		this.surveyRowID = surveyRowID;
	}

	public Integer getCustomerRowID() {
		return customerRowID;
	}

	public void setCustomerRowID(Integer customerRowID) {
		this.customerRowID = customerRowID;
	}

	public Integer getDb3AnalyzerRowID() {
		return db3AnalyzerRowID;
	}

	public void setDb3AnalyzerRowID(Integer db3AnalyzerRowID) {
		this.db3AnalyzerRowID = db3AnalyzerRowID;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}