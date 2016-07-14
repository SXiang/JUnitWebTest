package surveyor.dbseed.source;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import com.microsoft.sqlserver.jdbc.SQLServerBulkCSVFileRecord;
import common.source.ExceptionUtility;
import common.source.FileUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;

public class CustomerMaterialTypeDbSeedBuilder extends BaseDbSeedBuilder {
	public static final String TABLE_NAME = "[dbo].[CustomerMaterialType]";
	private static final String PK_COL_NAME = "Id";
	private static final String SEED_DATA_FOLDER = "GisSeedData";
	private static final String SEED_FILE_NAME = "CustomerMaterialTypeSeed.csv";

	public CustomerMaterialTypeDbSeedBuilder() {
		SeedDataFilePath = TestContext.INSTANCE.getExecutionPath() + TestSetup.SQL_DATA_FOLDER + File.separator + SEED_DATA_FOLDER + File.separator + SEED_FILE_NAME;
	}
	
	public DbSeed build(String customerID) throws FileNotFoundException, IOException {
		String workingCSVFile = SeedDataFilePath;
		boolean customerIDSpecified = false;
		if (customerID != null && !customerID.isEmpty()) {
			customerIDSpecified = true;
			workingCSVFile = createCSVFileWithCustomerData(customerID, PK_COL_NAME, TABLE_NAME);
		}
		
		DbSeed seedData = new DbSeed();
		if (customerIDSpecified) {
			seedData.addCleanupStatement(String.format("DELETE [dbo].[CustomerMaterialType] WHERE [CustomerId]='%s'", customerID));
		}
		
		SQLServerBulkCSVFileRecord fileRecord = null;  
        try  
        {              
            // Get data from the source file by loading it into a class that implements ISQLServerBulkRecord.  
            // Use the SQLServerBulkCSVFileRecord implementation to import the CSV file.  
            fileRecord = new SQLServerBulkCSVFileRecord(workingCSVFile, true);      
  
            // Set the metadata for each column to be copied.  
            fileRecord.addColumnMetadata(1, null, java.sql.Types.CHAR, 0, 0);  
            fileRecord.addColumnMetadata(2, null, java.sql.Types.CHAR, 0, 0);  
            fileRecord.addColumnMetadata(3, null, java.sql.Types.NVARCHAR, 50, 0);  
            fileRecord.addColumnMetadata(4, null, java.sql.Types.NVARCHAR, 7, 0);  
            fileRecord.addColumnMetadata(5, null, java.sql.Types.SMALLINT, 0, 0);  
            fileRecord.addColumnMetadata(6, null, java.sql.Types.BIT, 0, 0);  

            seedData.setSeedData(fileRecord);
            seedData.setDestinationTableName(TABLE_NAME);
        }
        catch (Exception e)  
        {  
            Log.error(ExceptionUtility.getStackTraceString(e));  
        }  
        finally  
        {  
            if (customerIDSpecified) {
            	FileUtility.deleteFile(Paths.get(workingCSVFile));
            }
        }  
		return seedData;
	}
}
