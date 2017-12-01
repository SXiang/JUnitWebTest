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
import surveyor.dataprovider.LoadAPITestDataProvider;
import surveyor.scommon.source.BaseTest;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class ProdGeoServerDriverViewAPITest extends BaseTest {

	private static LoadTestExecutor loadTestExecutor = null;

	@BeforeClass
	public static void beforeClass() throws IOException {
		loadTestExecutor = LoadTestExecutor.newExecutor();
	}

	@Test
	@UseDataProvider(value = LoadAPITestDataProvider.LOAD_TEST_API_PROVIDER_GEO_SERVER_DRIVERVIEW_API, location = LoadAPITestDataProvider.class)
	public void executeDriverViewAPITest(String testCaseName, String apiURL, String contentType, String username, String password, HttpMethod method, Integer concurrentRequests, Integer requestsInOneSession, Integer numPrimingRuns, Integer expectedResponseContentLength) throws IOException {
		Log.method("ProdGeoServerDriverViewAPITest.executeDriverViewAPITest", testCaseName, apiURL, contentType, username, password, method, concurrentRequests,
				requestsInOneSession, numPrimingRuns, expectedResponseContentLength);

		TestResult testResult = loadTestExecutor.executeTest(testCaseName, apiURL, contentType, username, password, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength);
		assertTrue(String.format("Load test failed. Test result file '%s' NOT found.", loadTestExecutor.getTestResultFile()), testResult != null);
		Log.info(String.format("Test result -> %s", testResult.toString()));
		assertTrue("Load Test status = [FAIL]. Refer errors in logs.", testResult.isTestPass());
	}
}