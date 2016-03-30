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
public class ReportsCompliance extends Reports {

	protected String exclusionRadius;
	protected List<String> listBoundary;
	protected String imageMapHeight;
	protected String imageMapWidth;
	protected String NELat;
	protected String NELong;
	protected String SWLat;
	protected String SWLong;
	protected String fovOpacity;
	protected String lisaOpacity;
	protected String customerBoundary;

	protected List<Map<String, String>> viewList;
	protected List<Map<String, String>> tablesList;
	protected List<Map<String, String>> viewLayersList;

	
	protected SurveyModeFilter surveyModeFilter;
	protected ReportModeFilter reportModeFilter;
	protected EthaneFilter ethaneFilter;
	
	public enum SurveyModeFilter {
		All, Standard, Operator, RapidResponse, Manual
	}

	public enum ReportModeFilter {
		Standard, RapidResponse, Manual
	}

	public enum EthaneFilter {
		ExcludeVehicleExhaust, ExcludeBiogenicMethane, Both, None
	}


	public ReportsCompliance(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, 
			List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList, List<Map<String, String>> viewList) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit,tagList);
		this.exclusionRadius = exclusionRadius;
		this.listBoundary = listBoundary;
		this.tablesList = tablesList;
		this.viewLayersList=viewList;
	}

	public ReportsCompliance(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, 
			List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList, List<Map<String, String>> viewList, List<Map<String, String>> viewLayersList) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, tagList);
		this.exclusionRadius=exclusionRadius;
		this.listBoundary=listBoundary;
		this.tablesList=tablesList;
		this.tagList=tagList;
		this.viewList=viewList;
		this.viewLayersList=viewLayersList;		
	}
	
	public ReportsCompliance(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, 
			List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList, String startDate, String endDate, List<Map<String, String>> viewList, SurveyModeFilter surveyMode) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, tagList, startDate, endDate);
		this.exclusionRadius=exclusionRadius;
		this.listBoundary = listBoundary;
		this.tablesList = tablesList;
		this.viewList= viewList;
		this.surveyModeFilter=surveyMode;
	}

	public ReportsCompliance(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, List<Map<String, String>> tablesList, 
			String surveyorUnit, List<String> tagList, String startDate, String endDate, List<Map<String, String>> viewList, SurveyModeFilter surveyMode, ReportModeFilter reportMode) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, tagList, startDate, endDate);
		this.exclusionRadius=exclusionRadius;
		this.listBoundary=listBoundary;
		this.tablesList=tablesList;
		this.viewList=viewList;
		this.surveyModeFilter=surveyMode;
		this.reportModeFilter=reportMode;
	}

	public ReportsCompliance(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, 
			List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList, String startDate, String endDate, 
			List<Map<String, String>> viewList, SurveyModeFilter surveyMode, Boolean geoFilter) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, tagList, startDate, endDate, geoFilter);
		this.exclusionRadius=exclusionRadius;
		this.listBoundary=listBoundary;
		this.tablesList=tablesList;
		this.viewList=viewList;
		this.surveyModeFilter=surveyMode;
	}

	public ReportsCompliance(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary,
			List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList, String startDate, String endDate, List<Map<String, String>> viewList, 
			SurveyModeFilter surveyMode, String userName, Boolean geoFilterOn, ReportModeFilter reportMode) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit,  startDate,endDate, userName, geoFilterOn,tagList);
		this.exclusionRadius=exclusionRadius;
		this.listBoundary=listBoundary;
		this.tablesList=tablesList;
		this.viewList=viewList;
		this.surveyModeFilter=surveyMode;
		this.reportModeFilter=reportMode;
	}

	public ReportsCompliance(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, 
			List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList, List<Map<String, String>> viewList, ReportModeFilter reportMode) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, tagList);
		this.exclusionRadius=exclusionRadius;
		this.listBoundary=listBoundary;
		this.tablesList=tablesList;
		this.viewList=viewList;
		this.reportModeFilter=reportMode;
	}

	public ReportsCompliance(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, String surveyorUnit, String userName, 
			String startDate, String endDate, String fovOpacity, String lisaOpacity, Boolean geoFilter, ReportModeFilter reportMode, SurveyModeFilter surveyModeFilter, 
			EthaneFilter ethaneFilter, List<String> listBoundary, List<String> tagList, List<Map<String, String>> tablesList, List<Map<String, String>> viewList,
			List<Map<String, String>> viewLayersList) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, userName, startDate, endDate,geoFilter, tagList);
		this.exclusionRadius=exclusionRadius;
		this.fovOpacity=fovOpacity;
		this.lisaOpacity=lisaOpacity;
		this.reportModeFilter=reportMode;
		this.surveyModeFilter=surveyModeFilter;
		this.ethaneFilter=ethaneFilter;
		this.listBoundary=listBoundary;
		this.tablesList=tablesList;
		this.viewList=viewList;
		this.viewLayersList=viewLayersList;
	}

	public String getExclusionRadius() {
		return exclusionRadius;
	}

	public void setExclusionRadius(String exclusionRadius) {
		this.exclusionRadius = exclusionRadius;
	}

	public List<String> getListBoundary() {
		return listBoundary;
	}

	public void setListBoundary(List<String> listBoundary) {
		this.listBoundary = listBoundary;
	}

	public String getImageMapHeight() {
		return imageMapHeight;
	}

	public void setImageMapHeight(String imageMapHeight) {
		this.imageMapHeight = imageMapHeight;
	}

	public String getImageMapWidth() {
		return imageMapWidth;
	}

	public void setImageMapWidth(String imageMapWidth) {
		this.imageMapWidth = imageMapWidth;
	}

	public String getNELat() {
		return NELat;
	}

	public void setNELat(String nELat) {
		NELat = nELat;
	}

	public String getNELong() {
		return NELong;
	}

	public void setNELong(String nELong) {
		NELong = nELong;
	}

	public String getSWLat() {
		return SWLat;
	}

	public void setSWLat(String sWLat) {
		SWLat = sWLat;
	}

	public String getSWLong() {
		return SWLong;
	}

	public void setSWLong(String sWLong) {
		SWLong = sWLong;
	}

	public String getFovOpactiy() {
		return fovOpacity;
	}

	public void setFovOpactiy(String fovOpactiy) {
		this.fovOpacity = fovOpactiy;
	}

	public String getLisaOpacity() {
		return lisaOpacity;
	}

	public void setLisaOpacity(String lisaOpacity) {
		this.lisaOpacity = lisaOpacity;
	}

	public String getCustomerBoundary() {
		return customerBoundary;
	}

	public void setCustomerBoundary(String customerBoundary) {
		this.customerBoundary = customerBoundary;
	}

	public List<Map<String, String>> getViewList() {
		return viewList;
	}

	public void setViewList(List<Map<String, String>> viewList) {
		this.viewList = viewList;
	}

	public List<Map<String, String>> getTablesList() {
		return tablesList;
	}

	public void setTablesList(List<Map<String, String>> tablesList) {
		this.tablesList = tablesList;
	}

	public List<Map<String, String>> getViewLayersList() {
		return viewLayersList;
	}

	public void setViewLayersList(List<Map<String, String>> viewLayersList) {
		this.viewLayersList = viewLayersList;
	}

	public SurveyModeFilter getSurveyModeFilter() {
		return surveyModeFilter;
	}

	public void setSurveyModeFilter(SurveyModeFilter surveyModeFilter) {
		this.surveyModeFilter = surveyModeFilter;
	}

	public ReportModeFilter getReportModeFilter() {
		return reportModeFilter;
	}

	public void setReportModeFilter(ReportModeFilter reportModeFilter) {
		this.reportModeFilter = reportModeFilter;
	}

	public EthaneFilter getEthaneFilter() {
		return ethaneFilter;
	}

	public void setEthaneFilter(EthaneFilter ethaneFilter) {
		this.ethaneFilter = ethaneFilter;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
