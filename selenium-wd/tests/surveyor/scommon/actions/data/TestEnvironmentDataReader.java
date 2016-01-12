package surveyor.scommon.actions.data;

import common.source.ExcelUtility;

public class TestEnvironmentDataReader extends BaseDataReader {
	public TestEnvironmentDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	private static final String TESTDATA_SHEET_NAME = "Test Environment Data";
	 
	public static final int Excel_TestData__Col_AnalyzerSerialNumber = 0;
	public static final int Excel_TestData__Col_AnalyzerSharedKey = 1;
	public static final int Excel_TestData__Col_ReplayScriptDB3File = 2;
	public static final int Excel_TestData__Col_ReplayScriptDefnFile = 3;
 
	public class TestEnvironmentDataRow {
		public String analyzerSerialNumber;
		public String analyzerSharedKey;
		public String replayScriptDB3File;
		public String replayScriptDefnFile;
 
		public TestEnvironmentDataRow(String analyzerSerialNumber, String analyzerSharedKey, String replayScriptDB3File, String replayScriptDefnFile) {
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
 
	public void setDataRow(TestEnvironmentDataRow dataRow) {
		this.dataRow = dataRow;
	}
 
	public TestEnvironmentDataRow getDataRow(Integer dataRowID) throws Exception {
		String analyzerSerialNumber = excelUtility.getCellData(dataRowID, Excel_TestData__Col_AnalyzerSerialNumber, TESTDATA_SHEET_NAME);
		String analyzerSharedKey = excelUtility.getCellData(dataRowID, Excel_TestData__Col_AnalyzerSharedKey, TESTDATA_SHEET_NAME);
		String replayScriptDB3File = excelUtility.getCellData(dataRowID, Excel_TestData__Col_ReplayScriptDB3File, TESTDATA_SHEET_NAME);
		String replayScriptDefnFile = excelUtility.getCellData(dataRowID, Excel_TestData__Col_ReplayScriptDefnFile, TESTDATA_SHEET_NAME);
		
		System.out.println(String.format("Found data row: analyzerSerialNumber=[%s], analyzerSharedKey=[%s], replayScriptDB3File=[%s], replayScriptDefnFile=[%s]", analyzerSerialNumber, analyzerSharedKey, replayScriptDB3File, replayScriptDefnFile));
		
		return new TestEnvironmentDataRow(analyzerSerialNumber, analyzerSharedKey, replayScriptDB3File, replayScriptDefnFile);
	}
}
