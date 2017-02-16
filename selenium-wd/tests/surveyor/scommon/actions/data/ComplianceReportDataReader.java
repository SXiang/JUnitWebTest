package surveyor.scommon.actions.data;

import java.util.HashMap;
import java.util.Map;

import common.source.ExcelUtility;
import common.source.Log;

public class ComplianceReportDataReader extends ReportsCommonDataReader {

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
	public static final int Excel_TestData__Col_SearchAreaPreference = 22;

	public ComplianceReportDataReader(ExcelUtility excelUtility) {
		super(excelUtility, () -> TESTDATA_SHEET_NAME, () -> buildColumnIndexMap());
	}

	public class ComplianceReportsDataRow extends ReportsCommonDataRow {
		public String reportMode;
		public String minAmplitude;
		public String opacityLISA;
		public String searchAreaPreference;

		public ComplianceReportsDataRow(String rowID, String tCID, String title, String customerRowID, String timezone, String exclusionRadius,
				String reportMode, String minAmplitude, String customBoundaryNELat, String customBoundaryNELong, String customBoundarySWLat,
				String customBoundarySWLong, String customerBoundaryType, String customerBoundaryName, String opacityFOV, String opacityLISA,
				String pDFImageOutputWidth, String pDFImageOutputHeight, String reportViewRowIDs, String reportOptViewLayerRowID,
				String reportOptTabularPDFContentRowID, String reportSurveyRowIDs, String searchAreaPreference) {
			super(rowID, tCID, title, customerRowID, timezone, exclusionRadius,customBoundaryNELat, customBoundaryNELong,
					customBoundarySWLat, customBoundarySWLong, customerBoundaryType, customerBoundaryName, opacityFOV, pDFImageOutputWidth,
					pDFImageOutputHeight, reportViewRowIDs, reportOptViewLayerRowID, reportOptTabularPDFContentRowID, reportSurveyRowIDs);
			this.reportMode = reportMode;
			this.minAmplitude = minAmplitude;
			this.opacityLISA = opacityLISA;
			this.searchAreaPreference = searchAreaPreference;
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
		columnIdxMap.put("Col_ReportMode", Excel_TestData__Col_ReportMode);
		columnIdxMap.put("Col_MinAmplitude", Excel_TestData__Col_MinAmplitude);
		columnIdxMap.put("Col_CustomBoundaryNELat", Excel_TestData__Col_CustomBoundaryNELat);
		columnIdxMap.put("Col_CustomBoundaryNELong", Excel_TestData__Col_CustomBoundaryNELong);
		columnIdxMap.put("Col_CustomBoundarySWLat", Excel_TestData__Col_CustomBoundarySWLat);
		columnIdxMap.put("Col_CustomBoundarySWLong", Excel_TestData__Col_CustomBoundarySWLong);
		columnIdxMap.put("Col_CustomerBoundaryType", Excel_TestData__Col_CustomerBoundaryType);
		columnIdxMap.put("Col_CustomerBoundaryName", Excel_TestData__Col_CustomerBoundaryName);
		columnIdxMap.put("Col_OpacityFOV", Excel_TestData__Col_OpacityFOV);
		columnIdxMap.put("Col_OpacityLISA", Excel_TestData__Col_OpacityLISA);
		columnIdxMap.put("Col_PDFImageOutputWidth", Excel_TestData__Col_PDFImageOutputWidth);
		columnIdxMap.put("Col_PDFImageOutputHeight", Excel_TestData__Col_PDFImageOutputHeight);
		columnIdxMap.put("Col_ReportViewRowIDs", Excel_TestData__Col_ReportViewRowIDs);
		columnIdxMap.put("Col_ReportOptViewLayerRowID", Excel_TestData__Col_ReportOptViewLayerRowID);
		columnIdxMap.put("Col_ReportOptTabularPDFContentRowID", Excel_TestData__Col_ReportOptTabularPDFContentRowID);
		columnIdxMap.put("Col_ReportSurveyRowIDs", Excel_TestData__Col_ReportSurveyRowIDs);
		columnIdxMap.put("Col_SearchAreaPreference", Excel_TestData__Col_SearchAreaPreference);
		return columnIdxMap;
	}

	public void setDataRow(ComplianceReportsDataRow dataRow) {
		super.setDataRow(dataRow);
	}

	public ComplianceReportsDataRow getDataRow(Integer dataRowID) throws Exception {
		ReportsCommonDataRow reportsDataRow = super.getDataRow(dataRowID);
		String reportMode = excelUtility.getCellData(dataRowID, Excel_TestData__Col_ReportMode, TESTDATA_SHEET_NAME);
		String minAmplitude = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_MinAmplitude, TESTDATA_SHEET_NAME);
		String opacityLISA = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_OpacityLISA, TESTDATA_SHEET_NAME);
		String searchAreaPreference = excelUtility.getCellData(dataRowID, Excel_TestData__Col_SearchAreaPreference, TESTDATA_SHEET_NAME);

		Log.info(String.format("Found data row: rowID=[%s], tCID=[%s], title=[%s], customerRowID=[%s], timezone=[%s], exclusionRadius=[%s], "
				+ "reportMode=[%s], minAmplitude=[%s], customBoundaryNELat=[%s], customBoundaryNELong=[%s], customBoundarySWLat=[%s], customBoundarySWLong=[%s], "
				+ "customerBoundaryType=[%s], customerBoundaryName=[%s], opacityFOV=[%s], opacityLISA=[%s], pDFImageOutputWidth=[%s], "
				+ "pDFImageOutputHeight=[%s], reportViewRowIDs=[%s], reportOptViewLayerRowID=[%s], reportOptTabularPDFContentRowID=[%s], "
				+ "reportSurveyRowIDs=[%s],searchAreaPreference=[%s]", reportsDataRow.rowID, reportsDataRow.tCID, reportsDataRow.title, reportsDataRow.customerRowID, reportsDataRow.timezone,
				reportsDataRow.exclusionRadius, reportMode, minAmplitude, reportsDataRow.customBoundaryNELat, reportsDataRow.customBoundaryNELong,
				reportsDataRow.customBoundarySWLat, reportsDataRow.customBoundarySWLong, reportsDataRow.customerBoundaryType, reportsDataRow.customerBoundaryName,
				reportsDataRow.opacityFOV, opacityLISA, reportsDataRow.pDFImageOutputWidth, reportsDataRow.pDFImageOutputHeight, reportsDataRow.reportViewRowIDs,
				reportsDataRow.reportOptViewLayerRowID, reportsDataRow.reportOptTabularPDFContentRowID, reportsDataRow.reportSurveyRowIDs,
				searchAreaPreference));

		return new ComplianceReportsDataRow(reportsDataRow.rowID, reportsDataRow.tCID, reportsDataRow.title, reportsDataRow.customerRowID, reportsDataRow.timezone,
				reportsDataRow.exclusionRadius, reportMode, minAmplitude, reportsDataRow.customBoundaryNELat, reportsDataRow.customBoundaryNELong,
				reportsDataRow.customBoundarySWLat, reportsDataRow.customBoundarySWLong, reportsDataRow.customerBoundaryType, reportsDataRow.customerBoundaryName,
				reportsDataRow.opacityFOV, opacityLISA, reportsDataRow.pDFImageOutputWidth, reportsDataRow.pDFImageOutputHeight, reportsDataRow.reportViewRowIDs,
				reportsDataRow.reportOptViewLayerRowID, reportsDataRow.reportOptTabularPDFContentRowID, reportsDataRow.reportSurveyRowIDs,
				searchAreaPreference);
	}
}