package surveyor.unittest.source;

import static surveyor.scommon.source.SurveyorConstants.EPSILON;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import common.source.CSVUtility;
import common.source.FileUtility;
import common.source.Log;
import common.source.NumberUtility;
import surveyor.dataaccess.source.Asset;
import surveyor.dataaccess.source.Boundary;
import surveyor.dataaccess.source.ConnectionFactory;
import surveyor.dataaccess.source.CustomerBoundaryType;
import surveyor.dataaccess.source.CustomerMaterialType;
import surveyor.dbseed.source.AnemometerRawDbSeedBuilder;
import surveyor.dbseed.source.AssetDbSeedBuilder;
import surveyor.dbseed.source.BoundaryDbSeedBuilder;
import surveyor.dbseed.source.CaptureEventDbSeedBuilder;
import surveyor.dbseed.source.DbSeed;
import surveyor.dbseed.source.DbSeedBuilderCache;
import surveyor.dbseed.source.DbSeedExecutor;
import surveyor.dbseed.source.DbStateVerifier;
import surveyor.dbseed.source.FieldOfViewDbSeedBuilder;
import surveyor.dbseed.source.GPSRawDbSeedBuilder;
import surveyor.dbseed.source.MeasurementDbSeedBuilder;
import surveyor.dbseed.source.NoteDbSeedBuilder;
import surveyor.dbseed.source.PeakDbSeedBuilder;
import surveyor.dbseed.source.SegmentDbSeedBuilder;
import surveyor.dbseed.source.SurveyConditionDbSeedBuilder;
import surveyor.dbseed.source.SurveyDbSeedBuilder;
import surveyor.dbseed.source.SurveyResultDbSeedBuilder;
import surveyor.scommon.source.BaseTest;

public class DbSeedExecutorBaseTest extends BaseTest {

	protected void verifyGenericSeedDataIsPresent() throws Exception, SQLException {
		// Verify generic seed data is now present in the DB.
		Connection connection = null;
		boolean isGenericSeedPresent = false;
		try {
			connection = ConnectionFactory.createConnection();
			isGenericSeedPresent = new DbStateVerifier(connection).isGenericSeedPresent();
		} finally {
			connection.close();
		}
		Assert.assertTrue(isGenericSeedPresent);
	}

	protected void verifyGisSeedDataIsPresent(String customerId) throws Exception {
		DbSeedBuilderCache dbSeedBuilderCache = new DbSeedBuilderCache();
		BoundaryDbSeedBuilder boundaryDbSeedBuilder = new BoundaryDbSeedBuilder();
    	boundaryDbSeedBuilder.setDbSeedCache(dbSeedBuilderCache);
    	AssetDbSeedBuilder assetDbSeedBuilder = new AssetDbSeedBuilder();
    	assetDbSeedBuilder.setDbSeedCache(dbSeedBuilderCache);

		Connection connection = null;
		boolean isGisSeedPresent = false;
		try {
			connection = ConnectionFactory.createConnection();
			DbSeed assetDbSeed = assetDbSeedBuilder.build(customerId);
			DbSeed boundaryDbSeed = boundaryDbSeedBuilder.build(customerId);
			int expectedAssetCount = assetDbSeed.getInsertStatements().size();
			int expectedBoundaryCount = boundaryDbSeed.getInsertStatements().size();
			DbStateVerifier dbStateVerifier = new DbStateVerifier(connection);
			isGisSeedPresent = dbStateVerifier.isGISSeedPresent(customerId, expectedAssetCount, expectedBoundaryCount);

		} finally {
			connection.close();
		}
		Assert.assertTrue(isGisSeedPresent);
	}

	protected void verifyGisSeedDataIsNotPresent(String customerId) {
		Assert.assertTrue(new Asset().getCount(customerId) == 0);
		Assert.assertTrue(new Boundary().getCount(customerId) == 0);
		Assert.assertTrue(new CustomerMaterialType().getCount(customerId) == 0);
		Assert.assertTrue(new CustomerBoundaryType().getCount(customerId) == 0);
	}

	protected void verifySurveySeedDataIsPresent() throws IOException, FileNotFoundException, SQLException {
		verifySurveySeedDataIsPresent(null);
	}

	protected void verifySurveySeedDataIsPresent(String[] surveyTags) throws IOException, FileNotFoundException, SQLException {
		verifySurveySeedDataIsPresent(surveyTags, false /*isCaptureRowsRestamped*/, false /*isFOVRowsRestamped*/);
	}

	protected void verifySurveySeedDataIsPresent(String[] surveyTags, boolean isCaptureEventRowsRestamped, boolean isFOVRowsRestamped) throws IOException, FileNotFoundException, SQLException {
		// Verify seed data pushed correctly for each survey tag.
		if (surveyTags == null) {
			surveyTags = DbSeedExecutor.PICARRO_CUSTOMER_SURVEYS;
		}

		boolean isRedate = false;
		Connection connection = null;
		try {
			connection = ConnectionFactory.createConnection();
			for (String surveyTag : surveyTags) {
				Log.info(String.format("***** START Verifying survey with tag - '%s' *****", surveyTag));

				SurveyDbSeedBuilder surveyDbSeedBuilder = (SurveyDbSeedBuilder)DbSeedExecutor.getSurveySeedBuilderCache().getDbSeedBuilder(String.format("Survey-%s.csv", surveyTag));
				SurveyConditionDbSeedBuilder surveyConditionDbSeedBuilder = (SurveyConditionDbSeedBuilder)DbSeedExecutor.getSurveySeedBuilderCache().getDbSeedBuilder(String.format("SurveyCondition-%s.csv", surveyTag));
				SurveyResultDbSeedBuilder surveyResultDbSeedBuilder = (SurveyResultDbSeedBuilder)DbSeedExecutor.getSurveySeedBuilderCache().getDbSeedBuilder(String.format("SurveyResult-%s.csv", surveyTag));
				MeasurementDbSeedBuilder measurementDbSeedBuilder = (MeasurementDbSeedBuilder)DbSeedExecutor.getSurveySeedBuilderCache().getDbSeedBuilder(String.format("Measurement-%s.csv", surveyTag));
				GPSRawDbSeedBuilder gpsRawDbSeedBuilder = (GPSRawDbSeedBuilder)DbSeedExecutor.getSurveySeedBuilderCache().getDbSeedBuilder(String.format("GPSRaw-%s.csv", surveyTag));
				AnemometerRawDbSeedBuilder anemometerRawDbSeedBuilder = (AnemometerRawDbSeedBuilder)DbSeedExecutor.getSurveySeedBuilderCache().getDbSeedBuilder(String.format("AnemometerRaw-%s.csv", surveyTag));
				CaptureEventDbSeedBuilder captureEventDbSeedBuilder = (CaptureEventDbSeedBuilder)DbSeedExecutor.getSurveySeedBuilderCache().getDbSeedBuilder(String.format("CaptureEvent-%s.csv", surveyTag));
				FieldOfViewDbSeedBuilder fieldOfViewDbSeedBuilder = (FieldOfViewDbSeedBuilder)DbSeedExecutor.getSurveySeedBuilderCache().getDbSeedBuilder(String.format("FieldOfView-%s.csv", surveyTag));
				PeakDbSeedBuilder peakDbSeedBuilder = (PeakDbSeedBuilder)DbSeedExecutor.getSurveySeedBuilderCache().getDbSeedBuilder(String.format("Peak-%s.csv", surveyTag));
				SegmentDbSeedBuilder segmentDbSeedBuilder = (SegmentDbSeedBuilder)DbSeedExecutor.getSurveySeedBuilderCache().getDbSeedBuilder(String.format("Segment-%s.csv", surveyTag));
				NoteDbSeedBuilder noteDbSeedBuilder = (NoteDbSeedBuilder)DbSeedExecutor.getSurveySeedBuilderCache().getDbSeedBuilder(String.format("Note-%s.csv", surveyTag));

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
				final List<Map<String, String>> firstSurveyRow = new CSVUtility().getTopRows(surveyCsvFilePath, 1);

				final String surveyId = firstSurveyRow.get(0).get("Id");
				final String analyzerId = firstSurveyRow.get(0).get("AnalyzerId");

				String startEpoch = firstSurveyRow.get(0).get("StartEpoch");
				String endEpoch = firstSurveyRow.get(0).get("EndEpoch");
				Double startEpochValue = Double.parseDouble(startEpoch) - EPSILON;
				Double endEpochValue = Double.parseDouble(endEpoch) + EPSILON;
				startEpoch = NumberUtility.formatString(startEpochValue, 10);
				endEpoch = NumberUtility.formatString(endEpochValue, 10);

				Assert.assertTrue(dbStateVerifier.isSurveySeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minSurveyCount));
				Assert.assertTrue(dbStateVerifier.isSurveyConditionSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minSurveyConditionCount));
				Assert.assertTrue(dbStateVerifier.isSurveyResultSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minSurveyResultCount));

				if (!isRedate) {
					Assert.assertTrue(dbStateVerifier.isMeasurementSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minMeasurementCount, false));
					Assert.assertTrue(dbStateVerifier.isGPSRawSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minGPSRawCount, false));
					Assert.assertTrue(dbStateVerifier.isAnemometerRawSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minAnemometerRawCount, false));
				} else {
					boolean checkAnalyzerRowsOnly = false;
					checkAnalyzerRowsOnly = (measurementDbSeedBuilder.getStartEpoch() == 0);
					String startEpochWithRedate = NumberUtility.formatString(measurementDbSeedBuilder.getStartEpoch() - EPSILON, 10);
					String endEpochWithRedate = NumberUtility.formatString(measurementDbSeedBuilder.getEndEpoch() + EPSILON, 10);
					Assert.assertTrue(dbStateVerifier.isMeasurementSeedPresent(surveyId, analyzerId, startEpochWithRedate, endEpochWithRedate, minMeasurementCount, checkAnalyzerRowsOnly));

					checkAnalyzerRowsOnly = (gpsRawDbSeedBuilder.getStartEpoch() == 0);
					startEpochWithRedate = NumberUtility.formatString(gpsRawDbSeedBuilder.getStartEpoch() - EPSILON, 10);
					endEpochWithRedate = NumberUtility.formatString(gpsRawDbSeedBuilder.getEndEpoch() + EPSILON, 10);
					Assert.assertTrue(dbStateVerifier.isGPSRawSeedPresent(surveyId, analyzerId, startEpochWithRedate, endEpochWithRedate, minGPSRawCount, checkAnalyzerRowsOnly));

					checkAnalyzerRowsOnly = (anemometerRawDbSeedBuilder.getStartEpoch() == 0);
					startEpochWithRedate = NumberUtility.formatString(anemometerRawDbSeedBuilder.getStartEpoch() - EPSILON, 10);
					endEpochWithRedate = NumberUtility.formatString(anemometerRawDbSeedBuilder.getEndEpoch() + EPSILON, 10);
					Assert.assertTrue(dbStateVerifier.isAnemometerRawSeedPresent(surveyId, analyzerId, startEpochWithRedate, endEpochWithRedate, minAnemometerRawCount, checkAnalyzerRowsOnly));
				}

				if (isCaptureEventRowsRestamped) {
					Assert.assertTrue(dbStateVerifier.isCaptureEventSeedPresent(surveyId, analyzerId, String.valueOf(Float.MIN_VALUE), String.valueOf(Float.MAX_VALUE), minCaptureEventCount));
				} else {
					Assert.assertTrue(dbStateVerifier.isCaptureEventSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minCaptureEventCount));
				}

				if (isFOVRowsRestamped) {
					Assert.assertTrue(dbStateVerifier.isFieldOfViewSeedPresent(surveyId, analyzerId, String.valueOf(Float.MIN_VALUE), String.valueOf(Float.MAX_VALUE), minFieldOfViewCount));
				} else {
					Assert.assertTrue(dbStateVerifier.isFieldOfViewSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minFieldOfViewCount));
				}

				Assert.assertTrue(dbStateVerifier.isPeakSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minPeakCount));
				Assert.assertTrue(dbStateVerifier.isSegmentSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minSegmentCount));
				Assert.assertTrue(dbStateVerifier.isNoteSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minNoteCount));

				Log.info(String.format("----- DONE Verifying survey with tag - '%s' -----", surveyTag));
			}
		} finally {
			connection.close();
		}
	}
}