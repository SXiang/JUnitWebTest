package surveyor.scommon.actions.data;

import java.util.HashMap;
import java.util.Map;

import common.source.ExcelUtility;
import common.source.Log;

public class EQReportDataReader extends ReportsBaseDataReader {

	private static final String TESTDATA_SHEET_NAME = "EQ Report Test Data";

	public static final int Excel_TestData__Col_RowID = 0;
	public static final int Excel_TestData__Col_TCID = 1;
	public static final int Excel_TestData__Col_Title = 2;
	public static final int Excel_TestData__Col_CustomerRowID = 3;
	public static final int Excel_TestData__Col_Timezone = 4;
	public static final int Excel_TestData__Col_LocationRowID = 5;
	public static final int Excel_TestData__Col_ReportSurveyRowIDs = 6;
	public static final int Excel_TestData__Col_LineSegmentRowIDs = 7;

	public EQReportDataReader(ExcelUtility excelUtility) {
		super(excelUtility, () -> TESTDATA_SHEET_NAME, () -> buildColumnIndexMap());
	}

	public class EQReportsDataRow extends ReportsBaseDataRow {
		public String locationRowID;
		public String lineSegmentRowIDs;

		public EQReportsDataRow(String rowID, String tCID, String title, String customerRowID, String timezone, String locationRowID,
				String reportSurveyRowIDs, String lineSegmentRowIDs) {
			super(rowID, tCID, title, customerRowID, timezone, reportSurveyRowIDs);
			this.locationRowID = locationRowID;
			this.lineSegmentRowIDs = lineSegmentRowIDs;
		}
	}
	private static Map<String, Integer> buildColumnIndexMap() {
		Map<String, Integer> columnIdxMap = new HashMap<String, Integer>();
		columnIdxMap.put("Col_RowID", Excel_TestData__Col_RowID);
		columnIdxMap.put("Col_TCID", Excel_TestData__Col_TCID);
		columnIdxMap.put("Col_Title", Excel_TestData__Col_Title);
		columnIdxMap.put("Col_CustomerRowID", Excel_TestData__Col_CustomerRowID);
		columnIdxMap.put("Col_Timezone", Excel_TestData__Col_Timezone);
		columnIdxMap.put("Col_LocationRowID", Excel_TestData__Col_LocationRowID);
		columnIdxMap.put("Col_ReportSurveyRowIDs", Excel_TestData__Col_ReportSurveyRowIDs);
		columnIdxMap.put("Col_LineSegmentRowIDs", Excel_TestData__Col_LineSegmentRowIDs);
		return columnIdxMap;
	}

	public EQReportsDataRow getDataRow(Integer dataRowID) throws Exception {
			ReportsBaseDataRow reportsDataRow = super.getDataRow(dataRowID);
			String locationRowID = excelUtility.getIntegerCellData(dataRowID, columnIndexMap.get("Col_LocationRowID"), sheetName);
			String lineSegmentRowIDs = excelUtility.getCellData(dataRowID, columnIndexMap.get("Col_LineSegmentRowIDs"), sheetName);

			Log.info(String.format("Found data row: rowID=[%s], tCID=[%s], title=[%s], customerRowID=[%s], timezone=[%s], eqLocationParameter=[%s], "
				+ "reportSurveyRowIDs=[%s], lineSegmentRowIDs=[%s]", reportsDataRow.rowID, reportsDataRow.tCID, reportsDataRow.title, reportsDataRow.customerRowID, reportsDataRow.timezone,
				locationRowID, reportsDataRow.reportSurveyRowIDs, lineSegmentRowIDs));

		return new EQReportsDataRow(reportsDataRow.rowID, reportsDataRow.tCID, reportsDataRow.title, reportsDataRow.customerRowID, reportsDataRow.timezone, locationRowID, reportsDataRow.reportSurveyRowIDs, lineSegmentRowIDs);
	}
}