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

	public static final String ASSET_PREFIX = "Asset_";
	public static final String BOUNDARY_PREFIX = "Boundary_";
	public static final String CANVAS_X_PATH = "//*[@id=\"map\"]/div/canvas";

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

	protected List<Map<String, String>> viewList;
	protected List<Map<String, String>> tablesList;
	protected List<Map<String, String>> viewLayersList;
	
	protected SurveyModeFilter surveyModeFilter;
	protected ReportModeFilter reportModeFilter;
	protected EthaneFilter ethaneFilter;
	
	private int latLongXOffset;
	private int latLongYOffset;
	private int latLongRectHeight;
	private int latLongRectWidth;
	
	private String customerBoundaryName; 
	private CustomerBoundaryFilterType customerBoundaryFilterType;
	
	public enum EthaneFilter {
		ExcludeVehicleExhaust, ExcludeBiogenicMethane, Both, None
	}
	
	public enum CustomerBoundaryFilterType {
		District ("District"),
		DistrictPlat ("District Plat"),
		BigBoundary ("Big Boundary"), 
		SmallBoundary ("Small Boundary"),
		LeakSurveyArea ("Leak Survey Area");
		
		private final String name;

		CustomerBoundaryFilterType(String nm) {
			name = nm;
		}
		
		public String toString() {
			return this.name;
		}
	}

	public enum DrivingSurveyTableColumns {
		StartDateTime ("StartDateTime", 1),
		EndDateTime ("EndDateTime", 2),
		Duration ("Duration", 3),
		username ("username", 4),
		Surveyor ("Surveyor", 5),
		Analyzer ("Analyzer", 6),
		Tag ("Tag", 7),
		FOV ("FOV", 8),
		LISA ("LISA", 9),
		Analysis ("Analysis", 10),
		StabilityClass ("StabilityClass", 11);
		
		private final String name;
		private final Integer colIndex;

		DrivingSurveyTableColumns(String nm) {
			this(nm, -1);
		}

		DrivingSurveyTableColumns(String nm, Integer colIdx) {
			name = nm;
			colIndex = colIdx;
		}

		public Integer getIndex() {
			return colIndex;
		}

		
		public String toString() {
			return this.name;
		}
	}

	public enum IsotopicAnalysisTableColumns {
		Surveyor ("Surveyor", 1),
		DateTime ("DateTime", 2),
		Result ("Result", 3),
		IsotopicValueUncertainty ("IsotopicValueUncertainty", 4),
		FieldNotes ("FieldNotes", 5);
		
		private final String name;
		private final Integer colIndex;

		IsotopicAnalysisTableColumns(String nm) {
			this(nm, -1);
		}

		IsotopicAnalysisTableColumns(String nm, Integer colIdx) {
			name = nm;
			colIndex = colIdx;
		}

		public Integer getIndex() {
			return colIndex;
		}

		
		public String toString() {
			return this.name;
		}
	}

	public enum LISAIndicationTableColumns {
		LISANum ("LISANum", 1),
		Surveyor ("Surveyor", 2),
		DateTime ("DateTime", 3),
		Amplitude ("Amplitude", 4),
		Concentration ("Concentration", 5),
		EthaneMethanRatio ("EthaneMethanRatio", 6),
		Disposition ("Disposition", 7),
		PercConfidenceInDisposition ("PercConfidenceInDisposition", 8),
		FIeldNotes ("FIeldNotes", 9);
		
		private final String name;
		private final Integer colIndex;

		LISAIndicationTableColumns(String nm) {
			this(nm, -1);
		}

		LISAIndicationTableColumns(String nm, Integer colIdx) {
			name = nm;
			colIndex = colIdx;
		}

		public Integer getIndex() {
			return colIndex;
		}

		public String toString() {
			return this.name;
		}
	}

	public enum ViewTableColumns {
		ViewName ("ViewName", 1),
		ShowLISAs ("ShowLISAs", 2),
		ShowFOV ("ShowFOV", 3),
		ShowBreadcrumb ("ShowBreadcrumb", 4),
		ShowIndications ("ShowIndications", 5),
		ShowAnalysis ("ShowAnalysis", 6),
		ShowFieldNotes ("ShowFieldNotes", 7),
		ShowGaps ("ShowGaps", 8),
		ShowAssets ("ShowAssets", 9),
		ShowBoundaries ("ShowBoundaries", 10),
		BaseMap ("BaseMap", 11);
		
		private final String name;
		private final Integer colIndex;

		ViewTableColumns(String nm) {
			this(nm, -1);
		}

		ViewTableColumns(String nm, Integer colIdx) {
			name = nm;
			colIndex = colIdx;
		}

		public Integer getIndex() {
			return colIndex;
		}
		
		public String toString() {
			return this.name;
		}
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

	public CustomerBoundaryFilterType getCustomerBoundaryFilterType() {
		return customerBoundaryFilterType;
	}

	public String getCustomerBoundaryName() {
		return customerBoundaryName;
	}

	public void setCustomerBoundaryInfo(CustomerBoundaryFilterType customerBoundaryFilterType, String customerBoundaryName) {
		this.customerBoundaryFilterType = customerBoundaryFilterType;
		this.customerBoundaryName = customerBoundaryName;
	}

	public int getLatLongXOffset() {
		return latLongXOffset;
	}

	public int getLatLongYOffset() {
		return latLongYOffset;
	}

	public int getLatLongRectHeight() {
		return latLongRectHeight;
	}

	public int getLatLongRectWidth() {
		return latLongRectWidth;
	}

	public void setCustomBoundaryInfo(int latLongXOffset, int latLongYOffset, int latLongRectHeight, int latLongRectWidth) {
		this.latLongXOffset = latLongXOffset;
		this.latLongYOffset = latLongYOffset;
		this.latLongRectHeight = latLongRectHeight;
		this.latLongRectWidth = latLongRectWidth;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
