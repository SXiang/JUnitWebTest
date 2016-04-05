package surveyor.scommon.actions.data;

import common.source.ExcelUtility;
import common.source.Log;

public class LocationDataReader extends BaseDataReader {

	public LocationDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	private static final String TESTDATA_SHEET_NAME = "Locations";
	 
	public static final int Excel_TestData__Col_RowID = 0;
	public static final int Excel_TestData__Col_Name = 1;
	public static final int Excel_TestData__Col_Latitude = 2;
	public static final int Excel_TestData__Col_Longitude = 3;
 
	public class LocationDataRow {
		public String rowID;
		public String name;
		public String latitude;
		public String longitude;
 
		public LocationDataRow(String rowID, String name, String latitude, String longitude) {
			this.rowID = rowID;
			this.name = name;
			this.latitude = latitude;
			this.longitude = longitude;
		}
	}	
 
	private LocationDataRow dataRow = null;
 
	public LocationDataRow getDataRow() {
		return dataRow;
	}
 
	public void setDataRow(LocationDataRow dataRow) {
		this.dataRow = dataRow;
	}
 
	public LocationDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_RowID, TESTDATA_SHEET_NAME);
		String name = excelUtility.getCellData(dataRowID, Excel_TestData__Col_Name, TESTDATA_SHEET_NAME);
		String latitude = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_Latitude, TESTDATA_SHEET_NAME);
		String longitude = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_Longitude, TESTDATA_SHEET_NAME);
		
		Log.info(String.format("Found data row: rowID=[%s], name=[%s], latitude=[%s], longitude=[%s]", 
				rowID, name, latitude, longitude));
		
		return new LocationDataRow(rowID, name, latitude, longitude);
	}
}
