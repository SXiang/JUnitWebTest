package surveyor.scommon.actions.data;

import common.source.ExcelUtility;
import common.source.Log;

public class ReportOptViewLayersBoundaryDataReader extends BaseDataReader {

	public ReportOptViewLayersBoundaryDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	public static final String TESTDATA_SHEET_NAME = "Report Opt View Layers Boundary";
	 
	public static final int Excel_TestData__Col_RowID = 0;
	public static final int Excel_TestData__Col_BoundaryID = 1;
	public static final int Excel_TestData__Col_BoundaryName = 2;
	public static final int Excel_TestData__Col_CustomerRowID = 3;
	public static final int Excel_TestData__Col_Environment = 4;
 
	public class ReportOptViewLayersBoundaryDataRow {
		public String rowID;
		public String boundaryID;
		public String boundaryName;
		public String customerRowID;
		public String environment;
 
		public ReportOptViewLayersBoundaryDataRow(String rowID, String boundaryID, String boundaryName, 
				String customerRowID, String environment) {
			this.rowID = rowID;
			this.boundaryID = boundaryID;
			this.boundaryName = boundaryName;
			this.customerRowID = customerRowID;
			this.environment = environment;
		}
	}	
 
	private ReportOptViewLayersBoundaryDataRow dataRow = null;
 
	public ReportOptViewLayersBoundaryDataRow getDataRow() {
		return dataRow;
	}
 
	public void setDataRow(ReportOptViewLayersBoundaryDataRow dataRow) {
		this.dataRow = dataRow;
	}
 
	public ReportOptViewLayersBoundaryDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_RowID, TESTDATA_SHEET_NAME);
		String boundaryID = excelUtility.getCellData(dataRowID, Excel_TestData__Col_BoundaryID, TESTDATA_SHEET_NAME);
		String boundaryName = excelUtility.getCellData(dataRowID, Excel_TestData__Col_BoundaryName, TESTDATA_SHEET_NAME);
		String customerRowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_CustomerRowID, TESTDATA_SHEET_NAME);
		String environment = excelUtility.getCellData(dataRowID, Excel_TestData__Col_Environment, TESTDATA_SHEET_NAME);
		
		Log.info(String.format("Found data row: rowID=[%s], boundaryID=[%s], boundaryName=[%s], customerRowID=[%s], "
				+ "environment=[%s]", rowID, boundaryID, boundaryName, customerRowID, environment));
		
		return new ReportOptViewLayersBoundaryDataRow(rowID, boundaryID, boundaryName, customerRowID, environment);
	}
}
