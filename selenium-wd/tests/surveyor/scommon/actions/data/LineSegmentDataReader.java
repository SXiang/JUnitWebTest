package surveyor.scommon.actions.data;

import common.source.ExcelUtility;
import common.source.Log;
import surveyor.scommon.actions.ActionArguments;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;

public class LineSegmentDataReader extends BaseDataReader {

	public LineSegmentDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	public static final String TESTDATA_SHEET_NAME = "Line Segment Data";
	 
	public static final int Excel_TestData__Col_RowID = 0;
	public static final int Excel_TestData__Col_Coordinates_RowIDs = 1;
	
	public class LineSegmentDataRow {
		public String rowID;
		public String coordinatesRowIDs;
		
		public LineSegmentDataRow(String rowID, String coordinatesRowIDs) {
			this.rowID = rowID;
			this.coordinatesRowIDs = coordinatesRowIDs;
		}
	}	
 
	private LineSegmentDataRow dataRow = null;
 
	public LineSegmentDataRow getDataRow() {
		return dataRow;
	}
 
	public Integer getRowCount() {
		return this.getRowCount(TESTDATA_SHEET_NAME);
	}
	
	public void setDataRow(LineSegmentDataRow dataRow) {
		this.dataRow = dataRow;
	}
 
	public LineSegmentDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_RowID, TESTDATA_SHEET_NAME);
		String coordinatesRowIDs = excelUtility.getCellData(dataRowID, Excel_TestData__Col_Coordinates_RowIDs, TESTDATA_SHEET_NAME);
		
		Log.info(String.format("Found data row: rowID=[%s], coordinatesRowIDs=[%s]", 
				rowID, coordinatesRowIDs));
		
		return new LineSegmentDataRow(rowID, coordinatesRowIDs);
	}
}
