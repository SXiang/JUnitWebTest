package surveyor.dbseed.source;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import common.source.CSVUtility;
import common.source.ExceptionUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;

public class CaptureEventDbSeedBuilder extends BaseDbSeedBuilder {
	private static final String TABLE_NAME = "[dbo].[CaptureEvent]";
	private static final String SEED_FILE_NAME = "CaptureEventSeed.csv";
	private static final String INSERT_TEMPLATE = "INSERT [dbo].[CaptureEvent] ([Id], [AnalyzerId], [EpochTime], [DateTime], [GpsLatitude], [GpsLongitude], [Shape], [Disposition], [Delta], [Concentration], [Uncertainty], [CaptureType], [Distance], [ReplayMax], [ReplayLMin], [ReplayRMin], [SurveyId], [EthaneRatio], [EthaneRatioSdev], [ClassificationConfidence]) VALUES (N'%s', N'%s', %s, CAST(N'%s' AS DateTime), %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, N'%s', %s, %s, %s)";

	public CaptureEventDbSeedBuilder() {
		SeedDataFilePath = TestContext.INSTANCE.getExecutionPath() + TestSetup.SQL_DATA_FOLDER + File.separator + SEED_FILE_NAME;
	}
	
	public DbSeed build() throws FileNotFoundException, IOException {
		String workingCSVFile = SeedDataFilePath;
		DbSeed seedData = new DbSeed();
		
        try  
        {              
    		CSVUtility csvUtility = new CSVUtility();
    		List<HashMap<String, String>> allRows = csvUtility.getAllRows(workingCSVFile);
    		for (HashMap<String, String> rowItem : allRows) {
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
    			String classificationConfidence = rowItem.get("ClassificationConfidence");

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
}
