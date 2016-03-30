package surveyor.scommon.actions.data;

import common.source.ExcelUtility;
import common.source.Log;

public class CustomerDataReader extends BaseDataReader {

	public CustomerDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	private static final String TESTDATA_SHEET_NAME = "Customers";
	 
	public static final int Excel_TestData__Col_RowID = 0;
	public static final int Excel_TestData__Col_Name = 1;
	public static final int Excel_TestData__Col_Enabled = 2;
	public static final int Excel_TestData__Col_EULA = 3;
	public static final int Excel_TestData__Col_LicensedFeaturesRowIDs = 4;
	
	public class CustomerDataRow {
		public String rowID;
		public String name;
		public String enabled;
		public String eULA;
		public String licensedFeaturesRowIDs;
		
		public CustomerDataRow(String rowID, String name, String enabled, String eULA, String licensedFeaturesRowIDs) {
			this.rowID = rowID;
			this.name = name;
			this.enabled = enabled;
			this.eULA = eULA;
			this.licensedFeaturesRowIDs = licensedFeaturesRowIDs;
		}
	}	
 
	private CustomerDataRow dataRow = null;
 
	public CustomerDataRow getDataRow() {
		return dataRow;
	}
 
	public void setDataRow(CustomerDataRow dataRow) {
		this.dataRow = dataRow;
	}
 
	public CustomerDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getCellData(dataRowID, Excel_TestData__Col_RowID, TESTDATA_SHEET_NAME);
		String name = excelUtility.getCellData(dataRowID, Excel_TestData__Col_Name, TESTDATA_SHEET_NAME);
		String enabled = excelUtility.getCellData(dataRowID, Excel_TestData__Col_Enabled, TESTDATA_SHEET_NAME);
		String eULA = excelUtility.getCellData(dataRowID, Excel_TestData__Col_EULA, TESTDATA_SHEET_NAME);
		String licensedFeaturesRowIDs = excelUtility.getCellData(dataRowID, Excel_TestData__Col_LicensedFeaturesRowIDs, TESTDATA_SHEET_NAME);
		
		Log.info(String.format("Found data row: rowID=[%s], name=[%s], enabled=[%s], eULA=[%s], licensedFeaturesRowIDs=[%s]", 
				rowID, name, enabled, eULA, licensedFeaturesRowIDs));
		
		return new CustomerDataRow(rowID, name, enabled, eULA, licensedFeaturesRowIDs);
	}
}
