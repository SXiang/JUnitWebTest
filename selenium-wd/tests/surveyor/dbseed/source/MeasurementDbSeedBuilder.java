package surveyor.dbseed.source;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import common.source.CSVUtility;
import common.source.ExceptionUtility;
import common.source.Log;

public class MeasurementDbSeedBuilder extends BaseDbSeedBuilder {
	private static final String TABLE_NAME = "[dbo].[Measurement]";
	private static final String SEED_DATA_FOLDER = SURVEY_SEED_DATA_FOLDER;
	private static final String SEED_FILE_NAME = "MeasurementSeed.csv";
	private static final String INSERT_TEMPLATE = "INSERT [dbo].[Measurement] ([AnalyzerId], [EpochTime], [CreateDate], [GpsLatitude], [GpsLongitude], [GpsFit], [Shape], [InstrumentStatus], [ValveMask], [CarSpeedNorth], [CarSpeedEast], [WindSpeedNorth], [WindSpeedEast], [WindDirectionStdDev], [WeatherStationRotation], [WindSpeedLateral], [WindSpeedLongitudinal], [ChemDetect], [Species], [CH4], [CO2], [H2OPercent], [DeltaCH4], [PeripheralStatus], [AnalyzerStatus], [CavityPressure], [WarmBoxTemperature], [HotBoxTemperature], [MobileFlowRate], [AnalyzerMode], [PeakDetectorState], [C2H6], [C2H4], [AnalyzerEthaneConcentrationUncertainty]) VALUES (N'%s', %s, CAST(N'%s' AS DateTime), %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)";

	public MeasurementDbSeedBuilder() {
		setSeedFilePath(SEED_DATA_FOLDER, SEED_FILE_NAME);
	}

	public MeasurementDbSeedBuilder(String seedFileName) {
		setSeedFilePath(SEED_DATA_FOLDER, seedFileName);
	}

	public DbSeed build() throws FileNotFoundException, IOException {
		String workingCSVFile = SeedDataFilePath;
		DbSeed seedData = new DbSeed();
		
        try  
        {              
    		CSVUtility csvUtility = new CSVUtility();
    		List<HashMap<String, String>> allRows = csvUtility.getAllRows(workingCSVFile);
    		for (HashMap<String, String> rowItem : allRows) {
    			String analyzerId = rowItem.get("AnalyzerId");
    			String epochTime = rowItem.get("EpochTime");
    			String createDate = rowItem.get("CreateDate");
    			String gpsLatitude = rowItem.get("GpsLatitude");
    			String gpsLongitude = rowItem.get("GpsLongitude");
    			String gpsFit = rowItem.get("GpsFit");
    			String shape = rowItem.get("Shape");
    			String instrumentStatus = rowItem.get("InstrumentStatus");
    			String valveMask = rowItem.get("ValveMask");
    			String carSpeedNorth = rowItem.get("CarSpeedNorth");
    			String carSpeedEast = rowItem.get("CarSpeedEast");
    			String windSpeedNorth = rowItem.get("WindSpeedNorth");
    			String windSpeedEast = rowItem.get("WindSpeedEast");
    			String windDirectionStdDev = rowItem.get("WindDirectionStdDev");
    			String weatherStationRotation = rowItem.get("WeatherStationRotation");
    			String windSpeedLateral = rowItem.get("WindSpeedLateral");
    			String windSpeedLongitudinal = rowItem.get("WindSpeedLongitudinal");
    			String chemDetect = rowItem.get("ChemDetect");
    			String species = rowItem.get("Species");
    			String cH4 = rowItem.get("CH4");
    			String cO2 = rowItem.get("CO2");
    			String h2OPercent = rowItem.get("H2OPercent");
    			String deltaCH4 = rowItem.get("DeltaCH4");
    			String peripheralStatus = rowItem.get("PeripheralStatus");
    			String analyzerStatus = rowItem.get("AnalyzerStatus");
    			String cavityPressure = rowItem.get("CavityPressure");
    			String warmBoxTemperature = rowItem.get("WarmBoxTemperature");
    			String hotBoxTemperature = rowItem.get("HotBoxTemperature");
    			String mobileFlowRate = rowItem.get("MobileFlowRate");
    			String analyzerMode = rowItem.get("AnalyzerMode");
    			String peakDetectorState = rowItem.get("PeakDetectorState");
    			String c2H6 = rowItem.get("C2H6");
    			String c2H4 = rowItem.get("C2H4");
    			String analyzerEthaneConcentrationUncertainty = rowItem.get("AnalyzerEthaneConcentrationUncertainty");

    			seedData.addInsertStatement(String.format(INSERT_TEMPLATE, analyzerId, epochTime, createDate, gpsLatitude, gpsLongitude, gpsFit, shape, instrumentStatus, valveMask, carSpeedNorth, carSpeedEast, windSpeedNorth, windSpeedEast, windDirectionStdDev, weatherStationRotation, windSpeedLateral, windSpeedLongitudinal, chemDetect, species, cH4, cO2, h2OPercent, deltaCH4, peripheralStatus, analyzerStatus, cavityPressure, warmBoxTemperature, hotBoxTemperature, mobileFlowRate, analyzerMode, peakDetectorState, c2H6, c2H4, analyzerEthaneConcentrationUncertainty));
			}
    		
            seedData.setDestinationTableName(TABLE_NAME);
        }
        catch (Exception e)  
        {  
            Log.error(ExceptionUtility.getStackTraceString(e));  
        }  
		return seedData;
	}
}
