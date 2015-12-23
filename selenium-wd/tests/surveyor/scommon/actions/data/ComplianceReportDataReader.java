package surveyor.scommon.actions.data;

import common.source.ExcelUtility;
import surveyor.scommon.actions.ActionArguments;

public class ComplianceReportDataReader extends BaseDataReader {

	public ComplianceReportDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	private static final String TESTDATA_SHEET_NAME = "Compliance Report Test Data";
	 
	public static final int Excel_TestData_Report_Col_RowID = 0;
	public static final int Excel_TestData_Report_Col_Title = 1;
	public static final int Excel_TestData_Report_Col_CustomerRowID = 2;
	public static final int Excel_TestData_Report_Col_Timezone = 3;
	public static final int Excel_TestData_Report_Col_ExclusionRadius = 4;
	public static final int Excel_TestData_Report_Col_ReportMode = 5;
	public static final int Excel_TestData_Report_Col_CustomBoundaryNELat = 6;
	public static final int Excel_TestData_Report_Col_CustomBoundaryNELong = 7;
	public static final int Excel_TestData_Report_Col_CustomBoundarySWLat = 8;
	public static final int Excel_TestData_Report_Col_CustomBoundarySWLong = 9;
	public static final int Excel_TestData_Report_Col_CustomerBoundaryType = 10;
	public static final int Excel_TestData_Report_Col_CustomerBoundaryName = 11;
	public static final int Excel_TestData_Report_Col_SurveySurveyor = 12;
	public static final int Excel_TestData_Report_Col_SurveyUsername = 13;
	public static final int Excel_TestData_Report_Col_SurveyTag = 14;
	public static final int Excel_TestData_Report_Col_SurveyStartDate = 15;
	public static final int Excel_TestData_Report_Col_SurveyEndDate = 16;
	public static final int Excel_TestData_Report_Col_SurveyModeFilter = 17;
	public static final int Excel_TestData_Report_Col_SurveyGeoFilterON = 18;
	public static final int Excel_TestData_Report_Col_NumberofSurveystoInclude = 19;
	public static final int Excel_TestData_Report_Col_OpacityFOV = 20;
	public static final int Excel_TestData_Report_Col_OpacityLISA = 21;
	public static final int Excel_TestData_Report_Col_PDFImageOutputWidth = 22;
	public static final int Excel_TestData_Report_Col_PDFImageOutputHeight = 23;
	public static final int Excel_TestData_Report_Col_ReportViewRowIDs = 24;
	public static final int Excel_TestData_Report_Col_ReportOptViewLayerRowID = 25;
	public static final int Excel_TestData_Report_Col_ReportOptTabularPDFContentRowID = 26;
 
	public class ComplianceReportDataRow {
		public String rowID;
		public String title;
		public String customerRowID;
		public String timezone;
		public String exclusionRadius;
		public String reportMode;
		public String customBoundaryNELat;
		public String customBoundaryNELong;
		public String customBoundarySWLat;
		public String customBoundarySWLong;
		public String customerBoundaryType;
		public String customerBoundaryName;
		public String surveySurveyor;
		public String surveyUsername;
		public String surveyTag;
		public String surveyStartDate;
		public String surveyEndDate;
		public String surveyModeFilter;
		public String surveyGeoFilterON;
		public String numberofSurveystoInclude;
		public String opacityFOV;
		public String opacityLISA;
		public String pDFImageOutputWidth;
		public String pDFImageOutputHeight;
		public String reportViewRowIDs;
		public String reportOptViewLayerRowID;
		public String reportOptTabularPDFContentRowID;
 
		public ComplianceReportDataRow(String rowID, String title, String customerRowID, String timezone, String exclusionRadius, String reportMode, String customBoundaryNELat, String customBoundaryNELong, String customBoundarySWLat, String customBoundarySWLong, String customerBoundaryType, String customerBoundaryName, String surveySurveyor, String surveyUsername, String surveyTag, String surveyStartDate, String surveyEndDate, String surveyModeFilter, String surveyGeoFilterON, String numberofSurveystoInclude, String opacityFOV, String opacityLISA, String pDFImageOutputWidth, String pDFImageOutputHeight, String reportViewRowIDs, String reportOptViewLayerRowID, String reportOptTabularPDFContentRowID) {
			this.rowID = rowID;
			this.title = title;
			this.customerRowID = customerRowID;
			this.timezone = timezone;
			this.exclusionRadius = exclusionRadius;
			this.reportMode = reportMode;
			this.customBoundaryNELat = customBoundaryNELat;
			this.customBoundaryNELong = customBoundaryNELong;
			this.customBoundarySWLat = customBoundarySWLat;
			this.customBoundarySWLong = customBoundarySWLong;
			this.customerBoundaryType = customerBoundaryType;
			this.customerBoundaryName = customerBoundaryName;
			this.surveySurveyor = surveySurveyor;
			this.surveyUsername = surveyUsername;
			this.surveyTag = surveyTag;
			this.surveyStartDate = surveyStartDate;
			this.surveyEndDate = surveyEndDate;
			this.surveyModeFilter = surveyModeFilter;
			this.surveyGeoFilterON = surveyGeoFilterON;
			this.numberofSurveystoInclude = numberofSurveystoInclude;
			this.opacityFOV = opacityFOV;
			this.opacityLISA = opacityLISA;
			this.pDFImageOutputWidth = pDFImageOutputWidth;
			this.pDFImageOutputHeight = pDFImageOutputHeight;
			this.reportViewRowIDs = reportViewRowIDs;
			this.reportOptViewLayerRowID = reportOptViewLayerRowID;
			this.reportOptTabularPDFContentRowID = reportOptTabularPDFContentRowID;
		}
	}	
 
	private ComplianceReportDataRow dataRow = null;
 
	public ComplianceReportDataRow getDataRow() {
		return dataRow;
	}
 
	public void setDataRow(ComplianceReportDataRow dataRow) {
		this.dataRow = dataRow;
	}
 
	public ComplianceReportDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData_Report_Col_RowID, TESTDATA_SHEET_NAME);
		String title = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_Title, TESTDATA_SHEET_NAME);
		title = ActionArguments.evaluateArgForFunction(title);
		String customerRowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData_Report_Col_CustomerRowID, TESTDATA_SHEET_NAME);
		String timezone = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_Timezone, TESTDATA_SHEET_NAME);
		String exclusionRadius = excelUtility.getNumericCellData(dataRowID, Excel_TestData_Report_Col_ExclusionRadius, TESTDATA_SHEET_NAME);
		String reportMode = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_ReportMode, TESTDATA_SHEET_NAME);
		String customBoundaryNELat = excelUtility.getNumericCellData(dataRowID, Excel_TestData_Report_Col_CustomBoundaryNELat, TESTDATA_SHEET_NAME);
		String customBoundaryNELong = excelUtility.getNumericCellData(dataRowID, Excel_TestData_Report_Col_CustomBoundaryNELong, TESTDATA_SHEET_NAME);
		String customBoundarySWLat = excelUtility.getNumericCellData(dataRowID, Excel_TestData_Report_Col_CustomBoundarySWLat, TESTDATA_SHEET_NAME);
		String customBoundarySWLong = excelUtility.getNumericCellData(dataRowID, Excel_TestData_Report_Col_CustomBoundarySWLong, TESTDATA_SHEET_NAME);
		String customerBoundaryType = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_CustomerBoundaryType, TESTDATA_SHEET_NAME);
		String customerBoundaryName = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_CustomerBoundaryName, TESTDATA_SHEET_NAME);
		String surveySurveyor = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_SurveySurveyor, TESTDATA_SHEET_NAME);
		String surveyUsername = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_SurveyUsername, TESTDATA_SHEET_NAME);
		String surveyTag = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_SurveyTag, TESTDATA_SHEET_NAME);
		String surveyStartDate = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_SurveyStartDate, TESTDATA_SHEET_NAME);
		String surveyEndDate = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_SurveyEndDate, TESTDATA_SHEET_NAME);
		String surveyModeFilter = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_SurveyModeFilter, TESTDATA_SHEET_NAME);
		String surveyGeoFilterON = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_SurveyGeoFilterON, TESTDATA_SHEET_NAME);
		String numberofSurveystoInclude = excelUtility.getIntegerCellData(dataRowID, Excel_TestData_Report_Col_NumberofSurveystoInclude, TESTDATA_SHEET_NAME);
		String opacityFOV = excelUtility.getNumericCellData(dataRowID, Excel_TestData_Report_Col_OpacityFOV, TESTDATA_SHEET_NAME);
		String opacityLISA = excelUtility.getNumericCellData(dataRowID, Excel_TestData_Report_Col_OpacityLISA, TESTDATA_SHEET_NAME);
		String pDFImageOutputWidth = excelUtility.getNumericCellData(dataRowID, Excel_TestData_Report_Col_PDFImageOutputWidth, TESTDATA_SHEET_NAME);
		String pDFImageOutputHeight = excelUtility.getNumericCellData(dataRowID, Excel_TestData_Report_Col_PDFImageOutputHeight, TESTDATA_SHEET_NAME);
		String reportViewRowIDs = excelUtility.getCellData(dataRowID, Excel_TestData_Report_Col_ReportViewRowIDs, TESTDATA_SHEET_NAME);
		String reportOptViewLayerRowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData_Report_Col_ReportOptViewLayerRowID, TESTDATA_SHEET_NAME);
		String reportOptTabularPDFContentRowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData_Report_Col_ReportOptTabularPDFContentRowID, TESTDATA_SHEET_NAME);
		
		System.out.println(String.format("Found data row: rowID=[%s], title=[%s], customerRowID=[%s], timezone=[%s], exclusionRadius=[%s], reportMode=[%s], customBoundaryNELat=[%s], customBoundaryNELong=[%s], customBoundarySWLat=[%s], customBoundarySWLong=[%s], customerBoundaryType=[%s], customerBoundaryName=[%s], surveySurveyor=[%s], surveyUsername=[%s], surveyTag=[%s], surveyStartDate=[%s], surveyEndDate=[%s], surveyModeFilter=[%s], surveyGeoFilterON=[%s], numberofSurveystoInclude=[%s], opacityFOV=[%s], opacityLISA=[%s], pDFImageOutputWidth=[%s], pDFImageOutputHeight=[%s], reportViewRowIDs=[%s], reportOptViewLayerRowID=[%s], reportOptTabularPDFContentRowID=[%s]", rowID, title, customerRowID, timezone, exclusionRadius, reportMode, customBoundaryNELat, customBoundaryNELong, customBoundarySWLat, customBoundarySWLong, customerBoundaryType, customerBoundaryName, surveySurveyor, surveyUsername, surveyTag, surveyStartDate, surveyEndDate, surveyModeFilter, surveyGeoFilterON, numberofSurveystoInclude, opacityFOV, opacityLISA, pDFImageOutputWidth, pDFImageOutputHeight, reportViewRowIDs, reportOptViewLayerRowID, reportOptTabularPDFContentRowID));
		
		return new ComplianceReportDataRow(rowID, title, customerRowID, timezone, exclusionRadius, reportMode, customBoundaryNELat, customBoundaryNELong, customBoundarySWLat, customBoundarySWLong, customerBoundaryType, customerBoundaryName, surveySurveyor, surveyUsername, surveyTag, surveyStartDate, surveyEndDate, surveyModeFilter, surveyGeoFilterON, numberofSurveystoInclude, opacityFOV, opacityLISA, pDFImageOutputWidth, pDFImageOutputHeight, reportViewRowIDs, reportOptViewLayerRowID, reportOptTabularPDFContentRowID);
	}
}
