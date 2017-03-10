package surveyor.scommon.actions.data;

import common.source.ExcelUtility;
import common.source.Log;
import common.source.NumberUtility;
import surveyor.scommon.actions.ActionArguments;
import surveyor.scommon.source.SurveyorConstants.LicensedFeatures;

public class CoordinateDataReader extends BaseDataReader {

	public CoordinateDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	public static final String TESTDATA_SHEET_NAME = "Coordinates";
	 
	public static final int Excel_TestData__Col_RowID = 0;
	public static final int Excel_TestData__Col_Latitude = 1;
	public static final int Excel_TestData__Col_Longitude = 2;
	
	public class CoordinateDataRow {
		public String rowID;
		public double latitude;
		public double longitude;
		
		public CoordinateDataRow(String rowID, double latitude, double longitude) {
			this.rowID = rowID;
			this.latitude = latitude;
			this.longitude = longitude;
		}

	}	
 
	private CoordinateDataRow dataRow = null;
 
	public CoordinateDataRow getDataRow() {
		return dataRow;
	}
 
	public Integer getRowCount() {
		return this.getRowCount(TESTDATA_SHEET_NAME);
	}
	
	public void setDataRow(CoordinateDataRow dataRow) {
		this.dataRow = dataRow;
	}
 
	public CoordinateDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_RowID, TESTDATA_SHEET_NAME);
		String latitude = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_Latitude, TESTDATA_SHEET_NAME);
		String longitude = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_Longitude, TESTDATA_SHEET_NAME);
		
		Log.info(String.format("Found data row: rowID=[%s], latitude=[%s], longitude=[%s]", 
				rowID, latitude, longitude));
		
		return new CoordinateDataRow(rowID, NumberUtility.getDoubleValueOf(latitude), NumberUtility.getDoubleValueOf(longitude));
	}
}
