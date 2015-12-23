package surveyor.scommon.actions.data;

import common.source.ExcelUtility;

public class ReportOptViewLayersDataReader extends BaseDataReader {

	public ReportOptViewLayersDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	private static final String TESTDATA_SHEET_NAME = "Report Opt View Layers";
	 
	public static final int Excel_TestData_Report_Col_RowID = 0;
	public static final int Excel_TestData_Report_Col_AssetCopper = 1;
	public static final int Excel_TestData_Report_Col_AssetUnprotectedSteel = 2;
	public static final int Excel_TestData_Report_Col_AssetProtectedSteel = 3;
	public static final int Excel_TestData_Report_Col_AssetCastIron = 4;
	public static final int Excel_TestData_Report_Col_AssetOtherPlastic = 5;
	public static final int Excel_TestData_Report_Col_AssetPEPlastic = 6;
	public static final int Excel_TestData_Report_Col_BoundaryDistrictPlat = 7;
	public static final int Excel_TestData_Report_Col_BoundaryDistrict = 8;
 
	public class ReportOptViewLayersDataRow {
		public String rowID;
		public String assetCopper;
		public String assetUnprotectedSteel;
		public String assetProtectedSteel;
		public String assetCastIron;
		public String assetOtherPlastic;
		public String assetPEPlastic;
		public String boundaryDistrictPlat;
		public String boundaryDistrict;
 
		public ReportOptViewLayersDataRow(String rowID, String assetCopper, String assetUnprotectedSteel, String assetProtectedSteel, String assetCastIron, String assetOtherPlastic, String assetPEPlastic, String boundaryDistrictPlat, String boundaryDistrict) {
			this.rowID = rowID;
			this.assetCopper = assetCopper;
			this.assetUnprotectedSteel = assetUnprotectedSteel;
			this.assetProtectedSteel = assetProtectedSteel;
			this.assetCastIron = assetCastIron;
			this.assetOtherPlastic = assetOtherPlastic;
			this.assetPEPlastic = assetPEPlastic;
			this.boundaryDistrictPlat = boundaryDistrictPlat;
			this.boundaryDistrict = boundaryDistrict;
		}
	}	
 
	private ReportOptViewLayersDataRow dataRow = null;
 
	public ReportOptViewLayersDataRow getDataRow() {
		return dataRow;
	}
 
	public void setDataRow(ReportOptViewLayersDataRow dataRow) {
		this.dataRow = dataRow;
	}
 
	public ReportOptViewLayersDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData_Report_Col_RowID, TESTDATA_SHEET_NAME);
		String assetCopper = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_AssetCopper, TESTDATA_SHEET_NAME);
		String assetUnprotectedSteel = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_AssetUnprotectedSteel, TESTDATA_SHEET_NAME);
		String assetProtectedSteel = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_AssetProtectedSteel, TESTDATA_SHEET_NAME);
		String assetCastIron = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_AssetCastIron, TESTDATA_SHEET_NAME);
		String assetOtherPlastic = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_AssetOtherPlastic, TESTDATA_SHEET_NAME);
		String assetPEPlastic = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_AssetPEPlastic, TESTDATA_SHEET_NAME);
		String boundaryDistrictPlat = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_BoundaryDistrictPlat, TESTDATA_SHEET_NAME);
		String boundaryDistrict = excelUtility.getBooleanCellData(dataRowID, Excel_TestData_Report_Col_BoundaryDistrict, TESTDATA_SHEET_NAME);
		
		System.out.println(String.format("Found data row: rowID=[%s], assetCopper=[%s], assetUnprotectedSteel=[%s], assetProtectedSteel=[%s], assetCastIron=[%s], assetOtherPlastic=[%s], assetPEPlastic=[%s], boundaryDistrictPlat=[%s], boundaryDistrict=[%s]", rowID, assetCopper, assetUnprotectedSteel, assetProtectedSteel, assetCastIron, assetOtherPlastic, assetPEPlastic, boundaryDistrictPlat, boundaryDistrict));
		
		return new ReportOptViewLayersDataRow(rowID, assetCopper, assetUnprotectedSteel, assetProtectedSteel, assetCastIron, assetOtherPlastic, assetPEPlastic, boundaryDistrictPlat, boundaryDistrict);
	}
}
