package surveyor.scommon.actions.data;

import java.util.HashMap;
import java.util.Map;

import common.source.BaseHelper;
import common.source.ExcelUtility;
import common.source.Log;
import surveyor.scommon.actions.ActionArguments;
import surveyor.scommon.actions.data.ReportsCommonDataReader.ReportsCommonDataRow;

public class EQReportDataReader extends ReportsCommonDataReader {

	private static final String TESTDATA_SHEET_NAME = "EQ Report Test Data";

	public static final int Excel_TestData__Col_RowID = 0;
	public static final int Excel_TestData__Col_TCID = 1;
	public static final int Excel_TestData__Col_Title = 2;
	public static final int Excel_TestData__Col_CustomerRowID = 3;
	public static final int Excel_TestData__Col_Timezone = 4;
	public static final int Excel_TestData__Col_EQLocationParameter = 5;
	public static final int Excel_TestData__Col_ReportSurveyRowIDs = 6;
	public static final int Excel_TestData__Col_LineSegmentRowIDs = 7;

	public EQReportDataReader(ExcelUtility excelUtility) {
		super(excelUtility, () -> TESTDATA_SHEET_NAME, () -> buildColumnIndexMap());
	}

	public class EQReportsDataRow extends ReportsCommonDataRow {
		public String eqLocationParameter;
		public String lineSegmentRowIDs;

		public EQReportsDataRow(String rowID, String tCID, String title, String customerRowID, String timezone, String eqLocationParameter,
				String reportSurveyRowIDs, String lineSegmentRowIDs) {
			super(tCID, title, customerRowID, timezone, lineSegmentRowIDs, reportSurveyRowIDs);
			this.eqLocationParameter = eqLocationParameter;
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
		columnIdxMap.put("Col_EQLocationParameter", Excel_TestData__Col_EQLocationParameter);
		columnIdxMap.put("Col_ReportSurveyRowIDs", Excel_TestData__Col_ReportSurveyRowIDs);
		columnIdxMap.put("Col_LineSegmentRowIDs", Excel_TestData__Col_LineSegmentRowIDs);
		return columnIdxMap;
	}

	public EQReportsDataRow getDataRow(Integer dataRowID) throws Exception {
			String rowID = excelUtility.getIntegerCellData(dataRowID, columnIndexMap.get("Col_RowID"), sheetName);
			String tCID = excelUtility.getCellData(dataRowID, columnIndexMap.get("Col_TCID"), sheetName);
			String title = excelUtility.getCellData(dataRowID, columnIndexMap.get("Col_Title"), sheetName);
			title = ActionArguments.evaluateArgForFunction(title);
			if (!BaseHelper.isNullOrEmpty(tCID)) {
				title = String.format("%s-%s", tCID, title);
			}
			String customerRowID = excelUtility.getIntegerCellData(dataRowID, columnIndexMap.get("Col_CustomerRowID"), sheetName);
			String timezone = excelUtility.getCellData(dataRowID, columnIndexMap.get("Col_Timezone"), sheetName);
			String eqLocationParameter = excelUtility.getIntegerCellData(dataRowID, columnIndexMap.get("Col_EQLocationParameter"), sheetName);
			String reportSurveyRowIDs = excelUtility.getCellData(dataRowID, columnIndexMap.get("Col_ReportSurveyRowIDs"), sheetName);
			String lineSegmentRowIDs = excelUtility.getCellData(dataRowID, columnIndexMap.get("Col_LineSegmentRowIDs"), sheetName);

			Log.info(String.format("Found data row: rowID=[%s], tCID=[%s], title=[%s], customerRowID=[%s], timezone=[%s], eqLocationParameter=[%s], "
				
				+ "reportSurveyRowIDs=[%s], lineSegmentRowIDs=[%s]", rowID, tCID, title, customerRowID, timezone,
				eqLocationParameter, reportSurveyRowIDs, lineSegmentRowIDs));

		return new EQReportsDataRow(rowID, tCID, title, customerRowID, timezone, eqLocationParameter, reportSurveyRowIDs, lineSegmentRowIDs);
	}
}
