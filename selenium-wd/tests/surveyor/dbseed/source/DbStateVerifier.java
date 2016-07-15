package surveyor.dbseed.source;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import common.source.ExceptionUtility;
import common.source.Log;
import surveyor.dataaccess.source.Analyzer;
import surveyor.dataaccess.source.Customer;
import surveyor.dataaccess.source.Location;
import surveyor.dataaccess.source.SurveyorUnit;
import surveyor.dataaccess.source.User;
import static surveyor.scommon.source.SurveyorConstants.*;

public class DbStateVerifier {
	
	private Connection connection = null;
	
	public DbStateVerifier(Connection connection) {
		this.connection = connection;
	}
	
	/**
	 * Checks if DB has automation generic seed data.
	 * Remarks: 
	 * - Generic seed data has update checks before inserts. 
	 * - SQL statements in generic seed data are fairly safer to execute even if the seed data is already present in DB.
	 * @return
	 * @throws Exception
	 */
	public boolean isGenericSeedPresent() throws Exception {
		Log.method("DbStateVerifier.isGenericSeedPresent");
		// perform multiple checks to check if generic automation seed has to be executed or NOT.
		
		// Check if users required for automation are present in DB.
		User automationAdmin = User.getUser(PICDFADMIN);
		User sqacusdr = User.getUser(SQACUSDR);
		User sqacussu = User.getUser(SQACUSSU);
		User sqacusua = User.getUser(SQACUSUA);
		User sqapgedr = User.getUser(SQAPGEDR);
		User sqapicad = User.getUser(SQAPICAD);
		User sqapicdr = User.getUser(SQAPICDR);
		User sqapicsu = User.getUser(SQAPICSU);
		User sqapicsup = User.getUser(SQAPICSUP);
		User sqapicua = User.getUser(SQAPICUA);

		if (automationAdmin == null || sqacusdr == null || sqacussu == null || sqacusua == null || sqapgedr == null 
				|| sqapicad == null || sqapicdr == null || sqapicsu == null || sqapicsup == null || sqapicua == null) {
			return false;
		}
		
		// Check if customers required for automation are present in DB.
		Customer picCust = Customer.getCustomer(CUSTOMER_PICARRO);
		Customer sqaCusCust = Customer.getCustomer(CUSTOMER_SQACUS);
		Customer sqaTestCust = Customer.getCustomer(CUSTOMER_SQATEST);
		Customer pgeCust = Customer.getCustomer(CUSTOMER_PGE);

		if (picCust == null || sqaCusCust == null || sqaTestCust == null || pgeCust == null) {
			return false;
		}
		
		// Check if locations required for automation are present in DB.
		Location santaClaraLoc = Location.getLocation(SQACUSSULOC);
		Location sqaCusLoc = Location.getLocation(SQACUSLOC);
		Location pgeScLoc = Location.getLocation(PGESCLOC);
		Location sqaTestloc = Location.getLocation(SQATESTLOC);
		
		if (santaClaraLoc == null || sqaCusLoc == null || pgeScLoc == null || sqaTestloc == null) {
			return false;
		}

		// Check if analyzers required for automation are present in DB.
		Analyzer fdds2037 = Analyzer.getAnalyzerBySerialNumber(SQACUSLOCANZ);
		Analyzer fdds2038 = Analyzer.getAnalyzerBySerialNumber(SQAPICLOC4SURANA);
		Analyzer fdds2037_2 = Analyzer.getAnalyzerBySerialNumber(SQACUSLOCANZ2);
		Analyzer feds2015 = Analyzer.getAnalyzerBySerialNumber(FEDS2015ANZ);
		Analyzer fdds2037_1 = Analyzer.getAnalyzerBySerialNumber(SQACUSLOCANZ1);
		Analyzer simautoAnalyzer1 = Analyzer.getAnalyzerBySerialNumber(SIMAUTO_ANALYZER1);
		Analyzer simautoAnalyzer2 = Analyzer.getAnalyzerBySerialNumber(SIMAUTO_ANALYZER2);
		Analyzer simautoAnalyzer3 = Analyzer.getAnalyzerBySerialNumber(SIMAUTO_ANALYZER3);

		if (fdds2037 == null || fdds2038 == null || fdds2037_2 == null || feds2015 == null || fdds2037_1 == null 
				|| simautoAnalyzer1 == null || simautoAnalyzer2 == null || simautoAnalyzer3 == null) {
			return false;
		}
		
		// Check if surveyors required for automation are present in DB.
		SurveyorUnit softwarecar_2037_picarro = SurveyorUnit.getSurveyorUnit(SURVEYOR_SOFTWARECAR2037PIC);
		SurveyorUnit softwarecar_2037_cust = SurveyorUnit.getSurveyorUnit(SURVEYOR_SOFTWARECAR2037CUST);
		SurveyorUnit pgefeds2015 = SurveyorUnit.getSurveyorUnit(SURVEYOR_PGEFEDS2015);
		SurveyorUnit softwarecar_2037_testcust = SurveyorUnit.getSurveyorUnit(SURVEYOR_SOFTWARECAR2037TESTCUST);
		SurveyorUnit softwareCar = SurveyorUnit.getSurveyorUnit(SQAPICLOC4SUR);
		SurveyorUnit simautoSurveyor1 = SurveyorUnit.getSurveyorUnit(SIMAUTO_SURVEYOR1);
		SurveyorUnit simautoSurveyor2 = SurveyorUnit.getSurveyorUnit(SIMAUTO_SURVEYOR2);
		SurveyorUnit simautoSurveyor3 = SurveyorUnit.getSurveyorUnit(SIMAUTO_SURVEYOR3);
		SurveyorUnit whiteDodge = SurveyorUnit.getSurveyorUnit(SQACUSLOCSUR);

		if (softwarecar_2037_picarro == null || softwarecar_2037_cust == null || pgefeds2015 == null || softwarecar_2037_testcust == null 
				|| softwareCar == null || simautoSurveyor1 == null || simautoSurveyor2 == null || simautoSurveyor3 == null || whiteDodge == null) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Checks if DB has GIS seed data for the specified customer.
	 * @param customerId
	 * @param expectedAssetCount
	 * @param expectedBoundaryCount
	 * @return
	 */
	public boolean isGISSeedPresent(String customerId, Integer expectedAssetCount, Integer expectedBoundaryCount) {
		Log.method("DbStateVerifier.isGISSeedPresent", customerId, expectedAssetCount, expectedBoundaryCount);
		// check if GIS seed data is already present in the database.
		try (Statement stmt = connection.createStatement())  
        {  
        	// Check if the assets and boundary counts match.  
            
			// check assets count.
			long count = 0;  
        	String tableName = AssetDbSeedBuilder.TABLE_NAME;
            try (ResultSet rsRowCount = stmt.executeQuery(String.format("SELECT COUNT(*) FROM %s WHERE CustomerId='%s';", tableName, customerId)))  
            {  
                rsRowCount.next();  
                count = rsRowCount.getInt(1);  
                Log.info(String.format("[%s] table row count for Customer[Id=%s] = %d", tableName, customerId, count));
                Log.info(String.format("Expected %s row count=%d, Actual row count=%d", tableName, expectedAssetCount, count));
                if (expectedAssetCount != count) {
                	Log.info("isGISSeedPresent = FALSE");
                	return false;
                }
            } 
            
            // check boundaries count.
            count = 0;  
        	tableName = BoundaryDbSeedBuilder.TABLE_NAME;
            try (ResultSet rsRowCount = stmt.executeQuery(String.format("SELECT COUNT(*) FROM %s WHERE CustomerId='%s';", tableName, customerId)))  
            {  
                rsRowCount.next();  
                count = rsRowCount.getInt(1);  
                Log.info(String.format("[%s] table row count for Customer[Id=%s] = %d", tableName, customerId, count));  
                Log.info(String.format("Expected %s row count=%d, Actual row count=%d", tableName, expectedBoundaryCount, count));
                if (expectedBoundaryCount != count) {
                	Log.info("isGISSeedPresent = FALSE");
                	return false;
                }
            } 
        }
        catch (Exception e)  
        {  
            Log.error(String.format("EXCEPTION in isGISSeedPresent(). ERROR Message: %s", ExceptionUtility.getStackTraceString(e)));  
        }  	

		return true;
	}

	public boolean isSurveySeedPresent(String surveyId, String analyzerId, String startEpoch, String endEpoch, Integer minSurveyCount, 
			Integer minSurveyConditionCount, Integer minMeasurementCount, Integer minAnemometerRawCount, Integer minGPSRawCount, Integer minNotesCount) {
		Log.method("DbStateVerifier.isSurveySeedPresent", surveyId, minSurveyCount, minSurveyConditionCount, 
				minMeasurementCount, minAnemometerRawCount, minGPSRawCount, minNotesCount);
		// check if survey seed data is already present in the database.
		try (Statement stmt = connection.createStatement())  
        {  
        	// Check if survey, surveyCondition, measurement, anemometerRaw, gpsRaw, notes counts match.  
			
			// check survey count.
			long count = 0;  
        	String tableName = SurveyDbSeedBuilder.TABLE_NAME;
            try (ResultSet rsRowCount = stmt.executeQuery(String.format("SELECT COUNT(*) FROM %s WHERE Id='%s';", tableName, surveyId)))  
            {  
                rsRowCount.next();  
                count = rsRowCount.getInt(1);  
                Log.info(String.format("[%s] table row count for Survey[Id=%s] = %d", tableName, surveyId, count));
                Log.info(String.format("Expected %s row count=%d, Actual row count=%d", tableName, minSurveyCount, count));
                if (minSurveyCount > count) {
                	Log.info("isSurveySeedPresent = FALSE");
                	return false;
                }
            } 
            
            // check survey condition count.
            count = 0;  
        	tableName = SurveyConditionDbSeedBuilder.TABLE_NAME;
            try (ResultSet rsRowCount = stmt.executeQuery(String.format("SELECT COUNT(*) FROM %s WHERE SurveyId='%s';", tableName, surveyId)))  
            {  
                rsRowCount.next();  
                count = rsRowCount.getInt(1);  
                Log.info(String.format("[%s] table row count for Survey[Id=%s] = %d", tableName, surveyId, count));  
                Log.info(String.format("Expected %s row count=%d, Actual row count=%d", tableName, minSurveyConditionCount, count));
                if (minSurveyConditionCount > count) {
                	Log.info("isSurveySeedPresent = FALSE");
                	return false;
                }
            } 

            // check measurement count.
            count = 0;  
        	tableName = MeasurementDbSeedBuilder.TABLE_NAME;
            try (ResultSet rsRowCount = stmt.executeQuery(String.format("SELECT COUNT(*) FROM %s WHERE AnalyzerId='%s' AND EpochTime>=%s AND EpochTime<=%s;", 
            		tableName, analyzerId, startEpoch, endEpoch)))  
            {  
                rsRowCount.next();  
                count = rsRowCount.getInt(1);  
                Log.info(String.format("[%s] table row count for Survey[Id=%s] = %d", tableName, surveyId, count));
                Log.info(String.format("Expected %s row count=%d, Actual row count=%d", tableName, minMeasurementCount, count));
                if (minAnemometerRawCount > count) {
                	Log.info("isSurveySeedPresent = FALSE");
                	return false;
                }
            } 

            // check anemometer raw count.
            count = 0;  
        	tableName = AnemometerRawDbSeedBuilder.TABLE_NAME;
            try (ResultSet rsRowCount = stmt.executeQuery(String.format("SELECT COUNT(*) FROM %s WHERE AnalyzerId='%s' AND EpochTime>=%s AND EpochTime<=%s;", 
            		tableName, analyzerId, startEpoch, endEpoch)))  
            {  
                rsRowCount.next();  
                count = rsRowCount.getInt(1);  
                Log.info(String.format("[%s] table row count for Survey[Id=%s] = %d", tableName, surveyId, count));
                Log.info(String.format("Expected %s row count=%d, Actual row count=%d", tableName, minAnemometerRawCount, count));
                if (minAnemometerRawCount > count) {
                	Log.info("isSurveySeedPresent = FALSE");
                	return false;
                }
            } 
            
            // check gps raw count.
            count = 0;  
        	tableName = GPSRawDbSeedBuilder.TABLE_NAME;
            try (ResultSet rsRowCount = stmt.executeQuery(String.format("SELECT COUNT(*) FROM %s WHERE AnalyzerId='%s' AND EpochTime>=%s AND EpochTime<=%s;", 
            		tableName, analyzerId, startEpoch, endEpoch)))  
            {  
                rsRowCount.next();  
                count = rsRowCount.getInt(1);  
                Log.info(String.format("[%s] table row count for Survey[Id=%s] = %d", tableName, surveyId, count));
                Log.info(String.format("Expected %s row count=%d, Actual row count=%d", tableName, minGPSRawCount, count));
                if (minGPSRawCount > count) {
                	Log.info("isSurveySeedPresent = FALSE");
                	return false;
                }
            } 

            // check notes count.
            count = 0;  
        	tableName = NoteDbSeedBuilder.TABLE_NAME;
            try (ResultSet rsRowCount = stmt.executeQuery(String.format("SELECT COUNT(*) FROM %s WHERE AnalyzerId='%s' AND EpochTime>=%s AND EpochTime<=%s;", 
            		tableName, analyzerId, startEpoch, endEpoch)))  
            {  
                rsRowCount.next();  
                count = rsRowCount.getInt(1);  
                Log.info(String.format("[%s] table row count for Survey[Id=%s] = %d", tableName, surveyId, count));
                Log.info(String.format("Expected %s row count=%d, Actual row count=%d", tableName, minNotesCount, count));
                if (minNotesCount > count) {
                	Log.info("isSurveySeedPresent = FALSE");
                	return false;
                }
            } 
        }
        catch (Exception e)  
        {  
            Log.error(String.format("EXCEPTION in isSurveySeedPresent(). ERROR Message: %s", ExceptionUtility.getStackTraceString(e)));  
        }  	

		return true;
	}
}
