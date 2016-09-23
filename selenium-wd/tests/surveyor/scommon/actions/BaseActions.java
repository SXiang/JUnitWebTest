package surveyor.scommon.actions;

import java.io.File;
import java.io.IOException;

import common.source.ExcelUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;

public class BaseActions implements IActions {

	protected static final String ARG_DATA_ROW_ID = "dataRowID";
	protected static final String ARG_DATA = "data";
	public static final String EMPTY = "";
	public static final Integer NOTSET = -1;

	protected ExcelUtility excelUtility = null;
	
	public BaseActions() {
		this.setExcelFile(TestSetup.TEST_DATA_XLSX);
	}
	
	protected void setExcelFile(String excelFileName) {
		String testDataExcelPath = TestContext.INSTANCE.getExecutionPath() + TestSetup.DATA_FOLDER + File.separator + excelFileName;
		try {
			excelUtility = new ExcelUtility();
			excelUtility.setExcelFile(testDataExcelPath);
		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

	public static ExcelUtility getExcelUtility() throws Exception, IOException {
		ExcelUtility excelUtility = new ExcelUtility();
		excelUtility.setExcelFile(TestSetup.TEST_DATA_XLSX);
		return excelUtility;
	}
	
	protected void logAction(String actionName, String data, Integer dataRowID) {
		Log.info(String.format("Executing action-[%s] : data=[%s], dataRowID=[%d]", actionName, data, dataRowID));
	}

	protected void log(String logText) {
		Log.info(logText);
	}

	@Override
	public boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception {
		return false;
	}
}
