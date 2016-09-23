package surveyor.unittest.source;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import common.source.DateUtility;
import common.source.ExceptionUtility;
import common.source.TestSetup;
import surveyor.scommon.source.SurveyorConstants.Environment;

public class PostAutomationReportingAPITest {

	// NOTE: This is local test endpoint. Before running unit tests from this class ensure that this endpoint is alive.
	private static final String automationReportingApiEndpoint = "http://localhost:63087";
	private static TestSetup testSetup = null;

	@BeforeClass
	public static void beforeClass() {
		testSetup = new TestSetup();
		testSetup.setAutomationReportingApiEndpoint(automationReportingApiEndpoint);
	}

	@Test
	public void postAutomationRunResultTest() {
		boolean result = true;
		String message = null;
		try {
			String htmlResultFilePath = Paths.get(TestSetup.getRootPath(),
					"selenium-wd" + File.separator + "reports" + File.separator + "regression" + File.separator
					+ "report-Local-SQAAuto-surveyor.regression.source.SurveyViewPageTest.html").toString();
			testSetup.postAutomationRunResult(htmlResultFilePath);
		} catch (Exception e) {
			result = false;
			message = ExceptionUtility.getStackTraceString(e);
		}
		Assert.assertTrue(message, result);
	}

	@Test
	public void postReportJobPerfStat() {
		boolean result = true;
		String message = null;
		try {
			String reportTitle = "TEST_REPORT_TITLE";
			String reportJobTypeId = "00000000-0000-0000-0008-000000000000";
			String reportJobTypeName = "ReportMeta";
			LocalDateTime reportJobStartTime = DateUtility.fromUnixTime(1465929741383L);
			LocalDateTime reportJobEndTime = DateUtility.fromUnixTime(1465930655257L);
			LocalDateTime testExecutionStartDate = DateUtility.fromUnixTime(1465929741383L);
			LocalDateTime testExecutionEndDate = DateUtility.fromUnixTime(1465930655257L);
			LocalDateTime reportStartTime = DateUtility.fromUnixTime(DateUtility.getCurrentUnixEpochTime() -300000L);
			LocalDateTime reportEndTime = DateUtility.fromUnixTime(DateUtility.getCurrentUnixEpochTime());
			String buildNumber = "2.4.0.0";
			String testCaseID = "TC1234";
			testSetup.postReportJobPerfStat(reportTitle, reportJobTypeId, reportJobTypeName, reportJobStartTime, reportJobEndTime,
					testExecutionStartDate, testExecutionEndDate, reportStartTime, reportEndTime, buildNumber, testCaseID, Environment.P3Scale);
		} catch (Exception e) {
			result = false;
			message = ExceptionUtility.getStackTraceString(e);
		}
		Assert.assertTrue(message, result);
	}

	@Test
	public void postAnalyzerApiPerfStat() {
		boolean result = true;
		String message = null;
		try {
			String aPIName = "Measurement";
			String aPIUrl = "api/analyzer/measurement";
			int numberOfSamples = 100;
			float average = 1.0f;
			float median = 1.0f;
			float responseTime90Pctl = 1.0f;
			float responseTime95Pctl = 1.0f;
			float responsetime99Pctl = 1.0f;
			float min = 1.0f;
			float max = 1.0f;
			float errorPercent = 0.0f;
			float throughputPerSec = 1.0f;
			float kBPerSec = 2.0f;
			LocalDateTime testExecutionStartDate = LocalDateTime.now();
			LocalDateTime testExecutionEndDate = LocalDateTime.now();
			String buildNumber = "2.4.0.0";
			String testCaseID = "TC1234";
			testSetup.postAnalyzerApiPerfStat(aPIName, aPIUrl, numberOfSamples, average, median, responseTime90Pctl, responseTime95Pctl,
					responsetime99Pctl, min, max, errorPercent, throughputPerSec, kBPerSec,
					testExecutionStartDate, testExecutionEndDate, buildNumber, testCaseID, Environment.P3Scale);
		} catch (Exception e) {
			result = false;
			message = ExceptionUtility.getStackTraceString(e);
		}
		Assert.assertTrue(message, result);
	}
}
