package surveyor.dbseed.source;

import com.microsoft.sqlserver.jdbc.SQLServerBulkCSVFileRecord;

public class AssetDbSeedBuilder extends BaseDbSeedBuilder {
	
	private static final String TABLE_NAME = "[dbo].[Asset]";
	private static final String SEED_DATA_FILE_PATH = "C:\\Test\\TestBulkCSVExample.csv";

	public DbSeed build() {
		DbSeed seedData = new DbSeed();
		seedData.addCleanupStatement("DELETE [dbo].[Asset] WHERE [CustomerId]='%s'");
		
		SQLServerBulkCSVFileRecord fileRecord = null;  
        try  
        {              
            // Get data from the source file by loading it into a class that implements ISQLServerBulkRecord.  
            // Here we are using the SQLServerBulkCSVFileRecord implementation to import the example CSV file.  
            fileRecord = new SQLServerBulkCSVFileRecord(SEED_DATA_FILE_PATH, true);      
  
            // Set the metadata for each column to be copied.  
            fileRecord.addColumnMetadata(1, null, java.sql.Types.INTEGER, 0, 0);  
            fileRecord.addColumnMetadata(2, null, java.sql.Types.NVARCHAR, 50, 0);  
            
            // NOTE: The data value for GEOMETRY should be in binary format.
            // See example - http://stackoverflow.com/questions/5792467/sqlbulkcopy-datatable-with-wellknowntext-spatial-data-column
            fileRecord.addColumnMetadata(3, null, java.sql.Types.NVARCHAR, 25, 0);
            
            seedData.setSeedData(fileRecord);
            seedData.setDestinationTableName(TABLE_NAME);
        }
        catch (Exception e)  
        {  
            // Handle any errors that may have occurred.  
            e.printStackTrace();  
        }  
        finally  
        {  
            if (fileRecord != null) try { fileRecord.close(); } catch(Exception e) {}  
        }  
		return seedData;
	}
}
