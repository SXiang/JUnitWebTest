package surveyor.scommon.actions.data;

import common.source.ExcelUtility;
import common.source.Log;
import surveyor.scommon.actions.ActionArguments;

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
	public static final int Excel_TestData_Report_Col_MinAmplitude = 7;
 
	public class DriverViewDataRow {
		public String rowID;
		public String surveyTag;
		public String surveyTime;
		public String solarRadiation;
		public String wind;
		public String cloudCover;
		public String surveyType;
		public String minAmplitude;
 
		public DriverViewDataRow(String rowID, String surveyTag, String surveyTime, String solarRadiation, 
				String wind, String cloudCover, String surveyType, String minAmplitude) {
			this.rowID = rowID;
			this.surveyTag = surveyTag;
			this.surveyTime = surveyTime;
			this.solarRadiation = solarRadiation;
			this.wind = wind;
			this.cloudCover = cloudCover;
			this.surveyType = surveyType;
			this.minAmplitude = minAmplitude;
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
		surveyTag = ActionArguments.evaluateArgForFunction(surveyTag);
		String surveyTime = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_SurveyTime, TESTDATA_SHEET_NAME);
		String solarRadiation = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_SolarRadiation, TESTDATA_SHEET_NAME);
		String wind = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_Wind, TESTDATA_SHEET_NAME);
		String cloudCover = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_CloudCover, TESTDATA_SHEET_NAME);
		String surveyType = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_SurveyType, TESTDATA_SHEET_NAME);
		String minAmplitude = excelUtility.getNumericCellData(dataRowID, Excel_TestData_Report_Col_MinAmplitude, TESTDATA_SHEET_NAME);
		
		Log.info(String.format("Found data row: rowID=[%s], surveyTag=[%s], surveyTime=[%s], solarRadiation=[%s], wind=[%s], cloudCover=[%s]"
				+ ", surveyType=[%s], minAmplitude=[%s]", rowID, surveyTag, surveyTime, solarRadiation, wind, cloudCover, surveyType, minAmplitude));
		
		return new DriverViewDataRow(rowID, surveyTag, surveyTime, solarRadiation, wind, cloudCover, surveyType, minAmplitude);
	}
}
