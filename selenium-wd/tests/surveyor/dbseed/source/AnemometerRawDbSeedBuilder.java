package surveyor.dbseed.source;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.microsoft.sqlserver.jdbc.SQLServerBulkCSVFileRecord;
import common.source.ExceptionUtility;
import common.source.Log;

public class AnemometerRawDbSeedBuilder extends BaseDbSeedBuilder {
	private static final String TABLE_NAME = "[dbo].[AnemometerRaw]";
	private static final String SEED_DATA_FOLDER = SURVEY_SEED_DATA_FOLDER;
	private static final String SEED_FILE_NAME = "AnemometerRawSeed.csv";

	public AnemometerRawDbSeedBuilder() {
		setSeedFilePath(SEED_DATA_FOLDER, SEED_FILE_NAME);
	}

	public AnemometerRawDbSeedBuilder(String seedFileName) {
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
            fileRecord.addColumnMetadata(2, null, java.sql.Types.DOUBLE, 0, 0);  
            fileRecord.addColumnMetadata(3, null, java.sql.Types.DOUBLE, 0, 0);  
            fileRecord.addColumnMetadata(4, null, java.sql.Types.DOUBLE, 0, 0);  
            fileRecord.addColumnMetadata(5, null, java.sql.Types.DOUBLE, 0, 0);  
            fileRecord.addColumnMetadata(6, null, java.sql.Types.INTEGER, 0, 0);  

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