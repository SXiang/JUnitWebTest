package surveyor.scommon.actions.data;

import common.source.ExcelUtility;
import common.source.Log;

public class SurveyorDataReader extends BaseDataReader {

	public SurveyorDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	private static final String TESTDATA_SHEET_NAME = "Surveyors";
	 
	public static final int Excel_TestData__Col_RowID = 0;
	public static final int Excel_TestData__Col_Description = 1;
	public static final int Excel_TestData__Col_CustomerRowID = 2;
	public static final int Excel_TestData__Col_LocationRowID = 3;
 
	public class SurveyorDataRow {
		public String rowID;
		public String description;
		public String customerRowID;
		public String locationRowID;
 
		public SurveyorDataRow(String rowID, String description, String customerRowID, String locationRowID) {
			this.rowID = rowID;
			this.description = description;
			this.customerRowID = customerRowID;
			this.locationRowID = locationRowID;
		}
	}	
 
	private SurveyorDataRow dataRow = null;
 
	public SurveyorDataRow getDataRow() {
		return dataRow;
	}
 
	public Integer getRowCount() {
		return this.getRowCount(TESTDATA_SHEET_NAME);
	}
	
	public void setDataRow(SurveyorDataRow dataRow) {
		this.dataRow = dataRow;
	}
 
	public SurveyorDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getCellData(dataRowID, Excel_TestData__Col_RowID, TESTDATA_SHEET_NAME);
		String description = excelUtility.getCellData(dataRowID, Excel_TestData__Col_Description, TESTDATA_SHEET_NAME);
		String customerRowID = excelUtility.getCellData(dataRowID, Excel_TestData__Col_CustomerRowID, TESTDATA_SHEET_NAME);
		String locationRowID = excelUtility.getCellData(dataRowID, Excel_TestData__Col_LocationRowID, TESTDATA_SHEET_NAME);
		
		Log.info(String.format("Found data row: rowID=[%s], description=[%s], customerRowID=[%s], locationRowID=[%s]", 
				rowID, description, customerRowID, locationRowID));
		
		return new SurveyorDataRow(rowID, description, customerRowID, locationRowID);
	}
}
