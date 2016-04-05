package surveyor.scommon.actions.data;

import common.source.ExcelUtility;
import common.source.Log;

public class CustomerLicensedFeaturesDataReader extends BaseDataReader {

	public CustomerLicensedFeaturesDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	private static final String TESTDATA_SHEET_NAME = "Customer Licensed Features";
	 
	public static final int Excel_TestData__Col_RowID = 0;
	public static final int Excel_TestData__Col_Name = 1;
 
	public class CustomerLicensedFeaturesDataRow {
		public String rowID;
		public String name;
 
		public CustomerLicensedFeaturesDataRow(String rowID, String name) {
			this.rowID = rowID;
			this.name = name;
		}
	}	
 
	private CustomerLicensedFeaturesDataRow dataRow = null;
 
	public CustomerLicensedFeaturesDataRow getDataRow() {
		return dataRow;
	}
 
	public void setDataRow(CustomerLicensedFeaturesDataRow dataRow) {
		this.dataRow = dataRow;
	}
 
	public CustomerLicensedFeaturesDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_RowID, TESTDATA_SHEET_NAME);
		String name = excelUtility.getCellData(dataRowID, Excel_TestData__Col_Name, TESTDATA_SHEET_NAME);
		
		Log.info(String.format("Found data row: rowID=[%s], name=[%s]", rowID, name));
		
		return new CustomerLicensedFeaturesDataRow(rowID, name);
	}
}
