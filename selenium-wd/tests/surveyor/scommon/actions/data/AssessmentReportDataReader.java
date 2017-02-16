package surveyor.scommon.actions.data;

import java.util.HashMap;
import java.util.Map;

import common.source.ExcelUtility;
import common.source.Log;

public class AssessmentReportDataReader extends ReportsCommonDataReader {

	private static final String TESTDATA_SHEET_NAME = "Assessment Report Test Data";

	public static final int Excel_TestData__Col_RowID = 0;
	public static final int Excel_TestData__Col_TCID = 1;
	public static final int Excel_TestData__Col_Title = 2;
	public static final int Excel_TestData__Col_CustomerRowID = 3;
	public static final int Excel_TestData__Col_Timezone = 4;
	public static final int Excel_TestData__Col_ExclusionRadius = 5;
	public static final int Excel_TestData__Col_CustomBoundaryNELat = 6;
	public static final int Excel_TestData__Col_CustomBoundaryNELong = 7;
	public static final int Excel_TestData__Col_CustomBoundarySWLat = 8;
	public static final int Excel_TestData__Col_CustomBoundarySWLong = 9;
	public static final int Excel_TestData__Col_CustomerBoundaryType = 10;
	public static final int Excel_TestData__Col_CustomerBoundaryName = 11;
	public static final int Excel_TestData__Col_OpacityFOV = 12;
	public static final int Excel_TestData__Col_PDFImageOutputWidth = 13;
	public static final int Excel_TestData__Col_PDFImageOutputHeight = 14;
	public static final int Excel_TestData__Col_ReportViewRowIDs = 15;
	public static final int Excel_TestData__Col_ReportOptViewLayerRowID = 16;
	public static final int Excel_TestData__Col_ReportOptTabularPDFContentRowID = 17;
	public static final int Excel_TestData__Col_ReportSurveyRowIDs = 18;

	public AssessmentReportDataReader(ExcelUtility excelUtility) {
		super(excelUtility, () -> TESTDATA_SHEET_NAME, () -> buildColumnIndexMap());
	}

	public class AssessmentReportsDataRow extends ReportsCommonDataRow {
		public AssessmentReportsDataRow(String rowID, String tCID, String title, String customerRowID, String timezone, String exclusionRadius,
				String customBoundaryNELat, String customBoundaryNELong, String customBoundarySWLat,
				String customBoundarySWLong, String customerBoundaryType, String customerBoundaryName, String opacityFOV,
				String pDFImageOutputWidth, String pDFImageOutputHeight, String reportViewRowIDs, String reportOptViewLayerRowID,
				String reportOptTabularPDFContentRowID, String reportSurveyRowIDs) {
			super(rowID, tCID, title, customerRowID, timezone, exclusionRadius,customBoundaryNELat, customBoundaryNELong,
					customBoundarySWLat, customBoundarySWLong, customerBoundaryType, customerBoundaryName, opacityFOV, pDFImageOutputWidth,
					pDFImageOutputHeight, reportViewRowIDs, reportOptViewLayerRowID, reportOptTabularPDFContentRowID, reportSurveyRowIDs);
		}
	}

	private static Map<String, Integer> buildColumnIndexMap() {
		Map<String, Integer> columnIdxMap = new HashMap<String, Integer>();
		columnIdxMap.put("Col_RowID", Excel_TestData__Col_RowID);
		columnIdxMap.put("Col_TCID", Excel_TestData__Col_TCID);
		columnIdxMap.put("Col_Title", Excel_TestData__Col_Title);
		columnIdxMap.put("Col_CustomerRowID", Excel_TestData__Col_CustomerRowID);
		columnIdxMap.put("Col_Timezone", Excel_TestData__Col_Timezone);
		columnIdxMap.put("Col_ExclusionRadius", Excel_TestData__Col_ExclusionRadius);
		columnIdxMap.put("Col_CustomBoundaryNELat", Excel_TestData__Col_CustomBoundaryNELat);
		columnIdxMap.put("Col_CustomBoundaryNELong", Excel_TestData__Col_CustomBoundaryNELong);
		columnIdxMap.put("Col_CustomBoundarySWLat", Excel_TestData__Col_CustomBoundarySWLat);
		columnIdxMap.put("Col_CustomBoundarySWLong", Excel_TestData__Col_CustomBoundarySWLong);
		columnIdxMap.put("Col_CustomerBoundaryType", Excel_TestData__Col_CustomerBoundaryType);
		columnIdxMap.put("Col_CustomerBoundaryName", Excel_TestData__Col_CustomerBoundaryName);
		columnIdxMap.put("Col_OpacityFOV", Excel_TestData__Col_OpacityFOV);
		columnIdxMap.put("Col_PDFImageOutputWidth", Excel_TestData__Col_PDFImageOutputWidth);
		columnIdxMap.put("Col_PDFImageOutputHeight", Excel_TestData__Col_PDFImageOutputHeight);
		columnIdxMap.put("Col_ReportViewRowIDs", Excel_TestData__Col_ReportViewRowIDs);
		columnIdxMap.put("Col_ReportOptViewLayerRowID", Excel_TestData__Col_ReportOptViewLayerRowID);
		columnIdxMap.put("Col_ReportOptTabularPDFContentRowID", Excel_TestData__Col_ReportOptTabularPDFContentRowID);
		columnIdxMap.put("Col_ReportSurveyRowIDs", Excel_TestData__Col_ReportSurveyRowIDs);
		return columnIdxMap;
	}

	public AssessmentReportsDataRow getDataRow(Integer dataRowID) throws Exception {
		ReportsCommonDataRow reportsDataRow = super.getDataRow(dataRowID);

		Log.info(String.format("Found data row: rowID=[%s], tCID=[%s], title=[%s], customerRowID=[%s], timezone=[%s], exclusionRadius=[%s], "
				+ "customBoundaryNELat=[%s], customBoundaryNELong=[%s], customBoundarySWLat=[%s], customBoundarySWLong=[%s], "
				+ "customerBoundaryType=[%s], customerBoundaryName=[%s], opacityFOV=[%s], pDFImageOutputWidth=[%s], "
				+ "pDFImageOutputHeight=[%s], reportViewRowIDs=[%s], reportOptViewLayerRowID=[%s], reportOptTabularPDFContentRowID=[%s], "
				+ "reportSurveyRowIDs=[%s]", reportsDataRow.rowID, reportsDataRow.tCID, reportsDataRow.title, reportsDataRow.customerRowID, reportsDataRow.timezone,
				reportsDataRow.exclusionRadius, reportsDataRow.customBoundaryNELat, reportsDataRow.customBoundaryNELong,
				reportsDataRow.customBoundarySWLat, reportsDataRow.customBoundarySWLong, reportsDataRow.customerBoundaryType, reportsDataRow.customerBoundaryName,
				reportsDataRow.opacityFOV, reportsDataRow.pDFImageOutputWidth, reportsDataRow.pDFImageOutputHeight, reportsDataRow.reportViewRowIDs,
				reportsDataRow.reportOptViewLayerRowID, reportsDataRow.reportOptTabularPDFContentRowID, reportsDataRow.reportSurveyRowIDs));

		return new AssessmentReportsDataRow(reportsDataRow.rowID, reportsDataRow.tCID, reportsDataRow.title, reportsDataRow.customerRowID, reportsDataRow.timezone,
				reportsDataRow.exclusionRadius, reportsDataRow.customBoundaryNELat, reportsDataRow.customBoundaryNELong,
				reportsDataRow.customBoundarySWLat, reportsDataRow.customBoundarySWLong, reportsDataRow.customerBoundaryType, reportsDataRow.customerBoundaryName,
				reportsDataRow.opacityFOV, reportsDataRow.pDFImageOutputWidth, reportsDataRow.pDFImageOutputHeight, reportsDataRow.reportViewRowIDs,
				reportsDataRow.reportOptViewLayerRowID, reportsDataRow.reportOptTabularPDFContentRowID, reportsDataRow.reportSurveyRowIDs);
	}
}
