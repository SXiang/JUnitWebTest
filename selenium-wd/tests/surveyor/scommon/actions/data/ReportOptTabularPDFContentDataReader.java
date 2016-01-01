package surveyor.scommon.actions.data;

import common.source.ExcelUtility;
import common.source.Log;

public class ReportOptTabularPDFContentDataReader extends BaseDataReader {

	public ReportOptTabularPDFContentDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	private static final String TESTDATA_SHEET_NAME = "Report Opt Tabular PDF Content";
	 
	public static final int Excel_TestData_Report_Col_RowID = 0;
	public static final int Excel_TestData_Report_Col_IndicationTable = 1;
	public static final int Excel_TestData_Report_Col_IsotopicAnalysis = 2;
	public static final int Excel_TestData_Report_Col_GapTable = 3;
	public static final int Excel_TestData_Report_Col_PercentCoverageAssets = 4;
	public static final int Excel_TestData_Report_Col_PercentCoverageReportArea = 5;
	public static final int Excel_TestData_Report_Col_PercentCoverageForecast = 6;
 
	public class ReportOptTabularPDFContentDataRow {
		public String rowID;
		public String indicationTable;
		public String isotopicAnalysis;
		public String gapTable;
		public String percentCoverageAssets;
		public String percentCoverageReportArea;
		public String percentCoverageForecast;
 
		public ReportOptTabularPDFContentDataRow(String rowID, String indicationTable, String isotopicAnalysis, String gapTable, String percentCoverageAssets, String percentCoverageReportArea, String percentCoverageForecast) {
			this.rowID = rowID;
			this.indicationTable = indicationTable;
			this.isotopicAnalysis = isotopicAnalysis;
			this.gapTable = gapTable;
			this.percentCoverageAssets = percentCoverageAssets;
			this.percentCoverageReportArea = percentCoverageReportArea;
			this.percentCoverageForecast = percentCoverageForecast;
		}
	}	
 
	private ReportOptTabularPDFContentDataRow dataRow = null;
 
	public ReportOptTabularPDFContentDataRow getDataRow() {
		return dataRow;
	}
 
	public void setDataRow(ReportOptTabularPDFContentDataRow dataRow) {
		this.dataRow = dataRow;
	}
 
	public ReportOptTabularPDFContentDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData_Report_Col_RowID, TESTDATA_SHEET_NAME);
		String indicationTable = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_IndicationTable, TESTDATA_SHEET_NAME);
		String isotopicAnalysis = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_IsotopicAnalysis, TESTDATA_SHEET_NAME);
		String gapTable = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_GapTable, TESTDATA_SHEET_NAME);
		String percentCoverageAssets = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_PercentCoverageAssets, TESTDATA_SHEET_NAME);
		String percentCoverageReportArea = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_PercentCoverageReportArea, TESTDATA_SHEET_NAME);
		String percentCoverageForecast = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_PercentCoverageForecast, TESTDATA_SHEET_NAME);
		
		Log.info(String.format("Found data row: rowID=[%s], indicationTable=[%s], isotopicAnalysis=[%s], gapTable=[%s], percentCoverageAssets=[%s], percentCoverageReportArea=[%s], percentCoverageForecast=[%s]", rowID, indicationTable, isotopicAnalysis, gapTable, percentCoverageAssets, percentCoverageReportArea, percentCoverageForecast));
		
		return new ReportOptTabularPDFContentDataRow(rowID, indicationTable, isotopicAnalysis, gapTable, percentCoverageAssets, percentCoverageReportArea, percentCoverageForecast);
	}
}
