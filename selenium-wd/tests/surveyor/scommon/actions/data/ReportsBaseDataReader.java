package surveyor.scommon.actions.data;

import java.util.Map;
import java.util.function.Supplier;

import common.source.BaseHelper;
import common.source.ExcelUtility;
import surveyor.scommon.actions.ActionArguments;

public class ReportsBaseDataReader extends BaseDataReader {
	protected String sheetName;
	protected Map<String, Integer> columnIndexMap;

	public ReportsBaseDataReader(ExcelUtility excelUtility, Supplier<String> sheetNm, Supplier<Map<String, Integer>> columnIdxMap) {
		super(excelUtility);
		this.sheetName = sheetNm.get();
		this.columnIndexMap = columnIdxMap.get();
	}

	public class ReportsBaseDataRow {
		public String rowID;
		public String tCID;
		public String title;
		public String customerRowID;
		public String timezone;
		public String reportSurveyRowIDs;

		public ReportsBaseDataRow(String rowID, String tCID, String title, String customerRowID, String timezone,
				String reportSurveyRowIDs) {
			this.rowID = rowID;
			this.tCID = tCID;
			this.title = title;
			this.customerRowID = customerRowID;
			this.timezone = timezone;
			this.reportSurveyRowIDs = reportSurveyRowIDs;
		}
	}
	private ReportsBaseDataRow dataRow = null;

	public ReportsBaseDataRow getDataRow() {
		return dataRow;
	}

	public Integer getRowCount() throws Exception {
		return this.getRowCount(sheetName);
	}

	public void setDataRow(ReportsBaseDataRow dataRow) {
		this.dataRow = dataRow;
	}

	public ReportsBaseDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getIntegerCellData(dataRowID, columnIndexMap.get("Col_RowID"), sheetName);
		String tCID = excelUtility.getCellData(dataRowID, columnIndexMap.get("Col_TCID"), sheetName);
		String title = excelUtility.getCellData(dataRowID, columnIndexMap.get("Col_Title"), sheetName);
		title = ActionArguments.evaluateArgForFunction(title);
		if (!BaseHelper.isNullOrEmpty(title)&& !BaseHelper.isNullOrEmpty(tCID)) {
			title = String.format("%s-%s", tCID, title);
		}
		String customerRowID = excelUtility.getIntegerCellData(dataRowID, columnIndexMap.get("Col_CustomerRowID"), sheetName);
		String timezone = excelUtility.getCellData(dataRowID, columnIndexMap.get("Col_Timezone"), sheetName);
		String reportSurveyRowIDs = excelUtility.getCellData(dataRowID, columnIndexMap.get("Col_ReportSurveyRowIDs"), sheetName);
		
		return new ReportsBaseDataRow(rowID, tCID, title, customerRowID, timezone, reportSurveyRowIDs);
	}
}