package surveyor.scommon.actions.data;

import java.util.HashMap;
import java.util.Map;

import common.source.ExcelUtility;
import common.source.Log;

public class FacilityEQReportDataReader extends ReportsBaseDataReader {

	private static final String TESTDATA_SHEET_NAME = "FEQ Report Test Data";

	public static final int Excel_TestData__Col_RowID = 0;
	public static final int Excel_TestData__Col_TCID = 1;
	public static final int Excel_TestData__Col_Title = 2;
	public static final int Excel_TestData__Col_CustomerRowID = 3;
	public static final int Excel_TestData__Col_Timezone = 4;
	public static final int Excel_TestData__Col_FacilityEQLocationParameter = 5;
	public static final int Excel_TestData__Col_ReportSurveyRowIDs = 6;
	public static final int Excel_TestData__Col_ShapeCoordinatesRowIDs = 7;
	public static final int Excel_TestData__Col_ShowLisas = 7;

	public FacilityEQReportDataReader(ExcelUtility excelUtility) {
		super(excelUtility, () -> TESTDATA_SHEET_NAME, () -> buildColumnIndexMap());
	}

	public class FacilityEQReportsDataRow extends ReportsBaseDataRow {
		public String facilityEQLocationParameter;
		public String shapeCoordinatesRowIDs;
		public boolean showLisas;
		
		public FacilityEQReportsDataRow(String rowID, String tCID, String title, String customerRowID, String timezone, String facilityEQLocationParameter,
				String reportSurveyRowIDs, String shapeCoordinatesRowIDs, boolean showLisas) {
			super(rowID, tCID, title, customerRowID, timezone, reportSurveyRowIDs);
			this.facilityEQLocationParameter = facilityEQLocationParameter;
			this.shapeCoordinatesRowIDs = shapeCoordinatesRowIDs;
			this.showLisas = showLisas;
		}
	}
	private static Map<String, Integer> buildColumnIndexMap() {
		Map<String, Integer> columnIdxMap = new HashMap<String, Integer>();
		columnIdxMap.put("Col_RowID", Excel_TestData__Col_RowID);
		columnIdxMap.put("Col_TCID", Excel_TestData__Col_TCID);
		columnIdxMap.put("Col_Title", Excel_TestData__Col_Title);
		columnIdxMap.put("Col_CustomerRowID", Excel_TestData__Col_CustomerRowID);
		columnIdxMap.put("Col_Timezone", Excel_TestData__Col_Timezone);
		columnIdxMap.put("Col_FacilityEQLocationParameter", Excel_TestData__Col_FacilityEQLocationParameter);
		columnIdxMap.put("Col_ReportSurveyRowIDs", Excel_TestData__Col_ReportSurveyRowIDs);
		columnIdxMap.put("Col_ShapeCoordinatesRowIDs", Excel_TestData__Col_ShapeCoordinatesRowIDs);
		columnIdxMap.put("Col_ShowLisas", Excel_TestData__Col_ShowLisas);
		return columnIdxMap;
	}

	public FacilityEQReportsDataRow getDataRow(Integer dataRowID) throws Exception {
			ReportsBaseDataRow reportsDataRow = super.getDataRow(dataRowID);
			String facilityEQLocationParameter = excelUtility.getCellData(dataRowID, columnIndexMap.get("Col_facilityEQLocationParameter"), sheetName);			
			String shapeCoordinatesRowIDs = excelUtility.getCellData(dataRowID, columnIndexMap.get("Col_ShapeCoordinatesRowIDs"), sheetName);
			String showLisas = excelUtility.getBooleanCellData(dataRowID, columnIndexMap.get("Col_ShowLisas"), sheetName);
			Log.info(String.format("Found data row: rowID=[%s], tCID=[%s], title=[%s], customerRowID=[%s], timezone=[%s], facilityEQLocationParameter=[%s], "
				
				+ "reportSurveyRowIDs=[%s], shapeCoordinatesRowIDs=[%s]", reportsDataRow.rowID, reportsDataRow.tCID, reportsDataRow.title, reportsDataRow.customerRowID, reportsDataRow.timezone,
				facilityEQLocationParameter, reportsDataRow.reportSurveyRowIDs, shapeCoordinatesRowIDs));

		return new FacilityEQReportsDataRow(reportsDataRow.rowID, reportsDataRow.tCID, reportsDataRow.title, reportsDataRow.customerRowID, reportsDataRow.timezone, facilityEQLocationParameter, reportsDataRow.reportSurveyRowIDs, shapeCoordinatesRowIDs, Boolean.valueOf(showLisas));
	}
}
