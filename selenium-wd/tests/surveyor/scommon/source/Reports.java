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
	protected List<String> tagList;
	protected String startDate;
	protected String endDate;
	protected String userName;
	protected String fovOpactiy;
	protected String lisaOpacity;
	protected String customerBoundary;

	protected List<Map<String, String>> viewList;
	protected List<Map<String, String>> tablesList;
	protected List<Map<String, String>> viewLayersList;

	protected Boolean geoFilter=false;
	protected SurveyModeFilter surveyModeFilter;
	protected ReportModeFilter reportModeFilter;
	protected EthaneFilter ethaneFilter;

	public enum SurveyModeFilter {
		All ("All"), 
		Standard ("All"), 
		Operator ("All"), 
		RapidResponse ("All"), 
		Manual ("All"), 
		EQ ("All"), 
		Assessment ("All");
		
		private final String name;

		SurveyModeFilter(String nm) {
			name = nm;
		}
		
		public String toString() {
			return this.name;
		}
	}

	public enum ReportModeFilter {
		All ("All"), 
		Standard ("All"), 
		Operator ("All"), 
		RapidResponse ("All"), 
		Manual ("All"), 
		EQ ("All"), 
		Assessment ("All");
		
		private final String name;

		ReportModeFilter(String nm) {
			name = nm;
		}
		
		public String toString() {
			return this.name;
		}
	}

	public enum EthaneFilter {
		ExcludeVehicleExhaust, ExcludeBiogenicMethane, Both, None
	}

	
	public Reports(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList, List<Map<String, String>> viewList) {
		this(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, surveyorUnit, null, null, null, null, null, null, null, null, null, listBoundary, tagList, tablesList, viewList, null);
	}

	public Reports(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList, List<Map<String, String>> viewList, List<Map<String, String>> viewLayersList) {
		this(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, surveyorUnit, null, null, null, null, null, null, null, null, null, listBoundary, tagList, tablesList, viewList, viewLayersList);
	}

	public Reports(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList, String startDate, String endDate, List<Map<String, String>> viewList, SurveyModeFilter surveyMode) {
		this(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, surveyorUnit, null, startDate, endDate, null, null, null, null, surveyMode, null, listBoundary, tagList, tablesList, viewList, null);
	}

	public Reports(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList, String startDate, String endDate, List<Map<String, String>> viewList, SurveyModeFilter surveyMode, ReportModeFilter reportMode) {
		this(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, surveyorUnit, null, startDate, endDate, null, null, null, reportMode, surveyMode, null, listBoundary, tagList, tablesList, viewList, null);
	}

	public Reports(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList, String startDate, String endDate, List<Map<String, String>> viewList, SurveyModeFilter surveyMode, boolean geoFilter) {
		this(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, surveyorUnit, null, startDate, endDate, null, null, geoFilter, null, surveyMode, null, listBoundary, tagList, tablesList, viewList, null);
	}

	public Reports(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList, String startDate, String endDate, List<Map<String, String>> viewList, SurveyModeFilter surveyMode, String userName, boolean geoFilterOn, ReportModeFilter reportMode) {
		this(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, surveyorUnit, userName, startDate, endDate, null, null, geoFilterOn, reportMode, surveyMode, null, listBoundary, tagList, tablesList, viewList, null);
	}

	public Reports(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList, List<Map<String, String>> viewList, ReportModeFilter reportMode) {
		this(rptTitle, strCreatedBy, customer, timeZone, exclusionRadius, surveyorUnit, null, null, null, null, null, null, reportMode, null, null, listBoundary, tagList, tablesList, viewList, null);
	}

	public Reports(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, 
			String surveyorUnit, String userName, String startDate, String endDate, String fovOpacity, String lisaOpacity, 
			Boolean geoFilter, ReportModeFilter reportMode, SurveyModeFilter surveyModeFilter, EthaneFilter ethaneFilter, 
			List<String> listBoundary, List<String> tagList, List<Map<String, String>> tablesList, List<Map<String, String>> viewList,
			List<Map<String, String>> viewLayersList) {
		this.rptTitle = rptTitle;
		this.strCreatedBy = strCreatedBy;
		this.setCustomer(customer);
		this.timeZone = timeZone;
		this.exclusionRadius = exclusionRadius;

		this.reportModeFilter = reportMode;
		this.ethaneFilter = ethaneFilter;
		this.surveyModeFilter = surveyModeFilter;
		this.geoFilter = geoFilter;
		
		this.listBoundary = listBoundary;
		this.imageMapHeight = listBoundary.get(0);
		this.imageMapWidth = listBoundary.get(1);
		this.NELat = listBoundary.get(2);
		this.NELong = listBoundary.get(3);
		this.SWLat = listBoundary.get(4);
		this.SWLong = listBoundary.get(5);

		this.surveyorUnit = surveyorUnit;
		this.userName = userName;
		this.tagList = tagList;

		this.startDate = startDate;
		this.endDate = endDate;
		this.surveyModeFilter = surveyModeFilter;

		this.viewList = viewList;
		this.tablesList = tablesList;
		this.viewLayersList = viewLayersList;

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

	public String getSurveyStartDate() {
		return this.startDate;
	}

	public String getSurveyEndDate() {
		return this.endDate;
	}

	public SurveyModeFilter getSurveyModeFilter() {
		return this.surveyModeFilter;
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

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public EthaneFilter getEthaneFilter() {
		return this.ethaneFilter;
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

	public ReportModeFilter getReportModeFilter() {
		return this.reportModeFilter;
	}

	public String getFovOpactiy() {
		return fovOpactiy;
	}

	public String getLisaOpacity() {
		return lisaOpacity;
	}

	public String getCustomerBoundary() {
		return customerBoundary;
	}

	public Boolean getGeoFilter() {
		return geoFilter;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
