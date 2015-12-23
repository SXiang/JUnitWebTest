package surveyor.scommon.actions.data;

import common.source.ExcelUtility;

public class CustomerDataReader extends BaseDataReader {

	public CustomerDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	private static final String TESTDATA_SHEET_NAME = "Customers";
	 
	public static final int Excel_TestData_Report_Col_RowID = 0;
	public static final int Excel_TestData_Report_Col_Name = 1;
	public static final int Excel_TestData_Report_Col_Enabled = 2;
 
	public class CustomerDataRow {
		public String rowID;
		public String name;
		public String enabled;
 
		public CustomerDataRow(String rowID, String name, String enabled) {
			this.rowID = rowID;
			this.name = name;
			this.enabled = enabled;
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
		String rowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData_Report_Col_RowID, TESTDATA_SHEET_NAME);
		String name = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_Name, TESTDATA_SHEET_NAME);
		String enabled = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_Enabled, TESTDATA_SHEET_NAME);
		
		System.out.println(String.format("Found data row: rowID=[%s], name=[%s], enabled=[%s]", rowID, name, enabled));
		
		return new CustomerDataRow(rowID, name, enabled);
	}
}
