package surveyor.scommon.actions.data;

import common.source.ExcelUtility;
import common.source.Log;
import surveyor.scommon.actions.ActionArguments;

public class ComplianceReportDataReader extends BaseDataReader {

	public ComplianceReportDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	private static final String TESTDATA_SHEET_NAME = "Compliance Report Test Data";
	 
	public static final int Excel_TestData__Col_RowID = 0;
	public static final int Excel_TestData__Col_TCID = 1;
	public static final int Excel_TestData__Col_Title = 2;
	public static final int Excel_TestData__Col_CustomerRowID = 3;
	public static final int Excel_TestData__Col_Timezone = 4;
	public static final int Excel_TestData__Col_ExclusionRadius = 5;
	public static final int Excel_TestData__Col_ReportMode = 6;
	public static final int Excel_TestData__Col_MinAmplitude = 7;
	public static final int Excel_TestData__Col_CustomBoundaryNELat = 8;
	public static final int Excel_TestData__Col_CustomBoundaryNELong = 9;
	public static final int Excel_TestData__Col_CustomBoundarySWLat = 10;
	public static final int Excel_TestData__Col_CustomBoundarySWLong = 11;
	public static final int Excel_TestData__Col_CustomerBoundaryType = 12;
	public static final int Excel_TestData__Col_CustomerBoundaryName = 13;
	public static final int Excel_TestData__Col_OpacityFOV = 14;
	public static final int Excel_TestData__Col_OpacityLISA = 15;
	public static final int Excel_TestData__Col_PDFImageOutputWidth = 16;
	public static final int Excel_TestData__Col_PDFImageOutputHeight = 17;
	public static final int Excel_TestData__Col_ReportViewRowIDs = 18;
	public static final int Excel_TestData__Col_ReportOptViewLayerRowID = 19;
	public static final int Excel_TestData__Col_ReportOptTabularPDFContentRowID = 20;
	public static final int Excel_TestData__Col_ReportSurveyRowIDs = 21;
 
	public class ComplianceReportsDataRow {
		public String rowID;
		public String tCID;
		public String title;
		public String customerRowID;
		public String timezone;
		public String exclusionRadius;
		public String reportMode;
		public String minAmplitude;
		public String customBoundaryNELat;
		public String customBoundaryNELong;
		public String customBoundarySWLat;
		public String customBoundarySWLong;
		public String customerBoundaryType;
		public String customerBoundaryName;
		public String opacityFOV;
		public String opacityLISA;
		public String pDFImageOutputWidth;
		public String pDFImageOutputHeight;
		public String reportViewRowIDs;
		public String reportOptViewLayerRowID;
		public String reportOptTabularPDFContentRowID;
		public String reportSurveyRowIDs;
		public boolean useDBCache = true;
 
		public ComplianceReportsDataRow(String rowID, String tCID, String title, String customerRowID, String timezone, String exclusionRadius, 
				String reportMode, String minAmplitude, String customBoundaryNELat, String customBoundaryNELong, String customBoundarySWLat, 
				String customBoundarySWLong, String customerBoundaryType, String customerBoundaryName, String opacityFOV, String opacityLISA, 
				String pDFImageOutputWidth, String pDFImageOutputHeight, String reportViewRowIDs, String reportOptViewLayerRowID, 
				String reportOptTabularPDFContentRowID, String reportSurveyRowIDs) {
			this.rowID = rowID;
			this.tCID = tCID;
			this.title = title;
			this.customerRowID = customerRowID;
			this.timezone = timezone;
			this.exclusionRadius = exclusionRadius;
			this.reportMode = reportMode;
			this.minAmplitude = minAmplitude;
			this.customBoundaryNELat = customBoundaryNELat;
			this.customBoundaryNELong = customBoundaryNELong;
			this.customBoundarySWLat = customBoundarySWLat;
			this.customBoundarySWLong = customBoundarySWLong;
			this.customerBoundaryType = customerBoundaryType;
			this.customerBoundaryName = customerBoundaryName;
			this.opacityFOV = opacityFOV;
			this.opacityLISA = opacityLISA;
			this.pDFImageOutputWidth = pDFImageOutputWidth;
			this.pDFImageOutputHeight = pDFImageOutputHeight;
			this.reportViewRowIDs = reportViewRowIDs;
			this.reportOptViewLayerRowID = reportOptViewLayerRowID;
			this.reportOptTabularPDFContentRowID = reportOptTabularPDFContentRowID;
			this.reportSurveyRowIDs = reportSurveyRowIDs;
		}
	}	
 
	private ComplianceReportsDataRow dataRow = null;
 
	public ComplianceReportsDataRow getDataRow() {
		return dataRow;
	}
 
	public Integer getRowCount() {
		return this.getRowCount(TESTDATA_SHEET_NAME);
	}
	
	public void setDataRow(ComplianceReportsDataRow dataRow) {
		this.dataRow = dataRow;
	}
 
	public ComplianceReportsDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_RowID, TESTDATA_SHEET_NAME);
		String tCID = excelUtility.getCellData(dataRowID, Excel_TestData__Col_TCID, TESTDATA_SHEET_NAME);
		String title = excelUtility.getCellData(dataRowID, Excel_TestData__Col_Title, TESTDATA_SHEET_NAME);
		title = ActionArguments.evaluateArgForFunction(title);
		String customerRowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_CustomerRowID, TESTDATA_SHEET_NAME);
		String timezone = excelUtility.getCellData(dataRowID, Excel_TestData__Col_Timezone, TESTDATA_SHEET_NAME);
		String exclusionRadius = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_ExclusionRadius, TESTDATA_SHEET_NAME);
		String reportMode = excelUtility.getCellData(dataRowID, Excel_TestData__Col_ReportMode, TESTDATA_SHEET_NAME);
		String minAmplitude = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_MinAmplitude, TESTDATA_SHEET_NAME);
		String customBoundaryNELat = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_CustomBoundaryNELat, TESTDATA_SHEET_NAME);
		String customBoundaryNELong = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_CustomBoundaryNELong, TESTDATA_SHEET_NAME);
		String customBoundarySWLat = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_CustomBoundarySWLat, TESTDATA_SHEET_NAME);
		String customBoundarySWLong = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_CustomBoundarySWLong, TESTDATA_SHEET_NAME);
		String customerBoundaryType = excelUtility.getCellData(dataRowID, Excel_TestData__Col_CustomerBoundaryType, TESTDATA_SHEET_NAME);
		String customerBoundaryName = excelUtility.getCellData(dataRowID, Excel_TestData__Col_CustomerBoundaryName, TESTDATA_SHEET_NAME);
		String opacityFOV = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_OpacityFOV, TESTDATA_SHEET_NAME);
		String opacityLISA = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_OpacityLISA, TESTDATA_SHEET_NAME);
		String pDFImageOutputWidth = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_PDFImageOutputWidth, TESTDATA_SHEET_NAME);
		String pDFImageOutputHeight = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_PDFImageOutputHeight, TESTDATA_SHEET_NAME);
		String reportViewRowIDs = excelUtility.getCellData(dataRowID, Excel_TestData__Col_ReportViewRowIDs, TESTDATA_SHEET_NAME);
		String reportOptViewLayerRowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_ReportOptViewLayerRowID, TESTDATA_SHEET_NAME);
		String reportOptTabularPDFContentRowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_ReportOptTabularPDFContentRowID, TESTDATA_SHEET_NAME);
		String reportSurveyRowIDs = excelUtility.getCellData(dataRowID, Excel_TestData__Col_ReportSurveyRowIDs, TESTDATA_SHEET_NAME);
		
		Log.info(String.format("Found data row: rowID=[%s], tCID=[%s], title=[%s], customerRowID=[%s], timezone=[%s], exclusionRadius=[%s], "
				+ "reportMode=[%s], minAmplitude=[%s], customBoundaryNELat=[%s], customBoundaryNELong=[%s], customBoundarySWLat=[%s], customBoundarySWLong=[%s], "
				+ "customerBoundaryType=[%s], customerBoundaryName=[%s], opacityFOV=[%s], opacityLISA=[%s], pDFImageOutputWidth=[%s], "
				+ "pDFImageOutputHeight=[%s], reportViewRowIDs=[%s], reportOptViewLayerRowID=[%s], reportOptTabularPDFContentRowID=[%s], "
				+ "reportSurveyRowIDs=[%s]", rowID, tCID, title, customerRowID, timezone, exclusionRadius, reportMode, minAmplitude, customBoundaryNELat, 
				customBoundaryNELong, customBoundarySWLat, customBoundarySWLong, customerBoundaryType, customerBoundaryName, opacityFOV, opacityLISA, 
				pDFImageOutputWidth, pDFImageOutputHeight, reportViewRowIDs, reportOptViewLayerRowID, reportOptTabularPDFContentRowID, reportSurveyRowIDs));
		
		return new ComplianceReportsDataRow(rowID, tCID, title, customerRowID, timezone, exclusionRadius, reportMode, minAmplitude, customBoundaryNELat, 
				customBoundaryNELong, customBoundarySWLat, customBoundarySWLong, customerBoundaryType, customerBoundaryName, opacityFOV, opacityLISA, 
				pDFImageOutputWidth, pDFImageOutputHeight, reportViewRowIDs, reportOptViewLayerRowID, reportOptTabularPDFContentRowID, reportSurveyRowIDs);
	}
}
