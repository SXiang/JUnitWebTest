package surveyor.scommon.entities;

import java.util.List;
import java.util.Map;

import common.source.Log;

public class ReportCommonEntity extends BaseReportEntity {
	public static final String ASSET_PREFIX = "Asset_";
	public static final String BOUNDARY_PREFIX = "Boundary_";
	public static final String ASSET_ALL_PREFIX = "AssetAll_";
	public static final String BOUNDARY_ALL_PREFIX = "BoundaryAll_";
	public static final String CANVAS_X_PATH = "//*[@id=\"map\"]/div/canvas";

	//Report
	protected String exclusionRadius;

	//Custom Boundary
	protected List<String> listBoundary;
	//Custom Boundary
	protected String NELat;
	protected String NELong;
	protected String SWLat;
	protected String SWLong;

	//Custom Boundary - Lat/Long Map Selector
	private int latLongXOffset;
	private int latLongYOffset;
	private int latLongRectHeight;
	private int latLongRectWidth;

	//View Size (PDF image output);
	protected String imageMapHeight;
	protected String imageMapWidth;
	//Opacity Fine-Tuning
	protected String fovOpacity;
	//Views
	protected List<Map<String, String>> viewList;
	//Optional Tablular PDF Content
	protected List<Map<String, String>> tablesList;
	//Optional View Layers
	protected List<Map<String, String>> viewLayersList;

	protected SurveyModeFilter surveyModeFilter;
	protected ReportModeFilter reportModeFilter;
	protected EthaneFilter ethaneFilter;

	private String customerBoundaryName;
	private CustomerBoundaryFilterType customerBoundaryFilterType;

	public enum EthaneFilter {
		ExcludeVehicleExhaust, ExcludeBiogenicMethane, ExcludePossibleNaturalGas, All, None
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
		StartDateTime ("StartDateTime", 0),
		EndDateTime ("EndDateTime", 1),
		Duration ("Duration", 2),
		username ("username", 3),
		Surveyor ("Surveyor", 4),
		Analyzer ("Analyzer", 5),
		Tag ("Tag", 6),
		FOV ("FOV", 7),
		LISA ("LISA", 8),
		Analysis ("Analysis", 9),
		StabilityClass ("StabilityClass", 10);

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
		Surveyor ("Surveyor", 0),
		DateTime ("DateTime", 1),
		Result ("Result", 2),
		IsotopicValueUncertainty ("IsotopicValueUncertainty", 3),
		FieldNotes ("FieldNotes", 4);

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
		// 0-based column index. First column has checkbox image.
		LISANum ("LISANum", 1),
		Surveyor ("Surveyor", 2),
		DateTime ("DateTime", 3),
		Amplitude ("Amplitude", 4),
		Concentration ("Concentration", 5),
		EthaneMethanRatio ("EthaneMethanRatio", 6),
		Disposition ("Disposition", 7),
		PercConfidenceInDisposition ("PercConfidenceInDisposition", 7),
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
		ViewName ("ViewName", 0),
		ShowLISAs ("ShowLISAs", 1),
		ShowFOV ("ShowFOV", 2),
		ShowBreadcrumb ("ShowBreadcrumb", 3),
		ShowIndications ("ShowIndications", 4),
		ShowAnalysis ("ShowAnalysis", 5),
		ShowFieldNotes ("ShowFieldNotes", 6),
		ShowGaps ("ShowGaps", 7),
		ShowAssets ("ShowAssets", 8),
		ShowBoundaries ("ShowBoundaries", 9),
		BaseMap ("BaseMap", 10);

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

	public ReportCommonEntity(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary,
			List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList, List<Map<String, String>> viewList) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit,tagList);
		this.exclusionRadius = exclusionRadius;
		this.imageMapHeight=listBoundary.get(0);
		this.imageMapWidth=listBoundary.get(1);
		if (listBoundary.size() > 2) {
			this.NELat = listBoundary.get(2);
			this.NELong =listBoundary.get(3);
			this.SWLat = listBoundary.get(4);
			this.SWLong = listBoundary.get(5);
		}
		this.listBoundary=listBoundary;
		this.tablesList = tablesList;
		this.viewList=viewList;
	}

	public ReportCommonEntity(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary,
			List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList, List<Map<String, String>> viewList, List<Map<String, String>> viewLayersList) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, tagList);
		this.exclusionRadius=exclusionRadius;
		this.setListBoundary(listBoundary);

		this.tablesList=tablesList;
		this.tagList=tagList;
		this.viewList=viewList;
		this.viewLayersList=viewLayersList;
	}

	public ReportCommonEntity(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary,
			List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList, String startDate, String endDate, List<Map<String, String>> viewList, SurveyModeFilter surveyMode) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, tagList, startDate, endDate);
		this.exclusionRadius=exclusionRadius;
        this.setListBoundary(listBoundary);

		this.tablesList = tablesList;
		this.viewList= viewList;
		this.surveyModeFilter=surveyMode;
	}

	public ReportCommonEntity(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary, List<Map<String, String>> tablesList,
			String surveyorUnit, List<String> tagList, String startDate, String endDate, List<Map<String, String>> viewList, SurveyModeFilter surveyMode, ReportModeFilter reportMode) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, tagList, startDate, endDate);
		this.exclusionRadius=exclusionRadius;
		this.setListBoundary(listBoundary);

		this.tablesList=tablesList;
		this.viewList=viewList;
		this.surveyModeFilter=surveyMode;
		this.reportModeFilter=reportMode;
	}

	public ReportCommonEntity(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary,
			List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList, String startDate, String endDate,
			List<Map<String, String>> viewList, SurveyModeFilter surveyMode, Boolean geoFilter) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, tagList, startDate, endDate, geoFilter);
		this.exclusionRadius=exclusionRadius;
		this.setListBoundary(listBoundary);

		this.tablesList=tablesList;
		this.viewList=viewList;
		this.surveyModeFilter=surveyMode;
	}

	public ReportCommonEntity(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary,
			List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList, String startDate, String endDate, List<Map<String, String>> viewList,
			SurveyModeFilter surveyMode, String userName, Boolean geoFilterOn, ReportModeFilter reportMode) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit,  startDate,endDate, userName, geoFilterOn,tagList);
		this.exclusionRadius=exclusionRadius;
		this.setListBoundary(listBoundary);

		this.tablesList=tablesList;
		this.viewList=viewList;
		this.surveyModeFilter=surveyMode;
		this.reportModeFilter=reportMode;
	}

	public ReportCommonEntity(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, List<String> listBoundary,
			List<Map<String, String>> tablesList, String surveyorUnit, List<String> tagList, List<Map<String, String>> viewList, ReportModeFilter reportMode) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, tagList);
		this.exclusionRadius=exclusionRadius;
		this.setListBoundary(listBoundary);

		this.tablesList=tablesList;
		this.viewList=viewList;
		this.reportModeFilter=reportMode;
	}

	public ReportCommonEntity(String rptTitle, String strCreatedBy, String customer, String timeZone, String exclusionRadius, String surveyorUnit, String userName,
			String startDate, String endDate, String fovOpacity, Boolean geoFilter, ReportModeFilter reportMode, SurveyModeFilter surveyModeFilter,
			EthaneFilter ethaneFilter, List<String> listBoundary, List<String> tagList, List<Map<String, String>> tablesList, List<Map<String, String>> viewList,
			List<Map<String, String>> viewLayersList) {
		super(rptTitle, strCreatedBy, customer, timeZone, surveyorUnit, userName, startDate, endDate,geoFilter, tagList);
		this.exclusionRadius=exclusionRadius;
		this.setListBoundary(listBoundary);

		this.fovOpacity=fovOpacity;
		this.reportModeFilter=reportMode;
		this.surveyModeFilter=surveyModeFilter;
		this.ethaneFilter=ethaneFilter;
		this.tablesList=tablesList;
		this.viewList=viewList;
		this.viewLayersList=viewLayersList;
	}

	public ReportCommonEntity(){
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
		try{
			this.imageMapHeight = listBoundary.get(0);
			this.imageMapWidth = listBoundary.get(1);
			if (listBoundary.size() > 2) {
				this.NELat = listBoundary.get(2);
				this.NELong = listBoundary.get(3);
				this.SWLat = listBoundary.get(4);
				this.SWLong = listBoundary.get(5);
			}
		}catch(Exception e){
			Log.error(e.toString());
		}
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
	public void setCustomerBoundaryInfo(String customerBoundaryType, String customerBoundaryName) {
		customerBoundaryType = customerBoundaryType.replaceAll(" ", "");
		CustomerBoundaryFilterType customerBoundaryFilterType = null;
		try{
			customerBoundaryFilterType = CustomerBoundaryFilterType.valueOf(customerBoundaryType);
		}catch(Exception e){}
		setCustomerBoundaryInfo(customerBoundaryFilterType, customerBoundaryName);
	}
	public void setCustomerBoundaryInfo(CustomerBoundaryFilterType customerBoundaryFilterType, String customerBoundaryName) {
		this.customerBoundaryFilterType = customerBoundaryFilterType;
		this.customerBoundaryName = customerBoundaryName;
	}

	public static CustomerBoundaryFilterType getCustomerBoundaryType(String custBoundaryType) {
		CustomerBoundaryFilterType customerBoundaryFilterType = CustomerBoundaryFilterType.District;
		if (custBoundaryType.equals("District")) {
			customerBoundaryFilterType = CustomerBoundaryFilterType.District;
		} else if (custBoundaryType.equals("District Plat")) {
			customerBoundaryFilterType = CustomerBoundaryFilterType.DistrictPlat;
		} else if (custBoundaryType.equals("Big Boundary")) {
			customerBoundaryFilterType = CustomerBoundaryFilterType.BigBoundary;
		} else if (custBoundaryType.equals("Small Boundary")) {
			customerBoundaryFilterType = CustomerBoundaryFilterType.SmallBoundary;
		} else if (custBoundaryType.equals("Leak Survey Area")) {
			customerBoundaryFilterType = CustomerBoundaryFilterType.LeakSurveyArea;
		}
		return customerBoundaryFilterType;
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

	public String getFovOpacity() {
		return fovOpacity;
	}

	public void setFovOpacity(String fovOpacity) {
		this.fovOpacity = fovOpacity;
	}

	public void setCustomerBoundaryName(String customerBoundaryName) {
		this.customerBoundaryName = customerBoundaryName;
	}

	public void setCustomerBoundaryFilterType(CustomerBoundaryFilterType customerBoundaryFilterType) {
		this.customerBoundaryFilterType = customerBoundaryFilterType;
	}
}