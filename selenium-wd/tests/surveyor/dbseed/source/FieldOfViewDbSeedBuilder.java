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

public class FieldOfViewDbSeedBuilder extends BaseDbSeedBuilder {
	private static final String TABLE_NAME = "[dbo].[FieldOfView]";
	private static final String SEED_FILE_NAME = "FieldOfViewSeed.csv";
	private static final String INSERT_TEMPLATE = "INSERT [dbo].[FieldOfView] ([AnalyzerId], [EpochTime], [SurveyModeTypeId], [Shape], [SurveyId]) VALUES (N'%s', %s, N'%s', %s, N'%s')";

	public FieldOfViewDbSeedBuilder() {
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
    			String analyzerId = rowItem.get("AnalyzerId");
    			String epochTime = rowItem.get("EpochTime");
    			String surveyModeTypeId = rowItem.get("SurveyModeTypeId");
    			String shape = rowItem.get("Shape");
    			String surveyId = rowItem.get("SurveyId");

    			seedData.addInsertStatement(String.format(INSERT_TEMPLATE, analyzerId, epochTime, surveyModeTypeId, shape, surveyId));
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
