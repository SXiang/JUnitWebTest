package surveyor.dbseed.source;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import com.microsoft.sqlserver.jdbc.SQLServerBulkCSVFileRecord;
import common.source.ExceptionUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;

public class SurveyConditionDbSeedBuilder extends BaseDbSeedBuilder {
	private static final String TABLE_NAME = "[dbo].[SurveyCondition]";
	private static final String SEED_FILE_NAME = "SurveyConditionSeed.csv";

	public SurveyConditionDbSeedBuilder() {
		SeedDataFilePath = TestContext.INSTANCE.getExecutionPath() + TestSetup.SQL_DATA_FOLDER + File.separator + SEED_FILE_NAME;
	}

	public DbSeed build() throws FileNotFoundException, IOException {
		String workingCSVFile = SeedDataFilePath;
		DbSeed seedData = new DbSeed();
        SQLServerBulkCSVFileRecord fileRecord = null;
        try
        {
            // Get data from the source file by loading it into a class that implements ISQLServerBulkRecord.  
            // Use the SQLServerBulkCSVFileRecord implementation to import the CSV file.  
            fileRecord = new SQLServerBulkCSVFileRecord(workingCSVFile, true);      
            fileRecord.addColumnMetadata(1, null, java.sql.Types.CHAR, 0, 0);  
            fileRecord.addColumnMetadata(2, null, java.sql.Types.CHAR, 0, 0);  
            fileRecord.addColumnMetadata(3, null, java.sql.Types.NVARCHAR, 450, 0);  
            fileRecord.addColumnMetadata(4, null, java.sql.Types.NVARCHAR, 450, 0);  

            seedData.setSeedData(fileRecord);
            seedData.setDestinationTableName(TABLE_NAME);
        }
        catch (Exception e)  
        {  
            // Log error.  
            Log.error(ExceptionUtility.getStackTraceString(e));  
        }  
		return seedData;
	}
}
