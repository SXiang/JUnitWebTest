package surveyor.dbseed.source;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import common.source.CSVUtility;
import common.source.ExceptionUtility;
import common.source.Log;
import surveyor.dataaccess.source.CaptureEvent;

public class CaptureEventDbSeedBuilder extends BaseDbSeedBuilder {
	public static final String TABLE_NAME = "[dbo].[CaptureEvent]";
	private static final String SEED_DATA_FOLDER = SURVEY_SEED_DATA_FOLDER;
	private static final String SEED_FILE_NAME = "CaptureEventSeed.csv";
	private static final String INSERT_TEMPLATE = "INSERT [dbo].[CaptureEvent] ([Id], [AnalyzerId], [EpochTime], [DateTime], [GpsLatitude], [GpsLongitude], [Shape], [Disposition], [Delta], [Concentration], [Uncertainty], [CaptureType], [Distance], [ReplayMax], [ReplayLMin], [ReplayRMin], [SurveyId], [EthaneRatio], [EthaneRatioSdev], [ClassificationConfidence]) VALUES (N'%s', N'%s', %s, CAST(N'%s' AS DateTime), %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, N'%s', %s, %s, %s)";

	public CaptureEventDbSeedBuilder() {
		setSeedFilePath(SEED_DATA_FOLDER, SEED_FILE_NAME);
	}

	public CaptureEventDbSeedBuilder(String seedFileName) {
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
    			String id = rowItem.get("Id");
    			String analyzerId = rowItem.get("AnalyzerId");
    			String epochTime = rowItem.get("EpochTime");
    			String dateTime = rowItem.get("DateTime");
    			String gpsLatitude = rowItem.get("GpsLatitude");
    			String gpsLongitude = rowItem.get("GpsLongitude");
    			String shape = rowItem.get("Shape");
    			String disposition = rowItem.get("Disposition");
    			String delta = rowItem.get("Delta");
    			String concentration = rowItem.get("Concentration");
    			String uncertainty = rowItem.get("Uncertainty");
    			String captureType = rowItem.get("CaptureType");
    			String distance = rowItem.get("Distance");
    			String replayMax = rowItem.get("ReplayMax");
    			String replayLMin = rowItem.get("ReplayLMin");
    			String replayRMin = rowItem.get("ReplayRMin");
    			String surveyId = rowItem.get("SurveyId");
    			String ethaneRatio = rowItem.get("EthaneRatio");
    			String ethaneRatioSdev = rowItem.get("EthaneRatioSdev");
    			String classificationConfidence = handleNullGetValue(rowItem.get("ClassificationConfidence"));

    			seedData.addInsertStatement(String.format(INSERT_TEMPLATE, id, analyzerId, epochTime, dateTime, gpsLatitude, gpsLongitude, shape, disposition, delta, concentration, uncertainty, captureType, distance, replayMax, replayLMin, replayRMin, surveyId, ethaneRatio, ethaneRatioSdev, classificationConfidence));
			}

            seedData.setDestinationTableName(TABLE_NAME);
        }
        catch (Exception e)
        {
            Log.error(ExceptionUtility.getStackTraceString(e));
        }
		return seedData;
	}

	public static List<CaptureEvent> readRowsFromSeed(String seedFileTag) throws FileNotFoundException, IOException {
		String seedFilename = String.format("CaptureEvent-%s.csv", seedFileTag);
		List<Map<String, String>> seedFileLines = new CaptureEventDbSeedBuilder(seedFilename).getSeedFileLines();
		List<CaptureEvent> captureEvents = new ArrayList<>();
		seedFileLines.stream()
			.forEach(row -> {
				CaptureEvent captureEvent = new CaptureEvent();
				captureEvent.setAnalyzerId(String.valueOf(row.get("AnalyzerId")).toUpperCase());
				captureEvent.setCaptureType(Boolean.valueOf(row.get("CaptureType")));
				captureEvent.setClassificationConfidence(row.get("ClassificationConfidence") != "NULL" ? Float.valueOf(row.get("ClassificationConfidence")) : Float.MIN_VALUE);
				captureEvent.setConcentration(Float.valueOf(row.get("Concentration")));
				captureEvent.setDateTime(new Date(Timestamp.valueOf(row.get("DateTime")).getTime()));
				captureEvent.setDelta(Float.valueOf(row.get("Delta")));
				captureEvent.setDisposition(Integer.valueOf(row.get("Disposition")));
				captureEvent.setDistance(row.get("Distance") != "NULL" ? Float.valueOf(row.get("Distance")) : Float.MIN_VALUE);
				captureEvent.setEpochTime(Float.valueOf(row.get("EpochTime")));
				captureEvent.setEthaneRatio(row.get("EthaneRatio") != "NULL" ? Float.valueOf(row.get("EthaneRatio")) : Float.MIN_VALUE);
				captureEvent.setEthaneRatioSdev(row.get("EthaneRatioSdev") != "NULL" ? Float.valueOf(row.get("EthaneRatioSdev")) : Float.MIN_VALUE);
				captureEvent.setGpsLatitude(Float.valueOf(row.get("GpsLatitude")));
				captureEvent.setGpsLongitude(Float.valueOf(row.get("GpsLongitude")));
				captureEvent.setId(String.valueOf(row.get("Id")).toString());
				captureEvent.setReplayLMin(Float.valueOf(row.get("ReplayLMin")));
				captureEvent.setReplayMax(Float.valueOf(row.get("ReplayMax")));
				captureEvent.setReplayRMin(Float.valueOf(row.get("ReplayRMin")));
				captureEvent.setShape(row.get("Shape").toString().replace("0x", ""));
				captureEvent.setSurveyId(row.get("SurveyId") != "NULL" ? String.valueOf(row.get("SurveyId")).toUpperCase() : "");
				captureEvent.setUncertainty(Float.valueOf(row.get("Uncertainty")));
				captureEvents.add(captureEvent);
			});

		return captureEvents;
	}
}