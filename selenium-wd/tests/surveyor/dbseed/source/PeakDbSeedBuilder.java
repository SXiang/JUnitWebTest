package surveyor.dbseed.source;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import common.source.CSVUtility;
import common.source.ExceptionUtility;
import common.source.Log;

public class PeakDbSeedBuilder extends BaseDbSeedBuilder {
	private static final String TABLE_NAME = "[dbo].[Peak]";
	private static final String SEED_DATA_FOLDER = SURVEY_SEED_DATA_FOLDER;
	private static final String SEED_FILE_NAME = "PeakSeed.csv";
	private static final String INSERT_TEMPLATE = "INSERT [dbo].[Peak] ([AnalyzerId], [EpochTime], [SurveyModeTypeId], [Amplitude], [CH4], [Position], [Lisa], [LisaOpeningAngle], [LisaBearing], [CarBearing], [Major], [Minor], [CarSpeedNorth], [CarSpeedEast], [WindDirectionStdDev], [WindSpeedNorth], [WindSpeedEast], [Sigma], [Distance], [GpsLatitude], [GpsLongitude], [PassedAutoThreshold], [SurveyId], [EthaneRatio], [EthaneRatioSdevRaw], [EthaneRatioSdev], [EthaneConcentrationSdev], [EthyleneRatio], [EthyleneRatioSdevRaw], [EthyleneRatioSdev], [EthyleneConcentrationSdev], [PipEnergy], [MethanePeaktoPeak], [Disposition], [ClassificationConfidence]) VALUES (N'%s', %s, N'%s', %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, N'%s', %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)";

	public PeakDbSeedBuilder() {
		setSeedFilePath(SEED_DATA_FOLDER, SEED_FILE_NAME);
	}

	public PeakDbSeedBuilder(String seedFileName) {
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
    			String surveyModeTypeId = rowItem.get("SurveyModeTypeId");
    			String amplitude = rowItem.get("Amplitude");
    			String cH4 = rowItem.get("CH4");
    			String position = rowItem.get("Position");
    			String lisa = rowItem.get("Lisa");
    			String lisaOpeningAngle = rowItem.get("LisaOpeningAngle");
    			String lisaBearing = rowItem.get("LisaBearing");
    			String carBearing = rowItem.get("CarBearing");
    			String major = rowItem.get("Major");
    			String minor = rowItem.get("Minor");
    			String carSpeedNorth = rowItem.get("CarSpeedNorth");
    			String carSpeedEast = rowItem.get("CarSpeedEast");
    			String windDirectionStdDev = rowItem.get("WindDirectionStdDev");
    			String windSpeedNorth = rowItem.get("WindSpeedNorth");
    			String windSpeedEast = rowItem.get("WindSpeedEast");
    			String sigma = rowItem.get("Sigma");
    			String distance = rowItem.get("Distance");
    			String gpsLatitude = rowItem.get("GpsLatitude");
    			String gpsLongitude = rowItem.get("GpsLongitude");
    			String passedAutoThreshold = rowItem.get("PassedAutoThreshold");
    			String surveyId = rowItem.get("SurveyId");
    			String ethaneRatio = rowItem.get("EthaneRatio");
    			String ethaneRatioSdevRaw = rowItem.get("EthaneRatioSdevRaw");
    			String ethaneRatioSdev = rowItem.get("EthaneRatioSdev");
    			String ethaneConcentrationSdev = rowItem.get("EthaneConcentrationSdev");
    			String ethyleneRatio = rowItem.get("EthyleneRatio");
    			String ethyleneRatioSdevRaw = rowItem.get("EthyleneRatioSdevRaw");
    			String ethyleneRatioSdev = rowItem.get("EthyleneRatioSdev");
    			String ethyleneConcentrationSdev = rowItem.get("EthyleneConcentrationSdev");
    			String pipEnergy = rowItem.get("PipEnergy");
    			String methanePeaktoPeak = rowItem.get("MethanePeaktoPeak");
    			String disposition = rowItem.get("Disposition");
    			String classificationConfidence = rowItem.get("ClassificationConfidence");

    			seedData.addInsertStatement(String.format(INSERT_TEMPLATE, analyzerId, epochTime, surveyModeTypeId, amplitude, cH4, position, lisa, lisaOpeningAngle, lisaBearing, carBearing, major, minor, carSpeedNorth, carSpeedEast, windDirectionStdDev, windSpeedNorth, windSpeedEast, sigma, distance, gpsLatitude, gpsLongitude, passedAutoThreshold, surveyId, ethaneRatio, ethaneRatioSdevRaw, ethaneRatioSdev, ethaneConcentrationSdev, ethyleneRatio, ethyleneRatioSdevRaw, ethyleneRatioSdev, ethyleneConcentrationSdev, pipEnergy, methanePeaktoPeak, disposition, classificationConfidence));
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
