package surveyor.dbseed.source;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.source.CSVUtility;
import common.source.DateUtility;
import common.source.ExceptionUtility;
import common.source.Log;
import common.source.NumberUtility;
import common.source.Redater;
import surveyor.dataaccess.source.Measurement;

public class MeasurementDbSeedBuilder extends BaseDbSeedBuilder {
	public static final String TABLE_NAME = "[dbo].[Measurement]";
	private static final String SEED_DATA_FOLDER = SURVEY_SEED_DATA_FOLDER;
	private static final String SEED_FILE_NAME = "MeasurementSeed.csv";
	private static final String INSERT_TEMPLATE = "INSERT [dbo].[Measurement] ([AnalyzerId], [EpochTime], [CreateDate], [GpsLatitude], [GpsLongitude], [GpsFit], [Shape], [InstrumentStatus], [ValveMask], [CarSpeedNorth], [CarSpeedEast], [WindSpeedNorth], [WindSpeedEast], [WindDirectionStdDev], [WeatherStationRotation], [WindSpeedLateral], [WindSpeedLongitudinal], [ChemDetect], [Species], [CH4], [CO2], [H2OPercent], [DeltaCH4], [PeripheralStatus], [AnalyzerStatus], [CavityPressure], [WarmBoxTemperature], [HotBoxTemperature], [MobileFlowRate], [AnalyzerMode], [PeakDetectorState], [C2H6], [C2H4], [AnalyzerEthaneConcentrationUncertainty]) VALUES (N'%s', %s, CAST(N'%s' AS DateTime), %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)";
	private boolean redate = false;
	private double startEpoch;
	private double endEpoch;

	public MeasurementDbSeedBuilder() {
		setSeedFilePath(SEED_DATA_FOLDER, SEED_FILE_NAME);
	}

	public MeasurementDbSeedBuilder(String seedFileName, boolean redate) {
		setSeedFilePath(SEED_DATA_FOLDER, seedFileName);
		this.setRedate(redate);
	}

	public DbSeed build() throws FileNotFoundException, IOException {
		String workingCSVFile = SeedDataFilePath;
		DbSeed seedData = new DbSeed();

        try
        {
    		CSVUtility csvUtility = new CSVUtility();
    		List<Map<String, String>> allRows = csvUtility.getAllRows(workingCSVFile);
			Redater redater = null;
    		for (Map<String, String> rowItem : allRows) {
    			String analyzerId = rowItem.get("AnalyzerId");

    			// Initialize the redater once.
    			redater = checkAndInitializeRedater(allRows, redater, analyzerId);

    			String epochTime = isRedate() ? NumberUtility.formatString(redater.getNextUnixTime(), 3) : rowItem.get("EpochTime");
    			String createDate = rowItem.get("CreateDate");
    			String gpsLatitude = handleNullGetValue(rowItem.get("GpsLatitude"));
    			String gpsLongitude = handleNullGetValue(rowItem.get("GpsLongitude"));
    			String gpsFit = handleNullGetValue(rowItem.get("GpsFit"));
    			String shape = handleNullGetValue(rowItem.get("Shape"));
    			String instrumentStatus = rowItem.get("InstrumentStatus");
    			String valveMask = rowItem.get("ValveMask");
    			String carSpeedNorth = handleNullGetValue(rowItem.get("CarSpeedNorth"));
    			String carSpeedEast = handleNullGetValue(rowItem.get("CarSpeedEast"));
    			String windSpeedNorth = handleNullGetValue(rowItem.get("WindSpeedNorth"));
    			String windSpeedEast = handleNullGetValue(rowItem.get("WindSpeedEast"));
    			String windDirectionStdDev = handleNullGetValue(rowItem.get("WindDirectionStdDev"));
    			String weatherStationRotation = handleNullGetValue(rowItem.get("WeatherStationRotation"));
    			String windSpeedLateral = handleNullGetValue(rowItem.get("WindSpeedLateral"));
    			String windSpeedLongitudinal = handleNullGetValue(rowItem.get("WindSpeedLongitudinal"));
    			String chemDetect = handleNullGetValue(rowItem.get("ChemDetect"));
    			String species = handleNullGetValue(rowItem.get("Species"));
    			String cH4 = handleNullGetValue(rowItem.get("CH4"));
    			String cO2 = handleNullGetValue(rowItem.get("CO2"));
    			String h2OPercent = handleNullGetValue(rowItem.get("H2OPercent"));
    			String deltaCH4 = handleNullGetValue(rowItem.get("DeltaCH4"));
    			String peripheralStatus = handleNullGetValue(rowItem.get("PeripheralStatus"));
    			String analyzerStatus = handleNullGetValue(rowItem.get("AnalyzerStatus"));
    			String cavityPressure = rowItem.get("CavityPressure");
    			String warmBoxTemperature = rowItem.get("WarmBoxTemperature");
    			String hotBoxTemperature = rowItem.get("HotBoxTemperature");
    			String mobileFlowRate = rowItem.get("MobileFlowRate");
    			String analyzerMode = rowItem.get("AnalyzerMode");
    			String peakDetectorState = rowItem.get("PeakDetectorState");
    			String c2H6 = handleNullGetValue(rowItem.get("C2H6"));
    			String c2H4 = handleNullGetValue(rowItem.get("C2H4"));
    			String analyzerEthaneConcentrationUncertainty = handleNullGetValue(rowItem.get("AnalyzerEthaneConcentrationUncertainty"));

    			seedData.addInsertStatement(String.format(INSERT_TEMPLATE, analyzerId, epochTime, createDate, gpsLatitude, gpsLongitude, gpsFit, shape, instrumentStatus, valveMask, carSpeedNorth, carSpeedEast, windSpeedNorth, windSpeedEast, windDirectionStdDev, weatherStationRotation, windSpeedLateral, windSpeedLongitudinal, chemDetect, species, cH4, cO2, h2OPercent, deltaCH4, peripheralStatus, analyzerStatus, cavityPressure, warmBoxTemperature, hotBoxTemperature, mobileFlowRate, analyzerMode, peakDetectorState, c2H6, c2H4, analyzerEthaneConcentrationUncertainty));
			}

    		this.setStartEpoch(redater.getFirstTime());
    		this.setEndEpoch(redater.getLastTime());

            seedData.setDestinationTableName(TABLE_NAME);
        }
        catch (Exception e)
        {
            Log.error(ExceptionUtility.getStackTraceString(e));
        }
		return seedData;
	}

	private Redater checkAndInitializeRedater(List<Map<String, String>> allRows, Redater redater, String analyzerId) {
		Measurement firstMeasurement = null;
		if (redater == null) {
			Measurement measurement = new Measurement();
			measurement.purgeCache();
			firstMeasurement = measurement.getFirstMeasurement(analyzerId);
			double bufferTime = 1000;
			double baseUnixTime = DateUtility.getCurrentUnixEpochTime() - allRows.size() - bufferTime;
			if (firstMeasurement != null) {
				baseUnixTime = firstMeasurement.getEpochTime() - allRows.size() - bufferTime;
			}
			redater = new Redater(baseUnixTime);
		}
		return redater;
	}

	public boolean isRedate() {
		return redate;
	}

	public void setRedate(boolean redate) {
		this.redate = redate;
	}

	public double getStartEpoch() {
		return startEpoch;
	}

	public void setStartEpoch(double startEpoch) {
		this.startEpoch = startEpoch;
	}

	public double getEndEpoch() {
		return endEpoch;
	}

	public void setEndEpoch(double endEpoch) {
		this.endEpoch = endEpoch;
	}
}
