package surveyor.dbseed.source;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.microsoft.sqlserver.jdbc.SQLServerBulkCopy;

import common.source.BcpDatFileTransferUtility;
import common.source.CSVUtility;
import common.source.Constants;
import common.source.ExceptionUtility;
import common.source.FileUtility;
import common.source.Log;
import common.source.LogHelper;
import common.source.NumberUtility;
import common.source.TestContext;
import common.source.TestSetup;
import common.source.ZipUtility;
import surveyor.dataaccess.source.Analyzer;
import surveyor.dataaccess.source.ConnectionFactory;
import surveyor.dataaccess.source.Customer;
import surveyor.dataaccess.source.CustomerWithGisDataPool;
import surveyor.dataaccess.source.SqlCmdUtility;
import surveyor.dataaccess.source.Survey;
import surveyor.dbseed.source.DbStateCorrector.TableName;

import static surveyor.scommon.source.SurveyorConstants.*;

public class DbSeedExecutor {

	private static final String ASSET_BOUNDARY_BA_ZIP = "Asset.Boundary.BA.zip";
	private static final String AUTOMATION_SEED_SCRIPT_LOAD_GIS_SQL = "AutomationSeedScript-LoadGIS.sql";
	private static final boolean ENABLE_VERBOSE_LOGGING = false;
	private static final boolean DEFAULT_REDATE_SETTING = false;
	private static final String MEASUREMENT_PREFIX = "Measurement-";
	private static final String GPSRAW_PREFIX = "GPSRaw-";
	private static final String ANEMOMETERRAW_PREFIX = "AnemometerRaw-";
	private static DbSeedBuilderCache surveySeedBuilderCache;

	private static Map<String, String> customerNameIdMap = Collections.synchronizedMap(new HashMap<String, String>());

	public static final String ASSET_DAT_FILE = "Asset.BA.dat";
	public static final String BOUNDARY_DAT_FILE = "Boundary.BA.dat";

	public static final String[] PICARRO_CUSTOMER_SURVEYS = {
			/* Surveys with raw data that have been reprocessed - 10/23/2017 (surveyor 3.0.1.2109, worker 3.0.1.884) */
			"AnalyticsTagA-1", "AnalyticsTagB-1", "AnalyticsTagC-1", "assessment-1", "assessment-2", "daysurvey3.2-1", "daysurvey3.2-2", "daysurvey4-1", "daysurvey5-1",
			"daysurvey7-1", "daysurvey8-1", "daysurvey8-2", "daysurvey8.2-1", "Ethane1MinSurvey-1", "EthaneManual-1", "FeqNoPeaks01-1", "FeqWithPeaks01-1", "FeqWithPeaks02-1",
			"iso-cap-1", "iso-cap-2", "IsoCapRedTrace-1", "man-pic-1", "man-pic-2", "MenloNight11_17EQ01-1", "MenloNight11_17EQ02-1",
			"MeqNoPeaks01-1", "MeqWithPeaks01-1", "No-fov-1-1", "No-fov-2-1", "No-fov-3-1", "op-pic-1", "rr-pic-1", "Standard-With-Leak-1", "StandardSurveyEQ01-1", "StandardSurveyEQ02-1",
			"standard-test-1-1", "standard-test-2-1", "standard-test-3-1", "stnd-pic-1", "LISANotIntersectingAssets-1", "2HourSurvey-1", "4HourSurvey-1",
			/* Surveys with raw data that failed in re-processing. Currently using original surveys for seed data. Regenerate seed data for processed survey once re-processing works with later Tahoe builds */
			"8HourSurvey-1", "GreaterThan4Hour-1",  "LessThan4Hour-1",
			/* Surveys below do NOT contain raw data */
			"8HourSurvey-2", "8HourSurvey-3", "8HourSurvey-4", "8HourSurvey-5", "8HourSurvey-6", "8HourSurvey-7", "8HourSurvey-8", "8HourSurvey-9", "8HourSurvey-10", "8HourSurvey-11", "8HourSurvey-12",
			"EthaneOpertor1", "EthaneOpertor2", "EthaneRR", "EthaneStnd", "EthaneStnd2", "EthaneStnd3", "stnd-sqacudr-1", "stnd-sqacudr-2", "stnd-sqacudr" };

	public static final String[] SQACUS_CUSTOMER_SURVEYS = {
			/* Surveys with raw data that have been reprocessed - 10/23/2017 (surveyor 3.0.1.2109, worker 3.0.1.884) */
			"AnalyticsTagA-sqacus-1", "AnalyticsTagB-sqacus-1", "AnalyticsTagC-sqacus-1", "assessment-sqacus-1", "daysurvey3.2-sqacus-1", "daysurvey3.2-sqacus-2",
			"daysurvey4-sqacus-1", "daysurvey5-sqacus-1", "daysurvey7-sqacus-1", "daysurvey8-sqacus-1", "daysurvey8-sqacus-2", "daysurvey8.2-sqacus-1", "Ethane1MinSurvey-sqacus-1",
			"EthaneManual-sqacus-1", "FeqNoPeaks01-sqacus-1", "FeqWithPeaks01-sqacus-1", "FeqWithPeaks02-sqacus-1", "iso-cap-sqacus-1", "iso-cap-sqacus-2", "man-pic-sqacus-1", "man-pic-sqacus-2",
			"MenloNight11_17EQ01-sqacus-1", "MenloNight11_17EQ02-sqacus-1", "MeqNoPeaks01-sqacus-1", "MeqWithPeaks01-sqacus-1", "No-fov-1-sqacus-1", "No-fov-2-sqacus-1", "No-fov-3-sqacus-1",
			"op-pic-sqacus-1", "op-sqacudr-sqacus-1", "op-sqacudr-sqacus-2", "rr-pic-sqacus-1", "rr-sqacudr-sqacus-1", "rr-sqacudr-sqacus-2", "rr-sqacudr-sqacus-3", "rr-sqacudr-sqacus-4",
			"Standard-With-Leak-sqacus-1", "StandardSurveyEQ01-sqacus-1", "StandardSurveyEQ02-sqacus-1", "standard-test-1-sqacus-1", "standard-test-2-sqacus-1", "standard-test-3-sqacus-1",
			"stnd-pic-sqacus-1", "stnd-sqacudr-sqacus-1", "stnd-sqacudr-sqacus-2", "LISANotIntersectingAssets-sqacus-1", "2HourSurvey-sqacus-1", "4HourSurvey-sqacus-1", "stnd-sqacudr-sqacus-6",
			/* Surveys below do NOT contain raw data */
			"assessment-sqacus-2", "EthaneOpertor1-sqacus", "EthaneOpertor2-sqacus", "EthaneRR-sqacus", "EthaneStnd-sqacus", "EthaneStnd2-sqacus", "EthaneStnd3-sqacus",
			"stnd-sqacudr-sqacus-3", "stnd-sqacudr-sqacus-4", "stnd-sqacudr-sqacus-5" };

	/* Method to detect/fix polluted survey seed data. */

	/**
	 * Detects if any of the specified surveys have been polluted during test executions (ie have different survey result data compared to what is specified in seed data).
	 * Fixes polluted surveys and adds logs for investigation.
	 * @param surveyFileTags - surveys to detect/fix
	 * @throws Exception
	 * @return - whether pollution was detected and correction was applied.
	 */
	public static boolean detectFixSurveySeed(String[] surveyFileTags) throws Exception {
		Log.method("DbSeedExecutor.detectFixSurveySeed", LogHelper.arrayToString(surveyFileTags));

		Connection connection = null;
		boolean applyCorrection = false;
		try {
			if (surveyFileTags != null && surveyFileTags.length > 0) {
				for (String surveyFileTag : surveyFileTags) {
					connection = ConnectionFactory.createConnection();
					DbStateVerifier dbStateVerifier = new DbStateVerifier(connection);
					DbStateCorrector dbStateCorrector = DbStateCorrector.newInstance(connection);

					List<Map<String, String>> surveyFileLines = getSurveyFileLines(surveyFileTag);

					// Get survey tag and AnalyzerId from Survey-*.csv
					String surveyTag = surveyFileLines.get(0).get("Tag");
					String analyzerId = surveyFileLines.get(0).get("AnalyzerId");
					String analyzerSerialNumber = Analyzer.getAnalyzer(analyzerId).getSerialNumber();
					String surveyId = null;
					Survey survey = Survey.getSurveys(surveyTag).stream().filter(s -> s.getAnalyzerId().equalsIgnoreCase(analyzerId)).findFirst().orElse(null);
					if (survey == null) {
						Log.info(String.format("Did NOT find expected seed survey for tag='%s' and AnalyzerId='%s'. Applying correction...", surveyTag, analyzerId));
						dbStateCorrector.correctSurveySeedData(surveyFileTag, surveyId, analyzerId);
					} else {
						surveyId = survey.getId();
						// Detect/fix survey results data.
						if (!dbStateVerifier.isSegmentSeedMatch(SegmentDbSeedBuilder.readRowsFromSeed(surveyFileTag), surveyId)) {
							dbStateCorrector.append(TableName.SEGMENT);
							applyCorrection = true;
						}

						if (!dbStateVerifier.isSurveyResultSeedMatch(SurveyResultDbSeedBuilder.readRowsFromSeed(surveyFileTag), surveyId)) {
							dbStateCorrector.append(TableName.SEGMENT);     // segment has FK to SurveyResult table.
							dbStateCorrector.append(TableName.SURVEYRESULT);
							applyCorrection = true;
						}

						if (!dbStateVerifier.isPeakSeedMatch(PeakDbSeedBuilder.readRowsFromSeed(surveyFileTag), surveyTag, analyzerSerialNumber)) {
							dbStateCorrector.append(TableName.PEAK);
							applyCorrection = true;
						}

						if (!dbStateVerifier.isCaptureEventSeedMatch(CaptureEventDbSeedBuilder.readRowsFromSeed(surveyFileTag), surveyTag, analyzerSerialNumber)) {
							dbStateCorrector.append(TableName.CAPTUREEVENT);
							applyCorrection = true;
						}

						if (!dbStateVerifier.isFieldOfViewSeedMatch(FieldOfViewDbSeedBuilder.readRowsFromSeed(surveyFileTag), surveyId)) {
							dbStateCorrector.append(TableName.FIELDOFVIEW);
							applyCorrection = true;
						}

						if (applyCorrection) {
							Log.warn(String.format("Found polluted survey. surveyFileTag=[%s], surveyTag=[%s], surveyId=[%s], analyzerId=[%s]", surveyFileTag, surveyTag, surveyId, analyzerId));
							dbStateCorrector.correctSurveySeedData(surveyFileTag, surveyId, analyzerId);
						}
					}
				}
			}
		} finally {
			connection.close();
		}

		return applyCorrection;
	}

	private static List<Map<String, String>> getSurveyFileLines(String surveyFileTag) throws FileNotFoundException, IOException {
		String surveySeedKey = String.format("Survey-%s.csv", surveyFileTag);
		return new SurveyDbSeedBuilder(surveySeedKey).getSeedFileLines();
	}

	/* Method to push all the seed data required for automation. */

	public static void executeAllDataSeed() throws Exception {
		DbSeedExecutor.executeGenericDataSeed();
		DbSeedExecutor.executeGISCustomerDataSeed();
		DbSeedExecutor.executeGisSeed();
		DbSeedExecutor.executeGisRefreshDataSeed();
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
			String sqlCmdLogFilePath = Paths.get(TestSetup.getRootPath(), String.format("sqlcmd-%s.log", TestSetup.getUUIDString())).toString();
			String sqlFileFullPath = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data", "sql", "AutomationSeedScript-Minimal.sql").toString();
			SqlCmdUtility.executeSQLFile(TestContext.INSTANCE.getDbIpAddress(), TestContext.INSTANCE.getDbPortNo(), TestContext.INSTANCE.getDbName(),
					TestContext.INSTANCE.getDbUser(), TestContext.INSTANCE.getDbPassword(), sqlFileFullPath, sqlCmdLogFilePath);
		} finally {
			connection.close();
		}
	}

	public static void executeGISCustomerDataSeed() throws Exception {
		Log.method("DbSeedExecutor.executeGISCustomerDataSeed");
		String sqlCmdLogFilePath = Paths.get(TestSetup.getRootPath(), String.format("sqlcmd-%s.log", TestSetup.getUUIDString())).toString();
		String sqlFileFullPath = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data", "sql", "AutomationSeedScript-GISCustomers.sql").toString();
		SqlCmdUtility.executeSQLFile(TestContext.INSTANCE.getDbIpAddress(), TestContext.INSTANCE.getDbPortNo(), TestContext.INSTANCE.getDbName(),
				TestContext.INSTANCE.getDbUser(), TestContext.INSTANCE.getDbPassword(), sqlFileFullPath, sqlCmdLogFilePath);
	}

	public static void executeGISCustomerDataSeedForSingleCustomer(String customerName) throws Exception {
		Log.method("DbSeedExecutor.executeGISCustomerDataSeedForSingleCustomer", customerName);
		String sqlCmdLogFilePath = Paths.get(TestSetup.getRootPath(), String.format("sqlcmd-%s.log", TestSetup.getUUIDString())).toString();
		String sqlTemplateFileFullPath = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data", "sql", "AutomationSeedScript-GISSingleCustomer.sql.template").toString();
		String sqlFileFullPath = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data", "sql",
				String.format("AutomationSeedScript-GISSingleCustomer-%s.sql", TestSetup.getUUIDString())).toString();

		String customerId = new Customer().get(customerName).getId();

		// Cleanup Analyzers for customer.
		Analyzer.getAnalyzersForCustomer(customerId).stream()
			.forEach(a -> a.cascadeDeleteAnalyzer());

		// Re-execute GIS customer data seed for this specific customer.

		// Create a working copy of the template file.
		Files.copy(Paths.get(sqlTemplateFileFullPath), Paths.get(sqlFileFullPath));

		// Update the working copy.
		Hashtable<String, String> placeholderMap = new Hashtable<String, String>();
		placeholderMap.put("%CUSTOMER_ID%", customerId);
		placeholderMap.put("%CUSTOMER_NAME%", customerName);
		String seedLocationId = CustomerWithGisDataPool.getSeedLocationIdForCustomer(customerId);
		if (seedLocationId == null) {
			placeholderMap.put("%SKIP_LOCATION_CLEANUP%", "1");
			placeholderMap.put("%LOCATION_ID_FROM_SEED%", "00000000-0000-0000-0000-000000000000");
		} else {
			placeholderMap.put("%SKIP_LOCATION_CLEANUP%", "0");
			placeholderMap.put("%LOCATION_ID_FROM_SEED%", seedLocationId);
		}

		FileUtility.updateFile(sqlFileFullPath, placeholderMap);

		// Use the working copy in the SQL command.
		SqlCmdUtility.executeSQLFile(TestContext.INSTANCE.getDbIpAddress(), TestContext.INSTANCE.getDbPortNo(), TestContext.INSTANCE.getDbName(),
				TestContext.INSTANCE.getDbUser(), TestContext.INSTANCE.getDbPassword(), sqlFileFullPath, sqlCmdLogFilePath);
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

		surveySeedBuilderCache = new DbSeedBuilderCache();

		if (surveyTags == null) {
			// Use default survey tags if NOT specified by caller.
			surveyTags = PICARRO_CUSTOMER_SURVEYS;
		}

		try {
			connection = ConnectionFactory.createConnection();
			connection.setAutoCommit(false);
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

					// Store all the seed builder in cache for future verification.
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
					connection.commit();

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
		        }
			}
		} catch (Exception ex) {
        	Log.error(String.format("EXCEPTION in executeSurveyDataSeed() - %s", ExceptionUtility.getStackTraceString(ex)));
		} finally {
			connection.setAutoCommit(true);
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

	private static boolean isSeedCustomer(String customerId) {
		String[] seedCustomerNames = {CUSTOMER_PICARRO, CUSTOMER_SQACUS, CUSTOMER_PGE};
		for (String custName : seedCustomerNames) {
			String custId = Customer.getCustomer(custName).getId();
			if (!customerNameIdMap.containsKey(custId)) {
				customerNameIdMap.put(custId, custName);
			}
		}

		return customerNameIdMap.containsKey(customerId);
	}

	/* Methods for pushing new refreshed GIS seed data (CustomerBoundaryType, CustomerMaterialType, Boundary and Asset) */

	public static void executeGisRefreshDataSeed() throws Exception {
		Log.method("DbSeedExecutor.executeGisRefreshDataSeed");
		executeGisRefreshDataSeed(null /*customerId*/); // default -> Picarro customer.
		executeGisRefreshDataSeed(Customer.getCustomer(CUSTOMER_SQACUS).getId());
		executeGisRefreshDataSeed(Customer.getCustomer(CUSTOMER_PGE).getId());
	}

	public static void executeGisRefreshDataSeed(String customerId) throws Exception {
		Log.method("DbSeedExecutor.executeGisRefreshDataSeed", customerId);
		boolean isCustomerSpecified = true;
		Customer customer = Customer.getCustomer(CUSTOMER_PICARRO);
		String picarroCustomerId = customer.getId();
		if (customerId == null) {
			isCustomerSpecified = false;
			customerId = picarroCustomerId;
		}

		Connection connection = null;
		DatFileBuilder datFileBuilder = null;
		String assetDatFile = null;
		String boundaryDatFile = null;
		try {
			connection = ConnectionFactory.createConnection();
			connection.setAutoCommit(false);

			DbStateVerifier dbStateVerifier = new DbStateVerifier(connection);
			ensureGISDatFilesArePresent();

			String datFolder = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data", "sql").toString();
			int expectedAssetCount = FileUtility.getLineCountInFile(Paths.get(datFolder, ASSET_DAT_FILE), FileUtility.ENCODING_UTF16LE) - 1;
			int expectedBoundaryCount = FileUtility.getLineCountInFile(Paths.get(datFolder, BOUNDARY_DAT_FILE), FileUtility.ENCODING_UTF16LE) - 1;
			if (dbStateVerifier.isGisRefreshSeedPresent(customerId, expectedAssetCount, expectedBoundaryCount)) {
				Log.info("GIS Refresh DB seed is already present. SKIP execution.");
				return;
			}

			assetDatFile = Paths.get(datFolder, ASSET_DAT_FILE).toString();
			boundaryDatFile = Paths.get(datFolder, BOUNDARY_DAT_FILE).toString();
			if (isCustomerSpecified) {
				datFileBuilder = new DatFileBuilder();
				assetDatFile = datFileBuilder.build(assetDatFile, picarroCustomerId, customerId);
				boundaryDatFile = datFileBuilder.build(boundaryDatFile, picarroCustomerId, customerId);;
			}

			Log.info("GIS Refresh DB seed NOT found. Executing SQL script to push GIS refresh DB seed...");
			String sqlCmdLogFilePath = Paths.get(TestSetup.getRootPath(), String.format("sqlcmd-%s.log", TestSetup.getUUIDString())).toString();

			if (!isRunningOnLocalDB()) {
				Log.info("[Step-0] Transfering .dat files to DB server ...");
				BcpDatFileTransferUtility.transferDatFileToDBServer(Paths.get(assetDatFile).getFileName().toString());
				BcpDatFileTransferUtility.transferDatFileToDBServer(Paths.get(boundaryDatFile).getFileName().toString());
			}

			Log.info("[Step-1] Preparing pre-GIS push steps ...");
			String sqlFileFullPath = Paths.get(datFolder, "AutomationSeedScript-PreGISLoad.sql").toString();
			SqlCmdUtility.executeSQLFile(TestContext.INSTANCE.getDbIpAddress(), TestContext.INSTANCE.getDbPortNo(), TestContext.INSTANCE.getDbName(),
					TestContext.INSTANCE.getDbUser(), TestContext.INSTANCE.getDbPassword(), sqlFileFullPath, sqlCmdLogFilePath);

			Log.info("[Step-2] Pushing GIS refresh Asset/Boundary data ...");
			sqlFileFullPath = Paths.get(datFolder, AUTOMATION_SEED_SCRIPT_LOAD_GIS_SQL).toString();
			String workingSqlFile = TestSetup.getUUIDString() + "_" + AUTOMATION_SEED_SCRIPT_LOAD_GIS_SQL;
			String workingSqlFullPath = Paths.get(datFolder, workingSqlFile).toString();
			Files.copy(Paths.get(sqlFileFullPath), Paths.get(workingSqlFullPath));
			Hashtable<String, String> placeholderMap = new Hashtable<String, String>();
			placeholderMap.put("%ASSET_DAT_FILE_PATH%", Paths.get(assetDatFile).toString());
			placeholderMap.put("%BOUNDARY_DAT_FILE_PATH%", Paths.get(boundaryDatFile).toString());
			placeholderMap.put("%DB_NAME%", TestContext.INSTANCE.getDbName());
			placeholderMap.put("%DB_USER%", TestContext.INSTANCE.getDbUser());
			placeholderMap.put("%DB_PASSWORD%", TestContext.INSTANCE.getDbPassword());
			placeholderMap.put("%SERVER_IP_ADDR%", TestContext.INSTANCE.getDbIpAddress());
			FileUtility.updateFile(workingSqlFullPath, placeholderMap);
			SqlCmdUtility.executeSQLFile(TestContext.INSTANCE.getDbIpAddress(), TestContext.INSTANCE.getDbPortNo(), TestContext.INSTANCE.getDbName(),
					TestContext.INSTANCE.getDbUser(), TestContext.INSTANCE.getDbPassword(), workingSqlFullPath, sqlCmdLogFilePath);

			Log.info("[Step-3] Executing post-GIS push steps ...");
			sqlFileFullPath = Paths.get(datFolder, "AutomationSeedScript-PostGISLoad.sql").toString();
			SqlCmdUtility.executeSQLFile(TestContext.INSTANCE.getDbIpAddress(), TestContext.INSTANCE.getDbPortNo(), TestContext.INSTANCE.getDbName(),
					TestContext.INSTANCE.getDbUser(), TestContext.INSTANCE.getDbPassword(), sqlFileFullPath, sqlCmdLogFilePath);

			connection.commit();

		} finally {
			if (assetDatFile != null && boundaryDatFile != null) {
				if (!isRunningOnLocalDB()) {
					Log.info("Cleanup remote bcp .dat files ...");
					String[] cleanupFilesPath = {assetDatFile, boundaryDatFile};
					BcpDatFileTransferUtility.cleanupBcpDatFilesOnRemoteMachine(cleanupFilesPath);
				}
			}

			connection.setAutoCommit(true);
			connection.close();
			if (datFileBuilder != null) {
				datFileBuilder.close();
			}
		}
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
		checkExecuteGisSeed(customerId);
	}

	private static void checkExecuteGisSeed(String customerId) throws Exception {
		if (!TestContext.INSTANCE.getTestSetup().isGeoServerEnabled()) {
			executeGisSeedInternal(customerId);
		}
	}

	private static void executeGisSeedInternal(String customerId) throws Exception {
		Log.method("DbSeedExecutor.executeGisSeedInternal", customerId);
		boolean isCustomerSpecified = true;
		if (customerId == null) {
			isCustomerSpecified = false;
			Customer customer = Customer.getCustomer(CUSTOMER_PICARRO);
			customerId = customer.getId();
		}

		boolean isSeedCustomer = isSeedCustomer(customerId);

		Connection connection = null;
		DbSeedBuilderCache dbSeedBuilderCache = new DbSeedBuilderCache();
		CustomerBoundaryTypeDbSeedBuilder customerBoundaryTypeDbSeedBuilder = null;
		CustomerMaterialTypeDbSeedBuilder customerMaterialTypeDbSeedBuilder = null;
		BoundaryDbSeedBuilder boundaryDbSeedBuilder = null;
		AssetDbSeedBuilder assetDbSeedBuilder = null;

		try
        {
			connection = ConnectionFactory.createConnection();
			connection.setAutoCommit(false);

        	customerBoundaryTypeDbSeedBuilder = new CustomerBoundaryTypeDbSeedBuilder();
        	customerBoundaryTypeDbSeedBuilder.setDbSeedCache(dbSeedBuilderCache);

        	customerMaterialTypeDbSeedBuilder = new CustomerMaterialTypeDbSeedBuilder();
        	customerMaterialTypeDbSeedBuilder.setDbSeedCache(dbSeedBuilderCache);

        	boundaryDbSeedBuilder = new BoundaryDbSeedBuilder();
        	boundaryDbSeedBuilder.setDbSeedCache(dbSeedBuilderCache);

			DbSeed custBoundaryTypeDbSeed = customerBoundaryTypeDbSeedBuilder.build(isCustomerSpecified ? customerId : null);
			DbSeed custMaterialTypeDbSeed = customerMaterialTypeDbSeedBuilder.build(isCustomerSpecified ? customerId : null);

			DbSeed assetDbSeed = null;
			int expectedAssetCount = 0;   // 0 - to ignore pushing assets from obsolete seed data for seed customer.
			if (!isSeedCustomer) {
				// For new customer - build all Assets and Boundaries
				assetDbSeedBuilder = new AssetDbSeedBuilder();
				assetDbSeedBuilder.setDbSeedCache(dbSeedBuilderCache);
				assetDbSeed = assetDbSeedBuilder.build(customerId);
				boundaryDbSeedBuilder.setSeedFilePath(BoundaryDbSeedBuilder.SEED_DATA_FOLDER, BoundaryDbSeedBuilder.SEED_FILE_NAME_NEW_CUSTOMER);
				expectedAssetCount = assetDbSeed.getInsertStatements().size();
			}

			DbSeed boundaryDbSeed = boundaryDbSeedBuilder.build(customerId);
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

			if (!isSeedCustomer) {
				// For new customer - push assets.
				executeSeed(connection, assetDbSeed);
			}

			connection.commit();

        } catch (Exception ex) {
        	Log.error(String.format("EXCEPTION in executeGisSeed() - %s", ExceptionUtility.getStackTraceString(ex)));
        } finally {
        	connection.setAutoCommit(true);
            connection.close();
            // cleanup seed builders.
            closeDbSeedBuilder(customerBoundaryTypeDbSeedBuilder);
            closeDbSeedBuilder(customerMaterialTypeDbSeedBuilder);
            closeDbSeedBuilder(boundaryDbSeedBuilder);
        }
	}

	public static void cleanUpGisSeed(String customerId) throws Exception {
		Log.method("DbSeedExecutor.cleanUpGisSeed", customerId);
		checkCleanUpGisSeed(customerId);
	}

	private static void checkCleanUpGisSeed(String customerId) throws Exception {
		Log.method("DbSeedExecutor.checkCleanUpGisSeed", customerId);
		if (!TestContext.INSTANCE.getTestSetup().isGeoServerEnabled()) {
			cleanUpGisSeedInternal(customerId);
		} else {
			Log.info("GeoServer enabled. GIS seed cleanup skipped ...");
		}
	}

	private static void cleanUpGisSeedInternal(String customerId) throws Exception {
		Log.method("DbSeedExecutor.cleanUpGisSeedInternal", customerId);

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
            		Log.info(String.format("Cleanup Statement -> %s", cleanupStmt));
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

	private static boolean isRunningOnLocalDB() {
		return TestContext.INSTANCE.getDbIpAddress().equals(Constants.LOCALHOST_IP);
	}

	private static void ensureGISDatFilesArePresent() throws IOException {
		Log.method("DbSeedExecutor.ensureGISDatFilesPresent");
		String datFolder = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data", "sql").toString();
		boolean assetDatFileExists = FileUtility.fileExists(Paths.get(datFolder, ASSET_DAT_FILE).toString());
		boolean boundaryDatFileExists = FileUtility.fileExists(Paths.get(datFolder, BOUNDARY_DAT_FILE).toString());
		if (!assetDatFileExists || !boundaryDatFileExists) {
			Log.info(String.format("AssetDatFile FOUND=[%b], BoundaryDatFile FOUND=[%b]", assetDatFileExists, boundaryDatFileExists));
			String datZipFileFullPath = Paths.get(datFolder, ASSET_BOUNDARY_BA_ZIP).toString();
			ZipUtility unZip = new ZipUtility();
			unZip.unZip(datZipFileFullPath, datFolder);
		}
	}
}