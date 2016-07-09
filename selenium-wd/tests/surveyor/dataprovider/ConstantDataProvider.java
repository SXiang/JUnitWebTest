package surveyor.dataprovider;

import java.io.IOException;

import common.source.ExcelUtility;
import common.source.ExcelUtility.ValueType;
import common.source.Log;
import common.source.TestContext;

public class ConstantDataProvider {

	private ExcelUtility excelUtility;
	private static ConstantDataProvider cdp;
	
	private ConstantDataProvider(){};
	
	public static ConstantDataProvider getConstantDataProvider(){
		String path = null;
		if(cdp==null){
			try {
				path = TestContext.INSTANCE.getTestSetup().getTestCaseDataPath();
				return getConstantDataProvider(path);
			} catch (IOException e) {
				Log.error("Failed to get the file path of TestCaseData");
				return null;
			}
		}
		return cdp;
	}
	
	public static ConstantDataProvider getConstantDataProvider(String path){
		if(cdp==null||!cdp.excelUtility.getExcelFilePath().equalsIgnoreCase(path)){
			cdp = new ConstantDataProvider();
		    cdp.excelUtility = new ExcelUtility();
		    cdp.excelUtility.setExcelFile(path);
		}
		return cdp;
	}

	public boolean getCellDataBoolean(String sheetName, String columnName, int rowId){
		String value = excelUtility.getCellData(rowId, columnName, sheetName, ValueType.Boolean);
		return Boolean.valueOf(value);
	}
	public String getCellData(String sheetName, String columnName, int rowId){
		String value = excelUtility.getCellData(rowId, columnName, sheetName, ValueType.String);
		return value;
	}
	public int getCellDataInteger(String sheetName, String columnName, int rowId){
		String value = excelUtility.getCellData(rowId, columnName, sheetName, ValueType.Integer);
		return Integer.valueOf(value);
	}
	public double getCellDataDouble(String sheetName, String columnName, int rowId){
		String value = excelUtility.getCellData(rowId, columnName, sheetName, ValueType.Numeric);
		return Double.valueOf(value);
	}
}