package surveyor.scommon.actions;

import common.source.ExcelUtility;
import common.source.Log;
import common.source.TestSetup;

public class BaseActions implements IActions {

	protected static final String ARG_DATA_ROW_ID = "dataRowID";
	protected static final String ARG_DATA = "data";
	public static final String EMPTY = "";
	public static final String TRUE = "TRUE";
	public static final Integer NOTSET = -1;

	protected ExcelUtility excelUtility = null;

	public BaseActions() {
		this.setExcelUtility(TestSetup.getExcelUtility());
	}

	protected void setExcelUtility(ExcelUtility exlUtility) {
		this.excelUtility = exlUtility;
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
