package surveyor.scommon.actions.data;

import common.source.ExcelUtility;
import common.source.Log;
import surveyor.scommon.actions.ActionArguments;
import surveyor.scommon.actions.data.LocationDataReader.LocationDataRow;
import surveyor.scommon.source.SurveyorConstants.SurveyModeType;

public class LocationDataReader extends BaseDataReader {

	public LocationDataReader(ExcelUtility excelUtility) {
		super(excelUtility);
	}

	private static final String TESTDATA_SHEET_NAME = "Locations";
	 
	public static final int Excel_TestData__Col_RowID = 0;
	public static final int Excel_TestData__Col_Name = 1;
	public static final int Excel_TestData__Col_Latitude = 2;
	public static final int Excel_TestData__Col_Longitude = 3;
	public static final int Excel_TestData__Col_StandardMinAmplitude = 4;
	public static final int Excel_TestData__Col_OperatorMinAmplitude = 5;
	public static final int Excel_TestData__Col_RapidResponseMinAmplitude = 6;
	public static final int Excel_TestData__Col_AssessmentMinAmplitude = 7;
	public static final int Excel_TestData__Col_EQMinAmplitude = 8;
	public static final int Excel_TestData__Col_IsotopicIdentityNoLowerBound = 9;
	public static final int Excel_TestData__Col_IsotopicIdentityYesLowerBound = 10;
	public static final int Excel_TestData__Col_IsotopicIdentityYesUpperBound = 11;
	public static final int Excel_TestData__Col_IsotopicIdentityNoUpperBound = 12;
	public static final int Excel_TestData__Col_EthMethRatioMin = 13;
	public static final int Excel_TestData__Col_EthMethRatioMax = 14;
	public static final int Excel_TestData__Col_CustomerDataRowID = 15;
 
	public class LocationDataRow {
		public String rowID;
		public String name;
		public String latitude;
		public String longitude;
		public String standardMinAmplitude;
		public String operatorMinAmplitude;
		public String rapidResponseMinAmplitude;
		public String assessmentMinAmplitude;
		public String eQMinAmplitude;
		public String isotopicIdentityNoLowerBound;
		public String isotopicIdentityYesLowerBound;
		public String isotopicIdentityYesUpperBound;
		public String isotopicIdentityNoUpperBound;
		public String ethMethRatioMin;
		public String ethMethRatioMax;
		public String customerDataRowID;
 
		public LocationDataRow(String rowID, String name, String latitude, String longitude, String standardMinAmplitude, 
				String operatorMinAmplitude, String rapidResponseMinAmplitude, String assessmentMinAmplitude, String eQMinAmplitude, 
				String isotopicIdentityNoLowerBound, String isotopicIdentityYesLowerBound, String isotopicIdentityYesUpperBound, 
				String isotopicIdentityNoUpperBound, String ethMethRatioMin, String ethMethRatioMax, String customerDataRowID) {
			this.rowID = rowID;
			this.name = name;
			this.latitude = latitude;
			this.longitude = longitude;
			this.standardMinAmplitude = standardMinAmplitude;
			this.operatorMinAmplitude = operatorMinAmplitude;
			this.rapidResponseMinAmplitude = rapidResponseMinAmplitude;
			this.assessmentMinAmplitude = assessmentMinAmplitude;
			this.eQMinAmplitude = eQMinAmplitude;
			this.isotopicIdentityNoLowerBound = isotopicIdentityNoLowerBound;
			this.isotopicIdentityYesLowerBound = isotopicIdentityYesLowerBound;
			this.isotopicIdentityYesUpperBound = isotopicIdentityYesUpperBound;
			this.isotopicIdentityNoUpperBound = isotopicIdentityNoUpperBound;
			this.ethMethRatioMin = ethMethRatioMin;
			this.ethMethRatioMax = ethMethRatioMax;
			this.customerDataRowID = customerDataRowID;
		}
	}	
 
	private LocationDataRow dataRow = null;
 
	public LocationDataRow getDataRow() {
		return dataRow;
	}
	
	public Integer getRowCount() {
		return this.getRowCount(TESTDATA_SHEET_NAME);
	}
 
	public void setDataRow(LocationDataRow dataRow) {
		this.dataRow = dataRow;
	}
 
	public LocationDataRow getLocationForCustomer(Integer customerRowID) throws Exception {
		for (int idx = 1; idx < this.getRowCount(); idx++) {
			LocationDataRow locationDataRow = this.getDataRow(idx);
			if (customerRowID == Integer.valueOf(locationDataRow.customerDataRowID)) {
				return locationDataRow;
			}
		}
		return null;
	}
	
	public Float getMinAmpForLocation(Integer dataRowID, SurveyModeType surveyModeType) throws Exception {
		Float minAmp = 0.0F;
		LocationDataRow locationDataRow = this.getDataRow(dataRowID);
		if (surveyModeType.equals("Standard")) {
			minAmp = Float.valueOf(locationDataRow.standardMinAmplitude);
		} else if (surveyModeType.equals("RapidResponse")) {
			minAmp = Float.valueOf(locationDataRow.rapidResponseMinAmplitude);
		} else if (surveyModeType.equals("Operator")) {
			minAmp = Float.valueOf(locationDataRow.operatorMinAmplitude);
		} else if (surveyModeType.equals("Assessment")) {
			minAmp = Float.valueOf(locationDataRow.assessmentMinAmplitude);
		} else if (surveyModeType.equals("EQ")) {
			minAmp = Float.valueOf(locationDataRow.eQMinAmplitude);
		}
		return minAmp;
	}

	public LocationDataRow getDataRow(Integer dataRowID) throws Exception {
		String rowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_RowID, TESTDATA_SHEET_NAME);
		String name = excelUtility.getCellData(dataRowID, Excel_TestData__Col_Name, TESTDATA_SHEET_NAME);
		name = ActionArguments.evaluateArgForFunction(name);
		String latitude = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_Latitude, TESTDATA_SHEET_NAME);
		String longitude = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_Longitude, TESTDATA_SHEET_NAME);
		String standardMinAmplitude = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_StandardMinAmplitude, TESTDATA_SHEET_NAME);
		String operatorMinAmplitude = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_OperatorMinAmplitude, TESTDATA_SHEET_NAME);
		String rapidResponseMinAmplitude = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_RapidResponseMinAmplitude, TESTDATA_SHEET_NAME);
		String assessmentMinAmplitude = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_AssessmentMinAmplitude, TESTDATA_SHEET_NAME);
		String eQMinAmplitude = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_EQMinAmplitude, TESTDATA_SHEET_NAME);
		String isotopicIdentityNoLowerBound = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_IsotopicIdentityNoLowerBound, TESTDATA_SHEET_NAME);
		String isotopicIdentityYesLowerBound = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_IsotopicIdentityYesLowerBound, TESTDATA_SHEET_NAME);
		String isotopicIdentityYesUpperBound = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_IsotopicIdentityYesUpperBound, TESTDATA_SHEET_NAME);
		String isotopicIdentityNoUpperBound = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_IsotopicIdentityNoUpperBound, TESTDATA_SHEET_NAME);
		String ethMethRatioMin = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_EthMethRatioMin, TESTDATA_SHEET_NAME);
		String ethMethRatioMax = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_EthMethRatioMax, TESTDATA_SHEET_NAME);
		String customerDataRowID = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_CustomerDataRowID, TESTDATA_SHEET_NAME);
		
		Log.info(String.format("Found data row: rowID=[%s], name=[%s], latitude=[%s], longitude=[%s], standardMinAmplitude=[%s], "
				+ "operatorMinAmplitude=[%s], rapidResponseMinAmplitude=[%s], assessmentMinAmplitude=[%s], eQMinAmplitude=[%s], "
				+ "isotopicIdentityNoLowerBound=[%s], isotopicIdentityYesLowerBound=[%s], isotopicIdentityYesUpperBound=[%s], "
				+ "isotopicIdentityNoUpperBound=[%s], ethMethRatioMin=[%s], ethMethRatioMax=[%s], customerDataRowID=[%s]", 
				rowID, name, latitude, longitude, standardMinAmplitude, operatorMinAmplitude, rapidResponseMinAmplitude, 
				assessmentMinAmplitude, eQMinAmplitude, isotopicIdentityNoLowerBound, isotopicIdentityYesLowerBound, 
				isotopicIdentityYesUpperBound, isotopicIdentityNoUpperBound, ethMethRatioMin, ethMethRatioMax, customerDataRowID));
		
		return new LocationDataRow(rowID, name, latitude, longitude, standardMinAmplitude, operatorMinAmplitude, rapidResponseMinAmplitude, 
				assessmentMinAmplitude, eQMinAmplitude, isotopicIdentityNoLowerBound, isotopicIdentityYesLowerBound, isotopicIdentityYesUpperBound, 
				isotopicIdentityNoUpperBound, ethMethRatioMin, ethMethRatioMax, customerDataRowID);
	}
}
