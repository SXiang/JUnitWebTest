package surveyor.dbseed.source;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerBulkCopy;

import common.source.CSVUtility;
import common.source.ExceptionUtility;
import common.source.FileUtility;
import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.dataaccess.source.ConnectionFactory;
import surveyor.dataaccess.source.Customer;
import surveyor.dataaccess.source.SqlCmdUtility;

import static surveyor.scommon.source.SurveyorConstants.CUSTOMER_PICARRO;
import static surveyor.scommon.source.SurveyorConstants.EPSILON;

public class DbSeedExecutor {

	private static final boolean ENABLE_VERBOSE_LOGGING = false;

	/* Method to push all the seed data required for automation. */
	
	public static void executeAllDataSeed() throws Exception {
		DbSeedExecutor.executeGenericDataSeed();
		DbSeedExecutor.executeGisSeed();
		DbSeedExecutor.executeSurveyDataSeed();
	}
	
	/* Method for pushing generic automation seed data (Users, Customers, Locations, Analyzers, etc.) */

	public static void executeGenericDataSeed() throws Exception {
		Log.method("DbSeedExecutor.executeGenericDataSeed");
		Connection connection = null;
		try {
			connection = ConnectionFactory.createConnection();
			DbStateVerifier dbStateVerifier = new DbStateVerifier(connection);
			if (dbStateVerifier.isGenericSeedPresent()) {
				Log.info("Automation DB seed is already present. SKIP execution.");
				return;
			}
			
			Log.info("Automation DB seed NOT found. Executing SQL script to push automation DB seed...");
			String sqlCmdLogFilePath = Paths.get(TestSetup.getRootPath(),"logs", String.format("sqlcmd-%s.log", TestSetup.getUUIDString())).toString();
			String sqlFileFullPath = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data", "sql", "AutomationSeedScript-Minimal.sql").toString();
			SqlCmdUtility.executeSQLFile(TestContext.INSTANCE.getDbIpAddress(), TestContext.INSTANCE.getDbPortNo(), TestContext.INSTANCE.getDbName(), 
					TestContext.INSTANCE.getDbUser(), TestContext.INSTANCE.getDbPassword(), sqlFileFullPath, sqlCmdLogFilePath);
		} finally {
			connection.close();
		}
	}
	
	/* Method for pushing Survey seed data */

	public static void executeSurveyDataSeed() throws Exception {
		Log.method("DbSeedExecutor.executeSurveyDataSeed");
		Connection connection = null;
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

		// NOTE: "stnd-sqacudr-3","man-pic-2","rr-sqacudr-1","EthaneStnd" survey NOT included. 
		// SurveyResult entry does NOT get inserted using INSERT statements. Tracked by DE2178.
		final String[] surveyTags = {"stnd-pic","rr-pic","man-pic-1","op-pic",
				"stnd-sqacudr","stnd-sqacudr-1","stnd-sqacudr-2","rr-sqacudr-2","op-sqacudr"};

		try {
			connection = ConnectionFactory.createConnection();
			for (String surveyTag : surveyTags) {
				try
		        {
					Log.info(String.format("***** START Processing survey with tag - '%s' *****", surveyTag));
					
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
					
					// check if survey data is present in database for this survey tag.
					final String surveyCsvFilePath = surveyDbSeedBuilder.getSeedFilePath();
					final DbStateVerifier dbStateVerifier = new DbStateVerifier(connection);
					
					// get line count from data files (-2 for header and last empty line) 
					final Integer minSurveyCount = FileUtility.getLineCountInFile(Paths.get(surveyDbSeedBuilder.getSeedFilePath())) - 2;
					final Integer minSurveyConditionCount = FileUtility.getLineCountInFile(Paths.get(surveyConditionDbSeedBuilder.getSeedFilePath())) - 2;
					final Integer minSurveyResultCount = FileUtility.getLineCountInFile(Paths.get(surveyResultDbSeedBuilder.getSeedFilePath())) - 2;
					final Integer minMeasurementCount = FileUtility.getLineCountInFile(Paths.get(measurementDbSeedBuilder.getSeedFilePath())) - 2;
					final Integer minGPSRawCount = FileUtility.getLineCountInFile(Paths.get(gpsRawDbSeedBuilder.getSeedFilePath())) - 2;
					final Integer minAnemometerRawCount = FileUtility.getLineCountInFile(Paths.get(anemometerRawDbSeedBuilder.getSeedFilePath())) - 2;
					final Integer minCaptureEventCount = FileUtility.getLineCountInFile(Paths.get(captureEventDbSeedBuilder.getSeedFilePath())) - 2;
					final Integer minFieldOfViewCount = FileUtility.getLineCountInFile(Paths.get(fieldOfViewDbSeedBuilder.getSeedFilePath())) - 2;
					final Integer minPeakCount = FileUtility.getLineCountInFile(Paths.get(peakDbSeedBuilder.getSeedFilePath())) - 2;
					final Integer minSegmentCount = FileUtility.getLineCountInFile(Paths.get(segmentDbSeedBuilder.getSeedFilePath())) - 2;
					final Integer minNoteCount = FileUtility.getLineCountInFile(Paths.get(noteDbSeedBuilder.getSeedFilePath())) - 2;
					
					final List<HashMap<String, String>> firstSurveyRow = new CSVUtility().getTopRows(surveyCsvFilePath, 1);
					final String surveyId = firstSurveyRow.get(0).get("Id");
					final String analyzerId = firstSurveyRow.get(0).get("AnalyzerId");
					
					String startEpoch = firstSurveyRow.get(0).get("StartEpoch");
					String endEpoch = firstSurveyRow.get(0).get("EndEpoch");
					Double startEpochValue = Double.parseDouble(startEpoch) - EPSILON;
					Double endEpochValue = Double.parseDouble(endEpoch) + EPSILON;
					startEpoch = startEpochValue.toString(); 
					endEpoch = endEpochValue.toString(); 

					// check and execute Survey DB seed.
					if (dbStateVerifier.isSurveySeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minSurveyCount)) {
						Log.info(String.format("Survey DB seed is already present for this survey-'%s'. SKIP execution.", surveyId));
					} else {
						if (FileUtility.fileExists(surveyDbSeedBuilder.getSeedFilePath())) {
							executeSeed(connection, surveyDbSeedBuilder.build());;
						}
					}

					// check and execute SurveyCondition DB seed.
					if (dbStateVerifier.isSurveyConditionSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minSurveyConditionCount)) {
						Log.info(String.format("SurveyCondition DB seed is already present for this survey-'%s'. SKIP execution.", surveyId));
					} else {
						if (FileUtility.fileExists(surveyConditionDbSeedBuilder.getSeedFilePath())) {
							executeSeed(connection, surveyConditionDbSeedBuilder.build());;
						}
					}

					// check and execute SurveyResult DB seed.
					if (dbStateVerifier.isSurveyResultSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minSurveyResultCount)) {
						Log.info(String.format("SurveyResult DB seed is already present for this survey-'%s'. SKIP execution.", surveyId));
					} else {
						if (FileUtility.fileExists(surveyResultDbSeedBuilder.getSeedFilePath())) {
							executeSeed(connection, surveyResultDbSeedBuilder.build());;
						}
					}

					// check and execute Measurement DB seed.
					if (dbStateVerifier.isMeasurementSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minMeasurementCount)) {
						Log.info(String.format("Measurement DB seed is already present for this survey-'%s'. SKIP execution.", surveyId));
					} else {
						if (FileUtility.fileExists(measurementDbSeedBuilder.getSeedFilePath())) {
							executeSeed(connection, measurementDbSeedBuilder.build());;
						}
					}

					// check and execute GPSRaw DB seed.
					if (dbStateVerifier.isGPSRawSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minGPSRawCount)) {
						Log.info(String.format("GPSRaw DB seed is already present for this survey-'%s'. SKIP execution.", surveyId));
					} else {
						if (FileUtility.fileExists(gpsRawDbSeedBuilder.getSeedFilePath())) {
							executeSeed(connection, gpsRawDbSeedBuilder.build());;
						}
					}

					// check and execute AnemometerRaw DB seed.
					if (dbStateVerifier.isAnemometerRawSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minAnemometerRawCount)) {
						Log.info(String.format("AnemometerRaw DB seed is already present for this survey-'%s'. SKIP execution.", surveyId));
					} else {
						if (FileUtility.fileExists(anemometerRawDbSeedBuilder.getSeedFilePath())) {
							executeSeed(connection, anemometerRawDbSeedBuilder.build());;
						}
					}

					// check and execute CaptureEvent DB seed.
					if (dbStateVerifier.isCaptureEventSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minCaptureEventCount)) {
						Log.info(String.format("CaptureEvent DB seed is already present for this survey-'%s'. SKIP execution.", surveyId));
					} else {
						if (FileUtility.fileExists(captureEventDbSeedBuilder.getSeedFilePath())) {
							executeSeed(connection, captureEventDbSeedBuilder.build());;
						}
					}

					// check and execute FieldOfView DB seed.
					if (dbStateVerifier.isFieldOfViewSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minFieldOfViewCount)) {
						Log.info(String.format("FieldOfView DB seed is already present for this survey-'%s'. SKIP execution.", surveyId));
					} else {
						if (FileUtility.fileExists(fieldOfViewDbSeedBuilder.getSeedFilePath())) {
							executeSeed(connection, fieldOfViewDbSeedBuilder.build());;
						}
					}

					// check and execute Peak DB seed.
					if (dbStateVerifier.isPeakSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minPeakCount)) {
						Log.info(String.format("Peak DB seed is already present for this survey-'%s'. SKIP execution.", surveyId));
					} else {
						if (FileUtility.fileExists(peakDbSeedBuilder.getSeedFilePath())) {
							executeSeed(connection, peakDbSeedBuilder.build());;
						}
					}

					// check and execute Segment DB seed.
					if (dbStateVerifier.isSegmentSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minSegmentCount)) {
						Log.info(String.format("Segment DB seed is already present for this survey-'%s'. SKIP execution.", surveyId));
					} else {
						if (FileUtility.fileExists(segmentDbSeedBuilder.getSeedFilePath())) {
							executeSeed(connection, segmentDbSeedBuilder.build());;
						}
					}

					// check and execute Note DB seed.
					if (dbStateVerifier.isNoteSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minNoteCount)) {
						Log.info(String.format("Note DB seed is already present for this survey-'%s'. SKIP execution.", surveyId));
					} else {
						if (FileUtility.fileExists(noteDbSeedBuilder.getSeedFilePath())) {
							executeSeed(connection, noteDbSeedBuilder.build());;
						}
					}

					Log.info(String.format("----- Done processing survey with tag - '%s' -----", surveyTag));
					
		        } catch (Exception ex) {
		        	Log.error(String.format("EXCEPTION in executeSurveyDataSeed(), tag='%s'. ERROR: %s", surveyTag, ExceptionUtility.getStackTraceString(ex)));
		        } finally {
		            // cleanup seed builders.
		            closeDbSeedBuilder(surveyDbSeedBuilder);
		            closeDbSeedBuilder(surveyConditionDbSeedBuilder);
		            closeDbSeedBuilder(surveyResultDbSeedBuilder);
		            closeDbSeedBuilder(measurementDbSeedBuilder);
		            closeDbSeedBuilder(gpsRawDbSeedBuilder);
		            closeDbSeedBuilder(anemometerRawDbSeedBuilder);
		            closeDbSeedBuilder(captureEventDbSeedBuilder);
		            closeDbSeedBuilder(fieldOfViewDbSeedBuilder);
		            closeDbSeedBuilder(peakDbSeedBuilder);
		            closeDbSeedBuilder(segmentDbSeedBuilder);
		            closeDbSeedBuilder(noteDbSeedBuilder);
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
		Log.method("DbSeedExecutor.executeGisSeed");
		executeGisSeed(null /*customerId*/);
	}
	
	public static void executeGisSeed(String customerId) throws Exception {
		Log.method("DbSeedExecutor.executeGisSeed", customerId);
		boolean isCustomerSpecified = true;
		if (customerId == null) {
			isCustomerSpecified = false;
			Customer customer = Customer.getCustomer(CUSTOMER_PICARRO);
			customerId = customer.getId();
		}
		 
		Connection connection = null;
		DbSeedBuilderCache dbSeedBuilderCache = new DbSeedBuilderCache(); 
		CustomerBoundaryTypeDbSeedBuilder customerBoundaryTypeDbSeedBuilder = null;
		CustomerMaterialTypeDbSeedBuilder customerMaterialTypeDbSeedBuilder = null;
		BoundaryDbSeedBuilder boundaryDbSeedBuilder = null;
		AssetDbSeedBuilder assetDbSeedBuilder = null;
		
		try
        {
			connection = ConnectionFactory.createConnection();
        	customerBoundaryTypeDbSeedBuilder = new CustomerBoundaryTypeDbSeedBuilder();
        	customerBoundaryTypeDbSeedBuilder.setDbSeedCache(dbSeedBuilderCache);
        	
        	customerMaterialTypeDbSeedBuilder = new CustomerMaterialTypeDbSeedBuilder();
        	customerMaterialTypeDbSeedBuilder.setDbSeedCache(dbSeedBuilderCache);        	
        	
        	boundaryDbSeedBuilder = new BoundaryDbSeedBuilder();
        	boundaryDbSeedBuilder.setDbSeedCache(dbSeedBuilderCache);

        	assetDbSeedBuilder = new AssetDbSeedBuilder();
        	assetDbSeedBuilder.setDbSeedCache(dbSeedBuilderCache);
        	
			DbSeed assetDbSeed = assetDbSeedBuilder.build(customerId);
			DbSeed boundaryDbSeed = boundaryDbSeedBuilder.build(customerId);
			int expectedAssetCount = assetDbSeed.getInsertStatements().size();
			int expectedBoundaryCount = boundaryDbSeed.getInsertStatements().size();
			
			// check if GIS seed is present in database for this customer.
			DbStateVerifier dbStateVerifier = new DbStateVerifier(connection);
			if (dbStateVerifier.isGISSeedPresent(customerId, expectedAssetCount, expectedBoundaryCount)) {
				Log.info(String.format("GIS DB seed is already present for customer-'%s'. SKIP execution.", customerId));
				return;
			}
			
			DbSeed custBoundaryTypeDbSeed = customerBoundaryTypeDbSeedBuilder.build(isCustomerSpecified ? customerId : null);
			DbSeed custMaterialTypeDbSeed = customerMaterialTypeDbSeedBuilder.build(isCustomerSpecified ? customerId : null);

			executeSeed(connection, custBoundaryTypeDbSeed);
			executeSeed(connection, custMaterialTypeDbSeed);
			executeSeed(connection, boundaryDbSeed);
			executeSeed(connection, assetDbSeed);
        	
        } catch (Exception ex) {
        	Log.error(String.format("EXCEPTION in executeGisSeed() - %s", ExceptionUtility.getStackTraceString(ex)));
        } finally {
            connection.close();
            // cleanup seed builders.
            closeDbSeedBuilder(customerBoundaryTypeDbSeedBuilder);
            closeDbSeedBuilder(customerMaterialTypeDbSeedBuilder);
            closeDbSeedBuilder(boundaryDbSeedBuilder);
            closeDbSeedBuilder(assetDbSeedBuilder);
        }
	}

	private static void closeDbSeedBuilder(BaseDbSeedBuilder dbSeedBuilder) {
		if (dbSeedBuilder!=null) { 
			dbSeedBuilder.close(); 
		}
	}
	
	private static void executeSeed(Connection connection, DbSeed dbSeedData) {
		Log.method("DbSeedExecutor.executeSeed", connection, dbSeedData);
        String executingInsertStatement = null;
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
                    Log.info(String.format("%s Table: Starting row count = %d", dbSeedData.getDestinationTableName(), countStart));  
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
                		executingInsertStatement = insertStmt;
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
                    Log.info(String.format("%s Table: Ending row count = %d", dbSeedData.getDestinationTableName(), countEnd));  
                    Log.info(String.format("%s Table: %d rows were added.", dbSeedData.getDestinationTableName(), (countEnd - countStart)));  
                }    
            }  
        }  
        catch (Exception e)  
        {  
        	if (executingInsertStatement != null) {
        		Log.error(String.format("Executing Insert Statement is: %s", executingInsertStatement));
        	}
        	Log.error(String.format("EXCEPTION Message: %s", ExceptionUtility.getStackTraceString(e)));  
        }  	
	}
}
