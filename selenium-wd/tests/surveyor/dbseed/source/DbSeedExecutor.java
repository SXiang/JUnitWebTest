package surveyor.dbseed.source;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.microsoft.sqlserver.jdbc.SQLServerBulkCopy;

import common.source.CSVUtility;
import common.source.ExceptionUtility;
import common.source.FileUtility;
import common.source.Log;
import common.source.LogHelper;
import common.source.NumberUtility;
import common.source.TestContext;
import common.source.TestSetup;
import surveyor.dataaccess.source.ConnectionFactory;
import surveyor.dataaccess.source.Customer;
import surveyor.dataaccess.source.SqlCmdUtility;
import static surveyor.scommon.source.SurveyorConstants.*;

public class DbSeedExecutor {

	private static final boolean ENABLE_VERBOSE_LOGGING = false;
	private static final boolean DEFAULT_REDATE_SETTING = false;
	private static final String MEASUREMENT_PREFIX = "Measurement-";
	private static final String GPSRAW_PREFIX = "GPSRaw-";
	private static final String ANEMOMETERRAW_PREFIX = "AnemometerRaw-";
	private static DbSeedBuilderCache surveySeedBuilderCache;

	public static final String[] PICARRO_CUSTOMER_SURVEYS = {"assessment-1", "assessment-2", "EthaneManual", "EthaneStnd3","EthaneStnd2","EthaneStnd","EthaneRR","EthaneOpertor2","EthaneOpertor1","Ethane1MinSurvey",
			"iso-cap-1", "iso-cap-2", "man-pic-1","man-pic-2","op-pic","op-sqacudr","rr-pic","rr-sqacudr-1","rr-sqacudr-2","stnd-pic",
			"standard_test-1", "standard_test-2", "standard_test-3", "stnd-sqacudr","stnd-sqacudr-1","stnd-sqacudr-2","stnd-sqacudr-3",
			"StandardWithLeak", "NoFOV-1", "NoFOV-2", "NoFOV-3"};

	public static final String[] SQACUS_CUSTOMER_SURVEYS = {"assessment-1-sqacus", "assessment-2-sqacus", "EthaneManual-sqacus","EthaneStnd3-sqacus","EthaneStnd2-sqacus","EthaneStnd-sqacus","EthaneRR-sqacus","EthaneOpertor2-sqacus",
			"EthaneOpertor1-sqacus","Ethane1MinSurvey-sqacus", "iso-cap-1-sqacus", "iso-cap-2-sqacus", "man-pic-1-sqacus","man-pic-2-sqacus","op-pic-sqacus","op-sqacudr-sqacus","rr-pic-sqacus",
			"rr-sqacudr-1-sqacus","rr-sqacudr-2-sqacus","stnd-pic-sqacus", "standard_test-1-sqacus", "standard_test-2-sqacus", "standard_test-3-sqacus", "stnd-sqacudr-sqacus","stnd-sqacudr-1-sqacus",
			"stnd-sqacudr-2-sqacus","stnd-sqacudr-3-sqacus","StandardWithLeak-sqacus", "NoFOV-1-sqacus", "NoFOV-2-sqacus", "NoFOV-3-sqacus"};

	/* Method to push all the seed data required for automation. */

	public static void executeAllDataSeed() throws Exception {
		DbSeedExecutor.executeGenericDataSeed();
		DbSeedExecutor.executeGisSeed();
		DbSeedExecutor.executeSurveyDataSeed();
		DbSeedExecutor.executeSurveyDataSeed(SQACUS_CUSTOMER_SURVEYS);
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
		executeSurveyDataSeed(null);
	}

	public static void executeSurveyDataSeed(String[] surveyTags) throws Exception {
		Log.method("DbSeedExecutor.executeSurveyDataSeed", LogHelper.arrayToString(surveyTags));

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

		surveySeedBuilderCache = new DbSeedBuilderCache();

		if (surveyTags == null) {
			// Use default survey tags if NOT specified by caller.
			surveyTags = PICARRO_CUSTOMER_SURVEYS;
		}

		try {
			connection = ConnectionFactory.createConnection();
			for (String surveyTag : surveyTags) {
				try
		        {
					Log.info(String.format("***** START Processing survey with tag - '%s' *****", surveyTag));

					String surveySeedKey = String.format("Survey-%s.csv", surveyTag);
					String surveyConditionSeedKey = String.format("SurveyCondition-%s.csv", surveyTag);
					String surveyResultSeedKey = String.format("SurveyResult-%s.csv", surveyTag);
					String measurementSeedKey = String.format("Measurement-%s.csv", surveyTag);
					String gpsRawSeedKey = String.format("GPSRaw-%s.csv", surveyTag);
					String anemometerRawSeedKey = String.format("AnemometerRaw-%s.csv", surveyTag);
					String captureEventSeedKey = String.format("CaptureEvent-%s.csv", surveyTag);
					String fieldOfViewSeedKey = String.format("FieldOfView-%s.csv", surveyTag);
					String peakSeedKey = String.format("Peak-%s.csv", surveyTag);
					String segmentSeedKey = String.format("Segment-%s.csv", surveyTag);
					String noteSeedKey = String.format("Note-%s.csv", surveyTag);

					surveyDbSeedBuilder = new SurveyDbSeedBuilder(surveySeedKey);
					surveyConditionDbSeedBuilder = new SurveyConditionDbSeedBuilder(surveyConditionSeedKey);
					surveyResultDbSeedBuilder = new SurveyResultDbSeedBuilder(surveyResultSeedKey);
					measurementDbSeedBuilder = new MeasurementDbSeedBuilder(measurementSeedKey, DEFAULT_REDATE_SETTING);
					gpsRawDbSeedBuilder = new GPSRawDbSeedBuilder(gpsRawSeedKey, DEFAULT_REDATE_SETTING);
					anemometerRawDbSeedBuilder = new AnemometerRawDbSeedBuilder(anemometerRawSeedKey, DEFAULT_REDATE_SETTING);
					captureEventDbSeedBuilder = new CaptureEventDbSeedBuilder(captureEventSeedKey);
					fieldOfViewDbSeedBuilder = new FieldOfViewDbSeedBuilder(fieldOfViewSeedKey);
					peakDbSeedBuilder = new PeakDbSeedBuilder(peakSeedKey);
					segmentDbSeedBuilder = new SegmentDbSeedBuilder(segmentSeedKey);
					noteDbSeedBuilder = new NoteDbSeedBuilder(noteSeedKey);

					// check if survey data is present in database for this survey tag.
					final String surveyCsvFilePath = surveyDbSeedBuilder.getSeedFilePath();
					final DbStateVerifier dbStateVerifier = new DbStateVerifier(connection);

					// get line count from data files (-2 for header and last empty line)
					final Integer minSurveyCount = FileUtility.getLineCountInFile(Paths.get(surveyDbSeedBuilder.getSeedFilePath())) - 2;
					final Integer minSurveyConditionCount = FileUtility.getLineCountInFile(Paths.get(surveyConditionDbSeedBuilder.getSeedFilePath())) - 2;
					final Integer minSurveyResultCount = FileUtility.getLineCountInFile(Paths.get(surveyResultDbSeedBuilder.getSeedFilePath())) - 2;
					final Integer minCaptureEventCount = FileUtility.getLineCountInFile(Paths.get(captureEventDbSeedBuilder.getSeedFilePath())) - 2;
					final Integer minFieldOfViewCount = FileUtility.getLineCountInFile(Paths.get(fieldOfViewDbSeedBuilder.getSeedFilePath())) - 2;
					final Integer minPeakCount = FileUtility.getLineCountInFile(Paths.get(peakDbSeedBuilder.getSeedFilePath())) - 2;
					final Integer minSegmentCount = FileUtility.getLineCountInFile(Paths.get(segmentDbSeedBuilder.getSeedFilePath())) - 2;
					final Integer minNoteCount = FileUtility.getLineCountInFile(Paths.get(noteDbSeedBuilder.getSeedFilePath())) - 2;
					Integer minMeasurementCount = FileUtility.getLineCountInFile(Paths.get(measurementDbSeedBuilder.getSeedFilePath())) - 2;
					Integer minGPSRawCount = FileUtility.getLineCountInFile(Paths.get(gpsRawDbSeedBuilder.getSeedFilePath())) - 2;
					Integer minAnemometerRawCount = FileUtility.getLineCountInFile(Paths.get(anemometerRawDbSeedBuilder.getSeedFilePath())) - 2;

					final List<Map<String, String>> firstSurveyRow = new CSVUtility().getTopRows(surveyCsvFilePath, 1);

					final String surveyId = firstSurveyRow.get(0).get("Id");
					final String analyzerId = firstSurveyRow.get(0).get("AnalyzerId");

					// If redate is enabled, instead of looking for startEpoch and endEpoch,
					// look for cumulative line count with corresonding CSVs for current analyzer.
					if (measurementDbSeedBuilder.isRedate()) {
						minMeasurementCount += getCumulativeRowCount(surveySeedBuilderCache, analyzerId, MEASUREMENT_PREFIX);
					}
					if (gpsRawDbSeedBuilder.isRedate()) {
						minGPSRawCount += getCumulativeRowCount(surveySeedBuilderCache, analyzerId, GPSRAW_PREFIX);
					}
					if (anemometerRawDbSeedBuilder.isRedate()) {
						minAnemometerRawCount += getCumulativeRowCount(surveySeedBuilderCache, analyzerId, ANEMOMETERRAW_PREFIX);
					}

					String startEpoch = firstSurveyRow.get(0).get("StartEpoch");
					String endEpoch = firstSurveyRow.get(0).get("EndEpoch");

					Log.info(String.format("First survey row from - '%s' is: StartEpoch=%s, EndEpoch=%s", surveyCsvFilePath,
							startEpoch, endEpoch));

					Double startEpochValue = Double.parseDouble(startEpoch) - EPSILON;
					Double endEpochValue = Double.parseDouble(endEpoch) + EPSILON;
					startEpoch = NumberUtility.formatString(startEpochValue, 10);
					endEpoch = NumberUtility.formatString(endEpochValue, 10);

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

					// check and execute Measurement DB seed. If redate is enabled, check RowCount for Analyzer.
					if (dbStateVerifier.isMeasurementSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minMeasurementCount, measurementDbSeedBuilder.isRedate())) {
						Log.info(String.format("Measurement DB seed is already present for this survey-'%s'. SKIP execution.", surveyId));
					} else {
						if (FileUtility.fileExists(measurementDbSeedBuilder.getSeedFilePath())) {
							executeSeed(connection, measurementDbSeedBuilder.build());;
						}
					}

					// check and execute GPSRaw DB seed. If redate is enabled, check RowCount for Analyzer.
					if (dbStateVerifier.isGPSRawSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minGPSRawCount, gpsRawDbSeedBuilder.isRedate())) {
						Log.info(String.format("GPSRaw DB seed is already present for this survey-'%s'. SKIP execution.", surveyId));
					} else {
						if (FileUtility.fileExists(gpsRawDbSeedBuilder.getSeedFilePath())) {
							executeSeed(connection, gpsRawDbSeedBuilder.build());;
						}
					}

					// check and execute AnemometerRaw DB seed. If redate is enabled, check RowCount for Analyzer.
					if (dbStateVerifier.isAnemometerRawSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minAnemometerRawCount, anemometerRawDbSeedBuilder.isRedate())) {
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

					// Store all the seed builder in cache for future verification.
					surveySeedBuilderCache.addDbSeedBuilder(noteSeedKey, noteDbSeedBuilder);
					surveySeedBuilderCache.addDbSeedBuilder(segmentSeedKey, segmentDbSeedBuilder);
					surveySeedBuilderCache.addDbSeedBuilder(peakSeedKey, peakDbSeedBuilder);
					surveySeedBuilderCache.addDbSeedBuilder(fieldOfViewSeedKey, fieldOfViewDbSeedBuilder);
					surveySeedBuilderCache.addDbSeedBuilder(captureEventSeedKey, captureEventDbSeedBuilder);

					surveySeedBuilderCache.addDbSeedBuilder(anemometerRawSeedKey, anemometerRawDbSeedBuilder);
					Log.info(String.format("StartEpoch=%s, EndEpoch=%s", NumberUtility.formatString(anemometerRawDbSeedBuilder.getStartEpoch(), 10),
							NumberUtility.formatString(anemometerRawDbSeedBuilder.getEndEpoch(), 10)));
					surveySeedBuilderCache.addDbSeedBuilder(gpsRawSeedKey, gpsRawDbSeedBuilder);
					Log.info(String.format("StartEpoch=%s, EndEpoch=%s", NumberUtility.formatString(gpsRawDbSeedBuilder.getStartEpoch(), 10),
							NumberUtility.formatString(gpsRawDbSeedBuilder.getEndEpoch(), 10)));
					surveySeedBuilderCache.addDbSeedBuilder(measurementSeedKey, measurementDbSeedBuilder);
					Log.info(String.format("StartEpoch=%s, EndEpoch=%s", NumberUtility.formatString(measurementDbSeedBuilder.getStartEpoch(), 10),
							NumberUtility.formatString(measurementDbSeedBuilder.getEndEpoch(), 10)));

					surveySeedBuilderCache.addDbSeedBuilder(surveyResultSeedKey, surveyResultDbSeedBuilder);
					surveySeedBuilderCache.addDbSeedBuilder(surveyConditionSeedKey, surveyConditionDbSeedBuilder);
					surveySeedBuilderCache.addDbSeedBuilder(surveySeedKey, surveyDbSeedBuilder);

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

	private static Integer getCumulativeRowCount(DbSeedBuilderCache dbSeedBuilderCache, String analyzerId, String filePrefix) throws IOException {
		Integer rowCount = 0;
		Set<String> seedBuilderCacheKeys = dbSeedBuilderCache.getDbSeedBuilderCacheKeys();
		for (String key : seedBuilderCacheKeys) {
			if (key.startsWith(filePrefix)) {
				BaseDbSeedBuilder dbSeedBuilder = dbSeedBuilderCache.getDbSeedBuilder(key);
				final String seedFilePath = dbSeedBuilder.getSeedFilePath();
				final List<Map<String, String>> firstRow = new CSVUtility().getTopRows(seedFilePath, 1);
				if (firstRow != null && firstRow.size() > 0) {
					if (firstRow.get(0).get("AnalyzerId").equals(analyzerId)) {
						rowCount += FileUtility.getLineCountInFile(Paths.get(seedFilePath)) - 2;
					}
				}
			}
		}
		return rowCount;
	}

	/* Methods for pushing GIS seed data (CustomerBoundaryType, CustomerMaterialType, Boundary and Asset) */

	public static void executeGisSeed() throws Exception {
		Log.method("DbSeedExecutor.executeGisSeed");
		executeGisSeed(null /*customerId*/); // default -> Picarro customer.
		executeGisSeed(Customer.getCustomer(CUSTOMER_SQACUS).getId());
		executeGisSeed(Customer.getCustomer(CUSTOMER_PGE).getId());
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

			DbSeed custBoundaryTypeDbSeed = customerBoundaryTypeDbSeedBuilder.build(isCustomerSpecified ? customerId : null);
			DbSeed custMaterialTypeDbSeed = customerMaterialTypeDbSeedBuilder.build(isCustomerSpecified ? customerId : null);

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

			int expectedCustomerBoundaryTypeCount = FileUtility.getLineCountInFile(Paths.get(customerBoundaryTypeDbSeedBuilder.getSeedFilePath())) - 2;
			int expectedCustomerMaterialTypeCount = FileUtility.getLineCountInFile(Paths.get(customerMaterialTypeDbSeedBuilder.getSeedFilePath())) - 2;

			// check and push CustomerBoundaryType db seed.
			boolean customerBoundaryTypeSeedPresent = dbStateVerifier.isGISCustomerBoundaryTypeSeedPresent(customerId, expectedCustomerBoundaryTypeCount);
			if (!customerBoundaryTypeSeedPresent) {
				executeSeed(connection, custBoundaryTypeDbSeed);
			} else {
				Log.info(String.format("GIS CustomerBoundaryType DB seed is already present for customer-'%s'.", customerId));
			}

			// check and push CustomerMaterialType db seed.
			boolean customerMaterialTypeSeedPresent = dbStateVerifier.isGISCustomerMaterialTypeSeedPresent(customerId, expectedCustomerMaterialTypeCount);
			if (!customerMaterialTypeSeedPresent) {
				executeSeed(connection, custMaterialTypeDbSeed);
			} else {
				Log.info(String.format("GIS CustomerMaterialType DB seed is already present for customer-'%s'.", customerId));
			}

			// For new customer seed to execute, CustomerBoundaryType and CustomerMaterialType should have been executed.
			if (isCustomerSpecified && (customerBoundaryTypeSeedPresent || customerMaterialTypeSeedPresent)) {
				Log.info(String.format("Pushing GIS DB seed for non-default customer-'%s'. "
						+ "There is currently data in database for this customer in CustomerBoundaryType=[%b], "
						+ "CustomerMaterialType=[%b] which should be cleared before pushing GIS DB seed for this customer."
						+ " SKIPPING execution!", customerId, customerBoundaryTypeSeedPresent, customerMaterialTypeSeedPresent));
				return;
			}

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

	public static void cleanUpGisSeed(String customerId) throws Exception {
		Log.method("DbSeedExecutor.cleanUpGisSeed", customerId);

		String invalidCustomerName = null;

		Customer picarroCustomer = Customer.getCustomer(CUSTOMER_PICARRO);
		Customer sqaCusCustomer = Customer.getCustomer(CUSTOMER_SQACUS);
		Customer pgeCustomer = Customer.getCustomer(CUSTOMER_PGE);

		String picarroCustomerId = picarroCustomer.getId();
		String sqaCusCustomerId = sqaCusCustomer.getId();
		String pgeCustomerId = pgeCustomer.getId();

		if (customerId.equals(picarroCustomerId)) {
			invalidCustomerName = picarroCustomer.getName();
		} else if (customerId.equals(sqaCusCustomerId)) {
			invalidCustomerName = sqaCusCustomer.getName();
		} else if (customerId.equals(pgeCustomerId)) {
			invalidCustomerName = pgeCustomer.getName();
		}

		if (invalidCustomerName != null) {
			throw new IllegalArgumentException(String.format("Cannot cleanup GIS seed data for automation seed Customer. "
					+ "Specified Invalid CustomerId=[%s], CustomerName=[%s]", customerId, invalidCustomerName));
		}

		Connection connection = null;
		AssetDbSeedBuilder assetDbSeedBuilder = null;
		BoundaryDbSeedBuilder boundaryDbSeedBuilder = null;
		CustomerMaterialTypeDbSeedBuilder customerMaterialTypeDbSeedBuilder = null;
		CustomerBoundaryTypeDbSeedBuilder customerBoundaryTypeDbSeedBuilder = null;

		try
        {
			connection = ConnectionFactory.createConnection();

        	assetDbSeedBuilder = new AssetDbSeedBuilder();
        	boundaryDbSeedBuilder = new BoundaryDbSeedBuilder();
        	customerMaterialTypeDbSeedBuilder = new CustomerMaterialTypeDbSeedBuilder();
        	customerBoundaryTypeDbSeedBuilder = new CustomerBoundaryTypeDbSeedBuilder();

			DbSeed assetDbSeed = assetDbSeedBuilder.cleanup(customerId);
			DbSeed boundaryDbSeed = boundaryDbSeedBuilder.cleanup(customerId);
			DbSeed custMaterialTypeDbSeed = customerMaterialTypeDbSeedBuilder.cleanup(customerId);
			DbSeed custBoundaryTypeDbSeed = customerBoundaryTypeDbSeedBuilder.cleanup(customerId);

			executeSeed(connection, assetDbSeed);
			executeSeed(connection, boundaryDbSeed);
			executeSeed(connection, custMaterialTypeDbSeed);
			executeSeed(connection, custBoundaryTypeDbSeed);

        } catch (Exception ex) {
        	Log.error(String.format("EXCEPTION in cleanUpGisSeed() - %s", ExceptionUtility.getStackTraceString(ex)));
        } finally {
            connection.close();
            // cleanup seed builders.
            closeDbSeedBuilder(assetDbSeedBuilder);
            closeDbSeedBuilder(boundaryDbSeedBuilder);
            closeDbSeedBuilder(customerMaterialTypeDbSeedBuilder);
            closeDbSeedBuilder(customerBoundaryTypeDbSeedBuilder);
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
					if (insertStatements != null && insertStatements.size() > 0) {
	                	for (String insertStmt : insertStatements) {
	                		executingInsertStatement = insertStmt;
	                		if (ENABLE_VERBOSE_LOGGING) {
	                			Log.info(String.format("Executing statement -> '%s'", insertStmt));
	                		}
							stmt.executeUpdate(insertStmt);
						}
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

	public static DbSeedBuilderCache getSurveySeedBuilderCache() {
		return surveySeedBuilderCache;
	}
}