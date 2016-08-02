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
	 * Checks if DB has automation generic seed data. Remarks: - Generic seed
	 * data has update checks before inserts. - SQL statements in generic seed
	 * data are fairly safer to execute even if the seed data is already present
	 * in DB.
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean isGenericSeedPresent() throws Exception {
		Log.method("DbStateVerifier.isGenericSeedPresent");
		// perform multiple checks to check if generic automation seed has to be
		// executed or NOT.

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

		if (softwarecar_2037_picarro == null || softwarecar_2037_cust == null || pgefeds2015 == null
				|| softwarecar_2037_testcust == null || softwareCar == null || simautoSurveyor1 == null
				|| simautoSurveyor2 == null || simautoSurveyor3 == null || whiteDodge == null) {
			return false;
		}

		return true;
	}

	/**
	 * Checks if DB has GIS seed data for the specified customer.
	 * 
	 * @param customerId
	 * @param expectedAssetCount
	 * @param expectedBoundaryCount
	 * @return
	 */
	public boolean isGISSeedPresent(String customerId, Integer expectedAssetCount, Integer expectedBoundaryCount) {
		Log.method("DbStateVerifier.isGISSeedPresent", customerId, expectedAssetCount, expectedBoundaryCount);
		// check if GIS seed data is already present in the database.
		try (Statement stmt = connection.createStatement()) {
			// Check if the assets and boundary counts match.

			// check assets count.
			long count = 0;
			String tableName = AssetDbSeedBuilder.TABLE_NAME;
			String selectQuery = String.format("SELECT COUNT(*) FROM %s WHERE CustomerId='%s';", tableName, customerId);
			Log.info("Checking Asset seed data ...");
			Log.info(String.format("Select query is : %s", selectQuery));
			try (ResultSet rsRowCount = stmt.executeQuery(
					selectQuery)) {
				rsRowCount.next();
				count = rsRowCount.getInt(1);
				Log.info(String.format("%s table row count for Customer[Id=%s] = %d", tableName, customerId, count));
				Log.info(String.format("Expected %s row count=%d, Actual row count=%d", tableName, expectedAssetCount,
						count));
				if (expectedAssetCount != count) {
					Log.info("isGISSeedPresent = FALSE");
					return false;
				}
			}

			// check boundaries count.
			count = 0;
			tableName = BoundaryDbSeedBuilder.TABLE_NAME;
			selectQuery = String.format("SELECT COUNT(*) FROM %s WHERE CustomerId='%s';", tableName, customerId);
			Log.info("Checking Boundary seed data ...");
			Log.info(String.format("Select query is : %s", selectQuery));
			try (ResultSet rsRowCount = stmt.executeQuery(
					selectQuery)) {
				rsRowCount.next();
				count = rsRowCount.getInt(1);
				Log.info(String.format("%s table row count for Customer[Id=%s] = %d", tableName, customerId, count));
				Log.info(String.format("Expected %s row count=%d, Actual row count=%d", tableName,
						expectedBoundaryCount, count));
				if (expectedBoundaryCount != count) {
					Log.info("isGISSeedPresent = FALSE");
					return false;
				}
			}
		} catch (Exception e) {
			Log.error(String.format("EXCEPTION in isGISSeedPresent(). ERROR Message: %s",
					ExceptionUtility.getStackTraceString(e)));
		}

		return true;
	}

	/**
	 * Verified if CustomerBoundaryType seed is present for the specified customer in database or not.
	 * 
	 * @param customerId
	 *            - Customer Id
	 * @param expectedCustomerBoundaryTypeCount
	 *            - Expected rows for Customer Boundary Type for the specified customer.
	 * @return - True/False. Whether CustomerBoundaryType seed is present in database.
	 */
	public boolean isGISCustomerBoundaryTypeSeedPresent(String customerId, Integer expectedCustomerBoundaryTypeCount) {
		Log.method("DbStateVerifier.isGISCustomerBoundarySeedPresent", customerId, expectedCustomerBoundaryTypeCount);
		// check if GIS CustomerBoundarySeedPresent seed data is already present in the database.
		try (Statement stmt = connection.createStatement()) {
			// check CustomerBoundaryType count.
			long count = 0;
			String tableName = CustomerBoundaryTypeDbSeedBuilder.TABLE_NAME;
			String selectQuery = String.format("SELECT COUNT(*) FROM %s WHERE CustomerId='%s';", tableName, customerId);
			Log.info(String.format("Select query is : %s", selectQuery));
			try (ResultSet rsRowCount = stmt
					.executeQuery(selectQuery)) {
				rsRowCount.next();
				count = rsRowCount.getInt(1);
				Log.info(String.format("%s table row count for Customer[Id=%s] = %d", tableName, customerId,
						count));
				Log.info(String.format("Expected %s row count=%d, Actual row count=%d", tableName,
						expectedCustomerBoundaryTypeCount, count));
				if (expectedCustomerBoundaryTypeCount > count) {
					Log.info("isGISCustomerBoundaryTypeSeedPresent = FALSE");
					return false;
				}
			}
		} catch (Exception e) {
			Log.error(String.format("EXCEPTION in isGISCustomerBoundaryTypeSeedPresent(). ERROR Message: %s",
					ExceptionUtility.getStackTraceString(e)));
		}

		return true;
	}
	
	/**
	 * Verified if CustomerMaterialType seed is present for the specified customer in database or not.
	 * 
	 * @param customerId
	 *            - Customer Id
	 * @param expectedCustomerMaterialTypeCount
	 *            - Expected rows for Customer Material Type for the specified customer.
	 * @return - True/False. Whether CustomerMaterialType seed is present in database.
	 */
	public boolean isGISCustomerMaterialTypeSeedPresent(String customerId, Integer expectedCustomerMaterialTypeCount) {
		Log.method("DbStateVerifier.isGISCustomerBoundarySeedPresent", customerId, expectedCustomerMaterialTypeCount);
		// check if GIS CustomerBoundarySeedPresent seed data is already present in the database.
		try (Statement stmt = connection.createStatement()) {
			// check CustomerMaterialType count.
			long count = 0;
			String tableName = CustomerMaterialTypeDbSeedBuilder.TABLE_NAME;
			String selectQuery = String.format("SELECT COUNT(*) FROM %s WHERE CustomerId='%s';", tableName, customerId);
			Log.info(String.format("Select query is : %s", selectQuery));
			try (ResultSet rsRowCount = stmt
					.executeQuery(selectQuery)) {
				rsRowCount.next();
				count = rsRowCount.getInt(1);
				Log.info(String.format("%s table row count for Customer[Id=%s] = %d", tableName, customerId,
						count));
				Log.info(String.format("Expected %s row count=%d, Actual row count=%d", tableName,
						expectedCustomerMaterialTypeCount, count));
				if (expectedCustomerMaterialTypeCount > count) {
					Log.info("isGISCustomerMaterialTypeSeedPresent = FALSE");
					return false;
				}
			}
		} catch (Exception e) {
			Log.error(String.format("EXCEPTION in isGISCustomerMaterialTypeSeedPresent(). ERROR Message: %s",
					ExceptionUtility.getStackTraceString(e)));
		}

		return true;
	}
	
	/**
	 * Verified if Survey seed is present in database or not.
	 * 
	 * @param surveyId
	 *            - Survey Id
	 * @param analyzerId
	 *            - Analyzer Id
	 * @param startEpoch
	 *            - Start Epoch
	 * @param endEpoch
	 *            - End Epoch
	 * @param minNoteCount
	 *            - Minimum number of Survey rows in database.
	 * @return - True/False. Whether Survey seed is present in database.
	 */
	public boolean isSurveySeedPresent(String surveyId, String analyzerId, String startEpoch, String endEpoch,
			Integer minSurveyCount) {
		Log.method("DbStateVerifier.isSurveySeedPresent", surveyId, minSurveyCount);
		// check if survey seed data is already present in the database.
		try (Statement stmt = connection.createStatement()) {
			// check survey count.
			long count = 0;
			String tableName = SurveyDbSeedBuilder.TABLE_NAME;
			String selectQuery = String.format("SELECT COUNT(*) FROM %s WHERE Id='%s';", tableName, surveyId);
			Log.info(String.format("Select query is : %s", selectQuery));
			try (ResultSet rsRowCount = stmt
					.executeQuery(selectQuery)) {
				rsRowCount.next();
				count = rsRowCount.getInt(1);
				Log.info(String.format("%s table row count for Survey[Id=%s] = %d", tableName, surveyId, count));
				Log.info(String.format("Expected %s row count=%d, Actual row count=%d", tableName, minSurveyCount,
						count));
				if (minSurveyCount > count) {
					Log.info("isSurveySeedPresent = FALSE");
					return false;
				}
			}
		} catch (Exception e) {
			Log.error(String.format("EXCEPTION in isSurveySeedPresent(). ERROR Message: %s",
					ExceptionUtility.getStackTraceString(e)));
		}

		return true;
	}

	/**
	 * Verified if SurveyCondition seed is present in database or not.
	 * 
	 * @param surveyId
	 *            - Survey Id
	 * @param analyzerId
	 *            - Analyzer Id
	 * @param startEpoch
	 *            - Start Epoch
	 * @param endEpoch
	 *            - End Epoch
	 * @param minNoteCount
	 *            - Minimum number of SurveyCondition rows in database.
	 * @return - True/False. Whether SurveyCondition seed is present in
	 *         database.
	 */
	public boolean isSurveyConditionSeedPresent(String surveyId, String analyzerId, String startEpoch, String endEpoch,
			Integer minSurveyConditionCount) {
		Log.method("DbStateVerifier.isSurveyConditionSeedPresent", surveyId, minSurveyConditionCount);
		// check if surveyCondition seed data is already present in the database.
		try (Statement stmt = connection.createStatement()) {
			// check surveyCondition count.
			long count = 0;
			String tableName = SurveyConditionDbSeedBuilder.TABLE_NAME;
			String selectQuery = String.format("SELECT COUNT(*) FROM %s WHERE SurveyId='%s';", tableName, surveyId);
			Log.info(String.format("Select query is : %s", selectQuery));
			try (ResultSet rsRowCount = stmt
					.executeQuery(selectQuery)) {
				rsRowCount.next();
				count = rsRowCount.getInt(1);
				Log.info(String.format("%s table row count for SurveyCondition[Id=%s] = %d", tableName, surveyId,
						count));
				Log.info(String.format("Expected %s row count=%d, Actual row count=%d", tableName,
						minSurveyConditionCount, count));
				if (minSurveyConditionCount > count) {
					Log.info("isSurveyConditionSeedPresent = FALSE");
					return false;
				}
			}
		} catch (Exception e) {
			Log.error(String.format("EXCEPTION in isSurveyConditionSeedPresent(). ERROR Message: %s",
					ExceptionUtility.getStackTraceString(e)));
		}

		return true;
	}

	/**
	 * Verified if SurveyResult seed is present in database or not.
	 * 
	 * @param surveyId
	 *            - Survey Id
	 * @param analyzerId
	 *            - Analyzer Id
	 * @param startEpoch
	 *            - Start Epoch
	 * @param endEpoch
	 *            - End Epoch
	 * @param minNoteCount
	 *            - Minimum number of SurveyResult rows in database.
	 * @return - True/False. Whether SurveyResult seed is present in database.
	 */
	public boolean isSurveyResultSeedPresent(String surveyId, String analyzerId, String startEpoch, String endEpoch,
			Integer minSurveyResultCount) {
		Log.method("DbStateVerifier.isSurveyResultSeedPresent", surveyId, minSurveyResultCount);
		// check if surveyResult seed data is already present in the database.
		try (Statement stmt = connection.createStatement()) {
			// check surveyResult count.
			long count = 0;
			String tableName = SurveyResultDbSeedBuilder.TABLE_NAME;
			String selectQuery = String.format("SELECT COUNT(*) FROM %s WHERE SurveyId='%s';", tableName, surveyId);
			Log.info(String.format("Select query is : %s", selectQuery));
			try (ResultSet rsRowCount = stmt
					.executeQuery(selectQuery)) {
				rsRowCount.next();
				count = rsRowCount.getInt(1);
				Log.info(
						String.format("%s table row count for SurveyResult[Id=%s] = %d", tableName, surveyId, count));
				Log.info(String.format("Expected %s row count=%d, Actual row count=%d", tableName, minSurveyResultCount,
						count));
				if (minSurveyResultCount > count) {
					Log.info("isSurveyResultSeedPresent = FALSE");
					return false;
				}
			}
		} catch (Exception e) {
			Log.error(String.format("EXCEPTION in isSurveyResultSeedPresent(). ERROR Message: %s",
					ExceptionUtility.getStackTraceString(e)));
		}

		return true;
	}

	/**
	 * Verified if Measurement seed is present in database or not.
	 * 
	 * @param surveyId
	 *            - Survey Id
	 * @param analyzerId
	 *            - Analyzer Id
	 * @param startEpoch
	 *            - Start Epoch
	 * @param endEpoch
	 *            - End Epoch
	 * @param minNoteCount
	 *            - Minimum number of Measurement rows in database.
	 * @return - True/False. Whether Measurement seed is present in database.
	 */
	public boolean isMeasurementSeedPresent(String surveyId, String analyzerId, String startEpoch, String endEpoch,
			Integer minMeasurementCount) {
		Log.method("DbStateVerifier.isMeasurementSeedPresent", surveyId, minMeasurementCount);
		// check if measurement seed data is already present in the database.
		try (Statement stmt = connection.createStatement()) {
			// check measurement count.
			long count = 0;
			String tableName = MeasurementDbSeedBuilder.TABLE_NAME;
			String selectQuery = String.format("SELECT COUNT(*) FROM %s WHERE AnalyzerId='%s' AND EpochTime>=%s AND EpochTime<=%s;",
					tableName, analyzerId, startEpoch, endEpoch);
			Log.info(String.format("Select query is: %s", selectQuery));
			try (ResultSet rsRowCount = stmt.executeQuery(selectQuery)) {
				rsRowCount.next();
				count = rsRowCount.getInt(1);
				Log.info(String.format("%s table row count for Measurement[Id=%s] = %d", tableName, surveyId, count));
				Log.info(String.format("Expected %s row count=%d, Actual row count=%d", tableName, minMeasurementCount,
						count));
				if (minMeasurementCount > count) {
					Log.info("isMeasurementSeedPresent = FALSE");
					return false;
				}
			}
		} catch (Exception e) {
			Log.error(String.format("EXCEPTION in isMeasurementSeedPresent(). ERROR Message: %s",
					ExceptionUtility.getStackTraceString(e)));
		}

		return true;
	}

	/**
	 * Verified if GPSRaw seed is present in database or not.
	 * 
	 * @param surveyId
	 *            - Survey Id
	 * @param analyzerId
	 *            - Analyzer Id
	 * @param startEpoch
	 *            - Start Epoch
	 * @param endEpoch
	 *            - End Epoch
	 * @param minNoteCount
	 *            - Minimum number of GPSRaw rows in database.
	 * @return - True/False. Whether GPSRaw seed is present in database.
	 */
	public boolean isGPSRawSeedPresent(String surveyId, String analyzerId, String startEpoch, String endEpoch,
			Integer minGPSRawCount) {
		Log.method("DbStateVerifier.isGPSRawSeedPresent", surveyId, minGPSRawCount);
		// check if gPSRaw seed data is already present in the database.
		try (Statement stmt = connection.createStatement()) {
			// check gPSRaw count.
			long count = 0;
			String tableName = GPSRawDbSeedBuilder.TABLE_NAME;
			String selectQuery = String.format("SELECT COUNT(*) FROM %s WHERE AnalyzerId='%s' AND EpochTime>=%s AND EpochTime<=%s;",
					tableName, analyzerId, startEpoch, endEpoch);
			Log.info(String.format("Select query is : %s", selectQuery));
			try (ResultSet rsRowCount = stmt.executeQuery(
					selectQuery)) {
				rsRowCount.next();
				count = rsRowCount.getInt(1);
				Log.info(String.format("%s table row count for GPSRaw[Id=%s] = %d", tableName, surveyId, count));
				Log.info(String.format("Expected %s row count=%d, Actual row count=%d", tableName, minGPSRawCount,
						count));
				if (minGPSRawCount > count) {
					Log.info("isGPSRawSeedPresent = FALSE");
					return false;
				}
			}
		} catch (Exception e) {
			Log.error(String.format("EXCEPTION in isGPSRawSeedPresent(). ERROR Message: %s",
					ExceptionUtility.getStackTraceString(e)));
		}

		return true;
	}

	/**
	 * Verified if AnemometerRaw seed is present in database or not.
	 * 
	 * @param surveyId
	 *            - Survey Id
	 * @param analyzerId
	 *            - Analyzer Id
	 * @param startEpoch
	 *            - Start Epoch
	 * @param endEpoch
	 *            - End Epoch
	 * @param minNoteCount
	 *            - Minimum number of AnemometerRaw rows in database.
	 * @return - True/False. Whether AnemometerRaw seed is present in database.
	 */
	public boolean isAnemometerRawSeedPresent(String surveyId, String analyzerId, String startEpoch, String endEpoch,
			Integer minAnemometerRawCount) {
		Log.method("DbStateVerifier.isAnemometerRawSeedPresent", surveyId, minAnemometerRawCount);
		// check if anemometerRaw seed data is already present in the database.
		try (Statement stmt = connection.createStatement()) {
			// check anemometerRaw count.
			long count = 0;
			String tableName = AnemometerRawDbSeedBuilder.TABLE_NAME;
			String selectQuery = String.format("SELECT COUNT(*) FROM %s WHERE AnalyzerId='%s' AND EpochTime>=%s AND EpochTime<=%s;",
					tableName, analyzerId, startEpoch, endEpoch);
			Log.info(String.format("Select query is : %s", selectQuery));
			try (ResultSet rsRowCount = stmt.executeQuery(
					selectQuery)) {
				rsRowCount.next();
				count = rsRowCount.getInt(1);
				Log.info(String.format("%s table row count for AnemometerRaw[Id=%s] = %d", tableName, surveyId,
						count));
				Log.info(String.format("Expected %s row count=%d, Actual row count=%d", tableName,
						minAnemometerRawCount, count));
				if (minAnemometerRawCount > count) {
					Log.info("isAnemometerRawSeedPresent = FALSE");
					return false;
				}
			}
		} catch (Exception e) {
			Log.error(String.format("EXCEPTION in isAnemometerRawSeedPresent(). ERROR Message: %s",
					ExceptionUtility.getStackTraceString(e)));
		}

		return true;
	}

	/**
	 * Verified if CaptureEvent seed is present in database or not.
	 * 
	 * @param surveyId
	 *            - Survey Id
	 * @param analyzerId
	 *            - Analyzer Id
	 * @param startEpoch
	 *            - Start Epoch
	 * @param endEpoch
	 *            - End Epoch
	 * @param minNoteCount
	 *            - Minimum number of CaptureEvent rows in database.
	 * @return - True/False. Whether CaptureEvent seed is present in database.
	 */
	public boolean isCaptureEventSeedPresent(String surveyId, String analyzerId, String startEpoch, String endEpoch,
			Integer minCaptureEventCount) {
		Log.method("DbStateVerifier.isCaptureEventSeedPresent", surveyId, minCaptureEventCount);
		// check if captureEvent seed data is already present in the database.
		try (Statement stmt = connection.createStatement()) {
			// check captureEvent count.
			long count = 0;
			String tableName = CaptureEventDbSeedBuilder.TABLE_NAME;
			String selectQuery = String.format(
					"SELECT COUNT(*) FROM %s WHERE SurveyId='%s' AND AnalyzerId='%s' AND EpochTime>=%s AND EpochTime<=%s;",
					tableName, surveyId, analyzerId, startEpoch, endEpoch);
			Log.info(String.format("Select query is : %s", selectQuery));
			try (ResultSet rsRowCount = stmt.executeQuery(selectQuery)) {
				rsRowCount.next();
				count = rsRowCount.getInt(1);
				Log.info(
						String.format("%s table row count for CaptureEvent[Id=%s] = %d", tableName, surveyId, count));
				Log.info(String.format("Expected %s row count=%d, Actual row count=%d", tableName, minCaptureEventCount,
						count));
				if (minCaptureEventCount > count) {
					Log.info("isCaptureEventSeedPresent = FALSE");
					return false;
				}
			}
		} catch (Exception e) {
			Log.error(String.format("EXCEPTION in isCaptureEventSeedPresent(). ERROR Message: %s",
					ExceptionUtility.getStackTraceString(e)));
		}

		return true;
	}

	/**
	 * Verified if FieldOfView seed is present in database or not.
	 * 
	 * @param surveyId
	 *            - Survey Id
	 * @param analyzerId
	 *            - Analyzer Id
	 * @param startEpoch
	 *            - Start Epoch
	 * @param endEpoch
	 *            - End Epoch
	 * @param minNoteCount
	 *            - Minimum number of FieldOfView rows in database.
	 * @return - True/False. Whether FieldOfView seed is present in database.
	 */
	public boolean isFieldOfViewSeedPresent(String surveyId, String analyzerId, String startEpoch, String endEpoch,
			Integer minFieldOfViewCount) {
		Log.method("DbStateVerifier.isFieldOfViewSeedPresent", surveyId, minFieldOfViewCount);
		// check if fieldOfView seed data is already present in the database.
		try (Statement stmt = connection.createStatement()) {
			// check fieldOfView count.
			long count = 0;
			String tableName = FieldOfViewDbSeedBuilder.TABLE_NAME;
			String selectQuery = String.format(
					"SELECT COUNT(*) FROM %s WHERE SurveyId='%s' AND AnalyzerId='%s' AND EpochTime>=%s AND EpochTime<=%s;",
					tableName, surveyId, analyzerId, startEpoch, endEpoch);
			Log.info(String.format("Select query is : %s", selectQuery));
			try (ResultSet rsRowCount = stmt.executeQuery(selectQuery)) {
				rsRowCount.next();
				count = rsRowCount.getInt(1);
				Log.info(String.format("%s table row count for FieldOfView[Id=%s] = %d", tableName, surveyId, count));
				Log.info(String.format("Expected %s row count=%d, Actual row count=%d", tableName, minFieldOfViewCount,
						count));
				if (minFieldOfViewCount > count) {
					Log.info("isFieldOfViewSeedPresent = FALSE");
					return false;
				}
			}
		} catch (Exception e) {
			Log.error(String.format("EXCEPTION in isFieldOfViewSeedPresent(). ERROR Message: %s",
					ExceptionUtility.getStackTraceString(e)));
		}

		return true;
	}

	/**
	 * Verified if Peak seed is present in database or not.
	 * 
	 * @param surveyId
	 *            - Survey Id
	 * @param analyzerId
	 *            - Analyzer Id
	 * @param startEpoch
	 *            - Start Epoch
	 * @param endEpoch
	 *            - End Epoch
	 * @param minNoteCount
	 *            - Minimum number of Peak rows in database.
	 * @return - True/False. Whether Peak seed is present in database.
	 */
	public boolean isPeakSeedPresent(String surveyId, String analyzerId, String startEpoch, String endEpoch,
			Integer minPeakCount) {
		Log.method("DbStateVerifier.isPeakSeedPresent", surveyId, minPeakCount);
		// check if peak seed data is already present in the database.
		try (Statement stmt = connection.createStatement()) {
			// check peak count.
			long count = 0;
			String tableName = PeakDbSeedBuilder.TABLE_NAME;
			String selectQuery = String.format(
					"SELECT COUNT(*) FROM %s WHERE SurveyId='%s' AND AnalyzerId='%s' AND EpochTime>=%s AND EpochTime<=%s;",
					tableName, surveyId, analyzerId, startEpoch, endEpoch);
			Log.info(String.format("Select query is : %s", selectQuery));
			try (ResultSet rsRowCount = stmt.executeQuery(selectQuery)) {
				rsRowCount.next();
				count = rsRowCount.getInt(1);
				Log.info(String.format("%s table row count for Peak[Id=%s] = %d", tableName, surveyId, count));
				Log.info(
						String.format("Expected %s row count=%d, Actual row count=%d", tableName, minPeakCount, count));
				if (minPeakCount > count) {
					Log.info("isPeakSeedPresent = FALSE");
					return false;
				}
			}
		} catch (Exception e) {
			Log.error(String.format("EXCEPTION in isPeakSeedPresent(). ERROR Message: %s",
					ExceptionUtility.getStackTraceString(e)));
		}

		return true;
	}

	/**
	 * Verified if Segment seed is present in database or not.
	 * 
	 * @param surveyId
	 *            - Survey Id
	 * @param analyzerId
	 *            - Analyzer Id
	 * @param startEpoch
	 *            - Start Epoch
	 * @param endEpoch
	 *            - End Epoch
	 * @param minNoteCount
	 *            - Minimum number of Segment rows in database.
	 * @return - True/False. Whether Segment seed is present in database.
	 */
	public boolean isSegmentSeedPresent(String surveyId, String analyzerId, String startEpoch, String endEpoch,
			Integer minSegmentCount) {
		Log.method("DbStateVerifier.isSegmentSeedPresent", surveyId, minSegmentCount);
		// check if segment seed data is already present in the database.
		try (Statement stmt = connection.createStatement()) {
			// check segment count.
			long count = 0;
			String tableName = SegmentDbSeedBuilder.TABLE_NAME;
			String selectQuery = String.format("SELECT COUNT(*) FROM %s WHERE SurveyId='%s';", tableName, surveyId);
			Log.info(String.format("Select query is : %s", selectQuery));
			try (ResultSet rsRowCount = stmt
					.executeQuery(selectQuery)) {
				rsRowCount.next();
				count = rsRowCount.getInt(1);
				Log.info(String.format("%s table row count for Segment[Id=%s] = %d", tableName, surveyId, count));
				Log.info(String.format("Expected %s row count=%d, Actual row count=%d", tableName, minSegmentCount,
						count));
				if (minSegmentCount > count) {
					Log.info("isSegmentSeedPresent = FALSE");
					return false;
				}
			}
		} catch (Exception e) {
			Log.error(String.format("EXCEPTION in isSegmentSeedPresent(). ERROR Message: %s",
					ExceptionUtility.getStackTraceString(e)));
		}

		return true;
	}

	/**
	 * Verified if Note seed is present in database or not.
	 * 
	 * @param surveyId
	 *            - Survey Id
	 * @param analyzerId
	 *            - Analyzer Id
	 * @param startEpoch
	 *            - Start Epoch
	 * @param endEpoch
	 *            - End Epoch
	 * @param minNoteCount
	 *            - Minimum number of Note rows in database.
	 * @return - True/False. Whether Note seed is present in database.
	 */
	public boolean isNoteSeedPresent(String surveyId, String analyzerId, String startEpoch, String endEpoch,
			Integer minNoteCount) {
		Log.method("DbStateVerifier.isNoteSeedPresent", surveyId, minNoteCount);
		// check if note seed data is already present in the database.
		try (Statement stmt = connection.createStatement()) {
			// check note count.
			long count = 0;
			String tableName = NoteDbSeedBuilder.TABLE_NAME;
			String selectQuery = String.format("SELECT COUNT(*) FROM %s WHERE AnalyzerId='%s' AND EpochTime>=%s AND EpochTime<=%s;",
					tableName, analyzerId, startEpoch, endEpoch);
			Log.info(String.format("Select query is: %s", selectQuery));
			try (ResultSet rsRowCount = stmt.executeQuery(
					selectQuery)) {
				rsRowCount.next();
				count = rsRowCount.getInt(1);
				Log.info(String.format("%s table row count for Note[Id=%s] = %d", tableName, surveyId, count));
				Log.info(
						String.format("Expected %s row count=%d, Actual row count=%d", tableName, minNoteCount, count));
				if (minNoteCount > count) {
					Log.info("isNoteSeedPresent = FALSE");
					return false;
				}
			}
		} catch (Exception e) {
			Log.error(String.format("EXCEPTION in isNoteSeedPresent(). ERROR Message: %s",
					ExceptionUtility.getStackTraceString(e)));
		}

		return true;
	}
}