package surveyor.scommon.actions.data;

import common.source.ExcelUtility;

public class UserDataReader extends BaseDataReader {

	public UserDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	private static final String TESTDATA_SHEET_NAME = "Users";
	 
	public static final int Excel_TestData_Report_Col_RowID = 0;
	public static final int Excel_TestData_Report_Col_Username = 1;
	public static final int Excel_TestData_Report_Col_Password = 2;
	public static final int Excel_TestData_Report_Col_Enabled = 3;
	public static final int Excel_TestData_Report_Col_Role = 4;
 
	public class UserDataRow {
		public String rowID;
		public String username;
		public String password;
		public String enabled;
		public String role;
 
		public UserDataRow(String rowID, String username, String password, String enabled, String role) {
			this.rowID = rowID;
			this.username = username;
			this.password = password;
			this.enabled = enabled;
			this.role = role;
		}
	}	
 
	private UserDataRow dataRow = null;
 
	public UserDataRow createDataRow(String rowID, String username, String password, String enabled, String role) {
		return new UserDataRow(rowID, username, password, enabled, role);
	}

	public UserDataRow getDataRow() {
		return dataRow;
	}
 
	public void setDataRow(UserDataRow dataRow) {
		this.dataRow = dataRow;
	}
 
	public UserDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData_Report_Col_RowID, TESTDATA_SHEET_NAME);
		String username = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_Username, TESTDATA_SHEET_NAME);
		String password = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_Password, TESTDATA_SHEET_NAME);
		String enabled = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_Enabled, TESTDATA_SHEET_NAME);
		String role = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_Role, TESTDATA_SHEET_NAME);
		
		System.out.println(String.format("Found data row: rowID=[%s], username=[%s], password=[%s], enabled=[%s], role=[%s]", rowID, username, password, enabled, role));
		
		return new UserDataRow(rowID, username, password, enabled, role);
	}

}
