package surveyor.scommon.actions.data;

import common.source.ExcelUtility;
import common.source.Log;
import surveyor.scommon.actions.ActionArguments;
import surveyor.scommon.actions.data.LocationDataReader.LocationDataRow;
import surveyor.scommon.source.SurveyorConstants.MinAmplitudeType;
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
	public static final int Excel_TestData__Col_SurveyMinAmp = 16;
	public static final int Excel_TestData__Col_RankingMinAmp = 17;
	public static final int Excel_TestData__Col_FilterPS = 18;
	public static final int Excel_TestData__Col_Top10PS = 19;
	public static final int Excel_TestData__Col_Top25PS = 20;
	public static final int Excel_TestData__Col_Top50PS = 21;
	public static final int Excel_TestData__Col_DbScanRadius = 22;
	public static final int Excel_TestData__Col_MinClusterSize = 23;
	public static final int Excel_TestData__Col_MaxClusterScale = 24;
	public static final int Excel_TestData__Col_ExpansionPower = 25;
	public static final int Excel_TestData__Col_InflationPower = 26;
	public static final int Excel_TestData__Col_Percentile = 27;

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
		public String surMinAmp;
		public String rankingMinAmp;
		public String psFilter;
		public String top10PS;
		public String top25PS;
		public String top50PS;
		public String dbScanRd;
		public String minClusterSz;
		public String maxClusterScale;
		public String expansionPower;
		public String inflationPower;
		public String percentile;

		public LocationDataRow(String rowID, String name, String latitude,
				String longitude, String standardMinAmplitude,
				String operatorMinAmplitude, String rapidResponseMinAmplitude,
				String assessmentMinAmplitude, String eQMinAmplitude,
				String isotopicIdentityNoLowerBound,
				String isotopicIdentityYesLowerBound,
				String isotopicIdentityYesUpperBound,
				String isotopicIdentityNoUpperBound, String ethMethRatioMin,
				String ethMethRatioMax, String customerDataRowID,
				String surMinAmp, String rankingMinAmp, String psFilter,
				String top10PS, String top25PS, String top50PS,
				String dbScanRd, String minClusterSz, String maxClusterScale,
				String expansionPower, String inflationPower, String percentile) {
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
			this.surMinAmp = surMinAmp;
			this.rankingMinAmp = rankingMinAmp;
			this.psFilter = psFilter;
			this.top10PS = top10PS;
			this.top25PS = top25PS;
			this.top50PS = top50PS;
			this.dbScanRd = dbScanRd;
			this.minClusterSz = minClusterSz;
			this.maxClusterScale = maxClusterScale;
			this.expansionPower = expansionPower;
			this.inflationPower = inflationPower;
			this.percentile = percentile;
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

	public Float getMinAmpForLocation(Integer dataRowID, MinAmplitudeType minAmplitudeType) throws Exception {
		Float minAmp = 0.0F;
		LocationDataRow locationDataRow = this.getDataRow(dataRowID);
		if (minAmplitudeType == MinAmplitudeType.Survey_Standard) {
			minAmp = Float.valueOf(locationDataRow.standardMinAmplitude);
		} else if (minAmplitudeType == MinAmplitudeType.Survey_RapidResponse) {
			minAmp = Float.valueOf(locationDataRow.rapidResponseMinAmplitude);
		} else if (minAmplitudeType == MinAmplitudeType.Survey_Operator) {
			minAmp = Float.valueOf(locationDataRow.operatorMinAmplitude);
		} else if (minAmplitudeType == MinAmplitudeType.Survey_Assessment) {
			minAmp = Float.valueOf(locationDataRow.assessmentMinAmplitude);
		} else if (minAmplitudeType == MinAmplitudeType.Survey_EQ) {
			minAmp = Float.valueOf(locationDataRow.eQMinAmplitude);
		} else if (minAmplitudeType == MinAmplitudeType.Survey_Analytics_Survey) {
			minAmp = Float.valueOf(locationDataRow.surMinAmp);
		} else if (minAmplitudeType == MinAmplitudeType.Survey_Analytics_Ranking) {
			minAmp = Float.valueOf(locationDataRow.rankingMinAmp);
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
		String surMinAmp = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_SurveyMinAmp, TESTDATA_SHEET_NAME);
		String rankingMinAmp = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_RankingMinAmp, TESTDATA_SHEET_NAME);
		String psFilter = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_FilterPS, TESTDATA_SHEET_NAME);
		String top10PS = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_Top10PS, TESTDATA_SHEET_NAME);
		String top25PS = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_Top25PS, TESTDATA_SHEET_NAME);
		String top50PS = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_Top50PS, TESTDATA_SHEET_NAME);
		String dbScanRd = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_DbScanRadius, TESTDATA_SHEET_NAME);
		String minClusterSize = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_MinClusterSize, TESTDATA_SHEET_NAME);
		String maxClusterScale = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_MaxClusterScale, TESTDATA_SHEET_NAME);
		String expansionPower = excelUtility.getIntegerCellData(dataRowID, Excel_TestData__Col_ExpansionPower, TESTDATA_SHEET_NAME);
		String inflationPower = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_InflationPower, TESTDATA_SHEET_NAME);
		String percentile = excelUtility.getNumericCellData(dataRowID, Excel_TestData__Col_Percentile, TESTDATA_SHEET_NAME);

		Log.info(String.format("Found data row: rowID=[%s], name=[%s], latitude=[%s], longitude=[%s], standardMinAmplitude=[%s], "
				+ "operatorMinAmplitude=[%s], rapidResponseMinAmplitude=[%s], assessmentMinAmplitude=[%s], eQMinAmplitude=[%s], "
				+ "isotopicIdentityNoLowerBound=[%s], isotopicIdentityYesLowerBound=[%s], isotopicIdentityYesUpperBound=[%s], "
				+ "isotopicIdentityNoUpperBound=[%s], ethMethRatioMin=[%s], ethMethRatioMax=[%s], customerDataRowID=[%s], "
				+ "surMinAmplitude=[%s], rankingMinAmplitude=[%s], psFilter=[%s], top10PS=[%s], top25PS=[%s], top50PS=[%s], "
				+ "dbScanRd=[%s], minClusterSize=[%s], maxClusterScale=[%s], expansionPower=[%s], inflationPower=[%s], percentile=[%s]",
				rowID, name, latitude, longitude, standardMinAmplitude, operatorMinAmplitude, rapidResponseMinAmplitude,
				assessmentMinAmplitude, eQMinAmplitude, isotopicIdentityNoLowerBound, isotopicIdentityYesLowerBound,
				isotopicIdentityYesUpperBound, isotopicIdentityNoUpperBound, ethMethRatioMin, ethMethRatioMax, customerDataRowID, surMinAmp,
				rankingMinAmp, psFilter, top10PS, top25PS, top50PS, dbScanRd, minClusterSize, maxClusterScale, expansionPower, inflationPower, percentile));

		return new LocationDataRow(rowID, name, latitude, longitude, standardMinAmplitude, operatorMinAmplitude, rapidResponseMinAmplitude,
				assessmentMinAmplitude, eQMinAmplitude, isotopicIdentityNoLowerBound, isotopicIdentityYesLowerBound, isotopicIdentityYesUpperBound,
				isotopicIdentityNoUpperBound, ethMethRatioMin, ethMethRatioMax, customerDataRowID, surMinAmp, rankingMinAmp, psFilter, top10PS, top25PS,
				top50PS, dbScanRd, minClusterSize, maxClusterScale, expansionPower, inflationPower, percentile);
	}
}
