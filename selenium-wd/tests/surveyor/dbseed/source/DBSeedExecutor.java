package surveyor.dbseed.source;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerBulkCopy;
import common.source.ExceptionUtility;
import common.source.Log;
import surveyor.dataaccess.source.ConnectionFactory;

public class DBSeedExecutor {

	/* Seed data for pushing GIS data (CustomerBoundaryType, CustomerMaterialType, Boundary and Asset) */
	
	public static void executeGisSeed() throws Exception {
		Log.method("executeGisSeed");
		executeGisSeed(null /*customerId*/);
	}
	
	public static void executeGisSeed(String customerId) throws Exception {
		Log.method("executeGisSeed", customerId);
		Connection connection = ConnectionFactory.createConnection();
		DbSeedBuilderCache dbSeedBuilderCache = new DbSeedBuilderCache(); 
		CustomerBoundaryTypeDbSeedBuilder customerBoundaryTypeDbSeedBuilder = null;
		CustomerMaterialTypeDbSeedBuilder customerMaterialTypeDbSeedBuilder = null;
		BoundaryDbSeedBuilder boundaryDbSeedBuilder = null;
		AssetDbSeedBuilder assetDbSeedBuilder = null;
		try
        {
        	customerBoundaryTypeDbSeedBuilder = new CustomerBoundaryTypeDbSeedBuilder();
        	customerBoundaryTypeDbSeedBuilder.setDbSeedCache(dbSeedBuilderCache);
        	
        	customerMaterialTypeDbSeedBuilder = new CustomerMaterialTypeDbSeedBuilder();
        	customerMaterialTypeDbSeedBuilder.setDbSeedCache(dbSeedBuilderCache);        	
        	
        	boundaryDbSeedBuilder = new BoundaryDbSeedBuilder();
        	boundaryDbSeedBuilder.setDbSeedCache(dbSeedBuilderCache);

        	assetDbSeedBuilder = new AssetDbSeedBuilder();
        	assetDbSeedBuilder.setDbSeedCache(dbSeedBuilderCache);
        	
			executeSeed(connection, customerBoundaryTypeDbSeedBuilder.build(customerId));
			executeSeed(connection, customerMaterialTypeDbSeedBuilder.build(customerId));
			executeSeed(connection, boundaryDbSeedBuilder.build(customerId));
			executeSeed(connection, assetDbSeedBuilder.build(customerId));
        	
        } catch (Exception ex) {
        	Log.error(String.format("EXCEPTION in executeGisSeed() - %s", ExceptionUtility.getStackTraceString(ex)));
        } finally {
            connection.close();
            // cleanup seed builders.
            if (customerBoundaryTypeDbSeedBuilder!=null) { 
            	customerBoundaryTypeDbSeedBuilder.close(); 
        	}
            if (customerMaterialTypeDbSeedBuilder!=null) { 
            	customerMaterialTypeDbSeedBuilder.close(); 
        	}
            if (boundaryDbSeedBuilder!=null) { 
            	boundaryDbSeedBuilder.close(); 
        	}
            if (assetDbSeedBuilder!=null) { 
            	assetDbSeedBuilder.close(); 
        	}
        }
	}

	private static void executeSeed(Connection connection, DbSeed dbSeedData) {
		Log.method("executeSeed", connection, dbSeedData);
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
                    Log.info(String.format("[%s] Table: Starting row count = %d", dbSeedData.getDestinationTableName(), countStart));  
                }  

                if (dbSeedData.getSeedData() != null) {
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
	                    	Log.info("Bulk copying seed data from CSV file...");
	                        bulkCopy.writeToServer(dbSeedData.getSeedData());  
	                    }  
	                    catch (Exception e)  
	                    {  
	                        Log.error(String.format("EXCEPTION in BulkCopy writeToServer. ERROR Message: %s", ExceptionUtility.getStackTraceString(e)));  
	                    }  
	                }  
                } else {
                	List<String> insertStatements = dbSeedData.getInsertStatements();
					if (insertStatements == null) {
                		throw new Exception("Either FileRecord or Insert Statements should be specified in a DBSeedBuilder.");
                	}
                	
                	for (String insertStmt : insertStatements) {
                    	Log.info(String.format("Executing statement -> '%s'", insertStmt));
						stmt.executeUpdate(insertStmt);
					}
                }

                // Perform a final count on the destination table to see how many rows were added.  
                try (ResultSet rsRowCount = stmt.executeQuery(String.format("SELECT COUNT(*) FROM %s;", dbSeedData.getDestinationTableName())))  
                {  
                    rsRowCount.next();  
                    long countEnd = rsRowCount.getInt(1);  
                    Log.info(String.format("[%s] Table: Ending row count = %d", dbSeedData.getDestinationTableName(), countEnd));  
                    Log.info(String.format("[%s] Table: %d rows were added.", dbSeedData.getDestinationTableName(), (countEnd - countStart)));  
                }    
            }  
        }  
        catch (Exception e)  
        {  
            Log.error(String.format("EXCEPTION in executeSeed(). ERROR Message: %s", ExceptionUtility.getStackTraceString(e)));  
        }  	
    }
}
