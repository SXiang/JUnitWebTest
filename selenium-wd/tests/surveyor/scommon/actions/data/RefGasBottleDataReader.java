package surveyor.scommon.actions.data;

import common.source.ExcelUtility;
import common.source.Log;
import surveyor.scommon.actions.ActionArguments;

public class RefGasBottleDataReader extends BaseDataReader {

	private static final String TESTDATA_SHEET_NAME = "Reference Gas Bottle";
	 
	public static final int Excel_TestData__Col_RowID = 0;
	public static final int Excel_TestData__Col_LotNumber = 1;
	public static final int Excel_TestData__Col_IsotopicValue = 2;
	public static final int Excel_TestData__Col_EthaneMethaneRatio = 3;
	public static final int Excel_TestData__Col_CustomerRowID = 4;
	public static final int Excel_TestData__Col_LocationRowID = 5;
	public static final int Excel_TestData__Col_SurveyorRowID = 6;

	public RefGasBottleDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	public class RefGasBottleDataRow {
		public String rowID;
		public String lotNumber;
		public String isotopicValue;
		public String ethaneMethaneRatio;
		public String customerRowID;
		public String locationRowID;
		public String surveyorRowID;
 
		public RefGasBottleDataRow(String rowID, String lotNumber, String isotopicValue, String ethaneMethaneRatio, 
				String customerRowID, String locationRowID, String surveyorRowID) {
			this.rowID = rowID;
			this.lotNumber = lotNumber;
			this.isotopicValue = isotopicValue;
			this.ethaneMethaneRatio = ethaneMethaneRatio;
			this.customerRowID = customerRowID;
			this.locationRowID = locationRowID;
			this.surveyorRowID = surveyorRowID;
		}
	}	
 
	private RefGasBottleDataRow dataRow = null;
 
	public RefGasBottleDataRow getDataRow() {
		return dataRow;
	}
 
	public void setDataRow(RefGasBottleDataRow dataRow) {
		this.dataRow = dataRow;
	}
 
	public RefGasBottleDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getCellData(dataRowID, Excel_TestData__Col_RowID, TESTDATA_SHEET_NAME);
		String lotNumber = excelUtility.getCellData(dataRowID, Excel_TestData__Col_LotNumber, TESTDATA_SHEET_NAME);
		lotNumber = ActionArguments.evaluateArgForFunction(lotNumber);
		String isotopicValue = excelUtility.getCellData(dataRowID, Excel_TestData__Col_IsotopicValue, TESTDATA_SHEET_NAME);
		String ethaneMethaneRatio = excelUtility.getCellData(dataRowID, Excel_TestData__Col_EthaneMethaneRatio, TESTDATA_SHEET_NAME);
		String customerRowID = excelUtility.getCellData(dataRowID, Excel_TestData__Col_CustomerRowID, TESTDATA_SHEET_NAME);
		String locationRowID = excelUtility.getCellData(dataRowID, Excel_TestData__Col_LocationRowID, TESTDATA_SHEET_NAME);
		String surveyorRowID = excelUtility.getCellData(dataRowID, Excel_TestData__Col_SurveyorRowID, TESTDATA_SHEET_NAME);
		
		Log.info(String.format("Found data row: rowID=[%s], lotNumber=[%s], isotopicValue=[%s], ethaneMethaneRatio=[%s], customerRowID=[%s], locationRowID=[%s], surveyorRowID=[%s]", rowID, lotNumber, isotopicValue, ethaneMethaneRatio, customerRowID, locationRowID, surveyorRowID));
		
		return new RefGasBottleDataRow(rowID, lotNumber, isotopicValue, ethaneMethaneRatio, customerRowID, locationRowID, surveyorRowID);
	}
}
