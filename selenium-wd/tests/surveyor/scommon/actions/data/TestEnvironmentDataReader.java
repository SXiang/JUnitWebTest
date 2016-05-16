package surveyor.scommon.actions.data;

import common.source.ExcelUtility;
import common.source.Log;

public class TestEnvironmentDataReader extends BaseDataReader {
	public TestEnvironmentDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	private static final String TESTDATA_SHEET_NAME = "Test Environment Data";
	 
	public static final int Excel_TestData__Col_RowID = 0;
	public static final int Excel_TestData__Col_AnalyzerSerialNumber = 1;
	public static final int Excel_TestData__Col_AnalyzerSharedKey = 2;
	public static final int Excel_TestData__Col_ReplayScriptDB3File = 3;
	public static final int Excel_TestData__Col_ReplayScriptDefnFile = 4;
 
	public class TestEnvironmentDataRow {
		public String rowID;
		public String analyzerSerialNumber;
		public String analyzerSharedKey;
		public String replayScriptDB3File;
		public String replayScriptDefnFile;
 
		public TestEnvironmentDataRow(String rowID, String analyzerSerialNumber, String analyzerSharedKey, String replayScriptDB3File, String replayScriptDefnFile) {
			this.rowID = rowID;
			this.analyzerSerialNumber = analyzerSerialNumber;
			this.analyzerSharedKey = analyzerSharedKey;
			this.replayScriptDB3File = replayScriptDB3File;
			this.replayScriptDefnFile = replayScriptDefnFile;
		}
	}	
 
	private TestEnvironmentDataRow dataRow = null;
 
	public TestEnvironmentDataRow getDataRow() {
		return dataRow;
	}
 
	public Integer getRowCount() {
		return this.getRowCount(TESTDATA_SHEET_NAME);
	}
	
	public void setDataRow(TestEnvironmentDataRow dataRow) {
		this.dataRow = dataRow;
	}
 
	public TestEnvironmentDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_RowID, TESTDATA_SHEET_NAME);
		String analyzerSerialNumber = excelUtility.getCellData(dataRowID, Excel_TestData__Col_AnalyzerSerialNumber, TESTDATA_SHEET_NAME);
		String analyzerSharedKey = excelUtility.getCellData(dataRowID, Excel_TestData__Col_AnalyzerSharedKey, TESTDATA_SHEET_NAME);
		String replayScriptDB3File = excelUtility.getCellData(dataRowID, Excel_TestData__Col_ReplayScriptDB3File, TESTDATA_SHEET_NAME);
		String replayScriptDefnFile = excelUtility.getCellData(dataRowID, Excel_TestData__Col_ReplayScriptDefnFile, TESTDATA_SHEET_NAME);
		
		Log.info(String.format("Found data row: rowID=[%s], analyzerSerialNumber=[%s], analyzerSharedKey=[%s], replayScriptDB3File=[%s], replayScriptDefnFile=[%s]", rowID, analyzerSerialNumber, analyzerSharedKey, replayScriptDB3File, replayScriptDefnFile));
		
		return new TestEnvironmentDataRow(rowID, analyzerSerialNumber, analyzerSharedKey, replayScriptDB3File, replayScriptDefnFile);
	}
}
