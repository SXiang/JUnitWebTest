package surveyor.scommon.actions.data;

import common.source.ExcelUtility;
import common.source.Log;

public class ReportSurveyDataReader extends BaseDataReader {

	public ReportSurveyDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	private static final String TESTDATA_SHEET_NAME = "Report Survey Data";
	 
	public static final int Excel_TestData__Col_RowID = 0;
	public static final int Excel_TestData__Col_SurveySurveyor = 1;
	public static final int Excel_TestData__Col_SurveyUsername = 2;
	public static final int Excel_TestData__Col_SurveyTag = 3;
	public static final int Excel_TestData__Col_SurveyStartDate = 4;
	public static final int Excel_TestData__Col_SurveyEndDate = 5;
	public static final int Excel_TestData__Col_SurveyModeFilter = 6;
	public static final int Excel_TestData__Col_SurveyGeoFilterON = 7;
	public static final int Excel_TestData__Col_NumberofSurveystoInclude = 8;
	public static final int Excel_TestData__Col_SelectAllSurveys = 9;
	public static final int Excel_TestData__Col_AnalyzerRowID = 10;
	
	public class ReportSurveyDataRow {
		public String rowID;
		public String surveySurveyor;
		public String surveyUsername;
		public String surveyTag;
		public String surveyStartDate;
		public String surveyEndDate;
		public String surveyModeFilter;
		public String surveyGeoFilterON;
		public String numberofSurveystoInclude;
		public String selectAllSurveys;
		public String analyzerRowID;
 
		public ReportSurveyDataRow(String rowID, String surveySurveyor, String surveyUsername, String surveyTag, String surveyStartDate, 
				String surveyEndDate, String surveyModeFilter, String surveyGeoFilterON, String numberofSurveystoInclude, String selectAllSurveys, 
				String analyzerRowID) {
			this.rowID = rowID;
			this.surveySurveyor = surveySurveyor;
			this.surveyUsername = surveyUsername;
			this.surveyTag = surveyTag;
			this.surveyStartDate = surveyStartDate;
			this.surveyEndDate = surveyEndDate;
			this.surveyModeFilter = surveyModeFilter;
			this.surveyGeoFilterON = surveyGeoFilterON;
			this.numberofSurveystoInclude = numberofSurveystoInclude;
			this.selectAllSurveys = selectAllSurveys;
			this.analyzerRowID = analyzerRowID;
		}
	}	
 
	private ReportSurveyDataRow dataRow = null;
 
	public ReportSurveyDataRow getDataRow() {
		return dataRow;
	}
 
	public void setDataRow(ReportSurveyDataRow dataRow) {
		this.dataRow = dataRow;
	}
 
	public ReportSurveyDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_RowID, TESTDATA_SHEET_NAME);
		String surveySurveyor = excelUtility.getCellData(dataRowID, Excel_TestData__Col_SurveySurveyor, TESTDATA_SHEET_NAME);
		String surveyUsername = excelUtility.getCellData(dataRowID, Excel_TestData__Col_SurveyUsername, TESTDATA_SHEET_NAME);
		String surveyTag = excelUtility.getCellData(dataRowID, Excel_TestData__Col_SurveyTag, TESTDATA_SHEET_NAME);
		String surveyStartDate = excelUtility.getCellData(dataRowID, Excel_TestData__Col_SurveyStartDate, TESTDATA_SHEET_NAME);
		String surveyEndDate = excelUtility.getCellData(dataRowID, Excel_TestData__Col_SurveyEndDate, TESTDATA_SHEET_NAME);
		String surveyModeFilter = excelUtility.getCellData(dataRowID, Excel_TestData__Col_SurveyModeFilter, TESTDATA_SHEET_NAME);
		String surveyGeoFilterON = excelUtility.getBooleanCellData(dataRowID, Excel_TestData__Col_SurveyGeoFilterON, TESTDATA_SHEET_NAME);
		String numberofSurveystoInclude = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_NumberofSurveystoInclude, TESTDATA_SHEET_NAME);
		String selectAllSurveys = excelUtility.getBooleanCellData(dataRowID, Excel_TestData__Col_SelectAllSurveys, TESTDATA_SHEET_NAME);
		String analyzerRowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_AnalyzerRowID, TESTDATA_SHEET_NAME);
		
		Log.info(String.format("Found data row: rowID=[%s], surveySurveyor=[%s], surveyUsername=[%s], surveyTag=[%s], surveyStartDate=[%s], "
				+ "surveyEndDate=[%s], surveyModeFilter=[%s], surveyGeoFilterON=[%s], numberofSurveystoInclude=[%s], selectAllSurveys=[%s], "
				+ "analyzerRowID=[%s]", rowID, surveySurveyor, surveyUsername, surveyTag, surveyStartDate, surveyEndDate, surveyModeFilter, 
				surveyGeoFilterON, numberofSurveystoInclude, selectAllSurveys, analyzerRowID));
		
		return new ReportSurveyDataRow(rowID, surveySurveyor, surveyUsername, surveyTag, surveyStartDate, surveyEndDate, surveyModeFilter, 
				surveyGeoFilterON, numberofSurveystoInclude, selectAllSurveys, analyzerRowID);
	}
}
