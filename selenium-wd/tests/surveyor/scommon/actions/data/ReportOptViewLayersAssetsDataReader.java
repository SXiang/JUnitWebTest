package surveyor.scommon.actions.data;

import common.source.ExcelUtility;
import common.source.Log;

public class ReportOptViewLayersAssetsDataReader extends BaseDataReader {

	public ReportOptViewLayersAssetsDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	public static final String TESTDATA_SHEET_NAME = "Report Opt View Layers Assets";
	 
	public static final int Excel_TestData__Col_RowID = 0;
	public static final int Excel_TestData__Col_AssetID = 1;
	public static final int Excel_TestData__Col_AssetName = 2;
	public static final int Excel_TestData__Col_CustomerRowID = 3;
	public static final int Excel_TestData__Col_Environment = 4;
 
	public class ReportOptViewLayersAssetsDataRow {
		public String rowID;
		public String assetID;
		public String assetName;
		public String customerRowID;
		public String environment;
 
		public ReportOptViewLayersAssetsDataRow(String rowID, String assetID, String assetName, 
				String customerRowID, String environment) {
			this.rowID = rowID;
			this.assetID = assetID;
			this.assetName = assetName;
			this.customerRowID = customerRowID;
			this.environment = environment;
		}
	}	
 
	private ReportOptViewLayersAssetsDataRow dataRow = null;
 
	public ReportOptViewLayersAssetsDataRow getDataRow() {
		return dataRow;
	}
 
	public void setDataRow(ReportOptViewLayersAssetsDataRow dataRow) {
		this.dataRow = dataRow;
	}
	
	public Integer getRowCount() {
		return this.getRowCount(TESTDATA_SHEET_NAME);
	}
 
	public ReportOptViewLayersAssetsDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_RowID, TESTDATA_SHEET_NAME);
		String assetID = excelUtility.getCellData(dataRowID, Excel_TestData__Col_AssetID, TESTDATA_SHEET_NAME);
		String assetName = excelUtility.getCellData(dataRowID, Excel_TestData__Col_AssetName, TESTDATA_SHEET_NAME);
		String customerRowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_CustomerRowID, TESTDATA_SHEET_NAME);
		String environment = excelUtility.getCellData(dataRowID, Excel_TestData__Col_Environment, TESTDATA_SHEET_NAME);
		
		Log.info(String.format("Found data row: rowID=[%s], assetID=[%s], assetName=[%s], customerRowID=[%s], "
				+ "environment=[%s]", rowID, assetID, assetName, customerRowID, environment));
		
		return new ReportOptViewLayersAssetsDataRow(rowID, assetID, assetName, customerRowID, environment);
	}
}
