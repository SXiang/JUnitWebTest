/**
 * 
 */
package surveyor.scommon.source;

import java.util.List;
import java.util.Map;

import surveyor.scommon.source.Reports.ReportModeFilter;

/**
 * @author zlu
 *
 */
public class Reports {
	protected String rptTitle;
	protected String strCreatedBy;
	private String customer;
	protected String timeZone;
	protected String exclusionRadius;
	
	protected List<String> listBoundary;
	protected String imageMapHeight;
	protected String imageMapWidth;
	protected String NELat;
	protected String NELong;
	protected String SWLat;
	protected String SWLong;
	
	protected String surveyorUnit;
	protected String surveyUsername;
	protected String tag;	
	protected List<String> tagList;
	protected String surveyMode;
	protected Boolean surveyGeoFilterOn=false;
	protected String startDate;
	protected String endDate;
	protected String userName;
	protected boolean geoFilterOn;
	
	protected List<Map<String, String>> viewList;
	protected List<Map<String, String>> tablesList;
	protected List<Map<String, String>> viewLayersList;
	
	protected SurveyModeFilter surveyModeFilter;	
	protected ReportModeFilter reportModeFilter;
	
	public enum SurveyModeFilter {
		All,
		Standard,
		Operator,
		RapidResponse,
		Manual
	}
	
	public enum ReportModeFilter {
		Standard,
		RapidResponse,
		Manual
	}
	
	/**
	 * 
	 */
	public Reports(String rptTitle, String strCreatedBy, String customer,
			String timeZone, String exclusionRadius, List<String> listBoundary,
			List<Map<String, String>> tablesList, String surveyorUnit,
			String tag, List<Map<String, String>> viewList) {
		this(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, listBoundary,
				tablesList, surveyorUnit, tag, viewList, null);
	}

	public Reports(String rptTitle, String strCreatedBy, String customer,
			String timeZone, String exclusionRadius, List<String> listBoundary,
			List<Map<String, String>> tablesList, String surveyorUnit,
			String tag, List<Map<String, String>> viewList, List<Map<String, String>> viewLayersList) {
		this.rptTitle = rptTitle;
		this.strCreatedBy = strCreatedBy;
		this.setCustomer(customer);
		this.timeZone = timeZone;
		this.exclusionRadius = exclusionRadius;

		this.listBoundary = listBoundary;
		this.imageMapHeight = listBoundary.get(0);
		this.imageMapWidth = listBoundary.get(1);
		this.NELat = listBoundary.get(2);
		this.NELong = listBoundary.get(3);
		this.SWLat = listBoundary.get(4);
		this.SWLong = listBoundary.get(5);
		
		this.tablesList = tablesList;
		this.surveyorUnit = surveyorUnit;
		this.tag = tag;

		this.viewList = viewList;
		this.viewLayersList = viewLayersList;
	}

	public Reports(String rptTitle, String strCreatedBy, String customer,
			String timeZone, String exclusionRadius, List<String> listBoundary,
			List<Map<String, String>> tablesList, String surveyorUnit, String tag,String startDate, String endDate,
			 List<Map<String, String>> viewList, SurveyModeFilter surveyMode) {
		this.rptTitle = rptTitle;
		this.strCreatedBy = strCreatedBy;
		this.setCustomer(customer);
		this.timeZone = timeZone;
		this.exclusionRadius = exclusionRadius;

		this.listBoundary = listBoundary;
		this.imageMapHeight = listBoundary.get(0);
		this.imageMapWidth = listBoundary.get(1);
		this.NELat = listBoundary.get(2);
		this.NELong = listBoundary.get(3);
		this.SWLat = listBoundary.get(4);
		this.SWLong = listBoundary.get(5);
		
		this.tablesList = tablesList;
		this.surveyorUnit = surveyorUnit;
		this.tag = tag;
		this.startDate=startDate;
		this.endDate=endDate;
		
		this.viewList = viewList;
		this.surveyModeFilter=surveyMode;
	}
	
	
	public Reports(String rptTitle, String strCreatedBy, String customer,
			String timeZone, String exclusionRadius, List<String> listBoundary,
			List<Map<String, String>> tablesList, String surveyorUnit, String tag,String startDate, String endDate,
			 List<Map<String, String>> viewList, SurveyModeFilter surveyMode, ReportModeFilter reportMode) {
		this.rptTitle = rptTitle;
		this.strCreatedBy = strCreatedBy;
		this.setCustomer(customer);
		this.timeZone = timeZone;
		this.exclusionRadius = exclusionRadius;

		this.listBoundary = listBoundary;
		this.imageMapHeight = listBoundary.get(0);
		this.imageMapWidth = listBoundary.get(1);
		this.NELat = listBoundary.get(2);
		this.NELong = listBoundary.get(3);
		this.SWLat = listBoundary.get(4);
		this.SWLong = listBoundary.get(5);
		
		this.tablesList = tablesList;
		this.surveyorUnit = surveyorUnit;
		this.tag = tag;
		this.startDate=startDate;
		this.endDate=endDate;
		
		this.viewList = viewList;
		this.surveyModeFilter=surveyMode;
		this.reportModeFilter=reportMode;
	}
	
	public Reports(String rptTitle, String strCreatedBy, String customer,
			String timeZone, String exclusionRadius, List<String> listBoundary,
			List<Map<String, String>> tablesList, String surveyorUnit, String tag,String startDate, String endDate,
			 List<Map<String, String>> viewList, SurveyModeFilter surveyMode, boolean geoFilter) {
		this.rptTitle = rptTitle;
		this.strCreatedBy = strCreatedBy;
		this.setCustomer(customer);
		this.timeZone = timeZone;
		this.exclusionRadius = exclusionRadius;

		this.listBoundary = listBoundary;
		this.imageMapHeight = listBoundary.get(0);
		this.imageMapWidth = listBoundary.get(1);
		this.NELat = listBoundary.get(2);
		this.NELong = listBoundary.get(3);
		this.SWLat = listBoundary.get(4);
		this.SWLong = listBoundary.get(5);
		
		this.tablesList = tablesList;
		this.surveyorUnit = surveyorUnit;
		this.tag = tag;
		this.startDate=startDate;
		this.endDate=endDate;
		
		this.viewList = viewList;
		this.surveyModeFilter=surveyMode;
		this.geoFilterOn=geoFilter;
	}
	
	public Reports(String rptTitle, String strCreatedBy, String customer,
			String timeZone, String exclusionRadius, List<String> listBoundary,
			List<Map<String, String>> tablesList, String surveyorUnit, String tag,String startDate, String endDate,
			 List<Map<String, String>> viewList, SurveyModeFilter surveyMode, String userName,Boolean geoFilterOn, ReportModeFilter reportMode) {
		this.rptTitle = rptTitle;
		this.strCreatedBy = strCreatedBy;
		this.setCustomer(customer);
		this.timeZone = timeZone;
		this.exclusionRadius = exclusionRadius;

		this.listBoundary = listBoundary;
		this.imageMapHeight = listBoundary.get(0);
		this.imageMapWidth = listBoundary.get(1);
		this.NELat = listBoundary.get(2);
		this.NELong = listBoundary.get(3);
		this.SWLat = listBoundary.get(4);
		this.SWLong = listBoundary.get(5);
		
		this.tablesList = tablesList;
		this.surveyorUnit = surveyorUnit;
		this.tag = tag;
		this.startDate=startDate;
		this.endDate=endDate;
		
		this.viewList = viewList;
		this.surveyModeFilter=surveyMode;
		this.reportModeFilter=reportMode;
	}
	
	public Reports(String rptTitle, String strCreatedBy, String customer,
			String timeZone, String exclusionRadius, List<String> listBoundary,
			List<Map<String, String>> tablesList, String surveyorUnit,
			List<String> tagList, List<Map<String, String>> viewList) {
		this.rptTitle = rptTitle;
		this.strCreatedBy = strCreatedBy;
		this.setCustomer(customer);
		this.timeZone = timeZone;
		this.exclusionRadius = exclusionRadius;

		this.listBoundary = listBoundary;
		this.imageMapHeight = listBoundary.get(0);
		this.imageMapWidth = listBoundary.get(1);
		this.NELat = listBoundary.get(2);
		this.NELong = listBoundary.get(3);
		this.SWLat = listBoundary.get(4);
		this.SWLong = listBoundary.get(5);
		
		this.tablesList = tablesList;
		this.surveyorUnit = surveyorUnit;
		this.tagList = tagList;

		this.viewList = viewList;
	}
	
	public Reports(String rptTitle, String strCreatedBy, String customer,
			String timeZone, String exclusionRadius, List<String> listBoundary,
			List<Map<String, String>> tablesList, String surveyorUnit,
			List<String> tagList, List<Map<String, String>> viewList, ReportModeFilter reportMode) {
		this.rptTitle = rptTitle;
		this.strCreatedBy = strCreatedBy;
		this.setCustomer(customer);
		this.timeZone = timeZone;
		this.exclusionRadius = exclusionRadius;

		this.listBoundary = listBoundary;
		this.imageMapHeight = listBoundary.get(0);
		this.imageMapWidth = listBoundary.get(1);
		this.NELat = listBoundary.get(2);
		this.NELong = listBoundary.get(3);
		this.SWLat = listBoundary.get(4);
		this.SWLong = listBoundary.get(5);
		
		this.tablesList = tablesList;
		this.surveyorUnit = surveyorUnit;
		this.tagList = tagList;

		this.viewList = viewList;
		this.reportModeFilter=reportMode;
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
	
	public String getExclusionRadius() {
		return this.exclusionRadius;
	}
	
	public List<String> getListBoundary() {
		return this.listBoundary;
	}

	public String getImageMapHeight() {
		return this.imageMapHeight;
	}
	
	public String getImageMapWidth() {
		return this.imageMapWidth;
	}
	
	public String getNELat() {
		return this.NELat;
	}
	
	public String getNELong() {
		return this.NELong;
	}
	
	public String getSWLat() {
		return this.SWLat;
	}

	public String getSWLong() {
		return this.SWLong;
	}
	
	public String getSurveyorUnit() {
		return this.surveyorUnit;
	}
	
	public String getTag() {
		return this.tag;
	}
	
	public String getSurveyStartDate() {
		return this.startDate;
	}
	
	public String getSurveyEndDate() {
		return this.endDate;
	}
	
	public SurveyModeFilter getSurveyModeFilter() {
		return this.surveyModeFilter;
	}
	
	public boolean getGeoFilter() {
		return this.surveyGeoFilterOn;
	}
	
	public String getUserName() {
		return this.surveyUsername;
	}
	
	public List<Map<String, String>> getViewList() {
		return this.viewList;
	}
	
	
	public List<Map<String, String>> getTablesList() {
		return this.tablesList;
	}
	
	public List<String> getTagList() {
		return this.tagList;
	}
	
	
	public List<Map<String, String>> getViewLayersList() {
		return this.viewLayersList;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

}
