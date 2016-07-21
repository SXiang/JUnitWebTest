package surveyor.dbseed.source;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.microsoft.sqlserver.jdbc.SQLServerBulkCSVFileRecord;
import common.source.ExceptionUtility;
import common.source.Log;

public class SurveyDbSeedBuilder extends BaseDbSeedBuilder {
	public static final String TABLE_NAME = "[dbo].[Survey]";
	private static final String SEED_DATA_FOLDER = SURVEY_SEED_DATA_FOLDER;
	private static final String SEED_FILE_NAME = "SurveySeed.csv";

	public SurveyDbSeedBuilder() {
		setSeedFilePath(SEED_DATA_FOLDER, SEED_FILE_NAME);
	}

	public SurveyDbSeedBuilder(String seedFileName) {
		setSeedFilePath(SEED_DATA_FOLDER, seedFileName);
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
            fileRecord.addColumnMetadata(3, null, java.sql.Types.CHAR, 0, 0);  
            fileRecord.addColumnMetadata(4, null, java.sql.Types.CHAR, 0, 0);  
            fileRecord.addColumnMetadata(5, null, java.sql.Types.CHAR, 0, 0);  
            fileRecord.addColumnMetadata(6, null, java.sql.Types.CHAR, 0, 0);  
            fileRecord.addColumnMetadata(7, null, java.sql.Types.DOUBLE, 0, 0);  
            fileRecord.addColumnMetadata(8, null, java.sql.Types.DOUBLE, 0, 0);  
            fileRecord.addColumnMetadata(9, null, java.sql.Types.TIMESTAMP, 0, 0);  
            fileRecord.addColumnMetadata(10, null, java.sql.Types.TIMESTAMP, 0, 0);  
            fileRecord.addColumnMetadata(11, null, java.sql.Types.NVARCHAR, 450, 0);  
            fileRecord.addColumnMetadata(12, null, java.sql.Types.NVARCHAR, 50, 0);  
            fileRecord.addColumnMetadata(13, null, java.sql.Types.DOUBLE, 0, 0);  
            fileRecord.addColumnMetadata(14, null, java.sql.Types.DOUBLE, 0, 0);  
            fileRecord.addColumnMetadata(15, null, java.sql.Types.NVARCHAR, 50, 0);  
            fileRecord.addColumnMetadata(16, null, java.sql.Types.BIT, 0, 0);  
            fileRecord.addColumnMetadata(17, null, java.sql.Types.TIMESTAMP, 0, 0);  
            fileRecord.addColumnMetadata(18, null, java.sql.Types.CHAR, 0, 0);  
            fileRecord.addColumnMetadata(19, null, java.sql.Types.NVARCHAR, 50, 0); 
            fileRecord.addColumnMetadata(20, null, java.sql.Types.TIMESTAMP, 0, 0); 

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
