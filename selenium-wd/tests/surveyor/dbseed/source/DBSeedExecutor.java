package surveyor.dbseed.source;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerBulkCopy;
import common.source.ExceptionUtility;
import common.source.FileUtility;
import common.source.Log;
import surveyor.dataaccess.source.ConnectionFactory;

public class DBSeedExecutor {

	private static final boolean ENABLE_VERBOSE_LOGGING = false;

	/* Method for pushing Survey seed data */

	public static void executeSurveyDataSeed() throws Exception {
		Log.method("executeSurveyDataSeed");
		Connection connection = ConnectionFactory.createConnection();

		SurveyDbSeedBuilder surveyDbSeedBuilder = null;
		SurveyConditionDbSeedBuilder surveyConditionDbSeedBuilder = null;
		SurveyResultDbSeedBuilder surveyResultDbSeedBuilder = null;
		MeasurementDbSeedBuilder measurementDbSeedBuilder = null;
		GPSRawDbSeedBuilder gpsRawDbSeedBuilder = null;
		AnemometerRawDbSeedBuilder anemometerRawDbSeedBuilder = null;
		CaptureEventDbSeedBuilder captureEventDbSeedBuilder = null;
		FieldOfViewDbSeedBuilder fieldOfViewDbSeedBuilder = null;
		PeakDbSeedBuilder peakDbSeedBuilder = null;
		SegmentDbSeedBuilder segmentDbSeedBuilder = null;
		NoteDbSeedBuilder noteDbSeedBuilder = null;
		
		String[] surveyTags = {"stnd-pic","rr-pic","man-pic-1","man-pic-2","op-pic","EthaneStnd","EthaneManual",
				"stnd-sqacudr-1","stnd-sqacudr-2","stnd-sqacudr-3","rr-sqacudr-1","rr-sqacudr-2","op-sqacudr"};

		try {
			for (String surveyTag : surveyTags) {
				try
		        {
					surveyDbSeedBuilder = new SurveyDbSeedBuilder(String.format("Survey-%s.csv", surveyTag));
					surveyConditionDbSeedBuilder = new SurveyConditionDbSeedBuilder(String.format("SurveyCondition-%s.csv", surveyTag));
					surveyResultDbSeedBuilder = new SurveyResultDbSeedBuilder(String.format("SurveyResult-%s.csv", surveyTag));
					measurementDbSeedBuilder = new MeasurementDbSeedBuilder(String.format("Measurement-%s.csv", surveyTag));
					gpsRawDbSeedBuilder = new GPSRawDbSeedBuilder(String.format("GPSRaw-%s.csv", surveyTag));
					anemometerRawDbSeedBuilder = new AnemometerRawDbSeedBuilder(String.format("AnemometerRaw-%s.csv", surveyTag));
					captureEventDbSeedBuilder = new CaptureEventDbSeedBuilder(String.format("CaptureEvent-%s.csv", surveyTag));
					fieldOfViewDbSeedBuilder = new FieldOfViewDbSeedBuilder(String.format("FieldOfView-%s.csv", surveyTag));
					peakDbSeedBuilder = new PeakDbSeedBuilder(String.format("Peak-%s.csv", surveyTag));
					segmentDbSeedBuilder = new SegmentDbSeedBuilder(String.format("Segment-%s.csv", surveyTag));
					noteDbSeedBuilder = new NoteDbSeedBuilder(String.format("Note-%s.csv", surveyTag));
					
					if (FileUtility.fileExists(surveyDbSeedBuilder.getSeedFilePath())) {
						executeSeed(connection, surveyDbSeedBuilder.build());
					}
					if (FileUtility.fileExists(surveyConditionDbSeedBuilder.getSeedFilePath())) {
						executeSeed(connection, surveyConditionDbSeedBuilder.build());
					}
					if (FileUtility.fileExists(surveyResultDbSeedBuilder.getSeedFilePath())) {
						executeSeed(connection, surveyResultDbSeedBuilder.build());
					}
					if (FileUtility.fileExists(measurementDbSeedBuilder.getSeedFilePath())) {
						executeSeed(connection, measurementDbSeedBuilder.build());
					}
					if (FileUtility.fileExists(gpsRawDbSeedBuilder.getSeedFilePath())) {
						executeSeed(connection, gpsRawDbSeedBuilder.build());
					}
					if (FileUtility.fileExists(anemometerRawDbSeedBuilder.getSeedFilePath())) {
						executeSeed(connection, anemometerRawDbSeedBuilder.build());
					}
					if (FileUtility.fileExists(captureEventDbSeedBuilder.getSeedFilePath())) {
						executeSeed(connection, captureEventDbSeedBuilder.build());
					}
					if (FileUtility.fileExists(fieldOfViewDbSeedBuilder.getSeedFilePath())) {
						executeSeed(connection, fieldOfViewDbSeedBuilder.build());
					}
					if (FileUtility.fileExists(peakDbSeedBuilder.getSeedFilePath())) {
						executeSeed(connection, peakDbSeedBuilder.build());
					}
					if (FileUtility.fileExists(segmentDbSeedBuilder.getSeedFilePath())) {
						executeSeed(connection, segmentDbSeedBuilder.build());
					}
					if (FileUtility.fileExists(noteDbSeedBuilder.getSeedFilePath())) {
						executeSeed(connection, noteDbSeedBuilder.build());
					}
					
		        } catch (Exception ex) {
		        	Log.error(String.format("EXCEPTION in executeSurveyDataSeed(), tag='%s'. ERROR: %s", surveyTag, ExceptionUtility.getStackTraceString(ex)));
		        } finally {
		            // cleanup seed builders.
		            if (surveyDbSeedBuilder!=null) { 
		            	surveyDbSeedBuilder.close(); 
	            	}
		            if (surveyConditionDbSeedBuilder!=null) { 
		            	surveyConditionDbSeedBuilder.close(); 
	            	}
		            if (surveyResultDbSeedBuilder!=null) { 
		            	surveyResultDbSeedBuilder.close(); 
	            	}
		            if (measurementDbSeedBuilder!=null) { 
		            	measurementDbSeedBuilder.close(); 
	            	}
		            if (gpsRawDbSeedBuilder!=null) { 
		            	gpsRawDbSeedBuilder.close(); 
	            	}
		            if (anemometerRawDbSeedBuilder!=null) { 
		            	anemometerRawDbSeedBuilder.close(); 
	            	}
		            if (captureEventDbSeedBuilder!=null) { 
		            	captureEventDbSeedBuilder.close(); 
	            	}
		            if (fieldOfViewDbSeedBuilder!=null) { 
		            	fieldOfViewDbSeedBuilder.close(); 
	            	}
		            if (peakDbSeedBuilder!=null) { 
	            		peakDbSeedBuilder.close(); 
            		}
		            if (segmentDbSeedBuilder!=null) { 
		            	segmentDbSeedBuilder.close(); 
	            	}
		            if (noteDbSeedBuilder!=null) { 
		            	noteDbSeedBuilder.close(); 
	            	}
		        }
			}
		} catch (Exception ex) {
        	Log.error(String.format("EXCEPTION in executeSurveyDataSeed() - %s", ExceptionUtility.getStackTraceString(ex)));
		} finally {
            connection.close();
		}
	}
	
	/* Methods for pushing GIS seed data (CustomerBoundaryType, CustomerMaterialType, Boundary and Asset) */
	
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
                		if (ENABLE_VERBOSE_LOGGING) {
                			Log.info(String.format("Executing statement -> '%s'", insertStmt));
                		}
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
