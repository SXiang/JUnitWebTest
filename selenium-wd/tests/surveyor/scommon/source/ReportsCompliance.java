/**
 * 
 */
package surveyor.scommon.source;

import static surveyor.scommon.source.SurveyorConstants.IMGMAPHEIGHT;
import static surveyor.scommon.source.SurveyorConstants.IMGMAPWIDTH;
import static surveyor.scommon.source.SurveyorConstants.RNELAT;
import static surveyor.scommon.source.SurveyorConstants.RNELON;
import static surveyor.scommon.source.SurveyorConstants.RSWLAT;
import static surveyor.scommon.source.SurveyorConstants.RSWLON;
import static surveyor.scommon.source.SurveyorConstants.SQACUSSU;
import static surveyor.scommon.source.SurveyorConstants.TIMEZONEPTUA;

import java.util.List;
import java.util.Map;

import surveyor.scommon.source.Reports.SurveyModeFilter;

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
	
	public enum EthaneFilter {
		ExcludeVehicleExhaust, ExcludeBiogenicMethane, Both, None
	}

	public ReportsCompliance(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, 
			List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList, List<Map<String, String>> viewList) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit,tagList);
		this.exclusionRadius = exclusionRadius;
		this.imageMapHeight=listBoundary.get(0);
		this.imageMapWidth=listBoundary.get(1);
		this.NELat = listBoundary.get(2);
		this.NELong =listBoundary.get(3);
		this.SWLat = listBoundary.get(4);
		this.SWLong = listBoundary.get(5);
		this.listBoundary=listBoundary;
		this.tablesList = tablesList;
		this.viewList=viewList;
	}

	public ReportsCompliance(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, 
			List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList, List<Map<String, String>> viewList, List<Map<String, String>> viewLayersList) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, tagList);
		this.exclusionRadius=exclusionRadius;
		this.imageMapHeight=listBoundary.get(0);
		this.imageMapWidth=listBoundary.get(1);
		this.NELat = listBoundary.get(2);
		this.NELong =listBoundary.get(3);
		this.SWLat = listBoundary.get(4);
		this.SWLong = listBoundary.get(5);
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
		this.imageMapHeight=listBoundary.get(0);
		this.imageMapWidth=listBoundary.get(1);
		this.NELat = listBoundary.get(2);
		this.NELong =listBoundary.get(3);
		this.SWLat = listBoundary.get(4);
		this.SWLong = listBoundary.get(5);
		this.listBoundary = listBoundary;
		this.tablesList = tablesList;
		this.viewList= viewList;
		this.surveyModeFilter=surveyMode;
	}

	public ReportsCompliance(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, List<Map<String, String>> tablesList, 
			String surveyorUnit, List<String> tagList, String startDate, String endDate, List<Map<String, String>> viewList, SurveyModeFilter surveyMode, ReportModeFilter reportMode) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, tagList, startDate, endDate);
		this.exclusionRadius=exclusionRadius;
		this.imageMapHeight=listBoundary.get(0);
		this.imageMapWidth=listBoundary.get(1);
		this.NELat = listBoundary.get(2);
		this.NELong =listBoundary.get(3);
		this.SWLat = listBoundary.get(4);
		this.SWLong = listBoundary.get(5);
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
		this.imageMapHeight=listBoundary.get(0);
		this.imageMapWidth=listBoundary.get(1);
		this.NELat = listBoundary.get(2);
		this.NELong =listBoundary.get(3);
		this.SWLat = listBoundary.get(4);
		this.SWLong = listBoundary.get(5);
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
		this.imageMapHeight=listBoundary.get(0);
		this.imageMapWidth=listBoundary.get(1);
		this.NELat = listBoundary.get(2);
		this.NELong =listBoundary.get(3);
		this.SWLat = listBoundary.get(4);
		this.SWLong = listBoundary.get(5);
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
		this.imageMapHeight=listBoundary.get(0);
		this.imageMapWidth=listBoundary.get(1);
		this.NELat = listBoundary.get(2);
		this.NELong =listBoundary.get(3);
		this.SWLat = listBoundary.get(4);
		this.SWLong = listBoundary.get(5);
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
		this.imageMapHeight=listBoundary.get(0);
		this.imageMapWidth=listBoundary.get(1);
		this.NELat = listBoundary.get(2);
		this.NELong =listBoundary.get(3);
		this.SWLat = listBoundary.get(4);
		this.SWLong = listBoundary.get(5);
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
	
	public ReportsCompliance(){
		super();
	}

	public String getExclusionRadius() {
		return this.exclusionRadius;
	}

	public void setExclusionRadius(String exclusionRadius) {
		this.exclusionRadius = exclusionRadius;
	}

	public List<String> getListBoundary() {
		return this.listBoundary;
	}

	public void setListBoundary(List<String> listBoundary) {
		this.listBoundary = listBoundary;
	}

	public String getImageMapHeight() {
		return this.imageMapHeight;
	}

	public void setImageMapHeight(String imageMapHeight) {
		this.imageMapHeight = imageMapHeight;
	}

	public String getImageMapWidth() {
		return this.imageMapWidth;
	}

	public void setImageMapWidth(String imageMapWidth) {
		this.imageMapWidth = imageMapWidth;
	}

	public String getNELat() {
		return this.NELat;
	}

	public void setNELat(String nELat) {
		this.NELat = nELat;
	}

	public String getNELong() {
		return this.NELong;
	}

	public void setNELong(String nELong) {
		this.NELong = nELong;
	}

	public String getSWLat() {
		return this.SWLat;
	}

	public void setSWLat(String sWLat) {
		this.SWLat = sWLat;
	}

	public String getSWLong() {
		return this.SWLong;
	}

	public void setSWLong(String sWLong) {
		this.SWLong = sWLong;
	}

	public String getFovOpactiy() {
		return this.fovOpacity;
	}

	public void setFovOpactiy(String fovOpactiy) {
		this.fovOpacity = fovOpactiy;
	}

	public String getLisaOpacity() {
		return this.lisaOpacity;
	}

	public void setLisaOpacity(String lisaOpacity) {
		this.lisaOpacity = lisaOpacity;
	}

	public String getCustomerBoundary() {
		return this.customerBoundary;
	}

	public void setCustomerBoundary(String customerBoundary) {
		this.customerBoundary = customerBoundary;
	}

	public List<Map<String, String>> getViewList() {
		return this.viewList;
	}

	public void setViewList(List<Map<String, String>> viewList) {
		this.viewList = viewList;
	}

	public List<Map<String, String>> getTablesList() {
		return this.tablesList;
	}

	public void setTablesList(List<Map<String, String>> tablesList) {
		this.tablesList = tablesList;
	}

	public List<Map<String, String>> getViewLayersList() {
		return this.viewLayersList;
	}

	public void setViewLayersList(List<Map<String, String>> viewLayersList) {
		this.viewLayersList = viewLayersList;
	}

	public SurveyModeFilter getSurveyModeFilter() {
		return this.surveyModeFilter;
	}

	public void setSurveyModeFilter(SurveyModeFilter surveyModeFilter) {
		this.surveyModeFilter = surveyModeFilter;
	}

	public ReportModeFilter getReportModeFilter() {
		return this.reportModeFilter;
	}

	public void setReportModeFilter(ReportModeFilter reportModeFilter) {
		this.reportModeFilter = reportModeFilter;
	}

	public EthaneFilter getEthaneFilter() {
		return this.ethaneFilter;
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
