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

public class SegmentDbSeedBuilder extends BaseDbSeedBuilder {
	private static final String TABLE_NAME = "[dbo].[Segment]";
	private static final String SEED_FILE_NAME = "SegmentSeed.csv";
	private static final String INSERT_TEMPLATE = "INSERT [dbo].[Segment] ([SurveyId], [Order], [Mode], [Shape]) VALUES (N'%s', %s, %s, %s)";

	public SegmentDbSeedBuilder() {
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
    			String surveyId = rowItem.get("SurveyId");
    			String order = rowItem.get("Order");
    			String mode = rowItem.get("Mode");
    			String shape = rowItem.get("Shape");

    			seedData.addInsertStatement(String.format(INSERT_TEMPLATE, surveyId, order, mode, shape));
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
