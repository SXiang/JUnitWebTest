package surveyor.dbseed.source;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.source.CSVUtility;
import common.source.ExceptionUtility;
import common.source.Log;
import surveyor.dataaccess.source.Peak;

public class PeakDbSeedBuilder extends BaseDbSeedBuilder {
	public static final String TABLE_NAME = "[dbo].[Peak]";
	private static final String SEED_DATA_FOLDER = SURVEY_SEED_DATA_FOLDER;
	private static final String SEED_FILE_NAME = "PeakSeed.csv";
	private static final String INSERT_TEMPLATE = "INSERT [dbo].[Peak] ([AnalyzerId], [EpochTime], [Amplitude], [CH4], [Position], [Lisa], [LisaOpeningAngle], [LisaBearing], [CarBearing], [Major], [Minor], [CarSpeedNorth], [CarSpeedEast], [WindDirectionStdDev], [WindSpeedNorth], [WindSpeedEast], [Sigma], [Distance], [GpsLatitude], [GpsLongitude], [PassedAutoThreshold], [SurveyId], [EthaneRatio], [EthaneRatioSdevRaw], [EthaneRatioSdev], [EthaneConcentrationSdev], [EthyleneRatio], [EthyleneRatioSdevRaw], [EthyleneRatioSdev], [EthyleneConcentrationSdev], [PipEnergy], [MethanePeaktoPeak], [Disposition], [ClassificationConfidence], [SurvivedCollection]) VALUES (N'%s', %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, N'%s', %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)";

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
    		List<Map<String, String>> allRows = csvUtility.getAllRows(workingCSVFile);
    		for (Map<String, String> rowItem : allRows) {
    			String analyzerId = rowItem.get("AnalyzerId");
    			String epochTime = rowItem.get("EpochTime");
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
    			String survivedCollection = rowItem.get("SurvivedCollection");

    			seedData.addInsertStatement(String.format(INSERT_TEMPLATE, analyzerId, epochTime, amplitude, cH4, position, lisa, lisaOpeningAngle, lisaBearing, carBearing, major, minor, carSpeedNorth, carSpeedEast, windDirectionStdDev, windSpeedNorth, windSpeedEast, sigma, distance, gpsLatitude, gpsLongitude, passedAutoThreshold, surveyId, ethaneRatio, ethaneRatioSdevRaw, ethaneRatioSdev, ethaneConcentrationSdev, ethyleneRatio, ethyleneRatioSdevRaw, ethyleneRatioSdev, ethyleneConcentrationSdev, pipEnergy, methanePeaktoPeak, disposition, classificationConfidence, survivedCollection));
			}

            seedData.setDestinationTableName(TABLE_NAME);
        }
        catch (Exception e)
        {
            Log.error(ExceptionUtility.getStackTraceString(e));
        }
		return seedData;
	}

	public static List<Peak> readRowsFromSeed(String seedFileTag) throws FileNotFoundException, IOException {
		String seedFilename = String.format("Peak-%s.csv", seedFileTag);
		List<Map<String, String>> seedFileLines = new PeakDbSeedBuilder(seedFilename).getSeedFileLines();
		List<Peak> peaks = new ArrayList<>();
		seedFileLines.stream()
			.forEach(row -> {
				Peak peak = new Peak();
				peak.setAmplitude(Float.valueOf(row.get("Amplitude")));
				peak.setAnalyzerId(String.valueOf(row.get("AnalyzerId")));
				peak.setCarBearing(Float.valueOf(row.get("CarBearing")));
				peak.setCarSpeedEast(row.get("CarSpeedEast") != "NULL" ? Float.valueOf(row.get("CarSpeedEast")) : Float.MIN_VALUE);
				peak.setCarSpeedNorth(row.get("CarSpeedNorth") != "NULL" ? Float.valueOf(row.get("CarSpeedNorth")) : Float.MIN_VALUE);
				peak.setCH4(Float.valueOf(row.get("CH4")));
				peak.setDistance(Float.valueOf(row.get("Distance")));
				peak.setEpochTime(Float.valueOf(row.get("EpochTime")));
				peak.setGpsLatitude(Float.valueOf(row.get("GpsLatitude")));
				peak.setGpsLongitude(Float.valueOf(row.get("GpsLongitude")));
				peak.setLisa(row.get("Lisa") != "NULL" ? row.get("Lisa") : null);
				peak.setLisaBearing(Float.valueOf(row.get("LisaBearing")));
				peak.setLisaOpeningAngle(Float.valueOf(row.get("LisaOpeningAngle")));
				peak.setMajor(Float.valueOf(row.get("Major")));
				peak.setMinor(Float.valueOf(row.get("Minor")));
				peak.setPassedAutoThreshold(Boolean.valueOf(row.get("PassedAutoThreshold")));
				peak.setPosition(row.get("Position") != "NULL" ? row.get("Position") : null);
				peak.setSigma(Float.valueOf(row.get("Sigma")));
				peak.setSurveyId(row.get("SurveyId") != "NULL" ? String.valueOf(row.get("SurveyId")) : "");
				peak.setWindDirectionStdDev(row.get("WindDirectionStdDev") != "NULL" ? Float.valueOf(row.get("WindDirectionStdDev")) : Float.MIN_VALUE);
				peak.setWindSpeedEast(row.get("WindSpeedEast") != "NULL" ? Float.valueOf(row.get("WindSpeedEast")) : Float.MIN_VALUE);
				peak.setWindSpeedNorth(row.get("WindSpeedNorth") != "NULL" ? Float.valueOf(row.get("WindSpeedNorth")) : Float.MIN_VALUE);
				peaks.add(peak);
			});

		return peaks;
	}
}
