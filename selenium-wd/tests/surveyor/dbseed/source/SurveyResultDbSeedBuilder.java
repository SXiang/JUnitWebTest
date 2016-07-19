package surveyor.dbseed.source;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import common.source.CSVUtility;
import common.source.ExceptionUtility;
import common.source.Log;

public class SurveyResultDbSeedBuilder extends BaseDbSeedBuilder {
	public static final String TABLE_NAME = "[dbo].[SurveyResult]";
	private static final String SEED_DATA_FOLDER = SURVEY_SEED_DATA_FOLDER;
	private static final String SEED_FILE_NAME = "SurveyResultSeed.csv";
	private static final String INSERT_TEMPLATE = "INSERT [dbo].[SurveyResult] ([SurveyId], [FieldOfView], [Breadcrumb]) VALUES (N'%s', %s, %s)";

	public SurveyResultDbSeedBuilder() {
		setSeedFilePath(SEED_DATA_FOLDER, SEED_FILE_NAME);
	}

	public SurveyResultDbSeedBuilder(String seedFileName) {
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
    			String surveyId = rowItem.get("SurveyId");
    			String fieldOfView = rowItem.get("FieldOfView");
    			String breadcrumb = rowItem.get("Breadcrumb");

    			seedData.addInsertStatement(String.format(INSERT_TEMPLATE, surveyId, fieldOfView, breadcrumb));
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
