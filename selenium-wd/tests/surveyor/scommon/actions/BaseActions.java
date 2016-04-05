package surveyor.scommon.actions;

import java.io.File;

import common.source.ExcelUtility;
import common.source.Log;
import common.source.TestContext;

public class BaseActions implements IActions {
	private static final String DATA_FOLDER = "data";
	private static final String TEST_DATA_XLSX = "TestCaseData.xlsx";

	protected static final String ARG_DATA_ROW_ID = "dataRowID";
	protected static final String ARG_DATA = "data";

	protected ExcelUtility excelUtility = null;
	
	public BaseActions() {
		this.setExcelFile(TEST_DATA_XLSX);
	}
	
	protected void setExcelFile(String excelFileName) {
		String testDataExcelPath = TestContext.INSTANCE.getExecutionPath() + DATA_FOLDER + File.separator + excelFileName;
		try {
			excelUtility = new ExcelUtility();
			excelUtility.setExcelFile(testDataExcelPath);
		} catch (Exception e) {
			Log.error(e.toString());
		}
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
