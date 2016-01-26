package surveyor.dataaccess.source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

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
		Log.info("Executing testReportPeak_GetReportPeakByPeakNumber_Valid() ...");
		testReportPeak_GetReportPeakByPeakNumber_Valid();
		Log.info("Executing testReportPeak_GetReportPeakByPeakNumber_Invalid() ...");
		testReportPeak_GetReportPeakByPeakNumber_Invalid();
		Log.info("Executing testReportDrivingSurvey_GetReportDrivingSurveyByReportId_Valid() ...");
		testReportDrivingSurvey_GetReportDrivingSurveyByReportId_Valid();
		Log.info("Executing testReportDrivingSurvey_GetReportDrivingSurveyByReportId_Invalid() ...");
		testReportDrivingSurvey_GetReportDrivingSurveyByReportId_Invalid();
		Log.info("Executing testReportCompliance_GetReportComplianceByReportId_Valid() ...");
		testReportCompliance_GetReportComplianceByReportId_Valid();
		Log.info("Executing testReportCompliance_GetReportComplianceByReportId_Invalid() ...");
		testReportCompliance_GetReportComplianceByReportId_Invalid();
		Log.info("Executing testReport_GetReportByReportTitle_Valid() ...");
		testReport_GetReportByReportTitle_Valid();
		Log.info("Executing testReport_GetReportByReportTitle_Invalid() ...");
		testReport_GetReportByReportTitle_Invalid();
		Log.info("Executing testReportCaptureEvent_GetReportCaptureEventByReportId_Valid() ...");
		testReportCaptureEvent_GetReportCaptureEventByReportId_Valid();
		Log.info("Executing testReportCaptureEvent_GetReportCaptureEventByReportId_Invalid() ...");
		testReportCaptureEvent_GetReportCaptureEventByReportId_Invalid();
		testStoredProcSystemHistory_GetStoredProcSystemHistoryByReportId_Valid();
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

	private static void testReportPeak_GetReportPeakByPeakNumber_Valid() {
		Integer validPeakNumber = 1;
		ReportPeak objReportPeak = ReportPeak.getReportPeak(validPeakNumber);
		Assert.assertTrue(objReportPeak != null, "Value cannot be NULL.");
	}

	private static void testReportPeak_GetReportPeakByPeakNumber_Invalid() {
		Integer invalidPeakNumber = -99;
		ReportPeak objReportPeak = ReportPeak.getReportPeak(invalidPeakNumber);
		Assert.assertTrue(objReportPeak == null, "Value should be NULL.");
	}

	private static void testReportDrivingSurvey_GetReportDrivingSurveyByReportId_Valid() {
		String validReportId = "D12F21F5-3FA4-C9EB-C6D6-39D47CC7AB16";
		String validSurveyId = "3E19B2AB-1F88-F86D-5C14-39D477085D64";
		ReportDrivingSurvey objReportDrivingSurvey = ReportDrivingSurvey.getReportDrivingSurvey(validReportId, validSurveyId);
		Assert.assertTrue(objReportDrivingSurvey != null, "Value cannot be NULL.");
	}

	private static void testReportDrivingSurvey_GetReportDrivingSurveyByReportId_Invalid() {
		String invalidReportId = "D12F21F5-3FA4-C9EB-C6D6-00007CC7AB16";
		String invalidSurveyId = "3E19B2AB-1F88-F86D-5C14-000077085D64";
		ReportDrivingSurvey objReportDrivingSurvey = ReportDrivingSurvey.getReportDrivingSurvey(invalidReportId, invalidSurveyId);
		Assert.assertTrue(objReportDrivingSurvey == null, "Value should be NULL.");
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

	private static void testReportCaptureEvent_GetReportCaptureEventByReportId_Valid() {
		String validReportId = "F63164E9-48BC-843D-8D04-39D1529F85A9";
		String validSurveyId = "088822B4-9814-06B8-1049-39CEEE5E4CB8";
		ReportCaptureEvent objReportCaptureEvent = ReportCaptureEvent.getReportCaptureEvent(validReportId, validSurveyId);
		Assert.assertTrue(objReportCaptureEvent != null, "Value cannot be NULL.");
	}

	private static void testReportCaptureEvent_GetReportCaptureEventByReportId_Invalid() {
		String invalidReportId = "F63164E9-48BC-843D-8D04-0000529F85A9";
		String invalidSurveyId = "088822B4-9814-06B8-1049-0000EE5E4CB8";
		ReportCaptureEvent objReportCaptureEvent = ReportCaptureEvent.getReportCaptureEvent(invalidReportId, invalidSurveyId);
		Assert.assertTrue(objReportCaptureEvent == null, "Value should be NULL.");
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

}
