/**
 * 
 */
package surveyor.scommon.source;

import java.util.List;
import java.util.Map;


/**
 * @author zlu
 *
 */
public class Reports {
	protected String rptTitle;
	protected String strCreatedBy;
	private String customer;
	protected String timeZone;

	

	protected String surveyorUnit;
	protected List<String> tagList;
	protected String startDate;
	protected String endDate;
	protected String userName;
	
	protected Boolean geoFilter = false;
	

	protected List<Coordinates> eqCoordinates;

	

	public Reports(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit, List<String> tagList) {
		this(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, null, null, null, null, tagList);
	}
	
	public Reports(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit, List<String> tagList, String startDate, String endDate) {
		this(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, null, startDate, endDate, null, tagList);
	}

	public Reports(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit, String userName, String startDate, String endDate, boolean geoFilter) {
		this(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, userName, startDate, endDate, geoFilter, null);
	}
	
	public Reports(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit, String startDate, String endDate, boolean geoFilter) {
		this(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, null, startDate, endDate, geoFilter, null);
	}
	
	public Reports(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit, List<String> tagList, String startDate, String endDate, boolean geoFilter) {
		this(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, null, startDate, endDate, geoFilter, tagList);
	}
	
	public Reports(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit,String userName,  String startDate, String endDate,  List<String> tagList) {
		this(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, userName, startDate, endDate, null, tagList);
	}
	
	public Reports(String rptTitle, String strCreatedBy, String customer, String timeZone, String surveyorUnit, String userName, String startDate, String endDate, Boolean geoFilter, List<String> tagList){
		this.rptTitle = rptTitle;
		this.strCreatedBy = strCreatedBy;
		this.setCustomer(customer);
		this.timeZone = timeZone;
		this.geoFilter = geoFilter;

		this.surveyorUnit = surveyorUnit;
		this.userName = userName;
		this.tagList = tagList;

		this.startDate = startDate;
		this.endDate = endDate;
	
	}

	public String getRptTitle() {
		return this.rptTitle;
	}

	public String getCustomer() {
		return this.customer;
	}

	public String getTimeZone() {
		return this.timeZone;
	}

	

	public String getSurveyorUnit() {
		return this.surveyorUnit;
	}

	public String getSurveyStartDate() {
		return this.startDate;
	}

	public String getSurveyEndDate() {
		return this.endDate;
	}

	public List<String> getTagList() {
		return this.tagList;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getStrCreatedBy() {
		return this.strCreatedBy;
	}

	public String getUsername() {
		return this.userName;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public Boolean getGeoFilter() {
		return geoFilter;
	}

	public List<Coordinates> getEqCoordinates() {
		return eqCoordinates;
	}

	public void setEqCoordinates(List<Coordinates> eqCoordinates) {
		this.eqCoordinates = eqCoordinates;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
