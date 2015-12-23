package surveyor.scommon.actions.data;

import common.source.ExcelUtility;

public class DriverViewDataReader extends BaseDataReader {

	public DriverViewDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	private static final String TESTDATA_SHEET_NAME = "Driver View Test Data";
	 
	public static final int Excel_TestData_Report_Col_RowID = 0;
	public static final int Excel_TestData_Report_Col_SurveyTag = 1;
	public static final int Excel_TestData_Report_Col_SurveyTime = 2;
	public static final int Excel_TestData_Report_Col_SolarRadiation = 3;
	public static final int Excel_TestData_Report_Col_Wind = 4;
	public static final int Excel_TestData_Report_Col_CloudCover = 5;
	public static final int Excel_TestData_Report_Col_SurveyType = 6;
	public static final int Excel_TestData_Report_Col_ReplayScriptDB3File = 7;
	public static final int Excel_TestData_Report_Col_ReplayScriptDefnFile = 8;
 
	public class DriverViewDataRow {
		public String rowID;
		public String surveyTag;
		public String surveyTime;
		public String solarRadiation;
		public String wind;
		public String cloudCover;
		public String surveyType;
		public String replayScriptDB3File;
		public String replayScriptDefnFile;
 
		public DriverViewDataRow(String rowID, String surveyTag, String surveyTime, String solarRadiation, String wind, String cloudCover, String surveyType, String replayScriptDB3File, String replayScriptDefnFile) {
			this.rowID = rowID;
			this.surveyTag = surveyTag;
			this.surveyTime = surveyTime;
			this.solarRadiation = solarRadiation;
			this.wind = wind;
			this.cloudCover = cloudCover;
			this.surveyType = surveyType;
			this.replayScriptDB3File = replayScriptDB3File;
			this.replayScriptDefnFile = replayScriptDefnFile;
		}
	}	
 
	private DriverViewDataRow dataRow = null;
 
	public DriverViewDataRow getDataRow() {
		return dataRow;
	}
 
	public void setDataRow(DriverViewDataRow dataRow) {
		this.dataRow = dataRow;
	}
 
	public DriverViewDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData_Report_Col_RowID, TESTDATA_SHEET_NAME);
		String surveyTag = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_SurveyTag, TESTDATA_SHEET_NAME);
		String surveyTime = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_SurveyTime, TESTDATA_SHEET_NAME);
		String solarRadiation = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_SolarRadiation, TESTDATA_SHEET_NAME);
		String wind = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_Wind, TESTDATA_SHEET_NAME);
		String cloudCover = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_CloudCover, TESTDATA_SHEET_NAME);
		String surveyType = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_SurveyType, TESTDATA_SHEET_NAME);
		String replayScriptDB3File = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_ReplayScriptDB3File, TESTDATA_SHEET_NAME);
		String replayScriptDefnFile = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_ReplayScriptDefnFile, TESTDATA_SHEET_NAME);
		
		System.out.println(String.format("Found data row: rowID=[%s], surveyTag=[%s], surveyTime=[%s], solarRadiation=[%s], wind=[%s], cloudCover=[%s], surveyType=[%s], replayScriptDB3File=[%s], replayScriptDefnFile=[%s]", rowID, surveyTag, surveyTime, solarRadiation, wind, cloudCover, surveyType, replayScriptDB3File, replayScriptDefnFile));
		
		return new DriverViewDataRow(rowID, surveyTag, surveyTime, solarRadiation, wind, cloudCover, surveyType, replayScriptDB3File, replayScriptDefnFile);
	}
}
