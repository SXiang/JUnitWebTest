package surveyor.scommon.actions.data;

import common.source.ExcelUtility;

public class BaseDataReader {
	protected ExcelUtility excelUtility;
	
	public BaseDataReader(ExcelUtility excelUtility) {
		this.excelUtility = excelUtility;
	}
	
	protected Integer getRowCount(String testSheetName) {
		return excelUtility.getRowCount(testSheetName);
	}
}
