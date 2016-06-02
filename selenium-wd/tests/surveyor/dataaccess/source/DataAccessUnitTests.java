package surveyor.dataaccess.source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;

import common.source.Log;
import common.source.TestContext;
import common.source.TestSetup;

public class DataAccessUnitTests {

	public static void main(String[] args) {
		// Initialize TestSetup to instantiate the TestContext and DB connection parameters.
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

		Log.info("Executing testSurvey_GetSurveyByTag_Valid() ...");
		testSurvey_GetSurveyByTag_Valid();
		Log.info("Executing testSurvey_GetSurveyByTag_Invalid() ...");
		testSurvey_GetSurveyByTag_Invalid();
		Log.info("Executing testReportGap_GetReportGapByGapNumber_Valid() ...");
		testReportGap_GetReportGapByGapNumber_Valid();
		Log.info("Executing testReportGap_GetReportGapByGapNumber_Invalid() ...");
		testReportGap_GetReportGapByGapNumber_Invalid();
		Log.info("Executing testUser_GetUserByName_Valid() ...");
		testUser_GetUserByName_Valid();
		Log.info("Executing testUser_GetUserByName_Invalid() ...");
		testUser_GetUserByName_Invalid();
		Log.info("Executing testReportCompliance_GetReportComplianceByReportId_Valid() ...");
		testReportCompliance_GetReportComplianceByReportId_Valid();
		Log.info("Executing testReportCompliance_GetReportComplianceByReportId_Invalid() ...");
		testReportCompliance_GetReportComplianceByReportId_Invalid();
		Log.info("Executing testReport_GetReportByReportTitle_Valid() ...");
		testReport_GetReportByReportTitle_Valid();
		Log.info("Executing testReport_GetReportByReportTitle_Invalid() ...");
		testReport_GetReportByReportTitle_Invalid();
		Log.info("Executing testStoredProcSystemHistory_GetStoredProcSystemHistoryByReportId_Valid() ...");
		testStoredProcSystemHistory_GetStoredProcSystemHistoryByReportId_Valid();
		Log.info("Executing testStoredProcComplianceAssessmentGetReportDrivingSurveys_getReportDrivingSurveys_Valid() ...");
		testStoredProcComplianceAssessmentGetReportDrivingSurveys_getReportDrivingSurveys_Valid();
		Log.info("Executing testStoredProcComplianceGetIsotopics_getReportIsotopics_Valid() ...");
		testStoredProcComplianceGetIsotopics_getReportIsotopics_Valid();
		Log.info("Executing testStoredProcComplianceGetIndications_getReportIndications_Valid() ...");
		testStoredProcComplianceGetIndications_getReportIndications_Valid();
		Log.info("Executing testReport_GetReportViewByReportTitle_Valid() ...");
		testReport_GetReportViewByReportTitle_Valid();
		Log.info("Executing testGetMapTypeId_Valid().....");
		testGetMapTypeId_Valid();
		Log.info("Executing testStoredProcComplianceGetGaps_getReportGaps_Valid().....");
		testStoredProcComplianceGetGaps_getReportGaps_Valid();
		Log.info("Executing testCaptureEvent_GetCaptureEventBySurveyId_Valid() ...");
		testCaptureEvent_GetCaptureEventBySurveyId_Valid();
		Log.info("Executing testCaptureEvent_GetCaptureEventBySurveyId_Invalid() ...");
		testCaptureEvent_GetCaptureEventBySurveyId_Invalid();
		Log.info("Executing testMeasurements_getMeasurements_Valid() ...");
		testMeasurements_getMeasurements_Valid();
		Log.info("Executing testMeasurements_getMeasurements_Invalid() ...");
		testMeasurements_getMeasurements_Invalid();
		Log.info("Executing testPeaks_getPeaks_Valid() ...");
		testPeaks_getPeaks_Valid();
		Log.info("Executing testPeaks_getPeaks_Invalid() ...");
		testPeaks_getPeaks_Invalid();
		Log.info("Executing testSurveyModeTypeId_getSurveyModeTypeId_valid() ...");
		testSurveyModeTypeId_getSurveyModeTypeId_valid();
		Log.info("Executing testSurveyModeTypeId_getSurveyModeTypeId_Invalid() ...");
		testSurveyModeTypeId_getSurveyModeTypeId_Invalid();
		Log.info("Executing testEQ_getDrivingSurvey_Valid() ...");
		testEQ_getDrivingSurvey_Valid();
		Log.info("Executing testEQ_getDrivingSurvey_Invalid() ...");
		testEQ_getDrivingSurvey_Invalid();
		Log.info("Executing testEQ_getEQData_Valid() ...");
		testEQ_getEQData_Valid();
		Log.info("Executing testEQ_getEQData_Invalid() ...");
		testEQ_getEQData_Invalid();
		Log.info("Executingtest StoredProcLisaInvestigationShowIndication_Valid()..." );
		testStoredProcLisaInvestigationShowIndication_Valid();
		Log.info("Executingtest StoredProcLisaInvestigationShowIndication_Invalid()..." );
		testStoredProcLisaInvestigationShowIndication_Invalid();

		Log.info("DONE!");

	}

	private static void testSurvey_GetSurveyByTag_Valid() {
		String validTag = "stnd-sqacudr";
		Survey objSurvey = Survey.getSurvey(validTag);
		Assert.assertTrue(objSurvey != null, "Value cannot be NULL.");
	}

	private static void testSurvey_GetSurveyByTag_Invalid() {
		String invalidTag = "Invalid value";
		Survey objSurvey = Survey.getSurvey(invalidTag);
		Assert.assertTrue(objSurvey == null, "Value should be NULL.");
	}

	private static void testReportGap_GetReportGapByGapNumber_Valid() {
		String validGapNumber = "K4";
		ReportGap objReportGap = ReportGap.getReportGap(validGapNumber);
		Assert.assertTrue(objReportGap != null, "Value cannot be NULL.");
	}

	private static void testReportGap_GetReportGapByGapNumber_Invalid() {
		String invalidGapNumber = "Invalid value";
		ReportGap objReportGap = ReportGap.getReportGap(invalidGapNumber);
		Assert.assertTrue(objReportGap == null, "Value should be NULL.");
	}

	private static void testUser_GetUserByName_Valid() {
		String validName = "Administrator";
		User objUser = User.getUser(validName);
		Assert.assertTrue(objUser != null, "Value cannot be NULL.");
	}

	private static void testUser_GetUserByName_Invalid() {
		String invalidName = "Invalid value";
		User objUser = User.getUser(invalidName);
		Assert.assertTrue(objUser == null, "Value should be NULL.");
	}

	private static void testReportCompliance_GetReportComplianceByReportId_Valid() {
		String validReportId = "D12F21F5-3FA4-C9EB-C6D6-39D47CC7AB16";
		ReportCompliance objReportCompliance = ReportCompliance.getReportCompliance(validReportId);
		Assert.assertTrue(objReportCompliance != null, "Value cannot be NULL.");
	}

	private static void testReportCompliance_GetReportComplianceByReportId_Invalid() {
		String invalidReportId = "D12F21F5-3FA4-C9EB-C6D6-00007CC7AB16";
		ReportCompliance objReportCompliance = ReportCompliance.getReportCompliance(invalidReportId);
		Assert.assertTrue(objReportCompliance == null, "Value should be NULL.");
	}

	private static void testReport_GetReportByReportTitle_Valid() {
		String validReportTitle = "SmokeTest";
		Report objReport = Report.getReport(validReportTitle);
		Assert.assertTrue(objReport != null, "Value cannot be NULL.");
	}

	private static void testReport_GetReportByReportTitle_Invalid() {
		String invalidReportTitle = "Invalid value";
		Report objReport = Report.getReport(invalidReportTitle);
		Assert.assertTrue(objReport == null, "Value should be NULL.");
	}

	private static void testReport_GetReportViewByReportTitle_Valid() {
		String validReportId = "5B8CBAD2-DAF4-F04F-5F3F-39D62D5A3B25";
		List<ReportView> objReport = ReportView.getReportView(validReportId);
		Assert.assertTrue(!objReport.isEmpty(), "Value cannot be NULL.");
	}

	private static void testGetMapTypeId_Valid() {
		String validMapType = "Map";
		String mapId = BaseMapType.getBaseMapTypeId(validMapType);
		Assert.assertTrue(mapId != null, "Value cannot be NULL.");
	}

	private static void testStoredProcSystemHistory_GetStoredProcSystemHistoryByReportId_Valid() {
		String validReportId = "A4E41CC1-7E43-2918-A24D-39D577864CB0";
		ArrayList<StoredProcSystemHistory> objStoredProcSystemHistory = StoredProcSystemHistory.getSystemHistory(validReportId);
		Iterator<StoredProcSystemHistory> iterator = objStoredProcSystemHistory.iterator();
		while (iterator.hasNext()) {
			StoredProcSystemHistory storedProcSystemHistoryObj = iterator.next();
			Assert.assertTrue(storedProcSystemHistoryObj != null, "Value cannot be NULL.");

		}
	}

	private static void testStoredProcComplianceAssessmentGetReportDrivingSurveys_getReportDrivingSurveys_Valid() {
		String validReportId = "5B8CBAD2-DAF4-F04F-5F3F-39D62D5A3B25";
		ArrayList<StoredProcComplianceAssessmentGetReportDrivingSurveys> listStoredProcDrivingSurvey = StoredProcComplianceAssessmentGetReportDrivingSurveys.getReportDrivingSurveys(validReportId);
		Iterator<StoredProcComplianceAssessmentGetReportDrivingSurveys> iterator = listStoredProcDrivingSurvey.iterator();
		Assert.assertTrue(iterator.hasNext());
		while (iterator.hasNext()) {
			StoredProcComplianceAssessmentGetReportDrivingSurveys objStoredProcDrivingSurvey = iterator.next();
			Assert.assertTrue(objStoredProcDrivingSurvey != null, "Value cannot be NULL.");

		}
	}

	private static void testStoredProcComplianceGetIsotopics_getReportIsotopics_Valid() {
		String validReportId = "5B8CBAD2-DAF4-F04F-5F3F-39D62D5A3B25";
		ArrayList<StoredProcComplianceGetIsotopics> listStoredProcIsotopics = StoredProcComplianceGetIsotopics.getReportIsotopics(validReportId);
		Iterator<StoredProcComplianceGetIsotopics> iterator = listStoredProcIsotopics.iterator();
		Assert.assertTrue(iterator.hasNext());
		while (iterator.hasNext()) {
			StoredProcComplianceGetIsotopics objStoredProcIsotopics = iterator.next();
			Assert.assertTrue(objStoredProcIsotopics != null, "Value cannot be NULL.");

		}
	}

	private static void testStoredProcComplianceGetIndications_getReportIndications_Valid() {
		String validReportId = "0e64a8e7-2305-b79c-3315-39d7c71ebda3";
		ArrayList<StoredProcComplianceGetIndications> listStoredProcIndications = StoredProcComplianceGetIndications.getReportIndications(validReportId);
		Iterator<StoredProcComplianceGetIndications> iterator = listStoredProcIndications.iterator();
		Assert.assertTrue(iterator.hasNext());
		while (iterator.hasNext()) {
			StoredProcComplianceGetIndications objStoredProcIndications = iterator.next();
			Assert.assertTrue(objStoredProcIndications != null, "Value cannot be NULL.");

		}
	}

	private static void testStoredProcComplianceGetGaps_getReportGaps_Valid() {
		String validReportId = "955B11D6-0E82-9DA5-AC4B-39D63B0A84A6";
		ArrayList<StoredProcComplianceGetGaps> listStoredProcGaps = StoredProcComplianceGetGaps.getReportGaps(validReportId);
		Iterator<StoredProcComplianceGetGaps> iterator = listStoredProcGaps.iterator();
		Assert.assertTrue(iterator.hasNext());
		while (iterator.hasNext()) {
			StoredProcComplianceGetGaps objStoredProcGaps = iterator.next();
			Assert.assertTrue(objStoredProcGaps != null, "Value cannot be NULL.");

		}
	}

	private static void testCaptureEvent_GetCaptureEventBySurveyId_Valid() {
		String validSurveyId = "c8782024-cc65-b53a-5317-39d4b4f4731f";
		CaptureEvent objCaptureEvent = CaptureEvent.getCaptureEvent(validSurveyId);
		Assert.assertTrue(objCaptureEvent != null, "Value cannot be NULL.");
	}

	private static void testCaptureEvent_GetCaptureEventBySurveyId_Invalid() {
		String surveyInvalidId = "D12F21F5-3FA4-C9EB-C6D6-00007CC7AB16";
		CaptureEvent objCaptureEvent = CaptureEvent.getCaptureEvent(surveyInvalidId);
		Assert.assertTrue(objCaptureEvent == null, "Value should be NULL.");
	}

	private static void testMeasurements_getMeasurements_Valid() {
		String analyzerId = "00000015-DB64-FDE7-7E67-39C8AC533D49";
		String startEpochTime = "1417806315";
		String endEpochTime = "1417806445";
		List<Measurement> measurements = Measurement.getMeasurements(analyzerId, startEpochTime, endEpochTime);
		Iterator<Measurement> iterator = measurements.iterator();
		Assert.assertTrue(iterator.hasNext());
		while (iterator.hasNext()) {
			Measurement objMeasurement = iterator.next();
			Assert.assertTrue(objMeasurement != null, "Value cannot be NULL.");
		}
	}

	private static void testMeasurements_getMeasurements_Invalid() {
		String analyzerInvalidId = "D12F21F5-3FA4-C9EB-C6D6-00007CC7AB16";
		String startEpochTime = "0.00000";
		String endEpochTime = "0.00000";
		List<Measurement> measurements = Measurement.getMeasurements(analyzerInvalidId, startEpochTime, endEpochTime);
		Iterator<Measurement> iterator = measurements.iterator();
		Assert.assertTrue(!iterator.hasNext());
	}

	private static void testPeaks_getPeaks_Valid() {
		String analyzerId = "00000015-DB64-FDE7-7E67-39C8AC533D49";
		Double startEpochTime = 1435339420.59089D;
		Double endEpochTime = 1435339788.07878D;
		String surveyModeTypeId = "4901E67A-4C00-4436-ADC0-9CFB277BB311";
		List<Peak> peaks = Peak.getPeaks(analyzerId, startEpochTime, endEpochTime, surveyModeTypeId);
		Iterator<Peak> iterator = peaks.iterator();
		Assert.assertTrue(iterator.hasNext());
		while (iterator.hasNext()) {
			Peak objPeak = iterator.next();
			Assert.assertTrue(objPeak != null, "Value cannot be NULL.");
		}
	}

	private static void testPeaks_getPeaks_Invalid() {
		String analyzerInvalidId = "D12F21F5-3FA4-C9EB-C6D6-00007CC7AB16";
		Double startEpochTime = 0.00000D;
		Double endEpochTime = 0.00000D;
		String surveyModeTypeInvalidId = "D12F21F5-3FA4-C9EB-C6D6-00007CC7AB16";
		List<Peak> peaks = Peak.getPeaks(analyzerInvalidId, startEpochTime, endEpochTime, surveyModeTypeInvalidId);
		Iterator<Peak> iterator = peaks.iterator();
		Assert.assertTrue(!iterator.hasNext());
	}

	private static void testSurveyModeTypeId_getSurveyModeTypeId_valid() {
		String id = "B310238A-A5AE-4E94-927B-F0F165E24522";
		SurveyModeType objSurveyModeType = SurveyModeType.getSurveyModeType(id);
		Assert.assertTrue(objSurveyModeType != null, "Value cannot be NULL.");
	}

	private static void testSurveyModeTypeId_getSurveyModeTypeId_Invalid() {
		String id = "B310238A-A5AE-4E94-927B-0FF165E24522";
		SurveyModeType objSurveyModeType = SurveyModeType.getSurveyModeType(id);
		Assert.assertTrue(objSurveyModeType == null, "Value must be NULL.");
	}

	private static void testEQ_getDrivingSurvey_Valid() {
		ArrayList<StoredProcEQAddedSurveys> list = StoredProcEQAddedSurveys.getReportDrivingSurveys("8be7d46f-2852-a801-768b-39d3c815d6fa");
		Iterator<StoredProcEQAddedSurveys> iterator = list.iterator();
		Assert.assertTrue(iterator.hasNext());
		while (iterator.hasNext()) {
			StoredProcEQAddedSurveys obj = iterator.next();
			Assert.assertTrue(obj != null, "Value cannot be NULL.");
		}
	}

	private static void testEQ_getDrivingSurvey_Invalid() {
		ArrayList<StoredProcEQAddedSurveys> list = StoredProcEQAddedSurveys.getReportDrivingSurveys("43c29b0a-f669-8897-00b7-39d433f6f85e");
		Iterator<StoredProcEQAddedSurveys> iterator = list.iterator();
		Assert.assertTrue(!iterator.hasNext());
	}

	private static void testEQ_getEQData_Valid() {
		ArrayList<StoredProcEQGetEQData> list = StoredProcEQGetEQData.getEQData("34c29b0a-f669-8897-00b7-39d433f6f8e5");
		Iterator<StoredProcEQGetEQData> iterator = list.iterator();
		Assert.assertTrue(iterator.hasNext());
		while (iterator.hasNext()) {
			StoredProcEQGetEQData obj = iterator.next();
			Assert.assertTrue(obj != null, "Value cannot be NULL.");
		}
	}

	private static void testEQ_getEQData_Invalid() {
		ArrayList<StoredProcEQGetEQData> list = StoredProcEQGetEQData.getEQData("43c29b0a-f669-8897-00b7-39d433f6f85e");
		Iterator<StoredProcEQGetEQData> iterator = list.iterator();
		Assert.assertTrue(!iterator.hasNext());
	}
	

	private static void testStoredProcLisaInvestigationShowIndication_Valid() {
		ArrayList<StoredProcLisaInvestigationShowIndication> list = StoredProcLisaInvestigationShowIndication.getLisaInvestigation("166328b0-b266-a7c2-3e41-39d7a866a18c");
		Iterator<StoredProcLisaInvestigationShowIndication> iterator = list.iterator();
		Assert.assertTrue(iterator.hasNext());
		while (iterator.hasNext()) {
			StoredProcLisaInvestigationShowIndication obj = iterator.next();
			Assert.assertTrue(obj != null, "Value cannot be NULL.");
		}
	}

	private static void testStoredProcLisaInvestigationShowIndication_Invalid() {
		ArrayList<StoredProcLisaInvestigationShowIndication> list = StoredProcLisaInvestigationShowIndication.getLisaInvestigation("f90e9dd2-1b65-0111-756a-39d7a8986637");
		Iterator<StoredProcLisaInvestigationShowIndication> iterator = list.iterator();
		Assert.assertTrue(!iterator.hasNext());
	}
}
