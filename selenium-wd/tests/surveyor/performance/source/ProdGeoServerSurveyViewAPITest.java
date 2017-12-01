package surveyor.performance.source;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.LoadTestExecutor;
import common.source.Log;
import common.source.LoadTestExecutor.HttpMethod;
import common.source.LoadTestExecutor.TestResult;
import surveyor.dataprovider.LoadAPITestSurveyViewDataProvider;
import surveyor.scommon.source.BaseTest;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class ProdGeoServerSurveyViewAPITest extends BaseTest {

	private static LoadTestExecutor loadTestExecutor = null;

	@BeforeClass
	public static void beforeClass() throws IOException {
		loadTestExecutor = LoadTestExecutor.newExecutor();
	}

	@Test
	@UseDataProvider(value = LoadAPITestSurveyViewDataProvider.LOAD_TEST_API_PROVIDER_GEO_SERVER_SURVEYVIEW_API_01, location = LoadAPITestSurveyViewDataProvider.class)
	public void executeSurveyViewAPITest01(String testCaseName, String apiURL, String contentType, String username, String password, HttpMethod method, Integer concurrentRequests, Integer requestsInOneSession, Integer numPrimingRuns, Integer expectedResponseContentLength) throws IOException {
		Log.method("ProdGeoServerSurveyViewAPITest.executeSurveyViewAPITest", testCaseName, apiURL, contentType, username, password, method, concurrentRequests,
				requestsInOneSession, numPrimingRuns, expectedResponseContentLength);

		executeTest(testCaseName, apiURL, contentType, username, password, method, concurrentRequests, requestsInOneSession,
				numPrimingRuns, expectedResponseContentLength);
	}

	@Test
	@UseDataProvider(value = LoadAPITestSurveyViewDataProvider.LOAD_TEST_API_PROVIDER_GEO_SERVER_SURVEYVIEW_API_02, location = LoadAPITestSurveyViewDataProvider.class)
	public void executeSurveyViewAPITest02(String testCaseName, String apiURL, String contentType, String username, String password, HttpMethod method, Integer concurrentRequests, Integer requestsInOneSession, Integer numPrimingRuns, Integer expectedResponseContentLength) throws IOException {
		Log.method("ProdGeoServerSurveyViewAPITest.executeSurveyViewAPITest", testCaseName, apiURL, contentType, username, password, method, concurrentRequests,
				requestsInOneSession, numPrimingRuns, expectedResponseContentLength);

		executeTest(testCaseName, apiURL, contentType, username, password, method, concurrentRequests, requestsInOneSession,
				numPrimingRuns, expectedResponseContentLength);
	}

	@Test
	@UseDataProvider(value = LoadAPITestSurveyViewDataProvider.LOAD_TEST_API_PROVIDER_GEO_SERVER_SURVEYVIEW_API_03, location = LoadAPITestSurveyViewDataProvider.class)
	public void executeSurveyViewAPITest03(String testCaseName, String apiURL, String contentType, String username, String password, HttpMethod method, Integer concurrentRequests, Integer requestsInOneSession, Integer numPrimingRuns, Integer expectedResponseContentLength) throws IOException {
		Log.method("ProdGeoServerSurveyViewAPITest.executeSurveyViewAPITest", testCaseName, apiURL, contentType, username, password, method, concurrentRequests,
				requestsInOneSession, numPrimingRuns, expectedResponseContentLength);

		executeTest(testCaseName, apiURL, contentType, username, password, method, concurrentRequests, requestsInOneSession,
				numPrimingRuns, expectedResponseContentLength);
	}

	@Test
	@UseDataProvider(value = LoadAPITestSurveyViewDataProvider.LOAD_TEST_API_PROVIDER_GEO_SERVER_SURVEYVIEW_API_04, location = LoadAPITestSurveyViewDataProvider.class)
	public void executeSurveyViewAPITest04(String testCaseName, String apiURL, String contentType, String username, String password, HttpMethod method, Integer concurrentRequests, Integer requestsInOneSession, Integer numPrimingRuns, Integer expectedResponseContentLength) throws IOException {
		Log.method("ProdGeoServerSurveyViewAPITest.executeSurveyViewAPITest", testCaseName, apiURL, contentType, username, password, method, concurrentRequests,
				requestsInOneSession, numPrimingRuns, expectedResponseContentLength);

		executeTest(testCaseName, apiURL, contentType, username, password, method, concurrentRequests, requestsInOneSession,
				numPrimingRuns, expectedResponseContentLength);
	}

	@Test
	@UseDataProvider(value = LoadAPITestSurveyViewDataProvider.LOAD_TEST_API_PROVIDER_GEO_SERVER_SURVEYVIEW_API_05, location = LoadAPITestSurveyViewDataProvider.class)
	public void executeSurveyViewAPITest05(String testCaseName, String apiURL, String contentType, String username, String password, HttpMethod method, Integer concurrentRequests, Integer requestsInOneSession, Integer numPrimingRuns, Integer expectedResponseContentLength) throws IOException {
		Log.method("ProdGeoServerSurveyViewAPITest.executeSurveyViewAPITest", testCaseName, apiURL, contentType, username, password, method, concurrentRequests,
				requestsInOneSession, numPrimingRuns, expectedResponseContentLength);

		executeTest(testCaseName, apiURL, contentType, username, password, method, concurrentRequests, requestsInOneSession,
				numPrimingRuns, expectedResponseContentLength);
	}

	@Test
	@UseDataProvider(value = LoadAPITestSurveyViewDataProvider.LOAD_TEST_API_PROVIDER_GEO_SERVER_SURVEYVIEW_API_06, location = LoadAPITestSurveyViewDataProvider.class)
	public void executeSurveyViewAPITest06(String testCaseName, String apiURL, String contentType, String username, String password, HttpMethod method, Integer concurrentRequests, Integer requestsInOneSession, Integer numPrimingRuns, Integer expectedResponseContentLength) throws IOException {
		Log.method("ProdGeoServerSurveyViewAPITest.executeSurveyViewAPITest", testCaseName, apiURL, contentType, username, password, method, concurrentRequests,
				requestsInOneSession, numPrimingRuns, expectedResponseContentLength);

		executeTest(testCaseName, apiURL, contentType, username, password, method, concurrentRequests, requestsInOneSession,
				numPrimingRuns, expectedResponseContentLength);
	}

	@Test
	@UseDataProvider(value = LoadAPITestSurveyViewDataProvider.LOAD_TEST_API_PROVIDER_GEO_SERVER_SURVEYVIEW_API_07, location = LoadAPITestSurveyViewDataProvider.class)
	public void executeSurveyViewAPITest07(String testCaseName, String apiURL, String contentType, String username, String password, HttpMethod method, Integer concurrentRequests, Integer requestsInOneSession, Integer numPrimingRuns, Integer expectedResponseContentLength) throws IOException {
		Log.method("ProdGeoServerSurveyViewAPITest.executeSurveyViewAPITest", testCaseName, apiURL, contentType, username, password, method, concurrentRequests,
				requestsInOneSession, numPrimingRuns, expectedResponseContentLength);

		executeTest(testCaseName, apiURL, contentType, username, password, method, concurrentRequests, requestsInOneSession,
				numPrimingRuns, expectedResponseContentLength);
	}

	private void executeTest(String testCaseName, String apiURL, String contentType, String username, String password,
			HttpMethod method, Integer concurrentRequests, Integer requestsInOneSession, Integer numPrimingRuns,
			Integer expectedResponseContentLength) throws IOException {
		TestResult testResult = loadTestExecutor.executeTest(testCaseName, apiURL, contentType, username, password, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength);
		assertTrue(String.format("Load test failed. Test result file '%s' NOT found.", loadTestExecutor.getTestResultFile()), testResult != null);
		Log.info(String.format("Test result -> %s", testResult.toString()));
		assertTrue("Load Test status = [FAIL]. Refer errors in logs.", testResult.isTestPass());
	}
}