package surveyor.scommon.actions.data;

import common.source.ExcelUtility;
import common.source.Log;

import surveyor.scommon.actions.ActionArguments;

public class ReportViewsDataReader extends BaseDataReader {

	public ReportViewsDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	private static final String TESTDATA_SHEET_NAME = "Report Views Data";
	 
	public static final int Excel_TestData_Report_Col_RowID = 0;
	public static final int Excel_TestData_Report_Col_Name = 1;
	public static final int Excel_TestData_Report_Col_LISAs = 2;
	public static final int Excel_TestData_Report_Col_FOV = 3;
	public static final int Excel_TestData_Report_Col_Breadcrumbs = 4;
	public static final int Excel_TestData_Report_Col_Indications = 5;
	public static final int Excel_TestData_Report_Col_FieldNotes = 6;
	public static final int Excel_TestData_Report_Col_Gaps = 7;
	public static final int Excel_TestData_Report_Col_Assets = 8;
	public static final int Excel_TestData_Report_Col_Boundaries = 9;
	public static final int Excel_TestData_Report_Col_IsotopicCapture = 10;
	public static final int Excel_TestData_Report_Col_Annotation = 11;
	public static final int Excel_TestData_Report_Col_BaseMap = 12;
 
	public class ReportViewsDataRow {
		public String rowID;
		public String name;
		public String lISAs;
		public String fOV;
		public String breadcrumbs;
		public String indications;
		public String fieldNotes;
		public String gaps;
		public String assets;
		public String boundaries;
		public String isotopicCapture;
		public String annotation;
		public String baseMap;
 
		public ReportViewsDataRow(String rowID, String name, String lISAs, String fOV, String breadcrumbs, String indications, String fieldNotes, String gaps, String assets, String boundaries, String isotopicCapture, String annotation, String baseMap) {
			this.rowID = rowID;
			this.name = name;
			this.lISAs = lISAs;
			this.fOV = fOV;
			this.breadcrumbs = breadcrumbs;
			this.indications = indications;
			this.fieldNotes = fieldNotes;
			this.gaps = gaps;
			this.assets = assets;
			this.boundaries = boundaries;
			this.isotopicCapture = isotopicCapture;
			this.annotation = annotation;
			this.baseMap = baseMap;
		}
	}	
 
	private ReportViewsDataRow dataRow = null;
 
	public ReportViewsDataRow getDataRow() {
		return dataRow;
	}
 
	public void setDataRow(ReportViewsDataRow dataRow) {
		this.dataRow = dataRow;
	}
 
	public ReportViewsDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData_Report_Col_RowID, TESTDATA_SHEET_NAME);
		String name = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_Name, TESTDATA_SHEET_NAME);
		name = ActionArguments.evaluateArgForFunction(name);
		String lISAs = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_LISAs, TESTDATA_SHEET_NAME);
		String fOV = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_FOV, TESTDATA_SHEET_NAME);
		String breadcrumbs = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_Breadcrumbs, TESTDATA_SHEET_NAME);
		String indications = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_Indications, TESTDATA_SHEET_NAME);
		String fieldNotes = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_FieldNotes, TESTDATA_SHEET_NAME);
		String gaps = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_Gaps, TESTDATA_SHEET_NAME);
		String assets = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_Assets, TESTDATA_SHEET_NAME);
		String boundaries = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_Boundaries, TESTDATA_SHEET_NAME);
		String isotopicCapture = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_IsotopicCapture, TESTDATA_SHEET_NAME);
		String annotation = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_Annotation, TESTDATA_SHEET_NAME);
		String baseMap = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_BaseMap, TESTDATA_SHEET_NAME);
		
		Log.info(String.format("Found data row: rowID=[%s], name=[%s], lISAs=[%s], fOV=[%s], breadcrumbs=[%s], indications=[%s], fieldNotes=[%s], gaps=[%s], assets=[%s], boundaries=[%s], isotopicCapture=[%s], annotation=[%s], baseMap=[%s]", rowID, name, lISAs, fOV, breadcrumbs, indications, fieldNotes, gaps, assets, boundaries, isotopicCapture, annotation, baseMap));
		
		return new ReportViewsDataRow(rowID, name, lISAs, fOV, breadcrumbs, indications, fieldNotes, gaps, assets, boundaries, isotopicCapture, annotation, baseMap);
	}
}
