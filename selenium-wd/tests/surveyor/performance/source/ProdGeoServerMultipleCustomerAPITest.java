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
import common.source.LoadTestExecutor.TestResult;
import common.source.LoadTestJob;
import common.source.LoadTestParallelExecutor;
import surveyor.dataprovider.LoadAPITestDataProvider;
import surveyor.dataprovider.LoadAPITestMultipleCustomersDataProvider;
import surveyor.scommon.source.BaseTest;
import surveyor.scommon.source.SurveyorTestRunner;

@RunWith(SurveyorTestRunner.class)
public class ProdGeoServerMultipleCustomerAPITest extends BaseTest {

	private static LoadTestParallelExecutor parallelTestExecutor = null;

	@BeforeClass
	public static void beforeClass() throws IOException {
		parallelTestExecutor = LoadTestParallelExecutor.newParallelExecutor();
	}

	@Test
	@UseDataProvider(value = LoadAPITestMultipleCustomersDataProvider.LOAD_TEST_LOW_FREQUENCY_API_PROVIDER_GEO_SERVER_MULTIPLE_CUSTOMERS_IN_PARALLEL, location = LoadAPITestMultipleCustomersDataProvider.class)
	public void multipleCustomersInParallelGeoserverAPITest_LowFrequency(List<LoadTestJob> loadTestJobs) throws Exception {
		Log.method("ProdGeoServerMultipleCustomerAPITest.multipleCustomersInParallelGeoserverAPITest_LowFrequency", LogHelper.collectionToString(loadTestJobs, "loadTestJobs"));

		List<TestResult> results = executeTest(loadTestJobs);
		assertResults(results, loadTestJobs.size());
	}

	@Test
	@UseDataProvider(value = LoadAPITestMultipleCustomersDataProvider.LOAD_TEST_MEDIUM_FREQUENCY_API_PROVIDER_GEO_SERVER_MULTIPLE_CUSTOMERS_IN_PARALLEL, location = LoadAPITestMultipleCustomersDataProvider.class)
	public void multipleCustomersInParallelGeoserverAPITest_MediumFrequency(List<LoadTestJob> loadTestJobs) throws Exception {
		Log.method("ProdGeoServerMultipleCustomerAPITest.multipleCustomersInParallelGeoserverAPITest_MediumFrequency", LogHelper.collectionToString(loadTestJobs, "loadTestJobs"));

		List<TestResult> results = executeTest(loadTestJobs);
		assertResults(results, loadTestJobs.size());
	}

	@Test
	@UseDataProvider(value = LoadAPITestMultipleCustomersDataProvider.LOAD_TEST_HIGH_FREQUENCY_API_PROVIDER_GEO_SERVER_MULTIPLE_CUSTOMERS_IN_PARALLEL, location = LoadAPITestMultipleCustomersDataProvider.class)
	public void multipleCustomersInParallelGeoserverAPITest_HighFrequency(List<LoadTestJob> loadTestJobs) throws Exception {
		Log.method("ProdGeoServerMultipleCustomerAPITest.multipleCustomersInParallelGeoserverAPITest_HighFrequency", LogHelper.collectionToString(loadTestJobs, "loadTestJobs"));

		List<TestResult> results = executeTest(loadTestJobs);
		assertResults(results, loadTestJobs.size());
	}

	@Ignore
	@UseDataProvider(value = LoadAPITestDataProvider.LOAD_TEST_API_PROVIDER_GEO_SERVER_MULTIPLE_CUSTOMERS_IN_PARALLEL, location = LoadAPITestDataProvider.class)
	public void multipleCustomersInParallelDriverViewAPITest(List<LoadTestJob> loadTestJobs) throws Exception {
		Log.method("ProdGeoServerMultipleCustomerAPITest.multipleCustomersInParallelDriverViewAPITest", LogHelper.collectionToString(loadTestJobs, "loadTestJobs"));

		List<TestResult> results = executeTest(loadTestJobs);
		assertResults(results, loadTestJobs.size());
	}

	private void assertResults(List<TestResult> results, Integer expectedResultsCount) {
		assertTrue("Test results should NOT be empty!", results != null);
		assertTrue(String.format("Actual and expected results count do NOT match. Expected=[%d], Actual=[%d]", expectedResultsCount, results.size()),
				results.size() == expectedResultsCount);
		results.forEach(result -> logResult(result));
		results.forEach(result -> assertTrue(String.format("TestJob failed!. TestJob = [%s]", result), result.isTestPass()));
	}

	private List<TestResult> executeTest(List<LoadTestJob> loadTestJobs) throws Exception {
		loadTestJobs.forEach(job -> {
			Log.info(String.format("[ProdGeoServerMultipleCustomerAPITest.executeTest] job -> %s", job.toString()));
			parallelTestExecutor.addTestJob(job);
		});
		return parallelTestExecutor.startTest().getTestResults();
	}

	private void logResult(TestResult result) {
		Log.info(String.format("Test result -> %s", result.toString()));
	}
}