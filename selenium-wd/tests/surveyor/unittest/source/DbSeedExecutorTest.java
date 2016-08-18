package surveyor.unittest.source;

import org.junit.Test;
import org.junit.BeforeClass;

import common.source.CSVUtility;
import common.source.FileUtility;
import common.source.Log;
import common.source.NumberUtility;
import common.source.TestContext;
import common.source.TestSetup;

import static surveyor.scommon.source.SurveyorConstants.CUSTOMER_PICARRO;
import static surveyor.scommon.source.SurveyorConstants.EPSILON;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;

import surveyor.dataaccess.source.ConnectionFactory;
import surveyor.dataaccess.source.Customer;
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

public class DbSeedExecutorTest {
	
	@BeforeClass
	public static void BeforeClass()	{
		TestSetup testSetup = new TestSetup(false /* skip initialization */);
		String rootPath;
		try {
			rootPath = TestSetup.getRootPath();
			testSetup.loadTestProperties(rootPath);
		} catch (IOException e) {
			Log.error(e.getMessage());
		}
		testSetup.initializeDBProperties();
		TestContext.INSTANCE.setTestSetup(testSetup);
	}
	
	@Test
	public void execute01_GenericSeedTest() throws Exception {
		DbSeedExecutor.executeGenericDataSeed();
		verifyGenericSeedDataIsPresent();
	}

	@Test
	public void execute02_PicarroCustomerGisSeedTest() throws Exception {
		// By default is no customerId is specified the GIS data is pushed for Picarro customer.
		DbSeedExecutor.executeGisSeed();
		verifyGisSeedDataIsPresent(Customer.getCustomer(CUSTOMER_PICARRO).getId());
	}
		
	@Test
	public void execute03_SurveyDataSeedTest() throws Exception {
		DbSeedExecutor.executeSurveyDataSeed();
		verifySurveySeedDataIsPresent();
	}

	@Test
	public void execute04_executeAllSeedTest() throws Exception {
		DbSeedExecutor.executeAllDataSeed();
		verifySurveySeedDataIsPresent();
		verifyGenericSeedDataIsPresent();
		verifyGisSeedDataIsPresent(Customer.getCustomer(CUSTOMER_PICARRO).getId());
	}

	@Test
	public void execute05_pushAssetBoundariesForSpecificCustomerTest() throws Exception {
		// By default if no customerId is specified the GIS data is pushed for Picarro customer.
		String customerId = "00000000-0000-0000-0000-000000000002";
		DbSeedExecutor.executeGisSeed(customerId);
		verifyGisSeedDataIsPresent(customerId);
	}
	
	/**
	 * NOTES: PGE asset/boundaries seed is required by few Driver view test cases.
	 * @throws Exception
	 */
	@Test
	public void execute06_pushAssetBoundariesForPGECustomerTest() throws Exception {
		String customerId = "E871C797-B62D-EF28-0EA7-39CAE44E5C19";
		DbSeedExecutor.executeGisSeed(customerId);
		verifyGisSeedDataIsPresent(customerId);
	}

	private void verifyGenericSeedDataIsPresent() throws Exception, SQLException {
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
	
	private void verifyGisSeedDataIsPresent(String customerId) throws Exception {
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
	
	private void verifySurveySeedDataIsPresent() throws IOException, FileNotFoundException, SQLException {
		// Verify seed data pushed correctly for each survey tag.
		final String[] surveyTags = {"assessment-1", "assessment-2", "EthaneStnd3","EthaneStnd2","EthaneStnd","EthaneRR","EthaneOpertor2","EthaneOpertor1","Ethane1MinSurvey", 
				"iso-cap-1", "iso-cap-2", "man-pic-1","man-pic-2","op-pic","op-sqacudr","rr-pic","rr-sqacudr-1","rr-sqacudr-2","stnd-pic",
				"standard_test-1", "standard_test-2", "standard_test-3", "stnd-sqacudr","stnd-sqacudr-1","stnd-sqacudr-2","stnd-sqacudr-3"};
		
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
				
				final List<HashMap<String, String>> firstSurveyRow = new CSVUtility().getTopRows(surveyCsvFilePath, 1);
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

				Assert.assertTrue(dbStateVerifier.isCaptureEventSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minCaptureEventCount));
				Assert.assertTrue(dbStateVerifier.isFieldOfViewSeedPresent(surveyId, analyzerId, startEpoch, endEpoch, minFieldOfViewCount));
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
