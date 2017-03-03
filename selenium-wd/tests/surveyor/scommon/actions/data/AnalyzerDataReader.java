package surveyor.scommon.actions.data;

import common.source.ExcelUtility;
import common.source.Log;
import surveyor.scommon.actions.ActionArguments;

public class AnalyzerDataReader extends BaseDataReader {

	public AnalyzerDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	private static final String TESTDATA_SHEET_NAME = "Analyzers";

	public static final int Excel_TestData__Col_RowID = 0;
	public static final int Excel_TestData__Col_SerialNumber = 1;
	public static final int Excel_TestData__Col_SharedKey = 2;
	public static final int Excel_TestData__Col_LocationRowID = 3;
	public static final int Excel_TestData__Col_SurveyorRowID = 4;
	public static final int Excel_TestData__Col_Type = 5;

	public class AnalyzerDataRow {
		public String rowID;
		public String serialNumber;
		public String sharedKey;
		public String locationRowID;
		public String surveyorRowID;
		public String type;
		public Boolean isSerialNumberFromPool;    // This variable stores whether the serialNumber has been fetched from pool.

		public AnalyzerDataRow(String rowID, String serialNumber, String sharedKey, String locationRowID, String surveyorRowID, String type,
				Boolean serialNumberFromPool) {
			this.rowID = rowID;
			this.serialNumber = serialNumber;
			this.sharedKey = sharedKey;
			this.locationRowID = locationRowID;
			this.surveyorRowID = surveyorRowID;
			this.type = type;
			this.isSerialNumberFromPool = serialNumberFromPool;
		}
	}

	private AnalyzerDataRow dataRow = null;

	public AnalyzerDataRow getDataRow() {
		return dataRow;
	}

	public Integer getRowCount() {
		return this.getRowCount(TESTDATA_SHEET_NAME);
	}

	public void setDataRow(AnalyzerDataRow dataRow) {
		this.dataRow = dataRow;
	}

	public Integer getAnalyzerDataRowIDForSurveyor(Integer surveyorDataRowID) throws Exception {
		Integer rowID = -1;
		Integer rowCount = getRowCount();
		for (int i = 1; i <= rowCount; i++) {
			AnalyzerDataRow dataRow = getDataRow(i);
			if (dataRow.surveyorRowID.equals(String.valueOf(surveyorDataRowID))) {
				rowID = i;
				break;
			}
		}

		return rowID;
	}

	public AnalyzerDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_RowID, TESTDATA_SHEET_NAME);
		String serialNumber = excelUtility.getCellData(dataRowID, Excel_TestData__Col_SerialNumber, TESTDATA_SHEET_NAME);
		Boolean serialNumberFromPool = false;
		StringBuilder evaluatedSerialNumValue = new StringBuilder();
		if (ActionArguments.evaluateArgForFunction(serialNumber, evaluatedSerialNumValue)) {
			serialNumberFromPool = true;
			serialNumber = evaluatedSerialNumValue.toString();
		}
		String sharedKey = excelUtility.getCellData(dataRowID, Excel_TestData__Col_SharedKey, TESTDATA_SHEET_NAME);
		sharedKey = ActionArguments.evaluateArgForFunction(sharedKey);
		String locationRowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_LocationRowID, TESTDATA_SHEET_NAME);
		String surveyorRowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_SurveyorRowID, TESTDATA_SHEET_NAME);
		String type = excelUtility.getCellData(dataRowID, Excel_TestData__Col_Type, TESTDATA_SHEET_NAME);

		Log.info(String.format("Found data row: rowID=[%s], serialNumber=[%s], sharedKey=[%s], locationRowID=[%s], surveyorRowID=[%s], "
				+ "type=[%s]", rowID, serialNumber, sharedKey, locationRowID, surveyorRowID, type));

		return new AnalyzerDataRow(rowID, serialNumber, sharedKey, locationRowID, surveyorRowID, type, serialNumberFromPool);
	}
}