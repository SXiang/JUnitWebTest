package surveyor.scommon.actions.data;

import common.source.CryptoUtility;
import common.source.ExcelUtility;
import common.source.Log;

public class UserDataReader extends BaseDataReader {

	public UserDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	public UserDataRow createDataRow(String rowID, String username, String password, String enabled, String role,
			String firstName, String lastName, String cellNumber, String timezone, String language, String locationRowID,
			String customerRowID) {
		return new UserDataRow(rowID, username, password, enabled, role, firstName, lastName, cellNumber, timezone, 
				language, locationRowID, customerRowID);
	}
	
	private static final String TESTDATA_SHEET_NAME = "Users";
	 
	public static final int Excel_TestData__Col_RowID = 0;
	public static final int Excel_TestData__Col_Username = 1;
	public static final int Excel_TestData__Col_Password = 2;
	public static final int Excel_TestData__Col_Enabled = 3;
	public static final int Excel_TestData__Col_Role = 4;
	public static final int Excel_TestData__Col_FirstName = 5;
	public static final int Excel_TestData__Col_LastName = 6;
	public static final int Excel_TestData__Col_CellNumber = 7;
	public static final int Excel_TestData__Col_Timezone = 8;
	public static final int Excel_TestData__Col_Language = 9;
	public static final int Excel_TestData__Col_LocationRowID = 10;
	public static final int Excel_TestData__Col_CustomerRowID = 11;
 
	public class UserDataRow {
		public String rowID;
		public String username;
		public String password;
		public String enabled;
		public String role;
		public String firstName;
		public String lastName;
		public String cellNumber;
		public String timezone;
		public String language;
		public String locationRowID;
		public String customerRowID;
 
		public UserDataRow(String rowID, String username, String password, String enabled, String role, String firstName, 
				String lastName, String cellNumber, String timezone, String language, String locationRowID, String customerRowID) {
			this.rowID = rowID;
			this.username = username;
			this.password = password;
			this.enabled = enabled;
			this.role = role;
			this.firstName = firstName;
			this.lastName = lastName;
			this.cellNumber = cellNumber;
			this.timezone = timezone;
			this.language = language;
			this.locationRowID = locationRowID;
			this.customerRowID = customerRowID;
		}
	}	
 
	private UserDataRow dataRow = null;
 
	public UserDataRow getDataRow() {
		return dataRow;
	}
 
	public void setDataRow(UserDataRow dataRow) {
		this.dataRow = dataRow;
	}
 
	public UserDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_RowID, TESTDATA_SHEET_NAME);
		String username = excelUtility.getCellData(dataRowID, Excel_TestData__Col_Username, TESTDATA_SHEET_NAME);
		String password = excelUtility.getCellData(dataRowID, Excel_TestData__Col_Password, TESTDATA_SHEET_NAME);
		password = CryptoUtility.decrypt(password);
		String enabled = excelUtility.getBooleanCellData(dataRowID, Excel_TestData__Col_Enabled, TESTDATA_SHEET_NAME);
		String role = excelUtility.getCellData(dataRowID, Excel_TestData__Col_Role, TESTDATA_SHEET_NAME);
		String firstName = excelUtility.getCellData(dataRowID, Excel_TestData__Col_FirstName, TESTDATA_SHEET_NAME);
		String lastName = excelUtility.getCellData(dataRowID, Excel_TestData__Col_LastName, TESTDATA_SHEET_NAME);
		String cellNumber = excelUtility.getCellData(dataRowID, Excel_TestData__Col_CellNumber, TESTDATA_SHEET_NAME);
		String timezone = excelUtility.getCellData(dataRowID, Excel_TestData__Col_Timezone, TESTDATA_SHEET_NAME);
		String language = excelUtility.getCellData(dataRowID, Excel_TestData__Col_Language, TESTDATA_SHEET_NAME);
		String locationRowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_LocationRowID, TESTDATA_SHEET_NAME);
		String customerRowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_CustomerRowID, TESTDATA_SHEET_NAME);
		
		Log.info(String.format("Found data row: rowID=[%s], username=[%s], password=[%s], enabled=[%s], role=[%s], "
				+ "firstName=[%s], lastName=[%s], cellNumber=[%s], timezone=[%s], language=[%s], locationRowID=[%s], "
				+ "customerRowID=[%s]", rowID, username, password, enabled, role, firstName, lastName, cellNumber, timezone, 
				language, locationRowID, customerRowID));
		
		return new UserDataRow(rowID, username, password, enabled, role, firstName, lastName, cellNumber, timezone, language, 
				locationRowID, customerRowID);
	}
}
