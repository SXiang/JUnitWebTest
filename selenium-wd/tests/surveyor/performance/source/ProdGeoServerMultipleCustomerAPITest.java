package surveyor.performance.source;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.UseDataProvider;

import common.source.Log;
import common.source.LogHelper;
import common.source.PollManager;
import common.source.LoadTestExecutor.TestResult;
import common.source.ExceptionUtility;
import common.source.LoadTestJob;
import common.source.LoadTestParallelExecutor;
import surveyor.dataprovider.LoadGeoServerAPITestDataProvider;
import surveyor.dataprovider.LoadGeoServerAPITestMultipleCustomersDataProvider;
import surveyor.scommon.source.BaseTest;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class ProdGeoServerMultipleCustomerAPITest extends BaseTest {
	private static final Integer DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC = 1000;
	private static final Integer RETRIES_PER_INDIVIDUAL_LOADJOB = 2;
	private static final Integer MAX_RETRIES = 300;

	private static LoadTestParallelExecutor parallelTestExecutor = null;

	@BeforeClass
	public static void beforeClass() throws IOException {
		parallelTestExecutor = LoadTestParallelExecutor.newParallelExecutor();
	}

	@Test
	@UseDataProvider(value = LoadGeoServerAPITestMultipleCustomersDataProvider.LOAD_TEST_LOW_FREQUENCY_API_PROVIDER_GEO_SERVER_MULTIPLE_CUSTOMERS_IN_PARALLEL, location = LoadGeoServerAPITestMultipleCustomersDataProvider.class)
	public void multipleCustomersInParallelGeoserverAPITest_LowFrequency(List<LoadTestJob> loadTestJobs) throws Exception {
		Log.method("ProdGeoServerMultipleCustomerAPITest.multipleCustomersInParallelGeoserverAPITest_LowFrequency", LogHelper.collectionToString(loadTestJobs, "loadTestJobs"));

		List<TestResult> results = executeTest(loadTestJobs);
		assertResults(results, loadTestJobs.size());
	}

	@Test
	@UseDataProvider(value = LoadGeoServerAPITestMultipleCustomersDataProvider.LOAD_TEST_MEDIUM_FREQUENCY_API_PROVIDER_GEO_SERVER_MULTIPLE_CUSTOMERS_IN_PARALLEL, location = LoadGeoServerAPITestMultipleCustomersDataProvider.class)
	public void multipleCustomersInParallelGeoserverAPITest_MediumFrequency(List<LoadTestJob> loadTestJobs) throws Exception {
		Log.method("ProdGeoServerMultipleCustomerAPITest.multipleCustomersInParallelGeoserverAPITest_MediumFrequency", LogHelper.collectionToString(loadTestJobs, "loadTestJobs"));

		List<TestResult> results = executeTest(loadTestJobs);
		assertResults(results, loadTestJobs.size());
	}

	@Test
	@UseDataProvider(value = LoadGeoServerAPITestMultipleCustomersDataProvider.LOAD_TEST_HIGH_FREQUENCY_API_PROVIDER_GEO_SERVER_MULTIPLE_CUSTOMERS_IN_PARALLEL, location = LoadGeoServerAPITestMultipleCustomersDataProvider.class)
	public void multipleCustomersInParallelGeoserverAPITest_HighFrequency(List<LoadTestJob> loadTestJobs) throws Exception {
		Log.method("ProdGeoServerMultipleCustomerAPITest.multipleCustomersInParallelGeoserverAPITest_HighFrequency", LogHelper.collectionToString(loadTestJobs, "loadTestJobs"));

		List<TestResult> results = executeTest(loadTestJobs);
		assertResults(results, loadTestJobs.size());
	}

	@Ignore
	@UseDataProvider(value = LoadGeoServerAPITestDataProvider.LOAD_TEST_API_PROVIDER_GEO_SERVER_MULTIPLE_CUSTOMERS_IN_PARALLEL, location = LoadGeoServerAPITestDataProvider.class)
	public void multipleCustomersInParallelDriverViewAPITest(List<LoadTestJob> loadTestJobs) throws Exception {
		Log.method("ProdGeoServerMultipleCustomerAPITest.multipleCustomersInParallelDriverViewAPITest", LogHelper.collectionToString(loadTestJobs, "loadTestJobs"));

		List<TestResult> results = executeTest(loadTestJobs);
		assertResults(results, loadTestJobs.size());
	}

	private void assertResults(List<TestResult> results, Integer expectedResultsCount) {
		assertTrue("Test results should NOT be empty!", results != null);
		results.forEach(result -> logResult(result));
		assertTrue(String.format("Actual and expected results count do NOT match. Expected=[%d], Actual=[%d]", expectedResultsCount, results.size()),
				results.size() == expectedResultsCount);
		results.forEach(result -> assertTrue(String.format("TestJob failed!. TestJob = [%s]", result), result.isTestPass()));
	}

	private List<TestResult> executeTest(List<LoadTestJob> loadTestJobs) throws Exception {
		loadTestJobs.forEach(job -> {
			Log.info(String.format("[ProdGeoServerMultipleCustomerAPITest.executeTest] job -> %s", job.toString()));
			parallelTestExecutor.addTestJob(job);
		});

		// Wait for results to be published by threads under execution.
		Integer maxRetries = RETRIES_PER_INDIVIDUAL_LOADJOB * loadTestJobs.size();
		if (maxRetries > MAX_RETRIES) maxRetries = MAX_RETRIES;
		LoadTestParallelExecutor executor = parallelTestExecutor.startTest();
		PollManager.poll(() -> !testResultsReady(executor, loadTestJobs), DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC, maxRetries);
		return executor.getTestResults();
	}

	private Boolean testResultsReady(LoadTestParallelExecutor executor, List<LoadTestJob> loadTestJobs) {
		try {
			return executor.getTestResults().size() == loadTestJobs.size();
		} catch (Exception e) {
			Log.error(String.format("[Error in testResultsReady] Exception: %s", ExceptionUtility.getStackTraceString(e)));
			return false;
		}
	}

	private void logResult(TestResult result) {
		Log.info(String.format("Logging Result -> %s", result.toString()));
	}
}