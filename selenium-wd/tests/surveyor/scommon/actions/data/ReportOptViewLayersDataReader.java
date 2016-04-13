package surveyor.scommon.actions.data;

import common.source.ExcelUtility;
import common.source.Log;

public class ReportOptViewLayersDataReader extends BaseDataReader {

	public ReportOptViewLayersDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	private static final String TESTDATA_SHEET_NAME = "Report Opt View Layers";
	 
	public static final int Excel_TestData__Col_RowID = 0;
	public static final int Excel_TestData__Col_AssetRowIDs = 1;
	public static final int Excel_TestData__Col_BoundariesRowIDs = 2;
	public static final int Excel_TestData__Col_Notes = 3;
 
	public class ReportOptViewLayersDataRow {
		public String rowID;
		public String assetRowIDs;
		public String boundariesRowIDs;
		public String notes;
 
		public ReportOptViewLayersDataRow(String rowID, String assetRowIDs, String boundariesRowIDs, String notes) {
			this.rowID = rowID;
			this.assetRowIDs = assetRowIDs;
			this.boundariesRowIDs = boundariesRowIDs;
			this.notes = notes;
		}
	}	
 
	private ReportOptViewLayersDataRow dataRow = null;
 
	public ReportOptViewLayersDataRow getDataRow() {
		return dataRow;
	}
 
	public void setDataRow(ReportOptViewLayersDataRow dataRow) {
		this.dataRow = dataRow;
	}
 
	public ReportOptViewLayersDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_RowID, TESTDATA_SHEET_NAME);
		String assetRowIDs = excelUtility.getCellData(dataRowID, Excel_TestData__Col_AssetRowIDs, TESTDATA_SHEET_NAME);
		String boundariesRowIDs = excelUtility.getCellData(dataRowID, Excel_TestData__Col_BoundariesRowIDs, TESTDATA_SHEET_NAME);
		String notes = excelUtility.getCellData(dataRowID, Excel_TestData__Col_Notes, TESTDATA_SHEET_NAME);
		
		Log.info(String.format("Found data row: rowID=[%s], assetRowIDs=[%s], boundariesRowIDs=[%s], notes=[%s]", 
				rowID, assetRowIDs, boundariesRowIDs, notes));
		
		return new ReportOptViewLayersDataRow(rowID, assetRowIDs, boundariesRowIDs, notes);
	}
}
