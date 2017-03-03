package surveyor.scommon.actions.data;

import java.util.Map;
import java.util.function.Supplier;

import common.source.BaseHelper;
import common.source.ExcelUtility;
import surveyor.scommon.actions.ActionArguments;
import surveyor.scommon.actions.data.ReportsCommonDataReader.ReportsCommonDataRow;

public class ReportsCommonDataReader extends ReportsBaseDataReader {

	public ReportsCommonDataReader(ExcelUtility excelUtility, Supplier<String> sheetNm, Supplier<Map<String, Integer>> columnIdxMap) {
		super(excelUtility, sheetNm, columnIdxMap);
	}

	public class ReportsCommonDataRow extends ReportsBaseDataRow{
		public String exclusionRadius;
		public String customBoundaryNELat;
		public String customBoundaryNELong;
		public String customBoundarySWLat;
		public String customBoundarySWLong;
		public String customerBoundaryType;
		public String customerBoundaryName;
		public String opacityFOV;
		public String pDFImageOutputWidth;
		public String pDFImageOutputHeight;
		public String reportViewRowIDs;
		public String reportOptViewLayerRowID;
		public String reportOptTabularPDFContentRowID;

		public ReportsCommonDataRow(String rowID, String tCID, String title, String customerRowID, String timezone,  String reportSurveyRowIDs) {
			super(rowID, tCID, title, customerRowID, timezone, reportSurveyRowIDs);
		}
		
		public ReportsCommonDataRow(String rowID, String tCID, String title, String customerRowID, String timezone, String exclusionRadius,
				String customBoundaryNELat, String customBoundaryNELong, String customBoundarySWLat,
				String customBoundarySWLong, String customerBoundaryType, String customerBoundaryName, String opacityFOV,
				String pDFImageOutputWidth, String pDFImageOutputHeight, String reportViewRowIDs, String reportOptViewLayerRowID,
				String reportOptTabularPDFContentRowID, String reportSurveyRowIDs) {
			super(rowID, tCID, title, customerRowID, timezone, reportSurveyRowIDs);
			this.exclusionRadius = exclusionRadius;
			this.customBoundaryNELat = customBoundaryNELat;
			this.customBoundaryNELong = customBoundaryNELong;
			this.customBoundarySWLat = customBoundarySWLat;
			this.customBoundarySWLong = customBoundarySWLong;
			this.customerBoundaryType = customerBoundaryType;
			this.customerBoundaryName = customerBoundaryName;
			this.opacityFOV = opacityFOV;
			this.pDFImageOutputWidth = pDFImageOutputWidth;
			this.pDFImageOutputHeight = pDFImageOutputHeight;
			this.reportViewRowIDs = reportViewRowIDs;
			this.reportOptViewLayerRowID = reportOptViewLayerRowID;
			this.reportOptTabularPDFContentRowID = reportOptTabularPDFContentRowID;
		}
	}

	public ReportsCommonDataRow getDataRow(Integer dataRowID) throws Exception {
		ReportsBaseDataRow reportsDataRow = super.getDataRow(dataRowID);
		
		String exclusionRadius = excelUtility.getIntegerCellData(dataRowID, columnIndexMap.get("Col_ExclusionRadius"), sheetName);
		String customBoundaryNELat = excelUtility.getNumericCellData(dataRowID, columnIndexMap.get("Col_CustomBoundaryNELat"), sheetName);
		String customBoundaryNELong = excelUtility.getNumericCellData(dataRowID, columnIndexMap.get("Col_CustomBoundaryNELong"), sheetName);
		String customBoundarySWLat = excelUtility.getNumericCellData(dataRowID, columnIndexMap.get("Col_CustomBoundarySWLat"), sheetName);
		String customBoundarySWLong = excelUtility.getNumericCellData(dataRowID, columnIndexMap.get("Col_CustomBoundarySWLong"), sheetName);
		String customerBoundaryType = excelUtility.getCellData(dataRowID, columnIndexMap.get("Col_CustomerBoundaryType"), sheetName);
		String customerBoundaryName = excelUtility.getCellData(dataRowID, columnIndexMap.get("Col_CustomerBoundaryName"), sheetName);
		String opacityFOV = excelUtility.getNumericCellData(dataRowID, columnIndexMap.get("Col_OpacityFOV"), sheetName);
		String pDFImageOutputWidth = excelUtility.getNumericCellData(dataRowID, columnIndexMap.get("Col_PDFImageOutputWidth"), sheetName);
		String pDFImageOutputHeight = excelUtility.getNumericCellData(dataRowID, columnIndexMap.get("Col_PDFImageOutputHeight"), sheetName);
		String reportViewRowIDs = excelUtility.getCellData(dataRowID, columnIndexMap.get("Col_ReportViewRowIDs"), sheetName);
		String reportOptViewLayerRowID = excelUtility.getIntegerCellData(dataRowID, columnIndexMap.get("Col_ReportOptViewLayerRowID"), sheetName);
		String reportOptTabularPDFContentRowID = excelUtility.getIntegerCellData(dataRowID, columnIndexMap.get("Col_ReportOptTabularPDFContentRowID"), sheetName);

		return new ReportsCommonDataRow(reportsDataRow.rowID, reportsDataRow.tCID, reportsDataRow.title, reportsDataRow.customerRowID, reportsDataRow.timezone, exclusionRadius, customBoundaryNELat,
				customBoundaryNELong, customBoundarySWLat, customBoundarySWLong, customerBoundaryType, customerBoundaryName, opacityFOV,
				pDFImageOutputWidth, pDFImageOutputHeight, reportViewRowIDs, reportOptViewLayerRowID, reportOptTabularPDFContentRowID, reportsDataRow.reportSurveyRowIDs);
	}
}