package surveyor.dbseed.source;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerBulkCSVFileRecord;
import com.microsoft.sqlserver.jdbc.SQLServerBulkCopy;
import common.source.ExceptionUtility;
import common.source.Log;
import surveyor.dataaccess.source.ConnectionFactory;

public class DBSeedExecutor {

	/* Seed data for pushing GIS data (CustomerBoundaryType, CustomerMaterialType, Boundary and Asset) */
	
	public static void executeGisSeed() throws Exception {
		executeGisSeed(null /*customerId*/);
	}
	
	public static void executeGisSeed(String customerId) throws Exception {
		Log.method("executeGisSeed", customerId);
		Connection connection = ConnectionFactory.createConnection();
		AssetDbSeedBuilder assetDbSeedBuilder = null;
		BoundaryDbSeedBuilder boundaryDbSeedBuilder = null;
		CustomerBoundaryTypeDbSeedBuilder customerBoundaryTypeDbSeedBuilder = null;
		CustomerMaterialTypeDbSeedBuilder customerMaterialTypeDbSeedBuilder = null;
		try
        {
        	assetDbSeedBuilder = new AssetDbSeedBuilder();
        	boundaryDbSeedBuilder = new BoundaryDbSeedBuilder();
        	customerBoundaryTypeDbSeedBuilder = new CustomerBoundaryTypeDbSeedBuilder();
        	customerMaterialTypeDbSeedBuilder = new CustomerMaterialTypeDbSeedBuilder();
        	
			executeSeed(connection, assetDbSeedBuilder.build());
			executeSeed(connection, boundaryDbSeedBuilder.build());
			executeSeed(connection, customerBoundaryTypeDbSeedBuilder.build());
			executeSeed(connection, customerMaterialTypeDbSeedBuilder.build());
        	
        } catch (Exception ex) {
        	Log.error(String.format("EXCEPTION in executeGisSeed() - %s", ExceptionUtility.getStackTraceString(ex)));
        } finally {
            connection.close();

            // close all the file records
            closeFileRecord(assetDbSeedBuilder);
            closeFileRecord(boundaryDbSeedBuilder);
            closeFileRecord(customerBoundaryTypeDbSeedBuilder);
            closeFileRecord(customerMaterialTypeDbSeedBuilder);
        }
	}

	private static void closeFileRecord(BaseDbSeedBuilder dbSeedBuilder) {
		if (dbSeedBuilder != null) {
			DbSeed dbSeedData = dbSeedBuilder.getDbSeedData();
			if (dbSeedData != null) {
				SQLServerBulkCSVFileRecord fileRecord = (SQLServerBulkCSVFileRecord)dbSeedData.getSeedData();
				if (fileRecord !=null) {
					if (fileRecord != null) try { fileRecord.close(); } catch(Exception e) {} 
				}
			}
		}
	}

	private static void executeSeed(Connection connection, DbSeed dbSeedData) {
        try  
        {  
            // Note: if you are not using try-with-resources statements (as here),  
            // you must remember to call close() on any Connection, Statement,   
            // ResultSet, and SQLServerBulkCopy objects that you create.  
            try (Statement stmt = connection.createStatement())  
            {  
                //  Execute the cleanup statements.
            	List<String> cleanupStatements = dbSeedData.getCleanupStatements();
            	for (String cleanupStmt : cleanupStatements) {
                    stmt.executeUpdate(cleanupStmt);  
				}
                 
            	// Perform an initial count on the destination table.  
                long countStart = 0;  
                try (ResultSet rsRowCount = stmt.executeQuery(String.format("SELECT COUNT(*) FROM %s;", dbSeedData.getDestinationTableName())))  
                {  
                    rsRowCount.next();  
                    countStart = rsRowCount.getInt(1);  
                    Log.info("Starting row count = " + countStart);  
                }  

                // Set up the bulk copy object.    
                // Note that the column positions in the source   
                // data reader match the column positions in    
                // the destination table so there is no need to   
                // map columns.   
                try (SQLServerBulkCopy bulkCopy = new SQLServerBulkCopy(connection))  
                {  
                    bulkCopy.setDestinationTableName(dbSeedData.getDestinationTableName());  

                    try  
                    {  
                        // Write from the source to the destination.  
                        bulkCopy.writeToServer(dbSeedData.getSeedData());  
                    }  
                    catch (Exception e)  
                    {  
                        // Handle any errors that may have occurred.  
                        e.printStackTrace();  
                    }  
                }  

                // Perform a final count on the destination table to see how many rows were added.  
                try (ResultSet rsRowCount = stmt.executeQuery(String.format("SELECT COUNT(*) FROM %s;", dbSeedData.getDestinationTableName())))  
                {  
                    rsRowCount.next();  
                    long countEnd = rsRowCount.getInt(1);  
                    Log.info("Ending row count = " + countEnd);  
                    Log.info((countEnd - countStart) + " rows were added.");  
                }    
            }  
        }  
        catch (Exception e)  
        {  
            // Handle any errors that may have occurred.  
            e.printStackTrace();  
        }  	
    }
}
