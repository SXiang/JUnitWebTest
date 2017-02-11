package surveyor.scommon.actions.data;

import java.util.Map;
import java.util.function.Supplier;

import common.source.BaseHelper;
import common.source.ExcelUtility;
import surveyor.scommon.actions.ActionArguments;

public class ReportsCommonDataReader extends BaseDataReader {
	protected String sheetName;
	protected Map<String, Integer> columnIndexMap;

	public ReportsCommonDataReader(ExcelUtility excelUtility, Supplier<String> sheetNm, Supplier<Map<String, Integer>> columnIdxMap) {
		super(excelUtility);
		this.sheetName = sheetNm.get();
		this.columnIndexMap = columnIdxMap.get();
	}

	public class ReportsCommonDataRow {
		public String rowID;
		public String tCID;
		public String title;
		public String customerRowID;
		public String timezone;
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
		public String reportSurveyRowIDs;

		public ReportsCommonDataRow(String rowID, String tCID, String title, String customerRowID, String timezone, String exclusionRadius,
				String customBoundaryNELat, String customBoundaryNELong, String customBoundarySWLat,
				String customBoundarySWLong, String customerBoundaryType, String customerBoundaryName, String opacityFOV,
				String pDFImageOutputWidth, String pDFImageOutputHeight, String reportViewRowIDs, String reportOptViewLayerRowID,
				String reportOptTabularPDFContentRowID, String reportSurveyRowIDs) {
			this.rowID = rowID;
			this.tCID = tCID;
			this.title = title;
			this.customerRowID = customerRowID;
			this.timezone = timezone;
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
			this.reportSurveyRowIDs = reportSurveyRowIDs;
		}
	}
	private ReportsCommonDataRow dataRow = null;

	public ReportsCommonDataRow getDataRow() {
		return dataRow;
	}

	public Integer getRowCount() throws Exception {
		return this.getRowCount(sheetName);
	}

	public void setDataRow(ReportsCommonDataRow dataRow) {
		this.dataRow = dataRow;
	}

	public ReportsCommonDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getIntegerCellData(dataRowID, columnIndexMap.get("Col_RowID"), sheetName);
		String tCID = excelUtility.getCellData(dataRowID, columnIndexMap.get("Col_TCID"), sheetName);
		String title = excelUtility.getCellData(dataRowID, columnIndexMap.get("Col_Title"), sheetName);
		title = ActionArguments.evaluateArgForFunction(title);
		if (!BaseHelper.isNullOrEmpty(tCID)) {
			title = String.format("%s-%s", tCID, title);
		}
		String customerRowID = excelUtility.getIntegerCellData(dataRowID, columnIndexMap.get("Col_CustomerRowID"), sheetName);
		String timezone = excelUtility.getCellData(dataRowID, columnIndexMap.get("Col_Timezone"), sheetName);
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
		String reportSurveyRowIDs = excelUtility.getCellData(dataRowID, columnIndexMap.get("Col_ReportSurveyRowIDs"), sheetName);

		return new ReportsCommonDataRow(rowID, tCID, title, customerRowID, timezone, exclusionRadius, customBoundaryNELat,
				customBoundaryNELong, customBoundarySWLat, customBoundarySWLong, customerBoundaryType, customerBoundaryName, opacityFOV,
				pDFImageOutputWidth, pDFImageOutputHeight, reportViewRowIDs, reportOptViewLayerRowID, reportOptTabularPDFContentRowID, reportSurveyRowIDs);
	}
}